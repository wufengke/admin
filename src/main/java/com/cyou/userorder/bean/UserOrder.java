package com.cyou.userorder.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="USER_ORDER")
public class UserOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	//自增id
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private Integer id;
	
	@Column(name="ORDER_ID",length=32)
	private String orderId;
	@Column(name="ACCOUNT_ID",length=32)
	private String accountId;
	@Column(name="USER_ID",length=32)
	private String userId;
	@Column(name="COURSE_ID",length=32)
	private String courseId;
	@Column(name="COURSE_TITLE",length=255)
	private String courseTitle;
	@Column(name="COURSE_IMG",length=255)
	private String courseSmallImage;
	@Column(name="AMOUNT")
	private Double amount;
	@Column(name="CREATE_TIME")
	private Date createTime;
	@Column(name="UPDATE_TIME")
	private Date updateTime;
	@Column(name="STATUS")
	private Integer status;
	@Column(name="REAL_NAME")
	private String realName;
	@Column(name="PHONE")
	private String phone;
	@Column(name="LESSION_SCHEDULE")
	private String lessionSchedule;
	@Column(name="LESSION_RANK")
	private Integer lessionRank;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getCourseSmallImage() {
		return courseSmallImage;
	}

	public void setCourseSmallImage(String courseSmallImage) {
		this.courseSmallImage = courseSmallImage;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLessionSchedule() {
		return lessionSchedule;
	}

	public void setLessionSchedule(String lessionSchedule) {
		this.lessionSchedule = lessionSchedule;
	}

	public Integer getLessionRank() {
		return lessionRank;
	}
	public void setLessionRank(Integer lessionRank) {
		this.lessionRank = lessionRank;
	}
	
}
