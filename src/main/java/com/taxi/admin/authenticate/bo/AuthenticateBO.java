package com.taxi.admin.authenticate.bo;

import java.util.List;


import com.taxi.admin.authenticate.model.MenuModel;
import com.taxi.admin.authenticate.model.RoleModel;
import com.taxi.admin.authenticate.model.RoleVsMenuModel;
import com.taxi.admin.authenticate.model.UserModel;
import com.taxi.admin.base.exception.DAOException;
import com.taxi.admin.common.Page;

public interface AuthenticateBO {
	
	
	 /**
		 * 查看所有的菜单项
		 * @return
		 * @throws DAOException
		 */
		public String listMainMenu(Long roleId)throws Exception;

	/**
	 * 查看所有的菜单项
	 * @return
	 * @throws DAOException
	 */
	public String listMenu()throws Exception;
	
	 /**
     * 根据才带你id查询菜单信息
     * @param menuId
     * 					菜单id
     * @return
     */
    public MenuModel getMenuModel(Long menuId)throws Exception;
	
	/**
	 * 增加菜单项
	 * @param menuModel
	 * 					菜单实体
	 */
	public void insertMenu(MenuModel menuModel, Long parentId)throws Exception;
	
	/**
	 * 修改菜单项
	 * @param menuModel
	 * 					菜单实体
	 */
	public void updateMenu(MenuModel menuModel,Long menuId)throws Exception;
	
	/**
	 * 删除菜单项
	 * @param menuId
	 * 					菜单id
	 */
	public void deleteMenu(Long menuId)throws Exception;
	
	/**
	 * 根据菜单的父节点查看菜单的子节点
	 * @param menuId
	 * @return
	 * @throws DAOException
	 */
	public List<MenuModel> getMenuByParentID(Long menuId)throws Exception;
	
	/**
	 * 查看菜单名称是否存在
	 * @param title
	 * @return
	 */
	public boolean isTitleIn(String title)throws Exception;
	
	/**
	 * 得到角色集合
	 * @return
	 */
	public Page listRole(int pageNo,int pageSize)throws Exception;
	
	/**
	 * 进入添加角色页面加载菜单信息
	 * @return
	 */
	public String addRole()throws Exception;
	
	/**
	 * 增加角色项
	 * @param roleModel
	 * 					角色实体
	 */
	public void insertRole(RoleModel roleModel)throws Exception;
	
	/**
	 * 修改角色项
	 * @param roleModel
	 * 					角色实体
	 */
	public void updateRole(RoleModel roleModel,Long roleId)throws Exception;
	
	   /**
     * 根据角色你id查询角色信息
      * @param roleId
     * 					角色id
     * @return
     */
    public RoleModel getRoleModel(Long roleId)throws Exception;
    
    /**
     * 删除角色信息
     * @param roleId
     * 					角色id
     * @return
     */
    public void deleteRoleModel(Long roleId)throws Exception;
    
    /**
 	 * 增加角色与菜单对应项
 	 * @param roleVsMenuModel
 	 * 					角色与菜单对应实体
 	 */
 	public void insertRoleVsMenu(RoleVsMenuModel roleVsMenuModel)throws Exception;
 	
 	/**
	 * 进入修改角色页面加载菜单信息
	 * @return
	 */
	public String modifyRole(Long roleId)throws Exception;
	
	   /**
		 * 根据菜单id喝角色id查询对应的菜单权限
		 * @param menuId
		 * @return
		 */
		public RoleVsMenuModel getRoleVs(Long roleId,Long menuId)throws Exception;
		
		 /**
	     * 执行更新菜单与角色对应信息
	     * @param roleVsMenuModel
	     */
	    public void updateRoleVsMenu(RoleVsMenuModel roleVsMenuModel)throws Exception;
	    
	    /**
		 * 根据角色id查询对应的菜单权限
		 * @param roleId
		 * @return
		 */
		public List<RoleVsMenuModel> getRoleVsRoleId(Long roleId)throws Exception;
		
		/**
		 * 根据角色id删除所有的权限
		 * @param roleId
		 */
		public void deleteRoleVsMenu(Long roleId)throws Exception;
		
		
		/**
		 * 查询所有用户列表
		 * @param pageNo
		 * @param pageSize
		 * @return
		 */
		public Page listUser(int pageNo,int pageSize);
		
		/**
		 * 校验登录名
		 */
		public int checkLoginName(UserModel userModel, String type)throws Exception;
		
		/**
		 * 执行增加用户操作
		 * @param userModel
		 */
		public void insertUser(UserModel userModel)throws Exception;
		
		/**
		 * 根据id查询用户对象
		 * @param userId
		 * @return
		 */
		public UserModel getUserModel(Long userId)throws Exception;
		
		/**
		 * 执行修改用户操作
		 * @param userModel
		 */
		public void updateUser(UserModel userModel,Long userId)throws Exception;
		/**
		 * 执行删除用户操作
		 * @param userModel
		 */
		public void deleteUser(Long userId)throws Exception;
		
		
		/**
		 * 获取用户信息
		 * 
		 * @param loginName
		 *            用户名
		 * @return 用户信息
		 */
		public UserModel listUser(String loginName);

		/**
		 * 校验用户是否登录成功，0为成功，1为失败
		 * 
		 * @param loginName
		 *            登录名
		 * @param loginPassword
		 *            登录密码
		 * @return 是否成功
		 */
		public boolean check(String loginName, String loginPassword)throws Exception;

		/**
		 * 修改用密码<br>
		 * 
		 * @param usersModel
		 *            用户model
		 * @return int 成功与否，1为修改成功，2为失败
		 */
		public int updateUserPassword(Long userId,String passwordNew)throws Exception;
		
		/**
		 * 根据角色id查看用户
		 * @param roleId
		 * @return
		 */
		public List<UserModel> listUserByRoleId(Long roleId)throws Exception;
}
