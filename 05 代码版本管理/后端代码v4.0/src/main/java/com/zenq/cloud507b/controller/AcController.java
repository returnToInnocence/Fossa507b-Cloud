package com.zenq.cloud507b.controller;

import com.zenq.cloud507b.config.common.ErrorMessage;
import com.zenq.cloud507b.config.common.SuccessMessage;
import com.zenq.cloud507b.po.Ac;
import com.zenq.cloud507b.po.AcTemExp;
import com.zenq.cloud507b.service.AcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AcController {
    @Autowired
    AcService acService;

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/api/getAcExpTemp")
    public Object getAcExptemp(HttpServletRequest req){
        try {
            System.out.println(acService.selectAcExpTemper());
            return new SuccessMessage<List<AcTemExp>>("获取今日温度与今日期望温度成功",acService.selectAcExpTemper()).getMessage();
        }catch (Exception e){
            System.out.println(e);
            return new ErrorMessage("获取数据失败").getMessage();
        }
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/api/setTemp")
    public Object settemp(HttpServletRequest req) {
        System.out.print("temper:");
        Integer temper = Integer.parseInt(req.getParameter("temper"));
        System.out.println(temper);
        try {
            Integer population = acService.listAcPopulation(temper);
            //System.out.println(population);
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

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/api/setExpTemp")
    public Object setAvgExpTemp(){
        try {
            List<Integer> temperList = acService.listAcTemper();
            List<Integer> popList = acService.listPopulation();
            System.out.println(temperList);
            System.out.println(popList);
            double AvgExpTemp = 0;
            double sumPop = 0;
            for(int i = 0; i < popList.size(); i++){
                sumPop += popList.get(i);
            }
            for(int i = 0; i < temperList.size(); i++){
                double temp = popList.get(i)/sumPop;
                AvgExpTemp += temperList.get(i) * temp;
            }
            System.out.println("AvgExpTemp："+AvgExpTemp);
            System.out.println("人数总和为："+sumPop);
            int intvalue = Double.valueOf(AvgExpTemp).intValue();
            System.out.println("转换后的AvgExpTemp："+intvalue);
            //这个日期需要前端来传吗？
            //String date = "2023-07-01";
            LocalDate today = LocalDate.now();
            String date = String.valueOf(today);
            System.out.println(date);
            return new SuccessMessage<Boolean>("期望温度计算成功",acService.updateAcExpTem(intvalue,date)).getMessage();
        }catch (Exception e){
            System.out.println(e);
            return new ErrorMessage("期望温度计算失败").getMessage();
        }
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/api/setTodayTemp")
    public Object setTodayACTemp(HttpServletRequest req){
        try {
            LocalDate today = LocalDate.now();
            System.out.println(today);
            String date = acService.getDate(String.valueOf(today));
            System.out.println(date);
            System.out.println(date == null);
            if(date == null){
                //如果数据库中日期已经存在则执行update(addTodayAcTem)，若不存在则需要执行insert(setTodayAcTem)
                Integer todayTemper = 10;  //这里的空调温度需要从硬件获取
                System.out.println("添加空调温度");
                return new SuccessMessage<Boolean>("添加今日空调温度成功",acService.addTodayAcTem(String.valueOf(today), todayTemper)).getMessage();
            }else {
                Integer todayTemper = 10;  //这里的空调温度需要从硬件获取
                System.out.println("更新空调温度");
                return new SuccessMessage<Boolean>("更新今日空调温度成功",acService.setTodayAcTem(date,todayTemper)).getMessage();
            }
        }catch (Exception e){
            System.out.println(e);
            return new ErrorMessage("设置今日空调温度失败").getMessage();
        }
    }
}
