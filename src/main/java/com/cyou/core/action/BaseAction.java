package com.cyou.core.action;

import java.net.URLDecoder;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.displaytag.properties.SortOrderEnum;
import org.springframework.security.context.SecurityContextHolder;

import com.cyou.base.bean.Role;
import com.cyou.base.bean.User;
import com.cyou.core.bean.PageList;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 基础action处理类
 * 实现了ServletContextAware接口，可以直接访问servletContext对象
 * 实现了ServletResponseAware接口，可以直接访问httpServletRequest对象
 * 实现了ServletRequestAware接口，可以直接访问httpServletResponse对象及httpSession对象
 * 实现了SessionAware接口，可以直接访问session对象,注意此session是struts通过将httpSession封装之后的session，类型是Map
 * @author liuyang_js
 *
 */
public class BaseAction extends ActionSupport implements ServletContextAware,
	ServletResponseAware, ServletRequestAware, SessionAware {

    private static final long serialVersionUID = 1L;
    /** 根据哪个字段排序*/
    private String sort;
    /** asc 还是 desc*/
    private String dir = "asc";
    /** 当前第几页*/
    private Integer page = 1;
    
    protected PageList pagelist;
    
    protected ServletContext servletContext;

    public HttpServletRequest httpServletRequest;

    protected HttpServletResponse httpServletResponse;
    
    protected HttpSession httpSession;
    
    protected Map<String, Object> session;
    
    

    @Override
    public void setServletContext(ServletContext context) {
    	URLDecoder.decode("");
	this.servletContext = context;
    }
    
    @Override
    public void setServletResponse(HttpServletResponse response) {
	this.httpServletResponse = response;
	httpServletResponse.setHeader("Cache-Control", "no-cache");   
	httpServletResponse.setContentType("text/json;charset=UTF-8");  
	httpServletResponse.setCharacterEncoding("UTF-8");
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
	this.httpServletRequest = request;
	this.httpSession = request.getSession();
    }

    @Override
    public void setSession(Map<String, Object> session) {
	this.session = session;
    }
    
	@Override
	public void addFieldError(String fieldName, String errorMessage) {
		if(getFieldErrors().get(fieldName)==null){
			super.addFieldError(fieldName, errorMessage);
			httpServletRequest.setAttribute("havefielderror", "yes");
		}
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public PageList getPagelist() {
		return pagelist;
	}

	public void setPagelist(PageList pagelist) {
		this.pagelist = pagelist;
	}

	@Override
	/**
	 * 初始化displaytag使用的pagelist对象，以及导出问题
	 */
	public void validate() {
		this.setPagelist(new PageList());
		if(this.getPage()!=null){
			this.getPagelist().setPageNumber(this.getPage());
		}
		if(this.getSort()!=null){
			this.getPagelist().setSortCriterion(this.getSort());
		}
		
		if(this.getDir()!=null && "asc".equals(this.getDir())){
			this.getPagelist().setSortDirection(SortOrderEnum.ASCENDING);
		}else{
			this.getPagelist().setSortDirection(SortOrderEnum.DESCENDING);
		}
		/**由于导出时传回来的参数名是以d-开头、-e结尾的一个字符串，所以此处这样接一下
		 * 
		 *  */
		Map<?, ?> map = httpServletRequest.getParameterMap();
		for(Object o: map.keySet()){
			String keyName =(String)o;
			if(keyName.indexOf("d-")!=-1 && keyName.indexOf("-e")!=-1){
				String value =  httpServletRequest.getParameter(keyName);
				//excel是2，pdf是5，目前支持此两种格式，但是导出pdf时会有中文不支持的问题
				if("2".equals(value) || "5".equals(value)){
					this.getPagelist().setExport(true);
					break;
				}
			}
		}
	}
    
	protected boolean checkNull(Object obj) {
		if(obj instanceof Collection){
			return obj==null || ((Collection)obj).size()==0;
		}else if(obj instanceof String){
			return obj==null || "".equals(obj.toString().trim());
		}
		return obj==null;
	}
	/**
	 * 判断用户角色，如果为admin用户，则返回null，否则返回其userId
	 * @return
	 */
	protected String getUserIdByRole(){
		User currentUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Set<Role> roles = currentUser.getRoles();
		for (Iterator<Role> iterator = roles.iterator(); iterator.hasNext();) {
			Role role = (Role) iterator.next();
			if("ROLE_ADMIN".equals(role.getName())){
				return null;
			}
		}
		return currentUser.getUserId();
	}
	
}
