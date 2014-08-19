package com.cyou.base.bean;

import java.io.Serializable;

import javax.persistence.Embeddable;
@Embeddable
public class UserRoleCompoundKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	@Column(name="USER_ID")
	private Integer user_Id;
//	@Column(name="ROLE_ID")
	private Integer role_Id;
	
	public UserRoleCompoundKey() {
		// TODO Auto-generated constructor stub
	}
	public UserRoleCompoundKey(int userId,int roleId){
		this.user_Id = userId;
		this.user_Id = roleId;
	}

//	public Integer getUserId() {
//		return userId;
//	}
//
//	public void setUserId(int userId) {
//		this.userId = userId;
//	}
//
//	public Integer getRoleId() {
//		return roleId;
//	}
//
//	public void setRoleId(int roleId) {
//		this.roleId = roleId;
//	}

	public Integer getUser_Id() {
		return user_Id;
	}

	public void setUser_Id(Integer user_Id) {
		this.user_Id = user_Id;
	}

	public Integer getRole_Id() {
		return role_Id;
	}

	public void setRole_Id(Integer role_Id) {
		this.role_Id = role_Id;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj instanceof UserRoleCompoundKey){
			if(((UserRoleCompoundKey)obj).getRole_Id()==this.user_Id && ((UserRoleCompoundKey)obj).getUser_Id()==this.user_Id){
				return true;
			}
		}
		return false;
	}
	
	
}
