package com.liferay.training.search;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.xml.namespace.QName;

import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class AmfSearch
 */
public class AmfSearch extends MVCPortlet {
 
	public void triggerZipSearch(
		ActionRequest actionRequest, ActionResponse actionResponse){
		String zipParam = ParamUtil.get(actionRequest, "zip-code", "");
		if (zipParam.length() == 5 && Validator.isNumber(zipParam)) {
			Integer zipValue = Integer.parseInt(zipParam);
			actionResponse.setEvent(_SEARCH_PROCCESS_Q_NAME, zipValue);
		} else {
			SessionErrors.add(actionRequest, "invalid-zip");
		}
	}

	private static final QName _SEARCH_PROCCESS_Q_NAME =
			new QName("http://liferay.com/search", "ipc.search.zip");
}
