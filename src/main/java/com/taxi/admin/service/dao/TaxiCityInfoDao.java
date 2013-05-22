package com.taxi.admin.service.dao;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import com.taxi.admin.common.Page;
import com.taxi.admin.service.module.TSysCity;
import com.taxi.admin.service.module.TSysDistrict;
import com.taxi.admin.service.module.TSysProvince;
import com.taxi.admin.service.module.TTaxiclientCitytaxiapply;
import com.taxi.admin.service.module.TTaxiserverCitytaxi;

/**
 * 城市起步价
 * 
 * @author talkliu
 * 
 */
public interface TaxiCityInfoDao {
	/**
	 * 获取城市信息
	 * 
	 * @param t
	 * @return
	 */
	public Page getTaxiCityList(TTaxiserverCitytaxi t, int pageNo, int pageSize)
			throws SQLException;

	/**
	 * 获取单个城市
	 * 
	 * @return
	 */
	public TTaxiserverCitytaxi getTaxiCityInfoObject(TTaxiserverCitytaxi t)
			throws SQLException;

	/**
	 * 添加城市信息
	 * 
	 * @param t
	 */
	public void addTaxiCityInfo(TTaxiserverCitytaxi t) throws SQLException;

	/**
	 * 导入城市信息
	 * 
	 * @param file
	 */
	public void addsTaxiCityInfo(List<TTaxiserverCitytaxi> list)
			throws SQLException;

	/**
	 * 修改城市信息
	 * 
	 * @param t
	 */
	public void updateTaxiCityInfo(TTaxiserverCitytaxi t) throws SQLException;

	/**
	 * 删除城市信息
	 * 
	 * @param t
	 */
	public void deleteTaxiCityInfo(Long t) throws SQLException;

	/**
	 * 获取省
	 * 
	 * @return
	 */
	public List<TSysProvince> getProvinceList() throws SQLException;

	/**
	 * 获取市
	 * 
	 * @param pid
	 * @return
	 */
	public List<TSysCity> getCity(Integer pid) throws SQLException;

	/**
	 * 获取县
	 * 
	 * @param pid
	 * @return
	 * @throws SQLException
	 */
	public List<TSysDistrict> getDistrict(Integer pid) throws SQLException;

	/**
	 * 验证唯一性
	 */
	public int isNewTaxiCity(TTaxiserverCitytaxi t) throws SQLException;
	/**
	 * 返回需要修改的对象
	 */
	public TTaxiserverCitytaxi getApplyToCityObj(TTaxiserverCitytaxi t) throws SQLException;

	/**
	 * 获取申请列表
	 */
	public Page getCityApplyList(TTaxiclientCitytaxiapply t, int pageNo, int pageSize)
			throws SQLException;

	/**
	 * 获取详细信息
	 */
	public TTaxiclientCitytaxiapply getCityApplyObj(Long applyId)
			throws SQLException;

	/**
	 * 修改信息
	 */
	public void updateCityApply(TTaxiclientCitytaxiapply t) throws SQLException;
}
