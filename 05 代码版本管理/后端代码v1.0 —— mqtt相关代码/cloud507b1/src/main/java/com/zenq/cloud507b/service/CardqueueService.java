package com.zenq.cloud507b.service;

import com.zenq.cloud507b.mapper.CardqueueMapper;
import com.zenq.cloud507b.po.Cardqueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardqueueService {
    @Autowired
    CardqueueMapper cardqueueMapper;

    public List<Cardqueue> listCardqueue(){
        return cardqueueMapper.selectCardqueueList();
    }

    public int listCardqueueCount(){
        return cardqueueMapper.selectCardqueueCountList();
    }

    public int deleteListCardqueue(){
        return cardqueueMapper.deleteCardqueueList();
    }

    public boolean addCardqueue(String userid) {
        return cardqueueMapper.insertCardqueueList(userid) > 0;
    }
}
