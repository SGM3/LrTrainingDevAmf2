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

package com.liferay.training.amf2.builder.service.base;

import com.liferay.training.amf2.builder.service.AmfSignupLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Shanon Mathai
 * @generated
 */
public class AmfSignupLocalServiceClpInvoker {
	public AmfSignupLocalServiceClpInvoker() {
		_methodName26 = "getBeanIdentifier";

		_methodParameterTypes26 = new String[] {  };

		_methodName27 = "setBeanIdentifier";

		_methodParameterTypes27 = new String[] { "java.lang.String" };

		_methodName30 = "addUserWithAddressAndPhones";

		_methodParameterTypes30 = new String[] {
				"long", "long", "boolean", "java.lang.String",
				"java.lang.String", "boolean", "java.lang.String",
				"java.lang.String", "java.util.Locale", "java.lang.String",
				"java.lang.String", "boolean", "int", "int", "int",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "long", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"boolean", "com.liferay.portal.service.ServiceContext"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName26.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes26, parameterTypes)) {
			return AmfSignupLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName27.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes27, parameterTypes)) {
			AmfSignupLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName30.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes30, parameterTypes)) {
			return AmfSignupLocalServiceUtil.addUserWithAddressAndPhones(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Boolean)arguments[2]).booleanValue(),
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				((Boolean)arguments[5]).booleanValue(),
				(java.lang.String)arguments[6], (java.lang.String)arguments[7],
				(java.util.Locale)arguments[8], (java.lang.String)arguments[9],
				(java.lang.String)arguments[10],
				((Boolean)arguments[11]).booleanValue(),
				((Integer)arguments[12]).intValue(),
				((Integer)arguments[13]).intValue(),
				((Integer)arguments[14]).intValue(),
				(java.lang.String)arguments[15],
				(java.lang.String)arguments[16],
				(java.lang.String)arguments[17],
				(java.lang.String)arguments[18],
				((Long)arguments[19]).longValue(),
				(java.lang.String)arguments[20],
				(java.lang.String)arguments[21],
				(java.lang.String)arguments[22],
				(java.lang.String)arguments[23],
				((Boolean)arguments[24]).booleanValue(),
				(com.liferay.portal.service.ServiceContext)arguments[25]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName26;
	private String[] _methodParameterTypes26;
	private String _methodName27;
	private String[] _methodParameterTypes27;
	private String _methodName30;
	private String[] _methodParameterTypes30;
}