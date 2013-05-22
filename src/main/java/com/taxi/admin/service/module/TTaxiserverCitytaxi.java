package com.taxi.admin.service.module;

/**
 * TTaxiserverCitytaxi entity. @author MyEclipse Persistence Tools
 */

public class TTaxiserverCitytaxi implements java.io.Serializable {

	// Fields

	private Long cid;
	private String cityName;
	private String companyName;
	private String goNumber;
	private String extraNumber;
	private String gokmNumber;
	private String kmNumber;
	private Integer struts;

	// Constructors

	/** default constructor */
	public TTaxiserverCitytaxi() {
	}

	/** full constructor */

	// Property accessors

	public Long getCid() {
		return this.cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public String getCityName() {
		return cityName;
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

	public String getExtraNumber() {
		return this.extraNumber;
	}

	public void setExtraNumber(String extraNumber) {
		this.extraNumber = extraNumber;
	}

	public String getGokmNumber() {
		return this.gokmNumber;
	}

	public void setGokmNumber(String gokmNumber) {
		this.gokmNumber = gokmNumber;
	}

	public String getKmNumber() {
		return this.kmNumber;
	}

	public void setKmNumber(String kmNumber) {
		this.kmNumber = kmNumber;
	}

	public Integer getStruts() {
		return struts;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setStruts(Integer struts) {
		this.struts = struts;
	}

}