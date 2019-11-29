<template>
    <div>
        <video autoplay muted loop id="myVideo">
            <source src="../assets/vid/main-back.mp4" type="video/mp4">
        </video>

        <div class="main-desc">
            <h1 class="times-2">WHAT WE PROVIDE</h1>
            <div class="subtitle">
                <span>HEALTHY FOOD FOR YOU</span>
            </div>
        </div>
        <div class="main-desc">
            <div>
                <div class="float-left col-md-4 col-sm-4 full-height desc1 padding-25">
                    <i class="icon-full fa fa-envira text-white"></i>
                    <hr style="border:1px solid white">
                    <h1 class="text-white text-highlight">HEALTHY</h1>
                    <h5 class="text-grey-light">당신을 위한<br>건강한 음식 데이터베이스</h5>
                </div>
                <div class="float-left col-md-4 col-sm-4 full-height desc2 padding-25">
                    <i class="icon-full fa fa-map text-white"></i>
                    <hr style="border:1px solid white">
                    <h1 class="text-white text-highlight">ASSISTANT</h1>
                    <h5 class="text-grey-light">식습관 개선을<br>위한 길잡이</h5>
                </div>
                <div class="float-left col-md-4 col-sm-4 full-height desc3 padding-25">
                    <i class="icon-full fa fa-universal-access text-white"></i>
                    <hr style="border:1px solid white">
                    <h1 class="text-white text-highlight">FOR YOU</h1>
                    <h5 class="text-grey-light">당신에게 꼭 들어맞는<br>관리기능</h5>
                </div>
            </div>
        </div>
        <div style="clear:both;height:100px">
        </div>
    </div>
</template>

<script>
    export default {

    }
</script>

<style>
    .intro-block {
        display: inline-block;
        text-align: center;
        color: white;
        width: 100%;
    }

    .intro-icon {
        display: inline-block;
        background-color: rgba(100, 100, 100, 0.5);
        padding: 12%;
        /* border-radius: 50%; */
        margin-bottom: 15px;
        box-shadow: 0px 0px 60px 60px rgba(155, 155, 155, 0.12);
    }

    .intro-icon:hover {
        background-color: rgba(0, 0, 0, 1);
    }

    .intro-desc {
        position: absolute;
        bottom: 0px;
        left: 0px;
        width: 100%;
        background-color: rgba(30, 30, 30, 0.5);
    }

    .main-desc {
        text-align: center;
        margin: 100px 0px 100px 0px;
        color: white;
    }

    .icon-full {
        font-size: 4em;
        margin: 10px;
    }

    .times-2 {
        font-size: 5em;
    }

    .main-description {
        height: 270px;
        /* background-color: rgba(30,30,30,0.5);         */
    }

    .desc1 {
        background-color: rgba(30, 30, 30, 0.5);
    }

    .desc2 {
        background-color: rgba(50, 50, 50, 0.5);
    }

    .desc3 {
        background-color: rgba(70, 70, 70, 0.5);
    }

    .subtitle {
        padding-top: 5px;
        padding-bottom: 5px;
        font-size: 1.5em;
        background-color: #007200
    }

    .text-highlight{
        text-shadow: 0px 0px 15px rgba(255,255,255,0.5);
    }
</style>