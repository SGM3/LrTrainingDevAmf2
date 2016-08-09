package com.liferay.training.amf2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Region;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RegionServiceUtil;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserLocalServiceUtil;
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
		uname = uname.trim();
		ThemeDisplay themeDisplay = (ThemeDisplay) req.getAttribute(WebKeys.THEME_DISPLAY);
		long groupId = themeDisplay.getScopeGroupId();
		long companyId = themeDisplay.getCompanyId();
		try {
			long id = UserLocalServiceUtil.getUserIdByScreenName(companyId, uname);
		} catch (PortalException e) {
			return true;
		} catch (SystemException e) {
			log.error("Unable to query User table");
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
			log.error("Unable to fetch Region aread codes for US (Country ID 19)");
		}//19 is country code for US
		return false;
	}
	
	@Override
	public void processAction(ActionRequest request, ActionResponse response)
			throws IOException, PortletException {
			boolean hasError = false;
			ArrayList<String> allErrors = new ArrayList<String>();
			User builtUser = null;
				
			String fname = ParamUtil.getString(request, FIRSTN_PARAM);
			if (Validator.isNull(fname)){
				hasError = true;
				allErrors.add("First name is required.");
			} else{
				if (!Validator.isName(fname)){
					hasError = true;
					allErrors.add("First name must be alpha numeric.");
				}
				if (fname.length() > 50){
					hasError = true;
					allErrors.add("First name is too long.");
				}
			}
			
			String lname = ParamUtil.getString(request, LASTN_PARAM);
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
			
			String emailAddr = ParamUtil.getString(request, EMAIL_PARAM);
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
			
			String username = ParamUtil.getString(request, UNAME_PARAM);
			if (Validator.isNull(username)){
				hasError = true;
				allErrors.add("Username is required.");
			} else{
				if (!isAlphaNumericString(username)){
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
			
			String gender = ParamUtil.getString(request, GENDER_PARAM);
			if (Validator.isNull(gender)){
				hasError = true;
				allErrors.add("Gender is required.");
			}
			
			String bmonth = ParamUtil.getString(request, BMONTH_PARAM);
			if (Validator.isNull(bmonth)){
				hasError = true;
				allErrors.add("Birthday month is required.");
			} else if (!Validator.isNumber(bmonth)){
				hasError = true;
				allErrors.add("Numeric birthday month expected.");
			} else if (Integer.parseInt(bmonth) < 1 || Integer.parseInt(bmonth) > 12){
				hasError = true;
				allErrors.add("Birthday month must be in range [1-12].");
			}
			
			String bday = ParamUtil.getString(request, BDAY_PARAM);
			if (Validator.isNull(bday)){
				hasError = true;
				allErrors.add("Birthday day is required.");
			} else if (!Validator.isNumber(bday)){
				hasError = true;
				allErrors.add("Numeric birthday day expected.");
			} else if (Integer.parseInt(bday) < 1 || Integer.parseInt(bday) > 31){
				hasError = true;
				allErrors.add("Birthday day must be in range [1-31].");
			}
			
			String byear = ParamUtil.getString(request, BYEAR_PARAM);
			if (Validator.isNull(byear)){
				hasError = true;
				allErrors.add("Birthday year is required.");
			} else if (!Validator.isNumber(byear)){
				hasError = true;
				allErrors.add("Numeric year month expected.");
			}
			//TODO Must be 13+
			
			String upass1 = ParamUtil.getString(request, UPASS1_PARAM);
			if (Validator.isNull(upass1)){
				hasError = true;
				allErrors.add("Password is required.");
			} else if (meetsPasswordRequirements(upass1)){
				hasError = true;
				allErrors.add("Does not meet minimum password requirements.(" 
								+ MIN_PASS_REQ + ").");
			}
			
			String upass2 = ParamUtil.getString(request, UPASS2_PARAM);
			if (Validator.isNotNull(upass2) && !upass2.equals(upass1)){
				allErrors.add("Does not match password 1.");
			}
			

			String homeNum = ParamUtil.getString(request, HOMEPHONE_PARAM);
			if (Validator.isNotNull(homeNum)){
				if (!Validator.isPhoneNumber(homeNum) || homeNum.length() != 10){
					hasError = true;
					allErrors.add("Home phone number invalid.");
				}
			}
			
			String mobileNum = ParamUtil.getString(request, MOBILEPHONE_PARAM);
			if (Validator.isNotNull(mobileNum)){
				if (!Validator.isPhoneNumber(mobileNum) || mobileNum.length() != 10){
					hasError = true;
					allErrors.add("Mobile phone number invalid.");
				}
			}

			String addr1 = ParamUtil.getString(request, ADDR1_PARAM);
			if (Validator.isNull(addr1)){
				hasError = true;
				allErrors.add("Address is required.");
			} else if (!isAlphaNumericString(addr1.replaceAll(" ", "")) 
					|| addr1.length() > 255){ // spaces are accepted
				hasError = true;
				allErrors.add("Address is invalid.");
			}
			
			String addr2 = ParamUtil.getString(request, ADDR2_PARAM);
			if (Validator.isNotNull(addr2)){
				if (!isAlphaNumericString(addr2.replaceAll(" ", "")) 
						|| addr2.length() > 255){ // spaces are accepted
					hasError = true;
					allErrors.add("Address 2 is invalid.");
				}
			}
			
			String city = ParamUtil.getString(request, CITY_PARAM);
			if (Validator.isNull(city)){
				hasError = true;
				allErrors.add("City is required.");
			} else if (!isAlphaNumericString(city.replaceAll(" ", "")) 
					|| city.length() > 255){ // spaces are accepted
				hasError = true;
				allErrors.add("City is invalid.");
			}
			
			String state = ParamUtil.getString(request, STATE_PARAM);
			if (Validator.isNull(state)){
				hasError = true;
				allErrors.add("State is required.");
			} else if (isValidRegionCode(request, state)){ 
				allErrors.add("State is invalid. (Must use LifeRay State code)");
			}
			
			String zipCode = ParamUtil.getString(request, ZIP_PARAM);
			if (Validator.isNull(zipCode)){
				hasError = true;
				allErrors.add("Zip code is required.");
			} else if (!Validator.isNumber(zipCode) || zipCode.length() != 5){
				allErrors.add("Zip code is invalid.");
			}

			
			String secQuestion = ParamUtil.getString(request, SECQ_PARAM);
			if (Validator.isNull(secQuestion)){
				hasError = true;
				allErrors.add("A security question is required.");
			}
			
			String secAnswer = ParamUtil.getString(request, SECA_PARAM);
			if (Validator.isNull(secAnswer)){
				hasError = true;
				allErrors.add("An anwser to the security question is required.");
			}
			
			String acceptedTou = ParamUtil.getString(request, ATOU_PARAM);
			if (Validator.isNull(acceptedTou)){
				hasError = true;
				allErrors.add("Terms of user must be accepted to continue.");
			} else if (!acceptedTou.equals("true")){
				hasError = true;
				allErrors.add("Terms of user must be accepted to continue.");
			}
			
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
			System.out.println("acceptedTou: " + acceptedTou);



			if (hasError) {
				SessionErrors.add(request,"basic_error");
				request.getPortletSession().setAttribute("bInfoErrorList", allErrors.toArray());
			} else {
				try {
//					UserLocalServiceUtil.addUser(builtUser); //TODO uncomment
					if (builtUser == null && builtUser != null)throw new SystemException();
					SessionMessages.add(request,"add_user_sucess", "New User created sucessfully");
				} catch (SystemException e) {
					log.error("Unable to add new user");
				}
			}
			
			response.setPortletMode(PortletMode.VIEW);
	}
	
	
}
