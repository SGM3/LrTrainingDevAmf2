package com.liferay.training.amf2.util;

import java.util.List;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.ListType;
import com.liferay.portal.model.ListTypeConstants;
import com.liferay.portal.model.Region;
import com.liferay.portal.service.CountryServiceUtil;
import com.liferay.portal.service.ListTypeServiceUtil;
import com.liferay.portal.service.RegionServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

public final class MyAmfUtil {
	
	public static boolean isPrintableAscii(char ch){
		
		// if is a control character (or del):
		// https://en.wikipedia.org/wiki/ASCII#Control_characters
		
		if (ch < ' ' || ch == 0x7f){
			return false;
		}
		return true;
	}
	
	public static boolean isAlphaNumericString(String str){
		
		//space is not valid in string
		
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
	
	public static boolean isSpecialChar(char ch){
		return isPrintableAscii(ch) && (!Character.isAlphabetic(ch) 
				&& !Character.isDigit(ch));
	}
	
	public static boolean isUsernameUnique(String uname) throws SystemException{
		long allComps[] = PortalUtil.getCompanyIds();
		uname = uname.trim();
		for (long companyId : allComps){
			try {
				UserLocalServiceUtil.getUserIdByScreenName(companyId, uname);
				return false;
			} catch (PortalException e) {
				
				// username not found in this company
				
				continue;
			}
		}
		return true;
	}
	
	public static List<Region> getCountryRegions(long countryCode)
	throws SystemException {
		return RegionServiceUtil.getRegions(countryCode);
	}
	
	public  static long getUsaCountryCode(){

		long countryId = -1;
		try {
			countryId = 
				CountryServiceUtil.fetchCountryByA3(
					_USA_A3_CODE).getCountryId();
			
		} catch (Exception e) {}
		
		if (countryId == -1){
			_log.warn(
				"Unable to retrieve personal address type "
				+ "from DB, will use 11002");
			countryId = 19L;
		}
		return 19L;
	}
	
	public static int getPersonalAdressTypeId() {

		int addressPersonalTypeId = -1;
		
		try {
			List<ListType> addrListType = 
				ListTypeServiceUtil.getListTypes(
					ListTypeConstants.CONTACT_ADDRESS);
			for (ListType lt : addrListType){
				if (lt.getName().equals("personal")){
					addressPersonalTypeId = lt.getListTypeId();
					break;
				}
			}
		} catch (Exception e) {}
		
		if (addressPersonalTypeId == -1){
			_log.warn(
				"Unable to retrieve personal address type "
				+ "from DB, will use 11002");
			addressPersonalTypeId = 11002;
		}
		
		return addressPersonalTypeId;
	}
	
	public static int getHomePhoneTypeId() {

		int phoneHomeTypeId = -1;
		
		try {
			List<ListType> addrListType = 
				ListTypeServiceUtil.getListTypes(
					ListTypeConstants.CONTACT_PHONE);
			for (ListType lt : addrListType){
				if (lt.getName().equals("personal")){
					phoneHomeTypeId = lt.getListTypeId();
					break;
				}
			}
		} catch (Exception e) {}
		
		if (phoneHomeTypeId == -1){
			_log.warn(
				"Unable to retrieve personal phone type "
				+ "from DB, will use 11011.");
			phoneHomeTypeId = 11011;
		}
		
		return phoneHomeTypeId;
	}
	
	public static int getMobilePhoneTypeId() {

		int phoneHomeTypeId = -1;
		
		try {
			List<ListType> addrListType = 
				ListTypeServiceUtil.getListTypes(
					ListTypeConstants.CONTACT_PHONE);
			for (ListType lt : addrListType){
				if (lt.getName().equals("mobile-phone")){
					phoneHomeTypeId = lt.getListTypeId();
					break;
				}
			}
		} catch (Exception e) {}
		
		if (phoneHomeTypeId == -1){
			_log.warn(
				"Unable to retrieve mobile phone type "
				+ "from DB, will use 11008");
			phoneHomeTypeId = 11008;
		}
		
		return phoneHomeTypeId;
	}
	
	//prevent instantiation of utility class

	private MyAmfUtil(){}
	
	private static final Log _log = LogFactoryUtil.getLog(MyAmfUtil.class);
	private static final String _USA_A3_CODE = "USA";
	
}
