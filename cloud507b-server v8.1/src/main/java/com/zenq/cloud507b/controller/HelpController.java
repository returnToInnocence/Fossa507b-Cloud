package com.zenq.cloud507b.controller;

import com.zenq.cloud507b.config.common.ErrorMessage;
import com.zenq.cloud507b.config.common.SuccessMessage;
import com.zenq.cloud507b.mapper.UserMapper;
import com.zenq.cloud507b.po.Help;
import com.zenq.cloud507b.service.HelpService;
import com.zenq.cloud507b.service.MailService;
import com.zenq.cloud507b.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "*")
@RestController
public class HelpController {
    @Autowired
    HelpService helpService;
    @Autowired
    private MailService mailService;

    @Autowired
    private UserService userService;


    @GetMapping("/api/selectUsermail")
    public Object selectUsermail(@RequestParam("id") String userid) {
        try {
            String usermail = userService.selectUsermail(userid);
            //System.out.println(usermail);
            return new SuccessMessage<>("查询邮件地址成功",userService.selectUsermail(userid)).getMessage();
        }
        catch (Exception e) {
            System.out.println(e);
            return new ErrorMessage("查询邮件地址失败").getMessage();
        }
    }

    @GetMapping("/api/helpList")
    public Object helpList() {
        try {
            System.out.println(helpService.helpList());
            return new SuccessMessage<List<Help>>("查询求助信息列表成功", helpService.helpList()).getMessage();
        }
        catch (Exception e) {
            System.out.println(e);
            return new ErrorMessage("查询求助信息列表失败").getMessage();
        }
    }

    @PostMapping("/api/inhelp")
    public Object inhelp(@RequestBody Map<String, Object> requestData) {
        String username = (String) requestData.get("username");
        String userid = (String) requestData.get("userid");
        String currentTime = (String) requestData.get("currentTime");
        String description = (String) requestData.get("description");
        try {
            if(userid == "" || currentTime == "" || description == ""){
                System.out.println("参数不完整");
                return new ErrorMessage("参数不完整").getMessage();
            }else {
                System.out.println("参数完整");
                return new SuccessMessage<Boolean>("求助信息增加成功", helpService.addHelp(username,userid, description, currentTime)).getMessage();
            }
        }
        catch (Exception e) {
            System.out.println(e);
            return new ErrorMessage("求助信息增加失败").getMessage();
        }
    }

    @PostMapping("/api/outhelp")
    public Object outhelp(@RequestBody Map<String, Object> requestData) {
        String inuserid = (String) requestData.get("inuserid");//被帮助人
        String outuserid = (String) requestData.get("outuserid");//帮助人
        String inuseremail = (String) requestData.get("contact");//被帮助人邮件地址
        String description = (String) requestData.get("description");//求助信息
        try {
            String content = "学号为" + outuserid + "的同学已经帮你接取" + '"' + description + '"' +"的任务啦~";
            helpService.deleteHelp(description);
            mailService.sendSimpleMail(inuseremail, "发送邮件测试", content);
            return new SuccessMessage<Boolean>("接任务成功，发送邮件成功").getMessage();
        }
        catch (Exception e) {
            System.out.println(e);
            return new ErrorMessage("接任务失败").getMessage();
        }
    }
}