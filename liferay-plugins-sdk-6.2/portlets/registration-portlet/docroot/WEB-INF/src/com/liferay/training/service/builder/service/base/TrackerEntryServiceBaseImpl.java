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
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.BaseServiceImpl;
import com.liferay.portal.service.persistence.UserPersistence;

import com.liferay.training.service.builder.model.TrackerEntry;
import com.liferay.training.service.builder.service.TrackerEntryService;
import com.liferay.training.service.builder.service.persistence.TrackerEntryPersistence;
import com.liferay.training.service.builder.service.persistence.UserAddressJoinerFinder;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the tracker entry remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.training.service.builder.service.impl.TrackerEntryServiceImpl}.
 * </p>
 *
 * @author Shanon Mathai
 * @see com.liferay.training.service.builder.service.impl.TrackerEntryServiceImpl
 * @see com.liferay.training.service.builder.service.TrackerEntryServiceUtil
 * @generated
 */
public abstract class TrackerEntryServiceBaseImpl extends BaseServiceImpl
	implements TrackerEntryService, IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.training.service.builder.service.TrackerEntryServiceUtil} to access the tracker entry remote service.
	 */

	/**
	 * Returns the search result local service.
	 *
	 * @return the search result local service
	 */
	public com.liferay.training.service.builder.service.SearchResultLocalService getSearchResultLocalService() {
		return searchResultLocalService;
	}

	/**
	 * Sets the search result local service.
	 *
	 * @param searchResultLocalService the search result local service
	 */
	public void setSearchResultLocalService(
		com.liferay.training.service.builder.service.SearchResultLocalService searchResultLocalService) {
		this.searchResultLocalService = searchResultLocalService;
	}

	/**
	 * Returns the search result remote service.
	 *
	 * @return the search result remote service
	 */
	public com.liferay.training.service.builder.service.SearchResultService getSearchResultService() {
		return searchResultService;
	}

	/**
	 * Sets the search result remote service.
	 *
	 * @param searchResultService the search result remote service
	 */
	public void setSearchResultService(
		com.liferay.training.service.builder.service.SearchResultService searchResultService) {
		this.searchResultService = searchResultService;
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
	 * Returns the user address joiner remote service.
	 *
	 * @return the user address joiner remote service
	 */
	public com.liferay.training.service.builder.service.UserAddressJoinerService getUserAddressJoinerService() {
		return userAddressJoinerService;
	}

	/**
	 * Sets the user address joiner remote service.
	 *
	 * @param userAddressJoinerService the user address joiner remote service
	 */
	public void setUserAddressJoinerService(
		com.liferay.training.service.builder.service.UserAddressJoinerService userAddressJoinerService) {
		this.userAddressJoinerService = userAddressJoinerService;
	}

	/**
	 * Returns the user address joiner finder.
	 *
	 * @return the user address joiner finder
	 */
	public UserAddressJoinerFinder getUserAddressJoinerFinder() {
		return userAddressJoinerFinder;
	}

	/**
	 * Sets the user address joiner finder.
	 *
	 * @param userAddressJoinerFinder the user address joiner finder
	 */
	public void setUserAddressJoinerFinder(
		UserAddressJoinerFinder userAddressJoinerFinder) {
		this.userAddressJoinerFinder = userAddressJoinerFinder;
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
	}

	public void destroy() {
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

	@BeanReference(type = com.liferay.training.service.builder.service.SearchResultLocalService.class)
	protected com.liferay.training.service.builder.service.SearchResultLocalService searchResultLocalService;
	@BeanReference(type = com.liferay.training.service.builder.service.SearchResultService.class)
	protected com.liferay.training.service.builder.service.SearchResultService searchResultService;
	@BeanReference(type = com.liferay.training.service.builder.service.TrackerEntryLocalService.class)
	protected com.liferay.training.service.builder.service.TrackerEntryLocalService trackerEntryLocalService;
	@BeanReference(type = com.liferay.training.service.builder.service.TrackerEntryService.class)
	protected com.liferay.training.service.builder.service.TrackerEntryService trackerEntryService;
	@BeanReference(type = TrackerEntryPersistence.class)
	protected TrackerEntryPersistence trackerEntryPersistence;
	@BeanReference(type = com.liferay.training.service.builder.service.UserAddressJoinerService.class)
	protected com.liferay.training.service.builder.service.UserAddressJoinerService userAddressJoinerService;
	@BeanReference(type = UserAddressJoinerFinder.class)
	protected UserAddressJoinerFinder userAddressJoinerFinder;
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
	private TrackerEntryServiceClpInvoker _clpInvoker = new TrackerEntryServiceClpInvoker();
}