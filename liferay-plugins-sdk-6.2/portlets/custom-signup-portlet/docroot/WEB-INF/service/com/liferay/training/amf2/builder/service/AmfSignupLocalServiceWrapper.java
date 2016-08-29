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

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AmfSignupLocalService}.
 *
 * @author Shanon Mathai
 * @see AmfSignupLocalService
 * @generated
 */
public class AmfSignupLocalServiceWrapper implements AmfSignupLocalService,
	ServiceWrapper<AmfSignupLocalService> {
	public AmfSignupLocalServiceWrapper(
		AmfSignupLocalService amfSignupLocalService) {
		_amfSignupLocalService = amfSignupLocalService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _amfSignupLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_amfSignupLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _amfSignupLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public java.util.List<java.lang.String> addUserWithAddressAndPhones(
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
		return _amfSignupLocalService.addUserWithAddressAndPhones(creatorUserId,
			companyId, autoPassword, password1, password2, autoScreenName,
			screenName, emailAddress, locale, firstName, lastName, male,
			birthdayMonth, birthdayDay, birthdayYear, street1, street2, city,
			zip, regionId, homeNumber, mobileNumber, securityQuestionKey,
			secuirtyAnswer, atou, serviceContext);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public AmfSignupLocalService getWrappedAmfSignupLocalService() {
		return _amfSignupLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedAmfSignupLocalService(
		AmfSignupLocalService amfSignupLocalService) {
		_amfSignupLocalService = amfSignupLocalService;
	}

	@Override
	public AmfSignupLocalService getWrappedService() {
		return _amfSignupLocalService;
	}

	@Override
	public void setWrappedService(AmfSignupLocalService amfSignupLocalService) {
		_amfSignupLocalService = amfSignupLocalService;
	}

	private AmfSignupLocalService _amfSignupLocalService;
}