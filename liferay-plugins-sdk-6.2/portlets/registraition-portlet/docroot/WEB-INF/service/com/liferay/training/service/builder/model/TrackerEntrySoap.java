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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.training.service.builder.service.http.TrackerEntryServiceSoap}.
 *
 * @author Shanon Mathai
 * @see com.liferay.training.service.builder.service.http.TrackerEntryServiceSoap
 * @generated
 */
public class TrackerEntrySoap implements Serializable {
	public static TrackerEntrySoap toSoapModel(TrackerEntry model) {
		TrackerEntrySoap soapModel = new TrackerEntrySoap();

		soapModel.setTrackerEntryId(model.getTrackerEntryId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setEventDate(model.getEventDate());
		soapModel.setEventType(model.getEventType());
		soapModel.setIpAddress(model.getIpAddress());

		return soapModel;
	}

	public static TrackerEntrySoap[] toSoapModels(TrackerEntry[] models) {
		TrackerEntrySoap[] soapModels = new TrackerEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TrackerEntrySoap[][] toSoapModels(TrackerEntry[][] models) {
		TrackerEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new TrackerEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new TrackerEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TrackerEntrySoap[] toSoapModels(List<TrackerEntry> models) {
		List<TrackerEntrySoap> soapModels = new ArrayList<TrackerEntrySoap>(models.size());

		for (TrackerEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TrackerEntrySoap[soapModels.size()]);
	}

	public TrackerEntrySoap() {
	}

	public long getPrimaryKey() {
		return _trackerEntryId;
	}

	public void setPrimaryKey(long pk) {
		setTrackerEntryId(pk);
	}

	public long getTrackerEntryId() {
		return _trackerEntryId;
	}

	public void setTrackerEntryId(long trackerEntryId) {
		_trackerEntryId = trackerEntryId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getEventDate() {
		return _eventDate;
	}

	public void setEventDate(Date eventDate) {
		_eventDate = eventDate;
	}

	public String getEventType() {
		return _eventType;
	}

	public void setEventType(String eventType) {
		_eventType = eventType;
	}

	public String getIpAddress() {
		return _ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		_ipAddress = ipAddress;
	}

	private long _trackerEntryId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _eventDate;
	private String _eventType;
	private String _ipAddress;
}