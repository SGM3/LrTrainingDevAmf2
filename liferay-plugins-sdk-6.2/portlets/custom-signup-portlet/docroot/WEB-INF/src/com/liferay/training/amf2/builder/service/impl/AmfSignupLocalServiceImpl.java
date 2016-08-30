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

package com.liferay.training.amf2.builder.service.impl;

import java.util.List;
import java.util.Locale;

import javax.portlet.PortletException;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Contact;
import com.liferay.portal.model.ListType;
import com.liferay.portal.model.ListTypeConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ListTypeServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.training.amf2.builder.service.base.AmfSignupLocalServiceBaseImpl;
import com.liferay.training.amf2.parameter.validator.SignupValidator;
import com.liferay.training.amf2.util.MyAmfUtil;

/**
 * The implementation of the amf signup local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.training.amf2.builder.service.AmfSignupLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Shanon Mathai
 * @see com.liferay.training.amf2.builder.service.base.AmfSignupLocalServiceBaseImpl
 * @see com.liferay.training.amf2.builder.service.AmfSignupLocalServiceUtil
 */
public class AmfSignupLocalServiceImpl extends AmfSignupLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.liferay.training.amf2.builder.service.AmfSignupLocalServiceUtil} to access the amf signup local service.
	 */
	
	public List<String> addUserWithAddressAndPhones(
			long creatorUserId, long companyId, boolean autoPassword,
			String password1, String password2, boolean autoScreenName,
			String screenName, String emailAddress, Locale locale,
			String firstName, String lastName, boolean male, 
			int birthdayMonth, int birthdayDay, int birthdayYear,
			String street1, String street2, String city,
			String zip, long regionId, String homeNumber, String mobileNumber, 
			String securityQuestionKey, String secuirtyAnswer, boolean atou,
			ServiceContext serviceContext) 
		throws PortalException, SystemException{
		
		SignupValidator validator = new SignupValidator();
		
		try {
			validator.validateFormAndListErrors(password1, password2,
				screenName, emailAddress, locale, firstName, lastName,
				male, birthdayMonth, birthdayDay, birthdayYear, street1,
				street2, city, zip, regionId, homeNumber, mobileNumber,
				securityQuestionKey, secuirtyAnswer, atou);
			
		} catch (PortletException e) {
			throw new PortalException(e);
		}
		
		List<String> errorsList = validator.getErrors();
		
		if (!errorsList.isEmpty()){
			return errorsList;
		}
		
		User newUser = userLocalService.addUser(
			creatorUserId, companyId, autoPassword, password1, password2,
			false, screenName, emailAddress, 0L, null,
			locale, firstName, null, lastName, 0, 0,
			male, birthdayMonth, birthdayDay, birthdayYear, null,
			null, null, null, null, true, serviceContext);
		
		String className = Contact.class.getName();

		long classPK = newUser.getContactId();
		long userId = newUser.getUserId();
		
		// Prerequisite is it will be US based
		
		long countryId = MyAmfUtil.getUsaCountryCode(); 
		
		int addressPersonalTypeId = MyAmfUtil.getPersonalAdressTypeId();
		int homePhoneTypeId = MyAmfUtil.getHomePhoneTypeId();
		int mobilePhoneTypeId = MyAmfUtil.getMobilePhoneTypeId();
		
		// we pass a clone since the service call 
		// may or may not dirty the context 
		
		addressLocalService.addAddress(userId, className, classPK, street1,
			street2, "", city, zip, regionId, countryId, addressPersonalTypeId, 
			false, true, (ServiceContext) serviceContext.clone());
		
		if (Validator.isNotNull(homeNumber)){
			phoneLocalService.addPhone(userId, className, classPK, homeNumber,
				null, homePhoneTypeId, true, 
				(ServiceContext) serviceContext.clone());
		}
		
		if (Validator.isNotNull(mobileNumber)){
			phoneLocalService.addPhone(userId, className, classPK, homeNumber,
				null, mobilePhoneTypeId, true,
				(ServiceContext) serviceContext.clone());
		}
		throw new SystemException("Unimplemented service");
	}
}