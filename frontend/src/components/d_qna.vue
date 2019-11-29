<template>
  <div id="app">
    <span class="text-2em">LIST</span>
    <span class="text-green" style = "font-size:1.8em"> | </span>
    <span class="text-green"><b>질문 목록</b></span>
    <div class="margin-20">
    </div>
    <div class="container">
      <div class="row">
        <div class="table-responsive">
          <table class="table table-striped">
            <thead>
              <tr>
                <th width="70px">번호</th>
                <th>제목</th>
                <th width="120px">작성자</th>
                <th width="150px">작성시간</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="board in boards" :key="board.no" @click="detail(board.no)">
                <td>{{board.no}}</td>
                <td>{{board.title}}</td>
                <td>{{board.id}}</td>
                <td>{{board.regdate}}</td>
              </tr>
            </tbody>
            <tfoot>
              <tr>
                <td width="15%"></td>
                <td colspan="2">
                  <nav aria-label="Page navigation example">
                    <ul class="pagination justify-content-center">
                      <li class="page-item">
                        <a class="page-link" href="#/qna" aria-label="Previous" @click="getQnaList(page-1)">
                          <span aria-hidden="true" class="text-green">&laquo;</span>
                        </a>
                      </li>
                      <li class="page-item" v-for="n in allPage" :key="n"><a class="page-link text-green"
                          href="#/qna" @click="getQnaList(n)">{{n}}</a></li>
                      <li class="page-item">
                        <a class="page-link" href="#/qna" aria-label="Next" @click="getQnaList(page+1)">
                          <span aria-hidden="true" class="text-green">&raquo;</span>
                        </a>
                      </li>
                    </ul>
                  </nav>
                </td>
                <td width="15%"> <a class="btn btn-default float-right" @click="qnainsert"><span class="text-green"><b>글쓰기</b></span></a>

                </td>
              </tr>
            </tfoot>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import router from "../assets/router";
  import axios from "../assets/http-commons.js";

  export default {
    data() {
      return {
        boards: [],
        allPage: "",
        page: ""
      };
    },
    mounted() {
      this.getQnaList(1);
    },
    methods: {
      getQnaList(page) {
          let url = "http://localhost:8195/QnABoard?curPage="+page;
          axios.get(url)
              .then((res) => {
                  // console.log(res.data);
                  this.boards = res.data.data;
                  this.allPage = res.data.dataCount;
                  this.page = page;
              })
              .catch()
              .finally()
      },      
      detail(id) {
        console.log(id);
        router.push({
          path: "/detail/" + id
        });
      },
      qnainsert() {
        router.push({
          path: "/insert"
        });
      }
    }
  };
</script>

<style>
</style>