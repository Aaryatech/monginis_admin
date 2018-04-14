package com.ats.adminpanel.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.commons.DateConvertor;
import com.ats.adminpanel.model.AllFrIdNameList;
import com.ats.adminpanel.model.AllRoutesListResponse;
import com.ats.adminpanel.model.Route;
import com.ats.adminpanel.model.SalesVoucherList;
import com.ats.adminpanel.model.billing.FrBillHeaderForPrint;
import com.ats.adminpanel.model.franchisee.FrNameIdByRouteId;
import com.ats.adminpanel.model.franchisee.FrNameIdByRouteIdResponse;
import com.ats.adminpanel.model.ggreports.GGReportByDateAndFr;
import com.ats.adminpanel.model.ggreports.GGReportGrpByFrId;
import com.ats.adminpanel.model.ggreports.GGReportGrpByMonthDate;
import com.ats.adminpanel.model.salesreport.SalesReportBillwise;

@Controller
@Scope("session")
public class GrnGvnReportController {
	
	
	public AllFrIdNameList allFrIdNameList = new AllFrIdNameList();
	
	public List<Route> routeList = new ArrayList<Route>();
	AllRoutesListResponse  allRouteListResponse= new AllRoutesListResponse();
	 
	AllFrIdNameList getFrNameId() {
		
		RestTemplate restTemplate = new RestTemplate();
		return allFrIdNameList=restTemplate.getForObject(Constants.url + "getAllFrIdName", AllFrIdNameList.class);
	}
	
	AllRoutesListResponse getAllRoute() {
		
		RestTemplate restTemplate = new RestTemplate();

		return  allRouteListResponse = restTemplate.getForObject(Constants.url + "showRouteList",
				AllRoutesListResponse.class);
		
	}
	//r1
	@RequestMapping(value = "/showGGReportDateWise", method = RequestMethod.GET)
	public ModelAndView showGGReportDateWise(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/grnGvn/ggByDate");
		
		try {

			ZoneId z = ZoneId.of("Asia/Calcutta");

			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			String todaysDate = date.format(formatters);

			allFrIdNameList = getFrNameId();

			allRouteListResponse = getAllRoute();
			
			model.addObject("routeList", allRouteListResponse.getRoute());
			model.addObject("todaysDate", todaysDate);
			model.addObject("unSelectedFrList", allFrIdNameList.getFrIdNamesList());

		} catch (Exception e) {
			System.out.println("Exce inshowGGReportDateWise " + e.getMessage());
			e.printStackTrace();
		}

		return model;
	}
	//consume R1 web Service
	
	
	
	
	@RequestMapping(value = "/getGrnGvnByDatewise", method = RequestMethod.GET)
	@ResponseBody
	public  List<GGReportByDateAndFr> excelForFrBill(HttpServletRequest request, HttpServletResponse response) {
		
		List<GGReportByDateAndFr> grnGvnByDateList= new ArrayList<>();

		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			RestTemplate restTemplate = new RestTemplate();

			String routeId = "0";
			String frIdString = "";

			System.out.println("inside getGrnGvnByDatewise ajax call");

			frIdString = request.getParameter("fr_id_list");
			String fromDate = request.getParameter("from_date");
			String toDate = request.getParameter("to_date");
			routeId = request.getParameter("route_id");
			
			String isGrn=request.getParameter("is_grn");

			

			System.out.println("fromDate= " + fromDate);

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

			map = new LinkedMultiValueMap<String, Object>();
			if (isAllFrSelected) {

								System.out.println("Inside IF  is All fr Selected " + isAllFrSelected);

				map.add("frIdList", 0);
				// model.addObject("billHeadersList",billHeadersListForPrint);

			} else { // few franchisee selected

				System.out.println("Inside Else: Few Fr Selected ");
				map.add("frIdList", frIdString);
				
			}

			map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			map.add("toDate", DateConvertor.convertToYMD(toDate));
			map.add("isGrn", isGrn);
			
			
				ParameterizedTypeReference<List<GGReportByDateAndFr>> typeRef = new ParameterizedTypeReference<List<GGReportByDateAndFr>>() {
				};
				ResponseEntity<List<GGReportByDateAndFr>> responseEntity = restTemplate.exchange(
						Constants.url + "getgGReportByDate", HttpMethod.POST, new HttpEntity<>(map), typeRef);
				
				grnGvnByDateList = responseEntity.getBody();
				
				System.err.println("List " +grnGvnByDateList.toString());

		} catch (Exception e) {

			System.out.println("Ex in getting /getgGReportByDate List  Ajax call" + e.getMessage());
			e.printStackTrace();
		}
		
		return grnGvnByDateList;
	
	}
	
	
	//r2
		@RequestMapping(value = "/showGGReportGrpByFr", method = RequestMethod.GET)
		public ModelAndView showGGReportGrpByFr(HttpServletRequest request, HttpServletResponse response) {

			ModelAndView model = new ModelAndView("reports/grnGvn/ggGrpByFr");
			
			try {

				ZoneId z = ZoneId.of("Asia/Calcutta");

				LocalDate date = LocalDate.now(z);
				DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
				String todaysDate = date.format(formatters);

				allFrIdNameList = getFrNameId();

				allRouteListResponse = getAllRoute();
				
				model.addObject("routeList", allRouteListResponse.getRoute());
				model.addObject("todaysDate", todaysDate);
				model.addObject("unSelectedFrList", allFrIdNameList.getFrIdNamesList());

			} catch (Exception e) {
				System.out.println("Exce inshowGGReportDateWise " + e.getMessage());
				e.printStackTrace();
			}

			return model;
		}
		//consume r2 web service Ajax call
		@RequestMapping(value = "/getGrnGvnByGrpByFr", method = RequestMethod.GET)
		@ResponseBody
		public  List<GGReportGrpByFrId> getGrnGvnByGrpByFr(HttpServletRequest request, HttpServletResponse response) {
			
			List<GGReportGrpByFrId> grnGvnGrpByFrList= new ArrayList<>();

			try {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				RestTemplate restTemplate = new RestTemplate();

				String routeId = "0";
				String frIdString = "";

				System.out.println("inside getGrnGvnByDatewise ajax call");

				frIdString = request.getParameter("fr_id_list");
				String fromDate = request.getParameter("from_date");
				String toDate = request.getParameter("to_date");
				routeId = request.getParameter("route_id");
				String isGrn=request.getParameter("is_grn");

				System.out.println("fromDate= " + fromDate);

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

				map = new LinkedMultiValueMap<String, Object>();
				if (isAllFrSelected) {

									System.out.println("Inside IF  is All fr Selected " + isAllFrSelected);

					map.add("frIdList", 0);
					// model.addObject("billHeadersList",billHeadersListForPrint);

				} else { // few franchisee selected

					System.out.println("Inside Else: Few Fr Selected ");
					map.add("frIdList", frIdString);
					
				}

				map.add("fromDate", DateConvertor.convertToYMD(fromDate));
				map.add("toDate", DateConvertor.convertToYMD(toDate));
				map.add("isGrn", isGrn);
				
				
					ParameterizedTypeReference<List<GGReportGrpByFrId>> typeRef = new ParameterizedTypeReference<List<GGReportGrpByFrId>>() {
					};
					ResponseEntity<List<GGReportGrpByFrId>> responseEntity = restTemplate.exchange(
							Constants.url + "gGReportGrpByFrId", HttpMethod.POST, new HttpEntity<>(map), typeRef);
					
					grnGvnGrpByFrList = responseEntity.getBody();
					
					System.err.println("List " +grnGvnGrpByFrList.toString());

			} catch (Exception e) {

				System.out.println("Ex in getting /getGrnGvnByGrpByFr List  Ajax call" + e.getMessage());
				e.printStackTrace();
			}
			
			return grnGvnGrpByFrList;
		
		}
		
		//r2 PDFshowGGreportGrpByFr
		
		@RequestMapping(value = "pdf/showGGreportGrpByFr/{fDate}/{tDate}/{selectedFr}/{routeId}/{isGrn}", method = RequestMethod.GET)
		public ModelAndView showSaleReportByDatePdf(@PathVariable String fDate, @PathVariable String tDate,
				@PathVariable String selectedFr, @PathVariable String routeId,@PathVariable int isGrn, HttpServletRequest request,
				HttpServletResponse response) {

			ModelAndView model = new ModelAndView("reports/grnGvn/pdf/r2");

			List<GGReportGrpByFrId> grnGvnGrpByFrList= new ArrayList<>();
			
			System.err.println("Inside PDF mapping");
			
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
					map.add("frIdList", 0);
					
				} else {
					System.out.println("Inside else Few fr Selected ");

					map.add("frIdList", selectedFr);
					
					
				}
				
					map.add("fromDate", DateConvertor.convertToYMD(fDate));
					map.add("toDate", DateConvertor.convertToYMD(tDate));
					map.add("isGrn", isGrn);

					ParameterizedTypeReference<List<GGReportGrpByFrId>> typeRef = new ParameterizedTypeReference<List<GGReportGrpByFrId>>() {
					};
					ResponseEntity<List<GGReportGrpByFrId>> responseEntity = restTemplate.exchange(
							Constants.url + "gGReportGrpByFrId", HttpMethod.POST, new HttpEntity<>(map), typeRef);
					
					grnGvnGrpByFrList = responseEntity.getBody();
					
					System.err.println("List---- " +grnGvnGrpByFrList.toString());

				
			} catch (Exception e) {
				System.err.println("Exc in GRN PDF report 2");
				e.printStackTrace();
			}

			model.addObject("fromDate", fDate);

			model.addObject("toDate", tDate);

			model.addObject("report", grnGvnGrpByFrList);

			return model;
		}


		
		//r3
		
		@RequestMapping(value = "/showGGReportGrpByDate", method = RequestMethod.GET)
		public ModelAndView showGGReportGrpByDate(HttpServletRequest request, HttpServletResponse response) {

			ModelAndView model = new ModelAndView("reports/grnGvn/grnGvnGrpByDate");
			
			try {

				ZoneId z = ZoneId.of("Asia/Calcutta");

				LocalDate date = LocalDate.now(z);
				DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
				String todaysDate = date.format(formatters);

				allFrIdNameList = getFrNameId();

				allRouteListResponse = getAllRoute();
				
				model.addObject("routeList", allRouteListResponse.getRoute());
				model.addObject("todaysDate", todaysDate);
				model.addObject("unSelectedFrList", allFrIdNameList.getFrIdNamesList());

			} catch (Exception e) {
				System.out.println("Exce inshowGGReportDateWise " + e.getMessage());
				e.printStackTrace();
			}

			return model;
		}
		
		
		//consume r3 web service  ajax call
		//
		
		@RequestMapping(value = "/getGrnGvnByGrpByDate", method = RequestMethod.GET)
		@ResponseBody
		public  List<GGReportGrpByMonthDate> getGrnGvnByGrpByDate(HttpServletRequest request, HttpServletResponse response) {
			
			List<GGReportGrpByMonthDate> grnGvnGrpByDateList= new ArrayList<>();

			try {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				RestTemplate restTemplate = new RestTemplate();

				String routeId = "0";
				String frIdString = "";

				System.out.println("inside getGrnGvnByDatewise ajax call");

				frIdString = request.getParameter("fr_id_list");
				String fromDate = request.getParameter("from_date");
				String toDate = request.getParameter("to_date");
				routeId = request.getParameter("route_id");
				String isGrn=request.getParameter("is_grn");

				System.out.println("fromDate= " + fromDate);

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

				map = new LinkedMultiValueMap<String, Object>();
				if (isAllFrSelected) {

									System.out.println("Inside IF  is All fr Selected " + isAllFrSelected);

					map.add("frIdList", 0);
					// model.addObject("billHeadersList",billHeadersListForPrint);

				} else { // few franchisee selected

					System.out.println("Inside Else: Few Fr Selected ");
					map.add("frIdList", frIdString);
					
				}

				map.add("fromDate", DateConvertor.convertToYMD(fromDate));
				map.add("toDate", DateConvertor.convertToYMD(toDate));
				map.add("isGrn", isGrn);
				
				
					ParameterizedTypeReference<List<GGReportGrpByMonthDate>> typeRef = new ParameterizedTypeReference<List<GGReportGrpByMonthDate>>() {
					};
					ResponseEntity<List<GGReportGrpByMonthDate>> responseEntity = restTemplate.exchange(
							Constants.url + "getGGReportGrpByDate", HttpMethod.POST, new HttpEntity<>(map), typeRef);
					
					grnGvnGrpByDateList = responseEntity.getBody();
					
					System.err.println("List " +grnGvnGrpByDateList.toString());

			} catch (Exception e) {

				System.out.println("Ex in getting /getGrnGvnByGrpByDate List  Ajax call" + e.getMessage());
				e.printStackTrace();
			}
			
			return grnGvnGrpByDateList;
		
		}
		
		
		@RequestMapping(value = "/showGGReportGrpByMonth", method = RequestMethod.GET)
		public ModelAndView showGGReportGrpByMonth(HttpServletRequest request, HttpServletResponse response) {

			ModelAndView model = new ModelAndView("reports/grnGvn/gGGrpByMonth");
			
			try {

				ZoneId z = ZoneId.of("Asia/Calcutta");

				LocalDate date = LocalDate.now(z);
				DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
				String todaysDate = date.format(formatters);

				allFrIdNameList = getFrNameId();

				allRouteListResponse = getAllRoute();
				
				model.addObject("routeList", allRouteListResponse.getRoute());
				model.addObject("todaysDate", todaysDate);
				model.addObject("unSelectedFrList", allFrIdNameList.getFrIdNamesList());

			} catch (Exception e) {
				System.out.println("Exce inshowGGReportDateWise " + e.getMessage());
				e.printStackTrace();
			}

			return model;
		}
		
		
		@RequestMapping(value = "/getGrnGvnByGrpByMonth", method = RequestMethod.GET)
		@ResponseBody
		public  List<GGReportGrpByMonthDate> getGrnGvnByGrpByMonth(HttpServletRequest request, HttpServletResponse response) {
			
			List<GGReportGrpByMonthDate> grnGvnGrpByMonthList= new ArrayList<>();

			try {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				RestTemplate restTemplate = new RestTemplate();

				String routeId = "0";
				String frIdString = "";

				System.out.println("inside getGrnGvnByDatewise ajax call");

				frIdString = request.getParameter("fr_id_list");
				String fromDate = request.getParameter("from_date");
				String toDate = request.getParameter("to_date");
				routeId = request.getParameter("route_id");
				String isGrn=request.getParameter("is_grn");

				System.out.println("fromDate= " + fromDate);

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

				map = new LinkedMultiValueMap<String, Object>();
				if (isAllFrSelected) {

									System.out.println("Inside IF  is All fr Selected " + isAllFrSelected);

					map.add("frIdList", 0);
					// model.addObject("billHeadersList",billHeadersListForPrint);

				} else { // few franchisee selected

					System.out.println("Inside Else: Few Fr Selected ");
					map.add("frIdList", frIdString);
					
				}

				map.add("fromDate", DateConvertor.convertToYMD(fromDate));
				map.add("toDate", DateConvertor.convertToYMD(toDate));
				map.add("isGrn", isGrn);
				
					ParameterizedTypeReference<List<GGReportGrpByMonthDate>> typeRef = new ParameterizedTypeReference<List<GGReportGrpByMonthDate>>() {
					};
					ResponseEntity<List<GGReportGrpByMonthDate>> responseEntity = restTemplate.exchange(
							Constants.url + "getGGReportGrpByMonth", HttpMethod.POST, new HttpEntity<>(map), typeRef);
					
					grnGvnGrpByMonthList = responseEntity.getBody();
					
					
					System.err.println("List " +grnGvnGrpByMonthList.toString());

			} catch (Exception e) {

				System.out.println("Ex in getting /getGrnGvnByGrpByDate List  Ajax call" + e.getMessage());
				e.printStackTrace();
			}
			
			return grnGvnGrpByMonthList;
			
		}
		
}
