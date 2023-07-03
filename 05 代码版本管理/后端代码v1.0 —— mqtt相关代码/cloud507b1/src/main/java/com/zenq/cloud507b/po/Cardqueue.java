package com.zenq.cloud507b.po;

import lombok.Data;

@Data
public class Cardqueue {
    private String userid;

    public String getCardqueue() {
        return userid;
    }

    public Cardqueue(String userid){
        this.userid = String.valueOf(userid);
    }

    @Override
    public String toString() {
        return "Cardqueue{" +
                "userid=" + userid +
                "}";
    }
}
