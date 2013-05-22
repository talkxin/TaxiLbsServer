package com.taxi.admin.service.module;

/**
 * TTaxiserverTaxiservice entity. @author MyEclipse Persistence Tools
 */

public class TTaxiserverTaxiservice implements java.io.Serializable {

	// Fields

	private Long serviceId;
	private Long did;
	private String userName;
	private String userNambr;
	private Long uid;
	private String city;
	private String startAddLon;
	private String startAddLat;
	private String endAdd;
	private String startTime;
	private String upTime;
	private String endTime;
	private String appointmentTime;
	private String appointmentAdd;
	private String appointmentEnd;
	private String appointmentEndTime;
	private String newEndAdd;
	private String newEndTime;
	private String kmNumber;
	private String payNumber;
	private Integer serviceType;
	private Integer serviceEnd;
	private String driverCompany;
	private String plateNumber;
	private String passengerBlue;
	private String driverBlue;
	private String other;
	private String orderAddTime;
	private Long goTime;

	// Constructors

	public String getAppointmentEnd() {
		return appointmentEnd;
	}

	public void setAppointmentEnd(String appointmentEnd) {
		this.appointmentEnd = appointmentEnd;
	}

	public Long getGoTime() {
		return goTime;
	}

	public void setGoTime(Long goTime) {
		this.goTime = goTime;
	}

	public String getAppointmentAdd() {
		return appointmentAdd;
	}

	public void setAppointmentAdd(String appointmentAdd) {
		this.appointmentAdd = appointmentAdd;
	}

	public String getOrderAddTime() {
		return orderAddTime;
	}

	public void setOrderAddTime(String orderAddTime) {
		this.orderAddTime = orderAddTime;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	/** default constructor */
	public TTaxiserverTaxiservice() {
	}

	public String getDriverCompany() {
		return driverCompany;
	}

	public void setDriverCompany(String driverCompany) {
		this.driverCompany = driverCompany;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	/** full constructor */
	public TTaxiserverTaxiservice(Long did, String userName, String userNambr,
			Long uid, String startAdd, String endAdd, String startTime,
			String upTime, String endTime, String appointmentTime,
			String appointmentAdd, String appointmentEnd,
			String appointmentEndTime, String newEndAdd, String newEndTime,
			String kmNumber, String payNumber, Integer serviceType,
			Integer serviceEnd) {
		this.did = did;
		this.userName = userName;
		this.userNambr = userNambr;
		this.uid = uid;
		this.endAdd = endAdd;
		this.startTime = startTime;
		this.upTime = upTime;
		this.endTime = endTime;
		this.appointmentTime = appointmentTime;
		this.appointmentAdd = appointmentAdd;
		this.appointmentEnd = appointmentEnd;
		this.appointmentEndTime = appointmentEndTime;
		this.newEndAdd = newEndAdd;
		this.newEndTime = newEndTime;
		this.kmNumber = kmNumber;
		this.payNumber = payNumber;
		this.serviceType = serviceType;
		this.serviceEnd = serviceEnd;
	}

	// Property accessors

	public Long getServiceId() {
		return this.serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public Long getDid() {
		return this.did;
	}

	public void setDid(Long did) {
		this.did = did;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNambr() {
		return this.userNambr;
	}

	public void setUserNambr(String userNambr) {
		this.userNambr = userNambr;
	}

	public Long getUid() {
		return this.uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getEndAdd() {
		return this.endAdd;
	}

	public void setEndAdd(String endAdd) {
		this.endAdd = endAdd;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getUpTime() {
		return this.upTime;
	}

	public void setUpTime(String upTime) {
		this.upTime = upTime;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getAppointmentTime() {
		return this.appointmentTime;
	}

	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public String getappointmentAdd() {
		return this.appointmentAdd;
	}

	public void setappointmentAdd(String appointmentAdd) {
		this.appointmentAdd = appointmentAdd;
	}

	public String getappointmentEnd() {
		return this.appointmentEnd;
	}

	public void setappointmentEnd(String appointmentEnd) {
		this.appointmentEnd = appointmentEnd;
	}

	public String getAppointmentEndTime() {
		return this.appointmentEndTime;
	}

	public void setAppointmentEndTime(String appointmentEndTime) {
		this.appointmentEndTime = appointmentEndTime;
	}

	public String getNewEndAdd() {
		return this.newEndAdd;
	}

	public void setNewEndAdd(String newEndAdd) {
		this.newEndAdd = newEndAdd;
	}

	public String getNewEndTime() {
		return this.newEndTime;
	}

	public void setNewEndTime(String newEndTime) {
		this.newEndTime = newEndTime;
	}

	public String getKmNumber() {
		return this.kmNumber;
	}

	public void setKmNumber(String kmNumber) {
		this.kmNumber = kmNumber;
	}

	public String getPayNumber() {
		return this.payNumber;
	}

	public void setPayNumber(String payNumber) {
		this.payNumber = payNumber;
	}

	public Integer getServiceType() {
		return this.serviceType;
	}

	public void setServiceType(Integer serviceType) {
		this.serviceType = serviceType;
	}

	public Integer getServiceEnd() {
		return this.serviceEnd;
	}

	public void setServiceEnd(Integer serviceEnd) {
		this.serviceEnd = serviceEnd;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPassengerBlue() {
		return passengerBlue;
	}

	public void setPassengerBlue(String passengerBlue) {
		this.passengerBlue = passengerBlue;
	}

	public String getDriverBlue() {
		return driverBlue;
	}

	public void setDriverBlue(String driverBlue) {
		this.driverBlue = driverBlue;
	}

	public String getStartAddLon() {
		return startAddLon;
	}

	public void setStartAddLon(String startAddLon) {
		this.startAddLon = startAddLon;
	}

	public String getStartAddLat() {
		return startAddLat;
	}

	public void setStartAddLat(String startAddLat) {
		this.startAddLat = startAddLat;
	}

}