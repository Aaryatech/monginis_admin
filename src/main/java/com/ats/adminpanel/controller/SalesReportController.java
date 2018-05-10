package com.ats.adminpanel.controller;

import java.awt.Dimension;
import java.awt.Insets;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.model.AllFrIdName;
import com.ats.adminpanel.model.AllFrIdNameList;
import com.ats.adminpanel.model.AllRoutesListResponse;
import com.ats.adminpanel.model.DispatchReport;
import com.ats.adminpanel.model.DispatchReportList;
import com.ats.adminpanel.model.ExportToExcel;
import com.ats.adminpanel.model.Route;
import com.ats.adminpanel.model.franchisee.FrNameIdByRouteId;
import com.ats.adminpanel.model.franchisee.FrNameIdByRouteIdResponse;
import com.ats.adminpanel.model.franchisee.Menu;
import com.ats.adminpanel.model.franchisee.SubCategory;
import com.ats.adminpanel.model.item.CategoryListResponse;
import com.ats.adminpanel.model.item.FrItemStockConfigureList;
import com.ats.adminpanel.model.item.Item;
import com.ats.adminpanel.model.item.MCategoryList;
import com.ats.adminpanel.model.salesreport.RoyaltyListBean;
import com.ats.adminpanel.model.salesreport.SalesReportBillwise;
import com.ats.adminpanel.model.salesreport.SalesReportBillwiseAllFr;
import com.ats.adminpanel.model.salesreport.SalesReportItemwise;
import com.ats.adminpanel.model.salesreport.SalesReportRoyalty;
import com.ats.adminpanel.model.salesreport.SalesReportRoyaltyFr;

@Controller
@Scope("session")
public class SalesReportController {

	List<String> frList = new ArrayList<>();
	AllFrIdNameList allFrIdNameList = new AllFrIdNameList();
	List<SalesReportBillwise> saleListForPdf;// it is Static
	String todaysDate;
	List<SalesReportRoyalty> royaltyListForPdf;

	List<SalesReportBillwiseAllFr> staticSaleByAllFr;

	List<SalesReportRoyaltyFr> royaltyFrList;

	List<SalesReportRoyaltyFr> staticRoyaltyFrList;

	List<SalesReportItemwise> staticSaleListItemWise;

	RoyaltyListBean staticRoyaltyBean = new RoyaltyListBean();
	
	
	float getRoyPer() {
		
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();
		
		String settingKey = new String();

		settingKey = "roy_percentage";

		map.add("settingKeyList", settingKey);

		FrItemStockConfigureList settingList = restTemplate.postForObject(Constants.url + "getDeptSettingValue", map,
				FrItemStockConfigureList.class);

		float royPer = settingList.getFrItemStockConfigure().get(0).getSettingValue();
		
		return royPer;
	}

	@RequestMapping(value = "/showSaleReports", method = RequestMethod.GET)
	public ModelAndView showSaleReporPage(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/viewSalesReports");

		return model;

	}

	@RequestMapping(value = "/showSaleReportByDate", method = RequestMethod.GET)
	public ModelAndView showSaleReportByDate(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/billwisesalesbydate");

		// Constants.mainAct =2;
		// Constants.subAct =20;

		try {
			ZoneId z = ZoneId.of("Asia/Calcutta");

			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			todaysDate = date.format(formatters);

			RestTemplate restTemplate = new RestTemplate();

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
			model.addObject("unSelectedFrList", allFrIdNameList.getFrIdNamesList());

			model.addObject("routeList", routeList);

		} catch (Exception e) {

			System.out.println("Exc in show sales report bill wise  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/getSaleBillwise", method = RequestMethod.GET)
	public @ResponseBody List<SalesReportBillwise> saleReportBillWise(HttpServletRequest request,
			HttpServletResponse response) {

		List<SalesReportBillwise> saleList = new ArrayList<>();

		try {
			System.out.println("Inside get Sale Bill Wise");
			String selectedFr = request.getParameter("fr_id_list");
			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
			String routeId = request.getParameter("route_id");

			boolean isAllFrSelected = false;
			selectedFr = selectedFr.substring(1, selectedFr.length() - 1);
			selectedFr = selectedFr.replaceAll("\"", "");

			frList = new ArrayList<>();
			frList = Arrays.asList(selectedFr);

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

			if (frList.contains("-1")) {
				isAllFrSelected = true;
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			if (isAllFrSelected) {

				System.out.println("Inside If all fr Selected ");

				map.add("fromDate", fromDate);
				map.add("toDate", toDate);
				ParameterizedTypeReference<List<SalesReportBillwise>> typeRef = new ParameterizedTypeReference<List<SalesReportBillwise>>() {
				};
				ResponseEntity<List<SalesReportBillwise>> responseEntity = restTemplate.exchange(
						Constants.url + "getSaleReportBillwiseAllFrSelected", HttpMethod.POST, new HttpEntity<>(map),
						typeRef);

				saleList = responseEntity.getBody();
				saleListForPdf = new ArrayList<>();
				saleListForPdf = saleList;
				System.out.println("sales List Bill Wise " + saleList.toString());

			} else {
				System.out.println("Inside else Few fr Selected ");

				map.add("frIdList", selectedFr);
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				ParameterizedTypeReference<List<SalesReportBillwise>> typeRef = new ParameterizedTypeReference<List<SalesReportBillwise>>() {
				};
				ResponseEntity<List<SalesReportBillwise>> responseEntity = restTemplate.exchange(
						Constants.url + "getSaleReportBillwise", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				saleList = responseEntity.getBody();
				saleListForPdf = new ArrayList<>();
				saleListForPdf = saleList;
				System.out.println("sales List Bill Wise " + saleList.toString());

			}
		} catch (Exception e) {
			System.out.println("get sale Report Bill Wise " + e.getMessage());
			e.printStackTrace();

		}

		// exportToExcel
		List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

		ExportToExcel expoExcel = new ExportToExcel();
		List<String> rowData = new ArrayList<String>();

		rowData.add("Bill No");
		rowData.add("Invoice No");
		rowData.add("Bill Date");
		rowData.add("Franchisee Code");
		rowData.add("Franchisee Name");
		rowData.add("Franchisee City");
		rowData.add("Franchisee Gst No");
		rowData.add("sgst sum");
		rowData.add("cgst sum");
		rowData.add("igst sum");
		rowData.add("Total Tax");
		rowData.add("Grand Total");
		rowData.add("Taxable Amt");

		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);
		for (int i = 0; i < saleList.size(); i++) {
			expoExcel = new ExportToExcel();
			rowData = new ArrayList<String>();

			rowData.add("" + saleList.get(i).getBillNo());
			rowData.add(saleList.get(i).getInvoiceNo());
			rowData.add(saleList.get(i).getBillDate());

			rowData.add("" + saleList.get(i).getFrId());
			rowData.add(saleList.get(i).getFrName());

			rowData.add(saleList.get(i).getFrCity());
			rowData.add(saleList.get(i).getFrGstNo());
			rowData.add("" + saleList.get(i).getSgstSum());
			rowData.add("" + saleList.get(i).getCgstSum());
			rowData.add("" + saleList.get(i).getIgstSum());
			rowData.add("" + saleList.get(i).getTotalTax());
			rowData.add("" + saleList.get(i).getGrandTotal());
			rowData.add("" + saleList.get(i).getTaxableAmt());

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);

		}

		HttpSession session = request.getSession();
		session.setAttribute("exportExcelList", exportToExcelList);
		session.setAttribute("excelName", "SaleBillWiseDate");

		return saleList;
	}

	@RequestMapping(value = "pdf/showSaleReportByDatePdf/{fDate}/{tDate}/{selectedFr}/{routeId}", method = RequestMethod.GET)
	public ModelAndView showSaleReportByDatePdf(@PathVariable String fDate, @PathVariable String tDate,
			@PathVariable String selectedFr, @PathVariable String routeId, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/pdf/billwisesalesbydatePdf");

		List<SalesReportBillwise> saleList = new ArrayList<>();

		boolean isAllFrSelected = false;
		try {

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

			if (selectedFr.equalsIgnoreCase("-1")) {
				isAllFrSelected = true;
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			if (isAllFrSelected) {

				System.out.println("Inside If all fr Selected ");

				map.add("fromDate", fDate);
				map.add("toDate", tDate);
				ParameterizedTypeReference<List<SalesReportBillwise>> typeRef = new ParameterizedTypeReference<List<SalesReportBillwise>>() {
				};
				ResponseEntity<List<SalesReportBillwise>> responseEntity = restTemplate.exchange(
						Constants.url + "getSaleReportBillwiseAllFrSelected", HttpMethod.POST, new HttpEntity<>(map),
						typeRef);

				saleList = responseEntity.getBody();
				saleListForPdf = new ArrayList<>();
				saleListForPdf = saleList;
				System.out.println("sales List Bill Wise " + saleList.toString());

			} else {
				System.out.println("Inside else Few fr Selected ");

				map.add("frIdList", selectedFr);
				map.add("fromDate", fDate);
				map.add("toDate", tDate);

				ParameterizedTypeReference<List<SalesReportBillwise>> typeRef = new ParameterizedTypeReference<List<SalesReportBillwise>>() {
				};
				ResponseEntity<List<SalesReportBillwise>> responseEntity = restTemplate.exchange(
						Constants.url + "getSaleReportBillwise", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				saleList = responseEntity.getBody();
				saleListForPdf = new ArrayList<>();
				saleListForPdf = saleList;
				System.out.println("sales List Bill Wise " + saleList.toString());

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		model.addObject("fromDate", fDate);

		model.addObject("toDate", tDate);

		model.addObject("report", saleList);

		return model;
	}

	// report 2
	@RequestMapping(value = "/showSaleReportByFr", method = RequestMethod.GET)
	public ModelAndView showSaleReportByFr(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/billwisesalebyfr");
		System.out.println("inside showSaleReportByFr ");
		// Constants.mainAct =2;
		// Constants.subAct =20;
		HttpSession session = request.getSession();

		System.out.println("session Id in show Page  " + session.getId());

		try {
			ZoneId z = ZoneId.of("Asia/Calcutta");

			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			String todaysDate = date.format(formatters);

			RestTemplate restTemplate = new RestTemplate();

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
			model.addObject("unSelectedFrList", allFrIdNameList.getFrIdNamesList());

			model.addObject("routeList", routeList);

		} catch (Exception e) {

			System.out.println("Exc in show sales report bill wise  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/getSaleBillwiseByFr", method = RequestMethod.GET)
	public @ResponseBody List<SalesReportBillwise> getSaleBillwiseByFr(HttpServletRequest request,
			HttpServletResponse response) {

		List<SalesReportBillwise> saleList = new ArrayList<>();

		try {
			System.out.println("Inside get Sale Bill Wise");
			String selectedFr = request.getParameter("fr_id_list");
			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
			String routeId = request.getParameter("route_id");

			boolean isAllFrSelected = false;
			selectedFr = selectedFr.substring(1, selectedFr.length() - 1);
			selectedFr = selectedFr.replaceAll("\"", "");

			frList = new ArrayList<>();
			frList = Arrays.asList(selectedFr);

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

			if (frList.contains("-1")) {
				isAllFrSelected = true;
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			if (isAllFrSelected) {

				System.out.println("Inside If all fr Selected ");

				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				ParameterizedTypeReference<List<SalesReportBillwise>> typeRef = new ParameterizedTypeReference<List<SalesReportBillwise>>() {
				};
				ResponseEntity<List<SalesReportBillwise>> responseEntity = restTemplate.exchange(
						Constants.url + "getSaleReportBillwiseByFrAllFr", HttpMethod.POST, new HttpEntity<>(map),
						typeRef);

				saleList = responseEntity.getBody();
				saleListForPdf = new ArrayList<>();
				String name = "Sachin";

				HttpSession session = request.getSession();

				System.out.println("session Id  In Ajax Call " + session.getId());
				session.setAttribute("pdfData", saleList);

				session.setAttribute("name", name);
				saleListForPdf = saleList;

				System.out.println("sales List Bill Wise " + saleList.toString());

			} else {
				System.out.println("Inside else Few fr Selected ");

				map.add("frIdList", selectedFr);
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				ParameterizedTypeReference<List<SalesReportBillwise>> typeRef = new ParameterizedTypeReference<List<SalesReportBillwise>>() {
				};
				ResponseEntity<List<SalesReportBillwise>> responseEntity = restTemplate.exchange(
						Constants.url + "getSaleReportBillwiseByFr", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				saleList = responseEntity.getBody();
				saleListForPdf = new ArrayList<>();
				String name = "Sachin";

				HttpSession session = request.getSession();

				System.out.println("session Id  In Ajax Call " + session.getId());
				session.setAttribute("pdfData", saleList);

				session.setAttribute("name", name);
				saleListForPdf = saleList;

				System.out.println("sales List Bill Wise " + saleList.toString());

			}
		} catch (Exception e) {
			System.out.println("get sale Report Bill Wise " + e.getMessage());
			e.printStackTrace();

		}

		// exportToExcel
		List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

		ExportToExcel expoExcel = new ExportToExcel();
		List<String> rowData = new ArrayList<String>();

		rowData.add("Bill No");
		rowData.add("Invoice No");
		rowData.add("Bill Date");
		rowData.add("Franchisee Code");
		rowData.add("Franchisee Name");
		rowData.add("Franchisee City");
		rowData.add("Franchisee Gst No");
		rowData.add("sgst sum");
		rowData.add("cgst sum");
		rowData.add("igst sum");
		rowData.add("Total Tax");
		rowData.add("Grand Total");
		rowData.add("Taxable Amt");

		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);
		for (int i = 0; i < saleList.size(); i++) {
			expoExcel = new ExportToExcel();
			rowData = new ArrayList<String>();

			rowData.add("" + saleList.get(i).getBillNo());
			rowData.add(saleList.get(i).getInvoiceNo());
			rowData.add(saleList.get(i).getBillDate());

			rowData.add("" + saleList.get(i).getFrId());
			rowData.add(saleList.get(i).getFrName());

			rowData.add(saleList.get(i).getFrCity());
			rowData.add(saleList.get(i).getFrGstNo());
			rowData.add("" + saleList.get(i).getSgstSum());
			rowData.add("" + saleList.get(i).getCgstSum());
			rowData.add("" + saleList.get(i).getIgstSum());
			rowData.add("" + saleList.get(i).getTotalTax());
			rowData.add("" + saleList.get(i).getGrandTotal());
			rowData.add("" + saleList.get(i).getTaxableAmt());

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);

		}

		HttpSession session = request.getSession();
		session.setAttribute("exportExcelList", exportToExcelList);
		session.setAttribute("excelName", "SaleBillWiseFr");
		return saleList;
	}

	@RequestMapping(value = "pdf/showSaleBillwiseByFrPdf/{fromDate}/{toDate}/{selectedFr}/{routeId}", method = RequestMethod.GET)
	public ModelAndView showSaleBillwiseByFrPdf(@PathVariable String fromDate, @PathVariable String toDate,
			@PathVariable String selectedFr, @PathVariable String routeId, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView model = new ModelAndView("reports/sales/pdf/billwisesalebyfrPdf");

		List<SalesReportBillwise> saleList = new ArrayList<>();
		boolean isAllFrSelected = false;

		try {

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

			if (selectedFr.equalsIgnoreCase("-1")) {
				isAllFrSelected = true;
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			if (isAllFrSelected) {

				System.out.println("Inside If all fr Selected ");

				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				ParameterizedTypeReference<List<SalesReportBillwise>> typeRef = new ParameterizedTypeReference<List<SalesReportBillwise>>() {
				};
				ResponseEntity<List<SalesReportBillwise>> responseEntity = restTemplate.exchange(
						Constants.url + "getSaleReportBillwiseByFrAllFr", HttpMethod.POST, new HttpEntity<>(map),
						typeRef);

				saleList = responseEntity.getBody();
				saleListForPdf = new ArrayList<>();
				String name = "Sachin";

				HttpSession session = request.getSession();

				System.out.println("session Id  In Ajax Call " + session.getId());
				session.setAttribute("pdfData", saleList);

				session.setAttribute("name", name);
				saleListForPdf = saleList;

				System.out.println("sales List Bill Wise " + saleList.toString());

			} else {

				System.out.println("Inside else Few fr Selected mgg" + selectedFr + "ui");

				map.add("frIdList", selectedFr);
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				ParameterizedTypeReference<List<SalesReportBillwise>> typeRef = new ParameterizedTypeReference<List<SalesReportBillwise>>() {
				};
				ResponseEntity<List<SalesReportBillwise>> responseEntity = restTemplate.exchange(
						Constants.url + "getSaleReportBillwiseByFr", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				saleList = responseEntity.getBody();
				saleListForPdf = new ArrayList<>();
				String name = "Sachin";

				HttpSession session = request.getSession();

				System.out.println("session Id  In Ajax Call " + session.getId());
				session.setAttribute("pdfData", saleList);

				session.setAttribute("name", name);
				saleListForPdf = saleList;

				System.out.println("sales List Bill Wise " + saleList.toString());

			}

			model.addObject("fromDate", fromDate);

			model.addObject("toDate", toDate);

			model.addObject("report", saleListForPdf);
		} catch (Exception e) {
			System.out.println("get sale Report Bill Wise1 " + e.getMessage());
			e.printStackTrace();

		}

		return model;
	}

	// report 3
	@RequestMapping(value = "/showSaleReportGrpByDate", method = RequestMethod.GET)
	public ModelAndView showSaleReportGrpByDate(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/billwisesalesgrpbydate");
		System.out.println("inside showSaleReportGrpByDate ");
		// Constants.mainAct =2;
		// Constants.subAct =20;

		try {
			ZoneId z = ZoneId.of("Asia/Calcutta");

			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			String todaysDate = date.format(formatters);

			RestTemplate restTemplate = new RestTemplate();

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
			model.addObject("unSelectedFrList", allFrIdNameList.getFrIdNamesList());

			model.addObject("routeList", routeList);

		} catch (Exception e) {

			System.out.println("Exc in show sales report bill wise  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/getSaleBillwiseGrpByDate", method = RequestMethod.GET)
	public @ResponseBody List<SalesReportBillwise> getSaleBillwiseGrpByDate(HttpServletRequest request,
			HttpServletResponse response) {

		List<SalesReportBillwise> saleList = new ArrayList<>();

		try {
			System.out.println("Inside get Sale Bill Wise");
			String selectedFr = request.getParameter("fr_id_list");
			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
			String routeId = request.getParameter("route_id");

			boolean isAllFrSelected = false;
			selectedFr = selectedFr.substring(1, selectedFr.length() - 1);
			selectedFr = selectedFr.replaceAll("\"", "");

			frList = new ArrayList<>();
			frList = Arrays.asList(selectedFr);

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

			if (frList.contains("-1")) {
				isAllFrSelected = true;
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			if (isAllFrSelected) {

				System.out.println("Inside If all fr Selected ");

				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				ParameterizedTypeReference<List<SalesReportBillwise>> typeRef = new ParameterizedTypeReference<List<SalesReportBillwise>>() {
				};
				ResponseEntity<List<SalesReportBillwise>> responseEntity = restTemplate.exchange(
						Constants.url + "getSaleReportBillwiseByDateAllFr", HttpMethod.POST, new HttpEntity<>(map),
						typeRef);

				saleList = responseEntity.getBody();
				saleListForPdf = new ArrayList<>();

				saleListForPdf = saleList;

				System.out.println("sales List Bill Wise " + saleList.toString());

			} else {
				System.out.println("Inside else Few fr Selected ");

				map.add("frIdList", selectedFr);
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				ParameterizedTypeReference<List<SalesReportBillwise>> typeRef = new ParameterizedTypeReference<List<SalesReportBillwise>>() {
				};
				ResponseEntity<List<SalesReportBillwise>> responseEntity = restTemplate.exchange(
						Constants.url + "getSaleReportBillwiseByDate", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				saleList = responseEntity.getBody();
				saleListForPdf = new ArrayList<>();

				saleListForPdf = saleList;

				System.out.println("sales List Bill Wise " + saleList.toString());

			}
		} catch (Exception e) {
			System.out.println("get sale Report Bill Wise " + e.getMessage());
			e.printStackTrace();

		}

		// exportToExcel
		List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

		ExportToExcel expoExcel = new ExportToExcel();
		List<String> rowData = new ArrayList<String>();

		rowData.add("Bill No");
		rowData.add("Invoice No");
		rowData.add("Bill Date");
		rowData.add("Franchisee Code");
		rowData.add("Franchisee Name");
		rowData.add("Franchisee City");
		rowData.add("Franchisee Gst No");
		rowData.add("sgst sum");
		rowData.add("cgst sum");
		rowData.add("igst sum");
		rowData.add("Total Tax");
		rowData.add("Grand Total");
		rowData.add("Taxable Amt");

		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);
		for (int i = 0; i < saleList.size(); i++) {
			expoExcel = new ExportToExcel();
			rowData = new ArrayList<String>();

			rowData.add("" + saleList.get(i).getBillNo());
			rowData.add(saleList.get(i).getInvoiceNo());
			rowData.add(saleList.get(i).getBillDate());

			rowData.add("" + saleList.get(i).getFrId());
			rowData.add(saleList.get(i).getFrName());

			rowData.add(saleList.get(i).getFrCity());
			rowData.add(saleList.get(i).getFrGstNo());
			rowData.add("" + saleList.get(i).getSgstSum());
			rowData.add("" + saleList.get(i).getCgstSum());
			rowData.add("" + saleList.get(i).getIgstSum());
			rowData.add("" + saleList.get(i).getTotalTax());
			rowData.add("" + saleList.get(i).getGrandTotal());
			rowData.add("" + saleList.get(i).getTaxableAmt());

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);

		}

		HttpSession session = request.getSession();
		session.setAttribute("exportExcelList", exportToExcelList);
		session.setAttribute("excelName", "BillWiseGroupByDate");
		return saleList;

	}

	@RequestMapping(value = "pdf/showSaleBillwiseGrpByDatePdf/{fromDate}/{toDate}/{selectedFr}/{routeId}", method = RequestMethod.GET)
	public ModelAndView showSaleBillwiseGrpByDate(@PathVariable String fromDate, @PathVariable String toDate,
			@PathVariable String selectedFr, @PathVariable String routeId, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/pdf/billwisesalesgrpbydatePdf");
		List<SalesReportBillwise> saleList = new ArrayList<>();
		boolean isAllFrSelected = false;

		try {
			System.out.println("Inside get Sale Bill Wise");

			frList = new ArrayList<>();
			frList = Arrays.asList(selectedFr);

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

			if (selectedFr.equalsIgnoreCase("-1")) {
				isAllFrSelected = true;
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			if (isAllFrSelected) {

				System.out.println("Inside If all fr Selected ");

				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				ParameterizedTypeReference<List<SalesReportBillwise>> typeRef = new ParameterizedTypeReference<List<SalesReportBillwise>>() {
				};
				ResponseEntity<List<SalesReportBillwise>> responseEntity = restTemplate.exchange(
						Constants.url + "getSaleReportBillwiseByDateAllFr", HttpMethod.POST, new HttpEntity<>(map),
						typeRef);

				saleList = responseEntity.getBody();
				saleListForPdf = new ArrayList<>();

				saleListForPdf = saleList;

				System.out.println("sales List Bill Wise " + saleList.toString());

			} else {
				System.out.println("Inside else Few fr Selected ");

				map.add("frIdList", selectedFr);
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				ParameterizedTypeReference<List<SalesReportBillwise>> typeRef = new ParameterizedTypeReference<List<SalesReportBillwise>>() {
				};
				ResponseEntity<List<SalesReportBillwise>> responseEntity = restTemplate.exchange(
						Constants.url + "getSaleReportBillwiseByDate", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				saleList = responseEntity.getBody();
				saleListForPdf = new ArrayList<>();

				saleListForPdf = saleList;

				System.out.println("sales List Bill Wise " + saleList.toString());

			}

		} catch (Exception e) {

			System.out.println("Exce in show Sale Bill wise by fr PDF " + e.getMessage());
			e.printStackTrace();

		}
		model.addObject("fromDate", fromDate);

		model.addObject("toDate", toDate);

		model.addObject("report", saleListForPdf);
		return model;
	}

	// getSaleReportBillwiseByMonth

	@RequestMapping(value = "/showSaleReportByMonth", method = RequestMethod.GET)
	public ModelAndView showSaleReportByMonth(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/billwisesalebymonth");

		// Constants.mainAct =2;
		// Constants.subAct =20;

		try {
			ZoneId z = ZoneId.of("Asia/Calcutta");

			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			todaysDate = date.format(formatters);

			RestTemplate restTemplate = new RestTemplate();

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
			model.addObject("unSelectedFrList", allFrIdNameList.getFrIdNamesList());

			model.addObject("routeList", routeList);

		} catch (Exception e) {

			System.out.println("Exc in show sales report bill wise  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/getSaleBillwiseGrpByMonth", method = RequestMethod.GET)
	public @ResponseBody List<SalesReportBillwise> getSaleBillwiseGrpByMonth(HttpServletRequest request,
			HttpServletResponse response) {

		List<SalesReportBillwise> saleList = new ArrayList<>();

		try {
			System.out.println("Inside get Sale Bill Wise");
			String selectedFr = request.getParameter("fr_id_list");
			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
			String routeId = request.getParameter("route_id");

			boolean isAllFrSelected = false;
			selectedFr = selectedFr.substring(1, selectedFr.length() - 1);
			selectedFr = selectedFr.replaceAll("\"", "");

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

			if (frList.contains("-1")) {
				isAllFrSelected = true;
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			if (isAllFrSelected) {

				System.out.println("Inside If all fr Selected ");

				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				ParameterizedTypeReference<List<SalesReportBillwise>> typeRef = new ParameterizedTypeReference<List<SalesReportBillwise>>() {
				};
				ResponseEntity<List<SalesReportBillwise>> responseEntity = restTemplate.exchange(
						Constants.url + "getSaleReportBillwiseByMonthAllFr", HttpMethod.POST, new HttpEntity<>(map),
						typeRef);

				saleList = responseEntity.getBody();
				saleListForPdf = new ArrayList<>();

				saleListForPdf = saleList;

				System.out.println("sales List Bill Wise " + saleList.toString());

			} else {
				System.out.println("Inside else Few fr Selected ");

				map.add("frIdList", selectedFr);
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				ParameterizedTypeReference<List<SalesReportBillwise>> typeRef = new ParameterizedTypeReference<List<SalesReportBillwise>>() {
				};
				ResponseEntity<List<SalesReportBillwise>> responseEntity = restTemplate.exchange(
						Constants.url + "getSaleReportBillwiseByMonth", HttpMethod.POST, new HttpEntity<>(map),
						typeRef);

				saleList = responseEntity.getBody();
				saleListForPdf = new ArrayList<>();

				saleListForPdf = saleList;

				System.out.println("sales List Bill Wise " + saleList.toString());

			}
		} catch (Exception e) {
			System.out.println("get sale Report Bill Wise " + e.getMessage());
			e.printStackTrace();

		}

		// exportToExcel
		List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

		ExportToExcel expoExcel = new ExportToExcel();
		List<String> rowData = new ArrayList<String>();

		rowData.add("Bill No");
		rowData.add("Invoice No");
		rowData.add("Bill Date");
		rowData.add("Franchisee Code");
		rowData.add("Franchisee Name");
		rowData.add("Franchisee City");
		rowData.add("Franchisee Gst No");
		rowData.add("sgst sum");
		rowData.add("cgst sum");
		rowData.add("igst sum");
		rowData.add("Total Tax");
		rowData.add("Grand Total");
		rowData.add("Taxable Amt");

		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);
		for (int i = 0; i < saleList.size(); i++) {
			expoExcel = new ExportToExcel();
			rowData = new ArrayList<String>();

			rowData.add("" + saleList.get(i).getBillNo());
			rowData.add(saleList.get(i).getInvoiceNo());
			rowData.add(saleList.get(i).getBillDate());

			rowData.add("" + saleList.get(i).getFrId());
			rowData.add(saleList.get(i).getFrName());

			rowData.add(saleList.get(i).getFrCity());
			rowData.add(saleList.get(i).getFrGstNo());
			rowData.add("" + saleList.get(i).getSgstSum());
			rowData.add("" + saleList.get(i).getCgstSum());
			rowData.add("" + saleList.get(i).getIgstSum());
			rowData.add("" + saleList.get(i).getTotalTax());
			rowData.add("" + saleList.get(i).getGrandTotal());
			rowData.add("" + saleList.get(i).getTaxableAmt());

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);

		}

		HttpSession session = request.getSession();
		session.setAttribute("exportExcelList", exportToExcelList);
		session.setAttribute("excelName", "SaleBillWiseByMonth");
		return saleList;

	}

	@RequestMapping(value = "pdf/showSaleBillwiseGrpByMonthPdf/{fromDate}/{toDate}/{selectedFr}/{routeId}", method = RequestMethod.GET)
	public ModelAndView showSaleBillwiseGrpByMonthPdf(@PathVariable String fromDate, @PathVariable String toDate,
			@PathVariable String selectedFr, @PathVariable String routeId, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/pdf/billwisesalesgrpbymonthPdf");
		List<SalesReportBillwise> saleList = new ArrayList<>();
		boolean isAllFrSelected = false;

		try {
			System.out.println("Inside get Sale Bill Wise");

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

			if (selectedFr.equalsIgnoreCase("-1")) {
				isAllFrSelected = true;
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			if (isAllFrSelected) {

				System.out.println("Inside If all fr Selected ");

				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				ParameterizedTypeReference<List<SalesReportBillwise>> typeRef = new ParameterizedTypeReference<List<SalesReportBillwise>>() {
				};
				ResponseEntity<List<SalesReportBillwise>> responseEntity = restTemplate.exchange(
						Constants.url + "getSaleReportBillwiseByMonthAllFr", HttpMethod.POST, new HttpEntity<>(map),
						typeRef);

				saleList = responseEntity.getBody();
				saleListForPdf = new ArrayList<>();

				saleListForPdf = saleList;

				System.out.println("sales List Bill Wise " + saleList.toString());

			} else {
				System.out.println("Inside else Few fr Selected ");

				map.add("frIdList", selectedFr);
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				ParameterizedTypeReference<List<SalesReportBillwise>> typeRef = new ParameterizedTypeReference<List<SalesReportBillwise>>() {
				};
				ResponseEntity<List<SalesReportBillwise>> responseEntity = restTemplate.exchange(
						Constants.url + "getSaleReportBillwiseByMonth", HttpMethod.POST, new HttpEntity<>(map),
						typeRef);

				saleList = responseEntity.getBody();
				saleListForPdf = new ArrayList<>();

				saleListForPdf = saleList;

				System.out.println("sales List Bill Wise " + saleList.toString());

			}

		} catch (Exception e) {
			System.out.println("Exce in show Sale Bill wise by fr PDF " + e.getMessage());
			e.printStackTrace();
		}
		model.addObject("fromDate", fromDate);

		model.addObject("toDate", toDate);

		model.addObject("report", saleListForPdf);
		return model;
	}

	// *******************************************************************//
	// Royalty Sale

	@RequestMapping(value = "/showSaleRoyaltyByCat", method = RequestMethod.GET)
	public ModelAndView showSaleRoyaltyByCat(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/salesroyaltybycat");

		// Constants.mainAct =2;
		// Constants.subAct =20;

		try {
			ZoneId z = ZoneId.of("Asia/Calcutta");

			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			todaysDate = date.format(formatters);

			RestTemplate restTemplate = new RestTemplate();
			
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			
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
			model.addObject("unSelectedFrList", allFrIdNameList.getFrIdNamesList());

			model.addObject("routeList", routeList);
			model.addObject("royPer",getRoyPer());

		} catch (Exception e) {

			System.out.println("Exc in show sales report bill wise  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/getSaleRoyaltyByCat", method = RequestMethod.GET)
	public @ResponseBody RoyaltyListBean getSaleRoyaltyByCat(HttpServletRequest request, HttpServletResponse response) {

		List<SalesReportBillwise> saleList = new ArrayList<>();
		List<SalesReportRoyalty> royaltyList = new ArrayList<>();
		RoyaltyListBean royaltyBean = new RoyaltyListBean();

		try {
			System.out.println("Inside get Sale Bill Wise");
			String selectedFr = request.getParameter("fr_id_list");
			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
			String routeId = request.getParameter("route_id");

			boolean isAllFrSelected = false;
			selectedFr = selectedFr.substring(1, selectedFr.length() - 1);
			selectedFr = selectedFr.replaceAll("\"", "");

			frList = new ArrayList<>();
			frList = Arrays.asList(selectedFr);

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

			if (frList.contains("-1")) {
				isAllFrSelected = true;
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			if (isAllFrSelected) {

				System.out.println("Inside If all fr Selected ");

				// web serviece: getSalesReportRoyaltyAllFr

				map.add("fromDate", fromDate);
				map.add("toDate", toDate);
				
				
				ParameterizedTypeReference<List<SalesReportRoyalty>> typeRef = new ParameterizedTypeReference<List<SalesReportRoyalty>>() {
				};
				ResponseEntity<List<SalesReportRoyalty>> responseEntity = restTemplate.exchange(
						Constants.url + "getSalesReportRoyaltyAllFr", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				royaltyList = responseEntity.getBody();
				royaltyListForPdf = new ArrayList<>();

			} else {
				System.out.println("Inside else Few fr Selected ");

				map.add("frIdList", selectedFr);
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				ParameterizedTypeReference<List<SalesReportRoyalty>> typeRef = new ParameterizedTypeReference<List<SalesReportRoyalty>>() {
				};
				ResponseEntity<List<SalesReportRoyalty>> responseEntity = restTemplate.exchange(
						Constants.url + "getSalesReportRoyalty", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				royaltyList = responseEntity.getBody();
				royaltyListForPdf = new ArrayList<>();
			}

				royaltyListForPdf = royaltyList;

				System.out.println("royaltyList List Bill Wise " + saleList.toString());

				CategoryListResponse categoryListResponse = restTemplate.getForObject(Constants.url + "showAllCategory",
						CategoryListResponse.class);
				List<MCategoryList> categoryList;
				categoryList = categoryListResponse.getmCategoryList();
				// allFrIdNameList = new AllFrIdNameList();
				System.out.println("Category list  " + categoryList);

				royaltyBean.setCategoryList(categoryList);
				royaltyBean.setSalesReportRoyalty(royaltyList);
				staticRoyaltyBean = royaltyBean;
			
		} catch (Exception e) {
			System.out.println("get sale Report royaltyList by cat " + e.getMessage());
			e.printStackTrace();

		}
		// exportToExcel
		List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

		ExportToExcel expoExcel = new ExportToExcel();
		List<String> rowData = new ArrayList<String>();

		rowData.add("Sr.No.");
		rowData.add("Category Name");
		rowData.add("Item Name");
		rowData.add("Sale Qty");
		rowData.add("Sale Value");

		rowData.add("GRN Qty");
		rowData.add("GRN Value");
		rowData.add("GVN Qty");
		rowData.add("GVN Value");

		rowData.add("Net Qty");
		rowData.add("Net Value");
		rowData.add("Royalty %");
		rowData.add("Royalty Amt");
		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);
		if (!royaltyBean.getSalesReportRoyalty().isEmpty()) {
			for (int i = 0; i < royaltyList.size(); i++) {
				int index = 1;
				index = index + i;
				expoExcel = new ExportToExcel();
				rowData = new ArrayList<String>();

				rowData.add("" + index);
				rowData.add("" + royaltyList.get(i).getCat_name());

				rowData.add("" + royaltyList.get(i).getItem_name());

				rowData.add("" + roundUp(royaltyList.get(i).gettBillQty()));
				rowData.add("" + roundUp(royaltyList.get(i).gettBillTaxableAmt()));

				rowData.add("" + roundUp(royaltyList.get(i).gettGrnQty()));

				rowData.add("" + roundUp(royaltyList.get(i).gettGrnTaxableAmt()));
				rowData.add("" + roundUp(royaltyList.get(i).gettGvnQty()));
				rowData.add("" + roundUp(royaltyList.get(i).gettGvnTaxableAmt()));

				float netQty = royaltyList.get(i).gettBillQty()
						- (royaltyList.get(i).gettGrnQty() + royaltyList.get(i).gettGvnQty());

				float netValue = royaltyList.get(i).gettBillTaxableAmt()
						- (royaltyList.get(i).gettGrnTaxableAmt() + royaltyList.get(i).gettGvnTaxableAmt());
				float royPer = getRoyPer();
				float rAmt = netValue * royPer / 100;

				rowData.add("" + roundUp(netQty));
				rowData.add("" + roundUp(netValue));
				rowData.add("" + roundUp(royPer));
				rowData.add("" + roundUp(rAmt));

				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);

			}
		}

		HttpSession session = request.getSession();
		session.setAttribute("exportExcelList", exportToExcelList);
		session.setAttribute("excelName", "RoyaltyByCatList");

		return royaltyBean;

	}

	public static float roundUp(float d) {
		return BigDecimal.valueOf(d).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
	}

	@RequestMapping(value = "pdf/showSaleRoyaltyByCatPdf/{fromDate}/{toDate}/{selectedFr}/{routeId}", method = RequestMethod.GET)
	public ModelAndView showSaleBil(@PathVariable String fromDate, @PathVariable String toDate,
			@PathVariable String selectedFr, @PathVariable String routeId, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/pdf/salesroyaltybycatPdf");
		List<SalesReportBillwise> saleList = new ArrayList<>();
		List<SalesReportRoyalty> royaltyList = new ArrayList<>();
		RoyaltyListBean royaltyBean = new RoyaltyListBean();
		boolean isAllFrSelected = false;

		try {
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			RestTemplate restTemplate = new RestTemplate();
			
			
			float royPer=getRoyPer();
			
			if (!routeId.equalsIgnoreCase("0")) {

		
				map = new LinkedMultiValueMap<String, Object>();
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

			if (selectedFr.equalsIgnoreCase("-1")) {
				isAllFrSelected = true;
			}

			 map = new LinkedMultiValueMap<String, Object>();
		
			if (isAllFrSelected) {

				System.out.println("Inside If all fr Selected ");
				// getSalesReportRoyaltyAllFr
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);
				
				
				ParameterizedTypeReference<List<SalesReportRoyalty>> typeRef = new ParameterizedTypeReference<List<SalesReportRoyalty>>() {
				};
				ResponseEntity<List<SalesReportRoyalty>> responseEntity = restTemplate.exchange(
						Constants.url + "getSalesReportRoyaltyAllFr", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				royaltyList = responseEntity.getBody();
				

			} else {
				System.out.println("Inside else Few fr Selected ");

				map.add("frIdList", selectedFr);
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				ParameterizedTypeReference<List<SalesReportRoyalty>> typeRef = new ParameterizedTypeReference<List<SalesReportRoyalty>>() {
				};
				ResponseEntity<List<SalesReportRoyalty>> responseEntity = restTemplate.exchange(
						Constants.url + "getSalesReportRoyalty", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				royaltyList = responseEntity.getBody();
				
			}
				royaltyListForPdf = new ArrayList<>();

				royaltyListForPdf = royaltyList;

				System.out.println("royaltyList List Bill Wise " + saleList.toString());

				CategoryListResponse categoryListResponse = restTemplate.getForObject(Constants.url + "showAllCategory",
						CategoryListResponse.class);
				List<MCategoryList> categoryList;
				categoryList = categoryListResponse.getmCategoryList();
				// allFrIdNameList = new AllFrIdNameList();
				System.out.println("Category list  " + categoryList);

				royaltyBean.setCategoryList(categoryList);
				royaltyBean.setSalesReportRoyalty(royaltyList);
				staticRoyaltyBean = royaltyBean;
				model.addObject("royPer",royPer);
			
		} catch (Exception e) {
			System.out.println("get sale Report royaltyList by cat " + e.getMessage());
			e.printStackTrace();

		}

		model.addObject("fromDate", fromDate);

		model.addObject("toDate", toDate);

		model.addObject("catList", staticRoyaltyBean.getCategoryList());
		model.addObject("royaltyList", staticRoyaltyBean.getSalesReportRoyalty());
		return model;
	}

	// royalty FR wise

	@RequestMapping(value = "/showSaleRoyaltyByFr", method = RequestMethod.GET)
	public ModelAndView showSaleRoyaltyByFr(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/salesroyaltybyfr");

		// Constants.mainAct =2;
		// Constants.subAct =20;

		try {
			ZoneId z = ZoneId.of("Asia/Calcutta");

			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			todaysDate = date.format(formatters);

			RestTemplate restTemplate = new RestTemplate();

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
model.addObject("royPer",getRoyPer());
			model.addObject("todaysDate", todaysDate);
			model.addObject("unSelectedFrList", allFrIdNameList.getFrIdNamesList());

			model.addObject("routeList", routeList);

		} catch (Exception e) {

			System.out.println("Exc in show sales report bill wise  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/getSaleRoyaltyByFr", method = RequestMethod.GET)
	public @ResponseBody List<SalesReportRoyaltyFr> getSaleRoyaltyByFr(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			System.out.println("Inside get Sale royalty by fr");
			String selectedFr = request.getParameter("fr_id_list");
			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
			String routeId = request.getParameter("route_id");

			boolean isAllFrSelected = false;
			selectedFr = selectedFr.substring(1, selectedFr.length() - 1);
			selectedFr = selectedFr.replaceAll("\"", "");

			frList = new ArrayList<>();
			frList = Arrays.asList(selectedFr);

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

			if (frList.contains("-1")) {
				isAllFrSelected = true;
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			if (isAllFrSelected) {

				System.out.println("Inside If all fr Selected /getSaleRoyaltyByFr :getSalesReportRoyaltyFrAllFr");
				// Web Service :getSalesReportRoyaltyFrAllFr
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);
				
				ParameterizedTypeReference<List<SalesReportRoyaltyFr>> typeRef = new ParameterizedTypeReference<List<SalesReportRoyaltyFr>>() {
				};
				ResponseEntity<List<SalesReportRoyaltyFr>> responseEntity = restTemplate.exchange(
						Constants.url + "getSalesReportRoyaltyFrAllFr", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				royaltyFrList = new ArrayList<>();
				royaltyFrList = responseEntity.getBody();

			} else {
				System.out.println("Inside else Few fr Selected /getSaleRoyaltyByFr :getSalesReportRoyaltyFr");

				map.add("frIdList", selectedFr);
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				ParameterizedTypeReference<List<SalesReportRoyaltyFr>> typeRef = new ParameterizedTypeReference<List<SalesReportRoyaltyFr>>() {
				};
				ResponseEntity<List<SalesReportRoyaltyFr>> responseEntity = restTemplate.exchange(
						Constants.url + "getSalesReportRoyaltyFr", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				royaltyFrList = new ArrayList<>();
				royaltyFrList = responseEntity.getBody();
			}
				// royaltyListForPdf=new ArrayList<>();

				// royaltyListForPdf=royaltyList;
				staticRoyaltyFrList = new ArrayList<>();

				staticRoyaltyFrList = royaltyFrList;
				System.out.println("royalty List List royaltyFr List " + royaltyFrList.toString());

				// allFrIdNameList = new AllFrIdNameList();

			
		} catch (Exception e) {
			System.out.println("get sale Report royaltyList by cat " + e.getMessage());
			e.printStackTrace();

		}

		// exportToExcel
		List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

		ExportToExcel expoExcel = new ExportToExcel();
		List<String> rowData = new ArrayList<String>();

		rowData.add("fr Id");
		rowData.add("Franchisee Code");
		rowData.add("Franchisee Name");
		rowData.add("Franchisee City");

		rowData.add("Grn Taxable Amt");
		rowData.add("Gvn Taxable Amt");
		rowData.add("Taxable Amt");

		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);
		for (int i = 0; i < royaltyFrList.size(); i++) {
			expoExcel = new ExportToExcel();
			rowData = new ArrayList<String>();

			rowData.add("" + royaltyFrList.get(i).getFrId());

			rowData.add("" + royaltyFrList.get(i).getFrId());
			rowData.add(royaltyFrList.get(i).getFrName());

			rowData.add(royaltyFrList.get(i).getFrCity());

			rowData.add("" + royaltyFrList.get(i).gettGrnTaxableAmt());
			rowData.add("" + royaltyFrList.get(i).gettGvnTaxableAmt());
			rowData.add("" + royaltyFrList.get(i).gettBillTaxableAmt());

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);

		}

		HttpSession session = request.getSession();
		session.setAttribute("exportExcelList", exportToExcelList);
		session.setAttribute("excelName", "RoyaltyFrList");

		return royaltyFrList;

	}

	// royalty fr pdf is not done

	// done pdf
	@RequestMapping(value = "pdf/showSaleRoyaltyByFrPdf/{fromDate}/{toDate}/{selectedFr}/{routeId}", method = RequestMethod.GET)
	public ModelAndView showSaleRoyaltyByFrPdf(@PathVariable String fromDate, @PathVariable String toDate,
			@PathVariable String selectedFr, @PathVariable String routeId, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/pdf/salesroyaltybyfrPdf");
		boolean isAllFrSelected = false;

		try {
			System.out.println("Inside get Sale royalty by fr");

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

			if (selectedFr.equalsIgnoreCase("-1")) {
				isAllFrSelected = true;
			}
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			if (isAllFrSelected) {

				System.out.println("Inside If all fr Selected ");

				map.add("fromDate", fromDate);
				map.add("toDate", toDate);
				
				ParameterizedTypeReference<List<SalesReportRoyaltyFr>> typeRef = new ParameterizedTypeReference<List<SalesReportRoyaltyFr>>() {
				};
				ResponseEntity<List<SalesReportRoyaltyFr>> responseEntity = restTemplate.exchange(
						Constants.url + "getSalesReportRoyaltyFrAllFr", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				royaltyFrList = new ArrayList<>();
				royaltyFrList = responseEntity.getBody();
				
				//getSalesReportRoyaltyFrAllFr

			} else {
				System.out.println("Inside else Few fr Selected ");

				map.add("frIdList", selectedFr);
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				ParameterizedTypeReference<List<SalesReportRoyaltyFr>> typeRef = new ParameterizedTypeReference<List<SalesReportRoyaltyFr>>() {
				};
				ResponseEntity<List<SalesReportRoyaltyFr>> responseEntity = restTemplate.exchange(
						Constants.url + "getSalesReportRoyaltyFr", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				royaltyFrList = new ArrayList<>();
				royaltyFrList = responseEntity.getBody();
				
			}
				// royaltyListForPdf=new ArrayList<>();

				// royaltyListForPdf=royaltyList;
				staticRoyaltyFrList = new ArrayList<>();

				staticRoyaltyFrList = royaltyFrList;
				System.out.println("royalty List List royaltyFr List " + royaltyFrList.toString());

				// allFrIdNameList = new AllFrIdNameList();

			
		} catch (Exception e) {
			System.out.println("get sale Report royaltyList by Fr " + e.getMessage());
			e.printStackTrace();

		}
		model.addObject("royPer",getRoyPer());

		model.addObject("fromDate", fromDate);

		model.addObject("toDate", toDate);

		model.addObject("report", staticRoyaltyFrList);
		
		

		return model;
	}

	// report no 8
	@RequestMapping(value = "/showSaleReportItemwise", method = RequestMethod.GET)
	public ModelAndView showSaleReportItemwise(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/salesreportitemwise");

		// Constants.mainAct =2;
		// Constants.subAct =20;

		try {
			ZoneId z = ZoneId.of("Asia/Calcutta");

			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			todaysDate = date.format(formatters);

			RestTemplate restTemplate = new RestTemplate();

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
			model.addObject("unSelectedFrList", allFrIdNameList.getFrIdNamesList());

			model.addObject("routeList", routeList);

		} catch (Exception e) {

			System.out.println("Exc in show sales report bill wise  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/getSaleReportItemwise", method = RequestMethod.GET)
	public @ResponseBody List<SalesReportItemwise> getSaleReportItemwise(HttpServletRequest request,
			HttpServletResponse response) {

		List<SalesReportItemwise> saleList = new ArrayList<>();

		try {
			System.out.println("Inside get Sale Item  Wise");
			String selectedFr = request.getParameter("fr_id_list");
			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
			String routeId = request.getParameter("route_id");

			boolean isAllFrSelected = false;
			selectedFr = selectedFr.substring(1, selectedFr.length() - 1);
			selectedFr = selectedFr.replaceAll("\"", "");

			frList = new ArrayList<>();
			frList = Arrays.asList(selectedFr);

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

			if (frList.contains("-1")) {
				isAllFrSelected = true;

				// No frIds for Filter:it us based on Item Selection :
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			if (isAllFrSelected) {

				System.out.println("Inside If all fr Selected ");

				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

			} else {
				System.out.println("Inside else Few fr Selected ");

				map.add("frIdList", selectedFr);
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				ParameterizedTypeReference<List<SalesReportItemwise>> typeRef = new ParameterizedTypeReference<List<SalesReportItemwise>>() {
				};
				ResponseEntity<List<SalesReportItemwise>> responseEntity = restTemplate.exchange(
						Constants.url + "getSaleReportItemwise", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				saleList = responseEntity.getBody();
				saleListForPdf = new ArrayList<>();
				staticSaleListItemWise = saleList;
				// saleListForPdf=saleList;

				System.out.println("sales List Bill Wise " + saleList.toString());

			}
		} catch (Exception e) {
			System.out.println("get sale Report Bill Wise " + e.getMessage());
			e.printStackTrace();

		}

		// exportToExcel
		List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

		ExportToExcel expoExcel = new ExportToExcel();
		List<String> rowData = new ArrayList<String>();

		rowData.add("Item Id");

		rowData.add("Item Name");
		rowData.add("Item Hsn Code");
		rowData.add("Bill Qty Sum");
		rowData.add("Item Tax1");
		rowData.add("Item Tax2");
		rowData.add("Item Tax2");
		rowData.add("Total Tax");
		rowData.add("sgst sum");
		rowData.add("cgst sum");
		rowData.add("igst sum");

		rowData.add("Taxable Amt");

		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);
		for (int i = 0; i < saleList.size(); i++) {
			expoExcel = new ExportToExcel();
			rowData = new ArrayList<String>();

			rowData.add("" + saleList.get(i).getId());

			rowData.add("" + saleList.get(i).getItemName());
			rowData.add("" + saleList.get(i).getItemHsncd());
			rowData.add("" + saleList.get(i).getBillQtySum());
			rowData.add("" + saleList.get(i).getItemTax1());

			rowData.add("" + saleList.get(i).getItemTax2());
			rowData.add("" + saleList.get(i).getItemTax3());
			rowData.add("" + saleList.get(i).getSgstRsSum());
			rowData.add("" + saleList.get(i).getCgstRsSum());

			rowData.add("" + saleList.get(i).getIgstRsSum());
			rowData.add("" + saleList.get(i).getTaxableAmtSum());

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);

		}

		HttpSession session = request.getSession();
		session.setAttribute("exportExcelList", exportToExcelList);
		session.setAttribute("excelName", "SaleReportItemWise");
		return saleList;

	}

	// pdf for r8 to be done
	// pdf for r8
	@RequestMapping(value = "pdf/showSaleReportItemwisePdf/{fromDate}/{toDate}/{selectedFr}/{routeId}", method = RequestMethod.GET)
	public ModelAndView showSaleReportItemwisePdf(@PathVariable String fromDate, @PathVariable String toDate,
			@PathVariable String selectedFr, @PathVariable String routeId, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/pdf/salesreportitemwisePdf");

		List<SalesReportItemwise> saleList = new ArrayList<>();
		boolean isAllFrSelected = false;

		try {
			System.out.println("Inside get Sale Item  Wise");

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

			if (selectedFr.equalsIgnoreCase("-1")) {
				isAllFrSelected = true;
			}
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			if (isAllFrSelected) {

				System.out.println("Inside If all fr Selected ");

				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

			} else {
				System.out.println("Inside else Few fr Selected ");

				map.add("frIdList", selectedFr);
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				ParameterizedTypeReference<List<SalesReportItemwise>> typeRef = new ParameterizedTypeReference<List<SalesReportItemwise>>() {
				};
				ResponseEntity<List<SalesReportItemwise>> responseEntity = restTemplate.exchange(
						Constants.url + "getSaleReportItemwise", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				saleList = responseEntity.getBody();
				saleListForPdf = new ArrayList<>();
				staticSaleListItemWise = saleList;
				// saleListForPdf=saleList;

				System.out.println("sales List Bill Wise " + saleList.toString());

			}
		} catch (Exception e) {
			System.out.println("get sale Report Bill Wise " + e.getMessage());
			e.printStackTrace();

		}

		model.addObject("fromDate", fromDate);

		model.addObject("toDate", toDate);

		model.addObject("report", staticSaleListItemWise);

		return model;
	}

	// report 7
	@RequestMapping(value = "/showSaleReportBillwiseAllFr", method = RequestMethod.GET)
	public ModelAndView showSaleReportBillwiseAllFr(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/salesreportbillallfr");

		// Constants.mainAct =2;
		// Constants.subAct =20;

		try {
			ZoneId z = ZoneId.of("Asia/Calcutta");

			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			todaysDate = date.format(formatters);

			RestTemplate restTemplate = new RestTemplate();

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
			model.addObject("unSelectedFrList", allFrIdNameList.getFrIdNamesList());

			model.addObject("routeList", routeList);

		} catch (Exception e) {

			System.out.println("Exc in show sales report bill wise  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/getSaleReportBillwiseAllFr", method = RequestMethod.GET)
	public @ResponseBody List<SalesReportBillwiseAllFr> getSaleReportBillwiseAllFr(HttpServletRequest request,
			HttpServletResponse response) {

		List<SalesReportBillwiseAllFr> saleList = new ArrayList<>();

		try {
			System.out.println("Inside get Sale Item  Wise");
			// String selectedFr = request.getParameter("fr_id_list");
			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
			String routeId = request.getParameter("route_id");

			String selectedFr;

			// boolean isAllFrSelected = false;
			// selectedFr = selectedFr.substring(1, selectedFr.length() - 1);
			// selectedFr = selectedFr.replaceAll("\"", "");

			// frList = new ArrayList<>();
			// frList = Arrays.asList(selectedFr);

			/*
			 * if (!routeId.equalsIgnoreCase("0")) {
			 * 
			 * MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,
			 * Object>();
			 * 
			 * RestTemplate restTemplate = new RestTemplate();
			 * 
			 * map.add("routeId", routeId);
			 * 
			 * FrNameIdByRouteIdResponse frNameId = restTemplate.postForObject(Constants.url
			 * + "getFrNameIdByRouteId", map, FrNameIdByRouteIdResponse.class);
			 * 
			 * List<FrNameIdByRouteId> frNameIdByRouteIdList =
			 * frNameId.getFrNameIdByRouteIds();
			 * 
			 * System.out.println("route wise franchisee " +
			 * frNameIdByRouteIdList.toString());
			 * 
			 * StringBuilder sbForRouteFrId = new StringBuilder(); for (int i = 0; i <
			 * frNameIdByRouteIdList.size(); i++) {
			 * 
			 * sbForRouteFrId =
			 * sbForRouteFrId.append(frNameIdByRouteIdList.get(i).getFrId().toString() +
			 * ",");
			 * 
			 * }
			 * 
			 * String strFrIdRouteWise = sbForRouteFrId.toString(); selectedFr =
			 * strFrIdRouteWise.substring(0, strFrIdRouteWise.length() - 1);
			 * System.out.println("fr Id Route WISE = " + selectedFr);
			 * 
			 * } // end of if
			 * 
			 * if (frList.contains("-1")) { //isAllFrSelected = true; }
			 */

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();
			
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				ParameterizedTypeReference<List<SalesReportBillwiseAllFr>> typeRef = new ParameterizedTypeReference<List<SalesReportBillwiseAllFr>>() {
				};
				ResponseEntity<List<SalesReportBillwiseAllFr>> responseEntity = restTemplate.exchange(
						Constants.url + "getSaleReportBillwiseAllFr", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				saleList = responseEntity.getBody();
				staticSaleByAllFr = new ArrayList<>();
				staticSaleByAllFr = saleList;
				// saleListForPdf=saleList;

				System.out.println("sales List Bill Wise all fr  " + saleList.toString());

			
		} catch (Exception e) {
			System.out.println("get sale Report Bill Wise all Fr " + e.getMessage());
			e.printStackTrace();

		}

		// exportToExcel
		List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

		ExportToExcel expoExcel = new ExportToExcel();
		List<String> rowData = new ArrayList<String>();

		rowData.add("Bill No");
		rowData.add("Invoice No");
		rowData.add("Bill Date");
		rowData.add("Franchisee Id");
		rowData.add("Franchisee Name");
		rowData.add("Franchisee City");
		rowData.add("Franchisee Gst No");
		rowData.add("Item Name");
		rowData.add("Item Hsn Code");
		rowData.add("Item Tax1");
		rowData.add("Item Tax2");
		rowData.add("Item Tax2");
		rowData.add("Total Tax");
		rowData.add("sgst sum");
		rowData.add("cgst sum");
		rowData.add("igst sum");

		rowData.add("Taxable Amt");

		/*
		 * float taxableAmtSum; float sgstRsSum; float cgstRsSum; float igstRsSum;
		 */
		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);
		for (int i = 0; i < saleList.size(); i++) {
			expoExcel = new ExportToExcel();
			rowData = new ArrayList<String>();

			rowData.add("" + saleList.get(i).getBillNo());
			rowData.add(saleList.get(i).getInvoiceNo());
			rowData.add(saleList.get(i).getBillDate());

			rowData.add("" + saleList.get(i).getFrId());
			rowData.add(saleList.get(i).getFrName());

			rowData.add(saleList.get(i).getFrCity());
			rowData.add(saleList.get(i).getFrGstNo());
			rowData.add("" + saleList.get(i).getItemName());
			rowData.add("" + saleList.get(i).getItemHsncd());
			rowData.add("" + saleList.get(i).getItemTax1());

			rowData.add("" + saleList.get(i).getItemTax2());
			rowData.add("" + saleList.get(i).getItemTax3());
			rowData.add("" + saleList.get(i).getSgstRsSum());
			rowData.add("" + saleList.get(i).getCgstRsSum());

			rowData.add("" + saleList.get(i).getIgstRsSum());
			rowData.add("" + saleList.get(i).getTaxableAmtSum());

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);

		}

		HttpSession session = request.getSession();
		session.setAttribute("exportExcelList", exportToExcelList);
		session.setAttribute("excelName", "BillWiseAllFr");
		return saleList;
	}
	// pdf to be done

	// pdf report 7
	@RequestMapping(value = "pdf/showSaleReportBillwiseAllFrPdf/{fromDate}/{toDate}", method = RequestMethod.GET)
	public ModelAndView showSaleReportBillwiseAllFrPdf(@PathVariable String fromDate, @PathVariable String toDate,
			HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/pdf/salereportbillallfrPdf");

		List<SalesReportBillwiseAllFr> saleList = new ArrayList<>();

		try {
			System.out.println("Inside get Sale Item  Wise");

			String selectedFr;

			// boolean isAllFrSelected = false;
			// selectedFr = selectedFr.substring(1, selectedFr.length() - 1);
			// selectedFr = selectedFr.replaceAll("\"", "");

			// frList = new ArrayList<>();
			// frList = Arrays.asList(selectedFr);

			/*
			 * if (!routeId.equalsIgnoreCase("0")) {
			 * 
			 * MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,
			 * Object>();
			 * 
			 * RestTemplate restTemplate = new RestTemplate();
			 * 
			 * map.add("routeId", routeId);
			 * 
			 * FrNameIdByRouteIdResponse frNameId = restTemplate.postForObject(Constants.url
			 * + "getFrNameIdByRouteId", map, FrNameIdByRouteIdResponse.class);
			 * 
			 * List<FrNameIdByRouteId> frNameIdByRouteIdList =
			 * frNameId.getFrNameIdByRouteIds();
			 * 
			 * System.out.println("route wise franchisee " +
			 * frNameIdByRouteIdList.toString());
			 * 
			 * StringBuilder sbForRouteFrId = new StringBuilder(); for (int i = 0; i <
			 * frNameIdByRouteIdList.size(); i++) {
			 * 
			 * sbForRouteFrId =
			 * sbForRouteFrId.append(frNameIdByRouteIdList.get(i).getFrId().toString() +
			 * ",");
			 * 
			 * }
			 * 
			 * String strFrIdRouteWise = sbForRouteFrId.toString(); selectedFr =
			 * strFrIdRouteWise.substring(0, strFrIdRouteWise.length() - 1);
			 * System.out.println("fr Id Route WISE = " + selectedFr);
			 * 
			 * } // end of if
			 * 
			 * if (frList.contains("-1")) { //isAllFrSelected = true; }
			 */

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();
			
				System.out.println("Inside else Few fr Selected ");

				// map.add("frIdList", selectedFr);
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				ParameterizedTypeReference<List<SalesReportBillwiseAllFr>> typeRef = new ParameterizedTypeReference<List<SalesReportBillwiseAllFr>>() {
				};
				ResponseEntity<List<SalesReportBillwiseAllFr>> responseEntity = restTemplate.exchange(
						Constants.url + "getSaleReportBillwiseAllFr", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				saleList = responseEntity.getBody();
				staticSaleByAllFr = new ArrayList<>();
				staticSaleByAllFr = saleList;
				// saleListForPdf=saleList;

				System.out.println("sales List Bill Wise all fr  " + saleList.toString());

			
		} catch (Exception e) {
			System.out.println("get sale Report Bill Wise all Fr " + e.getMessage());
			e.printStackTrace();

		}
		model.addObject("fromDate", fromDate);

		model.addObject("toDate", toDate);

		model.addObject("report", staticSaleByAllFr);

		return model;
	}

	// report no 10 conso by category report

	@RequestMapping(value = "/showSaleReportRoyConsoByCat", method = RequestMethod.GET)
	public ModelAndView showSaleReportRoyConsoByCat(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/salesconsbycat");

		// Constants.mainAct =2;
		// Constants.subAct =20;

		try {
			ZoneId z = ZoneId.of("Asia/Calcutta");

			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			todaysDate = date.format(formatters);

			RestTemplate restTemplate = new RestTemplate();

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

			CategoryListResponse categoryListResponse = restTemplate.getForObject(Constants.url + "showAllCategory",
					CategoryListResponse.class);
			List<MCategoryList> categoryList;
			categoryList = categoryListResponse.getmCategoryList();

			System.out.println(" Fr " + allFrIdNameList.getFrIdNamesList());

			model.addObject("catList", categoryList);

			model.addObject("todaysDate", todaysDate);
			model.addObject("unSelectedFrList", allFrIdNameList.getFrIdNamesList());

			model.addObject("routeList", routeList);
			model.addObject("royPer",getRoyPer());


		} catch (Exception e) {

			System.out.println("Exc in show sales report bill wise  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	// ----------------------------Show Dispatch Item
	// List-----------------------------
	@RequestMapping(value = "/showDispatchItemReport", method = RequestMethod.GET)
	public ModelAndView showDispatchItemReport(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/dispatchReport");

		// Constants.mainAct =2;
		// Constants.subAct =20;

		try {
			ZoneId z = ZoneId.of("Asia/Calcutta");

			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			todaysDate = date.format(formatters);

			RestTemplate restTemplate = new RestTemplate();

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

			CategoryListResponse categoryListResponse = restTemplate.getForObject(Constants.url + "showAllCategory",
					CategoryListResponse.class);
			List<MCategoryList> categoryList;
			categoryList = categoryListResponse.getmCategoryList();

			System.out.println(" Fr " + allFrIdNameList.getFrIdNamesList());

			model.addObject("catList", categoryList);

			model.addObject("todaysDate", todaysDate);
			model.addObject("unSelectedFrList", allFrIdNameList.getFrIdNamesList());

			model.addObject("routeList", routeList);

		} catch (Exception e) {

			System.out.println("Exc in show sales report bill wise  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	// ---------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/getDispatchReportByRoute", method = RequestMethod.GET)
	public @ResponseBody DispatchReportList getDispatchReportByRoute(HttpServletRequest request,
			HttpServletResponse response) {

		List<DispatchReport> dispatchReportList = new ArrayList<DispatchReport>();
		DispatchReportList dispatchReports = new DispatchReportList();
		try {
			System.out.println("Inside get Dispatch Report");
			String billDate = request.getParameter("bill_date");
			String routeId = request.getParameter("route_id");
			String selectedCat = request.getParameter("cat_id_list");

			boolean isAllCatSelected = false;
			String selectedFr = null;

			if (selectedCat.contains("-1")) {
				isAllCatSelected = true;
			} else {
				selectedCat = selectedCat.substring(1, selectedCat.length() - 1);
				selectedCat = selectedCat.replaceAll("\"", "");
				System.out.println("selectedCat" + selectedCat.toString());
			}
			List<String> catList = new ArrayList<>();
			catList = Arrays.asList(selectedCat);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			RestTemplate restTemplate = new RestTemplate();

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
			selectedFr = strFrIdRouteWise.substring(0, strFrIdRouteWise.length() - 1);
			System.out.println("fr Id Route WISE = " + selectedFr);

			if (selectedCat.contains("-1")) {
				isAllCatSelected = true;
			}

			map = new LinkedMultiValueMap<String, Object>();

			allFrIdNameList = new AllFrIdNameList();
			try {

				allFrIdNameList = restTemplate.getForObject(Constants.url + "getAllFrIdName", AllFrIdNameList.class);

			} catch (Exception e) {
				System.out.println("Exception in getAllFrIdName" + e.getMessage());
				e.printStackTrace();

			}

			if (isAllCatSelected) {
				map = new LinkedMultiValueMap<String, Object>();

				CategoryListResponse categoryListResponse = restTemplate.getForObject(Constants.url + "showAllCategory",
						CategoryListResponse.class);
				List<MCategoryList> categoryList = categoryListResponse.getmCategoryList();
				// List<Integer> cateList=new ArrayList<>();
				StringBuilder cateList = new StringBuilder();

				for (MCategoryList mCategoryList : categoryList) {
					 //cateList.add(mCategoryList.getCatId());
					cateList = cateList.append(mCategoryList.getCatId().toString() + ",");
				}
				
				String catlist = cateList.toString();
				selectedCat = catlist.substring(0, catlist.length() - 1);
				System.out.println("cateList" + selectedCat.toString());
				System.out.println("selectedFr" + selectedFr.toString());
				System.out.println("billDate" + billDate.toString());
				

				map.add("categories", selectedCat);
				map.add("billDate", billDate);
				map.add("frId", selectedFr);

				ParameterizedTypeReference<List<DispatchReport>> typeRef = new ParameterizedTypeReference<List<DispatchReport>>() {
				};

				ResponseEntity<List<DispatchReport>> responseEntity = restTemplate.exchange(
						Constants.url + "getDispatchItemReport", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				dispatchReportList = responseEntity.getBody();
				System.out.println("dispatchReportList = " + dispatchReportList.toString());

				map = new LinkedMultiValueMap<String, Object>();
				map.add("catIdList", selectedCat);
				ParameterizedTypeReference<List<Item>> typeRef1 = new ParameterizedTypeReference<List<Item>>() {
				};

				ResponseEntity<List<Item>> responseEntity1 = restTemplate.exchange(
						Constants.url + "getItemsByCatIdForDisp", HttpMethod.POST, new HttpEntity<>(map), typeRef1);
				System.out.println("Items:" + responseEntity1.toString());
				SubCategory[] subCatList = restTemplate.getForObject(Constants.url + "getAllSubCatList",
						SubCategory[].class);

				ArrayList<SubCategory> subCatAList = new ArrayList<SubCategory>(Arrays.asList(subCatList));
				System.out.println("subCatAList:" + subCatAList.toString());
				dispatchReports.setDispatchReportList(dispatchReportList);
				dispatchReports.setFrList(frNameIdByRouteIdList);
				dispatchReports.setItemList(responseEntity1.getBody());
				dispatchReports.setSubCatList(subCatAList);

			} else {
				System.out.println("selectedCat" + selectedCat.toString());
				System.out.println("selectedFr" + selectedFr.toString());

				map.add("categories", selectedCat);
				map.add("billDate", billDate);
				map.add("frId", selectedFr);

				ParameterizedTypeReference<List<DispatchReport>> typeRef = new ParameterizedTypeReference<List<DispatchReport>>() {
				};

				ResponseEntity<List<DispatchReport>> responseEntity = restTemplate.exchange(
						Constants.url + "getDispatchItemReport", HttpMethod.POST, new HttpEntity<>(map), typeRef);
				System.out.println("Items:" + responseEntity.toString());

				dispatchReportList = responseEntity.getBody();
				System.out.println("dispatchReportList = " + dispatchReportList.toString());

				map = new LinkedMultiValueMap<String, Object>();
				map.add("catIdList", selectedCat);
				ParameterizedTypeReference<List<Item>> typeRef1 = new ParameterizedTypeReference<List<Item>>() {
				};

				ResponseEntity<List<Item>> responseEntity1 = restTemplate.exchange(
						Constants.url + "getItemsByCatIdForDisp", HttpMethod.POST, new HttpEntity<>(map), typeRef1);

				map = new LinkedMultiValueMap<String, Object>();
				map.add("catId", selectedCat);
				ParameterizedTypeReference<List<SubCategory>> typeRef2 = new ParameterizedTypeReference<List<SubCategory>>() {
				};

				ResponseEntity<List<SubCategory>> responseEntity2 = restTemplate
						.exchange(Constants.url + "getSubCatList", HttpMethod.POST, new HttpEntity<>(map), typeRef2);

				dispatchReports.setDispatchReportList(dispatchReportList);
				dispatchReports.setFrList(frNameIdByRouteIdList);
				dispatchReports.setItemList(responseEntity1.getBody());
				dispatchReports.setSubCatList(responseEntity2.getBody());
			}

		} catch (Exception e) {
			System.out.println("get Dispatch Report Exception: " + e.getMessage());
			e.printStackTrace();

		}

		return dispatchReports;

	}

	@RequestMapping(value = "pdf/getDispatchReportPdf/{billDate}/{routeId}/{selectedCat}", method = RequestMethod.GET)
	public ModelAndView getSaleReportRoyConsoByCat(@PathVariable String billDate, @PathVariable String routeId,
			@PathVariable String selectedCat, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("reports/sales/dispatchReportPdf");
		RestTemplate restTemplate = new RestTemplate();

		List<DispatchReport> dispatchReportList = new ArrayList<DispatchReport>();
		DispatchReportList dispatchReports = new DispatchReportList();
		try {
			System.out.println("Inside get Dispatch Report");
			// String billDate = request.getParameter("bill_date");
			// String routeId = request.getParameter("route_id");
			// String selectedCat=request.getParameter("cat_id_list");
			AllRoutesListResponse allRouteListResponse = restTemplate.getForObject(Constants.url + "showRouteList",
					AllRoutesListResponse.class);

			List<Route> routeList = new ArrayList<Route>();

			routeList = allRouteListResponse.getRoute();
			String routeName = "def";
			for (int i = 0; i < routeList.size(); i++) {

				if (routeList.get(i).getRouteId() == Integer.parseInt(routeId)) {
					routeName = routeList.get(i).getRouteName();
					break;

				}
			}
			boolean isAllCatSelected = false;
			String selectedFr = null;

			if (selectedCat.contains("-1")) {
				isAllCatSelected = true;
			} else {
				// selectedCat = selectedCat.substring(1, selectedCat.length() - 1);
				// selectedCat = selectedCat.replaceAll("\"", "");
				// System.out.println("selectedCat"+selectedCat.toString());
			}
			List<String> catList = new ArrayList<>();
			catList = Arrays.asList(selectedCat);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

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
			selectedFr = strFrIdRouteWise.substring(0, strFrIdRouteWise.length() - 1);
			System.out.println("fr Id Route WISE = " + selectedFr);

			if (selectedCat.contains("-1")) {
				isAllCatSelected = true;
			}

			map = new LinkedMultiValueMap<String, Object>();

			allFrIdNameList = new AllFrIdNameList();
			try {

				allFrIdNameList = restTemplate.getForObject(Constants.url + "getAllFrIdName", AllFrIdNameList.class);

			} catch (Exception e) {
				System.out.println("Exception in getAllFrIdName" + e.getMessage());
				e.printStackTrace();

			}

			if (isAllCatSelected) {
				map = new LinkedMultiValueMap<String, Object>();

				CategoryListResponse categoryListResponse = restTemplate.getForObject(Constants.url + "showAllCategory",
						CategoryListResponse.class);
				List<MCategoryList> categoryList = categoryListResponse.getmCategoryList();
				
				StringBuilder cateList = new StringBuilder();
				//List<String> cateList = new ArrayList<>();
				for (MCategoryList mCategoryList : categoryList) {
					cateList = cateList.append(mCategoryList.getCatId().toString() + ",");
					//cateList.add("" + mCategoryList.getCatId());
				}
				System.err.println(cateList);
				String catlist = cateList.toString();
				selectedCat = catlist.substring(0, catlist.length() - 1);
				map.add("categories",selectedCat);
				map.add("billDate", billDate);
				map.add("frId", selectedFr);

				ParameterizedTypeReference<List<DispatchReport>> typeRef = new ParameterizedTypeReference<List<DispatchReport>>() {
				};

				ResponseEntity<List<DispatchReport>> responseEntity = restTemplate.exchange(
						Constants.url + "getDispatchItemReport", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				dispatchReportList = responseEntity.getBody();
				System.out.println("dispatchReportList = " + dispatchReportList.toString());

				map = new LinkedMultiValueMap<String, Object>();
				map.add("catIdList", selectedCat);
				ParameterizedTypeReference<List<Item>> typeRef1 = new ParameterizedTypeReference<List<Item>>() {
				};

				ResponseEntity<List<Item>> responseEntity1 = restTemplate.exchange(
						Constants.url + "getItemsByCatIdForDisp", HttpMethod.POST, new HttpEntity<>(map), typeRef1);

				SubCategory[] subCatList = restTemplate.getForObject(Constants.url + "getAllSubCatList",
						SubCategory[].class);

				ArrayList<SubCategory> subCatAList = new ArrayList<SubCategory>(Arrays.asList(subCatList));

				/*
				 * if(!dispatchReportList.isEmpty()&&!responseEntity1.getBody().isEmpty()&&!
				 * frNameIdByRouteIdList.isEmpty()) { for(int
				 * j=0;j<responseEntity1.getBody().size();j++) { for(int
				 * i=0;i<frNameIdByRouteIdList.size();i++) { boolean flag=false; for(int
				 * k=0;k<dispatchReportList.size();k++) {
				 * if(dispatchReportList.get(k).getFrId()==frNameIdByRouteIdList.get(i).getFrId(
				 * ) &&
				 * dispatchReportList.get(k).getItemId()==responseEntity1.getBody().get(j).getId
				 * ()) { flag=true; break;
				 * 
				 * } } if(flag==false) { DispatchReport dispachReport=new DispatchReport();
				 * dispachReport.setBillDetailNo(0);
				 * dispachReport.setFrId(frNameIdByRouteIdList.get(i).getFrId());
				 * dispachReport.setItemId(responseEntity1.getBody().get(j).getId());
				 * dispachReport.setBillQty(0); dispatchReportList.add(dispachReport); } } } }
				 */
				model.addObject("dispatchReportList", dispatchReportList);
				model.addObject("frList", frNameIdByRouteIdList);
				model.addObject("itemList", responseEntity1.getBody());
				model.addObject("subCatList", subCatAList);

			} else {
				System.out.println("selectedCat" + selectedCat.toString());
				System.out.println("selectedFr" + selectedFr.toString());

				map.add("categories", selectedCat);
				map.add("billDate", billDate);
				map.add("frId", selectedFr);

				ParameterizedTypeReference<List<DispatchReport>> typeRef = new ParameterizedTypeReference<List<DispatchReport>>() {
				};

				ResponseEntity<List<DispatchReport>> responseEntity = restTemplate.exchange(
						Constants.url + "getDispatchItemReport", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				dispatchReportList = responseEntity.getBody();
				System.out.println("dispatchReportList = " + dispatchReportList.toString());

				map = new LinkedMultiValueMap<String, Object>();
				map.add("catIdList", selectedCat);
				ParameterizedTypeReference<List<Item>> typeRef1 = new ParameterizedTypeReference<List<Item>>() {
				};

				ResponseEntity<List<Item>> responseEntity1 = restTemplate.exchange(
						Constants.url + "getItemsByCatIdForDisp", HttpMethod.POST, new HttpEntity<>(map), typeRef1);

				map = new LinkedMultiValueMap<String, Object>();
				map.add("catId", selectedCat);
				ParameterizedTypeReference<List<SubCategory>> typeRef2 = new ParameterizedTypeReference<List<SubCategory>>() {
				};

				ResponseEntity<List<SubCategory>> responseEntity2 = restTemplate
						.exchange(Constants.url + "getSubCatList", HttpMethod.POST, new HttpEntity<>(map), typeRef2);

				/*
				 * if(!dispatchReportList.isEmpty()&&!responseEntity1.getBody().isEmpty()&&!
				 * frNameIdByRouteIdList.isEmpty()) { for(int
				 * j=0;j<responseEntity1.getBody().size();j++) { for(int
				 * i=0;i<frNameIdByRouteIdList.size();i++) { boolean flag=false; for(int
				 * k=0;k<dispatchReportList.size();k++) {
				 * if(dispatchReportList.get(k).getFrId()==frNameIdByRouteIdList.get(i).getFrId(
				 * ) &&
				 * dispatchReportList.get(k).getItemId()==responseEntity1.getBody().get(j).getId
				 * ()) { flag=true; break;
				 * 
				 * } } if(flag==false) { DispatchReport dispachReport=new DispatchReport();
				 * dispachReport.setBillDetailNo(0);
				 * dispachReport.setFrId(frNameIdByRouteIdList.get(i).getFrId());
				 * dispachReport.setItemId(responseEntity1.getBody().get(j).getId());
				 * dispachReport.setBillQty(0); dispatchReportList.add(dispachReport); } } } }
				 */

			

				model.addObject("dispatchReportList", dispatchReportList);
				model.addObject("frList", frNameIdByRouteIdList);
				model.addObject("itemList", responseEntity1.getBody());
				model.addObject("subCatList", responseEntity2.getBody());
			}
			model.addObject("routeName", routeName);
		} catch (Exception e) {
			System.out.println("get Dispatch Report Exception: " + e.getMessage());
			e.printStackTrace();

		}
		return model;

	}

	// --------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/getSaleReportRoyConsoByCat", method = RequestMethod.GET)
	public @ResponseBody RoyaltyListBean getSaleReportRoyConsoByCat(HttpServletRequest request,
			HttpServletResponse response) {

		List<SalesReportBillwise> saleList = new ArrayList<>();
		List<SalesReportRoyalty> royaltyList = new ArrayList<>();
		RoyaltyListBean royaltyBean = new RoyaltyListBean();

		try {
			System.out.println("Inside get Sale Bill Wise");
			String selectedFr = request.getParameter("fr_id_list");
			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
			String routeId = request.getParameter("route_id");

			int isGraph = Integer.parseInt(request.getParameter("is_graph"));

			String selectedCat = request.getParameter("cat_id_list");

			boolean isAllFrSelected = false;
			boolean isAllCatSelected = false;
			selectedFr = selectedFr.substring(1, selectedFr.length() - 1);
			selectedFr = selectedFr.replaceAll("\"", "");

			selectedCat = selectedCat.substring(1, selectedCat.length() - 1);
			selectedCat = selectedCat.replaceAll("\"", "");

			List<String> catList = new ArrayList<>();
			catList = Arrays.asList(selectedCat);
			frList = new ArrayList<>();
			frList = Arrays.asList(selectedFr);

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

			if (frList.contains("-1")) {
				isAllFrSelected = true;
			}

			if (catList.contains("-1")) {
				isAllCatSelected = true;
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();
			
			
			
			// new code
			
			if (isAllFrSelected) {

				System.out.println("Inside If all fr Selected ");
				if(isAllCatSelected) {
					map.add("catIdList", 0);
				}else {
					map.add("catIdList",selectedCat);
				}
				
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);
				
				
				if (isGraph == 0) {
					ParameterizedTypeReference<List<SalesReportRoyalty>> typeRef = new ParameterizedTypeReference<List<SalesReportRoyalty>>() {
					};

					ResponseEntity<List<SalesReportRoyalty>> responseEntity = restTemplate.exchange(
							Constants.url + "getSaleReportRoyConsoByCatAllFr", HttpMethod.POST, new HttpEntity<>(map),
							typeRef);

					
					royaltyList = responseEntity.getBody();
					royaltyListForPdf = new ArrayList<>();

					royaltyListForPdf = royaltyList;
				}

				/*if (isGraph == 1) {
					ParameterizedTypeReference<List<SalesReportRoyalty>> typeRef = new ParameterizedTypeReference<List<SalesReportRoyalty>>() {
					};

					ResponseEntity<List<SalesReportRoyalty>> responseEntity = restTemplate.exchange(
							Constants.url + "getSaleReportRoyConsoByCatForGraph", HttpMethod.POST,
							new HttpEntity<>(map), typeRef);

					royaltyList = responseEntity.getBody();
					royaltyListForPdf = new ArrayList<>();

					royaltyListForPdf = royaltyList;
				}
*/
				

				}//end of if all fr Selected 
			else {
				
				//few fr Selected 
				
				if(isAllCatSelected) {
					map.add("catIdList", 0);
				}else {
					map.add("catIdList",selectedCat);
				}
				
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);
				
				map.add("frIdList", selectedFr);
				
				if (isGraph == 0) {
					ParameterizedTypeReference<List<SalesReportRoyalty>> typeRef = new ParameterizedTypeReference<List<SalesReportRoyalty>>() {
					};

					ResponseEntity<List<SalesReportRoyalty>> responseEntity = restTemplate.exchange(
							Constants.url + "getSaleReportRoyConsoByCat", HttpMethod.POST, new HttpEntity<>(map),
							typeRef);

					royaltyList = responseEntity.getBody();
					royaltyListForPdf = new ArrayList<>();

					royaltyListForPdf = royaltyList;
				}

				/*if (isGraph == 1) {
					ParameterizedTypeReference<List<SalesReportRoyalty>> typeRef = new ParameterizedTypeReference<List<SalesReportRoyalty>>() {
					};

					ResponseEntity<List<SalesReportRoyalty>> responseEntity = restTemplate.exchange(
							Constants.url + "getSaleReportRoyConsoByCatForGraph", HttpMethod.POST,
							new HttpEntity<>(map), typeRef);

					royaltyList = responseEntity.getBody();
					royaltyListForPdf = new ArrayList<>();

					royaltyListForPdf = royaltyList;
				}
*/
				
			}//end of else
			
			
			
			
			
			//new code

			/*if (isAllCatSelected) {

				System.out.println("Inside If all fr Selected ");
				
				map.add("catIdList", 0);

				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

			} else {

				map.add("catIdList", selectedCat);
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

			}

			if (isAllFrSelected) {

				System.out.println("Inside If all fr Selected ");
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				// getSaleReportRoyConsoByCatAllFr

			} else {
				System.out.println("Inside else Few fr Selected ");
				// map.add("catIdList", selectedCat);
				map.add("frIdList", selectedFr);
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);
				if (isGraph == 0) {
					ParameterizedTypeReference<List<SalesReportRoyalty>> typeRef = new ParameterizedTypeReference<List<SalesReportRoyalty>>() {
					};

					ResponseEntity<List<SalesReportRoyalty>> responseEntity = restTemplate.exchange(
							Constants.url + "getSaleReportRoyConsoByCat", HttpMethod.POST, new HttpEntity<>(map),
							typeRef);

					royaltyList = responseEntity.getBody();
					royaltyListForPdf = new ArrayList<>();

					royaltyListForPdf = royaltyList;
				}

				if (isGraph == 1) {
					ParameterizedTypeReference<List<SalesReportRoyalty>> typeRef = new ParameterizedTypeReference<List<SalesReportRoyalty>>() {
					};

					ResponseEntity<List<SalesReportRoyalty>> responseEntity = restTemplate.exchange(
							Constants.url + "getSaleReportRoyConsoByCatForGraph", HttpMethod.POST,
							new HttpEntity<>(map), typeRef);

					royaltyList = responseEntity.getBody();
					royaltyListForPdf = new ArrayList<>();

					royaltyListForPdf = royaltyList;
				}*/

				System.out.println("royaltyList List Bill Wise " + royaltyList.toString());

				CategoryListResponse categoryListResponse = restTemplate.getForObject(Constants.url + "showAllCategory",
						CategoryListResponse.class);
				List<MCategoryList> categoryList;

				categoryList = categoryListResponse.getmCategoryList();
				// allFrIdNameList = new AllFrIdNameList();
				System.out.println("Category list  " + categoryList);
				List<MCategoryList> tempList = new ArrayList<>();

				// royaltyBean.setCategoryList(categoryList);
				Map<Integer, String> catNameId = new HashMap<Integer, String>();

				for (int i = 0; i < categoryList.size(); i++) {

					for (int j = 0; j < royaltyList.size(); j++) {

						if (categoryList.get(i).getCatId() == royaltyList.get(j).getCatId()) {
							catNameId.put(categoryList.get(i).getCatId(), categoryList.get(i).getCatName());

							if (!tempList.contains(categoryList.get(i))) {

								tempList.add(categoryList.get(i));

							}
						}

					}

				

				System.out.println("temp list " + tempList.toString() + "size of t List " + tempList.size());
				royaltyBean.setCategoryList(tempList);
				royaltyBean.setSalesReportRoyalty(royaltyList);
				staticRoyaltyBean = royaltyBean;
			}
		} catch (Exception e) {
			System.out.println("get sale Report royaltyList by cat " + e.getMessage());
			e.printStackTrace();

		}
		// exportToExcel
		List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

		ExportToExcel expoExcel = new ExportToExcel();
		List<String> rowData = new ArrayList<String>();

		rowData.add("Sr.No.");
		rowData.add("Category Name");
		rowData.add("Item Name");
		rowData.add("Sale Qty");
		rowData.add("Sale Value");

		rowData.add("GRN Qty");
		rowData.add("GRN Value");
		rowData.add("GVN Qty");
		rowData.add("GVN Value");

		rowData.add("Net Qty");
		rowData.add("Net Value");

		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);
		if (!royaltyBean.getSalesReportRoyalty().isEmpty()) {
			for (int i = 0; i < royaltyList.size(); i++) {
				int index = 1;
				index = index + i;
				expoExcel = new ExportToExcel();
				rowData = new ArrayList<String>();

				rowData.add("" + index);
				rowData.add("" + royaltyList.get(i).getCat_name());

				rowData.add("" + royaltyList.get(i).getItem_name());

				rowData.add("" + roundUp(royaltyList.get(i).gettBillQty()));
				rowData.add("" + roundUp(royaltyList.get(i).gettBillTaxableAmt()));

				rowData.add("" + roundUp(royaltyList.get(i).gettGrnQty()));

				rowData.add("" + roundUp(royaltyList.get(i).gettGrnTaxableAmt()));
				rowData.add("" + roundUp(royaltyList.get(i).gettGvnQty()));
				rowData.add("" + roundUp(royaltyList.get(i).gettGvnTaxableAmt()));

				float netQty = royaltyList.get(i).gettBillQty()
						- (royaltyList.get(i).gettGrnQty() + royaltyList.get(i).gettGvnQty());

				float netValue = royaltyList.get(i).gettBillTaxableAmt()
						- (royaltyList.get(i).gettGrnTaxableAmt() + royaltyList.get(i).gettGvnTaxableAmt());
				float royPer=getRoyPer();

				float rAmt = netValue * royPer / 100;

				rowData.add("" + roundUp(netQty));
				rowData.add("" + roundUp(netValue));
				/*
				 * rowData.add(""+roundUp(royPer)); rowData.add(""+roundUp(rAmt));
				 */

				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);

			}
		}

		HttpSession session = request.getSession();
		session.setAttribute("exportExcelList", exportToExcelList);
		session.setAttribute("excelName", "RoyaltyConsolidatedCatList");

		return royaltyBean;

	}

	@RequestMapping(value = "pdf/getSaleReportRoyConsoByCatPdf/{fromDate}/{toDate}/{selectedFr}/{routeId}/{selectedCat}", method = RequestMethod.GET)
	public ModelAndView getSaleReportRoyConsoByCat(@PathVariable String fromDate, @PathVariable String toDate,
			@PathVariable String selectedFr, @PathVariable String routeId, @PathVariable String selectedCat,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("reports/sales/pdf/salesconsbycatPdf");

		List<SalesReportBillwise> saleList = new ArrayList<>();
		List<SalesReportRoyalty> royaltyList = new ArrayList<>();
		RoyaltyListBean royaltyBean = new RoyaltyListBean();
		boolean isAllFrSelected = false;
		boolean isAllCatSelected = false;
		try {
			System.out.println("Inside get Sale Bill Wise");
			/*
			 * 
			 * 
			 * selectedFr = selectedFr.substring(1, selectedFr.length() - 1); selectedFr =
			 * selectedFr.replaceAll("\"", "");
			 * 
			 * selectedCat = selectedCat.substring(1, selectedCat.length() - 1); selectedCat
			 * = selectedCat.replaceAll("\"", "");
			 * 
			 * List<String> catList=new ArrayList<>(); catList=Arrays.asList(selectedCat);
			 * frList = new ArrayList<>(); frList = Arrays.asList(selectedFr);
			 */

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

			if (selectedFr.contains("-1")) {
				isAllFrSelected = true;
			}

			if (selectedCat.contains("-1")) {
				isAllCatSelected = true;
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			if (isAllFrSelected) {

				System.out.println("Inside If all fr Selected ");
				if(isAllCatSelected) {
					map.add("catIdList", 0);
				}else {
					map.add("catIdList",selectedCat);
				}
				
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);
				
				
			
					ParameterizedTypeReference<List<SalesReportRoyalty>> typeRef = new ParameterizedTypeReference<List<SalesReportRoyalty>>() {
					};

					ResponseEntity<List<SalesReportRoyalty>> responseEntity = restTemplate.exchange(
							Constants.url + "getSaleReportRoyConsoByCatAllFr", HttpMethod.POST, new HttpEntity<>(map),
							typeRef);

					royaltyList = responseEntity.getBody();
					royaltyListForPdf = new ArrayList<>();

					royaltyListForPdf = royaltyList;
			

				

				

				}//end of if all fr Selected 
			else {
				
				//few fr Selected 
				
				if(isAllCatSelected) {
					map.add("catIdList", 0);
				}else {
					map.add("catIdList",selectedCat);
				}
				
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);
				
				map.add("frIdList", selectedFr);
				
			
					ParameterizedTypeReference<List<SalesReportRoyalty>> typeRef = new ParameterizedTypeReference<List<SalesReportRoyalty>>() {
					};

					ResponseEntity<List<SalesReportRoyalty>> responseEntity = restTemplate.exchange(
							Constants.url + "getSaleReportRoyConsoByCat", HttpMethod.POST, new HttpEntity<>(map),
							typeRef);

					royaltyList = responseEntity.getBody();
					royaltyListForPdf = new ArrayList<>();

					royaltyListForPdf = royaltyList;
			
					
				

				
			}//end of else

				System.out.println("royaltyList List Bill Wise " + royaltyList.toString());

				CategoryListResponse categoryListResponse = restTemplate.getForObject(Constants.url + "showAllCategory",
						CategoryListResponse.class);
				List<MCategoryList> categoryList;

				categoryList = categoryListResponse.getmCategoryList();
				// allFrIdNameList = new AllFrIdNameList();
				System.out.println("Category list  " + categoryList);
				List<MCategoryList> tempList = new ArrayList<>();

				// royaltyBean.setCategoryList(categoryList);
				Map<Integer, String> catNameId = new HashMap<Integer, String>();

				for (int i = 0; i < categoryList.size(); i++) {

					for (int j = 0; j < royaltyList.size(); j++) {

						if (categoryList.get(i).getCatId() == royaltyList.get(j).getCatId()) {
							catNameId.put(categoryList.get(i).getCatId(), categoryList.get(i).getCatName());

							if (!tempList.contains(categoryList.get(i))) {

								tempList.add(categoryList.get(i));

							}
						}

					}

				//}

				System.out.println("temp list " + tempList.toString() + "size of t List " + tempList.size());
				royaltyBean.setCategoryList(tempList);
				royaltyBean.setSalesReportRoyalty(royaltyList);
				model.addObject("royaltyList", royaltyBean);
				model.addObject("fromDate", fromDate);
				model.addObject("toDate", toDate);
				
				model.addObject("royPer",getRoyPer());


			}
		} catch (Exception e) {
			System.out.println("get sale Report royaltyList by cat " + e.getMessage());
			e.printStackTrace();

		}

		return model;

	}

	// pdf function

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

	@RequestMapping(value = "/pdfForReport", method = RequestMethod.GET)
	public void showPDF(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Inside PDf For Report URL ");
		String url = request.getParameter("url");
		System.out.println("URL " + url);

			File f = new File("/opt/tomcat-latest/webapps/uploads/report.pdf");
		// File f = new File("/opt/tomcat-latest/webapps/uploads/report.pdf");

		try {
			runConverter(Constants.ReportURL + url, f, request, response);
			// runConverter("www.google.com", f,request,response);

		} catch (IOException e) {

			System.out.println("Pdf conversion exception " + e.getMessage());
		}

		// get absolute path of the application
		ServletContext context = request.getSession().getServletContext();
		String appPath = context.getRealPath("");
		 String filePath = "/opt/tomcat-latest/webapps/uploads/report.pdf";

	// String filePath = "/opt/tomcat-latest/webapps/uploads/report.pdf";

		// construct the complete absolute path of the file
		String fullPath = appPath + filePath;
		File downloadFile = new File(filePath);
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(downloadFile);
		} catch (FileNotFoundException e) {
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

			OutputStream outStream;

			outStream = response.getOutputStream();

			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;

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

				Dimension landscapeA4 = pd4ml.changePageOrientation(PD4Constants.A4);
				pd4ml.setPageSize(landscapeA4);
				
				PD4PageMark footer = new PD4PageMark();

				footer.setPageNumberTemplate("Page $[page] of $[total]");
				footer.setPageNumberAlignment(PD4PageMark.RIGHT_ALIGN);
				footer.setFontSize(10);
				footer.setAreaHeight(20);

				pd4ml.setPageFooter(footer);

			} catch (Exception e) {
				System.out.println("Pdf conversion method excep " + e.getMessage());
			}

			if (unitsValue.equals("mm")) {
				pd4ml.setPageInsetsMM(new Insets(topValue, leftValue, bottomValue, rightValue));
			} else {
				pd4ml.setPageInsets(new Insets(topValue, leftValue, bottomValue, rightValue));
			}

			pd4ml.setHtmlWidth(userSpaceWidth);

			pd4ml.render(urlstring, fos);
		}
	}
	@RequestMapping(value = "/pdfForDisReport", method = RequestMethod.GET)
	public void showPDF1(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Inside PDf For Report URL ");
		String url = request.getParameter("url");
		System.out.println("URL " + url);

		File f = new File("/opt/tomcat-latest/webapps/uploads/report.pdf");
		//File f = new File("/opt/tomcat-latest/webapps/uploads/report.pdf");

		try {
			runConverter1(Constants.ReportURL + url, f, request, response);
			// runConverter("www.google.com", f,request,response);

		} catch (IOException e) {

			System.out.println("Pdf conversion exception " + e.getMessage());
		}

		// get absolute path of the application
		ServletContext context = request.getSession().getServletContext();
		String appPath = context.getRealPath("");
		String filePath = "/opt/tomcat-latest/webapps/uploads/report.pdf";

	// String filePath = "/opt/tomcat-latest/webapps/uploads/report.pdf";

		// construct the complete absolute path of the file
		String fullPath = appPath + filePath;
		File downloadFile = new File(filePath);
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(downloadFile);
		} catch (FileNotFoundException e) {
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

			OutputStream outStream;

			outStream = response.getOutputStream();

			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;

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

	private void runConverter1(String urlstring, File output, HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		if (urlstring.length() > 0) {
			if (!urlstring.startsWith("http://") && !urlstring.startsWith("file:")) {
				urlstring = "http://" + urlstring;
			}
			System.out.println("PDF URL " + urlstring);
			java.io.FileOutputStream fos = new java.io.FileOutputStream(output);

			PD4ML pd4ml = new PD4ML();

			try {

				  try {                                                              
                      pd4ml.setPageSize( landscapeValue ? pd4ml.changePageOrientation( format ): format );
                   } catch (Exception e) {
                      e.printStackTrace();
                   }
                     
				PD4PageMark footer = new PD4PageMark();

				footer.setPageNumberTemplate("Page $[page] of $[total]");
				footer.setPageNumberAlignment(PD4PageMark.RIGHT_ALIGN);
				footer.setFontSize(10);
				footer.setAreaHeight(20);

				pd4ml.setPageFooter(footer);

			} catch (Exception e) {
				System.out.println("Pdf conversion method excep " + e.getMessage());
			}

			if (unitsValue.equals("mm")) {
				pd4ml.setPageInsetsMM(new Insets(topValue, leftValue, bottomValue, rightValue));
			} else {
				pd4ml.setPageInsets(new Insets(topValue, leftValue, bottomValue, rightValue));
			}

			pd4ml.setHtmlWidth(userSpaceWidth);

			pd4ml.render(urlstring, fos);
		}
	}
	
}
