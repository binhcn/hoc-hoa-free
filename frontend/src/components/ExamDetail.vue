<template>
    <div>nhiph</div>
</template>
<script>
import axios from 'axios'
export default {
    data() {
        return {
            examInfo: {}
        }
    },
    async mounted() {
        await this.getExam()
    },
    methods: {
        async getExam() {
            try {
                let data = await axios({
                    url: `http://localhost:8000/api/exams/${this.$route.params.id}`,
                    method: 'GET'
                })
                if(data && data.data) {
                    console.log(data)
                }
            } catch(err) {
                console.log("err", err)
            }
        }
    }
}
</script>
<style lang="scss" scoped>

</style>