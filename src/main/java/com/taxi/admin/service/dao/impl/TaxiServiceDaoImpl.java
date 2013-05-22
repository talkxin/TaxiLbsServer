package com.taxi.admin.service.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.taxi.admin.common.Page;
import com.taxi.admin.service.dao.TaxiServiceDao;
import com.taxi.admin.service.module.TTaxiserverTaxiservice;

public class TaxiServiceDaoImpl implements TaxiServiceDao {
	private SqlMapClient sqlMapClient;

	/**
	 * 获取出租车服务流水
	 * 
	 */
	public Page getTaxiServiceList(TTaxiserverTaxiservice t, int pageNo,
			int pageSize) throws SQLException {
		List<TTaxiserverTaxiservice> list = sqlMapClient.queryForList(
				"getTaxiServiceList", t, (pageNo - 1) * pageSize, pageSize);
		Page page = new Page();
		page.search((Long) sqlMapClient.queryForObject("CountTaxiServiceList",
				t));
		page.turnToPage(pageNo);
		page.setList(list);
		return page;
	}

	/**
	 * 添加服务（先不启用）
	 * 
	 * @param t
	 */
	@Deprecated
	public void addTaxiService(TTaxiserverTaxiservice t) throws SQLException {
		sqlMapClient.insert("addTaxiService", t);
	}

	public SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

}
