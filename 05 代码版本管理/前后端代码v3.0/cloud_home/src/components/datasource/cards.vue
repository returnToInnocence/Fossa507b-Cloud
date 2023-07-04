<template>
  <div class="bg-gray-100 min-h-screen flex items-center justify-center px-4 sm:px-6 lg:px-8">
    <div class="max-w-md w-full space-y-8">
      <div>
        <h3 class="mt-6 text-center text-3xl font-extrabold text-gray-900">求助列表</h3>
      </div>
      <div class="bg-white py-8 px-4 shadow sm:rounded-lg sm:px-10">
        <div class="space-y-6">
          <div>
            <table class="min-w-full divide-y divide-gray-200">
              <thead class="bg-gray-50">
                <tr>
                  <th class="px-2 py-4 text-xs font-medium text-gray-500 uppercase tracking-wider  text-left">
                    求助人
                  </th>
                  <th class="px-6 py- text-xs font-medium text-gray-500 uppercase tracking-wider  text-left">
                    求助简述
                  </th>
                  <th class="px-2 py-4 text-xs font-medium text-gray-500 uppercase tracking-wider  text-left">
                    操作
                  </th>
                </tr>
              </thead>
            </table>
            <el-scrollbar class="scrollbar table-wrapper">
              <table class="min-w-full divide-y divide-gray-200">
                <tbody class="bg-white divide-y divide-gray-200">
                  <tr v-for="item in items" :key="item.id">
                    <td class="px-2 py-4 whitespace-nowrap">
                      <div class="text-sm font-medium text-gray-900  text-left">{{ item.name }}</div>
                    </td>
                    <td class="px-6 py-4 whitespace-nowrap">
                      <div class="text-sm text-gray-500 text-center" style="position:center;">{{ item.description }}</div>
                    </td>
                    <td class="px-2 py-4 whitespace-nowrap text-center">
                      <button @click="goToDetail(item.id)"
                        class="text-indigo-600 font-medium tracking-wider btn-details rounded-md">查看详情</button>
                    </td>
                  </tr>
                </tbody>
              </table>
            </el-scrollbar>

          </div>
          <div class="mt-6 flex justify-center">
            <button @click="goToPublish" class="px-4 py-2 bg-indigo-600 text-white font-medium rounded-md">我要求助</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { reactive } from 'vue';
import { useRouter } from 'vue-router';
import { ElScrollbar } from 'element-plus';

export default {
  setup() {
    const items = reactive([
      { id: 1, name: 'Item 1', description: 'This is item 1' },
      { id: 2, name: 'Item 2', description: 'This is item 2' },
      { id: 3, name: 'Item 3', description: 'This is item 3' },
      { id: 4, name: 'Item 4', description: 'This is item 4' },
      { id: 5, name: 'Item 5', description: 'This is item 5' },
      { id: 6, name: 'Item 6', description: 'This is item 6' },
      { id: 1, name: 'Item 1', description: 'This is item 1' },
      { id: 2, name: 'Item 2', description: 'This is item 2' },
      { id: 3, name: 'Item 3', description: 'This is item 3' },
      { id: 4, name: 'Item 4', description: 'This is item 4' },
      { id: 5, name: 'Item 5', description: 'This is item 5' },
      { id: 6, name: 'Item 6', description: 'This is item 6' }
    ]);

    const router = useRouter();

    const goToDetail = (id) => {
      router.push({ name: 'datasource-detail', params: { id } });
    };

    const goToPublish = () => {
      router.push('/index/publish');
    };

    return {
      items,
      goToDetail,
      goToPublish
    };
  }
};
</script>

<style scoped>
.bg-gray-100 {
  background-color: #f7fafc;
}

.scrollbar {
  height: 400px;
  width: auto;
  overflow: auto;
}

.min-h-screen {
  min-height: 100vh;
}

.flex {
  display: flex;
}

.py-2 {
  padding-top: 0.5rem;
  padding-bottom: 0.5rem;
}

.bg-indigo-600 {
  background-color: #4f46e5;
}

.text-white {
  color: #ffffff;
}

.rounded-md {
  border-radius: 0.375rem;
}

.items-center {
  align-items: center;
}

.justify-center {
  justify-content: center;
}

.px-4 {
  padding-left: 1rem;
  padding-right: 1rem;
}

.sm\:px-6 {
  padding-left: 1.5rem;
  padding-right: 1.5rem;
}

.lg\:px-8 {
  padding-left: 2rem;
  padding-right: 2rem;
}

.max-w-md {
  max-width: 28rem;
}

.w-full {
  width: 100%;
}

.space-y-8> :not([hidden])~ :not([hidden]) {
  margin-top: 2rem;
}

.mt-6 {
  margin-top: 1.5rem;
}

.text-center {
  text-align: center;
}

.text-left {
  text-align: left;
}

.btn-details {
  background-color: #3385f8;
  background-image: linear-gradient(45deg, #7af7c3 0%, #48dbdd 100%);
  color: #ffffff;
  border-radius: 0.175rem;
  padding: 0.45rem 0.5rem;
  transition: background-color 0.3s ease, transform 0.3s ease;
}

.btn-details:hover {
  background-color: #45a6cc;
  transform: scale(1.05);
}

.text-3xl {
  font-size: 1.875rem;
}

.font-extrabold {
  font-weight: 800;
}

.text-gray-900 {
  color: #111827;
}

.bg-white {
  background-color: #ffffff;
}

.py-8 {
  padding-top: 2rem;
  padding-bottom: 2rem;
}

.px-4 {
  padding-left: 1rem;
  padding-right: 1rem;
}

.shadow {
  box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1), 0 2px 4px -1px rgba(0, 0, 0, 0.06);
}

.rounded-lg {
  border-radius: 0.5rem;
}

.sm\:rounded-lg {
  border-radius: 0.375rem;
}

.py-10 {
  padding-top: 2.5rem;
  padding-bottom: 2.5rem;
}

.px-10 {
  padding-left: 2.5rem;
  padding-right: 2.5rem;
}

.space-y-6> :not([hidden])~ :not([hidden]) {
  margin-top: 1.5rem;
}

.table-wrapper {
  flex: 1;
  overflow: auto;
}


.min-w-full {
  min-width: 100%;
}

.divide-y {
  border-bottom-width: 1px;
  border-color: rgba(0, 0, 0, 0.06);
}

.text-xs {
  font-size: 0.75rem;
}

.font-medium {
  font-size: 5px;
}

.uppercase {
  text-transform: uppercase;
}

.tracking-wider {
  letter-spacing: 0.05em;
}

.text-gray-500 {
  color: #6b7280;
}

.whitespace-nowrap {
  white-space: nowrap;
}

.text-sm {
  font-size: 0.875rem;
}

.text-indigo-600 {
  color: #3295eb;
}

.underline {
  text-decoration: underline;
}
</style>
