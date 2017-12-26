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
import javax.servlet.http.HttpSession;

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
import com.ats.adminpanel.model.login.UserResponse;
import com.ats.adminpanel.model.remarks.GetAllRemarks;
import com.ats.adminpanel.model.remarks.GetAllRemarksList;

@Controller
public class GrnGvnController {

	GetGrnGvnDetailsList getGrnGvnDetailsList;
	List<GetGrnGvnDetails> getGrnGvnDetails;

	GetAllRemarksList allRemarksList;
	List<GetAllRemarks> getAllRemarks;

	GetAllRemarksList getAllRemarksList=new GetAllRemarksList();

	public static String gateGrnFromDate, gateGrnToDate, accGrnFromDate, accGrnToDate;

	public static String gateGvnFromDate, gateGvnToDate, storeGvnFromDate, storeGvnToDate, accGvnFromDate, accGvnToDate;

	
	//getting Dates using ajax
	@RequestMapping(value = "/getDateForGvnAcc", method = RequestMethod.GET)
	public String getDateForGvnAcc(HttpServletRequest request, HttpServletResponse response) {

		accGvnFromDate = request.getParameter("fromDate");
		accGvnToDate = request.getParameter("toDate");
		
		

		return "";

	}

	@RequestMapping(value = "/getDateForGvnStore", method = RequestMethod.GET)
	public String getDateForGvnStore(HttpServletRequest request, HttpServletResponse response) {

		storeGvnFromDate = request.getParameter("fromDate");
		storeGvnToDate = request.getParameter("toDate");

		return "";

	}

	@RequestMapping(value = "/getDateForGvnGate", method = RequestMethod.GET)
	public String getDateForGvnGate(HttpServletRequest request, HttpServletResponse response) {

		gateGvnFromDate = request.getParameter("fromDate");
		gateGvnToDate = request.getParameter("toDate");

		return "";

	}

	@RequestMapping(value = "/getDateForGrnGate", method = RequestMethod.GET)
	public String getDateForGrnGate(HttpServletRequest request, HttpServletResponse response) {

		gateGrnFromDate = request.getParameter("fromDate");
		gateGrnToDate = request.getParameter("toDate");

		return "";

	}

	@RequestMapping(value = "/getDateForGrnAcc", method = RequestMethod.GET)
	public String getDateForGrnAcc(HttpServletRequest request, HttpServletResponse response) {

		accGrnFromDate = request.getParameter("fromDate");
		accGrnToDate = request.getParameter("toDate");

		return "";

	}
	//end of getting dates 

	@RequestMapping(value = "/showGateGrn", method = RequestMethod.GET)
	public ModelAndView showGateGrn(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("grngvn/gateGrn");
		try {

			Constants.mainAct = 9;
			Constants.subAct = 91;

		} catch (Exception e) {

			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/showGateGrnDetails", method = RequestMethod.GET)
	public ModelAndView showGateGrnDetails(HttpServletRequest request, HttpServletResponse response) {

		Constants.mainAct = 9;
		Constants.subAct = 91;

		ModelAndView model = new ModelAndView("grngvn/gateGrn");

		try {

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("fromDate", gateGrnFromDate);
			map.add("toDate", gateGrnToDate);
			

			getGrnGvnDetailsList = new GetGrnGvnDetailsList();

			getGrnGvnDetailsList = restTemplate.postForObject(Constants.url + "getGrnDetail", map,
					GetGrnGvnDetailsList.class);

			getGrnGvnDetails = new ArrayList<GetGrnGvnDetails>();

			getGrnGvnDetails = getGrnGvnDetailsList.getGrnGvnDetails();
			
			
			//Ganesh Remrk
			map=new LinkedMultiValueMap<String, Object>();
			map.add("isFrUsed", 0);
			map.add("moduleId", 1);
			map.add("subModuleId", 1); 
			  getAllRemarksList=restTemplate.postForObject(Constants.url + "/getAllRemarks",map, GetAllRemarksList.class);
			  
			
			

			//allRemarksList = restTemplate.getForObject(Constants.url + "getAllRemarks", GetAllRemarksList.class);

			getAllRemarks = new ArrayList<>();
			getAllRemarks = getAllRemarksList.getGetAllRemarks();

			System.out.println("remark list " + getAllRemarks.toString());

			model.addObject("remarkList", getAllRemarks);

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
	public String insertGateGrnProcessAgree(HttpServletRequest request, HttpServletResponse response) {

		Constants.mainAct = 9;
		Constants.subAct = 91;

		ModelAndView model = new ModelAndView("grngvn/gateGrn");

		
		try {
			
			int grnId = Integer.parseInt(request.getParameter("grnId"));

			int gateApproveLogin = Integer.parseInt(request.getParameter("approveGateLogin"));

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("approvedLoginGate", gateApproveLogin);

			map.add("approveimedDateTimeGate", dateFormat.format(cal.getTime()));

			map.add("approvedRemarkGate", "");

			map.add("grnGvnStatus", Constants.AP_BY_GATE);

			map.add("grnGvnId", grnId);

			String info = restTemplate.postForObject(Constants.url + "updateGateGrn", map, String.class);
			
			System.out.println("after calling web service of gate grn agree ");

		} catch (Exception e) {

			 System.out.println("Error in insert Gat eGrn Process Agree " + e.getMessage());
			 e.printStackTrace();
		}
		
		return "redirect:/showGateGrnDetails";

	}

	// insert gate grn for disagree
	@RequestMapping(value = "/insertGateGrnProcessDisAgree", method = RequestMethod.GET)
	public String insertGateGrnProcessDisAgree(HttpServletRequest request, HttpServletResponse response) {

		Constants.mainAct = 9;
		Constants.subAct = 91;

		ModelAndView model = new ModelAndView("grngvn/gateGrn");
		try {
			
			int grnId = Integer.parseInt(request.getParameter("grnId"));

			int gateApproveLogin = Integer.parseInt(request.getParameter("approveGateLogin"));

			String gateRemark = request.getParameter("gateRemark");
			

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("approvedLoginGate", gateApproveLogin);

			map.add("approveimedDateTimeGate", dateFormat.format(cal.getTime()));

			map.add("approvedRemarkGate", gateRemark);

			map.add("grnGvnStatus", Constants.DIS_BY_GATE);

			map.add("grnGvnId", grnId);

			String updateGateGrn = restTemplate.postForObject(Constants.url + "updateGateGrn", map, String.class);

			System.out.println("after calling web service of disagree");

		} catch (Exception e) {

			 System.out.println("Error in updating grn details " + e.getMessage());

			e.printStackTrace();
		}
		System.out.println("INSERT GATE GRN DISAPPROVE : STATUS =3");

		return "redirect:/showGateGrnDetails";
		
		}

	// insert gate grn by check boxes
	@RequestMapping(value = "/insertGateGrnByCheckBoxes", method = RequestMethod.POST) // Using checkboxes to insert
	public String insertGateGrnByCheckBoxes(HttpServletRequest request, HttpServletResponse response) {

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

			for (int i = 0; i < grnIdList.length; i++) {
				
				for (int j = 0; j < getGrnGvnDetails.size(); j++) {

					if (Integer.parseInt(grnIdList[i]) == getGrnGvnDetails.get(j).getGrnGvnId()) {

						int aproveGateLogin = Integer.parseInt(
								request.getParameter("approve_gate_login" + getGrnGvnDetails.get(j).getGrnGvnId()));

						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Calendar cal = Calendar.getInstance();

						DateFormat Df = new SimpleDateFormat("yyyy-MM-dd");

						java.util.Date grnGvnDate = getGrnGvnDetails.get(j).getGrnGvnDate();

						grnGvnDate = Df.parse(grnGvnDate.toString());

						GrnGvn postGrnGvn = new GrnGvn();

						postGrnGvn.setGrnGvnDate(grnGvnDate);
						postGrnGvn.setGrnGvnId(getGrnGvnDetails.get(j).getGrnGvnId());
						postGrnGvn.setBillNo(getGrnGvnDetails.get(j).getBillNo());
						postGrnGvn.setFrId(getGrnGvnDetails.get(j).getFrId());
						postGrnGvn.setItemId(getGrnGvnDetails.get(j).getItemId());
						postGrnGvn.setItemRate(getGrnGvnDetails.get(j).getItemRate());
						postGrnGvn.setItemMrp(getGrnGvnDetails.get(j).getItemMrp());
						postGrnGvn.setGrnGvnQty(getGrnGvnDetails.get(j).getGrnGvnQty());
						postGrnGvn.setGrnType(getGrnGvnDetails.get(j).getGrnType());
						postGrnGvn.setIsGrn(getGrnGvnDetails.get(j).getIsGrn());
						postGrnGvn.setIsGrnEdit(getGrnGvnDetails.get(j).getIsGrnEdit());
						postGrnGvn.setGrnGvnEntryDateTime(getGrnGvnDetails.get(j).getGrnGvnEntryDateTime());
						postGrnGvn.setFrGrnGvnRemark(getGrnGvnDetails.get(j).getFrGrnGvnRemark());
						postGrnGvn.setGvnPhotoUpload1(getGrnGvnDetails.get(j).getGvnPhotoUpload1());
						postGrnGvn.setGvnPhotoUpload2(getGrnGvnDetails.get(j).getGvnPhotoUpload2());
						postGrnGvn.setGrnGvnStatus(Constants.AP_BY_GATE);
						postGrnGvn.setApprovedLoginGate(aproveGateLogin);
						postGrnGvn.setApprovedRemarkGate("Def: Grn Approved By Gate");
						postGrnGvn.setApproveimedDateTimeGate(dateFormat.format(cal.getTime()));

						postGrnGvn.setApprovedLoginStore(getGrnGvnDetails.get(j).getApprovedLoginStore());
						postGrnGvn.setApprovedDateTimeStore(getGrnGvnDetails.get(j).getApprovedDateTimeStore());
						postGrnGvn.setApprovedRemarkStore(getGrnGvnDetails.get(j).getApprovedRemarkStore());
						postGrnGvn.setApprovedLoginAcc(getGrnGvnDetails.get(j).getApprovedLoginAcc());
						postGrnGvn.setGrnApprovedDateTimeAcc(getGrnGvnDetails.get(j).getGrnApprovedDateTimeAcc());
						postGrnGvn.setApprovedRemarkAcc(getGrnGvnDetails.get(j).getApprovedRemarkAcc());

						postGrnGvn.setDelStatus(0);
						postGrnGvn.setGrnGvnQtyAuto(0);

						postGrnGvn.setGrnGvnAmt(getGrnGvnDetails.get(j).getGrnGvnAmt());

						// newly added
						postGrnGvn.setIsTallySync(0);
						postGrnGvn.setBaseRate(getGrnGvnDetails.get(j).getBaseRate());
						postGrnGvn.setSgstPer(getGrnGvnDetails.get(j).getSgstPer());
						postGrnGvn.setCgstPer(getGrnGvnDetails.get(j).getCgstPer());
						postGrnGvn.setIgstPer(getGrnGvnDetails.get(j).getIgstPer());

						postGrnGvn.setTaxableAmt(getGrnGvnDetails.get(j).getTaxableAmt());
						postGrnGvn.setTotalTax(getGrnGvnDetails.get(j).getTotalTax());
						postGrnGvn.setFinalAmt(getGrnGvnDetails.get(j).getFinalAmt());
						postGrnGvn.setRoundUpAmt(getGrnGvnDetails.get(j).getRoundUpAmt());

						// ADDED
						postGrnGvn.setMenuId(getGrnGvnDetails.get(j).getMenuId());
						postGrnGvn.setCatId(getGrnGvnDetails.get(j).getCatId());
						postGrnGvn.setInvoiceNo(getGrnGvnDetails.get(j).getInvoiceNo());
						postGrnGvn.setRefInvoiceDate(getGrnGvnDetails.get(j).getRefInvoiceDate());

						postGrnGvnList.add(postGrnGvn);


					} // end of if

					else {

						
					} // end of else

				} // inner for

				postGrnList.setGrnGvn(postGrnGvnList);

			} // outer for

			
			System.out.println("post grn for rest----- " + postGrnList.toString());
			System.out.println("post grn for rest size " + postGrnList.getGrnGvn().size());

			Info insertGrn = restTemplate.postForObject(Constants.url + "insertGrnGvn", postGrnList, Info.class);

			
		} catch (Exception e) {

			System.out.println("Exce in Insert Grn Gate  " + e.getMessage());
			e.printStackTrace();

		}

		return "redirect:/showGateGrnDetails";

	}

	@RequestMapping(value = "/showAccountGrn", method = RequestMethod.GET)
	public ModelAndView showAccountGrn(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("grngvn/accGrn");
		try {

			Constants.mainAct = 9;
			Constants.subAct = 92;

		} catch (Exception e) {

			System.out.println("Error in showing Acc grn Page " + e.getMessage());

			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/showAccountGrnDetails", method = RequestMethod.GET)
	public ModelAndView showAccountGrnDetails(HttpServletRequest request, HttpServletResponse response) {

		Constants.mainAct = 9;
		Constants.subAct = 92;

		ModelAndView model = new ModelAndView("grngvn/accGrn");

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		try {

			
			map.add("fromDate", accGrnFromDate);

			map.add("toDate", accGrnToDate);

			getGrnGvnDetailsList = restTemplate.postForObject(Constants.url + "getGrnDetail", map,
					GetGrnGvnDetailsList.class);

			getGrnGvnDetails = new ArrayList<GetGrnGvnDetails>();

			getGrnGvnDetails = getGrnGvnDetailsList.getGrnGvnDetails();

			map=new LinkedMultiValueMap<String, Object>();
			map.add("isFrUsed", 0);
			map.add("moduleId", 1);
			map.add("subModuleId", 1); 
			  getAllRemarksList=restTemplate.postForObject(Constants.url + "/getAllRemarks",map, GetAllRemarksList.class);
			  
			

			//allRemarksList = restTemplate.getForObject(Constants.url + "getAllRemarks", GetAllRemarksList.class);

			getAllRemarks = new ArrayList<>();
			getAllRemarks = getAllRemarksList.getGetAllRemarks();

			model.addObject("remarkList", getAllRemarks);

			model.addObject("grnList", getGrnGvnDetails);
			model.addObject("fromDate", accGrnFromDate);
			model.addObject("toDate", accGrnToDate);

			System.out.println("grn details " + getGrnGvnDetails.toString());

		} catch (Exception e) {

			System.out.println("Error in Getting grn details " + e.getMessage());

			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/insertAccGrnByCheckBoxes", method = RequestMethod.GET) // Using checkboxes to insert
	public String insertAccGrnByCheckBoxes(HttpServletRequest request, HttpServletResponse response) {

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


			for (int i = 0; i < grnIdList.length; i++) {
			
				for (int j = 0; j < getGrnGvnDetails.size(); j++) {

					if (Integer.parseInt(grnIdList[i]) == getGrnGvnDetails.get(j).getGrnGvnId()) {

						int aproveAccLogin = Integer.parseInt(
								request.getParameter("approve_acc_login" + getGrnGvnDetails.get(j).getGrnGvnId()));

						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Calendar cal = Calendar.getInstance();

						DateFormat Df = new SimpleDateFormat("yyyy-MM-dd");

						java.util.Date grnGvnDate = getGrnGvnDetails.get(j).getGrnGvnDate();

						grnGvnDate = Df.parse(grnGvnDate.toString());

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
						postGrnGvn.setGrnGvnStatus(Constants.AP_BY_ACC);// 16
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

						// newly added

						postGrnGvn.setIsTallySync(0);
						postGrnGvn.setBaseRate(getGrnGvnDetails.get(j).getBaseRate());
						postGrnGvn.setSgstPer(getGrnGvnDetails.get(j).getSgstPer());
						postGrnGvn.setCgstPer(getGrnGvnDetails.get(j).getCgstPer());
						postGrnGvn.setIgstPer(getGrnGvnDetails.get(j).getIgstPer());

						postGrnGvn.setTaxableAmt(getGrnGvnDetails.get(j).getTaxableAmt());
						postGrnGvn.setTotalTax(getGrnGvnDetails.get(j).getTotalTax());
						postGrnGvn.setFinalAmt(getGrnGvnDetails.get(j).getFinalAmt());
						postGrnGvn.setRoundUpAmt(getGrnGvnDetails.get(j).getRoundUpAmt());

						// newly ad
						postGrnGvn.setMenuId(getGrnGvnDetails.get(j).getMenuId());
						postGrnGvn.setCatId(getGrnGvnDetails.get(j).getCatId());
						postGrnGvn.setInvoiceNo(getGrnGvnDetails.get(j).getInvoiceNo());
						postGrnGvn.setRefInvoiceDate(getGrnGvnDetails.get(j).getRefInvoiceDate());

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

			

			System.out.println("post grn for rest----- " + postGrnList.toString());
			System.out.println("post grn for rest size " + postGrnList.getGrnGvn().size());

			Info insertAccGrn = restTemplate.postForObject(Constants.url + "insertGrnGvn", postGrnList, Info.class);

		} catch (Exception e) {

			System.out.println("Exce in Insert Acc Grn " + e.getMessage());
			
			e.printStackTrace();

		}

		return "redirect:/showAccountGrnDetails";
	}

	@RequestMapping(value = "/insertAccGrnProcessAgree", method = RequestMethod.GET)
	public String insertAccGrnProcessAgree(HttpServletRequest request, HttpServletResponse response) {

		Constants.mainAct = 9;
		Constants.subAct = 92;
		System.out.println("using a href to call insert account agree ");

		ModelAndView model = new ModelAndView("grngvn/accGrn");
		
		try {
		int grnId = Integer.parseInt(request.getParameter("grnId"));

		int accApproveLogin = Integer.parseInt(request.getParameter("approveAccLogin"));

		


			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			System.out.println("************* Date Time " + dateFormat.format(cal.getTime()));

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("approvedLoginAcc", accApproveLogin);

			map.add("approvedDateTimeAcc", dateFormat.format(cal.getTime()));

			map.add("approvedRemarkAcc", "");

			map.add("grnGvnStatus", Constants.AP_BY_ACC);

			map.add("grnGvnId", grnId);

			String updateAccGrnAgree = restTemplate.postForObject(Constants.url + "updateAccGrn", map, String.class);

		} catch (Exception e) {

			System.out.println("Error in update Acc Grn Agree " + e.getMessage());

			e.printStackTrace();
		}

		return "redirect:/showAccountGrnDetails";

	}

	@RequestMapping(value = "/insertAccGrnProcessDisAgree", method = RequestMethod.GET)
	public String insertAccGrnProcessDisAgree(HttpServletRequest request, HttpServletResponse response) {

		Constants.mainAct = 9;
		Constants.subAct = 92;

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

			map.add("approvedDateTimeAcc", dateFormat.format(cal.getTime()));

			map.add("approvedRemarkAcc", accRemark);

			map.add("grnGvnStatus", Constants.DIS_BY_ACC);

			map.add("grnGvnId", grnId);

			String updateAccGrn = restTemplate.postForObject(Constants.url + "updateAccGrn", map, String.class);


		} catch (Exception e) {

			System.out.println("Error in update Acc Grn " + e.getMessage());

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
		
		try {

			map.add("fromDate", gateGvnFromDate);
			map.add("toDate", gateGvnToDate);

			getGrnGvnDetailsList = restTemplate.postForObject(Constants.url + "getGvnDetails", map,
					GetGrnGvnDetailsList.class);

			getGrnGvnDetails = new ArrayList<GetGrnGvnDetails>();

			getGrnGvnDetails = getGrnGvnDetailsList.getGrnGvnDetails();

			map=new LinkedMultiValueMap<String, Object>();
			map.add("isFrUsed", 0);
			map.add("moduleId", 1);
			map.add("subModuleId", 1); 
			  getAllRemarksList=restTemplate.postForObject(Constants.url + "/getAllRemarks",map, GetAllRemarksList.class);
			  
			
			

			//allRemarksList = restTemplate.getForObject(Constants.url + "getAllRemarks", GetAllRemarksList.class);

			getAllRemarks = new ArrayList<>();
			getAllRemarks = getAllRemarksList.getGetAllRemarks();

			model.addObject("remarkList", getAllRemarks);

			model.addObject("url", Constants.SPCAKE_IMAGE_URL);
			model.addObject("gvnList", getGrnGvnDetails);


		} catch (Exception e) {

			System.out.println("Error in Getting gvn details " + e.getMessage());

			e.printStackTrace();
		}

		model.addObject("fromDate", gateGvnFromDate);
		model.addObject("toDate", gateGvnToDate);

		return model;

	}

	@RequestMapping(value = "/insertGateGvnByCheckBoxes", method = RequestMethod.GET) // Using checkboxes to insert
	public String insertGateGvnByCheckBoxes(HttpServletRequest request, HttpServletResponse response) {

		Constants.mainAct = 12;
		Constants.subAct = 121;

		ModelAndView model = new ModelAndView("grngvn/gateGvn");
		RestTemplate restTemplate = new RestTemplate();

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		try {
			String[] grnIdList = request.getParameterValues("select_to_agree");

			

			List<GrnGvn> postGrnGvnList = new ArrayList<GrnGvn>();

			PostGrnGvnList postGrnList = new PostGrnGvnList();

			getGrnGvnDetails = getGrnGvnDetailsList.getGrnGvnDetails();
			System.out.println("grn details line 191 " + getGrnGvnDetails.toString());

			for (int i = 0; i < grnIdList.length; i++) {

				int apLoginGate = Integer.parseInt(request.getParameter("approve_gate_login" + grnIdList[i]));


				for (int j = 0; j < getGrnGvnDetails.size(); j++) {

					if (Integer.parseInt(grnIdList[i]) == getGrnGvnDetails.get(j).getGrnGvnId()) {

						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Calendar cal = Calendar.getInstance();

						

						DateFormat Df = new SimpleDateFormat("yyyy-MM-dd");

						java.util.Date grnGvnDate = getGrnGvnDetails.get(j).getGrnGvnDate();

						grnGvnDate = Df.parse(grnGvnDate.toString());

						GrnGvn postGrnGvn = new GrnGvn();

						postGrnGvn.setGrnGvnDate(grnGvnDate);
						postGrnGvn.setGrnGvnId(getGrnGvnDetails.get(j).getGrnGvnId());
						postGrnGvn.setBillNo(getGrnGvnDetails.get(j).getBillNo());
						postGrnGvn.setFrId(getGrnGvnDetails.get(j).getFrId());
						postGrnGvn.setItemId(getGrnGvnDetails.get(j).getItemId());
						postGrnGvn.setItemRate(getGrnGvnDetails.get(j).getItemRate());
						postGrnGvn.setItemMrp(getGrnGvnDetails.get(j).getItemMrp());
						postGrnGvn.setGrnGvnQty(getGrnGvnDetails.get(j).getGrnGvnQty());
						postGrnGvn.setGrnType(getGrnGvnDetails.get(j).getGrnType());
						postGrnGvn.setIsGrn(getGrnGvnDetails.get(j).getIsGrn());
						postGrnGvn.setIsGrnEdit(getGrnGvnDetails.get(j).getIsGrnEdit());
						postGrnGvn.setGrnGvnEntryDateTime(getGrnGvnDetails.get(j).getGrnGvnEntryDateTime());
						postGrnGvn.setFrGrnGvnRemark(getGrnGvnDetails.get(j).getFrGrnGvnRemark());
						postGrnGvn.setGvnPhotoUpload1(getGrnGvnDetails.get(j).getGvnPhotoUpload1());
						postGrnGvn.setGvnPhotoUpload2(getGrnGvnDetails.get(j).getGvnPhotoUpload2());
						postGrnGvn.setGrnGvnStatus(Constants.AP_BY_GATE);
						postGrnGvn.setApprovedLoginGate(apLoginGate);
						postGrnGvn.setApprovedRemarkGate("");

						postGrnGvn.setApprovedLoginStore(0);
						postGrnGvn.setApprovedDateTimeStore(getGrnGvnDetails.get(j).getApprovedDateTimeStore());
						postGrnGvn.setApprovedRemarkStore(getGrnGvnDetails.get(j).getApprovedRemarkStore());
						postGrnGvn.setApprovedLoginAcc(getGrnGvnDetails.get(j).getApprovedLoginAcc());
						postGrnGvn.setGrnApprovedDateTimeAcc(getGrnGvnDetails.get(j).getGrnApprovedDateTimeAcc());
						postGrnGvn.setApprovedRemarkAcc(getGrnGvnDetails.get(j).getApprovedRemarkAcc());

						postGrnGvn.setDelStatus(0);
						postGrnGvn.setGrnGvnQtyAuto(getGrnGvnDetails.get(j).getGrnGvnQtyAuto());
						postGrnGvn.setApproveimedDateTimeGate(dateFormat.format(cal.getTime()));

						postGrnGvn.setGrnGvnAmt(getGrnGvnDetails.get(j).getGrnGvnAmt());

						// newly added
						postGrnGvn.setIsTallySync(0);
						postGrnGvn.setBaseRate(getGrnGvnDetails.get(j).getBaseRate());
						postGrnGvn.setSgstPer(getGrnGvnDetails.get(j).getSgstPer());
						postGrnGvn.setCgstPer(getGrnGvnDetails.get(j).getCgstPer());
						postGrnGvn.setIgstPer(getGrnGvnDetails.get(j).getIgstPer());

						postGrnGvn.setTaxableAmt(getGrnGvnDetails.get(j).getTaxableAmt());
						postGrnGvn.setTotalTax(getGrnGvnDetails.get(j).getTotalTax());
						postGrnGvn.setFinalAmt(getGrnGvnDetails.get(j).getFinalAmt());
						postGrnGvn.setRoundUpAmt(getGrnGvnDetails.get(j).getRoundUpAmt());

						// newly ad
						postGrnGvn.setMenuId(getGrnGvnDetails.get(j).getMenuId());
						postGrnGvn.setCatId(getGrnGvnDetails.get(j).getCatId());
						postGrnGvn.setInvoiceNo(getGrnGvnDetails.get(j).getInvoiceNo());
						postGrnGvn.setRefInvoiceDate(getGrnGvnDetails.get(j).getRefInvoiceDate());

						postGrnGvnList.add(postGrnGvn);


					} // end of if

					else {

					} // end of else

				} // inner for

				postGrnList.setGrnGvn(postGrnGvnList);

			} // outer for


			System.out.println("post grn for rest----- " + postGrnList.toString());
			System.out.println("post grn for rest size " + postGrnList.getGrnGvn().size());

			Info insertGvn = restTemplate.postForObject(Constants.url + "insertGrnGvn", postGrnList, Info.class);


		} catch (Exception e) {

			System.out.println("Exce in Insert Gvn for Gate" + e.getMessage());
			e.printStackTrace();

		}

		return "redirect:/showGateGvnDetails";

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

			map.add("approvedRemarkGate", "");

			map.add("grnGvnStatus", Constants.AP_BY_GATE);

			map.add("grnGvnId", grnId);

			String updateGvnForGate = restTemplate.postForObject(Constants.url + "updateGateGrn", map, String.class);


		} catch (Exception e) {

			System.out.println("Error in Updating gvn for Gate " + e.getMessage());

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



			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("approvedLoginGate", gateApproveLogin);

			map.add("approveimedDateTimeGate", dateFormat.format(cal.getTime()));

			map.add("approvedRemarkGate", gateRemark);

			map.add("grnGvnStatus", Constants.DIS_BY_GATE);

			map.add("grnGvnId", grnId);

			String updateGvnByGateDisagree = restTemplate.postForObject(Constants.url + "updateGateGrn", map, String.class);

		} catch (Exception e) {

			 System.out.println("Error in update Gvn By Gate Disagree " + e.getMessage());

			e.printStackTrace();
		}

		return "redirect:/showGateGvnDetails";

	}

	// Store GVN 

	@RequestMapping(value = "/showStoreGvn", method = RequestMethod.GET)
	public ModelAndView showStoreGvn(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("grngvn/storeGvn");
		try {

			Constants.mainAct = 12;
			Constants.subAct = 122;

		} catch (Exception e) {

			System.out.println("Error in showing store gvn page " + e.getMessage());

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
			
		
			map.add("fromDate", storeGvnFromDate);
			map.add("toDate", storeGvnToDate);

			getGrnGvnDetailsList = restTemplate.postForObject(Constants.url + "getGvnDetails", map,
					GetGrnGvnDetailsList.class);

			getGrnGvnDetails = new ArrayList<GetGrnGvnDetails>();

			getGrnGvnDetails = getGrnGvnDetailsList.getGrnGvnDetails();

			map=new LinkedMultiValueMap<String, Object>();
			map.add("isFrUsed", 0);
			map.add("moduleId", 1);
			map.add("subModuleId", 1); 
			  getAllRemarksList=restTemplate.postForObject(Constants.url + "/getAllRemarks",map, GetAllRemarksList.class);
			  
			
			

			//allRemarksList = restTemplate.getForObject(Constants.url + "getAllRemarks", GetAllRemarksList.class);

			getAllRemarks = new ArrayList<>();
			getAllRemarks = getAllRemarksList.getGetAllRemarks();

			model.addObject("remarkList", getAllRemarks);

			model.addObject("url", Constants.SPCAKE_IMAGE_URL);
			model.addObject("gvnList", getGrnGvnDetails);

			System.out.println("gvn  details " + getGrnGvnDetails.toString());

		} catch (Exception e) {

			System.out.println("Error in Getting store gvn details " + e.getMessage());

			e.printStackTrace();
		}

		model.addObject("fromDate", storeGvnFromDate);
		model.addObject("toDate", storeGvnToDate);

		return model;

	}

	@RequestMapping(value = "/insertStoreGvnByCheckBoxes", method = RequestMethod.GET) // Using checkboxes to insert
	public String insertStoreGvnByCheckBoxes(HttpServletRequest request, HttpServletResponse response) {

		Constants.mainAct = 12;
		Constants.subAct = 122;

		ModelAndView model = new ModelAndView("grngvn/storeGvn");
		RestTemplate restTemplate = new RestTemplate();

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		try {
			String[] grnIdList = request.getParameterValues("select_to_agree");

			
			List<GrnGvn> postGrnGvnList = new ArrayList<GrnGvn>();

			PostGrnGvnList postGrnList = new PostGrnGvnList();

			getGrnGvnDetails = getGrnGvnDetailsList.getGrnGvnDetails();


			for (int i = 0; i < grnIdList.length; i++) {
				int apLoginStore = Integer.parseInt(request.getParameter("approve_store_login" + grnIdList[i]));


				for (int j = 0; j < getGrnGvnDetails.size(); j++) {

					if (Integer.parseInt(grnIdList[i]) == getGrnGvnDetails.get(j).getGrnGvnId()) {

						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Calendar cal = Calendar.getInstance();

						
						DateFormat Df = new SimpleDateFormat("yyyy-MM-dd");

						java.util.Date grnGvnDate = getGrnGvnDetails.get(j).getGrnGvnDate();

						grnGvnDate = Df.parse(grnGvnDate.toString());

						GrnGvn postGrnGvn = new GrnGvn();

						postGrnGvn.setGrnGvnDate(grnGvnDate);
						postGrnGvn.setGrnGvnId(getGrnGvnDetails.get(j).getGrnGvnId());
						postGrnGvn.setBillNo(getGrnGvnDetails.get(j).getBillNo());
						postGrnGvn.setFrId(getGrnGvnDetails.get(j).getFrId());
						postGrnGvn.setItemId(getGrnGvnDetails.get(j).getItemId());
						postGrnGvn.setItemRate(getGrnGvnDetails.get(j).getItemRate());
						postGrnGvn.setItemMrp(getGrnGvnDetails.get(j).getItemMrp());
						postGrnGvn.setGrnGvnQty(getGrnGvnDetails.get(j).getGrnGvnQty());
						postGrnGvn.setGrnType(getGrnGvnDetails.get(j).getGrnType());
						postGrnGvn.setIsGrn(getGrnGvnDetails.get(j).getIsGrn());
						postGrnGvn.setIsGrnEdit(getGrnGvnDetails.get(j).getIsGrnEdit());
						postGrnGvn.setGrnGvnEntryDateTime(getGrnGvnDetails.get(j).getGrnGvnEntryDateTime());
						postGrnGvn.setFrGrnGvnRemark(getGrnGvnDetails.get(j).getFrGrnGvnRemark());
						postGrnGvn.setGvnPhotoUpload1(getGrnGvnDetails.get(j).getGvnPhotoUpload1());
						postGrnGvn.setGvnPhotoUpload2(getGrnGvnDetails.get(j).getGvnPhotoUpload2());
						postGrnGvn.setGrnGvnStatus(Constants.AP_BY_STORE);
						postGrnGvn.setApprovedLoginGate(getGrnGvnDetails.get(j).getApprovedLoginGate());
						postGrnGvn.setApprovedRemarkGate(getGrnGvnDetails.get(j).getApprovedRemarkGate());

						postGrnGvn.setApprovedLoginStore(apLoginStore);
						postGrnGvn.setApprovedDateTimeStore(dateFormat.format(cal.getTime()));
						postGrnGvn.setApprovedRemarkStore("Def: Approved by Store");
						postGrnGvn.setApprovedLoginAcc(getGrnGvnDetails.get(j).getApprovedLoginAcc());
						postGrnGvn.setGrnApprovedDateTimeAcc(getGrnGvnDetails.get(j).getGrnApprovedDateTimeAcc());
						postGrnGvn.setApprovedRemarkAcc(getGrnGvnDetails.get(j).getApprovedRemarkAcc());// 25

						postGrnGvn.setDelStatus(0);
						postGrnGvn.setGrnGvnQtyAuto(getGrnGvnDetails.get(j).getGrnGvnQtyAuto());
						postGrnGvn.setApproveimedDateTimeGate(getGrnGvnDetails.get(j).getApproveimedDateTimeGate());

						postGrnGvn.setGrnGvnAmt(getGrnGvnDetails.get(j).getGrnGvnAmt());

						// newly added

						postGrnGvn.setIsTallySync(0);
						postGrnGvn.setBaseRate(getGrnGvnDetails.get(j).getBaseRate());
						postGrnGvn.setSgstPer(getGrnGvnDetails.get(j).getSgstPer());
						postGrnGvn.setCgstPer(getGrnGvnDetails.get(j).getCgstPer());
						postGrnGvn.setIgstPer(getGrnGvnDetails.get(j).getIgstPer());

						postGrnGvn.setTaxableAmt(getGrnGvnDetails.get(j).getTaxableAmt());
						postGrnGvn.setTotalTax(getGrnGvnDetails.get(j).getTotalTax());
						postGrnGvn.setFinalAmt(getGrnGvnDetails.get(j).getFinalAmt());
						postGrnGvn.setRoundUpAmt(getGrnGvnDetails.get(j).getRoundUpAmt());

						// newly ad
						postGrnGvn.setMenuId(getGrnGvnDetails.get(j).getMenuId());
						postGrnGvn.setCatId(getGrnGvnDetails.get(j).getCatId());
						postGrnGvn.setInvoiceNo(getGrnGvnDetails.get(j).getInvoiceNo());
						postGrnGvn.setRefInvoiceDate(getGrnGvnDetails.get(j).getRefInvoiceDate());

						postGrnGvnList.add(postGrnGvn);

					
					} // end of if

					else {

					} // end of else

				} // inner for

				postGrnList.setGrnGvn(postGrnGvnList);

			} // outer for
			System.out.println("after for");


			System.out.println("post grn for rest----- " + postGrnList.toString());
			System.out.println("post grn for rest size " + postGrnList.getGrnGvn().size());

			Info info = restTemplate.postForObject(Constants.url + "insertGrnGvn", postGrnList, Info.class);


		} catch (Exception e) {

			System.out.println("Exce in Insert store gvn " + e.getMessage());
			e.printStackTrace();

		}

		return "redirect:/showStoreGvnDetails";

	}

	@RequestMapping(value = "/insertStoreGvnProcessAgree", method = RequestMethod.GET)
	public String insertStoreGvnProcessAgree(HttpServletRequest request, HttpServletResponse response) {

		Constants.mainAct = 12;
		Constants.subAct = 122;

		ModelAndView model = new ModelAndView("grngvn/storeGvn");

		try {

		int grnId = Integer.parseInt(request.getParameter("grnId"));

		int storeApproveLogin = Integer.parseInt(request.getParameter("approveStoreLogin"));

		


			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("approvedLoginStore", storeApproveLogin);

			map.add("approvedDateTimeStore", dateFormat.format(cal.getTime()));

			map.add("approvedRemarkStore", "");

			map.add("grnGvnStatus", Constants.AP_BY_STORE);

			map.add("grnGvnId", grnId);

			String updateStoreGvn = restTemplate.postForObject(Constants.url + "updateStoreGvn", map, String.class);

		} catch (Exception e) {

			System.out.println("Error in update Store Gvn " + e.getMessage());

			e.printStackTrace();
		}

		return "redirect:/showStoreGvnDetails";

	}

	@RequestMapping(value = "/insertStoreGvnProcessDisAgree", method = RequestMethod.GET)
	public String insertStoreGvnProcessDisAgree(HttpServletRequest request, HttpServletResponse response) {

		Constants.mainAct = 12;
		Constants.subAct = 122;

		ModelAndView model = new ModelAndView("grngvn/storeGvn");
		try {
			
			int grnId = Integer.parseInt(request.getParameter("grnId"));

			int storeApproveLogin = Integer.parseInt(request.getParameter("approveStoreLogin"));

			String storeRemark = request.getParameter("storeRemark");
			
			

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("approvedLoginStore", storeApproveLogin);

			map.add("approvedDateTimeStore", dateFormat.format(cal.getTime()));

			map.add("approvedRemarkStore", storeRemark);

			map.add("grnGvnStatus", Constants.DIS_BY_STORE);

			map.add("grnGvnId", grnId);

			String updateStoreGvn = restTemplate.postForObject(Constants.url + "updateStoreGvn", map, String.class);

		} catch (Exception e) {

			System.out.println("Error in update Store Gvn " + e.getMessage());

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

			System.out.println("Error in showing page acc gvn  " + e.getMessage());
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
			
			map.add("fromDate", accGvnFromDate);
			map.add("toDate", accGvnToDate);

			getGrnGvnDetailsList = restTemplate.postForObject(Constants.url + "getGvnDetails", map,
					GetGrnGvnDetailsList.class);

			getGrnGvnDetails = new ArrayList<GetGrnGvnDetails>();

			getGrnGvnDetails = getGrnGvnDetailsList.getGrnGvnDetails();

			map=new LinkedMultiValueMap<String, Object>();
			map.add("isFrUsed", 0);
			map.add("moduleId", 1);
			map.add("subModuleId", 1); 
			  getAllRemarksList=restTemplate.postForObject(Constants.url + "/getAllRemarks",map, GetAllRemarksList.class);
			  
			
			

			//allRemarksList = restTemplate.getForObject(Constants.url + "getAllRemarks", GetAllRemarksList.class);

			getAllRemarks = new ArrayList<>();
			getAllRemarks = getAllRemarksList.getGetAllRemarks();

			model.addObject("remarkList", getAllRemarks);

			model.addObject("url", Constants.SPCAKE_IMAGE_URL);
			model.addObject("gvnList", getGrnGvnDetails);
			
			model.addObject("fromDate", accGvnFromDate);
			model.addObject("toDate", accGvnToDate);


		} catch (Exception e) {

			System.out.println("Error in Getting Acc  gvn details " + e.getMessage());

			e.printStackTrace();
		}

		
		return model;

	}

	@RequestMapping(value = "/insertAccGvnByCheckBoxes", method = RequestMethod.GET) // Using checkboxes to insert
	public String insertAccGvnByCheckBoxes(HttpServletRequest request, HttpServletResponse response) {

		Constants.mainAct = 12;
		Constants.subAct = 123;

		ModelAndView model = new ModelAndView("grngvn/accGvn");

		RestTemplate restTemplate = new RestTemplate();

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		try {
			String[] grnIdList = request.getParameterValues("select_to_agree");

			
			List<GrnGvn> postGrnGvnList = new ArrayList<GrnGvn>();

			PostGrnGvnList postGrnList = new PostGrnGvnList();

			getGrnGvnDetails = getGrnGvnDetailsList.getGrnGvnDetails();


			for (int i = 0; i < grnIdList.length; i++) {
				
				int apLoginAcc = Integer.parseInt(request.getParameter("approve_acc_login" + grnIdList[i]));

				for (int j = 0; j < getGrnGvnDetails.size(); j++) {

					if (Integer.parseInt(grnIdList[i]) == getGrnGvnDetails.get(j).getGrnGvnId()) {

						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Calendar cal = Calendar.getInstance();

						

						DateFormat Df = new SimpleDateFormat("yyyy-MM-dd");

						java.util.Date grnGvnDate = getGrnGvnDetails.get(j).getGrnGvnDate();

						grnGvnDate = Df.parse(grnGvnDate.toString());

						GrnGvn postGrnGvn = new GrnGvn();

						postGrnGvn.setGrnGvnDate(grnGvnDate);
						postGrnGvn.setGrnGvnId(getGrnGvnDetails.get(j).getGrnGvnId());
						postGrnGvn.setBillNo(getGrnGvnDetails.get(j).getBillNo());
						postGrnGvn.setFrId(getGrnGvnDetails.get(j).getFrId());
						postGrnGvn.setItemId(getGrnGvnDetails.get(j).getItemId());
						postGrnGvn.setItemRate(getGrnGvnDetails.get(j).getItemRate());
						postGrnGvn.setItemMrp(getGrnGvnDetails.get(j).getItemMrp());
						postGrnGvn.setGrnGvnQty(getGrnGvnDetails.get(j).getGrnGvnQty());
						postGrnGvn.setGrnType(getGrnGvnDetails.get(j).getGrnType());
						postGrnGvn.setIsGrn(getGrnGvnDetails.get(j).getIsGrn());
						postGrnGvn.setIsGrnEdit(getGrnGvnDetails.get(j).getIsGrnEdit());
						postGrnGvn.setGrnGvnEntryDateTime(getGrnGvnDetails.get(j).getGrnGvnEntryDateTime());
						postGrnGvn.setFrGrnGvnRemark(getGrnGvnDetails.get(j).getFrGrnGvnRemark());
						postGrnGvn.setGvnPhotoUpload1(getGrnGvnDetails.get(j).getGvnPhotoUpload1());
						postGrnGvn.setGvnPhotoUpload2(getGrnGvnDetails.get(j).getGvnPhotoUpload2());
						postGrnGvn.setGrnGvnStatus(6);
						postGrnGvn.setApprovedLoginGate(getGrnGvnDetails.get(j).getApprovedLoginGate());
						postGrnGvn.setApprovedRemarkGate(getGrnGvnDetails.get(j).getApprovedRemarkGate());

						postGrnGvn.setApprovedLoginStore(getGrnGvnDetails.get(j).getApprovedLoginStore());
						postGrnGvn.setApprovedDateTimeStore(dateFormat.format(cal.getTime()));
						postGrnGvn.setApprovedRemarkStore(getGrnGvnDetails.get(j).getApprovedRemarkStore());
						postGrnGvn.setApprovedLoginAcc(apLoginAcc);
						postGrnGvn.setGrnApprovedDateTimeAcc(dateFormat.format(cal.getTime()));
						postGrnGvn.setApprovedRemarkAcc("Def: GVN Approved by Account");

						postGrnGvn.setDelStatus(getGrnGvnDetails.get(j).getDelStatus());
						postGrnGvn.setGrnGvnQtyAuto(getGrnGvnDetails.get(j).getGrnGvnQtyAuto());
						postGrnGvn.setApproveimedDateTimeGate(getGrnGvnDetails.get(j).getApproveimedDateTimeGate());

						postGrnGvn.setGrnGvnAmt(getGrnGvnDetails.get(j).getGrnGvnAmt());

						// newly added

						postGrnGvn.setIsTallySync(0);
						postGrnGvn.setBaseRate(getGrnGvnDetails.get(j).getBaseRate());
						postGrnGvn.setSgstPer(getGrnGvnDetails.get(j).getSgstPer());
						postGrnGvn.setCgstPer(getGrnGvnDetails.get(j).getCgstPer());
						postGrnGvn.setIgstPer(getGrnGvnDetails.get(j).getIgstPer());

						postGrnGvn.setTaxableAmt(getGrnGvnDetails.get(j).getTaxableAmt());
						postGrnGvn.setTotalTax(getGrnGvnDetails.get(j).getTotalTax());
						postGrnGvn.setFinalAmt(getGrnGvnDetails.get(j).getFinalAmt());
						postGrnGvn.setRoundUpAmt(getGrnGvnDetails.get(j).getRoundUpAmt());

						// newly ad
						postGrnGvn.setMenuId(getGrnGvnDetails.get(j).getMenuId());
						postGrnGvn.setCatId(getGrnGvnDetails.get(j).getCatId());
						postGrnGvn.setInvoiceNo(getGrnGvnDetails.get(j).getInvoiceNo());
						postGrnGvn.setRefInvoiceDate(getGrnGvnDetails.get(j).getRefInvoiceDate());

						postGrnGvnList.add(postGrnGvn);

						
					} // end of if

					else {

					} // end of else

				} // inner for

				postGrnList.setGrnGvn(postGrnGvnList);

			} // outer for


			System.out.println("post grn for rest----- " + postGrnList.toString());
			System.out.println("post grn for rest size " + postGrnList.getGrnGvn().size());

			Info insertGvnForAcc = restTemplate.postForObject(Constants.url + "insertGrnGvn", postGrnList, Info.class);


		} catch (Exception e) {

			System.out.println("Exce in Insert Gvn For Acc " + e.getMessage());
			e.printStackTrace();

		}
		return "redirect:/showAccountGvnDetails";
	}

	@RequestMapping(value = "/insertAccGvnProcessAgree", method = RequestMethod.GET)
	public String insertAccGvnProcessAgree(HttpServletRequest request, HttpServletResponse response) {

		Constants.mainAct = 12;
		Constants.subAct = 123;

		ModelAndView model = new ModelAndView("grngvn/accGvn");
		
		try {

		int grnId = Integer.parseInt(request.getParameter("grnId"));

		int accApproveLogin = Integer.parseInt(request.getParameter("approveAccLogin"));

		
		

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("approvedLoginAcc", accApproveLogin);

			map.add("approvedDateTimeAcc", dateFormat.format(cal.getTime()));

			map.add("approvedRemarkAcc", "");

			map.add("grnGvnStatus", Constants.AP_BY_ACC);

			map.add("grnGvnId", grnId);

			String updateAccGvn = restTemplate.postForObject(Constants.url + "updateAccGrn", map, String.class);
			
			model.addObject("fromDate", accGvnFromDate);
			model.addObject("toDate", accGvnToDate);

		} catch (Exception e) {

			System.out.println("Error in update Acc Gvn  " + e.getMessage());

			e.printStackTrace();
		}

		
		return "redirect:/showAccountGvnDetails";

	}

	@RequestMapping(value = "/insertAccGvnProcessDisAgree", method = RequestMethod.GET)
	public String insertAccGvnProcessDisAgree(HttpServletRequest request, HttpServletResponse response) {

		Constants.mainAct = 12;
		Constants.subAct = 123;

		ModelAndView model = new ModelAndView("grngvn/accGvn");
		
		try {

		int grnId = Integer.parseInt(request.getParameter("grnId"));

		int accApproveLogin = Integer.parseInt(request.getParameter("approveAccLogin"));

		String accremark = request.getParameter("accRemark");

		
		
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("approvedLoginAcc", accApproveLogin);

			map.add("approvedDateTimeAcc", dateFormat.format(cal.getTime()));

			map.add("approvedRemarkAcc", accremark);

			map.add("grnGvnStatus", Constants.DIS_BY_ACC);

			map.add("grnGvnId", grnId);

			String updateAccGvn = restTemplate.postForObject(Constants.url + "updateAccGrn", map, String.class);
			
			model.addObject("fromDate", accGvnFromDate);
			model.addObject("toDate", accGvnToDate);

			System.out.println("after calling web service ");

		} catch (Exception e) {

			 System.out.println("Error in update Acc Gvn " + e.getMessage());
		   	e.printStackTrace();
		}

		
		return "redirect:/showAccountGvnDetails";

	}

}
