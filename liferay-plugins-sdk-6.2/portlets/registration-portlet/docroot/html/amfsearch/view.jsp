<%@include file="/html/init.jsp"%>

<portlet:defineObjects />

<portlet:actionURL name="triggerZipSearch" var="triggerZipSearchUrl" />

<liferay-ui:error key="invalid-zip" message="invalid-zip" />

<aui:form action="${triggerZipSearchUrl}" method="post">
	<aui:fieldset label="enter-us-zip-prompt">
		<aui:input type="text" name="zip-code"/>
	</aui:fieldset>
	<aui:button name="submit-button" type="submit" />
</aui:form>
	
