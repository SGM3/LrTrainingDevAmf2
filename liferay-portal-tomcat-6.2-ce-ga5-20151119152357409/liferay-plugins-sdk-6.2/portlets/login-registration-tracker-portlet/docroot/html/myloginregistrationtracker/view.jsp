<%@page import="com.liferay.portal.kernel.util.ListUtil"%>
<%@include file="/html/init.jsp" %>

<!-- TODO remove -->
<%@page import="java.util.Date"%>
<%@ page import="java.util.ArrayList" %>

<%@page import="com.liferay.training.service.builder.model.TrackerEntry"%>
<%@page import="com.liferay.training.service.builder.model.impl.TrackerEntryImpl"%>


This is the <b>My Login Registration Tracker</b> portlet in View mode.

<!-- TODO remove -->
<%
TrackerEntry[] mockEntries = new TrackerEntry[7];
for (int i = 0; i < 7; i++){
	mockEntries[i] =  new TrackerEntryImpl();
}

List<TrackerEntry> mockEntriesList = new ArrayList<TrackerEntry>();

mockEntries[0].setEventDate(new Date());
mockEntries[1].setEventDate(new Date());
mockEntries[2].setEventDate(new Date());
mockEntries[3].setEventDate(new Date());
mockEntries[4].setEventDate(new Date());
mockEntries[5].setEventDate(new Date());
mockEntries[6].setEventDate(new Date());

mockEntries[0].setEventType(LoginRegistrationConstants.LOGIN_EVENT_TYPE);
mockEntries[1].setEventType(LoginRegistrationConstants.LOGIN_EVENT_TYPE);
mockEntries[2].setEventType(LoginRegistrationConstants.LOGIN_EVENT_TYPE);
mockEntries[3].setEventType(LoginRegistrationConstants.REGIS_EVENT_TYPE);
mockEntries[4].setEventType(LoginRegistrationConstants.REGIS_EVENT_TYPE);
mockEntries[5].setEventType(LoginRegistrationConstants.REGIS_EVENT_TYPE);
mockEntries[6].setEventType(LoginRegistrationConstants.REGIS_EVENT_TYPE);

mockEntries[0].setUserName("user1");
mockEntries[1].setUserName("user2");
mockEntries[2].setUserName("user1");
mockEntries[3].setUserName("user2");
mockEntries[4].setUserName("user1");
mockEntries[5].setUserName("user2");
mockEntries[6].setUserName("user1");

mockEntries[0].setUserId(111L);
mockEntries[1].setUserId(222L);
mockEntries[2].setUserId(111L);
mockEntries[3].setUserId(222L);
mockEntries[4].setUserId(111L);
mockEntries[5].setUserId(222L);
mockEntries[6].setUserId(111L);

mockEntries[0].setIpAddress("127.1.0.1");
mockEntries[1].setIpAddress("127.2.0.2");
mockEntries[2].setIpAddress("127.1.1.1");
mockEntries[3].setIpAddress("127.2.1.2");
mockEntries[4].setIpAddress("127.1.0.1");
mockEntries[5].setIpAddress("127.2.2.2");
mockEntries[6].setIpAddress("127.1.2.1");

for (TrackerEntry t : mockEntries){
	mockEntriesList.add(t);
}

// actionRequest.setAttribute(
// 		LoginRegistrationConstants.TRACKER_ENTRIES_ATTR, mockEntriesList);
%>

<liferay-ui:tabs names="All,Registration,Login" refresh="false" tabsValues="All,Registration,Login">
    <liferay-ui:section>
    	<liferay-ui:search-container delta="4" emptyResultsMessage="This is empty" total="<%= mockEntriesList.size() %>">
    		<liferay-ui:search-container-results results="<%= ListUtil.subList(mockEntriesList, searchContainer.getStart(),searchContainer.getEnd()) %>">
    		</liferay-ui:search-container-results>
			<liferay-ui:search-container-row className="com.liferay.training.service.builder.model.TrackerEntry" keyProperty="trackerEntryId" modelVar="entry" >
				<liferay-ui:search-container-column-text name="Date" value="<%= \"TO_FIX_\" + entry.getEventDate().toString() %>" />
   				<liferay-ui:search-container-column-text name="Username" value="<%= entry.getUserName() %>" />
   				<liferay-ui:search-container-column-text name="User ID" value="<%= \"\" + entry.getUserId() %>" />
   				<liferay-ui:search-container-column-text name="IP Address" value="<%= entry.getIpAddress() %>" />
   				<liferay-ui:search-container-column-text name="Event Type" value="<%= entry.getEventType() %>" />
   			</liferay-ui:search-container-row>
   			<liferay-ui:search-iterator />
    	</liferay-ui:search-container>
    </liferay-ui:section>
    <liferay-ui:section>
        Text for Tab 2.
    </liferay-ui:section>
    <liferay-ui:section>
        Text for Tab 3.
    </liferay-ui:section>
</liferay-ui:tabs>