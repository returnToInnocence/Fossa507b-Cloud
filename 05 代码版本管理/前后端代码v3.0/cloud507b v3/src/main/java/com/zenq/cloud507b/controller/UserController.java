package com.zenq.cloud507b.controller;


import com.zenq.cloud507b.config.common.ErrorMessage;
import com.zenq.cloud507b.config.common.SuccessMessage;
import com.zenq.cloud507b.po.User;
import com.zenq.cloud507b.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chenxi
 * @since 2023-07-01
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @CrossOrigin(origins = "http://localhost:5173")
    @RequestMapping("/api/UserList")
    public List<User> getListUser(){
        System.out.println(userService.listUser());
        return userService.listUser();
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/api/register")
    public Object register(HttpServletRequest req){
        try {
            String userid = req.getParameter("userid");
            String username = req.getParameter("username");
            String usermail = req.getParameter("usermail");
            String password = req.getParameter("password");
            //String usericon = req.getParameter("usericon");
            String userstatus = req.getParameter("userstatus");
            String usergroup = req.getParameter("usergroup");

            String realname = userService.selectUserName(username);
            String realid = userService.selectUserID(userid);
            System.out.println(realname);
            System.out.println(realid);
            // 先去数据库找用户名是否存在
            if(realname == null && realid == null){
                //insert
                System.out.println("需要新建用户");
                return new SuccessMessage<>("用户注册成功", userService.userRegister(new User(userid,username,usermail,password,userstatus,usergroup))).getMessage();
            }else{
                //update
                System.out.println("需要更新用户信息");
                return new SuccessMessage<>("用户更新信息成功",userService.updateUser(new User(userid,username,usermail,password,userstatus,usergroup))).getMessage();
            }
        }catch (Exception e){
            System.out.println(e);
            return new ErrorMessage("用户注册失败").getMessage();
        }
    }
}
