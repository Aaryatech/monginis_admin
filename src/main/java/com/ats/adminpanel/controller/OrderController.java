package com.ats.adminpanel.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.spi.LocationAwareLogger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.commons.Commons;
import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.commons.DateConvertor;
import com.ats.adminpanel.model.AllRoutesListResponse;
import com.ats.adminpanel.model.ChangeOrderRecord;
import com.ats.adminpanel.model.ExportToExcel;
import com.ats.adminpanel.model.GetOrder;
import com.ats.adminpanel.model.GetOrderListResponse;
import com.ats.adminpanel.model.GetOrdersResponse;
import com.ats.adminpanel.model.GetRegSpCakeOrders;
import com.ats.adminpanel.model.GetSpCakeOrders;
import com.ats.adminpanel.model.GetSpCkOrder;
import com.ats.adminpanel.model.Info;
import com.ats.adminpanel.model.Order;
import com.ats.adminpanel.model.OrderItemSubCatTotal;
import com.ats.adminpanel.model.RegularSpCkOrder;
import com.ats.adminpanel.model.RegularSpCkOrdersResponse;
import com.ats.adminpanel.model.Route;
import com.ats.adminpanel.model.SpCakeOrdersBean;
import com.ats.adminpanel.model.SpCakeOrdersBeanResponse;
import com.ats.adminpanel.model.flavours.FlavourRawMaterialInfo;
import com.ats.adminpanel.model.franchisee.AllFranchiseeList;
import com.ats.adminpanel.model.franchisee.AllMenuResponse;
import com.ats.adminpanel.model.franchisee.FrNameIdByRouteId;
import com.ats.adminpanel.model.franchisee.FrNameIdByRouteIdResponse;
import com.ats.adminpanel.model.franchisee.FranchiseeList;
import com.ats.adminpanel.model.franchisee.Menu;
import com.ats.adminpanel.model.login.UserResponse;

@Controller
@Scope("session")
public class OrderController {
	public List<FranchiseeList> franchiseeList = new ArrayList<FranchiseeList>();
	public List<FranchiseeList> tempFrList = new ArrayList<FranchiseeList>();
	public List<FranchiseeList> selectedFrList = new ArrayList<FranchiseeList>();
	public List<SpCakeOrdersBean> spCakeOrderList = new ArrayList<SpCakeOrdersBean>();
	List<GetOrder> orderList = null;
	public List<Menu> menuList;
	SpCakeOrdersBeanResponse orderListResponse;
	RegularSpCkOrdersResponse regOrderListResponse;

	@RequestMapping(value = "/showOrders")
	public ModelAndView searchOrder(HttpServletRequest request, HttpServletResponse response) {

		// ModelAndView model=new ModelAndView("orders/orders");
		ModelAndView model = new ModelAndView("orders/orders");
		Constants.mainAct = 4;
		Constants.subAct = 27;

		try {
			RestTemplate restTemplate = new RestTemplate();
			AllFranchiseeList allFranchiseeList = restTemplate.getForObject(Constants.url + "getAllFranchisee",
					AllFranchiseeList.class);

			// franchiseeList= new ArrayList<FranchiseeList>();
			franchiseeList = allFranchiseeList.getFranchiseeList();

			model.addObject("franchiseeList", franchiseeList);
			model.addObject("allOtherFrList", tempFrList);
			model.addObject("selectedFrList", selectedFrList);
			model.addObject("date", new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
			RestTemplate restTemplate1 = new RestTemplate();

			AllMenuResponse allMenuResponse = restTemplate1.getForObject(Constants.url + "getAllMenu",
					AllMenuResponse.class);

			menuList = new ArrayList<Menu>();
			menuList = allMenuResponse.getMenuConfigurationPage();

			System.out.println("MENU LIST= " + menuList.toString());
			model.addObject("menuList", menuList);
			System.out.println("menu list is" + menuList.toString());

			AllRoutesListResponse allRouteListResponse = restTemplate.getForObject(Constants.url + "showRouteList",
					AllRoutesListResponse.class);

			List<Route> routeList = new ArrayList<Route>();

			routeList = allRouteListResponse.getRoute();
			model.addObject("routeList", routeList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;

	}

	@RequestMapping(value = "/searchOrdersProcess", method = RequestMethod.GET) // getOrderListForAllFr new web service
	public @ResponseBody GetOrdersResponse searchOrderProcess(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView model = new ModelAndView("orders/orders");

		GetOrdersResponse getOrdersResponse = new GetOrdersResponse();
		System.err.println("/inside search order process  ");
		// model.addObject("franchiseeList", franchiseeList);
		try {
			model.addObject("menuList", menuList);

			String menuId = request.getParameter("item_id_list");
			String frIdString = request.getParameter("fr_id_list");
			String date = request.getParameter("date");
			int routeId = Integer.parseInt(request.getParameter("route_id"));

			menuId = menuId.substring(1, menuId.length() - 1);
			menuId = menuId.replaceAll("\"", "");
			System.out.println("menu Ids New =" + menuId);

			frIdString = frIdString.substring(1, frIdString.length() - 1);
			frIdString = frIdString.replaceAll("\"", "");
			System.out.println("frIds  New =" + frIdString);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			orderList = new ArrayList<GetOrder>();

			List<String> franchIds = new ArrayList();
			franchIds = Arrays.asList(frIdString);

			System.out.println("fr Id ArrayList " + franchIds.toString());

			if (routeId != 0) {

				MultiValueMap<String, Object> mvm = new LinkedMultiValueMap<String, Object>();

				RestTemplate restTemplate = new RestTemplate();

				mvm.add("routeId", routeId);

				FrNameIdByRouteIdResponse frNameId = restTemplate.postForObject(Constants.url + "getFrNameIdByRouteId",
						mvm, FrNameIdByRouteIdResponse.class);

				List<FrNameIdByRouteId> frNameIdByRouteIdList = frNameId.getFrNameIdByRouteIds();

				System.out.println("route wise franchisee " + frNameIdByRouteIdList.toString());

				StringBuilder sbForRouteFrId = new StringBuilder();
				for (int i = 0; i < frNameIdByRouteIdList.size(); i++) {

					sbForRouteFrId = sbForRouteFrId.append(frNameIdByRouteIdList.get(i).getFrId().toString() + ",");

				}

				String strFrIdRouteWise = sbForRouteFrId.toString();
				frIdString = strFrIdRouteWise.substring(0, strFrIdRouteWise.length() - 1);
				System.out.println("fr Id Route WISE = " + frIdString);

			} else if (franchIds.contains("0")) {

				System.out.println("all fr selected");
				System.out.println("Date" + date);
				map.add("date", date);
				map.add("menuId", menuId);

				RestTemplate restTemplate1 = new RestTemplate();

				GetOrderListResponse orderListResponse = restTemplate1
						.postForObject(Constants.url + "getOrderListForAllFr", map, GetOrderListResponse.class);

				orderList = orderListResponse.getGetOder();
				getOrdersResponse.setOrderList(orderList);
				try {
					List<OrderItemSubCatTotal> orderItemSubCatTotalList = restTemplate1
							.postForObject(Constants.url + "getSubCatOrderTotalAllFr", map, List.class);
					getOrdersResponse.setOrderItemSubCatTotalList(orderItemSubCatTotalList);
				} catch (Exception e) {
					e.printStackTrace();
				}

				System.out.println("order list is " + orderList.toString());
				System.out.println("order list count is" + orderList.size());

				model.addObject("orderList", orderList);

				model.addObject("franchIds", franchIds);
				model.addObject("allOtherFrList", tempFrList);
				model.addObject("selectedFrList", selectedFrList);
				model.addObject("franchiseeList", franchiseeList);

				System.out.println("Fr selected all " + franchIds.toString());

			} // end of if

			if (!franchIds.contains("0") || routeId != 0) {

				System.out.println("few Fr selected: FrId  ArrayList " + franchIds.toString());

				System.out.println("few fra selected");

				map.add("frId", frIdString);
				map.add("menuId", menuId);
				map.add("date", date);

				RestTemplate restTemplate1 = new RestTemplate();

				GetOrderListResponse orderListResponse = restTemplate1.postForObject(Constants.url + "getOrderList",
						map, GetOrderListResponse.class);

				orderList = orderListResponse.getGetOder();
				getOrdersResponse.setOrderList(orderList);
				try {
					List<OrderItemSubCatTotal> orderItemSubCatTotalList = restTemplate1
							.postForObject(Constants.url + "getSubCatOrderTotal", map, List.class);
					getOrdersResponse.setOrderItemSubCatTotalList(orderItemSubCatTotalList);
				} catch (Exception e) {
					e.printStackTrace();
				}

				System.out.println("order list1 is " + getOrdersResponse.toString());
				System.out.println("order list count is" + orderList.size());
				model.addObject("orderList", orderList);
				model.addObject("franchiseeList", franchiseeList);

			} // end of else

			List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

			ExportToExcel expoExcel = new ExportToExcel();
			List<String> rowData = new ArrayList<String>();

			rowData.add("Franchisee Name");
			rowData.add("Type");
			rowData.add("Item Id");
			rowData.add("Item Name");
			rowData.add("Quantity");

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);
			for (int i = 0; i < orderList.size(); i++) {
				expoExcel = new ExportToExcel();
				rowData = new ArrayList<String>();

				rowData.add(orderList.get(i).getFrName());

				rowData.add(orderList.get(i).getCatName());
				rowData.add("" + orderList.get(i).getId());
				rowData.add(orderList.get(i).getItemName());
				rowData.add("" + orderList.get(i).getOrderQty());

				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);
			}

			HttpSession session = request.getSession();
			session.setAttribute("exportExcelList", exportToExcelList);
			session.setAttribute("excelName", "Orders");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return getOrdersResponse;
	}

	@RequestMapping(value = "/excelOrderByItem", method = RequestMethod.GET) // getOrderListForAllFr new web service
	public @ResponseBody List<GetOrder> excelOrderByItem(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("orders/orders");

		System.out.println("/inside search order process  ");
		// model.addObject("franchiseeList", franchiseeList);
		try {
			model.addObject("menuList", menuList);

			String menuId = request.getParameter("item_id_list");
			String frIdString = request.getParameter("fr_id_list");
			String date = request.getParameter("date");
			int routeId = Integer.parseInt(request.getParameter("route_id"));

			menuId = menuId.substring(1, menuId.length() - 1);
			menuId = menuId.replaceAll("\"", "");
			System.out.println("menu Ids New =" + menuId);

			frIdString = frIdString.substring(1, frIdString.length() - 1);
			frIdString = frIdString.replaceAll("\"", "");
			System.out.println("frIds  New =" + frIdString);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			orderList = new ArrayList<GetOrder>();

			List<String> franchIds = new ArrayList();
			franchIds = Arrays.asList(frIdString);

			System.out.println("fr Id ArrayList " + franchIds.toString());

			if (routeId != 0) {

				MultiValueMap<String, Object> mvm = new LinkedMultiValueMap<String, Object>();

				RestTemplate restTemplate = new RestTemplate();

				mvm.add("routeId", routeId);

				FrNameIdByRouteIdResponse frNameId = restTemplate.postForObject(Constants.url + "getFrNameIdByRouteId",
						mvm, FrNameIdByRouteIdResponse.class);

				List<FrNameIdByRouteId> frNameIdByRouteIdList = frNameId.getFrNameIdByRouteIds();

				System.out.println("route wise franchisee " + frNameIdByRouteIdList.toString());

				StringBuilder sbForRouteFrId = new StringBuilder();
				for (int i = 0; i < frNameIdByRouteIdList.size(); i++) {

					sbForRouteFrId = sbForRouteFrId.append(frNameIdByRouteIdList.get(i).getFrId().toString() + ",");

				}

				String strFrIdRouteWise = sbForRouteFrId.toString();
				frIdString = strFrIdRouteWise.substring(0, strFrIdRouteWise.length() - 1);
				System.out.println("fr Id Route WISE = " + frIdString);

			} else if (franchIds.contains("0")) {

				System.out.println("all fr selected");
				System.out.println("Date" + date);
				map.add("date", date);
				map.add("menuId", menuId);

				RestTemplate restTemplate1 = new RestTemplate();

				GetOrderListResponse orderListResponse = restTemplate1
						.postForObject(Constants.url + "getOrderListForAllFrByOrder", map, GetOrderListResponse.class);

				orderList = orderListResponse.getGetOder();

				System.out.println("order list is " + orderList.toString());
				System.out.println("order list count is" + orderList.size());

				model.addObject("orderList", orderList);

				model.addObject("franchIds", franchIds);
				model.addObject("allOtherFrList", tempFrList);
				model.addObject("selectedFrList", selectedFrList);
				model.addObject("franchiseeList", franchiseeList);

				System.out.println("Fr selected all " + franchIds.toString());

			} // end of if

			if (!franchIds.contains("0") || routeId != 0) {

				System.out.println("few Fr selected: FrId  ArrayList " + franchIds.toString());

				System.out.println("few fra selected");

				map.add("frId", frIdString);
				map.add("menuId", menuId);
				map.add("date", date);

				RestTemplate restTemplate1 = new RestTemplate();

				GetOrderListResponse orderListResponse = restTemplate1
						.postForObject(Constants.url + "getOrderListByOrder", map, GetOrderListResponse.class);

				orderList = orderListResponse.getGetOder();

				System.out.println("order list is " + orderList.toString());
				System.out.println("order list count is" + orderList.size());
				model.addObject("orderList", orderList);
				model.addObject("franchiseeList", franchiseeList);

			} // end of else

			List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

			ExportToExcel expoExcel = new ExportToExcel();
			List<String> rowData = new ArrayList<String>();

			rowData.add("Franchisee Name");
			rowData.add("Type");
			rowData.add("Item Id");
			rowData.add("Item Name");
			rowData.add("Quantity");

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);
			for (int i = 0; i < orderList.size(); i++) {
				expoExcel = new ExportToExcel();
				rowData = new ArrayList<String>();

				rowData.add(orderList.get(i).getFrName());

				rowData.add(orderList.get(i).getCatName());
				rowData.add("" + orderList.get(i).getId());
				rowData.add(orderList.get(i).getItemName());
				rowData.add("" + orderList.get(i).getOrderQty());

				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);
				System.out.println("List" + orderList.get(i).toString());
			}

			HttpSession session = request.getSession();
			session.setAttribute("exportExcelList", exportToExcelList);
			session.setAttribute("excelName", "Orders");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return orderList;
	}
	// special cake orders

	@RequestMapping(value = "/spCakeOrders")
	public ModelAndView searchSpCakeOrder(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("orders/spcakeorders");
		Constants.mainAct = 4;
		Constants.subAct = 28;
		try {
			RestTemplate restTemplate = new RestTemplate();
			AllFranchiseeList allFranchiseeList = restTemplate.getForObject(Constants.url + "getAllFranchisee",
					AllFranchiseeList.class);

			// franchiseeList= new ArrayList<FranchiseeList>();
			franchiseeList = allFranchiseeList.getFranchiseeList();
			AllRoutesListResponse allRouteListResponse = restTemplate.getForObject(Constants.url + "showRouteList",
					AllRoutesListResponse.class);

			List<Route> routeList = new ArrayList<Route>();

			routeList = allRouteListResponse.getRoute();
			model.addObject("routeList", routeList);

			model.addObject("todayDate", new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
			model.addObject("franchiseeList", franchiseeList);
			
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}
	
	//------ANMOL 13-7-2019---------------------------
	@RequestMapping(value = "/spCakeAlbumOrders")
	public ModelAndView searchSpCakeAlbumOrder(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("orders/spCakeAlbumOrders");
		Constants.mainAct = 4;
		Constants.subAct = 28;
		try {
			RestTemplate restTemplate = new RestTemplate();
			AllFranchiseeList allFranchiseeList = restTemplate.getForObject(Constants.url + "getAllFranchisee",
					AllFranchiseeList.class);

			// franchiseeList= new ArrayList<FranchiseeList>();
			franchiseeList = allFranchiseeList.getFranchiseeList();
			AllRoutesListResponse allRouteListResponse = restTemplate.getForObject(Constants.url + "showRouteList",
					AllRoutesListResponse.class);

			List<Route> routeList = new ArrayList<Route>();

			routeList = allRouteListResponse.getRoute();
			model.addObject("routeList", routeList);

			model.addObject("todayDate", new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
			model.addObject("franchiseeList", franchiseeList);
			AllMenuResponse allMenuResponse = restTemplate.getForObject(Constants.url + "getAllMenu",
					AllMenuResponse.class);

			menuList = new ArrayList<Menu>();
			menuList = allMenuResponse.getMenuConfigurationPage();

			System.out.println("MENU LIST= " + menuList.toString());
			model.addObject("frMenuList", menuList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}
	

	@RequestMapping(value = "/regularSpCakeOrderProcess")
	public ModelAndView regularSpCakeOrderProcess(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("orders/regularsporders");
		Constants.mainAct = 4;
		Constants.subAct = 29;
		try {

			RestTemplate restTemplate = new RestTemplate();
			AllFranchiseeList allFranchiseeList = restTemplate.getForObject(Constants.url + "getAllFranchisee",
					AllFranchiseeList.class);

			// franchiseeList= new ArrayList<FranchiseeList>();
			franchiseeList = allFranchiseeList.getFranchiseeList();
			AllRoutesListResponse allRouteListResponse = restTemplate.getForObject(Constants.url + "showRouteList",
					AllRoutesListResponse.class);

			List<Route> routeList = new ArrayList<Route>();

			routeList = allRouteListResponse.getRoute();
			model.addObject("routeList", routeList);

			model.addObject("todayDate", new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
			model.addObject("franchiseeList", franchiseeList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/spCakeOrderProcess", method = RequestMethod.GET)
	public @ResponseBody List<SpCakeOrdersBean> spCakeOrderProcess(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView model = null;
		System.out.println("/inside search sp cake order process  ");
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		RestTemplate restTemplate1 = new RestTemplate();
		spCakeOrderList = new ArrayList<SpCakeOrdersBean>();
		/* try { */
		model = new ModelAndView("orders/spcakeorders");
		model.addObject("franchiseeList", franchiseeList);

		String frIdString = request.getParameter("fr_id_list");
		String prodDate = request.getParameter("prod_date");
		int routeId = Integer.parseInt(request.getParameter("route_id"));
		List<String> franchIds = new ArrayList();

		if (frIdString != null) {
			frIdString = frIdString.substring(1, frIdString.length() - 1);
			frIdString = frIdString.replaceAll("\"", "");
			System.out.println("frIds  New =" + frIdString);

			franchIds = Arrays.asList(frIdString);
		}
		System.out.println("fr Id ArrayList " + franchIds.toString());
		if (routeId != 0) {

			MultiValueMap<String, Object> mvm = new LinkedMultiValueMap<String, Object>();

			RestTemplate restTemplate = new RestTemplate();

			mvm.add("routeId", routeId);

			FrNameIdByRouteIdResponse frNameId = restTemplate.postForObject(Constants.url + "getFrNameIdByRouteId", mvm,
					FrNameIdByRouteIdResponse.class);

			List<FrNameIdByRouteId> frNameIdByRouteIdList = frNameId.getFrNameIdByRouteIds();

			System.out.println("route wise franchisee " + frNameIdByRouteIdList.toString());

			StringBuilder sbForRouteFrId = new StringBuilder();
			for (int i = 0; i < frNameIdByRouteIdList.size(); i++) {

				sbForRouteFrId = sbForRouteFrId.append(frNameIdByRouteIdList.get(i).getFrId().toString() + ",");

			}

			String strFrIdRouteWise = sbForRouteFrId.toString();
			frIdString = strFrIdRouteWise.substring(0, strFrIdRouteWise.length() - 1);
			System.out.println("fr Id Route WISE = " + frIdString);
			map = new LinkedMultiValueMap<String, Object>();

			map.add("frId", frIdString);
			map.add("prodDate", prodDate);

			SpCakeOrdersBeanResponse orderListResponse = restTemplate1
					.postForObject(Constants.url + "getSpCakeOrderLists", map, SpCakeOrdersBeanResponse.class); // s
																												// added

			spCakeOrderList = orderListResponse.getSpCakeOrdersBean();
		/*	
			for(int i=0;i<spCakeOrderList.size();i++)
			{
				String[] arryOfStr=spCakeOrderList.get(i).getItemId().split("#",3);
				spCakeOrderList.get(i).setName(arryOfStr[]);
			}*/
			model.addObject("spCakeOrderList", spCakeOrderList);

		} else

		if (franchIds.contains("0")) {
			System.out.println("all fr selected");

			map.add("prodDate", prodDate);

			orderListResponse = restTemplate1.postForObject(Constants.url + "getAllFrSpCakeOrderList", map,
					SpCakeOrdersBeanResponse.class);

			spCakeOrderList = orderListResponse.getSpCakeOrdersBean();

			System.out.println("order list is" + spCakeOrderList.toString());
			System.out.println("order list count is" + spCakeOrderList.size());

			model.addObject("spCakeOrderList", spCakeOrderList);

		} // end of if

		else {

			System.out.println("few fr selected" + frIdString.toString());

			map.add("frId", frIdString);
			map.add("prodDate", prodDate);

			SpCakeOrdersBeanResponse orderListResponse = restTemplate1
					.postForObject(Constants.url + "getSpCakeOrderLists", map, SpCakeOrdersBeanResponse.class); // s
																												// added

			spCakeOrderList = orderListResponse.getSpCakeOrdersBean();
			System.out.println("order list is" + spCakeOrderList.toString());
			System.out.println("order list count is" + spCakeOrderList.size());
			model.addObject("spCakeOrderList", spCakeOrderList);

		} // end of else

		/*
		 * } catch (Exception e) { System.out.println("exception in order display" +
		 * e.getMessage()); }
		 */

		List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

		ExportToExcel expoExcel = new ExportToExcel();
		List<String> rowData = new ArrayList<String>();
		rowData.add("Order No");
		rowData.add("Franchisee Name");

		rowData.add("Item Id");
		rowData.add("Sp Code");
		rowData.add("Sp Name");
		rowData.add("Sp Flavour");

		rowData.add("Event");
		rowData.add("Price");
		rowData.add("Sp Total Add Rate");

		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);
		for (int i = 0; i < spCakeOrderList.size(); i++) {
			expoExcel = new ExportToExcel();
			rowData = new ArrayList<String>();
			rowData.add("" + spCakeOrderList.get(i).getSpOrderNo());
			rowData.add(spCakeOrderList.get(i).getFrName());

			rowData.add(spCakeOrderList.get(i).getItemId());
			rowData.add(spCakeOrderList.get(i).getSpCode());

			rowData.add(spCakeOrderList.get(i).getSpfName());
			rowData.add(spCakeOrderList.get(i).getSpName());
			rowData.add(spCakeOrderList.get(i).getSpEvents());
			rowData.add("" + spCakeOrderList.get(i).getSpPrice());
			rowData.add("" + spCakeOrderList.get(i).getSpTotalAddRate());

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);
			System.out.println("List" + spCakeOrderList.get(i).toString());
		}

		HttpSession session = request.getSession();
		session.setAttribute("exportExcelList", exportToExcelList);
		session.setAttribute("excelName", "SpCakeOrders");
		return spCakeOrderList;
	}
	
	
	//-----Anmol 13-7-2019----------------
	@RequestMapping(value = "/spCakeAlbumOrderProcess", method = RequestMethod.GET)
	public @ResponseBody List<SpCakeOrdersBean> spCakeAlbumOrderProcess(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView model = null;
		System.out.println("/inside search sp cake album order process  ");
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		RestTemplate restTemplate1 = new RestTemplate();
		spCakeOrderList = new ArrayList<SpCakeOrdersBean>();
		/* try { */
		model = new ModelAndView("orders/spCakeAlbumOrders");
		model.addObject("franchiseeList", franchiseeList);

		String frIdString = request.getParameter("fr_id_list");
		String prodDate = request.getParameter("prod_date");
		int routeId = Integer.parseInt(request.getParameter("route_id"));
		int spMenuId = Integer.parseInt(request.getParameter("spMenuId"));
		List<String> franchIds = new ArrayList();

		if (frIdString != null) {
			frIdString = frIdString.substring(1, frIdString.length() - 1);
			frIdString = frIdString.replaceAll("\"", "");
			System.out.println("frIds  New =" + frIdString);

			franchIds = Arrays.asList(frIdString);
		}
		System.out.println("fr Id ArrayList " + franchIds.toString());
		if (routeId != 0) {

			MultiValueMap<String, Object> mvm = new LinkedMultiValueMap<String, Object>();

			RestTemplate restTemplate = new RestTemplate();

			mvm.add("routeId", routeId);

			FrNameIdByRouteIdResponse frNameId = restTemplate.postForObject(Constants.url + "getFrNameIdByRouteId", mvm,
					FrNameIdByRouteIdResponse.class);

			List<FrNameIdByRouteId> frNameIdByRouteIdList = frNameId.getFrNameIdByRouteIds();

			System.out.println("route wise franchisee " + frNameIdByRouteIdList.toString());

			StringBuilder sbForRouteFrId = new StringBuilder();
			for (int i = 0; i < frNameIdByRouteIdList.size(); i++) {

				sbForRouteFrId = sbForRouteFrId.append(frNameIdByRouteIdList.get(i).getFrId().toString() + ",");

			}

			String strFrIdRouteWise = sbForRouteFrId.toString();
			frIdString = strFrIdRouteWise.substring(0, strFrIdRouteWise.length() - 1);
			System.out.println("fr Id Route WISE = " + frIdString);
			map = new LinkedMultiValueMap<String, Object>();

			map.add("frId", frIdString);
			map.add("prodDate", prodDate);
			map.add("spMenuId", spMenuId);
			SpCakeOrdersBeanResponse orderListResponse = restTemplate1
					.postForObject(Constants.url + "getSpCakeAlbumOrderLists", map, SpCakeOrdersBeanResponse.class); // s
																												// added

			spCakeOrderList = orderListResponse.getSpCakeOrdersBean();
			model.addObject("spCakeOrderList", spCakeOrderList);

		} else

		if (franchIds.contains("0")) {
			System.out.println("all fr selected");

			map.add("prodDate", prodDate);
			map.add("spMenuId", spMenuId);
			orderListResponse = restTemplate1.postForObject(Constants.url + "getAllFrSpCakeAlbumOrderList", map,
					SpCakeOrdersBeanResponse.class);

			spCakeOrderList = orderListResponse.getSpCakeOrdersBean();

			System.out.println("order list is" + spCakeOrderList.toString());
			System.out.println("order list count is" + spCakeOrderList.size());

			model.addObject("spCakeOrderList", spCakeOrderList);

		} // end of if

		else {

			System.out.println("few fr selected" + frIdString.toString());

			map.add("frId", frIdString);
			map.add("prodDate", prodDate);
			map.add("spMenuId", spMenuId);
			SpCakeOrdersBeanResponse orderListResponse = restTemplate1
					.postForObject(Constants.url + "getSpCakeAlbumOrderLists", map, SpCakeOrdersBeanResponse.class); // s
																												// added

			spCakeOrderList = orderListResponse.getSpCakeOrdersBean();
			System.out.println("order list is" + spCakeOrderList.toString());
			System.out.println("order list count is" + spCakeOrderList.size());
			model.addObject("spCakeOrderList", spCakeOrderList);

		} // end of else

		/*
		 * } catch (Exception e) { System.out.println("exception in order display" +
		 * e.getMessage()); }
		 */

		List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

		ExportToExcel expoExcel = new ExportToExcel();
		List<String> rowData = new ArrayList<String>();
		rowData.add("Order No");
		rowData.add("Franchisee Name");

		rowData.add("Item Id");
		rowData.add("Sp Code");
		rowData.add("Category");
		rowData.add("Sp Flavour");

		rowData.add("Event");
		rowData.add("Price");
		rowData.add("Sp Total Add Rate");

		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);
		for (int i = 0; i < spCakeOrderList.size(); i++) {
			expoExcel = new ExportToExcel();
			rowData = new ArrayList<String>();
			rowData.add("" + spCakeOrderList.get(i).getSpOrderNo());
			rowData.add(spCakeOrderList.get(i).getFrName());
			
			String itemCode="";
			try {
				
				String[] arr=spCakeOrderList.get(i).getItemId().split("#",3);
				itemCode=arr[1];
				
			}catch(Exception e) {
				e.printStackTrace();
				itemCode=spCakeOrderList.get(i).getItemId();
			}
			

			rowData.add(itemCode);
			rowData.add(spCakeOrderList.get(i).getSpCode());

			rowData.add(spCakeOrderList.get(i).getSpfName());
			rowData.add(spCakeOrderList.get(i).getSpName());
			rowData.add(spCakeOrderList.get(i).getSpEvents());
			rowData.add("" + spCakeOrderList.get(i).getSpPrice());
			rowData.add("" + spCakeOrderList.get(i).getSpTotalAddRate());

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);
			System.out.println("List" + spCakeOrderList.get(i).toString());
		}

		HttpSession session = request.getSession();
		session.setAttribute("exportExcelList", exportToExcelList);
		session.setAttribute("excelName", "SpCakeAlbumOrders");
		return spCakeOrderList;
	}
	
	
	
	

	boolean isDelete = false;
	public String[] frIds = null;
	public String prodDate = null;
	public int routeId = 0;

	@RequestMapping(value = "/regularSpCkOrderProcess", method = RequestMethod.POST)
	public ModelAndView regularSpCkOrderProcess(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = null;
		System.out.println("/inside search sp cake order process  ");

		model = new ModelAndView("orders/regularsporders");
		model.addObject("isDelete", 0);

		try {
			frIds = request.getParameterValues("fr_id[]");
			System.out.println("frIds:" + frIds);
			routeId = Integer.parseInt(request.getParameter("selectRoute"));
			System.out.println("routeId:" + routeId);
			prodDate = request.getParameter("prod_date");
			System.out.println("prodDate:" + prodDate);
			String strFrId = "";

			List<String> frIdList = new ArrayList<>();
			if (frIds != null) {
				frIdList = (List) Arrays.asList(frIds);

				StringBuilder sb = new StringBuilder();

				if (frIds.length > 0) {
					for (int i = 0; i < frIds.length; i++) {
						System.out.println("fr Ids List " + frIds[i]);

						sb = sb.append(frIds[i] + ",");
					}

					strFrId = sb.toString();
					strFrId = strFrId.substring(0, strFrId.length() - 1);
				}
			}
			System.out.println("frid array is=" + strFrId);
			RestTemplate restTemplate = new RestTemplate();

			List<FranchiseeList> selectedFrList = new ArrayList<>();
			List<FranchiseeList> remFrList = new ArrayList<FranchiseeList>();
			try {
				AllFranchiseeList allFranchiseeList = restTemplate.getForObject(Constants.url + "getAllFranchisee",
						AllFranchiseeList.class);

				franchiseeList = allFranchiseeList.getFranchiseeList();
				remFrList = franchiseeList;

				for (int i = 0; i < frIdList.size(); i++) {
					for (int j = 0; j < franchiseeList.size(); j++) {
						if (Integer.parseInt(frIdList.get(i)) == franchiseeList.get(j).getFrId()) {
							selectedFrList.add(franchiseeList.get(j));
							remFrList.remove(j);
						}
					}
				}
			} catch (NullPointerException e) {
				System.out.println("Null Pointer Exc in Reg Sp Order");
			} catch (Exception e) {
				System.out.println(" Exc in Reg Sp Order:order Controller" + e.getMessage());

			}
			AllRoutesListResponse allRouteListResponse = restTemplate.getForObject(Constants.url + "showRouteList",
					AllRoutesListResponse.class);

			List<Route> routeList = new ArrayList<Route>();

			routeList = allRouteListResponse.getRoute();
			model.addObject("routeList", routeList);
			model.addObject("routeId", routeId);
			model.addObject("todayDate", prodDate);
			model.addObject("frIdList", selectedFrList);
			model.addObject("franchiseeList", remFrList);

			if (routeId != 0) {

				MultiValueMap<String, Object> mvm = new LinkedMultiValueMap<String, Object>();

				mvm.add("routeId", routeId);

				FrNameIdByRouteIdResponse frNameId = restTemplate.postForObject(Constants.url + "getFrNameIdByRouteId",
						mvm, FrNameIdByRouteIdResponse.class);

				List<FrNameIdByRouteId> frNameIdByRouteIdList = frNameId.getFrNameIdByRouteIds();

				System.out.println("route wise franchisee " + frNameIdByRouteIdList.toString());

				StringBuilder sbForRouteFrId = new StringBuilder();
				for (int i = 0; i < frNameIdByRouteIdList.size(); i++) {

					sbForRouteFrId = sbForRouteFrId.append(frNameIdByRouteIdList.get(i).getFrId().toString() + ",");

				}

				String strFrIdRouteWise = sbForRouteFrId.toString();
				strFrId = strFrIdRouteWise.substring(0, strFrIdRouteWise.length() - 1);
				System.out.println("fr Id Route WISE = " + strFrId);

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();//

				map.add("frId", strFrId);
				map.add("prodDate", prodDate);

				RestTemplate restTemp = new RestTemplate();

				regOrderListResponse = restTemp.postForObject(Constants.url + "getRegSpCkOrderList", map,
						RegularSpCkOrdersResponse.class);

				List<RegularSpCkOrder> regularSpCkOrderList = new ArrayList<RegularSpCkOrder>();
				regularSpCkOrderList = regOrderListResponse.getRegularSpCkOrdersList();

				System.out.println("order list count is" + regularSpCkOrderList.size());
				model.addObject("regularSpCkOrderList", regularSpCkOrderList);
			} else if (frIds[0].toString().equals("0")) {
				System.out.println("all fr selected");
				model.addObject("frIdList", franchiseeList);

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				map.add("prodDate", prodDate);

				RestTemplate restTemplate1 = new RestTemplate();

				regOrderListResponse = restTemplate1.postForObject(Constants.url + "getAllFrRegSpCakeOrders", map,
						RegularSpCkOrdersResponse.class);

				List<RegularSpCkOrder> regularSpCkOrderList = new ArrayList<RegularSpCkOrder>();
				regularSpCkOrderList = regOrderListResponse.getRegularSpCkOrdersList();

				System.out.println("order list count is" + regularSpCkOrderList.toString());
				model.addObject("regularSpCkOrderList", regularSpCkOrderList);

			} // end of if
			else {

				System.out.println("few fr selected");

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();//

				map.add("frId", strFrId);
				map.add("prodDate", prodDate);

				RestTemplate restTemp = new RestTemplate();

				regOrderListResponse = restTemp.postForObject(Constants.url + "getRegSpCkOrderList", map,
						RegularSpCkOrdersResponse.class);

				List<RegularSpCkOrder> regularSpCkOrderList = new ArrayList<RegularSpCkOrder>();
				regularSpCkOrderList = regOrderListResponse.getRegularSpCkOrdersList();

				System.out.println("order list count is" + regularSpCkOrderList.size());
				model.addObject("regularSpCkOrderList", regularSpCkOrderList);

			} // end of else

		} catch (Exception e) {
			System.out.println("exception in order display" + e.getMessage());
		}

		List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

		ExportToExcel expoExcel = new ExportToExcel();
		List<String> rowData = new ArrayList<String>();

		rowData.add("Franchisee Name");

		rowData.add("Item Id");
		rowData.add("Item Name");

		rowData.add("Mrp");
		rowData.add("Rate");

		rowData.add("Quantity");

		rowData.add("Sub Total");

		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);
		for (int i = 0; i < regOrderListResponse.getRegularSpCkOrdersList().size(); i++) {
			expoExcel = new ExportToExcel();
			rowData = new ArrayList<String>();

			rowData.add(regOrderListResponse.getRegularSpCkOrdersList().get(i).getFrName());

			rowData.add("" + regOrderListResponse.getRegularSpCkOrdersList().get(i).getId());

			rowData.add(regOrderListResponse.getRegularSpCkOrdersList().get(i).getItemName());

			rowData.add("" + regOrderListResponse.getRegularSpCkOrdersList().get(i).getMrp());
			rowData.add("" + regOrderListResponse.getRegularSpCkOrdersList().get(i).getRate());
			rowData.add("" + regOrderListResponse.getRegularSpCkOrdersList().get(i).getQty());
			rowData.add("" + regOrderListResponse.getRegularSpCkOrdersList().get(i).getRspSubTotal());

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);

		}

		HttpSession session = request.getSession();
		session.setAttribute("exportExcelList", exportToExcelList);
		session.setAttribute("excelName", "RegSpCakeOrders");

		return model;
	}

	// ganesh 24-10-2017

	@RequestMapping(value = "/callDeleteOrder", method = RequestMethod.GET)
	public @ResponseBody List<GetOrder> deleteOrder(HttpServletRequest request, HttpServletResponse response) {

		try { 
			System.out.println("/inside delete order process  ");
			int orderId = Integer.parseInt(request.getParameter("order_id"));
			HttpSession session=request.getSession();
			UserResponse userResponse =(UserResponse) session.getAttribute("UserDetail");
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("orderId", orderId);
			RestTemplate restTemp = new RestTemplate();
			
				if (!orderList.isEmpty()) {
					for (int i = 0; i < orderList.size(); i++) {
						if (orderList.get(i).getOrderId().equals(orderId)) {
							DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							Calendar cal = Calendar.getInstance();
							String curDateTime = dateFormat.format(cal.getTime());
							Date date = new Date();
							String modifiedDate= new SimpleDateFormat("dd-MM-yyyy").format(date);
System.err.println("modifiedDate delete function " +modifiedDate);
							ChangeOrderRecord reqBody=new ChangeOrderRecord();
							reqBody.setDeliveryDate(new SimpleDateFormat("dd-MM-yyyy").format(orderList.get(i).getDeliveryDate()));
							reqBody.setChangeDate(modifiedDate);
							reqBody.setChangeId(0);
							reqBody.setChangeName("Deleted");
							reqBody.setChangeQty(orderList.get(i).getOrderQty());
							reqBody.setChangeType(0);
							reqBody.setDateTime(curDateTime);
							reqBody.setExVar1("na");
							reqBody.setFrId(0);
							reqBody.setFrName(orderList.get(i).getFrName());
							reqBody.setItemId(0);
							reqBody.setItemName(orderList.get(i).getItemName());
							reqBody.setOrderId(orderList.get(i).getOrderId());
							reqBody.setOrigQty(orderList.get(i).getOrderQty());
							
							reqBody.setUserId(userResponse.getUser().getId());
							reqBody.setUserName(userResponse.getUser().getUsername());
							
							
							
							ChangeOrderRecord orderChangeRes = restTemp.postForObject(Constants.url + "saveChangeOrderRecord", reqBody, ChangeOrderRecord.class);
							
							break;
							
						}
					}
				}
			Integer isDeleted = restTemp.postForObject(Constants.url + "DeleteOrder", map, Integer.class);

			
			if (isDeleted != 0) {
				if (!orderList.isEmpty()) {
					for (int i = 0; i < orderList.size(); i++) {
						if (orderList.get(i).getOrderId() == orderId) {
							orderList.remove(i);
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Exception In delete Order" + e.getMessage());
		}
		return orderList;
	}

	@RequestMapping(value = "/showModifiedOrders", method = RequestMethod.GET)
	public ModelAndView showModifiedOrders(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView model = new ModelAndView("orders/changed_orders");
		try {
		RestTemplate restTemplate = new RestTemplate();
		AllFranchiseeList allFranchiseeList = restTemplate.getForObject(Constants.url + "getAllFranchisee",
				AllFranchiseeList.class);

		// franchiseeList= new ArrayList<FranchiseeList>();
		franchiseeList = allFranchiseeList.getFranchiseeList();
		AllRoutesListResponse allRouteListResponse = restTemplate.getForObject(Constants.url + "showRouteList",
				AllRoutesListResponse.class);

		List<Route> routeList = new ArrayList<Route>();

		routeList = allRouteListResponse.getRoute();
		model.addObject("routeList", routeList);

		model.addObject("todayDate", new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
		model.addObject("franchiseeList", franchiseeList);
		}catch (Exception e) {
			System.err.println("Exce in /showModifiedOrders at OrderController ");
		}
		
		return model;
	}
	
	//-GET DATA for Deleted/Edited t_order Data Sachin 18-02-2020
	@RequestMapping(value = "/getModifiedOrdersData", method = RequestMethod.GET)
	public @ResponseBody  List<ChangeOrderRecord> getModifiedOrdersData(HttpServletRequest request, HttpServletResponse response) {
		
		MultiValueMap<String, Object> mvm = new LinkedMultiValueMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		String frIdString = request.getParameter("fr_id_list");
		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");
		int routeId = Integer.parseInt(request.getParameter("route_id"));
		fromDate=DateConvertor.convertToYMD(fromDate);
		toDate=DateConvertor.convertToYMD(toDate);
		
		List<String> franchIds = new ArrayList();

		if (frIdString != null) {
			frIdString = frIdString.substring(1, frIdString.length() - 1);
			frIdString = frIdString.replaceAll("\"", "");
			System.out.println("frIds  New =" + frIdString);

			franchIds = Arrays.asList(frIdString);
		}
		System.out.println("fr Id ArrayList " + franchIds.toString());
		if (routeId != 0) {

			mvm = new LinkedMultiValueMap<String, Object>();

			mvm.add("routeId", routeId);

			FrNameIdByRouteIdResponse frNameId = restTemplate.postForObject(Constants.url + "getFrNameIdByRouteId", mvm,
					FrNameIdByRouteIdResponse.class);

			List<FrNameIdByRouteId> frNameIdByRouteIdList = frNameId.getFrNameIdByRouteIds();

			System.out.println("route wise franchisee " + frNameIdByRouteIdList.toString());

			StringBuilder sbForRouteFrId = new StringBuilder();
			for (int i = 0; i < frNameIdByRouteIdList.size(); i++) {

				sbForRouteFrId = sbForRouteFrId.append(frNameIdByRouteIdList.get(i).getFrId().toString() + ",");

			}

			String strFrIdRouteWise = sbForRouteFrId.toString();
			frIdString = strFrIdRouteWise.substring(0, strFrIdRouteWise.length() - 1);
			System.out.println("fr Id Route WISE = " + frIdString);
			mvm = new LinkedMultiValueMap<String, Object>();

			mvm.add("frIdList", frIdString);
			mvm.add("fromDate", fromDate);
			mvm.add("toDate", toDate);

		} else

		if (franchIds.contains("-1")) {
			System.out.println("all fr selected");
			mvm = new LinkedMultiValueMap<String, Object>();
			mvm.add("frIdList", "-1");
			mvm.add("fromDate", fromDate);
			mvm.add("toDate", toDate);
		} // end of if
		else {

			System.out.println("few fr selected" + frIdString.toString());
			mvm = new LinkedMultiValueMap<String, Object>();
			
			mvm.add("frIdList", frIdString);
			mvm.add("fromDate", fromDate);
			mvm.add("toDate", toDate);
		} // end of else
System.err.println("Mvm map " +mvm);
		
List<ChangeOrderRecord> changeOrList = restTemplate
				.postForObject(Constants.url + "getChangedOrdersRecordList", mvm, List.class);
		
		return changeOrList;

		
	}
	
	@RequestMapping(value = "/deleteSpOrder", method = RequestMethod.GET)
	public @ResponseBody List<SpCakeOrdersBean> deleteSpOrder(HttpServletRequest request,
			HttpServletResponse response) {

		System.out.println("/inside delete Sporder process  ");

		int spOrderNo = Integer.parseInt(request.getParameter("sp_order_no"));

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("spOrderNo", spOrderNo);

		RestTemplate restTemp = new RestTemplate();

		Info info = restTemp.postForObject(Constants.url + "deleteSpCkOrder", map, Info.class);

		if (info.getError() == false) {
			if (!spCakeOrderList.isEmpty()) {
				for (int i = 0; i < spCakeOrderList.size(); i++) {
					if (spCakeOrderList.get(i).getSpOrderNo() == spOrderNo) {
						spCakeOrderList.remove(i);
					}
				}
			}
		}
		return spCakeOrderList;
	}
	
	
	
	

	@RequestMapping(value = "/deleteRegSpOrder/{rspId}", method = RequestMethod.GET)
	public ModelAndView deleteRegSpOrder(@PathVariable int rspId, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("orders/regularsporders");

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("rspId", rspId);

		RestTemplate restTemp = new RestTemplate();

		Info info = restTemp.postForObject(Constants.url + "deleteRegularSpOrder", map, Info.class);
		System.out.println("Info" + info.toString());

		List<String> frIdList = (List) Arrays.asList(frIds);

		RestTemplate restTemplate = new RestTemplate();

		List<FranchiseeList> selectedFrList = new ArrayList<>();
		List<FranchiseeList> remFrList = new ArrayList<FranchiseeList>();

		AllFranchiseeList allFranchiseeList = restTemplate.getForObject(Constants.url + "getAllFranchisee",
				AllFranchiseeList.class);

		franchiseeList = allFranchiseeList.getFranchiseeList();
		remFrList = franchiseeList;

		try {
			for (int i = 0; i < frIdList.size(); i++) {
				for (int j = 0; j < franchiseeList.size(); j++) {
					if (Integer.parseInt(frIdList.get(i)) == franchiseeList.get(j).getFrId()) {
						selectedFrList.add(franchiseeList.get(j));
						remFrList.remove(j);
					}
				}
			}
		} catch (NullPointerException e) {
			System.out.println("Null Pointer Exc in Reg Sp Order");
		} catch (Exception e) {
			System.out.println(" Exc in Reg Sp Order:order Controller" + e.getMessage());

		}

		model.addObject("todayDate", prodDate);
		model.addObject("frIdList", selectedFrList);
		model.addObject("franchiseeList", remFrList);
		model.addObject("isDelete", 1);
		return model;
	}

	@RequestMapping(value = "/callChangeQty", method = RequestMethod.GET)
	public @ResponseBody void updateOrderQty(HttpServletRequest request, HttpServletResponse response) {

		System.out.println("/inside Update order process  ");

		String orderId = request.getParameter("order_id");
		String orderQty = request.getParameter("order_qty");
		HttpSession session=request.getSession();
		UserResponse userResponse =(UserResponse) session.getAttribute("UserDetail");
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("orderId", orderId);
		map.add("orderQty", orderQty);

		RestTemplate restTemp = new RestTemplate();

		String s = restTemp.postForObject(Constants.url + "updateOrderQty", map, String.class);
		
		
		for (int i = 0; i < orderList.size(); i++) {
			if (orderList.get(i).getOrderId().equals(Integer.parseInt(orderId))) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();
				String curDateTime = dateFormat.format(cal.getTime());
				Date date = new Date();
				String modifiedDate= new SimpleDateFormat("dd-MM-yyyy").format(date);
                 System.err.println("modifiedDate callChangeQty"  +modifiedDate);
				ChangeOrderRecord reqBody=new ChangeOrderRecord();
				reqBody.setDeliveryDate(new SimpleDateFormat("dd-MM-yyyy").format(orderList.get(i).getDeliveryDate()));

				reqBody.setChangeDate(modifiedDate);
				reqBody.setChangeId(0);
				reqBody.setChangeName("Edited");
				reqBody.setChangeQty(Integer.parseInt(orderQty));
				reqBody.setChangeType(1);
				reqBody.setDateTime(curDateTime);
				reqBody.setExVar1("na");
				reqBody.setFrId(0);
				reqBody.setFrName(orderList.get(i).getFrName());
				reqBody.setItemId(0);
				reqBody.setItemName(orderList.get(i).getItemName());
				reqBody.setOrderId(orderList.get(i).getOrderId());
				reqBody.setOrigQty(orderList.get(i).getOrderQty());
				
				reqBody.setUserId(userResponse.getUser().getId());
				reqBody.setUserName(userResponse.getUser().getUsername());
				
				
				
				ChangeOrderRecord orderChangeRes = restTemp.postForObject(Constants.url + "saveChangeOrderRecord", reqBody, ChangeOrderRecord.class);
				
				break;
				
			}else {
				
			}
		}
	

		// return "Success";
	}

	@RequestMapping(value = "/showHtmlViewSpcakeOrder/{spOrderNo}", method = RequestMethod.GET)
	public ModelAndView showHtmlViewSpcakeOrder(@PathVariable("spOrderNo") int spOrderNo, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("orders/htmlViewSpCakeOrder");

		RestTemplate restTemp = new RestTemplate();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("spOrderNo", spOrderNo);
		List<GetSpCakeOrders> orderListResponse = restTemp.postForObject(Constants.url + "getSpCakeOrderBySpOrderNo",
				map, List.class);

		model.addObject("spCakeOrder", orderListResponse.get(0));
		return model;
	}
	
	@RequestMapping(value = "/showHtmlViewSpcakeAlbumOrder/{spOrderNo}", method = RequestMethod.GET)
	public ModelAndView showHtmlViewSpcakeAlbumOrder(@PathVariable("spOrderNo") int spOrderNo, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("orders/htmlViewSpCakeOrder");

		RestTemplate restTemp = new RestTemplate();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("spOrderNo", spOrderNo);
		List<GetSpCakeOrders> orderListResponse = restTemp.postForObject(Constants.url + "getSpCakeAlbumOrderBySpOrderNo",
				map, List.class);

		model.addObject("spCakeOrder", orderListResponse.get(0));
		return model;
	}

	@RequestMapping(value = "/showSpcakeOrderPdf/{spOrderNo}/{key}", method = RequestMethod.GET)
	public ModelAndView showSpcakeOrderPdf(@PathVariable("spOrderNo") int spOrderNo, @PathVariable("key") int key,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("orders/spCakeOrderPdf");

		RestTemplate restTemp = new RestTemplate();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("spOrderNo", spOrderNo);
		List<GetSpCkOrder> orderListResponse = restTemp.postForObject(Constants.url + "getSpCKOrderBySpOrderNo", map,
				List.class);

		model.addObject("from", key);
		System.out.println("SpOrder" + orderListResponse.toString());
		model.addObject("spCakeOrder", orderListResponse);
		model.addObject("imgUrl", Constants.SP_CAKE_FOLDER);
		model.addObject("imgUrl2", Constants.CUST_CHOICE_PHOTO_CAKE_FOLDER);
		return model;
	}
	
	//----------ANMOL 13-7-2019
	@RequestMapping(value = "/showSpcakeAlbumOrderPdf/{spOrderNo}/{key}", method = RequestMethod.GET)
	public ModelAndView showSpcakeAlbumOrderPdf(@PathVariable("spOrderNo") int spOrderNo, @PathVariable("key") int key,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("orders/spCakeOrderPdf");

		RestTemplate restTemp = new RestTemplate();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("spOrderNo", spOrderNo);
		List<GetSpCkOrder> orderListResponse = restTemp.postForObject(Constants.url + "getSpCKAlbumOrderBySpOrderNo", map,
				List.class);

		model.addObject("from", key);
		System.out.println("SpOrder" + orderListResponse.toString());
		model.addObject("spCakeOrder", orderListResponse);
		model.addObject("imgUrl", Constants.SP_CAKE_FOLDER);
		model.addObject("imgUrl2", Constants.CUST_CHOICE_PHOTO_CAKE_FOLDER);
		return model;
	}

	@RequestMapping(value = "/showSpcakeOrderPdfInRange/{from}/{to}", method = RequestMethod.GET)
	public ModelAndView showSpcakeOrderPdfInRange(@PathVariable("from") int from, @PathVariable("to") int to,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("orders/spCakeOrderPdf");

		RestTemplate restTemp = new RestTemplate();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		StringBuffer orderId = new StringBuffer("0,");
		for (int i = from - 1; i < to && i < spCakeOrderList.size(); i++) {
			orderId.append(Integer.toString(spCakeOrderList.get(i).getSpOrderNo()) + ",");
		}

		orderId.setLength(orderId.length() - 1);
		map.add("spOrderNo", orderId);
		List<GetSpCkOrder> orderListResponse = restTemp.postForObject(Constants.url + "getSpCKOrderBySpOrderNo", map,
				List.class);

		System.out.println("SpOrder" + orderListResponse.toString());
		model.addObject("spCakeOrder", orderListResponse);
		model.addObject("from", from);
		model.addObject("imgUrl", Constants.SP_CAKE_FOLDER);
		model.addObject("imgUrl2", Constants.CUST_CHOICE_PHOTO_CAKE_FOLDER);
		return model;
	}
	
	
	
	//------------ANMOL 13-7-2019------------------
	@RequestMapping(value = "/showSpcakeAlbumOrderPdfInRange/{from}/{to}", method = RequestMethod.GET)
	public ModelAndView showSpcakeAbumOrderPdfInRange(@PathVariable("from") int from, @PathVariable("to") int to,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("orders/spCakeOrderPdf");

		RestTemplate restTemp = new RestTemplate();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		StringBuffer orderId = new StringBuffer("0,");
		for (int i = from - 1; i < to && i < spCakeOrderList.size(); i++) {
			orderId.append(Integer.toString(spCakeOrderList.get(i).getSpOrderNo()) + ",");
		}

		orderId.setLength(orderId.length() - 1);
		map.add("spOrderNo", orderId);
		List<GetSpCkOrder> orderListResponse = restTemp.postForObject(Constants.url + "getSpCKAlbumOrderBySpOrderNo", map,
				List.class);

		System.out.println("SpOrder" + orderListResponse.toString());
		model.addObject("spCakeOrder", orderListResponse);
		model.addObject("from", from);
		model.addObject("imgUrl", Constants.SP_CAKE_FOLDER);
		model.addObject("imgUrl2", Constants.CUST_CHOICE_PHOTO_CAKE_FOLDER);
		return model;
	}
	
	 

	@RequestMapping(value = "/showHtmlViewRegSpcakeOrder/{orderNo}", method = RequestMethod.GET)
	public ModelAndView showHtmlViewRegSpcakeOrder(@PathVariable("orderNo") int orderNo, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("orders/htmlViewRegSpCakeOrder");

		RestTemplate restTemp = new RestTemplate();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("orderNo", orderNo);
		List<GetRegSpCakeOrders> orderListResponse = restTemp
				.postForObject(Constants.url + "getRegSpCakeOrderBySpOrderNo", map, List.class);

		model.addObject("regularSpCkOrdersList", orderListResponse.get(0));
		return model;
	}

	@RequestMapping(value = "/showRegSpcakeOrderPdf/{orderNo}", method = RequestMethod.GET)
	public ModelAndView showRegSpcakeOrderPdf(@PathVariable("orderNo") int orderNo, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView model = new ModelAndView("orders/regSpCakeOrderPdf");

		RestTemplate restTemp = new RestTemplate();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("orderNo", orderNo);
		List<GetRegSpCakeOrders> orderListResponse = restTemp
				.postForObject(Constants.url + "getRegSpCakeOrderBySpOrderNo", map, List.class);

		System.out.println("regularSpCkOrdersList" + orderListResponse.toString());
		model.addObject("regularSpCkOrdersList", orderListResponse);
		return model;
	}

	@RequestMapping(value = "/showRegSpcakeOrderPdfInRange/{from}/{to}", method = RequestMethod.GET)
	public ModelAndView showRegSpcakeOrderPdfInRange(@PathVariable("from") int from, @PathVariable("to") int to,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("orders/regSpCakeOrderPdf");

		RestTemplate restTemp = new RestTemplate();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		StringBuffer orderId = new StringBuffer("0,");

		for (int i = from - 1; i < to && i < regOrderListResponse.getRegularSpCkOrdersList().size(); i++) {
			orderId.append(Integer.toString(regOrderListResponse.getRegularSpCkOrdersList().get(i).getRspId()) + ",");
		}

		orderId.setLength(orderId.length() - 1);
		map.add("orderNo", orderId);
		List<GetRegSpCakeOrders> orderListResponse = restTemp
				.postForObject(Constants.url + "getRegSpCakeOrderBySpOrderNo", map, List.class);

		System.out.println("regularSpCkOrdersList" + orderListResponse.toString());
		model.addObject("regularSpCkOrdersList", orderListResponse);
		model.addObject("from", from);
		return model;
	}

	@RequestMapping(value = "/searchOrdersProcessByItem", method = RequestMethod.GET) // getOrderListForAllFr new web
																						// service
	public @ResponseBody GetOrderListResponse searchOrdersProcessByItem(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView model = new ModelAndView("orders/orders");

		GetOrderListResponse getOrdersResponse = new GetOrderListResponse();

		// System.out.println("/inside search order process ");
		// model.addObject("franchiseeList", franchiseeList);
		try {
			model.addObject("menuList", menuList);
			String itemId = request.getParameter("itemId");
			String menuId = request.getParameter("item_id_list");
			String frIdString = request.getParameter("fr_id_list");
			String date = request.getParameter("date");
			int routeId = 0;// Integer.parseInt(request.getParameter("route_id"));

			menuId = menuId.substring(1, menuId.length() - 1);
			menuId = menuId.replaceAll("\"", "");
			System.out.println("menu Ids New =" + menuId);

			frIdString = frIdString.substring(1, frIdString.length() - 1);
			frIdString = frIdString.replaceAll("\"", "");
			System.out.println("frIds  New =" + frIdString);

			itemId = itemId.substring(1, itemId.length() - 1);
			itemId = itemId.replaceAll("\"", "");
			System.out.println("Item Ids =" + itemId);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			orderList = new ArrayList<GetOrder>();

			List<String> franchIds = new ArrayList();
			franchIds = Arrays.asList(frIdString);

			System.out.println("fr Id ArrayList " + franchIds.toString());

			if (routeId != 0) {

				MultiValueMap<String, Object> mvm = new LinkedMultiValueMap<String, Object>();

				RestTemplate restTemplate = new RestTemplate();

				mvm.add("routeId", routeId);

				FrNameIdByRouteIdResponse frNameId = restTemplate.postForObject(Constants.url + "getFrNameIdByRouteId",
						mvm, FrNameIdByRouteIdResponse.class);

				List<FrNameIdByRouteId> frNameIdByRouteIdList = frNameId.getFrNameIdByRouteIds();

				System.out.println("route wise franchisee " + frNameIdByRouteIdList.toString());

				StringBuilder sbForRouteFrId = new StringBuilder();
				for (int i = 0; i < frNameIdByRouteIdList.size(); i++) {

					sbForRouteFrId = sbForRouteFrId.append(frNameIdByRouteIdList.get(i).getFrId().toString() + ",");

				}

				String strFrIdRouteWise = sbForRouteFrId.toString();
				frIdString = strFrIdRouteWise.substring(0, strFrIdRouteWise.length() - 1);
				System.out.println("fr Id Route WISE = " + frIdString);

			} else if (franchIds.contains("0")) {

				System.out.println("all fr selected");
				System.out.println("Date" + date);
				map.add("date", date);
				map.add("menuId", menuId);
				map.add("itemId", itemId);
				RestTemplate restTemplate1 = new RestTemplate();

				GetOrderListResponse orderListResponse = restTemplate1
						.postForObject(Constants.url + "getOrderListForAllFrAndItem", map, GetOrderListResponse.class);

				orderList = orderListResponse.getGetOder();

				getOrdersResponse.setGetOder(orderList);

				getOrdersResponse.setGetOder(orderList);
				try {

					map = new LinkedMultiValueMap<String, Object>();

					map.add("menuId", menuId);
					map.add("date", date);
					map.add("itemId", itemId);
					List<OrderItemSubCatTotal> orderItemSubCatTotalList = restTemplate1
							.postForObject(Constants.url + "getSubCatOrderTotalAllFrSpecItemId", map, List.class);
					getOrdersResponse.setOrderItemSubCatTotalList(orderItemSubCatTotalList);
					System.out.println(
							"orderItemSubCatTotalListorderItemSubCatTotalListorderItemSubCatTotalList list count is"
									+ orderItemSubCatTotalList.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}

				System.out.println("order list is " + orderList.toString());
				System.out.println("order list count is" + orderList.size());

				model.addObject("orderList", orderList);

				model.addObject("franchIds", franchIds);
				model.addObject("allOtherFrList", tempFrList);
				model.addObject("selectedFrList", selectedFrList);
				model.addObject("franchiseeList", franchiseeList);

				System.out.println("Fr selected all " + franchIds.toString());

			} // end of if

			if (!franchIds.contains("0") || routeId != 0) {

				System.out.println("few Fr selected: FrId  ArrayList " + franchIds.toString());

				System.out.println("few fra selected");

				map.add("frId", frIdString);
				map.add("menuId", menuId);
				map.add("date", date);
				map.add("itemId", itemId);
				RestTemplate restTemplate1 = new RestTemplate();

				GetOrderListResponse orderListResponse = restTemplate1
						.postForObject(Constants.url + "getOrderListByItem", map, GetOrderListResponse.class);

				orderList = orderListResponse.getGetOder();
				getOrdersResponse.setGetOder(orderList);

				try {

					map = new LinkedMultiValueMap<String, Object>();

					map.add("date", date);
					map.add("menuId", menuId);
					map.add("frId", frIdString);
					map.add("itemId", itemId);
					List<OrderItemSubCatTotal> orderItemSubCatTotalList = restTemplate1
							.postForObject(Constants.url + "getSubCatOrderTotalSpecItemId", map, List.class);
					getOrdersResponse.setOrderItemSubCatTotalList(orderItemSubCatTotalList);
					System.out.println(
							"orderItemSubCatTotalListorderItemSubCatTotalListorderItemSubCatTotalList list count is"
									+ orderItemSubCatTotalList.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}

				System.out.println("order list is " + orderList.toString());
				System.out.println("order list count is" + orderList.size());

				model.addObject("orderList", orderList);
				model.addObject("franchiseeList", franchiseeList);

			} // end of else

			List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

			ExportToExcel expoExcel = new ExportToExcel();
			List<String> rowData = new ArrayList<String>();

			rowData.add("Franchisee Name");
			rowData.add("Type");
			rowData.add("Item Id");
			rowData.add("Item Name");
			rowData.add("Quantity");

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);
			for (int i = 0; i < orderList.size(); i++) {
				expoExcel = new ExportToExcel();
				rowData = new ArrayList<String>();

				rowData.add(orderList.get(i).getFrName());

				rowData.add(orderList.get(i).getCatName());
				rowData.add("" + orderList.get(i).getId());
				rowData.add(orderList.get(i).getItemName());
				rowData.add("" + orderList.get(i).getOrderQty());

				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);
				System.out.println("List" + orderList.get(i).toString());
			}

			HttpSession session = request.getSession();
			session.setAttribute("exportExcelList", exportToExcelList);
			session.setAttribute("excelName", "Orders");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return getOrdersResponse;
	}

	@RequestMapping(value = "/updateOrderDetails", method = RequestMethod.GET)
	public @ResponseBody Info updateOrderDetails(HttpServletRequest request, HttpServletResponse response) {

		String ids = request.getParameter("ids");
		String delDate = request.getParameter("delDate");
		String prodDate = request.getParameter("prodDate");

		System.out.println(DateConvertor.convertToYMD(delDate)+"--------------------------" + prodDate);
		System.out.println("********************" + delDate);

		/*
		 * StringBuilder sb1 = new StringBuilder(); for (int i = 0; i < ids.length; i++)
		 * { sb1 = sb1.append(ids[i] + ",");
		 * 
		 * } String orderIds = sb1.toString(); orderIds = orderIds.substring(0,
		 * orderIds.length() - 1);
		 */
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("orderIds", ids);
		map.add("delDate", DateConvertor.convertToYMD(delDate));
		map.add("prodDate",DateConvertor.convertToYMD(prodDate));
		System.err.println(map.toString());
		RestTemplate restTemp = new RestTemplate();

		Info info = restTemp.postForObject(Constants.url + "updateOrderDetails", map, Info.class);

		System.out.println(info.toString());

		return info;
	}

	//-------------------------------------------------------------------------------//
	//Mahendra Singh
	//03-10-2019
	
	@RequestMapping(value = "/showSpcakeRawMaterialInfo", method = RequestMethod.POST)
	@ResponseBody public List<FlavourRawMaterialInfo> showSpcakeRawMaterialInfo(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		String date = request.getParameter("prod_date");
		int from = 0;
		int to=0;
		
		int value = Integer.parseInt(request.getParameter("select_way"));
		
		List<FlavourRawMaterialInfo> flvrRawMtrl  = new ArrayList<FlavourRawMaterialInfo>();
		//ModelAndView model = new ModelAndView("orders/flavourRawMatrlDetail");
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		RestTemplate restTemp = new RestTemplate();

		if(value==1) {
			 from = Integer.parseInt(request.getParameter("from"));
			
			 to = Integer.parseInt(request.getParameter("to"));
			System.out.println("input------"+date+" / "+from+" / "+to+" / "+value);

		StringBuffer orderId = new StringBuffer("0,");
		for (int i = from - 1; i < to && i < spCakeOrderList.size(); i++) {
			orderId.append(Integer.toString(spCakeOrderList.get(i).getSpOrderNo()) + ",");
		}

		orderId.setLength(orderId.length() - 1);
		map.add("spOrderNo", orderId);
		FlavourRawMaterialInfo[] arr = restTemp.postForObject(Constants.url + "getRawMaterialDetails", map,
				FlavourRawMaterialInfo[].class);
		
		 flvrRawMtrl = new ArrayList<>(Arrays.asList(arr));
		System.out.println("AlbumList------------------"+flvrRawMtrl);
		}else {
			System.out.println("In Else");
			
			
			  String[] rawMatril = request.getParameterValues("selCheck");
			  String matrilList = new String(); 
				  for (int i = 0; i < rawMatril.length; i++) {
					  matrilList = rawMatril[i] + "," + matrilList; 
					  } 
				  matrilList = matrilList.substring(0, matrilList.length() - 1);
			  
			  System.out.println("selected bills for Printing " + matrilList);
			  
			  map.add("spOrderNo", matrilList); 
			  FlavourRawMaterialInfo[] arr = restTemp.postForObject(Constants.url + "getRawMaterialDetails", map, FlavourRawMaterialInfo[].class);
			  
			  flvrRawMtrl = new ArrayList<>(Arrays.asList(arr));
			 
		}
		
		model.addAttribute("flvrRawMtrl",flvrRawMtrl );
		model.addAttribute("from", from);
		model.addAttribute("to", to);
		model.addAttribute("date", date);
		return flvrRawMtrl;
	}
}
