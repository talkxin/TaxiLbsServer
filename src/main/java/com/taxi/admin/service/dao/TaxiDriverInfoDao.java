package com.taxi.admin.service.dao;

import java.io.File;
import java.sql.SQLException;

import com.taxi.admin.common.Page;
import com.taxi.admin.service.module.TTaxiclientDriverinfoapply;
import com.taxi.admin.service.module.TTaxiserverDriverinfo;

/**
 * 对出租车司机信息的操作
 * 
 * @author talkliu
 * 
 */
public interface TaxiDriverInfoDao {
	/**
	 * 获取司机
	 * 
	 * @param t
	 * @return
	 */
	public Page getDriverList(TTaxiserverDriverinfo t, int pageNo, int pageSize)
			throws SQLException;

	/**
	 * 获取单个对象
	 * 
	 * @return
	 */
	public TTaxiserverDriverinfo getDriverInfoObject(TTaxiserverDriverinfo t)
			throws SQLException;

	/**
	 * 导入一个出租车司机
	 * 
	 * @param t
	 */
	public void isAddDriver(TTaxiserverDriverinfo t) throws SQLException;

	/**
	 * 批量插入出租车司机
	 * 
	 * @param file
	 */
	public void isAddsDriver(File file) throws SQLException;

	/**
	 * 修改司机
	 * 
	 * @param t
	 */
	public void isUpdateDriver(TTaxiserverDriverinfo t) throws SQLException;

	/**
	 * 删除司机
	 * 
	 * @param t
	 */
	public void isDeleteDriver(Long t) throws SQLException;

	/**
	 * 获取实名申请
	 * 
	 * @param t
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	public Page getApplyDriver(TTaxiclientDriverinfoapply t, int pageNo,
			int pageSize) throws SQLException;

	/**
	 * 获取申请对象
	 * 
	 * @param t
	 * @return
	 * @throws SQLException
	 */
	public TTaxiclientDriverinfoapply getApplyDriverObj(
			TTaxiclientDriverinfoapply t) throws SQLException;

	/**
	 * 修改申请
	 * 
	 * @param t
	 * @throws SQLException
	 */
	public void updateApplyDriver(TTaxiclientDriverinfoapply t)
			throws SQLException;
}
