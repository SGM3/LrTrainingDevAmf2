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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.service.BaseLocalService;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service interface for AmfSignup. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Shanon Mathai
 * @see AmfSignupLocalServiceUtil
 * @see com.liferay.training.amf2.builder.service.base.AmfSignupLocalServiceBaseImpl
 * @see com.liferay.training.amf2.builder.service.impl.AmfSignupLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface AmfSignupLocalService extends BaseLocalService,
	InvokableLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AmfSignupLocalServiceUtil} to access the amf signup local service. Add custom service methods to {@link com.liferay.training.amf2.builder.service.impl.AmfSignupLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier();

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier);

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable;

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
			com.liferay.portal.kernel.exception.SystemException;
}