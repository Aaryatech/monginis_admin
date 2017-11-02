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

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;


 
@Controller
public class PushOrderController {
	AllFrIdNameList allFrIdNameList;
	List<Menu> menuList ;//= new ArrayList<Menu>();
	List<String> selectedFrList;
	ArrayList<Integer> selectedFrIdList;
	public static List<Item> items;
	int menuId;
	int selectedMainCatId;
	
	@RequestMapping(value = "/showpushorders", method = RequestMethod.GET)
	public ModelAndView showPushOrder(HttpServletRequest request, HttpServletResponse response) {

		

		ModelAndView model = new ModelAndView("orders/pushorders");
		Constants.mainAct = 8;
		Constants.subAct = 82;

		

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
		List<Menu> selectedMenuList = new ArrayList<Menu>();

		System.out.println(" Fr " + allFrIdNameList.getFrIdNamesList());

		
		model.addObject("unSelectedMenuList", menuList);
		model.addObject("unSelectedFrList", allFrIdNameList.getFrIdNamesList());

		return model;
	}
	
	
	
	//Ajax call

	@RequestMapping(value = "/getItemList", method = RequestMethod.GET)
	public @ResponseBody List<Item> generateItemList(HttpServletRequest request,
		HttpServletResponse response) {
		
		//int selectedMainCatId=0;
		String selectedMenu = request.getParameter("menu_id");
		menuId=Integer.parseInt(selectedMenu);
		
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
		
		try {
		RestTemplate restTemplate = new RestTemplate();

		ParameterizedTypeReference<List<Item>> typeRef = new ParameterizedTypeReference<List<Item>>() {
		};
		ResponseEntity<List<Item>> responseEntity = restTemplate.exchange(Constants.url + "getItemsByCatId",
				HttpMethod.POST, new HttpEntity<>(map), typeRef);
		
		items = responseEntity.getBody();
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("After Rest of Items   and mennu id is  :");
		
		//items=rest.postForObject(Constants.url + "getItemsByCatId",map,List.class);
		System.out.println("Item List: "+items.toString());
		for (int i=0;i<items.size();i++) {
			
		//	menuId=items.get(i).getMe
		System.out.println(items.get(i).getId());
		}
		
		String selectedFr = request.getParameter("fr_id_list");
		
		
		System.out.println("Selected Franchisee Ids"+selectedFr);

		
		selectedFr = selectedFr.substring(1, selectedFr.length() - 1);
		selectedFr = selectedFr.replaceAll("\"", "");

		
		selectedFrList=new ArrayList<>();
		
		selectedFrList=Arrays.asList(selectedFr.split(","));
		
		selectedFrIdList=new ArrayList();
		List<AllFrIdName> allFrList=allFrIdNameList.getFrIdNamesList();
		System.out.println("Selected Franchisee");
		 for(int i = 0; i < allFrList.size(); i++) {
			 
			 for(int j=0;j<selectedFrList.size();j++)
			 {
					//System.out.println("Current Fr"+selectedFrList.get(j));

				 if((allFrList.get(i).getFrId())==Integer.parseInt(selectedFrList.get(j)))
				 {
					 System.out.println(allFrList.get(i).getFrName());
					
					 selectedFrIdList.add(allFrList.get(i).getFrId());
				 }
			 }
			
	           
	        }
		
		return items;
	}
	
	
	//After submit order
	
	@RequestMapping(value = "/submitPushOrder", method = RequestMethod.POST)
	public ModelAndView submitPushOrders(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("orders/pushorders");
		Orders order=new Orders();
		
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
	
	
	
	AllFranchiseeList allFranchiseeList = restTemplate.getForObject(Constants.url + "getAllFranchisee",
			AllFranchiseeList.class);


	List<FranchiseeList> franchaseeList = new ArrayList<FranchiseeList>();
	franchaseeList = allFranchiseeList.getFranchiseeList();
	
	
		for(int j=0;j<items.size();j++)
		{
			
			//System.out.println(items.get(j).getId());
			for(int i=0;i<selectedFrIdList.size();i++)
			{
				
			System.out.println(items.get(j).getId());
			
			String quantity=request.getParameter("itemId"+items.get(j).getId()+"orderQty"+selectedFrIdList.get(i));
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
				order.setMenuId(0);
				order.setGrnType(2);
				order.setIsEdit(1);
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
					   				order.setOrderRate(items.get(j).getItemRate1()*qty);
					   				order.setOrderMrp(items.get(j).getItemMrp1());
					   			}
					   			else if(franchaseeList.get(k).getFrRateCat()==2)
					   			{
					   				order.setOrderRate(items.get(j).getItemRate2()*qty);
					   				order.setOrderMrp(items.get(j).getItemMrp2());
					   			}
					   			else if(franchaseeList.get(k).getFrRateCat()==3)
					   			{
					   				order.setOrderRate(items.get(j).getItemRate3()*qty);
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
		model.addObject("unSelectedMenuList", menuList);
		model.addObject("unSelectedFrList", allFrIdNameList.getFrIdNamesList());
		
		return model;
	}
	void PlaceOrder( List<Orders> oList)
	{
//	RestTemplate restTemplate = new RestTemplate();
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