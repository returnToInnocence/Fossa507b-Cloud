<template>
  <div id="back_box">
    <el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick">
      <el-tab-pane :label="`当前录卡排队人数：${x}`" name="first">
        <div class="space-y-8" id="table_space8">
          <div class="sm:rounded-lg sm:px-10" id="table1">
            <div class="space-y-6" id="table_help">
              <div>
                <p id="help_font">录卡</p>
                <table class="divide-gray-200" id="table_main">
                  <!-- 表头 -->
                  <thead class="bg-gray-50">
                  <tr>
                    <th class="px-2 py-4 text-xs">录卡人</th>
                    <th class="px-6 py- text-xs">申请时间</th>
                    <!-- <th class="px-2 py-4 text-xs">操作</th> -->
                  </tr>
                  </thead>
                </table>
                <el-scrollbar id="scrollbar">
                  <table class="divide-gray-200" id="table_center">
                    <tbody class="bg-white divide-y divide-gray-200" id="tbody_center">
                    <tr v-for="item in carditems" :key="item.id">
                      <td class="px-2 py-4" id="whitespace-nowrap">
                        <div id="td_div">{{ item.name }}</div>
                      </td>
                      <td class="px-6 py-4" id="whitespace-nowrap1">
                        <div style="position: center" id="td_div2">{{ item.date }}</div>
                      </td>
                    </tr>
                    </tbody>
                  </table>
                </el-scrollbar>
              </div>
              <div id="divf">
                <el-button text @click="dialogFormVisible = true" id="button_help">我要录卡</el-button>
                <el-button text @click="dialogFormVisible1 = true" id="button_rhelp">发布录卡</el-button>
                <!-- 录卡弹窗 -->
                <el-dialog v-model="dialogFormVisible" title="确认录卡">是否确认录卡？
                  <template #footer>
                    <span class="dialog-footer">
                      <el-button @click="dialogFormVisible = false">取消</el-button>
                      <el-button type="primary" @click="cardpush">确认</el-button>
                    </span>
                  </template>
                </el-dialog>
                <!-- 发布录卡信息弹窗 -->
                <el-dialog v-model="dialogFormVisible1" title="录卡发布信息">
                  <el-form :model="form">
                    <el-form-item label="录卡时间" :label-width="formLabelWidth">
                      <el-input v-model="form.date1" autocomplete="off"/>
                    </el-form-item>
                    <el-form-item label="录卡地点" :label-width="formLabelWidth">
                      <el-input v-model="form.address" autocomplete="off"/>
                    </el-form-item>
                  </el-form>
                  <template #footer>
                    <span class="dialog-footer">
                      <el-button @click="dialogFormVisible1 = false">取消</el-button>
                      <el-button type="primary" @click="goToPublish_card">确定</el-button>
                    </span>
                  </template>
                </el-dialog>
              </div>
            </div>
          </div>
        </div>
      </el-tab-pane>
      <el-tab-pane :label="`当前报修设备人数：${y}`" name="second">
        <div class="space-y-8" id="table_space8">
          <div class="sm:rounded-lg sm:px-10" id="table1">
            <div class="space-y-6" id="table_help">
              <div>
                <p id="help_font">报修</p>
                <table class="divide-gray-200" id="table_main">
                  <!-- 表头 -->
                  <thead class="bg-gray-50">
                  <tr>
                    <th class="px-2 py-4 text-xs">报修人</th>
                    <th class="px-6 py- text-xs">报修设备</th>
                    <th class="px-6 py- text-xs">报修时间</th>
                    <th class="px-2 py-4 text-xs">紧急程度</th>
                    <th class="px-2 py-4 text-xs">操作</th>
                  </tr>
                  </thead>
                </table>
                <el-scrollbar id="scrollbar">
                  <table class="divide-gray-200" id="table_center">
                    <tbody class="bg-white divide-y divide-gray-200" id="tbody_center">
                    <tr v-for="item in equipmentitems" :key="item.id">
                      <td class="px-2 py-4" id="whitespace-nowrap">
                        <div id="td_div">{{ item.name }}</div>
                      </td>
                      <td class="px-2 py-4" id="whitespace-nowrap">
                        <div id="td_div">{{ item.text }}</div>
                      </td>
                      <td class="px-6 py-4" id="whitespace-nowrap1">
                        <div style="position: center" id="td_div2">{{ item.time }}</div>
                      </td>
                      <td class="px-2 py-4" id="whitespace-nowrap">
                        <div id="td_div">{{ item.status }}</div>
                      </td>
                      <td class="px-2 py-4" id="td3">
                        <button @click="goToDelect_equip" id="td_div3">删除</button>
                      </td>
                    </tr>
                    </tbody>
                  </table>
                </el-scrollbar>
              </div>
              <div id="divf">
                <button @click="dialogFormVisible_fix = true;" id="button_help">我要报修</button>
                <el-dialog v-model="dialogFormVisible_fix" title="申请报修">
                  <el-form :model="form">
                    <el-form-item label="报修设备" :label-width="formLabelWidth">
                      <el-input v-model="form.equipment" autocomplete="off"/>
                    </el-form-item>
                    <el-form-item label="报修时间" :label-width="formLabelWidth">
                      <el-input v-model="form.currentTime" autocomplete="off"/>
                    </el-form-item>
                    <el-form-item label="紧急程度(0,1紧急)" :label-width="formLabelWidth">
                      <el-input v-model="form.quick" autocomplete="off"/>
                    </el-form-item>
                  </el-form>
                  <template #footer>
                    <span class="dialog-footer">
                      <el-button @click="dialogFormVisible_fix = false">取消</el-button>
                      <el-button type="primary" @click="goToPublish_equip">确认</el-button>
                    </span>
                  </template>
                </el-dialog>
              </div>
            </div>
          </div>
        </div>
      </el-tab-pane>
      <el-tab-pane :label="`当前需要留门人数：${z}`" name="third">
        <div class="space-y-8" id="table_space8">
          <div class="sm:rounded-lg sm:px-10" id="table1">
            <div class="space-y-6" id="table_help">
              <div>
                <p id="help_font">留门</p>
                <table class="divide-gray-200" id="table_main">
                  <!-- 表头 -->
                  <thead class="bg-gray-50">
                  <tr>
                    <th class="px-2 py-4 text-xs">求助人</th>
                    <th class="px-6 py- text-xs">简述</th>
                    <th class="px-2 py-4 text-xs">操作</th>
                  </tr>
                  </thead>
                </table>
                <el-scrollbar id="scrollbar">
                  <table class="divide-gray-200" id="table_center">
                    <tbody class="bg-white divide-y divide-gray-200" id="tbody_center">
                    <tr v-for="item in dooritems" :key="item.id">
                      <td class="px-2 py-4" id="whitespace-nowrap">
                        <div id="td_div">{{ item.name }}</div>
                      </td>
                      <td class="px-6 py-4" id="whitespace-nowrap1">
                        <div style="position: center" id="td_div2">需要留门</div>
                      </td>
                      <td class="px-2 py-4" id="td3">
                        <button @click="goToDelect_door" id="td_div3">删除</button>
                      </td>
                    </tr>
                    </tbody>
                  </table>
                </el-scrollbar>
              </div>
              <div id="divf">
                <el-button text @click="dialogFormVisible_door = true" id="button_help">留门</el-button>
                <!-- 录卡弹窗 -->
                <el-dialog v-model="dialogFormVisible_door" title="确认留门">是否确认留门？
                  <template #footer>
                    <span class="dialog-footer">
                      <el-button @click="dialogFormVisible_door = false">取消</el-button>
                      <el-button type="primary" @click="doorpush">确认</el-button>
                    </span>
                  </template>
                </el-dialog>
              </div>
            </div>
          </div>
        </div>
      </el-tab-pane>
      <el-tab-pane :label="`求助帖子：${a}`" name="fourth">
        <div class="space-y-8" id="table_space8">
          <div class="sm:rounded-lg sm:px-10" id="table1">
            <div class="space-y-6" id="table_help">
              <div>
                <p id="help_font">求助列表</p>
                <table class="divide-gray-200" id="table_main">
                  <!-- 表头 -->
                  <thead class="bg-gray-50">
                  <!-- 表列 -->
                  <tr>
                    <th class="px-2 py-4 text-xs">求助人</th>
                    <th class="px-6 py- text-xs">求助简述</th>
                    <th class="px-2 py-4 text-xs">操作</th>
                  </tr>
                  </thead>
                </table>
                <el-scrollbar id="scrollbar">
                  <table class="divide-gray-200" id="table_center">
                    <tbody class="bg-white divide-y divide-gray-200" id="tbody_center">
                    <tr v-for="item in helpitems" :key="item.id">
                      <td class="px-2 py-4" id="whitespace-nowrap">
                        <div id="td_div">{{ item.name }}</div>
                      </td>
                      <td class="px-6 py-4" id="whitespace-nowrap1">
                        <div style="position: center" id="td_div2">
                          {{ item.description }}
                        </div>
                      </td>
                      <td class="px-2 py-4" id="td3">
                        <button @click="goToDetail(item.id,item.name,item.description,item.date)" id="td_div3">
                          查看详情
                        </button>
                      </td>
                    </tr>
                    </tbody>
                  </table>
                </el-scrollbar>
              </div>

              <div id="divf">
                <!-- <button @click="goToPublish" id="button_help">我要求助</button> -->
                <el-button text @click="dialogFormVisible_help = true" id="button_help">
                  我要求助
                </el-button>

                <el-dialog v-model="dialogFormVisible_help" title="发布求助">
                  <el-form :model="form">
                    <el-form-item label="姓名" :label-width="formLabelWidth">
                      <el-input v-model="form.username" autocomplete="off"/>
                    </el-form-item>
                    <el-form-item label="学号" :label-width="formLabelWidth">
                      <el-input v-model="form.userid" autocomplete="off"/>
                    </el-form-item>
                    <el-form-item label="系统当前时间" :label-width="formLabelWidth">
                      <el-input v-model="form.currentTime" autocomplete="off"/>
                    </el-form-item>
                    <el-form-item label="求助简述" :label-width="formLabelWidth">
                      <el-input v-model="form.description" autocomplete="off"/>
                    </el-form-item>
                  </el-form>
                  <template #footer>
                    <span class="dialog-footer">
                      <el-button @click="dialogFormVisible_help = false">取消</el-button>
                      <el-button type="primary" @click="pushhelp()">确认</el-button>
                    </span>
                  </template>
                </el-dialog>
              </div>
            </div>
          </div>
        </div>
      </el-tab-pane>
    </el-tabs>
    <!-- 求助列表 -->
    <!-- 录卡功能弹窗 -->
    <!-- 弹窗容器 -->
  </div>
</template>

<script>
import axios from "axios";
import {reactive, onMounted, ref} from "vue";
import {useRouter} from "vue-router";
import {ElScrollbar} from "element-plus";
import Cookies from 'js-cookie';
export default {
  components: {
    ElScrollbar,
  },
  setup() {
    const userId = Cookies.get('userid');
    const getCurrentTime = () => {
      //获取系统当前时间函数
      const now = new Date();
      const year = now.getFullYear();
      const month = String(now.getMonth() + 1).padStart(2, "0");
      const day = String(now.getDate()).padStart(2, "0");
      const hours = String(now.getHours()).padStart(2, "0");
      const minutes = String(now.getMinutes()).padStart(2, "0");
      const seconds = String(now.getSeconds()).padStart(2, "0");
      return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
    }
    //录卡弹窗参数
    const dialogFormVisible = ref(false);
    const dialogFormVisible1 = ref(false);
    const dialogFormVisible_door = ref(false);
    const dialogFormVisible_help = ref(false);
    const dialogFormVisible_fix = ref(false);
    const formLabelWidth = "140px";
    const form = reactive({
      username: "",
      userid: userId,//cookie中的值
      description: "",
      currentTime: getCurrentTime(),
      equipment: "",
      quick: "",
      date1: "",
      address: "",
      date2: "",
      delivery: false,
      type: [],
    });
    //数据区
    const activeName = ref("first");

    const carditems = reactive([]);//录卡信息
    const equipmentitems =  reactive([]);//设备报修信息
    const dooritems =  reactive([]);//留门信息
    const helpitems = reactive([]);//求助帖子信息

    const x = ref(0);
    const y = ref(0);
    const z = ref(0);
    const a = ref(0);
    const router = useRouter();
    // 在组件加载时调用fetchData方法，获取数据
    const fetchData = async () => {
      try {
        const cardList = await axios.get("/api/cardqueue");
        const helpList = await axios.get("/api/helpList");
        const equipmentList = await axios.get("/api/equipmentqueue");
        const doorList = await axios.get("/api/doorqueue")
        console.log("helpList:",helpList);
        console.log("cardList:",cardList);
        console.log("equipmentList:",equipmentList.data);
        console.log("doorList:",doorList);
        //获取到录卡信息并展示到前端
        const CardItems = cardList.data.map((item) =>({
          id: item.userid,
          date: item.date,
          name: item.username,
        }))
        carditems.splice(0, CardItems.length, ...CardItems);
        x.value = CardItems.length;

        //获取到报修设备信息并展示到前端
        const EquipmentItems = equipmentList.data.map((item) =>({
          id: item.userid,
          name: item.username,
          text: item.text,
          time: item.time,
          status: item.status,
        }))
        equipmentitems.splice(0, EquipmentItems.length, ...EquipmentItems);
        y.value = EquipmentItems.length;

        //获取到留门信息并展示到前端
        const DoorItems = doorList.data.map((item) =>({
          id: item.cardqueue,
          name: item.username
        }))
        dooritems.splice(0, DoorItems.length, ...DoorItems)
        z.value = DoorItems.length;

        //获取到求助信息并展示到前端
        const HelpItems = helpList.data.data.map((item) => ({
          name: item.username,
          id: item.userid,
          description: item.content,
          date: item.time,
        }));
        helpitems.splice(0, helpitems.length, ...HelpItems);
        a.value = HelpItems.length;

      } catch (error) {
        console.error(error);
      }
    };
    onMounted(() => {
      fetchData();
    });
    const handleClick = (tab, event) => {
      console.log(tab, event);
    };

    //录卡后端逻辑(已完成)
    const cardpush = () =>{
      //点击确认录卡写请求
      //从Cookie中获取当前用户的id，写入到录卡人中,获取系统当前时间，写入到时间中
      const cardData = {
        userid: userId,
        currentTime: getCurrentTime()
      };
      //使用Axios发送post请求
      axios.post("/api/incardqueue",cardData)
          .then((response) =>{
            console.log(response.data.message);
          }).catch((error) =>{
            console.log(error);
      });
      dialogFormVisible.value = false;
    };
    const goToPublish_card = () =>{
      //发布录卡 点击确定
      console.log(form.date1);
      console.log(form.address);
      const cardData = {
        id: userId,
        date: form.date1,
        address: form.address
      };
      //使用Axios发送post请求
      axios.post("/api/delcardqueue",cardData)
          .then((response) =>{
            console.log(response.data.message);
          }).catch((error) =>{
        console.log(error);
      });
      dialogFormVisible1.value = false;
    };
    //设备报修后端逻辑(已完成)
    const goToPublish_equip =() =>{
      //点击我要报修的确认按钮 后端写入请求
      const fixData={
        userid: userId,
        equipment: form.equipment,
        currentTime: getCurrentTime(),
        quick: form.quick
      };
      //使用Axios发送POST请求
      axios.post("/api/inequipment",fixData).then((response)=>{
        console.log(response);
        alert("提交成功");
      }).catch((error) =>{
        console.log(error);
        alert("提交失败");
      });
      dialogFormVisible_fix.value = false;
    };
    const goToDelect_equip = () =>{
      //使用Axios发送POST请求
      const delData={
        id: userId
      };
      axios.post("/api/delequipment",delData).then((response)=>{
        console.log(response);
        alert("删除成功");
      }).catch((error) =>{
        console.log(error);
        alert("删除失败");
      });
    }

    //留门后端逻辑(已完成)
    const doorpush =() =>{
      //使用Axios发送POST请求
      const doorData={
        id: userId
      };
      axios.post("/api/indoorqueue",doorData).then((response)=>{
        console.log(response);
        alert("添加成功");
      }).catch((error) =>{
        console.log(error);
        alert("添加失败");
      });
      dialogFormVisible_door.value = false;
    }
    const goToDelect_door = () =>{
      //使用Axios发送POST请求
      const delData={
        id: userId
      };
      axios.post("/api/deldoorqueue",delData).then((response)=>{
        console.log(response.data.message);
        if(response.data.message = "取消帮留门信息失败"){
          alert("删除失败,你不能删除其他人的留门信息");
        }else{
          alert("删除成功");
        }
      }).catch((error) =>{
        console.log(error);
        alert("接口异常");
      });
    }
    //求助帖子后端逻辑(已完成)（接取求助的后端代码在Detail.vue）
    const pushhelp = () => {
      const formData = {
        username: form.username,
        userid: form.userid,
        currentTime: getCurrentTime(),
        description: form.description
      };
      //使用Axios发送POST请求
      axios.post("/api/inhelp", formData).then((response) => {
        console.log(response);
        alert("提交成功");
      }).catch((error) => {
        console.error(error);
        alert("提交失败");
      });
      dialogFormVisible_help.value = false;
    };
    const goToDetail = (id, name, description, date) => {
      router.push({
        name: "datasource-detail",
        params: {id, name, description, date},
      });
    };

    return {
      x, y, z, a,
      helpitems,
      carditems,//录卡信息
      equipmentitems,//设备报修信息
      dooritems,//留门信息
      cardpush,
      doorpush,
      goToPublish_card,
      goToDetail,
      goToPublish_equip,
      goToDelect_equip,
      goToDelect_door,
      handleClick,
      pushhelp,
      activeName,
      dialogFormVisible,
      dialogFormVisible1,
      dialogFormVisible_door,
      dialogFormVisible_help,
      dialogFormVisible_fix,
      formLabelWidth,
      form,
    };
  },
};
</script>

<style scoped>
/* xiugai */
.dialog-footer button:first-child {
  margin-right: 10px;
}

.demo-tabs .el-tabs__item {
  color: white;
}

.el-button--text {
  margin-right: 15px;
}

.el-select {
  width: 300px;
}

.el-input {
  width: 300px;
}

.dialog-footer button:first-child {
  margin-right: 10px;
}

/* jieshu */
.demo-tabs > .el-tabs__content {
  padding: 32px;
  color: #6b778c;
  font-size: 32px;
  font-weight: 600;
}

#request_card {
  background-color: #ffffff;
  width: 20%;
  height: 10vh;
  position: absolute;
  top: 20vh;
  left: 20vw;
  font-size: larger;
  font-family: "楷体";
  font-weight: bolder;
  text-align: center;
  display: flex;
  align-items: center;
  justify-content: center;
  border: 1vh double #3295eb;
  border-radius: 3vh;
}

#request_fix {
  background-color: #ffffff;
  width: 20%;
  height: 10vh;
  position: absolute;
  top: 20vh;
  left: 45vw;
  font-size: larger;
  font-family: "楷体";
  font-weight: bolder;
  text-align: center;
  display: flex;
  align-items: center;
  justify-content: center;
  border: 1vh double #3295eb;
  border-radius: 3vh;
}

#request_door {
  background-color: #ffffff;
  width: 20%;
  height: 10vh;
  position: absolute;
  top: 20vh;
  left: 70vw;
  font-size: larger;
  font-family: "楷体";
  font-weight: bolder;
  text-align: center;
  display: flex;
  align-items: center;
  justify-content: center;
  border: 1vh double #3295eb;
  border-radius: 3vh;
}

/* 求助列表文字 */
#back_box {
  padding-left: 1rem;
  padding-right: 1rem;
  justify-content: center;
  align-items: center;
  display: flex;
  height: 100%;
}

#help_font {
  color: grey;
  font-weight: lighter;
  font-size: 1rem;
  text-align: center;
  margin-top: 0rem;
}

#table_space8 {
  margin-top: 3vh;
  max-width: 31.5vw;
  width: 100%;
  margin-left: 0vh;
}

#table1 {
  box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1),
  0 2px 4px -1px rgba(0, 0, 0, 0.06);
  padding-left: 1rem;
  padding-right: 1rem;
  background-color: #ffffff;
  width: 100%;
}

#table_main {
  border-bottom-width: 1px;
  border-color: rgba(0, 0, 0, 0.06);
  min-width: 100%;
}

.text-xs {
  font-size: 0.75rem;
  font-size: 5px;
  color: #6b7280;
  text-transform: uppercase;
  letter-spacing: 0.05em;
  text-align: left;
}

#scrollbar {
  flex: 1;
  overflow: auto;
  height: 200px;
  width: auto;
  overflow: auto;
}

#table_center {
  border-bottom-width: 1px;
  border-color: rgba(0, 0, 0, 0.06);
  min-width: 100%;
}

#tbody_center {
  border-bottom-width: 1px;
  border-color: rgba(0, 0, 0, 0.06);
  background-color: #ffffff;
}

#whitespace-nowrap,
#whitespace-nowrap1 {
  white-space: nowrap;
}

#td_div {
  text-align: left;
  color: #111827;
  font-size: 5px;
  font-size: 0.875rem;
}

#td_div2 {
  text-align: center;
  color: #6b7280;
  font-size: 0.875rem;
}

#td3 {
  text-align: center;
  white-space: nowrap;
}

#td_div3 {
  border-radius: 0.375rem;
  background-color: #3385f8;
  background-image: linear-gradient(45deg, #7af7c3 0%, #48dbdd 100%);
  color: #ffffff;
  border-radius: 0.175rem;
  padding: 0.45rem 0.5rem;
  transition: background-color 0.3s ease, transform 0.3s ease;
}

#td_div3:hover {
  background-color: #45a6cc;
  transform: scale(1.05);
  letter-spacing: 0.05em;
  font-size: 5px;
  color: #3295eb;
}

#divf {
  justify-content: center;
  display: flex;
  margin-top: 0rem;
}

#button_help {
  border-radius: 0.375rem;
  font-size: 5px;
  color: #ffffff;
  background-color: #4f46e5;
  padding-top: 0.5rem;
  padding-bottom: 0.5rem;
  padding-left: 1rem;
  padding-right: 1rem;
}

#button_rhelp {
  border-radius: 0.375rem;
  font-size: 5px;
  color: #ffffff;
  background-color: #4f46e5;
  padding-top: 0.5rem;
  padding-bottom: 0.5rem;
  padding-left: 1rem;
  padding-right: 1rem;
}

.sm\:px-6 {
  padding-left: 1.5rem;
  padding-right: 1.5rem;
}

.lg\:px-8 {
  padding-left: 2rem;
  padding-right: 2rem;
}

.space-y-8 > :not([hidden]) ~ :not([hidden]) {
  /* 不可以 */
  margin-top: 2rem;
}

.sm\:rounded-lg {
  border-radius: 0.375rem;
}

.space-y-6 > :not([hidden]) ~ :not([hidden]) {
  /* 不可 */
  margin-top: 1rem;
}
</style>
