<template>
  <div class="board">
    <div class="container">
      <table class="table">
        <thead>
          <tr class="table-header">
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(item,idx) in state.items" :key="idx">
            <td>{{item.id}}</td>
            <td>
              <router-link to="/BoardDetail">{{item.title}}</router-link>
            </td>
            <td>{{item.writer}}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import {computed, reactive} from "vue";
import axios from "axios";
import lib from "@/scripts/lib";
import router from "@/scripts/router";
export default {
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
    const submit = () => {
      const args = JSON.parse(JSON.stringify(state.form));
      args.items = JSON.stringify(state.items);
      axios.post("/api/orders", args).then(() => {
        alert('주문 완료하였습니다.');
        router.push({path: "/orders"})
      })
    }
    const computedPrice = computed(() => {
      let result = 0;
      for (let i of state.items) {
        result += i.price - i.price * i.discountPer / 100;
      }
      return result;
    })
    load();
    return {state, lib, computedPrice, submit}
  }
}
</script>

<style scoped>
</style>
