<template>
    <div class="">
        <span class="text-2em">LIST</span>
        <span class="text-green" style = "font-size:1.8em"> | </span>
        <span class="text-green"><b>공지사항 목록</b></span>
        <div class="margin-20">
        </div>
        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                    <th width="60">번호</th>
                    <th>제목</th>
                    <th width="70">작성자</th>
                    <th width="130">작성시간</th>
                </thead>
                <tbody>

                <tr v-for="item in notice" :key="item.no" @click="goNoticeDetail(item.no)">
                    <td>{{item.no}}</td>
                    <td>{{item.title}}</td>
                    <td>{{item.id}}</td>
                    <td>{{item.regdate}}</td>
                </tr>
                </tbody>
                <tfoot>
                    <tr>
                        <td width="15%"></td>
                        <td colspan="2">
                            <nav aria-label="Page navigation example">
                                <ul class="pagination justify-content-center">
                                    <li class="page-item">
                                        <a class="page-link" href="#/notice" aria-label="Previous" @click="getNoticeList(page-1)">
                                            <span aria-hidden="true" class="text-green">&laquo;</span>
                                        </a>
                                    </li>
                                    <li class="page-item" v-for="n in allPage" :key = "n"><a class="page-link text-green" href="#/notice" @click="getNoticeList(n)">{{n}}</a></li>
                                    <li class="page-item">
                                        <a class="page-link" href="#/notice" aria-label="Next" @click="getNoticeList(page+1)">
                                            <span aria-hidden="true" class="text-green">&raquo;</span>
                                        </a>
                                    </li>
                                </ul>
                            </nav>
                        </td>
                        <td width= "15%"> <button class="btn btn-default text-green float-right" @click="goNoticeWrite"><b>글쓰기</b></button>
                        </td>
                    </tr>
                </tfoot>
            </table>

        </div>
        <div>
        </div>
    </div>
</template>

<script>
    import axios from "../assets/http-commons.js";
    import router from "../assets/router"
    export default {
        data() {
            return {
                notice: [],
                page: "",
                allPage: "",
            }
        },
        mounted() {
            this.getNoticeList(1);
        },
        methods: {
            goNoticeWrite() {
                router.push({
                    path: "/notice/write"
                });
            },
            getNoticeList(page) {
                let url = "http://localhost:8195/Board?curPage="+page;
                axios.get(url)
                    .then((res) => {
                        // console.log(res.data);
                        this.notice = res.data.data;
                        this.allPage = res.data.dataCount;
                        this.page = page;
                    })
                    .catch()
                    .finally()
            },
            goNoticeDetail(no) {
                router.push({
                    path: "/notice/" + no
                });
            }
        },
    }
</script>

<style>
    .none-back {
        background-color: none !important;
    }
</style>