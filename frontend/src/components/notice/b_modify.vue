<template>
    <div>
        <span class="text-2em">MODIFY</span>
        <span class="text-green" style = "font-size:1.8em"> | </span>
        <span class="text-green"><b>공지사항 글 수정</b></span>
        <div class="margin-20">
        </div>
        <div>
            <label for="">제목</label>
            <input type="text" class="form form-control" v-model="article.title">
            <br>
            <label for="">본문</label>
            <textarea class="form form-control" name="" id="" cols="30" rows="10" v-model="article.contents"></textarea>
            <br>
            <div class="text-right"><button class="btn btn-success margin-1" @click="goNoticeList">목록으로</button><button
                    class="btn btn-success margin-1" @click="doNoticeUpdate">수정하기</button></div>
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
                article: {}
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
                    })
                    .catch()
                    .finally()
            },
            doNoticeUpdate() {
                let url = "http://localhost:8195/Board";//" + this.no;
                axios.put(url, this.article)
                    .then((res) => {
                        if (res) {
                            router.push({path:"/notice/"+this.no});
                        }
                    })
            },
            goNoticeList() {
                router.push({
                    path: "/notice"
                });
            }
        },
    }
</script>

<style>

</style>