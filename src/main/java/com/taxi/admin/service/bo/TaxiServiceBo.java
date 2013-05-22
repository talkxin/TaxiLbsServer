package com.taxi.admin.service.bo;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.taxi.admin.common.Page;
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

/**
 * 出租车服务端
 * 
 * @author talkliu
 * 
 */
public interface TaxiServiceBo {

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
	 * 获取司机
	 * 
	 * @param t
	 * @return
	 */
	public Page getDriverList(TTaxiserverDriverinfo t, int pageNo, int pageSize)
			throws SQLException;

	/**
	 * 获取单个对象
	 * 
	 * @return
	 */
	public TTaxiserverDriverinfo getDriverInfoObject(TTaxiserverDriverinfo t)
			throws SQLException;

	/**
	 * 导入一个出租车司机
	 * 
	 * @param t
	 */
	public void isAddDriver(TTaxiserverDriverinfo t) throws SQLException;

	/**
	 * 批量插入出租车司机
	 * 
	 * @param file
	 */
	public void isAddsDriver(File file) throws SQLException;

	/**
	 * 修改司机
	 * 
	 * @param t
	 */
	public void isUpdateDriver(TTaxiserverDriverinfo t) throws SQLException;

	/**
	 * 删除司机
	 * 
	 * @param t
	 */
	public void isDeleteDriver(Long t) throws SQLException;

	/**
	 * ' 获取支付分页
	 * 
	 * @param t
	 * @return
	 */
	public Page getTaxiPayList(TTaxiserverTaxipayinfo t, int pageNo,
			int pageSize) throws SQLException;

	/**
	 * 获取出租车服务流水
	 * 
	 * @return
	 */
	public Page getTaxiServiceList(TTaxiserverTaxiservice t, int pageNo,
			int pageSize) throws SQLException;

	/**
	 * 获取用户信息（带查询）
	 * 
	 * @param t
	 * @return
	 */
	public Page getUserList(TTaxiserverTaxiuser t, int pageNo, int pageSize)
			throws SQLException;

	/**
	 * 获取单个用户对象
	 * 
	 * @param t
	 * @return
	 */
	public TTaxiserverTaxiuser getUserObject(Long t) throws SQLException;

	/**
	 * 添加用户
	 * 
	 * @param t
	 */
	public Long isAddUser(TTaxiserverTaxiuser t) throws SQLException;

	/**
	 * 修改用户
	 * 
	 * @param t
	 */
	public void isUpdateUser(TTaxiserverTaxiuser t) throws SQLException;

	/**
	 * 禁止用户登录
	 * 
	 * @param t
	 */
	public void isOutUser(TTaxiserverTaxiuser t) throws SQLException;

	/**
	 * 批量添加用户
	 * 
	 * @param li
	 */
	public List<Long> isAddsUser(List<TTaxiserverTaxiuser> li)
			throws SQLException;

	/**
	 * 验证用户唯一性
	 * 
	 * @param t
	 * @return
	 * @throws SQLException
	 */
	public int isNewUser(TTaxiserverTaxiuser t);

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
	 * 获取申请列表
	 */
	public Page getCityApplyList(TTaxiclientCitytaxiapply t, int pageNo,
			int pageSize) throws SQLException;

	/**
	 * 获取详细信息
	 */
	public TTaxiclientCitytaxiapply getCityApplyObj(Long applyId)
			throws SQLException;

	/**
	 * 修改信息
	 */
	public void updateCityApply(TTaxiclientCitytaxiapply t) throws SQLException;

	/**
	 * 返回需要修改的对象
	 */
	public TTaxiserverCitytaxi getApplyToCityObj(TTaxiserverCitytaxi t)
			throws SQLException;

	/**
	 * 返回验证对象
	 */
	public HashMap<String, Object> getAlluserMap() throws SQLException;

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
			int pageSize) throws SQLException;

	/**
	 * 获取申请对象
	 * 
	 * @param t
	 * @return
	 * @throws SQLException
	 */
	public TTaxiclientDriverinfoapply getApplyDriverObj(
			TTaxiclientDriverinfoapply t) throws SQLException;

	/**
	 * 修改申请
	 * 
	 * @param t
	 * @throws SQLException
	 */
	public void updateApplyDriver(TTaxiclientDriverinfoapply t)
			throws SQLException;
}
