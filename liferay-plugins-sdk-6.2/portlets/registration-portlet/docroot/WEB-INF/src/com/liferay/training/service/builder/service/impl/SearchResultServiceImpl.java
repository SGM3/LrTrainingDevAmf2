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

package com.liferay.training.service.builder.service.impl;

import java.util.List;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.User;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.service.permission.PortletPermissionUtil;
import com.liferay.training.service.builder.service.base.SearchResultServiceBaseImpl;

/**
 * The implementation of the search result remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.training.service.builder.service.SearchResultService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Shanon Mathai
 * @see com.liferay.training.service.builder.service.base.SearchResultServiceBaseImpl
 * @see com.liferay.training.service.builder.service.SearchResultServiceUtil
 */
public class SearchResultServiceImpl extends SearchResultServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.liferay.training.service.builder.service.SearchResultServiceUtil} to access the search result remote service.
	 */
	public List<User> getUserFromZip(
		int zip) throws SystemException, PortalException {
		
		String portletId = _getAmfSearchResultPortletId();
		PortletPermissionUtil.check(
			getPermissionChecker(), portletId, _AMF_SEARCH_PERFORM_ACTION_ID);
		return searchResultLocalService.getUserFromZip(zip);
	}
	
	public List<User> getUserFromZip(
		int zip, int start, int end) throws SystemException, PortalException {

		String portletId = _getAmfSearchResultPortletId();
		PortletPermissionUtil.check(
			getPermissionChecker(), portletId, _AMF_SEARCH_PERFORM_ACTION_ID);
		return searchResultLocalService.getUserFromZip(zip, start, end);
	}
	
	public long countUsersFromZip(
		int zip) throws SystemException, PortalException {

		String portletId = _getAmfSearchResultPortletId();
		PortletPermissionUtil.check(
			getPermissionChecker(), portletId, _AMF_SEARCH_PERFORM_ACTION_ID);
		return searchResultLocalService.countUsersFromZip(zip);
	}

	private String _getAmfSearchResultPortletId() {
		
		List<Portlet> lp = PortletLocalServiceUtil.getPortlets();
		String portletId = "";
		for (Portlet p : lp){
			
			boolean isMyPortlet = p.getPortletName().equals(_AMF_SEARCH_NAME);
			if (isMyPortlet) {
				portletId = p.getPortletId();
				break;
			}
		}
		return portletId;
	}

	// name from portlet.xml
	
	private static final String _AMF_SEARCH_NAME = "amf-search";
	private static final String _AMF_SEARCH_PERFORM_ACTION_ID =
		"PERFORM_SEARCH";
}