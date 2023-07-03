package com.zenq.cloud507b.controller;

import com.zenq.cloud507b.config.common.ErrorMessage;
import com.zenq.cloud507b.config.common.SuccessMessage;
import com.zenq.cloud507b.service.NoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class NoiceController {
    @Autowired
    NoiceService noiceService;

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
}
