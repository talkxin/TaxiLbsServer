package com.taxi.admin.service.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.taxi.admin.common.Page;
import com.taxi.admin.service.dao.TaxiUserDao;
import com.taxi.admin.service.module.TTaxiserverTaxiuser;

public class TaxiUserDaoImpl implements TaxiUserDao {
	private SqlMapClient sqlMapClient;

	/**
	 * 获取用户信息（带查询）
	 * 
	 * @param t
	 * @return
	 */
	public Page getUserList(TTaxiserverTaxiuser t, int pageNo, int pageSize)
			throws SQLException {
		List<TTaxiserverTaxiuser> list = sqlMapClient.queryForList(
				"getUserList", t, (pageNo - 1) * pageSize, pageSize);
		Page page = new Page();
		page.search((Long) sqlMapClient.queryForObject("countUserList", t));
		page.turnToPage(pageNo);
		page.setList(list);
		return page;
	}

	/**
	 * 获取单个用户对象
	 * 
	 * @param t
	 * @return
	 */
	public TTaxiserverTaxiuser getUserObject(Long t) throws SQLException {
		return (TTaxiserverTaxiuser) sqlMapClient.queryForObject(
				"getUserObject", t);
	}

	/**
	 * 添加用户
	 * 
	 * @param t
	 */
	public Long isAddUser(TTaxiserverTaxiuser t) throws SQLException {
		if (t.getUserName() == null) {
			t.setUserName(t.getLoginName());
		}
		sqlMapClient.insert("isAddUser", t);
		return t.getUid();
	}

	/**
	 * 修改用户
	 * 
	 * @param t
	 */
	public void isUpdateUser(TTaxiserverTaxiuser t) throws SQLException {
		sqlMapClient.update("isUpdateUser", t);
	}

	/**
	 * 禁止用户登录
	 * 
	 * @param t
	 */
	@Deprecated
	public void isOutUser(TTaxiserverTaxiuser t) throws SQLException {
		// sqlMapClient.update("isOutUser", t);
	}

	/**
	 * 批量添加用户
	 * 
	 * @param li
	 * @throws SQLException
	 */
	public List<Long> isAddsUser(List<TTaxiserverTaxiuser> li)
			throws SQLException {
		List<Long> list = new ArrayList<Long>();
		for (TTaxiserverTaxiuser i : li) {
			if (isNewUser(i) == 0) {
				if (i.getUserName() == null)
					i.setUserName(i.getLoginName());
				list.add(this.isAddUser(i));
			}
		}
		return list;
	}

	/**
	 * 验证用户唯一性
	 * 
	 * @param t
	 * @return
	 * @throws SQLException
	 */
	public int isNewUser(TTaxiserverTaxiuser t) throws SQLException {
		if (sqlMapClient.queryForObject("isNewUser", t) != null) {
			return 1;
		}
		return 0;
	}

	/**
	 * 返回验证对象
	 */
	public HashMap<String, Object> getAlluserMap() throws SQLException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<String> loginName = sqlMapClient.queryForList("getAllLoginName");
		List<Long> phoneNumber = sqlMapClient.queryForList("getAllUserPhone");
		map.put("loginName", loginName);
		map.put("phone", phoneNumber);
		return map;
	}

	public SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

}
