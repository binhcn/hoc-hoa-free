<template>
  <div class="container mx-auto">
    <!-- header -->
    <div class="font-bold mt-0 pt-0 mb-4 text-xl" style="margin-top: -40px;">
      THÔNG TIN BÀI TẬP
    </div>

    <div class="mb-4 flex justify-between items-center">
      <div class="flex">
        <!-- Categories -->
        <div class="dropdown">
          <button
            class="btn-admin bg-gray-300 text-gray-700 font-semibold py-2 px-4 rounded flex items-center justify-between"
            style="width: 160px;"
          >
            <span class="mr-1">{{ selectedCate }}</span>
            <svg
              class="fill-current h-4 w-8"
              xmlns="http://www.w3.org/2000/svg"
              viewBox="0 0 20 20"
            >
              <path
                d="M9.293 12.95l.707.707L15.657 8l-1.414-1.414L10 10.828 5.757 6.586 4.343 8z"
              />
            </svg>
          </button>
          <ul class="dropdown-menu absolute hidden text-gray-700 pt-2 ">
            <li v-for="(cate, idx) in listShow" :key="idx" class="">
              <a
                @click="selectCate(cate.categoryId)"
                class="w-44 text-blue-700 rounded-t bg-gray-200 hover:bg-gray-700 hover:text-yellow-500 py-2 px-4 block whitespace-no-wrap"
                href="#"
                >{{ cate.title }}</a
              >
            </li>
          </ul>
        </div>
        <!-- Topics by Categories -->
        <div class="dropdown ml-8">
          <button
            class="btn-admin bg-gray-300 text-gray-700 font-semibold py-2 px-4 rounded flex items-center justify-between"
            style="width: 175px;"
          >
            <span class="mr-1 w-44" style="width: 200px">{{
              selectedTopic.length > 15
                ? selectedTopic.substring(0, 15) + "..."
                : selectedTopic
            }}</span>
            <svg
              class="fill-current h-4 w-8"
              xmlns="http://www.w3.org/2000/svg"
              viewBox="0 0 20 20"
            >
              <path
                d="M9.293 12.95l.707.707L15.657 8l-1.414-1.414L10 10.828 5.757 6.586 4.343 8z"
              />
            </svg>
          </button>
          <ul 
            v-if="this.selectedInfo.cateId"
            class="dropdown-menu absolute hidden text-gray-700 pt-2 ">
            <li class="" v-for="(topic, idx) in lisTopic" :key="idx">
              <a
                @click="selectTopic(topic.id)"
                style="white-space: pre-wrap; word-break: break-word"
                class="w-44 rounded-b bg-gray-200 hover:bg-gray-400 py-2 px-4 block whitespace-no-wrap"
                href="#"
                >{{ topic.title }}
              </a>
            </li>
          </ul>
        </div>

        <!-- search -->
        <button
          :disabled="!this.selectedInfo.cateId || !this.selectedInfo.topicId"
          @click="filterExam()"
          class="btn-admin bg-gray-300 text-gray-700 font-semibold py-2 px-4 rounded items-center ml-8"
        >
          Tìm kiếm
        </button>
      </div>
      <!-- add more -->
      <div>
        <div class="flex items-center justify-center h-full">
          <button
            :disabled="!this.selectedInfo.cateId || !this.selectedInfo.topicId"
            class="add-more py-2 px-4 bg-blue-500 text-white rounded hover:bg-blue-700"
            @click="() => showModal()"
          >
            Thêm bài tập
          </button>
        </div>
        <div
          v-if="visible"
          class="fixed z-10 overflow-y-auto top-0 w-full left-0"
          id="modal"
        >
          <div
            class="flex items-center justify-center min-height-100vh pt-4 px-4 pb-20 text-center sm:block sm:p-0"
          >
            <div class="fixed inset-0 transition-opacity">
              <div class="absolute inset-0 bg-gray-900 opacity-75" />
            </div>
            <span class="hidden sm:inline-block sm:align-middle sm:h-screen"
              >&#8203;</span
            >
            <div
              class="inline-block align-center bg-white rounded-lg text-left overflow-hidden shadow-xl transform transition-all sm:my-8 sm:align-middle sm:max-w-xl sm:w-full"
              role="dialog"
              aria-modal="true"
              aria-labelledby="modal-headline"
            >
              <div 
                class="bg-white px-4 pt-5 pb-4 sm:p-6 sm:pb-4"
                style="max-height: 400px;
    overflow-y: auto;"
              >
                <div>
                  <label class="text-blue-500 font-bold"> {{ selectedInfo.cateId !== 3 ? `Nhập câu hỏi dạng text ` : `Nhập tiêu đề đề thi` }} </label>
                <Editor :question="question" @inputData="updateMessage" />
                </div>


                <!-- IMAGE QUESTION -->
                <div>
                  <label class="text-blue-500 font-bold">
                    {{ selectedInfo.cateId !== 3 ? `Tải hình ảnh câu hỏi dạng` : `Tải hình ảnh đề thi` }} </label><br />
                  <div class="mt-4" v-if="!questionImg">
                  <input type="file" @change="onFileQuestionChange">
                </div>
                <div v-else>
                  <img :src="questionImg" />
                  <button 
                  class="mt-4 text-red-500 font-bold" @click="removeImage(0)"> {{ selectedInfo.cateId !== 3 ? `Xóa hình ảnh câu hỏi` : `Xóa hình ảnh đề thi` }} </button>
                </div>
                </div>
                <!-- IMAGE SOLUTON -->
                <div class="mt-4">
                  <label class="text-blue-500 font-bold">
                    {{ selectedInfo.cateId !== 3 ? `Tải lời giải` : `Tải file đề thi` }} </label><br />
                <div class="mt-4" v-if="!image">
                  <input type="file" @change="onFileSolutionChange">
                </div>
                <div v-else>
                  <img :src="image" />
                  <button 
                  class="mt-4 text-red-500 font-bold" @click="removeImage(1)">{{ selectedInfo.cateId !== 3 ? `Xóa lời giải` : `Xóa file đề thi` }}</button>
                </div>
                </div>
                
              </div>
              <div class="bg-gray-200 px-4 py-3 text-right">
                <button
                  @click="saveExercise"
                  type="button"
                  class="btn-dang py-2 px-4 bg-blue-500 text-white rounded hover:bg-blue-700 mr-2"
                >
                  <i class="pr-2 fas fa-plus"></i>Đăng
                </button>
                <button
                  type="button"
                  class="btn-remove py-2 px-4 bg-gray-500 text-white rounded hover:bg-gray-700 mr-2"
                  @click="closeModal()"
                >
                  <i class="pr-2 fas fa-times"></i>Hủy
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="mb-4 text-xs text-red-500 flex justify-start items-center"><span>Yêu cầu chọn cấp bậc và chủ đề để tìm kiếm và thêm bài tập</span></div>

    <!-- TABLE LIST -->
    <div className="admin-course">
      <table class="table-fill">
        <thead>
          <tr>
            <th className="text-left w-16">STT</th>
            <th className="text-left">Bài tập</th>
            <th className="text-left w-40">Lời giải</th>
            <th className="text-left w-20">Hành động</th>
          </tr>
        </thead>
        <tbody class="table-hover">
          <template v-if="exercises?.length > 0" >
            <template v-for="(exercise, idx) in exercises">
            <tr>
            <td className="text-left">{{ idx + 1 }}</td>
            <td className="text-left">
              <div>
              <span :innerHTML="selectedInfo.cateId !== 3 ? exercise.question : exercise.title">
              </span>
              </div>
              <div v-if="exercise.questionImage">
                <img :src="`${FILE_DOMAIN}${selectedInfo.cateId !== 3 ? exercise.solutionImage : exercise.examFile}`"/>
              </div>
            </td>
            <td className="">
              <a target="blank" :href="`${FILE_DOMAIN}${ selectedInfo.cateId !== 3 ? exercise.solutionImage : exercise.examFile}`">
                 <img class="zoom-solution" style="width: 50px; height: 50px;" :src="`${FILE_DOMAIN}${selectedInfo.cateId !== 3 ? exercise.solutionImage : exercise.examImage}`" alt="..." />
              </a>
            </td>
            <td className="text-left">
              <div className="flex justify-center items-center">
                <button @click="deleteExercise(exercise.id)" className=" action-btn">
                  <i class="fa fa-trash"></i>
                </button>
              </div>
            </td>
          </tr>
          </template>
          </template>
          <template v-else>
            <tr class="text-red-500">
              <td colspan="4">
                Không tìm thấy bài tập nào !
              </td>
            </tr>
          </template>
        </tbody>
      </table>
      <!-- pagination -->
      <!--  v-if="exercises && exercises.length > 0" -->
      <div class="mt-8">
         <a-pagination 
          v-if="exercises && exercises.length > 0"
          :current="current" :total="exercises.length" @change="onChange"
        />
      </div>
       
    </div>
  </div>
</template>
<script>
// import QuillEditor from './Editor.vue'
import axios from 'axios'
import Editor from './Editor.vue'
import {DOMAIN, FILE_DOMAIN} from '../utils/common'

export default {
  components: {
    Editor
  },
  data() {
    return {
      DOMAIN: DOMAIN,
      FILE_DOMAIN: FILE_DOMAIN,
      titleExam: '',
      current: 1,
      questionImage: null,
      solutionImage: null,
      questionImg:'',
      image: '',
      question: '',
      exercises: [],
      selectedCate: "Chọn cấp bậc",
      selectedTopic: "Chọn chủ đề",
      visible: false,
      selectedInfo: {
        cateId: 0,
        topicId: 0,
      },
      lisTopic: [],
      listShow: [
        {
          categoryId: 11,
          title: "LỚP 11",
          topics: [
            {
              topicId: 1,
              title: "Este - Lipit",
            },
            {
              topicId: 2,
              title: "Cacbohidrat",
            },
            {
              topicId: 3,
              title: "Amin - Aminoaxit - Protein",
            },
            {
              topicId: 4,
              title: "Polime - Vật liệu Polime",
            },
            {
              topicId: 5,
              title: "Đại cương về kim loại",
            },
            {
              topicId: 6,
              title: "Polime - Vật liệu Polime",
            },
            {
              topicId: 7,
              title: "Polime - Vật liệu Polime",
            },
          ],
        },
        {
          categoryId: 12,
          title: "LỚP 12",
          topics: [
            {
              topicId: 1,
              title: "Phản ứng oxi hóa khử",
            },
            {
              topicId: 2,
              title: "Nhóm halogen",
            },
            {
              topicId: 3,
              title: "Oxi - Lưu huỳnh",
            },
          ],
        },
        {
          categoryId: 13,
          title: "THPT",
          topics: [
            {
              topicId: 1,
              title: "Đề thi thử 1",
            },
            {
              topicId: 2,
              title: "Đề thi thử 2",
            },
            {
              topicId: 3,
              title: "Đề thi thử 3",
            },
            {
              topicId: 4,
              title: "Đề thi thử 4",
            },
            {
              topicId: 5,
              title: "Đề thi thử 5",
            },
            {
              topicId: 6,
              title: "Đề thi thử 6",
            },
            {
              topicId: 7,
              title: "Đề thi thử 7",
            },
          ],
        },
      ],
    };
  },
  async mounted() {
    await this.getData();
    this.exercises = []
    this.lisTopic = this.listShow[0].topicList;
  },
  watch() {

  },
  methods: {
    onChange(current) {
      this.current = current;
      if(this.selectedInfo.cateId !== 3) {
          this.getExercises(this.selectedInfo.topicId, this.selectedInfo.cateId, "", this.current);
        } else {
          this.getExams(this.selectedInfo.topicId, this.current)
        } 
    },
    async deleteExercise(id) {
      try {
        let data = await axios({
          url: `${DOMAIN}/${this.selectedInfo.cateId !== 3 ? 'exercises' : 'exams'}/${id}`,
          method: 'DELETE'
        })
        if(this.selectedInfo.cateId !== 3) {
          this.getExercises(this.selectedInfo.topicId, this.selectedInfo.cateId, "", this.current);
        } else {
          this.getExams(this.selectedInfo.topicId, this.current)
        } 
      } catch(err) {
        console.log("err", err)
      }
    },
    onFileQuestionChange(e) {
      let files = e.target.files || e.dataTransfer.files;
      if (!files.length)
        return;
      this.questionImage = files[0]
      this.createImage(files[0], true);
    },
    onFileSolutionChange(e) {
      let files = e.target.files || e.dataTransfer.files;
      if (!files.length)
        return;
      this.solutionImage = files[0]
      this.createImage(files[0], false);
    },
    createImage(file, bool) {
      let image = new Image();
      let reader = new FileReader();
      let vm = this;

      reader.onload = (e) => {
        if(bool) vm.questionImg = e.target.result;
        else vm.image = e.target.result;
      };
      reader.readAsDataURL(file);
    },
    removeImage(id) {
      if(id) this.image = '';
      else this.questionImg = '';
    },
    updateMessage(mes) {
      this.question = mes;
    }
    ,
    saveExercise() {
      this.saveChemistryExercise()
      this.closeModal()
      this.image = ''
      this.questionImg = ''
    },
    filterExam() {
      if(this.selectedInfo.cateId !== 3) {
        this.getExercises(this.selectedInfo.topicId, this.selectedInfo.cateId, "", this.current);
      } else {
        this.getExams(this.selectedInfo.topicId, this.current)
      } 
    },
    selectTopic(topicId) {
      this.selectedInfo.topicId = topicId;
      let idx = this.lisTopic.findIndex((topic) => topic.id == topicId);
      if (idx != -1) this.selectedTopic = this.lisTopic[idx].title;

    },
    selectCate(cateId) {
      this.exercises = []
      let idx = this.listShow.findIndex((cate) => cate.categoryId == cateId);
      if (idx != -1) this.lisTopic = this.listShow[idx].topicList;
      this.selectedInfo.cateId = cateId;
      this.selectedCate = this.listShow[idx].title;
    },
    showModal() {
      this.visible = true;
    },
    closeModal() {
      this.visible = false;
    },
     async getData() {
      try {
        let data = await axios({
          url: `${DOMAIN}/structure`,
          method: "GET",
        });
        if (data && data.data.structure) {
          this.listShow = data.data.structure;
        }
      } catch (err) {
        console.log(err);
      }
    },
    async getExercises(topicId, cateId, textSearch = "", currentPage = 1) {
      try {
        let data = await axios({
          url: `${DOMAIN}/exercises?topicId=${topicId}&categoryId=${cateId}&text=${textSearch}&currentPage=${currentPage}&pageSize=10`,
          method: "GET",
        });
        if (data && data.data.exerciseList) {
          this.exercises = data.data.exerciseList;
        }
      } catch (err) {
        console.log(err);
      }
    },
    async getExams(topicId, currentPage = 1) {
      try {
        let data = await axios({
          url: `${DOMAIN}/exams?topicId=${topicId}&currentPage${currentPage}=&pageSize=20`,
          method: 'GET'
        })
        if(data && data.data && data.data.examList) {
          this.exercises = data.data.examList
        }
      } catch(err) {
        console.log("err", err)
      }
    },
    async saveChemistryExercise() {
      try {
        let formData = new FormData();
        if(this.selectedInfo.cateId !== 3) {
          formData.append('topicId', this.selectedInfo.topicId);
          formData.append('categoryId', this.selectedInfo.cateId);
          formData.append('question', this.question);
          formData.append('questionImage', this.questionImage);
          formData.append('solutionImage', this.solutionImage);
          let data = await axios.post(
            `${DOMAIN}/exercises`,
            formData,
            {
              headers: {
                'Content-Type': 'multipart/form-data'
              }
            }
          );
          this.getExercises(this.selectedInfo.topicId, this.selectedInfo.cateId, "", this.current);
        } else {
          formData.append('topicId', this.selectedInfo.topicId);
          formData.append('title', this.question);
          formData.append('examImage', this.questionImage);
          formData.append('examFile', this.solutionImage);
          let data = await axios.post(
            `${DOMAIN}/exams`,
            formData,
            {
              headers: {
                'Content-Type': 'multipart/form-data'
              }
            }
          );
          this.getExams(this.selectedInfo.topicId, this.current)
        }
        
      } catch(err) {
        console.log('err', err)
      }
    }
  },
};
</script>
<style scoped>
.dropdown:hover .dropdown-menu {
  display: block;
  z-index: 10;
}

.btn-admin:focus {
  outline: none;
  background: gray;
  color: white;
}
.admin-course {
  height: 350px;
  overflow-y: auto;
  color: rgb(6, 21, 44);
  overflow-x: hidden;
}
.table-fill {
  width: 100%;
  background: white;
  border-radius: 3px;
  border-collapse: collapse;
  margin: auto;
  width: 100%;
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.1);
  animation: float 5s infinite;
}

th {
  color: #d5dde5;
  background: #1b1e24;
  font-size: 14px;
  text-align: center;
  text-shadow: 0 1px 1px rgba(0, 0, 0, 0.1);
  vertical-align: middle;
  position: sticky;
  z-index: 5;
  top: 0;
}

th:first-child {
  border-top-left-radius: 3px;
}

th:last-child {
  border-top-right-radius: 3px;
  border-right: none;
}

tr {
  height: 50px;
  color: #22262e;
  font-size: 14px;
  text-shadow: 0 1px 1px rgba(256, 256, 256, 0.1);
}

tr:hover td {
  /* background: #22262e; */
  color: #22262e;
  /* color: white; */
}

tr:nth-child(odd) td {
  background: #ebebeb;
}

tr:nth-child(odd):hover td {
  /* background: #4e5066; */
  color: #22262e;
}

tr:last-child td:first-child {
  border-bottom-left-radius: 3px;
}

tr:last-child td:last-child {
  border-bottom-right-radius: 3px;
}

td {
  background: #ffffff;
  padding: 10px;
  text-align: center;
  vertical-align: middle;
  font-size: 13px;
  text-shadow: -1px -1px 1px rgba(0, 0, 0, 0.1);
  border-right: 1px solid #c1c3d1;
}
table > tbody > tr > td {
  color: #22262e;
}
#app
  > div:nth-child(2)
  > div
  > div
  > div.admin-course
  > table
  > tbody
  > tr
  > td:nth-child(2) {
  text-align: left;
}
.add-more:focus {
  outline: none;
}
.btn-dang {
  outline: none;
}
.btn-remove {
  outline: none;
}
</style>
