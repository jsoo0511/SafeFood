<template>
  <div>
    <span class="text-2em">CHART</span>
    <span class="text-green" style="font-size:1.8em"> | </span>
    <span class="text-green"><b>섭취한 영양 정보</b></span>
    <div class="margin-20">
    </div>
    <div>
      <ul class="nav nav-tabs test">
        <li class="nav-item" @click="setTab('year')">
          <transition>
            <a v-if="nowTab=='year'" class="nav-link active" data-toggle="tab" href="#/my/eat">YEAR</a>
            <a v-if="nowTab!='year'" class="nav-link text-black" data-toggle="tab" href="#/my/eat">YEAR</a>
          </transition>
        </li>
        <li class="nav-item" @click="setTab('month')">
          <transition>
            <a v-if="nowTab=='month'" class="nav-link active" data-toggle="tab" href="#/my/eat">MONTH</a>
            <a v-if="nowTab!='month'" class="nav-link text-black" data-toggle="tab" href="#/my/eat">MONTH</a>
          </transition>
        </li>
        <li class="nav-item" @click="setTab('day')">
          <transition>
            <a v-if="nowTab=='day'" class="nav-link active" data-toggle="tab" href="#/my/eat">DAY</a>
            <a v-if="nowTab!='day'" class="nav-link text-black" data-toggle="tab" href="#/my/eat">DAY</a>
          </transition>
        </li>
      </ul>

      <transition>
        <div v-if="nowTab=='year'">
          <div class="chart-border">
            <bar-chart :chart-data="chartYearData" height="150px"></bar-chart>
          </div>
        </div>
        <div v-if="nowTab=='month'">
          <div class="chart-border">
            <bar-chart :chart-data="chartMonthData" height="150px"></bar-chart>
          </div>
        </div>
        <div v-if="nowTab=='day'">
          <div class="chart-border">
            <bar-chart :chart-data="chartDayData" height="150px"></bar-chart>
          </div>
        </div>
      </transition>

      <div style="height:40px"></div>
      <table class="table table-response">
        <tbody>
          <tr>
            <td class="back-grey-bold" width="50%">
              <span class="text-green">
                <b>칼로리</b>
              </span>
            </td>
            <td>{{food.calory | toFixed}}</td>
          </tr>
          <tr>
            <td class="back-grey-bold">
              <span class="text-green">
                <b>탄수화물</b>
              </span>
            </td>
            <td>{{food.carbo | toFixed}}</td>
          </tr>
          <tr>
            <td class="back-grey-bold">
              <span class="text-green">
                <b>단백질</b>
              </span>
            </td>
            <td>{{food.protein | toFixed}}</td>
          </tr>
          <tr>
            <td class="back-grey-bold">
              <span class="text-green">
                <b>지방</b>
              </span>
            </td>
            <td>{{food.fat | toFixed}}</td>
          </tr>
          <tr>
            <td class="back-grey-bold">
              <span class="text-green">
                <b>설탕</b>
              </span>
            </td>
            <td>{{food.sugar | toFixed}}</td>
          </tr>
          <tr>
            <td class="back-grey-bold">
              <span class="text-green">
                <b>나트륨</b>
              </span>
            </td>
            <td>{{food.natrium | toFixed}}</td>
          </tr>
          <tr>
            <td class="back-grey-bold">
              <span class="text-green">
                <b>콜레스테롤</b>
              </span>
            </td>
            <td>{{food.chole | toFixed}}</td>
          </tr>
          <tr>
            <td class="back-grey-bold">
              <span class="text-green">
                <b>포화지방산</b>
              </span>
            </td>
            <td>{{food.fattyacid | toFixed}}</td>
          </tr>
          <tr>
            <td class="back-grey-bold">
              <span class="text-green">
                <b>트랜스지방</b>
              </span>
            </td>
            <td>{{food.transfat | toFixed}}</td>
          </tr>
        </tbody>
        <tfoot>
          <tr>
            <td colspan="2"></td>
          </tr>
        </tfoot>
      </table>

      <div style="height:20px"></div>

      <span class="text-2em">LIST</span>
      <span class="text-green" style="font-size:1.8em"> | </span>
      <span class="text-green"><b>섭취한 음식 목록</b></span>
      <div class="margin-20">
      </div>
      <table class="table">
        <thead class="back-grey-bold">
          <th class="text-green">음식명</th>
          <th class="text-green">수량</th>
          <th class="text-green">날짜</th>
          <th class="text-green" width="70px"></th>
        </thead>
        <tr v-for="item in foods" :key="item.idx">
          <td>{{item.foodname}}</td>
          <td>{{item.amount}}</td>
          <td>{{item.regdate}}</td>
          <td>
            <input class="float-right" type="checkbox" name="test" :value="item.idx" v-model="selected" />
          </td>
        </tr>
        <tfoot>
          <tr>
            <td colspan="4">
              <button class="btn btn-success float-right" @click="deleteMyEat">선택항목삭제</button>
            </td>
          </tr>
        </tfoot>
      </table>
    </div>
  </div>
</template>

<script>
  import axios from "../assets/http-commons.js";
  import BarChart from "../assets/barchart";

  export default {
    components: {
      BarChart
    },
    computed: {

    },
    data() {
      return {
        food: {},
        foodYear: [],
        foodMonth: [],
        foodDay: [],
        foods: [],
        chartOptions: {
          hoverBorderWidth: 20
        },
        chartYearData: {},
        chartMonthData: {},
        chartDayData: {},
        selected: [],
        nowTab: "year"
      };
    },
    created() {
      this.fillYearData();
      this.fillMonthData();
      this.fillDayData();
    },
    methods: {

      fillYearData() {
        this.chartYearData = {
          labels: [
            "칼로리",
            "탄수화물",
            "단백질",
            "지방",
            "설탕",
            "나트륨",
            "콜레스테롤",
            "포화지방산",
            "트랜스지방"
          ],
          datasets: []
        };

        this.foodYear.forEach(item => {
          let dst = {
            label: item.regdate,
            backgroundColor: [
              "#fc3d03",
              "#fc9003",
              "#f4fc03",
              "#009e1a",
              "#009e93",
              "#00229e",
              "#69009e",
              "#9e006c"
            ],
            data: [
              item.food.calory,
              item.food.carbo,
              item.food.protein,
              item.food.fat,
              item.food.sugar,
              item.food.natrium,
              item.food.chole,
              item.food.fattyacid,
              item.food.fransfat
            ]
          };
          this.chartYearData.datasets.push(dst);
        });
      },
      fillMonthData() {
        this.chartMonthData = {
          labels: [
            "칼로리",
            "탄수화물",
            "단백질",
            "지방",
            "설탕",
            "나트륨",
            "콜레스테롤",
            "포화지방산",
            "트랜스지방"
          ],
          datasets: []
        };

        this.foodMonth.forEach(item => {
          let dst = {
            label: item.regdate,
            backgroundColor: [
              "#fc3d03",
              "#fc9003",
              "#f4fc03",
              "#009e1a",
              "#009e93",
              "#00229e",
              "#69009e",
              "#9e006c"
            ],
            data: [
              item.food.calory,
              item.food.carbo,
              item.food.protein,
              item.food.fat,
              item.food.sugar,
              item.food.natrium,
              item.food.chole,
              item.food.fattyacid,
              item.food.fransfat
            ]
          };
          this.chartMonthData.datasets.push(dst);
        });
      },
      fillDayData() {
        this.chartDayData = {
          labels: [
            "칼로리",
            "탄수화물",
            "단백질",
            "지방",
            "설탕",
            "나트륨",
            "콜레스테롤",
            "포화지방산",
            "트랜스지방"
          ],
          datasets: []
        };

        this.foodDay.forEach(item => {
          let dst = {
            label: item.regdate,
            backgroundColor: [
              "#fc3d03",
              "#fc9003",
              "#f4fc03",
              "#009e1a",
              "#009e93",
              "#00229e",
              "#69009e",
              "#9e006c"
            ],
            data: [
              item.food.calory,
              item.food.carbo,
              item.food.protein,
              item.food.fat,
              item.food.sugar,
              item.food.natrium,
              item.food.chole,
              item.food.fattyacid,
              item.food.fransfat
            ]
          };
          this.chartDayData.datasets.push(dst);
        });
      },
      getMyEat() {
        let config = {
          headers: {
            Authorization: this.$session.get("auth_token"),
            "Content-Type": "applicaiton/json",
            "Access-Control-Allow-Origin": "*"
          }
        };

        let url =
          "http://localhost:8195/Member/EatList?loginuser=" +
          this.$session.get("login_user");
        axios
          .get(url, config)
          .then(res => {
            this.foods = res.data.data;
            this.food = res.data.dataIngredient;

            console.log("test", this.foods[0].idx);
          })
          .catch()
          .finally();

        url =
          "http://localhost:8195/Member/EatListYMD?id=" +
          this.$session.get("login_user") +
          "&choice=y";
        axios
          .get(url, config)
          .then(res => {
            this.foodYear = res.data.data;
            this.fillYearData();
          })
          .catch()
          .finally();

        url =
          "http://localhost:8195/Member/EatListYMD?id=" +
          this.$session.get("login_user") +
          "&choice=m";
        axios
          .get(url, config)
          .then(res => {
            this.foodMonth = res.data.data;
            this.fillMonthData();
          })
          .catch()
          .finally();

        url =
          "http://localhost:8195/Member/EatListYMD?id=" +
          this.$session.get("login_user") +
          "&choice=d";
        axios
          .get(url, config)
          .then(res => {
            this.foodDay = res.data.data;
            this.fillDayData();
          })
          .catch()
          .finally();
      },
      deleteMyEat() {
        console.log(this.selected);
        let url = "http://localhost:8195/Member/EatList";
        let deleteItem = "";
        this.selected.forEach((item) => {
          deleteItem += "selected=" + item + "&";
        })

        axios.delete(url + "?" + deleteItem).then(() => {
          this.getMyEat();
          this.fillYearData();
          this.fillMonthData();
          this.fillDayData();
        });
      },
      setTab(status) {
        this.nowTab = status;
      }
    },
    mounted() {
      this.getMyEat();
    },
    filters: {
      toFixed(num) {
        if (isNaN(num)) {
          return "0.00";
        } else {
          return num.toFixed(2);
        }
      }
    }
  };
</script>

<style>
  .chart-border {
    background-color: white;
    border: 1px solid #dddddd;
    /* border-left: 1px solid #dddddd;
    border-bottom: 1px solid #dddddd;
    border-right: 1px solid #dddddd; */

    padding-bottom: 10px;
    padding-left: 10px;
    padding-right: 10px;

    box-shadow: 4px 4px 15px 15px #f3f3f3;
    z-index: -1;
  }
</style>