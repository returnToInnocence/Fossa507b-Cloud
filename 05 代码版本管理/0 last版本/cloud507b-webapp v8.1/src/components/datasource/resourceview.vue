<template>
  <div class="all">
    <header>
      <h2 style="color: aliceblue;">实验室资源可视化</h2>
      <section class="section-bottom"> </section>
    </header>
    <div class="container">
      <div class="box1" style="height: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    background-color: rgba(245,246,250,.1);
    border-radius: 6px 6px 6px 6px;">
        <div id="chart1" ref="myChart2" class="column"></div>
        <div class="box2">
          <el-button text @click="dialogVisible = true" id="button1" style="color: white">
            吵闹程度投票
          </el-button>
          <el-dialog v-model="dialogVisible" title="请选择吵闹程度" width="30%" draggable>
            <el-select v-model="value" class="m-2" placeholder="请选择">
              <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value" />
            </el-select>
            <template #footer>
              <span class="dialog-footer">
                <el-button @click="dialogVisible = false">取消</el-button>
                <el-button type="primary" @click="dialogVisible = false">
                  确认
                </el-button>
              </span>
            </template>
          </el-dialog>
        </div>

      </div>
      <div class="box1" style="height: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    background-color: rgba(245,246,250,.1);
    border-radius: 6px 6px 6px 6px;">
        <div id="chart2" class="column" ref="myChart1"></div>
        <div class="box2">
          <el-button text id="button3" style="color:white;">座位资源详情</el-button>
        </div>
      </div>
      <div class="box1" style="height: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    background-color: rgba(245,246,250,.1);
    border-radius: 6px 6px 6px 6px;">
        <div id="chart3" class="column" ref="myChart3"></div>
        <div class="box2">
          <el-button text @click="open()" id="button2" style="color: white">期望温度反馈</el-button>
        </div>
      </div>
    </div>
    <section class="all-bottom">
      <div>
        <seats></seats>
      </div>
      <!-- <div>
        <cards></cards>
      </div> -->

    </section>
  </div>
</template>

<script>
import * as echarts from "echarts";
import { ref } from "vue";
import { ElMessage, ElMessageBox } from "element-plus";
import axios from 'axios';
export default {
  data: () => ({
    dialogVisible: ref(false),
    //吵闹程度选择的配置，value和options
    value: ref(""),
    options: [
      {
        value: "1",
        label: "很吵",
      },
      {
        value: "2",
        label: "有点吵",
      },
      {
        value: "3",
        label: "正常",
      },
      {
        value: "4",
        label: "安静",
      },
    ],
    responseData: null,
  }),
  name: "datapic",
  mounted() {
    this.drawLine();
    this.drawZhu();
    this.drawLine1();
  },
  methods: {
    open() {
      console.log("heloowooo");
      ElMessageBox.prompt("请输入你期望的空调温度", "Tip", {
        confirmButtonText: "确认",
        cancelButtonText: "取消",
        inputPattern: /^\d+$/,
        inputErrorMessage: "不是温度值",
      })
          .then(({ value }) => {
            //这里向后端发请求，将前端的值写入数据库
            console.log("value",value);
            axios.post('/api/addExpTemp', {
              temper: value,
            })
                .then(response =>{
                  //请求成功的处理逻辑
                  console.log(response);
                  alert("提交成功");
                }).catch(error => {
              //请求失败的处理逻辑
              console.error(error)
              alert("提交失败");
            })
            ElMessage({
              type: "success",
              message: `你期望的温度为:${value}`,
            });
          })
          .catch(() => {
            ElMessage({
              type: "info",
              message: "取消输入",
            });
          });
    },
    //图1，柱状图
    drawZhu() {
      let myChart = echarts.init(this.$refs.myChart2);
      let option;
      axios.get('/api/getNoiceList').then((response)=> {
        console.log(response.data.data);
        this.responseData = response.data.data;
        //提取吵闹程度的值
        const value0 = this.responseData.map(item => item.noice_status0_value)
        const value1 = this.responseData.map(item => item.noice_status1_value)
        const value2 = this.responseData.map(item => item.noice_status2_value)
        const value3 = this.responseData.map(item => item.noice_status3_value)
        //提取日期
        const date = this.responseData.map(item => item.data)
        option = {
          title: {
            text: "吵闹程度",
            left: "center", // 设置标题水平居中
            color: "white",
            textStyle: {
              color: "white", // 设置标题颜色
              fontSize: 18, // 设置标题字号
              fontWeight: "bold", // 设置标题加粗
              fontFamily: "Arial, sans-serif", // 设置标题字体
            },
          },
          tooltip: {
            trigger: "axis",
            axisPointer: {
              type: "shadow",
            },
          },
          grid: {
            left: "3%",
            right: "4%",
            bottom: "3%",
            containLabel: true,
          },
          xAxis: [
            {
              type: "category",
              data: date,
              axisLabel: { // 设置纵轴标签的字体颜色
                color: "white"
              }
            },
          ],
          yAxis: [
            {
              type: "value",
              min: 0,//设置纵坐标最小值
              max: 20,//设置纵坐标最大值
              interval:5,
              axisLabel: { // 设置纵轴标签的字体颜色
                color: "white"
              }
            },
          ],
          series: [
            {
              name: "很吵",
              type: "bar",
              stack: "Ad",
              emphasis: {
                focus: "series",
              },
              data: value0,
            },
            {
              name: "有点吵",
              type: "bar",
              stack: "Ad",
              emphasis: {
                focus: "series",
              },
              data: value1,
            },
            {
              name: "正常",
              type: "bar",
              stack: "Ad",
              emphasis: {
                focus: "series",
              },
              data: value2,
            },
            {
              name: "安静",
              type: "bar",
              stack: "Ad",
              emphasis: {
                focus: "series",
              },
              data: value3,
            },
          ],
        };
        myChart.setOption(option);
      }).catch(function (error){
        console.log(error)
      });
    },
    //图2，折线图
    drawLine() {
      let myChart = echarts.init(this.$refs.myChart1);
      let option;
      //这里的函数调用还有问题
      axios.get('/api/getNoiceList').then((response)=> {
        console.log(response.data.data);
        this.responseData = response.data.data;

        //提取吵闹程度的值
        const value0 = this.responseData.map(item => item.noice_status0_value)
        const value1 = this.responseData.map(item => item.noice_status1_value)
        const value2 = this.responseData.map(item => item.noice_status2_value)
        const value3 = this.responseData.map(item => item.noice_status3_value)
        //提取日期
        const date = this.responseData.map(item => item.data)
        option = {
          title: {
            text: "座位使用情况",
            left: 'center',
            textStyle: {
              color: "white", // 设置标题颜色
              fontSize: 18, // 设置标题字号
              fontWeight: "bold", // 设置标题加粗
              fontFamily: "Arial, sans-serif", // 设置标题字体
            },
          },
          tooltip: {
            trigger: "axis",
          },
          grid: {
            left: "3%",
            right: "4%",
            bottom: "3%",
            containLabel: true,
          },
          xAxis: {
            type: "category",
            boundaryGap: false,
            data: date,
            axisLabel: { // 设置纵轴标签的字体颜色
              color: "white"
            }
          },
          yAxis: {
            type: "value",
            min: 0,//设置纵坐标最小值
            max: 35,//设置纵坐标最大值
            interval:5,
            axisLabel: { // 设置纵轴标签的字体颜色
              color: "white"
            }
          },
          series: [
            {
              name: "座位使用情况",
              type: "line",
              stack: "Total",
              data: [10,15,18,12,16,32],
              itemStyle: {
                color: "yellow"
              },
              lineStyle: {
                color: "yellow" // 使用十六进制颜色代码或颜色名称
              }
            },
          ],
        };
        myChart.setOption(option);
      }).catch(function (error){
        console.log(error);
      });
    },
    //图3，双折线
    drawLine1() {
      let myChart = echarts.init(this.$refs.myChart3);
      let option; // 使用let来声明变量，避免this指向问题
      axios.get('/api/getAcExpTemp').then((response) => {
        console.log(response.data.data);
        this.responseData = response.data.data;
        //提取空调期望温度数据
        const expTemData = this.responseData.map(item => item.expTem);
        //提取空调今日温度数据
        const valueData = this.responseData.map(item => item.value);
        //提取日期
        const dateData = this.responseData.map(item => item.date);
        option = {
          title: {
            text: "空调温度和希望空调温度",
            left: 'center',
            textStyle: {
              color: "white",
              fontSize: 18,
              fontWeight: "bold",
              fontFamily: "Arial, sans-serif",
            },
          },
          tooltip: {
            trigger: "axis",
          },
          grid: {
            left: "3%",
            right: "4%",
            bottom: "3%",
            containLabel: true,
          },
          xAxis: {
            type: "category",
            boundaryGap: false,
            data: dateData,
            axisLabel: { // 设置纵轴标签的字体颜色
              color: "white"
            }
          },
          yAxis: {
            type: "value",
            boundaryGap: false,
            min: 10,//设置纵坐标最小值
            max: 30,//设置纵坐标最大值
            interval:5,
            axisLabel: { // 设置纵轴标签的字体颜色
              color: "white"
            }
          },
          series: [
            {
              name: "空调期望温度",
              type: "line",
              stack: "Total",
              data: expTemData, // 使用获取到的数据
              itemStyle: {
                color: "yellow"
              },
              lineStyle: {
                color: "yellow" // 使用十六进制颜色代码或颜色名称
              }
            },
            {
              name: "空调今日温度",
              type: "line",
              stack: "Total2",
              data: valueData,
              itemStyle: {
                color: "yellow"
              },
              lineStyle: {
                color: "yellow" // 使用十六进制颜色代码或颜色名称
              }
            },
          ],
        };
        myChart.setOption(option); // 设置图表的option
      }).catch(function (error) {
        console.log(error);
      });
    },
  },
};
</script>

<style scoped lang="scss">
.container {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 20px;
  /* 可以根据需要调整列之间的间隔 */
}

.box1 {
  // height: 450px;
  // margin-top: 3vh;
  height: 100%;
  display: flex;
  flex-direction: column;

  .column {
    flex: 1;
  }

  /* background-color: blue; */
}

.column {
  /* background-color: #583636; */
  // height: 350px;
}

.box2 {
  // margin-top: 4vh;
  // height: 60px;
  /* background-color: rgb(142, 33, 66); */
}

.el-dialog__headerbtn:hover {
  color: gray !important;
}

/* 温度按钮 */
/* 吵闹程度按钮 */
#button1,
#button2,
#button3 {
  color: gray;
  width: 100%;
  height: 100%;
}

.m-2 {
  position: absolute;
  left: 10%;
  width: 80%;
}

header {
  display: flex;
  justify-content: space-between;
  height: 5%;
  align-items: center;
  margin: 10px 0;

  h2 {
    margin-left: 10%;
    font-size: 24px;
    font-weight: 600;
    position: relative;
    left: 28vw;
  }

  section {

    &-bottom {}

  }
}

.section-chart {
  display: flex;

  >div:nth-child(2) {
    margin: 0 10px;
  }
}

.all {
  display: flex;
  flex-direction: column;
  height: 100%;
  padding: 10px;
  overflow: hidden;
  background-image: url("../../img/bg3.png");
  background-size: cover;
  background-repeat: no-repeat;

  .container {
    min-height: 30%;
  }

  .all-bottom {
    flex: 1;
    display: flex;
    height: 0;

    >div {
      flex: 1;
    }
  }
}
</style>
