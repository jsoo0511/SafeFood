<template>
    <div>
        <div class="detail-title">
            <span class="text-2em">FOOD INFO</span>
            <span class="text-green" style="font-size:1.8em"> | </span>
            <span class="text-green"><b>음식 상세영양정보</b></span>
            <div class="margin-20">
            </div>
            <div class="row">
                <div class="col-md-3">
                    <img :src="getURL(food.img)" width="100%" class="food-similar margin-b-20">
                </div>
                <div class="col-md-9">
                    <div class="align-bottom">
                        <div>
                            <h3>유사 영양 음식 정보</h3>
                        </div>
                        <img v-for="item in food_similar" :key="item.code" @click="goDetail(item.code)"
                            class="food-similar" :src="getURL(item.img)" width="18%">
                    </div>
                </div>
            </div>
            <table class="table">
                <tr>
                    <td width='25%'><span class="text-green"><b>음식명</b></span></td>
                    <td>{{food.name}}</td>
                </tr>
                <tr>
                    <td><span class="text-green"><b>성분</b></span></td>
                    <td>{{food.material}}</td>
                </tr>
                <tr>
                    <td><span class="text-green"><b>알레르기 성분</b></span></td>
                    <td>
                        <span v-for="item in food_allergy" :key="item">
                            <span class="text-red" v-if="checkAllergy(item)">
                                {{item}}
                            </span>
                            <span v-if="!checkAllergy(item)">
                                {{item}}
                            </span>
                        </span>
                    </td>
                </tr>
            </table>
        </div>
        <div style="height:40px"></div>
        <div class="detail-title">
            <span class="text-2em">NUTRITION</span>
            <span class="text-green" style="font-size:1.8em"> | </span>
            <span class="text-green"><b>제품 영양성분정보</b></span>
            <div class="margin-20">
            </div>
            <div class="row">
                <div class="col-sm-5 col-md-5">
                    <table class="table table-response table-nutrition">
                        <tr>
                            <td align="center" class="back-grey-bold" width="50%"><span class="text-green"><b>칼로리</b></span></td>
                            <td v-if="nutritionFilter(food.calory, 0)" align="center" class="text-red">{{food.calory}}</td>
                            <td v-if="!nutritionFilter(food.calory, 0)" align="center">{{food.calory}}</td>
                        </tr>
                        <tr>
                            <td align="center" class="back-grey-bold"><span class="text-green"><b>탄수화물</b></span></td>
                            <td v-if="nutritionFilter(food.carbo, 1)" align="center" class="text-red">{{food.carbo}}</td>
                            <td v-if="!nutritionFilter(food.carbo, 1)" align="center">{{food.carbo}}</td>
                        </tr>
                        <tr>
                            <td align="center" class="back-grey-bold"><span class="text-green"><b>단백질</b></span></td>
                            <td v-if="nutritionFilter(food.protein, 2)" align="center" class="text-red">{{food.protein}}</td>
                            <td v-if="!nutritionFilter(food.protein, 2)" align="center">{{food.protein}}</td>
                        </tr>
                        <tr>
                            <td align="center" class="back-grey-bold"><span class="text-green"><b>지방</b></span></td>
                            <td v-if="nutritionFilter(food.fat, 3)" align="center" class="text-red">{{food.fat}}</td>
                            <td v-if="!nutritionFilter(food.fat, 3)" align="center">{{food.fat}}</td>
                        </tr>
                        <tr>
                            <td align="center" class="back-grey-bold"><span class="text-green"><b>설탕</b></span></td>
                            <td v-if="nutritionFilter(food.sugar, 4)" align="center" class="text-red">{{food.sugar}}</td>
                            <td v-if="!nutritionFilter(food.sugar, 4)" align="center">{{food.sugar}}</td>
                        </tr>
                        <tr>
                            <td align="center" class="back-grey-bold"><span class="text-green"><b>나트륨</b></span></td>
                            <td v-if="nutritionFilter(food.natrium, 5)" align="center" class="text-red">{{food.natrium}}</td>
                            <td v-if="!nutritionFilter(food.natrium, 5)" align="center">{{food.natrium}}</td>
                        </tr>
                        <tr>
                            <td align="center" class="back-grey-bold"><span class="text-green"><b>콜레스테롤</b></span></td>
                            <td v-if="nutritionFilter(food.chole, 6)" align="center" class="text-red">{{food.chole}}</td>
                            <td v-if="!nutritionFilter(food.chole, 6)" align="center">{{food.chole}}</td>
                        </tr>
                        <tr>
                            <td align="center" class="back-grey-bold"><span class="text-green" @click="fillData(3)"><b>포화지방산</b></span></td>
                            <td v-if="nutritionFilter(food.fattyacid, 7)" align="center" class="text-red">{{food.fattyacid}}</td>
                            <td v-if="!nutritionFilter(food.fattyacid, 7)" align="center">{{food.fattyacid}}</td>
                        </tr>
                        <tr>
                            <td align="center" class="back-grey-bold"><span class="text-green" @click="fillData(2)"><b>트랜스지방</b></span></td>
                            <td v-if="nutritionFilter(food.transfat, 8)" align="center" class="text-red">{{food.transfat}}</td>
                            <td v-if="!nutritionFilter(food.transfat, 8)" align="center">{{food.transfat}}</td>
                        </tr>
                        <tfoot>
                            <tr>
                                <td colspan="2"></td>
                            </tr>
                        </tfoot>
                    </table>
                </div>
                <div class="col-sm-7 col-md-7">
                    <pie-chart :chart-data="chartData"></pie-chart>
                </div>
                <div class="col-sm-12 col-md-12">
                    <span class="1"><b>＃ 알레르기 정보 및 하루 권장 영양소 50% 초과시 표시</b></span>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import axios from '../../assets/http-commons.js';
    import PieChart from "../../assets/piechart.js";

    export default {
        components: {
            PieChart
        },
        props: ["code"],
        data() {
            return {
                food: {},
                food_allergy: [],
                food_similar: [],
                chartOptions: {
                    hoverBorderWidth: 20
                },
                chartData: {},
                //칼, 탄, 단, 지, 설, 나, 콜, 포, 트
                dailyNutritionLimit: [2600, 470, 55, 72, 9999, 1500, 9999, 9999, 9999]
            }
        },
        mounted() {
            this.getDetail();
        },
        created() {
            this.fillData([1, 1]);
        },
        methods: {
            fillData(num) {
                this.chartData = {
                    labels: ["칼로리", "탄수화물", "단백질", "지방", "설탕", "나트륨", "콜레스테롤", "포화지방산", "트랜스지방"],
                    datasets: [{
                        label: "FOOD DETAIL",
                        backgroundColor: ["#fc3d03", "#fc9003", "#f4fc03", "#009e1a", "#009e93", "#00229e",
                            "#69009e", "#9e006c"
                        ],
                        data: num
                    }]
                }
            },
            getDetail() {
                let url = "http://localhost:8195/food/" + this.code;
                axios.get(url)
                    .then((res) => {
                        this.food = res.data.data;
                        this.food_similar = res.data.dataIngredient;
                        this.fillData([this.food.calory, this.food.carbo, this.food.protein, this.food.fat, this
                            .food.sugar, this.food.natrium, this.food.chole, this.food.fattyacit, this.food
                            .transfat
                        ]);
                        this.food_allergy = this.food.allergy.split(" ");
                        console.log("allergy", this.food.allergy.split(" "));
                    })
                    .catch()
                    .finally()
            },
            getURL(imgURL) {
                console.log("url", imgURL);
                return require("../../assets/" + imgURL);
            },
            goDetail(code) {
                this.$router.push({
                    path: "../food/" + code
                });
            },
            checkAllergy(item) {
                if (this.$session.get("allergy").includes(item)) {
                    return true;
                } else {
                    return false;
                }
            },
            nutritionFilter(num, idx){
                if(num>this.dailyNutritionLimit[idx]/2){
                    return true;
                }else{
                    return false;
                }
            }
        },
        computed: {
            test() {
                return this.$session.get("allergy");
            }
        },
    }
</script>

<style>
    .food-similar {
        border: 1px solid #eeeeee;
        margin: 3px;
        border-radius: 5px;
    }

    .food-similar:hover {
        border: 1px solid #00af35;
    }

    hr {
        background-color: black;
    }

    .table-nutrition {
        height: 100%;
    }

    .table-nutrition>tr>td {
        vertical-align: middle;
    }

    .align-bottom {
        position: absolute;
        bottom: 17px;
    }
</style>