package com.taxi.admin.client.bo.impl;

import java.sql.SQLException;

import com.taxi.admin.client.bo.ClientUserBo;
import com.taxi.admin.client.dao.ClientUserDao;
import com.taxi.admin.service.module.TTaxiserverDriverinfo;
import com.taxi.admin.service.module.TTaxiserverTaxiuser;

public class ClientUserBoimpl implements ClientUserBo {
	private ClientUserDao clientUserDao;

	/**
	 * 注册并登陆
	 * 
	 * @param t
	 * @return
	 */
	public TTaxiserverTaxiuser regAndLogin(TTaxiserverTaxiuser t)
			throws SQLException {
		return clientUserDao.regAndLogin(t);
	}

	/**
	 * 登陆
	 * 
	 * @param t
	 * @return
	 */
	public TTaxiserverTaxiuser loginIn(TTaxiserverTaxiuser t)
			throws SQLException {
		return clientUserDao.loginIn(t);
	}

	/**
	 * 验证唯一性
	 * 
	 * @param t
	 * @return
	 */
	public boolean isNewUser(TTaxiserverTaxiuser t) throws SQLException {
		return clientUserDao.isNewUser(t);
	}

	/**
	 * 注册一个司机
	 * 
	 * @param t
	 */
	public void regDriver(TTaxiserverDriverinfo t) throws SQLException {
		clientUserDao.regDriver(t);
	}

	/**
	 * 获取司机详细信息
	 * 
	 * @param uid
	 * @return
	 */
	public TTaxiserverDriverinfo getDriver(Long uid) throws SQLException {
		return clientUserDao.getDriver(uid);
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
		return clientUserDao.updateDriverInfo(t);
	}

	/**
	 * 更新司机坐标
	 * 
	 * @param t
	 * @return
	 * @throws SQLException
	 */
	public boolean updateDriverCoordinate(TTaxiserverDriverinfo t)
			throws SQLException {
		return clientUserDao.updateDriverCoordinate(t);
	}

	public ClientUserDao getClientUserDao() {
		return clientUserDao;
	}

	public void setClientUserDao(ClientUserDao clientUserDao) {
		this.clientUserDao = clientUserDao;
	}

}
