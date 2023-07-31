package com.zenq.cloud507b.service;

import com.zenq.cloud507b.mapper.EquipmentMapper;
import com.zenq.cloud507b.mapper.UserMapper;
import com.zenq.cloud507b.po.Equipment;
import com.zenq.cloud507b.po.vo.RetEquipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EquipmentService {
    @Autowired
    EquipmentMapper equipmentMapper;
    @Autowired
    UserMapper userMapper;

    public boolean addEquipment(String userid, String text, String time, String status) {
        return equipmentMapper.insertEquipmentList(userid,text,time,status) > 0;
    }

    public List<RetEquipment> listEquipment(){
        List<RetEquipment> retEquipmentList = new ArrayList<>();
        RetEquipment retEquipmentqueue = new RetEquipment();
        for(Equipment equipment : equipmentMapper.selectEquipmentList()){
            retEquipmentqueue.username = userMapper.selectUserNameByID(equipment.getUserid());
            retEquipmentqueue.userid = equipment.getUserid();
            retEquipmentqueue.time = equipment.getTime();
            retEquipmentqueue.text = equipment.getText();
            retEquipmentqueue.status = equipment.getStatus();
            retEquipmentList.add(retEquipmentqueue);
            retEquipmentqueue = new RetEquipment();
        }
        return retEquipmentList;
    }

    public int listEquipmentCount(){
        return equipmentMapper.selectEquipmentCountList();
    }

    public String listEquipmentEmail(){
        return equipmentMapper.selectRespemailList();
    }

    public int deleteListEquipment(String userid){
        return equipmentMapper.deleteEquipmentList(userid);
    }
}
