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
@Table(name="COURSE_DETAIL")
public class CourseDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//自增id
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private Integer id;
	
	@Column(name="COURSE_DETAIL_ID",length=32)
	private String courseDetailId;
	@Column(name="LESSION_TIMES")
	private Integer lessionTimes;
	@Column(name="LESSION_SCHEDULE")
	private String lessionSchedule;
	@Column(name="COMMENTS")
	private Integer comments;
	@Column(name="CREATE_TIME")
	private Date createTime;
	@Column(name="UPDATE_TIME")
	private Date updateTime;
	
	
	@Column(name="COURSE_DETAIL_BRIEF",length=255)
	private String courseDetailBrief;
	@Column(name="COURSE_DETAIL_DESC",length=255)
	private String courseDetailDesc;
	@Column(name="COURSE_DETAIL_OUTLINE",length=512)
	private String courseDetailOutline;
	@Column(name="COURSE_DETAIL_SUMMARY")
	private String courseDetailSummary;
	@Column(name="COURSE_DETAIL_EVALUATE")
	private String courseDetailEvaluate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCourseDetailId() {
		return courseDetailId;
	}
	public void setCourseDetailId(String courseDetailId) {
		this.courseDetailId = courseDetailId;
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
	
}
