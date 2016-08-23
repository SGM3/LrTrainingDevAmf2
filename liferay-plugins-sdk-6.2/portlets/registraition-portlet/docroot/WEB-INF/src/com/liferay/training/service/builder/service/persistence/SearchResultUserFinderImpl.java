package com.liferay.training.service.builder.service.persistence;

import java.util.List;

import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserWrapper;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

public class SearchResultUserFinderImpl extends BasePersistenceImpl<User>{

	@SuppressWarnings("unchecked")
	public List<User> findUserByZipCode(
			Integer zipCode, int begin, int end){
		Session session = null;
		
		try {
			session = openSession();
			
			String sql = CustomSQLUtil.get(_SEARCH_RESULT_QUERY_KEY);
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.setCacheable(false);
			sqlQuery.addEntity("User", UserWrapper.class);
			
			QueryPos queryPos = QueryPos.getInstance(sqlQuery);
			queryPos.add(zipCode.toString());
			return (List<User>) QueryUtil.list(
				sqlQuery, getDialect(), begin, end);
		} catch (Exception e){}
		finally {
			closeSession(session);
		}
		return null;
	}
	
	public void setSearchResultUserFinder(SearchResultUserFinder sFinder){
		_searchResultUserFinder = sFinder;
		
	}
	
	public SearchResultUserFinder getSearchResultUserFinder(){
		return _searchResultUserFinder;
	}

	private SearchResultUserFinder _searchResultUserFinder;
	private static final String _SEARCH_RESULT_QUERY_KEY = 
			SearchResultUserFinderImpl.class.getName() 
			+ ".findUserByZipCode";
}
