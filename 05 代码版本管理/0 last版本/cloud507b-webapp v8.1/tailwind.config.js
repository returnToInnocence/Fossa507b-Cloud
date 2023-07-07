/** @type {import('tailwindcss').Config} */
module.exports = {
  purge: [
    './index.html',
    './src/**/*.{vue,js,ts,jsx,tsx}'  //包含了src文件夹下所有的vue,js等等文件
  ],
  content: [],
  theme: {
    extend: {},
  },
  plugins: [],
}

