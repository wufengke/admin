package com.cyou.feedback.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.cyou.core.action.BaseAction;
import com.cyou.feedback.service.AboutService;
@Controller
@Namespace("/feedback")
public class FeedbackAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource
	private AboutService aboutService;
	
	@Action(value="/feedbackList",results={@Result(name=SUCCESS,location="/WEB-INF/page/feedback/feedbackList.jsp")})
	public String feedbackList(){
		httpServletRequest.setAttribute("pageList", aboutService.getPageList(this.getPagelist()));
		return SUCCESS;
	}
}
