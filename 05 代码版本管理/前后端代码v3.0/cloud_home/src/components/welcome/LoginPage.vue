<template>
    <div class="box">
      <MainContentVue />
  
    </div>
  </template>
  
  <script>
  import MainContentVue from './MainContent.vue'
  
  export default {
    name: 'App',
    components: {
      MainContentVue,
  
    },
  }
  </script>
  
  <style>
  input {
    letter-spacing: 2px;
  }
  
  /* 圆角效果 */
  .round-corner {
    border-radius: 6px;
  }
  
  /* 垂直居中 */
  .vertical-center {
    display: flex;
    justify-content: space-evenly;
    align-items: center;
  }
  
  /* 初始化input */
  .init-input {
    display: block;
    padding: 0;
    margin: 0;
    outline: none;
    border: none;
    background-color: transparent;
  }
  
  /* 旋转图片的动画效果 */
  @keyframes rotate {
    from {
      transform: rotate(0deg);
    }
  
    to {
      transform: rotate(360deg);
    }
  }
  
  .rotate {
    animation: rotate 10s linear infinite;
  }
  
  .box {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 100%;
    background: linear-gradient(200deg, #FF6666, #FFCCCC) no-repeat;
  }
  </style>