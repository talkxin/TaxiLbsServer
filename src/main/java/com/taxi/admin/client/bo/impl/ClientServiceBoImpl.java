package com.taxi.admin.client.bo.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import net.sf.ehcache.Element;

import com.taxi.admin.client.bo.ClientServiceBo;
import com.taxi.admin.client.dao.ClientServiceDao;
import com.taxi.admin.service.module.TTaxiserverTaxiservice;

public class ClientServiceBoImpl implements ClientServiceBo {
	private ClientServiceDao clientServiceDao;

	public ClientServiceDao getClientServiceDao() {
		return clientServiceDao;
	}

	public void setClientServiceDao(ClientServiceDao clientServiceDao) {
		this.clientServiceDao = clientServiceDao;
	}

	/**
	 * 生成一条订单
	 * 
	 * @param t
	 */
	public TTaxiserverTaxiservice regService(TTaxiserverTaxiservice t)
			throws SQLException {
		return clientServiceDao.regService(t);
	}

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
			throws SQLException {
		return clientServiceDao.getServiceListForUser(t, pageNo, pageSize);
	}

	/**
	 * 通过坐标返回公里数内订单
	 * 
	 * @throws SQLException
	 */
	public List<TTaxiserverTaxiservice> getRandomServiceList(
			TTaxiserverTaxiservice t) throws SQLException {
		return clientServiceDao.getRandomServiceList(t);
	}

	/**
	 * 用于司机取消订单
	 * 
	 * @param serviceID
	 */
	public boolean cancelService(Long serviceID) throws SQLException {
		return clientServiceDao.cancelService(serviceID);
	}

	/**
	 * 乘客删除一个订单
	 * 
	 * @param serviceID
	 */
	public boolean removeService(TTaxiserverTaxiservice t) throws SQLException {
		return clientServiceDao.removeService(t);
	}

	/**
	 * 接受一个订单
	 * 
	 * @param t
	 * @return
	 */
	public boolean chexInService(TTaxiserverTaxiservice t) throws SQLException {
		return clientServiceDao.chexInService(t);
	}

	/**
	 * 完成一个订单
	 * 
	 * @param t
	 */
	public void overService(TTaxiserverTaxiservice t) throws SQLException {
		clientServiceDao.overService(t);
	}

	/**
	 * 返回预约列表
	 * 
	 * @param t
	 * @return
	 * @throws SQLException
	 */
	public List<TTaxiserverTaxiservice> getAppointmentServiceList(
			TTaxiserverTaxiservice t) throws SQLException {
		return clientServiceDao.getAppointmentServiceList(t);
	}
	/**
	 * 查询是否有已接单的，若有则返回，否则就将所有未开始的该人打车订单取消
	 * 
	 * @param t
	 * @return
	 * @throws SQLException
	 */
	public void updateAllSuiji(TTaxiserverTaxiservice t) throws SQLException{
		clientServiceDao.updateAllSuiji(t);
	}
}
