package com.taxi.admin.authenticate.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.taxi.admin.authenticate.dao.AuthRoleVsMenuDAO;
import com.taxi.admin.authenticate.model.MenuModel;
import com.taxi.admin.authenticate.model.RoleVsMenuModel;
import com.taxi.admin.base.dao.DaoSupportHibernate;

public class AuthRoleVsMenuDAOImpl implements AuthRoleVsMenuDAO {
	private SqlMapClient sqlMapClient;

	/**
	 * 根据角色id查询对应的菜单权限
	 * 
	 * @param roleId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<RoleVsMenuModel> getRoleVsRoleId(Long roleId)
			throws SQLException {
		// String sqlstr="from RoleVsMenuModel where roleId=?";
		// return this.queryhqlList(sqlstr,new Object[]{roleId});
		return sqlMapClient.queryForList("getRoleVsRoleId", roleId);
	}

	/**
	 * 根据角色id查询只只是主菜单的权限
	 * 
	 * @param roleId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<RoleVsMenuModel> getRoleVsRoleIdByMain(Long roleId)
			throws SQLException {
		// String
		// sql="select * from T_LNZS_ROLE_VS_MENU t  where t.roleid=? and t.menuid in (select menuid from t_lnzs_menu m  where m.menuParentId=0 ) ";
		// String
		// sql="from RoleVsMenuModel where roleId=? and menuId in (select menuId from MenuModel where menuParentId = 0)";
		// return this.queryhqlList(sql, new Object[]{roleId});
		return sqlMapClient.queryForList("getRoleVsRoleIdByMain", roleId);
	}

	/**
	 * 根据角色id查询只只是子菜单的权限
	 * 
	 * @param roleId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<RoleVsMenuModel> getRoleVsRoleIdByChild(Long roleId)
			throws SQLException {
		// String
		// sql="select * from T_LNZS_ROLE_VS_MENU t  where t.roleid=? and t.menuid in (select menuid from t_lnzs_menu m  where m.menuParentId=0 ) ";
		// String
		// sql="from RoleVsMenuModel where roleId=? and menuId in (select menuId from MenuModel where menuParentId <> 0)";
		// return this.queryhqlList(sql, new Object[]{roleId});
		return sqlMapClient.queryForList("getRoleVsRoleIdByChild", roleId);
	}

	/**
	 * 根据菜单id喝角色id查询对应的菜单权限
	 * 
	 * @param menuId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<RoleVsMenuModel> getRoleVs(Long roleId, Long menuId)
			throws SQLException {
		// String sqlstr="from RoleVsMenuModel where roleId=? and menuId=?";
		// return this.queryhqlList(sqlstr,new Object[]{roleId,menuId});
		HashMap sql = new HashMap();
		sql.put("roleId", roleId);
		sql.put("menuId", menuId);
		return sqlMapClient.queryForList("getRoleVs", sql);
	}

	/**
	 * 删除角色与菜单对应信息
	 * 
	 * @param roleVsMenuModel
	 * @throws SQLException
	 */
	@Override
	public void deleteRoleVsMenu(RoleVsMenuModel roleVsMenuModel)
			throws SQLException {
		// roleVsMenuModel.getId()
		// this.delete(roleVsMenuModel);
		sqlMapClient.delete("deleteRoleVsMenu", roleVsMenuModel.getId());
	}

	/**
	 * 根据菜单id查询对应的菜单权限
	 * 
	 * @param menuId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<RoleVsMenuModel> getRoleVsByMenuId(Long menuId)
			throws SQLException {
		// String sqlstr = "from RoleVsMenuModel where menuId=?";
		// return this.queryhqlList(sqlstr, new Object[] { menuId });
		return sqlMapClient.queryForList("getRoleVsByMenuId", menuId);
	}

	/**
	 * 修改对照表
	 */
	public void roleVsMenuModelUpdate(RoleVsMenuModel r) {
		try {
			sqlMapClient.update("roleVsMenuModelUpdate", r);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 删除
	 * @param id
	 */
	public void roleVsMenuModelDelete(Long id) {
		try {
			sqlMapClient.delete("roleVsMenuModelDelete", id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 存储方法
	 * @param r
	 */
	public void roleVsMenuModeSave(RoleVsMenuModel r){
		try {
			sqlMapClient.insert("roleVsMenuModeSave",r);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}

	public SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}


	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

}
