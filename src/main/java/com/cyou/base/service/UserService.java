package com.cyou.base.service;


import java.util.List;

import com.cyou.base.bean.Account;
import com.cyou.base.bean.Role;
import com.cyou.base.bean.User;
import com.cyou.base.bean.Users;
import com.cyou.core.bean.PageList;


public interface UserService{
	/**
	 * 查询用户列表
	 * @param pageList
	 * @return
	 */
	public PageList getPageList(PageList pageList);
	/**
	 * 查询线上注册用户列表
	 * @param pageList
	 * @return
	 */
	public PageList getOnlineUsersPageList(PageList pageList);
	/**
	 * 增加用户
	 * @param user
	 */
	public void addUser(User user);
	/**
	 * 修改用户
	 * @param user
	 */
	public void updateUser(User user);
	
	/**
	 * 修改用户权限
	 * @param user
	 */
	public void updateUserRole(User user);
	/**
	 * 用户启停权
	 * @param user
	 */
	public boolean deleteUser(User user,User currentUser);
	/**
	 * 根据id查找用户
	 * @param id
	 * @return
	 */
	public User getUser(Integer id);
	/**
	 * 根据唯一name判断用户是否存在
	 * @param username
	 * @return
	 */
	public boolean isUserExist(String username);
	/**
	 * 获取全部角色对象
	 * @return
	 */
	public List<Role> getAllRole();
	/**
	 * 根据id查找角色
	 * @param id
	 * @return
	 */
	public Role getRole(Integer id);
	
	/**
	 * 查找非admin用户
	 * @return
	 */
	public List<User> getUserNotAdmin();
	public PageList getPageList(PageList pagelist, Object[] objs);
	
	
	
	
	/**网站用户方法**/
	void saveAccount(Account account);

	Account getAccount(String username, String password);

	Account getAccountById(Integer id);

	Users getUsersByUserId(String userId);
	
	public List<Users> getTeacherUsers();
	
	public void updateAccount(Account account);
	public void updateUsers(Users user);
}
