package com.zenq.cloud507b.controller;

import com.zenq.cloud507b.config.common.ErrorMessage;
import com.zenq.cloud507b.config.common.SuccessMessage;
import com.zenq.cloud507b.po.Doorqueue;
import com.zenq.cloud507b.po.vo.RetDoorqueue;
import com.zenq.cloud507b.service.DoorqueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "*")
@RestController
public class DoorqueueController {
    @Autowired
    DoorqueueService doorqueueService;

    @GetMapping("/api/doorqueue")
    public List<RetDoorqueue> getListDoor(){
        System.out.println(doorqueueService.listDoor());
        return doorqueueService.listDoor();
    }

    @PostMapping("/api/indoorqueue")
    public Object inDoorqueue(@RequestBody Map<String, Object> requestData) {
        try {
            String userid = (String) requestData.get("id");
            System.out.println(userid);
            return new SuccessMessage<Boolean>("帮留门信息添加成功", doorqueueService.addDoorqueue(userid)).getMessage();
        } catch (Exception e) {
            System.out.println(e);
            return new ErrorMessage("帮留门信息添加失败").getMessage();
        }
    }

    @PostMapping("/api/deldoorqueue")
    public Object delDoorqueue(@RequestBody Map<String, Object> requestData) {
        try {
            String userid = (String) requestData.get("id");
            System.out.println(userid);
            if(doorqueueService.deleteListDoorqueue(userid)){
                return new SuccessMessage<>("取消帮留门信息成功").getMessage();
            }
                return new SuccessMessage<>("取消帮留门信息失败").getMessage();
        } catch (Exception e) {
            System.out.println(e);
            return new ErrorMessage("后端接口异常").getMessage();
        }
    }
}
