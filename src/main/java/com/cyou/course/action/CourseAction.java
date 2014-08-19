package com.cyou.course.action;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.cyou.base.bean.Users;
import com.cyou.base.service.UserService;
import com.cyou.base.util.PropertyUtil;
import com.cyou.common.util.UUIDUtil;
import com.cyou.core.action.BaseAction;
import com.cyou.course.bean.Course;
import com.cyou.course.bean.CourseDetail;
import com.cyou.course.service.CourseService;

@Controller
@Namespace(value="/course")
public class CourseAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(CourseAction.class);
	
	private Integer id;
	private Integer status;
	private Integer isRoll;
	private Integer rank;
	private Integer lessonTimes = 0;
	private Integer totalCount;
	private Integer count;
	private String userId;
	private String courseDesc;
	private String courseBrief;
	private String courseTitle;
	private Date courseTime;
	private String courseTimeDesc;
	private Double originalPrice;
	private Double price;
	private String bigImageSideColor;
	private String step = "step1";
	private static final int BUFFER_SIZE = 16 * 1024;  
    private List<File> myFile = new ArrayList<File>();    
    private List<String> myFileContentType = new ArrayList<String>();  
    private List<String> myFileFileName = new ArrayList<String>();    //文件名  
    private List<String> imageFileName = new ArrayList<String>();  
	
    private String courseId;
    
	@Resource
	private CourseService courseService;
	
	@Resource
	private UserService userService;
	
	
	@Action(value = "/courseList", results = { 
			@Result(name = SUCCESS, location = "/WEB-INF/page/course/courseList.jsp")})
	public String courseList(){
		try {
			httpServletRequest.setAttribute("pageList", courseService.getPageList(this.getPagelist()));
			httpServletRequest.setAttribute("teacherList", userService.getTeacherUsers());
			httpServletRequest.setAttribute("step", step);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return INPUT;
		}
		return SUCCESS;
	}
	@Action(value = "/deleteCourse", results = { 
			@Result(name = SUCCESS, type="redirect",location = "/course/courseList.action?page=${page}")})
			public String deleteCourse(){
		try {
			if(this.getId() != null){
				Course c = courseService.getCourseById(id);
				CourseDetail cd = null;
				if(c != null) {
					cd = courseService.getCourseDetailByDetailId(c.getCourseDetailId());
				}
				courseService.deleteCourse(c,cd);
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return INPUT;
		}
		return SUCCESS;
	}
	
	@Action(value = "/addCourseStep1", results = { 
			@Result(name = SUCCESS,location = "/WEB-INF/page/course/courseList.jsp"),
			@Result(name = INPUT, location = "/WEB-INF/page/course/courseList.jsp")})
	public String addCourseStep1(){
		try {
			if(!"step2".equals(step)){
				setStep("step2");
			}else{
				setStep("step1");
			}
			httpServletRequest.setAttribute("step", step);
			httpServletRequest.setAttribute("tab",2);
			httpServletRequest.setAttribute("pageList", courseService.getPageList(this.getPagelist()));
			httpServletRequest.setAttribute("teacherList", userService.getTeacherUsers());
			
			if(StringUtils.isNotBlank(courseId)){
				//
				return viewOldCourse(courseId);
			}else {
				return createNewCourse();
			}
	       
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return INPUT;
		}
	}
	
	/**
	 * 
	 * @param courseId2
	 * @return
	 */
	private String viewOldCourse(String courseId) {
		Course course = courseService.getCourseByDetailId(courseId);
		setCount(course.getCount());
		setCourseBrief(course.getCourseBrief());
		setCourseDesc(course.getCourseDesc());
		setCourseId(courseId);
		setCourseTime(course.getCourseTime());
		setCourseTitle(course.getCourseTitle());
		setIsRoll(Integer.parseInt(course.getIsRoll()));
		setLessonTimes(course.getLessonTimes());
		setRank(course.getRank());
		setStatus(course.getStatus());
		setTotalCount(course.getTotalCount());
		setUserId(course.getUserId());
		return SUCCESS;
	}
	
	/**
	 * 
	 * @return
	 */
	private String createNewCourse() {
		courseId = UUIDUtil.getUUID();
		httpServletRequest.setAttribute("courseId", courseId);
		Course course = new Course();
		course.setCount(count);
		course.setCourseBrief(courseBrief);
		course.setCourseDesc(courseDesc);
		course.setCourseDetailId(courseId);
		course.setCourseId(courseId);
		course.setCourseTime(courseTime);
		course.setCourseTitle(courseTitle);
		Date d = new Date();
		course.setCreateTime(d);
		course.setIsRoll(isRoll.toString());
		course.setLessonTimes(lessonTimes);
		course.setRank(rank);
		course.setStatus(0);
		course.setTotalCount(totalCount);
		course.setUpdateTime(d);
		course.setUserId(userId);
		course.setCourseTimeDesc(courseTimeDesc);
		course.setOriginalPrice(originalPrice);
		course.setPrice(price);
		course.setBigImageSideColor(bigImageSideColor);
		Users u = userService.getUsersByUserId(userId);
		if(u != null){
			course.setUserImageUrl(u.getImageUrl());
			course.setUserRealName(u.getRealName());
		}
		
		if (myFile == null)  
            return INPUT;  
        for (int i = 0; i < myFile.size(); i++) {  
            imageFileName.add(new Date().getTime()+ getExtention(this.getMyFileFileName().get(i))) ;  
            File dir = new File(PropertyUtil.getProperty("imagesLocation")+courseId +"/" + i);
            dir.mkdirs();
            File imageFile = new File(PropertyUtil.getProperty("imagesLocation")+ courseId +"/" + i +"/" + imageFileName.get(i));   
            copy(myFile.get(i), imageFile); 
        }  
        
        for (int i = 0; i < imageFileName.size(); i++) {
        	 String prefix = "/upload/" + courseId + "/" + i + "/";
			if(i == 0){
				course.setBigImageUrl(prefix + imageFileName.get(i));
			}
			if(i == 1){
				course.setMediumImageUrl(prefix + imageFileName.get(i));
			}
			if(i == 2){
				course.setSmallImageUrl(prefix + imageFileName.get(i));
			}
		}
        courseService.saveCourse(course);
        
        return SUCCESS;
	}
	private static void copy(File src, File dst) {  
        try {  
            InputStream in = null;  
            OutputStream out = null;  
            try {  
                in = new BufferedInputStream(new FileInputStream(src),  
                        BUFFER_SIZE);  
                out = new BufferedOutputStream(new FileOutputStream(dst),  
                        BUFFER_SIZE);  
                byte[] buffer = new byte[BUFFER_SIZE];  
                while (in.read(buffer) > 0) {  
                    out.write(buffer);  
                }  
            } finally {  
                if (null != in) {  
                    in.close();  
                }  
                if (null != out) {  
                    out.close();  
                }  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
	      
    private static String getExtention(String fileName) {  
        int pos = fileName.lastIndexOf(".");  
        return fileName.substring(pos);  
    }  
	    
    private Integer lessionTimes;
	private String lessionSchedule;
	private Integer comments;
	private String courseDetailBrief;
	private String courseDetailDesc;
	private String courseDetailOutline;
	private String courseDetailSummary;
	private String courseDetailEvaluate;
	
	@Action(value = "/addCourseStep2", results = { 
			@Result(name = SUCCESS,location = "/WEB-INF/page/course/courseList.jsp"),
			@Result(name = INPUT, location = "/WEB-INF/page/course/courseList.jsp")})
	public String addCourseStep2(){
		try {
			setStep("step3");
			httpServletRequest.setAttribute("step", step);
			httpServletRequest.setAttribute("tab",2);
			httpServletRequest.setAttribute("pageList", courseService.getPageList(this.getPagelist()));
			httpServletRequest.setAttribute("teacherList", userService.getTeacherUsers());
			
			CourseDetail cd = new CourseDetail();
			
			cd.setComments(comments);
			cd.setCourseDetailBrief(courseDetailBrief);
			cd.setCourseDetailDesc(courseDetailDesc);
			cd.setCourseDetailEvaluate(courseDetailEvaluate);
			cd.setCourseDetailId(courseId);
			cd.setCourseDetailOutline(courseDetailOutline);
			cd.setCourseDetailSummary(courseDetailSummary);
			Date d = new Date();
			cd.setCreateTime(d);
			cd.setLessionSchedule(lessionSchedule);
			cd.setLessionTimes(lessionTimes);
			cd.setUpdateTime(d);
			courseService.saveCourseDetail(cd);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return INPUT;
		}
		return SUCCESS;
	}
	
	@Action(value = "/courseOperation", results = { 
			@Result(name = SUCCESS, type="redirect",location = "/course/courseList.action?page=${page}"),
			@Result(name = INPUT, location = "/WEB-INF/page/course/courseList.jsp")})
	public String courseOperation(){
		
		try {
			step = "step1";
			if(this.getId() != null){
				Course course = courseService.getCourseById(this.getId());
				if(course != null){
					course.setStatus(status);
					courseService.updateCourse(course);
				}
			}
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getIsRoll() {
		return isRoll;
	}
	public void setIsRoll(Integer isRoll) {
		this.isRoll = isRoll;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public Integer getLessonTimes() {
		return lessonTimes;
	}
	public void setLessonTimes(Integer lessonTimes) {
		this.lessonTimes = lessonTimes;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCourseDesc() {
		return courseDesc;
	}
	public void setCourseDesc(String courseDesc) {
		this.courseDesc = courseDesc;
	}
	public String getCourseBrief() {
		return courseBrief;
	}
	public void setCourseBrief(String courseBrief) {
		this.courseBrief = courseBrief;
	}
	public String getCourseTitle() {
		return courseTitle;
	}
	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}
	public Date getCourseTime() {
		return courseTime;
	}
	public void setCourseTime(Date courseTime) {
		this.courseTime = courseTime;
	}
	public List<File> getMyFile() {
		return myFile;
	}
	public void setMyFile(List<File> myFile) {
		this.myFile = myFile;
	}
	public List<String> getMyFileContentType() {
		return myFileContentType;
	}
	public void setMyFileContentType(List<String> myFileContentType) {
		this.myFileContentType = myFileContentType;
	}
	public List<String> getMyFileFileName() {
		return myFileFileName;
	}
	public void setMyFileFileName(List<String> myFileFileName) {
		this.myFileFileName = myFileFileName;
	}
	public List<String> getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(List<String> imageFileName) {
		this.imageFileName = imageFileName;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	
	public String getStep() {
		return step;
	}
	public void setStep(String step) {
		this.step = step;
	}
	public Integer getLessionTimes() {
		return lessionTimes;
	}
	public void setLessionTimes(Integer lessionTimes) {
		this.lessionTimes = lessionTimes;
	}
	public String getLessionSchedule() {
		return lessionSchedule;
	}
	public void setLessionSchedule(String lessionSchedule) {
		this.lessionSchedule = lessionSchedule;
	}
	public Integer getComments() {
		return comments;
	}
	public void setComments(Integer comments) {
		this.comments = comments;
	}
	public String getCourseDetailBrief() {
		return courseDetailBrief;
	}
	public void setCourseDetailBrief(String courseDetailBrief) {
		this.courseDetailBrief = courseDetailBrief;
	}
	public String getCourseDetailDesc() {
		return courseDetailDesc;
	}
	public void setCourseDetailDesc(String courseDetailDesc) {
		this.courseDetailDesc = courseDetailDesc;
	}
	public String getCourseDetailOutline() {
		return courseDetailOutline;
	}
	public void setCourseDetailOutline(String courseDetailOutline) {
		this.courseDetailOutline = courseDetailOutline;
	}
	public String getCourseDetailSummary() {
		return courseDetailSummary;
	}
	public void setCourseDetailSummary(String courseDetailSummary) {
		this.courseDetailSummary = courseDetailSummary;
	}
	public String getCourseDetailEvaluate() {
		return courseDetailEvaluate;
	}
	public void setCourseDetailEvaluate(String courseDetailEvaluate) {
		this.courseDetailEvaluate = courseDetailEvaluate;
	}
	public String getCourseTimeDesc() {
		return courseTimeDesc;
	}
	public void setCourseTimeDesc(String courseTimeDesc) {
		this.courseTimeDesc = courseTimeDesc;
	}
	public Double getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getBigImageSideColor() {
		return bigImageSideColor;
	}
	public void setBigImageSideColor(String bigImageSideColor) {
		this.bigImageSideColor = bigImageSideColor;
	}
	
	
}