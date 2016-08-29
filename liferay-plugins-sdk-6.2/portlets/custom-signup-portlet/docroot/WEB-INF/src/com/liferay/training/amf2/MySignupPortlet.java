package com.liferay.training.amf2;

import java.io.IOException;
import java.util.List;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Region;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.training.amf2.builder.service.AmfSignupLocalServiceUtil;
import com.liferay.training.amf2.constants.MySignupConstants;
import com.liferay.training.amf2.parameter.handler.SignupParamHolder;
import com.liferay.training.amf2.parameter.handler.impl.AmfExcerciseSignupParamHolderImpl;
import com.liferay.training.amf2.parameter.validator.SignupValidator;
import com.liferay.training.amf2.util.MyAmfUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import static com.liferay.training.amf2.constants.MySignupConstants.*;

/**
 * Portlet implementation class MySignupPortlet
 */
public class MySignupPortlet extends MVCPortlet {
	
	@Override
	public void doView(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {
		try {
			List<Region> regs = MyAmfUtil.getCountryRegions(19);
			renderRequest.setAttribute(US_REG_CODES_ATTR, regs.toArray());
			super.doView(renderRequest, renderResponse);
		} catch (SystemException e) {
			_log.error("Unable to get available region codes");
			throw new PortletException(e);
		}
	}
	
	@Override
	public void processAction(ActionRequest request, ActionResponse response)
			throws IOException, PortletException {

			SignupParamHolder extractor =
				new AmfExcerciseSignupParamHolderImpl(request);
			
			if (extractor.isSignedIn()){
				response.setPortletMode(PortletMode.VIEW);
				return;
			}
			
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(
					WebKeys.THEME_DISPLAY);
			
			List<String> errorMessages = 
				_signupValidator.validateFormAndListErrors(
					themeDisplay, extractor);
			

			if (errorMessages.isEmpty()) { 
				
				//at this point we can begin the transaction
				
				try {
					
					try {
						boolean isMale = 
							extractor.getGender().equalsIgnoreCase(
								MySignupConstants.MALE_STRING_VALUE);
						int bm = Integer.parseInt(extractor.getBirthdayMonth());
						int bd = Integer.parseInt(extractor.getBirthdayDay());
						int by = Integer.parseInt(extractor.getBirthdayYear());
						
						ServiceContext serviceContext = 
							ServiceContextFactory.getInstance(request);
						
						// Pass new service context to each call since each call
						// might dirty the context
						
//						MyAmfUtil
						long regionCode = 
							Integer.parseInt(extractor.getState());
						
						AmfSignupLocalServiceUtil.addUserWithAddressAndPhones(
							themeDisplay.getUserId(), 
							themeDisplay.getCompanyId(), false,
							extractor.getPassword1(), extractor.getPassword2(),
							false, extractor.getUsername(),
							extractor.getEmailAddress(),
							themeDisplay.getLocale(), extractor.getFirstName(),
							extractor.getLastName(), isMale, bm, bd, by,
							extractor.getStreetAddress1(), 
							extractor.getStreetAddress2(), extractor.getCity(),
							extractor.getZip(), regionCode, 
							extractor.getHomePhoneNumber(), 
							extractor.getMobilePhoneNumber(),
							extractor.getSecurityQuestion(), 
							extractor.getSecurityAnswer(), 
							extractor.getAcceptedTermsOfUse().equals("true"),
							serviceContext);
						
//						User user = UserLocalServiceUtil.addUser(
//							themeDisplay.getUserId(), 
//							themeDisplay.getCompanyId(), false, 
//							extractor.getPassword1(), 
//							extractor.getPassword2(),
//							false, extractor.getUsername(), 
//							extractor.getEmailAddress(), 0L, null,
//							themeDisplay.getLocale(), 
//							extractor.getFirstName(), null, 
//							extractor.getLastName(), 0, 0, isMale,
//							bm - 1, bd, by, null, new long[]{groupId},
//							null, null, null, true, serviceContext);
//						
//						long regionId = Long.parseLong(extractor.getState());
//						
//						AddressLocalServiceUtil.addAddress(
//							user.getUserId(), 
//							Contact.class.getName(), user.getContactId(), 
//							extractor.getStreetAddress1(),
//							extractor.getStreetAddress2(),
//							"", extractor.getCity(), extractor.getZip(),
//							regionId, extractor.getCountryId(), 11002,
//							false, true, 
//							ServiceContextFactory.getInstance(request));
//						
//						serviceContext = 
//							ServiceContextFactory.getInstance(request);
//						
//						PhoneLocalServiceUtil.addPhone(
//								user.getUserId(), 
//								Contact.class.getName(), user.getContactId(),
//								extractor.getHomePhoneNumber(), 
//								null, 11011, true, serviceContext);
//						serviceContext = 
//								ServiceContextFactory.getInstance(request);
//						PhoneLocalServiceUtil.addPhone(
//								user.getUserId(), 
//								Contact.class.getName(), user.getContactId(),
//								extractor.getMobilePhoneNumber(), 
//								null, 11008, true, serviceContext);
					}
					catch (PortalException e) {
						String dbRelatedError = 
							LanguageUtil.get(
								themeDisplay.getLocale(),
								"user-or-entity-transaction-error");
						errorMessages.add(dbRelatedError);
						_log.error("Unable to add new user or supporting "
								+ "element: " + e.getLocalizedMessage());
					}
					if (errorMessages.isEmpty()){
						_log.info("SUCCESS");
						SessionMessages.add(
							request,"user-and-entity-transaction-success");
					}
				} catch (SystemException e) {
					String systemRelatedError = LanguageUtil.get(
						themeDisplay.getLocale(),
						"user-or-entity-transaction-system-error");
					_log.error("Unable to add new user");
					errorMessages.add(systemRelatedError);
					request.setAttribute("bInfoErrorList", errorMessages);
				}
			}
			if (!errorMessages.isEmpty()){
				SessionErrors.add(request,"basic_error");
				request.setAttribute(
						"bInfoErrorList", errorMessages);
			}
			PortalUtil.copyRequestParameters(request, response);
			
			response.setPortletMode(PortletMode.VIEW);
	}

	private Log _log = LogFactoryUtil.getLog(MySignupPortlet.class);
	private SignupValidator _signupValidator = new SignupValidator();
}
