package com.zenq.cloud507b.service.impl;


import com.zenq.cloud507b.mapper.LoginMapper;
import com.zenq.cloud507b.po.Staff;
import com.zenq.cloud507b.po.User;
import com.zenq.cloud507b.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,timeout=5)
@Service
//@Service("loginservice")
public class LoginServiceImpl implements LoginService{
	@Autowired
	LoginMapper loginMapper;
	
	public String getpwdbyname(String name) {
		User user=loginMapper.getpwdbyname(name);
		if(user!=null)
			return user.getUserpsd();
		else
			return null;
	}

	public String getpwdbyid(String id) {
		User user=loginMapper.getpwdbyid(id);
		if(user!=null)
			return user.getUserpsd();
		else
			return null;
	}

	public String getnamebyid(String id) {
		User user = loginMapper.getnamebyid(id);
		if(user != null)
			return user.getUsername();
		else
			return null;
	}

	public String getUidbyname(String name) {
		User user = loginMapper.getpwdbyname(name);
		if(user != null)
			return String.valueOf(user.getUserid());
		else
			return null;
	}
}
