package com.zenq.cloud507b.po;

public class AcTemExp {
    private String date;
    private Integer value;
    private Integer ExpTem;

    public String getDate() {
        return date;
    }

    public Integer getValue() {
        return value;
    }

    public Integer getExpTem() {
        return ExpTem;
    }

    public AcTemExp(String date, Integer value, Integer ExpTem){
        this.date = String.valueOf(date);
        this.value = value;
        this.ExpTem = ExpTem;
    }

    @Override
    public String toString() {
        return "AcTemExp{" +
                "date='" + date + '\'' +
                ", value=" + value +
                ", ExpTem=" + ExpTem +
                '}';
    }
}
