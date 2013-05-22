package com.taxi.admin.service.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.taxi.admin.common.Page;
import com.taxi.admin.service.module.TTaxiserverTaxiuser;

/**
 * 对用户操作
 * 
 * @author talkliu
 * 
 */
public interface TaxiUserDao {

	/**
	 * 获取用户信息（带查询）
	 * 
	 * @param t
	 * @return
	 */
	public Page getUserList(TTaxiserverTaxiuser t, int pageNo, int pageSize)
			throws SQLException;

	/**
	 * 获取单个用户对象
	 * 
	 * @param t
	 * @return
	 */
	public TTaxiserverTaxiuser getUserObject(Long t) throws SQLException;

	/**
	 * 添加用户
	 * 
	 * @param t
	 */
	public Long isAddUser(TTaxiserverTaxiuser t) throws SQLException;

	/**
	 * 验证用户唯一性
	 * 
	 * @param t
	 * @return
	 * @throws SQLException
	 */
	public int isNewUser(TTaxiserverTaxiuser t) throws SQLException;

	/**
	 * 修改用户
	 * 
	 * @param t
	 */
	public void isUpdateUser(TTaxiserverTaxiuser t) throws SQLException;

	/**
	 * 禁止用户登录
	 * 
	 * @param t
	 */
	public void isOutUser(TTaxiserverTaxiuser t) throws SQLException;

	/**
	 * 批量添加用户
	 * 
	 * @param li
	 */
	public List<Long> isAddsUser(List<TTaxiserverTaxiuser> li)
			throws SQLException;

	/**
	 * 返回验证对象
	 */
	public HashMap<String, Object> getAlluserMap()throws SQLException;
}
