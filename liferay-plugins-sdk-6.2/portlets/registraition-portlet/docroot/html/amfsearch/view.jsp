<%@include file="/html/init.jsp"%>

<portlet:defineObjects />

<portlet:actionURL name="processAction" var="processActionURL" />

<aui:form action="<%= processActionURL%>" method="post">
	<aui:fieldset label="Enter US Zip">
		<aui:input type="text" name="zip-code" inlineLabel="true"/>
	</aui:fieldset>
</aui:form>
	
