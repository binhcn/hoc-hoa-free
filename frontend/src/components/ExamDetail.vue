<template>
  <div>
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
  </div>
</template>
<script>
import axios from "axios";
import {DOMAIN, FILE_DOMAIN} from '../utils/common'

export default {
  props: {
    examId: Number,
    selectedCateId: {
      type: Number,
      default: 1
    },
    selectedTopicId: {
      type: Number,
      default: 1
    },
  },
  data() {
    return {
      DOMAIN: DOMAIN,
      FILE_DOMAIN: FILE_DOMAIN,
      examInfo: {},
    };
  },
  async mounted() {
    await this.getExam(this.examId);
  },
  methods: {
    async getExam(id) {
      try {
        let data = await axios({
          url: `${DOMAIN}/exams/${id}`,
          method: "GET",
        });
        if (data && data.data) {
          this.examInfo = data.data;
          let params = {
            capbac: this.selectedCateId,
            chude: this.selectedTopicId,
            dethi: id
          }
          this.$router.push({ path: location.pathname, query: params })
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
