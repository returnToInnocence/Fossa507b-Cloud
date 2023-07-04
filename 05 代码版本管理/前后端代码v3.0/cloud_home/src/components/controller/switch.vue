<script setup lang="ts">
import { ref, watch } from 'vue'
import axios from 'axios';
interface Props {
  checkedInfo?: string // 选中时的内容
  uncheckedInfo?: string // 未选中时的内容
  disabled?: boolean // 是否禁用
  checked?: boolean // （v-model）指定当前是否选中
}
const props = withDefaults(defineProps<Props>(), {
  checkedInfo: '',
  uncheckedInfo: '',
  disabled: false,
  checked: false
})
const checked = ref(props.checked)
watch(
  () => props.checked,
  (to): void => {
    checked.value = to
  }
)
const emit = defineEmits(['update:checked', 'change'])
function onSwitch() {
  emit('update:checked', !checked.value)
  emit('change', !checked.value)
  axios.get('/user?ID=12345').then(function (response) {
    console.log(response);
  }).catch(function (error) {
    console.log(error);
  });
}
</script>
<template>
  <div class="m-switch-wrap">
    <div @click="disabled ? () => false : onSwitch()"
      :class="['m-switch', { 'switch-checked': checked, 'disabled': disabled }]">
      <div :class="['u-switch-inner', checked ? 'inner-checked' : 'inner-unchecked']">{{ checked ? checkedInfo :
        uncheckedInfo }}</div>
      <div :class="['u-node', { 'node-checked': checked }]">
        <slot name="node" :checked="checked"></slot>
      </div>
    </div>
  </div>
</template>
<style scoped>
.m-switch-wrap {
  display: inline-block;
  height: 52px;
  min-width: 150px;
}

.m-switch {
  position: relative;
  display: inline-flex;
  vertical-align: top;
  align-items: center;
  width: 100%;
  height: 100%;
  color: rgba(0, 0, 0, .65);
  font-size: 14px;
  background: rgba(0, 0, 0, .25);
  border-radius: 100px;
  cursor: pointer;
  transition: background .36s;
}

.m-switch .u-switch-inner {
  color: #fff;
  font-size: 14px;
  line-height: 22px;
  padding: 0 8px;
  transition: all .36s;
}

.m-switch .inner-checked {
  margin-right: 18px;
}

.m-switch .inner-unchecked {
  margin-left: 18px;
}

.m-switch .u-node {
  position: absolute;
  left: 2px;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 80px;
  height: 52px;
  background: #292b2b;
  border-radius: 4.5vh;
  cursor: pointer;
  transition: all .36s;
}

.m-switch .u-node.node-checked {
  /* 结果等价于right: 2px; 为了滑动效果都以左边为基准进行偏移 */
  left: 100%;
  margin-left: 0px;
  transform: translateX(-100%);
}

.switch-checked {
  /* 这里的 @themeColor 是 Less 中的变量，你可以将其替换为实际的颜色值 */
  background: rgb(81, 81, 236);
}

.disabled {
  cursor: not-allowed;
  opacity: .4;
}

.disabled .u-node {
  cursor: not-allowed;
}
</style>