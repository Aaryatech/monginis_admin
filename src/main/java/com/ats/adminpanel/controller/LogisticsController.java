package com.ats.adminpanel.controller; 
import java.util.ArrayList; 
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.ats.adminpanel.commons.Constants; 
import com.ats.adminpanel.model.Info;
import com.ats.adminpanel.model.logistics.Dealer;
import com.ats.adminpanel.model.logistics.Document;
import com.ats.adminpanel.model.logistics.DriverMaster;
import com.ats.adminpanel.model.logistics.Make;
import com.ats.adminpanel.model.logistics.SparePart;
import com.ats.adminpanel.model.logistics.SprGroup;
import com.ats.adminpanel.model.logistics.Variant;
import com.ats.adminpanel.model.logistics.VehicalMaster;
import com.ats.adminpanel.model.logistics.VehicalType; 

@Controller
@Scope("session")
public class LogisticsController {
	
	RestTemplate restTemplate = new RestTemplate();
	
	//-------------------------------------------DRIVER-------------------------------------------------------
	
	@RequestMapping(value = "/showDriverList", method = RequestMethod.GET)
	public ModelAndView showBmsStock(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("logistics/insertDriver"); 
		try
		{
			
			List<DriverMaster> driverList = restTemplate.getForObject(Constants.url + "getAllDriverList", List.class);
			System.out.println("driverList"+driverList.toString());
			model.addObject("driverList",driverList);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return model;

	}
	
	
	@RequestMapping(value = "/insertNewDriver", method = RequestMethod.POST) 
	public String insertNewDriver(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("logistics/insertDriver"); 
		List<DriverMaster> driverList = new ArrayList<DriverMaster>();
		

		try
			{ 
				String driverId = request.getParameter("driverId");
		    	System.out.println("driverId"+driverId);
	        	String driverName = request.getParameter("driver_name");
	        	String add1 = request.getParameter("add1");
	        	String add2 = request.getParameter("add2");
	        	String mob1 = request.getParameter("mob1");
	        	String mob2 = request.getParameter("mob2");
	        	String mob3 = request.getParameter("mob3");
	        	String dob_date = request.getParameter("dob");
	        	String joining_Date = request.getParameter("joining_date");
	        	String licNo = request.getParameter("lic_no");
	        	String licExpr_Date = request.getParameter("lic_expr_date");
	        	System.out.println("driverId"+driverId);
	        	System.out.println("driverName"+driverName);
	        	System.out.println("add1"+add1);
	        	System.out.println("add2"+add2);
	        	System.out.println("mob1"+mob1);
	        	System.out.println("mob2"+mob2);
	        	System.out.println("mob3"+mob3);
	        	System.out.println("dob"+dob_date);
	        	System.out.println("joiningDate"+joining_Date);
	        	System.out.println("licNo"+licNo);
	        	System.out.println("licExprDate"+licExpr_Date);
	        	
	        		DriverMaster insertDriver = new DriverMaster(); 
	        		if(driverId.equals("") || driverId==null)
	        			insertDriver.setDriverId(0);
	        		else
	        			insertDriver.setDriverId(Integer.parseInt(driverId));
		        	insertDriver.setDriverName(driverName);
		        	insertDriver.setAddress1(add1);
		        	insertDriver.setAddress2(add2);
		        	insertDriver.setMobile1(mob1);
		        	insertDriver.setMobile2(mob2);
		        	insertDriver.setMobile3(mob3);
		        	insertDriver.setDriverDob(dob_date);
		        	insertDriver.setJoiningDate(joining_Date);
		        	insertDriver.setLicNo(licNo);
		        	insertDriver.setLicExpireDate(licExpr_Date);
		        	System.out.println("insertDriver " + insertDriver);
		        	DriverMaster driverMaster = restTemplate.postForObject(Constants.url + "postDriverMaster", insertDriver, DriverMaster.class);
	        	 
	        	 
	        	
			 
			 
			 System.out.println("driverList"+driverList);
		}catch(Exception e)
		{
			System.out.println("errorr  "+e.getMessage());
			e.printStackTrace();
		}
	        driverList = restTemplate.getForObject(Constants.url + "getAllDriverList", List.class);
			 
			 model.addObject("driverList",driverList);
		return "redirect:/showDriverList";
		

	}
	
	@RequestMapping(value = "/editDriver", method = RequestMethod.GET)
	@ResponseBody
	public DriverMaster editDriver(HttpServletRequest request, HttpServletResponse response) {
		
		DriverMaster editDriver = new DriverMaster();
	        try
			{ 
	        	
	        	int driverId = Integer.parseInt(request.getParameter("driverId"));
	        	 System.out.println("driverId"+driverId);
	        	 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object >();
	        	 map.add("driverId", driverId);
	        	
	        	 editDriver = restTemplate.postForObject(Constants.url + "getDriverById", map, DriverMaster.class);
			 System.out.println("editDriver " + editDriver); 
		}catch(Exception e)
		{
			System.out.println("errorr  "+e.getMessage());
			e.printStackTrace();
		}
	         
		return editDriver;
		

	}
	
	@RequestMapping(value = "/deleteDriver/{driverId}", method = RequestMethod.GET) 
	public String deleteDriver(@PathVariable int driverId, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("logistics/insertDriver"); 
		List<DriverMaster> driverList = new ArrayList<DriverMaster>();
		

		try
			{ 
				 
	        	 
	        	 System.out.println("driverId"+driverId);
	        	 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object >();
	        	 map.add("driverId", driverId);
	        	
	        	 Info info = restTemplate.postForObject(Constants.url + "deleteDriverMaster", map, Info.class);
	        		 
			 
			 System.out.println("info"+info);
		}catch(Exception e)
		{
			System.out.println("errorr  "+e.getMessage());
			e.printStackTrace();
		}
	        driverList = restTemplate.getForObject(Constants.url + "getAllDriverList", List.class);
			 
			 model.addObject("driverList",driverList);
		return "redirect:/showDriverList";
		

	}
	
	//----------------------------------------------------MAKE-----------------------------------------------------------------
	
	@RequestMapping(value = "/showMakeList", method = RequestMethod.GET)
	public ModelAndView showMakeList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("logistics/showMakeList"); 
		try
		{
			
			List<Make> makeList = restTemplate.getForObject(Constants.url + "getAllMakeList", List.class);
			System.out.println("makeList"+makeList.toString());
			model.addObject("makeList",makeList);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return model;

	}
	
	@RequestMapping(value = "/insertMake", method = RequestMethod.GET)
	@ResponseBody
	public List<Make> insertMake(HttpServletRequest request, HttpServletResponse response) { 
		try
		{
			Make Make = new Make();
			String makeId = request.getParameter("makeId");
			String makeName = request.getParameter("compny_name");
			System.out.println("makeName " + makeName + "makeId" +makeId);
			Make.setMakeName(makeName);
			 
				Make.setMakeId(Integer.parseInt(makeId));
			
			Make insertMake = restTemplate.postForObject(Constants.url + "postMake",Make, Make.class);
			System.out.println("makeList"+insertMake.toString());
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		List<Make> makeList = restTemplate.getForObject(Constants.url + "getAllMakeList", List.class);
		System.out.println("makeList"+makeList.toString());
		 
		return makeList;

	}
	
	@RequestMapping(value = "/editMake", method = RequestMethod.GET)
	@ResponseBody
	public Make editMake(HttpServletRequest request, HttpServletResponse response) {
		 
		Make editMake = new Make();
	        try
			{ 
	        	
	        	int makeId = Integer.parseInt(request.getParameter("makeId"));
	        	 System.out.println("makeId"+makeId);
	        	 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object >();
	        	 map.add("makeId", makeId);
	        	
	        	 editMake = restTemplate.postForObject(Constants.url + "getMakeById", map, Make.class);
			 System.out.println("editDriver " + editMake); 
		}catch(Exception e)
		{
			System.out.println("errorr  "+e.getMessage());
			e.printStackTrace();
		}
	         
		return editMake;
		

	}
	
	@RequestMapping(value = "/deleteMake/{makeId}", method = RequestMethod.GET)
	public String deleteMake(@PathVariable int makeId, HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("logistics/showMakeList"); 
		try
		{
			 System.out.println("makeId"+makeId);
        	 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object >();
        	 map.add("makeId", makeId);
			Info info = restTemplate.postForObject(Constants.url + "deleteMake",map, Info.class);
			System.out.println("info"+info.toString()); 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		 
		return "redirect:/showMakeList";

	}
	
	//------------------------------------------------------DEALER------------------------------------------------------------
	
	
	@RequestMapping(value = "/showDealarList", method = RequestMethod.GET)
	public ModelAndView showDealarList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("logistics/showDealarList"); 
		try
		{
			
			List<Dealer> getAllDealerList = restTemplate.getForObject(Constants.url + "getAllDealerList", List.class);
			System.out.println("getAllDealerList"+getAllDealerList.toString());
			List<Make> makeList = restTemplate.getForObject(Constants.url + "getAllMakeList", List.class);
			System.out.println("makeList"+makeList.toString());
			model.addObject("makeList",makeList);
			model.addObject("dealerList",getAllDealerList);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return model;

	}
	
	@RequestMapping(value = "/insertNewDealer", method = RequestMethod.POST)
	public String  insertNewDealer(HttpServletRequest request, HttpServletResponse response) {

	 
		try
		{
			String dealerId = request.getParameter("dealerId");
			String dName = request.getParameter("dealerName");
			String dMobile = request.getParameter("mob1");
			String dEmail = request.getParameter("email");
			String city = request.getParameter("city");
			int makeId = Integer.parseInt(request.getParameter("makeId"));
			String contactPerson = request.getParameter("cntprn");
			String pMobile = request.getParameter("mob2");
			String pEmail = request.getParameter("email2");

			Dealer insertDealer = new Dealer();
			if(dealerId==null || dealerId.equals(""))
				insertDealer.setDealerId(0);
			else
				insertDealer.setDealerId(Integer.parseInt(dealerId));
			insertDealer.setDealerName(dName);
			insertDealer.setDealerMobileNo(dMobile);
			insertDealer.setCity(city);
			insertDealer.setDealerEmail(dEmail);
			insertDealer.setMakeId(makeId);
			insertDealer.setContactPerson(contactPerson);
			insertDealer.setPersonMobileNo(pMobile);
			insertDealer.setContactPersonEmail(pEmail);
			
			
			
			
			insertDealer = restTemplate.postForObject(Constants.url + "postDealer",insertDealer, Dealer.class);
			System.out.println("insertDealer"+insertDealer.toString());
		 
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return "redirect:/showDealarList";

	}
	
	@RequestMapping(value = "/deleteDealer/{dealerId}", method = RequestMethod.GET)
	public String deleteDealer(@PathVariable int dealerId, HttpServletRequest request, HttpServletResponse response) {

		 
		try
		{
			 System.out.println("dealerId"+dealerId);
        	 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object >();
        	 map.add("dealerId", dealerId);
        	 Info info = restTemplate.postForObject(Constants.url + "deleteDealer",map, Info.class);
 			System.out.println("info"+info.toString()); 
		 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		 
		return "redirect:/showDealarList";

	}
	
	@RequestMapping(value = "/editDealer", method = RequestMethod.GET)
	@ResponseBody
	public Dealer editDealer(HttpServletRequest request, HttpServletResponse response) {
		 
		Dealer editDealer = new Dealer();
	        try
			{ 
	        	
	        	int dealerId = Integer.parseInt(request.getParameter("dealerId"));
	        	 System.out.println("dealerId"+dealerId);
	        	 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object >();
	        	 map.add("dealerId", dealerId);
	        	
	        	 editDealer = restTemplate.postForObject(Constants.url + "getDealerById", map, Dealer.class);
			 System.out.println("editDealer " + editDealer); 
		}catch(Exception e)
		{
			System.out.println("errorr  "+e.getMessage());
			e.printStackTrace();
		}
	         
		return editDealer;
		

	}
	
	//-------------------------------------------------VEHTYPE------------------------------------------------------
	
	@RequestMapping(value = "/showVehTypeList", method = RequestMethod.GET)
	public ModelAndView showVehTypeList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("logistics/showVehTypeList"); 
		try
		{
			
			List<VehicalType> getAllVehicalTypeList = restTemplate.getForObject(Constants.url + "getAllVehicalTypeList", List.class);
			System.out.println("getAllVehicalTypeList"+getAllVehicalTypeList.toString());
			List<Make> makeList = restTemplate.getForObject(Constants.url + "getAllMakeList", List.class);
			System.out.println("makeList"+makeList.toString());
			model.addObject("makeList",makeList);
			model.addObject("vehicalTypeList",getAllVehicalTypeList);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return model;

	}
	
	@RequestMapping(value = "/insertNewType", method = RequestMethod.POST)
	public String  insertNewType(HttpServletRequest request, HttpServletResponse response) {

	 
		try
		{
			String typeId = request.getParameter("typeId");
			String typeName = request.getParameter("typeName");
			int makeId = Integer.parseInt(request.getParameter("makeId"));
			 

			VehicalType insertVehicalType = new VehicalType();
			if(typeId==null || typeId.equals(""))
				insertVehicalType.setVehiTypeId(0);
			else
				insertVehicalType.setVehiTypeId(Integer.parseInt(typeId));
			insertVehicalType.setVehTypeName(typeName);
			insertVehicalType.setMakeId(makeId);
			  
			insertVehicalType = restTemplate.postForObject(Constants.url + "postVahType",insertVehicalType, VehicalType.class);
			System.out.println("insertVehicalType"+insertVehicalType.toString());
		 
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return "redirect:/showVehTypeList";

	}
	
	@RequestMapping(value = "/editVehicleType", method = RequestMethod.GET)
	@ResponseBody
	public VehicalType editVehicleType(HttpServletRequest request, HttpServletResponse response) {
		 
		VehicalType editVehicleType = new VehicalType();
	        try
			{ 
	        	
	        	int vehiTypeId = Integer.parseInt(request.getParameter("vehiTypeId"));
	        	 System.out.println("vehiTypeId"+vehiTypeId);
	        	 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object >();
	        	 map.add("vehiTypeId", vehiTypeId);
	        	
	        	 editVehicleType = restTemplate.postForObject(Constants.url + "getVehTypeById", map, VehicalType.class);
			 System.out.println("editVehicleType " + editVehicleType); 
		}catch(Exception e)
		{
			System.out.println("errorr  "+e.getMessage());
			e.printStackTrace();
		}
	         
		return editVehicleType;
		

	}
	
	@RequestMapping(value = "/deleteVehicleType/{vehiTypeId}", method = RequestMethod.GET)
	public String deleteVehicleType(@PathVariable int vehiTypeId, HttpServletRequest request, HttpServletResponse response) {

		 
		try
		{
			 System.out.println("vehiTypeId"+vehiTypeId);
        	 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object >();
        	 map.add("vehId", vehiTypeId);
        	 Info info = restTemplate.postForObject(Constants.url + "deleteVehType",map, Info.class);
 			System.out.println("info"+info.toString()); 
		 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		 
		return "redirect:/showVehTypeList";

	}
	
	//--------------------------------------Variant----------------------------------------------
	
	@RequestMapping(value = "/showVariantList", method = RequestMethod.GET)
	public ModelAndView showVariantList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("logistics/showVariantList"); 
		try
		{
			List<Variant> getAllVariantList = restTemplate.getForObject(Constants.url + "getAllVariantList", List.class);
			System.out.println("getAllVariantList"+getAllVariantList.toString());
			List<VehicalType> getAllVehicalTypeList = restTemplate.getForObject(Constants.url + "getAllVehicalTypeList", List.class);
			System.out.println("getAllVehicalTypeList"+getAllVehicalTypeList.toString());
			List<Make> makeList = restTemplate.getForObject(Constants.url + "getAllMakeList", List.class);
			System.out.println("makeList"+makeList.toString());
			model.addObject("makeList",makeList);
			model.addObject("vehicalTypeList",getAllVehicalTypeList);
			model.addObject("variantList",getAllVariantList);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return model;

	}
	
	@RequestMapping(value = "/typeByMakeId", method = RequestMethod.GET)
	@ResponseBody
	public List<VehicalType> typeByMakeId(HttpServletRequest request, HttpServletResponse response) {
		 
		List<VehicalType> typeByMakeId = new ArrayList<VehicalType>();
	        try
			{ 
	        	
	        	int makeId = Integer.parseInt(request.getParameter("makeId"));
	        	 System.out.println("makeId"+makeId);
	        	 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object >();
	        	 map.add("makeId", makeId);
	        	
	        	 typeByMakeId = restTemplate.postForObject(Constants.url + "typeByMakeId", map, List.class);
			 System.out.println("typeByMakeId " + typeByMakeId); 
		}catch(Exception e)
		{
			System.out.println("errorr  "+e.getMessage());
			e.printStackTrace();
		}
	         
		return typeByMakeId;
		

	}
	
	@RequestMapping(value = "/insertVariant", method = RequestMethod.POST)
	public String  insertVariant(HttpServletRequest request, HttpServletResponse response) {

	 
		try
		{
			String variantId = request.getParameter("variantId");
			int typeId = Integer.parseInt(request.getParameter("typeId"));
			String variantName = request.getParameter("variantName");
			int makeId = Integer.parseInt(request.getParameter("makeId"));
			 

			Variant insertVariant = new Variant();
			if(variantId==null || variantId.equals(""))
				insertVariant.setVariantId(0);
			else
				insertVariant.setVariantId(Integer.parseInt(variantId));
			insertVariant.setVariantName(variantName); 
			insertVariant.setVehTypeId(typeId); 
			insertVariant.setMakeId(makeId);
			  
			insertVariant = restTemplate.postForObject(Constants.url + "postVariant",insertVariant, Variant.class);
			System.out.println("insertVariant"+insertVariant.toString());
		 
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return "redirect:/showVariantList";

	}
	
	@RequestMapping(value = "/deleteVariant/{variantId}", method = RequestMethod.GET)
	public String deleteVariant(@PathVariable int variantId, HttpServletRequest request, HttpServletResponse response) {

		 
		try
		{
			 System.out.println("variantId"+variantId);
        	 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object >();
        	 map.add("variantId", variantId);
        	 Info info = restTemplate.postForObject(Constants.url + "deleteVariant",map, Info.class);
 			System.out.println("info"+info.toString()); 
		 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		 
		return "redirect:/showVariantList";

	}
	
	@RequestMapping(value = "/editVariant", method = RequestMethod.GET)
	@ResponseBody
	public Variant editVariant(HttpServletRequest request, HttpServletResponse response) {
		 
		Variant editVariant = new Variant();
	        try
			{ 
	        	
	        	int variantId = Integer.parseInt(request.getParameter("variantId"));
	        	 System.out.println("variantId"+variantId);
	        	 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object >();
	        	 map.add("variantId", variantId);
	        	
	        	 editVariant = restTemplate.postForObject(Constants.url + "getVariantById", map, Variant.class);
			 System.out.println("editVariant " + editVariant); 
		}catch(Exception e)
		{
			System.out.println("errorr  "+e.getMessage());
			e.printStackTrace();
		}
	         
		return editVariant;
		

	}
	
	//-----------------------------------------------DOCUMENT-------------------------------------------
	
	@RequestMapping(value = "/showDocumentList", method = RequestMethod.GET)
	public ModelAndView showDocumentList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("logistics/showDocumentList"); 
		try
		{
			List<Document> getAllDocumentList = restTemplate.getForObject(Constants.url + "getAllDocumentList", List.class);
			System.out.println("getAllDocumentList"+getAllDocumentList.toString());
			  
			model.addObject("documentList",getAllDocumentList);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return model;

	}
	
	@RequestMapping(value = "/insertDocument", method = RequestMethod.POST)
	public String  insertDocument(HttpServletRequest request, HttpServletResponse response) {

	 
		try
		{
			String docId = request.getParameter("docId");
			String docName =request.getParameter("docName");
			String days = request.getParameter("days");
			String seq = request.getParameter("seq");
			 

			Document insertDocument = new Document();
			if(docId==null || docId.equals(""))
				insertDocument.setDocId(0);
			else
				insertDocument.setDocId(Integer.parseInt(docId));
			insertDocument.setDocName(docName);
			insertDocument.setDocAlertdays(days);
			insertDocument.setDocSeq(seq);
			  
			insertDocument = restTemplate.postForObject(Constants.url + "postDocument",insertDocument, Document.class);
			System.out.println("insertVariant"+insertDocument.toString());
		 
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return "redirect:/showDocumentList";

	}
	
	@RequestMapping(value = "/deleteDoument/{docId}", method = RequestMethod.GET)
	public String deleteDoument(@PathVariable int docId, HttpServletRequest request, HttpServletResponse response) {

		 
		try
		{
			 System.out.println("docId"+docId);
        	 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object >();
        	 map.add("docId", docId);
        	 Info info = restTemplate.postForObject(Constants.url + "deleteDocument",map, Info.class);
 			System.out.println("info"+info.toString()); 
		 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		 
		return "redirect:/showDocumentList";

	}
	
	@RequestMapping(value = "/editDocument", method = RequestMethod.GET)
	@ResponseBody
	public Document editDocument(HttpServletRequest request, HttpServletResponse response) {
		 
		Document editDocument = new Document();
	        try
			{ 
	        	
	        	int docId = Integer.parseInt(request.getParameter("docId"));
	        	 System.out.println("docId"+docId);
	        	 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object >();
	        	 map.add("docId", docId);
	        	
	        	 editDocument = restTemplate.postForObject(Constants.url + "getDocById", map, Document.class);
			 System.out.println("editDocument " + editDocument); 
		}catch(Exception e)
		{
			System.out.println("errorr  "+e.getMessage());
			e.printStackTrace();
		}
	         
		return editDocument;
		

	}
	
	//------------------------------------------------Vehicle---------------------------------
	@RequestMapping(value = "/showVehicleList", method = RequestMethod.GET)
	public ModelAndView showVehicleList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("logistics/showVehicleList"); 
		try
		{
			
			List<VehicalMaster> vehicleList = restTemplate.getForObject(Constants.url + "getAllVehicalList", List.class);
			System.out.println("vehicleList"+vehicleList.toString());
			List<Make> makeList = restTemplate.getForObject(Constants.url + "getAllMakeList", List.class);
			System.out.println("makeList"+makeList.toString());
			List<Variant> getAllVariantList = restTemplate.getForObject(Constants.url + "getAllVariantList", List.class);
			System.out.println("getAllVariantList"+getAllVariantList.toString());
			List<VehicalType> getAllVehicalTypeList = restTemplate.getForObject(Constants.url + "getAllVehicalTypeList", List.class);
			System.out.println("getAllVehicalTypeList"+getAllVehicalTypeList.toString());
			List<Dealer> getAllDealerList = restTemplate.getForObject(Constants.url + "getAllDealerList", List.class);
			System.out.println("getAllDealerList"+getAllDealerList.toString());
			model.addObject("makeList",makeList);
			model.addObject("vehicleList",vehicleList); 
			model.addObject("variantList",getAllVariantList); 
			model.addObject("typeList",getAllVehicalTypeList);
			model.addObject("dealerList",getAllDealerList);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return model;

	}
	
	@RequestMapping(value = "/variantByTypeId", method = RequestMethod.GET)
	@ResponseBody
	public List<Variant> variantByTypeId(HttpServletRequest request, HttpServletResponse response) {
		 
		List<Variant> variantByTypeId = new ArrayList<Variant>();
	        try
			{ 
	        	
	        	int makeId = Integer.parseInt(request.getParameter("makeId"));
	        	int typeId = Integer.parseInt(request.getParameter("typeId"));
	        	 System.out.println("makeId"+makeId);
	        	 System.out.println("typeId"+typeId);
	        	 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object >();
	        	 map.add("makeId", makeId);
	        	 map.add("typeId", typeId);
	        	 variantByTypeId = restTemplate.postForObject(Constants.url + "getVariantByMakeIdAndTypeId", map, List.class);
			 System.out.println("variantByTypeId " + variantByTypeId); 
		}catch(Exception e)
		{
			System.out.println("errorr  "+e.getMessage());
			e.printStackTrace();
		}
	         
		return variantByTypeId;
		

	}
	
	@RequestMapping(value = "/dealerByMakeId", method = RequestMethod.GET)
	@ResponseBody
	public List<Dealer> dealerByMakeId(HttpServletRequest request, HttpServletResponse response) {
		 
		List<Dealer> variantByTypeId = new ArrayList<Dealer>();
	        try
			{ 
	        	
	        	int makeId = Integer.parseInt(request.getParameter("makeId")); 
	        	 System.out.println("makeId"+makeId); 
	        	 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object >();
	        	 map.add("makeId", makeId); 
	        	 variantByTypeId = restTemplate.postForObject(Constants.url + "getDealerByMakeId", map, List.class);
			 System.out.println("variantByTypeId " + variantByTypeId); 
		}catch(Exception e)
		{
			System.out.println("errorr  "+e.getMessage());
			e.printStackTrace();
		}
	         
		return variantByTypeId;
		

	}
	
	@RequestMapping(value = "/insertVehicle", method = RequestMethod.POST)
	public String  insertVehicle(HttpServletRequest request, HttpServletResponse response) {

	 
		try
		{
			String vehId = request.getParameter("vehId");
			String vehName = request.getParameter("vehName");
			int makeId =Integer.parseInt(request.getParameter("makeId"));
			int typeId =Integer.parseInt(request.getParameter("typeId"));
			int dealerId =Integer.parseInt(request.getParameter("dealerId"));
			int variantId =Integer.parseInt(request.getParameter("variantId"));
			int fuelType =Integer.parseInt(request.getParameter("fuelType"));
			
			String engNo = request.getParameter("engNo");
			String chsNo = request.getParameter("chsNo");
			String color = request.getParameter("color");
			String purDate = request.getParameter("purDate");
			String regDate = request.getParameter("regDate");
			float cmpnyAvg = Float.parseFloat(request.getParameter("cmpnyAvg"));
			float standAvg = Float.parseFloat(request.getParameter("standAvg"));
			float miniAvg = Float.parseFloat(request.getParameter("miniAvg")); 
			int frqKm =Integer.parseInt(request.getParameter("frqKm"));
			int wheelChange =Integer.parseInt(request.getParameter("wheelChange"));
			int batryChange =Integer.parseInt(request.getParameter("batryChange"));
			int acChang =Integer.parseInt(request.getParameter("acChang"));

			VehicalMaster insertVehicalMaster = new VehicalMaster();
			if(vehId==null || vehId.equals(""))
				insertVehicalMaster.setVehId(0);
			else
				insertVehicalMaster.setVehId(Integer.parseInt(vehId)); 
			insertVehicalMaster.setVehNo(vehName);
			insertVehicalMaster.setMakeId(makeId);
			insertVehicalMaster.setVehTypeId(typeId);
			insertVehicalMaster.setDealerId(dealerId);
			insertVehicalMaster.setVariantId(variantId);
			insertVehicalMaster.setFuelType(fuelType);
			insertVehicalMaster.setVehEngNo(engNo);
			insertVehicalMaster.setVehChesiNo(chsNo);
			insertVehicalMaster.setVehColor(color);
			insertVehicalMaster.setVehCompAvg(cmpnyAvg);
			insertVehicalMaster.setVehStandAvg(standAvg);
			insertVehicalMaster.setVehMiniAvg(miniAvg);
			insertVehicalMaster.setPurchaseDate(purDate);
			insertVehicalMaster.setRegDate(regDate);
			insertVehicalMaster.setFreqKIm(frqKm);
			insertVehicalMaster.setWheelChangeFreq(wheelChange);
			insertVehicalMaster.setBattaryChangeFreq(batryChange);
			insertVehicalMaster.setAcChangeFreq(acChang);
			  
			insertVehicalMaster = restTemplate.postForObject(Constants.url + "postVehicalMaster",insertVehicalMaster, VehicalMaster.class);
			System.out.println("insertVariant"+insertVehicalMaster.toString());
		 
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return "redirect:/showVehicleList";

	}
	
	@RequestMapping(value = "/deleteVehicle/{vehId}", method = RequestMethod.GET)
	public String deleteVehicle(@PathVariable int vehId, HttpServletRequest request, HttpServletResponse response) {

		 
		try
		{
			 System.out.println("vehId"+vehId);
        	 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object >();
        	 map.add("vehicalId", vehId);
        	 Info info = restTemplate.postForObject(Constants.url + "deleteVehicalMaster",map, Info.class);
 			System.out.println("info"+info.toString()); 
		 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		 
		return "redirect:/showVehicleList";

	}
	
	@RequestMapping(value = "/editVehicle", method = RequestMethod.GET)
	@ResponseBody
	public VehicalMaster editVehicle(HttpServletRequest request, HttpServletResponse response) {
		 
		VehicalMaster editVehicle = new VehicalMaster();
	        try
			{ 
	        	
	        	int vehId = Integer.parseInt(request.getParameter("vehId"));
	        	 System.out.println("vehId"+vehId);
	        	 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object >();
	        	 map.add("vehicalId", vehId);
	        	
	        	 editVehicle = restTemplate.postForObject(Constants.url + "getVehicalById", map, VehicalMaster.class);
			 System.out.println("editVehicle " + editVehicle); 
		}catch(Exception e)
		{
			System.out.println("errorr  "+e.getMessage());
			e.printStackTrace();
		}
	         
		return editVehicle;
		

	}
	
	//--------------------------------------SpareGroup--------------------------------------------------
	@RequestMapping(value = "/showSpareGroupList", method = RequestMethod.GET)
	public ModelAndView showSpareGroupList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("logistics/showSpareGroupList"); 
		try
		{
			
			List<SprGroup> sprGroupList = restTemplate.getForObject(Constants.url + "getAllSprGroupList", List.class);
			System.out.println("sprGroupList"+sprGroupList.toString());
			 
			model.addObject("sprGroupList",sprGroupList);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return model;

	}
	
	@RequestMapping(value = "/insertSpareGroup", method = RequestMethod.POST)
	public String  insertSpareGroup(HttpServletRequest request, HttpServletResponse response) {

	 
		try
		{
			String groupId = request.getParameter("groupId");
			String groupName = request.getParameter("groupName"); 
			int typeId =Integer.parseInt(request.getParameter("typeId")); 

			SprGroup insertSprGroup = new SprGroup();
			if(groupId==null || groupId.equals(""))
				insertSprGroup.setGroupId(0);
			else
				insertSprGroup.setGroupId(Integer.parseInt(groupId)); 
			insertSprGroup.setGroupName(groupName); 
			insertSprGroup.setTypeId(typeId); 
			  
			insertSprGroup = restTemplate.postForObject(Constants.url + "postSprGroup",insertSprGroup, SprGroup.class);
			System.out.println("insertSprGroup"+insertSprGroup.toString());
		 
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return "redirect:/showSpareGroupList";

	}
	
	@RequestMapping(value = "/deleteSpareGroup/{groupId}", method = RequestMethod.GET)
	public String deleteSpareGroup(@PathVariable int groupId, HttpServletRequest request, HttpServletResponse response) {

		 
		try
		{
			 System.out.println("groupId"+groupId);
        	 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object >();
        	 map.add("groupId", groupId);
        	 Info info = restTemplate.postForObject(Constants.url + "deleteSprGroup",map, Info.class);
 			System.out.println("info"+info.toString()); 
		 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		 
		return "redirect:/showSpareGroupList";

	}
	
	@RequestMapping(value = "/editSpareGroup", method = RequestMethod.GET)
	@ResponseBody
	public SprGroup editSpareGroup(HttpServletRequest request, HttpServletResponse response) {
		 
		SprGroup editSpareGroup = new SprGroup();
	        try
			{ 
	        	
	        	int groupId = Integer.parseInt(request.getParameter("groupId"));
	        	 System.out.println("groupId"+groupId);
	        	 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object >();
	        	 map.add("groupId", groupId);
	        	
	        	 editSpareGroup = restTemplate.postForObject(Constants.url + "getSprGroupById", map, SprGroup.class);
			 System.out.println("editSpareGroup " + editSpareGroup); 
		}catch(Exception e)
		{
			System.out.println("errorr  "+e.getMessage());
			e.printStackTrace();
		}
	         
		return editSpareGroup;
		

	}
	
	//---------------------------------------------SparePart-----------------------------------------
	
	@RequestMapping(value = "/showSparePartList", method = RequestMethod.GET)
	public ModelAndView showSparePartList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("logistics/showSparePartList"); 
		try
		{
			
			List<SparePart> getAllSparePart = restTemplate.getForObject(Constants.url + "getAllSparePart", List.class);
			System.out.println("getAllSparePart"+getAllSparePart.toString());
			List<Make> makeList = restTemplate.getForObject(Constants.url + "getAllMakeList", List.class);
			System.out.println("makeList"+makeList.toString());
			List<SprGroup> sprGroupList = restTemplate.getForObject(Constants.url + "getAllSprGroupList", List.class);
			System.out.println("sprGroupList"+sprGroupList.toString());
			model.addObject("sprGroupList",sprGroupList); 
			model.addObject("makeList",makeList); 
			model.addObject("sprPartList",getAllSparePart);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return model;

	}
	
	@RequestMapping(value = "/groupByTypeId", method = RequestMethod.GET)
	@ResponseBody
	public List<SprGroup> groupByTypeId(HttpServletRequest request, HttpServletResponse response) {
		 
		List<SprGroup> groupByTypeId = new ArrayList<SprGroup>();
	        try
			{ 
	        	
	        	int typeId = Integer.parseInt(request.getParameter("typeId")); 
	        	 System.out.println("typeId"+typeId); 
	        	 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object >();
	        	 map.add("typeId", typeId); 
	        	 groupByTypeId = restTemplate.postForObject(Constants.url + "getSprGroupListByTypeId", map, List.class);
			 System.out.println("groupByTypeId " + groupByTypeId); 
		}catch(Exception e)
		{
			System.out.println("errorr  "+e.getMessage());
			e.printStackTrace();
		}
	         
		return groupByTypeId;
		

	}
	
	@RequestMapping(value = "/insertSparePart", method = RequestMethod.POST)
	public String  insertSparePart(HttpServletRequest request, HttpServletResponse response) {

	 
		try
		{
			String sprId = request.getParameter("sprId");
			String partName = request.getParameter("partName");
			int makeId =Integer.parseInt(request.getParameter("makeId"));
			int typeId =Integer.parseInt(request.getParameter("typeId"));
			int groupId =Integer.parseInt(request.getParameter("groupId")); 
			int critical =Integer.parseInt(request.getParameter("critical")); 
			String uom = request.getParameter("uom");
			String date1 = request.getParameter("date1");
			int rate1 = Integer.parseInt(request.getParameter("rate1"));
			String date2 = request.getParameter("date2");
			int rate2 = Integer.parseInt(request.getParameter("rate2"));
			String date3 = request.getParameter("date3");
			int rate3 = Integer.parseInt(request.getParameter("rate3"));  
			int warnty =Integer.parseInt(request.getParameter("warnty"));
			float cgst = Float.parseFloat(request.getParameter("cgst"));
			float sgst = Float.parseFloat(request.getParameter("sgst"));
			float igst = Float.parseFloat(request.getParameter("igst"));
			int disc =Integer.parseInt(request.getParameter("disc"));
			int extra =Integer.parseInt(request.getParameter("extra"));

			SparePart insertSparePart = new SparePart();
			if(sprId==null || sprId.equals(""))
				insertSparePart.setSprId(0);
			else
				insertSparePart.setSprId(Integer.parseInt(sprId)); 
			insertSparePart.setSprName(partName);
			insertSparePart.setMakeId(makeId);
			insertSparePart.setTypeId(typeId);
			insertSparePart.setGroupId(groupId);
			insertSparePart.setSprIscritical(critical);
			insertSparePart.setSprUom(uom);
			insertSparePart.setSprDate1(date1);
			insertSparePart.setSprRate1(rate1);
			insertSparePart.setSprDate2(date2);
			insertSparePart.setSprRate2(rate2);
			insertSparePart.setSprDate3(date3);
			insertSparePart.setSprRate3(rate3);
			insertSparePart.setSprWarrantyPeriod(warnty);
			insertSparePart.setCgst(cgst);
			insertSparePart.setSgst(sgst);
			insertSparePart.setIgst(igst);
			insertSparePart.setDisc(disc);
			insertSparePart.setExtraCharges(extra);
			
			  System.out.println("after insert "+insertSparePart);
			insertSparePart = restTemplate.postForObject(Constants.url + "postSparePart",insertSparePart, SparePart.class);
			System.out.println("insertSparePart"+insertSparePart.toString());
		 
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return "redirect:/showSparePartList";

	}
	
	@RequestMapping(value = "/deleteSparePart/{sprId}", method = RequestMethod.GET)
	public String deleteSparePart(@PathVariable int sprId, HttpServletRequest request, HttpServletResponse response) {

		 
		try
		{
			 System.out.println("sprId"+sprId);
        	 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object >();
        	 map.add("sprId", sprId);
        	 Info info = restTemplate.postForObject(Constants.url + "deleteSparePart",map, Info.class);
 			System.out.println("info"+info.toString()); 
		 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		 
		return "redirect:/showSparePartList";

	}
	
	@RequestMapping(value = "/editSparePart", method = RequestMethod.GET)
	@ResponseBody
	public SparePart editSparePart(HttpServletRequest request, HttpServletResponse response) {
		 
		SparePart editSparePart = new SparePart();
	        try
			{ 
	        	
	        	int sprId = Integer.parseInt(request.getParameter("sprId"));
	        	 System.out.println("sprId"+sprId);
	        	 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object >();
	        	 map.add("sprId", sprId);
	        	
	        	 editSparePart = restTemplate.postForObject(Constants.url + "getSparePartById", map, SparePart.class);
			 System.out.println("editSparePart " + editSparePart); 
		}catch(Exception e)
		{
			System.out.println("errorr  "+e.getMessage());
			e.printStackTrace();
		}
	         
		return editSparePart;
		

	}

}
