<template>
  <main class="pt-8 md:pl-64">
    <h1 class="pt-4 text-gray-700 text-xl font-bold uppercase tracking-wider">
      {{ examInfo.title }}
    </h1>
    <div class="my-8">
      <a
      :href="`http://localhost:8000/api/download/${examInfo.examFile}`"
      download
    >
      DOWNLOAD ĐỀ THI
    </a>
    </div>
    <img
      :src="`http://localhost:8000/api/images/${examInfo.examImage}`"
      class="w-full"
    />
    <div class="my-8">
      <a
      :href="`http://localhost:8000/api/download/${examInfo.examFile}`"
      download
    >
      DOWNLOAD ĐỀ THI
    </a>
    </div>
  </main>
</template>
<script>
import axios from "axios";
export default {
  data() {
    return {
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
          url: `http://localhost:8000/api/exams/${this.$route.params.id}`,
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
<style lang="scss" scoped></style>
