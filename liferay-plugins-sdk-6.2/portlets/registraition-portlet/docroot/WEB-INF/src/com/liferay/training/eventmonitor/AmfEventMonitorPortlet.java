package com.liferay.training.eventmonitor;

import static com.liferay.training.eventmonitor.AmfEventMonitorConstants.*;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.training.service.builder.model.TrackerEntry;
import com.liferay.training.service.builder.service.TrackerEntryServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class AmfEventMonitorPortlet
 */
public class AmfEventMonitorPortlet extends MVCPortlet {

	@Override
	public void doView(
		RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest
					.getAttribute(WebKeys.THEME_DISPLAY);
			Locale loc = themeDisplay.getLocale();
			
			loc = loc == null?LocaleUtil.getDefault():loc;
			
			// Retrieve the page parameters
			
			String allString = LanguageUtil.get(loc, LAN_KEY_ALL);
			String regisString = LanguageUtil.get(loc, LAN_KEY_REGIS);
			String loginString = LanguageUtil.get(loc, LAN_KEY_LOGIN);

			String curTabValue = ParamUtil.getString(
				renderRequest, CURTAB_PARAM_NAME, allString);
			Integer aCurPage = ParamUtil.getInteger(
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
			
			long groupId = themeDisplay.getSiteGroupId();
			
			if (_log.isDebugEnabled()){
				StringBuilder message = new StringBuilder(
					"AmfEventMonitorPortlet doView "
					+ "processing with parameters: [");
				
				message.append(CURTAB_PARAM_NAME).append(": ");
				message.append(curTabValue).append(',');
				
				message.append(LAN_KEY_ALL).append(": ");
				message.append(allString).append(',');
				
				message.append(ALL_CUR_PARAM_NAME).append(": ");
				message.append(aCurPage).append(',');
				
				message.append(PAGE_DELTA_ALL_PARAM).append(": ");
				message.append(allDelta);
				
				message.append(LAN_KEY_REGIS).append(": ");
				message.append(regisString).append(',');
				
				message.append(REGIS_CUR_PARAM_NAME).append(": ");
				message.append(rCurPage).append(',');
				
				message.append(PAGE_DELTA_REGIS_PARAM).append(": ");
				message.append(rDelta).append(',');
				
				message.append(LAN_KEY_LOGIN).append(": ");
				message.append(loginString).append(',');
				
				message.append(LOGIN_CUR_PARAM_NAME).append(": ");
				message.append(lCurPage).append(',');
				
				message.append(PAGE_DELTA_LOGIN_PARAM).append(": ");
				message.append(lDelta).append(',');
				
				message.append("]");
				
				_log.debug(message);
			}
			
			int allStart = (aCurPage - 1) * allDelta;
			int allEnd = allStart + allDelta;
			List<TrackerEntry> curPageEntries = 
				TrackerEntryServiceUtil.getTrackerEntries(
					allStart, allEnd, groupId);
			renderRequest.setAttribute(
				ALL_TRACKER_ENTRIES_ATTR, curPageEntries);

			int regStart = (rCurPage - 1) * rDelta;
			int regEnd = regStart + rDelta;
			List<TrackerEntry> curPageRegisEntries = 
				TrackerEntryServiceUtil.findByEventType(
					REGIS_EVENT_TYPE, regStart, regEnd, groupId);
			renderRequest.setAttribute(
				REGIS_TRACKER_ENTRIES_ATTR, curPageRegisEntries);
			
			int logStart = (lCurPage - 1) * lDelta;
			int logEnd = logStart + lDelta;
			renderRequest.setAttribute(
				LOGIN_TRACKER_ENTRIES_ATTR,
				TrackerEntryServiceUtil.findByEventType(
						LOGIN_EVENT_TYPE, logStart, logEnd, groupId));

			int allCount = 
				TrackerEntryServiceUtil.getTrackerEntriesCount(groupId);
			renderRequest.setAttribute(SEARCH_ENTRY_ALL_COUNT_ATTR, allCount);
			renderRequest.setAttribute(
				SEARCH_ENTRY_LOGIN_COUNT_ATTR,
				TrackerEntryServiceUtil.countByEventType(
						LOGIN_EVENT_TYPE, groupId));

			renderRequest.setAttribute(
				SEARCH_ENTRY_REGIS_COUNT_ATTR, 
				TrackerEntryServiceUtil.countByEventType(
						REGIS_EVENT_TYPE, groupId));
			renderRequest.setAttribute(LOGIN_CUR_PARAM_NAME, lCurPage);
			renderRequest.setAttribute(REGIS_CUR_PARAM_NAME, rCurPage);
			renderRequest.setAttribute(
				SEARCH_CON_ADELTA_ATTR, allDelta.intValue());
			renderRequest.setAttribute(
				SEARCH_CON_RDELTA_ATTR, rDelta.intValue());
			renderRequest.setAttribute(
				SEARCH_CON_LDELTA_ATTR, lDelta.intValue());
			
			renderRequest.setAttribute(CURTAB_ATTR, curTabValue);
			
			PortletURL portletUrl = renderResponse.createRenderURL();
			
			portletUrl.setParameter(ALL_CUR_PARAM_NAME, aCurPage.toString());
			portletUrl.setParameter(REGIS_CUR_PARAM_NAME, rCurPage.toString());
			portletUrl.setParameter(LOGIN_CUR_PARAM_NAME, lCurPage.toString());
			portletUrl.setParameter(PAGE_DELTA_ALL_PARAM, allDelta.toString());
			portletUrl.setParameter(PAGE_DELTA_REGIS_PARAM, rDelta.toString());
			portletUrl.setParameter(PAGE_DELTA_LOGIN_PARAM, lDelta.toString());
			portletUrl.setParameter(CURTAB_PARAM_NAME, curTabValue);
			
			renderRequest.setAttribute(ITER_OBJ_ATTR, portletUrl);
			renderRequest.setAttribute(URL_STRING_ATTR, portletUrl.toString());
			renderRequest.setAttribute(URL_STRING_ATTR, portletUrl.toString());
			
			StringBuilder tabsCsl = new StringBuilder(allString);
			tabsCsl.append(',');
			tabsCsl.append(regisString);
			tabsCsl.append(',');
			tabsCsl.append(loginString);
			
			renderRequest.setAttribute(TABS_CSL_ATTR, tabsCsl);
			
		} catch (SystemException e) {
			throw new PortletException(
					"Unable to render portet: " + e.getLocalizedMessage());
		} catch (PortalException e) {
			throw new PortletException(
					"Unable to render portet: " + e.getLocalizedMessage());
		}
		super.doView(renderRequest, renderResponse);
	}
	
	private Log _log = LogFactoryUtil.getLog(AmfEventMonitorPortlet.class);

}
