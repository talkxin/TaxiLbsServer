package com.taxi.admin.authenticate.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Title. 用户对象<br>
 * Description. 用户对象
 * <p>
 * Copyright: Copyright (c) 2010-12-2 上午09:34:48
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: wangzhen@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
public class UserModel {
	/**
	 * 对应权限
	 */
	private String roleTitle;

	public String getRoleTitle() {
		return roleTitle;
	}

	public void setRoleTitle(String roleTitle) {
		this.roleTitle = roleTitle;
	}

	/**
	 * 用户主键
	 */
	private Long userId;

	/**
	 * 用户名称
	 */
	private String userName;

	/**
	 * 登陆名
	 */
	private String loginName;

	/**
	 * 登陆密码
	 */
	private String password;

	/**
	 * 电子邮件
	 */
	private String email;

	private Long roleId;

	/**
	 * 获取 userId
	 * 
	 * @return userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 设置 userId
	 * 
	 * @param userId
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 获取 userName
	 * 
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 设置 userName
	 * 
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 获取 loginName
	 * 
	 * @return loginName
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * 设置 loginName
	 * 
	 * @param loginName
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * 获取 loginPassword
	 * 
	 * @return loginPassword
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 设置 loginPassword
	 * 
	 * @param loginPassword
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 获取 email
	 * 
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 设置 email
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

}
