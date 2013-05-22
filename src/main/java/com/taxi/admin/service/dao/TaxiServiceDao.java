package com.taxi.admin.service.dao;

import java.sql.SQLException;

import com.taxi.admin.common.Page;
import com.taxi.admin.service.module.TTaxiserverTaxiservice;

/**
 * 获取打车流水单
 * 
 * @author talkliu
 * 
 */
public interface TaxiServiceDao {
	/**
	 * 获取出租车服务流水
	 * 
	 * @return
	 */
	public Page getTaxiServiceList(TTaxiserverTaxiservice t, int pageNo,
			int pageSize) throws SQLException;

	/**
	 * 添加服务
	 * 
	 * @param t
	 */
	public void addTaxiService(TTaxiserverTaxiservice t) throws SQLException;
}
