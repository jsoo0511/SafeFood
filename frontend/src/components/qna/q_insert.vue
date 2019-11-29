<template>
  <div>
    <span class="text-2em">WRITE</span>
    <span class="text-green" style="font-size:1.8em"> | </span>
    <span class="text-green"><b>질문 작성하기</b></span>
    <div class="margin-20">
    </div>

    <div>
      <div class="row">
        <div class="col-sm-12 col-md-12">
            <div class="form-group">
              <label for="title">아이디</label>
              <input type="text" class="form-control" name="id" id="id" v-model="qna.id" />
            </div>
            <div class="form-group">
              <label for="title">제목</label>
              <input type="text" class="form-control" name="title" id="title" v-model="qna.title" />
            </div>
            <div class="form-group">
              <label for="contents">내용</label>
              <textarea name="contents" class="form-control" id="contents" cols="50" rows="7"
                v-model="qna.contents"></textarea>
            </div>
            <div class="form-group text-right">
              <button class="btn btn-default text-green" @click="addqna"><b>글 작성</b></button>
              <button class="btn btn-default text-green" @click="goBack"><b>취소</b></button>
            </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
  import router from "../../assets/router";
  import axios from "../../assets/http-commons.js";
  export default {
    data() {
      return {
        qna: {}
      };
    },
    methods: {
      addqna() {
        console.log("qna", this.qna);
        axios
          .post("http://localhost:8195/QnABoard", this.qna)
          .then(res => {
            console.log(res);
            console.log("글작성완료");
            router.push({
              path: "/qna"
            })
          })
          .catch(error => {
            console.log(error);
            alert("실패");
          });
      },
      goBack(){
        router.push({path:"/qna"});
      }
    }
  };
</script>
<style>
  body {
    font-family: "Roboto Slab", "Noto Sans KR";
  }
</style>