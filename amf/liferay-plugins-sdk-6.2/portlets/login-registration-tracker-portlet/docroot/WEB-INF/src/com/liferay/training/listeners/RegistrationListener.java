package com.liferay.training.listeners;
import java.util.Date;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.ModelListenerException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.BaseModelListener;
import com.liferay.portal.model.User;
import com.liferay.training.LoginRegistrationConstants;
import com.liferay.training.service.builder.model.TrackerEntry;
import com.liferay.training.service.builder.service.TrackerEntryLocalServiceUtil;

public class RegistrationListener extends BaseModelListener<User> {

	@Override
	public void onAfterCreate(User user) throws ModelListenerException {
		try {
			long tId = CounterLocalServiceUtil.increment(
				TrackerEntry.class.getName());
			_log.debug("Generated PK for new Track Entry");
			Date now = new Date();
			TrackerEntry tEntry =
				TrackerEntryLocalServiceUtil.createTrackerEntry(tId);
			_log.debug("About to retrieve User UUID");
			tEntry.setUserUuid(user.getUserUuid());
			tEntry.setUserName(user.getScreenName());
			tEntry.setTrackerEntryId(tId);
			tEntry.setEventDate(now);
			tEntry.setEventType(LoginRegistrationConstants.LOGIN_EVENT_TYPE);
			tEntry.setIpAddress("0.0.0.0");
		} catch (SystemException e) {
			_log.error(
				"Unable to create TrackerEntry: " + e.getLocalizedMessage());
		}
	}

	private Log _log = LogFactoryUtil.getLog(
		RegistrationListener.class.getName());
}
