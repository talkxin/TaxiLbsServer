package com.taxi.admin.client.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.taxi.admin.client.dao.ClientServiceDao;
import com.taxi.admin.service.module.TTaxiserverTaxiservice;

public class ClientServiceDaoImpl implements ClientServiceDao {
	private SqlMapClient sqlMapClient;

	/**
	 * 生成一条订单
	 * 
	 * @param t
	 * @throws SQLException
	 */
	public TTaxiserverTaxiservice regService(TTaxiserverTaxiservice t)
			throws SQLException {
		sqlMapClient.insert("regService", t);
		return t;
	}

	/**
	 * 查询订单，可分为司机或乘客（分页）
	 * 
	 * @param t
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	public HashMap<String, Object> getServiceListForUser(
			TTaxiserverTaxiservice t, int pageNo, int pageSize)
			throws SQLException {
		String sql = "getServiceListForUser";
		String countSql = "getServiceListForUserCount";
		if (t.getOther() != null && t.getOther().equals("appointment")) {
			t.setServiceType(1);
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		Long count = (Long) sqlMapClient.queryForObject(countSql, t);
		map.put("count", count);
		map.put("pageNo", pageNo);
		map.put("pageSize", pageSize);
		List<TTaxiserverTaxiservice> list = sqlMapClient.queryForList(sql, t,
				(pageNo - 1) * pageSize, pageSize);
		map.put("list", list);
		return map;
	}

	/**
	 * 通过坐标返回公里数内订单
	 * 
	 * @throws SQLException
	 */
	public List<TTaxiserverTaxiservice> getRandomServiceList(
			TTaxiserverTaxiservice t) throws SQLException {
		// if (t.getOther() != null && !t.getOther().equals(""))
		return sqlMapClient.queryForList("getRandomServiceList", t);
		// else
		// return sqlMapClient.queryForList("getRandomServiceListForCity", t);
	}

	/**
	 * 用于司机取消订单
	 * 
	 * @param serviceID
	 * @throws SQLException
	 */
	public boolean cancelService(Long serviceID) throws SQLException {
		return sqlMapClient.update("cancelService", serviceID) > 0;
	}

	/**
	 * 乘客删除一个订单
	 * 
	 * @param serviceID
	 * @throws SQLException
	 */
	public boolean removeService(TTaxiserverTaxiservice t) throws SQLException {
		return sqlMapClient.update("deleteOneDidService", t) > 0;
	}

	/**
	 * 接受一个订单
	 * 
	 * @param t
	 * @return
	 * @throws SQLException
	 */
	public boolean chexInService(TTaxiserverTaxiservice t) throws SQLException {
		return chexIn(t, sqlMapClient);
	}

	/**
	 * 完成一个订单
	 * 
	 * @param t
	 * @throws SQLException
	 */
	public void overService(TTaxiserverTaxiservice t) throws SQLException {
		sqlMapClient.update("overService", t);
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
		return sqlMapClient.queryForList("getAppointmentServiceList", t);
	}

	/**
	 * 查询是否有已接单的，若有则返回，否则就将所有未开始的该人打车订单取消
	 * 
	 * @param t
	 * @return
	 * @throws SQLException
	 */
	public void updateAllSuiji(TTaxiserverTaxiservice t) throws SQLException {
		sqlMapClient.update("deleteAllDidSuiji", t);
	}

	public SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * 使线程安全，顺序处理订单,先到先得
	 * 
	 * @param t
	 * @return
	 * @throws SQLException
	 */
	private static synchronized boolean chexIn(TTaxiserverTaxiservice t,
			SqlMapClient sql) throws SQLException {
		int i = sql.update("chexInService", t);
		return i > 0;
	}

}
