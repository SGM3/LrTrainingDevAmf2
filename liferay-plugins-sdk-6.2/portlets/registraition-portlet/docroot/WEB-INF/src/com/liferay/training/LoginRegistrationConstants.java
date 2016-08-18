package com.liferay.training;

public interface LoginRegistrationConstants {
	
	public static final String PORTLET_PERM_KEY =
			"registraition_WAR_registraitionportlet";
	
	public static final String PAGE_DELTA_ALL_PARAM = "delta";
	public static final String PAGE_DELTA_REGIS_PARAM = "deltar";
	public static final String PAGE_DELTA_LOGIN_PARAM = "deltal";

	public static final String SEARCH_CON_ADELTA_ATTR = "pageDeltaAll";
	public static final String SEARCH_CON_RDELTA_ATTR = "pageDeltaRegis";
	public static final String SEARCH_CON_LDELTA_ATTR = "pageDeltaLogin";
	
	public static final String ALL_TRACKER_ENTRIES_ATTR = "trackerEntries";
	public static final String LOGIN_TRACKER_ENTRIES_ATTR = 
		"loginTrackerEntries";
	public static final String REGIS_TRACKER_ENTRIES_ATTR = 
		"regisTrackerEntries";
	
	public static final String SEARCH_ENTRY_ALL_COUNT_ATTR = "entryCount";
	public static final String SEARCH_ENTRY_LOGIN_COUNT_ATTR = 
		"loginEntryCount";
	public static final String SEARCH_ENTRY_REGIS_COUNT_ATTR = 
		"regisEntryCount";
	
	public static final String LOGIN_EVENT_TYPE = "Login";
	public static final String REGIS_EVENT_TYPE = "Registration";

	public static final String CURTAB_PARAM_NAME = "curTab";
	public static final String CURTAB_ATTR = "curTabValue";
	public static final String ITER_OBJ_ATTR = "iterUrlObj";
	public static final String URL_STRING_ATTR = "tabsUrl";
	
	public static final String ALL_CUR_PARAM_NAME = "cur";
	public static final String LOGIN_CUR_PARAM_NAME = "lcur";
	public static final String REGIS_CUR_PARAM_NAME = "rcur";
	

	public static final String LAN_KEY_ALL = "lrt-all-tab";
	public static final String LAN_KEY_REGIS = "lrt-regis-tab";
	public static final String LAN_KEY_LOGIN = "lrt-login-tab";
	
	public static final String TABS_CSL_ATTR = "tabsCsl";
	
	public static final String DATE_FORMAT_STRING = "YYYY-MM-DD kk:mm:ss";

	public static final int PAGE_DELTA = 20;
}
