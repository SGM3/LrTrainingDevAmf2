<%@include file="/html/init.jsp" %>

<!-- TODO remove -->
<%@page import="java.util.Date"%>
<%@ page import="java.util.ArrayList" %>

<%@page import="com.liferay.training.service.builder.model.TrackerEntry"%>
<%@page import="com.liferay.training.service.builder.model.impl.TrackerEntryImpl"%>

<%
String tabs1 = ParamUtil.getString(request, "curTab", "All");
Integer curPage = ParamUtil.getInteger(request, "cur", 1);
Integer rCurPage = ParamUtil.getInteger(request, "rcur", 1);
Integer lCurPage = ParamUtil.getInteger(request, "lcur", 1);
javax.portlet.PortletURL tabs1URL = renderResponse.createRenderURL();
tabs1URL.setParameter("cur", curPage.toString());
tabs1URL.setParameter("rcur", rCurPage.toString());
tabs1URL.setParameter("lcur", lCurPage.toString());
tabs1URL.setParameter("curTab", tabs1);
%>

<liferay-ui:tabs names="All,Registration,Login" refresh="true" param="curTab" tabsValues="All,Registration,Login"  value="<%=tabs1 %>" url="<%= tabs1URL.toString() %>"  >
    <liferay-ui:section>
    	<liferay-ui:search-container delta="${pageDelta}" emptyResultsMessage="no-entries-available" iteratorURL="<%= tabs1URL %>" total="${entryCount}"  curParam="cur" >
	    	<input type="hidden" name='<portlet:namespace/>curTab' value="All">
    		<liferay-ui:search-container-results results="${trackerEntries}" >
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

<%-- 	    <liferay-util:include page="/html/myloginregistrationtracker/logintab.jsp"> --%>
<%-- 	    	<liferay-util:param name="treeId" value="layoutsTree" /> --%>
<%-- 	    </liferay-util:include> --%>
<%-- 			<jsp:include page="/html/myloginregistrationtracker/logintab.jsp"> --%>
<%-- 				<jsp:param name="<portlet:namespace/>lcur" value="2"></jsp:param> --%>
<%-- 			</jsp:include> --%>
    </liferay-ui:section>
    <liferay-ui:section>
       	<liferay-ui:search-container delta="${pageDelta}" emptyResultsMessage="no-registration-entries-available" total="${regisEntryCount}" curParam="rcur"  >
	    	<input type="hidden" name="<portlet:namespace/>curTab" value="Registration">
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
       	<liferay-ui:search-container delta="${pageDelta}" emptyResultsMessage="no-login-entries-available" total="${loginEntryCount}"  curParam="lcur" >
	    	<input type="hidden" name="<portlet:namespace/>curTab" value="Login">
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