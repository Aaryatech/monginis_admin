package com.ats.adminpanel.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.commons.VpsImageUpload;
import com.ats.adminpanel.model.AllRoutesListResponse;
import com.ats.adminpanel.model.ConfigureFrBean;
import com.ats.adminpanel.model.ConfigureFrListResponse;
import com.ats.adminpanel.model.ConfiguredSpDayCkResponse;
import com.ats.adminpanel.model.ExportToExcel;
import com.ats.adminpanel.model.GetConfiguredSpDayCk;
import com.ats.adminpanel.model.Info;
import com.ats.adminpanel.model.ItemNameId;
import com.ats.adminpanel.model.Route;
import com.ats.adminpanel.model.SpCakeResponse;
import com.ats.adminpanel.model.SpDayConfigure;
import com.ats.adminpanel.model.SpecialCake;
import com.ats.adminpanel.model.franchisee.AllFranchiseeAndMenu;
import com.ats.adminpanel.model.franchisee.AllFranchiseeList;
import com.ats.adminpanel.model.franchisee.AllMenuResponse;
import com.ats.adminpanel.model.franchisee.CommonConf;
import com.ats.adminpanel.model.franchisee.FrTarget;
import com.ats.adminpanel.model.franchisee.FrTargetList;
import com.ats.adminpanel.model.franchisee.FrTotalSale;
import com.ats.adminpanel.model.franchisee.FranchiseSup;
import com.ats.adminpanel.model.franchisee.FranchiseSupList;
import com.ats.adminpanel.model.franchisee.FranchiseeAndMenuList;
import com.ats.adminpanel.model.franchisee.FranchiseeList;
import com.ats.adminpanel.model.franchisee.Menu;
import com.ats.adminpanel.model.item.AllItemsListResponse;
import com.ats.adminpanel.model.item.FrItemStockConfiResponse;
import com.ats.adminpanel.model.item.FrItemStockConfigure;
import com.ats.adminpanel.model.item.Item;
import com.ats.adminpanel.model.item.ItemSup;
import com.ats.adminpanel.model.masters.FrListForSupp;
import com.ats.adminpanel.model.mastexcel.Franchisee;
import com.ats.adminpanel.model.mastexcel.ItemList;
import com.ats.adminpanel.model.modules.ErrorMessage;

@Controller
public class FranchiseeController {

	int selectedCatId;
	List<Menu> menuList;
	AllFranchiseeAndMenu allFranchiseeAndMenuList;
	FranchiseeAndMenuList franchiseeAndMenuList;
	public static int settingValue;

	private static final Logger logger = LoggerFactory.getLogger(FranchiseeController.class);

	// -------------------------ADD NEW FRANCHISEE FORM
	// SHOW-----------------------------
	@RequestMapping(value = "/showAddNewFranchisee")
	public ModelAndView addNewFranchisee(HttpServletRequest request, HttpServletResponse response) {

		logger.info("/showAddNewFranchisee request mapping.");

		ModelAndView model = new ModelAndView("franchisee/addnewfranchisee");
		Constants.mainAct = 1;
		Constants.subAct = 2;

		RestTemplate restTemplate = new RestTemplate();
		AllRoutesListResponse allRoutesListResponse = restTemplate.getForObject(Constants.url + "showRouteList",
				AllRoutesListResponse.class);

		Integer maxFrId = restTemplate.getForObject(Constants.url + "getUnigueFrCode", Integer.class);

		int maxFrIdLenth = String.valueOf(maxFrId).length();
		maxFrIdLenth = 5 - maxFrIdLenth;
		StringBuilder frCode = new StringBuilder("DB");

		for (int i = 0; i < maxFrIdLenth; i++) {
			String j = "0";
			frCode.append(j);
		}
		frCode.append(String.valueOf(maxFrId));

		List<Route> routeList = new ArrayList<Route>();
		routeList = allRoutesListResponse.getRoute();
		FrItemStockConfiResponse frItemStockConfiResponse = restTemplate
				.getForObject(Constants.url + "getfrItemConfSetting", FrItemStockConfiResponse.class);
		List<FrItemStockConfigure> frItemStockConfigures = new ArrayList<FrItemStockConfigure>();
		List<FrItemStockConfigure> fileredFrItemStockConfigures = new ArrayList<FrItemStockConfigure>();

		frItemStockConfigures = frItemStockConfiResponse.getFrItemStockConfigure();

		for (int i = 0; i < frItemStockConfigures.size(); i++) {

			if (frItemStockConfigures.get(i).getSettingKey().equalsIgnoreCase("frItemStockType")) {
				settingValue = frItemStockConfigures.get(i).getSettingValue();
			}

			if (frItemStockConfigures.get(i).getSettingKey().equalsIgnoreCase("Non Registered")
					|| frItemStockConfigures.get(i).getSettingKey().equalsIgnoreCase("composite")
					|| frItemStockConfigures.get(i).getSettingKey().equalsIgnoreCase("Regular")) {

				fileredFrItemStockConfigures.add(frItemStockConfigures.get(i));
			}

		}
		frItemStockConfigures.remove(0);

		System.out.println("settingValue-------------------------------------------==" + settingValue);
		model.addObject("settingValue", settingValue);
		model.addObject("frItemStockConfigures", fileredFrItemStockConfigures);
		System.out.println("Event List" + routeList.toString());
		model.addObject("routeList", routeList);
		model.addObject("frCode", frCode);
		return model;
	}
	// ----------------------------------------END-------------------------------------------

	// 23 March -updateFrMenuTime
	@RequestMapping(value = "/showUpdateFrMenuTime")
	public ModelAndView updateFrMenuTime(HttpServletRequest request, HttpServletResponse response) {

		logger.info("/updateFrMenuTime request mapping.");

		ModelAndView mav = new ModelAndView("franchisee/confFrMenuTime");
		Constants.mainAct = 2;
		Constants.subAct = 14;

		try {
			RestTemplate restTemplate = new RestTemplate();
			franchiseeAndMenuList = restTemplate.getForObject(Constants.url + "getFranchiseeAndMenu",
					FranchiseeAndMenuList.class);

			System.out.println("Franchisee Response " + franchiseeAndMenuList.getAllFranchisee());

			mav.addObject("allFranchiseeAndMenuList", franchiseeAndMenuList);
			mav.addObject("menuList", franchiseeAndMenuList.getAllMenu());

		} catch (Exception e) {
			System.out.println("Franchisee Controller Exception " + e.getMessage());
		}

		return mav;
	}

	//// 23 March updateFrMenuTime
	@RequestMapping(value = "/updateFrMenuTime")
	public String updateFrMenuTimeProcess(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("franchisee/confFrMenuTime");
		String fromTime = request.getParameter("frm_time");

		System.out.println(fromTime);
		String toTime = request.getParameter("to_time");

		System.out.println(toTime);

		SimpleDateFormat format = new SimpleDateFormat("hh:mm a"); // if 24 hour format

		java.util.Date d1 = null;
		try {
			d1 = (java.util.Date) format.parse(fromTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.util.Date d2 = null;
		try {
			d2 = (java.util.Date) format.parse(toTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		java.sql.Time sqlFromTime = new java.sql.Time(d1.getTime());
		java.sql.Time sqlToTime = new java.sql.Time(d2.getTime());

		System.out.println("Converted From Time: " + sqlFromTime.toString() + " To time: " + sqlToTime.toString());
		String frId = new String();
		String[] frIdList = request.getParameterValues("fr_id");

		if (frIdList[0].equalsIgnoreCase("-1")) {
			System.err.println("fr id contains -1");
			frId = frId + "," + "0";
		}

		else {
			for (int i = 0; i < frIdList.length; i++) {

				System.out.println("fr Id " + frIdList[i]);
				frId = frId + "," + frIdList[i];
				System.err.println("Fr Id s " + frId.toString());
			}
		}
		System.out.println("FRID" + frIdList.toString());
		System.err.println("Fr Id s " + frId.toString());
		frId = frId.substring(1);
		int menuId = Integer.parseInt(request.getParameter("menu"));
		System.out.println("menuId" + menuId);

		RestTemplate rest = new RestTemplate();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("fromTime", sqlFromTime.toString());
		map.add("toTime", sqlToTime.toString());
		map.add("frIdList", frId);
		map.add("menuId", menuId);

		Info errorMessage = rest.postForObject(Constants.url + "updateFrConfMenuTime", map, Info.class);

		System.err.println("Update Response " + errorMessage.toString());
		return "redirect:/showUpdateFrMenuTime";

	}

	// -------------------------CONFIGURE FRANCHISEE FORM
	// SHOW-------------------------------
	@RequestMapping(value = "/configureFranchisee")
	public ModelAndView addconfigureFranchisee(HttpServletRequest request, HttpServletResponse response) {

		logger.info("/configureFranchisee request mapping.");

		ModelAndView mav = new ModelAndView("franchisee/configureFr");
		Constants.mainAct = 2;
		Constants.subAct = 14;

		try {
			RestTemplate restTemplate = new RestTemplate();
			franchiseeAndMenuList = restTemplate.getForObject(Constants.url + "getFranchiseeAndMenu",
					FranchiseeAndMenuList.class);

			System.out.println("Franchisee Response " + franchiseeAndMenuList.getAllFranchisee());

			mav.addObject("allFranchiseeAndMenuList", franchiseeAndMenuList);

		} catch (Exception e) {
			System.out.println("Franchisee Controller Exception " + e.getMessage());
		}

		return mav;
	}
	// ----------------------------------------END--------------------------------------------

	// -------------------------GET ALL SELECTED ITEMS SELECTED(AJAX
	// METHOD)-------------------------
	@RequestMapping(value = "/setAllItemSelected", method = RequestMethod.GET)
	public @ResponseBody List<Item> setAllItemSelected() {
		System.out.println("inside ajax call for item selected");
		RestTemplate restTemplate = new RestTemplate();

		List<Item> itemList = new ArrayList<Item>();
		AllItemsListResponse itemListResponse = restTemplate.getForObject(Constants.url + "getAllItems",
				AllItemsListResponse.class);
		itemList = itemListResponse.getItems();

		return itemList;
	}

	// ----------------------------------------END--------------------------------------------

	// -------------------------GET ALL CONFIGURED MENUS(AJAX
	// METHOD)-------------------------
	@RequestMapping(value = "/getAllMenus", method = RequestMethod.GET)
	public @ResponseBody List<Menu> findAllMenu(@RequestParam(value = "fr_id", required = true) int frId) {

		menuList = new ArrayList<Menu>();

		List<Menu> notConfiguredMenuList = new ArrayList<Menu>();
		RestTemplate restTemplate = new RestTemplate();

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("frId", frId);
		// Calling Service to get Configured Menus
		Integer[] configuredMenuId = restTemplate.postForObject(Constants.url + "getConfiguredMenuId", map,
				Integer[].class);

		ArrayList<Integer> configuredMenuList = new ArrayList<Integer>(Arrays.asList(configuredMenuId));

		menuList = franchiseeAndMenuList.getAllMenu();

		for (Menu menu : menuList) {
			int con = 0;
			for (int i = 0; i < configuredMenuList.size(); i++) {
				System.out.println("configuredMenuList index" + configuredMenuList.get(i));
				if (menu.getMenuId() == configuredMenuList.get(i)) {

					con = 1;
					break;
				}

			}
			if (con == 0) {
				notConfiguredMenuList.add(menu);
			}

		}

		return notConfiguredMenuList;
	}
	// ----------------------------------------END--------------------------------------------
	// -------------------------CONFIGURED FRANCHISEE List FORM
	// SHOW-------------------------------

	@RequestMapping(value = "/configureFranchiseesList")
	public ModelAndView addconfigureFranchiseeList(HttpServletRequest request, HttpServletResponse response) {
		logger.info("/configureFranchisee List request mapping.");
		Constants.mainAct = 2;
		Constants.subAct = 15;
		ModelAndView mav = new ModelAndView("franchisee/frlist");
		try {

			RestTemplate restTemplate = new RestTemplate();

			ConfigureFrListResponse congigureFrList = restTemplate.getForObject(Constants.url + "getAllConfFr",
					ConfigureFrListResponse.class);
			List<ConfigureFrBean> configureFrList = new ArrayList<ConfigureFrBean>();
			configureFrList = congigureFrList.getConfigureFrBean();

			System.out.println("congi fr bean " + configureFrList.toString());
			System.out.println("fr count = " + configureFrList.size());
			mav.addObject("configureFrList", configureFrList);

			List<Item> itemList = new ArrayList<Item>();
			AllItemsListResponse itemListResponse = restTemplate.getForObject(Constants.url + "getAllItems",
					AllItemsListResponse.class);

			itemList = itemListResponse.getItems();
			System.out.println();
			List<ItemNameId> itemNames = new ArrayList<ItemNameId>();

			mav.addObject("configureFrList", configureFrList);

		} catch (Exception e) {
			System.out.println("exce in fr list fr controller" + e.getMessage());
		}

		return mav;
	}

	// ----------------------------------------END--------------------------------------------

	// -------------------------ADD NEW FRANCHISEE
	// PROCESS-------------------------------

	@RequestMapping(value = "/addNewFrProcess", method = RequestMethod.POST)
	public String displayLogin(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("fr_image") List<MultipartFile> file) {
		ModelAndView model = new ModelAndView("franchisee/addnewfranchisee");
		// ModelAndView model = new ModelAndView("orders/orders");

		String frName = request.getParameter("fr_name");
		System.out.println("1] fr name- " + frName);

		String frCode = request.getParameter("fr_code");
		System.out.println("2] Fr code - " + frCode);

		String frOpeningDate = request.getParameter("fr_opening_date");
		System.out.println("3] fr opening date - " + frOpeningDate);

		/*
		 * String frImage = request.getParameter("fr_image");
		 * System.out.println("4] fr image--- "+frImage);
		 */

		int frRouteId = Integer.parseInt(request.getParameter("fr_route_id"));
		System.out.println("5] fr route id " + frRouteId);

		int frRateCat = Integer.parseInt(request.getParameter("fr_rate_cat"));
		System.out.println("6] fr rate cat " + frRateCat);

		int frRate = Integer.parseInt(request.getParameter("fr_rate"));
		System.out.println("7] fr Rate " + frRate);

		String frCity = request.getParameter("fr_city");
		System.out.println("8] fr city " + frCity);

		int frKg1 = Integer.parseInt(request.getParameter("kg_1"));
		System.out.println("9] fr kg1 " + frKg1);

		int frKg2 = Integer.parseInt(request.getParameter("kg_2"));
		System.out.println("10] fr kg 2 " + frKg2);

		int frKg3 = Integer.parseInt(request.getParameter("kg_3"));
		System.out.println("10] fr kg 3 " + frKg3);

		int frKg4 = Integer.parseInt(request.getParameter("kg_4"));
		System.out.println("11] fr kg 4 " + frKg4);

		String frPassword = request.getParameter("fr_password");
		System.out.println("12] fr Password " + frPassword);

		String frEmail = request.getParameter("fr_email");
		System.out.println("13] fr email " + frEmail);

		String frMob = request.getParameter("fr_mob");
		System.out.println("14] frMobile " + frMob);

		String frOwner = request.getParameter("fr_owner");
		System.out.println("15] frOwner " + frOwner);

		int grnTwo = Integer.parseInt(request.getParameter("grn_two"));
		System.out.println("16] fr grn two " + grnTwo);

		int isSameState = Integer.parseInt(request.getParameter("is_same_state"));

		int delStatus = Integer.parseInt(request.getParameter("fr_status"));
		System.out.println("17fr del status " + delStatus);

		String ownerBirthDate = request.getParameter("fr_birth_date");
		System.out.println("18] ownerBirthDate " + ownerBirthDate);

		String fbaLicenseDate = request.getParameter("fba_license_date");
		System.out.println("19] frLicenseDate " + fbaLicenseDate);

		String frAgreementDate = request.getParameter("fr_agreement_date");
		System.out.println("20] frAgreementDate " + frAgreementDate);

		int frGstType = Integer.parseInt(request.getParameter("fr_gst_type"));
		System.out.println("21] frGstType " + frGstType);

		String frGstNo;
		if (frGstType == 0) {
			frGstNo = "NA";

		} else {
			frGstNo = request.getParameter("fr_gst_no");

		}
		System.out.println("22] frGstNo " + frGstNo);

		int stockType = Integer.parseInt(request.getParameter("stock_type"));
		System.out.println("23] stockType " + stockType);

		String frAddr = request.getParameter("fr_addr");
		System.out.println("25] frAddr " + frAddr);

		String frTarget = request.getParameter("fr_target");
		System.out.println("26] frTarget " + frTarget);

		// String frImage=ImageS3Util.uploadFrImage(file);
		VpsImageUpload upload = new VpsImageUpload();

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		System.out.println(sdf.format(cal.getTime()));

		String curTimeStamp = sdf.format(cal.getTime());

		try {

			upload.saveUploadedFiles(file, Constants.FR_IMAGE_TYPE,
					curTimeStamp + "-" + file.get(0).getOriginalFilename());
			System.out.println("upload method called " + file.toString());

		} catch (IOException e) {

			System.out.println("Exce in File Upload In Fr Add " + e.getMessage());
			e.printStackTrace();
		}

		RestTemplate rest = new RestTemplate();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("frCode", frCode);
		map.add("frOpeningDate", frOpeningDate);
		map.add("frName", frName);
		map.add("frImage", curTimeStamp + "-" + file.get(0).getOriginalFilename());
		map.add("frRouteId", frRouteId);
		map.add("frRateCat", frRateCat);
		map.add("frRate", frRate);
		map.add("frCity", frCity);
		map.add("frKg1", frKg1);
		map.add("frKg2", frKg2);
		map.add("frKg3", frKg3);
		map.add("frKg4", frKg4);
		map.add("frPassword", frPassword);
		map.add("frEmail", frEmail);
		map.add("frMob", frMob);
		map.add("frOwner", frOwner);
		map.add("grnTwo", grnTwo);
		map.add("delStatus", delStatus);

		map.add("ownerBirthDate", ownerBirthDate);
		map.add("fbaLicenseDate", fbaLicenseDate);
		map.add("frAgreementDate", frAgreementDate);
		map.add("frGstType", frGstType);
		map.add("frGstNo", frGstNo);
		map.add("stockType", stockType);
		map.add("frAddress", frAddr);
		map.add("frTarget", frTarget);
		map.add("isSameState", isSameState);

		ErrorMessage errorMessage = rest.postForObject(Constants.url + "saveFranchisee", map, ErrorMessage.class);
		if (errorMessage.getError()) {
			return "redirect:/listAllFranchisee";
		} else {

			return "redirect:/listAllFranchisee";

		}

	}
	// ----------------------------------------END--------------------------------------------

	// -------------------------CONFIGURE FRANCHISEE
	// PROCESS-------------------------------

	@RequestMapping(value = "/addFranchiseeProcess", method = RequestMethod.POST)
	public String configureFranchiseeProcess(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("franchisee/configureFr");
		try {

			String date[];
			String day[];
			String convertedDate = "0";
			String convertedDays = "0";
			String fromTime = request.getParameter("frm_time");

			System.out.println(fromTime);
			String toTime = request.getParameter("to_time");

			System.out.println(toTime);

			SimpleDateFormat format = new SimpleDateFormat("hh:mm a"); // if 24 hour format

			java.util.Date d1 = (java.util.Date) format.parse(fromTime);
			java.util.Date d2 = (java.util.Date) format.parse(toTime);

			java.sql.Time sqlFromTime = new java.sql.Time(d1.getTime());
			java.sql.Time sqlToTime = new java.sql.Time(d2.getTime());

			System.out.println("Converted From Time: " + sqlFromTime.toString() + " To time: " + sqlToTime.toString());

			int frId = Integer.parseInt(request.getParameter("fr_id"));
			System.out.println("FRID" + frId);

			int menuId = Integer.parseInt(request.getParameter("menu"));
			System.out.println("menuId" + menuId);

			System.out.println("seatlected catId:" + selectedCatId);

			String[] subCat = request.getParameterValues("items[]");
			System.out.println(" array is" + subCat[0]);

			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < subCat.length; i++) {
				sb = sb.append(subCat[i] + ",");

			}
			String items = sb.toString();
			items = items.substring(0, items.length() - 1);

			System.out.println("items" + items);

			int settingType = Integer.parseInt(request.getParameter("typeselector"));
			System.out.println("settingType" + settingType);

			if (settingType == 1) {
				// date ="0";
				// day = "0";
			} else if (settingType == 2) {
				date = request.getParameterValues("date[]");

				StringBuilder sbForDate = new StringBuilder();

				for (int i = 0; i < date.length; i++) {
					sbForDate = sbForDate.append(date[i] + ",");

				}
				convertedDate = sbForDate.toString();
				convertedDate = convertedDate.substring(0, convertedDate.length() - 1);

				System.out.println("date" + convertedDate);
				// day ="0";
			} else {
				day = request.getParameterValues("day[]");

				StringBuilder sbForDay = new StringBuilder();

				for (int i = 0; i < day.length; i++) {
					sbForDay = sbForDay.append(day[i] + ",");

				}
				convertedDays = sbForDay.toString();
				convertedDays = convertedDays.substring(0, convertedDays.length() - 1);
				System.out.println("day:" + convertedDays);

				// date ="0";
			}

			RestTemplate rest = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("fromTime", sqlFromTime.toString());
			map.add("toTime", sqlToTime.toString());
			map.add("frId", frId);
			map.add("menuId", menuId);
			map.add("catId", selectedCatId);
			map.add("itemShow", items);
			map.add("settingType", settingType);
			map.add("date", convertedDate);
			map.add("day", convertedDays);

			ErrorMessage errorMessage = rest.postForObject(Constants.url + "configureFranchisee", map,
					ErrorMessage.class);
			if (errorMessage.getError()) {
				return "redirect:/configureFranchiseesList";
			} else {

				return "redirect:/configureFranchiseesList";

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/configureFranchiseesList";
	}
	// ----------------------------------------END------------------------------------

	// ----------------------UPDATE CONFIGURED
	// FRANCHISEE------------------------------

	@RequestMapping(value = "/updateFranchiseeConf/updateFranchiseeProcess", method = RequestMethod.POST)
	public String updateConfFr(HttpServletRequest request, HttpServletResponse response) {
		String[] date;
		String[] day;
		String convertedDate = "0";
		String convertedDays = "0";
		ModelAndView model = new ModelAndView("franchisee/configureFr");
		try {
			String fromTime = "";
			fromTime = request.getParameter("frm_time");
			System.out.println("from time :get parameter =" + fromTime);
			String toTime = "";
			toTime = request.getParameter("to_time");
			System.out.println("to time :get parameter =" + toTime);

			SimpleDateFormat format = new SimpleDateFormat("hh:mm a"); // if 24 hour format

			java.util.Date d1 = (java.util.Date) format.parse(fromTime);
			java.util.Date d2 = (java.util.Date) format.parse(toTime);

			java.sql.Time sqlFromTime = new java.sql.Time(d1.getTime());
			java.sql.Time sqlToTime = new java.sql.Time(d2.getTime());

			System.out.println(" converted sql from time=" + sqlFromTime);
			System.out.println("converted sql to time=" + sqlToTime);

			int settingType = Integer.parseInt(request.getParameter("typeselector"));
			System.out.println(" type selector :get parameter" + settingType);

			int settingId = Integer.parseInt(request.getParameter("settingId"));
			System.out.println(" setting Id :get Parameter " + settingId);

			String[] itemShow = request.getParameterValues("items[]");
			System.out.println("str item show: get Parameter " + itemShow);

			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < itemShow.length; i++) {
				sb = sb.append(itemShow[i] + ",");

			}

			String strItems = sb.toString();
			strItems = strItems.substring(0, strItems.length() - 1);
			System.out.println("item id list is" + strItems.toString());

			if (settingType == 1) {
				// date =0;
				// day =0;
			} else if (settingType == 2) {
				date = request.getParameterValues("date[]");

				StringBuilder sbForDate = new StringBuilder();

				for (int i = 0; i < date.length; i++) {
					sbForDate = sbForDate.append(date[i] + ",");

				}
				convertedDate = sbForDate.toString();
				convertedDate = convertedDate.substring(0, convertedDate.length() - 1);
				System.out.println("date" + convertedDate);

			} else {
				day = request.getParameterValues("day[]");

				StringBuilder sbForDay = new StringBuilder();

				for (int i = 0; i < day.length; i++) {
					sbForDay = sbForDay.append(day[i] + ",");

				}
				convertedDays = sbForDay.toString();
				convertedDays = convertedDays.substring(0, convertedDays.length() - 1);
				System.out.println("convertedDays" + convertedDays);

			}

			RestTemplate rest = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("fromTime", sqlFromTime.toString());
			map.add("toTime", sqlToTime.toString());

			map.add("settingType", settingType);
			map.add("day", convertedDays);
			map.add("date", convertedDate);
			map.add("itemShow", strItems);
			map.add("settingId", settingId);

			ErrorMessage errorMessage = rest.postForObject(Constants.url + "updateConfFr", map, ErrorMessage.class);
			if (errorMessage.getError()) {
				return "redirect:/configureFranchiseesList";
			} else {

				return "redirect:/configureFranchiseesList";

			}
		} catch (Exception e) {

			System.out.println("Exc is ==" + e.getMessage());
		}

		return "redirect:/configureFranchiseesList";
	}

	// ----------------------------------------END--------------------------------------------

	public List<Item> itemById(int catId) {

		System.out.println("menuId " + catId);

		System.out.println("Finding Item List for Selected CatId=" + catId);

		List<Item> filterItemsList = new ArrayList<Item>();

		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("itemGrp1", catId);

		Item[] item = restTemplate.postForObject(Constants.url + "getItemsByCatId", map, Item[].class);
		ArrayList<Item> itemList = new ArrayList<Item>(Arrays.asList(item));
		System.out.println("Filter Item List " + itemList.toString());

		return itemList;

	}

	// ----------------------------------------END--------------------------------------------

	public List<SpecialCake> spCakeById(int catId) {

		System.out.println("catId " + catId);

		List<SpecialCake> specialCakeList = new ArrayList<SpecialCake>();

		RestTemplate restTemplate = new RestTemplate();

		SpCakeResponse spCakeResponse = restTemplate.getForObject(Constants.url + "showSpecialCakeList",
				SpCakeResponse.class);
		System.out.println("SpCake Controller SpCakeList Response " + spCakeResponse.toString());

		specialCakeList = spCakeResponse.getSpecialCake();

		return specialCakeList;

	}

	// ----------------------------------------END--------------------------------------------

	// ----------------------EDIT CONFIGURED FRANCHISEE FORM
	// SHOW------------------------

	@RequestMapping(value = "/updateFranchiseeConf/{settingId}", method = RequestMethod.GET)
	public ModelAndView updateFranchiseeConf(@PathVariable int settingId) {

		ModelAndView model = new ModelAndView("franchisee/editConfigureFr");

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		franchiseeAndMenuList = restTemplate.getForObject(Constants.url + "getFranchiseeAndMenu",
				FranchiseeAndMenuList.class);

		System.out.println("Franchisee Response " + franchiseeAndMenuList.getAllFranchisee());

		model.addObject("franchiseeAndMenuList", franchiseeAndMenuList);

		ConfigureFrBean franchiseeList = restTemplate.getForObject(
				Constants.url + "getFrConfUpdate?settingId={settingId}", ConfigureFrBean.class, settingId);
		model.addObject("franchiseeList", franchiseeList);

		System.out.println("-------------SELECTED FRANCHISEE-------------" + franchiseeList.getItemShow());

		franchiseeList.getSettingId();

		String date = franchiseeList.getDate();
		List<String> dateList = Arrays.asList(date.split("\\s*,\\s*"));

		System.out.println("selected franchisee is" + franchiseeList.toString());
		int frId = franchiseeList.getFrId();

		FranchiseeList frList = restTemplate.getForObject(Constants.url + "getFranchisee?frId={frId}",
				FranchiseeList.class, frId);
		String frName = frList.getFrName();
		System.out.println("fr name== " + frName);
		model.addObject("frName", frName);

		menuList = franchiseeAndMenuList.getAllMenu();
		String menuName = "";
		System.out.println("menu titles are " + menuList.toString());

		for (int i = 0; i < menuList.size(); i++) {
			if (menuList.get(i).getMenuId() == franchiseeList.getMenuId()) {
				menuName = menuList.get(i).getMenuTitle();

				if (menuList.get(i).getMainCatId() == 5) {
					List<SpecialCake> getSpCakeByCatId = spCakeById(menuList.get(i).getMainCatId());

					System.out
							.println("-------------getSpCakeByCatId--------------------" + getSpCakeByCatId.toString());
					List<SpecialCake> tempAllSpCkList = getSpCakeByCatId;

					System.out.println("-------------tempAllSpCkList--------------------" + tempAllSpCkList.toString());

					List<SpecialCake> selectedSpCk = new ArrayList<SpecialCake>();

					String frPrevItems = franchiseeList.getItemShow();
					System.out.println("-------------frPrevItems--------------------" + frPrevItems.toString());

					List<String> frPrevItemsList = Arrays.asList(frPrevItems.split("\\s*,\\s*"));

					System.out.println("-------------frPrevItemsList--------------------" + frPrevItemsList.toString());

					for (int j = 0; j < frPrevItemsList.size(); j++) {

						for (int k = 0; k < getSpCakeByCatId.size(); k++) {

							if (Integer.parseInt(frPrevItemsList.get(j)) == (getSpCakeByCatId.get(k).getSpId())) {
								// System.out.println("-------------if--------------------"+frPrevItemsList.size()+"----"+frPrevItemsList.get(j));
								// System.out.println("-------------if--------------------"+getSpCakeByCatId.size()+"---"+getSpCakeByCatId.get(k).getSpId());

								selectedSpCk.add(getSpCakeByCatId.get(k));
								tempAllSpCkList.remove(k);
							}

						}

					}
					System.out.println("-------------selectedSpCk--------------------" + selectedSpCk.toString());

					model.addObject("selectedItems", selectedSpCk);
					System.out.println("-------------tempAllSpCkList--------------------" + tempAllSpCkList.toString());

					model.addObject("remItems", tempAllSpCkList);
					model.addObject("catId", menuList.get(i).getMainCatId());
				} else {
					List<Item> getItemByMenuId = itemById(menuList.get(i).getMainCatId());
					List<Item> tempAllItemsList = getItemByMenuId;
					List<Item> selectedItems = new ArrayList<Item>();
					String frPrevItems = franchiseeList.getItemShow();

					List<String> frPrevItemsList = Arrays.asList(frPrevItems.split("\\s*,\\s*"));
					System.out.println("frPrevItemsList SiZe: " + frPrevItemsList.size());
					System.out.println("getItemByMenuId SiZe: " + getItemByMenuId.size());

					int countOuter = 0;
					int countInner = 0;

					for (int l = 0; l < frPrevItemsList.size(); l++) {

						countOuter = countOuter + 1;
						for (int m = 0; m < getItemByMenuId.size(); m++) {
							countInner = countInner + 1;

							if (Integer.parseInt(frPrevItemsList.get(l)) == getItemByMenuId.get(m).getId()) {

								selectedItems.add(getItemByMenuId.get(m));
								tempAllItemsList.remove(m);

							}

						}

					}

					System.out.println("Outer " + countOuter + " inner " + countInner);
					model.addObject("selectedItems", selectedItems);

					model.addObject("remItems", tempAllItemsList);
					model.addObject("catId", menuList.get(i).getMainCatId());
				}

			}

		}

		System.out.println("menu name= ==" + menuName);

		model.addObject("menuName", menuName);

		System.out.println("Franchisee menu  id ******* : " + franchiseeList.getMenuId());// new
		int menuId = franchiseeList.getMenuId();

		int settingType = franchiseeList.getSettingType();
		model.addObject("settingType", settingType);

		String fromTime = franchiseeList.getFromTime();
		String toTime = franchiseeList.getToTime();

		try {
			final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			final Date fromDate = sdf.parse(fromTime);
			final Date toDate = sdf.parse(toTime);

			System.out.println("From Time " + fromDate);
			System.out.println("To Time " + toDate);

			System.out.println(new SimpleDateFormat("hh:mm a").format(fromDate));

			System.out.println(new SimpleDateFormat("hh:mm a").format(toDate));

			model.addObject("fromTime", new SimpleDateFormat("hh:mm a").format(fromDate));
			model.addObject("toTime", new SimpleDateFormat("hh:mm a").format(toDate));
		} catch (final ParseException e) {
			e.printStackTrace();
		}

		String day = franchiseeList.getDay();
		List<String> dayList = Arrays.asList(day.split("\\s*,\\s*"));
		System.out.println("days" + dayList.toString());
		List<String> dates = new ArrayList<>();

		for (int i = 1; i <= 32; i++) {
			dates.add("" + i);

		}
		HashMap<String, String> allDays = new HashMap<String, String>();
		allDays.put("1", "Sunday");
		allDays.put("2", "Monday");
		allDays.put("3", "Tuesday");
		allDays.put("4", "Wednesday");
		allDays.put("5", "Thursday");
		allDays.put("6", "Friday");
		allDays.put("7", "Saturday");

		model.addObject("frDay", dayList);
		model.addObject("remDate", dates);
		model.addObject("allDays", allDays);
		model.addObject("date", dateList);

		model.addObject("franchiseeList", franchiseeList);

		model.addObject("url", Constants.FR_IMAGE_URL);

		return model;

	}
	// ----------------------------------------END---------------------------------------------------------
	// ---------------------------------------LIST ALL
	// FRANCHISEE--------------------------------------------

	@RequestMapping(value = "/listAllFranchisee")
	public ModelAndView listAllFranchisee(HttpServletRequest request, HttpServletResponse response) {
		Constants.mainAct = 1;
		Constants.subAct = 3;
		RestTemplate restTemplate = new RestTemplate();
		AllFranchiseeList allFranchiseeList = restTemplate.getForObject(Constants.url + "getAllFranchisee",
				AllFranchiseeList.class);
		AllRoutesListResponse allRoutesListResponse = restTemplate.getForObject(Constants.url + "showRouteList",
				AllRoutesListResponse.class);

		List<Route> routeList = new ArrayList<Route>();
		routeList = allRoutesListResponse.getRoute();

		ModelAndView mav = new ModelAndView("franchisee/listAllFranchisee");

		List<FranchiseeList> franchiseeList = new ArrayList<FranchiseeList>();
		franchiseeList = allFranchiseeList.getFranchiseeList();
		System.out.println("Franchisee List:" + franchiseeList.toString());

		mav.addObject("franchiseeList", franchiseeList);
		mav.addObject("routeList", routeList);
		mav.addObject("url", Constants.FR_IMAGE_URL);

		// exportToExcel

		com.ats.adminpanel.model.mastexcel.FranchiseeList frList = restTemplate.getForObject(
				Constants.url + "tally/getAllExcelFranchise", com.ats.adminpanel.model.mastexcel.FranchiseeList.class);

		List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

		ExportToExcel expoExcel = new ExportToExcel();
		List<String> rowData = new ArrayList<String>();

		rowData.add("Sr. No.");
		rowData.add("Fr Id");
		rowData.add("Franchisee Code");
		rowData.add("Franchisee Name");
		rowData.add("Rate Cat");
		rowData.add("City");
		rowData.add("Address 1");
		rowData.add("Address 2");
		rowData.add("State");
		rowData.add("GSTIN");

		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);

		List<Franchisee> franchisee = frList.getFranchiseeList();

		for (int i = 0; i < franchisee.size(); i++) {
			expoExcel = new ExportToExcel();
			rowData = new ArrayList<String>();

			rowData.add("" + (i + 1));
			rowData.add("" + franchisee.get(i).getCustomerId());
			rowData.add(franchisee.get(i).getFrCode());
			rowData.add(franchisee.get(i).getCustomerName());
			rowData.add(""+franchisee.get(i).getFrRateCat());
			rowData.add(franchisee.get(i).getCity());
			rowData.add(franchisee.get(i).getAddress1());

			rowData.add(franchisee.get(i).getAddress2());

			rowData.add(franchisee.get(i).getState());
			rowData.add(franchisee.get(i).getGSTIN());

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);

		}

		HttpSession session = request.getSession();
		session.setAttribute("exportExcelList", exportToExcelList);
		session.setAttribute("excelName", "frList");
		return mav;
	}
	// ----------------------------------------END---------------------------------------------------------

	// ---------------------------------------GET COMMON ITEMS BY MENU
	// ID--------------------------------------------
	@RequestMapping(value = "/getCommonByMenuId", method = RequestMethod.GET)
	public @ResponseBody List<CommonConf> commonItemById(@RequestParam(value = "menuId", required = true) int menuId) {

		System.out.println("menuId " + menuId);

		RestTemplate restTemplate = new RestTemplate();
		List<Item> itemsList = new ArrayList<Item>();

		menuList = franchiseeAndMenuList.getAllMenu();
		Menu frMenu = new Menu();
		for (Menu menu : menuList) {
			if (menu.getMenuId() == menuId) {
				frMenu = menu;
				break;
			}
		}
		selectedCatId = frMenu.getMainCatId();

		System.out.println("Finding Item List for Selected CatId=" + selectedCatId);

		List<SpecialCake> specialCakeList = new ArrayList<SpecialCake>();
		List<Item> filterItemsList = new ArrayList<Item>();

		List<CommonConf> commonConfList = new ArrayList<CommonConf>();

		if (selectedCatId == 5) {
			SpCakeResponse spCakeResponse = restTemplate.getForObject(Constants.url + "showSpecialCakeList",
					SpCakeResponse.class);
			System.out.println("SpCake Controller SpCakeList Response " + spCakeResponse.toString());

			specialCakeList = spCakeResponse.getSpecialCake();

			for (SpecialCake specialCake : specialCakeList) {
				CommonConf commonConf = new CommonConf();
				commonConf.setId(specialCake.getSpId());
				commonConf.setName(specialCake.getSpCode() + "-" + specialCake.getSpName());
				commonConfList.add(commonConf);
				System.out.println("spCommonConf" + commonConf.toString());
			}

			System.out.println("------------------------");
		} else {
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("itemGrp1", selectedCatId);

			Item[] item = restTemplate.postForObject(Constants.url + "getItemsByCatId", map, Item[].class);
			ArrayList<Item> itemList = new ArrayList<Item>(Arrays.asList(item));
			System.out.println("Filter Item List " + itemList.toString());

			for (Item items : itemList) {
				CommonConf commonConf = new CommonConf();
				commonConf.setId(items.getId());
				commonConf.setName(items.getItemName());
				commonConfList.add(commonConf);
				System.out.println("itemCommonConf" + commonConf.toString());
			}
			System.out.println("------------------------");
		}

		return commonConfList;
	}

	// ----------------------------------------END---------------------------------------------------------
	// --------------------------------------GET ALL FR
	// MENUS(AJAX)------------------------------------------------------
	@RequestMapping(value = "/getAllMenu", method = RequestMethod.GET)
	public @ResponseBody List<Menu> findAllMenus() {
		logger.debug("finding all Menu");
		AllMenuResponse allMenus = new AllMenuResponse();
		try {
			RestTemplate restTemplate = new RestTemplate();

			allMenus = restTemplate.getForObject(Constants.url + "getAllMenu", AllMenuResponse.class);

			System.out.println("Franchisee Controller Response " + allFranchiseeAndMenuList.getAllMenu());

		} catch (Exception e) {
			System.out.println("Franchisee Controller Exception " + e.getMessage());
		}

		return allMenus.getMenuConfigurationPage();
	}
	// ----------------------------------------END---------------------------------------------------------
	// ----------------------------------------DELETE FRANCHISEE
	// PROCESS-----------------------------------

	@RequestMapping(value = "/deleteFranchisee/{frId}", method = RequestMethod.GET)
	public String deleteItem(@PathVariable int frId) {

		// String id=request.getParameter("id");

		ModelAndView mav = new ModelAndView("franchisee/listAllFranchisee");

		try {
			RestTemplate rest = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("frId", frId);

			ErrorMessage errorResponse = rest.postForObject(Constants.url + "deleteFranchisee", map,
					ErrorMessage.class);
			System.out.println(errorResponse.toString());

			Info info = rest.postForObject(Constants.url + "deleteFranchiseSup", map, Info.class);
			System.out.println(info.toString());

			if (errorResponse.getError()) {
				return "redirect:/listAllFranchisee";

			} else {
				return "redirect:/listAllFranchisee";

			}
		} catch (Exception e) {
			System.out.println("Exc In Del Fr");
		}
		return "redirect:/listAllFranchisee";
	}

	// ----------------------------------------END---------------------------------------------------------
	// --------------------------------------UPDATE ONE FRANCHISEE BY ID
	// PROCESS---------------------------

	@RequestMapping(value = "/updateFranchisee/{frId}", method = RequestMethod.GET)
	public ModelAndView updateFranchisee(@PathVariable int frId) {
		ModelAndView model = new ModelAndView("franchisee/editFranchisee");

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		map.add("frId", frId);
		RestTemplate restTemplate = new RestTemplate();

		FranchiseeList franchiseeList = restTemplate.getForObject(Constants.url + "getFranchisee?frId={frId}",
				FranchiseeList.class, frId);

		System.out.println("selected franchisee is" + franchiseeList.toString());

		model.addObject("franchiseeList", franchiseeList);

		AllRoutesListResponse allRoutesListResponse = restTemplate.getForObject(Constants.url + "showRouteList",
				AllRoutesListResponse.class);

		List<Route> routeList = new ArrayList<Route>();
		routeList = allRoutesListResponse.getRoute();
		// System.out.println("Route List" + routeList.toString());

		int frrouteid = franchiseeList.getFrRouteId();
		StringBuilder frRouteName = new StringBuilder();

		for (int i = 0; i < routeList.size(); i++) {

			if (routeList.get(i).getRouteId() == (franchiseeList.getFrRouteId()))

				frRouteName.append(routeList.get(i).getRouteName());
		}
		System.out.println("route name is" + frRouteName);
		model.addObject("frRouteName", frRouteName);

		for (int j = 0; j < routeList.size(); j++) {

			if (routeList.get(j).getRouteId() == franchiseeList.getFrRouteId()) {
				routeList.remove(j);

			}
		}

		model.addObject("routeList", routeList);

		String strRateCat = null;

		int rating = franchiseeList.getFrRate();

		System.out.println("fr  ratecat ===" + rating);

		switch (rating) {
		case 0:
			strRateCat = "Rating 0.5";
			break;
		case 1:
			strRateCat = "Rating 1";
			break;
		case 2:
			strRateCat = "Rating 1.5";
			break;
		case 3:
			strRateCat = "Rating 2";
			break;
		case 4:
			strRateCat = "Rating 2.5";
			break;
		case 5:
			strRateCat = "Rating 3";
			break;
		case 6:
			strRateCat = "Rating 3.5";
			break;
		case 7:
			strRateCat = "Rating 4";
			break;
		case 8:
			strRateCat = "Rating 4.5";
			break;
		case 9:
			strRateCat = "Rating 5";
			break;

		default:
			strRateCat = "";
			break;

		}
		System.out.println("fr  strRateCat ===" + strRateCat);

		model.addObject("strRateCat", strRateCat);

		int ratetype = franchiseeList.getFrRateCat();
		String strRatetype = String.valueOf(ratetype);
		System.out.println("fr rate type ===" + strRatetype);

		int grn2 = franchiseeList.getGrnTwo();
		String strGrnTwo = String.valueOf(grn2);
		model.addObject("strGrnTwo", strGrnTwo);
		System.out.println("Str grn two is" + strGrnTwo);

		model.addObject("ratetype", strRatetype);

		int frStatus = franchiseeList.getDelStatus();
		String strFrStatus = String.valueOf(frStatus);

		FrItemStockConfiResponse frItemStockConfiResponse = restTemplate
				.getForObject(Constants.url + "getfrItemConfSetting", FrItemStockConfiResponse.class);
		List<FrItemStockConfigure> frItemStockConfigures = new ArrayList<FrItemStockConfigure>();
		frItemStockConfigures = frItemStockConfiResponse.getFrItemStockConfigure();

		for (int i = 0; i < frItemStockConfigures.size(); i++) {

			if (frItemStockConfigures.get(i).getSettingKey().equals("frItemStockType")) {
				settingValue = frItemStockConfigures.get(i).getSettingValue();
			}

		}
		frItemStockConfigures.remove(0);

		// passing static arraylist
		ArrayList<String> list = new ArrayList();

		list.add("Rating 0.5");
		list.add("Rating 1");
		list.add("Rating 1.5");
		list.add("Rating 2");
		list.add("Rating 2.5");
		list.add("Rating 3");
		list.add("Rating 3.5");
		list.add("Rating 4");
		list.add("Rating 4.5");
		list.add("Rating 5");
		model.addObject("ratingList", list);

		model.addObject("strFrStatus", strFrStatus);
		model.addObject("url", Constants.FR_IMAGE_URL);
		model.addObject("settingValue", settingValue);
		model.addObject("frItemStockConfigures", frItemStockConfigures);
		return model;

	}
	// ----------------------------------------END---------------------------------------------------------
	// ----------------------------------------UPDATE FRACHISEE
	// PROCESS-------------------------------------

	@RequestMapping(value = "/updateFranchisee/updateFrProcess", method = RequestMethod.POST)
	public String updateFrProcess(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("fr_image") List<MultipartFile> file) {
		ModelAndView model = new ModelAndView("franchisee/addnewfranchisee");

		try {
			String frName = request.getParameter("fr_name");
			System.out.println("18] frName " + frName);

			String frCode = request.getParameter("fr_code");
			System.out.println("18] frCode " + frCode);

			int frId = Integer.parseInt(request.getParameter("frId"));
			System.out.println("18] frId " + frId);

			String frOpeningDate = request.getParameter("fr_opening_date");
			System.out.println("18] frOpeningDate " + frOpeningDate);

			/*
			 * String frImage = request.getParameter("fr_image");
			 */
			int frRouteId = Integer.parseInt(request.getParameter("fr_route_id"));
			System.out.println("18] frRouteId " + frRouteId);

			int frRateCat = Integer.parseInt(request.getParameter("fr_rate_cat"));
			System.out.println("18] frRateCat " + frRateCat);

			int frRate = Integer.parseInt(request.getParameter("fr_rate"));
			System.out.println("18] frRate " + frRate);

			String frCity = request.getParameter("fr_city");
			System.out.println("18] frCity " + frCity);

			int frKg1 = Integer.parseInt(request.getParameter("kg_1"));
			System.out.println("18] frKg1 " + frKg1);

			int frKg2 = Integer.parseInt(request.getParameter("kg_2"));
			System.out.println("18] frKg2 " + frKg2);

			int frKg3 = Integer.parseInt(request.getParameter("kg_3"));
			System.out.println("18] frKg3 " + frKg3);

			int frKg4 = Integer.parseInt(request.getParameter("kg_4"));
			System.out.println("18] frKg4 " + frKg4);

			String frPassword = request.getParameter("fr_password");
			System.out.println("18] frPassword " + frPassword);

			String frEmail = request.getParameter("fr_email");
			System.out.println("18] frEmail " + frEmail);

			String frMob = request.getParameter("fr_mob");
			System.out.println("18] frMob " + frMob);

			String frOwner = request.getParameter("fr_owner");
			System.out.println("18] frOwner " + frOwner);
			int isSameState = Integer.parseInt(request.getParameter("is_same_state"));

			int grnTwo = Integer.parseInt(request.getParameter("grn_two"));
			System.out.println("18] grnTwo " + grnTwo);

			int delStatus = Integer.parseInt(request.getParameter("fr_status"));
			System.out.println("18] delStatus " + delStatus);

			String ownerBirthDate = request.getParameter("fr_birth_date");
			System.out.println("18] ownerBirthDate " + ownerBirthDate);

			String frLicenseDate = request.getParameter("fr_license_date");
			System.out.println("19] frLicenseDate " + frLicenseDate);

			String frAgreementDate = request.getParameter("fr_agreement_date");
			System.out.println("20] frAgreementDate " + frAgreementDate);

			int frGstType = Integer.parseInt(request.getParameter("fr_gst_type"));
			System.out.println("21] frGstType " + frGstType);

			String frGstNo;
			if (frGstType == 0) {
				frGstNo = "NA";

			} else {
				frGstNo = request.getParameter("fr_gst_no");

			}

			System.out.println("22] frGstNo " + frGstNo);

			String stockType = request.getParameter("stock_type");
			System.out.println("23] stockType " + stockType);

			String frAddr = request.getParameter("fr_addr");
			System.out.println("25] frAddr " + frAddr);

			String frTarget = request.getParameter("fr_target");
			System.out.println("26] frTarget " + frTarget);
			// System.out.println("del status selected is==:" + delStatus);

			System.out.println("fr opening from  ==:" + frOpeningDate);

			String frImage = request.getParameter("prevImage");

			if (!file.get(0).getOriginalFilename().equalsIgnoreCase("")) {

				VpsImageUpload upload = new VpsImageUpload();

				Calendar cal = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
				System.out.println(sdf.format(cal.getTime()));

				String curTimeStamp = sdf.format(cal.getTime());

				try {
					frImage = curTimeStamp + "-" + file.get(0).getOriginalFilename();
					upload.saveUploadedFiles(file, Constants.FR_IMAGE_TYPE,
							curTimeStamp + "-" + file.get(0).getOriginalFilename());
					System.out.println("upload method called " + file.toString());

				} catch (IOException e) {

					System.out.println("Exce in File Upload In Fr Update Process " + e.getMessage());
					e.printStackTrace();
				}
			}

			RestTemplate rest = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("frId", frId);
			map.add("frCode", frCode);
			map.add("frOpeningDate", frOpeningDate);
			map.add("frName", frName);
			map.add("frImage", frImage);
			map.add("frRouteId", frRouteId);
			map.add("frRateCat", frRateCat);
			map.add("frRate", frRate);
			map.add("frCity", frCity);
			map.add("frKg1", frKg1);
			map.add("frKg2", frKg2);
			map.add("frKg3", frKg3);
			map.add("frKg4", frKg4);
			map.add("frPassword", frPassword);
			map.add("frEmail", frEmail);
			map.add("frMob", frMob);
			map.add("frOwner", frOwner);
			map.add("grnTwo", grnTwo);
			map.add("delStatus", delStatus);

			map.add("ownerBirthDate", ownerBirthDate);
			map.add("fbaLicenseDate", frLicenseDate);
			map.add("frAgreementDate", frAgreementDate);
			map.add("frGstType", frGstType);
			map.add("frGstNo", frGstNo);
			map.add("stockType", stockType);
			map.add("frAddress", frAddr);
			map.add("frTarget", frTarget);
			map.add("isSameState", isSameState);
			ErrorMessage errorMessage = rest.postForObject(Constants.url + "updateFranchisee", map, ErrorMessage.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "redirect:/listAllFranchisee";

	}

	// ----------------------------------------END---------------------------------------------------------
	List<String> frIdArray = new ArrayList<String>();

	// -------------------------CONFIGURE FRANCHISEEs SPECIAL DAY CAKE NEW FORM
	// SHOW-------------------------------
	@RequestMapping(value = "/configureSpecialDayCake")
	public ModelAndView showConfigureSpecialDayCake(HttpServletRequest request, HttpServletResponse response) {

		logger.info("/configureFranchisee request mapping.");

		ModelAndView mav = new ModelAndView("franchisee/specialDayCkConf");
		Constants.mainAct = 2;
		Constants.subAct = 16;

		try {
			RestTemplate restTemplate = new RestTemplate();
			franchiseeAndMenuList = restTemplate.getForObject(Constants.url + "getFranchiseeAndMenu",
					FranchiseeAndMenuList.class);

			System.out.println("Franchisee Response " + franchiseeAndMenuList.getAllFranchisee());
			List<Menu> menuList = franchiseeAndMenuList.getAllMenu();
			System.out.println("menu Response " + menuList.toString());

			for (FranchiseeList franchisee : franchiseeAndMenuList.getAllFranchisee()) {
				frIdArray.add(String.valueOf(franchisee.getFrId()));
				// System.out.println("frIdArray"+frIdArray.toString());
			}
			Menu frMenu = new Menu();
			for (Menu menu : menuList) {
				if (menu.getMainCatId() == 6) {
					frMenu = menu;
					break;
				}
			}
			int catId = 6;
			int menuId = frMenu.getMenuId();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("itemGrp1", catId);

			Item[] item = restTemplate.postForObject(Constants.url + "getItemsByCatId", map, Item[].class);
			ArrayList<Item> itemList = new ArrayList<Item>(Arrays.asList(item));
			System.out.println("Filter Item List " + itemList.toString());

			mav.addObject("catId", catId);
			mav.addObject("menuId", menuId);
			mav.addObject("itemList", itemList);
			mav.addObject("allFranchiseeAndMenuList", franchiseeAndMenuList);

		} catch (Exception e) {
			System.out.println("Franchisee Controller Exception " + e.getMessage());
			e.printStackTrace();
		}

		return mav;
	}
	// ----------------------------------------END--------------------------------------------
	// ----------------------------------CONFIGURE SPECIAL DAY CAKE LIST
	// SHOW-----------------------

	@RequestMapping(value = "/configureSpecialDayCkList")
	public ModelAndView configureSpecialDayCkList(HttpServletRequest request, HttpServletResponse response) {
		logger.info("/configureFranchisee List request mapping.");
		Constants.mainAct = 2;
		Constants.subAct = 17;
		ModelAndView mav = new ModelAndView("franchisee/specialDayCkConfList");
		try {

			RestTemplate restTemplate = new RestTemplate();

			ConfiguredSpDayCkResponse configuredSpDayCkRes = restTemplate
					.getForObject(Constants.url + "getConfiguredSpDayCkList", ConfiguredSpDayCkResponse.class);

			List<GetConfiguredSpDayCk> configureSpDayFrList = new ArrayList<GetConfiguredSpDayCk>();
			configureSpDayFrList = configuredSpDayCkRes.getConfiguredSpDayCkList();

			mav.addObject("configureSpDayFrList", configureSpDayFrList);

		} catch (Exception e) {

			System.out.println("configureSpecialDayCkList Fr controller" + e.getMessage());
			mav.addObject("configureSpDayFrList", "");
		}

		return mav;
	}

	// ----------------------------------------END------------------------------------------------------
	@RequestMapping(value = "/addFrSpDayCkProcess", method = RequestMethod.POST)
	public String addFrSpDayCkProcess(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("franchisee/specialDayCkConfList");

		try {
			String spdayName = request.getParameter("event_name");
			System.out.println("EventName" + spdayName);

			// ----------Getting Selected FrIds List and Make , Separated String--------
			String[] frList = request.getParameterValues("frId[]");
			System.out.println("frList" + frList);

			StringBuilder frIdStringbuilder = new StringBuilder();

			// if All Franchisee Selected All FrIDS added to frIdStringBuilder
			if (Arrays.asList(frList).contains("-1")) {

				for (String strFr : frIdArray) {

					frIdStringbuilder = frIdStringbuilder.append(strFr + ",");

				}
			} else {
				for (int i = 0; i < frList.length; i++) {
					frIdStringbuilder = frIdStringbuilder.append(frList[i] + ",");

				}
			}

			String frIdList = frIdStringbuilder.toString();// , separated
			frIdList = frIdList.substring(0, frIdList.length() - 1);

			System.out.println("frIdList" + frIdList);
			// -----------------------------------------------------------------------

			// ----------Getting Selected ItemId List and Make , Separated String--------
			String[] itemIdList = request.getParameterValues("items[]");
			System.out.println(" array is" + itemIdList[0]);

			StringBuilder itemStringbuilder = new StringBuilder();

			for (int i = 0; i < itemIdList.length; i++) {
				itemStringbuilder = itemStringbuilder.append(itemIdList[i] + ",");

			}
			String items = itemStringbuilder.toString();
			items = items.substring(0, items.length() - 1);

			System.out.println("items" + items);
			// -----------------------------------------------------------------------

			// ----------FROM TIME AND TO TIME GETTING AND CONVERSION-----------------
			String date;
			int day;

			String fromTime = request.getParameter("frm_time");
			String toTime = request.getParameter("to_time");

			SimpleDateFormat format = new SimpleDateFormat("hh:mm a"); // if 24 hour format

			java.util.Date d1 = (java.util.Date) format.parse(fromTime);
			java.util.Date d2 = (java.util.Date) format.parse(toTime);

			java.sql.Time sqlFromTime = new java.sql.Time(d1.getTime());
			java.sql.Time sqlToTime = new java.sql.Time(d2.getTime());
			System.out.println("Converted From Time: " + sqlFromTime.toString() + " To time: " + sqlToTime.toString());
			// -----------------------------------------------------------------------

			String orderFromDate = request.getParameter("from_order_date");
			System.out.println("orderFromDate: " + orderFromDate);

			String orderToDate = request.getParameter("to_order_date");
			System.out.println("orderToDate: " + orderToDate);

			String deliveryFromDate = request.getParameter("from_delivery_date");
			System.out.println("deliveryFromDate: " + deliveryFromDate);

			String deliveryToDate = request.getParameter("to_delivery_date");
			System.out.println("deliveryToDate: " + deliveryToDate);

			int spdayId = Integer.parseInt(request.getParameter("sp_day_id"));
			System.out.println("spdayId: " + spdayId);

			int menuId = Integer.parseInt(request.getParameter("menu_id"));
			System.out.println("menuId: " + menuId);

			int catId = Integer.parseInt(request.getParameter("cat_id"));
			System.out.println("catId: " + catId);

			RestTemplate rest = new RestTemplate();

			SpDayConfigure spDayConfigure = new SpDayConfigure();

			spDayConfigure.setSpdayId(spdayId);
			spDayConfigure.setSpdayName(spdayName);
			spDayConfigure.setDeliveryFromDate(deliveryFromDate);
			spDayConfigure.setDeliveryToDate(deliveryToDate);
			spDayConfigure.setFromTime(sqlFromTime);
			spDayConfigure.setToTime(sqlToTime);
			spDayConfigure.setItemId(items);
			spDayConfigure.setOrderFromDate(orderFromDate);
			spDayConfigure.setOrderToDate(orderToDate);
			spDayConfigure.setFrId(frIdList);
			spDayConfigure.setMenuId(menuId);
			spDayConfigure.setCatId(catId);
			spDayConfigure.setDelStatus(0);
			spDayConfigure.setSubCatId(22); // hardcoded
			ErrorMessage errorMessage = null;
			try {
				HttpHeaders httpHeaders = new HttpHeaders();
				httpHeaders.set("Content-Type", "application/json");

				ObjectMapper mapper = new ObjectMapper();
				String jsonInString = mapper.writeValueAsString(spDayConfigure);
				System.out.println("All Sp Order Data" + jsonInString.toString());

				HttpEntity<String> httpEntity = new HttpEntity<String>(jsonInString.toString(), httpHeaders);

				RestTemplate restTemplate = new RestTemplate();
				errorMessage = restTemplate.postForObject(Constants.url + "/configureSpDayCk", httpEntity,
						ErrorMessage.class);
				System.out.println("SpDayCake Configured");
			} catch (Exception e) {
				System.out.println("EXC IN CONVERTION JSON OF SAME DAY CAKE");
			}

			if (errorMessage.getError()) {
				return "redirect:/configureSpecialDayCkList";
			} else {

				return "redirect:/configureSpecialDayCkList";

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/configureSpecialDayCkList";
	}
	// ----------------------------------------END--------------------------------------------
	// ----------------------------------------DELETE CONFIGURED SPECIAL DAY CAKE
	// PROCESS-----------------------------------

	@RequestMapping(value = "/deleteConfSpDayCk/{spdayId}", method = RequestMethod.GET)
	public String deleteConfSpDayCk(@PathVariable int spdayId) {

		// String id=request.getParameter("id");

		ModelAndView mav = new ModelAndView("franchisee/configureSpecialDayCkList");

		RestTemplate rest = new RestTemplate();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("spdayId", spdayId);

		ErrorMessage errorResponse = rest.postForObject(Constants.url + "deleteConfSpDayCk", map, ErrorMessage.class);
		System.out.println(errorResponse.toString());

		if (errorResponse.getError()) {
			return "redirect:/configureSpecialDayCkList";

		} else {
			return "redirect:/configureSpecialDayCkList";

		}
	}

	// ----------------------------------------END---------------------------------------------------------
	// ----------------------EDIT CONFIGURED FRANCHISEE FORM
	// SHOW-------------------------------------

	@RequestMapping(value = "/updateConfSpDayCk/{spdayId}", method = RequestMethod.GET)
	public ModelAndView updateConfSpDayCk(@PathVariable int spdayId) {

		ModelAndView model = new ModelAndView("franchisee/editSpecialDayCkConf");

		RestTemplate restTemplate = new RestTemplate();

		// ------Service Call to get All FranchiseeANDMenu------------------------
		FranchiseeAndMenuList franchiseeAndMenuList = restTemplate.getForObject(Constants.url + "getFranchiseeAndMenu",
				FranchiseeAndMenuList.class);

		System.out.println("Franchisee Response " + franchiseeAndMenuList.getAllFranchisee());

		List<Menu> menuList = franchiseeAndMenuList.getAllMenu();
		Menu frMenu = new Menu();
		for (Menu menu : menuList) {
			if (menu.getMainCatId() == 6) {
				frMenu = menu;
				break;
			}
		}
		int catId = 6;
		int menuId = frMenu.getMenuId();
		// ------------------------------------------------------------------------
		// ------------Service Call to get Selected Record for Edit by Id----------
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("spdayId", spdayId);

		GetConfiguredSpDayCk getConfiguredSpDayCk = restTemplate.postForObject(Constants.url + "getConfSpDayCake", map,
				GetConfiguredSpDayCk.class);

		// ------------------------------------------------------------------------

		System.out.println("-------------SELECTED getConfiguredSpDayCk-------------");

		String frPrevItems = getConfiguredSpDayCk.getItemId();

		System.out.println("-------------frPrevItems--------------------" + frPrevItems.toString());

		List<String> frPrevItemsList = Arrays.asList(frPrevItems.split("\\s*,\\s*"));

		System.out.println("-------------frPrevItemsList--------------------" + getConfiguredSpDayCk.getCatId());

		// ------------Service Call to get Configured SpDay Cake by
		// CatId(Grp1)----------
		MultiValueMap<String, Object> mav = new LinkedMultiValueMap<String, Object>();
		mav.add("itemGrp1", getConfiguredSpDayCk.getCatId());

		Item[] item = restTemplate.postForObject(Constants.url + "getItemsByCatId", mav, Item[].class);
		ArrayList<Item> itemList = new ArrayList<Item>(Arrays.asList(item));
		System.out.println("Filter Item List " + itemList.toString());
		// ------------------------------------------------------------------------------
		List<Item> filteredItemList = new ArrayList<Item>();
		List<Item> tempItemList = new ArrayList<Item>(Arrays.asList(item));
		System.out.println("temp Item List " + itemList.toString());

		// ------------------------------------------------------------------------------

		for (int j = 0; j < frPrevItemsList.size(); j++) {
			System.out.println("j " + j);

			for (int k = 0; k < itemList.size(); k++) {
				System.out.println("k " + k);

				if (Integer.parseInt(frPrevItemsList.get(j)) == (itemList.get(k).getId())) {
					System.out.println("jk " + j + "k" + k);
					filteredItemList.add(itemList.get(k));
					tempItemList.remove(itemList.get(k));
				}

			}

		}

		// ------------------------------------------------------------------------------

		model.addObject("selectedItemList", filteredItemList);
		System.out.println("filteredItemList " + filteredItemList.toString());

		model.addObject("remItemList", tempItemList);
		System.out.println("tempItemList" + tempItemList.toString());

		// ------------------------------------------------------------------------------
		// -------------------Conversion of FromTime And ToTime in hh:mm
		// a----------------

		String fromTime = getConfiguredSpDayCk.getFromTime();
		String toTime = getConfiguredSpDayCk.getToTime();

		try {
			final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			final Date fromDate = sdf.parse(fromTime);
			final Date toDate = sdf.parse(toTime);

			System.out.println("From Time " + fromDate);
			System.out.println("To Time " + toDate);

			System.out.println(new SimpleDateFormat("hh:mm a").format(fromDate));

			System.out.println(new SimpleDateFormat("hh:mm a").format(toDate));

			model.addObject("fromTime", new SimpleDateFormat("hh:mm a").format(fromDate));
			model.addObject("toTime", new SimpleDateFormat("hh:mm a").format(toDate));

		} catch (final ParseException e) {
			e.printStackTrace();
		}

		// ------------------------------------------------------------------------------
		List<FranchiseeList> franchiseeList = franchiseeAndMenuList.getAllFranchisee();
		System.out.println("franchiseeList " + franchiseeList.toString());

		String frPrevFr = getConfiguredSpDayCk.getFrId();
		System.out.println("frPrevFr " + frPrevFr.toString());

		List<String> frPrevFrList = Arrays.asList(frPrevFr.split("\\s*,\\s*"));
		System.out.println("frPrevFrList " + frPrevFrList.toString());

		// ------------------------------------------------------------------------------
		List<FranchiseeList> filteredFrList = new ArrayList<FranchiseeList>();
		List<FranchiseeList> tempFrList = franchiseeAndMenuList.getAllFranchisee();
		System.out.println("tempFrList " + tempFrList.toString());

		// ------------------------------------------------------------------------------
		System.out.println("frPrevFrList.size() " + frPrevFrList.size());
		for (int j = 0; j < frPrevFrList.size(); j++) {

			for (int k = 0; k < franchiseeList.size(); k++) {

				if (Integer.parseInt(frPrevFrList.get(j)) == (franchiseeList.get(k).getFrId())) {

					filteredFrList.add(franchiseeList.get(k));
					tempFrList.remove(k);
				}

			}

		}
		// ------------------------------------------------------------------------------

		model.addObject("selectedFrList", filteredFrList);

		model.addObject("remFrList", tempFrList);

		// ------------------------------------------------------------------------------------
		model.addObject("catId", catId);
		model.addObject("menuId", menuId);

		model.addObject("franchiseeAndMenuList", franchiseeAndMenuList);

		model.addObject("getConfiguredSpDayCk", getConfiguredSpDayCk);

		model.addObject("spdayId", spdayId);

		return model;

	}
	// ----------------------------------------END---------------------------------------------------------
	// ---------------------------------------LIST ALL
	// FRANCHISEE--------------------------------------------

	@RequestMapping(value = "/showAddFranchiseSup")
	public ModelAndView addFranchiseSup(HttpServletRequest request, HttpServletResponse response) {
		Constants.mainAct = 1;
		Constants.subAct = 113;
		ModelAndView mav = new ModelAndView("franchisee/addFranchiseSup");

		RestTemplate restTemplate = new RestTemplate();
		List<FrListForSupp> franchiseeList = restTemplate.getForObject(Constants.url + "getFrListForSupp", List.class);

		FranchiseSupList frSupList = restTemplate.getForObject(Constants.url + "/getFranchiseSupList",
				FranchiseSupList.class);

		System.out.println("Franchisee List:" + franchiseeList.toString());

		mav.addObject("franchiseeList", franchiseeList);
		mav.addObject("frSupList", frSupList.getFrList());
		mav.addObject("isEdit", 0);
		mav.addObject("state", "Maharashtra");

		return mav;
	}
	// ----------------------------------------END---------------------------------------------------------

	// ------------------------------ADD Franchisee
	// Process------------------------------------
	@RequestMapping(value = "/addFrSupProcess", method = RequestMethod.POST)
	public String addFrSupProcess(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("franchisee/addFranchiseSup");
		try {

			int id = 0;

			try {
				id = Integer.parseInt(request.getParameter("id"));

			} catch (Exception e) {
				id = 0;
				System.out.println("In Catch of Add ItemSup Process Exc:" + e.getMessage());

			}
			int frId = Integer.parseInt(request.getParameter("fr_id"));

			String frPanNo = request.getParameter("pan_no");

			String frState = request.getParameter("fr_state");

			String frCountry = request.getParameter("fr_country");

			String pass1 = request.getParameter("pass1");

			String pass2 = request.getParameter("pass2");

			String pass3 = request.getParameter("pass3");

			String pestControlDate = request.getParameter("pest_control_date");

			int frequency = Integer.parseInt(request.getParameter("frequency"));

			int noInRoute = Integer.parseInt(request.getParameter("no_in_route"));

			// String remainderDate=request.getParameter("remainder_date");

			Date pestCtrlDate = new SimpleDateFormat("dd-MM-yyyy").parse(pestControlDate);
			Date newDate = addDays(pestCtrlDate, frequency);
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			String remainderDate = dateFormat.format(newDate);

			FranchiseSup frSup = new FranchiseSup();
			frSup.setId(id);
			frSup.setFrId(frId);
			frSup.setFrPanNo(frPanNo);
			frSup.setFrCountry(frCountry);
			frSup.setFrState(frState);
			frSup.setDelStatus(0);
			frSup.setPass1(pass1);
			frSup.setPass2(pass2);
			frSup.setPass3(pass3);
			frSup.setPass4("pass4");
			frSup.setPass5("pass5");
			frSup.setPestControlDate(pestControlDate);
			frSup.setFrequency(frequency);
			frSup.setRemainderDate(remainderDate);
			frSup.setIsTallySync(0);
			frSup.setNoInRoute(noInRoute);

			RestTemplate restTemplate = new RestTemplate();

			Info info = restTemplate.postForObject(Constants.url + "/saveFranchiseSup", frSup, Info.class);
			System.out.println("Response: " + info.toString());

			if (info.getError() == true) {

				System.out.println("Error:True" + info.toString());
				return "redirect:/showAddFranchiseSup";

			} else {
				return "redirect:/showAddFranchiseSup";
			}

		} catch (Exception e) {

			System.out.println("Exception In Add Fr Sup Process:" + e.getMessage());

		}

		return "redirect:/showAddFranchiseSup";
	}

	public static Date addDays(Date date, int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);

		return cal.getTime();
	}

	// ------------------------------ADD Franchisee
	// Process------------------------------------
	@RequestMapping(value = "/updateFranchiseSup/{id}", method = RequestMethod.GET)
	public ModelAndView updateFranchiseSup(@PathVariable("id") int id, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("franchisee/addFranchiseSup");
		try {
			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("id", id);

			FranchiseSup frSup = restTemplate.postForObject(Constants.url + "/getFranchiseSup", map,
					FranchiseSup.class);
			AllFranchiseeList allFranchiseeList = restTemplate.getForObject(Constants.url + "getAllFranchisee",
					AllFranchiseeList.class);
			List<FranchiseeList> franchiseeList = new ArrayList<FranchiseeList>();
			franchiseeList = allFranchiseeList.getFranchiseeList();
			System.out.println("Franchisee List:" + franchiseeList.toString());

			FranchiseSupList frSupList = restTemplate.getForObject(Constants.url + "/getFranchiseSupList",
					FranchiseSupList.class);

			model.addObject("franchiseeList", franchiseeList);
			model.addObject("frSup", frSup);
			model.addObject("frSupList", frSupList.getFrList());
			model.addObject("isEdit", 1);

			model.addObject("state", frSup.getFrState());

		} catch (Exception e) {
			System.out.println("Exc In Update Fr Sup");
		}
		return model;
	}

	// ------------------------------------showAddFrTarget--------------------------------------------
	@RequestMapping(value = "/showAddFrTarget", method = RequestMethod.GET)
	public ModelAndView showAddFrTarget(HttpServletRequest request, HttpServletResponse response) {
		Constants.mainAct = 2;
		Constants.subAct = 114;
		ModelAndView model = new ModelAndView("franchisee/addFrTarget");

		List<String> months = Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August",
				"September", "October", "November", "December");
		String thisYear = new SimpleDateFormat("yyyy").format(new Date());
		RestTemplate restTemplate = new RestTemplate();

		AllFranchiseeList allFranchiseeList = restTemplate.getForObject(Constants.url + "getAllFranchisee",
				AllFranchiseeList.class);
		List<FranchiseeList> franchiseeList = new ArrayList<FranchiseeList>();
		franchiseeList = allFranchiseeList.getFranchiseeList();
		System.out.println("Franchisee List:" + franchiseeList.toString());

		model.addObject("franchiseeList", franchiseeList);
		model.addObject("thisYear", thisYear);
		model.addObject("months", months);
		model.addObject("isSave", 0);

		return model;
	}
	// ---------------------------------------------------------------------------------------------

	// ------------------------------------showAddFrTarget--------------------------------------------
	@RequestMapping(value = "/addFrTargetProcess", method = RequestMethod.POST)
	public ModelAndView addFrTargetProcess(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("franchisee/addFrTarget");
		RestTemplate restTemplate = new RestTemplate();

		try {
			String[] selChk = request.getParameterValues("chk");
			System.out.println("selChk:" + selChk);

			int frId = Integer.parseInt(request.getParameter("fr_id"));
			System.out.println("frId:" + frId);

			int frTargetYear = Integer.parseInt(request.getParameter("year"));
			System.out.println("frTargetYear:" + frTargetYear);

			List<String> list = Arrays.asList(selChk);

			List<FrTarget> frTargetList = new ArrayList<FrTarget>();

			for (int j = 0; j < list.size(); j++) {

				int chk = Integer.parseInt(list.get(j));

				// String frMonth = request.getParameter("month"+chk);
				// System.out.println("month:" + frMonth);
				int frTargetId = Integer.parseInt(request.getParameter("id" + chk));
				System.out.println("frTargetId:" + frTargetId);

				float frTargetAmt = Float.parseFloat(request.getParameter("target" + chk));
				System.out.println("frTarget:" + frTargetAmt);

				float frAchievedSale = Float.parseFloat(request.getParameter("ach_target" + chk));
				System.out.println("frAchTarget:" + frAchievedSale);

				String frAward = request.getParameter("award" + chk);
				System.out.println("frAward" + frAward);

				String remark = request.getParameter("remark" + chk);
				System.out.println("remark" + remark);
				int status = 0;
				if (frAchievedSale > 0) {
					status = 1;// Integer.parseInt(request.getParameter("status"+chk));
					System.out.println("status:" + status);
				} else {
					status = 0;
				}
				FrTarget franchiseTarget = new FrTarget();

				franchiseTarget.setFrTargetId(frTargetId);
				franchiseTarget.setFrId(frId);
				franchiseTarget.setFrTargetAmt(frTargetAmt);
				franchiseTarget.setFrAchievedSale(frAchievedSale);
				franchiseTarget.setFrAward(frAward);
				franchiseTarget.setFrTargetMonth(chk);
				franchiseTarget.setFrTargetYear(frTargetYear);
				franchiseTarget.setRemark(remark);
				franchiseTarget.setStatus(status);

				franchiseTarget.setDelStatus(0);
				frTargetList.add(franchiseTarget);
			}
			Info info = restTemplate.postForObject(Constants.url + "/saveFrTarget", frTargetList, Info.class);
			System.out.println("saveFrTarget Response:" + info.toString());

			List<String> months = Arrays.asList("January", "February", "March", "April", "May", "June", "July",
					"August", "September", "October", "November", "December");

			AllFranchiseeList allFranchiseeList = restTemplate.getForObject(Constants.url + "getAllFranchisee",
					AllFranchiseeList.class);
			List<FranchiseeList> franchiseeList = new ArrayList<FranchiseeList>();
			franchiseeList = allFranchiseeList.getFranchiseeList();

			System.out.println("Franchisee List:" + franchiseeList.toString());

			model.addObject("franchiseeList", franchiseeList);
			model.addObject("months", months);
			model.addObject("frId", frId);
			model.addObject("thisYear", frTargetYear);
			model.addObject("isSave", 1);
		} catch (Exception e) {
			System.out.println("Exception In /saveFrTarget" + e.getMessage());
			e.printStackTrace();
		}
		return model;
	}
	// --------------------------------------------------------------------------------------------------

	@RequestMapping(value = "/searchFrMonthTarget", method = RequestMethod.GET)
	public @ResponseBody List<FrTarget> searchFrMonthTarget(HttpServletRequest request, HttpServletResponse response) {
		FrTargetList frTargetList = new FrTargetList();
		try {
			int frId = Integer.parseInt(request.getParameter("frId"));
			System.out.println("frId:" + frId);

			int year = Integer.parseInt(request.getParameter("year"));
			System.out.println("year:" + year);

			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("frId", frId);
			map.add("year", year);

			frTargetList = restTemplate.postForObject(Constants.url + "/getFrTargetList", map, FrTargetList.class);
			System.out.println("Fr Target List:" + frTargetList.toString());

			if (frTargetList.getInfo().getError() == false) {
				for (int i = 0; i < frTargetList.getFrTargetList().size(); i++) {
					if (frTargetList.getFrTargetList().get(i).getStatus() == 0) {
						System.out.println(frTargetList.getFrTargetList().get(i).getFrId());
						map = new LinkedMultiValueMap<String, Object>();
						map.add("frId", frTargetList.getFrTargetList().get(i).getFrId());
						map.add("month", frTargetList.getFrTargetList().get(i).getFrTargetMonth());
						map.add("year", frTargetList.getFrTargetList().get(i).getFrTargetYear());

						FrTotalSale frTotalSale = restTemplate.postForObject(Constants.url + "/getFrTotalSale", map,
								FrTotalSale.class);
						System.out.println("FrTotalSale:" + frTotalSale.toString());

						if (frTotalSale != null || frTotalSale.getTotalSale() != 0) {
							float achievedSale = frTotalSale.getTotalSale();
							frTargetList.getFrTargetList().get(i).setFrAchievedSale(achievedSale);
						}
						if (frTotalSale.getTotalSale() == 0) {
							frTotalSale = new FrTotalSale();
							frTotalSale.setFrId(frTargetList.getFrTargetList().get(i).getFrId());
							frTotalSale.setMonth(frTargetList.getFrTargetList().get(i).getFrTargetMonth());
							frTotalSale.setTotalSale(0.0f);
						}

					}
				}
			} else {
				frTargetList = new FrTargetList();
				FrTarget frTarget = new FrTarget();
				frTarget.setFrId(0);
				List<FrTarget> frTargetListTest = new ArrayList<FrTarget>();
				frTargetListTest.add(frTarget);
				frTargetList.setFrTargetList(frTargetListTest);
			}

		} catch (Exception e) {
			System.out.println("Exception In Search FrTarget Data By FrId" + e.getMessage());
		}
		return frTargetList.getFrTargetList();
	}

	@RequestMapping(value = "/findFranchiseeData", method = RequestMethod.GET)
	public @ResponseBody FranchiseeList findFranchiseeData(@RequestParam(value = "fr_id", required = true) int frId) {

		FranchiseeList franchiseeList = null;
		try {
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("frId", frId);
			RestTemplate restTemplate = new RestTemplate();

			franchiseeList = restTemplate.getForObject(Constants.url + "getFranchisee?frId={frId}",
					FranchiseeList.class, frId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return franchiseeList;
	}
	// ----------------------------------------END--------------------------------------------
}
