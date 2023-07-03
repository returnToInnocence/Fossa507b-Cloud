import { createRouter, createWebHistory } from 'vue-router';
import List from './components/List.vue';
import Detail from './components/Detail.vue';
import Publish from './components/Publish.vue';

const routes = [
    { path: '/', component: List },
    { path: '/detail/:id', name: 'detail', component: Detail },
    { path: '/list', name: 'list', component: List },
    { path: '/publish', name: 'publish', component: Publish }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;
