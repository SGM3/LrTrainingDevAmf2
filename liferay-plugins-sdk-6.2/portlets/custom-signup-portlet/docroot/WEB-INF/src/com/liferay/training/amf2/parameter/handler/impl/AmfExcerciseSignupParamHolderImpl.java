package com.liferay.training.amf2.parameter.handler.impl;

import javax.portlet.ActionRequest;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.service.CountryServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.training.amf2.parameter.handler.SignupParamHolder;

import static com.liferay.training.amf2.constants.MySignupConstants.*;

public class AmfExcerciseSignupParamHolderImpl 
	implements SignupParamHolder{
	
	public AmfExcerciseSignupParamHolderImpl(ActionRequest request){
		try {
			US_COUNTRY_CODE = 
				CountryServiceUtil.getCountryByA2("US").getCountryId();
		} catch (Exception e) {
			
			// US country ID in 6.2 is 19
			// as found in the 'country' table
			
			US_COUNTRY_CODE = 19L;
		}
		_actionRequest = request;
	}
	
	public boolean isSignedIn(){
		ThemeDisplay themeDisplay = (ThemeDisplay) _actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);
		return themeDisplay.isSignedIn();
	}
	
	public String getGender(){
		return StringUtil.trim(ParamUtil.getString(
			_actionRequest, GENDER_PARAM));
	}
	
	public String getBirthdayDay(){
		return StringUtil.trim(ParamUtil.getString(
			_actionRequest, BDAY_PARAM));
	}
	
	public String getBirthdayMonth() {
		return StringUtil.trim(ParamUtil.getString(
			_actionRequest, BMONTH_PARAM));
	}
	
	public String getPassword1() {
		return ParamUtil.getString(_actionRequest, UPASS1_PARAM);
	}
	
	public String getUsername() {
		return StringUtil.trim(ParamUtil.getString(
			_actionRequest, UNAME_PARAM));
	}
	
	public String getEmailAddress() {
		return StringUtil.trim(ParamUtil.getString(
			_actionRequest, EMAIL_PARAM));
	}
	
	public String getBirthdayYear() {
		return StringUtil.trim(ParamUtil.getString(
			_actionRequest, BYEAR_PARAM));
	}
	
	public String getFirstName() {
		return StringUtil.trim(ParamUtil.getString(
			_actionRequest, FIRSTN_PARAM));
	}
	
	public String getLastName() {
		return StringUtil.trim(ParamUtil.getString(
			_actionRequest, LASTN_PARAM));
	}
	
	public String getPassword2() {
		return ParamUtil.getString(
			_actionRequest, UPASS2_PARAM);
	}
	
	public String getStreetAddress1() {
		return StringUtil.trim(ParamUtil.getString(
			_actionRequest, ADDR1_PARAM));
	}
	
	public String getStreetAddress2() {
		return StringUtil.trim(ParamUtil.getString(
			_actionRequest, ADDR2_PARAM));
	}
	
	public String getCity() {
		return StringUtil.trim(ParamUtil.getString(
			_actionRequest, CITY_PARAM));
	}
	
	public String getState(){
		return StringUtil.trim(ParamUtil.getString(
			_actionRequest, STATE_PARAM));
	}
	
	public String getZip() {
		return StringUtil.trim(ParamUtil.getString(
			_actionRequest, ZIP_PARAM));
	}
	
	public String getHomePhoneNumber(){
		return StringUtil.trim(ParamUtil.getString(
			_actionRequest, HOMEPHONE_PARAM));
	}
	
	public String getMobilePhoneNumber(){
		return StringUtil.trim(ParamUtil.getString(
			_actionRequest, MOBILEPHONE_PARAM));
	}
	
	public String getSecurityQuestion(){
		return ParamUtil.getString(_actionRequest, SECQ_PARAM);
	}
	
	public String getSecurityAnswer(){
		return ParamUtil.getString(_actionRequest, SECA_PARAM);
	}
	
	public String getAcceptedTermsOfUse(){
		return ParamUtil.getString(_actionRequest, ATOU_PARAM);
	}
	
	public long getCountryId() {
		return US_COUNTRY_CODE;
	}
	
	private static long US_COUNTRY_CODE;
	private ActionRequest _actionRequest;
}
