package com.zenq.cloud507b.po;

public class Cardqueue {
    private String userid;

    private String date;

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

    @Override
    public String toString() {
        return "Cardqueue{" +
                "userid='" + userid + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public Cardqueue(String userid){
        this.userid = String.valueOf(userid);
    }

}
