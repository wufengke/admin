package com.cyou.userorder.action;


import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.cyou.core.action.BaseAction;
import com.cyou.userorder.bean.UserOrder;
import com.cyou.userorder.service.PayService;

@Controller
@Namespace(value="/userorder")
public class PayAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(PayAction.class);
	
	private Integer status;
	
	private String  orderId;
	
	private Integer tab;
	
	private UserOrder userOrder;
	@Resource
	private PayService payService;
	
	@Action(value = "/userOrderList", results = { 
			@Result(name = SUCCESS, location = "/WEB-INF/page/userorder/userOrderList.jsp")})
	public String courseList(){
		try {
			httpServletRequest.setAttribute("pageList", payService.getPageList(this.getPagelist(),status));
			httpServletRequest.setAttribute("tab", status);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return SUCCESS;
		}
		return SUCCESS;
	}
	@Action(value = "/lookUserOrderDetail", results = { 
			@Result(name = SUCCESS, location = "/WEB-INF/page/userorder/userOrderDetail.jsp")})
	public String lookUserOrderDetail(){
		try {
			if(StringUtils.isNotBlank(orderId)){
				userOrder = payService.getOrderbyOrderId(orderId);
			}
			httpServletRequest.setAttribute("tab", tab);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return SUCCESS;
		}
		return SUCCESS;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public PayService getPayService() {
		return payService;
	}

	public void setPayService(PayService payService) {
		this.payService = payService;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public UserOrder getUserOrder() {
		return userOrder;
	}
	public void setUserOrder(UserOrder userOrder) {
		this.userOrder = userOrder;
	}
	public Integer getTab() {
		return tab;
	}
	public void setTab(Integer tab) {
		this.tab = tab;
	}
}