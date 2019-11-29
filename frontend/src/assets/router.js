import VueRouter from "vue-router";
import dMain from "../components/d_main";
import dNotice from "../components/d_notice";
import dFood from "../components/d_food";
import dMyeat from "../components/d_myeat";
import dQna from "../components/d_qna";
import dMyfavorite from "../components/d_myfavorite";
import dTrend from "../components/d_trend";

import fDetail from "../components/food/f_detail";

import bWrite from "../components/notice/b_write";
import bModify from "../components/notice/b_modify";
import bDetail from "../components/notice/b_detail";

import qDetail from "../components/qna/q_detail"
import qInsert from "../components/qna/q_insert"
import qUpdate from "../components/qna/q_update"

const router = new VueRouter({
    // 애플리케이션에서 사용되는 모든 경로 정보를 저장하는 배열
    routes: [
        // {
        //     name:"경로에 대한 예명",
        //     path:"요청하는 경로",
        //     component:"표시해줄 컴포넌트"
        // }
        {
            name: "main",
            path: "/",
            component: dMain
        },
        {
            name: "notice",
            path: "/notice",
            component: dNotice
        },
        {
            name: "foodlist",
            path: "/food",
            component: dFood
        },
        {
            name: "myeat",
            path: "/my/eat",
            component: dMyeat
        },
        {
            name: "qna",
            path: "/qna",
            component: dQna
        },

        {
            name: "food_detail",
            path: "/food/:code",
            component: fDetail,
            props: true
        },
        {
            name: "myfavorite",
            path: "/my/favorite",
            component: dMyfavorite
        },

        // NOTICE
        {
            name: "NoticeWrite",
            path: "/notice/write",
            component: bWrite
        },
        {
            name: "NoticeModify",
            path: "/notice/modify/:no",
            component: bModify,
            props: true
        },
        {
            name: "NoticeDetail",
            path: "/notice/:no",
            component: bDetail,
            props: true
        },
        {
            name: "qnaDetail",
            path: "/detail/:no",
            component: qDetail,
            props: true
        },
        {
            name: "qnaInsert",
            path: "/insert",
            component: qInsert
        },
        {
            name: "qnaUpdate",
            path: "/updateBoard/:no",
            component: qUpdate,
            props: true
        },
        {
            name: "trend",
            path: "/trend",
            component: dTrend
        },
    ]
});

export default router

// const router = new VueRouter({
//     mode:'history',     // #값 제거