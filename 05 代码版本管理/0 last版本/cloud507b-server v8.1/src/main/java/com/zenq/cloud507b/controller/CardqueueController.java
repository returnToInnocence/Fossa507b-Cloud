package com.zenq.cloud507b.controller;

import com.zenq.cloud507b.config.common.ErrorMessage;
import com.zenq.cloud507b.config.common.SuccessMessage;
import com.zenq.cloud507b.po.Cardqueue;
import com.zenq.cloud507b.po.vo.RetCardqueue;
import com.zenq.cloud507b.service.CardqueueService;
import com.zenq.cloud507b.service.MailService;
import com.zenq.cloud507b.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@CrossOrigin(origins = "*")
@RestController
public class CardqueueController {
    @Autowired
    CardqueueService cardqueueService;
    @Autowired
    MailService mailService;
    @Autowired
    UserService userService;

    @GetMapping("/api/cardqueue")
    public List<RetCardqueue> getListCardqueue(){
        System.out.println(cardqueueService.listCardqueue());
        return cardqueueService.listCardqueue();
    }

//    用户点击录卡排队按钮，前端将获取到的userid发送给后端，
//    后端存入数据库，同时判断当前数据库用户数量再决定是否发邮件.
    @PostMapping("/api/incardqueue")
    public Object inCardqueue(@RequestBody Map<String, Object> requestData) {
        String userid = (String) requestData.get("userid");
        String currentTime = (String) requestData.get("currentTime");
        try {
            String cardemail = cardqueueService.listCardEmail();
            System.out.println("从前端返回的id:"+userid);
            System.out.println("从前端返回的时间："+currentTime);
            int cardqueuecount = cardqueueService.listCardqueueCount();
            System.out.println("当前排队人数："+cardqueuecount);
            if (cardqueuecount >= 4) {
                cardqueueService.addCardqueue(userid, currentTime);
                System.out.println("查询"+cardqueueService.selectCardDate(userid));
                String date = String.valueOf(cardqueueService.selectCardDate(userid));
                System.out.println("转换后"+date);
                Pattern pattern = Pattern.compile("userid=(\\d+)");
                Matcher matcher = pattern.matcher(date);
                String date2 = "";
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
                return new SuccessMessage<Boolean>("目前不需要发送邮件", cardqueueService.addCardqueue(userid, currentTime)).getMessage();
            }
        } catch (Exception e) {
            System.out.println(e);
            return new ErrorMessage("录卡排队失败").getMessage();
        }
    }

//    负责人点击取消按钮
    @PostMapping("/api/delcardqueue")
    public Object delCardqueue(@RequestBody Map<String, Object> requestData) {
        String date = (String) requestData.get("date");//录卡时间
        String address = (String) requestData.get("address");//录卡地点
        try {
            List<RetCardqueue> cardqueueList = cardqueueService.listCardqueue();
            Iterator<RetCardqueue> iterator = cardqueueList.iterator();
            while (iterator.hasNext()) {
                RetCardqueue retCardqueue = iterator.next();
                String userid = retCardqueue.getUserid();
                String username = retCardqueue.getUsername();
                String cardcontent = username + "同学您好，你的录卡申请已被老师接收，详情如下：\n"
                        + "录卡时间为：" + date + "\t" + "录卡地点为：" + address + "\n";
                System.out.println(cardcontent);
                mailService.sendSimpleMail(userService.selectUsermail(userid), "发送邮件测试", cardcontent);
            }
            cardqueueService.deleteListCardqueue();
            return new SuccessMessage<Integer>("取消录卡排队成功").getMessage();
        } catch (Exception e) {
            System.out.println(e);
            return new ErrorMessage("取消录卡排队失败").getMessage();
        }
    }

}
