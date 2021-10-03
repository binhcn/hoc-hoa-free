<template>
  <main class="pt-8 md:pl-64">
    <div class="flex justify-start items-center">
    </div>
    <h1 v-html="examInfo.title" class="pt-4 text-gray-700 text-xl font-bold uppercase tracking-wider">
      
    </h1>
    
    <div class="my-8">
      <a
      :href="`${FILE_DOMAIN}${examInfo.examFile}`"
      download
    >
      DOWNLOAD ĐỀ THI
    </a>
    </div>
    <img
      v-if="examInfo.examImage"
      :src="`${FILE_DOMAIN}${examInfo.examImage || 1}`"
      class="w-full"
    />
    <div class="my-8">
      <a
      :href="`${FILE_DOMAIN}${examInfo.examFile}`"
      download
    >
      DOWNLOAD ĐỀ THI
    </a>
    </div>
  </main>
</template>
<script>
import axios from "axios";
import {DOMAIN, FILE_DOMAIN} from '../utils/common'

export default {
  data() {
    return {
      DOMAIN: DOMAIN,
      FILE_DOMAIN: FILE_DOMAIN,
      examInfo: {},
    };
  },
  async mounted() {
    await this.getExam();
  },
  methods: {
    async getExam() {
      try {
        let data = await axios({
          url: `${DOMAIN}/exams/${this.$route.params.id}`,
          method: "GET",
        });
        if (data && data.data) {
          this.examInfo = data.data;
        }
      } catch (err) {
        console.log("err", err);
      }
    },
  },
};
</script>
<style scoped>
.btn-return {
  text-decoration: underline;
}
.btn-return:focus {
  outline: none;
}
</style>
