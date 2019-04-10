package com.ats.adminpanel.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
import com.ats.adminpanel.model.AllFrIdNameList;
import com.ats.adminpanel.model.AllRoutesListResponse;
import com.ats.adminpanel.model.DispatchReport;
import com.ats.adminpanel.model.DispatchReportList;
import com.ats.adminpanel.model.Info;
import com.ats.adminpanel.model.Route;
import com.ats.adminpanel.model.Station;
import com.ats.adminpanel.model.franchisee.FrNameIdByRouteId;
import com.ats.adminpanel.model.franchisee.FrNameIdByRouteIdResponse;
import com.ats.adminpanel.model.franchisee.SubCategory;
import com.ats.adminpanel.model.item.AllItemsListResponse;
import com.ats.adminpanel.model.item.CategoryListResponse;
import com.ats.adminpanel.model.item.Item;
import com.ats.adminpanel.model.item.MCategoryList;

@Controller
@Scope("session")
public class StationController {

	AllItemsListResponse allItemsListResponse;
	RestTemplate restTemplate = new RestTemplate();

	@RequestMapping(value = "/addStation", method = RequestMethod.GET)
	public ModelAndView addStation(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/add_station");
		try {
			allItemsListResponse = restTemplate.getForObject(Constants.url + "getAllItems", AllItemsListResponse.class);

			List<Item> itemsList = new ArrayList<Item>();
			itemsList = allItemsListResponse.getItems();
			System.out.println("LIst of items" + itemsList.toString());

			model.addObject("itemsList", itemsList);

			List<Station> stationList = restTemplate.getForObject(Constants.url + "/getAllStationList", List.class);
			model.addObject("stationList", stationList);
			System.out.println("stationList" + stationList.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/getAllItemsForStation", method = RequestMethod.GET)
	public @ResponseBody List<Item> getAllItemsForStation() {

		System.out.println("hii");

		allItemsListResponse = restTemplate.getForObject(Constants.url + "getAllItems", AllItemsListResponse.class);

		List<Item> itemsList = new ArrayList<Item>();
		itemsList = allItemsListResponse.getItems();
		System.out.println("LIst of items" + itemsList.toString());

		return itemsList;
	}

	@RequestMapping(value = "/insertStation", method = RequestMethod.POST)
	public String insertStation(HttpServletRequest request, HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("masters/addEmployee");

		String stationId = request.getParameter("stationId");
		String stationNo = request.getParameter("stationNo");
		String itemIdlist[] = request.getParameterValues("itemIdlist");

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < itemIdlist.length; i++) {
			sb = sb.append(itemIdlist[i] + ",");

		}
		String items = sb.toString();
		items = items.substring(0, items.length() - 1);

		Station station = new Station();

		if (stationId == "" || stationId == null)
			station.setStationId(0);
		else
			station.setStationId(Integer.parseInt(stationId));
		station.setDelStatus(0);
		station.setExInt1(1);
		station.setExInt2(1);
		station.setExVar1("NA");
		station.setExVar2("NA");
		station.setIsUsed(1);
		station.setItemId(items);
		station.setStationNo(stationNo);

		System.out.println("station" + station);

		Station res = restTemplate.postForObject(Constants.url + "/saveStation", station, Station.class);

		System.out.println("res " + res);

		return "redirect:/addStation";
	}

	@RequestMapping(value = "/editStation/{stationId}", method = RequestMethod.GET)
	public ModelAndView editStation(@PathVariable int stationId) {
		ModelAndView model = new ModelAndView("masters/add_station");

		allItemsListResponse = restTemplate.getForObject(Constants.url + "getAllItems", AllItemsListResponse.class);

		List<Item> itemsList = new ArrayList<Item>();
		itemsList = allItemsListResponse.getItems();
		System.out.println("LIst of items" + itemsList.toString());

		try {
			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("stationId", stationId);

			Station editStation = restTemplate.postForObject(Constants.url + "getStationByStationId", map,
					Station.class);

			List<Integer> itemids = Stream.of(editStation.getItemId().split(",")).map(Integer::parseInt)
					.collect(Collectors.toList());
			List<Item> nonSelected = new ArrayList<Item>();
			nonSelected.addAll(itemsList);
			List<Item> selected = new ArrayList<Item>();
			if (itemids.size() > 0) {
				for (int i = 0; i < itemids.size(); i++) {

					for (int j = 0; j < itemsList.size(); j++) {
						if (itemsList.get(j).getId() == itemids.get(i)) {
							selected.add(itemsList.get(j));
							nonSelected.remove(itemsList.get(j));
						}
					}

				}
			}
			model.addObject("nonSelectedItems", nonSelected);
			model.addObject("selectedItems", selected);
			model.addObject("itemsList", itemsList);

			model.addObject("editStation", editStation);
			model.addObject("isEdit", 1);

			List<Station> stationList = restTemplate.getForObject(Constants.url + "/getAllStationList", List.class);
			model.addObject("stationList", stationList);
			System.out.println("stationList" + stationList.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;

	}

	@RequestMapping(value = "/deleteStation/{stationId}", method = RequestMethod.GET)

	public String deleteCustomer(@PathVariable("stationId") int stationId) {

		try {
			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("stationId", stationId);

			Info editStation = restTemplate.postForObject(Constants.url + "deleteStation", map, Info.class);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "redirect:/addStation";
	}

	AllFrIdNameList allFrIdNameList = new AllFrIdNameList();

	// ----------------------------Show Dispatch Item
	// List-----------------------------
	@RequestMapping(value = "/showStationReport", method = RequestMethod.GET)
	public ModelAndView showStationReport(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/stationReport");
		String todaysDate;
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

			List<Station> stationList = restTemplate.getForObject(Constants.url + "/getAllStationList", List.class);
			model.addObject("stationList", stationList);
			System.out.println("stationList" + stationList.toString());

			model.addObject("stationList", stationList);

			model.addObject("todaysDate", todaysDate);
			model.addObject("unSelectedFrList", allFrIdNameList.getFrIdNamesList());

			model.addObject("routeList", routeList);

		} catch (Exception e) {

			System.out.println("Exc in show    " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}
//

	@RequestMapping(value = "/getStationReportByRoute", method = RequestMethod.GET)
	public @ResponseBody DispatchReportList getStationReportByRoute(HttpServletRequest request,
			HttpServletResponse response) {

		List<DispatchReport> dispatchReportList = new ArrayList<DispatchReport>();
		DispatchReportList dispatchReports = new DispatchReportList();
		try {
			System.out.println("Inside get Station Report");
			String billDate = request.getParameter("bill_date");
			String routeId = request.getParameter("route_id");
			String selectedStation = request.getParameter("station_id_list");

			boolean isAllStationSelected = false;
			String selectedFr = null;

			if (selectedStation.contains("-1")) {
				isAllStationSelected = true;
			} else {
				selectedStation = selectedStation.substring(1, selectedStation.length() - 1);
				selectedStation = selectedStation.replaceAll("\"", "");
				System.out.println("selectedCat" + selectedStation.toString());
			}
			List<String> staList = new ArrayList<>();
			staList = Arrays.asList(selectedStation);

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

			map = new LinkedMultiValueMap<String, Object>();

			allFrIdNameList = new AllFrIdNameList();
			try {

				allFrIdNameList = restTemplate.getForObject(Constants.url + "getAllFrIdName", AllFrIdNameList.class);

			} catch (Exception e) {
				System.out.println("Exception in getAllFrIdName" + e.getMessage());
				e.printStackTrace();

			}

			if (isAllStationSelected) {
				map = new LinkedMultiValueMap<String, Object>();

				Station[] usrArray = restTemplate.getForObject(Constants.url + "getStationList", Station[].class);
				List<Station> stationList = new ArrayList<Station>(Arrays.asList(usrArray));

				System.out.println("stationList" + stationList.toString());

				// List<Integer> cateList=new ArrayList<>();
				StringBuilder cateList = new StringBuilder();

				for (Station sList : stationList) {
					// cateList.add(mCategoryList.getCatId());
					cateList = cateList.append(sList.getItemId().toString() + ",");
				}

				String catlist = cateList.toString();
				selectedStation = catlist.substring(0, catlist.length() - 1);
				System.out.println("cateList" + selectedStation.toString());
				System.out.println("selectedFr" + selectedFr.toString());
				System.out.println("billDate" + billDate.toString());

				map.add("itemIdList", selectedStation);
				map.add("billDate", billDate);
				map.add("frId", selectedFr);

				ParameterizedTypeReference<List<DispatchReport>> typeRef = new ParameterizedTypeReference<List<DispatchReport>>() {
				};

				ResponseEntity<List<DispatchReport>> responseEntity = restTemplate.exchange(
						Constants.url + "getStationItemReport", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				dispatchReportList = responseEntity.getBody();
				System.out.println("dispatchReportList = " + dispatchReportList.toString());

				map = new LinkedMultiValueMap<String, Object>();
				map.add("itemIdList", selectedStation);
				ParameterizedTypeReference<List<Item>> typeRef1 = new ParameterizedTypeReference<List<Item>>() {
				};

				ResponseEntity<List<Item>> responseEntity1 = restTemplate.exchange(
						Constants.url + "getItemsByItemIdForDisp", HttpMethod.POST, new HttpEntity<>(map), typeRef1);
				System.out.println("Items:" + responseEntity1.toString());

				dispatchReports.setDispatchReportList(dispatchReportList);
				dispatchReports.setFrList(frNameIdByRouteIdList);
				dispatchReports.setItemList(responseEntity1.getBody());

			} else {
				System.out.println("selectedStation" + selectedStation.toString());
				System.out.println("selectedFr" + selectedFr.toString());

				List<Integer> stationIdList = Stream.of(selectedStation.split(",")).map(Integer::parseInt)
						.collect(Collectors.toList());

				Station[] usrArray = restTemplate.getForObject(Constants.url + "getStationList", Station[].class);
				List<Station> stationList = new ArrayList<Station>(Arrays.asList(usrArray));

				StringBuilder listofItemId = new StringBuilder();

				for (int i = 0; i < stationList.size(); i++) {
					for (int j = 0; j < stationIdList.size(); j++) {
						// cateList.add(mCategoryList.getCatId());
						if (stationList.get(i).getStationId() == stationIdList.get(j)) {
							listofItemId = listofItemId.append(stationList.get(i).getItemId().toString() + ",");
						}
					}
				}

				String itemIdList = listofItemId.toString();
				itemIdList = itemIdList.substring(0, itemIdList.length() - 1);

				map.add("itemIdList", itemIdList);
				map.add("billDate", billDate);
				map.add("frId", selectedFr);

				ParameterizedTypeReference<List<DispatchReport>> typeRef = new ParameterizedTypeReference<List<DispatchReport>>() {
				};

				ResponseEntity<List<DispatchReport>> responseEntity = restTemplate.exchange(
						Constants.url + "getStationItemReport", HttpMethod.POST, new HttpEntity<>(map), typeRef);
				System.out.println("Items:" + responseEntity.toString());

				dispatchReportList = responseEntity.getBody();
				System.out.println("dispatchReportList = " + dispatchReportList.toString());

				map = new LinkedMultiValueMap<String, Object>();
				map.add("itemIdList", itemIdList);
				ParameterizedTypeReference<List<Item>> typeRef1 = new ParameterizedTypeReference<List<Item>>() {
				};

				ResponseEntity<List<Item>> responseEntity1 = restTemplate.exchange(
						Constants.url + "getItemsByItemIdForDisp", HttpMethod.POST, new HttpEntity<>(map), typeRef1);

				dispatchReports.setDispatchReportList(dispatchReportList);
				dispatchReports.setFrList(frNameIdByRouteIdList);
				dispatchReports.setItemList(responseEntity1.getBody());

			}

		} catch (Exception e) {
			System.out.println("get Dispatch Report Exception: " + e.getMessage());
			e.printStackTrace();

		}

		return dispatchReports;

	}

	@RequestMapping(value = "pdf/getStationReportPdf/{billDate}/{routeId}/{selectedStation}", method = RequestMethod.GET)
	public ModelAndView getStationReportPdf(@PathVariable String billDate, @PathVariable String routeId,
			@PathVariable String selectedStation, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("reports/stationReportPdf");
		RestTemplate restTemplate = new RestTemplate();

		List<DispatchReport> dispatchReportList = new ArrayList<DispatchReport>();
		List<Station> stationListRes = new ArrayList<Station>();
		try {
			System.out.println("Inside get Dispatch Report");

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

			if (selectedStation.contains("-1")) {
				isAllCatSelected = true;
			} else {

			}
			List<String> catList = new ArrayList<>();
			catList = Arrays.asList(selectedStation);

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

			if (selectedStation.contains("-1")) {
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

				Station[] usrArray = restTemplate.getForObject(Constants.url + "getStationList", Station[].class);
				List<Station> stationList = new ArrayList<Station>(Arrays.asList(usrArray));

				System.out.println("stationList" + stationList.toString());

				// List<Integer> cateList=new ArrayList<>();
				StringBuilder cateList = new StringBuilder();

				for (Station sList : stationList) {
					// cateList.add(mCategoryList.getCatId());
					cateList = cateList.append(sList.getItemId().toString() + ",");
					stationListRes.add(sList);
				}

				String catlist = cateList.toString();
				selectedStation = catlist.substring(0, catlist.length() - 1);
				System.out.println("cateList" + selectedStation.toString());
				System.out.println("selectedFr" + selectedFr.toString());
				System.out.println("billDate" + billDate.toString());

				map.add("itemIdList", selectedStation);
				map.add("billDate", billDate);
				map.add("frId", selectedFr);

				ParameterizedTypeReference<List<DispatchReport>> typeRef = new ParameterizedTypeReference<List<DispatchReport>>() {
				};

				ResponseEntity<List<DispatchReport>> responseEntity = restTemplate.exchange(
						Constants.url + "getStationItemReport", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				dispatchReportList = responseEntity.getBody();
				System.out.println("dispatchReportList = " + dispatchReportList.toString());

				map = new LinkedMultiValueMap<String, Object>();
				map.add("itemIdList", selectedStation);
				ParameterizedTypeReference<List<Item>> typeRef1 = new ParameterizedTypeReference<List<Item>>() {
				};

				ResponseEntity<List<Item>> responseEntity1 = restTemplate.exchange(
						Constants.url + "getItemsByItemIdForDispNew", HttpMethod.POST, new HttpEntity<>(map), typeRef1);
				System.out.println("Items:" + responseEntity1.toString());

				model.addObject("dispatchReportList", dispatchReportList);
				model.addObject("frList", frNameIdByRouteIdList);
				model.addObject("itemList", responseEntity1.getBody());

			} else {
				System.out.println("selectedCat" + selectedStation.toString());
				System.out.println("selectedFr" + selectedFr.toString());

				List<Integer> stationIdList = Stream.of(selectedStation.split(",")).map(Integer::parseInt)
						.collect(Collectors.toList());

				Station[] usrArray = restTemplate.getForObject(Constants.url + "getStationList", Station[].class);
				List<Station> stationList = new ArrayList<Station>(Arrays.asList(usrArray));

				StringBuilder listofItemId = new StringBuilder();

				for (int i = 0; i < stationList.size(); i++) {
					for (int j = 0; j < stationIdList.size(); j++) {
						// cateList.add(mCategoryList.getCatId());
						if (stationList.get(i).getStationId() == stationIdList.get(j)) {
							listofItemId = listofItemId.append(stationList.get(i).getItemId().toString() + ",");
							stationListRes.add(stationList.get(i));
						}
					}
				}

				String itemIdList = listofItemId.toString();
				itemIdList = itemIdList.substring(0, itemIdList.length() - 1);

				map.add("itemIdList", itemIdList);
				map.add("billDate", billDate);
				map.add("frId", selectedFr);

				ParameterizedTypeReference<List<DispatchReport>> typeRef = new ParameterizedTypeReference<List<DispatchReport>>() {
				};

				ResponseEntity<List<DispatchReport>> responseEntity = restTemplate.exchange(
						Constants.url + "getStationItemReport", HttpMethod.POST, new HttpEntity<>(map), typeRef);
				System.out.println("Items:" + responseEntity.toString());

				dispatchReportList = responseEntity.getBody();
				System.out.println("dispatchReportList = " + dispatchReportList.toString());

				map = new LinkedMultiValueMap<String, Object>();
				map.add("itemIdList", itemIdList);
				ParameterizedTypeReference<List<Item>> typeRef1 = new ParameterizedTypeReference<List<Item>>() {
				};

				ResponseEntity<List<Item>> responseEntity1 = restTemplate.exchange(
						Constants.url + "getItemsByItemIdForDispNew", HttpMethod.POST, new HttpEntity<>(map), typeRef1);

				model.addObject("dispatchReportList", dispatchReportList);
				model.addObject("frList", frNameIdByRouteIdList);
				model.addObject("itemList", responseEntity1.getBody());

			}
			model.addObject("routeName", routeName);
			model.addObject("stationListRes", stationListRes);
		} catch (Exception e) {
			System.out.println("get Dispatch Report Exception: " + e.getMessage());
			e.printStackTrace();

		}
		return model;

	}

}
