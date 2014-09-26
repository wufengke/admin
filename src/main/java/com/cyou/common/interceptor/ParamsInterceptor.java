package com.cyou.common.interceptor;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.cyou.core.action.BaseAction;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.opensymphony.xwork2.util.ValueStack;

public class ParamsInterceptor implements Interceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		try{
			 
			BaseAction action = (BaseAction) invocation.getAction();
			HttpServletRequest httpServletRequest = action.httpServletRequest;
			Map<String,	String> paramsMap = httpServletRequest.getParameterMap();
			
			Set<?> set = paramsMap.entrySet();
			Iterator<?> it = set.iterator();
			//获取struts的值栈，并根据相应参数值，逐个替换特舒符号
			ValueStack v = invocation.getStack();
			while(it.hasNext()){
				Map.Entry<String,String> en = (Entry<String, String>) it.next();
				if(v.findValue(en.getKey())!= null && v.findValue(en.getKey()) instanceof String){
					v.setValue(en.getKey(), v.findValue(en.getKey()).toString().replace("<","&lt;"));
				}
				 
			}
			
			return invocation.invoke();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return invocation.invoke();
	}

}
