<template>
  <div class="whole">
    <div class="header">
      <div class="demo-fit">
<!--        <div class="block">-->
<!--          <el-avatar shape="square" :size="70" :fit="username" :src="url" />-->
<!--          <span class="title">yjy</span>-->
<!--        </div>-->
      </div>
      <div class="post">
        <el-button text @click="dialogFormVisible = true" style="font-size: 25px;font-weight: 500;
        background-color: white; position: fixed;bottom: 2vh;right: 6vh;font-family: '楷体'"> 发帖 </el-button>
      </div>
    </div>

    <el-dialog v-model="dialogFormVisible" title="分享新鲜事...">
      <el-form :model="form">
        <el-form-item label="" :label-width="formLabelWidth">
          <el-input v-model="textarea" :rows="5" type="textarea" placeholder="请输入文字"/>
          <el-upload action="#" list-type="picture-card" :auto-upload="false">
            <el-icon><Plus /></el-icon>
            <template #file="{ file }">
              <div>
                <img class="el-upload-list__item-thumbnail" :src="file.url" alt="" />
                <span class="el-upload-list__item-actions">
                  <span class="el-upload-list__item-preview" @click="handlePictureCardPreview(file)">
                    <el-icon><zoom-in /></el-icon>
                  </span>
                  <span v-if="!disabled" class="el-upload-list__item-delete" @click="handleDownload(file)">
                    <el-icon><Download /></el-icon>
                  </span>
                  <span v-if="!disabled" class="el-upload-list__item-delete" @click="handleRemove(file)">
                    <el-icon><Delete /></el-icon>
                  </span>
                </span>
              </div>
            </template>
          </el-upload>
          <el-dialog v-model="dialogVisible">
            <img w-full :src="dialogImageUrl" alt="Preview Image" />
          </el-dialog>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取消</el-button>
          <el-button type="primary" @click="handleSubmit">确认</el-button>
        </span>
      </template>
    </el-dialog>
    <!-- <div class="name">登陆人姓名</div> -->
    <!-- 获取用户头像 -->
    <!-- <img class="head-icon" :src="attachImageUrl(user.icon)" /> -->

    <div class="home-page">
      <!-- 发布人 -->
      <div v-for="post in posts" :key="post.id" class="post-card" style="margin-bottom: 20px;margin-top: 20px;">
        <div class="post-info1">
          <img :src="post.authorAvatar" class="user-avatar" />
          <div class="post-info-text">
            <div>{{ post.author }}</div>
            <div>{{ post.publishTime }}</div>
          </div>
        </div>
        <!-- 帖子内容 -->
        <div class="post-content">
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{ post.content }}
        </div>
        <br />

        <div class="post-actions">
          <img v-if="!liked" @click="likePost(post)" src="/src/img/heartc.png" class="like-button"/>
          <img v-else @click="likePost(post)" src="/src/img/heart.png" class="like-button"/>
          &nbsp;&nbsp;
          <img @click="openCommentDialog(post.id)" src="/src/img/comm.png" class="comm-button"/>
        </div>
        <div class="likeman">
          <img src="/src/img/like.png" class="comm-button" /><span style="color: rgb(107, 128, 235); vertical-align: top">点赞人列表</span>
        </div>
        <br />
        <div v-for="comment in post.comments" :key="comment.id" class="comment">
          <div class="comment-content">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
              style="color: rgb(107, 128, 235)"
          >{{ comment.author }}:</span
          >{{ comment.content }}
            <span class="comment-metadata">{{ comment.commentTime }}</span>
          </div>
        </div>
        <hr />
      </div>
      <div v-if="showCommentDialog" class="comment-dialog">
          <textarea v-model="comment" rows="2" cols="30" placeholder="在这里输入您的评论"></textarea>
        <div class="button-container">
          <button @click="submitComment" class="custom-button">提交评论</button>
          <button @click="closeCommentDialog" class="custom-button">
            返回主页
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { reactive, ref } from "vue";
import { ElMessage } from 'element-plus'
import axios from "axios";
import Cookies from "js-cookie";
export default {
  data() {
    return {
      pictureUrl: '',
      dialogImageUrl: '',
      dialogVisible: false,
      disabled: false,
      textarea: '',
      dialogFormVisible: false,
      formLabelWidth: '140px',
      form: {
        name: '',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: '',
      },
      username: "yjy",
      url: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
      imageUrl: '',
      posts: [],
      liked: false,
      comment: "",
      showCommentDialog: false,
    };
  },
  mounted() {
    this.getTweetList();
  },
  methods: {
    getCurrentTime(){
      //获取系统当前时间函数
      const now = new Date();
      const year = now.getFullYear();
      const month = String(now.getMonth() + 1).padStart(2, "0");
      const day = String(now.getDate()).padStart(2, "0");
      const hours = String(now.getHours()).padStart(2, "0");
      const minutes = String(now.getMinutes()).padStart(2, "0");
      const seconds = String(now.getSeconds()).padStart(2, "0");
      return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
    },
    getTweetList() {
      axios.get("/api/TweetList")
          .then(response => {
            const tweetList = response.data.data;
            console.log(tweetList);
            const postsitems =tweetList.map(item => ({
              id: item.id,
              content: item.content,
              author: item.username,
              publishTime: item.time,
              authorAvatar:new URL('/src/img/baga.jpg',import.meta.url).href,
              comments: item.commentList.map(comment => ({
                id: comment.id,
                author: comment.author,
                commentTime: comment.commentTime,
                content: comment.content
              }))
            }));
            this.posts = postsitems;
          })
          .catch(error => {
            console.error("获取推文列表失败:", error);
          });
    },
    handleSubmit() {
      //提交按钮
      console.log("this.dialogImageUrl: ", this.dialogImageUrl);
      console.log("textarea:", this.textarea);
      // 创建 FormData 对象
      const formData = new FormData();
      // 添加文本数据到 FormData
      formData.append('text', this.textarea);
      // 添加图片文件到 FormData
      formData.append('image', this.selectedImageFile);
      // 发送 POST 请求
      axios.post('/api/endpoint', formData)
          .then(response => {
            // 请求成功的处理逻辑
          })
          .catch(error => {
            // 请求失败的处理逻辑
          });
      dialogFormVisible.value = false
    },
    handleRemove(file) {
      //删除按钮
      console.log(file.url);
      this.pictureUrl = file.url;
      console.log(this.pictureUrl);
      this.dialogVisible = false;
    },
    handlePictureCardPreview(file) {
      //放大按钮
      this.dialogImageUrl = file.url;
      console.log("url:", this.dialogImageUrl);
      this.dialogVisible = true;
    },
    handleDownload(file) {
      //下载按钮
      console.log(file);
    },
    likePost(post) {
      if (!post.liked) {
        post.likes++;
        post.liked = true;
      } else {
        post.likes--;
        post.liked = false;
      }

      // 调用接口更新后端数据，示例代码：
      axios.post(`/api/likePost/${post.id}`)
          .then(response => {
            // 处理更新成功的逻辑
          })
          .catch(error => {
            console.error("更新点赞信息失败:", error);
          });
    },
    openCommentDialog(postId) {
      // 存储postId到组件数据属性中
      this.selectedPostId = postId;

      // 根据postId找到对应的帖子信息，示例代码：
      const post = this.posts.find(p => p.id === postId);
      console.log("对应的postid",post.id);
      if (post) {
        this.showCommentDialog = true;
        // 其他逻辑
        //将这里的post.id传给submitComment
      }
    },
    closeCommentDialog() {
      this.showCommentDialog = false;
    },
    submitComment() {
      const userId = Cookies.get('userid');
      const postId = this.selectedPostId; // 读取存储的postId
      // 处理提交评论的逻辑
      console.log("提交评论：" + this.comment);
      // console.log("postId:",this.posts.map(item =>({
      //   id:item.id,
      // })));
      console.log("postId:",postId);
      // 调用接口提交评论到后端，示例代码：
      axios.post("/api/CreateDisscusion", {
        id: postId,
        observer: userId,
        content: this.comment,
        time: this.getCurrentTime(),
      })
          .then(response => {
            // 处理提交成功的逻辑
            console.log(response);
            // 更新评论列表等
            this.comment = "";
            this.closeCommentDialog();
          })
          .catch(error => {
            console.error("提交评论失败:", error);
          });
    },
  },
};
</script>

<style scoped>
.avatar-uploader .avatar {
  width: 178px;
  height: 178px;
  display: block;
}
.whole {
  margin:-20px;
  padding:0;
}

.home-page {
  left: 0px;
  width: 100%;
}

.post-card {
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  border-radius: 3vh;
  margin: 20px;
  padding: 20px;
  width: 50%;
  max-height: 500px;
  margin: 0 auto;
  background-color: #dce1e5;
}

.comment-dialog {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 8px;
  background-color: #f9f9f9;
}

.likeman {
  position: relative;
  left: 30px;
}

.custom-button {
  flex: 1;
  background-color: #ffcc00;
  color: #fff;
  border: none;
  border-radius: 4px;
  padding: 10px 20px;
  font-size: 16px;
  cursor: pointer;
  transition: background-color 0.3s;
  margin-right: 10px;
}

.button-container {
  display: flex;
  justify-content: space-between;
}

.user-avatar {
  width: 30px;
  height: 30px;
  border-radius: 50%;
  margin-right: 5px;
}

.post-info1 {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}

.post-info-text {
  margin-left: 10px;
}

.post-content {
  font-size: 21px;
  /* margin-bottom: 8px; */
}

.comm-button,
.like-button {
  width: 32px;
  height: 30px;
  cursor: pointer;
}

.like-button1 {
  width: 20px;
  height: 20px;
  cursor: pointer;
}

.comment {
  /* position: relative; */
  padding: 1px;
}

.comment-content {
  font-size: 16px;
  margin-bottom: 5px;
}

.comment-metadata {
  /* position: absolute; */
  right: 0;
  color: #999999;
  font-size: 12px;
}

.post-actions {
  display: flex;
  justify-content: flex-end;
  /* margin-top: 10px; */
}
</style>
<style>
.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}
.el-button--text {
  margin-right: 15px;
}
.el-select {
  width: 300px;
}
.el-input {
  width: 300px;
}
.dialog-footer button:first-child {
  margin-right: 10px;
}
.header {
  /*background-color: brown;*/
  width: 100%;
  height: 30vh;
  margin: 0vh auto;
  background-image: url("/src/img/bg1.png");
  background-size: cover;
}
.demo-fit {
  display: flex;
  text-align: center;
  justify-content: space-between;
}
.demo-fit .block {
  flex: 1;
  display: flex;
  flex-direction: column;
  flex-grow: 0;
}
.demo-fit .title {
  margin-bottom: 10px;
  font-size: 14px;
  color: var(--el-text-color-secondary);
}
.block {
  position: relative;
  right: 200px;
  top: 200px;
  z-index: 1000;
}
</style>
