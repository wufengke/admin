package com.cyou.base.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USERS")
public class Users implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="USER_ID",length=32,unique=true)
	private String userId;
	
	@Column(name="PROVINCE_ID")
	private Integer provinceId;
	
	@Column(name="CITY_ID")
	private Integer cityId;
	
	@Column(name="REGION_ID")
	private Integer regionId;
	
	@Column(name="CLASSES",length=10)
	private String classes;
	
	@Column(name="REAL_NAME",length=32)
	private String realName;
	
	@Column(name="SEX")
	private String sex;
	
	@Column(name="SCHOOL_ID")
	private Integer schoolId;
	
	@Column(name="SCHOOL_NAME",length=255)
	private String schoolName;
	
	@Column(name="IMAGE_URL",length=255)
	private String imageUrl;
	
	@Column(name="TEACHER_TITLE",length=32)
	private String teacherTitle;
	@Column(name="TEACH_YEAR")
	private Integer teachYear;
	@Column(name="TEACHER_BRIEF")
	private String teacherBrief;
	
	@Column(name="QQ",length=20)
	private String qq;
	@Column(name="STAGE",length=10)
	private String stage;
	@Column(name="ID_CARD_NO",length=20)
	private String idCardNo;
	@Column(name="PHONE",length=16)
	private String phone;
	@Column(name="COMMENTS",length=255)
	private String comments;
	@Column(name="CREATE_TIME")
	private Date createTime;
	@Column(name="UPDATE_TIME")
	private Date updateTime;
	@Column(name="STATUS")
	private String status;
	
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
	public Integer getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public Integer getRegionId() {
		return regionId;
	}
	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}
	public String getClasses() {
		return classes;
	}
	public void setClasses(String classes) {
		this.classes = classes;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getTeacherTitle() {
		return teacherTitle;
	}
	public void setTeacherTitle(String teacherTitle) {
		this.teacherTitle = teacherTitle;
	}
	public Integer getTeachYear() {
		return teachYear;
	}
	public void setTeachYear(Integer teachYear) {
		this.teachYear = teachYear;
	}
	public String getTeacherBrief() {
		return teacherBrief;
	}
	public void setTeacherBrief(String teacherBrief) {
		this.teacherBrief = teacherBrief;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	public String getIdCardNo() {
		return idCardNo;
	}
	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
