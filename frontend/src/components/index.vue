<template>
    <main class="pt-8 md:pl-64">
      <h1 class="pt-4 text-gray-700 text-xl font-bold uppercase tracking-wider">
        BÀI TẬP VÀ LỜI GIẢI
      </h1>

      <div class="p-4">
        <template v-if="exercises.length > 0 && this.selectedCateId !== 3">
          <div
          v-for="(exercise, idx) in exercises"
          :key="idx"
          class="mt-4 p-4 bg-white rounded shadow font-semibold text-center hover:shadow-md mb-8"
        >
          <strong>Câu {{ idx + 1 }}</strong>
          <p class="text-sm text-left font-normal">
            <span :innerHTML="exercise.question">
              </span>
              <div v-if="exercise.questionImage">
                <img :src="`http://localhost:8000/api/images/${exercise.solutionImage}`"/>
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
              :src="`http://localhost:8000/api/images/${exercise.solutionImage}`"
              :alt="exercise.solutionImage"
            />
          </div>
        </div>
        </template>
        <template v-else-if="this.selectedCateId === 3">
          <div class="flex justify-start" v-for="exam in exams" :key="exam.topicId">
            <router-link target='_blank' class="text-left" :to="`/exam/${exam.id}`">
              {{ exam.title }}
            </router-link>
          </div>
        </template>
        <template v-else>
          <div
          class="mt-4 p-4 bg-white rounded shadow font-semibold text-center hover:shadow-md mb-8"
        >Không tìm thấy bài tập nào!</div>
        </template>
        <a-pagination 
          v-if="exercises && exercises.length > 0"
          :current="current" :total="total" @change="onChange"
        />
      </div>
    </main>
</template>
<script>
export default {
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
            default: 1
        },
        total: {
            type: Number,
            default: 1
        }
    },
    data() {
        return {
            toggleSolutionText: true,
            selectedSolution: 0,
            isShowSolution: false,
            current: 1,
        }
    },
    mounted() {
        console.log("err", this.exercises)
        this.toggleSolutionText = this.toggleSolutionText ? "Xem lời giải" : "Ẩn lời giải"
    },
    methods: {
        toggleSolution(exerciseId) {
            this.selectedSolution = exerciseId;
            this.isShowSolution = !this.isShowSolution;
            this.toggleSolutionSelectedText = this.isShowSolution 
                ? "Ẩn lời giải"
                : "Xem lời giải";
        },
        onChange(current) {
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
  width: 100px;
}
.btn-answer:focus {
  outline: none;
  background: orange;
  color: #1F2937;
}
</style>