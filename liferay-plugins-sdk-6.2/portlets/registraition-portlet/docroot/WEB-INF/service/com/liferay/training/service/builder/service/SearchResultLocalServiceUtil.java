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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for SearchResult. This utility wraps
 * {@link com.liferay.training.service.builder.service.impl.SearchResultLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Shanon Mathai
 * @see SearchResultLocalService
 * @see com.liferay.training.service.builder.service.base.SearchResultLocalServiceBaseImpl
 * @see com.liferay.training.service.builder.service.impl.SearchResultLocalServiceImpl
 * @generated
 */
public class SearchResultLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.training.service.builder.service.impl.SearchResultLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static long countUsersFromZip(int zip)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().countUsersFromZip(zip);
	}

	public static java.util.List<com.liferay.portal.model.User> getUserFromZip(
		int zip) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserFromZip(zip);
	}

	public static java.util.List<com.liferay.portal.model.User> getUserFromZip(
		int zip, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserFromZip(zip, start, end);
	}

	public static void clearService() {
		_service = null;
	}

	public static SearchResultLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					SearchResultLocalService.class.getName());

			if (invokableLocalService instanceof SearchResultLocalService) {
				_service = (SearchResultLocalService)invokableLocalService;
			}
			else {
				_service = new SearchResultLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(SearchResultLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(SearchResultLocalService service) {
	}

	private static SearchResultLocalService _service;
}