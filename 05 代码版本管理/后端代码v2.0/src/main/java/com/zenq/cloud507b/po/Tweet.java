package com.zenq.cloud507b.po;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * 
 * </p>
 *
 * @author chenxi
 * @since 2023-07-01
 */
public class Tweet implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String title;

    private String content;

    private String userid;

    private String time;

    private String img;

    private String follower;

    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Tweet(String id, String title, String content, String userid, String time, String img, String type) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userid = userid;
        this.time = time;
        this.img = img;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Tweet{" +
            "id=" + id +
            ", title=" + title +
            ", content=" + content +
            ", userid=" + userid +
            ", time=" + time +
            ", img=" + img +
            ", follower=" + follower +
            ", type=" + type +
        "}";
    }
}
