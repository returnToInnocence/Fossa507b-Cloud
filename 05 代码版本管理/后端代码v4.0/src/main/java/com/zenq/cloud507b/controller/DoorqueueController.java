package com.zenq.cloud507b.controller;

import com.zenq.cloud507b.config.common.ErrorMessage;
import com.zenq.cloud507b.config.common.SuccessMessage;
import com.zenq.cloud507b.service.DoorqueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class DoorqueueController {
    @Autowired
    DoorqueueService doorqueueService;

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/api/indoorqueue")
    public Object inDoorqueue(HttpServletRequest req) {
        try {
            String userid = req.getParameter("userid");
            System.out.println(userid);
            return new SuccessMessage<Boolean>("帮留门信息添加成功", doorqueueService.addDoorqueue(userid)).getMessage();
        } catch (Exception e) {
            System.out.println(e);
            return new ErrorMessage("帮留门信息添加失败").getMessage();
        }
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/api/deldoorqueue")
    public Object delDoorqueue(HttpServletRequest req) {
        try {
            String userid = req.getParameter("userid");
            System.out.println(userid);
            return new SuccessMessage<Boolean>("取消帮留门信息成功", doorqueueService.deleteListDoorqueue(userid)).getMessage();
        } catch (Exception e) {
            System.out.println(e);
            return new ErrorMessage("取消帮留门信息失败").getMessage();
        }
    }
}
