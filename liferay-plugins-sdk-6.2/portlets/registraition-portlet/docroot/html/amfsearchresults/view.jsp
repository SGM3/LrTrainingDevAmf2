<%@include file="/html/init.jsp" %>

<c:set var="entryCount" value="2000" />

<h2>Search Results for "${entryCount}" </h2>

<liferay-ui:search-container deltaParam="delta" delta="${pageDeltaAll}" emptyResultsMessage="no-entries-available" iteratorURL="${iterUrlObj}" total="${entryCount}" curParam="cur">
	<liferay-ui:search-container-results results="${trackerEntries}">
	</liferay-ui:search-container-results>
	<liferay-ui:search-container-row className="com.liferay.portal.model.User" keyProperty="trackerEntryId" modelVar="entry">
		<liferay-ui:search-container-column-text name="Username" value="<%=entry.getUserName()%>" />
		<liferay-ui:search-container-column-text name="User ID" value="<%=\"\" + entry.getUserId()%>" />
		<liferay-ui:search-container-column-text name="IP Address" value="<%=entry.getIpAddress()%>" />
		<liferay-ui:search-container-column-text name="Event Type" value="<%=entry.getEventType()%>" />
	</liferay-ui:search-container-row>
	<liferay-ui:search-iterator searchContainer="<%=searchContainer%>" />
</liferay-ui:search-container>
