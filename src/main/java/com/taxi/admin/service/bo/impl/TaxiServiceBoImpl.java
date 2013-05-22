package com.taxi.admin.service.bo.impl;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.taxi.admin.common.Page;
import com.taxi.admin.service.bo.TaxiServiceBo;
import com.taxi.admin.service.dao.TaxiCityInfoDao;
import com.taxi.admin.service.dao.TaxiDriverInfoDao;
import com.taxi.admin.service.dao.TaxiPayInfoDao;
import com.taxi.admin.service.dao.TaxiServiceDao;
import com.taxi.admin.service.dao.TaxiUserDao;
import com.taxi.admin.service.module.TSysCity;
import com.taxi.admin.service.module.TSysDistrict;
import com.taxi.admin.service.module.TSysProvince;
import com.taxi.admin.service.module.TTaxiclientCitytaxiapply;
import com.taxi.admin.service.module.TTaxiclientDriverinfoapply;
import com.taxi.admin.service.module.TTaxiserverCitytaxi;
import com.taxi.admin.service.module.TTaxiserverDriverinfo;
import com.taxi.admin.service.module.TTaxiserverTaxipayinfo;
import com.taxi.admin.service.module.TTaxiserverTaxiservice;
import com.taxi.admin.service.module.TTaxiserverTaxiuser;

public class TaxiServiceBoImpl implements TaxiServiceBo {
	private TaxiCityInfoDao taxiCityInfoDao;
	private TaxiDriverInfoDao taxiDriverInfoDao;
	private TaxiPayInfoDao taxiPayInfoDao;
	private TaxiServiceDao taxiServiceDao;
	private TaxiUserDao taxiUserDao;

	/**
	 * 返回验证对象
	 */
	public HashMap<String, Object> getAlluserMap() throws SQLException {
		return taxiUserDao.getAlluserMap();
	}

	/**
	 * 获取城市信息
	 * 
	 * @param t
	 * @return
	 */
	public Page getTaxiCityList(TTaxiserverCitytaxi t, int pageNo, int pageSize)
			throws SQLException {
		return taxiCityInfoDao.getTaxiCityList(t, pageNo, pageSize);
	}

	/**
	 * 获取单个城市
	 * 
	 * @return
	 */
	public TTaxiserverCitytaxi getTaxiCityInfoObject(TTaxiserverCitytaxi t)
			throws SQLException {
		return taxiCityInfoDao.getTaxiCityInfoObject(t);
	}

	/**
	 * 添加城市信息
	 * 
	 * @param t
	 */
	public void addTaxiCityInfo(TTaxiserverCitytaxi t) throws SQLException {
		taxiCityInfoDao.addTaxiCityInfo(t);
	}

	/**
	 * 导入城市信息
	 * 
	 * @param file
	 */
	public void addsTaxiCityInfo(List<TTaxiserverCitytaxi> list)
			throws SQLException {
		taxiCityInfoDao.addsTaxiCityInfo(list);
	}

	/**
	 * 修改城市信息
	 * 
	 * @param t
	 */
	public void updateTaxiCityInfo(TTaxiserverCitytaxi t) throws SQLException {
		taxiCityInfoDao.updateTaxiCityInfo(t);
	}

	/**
	 * 删除城市信息
	 * 
	 * @param t
	 */
	public void deleteTaxiCityInfo(Long t) throws SQLException {
		taxiCityInfoDao.deleteTaxiCityInfo(t);
	}

	/**
	 * 获取司机
	 * 
	 * @param t
	 * @return
	 */
	public Page getDriverList(TTaxiserverDriverinfo t, int pageNo, int pageSize)
			throws SQLException {
		return taxiDriverInfoDao.getDriverList(t, pageNo, pageSize);
	}

	/**
	 * 获取单个对象
	 * 
	 * @return
	 */
	public TTaxiserverDriverinfo getDriverInfoObject(TTaxiserverDriverinfo t)
			throws SQLException {
		return taxiDriverInfoDao.getDriverInfoObject(t);
	}

	/**
	 * 导入一个出租车司机
	 * 
	 * @param t
	 */
	public void isAddDriver(TTaxiserverDriverinfo t) throws SQLException {
		taxiDriverInfoDao.isAddDriver(t);
	}

	/**
	 * 批量插入出租车司机
	 * 
	 * @param file
	 */
	public void isAddsDriver(File file) throws SQLException {
		taxiDriverInfoDao.isAddsDriver(file);
	}

	/**
	 * 修改司机
	 * 
	 * @param t
	 */
	public void isUpdateDriver(TTaxiserverDriverinfo t) throws SQLException {
		taxiDriverInfoDao.isUpdateDriver(t);
	}

	/**
	 * 删除司机
	 * 
	 * @param t
	 */
	public void isDeleteDriver(Long t) throws SQLException {
		taxiDriverInfoDao.isDeleteDriver(t);
	}

	/**
	 * ' 获取支付分页
	 * 
	 * @param t
	 * @return
	 */
	public Page getTaxiPayList(TTaxiserverTaxipayinfo t, int pageNo,
			int pageSize) throws SQLException {
		return taxiPayInfoDao.getTaxiPayList(t, pageNo, pageSize);
	}

	/**
	 * 获取出租车服务流水
	 * 
	 * @return
	 */
	public Page getTaxiServiceList(TTaxiserverTaxiservice t, int pageNo,
			int pageSize) throws SQLException {
		return taxiServiceDao.getTaxiServiceList(t, pageNo, pageSize);
	}

	/**
	 * 获取用户信息（带查询）
	 * 
	 * @param t
	 * @return
	 */
	public Page getUserList(TTaxiserverTaxiuser t, int pageNo, int pageSize)
			throws SQLException {
		return taxiUserDao.getUserList(t, pageNo, pageSize);
	}

	/**
	 * 获取单个用户对象
	 * 
	 * @param t
	 * @return
	 */
	public TTaxiserverTaxiuser getUserObject(Long t) throws SQLException {
		return taxiUserDao.getUserObject(t);
	}

	/**
	 * 添加用户
	 * 
	 * @param t
	 */
	public Long isAddUser(TTaxiserverTaxiuser t) throws SQLException {
		return taxiUserDao.isAddUser(t);
	}

	/**
	 * 修改用户
	 * 
	 * @param t
	 */
	public void isUpdateUser(TTaxiserverTaxiuser t) throws SQLException {
		taxiUserDao.isUpdateUser(t);
	}

	/**
	 * 禁止用户登录
	 * 
	 * @param t
	 */
	public void isOutUser(TTaxiserverTaxiuser t) throws SQLException {
		taxiUserDao.isOutUser(t);
	}

	/**
	 * 批量添加用户
	 * 
	 * @param li
	 */
	public List<Long> isAddsUser(List<TTaxiserverTaxiuser> li)
			throws SQLException {
		return taxiUserDao.isAddsUser(li);
	}

	/**
	 * 验证用户唯一性
	 * 
	 * @param t
	 * @return
	 * @throws SQLException
	 */
	public int isNewUser(TTaxiserverTaxiuser t) {
		try {
			return taxiUserDao.isNewUser(t);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 获取省
	 * 
	 * @return
	 */
	public List<TSysProvince> getProvinceList() throws SQLException {
		return taxiCityInfoDao.getProvinceList();
	}

	/**
	 * 获取市
	 * 
	 * @param pid
	 * @return
	 */
	public List<TSysCity> getCity(Integer pid) throws SQLException {
		return taxiCityInfoDao.getCity(pid);
	}

	/**
	 * 获取县
	 * 
	 * @param pid
	 * @return
	 * @throws SQLException
	 */
	public List<TSysDistrict> getDistrict(Integer pid) throws SQLException {
		return taxiCityInfoDao.getDistrict(pid);
	}

	/**
	 * 验证唯一性
	 */
	public int isNewTaxiCity(TTaxiserverCitytaxi t) throws SQLException {
		return taxiCityInfoDao.isNewTaxiCity(t);
	}

	/**
	 * 获取申请列表
	 */
	public Page getCityApplyList(TTaxiclientCitytaxiapply t, int pageNo,
			int pageSize) throws SQLException {
		return taxiCityInfoDao.getCityApplyList(t, pageNo, pageSize);
	}

	/**
	 * 获取详细信息
	 */
	public TTaxiclientCitytaxiapply getCityApplyObj(Long applyId)
			throws SQLException {
		return taxiCityInfoDao.getCityApplyObj(applyId);
	}

	/**
	 * 修改信息
	 */
	public void updateCityApply(TTaxiclientCitytaxiapply t) throws SQLException {
		taxiCityInfoDao.updateCityApply(t);
	}

	/**
	 * 返回需要修改的对象
	 */
	public TTaxiserverCitytaxi getApplyToCityObj(TTaxiserverCitytaxi t)
			throws SQLException {
		return taxiCityInfoDao.getApplyToCityObj(t);
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
		return taxiDriverInfoDao.getApplyDriver(t, pageNo, pageSize);
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
		return taxiDriverInfoDao.getApplyDriverObj(t);
	}

	/**
	 * 修改申请
	 * 
	 * @param t
	 * @throws SQLException
	 */
	public void updateApplyDriver(TTaxiclientDriverinfoapply t)
			throws SQLException {
		taxiDriverInfoDao.updateApplyDriver(t);
	}

	public TaxiCityInfoDao getTaxiCityInfoDao() {
		return taxiCityInfoDao;
	}

	public void setTaxiCityInfoDao(TaxiCityInfoDao taxiCityInfoDao) {
		this.taxiCityInfoDao = taxiCityInfoDao;
	}

	public TaxiDriverInfoDao getTaxiDriverInfoDao() {
		return taxiDriverInfoDao;
	}

	public void setTaxiDriverInfoDao(TaxiDriverInfoDao taxiDriverInfoDao) {
		this.taxiDriverInfoDao = taxiDriverInfoDao;
	}

	public TaxiPayInfoDao getTaxiPayInfoDao() {
		return taxiPayInfoDao;
	}

	public void setTaxiPayInfoDao(TaxiPayInfoDao taxiPayInfoDao) {
		this.taxiPayInfoDao = taxiPayInfoDao;
	}

	public TaxiServiceDao getTaxiServiceDao() {
		return taxiServiceDao;
	}

	public void setTaxiServiceDao(TaxiServiceDao taxiServiceDao) {
		this.taxiServiceDao = taxiServiceDao;
	}

	public TaxiUserDao getTaxiUserDao() {
		return taxiUserDao;
	}

	public void setTaxiUserDao(TaxiUserDao taxiUserDao) {
		this.taxiUserDao = taxiUserDao;
	}

}
