<script setup>

</script>

<template>
  <div class="login">
            <router-view v-slot="{ Component }">
                <transition name="el-fade-in-linear">
                    <component :is="Component" />
                </transition>
            </router-view>
    </div>  
</template>

<style scoped>
.login{
    width:100%;
    height:100%;
    margin-top: 0vh;
}
</style>