package com.taxi.admin.base.dao;

import java.util.List;


public class DaoSupportHibernate<T> extends BaseDaoImpl<T>{

//	protected transient final Log log = LogFactory.getLog(getClass());
//
//	protected Class<T> modelClass;
//
//	@SuppressWarnings("unchecked")
//	public DaoSupportHibernate() {
//		this.modelClass = ReflectionUtils.getSuperClassGenricType(getClass());
//	}
//
//	public DaoSupportHibernate(final Class<T> modelClass) {
//		this.modelClass = modelClass;
//	}
//
//	/**
//	 * 根据条件删除对象的信息
//	 */
//	@Override
//	public void delete(Long id) throws DAOException {
//		try {
//			this.delete(id, modelClass);
//		} catch (Exception e) {
//			log.error(
//					"Error occurred when delete methods of DaoSupportHibernate.",
//					e);
//			throw new DAOException("删除失败", e);
//		}
//	}
//
//	/**
//	 * 删除对象的信息
//	 */
//	@Override
//	public void delete(Object obj) throws DAOException {
//		try {
//			this.getHibernateTemplate().delete(obj);
//		} catch (Exception e) {
//			log.error(
//					"Error occurred when delete methods of DaoSupportHibernate.",
//					e);
//			throw new DAOException("删除失败", e);
//		}
//	}
//
//	/**
//	 * 根据条件删除对象的信息，删除制定参数类的对象
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public void delete(Long id, Class objectClass) throws DAOException {
//
//		try {
//			Object obj = this.getHibernateTemplate().get(objectClass, id);
//			this.getHibernateTemplate().delete(obj);
//		} catch (Exception e) {
//			log.error(
//					"Error occurred when delete methods of DaoSupportHibernate.",
//					e);
//			throw new DAOException("删除失败", e);
//		}
//	}
//
//	/**
//	 * 查询总记录数
//	 */
//	@Override
//	public int getCount() throws DAOException {
//		try {
//			Long count;
//			count = (Long) this.getHibernateTemplate()
//					.find("select count(*) FROM " + modelClass.getName())
//					.iterator().next();
//			return Integer.parseInt(count.toString());
//		} catch (Exception e) {
//			log.error(
//					"Error occurred when getCount methods of DaoSupportHibernate.",
//					e);
//			throw new DAOException("获得总数量失败", e);
//		}
//	}
//
//	/**
//	 * 根据条件查询，返回结果数量
//	 */
//	@Override
//	public int getCount(String sqlStr, Object[] paras) throws DAOException {
//		try {
//			Long count;
//			sqlStr = "select count(*) "
//					+ sqlStr.substring(sqlStr.indexOf("from"));
//
//			Session session = this.getSession();
//			Query query = session.createQuery(sqlStr);
//			int i = 0;
//			if (null != paras && paras.length > 0) {
//				for (Object para : paras) {
//					query.setParameter(i++, para);
//				}
//			}
//			count = Long.parseLong(query.uniqueResult().toString());
//			session.close();
//			session.flush();
//			return Integer.parseInt(count.toString());
//		} catch (Exception e) {
//			log.error(
//					"Error occurred when getCount methods of DaoSupportHibernate.",
//					e);
//			throw new DAOException("通过条件获得总数量失败", e);
//		}
//	}
//
//	/**
//	 * 根据id得到符合条件的该对象，对象不存在时返回空
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public T getObject(Long id) throws DAOException {
//		Object obj = null;
//		try {
//			obj = this.getHibernateTemplate().get(modelClass, id);
//		} catch (Exception e) {
//			log.error(
//					"Error occurred when getObject methods of DaoSupportHibernate.",
//					e);
//			throw new DAOException("查询失败", e);
//		}
//		return (T) obj;// (T) getHibernateTemplate().load(modelClass, id);
//	}
//
//	/**
//	 * 从数据库读取所有对象
//	 */
//	@Override
//	public List<T> queryAllList() throws DAOException {
//
//		try {
//			return (this.getHibernateTemplate().loadAll(this.modelClass));
//		} catch (Exception e) {
//			log.error(
//					"Error occurred when getList methods of DaoSupportHibernate.",
//					e);
//			throw new DAOException("查询失败", e);
//		}
//	}
//
//	/**
//	 * 查询数据的列表
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<T> queryList(final String sqlStr, final Object[] paras)
//			throws DAOException {
//		List<T> objList = getHibernateTemplate().executeFind(
//				new HibernateCallback<List<?>>() {
//
//					public List<?> doInHibernate(Session session)
//							throws DataAccessException {
//						int count = 0;
//						Query query = session.createSQLQuery(sqlStr);
//						if (null != paras && paras.length > 0) {
//							for (Object para : paras) {
//								query.setParameter(count++, para);
//							}
//						}
//						return query.list();
//					}
//				});
//		return objList;
//	}
//
//	/**
//	 * 查询数据的列表
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Object> querysqlList(final String sqlStr, final Object[] paras)
//			throws DAOException {
//		List<Object> objList = getHibernateTemplate().executeFind(
//				new HibernateCallback<List<?>>() {
//
//					public List<Object> doInHibernate(Session session)
//							throws DataAccessException {
//						int count = 0;
//						Query query = session.createSQLQuery(sqlStr);
//						if (null != paras && paras.length > 0) {
//							for (Object para : paras) {
//								query.setParameter(count++, para);
//							}
//						}
//						return query.list();
//					}
//				});
//		return objList;
//	}
//
//	/**
//	 * 查询数据的列表
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<T> queryhqlList(final String sqlStr, final Object[] paras)
//			throws DAOException {
//		List<T> objList = getHibernateTemplate().executeFind(
//				new HibernateCallback<List<?>>() {
//
//					public List<?> doInHibernate(Session session)
//							throws DataAccessException {
//						int count = 0;
//						Query query = session.createQuery(sqlStr);
//						if (null != paras && paras.length > 0) {
//							for (Object para : paras) {
//								query.setParameter(count++, para);
//							}
//						}
//						return query.list();
//					}
//				});
//		return objList;
//	}
//
//	/**
//	 * 分页查询列表
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
	// public Page queryList(final String sqlStr, final Object[] paras,
	// final int pageNo, final int pageSize) throws DAOException {
	// List<T> objList = getHibernateTemplate().executeFind(
	// new HibernateCallback<List<?>>() {
	//
	// public List<?> doInHibernate(Session session)
	// throws DataAccessException {
	// int count = 0;
	//
	// Query query = session.createQuery(sqlStr);
	//
	// if (null != paras && paras.length > 0) {
	// for (Object para : paras) {
	// query.setParameter(count++, para);
	// }
	// }
	// query.setFirstResult((pageNo - 1) * pageSize);
	// query.setMaxResults(pageSize);
	// return query.list();
	// }
	// });
	// int count = this.getCount(sqlStr, paras);
	// Page page = new Page();
	// page.search(count);
	// page.turnToPage(pageNo);
	// page.setList(objList);
	// return page;
	// }
//
//	/**
//	 * 分页查询列表
//	 */
//	@SuppressWarnings("unchecked")
//	public List<T> queryForList(final String sqlStr, final Object[] paras,
//			final int pageNo, final int pageSize) throws DAOException {
//		List<T> objList = getHibernateTemplate().executeFind(
//				new HibernateCallback<List<?>>() {
//
//					public List<?> doInHibernate(Session session)
//							throws DataAccessException {
//						int count = 0;
//
//						Query query = session.createQuery(sqlStr);
//
//						if (null != paras && paras.length > 0) {
//							for (Object para : paras) {
//								query.setParameter(count++, para);
//							}
//						}
//						query.setFirstResult((pageNo - 1) * pageSize);
//						query.setMaxResults(pageSize);
//						return query.list();
//					}
//				});
//		return objList;
//	}
//
//	/**
//	 * 查询配置文件中的sql语句
//	 */
//	@Override
//	public String querySql(final String sqlName) throws DAOException {
//		String result = getHibernateTemplate().execute(
//				new HibernateCallback<String>() {
//
//					public String doInHibernate(Session session)
//							throws DataAccessException {
//						return session.getNamedQuery(sqlName).getQueryString();
//					}
//				});
//
//		return result;
//	}
//
//	/**
//	 * 将对象信息持久化
//	 */
//	@Override
//	public void save(T object) throws DAOException {
//		try {
//			this.getHibernateTemplate().save(object);
//		} catch (Exception e) {
//			log.error(
//					"Error occurred when create methods of DaoSupportHibernate.",
//					e);
//			throw new DAOException("保存数据失败", e);
//		}
//
//	}
//
//	/**
//	 * 将对象信息持久化
//	 */
//	@Override
//	public void saveObject(Object object) throws DAOException {
//		try {
//			this.getHibernateTemplate().save(object);
//		} catch (Exception e) {
//			log.error(
//					"Error occurred when create methods of DaoSupportHibernate.",
//					e);
//			throw new DAOException("保存数据失败", e);
//		}
//
//	}
//
//	/**
//	 * 修改对象信息
//	 */
//	@Override
//	public void update(T object) throws DAOException {
//		try {
//			this.getHibernateTemplate().merge(object);
//		} catch (Exception e) {
//			log.error(
//					"Error occurred when update methods of DaoSupportHibernate.",
//					e);
//			throw new DAOException("更新数据失败", e);
//		}
//
//	}
//
//	/**
//	 * 执行sql命令
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public int executeSql(final String sqlStr, final Object[] paras)
//			throws DAOException {
//		int result = (Integer) getHibernateTemplate().execute(
//				new HibernateCallback() {
//
//					public Integer doInHibernate(Session session)
//							throws DataAccessException {
//						int count = 0;
//						Query query = session.createQuery(sqlStr);
//						if (null != paras && paras.length > 0) {
//							for (Object para : paras) {
//								query.setParameter(count++, para);
//							}
//						}
//						return query.executeUpdate();
//					}
//				});
//		return result;
//	}

}
