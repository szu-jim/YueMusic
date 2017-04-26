package com.yuemusic.dao.imp;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.yuemusic.dao.intf.IBaseDao;
import com.yuemusic.tool.GenericsUtils;
import com.yuemusic.tool.ParamentCheckUtils;
import com.yuemusic.tool.Utility;



public class BaseDao<T> extends HibernateDaoSupport implements IBaseDao<T> {
	protected String entityClassName = GenericsUtils.getGenericType(
			this.getClass()).getSimpleName();
	
	@Autowired
	public void setMysessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	
	
	@Override
	public void save(T o) {
		getHibernateTemplate().save(o);
	}

	@Override
	public void update(T o) {
		getHibernateTemplate().update(o);
	}

	@Override
	public void del(T o) {
		getHibernateTemplate().delete(o);
	}

	@Override
	public void delById(final String id) {

		if (ParamentCheckUtils.isEmpty(id)) {
			try {
				throw new Exception("error,params can not be null");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		Session session = null;
		Transaction tx = null;

		String queryString = "delete from " + entityClassName
				+ " p where p.id=?";
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();

			tx = session.beginTransaction();
			session.createQuery(queryString).setString(0, id).executeUpdate();

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen() == true) {
				session.close();
			}
		}
	}

	@Override
	public T findById(final String id) {
		if (ParamentCheckUtils.isEmpty(id)) {
			try {
				throw new Exception("error,params can not be null");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		try {
			String queryString = null;
			queryString = "from  " + entityClassName + " p where p.id='" + id
					+ "'";

			List<T> l = getByHQL(queryString);
			if (l != null && l.size() > 0) {
				return l.get(0);
			} else {
				System.err.println("sss null");
				return null;
			}

		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getByHQL(String hql) {
		return getHibernateTemplate().find(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getOneByHQL(String hql) {
		System.out.println(hql);
		List<T> list = getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public List<T> getByHQL(Map<String, ?> propertyValueMap) {
		return getByHQL(Utility.getResultsWhereHql(entityClassName,
				propertyValueMap));
	}

	@Override
	public List<T> getByHQL(Map<String, ?> propertyValueMap, Integer pageNow,
			Integer pageSize) {
		Session session = null;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			Transaction ts = session.beginTransaction();
			String hql = Utility.getResultsWhereHql(entityClassName,
					propertyValueMap);
			Query query = session.createQuery(hql);
			if (pageNow != null && pageSize != null) {
				int firstResult = (pageNow - 1) * pageSize;
				query.setFirstResult(firstResult);
				query.setMaxResults(pageSize);
			}
			List<T> list = query.list();
			ts.commit();
			if (list.size() > 0) {
				return list;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (session != null && session.isOpen() == true) {
				session.close();
			}
		}
	}

	@Override
	public List<T> getBySpecialHQL(Map<String, ?> propertyValueMap,
			Integer pageNow, Integer pageSize, String specialhql) {
		Session session = null;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			Transaction ts = session.beginTransaction();

			String hql = Utility.getResultsSpecialWhereHql(entityClassName,
					propertyValueMap, specialhql);

			Query query = session.createQuery(hql);
			if (pageNow != null && pageSize != null) {
				int firstResult = (pageNow - 1) * pageSize;
				query.setFirstResult(firstResult);
				query.setMaxResults(pageSize);
			}
			List<T> list = query.list();
			ts.commit();
			if (list.size() > 0) {
				return list;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (session != null && session.isOpen() == true) {
				session.close();
			}
		}
	}

	@Override
	public List<T> getByHQL(Map<String, ?> propertyValueMap, Integer pageNow,
			Integer pageSize, Map<String, Boolean> orders) {
		Session session = null;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			Transaction ts = session.beginTransaction();
			String hql = Utility.getResultsWhereHql(entityClassName,
					propertyValueMap, orders);
			Query query = session.createQuery(hql);

			if (pageNow != null && pageSize != null) {
				int firstResult = (pageNow - 1) * pageSize;
				query.setFirstResult(firstResult);
				query.setMaxResults(pageSize);
			}
			List<T> list = query.list();
			ts.commit();
			if (list.size() > 0) {
				return list;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (session != null && session.isOpen() == true) {
				session.close();
			}
		}
	}

	@Override
	public List<T> getByHQL(Map<String, ?> propertyValueMap, Integer pageNow,
			Integer pageSize, Map<String, ?> notNullValue,
			Map<String, Boolean> orders) {
		Session session = null;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			Transaction ts = session.beginTransaction();
			String hql = Utility.getNotNullResultsWhereHql(entityClassName,
					propertyValueMap, notNullValue, orders);
			Query query = session.createQuery(hql);

			if (pageNow != null && pageSize != null) {
				int firstResult = (pageNow - 1) * pageSize;
				query.setFirstResult(firstResult);
				query.setMaxResults(pageSize);
			}
			List<T> list = query.list();
			ts.commit();
			if (list.size() > 0) {
				return list;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (session != null && session.isOpen() == true) {
				session.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getByHibernateObject(Object object, Integer pageNow,
			Integer pageSize) {
		return getHibernateTemplate().findByExample(object,
				(pageNow - 1) * pageSize, pageSize);
	}

	@Override
	public List<T> findAll() {
		String queryString = "from  " + entityClassName;
		return getByHQL(queryString);
	}

	@Override
	@Deprecated
	public Long getTotalSizeByHQL(String hql) {
		return (long) getHibernateTemplate().find(hql).size();
	}

	@Override
	public Long getTotalSizeByHQL(Map<String, ?> propertyValueMap) {
		return getTotalSizeByHQL(Utility.getCountWhereHql(entityClassName,
				propertyValueMap));
	}

	@Override
	public void delByField(String fieldName, String value) {
		String[] values = new String[] { fieldName, value };
		if (ParamentCheckUtils.isEmpty(values)) {
			try {
				throw new Exception("error,params can not be null");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		Session session = null;
		Transaction tx = null;

		String queryString = "delete from " + entityClassName + " p where p."
				+ fieldName + "=?";
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			tx = session.beginTransaction();
			session.createQuery(queryString).setString(0, value)
					.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen() == true) {
				session.close();
			}
		}
	}

	public int executeUpdate(final String hql) {

		Session session = null;
		Transaction tx = null;
		int result = 0;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			tx = session.beginTransaction();
			result = session.createQuery(hql).executeUpdate();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();

		} finally {
			if (session != null && session.isOpen() == true) {
				session.close();
			}
		}
		return result;
	}

	
	@Override
	public Long getTotalSizeByHqlWithCount(String hql, String s) {
		Session session = null;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			Query query = session.createQuery(hql);
			if (s != null)
				query.setParameter(0, s);
			Long size = (Long) query.uniqueResult();
			session.close();
			System.out.println("size:" + size);
			return size;
		} catch (Exception e) {
			e.printStackTrace();
			return 0l;
		} finally {
			if (session != null && session.isOpen() == true) {
				session.close();
			}
		}

	}

	/**
	 * 未经过测试
	 * HLX
	 * 请谨慎使用
	 * 执行原生态sql语句
	 * @param sql
	 */
	@Override
	public void executeUpdateBySQL(String sql) {
		Session session = null;
		Transaction tx = null;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			tx = session.beginTransaction();
			Query query = session.createSQLQuery(sql);
			query.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			if (session != null && session.isOpen() == true) {
				session.close();
			}
		}
	}

	/**
	 * 还未经过测试
	 * HLX
	 * 请谨慎使用
	 * 执行原生态sql语句
	 * @param sql, strs
	 */
	@Override
	public void executeUpdateBySQL(String sql, String... strs) {
		Session session = null;
		Transaction tx = null;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			tx = session.beginTransaction();
			Query query = session.createSQLQuery(sql);
			int i = 0;
			for(String str : strs) {
				query.setParameter(i, str);
				i++;
			}
			query.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			if (session != null && session.isOpen() == true) {
				session.close();
			}
		}
	}

	/**
	 * 还未经过测试
	 * HLX
	 * 请谨慎使用
	 * 执行原生态sql语句
	 * @param sql, strs
	 */
	@Override
	public List<Object[]> executeSelectBySQL(String sql) {
		Session session = null;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			Query query = session.createSQLQuery(sql);
			@SuppressWarnings("unchecked")
			List<Object[]> objectList = query.list();
			return objectList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (session != null && session.isOpen() == true) {
				session.close();
			}
		}
	}

	/**
	 * 还未经过测试
	 * HLX
	 * 请谨慎使用
	 * 执行原生态sql语句
	 * @param sql, strs
	 */
	@Override
	public List<Object[]> executeSelectBySQL(String sql, String... strs) {
		Session session = null;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			Query query = session.createSQLQuery(sql);
			int i = 0;
			for(String str : strs) {
				query.setParameter(i, str);
				i++;
			}
			@SuppressWarnings("unchecked")
			List<Object[]> objectList = query.list();
			return objectList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (session != null && session.isOpen() == true) {
				session.close();
			}
		}
	}
	
}
