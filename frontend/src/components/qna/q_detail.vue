<template>
  <div id="detail">
        <span class="text-2em">VIEW</span>
        <span class="text-green" style = "font-size:1.8em"> | </span>
        <span class="text-green"><b>질문 상세보기</b></span>
        <div class="margin-20">
        </div>
    <div id="result">
      <article id="mainContent">
        <div class="main" id="viewBoard">
          <input type="hidden" name="no" id="no" value="board.no" />
          <input type="hidden" name="query" id="query" />
          <table class="table">
            <tbody>
              <tr>
                <td width="20%" class="text-green text-center back-grey-bold"><b>글쓴이</b></td>
                <td width="30%">{{board.id}}</td>
                <td width="20%" class="text-green text-center back-grey-bold"><b>게시일</b></td>
                <td width="30%">{{board.regdate}}</td>
              </tr>
              <tr>
                <td class="text-green back-grey-bold text-center">
                  <b>제목</b>
                </td>
                <td colspan="3">{{board.title}}</td>
              </tr>
              <tr>
                <td class="text-left" colspan="4" valign="top" height="200" style="padding:25px">{{board.contents}}</td>
              </tr>
              <tr>
                <td colspan="4" class="back-grey-bold">REPLY
                  <div class="input-group">
                    <input type="text" class="form form-control" v-model="replyText" @enter="insertReply">
                    <div class="input-group-append">
                      <span class="input-group-text" id="basic-addon2" @click="insertReply">WRITE</span>
                    </div>
                  </div>
                </td>
              </tr>
              <tr>
                <td colspan="4">
                  <div class="row">
                    <div class="col-sm-2 col-md-2 text-green"><b>ID</b></div>
                    <div class="col-sm-7 col-md-7 text-green"><b>내용</b></div>
                    <div class="col-sm-3 col-md-3 text-green"><b>작성시각</b></div>
                  </div>
                </td>
              </tr>
              <tr v-for="item in replyList" :key="item.id">
                <td colspan="4">
                  <div class="row">
                    <div class="col-sm-2 col-md-2">{{item.id}}</div>
                    <div class="col-sm-7 col-md-7">{{item.reply}}</div>
                    <div class="col-sm-2 col-md-2">{{item.regdate}}</div>
                    <div class="col-sm-1 col-md-1"><button class = "btn btn-sm btn-success" v-if="checkMe(item.id)" @click="deleteReply(item.idx)">삭제</button></div>
                  </div>
                </td>

              </tr>
            </tbody>

            <tfoot>
              <tr>
                <td colspan="4" align="right">
                  <button @click="qnalist" class="btn btn-default margin-1 text-green"><b>목록</b></button>
                  <button @click="updateBoard(board.no)" class="btn btn-default margin-1 text-green" v-if="checkMe(board.id)"><b>수정</b></button>
                  <button @click="deleteBoard(board.no)" class="btn btn-default margin-1 text-green" v-if="checkMe(board.id)"><b>삭제</b></button>
                </td>
              </tr>
            </tfoot>
          </table>
        </div>
      </article>
    </div>
  </div>
</template>

<script>
  import router from "../../assets/router";
  import axios from "../../assets/http-commons.js";

  export default {
    name: "detail",
    props: ["no"],
    data() {
      return {
        board: {},
        replyList: [],
        replyText: ""
      };
    },
    mounted() {
      this.getReply();
    },
    methods: {
      getReply(){
      axios
        .get("http://localhost:8195/QnABoard/" + this.no)
        .then(res => {
          console.log(res.data.dataReply);
          this.board = res.data.data;
          this.replyList = res.data.dataReply;
        })
        .catch(e => {
          console.log(e);
        })
      },
      qnalist() {
        router.push({
          path: "/qna"
        })
      },
      updateBoard(no) {
        console.log(no);
        router.push({
          path: "/updateBoard/" + no
        })
      },
      deleteBoard(no) {
        axios.delete("http://localhost:8195/QnABoard/" + no)
          .then(res => {
            console.log(res.data.status);
            router.push({
              path: "/qna"
            })
          }).catch(e => {
            console.log(e);
          })
      },
      deleteReply(no){
        axios.delete("http://localhost:8195/QnAReply/" + no)
          .then(res => {
            console.log(res.data.status);
            this.getReply();
          }).catch(e => {
            console.log(e);
          })
      },
      insertReply() {
        let url = "http://localhost:8195/QnAReply";
        let data = {
          boardno: this.board.no,
          id: this.$session.get("login_user"),
          idx: 0,
          regdate: null,
          reply: this.replyText
        }
        console.log(data);
        axios.post(url, data)
          .then(() => {
            console.log("성공");
            this.getReply();
          })
      },
      checkMe(id){
        return id==this.$session.get("login_user");
      }
    },

  };
</script>

<style>
</style>