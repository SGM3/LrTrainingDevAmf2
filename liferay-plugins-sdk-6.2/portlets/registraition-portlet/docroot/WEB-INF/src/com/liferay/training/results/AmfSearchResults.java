package com.liferay.training.results;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.Event;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.ProcessEvent;

import com.liferay.portal.model.User;
import com.liferay.training.service.builder.service.persistence.SearchResultUserFinderImpl;
import com.liferay.training.service.builder.service.persistence.SearchResultUserFinderUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class AmfSearchResults
 */
public class AmfSearchResults extends MVCPortlet {
 
	@ProcessEvent(qname="{http://liferay.com/search}ipc.search.zip")
	public void setSearchCriteria(
		EventRequest eventRequest, EventResponse eventResponse) {
		Event criteriaEvent = eventRequest.getEvent();
		Integer zipCode = (Integer) criteriaEvent.getValue();
		List<User> listOfUsers = _getUsersForZip(eventRequest, zipCode);

		eventRequest.setAttribute("trackerEntries", listOfUsers);
		eventRequest.setAttribute("entryCount", listOfUsers.size());
		
		eventResponse.setRenderParameter("zipcodeparam", zipCode.toString());
	}

	private List<User> _getUsersForZip(
			EventRequest eventRequest, Integer zipCode) {
		List<User> listOfUsers = new ArrayList<User>();
		User newUser;
		
		listOfUsers = null;//SearchResultUserFinderUtil
		SearchResultUserFinderUtil.findUserByZipCode(zipCode, 0, 5);
		

//		newUser = com.liferay.portal.service.UserLocalServiceUtil.createUser(0);
//		newUser.setUserId(12L);
//		newUser.setFirstName("James");
//		newUser.setMiddleName("Henry");
//		newUser.setLastName("Thompson");
//		newUser.setEmailAddress("faker0001@that.com");
//		newUser.setScreenName("jthomp0001");
//		newUser.setUserId(12L);
//		listOfUsers.add(newUser);
//
//		newUser = com.liferay.portal.service.UserLocalServiceUtil.createUser(0);
//		newUser.setUserId(12L);
//		newUser.setFirstName("Ron");
//		newUser.setLastName("Thompson");
//		newUser.setEmailAddress("faker0002@that.com");
//		newUser.setScreenName("rthomp0002");
//		newUser.setUserId(13L);
//		listOfUsers.add(newUser);
		
		return listOfUsers;
	}

}
