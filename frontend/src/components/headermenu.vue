<template>
    <div class="top">
        <!-- Login Window -->
        <transition name="loginbackfade">
            <div v-if="loginWindow" class="back-black" @click="loginWindow=false">
            </div>
        </transition>

        <transition name="loginfade">
            <div class="back-black" v-if="loginWindow" key="login">
                <div class="container login-window">
                    <div class="login-window-right">
                        <div class="text-white loginwindow-right-text">
                        </div>
                    </div>
                    <div class="login-window-left">
                        <h1 class="text-green">LOGIN</h1>
                        <br>
                        <label class="text-grey" for="">ID</label><br>
                        <input type="text" class="login-inputbox" v-model="user.email"><br>
                        <label class="text-grey" for="">PASSWORD</label><br>
                        <input type="password" class="login-inputbox" v-model="user.password">
                        <br>
                        <br>
                        <button value="LOGIN" class="buttons float-left text-green" @click="doLogin">LOGIN</button>
                        <button value="SUBMIT" class="buttons float-right text-green"
                            @click="loginWindow=false; signupWindow=true">SIGN UP</button>
                        <br>
                        <div class="float-left">
                            <br>
                            <div class="text-red text-center">{{loginMessage}}</div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="back-black" v-if="signupWindow" key="signup">
                <div class="container login-window">
                    <div class="login-window-right">
                        <div class="text-white loginwindow-right-text">
                        </div>
                    </div>
                    <div class="login-window-left">
                        <h1 class="text-green">SIGN UP</h1>
                        <br>
                        <!-- <form> -->
                        <label class="text-grey" for="">ID?</label><br>
                        <input type="text" class="login-inputbox" v-model="signUp.id"><br>
                        <label class="text-grey" for="">PASSWORD</label><br>
                        <input type="password" class="login-inputbox" v-model="signUp.pw">
                        <label class="text-grey" for="">NAME</label><br>
                        <input type="text" class="login-inputbox" v-model="signUp.name"><br>
                        <label class="text-grey" for="">PHONE</label><br>
                        <input type="text" class="login-inputbox" v-model="signUp.phone">
                        <label class="text-grey" for="">EMAIL</label><br>
                        <input type="text" class="login-inputbox" v-model="signUp.email">
                        <div style="height:30px"></div>
                        <label class="text-grey" for="">ALLERGY</label><br>
                        <table class="table">
                            <tr>
                                <td><input id="대두" name="allergy" value="대두" type="checkbox"
                                        v-model="signUp.allergy"><label for="0">대두</label>
                                </td>
                                <td><input id="땅콩" name="allergy" value="땅콩" type="checkbox"
                                        v-model="signUp.allergy"><label for="1">땅콩</label>
                                </td>
                                <td><input id="우유" name="allergy" value="우유" type="checkbox"
                                        v-model="signUp.allergy"><label for="2">우유</label>
                                </td>
                            </tr>
                            <tr>
                                <td><input id="게" name="allergy" value="게" type="checkbox"
                                        v-model="signUp.allergy"><label for="3">게</label></td>
                                <td><input id="새우" name="allergy" value="새우" type="checkbox"
                                        v-model="signUp.allergy"><label for="4">새우</label>
                                </td>
                                <td><input id="참치" name="allergy" value="참치" type="checkbox"
                                        v-model="signUp.allergy"><label for="5">참치</label>
                                </td>
                            </tr>
                            <tr>
                                <td><input id="연어" name="allergy" value="연어" type="checkbox"
                                        v-model="signUp.allergy"><label for="6">연어</label>
                                </td>
                                <td><input id="쑥" name="allergy" value="쑥" type="checkbox"
                                        v-model="signUp.allergy"><label for="7">쑥</label></td>
                                <td><input id="소고기" name="allergy" value="소고기" type="checkbox"
                                        v-model="signUp.allergy"><label for="8">소고기</label>
                                </td>
                            </tr>
                            <tr>
                                <td><input id="닭고기" name="allergy" value="닭고기" type="checkbox"
                                        v-model="signUp.allergy"><label for="9">닭고기</label>
                                </td>
                                <td><input id="돼지고기" name="allergy" value="돼지고기" type="checkbox"
                                        v-model="signUp.allergy"><label for="10">돼지고기</label></td>
                                <td><input id="복숭아" name="allergy" value="복숭아" type="checkbox"
                                        v-model="signUp.allergy"><label for="11">복숭아</label></td>
                            </tr>
                            <tr>
                                <td><input id="민들레" name="allergy" value="민들레" type="checkbox"
                                        v-model="signUp.allergy"><label for="12">민들레</label></td>
                                <td><input id="계란흰자" name="allergy" value="계란흰자" type="checkbox"
                                        v-model="signUp.allergy"><label for="13">계란흰자</label></td>
                                <td></td>
                            </tr>
                        </table>
                        <br>
                        <button value="LOGIN" class="buttons float-left text-green"
                            @click="signupWindow=false">EXIT</button>
                        <button value="SUBMIT" class="buttons float-right text-green" @click="doSignUp">SIGN UP</button>

                        <!-- </form> -->
                    </div>
                </div>
            </div>

            <div class="back-black" v-if="modifyWindow" key="modify">
                <div class="container login-window">
                    <div class="login-window-right">
                        <div class="text-white loginwindow-right-text">
                        </div>
                    </div>
                    <div class="login-window-left">
                        <h1 class="text-green">MODIFY</h1>
                        <br>
                        <!-- <form> -->
                        <label class="text-grey" for="">ID</label><br>
                        <input type="text" class="login-inputbox" v-model="signUp.id"><br>
                        <label class="text-grey" for="">PASSWORD</label><br>
                        <input type="password" class="login-inputbox" v-model="signUp.pw">
                        <label class="text-grey" for="">NAME</label><br>
                        <input type="text" class="login-inputbox" v-model="signUp.name"><br>
                        <label class="text-grey" for="">PHONE</label><br>
                        <input type="text" class="login-inputbox" v-model="signUp.phone">
                        <label class="text-grey" for="">EMAIL</label><br>
                        <input type="text" class="login-inputbox" v-model="signUp.email">
                        <div style="height:30px"></div>
                        <label class="text-grey" for="">ALLERGY</label><br>
                        <table class="table">
                            <tr>
                                <td><input id="대두" name="allergy" value="대두" type="checkbox"
                                        v-model="signUp.allergy"><label for="0">대두</label>
                                </td>
                                <td><input id="땅콩" name="allergy" value="땅콩" type="checkbox"
                                        v-model="signUp.allergy"><label for="1">땅콩</label>
                                </td>
                                <td><input id="우유" name="allergy" value="우유" type="checkbox"
                                        v-model="signUp.allergy"><label for="2">우유</label>
                                </td>
                            </tr>
                            <tr>
                                <td><input id="게" name="allergy" value="게" type="checkbox"
                                        v-model="signUp.allergy"><label for="3">게</label></td>
                                <td><input id="새우" name="allergy" value="새우" type="checkbox"
                                        v-model="signUp.allergy"><label for="4">새우</label>
                                </td>
                                <td><input id="참치" name="allergy" value="참치" type="checkbox"
                                        v-model="signUp.allergy"><label for="5">참치</label>
                                </td>
                            </tr>
                            <tr>
                                <td><input id="연어" name="allergy" value="연어" type="checkbox"
                                        v-model="signUp.allergy"><label for="6">연어</label>
                                </td>
                                <td><input id="쑥" name="allergy" value="쑥" type="checkbox"
                                        v-model="signUp.allergy"><label for="7">쑥</label></td>
                                <td><input id="소고기" name="allergy" value="소고기" type="checkbox"
                                        v-model="signUp.allergy"><label for="8">소고기</label>
                                </td>
                            </tr>
                            <tr>
                                <td><input id="닭고기" name="allergy" value="닭고기" type="checkbox"
                                        v-model="signUp.allergy"><label for="9">닭고기</label>
                                </td>
                                <td><input id="돼지고기" name="allergy" value="돼지고기" type="checkbox"
                                        v-model="signUp.allergy"><label for="10">돼지고기</label></td>
                                <td><input id="복숭아" name="allergy" value="복숭아" type="checkbox"
                                        v-model="signUp.allergy"><label for="11">복숭아</label></td>
                            </tr>
                            <tr>
                                <td><input id="민들레" name="allergy" value="민들레" type="checkbox"
                                        v-model="signUp.allergy"><label for="12">민들레</label></td>
                                <td><input id="계란흰자" name="allergy" value="계란흰자" type="checkbox"
                                        v-model="signUp.allergy"><label for="13">계란흰자</label></td>
                                <td></td>
                            </tr>
                        </table>
                        <br>
                        <button value="LOGIN" class="buttons float-left text-green"
                            @click="modifyWindow=false">EXIT</button>
                        <button value="WITHDRAW" class="buttons float-right text-green" @click="doWithdraw">WITHDRAW</button>
                        <button value="SUBMIT" class="buttons float-right text-green" @click="doModify">MODIFY</button>

                        <!-- </form> -->
                    </div>
                </div>
            </div>
        </transition>

        <!-- MENU : TOP MENU -->
        <transition name="mainmenufade">
            <div v-if="now==='SAFEFOOD'" class="menu" @mouseover="eMenu=false" @mouseleave="eMenu=false">
                <nav class="menu-nav container">
                    <ul>
                        <li class="menu-contents menu-space" @click="setNowPage('SAFEFOOD')">
                            <router-link class="deco-none text-green" to="/"><b>SAFEFOOD</b></router-link>
                        </li>
                        <li class="menu-contents menu-space text-center">
                            <router-link class="deco-none text-white" to="/notice"><span
                                    @click="setNowPage('NOTICE')">NOTICE</span></router-link>
                        </li>
                        <li class="menu-contents menu-space text-center">
                            <router-link class="deco-none text-white" to="/food"><span
                                    @click="setNowPage('FOOD')">FOOD</span></router-link>
                        </li>
                        <li class="menu-contents menu-space text-center" v-if="loginSession">
                            <router-link class="deco-none text-white" to="/my/eat"><span
                                    @click="setNowPage('MY EAT')">MY EAT</span></router-link>
                        </li>
                        <li class="menu-contents menu-space text-center" v-if="loginSession">
                            <router-link class="deco-none text-white" to="/my/favorite"><span
                                    @click="setNowPage('MY FAVORITE')">ZZIM</span></router-link>
                        </li>
                        <li class="menu-contents menu-space text-center">
                            <router-link class="deco-none text-white" to="/qna"><span
                                    @click="setNowPage('QNA')">QnA</span></router-link>
                        </li>
                        <li class="menu-contents menu-space text-center">
                            <router-link class="deco-none text-white" to="/trend"><span
                                    @click="setNowPage('TREND')">TREND</span></router-link>
                        </li>
                    </ul>
                    <ul>
                        <li class="menu-contents float-right menu-right menu-space text-white text-center" v-if="!loginSession"
                            @click="loginWindow=true">LOGIN</li>
                        <li class="menu-contents float-right menu-right menu-space text-white text-center" v-if="loginSession"
                            @click="doLogout">LOGOUT</li>
                        <li class="menu-contents float-right menu-right menu-space text-white text-right" v-if="loginSession"
                            @click="showModifyWindow()">{{getSessionId}}</li>
                    </ul>
                </nav>
            </div>

            <div v-if="now!='SAFEFOOD'" class="menu menu-back-black" @mouseover="eMenu=false" @mouseleave="eMenu=false">
                <nav class="menu-nav container">
                    <ul>
                        <li class="menu-contents menu-space" @click="setNowPage('SAFEFOOD')">
                            <router-link class="deco-none text-green" to="/"><b>SAFEFOOD</b></router-link>
                        </li>
                        <!-- <li></li> -->
                        <li class="menu-contents menu-space text-center menu-highlight">
                            <router-link class="deco-none text-white" to="/notice"><span
                                    @click="setNowPage('NOTICE')">NOTICE</span></router-link>
                        </li>
                        <li class="menu-contents menu-space text-center menu-highlight">
                            <router-link class="deco-none text-white" to="/food"><span
                                    @click="setNowPage('FOOD')">FOOD</span></router-link>
                        </li>
                        <li class="menu-contents menu-space text-center menu-highlight" v-if="loginSession">
                            <router-link class="deco-none text-white" to="/my/eat"><span
                                    @click="setNowPage('MY EAT')">MY EAT</span></router-link>
                        </li>
                        <li class="menu-contents menu-space text-center menu-highlight" v-if="loginSession">
                            <router-link class="deco-none text-white" to="/my/favorite"><span
                                    @click="setNowPage('MY FAVORITE')">ZZIM</span></router-link>
                        </li>
                        <li class="menu-contents menu-space text-center menu-highlight">
                            <router-link class="deco-none text-white" to="/qna"><span
                                    @click="setNowPage('QNA')">QnA</span></router-link>
                        </li>
                        <li class="menu-contents menu-space text-center">
                            <router-link class="deco-none text-white" to="/trend"><span
                                    @click="setNowPage('TREND')">TREND</span></router-link>
                        </li>
                    </ul>
                    <ul>
                        <li class="menu-contents float-right menu-right menu-space text-white text-center" v-if="!loginSession"
                            @click="loginWindow=true">LOGIN</li>
                        <li class="menu-contents float-right menu-right menu-space text-white text-center" v-if="loginSession"
                            @click="doLogout">LOGOUT</li>
                        <li class="menu-contents float-right menu-right menu-space text-white text-right" v-if="loginSession"
                            @click="showModifyWindow()">{{getSessionId}}</li>
                    </ul>
                </nav>
            </div>
        </transition>

        <transition name="menufade" @mouseover="eMenu=true" @mouseleave="eMenu=false">
            <div v-if="eMenu" class="menu-extend">
                <div class="container menu-extend-inner">
                    submenu
                </div>
            </div>
        </transition>

        <!-- HEADER : IMAGE WITH PAGE DESCRIPTION -->
        <transition name="mainfade" mode="out-in">
            <!-- Not MAIN -->
            <div class="header" v-if="now!='SAFEFOOD'" key="notmain">
                <transition name="headerfade">
                    <div class="container header-desc" v-if="now=='FOOD'" key="food">
                        <div class="header-text">
                            <h2>FOODLIST</h2>
                            <span>음식리스트를 표시합니다</span>
                        </div>
                    </div>

                    <div class="container header-desc" v-if="now=='NOTICE'" key="notice">
                        <div class="header-text">
                            <h2>NOTICE</h2>
                            <span>알려드립니다</span>
                        </div>
                    </div>

                    <div class="container header-desc" v-if="now=='MY EAT'" key="myeat">
                        <div class="header-text">
                            <h2>MY EAT</h2>
                            <span>내가 먹은 음식들의 정보를 표시합니다</span>
                        </div>
                    </div>

                    <div class="container header-desc" v-if="now=='MY FAVORITE'" key="myfavorite">
                        <div class="header-text">
                            <h2>ZZIM</h2>
                            <span>내가 찜한 음식들의 정보를 표시합니다</span>
                        </div>
                    </div>

                    <div class="container header-desc" v-if="now=='QNA'" key="qna">
                        <div class="header-text">
                            <h2>QnA</h2>
                            <span>문의사항을 올려주세요</span>
                        </div>
                    </div>

                    <div class="container header-desc" v-if="now=='TREND'" key="trend">
                        <div class="header-text">
                            <h2>TREND</h2>
                            <span>기준별 많이 검색된 랭킹을 보여줍니다</span>
                        </div>
                    </div>
                </transition>
            </div>

            <!-- MAIN -->
            <div class="header-empty" v-if="now=='SAFEFOOD'" key="main">
            </div>
        </transition>
    </div>
</template>

<script>
    import axios from '../assets/http-commons.js';
    import router from '../assets/router'
    // session : https://www.npmjs.com/package/vue-session
    export default {
        data() {
            return {
                // Window & Menu Visiblity
                eMenu: false, // not use

                now: "SAFEFOOD",
                loginWindow: false,
                modifyWindow: false,
                signupWindow: false,

                loginSession: false,
                loginMessage: "",

                //Data
                signUp: {
                    id: "",
                    pw: "",
                    name: "",
                    phone: "",
                    email: "",
                    allergy: [],
                },

                user: {
                    email: "",
                    password: ""
                },
                status: "",
                token: "",
                info: "",
                message: "",
            }
        },
        methods: {
            setNowPage(name) {
                this.now = name;
                this.$session.set("last_name", name);
            },
            doLogin() {
                this.$session.set("auth_token", "");
                this.$session.set("login_user", "");

                let url = "http://localhost:8195/Member/Login";
                axios.post(url, this.user)
                    .then((res) => {
                        console.log(res.data);
                        if (res.data.status === true) {
                            this.$session.start();
                            this.$session.set("login", {
                                status: true
                            })

                            this.loginWindow = false;
                            this.loginSession = true;

                            this.setInfo(
                                "성공",
                                res.data.auth_token,
                                JSON.stringify(res.data.data.id)
                            );
                            this.$session.set("auth_token", res.data.auth_token);
                            this.$session.set("login_user", res.data.data.id);

                            this.signUp.id = res.data.data.id;
                            this.signUp.phone = res.data.data.phone;
                            this.signUp.email = res.data.data.email;
                            this.signUp.name = res.data.data.name;
                            this.signUp.allergy = res.data.data.allergy.split("/");
                            this.$session.set("allergy", this.signUp.allergy);

                            console.log("login", res.data);
                        } else {
                            this.setInfo("", "", "");
                            this.message = "로그인해주세요.";
                            alert("입력 정보를 확인하세요.");
                        }
                    })
                    .catch((e) => {
                        this.loginMessage = "아이디와 비밀번호를 확인하세요";
                        this.setInfo(
                            "실패", "", JSON.stringify(e.response || e.message)
                        )
                    })
                    .finally()
            },
            doLogout() {
                this.user.email = "";
                this.user.password = "";

                this.$session.set("auth_token", "");
                this.$session.set("login_user", "");

                this.loginSession = false;

                this.now = "SAFEFOOD";
                router.push({
                    path: "/"
                })
            },
            setInfo(status, token, info) {
                this.status = status;
                this.token = token;
                this.info = info;
            },
            getInfo() {
                axios.get("/info", {
                        params: {
                            auth_token: this.$store.state.auth_token
                        }
                    })
                    .then(res => {
                        this.setInfo(
                            "정보 조회 성공",
                            res.headers.auth_token,
                            JSON.stringify(res.data)
                        )
                    })
                    .catch(e => {
                        this.setInfo("정보 조회 실패", "", e.response.data.msg);
                    });
            },
            doSignUp() {
                let data = {
                    id: this.signUp.id,
                    pw: this.signUp.pw,
                    name: this.signUp.name,
                    phone: this.signUp.phone,
                    email: this.signUp.email,
                    allergy: this.signUp.allergy.join('/')
                }

                let url = "http://localhost:8195/Member";
                axios.post(url, data)
                    .then(res => {
                        console.log(res.data);
                        this.signupWindow=false;
                    })
            },
            doModify() {
                let data = {
                    id: this.signUp.id,
                    pw: this.signUp.pw,
                    name: this.signUp.name,
                    phone: this.signUp.phone,
                    email: this.signUp.email,
                    allergy: this.signUp.allergy.join('/')
                }

                let url = "http://localhost:8195/Member";
                axios.put(url, data)
                    .then(res => {
                        console.log(res.data);
                        this.modifyWindow = false;
                    })
            },
            doWithdraw(){
                let url = "http://localhost:8195/Member/"+this.$session.get("login_user");
                axios.delete(url)
                .then(res=>{
                    console.log(res.data, "완료");
                    router.push({path:'/'})
                })
            },
            showModifyWindow() {
                this.modifyWindow = true;
            }
        },
        mounted() {
            //backend session check

            //login session
            if (this.$session.has("login_user")) {
                if (this.$session.get("login_user") != "") {
                    this.loginSession = true;
                }
            }
            router.push({path:"/"});

            let url = "http://localhost:8195/info"
            let data = {
                email: this.$session.get("login_user"),
                password: ""
            };
            axios.post(url, data)
                .then(res => {
                    console.log("test", res.data);
                })
        },
        computed: {
            nowStatus() {
                console.log(this.now);
                return this.$session.get("last_name");
            },
            getSessionId(){
                return this.$session.get("login_user");
            }
        },
    }
</script>

<style>
    /* MENU */
    .menu {
        position: fixed;
        top: 0px;
        width: 100vw;
        height: 50px;
        /* padding-bottom:12px; */
        z-index: 3;
    }

    .back-black {
        background-color: black;
    }

    .menu-nav>ul {
        display: inline;
        margin: 0px;
        padding: 0px;
    }

    .menu-nav>ul>li {
        margin-top: 12px;
    }

    .menu-left {
        margin-right: 0px;
    }

    .menu-right {
        margin-left: 20px;
    }

    .menu-space {
        width: 100px;
    }

    .menu-highlight:hover {
        border-bottom: 5px solid green;
    }

    .menu-contents {
        display: inline-block;
        padding: 0px;
    }

    /* EXTEND MENU - NOT USE */
    .menu-extend {
        position: fixed;
        background-color: rgba(255, 255, 255, 0.2);
        width: 100%;
        height: 200px;
    }

    .menu-extend-inner {
        background-color: rgba(255, 255, 255, 0.9);
        height: 200px;
    }

    .deco-none {
        text-decoration: none;
    }

    .deco-none:hover {
        text-decoration: none;
        color: #007209;
    }

    .text-white:hover {
        color: rgba(255, 255, 255, 0.5) !important;
        text-shadow: 0px 0px 1px rgba(255, 255, 255, 1);
    }

    /* HEADER */
    .header {
        margin-top: 0px;
        width: 100%;
        height: 250px;
        color: black;
        background-image: url("../assets/img/header-back.jpg");
    }

    .header-empty {
        height: 100px;
    }

    .header-desc {
        color: white;
        vertical-align: middle;
        height: 100%;
    }

    .header-text {
        position: relative;
        top: 105px;
    }

    .empty {
        background-image: none;
    }

    .input-search {
        border: 0px;
        background-color: rgba(255, 255, 255, 0.0);
        border-bottom: 1px solid silver;
    }

    /* HEADER FADE */
    .headerfade-enter-active,
    .headerfade-leave-active {
        transition: opacity .3s;
    }

    .headerfade-enter,
    .headerfade-leave-to {
        opacity: 0;
    }

    /* SUBMENU FADE */
    .menufade-enter-active,
    .menufade-leave-active {
        transition: opacity .3s;
    }

    .menufade-enter,
    .menufade-leave-to {
        opacity: 0;
    }

    /* MAINPAGE VIDEO FADE */
    .mainfade-enter-active,
    .mainfade-leave-active {
        transition: opacity .3s;
    }

    .mainfade-enter,
    .mainfade-leave-to {
        opacity: 0;
    }

    /* LOGIN PAGE FADE */

    .loginfade-enter-active,
    .loginfade-leave-active {
        transition: opacity .5s;
    }

    .loginfade-enter,
    .loginfade-leave-to {
        opacity: 0;
    }

    .loginbackfade-enter-active,
    .loginbackfade-leave-active {
        transition: opacity .5s;
    }

    .loginbackfade-enter,
    .loginbackfade-leave-to {
        opacity: 0;
    }

    /* LOGIN & SIGNUP & MODIFY */
    .login-window {
        background-color: white;
        height: 100%;
        padding: 0px;
    }

    .login-inputbox {
        border: 0px;
        border-bottom: 1px solid grey;
        margin-top: -5px;
        margin-bottom: 10px;
        width: 100%;
        font-size: 1.5em;
    }

    .buttons {
        border: 0px;
        background-color: white;
    }

    .login-window-right {
        float: right;
        height: 100%;
        width: 70%;
        background-image: url("../assets/img/login-back.webp");
        background-size: cover;
        background-position: center;
        margin: 0px;
        margin-right: -15px;
    }

    .login-window-left {
        float: left;
        height: 100%;
        width: 30%;
        padding: 15px;
        padding-top: 50px;
    }

    .loginwindow-right-text {
        margin: 20px;
        margin-top: 425px;
        bottom: 0px;
    }

    table label {
        margin-left: 5px;
    }

    .back-black {
        position: fixed;
        background-color: rgba(0, 0, 0, 0.3);
        top: 0px;
        left: 0px;
        width: 100vw;
        height: 100vh;
        z-index: 20;
    }

    .fullsize {
        background-color: rgba(0, 0, 0, 0.3);
        z-index: 2000;
        height: 100%;

        left: auto;
        right: auto;
        margin-left: auto;
        margin-right: auto;
    }

    .menu-back-black {
        background-color: rgba(0, 0, 0, 0.8);
    }
</style>