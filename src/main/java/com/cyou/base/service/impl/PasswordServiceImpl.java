package com.cyou.base.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyou.base.bean.User;
import com.cyou.base.dao.PasswordDao;
import com.cyou.base.service.PasswordService;
import com.cyou.core.util.StrMD5;
@Service
@Transactional
public class PasswordServiceImpl  implements PasswordService{
	@Resource
	private PasswordDao passwordDao;

	@SuppressWarnings("unchecked")
	@Override
	public User getUser(String username) {
		List<User> users = passwordDao.find("FROM User user WHERE user.username = ? AND user.disabled = false",new Object[]{username} );
		if(users == null || users.size()==0){
			return null;
		}
        return users.get(0);
	}

	@Override
	public void updateUser(User user,String password) {
		User sessionUser = passwordDao.get(User.class, user.getId());
		if(password != null && password.trim().length()!=0 ){
			sessionUser.setPassword(new StrMD5(password.trim()).getResult());
		}
		passwordDao.update(sessionUser);
	}

	
}
