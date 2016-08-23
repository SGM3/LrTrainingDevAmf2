package com.liferay.training.results;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.Event;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.ProcessEvent;

import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactory;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactory;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.model.Address;
import com.liferay.portal.model.User;
import com.liferay.portal.service.AddressLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
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

	@SuppressWarnings("unchecked")
	private List<User> _getUsersForZip(
			EventRequest eventRequest, Integer zipCode) {
		List<User> listOfUsers = new ArrayList<User>();
		User newUser;
		
//		DynamicQueryFactory dqf = 
//			.getDynamicQueryFactory();
		String zeroPaddedZip = String.format("%05d", zipCode);
		
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil
			.forClass(Address.class)
			.add(PropertyFactoryUtil.forName("zip").eq(zeroPaddedZip))
			.addOrder(OrderFactoryUtil.desc("userId"));
		
		try {
			List<Address> addrs = 
				AddressLocalServiceUtil.dynamicQuery(dynamicQuery);
			for (Address addr: addrs){
				User curUser = UserLocalServiceUtil.getUser(addr.getUserId());
				listOfUsers.add(curUser);
			}
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		newUser = com.liferay.portal.service.UserLocalServiceUtil.createUser(0);
		newUser.setUserId(12L);
		newUser.setFirstName("James");
		newUser.setMiddleName("Henry");
		newUser.setLastName("Thompson");
		newUser.setEmailAddress("faker0001@that.com");
		newUser.setScreenName("jthomp0001");
		newUser.setUserId(12L);
		listOfUsers.add(newUser);
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
