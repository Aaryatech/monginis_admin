package com.ats.adminpanel.controller; 
import java.awt.Dimension;
import java.awt.Insets;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope; 
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
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
import org.zefer.pd4ml.PD4Constants;
import org.zefer.pd4ml.PD4ML;
import org.zefer.pd4ml.PD4PageMark;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.commons.DateConvertor;
import com.ats.adminpanel.commons.VpsImageUpload;
import com.ats.adminpanel.model.AllRoutesListResponse;
import com.ats.adminpanel.model.Info;
import com.ats.adminpanel.model.Route;
import com.ats.adminpanel.model.item.FrItemStockConfigureList;
import com.ats.adminpanel.model.logistics.AlertAmcRecord;
import com.ats.adminpanel.model.logistics.AlertMachineServicingRecord;
import com.ats.adminpanel.model.logistics.AlertVeihcleServicing;
import com.ats.adminpanel.model.logistics.Dealer;
import com.ats.adminpanel.model.logistics.Document;
import com.ats.adminpanel.model.logistics.DriverMaster;
import com.ats.adminpanel.model.logistics.LogisAmc;
import com.ats.adminpanel.model.logistics.MachineMaster;
import com.ats.adminpanel.model.logistics.MachineOrVehicle;
import com.ats.adminpanel.model.logistics.MachineServicing;
import com.ats.adminpanel.model.logistics.Make;
import com.ats.adminpanel.model.logistics.MechType;
import com.ats.adminpanel.model.logistics.ServDetail;
import com.ats.adminpanel.model.logistics.ServDetailAddPart;
import com.ats.adminpanel.model.logistics.ServHeader;
import com.ats.adminpanel.model.logistics.SparePart;
import com.ats.adminpanel.model.logistics.SprGroup;
import com.ats.adminpanel.model.logistics.TrayManagementReport;
import com.ats.adminpanel.model.logistics.Variant;
import com.ats.adminpanel.model.logistics.VehicalMaster;
import com.ats.adminpanel.model.logistics.VehicalType;
import com.ats.adminpanel.model.logistics.VehicleDcoument; 

@Controller
@Scope("session")
public class LogisticsController {
	
	RestTemplate restTemplate = new RestTemplate();
	
	
	
	@RequestMapping(value = "/logisticsDashBoard", method = RequestMethod.GET)
	public ModelAndView logisticsDashBoard(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("logistics/logisticsDashbord"); 
		try
		{
			 
			List<AlertAmcRecord> alertMachineAmcRecordList = restTemplate.getForObject(Constants.url + "getAlertMachineAmcRecord", List.class); 
			List<AlertAmcRecord> alertVehicleAmcRecordList = restTemplate.getForObject(Constants.url + "getAlertvehicleAmcRecord", List.class);
			List<DriverMaster> alertDriverMasterList = restTemplate.getForObject(Constants.url + "getAlertDriverRecord", List.class);
			List<AlertMachineServicingRecord> alertMachineServicingRecordList = restTemplate.getForObject(Constants.url + "getAlertMachineServicingRecord", List.class);
			List<VehicleDcoument> getAlertDocumentList = restTemplate.getForObject(Constants.url + "getAlertDocumentRecord", List.class);
			List<Document> getAllDocumentList = restTemplate.getForObject(Constants.url + "getAllDocumentList", List.class);
			List<VehicalMaster> vehicleList = restTemplate.getForObject(Constants.url + "getAllVehicalList", List.class); 
			List<AlertVeihcleServicing> getAlertVeihcleServicingRecord = restTemplate.getForObject(Constants.url + "getAlertVeihcleServicingRecord", List.class);
			System.out.println("alertMachineServicingRecordList " +alertMachineServicingRecordList);
			 model.addObject("getAllDocumentList", getAllDocumentList);
			 model.addObject("vehicleList", vehicleList);
			 model.addObject("alertDocumentList", getAlertDocumentList);
			 model.addObject("alertDriverMasterList", alertDriverMasterList);
			model.addObject("amcRecordList", alertMachineAmcRecordList);
			model.addObject("alertVehicleAmcList", alertVehicleAmcRecordList);
			model.addObject("alertMachineServicingList", alertMachineServicingRecordList);
			model.addObject("alertVeihcleServicingRecord", getAlertVeihcleServicingRecord);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return model;

	}
	
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
			int isSameState = Integer.parseInt(request.getParameter("isSameState"));
			String gstnNo = request.getParameter("gstnNo");

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
			insertDealer.setIsSameState(isSameState);
			insertDealer.setGstnNo(gstnNo);
			
			
			
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
			int currentRunningKm =Integer.parseInt(request.getParameter("currentRunningKm"));
			int lastServicingKm =Integer.parseInt(request.getParameter("lastServicingKm"));
			int nextServicingKm =Integer.parseInt(request.getParameter("nextServicingKm"));
			int alertNextServicingKm =Integer.parseInt(request.getParameter("alertNextServicingKm"));
			String lastAmcDate = request.getParameter("lastAmcDate");
			String nextAmcDate = request.getParameter("nextAmcDate");
			String alertAmcDate = request.getParameter("alertAmcDate");

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
			insertVehicalMaster.setFreqKm(frqKm);
			insertVehicalMaster.setWheelChangeFreq(wheelChange);
			insertVehicalMaster.setBattaryChangeFreq(batryChange);
			insertVehicalMaster.setAcChangeFreq(acChang);
			insertVehicalMaster.setCurrentRunningKm(currentRunningKm);
			insertVehicalMaster.setLastServicingKm(lastServicingKm);
			insertVehicalMaster.setNextServicingKm(nextServicingKm);
			insertVehicalMaster.setAlertNextServicingKm(alertNextServicingKm);
			insertVehicalMaster.setLastAmcDate(lastAmcDate);
			insertVehicalMaster.setNextAmcDate(nextAmcDate);
			insertVehicalMaster.setAlertAmcDate(alertAmcDate);
			
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
			List<MechType> mechTypeList = restTemplate.getForObject(Constants.url + "getTypeList", List.class);
			System.out.println("mechTypeList"+mechTypeList.toString());
			 
			model.addObject("sprGroupList",sprGroupList);
			model.addObject("mechTypeList",mechTypeList);
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
			List<MechType> mechTypeList = restTemplate.getForObject(Constants.url + "getTypeList", List.class);
			System.out.println("mechTypeList"+mechTypeList.toString());
			model.addObject("sprGroupList",sprGroupList); 
			model.addObject("makeList",makeList); 
			model.addObject("sprPartList",getAllSparePart);
			model.addObject("mechTypeList",mechTypeList);
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
	
	//----------------------------------------------Servicing-----------------------------------------------------------
	List<ServDetailAddPart> addSparePartList = new ArrayList<ServDetailAddPart>();
	
	
	@RequestMapping(value = "/showServicingList", method = RequestMethod.GET)
	public ModelAndView showServicingList(HttpServletRequest request, HttpServletResponse response) {

		List<ServHeader> servHeaderList = new ArrayList<ServHeader>();
		ModelAndView model = new ModelAndView("logistics/showServicingList"); 
		try
		{
		 servHeaderList = restTemplate.getForObject(Constants.url + "getServicingListPendingAndCurrentDate", List.class);
		 List<MechType> mechTypeList = restTemplate.getForObject(Constants.url + "getTypeList", List.class); 
		 System.out.println("mechTypeList " + mechTypeList);
		 model.addObject("servHeaderList",servHeaderList);
		 model.addObject("mechTypeList",mechTypeList);
		 model.addObject("flag",0);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return model;

	}
	
	@RequestMapping(value = "/showServicingListToDirector", method = RequestMethod.GET)
	public ModelAndView showServicingListToDirector(HttpServletRequest request, HttpServletResponse response) {

		List<ServHeader> servHeaderList = new ArrayList<ServHeader>();
		ModelAndView model = new ModelAndView("logistics/showServicingList"); 
		try
		{
		 servHeaderList = restTemplate.getForObject(Constants.url + "getServicingListPendingAndCurrentDate", List.class);
		 List<MechType> mechTypeList = restTemplate.getForObject(Constants.url + "getTypeList", List.class); 
		 model.addObject("mechTypeList",mechTypeList);
		 model.addObject("servHeaderList",servHeaderList);
		 model.addObject("flag",1);
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return model;

	}
	
	String pdfName;
	
 
	
	@RequestMapping(value = "/getServicingWithDate", method = RequestMethod.GET)
	@ResponseBody
	public List<ServHeader> getServicingWithDate(HttpServletRequest request, HttpServletResponse response) {
		 
		List<ServHeader> getServicingWithDate = new ArrayList<ServHeader>();
	        try
			{ 
	        	
	        	String fromDate = request.getParameter("from_date");
	        	String toDate = request.getParameter("to_date");
	        	String type =request.getParameter("type");
	        	String vehId =request.getParameter("vehId");
	        	String typeId =request.getParameter("typeId");
	        	System.out.println("type " + type);
	        	MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
	        	if(type.equals(""))
	        	{
	        		map.add("fromDate", DateConvertor.convertToYMD(fromDate));
		        	map.add("toDate", DateConvertor.convertToYMD(toDate)); 
		        	map.add("typeId", typeId); 
		        	getServicingWithDate = restTemplate.postForObject(Constants.url + "getServicingListBetweenDateByTypeId",map, List.class);
	        	}
	        	else
	        	{
	        		map.add("fromDate", DateConvertor.convertToYMD(fromDate));
		        	map.add("toDate", DateConvertor.convertToYMD(toDate));
		        	map.add("type", Integer.parseInt(type));
		        	map.add("vehId", Integer.parseInt(vehId));
		        	getServicingWithDate = restTemplate.postForObject(Constants.url + "getServicingListBetweenDate",map, List.class);
	        	}
	        	
	        	 
		}catch(Exception e)
		{
			System.out.println("errorr  "+e.getMessage());
			e.printStackTrace();
		}
	         
		return getServicingWithDate;
		

	}
	
	@RequestMapping(value = "pdf/vehOrMachInvoiceBill/{from_date}/{to_date}/{type}/{typeId}/{vehId}", method = RequestMethod.GET)
	public ModelAndView vehOrMachInvoiceBill(@PathVariable String from_date,@PathVariable String to_date,@PathVariable String type,@PathVariable String typeId,
			@PathVariable String vehId,HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("logistics/reportPdf/vehOrMachInvoiceBill");
		try {
			List<ServHeader> vehOrMachInvoiceBill = new ArrayList<ServHeader>(); 
        	 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        	 if(Integer.parseInt(type)==-1)
	        	{
        			map.add("fromDate", DateConvertor.convertToYMD(from_date));
		        	map.add("toDate", DateConvertor.convertToYMD(to_date)); 
		        	map.add("typeId", typeId); 
		        	vehOrMachInvoiceBill = restTemplate.postForObject(Constants.url + "getServicingListBetweenDateByTypeId",map, List.class);
	        	}
	        	else
	        	{
	        		map.add("fromDate", DateConvertor.convertToYMD(from_date));
		        	map.add("toDate", DateConvertor.convertToYMD(to_date));
		        	map.add("type", Integer.parseInt(type));
		        	map.add("vehId", Integer.parseInt(vehId));
		        	vehOrMachInvoiceBill = restTemplate.postForObject(Constants.url + "getServicingListBetweenDate",map, List.class);
	        	}
	   		 List<MechType> mechTypeList = restTemplate.getForObject(Constants.url + "getTypeList", List.class);  
	   		 model.addObject("mechTypeList",mechTypeList); 
			model.addObject("staticlist", vehOrMachInvoiceBill);
			model.addObject("fromDate", from_date);
			model.addObject("toDate", to_date); 
			System.out.println("vehOrMachInvoiceBill" + vehOrMachInvoiceBill);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;

	}
	
	@RequestMapping(value = "/viewServicingDetail/{servId}/{flag}", method = RequestMethod.GET)
	public ModelAndView viewServicingDetail(@PathVariable int servId, @PathVariable int flag, HttpServletRequest request, HttpServletResponse response) {

		ServHeader viewServicingDetail = new ServHeader(); 
		ModelAndView model = new ModelAndView("logistics/viewServicingDetail"); 
		try
		{
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        	map.add("servId", servId); 
        	viewServicingDetail = restTemplate.postForObject(Constants.url + "getServHeaderAndDetailById",map, ServHeader.class);
        	
        	pdfName = viewServicingDetail.getBillFile();
        	
        	List<SparePart> getAllSparePart = restTemplate.getForObject(Constants.url + "getAllSparePart", List.class);  
        	List<Dealer> getAllDealerList = restTemplate.getForObject(Constants.url + "getAllDealerList", List.class);  
			List<VehicalMaster> vehicleList = restTemplate.getForObject(Constants.url + "getAllVehicalList", List.class);
			List<SprGroup> sprGroupList = restTemplate.getForObject(Constants.url + "getAllSprGroupList", List.class); 
			List<MechType> mechTypeList = restTemplate.getForObject(Constants.url + "getTypeList", List.class);
			model.addObject("mechTypeList",mechTypeList);
			model.addObject("dealerList",getAllDealerList); 
			model.addObject("sprGroupList",sprGroupList); 
			model.addObject("sprPartList",getAllSparePart);
			model.addObject("vehicleList",vehicleList);
			model.addObject("url",Constants.LOGIS_BILL_URL);
			 model.addObject("viewServicingDetail", viewServicingDetail);
			 model.addObject("flag", flag);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return model; 
	}
	
	@RequestMapping(value = "/approvedServiceBill/{servId}", method = RequestMethod.GET)
	public String  approvedServiceBill(@PathVariable int servId,HttpServletRequest request, HttpServletResponse response) {

	 
		try
		{
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        	map.add("servId", servId); 
        	Info info = restTemplate.postForObject(Constants.url + "approvedServiceHeader",map, Info.class); 
        	System.out.println("info "+info);
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		 
		return "redirect:/showServicingListToDirector";

	}
	
	
	
	@RequestMapping(value = "/insertSarvicing", method = RequestMethod.GET)
	public ModelAndView insertSarvicing(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("logistics/insertSarvicing"); 
		try
		{
			addSparePartList = new ArrayList<ServDetailAddPart>();
			List<SparePart> getAllSparePart = restTemplate.getForObject(Constants.url + "getAllSparePart", List.class);
			System.out.println("getAllSparePart"+getAllSparePart.toString());
			List<Dealer> getAllDealerList = restTemplate.getForObject(Constants.url + "getAllDealerList", List.class);
			System.out.println("getAllDealerList"+getAllDealerList.toString());
			List<VehicalMaster> vehicleList = restTemplate.getForObject(Constants.url + "getAllVehicalList", List.class);
			System.out.println("vehicleList"+vehicleList.toString()); 
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			String settingKey =  "m_logis_labour_group"; 
			map.add("settingKeyList", settingKey);
			FrItemStockConfigureList settingList = restTemplate.postForObject(Constants.url + "getDeptSettingValue", map,
					FrItemStockConfigureList.class);
			int labourGroupId = settingList.getFrItemStockConfigure().get(0).getSettingValue();
			System.out.println("labourGroupId"+labourGroupId);
			
			model.addObject("dealerList",getAllDealerList); 
			model.addObject("sprPartList",getAllSparePart);
			model.addObject("vehicleList",vehicleList);
			model.addObject("labourGroupId",labourGroupId);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return model;

	}
	
	@RequestMapping(value = "/getTypeList", method = RequestMethod.GET)
	@ResponseBody
	public List<MechType> getTypeList(HttpServletRequest request, HttpServletResponse response) {
		 
		List<MechType> typeList = new ArrayList<MechType>();
	        try
			{ 
	        	
	        	int type = Integer.parseInt(request.getParameter("type"));
	        	MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
	        	map.add("type", type);
	        	typeList = restTemplate.postForObject(Constants.url + "getTypeListByType",map, List.class);
				System.out.println("typeList"+typeList.toString());
	        	 
		}catch(Exception e)
		{
			System.out.println("errorr  "+e.getMessage());
			e.printStackTrace();
		}
	         
		return typeList;
		

	}
	
	@RequestMapping(value = "/getAllTypeList", method = RequestMethod.GET)
	@ResponseBody
	public List<MechType> getAllTypeList(HttpServletRequest request, HttpServletResponse response) {
		 
		List<MechType> typeList = new ArrayList<MechType>();
	        try
			{ 
	        	 
	        	typeList = restTemplate.getForObject(Constants.url + "getTypeList", List.class);
				System.out.println("typeList"+typeList.toString());
	        	 
		}catch(Exception e)
		{
			System.out.println("errorr  "+e.getMessage());
			e.printStackTrace();
		}
	         
		return typeList;
		

	}
	
	
	@RequestMapping(value = "/addSparePart", method = RequestMethod.GET)
	@ResponseBody
	public List<ServDetailAddPart> addSparePart(HttpServletRequest request, HttpServletResponse response) {
		 
		
	        try
			{ 
	        	
	        	int sprId = Integer.parseInt(request.getParameter("sprId"));
	        	String sprName = request.getParameter("sprName");
	        	int groupId = Integer.parseInt(request.getParameter("groupId"));
	        	String groupName = request.getParameter("groupName");
	        	int vehId = Integer.parseInt(request.getParameter("vehId"));
	        	String vehName = request.getParameter("vehName");
	        	int spareRate = Integer.parseInt(request.getParameter("spareRate"));
	        	int spareQty = Integer.parseInt(request.getParameter("spareQty"));
	        	float taxaleAmtDetail = Float.parseFloat(request.getParameter("taxaleAmtDetail"));
	        	float taxAmtDetail = Float.parseFloat(request.getParameter("taxAmtDetail"));
	        	float totalDetail = Float.parseFloat(request.getParameter("totalDetail"));
	        	float discDetail = Float.parseFloat(request.getParameter("discDetail"));
	        	float extraChargeDetail = Float.parseFloat(request.getParameter("extraChargeDetail"));
	        	int servTypeDetail = Integer.parseInt(request.getParameter("servTypeDetail")); 
	        	int discPer = Integer.parseInt(request.getParameter("discPer"));
	        	int extraChargePer = Integer.parseInt(request.getParameter("extraChargePer"));
	        	int taxPer = Integer.parseInt(request.getParameter("taxPer"));
	        	 
	        	 ServDetailAddPart addSparePart = new ServDetailAddPart();
	        	 addSparePart.setSprId(sprId);
	        	 addSparePart.setPartName(sprName);
	        	 addSparePart.setGroupId(groupId);
	        	 addSparePart.setVehId(vehId);
	        	 addSparePart.setVehName(vehName);
	        	 addSparePart.setGroupName(groupName);
	        	 addSparePart.setSprRate(spareRate);
	        	 addSparePart.setSprQty(spareQty);
	        	 addSparePart.setSprTaxableAmt(taxaleAmtDetail);
	        	 addSparePart.setSprTaxAmt(taxAmtDetail);
	        	 addSparePart.setTotal(totalDetail);
	        	 addSparePart.setDisc(discDetail);
	        	 addSparePart.setExtraCharges(extraChargeDetail);
	        	 addSparePart.setServType(servTypeDetail); 
	        	 addSparePart.setDiscPer(discPer);
	        	 addSparePart.setExtraChargesPer(extraChargePer);
	        	 addSparePart.setSprTaxAmtPer(taxPer);
	        	 addSparePartList.add(addSparePart);
	        	 System.out.println("addSparePartList"+addSparePartList); 
	        	 
		}catch(Exception e)
		{
			System.out.println("errorr  "+e.getMessage());
			e.printStackTrace();
		}
	         
		return addSparePartList;
		

	}
	
	@RequestMapping(value = "/deleteSparePart", method = RequestMethod.GET)
	@ResponseBody
	public List<ServDetailAddPart> deleteSparePart(HttpServletRequest request, HttpServletResponse response) {
		 
		
	        try
			{ 
	        	
	        	int index = Integer.parseInt(request.getParameter("index")); 
	        	 System.out.println("index"+index); 
	        	 addSparePartList.remove(index);
	        	 System.out.println("addSparePartList"+addSparePartList); 
	        	 
		}catch(Exception e)
		{
			System.out.println("errorr  "+e.getMessage());
			e.printStackTrace();
		}
	         
		return addSparePartList;
		

	}
	int editindex;
	@RequestMapping(value = "/editInvoiceSparePart", method = RequestMethod.GET)
	@ResponseBody
	public ServDetailAddPart editInvoiceSparePart(HttpServletRequest request, HttpServletResponse response) {
		 
		ServDetailAddPart servDetailAddPart = new ServDetailAddPart();
	        try
			{ 
	        	
	        	editindex = Integer.parseInt(request.getParameter("index")); 
	        	 System.out.println("index"+editindex); 
	        	 servDetailAddPart = addSparePartList.get(editindex);
	        	 System.out.println("servDetailAddPart"+servDetailAddPart); 
	        	 
		}catch(Exception e)
		{
			System.out.println("errorr  "+e.getMessage());
			e.printStackTrace();
		}
	         
		return servDetailAddPart;
		

	}
	
	@RequestMapping(value = "/changeQtyOfSparePart", method = RequestMethod.GET)
	@ResponseBody
	public List<ServDetailAddPart> changeQtyOfSparePart(HttpServletRequest request, HttpServletResponse response) {
		 
		
        try
		{ 
        	
        	int sprId = Integer.parseInt(request.getParameter("sprId"));
        	String sprName = request.getParameter("sprName");
        	int groupId = Integer.parseInt(request.getParameter("groupId"));
        	String groupName = request.getParameter("groupName");
        	int vehId = Integer.parseInt(request.getParameter("vehId"));
        	String vehName = request.getParameter("vehName");
        	int spareRate = Integer.parseInt(request.getParameter("spareRate"));
        	int spareQty = Integer.parseInt(request.getParameter("spareQty"));
        	float taxaleAmtDetail = Float.parseFloat(request.getParameter("taxaleAmtDetail"));
        	float taxAmtDetail = Float.parseFloat(request.getParameter("taxAmtDetail"));
        	float totalDetail = Float.parseFloat(request.getParameter("totalDetail"));
        	float discDetail = Float.parseFloat(request.getParameter("discDetail"));
        	float extraChargeDetail = Float.parseFloat(request.getParameter("extraChargeDetail"));
        	int servTypeDetail = Integer.parseInt(request.getParameter("servTypeDetail")); 
        	
        	addSparePartList.get(editindex).setSprId(sprId);
        	addSparePartList.get(editindex).setPartName(sprName);
        	addSparePartList.get(editindex).setGroupId(groupId);
        	addSparePartList.get(editindex).setVehId(vehId);
        	addSparePartList.get(editindex).setVehName(vehName);
        	addSparePartList.get(editindex).setGroupName(groupName);
        	addSparePartList.get(editindex).setSprRate(spareRate);
        	addSparePartList.get(editindex).setSprQty(spareQty);
        	addSparePartList.get(editindex).setSprTaxableAmt(taxaleAmtDetail);
        	addSparePartList.get(editindex).setSprTaxAmt(taxAmtDetail);
        	addSparePartList.get(editindex).setTotal(totalDetail);
        	addSparePartList.get(editindex).setDisc(discDetail);
        	addSparePartList.get(editindex).setExtraCharges(extraChargeDetail);
        	addSparePartList.get(editindex).setServType(servTypeDetail);
        	 
	}catch(Exception e)
	{
		System.out.println("errorr  "+e.getMessage());
		e.printStackTrace();
	}
         
	return addSparePartList;
	

}
	
	@RequestMapping(value = "/sparePartByGroupId", method = RequestMethod.GET)
	@ResponseBody
	public List<SparePart> sparePartByGroupId(HttpServletRequest request, HttpServletResponse response) {
		 
		List<SparePart> sparePartByGroupId = new ArrayList<SparePart>();
	        try
			{ 
	        	
	        	int groupId = Integer.parseInt(request.getParameter("groupId")); 
	        	 System.out.println("groupId"+groupId); 
	        	 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object >();
	        	 map.add("groupId", groupId); 
	        	 sparePartByGroupId = restTemplate.postForObject(Constants.url + "sparePartByGroupId", map, List.class);
			 System.out.println("groupByTypeId " + sparePartByGroupId); 
		}catch(Exception e)
		{
			System.out.println("errorr  "+e.getMessage());
			e.printStackTrace();
		}
	         
		return sparePartByGroupId;
		

	}
	
	@RequestMapping(value = "/partDetailById", method = RequestMethod.GET)
	@ResponseBody
	public SparePart partDetailById(HttpServletRequest request, HttpServletResponse response) {
		 
		SparePart partDetailById = new SparePart();
	        try
			{ 
	        	
	        	int sprId = Integer.parseInt(request.getParameter("sprId")); 
	        	int dealerId = Integer.parseInt(request.getParameter("dealerId"));
	        	 System.out.println("sprId"+sprId); 
	        	 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object >();
	        	 map.add("sprId", sprId); 
	        	 partDetailById = restTemplate.postForObject(Constants.url + "getSparePartById", map, SparePart.class);
			 System.out.println("partDetailById " + partDetailById); 
			 
			  map = new LinkedMultiValueMap<String, Object >();
        	 map.add("dealerId", dealerId); 
        	 Dealer dealer = restTemplate.postForObject(Constants.url + "getDealerById", map, Dealer.class);
        	 
        	 
        	 if(dealer.getIsSameState()==1)
        	 {
        		 partDetailById.setIgst(0);
        	 }
        	 else
        	 {
        		 partDetailById.setCgst(0);
        		 partDetailById.setSgst(0);
        	 }
        		 
        	 
		}catch(Exception e)
		{
			System.out.println("errorr  "+e.getMessage());
			e.printStackTrace();
		}
	         
		return partDetailById;
		

	}
	
	@RequestMapping(value = "/updateNextServicingDueKm", method = RequestMethod.GET)
	@ResponseBody
	public  VehicalMaster updateNextServicingDueKm(HttpServletRequest request, HttpServletResponse response) {
		 
		VehicalMaster vehicalMaster = new VehicalMaster();
	        try
			{ 
	        	
	        	int vehId = Integer.parseInt(request.getParameter("vehId"));
	        	 System.out.println("vehId"+vehId);
	        	 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object >();
	        	 map.add("vehicalId", vehId);
	        	
	        	 vehicalMaster = restTemplate.postForObject(Constants.url + "getVehicalById", map, VehicalMaster.class); 
	        	 
		}catch(Exception e)
		{
			System.out.println("errorr  "+e.getMessage());
			e.printStackTrace();
		}
	         
		return vehicalMaster;
		

	}
	
	@RequestMapping(value = "/submitServicing", method = RequestMethod.POST)
	public String  submitServicing(@RequestParam("attachFile") List<MultipartFile> attachFile, HttpServletRequest request, HttpServletResponse response) {

	 
		try
		{
			String billNo = request.getParameter("billNo");
			String billDate = request.getParameter("billDate"); 
			int type =Integer.parseInt(request.getParameter("type"));
			int typeId =Integer.parseInt(request.getParameter("typeId"));
			int servType =Integer.parseInt(request.getParameter("servType")); 
			String servDate =request.getParameter("servDate"); 
			int dealerId = Integer.parseInt(request.getParameter("dealerId"));
			int vehId = Integer.parseInt(request.getParameter("vehId"));
			String servAdvRem = request.getParameter("servAdvRem");
			String servDoneRem = request.getParameter("servDoneRem");
			float totPart = Float.parseFloat(request.getParameter("totPart")); 
			float labCharge = Float.parseFloat(request.getParameter("labCharge")); 
			float totDisc = Float.parseFloat(request.getParameter("totDisc"));
			float totExtraCharge = Float.parseFloat(request.getParameter("totExtraCharge")); 
			float taxAmt =Float.parseFloat(request.getParameter("taxAmt"));
			float taxaleAmt =Float.parseFloat(request.getParameter("taxaleAmt"));
			float total =Float.parseFloat(request.getParameter("total"));
			String typeName = request.getParameter("typeName");
			int servDoneKm=0;
			int nextDueKm=0;
			if(type==1)
			{
				servDoneKm =Integer.parseInt(request.getParameter("servDoneKm"));
				nextDueKm =Integer.parseInt(request.getParameter("nextDueKm"));
			}
			 
			
			
			VpsImageUpload upload = new VpsImageUpload();

			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			System.out.println(sdf.format(cal.getTime()));

			String curTimeStamp = sdf.format(cal.getTime());
			String pdf = null;
			try {
				pdf = attachFile.get(0).getOriginalFilename();
				 

				upload.saveUploadedFiles(attachFile, Constants.LOGIS_BILL_PDF_TYPE,
						 attachFile.get(0).getOriginalFilename());
			 
				System.out.println("upload method called for image Upload " + attachFile.toString());

			} catch (IOException e) {

				System.out.println("Exce in File Upload In GATE ENTRY  Insert " + e.getMessage());
				e.printStackTrace();
			}

			ServHeader servHeader = new ServHeader();
			servHeader.setBillNo(billNo);
			servHeader.setBillDate(billDate);
			servHeader.setServType2(type);
			servHeader.setTypeId(typeId);
			servHeader.setServType(servType);
			servHeader.setServDate(servDate);
			servHeader.setDealerId(dealerId);
			servHeader.setVehId(vehId);
			servHeader.setServAdviseRem(servAdvRem);
			servHeader.setServDoneRem(servDoneRem);
			servHeader.setSprTot(totPart);
			servHeader.setLabChrge(labCharge);
			servHeader.setTotalDisc(totDisc);
			servHeader.setTotalExtra(totExtraCharge); 
			servHeader.setTaxAmt(taxAmt);
			servHeader.setTaxableAmt(taxaleAmt); 
			servHeader.setServDoneKm(servDoneKm);
			servHeader.setNextDueKm(nextDueKm);
			 
			servHeader.setTotal(total);
			servHeader.setBillFile(pdf);
			
			String vehName=null;
			List<ServDetail> servDetailList = new ArrayList<ServDetail>();
			for(int i=0;i<addSparePartList.size();i++)
			{
				ServDetail servDetail = new ServDetail();
				servDetail.setServDate(servDate);
				servDetail.setServType(addSparePartList.get(i).getServType());
				servDetail.setGroupId(addSparePartList.get(i).getGroupId());
				servDetail.setSprId(addSparePartList.get(i).getSprId());
				servDetail.setSprQty(addSparePartList.get(i).getSprQty());
				servDetail.setSprRate(addSparePartList.get(i).getSprRate());
				servDetail.setSprTaxableAmt(addSparePartList.get(i).getSprTaxableAmt());
				servDetail.setSprTaxAmt(addSparePartList.get(i).getSprTaxAmt());
				servDetail.setTotal(addSparePartList.get(i).getTotal());
				servDetail.setDisc(addSparePartList.get(i).getDisc());
				servDetail.setExtraCharges(addSparePartList.get(i).getExtraCharges());
				vehName=addSparePartList.get(i).getVehName();
				servDetailList.add(servDetail);
			}
			servHeader.setVehNo(vehName);
			servHeader.setServDetail(servDetailList);
			
			  System.out.println("before insert "+servHeader);
			  ServHeader  res = restTemplate.postForObject(Constants.url + "postServHeader",servHeader, ServHeader.class);
			System.out.println("res "+res.toString());
			
			if(res!=null)
			{
				 if(res.getServType2()==1)
				 {
					 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object >();
		        	 map.add("vehicalId", servHeader.getVehId()); 
		        	 VehicalMaster vehicalMaster = restTemplate.postForObject(Constants.url + "getVehicalById", map, VehicalMaster.class);
		        	 vehicalMaster.setLastServicingKm(res.getServDoneKm()); 
		        	 vehicalMaster.setNextServicingKm(res.getNextDueKm());
		        	 vehicalMaster.setAlertNextServicingKm(vehicalMaster.getNextServicingKm()-100);
		        	 vehicalMaster = restTemplate.postForObject(Constants.url + "postVehicalMaster",vehicalMaster, VehicalMaster.class);
		 			System.out.println("update Vehicle"+vehicalMaster.toString());
		 			
		 			map = new LinkedMultiValueMap<String, Object >();
		 			map.add("vehId", res.getVehId());
		 			map.add("typeId", res.getTypeId());
		 			AlertVeihcleServicing alertVeihcleServicing = restTemplate.postForObject(Constants.url + "getAlertVeihcleServicingByVehIdAndType", map, AlertVeihcleServicing.class);
		 			
		 			if(alertVeihcleServicing.getAlertId()!=0)
		 			{
		 				alertVeihcleServicing.setLastServKm(res.getServDoneKm());
		 				alertVeihcleServicing.setNextServKm(res.getNextDueKm());
		 				alertVeihcleServicing.setAlertServKm(alertVeihcleServicing.getNextServKm()-100);
		 				AlertVeihcleServicing update = restTemplate.postForObject(Constants.url + "postAlertVeihcleServicing", alertVeihcleServicing, AlertVeihcleServicing.class);
		 				System.out.println("update alert Vehicle " + update);
		 			}
		 			else
		 			{
		 				alertVeihcleServicing.setVehId(res.getVehId());
		 				alertVeihcleServicing.setVehNo(res.getVehNo());
		 				alertVeihcleServicing.setTypeId(res.getTypeId());
		 				alertVeihcleServicing.setTypeName(typeName);
		 				alertVeihcleServicing.setLastServKm(res.getServDoneKm());
		 				alertVeihcleServicing.setNextServKm(res.getNextDueKm());
		 				alertVeihcleServicing.setAlertServKm(alertVeihcleServicing.getNextServKm()-100);
		 				AlertVeihcleServicing insert = restTemplate.postForObject(Constants.url + "postAlertVeihcleServicing", alertVeihcleServicing, AlertVeihcleServicing.class);
		 				System.out.println("insert alert Vehicle " + insert);
		 			}
				 }
	        	 
			}
		 
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return "redirect:/insertSarvicing";

	}
	
	List<ServDetailAddPart> addSparePartListInEdit = new ArrayList<ServDetailAddPart>();
	@RequestMapping(value = "/editServiceBill/{servId}", method = RequestMethod.GET)
	public ModelAndView editServiceBill(@PathVariable int servId, HttpServletRequest request, HttpServletResponse response) {

		ServHeader viewServicingDetail = new ServHeader(); 
		List<VehicalMaster> vehicleList = new ArrayList<VehicalMaster>();
		List<MachineMaster> machineList = new ArrayList<MachineMaster>();
		ModelAndView model = new ModelAndView("logistics/editServiceBill"); 
		try
		{
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			String settingKey =  "m_logis_labour_group"; 
			map.add("settingKeyList", settingKey);
			FrItemStockConfigureList settingList = restTemplate.postForObject(Constants.url + "getDeptSettingValue", map,
					FrItemStockConfigureList.class);
			int labourGroupId = settingList.getFrItemStockConfigure().get(0).getSettingValue();
			
			map = new LinkedMultiValueMap<String, Object>();
        	map.add("servId", servId); 
        	viewServicingDetail = restTemplate.postForObject(Constants.url + "getServHeaderAndDetailById",map, ServHeader.class);
        	 
        	SparePart[] getAllSparePart = restTemplate.getForObject(Constants.url + "getAllSparePart", SparePart[].class);  
        	List<Dealer> getAllDealerList = restTemplate.getForObject(Constants.url + "getAllDealerList", List.class);  
        	vehicleList = restTemplate.getForObject(Constants.url + "getAllVehicalList", List.class);  
        	machineList = restTemplate.getForObject(Constants.url + "getAllMachineMaster", List.class);
        	
	       	 map = new LinkedMultiValueMap<String, Object >();
	       	 map.add("typeId", viewServicingDetail.getTypeId()); 
	       	SprGroup[] sprGroupList = restTemplate.postForObject(Constants.url + "getSprGroupListByTypeId", map, SprGroup[].class);
	       	
			ArrayList<SparePart> sparePartList = new ArrayList<>(Arrays.asList(getAllSparePart));
			ArrayList<SprGroup> groupList = new ArrayList<>(Arrays.asList(sprGroupList));
			
			addSparePartListInEdit = new ArrayList<ServDetailAddPart>();
			
			 map = new LinkedMultiValueMap<String, Object >();
        	 map.add("dealerId", viewServicingDetail.getDealerId()); 
        	 Dealer dealer = restTemplate.postForObject(Constants.url + "getDealerById", map, Dealer.class); 
        	  
	         map = new LinkedMultiValueMap<String, Object>();
	        	map.add("type", viewServicingDetail.getServType2());
	        List<MechType> typeList = restTemplate.postForObject(Constants.url + "getTypeListByType",map, List.class);
			
			for(int i=0;i<viewServicingDetail.getServDetail().size();i++)
			{
				ServDetailAddPart servDetailAddPart = new ServDetailAddPart();
				servDetailAddPart.setServDetailId(viewServicingDetail.getServDetail().get(i).getServDetailId());
				servDetailAddPart.setServId(viewServicingDetail.getServDetail().get(i).getServId());
				servDetailAddPart.setServDate(viewServicingDetail.getServDetail().get(i).getServDate());
				servDetailAddPart.setServType(viewServicingDetail.getServDetail().get(i).getServType());  
				servDetailAddPart.setSprQty(viewServicingDetail.getServDetail().get(i).getSprQty());
				servDetailAddPart.setSprRate(viewServicingDetail.getServDetail().get(i).getSprRate());
				servDetailAddPart.setSprTaxAmt(viewServicingDetail.getServDetail().get(i).getSprTaxAmt());
				servDetailAddPart.setSprTaxableAmt(viewServicingDetail.getServDetail().get(i).getSprTaxableAmt());
				servDetailAddPart.setDisc(viewServicingDetail.getServDetail().get(i).getDisc());
				servDetailAddPart.setExtraCharges(viewServicingDetail.getServDetail().get(i).getExtraCharges()); 
				servDetailAddPart.setVehName(viewServicingDetail.getVehNo());
				servDetailAddPart.setTotal(viewServicingDetail.getServDetail().get(i).getTotal());  
				for(int j=0;j<sparePartList.size();j++)
				{
					if(viewServicingDetail.getServDetail().get(i).getSprId()==sparePartList.get(j).getSprId())
					{
						servDetailAddPart.setSprId(sparePartList.get(j).getSprId());
						servDetailAddPart.setPartName(sparePartList.get(j).getSprName());  
			        	 if(dealer.getIsSameState()==1)
			        	 {
			        		 sparePartList.get(j).setIgst(0);
			        	 }
			        	 else
			        	 {
			        		 sparePartList.get(j).setCgst(0);
			        		 sparePartList.get(j).setSgst(0);
			        	 }
			        	 servDetailAddPart.setSprTaxAmtPer(sparePartList.get(j).getCgst()+sparePartList.get(j).getSgst()+sparePartList.get(j).getIgst());
			        	 servDetailAddPart.setDiscPer(sparePartList.get(j).getDisc());
			        	 servDetailAddPart.setExtraChargesPer(sparePartList.get(j).getExtraCharges());
						break;
					}
				}
				
				for(int j=0;j<groupList.size();j++)
				{
					 
					if(viewServicingDetail.getServDetail().get(i).getGroupId()==groupList.get(j).getGroupId())
					{
						 
						servDetailAddPart.setGroupId(groupList.get(j).getGroupId());
						servDetailAddPart.setGroupName(groupList.get(j).getGroupName());
						break;
					}
				}
				
				addSparePartListInEdit.add(servDetailAddPart);
			}
			System.out.println("header "+viewServicingDetail );
			System.out.println("addSparePartListInEdit " + addSparePartListInEdit);
			model.addObject("dealerList",getAllDealerList); 
			model.addObject("sprGroupList",sprGroupList); 
			model.addObject("sprPartList",getAllSparePart);
			if(viewServicingDetail.getServType2()==1) 
				model.addObject("vehicleList",vehicleList);
			else
				model.addObject("vehicleList",machineList); 
			 model.addObject("editServicing", viewServicingDetail);
			 model.addObject("typeList", typeList);
			 model.addObject("servDetail", addSparePartListInEdit);
			 model.addObject("labourGroupId", labourGroupId);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return model; 
	}
	
	@RequestMapping(value = "/addSparePartInEditInvoice", method = RequestMethod.GET)
	@ResponseBody
	public List<ServDetailAddPart> addSparePartInEditInvoice(HttpServletRequest request, HttpServletResponse response) {
		 
		
	        try
			{ 
	        	
	        	int sprId = Integer.parseInt(request.getParameter("sprId"));
	        	String sprName = request.getParameter("sprName");
	        	int groupId = Integer.parseInt(request.getParameter("groupId"));
	        	String groupName = request.getParameter("groupName");
	        	int vehId = Integer.parseInt(request.getParameter("vehId"));
	        	String vehName = request.getParameter("vehName");
	        	int spareRate = Integer.parseInt(request.getParameter("spareRate"));
	        	int spareQty = Integer.parseInt(request.getParameter("spareQty"));
	        	float taxaleAmtDetail = Float.parseFloat(request.getParameter("taxaleAmtDetail"));
	        	float taxAmtDetail = Float.parseFloat(request.getParameter("taxAmtDetail"));
	        	float totalDetail = Float.parseFloat(request.getParameter("totalDetail"));
	        	float discDetail = Float.parseFloat(request.getParameter("discDetail"));
	        	float extraChargeDetail = Float.parseFloat(request.getParameter("extraChargeDetail"));
	        	int servTypeDetail = Integer.parseInt(request.getParameter("servTypeDetail")); 
	        	float discPer = Float.parseFloat(request.getParameter("discPer"));
	        	float extraChargePer = Float.parseFloat(request.getParameter("extraChargePer"));
	        	float taxPer = Float.parseFloat(request.getParameter("taxPer"));
	        	 
	        	 ServDetailAddPart addSparePart = new ServDetailAddPart();
	        	 addSparePart.setSprId(sprId);
	        	 addSparePart.setPartName(sprName);
	        	 addSparePart.setGroupId(groupId);
	        	 addSparePart.setVehId(vehId);
	        	 addSparePart.setVehName(vehName);
	        	 addSparePart.setGroupName(groupName);
	        	 addSparePart.setSprRate(spareRate);
	        	 addSparePart.setSprQty(spareQty);
	        	 addSparePart.setSprTaxableAmt(taxaleAmtDetail);
	        	 addSparePart.setSprTaxAmt(taxAmtDetail);
	        	 addSparePart.setTotal(totalDetail);
	        	 addSparePart.setDisc(discDetail);
	        	 addSparePart.setExtraCharges(extraChargeDetail);
	        	 addSparePart.setServType(servTypeDetail); 
	        	 addSparePart.setDiscPer(discPer);
	        	 addSparePart.setExtraChargesPer(extraChargePer);
	        	 addSparePart.setSprTaxAmtPer(taxPer);
	        	 addSparePartListInEdit.add(addSparePart);
	        	 System.out.println("addSparePartList"+addSparePartListInEdit); 
	        	 
		}catch(Exception e)
		{
			System.out.println("errorr  "+e.getMessage());
			e.printStackTrace();
		}
	         
		return addSparePartListInEdit;
		

	}
	
	@RequestMapping(value = "/deleteSparePartInEditInvoice", method = RequestMethod.GET)
	@ResponseBody
	public List<ServDetailAddPart> deleteSparePartInEditInvoice(HttpServletRequest request, HttpServletResponse response) {
		 
		
	        try
			{ 
	        	
	        	int index = Integer.parseInt(request.getParameter("index")); 
	        	 System.out.println("index"+index); 
	        	 if(addSparePartListInEdit.get(index).getServDetailId()!=0) 
	        		 addSparePartListInEdit.get(index).setDelStatus(1); 
	        	 else
	        		 addSparePartListInEdit.remove(index);
	        		  
	        	 System.out.println("addSparePartListInEdit"+addSparePartListInEdit); 
	        	 
		}catch(Exception e)
		{
			System.out.println("errorr  "+e.getMessage());
			e.printStackTrace();
		}
	         
		return addSparePartListInEdit;
		

	}
	
	int editViewIndex;
	@RequestMapping(value = "/editSparePartInEditInvoice", method = RequestMethod.GET)
	@ResponseBody
	public ServDetailAddPart editSparePartInEditInvoice(HttpServletRequest request, HttpServletResponse response) {
		 
		ServDetailAddPart servDetailAddPart = new ServDetailAddPart();
	        try
			{ 
	        	
	        	editViewIndex = Integer.parseInt(request.getParameter("index")); 
	        	 System.out.println("editViewIndex"+editViewIndex); 
	        	 servDetailAddPart = addSparePartListInEdit.get(editViewIndex);
	        	 System.out.println("servDetailAddPart"+servDetailAddPart); 
	        	 
		}catch(Exception e)
		{
			System.out.println("errorr  "+e.getMessage());
			e.printStackTrace();
		}
	         
		return servDetailAddPart;
		

	}
	
	@RequestMapping(value = "/changeQtyOfSparePartInEditInvoice", method = RequestMethod.GET)
	@ResponseBody
	public List<ServDetailAddPart> changeQtyOfSparePartInEditInvoice(HttpServletRequest request, HttpServletResponse response) {
		 
		
        try
		{ 
        	
        	int sprId = Integer.parseInt(request.getParameter("sprId"));
        	String sprName = request.getParameter("sprName");
        	int groupId = Integer.parseInt(request.getParameter("groupId"));
        	String groupName = request.getParameter("groupName");
        	int vehId = Integer.parseInt(request.getParameter("vehId"));
        	String vehName = request.getParameter("vehName");
        	int spareRate = Integer.parseInt(request.getParameter("spareRate"));
        	int spareQty = Integer.parseInt(request.getParameter("spareQty"));
        	float taxaleAmtDetail = Float.parseFloat(request.getParameter("taxaleAmtDetail"));
        	float taxAmtDetail = Float.parseFloat(request.getParameter("taxAmtDetail"));
        	float totalDetail = Float.parseFloat(request.getParameter("totalDetail"));
        	float discDetail = Float.parseFloat(request.getParameter("discDetail"));
        	float extraChargeDetail = Float.parseFloat(request.getParameter("extraChargeDetail"));
        	int servTypeDetail = Integer.parseInt(request.getParameter("servTypeDetail")); 
        	System.out.println(spareQty);
        	addSparePartListInEdit.get(editViewIndex).setSprId(sprId);
        	addSparePartListInEdit.get(editViewIndex).setPartName(sprName);
        	addSparePartListInEdit.get(editViewIndex).setGroupId(groupId);
        	addSparePartListInEdit.get(editViewIndex).setVehId(vehId);
        	addSparePartListInEdit.get(editViewIndex).setVehName(vehName);
        	addSparePartListInEdit.get(editViewIndex).setGroupName(groupName);
        	addSparePartListInEdit.get(editViewIndex).setSprRate(spareRate);
        	addSparePartListInEdit.get(editViewIndex).setSprQty(spareQty);
        	addSparePartListInEdit.get(editViewIndex).setSprTaxableAmt(taxaleAmtDetail);
        	addSparePartListInEdit.get(editViewIndex).setSprTaxAmt(taxAmtDetail);
        	addSparePartListInEdit.get(editViewIndex).setTotal(totalDetail);
        	addSparePartListInEdit.get(editViewIndex).setDisc(discDetail);
        	addSparePartListInEdit.get(editViewIndex).setExtraCharges(extraChargeDetail);
        	addSparePartListInEdit.get(editViewIndex).setServType(servTypeDetail);
        	 
	}catch(Exception e)
	{
		System.out.println("errorr  "+e.getMessage());
		e.printStackTrace();
	}
         
	return addSparePartListInEdit;
	

}
	
	@RequestMapping(value = "/submitEditInvoice", method = RequestMethod.POST)
	public String  submitEditInvoice(@RequestParam("attachFile") List<MultipartFile> attachFile, HttpServletRequest request, HttpServletResponse response) {

	 
		try
		{
			int servId =Integer.parseInt(request.getParameter("servId"));
			String billNo = request.getParameter("billNo");
			String billDate = request.getParameter("billDate"); 
			int type =Integer.parseInt(request.getParameter("type"));
			int typeId =Integer.parseInt(request.getParameter("typeId"));
			int servType =Integer.parseInt(request.getParameter("servType")); 
			String servDate =request.getParameter("servDate"); 
			int dealerId = Integer.parseInt(request.getParameter("dealerId"));
			int vehId = Integer.parseInt(request.getParameter("vehId"));
			String servAdvRem = request.getParameter("servAdvRem");
			String servDoneRem = request.getParameter("servDoneRem");
			float totPart = Float.parseFloat(request.getParameter("totPart")); 
			float labCharge = Float.parseFloat(request.getParameter("labCharge")); 
			float totDisc = Float.parseFloat(request.getParameter("totDisc"));
			float totExtraCharge = Float.parseFloat(request.getParameter("totExtraCharge")); 
			float taxAmt =Float.parseFloat(request.getParameter("taxAmt"));
			float taxaleAmt =Float.parseFloat(request.getParameter("taxaleAmt"));
			float total =Float.parseFloat(request.getParameter("total"));
			String typeName = request.getParameter("typeName");
			int servDoneKm=0;
			int nextDueKm=0;
			if(type==1)
			{
				servDoneKm =Integer.parseInt(request.getParameter("servDoneKm"));
				nextDueKm =Integer.parseInt(request.getParameter("nextDueKm"));
			}
			String fileName =request.getParameter("fileName");
			
			VpsImageUpload upload = new VpsImageUpload();

			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			System.out.println(sdf.format(cal.getTime()));

			String curTimeStamp = sdf.format(cal.getTime());
			String pdf = null;
			try {
				pdf = attachFile.get(0).getOriginalFilename();
				 

				upload.saveUploadedFiles(attachFile, Constants.LOGIS_BILL_PDF_TYPE,
						 attachFile.get(0).getOriginalFilename());
			 
				System.out.println("upload method called for image Upload " + attachFile.toString());

			} catch (IOException e) {

				System.out.println("Exce in File Upload In GATE ENTRY  Insert " + e.getMessage());
				e.printStackTrace();
			}

			ServHeader servHeader = new ServHeader();
			servHeader.setServId(servId);
			servHeader.setBillNo(billNo);
			servHeader.setBillDate(billDate);
			servHeader.setTypeId(typeId);
			servHeader.setServType(servType);
			servHeader.setServDate(servDate);
			servHeader.setDealerId(dealerId);
			servHeader.setVehId(vehId);
			servHeader.setServAdviseRem(servAdvRem);
			servHeader.setServDoneRem(servDoneRem);
			servHeader.setSprTot(totPart);
			servHeader.setLabChrge(labCharge);
			servHeader.setTotalDisc(totDisc);
			servHeader.setTotalExtra(totExtraCharge); 
			servHeader.setTaxAmt(taxAmt);
			servHeader.setTaxableAmt(taxaleAmt);
			servHeader.setServDoneKm(servDoneKm);
			servHeader.setNextDueKm(nextDueKm);
			servHeader.setTotal(total);
			servHeader.setServType2(type);
			if(pdf!=null && pdf.length()>0) 
				servHeader.setBillFile(pdf); 
			else
				servHeader.setBillFile(fileName);
			
			
			String vehName=null;
			List<ServDetail> servDetailList = new ArrayList<ServDetail>();
			for(int i=0;i<addSparePartListInEdit.size();i++)
			{
				ServDetail servDetail = new ServDetail();
				servDetail.setServDetailId(addSparePartListInEdit.get(i).getServDetailId());
				servDetail.setServDate(servDate);
				servDetail.setServType(addSparePartListInEdit.get(i).getServType());
				servDetail.setGroupId(addSparePartListInEdit.get(i).getGroupId());
				servDetail.setSprId(addSparePartListInEdit.get(i).getSprId());
				servDetail.setSprQty(addSparePartListInEdit.get(i).getSprQty());
				servDetail.setSprRate(addSparePartListInEdit.get(i).getSprRate());
				servDetail.setSprTaxableAmt(addSparePartListInEdit.get(i).getSprTaxableAmt());
				servDetail.setSprTaxAmt(addSparePartListInEdit.get(i).getSprTaxAmt());
				servDetail.setTotal(addSparePartListInEdit.get(i).getTotal());
				servDetail.setDisc(addSparePartListInEdit.get(i).getDisc());
				servDetail.setExtraCharges(addSparePartListInEdit.get(i).getExtraCharges());
				servDetail.setDelStatus(addSparePartListInEdit.get(i).getDelStatus());
				vehName=addSparePartListInEdit.get(i).getVehName();
				servDetailList.add(servDetail);
			}
			servHeader.setVehNo(vehName);
			servHeader.setServDetail(servDetailList);
			
			  System.out.println("before insert "+servHeader);
			  ServHeader  res = restTemplate.postForObject(Constants.url + "postServHeader",servHeader, ServHeader.class);
			System.out.println("insertSparePart"+servHeader.toString());
			
			if(res!=null)
			{
				if(res.getServType2()==1)
				 {
					 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object >();
		        	 map.add("vehicalId", servHeader.getVehId()); 
		        	 VehicalMaster vehicalMaster = restTemplate.postForObject(Constants.url + "getVehicalById", map, VehicalMaster.class);
		        	 vehicalMaster.setLastServicingKm(servHeader.getServDoneKm()); 
		        	 vehicalMaster.setNextServicingKm(servHeader.getNextDueKm());
		        	 vehicalMaster.setAlertNextServicingKm(vehicalMaster.getNextServicingKm()-100);
		        	 vehicalMaster = restTemplate.postForObject(Constants.url + "postVehicalMaster",vehicalMaster, VehicalMaster.class);
		 			System.out.println("update Vehicle"+vehicalMaster.toString());
		 			
		 			map = new LinkedMultiValueMap<String, Object >();
		 			map.add("vehId", res.getVehId());
		 			map.add("typeId", res.getTypeId());
		 			AlertVeihcleServicing alertVeihcleServicing = restTemplate.postForObject(Constants.url + "getAlertVeihcleServicingByVehIdAndType", map, AlertVeihcleServicing.class);
		 			
		 			if(alertVeihcleServicing.getAlertId()!=0)
		 			{
		 				alertVeihcleServicing.setLastServKm(res.getServDoneKm());
		 				alertVeihcleServicing.setNextServKm(res.getNextDueKm());
		 				alertVeihcleServicing.setAlertServKm(alertVeihcleServicing.getNextServKm()-100);
		 				AlertVeihcleServicing update = restTemplate.postForObject(Constants.url + "postAlertVeihcleServicing", alertVeihcleServicing, AlertVeihcleServicing.class);
		 				System.out.println("update alert Vehicle " + update);
		 			}
		 			else
		 			{
		 				alertVeihcleServicing.setVehId(res.getVehId());
		 				alertVeihcleServicing.setVehNo(res.getVehNo());
		 				alertVeihcleServicing.setTypeId(res.getTypeId());
		 				alertVeihcleServicing.setTypeName(typeName);
		 				alertVeihcleServicing.setLastServKm(res.getServDoneKm());
		 				alertVeihcleServicing.setNextServKm(res.getNextDueKm());
		 				alertVeihcleServicing.setAlertServKm(alertVeihcleServicing.getNextServKm()-100);
		 				AlertVeihcleServicing insert = restTemplate.postForObject(Constants.url + "postAlertVeihcleServicing", alertVeihcleServicing, AlertVeihcleServicing.class);
		 				System.out.println("insert alert Vehicle " + insert);
		 			}
				 }
	        	
				
			}
		 
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return "redirect:/showServicingListToDirector";

	}
	
	//-----------------------------------------vehicleDocument-----------------------------------------
	
	@RequestMapping(value = "/insertVehicleDocument", method = RequestMethod.GET)
	public ModelAndView insertVehicleDocument(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("logistics/insertVehicleDocument"); 
		try
		{ 
			
			List<Document> getAllDocumentList = restTemplate.getForObject(Constants.url + "getAllDocumentList", List.class);  
			List<VehicalMaster> vehicleList = restTemplate.getForObject(Constants.url + "getAllVehicalList", List.class); 
			model.addObject("documentList",getAllDocumentList);  
			model.addObject("vehicleList",vehicleList);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return model;

	}
	
	@RequestMapping(value = "/submitVehicleDocument", method = RequestMethod.POST)
	public String submitVehicleDocument(@RequestParam("documentFile") List<MultipartFile> documentFile,HttpServletRequest request, HttpServletResponse response) {
 
		try
		{
			String vehDocId = request.getParameter("vehDocId");
			String entryDate = request.getParameter("entryDate");
			String docDate = request.getParameter("docDate"); 
			int vehId =Integer.parseInt(request.getParameter("vehId"));
			int docId =Integer.parseInt(request.getParameter("docId")); 
			String expireDate = request.getParameter("expireDate");
			String noficationDate = request.getParameter("noficationDate"); 
			int currentKm =Integer.parseInt(request.getParameter("currentKm")); 
			String document = request.getParameter("docPath");
			
			VehicleDcoument insert = new VehicleDcoument();
			VpsImageUpload upload = new VpsImageUpload();
			String docFile = null;
			try {
				docFile = documentFile.get(0).getOriginalFilename();
				 

				upload.saveUploadedFiles(documentFile, Constants.LOGIS_BILL_PDF_TYPE,
						documentFile.get(0).getOriginalFilename());
			 
				System.out.println("upload method called for image Upload " + documentFile.toString());

			} catch (IOException e) {

				System.out.println("Exce in File Upload In GATE ENTRY  Insert " + e.getMessage());
				e.printStackTrace();
			}
			if(vehDocId==null || vehDocId.equals(""))
				insert.setVehDocId(0);
			else
				insert.setVehDocId(Integer.parseInt(vehDocId));
			insert.setVehId(vehId);
			insert.setDocId(docId);
			insert.setCurrentKm(currentKm);
			insert.setEntryDate(entryDate);
			insert.setDocDate(docDate);
			insert.setDocExpireDate(expireDate);
			insert.setDocExpNotificationDate(noficationDate);
			if(docFile!=null && docFile.length()>0) 
				insert.setDocPath(docFile); 
			else
				insert.setDocPath(document);
				  
			System.out.println("document " + document);
			System.out.println("docFile " + docFile);
			System.out.println("insert " + insert);
			insert = restTemplate.postForObject(Constants.url + "postVehicleDocument",insert, VehicleDcoument.class);
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return "redirect:/showVehicleDocumentList";

	}
	
	@RequestMapping(value = "/showVehicleDocumentList", method = RequestMethod.GET)
	public ModelAndView showVehicleDocumentList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("logistics/showVehicleDocumentList"); 
		try
		{ 
			
			List<Document> getAllDocumentList = restTemplate.getForObject(Constants.url + "getAllDocumentList", List.class);
			List<VehicleDcoument> getAllVehicleDcoument = restTemplate.getForObject(Constants.url + "getAllVehicleDcoument", List.class);
			List<VehicalMaster> vehicleList = restTemplate.getForObject(Constants.url + "getAllVehicalList", List.class); 
			model.addObject("documentList",getAllDocumentList);
			model.addObject("vehicleDocument",getAllVehicleDcoument);
			model.addObject("vehicleList",vehicleList);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return model;

	}
	
	@RequestMapping(value = "/editVehicleDocument/{vehDocId}", method = RequestMethod.GET)
	public ModelAndView editVehicleDocument(@PathVariable int vehDocId, HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("logistics/insertVehicleDocument"); 
		try
		{ 
			
			List<Document> getAllDocumentList = restTemplate.getForObject(Constants.url + "getAllDocumentList", List.class); 
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("vehDocId", vehDocId);
			VehicleDcoument vehicleDcoument = restTemplate.postForObject(Constants.url + "getVehicleDcoumentById",map, VehicleDcoument.class); 
			List<VehicalMaster> vehicleList = restTemplate.getForObject(Constants.url + "getAllVehicalList", List.class); 
			model.addObject("documentList",getAllDocumentList);
			model.addObject("vehicleDocument",vehicleDcoument);
			model.addObject("vehicleList",vehicleList);
			model.addObject("vehDocId",vehDocId);
			model.addObject("imageUrl",Constants.LOGIS_BILL_URL);
			System.out.println(Constants.LOGIS_BILL_URL+vehicleDcoument.getDocPath());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return model;

	}
	String documentFile;
	@RequestMapping(value = "/viewDetailVehicleDocument/{vehDocId}", method = RequestMethod.GET)
	public ModelAndView viewDetailVehicleDocument(@PathVariable int vehDocId, HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("logistics/viewDetailVehicleDocument"); 
		try
		{ 
			
			List<Document> getAllDocumentList = restTemplate.getForObject(Constants.url + "getAllDocumentList", List.class); 
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("vehDocId", vehDocId);
			VehicleDcoument vehicleDcoument = restTemplate.postForObject(Constants.url + "getVehicleDcoumentById",map, VehicleDcoument.class); 
			List<VehicalMaster> vehicleList = restTemplate.getForObject(Constants.url + "getAllVehicalList", List.class); 
			model.addObject("documentList",getAllDocumentList);
			model.addObject("vehicleDocument",vehicleDcoument);
			model.addObject("vehicleList",vehicleList);
			model.addObject("vehDocId",vehDocId); 
			model.addObject("imageUrl",Constants.LOGIS_BILL_URL); 
			documentFile = vehicleDcoument.getDocPath();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return model;

	}
	
	 
	
	//--------------------------------------------------Machine Master ----------------------------------------

	@RequestMapping(value = "/showMachineList", method = RequestMethod.GET)
	public ModelAndView showMachineList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("logistics/showMachineList"); 
		try
		{
			
			List<MachineMaster> machineList = restTemplate.getForObject(Constants.url + "getAllMachineMaster", List.class);
			System.out.println("machineList"+machineList.toString());
			List<Make> makeList = restTemplate.getForObject(Constants.url + "getAllMakeList", List.class);
			System.out.println("makeList"+makeList.toString());
			List<Variant> getAllVariantList = restTemplate.getForObject(Constants.url + "getAllVariantList", List.class);
			System.out.println("getAllVariantList"+getAllVariantList.toString());
			List<VehicalType> getAllVehicalTypeList = restTemplate.getForObject(Constants.url + "getAllVehicalTypeList", List.class);
			System.out.println("getAllVehicalTypeList"+getAllVehicalTypeList.toString());
			List<Dealer> getAllDealerList = restTemplate.getForObject(Constants.url + "getAllDealerList", List.class);
			System.out.println("getAllDealerList"+getAllDealerList.toString());
			model.addObject("makeList",makeList);
			model.addObject("machineList",machineList); 
			model.addObject("variantList",getAllVariantList); 
			model.addObject("typeList",getAllVehicalTypeList);
			model.addObject("dealerList",getAllDealerList);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return model;

	}
	
	@RequestMapping(value = "/insertMachine", method = RequestMethod.POST)
	public String  insertMachine(HttpServletRequest request, HttpServletResponse response) {

	 
		try
		{
			String machineId = request.getParameter("machineId");
			String machineName = request.getParameter("machineName");
			int makeId =Integer.parseInt(request.getParameter("makeId"));
			int typeId =Integer.parseInt(request.getParameter("typeId"));
			int dealerId =Integer.parseInt(request.getParameter("dealerId"));
			int variantId =Integer.parseInt(request.getParameter("variantId"));  
			String machineNo = request.getParameter("machineNo"); 
			String color = request.getParameter("color");
			String variablePart = request.getParameter("variablePart");
			
			String purDate = request.getParameter("purDate");
			String regDate = request.getParameter("regDate"); 
			int cleaningFrq =Integer.parseInt(request.getParameter("cleaningFrq"));
			int alertFrq =Integer.parseInt(request.getParameter("alertFrq")); 
			String lastCleanDate = request.getParameter("lastCleanDate");
			String nextCleanDate = request.getParameter("nextCleanDate");
			String nextAlertDate = request.getParameter("nextAlertDate");
			String lastAmcDate = request.getParameter("lastAmcDate");
			String nextAmcDate = request.getParameter("nextAmcDate");
			String alertAmcDate = request.getParameter("alertAmcDate");

			MachineMaster insertMachineMaster = new MachineMaster();
			if(machineId==null || machineId.equals(""))
				insertMachineMaster.setMachineId(0);
			else
				insertMachineMaster.setMachineId(Integer.parseInt(machineId));  
			insertMachineMaster.setMachineName(machineName);
			insertMachineMaster.setMakeId(makeId);
			insertMachineMaster.setTypeId(typeId);
			insertMachineMaster.setDealerId(dealerId);
			insertMachineMaster.setVariantId(variantId);
			insertMachineMaster.setMachineNo(machineNo);
			insertMachineMaster.setColor(color);
			insertMachineMaster.setVariablePart(variablePart);
			insertMachineMaster.setPurchaseDate(purDate);
			insertMachineMaster.setRegistrationDate(regDate);
			insertMachineMaster.setCleaningFrq(cleaningFrq);
			insertMachineMaster.setAlertFreq(alertFrq);
			insertMachineMaster.setLastCleaningDate(lastCleanDate);
			insertMachineMaster.setNextCleaningDate(nextCleanDate);
			insertMachineMaster.setNextAlertDate(nextAlertDate);
			insertMachineMaster.setLastAmcDate(lastAmcDate);
			insertMachineMaster.setNextAmcDate(nextAmcDate);
			insertMachineMaster.setAlertAmcDate(alertAmcDate);
			  
			insertMachineMaster = restTemplate.postForObject(Constants.url + "postMachineMaster",insertMachineMaster, MachineMaster.class);
			System.out.println("insertMachineMaster "+insertMachineMaster.toString());
		 
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return "redirect:/showMachineList";

	}
	
	
	@RequestMapping(value = "/deleteMachine/{machineId}", method = RequestMethod.GET)
	public String deleteMachine(@PathVariable int machineId, HttpServletRequest request, HttpServletResponse response) {

		 
		try
		{
			 System.out.println("machineId "+machineId);
        	 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object >();
        	 map.add("machineId", machineId);
        	 Info info = restTemplate.postForObject(Constants.url + "deleteMachineMaster",map, Info.class);
 			System.out.println("info"+info.toString()); 
		 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		 
		return "redirect:/showMachineList";

	}
	
	@RequestMapping(value = "/editMachine", method = RequestMethod.GET)
	@ResponseBody
	public MachineMaster editMachine(HttpServletRequest request, HttpServletResponse response) {
		 
		MachineMaster editMachine = new MachineMaster();
	        try
			{ 
	        	
	        	int machineId = Integer.parseInt(request.getParameter("machineId"));
	        	 System.out.println("machineId "+machineId);
	        	 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object >();
	        	 map.add("machineId", machineId);
	        	
	        	 editMachine = restTemplate.postForObject(Constants.url + "getMachineMasterById", map, MachineMaster.class);
			 System.out.println("editVehicle " + editMachine); 
		}catch(Exception e)
		{
			System.out.println("errorr  "+e.getMessage());
			e.printStackTrace();
		}
	         
		return editMachine; 
	}
	
	//----------------------------------------------LogisAmc--------------------------------------------------
	List<MachineOrVehicle> machineListOrVehicleList = new ArrayList<MachineOrVehicle>();
	
	@RequestMapping(value = "/insertAmc", method = RequestMethod.GET)
	public ModelAndView insertAmc(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("logistics/insertAmc"); 
		try
		{
			List<Dealer> getAllDealerList = restTemplate.getForObject(Constants.url + "getAllDealerList", List.class);
			System.out.println("getAllDealerList"+getAllDealerList.toString()); 
			model.addObject("dealerList", getAllDealerList);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return model;

	}
	
	@RequestMapping(value = "/machineListOrVehicleList", method = RequestMethod.GET)
	@ResponseBody
	public List<MachineOrVehicle> machineListOrVehicleList(HttpServletRequest request, HttpServletResponse response) {
		 
		machineListOrVehicleList = new ArrayList<MachineOrVehicle>();
	        try
			{ 
	        	
	        	int typeId = Integer.parseInt(request.getParameter("typeId")); 
	        	
	        	if(typeId==1)
	        	{
	        		VehicalMaster[] vehicleList = restTemplate.getForObject(Constants.url + "getAllVehicalList", VehicalMaster[].class);
	    			System.out.println("vehicleList"+vehicleList.toString());
	    			
	    			ArrayList<VehicalMaster> vehicalMasterList = new ArrayList<>(Arrays.asList(vehicleList));
	    			
	    			for(int i=0;i<vehicalMasterList.size();i++)
	    			{
	    				MachineOrVehicle machineOrVehicle = new MachineOrVehicle();
	    				machineOrVehicle.setMechId(vehicalMasterList.get(i).getVehId());
	    				machineOrVehicle.setMechName(vehicalMasterList.get(i).getVehNo());
	    				machineOrVehicle.setDealerId(vehicalMasterList.get(i).getDealerId());
	    				machineListOrVehicleList.add(machineOrVehicle);
	    			}
	        	}
	        	else
	        	{
	        		MachineMaster[] machineList = restTemplate.getForObject(Constants.url + "getAllMachineMaster", MachineMaster[].class);
	    			System.out.println("machineList"+machineList.toString());
	    			
	    			ArrayList<MachineMaster> machineMasterList = new ArrayList<>(Arrays.asList(machineList));
	    			for(int i=0;i<machineMasterList.size();i++)
	    			{
	    				MachineOrVehicle machineOrVehicle = new MachineOrVehicle();
	    				machineOrVehicle.setMechId(machineMasterList.get(i).getMachineId());
	    				machineOrVehicle.setMechName(machineMasterList.get(i).getMachineName());
	    				machineOrVehicle.setDealerId(machineMasterList.get(i).getDealerId());
	    				machineListOrVehicleList.add(machineOrVehicle);
	    			}
	        	}
	        	
	        	 
			 System.out.println("machineListOrVehicleList " + machineListOrVehicleList); 
		}catch(Exception e)
		{
			System.out.println("errorr  "+e.getMessage());
			e.printStackTrace();
		}
	         
		return machineListOrVehicleList; 
	}
	
	@RequestMapping(value = "/getDealerIdBymechId", method = RequestMethod.GET)
	@ResponseBody
	public int getDealerIdBymechId(HttpServletRequest request, HttpServletResponse response) { 
		
		int dealerId=0;
	        try
			{ 
	        	
	        	int mechId = Integer.parseInt(request.getParameter("mechId")); 
	        	System.out.println("mechId "+mechId);
	        	for(int i=0;i<machineListOrVehicleList.size();i++)
	        	{
	        		if(mechId==machineListOrVehicleList.get(i).getMechId())
	        			dealerId=machineListOrVehicleList.get(i).getDealerId();
	        	}
	        	
	         
		}catch(Exception e)
		{
			System.out.println("errorr  "+e.getMessage());
			e.printStackTrace();
		}
	         
		return dealerId; 
	}
	
	@RequestMapping(value = "/submitAmc", method = RequestMethod.POST)
	public String  submitAmc(HttpServletRequest request, HttpServletResponse response) {

	 
		try
		{
			 
			int typeId =Integer.parseInt(request.getParameter("typeId"));
			int dealerId =Integer.parseInt(request.getParameter("dealerId"));
			String mechName = request.getParameter("mechName"); 
			int mechId =Integer.parseInt(request.getParameter("mechId")); 
			String dealerName = request.getParameter("dealerName"); 
			int amcAlertFrq =Integer.parseInt(request.getParameter("amcAlertFrq"));
			String fromDate = request.getParameter("fromDate"); 
			String toDate = request.getParameter("toDate");
			String alertDate = request.getParameter("alertDate"); 
			String billNo = request.getParameter("billNo");
			float taxableAmt = Float.parseFloat(request.getParameter("taxableAmt"));  
			float taxAmt = Float.parseFloat(request.getParameter("taxAmt"));   
			float total = Float.parseFloat(request.getParameter("total")); 

			LogisAmc insertLogisAmc = new LogisAmc();
			insertLogisAmc.setTypeId(typeId);
			insertLogisAmc.setDealerId(dealerId);
			insertLogisAmc.setDealerName(dealerName);
			insertLogisAmc.setMechId(mechId);
			insertLogisAmc.setMechName(mechName);
			insertLogisAmc.setAmcFromDate(fromDate);
			insertLogisAmc.setAmcToDate(toDate);
			insertLogisAmc.setAmcAlertDate(alertDate);
			insertLogisAmc.setBillNo(billNo);
			insertLogisAmc.setAmcTaxableAmt(taxableAmt);
			insertLogisAmc.setAmcTaxAmt(taxAmt);
			insertLogisAmc.setAmcTotal(total);
			insertLogisAmc.setAmcAlertFreq(amcAlertFrq);
			LogisAmc res = restTemplate.postForObject(Constants.url + "postLogisAmc",insertLogisAmc, LogisAmc.class);
			System.out.println("res "+res.toString());
			if(res!=null)
			{
				if(res.getTypeId()==1)
				{
					 
		        	 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object >();
		        	 map.add("vehicalId", res.getMechId()); 
		        	 VehicalMaster vehicalMaster = restTemplate.postForObject(Constants.url + "getVehicalById", map, VehicalMaster.class);
		        	 vehicalMaster.setLastAmcDate(res.getAmcFromDate());
		        	 vehicalMaster.setNextAmcDate(res.getAmcToDate());
		        	 vehicalMaster.setAlertAmcDate(res.getAmcAlertDate());
		        	 VehicalMaster update = restTemplate.postForObject(Constants.url + "postVehicalMaster",vehicalMaster, VehicalMaster.class);
		 			System.out.println("update"+update.toString());
				}
				else if(res.getTypeId()==2)
				{
					 
		        	 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object >(); 
		        	 map.add("machineId", res.getMechId()); 
		        	 MachineMaster machineMaster = restTemplate.postForObject(Constants.url + "getMachineMasterById", map, MachineMaster.class);
		        	 machineMaster.setLastAmcDate(res.getAmcFromDate());
		        	 machineMaster.setNextAmcDate(res.getAmcToDate());
		        	 machineMaster.setAlertAmcDate(res.getAmcAlertDate());
		        	 MachineMaster update = restTemplate.postForObject(Constants.url + "postMachineMaster",machineMaster, MachineMaster.class);
		 			System.out.println("update"+update.toString());
				}
			}
		 
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return "redirect:/insertAmc";

	}
	
	@RequestMapping(value = "/showAmcList", method = RequestMethod.GET)
	public ModelAndView showAmcList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("logistics/showAmcList");  
		return model;

	}
	
	@RequestMapping(value = "/serchAmcList", method = RequestMethod.GET)
	@ResponseBody
	public List<LogisAmc> serchAmcList(HttpServletRequest request, HttpServletResponse response) { 
		
		List<LogisAmc> logisAmcList = new ArrayList<LogisAmc>();
	        try
			{ 
	        	int typeId = Integer.parseInt(request.getParameter("typeId"));
	        	int mechId = Integer.parseInt(request.getParameter("mechId")); 
	        	System.out.println("mechId "+mechId);
	        	System.out.println("typeId "+typeId);
	        	
	        	 if(mechId==0)
	        	 {
	        		 System.out.println("in if mechid 0");
	        		 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
	        		 map.add("typeId", typeId);
	        		 logisAmcList = restTemplate.postForObject(Constants.url + "getLogisAmcListByTypeId",map, List.class);
	        	 }
	        	 else
	        	 {
	        		 System.out.println("in else mechid"+mechId);
	        		 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
	        		 map.add("typeId", typeId);
	        		 map.add("mechId", mechId);
	        		 logisAmcList = restTemplate.postForObject(Constants.url + "getLogisAmcListByTypeIdAndMechId",map, List.class);
	        	 }
	        	
	         System.out.println("logisAmcList " + logisAmcList);
		}catch(Exception e)
		{
			System.out.println("errorr  "+e.getMessage());
			e.printStackTrace();
		}
	         
		return logisAmcList; 
	}
	
	//--------------------------------------------MachineServicing---------------------------------------------
	
	List<MachineServicing> getAllMachineServicingForApprove;
	
	@RequestMapping(value = "/insertMachineServicing", method = RequestMethod.GET)
	public ModelAndView insertMachineServicing(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("logistics/insertMachineServicing"); 
		try
		{
			List<Dealer> getAllDealerList = restTemplate.getForObject(Constants.url + "getAllDealerList", List.class);
			System.out.println("getAllDealerList"+getAllDealerList.toString()); 
		  	List<MachineMaster> machineList = restTemplate.getForObject(Constants.url + "getAllMachineMaster", List.class);
		  	
		  	Date date = new Date(); 
			Format formatter = new SimpleDateFormat("dd-MM-yyyy");
			String today = formatter.format(date);

			model.addObject("today", today);
			model.addObject("dealerList", getAllDealerList);
			model.addObject("machineList", machineList);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return model;

	}
	
	@RequestMapping(value = "/getNextServDate", method = RequestMethod.GET)
	@ResponseBody
	public MachineMaster getNextServDate(HttpServletRequest request, HttpServletResponse response) { 
		
		MachineMaster machineMaster = new MachineMaster();
	        try
			{ 
	        	
	        	int machineId = Integer.parseInt(request.getParameter("mechId"));
	        	 System.out.println("machineId "+machineId);
	        	 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object >();
	        	 map.add("machineId", machineId); 
	        	 machineMaster = restTemplate.postForObject(Constants.url + "getMachineMasterById", map, MachineMaster.class);
	        	
	         
		}catch(Exception e)
		{
			System.out.println("errorr  "+e.getMessage());
			e.printStackTrace();
		}
	         
		return machineMaster; 
	}
	
	@RequestMapping(value = "/submitMachineServicing", method = RequestMethod.POST)
	public String  submitMachineServicing(HttpServletRequest request, HttpServletResponse response) {

	 
		try
		{
			 
			 
			int dealerId =Integer.parseInt(request.getParameter("dealerId"));
			String mechName = request.getParameter("mechName"); 
			int machineId =Integer.parseInt(request.getParameter("machineId")); 
			String dealerName = request.getParameter("dealerName");  
			String persnName = request.getParameter("persnName"); 
			String date = request.getParameter("date");
			String nextServDate = request.getParameter("nextServDate"); 
			String alertServDate = request.getParameter("alertServDate");
			int servType =Integer.parseInt(request.getParameter("servType"));
			int alertRequired =Integer.parseInt(request.getParameter("alertRequired")); 
			int paidType =Integer.parseInt(request.getParameter("paidType")); 

			MachineServicing insertMachineServicing = new MachineServicing();
			insertMachineServicing.setDealerId(dealerId);
			insertMachineServicing.setDealerName(dealerName);
			insertMachineServicing.setMachineId(machineId);
			insertMachineServicing.setMachineName(mechName);
			insertMachineServicing.setPrsnName(persnName);
			insertMachineServicing.setDate(date);
			insertMachineServicing.setNextServDate(nextServDate);
			insertMachineServicing.setAlertServDate(alertServDate);
			insertMachineServicing.setServType(servType);
			insertMachineServicing.setIsAlertRequired(alertRequired);
			insertMachineServicing.setPaidType(paidType);
			MachineServicing res = restTemplate.postForObject(Constants.url + "postMachineServicing",insertMachineServicing, MachineServicing.class);
			System.out.println("insertMachineServicing "+insertMachineServicing.toString());
			
			if(res!=null)
			{
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object >();
	        	 map.add("machineId", machineId); 
	        	 MachineMaster machineMaster = restTemplate.postForObject(Constants.url + "getMachineMasterById", map, MachineMaster.class);
	        	 machineMaster.setLastCleaningDate(res.getDate());
	        	 machineMaster.setNextCleaningDate(res.getNextServDate());
	        	 machineMaster.setNextAlertDate(res.getAlertServDate());
	        	 MachineMaster update = restTemplate.postForObject(Constants.url + "postMachineMaster",machineMaster, MachineMaster.class);
	 			System.out.println("update "+update.toString());
			}
		 
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return "redirect:/insertMachineServicing";

	}
	
	@RequestMapping(value = "/showMachineServicingList", method = RequestMethod.GET)
	public ModelAndView showMachineServicingList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("logistics/showMachineServicingList"); 
		try
		{
			List<MachineServicing> getAllMachineServicing = restTemplate.getForObject(Constants.url + "getAllMachineServicing", List.class); 
			model.addObject("getMachineServicingList", getAllMachineServicing);
			MachineMaster[] machineList = restTemplate.getForObject(Constants.url + "getAllMachineMaster", MachineMaster[].class);
			System.out.println("machineList"+machineList.toString());
			
			ArrayList<MachineMaster> machineMasterList = new ArrayList<>(Arrays.asList(machineList));
			model.addObject("machineMasterList", machineMasterList);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return model;

	}
	
	@RequestMapping(value = "/showMachineServicingListForApproved", method = RequestMethod.GET)
	public ModelAndView showMachineServicingListForApproved(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("logistics/showMachineServicingListForApproved"); 
		try
		{
			getAllMachineServicingForApprove = new ArrayList<>();
			MachineServicing[] machineServicingList = restTemplate.getForObject(Constants.url + "getAllMachineServicing", MachineServicing[].class); 
			getAllMachineServicingForApprove = new ArrayList<>(Arrays.asList(machineServicingList));
			
			MachineMaster[] machineList = restTemplate.getForObject(Constants.url + "getAllMachineMaster", MachineMaster[].class);
			System.out.println("machineList"+machineList.toString());
			
			ArrayList<MachineMaster> machineMasterList = new ArrayList<>(Arrays.asList(machineList));
			
			model.addObject("getMachineServicingList", getAllMachineServicingForApprove);
			model.addObject("machineMasterList", machineMasterList);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return model;

	}
	
	@RequestMapping(value = "/getMachineServicingWithDate", method = RequestMethod.GET)
	@ResponseBody
	public List<MachineServicing> getMachineServicingWithDate(HttpServletRequest request, HttpServletResponse response) { 
		
		List<MachineServicing> getMachineServicingWithDate = new ArrayList<MachineServicing>();
	        try
			{ 
	        	
	        	int machineId = Integer.parseInt(request.getParameter("machineId"));
	        	String fromDate = request.getParameter("from_date");  
				String toDate = request.getParameter("to_date"); 
	        	 System.out.println("machineId "+machineId);
	        	 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object >();
	        	 map.add("machineId", machineId); 
	        	 map.add("fromDate", DateConvertor.convertToYMD(fromDate)); 
	        	 map.add("toDate", DateConvertor.convertToYMD(toDate)); 
	        	 getMachineServicingWithDate = restTemplate.postForObject(Constants.url + "getMachineServicingWithDate", map, List.class);
	        	
	         
		}catch(Exception e)
		{
			System.out.println("errorr  "+e.getMessage());
			e.printStackTrace();
		}
	         
		return getMachineServicingWithDate; 
	}
	
	@RequestMapping(value = "pdf/machineServicingPdf/{from_date}/{to_date}/{machineId}", method = RequestMethod.GET)
	public ModelAndView machineServicingPdf(@PathVariable String from_date,@PathVariable String to_date,@PathVariable String machineId,HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("logistics/reportPdf/machineServicingPdf");
		try {
			List<MachineServicing> getMachineServicingWithDate = new ArrayList<MachineServicing>();  
        	 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object >();
        	 map.add("machineId", machineId); 
        	 map.add("fromDate", DateConvertor.convertToYMD(from_date)); 
        	 map.add("toDate", DateConvertor.convertToYMD(to_date)); 
        	 getMachineServicingWithDate = restTemplate.postForObject(Constants.url + "getMachineServicingWithDate", map, List.class);
			 
			 
			model.addObject("staticlist", getMachineServicingWithDate);
			model.addObject("fromDate", from_date);
			model.addObject("toDate", to_date); 
			System.out.println("getMachineServicingWithDate" + getMachineServicingWithDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;

	}
	
	@RequestMapping(value = "/approvedMachineServicing", method = RequestMethod.POST)
	public String  approvedMachineServicing(HttpServletRequest request, HttpServletResponse response) {

	 
		try
		{
			 
			 
			String []checkbox=request.getParameterValues("select_to_approve");
			for (int i = 0; i < getAllMachineServicingForApprove.size(); i++) 
			{
				for(int j=0;j<checkbox.length;j++) 
				{
					if(Integer.parseInt(checkbox[j])==getAllMachineServicingForApprove.get(i).getMechServId())  
						getAllMachineServicingForApprove.get(i).setIsApproved(1);
				}
			}
			 
			
			Info info = restTemplate.postForObject(Constants.url + "approveMachineServicing",getAllMachineServicingForApprove, Info.class);
			System.out.println("getAllMachineServicingForApprove "+getAllMachineServicingForApprove.toString());
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return "redirect:/showMachineServicingListForApproved";

	}
	
	
	//-------------------------------------------------------Tray Management Report----------------------------------------------------
	
	
	@RequestMapping(value = "/showTrayManagementReport", method = RequestMethod.GET)
	public ModelAndView showTrayManagementReport(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("trayBill/showTrayManagementReport"); 
		try
		{
			List<VehicalMaster> vehicleList = restTemplate.getForObject(Constants.url + "getAllVehicalList", List.class);
			AllRoutesListResponse allRouteListResponse = restTemplate.getForObject(Constants.url + "showRouteList",
					AllRoutesListResponse.class);  
			  
			 List<Route> routeList = allRouteListResponse.getRoute();
			
			model.addObject("vehicleList", vehicleList);
			model.addObject("routeList", routeList);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return model;

	}
	
	@RequestMapping(value = "/getTrayManagementReport", method = RequestMethod.GET)
	@ResponseBody
	public List<TrayManagementReport> getTrayManagementReport(HttpServletRequest request, HttpServletResponse response) {
		
		List<TrayManagementReport> getTrayManagementReport = new ArrayList<TrayManagementReport>();
		try
		{
			int vehId = Integer.parseInt(request.getParameter("vehId"));
			int routeId = Integer.parseInt(request.getParameter("routeId"));
        	String fromDate = request.getParameter("fromDate");  
			String toDate = request.getParameter("toDate"); 
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object >();
			map.add("vehId", vehId); 
	       	map.add("routeId", routeId); 
	       	map.add("fromDate", DateConvertor.convertToYMD(fromDate)); 
	       	map.add("toDate", DateConvertor.convertToYMD(toDate)); 
	       	getTrayManagementReport = restTemplate.postForObject(Constants.url + "getTrayManagementReport", map, List.class);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return getTrayManagementReport;

	}
	
	@RequestMapping(value = "pdf/trayManagementPdf/{fromDate}/{toDate}/{routeId}/{vehId}", method = RequestMethod.GET)
	public ModelAndView trayManagementPdf(@PathVariable String fromDate,@PathVariable String toDate,@PathVariable int routeId
			,@PathVariable int vehId,HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("trayBill/trayManagementReportPdf");
		try {
			List<TrayManagementReport> getTrayManagementReport = new ArrayList<TrayManagementReport>();  
        	 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object >(); 
 			map.add("vehId", vehId); 
 	       	map.add("routeId", routeId); 
 	       	map.add("fromDate", DateConvertor.convertToYMD(fromDate)); 
 	       	map.add("toDate", DateConvertor.convertToYMD(toDate)); 
 	       	getTrayManagementReport = restTemplate.postForObject(Constants.url + "getTrayManagementReport", map, List.class);
			 
			 
			model.addObject("staticlist", getTrayManagementReport);
			model.addObject("fromDate", fromDate);
			model.addObject("toDate", toDate); 
			System.out.println("getTrayManagementReport" + getTrayManagementReport);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;

	}
	
	
	private Dimension format = PD4Constants.A2;
	private boolean landscapeValue = false;
	private int topValue = 8;
	private int leftValue = 0;
	private int rightValue = 0;
	private int bottomValue =8;
	private String unitsValue = "m";
	private String proxyHost = "";
	private int proxyPort = 0;

	private int userSpaceWidth = 750;
	private static int BUFFER_SIZE = 1024;

	@RequestMapping(value = "/Logistics", method = RequestMethod.GET)
	public void showPDF(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Inside PDf For Report URL ");
		String url = request.getParameter("url");
		System.out.println("URL " + url);
		
		File f = new File("/opt/tomcat-latest/webapps/webapi/uploads/Inward.pdf");
		//File f = new File("C:/pdf/ordermemo221.pdf");

		try {
			runConverter(Constants.ReportURL + url, f,request,response);
			//runConverter("www.google.com", f,request,response);

		} catch (IOException e) {

			System.out.println("Pdf conversion exception " + e.getMessage());
		}

		// get absolute path of the application
		ServletContext context = request.getSession().getServletContext();
		String appPath = context.getRealPath("");
		 //String filePath = "C:/pdf/ordermemo221.pdf";

		String filePath = "/opt/tomcat-latest/webapps/webapi/uploads/Inward.pdf";

		// construct the complete absolute path of the file
		String fullPath = appPath + filePath;
		File downloadFile = new File(filePath);
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(downloadFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			// get MIME type of the file
			String mimeType = context.getMimeType(fullPath);
			if (mimeType == null) {
				// set to binary type if MIME mapping not found
				mimeType = "application/pdf";
			}
			System.out.println("MIME type: " + mimeType);

			String headerKey = "Content-Disposition";

			// response.addHeader("Content-Disposition", "attachment;filename=report.pdf");
			response.setContentType("application/pdf");

			OutputStream outStream;

			outStream = response.getOutputStream();

			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;


			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}

			inputStream.close();
			outStream.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void runConverter(String urlstring, File output, HttpServletRequest request, HttpServletResponse response ) throws IOException {

		if (urlstring.length() > 0) {
			if (!urlstring.startsWith("http://") && !urlstring.startsWith("file:")) {
				urlstring = "http://" + urlstring;
			}
			System.out.println("PDF URL " + urlstring);
			java.io.FileOutputStream fos = new java.io.FileOutputStream(output);

			PD4ML pd4ml = new PD4ML();
		
			try {

				Dimension landscapeA4 = pd4ml.changePageOrientation(PD4Constants.A4);
				pd4ml.setPageSize(landscapeA4);
			
				PD4PageMark footer = new PD4PageMark();  
				
	            footer.setPageNumberTemplate("Page $[page] of $[total]");  
	            footer.setPageNumberAlignment(PD4PageMark.RIGHT_ALIGN);  
	            footer.setFontSize(10);  
	            footer.setAreaHeight(20);     
	            
	            pd4ml.setPageFooter(footer); 
				
			} catch (Exception e) {
				System.out.println("Pdf conversion method excep " + e.getMessage());
			}

			if (unitsValue.equals("mm")) {
				pd4ml.setPageInsetsMM(new Insets(topValue, leftValue, bottomValue, rightValue));
			} else {
				pd4ml.setPageInsets(new Insets(topValue, leftValue, bottomValue, rightValue));
			}

			pd4ml.setHtmlWidth(userSpaceWidth);

			pd4ml.render(urlstring, fos);
		}
	}
}
