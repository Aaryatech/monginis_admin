package com.ats.adminpanel.controller;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.model.Info;
import com.ats.adminpanel.model.grngvn.GetGrnGvnDetails;
import com.ats.adminpanel.model.grngvn.GetGrnGvnDetailsList;
import com.ats.adminpanel.model.grngvn.GrnGvn;
import com.ats.adminpanel.model.grngvn.PostGrnGvnList;

@Controller
public class GrnGvnController {

	GetGrnGvnDetailsList getGrnGvnDetailsList;
	List<GetGrnGvnDetails> getGrnGvnDetails;

	public static String gateGrnFromDate, gateGrnToDate, accGrnFromDate, accGrnToDate;
	
	public static String gateGvnFromDate,gateGvnToDate,storeGvnFromDate,storeGvnToDate,accGvnFromDate,accGvnToDate;
	
	
	
	
	
	
	
	@RequestMapping(value = "/getDateForGvnAcc", method = RequestMethod.GET)
	public String getDateForGvnAcc(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("grngvn/accGvn");

		accGvnFromDate = request.getParameter("fromDate");
		accGvnToDate = request.getParameter("toDate");

		System.out.println("from Date using ajax  :" + accGvnFromDate);
		System.out.println("to Date using ajax  : " + accGvnToDate);

		return "";

	}
	
	@RequestMapping(value = "/getDateForGvnStore", method = RequestMethod.GET)
	public String getDateForGvnStore(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("grngvn/storeGvn");

		storeGvnFromDate = request.getParameter("fromDate");
		storeGvnToDate = request.getParameter("toDate");

		System.out.println("from Date using ajax  :" + storeGvnFromDate);
		System.out.println("to Date using ajax  : " + storeGvnToDate);

		return "";

	}

	
	
	@RequestMapping(value = "/getDateForGvnGate", method = RequestMethod.GET)
	public String getDateForGvnGate(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("grngvn/gateGvn");

		gateGvnFromDate = request.getParameter("fromDate");
		gateGvnToDate = request.getParameter("toDate");

		System.out.println("from Date using ajax  :" + gateGvnFromDate);
		System.out.println("to Date using ajax  : " + gateGvnToDate);

		return "";

	}


	@RequestMapping(value = "/getDateForGrnGate", method = RequestMethod.GET)
	public String getDateForGrnGate(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("grngvn/gateGrn");

		gateGrnFromDate = request.getParameter("fromDate");
		gateGrnToDate = request.getParameter("toDate");

		System.out.println("from Date using ajax  :" + gateGrnFromDate);
		System.out.println("to Date using ajax  : " + gateGrnToDate);

		return "";

	}

	@RequestMapping(value = "/getDateForGrnAcc", method = RequestMethod.GET)
	public String getDateForGrnAcc(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("grngvn/gateGrn");

		accGrnFromDate = request.getParameter("fromDate");
		accGrnToDate = request.getParameter("toDate");

		System.out.println("from Date using ajax  :" + accGrnFromDate);
		System.out.println("to Date using ajax  : " + accGrnToDate);

		return "";

	}

	@RequestMapping(value = "/showGateGrn", method = RequestMethod.GET)
	public ModelAndView showGateGrn(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("grngvn/gateGrn");
		try {

			Constants.mainAct = 9;
			Constants.subAct = 91;

		} catch (Exception e) {

			System.out.println("Error in Getting grn details " + e.getMessage());

			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/showGateGrnDetails", method = RequestMethod.GET)
	public ModelAndView showGateGrnDetails(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("grngvn/gateGrn");

		/*
		 * String fromDate = request.getParameter("from_date"); String toDate =
		 * request.getParameter("to_date");
		 */

		try {

			/*
			 * System.out.println("before getting from date ==" + fromDate);
			 * System.out.println("before getting to date== " + toDate);
			 */
			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			try {

				// if (fromDate == null && toDate == null) {

				/*
				 * System.out.println("from date----------- =" + fromDate);
				 * System.out.println("to date---- " + toDate); System.out.println("inside if");
				 */
				map.add("fromDate", gateGrnFromDate);
				map.add("toDate", gateGrnToDate);
				/*
				 * } else { map.add("fromDate", fromDate); map.add("toDate", toDate);
				 * System.out.println("inside else");
				 * System.out.println("from date----------- =" + fromDate);
				 * System.out.println("to date---- " + toDate);
				 * 
				 * }
				 */
			} catch (Exception e) {
				System.out.println("ex in getting dates ---line 80");
			}

			getGrnGvnDetailsList = restTemplate.postForObject(Constants.url + "getGrnDetail", map,
					GetGrnGvnDetailsList.class);

			getGrnGvnDetails = new ArrayList<GetGrnGvnDetails>();

			getGrnGvnDetails = getGrnGvnDetailsList.getGrnGvnDetails();

			model.addObject("grnList", getGrnGvnDetails);

			model.addObject("fromDate", gateGrnFromDate);
			model.addObject("toDate", gateGrnToDate);

			System.out.println("grn details " + getGrnGvnDetails.toString());

		} catch (Exception e) {

			System.out.println("Error in Getting grn details " + e.getMessage());

			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/insertGateGrnProcessAgree", method = RequestMethod.GET)
	public String insertGateGrnProcess(HttpServletRequest request, HttpServletResponse response) {

		Constants.mainAct = 9;
		Constants.subAct = 91;
		System.out.println("using a href to call insert Gate");

		ModelAndView model = new ModelAndView("grngvn/gateGrn");

		int grnId = Integer.parseInt(request.getParameter("grnId"));

		int gateApproveLogin = Integer.parseInt(request.getParameter("approveGateLogin"));

		System.out.println("gateApproveLogin =" + gateApproveLogin);

		System.out.println("grnId =" + grnId);

		try {

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			System.out.println("************* Date Time " + dateFormat.format(cal.getTime()));

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("approvedLoginGate", gateApproveLogin);

			map.add("approveimedDateTimeGate", dateFormat.format(cal.getTime()));

			map.add("approvedRemarkGate", "Def:Grn Accepted");

			map.add("grnGvnStatus", 2);

			map.add("grnGvnId", grnId);

			String info = restTemplate.postForObject(Constants.url + "updateGateGrn", map, String.class);
			System.out.println("after calling web service ");

		} catch (Exception e) {

			// System.out.println("Error in Getting grn details " + e.getMessage());

			e.printStackTrace();
		}

		return "redirect:/showGateGrnDetails";

	}

	// insert gate grn for disagree
	@RequestMapping(value = "/insertGateGrnProcessDisAgree", method = RequestMethod.GET)
	public String insertGateGrnProcessDisAgree(HttpServletRequest request, HttpServletResponse response) {

		Constants.mainAct = 9;
		Constants.subAct = 91;
		System.out.println("using Ajax to call disagree");

		ModelAndView model = new ModelAndView("grngvn/gateGrn");
		try {
			int grnId = Integer.parseInt(request.getParameter("grnId"));

			int gateApproveLogin = Integer.parseInt(request.getParameter("approveGateLogin"));

			String gateRemark = request.getParameter("gateRemark");
			System.out.println("Gate Remark  " + gateRemark);

			System.out.println("gateApproveLogin =" + gateApproveLogin);

			System.out.println("grnId =" + grnId);

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			System.out.println("************* Date Time " + dateFormat.format(cal.getTime()));

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("approvedLoginGate", gateApproveLogin);

			map.add("approveimedDateTimeGate", dateFormat.format(cal.getTime()));

			map.add("approvedRemarkGate", gateRemark);

			map.add("grnGvnStatus", 3);

			map.add("grnGvnId", grnId);

			String info = restTemplate.postForObject(Constants.url + "updateGateGrn", map, String.class);
			System.out.println("after calling web service of disagree");

		} catch (Exception e) {

			// System.out.println("Error in Getting grn details " + e.getMessage());

			e.printStackTrace();
		}

		return "redirect:/showGateGrnDetails";

	}

	//insert gate grn by check boxes
	@RequestMapping(value = "/insertGrnByCheckBoxes", method = RequestMethod.GET) // Using checkboxes to insert
	public ModelAndView getGrnId(HttpServletRequest request, HttpServletResponse response) {

		Constants.mainAct = 9;
		Constants.subAct = 91;

		ModelAndView model = new ModelAndView("grngvn/gateGrn");

		try {
			String[] grnIdList = request.getParameterValues("select_to_agree");

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			List<GrnGvn> postGrnGvnList = new ArrayList<GrnGvn>();

			PostGrnGvnList postGrnList = new PostGrnGvnList();

			getGrnGvnDetails = getGrnGvnDetailsList.getGrnGvnDetails();
			System.out.println("grn details line 191 " + getGrnGvnDetails.toString());

			
			System.out.println("before for");

			for (int i = 0; i < grnIdList.length; i++) {
				System.out.println("grn id List" + grnIdList[i]);
				System.out.println("detail list = " + getGrnGvnDetails.get(i).getGrnGvnId());
				for (int j = 0; j < getGrnGvnDetails.size(); j++) {

					if (Integer.parseInt(grnIdList[i]) == getGrnGvnDetails.get(j).getGrnGvnId()) {

						int aproveGateLogin = Integer
								.parseInt(request.getParameter("approve_gate_login" + getGrnGvnDetails.get(j).getGrnGvnId()));

						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Calendar cal = Calendar.getInstance();
						System.out.println("************* Date Time " + dateFormat.format(cal.getTime()));

						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						java.util.Date date = null;

						try {
							date = sdf.parse(getGrnGvnDetails.get(j).getGrnGvnDate());
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						java.sql.Date grnGvnDate = new Date(date.getTime());

						GrnGvn postGrnGvn = new GrnGvn();

						postGrnGvn.setGrnGvnDate(grnGvnDate);// 1
						postGrnGvn.setGrnGvnId(getGrnGvnDetails.get(j).getGrnGvnId());
						postGrnGvn.setBillNo(getGrnGvnDetails.get(j).getBillNo());// 2
						postGrnGvn.setFrId(getGrnGvnDetails.get(j).getFrId());// 3
						postGrnGvn.setItemId(getGrnGvnDetails.get(j).getItemId());// 4
						postGrnGvn.setItemRate(getGrnGvnDetails.get(j).getItemRate());// 5
						postGrnGvn.setItemMrp(getGrnGvnDetails.get(j).getItemMrp());// 6
						postGrnGvn.setGrnGvnQty(getGrnGvnDetails.get(j).getGrnGvnQty());// 7
						postGrnGvn.setGrnType(getGrnGvnDetails.get(j).getGrnType());// 9
						postGrnGvn.setIsGrn(getGrnGvnDetails.get(j).getIsGrn());// 10
						postGrnGvn.setIsGrnEdit(getGrnGvnDetails.get(j).getIsGrnEdit());// 11
						postGrnGvn.setGrnGvnEntryDateTime(getGrnGvnDetails.get(j).getGrnGvnEntryDateTime());// 12
						postGrnGvn.setFrGrnGvnRemark(getGrnGvnDetails.get(j).getFrGrnGvnRemark());// 13
						postGrnGvn.setGvnPhotoUpload1(getGrnGvnDetails.get(j).getGvnPhotoUpload1());// 14
						postGrnGvn.setGvnPhotoUpload2(getGrnGvnDetails.get(j).getGvnPhotoUpload2());// 15
						postGrnGvn.setGrnGvnStatus(2);// 16
						postGrnGvn.setApprovedLoginGate(aproveGateLogin);// 17dateFormat.format(cal.getTime()));//18
						postGrnGvn.setApprovedRemarkGate("Def: Grn Approved By Gate");// 19
						postGrnGvn.setApproveimedDateTimeGate(dateFormat.format(cal.getTime()));


						postGrnGvn.setApprovedLoginStore(getGrnGvnDetails.get(j).getApprovedLoginStore());// 20
						postGrnGvn.setApprovedDateTimeStore(getGrnGvnDetails.get(j).getApprovedDateTimeStore());// 21
						postGrnGvn.setApprovedRemarkStore(getGrnGvnDetails.get(j).getApprovedRemarkStore());// 22
						postGrnGvn.setApprovedLoginAcc(getGrnGvnDetails.get(j).getApprovedLoginAcc());// 23
						postGrnGvn.setGrnApprovedDateTimeAcc(getGrnGvnDetails.get(j).getGrnApprovedDateTimeAcc());// 24
						postGrnGvn.setApprovedRemarkAcc(getGrnGvnDetails.get(j).getApprovedRemarkAcc());// 25

						postGrnGvn.setDelStatus(0);// 26
						postGrnGvn.setGrnGvnQtyAuto(0);// 27

						postGrnGvn.setGrnGvnAmt(getGrnGvnDetails.get(j).getGrnGvnAmt());

						postGrnGvnList.add(postGrnGvn);

						System.out.println("Done it inside if ");
						System.out.println("grn ID=  " + grnIdList[i]);

					} // end of if

					else {

						System.out.println("No match found " + getGrnGvnDetails.get(j).getGrnGvnId());
					} // end of else

				} // inner for

				postGrnList.setGrnGvn(postGrnGvnList);

			} // outer for
			System.out.println("after for");

			// postGrnList.setGrnGvn(postGrnGvnList);

			System.out.println("post grn for rest----- " + postGrnList.toString());
			System.out.println("post grn for rest size " + postGrnList.getGrnGvn().size());

			Info info = restTemplate.postForObject(Constants.url + "insertGrnGvn", postGrnList, Info.class);

			// System.out.println("Error in Getting grn details " + e.getMessage());

			String fromDate = request.getParameter("from_date");
			String toDate = request.getParameter("to_date");

			System.out.println("gateGrnFromDate Date *******" + gateGrnFromDate);
			System.out.println("gateGrnToDate Date ***********" + gateGrnToDate);

			map.add("fromDate", gateGrnFromDate);
			map.add("toDate", gateGrnToDate);

			getGrnGvnDetailsList = restTemplate.postForObject(Constants.url + "getGrnDetail", map,
					GetGrnGvnDetailsList.class);

			getGrnGvnDetails = new ArrayList<GetGrnGvnDetails>();

			getGrnGvnDetails = getGrnGvnDetailsList.getGrnGvnDetails();

			model.addObject("grnList", getGrnGvnDetails);
			model.addObject("fromDate", gateGrnFromDate);
			model.addObject("toDate", gateGrnToDate);

		} catch (Exception e) {

			System.out.println("Exce in Insert Grn " + e.getMessage());
			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showAccountGrn", method = RequestMethod.GET)
	public ModelAndView showAccountGrn(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("grngvn/accGrn");
		try {

			Constants.mainAct = 9;
			Constants.subAct = 92;

		} catch (Exception e) {

			System.out.println("Error in Getting grn details " + e.getMessage());

			e.printStackTrace();
		}

		return model;

	}

	public static String fromDate_Acc, toDate_Acc;

	@RequestMapping(value = "/showAccountGrnDetails", method = RequestMethod.GET)
	public ModelAndView showAccountGrnDetails(HttpServletRequest request, HttpServletResponse response) {

		Constants.mainAct = 9;
		Constants.subAct = 92;

		ModelAndView model = new ModelAndView("grngvn/accGrn");

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		try {

			System.out.println("from Date using ajax  :" + accGrnFromDate);
			System.out.println("to Date using ajax  : " + accGrnToDate);

			map.add("fromDate", accGrnFromDate);

			map.add("toDate", accGrnToDate);

			getGrnGvnDetailsList = restTemplate.postForObject(Constants.url + "getGrnDetail", map,
					GetGrnGvnDetailsList.class);

			getGrnGvnDetails = new ArrayList<GetGrnGvnDetails>();

			getGrnGvnDetails = getGrnGvnDetailsList.getGrnGvnDetails();

			model.addObject("grnList", getGrnGvnDetails);
			model.addObject("fromDate", accGrnFromDate);
			model.addObject("toDate", accGrnToDate);

			System.out.println("grn details " + getGrnGvnDetails.toString());

		} catch (Exception e) {

			System.out.println("Error in Getting gvn details " + e.getMessage());

			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/insertAccGrnByCheckBoxes", method = RequestMethod.GET) // Using checkboxes to insert
	public ModelAndView insertAccGrnByCheckBoxes(HttpServletRequest request, HttpServletResponse response) {

		Constants.mainAct = 9;
		Constants.subAct = 92;
		
		ModelAndView model = new ModelAndView("grngvn/accGrn");


		try {
			String[] grnIdList = request.getParameterValues("select_to_agree");


			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			List<GrnGvn> postGrnGvnList = new ArrayList<GrnGvn>();

			PostGrnGvnList postGrnList = new PostGrnGvnList();

			getGrnGvnDetails = getGrnGvnDetailsList.getGrnGvnDetails();
			System.out.println("grn details line 465 " + getGrnGvnDetails.toString());

			
			System.out.println("before for");

			for (int i = 0; i < grnIdList.length; i++) {
				System.out.println("grn id List" + grnIdList[i]);
				System.out.println("detail list = " + getGrnGvnDetails.get(i).getGrnGvnId());
				for (int j = 0; j < getGrnGvnDetails.size(); j++) {

					if (Integer.parseInt(grnIdList[i]) == getGrnGvnDetails.get(j).getGrnGvnId()) {
						
						int aproveAccLogin = Integer
								.parseInt(request.getParameter("approve_acc_login" + getGrnGvnDetails.get(j).getGrnGvnId()));

						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Calendar cal = Calendar.getInstance();
						System.out.println("************* Date Time " + dateFormat.format(cal.getTime()));

						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						java.util.Date date = null;

						try {
							date = sdf.parse(getGrnGvnDetails.get(j).getGrnGvnDate());
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						java.sql.Date grnGvnDate = new Date(date.getTime());

						GrnGvn postGrnGvn = new GrnGvn();

						postGrnGvn.setGrnGvnDate(grnGvnDate);// 1
						postGrnGvn.setGrnGvnId(getGrnGvnDetails.get(j).getGrnGvnId());
						postGrnGvn.setBillNo(getGrnGvnDetails.get(j).getBillNo());// 2
						postGrnGvn.setFrId(getGrnGvnDetails.get(j).getFrId());// 3
						postGrnGvn.setItemId(getGrnGvnDetails.get(j).getItemId());// 4
						postGrnGvn.setItemRate(getGrnGvnDetails.get(j).getItemRate());// 5
						postGrnGvn.setItemMrp(getGrnGvnDetails.get(j).getItemMrp());// 6
						postGrnGvn.setGrnGvnQty(getGrnGvnDetails.get(j).getGrnGvnQty());// 7
						postGrnGvn.setGrnType(getGrnGvnDetails.get(j).getGrnType());// 9
						postGrnGvn.setIsGrn(getGrnGvnDetails.get(j).getIsGrn());// 10
						postGrnGvn.setIsGrnEdit(getGrnGvnDetails.get(j).getIsGrnEdit());// 11
						postGrnGvn.setGrnGvnEntryDateTime(getGrnGvnDetails.get(j).getGrnGvnEntryDateTime());// 12
						postGrnGvn.setFrGrnGvnRemark(getGrnGvnDetails.get(j).getFrGrnGvnRemark());// 13
						postGrnGvn.setGvnPhotoUpload1(getGrnGvnDetails.get(j).getGvnPhotoUpload1());// 14
						postGrnGvn.setGvnPhotoUpload2(getGrnGvnDetails.get(j).getGvnPhotoUpload2());// 15
						postGrnGvn.setGrnGvnStatus(6);// 16
						postGrnGvn.setApprovedLoginGate(getGrnGvnDetails.get(j).getApprovedLoginGate());// 17dateFormat.format(cal.getTime()));//18
						postGrnGvn.setApprovedRemarkGate(getGrnGvnDetails.get(j).getApprovedRemarkGate());// 19
						postGrnGvn.setApprovedLoginStore(getGrnGvnDetails.get(j).getApprovedLoginStore());// 20
						postGrnGvn.setApprovedDateTimeStore(getGrnGvnDetails.get(j).getApprovedDateTimeStore());// 21
						postGrnGvn.setApprovedRemarkStore(getGrnGvnDetails.get(j).getApprovedRemarkStore());// 22

						postGrnGvn.setApprovedLoginAcc(aproveAccLogin);// 23
						postGrnGvn.setGrnApprovedDateTimeAcc(dateFormat.format(cal.getTime()));// 24
						postGrnGvn.setApprovedRemarkAcc("Default:Grn approved by Account");// 25

						postGrnGvn.setDelStatus(0);// 26
						postGrnGvn.setGrnGvnQtyAuto(getGrnGvnDetails.get(j).getGrnGvnQtyAuto());// 27
						postGrnGvn.setApproveimedDateTimeGate(getGrnGvnDetails.get(j).getApproveimedDateTimeGate());

						postGrnGvn.setGrnGvnAmt(getGrnGvnDetails.get(j).getGrnGvnAmt());

						postGrnGvnList.add(postGrnGvn);

						System.out.println("Done it inside if ");
						System.out.println("grn ID=  " + grnIdList[i]);

					} // end of if

					else {

						System.out.println("No match found " + getGrnGvnDetails.get(j).getGrnGvnId());
					} // end of else

				} // inner for

				postGrnList.setGrnGvn(postGrnGvnList);

			} // outer for
			System.out.println("after for");

			// postGrnList.setGrnGvn(postGrnGvnList);

			System.out.println("post grn for rest----- " + postGrnList.toString());
			System.out.println("post grn for rest size " + postGrnList.getGrnGvn().size());

			Info info = restTemplate.postForObject(Constants.url + "insertGrnGvn", postGrnList, Info.class);

			map.add("fromDate", accGrnFromDate);

			map.add("toDate", accGrnToDate);

			getGrnGvnDetailsList = restTemplate.postForObject(Constants.url + "getGrnDetail", map,
					GetGrnGvnDetailsList.class);

			getGrnGvnDetails = new ArrayList<GetGrnGvnDetails>();

			getGrnGvnDetails = getGrnGvnDetailsList.getGrnGvnDetails();

			model.addObject("grnList", getGrnGvnDetails);

			model.addObject("fromDate", accGrnFromDate);
			model.addObject("toDate", accGrnToDate);

			// System.out.println("Error in Getting grn details " + e.getMessage());

		} catch (Exception e) {

			System.out.println("Exce in Insert Grn " + e.getMessage());
			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/insertAccGrnProcessAgree", method = RequestMethod.GET)
	public String insertAccGrnProcessAgree(HttpServletRequest request, HttpServletResponse response) {

		Constants.mainAct = 9;
		Constants.subAct = 92;
		System.out.println("using a href to call insert account agree ");

		ModelAndView model = new ModelAndView("grngvn/accGrn");

		int grnId = Integer.parseInt(request.getParameter("grnId"));

		int accApproveLogin = Integer.parseInt(request.getParameter("approveAccLogin"));

		System.out.println("accApproveLogin =" + accApproveLogin);

		System.out.println("grnId =" + grnId);

		try {

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			System.out.println("************* Date Time " + dateFormat.format(cal.getTime()));

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("approvedLoginAcc", accApproveLogin);

			map.add("grnApprovedDateTimeAcc", dateFormat.format(cal.getTime()));

			map.add("approvedRemarkAcc", "Def:Acc Grn Approved");

			map.add("grnGvnStatus", 6);

			map.add("grnGvnId", grnId);

			String info = restTemplate.postForObject(Constants.url + "updateAccGrn", map, String.class);
			System.out.println("after calling web service acc grn agree ");

		} catch (Exception e) {

			// System.out.println("Error in Getting grn details " + e.getMessage());

			e.printStackTrace();
		}

		return "redirect:/showAccountGrnDetails";

	}

	@RequestMapping(value = "/insertAccGrnProcessDisAgree", method = RequestMethod.GET)
	public String insertAccGrnProcessDisAgree(HttpServletRequest request, HttpServletResponse response) {

		Constants.mainAct = 9;
		Constants.subAct = 92;
		System.out.println("using a href to call insert account agree ");

		ModelAndView model = new ModelAndView("grngvn/accGrn");

		int grnId = Integer.parseInt(request.getParameter("grnId"));

		int accApproveLogin = Integer.parseInt(request.getParameter("approveAccLogin"));
		String accRemark = request.getParameter("accRemark");

		System.out.println("accRemark =" + accRemark);

		System.out.println("accApproveLogin =" + accApproveLogin);

		System.out.println("grnId =" + grnId);

		try {

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			System.out.println("************* Date Time " + dateFormat.format(cal.getTime()));

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("approvedLoginAcc", accApproveLogin);

			map.add("grnApprovedDateTimeAcc", dateFormat.format(cal.getTime()));

			map.add("approvedRemarkAcc", accRemark);

			map.add("grnGvnStatus", 7);

			map.add("grnGvnId", grnId);

			String info = restTemplate.postForObject(Constants.url + "updateAccGrn", map, String.class);
			System.out.println("after calling web service acc grn disagree ");

		} catch (Exception e) {

			// System.out.println("Error in Getting grn details " + e.getMessage());

			e.printStackTrace();
		}

		return "redirect:/showAccountGrnDetails";

	}

	// GVN STARTED

	@RequestMapping(value = "/showGateGvn", method = RequestMethod.GET)
	public ModelAndView showGateGvn(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("grngvn/gateGvn");
		try {

			Constants.mainAct = 12;
			Constants.subAct = 121;

		} catch (Exception e) {

			System.out.println("Error in showing Gate gvn Page " + e.getMessage());

			e.printStackTrace();
		}

		return model;

	}



	@RequestMapping(value = "/showGateGvnDetails", method = RequestMethod.GET)
	public ModelAndView showGateGvnDetails(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("grngvn/gateGvn");

		Constants.mainAct = 12;
		Constants.subAct = 121;

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		RestTemplate restTemplate = new RestTemplate();
		
		System.out.println("from Date using ajax  :" + gateGvnFromDate);
		System.out.println("to Date using ajax  : " + gateGvnToDate);

		try {

			
			map.add("fromDate", gateGvnFromDate);
			map.add("toDate", gateGvnToDate);
			
			getGrnGvnDetailsList = restTemplate.postForObject(Constants.url + "getGvnDetails", map,
					GetGrnGvnDetailsList.class);

			getGrnGvnDetails = new ArrayList<GetGrnGvnDetails>();

			getGrnGvnDetails = getGrnGvnDetailsList.getGrnGvnDetails();

			model.addObject("url", Constants.SPCAKE_IMAGE_URL);
			model.addObject("gvnList", getGrnGvnDetails);

			System.out.println("gvn  details " + getGrnGvnDetails.toString());

		} catch (Exception e) {

			System.out.println("Error in Getting gvn details " + e.getMessage());

			e.printStackTrace();
		}

		model.addObject("fromDate", gateGvnFromDate);
		model.addObject("toDate", gateGvnToDate);

		return model;

	}

	@RequestMapping(value = "/insertGateGvnByCheckBoxes", method = RequestMethod.GET) // Using checkboxes to insert
	public ModelAndView insertGatevrnByCheckBoxes(HttpServletRequest request, HttpServletResponse response) {

		Constants.mainAct = 12;
		Constants.subAct = 121;

		ModelAndView model = new ModelAndView("grngvn/gateGvn");
		RestTemplate restTemplate = new RestTemplate();

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();



		try {
			String[] grnIdList = request.getParameterValues("select_to_agree");

			for (int k = 0; k < grnIdList.length; k++) {
				System.out.println("GVN id for gate approval " + grnIdList[k]);

			}

			
			List<GrnGvn> postGrnGvnList = new ArrayList<GrnGvn>();

			PostGrnGvnList postGrnList = new PostGrnGvnList();

			getGrnGvnDetails = getGrnGvnDetailsList.getGrnGvnDetails();
			System.out.println("grn details line 191 " + getGrnGvnDetails.toString());

			
			System.out.println("before for");

			for (int i = 0; i < grnIdList.length; i++) {
				System.out.println("grn id List" + grnIdList[i]);
				int apLoginGate = Integer.parseInt(request.getParameter("approve_gate_login" + grnIdList[i]));

				System.out.println("approve login gate =" + apLoginGate);

				System.out.println("detail list = " + getGrnGvnDetails.get(i).getGrnGvnId());
				for (int j = 0; j < getGrnGvnDetails.size(); j++) {

					if (Integer.parseInt(grnIdList[i]) == getGrnGvnDetails.get(j).getGrnGvnId()) {

						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Calendar cal = Calendar.getInstance();
						System.out.println("************* Date Time " + dateFormat.format(cal.getTime()));

						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						java.util.Date date = null;

						try {
							date = sdf.parse(getGrnGvnDetails.get(j).getGrnGvnDate());
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						java.sql.Date grnGvnDate = new Date(date.getTime());

						GrnGvn postGrnGvn = new GrnGvn();

						postGrnGvn.setGrnGvnDate(grnGvnDate);// 1
						postGrnGvn.setGrnGvnId(getGrnGvnDetails.get(j).getGrnGvnId());
						postGrnGvn.setBillNo(getGrnGvnDetails.get(j).getBillNo());// 2
						postGrnGvn.setFrId(getGrnGvnDetails.get(j).getFrId());// 3
						postGrnGvn.setItemId(getGrnGvnDetails.get(j).getItemId());// 4
						postGrnGvn.setItemRate(getGrnGvnDetails.get(j).getItemRate());// 5
						postGrnGvn.setItemMrp(getGrnGvnDetails.get(j).getItemMrp());// 6
						postGrnGvn.setGrnGvnQty(getGrnGvnDetails.get(j).getGrnGvnQty());// 7
						postGrnGvn.setGrnType(getGrnGvnDetails.get(j).getGrnType());// 9
						postGrnGvn.setIsGrn(getGrnGvnDetails.get(j).getIsGrn());// 10
						postGrnGvn.setIsGrnEdit(getGrnGvnDetails.get(j).getIsGrnEdit());// 11
						postGrnGvn.setGrnGvnEntryDateTime(getGrnGvnDetails.get(j).getGrnGvnEntryDateTime());// 12
						postGrnGvn.setFrGrnGvnRemark(getGrnGvnDetails.get(j).getFrGrnGvnRemark());// 13
						postGrnGvn.setGvnPhotoUpload1(getGrnGvnDetails.get(j).getGvnPhotoUpload1());// 14
						postGrnGvn.setGvnPhotoUpload2(getGrnGvnDetails.get(j).getGvnPhotoUpload2());// 15
						postGrnGvn.setGrnGvnStatus(2);// 16
						postGrnGvn.setApprovedLoginGate(apLoginGate);// 17dateFormat.format(cal.getTime()));//18
						postGrnGvn.setApprovedRemarkGate("Def: GVN Approved by Gate");// 19

						postGrnGvn.setApprovedLoginStore(0);// 20
						postGrnGvn.setApprovedDateTimeStore(getGrnGvnDetails.get(j).getApprovedDateTimeStore());// 21
						postGrnGvn.setApprovedRemarkStore(getGrnGvnDetails.get(j).getApprovedRemarkStore());// 22
						postGrnGvn.setApprovedLoginAcc(0);// 23
						postGrnGvn.setGrnApprovedDateTimeAcc(getGrnGvnDetails.get(j).getGrnApprovedDateTimeAcc());// 24
						postGrnGvn.setApprovedRemarkAcc(getGrnGvnDetails.get(j).getApprovedRemarkAcc());// 25

						postGrnGvn.setDelStatus(0);// 26
						postGrnGvn.setGrnGvnQtyAuto(0);// 27
						postGrnGvn.setApproveimedDateTimeGate(dateFormat.format(cal.getTime()));

						postGrnGvn.setGrnGvnAmt(getGrnGvnDetails.get(j).getGrnGvnAmt());

						postGrnGvnList.add(postGrnGvn);

						System.out.println("Done it inside if ");
						System.out.println("grn ID=  " + grnIdList[i]);

					} // end of if

					else {

						System.out.println("No match found " + getGrnGvnDetails.get(j).getGrnGvnId());
					} // end of else

				} // inner for

				postGrnList.setGrnGvn(postGrnGvnList);

			} // outer for
			System.out.println("after for");

			// postGrnList.setGrnGvn(postGrnGvnList);

			System.out.println("post grn for rest----- " + postGrnList.toString());
			System.out.println("post grn for rest size " + postGrnList.getGrnGvn().size());

			Info info = restTemplate.postForObject(Constants.url + "insertGrnGvn", postGrnList, Info.class);

			// System.out.println("Error in Getting grn details " + e.getMessage());

		} catch (Exception e) {

			System.out.println("Exce in Insert Grn " + e.getMessage());
			e.printStackTrace();

		}
		
		map.add("fromDate", gateGvnFromDate);
		map.add("toDate", gateGvnToDate);
		
		getGrnGvnDetailsList = restTemplate.postForObject(Constants.url + "getGvnDetails", map,
				GetGrnGvnDetailsList.class);

		getGrnGvnDetails = new ArrayList<GetGrnGvnDetails>();

		getGrnGvnDetails = getGrnGvnDetailsList.getGrnGvnDetails();

		model.addObject("url", Constants.SPCAKE_IMAGE_URL);
		model.addObject("gvnList", getGrnGvnDetails);

		model.addObject("fromDate", gateGvnFromDate);
		model.addObject("toDate", gateGvnToDate);
		
		
		return model;

	}

	@RequestMapping(value = "/insertGateGvnProcessAgree", method = RequestMethod.GET)
	public String insertGateGvnProcessAgree(HttpServletRequest request, HttpServletResponse response) {

		Constants.mainAct = 9;
		Constants.subAct = 91;
		System.out.println("using a href to call insert Gate gvn");

		ModelAndView model = new ModelAndView("grngvn/gateGvn");

		int grnId = Integer.parseInt(request.getParameter("grnId"));

		int gateApproveLogin = Integer.parseInt(request.getParameter("approveGateLogin"));

		System.out.println("gateApproveLogin =" + gateApproveLogin);

		System.out.println("grnId =" + grnId);

		try {

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			System.out.println("************* Date Time " + dateFormat.format(cal.getTime()));

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("approvedLoginGate", gateApproveLogin);

			map.add("approveimedDateTimeGate", dateFormat.format(cal.getTime()));

			map.add("approvedRemarkGate", "Def:Gvn  Approved by gate");

			map.add("grnGvnStatus", 2);

			map.add("grnGvnId", grnId);

			String info = restTemplate.postForObject(Constants.url + "updateGateGrn", map, String.class);
			System.out.println("after calling web service ");

		} catch (Exception e) {

			// System.out.println("Error in Getting grn details " + e.getMessage());

			e.printStackTrace();
		}

		return "redirect:/showGateGvnDetails";

	}

	@RequestMapping(value = "/insertGateGvnProcessDisAgree", method = RequestMethod.GET)
	public String insertGateGvnProcessDisAgree(HttpServletRequest request, HttpServletResponse response) {

		Constants.mainAct = 12;
		Constants.subAct = 121;
		System.out.println("using Ajax to call disagree");

		ModelAndView model = new ModelAndView("grngvn/gateGvn");
		try {
			int grnId = Integer.parseInt(request.getParameter("grnId"));

			int gateApproveLogin = Integer.parseInt(request.getParameter("approveGateLogin"));

			String gateRemark = request.getParameter("gateRemark");
			System.out.println("Gate Remark  " + gateRemark);

			System.out.println("gateApproveLogin =" + gateApproveLogin);

			System.out.println("grnId =" + grnId);

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			System.out.println("************* Date Time " + dateFormat.format(cal.getTime()));

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("approvedLoginGate", gateApproveLogin);

			map.add("approveimedDateTimeGate", dateFormat.format(cal.getTime()));

			map.add("approvedRemarkGate", gateRemark);

			map.add("grnGvnStatus", 3);

			map.add("grnGvnId", grnId);

			String info = restTemplate.postForObject(Constants.url + "updateGateGrn", map, String.class);
			System.out.println("after calling web service of disagree gvn by gate");

		} catch (Exception e) {

			// System.out.println("Error in Getting grn details " + e.getMessage());

			e.printStackTrace();
		}

		return "redirect:/showGateGvnDetails";

	}

	// Store GVN started

	@RequestMapping(value = "/showStoreGvn", method = RequestMethod.GET)
	public ModelAndView showStoreGvn(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("grngvn/storeGvn");
		try {

			Constants.mainAct = 12;
			Constants.subAct = 122;

		} catch (Exception e) {

			System.out.println("Error in Getting grn details " + e.getMessage());

			e.printStackTrace();
		}

		return model;

	}

	

	@RequestMapping(value = "/showStoreGvnDetails", method = RequestMethod.GET)
	public ModelAndView showStoreGvnDetails(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("grngvn/storeGvn");

		Constants.mainAct = 12;
		Constants.subAct = 122;

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		RestTemplate restTemplate = new RestTemplate();

		try {
			System.out.println("from Date using ajax  :" + storeGvnFromDate);
			System.out.println("to Date using ajax  : " + storeGvnToDate);
			
			map.add("fromDate", storeGvnFromDate);
			map.add("toDate", storeGvnToDate);
			
			getGrnGvnDetailsList = restTemplate.postForObject(Constants.url + "getGvnDetails", map,
					GetGrnGvnDetailsList.class);

			getGrnGvnDetails = new ArrayList<GetGrnGvnDetails>();

			getGrnGvnDetails = getGrnGvnDetailsList.getGrnGvnDetails();

			model.addObject("url", Constants.SPCAKE_IMAGE_URL);
			model.addObject("gvnList", getGrnGvnDetails);

			System.out.println("gvn  details " + getGrnGvnDetails.toString());

		} catch (Exception e) {

			System.out.println("Error in Getting gvn details " + e.getMessage());

			e.printStackTrace();
		}

		model.addObject("fromDate", storeGvnFromDate);
		model.addObject("toDate", storeGvnToDate);

		return model;

	}

	@RequestMapping(value = "/insertStoreGvnByCheckBoxes", method = RequestMethod.GET) // Using checkboxes to insert
	public ModelAndView insertStoreGvnByCheckBoxes(HttpServletRequest request, HttpServletResponse response) {

		Constants.mainAct = 12;
		Constants.subAct = 122;

		ModelAndView model = new ModelAndView("grngvn/storeGvn");
		RestTemplate restTemplate = new RestTemplate();

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();


		try {
			String[] grnIdList = request.getParameterValues("select_to_agree");

			for (int k = 0; k < grnIdList.length; k++) {
				System.out.println("GVN id for gate approval " + grnIdList[k]);

			}

			

			List<GrnGvn> postGrnGvnList = new ArrayList<GrnGvn>();

			PostGrnGvnList postGrnList = new PostGrnGvnList();

			getGrnGvnDetails = getGrnGvnDetailsList.getGrnGvnDetails();
			System.out.println("grn details line 191 " + getGrnGvnDetails.toString());

			System.out.println("before for");

			for (int i = 0; i < grnIdList.length; i++) {
				System.out.println("grn id List" + grnIdList[i]);
				int apLoginStore = Integer.parseInt(request.getParameter("approve_store_login" + grnIdList[i]));

				System.out.println("approve login Store =" + apLoginStore);

				System.out.println("detail list = " + getGrnGvnDetails.get(i).getGrnGvnId());
				for (int j = 0; j < getGrnGvnDetails.size(); j++) {

					if (Integer.parseInt(grnIdList[i]) == getGrnGvnDetails.get(j).getGrnGvnId()) {

						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Calendar cal = Calendar.getInstance();
						System.out.println("************* Date Time " + dateFormat.format(cal.getTime()));

						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						java.util.Date date = null;

						try {
							date = sdf.parse(getGrnGvnDetails.get(j).getGrnGvnDate());
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						java.sql.Date grnGvnDate = new Date(date.getTime());

						GrnGvn postGrnGvn = new GrnGvn();

						postGrnGvn.setGrnGvnDate(grnGvnDate);// 1
						postGrnGvn.setGrnGvnId(getGrnGvnDetails.get(j).getGrnGvnId());
						postGrnGvn.setBillNo(getGrnGvnDetails.get(j).getBillNo());// 2
						postGrnGvn.setFrId(getGrnGvnDetails.get(j).getFrId());// 3
						postGrnGvn.setItemId(getGrnGvnDetails.get(j).getItemId());// 4
						postGrnGvn.setItemRate(getGrnGvnDetails.get(j).getItemRate());// 5
						postGrnGvn.setItemMrp(getGrnGvnDetails.get(j).getItemMrp());// 6
						postGrnGvn.setGrnGvnQty(getGrnGvnDetails.get(j).getGrnGvnQty());// 7
						postGrnGvn.setGrnType(getGrnGvnDetails.get(j).getGrnType());// 9
						postGrnGvn.setIsGrn(getGrnGvnDetails.get(j).getIsGrn());// 10
						postGrnGvn.setIsGrnEdit(getGrnGvnDetails.get(j).getIsGrnEdit());// 11
						postGrnGvn.setGrnGvnEntryDateTime(getGrnGvnDetails.get(j).getGrnGvnEntryDateTime());// 12
						postGrnGvn.setFrGrnGvnRemark(getGrnGvnDetails.get(j).getFrGrnGvnRemark());// 13
						postGrnGvn.setGvnPhotoUpload1(getGrnGvnDetails.get(j).getGvnPhotoUpload1());// 14
						postGrnGvn.setGvnPhotoUpload2(getGrnGvnDetails.get(j).getGvnPhotoUpload2());// 15
						postGrnGvn.setGrnGvnStatus(4);// 16
						postGrnGvn.setApprovedLoginGate(getGrnGvnDetails.get(j).getApprovedLoginGate());// 17dateFormat.format(cal.getTime()));//18
						postGrnGvn.setApprovedRemarkGate(getGrnGvnDetails.get(j).getApprovedRemarkGate());// 19

						postGrnGvn.setApprovedLoginStore(apLoginStore);// 20
						postGrnGvn.setApprovedDateTimeStore(dateFormat.format(cal.getTime()));// 21
						postGrnGvn.setApprovedRemarkStore("Def: Approved by Store");// 22
						postGrnGvn.setApprovedLoginAcc(getGrnGvnDetails.get(j).getApprovedLoginAcc());// 23
						postGrnGvn.setGrnApprovedDateTimeAcc(getGrnGvnDetails.get(j).getGrnApprovedDateTimeAcc());// 24
						postGrnGvn.setApprovedRemarkAcc(getGrnGvnDetails.get(j).getApprovedRemarkAcc());// 25

						postGrnGvn.setDelStatus(0);// 26
						postGrnGvn.setGrnGvnQtyAuto(0);// 27
						postGrnGvn.setApproveimedDateTimeGate(getGrnGvnDetails.get(j).getApproveimedDateTimeGate());

						postGrnGvn.setGrnGvnAmt(getGrnGvnDetails.get(j).getGrnGvnAmt());
						postGrnGvnList.add(postGrnGvn);

						System.out.println("Done it inside if ");
						System.out.println("grn ID=  " + grnIdList[i]);

					} // end of if

					else {

						System.out.println("No match found " + getGrnGvnDetails.get(j).getGrnGvnId());
					} // end of else

				} // inner for

				postGrnList.setGrnGvn(postGrnGvnList);

			} // outer for
			System.out.println("after for");

			// postGrnList.setGrnGvn(postGrnGvnList);

			System.out.println("post grn for rest----- " + postGrnList.toString());
			System.out.println("post grn for rest size " + postGrnList.getGrnGvn().size());

			Info info = restTemplate.postForObject(Constants.url + "insertGrnGvn", postGrnList, Info.class);

			// System.out.println("Error in Getting grn details " + e.getMessage());

		} catch (Exception e) {

			System.out.println("Exce in Insert Grn " + e.getMessage());
			e.printStackTrace();

		}

		
		map.add("fromDate", storeGvnFromDate);
		map.add("toDate", storeGvnToDate);
		
		getGrnGvnDetailsList = restTemplate.postForObject(Constants.url + "getGvnDetails", map,
				GetGrnGvnDetailsList.class);

		getGrnGvnDetails = new ArrayList<GetGrnGvnDetails>();

		getGrnGvnDetails = getGrnGvnDetailsList.getGrnGvnDetails();

		model.addObject("url", Constants.SPCAKE_IMAGE_URL);
		model.addObject("gvnList", getGrnGvnDetails);
		model.addObject("fromDate", storeGvnFromDate);
		model.addObject("toDate", storeGvnToDate);
		
		return model;

	}

	@RequestMapping(value = "/insertStoreGvnProcessAgree", method = RequestMethod.GET)
	public String insertStoreGvnProcessAgree(HttpServletRequest request, HttpServletResponse response) {

		Constants.mainAct = 12;
		Constants.subAct = 122;

		ModelAndView model = new ModelAndView("grngvn/storeGvn");

		int grnId = Integer.parseInt(request.getParameter("grnId"));

		int storeApproveLogin = Integer.parseInt(request.getParameter("approveStoreLogin"));

		System.out.println("gateApproveLogin =" + storeApproveLogin);

		System.out.println("grnId =" + grnId);

		try {

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			System.out.println("************* Date Time " + dateFormat.format(cal.getTime()));

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("approvedLoginStore", storeApproveLogin);

			map.add("approvedDateTimeStore", dateFormat.format(cal.getTime()));

			map.add("approvedRemarkStore", "Def:Gvn  Approved by Store");

			map.add("grnGvnStatus", 4);

			map.add("grnGvnId", grnId);

			String info = restTemplate.postForObject(Constants.url + "updateStoreGvn", map, String.class);
			System.out.println("after calling web service ");

		} catch (Exception e) {

			// System.out.println("Error in Getting grn details " + e.getMessage());

			e.printStackTrace();
		}

		return "redirect:/showStoreGvnDetails";

	}

	@RequestMapping(value = "/insertStoreGvnProcessDisAgree", method = RequestMethod.GET)
	public String insertStoreGvnProcessDisAgree(HttpServletRequest request, HttpServletResponse response) {

		Constants.mainAct = 12;
		Constants.subAct = 122;
		System.out.println("using Ajax to call disagree");

		ModelAndView model = new ModelAndView("grngvn/storeGvn");
		try {
			int grnId = Integer.parseInt(request.getParameter("grnId"));

			int storeApproveLogin = Integer.parseInt(request.getParameter("approveStoreLogin"));

			String storeRemark = request.getParameter("storeRemark");
			System.out.println("storeRemark Remark  " + storeRemark);

			System.out.println("storeApproveLogin =" + storeApproveLogin);

			System.out.println("grnId =" + grnId);

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			System.out.println("************* Date Time " + dateFormat.format(cal.getTime()));

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("approvedLoginStore", storeApproveLogin);

			map.add("approvedDateTimeStore", dateFormat.format(cal.getTime()));

			map.add("approvedRemarkStore", storeRemark);

			map.add("grnGvnStatus", 5);

			map.add("grnGvnId", grnId);

			String info = restTemplate.postForObject(Constants.url + "updateStoreGvn", map, String.class);
			System.out.println("after calling web service of disagree gvn by gate");

		} catch (Exception e) {

			// System.out.println("Error in Getting grn details " + e.getMessage());

			e.printStackTrace();
		}

		return "redirect:/showGateGvnDetails";

	}

	// Acc GVN started

	@RequestMapping(value = "/showAccountGvn", method = RequestMethod.GET)
	public ModelAndView showAccountGvn(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("grngvn/accGvn");
		try {

			Constants.mainAct = 12;
			Constants.subAct = 123;

		} catch (Exception e) {

			System.out.println("Error in Getting grn details " + e.getMessage());

			e.printStackTrace();
		}

		return model;

	}

	

	@RequestMapping(value = "/showAccountGvnDetails", method = RequestMethod.GET)
	public ModelAndView showAccountGvnDetails(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("grngvn/accGvn");

		Constants.mainAct = 12;
		Constants.subAct = 123;

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		RestTemplate restTemplate = new RestTemplate();

		try {
			System.out.println("from Date using ajax  :" + accGvnFromDate);
			System.out.println("to Date using ajax  : " + accGvnToDate);

			
			map.add("fromDate", accGvnFromDate);
			map.add("toDate", accGvnToDate);
			
			getGrnGvnDetailsList = restTemplate.postForObject(Constants.url + "getGvnDetails", map,
					GetGrnGvnDetailsList.class);

			getGrnGvnDetails = new ArrayList<GetGrnGvnDetails>();

			getGrnGvnDetails = getGrnGvnDetailsList.getGrnGvnDetails();

			model.addObject("url", Constants.SPCAKE_IMAGE_URL);
			model.addObject("gvnList", getGrnGvnDetails);

			System.out.println("gvn  details " + getGrnGvnDetails.toString());

		} catch (Exception e) {

			System.out.println("Error in Getting gvn details " + e.getMessage());

			e.printStackTrace();
		}

		model.addObject("fromDate", accGvnFromDate);
		model.addObject("toDate", accGvnToDate);

		return model;

	}

	@RequestMapping(value = "/insertAccGvnByCheckBoxes", method = RequestMethod.GET) // Using checkboxes to insert
	public ModelAndView insertAccGvnByCheckBoxes(HttpServletRequest request, HttpServletResponse response) {

		Constants.mainAct = 12;
		Constants.subAct = 123;

		ModelAndView model = new ModelAndView("grngvn/accGvn");
		
		RestTemplate restTemplate = new RestTemplate();

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();


		try {
			String[] grnIdList = request.getParameterValues("select_to_agree");

			for (int k = 0; k < grnIdList.length; k++) {
				System.out.println("GVN id for gate approval " + grnIdList[k]);

			}

			
			List<GrnGvn> postGrnGvnList = new ArrayList<GrnGvn>();

			PostGrnGvnList postGrnList = new PostGrnGvnList();

			getGrnGvnDetails = getGrnGvnDetailsList.getGrnGvnDetails();
			System.out.println("grn details line 191 " + getGrnGvnDetails.toString());

			System.out.println("before for");

			for (int i = 0; i < grnIdList.length; i++) {
				System.out.println("grn id List" + grnIdList[i]);
				int apLoginAcc = Integer.parseInt(request.getParameter("approve_store_login" + grnIdList[i]));

				System.out.println("approve login apLoginAcc =" + apLoginAcc);

				System.out.println("detail list = " + getGrnGvnDetails.get(i).getGrnGvnId());
				for (int j = 0; j < getGrnGvnDetails.size(); j++) {

					if (Integer.parseInt(grnIdList[i]) == getGrnGvnDetails.get(j).getGrnGvnId()) {

						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Calendar cal = Calendar.getInstance();
						System.out.println("************* Date Time " + dateFormat.format(cal.getTime()));

						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						java.util.Date date = null;

						try {
							date = sdf.parse(getGrnGvnDetails.get(j).getGrnGvnDate());
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						java.sql.Date grnGvnDate = new Date(date.getTime());

						GrnGvn postGrnGvn = new GrnGvn();

						postGrnGvn.setGrnGvnDate(grnGvnDate);// 1
						postGrnGvn.setGrnGvnId(getGrnGvnDetails.get(j).getGrnGvnId());
						postGrnGvn.setBillNo(getGrnGvnDetails.get(j).getBillNo());// 2
						postGrnGvn.setFrId(getGrnGvnDetails.get(j).getFrId());// 3
						postGrnGvn.setItemId(getGrnGvnDetails.get(j).getItemId());// 4
						postGrnGvn.setItemRate(getGrnGvnDetails.get(j).getItemRate());// 5
						postGrnGvn.setItemMrp(getGrnGvnDetails.get(j).getItemMrp());// 6
						postGrnGvn.setGrnGvnQty(getGrnGvnDetails.get(j).getGrnGvnQty());// 7
						postGrnGvn.setGrnType(getGrnGvnDetails.get(j).getGrnType());// 9
						postGrnGvn.setIsGrn(getGrnGvnDetails.get(j).getIsGrn());// 10
						postGrnGvn.setIsGrnEdit(getGrnGvnDetails.get(j).getIsGrnEdit());// 11
						postGrnGvn.setGrnGvnEntryDateTime(getGrnGvnDetails.get(j).getGrnGvnEntryDateTime());// 12
						postGrnGvn.setFrGrnGvnRemark(getGrnGvnDetails.get(j).getFrGrnGvnRemark());// 13
						postGrnGvn.setGvnPhotoUpload1(getGrnGvnDetails.get(j).getGvnPhotoUpload1());// 14
						postGrnGvn.setGvnPhotoUpload2(getGrnGvnDetails.get(j).getGvnPhotoUpload2());// 15
						postGrnGvn.setGrnGvnStatus(6);// 16
						postGrnGvn.setApprovedLoginGate(getGrnGvnDetails.get(j).getApprovedLoginGate());// 17dateFormat.format(cal.getTime()));//18
						postGrnGvn.setApprovedRemarkGate(getGrnGvnDetails.get(j).getApprovedRemarkGate());// 19

						postGrnGvn.setApprovedLoginStore(getGrnGvnDetails.get(j).getApprovedLoginStore());// 20
						postGrnGvn.setApprovedDateTimeStore(dateFormat.format(cal.getTime()));// 21
						postGrnGvn.setApprovedRemarkStore(getGrnGvnDetails.get(j).getApprovedRemarkStore());// 22
						postGrnGvn.setApprovedLoginAcc(apLoginAcc);// 23
						postGrnGvn.setGrnApprovedDateTimeAcc(dateFormat.format(cal.getTime()));// 24
						postGrnGvn.setApprovedRemarkAcc("Def: GVN Approved by Account");// 25

						postGrnGvn.setDelStatus(0);// 26
						postGrnGvn.setGrnGvnQtyAuto(0);// 27
						postGrnGvn.setApproveimedDateTimeGate(getGrnGvnDetails.get(j).getApproveimedDateTimeGate());

						postGrnGvn.setGrnGvnAmt(getGrnGvnDetails.get(j).getGrnGvnAmt());

						postGrnGvnList.add(postGrnGvn);

						System.out.println("Done it inside if ");
						System.out.println("grn ID=  " + grnIdList[i]);

					} // end of if

					else {

						System.out.println("No match found " + getGrnGvnDetails.get(j).getGrnGvnId());
					} // end of else

				} // inner for

				postGrnList.setGrnGvn(postGrnGvnList);

			} // outer for
			System.out.println("after for");

			// postGrnList.setGrnGvn(postGrnGvnList);

			System.out.println("post grn for rest----- " + postGrnList.toString());
			System.out.println("post grn for rest size " + postGrnList.getGrnGvn().size());

			Info info = restTemplate.postForObject(Constants.url + "insertGrnGvn", postGrnList, Info.class);

			// System.out.println("Error in Getting grn details " + e.getMessage());

		} catch (Exception e) {

			System.out.println("Exce in Insert Grn " + e.getMessage());
			e.printStackTrace();

		}
		
		
		map.add("fromDate", accGvnFromDate);
		map.add("toDate", accGvnToDate);
		
		getGrnGvnDetailsList = restTemplate.postForObject(Constants.url + "getGvnDetails", map,
				GetGrnGvnDetailsList.class);

		getGrnGvnDetails = new ArrayList<GetGrnGvnDetails>();

		getGrnGvnDetails = getGrnGvnDetailsList.getGrnGvnDetails();

		model.addObject("url", Constants.SPCAKE_IMAGE_URL);
		model.addObject("gvnList", getGrnGvnDetails);

		model.addObject("fromDate", accGvnFromDate);
		model.addObject("toDate", accGvnToDate);

		return model;

	}

	@RequestMapping(value = "/insertAccGvnProcessAgree", method = RequestMethod.GET)
	public String insertAccGvnProcessAgree(HttpServletRequest request, HttpServletResponse response) {

		Constants.mainAct = 12;
		Constants.subAct = 123;

		ModelAndView model = new ModelAndView("grngvn/accGvn");

		int grnId = Integer.parseInt(request.getParameter("grnId"));

		int accApproveLogin = Integer.parseInt(request.getParameter("approveAccLogin"));

		System.out.println("accApproveLogin =" + accApproveLogin);

		System.out.println("grnId =" + grnId);

		try {

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			System.out.println("************* Date Time " + dateFormat.format(cal.getTime()));

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("approvedLoginAcc", accApproveLogin);

			map.add("grnApprovedDateTimeAcc", dateFormat.format(cal.getTime()));

			map.add("approvedRemarkAcc", "Def:Gvn  Approved by Acc");

			map.add("grnGvnStatus", 6);

			map.add("grnGvnId", grnId);

			String info = restTemplate.postForObject(Constants.url + "updateAccGrn", map, String.class);
			System.out.println("after calling web service ");

		} catch (Exception e) {

			// System.out.println("Error in Getting grn details " + e.getMessage());

			e.printStackTrace();
		}
		
		model.addObject("fromDate", accGvnFromDate);
		model.addObject("toDate", accGvnToDate);

		return "redirect:/showAccountGvnDetails";

	}

	@RequestMapping(value = "/insertAccGvnProcessDisAgree", method = RequestMethod.GET)
	public String insertAccGvnProcessDisAgree(HttpServletRequest request, HttpServletResponse response) {

		Constants.mainAct = 12;
		Constants.subAct = 123;

		ModelAndView model = new ModelAndView("grngvn/accGvn");

		int grnId = Integer.parseInt(request.getParameter("grnId"));
		System.out.println("1] Grn Id " + grnId);

		int accApproveLogin = Integer.parseInt(request.getParameter("approveAccLogin"));

		String accremark = request.getParameter("accRemark");

		System.out.println("accApproveLogin =" + accApproveLogin);

		System.out.println("2] accApproveLogin " + accApproveLogin);

		System.out.println("3] accremark" + accremark);

		try {

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			System.out.println("************* Date Time " + dateFormat.format(cal.getTime()));

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("approvedLoginAcc", accApproveLogin);

			map.add("grnApprovedDateTimeAcc", dateFormat.format(cal.getTime()));

			map.add("approvedRemarkAcc", accremark);

			map.add("grnGvnStatus", 7);

			map.add("grnGvnId", grnId);

			String info = restTemplate.postForObject(Constants.url + "updateAccGrn", map, String.class);

			System.out.println("after calling web service ");

		} catch (Exception e) {

			// System.out.println("Error in Getting grn details " + e.getMessage());

			e.printStackTrace();
		}
		
		
		model.addObject("fromDate", accGvnFromDate);
		model.addObject("toDate", accGvnToDate);

		return "redirect:/showAccountGvnDetails";

	}

}
