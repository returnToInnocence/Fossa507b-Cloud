package com.zenq.cloud507b.service;

import com.zenq.cloud507b.mapper.EquipmentMapper;
import com.zenq.cloud507b.po.Cardqueue;
import com.zenq.cloud507b.po.Equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentService {
    @Autowired
    EquipmentMapper equipmentMapper;

    public boolean addEquipment(String userid, String text, String time, String status) {
        return equipmentMapper.insertEquipmentList(userid,text,time,status) > 0;
    }

    public List<Equipment> listEquipment(){
        return equipmentMapper.selectEquipmentList();
    }

    public int listEquipmentCount(){
        return equipmentMapper.selectEquipmentCountList();
    }

    public String listEquipmentEmail(){
        return equipmentMapper.selectRespemailList();
    }

    public int deleteListEquipment(){
        return equipmentMapper.deleteEquipmentList();
    }
}
