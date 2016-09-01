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

package com.liferay.training.service.builder.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link TrackerEntry}.
 * </p>
 *
 * @author Shanon Mathai
 * @see TrackerEntry
 * @generated
 */
public class TrackerEntryWrapper implements TrackerEntry,
	ModelWrapper<TrackerEntry> {
	public TrackerEntryWrapper(TrackerEntry trackerEntry) {
		_trackerEntry = trackerEntry;
	}

	@Override
	public Class<?> getModelClass() {
		return TrackerEntry.class;
	}

	@Override
	public String getModelClassName() {
		return TrackerEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("trackerEntryId", getTrackerEntryId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("eventDate", getEventDate());
		attributes.put("eventType", getEventType());
		attributes.put("ipAddress", getIpAddress());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long trackerEntryId = (Long)attributes.get("trackerEntryId");

		if (trackerEntryId != null) {
			setTrackerEntryId(trackerEntryId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date eventDate = (Date)attributes.get("eventDate");

		if (eventDate != null) {
			setEventDate(eventDate);
		}

		String eventType = (String)attributes.get("eventType");

		if (eventType != null) {
			setEventType(eventType);
		}

		String ipAddress = (String)attributes.get("ipAddress");

		if (ipAddress != null) {
			setIpAddress(ipAddress);
		}
	}

	/**
	* Returns the primary key of this tracker entry.
	*
	* @return the primary key of this tracker entry
	*/
	@Override
	public long getPrimaryKey() {
		return _trackerEntry.getPrimaryKey();
	}

	/**
	* Sets the primary key of this tracker entry.
	*
	* @param primaryKey the primary key of this tracker entry
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_trackerEntry.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the tracker entry ID of this tracker entry.
	*
	* @return the tracker entry ID of this tracker entry
	*/
	@Override
	public long getTrackerEntryId() {
		return _trackerEntry.getTrackerEntryId();
	}

	/**
	* Sets the tracker entry ID of this tracker entry.
	*
	* @param trackerEntryId the tracker entry ID of this tracker entry
	*/
	@Override
	public void setTrackerEntryId(long trackerEntryId) {
		_trackerEntry.setTrackerEntryId(trackerEntryId);
	}

	/**
	* Returns the group ID of this tracker entry.
	*
	* @return the group ID of this tracker entry
	*/
	@Override
	public long getGroupId() {
		return _trackerEntry.getGroupId();
	}

	/**
	* Sets the group ID of this tracker entry.
	*
	* @param groupId the group ID of this tracker entry
	*/
	@Override
	public void setGroupId(long groupId) {
		_trackerEntry.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this tracker entry.
	*
	* @return the company ID of this tracker entry
	*/
	@Override
	public long getCompanyId() {
		return _trackerEntry.getCompanyId();
	}

	/**
	* Sets the company ID of this tracker entry.
	*
	* @param companyId the company ID of this tracker entry
	*/
	@Override
	public void setCompanyId(long companyId) {
		_trackerEntry.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this tracker entry.
	*
	* @return the user ID of this tracker entry
	*/
	@Override
	public long getUserId() {
		return _trackerEntry.getUserId();
	}

	/**
	* Sets the user ID of this tracker entry.
	*
	* @param userId the user ID of this tracker entry
	*/
	@Override
	public void setUserId(long userId) {
		_trackerEntry.setUserId(userId);
	}

	/**
	* Returns the user uuid of this tracker entry.
	*
	* @return the user uuid of this tracker entry
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trackerEntry.getUserUuid();
	}

	/**
	* Sets the user uuid of this tracker entry.
	*
	* @param userUuid the user uuid of this tracker entry
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_trackerEntry.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this tracker entry.
	*
	* @return the user name of this tracker entry
	*/
	@Override
	public java.lang.String getUserName() {
		return _trackerEntry.getUserName();
	}

	/**
	* Sets the user name of this tracker entry.
	*
	* @param userName the user name of this tracker entry
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_trackerEntry.setUserName(userName);
	}

	/**
	* Returns the event date of this tracker entry.
	*
	* @return the event date of this tracker entry
	*/
	@Override
	public java.util.Date getEventDate() {
		return _trackerEntry.getEventDate();
	}

	/**
	* Sets the event date of this tracker entry.
	*
	* @param eventDate the event date of this tracker entry
	*/
	@Override
	public void setEventDate(java.util.Date eventDate) {
		_trackerEntry.setEventDate(eventDate);
	}

	/**
	* Returns the event type of this tracker entry.
	*
	* @return the event type of this tracker entry
	*/
	@Override
	public java.lang.String getEventType() {
		return _trackerEntry.getEventType();
	}

	/**
	* Sets the event type of this tracker entry.
	*
	* @param eventType the event type of this tracker entry
	*/
	@Override
	public void setEventType(java.lang.String eventType) {
		_trackerEntry.setEventType(eventType);
	}

	/**
	* Returns the ip address of this tracker entry.
	*
	* @return the ip address of this tracker entry
	*/
	@Override
	public java.lang.String getIpAddress() {
		return _trackerEntry.getIpAddress();
	}

	/**
	* Sets the ip address of this tracker entry.
	*
	* @param ipAddress the ip address of this tracker entry
	*/
	@Override
	public void setIpAddress(java.lang.String ipAddress) {
		_trackerEntry.setIpAddress(ipAddress);
	}

	@Override
	public boolean isNew() {
		return _trackerEntry.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_trackerEntry.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _trackerEntry.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_trackerEntry.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _trackerEntry.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _trackerEntry.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_trackerEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _trackerEntry.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_trackerEntry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_trackerEntry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_trackerEntry.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new TrackerEntryWrapper((TrackerEntry)_trackerEntry.clone());
	}

	@Override
	public int compareTo(
		com.liferay.training.service.builder.model.TrackerEntry trackerEntry) {
		return _trackerEntry.compareTo(trackerEntry);
	}

	@Override
	public int hashCode() {
		return _trackerEntry.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.training.service.builder.model.TrackerEntry> toCacheModel() {
		return _trackerEntry.toCacheModel();
	}

	@Override
	public com.liferay.training.service.builder.model.TrackerEntry toEscapedModel() {
		return new TrackerEntryWrapper(_trackerEntry.toEscapedModel());
	}

	@Override
	public com.liferay.training.service.builder.model.TrackerEntry toUnescapedModel() {
		return new TrackerEntryWrapper(_trackerEntry.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _trackerEntry.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _trackerEntry.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_trackerEntry.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TrackerEntryWrapper)) {
			return false;
		}

		TrackerEntryWrapper trackerEntryWrapper = (TrackerEntryWrapper)obj;

		if (Validator.equals(_trackerEntry, trackerEntryWrapper._trackerEntry)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public TrackerEntry getWrappedTrackerEntry() {
		return _trackerEntry;
	}

	@Override
	public TrackerEntry getWrappedModel() {
		return _trackerEntry;
	}

	@Override
	public void resetOriginalValues() {
		_trackerEntry.resetOriginalValues();
	}

	private TrackerEntry _trackerEntry;
}