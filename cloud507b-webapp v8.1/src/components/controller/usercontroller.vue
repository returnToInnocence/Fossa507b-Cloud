<template>
  <div class="grid-container">
<!--    <header>-->
<!--      <el-menu-->
<!--          :default-active="activeIndex"-->
<!--          class="el-menu-demo"-->
<!--          mode="horizontal"-->
<!--          :ellipsis="false"-->
<!--          @select="handleSelect"-->
<!--      >-->
<!--        <el-menu-item index="0">实验室资源可视化</el-menu-item>-->
<!--        <div class="flex-grow" />-->
<!--        <el-menu-item index="1" @click="router.push('/index/resourceview')"-->
<!--        >展板展示</el-menu-item-->
<!--        >-->
<!--        <el-menu-item index="2">用户控制与实时聊天</el-menu-item>-->
<!--        <el-menu-item index="3" @click="router.push('/index/homepost')"-->
<!--        >小窝帖子</el-menu-item-->
<!--        >-->
<!--      </el-menu>-->
<!--    </header>-->
    <main>
      <aside>
        <section class="bar">
          <div class="item">
            <div class="block">
              <img src="/src/img/temperature.png" />
              <div class="unit">温度</div>
              <div class="intemperature" style="color: white;">{{ airTemp }} °C</div>
            </div>
          </div>
          <div>
            <div class="light">
              <Switch v-model:checked="checked" id="check1">
                <template #node="{ checked }">
                  <svg
                      v-if="checked"
                      xmlns="http://www.w3.org/2000/svg"
                      aria-hidden="true"
                      focusable="false"
                      viewBox="0 0 24 24"
                      class="sun"
                  >
                    <path
                        d="M12,18c-3.3,0-6-2.7-6-6s2.7-6,6-6s6,2.7,6,6S15.3,18,12,18zM12,8c-2.2,0-4,1.8-4,4c0,2.2,1.8,4,4,4c2.2,0,4-1.8,4-4C16,9.8,14.2,8,12,8z"
                    ></path>
                    <path
                        d="M12,4c-0.6,0-1-0.4-1-1V1c0-0.6,0.4-1,1-1s1,0.4,1,1v2C13,3.6,12.6,4,12,4z"
                    ></path>
                    <path
                        d="M12,24c-0.6,0-1-0.4-1-1v-2c0-0.6,0.4-1,1-1s1,0.4,1,1v2C13,23.6,12.6,24,12,24z"
                    ></path>
                    <path
                        d="M5.6,6.6c-0.3,0-0.5-0.1-0.7-0.3L3.5,4.9c-0.4-0.4-0.4-1,0-1.4s1-0.4,1.4,0l1.4,1.4c0.4,0.4,0.4,1,0,1.4C6.2,6.5,5.9,6.6,5.6,6.6z"
                    ></path>
                    <path
                        d="M19.8,20.8c-0.3,0-0.5-0.1-0.7-0.3l-1.4-1.4c-0.4-0.4-0.4-1,0-1.4s1-0.4,1.4,0l1.4,1.4c0.4,0.4,0.4,1,0,1.4C20.3,20.7,20,20.8,19.8,20.8z"
                    ></path>
                    <path
                        d="M3,13H1c-0.6,0-1-0.4-1-1s0.4-1,1-1h2c0.6,0,1,0.4,1,1S3.6,13,3,13z"
                    ></path>
                    <path
                        d="M23,13h-2c-0.6,0-1-0.4-1-1s0.4-1,1-1h2c0.6,0,1,0.4,1,1S23.6,13,23,13z"
                    ></path>
                    <path
                        d="M4.2,20.8c-0.3,0-0.5-0.1-0.7-0.3c-0.4-0.4-0.4-1,0-1.4l1.4-1.4c0.4-0.4,1-0.4,1.4,0s0.4,1,0,1.4l-1.4,1.4C4.7,20.7,4.5,20.8,4.2,20.8z"
                    ></path>
                    <path
                        d="M18.4,6.6c-0.3,0-0.5-0.1-0.7-0.3c-0.4-0.4-0.4-1,0-1.4l1.4-1.4c0.4-0.4,1-0.4,1.4,0s0.4,1,0,1.4l-1.4,1.4C18.9,6.5,18.6,6.6,18.4,6.6z"
                    ></path>
                  </svg>
                  <svg
                      v-else
                      xmlns="http://www.w3.org/2000/svg"
                      aria-hidden="true"
                      focusable="false"
                      viewBox="0 0 24 24"
                      class="moon"
                  >
                    <path
                        d="M12.1,22c-0.3,0-0.6,0-0.9,0c-5.5-0.5-9.5-5.4-9-10.9c0.4-4.8,4.2-8.6,9-9c0.4,0,0.8,0.2,1,0.5c0.2,0.3,0.2,0.8-0.1,1.1c-2,2.7-1.4,6.4,1.3,8.4c2.1,1.6,5,1.6,7.1,0c0.3-0.2,0.7-0.3,1.1-0.1c0.3,0.2,0.5,0.6,0.5,1c-0.2,2.7-1.5,5.1-3.6,6.8C16.6,21.2,14.4,22,12.1,22zM9.3,4.4c-2.9,1-5,3.6-5.2,6.8c-0.4,4.4,2.8,8.3,7.2,8.7c2.1,0.2,4.2-0.4,5.8-1.8c1.1-0.9,1.9-2.1,2.4-3.4c-2.5,0.9-5.3,0.5-7.5-1.1C9.2,11.4,8.1,7.7,9.3,4.4z"
                    ></path>
                  </svg>
                </template>
              </Switch>
              <div class="lightp" >灯光控制</div>
              <el-switch
                  v-model="value2"
                  class="mt-2"
                  style="margin-left: 15px"
                  inline-prompt
                  :active-icon="Check"
                  :inactive-icon="Close"
                  size="large"
                  onclick="moterClick"
              />
              <button class="lightp1"  type="button" @click="method1()">窗帘控制</button>
            </div>
          </div>
          <div id="guangzhao">
            <div class="icon">
              <img src="/src/img/sun.png" />
            </div>
            <div class="word">光照</div>
            <div class="sun_number">{{ lightValueData }} Lux</div>
          </div>
          <div>
            <div class="light_status">
              <template v-if="lightValue">
                <img src="/src/img/lightc.png" alt="Image 1" />
              </template>
              <template v-else>
                <img src="/src/img/lighto.png" alt="Image 2" />
              </template>
              <div class="small_tip">灯光状态</div>
            </div>
            <div class="people_status">
              <template v-if="peopleValue">
                <img src="/src/img/people.png" alt="Image 1" />
              </template>
              <template v-else>
                <img src="/src/img/people1.png" alt="Image 2" />
              </template>
              <div class="small_tip1">人员状态</div>
            </div>
          </div>
        </section>
        <section class="cards">
          <cards></cards>
        </section>
      </aside>
      <section>
        <helloName v-if="!flag" :flag="flag" @changeFlag="changeFlag"></helloName>
        <chat class="chat" v-else></chat>
      </section>
    </main>
  </div>
</template>

<script setup>
import Switch from "./switch.vue";
import { ref, watch, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import axios from "axios";
import chat from "../community/chat.vue";
import helloName from "../community/nameChat.vue";
import cards from "../datasource/cards.vue";
import { Check, Close } from '@element-plus/icons-vue'
const value2 = ref(true)  //窗帘控制
const flag = ref(false);
//改变温度计显示的值
const sliderValue = ref(37.2);
// 实验室的灯关状态
const lightValue = ref(false); // 根据需要设置该值为 true 或 false
// 实验室是否有人状态 —— 通过上座来判断
const peopleValue = ref(false);
const activeIndex = ref("2");
const props = defineProps({
  value: {
    type: Boolean,
    required: true,
  },
});
const router = useRouter();
const marks = {
  0: "0",
  10: "10",
  20: "20",
  30: "30",
  40: "40",
};
//开关
const checked = ref(false);
function onChange(checked) {
  console.log("checked:", checked);
}
watch(checked, (to) => {
  console.log("to:", to);
});
function changeFlag() {
  flag.value = true;
}
function method1(){
  alert(changeValue);
  if (changeValue == false){
    axios
        .get("http://localhost:8080/api/controlMoterForeward")
        .then(function (response) {
        })
        .catch(function (error) {
          console.log(error);
        });
    changeValue.value = true;
  }else {
    axios
        .get("http://localhost:8080/api/controlMoterBackward")
        .then(function (response) {
        })
        .catch(function (error) {
          console.log(error);
        });
    changeValue.value = false;
  }

}
const changeValue = ref(false);
const airTemp = ref(0);
const lightValueData = ref(0);

function flashPage() {
  // alert("nihao")
  axios
      .get("http://localhost:8080/api/mqttGetAirTmp")
      .then(function (response) {
        console.log(response);
        airTemp.value = response.data.toFixed(1);
        console.log(airTemp.value);
        axios
            .get("http://localhost:8080/api/mqttGetLightValue")
            .then(function (response) {
              console.log(response);
              lightValueData.value = response.data;
              console.log(airTemp.value);
            })
            .catch(function (error) {
              console.log(error);
            });
      })
      .catch(function (error) {
        console.log(error);
      });
}
function moterClick(){
  alert("hahhah")
}
// 在页面加载后调用函数
onMounted(flashPage);
</script>

<style scoped lang="scss">
.el-main {
  overflow: hidden;
}

.grid-container {
  /* display: grid;
  grid-template-columns: 1fr 1fr; */
  /* 两列宽度相等 */
  /* grid-template-rows: 1fr 1fr; */
  /* 两行高度相等 */
  /* gap: 15px; */
  /* 可选，设置行列间距 */
  // opacity: 0.8;
  width: 100%;
  height: 100%;
  overflow: hidden;
  display: flex;
  flex-direction: column;
}

.item {
  /* 这里可以根据具体需求设置样式，比如设置背景色和内边距等 */
  background-color: #acbfdf;
  width: 100%;
  /* border-radius: 5vh; */
  /* box-shadow: #29458e; */
  height: 100%;
  // clip-path: polygon(0 0, 70% 0, 100% 30%, 100% 100%, 0 100%);
  border: 1px solid #0c1211;
}

img {
  // height: 100%;
  // width: 100%;
}

.sun {
  width: 25px;
  height: 25px;
  fill: rgba(255, 255, 255);
}

.moon {
  width: 25px;
  height: 25px;
  fill: rgba(255, 255, 255);
}
#check1 {
  position: relative;
  margin-top: -3vh;
  margin-left: 12vh;
}
/* 开关控制灯 */
.light {
  // width: 40%;
  height: 100%;
  // margin: 10vh auto;
  display: flex;
  justify-content: center;
  align-items: center;
}

.lightp {
  /* background-color: #2997f8; */
  font-size: larger;
  font-weight: bolder;
  font-family: "楷体";
  position: relative;
  color: white;
  top: 3vh;
  left: -10vh;
  white-space: nowrap;
  text-overflow: ellipsis;
}
.mt-2{
  position: relative;
  top: -15px;

}
.lightp1{
  font-size: larger;
  font-weight: bolder;
  font-family: "楷体";
  position: relative;
  top: 3vh;
  left: -8vh;
  white-space: nowrap;
  text-overflow: ellipsis;
  color: white;
}

/* 温度计 */
.block {
  display: flex;
  align-items: center;
  justify-content: center;
  > div {
    // flex: 1;
  }
}

.block .unit {
  // width: 100px;
  max-width: 40px;
  height: 30px;
  font-weight: bold;
  font-family: "楷体";
  color: white;
  margin-top: 8vh;
  margin-left: 5vh;
  // margin-left: 50px;
  // margin-top: 25vh;
  /* background-color: aquamarine; */
}
.block img {
  opacity: 1;
  width: 10vh;
  height: 10vh;
}
.block div {
  opacity: 1;
  color: black;
}
.unit p {
  font-size: 14px;
  color: rgb(59, 59, 59);
}

.block .bit {
  width: 15px;
  height: 15px;
  background: linear-gradient(180deg, #3fbbfe, #a541ff);
  border: 0.5px solid #29458e;
  border-radius: 50%;
  margin: 1px 10px;
}

.el-slider.is-vertical .el-slider__marks-text {
  // left: 37px;
  margin: 0 auto;
}

.el-slider__bar {
  background: linear-gradient(180deg, #ff1212, #43b6fe);
}

.el-slider__runway {
  width: 100%;
  margin: 16px 0;
  background-color: #9196a4;
  border-radius: 3px;
  position: relative;
  cursor: pointer;
  vertical-align: middle;
}

.el-slider__marks-text {
  color: #a2d6ef;
}

.el-slider__stop {
  height: 2px;
  width: 17px;
  margin-left: 11px;
  border-radius: 0;
  /* background-color: #43b6fe; */
  transform: translateX(-50%);
}

.el-slider__button {
  border: none;
  background-color: transparent;
}

.temperature {
  // width: 20%;
  // height: 70%;
  /* background-color: #2997f8; */
  // margin-left: 10vh;
  // margin-top: 4vh;
  flex: 1;
  color: white;
  // display: flex;
  // justify-content: center;
  // height: auto;
  margin: 0 auto;
}

.intemperature {
  // width: 130px;
  // height: 60px;
  /* background-color: #a541ff; */
  margin-top: -2vh;
  margin-left: -5vh;
  font-size: 35px;
  font-weight: bolder;
}

.icon {
  position: relative;
  // width: 100px;
  // height: 100px;
  /* background-color: #43b6fe; */
  // top: 10vh;
  // left: 10vh;
}

.icon img {
  // width: 100px;
  // height: 100px;
}
#guangzhao img{
  width:10vh;
  position: absolute;
  top:0vh;
  left:5vh;
}
.sun_number {
  // width: 100px;
  // height: 100px;
  /* background-color: #3fbbfe; */
  margin-top: -2vh;
  margin-left: 20vh;
  font-size: 35px;
  font-weight: bolder;
  color: white;
  position: relative;
  // left: 200px;
  // top: -30px;
  font-family: "楷体";
  min-width: 100px;
}

.word {
  /* background-color: #a541ff; */
  position: relative;
  left: 20vh;
  top: 7vh;
  // width: 200px;
  font-size: 18px;
  font-weight: bold;
  font-family: "楷体";
  color: white;
}
.light_status img{
  width:6vh;
  margin-left: 0vh;
  position: relative;
  left:4vh;
  top:0vh;
}
.people_status img{
  width:6vh;
  margin-left: 0vh;
  position: relative;
  left:20vh;
  top:-6vh;

}
/* 室内有无灯 */
.light_status {
  // width: 120px;
  // position: relative;
  // left: 70px;
  // top: 50px;
  /* background-color: #43b6fe; */
  display: flex;
  flex-direction: row;

}

.people_status {
  // width: 120px;
  position: relative;
  // left: 280px;
  // top: -50px;
  /* background-color: #041824; */
}

#status {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 0;

  > div {
    flex: 1;
  }
}

#status img {
  // width: 100px;
  // height: 100px;
}

.el-popper.is-customized {
  /* Set padding to ensure the height is 32px */
  padding: 6px 12px;
  background: linear-gradient(90deg, #43a8d7, #4a57e3);
}

.el-popper.is-customized .el-popper__arrow::before {
  background: linear-gradient(45deg, #43a8d7, #4a57e3);
  right: 0;
}

.small_tip {
  // width: 30px;
  height: 30px;
  // position: absolute;
  // right: 210px;
  // bottom: 50px;
  // width: 200px;
  font-size: 18px;
  font-weight: bold;
  font-family: "楷体";
  color: white;
  position: relative;
  top:6vh;
  left:-3vh;
}

.small_tip1 {
  // width: 30px;
  height: 30px;
  // position: absolute;
  // right: 10px;
  // bottom: 50px;
  // width: 200px;
  font-size: 18px;
  font-weight: bold;
  font-family: "楷体";
  color: white;
  position: relative;
  left:19vh;
  top:-6vh;
}

main {
  display: flex;
  flex: 1;
  align-items: center;
  width: 100%;

  > aside {
    width: 50%;
    display: flex;
    flex-direction: column;
    overflow: hidden;
    height: 100%;
    overflow: hidden;
    margin: 0 20px;
    padding: 20px;
    // background-color: #dfef93;
    // background-image: linear-gradient(to right, #dfef93 0%, #e55d87 100%);
    > section {
      // flex:1;
      overflow: hidden;
    }
    // .bar{
    //   display: flex;
    //   flex-direction: column;
    //   overflow: hidden;
    // }
    .bar {
      display: flex;
      flex-wrap: wrap;
      overflow: hidden;
      width: 80%;
      margin-left: 10%;
      // background-color: rgba(225, 236, 181, 0.3);
      border-radius: 2vh;
    }

    .bar > div {
      width: 48%; /* 每个子元素宽度占一半 */
      height: 100px; /* 每个子元素高度为100像素 */
      background-color: rgba(242, 243, 237, 0.3);
      margin: 5px;
      opacity: 1;
      border: none;
      border-radius: 2vh;
    }

    .cards {
      flex: 1;
      background-color: rgba(242, 243, 237, 0.3);
      border-radius: 2vh;
      margin-top: 2vh;
      height:10vh;
    }

    .strength {
      display: flex;
      align-items: center;
      justify-content: center;

      .icon {
        // flex: 1;
      }
    }

    .item {
      flex: 1;
    }
  }

  > section {
    flex: 1;
    height: 100%;
  }
}

header {
  section {
    display: flex;
    flex-direction: column;
    opacity: 0.1;
    background-color: #3fbbfe;
  }
}

.chat {
  padding: 0;
  margin: 0;
  height: 100%;
  width: 100%;
}
</style>
