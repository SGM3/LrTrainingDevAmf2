<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>

<%@include file="/html/init.jsp"%>

<portlet:defineObjects />

<portlet:actionURL name="processAction" var="processActionURL" />

<!-- TODO get the bInfoErrorList from renderRequest, not session -->
<%
	boolean isSignedIn = themeDisplay.isSignedIn();
StringBuilder builtStr = null;
Object[] bInfoErrorList = null;
if (!isSignedIn){
	builtStr = new StringBuilder();
	bInfoErrorList = (Object[])portletSession.getAttribute("bInfoErrorList"); 
	if (bInfoErrorList != null){
		for (Object str: bInfoErrorList){
	builtStr.append(str + "<br>");
		}
	}
}

%>
<c:if test="<%= isSignedIn %>">
 <p>
 
 <c:forEach items="${ us_reg_codes }"  var="reg">
   Item ${reg.regionId} is for ${reg.regionCode}<p>
</c:forEach>
<%--  <%= testString.toString() %> --%>
 </p>
 <span>You are already signed in as: <%= themeDisplay.getUser().getScreenName() %></span>
</c:if>

<c:if test="<%= !isSignedIn %>">

	<c:if test="<%= Validator.isNotNull(bInfoErrorList) %>">
		<liferay-ui:error key="basic_error" message="<%= builtStr.toString() %>"/>
	</c:if>
	
	<liferay-ui:success key="add_user_success" message="New User created sucessfully"/>
	
	<aui:form action="<%= processActionURL%>" method="post">
	 <aui:fieldset label="New User">
<%--  	  <liferay-ui:success key="request_processed" message="The request was completed successfully."/> --%>
<%--  	  <liferay-ui:error key="request_errored" message="reerrored"></liferay-ui:error> --%>
	   <aui:layout>
	      <aui:column>
	      	 <h3>Basic Info</h3>
	         <aui:input type="text" name="<%=MySignupConstants.FIRSTN_PARAM%>" label="First Name:" inlineLabel="true">
<%--    	      <aui:validator name=""></aui:validator> --%>
	         </aui:input>
	         <aui:input type="text" name="<%=MySignupConstants.LASTN_PARAM%>" label="Last Name:" inlineLabel="true"/>
	         <aui:input type="text" name="<%=MySignupConstants.EMAIL_PARAM%>" label="Email Address:" inlineLabel="true"/>
	         <aui:input type="text" name="<%=MySignupConstants.UNAME_PARAM%>" label="Usernname:" inlineLabel="true"/>
	         <aui:field-wrapper name="<%=MySignupConstants.GENDER_PARAM%>" label="Gender:">
	            <aui:input type="radio" name="<%=MySignupConstants.GENDER_PARAM%>" label="Male" inlineLabel="true" value="male" checked="true"/>
	            <aui:input type="radio" name="<%=MySignupConstants.GENDER_PARAM%>" label="Female" inlineLabel="true" value="female" />
	         </aui:field-wrapper>
	         <aui:input type="text" name="<%=MySignupConstants.BDAY_PARAM%>" label="Birth day:" inlineLabel="true"/>
	         <aui:input type="text" name="<%=MySignupConstants.BMONTH_PARAM%>" label="Birth Month:" inlineLabel="true"/>
	         <aui:input type="text" name="<%=MySignupConstants.BYEAR_PARAM%>" label="Birth Year:" inlineLabel="true"/>
	         <aui:input type="password" name="<%=MySignupConstants.UPASS1_PARAM%>" label="Password:" inlineLabel="true"/>
	         <aui:input type="password" name="<%=MySignupConstants.UPASS2_PARAM%>" label="Confirm Password:" inlineLabel="true"/>
	      </aui:column>
	      
	      <aui:column>
	      	 <h3>Contact Info</h3>
	         <aui:input type="text" name="<%=MySignupConstants.HOMEPHONE_PARAM%>" label="Home Phone:" inlineLabel="true"/>
	         <aui:input type="text" name="<%=MySignupConstants.MOBILEPHONE_PARAM%>" label="Mobile Phone:" inlineLabel="true"/>
	         <aui:input type="text" name="<%=MySignupConstants.ADDR1_PARAM%>" label="Address:" inlineLabel="true"/>
	         <aui:input type="text" name="<%=MySignupConstants.ADDR2_PARAM%>" label="Address 2:" inlineLabel="true"/>
	         <aui:input type="text" name="<%=MySignupConstants.CITY_PARAM%>" label="City:" inlineLabel="true"/>
	         <aui:select label="State:" name="<%=MySignupConstants.STATE_PARAM%>">
					<c:forEach items="${ us_reg_codes }"  var="reg">
						<aui:option selected="false" value="${reg.regionId}">${reg.regionCode}</aui:option>
					</c:forEach>
	    	 </aui:select>
<%-- 	         <aui:input type="text" name="<%=MySignupConstants.STATE_PARAM%>" label="State:" inlineLabel="true"/> --%>
	         <aui:input type="text" name="<%=MySignupConstants.ZIP_PARAM%>" label="Zip:" inlineLabel="true"/>
	      </aui:column>
	      
	      <aui:column>        
	      	 <h3>Security</h3>
<%--          	<aui:input type="text" name="<%=MySignupConstants.SECQ_PARAM%>" label="Security question:" inlineLabel="true"/> --%>
	         <aui:select label="Securiy Question" name="<%=MySignupConstants.SECQ_PARAM%>">
	            <aui:option selected="true" value="mom">What is your mother's maiden name?</aui:option>
	            <aui:option selected="false" value="make">What is the make of your first car?</aui:option>
	            <aui:option selected="false" value="mascot">What is your high school mascot?</aui:option>
	            <aui:option selected="false" value="actor">Who is your favorite actor?</aui:option> 
	    	 </aui:select>
	         <aui:input type="text" name="<%=MySignupConstants.SECA_PARAM%>" label="Answer:" inlineLabel="true"/>
	         <aui:input type="checkbox" name="<%=MySignupConstants.ATOU_PARAM%>" label="I have read, understand, and agree with the Terms of Use governing<br> my access to and use of the Acme Movie Fanatics web site."/>
	      </aui:column>
	      <aui:button-row>
	         <aui:button type="submit" value="Create"/>
	         <aui:button type="button" value="Cancel" last="true"/>
	      </aui:button-row>
	   </aui:layout>
	 </aui:fieldset>
	</aui:form>
</c:if>