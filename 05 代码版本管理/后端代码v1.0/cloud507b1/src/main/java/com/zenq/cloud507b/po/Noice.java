package com.zenq.cloud507b.po;

import lombok.Data;

@Data
public class Noice {
    private String noice_status0_value;
    private String noice_status1_value;
    private String noice_status2_value;
    private String noice_status3_value;
    private String data;

    public String getNoice_status0_value() {
        return noice_status0_value;
    }

    public String getNoice_status1_value() {
        return noice_status1_value;
    }

    public String getNoice_status2_value() {
        return noice_status2_value;
    }

    public String getNoice_status3_value() {
        return noice_status3_value;
    }

    public String getData() {
        return data;
    }

    public Noice(String noice_status0_value, String noice_status1_value, String noice_status2_value, String noice_status3_value, String data){
        this.noice_status0_value = String.valueOf(noice_status0_value);
        this.noice_status1_value = String.valueOf(noice_status1_value);
        this.noice_status2_value = String.valueOf(noice_status2_value);
        this.noice_status3_value = String.valueOf(noice_status3_value);
        this.data = String.valueOf(data);
    }

    @Override
    public String toString() {
        return "Noice{" +
                "noice_status0_value='" + noice_status0_value + '\'' +
                ", noice_status1_value='" + noice_status1_value + '\'' +
                ", noice_status2_value='" + noice_status2_value + '\'' +
                ", noice_status3_value='" + noice_status3_value + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
