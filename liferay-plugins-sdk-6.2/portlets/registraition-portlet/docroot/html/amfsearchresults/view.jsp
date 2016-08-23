<%@include file="/html/init.jsp" %>

<c:if test="${empty param[\"zipcodeparam\"]}" >
	<span>No zip code was requested yet.</span>
</c:if>
<c:if test="${not empty param[\"zipcodeparam\"]}" >
	<h2>Search Results for Zip "${param["zipcodeparam"]}" </h2>
	
	<liferay-ui:search-container deltaParam="delta" delta="${pageDeltaAll}" emptyResultsMessage="no-results-available" iteratorURL="${iterUrlObj}" total="${entryCount}" curParam="cur">
		<liferay-ui:search-container-results results="${trackerEntries}">
		</liferay-ui:search-container-results>
		<liferay-ui:search-container-row className="com.liferay.portal.model.User" keyProperty="userId" modelVar="entry">
			<liferay-ui:search-container-column-text name="User ID" value="${entry.firstName} ${fn:substring(entry.lastName, 0, 1)}." />
			<liferay-ui:search-container-column-text name="User ID" value="${entry.screenName}" />
			<liferay-ui:search-container-column-text name="User ID" value="${entry.emailAddress}" />
		</liferay-ui:search-container-row>
		<liferay-ui:search-iterator searchContainer="${searchContainer}" />
	</liferay-ui:search-container>
</c:if>
