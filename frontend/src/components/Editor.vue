<template>
<div>
 <h1>Content Type</h1>
  <QuillEditor v-model:content="contentHTML" contentType="html" theme="snow" toolbar="full"/>
  <pre>{{ contentHTML }}</pre>
</div>
 
</template>

<script lang="ts">
import '@vueup/vue-quill/dist/vue-quill.snow.css';
import { ref, defineComponent } from 'vue'
import { QuillEditor, Delta } from '@vueup/vue-quill'
export default defineComponent({
  components: {
    QuillEditor,
  },
  setup: () => {
    const contentHTML = ref('<h1>This is html header</h1>')
    return { contentHTML }
  },
  
})
</script>
