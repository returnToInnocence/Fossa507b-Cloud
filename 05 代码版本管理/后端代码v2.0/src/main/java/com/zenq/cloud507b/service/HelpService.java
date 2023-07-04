package com.zenq.cloud507b.service;

import com.zenq.cloud507b.mapper.HelpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelpService {
    @Autowired
    HelpMapper helpMapper;

    public boolean addHelp(String userid, String content, String time) {
        return helpMapper.insertHelpList(userid,content,time) > 0;
    }

    public String listUseremailCount(String userid){
        return helpMapper.selectUsermailList(userid);
    }
}
