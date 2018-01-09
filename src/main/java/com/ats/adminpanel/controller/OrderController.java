package com.ats.adminpanel.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.ats.adminpanel.model.GetOrder;
import com.ats.adminpanel.model.GetOrderListResponse;
import com.ats.adminpanel.model.GetRegSpCakeOrders;
import com.ats.adminpanel.model.GetSpCakeOrders;
import com.ats.adminpanel.model.Order;
import com.ats.adminpanel.model.RegularSpCkOrder;
import com.ats.adminpanel.model.RegularSpCkOrdersResponse;
import com.ats.adminpanel.model.SpCakeOrdersBean;
import com.ats.adminpanel.model.SpCakeOrdersBeanResponse;
import com.ats.adminpanel.model.franchisee.AllFranchiseeList;
import com.ats.adminpanel.model.franchisee.AllMenuResponse;
import com.ats.adminpanel.model.franchisee.FranchiseeList;
import com.ats.adminpanel.model.franchisee.Menu;
import com.fasterxml.jackson.annotation.JsonFormat.Value;


@Controller
public class OrderController {
	static  List<FranchiseeList> franchiseeList= new ArrayList<FranchiseeList>();
	static  List<FranchiseeList> tempFrList= new ArrayList<FranchiseeList>();
	static  List<FranchiseeList> selectedFrList= new ArrayList<FranchiseeList>();
	static List<Menu> menuList;
	SpCakeOrdersBeanResponse orderListResponse;
	RegularSpCkOrdersResponse regOrderListResponse ;
	
	@RequestMapping(value = "/showOrders")
	public ModelAndView searchOrder(HttpServletRequest request, HttpServletResponse response) {
		
		//ModelAndView model=new  ModelAndView("orders/orders");
		ModelAndView model=new  ModelAndView("orders/orders");
		Constants.mainAct=1;
		Constants.subAct=11;

		
		RestTemplate restTemplate = new RestTemplate();
		AllFranchiseeList allFranchiseeList=restTemplate.getForObject(
				Constants.url+"getAllFranchisee",
				AllFranchiseeList.class);
		
		 //franchiseeList= new ArrayList<FranchiseeList>();
		franchiseeList=allFranchiseeList.getFranchiseeList();
				
		model.addObject("franchiseeList",franchiseeList);
		model.addObject("allOtherFrList",tempFrList);
		model.addObject("selectedFrList",selectedFrList);
		
		RestTemplate restTemplate1 = new RestTemplate();
		
		
		AllMenuResponse allMenuResponse=restTemplate1.getForObject(
				Constants.url+"getAllMenu",
				AllMenuResponse.class);
		
		menuList= new ArrayList<Menu>();
		menuList=allMenuResponse.getMenuConfigurationPage();
		
		System.out.println("MENU LIST= "+menuList.toString());
		model.addObject("menuList",menuList);
		System.out.println("menu list is"+menuList.toString());
		
		
		
				
		return model;
		
	}
	
	@RequestMapping(value = "/searchOrdersProcess",method = RequestMethod.GET)//getOrderListForAllFr new web service
	public  @ResponseBody  List<GetOrder>  searchOrderProcess(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("orders/orders");
		
		System.out.println("/inside search order process  ");
		//model.addObject("franchiseeList", franchiseeList);
		
		model.addObject("menuList", menuList);
		
		
		String menuId = request.getParameter("item_id_list");
		String frIdString = request.getParameter("fr_id_list");
		//String prodDate = request.getParameter("prod_date");

		
		menuId=menuId.substring(1, menuId.length()-1);
		menuId=menuId.replaceAll("\"", "");
		System.out.println("menu Ids New ="+menuId);
		
		frIdString=frIdString.substring(1, frIdString.length()-1);
		frIdString=frIdString.replaceAll("\"", "");
		System.out.println("frIds  New ="+frIdString);
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		
		List<GetOrder> orderList = new ArrayList<GetOrder>();
		
		List<String> franchIds=new ArrayList();
		franchIds=Arrays.asList(frIdString);
		
		System.out.println("fr Id ArrayList "+franchIds.toString());
		

		if(franchIds.contains("0")) {
			
			System.out.println("all fr selected");
					
			map.add("date",  new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
			map.add("menuId", menuId);
			
			RestTemplate restTemplate1 = new RestTemplate();

			GetOrderListResponse orderListResponse = restTemplate1.postForObject(Constants.url + 
					"getOrderListForAllFr", map,GetOrderListResponse.class);

			
			orderList = orderListResponse.getGetOder();

			System.out.println("order list is " + orderList.toString());
			System.out.println("order list count is" + orderList.size());
			
			model.addObject("orderList", orderList);
			
			
			model.addObject("franchIds",franchIds);
			model.addObject("allOtherFrList",tempFrList);
			model.addObject("selectedFrList",selectedFrList);
			model.addObject("franchiseeList",franchiseeList);
		
			
			System.out.println("Fr selected all "+franchIds.toString());

		}// end of if
		
		else {
			
			System.out.println("few Fr selected: FrId  ArrayList "+franchIds.toString());
			
			
			System.out.println("few fra selected");

			map.add("frId", frIdString);
			map.add("menuId", menuId);
			map.add("date", new SimpleDateFormat("dd-MM-yyyy").format(new Date()));

			
			RestTemplate restTemplate1 = new RestTemplate();

			GetOrderListResponse orderListResponse = restTemplate1.postForObject(Constants.url + 
					"getOrderList",map, GetOrderListResponse.class);

			
			orderList = orderListResponse.getGetOder();

			System.out.println("order list is " + orderList.toString());
			System.out.println("order list count is" + orderList.size());
			model.addObject("orderList", orderList);
			model.addObject("franchiseeList",franchiseeList);
			

		}// end of else
		

		return orderList;
	}

	//special cake orders
	
	
	@RequestMapping(value = "/spCakeOrders")
	public ModelAndView searchSpCakeOrder(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView model=new ModelAndView("orders/spcakeorders");
		RestTemplate restTemplate = new RestTemplate();
		AllFranchiseeList allFranchiseeList=restTemplate.getForObject(
				Constants.url+"getAllFranchisee",
				AllFranchiseeList.class);
		
		 //franchiseeList= new ArrayList<FranchiseeList>();
		franchiseeList=allFranchiseeList.getFranchiseeList();
				
		model.addObject("todayDate",new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
		model.addObject("franchiseeList",franchiseeList);
		
	
		return model;
	}
	@RequestMapping(value = "/regularSpCakeOrderProcess")
	public ModelAndView regularSpCakeOrderProcess(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView model=new ModelAndView("orders/regularsporders");
		RestTemplate restTemplate = new RestTemplate();
		AllFranchiseeList allFranchiseeList=restTemplate.getForObject(
				Constants.url+"getAllFranchisee",
				AllFranchiseeList.class);
		
		 //franchiseeList= new ArrayList<FranchiseeList>();
		franchiseeList=allFranchiseeList.getFranchiseeList();
				
		model.addObject("todayDate",new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
		model.addObject("franchiseeList",franchiseeList);
		
	
		return model;
	}
	
	@RequestMapping(value = "/spCakeOrderProcess",method = RequestMethod.GET)
	public  @ResponseBody List<SpCakeOrdersBean> spCakeOrderProcess(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = null;
		System.out.println("/inside search sp cake order process  ");
		List<SpCakeOrdersBean> spCakeOrderList = new ArrayList<SpCakeOrdersBean>();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		RestTemplate restTemplate1 = new RestTemplate();

		try {
			model = new ModelAndView("orders/spcakeorders");
			model.addObject("franchiseeList", franchiseeList);
			
			String frIdString = request.getParameter("fr_id_list");
			String prodDate = request.getParameter("prod_date");
			
			frIdString=frIdString.substring(1, frIdString.length()-1);
			frIdString=frIdString.replaceAll("\"", "");
			System.out.println("frIds  New ="+frIdString);
			
			List<String> franchIds=new ArrayList();
			franchIds=Arrays.asList(frIdString);
			
			System.out.println("fr Id ArrayList "+franchIds.toString());
		
			if(franchIds.contains("0")) {
				System.out.println("all fr selected");

				map.add("prodDate", prodDate);

				  orderListResponse = restTemplate1
						.postForObject(Constants.url + "getAllFrSpCakeOrderList", map, SpCakeOrdersBeanResponse.class);

				
				spCakeOrderList = orderListResponse.getSpCakeOrdersBean();
				
				System.out.println("order list is" + spCakeOrderList.toString());
				System.out.println("order list count is" + spCakeOrderList.size());
				
				model.addObject("spCakeOrderList", spCakeOrderList);

			} // end of if
			
			
			else {
				
				System.out.println("few fr selected");


				map.add("frId", frIdString);
				map.add("prodDate", prodDate);

				SpCakeOrdersBeanResponse orderListResponse = restTemplate1
						.postForObject(Constants.url + "getSpCakeOrderLists", map, SpCakeOrdersBeanResponse.class);				//s added 

				
				spCakeOrderList = orderListResponse.getSpCakeOrdersBean();
				System.out.println("order list is" + spCakeOrderList.toString());
				System.out.println("order list count is" + spCakeOrderList.size());
				model.addObject("spCakeOrderList", spCakeOrderList);
				
			} // end of else

		} catch (Exception e) {
			System.out.println("exception in order display" + e.getMessage());
		}

		return spCakeOrderList;
	}
	
	
	@RequestMapping(value = "/regularSpCkOrderProcess")
	public ModelAndView regularSpCkOrderProcess(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = null;
		System.out.println("/inside search sp cake order process  ");

		try {
			model = new ModelAndView("orders/regularsporders");
			model.addObject("franchiseeList", franchiseeList);

			String[] frIds = request.getParameterValues("fr_id[]");
			System.out.println("ALL FR IDs:"+frIds);
			

			String prodDate = request.getParameter("prod_date");
			System.out.println("prodDate:"+prodDate);

			
			if (frIds[0].toString().equals("0")) {
				System.out.println("all fr selected");

				/*String[] parts;
				parts = prodDate.split("/");
				String part1 = parts[0];
				String part2 = parts[1];
				String part3 = parts[2];
				StringBuilder dateSb = new StringBuilder();
				dateSb.append(part3 + "/");
				dateSb.append(part2 + "/");
				dateSb.append(part1);
				String finalDate = dateSb.toString();
				System.out.println("final date is" + finalDate);
*/
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

							
				map.add("prodDate", prodDate);

				RestTemplate restTemplate1 = new RestTemplate();

				regOrderListResponse = restTemplate1
						.postForObject(Constants.url + "getAllFrRegSpCakeOrders", map, RegularSpCkOrdersResponse.class);

				List<RegularSpCkOrder> regularSpCkOrderList = new ArrayList<RegularSpCkOrder>();
				regularSpCkOrderList = regOrderListResponse.getRegularSpCkOrdersList();
			
				System.out.println("order list count is" + regularSpCkOrderList.toString());
				model.addObject("regularSpCkOrderList", regularSpCkOrderList);

			} // end of if
			
			
			else {
				
				System.out.println("few fr selected");


				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				List<Integer> frIdArray = new ArrayList<Integer>();

				StringBuilder sb = new StringBuilder();

				String strFrId = "";
				for (int i = 0; i < frIds.length; i++) {
					System.out.println("fr Ids List " + frIds[i]);
					// frIdArray.add(Integer.parseInt(frIds[0]));

					sb = sb.append(frIds[i] + ",");
				}

				strFrId = sb.toString();
				strFrId = strFrId.substring(0, strFrId.length() - 1);

				System.out.println("frid array is=" + strFrId);

				map.add("frId", strFrId);
				map.add("prodDate", prodDate);

				RestTemplate restTemp = new RestTemplate();


				regOrderListResponse = restTemp
						.postForObject(Constants.url + "getRegSpCkOrderList", map, RegularSpCkOrdersResponse.class);

				List<RegularSpCkOrder> regularSpCkOrderList = new ArrayList<RegularSpCkOrder>();
				regularSpCkOrderList = regOrderListResponse.getRegularSpCkOrdersList();
			
				System.out.println("order list count is" + regularSpCkOrderList.size());
				model.addObject("regularSpCkOrderList", regularSpCkOrderList);

			} // end of else

		} catch (Exception e) {
			System.out.println("exception in order display" + e.getMessage());
		}

		return model;
	}

	//ganesh 24-10-2017
	
	@RequestMapping(value = "/callDeleteOrder",method = RequestMethod.GET)
	public  @ResponseBody  void deleteOrder(HttpServletRequest request, HttpServletResponse response) {
	
		System.out.println("/inside delete order process  ");
		
		String orderId = request.getParameter("order_id");
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("orderId", orderId);
		map.add("orderStatus", 1);
		
		RestTemplate restTemp = new RestTemplate();

		String s= restTemp.postForObject(Constants.url + "DeleteOrder", map, String.class);
		
		//return "Success";
	}
	
	@RequestMapping(value = "/callChangeQty",method = RequestMethod.GET)
	public  @ResponseBody  void updateOrderQty(HttpServletRequest request, HttpServletResponse response) {
	
		System.out.println("/inside Update order process  ");
		
		String orderId = request.getParameter("order_id");
		String orderQty = request.getParameter("order_qty");
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("orderId", orderId);
		map.add("orderQty",orderQty );
		
		RestTemplate restTemp = new RestTemplate();

		String s= restTemp.postForObject(Constants.url + "updateOrderQty", map, String.class);
		
		//return "Success";
	}
	
	
	@RequestMapping(value = "/showHtmlViewSpcakeOrder/{spOrderNo}", method = RequestMethod.GET)
	public ModelAndView showHtmlViewSpcakeOrder(@PathVariable("spOrderNo")int spOrderNo, HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model=new ModelAndView("orders/htmlViewSpCakeOrder");
		
		RestTemplate restTemp = new RestTemplate();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("spOrderNo", spOrderNo);
		List<GetSpCakeOrders> orderListResponse = restTemp.postForObject(Constants.url + "getSpCakeOrderBySpOrderNo", map, List.class);
		
		model.addObject("spCakeOrder",orderListResponse.get(0) );
	return model;	
	}
	
	@RequestMapping(value = "/showSpcakeOrderPdf/{spOrderNo}", method = RequestMethod.GET)
	public ModelAndView showSpcakeOrderPdf(@PathVariable("spOrderNo")int spOrderNo, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model=new ModelAndView("orders/spCakeOrderPdf");
		
		RestTemplate restTemp = new RestTemplate();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("spOrderNo", spOrderNo);
		List<GetSpCakeOrders> orderListResponse = restTemp.postForObject(Constants.url + "getSpCakeOrderBySpOrderNo", map, List.class);
		
		System.out.println("SpOrder"+orderListResponse.toString());
		model.addObject("spCakeOrder",orderListResponse );
		return model;	
	}
	
	
	
	
	@RequestMapping(value = "/showSpcakeOrderPdfInRange/{from}/{to}", method = RequestMethod.GET)
	public ModelAndView showSpcakeOrderPdfInRange(@PathVariable("from")int from, @PathVariable("to")int to, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model=new ModelAndView("orders/spCakeOrderPdf");
	
		
		RestTemplate restTemp = new RestTemplate();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		StringBuffer orderId=new StringBuffer("0,");
		 
		for(int i=from-1;i<to && i<orderListResponse.getSpCakeOrdersBean().size();i++)
		{
			orderId.append(Integer.toString(orderListResponse.getSpCakeOrdersBean().get(i).getSpOrderNo())+",");
		}
		
		 

		orderId.setLength(orderId.length() - 1);
		map.add("spOrderNo", orderId);
		List<GetSpCakeOrders> orderListResponse = restTemp.postForObject(Constants.url + "getSpCakeOrderBySpOrderNo", map, List.class);
		
		System.out.println("SpOrder"+orderListResponse.toString());
		model.addObject("spCakeOrder",orderListResponse );
		model.addObject("from",from);
		return model;
	}
	
	@RequestMapping(value = "/showHtmlViewRegSpcakeOrder/{orderNo}", method = RequestMethod.GET)
	public ModelAndView showHtmlViewRegSpcakeOrder(@PathVariable("orderNo")int orderNo, HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model=new ModelAndView("orders/htmlViewRegSpCakeOrder");
		
		RestTemplate restTemp = new RestTemplate();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("orderNo", orderNo);
		List<GetRegSpCakeOrders> orderListResponse = restTemp.postForObject(Constants.url + "getRegSpCakeOrderBySpOrderNo", map, List.class);
		
	 
		
		 
		model.addObject("regularSpCkOrdersList",orderListResponse.get(0) );
	return model;	
	}
	
	@RequestMapping(value = "/showRegSpcakeOrderPdf/{orderNo}", method = RequestMethod.GET)
	public ModelAndView showRegSpcakeOrderPdf(@PathVariable("orderNo")int orderNo, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model=new ModelAndView("orders/regSpCakeOrderPdf");
		
		RestTemplate restTemp = new RestTemplate();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("orderNo", orderNo);
		List<GetRegSpCakeOrders> orderListResponse = restTemp.postForObject(Constants.url + "getRegSpCakeOrderBySpOrderNo", map, List.class);
		
		System.out.println("regularSpCkOrdersList"+orderListResponse.toString());
		model.addObject("regularSpCkOrdersList",orderListResponse );
		return model;	
	}
	
	@RequestMapping(value = "/showRegSpcakeOrderPdfInRange/{from}/{to}", method = RequestMethod.GET)
	public ModelAndView showRegSpcakeOrderPdfInRange(@PathVariable("from")int from, @PathVariable("to")int to, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model=new ModelAndView("orders/regSpCakeOrderPdf");
	
		
		RestTemplate restTemp = new RestTemplate();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		StringBuffer orderId=new StringBuffer("0,");
		 
		for(int i=from-1;i<to && i<regOrderListResponse.getRegularSpCkOrdersList().size();i++)
		{
			orderId.append(Integer.toString(regOrderListResponse.getRegularSpCkOrdersList().get(i).getRspId())+",");
		}
		
		 

		orderId.setLength(orderId.length() - 1);
		map.add("orderNo", orderId);
		List<GetRegSpCakeOrders> orderListResponse = restTemp.postForObject(Constants.url + "getRegSpCakeOrderBySpOrderNo", map, List.class);
		
		System.out.println("regularSpCkOrdersList"+orderListResponse.toString());
		model.addObject("regularSpCkOrdersList",orderListResponse );
		model.addObject("from",from);
		return model;
	}
	
}
