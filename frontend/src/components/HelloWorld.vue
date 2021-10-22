<template>
  <div id="app">
    <nav
      class="fixed top-0 inset-x-0 z-50 text-white bg-gray-800 font-medium flex justify-between items-center shadow-lg"
      style="height: 5.2rem"
    >
      <!-- Extract: MobileMenu Vue component -->
      <div
        class="w-16 flex justify-center items-center text-gray-600 md:hidden"
      >
        <button
          class="p-1 hover:text-white"
          :class="{ 'text-white': isMenuOpen }"
          @click="toggleMenu"
        >
          <svg
            class="fill-current w-10 h-10"
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 24 24"
          >
            <path
              v-if="!isMenuOpen"
              d="M4 5h16a1 1 0 010 2H4a1 1 0 110-2zm0 6h16a1 1 0 010 2H4a1 1 0 010-2zm0 6h16a1 1 0 010 2H4a1 1 0 010-2z"
            />
            <path
              v-else
              d="M16.24 14.83a1 1 0 0 1-1.41 1.41L12 13.41l-2.83 2.83a1 1 0 0 1-1.41-1.41L10.59 12 7.76 9.17a1 1 0 0 1 1.41-1.41L12 10.59l2.83-2.83a1 1 0 0 1 1.41 1.41L13.41 12l2.83 2.83z"
            />
          </svg>
        </button>

        <transition
          enter-active-class="transition duration-200 ease-in-out transform"
          enter-class="-translate-x-full opacity-0"
          leave-active-class="transition duration-200 ease-in-out transform"
          leave-to-class="-translate-x-full opacity-0"
        >
          <div
            v-show="isMenuOpen"
            class="absolute inset-0 h-screen"
            @click.self="toggleMenu"
          >
            <div ref="menu" class="mt-16 absolute inset-0 z-40 overflow-y-auto">
              <div class="py-6 w-full min-h-full bg-gray-700">
                <!-- Extract: user_info blade partial -->
                <div
                  class="px-6 pb-6 flex items-center border-b border-gray-100 sm:flex-col"
                >
                  <div
                    class="ml-4 flex-1 flex flex-col sm:mt-4 sm:ml-0 sm:items-center"
                  >
                    <div class="text-white text-md md:hidden">CHỌN CHỦ ĐỀ</div>
                  </div>
                </div>

                <!-- Extract: menu_items -->
                <div class="mt-4">
                  <a
                    v-for="(topic, idx) in listTopic"
                    :key="idx"
                    href="#"
                    class="px-10 py-2 flex items-center text-xl text-gray-100 hover:text-orange-500 sm:px-4 sm:text-base"
                  >
                    <div
                      @click="getExercisesByTopic(topic.id)"
                      style="white-space: pre-wrap; word-break: break-word"
                      :class="[topic.id === selectedTopicId ? 'selected-topic' : '']"
                      class="flex justify-center items-start "
                    >
                     <span><i :class="[topic.id === selectedTopicId ? 'selected-topic' : '']" class="fa fa-splotch text-gray-300 text-xs"></i></span>
                    <div class="ml-2 mt-2 text-left text-xs" :v-html="topic.title">
                      {{ topic.title }}
                    </div>
                    </div>
                    <div>
                      <svg
                        class="fill-current w-6 h-6 hover:block"
                        xmlns="http://www.w3.org/2000/svg"
                        viewBox="0 0 24 24"
                      >
                        <path
                          d="M9.3 8.7a1 1 0 011.4-1.4l4 4a1 1 0 010 1.4l-4 4a1 1 0 01-1.4-1.4l3.29-3.3-3.3-3.3z"
                        />
                      </svg>
                    </div>
                  </a>
                </div>
              </div>
            </div>
          </div>
        </transition>
      </div>

      <div class="p-4 sm:w-66 flex justify-center items-center">
        <img
          class="pl-4 logo"
          src="../assets/logo1.png"
          style="width: 130px; height: 80px"
        />
        <div class="pl-2 flex flex-col justify-center items-center common-slogan">
          <div class="font-bold text-md lg:text-lg">HỌC HÓA FREE</div>
          <!-- slogan -->
          <span class="slogan text-xs text-yellow-500">Học mọi nơi - Hỏi mọi lúc</span>
        </div>
      </div>

      <!--  category -->
      <div class="flex justify-center items-center">
        <button
          @click="() => showTopic(cate.categoryId)"
          v-for="(cate, idx) in listShow"
          :key="idx"
          class="btn-cate border rounded-md px-4 py-2 border-gray-50 text-gray-50 text-sm lg:text-lg mr-2 md:mr-6"
          :class="[cate.categoryId === selectedCateId ? 'text-yellow-500 font-bold border-yellow-500' : '']"
        >
          {{ cate.title }}
        </button>
      </div>

      <!-- search bar -->
      <div class="flex items-center justify-center search-bar">
        <div class="border rounded ">
          <input
            v-model="textSearch"
            type="text"
            class="px-4 py-2 input-search text-black"
            placeholder="Tìm kiếm..."
            @keypress.enter="searchExercises()"
          />
        </div>
        <button
          @click="searchExercises()"
          class="flex px-2 items-center justify-center border-l"
          style="margin-left: 5px"
        >
          <i class="fa fa-search"></i>
        </button>
      </div>

      <!-- Extract: Notifications Vue component -->
      <div
        class="notification w-16 h-16 flex justify-center items-center text-gray-600 border-l border-gray-600"
      >
        <button
          class="p-1 hover:text-white focus:text-white focus:outline-none"
          :class="{ 'text-white': isNotificationsOpen }"
          @click="toggleNotifications"
        >
          <svg class="fill-current w-10 h-10" viewBox="0 0 24 24">
            <path
              d="M11.5,22C11.64,22 11.77,22 11.9,21.96C12.55,21.82 13.09,21.38 13.34,20.78C13.44,20.54 13.5,20.27 13.5,20H9.5A2,2 0 0,0 11.5,22M18,10.5C18,7.43 15.86,4.86 13,4.18V3.5A1.5,1.5 0 0,0 11.5,2A1.5,1.5 0 0,0 10,3.5V4.18C7.13,4.86 5,7.43 5,10.5V16L3,18V19H20V18L18,16M19.97,10H21.97C21.82,6.79 20.24,3.97 17.85,2.15L16.42,3.58C18.46,5 19.82,7.35 19.97,10M6.58,3.58L5.15,2.15C2.76,3.97 1.18,6.79 1,10H3C3.18,7.35 4.54,5 6.58,3.58Z"
            ></path>
          </svg>
        </button>

        <transition
          enter-active-class="transition duration-200 ease-in-out"
          enter-class="opacity-0"
        >
          <div
            v-show="isNotificationsOpen"
            class="absolute inset-0 z-30 min-h-screen bg-black opacity-50 focus:outline-none"
            @click="toggleNotifications"
            tabindex="-1"
          ></div>
        </transition>

        <transition
          enter-active-class="transition duration-200 ease-in-out transform"
          enter-class="translate-x-full"
          leave-active-class="transition duration-200 ease-in-out transform"
          leave-to-class="translate-x-full"
        >
          <div
            v-show="isNotificationsOpen"
            class="pt-16 absolute inset-0 h-full h-screen"
          >
            <div
              ref="notifications"
              class="mt-16 absolute right-0 inset-y-0 w-full max-w-sm overflow-y-auto"
            >
              <div
                class="relative z-40 py-2 text-gray-700 bg-white border border-yellow-400 shadow-lg"
              >
                <a
                  href="#"
                  class="block px-4 py-2 hover:text-gray-100 hover:bg-gray-800"
                  :key="idx"
                  v-for="(i, idx) in Array(5).keys()"
                >
                  Notification {{ i + 1 }}
                </a>
              </div>
            </div>
          </div>
        </transition>
      </div>
    </nav>

    <aside
      class="hidden pt-16 w-60 text-white bg-gray-700 fixed inset-y-0 overflow-x-hidden overflow-y-auto md:block"
    >
      <div class="p-4 min-h-full">
        <!-- Extract: user_info blade partial -->
        <div
          class="px-6 pb-6 flex items-center border-b border-gray-100 sm:flex-col"
        >
          <div
            class="ml-4 flex-1 flex flex-col sm:mt-4 sm:ml-0 sm:items-center"
          >
            <div class="text-white text-md mt-8">CHỌN CHỦ ĐỀ</div>
          </div>
          <div
            class="ml-4 flex-1 flex flex-col sm:mt-4 sm:ml-0 sm:items-center"
          >
            <div class="text-yellow-500 text-xl font-bold sm:text-sm">
              {{ selectedClass }}
            </div>
          </div>
        </div>

        <!-- Extract: menu_items -->
        <div class="mt-4">
          <a
            v-for="(topic, idx) in listTopic"
            :key="idx"
            href="#"
            class="px-10 py-2 flex justify-between items-start text-sm text-gray-100 hover:text-orange-500 sm:px-4 sm:text-base"
          >
            <div
              @click="getExercisesByTopic(topic.id)"
              style="white-space: pre-wrap; word-break: break-word"
              :class="[topic.id === selectedTopicId ? 'selected-topic' : '']"
              class="flex justify-center items-start "
            >
              <span><i :class="[topic.id === selectedTopicId ? 'selected-topic' : '']" class="fa fa-splotch text-gray-300 text-xs"></i></span>
              <div class="ml-2 mt-1 text-left text-xs" :v-html="topic.title">
                {{ topic.title }}
              </div>
            </div>

            <svg
              class="fill-current w-6 h-6 ml-1"
              xmlns="http://www.w3.org/2000/svg"
              viewBox="0 0 24 24"
            >
              <path
                d="M9.3 8.7a1 1 0 011.4-1.4l4 4a1 1 0 010 1.4l-4 4a1 1 0 01-1.4-1.4l3.29-3.3-3.3-3.3z"
              />
            </svg>
          </a>
        </div>
      </div>
    </aside>
    <router-view :isSearch="searchStatus" @getExer="retrieveExercise" :exercises="exercises" :selectedCateId="selectedCateId" :selectedTopicId="selectedTopicId" :total="total" :exams="exams"></router-view>
  </div>
</template>

<script>
import axios from "axios";
import Index from './index.vue'
import {DOMAIN} from '../utils/common'

export default {
  name: "Home",
  props: {},
  data() {
    return {
      searchStatus: false,
      total: 0,
      exams: [],
      selectedCateId: 1,
      selectedTopicId: 0,
      textSearch: "",
      exercises: [],
      isNotificationsOpen: false,
      isMenuOpen: false,
      selectedClass: "",
      clikedTopic: false,
      listTopic: [],
      listShow: [
        {
          categoryId: 11,
          title: "LỚP 11",
          topicList: [],
        },
        
        
      ],
    };
  },
  methods: {
    retrieveExercise(payload) {
      let {current, topicId, cateId, isSearch} = payload
      this.searchStatus = isSearch
      if(cateId !== 3) this.getExercises(topicId, cateId, this.textSearch, current)
      else this.getExams(topicId, current)
    },
    searchExercises() {
      this.searchStatus = true
      this.getExercises(null, null, this.textSearch, 1)
    },
    showSelectedClass(id) {
      let idx = this.listShow.findIndex((cat) => cat.categoryId === id);
      if (idx != -1) this.selectedClass = this.listShow[idx].title;
    },
    showTopic(cateId) {
      let idx = this.listShow.findIndex((cat) => cat.categoryId === cateId);
      this.listTopic = this.listShow[idx].topicList;
      this.showSelectedClass(cateId);
      this.selectedCateId = cateId
      this.selectedTopicId = null
      let params = {
          capbac: this.selectedCateId,
          chude: this.null
        }
      this.$router.push({ path: location.pathname, query: params })
      if(this.selectedCateId !== 3) {
        this.getExercises(null, cateId, "", 1)
      } else {
        this.getExams(this.selectedTopicId, 1)
      }
    },
    toggleNotifications() {
      this.$refs.notifications.scrollTo(0, 0);
      this.isNotificationsOpen = !this.isNotificationsOpen;
    },
    toggleMenu() {
      this.$refs.menu.scrollTo(0, 0);
      this.isMenuOpen = !this.isMenuOpen;
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
        // this.selectedTopicId = topicId
        if(topicId == null) topicId = ''
        if(cateId == null) cateId = ''
        textSearch = textSearch.trim()
        let data = await axios({
          url: `${DOMAIN}/exercises?topicId=${topicId}&categoryId=${cateId}&text=${textSearch}&currentPage=${currentPage}&pageSize=10`,
          method: "GET",
        });
        if (data && data.data && data.data.exerciseList) {
          let params = {
            capbac: this.selectedCateId,
            chude: this.selectedTopicId
          }
          this.exercises = data.data.exerciseList;
          this.total = data.data.total
          this.$router.push({ path: location.pathname, query: params })
        }
      } catch (err) {
        console.log(err);
      }
    },
    async getExams(id, currentPage = 1) {
      try {
        if(id == null) id = ''
        let data = await axios({
          url: `${DOMAIN}/exams?topicId=${id}&currentPage=${currentPage}&pageSize=10`,
          method: 'GET'
        })
        if(data && data.data && data.data.examList) {
          let params = {
            capbac: this.selectedCateId,
            chude: this.selectedTopicId
          }
          this.exams = data.data.examList
          this.total = data.data.total
          this.$router.push({ path: location.pathname, query: params })
        }
      } catch(err) {
        console.log("err", err)
      } 
    },
    getExercisesByTopic(id) {
      this.selectedTopicId = id
      if(this.selectedCateId !== 3) {
        this.getExercises(id, this.selectedCateId, this.textSearch, 1);
      } else {
        this.getExams(this.selectedTopicId, 1)
      }
    },
  },
  created() {},
  async mounted() {
    await this.getData()
    this.selectedCateId = 1
    this.selectedTopicId = null
    await this.getExercises(this.selectedTopicId, this.selectedCateId,'', 1);
    this.showSelectedClass(1);
    this.listTopic = this.listShow[0].topicList;
  },
  watch: {
    isNotificationsOpen: function(isOpen) {
      if (isOpen) {
        document.body.style.setProperty("overflow", "hidden");
      } else {
        document.body.style.removeProperty("overflow");
      }
    },
    isMenuOpen: function(isOpen) {
      if (isOpen) {
        document.body.style.setProperty("overflow", "hidden");
        document.body.style.setProperty("position", "relative");
      } else {
        document.body.style.removeProperty("overflow");
        document.body.style.removeProperty("position");
      }
    },
  },
};
</script>

<style scoped>
.activeCate {
  background: blue;
}
.input-search:focus {
  outline: none;
  border: 1px solid rgb(40, 73, 97);
  box-shadow: 0 0 rgb(76, 161, 201);
}
.input-search:focus-within {
  outline: none;
  border: 1px solid rgb(40, 73, 97);
  box-shadow: 0 0 rgb(76, 161, 201);
}

@media only screen and (max-width: 992px) {
  .notification {
    display: none;
  }
}

@media only screen and (max-width: 992px) {
  .logo {
    display: none;
  }
}

@media only screen and (max-width: 560px) {
  .common-slogan {
    display: none;
  }
}

@media only screen and (max-width: 768px) {
  .search-bar {
    display: none;
  }
}

.selected-topic {
  text-transform: uppercase;
  font-weight: 700;
  color: orange;
}

.btn-cate:focus {
 outline: none;
}

.highlight {
  color: orange;
}
</style>

    
