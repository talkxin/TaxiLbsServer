package com.taxi.admin.authenticate.model;

/**
 * 菜单对象
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2012-1-31 下午4:18:02
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: wangzhen@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */

public class MenuModel implements java.io.Serializable {

    // Fields

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**菜单id*/
	private Long menuId;

	/**菜单名称*/
    private String menuTitle;

    /**菜单类型*/
    private int menuType;

    /**菜单描述*/
    private String menuDescription;

    /**菜单链接*/
    private String menuLink;

    /**菜单读写权限*/
    private int menuOperateType;

    /**菜单地区限制*/
    private int menuAreaScope;

    /**菜单父节点*/
    private Long menuParentId;
    
    /**菜单人员限制*/
    private int menuPsersonScope;

	
    public Long getMenuId() {
    	return menuId;
    }

	
    public void setMenuId(Long menuId) {
    	this.menuId = menuId;
    }

	
    public String getMenuTitle() {
    	return menuTitle;
    }

	
    public void setMenuTitle(String menuTitle) {
    	this.menuTitle = menuTitle;
    }

	
    public int getMenuType() {
    	return menuType;
    }

	
    public void setMenuType(int menuType) {
    	this.menuType = menuType;
    }

	
    public String getMenuDescription() {
    	return menuDescription;
    }

	
    public void setMenuDescription(String menuDescription) {
    	this.menuDescription = menuDescription;
    }

	
    public String getMenuLink() {
    	return menuLink;
    }

	
    public void setMenuLink(String menuLink) {
    	this.menuLink = menuLink;
    }

	
    public int getMenuOperateType() {
    	return menuOperateType;
    }

	
    public void setMenuOperateType(int menuOperateType) {
    	this.menuOperateType = menuOperateType;
    }

	
    public int getMenuAreaScope() {
    	return menuAreaScope;
    }

	
    public void setMenuAreaScope(int menuAreaScope) {
    	this.menuAreaScope = menuAreaScope;
    }

	
    public Long getMenuParentId() {
    	return menuParentId;
    }

	
    public void setMenuParentId(Long menuParentId) {
    	this.menuParentId = menuParentId;
    }

	
    public static long getSerialversionuid() {
    	return serialVersionUID;
    }

	
    public int getMenuPsersonScope() {
    	return menuPsersonScope;
    }

	
    public void setMenuPsersonScope(int menuPsersonScope) {
    	this.menuPsersonScope = menuPsersonScope;
    }

   

}