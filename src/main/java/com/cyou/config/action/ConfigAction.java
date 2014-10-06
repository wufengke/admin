package com.cyou.config.action;

import java.io.File;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.aspectj.util.FileUtil;
import org.springframework.stereotype.Controller;

import com.cyou.base.util.PropertyUtil;
import com.cyou.config.bean.IndexPic;
import com.cyou.config.service.ConfigService;
import com.cyou.core.action.BaseAction;
import com.cyou.core.bean.PageList;
@Controller
@Namespace("/config")
public class ConfigAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(ConfigAction.class);
	
	private Integer id;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	private File file;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	
	private int rank;

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	
	private Integer status;
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	@Resource
	private ConfigService configService;
	
	@Action(value="/indexPicList",results={@Result(name=SUCCESS,location="/WEB-INF/page/config/indexPicList.jsp")})
	public String configIndexPic()
	{
		try {
			PageList pageList = configService.getPageList(this.getPagelist());
			System.out.println(pageList.getFullListSize());
			httpServletRequest.setAttribute("pageList", pageList);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return INPUT;
		}
		return SUCCESS;
	}
	
	@Action(value = "/addIndexPic", results = { @Result(name = SUCCESS, type="redirect",location = "/config/indexPicList.action?page=${page}"), 
			   @Result(name = INPUT, location = "/config/indexPicList.action?page=${page}")
           })
	public String addIndexPic()
	{
		try {
			System.out.println("get into add Action");
			IndexPic pic=new IndexPic();
			pic.setDescription(this.getDescription());
			pic.setTitle(this.getTitle());
			pic.setRank(this.getRank());
            pic.setStatus(this.getStatus());
			if(this.file==null){
				System.out.println("file null success");
				return SUCCESS;
			}
			else{
				//save file and set path
				String basePath=PropertyUtil.getProperty("indexPictureLocation");
				String extention=".png";//com.cyou.base.util.FileUtil.getExtention(file.getName());
				Date time=new Date();
				SimpleDateFormat fmt=new SimpleDateFormat("yyyyMMddHHmmssSSSS");
				String fileName=fmt.format(time)+extention;
				File dst=new File(basePath+"\\"+fileName);
				com.cyou.base.util.FileUtil.copy(file, dst);
				pic.setPath("images/show/"+fileName);
			}
			configService.addIndexPic(pic);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	@Action(value = "/deleteIndexPic", results = { 
			@Result(name = SUCCESS, type="redirect",location = "/config/indexPicList.action?page=${page}")})
	public String deleteIndexPic(){
		try {
			if(this.getId() != null){
				IndexPic ip = configService.getIndexPicById(this.getId());
				configService.deleteIndexPic(ip);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return SUCCESS;
	}
	
	@Action(value = "/resetIndexPicState", results = { 
			@Result(name = SUCCESS, type="redirect",location = "/config/indexPicList.action?page=${page}")})
	public String resetIndexPicState()
	{
		try{
			if(this.getId()!=null)
			{
				IndexPic ip = configService.getIndexPicById(this.getId());
				if(ip.getStatus()==0){
					ip.setStatus(1);
				}else{
					ip.setStatus(0);
				}
				configService.updateIndexPic(ip);
			}
		}catch(Exception e)
		{
			logger.error(e.getMessage(),e);
		}
		return SUCCESS;
	}
	
	@Action(value="/updateIndexPic",results={@Result(name=SUCCESS,location="/config/indexPicList.action?page=${page}")})
	public String updateIndexPic()
	{
		try{
			if(this.getId() != null){
				IndexPic ip = configService.getIndexPicById(this.getId());
				if(this.file!=null){					
					String basePath=PropertyUtil.getProperty("indexPictureLocation");
					//remove preve file
					String prevFileName=ip.getPath().replace("images/show/", "");
					File prevFile=new File(basePath+prevFileName);
					prevFile.delete();
					//save file and set path
					String extention=".png";//com.cyou.base.util.FileUtil.getExtention(file.getName());
					Date time=new Date();
					SimpleDateFormat fmt=new SimpleDateFormat("yyyyMMddHHmmssSSSS");
					String fileName=fmt.format(time)+extention;
					File dst=new File(basePath+"\\"+fileName);
					com.cyou.base.util.FileUtil.copy(file, dst);
					ip.setPath("images/show/"+fileName);
				}
				
				ip.setRank(this.getRank());
				ip.setDescription(this.getDescription());
				ip.setTitle(this.getTitle());
				ip.setStatus(this.getStatus());
				configService.updateIndexPic(ip);
			}
		}catch(Exception e)
		{
			logger.error(e.getMessage(),e);
		}
		return SUCCESS;
	}
}
