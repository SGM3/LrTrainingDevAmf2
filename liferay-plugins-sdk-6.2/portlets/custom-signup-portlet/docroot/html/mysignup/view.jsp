<%@include file="/html/init.jsp"%>

<portlet:defineObjects />

<portlet:actionURL name="processAction" var="processActionURL" />

<%-- 
This way the 'usRegCodes' String constant is local only to the JSP
--%>

<c:set var="isSignedIn" value="<%= themeDisplay.isSignedIn() %>" />
<c:set var="usRegCodes" value="<%= renderRequest.getAttribute(MySignupConstants.US_REG_CODES_ATTR) %>"/>
<c:set var="maleValue" value="<%= MySignupConstants.MALE_STRING_VALUE %>"/>
<c:set var="femaleValue" value="<%= MySignupConstants.FEMALE_STRING_VALUE %>"/>

<%-- build the error string --%>

<c:if test="${not isSignedIn}" >
	<c:if test="${not empty bInfoErrorList}" >
		<%-- to avoid leading line break --%>
		<c:set var="builtStr" value="${bInfoErrorList[0]}" />
		<c:forEach items="${bInfoErrorList}" begin="1" var="curStr">
			<c:set var="builtStr" value="${builtStr}<br>${curStr}" />
		</c:forEach>
	</c:if>
</c:if>

<c:if test="${isSignedIn}">
 <span>You are already signed in as: <%= themeDisplay.getUser().getScreenName() %></span>
</c:if>

<c:if test="${not isSignedIn}">

	<c:if test="${not empty bInfoErrorList}">
		<%-- TODO Localize the built string? --%>
		<liferay-ui:error key="basic_error" message="${builtStr}"/>
	</c:if>
	
	<liferay-ui:success key="user-and-entity-transaction-success" message="user-and-entity-transaction-success"/>
	
	<aui:form action="<%= processActionURL%>" method="post">
	 <aui:fieldset label="New User">
	   <aui:layout>
	      <aui:column>
	      	 <h3>Basic Info</h3>
	         <aui:input type="text" name="<%=MySignupConstants.FIRSTN_PARAM%>" label="first-name-label" inlineLabel="true" />
	         <aui:input type="text" name="<%=MySignupConstants.LASTN_PARAM%>" label="last-name-label" inlineLabel="true"/>
	         <aui:input type="text" name="<%=MySignupConstants.EMAIL_PARAM%>" label="email-label" inlineLabel="true"/>
	         <aui:input type="text" name="<%=MySignupConstants.UNAME_PARAM%>" label="uname-label" inlineLabel="true"/>
	         <aui:field-wrapper name="<%=MySignupConstants.GENDER_PARAM%>" label="gender-label">
	            <aui:input type="radio" name="<%=MySignupConstants.GENDER_PARAM%>" label="gender-male-label" inlineLabel="true" value="${maleValue}" checked="true"/>
	            <aui:input type="radio" name="<%=MySignupConstants.GENDER_PARAM%>" label="gender-female-label" inlineLabel="true" value="${femaleValue}" />
	         </aui:field-wrapper>
	         <aui:input type="text" name="<%=MySignupConstants.BDAY_PARAM%>" label="birth-day-of-month-label" inlineLabel="true"/>
	         <aui:input type="text" name="<%=MySignupConstants.BMONTH_PARAM%>" label="birth-month-label" inlineLabel="true"/>
	         <aui:input type="text" name="<%=MySignupConstants.BYEAR_PARAM%>" label="birth-year-label" inlineLabel="true"/>
	         <aui:input type="password" name="<%=MySignupConstants.UPASS1_PARAM%>" label="password-label" inlineLabel="true"/>
	         <aui:input type="password" name="<%=MySignupConstants.UPASS2_PARAM%>" label="password-confirm-label" inlineLabel="true"/>
	      </aui:column>
	      
	      <aui:column>
	      	 <h3>Contact Info</h3>
	         <aui:input type="text" name="<%=MySignupConstants.HOMEPHONE_PARAM%>" label="home-phone-label" inlineLabel="true"/>
	         <aui:input type="text" name="<%=MySignupConstants.MOBILEPHONE_PARAM%>" label="mobile-phone-label" inlineLabel="true"/>
	         <aui:input type="text" name="<%=MySignupConstants.ADDR1_PARAM%>" label="address-line-one-label" inlineLabel="true"/>
	         <aui:input type="text" name="<%=MySignupConstants.ADDR2_PARAM%>" label="address-line-two-label" inlineLabel="true"/>
	         <aui:input type="text" name="<%=MySignupConstants.CITY_PARAM%>" label="city-label" inlineLabel="true"/>
	         <aui:select name="<%=MySignupConstants.STATE_PARAM%>" label="region-label" >
					<c:forEach items="${ usRegCodes }"  var="reg">
						<aui:option selected="false" value="${reg.regionId}">${reg.regionCode}</aui:option>
					</c:forEach>
	    	 </aui:select>
	         <aui:input type="text" name="<%=MySignupConstants.ZIP_PARAM%>" label="zip-label" inlineLabel="true"/>
	      </aui:column>
	      
	      <aui:column>        
	      	 <h3>Security</h3>
			 <%-- TODO maybe localize available questions --%>
	         <aui:select label="security-question-label" name="<%=MySignupConstants.SECQ_PARAM%>">
	            <aui:option selected="true" value="mom" label="security-question-maiden-name"/>
	            <aui:option selected="false" value="make" label="security-question-first-car-make"/>
	            <aui:option selected="false" value="mascot" label="security-question-hs-mascot"/>
	            <aui:option selected="false" value="actor" label="security-question-favorite-actor"/>
	    	 </aui:select>
	         <aui:input type="text" name="<%=MySignupConstants.SECA_PARAM%>" label="security-answer-label" inlineLabel="true"/>
	         <aui:input type="checkbox" name="<%=MySignupConstants.ATOU_PARAM%>" label="atou-terms"/>
	      </aui:column>
	      <aui:button-row>
	         <aui:button type="submit" value="create-button-text"/>
	         <aui:button type="button" value="cancel-button-text" last="true"/>
	      </aui:button-row>
	   </aui:layout>
	 </aui:fieldset>
	</aui:form>
</c:if>