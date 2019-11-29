<template>
  <div id="detail">
    <div id="result" class="container">
      <h3>게시물 수정</h3>
      <article id="mainContent">
        <input type="hidden" name="no" id="no" value="board.no" />
        <input type="hidden" name="regdate" id="regdate" value="${board.regdate}" />
        <table class="table">
          <tr>
            <td>
              <label for="title" class="high_light">제목</label>
            </td>
            <td>
              <input class="form-control" type="text" name="title" id="title" v-model="board.title" />
            </td>
          </tr>
          <tr>
            <td>
              <label for="title" class="high_light">아이디</label>
            </td>
            <td>{{board.id}}</td>
          </tr>
          <tr>
            <td colspan="2" align="center">
              <textarea
                class="form-control"
                name="contents"
                id="contents"
                cols="50"
                rows="5"
                v-model="board.contents"
              ></textarea>
            </td>
          </tr>
          <tr>
            <td colspan="2" align="center">
              <input class="btn btn-success" type="submit" value="수정" @click="update" />
              <a href="/">
                <input class="btn btn-default" type="button" value="취소" id="cancel" />
              </a>
            </td>
          </tr>
        </table>
      </article>
    </div>
  </div>
</template>

<script>
import router from "../../assets/router";
import axios from "../../assets/http-commons.js";

export default {
  name: "updateBoard",
  props: ["no"],
  data() {
    return {
      board: {}
    };
  },
  mounted() {
    axios
      .get("http://localhost:8195/QnABoard/" + this.no)
      .then(res => {
        console.log(res.data.data);
        this.board = res.data.data;
      })
      .catch(e => {
        console.log(e);
      });
  },
  methods: {
    update() {
      axios.put("http://localhost:8195/QnABoard/", this.board).then(res => {
        console.log(res.data.status);
        if(res.data.status){
          router.push({
            path:"/qna"
          })
        }
      })
      .catch(error=>{
        alert("다시 시도해주세요.");
        console.log(error);
      })
    }
  }
};
</script>

<style>
</style>