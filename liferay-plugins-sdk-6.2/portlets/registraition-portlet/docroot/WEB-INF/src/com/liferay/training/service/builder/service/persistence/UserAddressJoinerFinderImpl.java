package com.liferay.training.service.builder.service.persistence;

import java.util.List;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

@SuppressWarnings("rawtypes")
public class UserAddressJoinerFinderImpl extends BasePersistenceImpl implements UserAddressJoinerFinder {

	@SuppressWarnings("unchecked" )
	public List<User> findUsersAtZip(String zip, int being, int end){
		Session session = null;
		try {
//			InfrastructureUtil.getDataSource().
//			session = openSession();
			session = sessionFactory.openSession();
			
			String sql = CustomSQLUtil.get(_USER_ZIP_QUERY_KEY);
			
			Class userImplClass = _getUserImplClass();
			
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.setCacheable(false);
			sqlQuery.addEntity("User_", userImplClass);
			
			QueryPos queryPos = QueryPos.getInstance(sqlQuery);
			queryPos.add(zip);
			
			return (List<User>) QueryUtil.list(
				sqlQuery, getDialect(), being, end);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			closeSession(session);
			sessionFactory.closeSession(session);
		}
		return null;
	}
	
	public int countUsersAtZip(String zip){
		Session session = null;
		try {
//			session = openSession();
			session = sessionFactory.openSession();
			
			String sql = CustomSQLUtil.get(_COUNT_USER_ZIP_QUERY_KEY);
			
//			Class userImplClass = _getUserImplClass();
			
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.setCacheable(false);
			sqlQuery.addScalar("COUNT_VALUE", Type.INTEGER);// reference to portal source
			
			QueryPos queryPos = QueryPos.getInstance(sqlQuery);
			queryPos.add(zip);
			
//			List countValue =
//				QueryUtil.list(
//					sqlQuery, getDialect(), QueryUtil.ALL_POS,
//					QueryUtil.ALL_POS);
			Integer countValue = (Integer) sqlQuery.uniqueResult();
			
			return countValue;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			closeSession(session);
			sessionFactory.closeSession(session);
		}
		return -1;
	}

	private Class _getUserImplClass() throws ClassNotFoundException {
		ClassLoader userImplClassLoader = 
				PortalClassLoaderUtil.getClassLoader();
		String userClassImplName = "com.liferay.portal.model.impl.UserImpl";
		return userImplClassLoader.loadClass(userClassImplName);
	}
	
	
	@BeanReference(name="liferaySessionFactory")
	private static SessionFactory sessionFactory;

	private static final String _USER_ZIP_QUERY_KEY = UserAddressJoinerFinderImpl.class.getName() + ".findUsersAtZip";
	private static final String _COUNT_USER_ZIP_QUERY_KEY = UserAddressJoinerFinderImpl.class.getName() + ".countUsersAtZip";
}
