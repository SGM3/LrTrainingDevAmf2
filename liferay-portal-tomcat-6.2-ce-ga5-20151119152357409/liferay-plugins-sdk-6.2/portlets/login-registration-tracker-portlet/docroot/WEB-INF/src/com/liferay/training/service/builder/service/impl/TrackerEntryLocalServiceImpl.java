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

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.training.service.builder.model.TrackerEntry;
import com.liferay.training.service.builder.service.base.TrackerEntryLocalServiceBaseImpl;

/**
 * The implementation of the tracker entry local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.training.service.builder.service.TrackerEntryLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Shanon Mathai
 * @see com.liferay.training.service.builder.service.base.TrackerEntryLocalServiceBaseImpl
 * @see com.liferay.training.service.builder.service.TrackerEntryLocalServiceUtil
 */
public class TrackerEntryLocalServiceImpl
	extends TrackerEntryLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.liferay.training.service.builder.service.TrackerEntryLocalServiceUtil} to access the tracker entry local service.
	 */
	
	public List<TrackerEntry> findByEventType(String eventType, int start, int end) throws SystemException{
		return trackerEntryPersistence.findByEventType(eventType, start, end);
	}
	

	public List<TrackerEntry> findByEventType(String eventType) throws SystemException {
		return trackerEntryPersistence.findByEventType(eventType);
	}
	
	public int countByEventType(String eventType) throws SystemException{
		return trackerEntryPersistence.countByEventType(eventType);
	}
	
	public List<TrackerEntry> findByUserId(long userId, int start, int end) throws SystemException{
		return trackerEntryPersistence.findByUserId(userId, start, end);
	}
	

	public List<TrackerEntry> findByUserId(long userId) throws SystemException {
		return trackerEntryPersistence.findByUserId(userId);
	}
	
	public int countByUserId(long userId) throws SystemException{
		return trackerEntryPersistence.countByUserId(userId);
	}
	
}