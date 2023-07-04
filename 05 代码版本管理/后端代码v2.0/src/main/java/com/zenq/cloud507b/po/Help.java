package com.zenq.cloud507b.po;

public class Help {
    private String userid;
    private String content;
    private String time;

    public String getUserid() {
        return userid;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }

    public Help(String userid, String content, String time){
        this.userid = String.valueOf(userid);
        this.content = String.valueOf(content);
        this.time = String.valueOf(time);
    }

    @Override
    public String toString() {
        return "Help{" +
                "userid='" + userid + '\'' +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
