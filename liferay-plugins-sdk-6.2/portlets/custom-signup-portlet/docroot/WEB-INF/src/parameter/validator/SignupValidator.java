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
import com.liferay.training.amf2.constants.MySignupConstants;
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
		
		_validateFirstname(extractor.getFirstName());
		
		_validateLastname(extractor.getLastName());

		_validateEmail(extractor.getEmailAddress());

		try {
			_validateUsername(themeDisplay, extractor.getUsername());
		} catch (SystemException e) {
			throw new PortletException(e);
		}
		
		String gender = StringUtil.trim(extractor.getGender());
		
		if (Validator.isNull(gender) 
			&& !gender.equalsIgnoreCase(MySignupConstants.MALE_STRING_VALUE) 
			&& !gender.equalsIgnoreCase(MySignupConstants.FEMALE_STRING_VALUE)){
			_addErrorKey("gender-required-error");
		}
		String bmonth = extractor.getBirthdayMonth();
		String bday = extractor.getBirthdayDay();
		String byear = extractor.getBirthdayYear();
		
		_validateDateAndAge(bday, bmonth, byear);
		
		_validatePasswords(extractor.getPassword1(), extractor.getPassword2());

		_validatePhones(
			extractor.getHomePhoneNumber(), extractor.getMobilePhoneNumber());

		_validateAddressLines(
			extractor.getStreetAddress1(), extractor.getStreetAddress2());
		
		_validateCity(extractor.getCity());
		
		_validateState(extractor.getState());
		
		_validateZip(extractor.getZip());
		
		String secQuestion = extractor.getSecurityQuestion();
		if (Validator.isNull(secQuestion)){
			_addErrorKey("security-question-required-error");
		}
		
		String secAnswer = extractor.getSecurityAnswer();
		_validateSecurityAnswer(secAnswer);
		
		String acceptedTou = extractor.getAcceptedTermsOfUse();
		if (Validator.isNull(acceptedTou)){
			_addErrorKey("term-of-use-must-accept-error");
		} else if (!acceptedTou.equals("true")){
			_addErrorKey("term-of-use-must-accept-error");
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
	
	private void _validateFirstname(String fname){
		if (Validator.isNull(fname)){
			_addErrorKey("first-name-required-error");
		} else {
			if (!Validator.isName(fname)){
				_addErrorKey("first-name-invalid-characters-error");
			}
			if (fname.length() > 50){
				_addErrorKey("first-name-length-error");
			}
		}
	}
	
	private boolean _validateLastname(String lname){
		boolean hasError = false;
		if (Validator.isNull(lname)){
			hasError = true;
			_addErrorKey("last-name-required-error");
		} else{
			if (!Validator.isName(lname)){
				hasError = true;
				_addErrorKey("last-name-invalid-characters-error");
			}
			if (lname.length() > 50){
				hasError = true;
				_addErrorKey("last-name-length-error");
			}
		}
		return hasError;
	}
	
	private boolean _validateEmail(String emailAddr){
		boolean hasError = false;
		if (Validator.isNull(emailAddr)){
			hasError = true;
			_addErrorKey("email-required-error");
		} else{
			if (!Validator.isEmailAddress(emailAddr)){
				hasError = true;
				_addErrorKey("email-format-error");
			}
			if (emailAddr.length() > 255){
				hasError = true;
				_addErrorKey("email-length-error");
			}
		}
		return hasError;
	}
	
	private boolean _validateUsername(ThemeDisplay td, String username) 
			throws SystemException{
		boolean hasError = false;
		if (Validator.isNull(username)){
			hasError = true;
			_addErrorKey("username-required-error");
		} else{
			if (!MyAmfStringUtil.isAlphaNumericString(username)){
				hasError = true;
				_addErrorKey("username-invalid-characters-error");
			}
			if (username.length() > 50){
				hasError = true;
				_addErrorKey("username-length-error");
			}
			if (!MyAmfStringUtil.isUsernameUnique(td, username)){
				hasError = true;
				_addErrorKey("username-taken-error");
			}
		}
		return hasError;
	}
	
	private boolean _validateDateAndAge(
			String bdayStr, String bmonthStr, String byearStr){
		boolean hasError = false;
		if (Validator.isNull(bdayStr)){
			hasError = true;
			_addErrorKey("birthday-day-required-error");
		} else {
			if (!Validator.isNumber(bdayStr)){
				hasError = true;
				_addErrorKey("birthday-day-not-numeric-error");
			} else {
				int bday = Integer.parseInt(bdayStr);
				if (bday < 1 || bday > 31){
					hasError = true;
					_addErrorKey("birthday-day-range-error");
				}
			}
		}
		if (Validator.isNull(bmonthStr)){
			hasError = true;
			_addErrorKey("birthday-month-required-error");
		} else{
			if (!Validator.isNumber(bmonthStr)){
				hasError = true;
				_addErrorKey("birthday-month-not-numeric-error");
			} else {
				int bmonth = Integer.parseInt(bmonthStr);
				if (bmonth < 1 || bmonth > 12){
					hasError = true;
					_addErrorKey("birthday-month-range-error");
				}
			}
		}
		if (Validator.isNull(byearStr)){
			hasError = true;
			_addErrorKey("birthday-year-required-error");
		} else {
			if (!Validator.isNumber(byearStr)){
				hasError = true;
				_addErrorKey("birthday-year-not-numeric-error");
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
			_addErrorKey(
				"birthday-year-not-accepted-parameterized-error", bm, bd, by);
		} else {
			bCal.set(by, bm - 1, bd);
			if (bCal.after(thirteenYearsAgo)){
				_addErrorKey("birthday-age-requirement-error");
			}
		}
		return hasError;
	}
	
	private boolean _validatePasswords(String upass1, String upass2){
		boolean hasError = false;
		if (Validator.isNull(upass1)){
			hasError = true;
			_addErrorKey("password-required-error");
		} else if (!meetsPasswordRequirements(upass1)){
			//should not trim
			hasError = true;
			_addErrorKey("password-requirements-not-met-error");
		}
		
		return hasError;
	}
	
	private boolean _validatePhones(String homeNum, String mobileNum){
		boolean hasError = false;
		if (Validator.isNotNull(homeNum)){
			if (!Validator.isPhoneNumber(homeNum) || homeNum.length() != 10){
				hasError = true;
				_addErrorKey("phone-home-invalid-error");
			}
		}
		if (Validator.isNotNull(mobileNum)){
			if (!Validator.isPhoneNumber(mobileNum) 
				|| mobileNum.length() != 10){
				hasError = true;
				_addErrorKey("phone-mobile-invalid-error");
			}
		}
		return hasError;
	}
	
	private boolean _validateAddressLines(String addr1, String addr2){
		boolean hasError = false;
		
		// spaces are accepted in address, so must be taken into account for
		// length of the string
		
		if (Validator.isNull(addr1)){
			hasError = true;
			_addErrorKey("address-line-one-required-error");
		} else {
			if (!MyAmfStringUtil.isAlphaNumericString(addr1.replaceAll(" ", "")) 
				|| addr1.length() > 255) {
				hasError = true;
				_addErrorKey("address-line-one-invalid-error");
			}
		}
		
		if (Validator.isNotNull(addr2)) {
			if (!MyAmfStringUtil.isAlphaNumericString(addr2.replaceAll(" ", "")) 
					|| addr2.length() > 255){
				hasError = true;
				_addErrorKey("address-line-two-invalid-error");
			}
		}
		return hasError;
	}
	
	private boolean _validateCity(String city){
		boolean hasError = false;
		if (Validator.isNull(city)){
			hasError = true;
			_addErrorKey("city-required-error");
		} else {
			if (!MyAmfStringUtil.isAlphaNumericString(
				city.replaceAll(" ", ""))){
				hasError = true;
				_addErrorKey("city-invalid-characters-error"); 
			}
			else if (city.length() > 255){ // spaces are accepted
				hasError = true;
				_addErrorKey("city-length-error");
			}
		}
		return hasError;
	}
	
	private boolean _validateState(String state){
		boolean hasError = false;
		if (Validator.isNull(state)){
			hasError = true;
			_addErrorKey("region-required-error");
		} else {
			if (!Validator.isNumber(state)){
				hasError = true;
				_addErrorKey("region-invalid-error");
			}
		}
		// NOTE: 19 is the country code form Regions table for US
		return hasError;
	}
	
	private boolean _validateZip(String zipCode){
		boolean hasError = false;
		if (Validator.isNull(zipCode)){
			hasError = true;
			_addErrorKey("zip-required-error");
		} else {
			if (!Validator.isNumber(zipCode) || zipCode.length() != 5){
				hasError = true;
				_addErrorKey("zip-invalid-error");
			}
		}
		return hasError;
	}
	
	private boolean _validateSecurityAnswer(String secAnswer){
		boolean hasError = false;
		if (Validator.isNull(secAnswer)){
			hasError = true;
			_addErrorKey("security-answer-required-error");
		} else {
			//should not trim
			if (!MyAmfStringUtil.isAlphaNumericString(
				secAnswer.replaceAll(" ", ""))){
				hasError = true;
				_addErrorKey("security-answer-invalid-characters-error");
			}
			if (secAnswer.length() > 255){
				hasError = true;
				_addErrorKey("security-answer-length-error");
			}
		}
		return hasError;
	}
	private void _addErrorKey(String key, Object ... vars) {
		String parameterizedString = LanguageUtil.get(_userLocale, key);
		String injectedString = String.format(parameterizedString, vars);
		_allErrors.add(injectedString);
	}
	
	private void _addErrorKey(String key){
		_allErrors.add(LanguageUtil.get(_userLocale, key));
	}
	
	private List<String> _allErrors;
	private Locale _userLocale;	
	
}
