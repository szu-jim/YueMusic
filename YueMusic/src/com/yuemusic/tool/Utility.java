package com.yuemusic.tool;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 拼接hql公共类
 * 
 * @author jueying
 * 
 */
public class Utility {
	/**
	 * 根据属性和属性值对应关系获取结果集
	 * 
	 * @param propertyValueMap
	 * @return
	 */
	public static String getResultsWhereHql(String pojo,
			Map<String, ?> propertyValueMap) {
		String hql = "FROM " + pojo + " p ";
		return getWhereHql(hql, propertyValueMap);
	}

	/**
	 * 根据属性和属性值对应关系以及额外字符串获取结果集
	 * 
	 * @param propertyValueMap
	 *            ，specialhql
	 * @return
	 */
	public static String getResultsSpecialWhereHql(String pojo,
			Map<String, ?> propertyValueMap, String specialhql) {
		String hql = "FROM " + pojo + " p ";
		return getSpecialWhereHql(hql, propertyValueMap, specialhql);
	}

	/**
	 * 根据属性和属性值对应关系获取结果集
	 * 
	 * @param propertyValueMap
	 * @return
	 */
	public static String getResultsWhereHql(String pojo,
			Map<String, ?> propertyValueMap, Map<String, Boolean> orders) {
		String hql = "FROM " + pojo + " p ";
		return getWhereHql(hql, propertyValueMap, orders);
	}

	/**
	 * 根据属性和属性值对应关系获取结果集（not null）
	 * 
	 * @param propertyValueMap
	 * @return
	 */
	public static String getNotNullResultsWhereHql(String pojo,
			Map<String, ?> propertyValueMap, Map<String, ?> noNullValue,
			Map<String, Boolean> orders) {
		String hql = "FROM " + pojo + " p ";
		return getWhereHql(hql, propertyValueMap,noNullValue,orders);
	}

	/**
	 * 根据属性和属性值对应关系获取数量
	 * 
	 * @param propertyValueMap
	 * @return
	 */
	public static String getCountWhereHql(String pojo,
			Map<String, ?> propertyValueMap) {
		String hql = "SELECT COUNT(*) FROM " + pojo + " p ";
		return getWhereHql(hql, propertyValueMap);
	}

	public static String getWhereHql(String hql, Map<String, ?> propertyValueMap) {
		if (propertyValueMap != null && !propertyValueMap.isEmpty()) {
			hql += " where ";
			Iterator<?> iterator = propertyValueMap.entrySet().iterator();
			while (iterator.hasNext()) {

				Entry entry = (Entry) iterator.next();
				Object value = entry.getValue();
				String property = (String) entry.getKey();

				/* 通常用来查询时间段 */	
				if (value instanceof Object[]) {
					String[] timeStr = (String[]) value;
					if (timeStr[0] != null) {
						hql += " p." + property + " >= '" + timeStr[0]
								+ "' AND ";
					}
					if (timeStr[1] != null) {
						hql += " p." + property + " <= '" + timeStr[1]
								+ "' AND ";
					}
				} else {
//					System.out.println("property:" + property);
//					System.out.println("value:" + value);
					hql += " p." + property + " = '" + value.toString()
							+ "' AND ";
				}
			}
			hql = hql.substring(0, hql.length() - 4); // 去掉最後那個and

		}
		return hql;// 返回拼接好的hql语句
	}

	public static String getSpecialWhereHql(String hql,
			Map<String, ?> propertyValueMap, String specialhql) {
		if (propertyValueMap != null && !propertyValueMap.isEmpty()) {
			hql += " where ";
			Iterator<?> iterator = propertyValueMap.entrySet().iterator();
			while (iterator.hasNext()) {

				Entry entry = (Entry) iterator.next();
				Object value = entry.getValue();
				String property = (String) entry.getKey();

				/* 通常用来查询时间段 */
				if (value instanceof Object[]) {
					String[] timeStr = (String[]) value;
					if (timeStr[0] != null) {
						hql += " p." + property + " >= '" + timeStr[0]
								+ "' AND ";
					}
					if (timeStr[1] != null) {
						hql += " p." + property + " <= '" + timeStr[1]
								+ "' AND ";
					}
				} else {
//					System.out.println("property:" + property);
//					System.out.println("value:" + value);
					hql += " p." + property + " = '" + value.toString()
							+ "' AND ";
				}
			}
			hql = hql.substring(0, hql.length() - 4); // 去掉最後那個and
//			System.out.println("777");
			hql += specialhql; // 后面添加特殊查询字符串，例如order by （某个函数）
//			System.out.println(hql);

		} else {
			hql += specialhql;
//			System.out.println(hql);
		}
		return hql;// 返回拼接好的hql语句
	}

	/**
	 * 排序
	 * 
	 * @param hql
	 * @param propertyValueMap
	 * @param orders
	 *            <field,isASC> true为增序,false为倒序
	 * @return hql
	 */
	@SuppressWarnings("unchecked")
	public static String getWhereHql(String hql,
			Map<String, ?> propertyValueMap, Map<String, Boolean> orders) {
		hql = getWhereHql(hql, propertyValueMap);

		if (orders != null && !orders.isEmpty()) {
			hql += " ORDER BY ";
			Iterator<?> iterator = orders.entrySet().iterator();
			// ORDER BY f1 ASC, f2 DESC
			String rule = null;
			while (iterator.hasNext()) {

				Entry<String, Boolean> entry = (Entry<String, Boolean>) iterator
						.next();
				Boolean value = entry.getValue();
				String property = entry.getKey();
				rule = (value == true ? " ASC" : " DESC");// 判断是增序还是倒序

				hql += "p." + property + rule + ", ";
			}
			hql = hql.substring(0, hql.length() - 2); // ", "
		}
//		System.out.println("order hql:" + hql);
		return hql;

	}

	/**
	 * 排序+notnull
	 * 
	 * @param hql
	 * @param propertyValueMap
	 * @param notNullValue
	 * @param orders
	 *            <field,isASC> true为增序,false为倒序
	 * @return hql
	 */
	public static String getWhereHql(String hql,
			Map<String, ?> propertyValueMap, Map<String, ?> notNullValue,
			Map<String, Boolean> orders) {
		hql = getWhereHql(hql, propertyValueMap);
		if (notNullValue != null && !notNullValue.isEmpty()) {
			hql += " and ( ";
			Iterator<?> iterator = notNullValue.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry entry = (Entry) iterator.next();
				Object value = entry.getValue();
				String property = (String) entry.getKey();
//				System.out.println("notproperty:" + property);
//				System.out.println("notvalue:" + value);
				hql += " p." + property + " IS NOT NULL )";
			}
//			System.out.println("notnull hql:" + hql);
		}
		if (orders != null && !orders.isEmpty()) {
			hql += " ORDER BY ";
			Iterator<?> iterator = orders.entrySet().iterator();
			// ORDER BY f1 ASC, f2 DESC
			String rule = null;
			while (iterator.hasNext()) {

				Entry<String, Boolean> entry = (Entry<String, Boolean>) iterator
						.next();
				Boolean value = entry.getValue();
				String property = entry.getKey();
				rule = (value == true ? " ASC" : " DESC");// 判断是增序还是倒序

				hql += "p." + property + rule + ", ";
			}
			hql = hql.substring(0, hql.length() - 2); // ", "
		}
//		System.out.println("order hql:" + hql);
		return hql;

	}
}
