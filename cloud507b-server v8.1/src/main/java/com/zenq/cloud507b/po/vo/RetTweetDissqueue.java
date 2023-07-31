package com.zenq.cloud507b.po.vo;

import java.util.List;

public class RetTweetDissqueue {
    public String id;//帖子id
    public String content;//帖子内容
    public String userid;//发帖人id
    public String username;//发帖人姓名
    public String time;//帖子发布时间
    public String follower;//点赞人列表

    public static class Comment{
        public String id;//评论id 通过帖子id来查对应帖子下的评论
        public String author;//评论人名称
        public String content;//评论信息
        public String commentTime;//评论时间
    }

    public List<Comment> commentList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }
}
