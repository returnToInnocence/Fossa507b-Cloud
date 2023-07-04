package com.zenq.cloud507b.controller;

import com.zenq.cloud507b.config.common.ErrorMessage;
import com.zenq.cloud507b.config.common.SuccessMessage;
import com.zenq.cloud507b.service.EquipmentService;
import com.zenq.cloud507b.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class EquipmentController {
    @Autowired
    EquipmentService equipmentService;
    @Autowired
    private MailService mailService;

//    添加设备,根据紧急和不紧急进行处理（不紧急阙值到三就发送）
    @PostMapping("/api/inequipment")
    public Object inEquipment(HttpServletRequest req) {
        try {
            String userid = req.getParameter("userid");
            System.out.println(userid);
            String text = req.getParameter("text");
            System.out.println(text);
            String time = req.getParameter("time");
            System.out.println(time);
            String status = req.getParameter("status");
            System.out.println(status);
            String flag = "0";
            String equipmentemail = equipmentService.listEquipmentEmail();
            Integer equipmentcount = equipmentService.listEquipmentCount();
            if (status.equals(flag)){
                String equipmentcontent = " 老师您好，现收到紧急报修申请，详情如下：\n" + userid + "\t" + text + "\t" + time + "\t";
                mailService.sendSimpleMail(equipmentemail, "发送邮件测试", equipmentcontent);
                return new SuccessMessage<Boolean>("紧急设备添加成功，发送邮件成功", equipmentService.addEquipment(userid,text,time,status)).getMessage();
            }
            else{
                System.out.println(equipmentcount);
                if (equipmentcount >= 2) {
                    equipmentService.addEquipment(userid, text, time, status);
                    String date = String.valueOf(equipmentService.listEquipment());
                    System.out.println(date);
                    Pattern pattern = Pattern.compile("userid='(.*?)', text='(.*?)', time='(.*?)'");
                    Matcher matcher = pattern.matcher(date);
                    String date2 = null;
                    while (matcher.find()) {
                        String userid1 = matcher.group(1);
                        String text1 = matcher.group(2);
                        String time1 = matcher.group(3);
                        date2 = date2 + "\n" + userid1 + "\t" + text1 + "\t" + time1 + "\t";
                    }
                    String output = date2.replace("null", "");
                    Integer equipmentcount1 = equipmentcount + 1;
                    String equipmentcontent = "老师您好，现收到报修申请共" + equipmentcount1 + "条，罗列如下：\n" + output;
                    mailService.sendSimpleMail(equipmentemail, "发送邮件测试", equipmentcontent);
                    return new SuccessMessage<Boolean>("不紧急设备添加成功，发送邮件成功").getMessage();
                }
                else {
                    return new SuccessMessage<Boolean>("不紧急设备添加成功，不需要发送邮件", equipmentService.addEquipment(userid, text, time, status)).getMessage();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            return new ErrorMessage("设备添加失败").getMessage();
        }
    }

    //    负责人点击取消按钮
    @PostMapping("/api/delequipment")
    public Object delEquipment() {
        try {
            return new SuccessMessage<Integer>("取消紧急设备成功", equipmentService.deleteListEquipment()).getMessage();
        } catch (Exception e) {
            System.out.println(e);
            return new ErrorMessage("取消紧急设备失败").getMessage();
        }
    }
}
