package com.taxi.admin.service.module;

/**
 * TTaxiserverTaxipayinfo entity. @author MyEclipse Persistence Tools
 */

public class TTaxiserverTaxipayinfo implements java.io.Serializable {

	// Fields

	private Long pid;
	private Long serviceId;
	private Long payType;
	private Long payType2;
	private String payNumber;
	private String payFee;
	private String payTax;
	private String payNumber2;
	private String driverCompany;
	private String plateNumber;

	// Constructors

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public String getDriverCompany() {
		return driverCompany;
	}

	public void setDriverCompany(String driverCompany) {
		this.driverCompany = driverCompany;
	}

	/** default constructor */
	public TTaxiserverTaxipayinfo() {
	}

	/** full constructor */
	public TTaxiserverTaxipayinfo(Long serviceId, Long payType, Long payType2,
			String payNumber, String payFee, String payTax, String payNumber2) {
		this.serviceId = serviceId;
		this.payType = payType;
		this.payType2 = payType2;
		this.payNumber = payNumber;
		this.payFee = payFee;
		this.payTax = payTax;
		this.payNumber2 = payNumber2;
	}

	// Property accessors

	public Long getPid() {
		return this.pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Long getServiceId() {
		return this.serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public Long getPayType() {
		return this.payType;
	}

	public void setPayType(Long payType) {
		this.payType = payType;
	}

	public Long getPayType2() {
		return this.payType2;
	}

	public void setPayType2(Long payType2) {
		this.payType2 = payType2;
	}

	public String getPayNumber() {
		return this.payNumber;
	}

	public void setPayNumber(String payNumber) {
		this.payNumber = payNumber;
	}

	public String getPayFee() {
		return this.payFee;
	}

	public void setPayFee(String payFee) {
		this.payFee = payFee;
	}

	public String getPayTax() {
		return this.payTax;
	}

	public void setPayTax(String payTax) {
		this.payTax = payTax;
	}

	public String getPayNumber2() {
		return this.payNumber2;
	}

	public void setPayNumber2(String payNumber2) {
		this.payNumber2 = payNumber2;
	}

}