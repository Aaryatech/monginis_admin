package com.ats.adminpanel.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.ats.adminpanel.commons.DateConvertor;
import com.ats.adminpanel.model.AllFrIdName;
import com.ats.adminpanel.model.AllFrIdNameList;
import com.ats.adminpanel.model.AllRoutesListResponse;
import com.ats.adminpanel.model.ExportToExcel;
import com.ats.adminpanel.model.GetNonRegFr;
import com.ats.adminpanel.model.NonRegFrReports;
import com.ats.adminpanel.model.Route;
import com.ats.adminpanel.model.franchisee.AllMenuResponse;
import com.ats.adminpanel.model.franchisee.Menu;
import com.ats.adminpanel.model.salesreport.SpCakeDispatchReport;
@Controller
@Scope("session")
public class NonRegFrReportController {
	AllFrIdNameList	allFrIdNameList = new AllFrIdNameList();
	
	public static float roundUp(float d) {
		return BigDecimal.valueOf(d).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
	}
	String todaysDate;
	
	@RequestMapping(value = "/getNonRegFrReports", method = RequestMethod.GET)
	public ModelAndView getNonRegFrReports(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/nonRegFr");

		try {
			ZoneId z = ZoneId.of("Asia/Calcutta");

			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			todaysDate = date.format(formatters);
			model.addObject("todaysDate", todaysDate);

		} catch (Exception e) {

			System.out.println("Exc in show sales report bill wise  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}
	
	
	
	
	@RequestMapping(value = "/getNonRegFr", method = RequestMethod.GET)
	public @ResponseBody List<GetNonRegFr> getNonRegFr(HttpServletRequest request, HttpServletResponse response) {

		List<GetNonRegFr> list = new ArrayList<GetNonRegFr>();
		
		try {
			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
			
			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			
			map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			map.add("toDate", DateConvertor.convertToYMD(toDate));
			
			GetNonRegFr[] nonGenFr = restTemplate.postForObject(Constants.url + "getNonRegFrApi", map,
					GetNonRegFr[].class);
			list = new ArrayList<GetNonRegFr>(Arrays.asList(nonGenFr));
			
			
			// exportToExcel
			List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();
			float grandTotal = 0;
			float ttlTaxable = 0;
			float ttlTax = 0;
			ExportToExcel expoExcel = new ExportToExcel();
			List<String> rowData = new ArrayList<String>();
			rowData.add("Sr No.");
			rowData.add("Party Name");
			rowData.add("Taxable Amt");
			rowData.add("Total Tax");
			rowData.add("Grand Total");

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);
			int index = 0;
			for (int i = 0; i < list.size(); i++) {
				expoExcel = new ExportToExcel();
				index = index + 1;
				rowData = new ArrayList<String>();

				rowData.add("" + index);
				rowData.add("" + list.get(i).getFrName()+"-"+list.get(i).getFrCode());
				rowData.add("" + list.get(i).getTaxableAmt());
				rowData.add("" + list.get(i).getTotalTax());
				rowData.add("" + list.get(i).getGrandTotal());
				
				grandTotal = grandTotal+ Float.parseFloat(list.get(i).getGrandTotal());
				ttlTaxable = ttlTaxable+Float.parseFloat(list.get(i).getTaxableAmt());
				ttlTax = ttlTax+Float.parseFloat(list.get(i).getTotalTax());
				
				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);

			}

			expoExcel = new ExportToExcel();

			rowData = new ArrayList<String>();
			rowData.add("");
			rowData.add("Total");
			rowData.add("" + ttlTaxable);
			rowData.add("" + ttlTax);
			rowData.add("" + grandTotal);

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);

			HttpSession session = request.getSession();
			session.setAttribute("exportExcelList", exportToExcelList);
			session.setAttribute("excelName", "Non Generated Register Franchise");
		} catch (Exception e) {

			System.out.println("Exc in getNonRegFr  " + e.getMessage());
			e.printStackTrace();
		}

		return list;

	}
	
	
	@RequestMapping(value = "pdf/getNonRegFrPdf/{fromDate}/{toDate}", method = RequestMethod.GET)
	public ModelAndView getNonRegFrPdf(HttpServletRequest request, HttpServletResponse response, @PathVariable String fromDate, @PathVariable String toDate) {
		
		ModelAndView model = new ModelAndView("reports/sales/pdf/nonRegFrPdf");
		List<GetNonRegFr> list = new ArrayList<GetNonRegFr>();
		
		try {			
			
			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			
			map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			map.add("toDate", DateConvertor.convertToYMD(toDate));
			
			GetNonRegFr[] nonGenFr = restTemplate.postForObject(Constants.url + "getNonRegFrApi", map,
					GetNonRegFr[].class);
			list = new ArrayList<GetNonRegFr>(Arrays.asList(nonGenFr));
			model.addObject("list", list);
			
			model.addObject("fromDate", fromDate);
			model.addObject("toDate", toDate);
		} catch (Exception e) {

			System.out.println("Exc in getNonRegFr  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}
	


	@RequestMapping(value = "/getSpCakeDispatchReport", method = RequestMethod.GET)
	public ModelAndView showSaleReportByDate(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/spCakeDispatchReport");

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
			
			model.addObject("todaysDate", todaysDate);

			model.addObject("routeList", routeList);

		} catch (Exception e) {

			System.out.println("Exc in getSpCakeDispatchReport  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}
	
	@RequestMapping(value = "/getSpecialCakeDispatchDtl", method = RequestMethod.GET)
	public @ResponseBody List<SpCakeDispatchReport> getSpecialCakeDispatchDtl(HttpServletRequest request, HttpServletResponse response) {

		List<SpCakeDispatchReport> list = new ArrayList<SpCakeDispatchReport>();
		
		try {
			String fromDate = request.getParameter("fromDate");
			int routId = Integer.parseInt(request.getParameter("routeId"));
			
			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			
			map.add("spCakeDelDate", DateConvertor.convertToYMD(fromDate));
			map.add("routId", routId);
			
			SpCakeDispatchReport[] nonGenFr = restTemplate.postForObject(Constants.url + "getSpecialCakeDispatchReports", map,
					SpCakeDispatchReport[].class);
			list = new ArrayList<SpCakeDispatchReport>(Arrays.asList(nonGenFr));
			
			
			// exportToExcel
			List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();
			
			ExportToExcel expoExcel = new ExportToExcel();
			List<String> rowData = new ArrayList<String>();
			rowData.add("Sr No.");
			rowData.add("Party Name");
			rowData.add("Sp Cake Sr.No.");
			rowData.add("Delivery Place");

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);
			int index = 0;
			for (int i = 0; i < list.size(); i++) {
				expoExcel = new ExportToExcel();
				index = index + 1;
				rowData = new ArrayList<String>();

				rowData.add("" + index);
				rowData.add("" + list.get(i).getFrName()+"-"+list.get(i).getFrCode());
				rowData.add("" + list.get(i).getSrNo());
				rowData.add("" + list.get(i).getSpDeliveryPlace());				
				
				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);

			}			

			HttpSession session = request.getSession();
			session.setAttribute("exportExcelList", exportToExcelList);
			session.setAttribute("excelName", "Special Cake Dispatch");
		} catch (Exception e) {

			System.out.println("Exc in getSpecialCakeDispatchDtl  " + e.getMessage());
			e.printStackTrace();
		}

		return list;

	}
	
	@RequestMapping(value = "pdf/getSpCakeDispatchPdf/{fromDate}/{routeId}", method = RequestMethod.GET)
	public ModelAndView getSpCakeDispatchPdf(HttpServletRequest request, HttpServletResponse response, @PathVariable String fromDate,  @PathVariable int routeId) {
		
		ModelAndView model = new ModelAndView("reports/sales/pdf/spCakeDispPdf");
		List<SpCakeDispatchReport> list = new ArrayList<SpCakeDispatchReport>();
		
		try {			
			
			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			
			map.add("spCakeDelDate", DateConvertor.convertToYMD(fromDate));
			map.add("routId", routeId);
			
			SpCakeDispatchReport[] nonGenFr = restTemplate.postForObject(Constants.url + "getSpecialCakeDispatchReports", map,
					SpCakeDispatchReport[].class);
			list = new ArrayList<SpCakeDispatchReport>(Arrays.asList(nonGenFr));
			model.addObject("list", list);
			
			model.addObject("fromDate", fromDate);
		} catch (Exception e) {

			System.out.println("Exc in getSpCakeDispatchPdf  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	
}
