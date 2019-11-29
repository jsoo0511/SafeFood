<template>
    <div>
        <span class="text-2em">RANK</span>
        <span class="text-green" style = "font-size:1.8em"> | </span>
        <span class="text-green"><b>항목별 순위</b></span>
        <div class="margin-20">
        </div>
        <div class="row">
            <div class="col-md-4 col-sm-4">
                <h4 class="text-green">검색어</h4>
                <h6>가장 많이 검색된 검색어</h6>
                <table class="table table-striped">
                    <tbody>
                        <tr v-for = "(item, idx) in wordRank" :key="idx">
                            <td width="5%" class="text-right text-green"><b>{{idx+1}}</b></td>
                            <td>{{item.word=="" ? "DUMMY" : item.word}}</td>
                            <td>{{item.count}}</td>
                        </tr>
                    </tbody>
                    <tfoot>
                        <tr>
                            <td colspan="3"></td>
                        </tr>
                    </tfoot>
                </table>
            </div>
            <div class="col-md-4 col-sm-4">
                <h4 class="text-green">검색</h4>
                <h6>가장 많이 보여진 음식명</h6>
                <table class="table table-striped">
                    <tbody>
                        <tr v-for = "(item, idx) in searchRank" :key="idx">
                            <td width="5%" class="text-right text-green"><b>{{idx+1}}</b></td>
                            <td>{{item.name}}</td>
                            <td>{{item.sfreq}}</td>
                        </tr>
                    </tbody>
                    <tfoot>
                        <tr>
                            <td colspan="3"></td>
                        </tr>
                    </tfoot>
                </table>
            </div>
            <div class="col-md-4 col-sm-4">
               <h4 class="text-green">상세정보</h4>
                <h6>가장 상세정보를 많이 본 음식</h6>
               <table class="table table-striped">
                    <tbody>
                        <tr v-for = "(item, idx) in detailRank" :key="idx">
                            <td width="5%" class="text-right text-green"><b>{{idx+1}}</b></td>
                            <td>{{item.name}}</td>
                            <td>{{item.frequency}}</td>
                        </tr>                        
                    </tbody>
                    <tfoot>
                        <tr>
                            <td colspan="3"></td>
                        </tr>
                    </tfoot>
                </table>
            </div>
        </div>
    </div>
</template>

<script>
    import axios from '../assets/http-commons.js';
    // import router from '../assets/router'
    export default {
        data(){
            return{
                wordRank:[],
                searchRank:[],
                detailRank:[]
            }
        },
        mounted() {
            this.getWordRank();
            this.getSearchRank();
            this.getDetailRank();
        },
        methods: {
            getWordRank(){
                let url = "http://localhost:8195/SearchHistory?id="  
                axios.get(url)
                .then((res)=>{
                    console.log("t",res.data);
                    this.wordRank = res.data.data;
                })
            },
            getSearchRank(){
                let url = "http://localhost:8195/foodFrequency?choice=s"  
                axios.get(url)
                .then((res)=>{
                    this.searchRank = res.data.data;
                })
            },
            getDetailRank(){
                let url = "http://localhost:8195/foodFrequency?choice=c"  
                axios.get(url)
                .then((res)=>{
                    console.log(res.data.data);
                    this.detailRank = res.data.data;
                })
            }
        },
    }
</script>

<style>

</style>