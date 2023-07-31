<template>
  <el-container class="common_layout">
    <el-container style="height: auto;overflow: hidden;">
      <el-header style="padding: 0">
        <div id="logo">
          <img src="../img/logo.png" alt="" id="img1" />
          <span id="title">507b云上小窝</span>
        </div>
        <div class="flex flex-wrap items-center">
          <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"/>
          <div class="userid">
            <el-dropdown  class="el-dropdown-hover">
              <span class="el-dropdown-link">
                {{username}}<el-icon class="el-icon--right"><arrow-down /></el-icon>
              </span>
              <template #dropdown>
                <el-dropdown-menu>
                  <el-dropdown-item :icon="Plus" @click="$router.push('/')">退出</el-dropdown-item>
                </el-dropdown-menu>
              </template>
            </el-dropdown>
          </div>
        </div>
      </el-header>
      <el-container style="overflow: hidden;">
        <el-aside width="200px">
          <el-menu active-text-color="#409eff" background-color="#545c64" class="el-menu-vertical-demo" default-active="2"
                   text-color="#fff" @open="handleOpen" @close="handleClose">
            <el-menu-item index="1" @click="router.push('/index/resourceview')">
              <template #title>
                <el-icon>
                  <location />
                </el-icon>
                <span>展板展示</span>
              </template>
            </el-menu-item>
            <!-- <el-menu-item index="2"  @click="router.push('/index/cards')"> <template #title>
                <el-icon>
                  <icon-menu />
                </el-icon>
                <span>公共区</span>
              </template>
            </el-menu-item> -->
            <el-menu-item index="2"  @click="router.push('/index/homepost')"> <template #title>
              <el-icon>
                <location />
              </el-icon>
              <span>小窝帖子</span>
            </template>
            </el-menu-item>
            <el-menu-item index="3"  @click="router.push('/index/control')"> <template #title>
              <el-icon>
                <icon-menu />
              </el-icon>
              <span>用户控制</span>
            </template>
            </el-menu-item>
          </el-menu>
        </el-aside>
        <el-main>
          <router-view v-slot="{ Component }">
            <transition name="el-fade-in-linear">
              <component :is="Component" />
            </transition>
          </router-view>
        </el-main>
      </el-container>
    </el-container>
  </el-container>
</template>

<style>
.el-dropdown-link:focus {
  outline: none;
}
.el-dropdown:hover {
  border:none;
}
.userid {
  font-size: small;
  font-weight: bold;
  margin-left: 2vh;
}
.block-col-2 .demonstration {
  display: block;
  color: var(--el-text-color-secondary);
  font-size: 14px;
  margin-bottom: 20px;
}
.demo-type {
  display: flex;
}
.demo-type > div {
  flex: 1;
  text-align: center;
}

.demo-type > div:not(:last-child) {
  border-right: 1px solid var(--el-border-color);
}
.common_layout {
  height: 100vh;
  background-image: url('/src/img/background.png');
}
.submenu {
  background-color: #181e32;
}
.flex-grow {
  flex-grow: 1;
}
.el-aside {
  background-color: #313743;
  height: 168px;
}
.el-aside .el-menu {
  border-right: none;
}

.el-main {
  background-image: url('/src/img/background.png');
}
.el-header {
  background-image: url('/src/img/background.png');
  /*background-color: #393e47;*/
  display: flex;
  justify-content: space-between;
  padding-left: 0;
  align-items: center;
  color: #fdfdfd;
  font-size: 40px;
  font-family: "微软雅黑";
  font-weight: bolder;
}
#logo {
  display: flex;
  align-items: center;
}
#title {
  margin-left: 20px;
  font-size: 30px;
}
#img1 {
  width: 10vh;
  height: 10vh;
  margin-left: 1vh;
  margin-top: 0vh;
  z-index: 100;
}
.el-header .el-menu {
  background-color: #011b26;
  color: #fff;
}
.el-aside {
  background-image: url("src/img/background.png");
  /*background-color: #03122f;*/
}
.flex {
  margin-right: 5vh;
}
.example-showcase .el-dropdown + .el-dropdown {
  margin-left: 10px;
  font-size: larger;
}
.el-dropdown-link {
  cursor: pointer;
  color: white;
  display: flex;
  align-items: center;
}
</style>

<script setup>
import { ElMessage } from "element-plus";
import Cookies from "js-cookie";
import router from "@/router";
import {
  Document,
  Menu as IconMenu,
  Location,
  Setting,
} from "@element-plus/icons-vue";
import { ref } from "vue";
import { ArrowDown } from '@element-plus/icons-vue'
import axios from "axios";
const username = ref("游客");
const userId = Cookies.get('userid')
axios.get('/api/getUserName', {
  params: {
    userid: userId
  }
}).then(response => {
  console.log("username",response.data);
  username.value = response.data; // 将获取到的 username 赋值给 ref 变量
}).catch(error => {
  console.log(error);
});
Cookies.set('username', username, {expires: 1}); // 存入cookie，expires从当前时间开始的过期天数


// const handleOpen = (key: string, keyPath: string[]) => {
//     console.log(key, keyPath)
// }
// const handleClose = (key: string, keyPath: string[]) => {
//     console.log(key, keyPath)
// }

// const logout = () => {
//   get("/api/auth/logout", (message) => {
//     ElMessage.success(message);
//     router.push("/");
//   });
// };

// const activeIndex = ref("1");

// const handleClick = () => {
//   alert('button click')
// }

</script>
