package com.liferay.training.amf2.parameter.validator;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.training.amf2.constants.MySignupConstants;
import com.liferay.training.amf2.util.MyAmfUtil;

public class SignupValidator {
	
	public SignupValidator() {
		_validatorHasRun = false;
	}
	
	/**
	 * Validate the elements from a form
	 * 
	 * @param password1 New user's enter password.
	 * @param password2 New user's enter password confirmation.
	 * @param screenName New user's requesting screenname
	 * @param emailAddress New user's email adress.
	 * @param locale The locale to use.
	 * @param firstName New user's first name.
	 * @param lastName New user's first name.
	 * @param male True if user is male.
	 * @param birthdayMonth Month of year (Starting from 1).
	 * @param birthdayDay Day of month (Starting from 1).
	 * @param birthdayYear Four digit year.
	 * @param street1 Street address, line 1.
	 * @param street2 <code>[NULLABLE]</code> Street address, line 2.
	 * @param city City
	 * @param zip Zip
	 * @param regionId Liferay specific Region code
	 * @param homeNumber <code>[NULLABLE]</code> 10 digit number (no formatting characters accepted).
	 * @param mobileNumber <code>[NULLABLE]</code> 10 digit number (no formatting characters accepted).
	 * @param securityQuestionKey The locale key to the security question.
	 * @param secuirtyAnswer The raw answer to the question
	 * @param atou True if accept the the "Terms of use"
	 * @return
	 * @throws PortletException
	 */
	public List<String> validateFormAndListErrors(String password1,
			String password2, String screenName, String emailAddress, 
			Locale locale, String firstName, String lastName, boolean male,
			int birthdayMonth, int birthdayDay, int birthdayYear,
			String street1, String street2, String city, String zip,
			long regionId, String homeNumber, String mobileNumber,
			String securityQuestionKey, String secuirtyAnswer, boolean atou) 
		throws PortletException {

		_validatorHasRun = true;
		_allErrors = new ArrayList<String>();
		
		if (locale != null) {
			_userLocale = locale;
		} else {
			_userLocale = LocaleUtil.US;
		}
		
		_validateFirstname(firstName);
		
		_validateLastname(lastName);

		_validateEmail(emailAddress);

		try {
			_validateUsername(screenName);
		} catch (SystemException e) {
			throw new PortletException(e);
		}
		
		_validateDateAndAge(birthdayDay, birthdayMonth, birthdayYear);
		
		_validatePasswords(password1, password2);

		_validatePhones(homeNumber, mobileNumber);

		_validateAddressLines(street1, street2);
		
		_validateCity(city);
		
		_validateState(zip);
		
		_validateZip(zip);
		
		String secQuestion = securityQuestionKey;
		if (Validator.isNull(secQuestion)){
			_addErrorKey("security-question-required-error");
		}
		
		String secAnswer = secuirtyAnswer;
		_validateSecurityAnswer(secAnswer);
		
		if (!atou){
			_addErrorKey("term-of-use-must-accept-error");
		}
		
		return _allErrors;
	}
	
	public List<String> getErrors(){
		if (!_validatorHasRun){
			throw new IllegalAccessError(
				"validatFormAndListErrors must be be run once.");
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
			if (MyAmfUtil.isSpecialChar(cur)){
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
	
	private boolean _validateUsername(String username) 
			throws SystemException{
		boolean hasError = false;
		if (Validator.isNull(username)){
			hasError = true;
			_addErrorKey("username-required-error");
		} else{
			if (!MyAmfUtil.isAlphaNumericString(username)){
				hasError = true;
				_addErrorKey("username-invalid-characters-error");
			}
			if (username.length() > 50){
				hasError = true;
				_addErrorKey("username-length-error");
			}
			if (!MyAmfUtil.isUsernameUnique(username)){
				hasError = true;
				_addErrorKey("username-taken-error");
			}
		}
		return hasError;
	}
	
	private boolean _validateDateAndAge(int bd, int bm, int by) {			
		Calendar bCal = new GregorianCalendar(by, bm - 1, by);
		Calendar thirteenYearsAgo = new GregorianCalendar();
		boolean hasDateFormatError = false;
		
		// if flagged number is used, we can assume
		// it was originally a bad formatted number 
		
		if (bm == MySignupConstants.BAD_INPUT_FLAG) {
			hasDateFormatError = true;
			_addErrorKey("birthday-month-not-numeric-error");
		}
		if (bd == MySignupConstants.BAD_INPUT_FLAG) {
			hasDateFormatError = true;
			_addErrorKey("birthday-day-not-numeric-error");
		}
		if (by == MySignupConstants.BAD_INPUT_FLAG) {
			hasDateFormatError = true;
			_addErrorKey("birthday-year-not-numeric-error");
		}
		
		if (hasDateFormatError){
			return false;
		}
		
		
		if (bd < 1 || bd > 31){
			hasDateFormatError = true;
			_addErrorKey("birthday-day-range-error");
		}
		if (bm < 1 || bm > 12){
			hasDateFormatError = true;
			_addErrorKey("birthday-month-range-error");
		}
		
		if (hasDateFormatError){
			return false;
		}
		
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
			return false;
		} else {
			bCal.set(by, bm - 1, bd);
			if (bCal.after(thirteenYearsAgo)){
				_addErrorKey("birthday-age-requirement-error");
				return false;
			}
		}
		return true;
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
		} else if (!upass1.equals(upass2)){
			hasError = true;
			_addErrorKey("password-do-not-match-error");
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
			if (!MyAmfUtil.isAlphaNumericString(addr1.replaceAll(" ", "")) 
				|| addr1.length() > 255) {
				hasError = true;
				_addErrorKey("address-line-one-invalid-error");
			}
		}
		
		if (Validator.isNotNull(addr2)) {
			if (!MyAmfUtil.isAlphaNumericString(addr2.replaceAll(" ", "")) 
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
			if (!MyAmfUtil.isAlphaNumericString(
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
			if (!MyAmfUtil.isAlphaNumericString(
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
	
	private boolean _validatorHasRun;
	
}
