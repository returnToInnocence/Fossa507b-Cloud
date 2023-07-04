package com.zenq.cloud507b.service;

import com.zenq.cloud507b.mapper.UserMapper;
import com.zenq.cloud507b.po.User;
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
public class UserService {
    @Autowired
    UserMapper userMapper;

    public List<User> listUser(){
        return userMapper.selectUserList();
    }

    public String selectUserName(String username){
        return userMapper.selectUserName(username);
    }

    public String selectUserID(String userid) {
        return userMapper.selectUserID(userid);
    }
}
