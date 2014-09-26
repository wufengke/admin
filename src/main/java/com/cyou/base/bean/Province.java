package com.cyou.base.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Dictionary entity.
 * 
 * @author MyEclipse Persistence Tools
 */

@Entity
@Table(name="PROVINCE")
public class Province implements Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6216057797182379811L;
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Column(name="PROVINCE_NAME",length=20)
	private String provinceName;
	@Column(name="RANK")
	private Integer rank;
	@Column(name="ISVISIBLE")
	private Integer isvisible;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public Integer getIsvisible() {
		return isvisible;
	}
	public void setIsvisible(Integer isvisible) {
		this.isvisible = isvisible;
	}
}