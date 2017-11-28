package com.ats.adminpanel.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
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
import com.ats.adminpanel.model.RawMaterial.GetRawMaterialDetailList;
import com.ats.adminpanel.model.RawMaterial.Info;
import com.ats.adminpanel.model.RawMaterial.RawMaterialDetails;
import com.ats.adminpanel.model.RawMaterial.RawMaterialTaxDetails;
import com.ats.adminpanel.model.RawMaterial.RawMaterialTaxDetailsList;
import com.ats.adminpanel.model.RawMaterial.RmItemCategory;
import com.ats.adminpanel.model.item.Item;
import com.ats.adminpanel.model.purchaseorder.PurchaseOrderDetail;
import com.ats.adminpanel.model.purchaseorder.PurchaseOrderHeader;
import com.ats.adminpanel.model.supplierMaster.SupPaymentTermsList;
import com.ats.adminpanel.model.supplierMaster.SupplierDetails;
import com.ats.adminpanel.model.supplierMaster.TransporterList;
import com.sun.javafx.UnmodifiableArrayList;

@Controller
public class PurchaseOrderController {
	
	public static List<PurchaseOrderDetail> purchaseOrderDetailList;
	public static List<SupplierDetails> supplierDetailsList;
	public static TransporterList transporterList ;
	public static SupPaymentTermsList supPaymentTerms;
	public static List<RawMaterialDetails> rawMaterialDetailsList;
	public static float gstPer;
	public static GetRawMaterialDetailList getRawMaterialDetailList;
	public static RawMaterialTaxDetailsList rawMaterialTaxDetailsList;
	 
	
	@RequestMapping(value = "/showDirectPurchaseOrder", method = RequestMethod.GET)
	public ModelAndView showPurchaseOrder(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/purchaseOrder/directPurchaseOrder");
		
		purchaseOrderDetailList=new ArrayList<PurchaseOrderDetail>();
		
		RestTemplate rest=new RestTemplate();
		//rawMaterialDetailsList=new ArrayList<RawMaterialDetails>();
		
		
		
		getRawMaterialDetailList=rest.getForObject(Constants.url +"rawMaterial/getAllRawMaterialList", GetRawMaterialDetailList.class);
		
	    
		System.out.println("RM Details : "+getRawMaterialDetailList.getRawMaterialDetailsList().toString());
		supplierDetailsList=new ArrayList<SupplierDetails>();
		  supplierDetailsList=rest.getForObject(Constants.url + "getAllSupplier",   List.class);
		  
		  transporterList=new TransporterList();
			  transporterList = rest.getForObject(Constants.url + "/showTransporters",
					TransporterList.class);

			System.out.println("Transporter List Response:" + transporterList.toString());
			  rawMaterialTaxDetailsList=rest.getForObject(Constants.url + "rawMaterial/getAllRmTaxList", RawMaterialTaxDetailsList.class);
			System.out.println("RM Tax data : "+rawMaterialTaxDetailsList);
			
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
			model.addObject("RawmaterialList", getRawMaterialDetailList.getRawMaterialDetailsList());

		return model;
	}
	@RequestMapping(value = "/showPurchaseOrder", method = RequestMethod.GET)
	public ModelAndView showPurchaseOrder2(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/purchaseOrder/purchaseOrder");
		
		

		return model;
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
			
			float discPer=Float.parseFloat(disc_per);
			int rmId=Integer.parseInt(rm_id);
			System.out.println("Rm Id : "+rmId);
			
			int poQty=Integer.parseInt(rmQty);
			
			PurchaseOrderDetail purchaseOrderDetail=new PurchaseOrderDetail();
			RestTemplate rest=new RestTemplate();
			
			 
			
			// List<RawMaterialDetails> rawMaterialDetails =rest.getForObject(Constants.url +"rawMaterial/getAllRawMaterial", List.class);
			for(int i=0;i<getRawMaterialDetailList.getRawMaterialDetailsList().size();i++)
			{
				
				System.out.println("Raw Material List :"+getRawMaterialDetailList.getRawMaterialDetailsList());
				
			
				
				System.out.println("In 1for"+getRawMaterialDetailList.getRawMaterialDetailsList().get(i).getRmId());
				
				if(getRawMaterialDetailList.getRawMaterialDetailsList().get(i).getRmId()==rmId)
				{	System.out.println("In 1for");
					for(int j=0;j<rawMaterialTaxDetailsList.getRawMaterialTaxDetailsList().size();j++)
					{System.out.println("In 1for");
						if(rawMaterialTaxDetailsList.getRawMaterialTaxDetailsList().get(j).getTaxId()==getRawMaterialDetailList.getRawMaterialDetailsList().get(i).getRmTaxId())
						{System.out.println("In 1if");
							purchaseOrderDetail.setCgstPer(rawMaterialTaxDetailsList.getRawMaterialTaxDetailsList().get(j).getCgstPer());
							purchaseOrderDetail.setSgstPer(rawMaterialTaxDetailsList.getRawMaterialTaxDetailsList().get(j).getSgstPer());
							purchaseOrderDetail.setIgstPer(rawMaterialTaxDetailsList.getRawMaterialTaxDetailsList().get(j).getIgstPer());
							gstPer=rawMaterialTaxDetailsList.getRawMaterialTaxDetailsList().get(j).getSgstPer()+rawMaterialTaxDetailsList.getRawMaterialTaxDetailsList().get(j).getCgstPer();
							purchaseOrderDetail.setGstPer(gstPer);
						}
					}
					System.out.println("11");
					System.out.println("Raw Material List :"+getRawMaterialDetailList.getRawMaterialDetailsList());
					int k=0;
					//purchaseOrderDetail.setCgstPer(10.09f);
					System.out.println(k++);
					purchaseOrderDetail.setRmId(rmId);
					System.out.println(k++);
					purchaseOrderDetail.setDelStatus(0);
					purchaseOrderDetail.setDiscPer(discPer);  //Discount Hard Coded
					purchaseOrderDetail.setPoDate(poDate);
					System.out.println(k++);
					purchaseOrderDetail.setPoNo(Integer.parseInt(poNo));
					purchaseOrderDetail.setPoQty(poQty);
					System.out.println(k++);
					purchaseOrderDetail.setPoRate(getRawMaterialDetailList.getRawMaterialDetailsList().get(i).getRmRate());
					float poTaxable=poQty*(getRawMaterialDetailList.getRawMaterialDetailsList().get(i).getRmRate());
					System.out.println(k++);
					purchaseOrderDetail.setPoTaxable(poQty*(getRawMaterialDetailList.getRawMaterialDetailsList().get(i).getRmRate()));//-Discount per %
					float poTotal=(poTaxable*gstPer)/100;
					purchaseOrderDetail.setPoTotal(poTotal);
					System.out.println(k++);
					purchaseOrderDetail.setPoType(Integer.parseInt(poType));
					purchaseOrderDetail.setRmId(rmId);
					purchaseOrderDetail.setRmName(getRawMaterialDetailList.getRawMaterialDetailsList().get(i).getRmName());
					purchaseOrderDetail.setRmRemark("Remark ");//Remark Hard Coded
					System.out.println(k++);
					purchaseOrderDetail.setRmUomId(getRawMaterialDetailList.getRawMaterialDetailsList().get(i).getRmUomId());
					purchaseOrderDetail.setSpecification(getRawMaterialDetailList.getRawMaterialDetailsList().get(i).getRmSpecification());
					purchaseOrderDetail.setSuppId(Integer.parseInt(suppId));
					System.out.println(k++);
					
					purchaseOrderDetail.setSchDays(0);
					
					purchaseOrderDetailList.add(purchaseOrderDetail);
					System.out.println("Data "+purchaseOrderDetail.toString());
					System.out.println("DataList "+purchaseOrderDetailList.toString());
				}
			}
			
			
			
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
					
		
	
	@RequestMapping(value = "/submitPurchaseOrder", method = RequestMethod.POST)
	public String submitPurchaseOrder(HttpServletRequest request, HttpServletResponse response) {

		//ModelAndView model = new ModelAndView("masters/purchaseOrder/directPurchaseOrder");
		
	// String taxation_rem=request.getParameter("taxation");
	 //System.out.println("taxa :"+taxation_rem);
	 	int taxationRem = Integer.parseInt(request.getParameter("taxation"));
	

		String delvDateRem = request.getParameter("delv_date");
		String delvAtRem = request.getParameter("delv_at");
	//	String rm_id = request.getParameter("rm_id");
		float discPer = Float.parseFloat(request.getParameter("disc_per"));
		String rmQty = request.getParameter("rm_qty");
		
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
		
		
		return "redirect:/showDirectPurchaseOrder";
	}

}
