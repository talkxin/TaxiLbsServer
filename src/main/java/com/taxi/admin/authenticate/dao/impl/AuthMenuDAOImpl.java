package com.taxi.admin.authenticate.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.jdbc.object.SqlCall;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibm.disthub2.impl.formats.Envelop.Constants.querySubscriptionsReply_type;
import com.taxi.admin.authenticate.dao.AuthMenuDAO;
import com.taxi.admin.authenticate.model.MenuModel;
import com.taxi.admin.base.dao.DaoSupportHibernate;

public class AuthMenuDAOImpl implements AuthMenuDAO {
	private SqlMapClient sqlMapClient;

	/**
	 * 根据角色查看菜单
	 * 
	 * @return @
	 */
	@Override
	public List<Long> listMainMenu(Long roleId) throws SQLException {
		// String sqlStr =
		// "select r.menuId from t_auth_menu m,t_auth_role_vs_menu r  where r.roleid=? and m.menuid=r.menuid and m.menuParentId=0 order by r.menuid";
		// return this.querysqlList(sqlStr, new Object[] { roleId });
		return sqlMapClient.queryForList("listMainMenu", roleId);
	}

	/**
	 * 根据角色查看子菜单
	 * 
	 * @return
	 * @throws SQLException
	 *             @
	 */
	@Override
	public List<Long> listMainChildMenu(Long roleId, Long menuId)
			throws SQLException {
		// String sqlStr =
		// "select r.menuId from t_auth_menu m,t_auth_role_vs_menu r  where r.roleid=? and m.menuid=r.menuid and m.menuParentId=?  order by r.menuid";
		// return this.querysqlList(sqlStr, new Object[] { roleId, menuId });
		HashMap sql = new HashMap();
		sql.put("roleid", roleId);
		sql.put("menuParentId", menuId);
		return sqlMapClient.queryForList("listMainChildMenu", sql);
	}

	/**
	 * 查看所有的菜单项
	 * 
	 * @throws SQLException
	 */
	@Override
	public List<MenuModel> listMenu() throws SQLException {

		// String sqlStr =
		// "from MenuModel where menuParentId=0 order by menuId";
		// return this.queryhqlList(sqlStr.toString(), null);
		return sqlMapClient.queryForList("listMenu");
	}

	/**
	 * 根据菜单的父节点查看菜单的子节点
	 * 
	 * @param menuId
	 * @return
	 * @throws SQLException
	 *             @
	 */
	@Override
	public List<MenuModel> getMenuByParentID(Long menuId) throws SQLException {
		// String sqlStr =
		// "from MenuModel where menuParentId=? order by menuId";
		// return this.queryhqlList(sqlStr.toString(), new Object[] { menuId });
		return sqlMapClient.queryForList("getMenuByParentID", menuId);
	}

	/**
	 * 查看菜单名称是否存在
	 * 
	 * @param title
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<MenuModel> isTitleIn(String title) throws SQLException {
		// String sqlStr = "from MenuModel where menuTitle=?";
		// return this.queryhqlList(sqlStr, new Object[] { title });
		return sqlMapClient.queryForList("isTitleIn", title);
	}

	/**
	 * 获取对象
	 * 
	 * @param id
	 * @return
	 */
	public MenuModel getMenuModelObject(Long id) {
		try {
			return (MenuModel) sqlMapClient.queryForObject("getMenuModelObject", id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 存储方法
	 */
	public void menuModelSave(MenuModel m) {
		try {
			sqlMapClient.insert("menuModelSave", m);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 修改方法
	 * 
	 * @param m
	 */
	public void menuModelUpdate(MenuModel m) {
		try {
			sqlMapClient.update("menuModelUpdate", m);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除
	 * @param id
	 */
	public void menuModelDelete(Long id){
		try {
			sqlMapClient.delete("menuModelDelete",id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

}
