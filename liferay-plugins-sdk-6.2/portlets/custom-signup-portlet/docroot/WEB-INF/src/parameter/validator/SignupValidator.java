package parameter.validator;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.training.amf2.util.MyAmfStringUtil;

import parameter.handler.SignupParamExtractor;

public class SignupValidator {
	
	public List<String> validateFormAndListErrors(
		ThemeDisplay themeDisplay, SignupParamExtractor extractor) 
			throws PortletException {
		_allErrors = new ArrayList<String>();
		
		if (themeDisplay != null) {
			_userLocale = themeDisplay.getLocale();
		}
		
		if (_userLocale == null){
			_userLocale = LocaleUtil.US;
		}
		
		validateFirstname(extractor.getFirstName());
		
		validateLastname(extractor.getLastName());

		validateEmail(extractor.getEmailAddress());

		try {
			validateUsername(themeDisplay, extractor.getUsername());
		} catch (SystemException e) {
			throw new PortletException(e);
		}
		
		String gender = StringUtil.trim(extractor.getGender());
		
		if (Validator.isNull(gender) && !gender.equalsIgnoreCase("male") 
			&& !gender.equalsIgnoreCase("female")){
			addErrorKey("gender-required-error");
		}
		String bmonth = extractor.getBirthdayMonth();
		String bday = extractor.getBirthdayDay();
		String byear = extractor.getBirthdayYear();
		
		validateDateAndAge(bday, bmonth, byear);
		
		validatePasswords(extractor.getPassword1(), extractor.getPassword2());

		validatePhones(
			extractor.getHomePhoneNumber(), extractor.getMobilePhoneNumber());

		validateAddressLines(
			extractor.getStreetAddress1(), extractor.getStreetAddress2());
		
		validateCity(extractor.getCity());
		
		validateState(extractor.getState());
		
		validateZip(extractor.getZip());
		
		String secQuestion = extractor.getSecurityQuestion();
		if (Validator.isNull(secQuestion)){
			addErrorKey("security-question-required-error");
		}
		
		String secAnswer = extractor.getSecurityAnswer();
		validateSecurityAnswer(secAnswer);
		
		String acceptedTou = extractor.getAcceptedTermsOfUse();
		if (Validator.isNull(acceptedTou)){
			addErrorKey("term-of-use-must-accept-error");
		} else if (!acceptedTou.equals("true")){
			addErrorKey("term-of-use-must-accept-error");
		}
		
		return _allErrors;
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
			if (MyAmfStringUtil.isSpecialChar(cur)){
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
	
	private void validateFirstname(String fname){
		if (Validator.isNull(fname)){
			addErrorKey("first-name-required-error");
		} else {
			if (!Validator.isName(fname)){
				addErrorKey("first-name-invalid-characters-error");
			}
			if (fname.length() > 50){
				addErrorKey("first-name-length-error");
			}
		}
	}
	
	private boolean validateLastname(String lname){
		boolean hasError = false;
		if (Validator.isNull(lname)){
			hasError = true;
			addErrorKey("last-name-required-error");
		} else{
			if (!Validator.isName(lname)){
				hasError = true;
				addErrorKey("last-name-invalid-characters-error");
			}
			if (lname.length() > 50){
				hasError = true;
				addErrorKey("last-name-length-error");
			}
		}
		return hasError;
	}
	
	private boolean validateEmail(String emailAddr){
		boolean hasError = false;
		if (Validator.isNull(emailAddr)){
			hasError = true;
			addErrorKey("email-required-error");
		} else{
			if (!Validator.isEmailAddress(emailAddr)){
				hasError = true;
				addErrorKey("email-format-error");
			}
			if (emailAddr.length() > 255){
				hasError = true;
				addErrorKey("email-length-error");
			}
		}
		return hasError;
	}
	
	private boolean validateUsername(ThemeDisplay td, String username) 
			throws SystemException{
		boolean hasError = false;
		if (Validator.isNull(username)){
			hasError = true;
			addErrorKey("username-required-error");
		} else{
			if (!MyAmfStringUtil.isAlphaNumericString(username)){
				hasError = true;
				addErrorKey("username-invalid-characters-error");
			}
			if (username.length() > 50){
				hasError = true;
				addErrorKey("username-length-error");
			}
			if (!MyAmfStringUtil.isUsernameUnique(td, username)){
				hasError = true;
				addErrorKey("username-taken-error");
			}
		}
		return hasError;
	}
	
	private boolean validateDateAndAge(
			String bdayStr, String bmonthStr, String byearStr){
		boolean hasError = false;
		if (Validator.isNull(bdayStr)){
			hasError = true;
			addErrorKey("birthday-day-required-error");
		} else {
			if (!Validator.isNumber(bdayStr)){
				hasError = true;
				addErrorKey("birthday-day-not-numeric-error");
			} else {
				int bday = Integer.parseInt(bdayStr);
				if (bday < 1 || bday > 31){
					hasError = true;
					addErrorKey("birthday-day-range-error");
				}
			}
		}
		if (Validator.isNull(bmonthStr)){
			hasError = true;
			addErrorKey("birthday-month-required-error");
		} else{
			if (!Validator.isNumber(bmonthStr)){
				hasError = true;
				addErrorKey("birthday-month-not-numeric-error");
			} else {
				int bmonth = Integer.parseInt(bmonthStr);
				if (bmonth < 1 || bmonth > 12){
					hasError = true;
					addErrorKey("birthday-month-range-error");
				}
			}
		}
		if (Validator.isNull(byearStr)){
			hasError = true;
			addErrorKey("birthday-year-required-error");
		} else {
			if (!Validator.isNumber(byearStr)){
				hasError = true;
				addErrorKey("birthday-year-not-numeric-error");
			}
		}
		int bm = (!hasError)?Integer.parseInt(bmonthStr):1, 
				bd = (!hasError)?Integer.parseInt(bdayStr):1, 
				by = (!hasError)?Integer.parseInt(byearStr):1;			
		Calendar bCal = new GregorianCalendar(by, bm - 1, by);
		Calendar thirteenYearsAgo = new GregorianCalendar();
		
		//create a Calendar date equal to 13 yeas before today
		
		thirteenYearsAgo.clear(Calendar.AM_PM);
		thirteenYearsAgo.clear(Calendar.MILLISECOND);
		thirteenYearsAgo.clear(Calendar.SECOND);
		thirteenYearsAgo.clear(Calendar.MINUTE);
		thirteenYearsAgo.clear(Calendar.HOUR_OF_DAY);
		thirteenYearsAgo.add(Calendar.YEAR, -13);
		
		if (!Validator.isDate(bm - 1, bd, by)){
			addErrorKey(
				"birthday-year-not-accepted-parameterized-error", bm, bd, by);
		} else {
			bCal.set(by, bm - 1, bd);
			if (bCal.after(thirteenYearsAgo)){
				addErrorKey("birthday-age-requirement-error");
			}
		}
		return hasError;
	}
	
	private boolean validatePasswords(String upass1, String upass2){
		boolean hasError = false;
		if (Validator.isNull(upass1)){
			hasError = true;
			addErrorKey("password-required-error");
		} else if (!meetsPasswordRequirements(upass1)){
			//should not trim
			hasError = true;
			addErrorKey("password-requirements-not-met-error");
		}
		
		return hasError;
	}
	
	private boolean validatePhones(String homeNum, String mobileNum){
		boolean hasError = false;
		if (Validator.isNotNull(homeNum)){
			if (!Validator.isPhoneNumber(homeNum) || homeNum.length() != 10){
				hasError = true;
				addErrorKey("phone-home-invalid-error");
			}
		}
		if (Validator.isNotNull(mobileNum)){
			if (!Validator.isPhoneNumber(mobileNum) 
				|| mobileNum.length() != 10){
				hasError = true;
				addErrorKey("phone-mobile-invalid-error");
			}
		}
		return hasError;
	}
	
	private boolean validateAddressLines(String addr1, String addr2){
		boolean hasError = false;
		
		// spaces are accepted in address, so must be taken into account for
		// length of the string
		
		if (Validator.isNull(addr1)){
			hasError = true;
			addErrorKey("address-line-one-required-error");
		} else {
			if (!MyAmfStringUtil.isAlphaNumericString(addr1.replaceAll(" ", "")) 
				|| addr1.length() > 255) {
				hasError = true;
				addErrorKey("address-line-one-invalid-error");
			}
		}
		
		if (Validator.isNotNull(addr2)) {
			if (!MyAmfStringUtil.isAlphaNumericString(addr2.replaceAll(" ", "")) 
					|| addr2.length() > 255){
				hasError = true;
				addErrorKey("address-line-two-invalid-error");
			}
		}
		return hasError;
	}
	
	private boolean validateCity(String city){
		boolean hasError = false;
		if (Validator.isNull(city)){
			hasError = true;
			addErrorKey("city-required-error");
		} else {
			if (!MyAmfStringUtil.isAlphaNumericString(
				city.replaceAll(" ", ""))){
				hasError = true;
				addErrorKey("city-invalid-characters-error"); 
			}
			else if (city.length() > 255){ // spaces are accepted
				hasError = true;
				addErrorKey("city-length-error");
			}
		}
		return hasError;
	}
	
	private boolean validateState(String state){
		boolean hasError = false;
		if (Validator.isNull(state)){
			hasError = true;
			addErrorKey("region-required-error");
		} else {
			if (!Validator.isNumber(state)){
				hasError = true;
				addErrorKey("region-invalid-error");
			}
		}
		// NOTE: 19 is the country code form Regions table for US
		return hasError;
	}
	
	private boolean validateZip(String zipCode){
		boolean hasError = false;
		if (Validator.isNull(zipCode)){
			hasError = true;
			addErrorKey("zip-required-error");
		} else {
			if (!Validator.isNumber(zipCode) || zipCode.length() != 5){
				hasError = true;
				addErrorKey("zip-invalid-error");
			}
		}
		return hasError;
	}
	
	private boolean validateSecurityAnswer(String secAnswer){
		boolean hasError = false;
		if (Validator.isNull(secAnswer)){
			hasError = true;
			addErrorKey("security-answer-required-error");
		} else {
			//should not trim
			if (!MyAmfStringUtil.isAlphaNumericString(
				secAnswer.replaceAll(" ", ""))){
				hasError = true;
				addErrorKey("security-answer-invalid-characters-error");
			}
			if (secAnswer.length() > 255){
				hasError = true;
				addErrorKey("security-answer-length-error");
			}
		}
		return hasError;
	}
	private void addErrorKey(String key, Object ... vars) {
		String parameterizedString = LanguageUtil.get(_userLocale, key);
		String injectedString = String.format(parameterizedString, vars);
		_allErrors.add(injectedString);
	}
	
	private void addErrorKey(String key){
		_allErrors.add(LanguageUtil.get(_userLocale, key));
	}
	
	private List<String> _allErrors;
	private Locale _userLocale;	
	
}
