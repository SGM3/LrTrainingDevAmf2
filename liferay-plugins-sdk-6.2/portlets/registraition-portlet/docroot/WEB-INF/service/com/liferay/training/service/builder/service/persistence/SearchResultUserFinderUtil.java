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
public class SearchResultUserFinderUtil {
	public static java.util.List<com.liferay.portal.model.User> findUserByZipCode(
		java.lang.Integer zipCode, int begin, int end) {
		return getFinder().findUserByZipCode(zipCode, begin, end);
	}

	public static void setSearchResultUserFinder(
		com.liferay.training.service.builder.service.persistence.SearchResultUserFinder sFinder) {
		getFinder().setSearchResultUserFinder(sFinder);
	}

	public static com.liferay.training.service.builder.service.persistence.SearchResultUserFinder getSearchResultUserFinder() {
		return getFinder().getSearchResultUserFinder();
	}

	public static SearchResultUserFinder getFinder() {
		if (_finder == null) {
			_finder = (SearchResultUserFinder)PortletBeanLocatorUtil.locate(com.liferay.training.service.builder.service.ClpSerializer.getServletContextName(),
					SearchResultUserFinder.class.getName());

			ReferenceRegistry.registerReference(SearchResultUserFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(SearchResultUserFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(SearchResultUserFinderUtil.class,
			"_finder");
	}

	private static SearchResultUserFinder _finder;
}