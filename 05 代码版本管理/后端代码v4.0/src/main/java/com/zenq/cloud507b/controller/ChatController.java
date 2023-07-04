package com.zenq.cloud507b.controller;

import com.zenq.cloud507b.config.common.ErrorMessage;
import com.zenq.cloud507b.config.common.SuccessMessage;
import com.zenq.cloud507b.po.User;
//import com.zenq.cloud507b.service.LoginService;
import com.zenq.cloud507b.service.LoginService;
import com.zenq.cloud507b.service.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.Session;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
/**
 * <p>
 *  前端控制器
 *	聊天室逻辑
 *
 * </p>
 *
 * @author chenxi
 * @since 2023-07-03
 */
@RestController
public class ChatController {

	@Autowired
	LoginService loginservice;

	@CrossOrigin(origins = "http://localhost:5173")
	@RequestMapping("/onlineusers")
	public Object onlineusers(@RequestParam("currentuser") String currentuser) {
		// 返回当前聊天室中的所有用户
		// 参数currentuser（当前用户名）
		// 返回值Set，聊天室中的所有用户，从SessionPools中取
		try {
			ConcurrentHashMap<String, Session> map = WebSocketServer.getSessionPools();
			Set<String> set = map.keySet();
			Iterator<String> it = set.iterator();
			Set<String> nameset = new HashSet<String>();
			while (it.hasNext()) {
				String entry = it.next();
				if (!entry.equals(currentuser))
					nameset.add(entry);
			}
			return new SuccessMessage<Set>("获取聊天室实时在线人成功",nameset).getMessage();
		}catch (Exception e){
			System.out.println(e);
			return new ErrorMessage("获取聊天室实时在线人失败").getMessage();
		}
	}

	@CrossOrigin(origins = "http://localhost:5173")
	@RequestMapping("getuid")
	public Object getuid(@RequestParam("username") String username) {
		try {
			String a = loginservice.getUidbyname(username);
			if (a != null && !a.equals("null")){
				User u = new User();
				u.setUserid(a);
				return new SuccessMessage<User>("获取id成功",u).getMessage();
			}else{
				return new SuccessMessage<User>("id不存在").getMessage();
			}
		}catch (Exception e){
			System.out.println(e);
			return new ErrorMessage("获取id失败").getMessage();
		}
	}
}
