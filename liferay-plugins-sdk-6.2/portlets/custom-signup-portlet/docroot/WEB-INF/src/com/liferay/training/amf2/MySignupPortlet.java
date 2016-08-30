package com.liferay.training.amf2;

import java.io.IOException;
import java.util.ArrayList;
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
	public void processAction(
			ActionRequest request, ActionResponse response) 
		throws IOException, PortletException {

		SignupParamHolder extractor = 
			new AmfExcerciseSignupParamHolderImpl(request);

		if (extractor.isSignedIn()) {
			response.setPortletMode(PortletMode.VIEW);
			return;
		}

		ThemeDisplay themeDisplay = 
			(ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

		List<String> errorMessages = new ArrayList<String>();

		try {
			boolean isMale = 
				extractor.getGender().equalsIgnoreCase(
					MySignupConstants.MALE_STRING_VALUE);
			
			int bm = extractor.getBirthdayMonth();
			int bd = extractor.getBirthdayDay();
			int by = extractor.getBirthdayYear();

			long regionCode = Integer.parseInt(extractor.getState());

			ServiceContext serviceContext =
				ServiceContextFactory.getInstance(request);

			// Pass new service context to each call since each call
			// might dirty the context

			errorMessages =
				AmfSignupLocalServiceUtil.addUserWithAddressAndPhones(
					themeDisplay.getUserId(), themeDisplay.getCompanyId(),
					false, extractor.getPassword1(), extractor.getPassword2(),
					false, extractor.getUsername(), extractor.getEmailAddress(),
					themeDisplay.getLocale(), extractor.getFirstName(),
					extractor.getLastName(), isMale, bm, bd, by,
					extractor.getStreetAddress1(), 
					extractor.getStreetAddress2(),
					extractor.getCity(), extractor.getZip(), regionCode,
					extractor.getHomePhoneNumber(),
					extractor.getMobilePhoneNumber(),
					extractor.getSecurityQuestion(), 
					extractor.getSecurityAnswer(),
					extractor.getAcceptedTermsOfUse().equals("true"),
					serviceContext);
			
		} catch (PortalException e) {
			
			String dbRelatedError = 
				LanguageUtil.get(
					themeDisplay.getLocale(),
					"user-or-entity-transaction-error");
			
			errorMessages.add(dbRelatedError);
			
			_log.warn(
				"Unable to add new user or supporting element: "
				+ e.getLocalizedMessage());
			
		} catch (SystemException e) {
			
			String systemRelatedError = 
				LanguageUtil.get(
					themeDisplay.getLocale(), 
					"user-or-entity-transaction-system-error");
			
			_log.warn("Unable to add new user");
			
			errorMessages.add(systemRelatedError);
			request.setAttribute("bInfoErrorList", errorMessages);
		}
		
		if (errorMessages.isEmpty()) {
			
			_log.info("SUCCESS");
			SessionMessages.add(request, "user-and-entity-transaction-success");
			
		} else {
			
			SessionErrors.add(request, "basic_error");
			request.setAttribute("bInfoErrorList", errorMessages);
			
		}
		
		PortalUtil.copyRequestParameters(request, response);

		response.setPortletMode(PortletMode.VIEW);
	}

	private Log _log = LogFactoryUtil.getLog(MySignupPortlet.class);
}
