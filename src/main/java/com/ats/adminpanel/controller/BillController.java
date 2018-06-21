package com.ats.adminpanel.controller;

import java.awt.Dimension;
import java.awt.Insets;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.Catch;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
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
import org.zefer.pd4ml.PD4Constants;
import org.zefer.pd4ml.PD4ML;
import org.zefer.pd4ml.PD4PageMark;
import org.zefer.pd4ml.tools.PD4Browser.PD4Panel;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.commons.DateConvertor;
import com.ats.adminpanel.model.AllFrIdName;
import com.ats.adminpanel.model.AllFrIdNameList;
import com.ats.adminpanel.model.AllRoutesListResponse;
import com.ats.adminpanel.model.ExportToExcel;
import com.ats.adminpanel.model.GenerateBill;
import com.ats.adminpanel.model.GenerateBillList;
import com.ats.adminpanel.model.GetSellBillDetail;
import com.ats.adminpanel.model.GetSellBillHeader;
import com.ats.adminpanel.model.Info;
import com.ats.adminpanel.model.Route;
import com.ats.adminpanel.model.SalesVoucherList;
import com.ats.adminpanel.model.RawMaterial.GetItemSfHeader;
import com.ats.adminpanel.model.billing.FrBillHeaderForPrint;
import com.ats.adminpanel.model.billing.FrBillPrint;
import com.ats.adminpanel.model.billing.FrBillTax;
import com.ats.adminpanel.model.billing.GetBillDetail;
import com.ats.adminpanel.model.billing.GetBillDetailPrint;
import com.ats.adminpanel.model.billing.GetBillDetailsResponse;
import com.ats.adminpanel.model.billing.GetBillHeader;
import com.ats.adminpanel.model.billing.GetBillHeaderResponse;
import com.ats.adminpanel.model.billing.PostBillDataCommon;
import com.ats.adminpanel.model.billing.PostBillDetail;
import com.ats.adminpanel.model.billing.PostBillHeader;
import com.ats.adminpanel.model.billing.SlabwiseBillList;
import com.ats.adminpanel.model.franchisee.AllMenuResponse;
import com.ats.adminpanel.model.franchisee.FrNameIdByRouteId;
import com.ats.adminpanel.model.franchisee.FrNameIdByRouteIdResponse;
import com.ats.adminpanel.model.franchisee.Menu;
import com.ats.adminpanel.model.franchisee.SubCategory;
import com.ats.adminpanel.model.item.CategoryListResponse;
import com.ats.adminpanel.model.item.FrItemStockConfiResponse;
import com.ats.adminpanel.model.item.FrItemStockConfigure;
import com.ats.adminpanel.model.item.FrItemStockConfigureList;
import com.ats.adminpanel.model.item.MCategoryList;
import com.ats.adminpanel.model.modules.ErrorMessage;
import com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

@Controller
@Scope("session")
public class BillController {

	private static final Logger logger = LoggerFactory.getLogger(BillController.class);

	public AllFrIdNameList allFrIdNameList = new AllFrIdNameList();
	public List<Menu> menuList = new ArrayList<Menu>();
	public String selectedFrArray;
	public String selectedDate;
	public GenerateBillList generateBillList = new GenerateBillList();
	public List<GenerateBill> staticGetGenerateBills = new ArrayList<>();
	public List<String> frList = new ArrayList<>();

	public List<GetBillDetail> billDetailsList;

	public List<GetBillDetailPrint> billDetailsListForPrint;

	public GetBillHeader getBillHeader;

	public List<GetBillHeader> billHeadersList = new ArrayList<>();

	public List<FrBillHeaderForPrint> billHeadersListForPrint = new ArrayList<>();

	public List<FrBillPrint> billPrintList;

	public List<GetSellBillHeader> getSellBillHeaderList;
	public List<GetSellBillDetail> getSellBillDetailList;
	public int bQty;

	public String transportMode;

	public String vehicleNo;

	private boolean isTwice=false;
	
	public String getInvoiceNo() {

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		RestTemplate restTemplate = new RestTemplate();

		String settingKey = new String();

		settingKey = "PB";

		map.add("settingKeyList", settingKey);

		FrItemStockConfigureList settingList = restTemplate.postForObject(Constants.url + "getDeptSettingValue", map,
				FrItemStockConfigureList.class);

		int settingValue = settingList.getFrItemStockConfigure().get(0).getSettingValue();

		System.out.println("Setting Value Received " + settingValue);
		int year = Year.now().getValue();
		String strYear = String.valueOf(year);
		strYear = strYear.substring(2);

		int length = String.valueOf(settingValue).length();

		String invoiceNo = null;

		if (length == 1)

			invoiceNo = strYear + "-" + "0000" + settingValue;
		if (length == 2)

			invoiceNo = strYear + "-" + "000" + settingValue;

		if (length == 3)

			invoiceNo = strYear + "-" + "00" + settingValue;

		if (length == 4)

			invoiceNo = strYear + "-" + "0" + settingValue;

		System.out.println("*** settingValue= " + settingValue);
		return invoiceNo;

	}

	@RequestMapping(value = "/submitNewBill", method = RequestMethod.POST)
	public String submitNewBill(HttpServletRequest request, HttpServletResponse response) {

		logger.info("/submitNewBill request mapping.");

		int settingValue = 0;
		String invoiceNo = null;

		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
		Date billDate = null;
		try {
			billDate = DF.parse(selectedDate);
		} catch (ParseException e) {

			e.printStackTrace();
		}

		System.out.println("Bill Date " + billDate);

		ModelAndView model = new ModelAndView("billing/generatebill");

		// settingValue
		try {

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
				// System.out.println("Invoice No= " + invoiceNo);
				int frId = frIdList.get(i);

				// System.out.println("Outer For frId " + frId);
				header.setFrId(frId);
				postBillDetailsList = new ArrayList();

				float sumTaxableAmt = 0, sumTotalTax = 0, sumGrandTotal = 0;
				float sumT1 = 0;
				float sumT2 = 0;
				float sumT3 = 0;
				Map<Integer, String> frIdInvoiceMap = new HashMap<Integer, String>();

				for (int j = 0; j < tempGenerateBillList.size(); j++) {

					// String invNo = getInvoiceNo();

					/*if (frIdInvoiceMap.containsKey(frId)) {

						System.out.println("prev id found " + frId);

						header.setInvoiceNo(frIdInvoiceMap.get(frId));

					} else {
						System.out.println("New id found " + frId);

						String invNo = getInvoiceNo();
						frIdInvoiceMap.put(frId, invNo);
						header.setInvoiceNo(invNo);
						map = new LinkedMultiValueMap<String, Object>();

						String settingKey = new String();

						settingKey = "PB";

						map.add("settingKeyList", settingKey);

						FrItemStockConfigureList settingList = restTemplate.postForObject(
								Constants.url + "getDeptSettingValue", map, FrItemStockConfigureList.class);

						System.out.println("SettingKeyList" + settingList.toString());

						settingValue = settingList.getFrItemStockConfigure().get(0).getSettingValue();

						settingValue = settingValue + 1;
						System.out.println("inside update setting Value " + settingValue);

						map.add("settingValue", settingValue);

						map.add("settingKey", Constants.SETTING_KEY);

						Info updateSetting = restTemplate.postForObject(Constants.url + "updateSeetingForPB", map,
								Info.class);
					}
*/
					GenerateBill gBill = tempGenerateBillList.get(j);

					System.out.println("Inner For frId " + gBill.getFrId());

					if (gBill.getFrId() == frId) {
						// map.add(frId,"sd");

						System.out.println("If condn true " + gBill.getFrId());

						PostBillDetail billDetail = new PostBillDetail();

						String billQty = request
								.getParameter("" + "billQty" + tempGenerateBillList.get(j).getOrderId());

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
						Float totalTax = sgstRs + cgstRs;

						if (billQty == null || billQty == "") {// new code to handle hidden records
							billQty = "0";
						}

						if (gBill.getIsSameState() == 1) {
							baseRate = (orderRate * 100) / (100 + (tax1 + tax2));
							taxableAmt = (float) (baseRate * Integer.parseInt(billQty));

							sgstRs = (taxableAmt * tax1) / 100;
							cgstRs = (taxableAmt * tax2) / 100;
							igstRs = 0;
							totalTax = sgstRs + cgstRs;

						}

						else {
							baseRate = (orderRate * 100) / (100 + (tax3));
							taxableAmt = (float) (baseRate * Integer.parseInt(billQty));

							sgstRs = 0;
							cgstRs = 0;
							igstRs = (taxableAmt * tax3) / 100;
							totalTax = igstRs;
						}

						sgstRs = roundUp(sgstRs);
						cgstRs = roundUp(cgstRs);
						igstRs = roundUp(igstRs);

						// header.setSgstSum(sumT1);
						// header.setCgstSum(sumT2);
						// header.setIgstSum(sumT3);

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

						billDetail.setGrnType(gBill.getGrnType());// newly added

						header.setSgstSum(header.getSgstSum() + billDetail.getSgstRs());
						header.setCgstSum(header.getCgstSum() + billDetail.getCgstRs());
						header.setIgstSum(header.getIgstSum() + billDetail.getIgstRs());

						int itemShelfLife = gBill.getItemShelfLife();

						String deliveryDate = gBill.getDeliveryDate();

						String calculatedDate = incrementDate(deliveryDate, itemShelfLife);

						// inc exp date if these menuId
						if (gBill.getMenuId() == 44 || gBill.getMenuId() == 45 || gBill.getMenuId() == 46) {

							calculatedDate = incrementDate(calculatedDate, 1);

						}

						DateFormat Df = new SimpleDateFormat("dd-MM-yyyy");

						Date expiryDate = null;
						try {
							expiryDate = Df.parse(calculatedDate);
						} catch (ParseException e) {

							e.printStackTrace();
						}

						billDetail.setExpiryDate(expiryDate);
						postBillDetailsList.add(billDetail);

						// header.setInvoiceNo(invoiceNo);
						// header.setSgstSum(sumT1);
						// header.setCgstSum(sumT2);
						// header.setIgstSum(sumT3);
						header.setFrCode(gBill.getFrCode());
						header.setBillDate(billDate);
						header.setRemark("");
						header.setTaxApplicable((int) (gBill.getItemTax1() + gBill.getItemTax2()));

					}

				}

				// header.setTaxApplicable((int)sumT1+(int)sumT2+(int)sumT3);
				// header.setSgstSum(sumT1);
				// header.setCgstSum(sumT2);
				// header.setIgstSum(sumT3);
				header.setTaxableAmt(sumTaxableAmt);
				header.setGrandTotal(Math.round(sumGrandTotal));
				
				System.err.println("sumof grand total beofre "+sumGrandTotal);
				
				System.err.println("Math round up Sum " +header.getGrandTotal());
				header.setTotalTax(sumTotalTax);
				
				header.setStatus(1);
				header.setPostBillDetailsList(postBillDetailsList);

				ZoneId zoneId = ZoneId.of("Asia/Calcutta");
				ZonedDateTime zdt = ZonedDateTime.now(zoneId);
				// System.out.println("time ==" + zdt.format(null));

				SimpleDateFormat sdf = new SimpleDateFormat("kk:mm:ss ");
				TimeZone istTimeZone = TimeZone.getTimeZone("Asia/Kolkata");
				Date d = new Date();
				sdf.setTimeZone(istTimeZone);
				String strtime = sdf.format(d);
				
				
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();

				header.setRemark(dateFormat.format(cal.getTime()));
				// System.out.println("time ==" + strtime);
				header.setTime(strtime);
				postBillHeaderList.add(header);

			}

			postBillDataCommon.setPostBillHeadersList(postBillHeaderList);

			System.out.println("Test data : " + postBillDataCommon.toString());

		Info info = restTemplate.postForObject(Constants.url + "insertBillData", postBillDataCommon, Info.class);

			System.out.println("Info Data " + info.toString());

			// model.addObject("postBillDataCommon","");

			// if (info.getError() == false) {

			/*
			 * map = new LinkedMultiValueMap<String, Object>();
			 * 
			 * settingValue = settingValue + 1;
			 * 
			 * map.add("settingValue", settingValue);
			 * 
			 * map.add("settingKey", Constants.SETTING_KEY);
			 * 
			 * Info updateSetting = restTemplate.postForObject(Constants.url +
			 * "updateSeetingForPB", map, Info.class);
			 */

			// }.

		} catch (Exception e) {
			System.out.println("Exc in Inserting bill " + e.getMessage());
			e.printStackTrace();
		}
		return "redirect:/showBillListForPrint";

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
		Constants.mainAct = 2;
		Constants.subAct = 19;
		try {
			ZoneId z = ZoneId.of("Asia/Calcutta");

			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			String todaysDate = date.format(formatters);

			RestTemplate restTemplate = new RestTemplate();

			AllMenuResponse allMenuResponse = restTemplate.getForObject(Constants.url + "getAllMenu",
					AllMenuResponse.class);

			menuList = allMenuResponse.getMenuConfigurationPage();

			// get Routes

			AllRoutesListResponse allRouteListResponse = restTemplate.getForObject(Constants.url + "showRouteList",
					AllRoutesListResponse.class);

			List<Route> routeList = new ArrayList<Route>();

			routeList = allRouteListResponse.getRoute();

			// end get Routes

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

			model.addObject("routeList", routeList);

		} catch (Exception e) {

			System.out.println("Exc in show generate bill " + e.getMessage());
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/generateNewBill", method = RequestMethod.GET)
	public @ResponseBody List<GenerateBill> generateNewBill(HttpServletRequest request, HttpServletResponse response) {

		logger.info("/generateNewBill AJAX Call mapping.");

		selectedFrArray = null;

		List<GenerateBill> genBills = new ArrayList<>();

		List<GenerateBill> tempGenBills = new ArrayList<>();

		try {

			String selectedFr = request.getParameter("fr_id_list");
			selectedDate = request.getParameter("deliveryDate");
			String selectedMenu = request.getParameter("menu_id_list");
			String routeId = request.getParameter("route_id");

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

			// route-wise billing

			if (!routeId.equalsIgnoreCase("0")) {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				RestTemplate restTemplate = new RestTemplate();

				map.add("routeId", routeId);

				FrNameIdByRouteIdResponse frNameId = restTemplate.postForObject(Constants.url + "getFrNameIdByRouteId",
						map, FrNameIdByRouteIdResponse.class);

				List<FrNameIdByRouteId> frNameIdByRouteIdList = frNameId.getFrNameIdByRouteIds();

				System.out.println("route wise franchisee " + frNameIdByRouteIdList.toString());

				StringBuilder sbForRouteFrId = new StringBuilder();
				for (int i = 0; i < frNameIdByRouteIdList.size(); i++) {

					sbForRouteFrId = sbForRouteFrId.append(frNameIdByRouteIdList.get(i).getFrId().toString() + ",");

				}

				String strFrIdRouteWise = sbForRouteFrId.toString();
				selectedFr = strFrIdRouteWise.substring(0, strFrIdRouteWise.length() - 1);
				System.out.println("fr Id Route WISE = " + selectedFr);

			} // end of if

			// end of route wise billing

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
					System.out.println("generate bill list  All Menu " + generateBillList.toString());

					System.out.println("g bill first size " + generateBillList.getGenerateBills().size());

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

					System.out.println("g bill first size " + generateBillList.getGenerateBills().size());

				}

				/*
				 * // new code
				 * 
				 * //GenerateBillList generateBillList = new GenerateBillList();
				 * 
				 * genBills=generateBillList.getGenerateBills();
				 * 
				 * for (int c = 0; c < genBills.size(); c++) {
				 * 
				 * if (genBills.get(c).getMenuId() == 32 ) {
				 * 
				 * int frId=genBills.get(c).getFrId();
				 * 
				 * for (int d = 0; d < genBills.size(); d++) {
				 * 
				 * if(genBills.get(d).getMenuId()==31 && frId==genBills.get(d).getFrId() &&
				 * genBills.get(d).getItemId()==genBills.get(c).getItemId() ) {
				 * 
				 * genBills.get(d).setOrderQty(genBills.get(d).getOrderQty()+genBills.get(c).
				 * getOrderQty());
				 * 
				 * genBills.get(c).setOrderQty(0);
				 * 
				 * genBills.get(c).setIsPositive(1);
				 * 
				 * }
				 * 
				 * }
				 * 
				 * } }
				 * 
				 * 
				 * for(int i=0;i<genBills.size();i++) {
				 * 
				 * if(genBills.get(i).getOrderQty()>0) {
				 * 
				 * tempGenBills.add(genBills.get(i));
				 * 
				 * }
				 * 
				 * }
				 * 
				 * 
				 * generateBillList = new GenerateBillList();
				 * 
				 * //generateBillList.setGenerateBills(tempGenBills);
				 * 
				 * generateBillList.setGenerateBills(genBills);
				 * 
				 * System.out.println("new tempGenBills  bill List "+tempGenBills.toString());
				 * 
				 * System.out.println(" new size tempGenBills = "+tempGenBills.size());
				 * 
				 * //end of new Code
				 */

			} catch (Exception e) {
				System.out.println("Exception " + e.getMessage());
				e.printStackTrace();
			}

		} catch (Exception e) {
			System.out.println("Exc in generate bill data " + e.getMessage());
			e.printStackTrace();
		}

		// return generateBillList.getGenerateBills();

		// return tempGenBills;

		return generateBillList.getGenerateBills();
	}

	@RequestMapping(value = "/showBillList", method = RequestMethod.GET)
	public ModelAndView showBillList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("billing/viewbillheader");

		Constants.mainAct = 2;
		Constants.subAct = 20;
		try {
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			RestTemplate restTemplate = new RestTemplate();

			List<Menu> menuList = new ArrayList<Menu>();

			ZoneId z = ZoneId.of("Asia/Calcutta");

			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			String todaysDate = date.format(formatters);

			AllMenuResponse allMenuResponse = restTemplate.getForObject(Constants.url + "getAllMenu",
					AllMenuResponse.class);

			menuList = allMenuResponse.getMenuConfigurationPage();

			allFrIdNameList = restTemplate.getForObject(Constants.url + "getAllFrIdName", AllFrIdNameList.class);

			AllRoutesListResponse allRouteListResponse = restTemplate.getForObject(Constants.url + "showRouteList",
					AllRoutesListResponse.class);

			List<Route> routeList = new ArrayList<Route>();

			routeList = allRouteListResponse.getRoute();

			map.add("fromDate", todaysDate);
			map.add("toDate", todaysDate);
			// System.out.println("Inside is All fr Selected " + isAllFrSelected);

			GetBillHeaderResponse billHeaderResponse = restTemplate
					.postForObject(Constants.url + "getBillHeaderForAllFr", map, GetBillHeaderResponse.class);

			billHeadersList = billHeaderResponse.getGetBillHeaders();

			model.addObject("routeList", routeList);
			model.addObject("todaysDate", todaysDate);
			model.addObject("menuList", menuList);
			model.addObject("allFrIdNameList", allFrIdNameList.getFrIdNamesList());
			model.addObject("billHeadersList", billHeadersList);

		} catch (Exception e) {
			System.out.println("Exce in view Bills " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/showBillListForPrint", method = RequestMethod.GET)
	public ModelAndView showBillListForPrint(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("billing/billHeaderForPrint");

		// Constants.mainAct = 8;
		// Constants.subAct = 83;
		try {

			RestTemplate restTemplate = new RestTemplate();

			List<Menu> menuList = new ArrayList<Menu>();

			ZoneId z = ZoneId.of("Asia/Calcutta");

			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			String todaysDate = date.format(formatters);

			AllMenuResponse allMenuResponse = restTemplate.getForObject(Constants.url + "getAllMenu",
					AllMenuResponse.class);

			menuList = allMenuResponse.getMenuConfigurationPage();

			allFrIdNameList = restTemplate.getForObject(Constants.url + "getAllFrIdName", AllFrIdNameList.class);

			AllRoutesListResponse allRouteListResponse = restTemplate.getForObject(Constants.url + "showRouteList",
					AllRoutesListResponse.class);

			List<Route> routeList = new ArrayList<Route>();

			routeList = allRouteListResponse.getRoute();

			String fromDate = request.getParameter("from_date");
			String toDate = request.getParameter("to_date");
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			if (fromDate == null || fromDate == "" || toDate == null || toDate == "") {

				map.add("fromDate", todaysDate);
				map.add("toDate", todaysDate);
				// map.add("fromDate", "08-01-2018");
				// map.add("toDate", "08-01-2018");

				ParameterizedTypeReference<List<FrBillHeaderForPrint>> typeRef = new ParameterizedTypeReference<List<FrBillHeaderForPrint>>() {
				};
				ResponseEntity<List<FrBillHeaderForPrint>> responseEntity = restTemplate.exchange(
						Constants.url + "getBillHeaderForPrint", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				// List<GetBillDetail> billDetailsResponse = responseEntity.getBody();
				billHeadersListForPrint = new ArrayList<>();
				billHeadersListForPrint = responseEntity.getBody();
			} else {

				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

			}

			model.addObject("billHeadersList", billHeadersListForPrint);

			System.out.println(
					"First Header : bill header for print with address :  " + billHeadersListForPrint.toString());

			model.addObject("routeList", routeList);
			model.addObject("todaysDate", todaysDate);
			model.addObject("menuList", menuList);
			model.addObject("allFrIdNameList", allFrIdNameList.getFrIdNamesList());

		} catch (Exception e) {
			System.out.println("Exce in view Bills " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}
	
	@RequestMapping(value = "/excelForFrBill", method = RequestMethod.GET)
	@ResponseBody
	public SalesVoucherList excelForFrBill(HttpServletRequest request, HttpServletResponse response) {

		SalesVoucherList salesVoucherList = new SalesVoucherList();
		try {
			System.out.println("ala " );
			RestTemplate restTemplate = new RestTemplate();
			String checkboxes = request.getParameter("checkboxes");
			int all = Integer.parseInt(request.getParameter("all"));
			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
			System.out.println("checkboxes " + checkboxes);
			/*StringBuilder sb = new StringBuilder();
			String string = new String();
			
			for (int i = 0; i < checkboxes.length; i++) {
				sb = sb.append(checkboxes[i] + ",");

			}

			string = sb.toString();*/
			if(all==0)
				checkboxes = checkboxes.substring(0, checkboxes.length() - 1);
			System.out.println("string " + checkboxes);
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("billNo", checkboxes);
			map.add("all", all);
			map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			map.add("toDate",  DateConvertor.convertToYMD(toDate));
			System.out.println("map " + map);
			salesVoucherList = restTemplate.postForObject(Constants.url + "/tally/getSalesVouchersByBillNo",map, SalesVoucherList.class);
			System.out.println("salesVoucherList " + salesVoucherList.getSalesVoucherList());
			
			try
			{
				List<ExportToExcel> exportToExcelList=new ArrayList<ExportToExcel>();
				
				ExportToExcel expoExcel=new ExportToExcel();
				List<String> rowData=new ArrayList<String>();
				 
				rowData.add("Sr no");
				rowData.add("Invoice No");
				rowData.add("Date");
				rowData.add("Type");
				rowData.add("Fr Id ");
				rowData.add("Fr code ");
				rowData.add("Party Name"); 
				rowData.add("Gst No");
				rowData.add("State");
				rowData.add("Cat Id");
				rowData.add("Item Id");
				rowData.add("Item Code");
				rowData.add("Item Name");
				rowData.add("Hsn Code");
				rowData.add("Qty"); 
				rowData.add("Uom");
				rowData.add("Rate");
				rowData.add("Amount");
				rowData.add("Sgst Per");
				rowData.add("Sgst Rs");
				rowData.add("Cgst Per");
				rowData.add("Cgst Rs");
				rowData.add("Igst Per");
				rowData.add("Igst Rs");
				rowData.add("Cess Per");
				rowData.add("Cess Rs");
				rowData.add("Item Discount Per");
				rowData.add("Total Discount");
				rowData.add("Rount Off"); 
				rowData.add("Total Amt"); 
				rowData.add("Total Taxable Amt");
				rowData.add("Cgst sum");
				rowData.add("Sgst sum");
				rowData.add("Igst sum"); 
				rowData.add("Tax Amt ");
				rowData.add("Bill Total");
				rowData.add("Remark");
				rowData.add("Erp Link");
			 
					
				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);
				for(int i=0;i<salesVoucherList.getSalesVoucherList().size();i++)
				{
					  expoExcel=new ExportToExcel();
					 rowData=new ArrayList<String>();
					 
				 
					 rowData.add(""+(i+1));
					 rowData.add(""+salesVoucherList.getSalesVoucherList().get(i).getvNo());
					rowData.add(""+salesVoucherList.getSalesVoucherList().get(i).getDate());
					rowData.add(""+salesVoucherList.getSalesVoucherList().get(i).getvType()); 
					rowData.add(""+salesVoucherList.getSalesVoucherList().get(i).getFrId());
					rowData.add(""+salesVoucherList.getSalesVoucherList().get(i).getFrCode());
					rowData.add(""+salesVoucherList.getSalesVoucherList().get(i).getPartyName());
					rowData.add(""+salesVoucherList.getSalesVoucherList().get(i).getGstin());
					rowData.add(""+salesVoucherList.getSalesVoucherList().get(i).getState());
					rowData.add(""+salesVoucherList.getSalesVoucherList().get(i).getCatId()); 
					rowData.add(""+salesVoucherList.getSalesVoucherList().get(i).getItemId());
					rowData.add(salesVoucherList.getSalesVoucherList().get(i).getItemCode()); 
					rowData.add(salesVoucherList.getSalesVoucherList().get(i).getItemName());  
					rowData.add(""+salesVoucherList.getSalesVoucherList().get(i).getHsnCode());
					rowData.add(""+salesVoucherList.getSalesVoucherList().get(i).getQty());
					rowData.add(""+salesVoucherList.getSalesVoucherList().get(i).getUom());
					rowData.add(""+salesVoucherList.getSalesVoucherList().get(i).getRate()); 
					rowData.add(""+salesVoucherList.getSalesVoucherList().get(i).getAmount());
					rowData.add(""+salesVoucherList.getSalesVoucherList().get(i).getSgstPer());
					rowData.add(""+salesVoucherList.getSalesVoucherList().get(i).getSgstRs());
					rowData.add(""+salesVoucherList.getSalesVoucherList().get(i).getCgstPer());
					rowData.add(""+salesVoucherList.getSalesVoucherList().get(i).getCgstRs());
					rowData.add(""+salesVoucherList.getSalesVoucherList().get(i).getIgstPer());
					rowData.add(""+salesVoucherList.getSalesVoucherList().get(i).getIgstRs());
					rowData.add(""+salesVoucherList.getSalesVoucherList().get(i).getCessPer()); 
					rowData.add(""+salesVoucherList.getSalesVoucherList().get(i).getCessRs());
					rowData.add(""+salesVoucherList.getSalesVoucherList().get(i).getItemDiscPer());
					rowData.add(""+salesVoucherList.getSalesVoucherList().get(i).getTotalDisc()); 
					rowData.add(""+salesVoucherList.getSalesVoucherList().get(i).getRoundOff()); 
					rowData.add(""+salesVoucherList.getSalesVoucherList().get(i).getTotalAmt());
					
					rowData.add(""+salesVoucherList.getSalesVoucherList().get(i).getTotalTaxableAmt());
					rowData.add(""+salesVoucherList.getSalesVoucherList().get(i).getCgstSum());
					rowData.add(""+salesVoucherList.getSalesVoucherList().get(i).getSgstSum());
					rowData.add(""+salesVoucherList.getSalesVoucherList().get(i).getIgstSum());
					rowData.add(""+salesVoucherList.getSalesVoucherList().get(i).getTotalTax());
					rowData.add(""+salesVoucherList.getSalesVoucherList().get(i).getBillTotal());
					rowData.add(salesVoucherList.getSalesVoucherList().get(i).getRemark());
					rowData.add(salesVoucherList.getSalesVoucherList().get(i).getErpLink());
					
					
					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);
					 
				}
				 
				
				
				HttpSession session = request.getSession();
				session.setAttribute("exportExcelList", exportToExcelList);
				session.setAttribute("excelName", "billExcel");
			}catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("Exception to genrate excel ");
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return salesVoucherList;

	}


	// Search Bill Header for PDF providing fromDate,toDate,route/frIds...
	@RequestMapping(value = "/getBillListProcessForPrint", method = RequestMethod.GET)
	public @ResponseBody List<FrBillHeaderForPrint> getBillListProcessForPrint(HttpServletRequest request,
			HttpServletResponse response) {

		// Constants.mainAct = 8;
		// Constants.subAct = 83;

		billHeadersList = new ArrayList<>();

		try {

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

				FrNameIdByRouteIdResponse frNameId = restTemplate.postForObject(Constants.url + "getFrNameIdByRouteId",
						map, FrNameIdByRouteIdResponse.class);

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
				System.out.println("Inside IF  is All fr Selected " + isAllFrSelected);

				ParameterizedTypeReference<List<FrBillHeaderForPrint>> typeRef = new ParameterizedTypeReference<List<FrBillHeaderForPrint>>() {
				};
				ResponseEntity<List<FrBillHeaderForPrint>> responseEntity = restTemplate.exchange(
						Constants.url + "getBillHeaderForPrint", HttpMethod.POST, new HttpEntity<>(map), typeRef);
				// billHeadersListForPrint=new ArrayList<>();
				// billHeadersListForPrint=responseEntity.getBody();

				billHeadersListForPrint = new ArrayList<>();
				billHeadersListForPrint = responseEntity.getBody();

				// model.addObject("billHeadersList",billHeadersListForPrint);

			} else { // few franchisee selected

				System.out.println("Inside Else: Few Fr Selected ");
				map.add("frIdList", frIdString);
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				ParameterizedTypeReference<List<FrBillHeaderForPrint>> typeRef = new ParameterizedTypeReference<List<FrBillHeaderForPrint>>() {
				};
				ResponseEntity<List<FrBillHeaderForPrint>> responseEntity = restTemplate.exchange(
						Constants.url + "getBillHeaderForPrintByFrId", HttpMethod.POST, new HttpEntity<>(map), typeRef);
				billHeadersListForPrint = new ArrayList<>();
				// List<GetBillDetail> billDetailsResponse = responseEntity.getBody();
				billHeadersListForPrint = responseEntity.getBody();

				/*
				 * GetBillHeaderResponse billHeaderResponse =
				 * restTemplate.postForObject(Constants.url + "getBillHeader", map,
				 * GetBillHeaderResponse.class);
				 * 
				 * billHeadersList = billHeaderResponse.getGetBillHeaders();
				 */
			}

			System.out.println("bill header for Print Using Ajax call  " + billHeadersListForPrint.toString());
		} catch (Exception e) {

			System.out
					.println("Ex in getting billHeader List  for print using date and frId Ajax call" + e.getMessage());
			e.printStackTrace();
		}

		return billHeadersListForPrint;

	}

	@RequestMapping(value = "/getBillDetailForPrint", method = RequestMethod.GET)
	public ModelAndView getBillDetailForPrint(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("billing/billDetailPdf");
		billPrintList = new ArrayList<>();
		// Constants.mainAct = 8;
		// Constants.subAct = 83;
		try {

			vehicleNo = request.getParameter("vehicle_no");
			transportMode = request.getParameter("transport_mode");
			transportMode = transportMode.replaceAll("\\s", "-");
			System.out.println("Vehicle No " + vehicleNo + "Transport Mode = " + transportMode);

			System.out.println("Inside new form action ");

			RestTemplate restTemplate = new RestTemplate();

			String selectedBill = request.getParameter("select_to_print");
			String[] selectedBills = request.getParameterValues("select_to_print");
			String billList = new String();

			for (int i = 0; i < selectedBills.length; i++) {
				billList = selectedBills[i] + "," + billList;
			}

			billList = billList.substring(0, billList.length() - 1);

			System.out.println("selected bills for Printing " + billList);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("billNoList", billList);

			ParameterizedTypeReference<List<GetBillDetailPrint>> typeRef = new ParameterizedTypeReference<List<GetBillDetailPrint>>() {
			};
			ResponseEntity<List<GetBillDetailPrint>> responseEntity = restTemplate.exchange(
					Constants.url + "getBillDetailsForPrint", HttpMethod.POST, new HttpEntity<>(map), typeRef);
			// List<GetBillDetailPrint> billDetailsResponse =new ArrayList<>();

			List<GetBillDetailPrint> billDetailsResponse = responseEntity.getBody();

			System.out.println("bill No in Header " + billHeadersListForPrint.toString());

			System.out.println("selected bills for Printing " + billList);
			System.out.println("Size Here Now  " + billHeadersListForPrint.size());
			billHeadersListForPrint = new ArrayList<>();

			// billHeadersListForPrint=getBillListProcessForPrint(request, response);
			// List<FrBillHeaderForPrint> getBillListProcessForPrint

			/*
			 * List<String> billing=Arrays.asList(billList);
			 * 
			 * for(int a=0;a<billing.size();a++) {
			 * 
			 * if(billHeadersListForPrint.get(a).getBillNo()!=Integer.parseInt(billing.get(a
			 * )) ){
			 * 
			 * System.out.println("billHeader print removing bill"+billHeadersListForPrint.
			 * get(a));
			 * 
			 * billHeadersListForPrint.remove(a);
			 * 
			 * }
			 * 
			 * }
			 */

			/*
			 * List<GetBillDetail> billDetailsResponse =
			 * restTemplate.postForObject(Constants.url + "getBillDetailsForPrint", map,
			 * List.class);
			 * 
			 * 
			 */
			/*
			 * List<FrBillHeaderForPrint> tempList=new ArrayList<>();
			 * tempList=billHeadersListForPrint;
			 * System.out.println("temp List Before"+tempList); for(int
			 * p=0;p<selectedBills.length;p++) { System.out.println("selected Bill List "+p
			 * +""+selectedBills[p]);
			 * if(Integer.parseInt(selectedBills[p])==billHeadersListForPrint.get(p).
			 * getBillNo()) {
			 * 
			 * tempList.remove(p); }
			 * 
			 * } System.out.println("temp List After"+tempList); billHeadersListForPrint=new
			 * ArrayList<>(); billHeadersListForPrint=tempList;
			 */

			map = new LinkedMultiValueMap<String, Object>();

			map.add("billNoList", billList);

			ParameterizedTypeReference<List<FrBillHeaderForPrint>> typeRef2 = new ParameterizedTypeReference<List<FrBillHeaderForPrint>>() {
			};
			ResponseEntity<List<FrBillHeaderForPrint>> responseEntity2 = restTemplate.exchange(
					Constants.url + "getFrBillHeaderForPrintSelectedBill", HttpMethod.POST, new HttpEntity<>(map),
					typeRef2);
			billHeadersListForPrint = new ArrayList<>();
			// List<GetBillDetail> billDetailsResponse = responseEntity.getBody();
			billHeadersListForPrint = responseEntity2.getBody();

			System.out.println("in new BHLFP" + billHeadersListForPrint.toString());
			/*
			 * List<FrBillHeaderForPrint> temHeaderList=billHeadersListForPrint;
			 * 
			 * System.out.println("length of bil String "+selectedBills.length); for(int
			 * k=0;k<selectedBills.length;k++) { System.out.println("Inside for Loop 1 ");
			 * 
			 * for(int y=0;y<temHeaderList.size();y++) {
			 * System.out.println("Inside for Loop 2 ");
			 * 
			 * 
			 * int bNo=Integer.valueOf(selectedBills[k]);
			 * 
			 * System.out.println("bill No "+bNo);
			 * if(temHeaderList.get(y).getBillNo()==bNo){
			 * 
			 * System.out.println("Inside If Loop ");
			 * 
			 * System.out.println("billHeader print removing bill"+temHeaderList.get(k));
			 * billHeadersListForPrint.remove(y);
			 * 
			 * } } }
			 */

			billDetailsListForPrint = new ArrayList<GetBillDetailPrint>();
			billDetailsListForPrint = billDetailsResponse;
			System.out.println(" *** get Bill detail for Print response :: " + billDetailsListForPrint.toString());

			System.out.println("Size Here Now  " + billHeadersListForPrint.size());

			FrBillPrint billPrint;
			for (int i = 0; i < billHeadersListForPrint.size(); i++) {
				System.out.println("Inside outer for " + i);
				billPrint = new FrBillPrint();
				List<GetBillDetailPrint> billDetails = new ArrayList<>();

				for (int j = 0; j < billDetailsListForPrint.size(); j++) {
					System.out.println("Inside inner for " + j);
					System.out.println("Header bill no  " + billHeadersListForPrint.get(i).getBillNo());
					System.out.println("detail bill no " + billDetailsListForPrint.get(j).getBillNo());

					if (billHeadersListForPrint.get(i).getBillNo().equals(billDetailsListForPrint.get(j).getBillNo())) {

						System.out.println("Inside If  Bill no  = " + billHeadersListForPrint.get(i).getBillNo());

						billPrint.setBillNo(billHeadersListForPrint.get(i).getBillNo());
						billPrint.setFrAddress(billHeadersListForPrint.get(i).getFrAddress());
						billPrint.setFrId(billHeadersListForPrint.get(i).getFrId());
						billPrint.setFrName(billHeadersListForPrint.get(i).getFrName());
						billPrint.setInvoiceNo(billHeadersListForPrint.get(i).getInvoiceNo());
						billPrint.setIsSameState(billHeadersListForPrint.get(i).getIsSameState());
						billPrint.setBillDate(billHeadersListForPrint.get(i).getBillDate());

						billDetails.add(billDetailsListForPrint.get(j));

						// FrBillTax billTax=new FrBillTax(); not used

					} // end of if

				}
				billPrint.setBillDetailsList(billDetails);
				// billPrintList=new ArrayList<>();

				if (billPrint != null)
					billPrintList.add(billPrint);

			}

			System.out.println(" after adding detail List : bill Print List " + billPrintList.toString());
			model.addObject("billDetails", billPrintList);
			// HttpSession ses= request.getSession();
			// ses.setAttribute("billDetails", billPrintList);
			model.addObject("vehicleNo", vehicleNo);
			model.addObject("transportMode", transportMode);
			model.addObject("selectedBills", billList);
		} catch (Exception e) {
			System.out.println("Exce in getting bill Detail for Print " + e.getMessage());
			e.printStackTrace();

		}
		return model;

	}

	@RequestMapping(value = "pdf/showBillPdf/{transportMode}/{vehicleNo}/{selectedBills}", method = RequestMethod.GET)
	public ModelAndView showBillPdf(@PathVariable String transportMode, @PathVariable String vehicleNo,
			@PathVariable String[] selectedBills, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("IN Show bill PDF Method :/showBillPdf");
		ModelAndView model = new ModelAndView("billing/pdf/frBillPdf");

		billPrintList = new ArrayList<>();

		try {

			// vehicleNo=request.getParameter("vehicle_no");
			// transportMode=request.getParameter("transport_mode");

			System.out.println("Vehicle No " + vehicleNo + "Transport Mode = " + transportMode);

			System.out.println("Inside new form action ");

			RestTemplate restTemplate = new RestTemplate();

			// String[] selectedBills=request.getParameterValues("select_to_print");
			String billList = new String();

			for (int i = 0; i < selectedBills.length; i++) {
				billList = selectedBills[i] + "," + billList;
			}

			billList = billList.substring(0, billList.length() - 1);

			System.out.println("selected bills for Printing " + billList);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("billNoList", billList);

			ParameterizedTypeReference<List<GetBillDetailPrint>> typeRef = new ParameterizedTypeReference<List<GetBillDetailPrint>>() {
			};
			ResponseEntity<List<GetBillDetailPrint>> responseEntity = restTemplate.exchange(
					Constants.url + "getBillDetailsForPrint", HttpMethod.POST, new HttpEntity<>(map), typeRef);
			// List<GetBillDetailPrint> billDetailsResponse =new ArrayList<>();

			List<GetBillDetailPrint> billDetailsResponse = responseEntity.getBody();

			List<String> billnos = Arrays.asList(billList.split("\\s*,\\s*"));
			List<SlabwiseBillList> slabwiseBillList = new ArrayList<>();

			for (String billno : billnos) {
				map = new LinkedMultiValueMap<String, Object>();

				map.add("billNoList", billno);
				ParameterizedTypeReference<List<SlabwiseBillList>> typeRef1 = new ParameterizedTypeReference<List<SlabwiseBillList>>() {
				};
				ResponseEntity<List<SlabwiseBillList>> responseEntity1 = restTemplate.exchange(
						Constants.url + "getSlabwiseBillData", HttpMethod.POST, new HttpEntity<>(map), typeRef1);

				slabwiseBillList.addAll(responseEntity1.getBody());
			}
			System.out.println("slabwiseBillList" + slabwiseBillList.toString());
			System.out.println("bill No in Header " + billHeadersListForPrint.toString());

			System.out.println("selected bills for Printing " + billList);
			System.out.println("Size Here Now  " + billHeadersListForPrint.size());
			billHeadersListForPrint = new ArrayList<>();

			// billHeadersListForPrint=getBillListProcessForPrint(request, response);
			// List<FrBillHeaderForPrint> getBillListProcessForPrint

			/*
			 * List<String> billing=Arrays.asList(billList);
			 * 
			 * for(int a=0;a<billing.size();a++) {
			 * 
			 * if(billHeadersListForPrint.get(a).getBillNo()!=Integer.parseInt(billing.get(a
			 * )) ){
			 * 
			 * System.out.println("billHeader print removing bill"+billHeadersListForPrint.
			 * get(a));
			 * 
			 * billHeadersListForPrint.remove(a);
			 * 
			 * }
			 * 
			 * }
			 */

			/*
			 * List<GetBillDetail> billDetailsResponse =
			 * restTemplate.postForObject(Constants.url + "getBillDetailsForPrint", map,
			 * List.class);
			 * 
			 * 
			 */
			/*
			 * List<FrBillHeaderForPrint> tempList=new ArrayList<>();
			 * tempList=billHeadersListForPrint;
			 * System.out.println("temp List Before"+tempList); for(int
			 * p=0;p<selectedBills.length;p++) { System.out.println("selected Bill List "+p
			 * +""+selectedBills[p]);
			 * if(Integer.parseInt(selectedBills[p])==billHeadersListForPrint.get(p).
			 * getBillNo()) {
			 * 
			 * tempList.remove(p); }
			 * 
			 * } System.out.println("temp List After"+tempList); billHeadersListForPrint=new
			 * ArrayList<>(); billHeadersListForPrint=tempList;
			 */

			map = new LinkedMultiValueMap<String, Object>();

			map.add("billNoList", billList);

			ParameterizedTypeReference<List<FrBillHeaderForPrint>> typeRef2 = new ParameterizedTypeReference<List<FrBillHeaderForPrint>>() {
			};
			ResponseEntity<List<FrBillHeaderForPrint>> responseEntity2 = restTemplate.exchange(
					Constants.url + "getFrBillHeaderForPrintSelectedBill", HttpMethod.POST, new HttpEntity<>(map),
					typeRef2);
			billHeadersListForPrint = new ArrayList<>();
			// List<GetBillDetail> billDetailsResponse = responseEntity.getBody();
			billHeadersListForPrint = responseEntity2.getBody();

			System.out.println("in new BHLFP" + billHeadersListForPrint.toString());
			/*
			 * List<FrBillHeaderForPrint> temHeaderList=billHeadersListForPrint;
			 * 
			 * System.out.println("length of bil String "+selectedBills.length); for(int
			 * k=0;k<selectedBills.length;k++) { System.out.println("Inside for Loop 1 ");
			 * 
			 * for(int y=0;y<temHeaderList.size();y++) {
			 * System.out.println("Inside for Loop 2 ");
			 * 
			 * 
			 * int bNo=Integer.valueOf(selectedBills[k]);
			 * 
			 * System.out.println("bill No "+bNo);
			 * if(temHeaderList.get(y).getBillNo()==bNo){
			 * 
			 * System.out.println("Inside If Loop ");
			 * 
			 * System.out.println("billHeader print removing bill"+temHeaderList.get(k));
			 * billHeadersListForPrint.remove(y);
			 * 
			 * } } }
			 */

			CategoryListResponse categoryListResponse = restTemplate.getForObject(Constants.url + "showAllCategory",
					CategoryListResponse.class);
			List<MCategoryList> categoryList;
			categoryList = categoryListResponse.getmCategoryList();
			
			
			
			SubCategory[] subCatList = restTemplate.getForObject(Constants.url + "getAllSubCatList",
					SubCategory[].class);

			ArrayList<SubCategory> subCatAList = new ArrayList<SubCategory>(Arrays.asList(subCatList));
			SubCategory subCat=new SubCategory();
			subCat.setCatId(5);
			subCat.setSubCatName("Special Cake");
			subCat.setSubCatId(0);
			subCat.setDelStatus(0);
			subCatAList.add(subCat);

			System.out.println("subCatAList:" + subCatAList.toString());
		

			// List<MCategoryList> filteredCatList=new ArrayList<MCategoryList>();

			billDetailsListForPrint = new ArrayList<GetBillDetailPrint>();
			billDetailsListForPrint = billDetailsResponse;
			System.out.println(" *** get Bill detail for Print response :: " + billDetailsListForPrint.toString());

			System.out.println("Size Here Now  " + billHeadersListForPrint.size());

			FrBillPrint billPrint = null;
			for (int i = 0; i < billHeadersListForPrint.size(); i++) {
				billPrint = new FrBillPrint();
				List<GetBillDetailPrint> billDetails = new ArrayList<>();

				List<SubCategory> filteredSubCat = new ArrayList<SubCategory>();
				for (int j = 0; j < billDetailsListForPrint.size(); j++) {

					if (billHeadersListForPrint.get(i).getBillNo().equals(billDetailsListForPrint.get(j).getBillNo())) {

						System.out.println("Inside If  Bill no  = " + billHeadersListForPrint.get(i).getBillNo());
						billPrint.setAmtInWords(Currency.convertToIndianCurrency(
								String.valueOf(billHeadersListForPrint.get(i).getGrandTotal())));
						billPrint.setBillNo(billHeadersListForPrint.get(i).getBillNo());
						billPrint.setFrAddress(billHeadersListForPrint.get(i).getFrAddress());
						billPrint.setFrId(billHeadersListForPrint.get(i).getFrId());
						billPrint.setFrName(billHeadersListForPrint.get(i).getFrName());
						billPrint.setInvoiceNo(billHeadersListForPrint.get(i).getInvoiceNo());
						billPrint.setIsSameState(billHeadersListForPrint.get(i).getIsSameState());
						billPrint.setBillDate(billHeadersListForPrint.get(i).getBillDate());
						billPrint.setGrandTotal(billHeadersListForPrint.get(i).getGrandTotal());
						billDetails.add(billDetailsListForPrint.get(j));

						for (int a = 0; a < subCatAList.size(); a++) {

							for (int b = 0; b < billDetails.size(); b++) {

								
								if (billDetails.get(b).getSubCatId()==subCatAList.get(a).getSubCatId()) {

									if (filteredSubCat.isEmpty())
										filteredSubCat.add(subCatAList.get(a));
									else if (!filteredSubCat.contains(subCatAList.get(a))) {
										filteredSubCat.add(subCatAList.get(a));
									}
								}

							}

						}

						// FrBillTax billTax=new FrBillTax(); not used

					} // end of if

				}
				billPrint.setBillDetailsList(billDetails);
				// billPrintList=new ArrayList<>();
				billPrint.setSubCatList(filteredSubCat);
				if (billPrint != null)
					billPrintList.add(billPrint);

			}
			System.err.println("sub Cat List  " +billPrint.getSubCatList().toString());
			System.out.println(" after adding detail List : bill Print List " + billPrintList.toString());

			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			Calendar cal = Calendar.getInstance();

			System.out.println("time in Gen Bill PDF ==" + dateFormat.format(cal.getTime()));
			model.addObject("billDetails", billPrintList);
			model.addObject("slabwiseBillList", slabwiseBillList);
			model.addObject("vehicleNo", vehicleNo);
			model.addObject("transportMode", transportMode);
			model.addObject("dateTime", dateFormat.format(cal.getTime()));

			// allFrIdNameList = new AllFrIdNameList();

			// model.addObject("catList",filteredCatList);

			System.out.println("after Data ");

		} catch (Exception e) {

			System.out.println("Ex in getting bill Data for PDF " + e.getMessage());
			e.printStackTrace();
		}
		return model;

	}

	// List<GetBillHeader> billHeadersList;
	@RequestMapping(value = "/getBillListProcess", method = RequestMethod.GET)
	public @ResponseBody List<GetBillHeader> getBillListProcess(HttpServletRequest request,
			HttpServletResponse response) {

		// Constants.mainAct = 8;
		// Constants.subAct = 83;

		billHeadersList = new ArrayList<>();

		try {

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

				FrNameIdByRouteIdResponse frNameId = restTemplate.postForObject(Constants.url + "getFrNameIdByRouteId",
						map, FrNameIdByRouteIdResponse.class);

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

				GetBillHeaderResponse billHeaderResponse = restTemplate.postForObject(Constants.url + "getBillHeader",
						map, GetBillHeaderResponse.class);

				billHeadersList = billHeaderResponse.getGetBillHeaders();

			}

			System.out.println("bill header  " + billHeadersList.toString());
		} catch (Exception e) {

			System.out.println("Ex in getting billHeader List " + e.getMessage());
			e.printStackTrace();
		}

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

			/*
			 * for (int i = 0; i < billDetailsList.size(); i++) {
			 * 
			 * model.addObject("billNo", billDetailsList.get(i).getBillNo());
			 * model.addObject("billDate", billDetailsList.get(i).getBillDate());
			 * 
			 * }
			 */

			model.addObject("billNo", billDetailsList.get(0).getBillNo());
			model.addObject("billDate", billDetailsList.get(0).getBillDate());
			model.addObject("frName", frName);
			model.addObject("billDetails", billDetailsList);

		} catch (Exception e) {

			System.out.println("exce in showing Bill Details: " + e.getMessage());
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

			model.addObject("frName", frName);
			model.addObject("billNo", billDetailsList.get(0).getBillNo());
			model.addObject("billDate", billDetailsList.get(0).getBillDate());
			model.addObject("billDetails", billDetailsList);

		} catch (Exception e) {

			System.out.println("exce in showing  Bill update page " + e.getMessage());
			e.printStackTrace();
		}

		return model;
		
	}

	@RequestMapping(value = "/updateBillDetailsProcess", method = RequestMethod.POST)
	public String updateBillDetailsProcess(HttpServletRequest request, HttpServletResponse response) {
		// ModelAndView model = new ModelAndView("billing/editBillDetails");
		 
		DecimalFormat df = new DecimalFormat("#.00");
		try {
			RestTemplate restTemplate = new RestTemplate();

			PostBillDataCommon postBillDataCommon = new PostBillDataCommon();
			List<PostBillHeader> postBillHeadersList = new ArrayList<>();

			List<PostBillDetail> postBillDetailsList = new ArrayList<>();

			float sumTaxableAmt = 0, sumTotalTax = 0, sumGrandTotal = 0, sumTotalCgst = 0,sumTotalSgst = 0;

			PostBillDetail postBillDetail = new PostBillDetail();
			PostBillHeader postBillHeader = new PostBillHeader();
			for (int i = 0; i < billDetailsList.size(); i++) {

				Integer newBillQty = Integer
						.parseInt(request.getParameter("billQty" + billDetailsList.get(i).getBillDetailNo()));
				float newBillRate = Float
						.parseFloat(request.getParameter("billRate" + billDetailsList.get(i).getBillDetailNo()));
				float newSgstPer =  Float
						.parseFloat(request.getParameter("sgstPer" + billDetailsList.get(i).getBillDetailNo()));
				float newCgstPer =  Float
						.parseFloat(request.getParameter("cgstPer" + billDetailsList.get(i).getBillDetailNo()));

				System.out.println("new bill qty = " + newBillQty);
				System.out.println("new BillRate = " + newBillRate);
				System.out.println("new  SgstPer = " + newSgstPer);
				System.out.println("new  CgstPer = " + newCgstPer);

				GetBillDetail getBillDetail = billDetailsList.get(i);

				 
					postBillDetail = new PostBillDetail();
					postBillDetail.setBillDetailNo(getBillDetail.getBillDetailNo()); 
					postBillDetail.setBillNo(getBillDetail.getBillNo());
					postBillDetail.setRate(newBillRate);
					postBillDetail.setBillQty(newBillQty);
					float newBaserate=Float.valueOf(df.format((newBillRate*100)/(100+newSgstPer+newCgstPer)));
					postBillDetail.setBaseRate(newBaserate); 
					postBillDetail.setCatId(getBillDetail.getCatId());
					postBillDetail.setSgstPer(newSgstPer);
					postBillDetail.setCgstPer(newCgstPer);
					postBillDetail.setIgstPer(newSgstPer+newCgstPer);
					postBillDetail.setDelStatus(0); 
					postBillDetail.setItemId(getBillDetail.getItemId());
					postBillDetail.setMenuId(getBillDetail.getMenuId());
					postBillDetail.setMrp(getBillDetail.getMrp());
					postBillDetail.setOrderId(getBillDetail.getOrderId());
					postBillDetail.setOrderQty(getBillDetail.getOrderQty());
					
					postBillDetail.setRateType(getBillDetail.getRateType());
					postBillDetail.setRemark(getBillDetail.getRemark());
					postBillDetail.setGrnType(getBillDetail.getGrnType());
					postBillDetail.setExpiryDate(getBillDetail.getExpiryDate());
					postBillDetail.setIsGrngvnApplied(getBillDetail.getIsGrngvnApplied());

					float baseRate = postBillDetail.getBaseRate();

					float taxableAmt = baseRate * newBillQty;
					taxableAmt = roundUp(taxableAmt);

					float sgstRs = (taxableAmt * postBillDetail.getSgstPer()) / 100;
					float cgstRs = (taxableAmt * postBillDetail.getCgstPer()) / 100;
					float igstRs = (taxableAmt * getBillDetail.getIgstPer()) / 100;

					sgstRs = roundUp(sgstRs);
					cgstRs = roundUp(cgstRs);
					igstRs = 0;
					
					sumTotalSgst = sumTotalSgst + sgstRs;
					sumTotalCgst = sumTotalCgst + cgstRs;

					float totalTax = sgstRs + cgstRs;
					totalTax = roundUp(totalTax);

					float grandTotal = totalTax + taxableAmt;
					grandTotal = roundUp(grandTotal);

					sumTaxableAmt = sumTaxableAmt + taxableAmt;
					sumTotalTax = sumTotalTax + totalTax;
					sumGrandTotal = sumGrandTotal + grandTotal;
					
					postBillDetail.setSgstRs(Float.valueOf(df.format(sgstRs)));
					postBillDetail.setCgstRs(Float.valueOf(df.format(cgstRs)));
					postBillDetail.setIgstRs(igstRs);
					postBillDetail.setTaxableAmt(Float.valueOf(df.format(taxableAmt)));
					postBillDetail.setTotalTax(Float.valueOf(df.format(totalTax)));
					postBillDetail.setGrandTotal(Float.valueOf(df.format(grandTotal)));
					System.out.println("base rate " + baseRate);
					System.out.println("set rate " + postBillDetail.getRate() + "new rate " + newBillRate);
					System.out.println("set getTaxableAmt " + postBillDetail.getTaxableAmt()+ "new taxableAmt " + taxableAmt);
					System.out.println("set sgst " + postBillDetail.getSgstPer() + "new newSgstPer " + newSgstPer);
					System.out.println("set cgst " + postBillDetail.getCgstPer() + "new newCgstPer " + newCgstPer);
					System.out.println("set grandTotal " + grandTotal);
				 
				postBillDetailsList.add(postBillDetail);

				

			} // End of for
			
			
			for (int j = 0; j < billHeadersList.size(); j++) {

				if (billHeadersList.get(j).getBillNo() == postBillDetailsList.get(0).getBillNo()) {

					DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
					Date billDate = null;
					try {
						billDate = formatter.parse(billHeadersList.get(j).getBillDate());
					} catch (ParseException e) {
						System.out.println("exc in formatting bill Date " + e.getMessage());
						e.printStackTrace();
					}
					postBillHeader.setBillDate(billDate);

					postBillHeader.setBillNo(billHeadersList.get(j).getBillNo());
					postBillHeader.setDelStatus(0);
					postBillHeader.setFrCode(billHeadersList.get(j).getFrCode());
					postBillHeader.setFrId(billHeadersList.get(j).getFrId());
					postBillHeader.setGrandTotal(Float.valueOf(df.format(sumGrandTotal)));
					postBillHeader.setInvoiceNo(billHeadersList.get(j).getInvoiceNo());
					postBillHeader.setRemark(billHeadersList.get(j).getRemark());
					postBillHeader.setStatus(billHeadersList.get(j).getStatus());
					postBillHeader.setTaxableAmt(Float.valueOf(df.format(sumTaxableAmt)));
					postBillHeader.setTaxApplicable(billHeadersList.get(j).getTaxApplicable());
					postBillHeader.setTotalTax(Float.valueOf(df.format(sumTotalTax)));
					postBillHeader.setRemark(billHeadersList.get(j).getRemark());
					postBillHeader.setSgstSum(sumTotalSgst);
					postBillHeader.setCgstSum(sumTotalCgst);
					postBillHeader.setTime(billHeadersList.get(j).getTime());
					break;
				} // end of if

			} // end of for

			postBillHeader.setPostBillDetailsList(postBillDetailsList);
			postBillHeadersList.add(postBillHeader);
			postBillDataCommon.setPostBillHeadersList(postBillHeadersList);
			 
				Info info = restTemplate.postForObject(Constants.url + "updateBillData", postBillDataCommon, Info.class);
			 
			

		} catch (Exception e) {

			System.out.println("exce in  Bill update " + e.getMessage());
			e.printStackTrace();
		}
		return "redirect:/showBillList";
	}

	// delete Bill

	@RequestMapping(value = "/deleteBill/{billNo}/{frName}", method = RequestMethod.GET)
	public String deleteBill(@PathVariable int billNo, @PathVariable String frName) {
		ModelAndView model = new ModelAndView("billing/viewbillheader");

		try {

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("billNo", billNo);
			map.add("delStatus", 1);

			Info info = restTemplate.postForObject(Constants.url + "deleteBill", map, Info.class);

		} catch (Exception e) {
			System.out.println("Exce in Delete bill " + e.getMessage());
			e.printStackTrace();
		}

		return "redirect:/showBillList";

	}

	// ganesh

	@RequestMapping(value = "/viewBill", method = RequestMethod.GET)
	public ModelAndView viewBill(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("billing/sellBillHeader");
		Constants.mainAct = 2;
		Constants.subAct = 21;
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
		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");
		String selectedFr = request.getParameter("fr_id_list");
		selectedFr = selectedFr.substring(1, selectedFr.length() - 1);
		selectedFr = selectedFr.replaceAll("\"", "");

		RestTemplate restTemplate = new RestTemplate();

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		map.add("frId", selectedFr);
		map.add("fromDate", fromDate);
		map.add("toDate", toDate);
		// getFrGrnDetail
		try {

			ParameterizedTypeReference<List<GetSellBillHeader>> typeRef = new ParameterizedTypeReference<List<GetSellBillHeader>>() {
			};
			ResponseEntity<List<GetSellBillHeader>> responseEntity = restTemplate
					.exchange(Constants.url + "getSellBillHeader", HttpMethod.POST, new HttpEntity<>(map), typeRef);

			getSellBillHeaderList = responseEntity.getBody();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		System.out.println("Sell Bill Header " + getSellBillHeaderList.toString());

		return getSellBillHeaderList;

	}

	@RequestMapping(value = "/viewBillDetails", method = RequestMethod.GET)
	public ModelAndView viewBillDetails(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("billing/sellBillDetail");

		System.out.println("in method");

		String sellBill_no = request.getParameter("sellBillNo");

		String billDate = request.getParameter("billDate");
		String frName = request.getParameter("frName");

		RestTemplate restTemplate = new RestTemplate();

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		int sellBillNo = Integer.parseInt(sellBill_no);
		map.add("sellBillNo", sellBillNo); 

		
		try { 

			ParameterizedTypeReference<List<GetSellBillDetail>> typeRef = new ParameterizedTypeReference<List<GetSellBillDetail>>() {
			};
			ResponseEntity<List<GetSellBillDetail>> responseEntity = restTemplate
					.exchange(Constants.url + "getSellBillDetail", HttpMethod.POST, new HttpEntity<>(map), typeRef);

			getSellBillDetailList = responseEntity.getBody();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		System.out.println("Sell Bill Detail " + getSellBillDetailList.toString());

		model.addObject("getSellBillDetailList", getSellBillDetailList);
		model.addObject("sellBillNo", sellBillNo);
		model.addObject("billDate", billDate);
		model.addObject("frName", frName);

		return model;
	}

	/*
	 * @RequestMapping(value = "/showBillPdf", method = RequestMethod.GET) public
	 * ModelAndView showBillPdf(HttpServletRequest request, HttpServletResponse
	 * response) { System.out.println("IN Show bill Method"); ModelAndView model =
	 * new ModelAndView("billing/pdf/frBillPdf"); try {
	 * //System.out.println(" Data for PDF generateBillList "+
	 * generateBillList.toString());
	 * 
	 * staticGetGenerateBills=generateBillList.getGenerateBills();
	 * 
	 * //List<GenerateBill> generateNewBill=generateBillList.getGenerateBills(); //
	 * System.out.println(" Data for PDF generateBillList "+
	 * generateNewBill.toString()); model.addObject("getBillList",
	 * generateBillList.getGenerateBills()); System.out.println("after Data ");
	 * 
	 * }catch (Exception e) {
	 * 
	 * System.out.println("Ex in getting bill Data for PDF "+e.getMessage());
	 * e.printStackTrace(); } return model;
	 * 
	 * }
	 */

	private Dimension format = PD4Constants.A4;
	private boolean landscapeValue = false;
	private int topValue = 8;
	private int leftValue = 0;
	private int rightValue = 0;
	private int bottomValue = 8;
	private String unitsValue = "m";
	private String proxyHost = "";
	private int proxyPort = 0;

	private int userSpaceWidth = 750;
	private static int BUFFER_SIZE = 1024;
	
	@RequestMapping(value = "/pdf", method = RequestMethod.GET)
	public void showPDF(HttpServletRequest request, HttpServletResponse response) {

		String url = request.getParameter("url");
		System.out.println("URL " + url);
		// http://monginis.ap-south-1.elasticbeanstalk.com
	    File f = new File("/opt/tomcat-latest/webapps/uploads/report.pdf");
		//File f = new File("/home/ats-11/pdf/ordermemo221.pdf");
		//File f = new File("/Users/MIRACLEINFOTAINMENT/ATS/uplaods/reports/ordermemo221.pdf");

		System.out.println("I am here " + f.toString());
		try {
			isTwice =false;
			runConverter(Constants.ReportURL + url, f, request, response);
			System.out.println("Come on lets get ");
		} catch (IOException e) {
			// TODO Auto-generated catch block

			System.out.println("Pdf conversion exception " + e.getMessage());
		}

		// get absolute path of the application
		ServletContext context = request.getSession().getServletContext();
		String appPath = context.getRealPath("");
		String filename = "ordermemo221.pdf";
		 String filePath = "/opt/tomcat-latest/webapps/uploads/report.pdf";
		//String filePath = "/home/ats-11/pdf/ordermemo221.pdf";
		//String filePath = "/Users/MIRACLEINFOTAINMENT/ATS/uplaods/reports/ordermemo221.pdf";

		// construct the complete absolute path of the file
		String fullPath = appPath + filePath;
		File downloadFile = new File(filePath);
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(downloadFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			// get MIME type of the file
			String mimeType = context.getMimeType(fullPath);
			if (mimeType == null) {
				// set to binary type if MIME mapping not found
				mimeType = "application/pdf";
			}
			System.out.println("MIME type: " + mimeType);

			String headerKey = "Content-Disposition";

			// response.addHeader("Content-Disposition", "attachment;filename=report.pdf");
			response.setContentType("application/pdf");

			// get output stream of the response
			OutputStream outStream;

			outStream = response.getOutputStream();

			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;

			// write bytes read from the input stream into the output stream

			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}

			inputStream.close();
			outStream.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void runConverter(String urlstring, File output, HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		if (urlstring.length() > 0) {
			if (!urlstring.startsWith("http://") && !urlstring.startsWith("file:")) {
				urlstring = "http://" + urlstring;
			}
			System.out.println("PDF URL " + urlstring);
			java.io.FileOutputStream fos = new java.io.FileOutputStream(output);

			PD4ML pd4ml = new PD4ML();

			try {

				PD4PageMark footer = new PD4PageMark();  
				footer.setPageNumberTemplate("page $[page] of $[total]");  
				footer.setTitleAlignment(PD4PageMark.LEFT_ALIGN);  
				footer.setPageNumberAlignment(PD4PageMark.RIGHT_ALIGN);  
				footer.setInitialPageNumber(1);  
				footer.setFontSize(8);  
				footer.setAreaHeight(15); 
			
				pd4ml.setPageFooter(footer);

			} catch (Exception e) {
				System.out.println("Pdf conversion method excep " + e.getMessage());
			}
			try {
				pd4ml.setPageSize(landscapeValue ? pd4ml.changePageOrientation(format) : format);
			} catch (Exception e) {
				System.out.println("Pdf conversion ethod excep " + e.getMessage());
			}

			if (unitsValue.equals("mm")) {
				pd4ml.setPageInsetsMM(new Insets(topValue, leftValue, bottomValue, rightValue));
			} else {
				pd4ml.setPageInsets(new Insets(topValue, leftValue, bottomValue, rightValue));
			}

			pd4ml.setHtmlWidth(userSpaceWidth);

			
			

			pd4ml.render(urlstring, fos);
//			
//if(!isTwice) {
//	isTwice=true;
//			try {
//				Long no = (Long) pd4ml.getLastRenderInfo(PD4Constants.PD4ML_TOTAL_PAGES);
//				System.out.println("####### Total Pages " + no);
//
//				footer.setHtmlTemplate(
//						"<table width=\"100%\" border=\"0\"  cellpadding=\"0\" cellspacing=\"0\" style=\"border-top:1px solid #313131;border-right:1px solid #313131;\"><tr><td colspan=\"6\" width=\"50%\" style=\"border-left:1px solid #313131; padding:8px;color:#000; font-size:12px;\"><p style=\"color:#000; font-size:12px; text-align:left;margin:0px;\">After PDF Render: We hereby certify that food mentioned in the Tax Invoice is warranted to be of the nature and quality which it purpose to be. FDA Lic. No: 11515044000208</p></td><td colspan=\"5\" width=\"38%\" rowspan=\"2\" style=\"border-left:1px solid #313131; padding:8px;color:#000;font-size:15px;\">&nbsp;</td></tr><tr><td colspan=\"6\"width=\"50%\"style=\"border-top:1px solid #313131;border-left:1px solid #313131; padding:8px;color:#000; font-size:12px;\"><p>Certified that the particulars given above are true and correct.</p>&nbsp;</td></tr><tr><td colspan=\"6\" width=\"50%\"  style=\"border-top:1px solid #313131;border-left:1px solid #313131; padding:8px;color:#000; font-size:12px;\"><p><b>Invoice Value in Rs.</b><br></br> ${frDetails.amtInWords}</p>&nbsp;</td><td colspan=\"5\" width=\"38%\" style=\"border-top:1px solid #313131;border-left:1px solid #313131; padding:8px;color:#000;font-size:15px;\"><p style=\"color:#000; font-size:11px; text-align:left;margin:0px;\">Continue...</p></td></tr><tr><td colspan=\"6\"  width=\"50%\" style=\"border-bottom:1px solid #313131;border-top:1px solid #313131;border-left:1px solid #313131; padding:10px;color:#000; font-size:11px;\"><p style=\"color:#000; font-size:11px; text-align:left;margin:0px;\">Subject to Aurangabad Jurisdiction &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Receiver's Signature</p>"
//								+ "</td><td  align=\"center\" colspan=\"5\" width=\"38%\" style=\"border-bottom:1px solid #313131;border-top:1px solid #313131;border-left:1px solid #313131; padding:10px;color:#000;font-size:11px;\">Authorised Signature</td></tr></table>");
//				pd4ml.setPageFooter(footer);
//				fos = new java.io.FileOutputStream(output);
//runConverter(urlstring, output, request, response);				
//
//			} catch (Exception e) {
//				// TODO: handle exception
//				e.printStackTrace();
//			}
//}

		}
	}
}
