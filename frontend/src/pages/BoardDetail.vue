<template>
  <div>

    <div class="card-body">

      <form method="post">
        <div class="mb-3 mt-3">
          <label for="bno" class="form-label">bno</label>
          <input type="text" class="form-control" id="id" name="id"
                 v-model="form.id" readonly>
        </div>
        <div class="mb-3">
          <label for="title" class="form-label">title</label>
          <input type="text" class="form-control" id="title" name="title"
                 v-model="form.title">
        </div>
        <div class="mb-3">
          <label for="content" class="form-label">content</label>
          <textarea class="form-control" id="content" name="content"
                    v-model="form.content"></textarea>
        </div>
        <div class="mb-3">
          <label for="writer" class="form-label">writer</label>
          <input type="text" class="form-control" id="writer" name="writer"
                 v-model="form.writer" readonly>
        </div>
        <router-link to="/Board" class="btn btn-outline-primary">목록</router-link>
        <button class="btn btn-outline-warning w-100" @click="submit()">작성완료</button>

      </form>
    </div>
  </div>
</template>

<script>

// import axios from "axios";
// import router from "@/scripts/router";

// import {reactive} from "vue";
import axios from "axios";
// import router from "@/scripts/router";

export default {
  name: "BoardDetail",
  data() {
    return {
      requestBody: this.$route.query,
      id: this.$route.query.id,
      form: {
        id: "",
        title: "",
        content: "",
        writer: ""
      }
    }
  },
  setup() {
    // const load = ()=>{
    //   axios.get('/api/board/detail/'+this.id).then(({data})=>{
    //     this.id = data.id;
    //     this.title = data.title;
    //     this.content = data.content;
    //     this.writer = data.writer;
    //   }).catch((err) =>{
    //     console.log(err);
    //   })
    // }

    const submit = () => {
      // const args = JSON.parse(JSON.stringify(state.form));
      // args.items = JSON.stringify(state.items);
      // axios.post("/api/board/wirte", args).then(() => {
      //   alert('주문 완료하였습니다.');
      //   router.push({path: "/Board"})
      // })
    }
    // load();
    // console.log("데이터"+ state.item)
    return {submit}
  },
  mounted() {
    this.load()
  },
  methods: {
    load() {
      if(this.id !== undefined){
        axios.get(`/api/board/detail/` + this.id).then(({data}) => {
          this.form.id = data.id;
          this.form.title = data.title;
          this.form.content = data.content;
          this.form.writer = data.writer;
        }).catch((err) => {
          console.log(err);
        })
      }
    }
  }
}
</script>

<style scoped>

</style>