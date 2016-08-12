package com.liferay.training;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.training.service.builder.model.TrackerEntry;
import com.liferay.training.service.builder.service.TrackerEntryLocalServiceUtil;

public class PostLoginAction extends com.liferay.portal.kernel.events.Action {
	
	public PostLoginAction() {
		super();
	}
	
	@Override
	public void run(HttpServletRequest request, HttpServletResponse response) throws ActionException {
		// TODO Auto-generated method stub
		Date now;
		long tId = 0L;
		long groupId;
		TrackerEntry tEntry;
		ThemeDisplay themeDisplay;
		PermissionChecker permChecker;

		now = new Date();
		themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		groupId = themeDisplay.getScopeGroupId();
		permChecker = themeDisplay.getPermissionChecker();
		
		try {
			tId = CounterLocalServiceUtil.increment(TrackerEntry.class.getName());
		} catch (SystemException e) {
			e.printStackTrace();
		}
		tEntry = TrackerEntryLocalServiceUtil.createTrackerEntry(tId);
		tEntry.setTrackerEntryId(tId);
		tEntry.setEventDate(now);
		tEntry.setEventType(LoginRegistrationConstants.LOGIN_EVENT_TYPE);
		
		tEntry.setUserUuid(null);
		tEntry.setUserName(null);
		

		System.out.println("Hit postlogin with params:");
		System.out.println(request);
		System.out.println(response);
		
//		TrackerEntryLocalServiceUtil.addTrackerEntry(tEntry)
	}
	
	private Log _log = LogFactoryUtil.getLog(PostLoginAction.class.getName());

}