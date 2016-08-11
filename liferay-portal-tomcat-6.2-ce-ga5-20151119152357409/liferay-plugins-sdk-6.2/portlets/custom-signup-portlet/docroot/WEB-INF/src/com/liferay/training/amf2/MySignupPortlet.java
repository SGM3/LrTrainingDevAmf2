package com.liferay.training.amf2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Address;
import com.liferay.portal.model.Phone;
import com.liferay.portal.model.Region;
import com.liferay.portal.model.User;
import com.liferay.portal.service.AddressLocalServiceUtil;
import com.liferay.portal.service.PhoneLocalServiceUtil;
import com.liferay.portal.service.RegionServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import static com.liferay.training.amf2.constants.MySignupConstants.*;

/**
 * Portlet implementation class MySignupPortlet
 */
public class MySignupPortlet extends MVCPortlet {
	
	private static final String MIN_PASS_REQ = "6 characters long, must contain at "
			+ "least one of each: uppercase letter, number, special character";
	
	private Log _log = LogFactoryUtil.getLog(MySignupPortlet.class);

	private static boolean isPrintableAscii(char ch){
		if (ch < ' ' || ch == 0x7f){ // if is a control character (or del):
									 // https://en.wikipedia.org/wiki/ASCII#Control_characters
			return false;
		}
		return true;
	}
	
	private static boolean isAlphaNumericString(String str){//space is not valid in string
		if (str == null){
			return false;
		}
		for (int i = 0; i < str.length(); i++){
			char ch = str.charAt(i);
			if (!(Character.isAlphabetic(ch) || Character.isDigit(ch))){
				return false;
			}
		}
		return true;
	}
	
	private static boolean isSpecialChar(char ch){
		return isPrintableAscii(ch) && (!Character.isAlphabetic(ch) 
				&& !Character.isDigit(ch));
	}
	
	private boolean isUsernameUnique(ActionRequest req, String uname){
		ThemeDisplay themeDisplay = (ThemeDisplay) req.getAttribute(
				WebKeys.THEME_DISPLAY);
		long companyId = themeDisplay.getCompanyId();
		uname = uname.trim();
		try {
			UserLocalServiceUtil.getUserIdByScreenName(companyId, uname);
		} catch (PortalException e) {
			return true;
		} catch (SystemException e) {
			_log.error("Unable to query User table: " + e.getMessage());
		}
		return false;// TODO unique username logic
	}
	
	private List<Region> getCountryRegions(long countryCode){
		List<Region> reg = null;
		try {
			reg = RegionServiceUtil.getRegions(countryCode);
		} catch (SystemException e) {
			_log.error("Unable to query Region area codes(Country ID " + countryCode + ")");
			reg = new ArrayList<Region>();
		}
		return reg;
	}
	
	protected boolean meetsPasswordRequirements(String pass){
		if (pass == null){
			return false;
		}
		if (pass.length() < 6){
			return false;
			
		}
		short flag = 0x0;// 0x100 is for uppercase character
						 // 0x010 is for number character
						 // 0x001 is for special character
						 // 0x111 is all 3 above
		for (int i = 0; i < pass.length(); i++){
			char cur = pass.charAt(i);
			if (isSpecialChar(cur)){
				flag |= 0x001;
			} else if (Character.isDigit(cur)){
				flag |= 0x010;
			} else if (Character.isUpperCase(cur)){
				flag |= 0x100;
			}
			if (flag == 0x111){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void doView(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {
		// TODO Auto-generated method stub
		List<Region> regs = getCountryRegions(19);
//		log.error(regs);
		renderRequest.setAttribute(US_REG_CODES_ATTR, regs);
		super.doView(renderRequest, renderResponse);
	}
	
	private boolean validateFirstname(String fname, List<String> allErrors){
		boolean hasError = false;
		if (Validator.isNull(fname)){
			hasError = true;
			allErrors.add("First name is required.");
		} else {
			if (!Validator.isName(fname)){
				hasError = true;
				allErrors.add("First name must be alpha numeric.");
			}
			if (fname.length() > 50){
				hasError = true;
				allErrors.add("First name is too long.");
			}
		}
		return hasError;
	}
	
	private boolean validateLastname(String lname, List<String> allErrors){
		boolean hasError = false;
		if (Validator.isNull(lname)){
			hasError = true;
			allErrors.add("Last name is required.");
		} else{
			if (!Validator.isName(lname)){
				hasError = true;
				allErrors.add("Last name must be alpha numeric.");
			}
			if (lname.length() > 50){
				hasError = true;
				allErrors.add("Last name is too long.");
			}
		}
		return hasError;
	}
	
	private boolean validateEmail(String emailAddr, List<String> allErrors){
		boolean hasError = false;
		if (Validator.isNull(emailAddr)){
			hasError = true;
			allErrors.add("Email is required.");
		} else{
			if (!Validator.isEmailAddress(emailAddr)){
				hasError = true;
				allErrors.add("Email is in invalid format.");
			}
			if (emailAddr.length() > 255){
				hasError = true;
				allErrors.add("Email is too long.");
			}
		}
		return hasError;
	}
	
	private boolean validateUsername(ActionRequest request, String username, List<String> allErrors){
		boolean hasError = false;
		if (Validator.isNull(username)){
			hasError = true;
			allErrors.add("Username is required.");
		} else{
			if (!isAlphaNumericString(username)){// TODO Spaced accepted?
				hasError = true;
				allErrors.add("Username must be an alpha numeric string.");
			}
			if (username.length() > 50){
				hasError = true;
				allErrors.add("Username is too long.");
			}
			if (!isUsernameUnique(request, username)){
				hasError = true;
				allErrors.add("Username is aleady taken.");
			}
		}
		return hasError;
	}
	
	private boolean validateDateAndAge(
			String bday, String bmonth, String byear, List<String> allErrors){
		boolean hasError = false;
		if (Validator.isNull(bday)){
			hasError = true;
			allErrors.add("Birthday day is required.");
		} else {
			if (!Validator.isNumber(bday)){
				hasError = true;
				allErrors.add("Numeric birthday day expected.");
			} else if (Integer.parseInt(bday) < 1 || Integer.parseInt(bday) > 31){
				hasError = true;
				allErrors.add("Birthday day must be in range [1-31].");
			}
		}
		if (Validator.isNull(bmonth)){
			hasError = true;
			allErrors.add("Birthday month is required.");
		} else{
			if (!Validator.isNumber(bmonth)){
				hasError = true;
				allErrors.add("Numeric birthday month expected.");
			} else if (Integer.parseInt(bmonth) < 1 || Integer.parseInt(bmonth) > 12){
				hasError = true;
				allErrors.add("Birthday month must be in range [1-12].");
			}
		}
		if (Validator.isNull(byear)){
			hasError = true;
			allErrors.add("Birthday year is required.");
		} else {
			if (!Validator.isNumber(byear)){
				hasError = true;
				allErrors.add("Numeric year month expected.");
			}
		}
		int bm = (!hasError)?Integer.parseInt(bmonth):1, 
				bd = (!hasError)?Integer.parseInt(bday):1, 
				by = (!hasError)?Integer.parseInt(byear):1;			
		Calendar bCal = new GregorianCalendar(), thirteenYearsAgo = new GregorianCalendar();
				//create a Calendar date equal to 13 yeas before today
		bCal.clear();
		thirteenYearsAgo.clear(Calendar.AM_PM);
		thirteenYearsAgo.clear(Calendar.MILLISECOND);
		thirteenYearsAgo.clear(Calendar.SECOND);
		thirteenYearsAgo.clear(Calendar.MINUTE);
		thirteenYearsAgo.clear(Calendar.HOUR_OF_DAY);
		thirteenYearsAgo.add(Calendar.YEAR, -13);
		
		if (!Validator.isDate(bm, bd, by)){
			String gregDate = String.format("%02d/%02d/%d", bm, bd, by);
			allErrors.add("The date \"" + gregDate + "\" (format: MM/DD/YYYY) is not valid.");
		} else {
			bCal.set(by, bm - 1, bd);
			if (bCal.after(thirteenYearsAgo)){
				allErrors.add("Must be at least 13 years old to sign up.");
			}
		}
		return hasError;
	}
	
	private boolean validatePasswords(String upass1, String upass2, List<String> allErrors){
		boolean hasError = false;
		if (Validator.isNull(upass1)){
			hasError = true;
			allErrors.add("Password is required.");
		} else if (!meetsPasswordRequirements(upass1)){
			//should not trim
			hasError = true;
			allErrors.add("Does not meet minimum password requirements.(" 
							+ MIN_PASS_REQ + ").");
		}
		return hasError;
	}
	
	private boolean validatePhones(String homeNum, String mobileNum, List<String> allErrors){
		boolean hasError = false;
		if (Validator.isNotNull(homeNum)){
			if (!Validator.isPhoneNumber(homeNum) || homeNum.length() != 10){
				hasError = true;
				allErrors.add("Home phone number invalid.");
			}
		}
		if (Validator.isNotNull(mobileNum)){
			if (!Validator.isPhoneNumber(mobileNum) || mobileNum.length() != 10){
				hasError = true;
				allErrors.add("Mobile phone number invalid.");
			}
		}
		return hasError;
	}
	
	private boolean validateAddressLines(String addr1, String addr2, List<String> allErrors){
		boolean hasError = false;
		if (Validator.isNull(addr1)){
			hasError = true;
			allErrors.add("Address is required.");
		} else {
			if (!isAlphaNumericString(addr1.replaceAll(" ", "")) 
				|| addr1.length() > 255){ // spaces are accepted
				hasError = true;
				allErrors.add("Address is invalid.");
			}
		}
		if (Validator.isNotNull(addr2)){
			if (!isAlphaNumericString(addr2.replaceAll(" ", "")) 
					|| addr2.length() > 255){ // spaces are accepted
				hasError = true;
				allErrors.add("Address 2 is invalid.");
			}
		}
		return hasError;
	}
	
	private boolean validateCity(String city, List<String> allErrors){
		boolean hasError = false;
		if (Validator.isNull(city)){
			hasError = true;
			allErrors.add("City is required.");
		} else {
			if (!isAlphaNumericString(city.replaceAll(" ", "")) 
				|| city.length() > 255){ // spaces are accepted
				hasError = true;
				allErrors.add("City is invalid.");
			}
		}
		return hasError;
	}
	
	private boolean validateState(String state, List<String> allErrors){
		boolean hasError = false;
		if (Validator.isNull(state)){
			hasError = true;
			allErrors.add("State is required.");
		} else {
			if (!Validator.isNumber(state)){
				hasError = true;
				allErrors.add("State is invalid. (Must use LifeRay State code)");
			}
		}
		// NOTE: 19 is the country code form Regions table for US
		return hasError;
	}
	
	private boolean validateZip(String zipCode, List<String> allErrors){
		boolean hasError = false;
		if (Validator.isNull(zipCode)){
			hasError = true;
			allErrors.add("Zip code is required.");
		} else {
			if (!Validator.isNumber(zipCode) || zipCode.length() != 5){
				hasError = true;
				allErrors.add("Zip code is invalid.");
			}
		}
		return hasError;
	}
	
	private boolean validateSecurityAnswer(String secAnswer, List<String> allErrors){
		boolean hasError = false;
		if (Validator.isNull(secAnswer)){
			hasError = true;
			allErrors.add("An anwser to the security question is required.");
		} else {
			//should not trim
			if (!isAlphaNumericString(secAnswer.replaceAll(" ", ""))){
				hasError = true;
				allErrors.add("Security answer must be alph numeric.");
			}
			if (secAnswer.length() > 255){
				hasError = true;
				allErrors.add("Security answer is too long.");
			}
		}
		return hasError;
	}
	
	@Override
	public void processAction(ActionRequest request, ActionResponse response)
			throws IOException, PortletException {
			boolean hasError = false;
			boolean isMale = false;
			boolean isInvalidBday = false;
			boolean isValidState = false;
			int bd = 1, bm = 1, by = 1;
			long regionCodeState = 0;
			ArrayList<String> allErrors = new ArrayList<String>();

			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
//			long groupId = themeDisplay.getScopeGroupId();
			long companyId = themeDisplay.getCompanyId();
			long curUserId = themeDisplay.getUserId();
			Locale curUserLocale = themeDisplay.getLocale();
			
			if (themeDisplay.isSignedIn()){
				response.setPortletMode(PortletMode.VIEW);
				return;
			}
			
				
			String fname = StringUtil.trim(ParamUtil.getString(request, FIRSTN_PARAM));
			hasError |= validateFirstname(fname, allErrors);
			
			String lname = StringUtil.trim(ParamUtil.getString(request, LASTN_PARAM));
			hasError |= validateLastname(lname, allErrors);
			
			String emailAddr = StringUtil.trim(ParamUtil.getString(request, EMAIL_PARAM));
			hasError |= validateEmail(emailAddr, allErrors);

			String username = StringUtil.trim(ParamUtil.getString(request, UNAME_PARAM));
			hasError |= validateUsername(request, username, allErrors);
			
			String gender = StringUtil.trim(ParamUtil.getString(request, GENDER_PARAM));
			if (Validator.isNull(gender)){
				hasError = true;
				allErrors.add("Gender is required.");
			} else {
				isMale = gender.equals("male");
			}
			
			String bmonth = StringUtil.trim(ParamUtil.getString(request, BMONTH_PARAM));
			String bday = StringUtil.trim(ParamUtil.getString(request, BDAY_PARAM));
			String byear = StringUtil.trim(ParamUtil.getString(request, BYEAR_PARAM));
			isInvalidBday = validateDateAndAge(bday, bmonth, byear, allErrors);
			if (!isInvalidBday){
				bd = Integer.parseInt(bday);
				bm = Integer.parseInt(bmonth);
				by = Integer.parseInt(byear);
			}
			hasError |= isInvalidBday;
			
			String upass1 = ParamUtil.getString(request, UPASS1_PARAM);
			String upass2 = ParamUtil.getString(request, UPASS2_PARAM);
			hasError |= validatePasswords(upass1, upass2, allErrors);

			String homeNum = StringUtil.trim(ParamUtil.getString(request, HOMEPHONE_PARAM));
			String mobileNum = StringUtil.trim(ParamUtil.getString(request, MOBILEPHONE_PARAM));
			hasError |= validatePhones(homeNum, mobileNum, allErrors);

			String addr1 = StringUtil.trim(ParamUtil.getString(request, ADDR1_PARAM));
			String addr2 = StringUtil.trim(ParamUtil.getString(request, ADDR2_PARAM));
			hasError |= validateAddressLines(addr1, addr2, allErrors);
			
			String city = StringUtil.trim(ParamUtil.getString(request, CITY_PARAM));
			hasError |= validateCity(city, allErrors);
			
			String state = StringUtil.trim(ParamUtil.getString(request, STATE_PARAM));
			isValidState = validateState(state, allErrors);
			hasError |= isValidState;
			if (isValidState){
				regionCodeState = Integer.parseInt(state);
			}
			
			String zipCode = StringUtil.trim(ParamUtil.getString(request, ZIP_PARAM));
			hasError |= validateZip(zipCode, allErrors);
			
			String secQuestion = ParamUtil.getString(request, SECQ_PARAM);
			if (Validator.isNull(secQuestion)){
				hasError = true;
				allErrors.add("A security question is required.");
			}
			
			String secAnswer = ParamUtil.getString(request, SECA_PARAM);
			hasError |= validateSecurityAnswer(secAnswer, allErrors);
			
			String acceptedTou = ParamUtil.getString(request, ATOU_PARAM);
			if (Validator.isNull(acceptedTou)){
				hasError = true;
				allErrors.add("Terms of user must be accepted to continue.");
			} else if (!acceptedTou.equals("true")){
				hasError = true;
				allErrors.add("Terms of user must be accepted to continue.");
			}

			if (!hasError) { //at this point we can begin the transaction
				try {
					//Contact Phone Address
					long userId = -1;
					long contactId = -1;
					long addrId = -1;
					String uuid = null;
					
					//NOTE: Instance type service calls seems to be purely transactional
					
//					User utmp = UserLocalServiceUtil.addUser(builtUser); // Invalid, this service
																		 // call is purely
																		 // transactional
					try {
						User utmp = UserLocalServiceUtil.addUser(
								curUserId, companyId, false, upass1, upass2,
								false, username, emailAddr, 0L, null,
								curUserLocale, fname, null,
								lname, 0, 0, isMale, bm - 1, bd, by, null, null,
								null, null, null, true, null);
						
						userId = utmp.getUserId();
						contactId = utmp.getContactId();
						uuid = utmp.getUuid();
					}
					catch (PortalException e) {
						hasError = true;
						allErrors.add("Unable to add user.");
						_log.error("Unable to add new user: " + e.getLocalizedMessage());
					}
					if (userId != -1){
						try {
							ServiceContext sc = new ServiceContext();
							sc.setUuid(uuid);// mock out the service context because
											 // the services decide the pull it from
											 // the context PhoneLocalServiceImpl.java:76
							Address atmp = AddressLocalServiceUtil.addAddress(
									userId, Address.class.getName(), contactId, addr1, addr2,
									"", city, zipCode, regionCodeState, 19, 11002,
									false, true, sc);
							addrId = atmp.getAddressId();
						}
						catch (PortalException e) {
							allErrors.add("Unable to add address.");
							_log.error("Unable to add address: " + e.getLocalizedMessage());
						}
					}
					if (addrId != -1){
						ServiceContext sc = new ServiceContext();
						sc.setUuid(uuid);// mock out the service context because
										 // the services decide the pull it from
										 // the context PhoneLocalServiceImpl.java:84
						// from Prathima, classPk is contactId
						try {
							// From ListType Table, 11011 is for home and 11008 is mobile phone usage
							PhoneLocalServiceUtil.addPhone(
								userId, Phone.class.getName(), contactId,
								homeNum, null, 11011, true, sc);
							
							PhoneLocalServiceUtil.addPhone(
								userId, Phone.class.getName(), contactId
								, mobileNum, null, 11008, true, sc);
						} 
						catch (PortalException e) {
							hasError = true;
							allErrors.add("Internal error, unable to complete the request");
							_log.error("Unable to add phone number[s]: " + e.getLocalizedMessage());
						}
					}
					_log.info("SUCCESS");
					SessionMessages.add(request,"add_user_success", "New User created sucessfully");
				} catch (SystemException e) {
					_log.error("Unable to add new user");
					hasError = true;
					allErrors.add("Internal error, unable to complete the request");
					request.getPortletSession().setAttribute("bInfoErrorList", allErrors.toArray());
				}
			}
			if (hasError){
				SessionErrors.add(request,"basic_error");
				request.getPortletSession().setAttribute(
						"bInfoErrorList", allErrors.toArray());
			}
			PortalUtil.copyRequestParameters(request, response);
			
			response.setPortletMode(PortletMode.VIEW);
	}
	
	
}
