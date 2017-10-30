package com.ats.adminpanel.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.model.AllEventListResponse;
import com.ats.adminpanel.model.AllRoutesListResponse;
import com.ats.adminpanel.model.SpecialCake;
import com.ats.adminpanel.model.Event;
import com.ats.adminpanel.model.EventNameId;
import com.ats.adminpanel.model.Info;
import com.ats.adminpanel.model.InsertSpCakeResponse;
import com.ats.adminpanel.model.Login;
import com.ats.adminpanel.model.Route;
import com.ats.adminpanel.model.SpCakeResponse;
import com.ats.adminpanel.model.SpecialCake;
import com.ats.adminpanel.model.ViewSpCakeResponse;
import com.ats.adminpanel.model.masters.AllRatesResponse;
import com.ats.adminpanel.model.masters.Rate;
import com.ats.adminpanel.util.ImageS3Util;
import com.ats.adminpanel.model.ViewSpCakeListResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class SpecialCakeController {
	private static final Logger logger = LoggerFactory.getLogger(SpecialCakeController.class);

	@RequestMapping(value = "/addSpCake", method = RequestMethod.GET)

	public ModelAndView redirectToAddSpCake(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("spcake/addspcake");
		Constants.mainAct=3;
		Constants.subAct=31;
		RestTemplate restTemplate = new RestTemplate();
		try {

			AllEventListResponse allEventListResponse = restTemplate.getForObject(Constants.url + "showEventList",
					AllEventListResponse.class);

			List<Event> eventList = new ArrayList<Event>();
			eventList = allEventListResponse.getEvent();
			System.out.println("Event List" + eventList.toString());
			model.addObject("eventList", eventList);

			// for rate
			AllRatesResponse allRatesResponse = restTemplate.getForObject(Constants.url + "getAllRates",
					AllRatesResponse.class);

			List<Rate> rateList = new ArrayList<Rate>();
			rateList = allRatesResponse.getRates();
			System.out.println("Rate List" + rateList.toString());
			//model.addObject("rateList", rateList);

		} catch (Exception e) {
			System.out.println("Error in event list display" + e.getMessage());
		}

		return model;
	}

	@RequestMapping(value = "/showSpecialCake", method = RequestMethod.GET)

	public ModelAndView redirectToSpCakeList(HttpServletRequest request, HttpServletResponse response) {

		System.out.println("inside ViewSpCkeList");
		ModelAndView model = new ModelAndView("spcake/spcakelist");
		Constants.mainAct=3;
		Constants.subAct=32;
		RestTemplate restTemplate = new RestTemplate();

		try {
			SpCakeResponse spCakeResponse = restTemplate.getForObject(Constants.url + "showSpecialCakeList",
					SpCakeResponse.class);
			System.out.println("SpCake Controller SpCakeList Response " + spCakeResponse.toString());
			List<SpecialCake> specialCakeList = new ArrayList<SpecialCake>();

			specialCakeList = spCakeResponse.getSpecialCake();
			System.out.println("my cake list");

			System.out.println("CakeList=" + specialCakeList.toString());
			System.out.println("--------------------");
			
			System.out.println("name="+specialCakeList.get(0).getSpName());
			System.out.println("type="+specialCakeList.get(0).getSpType());

			model.addObject("specialCakeList", specialCakeList);// 1 object to be used in jsp 2 actual object
			model.addObject("url",Constants.SPCAKE_IMAGE_URL);
			
			

		} catch (Exception e) {
			System.out.println("Show Sp Cake List Excep: " + e.getMessage());
		}
		return model;
	}

	@RequestMapping(value = "/showSpecialCake")

	public ModelAndView redirectToSpecialcakeshow(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("spcake/spcakelist");

		return model;
	}

	
	
	
	@RequestMapping(value = "/addSpCakeProcess", method = RequestMethod.POST)

	public String redirectToLogin56(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("sp_image") MultipartFile file) {
		ModelAndView model = new ModelAndView("spcake/addspcake");

		RestTemplate rest = new RestTemplate();

		String code = request.getParameter("spc_code");
		System.out.println("code"+code);
		
		/*String spimg = request.getParameter("sp_image");
		System.out.println("spimg"+spimg);*/
		
		String name = request.getParameter("spc_name");
		System.out.println("name"+name);

		int type = Integer.parseInt(request.getParameter("spc_type"));
		System.out.println("type"+type);
		
		String minwt = request.getParameter("min_weight");
		System.out.println("minwt"+minwt);
		
		String maxwt = request.getParameter("max_weight");
		System.out.println("maxwt"+maxwt);
		
		String bookb4 = request.getParameter("book_before");
		System.out.println("bookb4"+bookb4);
		
		String spDesc = request.getParameter("sp_desc");
		System.out.println("spDesc"+spDesc);
		
		int orderQty = Integer.parseInt(request.getParameter("order_qty"));
		System.out.println("orderQty"+orderQty);
		
		float orderDisc = Float.parseFloat(request.getParameter("order_disc"));
		
		int spRate1 = Integer.parseInt(request.getParameter("sp_rate1"));
		
		int spRate2 = Integer.parseInt(request.getParameter("sp_rate2"));
		
		int spRate3 = Integer.parseInt(request.getParameter("sp_rate3"));
		
		int mrpRate1 = Integer.parseInt(request.getParameter("mrp_rate1"));
		
		int mrpRate2 = Integer.parseInt(request.getParameter("mrp_rate2"));
		
		int mrpRate3 = Integer.parseInt(request.getParameter("mrp_rate3"));
		
		double tx1 = Double.parseDouble(request.getParameter("tax_1"));
		
		double tx2 = Double.parseDouble(request.getParameter("tax_2"));
		
		double tx3 = Double.parseDouble(request.getParameter("tax_3"));

		String[] eventtypes = (request.getParameterValues("spe_id_list[]"));
		
		System.out.println("event type array is" + eventtypes[0]);

		String erplinkcode = request.getParameter("erplinkcode");
		
		int isCustChoiceCk = Integer.parseInt(request.getParameter("is_cust_choice_ck"));
		
		int isAddonRateAppli = Integer.parseInt(request.getParameter("is_addon_rate_appli"));
		
		int type2app = Integer.parseInt(request.getParameter("type_2_applicable"));
		
		int isused = Integer.parseInt(request.getParameter("is_used"));
		
		int allowphupload = Integer.parseInt(request.getParameter("allowphupload"));
		
		int isSlotUsed = Integer.parseInt(request.getParameter("isSlotUsed"));
		System.out.println("isSlotUsed"+isSlotUsed);
		
		StringBuilder sb = new StringBuilder();

		String strEventTypes = "";
		for (int i = 0; i < eventtypes.length; i++) {
			
			sb = sb.append(eventtypes[i] + ",");

		}
		String strEvents = sb.toString();
		
		strEvents = strEvents.substring(0, strEvents.length() - 1);
		System.out.println("Event id list is" + strEvents.toString());
		
		
		 String spImage=	ImageS3Util.uploadSpCakeImage(file);
		
		

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("spCode", code);
		map.add("spName", name);
		map.add("spType", type);
		map.add("spMinwt", minwt);
		map.add("spMaxwt", maxwt);
		map.add("spBookb4", bookb4);
		
		map.add("spImage", spImage);
		map.add("spTax1", tx1);
		map.add("spTax2", tx2);
		map.add("spTax3", tx3);
		map.add("speIdlist", strEvents);
		map.add("erpLinkcode", erplinkcode);
		map.add("timeTwoappli", type2app);
		map.add("isUsed", isused);
		map.add("spPhoupload", allowphupload);
		
	
		map.add("spDesc",spDesc);
		map.add("orderQty",orderQty);
		map.add("orderDiscount",orderDisc);
		map.add("isCustChoiceCk",isCustChoiceCk);
		map.add("isAddonRateAppli",isAddonRateAppli);
		map.add("mrpRate1",mrpRate1);
		map.add("mrpRate2",mrpRate2);
		map.add("mrpRate3",mrpRate3);
		map.add("spRate1",spRate1);
		map.add("spRate2",spRate2);
		map.add("spRate3",spRate3);
		map.add("isSlotUsed",isSlotUsed);


		
		
		System.out.println("map value for spc_type " + request.getParameter("spc_type"));
		try {
			String spcakeResponse = rest.postForObject(Constants.url + "insertSpecialCake", map, String.class);
			System.out.println(spcakeResponse);

			model = new ModelAndView("addSpCake");

		} catch (Exception e) {
			System.out.println("AddSpCakeProcess Excep: " + e.getMessage());
		}

		return "redirect:/showSpecialCake";

	}

	@RequestMapping(value = "/deleteSpecialCake/{spId}", method = RequestMethod.GET)
	public String deleteSpecialCake(@PathVariable int spId) {
		ModelAndView model = new ModelAndView("spcake/spcakelist");

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("spId", spId);
		RestTemplate restTemplate = new RestTemplate();
		Info info = restTemplate.postForObject(Constants.url + "deleteSpecialCake", map, Info.class);

		if (info.getError()) {
			return "redirect:/showSpecialcake";
		} else {
			return "redirect:/showSpecialCake";

		}

	}

	@RequestMapping(value = "/updateSpCake/{spId}")

	public ModelAndView redirectToshowUpdateSpCake(@PathVariable int spId, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("spcake/editspcake");
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		map.add("spId", spId);
		RestTemplate restTemplate = new RestTemplate();

		SpecialCake specialCake = restTemplate.getForObject(Constants.url + "getSpecialCake?spId={spId}",
				SpecialCake.class, spId);

		AllEventListResponse allEventListResponse = restTemplate.getForObject(Constants.url + "showEventList",
				AllEventListResponse.class);

		List<Event> eventList = new ArrayList<Event>();
		eventList = allEventListResponse.getEvent();
		//System.out.println("Event List" + eventList.toString());

		String strSpeIdList = specialCake.getSpeIdlist();

		List<String> speIdListArray = Arrays.asList(strSpeIdList.split("\\s*,\\s*"));

		//System.out.println("SP Event id list List" + speIdListArray.toString());

		List<EventNameId> spePrevEvents = new ArrayList<EventNameId>();

		for (int i = 0; i < speIdListArray.size(); i++) {
			//System.out.println("outer for " + i);

			for (int j = 0; j < eventList.size(); j++) {
				/*System.out.println("inner for " + j);

				System.out.println("inner for compare eventlist id=" + eventList.get(j).getSpeId() + " & arraylist id="
						+ speIdListArray.get(i));
*/
				if (eventList.get(j).getSpeId().toString().equalsIgnoreCase(speIdListArray.get(i))) {
					System.out.println("inner if i=" + i + " j=" + j);
					EventNameId nameId = new EventNameId();
					nameId.setId(speIdListArray.get(i));
					nameId.setName(eventList.get(j).getSpeName());
					spePrevEvents.add(nameId);
					model.addObject("nameId",nameId);
				}

			}

		}
		System.out.println("SP Event Name Id List" + spePrevEvents.toString());

		model.addObject("specialCake", specialCake);
		model.addObject("spEventsIdList", speIdListArray);
		model.addObject("speEventNameId", spePrevEvents);
	

		AllRatesResponse allRatesResponse = restTemplate.getForObject(Constants.url + "getAllRates",
				AllRatesResponse.class);

		List<Rate> rateList = new ArrayList<Rate>();
		rateList = allRatesResponse.getRates();
		//System.out.println("Rate List" + rateList.toString());
		//model.addObject("rateList", rateList);
		//List<ItemNameId>=new ArrayList<>

		for (int j = 0; j < speIdListArray.size(); j++) {

			for (int i = 0; i < eventList.size(); i++) {
				if (eventList.get(i).getSpeId().toString().equals(speIdListArray.get(j))) {
					eventList.remove(i);
				}

			}
		}
	
		
String rateName="";
int rate=0;
		
		for (int j = 0; j < rateList.size(); j++) {

			
			if(rateList.get(j).getSprId()==specialCake.getSprId()) {
				
				rateName=rateList.get(j).getSprName();
				rate=rateList.get(j).getSprRate();
				System.out.println("rate name ===="+rateName);
			}
			
		}
		model.addObject("rateName",rateName);
		model.addObject("rate",rate);

		
		
						//System.out.println(" my event list is " + eventList.toString());
		model.addObject("eventList", eventList);
		

		for (int j = 0; j < rateList.size(); j++) {

			
				if(rateList.get(j).getSprId()==specialCake.getSprId()) {
					rateList.remove(j);
				
				
			}
		}
		
		model.addObject("rateList",rateList);
		
		int isSlotUsed=specialCake.getIsSlotUsed();
		String strIsSlotUsed= String.valueOf(isSlotUsed);
		model.addObject("isSlotUsed", strIsSlotUsed);
		
		int timeTwoappli = specialCake.getTimeTwoappli();
		String strTimeTwoappli = String.valueOf(timeTwoappli);
		//System.out.println("time 2 appli is==" + strTimeTwoappli);
		model.addObject("strTimeTwoappli", strTimeTwoappli);
		
		int isUsed=specialCake.getIsUsed();
		String strIsUsed=String.valueOf(isUsed);
		model.addObject("strIsUsed",strIsUsed);
		int allowPhUp=specialCake.getSpPhoupload();
		String strallowPhUp=String.valueOf(allowPhUp);
		model.addObject("strallowPhUp",strallowPhUp);
		
		int isCustChoiceCk=specialCake.getIsCustChoiceCk();
		String strIsCustChoiceCk=String.valueOf(isCustChoiceCk);
		model.addObject("strIsCustChoiceCk",strIsCustChoiceCk);
		
		
		int isAddonRateAppli=specialCake.getIsAddonRateAppli();
		String strIsAddonRateAppli=String.valueOf(isAddonRateAppli);
		model.addObject("strIsAddonRateAppli",strIsAddonRateAppli);
		
		System.out.println("name  -----------for update="+specialCake.getSpName());
		
		
		String cakeType="";
		int spcName=specialCake.getSpType();
		switch(spcName) {
		case 1:
			cakeType="Chocolate";
			break;
		case 2:
			cakeType="FC";
			break;
		case 0:
			cakeType="All";
			break;
			default:
				cakeType="";
				 break;
					
		}
		model.addObject("cakeType",cakeType);
		System.out.println("cake type======" +cakeType);
		model.addObject("url",Constants.SPCAKE_IMAGE_URL);
		
		return model;

	}

	// @RequestMapping(value = "/updateSpCakeProcess")

	@RequestMapping(value = "/updateSpCake/updateSpCakeProcess", method = RequestMethod.POST)

	public String redirectToUpdateSpCakeProcess(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("sp_image") MultipartFile file) {
		try {
		ModelAndView model = new ModelAndView("spcake/editspcake");

		RestTemplate restTemplate = new RestTemplate();

		String code = request.getParameter("spc_code");
		System.out.println("spc code "+code);
		String name = request.getParameter("spc_name");
		
		System.out.println("SPC name== " + name);

		int type = Integer.parseInt(request.getParameter("spc_type"));
		System.out.println("spc-type"+type);

		String minwt = request.getParameter("min_weight");
		System.out.println("min wt"+minwt);

		String maxwt = request.getParameter("max_weight");
		System.out.println("max wt"+maxwt);

		String bookb4 = request.getParameter("book_before");
		System.out.println("book b4"+bookb4);

	    String spDesc = request.getParameter("sp_desc");
	    System.out.println("spDesc"+spDesc);
	    
		int orderQty = Integer.parseInt(request.getParameter("order_qty"));
		System.out.println("orderQty"+orderQty);
		
		
		float orderDisc = Float.parseFloat(request.getParameter("order_disc"));
		System.out.println("orderDisc"+orderDisc);
		
		
        int spRate1 = Integer.parseInt(request.getParameter("sp_rate1"));
    	
		        
		
		int spRate2 = Integer.parseInt(request.getParameter("sp_rate2"));
		
		int spRate3 = Integer.parseInt(request.getParameter("sp_rate3"));
		System.out.println("spRate1,2,3"+spRate1+""+spRate2+""+spRate2+""+spRate3);
		int mrpRate1 = Integer.parseInt(request.getParameter("mrp_rate1"));
		
		int mrpRate2 = Integer.parseInt(request.getParameter("mrp_rate2"));
		
		int mrpRate3 = Integer.parseInt(request.getParameter("mrp_rate3"));
		System.out.println("mrpRate1,2,3"+mrpRate1+""+mrpRate2+""+mrpRate3);
	    int isCustChoiceCk = Integer.parseInt(request.getParameter("is_cust_choice_ck"));
		System.out.println("isCustChoiceCk"+isCustChoiceCk);

		int isAddonRateAppli = Integer.parseInt(request.getParameter("is_addon_rate_appli"));
		System.out.println("isAddonRateAppli"+isAddonRateAppli);

		int isSlotUsed=Integer.parseInt(request.getParameter("isSlotUsed"));
		System.out.println("isSlotUsed"+isSlotUsed);
	
		/*String spimg = request.getParameter("sp_image");
		System.out.println(" fr image"+spimg);
*/
		double tx1 = Double.parseDouble(request.getParameter("tax_1"));
		System.out.println("tx 1 "+tx1);

		double tx2 = Double.parseDouble(request.getParameter("tax_2"));
		System.out.println("tx2 "+tx2);

		double tx3 = Double.parseDouble(request.getParameter("tax_3"));
		System.out.println("tx3 " +tx3);


		String[] eventtypes = (request.getParameterValues("spe_id_list[]"));
		


		String erplinkcode = request.getParameter("erplinkcode");
		System.out.println("erp code"+erplinkcode);

		int type2app = Integer.parseInt(request.getParameter("type_2_applicable"));
		System.out.println("type 2 applicable"+type2app);

		int isused = Integer.parseInt(request.getParameter("is_used"));
		System.out.println("is used="+isused);

		int allowphupload = Integer.parseInt(request.getParameter("allowphupload"));
		System.out.println("allow ph"+allowphupload);

		int id = Integer.parseInt(request.getParameter("spId"));
		System.out.println("id "+id);
		

		StringBuilder sb = new StringBuilder();
		String strEventTypes = "";
		for (int i = 0; i < eventtypes.length; i++) {
			sb = sb.append(eventtypes[i] + ",");

		}
		String strEvents = sb.toString();
		strEvents = strEvents.substring(0, strEvents.length() - 1);
		System.out.println("Event id list is" + strEvents.toString());
		
		
		
		String spImage=request.getParameter("prevImage");
		
		
		if(!file.getOriginalFilename().equalsIgnoreCase("")) {
			
			System.out.println("Empty image");
			spImage=	ImageS3Util.uploadSpCakeImage(file);
		}
		
		
		// String spImage=	ImageS3Util.uploadSpCakeImage(file);

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("spCode", code);
		map.add("spname", name);
		map.add("sptype", type);
		map.add("spminwt", minwt);
		map.add("spmaxwt", maxwt);
		map.add("spbookb4", bookb4);
		map.add("spimage", spImage);
		map.add("sptax1", tx1);
		map.add("sptax2", tx2);
		map.add("sptax3", tx3);
		map.add("spidlist", strEvents);
		map.add("erplinkcode", erplinkcode);
		map.add("timetwoappli", type2app);
		map.add("isUsed", isused);
		map.add("spphoupload", allowphupload);
		map.add("id", id);
		
		map.add("spDesc",spDesc);
		map.add("orderQty",orderQty);
		map.add("orderDiscount",orderDisc);
		map.add("isCustChoiceCk",isCustChoiceCk);
		map.add("isAddonRateAppli",isAddonRateAppli);
		map.add("mrpRate1",mrpRate1);
		map.add("mrpRate2",mrpRate2);
		map.add("mrpRate3",mrpRate3);
		map.add("spRate1",spRate1);
		map.add("spRate2",spRate2);
		map.add("spRate3",spRate3);
		map.add("isSlotUsed",isSlotUsed);
		
		System.out.println("sp name is "+name);
		//System.out.println("type 2 value in update event"+type2app);
		
			String cakeResponse = restTemplate.postForObject(Constants.url + "updateSpecialCake", map,
					String.class);
		}
		catch(Exception e)
		{
		System.out.println("Message::"+e.getMessage());
			
		}
		
		return "redirect:/showSpecialCake";

	}

}
