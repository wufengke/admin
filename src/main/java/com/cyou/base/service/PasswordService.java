package com.cyou.base.service;


import com.cyou.base.bean.User;



public interface PasswordService{

	public void updateUser(User user,String password);
	
	public User getUser(String username);
	
}
