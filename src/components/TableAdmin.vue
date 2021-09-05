<template>
  <div class="container mx-auto">
    <!-- header -->
    <div class="font-bold mt-0 pt-0 mb-2 text-xl">
      THÔNG TIN BÀI TẬP
    </div>

    <div class="mb-4 flex justify-between items-center">
        <div class="flex">
 <!-- Categories -->
      <div class="dropdown">
        <button
          class="btn-admin bg-gray-300 text-gray-700 font-semibold py-2 px-4 rounded inline-flex items-center"
        >
          <span class="mr-1">Chọn cấp bậc</span>
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
          <li class="">
            <a
              class="w-44 rounded-t bg-gray-200 hover:bg-gray-400 py-2 px-4 block whitespace-no-wrap"
              href="#"
              >One</a
            >
          </li>
        </ul>
      </div>
      <!-- Topics by Categories -->
      <div class="dropdown ml-8">
        <button
          class="btn-admin bg-gray-300 text-gray-700 font-semibold py-2 px-4 rounded inline-flex items-center"
        >
          <span class="mr-1">Chọn chủ đề</span>
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
          <li class="">
            <a
              style="white-space: pre-wrap; word-break: break-word"
              class="w-44 rounded-b bg-gray-200 hover:bg-gray-400 py-2 px-4 block whitespace-no-wrap"
              href="#"
              >Three is the magic
              numbeaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaar</a
            >
          </li>
        </ul>
      </div>

      <!-- search -->
      <button
          class="btn-admin bg-gray-300 text-gray-700 font-semibold py-2 px-4 rounded items-center ml-8"
        >Tìm kiếm</button>
        </div>
        <!-- add more -->
     <div>
         <!-- <button
          class="btn-admin bg-red-600 text-gray-50 font-semibold py-2 px-4 rounded items-center ml-8"
        >Thêm bài tập</button> -->
        
        <div class="flex items-center justify-center h-full">
  <button class="py-2 px-4 bg-blue-500 text-white rounded hover:bg-blue-700" @click="() => showModal()">Thêm bài tập</button>
</div>
<div v-if="visible" class="fixed z-10 overflow-y-auto top-0 w-full left-0" id="modal">
  <div class="flex items-center justify-center min-height-100vh pt-4 px-4 pb-20 text-center sm:block sm:p-0">
    <div class="fixed inset-0 transition-opacity">
      <div class="absolute inset-0 bg-gray-900 opacity-75" />
    </div>
    <span class="hidden sm:inline-block sm:align-middle sm:h-screen">&#8203;</span>
    <div class="inline-block align-center bg-white rounded-lg text-left overflow-hidden shadow-xl transform transition-all sm:my-8 sm:align-middle sm:max-w-lg sm:w-full" role="dialog" aria-modal="true" aria-labelledby="modal-headline">
      <div class="bg-white px-4 pt-5 pb-4 sm:p-6 sm:pb-4">
        <label>Name</label>
        <input type="text" class="w-full bg-gray-100 p-2 mt-2 mb-3" />
        <label>Url</label>
        <input type="text" class="w-full bg-gray-100 p-2 mt-2 mb-3" />
      </div>
      <div class="bg-gray-200 px-4 py-3 text-right">
        
        <button type="button" class="py-2 px-4 bg-blue-500 text-white rounded hover:bg-blue-700 mr-2"><i class="pr-2 fas fa-plus"></i>Đăng</button>
        <button type="button" class="py-2 px-4 bg-gray-500 text-white rounded hover:bg-gray-700 mr-2" @click="closeModal()"><i class="pr-2 fas fa-times"></i>Hủy</button>
      </div>
    </div>
  </div>
</div>

     </div>

    </div>


    <!-- TABLE LIST -->
    <div className="admin-course">
      <table class="table-fill">
        <thead>
          <tr>
            <th className="text-left w-16">STT</th>
            <th className="text-left">Bài tập</th>
            <th className="text-left w-40">Lời giải</th>
            <th className="text-left w-28">Cấp bậc</th>
            <th className="text-left w-40">Chủ đề</th>
            <th className="text-left w-20">Hành động</th>
          </tr>
        </thead>
        <tbody class="table-hover">
          <tr key="">
            <td className="text-left">123</td>
            <td className="text-left">
              Nhiệt phân hoàn toàn 11,88 gam X (là muối ở dạng ngậm nước), thu
              được hỗn hợp Y (gồm khí và hơi) và 3,24 gam một chất rắn Z. Hấp
              thụ hết Y vào nước, thu được dung dịch T. Cho 80 ml dung dịch NaOH
              1M vào T thu được dung dịch chỉ chứa một muối, khối lượng của muối
              là 6,8. Phần trăm khối lượng nguyên tố oxi trong X là
            </td>
            <td className="">
              <img
                src="https://picsum.photos/50/50"
                alt="..."
              />
            </td>
            <td className="text-left">THPT</td>

            <td className="text-left">Bài tập tổng hợp</td>
            <td className="text-left">
              <div className="flex justify-center items-center">
                <!-- <button className="mx-4 action-btn"><i class="fa fa-edit"></i></button> -->
                <button className=" action-btn">
                  <i class="fa fa-trash"></i>
                </button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>
<script>
export default {
    data() {
    return {
      visible: false,
    };
  },
  methods: {
    showModal() {
        this.visible = true
        console.log(this.visible)
    },
    closeModal() {
        this.visible = false
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
  background: #22262e;
  color: white;
}

tr:nth-child(odd) td {
  background: #ebebeb;
}

tr:nth-child(odd):hover td {
  background: #4e5066;
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
#app > div:nth-child(2) > div > div > div.admin-course > table > tbody > tr > td:nth-child(2) {
    text-align: left;
}
</style>
