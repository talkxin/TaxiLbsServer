package com.taxi.admin.service.dao.impl;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.cplatform.privacy.encrypt.MD5Algorithm;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.engine.mapping.sql.Sql;
import com.taxi.admin.common.Page;
import com.taxi.admin.service.dao.TaxiDriverInfoDao;
import com.taxi.admin.service.dao.TaxiUserDao;
import com.taxi.admin.service.module.TTaxiclientDriverinfoapply;
import com.taxi.admin.service.module.TTaxiserverCitytaxi;
import com.taxi.admin.service.module.TTaxiserverDriverinfo;
import com.taxi.admin.service.module.TTaxiserverTaxiuser;
import com.taxi.admin.system.UploadExcelToInsert;

public class TaxiDriverInfoDaoImpl implements TaxiDriverInfoDao {
	private SqlMapClient sqlMapClient;
	private TaxiUserDao taxiUserDao;

	/**
	 * 获取司机
	 * 
	 * @param t
	 * @return
	 */
	public Page getDriverList(TTaxiserverDriverinfo t, int pageNo, int pageSize)
			throws SQLException {
		List<TTaxiserverDriverinfo> list = sqlMapClient.queryForList(
				"getDriverList", t, (pageNo - 1) * pageSize, pageSize);
		Page page = new Page();
		page.search((Long) sqlMapClient.queryForObject("CountDriverList", t));
		page.turnToPage(pageNo);
		page.setList(list);
		return page;
	}

	/**
	 * 获取单个对象
	 * 
	 * @return
	 */
	public TTaxiserverDriverinfo getDriverInfoObject(TTaxiserverDriverinfo t)
			throws SQLException {

		return (TTaxiserverDriverinfo) sqlMapClient.queryForObject(
				"getDriverInfoObject", t.getId());
	}

	/**
	 * 导入一个出租车司机
	 * 
	 * @param t
	 */
	public void isAddDriver(TTaxiserverDriverinfo t) throws SQLException {
		// TTaxiserverTaxiuser tuser = new
		// TTaxiserverTaxiuser(t.getPhoneNumber(),
		// MD5Algorithm.digest2Str(t.getPhoneNumber() + t.getCompanyNo()),
		// "", 0, 0);
		// Long uid = taxiUserDao.isAddUser(tuser);
		// t.setUid(Integer.parseInt(uid.toString()));
		// sqlMapClient.insert("isAddDriver", t);
	}

	/**
	 * 批量插入出租车司机
	 * 
	 * @param file
	 */
	public void isAddsDriver(File file) throws SQLException {
		UploadExcelToInsert<TTaxiserverDriverinfo> f = new UploadExcelToInsert<TTaxiserverDriverinfo>();
		HashMap<String, String> key = new HashMap<String, String>();

		List<TTaxiserverDriverinfo> list = f.inserts(file,
				TTaxiserverCitytaxi.class, key);
		for (TTaxiserverDriverinfo t : list) {
			// TTaxiserverTaxiuser tuser = new TTaxiserverTaxiuser(
			// t.getPhoneNumber(), MD5Algorithm.digest2Str(t
			// .getPhoneNumber() + t.getCompanyNo()), "", 0, 0);
			// Long uid = taxiUserDao.isAddUser(tuser);
			// t.setUid(Integer.parseInt(uid.toString()));
			// sqlMapClient.insert("isAddDriver", t);
		}
	}

	/**
	 * 修改司机
	 * 
	 * @param t
	 */
	public void isUpdateDriver(TTaxiserverDriverinfo t) throws SQLException {
		sqlMapClient.update("isUpdateDriver", t);
	}

	/**
	 * 删除司机
	 * 
	 * @param t
	 */
	public void isDeleteDriver(Long t) throws SQLException {
		sqlMapClient.delete("isDeleteDriver", t);
	}

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
			int pageSize) throws SQLException {
		List<TTaxiclientDriverinfoapply> list = sqlMapClient.queryForList(
				"getApplyDriverList", t, (pageNo - 1) * pageSize, pageSize);
		Page page = new Page();
		page.search((Long) sqlMapClient.queryForObject("CountApplyDriverList",
				t));
		page.turnToPage(pageNo);
		page.setList(list);
		return page;
	}

	/**
	 * 获取申请对象
	 * 
	 * @param t
	 * @return
	 * @throws SQLException
	 */
	public TTaxiclientDriverinfoapply getApplyDriverObj(
			TTaxiclientDriverinfoapply t) throws SQLException {
		return (TTaxiclientDriverinfoapply) sqlMapClient.queryForObject(
				"getApplyDriverObj", t.getId());
	}

	/**
	 * 修改申请
	 * 
	 * @param t
	 * @throws SQLException
	 */
	public void updateApplyDriver(TTaxiclientDriverinfoapply t)
			throws SQLException {
		sqlMapClient.update("updateApplyDriver", t);
	}

	public SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public TaxiUserDao getTaxiUserDao() {
		return taxiUserDao;
	}

	public void setTaxiUserDao(TaxiUserDao taxiUserDao) {
		this.taxiUserDao = taxiUserDao;
	}

}
