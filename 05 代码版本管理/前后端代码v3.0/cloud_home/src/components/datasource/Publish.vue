<template>
    <div class="bg-gray-100 min-h-screen flex items-center justify-center px-4 sm:px-6 lg:px-8">
        <div class="max-w-md w-full space-y-8">
            <form @submit.prevent="publish" class="bg-white py-8 px-4 shadow sm:rounded-lg sm:px-10">
                <div class="space-y-6">
                    <h3 class="mt-6 text-center text-3xl font-extrabold text-gray-900">发布求助</h3>
                    <div class="mt-2">
                        <label for="name" class="block text-sm font-medium text-gray-700">求助人：</label>
                        <input v-model="name" type="text" name="name" id="name" autocomplete="off" required
                            class="mt-1 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border-gray-300 rounded-md border-2 border-blue-500 px-2 py-1">
                        <label for="phonenum" class="block text-sm font-medium text-gray-700">联系方式：</label>
                        <input v-model="phonenum" type="text" name="phonenum" id="phonenum" autocomplete="off" required
                            class="mt-1 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border-gray-300 rounded-md border-2 border-blue-500 px-2 py-1">

                    </div>
                    <div class="mt-2">
                        <label for="description" class="block text-sm font-medium text-gray-700">求助简述：</label>
                        <textarea v-model="description" name="description" id="description" rows="3" required
                            class="mt-1 resize-none focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border-gray-300 rounded-md border-2 border-blue-500 px-2 py-1"></textarea>
                    </div>
                    <div class="flex justify-center">
                        <button type="submit"
                            class="mt-2 inline-flex justify-center py-2 px-4 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">
                            提交求助
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</template>
  
<script>
import { reactive } from 'vue';
import { useRouter } from 'vue-router';

export default {
    setup() {
        const router = useRouter();
        const name = reactive('');
        const description = reactive('');
        const phonenum = reactive('');
        const time = reactive('');
        const items = reactive([]);
        const publish = () => {
            // 发布求助的逻辑处理

            let items = [name, description];
            router.push({ name: 'list', param: { items } });
        };

        return {
            name,
            description,
            publish
        };
    }
};
</script>
<style scoped>
.bg-gray-100 {
    background-color: #f7fafc;
}

.min-h-screen {
    min-height: 100vh;
}

.flex {
    display: flex;
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

.block {
    display: block;
}

.text-sm {
    font-size: 0.875rem;
}

.font-medium {
    font-weight: 500;
}

.text-gray-700 {
    color: #4b5563;
}

.mt-1 {
    margin-top: 0.25rem;
}

.appearance-none {
    appearance: none;
}

.border {
    border-width: 1px;
}

.border-gray-300 {
    border-color: #e5e7eb;
}

.rounded-md {
    border-radius: 0.375rem;
}

.placeholder-gray-400 {
    color: #9ca3af;
}

.focus\:outline-none:focus {
    outline: none;
}

.focus\:ring-indigo-500:focus {
    --tw-ring-color: rgba(59, 130, 246, 0.5);
    box-shadow: 0 0 0 3px var(--tw-ring-color);
    --tw-ring-opacity: 1;
}

.focus\:border-indigo-500:focus {
    --tw-border-opacity: 1;
    border-color: rgba(59, 130, 246, 0.5);
}

.resize-none {
    resize: none;
}

.bg-indigo-600 {
    background-color: #4f46e5;
}

.text-white {
    color: #ffffff;
}

.py-2 {
    padding-top: 0.5rem;
    padding-bottom: 0.5rem;
}

.px-4 {
    padding-left: 1rem;
    padding-right: 1rem;
}

.hover\:bg-indigo-700:hover {
    background-color: #4338ca;
}
</style>
  