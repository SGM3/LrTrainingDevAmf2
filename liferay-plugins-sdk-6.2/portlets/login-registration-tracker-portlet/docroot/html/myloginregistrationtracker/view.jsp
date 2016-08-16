<%@include file="/html/init.jsp" %>

<!-- TODO remove -->
<%@page import="java.util.Date"%>
<%@ page import="java.util.ArrayList" %>

<%@page import="com.liferay.training.service.builder.model.TrackerEntry"%>

<liferay-ui:tabs names="${tabsCsl}" refresh="true" param="curTab" tabsValues="${tabsCsl}"  value="${curTabValue}" url="${tabsUrl}"  >
    <liferay-ui:section>
    	<%--  delta="${pageDeltaAll}" --%>
    	<liferay-ui:search-container deltaParam="delta" delta="${pageDeltaAll}" emptyResultsMessage="no-entries-available" iteratorURL="${iterUrlObj}" total="${entryCount}"  curParam="cur" >
	    	<liferay-ui:search-container-results results="${trackerEntries}" >
    		</liferay-ui:search-container-results>
			<liferay-ui:search-container-row className="com.liferay.training.service.builder.model.TrackerEntry" keyProperty="trackerEntryId" modelVar="entry" >
				<liferay-ui:search-container-column-text name="Date" value="<%= \"TO_FIX_\" + entry.getEventDate().toString() %>" />
   				<liferay-ui:search-container-column-text name="Username" value="<%= entry.getUserName() %>" />
   				<liferay-ui:search-container-column-text name="User ID" value="<%= \"\" + entry.getUserId() %>" />
   				<liferay-ui:search-container-column-text name="IP Address" value="<%= entry.getIpAddress() %>" />
   				<liferay-ui:search-container-column-text name="Event Type" value="<%= entry.getEventType() %>" />
   			</liferay-ui:search-container-row>
   			<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />
    	</liferay-ui:search-container>
    </liferay-ui:section>
    <liferay-ui:section>
    	<%--  delta="${pageDeltaRegis}" --%>
       	<liferay-ui:search-container deltaParam="deltar" delta="${pageDeltaRegis}" emptyResultsMessage="no-registration-entries-available" iteratorURL="${iterUrlObj}" total="${regisEntryCount}" curParam="rcur"  >
	    	<liferay-ui:search-container-results results="${regisTrackerEntries}" >
    		</liferay-ui:search-container-results>
			<liferay-ui:search-container-row className="com.liferay.training.service.builder.model.TrackerEntry" keyProperty="trackerEntryId" modelVar="entry" >
				<liferay-ui:search-container-column-text name="Date" value="<%= \"TO_FIX_\" + entry.getEventDate().toString() %>" />
   				<liferay-ui:search-container-column-text name="Username" value="<%= entry.getUserName() %>" />
   				<liferay-ui:search-container-column-text name="User ID" value="<%= \"\" + entry.getUserId() %>" />
   				<liferay-ui:search-container-column-text name="IP Address" value="<%= entry.getIpAddress() %>" />
   				<liferay-ui:search-container-column-text name="Event Type" value="<%= entry.getEventType() %>" />
   			</liferay-ui:search-container-row>
   			<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />
    	</liferay-ui:search-container>
    </liferay-ui:section>
    <liferay-ui:section>
    	<%--   delta="${pageDeltaLogin}" --%>
       	<liferay-ui:search-container deltaParam="deltal" delta="${pageDeltaLogin}" emptyResultsMessage="no-login-entries-available" iteratorURL="${iterUrlObj}" total="${loginEntryCount}"  curParam="lcur" >
	    	<liferay-ui:search-container-results results="${loginTrackerEntries}" >
    		</liferay-ui:search-container-results>
			<liferay-ui:search-container-row className="com.liferay.training.service.builder.model.TrackerEntry" keyProperty="trackerEntryId" modelVar="entry" >
				<liferay-ui:search-container-column-text name="Date" value="<%= \"TO_FIX_\" + entry.getEventDate().toString() %>" />
   				<liferay-ui:search-container-column-text name="Username" value="<%= entry.getUserName() %>" />
   				<liferay-ui:search-container-column-text name="User ID" value="<%= \"\" + entry.getUserId() %>" />
   				<liferay-ui:search-container-column-text name="IP Address" value="<%= entry.getIpAddress() %>" />
   				<liferay-ui:search-container-column-text name="Event Type" value="<%= entry.getEventType() %>" />
   			</liferay-ui:search-container-row>
   			<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />
    	</liferay-ui:search-container>
    </liferay-ui:section>
</liferay-ui:tabs>