package com.zenq.cloud507b.controller;

import com.zenq.cloud507b.config.common.ErrorMessage;
import com.zenq.cloud507b.config.common.SuccessMessage;
import com.zenq.cloud507b.po.Seat;
import com.zenq.cloud507b.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class SeatController {
    @Autowired
    SeatService seatService;


    @GetMapping("/api/getseat")
    public Object selectseat(HttpServletRequest req){
        try {
            System.out.println("获取座位");
            //System.out.println(seatService.seatList());
            List<Seat> seatList = seatService.seatList();
            List<List<Seat>> rowColumnList = seatService.convertToRowsColumns(seatList, 5, 7);
            return new SuccessMessage<List<List<Seat>>>("获取座位信息成功",rowColumnList).getMessage();
        }catch (Exception e){
            System.out.println(e);
            return new ErrorMessage("获取座位信息失败").getMessage();
        }
    }

    @PostMapping("/api/updateseat")
    public Object updateseat(@RequestBody Map<String, Object> req){
        //前端点击座位进行信息更新（我要使用这个座位了）
        try {
//            String seatid = req.getParameter("seatid");
            String seatid = (String) req.get("seatid");
            String seatstatus = (String) req.get("seatstatus");
//            String seatstatus = req.getParameter("seatstatus"); //0为未使用，1为使用
            //String seatuserhost = req.getParameter("seatuserhost"); //座位拥有者，这个直接在数据库写就行了，前端不做更改。（长期录入）
//            String seatuserguest = req.getParameter("seatuserguest");
            String seatuserguest = (String) req.get("seatuserguest");
            String status = seatstatus;
            String guest = seatuserguest;
            if(status == "0" && guest == ""){
                //座位没人使用，申请使用这个座位
                return new SuccessMessage<>("申请座位成功",seatService.updateSeat(seatstatus,seatuserguest,seatid)).getMessage();
            }else{
                //申请释放这个座位
                return  new SuccessMessage<>("释放座位成功",seatService.updateSeat(status,seatuserguest,seatid)).getMessage();
            }
        }catch (Exception e){
            System.out.println(e);
            return new ErrorMessage("更新座位信息失败").getMessage();
        }
    }

    @GetMapping("/api/getseatrate")
    public Object getseatRate(HttpServletRequest req){
        try {
            LocalDate today = LocalDate.now();
            Integer MaxSeat = 40;//手动设置最大座位数量
            System.out.println(seatService.selectStatus());
            List<Integer> numbers = seatService.selectStatus();
            int sum = numbers.stream().mapToInt(Integer::intValue).sum();
            System.out.println("Sum: " + sum);
            double rate = sum/(double)MaxSeat;
            System.out.println(rate);
            return new SuccessMessage<>("获取最大上座率成功",seatService.setSeatUsedValue(sum, String.valueOf(today))).getMessage();
        }catch (Exception e){
            System.out.println(e);
            return new ErrorMessage("获取最大上座率失败").getMessage();
        }
    }
}
