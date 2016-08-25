package com.liferay.training.results;

import static com.liferay.training.eventmonitor.AmfEventMonitorConstants.ITER_OBJ_ATTR;
import static com.liferay.training.eventmonitor.AmfEventMonitorConstants.URL_STRING_ATTR;
import static com.liferay.training.results.AmfSearchResultsConstants.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.Event;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.ProcessEvent;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.model.Address;
import com.liferay.portal.model.User;
import com.liferay.portal.service.AddressLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class AmfSearchResults
 */
public class AmfSearchResults extends MVCPortlet {
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		Integer curPage = ParamUtil.getInteger(
			renderRequest, CUR_PARAM_NAME, 1);
		Integer curDelta = ParamUtil.getInteger(
				renderRequest, PAGE_DELTA_PARAM, 5);
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
		
		List<User> listOfUsers;
		try {
			listOfUsers = _getUsersForZip(eventRequest, zipCode, 0, 5);
		} catch (SystemException e) {
			SessionErrors.add(eventRequest, "unable-to-query-error");
			listOfUsers = new ArrayList<User>();
		}

		eventRequest.setAttribute("userEntries", listOfUsers);
		eventRequest.setAttribute("entryCount", listOfUsers.size());
		
		eventResponse.setRenderParameter("zipcodeparam", zipCode.toString());
	}

	@SuppressWarnings("unchecked")
	private List<User> _getUsersForZip(
			EventRequest eventRequest, Integer zipCode,
			int begin, int end) throws SystemException {
		
		String zeroPaddedZip = String.format("%05d", zipCode);
		
		try {
			DynamicQuery addressDynamicQuery = DynamicQueryFactoryUtil
				.forClass(Address.class)
				.add(PropertyFactoryUtil.forName("zip").eq(zeroPaddedZip))
				.setProjection(PropertyFactoryUtil.forName("userId"));
			
			List<Long> listOfUserIds = 
				AddressLocalServiceUtil.dynamicQuery(
					addressDynamicQuery, begin, end);
//			List<Long> listOfUserIds = new ArrayList<Long>();
//			for (Address addr: addrs){
//				listOfUserIds.add(addr.getUserId());
//			}
			
			// Use of 'in' may have limitations? (query size limits, etc)
			
			DynamicQuery userDynamicQuery = DynamicQueryFactoryUtil
				.forClass(User.class)
				.add(PropertyFactoryUtil.forName("userId").in(listOfUserIds));
			
			return UserLocalServiceUtil.dynamicQuery(
				userDynamicQuery, begin, end);
		} catch (SystemException e) {
			throw e;
		}
	}

}
