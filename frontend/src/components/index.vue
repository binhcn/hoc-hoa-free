<template>
    <main class="pt-8 md:pl-64">
      <!-- 1. SHOW LIST EXERCISES -->
      <div v-if="selectedCateId !== 3" class="p-4">
        <h1 class="pt-4 text-gray-700 text-xl font-bold uppercase tracking-wider">
          BÀI TẬP VÀ LỜI GIẢI 
        </h1>
        <!-- 1.1 have exercies -->
        <template v-if="exercises.length > 0">
          <div
          v-for="(exercise, idx) in exercises"
          :key="idx"
          class="mt-4 p-4 bg-white rounded shadow font-semibold text-center hover:shadow-md mb-8"
        >
          <strong>Câu {{ (current -1)*10 + idx + 1 }}</strong>
          <p class="text-sm text-left font-normal">
            <span :innerHTML="exercise.question">
            </span>
            <div v-if="exercise.questionImage">
              <img :src="`${FILE_DOMAIN}${exercise.questionImage}`"/>
            </div>
          </p>
          <div class="flex justify-end pr-10">
            <button
              @click="() => toggleSolution(exercise.id)"
              class="btn-answer text-xs font-bold px-4 py-2 mr-2 text-yellow-400 bg-gray-700 rounded-md"
            >
              {{ selectedSolution == exercise.id ? toggleSolutionSelectedText : toggleSolutionText}}
            </button>
          </div>
          <div v-if="isShowSolution && selectedSolution == exercise.id">
            <img
              :src="`${FILE_DOMAIN}${exercise.solutionImage}`"
              :alt="exercise.solutionImage"
            />
          </div>
        </div>
         <a-pagination 
          v-if="exercises && exercises.length > 0"
          :current="current" :total="total" @change="onChange"
          />
        </template>

        <!-- 1.2 no exercies -->
        <template v-else>
          <div
          class="mt-4 p-4 bg-white rounded shadow font-semibold text-center hover:shadow-md mb-8"
        >Không tìm thấy bài tập nào!</div>
        </template>
      </div>
      <!-- 2. SHOW LIST EXAMS -->
      <div v-else>
        <!-- have exams -->
        <template v-if="isTotalList">
          <h1 class="pt-4 text-gray-700 text-xl font-bold uppercase tracking-wider">
          DANH SÁCH ĐỀ THI
          </h1>
          <div class="flex justify-start" v-for="(exam, idx) in exams" :key="idx">
            <button class="text-blue-500 btn-exam" 
            @click="showDetailExam(exam.id)" v-html="exam.title"></button>
          </div>
          <a-pagination 
          v-if="exams && exams.length > 0"
          :current="current" :total="total" @change="onChange"
          />
        </template>
        <template v-else>
          <ExamDetail @changeTopicTHPT="changeTopicTHPT" :isTotalList="isTotalList" :selectedCateId="selectedCateId" :selectedTopicId="selectedTopicId" :examId="examId"/>
        </template>
        <!-- no exams -->
      </div>
    </main>
</template>
<script>
import {DOMAIN, FILE_DOMAIN} from '../utils/common'
import ExamDetail from './ExamDetail.vue'

export default {
    components : {
      ExamDetail,
    },
    props: {
        exams: {
            type: Array,
            default: []
        },
        exercises: {
            type: Array,
            default: []
        },
        selectedCateId: {
            type: Number,
            default: 1
        },
        selectedTopicId: {
            type: Number,
            default: 0
        },
        total: {
            type: Number,
            default: 1
        }
    },
    data() {
        return {
            examId: null,
            isTotalList: true, // false: detail exam
            DOMAIN: DOMAIN,
            FILE_DOMAIN: FILE_DOMAIN,
            toggleSolutionText: true,
            selectedSolution: 0,
            isShowSolution: false,
            current: 1,
        }
    },
    watch: {
      selectedCateId: function() {
        if(this.selectedCateId !== 3) this.isTotalList = true
      }
    },
    mounted() {
      this.toggleSolutionText = this.toggleSolutionText ? "Xem lời giải" : "Ẩn lời giải"
    },
    methods: {
      changeTopicTHPT(isShowList) {
        this.isTotalList = isShowList
      },
      showDetailExam(id) {
        this.examId = id
        this.isTotalList = false
      },
      toggleSolution(exerciseId) {
        this.selectedSolution = exerciseId;
        this.isShowSolution = !this.isShowSolution;
        this.toggleSolutionSelectedText = this.isShowSolution 
          ? "Ẩn lời giải"
          : "Xem lời giải";
      },
      onChange(current) {
        window.scrollTo(0,0);
        this.current = current;
        let payload = {
          current : current,
          topicId: this.selectedTopicId,
          cateId: this.selectedCateId
      }
        this.$emit('getExer', payload)
      },
    }
}
</script>
<style scoped>
.btn-answer {
  width: 120px;
}
.btn-answer:focus {
  outline: none;
  background: orange;
  color: #1F2937;
}
.btn-exam {
  text-decoration: underline;
}
.btn-exam:focus {
  outline: none;
}
</style>