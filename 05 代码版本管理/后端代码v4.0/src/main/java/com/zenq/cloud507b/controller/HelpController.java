package com.zenq.cloud507b.controller;

import com.zenq.cloud507b.config.common.ErrorMessage;
import com.zenq.cloud507b.config.common.SuccessMessage;
import com.zenq.cloud507b.po.Help;
import com.zenq.cloud507b.service.HelpService;
import com.zenq.cloud507b.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
public class HelpController {
    @Autowired
    HelpService helpService;
    @Autowired
    private MailService mailService;

    @CrossOrigin(origins = "*")
    @GetMapping("/api/helpList")
    public Object helpList() {
        try {
            return new SuccessMessage<List<Help>>("查询求助信息列表成功", helpService.helpList()).getMessage();
        }
        catch (Exception e) {
            System.out.println(e);
            return new ErrorMessage("查询求助信息列表失败").getMessage();
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/api/inhelp")
    public Object inhelp(@RequestBody Map<String, Object> requestData) {
        String userid = (String) requestData.get("userid");
        String currentTime = (String) requestData.get("currentTime");
        String description = (String) requestData.get("description");
        try {
            if(userid == "" || currentTime == "" || description == ""){
                System.out.println("参数不完整");
                return new ErrorMessage("参数不完整").getMessage();
            }else {
                System.out.println("参数完整");
                return new SuccessMessage<Boolean>("求助信息增加成功", helpService.addHelp(userid, description, currentTime)).getMessage();
            }
        }
        catch (Exception e) {
            System.out.println(e);
            return new ErrorMessage("求助信息增加失败").getMessage();
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/api/outhelp")
    public Object outhelp(HttpServletRequest req) {
        String inuserid = req.getParameter("inuserid");
        String outuserid = req.getParameter("outuserid");
        try {
            String inuseremail = String.valueOf(helpService.listUseremailCount(inuserid));
            String content = "学号为" + outuserid + "的同学已经帮你接任务啦~";
            mailService.sendSimpleMail(inuseremail, "发送邮件测试", content);
            return new SuccessMessage<Boolean>("接任务成功，发送邮件成功").getMessage();
        }
        catch (Exception e) {
            System.out.println(e);
            return new ErrorMessage("接任务失败").getMessage();
        }
    }
}