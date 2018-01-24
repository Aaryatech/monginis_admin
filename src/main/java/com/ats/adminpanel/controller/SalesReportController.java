package com.ats.adminpanel.controller;

import java.awt.Dimension;
import java.awt.Insets;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
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

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.model.AllFrIdName;
import com.ats.adminpanel.model.AllFrIdNameList;
import com.ats.adminpanel.model.AllRoutesListResponse;
import com.ats.adminpanel.model.Route;
import com.ats.adminpanel.model.franchisee.FrNameIdByRouteId;
import com.ats.adminpanel.model.franchisee.FrNameIdByRouteIdResponse;
import com.ats.adminpanel.model.franchisee.Menu;
import com.ats.adminpanel.model.item.CategoryListResponse;
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
	static  List<SalesReportBillwise> saleListForPdf ;//it is Static 
	static String todaysDate;
	static List<SalesReportRoyalty> royaltyListForPdf;
	
	static List<SalesReportBillwiseAllFr> staticSaleByAllFr;

	List<SalesReportRoyaltyFr> royaltyFrList;
	
	static List<SalesReportRoyaltyFr> staticRoyaltyFrList;

	static List<SalesReportItemwise> staticSaleListItemWise;
	
	static RoyaltyListBean staticRoyaltyBean=new RoyaltyListBean();
	
	

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

			} else {
				System.out.println("Inside else Few fr Selected ");
				
				map.add("frIdList", selectedFr);
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				ParameterizedTypeReference<List<SalesReportBillwise>> typeRef = new ParameterizedTypeReference<List<SalesReportBillwise>>() {
				};
				ResponseEntity<List<SalesReportBillwise>> responseEntity = restTemplate
						.exchange(Constants.url + "getSaleReportBillwise", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				saleList = responseEntity.getBody();
				saleListForPdf=new ArrayList<>();
				saleListForPdf=saleList;
				System.out.println("sales List Bill Wise "+saleList.toString());

			}
		} catch (Exception e) {
			System.out.println("get sale Report Bill Wise "+e.getMessage());
			e.printStackTrace();

		}
		return saleList;
	}
	
	
	@RequestMapping(value = "/showSaleReportByDatePdf/{fDate}/{tDate}", method = RequestMethod.GET)
	public ModelAndView showSaleReportByDatePdf(@PathVariable String fDate,@PathVariable String tDate,HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/pdf/billwisesalesbydatePdf");

		System.out.println("inside PDf data "+saleListForPdf.toString());
		
		model.addObject("fromDate",fDate);
		
		model.addObject("toDate",tDate);
		
		model.addObject("report",saleListForPdf);
		
		return model;
	}
	
	//report 2
	@RequestMapping(value = "/showSaleReportByFr", method = RequestMethod.GET)
	public ModelAndView showSaleReportByFr(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/billwisesalebyfr");
		System.out.println("inside showSaleReportByFr ");
		// Constants.mainAct =2;
		// Constants.subAct =20;
		HttpSession session=request.getSession();
		
		System.out.println("session Id in show Page  "+session.getId());
		
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

			} else {
				System.out.println("Inside else Few fr Selected ");
				
				map.add("frIdList", selectedFr);
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				ParameterizedTypeReference<List<SalesReportBillwise>> typeRef = new ParameterizedTypeReference<List<SalesReportBillwise>>() {
				};
				ResponseEntity<List<SalesReportBillwise>> responseEntity = restTemplate
						.exchange(Constants.url + "getSaleReportBillwiseByFr", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				saleList = responseEntity.getBody();
				saleListForPdf=new ArrayList<>();
				String name="Sachin";
				
				HttpSession session=request.getSession();
				
				System.out.println("session Id  In Ajax Call "+session.getId());
				session.setAttribute("pdfData", saleList);
				
				session.setAttribute("name", name);
				saleListForPdf=saleList;
				
				System.out.println("sales List Bill Wise "+saleList.toString());

			}
		} catch (Exception e) {
			System.out.println("get sale Report Bill Wise "+e.getMessage());
			e.printStackTrace();

		}
		return saleList;
	}
	
	@RequestMapping(value = "/showSaleBillwiseByFrPdf/{fDate}/{tDate}", method = RequestMethod.GET)
	public ModelAndView showSaleBillwiseByFrPdf(@PathVariable String fDate,@PathVariable String tDate,HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/pdf/billwisesalebyfrPdf");
		try {

			
			HttpSession session=request.getSession();
			List<SalesReportBillwise> saleList=new ArrayList<>();
			
			//session.setAttribute("pdfData", saleList);
			 saleList=(List<SalesReportBillwise>)session.getAttribute("pdfData");
				System.out.println("session Id in PDF Mapping "+session.getId());

			 String name=(String)request.getSession().getAttribute("name");
			 
			 System.out.println("name using session : ="+name);
			
			System.out.println("sale List Inside Pdf using session "+saleList);
			
			System.out.println("inside PDf data "+saleListForPdf.toString());
		
		model.addObject("fromDate",fDate);
		
		model.addObject("toDate",tDate);
		
		model.addObject("report",saleListForPdf);
		}catch (Exception e) {
			System.out.println("Exce in show Sale Bill wise by fr PDF "+e.getMessage());
			e.printStackTrace();
		}
		
		return model;
	}
	
	
	//report 3
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

			} else {
				System.out.println("Inside else Few fr Selected ");
				
				map.add("frIdList", selectedFr);
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				ParameterizedTypeReference<List<SalesReportBillwise>> typeRef = new ParameterizedTypeReference<List<SalesReportBillwise>>() {
				};
				ResponseEntity<List<SalesReportBillwise>> responseEntity = restTemplate
						.exchange(Constants.url + "getSaleReportBillwiseByDate", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				saleList = responseEntity.getBody();
				saleListForPdf=new ArrayList<>();
				
				saleListForPdf=saleList;
				
				System.out.println("sales List Bill Wise "+saleList.toString());

			}
		} catch (Exception e) {
			System.out.println("get sale Report Bill Wise "+e.getMessage());
			e.printStackTrace();

		}
	
		return saleList;
		
	}
	
	@RequestMapping(value = "/showSaleBillwiseGrpByDatePdf/{fDate}/{tDate}", method = RequestMethod.GET)
	public ModelAndView showSaleBillwiseGrpByDate(@PathVariable String fDate,@PathVariable
			String tDate,HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/pdf/billwisesalesgrpbydatePdf");
		try {

		System.out.println("inside PDf data "+saleListForPdf.toString());
		
		model.addObject("fromDate",fDate);
		
		model.addObject("toDate",tDate);
		
		model.addObject("report",saleListForPdf);
		}catch (Exception e) {
			
			System.out.println("Exce in show Sale Bill wise by fr PDF "+e.getMessage());
			e.printStackTrace();
			
		}
		
		return model;
	}
	
	
	//getSaleReportBillwiseByMonth
	
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

			} else {
				System.out.println("Inside else Few fr Selected ");
				
				map.add("frIdList", selectedFr);
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				ParameterizedTypeReference<List<SalesReportBillwise>> typeRef = new ParameterizedTypeReference<List<SalesReportBillwise>>() {
				};
				ResponseEntity<List<SalesReportBillwise>> responseEntity = restTemplate
						.exchange(Constants.url + "getSaleReportBillwiseByMonth", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				saleList = responseEntity.getBody();
				saleListForPdf=new ArrayList<>();
				
				saleListForPdf=saleList;
				
				System.out.println("sales List Bill Wise "+saleList.toString());

			}
		} catch (Exception e) {
			System.out.println("get sale Report Bill Wise "+e.getMessage());
			e.printStackTrace();

		}
	
		return saleList;
		
	}
	
	
	@RequestMapping(value = "/showSaleBillwiseGrpByMonthPdf/{fDate}/{tDate}", method = RequestMethod.GET)
	public ModelAndView showSaleBillwiseGrpByMonthPdf(@PathVariable String fDate, @PathVariable String tDate,
			HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/pdf/billwisesalesgrpbymonthPdf");
		try {

		System.out.println("inside PDf data "+saleListForPdf.toString());
		
		model.addObject("fromDate",fDate);
		
		model.addObject("toDate",tDate);
		
		model.addObject("report",saleListForPdf);
		}catch (Exception e) {
			System.out.println("Exce in show Sale Bill wise by fr PDF "+e.getMessage());
			e.printStackTrace();
		}
		
		return model;
	}
	
	
	//*******************************************************************//
	//Royalty Sale
	
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
	
	@RequestMapping(value = "/getSaleRoyaltyByCat", method = RequestMethod.GET)
	public @ResponseBody RoyaltyListBean getSaleRoyaltyByCat(HttpServletRequest request,
			HttpServletResponse response) {

		List<SalesReportBillwise> saleList = new ArrayList<>();
		List<SalesReportRoyalty> royaltyList = new ArrayList<>();
		RoyaltyListBean royaltyBean=new RoyaltyListBean();
		
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

			} else {
				System.out.println("Inside else Few fr Selected ");
				
				map.add("frIdList", selectedFr);
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				ParameterizedTypeReference<List<SalesReportRoyalty>> typeRef = new ParameterizedTypeReference<List<SalesReportRoyalty>>() {
				};
				ResponseEntity<List<SalesReportRoyalty>> responseEntity = restTemplate
						.exchange(Constants.url + "getSalesReportRoyalty", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				royaltyList = responseEntity.getBody();
				royaltyListForPdf=new ArrayList<>();
				
				royaltyListForPdf=royaltyList;
				
				System.out.println("royaltyList List Bill Wise "+saleList.toString());
				
				CategoryListResponse categoryListResponse = restTemplate.getForObject(Constants.url + "showAllCategory",
						CategoryListResponse.class);
				List<MCategoryList> categoryList ;
				categoryList = categoryListResponse.getmCategoryList();
				//allFrIdNameList = new AllFrIdNameList();
				System.out.println("Category list  " +categoryList);
				
				royaltyBean.setCategoryList(categoryList);
				royaltyBean.setSalesReportRoyalty(royaltyList);
				staticRoyaltyBean=royaltyBean;
			}
		} catch (Exception e) {
			System.out.println("get sale Report royaltyList by cat "+e.getMessage());
			e.printStackTrace();

		}
	
		return royaltyBean;
		
	}
	
	
	@RequestMapping(value = "/showSaleRoyaltyByCatPdf/{fDate}/{tDate}", method = RequestMethod.GET)
	public ModelAndView showSaleBil(@PathVariable String fDate, @PathVariable String tDate,
			HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/pdf/salesroyaltybycatPdf");
		try {

		System.out.println("inside PDf data staticRoyaltyBean "+staticRoyaltyBean.toString());
		
		model.addObject("fromDate",fDate);
		
		model.addObject("toDate",tDate);
		
		model.addObject("catList",staticRoyaltyBean.getCategoryList());
		model.addObject("royaltyList",staticRoyaltyBean.getSalesReportRoyalty());

		
		}catch (Exception e) {
			System.out.println("Exce in show Sale Bill wise by fr PDF "+e.getMessage());
			e.printStackTrace();
		}
		
		return model;
	}
	
	
	
	//royalty FR wise
	
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

				System.out.println("Inside If all fr Selected ");
				
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

			} else {
				System.out.println("Inside else Few fr Selected ");
				
				map.add("frIdList", selectedFr);
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				ParameterizedTypeReference<List<SalesReportRoyaltyFr>> typeRef = new ParameterizedTypeReference<List<SalesReportRoyaltyFr>>() {
				};
				ResponseEntity<List<SalesReportRoyaltyFr>> responseEntity = restTemplate
						.exchange(Constants.url + "getSalesReportRoyaltyFr", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				
				royaltyFrList=new ArrayList<>();
				royaltyFrList = responseEntity.getBody();
				//royaltyListForPdf=new ArrayList<>();
				
				//royaltyListForPdf=royaltyList;
				staticRoyaltyFrList=new ArrayList<>();
				
				staticRoyaltyFrList=royaltyFrList;
				System.out.println("royalty List List royaltyFr List "+royaltyFrList.toString());
				
				//allFrIdNameList = new AllFrIdNameList();
				
			}
		} catch (Exception e) {
			System.out.println("get sale Report royaltyList by cat "+e.getMessage());
			e.printStackTrace();

		}
	
		return royaltyFrList;
		
	}
	
	//royalty fr pdf is not done
	
	//done pdf
	@RequestMapping(value = "/showSaleRoyaltyByFrPdf/{fDate}/{tDate}", method = RequestMethod.GET)
	public ModelAndView showSaleRoyaltyByFrPdf(@PathVariable String fDate, @PathVariable String tDate,
			HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/pdf/salesroyaltybyfrPdf");
		try {

		System.out.println("inside PDf data royaltyFrList "+staticRoyaltyFrList.toString());
		
		model.addObject("fromDate",fDate);
		
		model.addObject("toDate",tDate);
		
		model.addObject("report",staticRoyaltyFrList);
		
		}catch (Exception e) {
			System.out.println("Exce in show showSaleRoyaltyByFrPdf "+e.getMessage());
			e.printStackTrace();
		}
		
		return model;
	}
	
	
	
	
	
	//report no 8
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
				ResponseEntity<List<SalesReportItemwise>> responseEntity = restTemplate
						.exchange(Constants.url + "getSaleReportItemwise", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				saleList = responseEntity.getBody();
				saleListForPdf=new ArrayList<>();
				staticSaleListItemWise=saleList;
				//saleListForPdf=saleList;
				
				System.out.println("sales List Bill Wise "+saleList.toString());

			}
		} catch (Exception e) {
			System.out.println("get sale Report Bill Wise "+e.getMessage());
			e.printStackTrace();

		}
	
		return saleList;
		
	}
	//pdf for r8 to be done
	//pdf for r8
	@RequestMapping(value = "/showSaleReportItemwisePdf/{fDate}/{tDate}", method = RequestMethod.GET)
	public ModelAndView showSaleReportItemwisePdf(@PathVariable String fDate, @PathVariable String tDate,
			HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/pdf/salesreportitemwisePdf");
		try {

		System.out.println("inside PDf data staticSaleListItemWise "+staticSaleListItemWise.toString());
		
		model.addObject("fromDate",fDate);
		
		model.addObject("toDate",tDate);
		
		model.addObject("report",staticSaleListItemWise);
		
		}catch (Exception e) {
			System.out.println("Exce in show staticSaleListItemWisePdf "+e.getMessage());
			e.printStackTrace();
		}
		
		return model;
	}
	
	
	//report 7
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

			} else {
				System.out.println("Inside else Few fr Selected ");
				
				map.add("frIdList", selectedFr);
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				ParameterizedTypeReference<List<SalesReportBillwiseAllFr>> typeRef = new ParameterizedTypeReference<List<SalesReportBillwiseAllFr>>() {
				};
				ResponseEntity<List<SalesReportBillwiseAllFr>> responseEntity = restTemplate
						.exchange(Constants.url + "getSaleReportBillwiseAllFr", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				saleList = responseEntity.getBody();
				staticSaleByAllFr=new ArrayList<>();
				staticSaleByAllFr=saleList;
				//saleListForPdf=saleList;
				
				System.out.println("sales List Bill Wise all fr  "+saleList.toString());

			}
		} catch (Exception e) {
			System.out.println("get sale Report Bill Wise all Fr "+e.getMessage());
			e.printStackTrace();

		}
	
		return saleList;
	}
	//pdf to be done
	
	//pdf report 7
	@RequestMapping(value = "/showSaleReportBillwiseAllFrPdf/{fDate}/{tDate}", method = RequestMethod.GET)
	public ModelAndView showSaleReportBillwiseAllFrPdf(@PathVariable String fDate, @PathVariable String tDate,
			HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/pdf/salereportbillallfrPdf");
		try {

		System.out.println("inside PDf data staticSaleByAllFr "+staticSaleByAllFr.toString());
		
		model.addObject("fromDate",fDate);
		
		model.addObject("toDate",tDate);
		
		model.addObject("report",staticSaleByAllFr);
		
		}catch (Exception e) {
			System.out.println("Exce in show staticSaleByAllFr "+e.getMessage());
			e.printStackTrace();
		}
		
		return model;
	}
	
	
	//report no 10 conso by category report
	
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
			List<MCategoryList> categoryList ;
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
	
	
	@RequestMapping(value = "/getSaleReportRoyConsoByCat", method = RequestMethod.GET)
	public @ResponseBody RoyaltyListBean getSaleReportRoyConsoByCat(HttpServletRequest request,
			HttpServletResponse response) {

		List<SalesReportBillwise> saleList = new ArrayList<>();
		List<SalesReportRoyalty> royaltyList = new ArrayList<>();
		RoyaltyListBean royaltyBean=new RoyaltyListBean();
		
		try {
			System.out.println("Inside get Sale Bill Wise");
			String selectedFr = request.getParameter("fr_id_list");
			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
			String routeId = request.getParameter("route_id");
			
			int isGraph = Integer.parseInt(request.getParameter("is_graph"));
			
			String selectedCat=request.getParameter("cat_id_list");
			
			boolean isAllFrSelected = false;
			boolean isAllCatSelected = false;
			selectedFr = selectedFr.substring(1, selectedFr.length() - 1);
			selectedFr = selectedFr.replaceAll("\"", "");
			
			selectedCat = selectedCat.substring(1, selectedCat.length() - 1);
			selectedCat = selectedCat.replaceAll("\"", "");

			List<String> catList=new ArrayList<>();
			catList=Arrays.asList(selectedCat);
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
			
			

			if (isAllCatSelected) {

				System.out.println("Inside If all fr Selected ");
				
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

			}else {
				
				map.add("catIdList", selectedCat);
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);
				
				
			}

			if (isAllFrSelected) {

				System.out.println("Inside If all fr Selected ");
				
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

			} else {
				System.out.println("Inside else Few fr Selected ");
			//	map.add("catIdList", selectedCat);
				map.add("frIdList", selectedFr);
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);
				if(isGraph==0) {
				ParameterizedTypeReference<List<SalesReportRoyalty>> typeRef = new ParameterizedTypeReference<List<SalesReportRoyalty>>() {
				};
				
				ResponseEntity<List<SalesReportRoyalty>> responseEntity = restTemplate
						.exchange(Constants.url + "getSaleReportRoyConsoByCat", HttpMethod.POST, new HttpEntity<>(map), typeRef);
			
				
				royaltyList = responseEntity.getBody();
				royaltyListForPdf=new ArrayList<>();
				
				royaltyListForPdf=royaltyList;
				}
				
				
				if(isGraph==1) {
					ParameterizedTypeReference<List<SalesReportRoyalty>> typeRef = new ParameterizedTypeReference<List<SalesReportRoyalty>>() {
					};
					
					ResponseEntity<List<SalesReportRoyalty>> responseEntity = restTemplate
							.exchange(Constants.url + "getSaleReportRoyConsoByCatForGraph", HttpMethod.POST, new HttpEntity<>(map), typeRef);
				
					
					royaltyList = responseEntity.getBody();
					royaltyListForPdf=new ArrayList<>();
					
					royaltyListForPdf=royaltyList;
					}
				
				System.out.println("royaltyList List Bill Wise "+royaltyList.toString());
				
				CategoryListResponse categoryListResponse = restTemplate.getForObject(Constants.url + "showAllCategory",
						CategoryListResponse.class);
				List<MCategoryList> categoryList ;
				
				categoryList = categoryListResponse.getmCategoryList();
				//allFrIdNameList = new AllFrIdNameList();
				System.out.println("Category list  " +categoryList);
				List<MCategoryList> tempList = new ArrayList<>();	
				
				//royaltyBean.setCategoryList(categoryList);
				Map<Integer, String> catNameId = new HashMap<Integer, String>();

				for(int i=0;i<categoryList.size();i++) {
					
					for(int j=0;j<royaltyList.size();j++) {
						
						if(categoryList.get(i).getCatId()==royaltyList.get(j).getCatId()) {
							catNameId.put(categoryList.get(i).getCatId(), categoryList.get(i).getCatName());
							
							if(!tempList.contains(categoryList.get(i))){
								
								tempList.add(categoryList.get(i));
								
							}
						}
						
					}
					
				}
				
				System.out.println("temp list "+tempList.toString()+"size of t List "+tempList.size());
				royaltyBean.setCategoryList(tempList);
				royaltyBean.setSalesReportRoyalty(royaltyList);
				staticRoyaltyBean=royaltyBean;
			}
		} catch (Exception e) {
			System.out.println("get sale Report royaltyList by cat "+e.getMessage());
			e.printStackTrace();

		}
	
		return royaltyBean;
		
	}
	
	
	//pdf function
	
	private Dimension format = PD4Constants.A4;
	private boolean landscapeValue = false;
	private int topValue = 0;
	private int leftValue = 0;
	private int rightValue = 0;
	private int bottomValue = 0;
	private String unitsValue = "m";
	private String proxyHost = "";
	private int proxyPort = 0;

	private int userSpaceWidth = 750;
	private static int BUFFER_SIZE = 1024;

	@RequestMapping(value = "/pdfForReport", method = RequestMethod.GET)
	public void showPDF(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Inside PDf For Report URL ");
		String url = request.getParameter("url");
		System.out.println("URL "+url);
		// http://monginis.ap-south-1.elasticbeanstalk.com
		//File f = new File("/home/ats-11/pdf/ordermemo223.pdf");
		
		File f = new File("/ordermemo223.pdf");

		System.out.println("I am here "+f.toString());
		
		try {
			runConverter(Constants.ReportURL + url, f);
			System.out.println("Come on lets get ");
		} catch (IOException e) {
			// TODO Auto-generated catch block

			System.out.println("Pdf conversion exception " + e.getMessage());
		}

		// get absolute path of the application
		ServletContext context = request.getSession().getServletContext();
		String appPath = context.getRealPath("");
		String filename = "ordermemo223.pdf";
		//String filePath = "/home/ats-11/pdf/ordermemo223.pdf";
		
		String filePath = "/ordermemo223.pdf";

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

	private void runConverter(String urlstring, File output) throws IOException {
	
		if (urlstring.length() > 0) {
			if (!urlstring.startsWith("http://") && !urlstring.startsWith("file:")) {
				urlstring = "http://" + urlstring;
			}

			java.io.FileOutputStream fos = new java.io.FileOutputStream(output);

			if (proxyHost != null && proxyHost.length() != 0 && proxyPort != 0) {
				System.getProperties().setProperty("proxySet", "true");
				System.getProperties().setProperty("proxyHost", proxyHost);
				System.getProperties().setProperty("proxyPort", "" + proxyPort);
			}

			PD4ML pd4ml = new PD4ML();

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
		}
	}
	
	
}
