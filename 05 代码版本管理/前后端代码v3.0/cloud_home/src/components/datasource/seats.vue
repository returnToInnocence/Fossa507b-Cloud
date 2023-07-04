<template>
  <div class="seat-plan">
    <h2>座位选择</h2>
    <div class="seat-container">
      <div
        v-for="(row, rowIndex) in seatRows"
        :key="rowIndex"
        class="row"
      >
        <div
          v-for="(seat, seatIndex) in row"
          :key="seatIndex"
          class="seat"
          :class="[{'seat-selected': seat.selected}, {'seat-disabled': seat.owner || isEditing}]"
          @click="isEditing ? null :selectSeat(rowIndex, seatIndex)"
        >
          {{ seat.owner ? seat.owner : ''}}
        </div>
      </div>
    </div>
    <div v-if="selectedSeat">
      <input type="text" v-model="selectedSeat.owner" placeholder="输入姓名" />
    </div>
    <el-button type="primary" round @click="confirmEdit">确定</el-button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      seatRows: [], // 座位布局
      selectedSeat: null, // 当前选中的座位
      isEditing: false, // 是否正在编辑座位名称
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
            return { owner: '', selected: false,userId:''};
          });
        });
      }
    },
    selectSeat(row, seat) {
      if (this.seatRows[row][seat].owner) {
        //css{'seat-selected': }, {'seat-disabled': 
        // return; // 如果座位已经有人，则无法继续选择
      }
      this.seatRows[row][seat].selected = !this.seatRows[row][seat].selected;
      this.selectedSeat = this.seatRows[row][seat].selected
        ? this.seatRows[row][seat]
        : null;
        // if(this.seatRows[row][seat] !=null){this.isEditing = true;}
        this.isEditing = true;
         // 标记正在编辑座位名称
      this.saveSeatData(); // 保存座位数据到本地存储
    },
    saveSeatData() {
      localStorage.setItem('seatData', JSON.stringify(this.seatRows));
    },
    confirmEdit() {
    this.isEditing = false; // 结束编辑座位名称
    // 根据需求添加其他逻辑，比如保存数据等
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
h2{
  display: flex;
  justify-content: center;
  align-items: center; /* 添加此行 */
  background-color: rgb(106, 106, 247);
  border-radius: 5vh;
  font-size: 25px;
  font-family: '楷体';
  width: 25%;
  margin: 2vh auto;
  height: 8vh;
  margin-bottom: 5vh;
}
.seat-container {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.row {
  display: flex;
  justify-content: center;
  margin-bottom: 15px;
}

.seat {
  width: 80px;
  height: 50px;
  background-color: #ffffff;
  border: 0.2vh solid blue;
  color: #fff;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-right: 15px;
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
