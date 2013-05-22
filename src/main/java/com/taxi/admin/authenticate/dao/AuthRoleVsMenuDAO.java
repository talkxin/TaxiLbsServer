package com.taxi.admin.authenticate.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.taxi.admin.authenticate.model.MenuModel;
import com.taxi.admin.authenticate.model.RoleVsMenuModel;
import com.taxi.admin.base.dao.BaseDAO;

public interface AuthRoleVsMenuDAO {

	/**
	 * 根据角色id查询对应的菜单权限
	 * 
	 * @param roleId
	 * @return
	 */
	public List<RoleVsMenuModel> getRoleVsRoleId(Long roleId)
			throws SQLException;

	/**
	 * 根据角色id查询只只是子菜单的权限
	 * 
	 * @param roleId
	 * @return
	 */
	public List<RoleVsMenuModel> getRoleVsRoleIdByChild(Long roleId)
			throws SQLException;

	/**
	 * 根据角色id查询只只是主菜单的权限
	 * 
	 * @param roleId
	 * @return
	 */
	public List<RoleVsMenuModel> getRoleVsRoleIdByMain(Long roleId)
			throws SQLException;

	/**
	 * 根据菜单id喝角色id查询对应的菜单权限
	 * 
	 * @param menuId
	 * @return
	 */
	public List<RoleVsMenuModel> getRoleVs(Long roleId, Long menuId)
			throws SQLException;

	/**
	 * 删除角色与菜单对应信息
	 * 
	 * @param roleVsMenuModel
	 */
	public void deleteRoleVsMenu(RoleVsMenuModel roleVsMenuModel)
			throws SQLException;

	/**
	 * 根据菜单id查询对应的菜单权限
	 * 
	 * @param menuId
	 * @return
	 */
	public List<RoleVsMenuModel> getRoleVsByMenuId(Long menuId)
			throws SQLException;

	/**
	 * 修改对照表
	 */
	public void roleVsMenuModelUpdate(RoleVsMenuModel r);

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void roleVsMenuModelDelete(Long id);
	
	/**
	 * 存储方法
	 * @param r
	 */
	public void roleVsMenuModeSave(RoleVsMenuModel r);
}
