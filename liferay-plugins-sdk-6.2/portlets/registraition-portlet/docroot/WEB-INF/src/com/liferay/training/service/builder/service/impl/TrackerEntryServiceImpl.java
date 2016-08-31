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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.training.eventmonitor.AmfMonitorKey;
import com.liferay.training.service.builder.model.TrackerEntry;
import com.liferay.training.service.builder.permission.TrackerEntryPermission;
import com.liferay.training.service.builder.service.base.TrackerEntryServiceBaseImpl;

/**
 * The implementation of the tracker entry remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.training.service.builder.service.TrackerEntryService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Shanon Mathai
 * @see com.liferay.training.service.builder.service.base.TrackerEntryServiceBaseImpl
 * @see com.liferay.training.service.builder.service.TrackerEntryServiceUtil
 */
public class TrackerEntryServiceImpl extends TrackerEntryServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.liferay.training.service.builder.service.TrackerEntryServiceUtil} to access the tracker entry remote service.
	 */
	public int getTrackerEntriesCount(
		long groupId) throws SystemException, PortalException {
		
		try {
			PermissionChecker permissionChecker = getPermissionChecker();
			TrackerEntryPermission.check(
				permissionChecker, groupId, AmfMonitorKey.VIEW_ALL);
			return trackerEntryLocalService.getTrackerEntriesCount();
		} catch (PrincipalException e) {
			
			// Does not have the VIEW_ALL permission
			
			try {
				return trackerEntryLocalService.countByUserId(getUserId());
			} catch (PrincipalException e1) {
				System.out.println("unable to get userId");
				e1.printStackTrace();
			}
		}
		
		// if this point is reached, the service failed

		throw new PortalException(
			"Unable to get the entry count for groupId " + groupId);
	}
	
	public int countByEventType(
			String eventType, long groupId)
		throws SystemException, PortalException{
		try {
			PermissionChecker permissionChecker = getPermissionChecker();
			TrackerEntryPermission.check(
				permissionChecker, groupId, AmfMonitorKey.VIEW_ALL);
		
			return trackerEntryLocalService.countByEventType(eventType);
		} catch (PrincipalException e) {
			// Does not have the VIEW_ALL permission
			try {
				return trackerEntryLocalService.countByUserIdAndEventType(
					getUserId(), eventType);
			} catch (PrincipalException e1) {
				System.out.println("unable to get userId");
				e1.printStackTrace();
			}
			
		}
		
		// if this point is reached, the service failed

		throw new PortalException(
			"Unable to get the entry count for groupId " 
			+ groupId + " and type " + eventType);
	}

	public List<TrackerEntry> getTrackerEntries(
			int start, int end, long groupId) 
		throws SystemException, PortalException {

		try {
			PermissionChecker permissionChecker = getPermissionChecker();
			TrackerEntryPermission.check(
				permissionChecker, groupId, AmfMonitorKey.VIEW_ALL);
		
		return trackerEntryLocalService.getTrackerEntries(start, end);

		} catch (PrincipalException e) {
			// Does not have the VIEW_ALL permission
			try {
				return trackerEntryLocalService.findByUserId(
					getUserId(), start, end);
			} catch (PrincipalException e1) {
				System.out.println("unable to get userId");
				e1.printStackTrace();
			}

		}
		
		// if this point is reached, the service failed

		throw new PortalException(
			"Unable to retrieve tracker entries for groupId " 
			+ groupId);

	}
	
	public List<TrackerEntry> findByEventType(
			String eventType, int start, int end, long groupId)
		throws SystemException, PortalException{

		try {
			PermissionChecker permissionChecker = getPermissionChecker();
			TrackerEntryPermission.check(
				permissionChecker, groupId, AmfMonitorKey.VIEW_ALL);

			return trackerEntryLocalService.findByEventType(
				eventType, start, end);

		} catch (PrincipalException e) {
			// Does not have the VIEW_ALL permission
			try {
				return trackerEntryLocalService.findByUserIdAndEventType( 
					getUserId(), eventType, start, end);
			} catch (PrincipalException e1) {
				System.out.println("unable to get userId");
				e1.printStackTrace();
			}

		}
		
		// if this point is reached, the service failed

		throw new PortalException(
			"Unable to retrieve tracker entries for groupId " 
			+ groupId + " and type " + eventType);
	}
	
	public List<TrackerEntry> findByEventType(
			String eventType, long groupId)
		throws SystemException, PortalException {
		
		try {
			PermissionChecker permissionChecker = getPermissionChecker();
			TrackerEntryPermission.check(
				permissionChecker, groupId, AmfMonitorKey.VIEW_ALL);
		
			return trackerEntryLocalService.findByEventType(eventType);

		} catch (PrincipalException e) {
			// Does not have the VIEW_ALL permission
			try {
				return trackerEntryLocalService.findByUserIdAndEventType(
					getUserId(), eventType);
			} catch (PrincipalException e1) {
				System.out.println("unable to get userId");
				e1.printStackTrace();
			}

		}
		
		// if this point is reached, the service failed

		throw new PortalException(
			"Unable to retrieve tracker entries for groupId " 
			+ groupId + " and type " + eventType);
	}
}