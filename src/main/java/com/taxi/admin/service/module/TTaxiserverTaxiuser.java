package com.taxi.admin.service.module;

/**
 * TTaxiserverTaxiuser entity. @author MyEclipse Persistence Tools
 */

public class TTaxiserverTaxiuser implements java.io.Serializable {

	// Fields

	private Long uid;
	private String phoneNumber;
	private String userName;
	private String loginName;
	private String password;
	private String email;
	private Integer roleid;
	private Integer isLogin;

	// Constructors

	/** default constructor */
	public TTaxiserverTaxiuser() {
	}

	/** full constructor */
	public TTaxiserverTaxiuser(String phoneNumber, String userName,
			String loginName, String password, String email, Integer roleid,
			Integer isLogin) {
		this.phoneNumber = phoneNumber;
		this.userName = userName;
		this.loginName = loginName;
		this.password = password;
		this.email = email;
		this.roleid = roleid;
		this.isLogin = isLogin;
	}

	// Property accessors

	public Long getUid() {
		return this.uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getRoleid() {
		return this.roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	public Integer getIsLogin() {
		return this.isLogin;
	}

	public void setIsLogin(Integer isLogin) {
		this.isLogin = isLogin;
	}

}