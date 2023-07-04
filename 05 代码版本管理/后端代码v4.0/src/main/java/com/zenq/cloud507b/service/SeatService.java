package com.zenq.cloud507b.service;

import com.zenq.cloud507b.mapper.SeatMapper;
import com.zenq.cloud507b.po.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {
    @Autowired
    SeatMapper seatMapper;

    public List<Seat> seatList(){
        return seatMapper.selectListSeat();
    }

    public List<Seat> updateSeat(String seatstatus, String seatuserguest){
        return seatMapper.updateSeat(seatstatus, seatuserguest);
    }

    public String selectStatusbyid(String seatid){
        return seatMapper.selectStatusbyID(seatid);
    }

    public String selectguest(String seatid) {
        return seatMapper.selectGuestbyID(seatid);
    }

    public List<Integer> selectStatus() {
        return seatMapper.selectListStatus();
    }


    public Object setSeatUsedValue(int sum, String date) {
        return seatMapper.setSeatUsedValue(sum, date);
    }
}
