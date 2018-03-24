package com.ats.adminpanel.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
//import java.util.Date;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.annotation.Scope;
//import org.joda.time.DateTime;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
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
import com.ats.adminpanel.model.GenerateBill;
import com.ats.adminpanel.model.Order;
import com.ats.adminpanel.model.Orders;

import com.ats.adminpanel.model.franchisee.AllFranchiseeList;
import com.ats.adminpanel.model.franchisee.AllMenuResponse;
import com.ats.adminpanel.model.franchisee.FranchiseeList;
import com.ats.adminpanel.model.franchisee.Menu;
import com.ats.adminpanel.model.item.AllItemsListResponse;
import com.ats.adminpanel.model.item.Item;
import com.ats.adminpanel.model.pushorderdata.GetOrderDataForPushOrder;
import com.ats.adminpanel.model.pushorderdata.GetOrderDataForPushOrderList;
import com.ats.adminpanel.model.pushorderdata.PushOrderList;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

@Controller
@Scope("session")
public class PushOrderController {
	AllFrIdNameList allFrIdNameList;
	List<Menu> menuList;// = new ArrayList<Menu>();
	List<String> selectedFrList;
	ArrayList<Integer> selectedFrIdList;
	List<Menu> selectedMenuList = new ArrayList<Menu>();
	public static List<Item> items;
	int menuId;
	int selectedMainCatId;
	List<GetOrderDataForPushOrder> pushOrderData;

	@RequestMapping(value = "/showpushorders", method = RequestMethod.GET)
	public ModelAndView showPushOrder(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("orders/pushorders");
		Constants.mainAct =4;
		Constants.subAct =30;

		RestTemplate restTemplate = new RestTemplate();

		AllMenuResponse allMenuResponse = restTemplate.getForObject(Constants.url + "getAllMenu",
				AllMenuResponse.class);

		menuList = allMenuResponse.getMenuConfigurationPage();
		allFrIdNameList = new AllFrIdNameList();
		try {

			allFrIdNameList = restTemplate.getForObject(Constants.url + "getAllFrIdName", AllFrIdNameList.class);

		} catch (Exception e) {
			System.out.println("Exception in getAllFrIdName" + e.getMessage());
			e.printStackTrace();

		}
		List<AllFrIdName> selectedFrListAll = new ArrayList();

		selectedMenuList = new ArrayList<Menu>();

		for (int i = 0; i < menuList.size(); i++) {
			if (menuList.get(i).getMenuId() == 26 || menuList.get(i).getMenuId() == 31
					|| menuList.get(i).getMenuId() == 33 || menuList.get(i).getMenuId() == 34) {
				selectedMenuList.add(menuList.get(i));
			}
		}
		System.out.println(" Fr " + allFrIdNameList.getFrIdNamesList());
		java.util.Date utilDate = new java.util.Date(); 
		model.addObject("unSelectedMenuList", selectedMenuList);
		model.addObject("unSelectedFrList", allFrIdNameList.getFrIdNamesList());
		model.addObject("date",new SimpleDateFormat("dd-MM-yyyy").format(utilDate));
		return model;
	}

	// Ajax call

	@RequestMapping(value = "/getItemList", method = RequestMethod.GET)
	public @ResponseBody List<PushOrderList> generateItemList(HttpServletRequest request,
			HttpServletResponse response) {

		RestTemplate restTemplate = new RestTemplate();

		List<AllFrIdName> selectedFrListAll = new ArrayList();

		List<PushOrderList> pushOrdeList = new ArrayList<PushOrderList>();

		selectedFrListAll = allFrIdNameList.getFrIdNamesList();

		// int selectedMainCatId=0;
		String selectedMenu = request.getParameter("menu_id");
		menuId = Integer.parseInt(selectedMenu);

		String selectedFr = request.getParameter("fr_id_list");

		System.out.println("Selected Franchisee Ids" + selectedFr);

		selectedFr = selectedFr.substring(1, selectedFr.length() - 1);
		selectedFr = selectedFr.replaceAll("\"", "");

		selectedFrList = new ArrayList<>();

		selectedFrList = Arrays.asList(selectedFr.split(","));

		selectedFrIdList = new ArrayList();
		List<AllFrIdName> allFrList = allFrIdNameList.getFrIdNamesList();

		for (int i = 0; i < menuList.size(); i++) {
			if (menuList.get(i).getMenuId() == menuId) {
				selectedMainCatId = menuList.get(i).getMainCatId();
			}
		}

		System.out.println("Before Rest of Items   and mennu id is  :  " + selectedMenu);

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		map.add("itemGrp1", selectedMainCatId);

		try {

			ParameterizedTypeReference<List<Item>> typeRef = new ParameterizedTypeReference<List<Item>>() {
			};
			ResponseEntity<List<Item>> responseEntity = restTemplate.exchange(Constants.url + "getItemsByCatIdAndSortId",
					HttpMethod.POST, new HttpEntity<>(map), typeRef);

			items = responseEntity.getBody();

		} catch (Exception e) {

			System.out.println(e.getMessage());

		}
		System.out.println("After Rest of Items   and mennu id is  :");

		map = new LinkedMultiValueMap<String, Object>();

		String strFrId = selectedFr;
		System.out.println("strFrId =" + strFrId.toString());
		map.add("frIdList", strFrId);

		GetOrderDataForPushOrderList pushOrderDataList = restTemplate
				.postForObject(Constants.url + "getOrderDataForPushOrder", map, GetOrderDataForPushOrderList.class);

		pushOrderData = pushOrderDataList.getOrderDataForPushOrder();

		// System.out.println("push order data "+pushOrderData.toString());

		System.out.println("Item List: " + items.toString());

		// new code start

		// new code

		// setting item Qty of pushed Item
		/*
		 * if(pushOrderData !=null) { //for(int l=0;l<selectedFrIdList.size();l++) {
		 * 
		 * 
		 * for (int i=0;i<pushOrderData.size();i++) {
		 * 
		 * for (int j=0;j<items.size();j++) {
		 * 
		 * if(items.get(j).getId()==pushOrderData.get(i).getItemId()) {
		 * 
		 * items.get(j).setDelStatus(pushOrderData.get(i).getOrderQty());
		 * 
		 * }
		 * 
		 * //System.out.println("item entry new qty "+items.toString());
		 * 
		 * }//end of for loop } }//end of if
		 */
		// End of setting Item Qty for pushed Item

		// PushOrderList pushOrdeList=new PushOrderList();

		List<GetOrderDataForPushOrder> prevPushOrderList = new ArrayList<GetOrderDataForPushOrder>();
		GetOrderDataForPushOrder prevOrderData = null;
		PushOrderList pushOrder = null;
		int x=0;
		for (int j = 0; j < items.size(); j++) {
			
			System.out.println("Inside First For ");

			pushOrder = new PushOrderList();

			pushOrder.setItemId(items.get(j).getId());
			pushOrder.setItemName(items.get(j).getItemName());

			if (pushOrderData != null) {

				for (int k = 0; k < pushOrderData.size(); k++) {


						prevOrderData = new GetOrderDataForPushOrder();

						if (pushOrderData.get(k).getItemId() == items.get(j).getId()) {
							x=x+1;
							System.out.println("matched "+x);

							prevOrderData.setFrId(pushOrderData.get(k).getFrId());
							prevOrderData.setItemId(pushOrderData.get(k).getItemId());
							prevOrderData.setOrderId(pushOrderData.get(k).getOrderId());
							prevOrderData.setOrderQty(pushOrderData.get(k).getOrderQty());

							System.out.println("prev Order Dat " + prevOrderData.toString());

							prevPushOrderList.add(prevOrderData);

							pushOrder.setGetOrderDataForPushOrder(prevPushOrderList);

							System.out.println("prev Order Dat List " + prevPushOrderList.toString());

						}

					
				}
			//	pushOrder.setGetOrderDataForPushOrder(prevPushOrderList);

			}

			pushOrdeList.add(pushOrder);

		}

		System.out.println("to String push Order List " + pushOrdeList.toString());

		/*
		 * Ganesh code Comment: String selectedFr = request.getParameter("fr_id_list");
		 * 
		 * System.out.println("Selected Franchisee Ids"+selectedFr);
		 * 
		 * 
		 * selectedFr = selectedFr.substring(1, selectedFr.length() - 1); selectedFr =
		 * selectedFr.replaceAll("\"", "");
		 * 
		 * 
		 * selectedFrList=new ArrayList<>();
		 * 
		 * selectedFrList=Arrays.asList(selectedFr.split(","));
		 * 
		 * selectedFrIdList=new ArrayList(); List<AllFrIdName>
		 * allFrList=allFrIdNameList.getFrIdNamesList();
		 */
		System.out.println("Selected Franchisee");
		for (int i = 0; i < allFrList.size(); i++) {

			for (int j = 0; j < selectedFrList.size(); j++) {
				// System.out.println("Current Fr"+selectedFrList.get(j));

				if ((allFrList.get(i).getFrId()) == Integer.parseInt(selectedFrList.get(j))) {
					System.out.println(allFrList.get(i).getFrName());

					selectedFrIdList.add(allFrList.get(i).getFrId());
				}
			}

		}

		// return items;
		System.out.println("Final List :" + pushOrdeList.toString());
		return pushOrdeList;
	}

	// After submit order

	@RequestMapping(value = "/submitPushOrder", method = RequestMethod.POST)
	public String submitPushOrders(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("orders/pushorders");
		
		Orders order = new Orders();

		// List<Orders> oList=new ArrayList<>();
		String todaysDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		java.util.Date utilDate = new java.util.Date();
		System.out.println(dateFormat.format(utilDate)); // 2016/11/16 12:08:43

		java.sql.Date date = new java.sql.Date(utilDate.getTime());
		java.sql.Date deliveryDate = new java.sql.Date(tomarrow().getTime());
		// java.sql.Date deliveryDate=new java.sql.Date(tomarrow1().getTime());

		// get all Franchisee details
		RestTemplate restTemplate = new RestTemplate();

		AllFranchiseeList allFranchiseeList = restTemplate.getForObject(Constants.url + "getAllFranchisee",
				AllFranchiseeList.class);

		List<FranchiseeList> franchaseeList = new ArrayList<FranchiseeList>();
		franchaseeList = allFranchiseeList.getFranchiseeList();
		boolean pushItem = false;

		if (pushOrderData != null) {

			pushItem = true;
		}

		if (pushItem) {
			System.out.println("push Order Length"+pushOrderData.size());
			

				for (int j = 0; j < items.size(); j++) {
					
					
					//if (pushOrderData.get(m).getItemId() != items.get(j).getId()) {

						// System.out.println(items.get(j).getId());
						for (int i = 0; i < selectedFrIdList.size(); i++) {
													
							boolean isSameItem=false;
							for (int m = 0; m < pushOrderData.size(); m++) {
								if (pushOrderData.get(m).getItemId() == items.get(j).getId()) {
									
									isSameItem=true;
									
								}
							}
							if(isSameItem==false) {
								
							
							System.out.println(items.get(j).getId());

							String quantity = request.getParameter(
									"itemId" + items.get(j).getId() + "orderQty" + selectedFrIdList.get(i));
							int qty = Integer.parseInt(quantity);
							
						
							if (qty != 0 ) {
								
								List<Orders> oList = new ArrayList<>();

								order.setOrderDatetime(todaysDate);
								order.setFrId(selectedFrIdList.get(i));
								order.setRefId(items.get(j).getId());
								order.setItemId(String.valueOf(items.get(j).getId()));
								order.setOrderQty(qty);
								order.setProductionDate(date);
								order.setOrderDate(date);
								order.setDeliveryDate(deliveryDate);
								order.setMenuId(0);
								order.setGrnType(4);
								order.setIsEdit(0);
								order.setMenuId(menuId);
								order.setOrderType(selectedMainCatId);

								for (int l = 0; l < selectedFrIdList.size(); l++) {
									for (int k = 0; k < franchaseeList.size(); k++) {
										if (selectedFrIdList.get(l) == franchaseeList.get(k).getFrId()) {
											if (franchaseeList.get(k).getFrRateCat() == 1) {
												order.setOrderRate(items.get(j).getItemRate1());
												order.setOrderMrp(items.get(j).getItemMrp1());
											} else if (franchaseeList.get(k).getFrRateCat() == 2) {
												order.setOrderRate(items.get(j).getItemRate2());
												order.setOrderMrp(items.get(j).getItemMrp2());
											} else if (franchaseeList.get(k).getFrRateCat() == 3) {
												order.setOrderRate(items.get(j).getItemRate3());
												order.setOrderMrp(items.get(j).getItemMrp3());
											}

										}
									}
								}

								oList.add(order);
								PlaceOrder(oList);
								System.out.println("oList Ganesh = "+oList.toString());

							}


							} // end of else
							
							else {
								
								isSameItem=false;
							}
						}
				//	} // end of if pushOrderData
				}
			// for }
		} // end of if pushItem

		
		
		
		
		if (pushItem == false) {
			for (int j = 0; j < items.size(); j++) {

				// System.out.println(items.get(j).getId());
				for (int i = 0; i < selectedFrIdList.size(); i++) {

					System.out.println(items.get(j).getId());

					String quantity = request
							.getParameter("itemId" + items.get(j).getId() + "orderQty" + selectedFrIdList.get(i));
					System.out.println("qtyb    " + quantity);
					int qty = Integer.parseInt(quantity);

					if (qty != 0) {
						List<Orders> oList = new ArrayList<>();

						order.setOrderDatetime(todaysDate);
						order.setFrId(selectedFrIdList.get(i));
						order.setRefId(items.get(j).getId());
						order.setItemId(String.valueOf(items.get(j).getId()));
						order.setOrderQty(qty);
						order.setProductionDate(date);
						order.setOrderDate(date);
						order.setDeliveryDate(deliveryDate);
						order.setMenuId(0);
						order.setGrnType(4);
						order.setIsEdit(0);
						order.setMenuId(menuId);
						order.setOrderType(selectedMainCatId);

						for (int l = 0; l < selectedFrIdList.size(); l++) {
							for (int k = 0; k < franchaseeList.size(); k++) {
								if (selectedFrIdList.get(l) == franchaseeList.get(k).getFrId()) {
									if (franchaseeList.get(k).getFrRateCat() == 1) {
										order.setOrderRate(items.get(j).getItemRate1());
										order.setOrderMrp(items.get(j).getItemMrp1());
									} else if (franchaseeList.get(k).getFrRateCat() == 2) {
										order.setOrderRate(items.get(j).getItemRate2() );
										order.setOrderMrp(items.get(j).getItemMrp2());
									} else if (franchaseeList.get(k).getFrRateCat() == 3) {
										order.setOrderRate(items.get(j).getItemRate3());
										order.setOrderMrp(items.get(j).getItemMrp3());
									}

								}
							}
						}

						oList.add(order);
						PlaceOrder(oList);
						System.out.println("oList Ganesh = "+oList.toString());


					} // end of if qty!=0
				}
			} // end items for loop

		} // end of not pushItem
		
		

		model.addObject("unSelectedMenuList", menuList);
		model.addObject("unSelectedFrList", allFrIdNameList.getFrIdNamesList());

		return "redirect:/showpushorders";
	}

	void PlaceOrder(List<Orders> oList) {
		// RestTemplate restTemplate = new RestTemplate();
		System.out.println("Order list  :   " + oList.toString());

		String url = Constants.url + "placePushDumpOrder";

		ObjectMapper mapperObj = new ObjectMapper();
		String jsonStr = null;

		try {
			jsonStr = mapperObj.writeValueAsString(oList);
			System.out.println("Converted JSON: " + jsonStr);
		} catch (IOException e) {
			System.out.println("Excep converting java 2 json " + e.getMessage());
			e.printStackTrace();
		}
		System.out.println("Before Order place");
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			HttpEntity<String> entity = new HttpEntity<String>(jsonStr, headers);

			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> orderListResponse = restTemplate.exchange(url, HttpMethod.POST, entity,
					String.class);

			System.out.println("Place Order Response" + orderListResponse.toString());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public java.util.Date tomarrow() {

		java.util.Date dt = new java.util.Date();
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		c.add(Calendar.DATE, 1);
		dt = c.getTime();
		return dt;
	}

	/*
	 * public java.util.Date tomarrow1() {
	 * 
	 * 
	 * java.util.Date dt = new java.util.Date(); Calendar c =
	 * Calendar.getInstance(); c.setTime(dt); c.add(Calendar.DATE, 2); dt =
	 * c.getTime(); return dt; }
	 */

}