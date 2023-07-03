package com.zenq.cloud507b.service;

import com.zenq.cloud507b.mapper.NoiceMapper;
import com.zenq.cloud507b.po.Noice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoiceService {
    @Autowired
    NoiceMapper noiceMapper;


    public int listSelectNoice0(){
        return noiceMapper.selectNoice0List();
    }

    public int listSelectNoice1(){
        return noiceMapper.selectNoice1List();
    }

    public int listSelectNoice2(){
        return noiceMapper.selectNoice2List();
    }

    public int listSelectNoice3(){
        return noiceMapper.selectNoice3List();
    }

    public boolean updateNoice0(String noice_status0_value) {
        return noiceMapper.updatetNoice0List(noice_status0_value) > 0;
    }

    public boolean updateNoice1(String noice_status1_value) {
        return noiceMapper.updatetNoice1List(noice_status1_value) > 0;
    }

    public boolean updateNoice2(String noice_status2_value) {
        return noiceMapper.updatetNoice2List(noice_status2_value) > 0;
    }

    public boolean updateNoice3(String noice_status3_value) {
        return noiceMapper.updatetNoice3List(noice_status3_value) > 0;
    }

    public boolean updateNoicedate(String date) {
        return noiceMapper.updatetNoicedateList(date) > 0;
    }
    public String getDate(String date) {
        return noiceMapper.selectDatebyNoice(date);
    }
    public List<Noice> getTodayNoiceList(String date){
        return noiceMapper.selectTodayNoiceList(date);
    }
}
