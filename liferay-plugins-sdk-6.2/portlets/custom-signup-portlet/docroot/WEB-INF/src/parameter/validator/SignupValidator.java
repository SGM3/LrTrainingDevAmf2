package parameter.validator;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.portlet.PortletException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.training.amf2.util.MyAmfStringUtil;

import parameter.handler.SignupParamExtractor;

public class SignupValidator {
	
	public List<String> validateAndListErrors(
		ThemeDisplay themeDisplay, SignupParamExtractor extractor) 
			throws PortletException {
		_allErrors = new ArrayList<String>();
		
		validateFirstname(extractor.getFirstName());
		
		validateLastname(extractor.getLastName());

		validateEmail(extractor.getEmailAddress());

		try {
			validateUsername(themeDisplay, extractor.getUsername());
		} catch (SystemException e) {
			throw new PortletException(e);
		}
		
		String gender = StringUtil.trim(extractor.getGender());
		
		if (Validator.isNull(gender)  && !gender.equalsIgnoreCase("male") 
			&& !gender.equalsIgnoreCase("female")){
			_allErrors.add("Gender is required.");
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
			_allErrors.add("A security question is required.");
		}
		
		String secAnswer = extractor.getSecurityAnswer();
		validateSecurityAnswer(secAnswer);
		
		String acceptedTou = extractor.getAcceptedTermsOfUse();
		if (Validator.isNull(acceptedTou)){
			_allErrors.add("Terms of user must be accepted to continue.");
		} else if (!acceptedTou.equals("true")){
			_allErrors.add("Terms of user must be accepted to continue.");
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
			_allErrors.add("First name is required.");
		} else {
			if (!Validator.isName(fname)){
				_allErrors.add("First name must be alpha numeric.");
			}
			if (fname.length() > 50){
				_allErrors.add("First name is too long.");
			}
		}
	}
	
	private boolean validateLastname(String lname){
		boolean hasError = false;
		if (Validator.isNull(lname)){
			hasError = true;
			_allErrors.add("Last name is required.");
		} else{
			if (!Validator.isName(lname)){
				hasError = true;
				_allErrors.add("Last name must be alpha numeric.");
			}
			if (lname.length() > 50){
				hasError = true;
				_allErrors.add("Last name is too long.");
			}
		}
		return hasError;
	}
	
	private boolean validateEmail(String emailAddr){
		boolean hasError = false;
		if (Validator.isNull(emailAddr)){
			hasError = true;
			_allErrors.add("Email is required.");
		} else{
			if (!Validator.isEmailAddress(emailAddr)){
				hasError = true;
				_allErrors.add("Email is in invalid format.");
			}
			if (emailAddr.length() > 255){
				hasError = true;
				_allErrors.add("Email is too long.");
			}
		}
		return hasError;
	}
	
	private boolean validateUsername(ThemeDisplay td, String username) 
			throws SystemException{
		boolean hasError = false;
		if (Validator.isNull(username)){
			hasError = true;
			_allErrors.add("Username is required.");
		} else{
			if (!MyAmfStringUtil.isAlphaNumericString(username)){
				hasError = true;
				_allErrors.add("Username must be an alpha numeric string.");
			}
			if (username.length() > 50){
				hasError = true;
				_allErrors.add("Username is too long.");
			}
			if (!MyAmfStringUtil.isUsernameUnique(td, username)){
				hasError = true;
				_allErrors.add("Username is aleady taken.");
			}
		}
		return hasError;
	}
	
	private boolean validateDateAndAge(
			String bdayStr, String bmonthStr, String byearStr){
		boolean hasError = false;
		if (Validator.isNull(bdayStr)){
			hasError = true;
			_allErrors.add("Birthday day is required.");
		} else {
			if (!Validator.isNumber(bdayStr)){
				hasError = true;
				_allErrors.add("Numeric birthday day expected.");
			} else {
				int bday = Integer.parseInt(bdayStr);
				if (bday < 1 || bday > 31){
					hasError = true;
					_allErrors.add("Birthday day must be in range [1-31].");
				}
			}
		}
		if (Validator.isNull(bmonthStr)){
			hasError = true;
			_allErrors.add("Birthday month is required.");
		} else{
			if (!Validator.isNumber(bmonthStr)){
				hasError = true;
				_allErrors.add("Numeric birthday month expected.");
			} else {
				int bmonth = Integer.parseInt(bmonthStr);
				if (bmonth < 1 || bmonth > 12){
					hasError = true;
					_allErrors.add("Birthday month must be in range [1-12].");
				}
			}
		}
		if (Validator.isNull(byearStr)){
			hasError = true;
			_allErrors.add("Birthday year is required.");
		} else {
			if (!Validator.isNumber(byearStr)){
				hasError = true;
				_allErrors.add("Numeric year month expected.");
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
			String gregDate = String.format("%02d/%02d/%d", bm, bd, by);
			_allErrors.add(
				"The date \"" + gregDate 
				+ "\" (format: MM/DD/YYYY) is not valid.");
		} else {
			bCal.set(by, bm - 1, bd);
			if (bCal.after(thirteenYearsAgo)){
				_allErrors.add("Must be at least 13 years old to sign up.");
			}
		}
		return hasError;
	}
	
	private boolean validatePasswords(String upass1, String upass2){
		boolean hasError = false;
		if (Validator.isNull(upass1)){
			hasError = true;
			_allErrors.add("Password is required.");
		} else if (!meetsPasswordRequirements(upass1)){
			//should not trim
			hasError = true;
			_allErrors.add("Does not meet minimum password requirements.(" 
							+ _MIN_PASS_REQ + ").");
		}
		
		return hasError;
	}
	
	private boolean validatePhones(String homeNum, String mobileNum){
		boolean hasError = false;
		if (Validator.isNotNull(homeNum)){
			if (!Validator.isPhoneNumber(homeNum) || homeNum.length() != 10){
				hasError = true;
				_allErrors.add("Home phone number invalid.");
			}
		}
		if (Validator.isNotNull(mobileNum)){
			if (!Validator.isPhoneNumber(mobileNum) 
				|| mobileNum.length() != 10){
				hasError = true;
				_allErrors.add("Mobile phone number invalid.");
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
			_allErrors.add("Address is required.");
		} else {
			if (!MyAmfStringUtil.isAlphaNumericString(addr1.replaceAll(" ", "")) 
				|| addr1.length() > 255) {
				hasError = true;
				_allErrors.add("Address is invalid.");
			}
		}
		
		if (Validator.isNotNull(addr2)) {
			if (!MyAmfStringUtil.isAlphaNumericString(addr2.replaceAll(" ", "")) 
					|| addr2.length() > 255){
				hasError = true;
				_allErrors.add("Address 2 is invalid.");
			}
		}
		return hasError;
	}
	
	private boolean validateCity(String city){
		boolean hasError = false;
		if (Validator.isNull(city)){
			hasError = true;
			_allErrors.add("City is required.");
		} else {
			if (!MyAmfStringUtil.isAlphaNumericString(city.replaceAll(" ", "")) 
				|| city.length() > 255){ // spaces are accepted
				hasError = true;
				_allErrors.add("City is invalid.");
			}
		}
		return hasError;
	}
	
	private boolean validateState(String state){
		boolean hasError = false;
		if (Validator.isNull(state)){
			hasError = true;
			_allErrors.add("State is required.");
		} else {
			if (!Validator.isNumber(state)){
				hasError = true;
				_allErrors.add("State is invalid. (Must use LifeRay State code)");
			}
		}
		// NOTE: 19 is the country code form Regions table for US
		return hasError;
	}
	
	private boolean validateZip(String zipCode){
		boolean hasError = false;
		if (Validator.isNull(zipCode)){
			hasError = true;
			_allErrors.add("Zip code is required.");
		} else {
			if (!Validator.isNumber(zipCode) || zipCode.length() != 5){
				hasError = true;
				_allErrors.add("Zip code is invalid.");
			}
		}
		return hasError;
	}
	
	private boolean validateSecurityAnswer(String secAnswer){
		boolean hasError = false;
		if (Validator.isNull(secAnswer)){
			hasError = true;
			_allErrors.add("An anwser to the security question is required.");
		} else {
			//should not trim
			if (!MyAmfStringUtil.isAlphaNumericString(
				secAnswer.replaceAll(" ", ""))){
				hasError = true;
				_allErrors.add("Security answer must be alph numeric.");
			}
			if (secAnswer.length() > 255){
				hasError = true;
				_allErrors.add("Security answer is too long.");
			}
		}
		return hasError;
	}

	
	private static final String _MIN_PASS_REQ = "6 characters long, must "
			+ "contain at least one of each: uppercase letter, number, "
			+ "special character";
	private List<String> _allErrors;
	
	
}
