package com.cyou.base.service;


import java.util.List;

import com.cyou.base.bean.Role;
import com.cyou.base.bean.UserRole;
import com.cyou.core.bean.PageList;


public interface RoleService{
	/**
	 * 获取角色列表的方法
	 * @param pageList
	 * @return
	 */
	public PageList getPageList(PageList pageList);
	
	/**
	 * 增加角色
	 * @param role
	 */
	public void addRole(Role role);
	
	/**
	 * 更改角色
	 * @param role
	 */
	public void updateRole(Role role);
	
	/**
	 * 判断角色名是否已存在
	 * @param name
	 */
	public boolean isRoleExist(String name);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Role getRoleById(Integer id);

	public List<UserRole> getUserRolesByRole(Role r);

	public void removeRole(Role role);

	public void removeUserRole(Integer integer);
	
	
}
