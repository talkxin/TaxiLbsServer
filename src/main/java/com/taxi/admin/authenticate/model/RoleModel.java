package com.taxi.admin.authenticate.model;


/**
 * Title. 角色对象 <br>
 * Description. 角色对象
 * <p>
 * Copyright: Copyright (c) 2011-05-31 上午09:53:34
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: wangzhen@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
public class RoleModel implements java.io.Serializable  {

	private static final long serialVersionUID = 1L;
	/** 角色id */
	private Long roleId;

	/** 角色名称 */
	private String roleTitle;

	/**
	 * 角色描述
	 */
	private String roleDescription;

	/**
	 * 得到角色id
	 * @return
	 */
    public Long getRoleId() {
    	return roleId;
    }

	/**
	 * 设置角色id
	 * @param roleId
	 */
    public void setRoleId(Long roleId) {
    	this.roleId = roleId;
    }

	/**
	 * 得到角色名称
	 * @return
	 */
    public String getRoleTitle() {
    	return roleTitle;
    }

	/**
	 * 设置角色名称
	 * @param roleTitle
	 */
    public void setRoleTitle(String roleTitle) {
    	this.roleTitle = roleTitle;
    }

	/**
	 * 得到角色描述
	 * @return
	 */
    public String getRoleDescription() {
    	return roleDescription;
    }

	/**
	 * 设置角色描述
	 * @param roleDescription
	 */
    public void setRoleDescription(String roleDescription) {
    	this.roleDescription = roleDescription;
    }

	
    public static long getSerialversionuid() {
    	return serialVersionUID;
    }

	
}
