package com.zenq.cloud507b.controller;

import com.zenq.cloud507b.config.common.ErrorMessage;
import com.zenq.cloud507b.config.common.SuccessMessage;
import com.zenq.cloud507b.service.AcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AcController {
    @Autowired
    AcService acService;

    @PostMapping("/api/gettemp")
    public Object gettemp(HttpServletRequest req) {
        Integer temper = Integer.parseInt(req.getParameter("temper"));
        System.out.println(temper);
        try {
            Integer population = acService.listAcPopulation(temper);
            System.out.println(population);
            population = population + 1;
            return new SuccessMessage<Boolean>("数据库有同样温度，人数增加成功", acService.updateAcPopulation(population, temper)).getMessage();
        } catch (NullPointerException e) {
            Integer population = 1;
            return new SuccessMessage<Boolean>("数据库没有同样温度，温度和人数增加成功", acService.addAcPopulation(temper, population)).getMessage();
        }
        catch (Exception e) {
            System.out.println(e);
            return new ErrorMessage("期望温度增加失败").getMessage();
        }
    }
}
