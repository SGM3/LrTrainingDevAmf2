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

package com.liferay.training.service.builder.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link UserAddressJoinerService}.
 *
 * @author Shanon Mathai
 * @see UserAddressJoinerService
 * @generated
 */
public class UserAddressJoinerServiceWrapper implements UserAddressJoinerService,
	ServiceWrapper<UserAddressJoinerService> {
	public UserAddressJoinerServiceWrapper(
		UserAddressJoinerService userAddressJoinerService) {
		_userAddressJoinerService = userAddressJoinerService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _userAddressJoinerService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_userAddressJoinerService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _userAddressJoinerService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public java.util.List<com.liferay.portal.model.User> findUsersAtZip(
		java.lang.String zip, int being, int end) {
		return _userAddressJoinerService.findUsersAtZip(zip, being, end);
	}

	@Override
	public int countUsersAtZip(java.lang.String zip) {
		return _userAddressJoinerService.countUsersAtZip(zip);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public UserAddressJoinerService getWrappedUserAddressJoinerService() {
		return _userAddressJoinerService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedUserAddressJoinerService(
		UserAddressJoinerService userAddressJoinerService) {
		_userAddressJoinerService = userAddressJoinerService;
	}

	@Override
	public UserAddressJoinerService getWrappedService() {
		return _userAddressJoinerService;
	}

	@Override
	public void setWrappedService(
		UserAddressJoinerService userAddressJoinerService) {
		_userAddressJoinerService = userAddressJoinerService;
	}

	private UserAddressJoinerService _userAddressJoinerService;
}