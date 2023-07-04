package com.zenq.cloud507b.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zenq.cloud507b.po.Disscusion;
import com.zenq.cloud507b.mapper.DisscusionMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenxi
 * @since 2023-07-01
 */
@Service
public class DisscusionService {
    @Autowired
    DisscusionMapper disscusionMapper;

    public List<Disscusion> disscusionList(){
        return disscusionMapper.selectDisscusionList();
    }

    public int publishdisscusion(Disscusion disscusion){
        return disscusionMapper.insertTweetDisscusion(disscusion);
    }

    public int deletedisscusion(String id){
        return disscusionMapper.deleteTweetDisscusion(id);
    }
}
