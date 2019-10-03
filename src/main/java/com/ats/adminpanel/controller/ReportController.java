package com.ats.adminpanel.controller;

import java.io.BufferedInputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.commons.DateConvertor;
import com.ats.adminpanel.model.ExportToExcel;
import com.ats.adminpanel.model.GetRegSpCakeOrderForProdApp;
import com.ats.adminpanel.model.GetSpCakeOrderForProdApp;
import com.ats.adminpanel.model.SpCakeWtCount;
import com.ats.adminpanel.model.franchisee.AllMenuResponse;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Controller
@Scope("session")
public class ReportController {
	String todaysDate;

	RestTemplate restTemplate = new RestTemplate();

	@RequestMapping(value = "/showTSPCakeOrderForApp", method = RequestMethod.GET)
	public ModelAndView showTSPCakeOrderForApp(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("reports/tspCakeForAppReport");
		AllMenuResponse allMenus = new AllMenuResponse();
		try {
			ZoneId z = ZoneId.of("Asia/Calcutta");
			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			todaysDate = date.format(formatters);
			model.addObject("todaysDate", todaysDate);
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			map.add("catId", 5);
			allMenus = restTemplate.postForObject(Constants.url + "getMenuByCat", map, AllMenuResponse.class);
			model.addObject("allMenus", allMenus.getMenuConfigurationPage());
			System.out.println(allMenus.getMenuConfigurationPage().toString());

		} catch (Exception e) {
			System.out.println("Exc in show Report report tspcake wise  " + e.getMessage());
			e.printStackTrace();
		}
		return model;

	}
	
	
	//-------Anmol 13-7-2019---------------------
	@RequestMapping(value = "/showTSPCakeAlbumOrderForApp", method = RequestMethod.GET)
	public ModelAndView showTSPCakeAlbumOrderForApp(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("reports/tspCakeAlbumForAppReport");
		AllMenuResponse allMenus = new AllMenuResponse();
		try {
			ZoneId z = ZoneId.of("Asia/Calcutta");
			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			todaysDate = date.format(formatters);
			model.addObject("todaysDate", todaysDate);
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			map.add("catId", 5);
			allMenus = restTemplate.postForObject(Constants.url + "getMenuByCat", map, AllMenuResponse.class);
			model.addObject("allMenus", allMenus.getMenuConfigurationPage());
			System.out.println(allMenus.getMenuConfigurationPage().toString());

		} catch (Exception e) {
			System.out.println("Exc in show Report report tspcake wise  " + e.getMessage());
			e.printStackTrace();
		}
		return model;

	}

	
	

	List<GetSpCakeOrderForProdApp> getSpCakeOrderForProdAppLsit = new ArrayList<>();

	@RequestMapping(value = "/getSpCakeListAjax", method = RequestMethod.GET)
	public @ResponseBody List<GetSpCakeOrderForProdApp> getSpCakeListAjax(HttpServletRequest request,
			HttpServletResponse response) {

		try {

			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
			String menuIdList = request.getParameter("menuIdList");
			int seqence = Integer.parseInt(request.getParameter("seqenceList"));

			System.out.println(menuIdList);

			menuIdList = menuIdList.substring(1, menuIdList.length() - 1);
			menuIdList = menuIdList.replaceAll("\"", "");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			map.add("toDate", DateConvertor.convertToYMD(toDate));

			map.add("menuIdList", menuIdList);

			if (seqence == 1) {

				map.add("isSlotUsed", 0);

				map.add("isOrderBy", 0);
			} else if (seqence == 2) {
				map.add("isSlotUsed", 0);

				map.add("isOrderBy", 1);
			} else {
				map.add("isSlotUsed", 1);

				map.add("isOrderBy", 1);
			}
			ParameterizedTypeReference<List<GetSpCakeOrderForProdApp>> typeRef = new ParameterizedTypeReference<List<GetSpCakeOrderForProdApp>>() {
			};
			ResponseEntity<List<GetSpCakeOrderForProdApp>> responseEntity = restTemplate
					.exchange(Constants.url + "getSpCakeAlbumOrdersForApp", HttpMethod.POST, new HttpEntity<>(map), typeRef);

			getSpCakeOrderForProdAppLsit = responseEntity.getBody();

			System.out.println("sales List Bill Wise " + spCakeList.toString());

		} catch (Exception e) {
			System.out.println("get sale Report Bill Wise " + e.getMessage());
			e.printStackTrace();

		}

		// exportToExcel
		List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();
		double totalCount = 0;
		ExportToExcel expoExcel = new ExportToExcel();
		List<String> rowData = new ArrayList<String>();
		rowData.add("Sr No.");
		rowData.add("Sequnce In Route");
		rowData.add("Route Name");
		rowData.add("Franchise Name");
		rowData.add("Cake Code");
		rowData.add("Weight");
		rowData.add("Flavour");
		rowData.add("Deliver At");

		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);
		int index = 0;
		for (int i = 0; i < getSpCakeOrderForProdAppLsit.size(); i++) {
			expoExcel = new ExportToExcel();
			index = index + 1;
			rowData = new ArrayList<String>();

			rowData.add("" + getSpCakeOrderForProdAppLsit.get(i).getSrNo());
			rowData.add("" + getSpCakeOrderForProdAppLsit.get(i).getNoInRoute());
			rowData.add("" + getSpCakeOrderForProdAppLsit.get(i).getRouteName());
			rowData.add("" + getSpCakeOrderForProdAppLsit.get(i).getFrName());
			rowData.add("" + getSpCakeOrderForProdAppLsit.get(i).getFrCode());
			rowData.add("" + getSpCakeOrderForProdAppLsit.get(i).getInputKgFr());
			rowData.add("" + getSpCakeOrderForProdAppLsit.get(i).getSpfName());
			rowData.add("" + getSpCakeOrderForProdAppLsit.get(i).getSpDeliveryPlace());

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);

		}

		expoExcel = new ExportToExcel();

		rowData = new ArrayList<String>();
		rowData.add("");
		rowData.add("Total");
		rowData.add("" + totalCount);

		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);

		HttpSession session = request.getSession();
		session.setAttribute("exportExcelList", exportToExcelList);
		session.setAttribute("excelName", "SP Cake Report");

		return getSpCakeOrderForProdAppLsit;
	}
	
	
	//------------ANMOL 13-7-2019--------------------
	@RequestMapping(value = "/getSpCakeAlbumListAjax", method = RequestMethod.GET)
	public @ResponseBody List<GetSpCakeOrderForProdApp> getSpCakeAlbumListAjax(HttpServletRequest request,
			HttpServletResponse response) {

		try {

			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
			String menuIdList = request.getParameter("menuIdList");
			int seqence = Integer.parseInt(request.getParameter("seqenceList"));

			System.out.println(menuIdList);

			menuIdList = menuIdList.substring(1, menuIdList.length() - 1);
			menuIdList = menuIdList.replaceAll("\"", "");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			map.add("toDate", DateConvertor.convertToYMD(toDate));

			map.add("menuIdList", menuIdList);

			if (seqence == 1) {

				map.add("isSlotUsed", 0);

				map.add("isOrderBy", 0);
			} else if (seqence == 2) {
				map.add("isSlotUsed", 0);

				map.add("isOrderBy", 1);
			} else {
				map.add("isSlotUsed", 1);

				map.add("isOrderBy", 1);
			}
			ParameterizedTypeReference<List<GetSpCakeOrderForProdApp>> typeRef = new ParameterizedTypeReference<List<GetSpCakeOrderForProdApp>>() {
			};
			ResponseEntity<List<GetSpCakeOrderForProdApp>> responseEntity = restTemplate
					.exchange(Constants.url + "getSpCakeAlbumOrdersForApp", HttpMethod.POST, new HttpEntity<>(map), typeRef);

			getSpCakeOrderForProdAppLsit = responseEntity.getBody();

			System.out.println("sales List Bill Wise " + spCakeList.toString());

		} catch (Exception e) {
			System.out.println("get sale Report Bill Wise " + e.getMessage());
			e.printStackTrace();

		}

		// exportToExcel
		List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();
		double totalCount = 0;
		ExportToExcel expoExcel = new ExportToExcel();
		List<String> rowData = new ArrayList<String>();
		rowData.add("Sr No.");
		rowData.add("Sequnce In Route");
		rowData.add("Route Name");
		rowData.add("Franchise Name");
		rowData.add("Cake Code");
		rowData.add("Weight");
		rowData.add("Flavour");
		rowData.add("Deliver At");

		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);
		int index = 0;
		for (int i = 0; i < getSpCakeOrderForProdAppLsit.size(); i++) {
			expoExcel = new ExportToExcel();
			index = index + 1;
			rowData = new ArrayList<String>();

			rowData.add("" + getSpCakeOrderForProdAppLsit.get(i).getSrNo());
			rowData.add("" + getSpCakeOrderForProdAppLsit.get(i).getNoInRoute());
			rowData.add("" + getSpCakeOrderForProdAppLsit.get(i).getRouteName());
			rowData.add("" + getSpCakeOrderForProdAppLsit.get(i).getFrName());
			rowData.add("" + getSpCakeOrderForProdAppLsit.get(i).getFrCode());
			rowData.add("" + getSpCakeOrderForProdAppLsit.get(i).getInputKgFr());
			rowData.add("" + getSpCakeOrderForProdAppLsit.get(i).getSpfName());
			rowData.add("" + getSpCakeOrderForProdAppLsit.get(i).getSpDeliveryPlace());

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);

		}

		expoExcel = new ExportToExcel();

		rowData = new ArrayList<String>();
		rowData.add("");
		rowData.add("Total");
		rowData.add("" + totalCount);

		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);

		HttpSession session = request.getSession();
		session.setAttribute("exportExcelList", exportToExcelList);
		session.setAttribute("excelName", "SP Cake Report");

		return getSpCakeOrderForProdAppLsit;
	}

	

	@RequestMapping(value = "/excelForTspCake/{checkboxes}", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView excelForTspCake(@PathVariable("checkboxes") String checkboxes, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView model = new ModelAndView("reports/spCakePdf");

		List<GetSpCakeOrderForProdApp> getSpCakeOrderForProdAppLsitLocal = new ArrayList<>();
		try {

			System.out.println("checkboxescheckboxescheckboxes" + checkboxes);

			checkboxes = checkboxes.substring(0, checkboxes.length() - 1);
			System.out.println("string " + checkboxes);

			List<Integer> checkBoxList = Stream.of(checkboxes.split(",")).map(Integer::parseInt)
					.collect(Collectors.toList());

			for (int i = 0; i < getSpCakeOrderForProdAppLsit.size(); i++) {

				for (int j = 0; j < checkBoxList.size(); j++) {
					if (checkBoxList.get(j) == getSpCakeOrderForProdAppLsit.get(i).gettSpCakeSupNo()) {
						getSpCakeOrderForProdAppLsitLocal.add(getSpCakeOrderForProdAppLsit.get(i));

					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception to genrate excel ");
		}

		System.out.println("SpOrder" + getSpCakeOrderForProdAppLsitLocal.toString());
		model.addObject("spCakeOrder", getSpCakeOrderForProdAppLsitLocal);
		model.addObject("imgUrl2", Constants.SP_CAKE_FOLDER);
		model.addObject("imgUrl", Constants.CUST_CHOICE_PHOTO_CAKE_FOLDER);

		return model;

	}

	@RequestMapping(value = "/showTSPRegularCakeOrderForApp", method = RequestMethod.GET)
	public ModelAndView showTSPRegularCakeOrderForApp(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("reports/tspRegCakeForAppReport");
		AllMenuResponse allMenus = new AllMenuResponse();
		try {
			ZoneId z = ZoneId.of("Asia/Calcutta");
			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			todaysDate = date.format(formatters);
			model.addObject("todaysDate", todaysDate);
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			map.add("catId", 2);
			allMenus = restTemplate.postForObject(Constants.url + "getMenuByCat", map, AllMenuResponse.class);
			model.addObject("allMenus", allMenus.getMenuConfigurationPage());
			System.out.println(allMenus.getMenuConfigurationPage().toString());

		} catch (Exception e) {
			System.out.println("Exc in show Report report tspcake wise  " + e.getMessage());
			e.printStackTrace();
		}
		return model;

	}

	List<GetRegSpCakeOrderForProdApp> getRegSpCakeOrderForProdAppList = new ArrayList<>();

	@RequestMapping(value = "/getSpRegCakeListAjax", method = RequestMethod.GET)
	public @ResponseBody List<GetRegSpCakeOrderForProdApp> getSpRegCakeListAjax(HttpServletRequest request,
			HttpServletResponse response) {

		try {

			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
			String menuIdList = request.getParameter("menuIdList");
			int seqence = Integer.parseInt(request.getParameter("seqenceList"));

			System.out.println(menuIdList);

			menuIdList = menuIdList.substring(1, menuIdList.length() - 1);
			menuIdList = menuIdList.replaceAll("\"", "");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			map.add("toDate", DateConvertor.convertToYMD(toDate));

			map.add("menuIdList", menuIdList);

			if (seqence == 1) {
				map.add("isOrderBy", 1);
			} else if (seqence == 2) {

				map.add("isOrderBy", 0);
			}
			ParameterizedTypeReference<List<GetRegSpCakeOrderForProdApp>> typeRef = new ParameterizedTypeReference<List<GetRegSpCakeOrderForProdApp>>() {
			};
			ResponseEntity<List<GetRegSpCakeOrderForProdApp>> responseEntity = restTemplate.exchange(
					Constants.url + "geRegSpCakeOrdersForApp", HttpMethod.POST, new HttpEntity<>(map), typeRef);

			getRegSpCakeOrderForProdAppList = responseEntity.getBody();

			System.out.println("sales List Bill Wise " + spCakeList.toString());

		} catch (Exception e) {
			System.out.println("get sale Report Bill Wise " + e.getMessage());
			e.printStackTrace();

		}

		// exportToExcel
		List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();
		double totalCount = 0;
		ExportToExcel expoExcel = new ExportToExcel();
		List<String> rowData = new ArrayList<String>();
		rowData.add("Sr No.");
		rowData.add("Sequnce In Route");
		rowData.add("Route Name");
		rowData.add("Franchise Name");
		rowData.add("Cake Code");
		rowData.add("Weight");
		rowData.add("Flavour");
		rowData.add("Deliver At");

		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);
		int index = 0;
		for (int i = 0; i < getRegSpCakeOrderForProdAppList.size(); i++) {
			expoExcel = new ExportToExcel();
			index = index + 1;
			rowData = new ArrayList<String>();

			rowData.add("" + getRegSpCakeOrderForProdAppList.get(i).getSrNo());
			rowData.add("" + getRegSpCakeOrderForProdAppList.get(i).getNoInRoute());
			rowData.add("" + getRegSpCakeOrderForProdAppList.get(i).getRouteName());
			rowData.add("" + getRegSpCakeOrderForProdAppList.get(i).getFrName());
			rowData.add("" + getRegSpCakeOrderForProdAppList.get(i).getFrCode());
			rowData.add("" + getRegSpCakeOrderForProdAppList.get(i).getInputKgProd());
			rowData.add("" + getRegSpCakeOrderForProdAppList.get(i).getNoInRoute());

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);

		}

		HttpSession session = request.getSession();
		session.setAttribute("exportExcelList", exportToExcelList);
		session.setAttribute("excelName", "SP Cake Report");

		return getRegSpCakeOrderForProdAppList;
	}

	@RequestMapping(value = "/excelForTspRegCake/{checkboxes}", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView excelForTspRegCake(@PathVariable("checkboxes") String checkboxes, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView model = new ModelAndView("reports/spRegCakePdf");

		List<GetRegSpCakeOrderForProdApp> getRegSpCakeOrderForProdAppListLocal = new ArrayList<>();
		try {

			System.out.println("checkboxescheckboxescheckboxes" + checkboxes);

			checkboxes = checkboxes.substring(0, checkboxes.length() - 1);
			System.out.println("string " + checkboxes);

			List<Integer> checkBoxList = Stream.of(checkboxes.split(",")).map(Integer::parseInt)
					.collect(Collectors.toList());

			for (int i = 0; i < getRegSpCakeOrderForProdAppList.size(); i++) {

				for (int j = 0; j < checkBoxList.size(); j++) {
					if (checkBoxList.get(j) == getRegSpCakeOrderForProdAppList.get(i).getSupId()) {
						getRegSpCakeOrderForProdAppListLocal.add(getRegSpCakeOrderForProdAppList.get(i));

					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception to genrate excel ");
		}

		System.out.println("SpOrder" + getRegSpCakeOrderForProdAppListLocal.toString());
		model.addObject("spCakeOrder", getRegSpCakeOrderForProdAppListLocal);
		model.addObject("imgUrl", Constants.SP_CAKE_FOLDER);
		model.addObject("imgUrl2", Constants.CUST_CHOICE_PHOTO_CAKE_FOLDER);

		return model;

	}

	/*
	 * @RequestMapping(value = "/excelForTspRegCake", method = RequestMethod.GET)
	 * 
	 * @ResponseBody public GetSpCakeOrderForProdApp
	 * excelForTspRegCake(HttpServletRequest request, HttpServletResponse response)
	 * {
	 * 
	 * GetSpCakeOrderForProdApp salesVoucherList = new GetSpCakeOrderForProdApp();
	 * try {
	 * 
	 * String checkboxes = request.getParameter("checkboxes");
	 * System.out.println("checkboxescheckboxescheckboxes" + checkboxes);
	 * 
	 * checkboxes = checkboxes.substring(0, checkboxes.length() - 1);
	 * System.out.println("string " + checkboxes);
	 * 
	 * List<Integer> checkBoxList =
	 * Stream.of(checkboxes.split(",")).map(Integer::parseInt)
	 * .collect(Collectors.toList());
	 * 
	 * try { List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();
	 * 
	 * ExportToExcel expoExcel = new ExportToExcel(); List<String> rowData = new
	 * ArrayList<String>(); rowData.add("Sr No."); rowData.add("Sequnce In Route");
	 * rowData.add("Route Name"); rowData.add("Franchise Name");
	 * rowData.add("Cake Code"); rowData.add("Weight");
	 * 
	 * rowData.add("Deliver At");
	 * 
	 * expoExcel.setRowData(rowData); exportToExcelList.add(expoExcel); int index =
	 * 0; for (int i = 0; i < getRegSpCakeOrderForProdAppList.size(); i++) {
	 * 
	 * for (int j = 0; j < checkBoxList.size(); j++) { if (checkBoxList.get(j) ==
	 * getRegSpCakeOrderForProdAppList.get(i).getSupId()) { expoExcel = new
	 * ExportToExcel(); index = index + 1; rowData = new ArrayList<String>();
	 * 
	 * rowData.add("" + getRegSpCakeOrderForProdAppList.get(i).getSrNo());
	 * rowData.add("" + getRegSpCakeOrderForProdAppList.get(i).getNoInRoute());
	 * rowData.add("" + getRegSpCakeOrderForProdAppList.get(i).getRouteName());
	 * rowData.add("" + getRegSpCakeOrderForProdAppList.get(i).getFrName());
	 * rowData.add("" + getRegSpCakeOrderForProdAppList.get(i).getFrCode());
	 * rowData.add("" + getRegSpCakeOrderForProdAppList.get(i).getInputKgProd());
	 * 
	 * rowData.add("" + getRegSpCakeOrderForProdAppList.get(i).getRspPlace());
	 * 
	 * expoExcel.setRowData(rowData); exportToExcelList.add(expoExcel); } }
	 * 
	 * }
	 * 
	 * HttpSession session = request.getSession();
	 * session.setAttribute("exportExcelList", exportToExcelList);
	 * session.setAttribute("excelName", "SPRegularCakeReportForApp"); } catch
	 * (Exception e) { e.printStackTrace();
	 * System.out.println("Exception to genrate excel "); } } catch (Exception e) {
	 * e.printStackTrace(); } return salesVoucherList;
	 * 
	 * }
	 */
	@RequestMapping(value = "/showTSPCakeCountBetweenDate", method = RequestMethod.GET)
	public ModelAndView showTSPCakeCountBetweenDate(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("reports/tspCakeBetDate");
		AllMenuResponse allMenus = new AllMenuResponse();
		try {
			ZoneId z = ZoneId.of("Asia/Calcutta");
			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			todaysDate = date.format(formatters);
			model.addObject("todaysDate", todaysDate);
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			map.add("catId", 5);
			allMenus = restTemplate.postForObject(Constants.url + "getMenuByCat", map, AllMenuResponse.class);
			model.addObject("allMenus", allMenus.getMenuConfigurationPage());
			System.out.println(allMenus.getMenuConfigurationPage().toString());

		} catch (Exception e) {
			System.out.println("Exc in show Report report tspcake wise  " + e.getMessage());
			e.printStackTrace();
		}
		return model;

	}

	List<SpCakeWtCount> spCakeList = new ArrayList<>();

	@RequestMapping(value = "/getSpCakeWtCountAjax", method = RequestMethod.GET)
	public @ResponseBody List<SpCakeWtCount> getSpCakeWtCountAjax(HttpServletRequest request,
			HttpServletResponse response) {

		try {

			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
			String menuIdList = request.getParameter("menuIdList");

			System.out.println(menuIdList);

			menuIdList = menuIdList.substring(1, menuIdList.length() - 1);
			menuIdList = menuIdList.replaceAll("\"", "");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			map.add("toDate", DateConvertor.convertToYMD(toDate));

			map.add("menuIdList", menuIdList);
			ParameterizedTypeReference<List<SpCakeWtCount>> typeRef = new ParameterizedTypeReference<List<SpCakeWtCount>>() {
			};
			ResponseEntity<List<SpCakeWtCount>> responseEntity = restTemplate
					.exchange(Constants.url + "getSpCakeCountBetDate", HttpMethod.POST, new HttpEntity<>(map), typeRef);

			spCakeList = responseEntity.getBody();

			System.out.println("sales List Bill Wise " + spCakeList.toString());

		} catch (Exception e) {
			System.out.println("get sale Report Bill Wise " + e.getMessage());
			e.printStackTrace();

		}

		// exportToExcel
		List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();
		double totalCount = 0;
		ExportToExcel expoExcel = new ExportToExcel();
		List<String> rowData = new ArrayList<String>();
		rowData.add("Sr No.");
		rowData.add("Sp Cake weight");
		rowData.add("Count");

		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);
		int index = 0;
		for (int i = 0; i < spCakeList.size(); i++) {
			expoExcel = new ExportToExcel();
			index = index + 1;
			rowData = new ArrayList<String>();
			rowData.add((index) + "");
			rowData.add("" + spCakeList.get(i).getSpSelectedWt());
			rowData.add("" + spCakeList.get(i).getCount());
			totalCount = totalCount + spCakeList.get(i).getCount();

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);

		}

		expoExcel = new ExportToExcel();

		rowData = new ArrayList<String>();
		rowData.add("");
		rowData.add("Total");
		rowData.add("" + totalCount);

		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);

		HttpSession session = request.getSession();
		session.setAttribute("exportExcelList", exportToExcelList);
		session.setAttribute("excelName", "SP Cake Report");

		return spCakeList;
	}

// SpCakeWtCount

	@RequestMapping(value = "/getSpCakeCountPdf/{fromDate}/{toDate}", method = RequestMethod.GET)
	public void getSpCakeCountPdf(@PathVariable String fromDate, @PathVariable String toDate,
			HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException {

		Document document = new Document(PageSize.A4);
		document.setPageSize(PageSize.A4.rotate());
		// ByteArrayOutputStream out = new ByteArrayOutputStream();

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();

		System.out.println("getHsnWisePdf PDF ==" + dateFormat.format(cal.getTime()));
		String timeStamp = dateFormat.format(cal.getTime());
		String FILE_PATH = Constants.REPORT_SAVE;
		File file = new File(FILE_PATH);

		PdfWriter writer = null;

		FileOutputStream out = new FileOutputStream(FILE_PATH);

		try {
			writer = PdfWriter.getInstance(document, out);
		} catch (DocumentException e) {

			e.printStackTrace();
		}

		PdfPTable table = new PdfPTable(3);
		table.setHeaderRows(1);
		try {
			System.out.println("Inside PDF Table try");
			table.setWidthPercentage(100);
			table.setWidths(new float[] { 0.7f, 2.1f, 0.9f });
			Font headFont = new Font(FontFamily.HELVETICA, 8, Font.NORMAL, BaseColor.BLACK);
			Font headFont1 = new Font(FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK);
			Font f = new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.UNDERLINE, BaseColor.BLUE);

			PdfPCell hcell;
			hcell = new PdfPCell(new Phrase("Sr.", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("SP Cake Weight", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Count", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);
			table.addCell(hcell);

			double totalCount = 0;

			int index = 0;
			for (int j = 0; j < spCakeList.size(); j++) {

				index++;
				PdfPCell cell;

				cell = new PdfPCell(new Phrase(String.valueOf(index), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + spCakeList.get(j).getSpSelectedWt(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(1);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + spCakeList.get(j).getCount(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(1);
				table.addCell(cell);

				totalCount = totalCount + spCakeList.get(j).getCount();

			}

			PdfPCell cell;

			cell = new PdfPCell(new Phrase("", headFont));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("Total", headFont));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingRight(1);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("" + totalCount + "  ", headFont));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingRight(1);
			table.addCell(cell);
			document.open();

			Paragraph heading = new Paragraph("Sp Cake Report \n From Date:" + fromDate + " To Date:" + toDate);
			heading.setAlignment(Element.ALIGN_CENTER);
			document.add(heading);

			DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
			String reportDate = DF.format(new Date());

			document.add(new Paragraph("\n"));

			document.add(table);

			document.close();

			if (file != null) {

				String mimeType = URLConnection.guessContentTypeFromName(file.getName());

				if (mimeType == null) {

					mimeType = "application/pdf";

				}

				response.setContentType(mimeType);

				response.addHeader("content-disposition", String.format("inline; filename=\"%s\"", file.getName()));

				// response.setHeader("Content-Disposition", String.format("attachment;
				// filename=\"%s\"", file.getName()));

				response.setContentLength((int) file.length());

				InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

				try {
					FileCopyUtils.copy(inputStream, response.getOutputStream());
				} catch (IOException e) {
					System.out.println("Excep in Opening a Pdf File");
					e.printStackTrace();
				}

			}

		} catch (DocumentException ex) {

			System.out.println("Pdf Generation Error: Prod From Orders" + ex.getMessage());

			ex.printStackTrace();

		}
	}

}
