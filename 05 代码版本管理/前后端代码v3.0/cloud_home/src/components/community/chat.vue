<template>
    <div id="app">
      <div class="container">
        <div class="row">
          <div class="col-md-3">
            <div class="panel panel-primary">
              <div class="panel-heading">
                <h3 class="panel-title">当前登录用户</h3>
              </div>
              <div class="panel-body">
                <div class="list-group">
                  <a href="#" class="list-group-item">你好，<span id="user">{{ user }}</span></a>
                  <a href="logout" class="list-group-item">退出</a>
                </div>
              </div>
            </div>
            <div class="panel panel-primary" id="online">
              <div class="panel-heading">
                <h3 class="panel-title">当前在线的其他用户</h3>
              </div>
              <div class="panel-body">
                <div class="list-group" id="users">
                  <a v-for="onlineUser in onlineUsers" :key="onlineUser" href="#" class="list-group-item">{{ onlineUser }}</a>
                </div>
              </div>
            </div>
            <div class="panel panel-primary">
              <div class="panel-heading">
                <h3 class="panel-title">群发系统广播</h3>
              </div>
              <div class="panel-body">
                <input type="text" class="form-control" v-model="broadcastMessage" /><br />
                <button @click="sendBroadcast" type="button" class="btn btn-primary">发送</button>
              </div>
            </div>
          </div>
          <div class="col-md-9">
            <div class="panel panel-primary">
              <div class="panel-heading">
                <h3 class="panel-title" id="talktitle"></h3>
              </div>
              <div class="panel-body">
                <div class="well" id="log-container" style="height: 400px; overflow-y: scroll">
                  <div v-for="(message, index) in messages" :key="index" class="bg-info">
                    <label class="text-danger">{{ message.from }}&nbsp;{{ message.date }}</label>
                    <div class="text-success">{{ message.text }}</div>
                  </div>
                </div>
                <input type="text" v-model="myInfo" class="form-control col-md-12" /><br />
                <button @click="sendMessage" type="button" class="btn btn-primary">发送</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import "../jsLib/bootstrap.min.css"
  export default {
    data() {
      return {
        user: '',
        onlineUsers: [],
        broadcastMessage: '',
        myInfo: '',
        messages: []
      };
    },
    mounted() {
      // 获取当前登录用户
      this.user = ''; // 根据实际情况获取用户数据
  
      // 建立WebSocket连接
      if ("WebSocket" in window) {
        const websocket = new WebSocket("ws://localhost:8080/webSocket/" + this.user);
  
        websocket.onmessage = (event) => {
          const data = JSON.parse(event.data);
          if (data.to === 0) {
            // 上线消息
            if (data.text !== this.user) {
              this.onlineUsers.push(data.text);
              alert(data.text + "上线了");
            }
          } else if (data.to === -2) {
            // 下线消息
            if (data.text !== this.user) {
              const index = this.onlineUsers.indexOf(data.text);
              if (index !== -1) {
                this.onlineUsers.splice(index, 1);
              }
              alert(data.text + "下线了");
            }
          } else {
            // 普通消息
            this.messages.push(data);
            this.scrollToBottom();
          }
        };
  
        // 获取在线用户列表
        fetch("/onlineusers?currentuser=" + this.user)
          .then(response => response.json())
          .then(data => {
            this.onlineUsers = data;
          });
      }
    },
    methods: {
      sendBroadcast() {
        const data = {
          from: this.user + "发布了全局广播",
          to: -1,
          text: this.broadcastMessage
        };
        // 发送广播消息
        // websocket.send(JSON.stringify(data));
      },
      sendMessage() {
        if (!this.myInfo || !this.toUser) {
          alert("请选择聊天对象");
          return;
        }
        const data = {
          from: this.user,
          to: this.toUser,
          text: this.myInfo
        };
        // 发送消息
        // websocket.send(JSON.stringify(data));
  
        this.messages.push({
          from: "我",
          date: new Date().format("yyyy-MM-dd hh:mm:ss"),
          text: this.myInfo
        });
        this.myInfo = '';
        this.scrollToBottom();
      },
      scrollToBottom() {
        this.$nextTick(() => {
          const div = document.getElementById("log-container");
          div.scrollTop = div.scrollHeight;
        });
      }
    }
  };
  </script>
  