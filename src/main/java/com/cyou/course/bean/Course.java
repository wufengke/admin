package com.cyou.course.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="COURSE_BRIEF")
public class Course implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//自增id
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private Integer id;
	
	@Column(name="COURSE_ID",length=32,nullable=false,unique=true)
	private String courseId;
	@Column(name="COURSE_TITLE",length=255)
	private String courseTitle;
	@Column(name="COURSE_DESC",length=32)
	private String courseDesc;
	@Column(name="COURSE_BRIEF",length=128)
	private String courseBrief;
	@Column(name="BIG_IMAGE_URL",length=255)
	private String bigImageUrl;
	@Column(name="MEDIUM_IMAGE_URL",length=255)
	private String mediumImageUrl;
	@Column(name="SMALL_IMAGE_URL",length=255)
	private String smallImageUrl;
	@Column(name="COURSE_TIME")
	private Date courseTime;
	@Column(name="COURSE_TIME_DESC",length=32)
	private String courseTimeDesc;
	@Column(name="TOTAL_COUNT")
	private Integer totalCount;
	@Column(name="COUNT")
	private Integer count;
	@Column(name="LESSON_TIMES")
	private Integer lessonTimes;
	@Column(name="STATUS")
	private Integer status;
	@Column(name="CREATE_TIME")
	private Date createTime;
	@Column(name="UPDATE_TIME")
	private Date updateTime;
	@Column(name="COURSE_DETAIL_ID",length=32)
	private String courseDetailId;
	@Column(name="USER_ID",length=255)
	private String userId;
	@Column(name="USER_REAL_NAME",length=32)
	private String userRealName;
	@Column(name="USER_IMAGE_URL",length=255)
	private String userImageUrl;
	@Column(name="RANK")
	private Integer rank;
	@Column(name="IS_ROLL")
	private String isRoll;
	@Column(name="ORIGINAL_PRICE")
	private Double originalPrice;
	@Column(name="PRICE")
	private Double price;
	@Column(name="BIG_IMAGE_SIDE_COLOR")
	private String bigImageSideColor;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
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

	public String getBigImageUrl() {
		return bigImageUrl;
	}

	public void setBigImageUrl(String bigImageUrl) {
		this.bigImageUrl = bigImageUrl;
	}
	public Date getCourseTime() {
		return courseTime;
	}
	public void setCourseTime(Date courseTime) {
		this.courseTime = courseTime;
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

	public Integer getLessonTimes() {
		return lessonTimes;
	}

	public void setLessonTimes(Integer lessonTimes) {
		this.lessonTimes = lessonTimes;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCourseDetailId() {
		return courseDetailId;
	}

	public void setCourseDetailId(String courseDetailId) {
		this.courseDetailId = courseDetailId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserRealName() {
		return userRealName;
	}
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	public String getUserImageUrl() {
		return userImageUrl;
	}
	public void setUserImageUrl(String userImageUrl) {
		this.userImageUrl = userImageUrl;
	}
	public String getMediumImageUrl() {
		return mediumImageUrl;
	}
	public void setMediumImageUrl(String mediumImageUrl) {
		this.mediumImageUrl = mediumImageUrl;
	}
	public String getSmallImageUrl() {
		return smallImageUrl;
	}
	public void setSmallImageUrl(String smallImageUrl) {
		this.smallImageUrl = smallImageUrl;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public String getIsRoll() {
		return isRoll;
	}
	public void setIsRoll(String isRoll) {
		this.isRoll = isRoll;
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
