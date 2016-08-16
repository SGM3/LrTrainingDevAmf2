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
 * Provides a wrapper for {@link TrackerEntryLocalService}.
 *
 * @author Shanon Mathai
 * @see TrackerEntryLocalService
 * @generated
 */
public class TrackerEntryLocalServiceWrapper implements TrackerEntryLocalService,
	ServiceWrapper<TrackerEntryLocalService> {
	public TrackerEntryLocalServiceWrapper(
		TrackerEntryLocalService trackerEntryLocalService) {
		_trackerEntryLocalService = trackerEntryLocalService;
	}

	/**
	* Adds the tracker entry to the database. Also notifies the appropriate model listeners.
	*
	* @param trackerEntry the tracker entry
	* @return the tracker entry that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.training.service.builder.model.TrackerEntry addTrackerEntry(
		com.liferay.training.service.builder.model.TrackerEntry trackerEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trackerEntryLocalService.addTrackerEntry(trackerEntry);
	}

	/**
	* Creates a new tracker entry with the primary key. Does not add the tracker entry to the database.
	*
	* @param trackerEntryId the primary key for the new tracker entry
	* @return the new tracker entry
	*/
	@Override
	public com.liferay.training.service.builder.model.TrackerEntry createTrackerEntry(
		long trackerEntryId) {
		return _trackerEntryLocalService.createTrackerEntry(trackerEntryId);
	}

	/**
	* Deletes the tracker entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param trackerEntryId the primary key of the tracker entry
	* @return the tracker entry that was removed
	* @throws PortalException if a tracker entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.training.service.builder.model.TrackerEntry deleteTrackerEntry(
		long trackerEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trackerEntryLocalService.deleteTrackerEntry(trackerEntryId);
	}

	/**
	* Deletes the tracker entry from the database. Also notifies the appropriate model listeners.
	*
	* @param trackerEntry the tracker entry
	* @return the tracker entry that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.training.service.builder.model.TrackerEntry deleteTrackerEntry(
		com.liferay.training.service.builder.model.TrackerEntry trackerEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trackerEntryLocalService.deleteTrackerEntry(trackerEntry);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _trackerEntryLocalService.dynamicQuery();
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
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trackerEntryLocalService.dynamicQuery(dynamicQuery);
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
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _trackerEntryLocalService.dynamicQuery(dynamicQuery, start, end);
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
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trackerEntryLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trackerEntryLocalService.dynamicQueryCount(dynamicQuery);
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
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trackerEntryLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.training.service.builder.model.TrackerEntry fetchTrackerEntry(
		long trackerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trackerEntryLocalService.fetchTrackerEntry(trackerEntryId);
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
	public com.liferay.training.service.builder.model.TrackerEntry getTrackerEntry(
		long trackerEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trackerEntryLocalService.getTrackerEntry(trackerEntryId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trackerEntryLocalService.getPersistedModel(primaryKeyObj);
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
	public java.util.List<com.liferay.training.service.builder.model.TrackerEntry> getTrackerEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trackerEntryLocalService.getTrackerEntries(start, end);
	}

	/**
	* Returns the number of tracker entries.
	*
	* @return the number of tracker entries
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getTrackerEntriesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trackerEntryLocalService.getTrackerEntriesCount();
	}

	/**
	* Updates the tracker entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param trackerEntry the tracker entry
	* @return the tracker entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.training.service.builder.model.TrackerEntry updateTrackerEntry(
		com.liferay.training.service.builder.model.TrackerEntry trackerEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trackerEntryLocalService.updateTrackerEntry(trackerEntry);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _trackerEntryLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_trackerEntryLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _trackerEntryLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public java.util.List<com.liferay.training.service.builder.model.TrackerEntry> findByEventType(
		java.lang.String eventType, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trackerEntryLocalService.findByEventType(eventType, start, end);
	}

	@Override
	public java.util.List<com.liferay.training.service.builder.model.TrackerEntry> findByEventType(
		java.lang.String eventType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trackerEntryLocalService.findByEventType(eventType);
	}

	@Override
	public int countByEventType(java.lang.String eventType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trackerEntryLocalService.countByEventType(eventType);
	}

	@Override
	public java.util.List<com.liferay.training.service.builder.model.TrackerEntry> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trackerEntryLocalService.findByUserId(userId, start, end);
	}

	@Override
	public java.util.List<com.liferay.training.service.builder.model.TrackerEntry> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return _trackerEntryLocalService.findByUserId(userId);
	}

	@Override
	public int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trackerEntryLocalService.countByUserId(userId);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public TrackerEntryLocalService getWrappedTrackerEntryLocalService() {
		return _trackerEntryLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedTrackerEntryLocalService(
		TrackerEntryLocalService trackerEntryLocalService) {
		_trackerEntryLocalService = trackerEntryLocalService;
	}

	@Override
	public TrackerEntryLocalService getWrappedService() {
		return _trackerEntryLocalService;
	}

	@Override
	public void setWrappedService(
		TrackerEntryLocalService trackerEntryLocalService) {
		_trackerEntryLocalService = trackerEntryLocalService;
	}

	private TrackerEntryLocalService _trackerEntryLocalService;
}