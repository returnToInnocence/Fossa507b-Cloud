<template>
  <el-container class="common_layout">
    <el-container style="height: auto;overflow: hidden;">
      <el-header style="padding: 0">
        <div id="logo">
          <img src="../img/logo.png" alt="" id="img1" />
          <span id="title">507b云上小窝</span>
        </div>
        <div class="flex flex-wrap items-center">
          <el-dropdown>
            <el-button type="primary" id="zhanghu">
              账户<el-icon class="el-icon--right"><arrow-down /></el-icon>
            </el-button>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item>个人信息</el-dropdown-item>
                <el-dropdown-item>其他</el-dropdown-item>
                <el-dropdown-item @click="logout">退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
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

<style scoped>
.common_layout {
  height: 100vh;
  background-color: aqua;
  overflow: hidden;
}

.submenu {
  background-color: #181e32;
}

.flex-grow {
  flex-grow: 1;
}

.el-aside {
  background-color: #313743;
}

.el-aside .el-menu {
  border-right: none;
}

.el-main {
  background-color: #e9edf1;
  overflow: hidden;
  padding: 0;
  margin: 20px;
}

.el-header {
  background-color: #393e47;
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
  margin-left: 25px;
  font-size: 30px;
}

#img1 {
  width: 13vh;
  height: 13vh;
  margin-left: 1vh;
  margin-top: 0vh;
  z-index: 100;
}

.el-header .el-menu {
  background-color: #011b26;
  color: #fff;
}

.el-aside {
  background-color: #03122f;
}

.flex {
  margin-right: 5vh;
}

.example-showcase .el-dropdown+.el-dropdown {
  margin-left: 10px;
  font-size: larger;
}

.example-showcase .el-dropdown-link {
  cursor: pointer;
  color: var(--el-color-primary);
  display: flex;
  align-items: center;
}
</style>

<script setup>
import { ElMessage } from "element-plus";
import { get } from "@/net";
import router from "@/router";
import {
  Document,
  Menu as IconMenu,
  Location,
  Setting,
} from "@element-plus/icons-vue";
import { ref } from "vue";
import { ArrowDown } from '@element-plus/icons-vue'
// const handleOpen = (key: string, keyPath: string[]) => {
//     console.log(key, keyPath)
// }
// const handleClose = (key: string, keyPath: string[]) => {
//     console.log(key, keyPath)
// }

const logout = () => {
  get("/api/auth/logout", (message) => {
    ElMessage.success(message);
    router.push("/");
  });
};

const activeIndex = ref("1");


const handleClick = () => {
  alert('button click')
}
</script>
