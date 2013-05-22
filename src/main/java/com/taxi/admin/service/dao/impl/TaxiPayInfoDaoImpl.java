package com.taxi.admin.service.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.taxi.admin.common.Page;
import com.taxi.admin.service.dao.TaxiPayInfoDao;
import com.taxi.admin.service.module.TTaxiserverTaxipayinfo;

public class TaxiPayInfoDaoImpl implements TaxiPayInfoDao {
	private SqlMapClient sqlMapClient;

	/**
	 * ' 获取支付分页
	 * 
	 * @param t
	 * @return
	 */
	public Page getTaxiPayList(TTaxiserverTaxipayinfo t, int pageNo,
			int pageSize) throws SQLException {
		List<TTaxiserverTaxipayinfo> list = sqlMapClient.queryForList(
				"getTaxiPayList", t, (pageNo - 1) * pageSize, pageSize);
		Page page = new Page();
		page.search((Long)sqlMapClient.queryForObject("CountTaxiPayList",t));
		page.turnToPage(pageNo);
		page.setList(list);
		return page;
	}

	/**
	 * 添加支付
	 * 
	 * @param t
	 */
	public void addTaxiPay(TTaxiserverTaxipayinfo t)throws SQLException {
		sqlMapClient.insert("addTaxiPay", t);
	}

	public SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

}
