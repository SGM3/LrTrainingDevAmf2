package com.liferay.training.amf2.util;

import java.util.List;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Region;
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
	
	//prevent instantiation of utility class

	private MyAmfUtil(){}
}
