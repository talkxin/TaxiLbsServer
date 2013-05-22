package com.taxi.admin.authenticate.dao;

import java.sql.SQLException;

import com.taxi.admin.authenticate.model.RoleModel;
import com.taxi.admin.base.dao.BaseDAO;
import com.taxi.admin.common.Page;

public interface AuthRoleDAO {

	/**
	 * 查看所有的角色项
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Page listRole(int pageNo, int pageSize) throws SQLException;

	/**
	 * 存储
	 */
	public void authRoleSave(RoleModel r);

	/**
	 * 修改
	 * 
	 * @param r
	 */
	public void authRoleUpdate(RoleModel r);

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void authRoleDelete(Long id);

	/**
	 * 获取对象
	 * 
	 * @param id
	 * @return
	 */
	public RoleModel getAuthRoleObject(Long id);
}
