package com.taxi.admin.authenticate.model;


/**
 * Title. 角色对应菜单信息<br>
 * Description. 角色对应菜单
 * <p>
 * Copyright: Copyright (c) 2011-06-1 上午09:40:52
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: wangzhen@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
public class RoleVsMenuModel implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Long  id;
	/** 角色id */
	private Long roleId;

	/** 菜单id */
	private Long menuId;
	
	/**
	 * 读写权限
	 */
	private int menuOperateType;
	
	/**
	 * 人员权限
	 */
	private int menuPersonScope;
	
	/**
	 * 地区限制
	 */
	private String menuAreaScope;

    public Long getId() {
    	return id;
    }

    public void setId(Long id) {
    	this.id = id;
    }

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
	 * 得到菜单id
	 * @return
	 */
	public Long getMenuId() {
		return menuId;
	}

	/**
	 * 设置菜单id
	 * @param menuId
	 */
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	/**
	 * 得到读写权限
	 * @return
	 */
    public int getMenuOperateType() {
    	return menuOperateType;
    }

	/**
	 * 设置读写权限
	 * @param menuOperateType
	 */
    public void setMenuOperateType(int menuOperateType) {
    	this.menuOperateType = menuOperateType;
    }

	/**
	 * 得到人员权限
	 * @return
	 */
    public int getMenuPersonScope() {
    	return menuPersonScope;
    }

	/**
	 * 设置人员权限
	 * @param menuPersonScope
	 */
    public void setMenuPersonScope(int menuPersonScope) {
    	this.menuPersonScope = menuPersonScope;
    }

	
    public String getMenuAreaScope() {
    	return menuAreaScope;
    }

	
    public void setMenuAreaScope(String menuAreaScope) {
    	this.menuAreaScope = menuAreaScope;
    }


	

}
