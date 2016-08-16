<%@include file="/html/init.jsp" %>


       	<liferay-ui:search-container delta="${pageDelta}" emptyResultsMessage="no-login-entries-available" total="${loginEntryCount}"  curParam="lcur" >
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