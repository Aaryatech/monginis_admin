package com.ats.adminpanel.controller;

 
import java.awt.Dimension;
import java.awt.Insets;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList; 
import java.util.List; 
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.zefer.pd4ml.PD4Constants;
import org.zefer.pd4ml.PD4ML;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.commons.DateConvertor;
import com.ats.adminpanel.model.GetPoHeaderForPdf;
import com.ats.adminpanel.model.RmRateVerificationList;
import com.ats.adminpanel.model.RawMaterial.GetRawMaterialDetailList;
import com.ats.adminpanel.model.RawMaterial.Info;
import com.ats.adminpanel.model.RawMaterial.RawMaterialDetails;
import com.ats.adminpanel.model.RawMaterial.RawMaterialDetailsList; 
import com.ats.adminpanel.model.RawMaterial.RawMaterialTaxDetailsList;
import com.ats.adminpanel.model.RawMaterial.RawMaterialUom;
import com.ats.adminpanel.model.RawMaterial.RmItemGroup;
import com.ats.adminpanel.model.RawMaterial.RmRateVerification;
import com.ats.adminpanel.model.materialreceipt.Supplist;
import com.ats.adminpanel.model.materialrecreport.GetMaterialRecieptReportMonthWise;
import com.ats.adminpanel.model.purchaseorder.GetPurchaseOrderList;
import com.ats.adminpanel.model.purchaseorder.GetRmRateAndTax;
import com.ats.adminpanel.model.purchaseorder.PurchaseOrderDetail;
import com.ats.adminpanel.model.purchaseorder.PurchaseOrderHeader; 
import com.ats.adminpanel.model.supplierMaster.SupPaymentTermsList;
import com.ats.adminpanel.model.supplierMaster.SupplierDetails;
import com.ats.adminpanel.model.supplierMaster.TransporterList;
import com.itextpdf.text.log.SysoCounter; 

@Controller
@Scope("session")
public class PurchaseOrderController {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public List<PurchaseOrderDetail> purchaseOrderDetailList;
	public List<SupplierDetails> supplierDetailsList;
	public TransporterList transporterList ;
	public SupPaymentTermsList supPaymentTerms;
	public List<RawMaterialDetails> rawMaterialDetailsList;
	public float gstPer;
	public GetRawMaterialDetailList getRawMaterialDetailList;
	public static RawMaterialTaxDetailsList rawMaterialTaxDetailsList;
	public List<PurchaseOrderDetail> editPurchaseOrderDetailList;
	
	private GetPurchaseOrderList getPurchaseOrderList;
	public PurchaseOrderHeader purchaseOrderHeaderedit;
	
	public GetRmRateAndTax getRmRateAndTax;
	  
	@RequestMapping(value = "/showDirectPurchaseOrder", method = RequestMethod.GET)
	public ModelAndView showPurchaseOrder(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/purchaseOrder/directPurchaseOrder");
		Constants.mainAct =10;
		Constants.subAct =57;
		
		purchaseOrderDetailList=new ArrayList<PurchaseOrderDetail>();
		RestTemplate rest=new RestTemplate();
		//rawMaterialDetailsList=new ArrayList<RawMaterialDetails>();
		
		
	//	rawMaterialTaxDetailsList= new RawMaterialTaxDetailsList();
			 // rawMaterialTaxDetailsList=rest.getForObject(Constants.url + "rawMaterial/getAllRmTaxList", RawMaterialTaxDetailsList.class);
			//System.out.println("RM Tax data : "+rawMaterialTaxDetailsList);
		
		int poNo=rest.getForObject(Constants.url + "purchaseOrder/getPoNo", Integer.class);
		
		
		List<RmItemGroup> rmItemGroupList=rest.getForObject(Constants.url + "rawMaterial/getAllRmItemGroup", List.class);
		
			supPaymentTerms=new SupPaymentTermsList();
			  supPaymentTerms = rest.getForObject(Constants.url + "/showPaymentTerms",
					SupPaymentTermsList.class);

			System.out.println("Payment Term List Response:" + supPaymentTerms.toString());
			
			 getRawMaterialDetailList=rest.getForObject(Constants.url +"rawMaterial/getAllRawMaterialList", GetRawMaterialDetailList.class);
				System.out.println("RM Details : "+getRawMaterialDetailList.getRawMaterialDetailsList().toString());
				
				supplierDetailsList=new ArrayList<SupplierDetails>();
				  supplierDetailsList=rest.getForObject(Constants.url + "getAllSupplier",   List.class);
				
				transporterList=new TransporterList();
				transporterList = rest.getForObject(Constants.url + "/showTransporters",TransporterList.class);
						System.out.println("Transporter List Response:" + transporterList.toString());

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.now();
			System.out.println(dtf.format(localDate)); //2016/11/16
			
			model.addObject("todayDate", dtf.format(localDate));
			model.addObject("paymentTermsList", supPaymentTerms.getSupPaymentTermsList());
			model.addObject("transporterList", transporterList.getTransporterList());
			model.addObject("supplierList", supplierDetailsList);
			model.addObject("rmItemGroupList", rmItemGroupList);
			model.addObject("poNo", poNo);

		return model;
	}
	
	@RequestMapping(value = "/getRmListByCatId", method = RequestMethod.GET)
	public @ResponseBody List<RawMaterialDetails> getRmListByCatId(HttpServletRequest request,
		HttpServletResponse response) {
	
		
		int catId=Integer.parseInt(request.getParameter("catId"));
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,Object>();
		map.add("catId", catId);
		
		RestTemplate rest=new RestTemplate();
		RawMaterialDetailsList rawMaterialDetailsList=  rest.postForObject(Constants.url + "rawMaterial/getRawMaterialByCategory",map,
				RawMaterialDetailsList.class);
		System.out.println("rawMaterialDetailsList"+rawMaterialDetailsList.getRawMaterialDetailsList());
		
		return rawMaterialDetailsList.getRawMaterialDetailsList();
	}
	
	@RequestMapping(value = "/getUomForRawMaterial", method = RequestMethod.GET)
	public @ResponseBody List<RawMaterialUom> getUomForRawMaterial(HttpServletRequest request,
		HttpServletResponse response) {
	
		RestTemplate rest = new RestTemplate();
		List<RawMaterialUom> rawMaterialUomList = new ArrayList<RawMaterialUom>();
		try {
			 rawMaterialUomList=rest.getForObject(Constants.url + "rawMaterial/getRmUom", List.class);
			System.out.println("RM UOM data : "+rawMaterialUomList);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
		return rawMaterialUomList;
	}
	
	@RequestMapping(value = "/showPurchaseOrder", method = RequestMethod.GET)
	public ModelAndView showPurchaseOrder2(HttpServletRequest request, HttpServletResponse response) {
		
		purchaseOrderDetailList=new ArrayList<PurchaseOrderDetail>();

		ModelAndView model = new ModelAndView("masters/purchaseOrder/purchaseOrder");
		Constants.mainAct =10;
		Constants.subAct =56;
		RestTemplate rest=new RestTemplate();
		supplierDetailsList=new ArrayList<SupplierDetails>();
		  supplierDetailsList=rest.getForObject(Constants.url + "getAllSupplier",   List.class);
		  
		  transporterList=new TransporterList();
			transporterList = rest.getForObject(Constants.url + "/showTransporters",TransporterList.class);
					System.out.println("Transporter List Response:" + transporterList.toString());
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,Object>();
		map.add("status", "0,1,3");
		
		 getPurchaseOrderList=rest.postForObject(Constants.url + "purchaseOrder/getpurchaseorderList",map, GetPurchaseOrderList.class);
			System.out.println("Purchase Order : "+getPurchaseOrderList.getPurchaseOrderHeaderList());
			
			for(int i=0;i<getPurchaseOrderList.getPurchaseOrderHeaderList().size();i++)
			{
				getPurchaseOrderList.getPurchaseOrderHeaderList().get(i).setPoDate(DateConvertor.convertToDMY(getPurchaseOrderList.getPurchaseOrderHeaderList().get(i).getPoDate()));
			}
			
			model.addObject("purchaseorderlist",getPurchaseOrderList.getPurchaseOrderHeaderList());
			model.addObject("supplierList", supplierDetailsList);
			model.addObject("flag", 0);
		return model;
	}
	
	@RequestMapping(value = "/searchPo", method = RequestMethod.GET)
	@ResponseBody
	public List<PurchaseOrderHeader> searchPo(HttpServletRequest request, HttpServletResponse response) {
		 
		List<PurchaseOrderHeader> list = new ArrayList<PurchaseOrderHeader>();
		 
		 try
		 {
			 String search = request.getParameter("search");
			 System.out.println("String "+search);
			 
			 for(int i=0;i<getPurchaseOrderList.getPurchaseOrderHeaderList().size();i++)
			 {
				 String poNo=String.valueOf(getPurchaseOrderList.getPurchaseOrderHeaderList().get(i).getPoNo());
				 System.out.println(poNo);
				  
					 if(poNo.contains(search))
					 {
						 list.add(getPurchaseOrderList.getPurchaseOrderHeaderList().get(i)); 
					 }
					  
			 }
			 
			 System.out.println("list"+list);
		 }catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		return list;
	}
	
	@RequestMapping(value = "/dateWisePo", method = RequestMethod.GET)
	@ResponseBody
	public List<PurchaseOrderHeader> dateWisePo(HttpServletRequest request, HttpServletResponse response) {
		 
		List<PurchaseOrderHeader> list = new ArrayList<PurchaseOrderHeader>();
		GetPurchaseOrderList getPurchaseOrderList=null;
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,Object>();
		 try
		 {
			 String fromDate = request.getParameter("from_date");
			 String toDate = request.getParameter("to_date");
			 int status = Integer.parseInt(request.getParameter("all"));
			 String sts=null;
			 System.out.println("fromDate "+fromDate);
			 System.out.println("toDate "+toDate);
			 System.out.println("status "+status);
			 if(status==1)
			 {
				 sts="0,1,2,3,4,5,6,7"; 
			 }
			 else
			 {
				 sts="0"; 
			 }
			 	map.add("status", sts);
				map.add("fromDate",DateConvertor.convertToYMD(fromDate));
				map.add("toDate",DateConvertor.convertToYMD(toDate));
				RestTemplate rest=new RestTemplate();
				 getPurchaseOrderList=rest.postForObject(Constants.url + "purchaseOrder/dateWisePo",map, GetPurchaseOrderList.class);
					System.out.println("Purchase Order : "+getPurchaseOrderList.getPurchaseOrderHeaderList());
					for(int i=0;i<getPurchaseOrderList.getPurchaseOrderHeaderList().size();i++)
					{
						getPurchaseOrderList.getPurchaseOrderHeaderList().get(i).setPoDate(DateConvertor.convertToDMY(getPurchaseOrderList.getPurchaseOrderHeaderList().get(i).getPoDate()));
					}
			 
			 System.out.println("list"+list);
		 }catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		return getPurchaseOrderList.getPurchaseOrderHeaderList();
	}
	
	@RequestMapping(value = "/supplier", method = RequestMethod.GET)
	@ResponseBody
	public List<SupplierDetails> supplier(HttpServletRequest request, HttpServletResponse response) {
		 
		 
		 
		 try
		 {
			 
			 System.out.println("supplierDetailsList"+supplierDetailsList);
		 }catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		return supplierDetailsList;
	}
	
	@RequestMapping(value = "/deletePoRecord/{poId}", method = RequestMethod.GET)
	public String deletePoRecord(@PathVariable int poId) {

		
		RestTemplate rest=new RestTemplate();
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,Object>();
		map.add("poId", poId);
		
		Info info=rest.postForObject(Constants.url + "purchaseOrder/deletePoRecord",map, Info.class);
			System.out.println("Info : "+info);
			
			
			return "redirect:/showPurchaseOrder";
	}
	
	
	@RequestMapping(value = "/editPurchaseOrder/{poId}/{flag}", method = RequestMethod.GET)
	public ModelAndView editPurchaseOrder(@PathVariable int poId,@PathVariable int flag,HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/purchaseOrder/editPurchaseOrder");
		RestTemplate rest=new RestTemplate();
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,Object>();
		map.add("poId", poId);
		
		purchaseOrderHeaderedit=rest.postForObject(Constants.url + "purchaseOrder/getpurchaseorderHeaderWithDetailed",map, PurchaseOrderHeader.class);
			System.out.println("Purchase Order : "+purchaseOrderHeaderedit);
			
			rawMaterialTaxDetailsList= new RawMaterialTaxDetailsList();
			  rawMaterialTaxDetailsList=rest.getForObject(Constants.url + "rawMaterial/getAllRmTaxList", RawMaterialTaxDetailsList.class);
			System.out.println("RM Tax data : "+rawMaterialTaxDetailsList);
			
			List<RmItemGroup> rmItemGroupList=rest.getForObject(Constants.url + "rawMaterial/getAllRmItemGroup", List.class);
			
			purchaseOrderHeaderedit.setDelvDateRem(DateConvertor.convertToDMY(purchaseOrderHeaderedit.getDelvDateRem()));
			purchaseOrderHeaderedit.setQuotationRefDate(DateConvertor.convertToDMY(purchaseOrderHeaderedit.getQuotationRefDate()));
			 
			editPurchaseOrderDetailList=purchaseOrderHeaderedit.getPurchaseOrderDetail();
			model.addObject("purchaseOrderHeader",purchaseOrderHeaderedit);
			model.addObject("purchaseOrderDetailedList",editPurchaseOrderDetailList);
			model.addObject("supplierList", supplierDetailsList);
			model.addObject("supPaymentTerms", supPaymentTerms.getSupPaymentTermsList());
			model.addObject("transporterList", transporterList.getTransporterList());
			model.addObject("RawmaterialList",rmItemGroupList);
			model.addObject("flag",flag);
			
		return model;
	}
	static PurchaseOrderHeader purchaseOrderHeaderPdf;
	
	@RequestMapping(value = "/poHeaderWithDetailed/{poId}/{flag}", method = RequestMethod.GET)
	public ModelAndView poHeaderWithDetailed(@PathVariable int poId,@PathVariable int flag,HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/purchaseOrder/poHeaderWithDetailed");
		try
		{
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,Object>();
			map.add("poId", poId);
			System.out.println("flag"+flag);
			RestTemplate rest=new RestTemplate();
			PurchaseOrderHeader purchaseOrderHeader=rest.postForObject(Constants.url + "purchaseOrder/getpurchaseorderHeaderWithDetailed",map, PurchaseOrderHeader.class);
			 System.out.println("Response :"+purchaseOrderHeader.toString());
			 
			 supPaymentTerms=new SupPaymentTermsList();
			  supPaymentTerms = rest.getForObject(Constants.url + "/showPaymentTerms",
					SupPaymentTermsList.class);
			  System.out.println("supPaymentTerms"+supPaymentTerms.getSupPaymentTermsList());
			 
			 purchaseOrderHeader.setDelvDateRem(DateConvertor.convertToDMY(purchaseOrderHeader.getDelvDateRem()));
			 purchaseOrderHeader.setPoDate(DateConvertor.convertToDMY(purchaseOrderHeader.getPoDate()));
			 purchaseOrderHeader.setQuotationRefDate(DateConvertor.convertToDMY(purchaseOrderHeader.getQuotationRefDate()));
			 model.addObject("flag",flag);
			 model.addObject("purchaseOrderHeader",purchaseOrderHeader);
			 model.addObject("supplierList", supplierDetailsList);
			 model.addObject("supPaymentTerms", supPaymentTerms.getSupPaymentTermsList());
			 model.addObject("transporterList", transporterList.getTransporterList());
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		return model;
	}
	
	@RequestMapping(value = "/requestPOStoreToPurchase/{poId}", method = RequestMethod.GET)
	public String requestPOStoreToPurchase(@PathVariable int poId,HttpServletRequest request, HttpServletResponse response) {

		try
		{
			PurchaseOrderHeader purchaseOrderHeader = new PurchaseOrderHeader();
			System.out.println("poId"+poId);
			for(int i=0;i<=getPurchaseOrderList.getPurchaseOrderHeaderList().size();i++)
			{
				if(getPurchaseOrderList.getPurchaseOrderHeaderList().get(i).getPoId()==poId)
				{
					 purchaseOrderHeader = getPurchaseOrderList.getPurchaseOrderHeaderList().get(i);
					break;
				}
			}
			purchaseOrderHeader.setPoDate(DateConvertor.convertToYMD(purchaseOrderHeader.getPoDate()));
			purchaseOrderHeader.setDelvDateRem(DateConvertor.convertToDMY(purchaseOrderHeader.getDelvDateRem()));
			purchaseOrderHeader.setQuotationRefDate(DateConvertor.convertToDMY(purchaseOrderHeader.getQuotationRefDate()));
			purchaseOrderHeader.setPoStatus(1);
			List<PurchaseOrderDetail> purchaseOrderDetail = new ArrayList<PurchaseOrderDetail>();
			purchaseOrderHeader.setPurchaseOrderDetail(purchaseOrderDetail);
			System.out.println("purchaseOrderHeader "+purchaseOrderHeader);
			
			RestTemplate rest=new RestTemplate();
			 Info info=rest.postForObject(Constants.url + "purchaseOrder/insertPurchaseOrder",purchaseOrderHeader, Info.class);
			 System.out.println("Response :"+info.toString());
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		return "redirect:/showPurchaseOrder";
	}
	
	@RequestMapping(value = "/poListAtPurchase", method = RequestMethod.GET)
	public ModelAndView poListAtPurchase(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/purchaseOrder/polistAtPurchase");
		 Constants.mainAct =14;
		 Constants.subAct =86;
		RestTemplate rest=new RestTemplate();
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,Object>();
		map.add("status", "1,2,4");
		
		 getPurchaseOrderList = new GetPurchaseOrderList();
		 getPurchaseOrderList=rest.postForObject(Constants.url + "purchaseOrder/getpurchaseorderList",map, GetPurchaseOrderList.class);
			System.out.println("Purchase Order : "+getPurchaseOrderList.getPurchaseOrderHeaderList());
			
			supplierDetailsList=new ArrayList<SupplierDetails>();
			  supplierDetailsList=rest.getForObject(Constants.url + "getAllSupplier",   List.class);
			  
			  transporterList=new TransporterList();
				transporterList = rest.getForObject(Constants.url + "/showTransporters",TransporterList.class);
						System.out.println("Transporter List Response:" + transporterList.toString());
						for(int i=0;i<getPurchaseOrderList.getPurchaseOrderHeaderList().size();i++)
						{
							getPurchaseOrderList.getPurchaseOrderHeaderList().get(i).setPoDate(DateConvertor.convertToDMY(getPurchaseOrderList.getPurchaseOrderHeaderList().get(i).getPoDate()));
						}
			model.addObject("flag", 1);
			model.addObject("purchaseorderlist",getPurchaseOrderList.getPurchaseOrderHeaderList());
			model.addObject("supplierList", supplierDetailsList);
		return model;
	}
	
	
	
	@RequestMapping(value = "/requestPOPurachaseToDirectore/{poId}", method = RequestMethod.GET)
	public String requestPOPurachaseToDirectore(@PathVariable int poId,HttpServletRequest request, HttpServletResponse response) {

		try
		{
			PurchaseOrderHeader purchaseOrderHeader = new PurchaseOrderHeader();
			for(int i=0;i<=getPurchaseOrderList.getPurchaseOrderHeaderList().size();i++)
			{
				if(getPurchaseOrderList.getPurchaseOrderHeaderList().get(i).getPoId()==poId)
				{
					 purchaseOrderHeader = getPurchaseOrderList.getPurchaseOrderHeaderList().get(i);
					break;
				}
			}
			purchaseOrderHeader.setPoDate(DateConvertor.convertToYMD(purchaseOrderHeader.getPoDate()));
			purchaseOrderHeader.setDelvDateRem(DateConvertor.convertToDMY(purchaseOrderHeader.getDelvDateRem()));
			purchaseOrderHeader.setQuotationRefDate(DateConvertor.convertToDMY(purchaseOrderHeader.getQuotationRefDate()));
			purchaseOrderHeader.setPoStatus(2);
			List<PurchaseOrderDetail> purchaseOrderDetail = new ArrayList<PurchaseOrderDetail>();
			purchaseOrderHeader.setPurchaseOrderDetail(purchaseOrderDetail);
			System.out.println("purchaseOrderHeader "+purchaseOrderHeader);
			
			RestTemplate rest=new RestTemplate();
			 Info info=rest.postForObject(Constants.url + "purchaseOrder/insertPurchaseOrder",purchaseOrderHeader, Info.class);
			 System.out.println("Response :"+info.toString());
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		return "redirect:/poListAtPurchase";
	}
	
	@RequestMapping(value = "/rejectPOPurachaseToStore/{poId}", method = RequestMethod.GET)
	public String rejectPOPurachaseToStore(@PathVariable int poId,HttpServletRequest request, HttpServletResponse response) {

		try
		{
			PurchaseOrderHeader purchaseOrderHeader = new PurchaseOrderHeader();
			for(int i=0;i<=getPurchaseOrderList.getPurchaseOrderHeaderList().size();i++)
			{
				if(getPurchaseOrderList.getPurchaseOrderHeaderList().get(i).getPoId()==poId)
				{
					 purchaseOrderHeader = getPurchaseOrderList.getPurchaseOrderHeaderList().get(i);
					break;
				}
			}
			purchaseOrderHeader.setPoDate(DateConvertor.convertToYMD(purchaseOrderHeader.getPoDate()));
			purchaseOrderHeader.setDelvDateRem(DateConvertor.convertToDMY(purchaseOrderHeader.getDelvDateRem()));
			purchaseOrderHeader.setQuotationRefDate(DateConvertor.convertToDMY(purchaseOrderHeader.getQuotationRefDate()));
			purchaseOrderHeader.setPoStatus(3);
			List<PurchaseOrderDetail> purchaseOrderDetail = new ArrayList<PurchaseOrderDetail>();
			purchaseOrderHeader.setPurchaseOrderDetail(purchaseOrderDetail);
			System.out.println("purchaseOrderHeader "+purchaseOrderHeader);
			
			RestTemplate rest=new RestTemplate();
			 Info info=rest.postForObject(Constants.url + "purchaseOrder/insertPurchaseOrder",purchaseOrderHeader, Info.class);
			 System.out.println("Response :"+info.toString());
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		return "redirect:/poListAtPurchase";
	}
	
	
	@RequestMapping(value = "/poListAtDirector", method = RequestMethod.GET)
	public ModelAndView poListAtDirector(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/purchaseOrder/poListAtDirectore");
		Constants.mainAct =21;
		Constants.subAct =103;

		RestTemplate rest=new RestTemplate();
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,Object>();
		map.add("status", "2");
		
		 getPurchaseOrderList = new GetPurchaseOrderList();
		 getPurchaseOrderList=rest.postForObject(Constants.url + "purchaseOrder/getpurchaseorderList",map, GetPurchaseOrderList.class);
			System.out.println("Purchase Order : "+getPurchaseOrderList.getPurchaseOrderHeaderList());
			
			supplierDetailsList=new ArrayList<SupplierDetails>();
			  supplierDetailsList=rest.getForObject(Constants.url + "getAllSupplier",   List.class);
			  
			  transporterList=new TransporterList();
				transporterList = rest.getForObject(Constants.url + "/showTransporters",TransporterList.class);
						System.out.println("Transporter List Response:" + transporterList.toString());
						for(int i=0;i<getPurchaseOrderList.getPurchaseOrderHeaderList().size();i++)
						{
							getPurchaseOrderList.getPurchaseOrderHeaderList().get(i).setPoDate(DateConvertor.convertToDMY(getPurchaseOrderList.getPurchaseOrderHeaderList().get(i).getPoDate()));
						}
			 model.addObject("flag", 2);
			model.addObject("purchaseorderlist",getPurchaseOrderList.getPurchaseOrderHeaderList());
			model.addObject("supplierList", supplierDetailsList);
		return model;
	}
	
	@RequestMapping(value = "pdf/poPdf/{poId}", method = RequestMethod.GET)
	public ModelAndView monthWisePdf(@PathVariable String poId,HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/materialRecieptReport/pdf/poPdf");
		try {
			System.out.println("poId"+poId);
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,Object>();
			map.add("poId", poId); 
			RestTemplate rest=new RestTemplate();
			GetPoHeaderForPdf getPoHeaderForPdf=rest.postForObject(Constants.url + "purchaseOrder/poDetailedForPdf",map, GetPoHeaderForPdf.class);
			 System.out.println("Response :"+getPoHeaderForPdf.toString());
			
			 model.addObject("purchaseOrderHeaderPdf", getPoHeaderForPdf);
			System.out.println("purchaseOrderHeaderPdf" + getPoHeaderForPdf);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;

	}
	
	
	@RequestMapping(value = "/requestPOFinalByDirectore", method = RequestMethod.POST)
	public String requestPOFinalByDirectore(final @RequestParam CommonsMultipartFile attachFile,HttpServletRequest request, HttpServletResponse response) {

		try
		{
			int poId = Integer.parseInt(request.getParameter("poId"));
			System.out.println("poId "+ poId);
			PurchaseOrderHeader purchaseOrderHeader = new PurchaseOrderHeader();
			for(int i=0;i<=getPurchaseOrderList.getPurchaseOrderHeaderList().size();i++)
			{
				if(getPurchaseOrderList.getPurchaseOrderHeaderList().get(i).getPoId()==poId)
				{
					 purchaseOrderHeader = getPurchaseOrderList.getPurchaseOrderHeaderList().get(i);
					break;
				}
			}
			purchaseOrderHeader.setPoDate(DateConvertor.convertToYMD(purchaseOrderHeader.getPoDate()));
			purchaseOrderHeader.setDelvDateRem(DateConvertor.convertToDMY(purchaseOrderHeader.getDelvDateRem()));
			purchaseOrderHeader.setQuotationRefDate(DateConvertor.convertToDMY(purchaseOrderHeader.getQuotationRefDate()));
			purchaseOrderHeader.setPoStatus(5);
			System.out.println("purchaseOrderHeader "+purchaseOrderHeader);
			List<PurchaseOrderDetail> purchaseOrderDetail = new ArrayList<PurchaseOrderDetail>();
			purchaseOrderHeader.setPurchaseOrderDetail(purchaseOrderDetail);
			
			RestTemplate rest=new RestTemplate();
			
			Info info=rest.postForObject(Constants.url + "purchaseOrder/insertPurchaseOrder",purchaseOrderHeader, Info.class);
			 System.out.println("Response :"+info.toString());
			
			 if(info.isError()==false)
			 {
				 String phonno = null;
				 String email = null;
				 String email2 = null; 
				 Supplist supplierDetailsList = rest.getForObject(Constants.url + "/getAllSupplierlist", Supplist.class);
				 for(int i=0;i<supplierDetailsList.getSupplierDetailslist().size();i++)
				 {
					 if(supplierDetailsList.getSupplierDetailslist().get(i).getSuppId()==purchaseOrderHeader.getSuppId())
					 {
						 phonno=supplierDetailsList.getSupplierDetailslist().get(i).getSuppMob1();
						 email= supplierDetailsList.getSupplierDetailslist().get(i).getSuppEmail1(); 
						 email2= supplierDetailsList.getSupplierDetailslist().get(i).getSuppEmail2();
						 break;
					 }
				 }
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				 
				 map.add("authkey", "140742AbB1cy8zZt589c06d5");
				 map.add("mobiles", phonno);
				 map.add("message", "PO Approved");
				 map.add("sender", "RCONNT");
				 map.add("route", "4");
				 map.add("country", "91");
				 map.add("response", "json");
				String String=rest.postForObject("http://control.bestsms.co.in/api/sendhttp.php",map, String.class);
				final String[] e_mail={email,email2};
				System.out.println("email"+email);
				System.out.println("email2"+email2);
				System.out.println("e_mail"+e_mail);
				System.out.println("phonno"+phonno);
				mailSender.send(new MimeMessagePreparator() {

					@Override
					public void prepare(MimeMessage mimeMessage) throws Exception {
						MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
						messageHelper.setTo(e_mail);
						messageHelper.setSubject("Email Testing");
						messageHelper.setText("Nana Po Approved");
						
						String attachName = attachFile.getOriginalFilename();
						if ( attachFile.getSize()>0) {
							System.out.println("Attaching file to mail");
							messageHelper.addAttachment(attachName, new InputStreamSource() {

								@Override
								public InputStream getInputStream() throws IOException {
									return attachFile.getInputStream();
								}
							});
						}

					}

				});
				 
			 }

			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		return "redirect:/poListAtDirector";
	}
	
	@RequestMapping(value = "/rejectPODirectoreToPurchase/{poId}", method = RequestMethod.GET)
	public String rejectPODirectoreToPurchase(@PathVariable int poId,HttpServletRequest request, HttpServletResponse response) {

		try
		{
			PurchaseOrderHeader purchaseOrderHeader = new PurchaseOrderHeader();
			for(int i=0;i<=getPurchaseOrderList.getPurchaseOrderHeaderList().size();i++)
			{
				if(getPurchaseOrderList.getPurchaseOrderHeaderList().get(i).getPoId()==poId)
				{
					 purchaseOrderHeader = getPurchaseOrderList.getPurchaseOrderHeaderList().get(i);
					break;
				}
			}
			purchaseOrderHeader.setPoDate(DateConvertor.convertToYMD(purchaseOrderHeader.getPoDate()));
			purchaseOrderHeader.setDelvDateRem(DateConvertor.convertToDMY(purchaseOrderHeader.getDelvDateRem()));
			purchaseOrderHeader.setQuotationRefDate(DateConvertor.convertToDMY(purchaseOrderHeader.getQuotationRefDate()));
			purchaseOrderHeader.setPoStatus(4);
			List<PurchaseOrderDetail> purchaseOrderDetail = new ArrayList<PurchaseOrderDetail>();
			purchaseOrderHeader.setPurchaseOrderDetail(purchaseOrderDetail);
			System.out.println("purchaseOrderHeader "+purchaseOrderHeader);
			
			RestTemplate rest=new RestTemplate();
			 Info info=rest.postForObject(Constants.url + "purchaseOrder/insertPurchaseOrder",purchaseOrderHeader, Info.class);
			 System.out.println("Response :"+info.toString());
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		return "redirect:/poListAtDirector";
	}
	
	
	
	@RequestMapping(value = "/getRmRateAndTax", method = RequestMethod.GET)
	public @ResponseBody int getRmRateAndTax(HttpServletRequest request,
		HttpServletResponse response) {
		
		
		String suppId = request.getParameter("supp_id");
		String poType = request.getParameter("po_type");
		String poNo = request.getParameter("po_no");
		String poDate = request.getParameter("po_date");
		String rm_id = request.getParameter("rm_id");
		String disc_per = request.getParameter("disc_per");
		String rmQty = request.getParameter("rm_qty");
		int taxation = Integer.parseInt(request.getParameter("taxation"));
		
		float discPer=Float.parseFloat(disc_per);
		int rmId=Integer.parseInt(rm_id);
		System.out.println("Rm Id : "+rmId);
		
		int poQty=Integer.parseInt(rmQty);
		
		int res=0;
		RestTemplate rest=new RestTemplate();
		MultiValueMap<String, Object> map=new LinkedMultiValueMap<String, Object>();
		
		map.add("rmId", rmId);
		map.add("suppId", suppId);
		try {
		  getRmRateAndTax=rest.postForObject(Constants.url +"purchaseOrder/getRmDetailByRmId", map, GetRmRateAndTax.class);
		if(getRmRateAndTax!=null)
		{
			
				getRmRateAndTax.toString();
				res=1;
			
			 
		}
		}catch (Exception e) {
			 
		}
		return res;
	}
	
	//---------------------------------------Maintain item List ------------------------
		@RequestMapping(value = "/addItemToList", method = RequestMethod.GET)
		public @ResponseBody List<PurchaseOrderDetail> addItemToList(HttpServletRequest request,
			HttpServletResponse response) {
			
			 
			 
			
//			String taxation = request.getParameter("taxation");
//			String kindAttn = request.getParameter("kind_attn");
//
//			String delvDate = request.getParameter("delv_date");
//			String delvAt = request.getParameter("delv_at");
//			String quotationRef_no = request.getParameter("quotation_ref_no");
			
			String suppId = request.getParameter("supp_id");
			String poType = request.getParameter("po_type");
			String poNo = request.getParameter("po_no");
			String poDate = request.getParameter("po_date");
			String rm_id = request.getParameter("rm_id");
			String disc_per = request.getParameter("disc_per");
			String rmQty = request.getParameter("rm_qty");
			int taxation = Integer.parseInt(request.getParameter("taxation"));
			
			float discPer=Float.parseFloat(disc_per);
			int rmId=Integer.parseInt(rm_id);
			System.out.println("Rm Id : "+rmId);
			
			int poQty=Integer.parseInt(rmQty);
			
			PurchaseOrderDetail purchaseOrderDetail=new PurchaseOrderDetail();
			
			  
		 
			
							purchaseOrderDetail.setCgstPer(getRmRateAndTax.getCgstPer());
							purchaseOrderDetail.setSgstPer(getRmRateAndTax.getSgstPer());
							purchaseOrderDetail.setIgstPer(getRmRateAndTax.getIgstPer());
							 purchaseOrderDetail.setGstPer(getRmRateAndTax.getGstPer());
						 
				 
					 
				 
					purchaseOrderDetail.setRmId(rmId);
				 
					purchaseOrderDetail.setDelStatus(0);
					purchaseOrderDetail.setDiscPer(discPer);  //Discount Hard Coded
					purchaseOrderDetail.setPoDate(poDate);
				 
					purchaseOrderDetail.setPoNo(Integer.parseInt(poNo));
					purchaseOrderDetail.setPoQty(poQty);
				 
					
					if(taxation==1) {
						 
					purchaseOrderDetail.setPoRate(getRmRateAndTax.getRateTaxIncl());
					float poTaxable=poQty*(getRmRateAndTax.getRateTaxIncl());
					purchaseOrderDetail.setPoTaxable(poTaxable);//-Discount per %
					float poTotal=(poTaxable*getRmRateAndTax.getGstPer())/100;
					purchaseOrderDetail.setPoTotal(poTotal);
					}
					else if(taxation==2){
						purchaseOrderDetail.setPoRate(getRmRateAndTax.getRateTaxExtra());
						float poTaxable=poQty*(getRmRateAndTax.getRateTaxExtra());
						purchaseOrderDetail.setPoTaxable(poTaxable);//-Discount per %
						float poTotal=(poTaxable*getRmRateAndTax.getGstPer())/100;
						purchaseOrderDetail.setPoTotal(poTotal);
						}
					 
					
					 
					purchaseOrderDetail.setPoType(Integer.parseInt(poType));
					purchaseOrderDetail.setRmId(rmId);
					purchaseOrderDetail.setRmName(getRmRateAndTax.getRmName());
					purchaseOrderDetail.setRmRemark("Remark ");//Remark Hard Coded
				 
					purchaseOrderDetail.setRmUomId(getRmRateAndTax.getRmUomId());
					purchaseOrderDetail.setSpecification(getRmRateAndTax.getSpecification());
					purchaseOrderDetail.setSuppId(Integer.parseInt(suppId));
					purchaseOrderDetail.setSchDays(0);
					
					System.out.println("Data "+purchaseOrderDetail.toString());
					purchaseOrderDetailList.add(purchaseOrderDetail);
		 
					System.out.println("DataList "+purchaseOrderDetailList.toString());
					  
			System.out.println("Item Lisst :"+purchaseOrderDetailList);
			return purchaseOrderDetailList;
			
		}
		
		
		//---------------------------------------Maintain item List ------------------------
				@RequestMapping(value = "/updateRmQty", method = RequestMethod.GET)
				public @ResponseBody List<PurchaseOrderDetail> updateRmQty(HttpServletRequest request,
					HttpServletResponse response) {
					
					int updateQty=Integer.parseInt(request.getParameter("updateQty"));
					int index=Integer.parseInt(request.getParameter("index"));
					
					for(int i=0;i<purchaseOrderDetailList.size();i++)
					{
						if(i==index)
						{
						purchaseOrderDetailList.get(i).setPoQty(updateQty);
						float rate=purchaseOrderDetailList.get(i).getPoRate();
						purchaseOrderDetailList.get(i).setPoTaxable(updateQty*rate);
						}
					}
						return null;
					
				}
				
				@RequestMapping(value = "/delItem", method = RequestMethod.GET)
				public @ResponseBody List<PurchaseOrderDetail> delItem(HttpServletRequest request,
					HttpServletResponse response) {
					
					int index=Integer.parseInt(request.getParameter("index"));
					
					for(int i=0;i<purchaseOrderDetailList.size();i++)
					{
						if(i==index)
						{
							if(purchaseOrderDetailList.get(i).getPoDetailId()!=0)
							{
								purchaseOrderDetailList.get(i).setDelStatus(1);
							}
							else
							{
								purchaseOrderDetailList.remove(i);
							}
							
						}
					}
					
					System.out.println("delete"+purchaseOrderDetailList.toString());
						return purchaseOrderDetailList;
					
				}
				
				
					
		
	
	@RequestMapping(value = "/submitPurchaseOrder", method = RequestMethod.POST)
	public String submitPurchaseOrder(HttpServletRequest request, HttpServletResponse response) {
		
		try
		{
			int taxationRem = Integer.parseInt(request.getParameter("taxation"));
			

			String delvDateRem = request.getParameter("delv_date");
			String delvAtRem = request.getParameter("delv_at");
		//	String rm_id = request.getParameter("rm_id");
			
			
			String kindAttn = request.getParameter("kind_attn");
			int poNo =Integer.parseInt(request.getParameter("po_no"));
			int poType = Integer.parseInt(request.getParameter("po_type"));
			int quotationRefNo = Integer.parseInt(request.getParameter("quotation_ref_no"));
			int suppId = Integer.parseInt(request.getParameter("supp_id"));
			String poDate = request.getParameter("po_date"); 
			int insuRem=Integer.parseInt(request.getParameter("insurance"));
			int freidhtRem=Integer.parseInt(request.getParameter("freight"));
			 
			int tranId=Integer.parseInt(request.getParameter("transportation"));
			String spRem= request.getParameter("sp_instruction");
			int validity=Integer.parseInt(request.getParameter("po_validity"));
			int payId=Integer.parseInt(request.getParameter("pay_terms"));
			int poStatus=0;
		
			int approvedId=0;
			int delStatusId=0;
			String quotationRefDate =request.getParameter("quotation_date");;
			int userId=0;
			
			PurchaseOrderHeader purchaseOrderHeader=new PurchaseOrderHeader();
			
			purchaseOrderHeader.setApprovedId(approvedId);
			purchaseOrderHeader.setDelStatusId(delStatusId);
			purchaseOrderHeader.setDelvAtRem(delvAtRem);
			purchaseOrderHeader.setDelvDateRem(delvDateRem);
			purchaseOrderHeader.setFreidhtRem(freidhtRem);
			purchaseOrderHeader.setInsuRem(insuRem);
			purchaseOrderHeader.setKindAttn(kindAttn);
			purchaseOrderHeader.setPayId(payId);
			purchaseOrderHeader.setPoDate(poDate);
			purchaseOrderHeader.setPoNo(poNo);
			purchaseOrderHeader.setPoStatus(poStatus);
			//  float totalValue=0;
			for(int i=0;i<purchaseOrderDetailList.size();i++)
			{
				
				//totalValue+=purchaseOrderDetailList.get(i).getPoTotal();
				purchaseOrderHeader.setPoTotalValue(purchaseOrderHeader.getPoTotalValue()+purchaseOrderDetailList.get(i).getPoTaxable());
			}
			
			
			purchaseOrderHeader.setPoType(poType);
			purchaseOrderHeader.setPurchaseOrderDetail(purchaseOrderDetailList);
			purchaseOrderHeader.setQuotationRefDate(quotationRefDate);
			purchaseOrderHeader.setQuotationRefNo(quotationRefNo);
			purchaseOrderHeader.setSpRem(spRem);
			purchaseOrderHeader.setSuppId(suppId);
			purchaseOrderHeader.setValidity(validity);
			purchaseOrderHeader.setUserId(userId);
			purchaseOrderHeader.setTranId(tranId);
			purchaseOrderHeader.setTaxationRem(taxationRem);
			 
			System.out.println("List : "+purchaseOrderHeader.toString());
			RestTemplate rest=new RestTemplate();
			 Info info=rest.postForObject(Constants.url + "purchaseOrder/insertPurchaseOrder",purchaseOrderHeader, Info.class);
			 System.out.println("Response :"+info.toString());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	 	
		
		
		return "redirect:/showPurchaseOrder";
	}

	
	@RequestMapping(value = "/addItemToListInOldItemList", method = RequestMethod.GET)
	public @ResponseBody List<PurchaseOrderDetail> addItemToListInOldItemList(HttpServletRequest request,
		HttpServletResponse response) {
		
		String suppId = request.getParameter("supp_id");
		String poType = request.getParameter("po_type");
		String poNo = request.getParameter("po_no");
		String poDate = request.getParameter("po_date");
		String rm_id = request.getParameter("rm_id");
		String disc_per = request.getParameter("disc_per");
		String rmQty = request.getParameter("rm_qty");
		int taxation = Integer.parseInt(request.getParameter("taxation"));
		
		float discPer=Float.parseFloat(disc_per);
		int rmId=Integer.parseInt(rm_id);
		System.out.println("Rm Id : "+rmId);
		
		int poQty=Integer.parseInt(rmQty);
		
		try
		{
			PurchaseOrderDetail purchaseOrderDetail=new PurchaseOrderDetail();
			
			  
			 
			
			purchaseOrderDetail.setCgstPer(getRmRateAndTax.getCgstPer());
			purchaseOrderDetail.setSgstPer(getRmRateAndTax.getSgstPer());
			purchaseOrderDetail.setIgstPer(getRmRateAndTax.getIgstPer());
			 purchaseOrderDetail.setGstPer(getRmRateAndTax.getGstPer());
		 
 
	 
 
					purchaseOrderDetail.setRmId(rmId);
				 
					purchaseOrderDetail.setDelStatus(0);
					purchaseOrderDetail.setDiscPer(discPer);  //Discount Hard Coded
					purchaseOrderDetail.setPoDate(poDate);
				 
					purchaseOrderDetail.setPoNo(Integer.parseInt(poNo));
					purchaseOrderDetail.setPoQty(poQty);
				 
					
					if(taxation==1) {
						 
					purchaseOrderDetail.setPoRate(getRmRateAndTax.getRateTaxIncl());
					float poTaxable=poQty*(getRmRateAndTax.getRateTaxIncl());
					purchaseOrderDetail.setPoTaxable(poTaxable);//-Discount per %
					float poTotal=(poTaxable*getRmRateAndTax.getGstPer())/100;
					purchaseOrderDetail.setPoTotal(poTotal);
					}
					else if(taxation==2){
						purchaseOrderDetail.setPoRate(getRmRateAndTax.getRateTaxExtra());
						float poTaxable=poQty*(getRmRateAndTax.getRateTaxExtra());
						purchaseOrderDetail.setPoTaxable(poTaxable);//-Discount per %
						float poTotal=(poTaxable*getRmRateAndTax.getGstPer())/100;
						purchaseOrderDetail.setPoTotal(poTotal);
						}
					 
					
					 
					purchaseOrderDetail.setPoType(Integer.parseInt(poType));
					purchaseOrderDetail.setRmId(rmId);
					purchaseOrderDetail.setRmName(getRmRateAndTax.getRmName());
					purchaseOrderDetail.setRmRemark("Remark ");//Remark Hard Coded
				 
					purchaseOrderDetail.setRmUomId(getRmRateAndTax.getRmUomId());
					purchaseOrderDetail.setSpecification(getRmRateAndTax.getSpecification());
					purchaseOrderDetail.setSuppId(Integer.parseInt(suppId));
					purchaseOrderDetail.setSchDays(0);
					
					System.out.println("Data "+purchaseOrderDetail.toString());
					editPurchaseOrderDetailList.add(purchaseOrderDetail);
				
					System.out.println("DataList "+editPurchaseOrderDetailList.toString());
					  
				System.out.println("Item Lisst :"+editPurchaseOrderDetailList);
				
				
				
				
				System.out.println("Item Lisst :"+editPurchaseOrderDetailList);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		 
		
		
		
		return editPurchaseOrderDetailList;
		
	}
	
	@RequestMapping(value = "/updateRmQtyInEdit", method = RequestMethod.GET)
	public @ResponseBody List<PurchaseOrderDetail> updateRmQtyInEdit(HttpServletRequest request,
		HttpServletResponse response) {
		
		int updateQty=Integer.parseInt(request.getParameter("updateQty"));
		int index=Integer.parseInt(request.getParameter("index"));
		
		for(int i=0;i<editPurchaseOrderDetailList.size();i++)
		{
			if(i==index)
			{
				editPurchaseOrderDetailList.get(i).setPoQty(updateQty);
			float rate=editPurchaseOrderDetailList.get(i).getPoRate();
			editPurchaseOrderDetailList.get(i).setPoTaxable(updateQty*rate);
			}
		}
		
		System.out.println("editPurchaseOrderDetailList"+editPurchaseOrderDetailList.toString());
			return null;
		
	}
	
	@RequestMapping(value = "/deleteItem", method = RequestMethod.GET)
	public @ResponseBody List<PurchaseOrderDetail> deleteItem(HttpServletRequest request,
		HttpServletResponse response) {
		
		int index=Integer.parseInt(request.getParameter("index"));
		
		for(int i=0;i<editPurchaseOrderDetailList.size();i++)
		{
			if(i==index)
			{
				if(editPurchaseOrderDetailList.get(i).getPoDetailId()!=0)
				{
					editPurchaseOrderDetailList.get(i).setDelStatus(1);
				}
				else
				{
					editPurchaseOrderDetailList.remove(i);
				}
				
			}
		}
		
		System.out.println("delete"+editPurchaseOrderDetailList.toString());
			return editPurchaseOrderDetailList;
		
	}
	
	@RequestMapping(value = "/submitEditPurchaseOrder", method = RequestMethod.POST)
	public String submitEditPurchaseOrder(HttpServletRequest request, HttpServletResponse response) {
		String ret=null;
		int flag = Integer.parseInt(request.getParameter("flag"));
		try
		{
			int taxationRem = Integer.parseInt(request.getParameter("taxation"));
			

			String delvDateRem = request.getParameter("delv_date");
			System.out.println("Comming Date"+delvDateRem);
			String delvAtRem = request.getParameter("delv_at");
			String kindAttn = request.getParameter("kind_attn");
			int poNo =Integer.parseInt(request.getParameter("po_no"));
			int poType = Integer.parseInt(request.getParameter("po_type"));
			int quotationRefNo = Integer.parseInt(request.getParameter("quotation_ref_no"));
			int suppId = Integer.parseInt(request.getParameter("supp_id"));
			String poDate = request.getParameter("po_date");
			int poStatus=Integer.parseInt(request.getParameter("status"));
			int validity=Integer.parseInt(request.getParameter("po_validity"));
			int payId=Integer.parseInt(request.getParameter("pay_terms"));
			
			int insuRem=Integer.parseInt(request.getParameter("insurance"));
			int freidhtRem=Integer.parseInt(request.getParameter("freight"));
			 
			int tranId=Integer.parseInt(request.getParameter("transportation"));
			String spRem= request.getParameter("sp_instruction");
			 
			
		
			int approvedId=0;
			int delStatusId=0;
			String quotationRefDate =request.getParameter("quotation_date");;
			int userId=0;
			
			PurchaseOrderHeader purchaseOrderHeader=purchaseOrderHeaderedit;
			
			purchaseOrderHeader.setApprovedId(approvedId);
			purchaseOrderHeader.setDelStatusId(delStatusId);
			purchaseOrderHeader.setDelvAtRem(delvAtRem);
			purchaseOrderHeader.setDelvDateRem(delvDateRem);
			purchaseOrderHeader.setFreidhtRem(freidhtRem);
			purchaseOrderHeader.setInsuRem(insuRem);
			purchaseOrderHeader.setKindAttn(kindAttn);
			purchaseOrderHeader.setPayId(payId);
			purchaseOrderHeader.setPoDate(poDate);
			purchaseOrderHeader.setPoNo(poNo);
			purchaseOrderHeader.setPoStatus(poStatus);
			 float totalValue=0;
			for(int i=0;i<editPurchaseOrderDetailList.size();i++)
			{
				if(editPurchaseOrderDetailList.get(i).getDelStatus()!=1)
				{
					totalValue=totalValue+editPurchaseOrderDetailList.get(i).getPoTaxable();
				}
				
			}
			
			purchaseOrderHeader.setPoTotalValue(totalValue);
			purchaseOrderHeader.setPoType(poType);
			purchaseOrderHeader.setPurchaseOrderDetail(editPurchaseOrderDetailList);
			purchaseOrderHeader.setQuotationRefDate(quotationRefDate);
			purchaseOrderHeader.setQuotationRefNo(quotationRefNo);
			purchaseOrderHeader.setSpRem(spRem);
			purchaseOrderHeader.setSuppId(suppId);
			purchaseOrderHeader.setValidity(validity);
			purchaseOrderHeader.setUserId(userId);
			purchaseOrderHeader.setTranId(tranId);
			purchaseOrderHeader.setTaxationRem(taxationRem);
			 
			System.out.println("List : "+purchaseOrderHeader.toString());
			RestTemplate rest=new RestTemplate();
			 Info info=rest.postForObject(Constants.url + "purchaseOrder/insertPurchaseOrder",purchaseOrderHeader, Info.class);
			 System.out.println("Response :"+info.toString());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	 	
		if(flag==0)
		{
			ret="redirect:/showPurchaseOrder";
		}
		else if(flag==1)
		{
			ret="redirect:/poListAtPurchase";
		}
		else if(flag==2)
		{
			ret="redirect:/poListAtDirector";
		}
		 
		return ret;
	}
	
	public Supplist supplier = new Supplist();
	GetRawMaterialDetailList rawMaterialList = new GetRawMaterialDetailList();
	RmRateVerification rmRateVerification=new RmRateVerification();
	
	@RequestMapping(value = "/showRmRateVerificationList", method = RequestMethod.GET)
	public ModelAndView showRmRateVerification(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView model = new ModelAndView("masters/rawMaterial/rmRateVerificationList");
		Constants.mainAct=14;
		Constants.subAct=85;
			RestTemplate rest=new RestTemplate();
			try {
				rawMaterialList =rest.getForObject(Constants.url +"rawMaterial/getAllRawMaterialList", GetRawMaterialDetailList.class);
				System.out.println("RM Details : "+rawMaterialList.getRawMaterialDetailsList().toString()); 
		
			supplier = rest.getForObject(Constants.url + "/getAllSupplierlist", Supplist.class);
			System.out.println("supplier"+rawMaterialList.getRawMaterialDetailsList().toString()); 

			model.addObject("supplierList", supplier.getSupplierDetailslist());
			model.addObject("RawmaterialList", rawMaterialList.getRawMaterialDetailsList());
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}

		return model;
	}
	
	@RequestMapping(value = "/getRmRateVerificationList", method = RequestMethod.GET)
	@ResponseBody
	public List<RmRateVerificationList> getRmRateVerificationList(HttpServletRequest request, HttpServletResponse response) {

		List<RmRateVerificationList> rmRateVerificationList = new ArrayList<RmRateVerificationList>();
			RestTemplate rest=new RestTemplate();
			try {
				String supId = request.getParameter("supp_id");
				String rmId = request.getParameter("rm_id");
				
				String supIdList = new String();
				String rmIdList = new String();
				
				if(supId.equals("0"))
				{
					 for(int i=0;i<supplier.getSupplierDetailslist().size();i++)
					 {
						 supIdList = supIdList.concat(String.valueOf(supplier.getSupplierDetailslist().get(i).getSuppId()))+',';
						 
					 }
					
				}
				else
				{
					supIdList=supId;
				}
				
				if(rmId.equals("0"))
				{
					 for(int i=0;i<rawMaterialList.getRawMaterialDetailsList().size();i++)
					 {
						 rmIdList = rmIdList.concat(String.valueOf(rawMaterialList.getRawMaterialDetailsList().get(i).getRmId()))+','; 
					 }
					
				}
				else
				{
					rmIdList=rmId; 
				}
				
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("suppId", supIdList);
				map.add("rmId", rmIdList);
				System.out.println("map"+map);
				 rmRateVerificationList=rest.postForObject(Constants.url +"rawMaterial/getRmRateVerificationList",map, List.class);
		
		System.out.println("rmRateVerificationList : "+rmRateVerificationList);
		   }
			catch(Exception e)
			{
				 e.printStackTrace();
			}

		return rmRateVerificationList;
	}
	
	@RequestMapping(value = "/showRmRateVerificationDetailed/{suppId}/{rmId}", method = RequestMethod.GET)
	public ModelAndView showRmRateVerificationDetailed(@PathVariable int suppId,@PathVariable int rmId,HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/rawMaterial/rmRateVerificationDetailed");
		 
			RestTemplate rest=new RestTemplate();
			try {
				
				 System.out.println("suppId"+suppId);
				 System.out.println("rmId"+rmId);
				MultiValueMap<String, Object> map=new LinkedMultiValueMap<String, Object>(); 
				map.add("suppId", suppId);
				map.add("rmId", rmId);
				
				rmRateVerification=new RmRateVerification();
					rmRateVerification = rest.postForObject(Constants.url + "rawMaterial/getRmRateVerification",map,
							RmRateVerification.class);
				 
			 
			model.addObject("rmRateVerification", rmRateVerification);
			model.addObject("supplierList", supplier.getSupplierDetailslist());
			model.addObject("RawmaterialList", rawMaterialList.getRawMaterialDetailsList());
			}
			catch(Exception e)
			{
				System.out.println("Exception In /showRmRateVerification"+e.getMessage());
			}

		return model;
	}
	
	@RequestMapping(value = "/submitRmRateVerificationDetailed", method = RequestMethod.POST)
	public String submitRmRateVerificationDetailed(HttpServletRequest request, HttpServletResponse response) {

		 try
		 {
			 String currRateDate=request.getParameter("curr_rate_date");
				System.out.println(" curr_rate_date      : "+currRateDate);
				String currRateTaxExtra=request.getParameter("curr_rate_tax_extra");
				System.out.println(" currRateTaxExtra      : "+currRateTaxExtra);
				String currRateTaxIncl=request.getParameter("curr_rate_tax_incl");
				System.out.println(" currRateTaxIncl : "+currRateTaxIncl);
				
				 
				 if(currRateDate==null)
				 {
					 rmRateVerification.setRateTaxIncl(Float.valueOf(currRateTaxIncl));
						rmRateVerification.setRateTaxExtra(Float.valueOf(currRateTaxExtra));
				 }
				 else
				 {
					 	rmRateVerification.setRateTaxIncl2(rmRateVerification.getRateTaxIncl1());
						rmRateVerification.setRateTaxExtra2(rmRateVerification.getRateTaxExtra1());
						rmRateVerification.setDate2(rmRateVerification.getDate1());
						
						rmRateVerification.setRateTaxIncl1(rmRateVerification.getRateTaxIncl());
						rmRateVerification.setRateTaxExtra1(rmRateVerification.getRateTaxExtra());
						rmRateVerification.setDate1(rmRateVerification.getRateDate());
						
						rmRateVerification.setRateTaxIncl(Float.valueOf(currRateTaxIncl));
						rmRateVerification.setRateTaxExtra(Float.valueOf(currRateTaxExtra));
						rmRateVerification.setRateDate(currRateDate);
				 }
				
				
				RestTemplate rest=new RestTemplate();
				  Info info=rest.postForObject(Constants.url + "rawMaterial/insertRmRateVerification",rmRateVerification, Info.class);

				  System.out.println("response : "+ info.toString());
		 }catch(Exception e)
		 {
			 e.printStackTrace();
		 }

		
		 
		return "redirect:/showRmRateVerificationDetailed"+"/"+rmRateVerification.getSuppId()+"/"+rmRateVerification.getRmId();
	}
	
	private Dimension format = PD4Constants.A4;
	private boolean landscapeValue = false;
	private int topValue = 8;
	private int leftValue = 0;
	private int rightValue = 0;
	private int bottomValue = 8;
	private String unitsValue = "m";
	private String proxyHost = "";
	private int proxyPort = 0;

	private int userSpaceWidth = 750;
	private static int BUFFER_SIZE = 1024;

	@RequestMapping(value = "/purchase", method = RequestMethod.GET)
	public void showPDF(HttpServletRequest request, HttpServletResponse response) {

		String url = request.getParameter("url");
		System.out.println("URL " + url);
		// http://monginis.ap-south-1.elasticbeanstalk.com
		File f = new File("/opt/tomcat-latest/webapps/uploads/Po.pdf");
		//File f = new File("C:/pdf/ordermemo221.pdf");
		System.out.println("I am here " + f.toString());
		try {
			runConverter(Constants.ReportURL + url, f);
			System.out.println("Come on lets get ");
		} catch (IOException e) {
			// TODO Auto-generated catch block

			System.out.println("Pdf conversion exception " + e.getMessage());
		}

		// get absolute path of the application
		ServletContext context = request.getSession().getServletContext();
		String appPath = context.getRealPath("");
		String filename = "ordermemo221.pdf";
		String filePath = "/opt/tomcat-latest/webapps/uploads/Po.pdf";
		//String filePath = "C:/pdf/ordermemo221.pdf";
		//String filePath = "/ordermemo221.pdf";

		// construct the complete absolute path of the file
		String fullPath = appPath + filePath;
		File downloadFile = new File(filePath);
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(downloadFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
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

			// get output stream of the response
			OutputStream outStream;

			outStream = response.getOutputStream();

			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;

			// write bytes read from the input stream into the output stream

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

	private void runConverter(String urlstring, File output) throws IOException {

		if (urlstring.length() > 0) {
			if (!urlstring.startsWith("http://") && !urlstring.startsWith("file:")) {
				urlstring = "http://" + urlstring;
			}

			java.io.FileOutputStream fos = new java.io.FileOutputStream(output);

			if (proxyHost != null && proxyHost.length() != 0 && proxyPort != 0) {
				System.getProperties().setProperty("proxySet", "true");
				System.getProperties().setProperty("proxyHost", proxyHost);
				System.getProperties().setProperty("proxyPort", "" + proxyPort);
			}

			PD4ML pd4ml = new PD4ML();

			try {
				pd4ml.setPageSize(landscapeValue ? pd4ml.changePageOrientation(format) : format);
			} catch (Exception e) {
				System.out.println("Pdf conversion ethod excep " + e.getMessage());
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
