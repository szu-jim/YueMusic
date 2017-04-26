package com.yuemusic.dao.intf;

import java.util.List;
import java.util.Map;

public interface IBaseDao<T> {
	/**
	 * 添加
	 * 
	 * @param <T>
	 * @param o
	 */
	public void save(T o);

	/**
	 * 修改
	 * 
	 * @param <T>
	 * @param o
	 */
	public void update(T o);

	/**
	 * 删除
	 * 
	 * @param <T>
	 * @param o
	 */
	public void del(T o);

	/**
	 * 通过hql语句查询
	 * 
	 * @param hql
	 * @return 查询结果
	 */
	public List<T> getByHQL(final String hql);

	/**
	 * 根据条件查询
	 * 
	 * @param propertyValueMap
	 *            键值对
	 * @return 得到的结果
	 */
	public List<T> getByHQL(Map<String, ?> propertyValueMap);

	/**
	 * 根据条件查询 带分页
	 * 
	 * @param propertyValueMap
	 * @param pageNow
	 * @param pageSize
	 * @return
	 */
	public List<T> getByHQL(Map<String, ?> propertyValueMap, Integer pageNow,
			Integer pageSize);
	/**
	 * 根据特殊字符串查询 带分页
	 * 
	 * @param propertyValueMap
	 * @param pageNow
	 * @param pageSize
	 * @param specialhql
	 * @return
	 */
	public List<T> getBySpecialHQL(Map<String, ?> propertyValueMap, Integer pageNow,
			Integer pageSize ,String  specialhql) ;

	/**
	 * 查询pojo表所有记录
	 * 
	 * @param pojo
	 *            表名
	 * @return
	 */
	public List<T> findAll();

	
	/**
	 * 获得某一张表的条目数（已弃用）
	 * 
	 * @author jueying
	 * @param hql
	 * @return
	 */
	public Long getTotalSizeByHQL(final String hql);
	
	/**
	 * 查询总数
	 * @param hql(带count的hql语句，如:SELECT COUNT(*) FROM HyHealthUser)
	 * @return 数量
	 */
	public Long getTotalSizeByHqlWithCount(final String hql,final String s);
	
	

	/**
	 * 根据主键查找对象
	 * 
	 * @param idField
	 *            id字段名
	 * @param id
	 *            主键值
	 * @return
	 */
	public T findById(final String id);

	/**
	 * 根据主键删除对象
	 * 
	 * @param idField
	 *            id字段名
	 * @param id
	 *            主键值
	 */
	public void delById(final String id);

	/**
	 * 得到总数 (见getAllByHQL)
	 * 
	 * @param pojo
	 * @param propertyValueMap
	 * @return
	 */
	public Long getTotalSizeByHQL(Map<String, ?> propertyValueMap);

	/**
	 * 根据某个字段删除(建议确保此字段是唯一的)
	 * 
	 * @param fieldName
	 *            字段名称
	 * @param value
	 *            值
	 */
	public void delByField(final String fieldName, final String value);

	/**
	 * 
	 * @param object
	 * @param pageNow
	 * @param pageSize
	 * @return
	 */
	public List<T> getByHibernateObject(Object object, Integer pageNow,
			Integer pageSize);

	/**
	 * 执行HQL更新语句
	 * 
	 * @param hql
	 * @return
	 */
	public int executeUpdate(final String hql);

	public List<T> getByHQL(Map<String, ?> propertyValueMap, Integer pageNow,
			Integer pageSize, Map<String, Boolean> orders);
	
	public List<T> getByHQL(Map<String, ?> propertyValueMap, Integer pageNow,
			Integer pageSize,Map<String, ?> notNullValue, Map<String, Boolean> orders);
	/**
	 * 根据hql得到一条记录
	 * @param hql
	 * @return null or T
	 */
	public T getOneByHQL(String hql);
	
	/**
	 * HLX
	 * 请谨慎使用
	 * 执行原生态sql语句
	 * @param sql
	 */
	public void executeUpdateBySQL(String sql);
	
	/**
	 * HLX
	 * 请谨慎使用
	 * 执行原生态sql语句
	 * @param sql, strs
	 */
	public void executeUpdateBySQL(String sql, String... strs);
	
	/**
	 * HLX
	 * 请谨慎使用
	 * 执行原生态sql语句
	 * @param sql
	 * @return
	 */
	public List<Object[]> executeSelectBySQL(String sql);
	
	/**
	 * HLX
	 * 请谨慎使用
	 * 执行原生态sql语句
	 * @param sql, strs
	 * @return
	 */
	public List<Object[]> executeSelectBySQL(String sql, String... strs);
}
