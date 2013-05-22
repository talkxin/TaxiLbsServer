package com.taxi.admin.base.dao;

import java.sql.SQLException;
import java.util.List;

import com.taxi.admin.common.Page;

/**
 * Title. <br>
 * Description.持久层基本的接口定义 所有的DAO接口都必须继承该接口
 * <p>
 * Copyright: Copyright (c) 2011-12-2 下午01:45:11
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: guodong@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
public interface BaseDAO<T> {
	
	/**
	 * 执行sql命令
	 * @param sqlStr
	 *            sql更新语句
	 * @param paras
	 *            sql语句中需要传递的参数
	 * @return
	 * @throws SQLException
	 */
	public int executeSql(final String sqlStr, final Object[] paras) throws SQLException;
	
	/**
	 * 将对象信息持久化
	 * 
	 * @param object
	 * @throws SQLException
	 */
	public void save(T object) throws SQLException;
	
	/**
	 * 将对象信息持久化
	 * 
	 * @param object
	 * @throws SQLException
	 */
	public void saveObject(Object object) throws SQLException;
	
	/**
	 * 根据条件删除对象的信息
	 * 
	 * @param id
	 *            要删除对象的ID
	 * @throws SQLException
	 */
	public void delete(Long id) throws SQLException;
	
	/**
	 * 删除对象的信息
	 */
	public void delete(Object obj)throws SQLException;
	
	/**
	 * 根据条件删除对象的信息，删除制定参数类的对象
	 * 
	 * @param id
	 *            要删除对象的ID
	 * @param objectClass
	 *            删除对象的类
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	public void delete(Long id, Class objectClass) throws SQLException;
	
	/**
	 * 修改对象信息
	 * 
	 * @param object
	 * @throws SQLException
	 */
	public void update(T object) throws SQLException;

	/**
	 * 从数据库读取所有对象
	 * 
	 * @return list 对象的记录集合
	 * @throws SQLException
	 */
	public List<T> queryAllList() throws SQLException;
	
	/**
	 * 查询数据的列表
	 * @param sqlStr sql查询语句
	 * @param paras sql语句中需要传递的参数
	 * @return
	 * @throws SQLException
	 */
	public List<T> queryList(String sqlStr, Object[] paras) throws SQLException;
	
	/**
	 * 查询数据的列表
	 */
	public List<Object> querysqlList(final String sqlStr, final Object[] paras) throws SQLException;
	
	/**
	 * 查询数据的列表
	 * @param sqlStr hql查询语句
	 * @param paras sql语句中需要传递的参数
	 */
	public List<T> queryhqlList(final String sqlStr, final Object[] paras) throws SQLException;
	
	/**
	 * 分页查询列表
	 * 
	 * @param sqlStr
	 *            sql查询语句，只传查询的条件及信息，不设计传递分页信息
	 * @param paras
	 *            sql语句中需要传递的参数
	 * @param page
	 *            查询的页码
	 * @param pageSize
	 *            每页显示的记录数
	 * @return Page 分页对象
	 * @throws SQLException
	 */
	public Page queryList(String sqlStr, Object[] paras, int pageNo, int pageSize) throws SQLException;
	
	/**
	 * 分页查询列表
	 * @param sqlStr
	 * 			sql查询语句，只传查询的条件及信息，不设计传递分页信息
	 * @param paras
	 * 			sql语句中需要传递的参数
	 * @param pageNo
	 * 			查询的页码
	 * @param pageSize
	 * 			每页显示的记录数
	 * @return List集合
	 * @throws SQLException
	 */
	public List<T> queryForList(String sqlStr, Object[] paras, int pageNo, int pageSize) throws SQLException;
	
	/**
	 * 查询配置文件中的sql语句
	 * 
	 * @param sqlName
	 *            配置sql语句的名字
	 * @return 返回字符串
	 * @throws SQLException
	 */
	public String querySql(String sqlName) throws SQLException;
	
	/**
	 * 查询总记录数
	 * 
	 * @return 结果数量
	 * @throws SQLException
	 */
	public int getCount() throws SQLException;
	
	/**
	 * 根据条件查询，返回结果数量
	 * @param sqlStr 查询集合的sql语句
	 * @param paras sql语句中需要传递的参数
	 * @return
	 * @throws SQLException
	 */
	public int getCount(String sqlStr, Object[] paras) throws SQLException;

	/**
	 * 根据id得到符合条件的该对象，对象不存在时返回空
	 * 
	 * @param id
	 *            对象的ID
	 * @return 符合条件的该对象
	 * @throws SQLException
	 */
	public T getObject(Long id) throws SQLException;
	
}