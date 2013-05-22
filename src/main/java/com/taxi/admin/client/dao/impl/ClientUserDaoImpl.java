package com.taxi.admin.client.dao.impl;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.taxi.admin.client.dao.ClientUserDao;
import com.taxi.admin.service.module.TTaxiserverDriverinfo;
import com.taxi.admin.service.module.TTaxiserverTaxiuser;

public class ClientUserDaoImpl implements ClientUserDao {
	private SqlMapClient sqlMapClient;

	/**
	 * 注册并登陆
	 * 
	 * @param t
	 * @return
	 */
	public TTaxiserverTaxiuser regAndLogin(TTaxiserverTaxiuser t)
			throws SQLException {
		if (isNewUser(t))
			sqlMapClient.insert("isAddUser", t);
		else
			return null;
		return t;
	}

	/**
	 * 登陆
	 * 
	 * @param t
	 * @return
	 */
	public TTaxiserverTaxiuser loginIn(TTaxiserverTaxiuser t)
			throws SQLException {
		Object obj = sqlMapClient.queryForObject("loginInClient", t);
		return obj == null ? null : (TTaxiserverTaxiuser) obj;
	}

	/**
	 * 验证唯一性
	 * 
	 * @param t
	 * @return
	 * @throws SQLException
	 */
	public boolean isNewUser(TTaxiserverTaxiuser t) throws SQLException {
		Long newU = (Long) sqlMapClient.queryForObject("regForTrue", t);
		return newU == 0 ? true : false;
	}

	/**
	 * 注册一个司机
	 * 
	 * @param t
	 */
	public void regDriver(TTaxiserverDriverinfo t) throws SQLException {
		sqlMapClient.insert("isAddDriver", t);
	}

	/**
	 * 获取司机详细信息
	 * 
	 * @param uid
	 * @return
	 */
	public TTaxiserverDriverinfo getDriver(Long uid) throws SQLException {
		Object obj = sqlMapClient.queryForObject("getDriverInfoObjectForUid",
				uid);
		return obj == null ? null : (TTaxiserverDriverinfo) obj;
	}

	/**
	 * 通过用户名修改司机信息
	 * 
	 * @param t
	 * @return
	 * @throws SQLException
	 */
	public boolean updateDriverInfo(TTaxiserverDriverinfo t)
			throws SQLException {
		return sqlMapClient.update("updateDriverInfoForClient", t) == 0 ? false
				: true;
	}
	/**
	 * 更新司机坐标
	 * @param t
	 * @return
	 * @throws SQLException
	 */
	public boolean updateDriverCoordinate(TTaxiserverDriverinfo t)throws SQLException{
		return sqlMapClient.update("updateDriverCoordinate",t)==0?false:true;
	}

	public SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

}
