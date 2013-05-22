package com.taxi.admin.authenticate.dao;

import java.sql.SQLException;
import java.util.List;

import com.taxi.admin.authenticate.model.UserModel;
import com.taxi.admin.base.dao.BaseDAO;
import com.taxi.admin.common.Page;

public interface AuthUserDAO {

	/**
	 * 查看所有的用户
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Page listUser(int pageNo, int pageSize) throws SQLException;

	/**
	 * 登录名的唯一性验证
	 */
	@SuppressWarnings("unchecked")
	public List<UserModel> validatorLoginName(UserModel userModel);

	/**
	 * 验证登录名唯一
	 */
	@SuppressWarnings("unchecked")
	public List<UserModel> validatorLoginNaByNameAndId(UserModel userModel);

	/**
	 * 根据登录名或得用户列表
	 * 
	 * @param loginName
	 *            登录名
	 * @return 用户列表
	 */
	public List<UserModel> listUser(String loginName);

	/**
	 * 根据登录名和密码获取用户对象
	 * 
	 * @param loginName
	 * @param password
	 * @return
	 */
	public UserModel checkUser(String loginName, String password);

	/**
	 * 根据角色id查看用户
	 * 
	 * @param roleId
	 * @return
	 */
	public List<UserModel> listUserByRoleId(Long roleId);

	/**
	 * 存储
	 * 
	 * @param u
	 */
	public void userModelSave(UserModel u);

	/**
	 * 修改方法
	 * 
	 * @param u
	 */
	public void userModelUpdate(UserModel u);

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void userModelDelete(Long id);

	/**
	 * 获取对象
	 * 
	 * @param id
	 * @return
	 */
	public UserModel getUserModel(Long id);

}
