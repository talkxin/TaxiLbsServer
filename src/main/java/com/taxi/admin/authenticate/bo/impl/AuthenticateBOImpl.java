package com.taxi.admin.authenticate.bo.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.taxi.admin.authenticate.bo.AuthenticateBO;
import com.taxi.admin.authenticate.dao.AuthMenuDAO;
import com.taxi.admin.authenticate.dao.AuthRoleDAO;
import com.taxi.admin.authenticate.dao.AuthRoleVsMenuDAO;
import com.taxi.admin.authenticate.dao.AuthUserDAO;
import com.taxi.admin.authenticate.model.MenuModel;
import com.taxi.admin.authenticate.model.RoleModel;
import com.taxi.admin.authenticate.model.RoleVsMenuModel;
import com.taxi.admin.authenticate.model.UserModel;
import com.taxi.admin.base.exception.DAOException;
import com.taxi.admin.common.DTree;
import com.taxi.admin.common.Page;
import com.taxi.admin.common.ProvinceList;
import com.cplatform.privacy.encrypt.MD5Algorithm;

public class AuthenticateBOImpl implements AuthenticateBO {

	/**
	 * 得到菜单接口
	 */
	private AuthMenuDAO authenticateDao;

	public void setAuthenticateDao(AuthMenuDAO authenticateDao) {
		this.authenticateDao = authenticateDao;
	}

	/**
	 * 得到角色接口
	 */
	private AuthRoleDAO authRoleDao;

	public void setAuthRoleDao(AuthRoleDAO authRoleDao) {
		this.authRoleDao = authRoleDao;
	}

	/**
	 * 得到角色与菜单对应接口
	 */
	private AuthRoleVsMenuDAO authRoleVsMenuDao;

	public void setAuthRoleVsMenuDao(AuthRoleVsMenuDAO authRoleVsMenuDao) {
		this.authRoleVsMenuDao = authRoleVsMenuDao;
	}

	/**
	 * 得到用户接口
	 */
	private AuthUserDAO userDao;

	public void setUserDao(AuthUserDAO userDao) {
		this.userDao = userDao;
	}

	/**************** 加载首页 ****************/

	/**
	 * 查看所有的首页菜单项
	 * 
	 * @return
	 * @throws DAOException
	 */
	@Override
	public String listMainMenu(Long roleId) throws Exception {
		List<Long> listMenu = this.authenticateDao.listMainMenu(roleId);
		String listMenuStr = "";
		if (null != listMenu) {
			DTree root = DTree.createRoot(null, null, "管理系统",
					"javascript:menu_modify(0,1);", "0", "fff", "", false,
					false, "e");
			if (null != listMenu) {
				for (int i = 0; i < listMenu.size(); i++) {
					MenuModel menuModel = this.authenticateDao
							.getMenuModelObject(listMenu.get(i));
					if (null != menuModel) {
						addMainMenuChild(roleId, menuModel, root.appendChild(
								null, null, menuModel.getMenuTitle(), "",
								String.valueOf(menuModel.getMenuId()),
								menuModel.getMenuTitle(), "contentIframe",
								false, false, ""));
					}
				}
			}
			listMenuStr = DTree.initDefaultTree(root);
		}
		return listMenuStr;
	}

	/**
	 * 进入首页菜单树子页面
	 * 
	 * @param menu
	 * @param parent
	 */
	private void addMainMenuChild(Long roleId, MenuModel menu, DTree parent)
			throws Exception {
		List<Long> list = this.authenticateDao.listMainChildMenu(roleId,
				menu.getMenuId());
		if (null != list) {
			for (int i = 0; i < list.size(); i++) {
				MenuModel sub = this.authenticateDao.getMenuModelObject(list
						.get(i));
				if (sub.getMenuLink().indexOf("?") != -1) {
					addMainMenuChild(roleId, sub, parent.appendChild(null,
							null, sub.getMenuTitle(), sub.getMenuLink()
									+ "&menuId=" + sub.getMenuId(),
							String.valueOf(sub.getMenuId()),
							sub.getMenuTitle(), "contentIframe", false, false,
							""));
				} else {
					addMainMenuChild(roleId, sub, parent.appendChild(null,
							null, sub.getMenuTitle(), sub.getMenuLink()
									+ "?menuId=" + sub.getMenuId(),
							String.valueOf(sub.getMenuId()),
							sub.getMenuTitle(), "contentIframe", false, false,
							""));
				}
			}
		}
	}

	/**************** 菜单管理功能 ****************/

	/**
	 * 查看所有的菜单项
	 * 
	 * @return
	 * @throws DAOException
	 */
	@Override
	public String listMenu() throws Exception {
		List<MenuModel> listMenu = this.authenticateDao.listMenu();
		String listMenuStr = "";
		if (null != listMenu) {
			DTree root = DTree.createRoot(null, null, "管理系统",
					"javascript:menu_modify(0,1);", "0", "fff", "", false,
					false, "e");
			if (null != listMenu) {
				for (int i = 0; i < listMenu.size(); i++) {
					MenuModel menuModel = listMenu.get(i);
					if (null != menuModel) {
						// 查看该主菜单下是否含有子菜单
						List<MenuModel> menuChild = this.authenticateDao
								.getMenuByParentID(menuModel.getMenuId());
						if (null != menuChild && !menuChild.isEmpty()) {
							addMenuChild(
									menuModel,
									root.appendChild(
											null,
											null,
											menuModel.getMenuTitle(),
											"javascript:menu_modify("
													+ menuModel.getMenuId()
													+ ","
													+ menuModel.getMenuType()
													+ ",2)",
											String.valueOf(menuModel
													.getMenuId()),
											menuModel.getMenuTitle(),
											"",
											false,
											false,
											" <a href=\"#\" onclick=\"javascript:menu_modify("
													+ menuModel.getMenuId()
													+ ","
													+ menuModel.getMenuType()
													+ ",1);\"><img src=\"images//dtree//add.gif\"//></a>"));
						} else {
							addMenuChild(
									menuModel,
									root.appendChild(
											null,
											null,
											menuModel.getMenuTitle(),
											"javascript:menu_modify("
													+ menuModel.getMenuId()
													+ ","
													+ menuModel.getMenuType()
													+ ",2)",
											String.valueOf(menuModel
													.getMenuId()),
											menuModel.getMenuTitle(),
											"",
											false,
											false,
											" <a href=\"#\" onclick=\"javascript:menu_modify("
													+ menuModel.getMenuId()
													+ ","
													+ menuModel.getMenuType()
													+ ",1);\"><img src=\"images//dtree//add.gif\"//></a>  <a href=\"#\" onclick=\"javascript:menu_del("
													+ menuModel.getMenuId()
													+ ");\"><img src=\"images//dtree//del.gif\"//></a>"));
						}

					}
				}
			}
			listMenuStr = DTree.initDefaultTree(root);
		}
		return listMenuStr;
	}

	/**
	 * 进入菜单树子页面
	 * 
	 * @param menu
	 * @param parent
	 */
	private void addMenuChild(MenuModel menu, DTree parent) throws Exception {
		List<MenuModel> list = this.getMenuByParentID(menu.getMenuId());
		for (MenuModel sub : list) {
			if (sub.getMenuType() == 1) {
				// 查看该主菜单下是否含有子菜单
				List<MenuModel> menuChild = this.authenticateDao
						.getMenuByParentID(sub.getMenuId());
				if (null != menuChild && !menuChild.isEmpty()) {
					addMenuChild(
							sub,
							parent.appendChild(
									null,
									null,
									sub.getMenuTitle(),
									"javascript:menu_modify(" + sub.getMenuId()
											+ "," + sub.getMenuType() + ",2)",
									String.valueOf(sub.getMenuId()),
									sub.getMenuTitle(),
									"",
									false,
									false,
									" <a href=\"#\" onclick=\"javascript:menu_modify("
											+ sub.getMenuId()
											+ ","
											+ sub.getMenuType()
											+ ",1);\"><img src=\"images//dtree//add.gif\"//></a>"));
				} else {
					addMenuChild(
							sub,
							parent.appendChild(
									null,
									null,
									sub.getMenuTitle(),
									"javascript:menu_modify(" + sub.getMenuId()
											+ "," + sub.getMenuType() + ",2)",
									String.valueOf(sub.getMenuId()),
									sub.getMenuTitle(),
									"",
									false,
									false,
									" <a href=\"#\" onclick=\"javascript:menu_modify("
											+ sub.getMenuId()
											+ ","
											+ sub.getMenuType()
											+ ",1);\"><img src=\"images//dtree//add.gif\"//></a> <a href=\"#\" onclick=\"javascript:menu_del("
											+ sub.getMenuId()
											+ ");\"><img src=\"images//dtree//del.gif\"//></a>"));
				}

			} else {
				addMenuChild(
						sub,
						parent.appendChild(
								null,
								null,
								sub.getMenuTitle(),
								"javascript:menu_modify(" + sub.getMenuId()
										+ "," + sub.getMenuType() + ",2)",
								String.valueOf(sub.getMenuId()),
								sub.getMenuTitle(),
								"",
								false,
								false,
								"<a href=\"#\" onclick=\"javascript:menu_del("
										+ sub.getMenuId()
										+ ");\"><img src=\"images//dtree//del.gif\"//></a>"));
			}

		}
	}

	/**
	 * 根据才带你id查询菜单信息
	 * 
	 * @param menuId
	 *            菜单id
	 * @return
	 */
	@Override
	public MenuModel getMenuModel(Long menuId) throws Exception {
		return this.authenticateDao.getMenuModelObject(menuId);
	}

	/**
	 * 增加菜单项
	 * 
	 * @param menuModel
	 *            菜单实体
	 */
	@Override
	public void insertMenu(MenuModel menuModel, Long parentId) throws Exception {
		menuModel.setMenuParentId(parentId);
		if (menuModel.getMenuType() == 1) {
			menuModel.setMenuOperateType(0);// 读写权限
			menuModel.setMenuAreaScope(0);// 地区限制
			menuModel.setMenuPsersonScope(0);// 人员限制
		}
		this.authenticateDao.menuModelSave(menuModel);
	}

	/**
	 * 修改菜单项
	 * 
	 * @param menuModel
	 *            菜单实体
	 */
	@Override
	public void updateMenu(MenuModel menuModel, Long menuId) throws Exception {
		MenuModel menuModelOld = this.getMenuModel(Long.valueOf(menuId));// 根据id得到菜单信息
		int read = menuModelOld.getMenuOperateType();// 读写权限
		if (null != menuModelOld) {
			menuModelOld.setMenuTitle(menuModel.getMenuTitle());// 菜单名称
			menuModelOld.setMenuType(menuModel.getMenuType());// 菜单是否文件夹
			menuModelOld.setMenuLink(menuModel.getMenuLink());// 菜单链接
			menuModelOld.setMenuOperateType(menuModel.getMenuOperateType());// 菜单读写属性
			menuModelOld.setMenuPsersonScope(menuModel.getMenuPsersonScope());// 菜单限制人员
			menuModelOld.setMenuAreaScope(menuModel.getMenuAreaScope());// 菜单限制地区
			this.authenticateDao.menuModelUpdate(menuModelOld);

			// 如果修改了读写权限
			if (read != menuModel.getMenuOperateType()) {
				// 查询该菜单下的所有权限
				List<RoleVsMenuModel> roleVs = this.authRoleVsMenuDao
						.getRoleVsByMenuId(menuModelOld.getMenuId());
				if (menuModel.getMenuOperateType() == 2) {// 读写权限修改为查询+增加+修改
					if (null != roleVs && !roleVs.isEmpty()) {
						for (RoleVsMenuModel role : roleVs) {
							if (role.getMenuOperateType() != 3) {// 角色权限对应信息里的读写权限不为查询
								role.setMenuOperateType(menuModel
										.getMenuOperateType());// 修改角色权限对应信息里的读写权限为查询+增加+修改
								this.authRoleVsMenuDao
										.roleVsMenuModelUpdate(role);
							}
						}
					}
				} else if (menuModel.getMenuOperateType() == 3) {// 读写权限修改为查询
					if (null != roleVs && !roleVs.isEmpty()) {
						for (RoleVsMenuModel role : roleVs) {
							role.setMenuOperateType(menuModel
									.getMenuOperateType());// 修改角色权限对应信息里的读写权限为查询
							this.authRoleVsMenuDao.roleVsMenuModelUpdate(role);
						}
					}
				}
			}
		}
	}

	/**
	 * 删除菜单项
	 * 
	 * @param menuId
	 *            菜单id
	 */
	@Override
	public void deleteMenu(Long menuId) throws Exception {
		this.authenticateDao.menuModelDelete(menuId);
		// 根据菜单id查询菜单与角色对应信息
		List<RoleVsMenuModel> roleVsMenu = this.authRoleVsMenuDao
				.getRoleVsByMenuId(menuId);
		if (null != roleVsMenu && !roleVsMenu.isEmpty()) {
			for (RoleVsMenuModel model : roleVsMenu) {
				this.authRoleVsMenuDao.roleVsMenuModelDelete(model.getId());// 删除该菜单下的所有角色信息
			}
		}

	}

	/**
	 * 根据菜单的父节点查看菜单的子节点
	 * 
	 * @param menuId
	 * @return
	 * @throws DAOException
	 */
	@Override
	public List<MenuModel> getMenuByParentID(Long menuId) throws Exception {
		return this.authenticateDao.getMenuByParentID(menuId);
	}

	/**
	 * 查看菜单名称是否存在
	 * 
	 * @param title
	 * @return
	 */
	@Override
	public boolean isTitleIn(String title) throws Exception {
		List<MenuModel> list = this.authenticateDao.isTitleIn(title);
		if (null != list) {
			if (list.size() > 0) {
				MenuModel menuModel = list.get(0);
				if (menuModel.getMenuTitle().equals(title)) {
					return false;
				} else {
					return true;
				}
			}
		}
		return false;
	}

	/**************** 角色管理功能 ****************/

	/**
	 * 得到角色集合
	 * 
	 * @return
	 */
	@Override
	public Page listRole(int pageNo, int pageSize) throws Exception {
		return this.authRoleDao.listRole(pageNo, pageSize);
	}

	/**
	 * 进入添加角色页面加载菜单信息
	 * 
	 * @return
	 */
	@Override
	public String addRole() throws Exception {
		List<MenuModel> listMenu = this.authenticateDao.listMenu();
		String listMenuStr = "";
		if (null != listMenu) {
			DTree root = DTree.createRoot(null, null, "管理系统",
					"javascript:menu_modify(0,1);", "0", "fff", "", true,
					false, "");
			if (null != listMenu) {
				for (int i = 0; i < listMenu.size(); i++) {
					MenuModel menuModel = listMenu.get(i);
					addMenuChildRole(menuModel, root.appendChild(null, null,
							menuModel.getMenuTitle(),
							"javascript:menu_modify(" + menuModel.getMenuId()
									+ "," + menuModel.getMenuType() + ")",
							String.valueOf(menuModel.getMenuId()),
							menuModel.getMenuTitle(), "", true, false, ""));
				}
			}
			listMenuStr = DTree.initDefaultTree(root);
		}
		return listMenuStr;
	}

	/**
	 * 进入角色菜单树子页面
	 * 
	 * @param menu
	 * @param parent
	 */
	private void addMenuChildRole(MenuModel menu, DTree parent)
			throws Exception {
		List<String> listProvince = ProvinceList.getProvinceList();
		String provinceStr = "";

		List<MenuModel> list = this.getMenuByParentID(menu.getMenuId());
		String htmlRW = "";
		String htmlPerson = "";
		String htmlArea = "";
		for (MenuModel sub : list) {
			if (sub.getMenuOperateType() == 1) {
				htmlRW = "  &nbsp;&nbsp;读写权限:<input type=\"radio\" name=\"operateType"
						+ sub.getMenuId()
						+ "\"  value=\"1\" checked />不限 <input type=\"radio\" name=\"operateType"
						+ sub.getMenuId()
						+ "\"  value=\"2\"/> 查询+增加+修改  <input type=\"radio\" name=\"operateType"
						+ sub.getMenuId() + "\"  value=\"3\" />查询";
			} else if (sub.getMenuOperateType() == 2) {
				htmlRW = "  &nbsp;&nbsp;读写权限:<input type=\"radio\" name=\"operateType"
						+ sub.getMenuId()
						+ "\"  value=\"2\" checked   /> 查询+增加+修改  <input type=\"radio\" name=\"operateType"
						+ sub.getMenuId() + "\"  value=\"3\" />查询";
			} else if (sub.getMenuOperateType() == 3) {
				htmlRW = "  &nbsp;&nbsp;读写权限:<input type=\"radio\" name=\"operateType"
						+ sub.getMenuId() + "\"  value=\"3\" checked />查询";
			}
			if (sub.getMenuPsersonScope() == 1) {
				htmlPerson = "  &nbsp;&nbsp;人员限制:<input type=\"radio\" name=\"personScope"
						+ sub.getMenuId()
						+ "\" value=\"1\" checked />不限   <input type=\"radio\" name=\"personScope"
						+ sub.getMenuId() + "\" value=\"2\"/> 按创建人限制";
			} else if (sub.getMenuPsersonScope() == 2) {
				htmlPerson = "  &nbsp;&nbsp;人员限制:<input type=\"radio\" name=\"personScope"
						+ sub.getMenuId() + "\" value=\"2\" checked /> 按创建人限制";
			}
			if (sub.getMenuAreaScope() == 1) {
				htmlArea = "  &nbsp;&nbsp;地区限制:不限";
			} else if (sub.getMenuAreaScope() == 2) {
				if (null != listProvince) {
					for (int i = 0; i < listProvince.size(); i++) {
						String province = "<input type=\"checkbox\" name=\"ckProvince"
								+ sub.getMenuId()
								+ "\" value=\""
								+ listProvince.get(i)
								+ "\"/>"
								+ listProvince.get(i);
						provinceStr += province;
					}
				}
				htmlArea = "  &nbsp;&nbsp;地区限制:" + provinceStr;
			}
			addMenuChildRole(sub, parent.appendChild(null, null,
					sub.getMenuTitle(),
					"javascript:menmenuOperateTypeu_modify(" + sub.getMenuId()
							+ "," + sub.getMenuType() + ")",
					String.valueOf(sub.getMenuId()), sub.getMenuTitle(), "",
					true, false, htmlRW + htmlPerson + htmlArea));
		}
	}

	/**
	 * 进入修改角色页面加载菜单信息
	 * 
	 * @return
	 */
	@Override
	public String modifyRole(Long roleId) throws Exception {

		List<MenuModel> listMenu = this.authenticateDao.listMenu();
		List<RoleVsMenuModel> listRoleVs = this.authRoleVsMenuDao
				.getRoleVsRoleIdByMain(roleId);
		// int count=0;
		String listMenuStr = "";
		if (null != listMenu) {
			DTree root = DTree.createRoot(null, null, "管理系统",
					"javascript:menu_modify(0,1);", "0", "fff", "", true,
					false, "");
			if (null != listMenu) {

				// 匹配用Map
				Map<Long, Long> tmpMatchMap = new HashMap<Long, Long>();

				// 选中
				for (MenuModel tmpMenuMod : listMenu) {
					long menuId = tmpMenuMod.getMenuId();
					for (RoleVsMenuModel tmpRoleVsMenuMod : listRoleVs) {
						long roleVsMenuId = tmpRoleVsMenuMod.getMenuId();
						if (menuId == roleVsMenuId) {
							addMenuChildRoleVs(tmpMenuMod, root.appendChild(
									null,
									null,
									tmpMenuMod.getMenuTitle(),
									"javascript:menu_modify("
											+ tmpMenuMod.getMenuId() + ","
											+ tmpMenuMod.getMenuType() + ")",
									String.valueOf(tmpMenuMod.getMenuId()),
									tmpMenuMod.getMenuTitle(), "", true, true,
									""), roleId);
							tmpMatchMap.put(tmpMenuMod.getMenuId(),
									tmpMenuMod.getMenuId());
						}
					}
				}

				// 不选
				for (MenuModel tmpMenuMod : listMenu) {
					if (tmpMatchMap.get(tmpMenuMod.getMenuId()) == null) {
						addMenuChildRoleVs(tmpMenuMod,
								root.appendChild(
										null,
										null,
										tmpMenuMod.getMenuTitle(),
										"javascript:menu_modify("
												+ tmpMenuMod.getMenuId() + ","
												+ tmpMenuMod.getMenuType()
												+ ")",
										String.valueOf(tmpMenuMod.getMenuId()),
										tmpMenuMod.getMenuTitle(), "", true,
										false, ""), roleId);
					}
				}

			}
			listMenuStr = DTree.initDefaultTree(root);
		}
		return listMenuStr;
	}

	/**
	 * 进入修改角色菜单树子页面
	 * 
	 * @param menu
	 * @param parent
	 */
	private void addMenuChildRoleVs(MenuModel menu, DTree parent, Long roleId)
			throws Exception {
		List<String> listProvince = ProvinceList.getProvinceList();
		String provinceStr = "";// 选中的区域限制
		String provinceStr_no = "";// 为选中的区域限制

		List<MenuModel> list = this.getMenuByParentID(menu.getMenuId());
		List<RoleVsMenuModel> listRoleVs = this.authRoleVsMenuDao
				.getRoleVsRoleIdByChild(roleId);
		String htmlRW = "";
		String htmlPerson = "";
		String htmlArea = "";
		// 匹配用Map
		Map<Long, Long> tmpMatchMap = new HashMap<Long, Long>();
		String province = "";
		String province_no = "";
		// 选中
		for (MenuModel sub : list) {
			long menuid = sub.getMenuId();
			for (RoleVsMenuModel tmpRoleVsMenuMod : listRoleVs) {
				long roleVsMenuid = tmpRoleVsMenuMod.getMenuId();
				if (menuid == roleVsMenuid) {
					if (sub.getMenuOperateType() == 1) {
						if (tmpRoleVsMenuMod.getMenuOperateType() == 1) {
							htmlRW = "  &nbsp;&nbsp;读写权限:<input type=\"radio\" name=\"operateType"
									+ sub.getMenuId()
									+ "\"  value=\"1\" checked />不限 <input type=\"radio\" name=\"operateType"
									+ sub.getMenuId()
									+ "\"  value=\"2\"/> 查询+增加+修改  <input type=\"radio\" name=\"operateType"
									+ sub.getMenuId() + "\"  value=\"3\" />查询";
						} else if (tmpRoleVsMenuMod.getMenuOperateType() == 2) {
							htmlRW = "  &nbsp;&nbsp;读写权限:<input type=\"radio\" name=\"operateType"
									+ sub.getMenuId()
									+ "\"  value=\"1\"  />不限 <input type=\"radio\" name=\"operateType"
									+ sub.getMenuId()
									+ "\"  value=\"2\" checked/> 查询+增加+修改  <input type=\"radio\" name=\"operateType"
									+ sub.getMenuId() + "\"  value=\"3\" />查询";
						} else if (tmpRoleVsMenuMod.getMenuOperateType() == 3) {
							htmlRW = "  &nbsp;&nbsp;读写权限:<input type=\"radio\" name=\"operateType"
									+ sub.getMenuId()
									+ "\"  value=\"1\"  />不限 <input type=\"radio\" name=\"operateType"
									+ sub.getMenuId()
									+ "\"  value=\"2\"/> 查询+增加+修改  <input type=\"radio\" name=\"operateType"
									+ sub.getMenuId()
									+ "\"  value=\"3\" checked />查询";
						}
					} else if (sub.getMenuOperateType() == 2) {
						if (tmpRoleVsMenuMod.getMenuOperateType() == 2) {
							htmlRW = "  &nbsp;&nbsp;读写权限:<input type=\"radio\" name=\"operateType"
									+ sub.getMenuId()
									+ "\"  value=\"2\" checked   /> 查询+增加+修改  <input type=\"radio\" name=\"operateType"
									+ sub.getMenuId() + "\"  value=\"3\" />查询";
						} else if (tmpRoleVsMenuMod.getMenuOperateType() == 3) {
							htmlRW = "  &nbsp;&nbsp;读写权限:<input type=\"radio\" name=\"operateType"
									+ sub.getMenuId()
									+ "\"  value=\"2\" /> 查询+增加+修改  <input type=\"radio\" name=\"operateType"
									+ sub.getMenuId()
									+ "\"  value=\"3\" checked />查询";
						}
					} else if (sub.getMenuOperateType() == 3) {
						htmlRW = "  &nbsp;&nbsp;读写权限:<input type=\"radio\" name=\"operateType"
								+ sub.getMenuId()
								+ "\"  value=\"3\" checked />查询";

					}
					if (sub.getMenuPsersonScope() == 1) {
						if (tmpRoleVsMenuMod.getMenuPersonScope() == 1) {
							htmlPerson = "  &nbsp;&nbsp;人员限制:<input type=\"radio\" name=\"personScope"
									+ sub.getMenuId()
									+ "\" value=\"1\" checked />不限   <input type=\"radio\" name=\"personScope"
									+ sub.getMenuId()
									+ "\" value=\"2\"/> 按创建人限制";
						} else if (tmpRoleVsMenuMod.getMenuPersonScope() == 2) {
							htmlPerson = "  &nbsp;&nbsp;人员限制:<input type=\"radio\" name=\"personScope"
									+ sub.getMenuId()
									+ "\" value=\"1\"/>不限   <input type=\"radio\" name=\"personScope"
									+ sub.getMenuId()
									+ "\" value=\"2\"  checked /> 按创建人限制";
						}

					} else if (sub.getMenuPsersonScope() == 2) {
						htmlPerson = "  &nbsp;&nbsp;人员限制:<input type=\"radio\" name=\"personScope"
								+ sub.getMenuId()
								+ "\" value=\"2\" checked /> 按创建人限制";
					}
					if (sub.getMenuAreaScope() == 1) {
						htmlArea = "  &nbsp;&nbsp;地区限制:不限";
					} else if (sub.getMenuAreaScope() == 2) {
						if (null != listProvince) {
							for (int i = 0; i < listProvince.size(); i++) {
								if (null != tmpRoleVsMenuMod.getMenuAreaScope()) {
									if (tmpRoleVsMenuMod.getMenuAreaScope()
											.indexOf(listProvince.get(i)) != -1) {
										province = "<input type=\"checkbox\" name=\"ckProvince"
												+ sub.getMenuId()
												+ "\" value=\""
												+ listProvince.get(i)
												+ "\" checked/>"
												+ listProvince.get(i);
									} else {
										province = "<input type=\"checkbox\" name=\"ckProvince"
												+ sub.getMenuId()
												+ "\" value=\""
												+ listProvince.get(i)
												+ "\"/>"
												+ listProvince.get(i);
									}
								} else {
									province = "<input type=\"checkbox\" name=\"ckProvince"
											+ sub.getMenuId()
											+ "\" value=\""
											+ listProvince.get(i)
											+ "\"/>"
											+ listProvince.get(i);
								}
								provinceStr += province;
							}

						}
						htmlArea = "  &nbsp;&nbsp;地区限制:" + provinceStr;
					}
					addMenuChildRoleVs(sub, parent.appendChild(
							null,
							null,
							sub.getMenuTitle(),
							"javascript:menmenuOperateTypeu_modify("
									+ sub.getMenuId() + "," + sub.getMenuType()
									+ ")", String.valueOf(sub.getMenuId()),
							sub.getMenuTitle(), "", true, true, htmlRW
									+ htmlPerson + htmlArea), roleId);
					tmpMatchMap.put(sub.getMenuId(), sub.getMenuId());
				}
			}
		}

		// 不选
		for (MenuModel sub : list) {
			if (tmpMatchMap.get(sub.getMenuId()) == null) {
				if (sub.getMenuOperateType() == 1) {
					htmlRW = "  &nbsp;&nbsp;读写权限:<input type=\"radio\" name=\"operateType"
							+ sub.getMenuId()
							+ "\"  value=\"1\" checked />不限 <input type=\"radio\" name=\"operateType"
							+ sub.getMenuId()
							+ "\"  value=\"2\"/> 查询+增加+修改  <input type=\"radio\" name=\"operateType"
							+ sub.getMenuId() + "\"  value=\"3\"/>查询";
				} else if (sub.getMenuOperateType() == 2) {
					htmlRW = "  &nbsp;&nbsp;读写权限:<input type=\"radio\" name=\"operateType"
							+ sub.getMenuId()
							+ "\"  value=\"2\" checked   /> 查询+增加+修改 <input type=\"radio\" name=\"operateType"
							+ sub.getMenuId() + "\"  value=\"3\" />查询";
				} else if (sub.getMenuOperateType() == 3) {
					htmlRW = "  &nbsp;&nbsp;读写权限:<input type=\"radio\" name=\"operateType"
							+ sub.getMenuId() + "\"  value=\"3\" />查询";
				}
				if (sub.getMenuPsersonScope() == 1) {
					htmlPerson = "  &nbsp;&nbsp;人员限制:<input type=\"radio\" name=\"personScope"
							+ sub.getMenuId()
							+ "\" value=\"1\" checked />不限   <input type=\"radio\" name=\"personScope"
							+ sub.getMenuId() + "\" value=\"2\"/> 按创建人限制";
				} else if (sub.getMenuPsersonScope() == 2) {
					htmlPerson = "  &nbsp;&nbsp;人员限制:<input type=\"radio\" name=\"personScope"
							+ sub.getMenuId()
							+ "\" value=\"2\" checked /> 按创建人限制";
				}
				if (sub.getMenuAreaScope() == 1) {
					htmlArea = "  &nbsp;&nbsp;地区限制:不限";
				} else if (sub.getMenuAreaScope() == 2) {
					if (null != listProvince) {
						for (int i = 0; i < listProvince.size(); i++) {
							province_no = "<input type=\"checkbox\" name=\"ckProvince"
									+ sub.getMenuId()
									+ "\" value=\""
									+ listProvince.get(i)
									+ "\"/>"
									+ listProvince.get(i);
							provinceStr_no += province_no;
						}
					}
					htmlArea = "  &nbsp;&nbsp;地区限制:" + provinceStr_no;
				}
				addMenuChildRoleVs(sub, parent.appendChild(
						null,
						null,
						sub.getMenuTitle(),
						"javascript:menmenuOperateTypeu_modify("
								+ sub.getMenuId() + "," + sub.getMenuType()
								+ ")", String.valueOf(sub.getMenuId()),
						sub.getMenuTitle(), "", true, false, htmlRW
								+ htmlPerson + htmlArea), roleId);
			}
		}
	}

	/**
	 * 增加角色项
	 * 
	 * @param roleModel
	 *            角色实体
	 */
	@Override
	public void insertRole(RoleModel roleModel) throws Exception {
		this.authRoleDao.authRoleSave(roleModel);// 像角色表中插入记录
	}

	/**
	 * 修改角色项
	 * 
	 * @param roleModel
	 *            角色实体
	 */
	@Override
	public void updateRole(RoleModel roleModel, Long roleId) throws Exception {
		RoleModel roleModelOld = this.getRoleModel(Long.valueOf(roleId));
		if (null != roleModelOld) {
			roleModelOld.setRoleTitle(roleModel.getRoleTitle());
			roleModelOld.setRoleDescription(roleModel.getRoleDescription());
			this.authRoleDao.authRoleUpdate(roleModelOld);
		}

	}

	/**
	 * 根据角色你id查询角色信息发发
	 * 
	 * @param roleId
	 *            角色id
	 * @return
	 */
	@Override
	public RoleModel getRoleModel(Long roleId) throws Exception {
		return this.authRoleDao.getAuthRoleObject(roleId);
	}

	/**
	 * 删除角色信息
	 * 
	 * @param roleId
	 *            角色id
	 * @return
	 */
	@Override
	public void deleteRoleModel(Long roleId) throws Exception {
		this.authRoleDao.authRoleDelete(roleId);
	}

	/**
	 * 增加角色与菜单对应项
	 * 
	 * @param roleVsMenuModel
	 *            角色与菜单对应实体
	 */
	@Override
	public void insertRoleVsMenu(RoleVsMenuModel roleVsMenuModel)
			throws Exception {
		this.authRoleVsMenuDao.roleVsMenuModeSave(roleVsMenuModel);// 像角色表中插入记录
	}

	/**
	 * 根据菜单id喝角色id查询对应的菜单权限
	 * 
	 * @param menuId
	 * @return
	 */
	@Override
	public RoleVsMenuModel getRoleVs(Long roleId, Long menuId) throws Exception {
		List<RoleVsMenuModel> list = this.authRoleVsMenuDao.getRoleVs(roleId,
				menuId);
		if (list.isEmpty() || null == list) {
			return null;
		} else {
			RoleVsMenuModel roleVs = list.get(0);
			if (null != roleVs) {
				return roleVs;
			} else {
				return null;
			}
		}
	}

	/**
	 * 执行更新菜单与角色对应信息
	 * 
	 * @param roleVsMenuModel
	 */
	@Override
	public void updateRoleVsMenu(RoleVsMenuModel roleVsMenuModel)
			throws Exception {
		this.authRoleVsMenuDao.roleVsMenuModelUpdate(roleVsMenuModel);
	}

	/**
	 * 根据角色id查询对应的菜单权限
	 * 
	 * @param roleId
	 * @return
	 */
	@Override
	public List<RoleVsMenuModel> getRoleVsRoleId(Long roleId) throws Exception {
		return this.authRoleVsMenuDao.getRoleVsRoleId(roleId);
	}

	/**
	 * 根据角色id删除所有的权限
	 * 
	 * @param roleId
	 */
	@Override
	public void deleteRoleVsMenu(Long roleId) throws Exception {
		List<RoleVsMenuModel> roleVsMenu = this.getRoleVsRoleId(roleId);
		if (null != roleVsMenu && !roleVsMenu.isEmpty()) {
			for (int i = 0; i < roleVsMenu.size(); i++) {
				RoleVsMenuModel roleVs = roleVsMenu.get(i);
				if (null != roleVs) {
					this.authRoleVsMenuDao
							.roleVsMenuModelDelete(roleVs.getId());
				}
			}
		}
	}

	/**************** 用户管理功能 ****************/

	/**
	 * 查询所有用户列表
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@Override
	public Page listUser(int pageNo, int pageSize) {
		Page page = null;
		try {
			page = this.userDao.listUser(pageNo, pageSize);

			if (null == page) {
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return page;
	}

	/**
	 * 校验登录名
	 */
	@Override
	public int checkLoginName(UserModel userModel, String type)
			throws Exception {
		// 是否唯一
		int isUnique = 0;
		// 用户集合
		List<UserModel> tmpList = null;
		if ("add".equals(type)) {
			tmpList = userDao.validatorLoginName(userModel);
		} else if ("modify".equals(type)) {
			tmpList = userDao.validatorLoginNaByNameAndId(userModel);
		}
		if (tmpList == null || tmpList.size() == 0) {
			isUnique = 1;
		}
		return isUnique;
	}

	/**
	 * 执行增加用户操作
	 * 
	 * @param userModel
	 */
	@Override
	public void insertUser(UserModel userModel) throws Exception {
		userModel.setPassword(MD5Algorithm.digest2Str(userModel.getPassword()));// 将密码加密
		this.userDao.userModelSave(userModel);
	}

	/**
	 * 根据id查询用户对象
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public UserModel getUserModel(Long userId) throws Exception {
		return this.userDao.getUserModel(userId);
	}

	/**
	 * 执行修改用户操作
	 * 
	 * @param userModel
	 */
	@Override
	public void updateUser(UserModel userModel, Long userId) throws Exception {
		String pwd = userModel.getPassword();
		if (pwd == null || "".equals(pwd)) {
			UserModel old = this.getUserModel(userId);
			if (null != old) {
				old.setUserName(userModel.getUserName());
				// old.setLoginName(userModel.getLoginName());
				old.setEmail(userModel.getEmail());
				old.setRoleId(userModel.getRoleId());
			}
			this.userDao.userModelUpdate(old);
		} else {
			UserModel old = this.getUserModel(userId);
			if (null != old) {
				old.setUserName(userModel.getUserName());
				// old.setLoginName(userModel.getLoginName());
				old.setPassword(MD5Algorithm.digest2Str(userModel.getPassword()));// 将密码加密
				old.setEmail(userModel.getEmail());
				old.setRoleId(userModel.getRoleId());
			}
			this.userDao.userModelUpdate(old);
		}
	}

	/**
	 * 执行删除用户操作
	 * 
	 * @param userModel
	 */
	@Override
	public void deleteUser(Long userId) throws Exception {
		this.userDao.userModelDelete(userId);
	}

	/********************************** 登录管理 *****************************************/

	/**
	 * 校验用户是否登录成功
	 */
	@Override
	public boolean check(String loginName, String loginPassword)
			throws Exception {
		// 是否成功
		boolean result = false;
		// 用户集合
		UserModel user = userDao.checkUser(loginName, loginPassword);
		// 存在用户
		if (user != null) {
			result = true;
		}
		return result;
	}

	/**
	 * 获取用户信息
	 */
	@Override
	public UserModel listUser(String loginName) {
		// 用户列表
		List<UserModel> userInfoList = new ArrayList<UserModel>();
		try {
			userInfoList = userDao.listUser(loginName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (userInfoList != null && userInfoList.size() > 0) {
			return userInfoList.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 修改用密码
	 */
	@Override
	public int updateUserPassword(Long userId, String passwordNew)
			throws Exception {
		UserModel userModelOld = this.getUserModel(userId);
		;
		if (null != userModelOld) {
			userModelOld.setPassword(MD5Algorithm.digest2Str(passwordNew));
			this.userDao.userModelUpdate(userModelOld);
			return 1;
		}
		return 2;
	}

	/**
	 * 根据角色id查看用户
	 * 
	 * @param roleId
	 * @return
	 */
	@Override
	public List<UserModel> listUserByRoleId(Long roleId) throws Exception {
		return this.userDao.listUserByRoleId(roleId);
	}
}
