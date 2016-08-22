package com.liferay.training.eventmonitor;

import static com.liferay.training.eventmonitor.AmfEventMonitorConstants.*;

import java.io.IOException;
import java.util.ArrayList;
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
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.permission.PortletPermissionUtil;
import com.liferay.portal.theme.PortletDisplay;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.training.service.builder.model.TrackerEntry;
import com.liferay.training.service.builder.service.TrackerEntryLocalServiceUtil;
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
			
			String allString = LanguageUtil.get(loc, LAN_KEY_ALL);
			String regisString = LanguageUtil.get(loc, LAN_KEY_REGIS);
			String loginString = LanguageUtil.get(loc, LAN_KEY_LOGIN);
			StringBuilder tabsCsl = new StringBuilder(allString).append(',')
					.append(regisString).append(',').append(loginString);
			PermissionChecker permissionChecker =
				    themeDisplay.getPermissionChecker();

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
			
			//uninitialized on purpose
			
			int allCount;
			int loginCount;
			int regisCount;
			int start, end;
			int rstart, rend;
			int lstart, lend;
			List<TrackerEntry> curPageEntries;
			List<TrackerEntry> curPageLoginEntries;
			List<TrackerEntry> curPageRegisEntries;
			PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

			if (PortletPermissionUtil.contains(
					permissionChecker, themeDisplay.getPlid(),
					portletDisplay.getRootPortletId(), AmfMonitorKey.VIEW_ALL)){

				allCount = 
					TrackerEntryLocalServiceUtil.getTrackerEntriesCount();
				loginCount = 
					TrackerEntryLocalServiceUtil.countByEventType(
						LOGIN_EVENT_TYPE);
				regisCount = 
					TrackerEntryLocalServiceUtil.countByEventType(
						REGIS_EVENT_TYPE);
				start = curPage * allDelta;
				end = start + allDelta;
				rstart = rCurPage * rDelta;
				rend = rstart + rDelta;
				lstart = lCurPage * lDelta;
				lend = lstart + lDelta;
				curPageEntries = 
					TrackerEntryLocalServiceUtil.getTrackerEntries(start, end);
				curPageLoginEntries = 
					TrackerEntryLocalServiceUtil.findByEventType(
						LOGIN_EVENT_TYPE, lstart, lend);
				curPageRegisEntries = 
					TrackerEntryLocalServiceUtil.findByEventType(
						REGIS_EVENT_TYPE, rstart, rend);
			} else if (PortletPermissionUtil.contains(
				permissionChecker, themeDisplay.getPlid(),
				portletDisplay.getRootPortletId(), AmfMonitorKey.VIEW_SELF)){

				User curUser = themeDisplay.getUser();
				long userId = -1;
				
				if (curUser != null){
					userId = curUser.getUserId();
				} else {
					try {
						userId = themeDisplay.getDefaultUserId();
					} catch (PortalException e) {
						_log.error("Unable to retrieve user "
								+ "id with VIEW_ALL_ENTRIES permission.");

						// now sure of best practice here
						
						throw new PortletException(e);
					}
				}
				
				allCount = 
					TrackerEntryLocalServiceUtil.countByUserId(userId);
				loginCount = 
					TrackerEntryLocalServiceUtil.countByUserIdAndEventType(
						userId, LOGIN_EVENT_TYPE);
				regisCount = 
					TrackerEntryLocalServiceUtil.countByUserIdAndEventType(
						userId, REGIS_EVENT_TYPE);
				start = curPage * allDelta;
				end = start + allDelta;
				rstart = rCurPage * rDelta;
				rend = rstart + rDelta;
				lstart = lCurPage * lDelta;
				lend = lstart + lDelta;
				curPageEntries = 
					TrackerEntryLocalServiceUtil.findByUserId(
							userId, start, end);
				curPageLoginEntries = 
					TrackerEntryLocalServiceUtil.findByUserIdAndEventType(
						userId, LOGIN_EVENT_TYPE, lstart, lend);
				curPageRegisEntries = 
					TrackerEntryLocalServiceUtil.findByUserIdAndEventType(
						userId, REGIS_EVENT_TYPE, rstart, rend);
			}else {
				allCount = 0;
				loginCount = 0;
				regisCount = 0;
				start = 0;
				end = 0;
				rstart = 0;
				rend = 0;
				lstart = 0;
				lend = 0;

				curPageEntries = new ArrayList<TrackerEntry>();
				curPageLoginEntries = new ArrayList<TrackerEntry>();
				curPageRegisEntries = new ArrayList<TrackerEntry>();
			}
			
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
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.doView(renderRequest, renderResponse);
	}
	
	private Log _log = LogFactoryUtil.getLog(AmfEventMonitorPortlet.class);

}
