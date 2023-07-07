package com.zenq.cloud507b.po.vo;

public class RetCardqueue {
    public String userid;
    public String date;
    public String username;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "RetCardqueue{" +
                "userid='" + userid + '\'' +
                ", date='" + date + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
