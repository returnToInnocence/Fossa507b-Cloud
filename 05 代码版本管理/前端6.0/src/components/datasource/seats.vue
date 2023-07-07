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
    <div v-if="canInput">
      <input type="text" v-model="selectedSeat.owner" placeholder="输入姓名" />
    </div>
    <el-button type="primary" round @click="confirmEdit()">确定</el-button>
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
      axios.get('http://localhost:8080/api/getseat').then(function (response) {
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
      axios.post('http://localhost:8080/api/updateseat', {
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
  align-items: center;

  /* 添加此行 */
  background-color: rgb(106, 106, 247);
  border-radius: 5vh;
  font-size: 25px;
  font-family: '楷体';
  width: 100%;
  text-align: center;
  /* margin: 10px 0 auto; */
  /* margin: 2vh auto;
  height: 8vh;
  margin-bottom: 5vh; */
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
  border: 0.2vh solid rgb(255, 255, 255);
  color: #000000;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-right: 15px;
  cursor: pointer;
}

.seat-selected {
  background-color: rgb(254, 255, 165);
  cursor: default;
}

.seat-disabled {
  opacity: 0.5;
  cursor: not-allowed;
}
</style>
