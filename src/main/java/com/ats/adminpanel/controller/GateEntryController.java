package com.ats.adminpanel.controller;



import java.text.DateFormat;
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
import com.ats.adminpanel.model.MRawMaterial;
import com.ats.adminpanel.model.RawMaterial.GetRawMaterialDetailList;
import com.ats.adminpanel.model.RawMaterial.RmItemGroup;

import com.ats.adminpanel.model.materialreceipt.MaterialRecNote;
import com.ats.adminpanel.model.materialreceipt.MaterialRecNoteDetails;
import com.ats.adminpanel.model.materialreceipt.Supplist;
import com.ats.adminpanel.model.purchaseorder.GetPurchaseOrder;
import com.ats.adminpanel.model.purchaseorder.GetPurchaseOrderList;
import com.ats.adminpanel.model.purchaseorder.PurchaseOrderDetail;
import com.ats.adminpanel.model.purchaseorder.PurchaseOrderDetailedList;
import com.ats.adminpanel.model.purchaseorder.PurchaseOrderHeader;
import com.ats.adminpanel.model.supplierMaster.SupplierDetails;
import com.ats.adminpanel.model.supplierMaster.TransporterList;

@Controller
public class GateEntryController {

	public static GetRawMaterialDetailList getRawMaterialDetailList = new GetRawMaterialDetailList();
	public static List<MRawMaterial> rawlist = new ArrayList<MRawMaterial>();
	private static List<MaterialRecNoteDetails> materialRecNoteDetailslist = new ArrayList<MaterialRecNoteDetails>();
	private List<PurchaseOrderHeader> purchaseOrderHeaderlist = new ArrayList<PurchaseOrderHeader>();
	public static List<PurchaseOrderDetail> purchaseOrderDetailList = new ArrayList<PurchaseOrderDetail>();
	
	
	
	MaterialRecNote materialRecNotes;
	
	@RequestMapping(value = "/gateEntries", method = RequestMethod.GET)
	public ModelAndView gateEntries(HttpServletRequest request, HttpServletResponse response) {
		Constants.mainAct = 17;
		Constants.subAct=184;
		
		ModelAndView model = new ModelAndView("masters/gateEntryList");//


		return model;
	}
	
	@RequestMapping(value = "/materialReceiptStore", method = RequestMethod.POST)
	public ModelAndView materialReceiptStore(HttpServletRequest request, HttpServletResponse response) {
		/*Constants.mainAct = 17;
		Constants.subAct=184;*/
		

		
		/*java.util.Date utildate = new java.util.Date();
		java.sql.Date date = new Date(utildate.getTime());*/
		
		
		
		ModelAndView model;
		model = new ModelAndView("masters/materialReceiptStore");
		
		int sup_id = Integer.parseInt(request.getParameter("supp_id"));
		String mrn_no=request.getParameter("mrn_no");
		String vehino = request.getParameter("vehicle_no");
		String lrno = request.getParameter("lr_no");
		int tran_id = Integer.parseInt(request.getParameter("tran_id"));
		int no_of_items = Integer.parseInt(request.getParameter("no_of_items"));
		String remark = request.getParameter("remark");
		String photo1 = request.getParameter("image1");
		String photo2 = request.getParameter("image2");
		
		System.out.println(sup_id+ vehino+ lrno +tran_id +no_of_items +remark+ photo1 +photo2);
		
		MaterialRecNote materialRecNote = new MaterialRecNote();
		
		
		try
		{
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date date = new Date();
			System.out.println(dateFormat.format(date));
			
			SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
			Date dt = sf.parse(sf.format(new Date()));
			
			materialRecNote.setMrnId(0);
			materialRecNote.setMrnNo(mrn_no);
			materialRecNote.setGateEntryTime("00:00:00");
			materialRecNote.setGateEntryDate(dt);
			materialRecNote.setTransportId(tran_id);
			materialRecNote.setVehicleNo(vehino);
			materialRecNote.setLrNo(lrno);
			materialRecNote.setLrDate(dt);
			materialRecNote.setSupplierId(sup_id);
			materialRecNote.setNoOfItem(no_of_items);
			materialRecNote.setPhoto1(photo1);
			materialRecNote.setPhoto2(photo2);
			materialRecNote.setGateUserId(1);
			materialRecNote.setGateRemark(remark);
			//--------------------------------------------
			materialRecNote.setMrnStoreDate(dt);
			materialRecNote.setMrnType(0);
			materialRecNote.setApainstPo(0);
			materialRecNote.setPoId(0);
			materialRecNote.setPoNo("");
			materialRecNote.setPoDate(dt);
			materialRecNote.setUseridStores(0);
			materialRecNote.setStoresRemark("");
			materialRecNote.setApprovedUserId(0);
			materialRecNote.setApprovalRemark("");
			//--------------------------------------------
			materialRecNote.setInvBookDate(dt);
			materialRecNote.setInvoiceNumber("");
			materialRecNote.setInvDate(dt);
			materialRecNote.setBasicValue(0);
			materialRecNote.setDiscPer(0);
			materialRecNote.setDiscAmt(0);
			materialRecNote.setDiscAmt2(0);
			materialRecNote.setFreightAmt(0);
			materialRecNote.setInsuranceAmt(0);
			materialRecNote.setCgst(0);
			materialRecNote.setSgst(0);
			materialRecNote.setIgst(0);
			materialRecNote.setCess(0);
			materialRecNote.setRoundOff(0);
			materialRecNote.setBillAmount(0);
			materialRecNote.setUseridAcc(0);
			materialRecNote.setAccRemark("");
			//-----------------------------------------
			materialRecNote.setStatus(0);
			materialRecNote.setDelStatus(0);
			materialRecNote.setIsTallySync(0);
			
			for(int i=0;i<rawlist.size();i++)
			{
				MaterialRecNoteDetails materialRecNoteDetails = new MaterialRecNoteDetails();
				
				materialRecNoteDetails.setRmId(rawlist.get(i).getRmId());
				materialRecNoteDetails.setRmName(rawlist.get(i).getRmName());	
				materialRecNoteDetails.setPoRate(rawlist.get(i).getRmRate());
				materialRecNoteDetails.setMrnDetailId(0);
				materialRecNoteDetails.setMrnId(0);
				materialRecNoteDetails.setMrnNo(mrn_no);
				materialRecNoteDetails.setRmUom("");
				materialRecNoteDetails.setSupplierId(sup_id);
				materialRecNoteDetails.setPoId(0);
				materialRecNoteDetails.setPoQty(rawlist.get(i).getQty());
				materialRecNoteDetails.setRecdQty(0);
				materialRecNoteDetails.setStockQty(0);
				materialRecNoteDetails.setRejectedQty(0);
				materialRecNoteDetails.setValue(0);
				materialRecNoteDetails.setDiscPer(0);
				materialRecNoteDetails.setDiscAmt(0);
				materialRecNoteDetails.setGstPer(0);
				materialRecNoteDetails.setFreightAmt(0);
				materialRecNoteDetails.setFreightAmt(0);
				materialRecNoteDetails.setInsurance_amt(0);
				materialRecNoteDetails.setInsurancePer(0);
				materialRecNoteDetails.setCgstPer(0);
				materialRecNoteDetails.setCgstRs(0);
				materialRecNoteDetails.setSgstPer(0);
				materialRecNoteDetails.setSgstRs(0);
				materialRecNoteDetails.setIgstPer(0);
				materialRecNoteDetails.setIgstRs(0);
				materialRecNoteDetails.setCessPer(0);
				materialRecNoteDetails.setCessRs(0);
				materialRecNoteDetails.setAmount(0);
				materialRecNoteDetails.setDirectorApproved(0);
				materialRecNoteDetails.setDelStatus(0);
				materialRecNoteDetails.setStatus(0);
				materialRecNoteDetailslist.add(materialRecNoteDetails);
				
				
			}
			
			materialRecNote.setMaterialRecNoteDetails(materialRecNoteDetailslist);
			RestTemplate rest=new RestTemplate();
			materialRecNotes=rest.postForObject(Constants.url + "/postMaterialRecNote",materialRecNote, MaterialRecNote.class);//enter first form
			
			//----------------------------------------------------------------------------------------------------------
			DateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
		
			
			model.addObject("mrnid", materialRecNotes.getMrnId());
			model.addObject("mrnno", materialRecNotes.getMrnNo());
			model.addObject("date", dateFormat1.format(materialRecNotes.getMrnStoreDate()));
			model.addObject("vehicalno", materialRecNotes.getVehicleNo());
			model.addObject("lrno", materialRecNotes.getLrNo());
			List<RmItemGroup> rmItemGroupList=rest.getForObject(Constants.url + "rawMaterial/getAllRmItemGroup", List.class);
			model.addObject("mrntype", rmItemGroupList);
			
			TransporterList transporterList = rest.getForObject(Constants.url + "/showTransporters",TransporterList.class);
			
			for(int i=0;i<transporterList.getTransporterList().size();i++)
			{
				if(transporterList.getTransporterList().get(i).getTranId()==materialRecNotes.getTransportId())
				{
					model.addObject("transname", transporterList.getTransporterList().get(i).getTranName());
					break;
				}
			}
			Supplist supplierDetailsList =  rest.getForObject(Constants.url + "/getAllSupplierlist", Supplist.class);
			 
			System.out.println(supplierDetailsList.getSupplierDetailslist().toString());
			
			int supplirid = 0;
			
			for(int i=0;i<supplierDetailsList.getSupplierDetailslist().size();i++)
			{
				if(supplierDetailsList.getSupplierDetailslist().get(i).getSuppId()==materialRecNotes.getSupplierId())
				{
					 supplirid=supplierDetailsList.getSupplierDetailslist().get(i).getSuppId();
					model.addObject("suplrname", supplierDetailsList.getSupplierDetailslist().get(i).getSuppName());
					break;
				}
			}
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("suppId", supplirid);
			System.out.println("SupplierId"+supplirid);
			GetPurchaseOrder getPurchaseOrder = rest.postForObject(Constants.url + "purchaseOrder/perchaseorderList",map,GetPurchaseOrder.class);
			System.out.println(getPurchaseOrder);
			
			List<GetPurchaseOrderList> getPurchaseOrderList = getPurchaseOrder.getGetPurchaseOrderList();
			
			for(int i=1;i<getPurchaseOrderList.size();i++)
			{
				PurchaseOrderHeader purchaseOrderHeader = new PurchaseOrderHeader();
				purchaseOrderHeader=getPurchaseOrderList.get(i).getPurchaseOrderHeader();
				System.out.println(purchaseOrderHeader.getPoId());
				purchaseOrderHeaderlist.add(purchaseOrderHeader);
				
			}
			model.addObject("polist", purchaseOrderHeaderlist);
			model.addObject("rawlist", rawlist);
			
				
			
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
			
		}
		
		return model;
	}
	
	
	


	@RequestMapping(value = "/materialReceiptDirectore", method = RequestMethod.GET)
	public ModelAndView materialReceiptDirectore(HttpServletRequest request, HttpServletResponse response) {
		/*Constants.mainAct = 17;
		Constants.subAct=184;*/
		
		ModelAndView model = new ModelAndView("masters/materialReceiptDirectore");


		return model;
	}
	
	
	@RequestMapping(value = "/addGateEntry", method = RequestMethod.GET)
	public ModelAndView addGateEntry(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/gateEntry");
		rawlist = new ArrayList<MRawMaterial>();
		try {
		RestTemplate rest = new RestTemplate();
		List<SupplierDetails> supplierDetailsList = rest.getForObject(Constants.url + "/getAllSupplier", List.class);

		TransporterList transporterList = rest.getForObject(Constants.url + "/showTransporters",TransporterList.class);
		
		  getRawMaterialDetailList=rest.getForObject(Constants.url +"rawMaterial/getAllRawMaterialList", GetRawMaterialDetailList.class);
		String key = "mrn_no";
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		
		map.add("key",key);
		
		
		
		
		
		System.out.println("Transporter List Response:" + transporterList.toString());

		System.out.println("Transporter List Response:" +  getRawMaterialDetailList.getRawMaterialDetailsList().toString());
		model.addObject("supplierList", supplierDetailsList);

		model.addObject("rmlist", getRawMaterialDetailList.getRawMaterialDetailsList());
		model.addObject("transporterList", transporterList.getTransporterList());
		
		Integer value = rest.postForObject(Constants.url+ "/findvaluebykey",map,Integer.class);
		
		System.out.println("value = "+value);
		model.addObject("mrnno", String.valueOf(value));

		} catch (Exception e) {
			System.out.println("Exception In Add Gate Entry :" + e.getMessage());

		}

		return model;
	}
	
	
	
	@RequestMapping(value = "/gateEntryList", method = RequestMethod.GET)
	public @ResponseBody List<MRawMaterial> gateEntryList(HttpServletRequest request,
		HttpServletResponse response) {
		
		int rmId = Integer.parseInt(request.getParameter("rm_id"));
		int qty = Integer.parseInt(request.getParameter("rm_qty"));
		
		
		
		System.out.println(qty);
		
		
	try
	{
		
		
		for(int i=0;i<getRawMaterialDetailList.getRawMaterialDetailsList().size();i++) {
			if(getRawMaterialDetailList.getRawMaterialDetailsList().get(i).getRmId()==rmId)
			{
				MRawMaterial mRm = new MRawMaterial();
				System.out.println("rawMaterialDetails  :"+getRawMaterialDetailList.getRawMaterialDetailsList().get(i).toString());
				
				mRm.setRmId(rmId);
				mRm.setRmName(getRawMaterialDetailList.getRawMaterialDetailsList().get(i).getRmName());
				mRm.setRmRate(getRawMaterialDetailList.getRawMaterialDetailsList().get(i).getRmRate());
				mRm.setQty(qty);
				System.out.println(mRm.toString());
				rawlist.add(mRm);
				
			}
		}
	}catch(Exception e)
	{
		e.printStackTrace();
		System.out.println(e.getMessage());
	}
		
		return rawlist;
		
	}
	
	
	@RequestMapping(value = "/withPoRef", method = RequestMethod.GET)
	public @ResponseBody List<MRawMaterial> withPo(HttpServletRequest request,
		HttpServletResponse response) {
		
		
		try
		{
			System.out.println("in controller");
			int poId = Integer.parseInt(request.getParameter("poref_id"));
			
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("poId", poId);
			
			
			
			RestTemplate rest = new RestTemplate();
			PurchaseOrderDetailedList purchaseOrderDetailedList = rest.postForObject(Constants.url + "purchaseOrder/perchaseorderdetailedList",map,PurchaseOrderDetailedList.class);
			for(int i=0;i<purchaseOrderDetailedList.getPurchaseOrderDetaillist().size();i++)
			{
				MRawMaterial mRm = new MRawMaterial();
				
				mRm.setRmId(purchaseOrderDetailedList.getPurchaseOrderDetaillist().get(i).getRmId());
				mRm.setRmName(purchaseOrderDetailedList.getPurchaseOrderDetaillist().get(i).getRmName());
				mRm.setStockQty(0);
				mRm.setPoQty(purchaseOrderDetailedList.getPurchaseOrderDetaillist().get(i).getPoQty());
				mRm.setPoRate(purchaseOrderDetailedList.getPurchaseOrderDetaillist().get(i).getPoRate());
				mRm.setRmRate(0);
				
				rawlist.add(mRm);
				
			}
		System.out.println("End for");
		System.out.println(rawlist);
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return rawlist;

	}
	
}





