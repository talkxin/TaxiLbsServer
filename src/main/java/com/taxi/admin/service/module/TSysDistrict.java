package com.taxi.admin.service.module;

/**
 * TSysDistrict entity. @author MyEclipse Persistence Tools
 */

public class TSysDistrict implements java.io.Serializable {

	// Fields

	private Integer identity;
	private String disName;
	private Integer cityId;
	private Integer disSort;

	// Constructors

	/** default constructor */
	public TSysDistrict() {
	}

	/** full constructor */
	public TSysDistrict(String disName, Integer cityId, Integer disSort) {
		this.disName = disName;
		this.cityId = cityId;
		this.disSort = disSort;
	}

	// Property accessors

	public Integer getIdentity() {
		return this.identity;
	}

	public void setIdentity(Integer identity) {
		this.identity = identity;
	}

	public String getDisName() {
		return this.disName;
	}

	public void setDisName(String disName) {
		this.disName = disName;
	}

	public Integer getCityId() {
		return this.cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getDisSort() {
		return this.disSort;
	}

	public void setDisSort(Integer disSort) {
		this.disSort = disSort;
	}

}