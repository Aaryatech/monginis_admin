package com.ats.adminpanel.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.model.AllFrIdName;
import com.ats.adminpanel.model.AllFrIdNameList;
import com.ats.adminpanel.model.AllRoutesListResponse;
import com.ats.adminpanel.model.DispatchReport;
import com.ats.adminpanel.model.DispatchReportList;
import com.ats.adminpanel.model.Orders;
import com.ats.adminpanel.model.Route;
import com.ats.adminpanel.model.catstock.CategoryStockDAO;
import com.ats.adminpanel.model.catstock.CategoryWiseOrderData;
import com.ats.adminpanel.model.franchisee.AllMenuResponse;
import com.ats.adminpanel.model.franchisee.FrNameIdByRouteId;
import com.ats.adminpanel.model.franchisee.FrNameIdByRouteIdResponse;
import com.ats.adminpanel.model.franchisee.FranchiseeList;
import com.ats.adminpanel.model.franchisee.SubCategory;
import com.ats.adminpanel.model.item.AllItemsListResponse;
import com.ats.adminpanel.model.item.CategoryListResponse;
import com.ats.adminpanel.model.item.Item;
import com.ats.adminpanel.model.item.MCategoryList;

@Controller
@Scope("session")
public class CategoryStkUtilityController {

	RestTemplate restTemplate = new RestTemplate();

	@RequestMapping(value = "/showCategoryStock", method = RequestMethod.GET)
	public ModelAndView showCategoryStock(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("stock/catstockutility");
		try {
			AllFrIdNameList allFrIdNameList = new AllFrIdNameList();
			try {

				allFrIdNameList = restTemplate.getForObject(Constants.url + "getAllFrIdName", AllFrIdNameList.class);

			} catch (Exception e) {
				System.out.println("Exception in getAllFrIdName" + e.getMessage());
				e.printStackTrace();

			}
			AllMenuResponse allMenus = new AllMenuResponse();
			try {
				RestTemplate restTemplate = new RestTemplate();

				allMenus = restTemplate.getForObject(Constants.url + "getAllMenu", AllMenuResponse.class);

			} catch (Exception e) {
				System.out.println("Franchisee Controller Exception " + e.getMessage());
			}
			model.addObject("menuList", allMenus.getMenuConfigurationPage());

			AllRoutesListResponse allRouteListResponse = restTemplate.getForObject(Constants.url + "showRouteList",
					AllRoutesListResponse.class);

			List<Route> routeList = new ArrayList<Route>();

			routeList = allRouteListResponse.getRoute();
			model.addObject("routeList", routeList);
			/*
			 * CategoryListResponse categoryListResponse =
			 * restTemplate.getForObject(Constants.url + "showAllCategory",
			 * CategoryListResponse.class); List<MCategoryList> categoryList; categoryList =
			 * categoryListResponse.getmCategoryList();
			 */

			SubCategory[] subCatList = restTemplate.getForObject(Constants.url + "getAllSubCatList",
					SubCategory[].class);

			ArrayList<SubCategory> subCatAList = new ArrayList<SubCategory>(Arrays.asList(subCatList));

			System.out.println(" Fr " + allFrIdNameList.getFrIdNamesList());
			String allSubCats = "";
			for (int i = 0; i < subCatAList.size(); i++) {
				allSubCats = allSubCats + "," + subCatAList.get(i).getSubCatId();
			}
			allSubCats = allSubCats.substring(1);
			String allFrs = "";
			for (int j = 0; j < allFrIdNameList.getFrIdNamesList().size(); j++) {
				allFrs = allFrs + "," + allFrIdNameList.getFrIdNamesList().get(j).getFrId();
			}
			allFrs = allFrs.substring(1);
			model.addObject("subCatList", subCatList);
			model.addObject("allFrs", allFrs);
			model.addObject("allSubCats", allSubCats);
			String pattern = "dd-MM-yyyy";
			String dateInString = new SimpleDateFormat(pattern).format(new Date());
			model.addObject("todaysDate", dateInString);
			model.addObject("unSelectedFrList", allFrIdNameList.getFrIdNamesList());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	CategoryStockDAO categoryStockDAO = null;
	List<AllFrIdName> franchiseList = null;
	List<SubCategory> subCategoryList = null;
	List<CategoryWiseOrderData> categoryWiseOrderDataList = null;
	List<Item> itemsList = null;

	// getAllFrByRouteId

	@RequestMapping(value = "/getAllFrByRouteId", method = RequestMethod.GET)
	public @ResponseBody List<FrNameIdByRouteId> getAllFrByRouteId(HttpServletRequest request,
			HttpServletResponse response) {
		List<FrNameIdByRouteId> frList = null;
		try {
			int routeId = Integer.parseInt(request.getParameter("routeId"));
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			RestTemplate restTemplate = new RestTemplate();

			map.add("routeId", routeId);

			FrNameIdByRouteIdResponse frNameId = restTemplate.postForObject(Constants.url + "getFrNameIdByRouteId", map,
					FrNameIdByRouteIdResponse.class);
			frList = frNameId.getFrNameIdByRouteIds();
		} catch (Exception e) {

		}

		return frList;
	}

	@RequestMapping(value = "/searchCategoryStock", method = RequestMethod.GET)
	public @ResponseBody CategoryStockDAO searchCategoryStock(HttpServletRequest request,
			HttpServletResponse response) {

		categoryStockDAO = new CategoryStockDAO();
		try {
			itemsList = new ArrayList<Item>();
			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
			String selectedSubCat = request.getParameter("subcatidlist");
			String selectFr = request.getParameter("selectFr");

			selectedSubCat = selectedSubCat.substring(1, selectedSubCat.length() - 1);
			selectedSubCat = selectedSubCat.replaceAll("\"", "");

			selectFr = selectFr.substring(1, selectFr.length() - 1);
			selectFr = selectFr.replaceAll("\"", "");
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			AllFrIdNameList allFrIdNameList = new AllFrIdNameList();
			try {

				allFrIdNameList = restTemplate.getForObject(Constants.url + "getAllFrIdName", AllFrIdNameList.class);

			} catch (Exception e) {
				e.printStackTrace();
			}
			SubCategory[] subCatList = restTemplate.getForObject(Constants.url + "getAllSubCatList",
					SubCategory[].class);

			ArrayList<SubCategory> subCatAList = new ArrayList<SubCategory>(Arrays.asList(subCatList));

			List<Integer> frids = Stream.of(selectFr.split(",")).map(Integer::parseInt).collect(Collectors.toList());

			List<Integer> subCatIds = Stream.of(selectedSubCat.split(",")).map(Integer::parseInt)
					.collect(Collectors.toList());

			franchiseList = new ArrayList<>();
			for (int i = 0; i < allFrIdNameList.getFrIdNamesList().size(); i++) {
				for (int j = 0; j < frids.size(); j++) {
					if (frids.get(j) == allFrIdNameList.getFrIdNamesList().get(i).getFrId()) {
						franchiseList.add(allFrIdNameList.getFrIdNamesList().get(i));
					}
				}
			}
			subCategoryList = new ArrayList<>();
			for (int k = 0; k < subCatAList.size(); k++) {
				for (int l = 0; l < subCatIds.size(); l++) {
					if (subCatIds.get(l) == subCatAList.get(k).getSubCatId()) {
						subCategoryList.add(subCatAList.get(k));
					}
				}
			}

			AllItemsListResponse allItemsListResponse = restTemplate.getForObject(Constants.url + "getAllItems",
					AllItemsListResponse.class);

			itemsList = allItemsListResponse.getItems();

			map.add("subCatList", selectedSubCat);
			map.add("fromDate", fromDate);
			map.add("toDate", toDate);
			map.add("frId", selectFr);

			ParameterizedTypeReference<List<CategoryWiseOrderData>> typeRef = new ParameterizedTypeReference<List<CategoryWiseOrderData>>() {
			};

			ResponseEntity<List<CategoryWiseOrderData>> responseEntity = restTemplate.exchange(
					Constants.url + "getCategoryWiseOrderDataReport", HttpMethod.POST, new HttpEntity<>(map), typeRef);

			categoryWiseOrderDataList = responseEntity.getBody();
			System.out.println("categoryWiseOrderDataList = " + categoryWiseOrderDataList.toString());

			categoryStockDAO.setFrList(franchiseList);
			categoryStockDAO.setSubCatList(subCategoryList);
			categoryStockDAO.setItemList(itemsList);
			categoryStockDAO.setCategoryWiseOrderDataList(categoryWiseOrderDataList);

			System.err.println("categoryStockDAO" + categoryStockDAO.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categoryStockDAO;

	}

	@RequestMapping(value = "/calcCategoryStock", method = RequestMethod.GET)
	public @ResponseBody List<CategoryWiseOrderData> calcCategoryStock(HttpServletRequest request,
			HttpServletResponse response) {

		List<CategoryWiseOrderData> categoryWiseOrderDataList = null;
		try {
			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
			String selectFr = request.getParameter("selectFr");
			int itemId = Integer.parseInt(request.getParameter("itemId"));
			selectFr = selectFr.substring(1, selectFr.length() - 1);
			selectFr = selectFr.replaceAll("\"", "");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("itemId", itemId);
			map.add("fromDate", fromDate);
			map.add("toDate", toDate);
			map.add("frId", selectFr);

			ParameterizedTypeReference<List<CategoryWiseOrderData>> typeRef = new ParameterizedTypeReference<List<CategoryWiseOrderData>>() {
			};

			ResponseEntity<List<CategoryWiseOrderData>> responseEntity = restTemplate.exchange(
					Constants.url + "getCategoryWiseItemOrderDataReport", HttpMethod.POST, new HttpEntity<>(map),
					typeRef);

			categoryWiseOrderDataList = responseEntity.getBody();

			System.err.println("categoryWiseOrderDataList" + categoryWiseOrderDataList.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return categoryWiseOrderDataList;
	}

	@RequestMapping(value = "/submitCatOrder", method = RequestMethod.POST)
	public String submitCatOrder(HttpServletRequest request, HttpServletResponse response) {

		List<Orders> orderListResponse = new ArrayList<>();
		List<Orders> orderList = new ArrayList<>();
		try {
			int menuId = Integer.parseInt(request.getParameter("menu"));
			for (int i = 0; i < subCategoryList.size(); i++) {
				int itemId = Integer.parseInt(request.getParameter("items" + subCategoryList.get(i).getSubCatId()));
				System.out.println("itemId" + itemId);

				for (int j = 0; j < franchiseList.size(); j++) {

					int frId = franchiseList.get(j).getFrId();
					int qty = Integer.parseInt(
							request.getParameter("orderQty" + frId + "" + subCategoryList.get(j).getSubCatId()));
					System.out.println("qty" + qty);

					// System.out.println("menuId"+menuId);

					RestTemplate restTemplate = new RestTemplate();
					MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
					map.add("id", itemId);

					Item item = restTemplate.postForObject("" + Constants.url + "getItem", map, Item.class);
					System.out.println("ItemResponse" + item);
					map = new LinkedMultiValueMap<String, Object>();

					map.add("frId", frId);

					FranchiseeList franchiseeList = restTemplate
							.getForObject(Constants.url + "getFranchisee?frId={frId}", FranchiseeList.class, frId);
					System.out.println("franchiseeList" + franchiseeList.toString());

					Orders order = new Orders();

					if (franchiseeList.getFrRateCat() == 1) {
						order.setOrderRate(item.getItemRate1());
						order.setOrderMrp(item.getItemMrp1());
					} else if (franchiseeList.getFrRateCat() == 2) {
						order.setOrderRate(item.getItemRate2());
						order.setOrderMrp(item.getItemMrp2());
					} else {
						order.setOrderRate(item.getItemRate3());
						order.setOrderMrp(item.getItemMrp3());
					}
					int frGrnTwo = franchiseeList.getGrnTwo();
					System.err.println("frGrnTwo" + frGrnTwo + "item.getGrnTwo()" + item.getGrnTwo());
					if (item.getGrnTwo() == 1) {

						if (frGrnTwo == 1) {

							order.setGrnType(1);

						} else {

							order.setGrnType(0);
						}
					} // end of if

					else {
						if (item.getGrnTwo() == 2) {
							order.setGrnType(2);

						} else {
							order.setGrnType(0);
						}
					} // end of else
					if (menuId == 29 || menuId == 30 || menuId == 42 || menuId == 43 || menuId == 44 || menuId == 47) {

						order.setGrnType(3);

					}
					// for push grn
					if (menuId == 48) {

						order.setGrnType(4);
					}
					// Added--------------------------------------------------
					String dateStr = request.getParameter("delDate");
					SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date udate = sdf1.parse(dateStr);
					java.sql.Date deliveryDate = new java.sql.Date(udate.getTime());
					System.err.println("deliveryDate" + deliveryDate);
					// -----------------------------------------------------------------------------

					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					Calendar c = Calendar.getInstance();
					c.setTime(sdf.parse(dateStr));
					c.add(Calendar.DATE, -1); // number of days to add
					String dt = sdf.format(c.getTime());

					java.util.Date udatepd = sdf1.parse(dt);
					java.sql.Date pdDate = new java.sql.Date(udatepd.getTime());
					System.err.println("pdDate" + pdDate);

					Date today = new Date();
					java.sql.Date sqlCurrDate = new java.sql.Date(today.getTime());

					order.setOrderId(0);
					order.setItemId(String.valueOf(itemId));
					order.setItemName(item.getItemName() + "--[" + franchiseeList.getFrCode() + "]");
					order.setFrId(frId);

					order.setDeliveryDate(deliveryDate);
					order.setIsEdit(0);
					order.setEditQty(0);
					order.setIsPositive(1);
					order.setMenuId(menuId);
					order.setOrderDate(sqlCurrDate);
					order.setOrderDatetime("" + sqlCurrDate);
					order.setUserId(0);
					order.setOrderQty(qty);
					order.setOrderStatus(0);
					order.setOrderType(item.getItemGrp1());
					order.setOrderSubType(item.getItemGrp2());
					order.setProductionDate(pdDate);
					order.setRefId(itemId);

					orderList.add(order);
				}
			}

			if (orderList != null || !orderList.isEmpty()) {
				orderListResponse = restTemplate.postForObject(Constants.url + "placeOrder", orderList, List.class);
				orderList = new ArrayList<Orders>();
				System.out.println("Place Order Response" + orderListResponse.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/showCategoryStock";
	}
}
