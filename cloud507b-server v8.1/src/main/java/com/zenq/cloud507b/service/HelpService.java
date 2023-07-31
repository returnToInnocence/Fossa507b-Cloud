package com.zenq.cloud507b.service;

import com.zenq.cloud507b.mapper.HelpMapper;
import com.zenq.cloud507b.po.Help;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HelpService {
    @Autowired
    HelpMapper helpMapper;

    public boolean addHelp(String username, String userid, String content, String time) {
        return helpMapper.insertHelpList(username,userid,content,time) > 0;
    }

    public List<Help> helpList() {
        return helpMapper.selectHelpList();
    }

    public boolean deleteHelp(String description) {
        return helpMapper.deleteHelp(description);
    }
}
