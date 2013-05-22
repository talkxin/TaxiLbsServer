package com.taxi.admin.client.bo;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.taxi.admin.service.module.TTaxiserverTaxiservice;

public interface ClientServiceBo {
	/**
	 * 生成一条订单
	 * 
	 * @param t
	 */
	public TTaxiserverTaxiservice regService(TTaxiserverTaxiservice t) throws SQLException;

	/**
	 * 查询订单，可分为司机或乘客（分页）
	 * 
	 * @param t
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public HashMap<String, Object> getServiceListForUser(
			TTaxiserverTaxiservice t, int pageNo, int pageSize)
			throws SQLException;

	/**
	 * 通过坐标返回公里数内订单
	 */
	public List<TTaxiserverTaxiservice> getRandomServiceList(
			TTaxiserverTaxiservice t)throws SQLException;

	/**
	 * 用于司机取消订单
	 * 
	 * @param serviceID
	 */
	public boolean cancelService(Long serviceID) throws SQLException;

	/**
	 * 乘客删除一个订单
	 * 
	 * @param serviceID
	 */
	public boolean removeService(TTaxiserverTaxiservice t) throws SQLException;

	/**
	 * 接受一个订单
	 * 
	 * @param t
	 * @return
	 */
	public boolean chexInService(TTaxiserverTaxiservice t) throws SQLException;

	/**
	 * 完成一个订单
	 * 
	 * @param t
	 */
	public void overService(TTaxiserverTaxiservice t) throws SQLException;
	/**
	 * 返回预约列表
	 * @param t
	 * @return
	 * @throws SQLException
	 */
	public List<TTaxiserverTaxiservice> getAppointmentServiceList(TTaxiserverTaxiservice t)throws SQLException;	
	/**
	 * 查询是否有已接单的，若有则返回，否则就将所有未开始的该人打车订单取消
	 * 
	 * @param t
	 * @return
	 * @throws SQLException
	 */
	public void updateAllSuiji(TTaxiserverTaxiservice t) throws SQLException;
}
