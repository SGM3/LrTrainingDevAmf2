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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Shanon Mathai
 */
public class UserAddressJoinerFinderUtil {
	public static java.util.List<com.liferay.portal.model.User> findUsersAtZip(
		java.lang.String zip, int being, int end) {
		return getFinder().findUsersAtZip(zip, being, end);
	}

	public static int countUsersAtZip(java.lang.String zip) {
		return getFinder().countUsersAtZip(zip);
	}

	public static UserAddressJoinerFinder getFinder() {
		if (_finder == null) {
			_finder = (UserAddressJoinerFinder)PortletBeanLocatorUtil.locate(com.liferay.training.service.builder.service.ClpSerializer.getServletContextName(),
					UserAddressJoinerFinder.class.getName());

			ReferenceRegistry.registerReference(UserAddressJoinerFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(UserAddressJoinerFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(UserAddressJoinerFinderUtil.class,
			"_finder");
	}

	private static UserAddressJoinerFinder _finder;
}