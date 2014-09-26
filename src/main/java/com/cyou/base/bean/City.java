package com.cyou.base.bean;

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
@Table(name="CITY")
public class City implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6216057797182379811L;
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Column(name="PROVINCE_ID")
	private String provinceId;
	@Column(name="CITY_NAME")
	private String cityName;
	@Column(name="CITY_ZIP_CODE")
	private String cityZipCode;
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
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCityZipCode() {
		return cityZipCode;
	}
	public void setCityZipCode(String cityZipCode) {
		this.cityZipCode = cityZipCode;
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