package com.cyou.base.bean;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USER_ROLE")
public class UserRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private UserRoleCompoundKey userRoleId;
	@Embedded
	@AttributeOverrides({@AttributeOverride(name="user_Id",column=@Column(name="user_Id")),
	@AttributeOverride(name="role_Id",column=@Column(name="role_Id"))})
	public UserRoleCompoundKey getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(UserRoleCompoundKey userRoleId) {
		this.userRoleId = userRoleId;
	}
	public UserRole() {
	}
}
