<template>
    <div>
        <span class="text-2em">WRITE</span>
        <span class="text-green" style = "font-size:1.8em"> | </span>
        <span class="text-green"><b>공지사항 글쓰기</b></span>
        <div class="margin-20">
        </div>
        <div>
            <label for="">제목</label>
            <input type="text" class="form form-control" v-model="article.title">
            <br>
            <label for="">본문</label>
            <textarea class="form form-control" cols="30" rows="10" v-model="article.contents"></textarea>
            <br>
            <div style="text-align:right">
                <button class="btn btn-default text-green margin-1" @click="doWrite"><b>글쓰기</b></button>
                <button class="btn btn-default text-green margin-1" @click="goBack"><b>취소</b></button>
            </div>
        </div>
    </div>
</template>

<script>
    import axios from '../../assets/http-commons.js';
    import router from "../../assets/router";
    export default {
        data() {
            return {
                article:{
                    id:this.$session.get("login_user"),
                    title: "",
                    contents: "",
                }
            }
        },
        methods: {
            doWrite() {
                let url = "http://localhost:8195/Board";
                axios.post(
                        url,
                        this.article    
                    )
                    .then((res) => {
                        if(res){
                            router.push({path:"/notice"});
                        }
                    })
                    .catch()
                    .finally()
            },
            goBack() {
                router.push({path:"/notice"});
            }
        },
    }
</script>

<style>

</style>