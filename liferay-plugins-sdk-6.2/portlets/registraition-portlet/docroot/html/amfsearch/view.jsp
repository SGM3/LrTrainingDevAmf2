<%@include file="/html/init.jsp"%>

<portlet:defineObjects />

<portlet:actionURL name="triggerZipSearch" var="triggerZipSearchUrl" />

<aui:form action="${triggerZipSearchUrl}" method="post">
	<aui:fieldset label="enter-us-zip-prompt">
		<aui:input type="text" name="zip-code"/>
	</aui:fieldset>
	<aui:button name="submit-button" value="submit-button" />
</aui:form>
	
