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
 * Provides a wrapper for {@link SearchResultLocalService}.
 *
 * @author Shanon Mathai
 * @see SearchResultLocalService
 * @generated
 */
public class SearchResultLocalServiceWrapper implements SearchResultLocalService,
	ServiceWrapper<SearchResultLocalService> {
	public SearchResultLocalServiceWrapper(
		SearchResultLocalService searchResultLocalService) {
		_searchResultLocalService = searchResultLocalService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _searchResultLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_searchResultLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _searchResultLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public long countUsersFromZip(int zip)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _searchResultLocalService.countUsersFromZip(zip);
	}

	@Override
	public java.util.List<com.liferay.portal.model.User> getUserFromZip(int zip)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _searchResultLocalService.getUserFromZip(zip);
	}

	@Override
	public java.util.List<com.liferay.portal.model.User> getUserFromZip(
		int zip, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _searchResultLocalService.getUserFromZip(zip, start, end);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public SearchResultLocalService getWrappedSearchResultLocalService() {
		return _searchResultLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedSearchResultLocalService(
		SearchResultLocalService searchResultLocalService) {
		_searchResultLocalService = searchResultLocalService;
	}

	@Override
	public SearchResultLocalService getWrappedService() {
		return _searchResultLocalService;
	}

	@Override
	public void setWrappedService(
		SearchResultLocalService searchResultLocalService) {
		_searchResultLocalService = searchResultLocalService;
	}

	private SearchResultLocalService _searchResultLocalService;
}