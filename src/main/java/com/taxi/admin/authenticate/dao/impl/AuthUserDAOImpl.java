package com.taxi.admin.authenticate.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.taxi.admin.authenticate.dao.AuthUserDAO;
import com.taxi.admin.authenticate.model.UserModel;
import com.taxi.admin.base.dao.DaoSupportHibernate;
import com.taxi.admin.common.Page;

public class AuthUserDAOImpl implements AuthUserDAO {
	private SqlMapClient sqlMapClient;

	/**
	 * 查看所有的用户
	 */
	@Override
	public Page listUser(int pageNo, int pageSize) throws SQLException {
		List<UserModel> list = sqlMapClient.queryForList("listUser",
				(pageNo - 1) * pageSize, pageSize);
		Page page = new Page();
		page.search(list.size());
		page.turnToPage(pageNo);
		page.setList(list);
		return page;
		// String sqlStr = "from UserModel order by userId desc";
		// return this.queryList(sqlStr, null, pageNo, pageSize);

	}

	/**
	 * 登录名的唯一性验证
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UserModel> validatorLoginName(UserModel userModel) {
		// return
		// getHibernateTemplate().find("from UserModel where loginName=?",
		// userModel.getLoginName());
		try {
			return sqlMapClient.queryForList("validatorLoginName", userModel);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 验证登录名唯一
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UserModel> validatorLoginNaByNameAndId(UserModel userModel) {
		// return getHibernateTemplate().find(
		// "from UserModel where loginName=? and userId!=?",
		// userModel.getLoginName(), userModel.getUserId());
		try {
			return sqlMapClient.queryForList("validatorLoginNaByNameAndId",
					userModel);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据登录名或得用户列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UserModel> listUser(String loginName) {
		// return getHibernateTemplate().find(
		// "from UserModel where loginName = ?", loginName);
		try {
			return sqlMapClient.queryForList("listUser1", loginName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据用户名喝密码查看用户
	 */
	@SuppressWarnings("unchecked")
	@Override
	public UserModel checkUser(String loginName, String password) {
		// List<UserModel> list =
		// getHibernateTemplate().find("from UserModel where loginName= ? and password= ?",
		// loginName, password);
		// String sql = "from UserModel where loginName= ? and password= ?";
		// List<UserModel> list = this.queryhqlList(sql, new Object[] {
		// loginName,
		// password });
		// if (list != null && list.size() > 0) {
		// return list.get(0);
		// }
		// return null;
		HashMap sql = new HashMap();
		sql.put("loginName", loginName);
		sql.put("password", password);
		try {
			return (UserModel) sqlMapClient.queryForObject("checkUser", sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据角色id查看用户
	 * 
	 * @param roleId
	 * @return
	 */
	@Override
	public List<UserModel> listUserByRoleId(Long roleId) {
		// String sql = "from UserModel where roleId.roleId=?";
		// return this.queryhqlList(sql, new Object[] { roleId });
		try {
			return sqlMapClient.queryForList("listUserByRoleId", roleId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 存储
	 * 
	 * @param u
	 */
	public void userModelSave(UserModel u) {
		try {
			sqlMapClient.insert("userModelSave", u);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 修改方法
	 * 
	 * @param u
	 */
	public void userModelUpdate(UserModel u) {
		try {
			sqlMapClient.update("userModelUpdate", u);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void userModelDelete(Long id) {
		try {
			sqlMapClient.delete("userModelDelete", id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 获取对象
	 * 
	 * @param id
	 * @return
	 */
	public UserModel getUserModel(Long id) {
		try {
			return (UserModel) sqlMapClient.queryForObject("getUserModel", id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

}
