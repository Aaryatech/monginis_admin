package com.ats.adminpanel.controller;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.ats.adminpanel.model.productionplan.GetMixingList;
import com.ats.adminpanel.model.productionplan.MixingDetailed;
import com.ats.adminpanel.model.productionplan.MixingHeader;



@Controller
public class ViewMixingController {
	
	
	
	
	public List<MixingHeader> mixingHeaderList = new ArrayList<MixingHeader>();
	public List<MixingHeader> getMixingListall = new ArrayList<MixingHeader>();
	public List<MixingDetailed> mixwithdetaild = new ArrayList<MixingDetailed>();
	
	public MixingHeader mixingHeader=new MixingHeader();
	
	
	@RequestMapping(value = "/getMixingList", method = RequestMethod.GET)
	public ModelAndView getMixingList(HttpServletRequest request, HttpServletResponse response) {
		/*Constants.mainAct = 17;
		Constants.subAct=184;*/
		
		ModelAndView model = new ModelAndView("productionPlan/getMixinglist");//

		
		
		RestTemplate rest = new RestTemplate();
		
			GetMixingList getMixingList= rest.getForObject(Constants.url + "/gettodaysMixingRequest", GetMixingList.class);
		
		
		
		
		model.addObject("todaysmixrequest",getMixingList.getMixingHeaderList());
		return model;

	}
	
	
	@RequestMapping(value = "/getMixingListWithDate", method = RequestMethod.GET)
	@ResponseBody
	public List<MixingHeader> getMixingListWithDate(HttpServletRequest request, HttpServletResponse response) {
		/*Constants.mainAct = 17;
		Constants.subAct=184;*/
		
		String frmdate=request.getParameter("from_date");
		String todate=request.getParameter("to_date");
		
		System.out.println("in getMixingListWithDate   "+frmdate+todate);
		String frdate=DateConvertor.convertToYMD(frmdate);
		String tdate=DateConvertor.convertToYMD(todate);
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("frmdate",frdate);
		map.add("todate",tdate);
		System.out.println("in getMixingListWithDate   "+frdate+tdate);
		

		RestTemplate rest = new RestTemplate();
		try
		{
			GetMixingList getMixingList= rest.postForObject(Constants.url + "/getMixingHeaderList",map, GetMixingList.class);
			 
			mixingHeaderList = new ArrayList<MixingHeader>();
			System.out.println("getMixingList"+getMixingList.getMixingHeaderList().toString());
			for(int i=0;i<getMixingList.getMixingHeaderList().size();i++)
			{
				MixingHeader mixingHeader=getMixingList.getMixingHeaderList().get(i);
				int Status=mixingHeader.getStatus();
				if(Status==0)
				{
					mixingHeaderList.add(getMixingList.getMixingHeaderList().get(i));
				}
			}
			System.out.println("mixingHeaderList"+mixingHeaderList.toString());
			
		}catch(Exception e)
		{
			System.out.println("error in controller "+e.getMessage());
		}
		return mixingHeaderList;
	

	}
	
	
	@RequestMapping(value = "/getMixingAllListWithDate", method = RequestMethod.GET)
	@ResponseBody
	public List<MixingHeader> getMixingAllListWithDate(HttpServletRequest request, HttpServletResponse response) {
		/*Constants.mainAct = 17;
		Constants.subAct=184;*/
		String frmdate=request.getParameter("from_date");
		String todate=request.getParameter("to_date");
		
		System.out.println("in getMixingListWithDate   "+frmdate+todate);
		String frdate=DateConvertor.convertToYMD(frmdate);
		String tdate=DateConvertor.convertToYMD(todate);
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("frmdate",frdate);
		map.add("todate",tdate);
		System.out.println("in getMixingListWithDate   "+frdate+tdate);
		RestTemplate rest = new RestTemplate();
		GetMixingList getMixingList= rest.postForObject(Constants.url + "/getMixingHeaderList",map, GetMixingList.class);
		getMixingListall  = getMixingList.getMixingHeaderList();
		return getMixingListall;
	

	}
	
	@RequestMapping(value = "/viewDetailMixRequest", method = RequestMethod.GET)
	public ModelAndView viewDetailMixRequest(HttpServletRequest request, HttpServletResponse response) {
		/*Constants.mainAct = 17;
		Constants.subAct=184;*/
		ModelAndView model = new ModelAndView("productionPlan/showmixindetailed");
		
		
		//String mixId=request.getParameter("mixId");
		int mixId=Integer.parseInt(request.getParameter("mixId"));
		System.out.println(mixId);
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("mixId",mixId);
		RestTemplate rest = new RestTemplate();
		 mixingHeader=rest.postForObject(Constants.url + "/getDetailedwithMixId",map, MixingHeader.class);
		 mixwithdetaild =mixingHeader.getMixingDetailed();
		
		 Date mixdate = mixingHeader.getMixDate();
		 
		 SimpleDateFormat dtFormat=new SimpleDateFormat("dd-MM-yyyy");
			
		 String date=dtFormat.format(mixdate);
				
		 System.out.println("date" +date);
			model.addObject("date",date);

			model.addObject("mixheader",mixingHeader);
		model.addObject("mixwithdetaild", mixwithdetaild);
		
		return model;
	}
	
	@RequestMapping(value = "/updateProdctionQty", method = RequestMethod.POST)
	public String updateProdctionQty(HttpServletRequest request, HttpServletResponse response) {
		/*Constants.mainAct = 17;
		Constants.subAct=184;*/
		
		for(int i=0;i<mixingHeader.getMixingDetailed().size();i++)
		{
			System.out.println(12);
			 
			 
				System.out.println(13);
				String production_Qty=request.getParameter("production_Qty"+mixingHeader.getMixingDetailed().get(i).getMixing_detailId());
				
				if(production_Qty!=null) {
					System.out.println("production_Qty Qty   :"+production_Qty);
					int productionQty=Integer.parseInt(production_Qty);
					mixingHeader.getMixingDetailed().get(i).setProductionQty(productionQty);
					System.out.println("productionQty  :"+productionQty);
				}
				else
				{
					mixingHeader.getMixingDetailed().get(i).setProductionQty(0);
				}
				System.out.println(2);
			 
		}
		mixingHeader.setStatus(2);
		mixingHeader.setMixingDetailed(mixingHeader.getMixingDetailed());
		
		System.out.println(mixingHeader.toString());
		
		RestTemplate rest = new RestTemplate();
		
		
		
		
		mixingHeader=rest.postForObject(Constants.url + "/insertMixingHeaderndDetailed",mixingHeader, MixingHeader.class);	
		
		
		return "redirect:/getMixingList";
	}
	
	
	

}
