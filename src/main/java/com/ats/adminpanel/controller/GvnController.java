package com.ats.adminpanel.controller;

import java.math.BigDecimal;
import java.text.DateFormat;
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
import com.ats.adminpanel.model.AllFrIdNameList;
import com.ats.adminpanel.model.Info;
import com.ats.adminpanel.model.grngvn.GetGrnGvnDetails;
import com.ats.adminpanel.model.grngvn.GetGrnGvnDetailsList;
import com.ats.adminpanel.model.grngvn.GrnGvnHeader;
import com.ats.adminpanel.model.grngvn.GrnGvnHeaderList;
import com.ats.adminpanel.model.grngvn.TempGrnGvnBeanUp;
import com.ats.adminpanel.model.login.UserResponse;
import com.ats.adminpanel.model.remarks.GetAllRemarks;
import com.ats.adminpanel.model.remarks.GetAllRemarksList;

@Controller
@Scope("session")
public class GvnController {

	// GVN HEADER LIST
	List<GrnGvnHeader> gvnGateHeaderList, gvnStoreHeaderList, gvnAccHeaderList;
	// GVN HEADER LIST

	GrnGvnHeaderList headerList = new GrnGvnHeaderList();

	GetAllRemarksList allRemarksList;
	List<GetAllRemarks> getAllRemarks;

	GetAllRemarksList getAllRemarksList = new GetAllRemarksList();

	// GVN DATE
	String gateGvnHeaderFromDate, gateGvnHeaderToDate, accGvnHeaderFromDate, accGvnHeaderToDate, storeGvnHeaderFromDate,
			storeGvnHeaderToDate;
	// GVN DATE

	// GVN Detail List
	List<GetGrnGvnDetails> gvnGateDetailList = new ArrayList<>();
	List<GetGrnGvnDetails> gvnStoreDetailList = new ArrayList<>();
	List<GetGrnGvnDetails> gvnAccDetailList = new ArrayList<>();
	// GVN Detail List

	public AllFrIdNameList allFrIdNameList = new AllFrIdNameList();

	public List<String> frList = new ArrayList<String>();

	String frSelectedGateGvnHeader, frSelectedStoreGvnHeader, frSelectedAccGvnHeader;

	// int globalGateHeaderId, globalAccHeaderId;
	int globalGvnGateHeaderId, globalGvnStoreHeaderId, globalGvnAccHeaderId;

	// private String gateGrnHeaderToDate;

	@RequestMapping(value = "/getDateForGateGvnHeader", method = RequestMethod.GET)
	public String getDateForGateGvnHeader(HttpServletRequest request, HttpServletResponse response) {

		gateGvnHeaderFromDate = request.getParameter("fromDate");
		gateGvnHeaderToDate = request.getParameter("toDate");

		String selectedFr = request.getParameter("fr_id_list");

		System.out.println(
				"From Date " + gateGvnHeaderFromDate + "toDate " + gateGvnHeaderToDate + "fr ID List " + selectedFr);
		selectedFr = selectedFr.substring(1, selectedFr.length() - 1);
		selectedFr = selectedFr.replaceAll("\"", "");

		frSelectedGateGvnHeader = selectedFr;

		frList = Arrays.asList(selectedFr);

		System.out.println("Fr List Final " + frList);

		return "";

	}

	@RequestMapping(value = "/getGvnHeaderForGate", method = RequestMethod.GET)
	public ModelAndView getGvnHeaderForGate(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("grngvn/gateGvnHeader");

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

			if (gateGvnHeaderFromDate == "" || gateGvnHeaderFromDate == null) {

				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();

				String curTimeStamp = dateFormat.format(cal.getTime());
				System.out.println("Cur time Stamp= " + curTimeStamp);

				cal.set(Calendar.HOUR, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 1);
				String initialTimeStamp = dateFormat.format(cal.getTime());
				System.out.println("initialTime time Stamp= " + initialTimeStamp);

				String statusList = new String();

				statusList = "1";
				// for Sending Current Date
				java.util.Date date = new java.util.Date();

				DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				gateGvnHeaderFromDate = df.format(date);
				gateGvnHeaderToDate = df.format(date);
				System.out.println("From Date And :" + gateGvnHeaderFromDate + "ToDATE" + gateGvnHeaderToDate);

				map = new LinkedMultiValueMap<String, Object>();

				// map.add("initTime", initialTimeStamp);
				// map.add("curTime", curTimeStamp);
				map.add("statusList", statusList);
				map.add("fromDate", gateGvnHeaderFromDate);// ie current date
				map.add("toDate", gateGvnHeaderToDate);// ie current date
				// map.add("isGrn", 0);
				map.add("isGrn", "0" + "," + "2");

				gvnGateHeaderList = new ArrayList<>();

				headerList = restTemplate.postForObject(Constants.url + "findGrnGvnHeaderOnLoad", map,
						GrnGvnHeaderList.class);

				gvnGateHeaderList = headerList.getGrnGvnHeader();

				System.out.println("Grn Gate Header List ON load  " + gvnGateHeaderList.toString());

			} // end of if onload call

			else {

				if (frList.contains("-1")) {
					isAllFrSelected = true;

				}

				if (isAllFrSelected) {
					// all Fr selected Web Service
					System.out.println("All Fr Selected =true");

					map = new LinkedMultiValueMap<String, Object>();

					map.add("fromDate", gateGvnHeaderFromDate);// ie current date
					map.add("toDate", gateGvnHeaderToDate);// ie current date
					map.add("isGrn", "0" + "," + "2");

					gvnGateHeaderList = new ArrayList<>();

					headerList = restTemplate.postForObject(Constants.url + "getGrnGvnHeaderAllFr", map,
							GrnGvnHeaderList.class);

					gvnGateHeaderList = headerList.getGrnGvnHeader();

					System.out.println("Grn Gate Header List  All FR" + gvnGateHeaderList.toString());

				} else {

					System.out.println("Specific Fr Selected ");

					map = new LinkedMultiValueMap<String, Object>();

					map.add("frIdList", frSelectedGateGvnHeader);
					map.add("fromDate", gateGvnHeaderFromDate);
					map.add("toDate", gateGvnHeaderToDate);
					// map.add("isGrn", 0);
					map.add("isGrn", "0" + "," + "2");

					gvnGateHeaderList = new ArrayList<>();

					headerList = restTemplate.postForObject(Constants.url + "getGrnGvnHeader", map,
							GrnGvnHeaderList.class);

					gvnGateHeaderList = headerList.getGrnGvnHeader();

					System.out.println("Grn Gate Header List  specific FR " + gvnGateHeaderList.toString());
				}

			} // End of else

			model.addObject("fromDate", gateGvnHeaderFromDate);
			model.addObject("toDate", gateGvnHeaderToDate);
			model.addObject("grnList", gvnGateHeaderList);
			model.addObject("selectedFr", frList);

		} catch (Exception e) {

			System.out.println("Excep in Gate Header List /getGrnHeaderForGate " + e.getMessage());
			e.printStackTrace();
		}

		return model;
	}

	// getGateGvnDetail

	@RequestMapping(value = "/getGateGvnDetail/{headerId}", method = RequestMethod.GET)
	public ModelAndView getGateGvnDetail(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("headerId") int headerId) {
		ModelAndView modelAndView = null;

		modelAndView = new ModelAndView("grngvn/gateGvn");

		System.out.println("He ader " + headerId);

		globalGvnGateHeaderId = headerId;

		RestTemplate restTemplate = new RestTemplate();

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		map.add("grnGvnHeaderId", headerId);
		// getFrGvnDetail
		try {

			GetGrnGvnDetailsList detailList = restTemplate.postForObject(Constants.url + "getFrGvnDetails", map,
					GetGrnGvnDetailsList.class);

			gvnGateDetailList = new ArrayList<>();

			gvnGateDetailList = detailList.getGrnGvnDetails();

			System.out.println("GRN Detail   " + gvnGateDetailList.toString());

			// Ganesh Remrk
			map = new LinkedMultiValueMap<String, Object>();
			map.add("isFrUsed", 0);
			map.add("moduleId", 1);
			map.add("subModuleId", 3);
			getAllRemarksList = restTemplate.postForObject(Constants.url + "/getAllRemarks", map,
					GetAllRemarksList.class);

			getAllRemarks = new ArrayList<>();
			getAllRemarks = getAllRemarksList.getGetAllRemarks();

			System.out.println("remark list " + getAllRemarks.toString());

			modelAndView.addObject("remarkList", getAllRemarks);
			
			GrnGvnHeader gateHeader = new GrnGvnHeader();
			
			String grnDate =null;

			for (int i = 0; i < gvnGateHeaderList.size(); i++) {

				if (gvnGateHeaderList.get(i).getGrnGvnHeaderId() == globalGvnGateHeaderId) {

					gateHeader = gvnGateHeaderList.get(i);

					break;

				}
			}
			modelAndView.addObject("srNo", gateHeader.getGrngvnSrno());

			modelAndView.addObject("grnDate", gateHeader.getGrngvnDate());

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("Ex in grn Detail " + e.getMessage());

		}

		
		modelAndView.addObject("gvnList", gvnGateDetailList);
		
		modelAndView.addObject("url",Constants.GVN_IMAGE_URL);

		return modelAndView;

	}

	// A]: Gate GVN Approve Specific Record
	@RequestMapping(value = "/insertGateGvnProcessAgree", method = RequestMethod.GET)
	public String insertGateGvnProcessAgree(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = new ModelAndView("grngvn/gateGvn");

		try {

			HttpSession session = request.getSession();
			UserResponse userResponse = (UserResponse) session.getAttribute("UserDetail");
			int gateApproveLogin = userResponse.getUser().getId();

			int gateGvnQty = Integer.parseInt(request.getParameter("gate_gvn_qty"));

			int grnId = Integer.parseInt(request.getParameter("grnId"));

			// Integer.parseInt(request.getParameter("approveGateLogin"));
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

			data.setAprQtyGate(gateGvnQty);

			dataList.add(data);

			Info info = restTemplate.postForObject(Constants.url + "updateGateGrn", dataList, Info.class);

			System.out.println("after calling web service of gate grn agree info response as String - " + info);

			if (info.getError() == false) {

				for (int i = 0; i < gvnGateDetailList.size(); i++) {

					if (grnId == gvnGateDetailList.get(i).getGrnGvnId()) {

						gvnGateDetailList.get(i).setGrnGvnStatus(Constants.AP_BY_GATE);
						System.out.println("Record Updated Locally  apr");
						break;

					}
				}

			}

			GrnGvnHeader gateHeader = new GrnGvnHeader();

			for (int i = 0; i < gvnGateHeaderList.size(); i++) {

				if (gvnGateHeaderList.get(i).getGrnGvnHeaderId() == globalGvnGateHeaderId) {

					gateHeader = gvnGateHeaderList.get(i);

					break;

				}
			}

			int rejStatus = 0, aprStatus = 0;

			for (int i = 0; i < gvnGateDetailList.size(); i++) {

				// setting approve and rejected count
				if (gvnGateDetailList.get(i).getGrnGvnStatus() == 2
						|| gvnGateDetailList.get(i).getGrnGvnStatus() == 6) {
					aprStatus = aprStatus + 1;
				} else {

					rejStatus = rejStatus + 1;
				}

			}

			// all Rejected
			if (gvnGateDetailList.size() == rejStatus) {

				System.out.println("list Size " + rejStatus);

				gateHeader.setGrngvnStatus(3);

				System.out.println("Heade status =3 ");
			}

			// partially approved
			if (gvnGateDetailList.size() != aprStatus && gvnGateDetailList.size() != rejStatus) {

				gateHeader.setGrngvnStatus(8);

				System.out.println("header Status =8 ");

			}

			// all Approved
			if (gvnGateDetailList.size() == aprStatus) {

				gateHeader.setGrngvnStatus(2);

				System.out.println("header Status = 2");

			}

			// Update Grn Gvn Header
			gateHeader.setApprovedDatetime(dateFormat.format(cal.getTime()));

			gateHeader = restTemplate.postForObject(Constants.url + "updateGrnGvnHeader", gateHeader,
					GrnGvnHeader.class);

			System.out.println("GRN HEADER rESPONSE " + gateHeader.toString());

		} catch (Exception e) {

			System.out.println("Error in insert Gat eGrn Process Agree " + e.getMessage());
			e.printStackTrace();
		}

		return "redirect:/getGateGvnDetail";

	}

	// B]: Gate GVN DisApprove Specific Record
	@RequestMapping(value = "/insertGateGvnProcessDisAgree", method = RequestMethod.GET)
	public String insertGateGvnProcessDisAgree(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = new ModelAndView("grngvn/gateGvn");

		try {

			HttpSession session = request.getSession();
			UserResponse userResponse = (UserResponse) session.getAttribute("UserDetail");
			int gateApproveLogin = userResponse.getUser().getId();

			int grnId = Integer.parseInt(request.getParameter("grnId"));

			// Integer.parseInt(request.getParameter("approveGateLogin"));
			int gateGvnQty = Integer.parseInt(request.getParameter("gate_gvn_qty"));
			String gateRemark = request.getParameter("gateRemark");

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

			data.setAprQtyGate(gateGvnQty);

			dataList.add(data);

			Info info = restTemplate.postForObject(Constants.url + "updateGateGrn", dataList, Info.class);

			System.out.println("after calling web service of gate grn agree info response as String - " + info);

			if (info.getError() == false) {

				for (int i = 0; i < gvnGateDetailList.size(); i++) {

					if (grnId == gvnGateDetailList.get(i).getGrnGvnId()) {

						gvnGateDetailList.get(i).setGrnGvnStatus(Constants.DIS_BY_GATE);
						System.out.println("Record Updated Locally  apr");
						break;

					}
				}

			}

			GrnGvnHeader gateHeader = new GrnGvnHeader();

			for (int i = 0; i < gvnGateHeaderList.size(); i++) {

				if (gvnGateHeaderList.get(i).getGrnGvnHeaderId() == globalGvnGateHeaderId) {

					gateHeader = gvnGateHeaderList.get(i);

					break;

				}
			}

			int rejStatus = 0, aprStatus = 0;

			for (int i = 0; i < gvnGateDetailList.size(); i++) {

				// setting approve and rejected count
				if (gvnGateDetailList.get(i).getGrnGvnStatus() == 2
						|| gvnGateDetailList.get(i).getGrnGvnStatus() == 6) {
					aprStatus = aprStatus + 1;
				} else {

					rejStatus = rejStatus + 1;
				}

			}

			// all Rejected
			if (gvnGateDetailList.size() == rejStatus) {

				System.out.println("list Size " + rejStatus);

				gateHeader.setGrngvnStatus(3);

				System.out.println("Heade status =3 ");
			}

			// partially approved
			if (gvnGateDetailList.size() != aprStatus && gvnGateDetailList.size() != rejStatus) {

				gateHeader.setGrngvnStatus(8);

				System.out.println("header Status =8 ");

			}

			// all Approved
			if (gvnGateDetailList.size() == aprStatus) {

				gateHeader.setGrngvnStatus(2);

				System.out.println("header Status = 2");

			}

			// Update Grn Gvn Header
			gateHeader.setApprovedDatetime(dateFormat.format(cal.getTime()));

			gateHeader = restTemplate.postForObject(Constants.url + "updateGrnGvnHeader", gateHeader,
					GrnGvnHeader.class);

			System.out.println("GRN HEADER rESPONSE " + gateHeader.toString());

		} catch (Exception e) {

			System.out.println("Error in insert Gat eGrn Process Agree " + e.getMessage());
			e.printStackTrace();
		}

		return "redirect:/getGateGvnDetail";

	}

	// C]: Gate Gvn Approve Multiple Records using CheckBox

	@RequestMapping(value = "/insertGateGvnByCheckBoxes", method = RequestMethod.POST) // Using checkboxes to insert
	public String insertGateGvnByCheckBoxes(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in checkboxes for Gate GVN  ");

		ModelAndView model = new ModelAndView("grngvn/gateGvn");

		ModelAndView modelAndView = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();

			// Integer.parseInt(request.getParameter("approveGateLogin"));

			HttpSession session = request.getSession();
			UserResponse userResponse = (UserResponse) session.getAttribute("UserDetail");

			int gateApproveLogin = userResponse.getUser().getId();

			String[] grnIdList = request.getParameterValues("select_to_agree");

			System.out.println("GRN ID " + grnIdList[0]);

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			String gIds = new String();
			for (int i = 0; i < grnIdList.length; i++) {

				gIds = gIds + "," + grnIdList[i];

			}
			gIds = gIds.substring(1);

			List<TempGrnGvnBeanUp> dataList = new ArrayList<>();

			for (int i = 0; i < grnIdList.length; i++) {

				int gateGvnQty = Integer.parseInt(request.getParameter("gate_gvn_qty" + grnIdList[i]));
				System.out.println("corresponding gID " + grnIdList[i] + "Gate Qty " + gateGvnQty);

				TempGrnGvnBeanUp data = new TempGrnGvnBeanUp();

				data.setApproveimedDateTimeGate(dateFormat.format(cal.getTime()));

				data.setApprovedLoginGate(gateApproveLogin);

				data.setGrnGvnStatus(Constants.AP_BY_GATE);

				data.setApprovedRemarkGate("Approve By Dispatch");

				data.setGrnGvnId(Integer.parseInt(grnIdList[i]));

				data.setAprQtyGate(gateGvnQty);

				dataList.add(data);

				gIds = gIds + "," + grnIdList[i];

			}
			System.out.println("GIDS " + gIds);

			Info updateGateGrn = restTemplate.postForObject(Constants.url + "updateGateGrn", dataList, Info.class);

			if (updateGateGrn.getError() == false) {

				for (int i = 0; i < grnIdList.length; i++) {

					for (int j = 0; j < gvnGateDetailList.size(); j++) {

						if (Integer.parseInt(grnIdList[i]) == gvnGateDetailList.get(j).getGrnGvnId()) {

							gvnGateDetailList.get(j).setGrnGvnStatus(Constants.AP_BY_GATE);
							System.out.println("Record Updated Locally apr chk boxes ");

						}
					}
				}
			}

			System.out.println("update Grn chk response " + updateGateGrn.toString());

			// update Header set data and web service call

			GrnGvnHeader gateHeader = new GrnGvnHeader();

			for (int i = 0; i < gvnGateHeaderList.size(); i++) {

				if (gvnGateHeaderList.get(i).getGrnGvnHeaderId() == globalGvnGateHeaderId) {

					gateHeader = gvnGateHeaderList.get(i);

					break;

				}
			}

			int rejStatus = 0, aprStatus = 0;

			for (int i = 0; i < gvnGateDetailList.size(); i++) {

				if (gvnGateDetailList.get(i).getGrnGvnStatus() == 2
						|| gvnGateDetailList.get(i).getGrnGvnStatus() == 6) {

					aprStatus = aprStatus + 1;

				} else {

					rejStatus = rejStatus + 1;

				}

			} // End of For Loop

			if (gvnGateDetailList.size() == rejStatus) {

				gateHeader.setGrngvnStatus(3);
				System.out.println("header Status =3");
			}

			// partially approved
			if (gvnGateDetailList.size() != aprStatus && gvnGateDetailList.size() != rejStatus) {

				gateHeader.setGrngvnStatus(8);
				System.out.println("Header Status =8 ");
			}

			// all Approved
			if (gvnGateDetailList.size() == aprStatus) {

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

		return "redirect:/getGateGvnDetail/" + globalGvnGateHeaderId;

	}

	// Store GVN started
	@RequestMapping(value = "/getDateForStoreGvnHeader", method = RequestMethod.GET)
	public String getDateForStoreGvnHeader(HttpServletRequest request, HttpServletResponse response) {

		storeGvnHeaderFromDate = request.getParameter("fromDate");
		storeGvnHeaderToDate = request.getParameter("toDate");

		String selectedFr = request.getParameter("fr_id_list");

		System.out.println(
				"From Date " + storeGvnHeaderFromDate + "toDate " + storeGvnHeaderToDate + "fr ID List " + selectedFr);
		selectedFr = selectedFr.substring(1, selectedFr.length() - 1);
		selectedFr = selectedFr.replaceAll("\"", "");

		frSelectedStoreGvnHeader = selectedFr;

		frList = Arrays.asList(selectedFr);

		System.out.println("Fr List Final " + frList);

		return "";

	}

	void clearDate(String fromDate, String toDate) {

		fromDate = null;
		toDate = null;

	}

	@RequestMapping(value = "/getGvnHeaderForStore/{type}", method = RequestMethod.GET)
	public ModelAndView getGvnHeaderForStore(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int type) {

		ModelAndView model = new ModelAndView("grngvn/storeGvnHeader");
		System.err.println("Type Received  " + type);
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

			if (storeGvnHeaderFromDate == "" || storeGvnHeaderFromDate == null) {

				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();

				String curTimeStamp = dateFormat.format(cal.getTime());
				System.out.println("Cur time Stamp= " + curTimeStamp);

				cal.set(Calendar.HOUR, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 1);
				String initialTimeStamp = dateFormat.format(cal.getTime());
				System.out.println("initialTime time Stamp= " + initialTimeStamp);

				String statusList = new String();

				statusList = "2" + "," + "8";
				// for Sending Current Date
				java.util.Date date = new java.util.Date();

				DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				storeGvnHeaderFromDate = df.format(date);
				storeGvnHeaderToDate = df.format(date);
				System.out.println("From Date And :" + storeGvnHeaderFromDate + "ToDATE" + storeGvnHeaderToDate);

				map = new LinkedMultiValueMap<String, Object>();

				// map.add("initTime", initialTimeStamp);
				// map.add("curTime", curTimeStamp);
				map.add("statusList", statusList);
				map.add("fromDate", storeGvnHeaderFromDate);// ie current date
				map.add("toDate", storeGvnHeaderToDate);// ie current date

				if (type == 0)
					map.add("isGrn", 0);
				else
					map.add("isGrn", 2);

				gvnStoreHeaderList = new ArrayList<>();

				headerList = restTemplate.postForObject(Constants.url + "findGrnGvnHeaderOnLoad", map,
						GrnGvnHeaderList.class);

				gvnStoreHeaderList = headerList.getGrnGvnHeader();

				System.out.println("Grn Gate Header List ON load  " + gvnStoreHeaderList.toString());

			} // end of if onload call

			else {

				if (frList.contains("-1")) {
					isAllFrSelected = true;

				}

				if (isAllFrSelected) {
					// all Fr selected Web Service
					System.out.println("All Fr Selected =true");

					map = new LinkedMultiValueMap<String, Object>();

					map.add("fromDate", storeGvnHeaderFromDate);// ie current date
					map.add("toDate", storeGvnHeaderToDate);// ie current date
					if (type == 0)
						map.add("isGrn", 0);
					else
						map.add("isGrn", 2);

					gvnStoreHeaderList = new ArrayList<>();

					headerList = restTemplate.postForObject(Constants.url + "getGrnGvnHeaderAllFr", map,
							GrnGvnHeaderList.class);

					gvnStoreHeaderList = headerList.getGrnGvnHeader();

					System.out.println("Grn Gate Header List  All FR" + gvnStoreHeaderList.toString());

				} else {

					System.out.println("Specific Fr Selected ");

					map = new LinkedMultiValueMap<String, Object>();

					map.add("frIdList", frSelectedStoreGvnHeader);
					map.add("fromDate", storeGvnHeaderFromDate);
					map.add("toDate", storeGvnHeaderToDate);
					if (type == 0)
						map.add("isGrn", 0);
					else
						map.add("isGrn", 2);

					gvnStoreHeaderList = new ArrayList<>();

					headerList = restTemplate.postForObject(Constants.url + "getGrnGvnHeader", map,
							GrnGvnHeaderList.class);

					gvnStoreHeaderList = headerList.getGrnGvnHeader();

					System.out.println("Grn Gate Header List  specific FR " + gvnStoreHeaderList.toString());
				}

			} // End of else

			model.addObject("fromDate", storeGvnHeaderFromDate);
			model.addObject("toDate", storeGvnHeaderToDate);
			model.addObject("grnList", gvnStoreHeaderList);
			model.addObject("selectedFr", frList);
			model.addObject("type", type);
		} catch (Exception e) {

			System.out.println("Excep in Gate Header List /getGvnHeaderForStore " + e.getMessage());
			e.printStackTrace();
		}
		// clearDate(storeGvnHeaderFromDate,storeGvnHeaderToDate);
		return model;
	}

	// getStoreGvnDetail

	@RequestMapping(value = "/getStoreGvnDetail/{headerId}", method = RequestMethod.GET)
	public ModelAndView getStoreGvnDetail(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("headerId") int headerId) {
		ModelAndView modelAndView = null;

		modelAndView = new ModelAndView("grngvn/storeGvn");

		System.out.println("He ader " + headerId);

		globalGvnStoreHeaderId = headerId;

		RestTemplate restTemplate = new RestTemplate();

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		map.add("grnGvnHeaderId", headerId);
		// getFrGvnDetail
		try {

			GetGrnGvnDetailsList detailList = restTemplate.postForObject(Constants.url + "getFrGvnDetails", map,
					GetGrnGvnDetailsList.class);

			gvnStoreDetailList = new ArrayList<>();

			gvnStoreDetailList = detailList.getGrnGvnDetails();

			System.out.println("GRN Detail   " + gvnStoreDetailList.toString());

			// Ganesh Remrk
			map = new LinkedMultiValueMap<String, Object>();
			map.add("isFrUsed", 0);
			map.add("moduleId", 1);
			map.add("subModuleId", 4);//4 for QC and 5 for Store/Sales
			getAllRemarksList = restTemplate.postForObject(Constants.url + "/getAllRemarks", map,
					GetAllRemarksList.class);

			getAllRemarks = new ArrayList<>();
			getAllRemarks = getAllRemarksList.getGetAllRemarks();

			modelAndView.addObject("remarkList", getAllRemarks);
			
			
			
			GrnGvnHeader gateHeader = new GrnGvnHeader();

			for (int i = 0; i < gvnStoreHeaderList.size(); i++) {

				if (gvnStoreHeaderList.get(i).getGrnGvnHeaderId() == globalGvnStoreHeaderId) {

					gateHeader = gvnStoreHeaderList.get(i);

					break;

				}
			}
			modelAndView.addObject("srNo", gateHeader.getGrngvnSrno());


		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("Ex in grn Detail " + e.getMessage());

		}

		String grnDate = gvnStoreDetailList.get(0).getGrnGvnDate();
		modelAndView.addObject("gvnList", gvnStoreDetailList);
		modelAndView.addObject("grnDate", grnDate);
		modelAndView.addObject("url",Constants.GVN_IMAGE_URL);

		return modelAndView;

	}

	// A]: Store GVN Approve Specific Record
	@RequestMapping(value = "/insertStoreGvnProcessAgree", method = RequestMethod.GET)
	public String insertStoreGvnProcessAgree(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = new ModelAndView("grngvn/storeGvn");

		try {

			HttpSession session = request.getSession();
			UserResponse userResponse = (UserResponse) session.getAttribute("UserDetail");
			int storeApproveLogin = userResponse.getUser().getId();

			int grnId = Integer.parseInt(request.getParameter("grnId"));
			int storeGvnQty = Integer.parseInt(request.getParameter("store_gvn_qty"));
			// Integer.parseInt(request.getParameter("approveGateLogin"));
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			List<TempGrnGvnBeanUp> dataList = new ArrayList<>();

			TempGrnGvnBeanUp data = new TempGrnGvnBeanUp();

			data.setApprovedDateTimeStore(dateFormat.format(cal.getTime()));

			data.setApprovedLoginStore(storeApproveLogin);

			data.setGrnGvnStatus(Constants.AP_BY_STORE);

			data.setApprovedRemarkStore("Approve BY Store");

			data.setGrnGvnId(grnId);

			data.setAprQtyStore(storeGvnQty);

			dataList.add(data);

			Info info = restTemplate.postForObject(Constants.url + "updateStoreGvn", dataList, Info.class);

			System.out.println("after calling web service of gate grn agree info response as String - " + info);

			if (info.getError() == false) {

				for (int i = 0; i < gvnStoreDetailList.size(); i++) {

					if (grnId == gvnStoreDetailList.get(i).getGrnGvnId()) {

						gvnStoreDetailList.get(i).setGrnGvnStatus(Constants.AP_BY_STORE);
						System.out.println("Record Updated Locally  apr");
						break;

					}
				}

			}

			GrnGvnHeader gateHeader = new GrnGvnHeader();

			for (int i = 0; i < gvnStoreHeaderList.size(); i++) {

				if (gvnStoreHeaderList.get(i).getGrnGvnHeaderId() == globalGvnStoreHeaderId) {

					gateHeader = gvnStoreHeaderList.get(i);

					break;

				}
			}

			System.out.println("Data ==***" + gvnStoreDetailList.toString());

			if (gateHeader.getGrngvnStatus() == 2 || gateHeader.getGrngvnStatus() == 4
					|| gateHeader.getGrngvnStatus() == 5) {

				int rejStatus = 0, aprStatus = 0;

				for (int i = 0; i < gvnStoreDetailList.size(); i++) {

					if (gvnStoreDetailList.get(i).getGrnGvnStatus() == 4) {

						aprStatus = aprStatus + 1;
					} else if (gvnStoreDetailList.get(i).getGrnGvnStatus() == 5) {

						rejStatus = rejStatus + 1;
					}
				} // end of for loop

				if (rejStatus == gvnStoreDetailList.size()) {

					gateHeader.setGrngvnStatus(5);// reject by store
				} else if (aprStatus == gvnStoreDetailList.size()) {

					gateHeader.setGrngvnStatus(4);// app by store

				} else {
					gateHeader.setGrngvnStatus(8);
				}
			} // end of if header Status==2,4,5

			else if (gateHeader.getGrngvnStatus() == 8) {

				int rejStatus = 0, aprStatus = 0;

				for (int i = 0; i < gvnStoreDetailList.size(); i++) {

					if (gvnStoreDetailList.get(i).getGrnGvnStatus() == 3
							|| gvnStoreDetailList.get(i).getGrnGvnStatus() == 5) {

						rejStatus = rejStatus + 1;

					} else if (gvnStoreDetailList.get(i).getGrnGvnStatus() == 4) {

						aprStatus = aprStatus + 1;

					}
				} // end of for loop

				if (rejStatus == gvnStoreDetailList.size()) {

					gateHeader.setGrngvnStatus(5);// reject by store

				} else if (aprStatus == gvnStoreDetailList.size()) {

					gateHeader.setGrngvnStatus(4);// app by store

				} else {

					gateHeader.setGrngvnStatus(8);
				}

			} // end of else if header status ==8

			// Update Grn Gvn Header
			gateHeader.setApprovedDatetime(dateFormat.format(cal.getTime()));

			gateHeader = restTemplate.postForObject(Constants.url + "updateGrnGvnHeader", gateHeader,
					GrnGvnHeader.class);

			System.out.println("GRN HEADER rESPONSE " + gateHeader.toString());

		} catch (Exception e) {

			System.out.println("Error in insert Gat eGrn Process Agree " + e.getMessage());
			e.printStackTrace();
		}

		return "redirect:/getStoreGvnDetail";

	}

	// B]: Store GVN DisApprove Specific Record

	@RequestMapping(value = "/insertStoreGvnProcessDisAgree", method = RequestMethod.GET)
	public String insertStoreGvnProcessDisAgree(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = new ModelAndView("grngvn/storeGvn");

		try {

			HttpSession session = request.getSession();
			UserResponse userResponse = (UserResponse) session.getAttribute("UserDetail");
			int storeApproveLogin = userResponse.getUser().getId();
			int storeGvnQty = Integer.parseInt(request.getParameter("store_gvn_qty"));
			int grnId = Integer.parseInt(request.getParameter("grnId"));

			String storeRemark = request.getParameter("storeRemark");

			// Integer.parseInt(request.getParameter("approveGateLogin"));
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			List<TempGrnGvnBeanUp> dataList = new ArrayList<>();

			TempGrnGvnBeanUp data = new TempGrnGvnBeanUp();

			data.setApprovedDateTimeStore(dateFormat.format(cal.getTime()));

			data.setApprovedLoginStore(storeApproveLogin);

			data.setGrnGvnStatus(Constants.DIS_BY_STORE);

			data.setApprovedRemarkStore(storeRemark);

			data.setGrnGvnId(grnId);

			data.setAprQtyStore(storeGvnQty);

			dataList.add(data);
			Info info = restTemplate.postForObject(Constants.url + "updateStoreGvn", dataList, Info.class);

			System.out.println("after calling web service of gate grn agree info response as String - " + info);

			if (info.getError() == false) {

				for (int i = 0; i < gvnStoreDetailList.size(); i++) {

					if (grnId == gvnStoreDetailList.get(i).getGrnGvnId()) {

						gvnStoreDetailList.get(i).setGrnGvnStatus(Constants.DIS_BY_STORE);
						System.out.println("Record Updated Locally  apr");
						break;

					}
				}

			}

			GrnGvnHeader gateHeader = new GrnGvnHeader();

			for (int i = 0; i < gvnStoreHeaderList.size(); i++) {

				if (gvnStoreHeaderList.get(i).getGrnGvnHeaderId() == globalGvnStoreHeaderId) {

					gateHeader = gvnStoreHeaderList.get(i);

					break;

				}
			}

			System.out.println("Data ==***" + gvnStoreDetailList.toString());

			if (gateHeader.getGrngvnStatus() == 2 || gateHeader.getGrngvnStatus() == 4
					|| gateHeader.getGrngvnStatus() == 5) {

				int rejStatus = 0, aprStatus = 0;

				for (int i = 0; i < gvnStoreDetailList.size(); i++) {

					if (gvnStoreDetailList.get(i).getGrnGvnStatus() == 4) {

						aprStatus = aprStatus + 1;
					} else if (gvnStoreDetailList.get(i).getGrnGvnStatus() == 5) {

						rejStatus = rejStatus + 1;
					}
				} // end of for loop

				if (rejStatus == gvnStoreDetailList.size()) {

					gateHeader.setGrngvnStatus(5);// reject by store
				} else if (aprStatus == gvnStoreDetailList.size()) {

					gateHeader.setGrngvnStatus(4);// app by store

				} else {
					gateHeader.setGrngvnStatus(8);
				}
			} // end of if header Status==2,4,5

			else if (gateHeader.getGrngvnStatus() == 8) {

				int rejStatus = 0, aprStatus = 0;

				for (int i = 0; i < gvnStoreDetailList.size(); i++) {

					if (gvnStoreDetailList.get(i).getGrnGvnStatus() == 3
							|| gvnStoreDetailList.get(i).getGrnGvnStatus() == 5) {

						rejStatus = rejStatus + 1;

					} else if (gvnStoreDetailList.get(i).getGrnGvnStatus() == 4) {

						aprStatus = aprStatus + 1;

					}
				} // end of for loop

				if (rejStatus == gvnStoreDetailList.size()) {

					gateHeader.setGrngvnStatus(5);// reject by store

				} else if (aprStatus == gvnStoreDetailList.size()) {

					gateHeader.setGrngvnStatus(4);// app by store

				} else {

					gateHeader.setGrngvnStatus(8);
				}

			} // end of else if header status ==8

			// Update Grn Gvn Header
			gateHeader.setApprovedDatetime(dateFormat.format(cal.getTime()));

			gateHeader = restTemplate.postForObject(Constants.url + "updateGrnGvnHeader", gateHeader,
					GrnGvnHeader.class);

			System.out.println("GRN HEADER rESPONSE " + gateHeader.toString());

		} catch (Exception e) {

			System.out.println("Error in insert Gat eGrn Process Agree " + e.getMessage());
			e.printStackTrace();
		}

		return "redirect:/getStoreGvnDetail";

	}

	// C]: Gate Gvn Approve Multiple Records using CheckBox

	@RequestMapping(value = "/insertStoreGvnByCheckBoxes", method = RequestMethod.POST) // Using checkboxes to insert
	public String insertStoreGvnByCheckBoxes(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in checkboxes for Gate GVN  ");

		ModelAndView model = new ModelAndView("grngvn/storeGvn");

		ModelAndView modelAndView = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();

			// Integer.parseInt(request.getParameter("approveGateLogin"));

			HttpSession session = request.getSession();
			UserResponse userResponse = (UserResponse) session.getAttribute("UserDetail");

			int storeApproveLogin = userResponse.getUser().getId();

			String[] grnIdList = request.getParameterValues("select_to_agree");

			System.out.println("Length Of Grn Ids " + grnIdList.length);

			System.out.println("GRN ID " + grnIdList[0]);

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			String gIds = new String();
			List<TempGrnGvnBeanUp> dataList = new ArrayList<>();

			for (int i = 0; i < grnIdList.length; i++) {

				int storeGvnQty = Integer.parseInt(request.getParameter("store_gvn_qty" + grnIdList[i]));
				System.out.println("corresponding gID " + grnIdList[i] + "storeGvnQty Qty " + storeGvnQty);

				TempGrnGvnBeanUp data = new TempGrnGvnBeanUp();

				data.setApprovedDateTimeStore(dateFormat.format(cal.getTime()));

				data.setApprovedLoginStore(storeApproveLogin);

				data.setGrnGvnStatus(Constants.AP_BY_STORE);

				data.setApprovedRemarkStore("Approve BY Store");

				data.setGrnGvnId(Integer.parseInt(grnIdList[i]));

				data.setAprQtyStore(storeGvnQty);

				dataList.add(data);

				gIds = gIds + "," + grnIdList[i];

			}
			gIds = gIds.substring(1);

			System.out.println("GIDS " + gIds);

			Info updateStoreGvn = restTemplate.postForObject(Constants.url + "updateStoreGvn", dataList, Info.class);

			if (updateStoreGvn.getError() == false) {

				// System.out.println("GRN ID LIST IN if update Success:"+grnIdList[i]);

				for (int i = 0; i < grnIdList.length; i++) {

					for (int j = 0; j < gvnStoreDetailList.size(); j++) {

						System.out.println("GRN ID LIST IN if update Success:" + grnIdList[i]);

						if (Integer.parseInt(grnIdList[i]) == gvnStoreDetailList.get(j).getGrnGvnId()) {

							gvnStoreDetailList.get(j).setGrnGvnStatus(Constants.AP_BY_STORE);
							System.out.println("Record Updated Locally apr chk boxes ");

						}
					}
				}
			}

			System.out.println("update Grn chk response " + updateStoreGvn.toString());

			// update Header set data and web service call

			GrnGvnHeader gateHeader = new GrnGvnHeader();

			for (int i = 0; i < gvnStoreHeaderList.size(); i++) {

				if (gvnStoreHeaderList.get(i).getGrnGvnHeaderId() == globalGvnStoreHeaderId) {

					gateHeader = gvnStoreHeaderList.get(i);

					break;

				}
			}

			System.out.println("Data ==***" + gvnStoreDetailList.toString());
			if (gateHeader.getGrngvnStatus() == 2 || gateHeader.getGrngvnStatus() == 4
					|| gateHeader.getGrngvnStatus() == 5) {

				int rejStatus = 0, aprStatus = 0;

				for (int i = 0; i < gvnStoreDetailList.size(); i++) {

					if (gvnStoreDetailList.get(i).getGrnGvnStatus() == 4) {

						aprStatus = aprStatus + 1;
					} else if (gvnStoreDetailList.get(i).getGrnGvnStatus() == 5) {

						rejStatus = rejStatus + 1;
					}
				} // end of for loop

				if (rejStatus == gvnStoreDetailList.size()) {

					gateHeader.setGrngvnStatus(5);// reject by store
				} else if (aprStatus == gvnStoreDetailList.size()) {

					gateHeader.setGrngvnStatus(4);// app by store

				} else {
					gateHeader.setGrngvnStatus(8);
				}
			} // end of if header Status==2,4,5

			else if (gateHeader.getGrngvnStatus() == 8) {

				int rejStatus = 0, aprStatus = 0;

				for (int i = 0; i < gvnStoreDetailList.size(); i++) {

					if (gvnStoreDetailList.get(i).getGrnGvnStatus() == 3
							|| gvnStoreDetailList.get(i).getGrnGvnStatus() == 5) {

						rejStatus = rejStatus + 1;

					} else if (gvnStoreDetailList.get(i).getGrnGvnStatus() == 4) {

						aprStatus = aprStatus + 1;

					}
				} // end of for loop

				if (rejStatus == gvnStoreDetailList.size()) {

					gateHeader.setGrngvnStatus(5);// reject by store

				} else if (aprStatus == gvnStoreDetailList.size()) {

					gateHeader.setGrngvnStatus(4);// app by store

				} else {

					gateHeader.setGrngvnStatus(8);
				}

			} // end of else if header status ==8

			// update Grn Gvn Header
			gateHeader.setApprovedDatetime(dateFormat.format(cal.getTime()));

			gateHeader = restTemplate.postForObject(Constants.url + "updateGrnGvnHeader", gateHeader,
					GrnGvnHeader.class);

			System.out.println("GRN HEADER Response : " + gateHeader.toString());

		} catch (Exception e) {

			System.out.println("Gate Grn Checkbox Error :" + e.getMessage());
			e.printStackTrace();

		}

		return "redirect:/getStoreGvnDetail/" + globalGvnStoreHeaderId;

	}

	@RequestMapping(value = "/getDateFoAccGvnHeader", method = RequestMethod.GET)
	public String getDateFoAccGvnHeader(HttpServletRequest request, HttpServletResponse response) {

		accGvnHeaderFromDate = request.getParameter("fromDate");
		accGvnHeaderToDate = request.getParameter("toDate");

		String selectedFr = request.getParameter("fr_id_list");

		System.out.println(
				"From Date " + accGvnHeaderFromDate + "toDate " + accGvnHeaderToDate + "fr ID List " + selectedFr);
		selectedFr = selectedFr.substring(1, selectedFr.length() - 1);
		selectedFr = selectedFr.replaceAll("\"", "");

		frSelectedAccGvnHeader = selectedFr;

		frList = Arrays.asList(selectedFr);

		System.out.println("Fr List Final " + frList);

		return "";

	}

	@RequestMapping(value = "/getGvnHeaderForAcc", method = RequestMethod.GET)
	public ModelAndView getGvnHeaderForAcc(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("grngvn/accGvnHeader");

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

			if (accGvnHeaderFromDate == "" || accGvnHeaderFromDate == null) {

				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();

				String curTimeStamp = dateFormat.format(cal.getTime());
				System.out.println("Cur time Stamp= " + curTimeStamp);

				cal.set(Calendar.HOUR, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 1);
				String initialTimeStamp = dateFormat.format(cal.getTime());
				System.out.println("initialTime time Stamp= " + initialTimeStamp);

				String statusList = new String();

				statusList = "4" + "," + "8";
				// for Sending Current Date
				java.util.Date date = new java.util.Date();

				DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				accGvnHeaderFromDate = df.format(date);
				accGvnHeaderToDate = df.format(date);
				System.out.println("From Date And :" + accGvnHeaderFromDate + "ToDATE" + accGvnHeaderToDate);

				map = new LinkedMultiValueMap<String, Object>();

				// map.add("initTime", initialTimeStamp);
				// map.add("curTime", curTimeStamp);
				map.add("statusList", statusList);
				map.add("fromDate", accGvnHeaderFromDate);// ie current date
				map.add("toDate", accGvnHeaderToDate);// ie current date
				map.add("isGrn", "0" + "," + "2");

				gvnAccHeaderList = new ArrayList<>();

				headerList = restTemplate.postForObject(Constants.url + "findGrnGvnHeaderOnLoad", map,
						GrnGvnHeaderList.class);

				gvnAccHeaderList = headerList.getGrnGvnHeader();

				System.out.println("Grn Gate Header List ON load  " + gvnAccHeaderList.toString());

			} // end of if onload call

			else {

				if (frList.contains("-1")) {
					isAllFrSelected = true;

				}

				if (isAllFrSelected) {
					// all Fr selected Web Service
					System.out.println("All Fr Selected =true");

					map = new LinkedMultiValueMap<String, Object>();

					map.add("fromDate", accGvnHeaderFromDate);// ie current date
					map.add("toDate", accGvnHeaderToDate);// ie current date
					map.add("isGrn", "0" + "," + "2");

					gvnAccHeaderList = new ArrayList<>();

					headerList = restTemplate.postForObject(Constants.url + "getGrnGvnHeaderAllFr", map,
							GrnGvnHeaderList.class);

					gvnAccHeaderList = headerList.getGrnGvnHeader();

					System.out.println("Grn Gate Header List  All FR" + gvnAccHeaderList.toString());

				} else {

					System.out.println("Specific Fr Selected ");

					map = new LinkedMultiValueMap<String, Object>();

					map.add("frIdList", frSelectedAccGvnHeader);
					map.add("fromDate", accGvnHeaderFromDate);
					map.add("toDate", accGvnHeaderToDate);
					map.add("isGrn", "0" + "," + "2");

					gvnAccHeaderList = new ArrayList<>();

					headerList = restTemplate.postForObject(Constants.url + "getGrnGvnHeader", map,
							GrnGvnHeaderList.class);

					gvnAccHeaderList = headerList.getGrnGvnHeader();

					System.out.println("Grn Acc Header List  specific FR " + gvnAccHeaderList.toString());
				}

			} // End of else

			model.addObject("fromDate", accGvnHeaderFromDate);
			model.addObject("toDate", accGvnHeaderToDate);
			model.addObject("gvnList", gvnAccHeaderList);
			model.addObject("selectedFr", frList);

		} catch (Exception e) {

			System.out.println("Excep in Gate Header List /getGvnHeaderForAcc " + e.getMessage());
			e.printStackTrace();
		}

		return model;
	}

	// Get GVN Acc detail
	List<Integer> statusIndexList;

	@RequestMapping(value = "/getAccGvnDetail/{headerId}", method = RequestMethod.GET)
	public ModelAndView getAccGvnDetail(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("headerId") int headerId) {
		ModelAndView modelAndView = null;

		modelAndView = new ModelAndView("grngvn/accGvn");

		System.out.println("He ader " + headerId);

		globalGvnAccHeaderId = headerId;

		RestTemplate restTemplate = new RestTemplate();

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		map.add("grnGvnHeaderId", headerId);
		// getFrGvnDetail
		try {

			GetGrnGvnDetailsList detailList = restTemplate.postForObject(Constants.url + "getFrGvnDetails", map,
					GetGrnGvnDetailsList.class);

			gvnAccDetailList = new ArrayList<>();

			gvnAccDetailList = detailList.getGrnGvnDetails();

			System.out.println("GRN Detail   " + gvnAccDetailList.toString());

			// Ganesh Remrk
			map = new LinkedMultiValueMap<String, Object>();
			map.add("isFrUsed", 0);
			map.add("moduleId", 1);
			map.add("subModuleId", 6);
			getAllRemarksList = restTemplate.postForObject(Constants.url + "/getAllRemarks", map,
					GetAllRemarksList.class);

			getAllRemarks = new ArrayList<>();
			getAllRemarks = getAllRemarksList.getGetAllRemarks();

			System.out.println("remark list " + getAllRemarks.toString());

			GetGrnGvnDetails detail;

			for (int i = 0; i < gvnAccDetailList.size(); i++) {

				detail = new GetGrnGvnDetails();
				detail = gvnAccDetailList.get(i);
				int qty = 0;

				if (detail.getGrnGvnStatus() == 1 || detail.getGrnGvnStatus() == 2 || detail.getGrnGvnStatus() == 3) {
					System.out.println("a] fr Grn Qty ");
					qty = detail.getAprQtyGate();

				}

				if (detail.getGrnGvnStatus() == 4 || detail.getGrnGvnStatus() == 5) {
					System.out.println("b] Gate Grn Qty ");

					qty = detail.getAprQtyStore();

				}

				if (detail.getGrnGvnStatus() == 6 || detail.getGrnGvnStatus() == 7) {
					System.out.println("c] Acc Grn Qty ");
					qty = detail.getAprQtyAcc();

				}

				float grnRate = 0;

				float total = detail.getBaseRate() * qty;

				float aprAmt = 0;
				float aprTotalTax;
				float sgstRs, cgstRs, igstRs;

				float grandTotal;

				float baseRate = detail.getBaseRate();
				float gvnAmt = qty * baseRate;

				float aprTaxableAmt = baseRate * qty;

				aprTotalTax = (aprTaxableAmt * (detail.getSgstPer() + detail.getCgstPer())) / 100;

				grandTotal = aprTaxableAmt + aprTotalTax;

				float finalAmt = detail.getItemRate() * qty;

				float roundUpAmt = finalAmt - grandTotal;

				gvnAmt = roundUp(gvnAmt);

				//

				aprTotalTax = ((aprTaxableAmt) * (detail.getSgstPer() + detail.getCgstPer())) / 100;

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

			} // end of for Loop

			statusIndexList = new ArrayList<Integer>();
			for (int i = 0; i < gvnAccDetailList.size(); i++) {

				System.err.println("In For ");
				if (gvnAccDetailList.get(i).getGrnGvnStatus() == 7 || gvnAccDetailList.get(i).getGrnGvnStatus() == 4) {
					System.err.println("In If ");

					statusIndexList.add(i);

				}
			}

			modelAndView.addObject("remarkList", getAllRemarks);
			
			GrnGvnHeader gateHeader = new GrnGvnHeader();

			for (int i = 0; i < gvnAccHeaderList.size(); i++) {

				if (gvnAccHeaderList.get(i).getGrnGvnHeaderId() == globalGvnAccHeaderId) {

					gateHeader = gvnAccHeaderList.get(i);

					break;

				}
			}
			modelAndView.addObject("srNo", gateHeader.getGrngvnSrno());

		

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("Ex in grn Detail " + e.getMessage());

		}

		String grnDate = gvnAccDetailList.get(0).getGrnGvnDate();
		modelAndView.addObject("gvnList", gvnAccDetailList);
		modelAndView.addObject("grnDate", grnDate);
		modelAndView.addObject("url",Constants.GVN_IMAGE_URL);

		return modelAndView;

	}

	@RequestMapping(value = "/getGvnStatus", method = RequestMethod.GET)
	public @ResponseBody List<Integer> getGvnStatus(HttpServletRequest request, HttpServletResponse response) {

		return statusIndexList;

	}

	// A] --//Acc Gvn Process Agree

	@RequestMapping(value = "/insertAccGvnProcessAgree", method = RequestMethod.GET)
	public String insertAccGrnProcessAgree(HttpServletRequest request, HttpServletResponse response) {

		System.out.println("using a href to call insert account agree ");

		ModelAndView model = new ModelAndView("grngvn/accGrn");

		try {

			int grnId = Integer.parseInt(request.getParameter("grnId"));
			int accGvnQty = Integer.parseInt(request.getParameter("acc_gvn_qty"));
			// int accApproveLogin = //
			// Integer.parseInt(request.getParameter("approveAccLogin"));
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

			for (int i = 0; i < gvnAccDetailList.size(); i++) {

				if (gvnAccDetailList.get(i).getGrnGvnId() == grnId) {

					System.out.println("GRN ID MAtched " + grnId);

					detail = gvnAccDetailList.get(i);

					float grnRate = 0;

					float total = detail.getBaseRate() * accGvnQty;

					float aprAmt = 0;
					float aprTotalTax;
					float sgstRs, cgstRs, igstRs;

					float grandTotal;

					//

					float baseRate = detail.getBaseRate();
					float gvnAmt = accGvnQty * baseRate;

					float aprTaxableAmt = baseRate * accGvnQty;

					aprTotalTax = (aprTaxableAmt * (detail.getSgstPer() + detail.getCgstPer())) / 100;

					grandTotal = aprTaxableAmt + aprTotalTax;

					float finalAmt = detail.getItemRate() * accGvnQty;

					float roundUpAmt = finalAmt - grandTotal;

					gvnAmt = roundUp(gvnAmt);

					//

					aprTotalTax = ((aprTaxableAmt) * (detail.getSgstPer() + detail.getCgstPer())) / 100;

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

					data.setAprQtyAcc(accGvnQty);

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

					gvnAccDetailList.set(i, detail);

					break;

				} // end of matching grnId
			} // end of for Loop
			Info info = restTemplate.postForObject(Constants.url + "updateAccGrn", dataList, Info.class);

			// new code

			if (info.getError() == false) {

				for (int i = 0; i < gvnAccDetailList.size(); i++) {

					if (grnId == gvnAccDetailList.get(i).getGrnGvnId()) {

						gvnAccDetailList.get(i).setGrnGvnStatus(Constants.AP_BY_ACC);

						System.out.println("Record Updated Locally  apr");
						break;

					}
				}

			}

			GrnGvnHeader accHeader = new GrnGvnHeader();

			for (int i = 0; i < gvnAccHeaderList.size(); i++) {

				if (gvnAccHeaderList.get(i).getGrnGvnHeaderId() == globalGvnAccHeaderId) {

					accHeader = gvnAccHeaderList.get(i);

					break;

				}
			}

			if (accHeader.getGrngvnStatus() == 4 || accHeader.getGrngvnStatus() == 6
					|| accHeader.getGrngvnStatus() == 7) {

				int rejStatus = 0, aprStatus = 0;

				for (int i = 0; i < gvnAccDetailList.size(); i++) {

					// setting approve and rejected count
					if (gvnAccDetailList.get(i).getGrnGvnStatus() == 6) {
						aprStatus = aprStatus + 1;
					} else if (gvnAccDetailList.get(i).getGrnGvnStatus() == 7) {

						rejStatus = rejStatus + 1;
					}

				} // end of for Loop

				if (gvnAccDetailList.size() == aprStatus) {

					System.out.println("list Size " + aprStatus);
					accHeader.setGrngvnStatus(6);

				}

				else if (gvnAccDetailList.size() == rejStatus) {
					accHeader.setGrngvnStatus(7);
				} else {

					accHeader.setGrngvnStatus(8);
				}

			} // End of If headerStatus==4,6,7

			else if (accHeader.getGrngvnStatus() == 8) {

				int rejStatus = 0, aprStatus = 0;

				for (int i = 0; i < gvnAccDetailList.size(); i++) {

					// setting approve and rejected count
					if (gvnAccDetailList.get(i).getGrnGvnStatus() == 3 || gvnAccDetailList.get(i).getGrnGvnStatus() == 5
							|| gvnAccDetailList.get(i).getGrnGvnStatus() == 7) {

						rejStatus = rejStatus + 1;

					} else if (gvnAccDetailList.get(i).getGrnGvnStatus() == 6) {

						aprStatus = aprStatus + 1;
					}

				} // end of for Loop

				if (rejStatus == gvnAccDetailList.size()) {

					accHeader.setGrngvnStatus(7);
				}

				else if (aprStatus == gvnAccDetailList.size()) {

					accHeader.setGrngvnStatus(6);

				} else {

					accHeader.setGrngvnStatus(8);

				}
			} // End of Else header status==8
				// Update Grn Gvn Header

			accHeader.setAprTaxableAmt(0);
			accHeader.setAprTotalTax(0);
			accHeader.setAprGrandTotal(0);
			accHeader.setAprCgstRs(0);
			accHeader.setAprSgstRs(0);
			accHeader.setAprIgstRs(0);
			accHeader.setAprROff(0);
			accHeader.setApporvedAmt(0);

			for (int i = 0; i < gvnAccDetailList.size(); i++) {

				if (gvnAccDetailList.get(i).getGrnGvnStatus() == 6) {

					System.out
							.println("Status 6 found  and its total :::" + gvnAccDetailList.get(i).getAprGrandTotal());

					accHeader.setApporvedAmt(accHeader.getApporvedAmt() + gvnAccDetailList.get(i).getAprGrandTotal());

					accHeader.setAprTaxableAmt(
							accHeader.getAprTaxableAmt() + gvnAccDetailList.get(i).getAprTaxableAmt());
					accHeader.setAprTotalTax(accHeader.getAprTotalTax() + gvnAccDetailList.get(i).getAprTotalTax());
					accHeader.setAprGrandTotal(
							accHeader.getAprGrandTotal() + gvnAccDetailList.get(i).getAprGrandTotal());
					accHeader.setAprCgstRs(accHeader.getAprCgstRs() + gvnAccDetailList.get(i).getAprCgstRs());
					accHeader.setAprSgstRs(accHeader.getAprSgstRs() + gvnAccDetailList.get(i).getAprSgstRs());
					accHeader.setAprIgstRs(accHeader.getAprIgstRs() + gvnAccDetailList.get(i).getAprIgstRs());
					accHeader.setAprROff(accHeader.getAprROff() + gvnAccDetailList.get(i).getAprROff());

					// accHeader.setApporvedAmt(0);

				}
			}

			accHeader.setApprovedDatetime(dateFormat.format(cal.getTime()));

			accHeader = restTemplate.postForObject(Constants.url + "updateGrnGvnHeader", accHeader, GrnGvnHeader.class);

			System.out.println("GRN HEADER rESPONSE " + accHeader.toString());

			//
			
			//-----------------------For Notification-----------------
			String frToken="";
		
			try {
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				  map.add("frId",accHeader.getFrId());
				   
                 frToken= restTemplate.postForObject(Constants.url+"getFrToken", map, String.class);
		         Firebase.sendPushNotifForCommunication(frToken,"","GVN no"+accHeader.getGrngvnSrno()+"punched by you has been cleared by Accounts"+accHeader.getAprGrandTotal() + " Thank You..Team Monginis","inbox");
		   	
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

		return "redirect:/getAccGvnDetail";

	}

	// B] --//insertAccGvnProcessDisAgree

	@RequestMapping(value = "/insertAccGvnProcessDisAgree", method = RequestMethod.GET)
	public String insertAccGvnProcessDisAgree(HttpServletRequest request, HttpServletResponse response) {

		System.out.println("using a href to call insert account dis agree ");

		// ModelAndView model = new ModelAndView("grngvn/accGrn");

		try {

			int grnId = Integer.parseInt(request.getParameter("grnId"));

			// int accApproveLogin = //
			// Integer.parseInt(request.getParameter("approveAccLogin"));
			HttpSession session = request.getSession();
			UserResponse userResponse = (UserResponse) session.getAttribute("UserDetail");
			int accApproveLogin = userResponse.getUser().getId();
			int accGvnQty = Integer.parseInt(request.getParameter("acc_gvn_qty"));
			String accRemark = request.getParameter("accRemark");

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			System.out.println("************* Date Time " + dateFormat.format(cal.getTime()));

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			List<TempGrnGvnBeanUp> dataList = new ArrayList<>();

			TempGrnGvnBeanUp data = new TempGrnGvnBeanUp();

			GetGrnGvnDetails detail = new GetGrnGvnDetails();

			for (int i = 0; i < gvnAccDetailList.size(); i++) {

				if (gvnAccDetailList.get(i).getGrnGvnId() == grnId) {

					System.out.println("GRN ID MAtched " + grnId);

					detail = gvnAccDetailList.get(i);

					float grnRate = 0;

					float total = detail.getBaseRate() * accGvnQty;

					float aprAmt = 0;
					float aprTotalTax;
					float sgstRs, cgstRs, igstRs;

					float grandTotal;

					//

					float baseRate = detail.getBaseRate();
					float gvnAmt = accGvnQty * baseRate;

					float aprTaxableAmt = baseRate * accGvnQty;

					aprTotalTax = (aprTaxableAmt * (detail.getSgstPer() + detail.getCgstPer())) / 100;

					grandTotal = aprTaxableAmt + aprTotalTax;

					float finalAmt = detail.getItemRate() * accGvnQty;

					float roundUpAmt = finalAmt - grandTotal;

					gvnAmt = roundUp(gvnAmt);

					//

					aprTotalTax = ((aprTaxableAmt) * (detail.getSgstPer() + detail.getCgstPer())) / 100;

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

					data.setAprQtyAcc(accGvnQty);

					data.setAprCgstRs(roundUp(cgstRs));
					data.setAprIgstRs(roundUp(igstRs));
					data.setAprSgstRs(roundUp(sgstRs));

					data.setAprTaxableAmt(roundUp(aprTaxableAmt));
					data.setAprTotalTax(roundUp(aprTotalTax));
					// data.setGrnGvnId(grnId);

					data.setAprGrandTotal(roundUp(grandTotal));

					dataList.add(data);

					detail.setAprGrandTotal(data.getAprGrandTotal());
					detail.setAprTaxableAmt(data.getAprTaxableAmt());
					detail.setAprTotalTax(data.getAprTotalTax());

					detail.setAprCgstRs(data.getAprCgstRs());
					detail.setAprSgstRs(data.getAprSgstRs());
					detail.setAprIgstRs(data.getAprIgstRs());

					detail.setAprROff(data.getAprROff());

					gvnAccDetailList.set(i, detail);

					break;

				} // end of matching grnId
			} // end of for Loop
			Info info = restTemplate.postForObject(Constants.url + "updateAccGrn", dataList, Info.class);

			// new code

			if (info.getError() == false) {

				for (int i = 0; i < gvnAccDetailList.size(); i++) {

					if (grnId == gvnAccDetailList.get(i).getGrnGvnId()) {

						gvnAccDetailList.get(i).setGrnGvnStatus(Constants.DIS_BY_ACC);
						System.out.println("Record Updated Locally  apr");
						break;

					}
				}

			}

			GrnGvnHeader accHeader = new GrnGvnHeader();

			for (int i = 0; i < gvnAccHeaderList.size(); i++) {

				if (gvnAccHeaderList.get(i).getGrnGvnHeaderId() == globalGvnAccHeaderId) {

					accHeader = gvnAccHeaderList.get(i);

					break;

				}
			}

			if (accHeader.getGrngvnStatus() == 4 || accHeader.getGrngvnStatus() == 6
					|| accHeader.getGrngvnStatus() == 7) {

				int rejStatus = 0, aprStatus = 0;

				for (int i = 0; i < gvnAccDetailList.size(); i++) {

					// setting approve and rejected count
					if (gvnAccDetailList.get(i).getGrnGvnStatus() == 6) {
						aprStatus = aprStatus + 1;
					} else if (gvnAccDetailList.get(i).getGrnGvnStatus() == 7) {

						rejStatus = rejStatus + 1;
					}

				} // end of for Loop

				if (gvnAccDetailList.size() == aprStatus) {

					System.out.println("list Size " + aprStatus);
					accHeader.setGrngvnStatus(6);

				}

				else if (gvnAccDetailList.size() == rejStatus) {
					accHeader.setGrngvnStatus(7);
				} else {

					accHeader.setGrngvnStatus(8);
				}

			} // End of If headerStatus==4,6,7

			else if (accHeader.getGrngvnStatus() == 8) {

				int rejStatus = 0, aprStatus = 0;

				for (int i = 0; i < gvnAccDetailList.size(); i++) {

					// setting approve and rejected count
					if (gvnAccDetailList.get(i).getGrnGvnStatus() == 3 || gvnAccDetailList.get(i).getGrnGvnStatus() == 5
							|| gvnAccDetailList.get(i).getGrnGvnStatus() == 7) {

						rejStatus = rejStatus + 1;

					} else if (gvnAccDetailList.get(i).getGrnGvnStatus() == 6) {

						aprStatus = aprStatus + 1;
					}

				} // end of for Loop

				if (rejStatus == gvnAccDetailList.size()) {

					accHeader.setGrngvnStatus(7);
				}

				else if (aprStatus == gvnAccDetailList.size()) {

					accHeader.setGrngvnStatus(6);

				} else {

					accHeader.setGrngvnStatus(8);

				}
			} // End of Else header status==8

			// Update Grn Gvn Header

			accHeader.setAprTaxableAmt(0);
			accHeader.setAprTotalTax(0);
			accHeader.setAprGrandTotal(0);
			accHeader.setAprCgstRs(0);
			accHeader.setAprSgstRs(0);
			accHeader.setAprIgstRs(0);
			accHeader.setAprROff(0);
			accHeader.setApporvedAmt(0);

			for (int i = 0; i < gvnAccDetailList.size(); i++) {

				if (gvnAccDetailList.get(i).getGrnGvnStatus() == 6) {

					System.out
							.println("Status 6 found  and its total :::" + gvnAccDetailList.get(i).getAprGrandTotal());

					accHeader.setApporvedAmt(accHeader.getApporvedAmt() + gvnAccDetailList.get(i).getAprGrandTotal());

					accHeader.setAprTaxableAmt(
							accHeader.getAprTaxableAmt() + gvnAccDetailList.get(i).getAprTaxableAmt());
					accHeader.setAprTotalTax(accHeader.getAprTotalTax() + gvnAccDetailList.get(i).getAprTotalTax());
					accHeader.setAprGrandTotal(
							accHeader.getAprGrandTotal() + gvnAccDetailList.get(i).getAprGrandTotal());
					accHeader.setAprCgstRs(accHeader.getAprCgstRs() + gvnAccDetailList.get(i).getAprCgstRs());
					accHeader.setAprSgstRs(accHeader.getAprSgstRs() + gvnAccDetailList.get(i).getAprSgstRs());
					accHeader.setAprIgstRs(accHeader.getAprIgstRs() + gvnAccDetailList.get(i).getAprIgstRs());
					accHeader.setAprROff(accHeader.getAprROff() + gvnAccDetailList.get(i).getAprROff());

				}
			}
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
		         Firebase.sendPushNotifForCommunication(frToken,"","GVN no"+accHeader.getGrngvnSrno()+"punched by you has been rejected by Accounts" + " Thank You..Team Monginis","inbox");
		   	
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

		return "redirect:/getAccGvnDetail";

	}

	// C] -- //insertAccGvnByCheckBoxes

	@RequestMapping(value = "/insertAccGvnByCheckBoxes", method = RequestMethod.POST) // Using checkboxes to insert
	public String insertAccGvnByCheckBoxes(HttpServletRequest request, HttpServletResponse response) {
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

		
			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			String gIds = new String();
			for (int i = 0; i < grnIdList.length; i++) {

				gIds = gIds + "," + grnIdList[i];

			}
			gIds = gIds.substring(1);

			List<TempGrnGvnBeanUp> dataList = new ArrayList<>();

			TempGrnGvnBeanUp data = new TempGrnGvnBeanUp();

			GetGrnGvnDetails detail = new GetGrnGvnDetails();

			for (int j = 0; j < grnIdList.length; j++) {

				int accGvnQty = Integer.parseInt(request.getParameter("acc_gvn_qty" + grnIdList[j]));

				for (int i = 0; i < gvnAccDetailList.size(); i++) {

					if (gvnAccDetailList.get(i).getGrnGvnId() == Integer.parseInt(grnIdList[j])) {

						// System.out.println("GRN ID MAtched " + grnIdList[i]);

						detail = gvnAccDetailList.get(i);

						float grnRate = 0;

						float total = detail.getBaseRate() * accGvnQty;

						float aprAmt = 0;
						float aprTotalTax;
						float sgstRs, cgstRs, igstRs;

						float grandTotal;

						//

						float baseRate = detail.getBaseRate();
						float gvnAmt = accGvnQty * baseRate;

						float aprTaxableAmt = baseRate * accGvnQty;

						aprTotalTax = (aprTaxableAmt * (detail.getSgstPer() + detail.getCgstPer())) / 100;

						grandTotal = aprTaxableAmt + aprTotalTax;

						float finalAmt = detail.getItemRate() * accGvnQty;

						float roundUpAmt = finalAmt - grandTotal;

						gvnAmt = roundUp(gvnAmt);

						//

						aprTotalTax = ((aprTaxableAmt) * (detail.getSgstPer() + detail.getCgstPer())) / 100;

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

						data.setGrnGvnId(Integer.parseInt(grnIdList[j]));

						data.setAprQtyAcc(accGvnQty);

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

						gvnAccDetailList.set(i, detail);

						dataList.add(data);

					} // end of matching grnId
				} // end of for Loop
			}
			System.out.println("GIDS " + gIds);

			Info updateAccGrn = restTemplate.postForObject(Constants.url + "updateAccGrn", dataList, Info.class);

			if (updateAccGrn.getError() == false) {

				for (int i = 0; i < grnIdList.length; i++) {

					for (int j = 0; j < gvnAccDetailList.size(); j++) {

						if (Integer.parseInt(grnIdList[i]) == gvnAccDetailList.get(j).getGrnGvnId()) {

							gvnAccDetailList.get(j).setGrnGvnStatus(Constants.AP_BY_ACC);
							System.out.println("Record Updated Locally  apr");

						}
					}
				}
			}

			GrnGvnHeader accHeader = new GrnGvnHeader();

			for (int i = 0; i < gvnAccHeaderList.size(); i++) {

				if (gvnAccHeaderList.get(i).getGrnGvnHeaderId() == globalGvnAccHeaderId) {

					accHeader = gvnAccHeaderList.get(i);

					break;

				}
			}

			if (accHeader.getGrngvnStatus() == 4 || accHeader.getGrngvnStatus() == 6
					|| accHeader.getGrngvnStatus() == 7) {

				int rejStatus = 0, aprStatus = 0;

				for (int i = 0; i < gvnAccDetailList.size(); i++) {

					// setting approve and rejected count
					if (gvnAccDetailList.get(i).getGrnGvnStatus() == 6) {
						aprStatus = aprStatus + 1;
					} else if (gvnAccDetailList.get(i).getGrnGvnStatus() == 7) {

						rejStatus = rejStatus + 1;
					}

				} // end of for Loop

				if (gvnAccDetailList.size() == aprStatus) {

					System.out.println("list Size " + aprStatus);
					accHeader.setGrngvnStatus(6);

				}

				else if (gvnAccDetailList.size() == rejStatus) {
					accHeader.setGrngvnStatus(7);
				} else {

					accHeader.setGrngvnStatus(8);
				}

			} // End of If headerStatus==4,6,7

			else if (accHeader.getGrngvnStatus() == 8) {

				int rejStatus = 0, aprStatus = 0;

				for (int i = 0; i < gvnAccDetailList.size(); i++) {

					// setting approve and rejected count
					if (gvnAccDetailList.get(i).getGrnGvnStatus() == 3 || gvnAccDetailList.get(i).getGrnGvnStatus() == 5
							|| gvnAccDetailList.get(i).getGrnGvnStatus() == 7) {

						rejStatus = rejStatus + 1;

					} else if (gvnAccDetailList.get(i).getGrnGvnStatus() == 6) {

						aprStatus = aprStatus + 1;
					}

				} // end of for Loop

				if (rejStatus == gvnAccDetailList.size()) {

					accHeader.setGrngvnStatus(7);
				}

				else if (aprStatus == gvnAccDetailList.size()) {

					accHeader.setGrngvnStatus(6);

				} else {

					accHeader.setGrngvnStatus(8);

				}
			} // End of Else header status==8

			// Update Grn Gvn Header

			accHeader.setAprTaxableAmt(0);
			accHeader.setAprTotalTax(0);
			accHeader.setAprGrandTotal(0);
			accHeader.setAprCgstRs(0);
			accHeader.setAprSgstRs(0);
			accHeader.setAprIgstRs(0);
			accHeader.setAprROff(0);
			accHeader.setApporvedAmt(0);

			for (int i = 0; i < gvnAccDetailList.size(); i++) {

				if (gvnAccDetailList.get(i).getGrnGvnStatus() == 6) {

					System.out
							.println("Status 6 found  and its total :::" + gvnAccDetailList.get(i).getAprGrandTotal());

					accHeader.setApporvedAmt(accHeader.getAprGrandTotal() + gvnAccDetailList.get(i).getAprGrandTotal());

					accHeader.setAprTaxableAmt(
							accHeader.getAprTaxableAmt() + gvnAccDetailList.get(i).getAprTaxableAmt());
					accHeader.setAprTotalTax(accHeader.getAprTotalTax() + gvnAccDetailList.get(i).getAprTotalTax());
					accHeader.setAprGrandTotal(
							accHeader.getAprGrandTotal() + gvnAccDetailList.get(i).getAprGrandTotal());
					accHeader.setAprCgstRs(accHeader.getAprCgstRs() + gvnAccDetailList.get(i).getAprCgstRs());
					accHeader.setAprSgstRs(accHeader.getAprSgstRs() + gvnAccDetailList.get(i).getAprSgstRs());
					accHeader.setAprIgstRs(accHeader.getAprIgstRs() + gvnAccDetailList.get(i).getAprIgstRs());
					accHeader.setAprROff(accHeader.getAprROff() + gvnAccDetailList.get(i).getAprROff());

				}
			}
			accHeader.setApprovedDatetime(dateFormat.format(cal.getTime()));

			System.out.println("Approved Grand Total setted " + accHeader.getAprGrandTotal());

			GrnGvnHeader accHeaderRes = restTemplate.postForObject(Constants.url + "updateGrnGvnHeader", accHeader,
					GrnGvnHeader.class);

			System.out.println("GRN HEADER rESPONSE " + accHeaderRes.toString());
			
			

			//-----------------------For Notification-----------------
			String frToken="";
		
			try {
				map = new LinkedMultiValueMap<String, Object>();
				  map.add("frId",accHeader.getFrId());
				   
                 frToken= restTemplate.postForObject(Constants.url+"getFrToken", map, String.class);
		         Firebase.sendPushNotifForCommunication(frToken,"","GVN no"+accHeader.getGrngvnSrno()+"punched by you has been cleared by Accounts"+accHeader.getAprGrandTotal() + " Thank You..Team Monginis","inbox");
		   	
		        }
		        catch(Exception e2)
		        {
			      e2.printStackTrace();
		        }
			
			//-----------------------------------------------------


			//

		} catch (Exception e) {

			System.out.println("Error in update Acc Grn Agree " + e.getMessage());

			e.printStackTrace();

		}

		return "redirect:/getAccGvnDetail/" + globalGvnAccHeaderId;
	}

	public static float roundUp(float d) {
		return BigDecimal.valueOf(d).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
	}
}
