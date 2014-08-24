package com.cyou.base.dao;

import java.util.List;

import com.cyou.base.bean.Account;
import com.cyou.base.bean.Users;
import com.cyou.core.dao.BaseDao;

public interface UserDao extends BaseDao{
	Account getAccount(String username, String password);

	Users getUsersByUserId(String userId);

	List<Users> getTeacherUsers();

	Account getAccountByUserId(String userId);
}
