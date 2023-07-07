<template>
  <div class="seat-plan">
    <h2>座位选择</h2>
    <div class="seat-container">
      <div v-for="(row, rowIndex) in seatRows" :key="rowIndex" class="row">
        <div v-for="(seat, seatIndex) in row" :key="seatIndex" class="seat"
          :class="[{ 'seat-selected': seat.selected }, { 'seat-disabled': seat.owner || isEditing }]"
          @click="isEditing ? null : selectSeat(rowIndex, seatIndex)">
          {{ seat.owner }}
        </div>
      </div>
    </div>
    <div style="text-align: center">
      <span v-if="canInput">
        <input type="text" style="height: 32px;" v-model="selectedSeat.owner" placeholder="输入姓名" />
      </span>
      <el-button type="primary" round @click="confirmEdit()">确定</el-button>
    </div>
  </div>
</template>
<script>
import axios from 'axios';
export default {
  data() {
    return {
      seatRows: [], // 座位布局
      selectedSeat: null, // 当前选中的座位
      isEditing: false, // 是否正在编辑座位名称
      nowrowIndex: 0,
      nowseatIndex: 0,
      canInput: false
    };
  },
  mounted() {
    // this.retrieveSeatData(); // 每次加载页面时初始化座位数据
    this.generateSeatPlan();
  },
  methods: {
    generateSeatPlan() {
      const totalRows = 5;
      const seatsPerRow = 7;
      // 先生成空数组
      // this.seatRows = Array.from({ length: totalRows }, () => {
      //   return Array.from({ length: seatsPerRow }, () => {
      //     return { owner: '', selected: false, userId: '' };
      //   });
      // });
      const vm = this;
      axios.get('/api/getseat').then(function (response) {
        // console.log(response);
        vm.seatRows = response.data.data;
        // console.log("下面是我发布的")
        // console.log(typeof vm.seatRows);
        //下面做数据处理
        for (let i = 0; i < vm.seatRows.length; i++) {
          for (let j = 0; j < vm.seatRows[i].length; j++) {
            const element = vm.seatRows[i][j];
            // 代表有人
            if (vm.seatRows[i][j].seatstatus == '1') {
              vm.seatRows[i][j].selected = true;
              vm.seatRows[i][j].owner = vm.seatRows[i][j].seatuserguest;
            } else if (vm.seatRows[i][j].seatstatus == '0') {
              vm.seatRows[i][j].selected = false;
              vm.seatRows[i][j].owner = vm.seatRows[i][j].seatuserhost;
            }
          }
        }
        // console.log(vm.seatRows[0][0].owner);
      }).catch(function (error) {
        console.log(error);
      });

      // this.seatRows = Array.from({ length: totalRows }, () => {
      //   return Array.from({ length: seatsPerRow }, () => {
      //     return { owner: '', selected: false, userId: '' };
      //   });
      // });
    },
    selectSeat(row, seat) {
      if (this.seatRows[row][seat].owner) {
        //css{'seat-selected': }, {'seat-disabled': 
        // return; // 如果座位已经有人，则无法继续选择
      }
      this.seatRows[row][seat].selected = !this.seatRows[row][seat].selected;
      // this.selectedSeat = this.seatRows[row][seat].selected
      //   ? this.seatRows[row][seat]
      //   : null;
      if (this.seatRows[row][seat].selected == true) {
        this.selectedSeat = this.seatRows[row][seat];
        this.selectedSeat.seatstatus = "1";

        this.canInput = true
        console.log("点开了")
        console.log(this.selectedSeat)
      } else {
        this.selectedSeat = this.seatRows[row][seat];
        this.selectedSeat.selected = this.seatRows[row][seat].selected;
        this.selectedSeat.owner = this.seatRows[row][seat].seatuserhost;
        this.selectedSeat.seatstatus = "0";
        this.selectedSeat.seatuserguest = "";
        this.canInput = false;
        console.log("关闭了")
        console.log(this.selectedSeat)
      }

      // if(this.seatRows[row][seat] !=null){this.isEditing = true;}
      this.isEditing = true;
      // 标记正在编辑座位名称
      // this.saveSeatData(); // 保存座位数据到本地存储
      this.nowrowIndex = row;
      this.nowseatIndex = seat;


      // console.log("当前正在调试的")
      // console.log(this.seatRows[row][seat].seatid);
      // console.log(this.seatRows[row][seat].owner);
      // console.log(this.seatRows[row][seat].selected);
    },
    // saveSeatData() {
    //   localStorage.setItem('seatData', JSON.stringify(this.seatRows));
    // },
    confirmEdit() {
      this.isEditing = false; // 结束编辑座位名称
      // 根据需求添加其他逻辑，比如保存数据等
      // console.log("当前正在调试的")
      // console.log(this.seatRows[this.nowrowIndex][this.nowseatIndex].seatid);
      // console.log(this.seatRows[this.nowrowIndex][this.nowseatIndex].owner);
      // console.log(this.seatRows[this.nowrowIndex][this.nowseatIndex].selected);
      this.selectedSeat.seatuserguest = this.seatRows[this.nowrowIndex][this.nowseatIndex].owner;
      const vm = this;
      console.log(this.selectedSeat)
      axios.post('/api/updateseat', {
        seatid: vm.selectedSeat.seatid,
        seatstatus: vm.selectedSeat.seatstatus,
        seatuserguest: vm.selectedSeat.seatuserguest
      })
        .then(response => {
          // 请求成功时的处理
          //刷新页面功能
          window.location.reload();
          console.log(response.data);
        })
        .catch(error => {
          // 请求失败时的处理
          console.error(error);
        });

    },
    // retrieveSeatData() {
    //   const savedData = localStorage.getItem('seatData');
    //   if (savedData) {
    //     this.seatRows = JSON.parse(savedData);
    //   }
    // },
  },
};
</script>

<style scoped>
.seat-plan {
  text-align: center;
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

h2 {
  display: flex;
  justify-content: center;
  background-color: rgb(106, 106, 247);
  border-radius: 5vh;
  font-size: 25px;
  font-family: '楷体';
  width: 52%;
  padding: 10px;
  margin: 2vh auto;
  color: #ffffff;
  text-align: center;
}

.seat-container {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.el-button.confirm-button {
  background-color: #6066f7;
  color: #fff;
  border-radius: 5px;
}

.input-container {
  margin-top: 20px;
}

.input-group {
  display: flex;
  justify-content: center;
  align-items: center;
}

input[type="text"] {
  width: 200px;
  height: 30px;
  padding: 5px;
  border: 1px solid #ccc;
  border-radius: 5px;
  font-size: 14px;
}

.el-button {
  margin-left: 10px;
  background-color: rgb(106, 106, 247);
  border-color: rgb(106, 106, 247);
  color: #ffffff;
  font-size: 14px;
  padding: 8px 20px;
  border-radius: 5px;
}

.el-button:hover {
  background-color: rgb(96, 96, 227);
  border-color: rgb(96, 96, 227);
}

input[type="text"]::placeholder {
  color: #999;
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
  border: 0.2vh solid rgb(255, 255, 255);
  color: #000000;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-right: 15px;
  cursor: pointer;
  border-radius: 5px;
}

.seat-selected {
  background-color: rgb(254, 255, 165);
  cursor: default;
  transition: background-color 0.3s ease;
}
</style>
