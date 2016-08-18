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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the TrackerEntry service. Represents a row in the &quot;tracking_TrackerEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.training.service.builder.model.impl.TrackerEntryModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.training.service.builder.model.impl.TrackerEntryImpl}.
 * </p>
 *
 * @author Shanon Mathai
 * @see TrackerEntry
 * @see com.liferay.training.service.builder.model.impl.TrackerEntryImpl
 * @see com.liferay.training.service.builder.model.impl.TrackerEntryModelImpl
 * @generated
 */
public interface TrackerEntryModel extends BaseModel<TrackerEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a tracker entry model instance should use the {@link TrackerEntry} interface instead.
	 */

	/**
	 * Returns the primary key of this tracker entry.
	 *
	 * @return the primary key of this tracker entry
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this tracker entry.
	 *
	 * @param primaryKey the primary key of this tracker entry
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the tracker entry ID of this tracker entry.
	 *
	 * @return the tracker entry ID of this tracker entry
	 */
	public long getTrackerEntryId();

	/**
	 * Sets the tracker entry ID of this tracker entry.
	 *
	 * @param trackerEntryId the tracker entry ID of this tracker entry
	 */
	public void setTrackerEntryId(long trackerEntryId);

	/**
	 * Returns the group ID of this tracker entry.
	 *
	 * @return the group ID of this tracker entry
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this tracker entry.
	 *
	 * @param groupId the group ID of this tracker entry
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this tracker entry.
	 *
	 * @return the company ID of this tracker entry
	 */
	public long getCompanyId();

	/**
	 * Sets the company ID of this tracker entry.
	 *
	 * @param companyId the company ID of this tracker entry
	 */
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this tracker entry.
	 *
	 * @return the user ID of this tracker entry
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this tracker entry.
	 *
	 * @param userId the user ID of this tracker entry
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this tracker entry.
	 *
	 * @return the user uuid of this tracker entry
	 * @throws SystemException if a system exception occurred
	 */
	public String getUserUuid() throws SystemException;

	/**
	 * Sets the user uuid of this tracker entry.
	 *
	 * @param userUuid the user uuid of this tracker entry
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this tracker entry.
	 *
	 * @return the user name of this tracker entry
	 */
	@AutoEscape
	public String getUserName();

	/**
	 * Sets the user name of this tracker entry.
	 *
	 * @param userName the user name of this tracker entry
	 */
	public void setUserName(String userName);

	/**
	 * Returns the event date of this tracker entry.
	 *
	 * @return the event date of this tracker entry
	 */
	public Date getEventDate();

	/**
	 * Sets the event date of this tracker entry.
	 *
	 * @param eventDate the event date of this tracker entry
	 */
	public void setEventDate(Date eventDate);

	/**
	 * Returns the event type of this tracker entry.
	 *
	 * @return the event type of this tracker entry
	 */
	@AutoEscape
	public String getEventType();

	/**
	 * Sets the event type of this tracker entry.
	 *
	 * @param eventType the event type of this tracker entry
	 */
	public void setEventType(String eventType);

	/**
	 * Returns the ip address of this tracker entry.
	 *
	 * @return the ip address of this tracker entry
	 */
	@AutoEscape
	public String getIpAddress();

	/**
	 * Sets the ip address of this tracker entry.
	 *
	 * @param ipAddress the ip address of this tracker entry
	 */
	public void setIpAddress(String ipAddress);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(
		com.liferay.training.service.builder.model.TrackerEntry trackerEntry);

	@Override
	public int hashCode();

	@Override
	public CacheModel<com.liferay.training.service.builder.model.TrackerEntry> toCacheModel();

	@Override
	public com.liferay.training.service.builder.model.TrackerEntry toEscapedModel();

	@Override
	public com.liferay.training.service.builder.model.TrackerEntry toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}