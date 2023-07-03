package com.zenq.cloud507b.controller;

import com.zenq.cloud507b.config.common.ErrorMessage;
import com.zenq.cloud507b.config.common.SuccessMessage;
import com.zenq.cloud507b.service.HelpService;
import com.zenq.cloud507b.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HelpController {
    @Autowired
    HelpService helpService;
    @Autowired
    private MailService mailService;

    @PostMapping("/api/inhelp")
    public Object inhelp(HttpServletRequest req) {
        String userid = req.getParameter("userid");
        String content = req.getParameter("content");
        String time = req.getParameter("time");
        try {
            return new SuccessMessage<Boolean>("求助信息增加成功", helpService.addHelp(userid,content,time)).getMessage();
        }
        catch (Exception e) {
            System.out.println(e);
            return new ErrorMessage("求助信息增加失败").getMessage();
        }
    }

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