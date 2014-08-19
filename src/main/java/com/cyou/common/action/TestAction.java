package com.cyou.common.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.cyou.core.action.BaseAction;
@Controller
@Namespace(value="/login")
public class TestAction extends BaseAction{
      
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Action(value = "/toLoginPage", results = { @Result(name = SUCCESS, location = "/login.jsp") })
	public String toLoginPage(){
    	  return SUCCESS;
    }
	
	@Action(value = "/toIndexPage", results = { @Result(name = SUCCESS, location = "/index.jsp") })
	public String toIndexPage(){
    	  return SUCCESS;
    }
}
