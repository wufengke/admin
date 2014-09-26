package com.cyou.base.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.cyou.base.bean.Role;
import com.cyou.base.bean.UserRole;
import com.cyou.base.service.RoleService;
import com.cyou.core.action.BaseAction;
/**
 * 角色管理功能模块对应的action类
 * @author liuyang_js
 *
 */

@Controller
@Namespace(value="/role")
public class RoleAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private RoleService roleService ;
	
	//角色的ID 应用于修改方法
	private Integer id;
	//角色的备注 应用于修改方法
	private String description;
	
	private String name;
	
	//角色名称 用于添加方法使用
	private String addname;
	//角色描述 用于添加方法使用
	private String adddescription;
	
	/**
	 * 跳转至角色列表的action处理方法
	 * @return
	 */
	@Action(value = "/roleList", results = { @Result(name = SUCCESS, location = "/WEB-INF/page/base/roleList.jsp") })
	public String roleList(){
		httpServletRequest.setAttribute("pageList", roleService.getPageList(this.getPagelist()));
		return SUCCESS;
	}
	
	/**
	 * 添加用户的方法
	 * @return
	 */
	@Action(value = "/addRole", results = { @Result(name = SUCCESS, type="redirect",location = "/role/roleList.action?page=${page}"), 
												   @Result(name = INPUT, location = "/WEB-INF/page/base/roleList.jsp")
	                                              })
	public String addRole(){
	
		Role role = new Role();
		role.setName(this.getAddname());
		role.setDescription(this.getAdddescription());
		roleService.addRole(role);
		return SUCCESS;
	}
	
	public void validateAddRole() {
		super.validate();
		if(this.getAddname()==null || "".equals(this.getAddname().trim())){
			this.addFieldError("addname", getText("role_nameerror"));
		} else if(this.getAdddescription()==null || "".equals(this.getAdddescription().trim())){
			this.addFieldError("adddescription", getText("role_descriptionerror"));
		} else if(roleService.isRoleExist(addname)) {
			this.addFieldError("addname", getText("role_nameexist"));
		}
		if( "yes".equals(httpServletRequest.getAttribute("havefielderror"))){
			httpServletRequest.setAttribute("tab",2);
			roleList();
		}
	}
	
	/**
	 * 处理修改角色的action方法
	 * @return
	 */
	@Action(value = "/updateRole", results = { @Result(name = SUCCESS, type="redirect",location = "/role/roleList.action?page=${page}"), 
			   @Result(name = INPUT, location = "/WEB-INF/page/base/roleList.jsp")
           })
	public String updateRole(){
		Role role = new Role();
		role.setId(this.getId());
		role.setDescription(this.getDescription());
		role.setName(this.getName());
		roleService.updateRole(role);
		return SUCCESS;
	}
	
	public void validateUpdateRole() {
		super.validate();
		if(this.getId() == null ){
			this.addFieldError("id", getText("role_iderror"));
		}else if(this.getDescription()==null || "".equals(this.getDescription().trim())){
			this.addFieldError("roledescription", getText("role_descriptionerror"));
		}
		if( "yes".equals(httpServletRequest.getAttribute("havefielderror"))){
			roleList();
		}
	}
	@Action(value="deleteRole",results={@Result(name=SUCCESS,type="redirect",location="/role/roleList.action?page=${page}"),@Result(name=INPUT,type="redirect",location="/role/roleList.action")})
	public String deleteRole() {
		try {
			Role r = roleService.getRoleById(id);
			if(r != null){
				List<UserRole> urList = roleService.getUserRolesByRole(r);
				if(urList != null){
					roleService.removeUserRole(r.getId());
				}
				roleService.removeRole(r);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return SUCCESS;
		}
		
		return SUCCESS;
		
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getAddname() {
		return addname;
	}
	public void setAddname(String addname) {
		this.addname = addname;
	}
	public String getAdddescription() {
		return adddescription;
	}
	public void setAdddescription(String adddescription) {
		this.adddescription = adddescription;
	}

}