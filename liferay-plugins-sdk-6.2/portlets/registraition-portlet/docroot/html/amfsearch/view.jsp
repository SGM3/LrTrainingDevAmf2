<%@include file="/html/init.jsp"%>

<portlet:defineObjects />

<portlet:actionURL name="triggerZipSearch" var="triggerZipSearchUrl" />

<aui:form action="${triggerZipSearchUrl}" method="post">
	<aui:fieldset label="enter-us-zip-prompt">
		<aui:input type="text" name="zip-code" inlineLabel="true"/>
	</aui:fieldset>
</aui:form>
	
