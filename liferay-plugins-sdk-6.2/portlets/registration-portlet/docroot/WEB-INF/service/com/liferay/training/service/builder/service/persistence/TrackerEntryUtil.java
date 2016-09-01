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

package com.liferay.training.service.builder.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import com.liferay.training.service.builder.model.TrackerEntry;

import java.util.List;

/**
 * The persistence utility for the tracker entry service. This utility wraps {@link TrackerEntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Shanon Mathai
 * @see TrackerEntryPersistence
 * @see TrackerEntryPersistenceImpl
 * @generated
 */
public class TrackerEntryUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(TrackerEntry trackerEntry) {
		getPersistence().clearCache(trackerEntry);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<TrackerEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TrackerEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TrackerEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static TrackerEntry update(TrackerEntry trackerEntry)
		throws SystemException {
		return getPersistence().update(trackerEntry);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static TrackerEntry update(TrackerEntry trackerEntry,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(trackerEntry, serviceContext);
	}

	/**
	* Returns all the tracker entries where eventType = &#63;.
	*
	* @param eventType the event type
	* @return the matching tracker entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.training.service.builder.model.TrackerEntry> findByEventType(
		java.lang.String eventType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByEventType(eventType);
	}

	/**
	* Returns a range of all the tracker entries where eventType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.training.service.builder.model.impl.TrackerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param eventType the event type
	* @param start the lower bound of the range of tracker entries
	* @param end the upper bound of the range of tracker entries (not inclusive)
	* @return the range of matching tracker entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.training.service.builder.model.TrackerEntry> findByEventType(
		java.lang.String eventType, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByEventType(eventType, start, end);
	}

	/**
	* Returns an ordered range of all the tracker entries where eventType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.training.service.builder.model.impl.TrackerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param eventType the event type
	* @param start the lower bound of the range of tracker entries
	* @param end the upper bound of the range of tracker entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching tracker entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.training.service.builder.model.TrackerEntry> findByEventType(
		java.lang.String eventType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByEventType(eventType, start, end, orderByComparator);
	}

	/**
	* Returns the first tracker entry in the ordered set where eventType = &#63;.
	*
	* @param eventType the event type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tracker entry
	* @throws com.liferay.training.service.builder.NoSuchTrackerEntryException if a matching tracker entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.training.service.builder.model.TrackerEntry findByEventType_First(
		java.lang.String eventType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.training.service.builder.NoSuchTrackerEntryException {
		return getPersistence()
				   .findByEventType_First(eventType, orderByComparator);
	}

	/**
	* Returns the first tracker entry in the ordered set where eventType = &#63;.
	*
	* @param eventType the event type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tracker entry, or <code>null</code> if a matching tracker entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.training.service.builder.model.TrackerEntry fetchByEventType_First(
		java.lang.String eventType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByEventType_First(eventType, orderByComparator);
	}

	/**
	* Returns the last tracker entry in the ordered set where eventType = &#63;.
	*
	* @param eventType the event type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tracker entry
	* @throws com.liferay.training.service.builder.NoSuchTrackerEntryException if a matching tracker entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.training.service.builder.model.TrackerEntry findByEventType_Last(
		java.lang.String eventType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.training.service.builder.NoSuchTrackerEntryException {
		return getPersistence()
				   .findByEventType_Last(eventType, orderByComparator);
	}

	/**
	* Returns the last tracker entry in the ordered set where eventType = &#63;.
	*
	* @param eventType the event type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tracker entry, or <code>null</code> if a matching tracker entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.training.service.builder.model.TrackerEntry fetchByEventType_Last(
		java.lang.String eventType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByEventType_Last(eventType, orderByComparator);
	}

	/**
	* Returns the tracker entries before and after the current tracker entry in the ordered set where eventType = &#63;.
	*
	* @param trackerEntryId the primary key of the current tracker entry
	* @param eventType the event type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next tracker entry
	* @throws com.liferay.training.service.builder.NoSuchTrackerEntryException if a tracker entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.training.service.builder.model.TrackerEntry[] findByEventType_PrevAndNext(
		long trackerEntryId, java.lang.String eventType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.training.service.builder.NoSuchTrackerEntryException {
		return getPersistence()
				   .findByEventType_PrevAndNext(trackerEntryId, eventType,
			orderByComparator);
	}

	/**
	* Removes all the tracker entries where eventType = &#63; from the database.
	*
	* @param eventType the event type
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByEventType(java.lang.String eventType)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByEventType(eventType);
	}

	/**
	* Returns the number of tracker entries where eventType = &#63;.
	*
	* @param eventType the event type
	* @return the number of matching tracker entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByEventType(java.lang.String eventType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByEventType(eventType);
	}

	/**
	* Returns all the tracker entries where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching tracker entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.training.service.builder.model.TrackerEntry> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId);
	}

	/**
	* Returns a range of all the tracker entries where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.training.service.builder.model.impl.TrackerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of tracker entries
	* @param end the upper bound of the range of tracker entries (not inclusive)
	* @return the range of matching tracker entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.training.service.builder.model.TrackerEntry> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId, start, end);
	}

	/**
	* Returns an ordered range of all the tracker entries where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.training.service.builder.model.impl.TrackerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of tracker entries
	* @param end the upper bound of the range of tracker entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching tracker entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.training.service.builder.model.TrackerEntry> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator);
	}

	/**
	* Returns the first tracker entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tracker entry
	* @throws com.liferay.training.service.builder.NoSuchTrackerEntryException if a matching tracker entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.training.service.builder.model.TrackerEntry findByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.training.service.builder.NoSuchTrackerEntryException {
		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the first tracker entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tracker entry, or <code>null</code> if a matching tracker entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.training.service.builder.model.TrackerEntry fetchByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the last tracker entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tracker entry
	* @throws com.liferay.training.service.builder.NoSuchTrackerEntryException if a matching tracker entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.training.service.builder.model.TrackerEntry findByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.training.service.builder.NoSuchTrackerEntryException {
		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the last tracker entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tracker entry, or <code>null</code> if a matching tracker entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.training.service.builder.model.TrackerEntry fetchByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the tracker entries before and after the current tracker entry in the ordered set where userId = &#63;.
	*
	* @param trackerEntryId the primary key of the current tracker entry
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next tracker entry
	* @throws com.liferay.training.service.builder.NoSuchTrackerEntryException if a tracker entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.training.service.builder.model.TrackerEntry[] findByUserId_PrevAndNext(
		long trackerEntryId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.training.service.builder.NoSuchTrackerEntryException {
		return getPersistence()
				   .findByUserId_PrevAndNext(trackerEntryId, userId,
			orderByComparator);
	}

	/**
	* Removes all the tracker entries where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUserId(userId);
	}

	/**
	* Returns the number of tracker entries where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching tracker entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUserId(userId);
	}

	/**
	* Returns all the tracker entries where userId = &#63; and eventType = &#63;.
	*
	* @param userId the user ID
	* @param eventType the event type
	* @return the matching tracker entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.training.service.builder.model.TrackerEntry> findByUserIdAndEventType(
		long userId, java.lang.String eventType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserIdAndEventType(userId, eventType);
	}

	/**
	* Returns a range of all the tracker entries where userId = &#63; and eventType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.training.service.builder.model.impl.TrackerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param eventType the event type
	* @param start the lower bound of the range of tracker entries
	* @param end the upper bound of the range of tracker entries (not inclusive)
	* @return the range of matching tracker entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.training.service.builder.model.TrackerEntry> findByUserIdAndEventType(
		long userId, java.lang.String eventType, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserIdAndEventType(userId, eventType, start, end);
	}

	/**
	* Returns an ordered range of all the tracker entries where userId = &#63; and eventType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.training.service.builder.model.impl.TrackerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param eventType the event type
	* @param start the lower bound of the range of tracker entries
	* @param end the upper bound of the range of tracker entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching tracker entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.training.service.builder.model.TrackerEntry> findByUserIdAndEventType(
		long userId, java.lang.String eventType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserIdAndEventType(userId, eventType, start, end,
			orderByComparator);
	}

	/**
	* Returns the first tracker entry in the ordered set where userId = &#63; and eventType = &#63;.
	*
	* @param userId the user ID
	* @param eventType the event type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tracker entry
	* @throws com.liferay.training.service.builder.NoSuchTrackerEntryException if a matching tracker entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.training.service.builder.model.TrackerEntry findByUserIdAndEventType_First(
		long userId, java.lang.String eventType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.training.service.builder.NoSuchTrackerEntryException {
		return getPersistence()
				   .findByUserIdAndEventType_First(userId, eventType,
			orderByComparator);
	}

	/**
	* Returns the first tracker entry in the ordered set where userId = &#63; and eventType = &#63;.
	*
	* @param userId the user ID
	* @param eventType the event type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tracker entry, or <code>null</code> if a matching tracker entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.training.service.builder.model.TrackerEntry fetchByUserIdAndEventType_First(
		long userId, java.lang.String eventType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUserIdAndEventType_First(userId, eventType,
			orderByComparator);
	}

	/**
	* Returns the last tracker entry in the ordered set where userId = &#63; and eventType = &#63;.
	*
	* @param userId the user ID
	* @param eventType the event type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tracker entry
	* @throws com.liferay.training.service.builder.NoSuchTrackerEntryException if a matching tracker entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.training.service.builder.model.TrackerEntry findByUserIdAndEventType_Last(
		long userId, java.lang.String eventType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.training.service.builder.NoSuchTrackerEntryException {
		return getPersistence()
				   .findByUserIdAndEventType_Last(userId, eventType,
			orderByComparator);
	}

	/**
	* Returns the last tracker entry in the ordered set where userId = &#63; and eventType = &#63;.
	*
	* @param userId the user ID
	* @param eventType the event type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tracker entry, or <code>null</code> if a matching tracker entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.training.service.builder.model.TrackerEntry fetchByUserIdAndEventType_Last(
		long userId, java.lang.String eventType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUserIdAndEventType_Last(userId, eventType,
			orderByComparator);
	}

	/**
	* Returns the tracker entries before and after the current tracker entry in the ordered set where userId = &#63; and eventType = &#63;.
	*
	* @param trackerEntryId the primary key of the current tracker entry
	* @param userId the user ID
	* @param eventType the event type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next tracker entry
	* @throws com.liferay.training.service.builder.NoSuchTrackerEntryException if a tracker entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.training.service.builder.model.TrackerEntry[] findByUserIdAndEventType_PrevAndNext(
		long trackerEntryId, long userId, java.lang.String eventType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.training.service.builder.NoSuchTrackerEntryException {
		return getPersistence()
				   .findByUserIdAndEventType_PrevAndNext(trackerEntryId,
			userId, eventType, orderByComparator);
	}

	/**
	* Removes all the tracker entries where userId = &#63; and eventType = &#63; from the database.
	*
	* @param userId the user ID
	* @param eventType the event type
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUserIdAndEventType(long userId,
		java.lang.String eventType)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUserIdAndEventType(userId, eventType);
	}

	/**
	* Returns the number of tracker entries where userId = &#63; and eventType = &#63;.
	*
	* @param userId the user ID
	* @param eventType the event type
	* @return the number of matching tracker entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUserIdAndEventType(long userId,
		java.lang.String eventType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUserIdAndEventType(userId, eventType);
	}

	/**
	* Caches the tracker entry in the entity cache if it is enabled.
	*
	* @param trackerEntry the tracker entry
	*/
	public static void cacheResult(
		com.liferay.training.service.builder.model.TrackerEntry trackerEntry) {
		getPersistence().cacheResult(trackerEntry);
	}

	/**
	* Caches the tracker entries in the entity cache if it is enabled.
	*
	* @param trackerEntries the tracker entries
	*/
	public static void cacheResult(
		java.util.List<com.liferay.training.service.builder.model.TrackerEntry> trackerEntries) {
		getPersistence().cacheResult(trackerEntries);
	}

	/**
	* Creates a new tracker entry with the primary key. Does not add the tracker entry to the database.
	*
	* @param trackerEntryId the primary key for the new tracker entry
	* @return the new tracker entry
	*/
	public static com.liferay.training.service.builder.model.TrackerEntry create(
		long trackerEntryId) {
		return getPersistence().create(trackerEntryId);
	}

	/**
	* Removes the tracker entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param trackerEntryId the primary key of the tracker entry
	* @return the tracker entry that was removed
	* @throws com.liferay.training.service.builder.NoSuchTrackerEntryException if a tracker entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.training.service.builder.model.TrackerEntry remove(
		long trackerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.training.service.builder.NoSuchTrackerEntryException {
		return getPersistence().remove(trackerEntryId);
	}

	public static com.liferay.training.service.builder.model.TrackerEntry updateImpl(
		com.liferay.training.service.builder.model.TrackerEntry trackerEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(trackerEntry);
	}

	/**
	* Returns the tracker entry with the primary key or throws a {@link com.liferay.training.service.builder.NoSuchTrackerEntryException} if it could not be found.
	*
	* @param trackerEntryId the primary key of the tracker entry
	* @return the tracker entry
	* @throws com.liferay.training.service.builder.NoSuchTrackerEntryException if a tracker entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.training.service.builder.model.TrackerEntry findByPrimaryKey(
		long trackerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.training.service.builder.NoSuchTrackerEntryException {
		return getPersistence().findByPrimaryKey(trackerEntryId);
	}

	/**
	* Returns the tracker entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param trackerEntryId the primary key of the tracker entry
	* @return the tracker entry, or <code>null</code> if a tracker entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.training.service.builder.model.TrackerEntry fetchByPrimaryKey(
		long trackerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(trackerEntryId);
	}

	/**
	* Returns all the tracker entries.
	*
	* @return the tracker entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.training.service.builder.model.TrackerEntry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the tracker entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.training.service.builder.model.impl.TrackerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of tracker entries
	* @param end the upper bound of the range of tracker entries (not inclusive)
	* @return the range of tracker entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.training.service.builder.model.TrackerEntry> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the tracker entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.training.service.builder.model.impl.TrackerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of tracker entries
	* @param end the upper bound of the range of tracker entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of tracker entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.training.service.builder.model.TrackerEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the tracker entries from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of tracker entries.
	*
	* @return the number of tracker entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static TrackerEntryPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (TrackerEntryPersistence)PortletBeanLocatorUtil.locate(com.liferay.training.service.builder.service.ClpSerializer.getServletContextName(),
					TrackerEntryPersistence.class.getName());

			ReferenceRegistry.registerReference(TrackerEntryUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(TrackerEntryPersistence persistence) {
	}

	private static TrackerEntryPersistence _persistence;
}