<template>
    <div>
        <span class="text-2em">MY NUTRITION</span>
        <span class="text-green" style = "font-size:1.8em"> | </span>
        <span class="text-green"><b>영양소 변화량</b></span>
        <div class="margin-20">
        </div>
        <div>
            <div>
                <table class="table table-response table-nutrition">
                    <tr>
                        <td width="25%" class="back-grey-bold"><span class="text-green"><b>칼로리</b></span></td>
                        <td width="25%" class="back-white text-center">{{food.calory | toFixed}}</td>
                        <td width="25%" class="text-center"><span class="text-green"><b>+{{zzimNutri.calory | toFixed}}
                                    ({{zzimNutri.calory/food.calory * 100 | toFixed}}%)</b></span></td>
                        <td width="25%" class="back-white text-center">{{food.calory + zzimNutri.calory | toFixed}}</td>
                    </tr>
                    <tr>
                        <td class="back-grey-bold"><span class="text-green"><b>탄수화물</b></span></td>
                        <td class="back-white text-center">{{food.carbo | toFixed}}</td>
                        <td class="text-center"><span class="text-green"><b>+{{zzimNutri.carbo | toFixed}}
                                    ({{zzimNutri.carbo/food.carbo * 100 | toFixed}}%)</b></span></td>
                        <td class="back-white text-center">{{food.carbo + zzimNutri.carbo | toFixed}}</td>
                    </tr>
                    <tr>
                        <td class="back-grey-bold"><span class="text-green"><b>단백질</b></span></td>
                        <td class="back-white text-center">{{food.protein | toFixed}}</td>
                        <td class="text-center"><span class="text-green"><b>+{{zzimNutri.protein | toFixed}}
                                    ({{zzimNutri.protein/food.protein * 100 | toFixed}}%)</b></span></td>
                        <td class="back-white text-center">{{food.protein + zzimNutri.protein | toFixed}}</td>
                    </tr>
                    <tr>
                        <td class="back-grey-bold"><span class="text-green"><b>지방</b></span></td>
                        <td class="back-white text-center">{{food.fat | toFixed}}</td>
                        <td class="text-center"><span class="text-green"><b>+{{zzimNutri.fat | toFixed}}
                                    ({{zzimNutri.fat/food.fat * 100 | toFixed}}%)</b></span></td>
                        <td class="back-white text-center">{{food.fat + zzimNutri.fat | toFixed}}</td>
                    </tr>
                    <tr>
                        <td class="back-grey-bold"><span class="text-green"><b>설탕</b></span></td>
                        <td class="back-white text-center">{{food.sugar | toFixed}}</td>
                        <td class="text-center"><span class="text-green"><b>+{{zzimNutri.sugar | toFixed}}
                                    ({{zzimNutri.sugar/food.sugar * 100 | toFixed}}%)</b></span></td>
                        <td class="back-white text-center">{{food.sugar + zzimNutri.sugar | toFixed}}</td>
                    </tr>
                    <tr>
                        <td class="back-grey-bold"><span class="text-green"><b>나트륨</b></span></td>
                        <td class="back-white text-center">{{food.natrium | toFixed}}</td>
                        <td class="text-center"><span class="text-green"><b>+{{zzimNutri.natrium | toFixed}}
                                    ({{zzimNutri.natrium/food.natrium * 100 | toFixed}}%)</b></span></td>
                        <td class="back-white text-center">{{food.natrium + zzimNutri.natrium | toFixed}}</td>
                    </tr>
                    <tr>
                        <td class="back-grey-bold"><span class="text-green"><b>콜레스테롤</b></span></td>
                        <td class="back-white text-center">{{food.chole | toFixed}}</td>
                        <td class="text-center"><span class="text-green"><b>+{{zzimNutri.chole | toFixed}}
                                    ({{zzimNutri.chole/food.chole * 100 | toFixed}}%)</b></span></td>
                        <td class="back-white text-center">{{food.chole + zzimNutri.chole | toFixed}}</td>
                    </tr>
                    <tr>
                        <td class="back-grey-bold"><span class="text-green"><b>포화지방산</b></span></td>
                        <td class="back-white text-center">{{food.fattyacid | toFixed}}</td>
                        <td class="text-center"><span class="text-green"><b>+{{zzimNutri.fattyacid | toFixed}}
                                    ({{zzimNutri.fattyacid/food.fattyacid * 100 | toFixed}}%)</b></span></td>
                        <td class="back-white text-center">{{food.fattyacid + zzimNutri.fattyacid | toFixed}}</td>
                    </tr>
                    <tr>
                        <td class="back-grey-bold"><span class="text-green"><b>트랜스지방</b></span></td>
                        <td class="back-white text-center">{{food.transfat | toFixed}}</td>
                        <td class="text-center"><span class="text-green"><b>+{{zzimNutri.transfat | toFixed}}
                                    ({{zzimNutri.transfat/food.transfat * 100 | toFixed}}%)</b></span></td>
                        <td class="back-white text-center">{{food.transfat + zzimNutri.transfat | toFixed}}</td>
                    </tr>
                    <tfoot>
                        <tr>
                            <td colspan="4"></td>
                        </tr>
                    </tfoot>
                </table>
            </div>
        </div>
        <div style="height:50px">
        </div>
        <span class="text-2em">FAVORITE FOOD</span>
        <span class="text-green" style = "font-size:1.8em"> | </span>
        <span class="text-green"><b>찜한 음식</b></span>
        <div class="margin-20">
        </div>
        <div class="row">
            <div v-for="item in list" :key="item.no" class="col-md-3 col-sm-3 zzim-outer">
                <div class="zzim-inner">
                    <i class="fa fa-star star float-right"
                            @click="deleteFavorite(item.food.code)"></i>
                    <img class="favorite-img" :src="getURL(item.food.img)" alt="" width="100%"
                        @click="goDetail(item.food.code)">
                    <div style="height:20px"></div>
                    <h5 class="text-green">{{item.food.name}}</h5>
                    <h6>{{item.food.maker}}</h6>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import axios from '../assets/http-commons.js';
    // import router from "../assets/router"

    export default {
        data() {
            return {
                // foods:[],
                food: {},
                zzimNutri: {},
                list: []
            }
        },
        methods: {
            goDetail(code) {
                this.$router.push({
                    path: "/food/" + code
                });
            },
            getFavorite() {
                let url = "http://localhost:8195/Zzim/" + this.$session.get("login_user");
                axios.get(url)
                    .then(res => {
                        this.list = res.data.data;
                    })
                    .catch()
            },
            deleteFavorite(code) {
                let url = "http://localhost:8195/Zzim";
                let data = {
                    id : this.$session.get("login_user"),
                    code : code
                };
                axios.delete(url, {params:{id:data.id, code : data.code}})
                    .then(() => {
                        this.getNowNutrition();
                        this.getZzimNutrition();
                        this.getFavorite();
                    })
            },
            getURL(imgURL) {
                return require("../assets/" + imgURL);
            },
            getNowNutrition() {
                let config = {
                    headers: {
                        "Authorization": this.$session.get("auth_token"),
                        "Content-Type": "applicaiton/json",
                        'Access-Control-Allow-Origin': '*'
                    }
                }

                let url = "http://localhost:8195/Member/EatList?loginuser=" + this.$session.get("login_user");
                axios.get(url, config)
                    .then((res) => {
                        this.food = res.data.dataIngredient;
                    })
                    .catch()
                    .finally()
            },
            getZzimNutrition() {
                let config = {
                    headers: {
                        "Authorization": this.$store.state.auth_token,
                        "Content-Type": "applicaiton/json",
                        'Access-Control-Allow-Origin': '*'
                    }
                }

                let url = "http://localhost:8195/Member/EatList/" + this.$session.get("login_user");
                axios.get(url, config)
                    .then((res) => {
                        this.zzimNutri = res.data.data.food;
                    })
                    .catch()
            }
        },
        mounted() {
            this.getFavorite();
            this.getNowNutrition();
            this.getZzimNutrition();
        },
        filters: {
            toFixed(num) {
                if (isNaN(num)) {
                    return '0.00';
                } else {
                    return num.toFixed(2);
                }
            }
        }
    }
</script>

<style>
    .zzim-outer {
        padding: 15px;
    }

    .zzim-inner {
        border-radius: 3px;
        background-color: white;
        display: inline-block;
        text-align: center;

        padding: 10px;
        box-shadow: 3px 3px 20px 20px #eeeeee;
    }

    .button-star {
        width: 35px;
    }

    .favorite-img:hover {
        border: 1px solid #007209;
        border-radius: 5px;
    }
</style>