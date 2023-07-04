
<template>
  <div class="container">
    <div class="box1">
      <div id="chart1" ref="myChart2" class="column"></div>
    </div>
    <div class="box1">
      <div id="chart2" class="column" ref="myChart1"></div>
      <div class="box2">
        <el-button text @click="dialogVisible = true">吵闹程度投票！</el-button>
        <el-dialog v-model="dialogVisible" title="Tips" width="30%" draggable>
          <span>请选择吵闹程度</span>
          <template #footer>
            <span class="dialog-footer">
              <el-button @click="dialogVisible = false">Cancel</el-button>
              <el-button type="primary" @click="dialogVisible = false">
                Confirm
              </el-button>
            </span>
          </template>
        </el-dialog>
      </div>
    </div>
    <div class="box1">
      <div id="chart3" class="column" ref="myChart3"></div>
      <div class="box2">
        <el-button text @click="open()" id="button2">希望温度反馈</el-button>
      </div>
    </div>
  </div>
</template>
<script>
import * as echarts from "echarts";
import { ref } from "vue";
import { ElMessage, ElMessageBox } from "element-plus";
const dialogVisible = ref(false);
export default {
  // name: "datapic",
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

    drawZhu() {
      let myChart = echarts.init(this.$refs.myChart2);
      var option;
      option = {
        title: {
          text: "座位使用情况",
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
            data: [
              "2021-11",
              "2021-12",
              "2022-1",
              "2022-2",
              "2022-3",
              "2022-4",
              "2022-5",
              "2022-6",
              "2022-7",
              "2022-8",
              "2022-9",
              "2022-10",
            ],
          },
        ],
        yAxis: [
          {
            type: "value",
          },
        ],
        series: [
          {
            name: "食品",
            type: "bar",
            stack: "Ad",
            emphasis: {
              focus: "series",
            },
            data: [320, 332, 301, 334, 390, 330, 320, 301, 334, 390, 330, 320],
          },
          {
            name: "日用百货",
            type: "bar",
            stack: "Ad",
            emphasis: {
              focus: "series",
            },
            data: [120, 132, 101, 134, 90, 230, 210, 101, 134, 90, 230, 210],
          },
          {
            name: "果蔬",
            type: "bar",
            stack: "Ad",
            emphasis: {
              focus: "series",
            },
            data: [220, 182, 191, 234, 290, 330, 310, 191, 234, 290, 330, 310],
          },
        ],
      };
      option && myChart.setOption(option);
    },
  },
};

</script>
  
  
<!-- 
  <template>
    <div class="container">
      <div class="box1">
        <div id="chart1" ref="myChart2" class="column"></div>
      </div>
      <div class="box1">
        <div id="chart2" class="column" ref="myChart1"></div>
        <div class="box2">
          <el-button text @click="dialogVisible = true">
            吵闹程度投票！
          </el-button>
          <el-dialog v-model="dialogVisible" title="Tips" width="30%" draggable>
            <span>请选择吵闹程度</span>
            <template #footer>
              <span class="dialog-footer">
                <el-button @click="dialogVisible = false">Cancel</el-button>
                <el-button type="primary" @click="dialogVisible = false">
                  Confirm
                </el-button>
              </span>
            </template>
          </el-dialog>
        </div>
      </div>
      <div class="box1">
        <div id="chart3" class="column" ref="myChart3"></div>
        <div class="box2">
          <el-button text @click="open()" id="button2">希望温度反馈</el-button>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import * as echarts from "echarts";
  import { ref } from "vue";
  import { ElMessage, ElMessageBox } from "element-plus";
  const dialogVisible = ref(false);
    export default{

    }
  </script>
  
  <style>
  .container {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    gap: 20px; /* 可以根据需要调整列之间的间隔 */
  }
  .box1 {
    height: 450px;
    background-color: blue;
  }
  .column {
    background-color: #583636;
    height: 350px;
  }
  .box2 {
    margin-top: 2vh;
    height: 60px;
    background-color: rgb(142, 33, 66);
  }
  #button2 {
    width: 100%;
    height: 100%;
  }
  </style> -->
  
<!-- 
  <script lang="ts" setup>
import * as echarts from "echarts";
import { ref } from "vue";
import { ElMessage, ElMessageBox } from "element-plus";
import { onMounted } from "vue";
const dialogVisible = ref(false);
function open(){
  console.log("heloowooo");
    ElMessageBox.prompt("请输入你期望的空调温度", "Tip", {
      confirmButtonText: "确认",
      cancelButtonText: "取消",
      inputPattern:/^\d+$/,
      inputErrorMessage: "不是温度值",
    })
      .then(({ value }) => {
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
}
function drawZhu() {
        let myChart = echarts.init(this.$refs.myChart2);
        var option;
        option = {
          title: {
            text: "座位使用情况",
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
              data: [
                "2021-11",
                "2021-12",
                "2022-1",
                "2022-2",
                "2022-3",
                "2022-4",
                "2022-5",
                "2022-6",
                "2022-7",
                "2022-8",
                "2022-9",
                "2022-10",
              ],
            },
          ],
          yAxis: [
            {
              type: "value",
            },
          ],
          series: [
            {
              name: "食品",
              type: "bar",
              stack: "Ad",
              emphasis: {
                focus: "series",
              },
              data: [320, 332, 301, 334, 390, 330, 320, 301, 334, 390, 330, 320],
            },
            {
              name: "日用百货",
              type: "bar",
              stack: "Ad",
              emphasis: {
                focus: "series",
              },
              data: [120, 132, 101, 134, 90, 230, 210, 101, 134, 90, 230, 210],
            },
            {
              name: "果蔬",
              type: "bar",
              stack: "Ad",
              emphasis: {
                focus: "series",
              },
              data: [220, 182, 191, 234, 290, 330, 310, 191, 234, 290, 330, 310],
            },
          ],
        };
        option && myChart.setOption(option);
      }
  onMounted(()=>{
    // drawLine();
    drawZhu();
    // drawLine1();
  })
</script> -->