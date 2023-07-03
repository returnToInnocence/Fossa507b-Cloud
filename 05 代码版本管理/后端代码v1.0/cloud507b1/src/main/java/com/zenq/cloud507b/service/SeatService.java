package com.zenq.cloud507b.service;

import com.zenq.cloud507b.mapper.SeatsMapper;
import com.zenq.cloud507b.po.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {
    @Autowired
    SeatsMapper seatsMapper;

    public List<Seat> listSeat(){
        return seatsMapper.selectAll();
    }
}
