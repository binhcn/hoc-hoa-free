<template>
  <div>
    <!-- Only admin can access this page -->
    <div v-if="!isShowTable">
      <div class="text-red-500 py-2">Vui lòng nhập mã xác thực để truy cập</div>
      <input @keypress.enter="onConfirm()" type="password" v-model="authCode" class="auth-admin px-4 py-2" />
      <br />
      <button class="border-2 mt-4 border-green-600 rounded-md px-4 py-2 text-xl ml-2 btn-auth-admin" @click="onConfirm()">
        Xác thực
      </button>
    </div>
    <!-- ListTable -->
    <div v-if="isShowTable">
        <TableAdmin />
    </div>
  </div>
</template>
<script>
import TableAdmin from "./TableAdmin.vue";

export default {
  name: "Admin",
  components: {
    TableAdmin,
  },
  data() {
    return {
      authCode: "",
      isShowTable: false,
    };
  },
  methods: {
    onConfirm() {
      if (this.authCode != "!@#admin$%^Tin&*(") {
        this.$router.push("/");
      } else {
        this.isShowTable = true;
      }
    },
  },
  mounted() {},
};
</script>
<style scoped>
.auth-admin {
  border: 1px solid blue;
  border-radius: 5px;
}
.auth-admin:focus-within {
  outline: none;
}
.btn-auth-admin:focus {
  outline: none;
}
</style>
