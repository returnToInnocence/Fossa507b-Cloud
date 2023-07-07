<template>
    <div class="login-submit round-corner" ref="slide">
        <form>
            <div class="left-login vertical-center" v-show="this.sw">
                <h1>login</h1>
                <div class="count">
                    <input v-model="username" type="text" placeholder="用户名" class="init-input untize-input underline">
                </div>
                <div>
                    <input v-model="password" type="password" placeholder="密码" class="init-input untize-input underline">
                </div>
                <input type="submit" @click="confirm" value="学生登录"
                    class="init-input untize-input login-button round-corner">
                <input type="submit" @click="confirm" value="教师登录"
                    class="init-input untize-input login-button round-corner">
            </div>
            <div class="right-signup vertical-center" v-show="!this.sw">
                <h1>signup</h1>
                <div class="count">
                    <input v-model="username" type="text" placeholder="用户名" class="init-input untize-input underline">
                </div>
                <div>
                    <input v-model="password" type="password" placeholder="密码" class="init-input untize-input underline">
                </div>
                <div>
                    <input v-model="password" type="password" placeholder="确认密码" class="init-input untize-input underline">
                </div>
                <input type="submit" value="注册" class="init-input untize-input login-button round-corner1">
            </div>
        </form>
    </div>
</template>

<script>
export default {
    props: ["sw"],
    // name: "login",
    // data() {
    //     return {
    //         loginForm: {
    //             name: '',
    //             password: ''
    //         },
    //         rules: {

    //         }
    //     }
    // },
    // //登陆跳转
    // methods: {
    //     confirm() {
    //         this.$router.replace('/index');
    //     }
    // },
    // 实现滑动效果
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
            loginForm: {
                name: '',
                password: ''
            },
            rules: {

            }
        }
    },
    methods: {
        confirm() {
            this.$router.replace('/index');
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
    border-bottom: 1px solid red !important;
}

input:focus::placeholder {
    color: red;
}

.untize-input {
    width: 210px;
    height: 45px;
    font-size: 14px;
}

.underline {
    border-bottom: 1px solid #FF6666 !important;
}

.underline::placeholder {
    color: #FF6666;
}

.login-submit {
    position: absolute;
    left: 30px;
    top: -50px;
    width: 300px;
    height: 450px;
    background-color: #FFCCCC;
    box-shadow: 5px 5px 5px 0 rgba(0, 0, 0, 0.1);
}

.login-submit .left-login,
.login-submit .right-signup {
    height: 100%;
    flex-flow: column nowrap;
}

.login-submit .login-button {
    background-color: hsla(0, 0%, 100%, 0.5);
    color: #FF6666;
}

.login-submit .login-button:hover {
    background-color: #FF6666;
    color: white;
    transition: 0.5s;
}
</style>