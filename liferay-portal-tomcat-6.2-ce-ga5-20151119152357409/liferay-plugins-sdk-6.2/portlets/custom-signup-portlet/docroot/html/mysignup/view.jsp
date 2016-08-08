<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@include file="/html/init.jsp"%>

<% 
// JournalArticle article = JournalArticleLocalServiceUtil.getArticle(scopeGroupId, "TERMS-OF-USE");
// long articleResourcePrimKey = article.getResourcePrimKey();
%>


<portlet:defineObjects />

<form id="myForm" action="<portlet:actionURL />" method="post" name="<portlet:namespace/>_form">
	<h3>Basic Info</h3>
	<div>
	    <div class="control-group">
	        <label class="control-label" for="first_name">First Name:</label>
	        <div class="controls">
	            <input name="<portlet:namespace />first_name" id="first_name" type="text">
	        </div>
	    </div>
	    <div class="control-group">
	        <label class="control-label" for="last_name">Last Name:</label>
	        <div class="controls">
	            <input name="<portlet:namespace />last_name" id="first_name" type="text">
	        </div>
	    </div>
	    <div class="control-group">
	        <label class="control-label" for="email_address">Email Address:</label>
	        <div class="controls">
	            <input name="<portlet:namespace />email_address" id="email_address" type="text">
	        </div>
	    </div>
	    <div class="control-group">
	        <label class="control-label" for="username">Username:</label>
	        <div class="controls">
	            <input name="<portlet:namespace />username" id="username" type="text">
	        </div>
	    </div>
	    <div class="control-group">
	        <label class="control-label" for="gender">Gender:</label>
	        <div class="controls">
		        <input type="radio" name="<portlet:namespace />gender" value="male" checked> Male<br>
	 			<input type="radio" name="<portlet:namespace />gender" value="female"> Female<br>
	        </div>
	    </div>
	    <div class="control-group">
	        <label class="control-label" for="birthday">Birthday:</label>
	        <div class="controls" id="birthday">
<%-- 				<liferay-ui:input-date/> --%>

		        <label class="control-label" for="b_month">Month:</label>
		        <input name="<portlet:namespace />b_month" id="b_month" type="text">
		        <label class="control-label" for="b_day">Day:</label>
		        <input name="<portlet:namespace />b_day" id="b_day" type="text">
		        <label class="control-label" for="b_year">Year:</label>
		        <input name="<portlet:namespace />b_year" id="b_year" type="text">
	        </div>
	    </div>
	    <div class="control-group">
	        <label class="control-label" for="password1">Password:</label>
	        <div class="controls">
	            <input name="<portlet:namespace />age" id="age" type="password">
	        </div>
	    </div>
	    <div class="control-group">
	        <label class="control-label" for="password2">Confirm Password:</label>
	        <div class="controls">
	            <input name="<portlet:namespace />age" id="age" type="password">
	        </div>
	    </div>
    </div>
	<h3>Phone</h3>
	<div>
	    <div class="control-group">
	        <label class="control-label" for="home_phone">Home Phone:</label>
	        <div class="controls">
	            <input name="<portlet:namespace />home_phone" id="home_phone" type="text">
	        </div>
	    </div>
	    
	    <div class="control-group">
	        <label class="control-label" for="mobile_phone">Mobile Phone:</label>
	        <div class="controls">
	            <input name="<portlet:namespace />mobile_phone" id="mobile_phone" type="text">
	        </div>
	    </div>
	</div>
	<h3>Billing Address (US-Only)</h3>
	<div>
	    <div class="control-group">
	        <label class="control-label" for="address">Address 1</label>
	        <div class="controls">
	            <input name="<portlet:namespace />address" id="address" type="text">
	        </div>
	        <label class="control-label" for="address2">Address 2</label>
	        <div class="controls">
	            <input name="<portlet:namespace />address2" id="address2" type="text">
	        </div>
	        <label class="control-label" for="city">City</label>
	        <div class="controls">
	            <input name="<portlet:namespace />city" id="city" type="text">
	        </div>
	        <label class="control-label" for="state">State</label>
	        <div class="controls">
	            <input name="<portlet:namespace />state" id="state" type="text">
	        </div>
	        <label class="control-label" for="zip">Zip</label>
	        <div class="controls">
	            <input name="<portlet:namespace />zip" id="zip" type="text">
	        </div>
	    </div>
	</div>
    <h3>Misc.</h3>
    <div>
    	<div class="control-group">
	        <label class="control-label" for="security_question">Choose security question:</label>
	    	<select name="<portlet:namespace />security_question" id="security_question">
			  <option value="mom">What is your mother's maiden name?</option>
			  <option value="car">What is the make of your first car?</option>
			  <option value="mascot">What is your high school mascot?</option>
			  <option value="actor">Who is your favorite actor?</option>
			</select>
    	</div>
	    <div class="control-group">
	        <label class="control-label" for="security_answer">Answer:</label>
	        <div class="controls">
	            <input name="<portlet:namespace />security_answer" id="security_answer" type="text">
	        </div>
	    </div>
    </div>
    
    <h3>Terms of Use</h3>
    <div>
    	<div class="control-group">
    		<input class="display:inline-block" type="checkbox" name="<portlet:namespace />accepted_tou" >
    		<span> I have read, understand, and agree with the Terms of Use governing my access to and use of the Acme Movie Fanatics web site.</span>
    	</div>
    </div>
    <input type="submit" value="Create">
<%-- 	<liferay-ui:journal-article articleResourcePrimKey="<%= articleResourcePrimKey %>" /> --%>
<%--    <liferay-ui:journal-article articleId="TERMS-OF-USE" groupId="<%= groupId %>" /> --%>
    
</form>