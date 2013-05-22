package com.taxi.admin.authenticate.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.taxi.admin.authenticate.bo.AuthenticateBO;
import com.taxi.admin.authenticate.model.MenuModel;
import com.taxi.admin.authenticate.model.RoleModel;
import com.taxi.admin.authenticate.model.RoleVsMenuModel;
import com.taxi.admin.authenticate.model.UserModel;
import com.taxi.admin.base.BaseAction;
import com.taxi.admin.cache.CacheFactory;
import com.taxi.admin.common.CookieUtil;
import com.taxi.admin.common.Page;
import com.taxi.admin.email.EmailModel;
import com.taxi.admin.email.SendEmailBOable;
import com.cplatform.privacy.encrypt.MD5Algorithm;
import com.opensymphony.xwork2.ActionContext;

/**
 * 系统管理 Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2012-1-31 下午3:29:33
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: wangzhen@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@SuppressWarnings("serial")
public class AuthenticateAction extends BaseAction {

	private static Logger log = Logger.getLogger("AuthenticateAction.class");

	private AuthenticateBO authenticateBo;

	public void setAuthenticateBo(AuthenticateBO authenticateBo) {
		this.authenticateBo = authenticateBo;
	}

	/**
	 * 菜单id
	 */
	private String menuId = "0";

	/**
	 * 主菜单实体类
	 */
	private MenuModel menuModel;

	/**
	 * 存放主菜单的集合
	 */
	private List<MenuModel> listMenu;

	/**
	 * 菜单字符串
	 */
	private String listMenuStr = "";

	/**
	 * 返回错误信息
	 */
	private String errorMsg = "";

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/**
	 * 得到菜单字符串
	 * 
	 * @return
	 */
	public String getListMenuStr() {
		return listMenuStr;
	}

	/**
	 * 设置菜单字符串
	 * 
	 * @param listMenuStr
	 */
	public void setListMenuStr(String listMenuStr) {
		this.listMenuStr = listMenuStr;
	}

	/**
	 * 得到菜单id
	 * 
	 * @return
	 */
	public String getMenuId() {
		return menuId;
	}

	/**
	 * 设置菜单id
	 * 
	 * @param menuId
	 */
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	/**
	 * 得到菜单实体
	 * 
	 * @return
	 */
	public MenuModel getMenuModel() {
		return menuModel;
	}

	/**
	 * 设置菜单实体
	 * 
	 * @param menuModel
	 */
	public void setMenuModel(MenuModel menuModel) {
		this.menuModel = menuModel;
	}

	/**
	 * 得到菜单集合
	 * 
	 * @return
	 */
	public List<MenuModel> getListMenu() {
		return listMenu;
	}

	/**
	 * 设置菜单集合
	 * 
	 * @param listMenu
	 */
	public void setListMenu(List<MenuModel> listMenu) {
		this.listMenu = listMenu;
	}

	/**
	 * 存放角色集合
	 */
	private List<RoleModel> listRole;

	public List<RoleModel> getListRole() {
		return listRole;
	}

	public void setListRole(List<RoleModel> listRole) {
		this.listRole = listRole;
	}

	/**
	 * 角色对象
	 */
	private RoleModel roleModel;

	/**
	 * 得到角色对象
	 * 
	 * @return
	 */
	public RoleModel getRoleModel() {
		return roleModel;
	}

	/**
	 * 设置角色对象
	 * 
	 * @param roleModel
	 */
	public void setRoleModel(RoleModel roleModel) {
		this.roleModel = roleModel;
	}

	private int pageNo = 1;

	private int pageSize = 15;

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

	/**
	 * 分页
	 */
	private Page pageWapSite;

	public Page getPageWapSite() {
		return pageWapSite;
	}

	public void setPageWapSite(Page pageWapSite) {
		this.pageWapSite = pageWapSite;
	}

	/**
	 * 存放用户集合
	 */
	private List<UserModel> listUser;

	public List<UserModel> getListUser() {
		return listUser;
	}

	public void setListUser(List<UserModel> listUser) {
		this.listUser = listUser;
	}

	/**
	 * 角色id
	 */
	private String roleId = "0";

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * 存放页面传来的菜单复选框的菜单id
	 */
	private String[] ck_id;

	public String[] getCk_id() {
		return ck_id;
	}

	public void setCk_id(String[] ck_id) {
		this.ck_id = ck_id;
	}

	/**
	 * 角色与菜单对应实体
	 */
	private RoleVsMenuModel roleVsMenu;

	public RoleVsMenuModel getRoleVsMenu() {
		return roleVsMenu;
	}

	public void setRoleVsMenu(RoleVsMenuModel roleVsMenu) {
		this.roleVsMenu = roleVsMenu;
	}

	/**
	 * 用户实体
	 */
	private UserModel userModel;

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

	/**
	 * 用户id
	 */
	private String userId = "";

	/**
	 * 得到用户id
	 * 
	 * @return
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * 设置用户id
	 * 
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/** 判断登录名是否唯一，1表示是，0表示否 */
	private int isUnique;

	public int getIsUnique() {
		return isUnique;
	}

	public void setIsUnique(int isUnique) {
		this.isUnique = isUnique;
	}

	/** 判断是增加还是修改 */
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/** 当前选择的角色id */
	private Long currentRoleId;

	/** 发送邮件bo层接口 */
	private SendEmailBOable sendEmail;

	/** 发送的邮箱地址 */
	private String to = "";

	public Long getCurrentRoleId() {
		return currentRoleId;
	}

	public void setCurrentRoleId(Long currentRoleId) {
		this.currentRoleId = currentRoleId;
	}

	public SendEmailBOable getSendEmail() {
		return sendEmail;
	}

	public void setSendEmail(SendEmailBOable sendEmail) {
		this.sendEmail = sendEmail;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	/** 验证码 */
	private String verifycode;

	public String getVerifycode() {
		return verifycode;
	}

	public void setVerifycode(String verifycode) {
		this.verifycode = verifycode;
	}

	/** 是否登录成功或是否修改密码或登录成功0为成功 */
	private int result = 0;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	/**
	 * 页面接受地区数组
	 */
	private String[] ckProvince;

	public String[] getCkProvince() {
		return ckProvince;
	}

	public void setCkProvince(String[] ckProvince) {
		this.ckProvince = ckProvince;
	}

	/**
	 * 如果缓存里没有该权限信息，从数据库里读取
	 * 
	 * @return
	 */
	public RoleVsMenuModel getRoleVsMenuModel() {
		String loginName = CookieUtil
				.getCookieValue(ServletActionContext.getRequest(),
						CookieUtil.LOGIN_NAME_COOKIE);
		userModel = authenticateBo.listUser(loginName);
		if (null != userModel) {
			try {
				roleVsMenu = this.authenticateBo.getRoleVs(
						userModel.getRoleId(), Long.valueOf(menuId));
			} catch (Exception ex) {
				ex.printStackTrace();
				log.info("从库里读取用户角色权限失败" + ex);
			}
		}
		return roleVsMenu;

	}

	/**
	 * 加载系统登录页面
	 * 
	 * @return
	 */
	public String login() {
		return SUCCESS;
	}

	/**
	 * 校验登录</p> 验证码不符返回登录界面</P>
	 * <p>
	 * 用户验证成功首先获取该用户所有的角色放入session，该角色集合可根据用户需要在其中自由切换
	 * </P>
	 * 当前角色默认获取保留在Cookie中的上次使用的角色
	 * 
	 * @author guodong@c-platform.com
	 * @return 到相应页面的字符串
	 */
	public String check() {
		try {
			ActionContext ctx = ActionContext.getContext();
			Map session = ctx.getSession();
			String code = (String) session.get("emailRand");
			if (verifycode.equals(code)) {
				if (authenticateBo.check(userModel.getLoginName().trim(),
						MD5Algorithm.digest2Str(userModel.getPassword()))) {
					HttpServletRequest request = ServletActionContext
							.getRequest();
					HttpServletResponse response = ServletActionContext
							.getResponse();
					UserModel tmpUserModel = authenticateBo.listUser(userModel
							.getLoginName().trim());
					if (tmpUserModel != null) {
						ActionContext.getContext().getSession()
								.put("loginName", tmpUserModel.getLoginName());
						ActionContext.getContext().getSession()
								.put("userId", tmpUserModel.getUserId());
						ActionContext.getContext().getSession()
								.put("roleId", tmpUserModel.getRoleId());
						CookieUtil.setCookie(request, response,
								CookieUtil.LOGIN_NAME_COOKIE,
								String.valueOf(tmpUserModel.getLoginName()),
								CookieUtil.COOKIE_ALIVE_TIME);
						CookieUtil.setCookie(request, response,
								CookieUtil.USER_ID_COOKIE,
								String.valueOf(tmpUserModel.getUserId()),
								CookieUtil.COOKIE_ALIVE_TIME);
						CookieUtil.setCookie(request, response,
								CookieUtil.USER_ROLEID_COOKIE,
								String.valueOf(tmpUserModel.getRoleId()),
								CookieUtil.COOKIE_ALIVE_TIME);
						// 根据用户的角色查询所对应的权限
						List<RoleVsMenuModel> list = this.authenticateBo
								.getRoleVsRoleId(tmpUserModel.getRoleId());
						Map<Long, RoleVsMenuModel> map = new HashMap<Long, RoleVsMenuModel>();// 存放角色的权限
						if (null != list && !list.isEmpty()) {
							for (int i = 0; i < list.size(); i++) {
								roleVsMenu = list.get(i);
								map.put(roleVsMenu.getMenuId(), roleVsMenu);
							}
							// 将角色权限放入缓存
							CacheFactory.setRoleVsMenuByKey("roleVsMenuCache",
									tmpUserModel.getRoleId().toString(), map);
						}
					}
					return SUCCESS;
				} else {// 用户名或密码不正确
					result = 1;
					errorMsg = "用户名或密码不正确";
					return ERROR;
				}
			} else {// 验证码错误
				errorMsg = "验证码错误";
				result = 2;
				return ERROR;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			log.info("登录失败" + ex);
			errorMsg = "系统错误";
		}
		return SUCCESS;
	}

	/**
	 * 发送验证码到邮箱
	 * 
	 * @return
	 * @throws Exception
	 */
	public String sendVerifycode() {
		String toEmail = "";
		if (to != null && !to.equals("")) {
			toEmail = getTo().trim();
		} else if (userModel != null && userModel.getLoginName() != null
				&& !userModel.getLoginName().equals("")) {
			UserModel toEmailUser = authenticateBo.listUser(userModel
					.getLoginName());
			if (toEmailUser != null) {
				toEmail = toEmailUser.getEmail().trim();
			}
		}

		EmailModel emailModel = new EmailModel();
		emailModel.setFrom("talkliu@126.com");// 设置发送人
		emailModel.setTo(toEmail);// 设置接收人
		emailModel.setTitle("出租车管理平台");// 设置标题
		// 取随机产生的认证码(4位数字)
		String sRand = "";
		Random random = new Random();
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;
		}
		// 将认证码存入SESSION
		ServletActionContext.getRequest().getSession()
				.setAttribute("emailRand", sRand);

		emailModel.setContent("您的验证码为：" + sRand);
		try {
			sendEmail.sendEmailNoAttachFile(emailModel);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("发送验证码失败：" + e);
		}

		return SUCCESS;
	}

	/**
	 * 登出
	 * 
	 * @return SUCCESS
	 */
	public String logout() {
		ActionContext.getContext().getSession().remove("loginName");
		return SUCCESS;
	}

	/**
	 * 加载首页框架
	 * 
	 * @return
	 */
	public String main() {
		return SUCCESS;
	}

	/**
	 * 加载菜单
	 * 
	 * @return
	 */
	public String menu() {
		String loginName = CookieUtil
				.getCookieValue(ServletActionContext.getRequest(),
						CookieUtil.LOGIN_NAME_COOKIE);
		userModel = authenticateBo.listUser(loginName);
		if (null != userModel) {
			try {
				listMenuStr = this.authenticateBo.listMainMenu(userModel
						.getRoleId());
			} catch (Exception ex) {
				ex.printStackTrace();
				log.info("加载菜单失败：" + ex);
			}

		}
		return SUCCESS;
	}

	/**
	 * 加载主页部分
	 * 
	 * @return
	 */
	public String welcome() {
		return SUCCESS;
	}

	/**
	 * 进入修改密码页面
	 * 
	 * @return
	 */
	public String modifyPassword() {
		String loginName = CookieUtil
				.getCookieValue(ServletActionContext.getRequest(),
						CookieUtil.LOGIN_NAME_COOKIE);
		userModel = authenticateBo.listUser(loginName);
		return SUCCESS;
	}

	/**
	 * 用户输入的旧密码
	 */
	private String passwordOld;

	/**
	 * 用户输入的新密码
	 */
	private String passwordNew;

	public String getPasswordOld() {
		return passwordOld;
	}

	public void setPasswordOld(String passwordOld) {
		this.passwordOld = passwordOld;
	}

	public String getPasswordNew() {
		return passwordNew;
	}

	public void setPasswordNew(String passwordNew) {
		this.passwordNew = passwordNew;
	}

	/**
	 * 修改密码
	 * 
	 * @return
	 */
	public String updatePassword() {
		String loginName = CookieUtil
				.getCookieValue(ServletActionContext.getRequest(),
						CookieUtil.LOGIN_NAME_COOKIE);
		userModel = authenticateBo.listUser(loginName);
		if (userModel.getPassword()
				.equals(MD5Algorithm.digest2Str(passwordOld))) {
			try {
				this.authenticateBo.updateUserPassword(userModel.getUserId(),
						passwordNew);
			} catch (Exception ex) {
				// TODO: handle exception
				log.info("修改密码失败：" + ex);
			}

			return SUCCESS;
		} else {
			errorMsg = "您输入的原始密码不正确";
			return "error";
		}
	}

	/**************** 菜单管理功能 ****************/

	/**
	 * 加载菜单编辑主页
	 * 
	 * @return
	 */
	public String mainEditMenu() {

		return SUCCESS;
	}

	/**
	 * 加载菜单树页
	 * 
	 * @return
	 */
	public String listEditMenu() {
		String roleId = CookieUtil.getCookieValue(
				ServletActionContext.getRequest(),
				CookieUtil.USER_ROLEID_COOKIE);
		Map<Long, RoleVsMenuModel> map = CacheFactory.getRoleVsMenuByKey(
				"roleVsMenuCache", roleId);// 从缓存里读取角色对应权限信息
		try {
			if (null == map) {
				// 从库里读取角色权限信息
				this.getRoleVsMenuModel();
			} else {
				roleVsMenu = map.get(Long.valueOf(menuId));// 根据菜单id读取缓存里的权限信息
			}
			listMenuStr = this.authenticateBo.listMenu();
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
			log.info("加载菜单列表页失败：" + ex);
		}

		return SUCCESS;
	}

	/**
	 * 加载增加菜单页
	 * 
	 * @return
	 */
	public String addEditMenu() {
		return SUCCESS;
	}

	/**
	 * 执行增加菜单操作
	 * 
	 * @return
	 */
	public String insertEditMenu() {

		try {
			boolean isTitleIn = this.authenticateBo.isTitleIn(menuModel
					.getMenuTitle());
			if (isTitleIn) {
				errorMsg = "该名称已存在";
			} else {
				this.authenticateBo.insertMenu(menuModel, Long.valueOf(menuId));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			log.info("执行增加菜单失败" + ex);
		}

		return SUCCESS;
	}

	/**
	 * 加载编辑菜单页
	 * 
	 * @return
	 */
	public String modifyEditMenu() {
		try {
			menuModel = this.authenticateBo.getMenuModel(Long.valueOf(menuId));// 根据菜单id查询菜单信息
		} catch (Exception ex) {
			ex.printStackTrace();
			log.info("加载编辑菜单页失败" + ex);
		}

		return SUCCESS;
	}

	/**
	 * 执行修改菜单操作
	 * 
	 * @return
	 */
	public String updateEditMenu() {
		try {
			boolean isTitleIn = this.authenticateBo.isTitleIn(menuModel
					.getMenuTitle());
			if (isTitleIn) {
				errorMsg = "该名称已存在";
			} else {
				this.authenticateBo.updateMenu(menuModel, Long.valueOf(menuId));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			log.info("执行修改菜单失败" + ex);
		}

		return SUCCESS;
	}

	/**
	 * 执行删除菜单操作
	 * 
	 * @return
	 */
	public String deleteEditMenu() {
		try {
			this.authenticateBo.deleteMenu(Long.valueOf(menuId));
		} catch (Exception ex) {
			ex.printStackTrace();
			log.info("执行删除菜单失败" + ex);
		}

		return SUCCESS;
	}

	/**************** 角色管理功能 ****************/

	/**
	 * 加载角色列表信息页
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String listRole() {
		String roleId = CookieUtil.getCookieValue(
				ServletActionContext.getRequest(),
				CookieUtil.USER_ROLEID_COOKIE);
		Map<Long, RoleVsMenuModel> map = CacheFactory.getRoleVsMenuByKey(
				"roleVsMenuCache", roleId);// 从缓存里读取角色对应权限信息
		try {
			if (null == map) {
				// 从库里读取角色权限信息
				this.getRoleVsMenuModel();
			} else {
				roleVsMenu = map.get(Long.valueOf(menuId));// 根据菜单id读取缓存里的权限信息
			}
			pageWapSite = this.authenticateBo.listRole(pageNo, pageSize);
			pageWapSite.addParam("menuId", menuId);
			if (null != pageWapSite) {
				listRole = (List<RoleModel>) pageWapSite.getList();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			log.info("加载角色列表失败" + ex);
		}

		return SUCCESS;
	}

	/**
	 * 加载增加角色页
	 * 
	 * @return
	 */
	public String addRole() {
		try {
			listMenuStr = this.authenticateBo.addRole();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return SUCCESS;
	}

	/**
	 * 插入角色信息
	 * 
	 * @return
	 */
	public String insertRole() {
		try {

			HttpServletRequest request = ServletActionContext.getRequest();
			// 插入角色与菜单对应信息
			if (null != ck_id) {
				this.authenticateBo.insertRole(roleModel);// 插入角色信息
				for (int i = 0; i < ck_id.length; i++) {
					String provinceStr = "";// 接收地区限制的地区
					MenuModel menuModelOld = this.authenticateBo
							.getMenuModel(Long.valueOf(ck_id[i]));
					if (null != menuModelOld) {
						RoleVsMenuModel roleVsMenu = new RoleVsMenuModel();
						roleVsMenu.setRoleId(roleModel.getRoleId());
						roleVsMenu.setMenuId(menuModelOld.getMenuId());// 得到id查询出的菜单的id
						if (menuModelOld.getMenuParentId() == 0) {
							roleVsMenu.setMenuOperateType(1);// 得到页面上传的读写权限
							roleVsMenu.setMenuAreaScope("不限");// 地区限制
							roleVsMenu.setMenuPersonScope(1);// 得到页面上川的人员限制
						} else {
							if (request.getParameter("operateType"
									+ menuModelOld.getMenuId()) != null
									&& request.getParameter("operateType"
											+ menuModelOld.getMenuId()) != "") {
								roleVsMenu.setMenuOperateType(Integer
										.valueOf(request
												.getParameter("operateType"
														+ menuModelOld
																.getMenuId())));// 得到页面上传的读写权限
							}

							ckProvince = request
									.getParameterValues("ckProvince"
											+ menuModelOld.getMenuId());
							if (null != ckProvince) {
								for (int j = 0; j < ckProvince.length; j++) {
									provinceStr += ckProvince[j] + ",";
								}
							}
							roleVsMenu.setMenuAreaScope(provinceStr);// 地区限制
							if (request.getParameter("personScope"
									+ menuModelOld.getMenuId()) != null
									&& request.getParameter("personScope"
											+ menuModelOld.getMenuId()) != "") {
								roleVsMenu.setMenuPersonScope(Integer
										.valueOf(request
												.getParameter("personScope"
														+ menuModelOld
																.getMenuId())));// 得到页面上川的人员限制
							}

						}
						this.authenticateBo.insertRoleVsMenu(roleVsMenu);

					}
				}
			} else {
				errorMsg = "请选择权限";
				return "error";
			}
			/************** 更新缓存权限信息 **************/
			// 根据用户的角色查询所对应的权限
			List<RoleVsMenuModel> list = this.authenticateBo
					.getRoleVsRoleId(roleModel.getRoleId());
			Map<Long, RoleVsMenuModel> map = new HashMap<Long, RoleVsMenuModel>();// 存放角色的权限
			if (null != list && !list.isEmpty()) {
				for (int i = 0; i < list.size(); i++) {
					roleVsMenu = list.get(i);
					map.put(roleVsMenu.getMenuId(), roleVsMenu);
				}
				// 将角色权限放入缓存
				CacheFactory.setRoleVsMenuByKey("roleVsMenuCache", roleModel
						.getRoleId().toString(), map);
			}
			/************** 更新缓存权限信息 **************/
		} catch (Exception ex) {
			ex.printStackTrace();
			log.info("插入角色失败" + ex);
		}

		return SUCCESS;
	}

	/**
	 * 加载修改角色页
	 * 
	 * @return
	 */
	public String modifyRole() {
		try {
			roleModel = this.authenticateBo.getRoleModel(Long.valueOf(roleId));
			listMenuStr = this.authenticateBo.modifyRole(Long.valueOf(roleId));
		} catch (Exception ex) {
			ex.printStackTrace();
			log.info("加载修改角色页失败" + ex);
		}

		return SUCCESS;
	}

	/**
	 * 更新角色信息
	 * 
	 * @return
	 */
	public String updateRole() {
		try {
			this.authenticateBo.updateRole(roleModel, Long.valueOf(roleId));
			HttpServletRequest request = ServletActionContext.getRequest();

			// 插入角色与菜单对应信息
			if (null != ck_id) {
				this.authenticateBo.deleteRoleVsMenu(Long.valueOf(roleId));// 删除角色下的所有权限
				for (int i = 0; i < ck_id.length; i++) {
					String provinceStr = "";// 接收地区限制的地区
					MenuModel menuModelOld = this.authenticateBo
							.getMenuModel(Long.valueOf(ck_id[i]));
					if (null != menuModelOld) {
						RoleVsMenuModel roleVsMenu = new RoleVsMenuModel();
						roleVsMenu.setRoleId(Long.valueOf(roleId));
						roleVsMenu.setMenuId(menuModelOld.getMenuId());// 得到id查询出的菜单的id
						if (menuModelOld.getMenuParentId() == 0) {
							roleVsMenu.setMenuOperateType(1);// 得到页面上传的读写权限
							roleVsMenu.setMenuAreaScope("");// 地区限制
							roleVsMenu.setMenuPersonScope(1);// 得到页面上川的人员限制
						} else {
							if (request.getParameter("operateType"
									+ menuModelOld.getMenuId()) != null
									&& request.getParameter("operateType"
											+ menuModelOld.getMenuId()) != "") {
								roleVsMenu.setMenuOperateType(Integer
										.valueOf(request
												.getParameter("operateType"
														+ menuModelOld
																.getMenuId())));// 得到页面上传的读写权限
							}
							ckProvince = request
									.getParameterValues("ckProvince"
											+ menuModelOld.getMenuId());
							if (null != ckProvince) {
								for (int j = 0; j < ckProvince.length; j++) {
									provinceStr += ckProvince[j] + ",";
								}
							}
							roleVsMenu.setMenuAreaScope(provinceStr);// 地区限制
							if (request.getParameter("personScope"
									+ menuModelOld.getMenuId()) != null
									&& request.getParameter("personScope"
											+ menuModelOld.getMenuId()) != "") {
								roleVsMenu.setMenuPersonScope(Integer
										.valueOf(request
												.getParameter("personScope"
														+ menuModelOld
																.getMenuId())));// 得到页面上川的人员限制
							}
						}
						this.authenticateBo.insertRoleVsMenu(roleVsMenu);
					}
				}
			} else {
				errorMsg = "请选择权限";
				return "error";
			}
			/************** 更新缓存权限信息 **************/
			// 根据用户的角色查询所对应的权限
			List<RoleVsMenuModel> list = this.authenticateBo
					.getRoleVsRoleId(Long.valueOf(roleId));
			Map<Long, RoleVsMenuModel> map = new HashMap<Long, RoleVsMenuModel>();// 存放角色的权限
			if (null != list && !list.isEmpty()) {
				for (int i = 0; i < list.size(); i++) {
					roleVsMenu = list.get(i);
					map.put(roleVsMenu.getMenuId(), roleVsMenu);
				}
				// 将角色权限放入缓存
				CacheFactory.setRoleVsMenuByKey("roleVsMenuCache", roleId, map);
			}
			/************** 更新缓存权限信息 **************/
		} catch (Exception ex) {
			ex.printStackTrace();
			log.info("更新角色信息失败" + ex);
		}

		return SUCCESS;
	}

	/**
	 * 删除角色信息
	 * 
	 * @return
	 */
	public String deleteRole() {
		try {
			// 根据角色id查看是否有用户
			List<UserModel> listUser = this.authenticateBo
					.listUserByRoleId(Long.valueOf(roleId));
			if (null == listUser || listUser.isEmpty()) {
				this.authenticateBo.deleteRoleModel(Long.valueOf(roleId));
				this.authenticateBo.deleteRoleVsMenu(Long.valueOf(roleId));// 删除角色下的所有权限
			} else {
				errorMsg = "该角色下含有用户信息，请删除该角色下的所有用户再执行删除操作";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return SUCCESS;
	}

	/**************** 用户管理功能 ****************/

	/**
	 * 加载用户列表信息页
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String listUser() {
		String roleId = CookieUtil.getCookieValue(
				ServletActionContext.getRequest(),
				CookieUtil.USER_ROLEID_COOKIE);
		Map<Long, RoleVsMenuModel> map = CacheFactory.getRoleVsMenuByKey(
				"roleVsMenuCache", roleId);// 从缓存里读取角色对应权限信息
		try {
			if (null == map) {
				// 从库里读取角色权限信息
				this.getRoleVsMenuModel();
			} else {
				roleVsMenu = map.get(Long.valueOf(menuId));// 根据菜单id读取缓存里的权限信息
			}
			pageWapSite = this.authenticateBo.listUser(pageNo, pageSize);
			pageWapSite.addParam("menuId", menuId);
			if (null != pageWapSite) {
				listUser = (List<UserModel>) pageWapSite.getList();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			log.info("加载用户列表失败" + ex);
		}

		// }
		return SUCCESS;
	}

	/**
	 * 加载增用户色页
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String addUser() {
		try {
			listRole = (List<RoleModel>) this.authenticateBo.listRole(pageNo,
					100).getList();
		} catch (Exception ex) {
			ex.printStackTrace();
			log.info("加载增加用户页失败" + ex);
		}

		return SUCCESS;
	}

	/**
	 * 校验登录名是否唯一
	 * 
	 * @return SUCCESS
	 */
	public String checkLoginName() {
		try {
			isUnique = authenticateBo.checkLoginName(userModel, type);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return SUCCESS;
	}

	/**
	 * 插入用户信息
	 * 
	 * @return
	 */
	public String insertUser() {
		try {
			this.authenticateBo.insertUser(userModel);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.info("插入用户信息失败" + ex);
		}

		return SUCCESS;
	}

	/**
	 * 加载修改用户页
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String modifyUser() {
		try {
			userModel = this.authenticateBo.getUserModel(Long.valueOf(userId));
			listRole = (List<RoleModel>) this.authenticateBo.listRole(pageNo,
					100).getList();
		} catch (Exception ex) {
			ex.printStackTrace();
			log.info("加载修改用户页失败" + ex);
		}

		return SUCCESS;
	}

	/**
	 * 更新用户信息
	 * 
	 * @return
	 */
	public String updateUser() {
		try {
			this.authenticateBo.updateUser(userModel, Long.valueOf(userId));
		} catch (Exception ex) {
			ex.printStackTrace();
			log.info("更新用户信息失败" + ex);
		}

		return SUCCESS;
	}

	/**
	 * 删除用户信息
	 * 
	 * @return
	 */
	public String deleteUser() {
		try {
			this.authenticateBo.deleteUser(Long.valueOf(userId));
		} catch (Exception ex) {
			ex.printStackTrace();
			log.info("删除用户信息失败" + ex);
		}

		return SUCCESS;
	}

	public AuthenticateBO getAuthenticateBo() {
		return authenticateBo;
	}

}
