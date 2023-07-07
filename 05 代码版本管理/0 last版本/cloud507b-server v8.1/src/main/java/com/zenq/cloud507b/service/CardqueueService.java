package com.zenq.cloud507b.service;

import com.zenq.cloud507b.mapper.CardqueueMapper;
import com.zenq.cloud507b.mapper.UserMapper;
import com.zenq.cloud507b.po.Cardqueue;
import com.zenq.cloud507b.po.vo.RetCardqueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardqueueService {
    @Autowired
    CardqueueMapper cardqueueMapper;

    @Autowired
    UserMapper userMapper;

    public List<RetCardqueue> listCardqueue(){
        List<RetCardqueue> retCardqueueList = new ArrayList<>();
        RetCardqueue retCardqueue = new RetCardqueue();
        for (Cardqueue cardqueue : cardqueueMapper.selectCardqueueList()){
            retCardqueue.username = userMapper.selectUserNameByID(cardqueue.getUserid());
            retCardqueue.userid = cardqueue.getUserid();
            retCardqueue.date = cardqueue.getDate();
            retCardqueueList.add(retCardqueue);
            retCardqueue = new RetCardqueue();
        }
        return retCardqueueList;
    }

    public String selectCardDate(String userid){
        return cardqueueMapper.selectCardqueueDate(userid);
    }

    public int listCardqueueCount(){
        return cardqueueMapper.selectCardqueueCountList();
    }

    public int deleteListCardqueue(){
        return cardqueueMapper.deleteCardqueueList();
    }

    public boolean addCardqueue(String userid, String date) {
        return cardqueueMapper.insertCardqueueList(userid, date) > 0;
    }

    public String listCardEmail(){
        return cardqueueMapper.selectCardRespemailList();
    }
}
