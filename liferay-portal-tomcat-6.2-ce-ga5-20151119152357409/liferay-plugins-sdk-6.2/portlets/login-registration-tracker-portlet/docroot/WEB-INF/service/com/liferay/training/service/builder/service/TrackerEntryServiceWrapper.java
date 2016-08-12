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
 * Provides a wrapper for {@link TrackerEntryService}.
 *
 * @author Shanon Mathai
 * @see TrackerEntryService
 * @generated
 */
public class TrackerEntryServiceWrapper implements TrackerEntryService,
	ServiceWrapper<TrackerEntryService> {
	public TrackerEntryServiceWrapper(TrackerEntryService trackerEntryService) {
		_trackerEntryService = trackerEntryService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _trackerEntryService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_trackerEntryService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _trackerEntryService.invokeMethod(name, parameterTypes, arguments);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public TrackerEntryService getWrappedTrackerEntryService() {
		return _trackerEntryService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedTrackerEntryService(
		TrackerEntryService trackerEntryService) {
		_trackerEntryService = trackerEntryService;
	}

	@Override
	public TrackerEntryService getWrappedService() {
		return _trackerEntryService;
	}

	@Override
	public void setWrappedService(TrackerEntryService trackerEntryService) {
		_trackerEntryService = trackerEntryService;
	}

	private TrackerEntryService _trackerEntryService;
}