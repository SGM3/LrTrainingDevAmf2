package com.liferay.training.amf2;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;

import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.util.bridges.mvc.MVCPortlet;

import static com.liferay.training.amf2.constants.MySignupConstants.*;

/**
 * Portlet implementation class MySignupPortlet
 */
public class MySignupPortlet extends MVCPortlet {
	
	@Override
	public void processAction(ActionRequest request, ActionResponse response)
			throws IOException, PortletException {
			SessionMessages.add(request, "request_processed", "this-is-my-custom-success-message");
			SessionErrors.add(request,"","Blah");

			PortletPreferences prefs = request.getPreferences();
			int t = 0x0;
			
			String fname = ParamUtil.getString(request, FIRSTN_PARAM);
			Validator.isName(fname);
			String lname = ParamUtil.getString(request, LASTN_PARAM);
			String emailAddr = ParamUtil.getString(request, EMAIL_PARAM);
			String username = ParamUtil.getString(request, UNAME_PARAM);
			String gender = ParamUtil.getString(request, GENDER_PARAM);
			String bmonth = ParamUtil.getString(request, BMONTH_PARAM);
			String bday = ParamUtil.getString(request, BDAY_PARAM);
			String byear = ParamUtil.getString(request, BYEAR_PARAM);
			String upass1 = ParamUtil.getString(request, UPASS1_PARAM);
			String upass2 = ParamUtil.getString(request, UPASS2_PARAM);

			String homeNum = ParamUtil.getString(request, HOMEPHONE_PARAM);
			String phoneNum = ParamUtil.getString(request, MOBILEPHONE_PARAM);

			String addr1 = ParamUtil.getString(request, ADDR1_PARAM);
			String addr2 = ParamUtil.getString(request, ADDR2_PARAM);
			String city = ParamUtil.getString(request, CITY_PARAM);
			String state = ParamUtil.getString(request, STATE_PARAM);
			String zipCode = ParamUtil.getString(request, ZIP_PARAM);

			String secQuestion = ParamUtil.getString(request, SECQ_PARAM);
			String secAnswer = ParamUtil.getString(request, SECA_PARAM);
			String acceptedTou = ParamUtil.getString(request, ATOU_PARAM);
			
			System.out.println("fname: " + fname);
			System.out.println("lname: " + lname);
			System.out.println("emailAddr: " + emailAddr);
			System.out.println("username: " + username);
			System.out.println("gender: " + gender);
			System.out.println("bmonth: " + bmonth);
			System.out.println("bday: " + bday);
			System.out.println("byear: " + byear);
			System.out.println("upass1: " + upass1);
			System.out.println("upass2: " + upass2);
			System.out.println("homeNum: " + homeNum);
			System.out.println("phoneNum: " + phoneNum);
			System.out.println("addr1: " + addr1);
			System.out.println("addr2: " + addr2);
			System.out.println("city: " + city);
			System.out.println("state: " + state);
			System.out.println("zipCode: " + zipCode);
			System.out.println("secQuestion: " + secQuestion);
			System.out.println("secAnswer: " + secAnswer);
			System.out.println("acceptedTou: " + acceptedTou);


			
			response.setPortletMode(PortletMode.VIEW);
	}
	
	
}
