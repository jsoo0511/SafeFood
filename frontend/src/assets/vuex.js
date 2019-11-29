// 비동기 처리 계획
import Vue from "vue"
import Vuex from "vuex"
// import c from "./constrant"
// import loginAPI from "./loginAPI";
// import axios from "./js/http-commons";
// import actions from "./loginAction";

Vue.use(Vuex);

const store = new Vuex.Store({
    state: {
        loading: false, // 동기 : action 옵션
        error: null, // 동기 : action 옵션

        //Login Options
        auth_token: "",
        login_user: ""        
    },
    getters: {

    },
    mutations: {

    },
    actions: {
    }
})


export default store;