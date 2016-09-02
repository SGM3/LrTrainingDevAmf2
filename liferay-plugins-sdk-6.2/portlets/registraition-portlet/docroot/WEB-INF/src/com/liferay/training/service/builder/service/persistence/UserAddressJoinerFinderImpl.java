package com.liferay.training.service.builder.service.persistence;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

@SuppressWarnings("rawtypes")
public class UserAddressJoinerFinderImpl extends BasePersistenceImpl implements UserAddressJoinerFinder {

	@SuppressWarnings("unchecked" )
	public List<User> findUsersAtZip(String zip, int being, int end){
//		Connection connection = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
		Session session = null;
		try {
//			InfrastructureUtil.getDataSource().
//			session = openSession();
			sessionFactory = (SessionFactory) PortalBeanLocatorUtil.locate("liferaySessionFactory");
			session = sessionFactory.openSession();
			
			
			String sql = CustomSQLUtil.get(_USER_ZIP_QUERY_KEY);
			
//			
			Class userImplClass = _getUserImplClass();
			
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.setCacheable(false);
			sqlQuery.addEntity("User_", userImplClass);
			
			QueryPos queryPos = QueryPos.getInstance(sqlQuery);
			queryPos.add(zip);
			return (List<User>) QueryUtil.list(
				sqlQuery, getDialect(), being, end);

			
			
//			connection = DataAccess.getConnection();
//			ps = connection.prepareStatement(sql);
//			ps.setString(1, zip);
//			
//			rs = ps.executeQuery();
//			
//			List<User> users = new ArrayList<User>();
//			while (rs.next()){
//				users.add(_createUserFromRow(rs));
//			}
//			return users;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			DataAccess.cleanUp(connection, ps, rs);
			sessionFactory.closeSession(session);
//			sessionFactory.closeSession(session);
		}
		return null;
	}

	public int countUsersAtZip(String zip){
		Session session = null;
		try {
//			session = openSession();
			sessionFactory = (SessionFactory) PortalBeanLocatorUtil.locate("liferaySessionFactory");
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
	
	private User _createUserFromRow(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub

		User user = UserLocalServiceUtil.createUser(0);
		
		user.setAgreedToTermsOfUse(rs.getBoolean("agreedToTermsOfUse"));
		user.setComments(rs.getString("comments"));
		user.setCompanyId(rs.getLong("companyId"));
		user.setContactId(rs.getLong("contactId"));
		user.setCreateDate(rs.getDate("createDate"));
		user.setDefaultUser(rs.getBoolean("defaultUser"));
		user.setDigest(rs.getString("digest"));
		user.setEmailAddressVerified(rs.getBoolean("emailAddressVerified"));
		user.setEmailAddress(rs.getString("emailAddress"));
		user.setFacebookId(rs.getLong("facebookId"));
		user.setFailedLoginAttempts(rs.getInt("failedLoginAttempts"));
		user.setFirstName(rs.getString("firstName"));
		user.setGraceLoginCount(rs.getInt("graceLoginCount"));
		user.setGreeting(rs.getString("greeting"));
		user.setJobTitle(rs.getString("jobTitle"));
		user.setLanguageId(rs.getString("languageId"));
		user.setLastFailedLoginDate(rs.getDate("lastFailedLoginDate"));
		user.setLastLoginDate(rs.getDate("lastLoginDate"));
		user.setLastLoginIP(rs.getString("lastLoginIP"));
		user.setLastName(rs.getString("lastName"));
		user.setLdapServerId(rs.getLong("ldapServerId"));
		user.setLockoutDate(rs.getDate("lockoutDate"));
		user.setLockout(rs.getBoolean("lockout"));
		user.setLoginDate(rs.getDate("loginDate"));
		user.setLoginIP(rs.getString("loginIP"));
		user.setMiddleName(rs.getString("middleName"));
		user.setModifiedDate(rs.getDate("modifiedDate"));
		user.setOpenId(rs.getString("openId"));
		user.setPasswordEncrypted(rs.getBoolean("passwordEncrypted"));
		user.setPasswordModifiedDate(rs.getDate("passwordModifiedDate"));
		user.setPasswordReset(rs.getBoolean("passwordReset"));
		user.setPassword(rs.getString("password_"));
		user.setPortraitId(rs.getLong("portraitId"));
		user.setReminderQueryAnswer(rs.getString("reminderQueryAnswer"));
		user.setReminderQueryQuestion(rs.getString("reminderQueryQuestion"));
		user.setScreenName(rs.getString("screenName"));
		user.setStatus(rs.getInt("status"));
		user.setTimeZoneId(rs.getString("timeZoneId"));
		user.setUserId(rs.getLong("userId"));
		user.setUuid(rs.getString("uuid_"));
		
		return user;
	}
	
	
	@BeanReference(name="liferaySessionFactory")
	private static SessionFactory sessionFactory;

	private static final String _USER_ZIP_QUERY_KEY = UserAddressJoinerFinderImpl.class.getName() + ".findUsersAtZip";
	private static final String _COUNT_USER_ZIP_QUERY_KEY = UserAddressJoinerFinderImpl.class.getName() + ".countUsersAtZip";
}
