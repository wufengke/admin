package com.cyou.base.action;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.cyou.base.bean.User;
import com.cyou.base.service.PasswordService;
import com.cyou.core.action.BaseAction;
import com.cyou.core.util.StrMD5;


@Controller
@Namespace(value="/password")
public class PasswordAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private PasswordService passwordService ;
	
	//用户登陆名 
	private String username;
	//用户的旧密码
	private String oldPassword;
	//用户的新密码
	private String newPassword;
	//用户的重复新密码
	private String newRePassword;
	
	
	
	
	/**
	 * 更改用户的方法
	 * @return
	 */
	@Action(value = "/toChangePasswordPage", results = { @Result(name = SUCCESS, location = "/changePassword.jsp"), 
			   @Result(name = INPUT, location = "/changePassword.jsp")
           })
	public String toChangePasswordPage(){
		return SUCCESS;
	}
	
	
	
	/**
	 * 修改密码的方法
	 * @return
	 */
	@Action(value = "/changePassword", results = { 
			   @Result(name = SUCCESS, location = "/changePassword.jsp"), 
			   @Result(name = INPUT, location = "/changePassword.jsp")
           })
	public String changePassword(){
		User user = passwordService.getUser(this.getUsername());
		if(user == null){
			this.addFieldError("username", getText("password_usernameerror"));
			return INPUT;
		}else if(!user.getPassword().equals(new StrMD5(this.getOldPassword()).getResult())){
			this.addFieldError("oldPassword", getText("password_oldPassworderror"));
			return INPUT;
		}
		passwordService.updateUser(user,this.getNewPassword());
		this.httpServletRequest.setAttribute("changepasswordok", "ok" );
		return SUCCESS;
	}
	
	public void validateChangePassword() {
		super.validate();
		if(this.getUsername()==null || "".equals(this.getUsername().trim())){
			this.addFieldError("username", getText("password_usernameerror"));
		}else if(this.getOldPassword()==null || "".equals(this.getOldPassword().trim())){
			this.addFieldError("oldPassword", getText("password_oldPassworderror"));
		}else if(this.getNewPassword()==null || "".equals(this.getNewPassword().trim())){
			this.addFieldError("newPassword", getText("password_newPassworderror"));
		}else if(this.getNewPassword().trim().length() < 16){
			this.addFieldError("newPassword", getText("password_newPassword_tooshort"));
		}else if(checkNewPassword(this.getNewPassword())){
			this.addFieldError("newPassword", getText("password_newPassword_notstrong"));
		}else if(this.getNewRePassword()==null || "".equals(this.getNewRePassword().trim())){
			this.addFieldError("newRePassword", getText("password_newRePassworderror"));
		}else if(!this.getNewPassword().equals(this.getNewRePassword())){
			this.addFieldError("newRePassword", getText("password_wrongRePassword"));
		}

	}

	private boolean checkNewPassword(String newPassword2) {
		Pattern regex1 = Pattern.compile("^[0-9]*$");
		Pattern regex2 = Pattern.compile("^[a-zA-Z]*$");
		Matcher matcher1 = regex1.matcher(newPassword2);
		Matcher matcher2 = regex2.matcher(newPassword2);
		if(matcher1.matches() || matcher2.matches()){
			return true;
		}
		return false;
	}



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewRePassword() {
		return newRePassword;
	}

	public void setNewRePassword(String newRePassword) {
		this.newRePassword = newRePassword;
	}

}