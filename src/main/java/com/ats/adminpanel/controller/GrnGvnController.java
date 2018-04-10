package com.ats.adminpanel.controller;

import java.math.BigDecimal;
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

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.commons.Firebase;
import com.ats.adminpanel.model.AllFrIdName;
import com.ats.adminpanel.model.AllFrIdNameList;
import com.ats.adminpanel.model.Info;
import com.ats.adminpanel.model.franchisee.Menu;
import com.ats.adminpanel.model.grngvn.GetGrnGvnDetails;
import com.ats.adminpanel.model.grngvn.GetGrnGvnDetailsList;
import com.ats.adminpanel.model.grngvn.GrnGvn;
import com.ats.adminpanel.model.grngvn.GrnGvnHeader;
import com.ats.adminpanel.model.grngvn.GrnGvnHeaderList;
import com.ats.adminpanel.model.grngvn.PostGrnGvnList;
import com.ats.adminpanel.model.grngvn.TempGrnGvnBeanUp;
import com.ats.adminpanel.model.login.UserResponse;
import com.ats.adminpanel.model.remarks.GetAllRemarks;
import com.ats.adminpanel.model.remarks.GetAllRemarksList;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
   

@Controller
@Scope("session")
public class GrnGvnController {
 
	public static float roundUp(float d) {
		return BigDecimal.valueOf(d).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
	}

	GetGrnGvnDetailsList getGrnGvnDetailsList;
	List<GetGrnGvnDetails> getGrnGvnDetails;

	GetAllRemarksList allRemarksList;
	List<GetAllRemarks> getAllRemarks;

	GetAllRemarksList getAllRemarksList = new GetAllRemarksList();

	public static String gateGrnFromDate, gateGrnToDate, accGrnFromDate, accGrnToDate;

	public static String gateGvnFromDate, gateGvnToDate, storeGvnFromDate, storeGvnToDate, accGvnFromDate, accGvnToDate;

	public String gateGrnHeaderFromDate, gateGrnHeaderToDate;

	// 16 FEB NEW
	// List<GrnGvnHeader> grnHeaderList;

	List<GrnGvnHeader> grnGateHeaderList, grnAccHeaderList;

	GrnGvnHeaderList headerList = new GrnGvnHeaderList();

	List<GetGrnGvnDetails> grnGateDetailList = new ArrayList<>();
	List<GetGrnGvnDetails> grnAccDetailList = new ArrayList<>();

	public AllFrIdNameList allFrIdNameList = new AllFrIdNameList();

	public List<String> frList = new ArrayList<String>();

	String frSelectedGateHeader, frSelectedAccHeader;
	String accGrnHeaderFromDate, accGrnHeaderToDate;

	int globalGateHeaderId, globalAccHeaderId;

	@RequestMapping(value = "/getDateForGateHeader", method = RequestMethod.GET)
	public String getDateForGateHeader(HttpServletRequest request, HttpServletResponse response) {

		gateGrnHeaderFromDate = request.getParameter("fromDate");
		gateGrnHeaderToDate = request.getParameter("toDate");

		String selectedFr = request.getParameter("fr_id_list");

		System.out.println(
				"From Date " + gateGrnHeaderFromDate + "toDate " + gateGrnHeaderToDate + "fr ID List " + selectedFr);
		selectedFr = selectedFr.substring(1, selectedFr.length() - 1);
		selectedFr = selectedFr.replaceAll("\"", "");

		frSelectedGateHeader = selectedFr;

		frList = Arrays.asList(selectedFr);

		System.out.println("Fr List Final " + frList);

		return "";

	}

	@RequestMapping(value = "/getGrnHeaderForGate", method = RequestMethod.GET)
	public ModelAndView getGrnHeaderForGate(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("grngvn/gateGrnHeader");

		boolean isAllFrSelected = false;

		try {

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			allFrIdNameList = new AllFrIdNameList();
			try {

				allFrIdNameList = restTemplate.getForObject(Constants.url + "getAllFrIdName", AllFrIdNameList.class);

			} catch (Exception e) {
				System.out.println("Exception in getAllFrIdName" + e.getMessage());
				e.printStackTrace();

			}
			model.addObject("unSelectedFrList", allFrIdNameList.getFrIdNamesList());

			if (gateGrnHeaderFromDate == "" || gateGrnHeaderFromDate == null) {

				String statusList = new String();

				statusList = "1";
				// for Sending Current Date
				java.util.Date date = new java.util.Date();

				DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				gateGrnHeaderFromDate = df.format(date);
				gateGrnHeaderToDate = df.format(date);
				System.out.println("From Date And :" + gateGrnHeaderFromDate + "ToDATE" + gateGrnHeaderToDate);

				map = new LinkedMultiValueMap<String, Object>();

				map.add("statusList", statusList);
				map.add("fromDate", gateGrnHeaderFromDate);// ie current date
				map.add("toDate", gateGrnHeaderToDate);// ie current date
				map.add("isGrn", 1);

				grnGateHeaderList = new ArrayList<>();

				headerList = restTemplate.postForObject(Constants.url + "findGrnGvnHeaderOnLoad", map,
						GrnGvnHeaderList.class);

				grnGateHeaderList = headerList.getGrnGvnHeader();

				System.out.println("Grn Gate Header List ON load  " + grnGateHeaderList.toString());

			} // end of if onload call

			else {

				if (frList.contains("-1")) {
					isAllFrSelected = true;

				}

				if (isAllFrSelected) {
					// all Fr selected Web Service
					System.out.println("All Fr Selected =true");

					map = new LinkedMultiValueMap<String, Object>();

					map.add("fromDate", gateGrnHeaderFromDate);
					map.add("toDate", gateGrnHeaderToDate);//
					map.add("isGrn", 1);

					grnGateHeaderList = new ArrayList<>();

					headerList = restTemplate.postForObject(Constants.url + "getGrnGvnHeaderAllFr", map,
							GrnGvnHeaderList.class);

					grnGateHeaderList = headerList.getGrnGvnHeader();

					System.out.println("Grn Gate Header List  All FR" + grnGateHeaderList.toString());

				} else {

					System.out.println("Specific Fr Selected ");

					map = new LinkedMultiValueMap<String, Object>();

					map.add("frIdList", frSelectedGateHeader);
					map.add("fromDate", gateGrnHeaderFromDate);
					map.add("toDate", gateGrnHeaderToDate);
					map.add("isGrn", 1);

					grnGateHeaderList = new ArrayList<>();

					headerList = restTemplate.postForObject(Constants.url + "getGrnGvnHeader", map,
							GrnGvnHeaderList.class);

					grnGateHeaderList = headerList.getGrnGvnHeader();

					System.out.println("Grn Gate Header List  specific FR " + grnGateHeaderList.toString());
				}

			} // End of else

			model.addObject("fromDate", gateGrnHeaderFromDate);
			model.addObject("toDate", gateGrnHeaderToDate);
			model.addObject("grnList", grnGateHeaderList);
			model.addObject("selectedFr", frList);

		} catch (Exception e) {

			System.out.println("Excep in Gate Header List /getGrnHeaderForGate " + e.getMessage());
			e.printStackTrace();
		}

		return model;
	}

	// getGateGrnDetail

	@RequestMapping(value = "/getGateGrnDetail/{headerId}", method = RequestMethod.GET)
	public ModelAndView getGrnDetailList(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("headerId") int headerId) {
		ModelAndView modelAndView = null;

		modelAndView = new ModelAndView("grngvn/gateGrn");

		System.out.println("He ader " + headerId);

		globalGateHeaderId = headerId;

		RestTemplate restTemplate = new RestTemplate();

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		// getFrGrnDetail
		try {

			map.add("grnGvnHeaderId", headerId);
			GetGrnGvnDetailsList detailList = restTemplate.postForObject(Constants.url + "getFrGrnDetails", map,
					GetGrnGvnDetailsList.class);

			grnGateDetailList = new ArrayList<>();

			grnGateDetailList = detailList.getGrnGvnDetails();

			System.out.println("GRN Detail   " + grnGateDetailList.toString());

			map = new LinkedMultiValueMap<String, Object>();

			map.add("isFrUsed", 0);
			map.add("moduleId", 1);
			map.add("subModuleId", 3);
			getAllRemarksList = restTemplate.postForObject(Constants.url + "/getAllRemarks", map,
					GetAllRemarksList.class);

			getAllRemarks = new ArrayList<>();
			getAllRemarks = getAllRemarksList.getGetAllRemarks();
			
			
			
			GrnGvnHeader gateHeader = new GrnGvnHeader();

			for (int i = 0; i < grnGateHeaderList.size(); i++) {

				if (grnGateHeaderList.get(i).getGrnGvnHeaderId() == globalGateHeaderId) {

					gateHeader = grnGateHeaderList.get(i);

					break;

				}
			}
			modelAndView.addObject("srNo", gateHeader.getGrngvnSrno());


		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("Ex in grn Detail " + e.getMessage());
		}

		String grnDate = grnGateDetailList.get(0).getGrnGvnDate();
		modelAndView.addObject("grnList", grnGateDetailList);
		modelAndView.addObject("grnDate", grnDate);


		
		modelAndView.addObject("remarkList", getAllRemarks);

		return modelAndView;

	}

	// A]: Gate Grn Approve Specific Record
	@RequestMapping(value = "/insertGateGrnProcessAgree", method = RequestMethod.GET)
	public String insertGateGrnProcessAgree(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = new ModelAndView("grngvn/gateGrn");

		try {

			HttpSession session = request.getSession();
			UserResponse userResponse = (UserResponse) session.getAttribute("UserDetail");
			int gateApproveLogin = userResponse.getUser().getId();

			int grnId = Integer.parseInt(request.getParameter("grnId"));

			int gateGrnQty = Integer.parseInt(request.getParameter("gate_grn_qty"));

			System.out.println("Gate Grn QTY RECEIVED " + gateGrnQty);

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			List<TempGrnGvnBeanUp> dataList = new ArrayList<>();

			TempGrnGvnBeanUp data = new TempGrnGvnBeanUp();

			data.setApproveimedDateTimeGate(dateFormat.format(cal.getTime()));

			data.setApprovedLoginGate(gateApproveLogin);

			data.setGrnGvnStatus(Constants.AP_BY_GATE);

			data.setApprovedRemarkGate("Approve BY Dispatch");

			data.setGrnGvnId(grnId);

			data.setAprQtyGate(gateGrnQty);

			dataList.add(data);

			Info info = restTemplate.postForObject(Constants.url + "updateGateGrn", dataList, Info.class);

			System.out.println("after calling web service of gate grn agree info response as String - " + info);

			if (info.getError() == false) {

				for (int i = 0; i < grnGateDetailList.size(); i++) {

					if (grnGateDetailList.get(i).getGrnGvnId() == grnId) {

						grnGateDetailList.get(i).setGrnGvnStatus(Constants.AP_BY_GATE);
						System.out
								.println("Record Updated Locally  apr by gate " + grnGateDetailList.get(i).toString());
						break;

					}
				}

			}

			GrnGvnHeader gateHeader = new GrnGvnHeader();

			for (int i = 0; i < grnGateHeaderList.size(); i++) {

				if (grnGateHeaderList.get(i).getGrnGvnHeaderId() == globalGateHeaderId) {

					gateHeader = grnGateHeaderList.get(i);

					break;

				}
			}

			int rejStatus = 0, aprStatus = 0;

			for (int i = 0; i < grnGateDetailList.size(); i++) {

				// setting approve and rejected count
				if (grnGateDetailList.get(i).getGrnGvnStatus() == 2
						|| grnGateDetailList.get(i).getGrnGvnStatus() == 6) {

					aprStatus = aprStatus + 1;

				} else {

					rejStatus = rejStatus + 1;

				}

			}

			System.out.println("grnGateDetailList LIST SIZE : " + grnGateDetailList.size());

			System.out.println("aprStatus count = " + aprStatus + " reJStatus count = " + rejStatus);

			// all Rejected
			if (grnGateDetailList.size() == rejStatus) {

				System.out.println("list Size " + rejStatus);

				gateHeader.setGrngvnStatus(3);

				System.out.println("list size matched to rejCount then : status =3 ");
			}

			// partially approved
			if (grnGateDetailList.size() != aprStatus && grnGateDetailList.size() != rejStatus) {

				gateHeader.setGrngvnStatus(8);

				System.out.println(" some approved some dis approved header Status =8  	// partially approved");

			}

			// all Approved
			if (grnGateDetailList.size() == aprStatus) {

				gateHeader.setGrngvnStatus(2);

				System.out.println("header Status = 2 	// all Approved");

			}

			// Update Grn Gvn Header
			gateHeader.setApprovedDatetime(dateFormat.format(cal.getTime()));

			gateHeader = restTemplate.postForObject(Constants.url + "updateGrnGvnHeader", gateHeader,
					GrnGvnHeader.class);

			System.out.println("GRN HEADER Response after Update  " + gateHeader.toString());

		} catch (Exception e) {

			System.out.println("Error in insert Gate Grn Process Agree " + e.getMessage());
			e.printStackTrace();
		}

		return "redirect:/getGateGrnDetail";

	}

	// B]: Gate Grn DisApprove Specific Records

	@RequestMapping(value = "/insertGateGrnProcessDisAgree", method = RequestMethod.GET)
	public String insertGateGrnProcessDisAgree(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("grngvn/gateGrn");

		try {

			int grnId = Integer.parseInt(request.getParameter("grnId"));

			HttpSession session = request.getSession();
			UserResponse userResponse = (UserResponse) session.getAttribute("UserDetail");

			int gateApproveLogin = userResponse.getUser().getId();

			String gateRemark = request.getParameter("gateRemark");

			int gateGrnQty = Integer.parseInt(request.getParameter("gate_grn_qty"));

			System.out.println("Gate Grn QTY RECEIVED " + gateGrnQty);

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			List<TempGrnGvnBeanUp> dataList = new ArrayList<>();

			TempGrnGvnBeanUp data = new TempGrnGvnBeanUp();

			data.setApproveimedDateTimeGate(dateFormat.format(cal.getTime()));

			data.setApprovedLoginGate(gateApproveLogin);

			data.setGrnGvnStatus(Constants.DIS_BY_GATE);

			data.setApprovedRemarkGate(gateRemark);

			data.setGrnGvnId(grnId);

			data.setAprQtyGate(gateGrnQty);

			dataList.add(data);

			Info updateGateGrn = restTemplate.postForObject(Constants.url + "updateGateGrn", dataList, Info.class);

			System.out.println("after calling web service of disagree" + updateGateGrn.toString());

			if (updateGateGrn.getError() == false) {

				for (int i = 0; i < grnGateDetailList.size(); i++) {

					if (grnGateDetailList.get(i).getGrnGvnId() == grnId) {

						grnGateDetailList.get(i).setGrnGvnStatus(Constants.DIS_BY_GATE);
						System.out.println("Record Updated Locally Gate Grn  disapp "
								+ grnGateDetailList.get(i).getItemName() + "disApproved");
						break;

					}
				}

			}

			GrnGvnHeader gateHeader = new GrnGvnHeader();

			for (int i = 0; i < grnGateHeaderList.size(); i++) {

				if (grnGateHeaderList.get(i).getGrnGvnHeaderId() == globalGateHeaderId) {

					gateHeader = grnGateHeaderList.get(i);

					System.out.println("Gate Header  " + gateHeader.toString());

					break;

				}
			}

			int rejStatus = 0, aprStatus = 0;

			System.out.println("LIST SIZE : " + grnGateDetailList.size());

			for (int i = 0; i < grnGateDetailList.size(); i++) {

				if (grnGateDetailList.get(i).getGrnGvnStatus() == 2
						|| grnGateDetailList.get(i).getGrnGvnStatus() == 6) {
					aprStatus = aprStatus + 1;
				} else {

					rejStatus = rejStatus + 1;
				}

			}
			System.out.println(" grnGateDetailList LIST SIZE : " + grnGateDetailList.size());

			System.out.println("aprStatus count = " + aprStatus + " reJStatus count = " + rejStatus);

			if (grnGateDetailList.size() == rejStatus) {

				gateHeader.setGrngvnStatus(3);
				System.out.println("GRN Status =3" + gateHeader.getGrngvnStatus());

			}

			// partially approved
			if (grnGateDetailList.size() != aprStatus && grnGateDetailList.size() != rejStatus) {

				gateHeader.setGrngvnStatus(8);
				System.out.println("GRN Status =8" + gateHeader.getGrngvnStatus());

			}

			// all Approved
			if (grnGateDetailList.size() == aprStatus) {

				gateHeader.setGrngvnStatus(2);

				System.out.println("GRN Status =2" + gateHeader.getGrngvnStatus());

			}

			// update Grn GVN Header

			gateHeader.setApprovedDatetime(dateFormat.format(cal.getTime()));

			gateHeader = restTemplate.postForObject(Constants.url + "updateGrnGvnHeader", gateHeader,
					GrnGvnHeader.class);

			System.out.println("GRN HEADER response  " + gateHeader.toString());

		} catch (Exception e) {

			System.out.println("Error in updating grn details " + e.getMessage());

			e.printStackTrace();
		}
		System.out.println("INSERT GATE GRN DISAPPROVE : STATUS =3");

		return "redirect:/getGateGrnDetail";

	}

	// C]: Gate Grn Approve Multiple Records using CheckBox

	@RequestMapping(value = "/insertGateGrnByCheckBoxes", method = RequestMethod.POST) // Using checkboxes to insert
	public String insertGateGrnByCheckBoxes(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in checkboxes ");

		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();

			HttpSession session = request.getSession();
			UserResponse userResponse = (UserResponse) session.getAttribute("UserDetail");

			int gateApproveLogin = userResponse.getUser().getId();

			String[] grnIdList = request.getParameterValues("select_to_agree");

			System.out.println("first GRN ID " + grnIdList[0]);

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			String gIds = new String();

			List<TempGrnGvnBeanUp> dataList = new ArrayList<>();

			for (int i = 0; i < grnIdList.length; i++) {

				int gateGrnQty = Integer.parseInt(request.getParameter("gate_grn_qty" + grnIdList[i]));
				System.out.println("corresponding gID " + grnIdList[i] + "Gate Qty " + gateGrnQty);

				TempGrnGvnBeanUp data = new TempGrnGvnBeanUp();

				data.setApproveimedDateTimeGate(dateFormat.format(cal.getTime()));

				data.setApprovedLoginGate(gateApproveLogin);

				data.setGrnGvnStatus(Constants.AP_BY_GATE);

				data.setApprovedRemarkGate("Approve By Dispatch");

				data.setGrnGvnId(Integer.parseInt(grnIdList[i]));

				data.setAprQtyGate(gateGrnQty);

				dataList.add(data);

				gIds = gIds + "," + grnIdList[i];

			}
			gIds = gIds.substring(1);

			Info updateGateGrn = restTemplate.postForObject(Constants.url + "updateGateGrn", dataList, Info.class);

			if (updateGateGrn.getError() == false) {

				System.out.println("Inside For Loop/updateGateGrn.getError() == false ");

				for (int i = 0; i < grnIdList.length; i++) {

					for (int j = 0; j < grnGateDetailList.size(); j++) {

						if (Integer.parseInt(grnIdList[i]) == grnGateDetailList.get(j).getGrnGvnId()) {

							grnGateDetailList.get(j).setGrnGvnStatus(Constants.AP_BY_GATE);

						} else {

							System.out.println("No MAtch Found ");
						}
					}
				}
			}

			System.out.println("update Grn Using checkBoxes response " + updateGateGrn.toString());

			// update Header :set data and web service call

			GrnGvnHeader gateHeader = new GrnGvnHeader();

			System.out.println("globalGateHeaderId " + globalGateHeaderId);

			for (int i = 0; i < grnGateHeaderList.size(); i++) {

				if (grnGateHeaderList.get(i).getGrnGvnHeaderId() == globalGateHeaderId) {

					gateHeader = grnGateHeaderList.get(i);

					System.out.println("Gate Header Received " + gateHeader.toString());

					break;

				}
			}

			int rejStatus = 0, aprStatus = 0;

			for (int i = 0; i < grnGateDetailList.size(); i++) {

				if (grnGateDetailList.get(i).getGrnGvnStatus() == 2
						|| grnGateDetailList.get(i).getGrnGvnStatus() == 6) {

					aprStatus = aprStatus + 1;

				} else {

					rejStatus = rejStatus + 1;

				}

			} // End of For Loop

			System.out.println("grnGateDetailList LIST SIZE : " + grnGateDetailList.size());

			System.out.println("aprStatus count = " + aprStatus + " reJStatus count = " + rejStatus);

			if (grnGateDetailList.size() == rejStatus) {

				gateHeader.setGrngvnStatus(3);
				System.out.println("header Status =3");
			}

			// partially approved
			if (grnGateDetailList.size() != aprStatus && grnGateDetailList.size() != rejStatus) {

				gateHeader.setGrngvnStatus(8);
				System.out.println("Header Status =8 ");
			}

			// all Approved
			if (grnGateDetailList.size() == aprStatus) {

				gateHeader.setGrngvnStatus(2);
				System.out.println("Header Status = 2");
			}

			// update Grn Gvn Header
			gateHeader.setApprovedDatetime(dateFormat.format(cal.getTime()));

			gateHeader = restTemplate.postForObject(Constants.url + "updateGrnGvnHeader", gateHeader,
					GrnGvnHeader.class);

			System.out.println("GRN HEADER Response : " + gateHeader.toString());

		} catch (Exception e) {

			System.out.println("Gate Grn Checkbox Error :" + e.getMessage());
			e.printStackTrace();

		}

		return "redirect:/getGateGrnDetail/" + globalGateHeaderId;
	}
	// Acc Grn Started

	@RequestMapping(value = "/getDateForAccHeader", method = RequestMethod.GET)
	public String getDateForAccHeader(HttpServletRequest request, HttpServletResponse response) {

		accGrnHeaderFromDate = request.getParameter("fromDate");
		accGrnHeaderToDate = request.getParameter("toDate");

		String selectedFr = request.getParameter("fr_id_list");

		System.out.println(
				"From Date " + accGrnHeaderFromDate + "toDate " + accGrnHeaderToDate + "fr ID List " + selectedFr);
		selectedFr = selectedFr.substring(1, selectedFr.length() - 1);
		selectedFr = selectedFr.replaceAll("\"", "");

		frSelectedAccHeader = selectedFr;

		frList = Arrays.asList(selectedFr);

		System.out.println("Fr List Final " + frList);

		return "";

	}

	@RequestMapping(value = "/getGrnHeaderForAcc", method = RequestMethod.GET)
	public ModelAndView getGrnHeaderForAcc(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("grngvn/accGrnHeader");

		boolean isAllFrSelected = false;

		try {

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			allFrIdNameList = new AllFrIdNameList();
			try {

				allFrIdNameList = restTemplate.getForObject(Constants.url + "getAllFrIdName", AllFrIdNameList.class);

			} catch (Exception e) {
				System.out.println("Exception in getAllFrIdName" + e.getMessage());
				e.printStackTrace();

			}
			model.addObject("unSelectedFrList", allFrIdNameList.getFrIdNamesList());

			if (accGrnHeaderFromDate == "" || accGrnHeaderFromDate == null) {

				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();

				String curTimeStamp = dateFormat.format(cal.getTime());
				System.out.println("Cur time Stamp= " + curTimeStamp);

				cal.set(Calendar.HOUR, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				String initialTimeStamp = dateFormat.format(cal.getTime());
				System.out.println("initialTime time Stamp= " + initialTimeStamp);

				String statusList = new String();

				statusList = "2" + "," + "8";
				// for Sending Current Date
				java.util.Date date = new java.util.Date();

				DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				accGrnHeaderFromDate = df.format(date);
				accGrnHeaderToDate = df.format(date);
				System.out.println("From Date And :" + accGrnHeaderFromDate + "ToDATE" + accGrnHeaderToDate);

				map = new LinkedMultiValueMap<String, Object>();

				// map.add("initTime", initialTimeStamp);
				// map.add("curTime", curTimeStamp);
				map.add("statusList", statusList);
				map.add("fromDate", accGrnHeaderFromDate);// ie current date
				map.add("toDate", accGrnHeaderToDate);// ie current date
				map.add("isGrn", 1);

				grnAccHeaderList = new ArrayList<>();

				headerList = restTemplate.postForObject(Constants.url + "findGrnGvnHeaderOnLoad", map,
						GrnGvnHeaderList.class);

				grnAccHeaderList = headerList.getGrnGvnHeader();

				System.out.println("Grn Acc Header List ON load  " + grnAccHeaderList.toString());

			} // end of if onload call

			else {

				if (frList.contains("-1")) {
					/*
					 * isAllFrSelected = true;
					 * 
					 * }
					 * 
					 * if (isAllFrSelected) {
					 */
					// all Fr selected Web Service
					System.out.println("All Fr Selected =true");

					map = new LinkedMultiValueMap<String, Object>();

					map.add("fromDate", accGrnHeaderFromDate);// ie current date
					map.add("toDate", accGrnHeaderToDate);// ie current date
					map.add("isGrn", 1);

					grnAccHeaderList = new ArrayList<>();

					headerList = restTemplate.postForObject(Constants.url + "getGrnGvnHeaderAllFr", map,
							GrnGvnHeaderList.class);

					grnAccHeaderList = headerList.getGrnGvnHeader();

					System.out.println("Grn Acc Header List  All FR" + grnAccHeaderList.toString());

				} else {

					System.out.println("Specific Fr Selected ");

					map = new LinkedMultiValueMap<String, Object>();

					map.add("frIdList", frSelectedAccHeader);
					map.add("fromDate", accGrnHeaderFromDate);// ie current date
					map.add("toDate", accGrnHeaderToDate);// ie current date
					map.add("isGrn", 1);

					grnAccHeaderList = new ArrayList<>();

					headerList = restTemplate.postForObject(Constants.url + "getGrnGvnHeader", map,
							GrnGvnHeaderList.class);

					grnAccHeaderList = headerList.getGrnGvnHeader();

					System.out.println("Grn Acc Header List  specific FR " + grnAccHeaderList.toString());
				}

			} // End of else

			model.addObject("fromDate", accGrnHeaderFromDate);
			model.addObject("toDate", accGrnHeaderToDate);
			model.addObject("grnList", grnAccHeaderList);
			model.addObject("selectedFr", frList);

		} catch (Exception e) {

			System.out.println("Excep in Acc GrN Header List /getGrnHeaderForAcc " + e.getMessage());
			e.printStackTrace();
		}

		return model;
	}
	
	
	List<Integer> statuses;

	@RequestMapping(value = "/getAccGrnDetail/{headerId}", method = RequestMethod.GET)
	public ModelAndView getAccGrnDetail(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("headerId") int headerId) {
		ModelAndView modelAndView = null;

		modelAndView = new ModelAndView("grngvn/accGrn");

		System.out.println("He ader " + headerId);

		globalAccHeaderId = headerId;

		RestTemplate restTemplate = new RestTemplate();

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		map.add("grnGvnHeaderId", headerId);
		// getFrGrnDetail
		try {

			GetGrnGvnDetailsList detailList = restTemplate.postForObject(Constants.url + "getFrGrnDetails", map,
					GetGrnGvnDetailsList.class);

			grnAccDetailList = new ArrayList<>();

			grnAccDetailList = detailList.getGrnGvnDetails();

			System.out.println("GRN Detail   " + grnAccDetailList.toString());
			statuses=new ArrayList<Integer>();
			for(int i=0;i<grnAccDetailList.size();i++) {
				
				System.err.println("In For ");
				if(grnAccDetailList.get(i).getGrnGvnStatus()==7 || grnAccDetailList.get(i).getGrnGvnStatus()==2) {
					System.err.println("In If ");

				statuses.add(i);
				}
			}

			map = new LinkedMultiValueMap<String, Object>();
			map.add("isFrUsed", 0);
			map.add("moduleId", 1);
			map.add("subModuleId", 6);
			getAllRemarksList = restTemplate.postForObject(Constants.url + "/getAllRemarks", map,
					GetAllRemarksList.class);

			getAllRemarks = new ArrayList<>();
			getAllRemarks = getAllRemarksList.getGetAllRemarks();
			
			
			GrnGvnHeader gateHeader = new GrnGvnHeader();

			for (int i = 0; i < grnAccHeaderList.size(); i++) {

				if (grnAccHeaderList.get(i).getGrnGvnHeaderId() == globalAccHeaderId) {

					gateHeader = grnAccHeaderList.get(i);

					break;

				}
			}
			modelAndView.addObject("srNo", gateHeader.getGrngvnSrno());


		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("Ex in grn Detasil " + e.getMessage());

		}

		GetGrnGvnDetails detail;

		for (int i = 0; i < grnAccDetailList.size(); i++) {

			detail = new GetGrnGvnDetails();
			detail = grnAccDetailList.get(i);
			int qty = 0;

			if (detail.getGrnGvnStatus() == 1) {
				System.out.println("a] fr Grn Qty ");
				qty = detail.getAprQtyGate();

			}

			if (detail.getGrnGvnStatus() == 2 || detail.getGrnGvnStatus() == 3) {
				System.out.println("b] Gate Grn Qty ");

				qty = detail.getAprQtyGate();

			}

			if (detail.getGrnGvnStatus() == 6 || detail.getGrnGvnStatus() == 7) {
				System.out.println("c] Acc Grn Qty ");
				qty = detail.getAprQtyAcc();

			}

			float grnRate = 0;

			float total = detail.getBaseRate() * qty;

			float aprAmt = 0;
			float aprTaxableAmt, aprTotalTax;
			float sgstRs, cgstRs, igstRs;

			float grandTotal;
			if (detail.getGrnType() == 0) {

				grnRate = detail.getBaseRate() * 75 / 100;
			}

			if (detail.getGrnType() == 1) {

				grnRate = detail.getBaseRate() * 90 / 100;
			}

			if (detail.getGrnType() == 2 || detail.getGrnType() == 4) {

				grnRate = detail.getBaseRate();

			}

			aprTaxableAmt = grnRate * qty;

			System.out.println("apr Taxable Amt = " + aprTaxableAmt);

			System.out.println("Grn RAte " + grnRate);
			aprTotalTax = ((aprTaxableAmt) * (detail.getSgstPer() + detail.getCgstPer())) / 100;
			System.out.println("Apr Total Tax " + aprTotalTax);

			if (detail.getIsSameState() == 1) {

				sgstRs = aprTotalTax / 2;
				cgstRs = aprTotalTax / 2;

				igstRs = 0.0f;
			} else {

				igstRs = aprTotalTax;

				sgstRs = 0.0f;
				cgstRs = 0.0f;
			}

			System.out.println("sgstsRs" + sgstRs + "CGSt Rs " + cgstRs);

			grandTotal = aprTaxableAmt + aprTotalTax;
			detail.setGrnGvnAmt(roundUp(grandTotal));
		} // end of for loop

		String grnDate = grnAccDetailList.get(0).getGrnGvnDate();

		modelAndView.addObject("grnList", grnAccDetailList);
		modelAndView.addObject("grnDate", grnDate);
		modelAndView.addObject("remarkList", getAllRemarks);

		return modelAndView;
	}

	
	
	
	
	@RequestMapping(value = "/getStatus", method = RequestMethod.GET)
	public @ResponseBody List<Integer> getStatus(HttpServletRequest request, HttpServletResponse response) {

		return statuses;
		
	}
	
	
	// Acc GRN started

	// A] --//Acc Grn Process Agree
	@RequestMapping(value = "/insertAccGrnProcessAgree", method = RequestMethod.GET)
	public String insertAccGrnProcessAgree(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("grngvn/accGrn");

		try {

			int grnId = Integer.parseInt(request.getParameter("grnId"));

			int accGrnQty = Integer.parseInt(request.getParameter("acc_grn_qty"));

			System.out.println("Acc GRN QTY " + accGrnQty + "for GRN ID =" + grnId);
			HttpSession session = request.getSession();
			UserResponse userResponse = (UserResponse) session.getAttribute("UserDetail");
			int accApproveLogin = userResponse.getUser().getId();

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			System.out.println("************* Date Time " + dateFormat.format(cal.getTime()));

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			List<TempGrnGvnBeanUp> dataList = new ArrayList<>();

			TempGrnGvnBeanUp data = new TempGrnGvnBeanUp();

			GetGrnGvnDetails detail = new GetGrnGvnDetails();

			for (int i = 0; i < grnAccDetailList.size(); i++) {

				if (grnAccDetailList.get(i).getGrnGvnId() == grnId) {

					System.out.println("GRN ID MAtched " + grnId);

					detail = grnAccDetailList.get(i);

					float grnRate = 0;

					float total = detail.getBaseRate() * accGrnQty;

					float aprAmt = 0;
					float aprTaxableAmt, aprTotalTax;
					float sgstRs, cgstRs, igstRs;

					float grandTotal;
					if (detail.getGrnType() == 0) {

						grnRate = detail.getBaseRate() * 75 / 100;
					}

					if (detail.getGrnType() == 1) {

						grnRate = detail.getBaseRate() * 90 / 100;
					}

					if (detail.getGrnType() == 2 || detail.getGrnType() == 4) {

						grnRate = detail.getBaseRate();

					}

					aprTaxableAmt = grnRate * accGrnQty;

					System.out.println("apr Taxable Amt = " + aprTaxableAmt);

					System.out.println("Grn RAte " + grnRate);
					aprTotalTax = ((aprTaxableAmt) * (detail.getSgstPer() + detail.getCgstPer())) / 100;
					System.out.println("Apr Total Tax " + aprTotalTax);

					if (detail.getIsSameState() == 1) {

						sgstRs = aprTotalTax / 2;
						cgstRs = aprTotalTax / 2;

						igstRs = 0.0f;
					} else {

						igstRs = aprTotalTax;

						sgstRs = 0.0f;
						cgstRs = 0.0f;
					}

					System.out.println("sgstsRs" + sgstRs + "CGSt Rs " + cgstRs);

					grandTotal = aprTaxableAmt + aprTotalTax;

					System.out.println("GRAND total " + grandTotal);

					data = new TempGrnGvnBeanUp();

					data.setApprovedDateTimeAcc(dateFormat.format(cal.getTime()));

					data.setApprovedLoginAcc(accApproveLogin);

					data.setGrnGvnStatus(Constants.AP_BY_ACC);

					data.setApprovedRemarkAcc("Approved BY Acc");

					data.setGrnGvnId(grnId);

					data.setAprQtyAcc(accGrnQty);

					data.setAprCgstRs(roundUp(cgstRs));
					data.setAprIgstRs(roundUp(igstRs));
					data.setAprSgstRs(roundUp(sgstRs));

					data.setAprTaxableAmt(roundUp(aprTaxableAmt));
					data.setAprTotalTax(roundUp(aprTotalTax));
					data.setGrnGvnId(grnId);

					data.setAprGrandTotal(roundUp(grandTotal));

					dataList.add(data);
					
					
					detail.setAprGrandTotal(data.getAprGrandTotal());
					detail.setAprTaxableAmt(data.getAprTaxableAmt());
					detail.setAprTotalTax(data.getAprTotalTax());
					
					detail.setAprCgstRs(data.getAprCgstRs());
					detail.setAprSgstRs(data.getAprSgstRs());
					detail.setAprIgstRs(data.getAprIgstRs());
					
					detail.setAprROff(data.getAprROff());
					
					grnAccDetailList.set(i, detail);


					break;

				} // end of matching grnId
			} // end of for Loop

			Info info = restTemplate.postForObject(Constants.url + "updateAccGrn", dataList, Info.class);

			System.out.println("Response UPDATE " + info.toString());
			// new code

			if (info.getError() == false) {

				for (int i = 0; i < grnAccDetailList.size(); i++) {

					if (grnAccDetailList.get(i).getGrnGvnId() == grnId) {

						grnAccDetailList.get(i).setGrnGvnStatus(Constants.AP_BY_ACC);
						System.out.println("Record Updated Locally  apr");
						break;

					}
				}

			}

			GrnGvnHeader accHeader = new GrnGvnHeader();

			for (int i = 0; i < grnAccHeaderList.size(); i++) {

				if (grnAccHeaderList.get(i).getGrnGvnHeaderId() == globalAccHeaderId) {

					accHeader = grnAccHeaderList.get(i);

					break;
				}
			}

			if (accHeader.getGrngvnStatus() == 2 || accHeader.getGrngvnStatus() == 6
					|| accHeader.getGrngvnStatus() == 7) {

				int rejStatus = 0, aprStatus = 0;

				for (int i = 0; i < grnAccDetailList.size(); i++) {

					// setting approve and rejected count
					if (grnAccDetailList.get(i).getGrnGvnStatus() == 6) {

						aprStatus = aprStatus + 1;

					} else if (grnAccDetailList.get(i).getGrnGvnStatus() == 7) {

						rejStatus = rejStatus + 1;
					}

				}

				// all Rejected
				if (grnAccDetailList.size() == rejStatus) {

					System.out.println("rejStatus count " + rejStatus);

					accHeader.setGrngvnStatus(7);

					System.out.println("Heade status =7 ");
				}

				else if (grnAccDetailList.size() == aprStatus) {

					accHeader.setGrngvnStatus(6);

					System.out.println("header Status = 6");

				}

				// partially approved
				else {

					accHeader.setGrngvnStatus(8);

					System.out.println("header Status =8 ");

				}

				// all Approved

			} // If grnStatus =2

			// if Status=3||7
			else if (accHeader.getGrngvnStatus() == 8) {

				int rejStatus = 0, aprStatus = 0;

				for (int i = 0; i < grnAccDetailList.size(); i++) {

					// setting approve and rejected count
					if (grnAccDetailList.get(i).getGrnGvnStatus() == 3
							|| grnAccDetailList.get(i).getGrnGvnStatus() == 7) {

						rejStatus = rejStatus + 1;

					} else if (grnAccDetailList.get(i).getGrnGvnStatus() == 6) {

						aprStatus = aprStatus + 1;
					}

				} // enf of for

				if (rejStatus == grnAccDetailList.size()) {

					accHeader.setGrngvnStatus(7);
				} else if (aprStatus == grnAccDetailList.size()) {

					accHeader.setGrngvnStatus(6);

				} else {

					accHeader.setGrngvnStatus(8);

				}

			}

			// Update Grn Gvn Header

			accHeader.setAprTaxableAmt(0);
			accHeader.setAprTotalTax(0);
			accHeader.setAprGrandTotal(0);
			accHeader.setAprCgstRs(0);
			accHeader.setAprSgstRs(0);
			accHeader.setAprIgstRs(0);
			accHeader.setAprROff(0);
			accHeader.setApporvedAmt(0);

			for (int i = 0; i < grnAccDetailList.size(); i++) {

				if (grnAccDetailList.get(i).getGrnGvnStatus() == 6) {

					System.out.println("Status 6 found ");

					accHeader.setApporvedAmt(accHeader.getAprGrandTotal() + grnAccDetailList.get(i).getAprGrandTotal());

					accHeader.setAprTaxableAmt(
							accHeader.getAprTaxableAmt() + grnAccDetailList.get(i).getAprTaxableAmt());
					accHeader.setAprTotalTax(accHeader.getAprTotalTax() + grnAccDetailList.get(i).getAprTotalTax());
					accHeader.setAprGrandTotal(
							accHeader.getAprGrandTotal() + grnAccDetailList.get(i).getAprGrandTotal());
					accHeader.setAprCgstRs(accHeader.getAprCgstRs() + grnAccDetailList.get(i).getAprCgstRs());
					accHeader.setAprSgstRs(accHeader.getAprSgstRs() + grnAccDetailList.get(i).getAprSgstRs());
					accHeader.setAprIgstRs(accHeader.getAprIgstRs() + grnAccDetailList.get(i).getAprIgstRs());
					accHeader.setAprROff(accHeader.getAprROff() + grnAccDetailList.get(i).getAprROff());

					// accHeader.setApporvedAmt(0);

				}
			}

			System.out.println("acc Header total amt " + accHeader.getApporvedAmt());
			accHeader.setApprovedDatetime(dateFormat.format(cal.getTime()));

			System.out.println("LIST SIZE ACC GRN : " + grnAccDetailList.size());

			accHeader = restTemplate.postForObject(Constants.url + "updateGrnGvnHeader", accHeader, GrnGvnHeader.class);

			System.out.println("GRN HEADER rESPONSE " + accHeader.toString());

			//
			
			//-----------------------For Notification-----------------
			String frToken="";
		
			try {
				map = new LinkedMultiValueMap<String, Object>();
				  map.add("frId",accHeader.getFrId());
				   
                 frToken= restTemplate.postForObject(Constants.url+"getFrToken", map, String.class);
		         Firebase.sendPushNotifForCommunication(frToken,"","GRN no"+accHeader.getGrngvnSrno()+"punched by you has been cleared by Accounts"+accHeader.getAprGrandTotal() + " Thank You..Team Monginis","inbox");
		   	
		        }
		        catch(Exception e2)
		        {
			      e2.printStackTrace();
		        }
			
			//-----------------------------------------------------

		} catch (Exception e) {

			System.out.println("Error in update Acc Grn Agree " + e.getMessage());

			e.printStackTrace();

		}

		return "redirect:/getAccGrnDetail";

	}

	// B] --//insertAccGrnProcessDisAgree

	@RequestMapping(value = "/insertAccGrnProcessDisAgree", method = RequestMethod.GET)
	public String insertAccGrnProcessDisAgree(HttpServletRequest request, HttpServletResponse response) {

		System.out.println("using a href to call insert account dis agree ");

		ModelAndView model = new ModelAndView("grngvn/accGrn");

		try {
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			int grnId = Integer.parseInt(request.getParameter("grnId"));

			int accGrnQty = Integer.parseInt(request.getParameter("acc_grn_qty"));
			System.out.println("Acc GRN QTY " + accGrnQty + "for GRN ID =" + grnId);

			String accRemark = request.getParameter("accRemark");

			// int accApproveLogin = //
			Integer.parseInt(request.getParameter("approveAccLogin"));
			HttpSession session = request.getSession();
			UserResponse userResponse = (UserResponse) session.getAttribute("UserDetail");
			int accApproveLogin = userResponse.getUser().getId();

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			System.out.println("************* Date Time " + dateFormat.format(cal.getTime()));

			RestTemplate restTemplate = new RestTemplate();

			List<TempGrnGvnBeanUp> dataList = new ArrayList<>();

			TempGrnGvnBeanUp data = new TempGrnGvnBeanUp();

			GetGrnGvnDetails detail = new GetGrnGvnDetails();
			for (int i = 0; i < grnAccDetailList.size(); i++) {

				if (grnAccDetailList.get(i).getGrnGvnId() == grnId) {

					System.out.println("GRN ID MAtched " + grnId);

					detail = grnAccDetailList.get(i);

					float grnRate = 0;

					float total = detail.getBaseRate() * accGrnQty;

					float aprAmt = 0;
					float aprTaxableAmt, aprTotalTax, aprGrandTotal;
					float sgstRs, cgstRs, igstRs;

					float grandTotal;
					if (detail.getGrnType() == 0) {

						grnRate = detail.getBaseRate() * 75 / 100;
					}

					if (detail.getGrnType() == 1) {

						grnRate = detail.getBaseRate() * 90 / 100;
					}

					if (detail.getGrnType() == 2 || detail.getGrnType() == 4) {

						grnRate = detail.getBaseRate();

					}

					aprTaxableAmt = grnRate * accGrnQty;

					System.out.println("apr Taxable Amt = " + aprTaxableAmt);

					System.out.println("Grn RAte " + grnRate);
					aprTotalTax = ((aprTaxableAmt) * (detail.getSgstPer() + detail.getCgstPer())) / 100;
					System.out.println("Apr Total Tax " + aprTotalTax);

					if (detail.getIsSameState() == 1) {

						sgstRs = aprTotalTax / 2;
						cgstRs = aprTotalTax / 2;

						igstRs = 0.0f;
					} else {

						igstRs = aprTotalTax;

						sgstRs = 0.0f;
						cgstRs = 0.0f;
					}

					System.out.println("sgstsRs" + sgstRs + "CGSt Rs " + cgstRs);

					grandTotal = aprTaxableAmt + aprTotalTax;

					System.out.println("GRAND total " + grandTotal);

					data = new TempGrnGvnBeanUp();

					data.setApprovedDateTimeAcc(dateFormat.format(cal.getTime()));

					data.setApprovedLoginAcc(accApproveLogin);

					data.setGrnGvnStatus(Constants.DIS_BY_ACC);

					data.setApprovedRemarkAcc(accRemark);

					data.setGrnGvnId(grnId);

					data.setAprQtyAcc(accGrnQty);

					data.setAprCgstRs(roundUp(cgstRs));
					data.setAprIgstRs(roundUp(igstRs));
					data.setAprSgstRs(roundUp(sgstRs));

					data.setAprTaxableAmt(roundUp(aprTaxableAmt));
					data.setAprTotalTax(roundUp(aprTotalTax));
					data.setGrnGvnId(grnId);

					data.setAprGrandTotal(roundUp(grandTotal));

					dataList.add(data);
					
					
					detail.setAprGrandTotal(data.getAprGrandTotal());
					detail.setAprTaxableAmt(data.getAprTaxableAmt());
					detail.setAprTotalTax(data.getAprTotalTax());
					
					detail.setAprCgstRs(data.getAprCgstRs());
					detail.setAprSgstRs(data.getAprSgstRs());
					detail.setAprIgstRs(data.getAprIgstRs());
					
					detail.setAprROff(data.getAprROff());
					
					grnAccDetailList.set(i, detail);


					break;

				} // end of matching grnId
			} // end of for Loop

			Info info = restTemplate.postForObject(Constants.url + "updateAccGrn", dataList, Info.class);

			// new code

			if (info.getError() == false) {

				for (int i = 0; i < grnAccDetailList.size(); i++) {

					if (grnId == grnAccDetailList.get(i).getGrnGvnId()) {

						grnAccDetailList.get(i).setGrnGvnStatus(Constants.DIS_BY_ACC);
						System.out.println("Record Updated Locally  apr");
						break;

					}
				}

			}

			GrnGvnHeader accHeader = new GrnGvnHeader();

			for (int i = 0; i < grnAccHeaderList.size(); i++) {

				if (grnAccHeaderList.get(i).getGrnGvnHeaderId() == globalAccHeaderId) {

					accHeader = grnAccHeaderList.get(i);

					break;

				}
			}

			if (accHeader.getGrngvnStatus() == 2 || accHeader.getGrngvnStatus() == 6
					|| accHeader.getGrngvnStatus() == 7) {

				int rejStatus = 0, aprStatus = 0;

				for (int i = 0; i < grnAccDetailList.size(); i++) {

					// setting approve and rejected count
					if (grnAccDetailList.get(i).getGrnGvnStatus() == 6) {

						aprStatus = aprStatus + 1;

					} else if (grnAccDetailList.get(i).getGrnGvnStatus() == 7) {

						rejStatus = rejStatus + 1;
					}

				}

				// all Rejected
				if (grnAccDetailList.size() == rejStatus) {

					System.out.println("rejStatus count " + rejStatus);

					accHeader.setGrngvnStatus(7);

					System.out.println("Heade status =7 ");
				}

				else if (grnAccDetailList.size() == aprStatus) {

					accHeader.setGrngvnStatus(6);

					System.out.println("header Status = 6");

				}

				// partially approved
				else {

					accHeader.setGrngvnStatus(8);

					System.out.println("header Status =8 ");

				}

				// all Approved

			} // If grnStatus =2

			// if Status=3||7
			else if (accHeader.getGrngvnStatus() == 8) {
				int rejStatus = 0, aprStatus = 0;

				for (int i = 0; i < grnAccDetailList.size(); i++) {

					// setting approve and rejected count
					if (grnAccDetailList.get(i).getGrnGvnStatus() == 3
							|| grnAccDetailList.get(i).getGrnGvnStatus() == 7) {

						rejStatus = rejStatus + 1;

					} else if (grnAccDetailList.get(i).getGrnGvnStatus() == 6) {

						aprStatus = aprStatus + 1;
					}

				} // enf of for

				if (rejStatus == grnAccDetailList.size()) {

					accHeader.setGrngvnStatus(7);
				} else if (aprStatus == grnAccDetailList.size()) {

					accHeader.setGrngvnStatus(6);

				} else {

					accHeader.setGrngvnStatus(8);

				}

			}

			accHeader.setAprTaxableAmt(0);
			accHeader.setAprTotalTax(0);
			accHeader.setAprGrandTotal(0);
			accHeader.setAprCgstRs(0);
			accHeader.setAprSgstRs(0);
			accHeader.setAprIgstRs(0);
			accHeader.setAprROff(0);
			accHeader.setApporvedAmt(0);

			for (int i = 0; i < grnAccDetailList.size(); i++) {

				if (grnAccDetailList.get(i).getGrnGvnStatus() == 6) {

					accHeader.setApporvedAmt(accHeader.getAprGrandTotal() + grnAccDetailList.get(i).getAprGrandTotal());

					accHeader.setAprTaxableAmt(
							accHeader.getAprTaxableAmt() + grnAccDetailList.get(i).getAprTaxableAmt());
					accHeader.setAprTotalTax(accHeader.getAprTotalTax() + grnAccDetailList.get(i).getAprTotalTax());
					accHeader.setAprGrandTotal(
							accHeader.getAprGrandTotal() + grnAccDetailList.get(i).getAprGrandTotal());
					accHeader.setAprCgstRs(accHeader.getAprCgstRs() + grnAccDetailList.get(i).getAprCgstRs());
					accHeader.setAprSgstRs(accHeader.getAprSgstRs() + grnAccDetailList.get(i).getAprSgstRs());
					accHeader.setAprIgstRs(accHeader.getAprIgstRs() + grnAccDetailList.get(i).getAprIgstRs());
					accHeader.setAprROff(accHeader.getAprROff() + grnAccDetailList.get(i).getAprROff());

					// accHeader.setApporvedAmt(0);

				}
			}

			// Update Grn Gvn Header
			accHeader.setApprovedDatetime(dateFormat.format(cal.getTime()));

			accHeader = restTemplate.postForObject(Constants.url + "updateGrnGvnHeader", accHeader, GrnGvnHeader.class);

			System.out.println("GRN HEADER rESPONSE " + accHeader.toString());

			System.out.println("LIST SIZE ACC GRN : " + grnAccDetailList.size());
			
			//
			
			
			//-----------------------For Notification-----------------
			String frToken="";
		
			try {
				map = new LinkedMultiValueMap<String, Object>();
				  map.add("frId",accHeader.getFrId());
				   
                 frToken= restTemplate.postForObject(Constants.url+"getFrToken", map, String.class);
		         Firebase.sendPushNotifForCommunication(frToken,"","GRN no"+accHeader.getGrngvnSrno()+"punched by you has been rejected by Accounts" + " Thank You..Team Monginis","inbox");
		   	
		        }
		        catch(Exception e2)
		        {
			      e2.printStackTrace();
		        }
			
			//-----------------------------------------------------

		} catch (Exception e) {

			System.out.println("Error in update Acc Grn Agree " + e.getMessage());

			e.printStackTrace();

		}

		return "redirect:/getAccGrnDetail";

	}

	// C] -- //insertAccGrnByCheckBoxes

	@RequestMapping(value = "/insertAccGrnByCheckBoxes", method = RequestMethod.POST) // Using checkboxes to insert
	public String insertAccGrnByCheckBoxes(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in checkboxes ");

		// ModelAndView model = new ModelAndView("grngvn/accGrn");

		// ModelAndView modelAndView = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();

			// Integer.parseInt(request.getParameter("approveGateLogin"));

			HttpSession session = request.getSession();
			UserResponse userResponse = (UserResponse) session.getAttribute("UserDetail");

			int accApproveLogin = userResponse.getUser().getId();

			String[] grnIdList = request.getParameterValues("select_to_agree");

			System.out.println("GRN ID " + grnIdList[0]);

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			String gIds = new String();
			for (int i = 0; i < grnIdList.length; i++) {

				gIds = gIds + "," + grnIdList[i];

			}
			gIds = gIds.substring(1);

			System.out.println("GIDS " + gIds);

			// new code

			List<TempGrnGvnBeanUp> dataList = new ArrayList<>();

			TempGrnGvnBeanUp data = new TempGrnGvnBeanUp();

			GetGrnGvnDetails detail = new GetGrnGvnDetails();

			for (int j = 0; j < grnIdList.length; j++) {

				int accGrnQty = Integer.parseInt(request.getParameter("acc_grn_qty" + grnIdList[j]));

				for (int i = 0; i < grnAccDetailList.size(); i++) {

					if (grnAccDetailList.get(i).getGrnGvnId() == Integer.parseInt(grnIdList[j])) {

						//System.out.println("GRN ID MAtched " + grnIdList[j]);

						detail = new GetGrnGvnDetails();
						data = new TempGrnGvnBeanUp();
						detail = grnAccDetailList.get(i);
						float grnRate = 0;
						float total = detail.getBaseRate() * accGrnQty;
						float aprAmt = 0;
						float aprTaxableAmt, aprTotalTax, aprGrandTotal;
						float sgstRs, cgstRs, igstRs;
						float grandTotal;
						if (detail.getGrnType() == 0) {

							grnRate = detail.getBaseRate() * 75 / 100;
						}

						if (detail.getGrnType() == 1) {

							grnRate = detail.getBaseRate() * 90 / 100;
						}

						if (detail.getGrnType() == 2 || detail.getGrnType() == 4) {

							grnRate = detail.getBaseRate();

						}

						aprTaxableAmt = grnRate * accGrnQty;

						System.out.println("apr Taxable Amt = " + aprTaxableAmt);

						System.out.println("Grn RAte " + grnRate);
						aprTotalTax = ((aprTaxableAmt) * (detail.getSgstPer() + detail.getCgstPer())) / 100;
						System.out.println("Apr Total Tax " + aprTotalTax);

						if (detail.getIsSameState() == 1) {

							sgstRs = aprTotalTax / 2;
							cgstRs = aprTotalTax / 2;

							igstRs = 0.0f;
						} else {

							igstRs = aprTotalTax;

							sgstRs = 0.0f;
							cgstRs = 0.0f;
						}

						System.out.println("sgstsRs" + sgstRs + "CGSt Rs " + cgstRs);

						grandTotal = aprTaxableAmt + aprTotalTax;

						System.out.println("GRAND total " + grandTotal);

						data = new TempGrnGvnBeanUp();

						data.setApprovedDateTimeAcc(dateFormat.format(cal.getTime()));

						data.setApprovedLoginAcc(accApproveLogin);

						data.setGrnGvnStatus(Constants.AP_BY_ACC);

						data.setApprovedRemarkAcc("");

						data.setGrnGvnId(Integer.parseInt(grnIdList[j]));

						data.setAprQtyAcc(accGrnQty);

						data.setAprCgstRs(roundUp(cgstRs));
						data.setAprIgstRs(roundUp(igstRs));
						data.setAprSgstRs(roundUp(sgstRs));

						data.setAprTaxableAmt(roundUp(aprTaxableAmt));
						data.setAprTotalTax(roundUp(aprTotalTax));

						data.setAprGrandTotal(roundUp(grandTotal));
						
						
						

						detail.setAprGrandTotal(data.getAprGrandTotal());
						detail.setAprTaxableAmt(data.getAprTaxableAmt());
						detail.setAprTotalTax(data.getAprTotalTax());
						
						detail.setAprCgstRs(data.getAprCgstRs());
						detail.setAprSgstRs(data.getAprSgstRs());
						detail.setAprIgstRs(data.getAprIgstRs());
						
						detail.setAprROff(data.getAprROff());
						
						grnAccDetailList.set(i, detail);

					} // end of matching grnId

				} // end of for Loop
				dataList.add(data);

			} // end of ids for loop

			// new code

			System.out.println("Data List " + dataList.toString());

			Info updateAccGrn = restTemplate.postForObject(Constants.url + "updateAccGrn", dataList, Info.class);

			if (updateAccGrn.getError() == false) {

				for (int i = 0; i < grnIdList.length; i++) {

					for (int j = 0; j < grnAccDetailList.size(); j++) {

						if (Integer.parseInt(grnIdList[i]) == grnAccDetailList.get(j).getGrnGvnId()) {

							grnAccDetailList.get(j).setGrnGvnStatus(Constants.AP_BY_ACC);
							System.out.println("Record Updated Locally  apr");

						}
					}
				}
			}

			GrnGvnHeader accHeader = new GrnGvnHeader();

			for (int i = 0; i < grnAccHeaderList.size(); i++) {

				if (grnAccHeaderList.get(i).getGrnGvnHeaderId() == globalAccHeaderId) {

					accHeader = grnAccHeaderList.get(i);

					break;

				}
			}

			if (accHeader.getGrngvnStatus() == 2 || accHeader.getGrngvnStatus() == 6
					|| accHeader.getGrngvnStatus() == 7) {
				int rejStatus = 0, aprStatus = 0;

				for (int i = 0; i < grnAccDetailList.size(); i++) {

					// setting approve and rejected count
					if (grnAccDetailList.get(i).getGrnGvnStatus() == 6) {

						aprStatus = aprStatus + 1;

					} else if (grnAccDetailList.get(i).getGrnGvnStatus() == 7) {

						rejStatus = rejStatus + 1;
					}

				}

				// all Rejected
				if (grnAccDetailList.size() == rejStatus) {

					System.out.println("rejStatus count " + rejStatus);

					accHeader.setGrngvnStatus(7);

					System.out.println("Heade status =7 ");
				}

				else if (grnAccDetailList.size() == aprStatus) {

					accHeader.setGrngvnStatus(6);

					System.out.println("header Status = 6");

				}

				// partially approved
				else {

					accHeader.setGrngvnStatus(8);

					System.out.println("header Status =8 ");

				}

				// all Approved

			} // If grnStatus =2

			// if Status=3||7
			else if (accHeader.getGrngvnStatus() == 8) {

				int rejStatus = 0, aprStatus = 0;

				for (int i = 0; i < grnAccDetailList.size(); i++) {

					// setting approve and rejected count
					if (grnAccDetailList.get(i).getGrnGvnStatus() == 3
							|| grnAccDetailList.get(i).getGrnGvnStatus() == 7) {

						rejStatus = rejStatus + 1;

					} else if (grnAccDetailList.get(i).getGrnGvnStatus() == 6) {

						aprStatus = aprStatus + 1;
					}

				} // enf of for

				if (rejStatus == grnAccDetailList.size()) {

					accHeader.setGrngvnStatus(7);
				} else if (aprStatus == grnAccDetailList.size()) {

					accHeader.setGrngvnStatus(6);

				} else {

					accHeader.setGrngvnStatus(8);

				}

			}

			accHeader.setAprTaxableAmt(0);
			accHeader.setAprTotalTax(0);
			accHeader.setAprGrandTotal(0);
			accHeader.setAprCgstRs(0);
			accHeader.setAprSgstRs(0);
			accHeader.setAprIgstRs(0);
			accHeader.setAprROff(0);
			accHeader.setApporvedAmt(0);

			for (int i = 0; i < grnAccDetailList.size(); i++) {

				if (grnAccDetailList.get(i).getGrnGvnStatus() == 6) {

					System.out.println("Status 6 found at C]");

					accHeader.setApporvedAmt(accHeader.getAprGrandTotal() + grnAccDetailList.get(i).getAprGrandTotal());

					accHeader.setAprTaxableAmt(
							accHeader.getAprTaxableAmt() + grnAccDetailList.get(i).getAprTaxableAmt());
					accHeader.setAprTotalTax(accHeader.getAprTotalTax() + grnAccDetailList.get(i).getAprTotalTax());
					accHeader.setAprGrandTotal(accHeader.getAprGrandTotal() + grnAccDetailList.get(i).getAprGrandTotal());
					accHeader.setAprCgstRs(accHeader.getAprCgstRs() + grnAccDetailList.get(i).getAprCgstRs());
					accHeader.setAprSgstRs(accHeader.getAprSgstRs() + grnAccDetailList.get(i).getAprSgstRs());
					accHeader.setAprIgstRs(accHeader.getAprIgstRs() + grnAccDetailList.get(i).getAprIgstRs());
					accHeader.setAprROff(accHeader.getAprROff() + grnAccDetailList.get(i).getAprROff());

				}
			}
			System.out.println("acc Header total amt ***" + accHeader.getApporvedAmt());

			System.out.println("LIST SIZE ACC GRN : " + grnAccDetailList.size());

			accHeader.setApprovedDatetime(dateFormat.format(cal.getTime()));

			accHeader = restTemplate.postForObject(Constants.url + "updateGrnGvnHeader", accHeader, GrnGvnHeader.class);

			System.out.println("GRN HEADER rESPONSE " + accHeader.toString());

			//
			
			
			//-----------------------For Notification-----------------
			String frToken="";
		
			try {
				map = new LinkedMultiValueMap<String, Object>();
				  map.add("frId",accHeader.getFrId());
				   
                 frToken= restTemplate.postForObject(Constants.url+"getFrToken", map, String.class);
		         Firebase.sendPushNotifForCommunication(frToken,"","GRN no"+accHeader.getGrngvnSrno()+"punched by you has been cleared by Accounts"+accHeader.getAprGrandTotal() + " Thank You..Team Monginis","inbox");
		   	
		        }
		        catch(Exception e2)
		        {
			      e2.printStackTrace();
		        }
			
			//-----------------------------------------------------


		} catch (Exception e) {

			System.out.println("Error in update Acc Grn Agree " + e.getMessage());

			e.printStackTrace();

		}

		return "redirect:/getAccGrnDetail/" + globalAccHeaderId;
	}

}
