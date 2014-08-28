package com.cyou.base.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import com.cyou.base.bean.Account;
import com.cyou.base.bean.Role;
import com.cyou.base.bean.User;
import com.cyou.base.service.UserService;
import com.cyou.common.util.UUIDUtil;
import com.cyou.core.action.BaseAction;
import com.cyou.core.util.StrMD5;


@Controller
@Namespace(value="/user")
public class UserAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(UserAction.class);
	
	@Resource
	private UserService userService ;
	
	//用户的ID 用于给用户分配角色使用
	private Integer userid;
	//用户角色列表 用于给用户分配角色使用
	private List<Integer> addrole;
	
	
	//用户ID 用于修改、启停权方法使用
	private Integer id;
	//用户登陆名 用于修改、启停权方法使用
	private String username;
	//用户密码 用于修改、启停权方法使用
	private String password;
	//用户姓名 用于修改、启停权方法使用
	private String name;
	//用户邮箱 用于修改、启停权方法使用
	private String email;
	//用户电话 用于修改、启停权方法使用
	private String tel;
	//用户地址 用于修改、启停权方法使用
	private String address;
	//用户备注 用于修改、启停权方法使用
	private String description;
	
	//用户登录名 用于添加方法使用
	private String addusername;
	//用户登录名密码 用于添加方法使用
	private String addpassword;
	//用户姓名 用于添加方法使用
	private String addname;
	//用户email 用于添加方法使用
	private String addemail;
	//用户tel 用于添加方法使用
	private String addtel;
	//用户address 用于添加方法使用
	private String addaddress;
	//用户备注 用于添加方法使用
	private String adddescription;
	
	private String conditionUsername;
	
	private Integer disabled = -1;
	
	public String getConditionUsername() {
		return conditionUsername;
	}
	public void setConditionUsername(String conditionUsername) {
		this.conditionUsername = conditionUsername;
	}
	public Integer getDisabled() {
		return disabled;
	}
	public void setDisabled(Integer disabled) {
		this.disabled = disabled;
	}
	/**
	 * 跳转至用户类表的处理类
	 * @return
	 */
	@Action(value = "/userList", results = { @Result(name = SUCCESS, location = "/WEB-INF/page/base/userList.jsp") })
	public String userList(){
		Object[] objs = {conditionUsername,disabled};
		httpServletRequest.setAttribute("pageList", userService.getPageList(this.getPagelist(),objs));
		return SUCCESS;
	}
	@Action(value = "/onlineUsersList", results = { @Result(name = SUCCESS, location = "/WEB-INF/page/base/onlineUsersList.jsp") })
	public String onlineUsersList(){
		httpServletRequest.setAttribute("pageList", userService.getOnlineUsersPageList(this.getPagelist()));
		return SUCCESS;
	}
	/**
	 * 添加用户的方法
	 * @return
	 */
	@Action(value = "/addUser", results = { @Result(name = SUCCESS, type="redirect",location = "/user/userList.action?page=${page}"), 
												   @Result(name = INPUT, location = "/WEB-INF/page/base/userList.jsp")
	                                              })
	public String addUser(){
		try {
			User user = new User();
			user.setUsername(this.getAddusername());
			user.setPassword(new StrMD5(this.getAddpassword()).getResult());
			user.setName(this.getAddname());
			user.setEmail(this.getAddemail());
			user.setTel(this.getAddtel());
			user.setAddress(this.getAddaddress());
			user.setDescription(this.getAdddescription());
			user.setUserId(UUIDUtil.getUUID());
			userService.addUser(user);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return SUCCESS;
		}
		
		return SUCCESS;
	}
	
	
	public void validateAddUser() {
		super.validate();
		if(StringUtils.isBlank(this.getAddusername())){
			this.addFieldError("addusername", getText("user_usernameerror"));
		}else if(StringUtils.isBlank(this.getAddpassword())){
			this.addFieldError("addpassword", getText("user_passworderror"));
		}else if(this.getAddpassword().trim().length() < 16 ){
			this.addFieldError("addpassword", getText("password_newPassword_tooshort"));
		}else if(checkNewPassword(this.getAddpassword())){
			this.addFieldError("addpassword", getText("password_newPassword_notstrong"));
		}else if(userService.isUserExist(addusername)) {
			this.addFieldError("addusername", getText("user_usernameexist"));
		}
		if( "yes".equals(httpServletRequest.getAttribute("havefielderror"))){
			httpServletRequest.setAttribute("tab",2);
			userList();
		}
	}
	
	/**
	 * 更改用户的方法
	 * @return
	 */
	@Action(value = "/updateUser", results = { @Result(name = SUCCESS, type="redirect",location = "/user/userList.action?page=${page}"), 
			   @Result(name = INPUT, location = "/WEB-INF/page/base/userList.jsp")
           })
	public String updateUser(){
		
		User user = userService.getUser(this.getId());
		if(StringUtils.isNotBlank(this.getPassword())){
			user.setPassword(new StrMD5(this.getPassword()).getResult());
		}
		user.setName(this.getName());
		user.setEmail(this.getEmail());
		user.setTel(this.getTel());
		user.setAddress(this.getAddress());
		user.setDescription(this.getDescription());
		userService.updateUser(user);
		return SUCCESS;
	}
	
	public void validateUpdateUser() {
		super.validate();
		if(this.getId() == null ){
			this.addFieldError("id", getText("user_iderror"));
		}else if(StringUtils.isNotBlank(this.getPassword())){
			if(this.getPassword().trim().length() < 16 ){
				this.addFieldError("password", getText("password_newPassword_tooshort"));
			}else if(checkNewPassword(this.getPassword())){
				this.addFieldError("password", getText("password_newPassword_notstrong"));
			}
		}
		if( "yes".equals(httpServletRequest.getAttribute("havefielderror"))){
			userList();
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
	/**
	 * 用户启停权的方法
	 * @return
	 */
	@Action(value = "/deleteUser", results = { @Result(name = SUCCESS, type="redirect",location = "/user/userList.action?page=${page}"), 
			   @Result(name = INPUT, location = "/WEB-INF/page/base/userList.jsp")
        })
	public String deleteUser(){
		User user = new User();
		user.setId(this.getId());
		User currentUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		boolean b = userService.deleteUser(user,currentUser);
		if(!b){
			this.addFieldError("username", getText("user_thecurrentusererror"));
			userList();
			return INPUT;
		}
		return SUCCESS;
	}
	
	public void validateDeleteUser() {
		super.validate();
		if(this.getId() == null ){
			this.addFieldError("id", getText("user_iderror"));
		}
		if( "yes".equals(httpServletRequest.getAttribute("havefielderror"))){
			userList();
		}
	}
	@Action(value = "/resetUserAccountType", results = { 
			@Result(name = SUCCESS, type="redirect",location = "/user/onlineUsersList.action?page=${page}"), 
			@Result(name = INPUT, location = "/WEB-INF/page/base/onlineUsersList.jsp")
        })
	public String resetUserAccountType(){
		try {
			if(id != null){
				Account account = userService.getAccountById(id);
				if(account != null){
					account.setAccountType(account.getAccountType().equals("0")? "1":"0");
					userService.updateAccount(account);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return INPUT;
		}
		return SUCCESS;
	}
	@Action(value = "/resetUserStatus", results = { 
			@Result(name = SUCCESS, type="redirect",location = "/user/onlineUsersList.action?page=${page}"), 
			@Result(name = INPUT, location = "/WEB-INF/page/base/onlineUsersList.jsp")
	})
	public String resetUserStatus(){
		try {
			if(id != null){
				Account account = userService.getAccountById(id);
				if(account != null){
					account.setDisabled(account.isDisabled()? false:true);
					userService.updateAccount(account);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return INPUT;
		}
		return SUCCESS;
	}
	/**
	 * 给用户分配权限时 跳转至用户权限列表页的方法
	 * @return
	 */
	@Action(value = "/userRole", results = { @Result(name = SUCCESS, location = "/WEB-INF/page/base/userRole.jsp"), 
			   @Result(name = INPUT, location = "/WEB-INF/page/base/userList.jsp")
     })
	public String userRole(){
		User user = userService.getUser(this.getId());
		if(user == null){
			this.addFieldError("id", getText("user_iderror"));
			httpServletRequest.setAttribute("pageList", userService.getPageList(this.getPagelist()));
			return INPUT;
		}else{
			List<Role> roles = new ArrayList<Role>();
			for(Role role:userService.getAllRole()){
				if(!user.getRoles().contains(role)){
					roles.add(role);
				}
			}
			httpServletRequest.setAttribute("roles",roles);
			httpServletRequest.setAttribute("user",user);
		}
		
		return SUCCESS;
	}
	
	public void validateUserRole() {
		super.validate();
		if(this.getId() == null ){
			this.addFieldError("id", getText("user_iderror"));
		}
		if( "yes".equals(httpServletRequest.getAttribute("havefielderror"))){
			httpServletRequest.setAttribute("pageList", userService.getPageList(this.getPagelist()));
		}
	}
	
	/**
	 * 给用户分配权限的方法
	 * @return
	 */
	@Action(value = "/addUserRole", results = { 
			@Result(name = SUCCESS, type="redirect",location = "/user/userRole.action?id=${userid}"), 
			@Result(name = INPUT, location = "/WEB-INF/page/base/userRole.jsp")
	})
	public String addUserRole(){
		User user = userService.getUser(this.getUserid());
		if(user == null){
			this.addFieldError("id", getText("user_iderror"));
			return INPUT;
		}else{
			Set<Role> addroles = new HashSet<Role>();
			if(addrole!=null && addrole.size()>0){
				for(Integer addid :addrole){
					Role r = userService.getRole(addid);
					if(r != null){
						addroles.add(r);
					}
					
				}
			}
			
			user.setRoles(addroles);
			userService.updateUserRole(user);
			List<Role> roles = new ArrayList<Role>();;
			for(Role role:userService.getAllRole()){
				if(!user.getRoles().contains(role)){
					roles.add(role);
				}
			}
			httpServletRequest.setAttribute("roles",roles);
			httpServletRequest.setAttribute("user",user);
		}
		httpServletRequest.setAttribute("addUserRoleSuccess","yes");
		return SUCCESS;
	}
	
	

	public String getAddusername() {
		return addusername;
	}

	public void setAddusername(String addusername) {
		this.addusername = addusername;
	}

	public String getAddpassword() {
		return addpassword;
	}

	public void setAddpassword(String addpassword) {
		this.addpassword = addpassword;
	}

	public String getAddname() {
		return addname;
	}

	public void setAddname(String addname) {
		this.addname = addname;
	}

	public String getAddemail() {
		return addemail;
	}

	public void setAddemail(String addemail) {
		this.addemail = addemail;
	}

	public String getAddtel() {
		return addtel;
	}
	
	public void setAddtel(String addtel) {
		this.addtel = addtel;
	}
	
	public String getAddaddress() {
		return addaddress;
	}
	
	public void setAddaddress(String addaddress) {
		this.addaddress = addaddress;
	}
	
	public String getAdddescription() {
		return adddescription;
	}

	public void setAdddescription(String adddescription) {
		this.adddescription = adddescription;
	}

	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}
	
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public List<Integer> getAddrole() {
		return addrole;
	}

	public void setAddrole(List<Integer> addrole) {
		this.addrole = addrole;
	}

}

