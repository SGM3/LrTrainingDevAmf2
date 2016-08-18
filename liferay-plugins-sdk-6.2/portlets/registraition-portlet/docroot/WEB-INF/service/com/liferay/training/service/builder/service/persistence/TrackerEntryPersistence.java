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

import com.liferay.portal.service.persistence.BasePersistence;

import com.liferay.training.service.builder.model.TrackerEntry;

/**
 * The persistence interface for the tracker entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Shanon Mathai
 * @see TrackerEntryPersistenceImpl
 * @see TrackerEntryUtil
 * @generated
 */
public interface TrackerEntryPersistence extends BasePersistence<TrackerEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TrackerEntryUtil} to access the tracker entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the tracker entries where eventType = &#63;.
	*
	* @param eventType the event type
	* @return the matching tracker entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.training.service.builder.model.TrackerEntry> findByEventType(
		java.lang.String eventType)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.training.service.builder.model.TrackerEntry> findByEventType(
		java.lang.String eventType, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.training.service.builder.model.TrackerEntry> findByEventType(
		java.lang.String eventType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first tracker entry in the ordered set where eventType = &#63;.
	*
	* @param eventType the event type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tracker entry
	* @throws com.liferay.training.service.builder.NoSuchTrackerEntryException if a matching tracker entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.training.service.builder.model.TrackerEntry findByEventType_First(
		java.lang.String eventType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.training.service.builder.NoSuchTrackerEntryException;

	/**
	* Returns the first tracker entry in the ordered set where eventType = &#63;.
	*
	* @param eventType the event type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tracker entry, or <code>null</code> if a matching tracker entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.training.service.builder.model.TrackerEntry fetchByEventType_First(
		java.lang.String eventType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last tracker entry in the ordered set where eventType = &#63;.
	*
	* @param eventType the event type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tracker entry
	* @throws com.liferay.training.service.builder.NoSuchTrackerEntryException if a matching tracker entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.training.service.builder.model.TrackerEntry findByEventType_Last(
		java.lang.String eventType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.training.service.builder.NoSuchTrackerEntryException;

	/**
	* Returns the last tracker entry in the ordered set where eventType = &#63;.
	*
	* @param eventType the event type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tracker entry, or <code>null</code> if a matching tracker entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.training.service.builder.model.TrackerEntry fetchByEventType_Last(
		java.lang.String eventType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.training.service.builder.model.TrackerEntry[] findByEventType_PrevAndNext(
		long trackerEntryId, java.lang.String eventType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.training.service.builder.NoSuchTrackerEntryException;

	/**
	* Removes all the tracker entries where eventType = &#63; from the database.
	*
	* @param eventType the event type
	* @throws SystemException if a system exception occurred
	*/
	public void removeByEventType(java.lang.String eventType)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of tracker entries where eventType = &#63;.
	*
	* @param eventType the event type
	* @return the number of matching tracker entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByEventType(java.lang.String eventType)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the tracker entries where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching tracker entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.training.service.builder.model.TrackerEntry> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.training.service.builder.model.TrackerEntry> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.training.service.builder.model.TrackerEntry> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first tracker entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tracker entry
	* @throws com.liferay.training.service.builder.NoSuchTrackerEntryException if a matching tracker entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.training.service.builder.model.TrackerEntry findByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.training.service.builder.NoSuchTrackerEntryException;

	/**
	* Returns the first tracker entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tracker entry, or <code>null</code> if a matching tracker entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.training.service.builder.model.TrackerEntry fetchByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last tracker entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tracker entry
	* @throws com.liferay.training.service.builder.NoSuchTrackerEntryException if a matching tracker entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.training.service.builder.model.TrackerEntry findByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.training.service.builder.NoSuchTrackerEntryException;

	/**
	* Returns the last tracker entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tracker entry, or <code>null</code> if a matching tracker entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.training.service.builder.model.TrackerEntry fetchByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.training.service.builder.model.TrackerEntry[] findByUserId_PrevAndNext(
		long trackerEntryId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.training.service.builder.NoSuchTrackerEntryException;

	/**
	* Removes all the tracker entries where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of tracker entries where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching tracker entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the tracker entries where userId = &#63; and eventType = &#63;.
	*
	* @param userId the user ID
	* @param eventType the event type
	* @return the matching tracker entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.training.service.builder.model.TrackerEntry> findByUserIdAndEventType(
		long userId, java.lang.String eventType)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.training.service.builder.model.TrackerEntry> findByUserIdAndEventType(
		long userId, java.lang.String eventType, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.training.service.builder.model.TrackerEntry> findByUserIdAndEventType(
		long userId, java.lang.String eventType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.training.service.builder.model.TrackerEntry findByUserIdAndEventType_First(
		long userId, java.lang.String eventType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.training.service.builder.NoSuchTrackerEntryException;

	/**
	* Returns the first tracker entry in the ordered set where userId = &#63; and eventType = &#63;.
	*
	* @param userId the user ID
	* @param eventType the event type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tracker entry, or <code>null</code> if a matching tracker entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.training.service.builder.model.TrackerEntry fetchByUserIdAndEventType_First(
		long userId, java.lang.String eventType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.training.service.builder.model.TrackerEntry findByUserIdAndEventType_Last(
		long userId, java.lang.String eventType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.training.service.builder.NoSuchTrackerEntryException;

	/**
	* Returns the last tracker entry in the ordered set where userId = &#63; and eventType = &#63;.
	*
	* @param userId the user ID
	* @param eventType the event type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tracker entry, or <code>null</code> if a matching tracker entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.training.service.builder.model.TrackerEntry fetchByUserIdAndEventType_Last(
		long userId, java.lang.String eventType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.training.service.builder.model.TrackerEntry[] findByUserIdAndEventType_PrevAndNext(
		long trackerEntryId, long userId, java.lang.String eventType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.training.service.builder.NoSuchTrackerEntryException;

	/**
	* Removes all the tracker entries where userId = &#63; and eventType = &#63; from the database.
	*
	* @param userId the user ID
	* @param eventType the event type
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUserIdAndEventType(long userId,
		java.lang.String eventType)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of tracker entries where userId = &#63; and eventType = &#63;.
	*
	* @param userId the user ID
	* @param eventType the event type
	* @return the number of matching tracker entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByUserIdAndEventType(long userId, java.lang.String eventType)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the tracker entry in the entity cache if it is enabled.
	*
	* @param trackerEntry the tracker entry
	*/
	public void cacheResult(
		com.liferay.training.service.builder.model.TrackerEntry trackerEntry);

	/**
	* Caches the tracker entries in the entity cache if it is enabled.
	*
	* @param trackerEntries the tracker entries
	*/
	public void cacheResult(
		java.util.List<com.liferay.training.service.builder.model.TrackerEntry> trackerEntries);

	/**
	* Creates a new tracker entry with the primary key. Does not add the tracker entry to the database.
	*
	* @param trackerEntryId the primary key for the new tracker entry
	* @return the new tracker entry
	*/
	public com.liferay.training.service.builder.model.TrackerEntry create(
		long trackerEntryId);

	/**
	* Removes the tracker entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param trackerEntryId the primary key of the tracker entry
	* @return the tracker entry that was removed
	* @throws com.liferay.training.service.builder.NoSuchTrackerEntryException if a tracker entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.training.service.builder.model.TrackerEntry remove(
		long trackerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.training.service.builder.NoSuchTrackerEntryException;

	public com.liferay.training.service.builder.model.TrackerEntry updateImpl(
		com.liferay.training.service.builder.model.TrackerEntry trackerEntry)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the tracker entry with the primary key or throws a {@link com.liferay.training.service.builder.NoSuchTrackerEntryException} if it could not be found.
	*
	* @param trackerEntryId the primary key of the tracker entry
	* @return the tracker entry
	* @throws com.liferay.training.service.builder.NoSuchTrackerEntryException if a tracker entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.training.service.builder.model.TrackerEntry findByPrimaryKey(
		long trackerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.training.service.builder.NoSuchTrackerEntryException;

	/**
	* Returns the tracker entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param trackerEntryId the primary key of the tracker entry
	* @return the tracker entry, or <code>null</code> if a tracker entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.training.service.builder.model.TrackerEntry fetchByPrimaryKey(
		long trackerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the tracker entries.
	*
	* @return the tracker entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.training.service.builder.model.TrackerEntry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.training.service.builder.model.TrackerEntry> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.training.service.builder.model.TrackerEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the tracker entries from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of tracker entries.
	*
	* @return the number of tracker entries
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}