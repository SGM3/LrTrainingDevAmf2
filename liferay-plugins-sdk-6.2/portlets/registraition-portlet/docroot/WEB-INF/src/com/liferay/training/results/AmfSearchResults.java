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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.Tuple;
import com.liferay.portal.model.Address;
import com.liferay.portal.model.User;
import com.liferay.portal.service.AddressLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.training.service.builder.service.UserAddressJoinerServiceUtil;
import com.liferay.training.service.builder.service.persistence.UserAddressJoinerFinderUtil;
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
				renderRequest, "zipcodeparam", -1);
		
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

		eventResponse.setRenderParameter("zipcodeparam", zipCode.toString());
	}

	@SuppressWarnings("unchecked")
	private void _populateRenderedTable(
		PortletRequest eventRequest, PortletResponse eventResponse,
		Integer zipCode) {
		
		Integer curPage = ParamUtil.getInteger(
				eventRequest, CUR_PARAM_NAME, 1);
		Integer curDelta = ParamUtil.getInteger(
				eventRequest, PAGE_DELTA_PARAM, 5);

		long maxUserCount;
		List<User> listOfUsers;
		try {
			int begin = (curPage - 1) * curDelta;
			int end = begin + curDelta;
			String paddedZip = String.format("%05d", zipCode);

			maxUserCount = 
					UserAddressJoinerServiceUtil.countUsersAtZip(paddedZip);
			listOfUsers = UserAddressJoinerServiceUtil.findUsersAtZip(
					paddedZip, begin, end) ;
		} catch (Exception e){
			maxUserCount = 0;
			listOfUsers = new ArrayList<User>();
			
		}
//		try {
//			listOfUsers = 
//				_getUsersForZip(zipCode, curPage, curDelta);
//		} catch (SystemException e) {
//			SessionErrors.add(eventRequest, "unable-to-query-error");
//			listOfUsers = new ArrayList<User>();
//		}

		eventRequest.setAttribute("userEntries", listOfUsers);
		eventRequest.setAttribute("entryCount", maxUserCount);
	}

	private Tuple _queryForUsersByZip(int begin, int end, String paddedZip) {
		Thread currentThread = Thread.currentThread();
		ClassLoader curContextCL = 
			currentThread.getContextClassLoader();
		
		try {
//			currentThread.setContextClassLoader(
//				PortalClassLoaderUtil.getClassLoader());
			
		} catch (Exception e){
			e.printStackTrace();
		}finally {
//			currentThread.setContextClassLoader(curContextCL);
		}
		
		return new Tuple(new ArrayList<User>(), new Integer(0));
	}

	@SuppressWarnings("unchecked")
	private List<User> _getUsersForZip(
			Integer zipCode, int page, int delta) throws SystemException {
		
		try {
			
			// Users potentially may have multiple addresses in 
			// the same zip code
			
			DynamicQuery addressDynamicQuery = 
					_getQueryForUserIdsFromZip(zipCode);
				
			int begin = (page - 1) * delta;
			int end = begin + delta;
			
			List<Long> listOfUserIds = 
				AddressLocalServiceUtil.dynamicQuery(
					addressDynamicQuery, begin, end);
			
			// Use of 'in' may have limitations? (query size limits, etc)
			
			DynamicQuery userDynamicQuery = DynamicQueryFactoryUtil
				.forClass(User.class)
				.add(PropertyFactoryUtil.forName("userId").in(listOfUserIds));
			
			return UserLocalServiceUtil.dynamicQuery(userDynamicQuery);
		} catch (SystemException e) {
			throw e;
		}
	}

	private static DynamicQuery _getQueryForUserIdsFromZip(Integer zipCode) {
		
		String zeroPaddedZip = String.format("%05d", zipCode);
		
		DynamicQuery dq = DynamicQueryFactoryUtil.forClass(Address.class)
			.add(PropertyFactoryUtil.forName("zip")
			.eq(zeroPaddedZip))
			.setProjection(
				ProjectionFactoryUtil.distinct(
					PropertyFactoryUtil.forName("userId")));
		
		return dq;
	}

}
