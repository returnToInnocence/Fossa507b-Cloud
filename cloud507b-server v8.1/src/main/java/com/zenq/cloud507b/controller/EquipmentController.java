package com.zenq.cloud507b.controller;

import com.zenq.cloud507b.config.common.ErrorMessage;
import com.zenq.cloud507b.config.common.SuccessMessage;
import com.zenq.cloud507b.po.Equipment;
import com.zenq.cloud507b.po.vo.RetEquipment;
import com.zenq.cloud507b.service.EquipmentService;
import com.zenq.cloud507b.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@CrossOrigin(origins = "*")
@RestController
public class EquipmentController {
    @Autowired
    EquipmentService equipmentService;
    @Autowired
    private MailService mailService;

    @GetMapping("/api/equipmentqueue")
    public List<RetEquipment> getListEquipment(){
        System.out.println(equipmentService.listEquipment());
        return equipmentService.listEquipment();
    }

    //添加设备,根据紧急和不紧急进行处理（不紧急阙值到三就发送）
    @PostMapping("/api/inequipment")
    public Object inEquipment(@RequestBody Map<String, Object> requestData) {
        String userid = (String) requestData.get("userid");
        String text = (String) requestData.get("equipment");
        String time = (String) requestData.get("currentTime");
        String status = (String) requestData.get("quick");
        try {
            //status为1是紧急设备
            String flag = "1";
            String equipmentemail = equipmentService.listEquipmentEmail();
            Integer equipmentcount = equipmentService.listEquipmentCount();
            if (status.equals(flag)){
                String equipmentcontent = " 老师您好，现收到紧急报修申请，详情如下：\n" + userid + "\t" + text + "\t" + time + "\t";
                mailService.sendSimpleMail(equipmentemail, "发送邮件测试", equipmentcontent);
                return new SuccessMessage<Boolean>("紧急设备添加成功，发送邮件成功", equipmentService.addEquipment(userid,text,time,status)).getMessage();
            }
            else{
                System.out.println("当前设备数量："+equipmentcount);
                if (equipmentcount >= 3) {
                    //如果设备大于2则发送邮件
                    equipmentService.addEquipment(userid, text, time, status);
                    String date = String.valueOf(equipmentService.listEquipment());
                    Pattern pattern = Pattern.compile("userid='(.*?)', text='(.*?)', time='(.*?)'");
                    Matcher matcher = pattern.matcher(date);
                    String date2 = "";
                    while (matcher.find()) {
                        String userid1 = matcher.group(1);
                        String text1 = matcher.group(2);
                        String time1 = matcher.group(3);
                        date2 = date2 + "\n" + userid1 + "\t" + text1 + "\t" + time1 + "\t";
                    }
                    System.out.println(date2);
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

    //负责人点击取消按钮
    @PostMapping("/api/delequipment")
    public Object delEquipment(@RequestBody Map<String, Object> requestData) {
        String userid = (String) requestData.get("id");
        try {
            return new SuccessMessage<Integer>("删除报修设备成功", equipmentService.deleteListEquipment(userid)).getMessage();
        } catch (Exception e) {
            System.out.println(e);
            return new ErrorMessage("删除报修设备失败").getMessage();
        }
    }
}
