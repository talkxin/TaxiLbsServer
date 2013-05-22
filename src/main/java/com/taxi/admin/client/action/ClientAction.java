package com.taxi.admin.client.action;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.taxi.admin.client.bo.ClientServiceBo;
import com.taxi.admin.client.bo.ClientUserBo;
import com.taxi.admin.service.module.TTaxiserverDriverinfo;
import com.taxi.admin.service.module.TTaxiserverTaxiservice;
import com.taxi.admin.service.module.TTaxiserverTaxiuser;

/**
 * 客户端的
 * 
 * @author talkliu
 * 
 */
public class ClientAction extends ActionSupport {

	private ClientUserBo clientUserBo;
	private ClientServiceBo clientServiceBo;
	private TTaxiserverTaxiuser userInfo;
	private TTaxiserverDriverinfo drivreInfo;
	private TTaxiserverTaxiservice serviceObj;
	private List<TTaxiserverTaxiservice> serviceList;
	private HashMap<String, Object> serviceMap;
	private boolean isTrue;
	private Long uid;
	private Long serviceID;
	private String lon;
	private String lat;
	private Integer updateCount;
	/**
	 * 当前页
	 */
	private int pageNo = 1;

	/**
	 * 页面条数
	 */
	private int pageSize = 15;

	/**
	 * 注册并登陆
	 * 
	 * @return
	 */
	public String regAndLogin() {
		try {
			userInfo = clientUserBo.regAndLogin(userInfo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 登陆
	 * 
	 * @return
	 */
	public String loginInClient() {
		try {
			userInfo = clientUserBo.loginIn(userInfo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 验证用户名
	 * 
	 * @return
	 */
	public String isNewUserForClient() {
		try {
			isTrue = clientUserBo.isNewUser(userInfo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 注册一个司机
	 * 
	 * @return
	 */
	public String regDriver() {
		try {
			clientUserBo.regDriver(drivreInfo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 获取司机信息
	 * 
	 * @return
	 */
	public String getDriverInfo() {
		try {
			drivreInfo = clientUserBo.getDriver(uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 修改司机
	 * 
	 * @return
	 */
	public String updateDriverInfo() {
		try {
			isTrue = clientUserBo.updateDriverInfo(drivreInfo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public Integer getUpdateCount() {
		return updateCount;
	}

	public void setUpdateCount(Integer updateCount) {
		this.updateCount = updateCount;
	}

	/**
	 * 生成一个订单
	 * 
	 * @return
	 */
	public String regService() {
		try {
			Date date = new Date();
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			// 订单创建时间
			serviceObj.setOrderAddTime(sdf1.format(date));
			// serviceObj.setServiceEnd(0);
			// 返回已经接单的随机打车，若没有，则将全部随即打车取消订单,若不是则为预约
			if (serviceObj.getServiceType() == 0) {
				clientServiceBo.updateAllSuiji(serviceObj);
			}
			serviceObj = clientServiceBo.regService(serviceObj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 翻页
	 * 
	 * @return
	 */
	public String getServiceListForUser() {
		try {
			serviceMap = clientServiceBo.getServiceListForUser(serviceObj,
					pageNo, pageSize);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 通过坐标返回公里数内订单
	 * 
	 * @return
	 */
	public String getRandomServiceList() {
		try {
			serviceObj = new TTaxiserverTaxiservice();
			serviceObj.setStartAddLat(lat);
			serviceObj.setStartAddLon(lon);
			serviceList = clientServiceBo.getRandomServiceList(serviceObj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 司机取消订单
	 * 
	 * @return
	 */
	public String cancelService() {
		try {
			isTrue = clientServiceBo.cancelService(serviceID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 乘客删除订单
	 * 
	 * @return
	 */
	public String removeService() {
		try {
			isTrue = clientServiceBo.removeService(serviceObj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 接受一个订单
	 * 
	 * @return
	 */
	public String chexInService() {
		try {
			isTrue = clientServiceBo.chexInService(serviceObj);
			if (isTrue) {
				updateCount = 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 完成订单
	 * 
	 * @return
	 */
	public String overService() {
		try {
			Date date = new Date();
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (serviceObj.getServiceType() == 0) {
				serviceObj.setEndTime(sdf1.format(date));
				serviceObj.setStartTime(sdf1.format(new Date(date.getTime()
						- serviceObj.getGoTime())));
			} else if (serviceObj.getServiceType() == 1) {
				serviceObj.setAppointmentEndTime(sdf1.format(date));
			}
			clientServiceBo.overService(serviceObj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 返回预约列表
	 * 
	 * @return
	 */
	public String getAppointmentService() {
		try {
			serviceList = clientServiceBo.getAppointmentServiceList(serviceObj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 更新司机坐标
	 * 
	 * @return
	 */
	public String updateDriverCoordinate() {
		try {
			Date date = new Date();
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			drivreInfo.setUpdateTime(sdf1.format(date));
			clientUserBo.updateDriverCoordinate(drivreInfo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public Long getServiceID() {
		return serviceID;
	}

	public void setServiceID(Long serviceID) {
		this.serviceID = serviceID;
	}

	public ClientUserBo getClientUserBo() {
		return clientUserBo;
	}

	public void setClientUserBo(ClientUserBo clientUserBo) {
		this.clientUserBo = clientUserBo;
	}

	public ClientServiceBo getClientServiceBo() {
		return clientServiceBo;
	}

	public void setClientServiceBo(ClientServiceBo clientServiceBo) {
		this.clientServiceBo = clientServiceBo;
	}

	public TTaxiserverTaxiuser getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(TTaxiserverTaxiuser userInfo) {
		this.userInfo = userInfo;
	}

	public TTaxiserverDriverinfo getDrivreInfo() {
		return drivreInfo;
	}

	public void setDrivreInfo(TTaxiserverDriverinfo drivreInfo) {
		this.drivreInfo = drivreInfo;
	}

	public List<TTaxiserverTaxiservice> getServiceList() {
		return serviceList;
	}

	public void setServiceList(List<TTaxiserverTaxiservice> serviceList) {
		this.serviceList = serviceList;
	}

	public HashMap<String, Object> getServiceMap() {
		return serviceMap;
	}

	public void setServiceMap(HashMap<String, Object> serviceMap) {
		this.serviceMap = serviceMap;
	}

	public boolean isTrue() {
		return isTrue;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
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

	public TTaxiserverTaxiservice getServiceObj() {
		return serviceObj;
	}

	public void setServiceObj(TTaxiserverTaxiservice serviceObj) {
		this.serviceObj = serviceObj;
	}

	public void setTrue(boolean isTrue) {
		this.isTrue = isTrue;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

}
