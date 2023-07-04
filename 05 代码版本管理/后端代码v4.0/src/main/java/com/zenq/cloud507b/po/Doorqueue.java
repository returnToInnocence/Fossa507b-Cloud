package com.zenq.cloud507b.po;

public class Doorqueue {
    private String userid;

    public String getCardqueue() {
        return userid;
    }

    public Doorqueue(String userid){
        this.userid = String.valueOf(userid);
    }

    @Override
    public String toString() {
        return "Doorqueue{" +
                "userid=" + userid +
                "}";
    }
}
