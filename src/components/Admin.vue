<template>
  <div>
    <div v-if="!isConfirm">
        <div class="text-red-500 py-2">Vui lòng nhập mã xác thực để truy cập</div>
      <input v-model="authCode" class="auth-admin px-4 py-2" />
      <button class="text-xl ml-2 btn-auth-admin" @click="onConfirm()">
        Xác thực
      </button>
    </div>
    <TextEditor />
  </div>
</template>
<script>
import TextEditor from './TextEditor.vue'

export default {
  name: "Admin",
  components: {
      TextEditor
  },
  data() {
    return {
      authCode: "",
      isConfirm: false,
    };
  },
  methods: {
    onConfirm() {
      if (this.authCode != "admin") {
        this.$router.push("/");
      } else {
        this.isConfirm = true;
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
