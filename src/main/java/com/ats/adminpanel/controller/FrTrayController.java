package com.ats.adminpanel.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.commons.DateConvertor;
import com.ats.adminpanel.model.AllRoutesListResponse;
import com.ats.adminpanel.model.GetSubCategory;
import com.ats.adminpanel.model.Route;
import com.ats.adminpanel.model.RouteWithFrList;
import com.ats.adminpanel.model.franchisee.AllMenuResponse;
import com.ats.adminpanel.model.franchisee.Menu;
import com.ats.adminpanel.model.tray.CalCulateTray;
import com.ats.adminpanel.model.tray.FranchiseInRoute;

@Controller
public class FrTrayController {

	String todaysDate;
	public List<Menu> menuList;
	List<Route> routeList;

	@RequestMapping(value = "/showCalculateTrayReport", method = RequestMethod.GET)
	public ModelAndView showCalculateTrayReport(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/calTrayReport");

		try {
			ZoneId z = ZoneId.of("Asia/Calcutta");

			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			todaysDate = date.format(formatters);

			RestTemplate restTemplate = new RestTemplate();

			AllRoutesListResponse allRouteListResponse = restTemplate.getForObject(Constants.url + "showRouteList",
					AllRoutesListResponse.class);

			routeList = new ArrayList<Route>();

			routeList = allRouteListResponse.getRoute();

			model.addObject("routeList", routeList);

			model.addObject("todaysDate", todaysDate);

			AllMenuResponse allMenuResponse = restTemplate.getForObject(Constants.url + "getAllMenu",
					AllMenuResponse.class);

			menuList = new ArrayList<Menu>();
			menuList = allMenuResponse.getMenuConfigurationPage();

			System.out.println("MENU LIST= " + menuList.toString());
			model.addObject("menuList", menuList);

		} catch (Exception e) {

			System.out.println("Exc in showCalculateTrayReport  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/searchCalculateTrayReport", method = RequestMethod.GET)
	public ModelAndView searchCalculateTrayReport(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/calTrayRepDetail");

		try {

			String date = request.getParameter("billDate");

			// -------------------------------------------------------------------------------
			// int routeId = Integer.parseInt(request.getParameter("selectRoute"));

			String[] selRouteId = request.getParameterValues("selectRoute");

			String routeIds = new String();

			for (int i = 0; i < selRouteId.length; i++) {
				routeIds = routeIds + "," + selRouteId[i];
			}
			routeIds = routeIds.substring(1, routeIds.length());

			if (routeIds.contains("0")) {

				routeIds = new String();

				for (int i = 0; i < routeList.size(); i++) {
					routeIds = routeIds + "," + routeList.get(i).getRouteId();
				}
				routeIds = routeIds.substring(1, routeIds.length());
			}

			System.out.println("routeIdsrouteIdsrouteIdsrouteIdsrouteIdsrouteIdsrouteIds" + routeIds);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			RestTemplate restTemplate = new RestTemplate();

			String[] menuId = request.getParameterValues("menuId");

			String menuIds = new String();

			for (int i = 0; i < menuId.length; i++) {
				menuIds = menuIds + "," + menuId[i];
			}
			menuIds = menuIds.substring(1, menuIds.length());

			if (menuIds.contains("0")) {

				menuIds = new String();

				for (int i = 0; i < menuList.size(); i++) {
					menuIds = menuIds + "," + menuList.get(i).getMenuId();
				}
				menuIds = menuIds.substring(1, menuIds.length());
			}

			System.out.println("menuIdsmenuIdsmenuIdsmenuIdsmenuIdsmenuIds" + menuIds);

			// RestTemplate restTemplate = new RestTemplate();

			map = new LinkedMultiValueMap<>();// change
			map.add("routeIds", routeIds);

			FranchiseInRoute[] routeMaster = restTemplate.postForObject(
					Constants.url + "traymgt/getFranchiseInRouteListForTray", map, FranchiseInRoute[].class);
			List<FranchiseInRoute> routeListForFr = new ArrayList<FranchiseInRoute>(Arrays.asList(routeMaster));
			System.out.println("FRLIST ---------------------- " + routeListForFr.toString());

			RouteWithFrList[] routeMaster1 = restTemplate
					.postForObject(Constants.url + "traymgt/getRouteWithFrListForTray", map, RouteWithFrList[].class);
			List<RouteWithFrList> routeWithFrList = new ArrayList<RouteWithFrList>(Arrays.asList(routeMaster1));
			System.out.println("FRLIST routeWithFrList---------------------- " + routeWithFrList.toString());

			String frIds = new String();

			for (int i = 0; i < routeListForFr.size(); i++) {
				frIds = frIds + "," + routeListForFr.get(i).getFrId();

			}
			frIds = frIds.substring(1, frIds.length());

			System.out.println("frIdsfrIdsfrIdsfrIdsfrIdsfrIds" + frIds);

			map = new LinkedMultiValueMap<>();
			map.add("deliveryDate", DateConvertor.convertToYMD(date));
			map.add("frIdList", frIds);
			map.add("menuIdList", menuIds);

			CalCulateTray[] calCulateTray = restTemplate.postForObject(Constants.url + "traymgt/getAllCalTrayReport",
					map, CalCulateTray[].class);
			List<CalCulateTray> calListForFr = new ArrayList<CalCulateTray>(Arrays.asList(calCulateTray));
			System.out.println("calListForFr" + calListForFr.toString());

			model.addObject("calListForFr", calListForFr);
			model.addObject("routeListForFr", routeListForFr);
			model.addObject("frIds", frIds);
			model.addObject("routeWithFr", routeWithFrList);

			String submit1 = request.getParameter("submit1");
			String submit2 = request.getParameter("submit2");

			String submit3 = request.getParameter("submit3");

			String submit4 = request.getParameter("submit4");
			if (submit1 != null) {
				model.addObject("submit1", 1);
			} else if (submit2 != null) {

				model.addObject("submit2", 2);
			} else if (submit3 != null) {

				model.addObject("submit3", 3);
			} else if (submit4 != null) {

				model.addObject("submit4", 4);
			}

			GetSubCategory[] subCatList = restTemplate.getForObject(Constants.url + "getAllSubCatList",
					GetSubCategory[].class);

			ArrayList<GetSubCategory> subCatAList = new ArrayList<GetSubCategory>(Arrays.asList(subCatList));
			System.out.println("subCatAList:----------" + subCatAList.toString());
			model.addObject("subCatAList", subCatAList);

			// model.addObject("frNameIdByRouteIdList", frNameIdByRouteIdList);
			model.addObject("date", date);
			model.addObject("menuIds", menuIds);
			model.addObject("routeIds", routeIds);

			model.addObject("routeListForFr1", routeList);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return model;

	}

	// calculateTrayDetailPdf

	@RequestMapping(value = "/pdf/getCalculateTrayReportPDF/{date}/{routeIds}/{menuIds}/{submit}", method = RequestMethod.GET)
	public ModelAndView getPDispatchReportNewPdf(@PathVariable String date, @PathVariable String routeIds,
			@PathVariable String menuIds, @PathVariable String submit, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/calculateTrayDetailPdf");

		try {

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("routeIds", routeIds);

			RouteWithFrList[] routeMaster1 = restTemplate
					.postForObject(Constants.url + "traymgt/getRouteWithFrListForTray", map, RouteWithFrList[].class);
			List<RouteWithFrList> routeListForFr = new ArrayList<RouteWithFrList>(Arrays.asList(routeMaster1));

			System.out.println("RouteListRouteListRouteListRouteListRouteList" + routeListForFr.toString());

			FranchiseInRoute[] routeMaster = restTemplate.postForObject(
					Constants.url + "traymgt/getFranchiseInRouteListForTray", map, FranchiseInRoute[].class);
			List<FranchiseInRoute> frListByRoute = new ArrayList<FranchiseInRoute>(Arrays.asList(routeMaster));
			System.out.println("FRLIST ---------------------- " + frListByRoute.toString());

			String frIds = new String();

			for (int i = 0; i < frListByRoute.size(); i++) {
				frIds = frIds + "," + frListByRoute.get(i).getFrId();

			}
			frIds = frIds.substring(1, frIds.length());

			System.out.println("frIdsfrIdsfrIdsfrIdsfrIdsfrIds" + frIds);

			map = new LinkedMultiValueMap<>();
			map.add("deliveryDate", DateConvertor.convertToYMD(date));
			map.add("frIdList", frIds);
			map.add("menuIdList", menuIds);

			CalCulateTray[] calCulateTray = restTemplate.postForObject(Constants.url + "traymgt/getAllCalTrayReport",
					map, CalCulateTray[].class);
			List<CalCulateTray> calListForFr = new ArrayList<CalCulateTray>(Arrays.asList(calCulateTray));
			System.out.println("calListForFr" + calListForFr.toString());

			model.addObject("calListForFr", calListForFr);
			model.addObject("routeListForFr", routeListForFr);
			model.addObject("frIds", frIds);

			System.out.println("calListForFrcalListForFrcalListForFrcalListForFr" + calListForFr.toString());

			System.out.println("submit1submit1submit1submit1submit1submit1submit1submit1submit1submit1" + submit);

			if (submit.contains("1")) {
				model.addObject("submit1", submit);
			} else if (submit.contains("2")) {

				model.addObject("submit2", 2);
			} else if (submit.contains("3")) {

				model.addObject("submit3", 3);
			} else if (submit.contains("4")) {

				model.addObject("submit4", 4);
			}

			GetSubCategory[] subCatList = restTemplate.getForObject(Constants.url + "getAllSubCatList",
					GetSubCategory[].class);

			ArrayList<GetSubCategory> subCatAList = new ArrayList<GetSubCategory>(Arrays.asList(subCatList));
			System.out.println("subCatAList:----------" + subCatAList.toString());
			model.addObject("subCatAList", subCatAList);

			map = new LinkedMultiValueMap<>();
			map.add("frIds", frIds);

			model.addObject("date", date);
			model.addObject("routeIds", routeIds);
			model.addObject("menuIds", menuIds);

		} catch (

		Exception e) {

			e.printStackTrace();
		}

		return model;

	}

}
