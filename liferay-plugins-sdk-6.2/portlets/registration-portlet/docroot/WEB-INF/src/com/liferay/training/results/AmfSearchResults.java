package com.liferay.training.results;

import static com.liferay.training.results.AmfSearchResultsConstants.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.Event;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;
import javax.portlet.ProcessEvent;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.model.User;
import com.liferay.training.service.builder.service.UserAddressJoinerServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class AmfSearchResults
 */
public class AmfSearchResults extends MVCPortlet {
	
	@Override
	public void doView(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {
		
		Integer curPage = ParamUtil.getInteger(
			renderRequest, CUR_PARAM_NAME, 1);
		Integer curDelta = ParamUtil.getInteger(
				renderRequest, PAGE_DELTA_PARAM, 5);
		Integer zipCode = ParamUtil.getInteger(
				renderRequest, ZIP_CODE_PARAM, -1);
		
		if (zipCode.intValue() != -1) {
			_populateRenderedTable(renderRequest, renderResponse, zipCode);
		}
		
		PortletURL searchResultUrl = renderResponse.createRenderURL();
		searchResultUrl.setParameter(CUR_PARAM_NAME, curPage.toString());
		searchResultUrl.setParameter(PAGE_DELTA_PARAM, curDelta.toString());
		
		renderRequest.setAttribute(ITER_OBJ_ATTR, searchResultUrl);
		renderRequest.setAttribute(DELTA_VAL_ATTR, curDelta.toString());
		
		super.doView(renderRequest, renderResponse);
	}
 
	@ProcessEvent(qname="{http://liferay.com/search}ipc.search.zip")
	public void setSearchCriteria(
		EventRequest eventRequest, EventResponse eventResponse) {
		
		Event criteriaEvent = eventRequest.getEvent();
		Integer zipCode = (Integer) criteriaEvent.getValue();
		
		// set the page parameter that is used later in the doView
		
		String zipLeftPaddedZeros = String.format("%05d", zipCode.intValue());

		eventResponse.setRenderParameter(ZIP_CODE_PARAM, zipLeftPaddedZeros);
	}

	private void _populateRenderedTable(
		PortletRequest eventRequest, PortletResponse eventResponse,
		Integer zipCode) throws PortletException {
		
		Integer curPage = ParamUtil.getInteger(
				eventRequest, CUR_PARAM_NAME, 1);
		Integer curDelta = ParamUtil.getInteger(
				eventRequest, PAGE_DELTA_PARAM, 5);

		long maxUserCount = 0;

		try {
			String zeroPadded = String.format("%05d", zipCode);
			maxUserCount = 
				UserAddressJoinerServiceUtil.countUsersAtZip(zeroPadded);
		} catch (SystemException e) {
			_log.warn("Users by zip query failed");
			throw new PortletException(e); 
		} catch (PortalException e) {
			SessionErrors.add(eventRequest, "tec-authentication-failure");
			_log.debug("Current user verification failed.");
		}
		
		eventRequest.setAttribute("entryCount", maxUserCount);
		
		List<User> listOfUsers;
		try {
			listOfUsers = 
				_getUsersForZip(zipCode, curPage, curDelta);
		} catch (SystemException e) {
			SessionErrors.add(eventRequest, "unable-to-query-error");
			listOfUsers = new ArrayList<User>();
		}

		eventRequest.setAttribute("userEntries", listOfUsers);
	}

	private List<User> _getUsersForZip(
			Integer zipCode, int page, int delta) throws SystemException {
		
		try {
			
			// Users potentially may have multiple addresses in 
			// the same zip code
			
			int begin = (page - 1) * delta;
			int end = begin + delta;
			
			String zeroPadded = String.format("%05d", zipCode);
			return UserAddressJoinerServiceUtil.findUsersAtZip(
				zeroPadded, begin, end);
		} catch (SystemException e) {
			throw e;
		} catch (PortalException e) {
			_log.warn("Current user verification failed.");
			return new ArrayList<User>();
		}
	}
	
	private static final Log _log = 
		LogFactoryUtil.getLog(AmfSearchResults.class);

}
