import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import ElementPlus from 'element-plus';
import './index.css'

createApp(App).use(router).use(ElementPlus).mount('#app');
