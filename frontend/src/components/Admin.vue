<template>
  <div>
    <!-- Confirm  -->
    <!-- <div v-if="!isConfirm">
      <div class="text-red-500 py-2">Vui lòng nhập mã xác thực để truy cập</div>
      <input v-model="authCode" class="auth-admin px-4 py-2" />
      <button class="text-xl ml-2 btn-auth-admin" @click="onConfirm()">
        Xác thực
      </button>
    </div> -->
    <!-- ListTable -->
    <div v-if="!isShowTable">
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
      isConfirm: false,
      isShowTable: false,
    };
  },
  methods: {
    onConfirm() {
      if (this.authCode != "admin") {
        this.$router.push("/");
      } else {
        this.isConfirm = true;
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
