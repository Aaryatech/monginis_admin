package com.ats.adminpanel.controller;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
import com.ats.adminpanel.model.AllFrIdName;
import com.ats.adminpanel.model.AllFrIdNameList;
import com.ats.adminpanel.model.AllRoutesListResponse;
import com.ats.adminpanel.model.GenerateBill;
import com.ats.adminpanel.model.GenerateBillList;
import com.ats.adminpanel.model.GetSellBillDetail;
import com.ats.adminpanel.model.GetSellBillHeader;
import com.ats.adminpanel.model.Info;
import com.ats.adminpanel.model.Route;
import com.ats.adminpanel.model.billing.GetBillDetail;
import com.ats.adminpanel.model.billing.GetBillDetailsResponse;
import com.ats.adminpanel.model.billing.GetBillHeader;
import com.ats.adminpanel.model.billing.GetBillHeaderResponse;
import com.ats.adminpanel.model.billing.PostBillDataCommon;
import com.ats.adminpanel.model.billing.PostBillDetail;
import com.ats.adminpanel.model.billing.PostBillHeader;
import com.ats.adminpanel.model.franchisee.AllMenuResponse;
import com.ats.adminpanel.model.franchisee.FrNameIdByRouteId;
import com.ats.adminpanel.model.franchisee.FrNameIdByRouteIdResponse;
import com.ats.adminpanel.model.franchisee.Menu;
import com.ats.adminpanel.model.item.CategoryListResponse;
import com.ats.adminpanel.model.item.MCategoryList;
import com.ats.adminpanel.model.modules.ErrorMessage;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

@Controller
public class BillController {

	private static final Logger logger = LoggerFactory.getLogger(BillController.class);

	AllFrIdNameList allFrIdNameList = new AllFrIdNameList();
	List<Menu> menuList = new ArrayList<Menu>();
	String selectedFrArray;
	String selectedDate;

	GenerateBillList generateBillList = new GenerateBillList();
	List<String> frList = new ArrayList<>();
	


	public static List<GetBillDetail> billDetailsList;
	public GetBillHeader getBillHeader;

	List<GetBillHeader> billHeadersList = new ArrayList<>();

	List<GetSellBillHeader> getSellBillHeaderList;
	List<GetSellBillDetail>getSellBillDetailList;
	
	
	

	
	@RequestMapping(value = "/submitNewBill", method = RequestMethod.POST)
	public String submitNewBill(HttpServletRequest request, HttpServletResponse response) {

		logger.info("/submitNewBill request mapping.");

		DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
		Date billDate = null;
		try {
			billDate = DF.parse(selectedDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Bill Date " + billDate);

		ModelAndView model = new ModelAndView("billing/generatebill");
		Constants.mainAct = 8;
		Constants.subAct = 81;

		PostBillDataCommon postBillDataCommon = new PostBillDataCommon();

		GenerateBillList generateBillListNew = generateBillList;
		List<GenerateBill> tempGenerateBillList = generateBillListNew.getGenerateBills();
		List<PostBillHeader> postBillHeaderList = new ArrayList<PostBillHeader>();

		Set<Integer> set = new HashSet();
		for (int i = 0; i < tempGenerateBillList.size(); i++) {

			set.add(tempGenerateBillList.get(i).getFrId());

		}

		List<Integer> frIdList = new ArrayList(set);
		List<PostBillDetail> postBillDetailsList = new ArrayList();

		for (int i = 0; i < frIdList.size(); i++) {

			PostBillHeader header = new PostBillHeader();

			int frId = frIdList.get(i);

			System.out.println("Outer For frId " + frId);

			header.setFrId(frId);
			postBillDetailsList = new ArrayList();

			float sumTaxableAmt = 0, sumTotalTax = 0, sumGrandTotal = 0;

			for (int j = 0; j < tempGenerateBillList.size(); j++) {

				GenerateBill gBill = tempGenerateBillList.get(j);

				System.out.println("Inner For frId " + gBill.getFrId());

				if (gBill.getFrId() == frId) {

					System.out.println("If condn true " + gBill.getFrId());

					PostBillDetail billDetail = new PostBillDetail();

					String billQty = request.getParameter("" + "billQty" + tempGenerateBillList.get(j).getOrderId());

					System.out.println(
							"Bill qty for id:" + tempGenerateBillList.get(j).getOrderId() + " is = " + billQty);

					// billQty = String.valueOf(gBill.getOrderQty());
					Float orderRate = (float) gBill.getOrderRate();
					Float tax1 = (float) gBill.getItemTax1();
					Float tax2 = (float) gBill.getItemTax2();
					Float tax3 = (float) gBill.getItemTax3();

					Float baseRate = (orderRate * 100) / (100 + (tax1 + tax2));
					baseRate = roundUp(baseRate);

					Float taxableAmt = (float) (baseRate * Integer.parseInt(billQty));
					taxableAmt = roundUp(taxableAmt);

					float sgstRs = (taxableAmt * tax1) / 100;
					float cgstRs = (taxableAmt * tax2) / 100;
					float igstRs = (taxableAmt * tax3) / 100;

					sgstRs = roundUp(sgstRs);
					cgstRs = roundUp(cgstRs);
					igstRs = roundUp(igstRs);

					Float totalTax = sgstRs + cgstRs;
					totalTax = roundUp(totalTax);

					Float grandTotal = totalTax + taxableAmt;
					grandTotal = roundUp(grandTotal);

					sumTaxableAmt = sumTaxableAmt + taxableAmt;
					sumTaxableAmt = roundUp(sumTaxableAmt);

					sumTotalTax = sumTotalTax + totalTax;
					sumTotalTax = roundUp(sumTotalTax);

					sumGrandTotal = sumGrandTotal + grandTotal;
					sumGrandTotal = roundUp(sumGrandTotal);

					billDetail.setOrderId(tempGenerateBillList.get(j).getOrderId());
					billDetail.setMenuId(gBill.getMenuId());
					billDetail.setCatId(gBill.getCatId());
					billDetail.setItemId(gBill.getItemId());
					billDetail.setOrderQty(gBill.getOrderQty());
					billDetail.setBillQty(Integer.parseInt(billQty));
					billDetail.setMrp((float) gBill.getOrderMrp());
					billDetail.setRateType(gBill.getRateType());
					billDetail.setRate((float) gBill.getOrderRate());
					billDetail.setBaseRate(baseRate);
					billDetail.setTaxableAmt(taxableAmt);
					billDetail.setSgstPer(tax1);
					billDetail.setSgstRs(sgstRs);
					billDetail.setCgstPer(tax2);
					billDetail.setCgstRs(cgstRs);
					billDetail.setIgstPer(tax3);
					billDetail.setIgstRs(igstRs);
					billDetail.setTotalTax(totalTax);
					billDetail.setGrandTotal(grandTotal);
					billDetail.setRemark("");
					billDetail.setDelStatus(0);
					billDetail.setIsGrngvnApplied(0);
					
					billDetail.setGrnType(gBill.getGrnType());//newly added
					
					int itemShelfLife=gBill.getItemShelfLife();
					
					String deliveryDate=gBill.getDeliveryDate();
					System.out.println("delivery date from order ************"+deliveryDate);
					System.out.println("item shelf life******** "+itemShelfLife);
					
					String calculatedDate=incrementDate(deliveryDate,itemShelfLife);
					
					System.out.println("calculatedDate date************ ="+calculatedDate);
					
					DateFormat Df = new SimpleDateFormat("dd-MM-yyyy");
					//DateFormat Df = new SimpleDateFormat("yyyy-MM-dd");

					Date expiryDate = null;
					try {
						expiryDate = Df.parse(calculatedDate);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("Expiry date**************** ="+expiryDate);
					billDetail.setExpiryDate(expiryDate);
					
					postBillDetailsList.add(billDetail);

					System.out.println("New Detail Object " + billDetail.toString());

					header.setFrCode(gBill.getFrCode());
					header.setBillDate(billDate);
					header.setRemark("");
					header.setTaxApplicable((int) (gBill.getItemTax1() + gBill.getItemTax2()));

				}

			   }

			header.setTaxableAmt(sumTaxableAmt);
			header.setGrandTotal(sumGrandTotal);
			header.setTotalTax(sumTotalTax);
			header.setStatus(1);
			header.setPostBillDetailsList(postBillDetailsList);

			postBillHeaderList.add(header);

		}

		postBillDataCommon.setPostBillHeadersList(postBillHeaderList);

		System.out.println("Test data : " + postBillDataCommon.toString());

		RestTemplate restTemplate = new RestTemplate();

		Info info = restTemplate.postForObject(Constants.url + "insertBillData", postBillDataCommon, Info.class);

		System.out.println("Info Data " + info.toString());

		return "redirect:/showGenerateBill";
		
		
	}

	
	
	public String incrementDate(String date, int day) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(date));

		} catch (ParseException e) {
			System.out.println("Exception while incrementing date " + e.getMessage());
			e.printStackTrace();
		}
		c.add(Calendar.DATE, day); // number of days to add
		date = sdf.format(c.getTime());

		return date;

	}
	
	
	public static float roundUp(float d) {
		return BigDecimal.valueOf(d).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
	}

	@RequestMapping(value = "/showGenerateBill")
	public ModelAndView showGenerateBill(HttpServletRequest request, HttpServletResponse response) {

		logger.info("/showGenerateBill request mapping.");

		ModelAndView model = new ModelAndView("billing/generatebill");
		Constants.mainAct = 8;
		Constants.subAct = 82;

		ZoneId z = ZoneId.of("Asia/Calcutta");

		LocalDate date = LocalDate.now(z);
		DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
		String todaysDate = date.format(formatters);

		RestTemplate restTemplate = new RestTemplate();

		AllMenuResponse allMenuResponse = restTemplate.getForObject(Constants.url + "getAllMenu",
				AllMenuResponse.class);

		menuList = allMenuResponse.getMenuConfigurationPage();
		allFrIdNameList = new AllFrIdNameList();
		try {

			allFrIdNameList = restTemplate.getForObject(Constants.url + "getAllFrIdName", AllFrIdNameList.class);

		} catch (Exception e) {
			System.out.println("Exception in getAllFrIdName" + e.getMessage());
			e.printStackTrace();

		}
		List<AllFrIdName> selectedFrListAll = new ArrayList();
		List<Menu> selectedMenuList = new ArrayList<Menu>();

		System.out.println(" Fr " + allFrIdNameList.getFrIdNamesList());

		model.addObject("todaysDate", todaysDate);
		model.addObject("unSelectedMenuList", menuList);
		model.addObject("unSelectedFrList", allFrIdNameList.getFrIdNamesList());

		return model;
	}

	@RequestMapping(value = "/generateNewBill", method = RequestMethod.GET)
	public @ResponseBody List<GenerateBill> generateNewBill(HttpServletRequest request, HttpServletResponse response) {

		logger.info("/generateNewBill AJAX Call mapping.");

		selectedFrArray = null;

		String selectedFr = request.getParameter("fr_id_list");
		selectedDate = request.getParameter("deliveryDate");
		String selectedMenu = request.getParameter("menu_id_list");

		boolean isAllFrSelected = false;
		boolean isAllMenuSelected = false;

		selectedFr = selectedFr.substring(1, selectedFr.length() - 1);
		selectedFr = selectedFr.replaceAll("\"", "");

		selectedMenu = selectedMenu.substring(1, selectedMenu.length() - 1);
		selectedMenu = selectedMenu.replaceAll("\"", "");

		frList = new ArrayList<>();
		frList = Arrays.asList(selectedFr);

		List<String> menuList = new ArrayList<>();
		menuList = Arrays.asList(selectedMenu);

		if (frList.contains("-1")) {
			isAllFrSelected = true;
		}

		if (menuList.contains("-1")) {
			isAllMenuSelected = true;
		}

		try {

			generateBillList = new GenerateBillList();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			if (isAllFrSelected && isAllMenuSelected) {

				map.add("delDate", selectedDate);

				generateBillList = restTemplate.postForObject(Constants.url + "generateBillForAllFrAllMenu", map,
						GenerateBillList.class);
				System.out.println("generate bill list All Fr All Menu " + generateBillList.toString());

			} else if (isAllMenuSelected) {

				map.add("frId", selectedFr);
				map.add("delDate", selectedDate);

				generateBillList = restTemplate.postForObject("" + Constants.url + "generateBillForAllMenu", map,
						GenerateBillList.class);
				System.out.println("generate bill list  All Menu" + generateBillList.toString());

			} else if (isAllFrSelected) {

				map.add("menuId", selectedMenu);
				map.add("delDate", selectedDate);

				generateBillList = restTemplate.postForObject("" + Constants.url + "generateBillForAllFr", map,
						GenerateBillList.class);
				System.out.println("generate bill list All Fr" + generateBillList.toString());

			} else {

				map.add("frId", selectedFr);
				map.add("menuId", selectedMenu);
				map.add("delDate", selectedDate);

				generateBillList = restTemplate.postForObject("" + Constants.url + "generateBill", map,
						GenerateBillList.class);
				System.out.println("generate bill list " + generateBillList.toString());

			}
		} catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
			e.printStackTrace();
		}

		return generateBillList.getGenerateBills();
	}
	
	
	@RequestMapping(value = "/showBillList", method = RequestMethod.GET)
	public ModelAndView showBillList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("billing/viewbillheader");

		Constants.mainAct = 8;
		Constants.subAct = 83;

		String[] frIds = request.getParameterValues("fr_name[]");
		String fromDate = request.getParameter("from_date");
		String toDate = request.getParameter("to_date");

		// AllFrIdNameList allFrIdNameList = new AllFrIdNameList();
		List<Menu> menuList = new ArrayList<Menu>();

		ZoneId z = ZoneId.of("Asia/Calcutta");

		LocalDate date = LocalDate.now(z);
		DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
		String todaysDate = date.format(formatters);

		RestTemplate restTemplate = new RestTemplate();

		AllMenuResponse allMenuResponse = restTemplate.getForObject(Constants.url + "getAllMenu",
				AllMenuResponse.class);

		menuList = allMenuResponse.getMenuConfigurationPage();

		allFrIdNameList = restTemplate.getForObject(Constants.url + "getAllFrIdName", AllFrIdNameList.class);

		AllRoutesListResponse allRouteListResponse = restTemplate.getForObject(Constants.url + "showRouteList",
				AllRoutesListResponse.class);

		List<Route> routeList = new ArrayList<Route>();

		routeList = allRouteListResponse.getRoute();

		model.addObject("routeList", routeList);
		model.addObject("todaysDate", todaysDate);
		model.addObject("menuList", menuList);
		model.addObject("allFrIdNameList", allFrIdNameList.getFrIdNamesList());

		return model;

	}
	
	
	//List<GetBillHeader> billHeadersList;
		@RequestMapping(value = "/getBillListProcess", method = RequestMethod.GET)
		public @ResponseBody List<GetBillHeader> getBillListProcess(HttpServletRequest request,
				HttpServletResponse response) {

			Constants.mainAct = 8;
			Constants.subAct = 83;

			billHeadersList = new ArrayList<>();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			RestTemplate restTemplate = new RestTemplate();

			String routeId = "0";
			String frIdString = "";

			System.out.println("inside getBillListProcess ajax call");

			frIdString = request.getParameter("fr_id_list");
			String fromDate = request.getParameter("from_date");
			String toDate = request.getParameter("to_date");
			routeId = request.getParameter("route_id");

			System.out.println("routeId= " + routeId);

			boolean isAllFrSelected = false;

			frIdString = frIdString.substring(1, frIdString.length() - 1);
			frIdString = frIdString.replaceAll("\"", "");

			List<String> franchIds = new ArrayList();
			franchIds = Arrays.asList(frIdString);

			System.out.println("fr Id ArrayList " + franchIds.toString());

			if (franchIds.contains("-1")) {
				isAllFrSelected = true;

			}

			if (!routeId.equalsIgnoreCase("0")) {

				map.add("routeId", routeId);

				FrNameIdByRouteIdResponse frNameId = restTemplate.postForObject(Constants.url + "getFrNameIdByRouteId", map,
						FrNameIdByRouteIdResponse.class);

				List<FrNameIdByRouteId> frNameIdByRouteIdList = frNameId.getFrNameIdByRouteIds();

				System.out.println("route wise franchisee " + frNameIdByRouteIdList.toString());

				StringBuilder sbForRouteFrId = new StringBuilder();
				for (int i = 0; i < frNameIdByRouteIdList.size(); i++) {

					sbForRouteFrId = sbForRouteFrId.append(frNameIdByRouteIdList.get(i).getFrId().toString() + ",");

				}

				String strFrIdRouteWise = sbForRouteFrId.toString();
				frIdString = strFrIdRouteWise.substring(0, strFrIdRouteWise.length() - 1);
				System.out.println("fr Id Route WISE = " + frIdString);

			} // end of if

			if (isAllFrSelected) {

				map.add("fromDate", fromDate);
				map.add("toDate", toDate);
				System.out.println("Inside is All fr Selected " + isAllFrSelected);

				GetBillHeaderResponse billHeaderResponse = restTemplate
						.postForObject(Constants.url + "getBillHeaderForAllFr", map, GetBillHeaderResponse.class);

				billHeadersList = billHeaderResponse.getGetBillHeaders();

				System.out.println("bill header  " + billHeadersList.toString());

			} else { // few franchisee selected

				map.add("frId", frIdString);
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				GetBillHeaderResponse billHeaderResponse = restTemplate.postForObject(Constants.url + "getBillHeader", map,
						GetBillHeaderResponse.class);

				billHeadersList = billHeaderResponse.getGetBillHeaders();

			}

			System.out.println("bill header  " + billHeadersList.toString());

			return billHeadersList;

		}

	
		@RequestMapping(value = "/viewBillDetails/{billNo}/{frName}", method = RequestMethod.GET)
		public ModelAndView viewBillDetails(@PathVariable int billNo, @PathVariable String frName) {
			ModelAndView model = new ModelAndView("billing/viewBillDetails");
			try {

				RestTemplate restTemplate = new RestTemplate();

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("billNo", billNo);

				GetBillDetailsResponse billDetailsResponse = restTemplate.postForObject(Constants.url + "getBillDetails",
						map, GetBillDetailsResponse.class);

				billDetailsList = new ArrayList<GetBillDetail>();
				billDetailsList = billDetailsResponse.getGetBillDetails();

				System.out.println(" *** get Bill response  " + billDetailsResponse.getGetBillDetails().toString());
				System.out.println("fr Name==" + frName);
				
				for(int i=0;i<billDetailsList.size();i++) {
					
					model.addObject("billNo", billDetailsList.get(i).getBillNo());
					model.addObject("billDate", billDetailsList.get(i).getBillDate());
					
				}

				model.addObject("frName", frName);
				/*model.addObject("billNo", billDetailsList.get(0).getBillNo());
				model.addObject("billDate", billDetailsList.get(0).getBillDate());*/
				model.addObject("billDetails", billDetailsList);

			} catch (Exception e) {
				System.out.println("bill controller error " + e.getMessage());
				e.printStackTrace();
			}

			return model;

		}
		
		
		
		@RequestMapping(value = "/updateBillDetails/{billNo}/{frName}", method = RequestMethod.GET)
		public ModelAndView updateBillDetails(@PathVariable int billNo, @PathVariable String frName) {
			ModelAndView model = new ModelAndView("billing/editBillDetails");
			try {

				RestTemplate restTemplate = new RestTemplate();

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("billNo", billNo);

				GetBillDetailsResponse billDetailsResponse = restTemplate.postForObject(Constants.url + "getBillDetails",
						map, GetBillDetailsResponse.class);

				billDetailsList = new ArrayList<GetBillDetail>();
				billDetailsList = billDetailsResponse.getGetBillDetails();

				System.out.println(" *** get Bill response  " + billDetailsResponse.getGetBillDetails().toString());
				System.out.println("fr Name==----------" + frName);

				model.addObject("frName", frName);
				model.addObject("billNo", billDetailsList.get(0).getBillNo());
				model.addObject("billDate", billDetailsList.get(0).getBillDate());
				model.addObject("billDetails", billDetailsList);

			} catch (Exception e) {
				System.out.println("bill controller error " + e.getMessage());
			}

			return model;

		}
		
		
		@RequestMapping(value = "/updateBillDetailsProcess", method = RequestMethod.POST)
		public String updateBillDetailsProcess(HttpServletRequest request, HttpServletResponse response) {
			// ModelAndView model = new ModelAndView("billing/editBillDetails");
			System.out.println("It is Process");
			System.out.println("bill Detail list " + billDetailsList.toString());

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			List<Integer> billDetailNoList = new ArrayList<Integer>();
			List<Integer> BilQtyList = new ArrayList<Integer>();
			List<Float> grandTotalList = new ArrayList<Float>();
			List<Float> taxableAmtList = new ArrayList<Float>();

			
			
			int billNo = 0;

			PostBillDataCommon postBillDataCommon = new PostBillDataCommon();
			List<PostBillHeader>postBillHeadersList=new ArrayList<>();
			
			List<PostBillDetail>postBillDetailsList=new ArrayList<>();
			
			float sumTaxableAmt = 0,sumTotalTax=0,sumGrandTotal=0;
			
			PostBillDetail postBillDetail=new PostBillDetail();
			PostBillHeader postBillHeader=new PostBillHeader();
			for (int i = 0; i < billDetailsList.size(); i++) {
				
				Integer newBillQty=Integer.parseInt(request.getParameter("billQty" + billDetailsList.get(i).getBillDetailNo()));

				System.out.println("new bill qty = "+newBillQty);
				
				GetBillDetail getBillDetail=billDetailsList.get(i);
				
				if (getBillDetail.getBillQty() != newBillQty) {

				 postBillDetail=new PostBillDetail();
				
				postBillDetail.setBaseRate(getBillDetail.getBaseRate());
				postBillDetail.setBillDetailNo(getBillDetail.getBillDetailNo());
				
				postBillDetail.setBillNo(getBillDetail.getBillNo());
				postBillDetail.setCatId(getBillDetail.getCatId());
				postBillDetail.setSgstPer(getBillDetail.getSgstPer());
				postBillDetail.setIgstPer(getBillDetail.getIgstPer());
				postBillDetail.setDelStatus(0);
				postBillDetail.setCgstPer(getBillDetail.getCgstPer());
				postBillDetail.setItemId(getBillDetail.getItemId());
				postBillDetail.setMenuId(getBillDetail.getMenuId());
				postBillDetail.setMrp(getBillDetail.getMrp());
				postBillDetail.setOrderId(getBillDetail.getOrderId());
				postBillDetail.setOrderQty(getBillDetail.getOrderQty());
				postBillDetail.setRate(getBillDetail.getRate());
				postBillDetail.setRateType(getBillDetail.getRateType());
				postBillDetail.setRemark(getBillDetail.getRemark());
				postBillDetail.setGrnType(getBillDetail.getGrnType());
				postBillDetail.setExpiryDate(getBillDetail.getExpiryDate());
				postBillDetail.setIsGrngvnApplied(getBillDetail.getIsGrngvnApplied());

				
				
							
					float baseRate=getBillDetail.getBaseRate();
					
					float taxableAmt=baseRate*newBillQty;
					taxableAmt=roundUp(taxableAmt);
					
					float sgstRs=(taxableAmt*getBillDetail.getSgstPer())/100;
					float cgstRs=(taxableAmt*getBillDetail.getCgstPer())/100;
					float igstRs=(taxableAmt*getBillDetail.getIgstPer())/100;
					
					sgstRs=roundUp(sgstRs);
					cgstRs=roundUp(cgstRs);
					igstRs=roundUp(igstRs);
					
					float totalTax=sgstRs+cgstRs;
					totalTax=roundUp(totalTax);
					
					float grandTotal=totalTax+taxableAmt;
					grandTotal=roundUp(grandTotal);
					
					sumTaxableAmt=sumTaxableAmt+taxableAmt;
					sumTotalTax=sumTotalTax+totalTax;
					sumGrandTotal=sumGrandTotal+grandTotal;
					postBillDetail.setBillQty(newBillQty);
					postBillDetail.setSgstRs(sgstRs);
					postBillDetail.setCgstRs(cgstRs);
					postBillDetail.setIgstRs(igstRs);
					postBillDetail.setTaxableAmt(taxableAmt);
					postBillDetail.setTotalTax(totalTax);
					postBillDetail.setGrandTotal(grandTotal);
					
					

				}//end of if 
				
				/*else {

					System.out.println(" Nothing Changed ");
					postBillDetail.setBillQty(getBillDetail.getBillQty());

					postBillDetail.setSgstRs(getBillDetail.getSgstRs());
					postBillDetail.setCgstRs(getBillDetail.getSgstRs());
					postBillDetail.setIgstRs(getBillDetail.getIgstRs());
					postBillDetail.setTaxableAmt(getBillDetail.getTaxableAmt());
					postBillDetail.setTotalTax(getBillDetail.getTotalTax());
					postBillDetail.setGrandTotal(getBillDetail.getGrandTotal());
					
					
					
				}//end of else
*/				
				postBillDetailsList.add(postBillDetail);
				

				for(int j=0;j<billHeadersList.size();j++) {
					
					if(billHeadersList.get(j).getBillNo()==postBillDetailsList.get(0).getBillNo()) {
						
						
						
						DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
						Date billDate=null;
						try {
							billDate = formatter.parse(billHeadersList.get(j).getBillDate());
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						postBillHeader.setBillDate(billDate);
						
						postBillHeader.setBillNo(billHeadersList.get(j).getBillNo());
						postBillHeader.setDelStatus(0);
						postBillHeader.setFrCode(billHeadersList.get(j).getFrCode());
						postBillHeader.setFrId(billHeadersList.get(j).getFrId());
						postBillHeader.setGrandTotal(sumGrandTotal);
						postBillHeader.setInvoiceNo(billHeadersList.get(j).getInvoiceNo());
						postBillHeader.setRemark(billHeadersList.get(j).getRemark());
						postBillHeader.setStatus(billHeadersList.get(j).getStatus());
						postBillHeader.setTaxableAmt(sumTaxableAmt);
						postBillHeader.setTaxApplicable(billHeadersList.get(j).getTaxApplicable());
						postBillHeader.setTotalTax(sumTotalTax);
						break;
					}//end of if
			
				}//end of for
				
				
				
				
			} // End of for
			
			
			
			
			postBillHeader.setPostBillDetailsList(postBillDetailsList);
			postBillHeadersList.add(postBillHeader);
			postBillDataCommon.setPostBillHeadersList(postBillHeadersList);
			

			Info info = restTemplate.postForObject(Constants.url + "updateBillData", postBillDataCommon,
					Info.class);

			return "redirect:/showBillList";
		}
		
		

		//delete Bill
		
		@RequestMapping(value = "/deleteBill/{billNo}/{frName}", method = RequestMethod.GET)
		public String deleteBill(@PathVariable int billNo, @PathVariable String frName) {
			ModelAndView model = new ModelAndView("billing/viewbillheader");
			
				RestTemplate restTemplate = new RestTemplate();

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("billNo", billNo);
				map.add("delStatus", 1);

				Info info  = restTemplate.postForObject(Constants.url + "deleteBill",
						map, Info.class);
		
		return "redirect:/showBillList";
		
		}
		
		
		//ganesh
		
	      
		@RequestMapping(value = "/viewBill", method = RequestMethod.GET)
		public ModelAndView viewBill(HttpServletRequest request,
					HttpServletResponse response) {

				ModelAndView model = new ModelAndView("billing/sellBillHeader");
				
				RestTemplate restTemplate = new RestTemplate();
				allFrIdNameList = new AllFrIdNameList();
				try {

					allFrIdNameList = restTemplate.getForObject(Constants.url + "getAllFrIdName", AllFrIdNameList.class);

				} catch (Exception e) {
					System.out.println("Exception in getAllFrIdName" + e.getMessage());
					e.printStackTrace();

				}
				
				model.addObject("allFrIdNameList", allFrIdNameList.getFrIdNamesList());
				
				return model;			
		}
		
		
		@RequestMapping(value = "/getSellBillHeader", method = RequestMethod.GET)
		public @ResponseBody List<GetSellBillHeader> getSellBillHeader(HttpServletRequest request,
			HttpServletResponse response) {
					
			
			System.out.println("in method");
			String fromDate=request.getParameter("fromDate");
			String toDate=request.getParameter("toDate");
			String selectedFr = request.getParameter("fr_id_list");
			selectedFr=selectedFr.substring(1, selectedFr.length()-1);
			selectedFr=selectedFr.replaceAll("\"", "");
			
				
						RestTemplate restTemplate = new RestTemplate();
						
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			
			map.add("frId", selectedFr);
			map.add("fromDate", fromDate);
			map.add("toDate", toDate);
			//getFrGrnDetail
			try {
			  
			ParameterizedTypeReference<List<GetSellBillHeader>> typeRef = new ParameterizedTypeReference<List<GetSellBillHeader>>() {
			};
			ResponseEntity<List<GetSellBillHeader>> responseEntity = restTemplate.exchange(Constants.url + "getSellBillHeader",
					HttpMethod.POST, new HttpEntity<>(map), typeRef);
			
			getSellBillHeaderList = responseEntity.getBody();	
			}
			catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
			
			
			 System.out.println("Sell Bill Header "+getSellBillHeaderList.toString());
			
				
						
			
		
		return getSellBillHeaderList;
		
		}
		
		@RequestMapping(value = "/viewBillDetails", method = RequestMethod.GET)
		public ModelAndView viewBillDetails(HttpServletRequest request,
					HttpServletResponse response) {

				ModelAndView model = new ModelAndView("billing/sellBillDetail");
				

				System.out.println("in method");
				
				String sellBill_no=request.getParameter("sellBillNo");

				String billDate=request.getParameter("billDate");
				String frName=request.getParameter("frName");
				
				
					
							RestTemplate restTemplate = new RestTemplate();
							
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				int sellBillNo=Integer.parseInt(sellBill_no);
				map.add("sellBillNo", sellBillNo);
				
				try {
				
				ParameterizedTypeReference<List<GetSellBillDetail>> typeRef = new ParameterizedTypeReference<List<GetSellBillDetail>>() {
				};
				ResponseEntity<List<GetSellBillDetail>> responseEntity = restTemplate.exchange(Constants.url + "getSellBillDetail",
						HttpMethod.POST, new HttpEntity<>(map), typeRef);
				
				getSellBillDetailList = responseEntity.getBody();	
				}
				catch (Exception e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
				}
				
				
				 System.out.println("Sell Bill Detail "+getSellBillDetailList.toString());
				
				model.addObject("getSellBillDetailList",getSellBillDetailList);
				model.addObject("sellBillNo", sellBillNo);	
				model.addObject("billDate",billDate);
				model.addObject("frName",frName);
				
				return model;			
		}	
	

}
