<template>
    <div class="login-wrap round-corner">
        <div class="content">
            <div class="background-kanban vertical-center">
                <div class="left-inside round-corner vertical-center">
                    <h1>让你久等了</h1>
                    <div class="eris">
                        <img src="/src/img/Logo.gif" alt="signup-img" class="round-corner rotate">
                    </div>
                    <span style="color: white">已有账号？快回去登录吧！</span>
                    <input type="submit" value="登录" class="init-input submit round-corner " @click="change">
                </div>
                <div class="right-inside round-corner vertical-center">
                    <h1>507B欢迎你</h1>
                    <div class="eris">
                        <img src="/src/img/Logo.gif" alt="signup-img" title="Meow" class="round-corner rotate"
                            @click="Meow">
                    </div>
                    <span style="color: white">没有账号？真拿你没办法呢</span>
                    <input type="submit" value="注册" class="init-input submit round-corner" @click="change">
                </div>
            </div>
            <Sliding :sw="sw" />
        </div>
    </div>
</template>

<script>
import Sliding from "./Sliding.vue"
export default {
    components: {
        Sliding,
    },
    data() {
        return {
            sw: true,
            isPlay: false,
        }
    },
    methods: {
        change() {
            this.sw = !this.sw;
        },
        Meow() {
            const audio = new Audio(Meow);
            if (!this.isPlay) {
                audio.play();
                this.isPlay = true;
                // 防止重复播放
                audio.onended = () => this.isPlay = false;
            }
        }
    },
}
</script>

<style scoped>
h1 {
    letter-spacing: 3px;
    color: hsla(0, 0%, 100%, 0.7);
}

.login-wrap {
    background-image: url("src/img/background.jpg");
    width: 600px;
    height: 350px;
    background: hsla(0, 0%, 100%, 0.25);
    box-shadow: 5px 5px 5px 0 rgba(0, 0, 0, 0.1);
}

.content {
    width: 100%;
    height: 100%;
    position: relative;
}

.content .background-kanban {
    width: 100%;
    height: 100%;
}

.background-kanban img {
    display: block;
    width: 100px;
    height: 100px;
    margin: 0;
    clip-path: circle(at center);
    transition: 1s clip-path;
}

.background-kanban img:hover {
    cursor: pointer;
}

.background-kanban .right-inside,
.background-kanban .left-inside {
    flex-flow: column nowrap;
    width: 40%;
    height: 80%;
}

.background-kanban .submit {
    width: 60px;
    height: 30px;
    color: black;
    border: 1px solid white;
    background-color: white;
}

.background-kanban span {
    font-size: 10px;
    color: gray;
}

.submit:hover {
    cursor: pointer;
    background-color: #FF6666;
    color: white;
    transition: 0.5s;
}
</style>