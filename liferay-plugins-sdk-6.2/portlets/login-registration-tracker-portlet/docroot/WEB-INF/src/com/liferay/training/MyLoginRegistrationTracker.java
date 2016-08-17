package com.liferay.training;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.training.service.builder.model.TrackerEntry;
import com.liferay.training.service.builder.service.TrackerEntryLocalServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import static com.liferay.training.LoginRegistrationConstants.*;

/**
 * Portlet implementation class MyLoginRegistrationTracker
 */
public class MyLoginRegistrationTracker extends MVCPortlet {
	
	@Override
	public void doView(
		RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest
					.getAttribute(WebKeys.THEME_DISPLAY);
			Locale loc = themeDisplay.getLocale();
			
			loc = loc == null?LocaleUtil.getDefault():loc;
			
			long scopeGroupId = themeDisplay.getScopeGroupId();
			String allString = LanguageUtil.get(loc, LAN_KEY_ALL);
			String regisString = LanguageUtil.get(loc, LAN_KEY_REGIS);
			String loginString = LanguageUtil.get(loc, LAN_KEY_LOGIN);
			StringBuilder tabsCsl = new StringBuilder(allString).append(',')
					.append(regisString).append(',').append(loginString);
			PermissionChecker permissionChecker =
				    themeDisplay.getPermissionChecker();
			
//			if (permissionChecker.hasPermission(groupId, name, primKey, actionId)){} //TODO

			String curTabValue = ParamUtil.getString(
				renderRequest, CURTAB_PARAM_NAME, allString);
			Integer curPage = ParamUtil.getInteger(
				renderRequest, ALL_CUR_PARAM_NAME, 1);
			Integer rCurPage = ParamUtil.getInteger(
				renderRequest, REGIS_CUR_PARAM_NAME, 1);
			Integer lCurPage = ParamUtil.getInteger(
				renderRequest, LOGIN_CUR_PARAM_NAME, 1);
			Integer allDelta = ParamUtil.getInteger(
				renderRequest, PAGE_DELTA_ALL_PARAM, PAGE_DELTA);
			Integer rDelta = ParamUtil.getInteger(
				renderRequest, PAGE_DELTA_REGIS_PARAM, PAGE_DELTA);
			Integer lDelta = ParamUtil.getInteger(
				renderRequest, PAGE_DELTA_LOGIN_PARAM, PAGE_DELTA);
			
			PortletURL tabs1URL = renderResponse.createRenderURL();
			
			tabs1URL.setParameter(ALL_CUR_PARAM_NAME, curPage.toString());
			tabs1URL.setParameter(REGIS_CUR_PARAM_NAME, rCurPage.toString());
			tabs1URL.setParameter(LOGIN_CUR_PARAM_NAME, lCurPage.toString());
			tabs1URL.setParameter(PAGE_DELTA_ALL_PARAM, allDelta.toString());
			tabs1URL.setParameter(PAGE_DELTA_REGIS_PARAM, rDelta.toString());
			tabs1URL.setParameter(PAGE_DELTA_LOGIN_PARAM, lDelta.toString());
			tabs1URL.setParameter(CURTAB_PARAM_NAME, curTabValue);
			
			curPage--;
			rCurPage--;
			lCurPage--;
			
			int allCount = 
				TrackerEntryLocalServiceUtil.getTrackerEntriesCount();
			int loginCount = 
				TrackerEntryLocalServiceUtil.countByEventType(LOGIN_EVENT_TYPE);
			int regisCount = 
				TrackerEntryLocalServiceUtil.countByEventType(REGIS_EVENT_TYPE);
			int start = curPage * allDelta, end = start + allDelta;
			int rstart = rCurPage * rDelta, rend = rstart + rDelta;
			int lstart = lCurPage * lDelta, lend = lstart + lDelta;
			List<TrackerEntry> curPageEntries = 
				TrackerEntryLocalServiceUtil.getTrackerEntries(start, end);
			List<TrackerEntry> curPageLoginEntries = 
				TrackerEntryLocalServiceUtil.findByEventType(
					LOGIN_EVENT_TYPE, lstart, lend);
			List<TrackerEntry> curPageRegisEntries = 
				TrackerEntryLocalServiceUtil.findByEventType(
					REGIS_EVENT_TYPE, rstart, rend);
			
			renderRequest.setAttribute(
				ALL_TRACKER_ENTRIES_ATTR, curPageEntries);
			renderRequest.setAttribute(SEARCH_ENTRY_ALL_COUNT_ATTR, allCount);
			
			renderRequest.setAttribute(
				LOGIN_TRACKER_ENTRIES_ATTR, curPageLoginEntries);
			renderRequest.setAttribute(
				LOGIN_CUR_PARAM_NAME, lCurPage);
			renderRequest.setAttribute(
				SEARCH_ENTRY_LOGIN_COUNT_ATTR, loginCount);
			
			renderRequest.setAttribute(
				REGIS_TRACKER_ENTRIES_ATTR, curPageRegisEntries);
			renderRequest.setAttribute(
				REGIS_CUR_PARAM_NAME, rCurPage);
			renderRequest.setAttribute(
				SEARCH_ENTRY_REGIS_COUNT_ATTR, regisCount);
			
			renderRequest.setAttribute(
				SEARCH_CON_ADELTA_ATTR, allDelta.intValue());
			renderRequest.setAttribute(
				SEARCH_CON_RDELTA_ATTR, rDelta.intValue());
			renderRequest.setAttribute(
				SEARCH_CON_LDELTA_ATTR, lDelta.intValue());
			
			renderRequest.setAttribute(CURTAB_ATTR, curTabValue);
			renderRequest.setAttribute(ITER_OBJ_ATTR, tabs1URL);
			renderRequest.setAttribute(URL_STRING_ATTR, tabs1URL.toString());
			renderRequest.setAttribute(URL_STRING_ATTR, tabs1URL.toString());
			renderRequest.setAttribute(TABS_CSL_ATTR, tabsCsl);
			
		} catch (SystemException e) {
			throw new PortletException(
					"Unable to render portet: " + e.getLocalizedMessage());
		}
		super.doView(renderRequest, renderResponse);
	}

}
