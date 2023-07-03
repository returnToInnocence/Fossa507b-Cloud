<template>
  <div class="seat-plan">
    <h2>座位选择</h2>
    <div class="seat-container">
      <div v-for="(row, rowIndex) in seatRows" :key="rowIndex" class="row">
        <div v-for="(seat, seatIndex) in row" :key="seatIndex" class="seat"
          :class="[{ 'seat-selected': seat.selected }, { 'seat-disabled': seat.owner }]"
          @click="selectSeat(rowIndex, seatIndex)">
          {{ seat.owner ? seat.owner : '空' }}
        </div>
      </div>
    </div>
    <div v-if="selectedSeat">
      <input type="text" v-model="selectedSeat.owner" placeholder="输入姓名" />
    </div>
  </div>
</template>

<script>
import axios from 'axios'
axios.defaults.baseURL = "http://127.0.0.1:8000/"; // 接口ip:端口号
export default {
  data() {
    return {
      seatRows: [], // 座位布局
      selectedSeat: null, // 当前选中的座位
    };
  },
  mounted() {
    this.retrieveSeatData(); // 每次加载页面时初始化座位数据
    this.generateSeatPlan();
  },
  methods: {
    generateSeatPlan() {
      const totalRows = 5;
      const seatsPerRow = 7;
      if (this.seatRows.length === 0) { // 如果没有保存的座位数据，则生成新的座位布局
        this.seatRows = Array.from({ length: totalRows }, () => {
          return Array.from({ length: seatsPerRow }, () => {
            return { owner: '', selected: false };
          });
        });
      }
    },
    // 这里是座位的点击响应事件
    selectSeat(row, seat) {
      this.seatRows[row][seat].selected = !this.seatRows[row][seat].selected;
      this.selectedSeat = this.seatRows[row][seat].selected
        ? this.seatRows[row][seat]
        : null;
      this.saveSeatData(); // 保存座位数据到本地存储
      console.log("helo!");

      //这里尝试网络请求发送
      // axios.get('/api/user/list-user/').then((response) => {
      //   console.log(response.data)
      // }).catch((error) => {
      //   console.log(error)
      // });

      axios.post(
        '/api/user/create-user/',
        {
          "seatRows": this.seatRows
        }
      ).then((response) => {
        console.log(response.data)
      }).catch((error) => {
        console.log(error.response.status)
      });
    },
    saveSeatData() {
      localStorage.setItem('seatData', JSON.stringify(this.seatRows));
    },
    retrieveSeatData() {
      const savedData = localStorage.getItem('seatData');
      if (savedData) {
        this.seatRows = JSON.parse(savedData);
      }
    },
  },
};
</script>

<style>
.seat-plan {
  text-align: center;
}

.seat-container {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.row {
  display: flex;
  justify-content: center;
  margin-bottom: 10px;
}

.seat {
  width: 70px;
  height: 50px;
  background-color: #ccc;
  color: #fff;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-right: 10px;
  cursor: pointer;
}

.seat-selected {
  background-color: gray;
  cursor: default;
}

.seat-disabled {
  opacity: 0.5;
  cursor: not-allowed;
}
</style>
