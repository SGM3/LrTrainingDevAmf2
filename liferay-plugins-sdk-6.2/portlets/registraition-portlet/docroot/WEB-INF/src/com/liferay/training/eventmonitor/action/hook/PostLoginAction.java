package com.liferay.training.eventmonitor.action.hook;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.training.eventmonitor.AmfEventMonitorConstants;
import com.liferay.training.service.builder.model.TrackerEntry;
import com.liferay.training.service.builder.service.TrackerEntryLocalServiceUtil;

public class PostLoginAction extends com.liferay.portal.kernel.events.Action {
	
	public PostLoginAction() {
		super();
	}
	
	@Override
	public void run(
			HttpServletRequest request, HttpServletResponse response)
					throws ActionException {
		Date now;
		long tId = 0L;
		TrackerEntry tEntry;

		now = new Date();
		HttpSession session = request.getSession(false);
		User curUser;
		curUser = (User) session.getAttribute("USER");
		
		if (curUser != null) {
			
			tEntry = TrackerEntryLocalServiceUtil.createTrackerEntry(tId);
			try {
				tId = CounterLocalServiceUtil.increment(
						TrackerEntry.class.getName());
				tEntry.setUserUuid(curUser.getUserUuid());
				tEntry.setUserName(curUser.getScreenName());
				tEntry.setUserId(curUser.getUserId());
				tEntry.setTrackerEntryId(tId);
				tEntry.setEventDate(now);
				tEntry.setEventType(
					AmfEventMonitorConstants.LOGIN_EVENT_TYPE);
				tEntry.setIpAddress(request.getRemoteAddr());
				
				TrackerEntryLocalServiceUtil.addTrackerEntry(
					tEntry);
			} catch (SystemException e) {
				_log.error(
					"Unable to create Tracker Entry: " 
					+ e.getLocalizedMessage());
			}
		}
	}
	
	private Log _log = LogFactoryUtil.getLog(PostLoginAction.class.getName());

}