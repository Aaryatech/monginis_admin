package com.ats.adminpanel.controller;



import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.ats.adminpanel.model.ErrorMessage;
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
import com.ats.adminpanel.model.remarks.GetAllRemarksList;
import com.ats.adminpanel.model.supplierMaster.SupplierDetails;
import com.ats.adminpanel.model.supplierMaster.TransporterList;

@Controller
public class MaterialReceiptNoteController {

	public static GetRawMaterialDetailList getRawMaterialDetailList = new GetRawMaterialDetailList();
	public static List<MRawMaterial> rawlist = new ArrayList<MRawMaterial>();
	public static List<MaterialRecNoteDetails> materialRecNoteDetailslist = new ArrayList<MaterialRecNoteDetails>();
	public List<PurchaseOrderHeader> purchaseOrderHeaderlist = new ArrayList<PurchaseOrderHeader>();
	public static List<PurchaseOrderDetail> purchaseOrderDetailList = new ArrayList<PurchaseOrderDetail>();
	MaterialRecNote materialRecNoteHeader;
	public static MaterialRecNote materialRecNote;
	
	
	MaterialRecNote materialRecNotes;
	public static  List<MaterialRecNoteDetails> getmaterialRecNoteDetailslist = new ArrayList<MaterialRecNoteDetails>();
	public static  List<MaterialRecNoteDetails> addmaterialRecNoteDetailslist = new ArrayList<MaterialRecNoteDetails>();
	private PurchaseOrderHeader purchaseOrderHeaderfordate;
	private String podate;
	public static List<PurchaseOrderDetail> purchaseOrderDetailedListcomp;
	
	@RequestMapping(value = "/gateEntries", method = RequestMethod.GET)
	public ModelAndView gateEntries(HttpServletRequest request, HttpServletResponse response) {
		Constants.mainAct = 17;
		Constants.subAct=184;
		
		ModelAndView model = new ModelAndView("masters/gateEntryList");//
		
		List<SupplierDetails> supplierDetailsList=new ArrayList<SupplierDetails>();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("status",0);
 
		RestTemplate rest = new RestTemplate();
		GetMaterialRecNoteList materialRecNoteList=rest.postForObject(Constants.url + "/getMaterialRecNotes",map, GetMaterialRecNoteList.class);
		System.out.println("materialRecNoteList  :"+materialRecNoteList.toString());
		
		supplierDetailsList = rest.getForObject(Constants.url + "/getAllSupplier", List.class);
		TransporterList transporterList = rest.getForObject(Constants.url + "/showTransporters",TransporterList.class);
		System.out.println("Supplier List :"+supplierDetailsList.toString());
 
		model.addObject("materialRecNoteList", materialRecNoteList.getMaterialRecNoteList());
		model.addObject("supplierDetailsList",supplierDetailsList);
		model.addObject("transportlist",transporterList.getTransporterList());
		return model;


		
	}
	
	
	@RequestMapping(value = "/addGateEntry", method = RequestMethod.GET)
	public ModelAndView addGateEntry(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/gateEntry");
		
		addmaterialRecNoteDetailslist = new ArrayList<MaterialRecNoteDetails>();
		System.out.println(rawlist);
		try {
		RestTemplate rest = new RestTemplate();
		List<SupplierDetails> supplierDetailsList = rest.getForObject(Constants.url + "/getAllSupplier", List.class);

		TransporterList transporterList = rest.getForObject(Constants.url + "/showTransporters",TransporterList.class);
		
		  getRawMaterialDetailList=rest.getForObject(Constants.url +"rawMaterial/getAllRawMaterialList", GetRawMaterialDetailList.class);
		String key = "mrn_no";
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		
		map.add("key",key);
		
		
		
		
		
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
		String nowDate = request.getParameter("nowDate");
		System.out.println("NOW data   dfghjjj :"+nowDate);
		
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
			materialRecNote.setGateEntryDate(nowDate);
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
			materialRecNote.setPoDate(nowDate);
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
			
			for(int i=0;i<addmaterialRecNoteDetailslist.size();i++)
			{
				MaterialRecNoteDetails materialRecNoteDetails = new MaterialRecNoteDetails();
				
				materialRecNoteDetails.setRmId(addmaterialRecNoteDetailslist.get(i).getRmId());
				materialRecNoteDetails.setRmName(addmaterialRecNoteDetailslist.get(i).getRmName());	
				materialRecNoteDetails.setPoRate(0);
				materialRecNoteDetails.setMrnDetailId(0);
				materialRecNoteDetails.setMrnId(0);
				materialRecNoteDetails.setMrnNo(mrn_no);
				materialRecNoteDetails.setRmUom("");
				materialRecNoteDetails.setSupplierId(sup_id);
				materialRecNoteDetails.setPoId(0);
				materialRecNoteDetails.setPoQty(0);
				materialRecNoteDetails.setRecdQty(addmaterialRecNoteDetailslist.get(i).getRecdQty());
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
			int value = rest.getForObject(Constants.url+ "/updateValuekey",Integer.class);
			System.out.println("return value "+value);
			//----------------------------------------------------------------------------------------------------------
			DateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
			
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
			
		}
		
		return "redirect:/gateEntries";
	}
	
	@RequestMapping(value = "/editGateEntry", method = RequestMethod.GET)
	public ModelAndView editStoreMaterialReciept(HttpServletRequest request, HttpServletResponse response) {
	
		
		ModelAndView model = new ModelAndView("masters/editGateEntry");
		addmaterialRecNoteDetailslist = new ArrayList<MaterialRecNoteDetails>();

		int mrnId=Integer.parseInt(request.getParameter("mrnId"));
	MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
	map.add("mrnId",mrnId);


	RestTemplate rest = new RestTemplate();
	materialRecNoteHeader = rest.postForObject(Constants.url + "/getMaterialRecNotesHeaderDetails",map, MaterialRecNote.class);
	getmaterialRecNoteDetailslist = materialRecNoteHeader.getMaterialRecNoteDetails();
	 
	System.out.println("material detailed   :"+ materialRecNoteHeader.getMaterialRecNoteDetails());
	
	
	TransporterList transporterList = rest.getForObject(Constants.url + "/showTransporters",TransporterList.class);
	
	
	Supplist supplierDetailsList =  rest.getForObject(Constants.url + "/getAllSupplierlist", Supplist.class);
	 
	System.out.println(supplierDetailsList.getSupplierDetailslist().toString());
	
	
	
	GetRawMaterialDetailList getRawMaterialDetail=rest.getForObject(Constants.url +"rawMaterial/getAllRawMaterialList", GetRawMaterialDetailList.class);
	
	

	model.addObject("materialRecNote", materialRecNoteHeader);
	model.addObject("materialRecNoteDetail", getmaterialRecNoteDetailslist);
	model.addObject("supplierList",supplierDetailsList.getSupplierDetailslist());
	model.addObject("transport",transporterList.getTransporterList());
	model.addObject("rmlist", getRawMaterialDetail.getRawMaterialDetailsList());
	return model;
	}
	
	
	@RequestMapping(value = "/submitEditGateEntry", method = RequestMethod.POST)
	public String submitEditGateEntry(HttpServletRequest request, HttpServletResponse response) {
		/*Constants.mainAct = 17;
		Constants.subAct=184;*/
		
		
		int sup_id = Integer.parseInt(request.getParameter("supp_id"));
		String mrn_no=request.getParameter("mrn_no");
		String vehino = request.getParameter("vehicle_no");
		String lrno = request.getParameter("lr_no");
		int tran_id = Integer.parseInt(request.getParameter("tran_id"));
		int no_of_items = Integer.parseInt(request.getParameter("no_of_items"));
		String remark = request.getParameter("remark");
		String photo1 = request.getParameter("image1");
		String photo2 = request.getParameter("image2");
		
		MaterialRecNote materialRecNote = materialRecNoteHeader;
		
		System.out.println("reEnter value : "+ sup_id+mrn_no+vehino+lrno+tran_id+no_of_items+remark+photo1+photo2);
	
		try
		{
		
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date date = new Date();
			System.out.println(dateFormat.format(date));
			
			SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
			Date dt = sf.parse(sf.format(new Date()));
			
		
			//--------------------------------------------
			materialRecNote.setMrnId(materialRecNote.getMrnId());
			materialRecNote.setMrnNo(mrn_no);
			materialRecNote.setGateEntryTime("00:00:00");
			materialRecNote.setTransportId(tran_id);
			materialRecNote.setVehicleNo(vehino);
			materialRecNote.setLrNo(lrno);
			materialRecNote.setSupplierId(sup_id);
			materialRecNote.setNoOfItem(no_of_items);
			materialRecNote.setPhoto1(photo1);
			materialRecNote.setPhoto2(photo2);
			materialRecNote.setGateUserId(1);
			materialRecNote.setGateRemark(remark);
			
			System.out.println("SEt header data"+materialRecNote.toString());
			
			for(int i=0;i<addmaterialRecNoteDetailslist.size();i++)
			{
				
				MaterialRecNoteDetails materialRecNoteDetails= addmaterialRecNoteDetailslist.get(i);
				 addmaterialRecNoteDetailslist.get(i).setMrnNo(mrn_no);
				 addmaterialRecNoteDetailslist.get(i).setRmUom("");
				getmaterialRecNoteDetailslist.add(materialRecNoteDetails);		
			}
			materialRecNote.setMaterialRecNoteDetails(getmaterialRecNoteDetailslist);
			
			
			System.out.println(materialRecNote.getMaterialRecNoteDetails());
			
			
			//-------------------------------------------------------
			RestTemplate rest=new RestTemplate();
			System.out.println("EditGateEntry   "+materialRecNote.toString());
			materialRecNote=rest.postForObject(Constants.url + "/postMaterialRecNote",materialRecNote, MaterialRecNote.class);
		
			System.out.println("returnEditGateEntry   "+materialRecNote.toString());	
			
			 
			
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return "redirect:/gateEntries";
	}
	
	
	


	@RequestMapping(value = "/submitMaterialStore", method = RequestMethod.POST)
	public String submitMaterialStore(HttpServletRequest request, HttpServletResponse response) {
		/*Constants.mainAct = 17;
		Constants.subAct=184;*/
		
		
		int mrnType = Integer.parseInt(request.getParameter("mrn_id"));
		int againstpo_id=0;
		int poref_id=0;
		
		if(request.getParameter("po_id")=="")
		{
			againstpo_id = 2;
		}
		else
		{
			againstpo_id = Integer.parseInt(request.getParameter("po_id"));
		}
		
		if(request.getParameter("poref_id")==null)
		{
			poref_id = 0;
		}
		else
		{
			poref_id = Integer.parseInt(request.getParameter("poref_id"));
		}
		
		String po_date;
		String Remark = request.getParameter("Remark");
		if(request.getParameter("po_date")==null)
		{

			Date todaysDate = new Date();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

			String testDateString = df.format(todaysDate);
			po_date=testDateString;
			System.out.println("po_date in null"+po_date);
		}
		else
		{
			po_date=request.getParameter("po_date");
			System.out.println("po_date"+po_date);
		}
		
		int status = 1;
		MaterialRecNote materialRecNote = materialRecNoteHeader;
		
		
	
		 
		System.out.println(mrnType+ againstpo_id +poref_id+ Remark);
		try
		{
		
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date date = new Date();
			System.out.println(dateFormat.format(date));
			
			SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
			Date dt = sf.parse(sf.format(new Date()));
			
		
			//--------------------------------------------
			materialRecNote.setMrnStoreDate(dt);
			materialRecNote.setMrnType(mrnType);
			materialRecNote.setApainstPo(againstpo_id);
			materialRecNote.setPoId(poref_id);
			materialRecNote.setInvoiceNumber("");
			materialRecNote.setInvDate(dt);
			materialRecNote.setPoNo("");
			
			materialRecNote.setPoDate(po_date);
			materialRecNote.setUseridStores(0);
			materialRecNote.setStoresRemark(Remark);
			materialRecNote.setApprovedUserId(0);
			materialRecNote.setApprovalRemark("");
			materialRecNote.setStatus(status);
			
			System.out.println(materialRecNote.toString());
			
			for(int i=0;i<getmaterialRecNoteDetailslist.size();i++)
			{System.out.println(12);
				//MaterialRecNoteDetails materialRecNoteDetails=getmaterialRecNoteDetailslist.get(i);
				if(getmaterialRecNoteDetailslist.get(i).getMrnDetailId()!=0) {
					System.out.println(13);
					String stock_qty=request.getParameter("stockQty"+getmaterialRecNoteDetailslist.get(i).getMrnDetailId());
					String rejected_Qty=request.getParameter("rejectedQty"+getmaterialRecNoteDetailslist.get(i).getMrnDetailId());
					
					if(stock_qty!=null) {
						System.out.println("Stock Qty   :"+stock_qty);
						int stockQty=Integer.parseInt(stock_qty);
						getmaterialRecNoteDetailslist.get(i).setStockQty(stockQty);
						System.out.println("Stock Qty   :"+stockQty);
					}
					else
					{
						getmaterialRecNoteDetailslist.get(i).setStockQty(0);
					}
					if(stock_qty!=null) {
						System.out.println("Stock Qty   :"+stock_qty);
						int rejectedQty=Integer.parseInt(rejected_Qty);
						getmaterialRecNoteDetailslist.get(i).setStockQty(rejectedQty);
						System.out.println("Stock Qty   :"+rejectedQty);
					}
					else
					{
						getmaterialRecNoteDetailslist.get(i).setStockQty(0);
					}
					
					//System.out.println("Stock QTY :"+stockQty+"   REje  :"+rejectedQty);
					
					
					 
					System.out.println(2);
				}
			}
			materialRecNote.setMaterialRecNoteDetails(getmaterialRecNoteDetailslist);
			
			
			System.out.println(3);
			
			
			//-------------------------------------------------------
			RestTemplate rest=new RestTemplate();
			System.out.println("Update   "+materialRecNote.toString());
			materialRecNote=rest.postForObject(Constants.url + "/postMaterialRecNote",materialRecNote, MaterialRecNote.class);
		
			System.out.println("return   "+materialRecNote.toString());	
			
			 
			
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return "redirect:/showAllStoreMaterialReciept";
	}
	
		
	
	
	@RequestMapping(value = "/gateEntryList", method = RequestMethod.GET)
	public @ResponseBody List<MaterialRecNoteDetails> gateEntryList(HttpServletRequest request,
		HttpServletResponse response) {
		
		int rmId = Integer.parseInt(request.getParameter("rm_id"));
		int qty = Integer.parseInt(request.getParameter("rm_qty"));
		
		
		
		System.out.println(qty);
		RestTemplate rest=new RestTemplate();
		getRawMaterialDetailList=rest.getForObject(Constants.url +"rawMaterial/getAllRawMaterialList", GetRawMaterialDetailList.class);
		
	try
	{
		
		
		for(int i=0;i<getRawMaterialDetailList.getRawMaterialDetailsList().size();i++) {
			if(getRawMaterialDetailList.getRawMaterialDetailsList().get(i).getRmId()==rmId)
			{
				
				System.out.println("rawMaterialDetails  :"+getRawMaterialDetailList.getRawMaterialDetailsList().get(i).toString());
				MaterialRecNoteDetails materialRecNoteDetails = new MaterialRecNoteDetails();
				materialRecNoteDetails.setRmId(rmId);
				materialRecNoteDetails.setRmName(getRawMaterialDetailList.getRawMaterialDetailsList().get(i).getRmName());
				materialRecNoteDetails.setRecdQty(qty);
				materialRecNoteDetails.setMrnNo("");
				System.out.println(materialRecNoteDetails.toString());
				addmaterialRecNoteDetailslist.add(materialRecNoteDetails);
				
			}
		}
		System.out.println(addmaterialRecNoteDetailslist.size());
	}catch(Exception e)
	{
		e.printStackTrace();
		System.out.println(e.getMessage());
	}
		
		return addmaterialRecNoteDetailslist;
		
	}
	
	
	@RequestMapping(value = "/showStoreMaterialReciept", method = RequestMethod.GET)
	public ModelAndView showStoreMaterialReciept(HttpServletRequest request, HttpServletResponse response) {
	
		
		ModelAndView model = new ModelAndView("masters/materialReceiptStore");

		int mrnId=Integer.parseInt(request.getParameter("mrnId"));
	MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
	map.add("mrnId",mrnId);


	RestTemplate rest = new RestTemplate();
	materialRecNoteHeader = rest.postForObject(Constants.url + "/getMaterialRecNotesHeaderDetails",map, MaterialRecNote.class);
	getmaterialRecNoteDetailslist = materialRecNoteHeader.getMaterialRecNoteDetails();
	 
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
	
	
	@RequestMapping(value = "/withPoRef", method = RequestMethod.GET)
	public @ResponseBody List<MaterialRecNoteDetails> withPo(HttpServletRequest request,
		HttpServletResponse response) {
		
		
		try
		{
			System.out.println("in controller");
			int poId = Integer.parseInt(request.getParameter("poref_id"));
			System.out.println("poref_id"+poId);
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("poId", poId);
			
			
			RestTemplate rest = new RestTemplate();
			PurchaseOrderDetailedList purchaseOrderDetailedList = rest.postForObject(Constants.url + "purchaseOrder/purchaseorderdetailedList",map,PurchaseOrderDetailedList.class);
			  purchaseOrderDetailedListcomp=purchaseOrderDetailedList.getPurchaseOrderDetaillist();
			System.out.println("LISt  :"+purchaseOrderDetailedList.getPurchaseOrderDetaillist());
			
			
			List<MaterialRecNoteDetails> getmaterialRecNoteDetailslist2 = new ArrayList<MaterialRecNoteDetails>();
			
			int mrnId = 0;
			String mrnNo =null;
			for(int i=0;i<purchaseOrderDetailedListcomp.size();i++)
			{
				podate = purchaseOrderDetailedListcomp.get(i).getPoDate();
				PurchaseOrderDetail purchaseDetail = purchaseOrderDetailedListcomp.get(i);
				int poRmId=purchaseDetail.getRmId();
				System.out.println("purchaseDetaiil"+purchaseDetail.getPoRate());
				int Issame=0;
				System.out.println("poRmId  "+poRmId);
				
				for(int j=0;j<getmaterialRecNoteDetailslist.size();j++)
				{
					MaterialRecNoteDetails materialRecNoteDetails = getmaterialRecNoteDetailslist.get(j);
					
					int mrnRmId=materialRecNoteDetails.getRmId();
					System.out.println("mrnRmId  "+mrnRmId);
					mrnId=getmaterialRecNoteDetailslist.get(j).getMrnId();
					mrnNo=getmaterialRecNoteDetailslist.get(j).getMrnNo();
					if(poRmId==mrnRmId)
					{
						System.out.println("in if");
						
						getmaterialRecNoteDetailslist.get(j).setPoQty(purchaseDetail.getPoQty());
						System.out.println("old po rate:   "+ getmaterialRecNoteDetailslist.get(j).toString());
						getmaterialRecNoteDetailslist.get(j).setPoRate(purchaseDetail.getPoRate());
						
						System.out.println("new PoRate:   "+getmaterialRecNoteDetailslist.get(j).getPoQty());
						Issame=1;
						
					}
					
					
				}
				
				if(Issame==0)
				{
					MaterialRecNoteDetails materialRecNoteDetails = new MaterialRecNoteDetails();
					materialRecNoteDetails.setMrnDetailId(0);
					materialRecNoteDetails.setMrnId(mrnId);
					
					materialRecNoteDetails.setRmId(purchaseDetail.getRmId());
					materialRecNoteDetails.setRmName(purchaseDetail.getRmName());	
					materialRecNoteDetails.setPoRate(purchaseDetail.getPoRate());
					materialRecNoteDetails.setRmUom("");
					materialRecNoteDetails.setMrnNo(mrnNo);
					materialRecNoteDetails.setSupplierId(purchaseDetail.getSuppId());
					materialRecNoteDetails.setPoId(purchaseDetail.getPoId());
					materialRecNoteDetails.setPoQty(purchaseDetail.getPoQty());
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
					getmaterialRecNoteDetailslist2.add(materialRecNoteDetails);
					
				}
			}
			
			for(int i=0;i<getmaterialRecNoteDetailslist2.size();i++)
			{
				getmaterialRecNoteDetailslist.add(getmaterialRecNoteDetailslist2.get(i));
			}
			System.out.println(getmaterialRecNoteDetailslist.toString());
			
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return getmaterialRecNoteDetailslist;

	}
	
	@RequestMapping(value = "/showAllStoreMaterialReciept", method = RequestMethod.GET)
	public ModelAndView showAllStoreMaterialReciept(HttpServletRequest request, HttpServletResponse response) {
		/*Constants.mainAct = 17;
		Constants.subAct=184;
		*/
		ModelAndView model = new ModelAndView("masters/allStoreMaterialReciept");
		List<SupplierDetails> supplierDetailsList=new ArrayList<SupplierDetails>();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("status","0,2");
		
 
		RestTemplate rest = new RestTemplate();
		GetMaterialRecNoteList materialRecNoteList=rest.postForObject(Constants.url + "/getMaterialRecNotes",map, GetMaterialRecNoteList.class);
		System.out.println("materialRecNoteList  :"+materialRecNoteList.toString());
		
		supplierDetailsList = rest.getForObject(Constants.url + "/getAllSupplier", List.class);
		TransporterList transporterList = rest.getForObject(Constants.url + "/showTransporters",TransporterList.class);
		System.out.println("Supplier List :"+supplierDetailsList.toString());
 
		model.addObject("materialRecNoteList", materialRecNoteList.getMaterialRecNoteList());
		model.addObject("supplierDetailsList",supplierDetailsList);
		model.addObject("transportlist",transporterList.getTransporterList());
		return model;
	}
	
	
	@RequestMapping(value = "/withPoRefDate", method = RequestMethod.GET)
	public @ResponseBody List<PurchaseOrderDetail> withPoRefDate(HttpServletRequest request,
		HttpServletResponse response) {
		
		System.out.println(podate);
		return purchaseOrderDetailedListcomp;
	
	}
	
	@RequestMapping(value = "/allDirectorMaterialReceiptNote", method = RequestMethod.GET)
	public ModelAndView allMaterialReceiptNote(HttpServletRequest request, HttpServletResponse response) {
		/*Constants.mainAct = 17;
		Constants.subAct=184;*/
		
		ModelAndView model = new ModelAndView("masters/allMaterialReceiptNote");
		String viewAll=request.getParameter("viewAll");
		
		
		List<SupplierDetails> supplierDetailsList=new ArrayList<SupplierDetails>();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		 
		if(viewAll!=null) {
			map.add("status","0,1,2,3,4");
		}
		else
		map.add("status","1");
 
		RestTemplate rest = new RestTemplate();
		GetMaterialRecNoteList materialRecNoteList=rest.postForObject(Constants.url + "/getMaterialRecNotes",map, GetMaterialRecNoteList.class);
		System.out.println("materialRecNoteList  :"+materialRecNoteList.toString());
		
		supplierDetailsList = rest.getForObject(Constants.url + "/getAllSupplier", List.class);

		System.out.println("Supplier List :"+supplierDetailsList.toString());
		System.out.println("Final List Material  "+materialRecNoteList.getMaterialRecNoteList());
		model.addObject("materialRecNoteList", materialRecNoteList.getMaterialRecNoteList());
		model.addObject("supplierDetailsList",supplierDetailsList);
		return model;
		 
	}
	
	@RequestMapping(value = "/materialReceiptDirectore", method = RequestMethod.GET)
	public ModelAndView materialReceiptDirectore(HttpServletRequest request, HttpServletResponse response) {
		/*Constants.mainAct = 17;
		Constants.subAct=184;*/
		int mrnId=Integer.parseInt(request.getParameter("mrnId"));
		
		GetAllRemarksList getAllRemarksList=new GetAllRemarksList();
		MultiValueMap<String, Object> map=new LinkedMultiValueMap<String, Object>();
		map.add("mrnId", mrnId);
		  materialRecNote=new MaterialRecNote();
		ModelAndView model = new ModelAndView("masters/materialReceiptDirectore");
		try {
			RestTemplate rest = new RestTemplate();
		 
			materialRecNote=rest.postForObject(Constants.url + "/getMaterialRecNotesHeaderDetails",map, MaterialRecNote.class);
			System.out.println("materialRecNoteList  :"+materialRecNote.toString());
			
			Supplist supplierDetailsList =  rest.getForObject(Constants.url + "/getAllSupplierlist", Supplist.class);
			 
			System.out.println(supplierDetailsList.getSupplierDetailslist().toString());
			 
			int supId=materialRecNote.getSupplierId();
			
			for(int i=0;i<supplierDetailsList.getSupplierDetailslist().size();i++)
			{
				if(supId==supplierDetailsList.getSupplierDetailslist().get(i).getSuppId()) {
					System.out.println("SuppName1 :"+supplierDetailsList.getSupplierDetailslist().get(i).getSuppName());
				 
					model.addObject("suppName1", supplierDetailsList.getSupplierDetailslist().get(i).getSuppName());
				}
			}
			map=new LinkedMultiValueMap<String, Object>();
			map.add("isFrUsed", 0);
			map.add("moduleId", 1);
			map.add("subModuleId", 1); 
			  getAllRemarksList=rest.postForObject(Constants.url + "/getAllRemarks",map, GetAllRemarksList.class);
			  
			  List<RmItemGroup> rmItemGroupList=rest.getForObject(Constants.url + "rawMaterial/getAllRmItemGroup", List.class);
			  model.addObject("rmItemGroupList",rmItemGroupList);
			  
			  TransporterList transporterList=rest.getForObject(Constants.url + "showTransporters", TransporterList.class);
			  for(int i=0;i<transporterList.getTransporterList().size();i++)
				{
					if(materialRecNote.getTransportId()==transporterList.getTransporterList().get(i).getTranId()) {
						  
						model.addObject("transportName", transporterList.getTransporterList().get(i).getTranName());
					}
				}
			  DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			 // materialRecNote.setPoDate(dateFormat.format(materialRecNote.getPoDate());
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		model.addObject("imageUrl",materialRecNote);
		model.addObject("materialRecNoteHeader",materialRecNote);
		model.addObject("materialRecNoteDetail",materialRecNote.getMaterialRecNoteDetails());
		model.addObject("allRemarksList",getAllRemarksList.getGetAllRemarks());
		//model.addObject("supplierDetailsList",supplierDetailsList);
		return model;
	}
	
	@RequestMapping(value = "/submitMaterialReceiptDirector/{status}", method = RequestMethod.POST)
	public String submitMaterialReceiptDirector(@PathVariable int status, HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("Status : "+status);
		String[] statusList = request.getParameterValues("select_to_approve");
		String approvalRemark=request.getParameter("issue");
		MaterialRecNote materialRecNote1=materialRecNote;
				materialRecNote1.setStatus(status);
				materialRecNote1.setApprovalRemark(approvalRemark);
				
				if(statusList!=null)
		for(int j=0;j<statusList.length;j++) {
		System.out.println("Checked Item :"+statusList[j]);
			int statusId= Integer.parseInt(statusList[j]);
			
			for(int i=0;i<materialRecNote1.getMaterialRecNoteDetails().size();i++) {
				if(materialRecNote1.getMaterialRecNoteDetails().get(i).getMrnDetailId()==statusId)
				{
					System.out.println("Item "+materialRecNote1.getMaterialRecNoteDetails().get(i).getMrnDetailId());
					materialRecNote1.getMaterialRecNoteDetails().get(i).setStatus(1);
				}
				/*else {
					System.out.println("Item "+materialRecNote1.getMaterialRecNoteDetails().get(i).getMrnDetailId());
					materialRecNote1.getMaterialRecNoteDetails().get(i).setStatus(1);
				}*/
			}
		}
		try {
			
			RestTemplate rest = new RestTemplate();
			System.out.println("Before Update  :"+materialRecNote1.toString());
			materialRecNote=rest.postForObject(Constants.url + "postMaterialRecNote",materialRecNote1, MaterialRecNote.class); 
			System.out.println("After Update  :"+materialRecNote.toString());
		}catch (Exception e) {
			
			System.out.println(e.getMessage());
		}
		return "redirect:/allDirectorMaterialReceiptNote";
	}
	
	
	
	@RequestMapping(value = "/allMaterialRecieptAccList", method = RequestMethod.GET)
	public ModelAndView allMaterialRecieptAccList(HttpServletRequest request, HttpServletResponse response) {
		/*Constants.mainAct = 17;
		Constants.subAct=184;*/
		
		ModelAndView model = new ModelAndView("masters/allMaterialRecieptAccList");
		
		
		
		List<SupplierDetails> supplierDetailsList=new ArrayList<SupplierDetails>();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		 

		map.add("status","3,4");
 
		RestTemplate rest = new RestTemplate();
		GetMaterialRecNoteList materialRecNoteList=rest.postForObject(Constants.url + "/getMaterialRecNotes",map, GetMaterialRecNoteList.class);
		System.out.println("materialRecNoteList  :"+materialRecNoteList.toString());
		
		supplierDetailsList = rest.getForObject(Constants.url + "/getAllSupplier", List.class);

		System.out.println("Supplier List :"+supplierDetailsList.toString());
		System.out.println("materialRecNoteList  "+materialRecNoteList.getMaterialRecNoteList());
		model.addObject("materialRecNoteList", materialRecNoteList.getMaterialRecNoteList());
		model.addObject("supplierDetailsList",supplierDetailsList);
		return model;
		 
	}
	
}





