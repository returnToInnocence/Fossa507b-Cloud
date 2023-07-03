package com.zenq.cloud507b.controller;


import com.zenq.cloud507b.config.common.ErrorMessage;
import com.zenq.cloud507b.po.User;
import com.zenq.cloud507b.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

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
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/api/UserList")
    @ResponseBody
    public List<User> getListUser(){
        System.out.println(userService.listUser());
        return userService.listUser();
    }

//    @PostMapping("/api/register")
//    @ResponseBody
//    public Object register(HttpServletRequest req){
//        try {
//            String userid = req.getParameter("id");
//            String username = req.getParameter("name");
//            String password = req.getParameter("password");
//            String realname = userService.selectUserName(username);
//            String realid = userService.selectUserID(userid);
//            System.out.println(realname);
//            System.out.println(realid);
//            // 先去数据库找用户名是否存在
//            if(realname != null || realid != null){
//                System.out.println("用户已经存在");
//            }
//        }catch (Exception e){
//            System.out.println(e);
//            new ErrorMessage("用户注册失败").getMessage();
//        }
//    }
}
