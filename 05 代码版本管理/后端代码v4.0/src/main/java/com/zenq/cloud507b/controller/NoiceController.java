package com.zenq.cloud507b.controller;

import com.zenq.cloud507b.config.common.ErrorMessage;
import com.zenq.cloud507b.config.common.SuccessMessage;
import com.zenq.cloud507b.po.Noice;
import com.zenq.cloud507b.service.NoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

@RestController
public class NoiceController {
    @Autowired
    NoiceService noiceService;

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/api/innoice")
    public Object inNoice(HttpServletRequest req) {
        try {
            String judgement = req.getParameter("judgement");
            System.out.println(judgement);
            String noicedate = req.getParameter("noicedate");
            System.out.println(noicedate);
            if (judgement.equals("很吵")) {
                int noice_status0_value = noiceService.listSelectNoice0();
                noice_status0_value = noice_status0_value + 1;
                System.out.println(noice_status0_value);
                noiceService.updateNoicedate(noicedate);
                return new SuccessMessage<Boolean>("很吵评价增加成功", noiceService.updateNoice0(String.valueOf(noice_status0_value))).getMessage();
            } else if (judgement.equals("有点吵")) {
                int noice_status1_value = noiceService.listSelectNoice1();
                noice_status1_value = noice_status1_value + 1;
                System.out.println(noice_status1_value);
                noiceService.updateNoicedate(noicedate);
                return new SuccessMessage<Boolean>("有点吵评价增加成功", noiceService.updateNoice1(String.valueOf(noice_status1_value))).getMessage();
            } else if (judgement.equals("正常")) {
                int noice_status2_value = noiceService.listSelectNoice2();
                noice_status2_value = noice_status2_value + 1;
                System.out.println(noice_status2_value);
                noiceService.updateNoicedate(noicedate);
                return new SuccessMessage<Boolean>("正常评价增加成功", noiceService.updateNoice2(String.valueOf(noice_status2_value))).getMessage();
            } else if (judgement.equals("安静")) {
                int noice_status3_value = noiceService.listSelectNoice3();
                noice_status3_value = noice_status3_value + 1;
                System.out.println(noice_status3_value);
                noiceService.updateNoicedate(noicedate);
                return new SuccessMessage<Boolean>("安静评价增加成功", noiceService.updateNoice3(String.valueOf(noice_status3_value))).getMessage();
            }
            else {
                return new SuccessMessage<Boolean>("评价增加失败").getMessage();
            }
        } catch (Exception e) {
            System.out.println(e);
            return new ErrorMessage("评价增加失败").getMessage();
        }
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/api/getTodayNoiceList")
    public Object TodayNoice(HttpServletRequest req){
        try {
            LocalDate today = LocalDate.now();
            String date = noiceService.getDate(String.valueOf(today));
            System.out.println(date);
            //System.out.println(String.valueOf(today).getClass());
            System.out.println(date != null);
            if(date != null){
                //如果数据库中日期已经存在则执行noiceListbyDate(select)，若不存在则报错
                System.out.println("查询当天吵闹程度");
                return new SuccessMessage<List<Noice>>("获取当天所有吵闹程度成功",noiceService.getTodayNoiceList(date)).getMessage();
            }else {
                System.out.println("当天吵闹程度无数据");
                return new ErrorMessage("获取当天吵闹程度失败，无数据").getMessage();
            }
        }catch (Exception e){
            System.out.println(e);
            return new ErrorMessage("获取当天所有吵闹程度失败").getMessage();
        }
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/api/getNoiceList")
    public Object AllNoice(HttpServletRequest req){
        try {
            System.out.println("获取吵闹程度列表");
            return new SuccessMessage<List<Noice>>("获取吵闹程度列表成功",noiceService.getNoiceList()).getMessage();
        }catch (Exception e){
            System.out.println(e);
            return new ErrorMessage("获取吵闹程度列表失败").getMessage();
        }
    }
}
