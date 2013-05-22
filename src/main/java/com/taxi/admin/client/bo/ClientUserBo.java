package com.taxi.admin.client.bo;

import java.sql.SQLException;

import com.taxi.admin.service.module.TTaxiserverDriverinfo;
import com.taxi.admin.service.module.TTaxiserverTaxiuser;

public interface ClientUserBo {
	/**
	 * 注册并登陆
	 * 
	 * @param t
	 * @return
	 */
	public TTaxiserverTaxiuser regAndLogin(TTaxiserverTaxiuser t)
			throws SQLException;

	/**
	 * 登陆
	 * 
	 * @param t
	 * @return
	 */
	public TTaxiserverTaxiuser loginIn(TTaxiserverTaxiuser t)
			throws SQLException;

	/**
	 * 验证唯一性
	 * 
	 * @param t
	 * @return
	 */
	public boolean isNewUser(TTaxiserverTaxiuser t) throws SQLException;

	/**
	 * 注册一个司机
	 * 
	 * @param t
	 */
	public void regDriver(TTaxiserverDriverinfo t) throws SQLException;
	
	/**
	 * 获取司机详细信息
	 * 
	 * @param uid
	 * @return
	 */
	public TTaxiserverDriverinfo getDriver(Long uid) throws SQLException;
	
	/**
	 * 通过用户名修改司机信息
	 * 
	 * @param t
	 * @return
	 * @throws SQLException
	 */
	public boolean updateDriverInfo(TTaxiserverDriverinfo t)
			throws SQLException;
	
	/**
	 * 更新司机坐标
	 * @param t
	 * @return
	 * @throws SQLException
	 */
	public boolean updateDriverCoordinate(TTaxiserverDriverinfo t)throws SQLException;
}
