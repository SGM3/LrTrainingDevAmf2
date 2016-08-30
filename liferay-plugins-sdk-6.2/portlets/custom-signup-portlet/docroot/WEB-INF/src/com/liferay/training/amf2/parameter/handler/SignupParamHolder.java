package com.liferay.training.amf2.parameter.handler;

public interface SignupParamHolder {

	public boolean isSignedIn();

	public String getGender();

	public int getBirthdayDay();

	public int getBirthdayMonth();

	public int getBirthdayYear();

	public String getPassword1();

	public String getUsername();

	public String getEmailAddress();

	public String getFirstName();

	public String getLastName();

	public String getPassword2();

	public String getStreetAddress1();

	public String getStreetAddress2();

	public String getCity();

	public String getState();

	public String getZip();

	public String getHomePhoneNumber();

	public String getMobilePhoneNumber();

	public String getSecurityQuestion();

	public String getSecurityAnswer();

	public String getAcceptedTermsOfUse();

	public long getCountryId();

}
