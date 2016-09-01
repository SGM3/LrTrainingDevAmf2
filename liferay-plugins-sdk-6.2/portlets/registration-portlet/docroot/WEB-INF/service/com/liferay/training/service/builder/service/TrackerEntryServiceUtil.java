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
import com.liferay.portal.service.InvokableService;

/**
 * Provides the remote service utility for TrackerEntry. This utility wraps
 * {@link com.liferay.training.service.builder.service.impl.TrackerEntryServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Shanon Mathai
 * @see TrackerEntryService
 * @see com.liferay.training.service.builder.service.base.TrackerEntryServiceBaseImpl
 * @see com.liferay.training.service.builder.service.impl.TrackerEntryServiceImpl
 * @generated
 */
public class TrackerEntryServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.training.service.builder.service.impl.TrackerEntryServiceImpl} and rerun ServiceBuilder to regenerate this class.
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

	public static int getTrackerEntriesCount(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getTrackerEntriesCount(groupId);
	}

	public static int countByEventType(java.lang.String eventType, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().countByEventType(eventType, groupId);
	}

	public static java.util.List<com.liferay.training.service.builder.model.TrackerEntry> getTrackerEntries(
		int start, int end, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getTrackerEntries(start, end, groupId);
	}

	public static java.util.List<com.liferay.training.service.builder.model.TrackerEntry> findByEventType(
		java.lang.String eventType, int start, int end, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().findByEventType(eventType, start, end, groupId);
	}

	public static java.util.List<com.liferay.training.service.builder.model.TrackerEntry> findByEventType(
		java.lang.String eventType, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().findByEventType(eventType, groupId);
	}

	public static void clearService() {
		_service = null;
	}

	public static TrackerEntryService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					TrackerEntryService.class.getName());

			if (invokableService instanceof TrackerEntryService) {
				_service = (TrackerEntryService)invokableService;
			}
			else {
				_service = new TrackerEntryServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(TrackerEntryServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(TrackerEntryService service) {
	}

	private static TrackerEntryService _service;
}