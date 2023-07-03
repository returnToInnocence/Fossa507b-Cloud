import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
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
        {
          path:'register',
          name:'welcome-register',
          component:()=>import('@/components/welcome/RegisterPage.vue')
        }
      ]
    },{
      path:'/index',
      name:'index',
      component:() => import('@/views/IndexView.vue'),
      children:[
        {
          path:'',
          name:'datashow-index',
          component:()=>import('@/components/datashow/index.vue')
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
      ]
    }
  ]
})

export default router
