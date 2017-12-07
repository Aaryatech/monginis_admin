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
import com.ats.adminpanel.model.materialreceipt.AddPolist;
import com.ats.adminpanel.model.materialreceipt.GetMaterialRecNoteList;
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
	private List<AddPolist> withaddPolist = new ArrayList<AddPolist>();;
	
	@RequestMapping(value = "/gateEntries", method = RequestMethod.GET)
	public ModelAndView gateEntries(HttpServletRequest request, HttpServletResponse response) {
		Constants.mainAct = 17;
		Constants.subAct=184;
		
		ModelAndView model = new ModelAndView("masters/gateEntryList");//


		return model;
	}
	
	
	@RequestMapping(value = "/addGateEntry", method = RequestMethod.GET)
	public ModelAndView addGateEntry(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/gateEntry");
		
		rawlist = new ArrayList<MRawMaterial>();
		System.out.println(rawlist);
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
	
	
	@RequestMapping(value = "/materialReceiptStore", method = RequestMethod.POST)
	public String materialReceiptStore(HttpServletRequest request, HttpServletResponse response) {
		/*Constants.mainAct = 17;
		Constants.subAct=184;*/
		ModelAndView model;
		 
		materialRecNoteDetailslist=new ArrayList<>();
		
		/*java.util.Date utildate = new java.util.Date();
		java.sql.Date date = new Date(utildate.getTime());*/
		
		
		
		
		
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
				materialRecNoteDetails.setPoQty(0);
				materialRecNoteDetails.setRecdQty(rawlist.get(i).getQty());
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
			System.out.println("materialRecNoteDetailslist" + materialRecNoteDetailslist.size());
			RestTemplate rest=new RestTemplate();
			materialRecNotes=rest.postForObject(Constants.url + "/postMaterialRecNote",materialRecNote, MaterialRecNote.class);//enter first form
			
			//----------------------------------------------------------------------------------------------------------
			DateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
			
			
			
			
			
			
			
				
			
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
			
		}
		
		return "addGateEntry";
	}
	
	
	


	@RequestMapping(value = "/submitMaterialStore", method = RequestMethod.POST)
	public ModelAndView submitMaterialStore(HttpServletRequest request, HttpServletResponse response) {
		/*Constants.mainAct = 17;
		Constants.subAct=184;*/
		System.out.println(rawlist);
		materialRecNoteDetailslist=new ArrayList<MaterialRecNoteDetails>();
		ModelAndView model = new ModelAndView("masters/materialReceiptDirectore");
		
		int mrn_no = Integer.parseInt(request.getParameter("mrn_id"));
		String invoice_no = request.getParameter("invoice_no");
		int againstpo_id = Integer.parseInt(request.getParameter("po_id"));
		int poref_id = Integer.parseInt(request.getParameter("poref_id"));
		String Remark = request.getParameter("Remark");
		
		MaterialRecNote materialRecNote =materialRecNotes;
		
		 
		System.out.println(mrn_no+ invoice_no+ againstpo_id +poref_id+ Remark);
		try
		{
		
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date date = new Date();
			System.out.println(dateFormat.format(date));
			
			SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
			Date dt = sf.parse(sf.format(new Date()));
			
		
			//--------------------------------------------
			materialRecNote.setMrnStoreDate(dt);
			materialRecNote.setMrnType(mrn_no);
			materialRecNote.setApainstPo(againstpo_id);
			materialRecNote.setPoId(poref_id);
			materialRecNote.setInvoiceNumber(invoice_no);
			materialRecNote.setInvDate(dt);
			materialRecNote.setPoNo("");
			materialRecNote.setPoDate(dt);
			materialRecNote.setUseridStores(0);
			materialRecNote.setStoresRemark(Remark);
			materialRecNote.setApprovedUserId(0);
			materialRecNote.setApprovalRemark("HardCoded Apprv Remark");
			//--------------------------------------------
			 
			System.out.println("materialRecNote"+materialRecNote.toString());
			
			for(int i=0;i<withaddPolist.size();i++)
			{
			 
			  
				MaterialRecNoteDetails materialRecNoteDetails = new MaterialRecNoteDetails();
				
				materialRecNoteDetails.setRmId(withaddPolist.get(i).getRmId());
				materialRecNoteDetails.setRmName(withaddPolist.get(i).getRmName());	
				materialRecNoteDetails.setPoRate(withaddPolist.get(i).getPoRate());
				 
				 
				//materialRecNoteDetails.setMrnNo(withaddPolist.get(i).getMrnNo());
				materialRecNoteDetails.setRmUom("");
				materialRecNoteDetails.setSupplierId(materialRecNoteDetailslist.get(i).getSupplierId());
				materialRecNoteDetails.setPoId(0);
				materialRecNoteDetails.setPoQty(materialRecNoteDetailslist.get(i).getPoQty());
				materialRecNoteDetails.setRecdQty(5);
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
			System.out.println("materialRecNoteDetailslist" + materialRecNoteDetailslist.toString());
			RestTemplate rest=new RestTemplate();
			materialRecNotes=rest.postForObject(Constants.url + "/postMaterialRecNote",materialRecNote, MaterialRecNote.class);
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
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
		System.out.println(rawlist.size());
	}catch(Exception e)
	{
		e.printStackTrace();
		System.out.println(e.getMessage());
	}
		
		return rawlist;
		
	}
	
	
	@RequestMapping(value = "/withPoRef", method = RequestMethod.GET)
	public @ResponseBody List<PurchaseOrderDetail> withPo(HttpServletRequest request,
		HttpServletResponse response) {
		
		PurchaseOrderDetailedList purchaseOrderDetailedList=new PurchaseOrderDetailedList();
		try
		{
			System.out.println("in controller");
			int poId = Integer.parseInt(request.getParameter("poref_id"));
			
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("poId", poId);
			
			
			RestTemplate rest = new RestTemplate();
			purchaseOrderDetailedList = rest.postForObject(Constants.url + "purchaseOrder/purchaseorderdetailedList",map,PurchaseOrderDetailedList.class);
		
			
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return purchaseOrderDetailedList.getPurchaseOrderDetaillist() ;

	}
	
	@RequestMapping(value = "/showAllStoreMaterialReciept", method = RequestMethod.GET)
	public ModelAndView showAllStoreMaterialReciept(HttpServletRequest request, HttpServletResponse response) {
		/*Constants.mainAct = 17;
		Constants.subAct=184;
		*/
		ModelAndView model = new ModelAndView("masters/allStoreMaterialReciept");
		List<SupplierDetails> supplierDetailsList=new ArrayList<SupplierDetails>();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("status",0);
 
		RestTemplate rest = new RestTemplate();
		GetMaterialRecNoteList materialRecNoteList=rest.postForObject(Constants.url + "/getMaterialRecNotes",map, GetMaterialRecNoteList.class);
		System.out.println("materialRecNoteList  :"+materialRecNoteList.toString());
		
		supplierDetailsList = rest.getForObject(Constants.url + "/getAllSupplier", List.class);

		System.out.println("Supplier List :"+supplierDetailsList.toString());
 
		model.addObject("materialRecNoteList", materialRecNoteList.getMaterialRecNoteList());
		model.addObject("supplierDetailsList",supplierDetailsList);
		return model;
	}
	
	
	@RequestMapping(value = "/showStoreMaterialReciept", method = RequestMethod.GET)
	public ModelAndView showStoreMaterialReciept(HttpServletRequest request, HttpServletResponse response) {
	
		
		ModelAndView model = new ModelAndView("masters/materialReceiptStore");

		int mrnId=Integer.parseInt(request.getParameter("mrnId"));
	MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
	map.add("mrnId",mrnId);


	RestTemplate rest = new RestTemplate();
	MaterialRecNote materialRecNoteHeader = rest.postForObject(Constants.url + "/getMaterialRecNotesHeaderDetails",map, MaterialRecNote.class);
	 
	System.out.println("purchaseOrderListDetailedList   :"+ materialRecNoteHeader.getMaterialRecNoteDetails());
	
	
	List<RmItemGroup> rmItemGroupList=rest.getForObject(Constants.url + "rawMaterial/getAllRmItemGroup", List.class);
	model.addObject("mrntype", rmItemGroupList);
	
	TransporterList transporterList = rest.getForObject(Constants.url + "/showTransporters",TransporterList.class);
	
	for(int i=0;i<transporterList.getTransporterList().size();i++)
	{
		if(transporterList.getTransporterList().get(i).getTranId()==materialRecNoteHeader.getTransportId())
		{
			model.addObject("transname", transporterList.getTransporterList().get(i).getTranName());
			break;
		}
	}
	Supplist supplierDetailsList =  rest.getForObject(Constants.url + "/getAllSupplierlist", Supplist.class);
	 
	System.out.println(supplierDetailsList.getSupplierDetailslist().toString());
	
	 
	
	int suupId=materialRecNoteHeader.getSupplierId();
	
	for(int i=0;i<supplierDetailsList.getSupplierDetailslist().size();i++)
	{
		if(suupId==supplierDetailsList.getSupplierDetailslist().get(i).getSuppId())
			model.addObject("suppName",supplierDetailsList.getSupplierDetailslist().get(i).getSuppName() );
	}
	
	MultiValueMap<String, Object> map1 = new LinkedMultiValueMap<String, Object>();
	map1.add("suppId", suupId);
	System.out.println("SupplierId"+suupId);
	
	GetPurchaseOrderList getPurchaseOrderList = rest.postForObject(Constants.url + "purchaseOrder/purchaseorderList",map1,GetPurchaseOrderList.class);
	System.out.println(getPurchaseOrderList.toString());

	model.addObject("polist", purchaseOrderHeaderlist);
	model.addObject("rawlist", rawlist);
	model.addObject("materialRecNote", materialRecNoteHeader);
	model.addObject("materialRecNoteDetail", materialRecNoteHeader.getMaterialRecNoteDetails());
	model.addObject("purchaseOrderList", getPurchaseOrderList.getPurchaseOrderHeaderList());
	return model;
	}
}





