<template>
    <div>
        <span class="text-2em">VIEW</span>
        <span class="text-green" style = "font-size:1.8em"> | </span>
        <span class="text-green"><b>공지사항 글 보기</b></span>
        <div class="margin-20">
        </div>
        <table class="table">
            <tr>
                <td width="100/6%" class="back-grey-bold text-center"><span class="text-green"><b>글 번호</b></span></td>
                <td width="100/6%">{{article.no}}</td>
                <td width="100/6%" class="back-grey-bold text-center"><span class="text-green"><b>작성자</b></span></td>
                <td width="100/6%">{{article.id}}</td>
                <td width="100/6%" class="back-grey-bold text-center"><span class="text-green"><b>작성날짜</b></span></td>
                <td width="100/6%">{{article.regdate}}</td>
            </tr>
            <tr>
                <td class="back-grey text-center back-grey-bold"><span class="text-green"><b>제목</b></span></td>
                <td colspan="5">{{article.title}}</td>
            </tr>
            <tr>
                <td class="back-grey" colspan="6" style="padding:25px">{{article.contents}}</td>
            </tr>
            <tfoot>
                <tr>
                    <td colspan="6">
                        <button class="btn btn-default text-green" @click="goNoticeList">LIST</button>
                        <button class="btn btn-default float-right text-green" @click="goNoticeModify" v-if="checkMe(article.id)">MODIFY</button>
                        <button class="btn btn-default float-right text-green" @click="doDelete" v-if="checkMe(article.id)">DELETE</button>
                    </td>
                </tr>

            </tfoot>

        </table>
        <div>
        </div>
    </div>
</template>

<script>
    import axios from '../../assets/http-commons.js';
    import router from "../../assets/router";
    export default {
        props: ["no"],
        data() {
            return {
                article: {},
                similar: []
            }
        },
        mounted() {
            this.getNoticeDetail();
        },
        methods: {
            getNoticeDetail() {
                let url = "http://localhost:8195/Board/" + this.no;
                axios.get(url)
                    .then((res) => {
                        this.article = res.data.data;
                        console.log(this.article);
                    })
                    .catch()
                    .finally()
            },
            goNoticeModify() {
                router.push({
                    path: "/notice/modify/" + this.no
                });
            },
            doDelete() {
                let url = "http://localhost:8195/Board/" + this.no;
                axios.delete(url)
                    .then((res) => {
                        console.log(res.data);
                        router.push({
                            path: "/notice"
                        });
                    })
                    .catch()
                    .finally()
            },
            goNoticeList() {
                router.push({
                    path: "/notice"
                });
            },
            checkMe(id) {
                return id == this.$session.get("login_user");
            }
        },
    }
</script>

<style>

</style>