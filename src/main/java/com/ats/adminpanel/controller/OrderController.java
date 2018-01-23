package com.ats.adminpanel.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.spi.LocationAwareLogger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
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
import com.ats.adminpanel.model.ExportToExcel;
import com.ats.adminpanel.model.GetOrder;
import com.ats.adminpanel.model.GetOrderListResponse;
import com.ats.adminpanel.model.GetRegSpCakeOrders;
import com.ats.adminpanel.model.GetSpCakeOrders;
import com.ats.adminpanel.model.Info;
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
@Scope("session")
public class OrderController {
	public List<FranchiseeList> franchiseeList= new ArrayList<FranchiseeList>();
	  public List<FranchiseeList> tempFrList= new ArrayList<FranchiseeList>();
	  public  List<FranchiseeList> selectedFrList= new ArrayList<FranchiseeList>();
	public List<SpCakeOrdersBean> spCakeOrderList = new ArrayList<SpCakeOrdersBean>();
	List<GetOrder> orderList=null;
	public List<Menu> menuList;
	SpCakeOrdersBeanResponse orderListResponse;
	RegularSpCkOrdersResponse regOrderListResponse ;
	
	@RequestMapping(value = "/showOrders")
	public ModelAndView searchOrder(HttpServletRequest request, HttpServletResponse response) {
		
		//ModelAndView model=new  ModelAndView("orders/orders");
		ModelAndView model=new  ModelAndView("orders/orders");
		Constants.mainAct=4;
		Constants.subAct=27;

		
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
		orderList = new ArrayList<GetOrder>();
		
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
		

		
	List<ExportToExcel> exportToExcelList=new ArrayList<ExportToExcel>();
		
		ExportToExcel expoExcel=new ExportToExcel();
		List<String> rowData=new ArrayList<String>();
		 
		rowData.add("Franchisee Name");
		rowData.add("Type");
		rowData.add("Item Id");
		rowData.add("Item Name");
		rowData.add("Quantity");
	
		
		 
		 
		
		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);
		for(int i=0;i<orderList.size();i++)
		{
			  expoExcel=new ExportToExcel();
			 rowData=new ArrayList<String>();
			 
			rowData.add(orderList.get(i).getFrName());
			
			rowData.add(orderList.get(i).getCatName());
			rowData.add(""+orderList.get(i).getId());
			rowData.add(orderList.get(i).getItemName());
			rowData.add(""+orderList.get(i).getOrderQty());
			
			
			 
			
			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);
			System.out.println("List"+orderList.get(i).toString());
		}
		 
		
		
		HttpSession session = request.getSession();
		session.setAttribute("exportExcelList", exportToExcelList);
		session.setAttribute("excelName", "Orders");
		
		return orderList;
	}

	//special cake orders
	
	
	@RequestMapping(value = "/spCakeOrders")
	public ModelAndView searchSpCakeOrder(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView model=new ModelAndView("orders/spcakeorders");
		Constants.mainAct=4;
		Constants.subAct=28;
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
		Constants.mainAct=4;
		Constants.subAct=29;
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
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		RestTemplate restTemplate1 = new RestTemplate();
		spCakeOrderList = new ArrayList<SpCakeOrdersBean>();
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
		 
		
		List<ExportToExcel> exportToExcelList=new ArrayList<ExportToExcel>();
		
		ExportToExcel expoExcel=new ExportToExcel();
		List<String> rowData=new ArrayList<String>();
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
		for(int i=0;i<spCakeOrderList.size();i++)
		{
			  expoExcel=new ExportToExcel();
			 rowData=new ArrayList<String>();
			rowData.add(""+spCakeOrderList.get(i).getSpOrderNo());
			rowData.add(spCakeOrderList.get(i).getFrName());
		
			rowData.add(spCakeOrderList.get(i).getItemId());
			rowData.add(spCakeOrderList.get(i).getSpCode());
			
			rowData.add(spCakeOrderList.get(i).getSpfName());
			rowData.add(spCakeOrderList.get(i).getSpName());
			rowData.add(spCakeOrderList.get(i).getSpEvents());
			rowData.add(""+spCakeOrderList.get(i).getSpPrice());
			rowData.add(""+spCakeOrderList.get(i).getSpTotalAddRate());
			
			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);
			System.out.println("List"+spCakeOrderList.get(i).toString());
		}
		 
		
		
		HttpSession session = request.getSession();
		session.setAttribute("exportExcelList", exportToExcelList);
		session.setAttribute("excelName", "SpCakeOrders");
		return spCakeOrderList;
	}
	
	boolean isDelete=false;
	public String[] frIds=null;
	public String prodDate=null;
	@RequestMapping(value = "/regularSpCkOrderProcess",method = RequestMethod.POST)
	public ModelAndView regularSpCkOrderProcess(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = null;
		System.out.println("/inside search sp cake order process  ");

		try {
			model = new ModelAndView("orders/regularsporders");
			//model.addObject("franchiseeList", franchiseeList);
			model.addObject("isDelete",0);

			 frIds= request.getParameterValues("fr_id[]");

			 prodDate=request.getParameter("prod_date");
			System.out.println("prodDate:"+prodDate);
		
			List<String> frIdList = (List) Arrays.asList(frIds);
			
			RestTemplate restTemplate = new RestTemplate();

			List<FranchiseeList> selectedFrList=new ArrayList<>();
			List<FranchiseeList> remFrList=new ArrayList<FranchiseeList>();
			
			AllFranchiseeList allFranchiseeList=restTemplate.getForObject(Constants.url+"getAllFranchisee",AllFranchiseeList.class);
			
			franchiseeList=allFranchiseeList.getFranchiseeList();
			remFrList=franchiseeList;

			try {
			for(int i=0;i<frIdList.size();i++)
			{
				for(int j=0;j<franchiseeList.size();j++)
				{
					if(Integer.parseInt(frIdList.get(i))==franchiseeList.get(j).getFrId())
					{
						selectedFrList.add(franchiseeList.get(j));
						remFrList.remove(j);
					}
				}
			}
			}
			catch (NullPointerException e) {
                System.out.println("Null Pointer Exc in Reg Sp Order");
			}
			catch(Exception e)
			{
                System.out.println(" Exc in Reg Sp Order:order Controller"+e.getMessage());

			}

			
			model.addObject("todayDate", prodDate);
			model.addObject("frIdList", selectedFrList);
			model.addObject("franchiseeList", remFrList);
			
			if (frIds[0].toString().equals("0")) {
				System.out.println("all fr selected");
				model.addObject("frIdList", franchiseeList);

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

		
	List<ExportToExcel> exportToExcelList=new ArrayList<ExportToExcel>();
		
		ExportToExcel expoExcel=new ExportToExcel();
		List<String> rowData=new ArrayList<String>();
		 
		rowData.add("Franchisee Name");
	 
		rowData.add("Item Id");
		rowData.add("Item Name");
		
		rowData.add("Mrp");
		rowData.add("Rate");
		
		rowData.add("Quantity");
	
		rowData.add("Sub Total");
		 
		 
		
		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);
		for(int i=0;i<regOrderListResponse.getRegularSpCkOrdersList().size();i++)
		{
			  expoExcel=new ExportToExcel();
			 rowData=new ArrayList<String>();
			 
			rowData.add(regOrderListResponse.getRegularSpCkOrdersList().get(i).getFrName());
			
			rowData.add(""+regOrderListResponse.getRegularSpCkOrdersList().get(i).getId());
			 
			rowData.add(regOrderListResponse.getRegularSpCkOrdersList().get(i).getItemName());
			
			rowData.add(""+regOrderListResponse.getRegularSpCkOrdersList().get(i).getMrp());
			rowData.add(""+regOrderListResponse.getRegularSpCkOrdersList().get(i).getRate());
			rowData.add(""+regOrderListResponse.getRegularSpCkOrdersList().get(i).getQty());
			rowData.add(""+regOrderListResponse.getRegularSpCkOrdersList().get(i).getRspSubTotal());
			
			
			
			 
			
			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);
			 
		}
		 
		
		
		HttpSession session = request.getSession();
		session.setAttribute("exportExcelList", exportToExcelList);
		session.setAttribute("excelName", "RegSpCakeOrders");
		
		return model;
	}

	//ganesh 24-10-2017
	
	@RequestMapping(value = "/callDeleteOrder",method = RequestMethod.GET)
	public  @ResponseBody  List<GetOrder> deleteOrder(HttpServletRequest request, HttpServletResponse response) {
	
		try {
		System.out.println("/inside delete order process  ");
        int orderId = Integer.parseInt(request.getParameter("order_id"));
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("orderId", orderId);
		RestTemplate restTemp = new RestTemplate();
		Integer isDeleted= restTemp.postForObject(Constants.url + "DeleteOrder", map, Integer.class);
		
		if(isDeleted!=0)
		{
			if(!orderList.isEmpty())
			{
				for(int i=0;i<orderList.size();i++)
				{
					if(orderList.get(i).getOrderId()==orderId)
					{
						orderList.remove(i);
					}
				}
			}
		}
		}
		catch(Exception e)
		{
			System.out.println("Exception In delete Order"+e.getMessage());
		}
		return orderList;
	}
	@RequestMapping(value = "/deleteSpOrder",method = RequestMethod.GET)
	public  @ResponseBody List<SpCakeOrdersBean> deleteSpOrder(HttpServletRequest request, HttpServletResponse response) {
	
		System.out.println("/inside delete Sporder process  ");
		
		int spOrderNo = Integer.parseInt(request.getParameter("sp_order_no"));
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("spOrderNo", spOrderNo);

		RestTemplate restTemp = new RestTemplate();

		Info info= restTemp.postForObject(Constants.url + "deleteSpCkOrder", map, Info.class);
		
		if(info.getError()==false)
		{
			if(!spCakeOrderList.isEmpty())
			{
				for(int i=0;i<spCakeOrderList.size();i++)
				{
					if(spCakeOrderList.get(i).getSpOrderNo()==spOrderNo)
					{
						spCakeOrderList.remove(i);
					}
				}
			}
		}
		return spCakeOrderList;
	}
	@RequestMapping(value = "/deleteRegSpOrder/{rspId}",method = RequestMethod.GET)
	public  ModelAndView deleteRegSpOrder(@PathVariable int rspId,HttpServletRequest request, HttpServletResponse response) {
		
	
		ModelAndView model = new ModelAndView("orders/regularsporders");

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("rspId", rspId);
		
		RestTemplate restTemp = new RestTemplate();

		Info info= restTemp.postForObject(Constants.url + "deleteRegularSpOrder", map, Info.class);
		System.out.println("Info"+info.toString());
		
		List<String> frIdList = (List) Arrays.asList(frIds);
		
		RestTemplate restTemplate = new RestTemplate();

		List<FranchiseeList> selectedFrList=new ArrayList<>();
		List<FranchiseeList> remFrList=new ArrayList<FranchiseeList>();
		
		AllFranchiseeList allFranchiseeList=restTemplate.getForObject(Constants.url+"getAllFranchisee",AllFranchiseeList.class);
		
		franchiseeList=allFranchiseeList.getFranchiseeList();
		remFrList=franchiseeList;

		try {
		for(int i=0;i<frIdList.size();i++)
		{
			for(int j=0;j<franchiseeList.size();j++)
			{
				if(Integer.parseInt(frIdList.get(i))==franchiseeList.get(j).getFrId())
				{
					selectedFrList.add(franchiseeList.get(j));
					remFrList.remove(j);
				}
			}
		}
		}
		catch (NullPointerException e) {
            System.out.println("Null Pointer Exc in Reg Sp Order");
		}
		catch(Exception e)
		{
            System.out.println(" Exc in Reg Sp Order:order Controller"+e.getMessage());

		}

		
		model.addObject("todayDate", prodDate);
		model.addObject("frIdList", selectedFrList);
		model.addObject("franchiseeList", remFrList);
		model.addObject("isDelete",1);
		return model;
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
