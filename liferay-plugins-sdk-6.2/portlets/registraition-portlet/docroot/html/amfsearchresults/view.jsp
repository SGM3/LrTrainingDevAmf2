<%@include file="/html/init.jsp" %>


<%
java.util.List<com.liferay.portal.model.User> tmp = new java.util.ArrayList<com.liferay.portal.model.User>();
com.liferay.portal.model.User newUser;

newUser = com.liferay.portal.service.UserLocalServiceUtil.createUser(0);
newUser.setUserId(12L);
newUser.setFirstName("James");
newUser.setMiddleName("Henry");
newUser.setLastName("Thompson");
newUser.setEmailAddress("faker0001@that.com");
newUser.setScreenName("jthomp0001");
newUser.setUserId(12L);
tmp.add(newUser);

newUser = com.liferay.portal.service.UserLocalServiceUtil.createUser(0);
newUser.setUserId(12L);
newUser.setFirstName("Ron");
newUser.setLastName("Thompson");
newUser.setEmailAddress("faker0002@that.com");
newUser.setScreenName("rthomp0002");
newUser.setUserId(13L);
tmp.add(newUser);


renderRequest.setAttribute("trackerEntries", tmp);
renderRequest.setAttribute("entryCount", tmp.size());
renderRequest.setAttribute("zipCode", 12345);
%>

<h2>Search Results for "${zipCode}" </h2>

<liferay-ui:search-container deltaParam="delta" delta="${pageDeltaAll}" emptyResultsMessage="no-results-available" iteratorURL="${iterUrlObj}" total="${entryCount}" curParam="cur">
	<liferay-ui:search-container-results results="${trackerEntries}">
	</liferay-ui:search-container-results>
	<liferay-ui:search-container-row className="com.liferay.portal.model.User" keyProperty="userId" modelVar="entry">
		<liferay-ui:search-container-column-text name="User ID" value="${entry.fullName}" />
		<liferay-ui:search-container-column-text name="User ID" value="${entry.screenName}" />
		<liferay-ui:search-container-column-text name="User ID" value="${entry.userId}" />
		<liferay-ui:search-container-column-text name="User ID" value="${entry.emailAddress}" />
	</liferay-ui:search-container-row>
	<liferay-ui:search-iterator searchContainer="${searchContainer}" />
</liferay-ui:search-container>
