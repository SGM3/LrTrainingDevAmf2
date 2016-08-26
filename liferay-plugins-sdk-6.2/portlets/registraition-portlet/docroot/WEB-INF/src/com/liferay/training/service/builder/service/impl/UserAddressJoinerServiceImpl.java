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

import java.util.List;

import com.liferay.portal.model.User;
import com.liferay.training.service.builder.service.base.UserAddressJoinerServiceBaseImpl;
import com.liferay.training.service.builder.service.persistence.UserAddressJoinerFinderImpl;
import com.liferay.training.service.builder.service.persistence.UserAddressJoinerFinderUtil;

/**
 * The implementation of the user address joiner remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.training.service.builder.service.UserAddressJoinerService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Shanon Mathai
 * @see com.liferay.training.service.builder.service.base.UserAddressJoinerServiceBaseImpl
 * @see com.liferay.training.service.builder.service.UserAddressJoinerServiceUtil
 */
public class UserAddressJoinerServiceImpl
	extends UserAddressJoinerServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.liferay.training.service.builder.service.UserAddressJoinerServiceUtil} to access the user address joiner remote service.
	 */
	public List<User> findUsersAtZip(String zip, int being, int end){
		return userAddressJoinerFinder.findUsersAtZip(zip, being, end);
	}
	
	public int countUsersAtZip(String zip){
		return userAddressJoinerFinder.countUsersAtZip(zip);
	}
}