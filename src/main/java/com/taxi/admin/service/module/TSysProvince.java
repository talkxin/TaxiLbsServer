package com.taxi.admin.service.module;

/**
 * TSysProvince entity. @author MyEclipse Persistence Tools
 */

public class TSysProvince implements java.io.Serializable {

	// Fields

	private Integer proId;
	private String proName;
	private Integer proSort;
	private String proRemark;

	// Constructors

	/** default constructor */
	public TSysProvince() {
	}

	/** full constructor */
	public TSysProvince(String proName, Integer proSort, String proRemark) {
		this.proName = proName;
		this.proSort = proSort;
		this.proRemark = proRemark;
	}

	// Property accessors

	public Integer getProId() {
		return this.proId;
	}

	public void setProId(Integer proId) {
		this.proId = proId;
	}

	public String getProName() {
		return this.proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public Integer getProSort() {
		return this.proSort;
	}

	public void setProSort(Integer proSort) {
		this.proSort = proSort;
	}

	public String getProRemark() {
		return this.proRemark;
	}

	public void setProRemark(String proRemark) {
		this.proRemark = proRemark;
	}

}