/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.training.service.builder.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Address;
import com.liferay.portal.model.User;
import com.liferay.training.service.builder.service.base.SearchResultLocalServiceBaseImpl;

/**
 * The implementation of the search result local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.training.service.builder.service.SearchResultLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Shanon Mathai
 * @see com.liferay.training.service.builder.service.base.SearchResultLocalServiceBaseImpl
 * @see com.liferay.training.service.builder.service.SearchResultLocalServiceUtil
 */
public class SearchResultLocalServiceImpl
	extends SearchResultLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.liferay.training.service.builder.service.SearchResultLocalServiceUtil} to access the search result local service.
	 */
	
	public long countUsersFromZip(int zip) throws SystemException{
		DynamicQuery userIdsFromZipQuery = _getUserIdsFromZipQuery(zip);
		
		return addressLocalService.dynamicQueryCount(userIdsFromZipQuery);
	}
	
	public List<User> getUserFromZip(int zip) throws SystemException{
		
		return getUserFromZip(zip, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getUserFromZip(
		int zip, int start, int end) throws SystemException{
		
		DynamicQuery userIdsFromZipQuery = _getUserIdsFromZipQuery(zip);
		
		List<Long> userIds = addressLocalService.dynamicQuery(
			userIdsFromZipQuery, start, end);
		
		if (userIds == null || userIds.isEmpty()){
			return new ArrayList<User>();
		}
		
		DynamicQuery usersFromUserIdsQuery = _getUsersFromUserIdsQuery(userIds);
		
		return userLocalService.dynamicQuery(usersFromUserIdsQuery);
	}
	
	private static DynamicQuery _getUsersFromUserIdsQuery(List<Long> userIds){
		
		// Use of 'in' may have limitations? (query size limits, etc)
		
		return DynamicQueryFactoryUtil
				.forClass(User.class)
				.add(PropertyFactoryUtil.forName("userId").in(userIds));
	}
	
	private static DynamicQuery _getUserIdsFromZipQuery(Integer zipCode) {
		
		String zeroPaddedZip = String.format("%05d", zipCode);
		
		return DynamicQueryFactoryUtil.forClass(Address.class)
			.add(PropertyFactoryUtil.forName("zip")
			.eq(zeroPaddedZip))
			.setProjection(
				ProjectionFactoryUtil.distinct(
					PropertyFactoryUtil.forName("userId")));
		
	}
}