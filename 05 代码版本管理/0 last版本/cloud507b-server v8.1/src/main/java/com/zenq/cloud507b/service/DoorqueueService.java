package com.zenq.cloud507b.service;

import com.zenq.cloud507b.mapper.DoorqueueMapper;
import com.zenq.cloud507b.mapper.UserMapper;
import com.zenq.cloud507b.po.Doorqueue;
import com.zenq.cloud507b.po.vo.RetDoorqueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoorqueueService {
    @Autowired
    DoorqueueMapper doorqueueMapper;
    @Autowired
    UserMapper userMapper;

    public boolean deleteListDoorqueue(String userid){
        return doorqueueMapper.deleteDoorqueueList(userid) > 0;
    }

    public boolean addDoorqueue(String userid) {
        return doorqueueMapper.insertDoorqueueList(userid) > 0;
    }

    public List<RetDoorqueue> listDoor() {
        List<RetDoorqueue> retDoorqueueList = new ArrayList<>();
        RetDoorqueue retDoorqueue = new RetDoorqueue();
        for (Doorqueue doorqueue : doorqueueMapper.selectDoorList()){
            retDoorqueue.username = userMapper.selectUserNameByID(doorqueue.getUserid());
            retDoorqueue.userid = doorqueue.getUserid();
            retDoorqueueList.add(retDoorqueue);
            retDoorqueue = new RetDoorqueue();
        }
        return retDoorqueueList;
    }
}
