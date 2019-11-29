<template>
    <div>
        <span class="text-2em">SEARCH</span>
        <span class="text-green" style = "font-size:1.8em"> | </span>
        <span class="text-green"><b>검색 조건 설정</b></span>
        <div class="margin-20">
        </div>
        <div class="row">
            <div class="col-md-6">
                <label for="">검색조건</label>
                <br>

                <select v-model="key" id="manager_id" class="form form-control">
                    <option selected="selected" disabled value=""><span style="font-color:red">검색 조건을 선택하세요 (기본 : 이름)</span></option>
                    <option value="name">음식 이름</option>
                    <option value="material">음식 재료</option>
                    <option value="maker">제조사</option>
                </select><br>

            </div>
            <div class="col-md-6">
                <label for="">검색단어</label>
                <br>
                <input type="text" class="form form-control" v-model="word" @enter="showIf='true';searchFoodList()" required="required">
            </div>
            <div class="col-md-6">
                <button class="btn btn-success" style="width:100%" @click="showIf='true';searchFoodList()">검색</button>
            </div>
            <div class="col-md-6">
                <button class="btn btn-success" style="width:100%" @click="key='name';word='';searchFoodList()">취소</button>
            </div>
        </div>

        <div style="height:50px"></div>
        <span class="text-2em">RESULT</span>
        <span class="text-green" style = "font-size:1.8em"> | </span>
        <span class="text-green"><b>검색 결과</b></span>
        <div class="margin-20">
        </div>
        <div>
            <!-- <button class="btn btn-success btn-sm margin-1" v-if="showIf">검색단어 : {{searchRecent}}</button> -->
            <button class="btn btn-success btn-sm margin-1" v-for="item in wordHistory"
                :key="item.regdate" @click="word=item.word;searchFoodList()">{{item.word | emptyWord}}</button>
        </div>

        <div class="row">
            <div class="food-list-box margin-t-20 col-md-6 col-sm-6" v-for="item in foods" v-bind:key="item.code">
                <div class="food-inner" @click="dView=true">
                    <div class="food-img">
                        <div class="float-left col-md-4" style="padding-top:15px;padding-bottom:15px">
                            <img class="" :src="getURL(item.img)" @click="goDetail(item.code)" width="100%">
                        </div>
                        <div class="float-left col-md-8">
                            <div class="food-title">
                                <div class="float-right">
                                    <transition name="star" mode="out-in">
                                        <div v-if="zzim[item.code]" key="on">
                                            {{item.frequency}} <i class="fa fa-star star text-yellow" @click="deleteFavorite(item.code)"></i>
                                        </div>
                                        <div v-if="!zzim[item.code]" key="off">
                                            {{item.frequency}} <i class="fa fa-star star text-white" @click="insertFavorite(item.code)"></i>
                                        </div>                                    
                                    </transition>
                                </div>
                                <h4 class="text-green">{{item.name}}</h4>
                                <span class="text-grey">{{item.maker}}</span>
                                <div style="height:10px"></div>
                                <div class="input-group full-width">
                                    <input type="number" class="form-control" v-model="foodAmount[item.code]"
                                        @enter="insertEat">
                                    <div class="input-group-append" @click="insertEat(item.code, item.name, 1)">
                                        <span class="input-group-text" id="basic-addon2">Eat</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="wrap">
                        <span>{{item.material}}</span>
                    </div>
                </div>
            </div>
        </div>
    </div>


</template>

<script>
    import axios from '../assets/http-commons.js';
    import router from "../assets/router"

    export default {
        router,
        data() {
            return {
                dView: false,
                showIf: false,
                key: "name",
                word: "",
                sort: "",
                foods: [{}],
                wordHistory: [],
                foodAmount: [],
                zzim: [],
            }
        },
        methods: {
            searchFoodList() {
                this.$session.set("search_Recent", this.word);
                let url = "http://localhost:8195/food?word=" + this.word + "&stype=" + this.key;
                axios.get(url)
                    .then((res) => {
                        this.foods = res.data.data;
                        this.foods.forEach((item) => {
                            this.checkZzim(item.code);
                        })
                    })

                //최근 검색어 관련
                url = "http://localhost:8195/SearchHistory/";
                let data = {
                    id: this.$session.get("login_user"),
                    word: this.word
                };

                axios.post(url, data)
                    .then(() => {
                        url = "http://localhost:8195/SearchHistory/";
                        axios.get(url, {
                                params: {
                                    id: this.$session.get("login_user")
                                }
                            })
                            .then(res => {
                                console.log("여까지오나", res.data);
                                this.wordHistory = res.data.data;
                            })
                    })
            },
            goDetail(code) {
                this.$router.push({
                    path: "/food/" + code
                });
            },
            getURL(imgURL) {
                return require("../assets/" + imgURL);
            },
            insertFavorite(code) {
                let data = {
                    id: this.$session.get("login_user"),
                    code: code
                }

                console.log("입력", data);
                let url = "http://localhost:8195/Zzim";
                axios.post(url, data)
                    .then(res => {
                        console.log(res.data);
                        // this.searchFoodList();
                        this.$set(this.zzim, code, !this.zzim[code])
                        // this.zzim[code] = "true";
                    })
                    .catch(e => {
                        console.log(e);
                    })
            },
            deleteFavorite(code) {
                let url = "http://localhost:8195/Zzim";
                let data = {
                    id : this.$session.get("login_user"),
                    code : code
                };
                axios.delete(url, {params:{id:data.id, code : data.code}})
                    .then(() => {
                        this.$set(this.zzim, code, !this.zzim[code])
                    })
            },
            insertEat(code, foodname, amount) {
                console.log(amount);
                let data = {
                    idx: 0,
                    id: this.$session.get('login_user'),
                    code: code,
                    foodname: foodname,
                    amount: this.foodAmount[code]
                }

                let url = "http://localhost:8195/Member/EatList";
                axios.post(url, data)
                    .then(res => {
                        console.log(res.data);

                    })
                    .catch(e => {
                        console.log(e);
                    })
            },
            checkZzim(code) {
                let url = "http://localhost:8195/Zzim?id=" + this.$session.get('login_user') + "&code=" + code;
                axios.get(url)
                    .then(res => {
                        if (res.data.data == 0) {
                            this.zzim[code] = false;
                        } else {
                            this.zzim[code] = true;
                        }
                    })
            },
        },
        created() {
            this.searchFoodList();
        },
        mounted() {
            this.searchFoodList();
        },
        computed: {
            searchRecent(){
                return this.$session.get("search_Recent");
            },
        },
        filters:{
            emptyWord(str){
                if(str == ""){
                    return "전체"
                }
                return str
            }
        }
    }
</script>

<style>
    .food-list-box {
        padding: 0px;
        margin: 5px 0px;
    }

    .food-inner {
        background-color: #ffffff;
        height: 100%;
        width: 95%;
        left: 0px;
        right: 0px;
        margin: auto;
        padding: 10px;
        box-shadow: -12px 0px 20px -18px #CCCCCC, 12px 0px 20px -18px #CCCCCC;
        border-radius: 2px 2px 2px 2px;
    }

    .food-inner img {
        border: 1px solid #eeeeee;
        border-radius: 5px;
        width: 100%;
    }

    .food-inner img:hover {
        border: 1px solid #007209;
        border-radius: 5px;
    }

    .food-img {
        display: inline-block;
    }

    .food-title {
        /* position: absolute; */
        padding-top: 15px;
        padding-bottom: 15px;
        /* background-color: blue; */
    }

    .wrap {
        line-height: 1.5em;
        height: 6.0em;
        overflow: hidden;
        text-overflow: ellipsis;
        padding-left:10px;
        padding-right:5px;
    }

    /* FOODLIST FADE */
    .food-list-enter-active,
    .food-list-leave-active {
        transition: opacity .3s;
    }

    .food-list-enter,
    .food-list-leave-to {
        opacity: 0;
    }

    /* .star-enter-active,
    .star-leave-active {
        transition: opacity .3s;
    }

    .star-enter,
    .star-leave-to {
        opacity: 0;
    } */

    .float-bottom {
        /* disp */
        bottom: 0px;
    }
</style>