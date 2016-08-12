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

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.training.service.builder.NoSuchTrackerEntryException;
import com.liferay.training.service.builder.model.TrackerEntry;
import com.liferay.training.service.builder.model.impl.TrackerEntryImpl;
import com.liferay.training.service.builder.model.impl.TrackerEntryModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the tracker entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Shanon Mathai
 * @see TrackerEntryPersistence
 * @see TrackerEntryUtil
 * @generated
 */
public class TrackerEntryPersistenceImpl extends BasePersistenceImpl<TrackerEntry>
	implements TrackerEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TrackerEntryUtil} to access the tracker entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = TrackerEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(TrackerEntryModelImpl.ENTITY_CACHE_ENABLED,
			TrackerEntryModelImpl.FINDER_CACHE_ENABLED, TrackerEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(TrackerEntryModelImpl.ENTITY_CACHE_ENABLED,
			TrackerEntryModelImpl.FINDER_CACHE_ENABLED, TrackerEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TrackerEntryModelImpl.ENTITY_CACHE_ENABLED,
			TrackerEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_EVENTTYPE =
		new FinderPath(TrackerEntryModelImpl.ENTITY_CACHE_ENABLED,
			TrackerEntryModelImpl.FINDER_CACHE_ENABLED, TrackerEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByEventType",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EVENTTYPE =
		new FinderPath(TrackerEntryModelImpl.ENTITY_CACHE_ENABLED,
			TrackerEntryModelImpl.FINDER_CACHE_ENABLED, TrackerEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByEventType",
			new String[] { String.class.getName() },
			TrackerEntryModelImpl.EVENTTYPE_COLUMN_BITMASK |
			TrackerEntryModelImpl.EVENTDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_EVENTTYPE = new FinderPath(TrackerEntryModelImpl.ENTITY_CACHE_ENABLED,
			TrackerEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByEventType",
			new String[] { String.class.getName() });

	/**
	 * Returns all the tracker entries where eventType = &#63;.
	 *
	 * @param eventType the event type
	 * @return the matching tracker entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<TrackerEntry> findByEventType(String eventType)
		throws SystemException {
		return findByEventType(eventType, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
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
	@Override
	public List<TrackerEntry> findByEventType(String eventType, int start,
		int end) throws SystemException {
		return findByEventType(eventType, start, end, null);
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
	@Override
	public List<TrackerEntry> findByEventType(String eventType, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EVENTTYPE;
			finderArgs = new Object[] { eventType };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_EVENTTYPE;
			finderArgs = new Object[] { eventType, start, end, orderByComparator };
		}

		List<TrackerEntry> list = (List<TrackerEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TrackerEntry trackerEntry : list) {
				if (!Validator.equals(eventType, trackerEntry.getEventType())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_TRACKERENTRY_WHERE);

			boolean bindEventType = false;

			if (eventType == null) {
				query.append(_FINDER_COLUMN_EVENTTYPE_EVENTTYPE_1);
			}
			else if (eventType.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_EVENTTYPE_EVENTTYPE_3);
			}
			else {
				bindEventType = true;

				query.append(_FINDER_COLUMN_EVENTTYPE_EVENTTYPE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TrackerEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindEventType) {
					qPos.add(eventType);
				}

				if (!pagination) {
					list = (List<TrackerEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<TrackerEntry>(list);
				}
				else {
					list = (List<TrackerEntry>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public TrackerEntry findByEventType_First(String eventType,
		OrderByComparator orderByComparator)
		throws NoSuchTrackerEntryException, SystemException {
		TrackerEntry trackerEntry = fetchByEventType_First(eventType,
				orderByComparator);

		if (trackerEntry != null) {
			return trackerEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("eventType=");
		msg.append(eventType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTrackerEntryException(msg.toString());
	}

	/**
	 * Returns the first tracker entry in the ordered set where eventType = &#63;.
	 *
	 * @param eventType the event type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tracker entry, or <code>null</code> if a matching tracker entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TrackerEntry fetchByEventType_First(String eventType,
		OrderByComparator orderByComparator) throws SystemException {
		List<TrackerEntry> list = findByEventType(eventType, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public TrackerEntry findByEventType_Last(String eventType,
		OrderByComparator orderByComparator)
		throws NoSuchTrackerEntryException, SystemException {
		TrackerEntry trackerEntry = fetchByEventType_Last(eventType,
				orderByComparator);

		if (trackerEntry != null) {
			return trackerEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("eventType=");
		msg.append(eventType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTrackerEntryException(msg.toString());
	}

	/**
	 * Returns the last tracker entry in the ordered set where eventType = &#63;.
	 *
	 * @param eventType the event type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tracker entry, or <code>null</code> if a matching tracker entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TrackerEntry fetchByEventType_Last(String eventType,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByEventType(eventType);

		if (count == 0) {
			return null;
		}

		List<TrackerEntry> list = findByEventType(eventType, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public TrackerEntry[] findByEventType_PrevAndNext(long trackerEntryId,
		String eventType, OrderByComparator orderByComparator)
		throws NoSuchTrackerEntryException, SystemException {
		TrackerEntry trackerEntry = findByPrimaryKey(trackerEntryId);

		Session session = null;

		try {
			session = openSession();

			TrackerEntry[] array = new TrackerEntryImpl[3];

			array[0] = getByEventType_PrevAndNext(session, trackerEntry,
					eventType, orderByComparator, true);

			array[1] = trackerEntry;

			array[2] = getByEventType_PrevAndNext(session, trackerEntry,
					eventType, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TrackerEntry getByEventType_PrevAndNext(Session session,
		TrackerEntry trackerEntry, String eventType,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TRACKERENTRY_WHERE);

		boolean bindEventType = false;

		if (eventType == null) {
			query.append(_FINDER_COLUMN_EVENTTYPE_EVENTTYPE_1);
		}
		else if (eventType.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_EVENTTYPE_EVENTTYPE_3);
		}
		else {
			bindEventType = true;

			query.append(_FINDER_COLUMN_EVENTTYPE_EVENTTYPE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(TrackerEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindEventType) {
			qPos.add(eventType);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(trackerEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TrackerEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the tracker entries where eventType = &#63; from the database.
	 *
	 * @param eventType the event type
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByEventType(String eventType) throws SystemException {
		for (TrackerEntry trackerEntry : findByEventType(eventType,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(trackerEntry);
		}
	}

	/**
	 * Returns the number of tracker entries where eventType = &#63;.
	 *
	 * @param eventType the event type
	 * @return the number of matching tracker entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByEventType(String eventType) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_EVENTTYPE;

		Object[] finderArgs = new Object[] { eventType };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TRACKERENTRY_WHERE);

			boolean bindEventType = false;

			if (eventType == null) {
				query.append(_FINDER_COLUMN_EVENTTYPE_EVENTTYPE_1);
			}
			else if (eventType.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_EVENTTYPE_EVENTTYPE_3);
			}
			else {
				bindEventType = true;

				query.append(_FINDER_COLUMN_EVENTTYPE_EVENTTYPE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindEventType) {
					qPos.add(eventType);
				}

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_EVENTTYPE_EVENTTYPE_1 = "trackerEntry.eventType IS NULL";
	private static final String _FINDER_COLUMN_EVENTTYPE_EVENTTYPE_2 = "trackerEntry.eventType = ?";
	private static final String _FINDER_COLUMN_EVENTTYPE_EVENTTYPE_3 = "(trackerEntry.eventType IS NULL OR trackerEntry.eventType = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(TrackerEntryModelImpl.ENTITY_CACHE_ENABLED,
			TrackerEntryModelImpl.FINDER_CACHE_ENABLED, TrackerEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(TrackerEntryModelImpl.ENTITY_CACHE_ENABLED,
			TrackerEntryModelImpl.FINDER_CACHE_ENABLED, TrackerEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] { Long.class.getName() },
			TrackerEntryModelImpl.USERID_COLUMN_BITMASK |
			TrackerEntryModelImpl.EVENTDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(TrackerEntryModelImpl.ENTITY_CACHE_ENABLED,
			TrackerEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the tracker entries where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching tracker entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<TrackerEntry> findByUserId(long userId)
		throws SystemException {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<TrackerEntry> findByUserId(long userId, int start, int end)
		throws SystemException {
		return findByUserId(userId, start, end, null);
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
	@Override
	public List<TrackerEntry> findByUserId(long userId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId, start, end, orderByComparator };
		}

		List<TrackerEntry> list = (List<TrackerEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TrackerEntry trackerEntry : list) {
				if ((userId != trackerEntry.getUserId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_TRACKERENTRY_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TrackerEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (!pagination) {
					list = (List<TrackerEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<TrackerEntry>(list);
				}
				else {
					list = (List<TrackerEntry>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public TrackerEntry findByUserId_First(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchTrackerEntryException, SystemException {
		TrackerEntry trackerEntry = fetchByUserId_First(userId,
				orderByComparator);

		if (trackerEntry != null) {
			return trackerEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTrackerEntryException(msg.toString());
	}

	/**
	 * Returns the first tracker entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tracker entry, or <code>null</code> if a matching tracker entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TrackerEntry fetchByUserId_First(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		List<TrackerEntry> list = findByUserId(userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public TrackerEntry findByUserId_Last(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchTrackerEntryException, SystemException {
		TrackerEntry trackerEntry = fetchByUserId_Last(userId, orderByComparator);

		if (trackerEntry != null) {
			return trackerEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTrackerEntryException(msg.toString());
	}

	/**
	 * Returns the last tracker entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tracker entry, or <code>null</code> if a matching tracker entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TrackerEntry fetchByUserId_Last(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<TrackerEntry> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public TrackerEntry[] findByUserId_PrevAndNext(long trackerEntryId,
		long userId, OrderByComparator orderByComparator)
		throws NoSuchTrackerEntryException, SystemException {
		TrackerEntry trackerEntry = findByPrimaryKey(trackerEntryId);

		Session session = null;

		try {
			session = openSession();

			TrackerEntry[] array = new TrackerEntryImpl[3];

			array[0] = getByUserId_PrevAndNext(session, trackerEntry, userId,
					orderByComparator, true);

			array[1] = trackerEntry;

			array[2] = getByUserId_PrevAndNext(session, trackerEntry, userId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TrackerEntry getByUserId_PrevAndNext(Session session,
		TrackerEntry trackerEntry, long userId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TRACKERENTRY_WHERE);

		query.append(_FINDER_COLUMN_USERID_USERID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(TrackerEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(trackerEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TrackerEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the tracker entries where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByUserId(long userId) throws SystemException {
		for (TrackerEntry trackerEntry : findByUserId(userId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(trackerEntry);
		}
	}

	/**
	 * Returns the number of tracker entries where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching tracker entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByUserId(long userId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_USERID;

		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TRACKERENTRY_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_USERID_USERID_2 = "trackerEntry.userId = ?";

	public TrackerEntryPersistenceImpl() {
		setModelClass(TrackerEntry.class);
	}

	/**
	 * Caches the tracker entry in the entity cache if it is enabled.
	 *
	 * @param trackerEntry the tracker entry
	 */
	@Override
	public void cacheResult(TrackerEntry trackerEntry) {
		EntityCacheUtil.putResult(TrackerEntryModelImpl.ENTITY_CACHE_ENABLED,
			TrackerEntryImpl.class, trackerEntry.getPrimaryKey(), trackerEntry);

		trackerEntry.resetOriginalValues();
	}

	/**
	 * Caches the tracker entries in the entity cache if it is enabled.
	 *
	 * @param trackerEntries the tracker entries
	 */
	@Override
	public void cacheResult(List<TrackerEntry> trackerEntries) {
		for (TrackerEntry trackerEntry : trackerEntries) {
			if (EntityCacheUtil.getResult(
						TrackerEntryModelImpl.ENTITY_CACHE_ENABLED,
						TrackerEntryImpl.class, trackerEntry.getPrimaryKey()) == null) {
				cacheResult(trackerEntry);
			}
			else {
				trackerEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all tracker entries.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(TrackerEntryImpl.class.getName());
		}

		EntityCacheUtil.clearCache(TrackerEntryImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the tracker entry.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TrackerEntry trackerEntry) {
		EntityCacheUtil.removeResult(TrackerEntryModelImpl.ENTITY_CACHE_ENABLED,
			TrackerEntryImpl.class, trackerEntry.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<TrackerEntry> trackerEntries) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (TrackerEntry trackerEntry : trackerEntries) {
			EntityCacheUtil.removeResult(TrackerEntryModelImpl.ENTITY_CACHE_ENABLED,
				TrackerEntryImpl.class, trackerEntry.getPrimaryKey());
		}
	}

	/**
	 * Creates a new tracker entry with the primary key. Does not add the tracker entry to the database.
	 *
	 * @param trackerEntryId the primary key for the new tracker entry
	 * @return the new tracker entry
	 */
	@Override
	public TrackerEntry create(long trackerEntryId) {
		TrackerEntry trackerEntry = new TrackerEntryImpl();

		trackerEntry.setNew(true);
		trackerEntry.setPrimaryKey(trackerEntryId);

		return trackerEntry;
	}

	/**
	 * Removes the tracker entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param trackerEntryId the primary key of the tracker entry
	 * @return the tracker entry that was removed
	 * @throws com.liferay.training.service.builder.NoSuchTrackerEntryException if a tracker entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TrackerEntry remove(long trackerEntryId)
		throws NoSuchTrackerEntryException, SystemException {
		return remove((Serializable)trackerEntryId);
	}

	/**
	 * Removes the tracker entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the tracker entry
	 * @return the tracker entry that was removed
	 * @throws com.liferay.training.service.builder.NoSuchTrackerEntryException if a tracker entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TrackerEntry remove(Serializable primaryKey)
		throws NoSuchTrackerEntryException, SystemException {
		Session session = null;

		try {
			session = openSession();

			TrackerEntry trackerEntry = (TrackerEntry)session.get(TrackerEntryImpl.class,
					primaryKey);

			if (trackerEntry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTrackerEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(trackerEntry);
		}
		catch (NoSuchTrackerEntryException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected TrackerEntry removeImpl(TrackerEntry trackerEntry)
		throws SystemException {
		trackerEntry = toUnwrappedModel(trackerEntry);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(trackerEntry)) {
				trackerEntry = (TrackerEntry)session.get(TrackerEntryImpl.class,
						trackerEntry.getPrimaryKeyObj());
			}

			if (trackerEntry != null) {
				session.delete(trackerEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (trackerEntry != null) {
			clearCache(trackerEntry);
		}

		return trackerEntry;
	}

	@Override
	public TrackerEntry updateImpl(
		com.liferay.training.service.builder.model.TrackerEntry trackerEntry)
		throws SystemException {
		trackerEntry = toUnwrappedModel(trackerEntry);

		boolean isNew = trackerEntry.isNew();

		TrackerEntryModelImpl trackerEntryModelImpl = (TrackerEntryModelImpl)trackerEntry;

		Session session = null;

		try {
			session = openSession();

			if (trackerEntry.isNew()) {
				session.save(trackerEntry);

				trackerEntry.setNew(false);
			}
			else {
				session.merge(trackerEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !TrackerEntryModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((trackerEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EVENTTYPE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						trackerEntryModelImpl.getOriginalEventType()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_EVENTTYPE,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EVENTTYPE,
					args);

				args = new Object[] { trackerEntryModelImpl.getEventType() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_EVENTTYPE,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EVENTTYPE,
					args);
			}

			if ((trackerEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						trackerEntryModelImpl.getOriginalUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] { trackerEntryModelImpl.getUserId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}
		}

		EntityCacheUtil.putResult(TrackerEntryModelImpl.ENTITY_CACHE_ENABLED,
			TrackerEntryImpl.class, trackerEntry.getPrimaryKey(), trackerEntry);

		return trackerEntry;
	}

	protected TrackerEntry toUnwrappedModel(TrackerEntry trackerEntry) {
		if (trackerEntry instanceof TrackerEntryImpl) {
			return trackerEntry;
		}

		TrackerEntryImpl trackerEntryImpl = new TrackerEntryImpl();

		trackerEntryImpl.setNew(trackerEntry.isNew());
		trackerEntryImpl.setPrimaryKey(trackerEntry.getPrimaryKey());

		trackerEntryImpl.setTrackerEntryId(trackerEntry.getTrackerEntryId());
		trackerEntryImpl.setGroupId(trackerEntry.getGroupId());
		trackerEntryImpl.setCompanyId(trackerEntry.getCompanyId());
		trackerEntryImpl.setUserId(trackerEntry.getUserId());
		trackerEntryImpl.setUserName(trackerEntry.getUserName());
		trackerEntryImpl.setEventDate(trackerEntry.getEventDate());
		trackerEntryImpl.setEventType(trackerEntry.getEventType());
		trackerEntryImpl.setIpAddress(trackerEntry.getIpAddress());

		return trackerEntryImpl;
	}

	/**
	 * Returns the tracker entry with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the tracker entry
	 * @return the tracker entry
	 * @throws com.liferay.training.service.builder.NoSuchTrackerEntryException if a tracker entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TrackerEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTrackerEntryException, SystemException {
		TrackerEntry trackerEntry = fetchByPrimaryKey(primaryKey);

		if (trackerEntry == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTrackerEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return trackerEntry;
	}

	/**
	 * Returns the tracker entry with the primary key or throws a {@link com.liferay.training.service.builder.NoSuchTrackerEntryException} if it could not be found.
	 *
	 * @param trackerEntryId the primary key of the tracker entry
	 * @return the tracker entry
	 * @throws com.liferay.training.service.builder.NoSuchTrackerEntryException if a tracker entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TrackerEntry findByPrimaryKey(long trackerEntryId)
		throws NoSuchTrackerEntryException, SystemException {
		return findByPrimaryKey((Serializable)trackerEntryId);
	}

	/**
	 * Returns the tracker entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the tracker entry
	 * @return the tracker entry, or <code>null</code> if a tracker entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TrackerEntry fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		TrackerEntry trackerEntry = (TrackerEntry)EntityCacheUtil.getResult(TrackerEntryModelImpl.ENTITY_CACHE_ENABLED,
				TrackerEntryImpl.class, primaryKey);

		if (trackerEntry == _nullTrackerEntry) {
			return null;
		}

		if (trackerEntry == null) {
			Session session = null;

			try {
				session = openSession();

				trackerEntry = (TrackerEntry)session.get(TrackerEntryImpl.class,
						primaryKey);

				if (trackerEntry != null) {
					cacheResult(trackerEntry);
				}
				else {
					EntityCacheUtil.putResult(TrackerEntryModelImpl.ENTITY_CACHE_ENABLED,
						TrackerEntryImpl.class, primaryKey, _nullTrackerEntry);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(TrackerEntryModelImpl.ENTITY_CACHE_ENABLED,
					TrackerEntryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return trackerEntry;
	}

	/**
	 * Returns the tracker entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param trackerEntryId the primary key of the tracker entry
	 * @return the tracker entry, or <code>null</code> if a tracker entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TrackerEntry fetchByPrimaryKey(long trackerEntryId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)trackerEntryId);
	}

	/**
	 * Returns all the tracker entries.
	 *
	 * @return the tracker entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<TrackerEntry> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<TrackerEntry> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
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
	@Override
	public List<TrackerEntry> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<TrackerEntry> list = (List<TrackerEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_TRACKERENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TRACKERENTRY;

				if (pagination) {
					sql = sql.concat(TrackerEntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<TrackerEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<TrackerEntry>(list);
				}
				else {
					list = (List<TrackerEntry>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the tracker entries from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (TrackerEntry trackerEntry : findAll()) {
			remove(trackerEntry);
		}
	}

	/**
	 * Returns the number of tracker entries.
	 *
	 * @return the number of tracker entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TRACKERENTRY);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the tracker entry persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.training.service.builder.model.TrackerEntry")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<TrackerEntry>> listenersList = new ArrayList<ModelListener<TrackerEntry>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<TrackerEntry>)InstanceFactory.newInstance(
							getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(TrackerEntryImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_TRACKERENTRY = "SELECT trackerEntry FROM TrackerEntry trackerEntry";
	private static final String _SQL_SELECT_TRACKERENTRY_WHERE = "SELECT trackerEntry FROM TrackerEntry trackerEntry WHERE ";
	private static final String _SQL_COUNT_TRACKERENTRY = "SELECT COUNT(trackerEntry) FROM TrackerEntry trackerEntry";
	private static final String _SQL_COUNT_TRACKERENTRY_WHERE = "SELECT COUNT(trackerEntry) FROM TrackerEntry trackerEntry WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "trackerEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TrackerEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TrackerEntry exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(TrackerEntryPersistenceImpl.class);
	private static TrackerEntry _nullTrackerEntry = new TrackerEntryImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<TrackerEntry> toCacheModel() {
				return _nullTrackerEntryCacheModel;
			}
		};

	private static CacheModel<TrackerEntry> _nullTrackerEntryCacheModel = new CacheModel<TrackerEntry>() {
			@Override
			public TrackerEntry toEntityModel() {
				return _nullTrackerEntry;
			}
		};
}