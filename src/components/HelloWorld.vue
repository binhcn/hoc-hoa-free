<template>
  
    <div id="app">
        <nav class="fixed top-0 inset-x-0 z-50 text-white bg-gray-800 font-medium flex justify-between items-center shadow-lg" style="height: 5.2rem">
            <!-- Extract: MobileMenu Vue component -->
            <div class="w-16 flex justify-center items-center text-gray-600 sm:hidden">
                <button class="p-1 hover:text-white" :class="{'text-white': isMenuOpen}" @click="toggleMenu">
                    <svg class="fill-current w-10 h-10" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
                        <path v-if="!isMenuOpen" d="M4 5h16a1 1 0 010 2H4a1 1 0 110-2zm0 6h16a1 1 0 010 2H4a1 1 0 010-2zm0 6h16a1 1 0 010 2H4a1 1 0 010-2z" />
                        <path v-else d="M16.24 14.83a1 1 0 0 1-1.41 1.41L12 13.41l-2.83 2.83a1 1 0 0 1-1.41-1.41L10.59 12 7.76 9.17a1 1 0 0 1 1.41-1.41L12 10.59l2.83-2.83a1 1 0 0 1 1.41 1.41L13.41 12l2.83 2.83z" />
                    </svg>
                </button>

                <transition enter-active-class="transition duration-200 ease-in-out transform" enter-class="-translate-x-full opacity-0" leave-active-class="transition duration-200 ease-in-out transform" leave-to-class="-translate-x-full opacity-0">
                    <div v-show="isMenuOpen" class="absolute inset-0 h-screen" @click.self="toggleMenu">
                        <div ref="menu" class="mt-16 absolute inset-0 z-40 overflow-y-auto">
                            <div class="py-6 w-full min-h-full bg-gray-700">
                                <!-- Extract: user_info blade partial -->
                                <div class="px-6 pb-6 flex items-center border-b border-gray-100 sm:flex-col">
                                    <div class="hidden px-3 text-white text-base rounded-full border sm:block">Username</div>
                                    <div class="mr-auto text-white flex-shrink-0 flex justify-center items-center rounded-full border-4 sm:mt-4 sm:mx-auto">
                                        <svg class="fill-current w-24 h-24 sm:w-32 sm:h-32" viewBox="0 0 24 24">
                                            <path d="M12,8L10.67,8.09C9.81,7.07 7.4,4.5 5,4.5C5,4.5 3.03,7.46 4.96,11.41C4.41,12.24 4.07,12.67 4,13.66L2.07,13.95L2.28,14.93L4.04,14.67L4.18,15.38L2.61,16.32L3.08,17.21L4.53,16.32C5.68,18.76 8.59,20 12,20C15.41,20 18.32,18.76 19.47,16.32L20.92,17.21L21.39,16.32L19.82,15.38L19.96,14.67L21.72,14.93L21.93,13.95L20,13.66C19.93,12.67 19.59,12.24 19.04,11.41C20.97,7.46 19,4.5 19,4.5C16.6,4.5 14.19,7.07 13.33,8.09L12,8M9,11A1,1 0 0,1 10,12A1,1 0 0,1 9,13A1,1 0 0,1 8,12A1,1 0 0,1 9,11M15,11A1,1 0 0,1 16,12A1,1 0 0,1 15,13A1,1 0 0,1 14,12A1,1 0 0,1 15,11M11,14H13L12.3,15.39C12.5,16.03 13.06,16.5 13.75,16.5A1.5,1.5 0 0,0 15.25,15H15.75A2,2 0 0,1 13.75,17C13,17 12.35,16.59 12,16V16H12C11.65,16.59 11,17 10.25,17A2,2 0 0,1 8.25,15H8.75A1.5,1.5 0 0,0 10.25,16.5C10.94,16.5 11.5,16.03 11.7,15.39L11,14Z" />
                                        </svg>
                                    </div>
                                    <div class="ml-4 flex-1 flex flex-col sm:mt-4 sm:ml-0 sm:items-center">
                                        <div class="text-white text-2xl sm:hidden">Username</div>
                                        <div class="text-yellow-500 text-xl sm:text-sm">10.000 BET</div>
                                    </div>
                                </div>

                                <!-- Extract: menu_items -->
                                <div class="mt-4">
                                    <!-- TODO: add group here and group-hover to the shevron icon to show it only when the group is hovered -->
                                    <a v-for="(i, idx) in Array(14).keys()" :key="idx" href="#" class="px-10 py-2 flex items-center text-xl text-gray-100 hover:text-orange-500 sm:px-4 sm:text-base">
                                        <div>
                                            <svg class="w-6 h-6 stroke-current" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 72 71">
                                                <path d="M28.559 5.848c2.665-6.75 12.217-6.75 14.882 0l.691 1.751a8 8 0 009.784 4.712l1.8-.551c6.938-2.125 12.895 5.343 9.279 11.635l-.938 1.632a8 8 0 002.416 10.587l1.553 1.063c5.988 4.1 3.862 13.413-3.311 14.51l-1.86.284a8 8 0 00-6.771 8.49l.136 1.877c.528 7.238-8.079 11.382-13.408 6.457l-1.382-1.277a8 8 0 00-10.86 0l-1.382 1.277c-5.33 4.925-13.936.78-13.408-6.457l.136-1.877a8 8 0 00-6.77-8.49l-1.86-.285c-7.174-1.096-9.3-10.409-3.312-14.509l1.553-1.063a8 8 0 002.416-10.587l-.938-1.632c-3.616-6.292 2.34-13.76 9.28-11.635l1.799.55a8 8 0 009.784-4.71l.69-1.752z" fill="none" /></svg>
                                        </div>
                                        <div class="ml-2 flex-1 sm:ml-4">Item #{{ i+1 }}</div>
                                        <div>
                                            <svg class="fill-current w-6 h-6 hover:block" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
                                                <path d="M9.3 8.7a1 1 0 011.4-1.4l4 4a1 1 0 010 1.4l-4 4a1 1 0 01-1.4-1.4l3.29-3.3-3.3-3.3z" /></svg>
                                        </div>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </transition>
            </div>

            <div class="p-4 sm:w-66 flex justify-center items-center">
                    <img class="pl-4 logo" src="../assets/logo1.png" style="width: 130px; height: 80px"/>
                    <div class="pl-2 flex flex-col justify-center items-center">
                        <div class="font-bold text-md lg:text-lg">HỌC HÓA FREE</div>
                     <!-- slogan -->
                    <span class="slogan text-xs">Học mọi nơi - Hỏi mọi lúc</span>
                </div>
            </div>

            <!--  category -->
            <div class="flex justify-center items-center">
                <button @click="() => showTopic(cate.categoryId)" v-for="(cate, idx) in listShow" :key="idx" class="border rounded-md px-4 py-2 border-yellow-500 text-sm lg:text-lg mr-6">{{ cate.title }}</button>
            </div>

            <!-- search bar -->
            <div class="flex items-center justify-center">
<div class="border rounded ">
    <input type="text" class="px-4 py-2 input-search text-black" placeholder="Tìm kiếm...">
    </div>
              <button class="flex px-2 items-center justify-center border-l" style="margin-left: 5px">
                  <i class="fa fa-search"></i>
              </button>
            </div>
                

            <!-- Extract: Notifications Vue component -->
            <div class="notification w-16 h-16 flex justify-center items-center text-gray-600 border-l border-gray-600">
                 
                <button class="p-1 hover:text-white focus:text-white focus:outline-none" :class="{'text-white': isNotificationsOpen}" @click="toggleNotifications">
                    <svg class="fill-current w-10 h-10" viewBox="0 0 24 24">
                        <path d="M11.5,22C11.64,22 11.77,22 11.9,21.96C12.55,21.82 13.09,21.38 13.34,20.78C13.44,20.54 13.5,20.27 13.5,20H9.5A2,2 0 0,0 11.5,22M18,10.5C18,7.43 15.86,4.86 13,4.18V3.5A1.5,1.5 0 0,0 11.5,2A1.5,1.5 0 0,0 10,3.5V4.18C7.13,4.86 5,7.43 5,10.5V16L3,18V19H20V18L18,16M19.97,10H21.97C21.82,6.79 20.24,3.97 17.85,2.15L16.42,3.58C18.46,5 19.82,7.35 19.97,10M6.58,3.58L5.15,2.15C2.76,3.97 1.18,6.79 1,10H3C3.18,7.35 4.54,5 6.58,3.58Z"></path>
                    </svg>
                </button>

                <transition enter-active-class="transition duration-200 ease-in-out" enter-class="opacity-0">
                    <div v-show="isNotificationsOpen" class="absolute inset-0 z-30 min-h-screen bg-black opacity-50 focus:outline-none" @click="toggleNotifications" tabindex="-1">
                    </div>
                </transition>

                <transition enter-active-class="transition duration-200 ease-in-out transform" enter-class="translate-x-full" leave-active-class="transition duration-200 ease-in-out transform" leave-to-class="translate-x-full">
                    <div v-show="isNotificationsOpen" class="pt-16 absolute inset-0 h-full h-screen">
                        <div ref="notifications" class="mt-16 absolute right-0 inset-y-0 w-full max-w-sm overflow-y-auto">
                            <div class="relative z-40 py-2 text-gray-700 bg-white border border-yellow-400 shadow-lg">
                                <a href="#" class="block px-4 py-2 hover:text-gray-100 hover:bg-gray-800" :key="idx" v-for="(i,idx) in Array(5).keys()">
                                    Notification {{ i+1 }}
                                </a>
                            </div>
                        </div>
                    </div>
                </transition>
            </div>
        </nav>

        <aside class="hidden pt-16 w-60 text-white bg-gray-700 fixed inset-y-0 overflow-x-hidden overflow-y-auto sm:block">
            <div class="p-4 min-h-full">
                <!-- Extract: user_info blade partial -->
                <div class="px-6 pb-6 flex items-center border-b border-gray-100 sm:flex-col">
                    
                    <div class="mr-auto text-white flex-shrink-0 flex justify-center items-center rounded-full border-4 sm:mt-4 sm:mx-auto">
                        <svg class="fill-current w-24 h-24 sm:w-32 sm:h-32" viewBox="0 0 24 24">
                            <path d="M12,8L10.67,8.09C9.81,7.07 7.4,4.5 5,4.5C5,4.5 3.03,7.46 4.96,11.41C4.41,12.24 4.07,12.67 4,13.66L2.07,13.95L2.28,14.93L4.04,14.67L4.18,15.38L2.61,16.32L3.08,17.21L4.53,16.32C5.68,18.76 8.59,20 12,20C15.41,20 18.32,18.76 19.47,16.32L20.92,17.21L21.39,16.32L19.82,15.38L19.96,14.67L21.72,14.93L21.93,13.95L20,13.66C19.93,12.67 19.59,12.24 19.04,11.41C20.97,7.46 19,4.5 19,4.5C16.6,4.5 14.19,7.07 13.33,8.09L12,8M9,11A1,1 0 0,1 10,12A1,1 0 0,1 9,13A1,1 0 0,1 8,12A1,1 0 0,1 9,11M15,11A1,1 0 0,1 16,12A1,1 0 0,1 15,13A1,1 0 0,1 14,12A1,1 0 0,1 15,11M11,14H13L12.3,15.39C12.5,16.03 13.06,16.5 13.75,16.5A1.5,1.5 0 0,0 15.25,15H15.75A2,2 0 0,1 13.75,17C13,17 12.35,16.59 12,16V16H12C11.65,16.59 11,17 10.25,17A2,2 0 0,1 8.25,15H8.75A1.5,1.5 0 0,0 10.25,16.5C10.94,16.5 11.5,16.03 11.7,15.39L11,14Z" />
                        </svg>
                    </div>
                    <div class="ml-4 flex-1 flex flex-col sm:mt-4 sm:ml-0 sm:items-center">
                        <div class="text-yellow-500 text-xl font-bold sm:text-sm">
                            {{ selectedClass }}
                        </div>
                    </div>
                </div>

                <!-- Extract: menu_items -->
                <div class="mt-4">
                    <a  v-for="(topic,idx) in listTopic" :key="idx" href="#" class="px-10 py-2 flex justify-between items-start text-sm text-gray-100 hover:text-orange-500 sm:px-4 sm:text-base" >
                        <div style="white-space: pre-wrap; word-break: break-word" class="flex justify-center items-start ">
                            <span><i class="fa fa-splotch text-gray-300 text-xs"></i></span>
                        <div class="ml-2 mt-1 text-left text-xs" :v-html="topic.title">{{topic.title}}</div>
                        </div>
                        

                        <svg class="fill-current w-6 h-6 ml-1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
                            <path d="M9.3 8.7a1 1 0 011.4-1.4l4 4a1 1 0 010 1.4l-4 4a1 1 0 01-1.4-1.4l3.29-3.3-3.3-3.3z" /></svg>
                    </a>
                </div>
            </div>
        </aside>

        <main class="pt-8 sm:pl-64">
        <h1 class="pt-4 text-gray-700 text-xl font-bold uppercase tracking-wider">BÀI TẬP VÀ LỜI GIẢI</h1>

            <div class="p-4">
                
                <div v-for="(i,idx) in Array(15).keys()" :key="idx" class="mt-4 p-4 bg-white rounded shadow font-semibold text-center hover:shadow-md">
                    <strong>Câu {{ i }}</strong>
                    <p class="text-sm text-left font-normal">
                        Nhiệt phân hoàn toàn 11,88 gam X (là muối ở dạng ngậm nước), thu được hỗn hợp Y (gồm khí và hơi) và 3,24 gam một chất rắn Z. Hấp thụ hết Y vào nước, thu được dung dịch T. Cho 80 ml dung dịch NaOH 1M vào T thu được dung dịch chỉ chứa một muối, khối lượng của muối là 6,8. Phần trăm khối lượng nguyên tố oxi trong X là
                    </p>
                    <div class="flex justify-end pr-10">
                        <button class="text-xs font-bold px-4 py-2 text-gray-50 bg-green-500 rounded-md">Xem lời giải</button>
                    </div>
                </div>
            </div>
        </main>
    </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'Home',
  props: {
  },
  data() {
    return {
      exercises: [],
      isNotificationsOpen: false,
      isMenuOpen: false,
      selectedClass: '',
      clikedTopic: false,
      listTopic: [],
      listShow: [
        {
            categoryId: 11,
            title: "LỚP 11",
            topicList: [
                {
                    id: 1,
                    title: "Este - Lipit"
                },
                {
                    id: 2,
                    title: "Cacbohidrat"
                },
                {
                    id: 3,
                    title: "Amin - Aminoaxit - Protein"
                },
                {
                    id: 4,
                    title: "Polime - Vật liệu Polime"
                },
                {
                    id: 5,
                    title: "Đại cương về kim loại"
                },
                {
                    id: 6,
                    title: "Polime - Vật liệu Polime"
                },
                {
                    id: 7,
                    title: "Polime - Vật liệu Polime"
                }
            ]
        },
        {
            categoryId: 12,
            title: "LỚP 12",
            topicList: [
                {
                    id: 1,
                    title: "Phản ứng oxi hóa khử"
                },
                {
                    id: 2,
                    title: "Nhóm halogen"
                },
                {
                    id: 3,
                    title: "Oxi - Lưu huỳnh"
                }
            ]
        },
        {
            categoryId: 13,
            title: "THPT",
            topicList: [
                {
                    id: 1,
                    title: "Đề thi thử 1"
                },
                {
                    id: 2,
                    title: "Đề thi thử 2"
                },
                {
                    id: 3,
                    title: "Đề thi thử 3"
                },
                {
                    id: 4,
                    title: "Đề thi thử 4"
                },
                {
                    id: 5,
                    title: "Đề thi thử 5"
                },
                {
                    id: 6,
                    title: "Đề thi thử 6"
                },
                {
                    id: 7,
                    title: "Đề thi thử 7"
                }
            ]
        },
      ],
  }},
//   created() {
      
//   },
  methods: {
        showSelectedClass(id) {
            let idx = this.listShow.findIndex(cat => cat.categoryId === id)
            if(idx != -1) this.selectedClass =  this.listShow[idx].title
        },
        showTopic(cateId) {
            let idx = this.listShow.findIndex(cat => cat.categoryId === cateId)
            this.listTopic = this.listShow[idx].topicList
            this.showSelectedClass(cateId)
        },
        toggleNotifications() {
            this.$refs.notifications.scrollTo(0, 0)
            this.isNotificationsOpen = !this.isNotificationsOpen;
        },
        toggleMenu() {
            this.$refs.menu.scrollTo(0, 0)
            this.isMenuOpen = !this.isMenuOpen;
        },
        async getData() {
            try {
                let data = await axios({
                    url: 'http://localhost:8000/structure',
                    method: 'GET'
                })
                console.log("data structure", data)
                if(data && data.structure) {
                    this.listShow = data.structure
                }
            } catch(err) {
                console.log("failed roi", err)
            }
        },
        // async getExercises(cateId, topicId) {
        //     try {
        //         let exercises = await axios({
        //         url: `${cateId}/${topicId}`,
        //         method: 'GET'
        //         })
        //         if(exercises) {
        //             this.exercises = exercises
        //         }
        //     } catch(err) {
        //         console.log(err)
        //     }
        // }
  },
  mounted() {
    //   this.getExercises(11, 1)
      this.showSelectedClass(11)
        this.listTopic = this.listShow[0].topicList
        this.getData()
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
        }
    }

}
</script>

<style scoped>
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

.highlight {
    color: orange;
}
</style>
