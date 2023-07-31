<template>
  <div class="login-submit round-corner" ref="slide">
    <form>
      <div class="left-login vertical-center" v-show="this.sw">
        <h1>login</h1>
        <div class="count">
          <input v-model="userid" type="text" placeholder="学号" class="init-input untize-input underline">
        </div>
        <div>
          <input v-model="userpsd" type="password" placeholder="密码" class="init-input untize-input underline">
        </div>
        <input @click="login" value="学生登录"
               class="init-input untize-input login-button round-corner ">
        <input @click="login" value="教师登录"
               class="init-input untize-input login-button round-corner ">
      </div>
      <div class="right-signup vertical-center" v-show="!this.sw">
        <h1>signup</h1>
        <div class="count">
          <input v-model="userid" type="text" placeholder="学号" class="init-input untize-input underline">
        </div>
        <div class="count">
          <input v-model="username" type="text" placeholder="姓名" class="init-input untize-input underline">
        </div>
        <div class="count">
          <input v-model="usermail" type="text" placeholder="邮箱" class="init-input untize-input underline">
        </div>
        <div class="count">
          <input v-model="userpsd" type="password" placeholder="密码" class="init-input untize-input underline">
        </div>

        <div class="count">
          <select v-model="userstatus" class="init-input untize-input underline">
            <option disabled value="">请选择权限组</option>
            <option value="0">老师</option>
            <option value="1">学生</option>
          </select>
        </div>
        <div class="count">
          <select v-model="usergroup" class="init-input untize-input underline">
            <option disabled value="">请选择所属新思路小组</option>
            <option v-for="group in userGroups" :value="group">{{ group }}</option>
          </select>
        </div>

        <input value="注册" class="init-input untize-input login-button round-corner register-button" @click="register">
      </div>
    </form>
  </div>
</template>

<script>
import { useRouter } from 'vue-router';
import axios from "axios";
import Cookies from "js-cookie"

export default {
  props: ["sw"],
  updated() {
    let slide = this.$refs.slide;
    this.sw
        ? slide.style.transform = 'translateX(0)'
        : slide.style.transform = "translateX(80%)";
    slide.style.transition = "transform 0.5s ease-in-out"
  },
  mounted() {
    // 登录注册界面颜色调整
    let input = document.querySelectorAll(".underline");
    input.forEach(e => {
      e.addEventListener("blur", () => {
        e.style.color = "#FF6666";
      });
      e.addEventListener("focus", () => {
        e.style.color = "red";
      });
    })
  },
  name: "login",
  data() {
    return {
      userid: '',
      username: '',
      usermail: '',
      userpsd: '',
      userstatus: '',// 初始值为空，即默认显示 "请选择权限组"
      usergroup: '',
      rules: {
        //数据合法性检查
      }
    }
  },
  computed: {
    userGroups() {
      if (this.userstatus === '0') { // 如果选择的是老师
        return ['教师组'];
      } else if (this.userstatus === '1') { // 如果选择的是学生
        return ['CPP', 'Geek', '安卓组', '产品组', 'iOS组', '设计组', '微软组', '前端组', '算法组'];
      } else {
        return []; // 其他情况下没有选项
      }
    },
  },
  methods: {
    register(){
      const userForm = {
        userid: this.userid,
        username: this.username,
        usermail: this.usermail,
        userpsd: this.userpsd,
        userstatus: this.userstatus,
        usergroup: this.usergroup
      }
      console.log("点击了Sliding的注册按钮");
      axios.post("/api/register",{
        userForm
      }).then(response => {
        console.log(response);
        if(response.data.message === "用户注册成功")
        {
          alert("注册成功");
          this.$router.push('/');
        }else if(response.data.message === "用户更新信息成功")
        {
          alert("信息更新成功");
          this.$router.push('/');
        }else{
          alert("注册失败");
          this.$router.push('/');
        }
      }).catch(err => {
        console.log(err);
      });
    },
    login() {
      console.log("点击了登录按钮")
      //console.log(this.userid)
      //console.log(this.userpsd)
      const loginForm = {
        userid: this.userid,
        userpsd: this.userpsd,
      }
      axios.post("/api/login",{
        loginForm
      }).then(response => {
        //console.log("message:", response.data.message);
        //console.log("判断结果:", response.data.message === "密码不正确");
        if (response.data.message === "密码不正确"){
          alert("密码错误");
          this.$router.push('/');
        }else{
          alert("登录成功");
          console.log("userid：",response.data.data)
          const userId = response.data.data; // 假设后端返回的用户ID为userId
          Cookies.set('userid', userId, {expires: 1}); // 存入cookie，expires从当前时间开始的过期天数
          //Cookie.set('cookieName', 'cookieValue',{expires: 1});//
          this.$router.push('/index');
        }
      }).catch(err => {
        console.log(err);
        alert("用户信息异常");
        this.$router.back();
      });
    }
  }

}
</script>

<style scoped>
form {
  height: 100%;
}

h1 {
  /* 文本居中 */
  text-align: center;
  /* 转换大写 */
  text-transform: uppercase;
  color: hsla(0, 0%, 100%, 0.5);
  /* 字间距 */
  letter-spacing: 5px;
}

input::placeholder {
  letter-spacing: 2px;
}

input:focus {
  border-bottom: 1px solid white ;
}

input:focus::placeholder {
  color: white;
}

.register-button{
  text-align: center;
}

.untize-input {
  width: 210px;
  height: 45px;
  font-size: 14px;
  color: white;
  text-decoration: none;
}

.underline {
  color: white;
  border-bottom: 1px solid white ;
}

.underline::placeholder {
  color: white;
}

.login-submit {
  position: absolute;
  left: 30px;
  top: -50px;
  width: 300px;
  height: 450px;
  background-color: rgb(141,148,195);
  box-shadow: 5px 5px 5px 0 rgba(0, 0, 0, 0.5);
}

.login-submit .left-login,
.login-submit .right-signup {
  height: 100%;
  flex-flow: column nowrap;
}

.login-submit .login-button {
  background-color: hsla(0, 0%, 100%, 0.5);
  color: white;
  text-align: center;
}

.login-submit .login-button:hover {
  background-color: white;
  color: white;
  transition: 0.5s;
}
</style>