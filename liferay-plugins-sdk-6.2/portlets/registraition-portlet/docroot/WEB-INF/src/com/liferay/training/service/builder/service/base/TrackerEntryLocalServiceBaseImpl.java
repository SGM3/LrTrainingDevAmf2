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

package com.liferay.training.service.builder.service.base;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.service.BaseLocalServiceImpl;
import com.liferay.portal.service.PersistedModelLocalServiceRegistryUtil;
import com.liferay.portal.service.persistence.UserPersistence;

import com.liferay.training.service.builder.model.TrackerEntry;
import com.liferay.training.service.builder.service.TrackerEntryLocalService;
import com.liferay.training.service.builder.service.persistence.TrackerEntryPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the tracker entry local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.training.service.builder.service.impl.TrackerEntryLocalServiceImpl}.
 * </p>
 *
 * @author Shanon Mathai
 * @see com.liferay.training.service.builder.service.impl.TrackerEntryLocalServiceImpl
 * @see com.liferay.training.service.builder.service.TrackerEntryLocalServiceUtil
 * @generated
 */
public abstract class TrackerEntryLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements TrackerEntryLocalService,
		IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.training.service.builder.service.TrackerEntryLocalServiceUtil} to access the tracker entry local service.
	 */

	/**
	 * Adds the tracker entry to the database. Also notifies the appropriate model listeners.
	 *
	 * @param trackerEntry the tracker entry
	 * @return the tracker entry that was added
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public TrackerEntry addTrackerEntry(TrackerEntry trackerEntry)
		throws SystemException {
		trackerEntry.setNew(true);

		return trackerEntryPersistence.update(trackerEntry);
	}

	/**
	 * Creates a new tracker entry with the primary key. Does not add the tracker entry to the database.
	 *
	 * @param trackerEntryId the primary key for the new tracker entry
	 * @return the new tracker entry
	 */
	@Override
	public TrackerEntry createTrackerEntry(long trackerEntryId) {
		return trackerEntryPersistence.create(trackerEntryId);
	}

	/**
	 * Deletes the tracker entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param trackerEntryId the primary key of the tracker entry
	 * @return the tracker entry that was removed
	 * @throws PortalException if a tracker entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public TrackerEntry deleteTrackerEntry(long trackerEntryId)
		throws PortalException, SystemException {
		return trackerEntryPersistence.remove(trackerEntryId);
	}

	/**
	 * Deletes the tracker entry from the database. Also notifies the appropriate model listeners.
	 *
	 * @param trackerEntry the tracker entry
	 * @return the tracker entry that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public TrackerEntry deleteTrackerEntry(TrackerEntry trackerEntry)
		throws SystemException {
		return trackerEntryPersistence.remove(trackerEntry);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(TrackerEntry.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return trackerEntryPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.training.service.builder.model.impl.TrackerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return trackerEntryPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.training.service.builder.model.impl.TrackerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return trackerEntryPersistence.findWithDynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows that match the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows that match the dynamic query
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery)
		throws SystemException {
		return trackerEntryPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows that match the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows that match the dynamic query
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) throws SystemException {
		return trackerEntryPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public TrackerEntry fetchTrackerEntry(long trackerEntryId)
		throws SystemException {
		return trackerEntryPersistence.fetchByPrimaryKey(trackerEntryId);
	}

	/**
	 * Returns the tracker entry with the primary key.
	 *
	 * @param trackerEntryId the primary key of the tracker entry
	 * @return the tracker entry
	 * @throws PortalException if a tracker entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TrackerEntry getTrackerEntry(long trackerEntryId)
		throws PortalException, SystemException {
		return trackerEntryPersistence.findByPrimaryKey(trackerEntryId);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException, SystemException {
		return trackerEntryPersistence.findByPrimaryKey(primaryKeyObj);
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
	public List<TrackerEntry> getTrackerEntries(int start, int end)
		throws SystemException {
		return trackerEntryPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of tracker entries.
	 *
	 * @return the number of tracker entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int getTrackerEntriesCount() throws SystemException {
		return trackerEntryPersistence.countAll();
	}

	/**
	 * Updates the tracker entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param trackerEntry the tracker entry
	 * @return the tracker entry that was updated
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public TrackerEntry updateTrackerEntry(TrackerEntry trackerEntry)
		throws SystemException {
		return trackerEntryPersistence.update(trackerEntry);
	}

	/**
	 * Returns the tracker entry local service.
	 *
	 * @return the tracker entry local service
	 */
	public com.liferay.training.service.builder.service.TrackerEntryLocalService getTrackerEntryLocalService() {
		return trackerEntryLocalService;
	}

	/**
	 * Sets the tracker entry local service.
	 *
	 * @param trackerEntryLocalService the tracker entry local service
	 */
	public void setTrackerEntryLocalService(
		com.liferay.training.service.builder.service.TrackerEntryLocalService trackerEntryLocalService) {
		this.trackerEntryLocalService = trackerEntryLocalService;
	}

	/**
	 * Returns the tracker entry remote service.
	 *
	 * @return the tracker entry remote service
	 */
	public com.liferay.training.service.builder.service.TrackerEntryService getTrackerEntryService() {
		return trackerEntryService;
	}

	/**
	 * Sets the tracker entry remote service.
	 *
	 * @param trackerEntryService the tracker entry remote service
	 */
	public void setTrackerEntryService(
		com.liferay.training.service.builder.service.TrackerEntryService trackerEntryService) {
		this.trackerEntryService = trackerEntryService;
	}

	/**
	 * Returns the tracker entry persistence.
	 *
	 * @return the tracker entry persistence
	 */
	public TrackerEntryPersistence getTrackerEntryPersistence() {
		return trackerEntryPersistence;
	}

	/**
	 * Sets the tracker entry persistence.
	 *
	 * @param trackerEntryPersistence the tracker entry persistence
	 */
	public void setTrackerEntryPersistence(
		TrackerEntryPersistence trackerEntryPersistence) {
		this.trackerEntryPersistence = trackerEntryPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public com.liferay.portal.service.UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(
		com.liferay.portal.service.UserService userService) {
		this.userService = userService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		Class<?> clazz = getClass();

		_classLoader = clazz.getClassLoader();

		PersistedModelLocalServiceRegistryUtil.register("com.liferay.training.service.builder.model.TrackerEntry",
			trackerEntryLocalService);
	}

	public void destroy() {
		PersistedModelLocalServiceRegistryUtil.unregister(
			"com.liferay.training.service.builder.model.TrackerEntry");
	}

	/**
	 * Returns the Spring bean ID for this bean.
	 *
	 * @return the Spring bean ID for this bean
	 */
	@Override
	public String getBeanIdentifier() {
		return _beanIdentifier;
	}

	/**
	 * Sets the Spring bean ID for this bean.
	 *
	 * @param beanIdentifier the Spring bean ID for this bean
	 */
	@Override
	public void setBeanIdentifier(String beanIdentifier) {
		_beanIdentifier = beanIdentifier;
	}

	@Override
	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		if (contextClassLoader != _classLoader) {
			currentThread.setContextClassLoader(_classLoader);
		}

		try {
			return _clpInvoker.invokeMethod(name, parameterTypes, arguments);
		}
		finally {
			if (contextClassLoader != _classLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	protected Class<?> getModelClass() {
		return TrackerEntry.class;
	}

	protected String getModelClassName() {
		return TrackerEntry.class.getName();
	}

	/**
	 * Performs an SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = trackerEntryPersistence.getDataSource();

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = com.liferay.training.service.builder.service.TrackerEntryLocalService.class)
	protected com.liferay.training.service.builder.service.TrackerEntryLocalService trackerEntryLocalService;
	@BeanReference(type = com.liferay.training.service.builder.service.TrackerEntryService.class)
	protected com.liferay.training.service.builder.service.TrackerEntryService trackerEntryService;
	@BeanReference(type = TrackerEntryPersistence.class)
	protected TrackerEntryPersistence trackerEntryPersistence;
	@BeanReference(type = com.liferay.counter.service.CounterLocalService.class)
	protected com.liferay.counter.service.CounterLocalService counterLocalService;
	@BeanReference(type = com.liferay.portal.service.ResourceLocalService.class)
	protected com.liferay.portal.service.ResourceLocalService resourceLocalService;
	@BeanReference(type = com.liferay.portal.service.UserLocalService.class)
	protected com.liferay.portal.service.UserLocalService userLocalService;
	@BeanReference(type = com.liferay.portal.service.UserService.class)
	protected com.liferay.portal.service.UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private String _beanIdentifier;
	private ClassLoader _classLoader;
	private TrackerEntryLocalServiceClpInvoker _clpInvoker = new TrackerEntryLocalServiceClpInvoker();
}