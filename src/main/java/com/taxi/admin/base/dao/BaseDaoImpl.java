package com.taxi.admin.base.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.taxi.admin.common.Page;

public class BaseDaoImpl<T> implements BaseDAO<T> {
	private SqlMapClient sqlMapClient;

	@Override
	@Deprecated
	public int executeSql(String sqlStr, Object[] paras) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void save(T object) throws SQLException {
		// TODO Auto-generated method stub
		this.sqlMapClient.insert("add" + object.getClass().toString(), object);
	}

	@Override
	public void saveObject(Object object) throws SQLException {
		// TODO Auto-generated method stub
		this.sqlMapClient.insert("add" + object.getClass().toString(), object);
	}

	@Override
	public void delete(Long id) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Object obj) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Long id, Class objectClass) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(T object) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<T> queryAllList() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> queryList(String sqlStr, Object[] paras) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> querysqlList(String sqlStr, Object[] paras)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> queryhqlList(String sqlStr, Object[] paras)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page queryList(String sqlStr, Object[] paras, int pageNo,
			int pageSize) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> queryForList(String sqlStr, Object[] paras, int pageNo,
			int pageSize) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String querySql(String sqlName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCount() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCount(String sqlStr, Object[] paras) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public T getObject(Long id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

}
