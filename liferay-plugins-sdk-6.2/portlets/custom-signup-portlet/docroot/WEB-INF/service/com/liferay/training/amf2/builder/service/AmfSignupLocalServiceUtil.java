/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.training.amf2.builder.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for AmfSignup. This utility wraps
 * {@link com.liferay.training.amf2.builder.service.impl.AmfSignupLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Shanon Mathai
 * @see AmfSignupLocalService
 * @see com.liferay.training.amf2.builder.service.base.AmfSignupLocalServiceBaseImpl
 * @see com.liferay.training.amf2.builder.service.impl.AmfSignupLocalServiceImpl
 * @generated
 */
public class AmfSignupLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.training.amf2.builder.service.impl.AmfSignupLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static java.util.List<java.lang.String> addUserWithAddressAndPhones(
		long creatorUserId, long companyId, boolean autoPassword,
		java.lang.String password1, java.lang.String password2,
		boolean autoScreenName, java.lang.String screenName,
		java.lang.String emailAddress, java.util.Locale locale,
		java.lang.String firstName, java.lang.String lastName, boolean male,
		int birthdayMonth, int birthdayDay, int birthdayYear,
		java.lang.String street1, java.lang.String street2,
		java.lang.String city, java.lang.String zip, long regionId,
		java.lang.String homeNumber, java.lang.String mobileNumber,
		java.lang.String securityQuestionKey, java.lang.String secuirtyAnswer,
		boolean atou, com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addUserWithAddressAndPhones(creatorUserId, companyId,
			autoPassword, password1, password2, autoScreenName, screenName,
			emailAddress, locale, firstName, lastName, male, birthdayMonth,
			birthdayDay, birthdayYear, street1, street2, city, zip, regionId,
			homeNumber, mobileNumber, securityQuestionKey, secuirtyAnswer,
			atou, serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static AmfSignupLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					AmfSignupLocalService.class.getName());

			if (invokableLocalService instanceof AmfSignupLocalService) {
				_service = (AmfSignupLocalService)invokableLocalService;
			}
			else {
				_service = new AmfSignupLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(AmfSignupLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(AmfSignupLocalService service) {
	}

	private static AmfSignupLocalService _service;
}