package com.zenq.cloud507b.po;

public class Seat {
    private String seatid;
    private String seatstatus;
    private String seatuserhost;
    private String seatuserguest;

    public Seat() {
    }

    public Seat(String seatid, String seatstatus, String seatuserhost, String seatuserguest) {
        this.seatid = seatid;
        this.seatstatus = seatstatus;
        this.seatuserhost = seatuserhost;
        this.seatuserguest = seatuserguest;
    }

    public String getSeatid() {
        return seatid;
    }

    public void setSeatid(String seatid) {
        this.seatid = seatid;
    }

    public String getSeatstatus() {
        return seatstatus;
    }

    public void setSeatstatus(String seatstatus) {
        this.seatstatus = seatstatus;
    }

    public String getSeatuserhost() {
        return seatuserhost;
    }

    public void setSeatuserhost(String seatuserhost) {
        this.seatuserhost = seatuserhost;
    }

    public String getSeatuserguest() {
        return seatuserguest;
    }

    public void setSeatuserguest(String seatuserguest) {
        this.seatuserguest = seatuserguest;
    }
}
