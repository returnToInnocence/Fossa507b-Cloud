<template>
  <div>
    <div class="container mx-auto px-4 py-8">
      <div class="shadow-lg rounded-lg bg-white overflow-hidden">
        <div class="p-8">
          <h3 class="text-center text-xl font-bold mb-4">求助详情</h3>
          <div>
            <ul class="text-gray-700">
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
          <div class="text-center mt-8">
            <button @click="goToList" class="text-indigo-600 font-medium underline">返回列表</button>
          </div>
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

    // 假设这是从服务器或其他来源获取的具体项目数据
    const currentItem = reactive({
      id: null,
      name: '',
      description: '',
      contact: '',
      createdAt: ''
    });

    // 根据传入的id查找并设置当前项目的详细信息
    const fetchItemDetails = () => {
      // 这里可以根据id向服务器请求具体项目的详细信息
      // 假设从服务器获取到了对应id的项目数据并赋值给currentItem

      // 示例:
      currentItem.id = id;
      currentItem.name = `Item ${id}`;
      currentItem.description = `This is item ${id}`;
      currentItem.contact = `example@example.com`;
      currentItem.createdAt = `July 3, 2023`;
    };

    const goToList = () => {
      router.push({ name: 'list' });
    };

    fetchItemDetails();

    return {
      currentItem,
      goToList
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

.text-xl {
  font-size: 1.25rem;
}

.mb-4 {
  margin-bottom: 1rem;
}

.text-gray-700 {
  color: #4b5563;
}

.font-medium {
  font-weight: 500;
}

.mt-8 {
  margin-top: 2rem;
}

.text-indigo-600 {
  color: #6366f1;
}

.underline {
  text-decoration: underline;
}
</style>
