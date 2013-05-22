package com.taxi.admin.service.module;

/**
 * TTaxiserverDriverinfo entity. @author MyEclipse Persistence Tools
 */

public class TTaxiserverDriverinfo implements java.io.Serializable {

	// Fields

	private Long id;
	private Long uid;
	private String driverName;
	private String driverCompany;
	private String companyPhone;
	private String driverCity;
	private String plateNumber;
	private String companyNo;
	private String taobaoLoginName;
	private Long isTrue;
	private String phoneNumber;
	private String loginName;
	private String lon;
	private String lat;
	private String updateTime;
	

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getDriverCompany() {
		return driverCompany;
	}

	public void setDriverCompany(String driverCompany) {
		this.driverCompany = driverCompany;
	}

	public String getCompanyPhone() {
		return companyPhone;
	}

	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}

	public String getDriverCity() {
		return driverCity;
	}

	public void setDriverCity(String driverCity) {
		this.driverCity = driverCity;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public String getCompanyNo() {
		return companyNo;
	}

	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}

	public String getTaobaoLoginName() {
		return taobaoLoginName;
	}

	public void setTaobaoLoginName(String taobaoLoginName) {
		this.taobaoLoginName = taobaoLoginName;
	}

	public Long getIsTrue() {
		return isTrue;
	}

	public void setIsTrue(Long isTrue) {
		this.isTrue = isTrue;
	}

}