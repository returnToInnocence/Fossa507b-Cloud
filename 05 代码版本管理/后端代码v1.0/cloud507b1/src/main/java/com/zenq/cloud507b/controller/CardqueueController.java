package com.zenq.cloud507b.controller;

import com.zenq.cloud507b.config.common.ErrorMessage;
import com.zenq.cloud507b.config.common.SuccessMessage;
import com.zenq.cloud507b.po.Cardqueue;
import com.zenq.cloud507b.po.User;
import com.zenq.cloud507b.service.CardqueueService;
import com.zenq.cloud507b.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class CardqueueController {
    @Autowired
    CardqueueService cardqueueService;
    @Autowired
    private MailService mailService;

    @GetMapping("/api/cardqueue")
    public List<Cardqueue> getListCardqueue(){
        System.out.println(cardqueueService.listCardqueue());
        return cardqueueService.listCardqueue();
    }

//    用户点击录卡排队按钮，前端将获取到的userid发送给后端，
//    后端存入数据库，同时判断当前数据库用户数量再决定是否发邮件
    @PostMapping("/api/incardqueue")
    public Object inCardqueue(HttpServletRequest req) {
        try {
            String cardemail = cardqueueService.listCardEmail();
            String userid = req.getParameter("userid");
            System.out.println(userid);
            int cardqueuecount = cardqueueService.listCardqueueCount();
            System.out.println(cardqueuecount);
            if (cardqueuecount >= 4) {
                cardqueueService.addCardqueue(userid);
                String date = String.valueOf(cardqueueService.listCardqueue());
                System.out.println(date);
                Pattern pattern = Pattern.compile("userid=(\\d+)");
                Matcher matcher = pattern.matcher(date);
                String date2 = null;
                while (matcher.find()) {
                    String userid1 = matcher.group(1);
                    date2 = date2 + "\n" + userid1 + "\n";
                }
                String output = date2.replace("null", "");
                Integer cardqueuecount1 = cardqueuecount + 1;
                String content = "老师您好，现收到录卡申请共" + cardqueuecount1 + "条，罗列如下：\n" + output;
                mailService.sendSimpleMail(cardemail, "发送邮件测试", content);
                return new SuccessMessage<Boolean>("录卡排队成功，发送邮件成功").getMessage();
            }
            else{
                return new SuccessMessage<Boolean>("目前不需要发送邮件", cardqueueService.addCardqueue(userid)).getMessage();
            }
        } catch (Exception e) {
            System.out.println(e);
            return new ErrorMessage("录卡排队失败").getMessage();
        }
    }

//    负责人点击取消按钮
    @PostMapping("/api/delcardqueue")
    public Object delCardqueue() {
        try {
            return new SuccessMessage<Integer>("取消录卡排队成功", cardqueueService.deleteListCardqueue()).getMessage();
        } catch (Exception e) {
            System.out.println(e);
            return new ErrorMessage("取消录卡排队失败").getMessage();
        }
    }

}
