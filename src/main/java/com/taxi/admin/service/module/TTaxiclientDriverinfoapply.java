package com.taxi.admin.service.module;

/**
 * TTaxiclientDriverinfoapply entity. @author MyEclipse Persistence Tools
 */

public class TTaxiclientDriverinfoapply implements java.io.Serializable {

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
	private String loginName;

	// Constructors

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/** default constructor */
	public TTaxiclientDriverinfoapply() {
	}

	/** full constructor */
	public TTaxiclientDriverinfoapply(Long uid, String driverName,
			String driverCompany, String companyPhone, String driverCity,
			String plateNumber, String companyNo, String taobaoLoginName,
			Long isTrue) {
		this.uid = uid;
		this.driverName = driverName;
		this.driverCompany = driverCompany;
		this.companyPhone = companyPhone;
		this.driverCity = driverCity;
		this.plateNumber = plateNumber;
		this.companyNo = companyNo;
		this.taobaoLoginName = taobaoLoginName;
		this.isTrue = isTrue;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUid() {
		return this.uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getDriverName() {
		return this.driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getDriverCompany() {
		return this.driverCompany;
	}

	public void setDriverCompany(String driverCompany) {
		this.driverCompany = driverCompany;
	}

	public String getCompanyPhone() {
		return this.companyPhone;
	}

	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}

	public String getDriverCity() {
		return this.driverCity;
	}

	public void setDriverCity(String driverCity) {
		this.driverCity = driverCity;
	}

	public String getPlateNumber() {
		return this.plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public String getCompanyNo() {
		return this.companyNo;
	}

	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}

	public String getTaobaoLoginName() {
		return this.taobaoLoginName;
	}

	public void setTaobaoLoginName(String taobaoLoginName) {
		this.taobaoLoginName = taobaoLoginName;
	}

	public Long getIsTrue() {
		return this.isTrue;
	}

	public void setIsTrue(Long isTrue) {
		this.isTrue = isTrue;
	}

}