<template>
  <div id="app">
    <d-top />
    <div class="back-whit">
      <div class="container margin-t-40">
        <transition name="detailfade" mode="out-in">
          <router-view></router-view>
        </transition>
        <d-footer />
      </div>
    </div>
  </div>
</template>

<script>
  import dTop from './components/headermenu'
  import dFooter from './components/d_footer'

  export default {
    name: 'app',
    components: {
      dTop, //header + menu
      dFooter
    }
  }
</script>

<style>
  @import url('https://fonts.googleapis.com/css?family=Kanit|Noto+Sans+KR&display=swap');

  #app {
    font-family: 'Kanit', 'Noto Sans KR';
    /* background-color: #f7f7f7; */
  }

  /* Useless outline init*/
  input:focus {
    outline: none;
  }

  .back-white {
    background-color: white
  }

  .back-grey {
    background-color: #f7f7f7;
  }

  .tab-grey-i {
    background-color: #f7f7f7 !important;
    /* border-bottom: 1px solid grey; */
  }

  .back-grey-bold {
    background-color: #f2f2f2;
  }

  .float-right {
    float: right;
  }

  .text-green {
    color: #007209 !important;
  }

  .text-white {
    color: white;
  }

  .text-black {
    color: black;
  }

  .text-grey {
    color: grey;
  }

  .text-grey-light {
    color: #dddddd;
  }

  .text-red {
    color: red;
    font-weight: bold;
  }

  .text-center {
    text-align: center
  }

  .detailfade-enter-active,
  .detailfade-leave-active {
    transition: opacity .3s;
  }

  .detailfade-enter,
  .detailfade-leave-to {
    opacity: 0;
  }

  .margin-t-20 {
    margin-top: 20px;
  }

  .margin-t-40 {
    margin-top: 40px;
  }

  .margin-l-20 {
    margin-left: 20px;
  }

  .margin-r-20 {
    margin-right: 20px;
  }

  .margin-b-20 {
    margin-bottom: 20px;
  }

  .margin-10 {
    margin: 10px;
  }

  .margin-15 {
    margin: 15px;
  }

  .margin-20 {
    margin: 20px;
  }

  .padding-10 {
    padding: 10px;
  }

  .padding-15 {
    padding: 15px;
  }

  .padding-20 {
    padding: 15px;
  }

  .padding-25 {
    padding: 15px;
  }

  .margin-1 {
    margin: 1px;
  }

  .text-1em {
    font-size: 1em;
  }

  .text-2em {
    font-size: 2em;
  }

  .text-3em {
    font-size: 3em;
  }

  .text-4em {
    font-size: 4em;
  }

  .text-5em {
    font-size: 5em;
  }

  .star {
    color: #faf600;
    text-shadow: 0px 0px 1px #000000;
    font-size: 1em;
  }

  .star:hover {
    text-shadow: 0px 0px 1px #000000;
  }

  #myVideo {
    position: fixed;
    right: 0;
    bottom: 0;
    min-width: 100%;
    min-height: 100%;
    z-index: -99;
  }

  .full-height {
    height: 100%;
  }

  .full-width {
    width: 100%;
  }

  /* SCROLL BAR */
  html {
    overflow-y: scroll;
  }

</style>