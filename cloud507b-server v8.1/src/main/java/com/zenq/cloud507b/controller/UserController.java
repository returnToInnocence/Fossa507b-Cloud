package com.zenq.cloud507b.controller;


import com.zenq.cloud507b.config.common.ErrorMessage;
import com.zenq.cloud507b.config.common.SuccessMessage;
import com.zenq.cloud507b.po.User;
import com.zenq.cloud507b.service.LoginService;
import com.zenq.cloud507b.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chenxi
 * @since 2023-07-01
 */
@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    LoginService loginService;

    @RequestMapping("/api/UserList")
    public List<User> getListUser(){
        System.out.println(userService.listUser());
        return userService.listUser();
    }

    @PostMapping("/api/register")
    public Object register(@RequestBody Map<String, Object> requestData){
        System.out.println(requestData);
        Map<String, Object> userForm = (Map<String, Object>) requestData.get("userForm");
        String userid = (String) userForm.get("userid");
        String username = (String) userForm.get("username");
        String usermail = (String) userForm.get("usermail");
        String userpsd = (String) userForm.get("userpsd");
        String userstatus = (String) userForm.get("userstatus");
        String usergroup = (String) userForm.get("usergroup");
        try {
            String realname = userService.selectUserName(username);
            String realid = userService.selectUserID(userid);
            System.out.println("realname"+realname);
            System.out.println("realid"+realid);
            // 先去数据库找用户名是否存在
            if(realname == null && realid == null){
                //insert
                System.out.println("需要新建用户");
                return new SuccessMessage<>("用户注册成功", userService.userRegister(new User(userid,username,usermail,userpsd,userstatus,usergroup))).getMessage();
            }else{
                //update
                System.out.println("需要更新用户");
                //通过userid和username来定位一条数据
                return new SuccessMessage<>("用户更新信息成功",userService.updateUser(new User(userid,username,usermail,userpsd,userstatus,usergroup))).getMessage();
            }
        }catch (Exception e){
            System.out.println(e);
            return new ErrorMessage("用户注册失败").getMessage();
        }
    }

    @PostMapping("/api/login")
    public Object login(@RequestBody Map<String, Object> requestData){
        Map<String, Object> userForm = (Map<String, Object>) requestData.get("loginForm");
        String userid = (String) userForm.get("userid");
        String userpsd = (String) userForm.get("userpsd");
        String realpsd = loginService.getpwdbyid(userid);
        System.out.println(userpsd);
        System.out.println(realpsd);
        if(userpsd.equals(realpsd)){
            System.out.println("密码验证成功");
            return new SuccessMessage<>("登录成功",userid).getMessage();
        }else{
            System.out.println("密码不正确");
            return new ErrorMessage("密码不正确").getMessage();
        }
    }

    @RequestMapping("/api/getUserName")
    public String getUserName(@RequestParam("userid") String userid){
        //System.out.println(userService.getUserName(userid));
        return userService.getUserName(userid);
    }
}
