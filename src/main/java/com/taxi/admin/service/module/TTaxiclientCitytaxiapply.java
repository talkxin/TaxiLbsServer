package com.taxi.admin.service.module;

/**
 * TTaxiclientCitytaxiapply entity. @author MyEclipse Persistence Tools
 */

public class TTaxiclientCitytaxiapply implements java.io.Serializable {

	// Fields

	private Long applyId;
	private Long uid;
	private String cityName;
	private String companyName;
	private String goNumber;
	private String extraNumber;
	private String gokmNumber;
	private String kmNumber;
	private Integer struts;
	private String remark;
	private String other;
	private String loginName;

	// Constructors

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getLoginName() {
		return loginName;
	}

	public String getExtraNumber() {
		return extraNumber;
	}

	public void setExtraNumber(String extraNumber) {
		this.extraNumber = extraNumber;
	}

	public String getGokmNumber() {
		return gokmNumber;
	}

	public void setGokmNumber(String gokmNumber) {
		this.gokmNumber = gokmNumber;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/** default constructor */
	public TTaxiclientCitytaxiapply() {
	}

	/** full constructor */
	public TTaxiclientCitytaxiapply(Long uid, String cityName, String goNumber,
			String kmNumber, Integer struts, String remark, String other) {
		this.uid = uid;
		this.cityName = cityName;
		this.goNumber = goNumber;
		this.kmNumber = kmNumber;
		this.struts = struts;
		this.remark = remark;
		this.other = other;
	}

	// Property accessors

	public Long getApplyId() {
		return this.applyId;
	}

	public void setApplyId(Long applyId) {
		this.applyId = applyId;
	}

	public Long getUid() {
		return this.uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getCityName() {
		return this.cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getGoNumber() {
		return this.goNumber;
	}

	public void setGoNumber(String goNumber) {
		this.goNumber = goNumber;
	}

	public String getKmNumber() {
		return this.kmNumber;
	}

	public void setKmNumber(String kmNumber) {
		this.kmNumber = kmNumber;
	}

	public Integer getStruts() {
		return this.struts;
	}

	public void setStruts(Integer struts) {
		this.struts = struts;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOther() {
		return this.other;
	}

	public void setOther(String other) {
		this.other = other;
	}

}