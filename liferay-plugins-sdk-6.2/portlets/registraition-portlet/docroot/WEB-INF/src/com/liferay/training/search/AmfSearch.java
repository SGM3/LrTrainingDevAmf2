package com.liferay.training.search;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class AmfSearch
 */
public class AmfSearch extends MVCPortlet {
 
	public void triggerZipSearch(
		ActionRequest actionRequest, ActionResponse actionResponse){
		System.out.println("triggerAction fired");
	}

}
