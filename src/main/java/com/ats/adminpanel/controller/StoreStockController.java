package com.ats.adminpanel.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.ats.adminpanel.commons.DateConvertor;
import com.ats.adminpanel.model.BmsStockDetailed;
import com.ats.adminpanel.model.Info;
import com.ats.adminpanel.model.RawMaterial.RawMaterialDetails;
import com.ats.adminpanel.model.RawMaterial.RawMaterialDetailsList;
import com.ats.adminpanel.model.RawMaterial.RawMaterialUom;
import com.ats.adminpanel.model.RawMaterial.RawMaterialUomList;
import com.ats.adminpanel.model.item.FrItemStockConfigureList;
import com.ats.adminpanel.model.login.UserResponse;
import com.ats.adminpanel.model.stock.GetStoreCurrentStock;
import com.ats.adminpanel.model.stock.StoreStockDetail;
import com.ats.adminpanel.model.stock.StoreStockDetailList;
import com.ats.adminpanel.model.stock.StoreStockHeader;

@Controller
@Scope("session")
public class StoreStockController {

	public RawMaterialDetailsList rawMaterialDetailsList;
	
	public  List<GetStoreCurrentStock> getStoreCurrentStockList;
	
	RestTemplate rest = new RestTemplate();
	
	@RequestMapping(value = "/showStoreOpeningStock", method = RequestMethod.GET)
	public ModelAndView showStoreOpeningStock(HttpServletRequest request, HttpServletResponse response) {
		 Constants.mainAct =10;
		Constants.subAct=65; 
		
		ModelAndView model = new ModelAndView("stock/storeOpeningStock");//
		
		
		try {
		 RawMaterialUomList rawMaterialUomList = rest.getForObject(Constants.url + "rawMaterial/getRmUomList",
	                RawMaterialUomList.class);

	      
	        
	            rawMaterialDetailsList=rest.getForObject(Constants.url +"rawMaterial/getAllRawMaterial", RawMaterialDetailsList.class);
			
	        System.out.println("Uom List "+rawMaterialUomList.getRawMaterialUom().toString());
	        System.out.println("Rm List  "+rawMaterialDetailsList.getRawMaterialDetailsList().toString());
	        model.addObject("uomList",rawMaterialUomList.getRawMaterialUom());
	        model.addObject("rmList",rawMaterialDetailsList.getRawMaterialDetailsList());
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return model;

	}
	
	
	@RequestMapping(value = "/insertStoreOpeningStock", method = RequestMethod.POST)
	public String insertStoreOpeningStock(HttpServletRequest request, HttpServletResponse response) {

		RawMaterialDetails rawMaterialDetails=new RawMaterialDetails();
		StoreStockHeader storeStockHeader=new StoreStockHeader();
		List<StoreStockDetail> storeStockDetailList=new ArrayList<>();
		StoreStockDetail storeStockDetail=new StoreStockDetail();
		storeStockHeader.setStoreStockDate(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
		storeStockHeader.setStoreStockStatus(0);
		storeStockHeader.setExBoll1(0);
		storeStockHeader.setExBoll2(0);
		storeStockHeader.setExInt1(0);
		storeStockHeader.setExInt2(0);
		 
		
		for(int i=0;i<rawMaterialDetailsList.getRawMaterialDetailsList().size();i++)
		{
			storeStockDetail=new StoreStockDetail();
			rawMaterialDetails=rawMaterialDetailsList.getRawMaterialDetailsList().get(i);
			storeStockDetail.setRmId(rawMaterialDetails.getRmId());
			storeStockDetail.setBmsIssueQty(0);
			storeStockDetail.setExBool(0);
			storeStockDetail.setExInt1(0);
			storeStockDetail.setExInt2(0);
			storeStockDetail.setIsDelete(0);
			storeStockDetail.setPurRecQty(0.0f);
			storeStockDetail.setStoreStockDate(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
			storeStockDetail.setRmGroup(rawMaterialDetails.getGrpId());
			storeStockDetail.setRmName(rawMaterialDetails.getRmName());
			storeStockDetail.setRmUom(rawMaterialDetails.getRmUomId());
			storeStockDetail.setStoreClosingStock(0);
			
			int openingStock=Integer.parseInt(request.getParameter("stockQty"+rawMaterialDetails.getRmId()));
			storeStockDetail.setStoreOpeningStock(openingStock);
			storeStockDetailList.add(storeStockDetail);
		}
		storeStockHeader.setStoreStockDetailList(storeStockDetailList);
		System.out.println("Before Insert "+storeStockHeader.toString());
		  storeStockHeader=rest.postForObject(Constants.url +"insertStoreOpeningStock",storeStockHeader, StoreStockHeader.class);
		
		System.out.println("Res : "+storeStockHeader.toString());
		return "redirect:/showStoreOpeningStock";
	}
	
	@RequestMapping(value = "/showStoreStock", method = RequestMethod.GET)
	public ModelAndView showStoreStock(HttpServletRequest request, HttpServletResponse response) {
		 Constants.mainAct =10;
		Constants.subAct=66; 
		 
		ModelAndView model = new ModelAndView("stock/storeStock");//
		
		  getStoreCurrentStockList=new ArrayList<GetStoreCurrentStock>();
		
		
		return model;
	}
		
	
	@RequestMapping(value = "/getMonthWiseStoreStock", method = RequestMethod.GET)
	public @ResponseBody List<StoreStockDetail> getMonthWiseStock(HttpServletRequest request,
			HttpServletResponse response) {
		
		StoreStockDetail storeStockDetail=new StoreStockDetail();
	
		System.out.println("in method");
		String fromDate=request.getParameter("fromDate");
		String toDate=request.getParameter("toDate");
		System.out.println("Date  "+fromDate+"And "+toDate);
		
		DateTimeFormatter f = DateTimeFormatter.ofPattern( "dd-MM-uuuu" );
		
		YearMonth ym = YearMonth.parse( fromDate , f );
		System.out.println(1);
		LocalDate fDate = ym.atDay( 1 );
		System.out.println("fdate"+fDate);
        
		YearMonth ym1 = YearMonth.parse( toDate , f );
		LocalDate tDate = ym1.atEndOfMonth();
		System.out.println("tdate"+tDate);
		
		if(tDate.isAfter(LocalDate.now()) || tDate.isEqual(LocalDate.now())){
			System.out.println("GGGGGGGGGGGGGGG   Date is greater than today"+LocalDate.now().minus(Period.ofDays(1)));
			tDate=LocalDate.now().minus(Period.ofDays(1));
			}
		System.out.println("Month Date    :"+ tDate+"     "+ fDate);
		
		MultiValueMap<String, Object> map=new LinkedMultiValueMap<>();
		map.add("fromDate",""+fDate);
		map.add("toDate",""+tDate);
		 
		StoreStockDetailList storeStockDetailList=rest.postForObject(Constants.url +"getMonthWiseStoreStock", map, StoreStockDetailList.class);
		System.out.println("Res List "+storeStockDetailList.toString());
		
		List<StoreStockDetail> storeStockList=new ArrayList<StoreStockDetail>();
		if(storeStockDetailList.getStoreStockDetailList()!=null && !storeStockDetailList.getStoreStockDetailList().isEmpty())
				storeStockList=storeStockDetailList.getStoreStockDetailList();
		return  storeStockList;
		
		 
	}
	
	@RequestMapping(value = "/getDateWiseStoreStock", method = RequestMethod.GET)
	public @ResponseBody List<StoreStockDetail> getDateWiseStoreStock(HttpServletRequest request,
			HttpServletResponse response) {
		
		StoreStockDetail storeStockDetail=new StoreStockDetail();
	
		System.out.println("in method");
		String fromDate=request.getParameter("fromDate");
		String toDate=request.getParameter("toDate");
		System.out.println("Date  "+fromDate+"And "+toDate);
		
		DateTimeFormatter f = DateTimeFormatter.ofPattern( "dd-MM-uuuu" );
		  LocalDate tDate = LocalDate.parse(toDate, f);
		
		if(tDate.isAfter(LocalDate.now()) || tDate.isEqual(LocalDate.now())){
			System.out.println("GGGGGGGGGGGGGGG   Date is greater than today"+LocalDate.now().minus(Period.ofDays(1)));
			tDate=LocalDate.now().minus(Period.ofDays(1));
			
			}
		System.out.println("ppppppppp  "+tDate);
		MultiValueMap<String, Object> map=new LinkedMultiValueMap<>();
		map.add("fromDate", DateConvertor.convertToYMD(fromDate));
		map.add("toDate", ""+tDate );
		 
		StoreStockDetailList storeStockDetailList=rest.postForObject(Constants.url +"getMonthWiseStoreStock", map, StoreStockDetailList.class);
		System.out.println("Res List "+storeStockDetailList.toString());
		List<StoreStockDetail> storeStockList=new ArrayList<StoreStockDetail>();
		if(storeStockDetailList.getStoreStockDetailList()!=null && !storeStockDetailList.getStoreStockDetailList().isEmpty())
				storeStockList=storeStockDetailList.getStoreStockDetailList();
		return storeStockList;
	}
	
	//getCurrentStoreStock
	
	@RequestMapping(value = "/getCurrentStoreStock", method = RequestMethod.GET)
	public @ResponseBody List<GetStoreCurrentStock> getCurrentStoreStock(HttpServletRequest request,
			HttpServletResponse response) {
		
		getStoreCurrentStockList=new ArrayList<>();
		
		System.out.println("in method");
		 
		HttpSession session=request.getSession();
		UserResponse userResponse =(UserResponse) session.getAttribute("UserDetail");
		int deptId=userResponse.getUser().getDeptId();
		
		String settingKey = new String();
		MultiValueMap<String, Object> map=new LinkedMultiValueMap<>();
		settingKey = "STORE";

		map.add("settingKeyList", settingKey);

		FrItemStockConfigureList settingList = rest.postForObject(Constants.url + "getDeptSettingValue", map,
				FrItemStockConfigureList.class);
		try {
		map=new LinkedMultiValueMap<>();
		map.add("deptId", settingList.getFrItemStockConfigure().get(0).getSettingValue());
		 
		System.out.println("Dept id :"+settingList.getFrItemStockConfigure().get(0).getSettingValue());
		ParameterizedTypeReference<List<GetStoreCurrentStock>> typeRef = new ParameterizedTypeReference<List<GetStoreCurrentStock>>() {
		};
		ResponseEntity<List<GetStoreCurrentStock>> responseEntity = rest.exchange(Constants.url + "getCurrentStoreStock",
				HttpMethod.POST, new HttpEntity<>(map), typeRef);
		
		getStoreCurrentStockList = responseEntity.getBody();
		System.out.println("Res List "+getStoreCurrentStockList.toString());

		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Res List "+getStoreCurrentStockList.toString());
		
		return getStoreCurrentStockList;
	}
	
	//getCurrentStoreStockHeader
	
	@RequestMapping(value = "/dayEndStoreStock", method = RequestMethod.POST)
	public String dayEndStoreStock(HttpServletRequest request, HttpServletResponse response) {

		
		//getStoreCurrentStockList
		StoreStockHeader storeStockHeader=new StoreStockHeader();
		
		
		MultiValueMap<String, Object> map=new LinkedMultiValueMap<>();
		map.add("status", 0);
		
		storeStockHeader=rest.postForObject(Constants.url +"getCurrentStoreStockHeader", map, StoreStockHeader.class);
		
		List<StoreStockDetail> storeStockDetailList=storeStockHeader.getStoreStockDetailList();
		for(int i=0;i<storeStockDetailList.size();i++)
		{
			for(int j=0;j<getStoreCurrentStockList.size();j++)
			{
				if(storeStockDetailList.get(i).getRmId()==getStoreCurrentStockList.get(j).getRmId())
				{
					storeStockDetailList.get(i).setPurRecQty(getStoreCurrentStockList.get(j).getPurRecQty());
					storeStockDetailList.get(i).setBmsIssueQty(getStoreCurrentStockList.get(j).getBmsIssueQty());
					 float closingQty=getStoreCurrentStockList.get(j).getStoreOpeningStock()+getStoreCurrentStockList.get(j).getPurRecQty()-getStoreCurrentStockList.get(j).getBmsIssueQty();
					storeStockDetailList.get(i).setStoreClosingStock(closingQty);
					break;
				}
			}
		}
		
		storeStockHeader.setStoreStockStatus(1);
		storeStockHeader.setStoreStockDetailList(storeStockDetailList);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(storeStockHeader.getStoreStockDate()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.add(Calendar.DATE, 1);  // number of days to add
		String nextDate = sdf.format(c.getTime());
		
		storeStockHeader =rest.postForObject(Constants.url +"insertStoreOpeningStock", storeStockHeader, StoreStockHeader.class);
		 
		
		//insert next day Stock
		
		
		 RawMaterialUomList rawMaterialUomList = rest.getForObject(Constants.url + "rawMaterial/getRmUomList",
	                RawMaterialUomList.class);

	      
	        
		 RawMaterialDetailsList  rawMaterialDetailsList=rest.getForObject(Constants.url +"rawMaterial/getAllRawMaterial", RawMaterialDetailsList.class);
			
		 
		 RawMaterialDetails rawMaterialDetails=new RawMaterialDetails();
			  storeStockHeader=new StoreStockHeader();
			  List<StoreStockDetail>  newStoreStockDetailList=new ArrayList<>();
				StoreStockDetail storeStockDetail=new StoreStockDetail();
			storeStockHeader.setStoreStockDate(nextDate);
			storeStockHeader.setStoreStockStatus(0);
			storeStockHeader.setExBoll1(0);
			storeStockHeader.setExBoll2(0);
			storeStockHeader.setExInt1(0);
			storeStockHeader.setExInt2(0);
			 
			
			for(int i=0;i<rawMaterialDetailsList.getRawMaterialDetailsList().size();i++)
			{
				for(int j=0;j< storeStockDetailList.size();j++) {
					if(storeStockDetailList.get(j).getRmId()==rawMaterialDetailsList.getRawMaterialDetailsList().get(i).getRmId())
					{
				storeStockDetail=new StoreStockDetail();
				rawMaterialDetails=rawMaterialDetailsList.getRawMaterialDetailsList().get(i);
				storeStockDetail.setRmId(rawMaterialDetails.getRmId());
				storeStockDetail.setBmsIssueQty(0);
				storeStockDetail.setExBool(0);
				storeStockDetail.setExInt1(0);
				storeStockDetail.setExInt2(0);
				storeStockDetail.setIsDelete(0);
				storeStockDetail.setPurRecQty(0.0f);
				storeStockDetail.setStoreStockDate(nextDate);
				storeStockDetail.setRmGroup(rawMaterialDetails.getGrpId());
				storeStockDetail.setRmName(rawMaterialDetails.getRmName());
				storeStockDetail.setRmUom(rawMaterialDetails.getRmUomId());
				storeStockDetail.setStoreClosingStock(0);
				
				 
				storeStockDetail.setStoreOpeningStock(storeStockDetailList.get(j).getStoreClosingStock());
				newStoreStockDetailList.add(storeStockDetail);
				break;
			}
				}
			}
			storeStockHeader.setStoreStockDetailList(newStoreStockDetailList);
			System.out.println("Before Insert "+storeStockHeader.toString());
			  storeStockHeader=rest.postForObject(Constants.url +"insertStoreOpeningStock",storeStockHeader, StoreStockHeader.class);
			
			System.out.println("Res : "+storeStockHeader.toString());
		
		
		return "redirect:/showStoreStock";
	}
		
}
