<template>
  <div class="board">
    <div class="container">
      <button class="w-50 btn btn-lg btn-primary" @click="write">글쓰기</button>
      <table class="table">
        <thead>
        <tr class="table-header">
          <th>번호</th>
          <th>제목</th>
          <th>작성자</th>
        </tr>
        </thead>
        <tbody>
        <tr class="active-table-tab" v-for="(item,idx) in state.items" :key="idx">
          <td>{{ item.id }}</td>
          <td><a href="javascript:;" @click="detail(`${item.id}`)">{{ item.title }}</a></td>
          <!--            <router-link to="/boardDetail"  >상세보기</router-link>-->
          <!--            <router-link :to="{ name: 'BoardDetail', params: { id: item.id }}">User</router-link>-->
          <td>{{ item.writer }}</td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import {reactive} from "vue";
import axios from "axios";
import lib from "@/scripts/lib";
import router from "@/scripts/router";

export default {
  name: "Board",
  data() {
    return {
      requestBody: {}
    }
  },
  setup() {
    const state = reactive({
      items: []

    })
    const load = () => {
      axios.get("/api/board/list").then(({data}) => {
        console.log(data);
        state.items = data;

      })
    };
    const write = () => {
      router.push('/BoardDetail')
    }

    load();
    return {state, lib,write}
  },
  methods: {
    fnView(idx) {
      this.requestBody.idx = idx
      this.$router.push({
        path: './detail',
        query: this.requestBody
      })
    },
    detail(id) {   //셋업으로 불러오니까 오류남..  애로우함수처럼 적어도 에러남..
      this.requestBody.id = id;
      router.push({
        path: '/boardDetail',
        query: this.requestBody
      })
    }
  }
}
</script>

<style scoped>
</style>
