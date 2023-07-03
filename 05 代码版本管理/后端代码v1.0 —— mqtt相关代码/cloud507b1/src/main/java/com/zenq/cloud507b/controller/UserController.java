package com.zenq.cloud507b.controller;


import com.zenq.cloud507b.po.User;
import com.zenq.cloud507b.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
