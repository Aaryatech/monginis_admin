package com.ats.adminpanel.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.annotation.Scope;
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
import com.ats.adminpanel.model.ConfigureFrBean;
import com.ats.adminpanel.model.ConfigureFrListResponse;
import com.ats.adminpanel.model.DumpOrderList;
import com.ats.adminpanel.model.GetDumpOrder;
import com.ats.adminpanel.model.GetDumpOrderList;
import com.ats.adminpanel.model.OrderData;
import com.ats.adminpanel.model.Orders;
import com.ats.adminpanel.model.franchisee.AllFranchiseeList;
import com.ats.adminpanel.model.franchisee.AllMenuResponse;
import com.ats.adminpanel.model.franchisee.FranchiseeList;
import com.ats.adminpanel.model.franchisee.Menu;
import com.ats.adminpanel.model.item.Item;

@Controller
@Scope("session")
public class DumpOrderController {
	 
	public static AllFrIdNameList allFrIdNameList;
	List<Menu> menuList ;//= new ArrayList<Menu>();
	public static List<Menu> selectedMenuList;
	List<String> selectedFrList;
	ArrayList<Integer> selectedFrIdList;
	public static List<Item> items;
	//public static List<GetDumpOrderList> getdumpOrderList;
	public static List<GetDumpOrder> getdumpOrder;
	List<DumpOrderList> dumpOrderList;
	GetDumpOrderList getdumpOrderList;
	int menuId;
	int selectedMainCatId;
	
	@RequestMapping(value = "/showdumporders", method = RequestMethod.GET)
	public ModelAndView showDumpOrder(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("orders/dumporders");
		Constants.mainAct =4;
		Constants.subAct =31;
		
		RestTemplate restTemplate = new RestTemplate();
		try {
	
		AllMenuResponse allMenuResponse = restTemplate.getForObject(Constants.url + "getAllMenu",
				AllMenuResponse.class);
		
		menuList = allMenuResponse.getMenuConfigurationPage();
	
		allFrIdNameList = new AllFrIdNameList();
		
		
		//System.out.println(orderDate);
		
			

		} catch (Exception e) {
			System.out.println("Exception in getAllFrIdName" + e.getMessage());
			e.printStackTrace();

		}
		//List<AllFrIdName> selectedFrListAll=new ArrayList();
		selectedMenuList = new ArrayList<Menu>();
		
		for(int i=0;i<menuList.size();i++)
		{
			if(menuList.get(i).getMenuId()==26||menuList.get(i).getMenuId()==31||menuList.get(i).getMenuId()==33||menuList.get(i).getMenuId()==34)
			{
				selectedMenuList.add(menuList.get(i));
			}
		}
		
		

		System.out.println(" Fr " + allFrIdNameList.getFrIdNamesList());
		
		model.addObject("todayDate",new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
		
		model.addObject("unSelectedMenuList", selectedMenuList);
		model.addObject("unSelectedFrList", allFrIdNameList.getFrIdNamesList());

		return model;
	}
	
	
	

	@RequestMapping(value = "/getNonOrderFrList", method = RequestMethod.GET)
	public @ResponseBody List<AllFrIdName> getNonOrderFrList(HttpServletRequest request,
		HttpServletResponse response) {
		
		
		int menu_id=Integer.parseInt(request.getParameter("menu_id"));
		 
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date date=new java.sql.Date(utilDate.getTime());
		String orderDate=date.toString();
		
		
		MultiValueMap<String, Object> map=new LinkedMultiValueMap<String, Object>();
		map.add("orderDate", orderDate);
		map.add("menuId", menu_id);
		RestTemplate restTemplate = new RestTemplate();
		try {
		allFrIdNameList = restTemplate.postForObject(Constants.url + "getNonOrderFr",map, AllFrIdNameList.class);
		
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return allFrIdNameList.getFrIdNamesList();
	}
	//Ajax Call
	
		@RequestMapping(value = "/getOrderItemList", method = RequestMethod.GET)
		public @ResponseBody List<DumpOrderList> generateItemOrder(HttpServletRequest request,
			HttpServletResponse response) {
			
			//int selectedMainCatId=0;
			String selectOrderDate=request.getParameter("preOrder_Date");
			String selectedFr = request.getParameter("fr_id_list");
			selectedFr=selectedFr.substring(1, selectedFr.length()-1);
			selectedFr=selectedFr.replaceAll("\"", "");
		
			String selectedMenu = request.getParameter("menu_id");
			menuId=Integer.parseInt(selectedMenu);
			System.out.println("here");
			
			System.out.println("Selected Franchisee Ids"+selectedFr);
			//selectedFr = selectedFr.substring(1, selectedFr.length() - 1);
			//selectedFr = selectedFr.replaceAll("\"", "");
			selectedFrList=new ArrayList<>();
			selectedFrList=Arrays.asList(selectedFr.split(","));
			selectedFrIdList=new ArrayList();
			List<AllFrIdName> allFrList=allFrIdNameList.getFrIdNamesList();
			System.out.println("Selected Franchisee");
			 for(int i = 0; i < allFrList.size(); i++) {
				 for(int j=0;j<selectedFrList.size();j++)
				 {
					 if((allFrList.get(i).getFrId())==Integer.parseInt(selectedFrList.get(j)))
					 {
						 System.out.println(allFrList.get(i).getFrName());
						
						 selectedFrIdList.add(allFrList.get(i).getFrId());
					 }
				 }
				
		       }
			 
			
			for(int i=0;i<menuList.size();i++)
			{
				if(menuList.get(i).getMenuId()==menuId)
				{
					selectedMainCatId=menuList.get(i).getMainCatId();
				}
			}
			
			
		System.out.println("Before Rest of Items   and mennu id is  :  "+selectedMenu);
		
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("itemGrp1", selectedMainCatId);
			RestTemplate restTemplate = new RestTemplate();
			try {
			

			ParameterizedTypeReference<List<Item>> typeRef = new ParameterizedTypeReference<List<Item>>() {
			};
			ResponseEntity<List<Item>> responseEntity = restTemplate.exchange(Constants.url + "getItemsByCatIdAndSortId",
					HttpMethod.POST, new HttpEntity<>(map), typeRef);
			
			items = responseEntity.getBody();
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
			MultiValueMap<String, Object> map2 = new LinkedMultiValueMap<String, Object>();
			
			map2.add("date", selectOrderDate);
			map2.add("menuId", selectedMenu);
			
			map2.add("frId", selectedFr);
			//GetDumpOrderList getdumpOrderList;
			List<GetDumpOrder> OrderList = new ArrayList<GetDumpOrder>();
			try {
			getdumpOrderList=restTemplate.postForObject(Constants.url+"getOrderListForDumpOrder", map2, GetDumpOrderList.class);
			
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
			OrderList = getdumpOrderList.getGetDumpOrder();
			System.out.println("List  "+OrderList.toString());
			for(int i=0;i<OrderList.size();i++) {
			System.out.println("Count    "+OrderList.get(i).getItemId() + "  And   "+OrderList.get(i).getFrId() );
			}
				
			
			//dumpOrderList=new ArrayList<DumpOrderList>();
			dumpOrderList=new ArrayList<>();
			
			
			for(int i=0;i<items.size();i++)
			{
				System.out.println("Item ID  "+items.get(i).getId());
				DumpOrderList dumpOrder=new DumpOrderList();
				
				List<OrderData> orderDataList=new ArrayList<OrderData>();
											
				dumpOrder.setItemId(String.valueOf(items.get(i).getId()));
				dumpOrder.setItemName(items.get(i).getItemName());
				
				for(int j=0;j<OrderList.size();j++)
				{
					if(items.get(i).getId()==Integer.parseInt((OrderList.get(j).getItemId())))
					{
						OrderData orderData=new OrderData();
					orderData.setFrId(OrderList.get(j).getFrId());
				
					orderData.setOrderQty(OrderList.get(j).getOrderQty());
					orderDataList.add(orderData);
					
					System.out.println("FR  "+OrderList.get(j).getFrId()+" Item QTY  "+OrderList.get(j).getOrderQty() );
					dumpOrder.setOrderData(orderDataList);
					
					}
					
				}
				System.out.println("List of orders   "+dumpOrder.toString());
				dumpOrderList.add(dumpOrder);
				}
			System.out.println("Final List  :  "+dumpOrderList.toString());
			System.out.println("Count  "+dumpOrderList.size());
			
			
			System.out.println("After Rest of Items   and mennu id is  :");
			
			
			System.out.println("Item List: "+items.toString());
			for (int i=0;i<items.size();i++) {
				
			//	menuId=items.get(i).getMe
			System.out.println(items.get(i).getId());
			}
			
			
			
			 
			return dumpOrderList;
}
	
	

		//After submit order
		
		@RequestMapping(value = "/submitDumpOrder", method = RequestMethod.POST)
		public String submitDumpOrders(HttpServletRequest request, HttpServletResponse response) {
			ModelAndView model = new ModelAndView("orders/dumporders");
			Orders order=new Orders();
			System.out.println("In Submit order call");
		// List<Orders> oList=new ArrayList<>();
		String todaysDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		java.util.Date utilDate = new java.util.Date();
		System.out.println(dateFormat.format(utilDate)); //2016/11/16 12:08:43
		
		java.sql.Date date=new java.sql.Date(utilDate.getTime());
		java.sql.Date deliveryDate=new java.sql.Date(tomarrow().getTime());
		//java.sql.Date deliveryDate=new java.sql.Date(tomarrow1().getTime());
		
		//get all Franchisee details
		RestTemplate restTemplate = new RestTemplate();
		
		
		System.out.println("Before Fr Rest call" );
		AllFranchiseeList allFranchiseeList = restTemplate.getForObject(Constants.url + "getAllFranchisee",
				AllFranchiseeList.class);

		System.out.println("Aftr Fr Rest call" );
		List<FranchiseeList> franchaseeList = new ArrayList<FranchiseeList>();
		franchaseeList = allFranchiseeList.getFranchiseeList();
		
		System.out.println("Items   "+items.toString());
			for(int j=0;j<items.size();j++)
			{
				System.out.println("Items   "+items.get(j).getItemName());
				
				//System.out.println(items.get(j).getId());
				for(int i=0;i<selectedFrIdList.size();i++)
				{
					System.out.println("FR   "+selectedFrIdList.get(i));
				System.out.println(items.get(j).getId());
				
				String quantity=request.getParameter("itemId"+items.get(j).getId()+"orderQty"+selectedFrIdList.get(i));
				//String quantity=request.getParameter("ggg");
				System.out.println("Quantity  "+ quantity);
				int qty=Integer.parseInt(quantity);
				//System.out.println("For Fr and item id"+items.get(j).getId()+"orderQty"+selectedFrIdList.get(i)+"     : "+quantity);
				
				if(qty!=0)
				{
					 List<Orders> oList=new ArrayList<>();
					
					order.setOrderDatetime(todaysDate);
					order.setFrId(selectedFrIdList.get(i));
					order.setRefId(items.get(j).getId());
					order.setItemId(String.valueOf(items.get(j).getId()));
					order.setOrderQty(qty);
					order.setProductionDate(date);
					order.setOrderDate(date);
					order.setDeliveryDate(deliveryDate);
					//order.setMenuId(0);
					order.setGrnType(3);
					order.setIsEdit(0);
					order.setMenuId(menuId);
					order.setOrderType(selectedMainCatId);
					
					
					for(int l=0;l<selectedFrIdList.size();l++)
					{
					for(int k=0;k<franchaseeList.size();k++)
					{
					   if(selectedFrIdList.get(l)==franchaseeList.get(k).getFrId())
							   {
						   			if(franchaseeList.get(k).getFrRateCat()==1)
						   			{
						   				order.setOrderRate(items.get(j).getItemRate1());
						   				order.setOrderMrp(items.get(j).getItemMrp1());
						   			}
						   			else if(franchaseeList.get(k).getFrRateCat()==2)
						   			{
						   				order.setOrderRate(items.get(j).getItemRate2());
						   				order.setOrderMrp(items.get(j).getItemMrp2());
						   			}
						   			else if(franchaseeList.get(k).getFrRateCat()==3)
						   			{
						   				order.setOrderRate(items.get(j).getItemRate3());
						   				order.setOrderMrp(items.get(j).getItemMrp3());
						   			}
						   			
							   }
					}
					}
					
					oList.add(order);
					PlaceOrder(oList);
					
				}
				
			}
		}
			model.addObject("unSelectedFrList", allFrIdNameList.getFrIdNamesList());
			model.addObject("unSelectedMenuList", selectedMenuList);
			
			return "redirect:/showdumporders";
		}
		void PlaceOrder( List<Orders> oList)
		{
//		RestTemplate restTemplate = new RestTemplate();
		System.out.println( "Order list  :   "+oList.toString());

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

		RestTemplate restTemplate=new RestTemplate();
		ResponseEntity<String> orderListResponse = restTemplate.exchange(url, HttpMethod.POST, entity,
				String.class);

		System.out.println("Place Order Response" + orderListResponse.toString());

	}catch (Exception e) {
	System.out.println(e.getMessage());
	}
			
	}
		
		
		public java.util.Date tomarrow()
		{
			
		
			java.util.Date dt = new java.util.Date();
			Calendar c = Calendar.getInstance(); 
			c.setTime(dt); 
			c.add(Calendar.DATE, 1);
			dt = c.getTime();
		return dt;
		}
		
		/*public java.util.Date tomarrow1()
		{
			
		
			java.util.Date dt = new java.util.Date();
			Calendar c = Calendar.getInstance(); 
			c.setTime(dt); 
			c.add(Calendar.DATE, 2);
			dt = c.getTime();
		return dt;
		}*/
		
		
}
