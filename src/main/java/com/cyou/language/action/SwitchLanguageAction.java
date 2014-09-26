package com.cyou.language.action;

import javax.servlet.http.Cookie;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.cyou.core.action.BaseAction;

@Controller
@Namespace(value="/language")
public class SwitchLanguageAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String referer; 
	private static Logger logger = Logger.getLogger(SwitchLanguageAction.class);

	 @Action(value = "/chooseLanguage", results = { @Result(name = SUCCESS, type="redirect", location = "${referer}") })
	 public String chooseLanguage(){
		 try {
			 String request_locale = this.httpServletRequest.getParameter("request_locale");
			  if (request_locale.equals("zh_CN"))
				  this.httpServletRequest.getSession().setAttribute("WW_TRANS_I18N_LOCALE",java.util.Locale.CHINA);
			  if (request_locale.equals("en_US"))
				  this.httpServletRequest.getSession().setAttribute("WW_TRANS_I18N_LOCALE",java.util.Locale.US);
			  Cookie cookie = new Cookie("CYOU_LANG",request_locale);
			  cookie.setMaxAge(Integer.MAX_VALUE);
			  cookie.setPath("/");
			  this.httpServletResponse.addCookie(cookie);
			  logger.debug(request_locale);
			  referer = this.httpServletRequest.getHeader("referer");
			  if ( (referer==null  || "".equals(referer.trim()))||referer.contains("login_error") ) {		
				  String path = httpServletRequest.getContextPath();
				  String basePath = httpServletRequest.getScheme()+"://"+httpServletRequest.getServerName()+":"+httpServletRequest.getServerPort()+path;
				  referer = basePath + "/login/toLoginPage.action?login_error=1";
			  }
			  return SUCCESS;
		} catch (Exception e) {
			logger.error(e);
			return SUCCESS;
		}
		 
	 }

	 public String getReferer() {
	  return referer;
	 }

	 public void setReferer(String referer) {
	  this.referer = referer;
	 }
	}
