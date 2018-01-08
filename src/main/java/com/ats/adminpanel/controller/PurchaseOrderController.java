package com.ats.adminpanel.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.commons.DateConvertor;
import com.ats.adminpanel.model.RawMaterial.GetRawMaterialDetailList;
import com.ats.adminpanel.model.RawMaterial.Info;
import com.ats.adminpanel.model.RawMaterial.RawMaterialDetails;
import com.ats.adminpanel.model.RawMaterial.RawMaterialDetailsList;
import com.ats.adminpanel.model.RawMaterial.RawMaterialTaxDetails;
import com.ats.adminpanel.model.RawMaterial.RawMaterialTaxDetailsList;
import com.ats.adminpanel.model.RawMaterial.RmItemCategory;
import com.ats.adminpanel.model.RawMaterial.RmItemGroup;
import com.ats.adminpanel.model.item.Item;
import com.ats.adminpanel.model.materialreceipt.GetMaterialRecNoteList;
import com.ats.adminpanel.model.materialreceipt.MaterialRecNote;
import com.ats.adminpanel.model.materialreceipt.Supplist;
import com.ats.adminpanel.model.purchaseorder.GetPurchaseOrderList;
import com.ats.adminpanel.model.purchaseorder.GetRmRateAndTax;
import com.ats.adminpanel.model.purchaseorder.PurchaseOrderDetail;
import com.ats.adminpanel.model.purchaseorder.PurchaseOrderHeader;

import com.ats.adminpanel.model.remarks.GetAllRemarksList;
import com.ats.adminpanel.model.supplierMaster.SupPaymentTermsList;
import com.ats.adminpanel.model.supplierMaster.SupplierDetails;
import com.ats.adminpanel.model.supplierMaster.TransporterList;
import com.sun.javafx.UnmodifiableArrayList;

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
		
		
		return rawMaterialDetailsList.getRawMaterialDetailsList();
	}
	
	@RequestMapping(value = "/showPurchaseOrder", method = RequestMethod.GET)
	public ModelAndView showPurchaseOrder2(HttpServletRequest request, HttpServletResponse response) {
		
		purchaseOrderDetailList=new ArrayList<PurchaseOrderDetail>();

		ModelAndView model = new ModelAndView("masters/purchaseOrder/purchaseOrder");
		RestTemplate rest=new RestTemplate();
		supplierDetailsList=new ArrayList<SupplierDetails>();
		  supplierDetailsList=rest.getForObject(Constants.url + "getAllSupplier",   List.class);
		  
		  getRawMaterialDetailList=rest.getForObject(Constants.url +"rawMaterial/getAllRawMaterialList", GetRawMaterialDetailList.class);
		System.out.println("RM Details : "+getRawMaterialDetailList.getRawMaterialDetailsList().toString());
			
			  
		transporterList=new TransporterList();
		transporterList = rest.getForObject(Constants.url + "/showTransporters",TransporterList.class);
				System.out.println("Transporter List Response:" + transporterList.toString());
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,Object>();
		map.add("status", "0,1,3");
		
		 getPurchaseOrderList=rest.postForObject(Constants.url + "purchaseOrder/getpurchaseorderList",map, GetPurchaseOrderList.class);
			System.out.println("Purchase Order : "+getPurchaseOrderList.getPurchaseOrderHeaderList());
			
			model.addObject("purchaseorderlist",getPurchaseOrderList.getPurchaseOrderHeaderList());
			model.addObject("supplierList", supplierDetailsList);
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
				 for(int j=0;j<poNo.length();j++)
				 {
					 if(poNo.contains(search))
					 {
						 list.add(getPurchaseOrderList.getPurchaseOrderHeaderList().get(i));
						 break;
					 }
				 }
			 }
			 
			 System.out.println("list"+list);
		 }catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		return list;
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
	
	
	@RequestMapping(value = "/editPurchaseOrder/{poId}", method = RequestMethod.GET)
	public ModelAndView editPurchaseOrder(@PathVariable int poId,HttpServletRequest request, HttpServletResponse response) {

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
			model.addObject("transporterList", transporterList.getTransporterList());
			model.addObject("RawmaterialList",rmItemGroupList);
			
		return model;
	}
	
	@RequestMapping(value = "/poHeaderWithDetailed/{poId}", method = RequestMethod.GET)
	public ModelAndView poHeaderWithDetailed(@PathVariable int poId,HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/purchaseOrder/poHeaderWithDetailed");
		try
		{
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,Object>();
			map.add("poId", poId);
			
			RestTemplate rest=new RestTemplate();
			PurchaseOrderHeader purchaseOrderHeader=rest.postForObject(Constants.url + "purchaseOrder/getpurchaseorderHeaderWithDetailed",map, PurchaseOrderHeader.class);
			 System.out.println("Response :"+purchaseOrderHeader.toString());
			 model.addObject("purchaseOrderHeader",purchaseOrderHeader);
			 model.addObject("supplierList", supplierDetailsList);
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
			for(int i=0;i<=getPurchaseOrderList.getPurchaseOrderHeaderList().size();i++)
			{
				if(getPurchaseOrderList.getPurchaseOrderHeaderList().get(i).getPoId()==poId)
				{
					 purchaseOrderHeader = getPurchaseOrderList.getPurchaseOrderHeaderList().get(i);
					break;
				}
			}
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
			
			model.addObject("purchaseorderlist",getPurchaseOrderList.getPurchaseOrderHeaderList());
			model.addObject("supplierList", supplierDetailsList);
		return model;
	}
	
	@RequestMapping(value = "/requestPOFinalByDirectore/{poId}", method = RequestMethod.GET)
	public String requestPOFinalByDirectore(@PathVariable int poId,HttpServletRequest request, HttpServletResponse response) {

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
				 Supplist supplierDetailsList = rest.getForObject(Constants.url + "/getAllSupplierlist", Supplist.class);
				 for(int i=0;i<supplierDetailsList.getSupplierDetailslist().size();i++)
				 {
					 if(supplierDetailsList.getSupplierDetailslist().get(i).getSuppId()==purchaseOrderHeader.getSuppId())
					 {
						 phonno=supplierDetailsList.getSupplierDetailslist().get(i).getSuppMob1();
						 email=supplierDetailsList.getSupplierDetailslist().get(i).getSuppEmail1();
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
				final String e_mail=email;
				mailSender.send(new MimeMessagePreparator() {

					@Override
					public void prepare(MimeMessage mimeMessage) throws Exception {
						MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
						messageHelper.setTo(e_mail);
						messageHelper.setSubject("Email Testing");
						messageHelper.setText("Nana Po Approved");

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
		
		  getRmRateAndTax=rest.postForObject(Constants.url +"purchaseOrder/getRmDetailByRmId", map, GetRmRateAndTax.class);
		if(getRmRateAndTax!=null)
		{
			res=1;
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
			
			int payId=0;
			
			int insuRem=Integer.parseInt(request.getParameter("insurance"));
			int freidhtRem=Integer.parseInt(request.getParameter("freight"));
			 
			int tranId=Integer.parseInt(request.getParameter("transportation"));
			String spRem= request.getParameter("sp_instruction");
			int validity=0;
			//float poTotalValue;
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
			
			int payId=0;
			
			int insuRem=Integer.parseInt(request.getParameter("insurance"));
			int freidhtRem=Integer.parseInt(request.getParameter("freight"));
			 
			int tranId=Integer.parseInt(request.getParameter("transportation"));
			String spRem= request.getParameter("sp_instruction");
			int validity=0;
			int poStatus=0;
		
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
	 	
		
		
		return "redirect:/showPurchaseOrder";
	}
	 
}
