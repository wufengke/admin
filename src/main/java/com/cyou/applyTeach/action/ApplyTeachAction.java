package com.cyou.applyTeach.action;


import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.cyou.applyTeach.bean.ApplyTeach;
import com.cyou.applyTeach.service.ApplyTeachService;
import com.cyou.core.action.BaseAction;

@Controller
@Namespace(value="/applyteach")
public class ApplyTeachAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(ApplyTeachAction.class);
	private Integer id;
	private Integer status;
	
	private String comments;
	@Resource
	private ApplyTeachService applyTeachService;
	
	@Action(value = "/applyTeachList", results = { 
			@Result(name = SUCCESS, location = "/WEB-INF/page/applyteach/applyTeachList.jsp")})
	public String applyTeachList(){
		try {
			httpServletRequest.setAttribute("pageList", applyTeachService.getPageList(pagelist));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return INPUT;
		}
		return SUCCESS;
	}
	@Action(value = "/resetApplyTeachStatus", results = { 
			@Result(name = SUCCESS, type="redirect",location = "/applyteach/applyTeachList.action?page=${page}")})
	public String resetApplyTeachStatus(){
		try {
			if(id != null){
				ApplyTeach at = applyTeachService.getApplyTeach(id);
				if(at != null){
					at.setStatus(status);
				}
				boolean b = applyTeachService.saveOrUpdateApplyTeach(at);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return INPUT;
		}
		return SUCCESS;
	}
	@Action(value = "/lookApplyTeachDetail", results = { 
			@Result(name = SUCCESS,location = "/WEB-INF/page/applyteach/applyTeachDetail.jsp")})
	public String lookApplyTeachDetail(){
		try {
			ApplyTeach at = null;
			if(id != null){
				at = applyTeachService.getApplyTeach(id);
			}else{
				at = new ApplyTeach();
			}
				
			httpServletRequest.setAttribute("applyTeach", at);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return INPUT;
		}
		return SUCCESS;
	}
	@Action(value = "/updateApplyTeach", results = { 
			@Result(name = SUCCESS,location = "/WEB-INF/page/applyteach/applyTeachDetail.jsp")})
	public String updateApplyTeach(){
		try {
			ApplyTeach at = null;
			
			if(id != null){
				at = applyTeachService.getApplyTeach(id);
				if(at != null){
					at.setStatus(status);
					at.setComments(comments);
					applyTeachService.saveOrUpdateApplyTeach(at);
				}
			}else {
				at = new ApplyTeach();
			}
			httpServletRequest.setAttribute("applyTeach", at);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return INPUT;
		}
		return SUCCESS;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getStatus() {
		return status;
	}
	
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
}