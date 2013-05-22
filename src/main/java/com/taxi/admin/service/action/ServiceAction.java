package com.taxi.admin.service.action;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.cplatform.privacy.encrypt.MD5Algorithm;
import com.ibatis.common.logging.Log;
import com.taxi.admin.base.BaseAction;
import com.taxi.admin.common.Page;
import com.taxi.admin.service.bo.TaxiServiceBo;
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
import com.taxi.admin.system.UploadExcelToInsert;

/**
 * service对应Action
 * 
 * @author talkliu
 * 
 */
public class ServiceAction extends BaseAction {
	/**
	 * 分页
	 */
	private Page pageWapSite;

	/**
	 * 当前页
	 */
	private int pageNo = 1;

	/**
	 * 页面条数
	 */
	private int pageSize = 15;

	/**
	 * 操作Bo
	 */
	private TaxiServiceBo taxiServiceBo;
	/**
	 * 城市对象
	 */
	private TTaxiserverCitytaxi cityTaxi;
	/**
	 * 城市List
	 */
	private List<TTaxiserverCitytaxi> cityList;
	/**
	 * 司机信息对象
	 */
	private TTaxiserverDriverinfo driverInfo;
	/**
	 * 司机信息List
	 */
	private List<TTaxiserverDriverinfo> driverList;

	/**
	 * 支付流水List
	 */
	private List<TTaxiserverTaxipayinfo> payInfoList;
	/**
	 * 支付流水查询对象
	 */
	private TTaxiserverTaxipayinfo payInfoObject;
	/**
	 * 服务流水
	 */
	private List<TTaxiserverTaxiservice> serviceList;
	/**
	 * 服务流水查询对象
	 */
	private TTaxiserverTaxiservice serviceObject;
	/**
	 * 用户对象
	 */
	private TTaxiserverTaxiuser taxiUser;
	/**
	 * 用户列表
	 */
	private List<TTaxiserverTaxiuser> taxiUserList;
	/**
	 * 上传文件域对象
	 */
	private List<File> upload;
	/**
	 * 返回错误信息
	 */
	private String masager;
	/**
	 * 查询条件组
	 */
	private String[] seach = new String[10];

	/**
	 * 支付流水
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String inPayinfo() {
		try {
			// 检查权限
			returnRole();
			pageWapSite = taxiServiceBo.getTaxiPayList(payInfoObject, pageNo,
					pageSize);
			pageWapSite.addParam("menuId", this.getMenuId());
			if (null != pageWapSite) {
				if (null != payInfoObject) {
					pageWapSite.addParam("payInfoObject.PayType",
							String.valueOf(payInfoObject.getPayType()));
					pageWapSite.addParam("payInfoObject.PayType2",
							String.valueOf(payInfoObject.getPayType2()));
					pageWapSite.addParam("payInfoObject.driverCompany",
							payInfoObject.getDriverCompany());
					pageWapSite.addParam("payInfoObject.plateNumber",
							payInfoObject.getPlateNumber());
					pageWapSite.addParam("menuId", getMenuId());
				}
			}
			payInfoList = (List<TTaxiserverTaxipayinfo>) pageWapSite.getList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
		return SUCCESS;
	}

	/**
	 * 业务流水
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String inServiceInfo() {
		try {
			// 检查权限
			returnRole();
			pageWapSite = taxiServiceBo.getTaxiServiceList(serviceObject,
					pageNo, pageSize);
			pageWapSite.addParam("menuId", this.getMenuId());
			if (null != pageWapSite) {
				if (null != serviceObject) {
					// 添加查找
					pageWapSite.addParam("serviceObject.driverCompany",
							serviceObject.getDriverCompany());
					pageWapSite.addParam("serviceObject.plateNumber",
							serviceObject.getPlateNumber());
					pageWapSite.addParam("menuId", getMenuId());
				}
			}
			serviceList = (List<TTaxiserverTaxiservice>) pageWapSite.getList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
		return SUCCESS;
	}

	/**
	 * 进入用户管理列表
	 * 
	 * @return
	 */
	public String inUserInfo() {
		try {
			// 检查权限
			returnRole();
			taxiUser = new TTaxiserverTaxiuser();
			if (seach[1] != null && !seach[1].equals("")
					&& !seach[1].equals("null"))
				taxiUser.setPhoneNumber(seach[1]);
			if (seach[0] != null && !seach[0].equals("")
					&& !seach[0].equals("null"))
				taxiUser.setLoginName(seach[0]);
			pageWapSite = taxiServiceBo.getUserList(taxiUser, pageNo, pageSize);
			if (null != pageWapSite) {
				// if (null != taxiUser) {
				// 查询条件
				pageWapSite.addParam("seach[0]", seach[0]);
				pageWapSite.addParam("seach[1]", seach[1]);
				pageWapSite.addParam("menuId", getMenuId());
				// }
			}
			taxiUserList = (List<TTaxiserverTaxiuser>) pageWapSite.getList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 添加用户
	 * 
	 * @return
	 */
	public String addUserInfo() {
		return SUCCESS;
	}

	/**
	 * 批量添加用户
	 * 
	 * @return
	 */
	public String addsUserInfo() {
		return SUCCESS;
	}

	/**
	 * 修改用户
	 * 
	 * @return
	 */
	public String updateUserInfo() {
		try {
			taxiUser = taxiServiceBo.getUserObject(taxiUser.getUid());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 保存添加用户
	 * 
	 * @return
	 */
	public String saveAddUser() {
		try {
			taxiUser.setPassword(MD5Algorithm.digest2Str(taxiUser.getPassword()));
			taxiServiceBo.isAddUser(taxiUser);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 新用户验证
	 */
	private int newUser;

	public int getNewUser() {
		return newUser;
	}

	public void setNewUser(int newUser) {
		this.newUser = newUser;
	}

	/**
	 * 检验是否是唯一用户
	 * 
	 * @return
	 */
	public String isNewUser() {
		newUser = this.taxiServiceBo.isNewUser(taxiUser);

		return SUCCESS;
	}

	/**
	 * 批量添加用户
	 * 
	 * @return
	 */
	public String saveAddsUser() {
		// try {
		// HashMap<String, Object> map = taxiServiceBo.getAlluserMap();
		// // 缓冲文件名
		// List<String> loginAll = (List<String>) map.get("loginName");
		// // 缓冲用户名
		// List<Long> phoneAll = (List<Long>) map.get("phone");
		HashMap<String, String> key = new HashMap<String, String>();
		key.put("登录名", "loginName");
		key.put("密码", "password");
		key.put("邮箱", "email");
		key.put("手机号", "phoneNumber");
		key.put("用户类型（0为司机，1为普通用户）", "roleid");
		String emailTo = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
		List<TTaxiserverTaxiuser> list = new UploadExcelToInsert<TTaxiserverTaxiuser>()
				.inserts(upload.get(0), TTaxiserverTaxiuser.class, key);
		if (list != null && list.size() != 0) {
			for (int j = 0; j < list.size(); j++) {
				TTaxiserverTaxiuser i = list.get(j);
				int m = j + 1;
				if (i.getLoginName() == null || i.getPassword() == null
						|| i.getPhoneNumber() == null) {
					masager = "您上传的文件第" + m + "行有误";
					return "bading";
				} else if (i.getLoginName().length() > 11
						|| i.getPassword().length() > 15
						|| i.getPhoneNumber().length() > 13) {
					if (i.getEmail() != null && i.getEmail().matches(emailTo)) {
					} else {
						masager = "您上传的文件第" + m + "行邮箱有误";
						return "bading";
					}
					masager = "您上传的文件第" + m + "行有误（用户名或密码超长或手机号不正确）";
					return "bading";
				} else if (this.taxiServiceBo.isNewUser(i) == 1) {
					masager = "您上传的文件第" + m + "行已有该用户";
					return "bading";
				}
				i.setPassword(MD5Algorithm.digest2Str(i.getPassword()));
				i.setIsLogin(0);
			}
			try {
				taxiServiceBo.isAddsUser(list);
			} catch (SQLException e) {
				masager = "您上传的文件有误";
				return "bading";
			}
		} else {
			masager = "您上传的文件有误";
			return "bading";
		}
		masager = "成功上传" + list.size() + "条信息";
		return "bading";
		// } catch (SQLException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		// return "bading";
	}

	/**
	 * 保存修改用户
	 * 
	 * @return
	 */
	public String saveUpdateUser() {
		try {
			if (taxiUser.getPassword() != null
					&& !taxiUser.getPassword().equals("")) {
				taxiUser.setPassword(MD5Algorithm.digest2Str(taxiUser
						.getPassword()));
			}
			taxiServiceBo.isUpdateUser(taxiUser);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
		return SUCCESS;
	}

	/**
	 * 禁止登陆
	 * 
	 * @return
	 */
	public String delUser() {
		try {
			taxiUser = taxiServiceBo.getUserObject(taxiUser.getUid());
			if (taxiUser.getIsLogin() == 1)
				taxiUser.setIsLogin(0);
			else
				taxiUser.setIsLogin(1);
			taxiServiceBo.isUpdateUser(taxiUser);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 进入城市价格管理
	 * 
	 * @return
	 */
	public String inCityTaxi() {
		try {
			// 检查权限
			returnRole();
			cityTaxi = new TTaxiserverCitytaxi();
			if (seach[0] != null && !seach[0].equals("")
					&& !seach[0].equals("null")) {
				cityTaxi.setCityName(seach[0]);
			}
			if (seach[1] != null && !seach[1].equals("")
					&& !seach[1].equals("null")) {
				cityTaxi.setStruts(Integer.parseInt(seach[1]));
			}
			if (seach[2] != null && !seach[2].equals("")
					&& !seach[2].equals("null")) {
				cityTaxi.setCompanyName(seach[2]);
			}
			pageWapSite = taxiServiceBo.getTaxiCityList(cityTaxi, pageNo,
					pageSize);
			if (null != pageWapSite)
				if (null != cityTaxi) {
					// 查询条件
					pageWapSite.addParam("seach[0]", seach[0]);
					pageWapSite.addParam("seach[1]", seach[1]);
					pageWapSite.addParam("seach[2]", seach[2]);
					pageWapSite.addParam("menuId", getMenuId());
				}
			cityList = (List<TTaxiserverCitytaxi>) pageWapSite.getList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
		return SUCCESS;
	}

	/**
	 * 添加城市
	 * 
	 * @return
	 */
	public String addCity() {
		try {
			sysProvince = taxiServiceBo.getProvinceList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 区域审核
	 * 
	 * @return
	 */
	public String addsCity() {
		try {
			cityApply = new TTaxiclientCitytaxiapply();
			if (seach[0] != null && !seach[0].equals("")
					&& !seach[0].equals("null")) {
				cityTaxi.setCityName("");
			}
			if (seach[1] != null && !seach[1].equals("")
					&& !seach[1].equals("null")) {
				cityTaxi.setStruts(Integer.parseInt(seach[1]));
			}
			if (seach[2] != null && !seach[2].equals("")
					&& !seach[2].equals("null")) {
				cityTaxi.setCompanyName(seach[2]);
			}
			if (cityApply.getStruts() == null) {
				cityApply.setStruts(0);
			}
			pageWapSite = taxiServiceBo.getCityApplyList(cityApply, pageNo,
					pageSize);
			if (null != pageWapSite)
				if (null != cityTaxi) {
					// 查询条件
					pageWapSite.addParam("seach[0]", seach[0]);
					pageWapSite.addParam("seach[1]", seach[1]);
					pageWapSite.addParam("seach[2]", seach[2]);
					pageWapSite.addParam("menuId", getMenuId());
				}
			cityApplyList = (List<TTaxiclientCitytaxiapply>) pageWapSite
					.getList();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 修改审核状态
	 * 
	 * @return
	 */
	public String updateCityApply() {
		try {
			taxiServiceBo.updateCityApply(cityApply);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 获取确认对象
	 * 
	 * @return
	 */
	public String getCityApplyObj() {
		try {
			cityApply = taxiServiceBo.getCityApplyObj(cityApply.getApplyId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 返回的城市
	 */
	private String[] rcity;

	/**
	 * 修改城市
	 * 
	 * @return
	 */
	public String updateCity() {
		try {
			sysProvince = taxiServiceBo.getProvinceList();
			cityTaxi = taxiServiceBo.getTaxiCityInfoObject(cityTaxi);
			rcity = cityTaxi.getCityName().split(",");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 启用该城市
	 */
	public String statusCity() {
		try {
			cityTaxi = taxiServiceBo.getTaxiCityInfoObject(cityTaxi);
			if (cityTaxi.getStruts() == 0) {
				cityTaxi.setStruts(1);
			} else {
				cityTaxi.setStruts(0);
			}
			taxiServiceBo.updateTaxiCityInfo(cityTaxi);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 保存添加城市
	 * 
	 * @return
	 */
	public String saveAddCity() {
		try {
			taxiServiceBo.addTaxiCityInfo(cityTaxi);
			if (cityApply != null) {
				taxiServiceBo.updateCityApply(cityApply);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 保存批量添加城市
	 * 
	 * @return
	 */
	public String saveAddsCity() {
		HashMap<String, String> key = new HashMap<String, String>();
		List<TTaxiserverCitytaxi> list = new UploadExcelToInsert<TTaxiserverCitytaxi>()
				.inserts(upload.get(0), TTaxiserverCitytaxi.class, key);
		if (key != null) {
			try {
				taxiServiceBo.addsTaxiCityInfo(list);
			} catch (SQLException e) {
				masager = "您上传的文件有误";
				return "bading";
			}
		} else {
			masager = "您上传的文件有误";
			return "bading";
		}
		return "bading";
	}

	/**
	 * 保存修改城市
	 * 
	 * @return
	 */
	public String saveUpdateCity() {
		try {
			taxiServiceBo.updateTaxiCityInfo(cityTaxi);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 保存修改城市
	 * 
	 * @return
	 */
	public String saveUpdateCity2() {
		try {
			if (cityTaxi.getCompanyName().equals("null")) {
				cityTaxi.setCompanyName(null);
			}
			cityTaxi.setCid(taxiServiceBo.getApplyToCityObj(cityTaxi).getCid());
			taxiServiceBo.updateTaxiCityInfo(cityTaxi);
			if (cityApply != null) {
				taxiServiceBo.updateCityApply(cityApply);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 删除城市
	 * 
	 * @return
	 */
	public String delCity() {
		try {
			taxiServiceBo.deleteTaxiCityInfo(cityTaxi.getCid());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 省
	 */
	List<TSysProvince> sysProvince;
	/**
	 * 市
	 */
	List<TSysCity> sysCity;
	/**
	 * 区
	 */
	List<TSysDistrict> sysDistrict;

	/**
	 * 获取城市
	 * 
	 * @return
	 */
	public String getSysCityList() {
		try {
			if (ServletActionContext.getRequest().getParameter("pid") != null
					&& !ServletActionContext.getRequest().getParameter("pid")
							.equals("")) {
				sysCity = taxiServiceBo.getCity(Integer
						.parseInt(ServletActionContext.getRequest()
								.getParameter("pid")));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 获取县区
	 * 
	 * @return
	 */
	public String getSysDistrictList() {
		try {
			if (ServletActionContext.getRequest().getParameter("pid") != null
					&& !ServletActionContext.getRequest().getParameter("pid")
							.equals("")) {
				sysDistrict = taxiServiceBo.getDistrict(Integer
						.parseInt(ServletActionContext.getRequest()
								.getParameter("pid")));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 验证该城市是否有添加
	 */
	private int newCity;

	/**
	 * 验证新城市
	 */
	public String isNewCity() {
		try {
			newCity = taxiServiceBo.isNewTaxiCity(cityTaxi);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 申请列表
	 */
	private List<TTaxiclientCitytaxiapply> cityApplyList;
	/**
	 * 申请对象
	 */
	private TTaxiclientCitytaxiapply cityApply;

	/**
	 * 进入司机管理
	 * 
	 * @return
	 */
	public String inDriver() {
		try {
			// 查询条件
			driverInfo = new TTaxiserverDriverinfo();
			if (seach[0] != null && !seach[0].equals("")
					&& !seach[0].equals("null")) {
				driverInfo.setDriverName(seach[0]);
			}
			if (seach[1] != null && !seach[1].equals("")
					&& !seach[1].equals("null")) {
				driverInfo.setDriverCompany(seach[1]);
			}
			if (seach[2] != null && !seach[2].equals("")
					&& !seach[2].equals("null")) {
				driverInfo.setPlateNumber(seach[2]);
			}
			// 检查权限
			returnRole();
			pageWapSite = taxiServiceBo.getDriverList(driverInfo, pageNo,
					pageSize);
			if (null != pageWapSite)
				if (null != driverInfo) {
					// 搜索条件
					pageWapSite.addParam("menuId", getMenuId());
					pageWapSite.addParam("seach[0]", seach[0]);
					pageWapSite.addParam("seach[1]", seach[1]);
					pageWapSite.addParam("seach[2]", seach[2]);
				}
			driverList = (List<TTaxiserverDriverinfo>) pageWapSite.getList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 司机申请列表
	 */
	private List<TTaxiclientDriverinfoapply> driverApplyList;
	/**
	 * 司机申请对象
	 */
	private TTaxiclientDriverinfoapply driverApply;

	/**
	 * 进入司机身份申请
	 * 
	 * @return
	 */
	public String inDriverApply() {
		try {
			// 查询条件
			driverApply = new TTaxiclientDriverinfoapply();
			if (seach[0] != null && !seach[0].equals("")
					&& !seach[0].equals("null")) {
				driverApply.setDriverName(seach[0]);
			}
			if (seach[1] != null && !seach[1].equals("")
					&& !seach[1].equals("null")) {
				driverApply.setDriverCompany(seach[1]);
			}
			if (seach[2] != null && !seach[2].equals("")
					&& !seach[2].equals("null")) {
				driverApply.setPlateNumber(seach[2]);
			}
			// 检查权限
			returnRole();
			pageWapSite = taxiServiceBo.getApplyDriver(driverApply, pageNo,
					pageSize);
			if (null != pageWapSite)
				if (null != driverInfo) {
					// 搜索条件
					pageWapSite.addParam("menuId", getMenuId());
					pageWapSite.addParam("seach[0]", seach[0]);
					pageWapSite.addParam("seach[1]", seach[1]);
					pageWapSite.addParam("seach[2]", seach[2]);
				}
			driverApplyList = (List<TTaxiclientDriverinfoapply>) pageWapSite
					.getList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 添加司机
	 * 
	 * @return
	 */
	public String addDriver() {
		return SUCCESS;
	}

	/**
	 * 批量添加司机
	 * 
	 * @return
	 */
	public String addsDriver() {
		return SUCCESS;
	}

	/**
	 * 修改司机
	 * 
	 * @return
	 */
	public String updateDriver() {
		try {
			driverInfo = taxiServiceBo.getDriverInfoObject(driverInfo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 保存添加
	 * 
	 * @return
	 */
	public String saveAddDriver() {
		try {
			taxiServiceBo.isAddDriver(driverInfo);
			// 创建一个司机用户，默认手机号登陆
			TTaxiserverTaxiuser driver = new TTaxiserverTaxiuser();
			driver.setUserName(driverInfo.getPhoneNumber());
			driver.setPassword(driverInfo.getPhoneNumber() + "888888");
			driver.setRoleid(1);
			driver.setIsLogin(0);
			driver.setEmail("");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 保存批量添加
	 * 
	 * @return
	 */
	public String saveAddsDriver() {
		HashMap<String, String> key = new HashMap<String, String>();
		List<TTaxiserverDriverinfo> list = new UploadExcelToInsert<TTaxiserverDriverinfo>()
				.inserts(upload.get(0), TTaxiserverDriverinfo.class, key);
		if (list != null && list.size() != 0) {
			List<TTaxiserverTaxiuser> user = new ArrayList<TTaxiserverTaxiuser>();
			for (TTaxiserverDriverinfo e : list) {
				// 创建一个司机用户，默认手机号登陆
				TTaxiserverTaxiuser driver = new TTaxiserverTaxiuser();
				driver.setUserName(e.getPhoneNumber());
				driver.setPassword(e.getPhoneNumber() + "888888");
				driver.setRoleid(1);
				driver.setIsLogin(0);
				driver.setEmail("");
				user.add(driver);
			}
		} else {
			masager = "您上传的文件有误";
			return "bading";
		}

		return SUCCESS;
	}

	/**
	 * 保存修改
	 * 
	 * @return
	 */
	public String saveUpdateDriver() {
		try {
			taxiServiceBo.isUpdateDriver(driverInfo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 删除司机
	 * 
	 * @return
	 */
	public String delDriver() {
		try {
			taxiServiceBo.isDeleteDriver(driverInfo.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 获取申请对象
	 * 
	 * @return
	 */
	public String getApplyDriverObj() {
		try {
			driverApply = taxiServiceBo.getApplyDriverObj(driverApply);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String newDriverTo() {
		try {
			taxiServiceBo.updateApplyDriver(driverApply);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public Page getPageWapSite() {
		return pageWapSite;
	}

	public void setPageWapSite(Page pageWapSite) {
		this.pageWapSite = pageWapSite;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public TTaxiserverCitytaxi getCityTaxi() {
		return cityTaxi;
	}

	public void setCityTaxi(TTaxiserverCitytaxi cityTaxi) {
		this.cityTaxi = cityTaxi;
	}

	public List<TTaxiserverCitytaxi> getCityList() {
		return cityList;
	}

	public List<TTaxiclientCitytaxiapply> getCityApplyList() {
		return cityApplyList;
	}

	public void setCityApplyList(List<TTaxiclientCitytaxiapply> cityApplyList) {
		this.cityApplyList = cityApplyList;
	}

	public TTaxiclientCitytaxiapply getCityApply() {
		return cityApply;
	}

	public void setCityApply(TTaxiclientCitytaxiapply cityApply) {
		this.cityApply = cityApply;
	}

	public void setCityList(List<TTaxiserverCitytaxi> cityList) {
		this.cityList = cityList;
	}

	public TTaxiserverDriverinfo getDriverInfo() {
		return driverInfo;
	}

	public void setDriverInfo(TTaxiserverDriverinfo driverInfo) {
		this.driverInfo = driverInfo;
	}

	public List<TTaxiserverDriverinfo> getDriverList() {
		return driverList;
	}

	public void setDriverList(List<TTaxiserverDriverinfo> driverList) {
		this.driverList = driverList;
	}

	public List<TTaxiserverTaxipayinfo> getPayInfoList() {
		return payInfoList;
	}

	public TaxiServiceBo getTaxiServiceBo() {
		return taxiServiceBo;
	}

	public void setTaxiServiceBo(TaxiServiceBo taxiServiceBo) {
		this.taxiServiceBo = taxiServiceBo;
	}

	public void setPayInfoList(List<TTaxiserverTaxipayinfo> payInfoList) {
		this.payInfoList = payInfoList;
	}

	public List<TTaxiserverTaxiservice> getServiceList() {
		return serviceList;
	}

	public void setServiceList(List<TTaxiserverTaxiservice> serviceList) {
		this.serviceList = serviceList;
	}

	public TTaxiserverTaxiuser getTaxiUser() {
		return taxiUser;
	}

	public void setTaxiUser(TTaxiserverTaxiuser taxiUser) {
		this.taxiUser = taxiUser;
	}

	public List<TTaxiserverTaxiuser> getTaxiUserList() {
		return taxiUserList;
	}

	public void setTaxiUserList(List<TTaxiserverTaxiuser> taxiUserList) {
		this.taxiUserList = taxiUserList;
	}

	public TTaxiserverTaxipayinfo getPayInfoObject() {
		return payInfoObject;
	}

	public void setPayInfoObject(TTaxiserverTaxipayinfo payInfoObject) {
		this.payInfoObject = payInfoObject;
	}

	public TTaxiserverTaxiservice getServiceObject() {
		return serviceObject;
	}

	public List<File> getUpload() {
		return upload;
	}

	public void setUpload(List<File> upload) {
		this.upload = upload;
	}

	public String getMasager() {
		return masager;
	}

	public void setMasager(String masager) {
		this.masager = masager;
	}

	public void setServiceObject(TTaxiserverTaxiservice serviceObject) {
		this.serviceObject = serviceObject;
	}

	public String[] getSeach() {
		return seach;
	}

	public String[] getRcity() {
		return rcity;
	}

	public void setRcity(String[] rcity) {
		this.rcity = rcity;
	}

	public void setSeach(String[] seach) {
		this.seach = seach;
	}

	public List<TSysProvince> getSysProvince() {
		return sysProvince;
	}

	public void setSysProvince(List<TSysProvince> sysProvince) {
		this.sysProvince = sysProvince;
	}

	public List<TSysCity> getSysCity() {
		return sysCity;
	}

	public void setSysCity(List<TSysCity> sysCity) {
		this.sysCity = sysCity;
	}

	public int getNewCity() {
		return newCity;
	}

	public void setNewCity(int newCity) {
		this.newCity = newCity;
	}

	public void setSysDistrict(List<TSysDistrict> sysDistrict) {
		this.sysDistrict = sysDistrict;
	}

	public List<TSysDistrict> getSysDistrict() {
		return sysDistrict;
	}

	public List<TTaxiclientDriverinfoapply> getDriverApplyList() {
		return driverApplyList;
	}

	public void setDriverApplyList(
			List<TTaxiclientDriverinfoapply> driverApplyList) {
		this.driverApplyList = driverApplyList;
	}

	public TTaxiclientDriverinfoapply getDriverApply() {
		return driverApply;
	}

	public void setDriverApply(TTaxiclientDriverinfoapply driverApply) {
		this.driverApply = driverApply;
	}

}
