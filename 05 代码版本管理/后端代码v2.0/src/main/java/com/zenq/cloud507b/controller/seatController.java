package com.zenq.cloud507b.controller;

import com.zenq.cloud507b.po.seat;
import com.zenq.cloud507b.service.seatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class seatController {
    @Autowired
    seatService seatService;

    @GetMapping("/api/getseat")
    public List<seat> selectseat(HttpServletRequest req){
        System.out.println(seatService.seatList());
        return seatService.seatList();
    }
}
