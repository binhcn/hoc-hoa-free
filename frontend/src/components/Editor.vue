<template>
  <div>
    <QuillEditor
      v-model:content="contentHTML"
      contentType="html"
      theme="snow"
      toolbar="full"
      @keypress="saveQuestion()"
      @paste="saveQuestion()"
    />
  </div>
</template>

<script>
import "@vueup/vue-quill/dist/vue-quill.snow.css";
import { ref, defineComponent } from "vue";
import { QuillEditor, Delta } from "@vueup/vue-quill";

export default {

  components: {
    QuillEditor,
  },
   data() {
    const contentHTML = ref('')
    return { 
      contentHTML, 
    }
  },

  watch: {
    contentHTML() {
      this.saveQuestion()
    }
  },

  methods: {
    saveQuestion() {
      this.$emit('inputData', this.contentHTML.trim());
    },
  },
};
</script>
