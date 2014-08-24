package com.cyou.applyTeach.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="APPLY_TEACH")
public class ApplyTeach implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//自增id
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private Integer id;
	
	@Column(name="USER_ID",length=32)
	private String userId;
	@Column(name="REAL_NAME",length=64)
	private String realName;
	@Column(name="SCHOOL_ID")
	private Integer schoolId;
	@Column(name="SCHOOL_NAME",length=255)
	private String schoolName;
	@Column(name="GENDER")
	private String gender;
	@Column(name="AGE")
	private Integer age;
	@Column(name="STAGE",length=10)
	private String stage;
	@Column(name="TEACHER_TITLE",length=32)
	private String teacherTitle;
	@Column(name="TEACH_YEARS")
	private Integer teachYears;
	@Column(name="PHONE",length=32)
	private String phone;
	@Column(name="EMAIL",length=64)
	private String email;
	@Column(name="QQ",length=20)
	private String qq;
	@Column(name="TEACH_TYPE",length=32)
	private String teachType;
	@Column(name="SUBJECT",length=32)
	private String subject;
	@Column(name="START_DATE")
	private String startDate;
	@Column(name="START_TIME")
	private String startTime;
	@Column(name="END_TIME")
	private String endTime;
	@Column(name="COURSE_NAME",length=255)
	private String courseName;
	@Column(name="COURSE_BRIEF")
	private String courseBrief;
	@Column(name="RESUME")
	private String resume;
	
	@Column(name="STATUS")
	private Integer status;
	@Column(name="CREATE_TIME")
	private Date createTime;
	@Column(name="UPDATE_TIME")
	private Date updateTime;
	
	@Column(name="COMMENTS")
	private String comments;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public Integer getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	public String getTeacherTitle() {
		return teacherTitle;
	}
	public void setTeacherTitle(String teacherTitle) {
		this.teacherTitle = teacherTitle;
	}
	public Integer getTeachYears() {
		return teachYears;
	}
	public void setTeachYears(Integer teachYears) {
		this.teachYears = teachYears;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getTeachType() {
		return teachType;
	}
	public void setTeachType(String teachType) {
		this.teachType = teachType;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseBrief() {
		return courseBrief;
	}
	public void setCourseBrief(String courseBrief) {
		this.courseBrief = courseBrief;
	}
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
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
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
}
