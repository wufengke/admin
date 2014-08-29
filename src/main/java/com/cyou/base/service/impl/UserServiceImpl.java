package com.cyou.base.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyou.base.bean.Account;
import com.cyou.base.bean.Role;
import com.cyou.base.bean.User;
import com.cyou.base.bean.Users;
import com.cyou.base.dao.UserDao;
import com.cyou.base.service.UserService;
import com.cyou.core.bean.PageList;
import com.cyou.core.util.StrMD5;
@Service
@Transactional
public class UserServiceImpl  implements UserService{
	private final static Logger logger = Logger.getLogger(UserServiceImpl.class);
	@Resource
	private UserDao userDao;

	@Override
	public PageList getPageList(PageList pageList) {
		PageList list = null;
		try {
			list = userDao.search("from User ", null, pageList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		return list;
	}

	@Override
	public PageList getPageList(PageList pageList, Object[] objs) {
		PageList list = null;
		try {
			StringBuilder sql = new StringBuilder("from User u where 1=1 ");
			sql.append(getConditionHql(objs));
			list = userDao.search(sql.toString(), null, pageList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		return list;
	}

	private String getConditionHql(Object[] objs) {
		if(objs == null){
			return " order by u.username asc";
		}
		StringBuilder sb = new StringBuilder();
		
		if(StringUtils.isNotBlank((String)objs[0])){
			sb.append(" and u.username='").append(objs[0]).append("'");
		}
		if((Integer)objs[1] != -1){
			sb.append(" and u.disabled != ").append(objs[1]);
		}
		sb.append(" order by u.username asc");
		
		return sb.toString();
		
	}

	@Override
	public void addUser(User user) {
		try {
			userDao.save(user);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
	}

	@Override
	public void updateUser(User user) {
		User sessionUser = userDao.get(User.class, user.getId());
		if(user.getPassword() != null 
				&& user.getPassword().trim().length()!=0 
				&& !sessionUser.getPassword().equals(new StrMD5(user.getPassword()).getResult())
				&& !sessionUser.getPassword().equals(user.getPassword())){
			sessionUser.setPassword(new StrMD5(user.getPassword()).getResult());
		}
		sessionUser.setName(user.getName());
		sessionUser.setEmail(user.getEmail());
		sessionUser.setTel(user.getTel());
		sessionUser.setAddress(user.getAddress());
		sessionUser.setDescription(user.getDescription());
		userDao.update(sessionUser);
	}
	
	@Override
	public void updateUserRole(User user) {
		userDao.update(user);
		
	}

	@Override
	public boolean deleteUser(User user,User currentUser) {
		User sessionUser = userDao.get(User.class, user.getId());
		if(!sessionUser.getUsername().equals(currentUser.getUsername())){
			sessionUser.setDisabled(!sessionUser.isDisabled());
			userDao.update(sessionUser);
			return true;
		}
		
		return false;
		
	}



	@Override
	public User getUser(Integer id) {
		return userDao.get(User.class, id);
	}

	@Override
	public boolean isUserExist(String username) {
		List list =  userDao.find("from User where username = ?", new Object[]{username});
		if(list != null && list.size() > 0) {
			return true;
		}
		return false;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getAllRole() {
		return userDao.find("from Role");
	}

	@Override
	public Role getRole(Integer id) {
		return userDao.get(Role.class, id);
	}



	@Override
	public List<User> getUserNotAdmin() {
		// TODO Auto-generated method stub
		return userDao.find(" from User user where id not in (select userRoleId.user_Id from UserRole where userRoleId.role_Id=1)") ;
//		return userDao.find("select distinct(user) from User user,IN(user.roles) role where role.name != 'ROLE_ADMIN'") ;
	}

	/**=====================网站用户方法========================**/
	@Override
	public Account getAccount(String username, String password) {
		try {
			return userDao.getAccount(username,password);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public Account getAccountById(Integer id) {
		try {
			return userDao.get(Account.class, id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public Users getUsersByUserId(String userId) {
		try {
			return userDao.getUsersByUserId(userId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public void saveAccount(Account account) {
		try {
			userDao.save(account);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public List<Users> getTeacherUsers() {
		try {
			return userDao.getTeacherUsers();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ArrayList<Users>();
		}
	}

	@Override
	public PageList getOnlineUsersPageList(PageList pageList) {
		try {
			return userDao.search("from Account a order by a.createTime desc", null, pageList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return pageList;
		}
		
	}

	@Override
	public void updateAccount(Account account) {
		try {
			userDao.update(account);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
