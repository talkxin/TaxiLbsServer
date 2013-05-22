package com.taxi.admin.authenticate.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.management.relation.Role;

import com.ibatis.common.util.PaginatedList;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.taxi.admin.authenticate.dao.AuthRoleDAO;
import com.taxi.admin.authenticate.model.RoleModel;
import com.taxi.admin.base.dao.DaoSupportHibernate;
import com.taxi.admin.common.Page;

public class AuthRoleDAOImpl implements AuthRoleDAO {
	private SqlMapClient sqlMapClient;

	/**
	 * 查看所有的角色项
	 */
	@Override
	public Page listRole(int pageNo, int pageSize) throws SQLException {
		List<RoleModel> list = sqlMapClient.queryForList("listRole",
				(pageNo - 1) * pageSize, pageSize);
		Page page = new Page();
		page.search(list.size());
		page.turnToPage(pageNo);
		page.setList(list);
		return page;
		// String sqlStr = "from RoleModel order by roleId desc";
		// return this.queryList(sqlStr, null, pageNo, pageSize);
	}

	/**
	 * 存储
	 */
	public void authRoleSave(RoleModel r) {
		try {
			sqlMapClient.insert("authRoleSave", r);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 修改
	 * 
	 * @param r
	 */
	public void authRoleUpdate(RoleModel r) {
		try {
			sqlMapClient.update("authRoleUpdate", r);
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
	public void authRoleDelete(Long id) {
		try {
			sqlMapClient.delete("authRoleDelete", id);
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
	public RoleModel getAuthRoleObject(Long id) {
		try {
			return (RoleModel) sqlMapClient.queryForObject("getAuthRoleObject",
					id);
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
