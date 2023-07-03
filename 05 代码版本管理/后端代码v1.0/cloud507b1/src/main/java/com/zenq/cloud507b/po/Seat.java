package com.zenq.cloud507b.po;
import lombok.Data;

@Data
public class Seat {
    private String seat_id;
    private String seat_status;
    private String seat_userhost;
    private String seat_userguest;

    public Seat() {
    }

    public Seat(String seat_id, String seat_status, String seat_userhost, String seat_userguest) {
        this.seat_id = seat_id;
        this.seat_status = seat_status;
        this.seat_userhost = seat_userhost;
        this.seat_userguest = seat_userguest;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "seat_id='" + seat_id + '\'' +
                ", seat_status='" + seat_status + '\'' +
                ", seat_userhost='" + seat_userhost + '\'' +
                ", seat_userguest='" + seat_userguest + '\'' +
                '}';
    }
}
