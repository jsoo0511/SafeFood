import Vue from 'vue'
import App from './App.vue'

import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/css/bootstrap-reboot.css';

Vue.config.productionTip = false

import VueRouter from "vue-router";
import router from "./assets/router";
import store from "./assets/vuex";
import VueSession from "vue-session";

import Cheerio from "cheerio";

import cors from "cors";

Vue.use(VueRouter);
Vue.use(VueSession);
Vue.use(cors);
Vue.use(Cheerio);

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
