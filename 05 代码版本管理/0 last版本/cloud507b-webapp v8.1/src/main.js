

import { createApp } from 'vue'
import { createPinia } from 'pinia'
import App from './App.vue'
import router from './router'
import axios from "axios";
import ElementPlus from 'element-plus';//为vue3项目特别更新的版本
import 'element-plus/dist/index.css';
import * as echarts from 'echarts'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import 'tailwindcss/tailwind.css';

const app = createApp(App)
app.config.globalProperties.$echarts = echarts
app.use(createPinia())
app.use(router)
app.mount('#app')
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
  }
//配置后端服务地址
axios.defaults.baseURL = 'http://localhost:8080'

