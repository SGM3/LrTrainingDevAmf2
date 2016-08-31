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

import com.liferay.training.service.builder.service.TrackerEntryServiceUtil;

import java.util.Arrays;

/**
 * @author Shanon Mathai
 * @generated
 */
public class TrackerEntryServiceClpInvoker {
	public TrackerEntryServiceClpInvoker() {
		_methodName18 = "getBeanIdentifier";

		_methodParameterTypes18 = new String[] {  };

		_methodName19 = "setBeanIdentifier";

		_methodParameterTypes19 = new String[] { "java.lang.String" };

		_methodName24 = "getTrackerEntriesCount";

		_methodParameterTypes24 = new String[] { "long" };

		_methodName25 = "countByEventType";

		_methodParameterTypes25 = new String[] { "java.lang.String", "long" };

		_methodName26 = "getTrackerEntries";

		_methodParameterTypes26 = new String[] { "int", "int", "long" };

		_methodName27 = "findByEventType";

		_methodParameterTypes27 = new String[] {
				"java.lang.String", "int", "int", "long"
			};

		_methodName28 = "findByEventType";

		_methodParameterTypes28 = new String[] { "java.lang.String", "long" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName18.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes18, parameterTypes)) {
			return TrackerEntryServiceUtil.getBeanIdentifier();
		}

		if (_methodName19.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes19, parameterTypes)) {
			TrackerEntryServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName24.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes24, parameterTypes)) {
			return TrackerEntryServiceUtil.getTrackerEntriesCount(((Long)arguments[0]).longValue());
		}

		if (_methodName25.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes25, parameterTypes)) {
			return TrackerEntryServiceUtil.countByEventType((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName26.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes26, parameterTypes)) {
			return TrackerEntryServiceUtil.getTrackerEntries(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue(),
				((Long)arguments[2]).longValue());
		}

		if (_methodName27.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes27, parameterTypes)) {
			return TrackerEntryServiceUtil.findByEventType((java.lang.String)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				((Long)arguments[3]).longValue());
		}

		if (_methodName28.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes28, parameterTypes)) {
			return TrackerEntryServiceUtil.findByEventType((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName18;
	private String[] _methodParameterTypes18;
	private String _methodName19;
	private String[] _methodParameterTypes19;
	private String _methodName24;
	private String[] _methodParameterTypes24;
	private String _methodName25;
	private String[] _methodParameterTypes25;
	private String _methodName26;
	private String[] _methodParameterTypes26;
	private String _methodName27;
	private String[] _methodParameterTypes27;
	private String _methodName28;
	private String[] _methodParameterTypes28;
}