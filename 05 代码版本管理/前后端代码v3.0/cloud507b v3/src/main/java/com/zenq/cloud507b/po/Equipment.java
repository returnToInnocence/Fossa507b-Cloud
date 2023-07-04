package com.zenq.cloud507b.po;

public class Equipment {
    private String userid;
    private String text;
    private String time;
    private String status;

    public String getUserid() {
        return userid;
    }

    public String getText() {
        return text;
    }

    public String getTime() {
        return time;
    }

    public String getStatus() {
        return status;
    }

    public Equipment(String userid, String text, String time, String status){
        this.userid = String.valueOf(userid);
        this.text = String.valueOf(text);
        this.time = String.valueOf(time);
        this.status = String.valueOf(status);
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "userid='" + userid + '\'' +
                ", text='" + text + '\'' +
                ", time='" + time + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
