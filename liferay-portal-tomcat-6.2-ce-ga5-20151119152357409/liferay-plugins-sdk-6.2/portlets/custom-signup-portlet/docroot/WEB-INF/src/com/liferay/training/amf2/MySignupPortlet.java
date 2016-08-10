package com.liferay.training.amf2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;

import com.liferay.counter.service.CounterLocalServiceUtil;
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
import com.liferay.portal.model.Contact;
import com.liferay.portal.model.ListTypeConstants;
import com.liferay.portal.model.ListTypeModel;
import com.liferay.portal.model.Phone;
import com.liferay.portal.model.Region;
import com.liferay.portal.model.User;
import com.liferay.portal.service.AddressLocalServiceUtil;
import com.liferay.portal.service.ContactLocalServiceUtil;
import com.liferay.portal.service.PhoneLocalServiceUtil;
import com.liferay.portal.service.RegionServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.persistence.ListTypeUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.util.bridges.mvc.MVCPortlet;

import static com.liferay.training.amf2.constants.MySignupConstants.*;

/**
 * Portlet implementation class MySignupPortlet
 */
public class MySignupPortlet extends MVCPortlet {
	
	private static final String MIN_PASS_REQ = "6 characters long, must contain at "
			+ "least one of each: uppercase letter, number, special character";
	
	private Log log = LogFactoryUtil.getLog(MySignupPortlet.class);

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
				|| !Character.isDigit(ch));
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
	
	private boolean isUsernameUnique(ActionRequest req, String uname){
		ThemeDisplay themeDisplay = (ThemeDisplay) req.getAttribute(WebKeys.THEME_DISPLAY);
		long companyId = themeDisplay.getCompanyId();
		uname = uname.trim();
		try {
			UserLocalServiceUtil.getUserIdByScreenName(companyId, uname);
		} catch (PortalException e) {
			return true;
		} catch (SystemException e) {
			log.error("Unable to query User table: " + e.getMessage());
		}
		return false;// TODO unique username logic
	}
	
	private boolean isValidRegionCode(ActionRequest req, String rCode){// TODO LR state code (from DB)
		try {
			List<Region> reg = RegionServiceUtil.getRegions(19);
			for (Region r: reg){
				if (r.getRegionCode().equals(rCode)){//includes 50 states and Puerto Rico and DC
					return true;
				}
			}
		} catch (SystemException e) {
			log.error("Unable to query Region area codes for US (Country ID 19)");
		}//19 is country code for US
		return false;
	}
	
	@Override
	public void processAction(ActionRequest request, ActionResponse response)
			throws IOException, PortletException {
			boolean hasError = false;
			ArrayList<String> allErrors = new ArrayList<String>();
			
//			User builtUser = UserLocalServiceUtil.createUser(0);
//			Contact builtUserContact = ContactLocalServiceUtil.createContact(0);
//			Phone homePhone = null, mobilePhone = null;
//			Address builtUserAddr = AddressLocalServiceUtil.createAddress(0);

			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			long groupId = themeDisplay.getScopeGroupId();
			long companyId = themeDisplay.getCompanyId();
			long curUserId = themeDisplay.getUserId();
			Locale curUserLocale = themeDisplay.getLocale();
				
			String fname = ParamUtil.getString(request, FIRSTN_PARAM);
			if (Validator.isNull(fname)){
				hasError = true;
				allErrors.add("First name is required.");
			} else {
				fname = StringUtil.trim(fname);
				if (!Validator.isName(fname)){
					hasError = true;
					allErrors.add("First name must be alpha numeric.");
				}
				if (fname.length() > 50){
					hasError = true;
					allErrors.add("First name is too long.");
				}
			}
//			builtUser.setFirstName(fname);
			
			String lname = ParamUtil.getString(request, LASTN_PARAM);
			if (Validator.isNull(lname)){
				hasError = true;
				allErrors.add("Last name is required.");
			} else{
				lname = StringUtil.trim(lname);
				if (!Validator.isName(lname)){
					hasError = true;
					allErrors.add("Last name must be alpha numeric.");
				}
				if (lname.length() > 50){
					hasError = true;
					allErrors.add("Last name is too long.");
				}
			}
//			builtUser.setLastName(lname);
			
			String emailAddr = ParamUtil.getString(request, EMAIL_PARAM);
			if (Validator.isNull(emailAddr)){
				hasError = true;
				allErrors.add("Email is required.");
			} else{
				emailAddr = StringUtil.trim(emailAddr);
				if (!Validator.isEmailAddress(emailAddr)){
					hasError = true;
					allErrors.add("Email is in invalid format.");
				}
				if (emailAddr.length() > 255){
					hasError = true;
					allErrors.add("Email is too long.");
				}
			}
//			builtUser.setEmailAddress(emailAddr);
			
			String username = ParamUtil.getString(request, UNAME_PARAM);
			if (Validator.isNull(username)){
				hasError = true;
				allErrors.add("Username is required.");
			} else{
				username = StringUtil.trim(username);
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
//			builtUser.setScreenName(username);
			
			String gender = ParamUtil.getString(request, GENDER_PARAM);
			boolean isMale = false;
			if (Validator.isNull(gender)){
				hasError = true;
				allErrors.add("Gender is required.");
			} else {
				gender = StringUtil.trim(gender);//shouldn't need to trim, just to make sure
				isMale = gender.equals("male");
			}
//			builtUserContact.setMale(isMale);//TODO set gender on user
			
			String bmonth = ParamUtil.getString(request, BMONTH_PARAM);
			if (Validator.isNull(bmonth)){
				hasError = true;
				allErrors.add("Birthday month is required.");
			} else{
				bmonth = StringUtil.trim(bmonth);
				if (!Validator.isNumber(bmonth)){
					hasError = true;
					allErrors.add("Numeric birthday month expected.");
				} else if (Integer.parseInt(bmonth) < 1 || Integer.parseInt(bmonth) > 12){
					hasError = true;
					allErrors.add("Birthday month must be in range [1-12].");
				}
			}
			
			String bday = ParamUtil.getString(request, BDAY_PARAM);
			if (Validator.isNull(bday)){
				hasError = true;
				allErrors.add("Birthday day is required.");
			} else {
				bday = StringUtil.trim(bday);
				if (!Validator.isNumber(bday)){
					hasError = true;
					allErrors.add("Numeric birthday day expected.");
				} else if (Integer.parseInt(bday) < 1 || Integer.parseInt(bday) > 31){
					hasError = true;
					allErrors.add("Birthday day must be in range [1-31].");
				}
			}
			
			String byear = ParamUtil.getString(request, BYEAR_PARAM);
			if (Validator.isNull(byear)){
				hasError = true;
				allErrors.add("Birthday year is required.");
			} else {
				byear = StringUtil.trim(byear);
				if (!Validator.isNumber(byear)){
					hasError = true;
					allErrors.add("Numeric year month expected.");
				}
			}
			
			int bm = Integer.parseInt(bmonth), bd = Integer.parseInt(bday), by = Integer.parseInt(byear);
			
			Calendar bCal = new GregorianCalendar(), thirteenYearsAgo = new GregorianCalendar();
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
//			builtUserContact.setBirthday(new Date(bCal.getTimeInMillis()));
			//TODO Must be 13+
			//TODO Validate as Gregorian date

			
			String upass1 = ParamUtil.getString(request, UPASS1_PARAM);
			if (Validator.isNull(upass1)){
				hasError = true;
				allErrors.add("Password is required.");
			} else if (meetsPasswordRequirements(upass1)){
				//should not trim
				hasError = true;
				allErrors.add("Does not meet minimum password requirements.(" 
								+ MIN_PASS_REQ + ").");
			}
//			builtUser.setPassword(upass1);
			
			String upass2 = ParamUtil.getString(request, UPASS2_PARAM);
			if (Validator.isNotNull(upass2) && !upass2.equals(upass1)){
				allErrors.add("Does not match password 1.");
			}
			// TODO nothing to add to builtUser?

			String homeNum = ParamUtil.getString(request, HOMEPHONE_PARAM);
			if (Validator.isNotNull(homeNum)){
				homeNum = StringUtil.trim(homeNum);
				if (!Validator.isPhoneNumber(homeNum) || homeNum.length() != 10){
					hasError = true;
					allErrors.add("Home phone number invalid.");
				} else {
					// should be valid format at this point
//					homePhone = PhoneLocalServiceUtil.createPhone(0);
//					homePhone.setNumber(homeNum);
//					homePhone.setTypeId(11011);// TODO Set as 'home'
					// from DB table ListType for 'com.liferay.portal.model.Contact.phone'
					// for 'personal'
				}
			}
			
			String mobileNum = ParamUtil.getString(request, MOBILEPHONE_PARAM);
			if (Validator.isNotNull(mobileNum)){
				mobileNum = StringUtil.trim(mobileNum);
				if (!Validator.isPhoneNumber(mobileNum) || mobileNum.length() != 10){
					hasError = true;
					allErrors.add("Mobile phone number invalid.");
				} else {
					// should be valid format at this point
//					mobilePhone = PhoneLocalServiceUtil.createPhone(0);
//					mobilePhone.setNumber(homeNum);
//					mobilePhone.setTypeId(11008);// TODO Set as 'mobile'
					// from DB table ListType for 'com.liferay.portal.model.Contact.phone'
					// for 'mobile-phone'
				}
			}

			String addr1 = ParamUtil.getString(request, ADDR1_PARAM);
			if (Validator.isNull(addr1)){
				hasError = true;
				allErrors.add("Address is required.");
			} else {
				addr1 = StringUtil.trim(addr1);
				if (!isAlphaNumericString(addr1.replaceAll(" ", "")) 
					|| addr1.length() > 255){ // spaces are accepted
					hasError = true;
					allErrors.add("Address is invalid.");
				}
			}
//			builtUserAddr.setStreet1(addr1);
			
			String addr2 = ParamUtil.getString(request, ADDR2_PARAM);
			if (Validator.isNotNull(addr2)){
				addr2 = StringUtil.trim(addr2);
				if (!isAlphaNumericString(addr2.replaceAll(" ", "")) 
						|| addr2.length() > 255){ // spaces are accepted
					hasError = true;
					allErrors.add("Address 2 is invalid.");
				} else {
//					builtUserAddr.setStreet2(addr2);
				}
			}
			
			String city = ParamUtil.getString(request, CITY_PARAM);
			if (Validator.isNull(city)){
				hasError = true;
				allErrors.add("City is required.");
			} else {
				city = StringUtil.trim(city);
				if (!isAlphaNumericString(city.replaceAll(" ", "")) 
					|| city.length() > 255){ // spaces are accepted
					hasError = true;
					allErrors.add("City is invalid.");
				}
			}
//			builtUserAddr.setCity(city);
			
			String state = ParamUtil.getString(request, STATE_PARAM);
			if (Validator.isNull(state)){
				hasError = true;
				allErrors.add("State is required.");
			} else {
				state = StringUtil.trim(state);
				if (!isValidRegionCode(request, state)){ 
					hasError = true;
					allErrors.add("State is invalid. (Must use LifeRay State code)");
				}
			}
			//TODO Set state somewhere, maybe make UI dropdown
//			builtUserAddr.setCountryId(19);// 19 is the country code form Regions table for US
			
			String zipCode = ParamUtil.getString(request, ZIP_PARAM);
			if (Validator.isNull(zipCode)){
				hasError = true;
				allErrors.add("Zip code is required.");
			} else {
				zipCode = StringUtil.trim(zipCode);
				if (!Validator.isNumber(zipCode) || zipCode.length() != 5){
					hasError = true;
					allErrors.add("Zip code is invalid.");
				}
			}
//			builtUserAddr.setZip(zipCode);
			
			String secQuestion = ParamUtil.getString(request, SECQ_PARAM);
			if (Validator.isNull(secQuestion)){
				hasError = true;
				allErrors.add("A security question is required.");
			}
			// no need to trim, drop down
//			builtUser.setReminderQueryQuestion(secQuestion);
			
			String secAnswer = ParamUtil.getString(request, SECA_PARAM);
			if (Validator.isNull(secAnswer)){
				hasError = true;
				allErrors.add("An anwser to the security question is required.");
			} else {
				//should not trim
				if (!isAlphaNumericString(secAnswer.replaceAll(" ", ""))){
					hasError = true;
					allErrors.add("Email is in invalid format.");
				}
				if (secAnswer.length() > 255){
					hasError = true;
					allErrors.add("Security answer is too long.");
				}
			}
//			builtUser.setReminderQueryAnswer(secAnswer);
			
			
			String acceptedTou = ParamUtil.getString(request, ATOU_PARAM);
			if (Validator.isNull(acceptedTou)){
				hasError = true;
				allErrors.add("Terms of user must be accepted to continue.");
			} else if (!acceptedTou.equals("true")){
				hasError = true;
				allErrors.add("Terms of user must be accepted to continue.");
			}
//			builtUser.setAgreedToTermsOfUse(true);
			
//			System.out.println("fname: " + fname);
//			System.out.println("lname: " + lname);
//			System.out.println("emailAddr: " + emailAddr);
//			System.out.println("username: " + username);
//			System.out.println("gender: " + gender);
//			System.out.println("bmonth: " + bmonth);
//			System.out.println("bday: " + bday);
//			System.out.println("byear: " + byear);
//			System.out.println("upass1: " + upass1);
//			System.out.println("upass2: " + upass2);
//			System.out.println("homeNum: " + homeNum);
//			System.out.println("phoneNum: " + phoneNum);
//			System.out.println("addr1: " + addr1);
//			System.out.println("addr2: " + addr2);
//			System.out.println("city: " + city);
//			System.out.println("state: " + state);
//			System.out.println("zipCode: " + zipCode);
//			System.out.println("secQuestion: " + secQuestion);
//			System.out.println("secAnswer: " + secAnswer);
//			System.out.println("acceptedTou: " + acceptedTou);



			if (hasError) {
				SessionErrors.add(request,"basic_error");
				request.getPortletSession().setAttribute("bInfoErrorList", allErrors.toArray());
			} else {
				try {
					//Contact Phone Address
					long userId = CounterLocalServiceUtil.increment(User.class.getName());
					long contactId = CounterLocalServiceUtil.increment(Contact.class.getName());
					long hPhoneId = CounterLocalServiceUtil.increment(Phone.class.getName());
					long mPhoneId = CounterLocalServiceUtil.increment(Phone.class.getName());
					long addrId = CounterLocalServiceUtil.increment(Address.class.getName());
					
//					builtUser.setPrimaryKey(0);
					//NOTE: Instance type service calls seems to be purely transactional
					
//					User utmp = UserLocalServiceUtil.addUser(builtUser); // Invalid, this service
//																		 // call is purely
//																		 // transactional
					try {
						User utmp = UserLocalServiceUtil.addUser(
								curUserId, companyId, false, upass1, upass2,
								false, username, emailAddr, 0L, null, curUserLocale, fname, null,
								lname, 0, 0, isMale, bm - 1, bd, by, null, null,
								null, null, null, true, null);
					} catch (PortalException e) {
						log.error("Unable to add new user: " + e.getMessage());
					}
//					ContactLocalServiceUtil.addContact(builtUserContact); //TODO uncomment
//					PhoneLocalServiceUtil.addPhone(homePhone); //TODO uncomment
//					PhoneLocalServiceUtil.addPhone(mobilePhone); //TODO uncomment
//					AddressLocalServiceUtil.addAddress(builtUserAddr); //TODO uncomment
					if (request == null && request != null) throw new SystemException();
					SessionMessages.add(request,"add_user_sucess", "New User created sucessfully");
				} catch (SystemException e) {
					log.error("Unable to add new user");
				}
			}
			
			response.setPortletMode(PortletMode.VIEW);
	}
	
	
}
