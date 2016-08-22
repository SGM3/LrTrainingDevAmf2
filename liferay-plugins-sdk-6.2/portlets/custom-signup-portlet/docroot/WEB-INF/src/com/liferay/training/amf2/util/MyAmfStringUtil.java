package com.liferay.training.amf2.util;

import java.util.List;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Region;
import com.liferay.portal.service.RegionServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;

public final class MyAmfStringUtil {
	
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
	
	public static boolean isUsernameUnique(
			ThemeDisplay themeDisplay, String uname) throws SystemException{
		long companyId = themeDisplay.getCompanyId();
		uname = uname.trim();
		try {
			UserLocalServiceUtil.getUserIdByScreenName(companyId, uname);
		} catch (PortalException e) {
			return true;
		}
		return false;
	}
	
	public static List<Region> getCountryRegions(long countryCode)
	throws SystemException {
		return RegionServiceUtil.getRegions(countryCode);
	}
	
	//prevent instantiation of utility class

	private MyAmfStringUtil(){}
}
