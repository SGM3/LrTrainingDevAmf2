package parameter.handler;

public interface SignupParamExtractor {

	public boolean isSignedIn();

	public String getGender();

	public String getBirthdayDay();

	public String getBirthdayMonth();

	public String getPassword1();

	public String getUsername();

	public String getEmailAddress();

	public String getBirthdayYear();

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
