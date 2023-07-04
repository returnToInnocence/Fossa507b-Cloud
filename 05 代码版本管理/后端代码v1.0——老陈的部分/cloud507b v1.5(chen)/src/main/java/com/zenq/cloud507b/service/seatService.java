package com.zenq.cloud507b.service;

import com.zenq.cloud507b.mapper.seatMapper;
import com.zenq.cloud507b.po.seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class seatService {
    @Autowired
    seatMapper seatMapper;

    public List<seat> seatList(){
        return seatMapper.selectListSeat();
    }
}
