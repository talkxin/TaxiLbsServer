package com.taxi.admin.service.dao;

import java.sql.SQLException;

import com.taxi.admin.common.Page;
import com.taxi.admin.service.module.TTaxiserverTaxipayinfo;

/**
 * 获取和查看支付流水
 * 
 * @author talkliu
 * 
 */
public interface TaxiPayInfoDao {

	/**
	 * ' 获取支付分页
	 * 
	 * @param t
	 * @return
	 */
	public Page getTaxiPayList(TTaxiserverTaxipayinfo t, int pageNo,
			int pageSize) throws SQLException;

	/**
	 * 添加支付
	 * 
	 * @param t
	 */
	@Deprecated
	public void addTaxiPay(TTaxiserverTaxipayinfo t) throws SQLException;

}
