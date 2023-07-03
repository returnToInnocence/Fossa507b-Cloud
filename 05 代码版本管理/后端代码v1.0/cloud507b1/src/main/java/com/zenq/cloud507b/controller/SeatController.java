package com.zenq.cloud507b.controller;

import com.zenq.cloud507b.po.Seat;
import com.zenq.cloud507b.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SeatController {
    @Autowired
    SeatService seatService;

    @GetMapping("/api/testSeat")
    public List<Seat> testSeat(){
        System.out.println(seatService.listSeat());
        return seatService.listSeat();
    }
}
