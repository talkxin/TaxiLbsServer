package com.taxi.admin.authenticate.dao;

import java.sql.SQLException;
import java.util.List;

import com.taxi.admin.authenticate.model.MenuModel;
import com.taxi.admin.base.dao.BaseDAO;
import com.taxi.admin.common.Page;

public interface AuthMenuDAO {

	/**
	 * 根据角色查看菜单
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Long> listMainMenu(Long roleId) throws SQLException;

	/**
	 * 根据角色查看子菜单
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Long> listMainChildMenu(Long roleId, Long menuId)
			throws SQLException;

	/**
	 * 查看所有的菜单项
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<MenuModel> listMenu() throws SQLException;

	/**
	 * 根据菜单的父节点查看菜单的子节点
	 * 
	 * @param menuId
	 * @return
	 * @throws SQLException
	 */
	public List<MenuModel> getMenuByParentID(Long menuId) throws SQLException;

	/**
	 * 查看菜单名称是否存在
	 * 
	 * @param title
	 * @return
	 */
	public List<MenuModel> isTitleIn(String title) throws SQLException;

	/**
	 * 获取对象
	 * 
	 * @param id
	 * @return
	 */
	public MenuModel getMenuModelObject(Long id);

	/**
	 * 存储方法
	 */
	public void menuModelSave(MenuModel m);

	/**
	 * 修改方法
	 * 
	 * @param m
	 */
	public void menuModelUpdate(MenuModel m);
	
	/**
	 * 删除
	 * @param id
	 */
	public void menuModelDelete(Long id);
}
