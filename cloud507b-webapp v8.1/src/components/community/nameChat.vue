<template>
    <div class="name">
        <el-form class="name-form" ref="nameFormRef" >
            <el-form-item style="color: white;">
                <h1 style="font-size: 240%;padding-bottom: 20px ">507b小窝聊天区</h1>
            </el-form-item>
            <el-form-item class="name-input" prop="nickname">
                <el-input class="nickname" v-model="nameForm.nickname" placeholder="输入一个昵称"></el-input>
                <el-button class="submit" type="primary" @click="submitButton(nameFormRef)">进入</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
import { reactive,ref,defineEmits } from "vue";
import { ElForm, ElFormItem, ElInput, ElButton, ElMessage } from "element-plus";
import router from "@/router";
import axios from "axios";

export default {
    props: ['flag'],
    setup(props,ctx) {
        const nameForm = reactive({
            nickname: "",
        });
        const {flag} = props
        console.log(ctx);
        // 昵称表单
        const nameFormRef = ref();
        // 表单校验
        const nameRules = reactive({
            nickname: [{ required: true, message: "请输入一个昵称", trigger: "blur" }],
        });

        // 登录操作
        const submitButton = (formEl) => {
            axios.get("http://localhost:8888/list/" + nameForm.nickname).then((resp) => {
                const data = resp.data;
                // 判断用户名是否存在
                if (!data.isExist) {
                    sessionStorage.setItem("name", nameForm.nickname);
                    alert("成功", nameForm.nickname);
                    router.push("/index/control");
                    ctx.emit('changeFlag')
                } else {
                    ElMessage({
                        message: "您已经登录",
                        grouping: true,
                        type: "error",
                    });
                }
            });
        };
        return {
            nameForm,
            nameFormRef,
            submitButton
        }
    }
}
</script>
  
<style lang="scss">
@import "../../css/name.scss";
</style>
  