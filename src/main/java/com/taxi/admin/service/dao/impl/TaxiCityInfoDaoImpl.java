package com.taxi.admin.service.dao.impl;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.taxi.admin.common.Page;
import com.taxi.admin.service.dao.TaxiCityInfoDao;
import com.taxi.admin.service.module.TSysCity;
import com.taxi.admin.service.module.TSysDistrict;
import com.taxi.admin.service.module.TSysProvince;
import com.taxi.admin.service.module.TTaxiclientCitytaxiapply;
import com.taxi.admin.service.module.TTaxiserverCitytaxi;
import com.taxi.admin.system.UploadExcelToInsert;

public class TaxiCityInfoDaoImpl implements TaxiCityInfoDao {
	private SqlMapClient sqlMapClient;

	/**
	 * 获取城市信息
	 * 
	 * @param t
	 * @return
	 */
	public Page getTaxiCityList(TTaxiserverCitytaxi t, int pageNo, int pageSize)
			throws SQLException {
		List<TTaxiserverCitytaxi> list = sqlMapClient.queryForList(
				"getTaxiCityList", t, (pageNo - 1) * pageSize, pageSize);
		Page page = new Page();
		page.search((Long) sqlMapClient.queryForObject("CountTaxiCityList", t));
		page.turnToPage(pageNo);
		page.setList(list);
		return page;
	}

	/**
	 * 获取单个城市
	 * 
	 * @return
	 */
	public TTaxiserverCitytaxi getTaxiCityInfoObject(TTaxiserverCitytaxi t)
			throws SQLException {
		return (TTaxiserverCitytaxi) sqlMapClient.queryForObject(
				"getTaxiCityInfoObject", t.getCid());
	}

	/**
	 * 添加城市信息
	 * 
	 * @param t
	 */
	public void addTaxiCityInfo(TTaxiserverCitytaxi t) throws SQLException {
		sqlMapClient.insert("addTaxiCityInfo", t);
	}

	/**
	 * 导入城市信息
	 * 
	 * @param file
	 */
	public void addsTaxiCityInfo(List<TTaxiserverCitytaxi> list)
			throws SQLException {
		for (TTaxiserverCitytaxi t : list) {
			List li = sqlMapClient.queryForList("getTaxiCityList", t);
			if (li == null || li.size() == 0)
				sqlMapClient.insert("addTaxiCityInfo", t);
		}
	}

	/**
	 * 修改城市信息
	 * 
	 * @param t
	 */
	public void updateTaxiCityInfo(TTaxiserverCitytaxi t) throws SQLException {
		sqlMapClient.update("updateTaxiCityInfo", t);
	}

	/**
	 * 删除城市信息
	 * 
	 * @param t
	 */
	public void deleteTaxiCityInfo(Long t) throws SQLException {
		sqlMapClient.delete("deleteTaxiCityInfo", t);
	}

	/**
	 * 获取省
	 * 
	 * @return
	 */
	public List<TSysProvince> getProvinceList() throws SQLException {
		return sqlMapClient.queryForList("getProvinceList");
	}

	/**
	 * 获取市
	 * 
	 * @param pid
	 * @return
	 */
	public List<TSysCity> getCity(Integer pid) throws SQLException {
		return sqlMapClient.queryForList("getCity", pid);
	}

	/**
	 * 获取县
	 * 
	 * @param pid
	 * @return
	 * @throws SQLException
	 */
	public List<TSysDistrict> getDistrict(Integer pid) throws SQLException {
		return sqlMapClient.queryForList("getDistrict", pid);
	}

	/**
	 * 验证唯一性
	 */
	public int isNewTaxiCity(TTaxiserverCitytaxi t) throws SQLException {
		if (sqlMapClient.queryForObject("isNewTaxiCity", t) != null) {
			return 1;
		}
		return 0;
	}

	/**
	 * 返回需要修改的对象
	 */
	public TTaxiserverCitytaxi getApplyToCityObj(TTaxiserverCitytaxi t)
			throws SQLException {
		return (TTaxiserverCitytaxi) sqlMapClient.queryForObject(
				"isNewTaxiCity", t);
	}

	/**
	 * 获取申请列表
	 */
	public Page getCityApplyList(TTaxiclientCitytaxiapply t, int pageNo,
			int pageSize) throws SQLException {
		List<TTaxiclientCitytaxiapply> list = sqlMapClient.queryForList(
				"getCityApplyList", t, (pageNo - 1) * pageSize, pageSize);
		Page page = new Page();
		page.search((Long) sqlMapClient.queryForObject("CountCityApplyList", t));
		page.turnToPage(pageNo);
		page.setList(list);
		return page;
	}

	/**
	 * 获取详细信息
	 */
	public TTaxiclientCitytaxiapply getCityApplyObj(Long applyId)
			throws SQLException {
		return (TTaxiclientCitytaxiapply) sqlMapClient.queryForObject(
				"getCityApplyObj", applyId);
	}

	/**
	 * 修改信息
	 */
	public void updateCityApply(TTaxiclientCitytaxiapply t) throws SQLException {
		sqlMapClient.update("updateCityApply", t);
	}

	public SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

}
