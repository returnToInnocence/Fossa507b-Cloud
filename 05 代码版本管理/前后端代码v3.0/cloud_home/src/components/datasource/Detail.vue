<template>
  <div class="container mx-auto px-4 py-8">
    <div class="shadow-lg rounded-lg bg-white overflow-hidden">
      <div class="p-8">
        <h3 class="text-center text-2xl font-bold mb-6">求助详情</h3>
        <div>
          <ul class="text-gray-700 space-y-4">
            <li>
              <span class="font-medium">求助人：</span>
              <span>{{ currentItem.name }}</span>
            </li>
            <li>
              <span class="font-medium">求助简述：</span>
              <span>{{ currentItem.description }}</span>
            </li>
            <li>
              <span class="font-medium">联系方式：</span>
              <span>{{ currentItem.contact }}</span>
            </li>
            <li>
              <span class="font-medium">创建时间：</span>
              <span>{{ currentItem.createdAt }}</span>
            </li>
          </ul>
        </div>
        <div class="flex justify-center mt-8">
          <button @click="goToList" class="mr-4 button button-secondary">返回列表</button>
          <button @click="deleteItem" class="button button-primary">我帮了！</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { reactive } from 'vue';
import { useRouter } from 'vue-router';

export default {
  setup() {
    const router = useRouter();
    const { id } = router.currentRoute.value.params;

    const currentItem = reactive({
      id: null,
      name: '',
      description: '',
      contact: '',
      createdAt: ''
    });

    const fetchItemDetails = () => {
      // 模拟从服务器获取数据
      currentItem.id = id;
      currentItem.name = `Item ${id}`;
      currentItem.description = `This is item ${id}`;
      currentItem.contact = `example@example.com`;
      currentItem.createdAt = `July 3, 2023`;
    };

    const goToList = () => {
      router.push('/index/cards');
    };

    fetchItemDetails();

    const deleteItem = () => {
      // 模拟发送删除请求
      window.alert('发送成功'); // 删除成功后的提示
      goToList();
    };

    return {
      currentItem,
      goToList,
      deleteItem
    };
  }
};
</script>

<style scoped>
.container {
  max-width: 600px;
}

.shadow-lg {
  box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1), 0 4px 6px -2px rgba(0, 0, 0, 0.05);
}

.bg-white {
  background-color: #fff;
}

.p-8 {
  padding: 2rem;
}

.text-2xl {
  font-size: 1.5rem;
}

.mb-6 {
  margin-bottom: 1.5rem;
}

.space-y-4>li {
  margin-bottom: 1rem;
}

.text-gray-700 {
  color: #4b5563;
}

.font-medium {
  font-weight: 500;
}

.flex {
  display: flex;
}

.justify-center {
  justify-content: center;
}

.mr-4 {
  margin-right: 1rem;
}

.button {
  display: inline-block;
  padding: 0.5rem 1.5rem;
  text-align: center;
  border-radius: 0.25rem;
  cursor: pointer;
}

.button-secondary {
  background-color: #e5e7eb;
  color: #4b5563;
  border: none;
  transition: background-color 0.3s ease;
}

.button-secondary:hover {
  background-color: #d1d5db;
}

.button-primary {
  background-color: #6366f1;
  color: #fff;
  border: none;
  transition: background-color 0.3s ease;
}

.button-primary:hover {
  background-color: #4f46e5;
}
</style>
