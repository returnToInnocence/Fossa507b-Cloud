package com.zenq.cloud507b.controller;

import com.zenq.cloud507b.config.common.ErrorMessage;
import com.zenq.cloud507b.config.common.SuccessMessage;
import com.zenq.cloud507b.po.User;
import com.zenq.cloud507b.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

@GetMapping("/currentuser")
@ResponseBody
public Object currentuser(HttpSession httpSession){
    try {
        String uid = (String) httpSession.getAttribute("userid");
        System.out.println("用户登录到聊天室的uid："+uid);
        //System.out.println(uid != null);
        //System.out.println(uid.equals("null"));
        String name = null;
        if (uid != null) {
            name = loginService.getnamebyid(uid);
            System.out.println("当前登录用户的姓名为"+name);
            return new SuccessMessage<User>("当前登录用户成功添加到聊天室", new User(uid, name)).getMessage();
        } else {
            return new ErrorMessage("没有用户登录到聊天室").getMessage();
        }
    }catch (Exception e){
        System.out.println(e);
        return new ErrorMessage("添加到聊天室失败").getMessage();
    }
}

    @PostMapping("/loginvalidate")
    public String loginvalidate(@RequestParam("username") String username, @RequestParam("password") String pwd, HttpSession httpSession){
        if(username==null)
            return "login";
        String realpwd = loginService.getpwdbyname(username);
        if(realpwd != null && pwd.equals(realpwd))
        {
            String uid = loginService.getUidbyname(username);
            httpSession.setAttribute("userid", uid);
            return "chatroom";
        }else
            return "fail";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession httpSession){
        return "login";
    }
}
