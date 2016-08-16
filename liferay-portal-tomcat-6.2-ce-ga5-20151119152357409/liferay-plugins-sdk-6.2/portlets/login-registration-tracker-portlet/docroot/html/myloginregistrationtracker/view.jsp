<%@include file="/html/init.jsp" %>

<!-- TODO remove -->
<%@page import="java.util.Date"%>
<%@ page import="java.util.ArrayList" %>

<%@page import="com.liferay.training.service.builder.model.TrackerEntry"%>
<%@page import="com.liferay.training.service.builder.model.impl.TrackerEntryImpl"%>

<liferay-ui:tabs names="All,Registration,Login" refresh="true" param="curTab" tabsValues="All,Registration,Login"  value="${curTab}" url="${tabsUrl}"  >
    <liferay-ui:section>
    	<liferay-ui:search-container delta="${pageDelta}" emptyResultsMessage="no-entries-available" iteratorURL="${iterUrlObj}" total="${entryCount}"  curParam="cur" >
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
       	<liferay-ui:search-container delta="${pageDelta}" emptyResultsMessage="no-registration-entries-available" iteratorURL="${iterUrlObj}" total="${regisEntryCount}" curParam="rcur"  >
	    	<liferay-ui:search-container-results results="${regisTrackerEntries}" >
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
       	<liferay-ui:search-container delta="${pageDelta}" emptyResultsMessage="no-login-entries-available" iteratorURL="${iterUrlObj}" total="${loginEntryCount}"  curParam="lcur" >
	    	<liferay-ui:search-container-results results="${loginTrackerEntries}" >
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
</liferay-ui:tabs>