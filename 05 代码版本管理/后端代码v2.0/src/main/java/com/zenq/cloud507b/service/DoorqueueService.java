package com.zenq.cloud507b.service;

import com.zenq.cloud507b.mapper.DoorqueueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoorqueueService {
    @Autowired
    DoorqueueMapper doorqueueMapper;

    public boolean deleteListDoorqueue(String userid){
        return doorqueueMapper.deleteDoorqueueList(userid) > 0;
    }

    public boolean addDoorqueue(String userid) {
        return doorqueueMapper.insertDoorqueueList(userid) > 0;
    }
}
