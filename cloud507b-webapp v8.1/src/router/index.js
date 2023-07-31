import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    //登录注册的路由
    {
      path:'/',
      name:'welcome',
      component:() => import('@/views/WelcomeView.vue'),
      children:[
        {
          path:'',
          name:'welcome-login',
          component:()=>import('@/components/welcome/LoginPage.vue')
        },
      ]
    },
    //实验室资源展示区的路由
    {
      path:'/index',
      name:'index',
      component:() => import('@/views/IndexView.vue'),
      children:[
        {
          path:'',
          name:'datashow-index',
          component:()=>import('@/components/datasource/resourceview.vue')
        },
        {
          path:'seats',
          name:'datasource-seats',
          component:()=>import('@/components/datasource/seats.vue')
        },
        {
          path:'resourceview',
          name:'datasource-resourceview',
          component:()=>import('@/components/datasource/resourceview.vue')
        },
        {
          path:'cards',
          name:'datasource-cards',
          component:()=>import('@/components/datasource/cards.vue')
        },
        {
          path:'/detail/:id/:name/:description/:date',
          name:'datasource-detail',
          component:()=>import('@/components/datasource/Detail.vue')
          // props:router => ({id:route.params.id, name: route.params.name, })
        },
        //聊天区域的路由
        {
          path:'chat',
          name:'community-chat',
          component:()=>import('@/components/community/chat.vue')
        },
        {
          path:'homepost',
          name:'community-homepost',
          component:()=>import('@/components/community/homepost.vue')
        },
        //用户控制区
        {
          path:'control',
          name:'controller-usercontroller',
          component:()=>import('@/components/controller/usercontroller.vue')
        },
      ]
    }

  ]
})

export default router
