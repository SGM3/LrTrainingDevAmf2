package com.liferay.training;

import java.io.IOException;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.training.service.builder.model.TrackerEntry;
import com.liferay.training.service.builder.service.TrackerEntryLocalServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import static com.liferay.training.LoginRegistrationConstants.*;

/**
 * Portlet implementation class MyLoginRegistrationTracker
 */
public class MyLoginRegistrationTracker extends MVCPortlet {
	
	public static final int PAGE_DELTA = 2;
	public static final String CUR_PARAM_NAME = "cur";
	public static final String DELTA_PARAM_NAME = "delta";
	
	
	@Override
	public void doView(
		RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			int curPageParamAllTab = ParamUtil.get(
					renderRequest, CUR_PARAM_NAME, 1);
			int curPageParamRegisTab = ParamUtil.get(
					renderRequest, REGIS_CUR_PARAM_NAME, 1);
			int curPageParamLoginTab = ParamUtil.get(
					renderRequest, LOGIN_CUR_PARAM_NAME, 1);


			String tabs1 = ParamUtil.getString(renderRequest, "curTab", "All");
			Integer curPage = ParamUtil.getInteger(renderRequest, "cur", 1);
			Integer rCurPage = ParamUtil.getInteger(renderRequest, "rcur", 1);
			Integer lCurPage = ParamUtil.getInteger(renderRequest, "lcur", 1);
			PortletURL tabs1URL = renderResponse.createRenderURL();
			tabs1URL.setParameter("cur", curPage.toString());
			tabs1URL.setParameter("rcur", rCurPage.toString());
			tabs1URL.setParameter("lcur", lCurPage.toString());
			tabs1URL.setParameter("curTab", tabs1);
			
			String curTabValue = (String) ParamUtil.get(
					renderRequest, "curTab", "All");
			
			curTabValue = curTabValue==null?"All":curTabValue;
			//renderRequest.setAttribute("curTab", curTabValue);
			
			int delta = ParamUtil.get(
					renderRequest, DELTA_PARAM_NAME, PAGE_DELTA);
			//TODO byUser entry
			curPageParamAllTab--;
			curPageParamRegisTab--;
			curPageParamLoginTab--;
			
			int allCount = 
				TrackerEntryLocalServiceUtil.getTrackerEntriesCount();
			int loginCount = 
				TrackerEntryLocalServiceUtil.countByEventType(LOGIN_EVENT_TYPE);
			int regisCount = 
				TrackerEntryLocalServiceUtil.countByEventType(REGIS_EVENT_TYPE);
			int start = curPageParamAllTab * delta, end = start + delta;
			int rstart = curPageParamLoginTab * delta, rend = rstart + delta;
			int lstart = curPageParamRegisTab * delta, lend = lstart + delta;
			List<TrackerEntry> curPageEntries = 
				TrackerEntryLocalServiceUtil.getTrackerEntries(start, end);
			List<TrackerEntry> curPageLoginEntries = 
				TrackerEntryLocalServiceUtil.findByEventType(
					LOGIN_EVENT_TYPE, lstart + 1, lend + 1);//TODO remove +1's after testing
			loginCount--;//TODO remove after testing
			List<TrackerEntry> curPageRegisEntries = 
				TrackerEntryLocalServiceUtil.findByEventType(
					REGIS_EVENT_TYPE, rstart, rend);
			
			renderRequest.setAttribute(ALL_TRACKER_ENTRIES_ATTR, curPageEntries);
			renderRequest.setAttribute(SEARCH_ENTRY_ALL_COUNT_ATTR, allCount);
			
			renderRequest.setAttribute(LOGIN_TRACKER_ENTRIES_ATTR, curPageLoginEntries);
			renderRequest.setAttribute(LOGIN_CUR_PARAM_NAME, curPageParamLoginTab);
			renderRequest.setAttribute(SEARCH_ENTRY_LOGIN_COUNT_ATTR, loginCount);
			
			renderRequest.setAttribute(REGIS_TRACKER_ENTRIES_ATTR, curPageRegisEntries);
			renderRequest.setAttribute(REGIS_CUR_PARAM_NAME, curPageParamRegisTab);
			renderRequest.setAttribute(SEARCH_ENTRY_REGIS_COUNT_ATTR, regisCount);
			
			renderRequest.setAttribute("curTab", tabs1);
			renderRequest.setAttribute("iterUrlObj", tabs1URL);
			renderRequest.setAttribute("tabsUrl", tabs1URL.toString());
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		renderRequest.setAttribute(SEARCH_CON_DELTA_ATTR, PAGE_DELTA);
		super.doView(renderRequest, renderResponse);
	}

}
