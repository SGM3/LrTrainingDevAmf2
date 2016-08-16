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

package com.liferay.training.service.builder.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.training.service.builder.model.TrackerEntry;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing TrackerEntry in entity cache.
 *
 * @author Shanon Mathai
 * @see TrackerEntry
 * @generated
 */
public class TrackerEntryCacheModel implements CacheModel<TrackerEntry>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{trackerEntryId=");
		sb.append(trackerEntryId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", eventDate=");
		sb.append(eventDate);
		sb.append(", eventType=");
		sb.append(eventType);
		sb.append(", ipAddress=");
		sb.append(ipAddress);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public TrackerEntry toEntityModel() {
		TrackerEntryImpl trackerEntryImpl = new TrackerEntryImpl();

		trackerEntryImpl.setTrackerEntryId(trackerEntryId);
		trackerEntryImpl.setGroupId(groupId);
		trackerEntryImpl.setCompanyId(companyId);
		trackerEntryImpl.setUserId(userId);

		if (userName == null) {
			trackerEntryImpl.setUserName(StringPool.BLANK);
		}
		else {
			trackerEntryImpl.setUserName(userName);
		}

		if (eventDate == Long.MIN_VALUE) {
			trackerEntryImpl.setEventDate(null);
		}
		else {
			trackerEntryImpl.setEventDate(new Date(eventDate));
		}

		if (eventType == null) {
			trackerEntryImpl.setEventType(StringPool.BLANK);
		}
		else {
			trackerEntryImpl.setEventType(eventType);
		}

		if (ipAddress == null) {
			trackerEntryImpl.setIpAddress(StringPool.BLANK);
		}
		else {
			trackerEntryImpl.setIpAddress(ipAddress);
		}

		trackerEntryImpl.resetOriginalValues();

		return trackerEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		trackerEntryId = objectInput.readLong();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		eventDate = objectInput.readLong();
		eventType = objectInput.readUTF();
		ipAddress = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(trackerEntryId);
		objectOutput.writeLong(groupId);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(eventDate);

		if (eventType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(eventType);
		}

		if (ipAddress == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(ipAddress);
		}
	}

	public long trackerEntryId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long eventDate;
	public String eventType;
	public String ipAddress;
}