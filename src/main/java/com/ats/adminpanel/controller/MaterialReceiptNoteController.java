package com.ats.adminpanel.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.commons.DateConvertor;
import com.ats.adminpanel.commons.VpsImageUpload;
import com.ats.adminpanel.model.MRawMaterial;
import com.ats.adminpanel.model.MaterialRecieptAcc;
import com.ats.adminpanel.model.RawMaterial.GetRawMaterialDetailList;
import com.ats.adminpanel.model.RawMaterial.Info;
import com.ats.adminpanel.model.RawMaterial.RmItemGroup;
import com.ats.adminpanel.model.item.FrItemStockConfigureList;
import com.ats.adminpanel.model.materialreceipt.GetMaterialRecNoteList;
import com.ats.adminpanel.model.materialreceipt.GetMaterialReceiptByDate;
import com.ats.adminpanel.model.materialreceipt.GetTaxListByRmId;
import com.ats.adminpanel.model.materialreceipt.ItemRateByRmIdAndSuppId;
import com.ats.adminpanel.model.materialreceipt.MaterialRecNote;
import com.ats.adminpanel.model.materialreceipt.MaterialRecNoteDetails;
import com.ats.adminpanel.model.materialreceipt.Supplist;
import com.ats.adminpanel.model.purchaseorder.GetPurchaseOrderList;
import com.ats.adminpanel.model.purchaseorder.PurchaseOrderDetail;
import com.ats.adminpanel.model.purchaseorder.PurchaseOrderDetailedList;
import com.ats.adminpanel.model.purchaseorder.PurchaseOrderHeader;
import com.ats.adminpanel.model.remarks.GetAllRemarksList;
import com.ats.adminpanel.model.supplierMaster.SupplierDetails;
import com.ats.adminpanel.model.supplierMaster.TransporterList;

@Controller
@Scope("session")
public class MaterialReceiptNoteController {

	public GetRawMaterialDetailList getRawMaterialDetailList = new GetRawMaterialDetailList();
	public List<MRawMaterial> rawlist = new ArrayList<MRawMaterial>();
	public List<MaterialRecNoteDetails> materialRecNoteDetailslist = new ArrayList<MaterialRecNoteDetails>();
	public List<PurchaseOrderHeader> purchaseOrderHeaderlist = new ArrayList<PurchaseOrderHeader>();
	public List<PurchaseOrderDetail> purchaseOrderDetailList = new ArrayList<PurchaseOrderDetail>();
	MaterialRecNote materialRecNoteHeader;
	public MaterialRecNote materialRecNote;

	MaterialRecNote materialRecNotes;
	public List<MaterialRecNoteDetails> getmaterialRecNoteDetailslist = new ArrayList<MaterialRecNoteDetails>();
	public List<MaterialRecNoteDetails> addmaterialRecNoteDetailslist = new ArrayList<MaterialRecNoteDetails>(); 
	public List<PurchaseOrderDetail> purchaseOrderDetailedListcomp;
	List<MaterialRecieptAcc> materialRecieptAccList = new ArrayList<MaterialRecieptAcc>();

	@RequestMapping(value = "/gateEntries", method = RequestMethod.GET)
	public ModelAndView gateEntries(HttpServletRequest request, HttpServletResponse response) {
		Constants.mainAct=10;
		Constants.subAct =55;

		ModelAndView model = null ;
		try {
			model = new ModelAndView("masters/gateEntryList");
		List<SupplierDetails> supplierDetailsList = new ArrayList<SupplierDetails>();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("status", 0);

		RestTemplate rest = new RestTemplate();
		GetMaterialRecNoteList materialRecNoteList = rest.postForObject(Constants.url + "/getMaterialRecNotes", map,
				GetMaterialRecNoteList.class);
		System.out.println("materialRecNoteList  :" + materialRecNoteList.toString());

		supplierDetailsList = rest.getForObject(Constants.url + "/getAllSupplier", List.class);
		TransporterList transporterList = rest.getForObject(Constants.url + "/showTransporters", TransporterList.class);
		System.out.println("Supplier List :" + supplierDetailsList.toString());

		model.addObject("materialRecNoteList", materialRecNoteList.getMaterialRecNoteList());
		model.addObject("supplierDetailsList", supplierDetailsList);
		model.addObject("transportlist", transporterList.getTransporterList());
		}catch (Exception e) {
			System.out.println("Ex in  showing Gate Entry"+e.getMessage());
			e.printStackTrace();
		}
		return model;

	}
	
	@RequestMapping(value = "/allRecordwithDate", method = RequestMethod.GET)
	public @ResponseBody List<GetMaterialReceiptByDate> allRecordwithDate(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("hello");
		String fromDate =  request.getParameter("from_date");
		String toDate =  request.getParameter("to_date");
		int flag = Integer.parseInt(request.getParameter("flag"));
		List<GetMaterialReceiptByDate> getMaterialRecNoteList = new ArrayList<GetMaterialReceiptByDate>();
		System.out.println("fromDate"+fromDate);
		System.out.println("toDate"+toDate);
		System.out.println("flag"+flag);
		RestTemplate rest = new RestTemplate();
		String status=null;

		try {
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			if(flag==0)
			{
				status="0,1,2,3,4,5";
			}
			else if(flag==1)
			{
				status="0,1,2,3,4,5";
			}
			else if(flag==2)
			{
				status="1,2,3,4,5";
			}
			else if(flag==3)
			{
				status="3,4,5";
			}
			map.add("status", status);
			map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			map.add("toDate", DateConvertor.convertToYMD(toDate));
			map.add("flag", flag);
			System.out.println("map"+map);
			getMaterialRecNoteList = rest.postForObject(Constants.url + "/getAllMaterialRecNotes",map,
					List.class);
			System.out.println("getMaterialRecNoteList"+getMaterialRecNoteList);
			 
		} catch (Exception e) {
			e.printStackTrace();
			 
		}

		return getMaterialRecNoteList;

	}
	
	@RequestMapping(value = "/allRecordwithDateDetailed", method = RequestMethod.GET)
	public @ResponseBody ModelAndView allRecordwithDateDetailed(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("hello");
		int mrnId = Integer.parseInt(request.getParameter("mrnId"));
		System.out.println("mrnId"+mrnId);
		ModelAndView model = new ModelAndView("masters/detailedGateEntry");
		List<SupplierDetails> supplierDetailsList = new ArrayList<SupplierDetails>();
		RestTemplate rest = new RestTemplate(); 
		try {
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("mrnId", mrnId);
			MaterialRecNote materialRecNote = rest.postForObject(Constants.url + "/getMaterialRecNotesHeaderDetails", map,
					MaterialRecNote.class);
			
			supplierDetailsList = rest.getForObject(Constants.url + "/getAllSupplier", List.class);
			TransporterList transporterList = rest.getForObject(Constants.url + "/showTransporters", TransporterList.class);
			System.out.println("materialRecNote"+materialRecNote);
			model.addObject("materialRecNote",materialRecNote);
			model.addObject("supplierDetailsList",supplierDetailsList);
			model.addObject("transporterList",transporterList.getTransporterList());
			model.addObject("imageUrl",Constants.GATE_ENTRY_IMG_URL);
		} catch (Exception e) {
			e.printStackTrace();
			 
		}

		return model;

	}
	
	@RequestMapping(value = "/allRecordwithDateAccDetailed", method = RequestMethod.GET)
	public @ResponseBody ModelAndView allRecordwithDateAccDetailed(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("hello");
		int mrnId = Integer.parseInt(request.getParameter("mrnId"));
		System.out.println("mrnId"+mrnId);
		ModelAndView model = new ModelAndView("masters/detailedGateEntryAcc");
		List<SupplierDetails> supplierDetailsList = new ArrayList<SupplierDetails>();
		RestTemplate rest = new RestTemplate(); 
		try {
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("mrnId", mrnId);
			MaterialRecNote materialRecNote = rest.postForObject(Constants.url + "/getMaterialRecNotesHeaderDetails", map,
					MaterialRecNote.class);
			
			supplierDetailsList = rest.getForObject(Constants.url + "/getAllSupplier", List.class);
			TransporterList transporterList = rest.getForObject(Constants.url + "/showTransporters", TransporterList.class);
			System.out.println("materialRecNote"+materialRecNote);
			model.addObject("materialRecNote",materialRecNote);
			model.addObject("supplierDetailsList",supplierDetailsList);
			model.addObject("transporterList",transporterList.getTransporterList());
			 model.addObject("imageUrl",Constants.GATE_ENTRY_IMG_URL);
		} catch (Exception e) {
			e.printStackTrace();
			 
		}

		return model;

	}

	@RequestMapping(value = "/addGateEntry", method = RequestMethod.GET)
	public ModelAndView addGateEntry(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/insertGateEntry");

		addmaterialRecNoteDetailslist = new ArrayList<MaterialRecNoteDetails>();
		System.out.println(rawlist);
		try {
			RestTemplate rest = new RestTemplate();
			List<SupplierDetails> supplierDetailsList = rest.getForObject(Constants.url + "/getAllSupplier",
					List.class);

			TransporterList transporterList = rest.getForObject(Constants.url + "/showTransporters",
					TransporterList.class);

			getRawMaterialDetailList = rest.getForObject(Constants.url + "rawMaterial/getAllRawMaterialList",
					GetRawMaterialDetailList.class);
			String key = "mrn_no";

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("key", key);

			model.addObject("supplierList", supplierDetailsList);

			model.addObject("rmlist", getRawMaterialDetailList.getRawMaterialDetailsList());
			model.addObject("transporterList", transporterList.getTransporterList());

			Integer value = rest.postForObject(Constants.url + "/findvaluebykey", map, Integer.class);

			System.out.println("value = " + value);
			model.addObject("mrnno", String.valueOf(value));

		} catch (Exception e) {
			System.out.println("Exception In Add Gate Entry :" + e.getMessage());

		}

		return model;
	}

	@RequestMapping(value = "/materialReceiptStore", method = RequestMethod.POST)
	public String materialReceiptStore(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("image1") List<MultipartFile> image1, @RequestParam("image2") List<MultipartFile> image2) {
		/*
		 * Constants.mainAct = 17; Constants.subAct=184;
		 */
		RestTemplate rest = new RestTemplate();
		materialRecNoteDetailslist = new ArrayList<>();

		/*
		 * java.util.Date utildate = new java.util.Date(); java.sql.Date date = new
		 * Date(utildate.getTime());
		 */

		VpsImageUpload upload = new VpsImageUpload();

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		System.out.println(sdf.format(cal.getTime()));

		String curTimeStamp = sdf.format(cal.getTime());
		String img1 = null, img2 = null;
		try {
			img1 = curTimeStamp + "-" + image1.get(0).getOriginalFilename();
			img2 = curTimeStamp + "-" + image2.get(0).getOriginalFilename();

			upload.saveUploadedFiles(image1, Constants.GATE_ENTRY_IMAGE_TYPE,
					curTimeStamp + "-" + image1.get(0).getOriginalFilename());
			upload.saveUploadedFiles(image2, Constants.GATE_ENTRY_IMAGE_TYPE,
					curTimeStamp + "-" + image2.get(0).getOriginalFilename());

			System.out.println("upload method called for image Upload " + image1.toString());

		} catch (IOException e) {

			System.out.println("Exce in File Upload In GATE ENTRY  Insert " + e.getMessage());
			e.printStackTrace();
		}

		int sup_id = Integer.parseInt(request.getParameter("supp_id"));
		String mrn_no = request.getParameter("mrn_no");
		String vehino = request.getParameter("vehicle_no");
		String lrno = request.getParameter("lr_no");
		int tran_id = Integer.parseInt(request.getParameter("tran_id"));
		int no_of_items = Integer.parseInt(request.getParameter("no_of_items"));
		String remark = request.getParameter("remark");
		String photo1 = request.getParameter("image1");
		String photo2 = request.getParameter("image2");
		String nowDate = request.getParameter("nowDate");
		System.out.println("NOW data   dfghjjj :" + nowDate);

		System.out.println(sup_id + vehino + lrno + tran_id + no_of_items + remark + photo1 + photo2);

		MaterialRecNote materialRecNote = new MaterialRecNote();

		try {
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			String settingKey = new String();
			settingKey = "mrn_no";
			map.add("settingKeyList", settingKey);
			FrItemStockConfigureList settingList = rest.postForObject(Constants.url + "getDeptSettingValue", map,
					FrItemStockConfigureList.class);
			mrn_no=String.valueOf(settingList.getFrItemStockConfigure().get(0).getSettingValue());
			System.out.println("mrn_no"+mrn_no);
			
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
			materialRecNote.setPhoto1(img1);
			materialRecNote.setPhoto2(img2);
			materialRecNote.setGateUserId(1);
			materialRecNote.setGateRemark(remark);
			// --------------------------------------------
			materialRecNote.setMrnStoreDate(dt);
			materialRecNote.setMrnType(0);
			materialRecNote.setApainstPo(0);
			materialRecNote.setPoId(0);
			materialRecNote.setPoNo("");
			materialRecNote.setPoDate("");
			materialRecNote.setUseridStores(0);
			materialRecNote.setStoresRemark("");
			materialRecNote.setApprovedUserId(0);
			materialRecNote.setApprovalRemark("");
			// --------------------------------------------
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
			// -----------------------------------------
			materialRecNote.setStatus(0);
			materialRecNote.setDelStatus(0);
			materialRecNote.setIsTallySync(0);

			for (int i = 0; i < addmaterialRecNoteDetailslist.size(); i++) {
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
			
			materialRecNotes = rest.postForObject(Constants.url + "/postMaterialRecNote", materialRecNote,
					MaterialRecNote.class);// enter first form
			if(materialRecNotes!=null)
			{
				int value = rest.getForObject(Constants.url + "/updateValuekey", Integer.class);
				System.out.println("return value " + value);
			}
			
			

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();

		}

		return "redirect:/gateEntries";
	}

	@RequestMapping(value = "/gateEntryList", method = RequestMethod.GET)
	public @ResponseBody List<MaterialRecNoteDetails> gateEntryList(HttpServletRequest request,
			HttpServletResponse response) {

		int rmId = Integer.parseInt(request.getParameter("rm_id"));
		int qty = Integer.parseInt(request.getParameter("rm_qty"));

		System.out.println(qty);
		RestTemplate rest = new RestTemplate();
		getRawMaterialDetailList = rest.getForObject(Constants.url + "rawMaterial/getAllRawMaterialList",
				GetRawMaterialDetailList.class);

		try {

			for (int i = 0; i < getRawMaterialDetailList.getRawMaterialDetailsList().size(); i++) {
				if (getRawMaterialDetailList.getRawMaterialDetailsList().get(i).getRmId() == rmId) {

					System.out.println("rawMaterialDetails  :"
							+ getRawMaterialDetailList.getRawMaterialDetailsList().get(i).toString());
					MaterialRecNoteDetails materialRecNoteDetails = new MaterialRecNoteDetails();
					materialRecNoteDetails.setRmId(rmId);
					materialRecNoteDetails
							.setRmName(getRawMaterialDetailList.getRawMaterialDetailsList().get(i).getRmName());
					materialRecNoteDetails.setRecdQty(qty);
					materialRecNoteDetails.setMrnNo("");
					materialRecNoteDetails.setRmUom("");
					System.out.println(materialRecNoteDetails.toString());
					addmaterialRecNoteDetailslist.add(materialRecNoteDetails);

				}
			}
			System.out.println(addmaterialRecNoteDetailslist.size());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return addmaterialRecNoteDetailslist;

	}

	@RequestMapping(value = "/editRmQtyOnGate", method = RequestMethod.GET)
	public @ResponseBody List<MaterialRecNoteDetails> editRmQtyOnGate(HttpServletRequest request,
			HttpServletResponse response) {

		int index = Integer.parseInt(request.getParameter("index"));
		int updateQty = Integer.parseInt(request.getParameter("updateQty"));
		System.out.println("index" + index);
		System.out.println("updateQty" + updateQty);

		try {

			for (int i = 0; i < addmaterialRecNoteDetailslist.size(); i++) {
				if (i == index) {

					addmaterialRecNoteDetailslist.get(index).setRecdQty(updateQty);
				}
			}
			System.out.println(addmaterialRecNoteDetailslist.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return null;

	}

	@RequestMapping(value = "/deleteRmItem", method = RequestMethod.GET)
	public @ResponseBody List<MaterialRecNoteDetails> deleteRmItem(HttpServletRequest request,
			HttpServletResponse response) {

		int index = Integer.parseInt(request.getParameter("index"));

		try {

			for (int i = 0; i < addmaterialRecNoteDetailslist.size(); i++) {
				if (i == index) {
					addmaterialRecNoteDetailslist.remove(i);
				}
			}
			System.out.println(addmaterialRecNoteDetailslist.size());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return addmaterialRecNoteDetailslist;

	}

	int flag=0;
	@RequestMapping(value = "/editGateEntry", method = RequestMethod.GET)
	public ModelAndView editStoreMaterialReciept(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/editGateEntry");
		addmaterialRecNoteDetailslist = new ArrayList<MaterialRecNoteDetails>();

		flag = Integer.parseInt(request.getParameter("flag"));
		int mrnId = Integer.parseInt(request.getParameter("mrnId"));
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("mrnId", mrnId);
		System.out.println("flag"+flag);

		RestTemplate rest = new RestTemplate();
		materialRecNoteHeader = rest.postForObject(Constants.url + "/getMaterialRecNotesHeaderDetails", map,
				MaterialRecNote.class);
		getmaterialRecNoteDetailslist = materialRecNoteHeader.getMaterialRecNoteDetails();

		System.out.println("material detailed   :" + materialRecNoteHeader.getMaterialRecNoteDetails());

		TransporterList transporterList = rest.getForObject(Constants.url + "/showTransporters", TransporterList.class);

		Supplist supplierDetailsList = rest.getForObject(Constants.url + "/getAllSupplierlist", Supplist.class);

		System.out.println(supplierDetailsList.getSupplierDetailslist().toString());

		GetRawMaterialDetailList getRawMaterialDetail = rest
				.getForObject(Constants.url + "rawMaterial/getAllRawMaterialList", GetRawMaterialDetailList.class);

		model.addObject("materialRecNote", materialRecNoteHeader);
		model.addObject("materialRecNoteDetail", getmaterialRecNoteDetailslist);
		model.addObject("supplierList", supplierDetailsList.getSupplierDetailslist());
		model.addObject("transport", transporterList.getTransporterList());
		model.addObject("rmlist", getRawMaterialDetail.getRawMaterialDetailsList());
		model.addObject("flag",flag);
		
		model.addObject("imageUrl",Constants.GATE_ENTRY_IMG_URL);
		return model;
	}

	@RequestMapping(value = "/addRmitemInEditGateEntry", method = RequestMethod.GET)
	public @ResponseBody List<MaterialRecNoteDetails> addRmitemInEditGateEntry(HttpServletRequest request,
			HttpServletResponse response) {

		int rmId = Integer.parseInt(request.getParameter("rm_id"));
		int qty = Integer.parseInt(request.getParameter("rm_qty"));

		System.out.println(qty);
		RestTemplate rest = new RestTemplate();
		getRawMaterialDetailList = rest.getForObject(Constants.url + "rawMaterial/getAllRawMaterialList",
				GetRawMaterialDetailList.class);

		try {

			for (int i = 0; i < getRawMaterialDetailList.getRawMaterialDetailsList().size(); i++) {
				if (getRawMaterialDetailList.getRawMaterialDetailsList().get(i).getRmId() == rmId) {

					System.out.println("rawMaterialDetails  :"
							+ getRawMaterialDetailList.getRawMaterialDetailsList().get(i).toString());
					MaterialRecNoteDetails materialRecNoteDetails = new MaterialRecNoteDetails();
					materialRecNoteDetails.setRmId(rmId);
					materialRecNoteDetails
							.setRmName(getRawMaterialDetailList.getRawMaterialDetailsList().get(i).getRmName());
					materialRecNoteDetails.setRecdQty(qty);
					materialRecNoteDetails.setMrnNo("");
					materialRecNoteDetails.setRmUom("");
					System.out.println(materialRecNoteDetails.toString());
					getmaterialRecNoteDetailslist.add(materialRecNoteDetails);

				}
			}
			System.out.println(getmaterialRecNoteDetailslist.size());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return getmaterialRecNoteDetailslist;

	}

	@RequestMapping(value = "/editRmQtyinEditGate", method = RequestMethod.GET)
	public @ResponseBody List<MaterialRecNoteDetails> editRmQtyinEditGate(HttpServletRequest request,
			HttpServletResponse response) {

		int index = Integer.parseInt(request.getParameter("index"));
		int updateQty = Integer.parseInt(request.getParameter("updateQty"));
		System.out.println("index" + index);
		System.out.println("updateQty" + updateQty);

		try {

			for (int i = 0; i < getmaterialRecNoteDetailslist.size(); i++) {
				if (i == index) {

					getmaterialRecNoteDetailslist.get(index).setRecdQty(updateQty);
				}
			}
			System.out.println(getmaterialRecNoteDetailslist.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return null;

	}

	@RequestMapping(value = "/deleteRmIteminEditGate", method = RequestMethod.GET)
	public @ResponseBody List<MaterialRecNoteDetails> deleteRmIteminEditGate(HttpServletRequest request,
			HttpServletResponse response) {

		int index = Integer.parseInt(request.getParameter("index"));

		try {

			for (int i = 0; i < getmaterialRecNoteDetailslist.size(); i++) {
				if (i == index) {
					if (getmaterialRecNoteDetailslist.get(i).getMrnDetailId() != 0) {
						getmaterialRecNoteDetailslist.get(i).setDelStatus(1);

					} else {
						getmaterialRecNoteDetailslist.remove(i);
					}

				}
			}
			System.out.println(getmaterialRecNoteDetailslist.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return getmaterialRecNoteDetailslist;

	}

	@RequestMapping(value = "/submitEditGateEntry", method = RequestMethod.POST)
	public String submitEditGateEntry(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("image1") List<MultipartFile> image1, @RequestParam("image2") List<MultipartFile> image2) {

		int sup_id = Integer.parseInt(request.getParameter("supp_id"));
		String mrn_no = request.getParameter("mrn_no");
		String vehino = request.getParameter("vehicle_no");
		String lrno = request.getParameter("lr_no");
		int tran_id = Integer.parseInt(request.getParameter("tran_id"));
		int no_of_items = Integer.parseInt(request.getParameter("no_of_items"));
		String remark = request.getParameter("remark");
		String photo1 = request.getParameter("image1");
		String photo2 = request.getParameter("image2");

		String prevImage1 = request.getParameter("prevImage1");
		String prevImage2 = request.getParameter("prevImage2");

		MaterialRecNote materialRecNote = materialRecNoteHeader;

		if (!image1.get(0).getOriginalFilename().equalsIgnoreCase("")) {
			VpsImageUpload upload = new VpsImageUpload();

			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			System.out.println(sdf.format(cal.getTime()));


			String curTimeStamp = sdf.format(cal.getTime());
			 String img1 = null;
			try {
				prevImage1=curTimeStamp + "-" + image1.get(0).getOriginalFilename();

				upload.saveUploadedFiles(image1, Constants.GATE_ENTRY_IMAGE_TYPE, curTimeStamp + "-" + image1.get(0).getOriginalFilename());

				System.out.println("upload method called for image Upload " + image1.toString());
				
			} catch (IOException e) {
				
				System.out.println("Exce in File Upload In GATE ENTRY  Insert " + e.getMessage());
				e.printStackTrace();
			}
			
		}

		if (!image2.get(0).getOriginalFilename().equalsIgnoreCase("")) {
			
			VpsImageUpload upload = new VpsImageUpload();

			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			System.out.println(sdf.format(cal.getTime()));


			String curTimeStamp = sdf.format(cal.getTime());
			 String img2=null;
			try {
				prevImage2=curTimeStamp + "-" + image2.get(0).getOriginalFilename();

				upload.saveUploadedFiles(image2, Constants.GATE_ENTRY_IMAGE_TYPE, curTimeStamp + "-" + image2.get(0).getOriginalFilename());

				System.out.println("upload method called for image Upload " + image1.toString());
				
			} catch (IOException e) {
				
				System.out.println("Exce in File Upload In GATE ENTRY  Insert " + e.getMessage());
				e.printStackTrace();
			}

		}

		System.out.println("reEnter value : " + sup_id + mrn_no + vehino + lrno + tran_id + no_of_items + remark
				+ photo1 + photo2);

		try {

			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date date = new Date();
			System.out.println(dateFormat.format(date));

			SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
			Date dt = sf.parse(sf.format(new Date()));

			// --------------------------------------------
			materialRecNote.setMrnId(materialRecNote.getMrnId());
			materialRecNote.setMrnNo(mrn_no);
			materialRecNote.setTransportId(tran_id);
			materialRecNote.setVehicleNo(vehino);
			materialRecNote.setLrNo(lrno);
			materialRecNote.setSupplierId(sup_id);
			materialRecNote.setNoOfItem(no_of_items);
			materialRecNote.setPhoto1(prevImage1);
			materialRecNote.setPhoto2(prevImage2);
			materialRecNote.setGateUserId(1);
			materialRecNote.setGateRemark(remark);
			materialRecNote.setPoDate("");

			System.out.println("SEt header data" + materialRecNote.toString());
			for (int i = 0; i < getmaterialRecNoteDetailslist.size(); i++) {
				getmaterialRecNoteDetailslist.get(i).setMrnNo(mrn_no);
				getmaterialRecNoteDetailslist.get(i).setSupplierId(sup_id);
			}

			materialRecNote.setMaterialRecNoteDetails(getmaterialRecNoteDetailslist);

			System.out.println(materialRecNote.getMaterialRecNoteDetails());

			// -------------------------------------------------------
			RestTemplate rest = new RestTemplate();
			System.out.println("EditGateEntry   " + materialRecNote.toString());
			materialRecNote = rest.postForObject(Constants.url + "/postMaterialRecNote", materialRecNote,
					MaterialRecNote.class);

			System.out.println("returnEditGateEntry   " + materialRecNote.toString());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		String ret=null;
		if(flag==0)
		{
			ret="redirect:/gateEntries";
		}
		else if(flag==1)
		{
			ret="redirect:/showAllStoreMaterialReciept";
		}
		return ret;
	}

	@RequestMapping(value = "/submitMaterialStore", method = RequestMethod.POST)
	public String submitMaterialStore(HttpServletRequest request, HttpServletResponse response) {
		/*
		 * Constants.mainAct = 17; Constants.subAct=184;
		 */

		int mrnType = Integer.parseInt(request.getParameter("mrntype"));
		int againstpo_id = 0;
		int poref_id = 0;
		String po_no=request.getParameter("po_no");
		if (request.getParameter("po_id") == "") {
			againstpo_id = 2;
		} else {
			againstpo_id = Integer.parseInt(request.getParameter("po_id"));
		}

		if (request.getParameter("poref_id") == null) {
			poref_id = 0;
		} else {
			poref_id = Integer.parseInt(request.getParameter("poref_id"));
		}

		String po_date;
		String Remark = request.getParameter("Remark");
		if (request.getParameter("po_date") == null) {

			Date todaysDate = new Date();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

			String testDateString = df.format(todaysDate);
			po_date = "";
			System.out.println("po_date in null" + po_date);
		} else {
			po_date = request.getParameter("po_date");
			System.out.println("po_date" + po_date);
		}

		int status = 1;
		MaterialRecNote materialRecNote = materialRecNoteHeader;

		System.out.println(mrnType + againstpo_id + poref_id + Remark);
		try {

			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date date = new Date();
			System.out.println(dateFormat.format(date));

			SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
			Date dt = sf.parse(sf.format(new Date()));

			// --------------------------------------------
			materialRecNote.setMrnStoreDate(dt);
			materialRecNote.setMrnType(mrnType);
			materialRecNote.setApainstPo(againstpo_id);
			materialRecNote.setPoId(poref_id);
			materialRecNote.setInvoiceNumber("");
			materialRecNote.setInvDate(dt);
			materialRecNote.setPoNo(po_no);

			materialRecNote.setPoDate(po_date);
			materialRecNote.setUseridStores(0);
			materialRecNote.setStoresRemark(Remark);
			materialRecNote.setApprovedUserId(0);
			materialRecNote.setApprovalRemark("");
			materialRecNote.setStatus(status);

			System.out.println(materialRecNote.toString());

			for (int i = 0; i < getmaterialRecNoteDetailslist.size(); i++) {
				System.out.println(12);
				// MaterialRecNoteDetails
				// materialRecNoteDetails=getmaterialRecNoteDetailslist.get(i);
				//if (getmaterialRecNoteDetailslist.get(i).getMrnDetailId() != 0) {
					System.out.println(13);
					String stock_qty = request
							.getParameter("stockQty" + i);
					String rejected_Qty = request
							.getParameter("rejectedQty" + i);

					if (stock_qty != null) {
						System.out.println("Stock Qty   :" + stock_qty);
						int stockQty = Integer.parseInt(stock_qty);
						getmaterialRecNoteDetailslist.get(i).setStockQty(stockQty);
						System.out.println("Stock Qty   :" + stockQty);
					} else {
						getmaterialRecNoteDetailslist.get(i).setStockQty(0);
					}
					if (rejected_Qty != null) {
						System.out.println("rejectedQty   :" + rejected_Qty);
						int rejectedQty = Integer.parseInt(rejected_Qty);
						getmaterialRecNoteDetailslist.get(i).setRejectedQty(rejectedQty);
						System.out.println("rejectedQty   :" + rejectedQty);
					} else {
						getmaterialRecNoteDetailslist.get(i).setRejectedQty(0);
					}

					getmaterialRecNoteDetailslist.get(i)
							.setValue((getmaterialRecNoteDetailslist.get(i).getStockQty()
									+ getmaterialRecNoteDetailslist.get(i).getRejectedQty())
									* getmaterialRecNoteDetailslist.get(i).getPoRate());

					System.out.println(2);
				//}
			}
			materialRecNote.setMaterialRecNoteDetails(getmaterialRecNoteDetailslist);

			System.out.println(3);

			// -------------------------------------------------------
			RestTemplate rest = new RestTemplate();
			System.out.println("Update   " + materialRecNote.toString());
			materialRecNote = rest.postForObject(Constants.url + "/postMaterialRecNote", materialRecNote,
					MaterialRecNote.class);

			System.out.println("return   " + materialRecNote.toString());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "redirect:/showAllStoreMaterialReciept";
	}
	int mrnIdStore=0;
	@RequestMapping(value = "/showStoreMaterialReciept", method = RequestMethod.GET)
	public ModelAndView showStoreMaterialReciept(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/materialReceiptStore");
		try
		{
			mrnIdStore = Integer.parseInt(request.getParameter("mrnId"));
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("mrnId", mrnIdStore);

			RestTemplate rest = new RestTemplate();
			materialRecNoteHeader = rest.postForObject(Constants.url + "/getMaterialRecNotesHeaderDetails", map,
					MaterialRecNote.class);
			getmaterialRecNoteDetailslist = materialRecNoteHeader.getMaterialRecNoteDetails();

			System.out.println("purchaseOrderListDetailedList   :" + materialRecNoteHeader.getMaterialRecNoteDetails());

			List<RmItemGroup> rmItemGroupList = rest.getForObject(Constants.url + "rawMaterial/getAllRmItemGroup",
					List.class);
			model.addObject("mrntype", rmItemGroupList);

			TransporterList transporterList = rest.getForObject(Constants.url + "/showTransporters", TransporterList.class);

			for (int i = 0; i < transporterList.getTransporterList().size(); i++) {
				if (transporterList.getTransporterList().get(i).getTranId() == materialRecNoteHeader.getTransportId()) {
					model.addObject("transname", transporterList.getTransporterList().get(i).getTranName());
					break;
				}
			}
			Supplist supplierDetailsList = rest.getForObject(Constants.url + "/getAllSupplierlist", Supplist.class);

			System.out.println(supplierDetailsList.getSupplierDetailslist().toString());

			int suupId = materialRecNoteHeader.getSupplierId();

			for (int i = 0; i < supplierDetailsList.getSupplierDetailslist().size(); i++) {
				if (suupId == supplierDetailsList.getSupplierDetailslist().get(i).getSuppId())
					model.addObject("suppName", supplierDetailsList.getSupplierDetailslist().get(i).getSuppName());
			}

			MultiValueMap<String, Object> map1 = new LinkedMultiValueMap<String, Object>();
			map1.add("suppId", suupId);
			System.out.println("SupplierId" + suupId);

			GetPurchaseOrderList getPurchaseOrderList = rest
					.postForObject(Constants.url + "purchaseOrder/purchaseorderList", map1, GetPurchaseOrderList.class);

			System.out.println(getPurchaseOrderList.toString());

			model.addObject("polist", purchaseOrderHeaderlist);
			model.addObject("rawlist", rawlist);
			model.addObject("materialRecNote", materialRecNoteHeader);
			model.addObject("materialRecNoteDetail", materialRecNoteHeader.getMaterialRecNoteDetails());
			model.addObject("purchaseOrderList", getPurchaseOrderList.getPurchaseOrderHeaderList());
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		
		return model;
	}

	@RequestMapping(value = "/withPoRef", method = RequestMethod.GET)
	public @ResponseBody List<MaterialRecNoteDetails> withPo(HttpServletRequest request, HttpServletResponse response) {

		try {
			
			purchaseOrderDetailedListcomp = new ArrayList<PurchaseOrderDetail>();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("mrnId", mrnIdStore);

			RestTemplate rest = new RestTemplate();
			materialRecNoteHeader = rest.postForObject(Constants.url + "/getMaterialRecNotesHeaderDetails", map,
					MaterialRecNote.class);
			getmaterialRecNoteDetailslist = materialRecNoteHeader.getMaterialRecNoteDetails();
			
			 System.out.println("initial size "+getmaterialRecNoteDetailslist.size());
			int poId = Integer.parseInt(request.getParameter("poref_id"));
			System.out.println("poref_id" + poId);

			 map = new LinkedMultiValueMap<String, Object>();
			map.add("poId", poId);

			 
			PurchaseOrderDetailedList purchaseOrderDetailedList = rest.postForObject(
					Constants.url + "purchaseOrder/purchaseorderdetailedList", map, PurchaseOrderDetailedList.class);
			purchaseOrderDetailedListcomp = purchaseOrderDetailedList.getPurchaseOrderDetaillist();
			System.out.println("LISt  :" + purchaseOrderDetailedList.getPurchaseOrderDetaillist());

			List<MaterialRecNoteDetails> getmaterialRecNoteDetailslist2 = new ArrayList<MaterialRecNoteDetails>();

			int mrnId = 0;
			String mrnNo = null;
			for (int i = 0; i < purchaseOrderDetailedListcomp.size(); i++) { 
				PurchaseOrderDetail purchaseDetail = purchaseOrderDetailedListcomp.get(i);
				int poRmId = purchaseDetail.getRmId();
				System.out.println("purchaseDetaiil" + purchaseDetail.getPoRate());
				int Issame = 0;
				System.out.println("poRmId  " + poRmId);

				for (int j = 0; j < getmaterialRecNoteDetailslist.size(); j++) {
					MaterialRecNoteDetails materialRecNoteDetails = getmaterialRecNoteDetailslist.get(j);

					int mrnRmId = materialRecNoteDetails.getRmId();
					System.out.println("mrnRmId  " + mrnRmId);
					mrnId = getmaterialRecNoteDetailslist.get(j).getMrnId();
					mrnNo = getmaterialRecNoteDetailslist.get(j).getMrnNo();
					if (poRmId == mrnRmId) {
						System.out.println("in if");

						getmaterialRecNoteDetailslist.get(j).setPoQty(purchaseDetail.getPoQty());
						System.out.println("old po rate:   " + getmaterialRecNoteDetailslist.get(j).toString());
						getmaterialRecNoteDetailslist.get(j).setPoRate(purchaseDetail.getPoRate());
						getmaterialRecNoteDetailslist.get(j).setPoId(purchaseDetail.getPoId());

						System.out.println("new PoRate:   " + getmaterialRecNoteDetailslist.get(j).getPoQty());
						Issame = 1;

					}

				}

				if (Issame == 0) {
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

			for (int i = 0; i < getmaterialRecNoteDetailslist2.size(); i++) {
				getmaterialRecNoteDetailslist.add(getmaterialRecNoteDetailslist2.get(i));
			}
			System.out.println(getmaterialRecNoteDetailslist.toString());

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return getmaterialRecNoteDetailslist;

	}
	
	@RequestMapping(value = "/withoutPo", method = RequestMethod.GET)
	public @ResponseBody List<MaterialRecNoteDetails> withoutPo(HttpServletRequest request, HttpServletResponse response) {

		try {
			System.out.println("whithout po");
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("mrnId", mrnIdStore);

			RestTemplate rest = new RestTemplate();
			materialRecNoteHeader = rest.postForObject(Constants.url + "/getMaterialRecNotesHeaderDetails", map,
					MaterialRecNote.class);
			getmaterialRecNoteDetailslist = materialRecNoteHeader.getMaterialRecNoteDetails();
			 
			 
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return getmaterialRecNoteDetailslist;

	}
	@RequestMapping(value = "/withPoRefDate", method = RequestMethod.GET)
	public @ResponseBody List<PurchaseOrderDetail> withPoRefDate(HttpServletRequest request,
			HttpServletResponse response) {

		System.out.println("poDate"+purchaseOrderDetailedListcomp.get(0).getPoDate());
		System.out.println("poDate"+purchaseOrderDetailedListcomp.get(0).getPoNo());
		purchaseOrderDetailedListcomp.get(0).setPoDate(DateConvertor.convertToDMY(purchaseOrderDetailedListcomp.get(0).getPoDate()));
		return purchaseOrderDetailedListcomp;

	}

	@RequestMapping(value = "/showAllStoreMaterialReciept", method = RequestMethod.GET)
	public ModelAndView showAllStoreMaterialReciept(HttpServletRequest request, HttpServletResponse response) {
		  Constants.mainAct =10; Constants.subAct=61;
		ModelAndView model = new ModelAndView("masters/allStoreMaterialReciept");
		List<SupplierDetails> supplierDetailsList = new ArrayList<SupplierDetails>();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("status", "0,2");

		RestTemplate rest = new RestTemplate();
		GetMaterialRecNoteList materialRecNoteList = rest.postForObject(Constants.url + "/getMaterialRecNotes", map,
				GetMaterialRecNoteList.class);
		System.out.println("materialRecNoteList  :" + materialRecNoteList.toString());

		supplierDetailsList = rest.getForObject(Constants.url + "/getAllSupplier", List.class);
		TransporterList transporterList = rest.getForObject(Constants.url + "/showTransporters", TransporterList.class);
		System.out.println("Supplier List :" + supplierDetailsList.toString());

		model.addObject("materialRecNoteList", materialRecNoteList.getMaterialRecNoteList());
		model.addObject("supplierDetailsList", supplierDetailsList);
		model.addObject("transportlist", transporterList.getTransporterList());
		return model;
	}

	

	@RequestMapping(value = "/allDirectorMaterialReceiptNote", method = RequestMethod.GET)
	public ModelAndView allMaterialReceiptNote(HttpServletRequest request, HttpServletResponse response) {
		Constants.mainAct =21; 
		 Constants.subAct=104;

		ModelAndView model = new ModelAndView("masters/allMaterialReceiptNote");
		String viewAll = request.getParameter("viewAll");

		List<SupplierDetails> supplierDetailsList = new ArrayList<SupplierDetails>();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		if (viewAll != null) {
			map.add("status", "0,1,2,3,4");
		} else
			map.add("status", "1");

		RestTemplate rest = new RestTemplate();
		GetMaterialRecNoteList materialRecNoteList = rest.postForObject(Constants.url + "/getMaterialRecNotes", map,
				GetMaterialRecNoteList.class);
		System.out.println("materialRecNoteList  :" + materialRecNoteList.toString());

		supplierDetailsList = rest.getForObject(Constants.url + "/getAllSupplier", List.class);

		System.out.println("Supplier List :" + supplierDetailsList.toString());
		System.out.println("Final List Material  " + materialRecNoteList.getMaterialRecNoteList());
		model.addObject("materialRecNoteList", materialRecNoteList.getMaterialRecNoteList());
		model.addObject("supplierDetailsList", supplierDetailsList);
		return model;

	}

	
	public PurchaseOrderHeader purchaseOrderHeaderChangePoStsByDirector = new PurchaseOrderHeader();
	
	@RequestMapping(value = "/materialReceiptDirectore", method = RequestMethod.GET)
	public ModelAndView materialReceiptDirectore(HttpServletRequest request, HttpServletResponse response) {
		/*
		 * Constants.mainAct = 17; Constants.subAct=184;
		 */
		int mrnId = Integer.parseInt(request.getParameter("mrnId"));
		
		purchaseOrderHeaderChangePoStsByDirector = new PurchaseOrderHeader();
		GetAllRemarksList getAllRemarksList = new GetAllRemarksList();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("mrnId", mrnId);
		materialRecNote = new MaterialRecNote();
		ModelAndView model = new ModelAndView("masters/materialReceiptDirectore");
		try {
			RestTemplate rest = new RestTemplate();

			materialRecNote = rest.postForObject(Constants.url + "/getMaterialRecNotesHeaderDetails", map,
					MaterialRecNote.class);
			System.out.println("materialRecNoteList  :" + materialRecNote.toString());

			Supplist supplierDetailsList = rest.getForObject(Constants.url + "/getAllSupplierlist", Supplist.class);

			System.out.println(supplierDetailsList.getSupplierDetailslist().toString());

			int supId = materialRecNote.getSupplierId();

			for (int i = 0; i < supplierDetailsList.getSupplierDetailslist().size(); i++) {
				if (supId == supplierDetailsList.getSupplierDetailslist().get(i).getSuppId()) {
					 
					model.addObject("suppName1", supplierDetailsList.getSupplierDetailslist().get(i).getSuppName());
					break;
				}
			}
			map = new LinkedMultiValueMap<String, Object>();
			map.add("isFrUsed", 0);
			map.add("moduleId", 1);
			map.add("subModuleId", 1);
			getAllRemarksList = rest.postForObject(Constants.url + "/getAllRemarks", map, GetAllRemarksList.class);

			List<RmItemGroup> rmItemGroupList = rest.getForObject(Constants.url + "rawMaterial/getAllRmItemGroup",
					List.class);
			model.addObject("rmItemGroupList", rmItemGroupList);

			TransporterList transporterList = rest.getForObject(Constants.url + "showTransporters",
					TransporterList.class);
			for (int i = 0; i < transporterList.getTransporterList().size(); i++) {
				if (materialRecNote.getTransportId() == transporterList.getTransporterList().get(i).getTranId()) {

					model.addObject("transportName", transporterList.getTransporterList().get(i).getTranName());
					break;
				}
			}
			
			if(materialRecNote.getApainstPo()==1)
			{
			map = new LinkedMultiValueMap<String, Object>();
			int poid=materialRecNote.getPoId(); 
			map.add("poId", poid);
			purchaseOrderHeaderChangePoStsByDirector = rest.postForObject(Constants.url + "purchaseOrder/getpurchaseorderHeader", map,
					PurchaseOrderHeader.class);
			System.out.println("purchaseOrderHeader " + purchaseOrderHeaderChangePoStsByDirector.toString());
			}
			
			map = new LinkedMultiValueMap<String, Object>();
			String id = new String();
			 for(int i=0;i<materialRecNote.getMaterialRecNoteDetails().size();i++)
			 { 
				 id=id+","+materialRecNote.getMaterialRecNoteDetails().get(i).getRmId();
			 }
			map.add("suppId", supId);
			map.add("rmId", id);
			System.out.println("map"+map);
			ItemRateByRmIdAndSuppId itemRateByRmIdAndSuppId = rest.postForObject(Constants.url + "/getRateByRmId", map,
					ItemRateByRmIdAndSuppId.class);
			System.out.println("itemRateByRmIdAndSuppId " + itemRateByRmIdAndSuppId.toString());
			
			 for(int i=0;i<materialRecNote.getMaterialRecNoteDetails().size();i++)
			 { 
				 for(int j=0;j<itemRateByRmIdAndSuppId.getItemRateByRmIdAndSuppId().size();j++)
				 {
					 if(materialRecNote.getMaterialRecNoteDetails().get(i).getRmId()==itemRateByRmIdAndSuppId.getItemRateByRmIdAndSuppId().get(j).getRmId())
							 {
						 materialRecNote.getMaterialRecNoteDetails().get(i).setVarifiedRate(itemRateByRmIdAndSuppId.getItemRateByRmIdAndSuppId().get(j).getRate());
							 }
				 }
				  
			 }
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		model.addObject("imageUrl", Constants.GATE_ENTRY_IMG_URL);
		model.addObject("materialRecNoteHeader", materialRecNote);
		model.addObject("materialRecNoteDetail", materialRecNote.getMaterialRecNoteDetails());
		model.addObject("allRemarksList", getAllRemarksList.getGetAllRemarks());
		 model.addObject("purchaseOrderHeader",purchaseOrderHeaderChangePoStsByDirector);
		return model;
	}

	@RequestMapping(value = "/submitMaterialReceiptDirector/{status}", method = RequestMethod.POST)
	public String submitMaterialReceiptDirector(@PathVariable int status, HttpServletRequest request,
			HttpServletResponse response) {

		System.out.println("Status : " + status);
		String[] statusList = request.getParameterValues("select_to_approve");
		String approvalRemark = request.getParameter("issue");
		String poSts = request.getParameter("po_sts");
		
		MaterialRecNote materialRecNote1 = materialRecNote;
		materialRecNote1.setStatus(status);
		materialRecNote1.setApprovalRemark(approvalRemark);

		if (statusList != null)
			for (int j = 0; j < statusList.length; j++) {
				System.out.println("Checked Item :" + statusList[j]);
				int statusId = Integer.parseInt(statusList[j]);

				for (int i = 0; i < materialRecNote1.getMaterialRecNoteDetails().size(); i++) {
					if (materialRecNote1.getMaterialRecNoteDetails().get(i).getMrnDetailId() == statusId) {
						System.out.println(
								"Item " + materialRecNote1.getMaterialRecNoteDetails().get(i).getMrnDetailId());
						materialRecNote1.getMaterialRecNoteDetails().get(i).setStatus(1);
					}
					/*
					 * else {
					 * System.out.println("Item "+materialRecNote1.getMaterialRecNoteDetails().get(i
					 * ).getMrnDetailId());
					 * materialRecNote1.getMaterialRecNoteDetails().get(i).setStatus(1); }
					 */
				}
			}
		try {

			RestTemplate rest = new RestTemplate();
			System.out.println("Before Update  :" + materialRecNote1.toString());
			materialRecNote = rest.postForObject(Constants.url + "postMaterialRecNote", materialRecNote1,
					MaterialRecNote.class);
			System.out.println("After Update  :" + materialRecNote.toString());
			
			if(materialRecNote!=null && materialRecNote.getApainstPo()==1)
			{
				if(!poSts.equals("") || !poSts.equals(null))
				{
					int po_sts=Integer.parseInt(poSts);
					if(po_sts==1)
					{
						purchaseOrderHeaderChangePoStsByDirector.setPoStatus(6);
					}
					else if(po_sts==2)
					{
						purchaseOrderHeaderChangePoStsByDirector.setPoStatus(7);
					}
					
					
					purchaseOrderHeaderChangePoStsByDirector.setDelvDateRem(DateConvertor.convertToDMY(purchaseOrderHeaderChangePoStsByDirector.getDelvDateRem()));
					purchaseOrderHeaderChangePoStsByDirector.setQuotationRefDate(DateConvertor.convertToDMY(purchaseOrderHeaderChangePoStsByDirector.getQuotationRefDate()));
					List<PurchaseOrderDetail> purchaseOrderDetail = new ArrayList<PurchaseOrderDetail>();
					purchaseOrderHeaderChangePoStsByDirector.setPurchaseOrderDetail(purchaseOrderDetail);
					System.out.println("purchaseOrderHeader "+purchaseOrderHeaderChangePoStsByDirector);
					
					 Info info=rest.postForObject(Constants.url + "purchaseOrder/insertPurchaseOrder",purchaseOrderHeaderChangePoStsByDirector, Info.class);
					 System.out.println("Response :"+info.toString());
				}
				 
			}
			
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return "redirect:/allDirectorMaterialReceiptNote";
	}

	@RequestMapping(value = "/allMaterialRecieptAccList", method = RequestMethod.GET)
	public ModelAndView allMaterialRecieptAccList(HttpServletRequest request, HttpServletResponse response) {
		 Constants.mainAct =11; Constants.subAct=73;
		ModelAndView model = new ModelAndView("masters/allMaterialRecieptAccList");

		 
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		map.add("status", "3,4");

		RestTemplate rest = new RestTemplate();
		GetMaterialRecNoteList materialRecNoteList = rest.postForObject(Constants.url + "/getMaterialRecNotes", map,
				GetMaterialRecNoteList.class);
		System.out.println("materialRecNoteList  :" + materialRecNoteList.toString());

		Supplist supplierDetailsList = rest.getForObject(Constants.url + "/getAllSupplierlist", Supplist.class);

		System.out.println("Supplier List :" + supplierDetailsList.getSupplierDetailslist().toString());
		System.out.println("materialRecNoteList  " + materialRecNoteList.getMaterialRecNoteList());
		model.addObject("materialRecNoteList", materialRecNoteList.getMaterialRecNoteList());
		model.addObject("supplierDetailsList", supplierDetailsList.getSupplierDetailslist());
		return model;

	}

	public float valueTotal = 0;
	public float discAmtTotal = 0;
	public float cdAmtTotal = 0;
	public float freightAmtTotal = 0;
	public float insuAmtTotal = 0;
	public float other1total = 0;
	public float other2total = 0;
	public float other3total = 0;
	public float other4total = 0;
	public float taxableAmt = 0;
	public float cgst = 0;
	public float sgst = 0;
	public float igst = 0;
	public float cess = 0;
	DecimalFormat df = new DecimalFormat("#.00");
	MaterialRecNote materialRecNoteHeaderAcc = new MaterialRecNote();
	int checkSupp;

	@RequestMapping(value = "/materialRecieptAccDetail", method = RequestMethod.GET)
	public ModelAndView materialRecieptAccDetail(HttpServletRequest request, HttpServletResponse response) {
		valueTotal = 0;
		discAmtTotal = 0;
		cdAmtTotal = 0;
		freightAmtTotal = 0;
		insuAmtTotal = 0;
		other1total = 0;
		other2total = 0;
		other3total = 0;
		other4total = 0;
		taxableAmt = 0;
		cgst = 0;
		sgst = 0;
		igst = 0;
		cess = 0;

		int mrnId = Integer.parseInt(request.getParameter("mrnId"));
		System.out.println("mrnId" + mrnId);
		ModelAndView model = new ModelAndView("masters/materialRecieptAccDetail");
		RestTemplate rest = new RestTemplate();
		try {
			 
			List<RmItemGroup> rmItemGroupList = rest.getForObject(Constants.url + "rawMaterial/getAllRmItemGroup",
					List.class);
			model.addObject("rmItemGroupList", rmItemGroupList);
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("mrnId", mrnId);

			
			materialRecNoteHeaderAcc = rest.postForObject(Constants.url + "/getMaterialRecNotesHeaderDetails", map,
					MaterialRecNote.class);
			System.out.println(
					"materialRecNoteHeaderAcc" + materialRecNoteHeaderAcc.getMaterialRecNoteDetails().toString());
			
			Supplist supplierList = rest.getForObject(Constants.url + "/getAllSupplierlist", Supplist.class);
			//supplierDetailsList = rest.getForObject(Constants.url + "/getAllSupplier", List.class);
			
			 

			int poId = materialRecNoteHeaderAcc.getPoId();
			System.out.println("poId" + poId);
			PurchaseOrderHeader purchaseOrderHeader = new PurchaseOrderHeader();
			if (poId != 0) {
				map = new LinkedMultiValueMap<String, Object>();
				map.add("poId", poId);

				purchaseOrderHeader = rest.postForObject(Constants.url + "purchaseOrder/getpurchaseorderHeader", map,
						PurchaseOrderHeader.class);
				System.out.println("purchaseOrderHeader " + purchaseOrderHeader.toString());
			}

			String rmId = new String();
			for (int i = 0; i < materialRecNoteHeaderAcc.getMaterialRecNoteDetails().size(); i++) {
				rmId = rmId + "," + materialRecNoteHeaderAcc.getMaterialRecNoteDetails().get(i).getRmId();
			}

			System.out.println("rmId " + rmId);

			map = new LinkedMultiValueMap<String, Object>();
			map.add("rmId", rmId);
			GetTaxListByRmId getTaxListByRmId = rest.postForObject(Constants.url + "getTaxByRmId", map,
					GetTaxListByRmId.class);

			System.out.println("getTaxListByRmId " + getTaxListByRmId.getGetTaxByRmIdList());
			
			map = new LinkedMultiValueMap<String, Object>();
			int suppId=materialRecNoteHeaderAcc.getSupplierId();
			map.add("suppId", suppId);
			 checkSupp = rest.postForObject(Constants.url + "CheckSuppGst", map,
					Integer.class);
			System.out.println("checkSupp  "+checkSupp);

			materialRecieptAccList = new ArrayList<MaterialRecieptAcc>();
			MaterialRecieptAcc materialRecieptAcc = new MaterialRecieptAcc();

			for (int i = 0; i < materialRecNoteHeaderAcc.getMaterialRecNoteDetails().size(); i++) {
				
					materialRecieptAcc = new MaterialRecieptAcc();
				 
					materialRecieptAcc.setMrnDetailedId(materialRecNoteHeaderAcc.getMaterialRecNoteDetails().get(i).getMrnDetailId());
					materialRecieptAcc.setItem(materialRecNoteHeaderAcc.getMaterialRecNoteDetails().get(i).getRmName());
					if (materialRecNoteHeaderAcc.getMaterialRecNoteDetails().get(i).getPoId() != 0) {
						materialRecieptAcc.setIncldTax(purchaseOrderHeader.getTaxationRem());
					} else {
						materialRecieptAcc.setIncldTax(0);
					}
					materialRecieptAcc.setVarifiedRate(materialRecNoteHeaderAcc.getMaterialRecNoteDetails().get(i).getVarifiedRate());
					materialRecieptAcc.setPoRate(materialRecNoteHeaderAcc.getMaterialRecNoteDetails().get(i).getPoRate());
					materialRecieptAcc.setReciedvedQty(materialRecNoteHeaderAcc.getMaterialRecNoteDetails().get(i).getRecdQty());
					materialRecieptAcc.setStockQty(materialRecNoteHeaderAcc.getMaterialRecNoteDetails().get(i).getStockQty());
					
					int rejQty = materialRecNoteHeaderAcc.getMaterialRecNoteDetails().get(i).getRecdQty()-materialRecNoteHeaderAcc.getMaterialRecNoteDetails().get(i).getStockQty();
					if(rejQty<0) 
						materialRecieptAcc.setRejectedQty(0); 
					else 
						materialRecieptAcc.setRejectedQty(rejQty);
					 
					
					 materialRecieptAcc.setRmId(materialRecNoteHeaderAcc.getMaterialRecNoteDetails().get(i).getRmId());

					for (int j = 0; j < getTaxListByRmId.getGetTaxByRmIdList().size(); j++) {
						if (getTaxListByRmId.getGetTaxByRmIdList().get(j).getRmId() == materialRecNoteHeaderAcc
								.getMaterialRecNoteDetails().get(i).getRmId()) {
							materialRecieptAcc.setGst(getTaxListByRmId.getGetTaxByRmIdList().get(j).getCgstPer()
									+ getTaxListByRmId.getGetTaxByRmIdList().get(j).getSgstPer());
							materialRecieptAcc.setCgst(getTaxListByRmId.getGetTaxByRmIdList().get(j).getCgstPer());
							materialRecieptAcc.setSgst(getTaxListByRmId.getGetTaxByRmIdList().get(j).getSgstPer());
							materialRecieptAcc.setIgst(getTaxListByRmId.getGetTaxByRmIdList().get(j).getIgstPer());
							break;

						}
					}
					if (materialRecieptAcc.getIncldTax() == 1) {
						materialRecieptAcc
								.setRateCal(Float.valueOf(df.format(materialRecieptAcc.getVarifiedRate() / (1 + materialRecieptAcc.getGst() / 100))));
					} else {
						materialRecieptAcc.setRateCal(materialRecieptAcc.getVarifiedRate());
					}

					materialRecieptAcc.setValue(Float.valueOf(df.format(materialRecieptAcc.getReciedvedQty() * materialRecieptAcc.getRateCal())));
					valueTotal = valueTotal + materialRecieptAcc.getValue();// for calculate total Value
					System.out.println("valueTotal" + valueTotal);

					materialRecieptAcc.setDiscPer(0);
					materialRecieptAcc.setDiscAmt(0);
					discAmtTotal = discAmtTotal + materialRecieptAcc.getDiscAmt();
					System.out.println("discAmtTotal" + discAmtTotal);
						
					materialRecieptAcc.setCdPer(0);
					materialRecieptAcc.setCdAmt(0);
					materialRecieptAcc.setDivFactor(0);
					materialRecieptAcc.setInsuAmt(0);
					materialRecieptAcc.setFreightAmt(0);
					materialRecieptAcc.setOther1(0);
					materialRecieptAcc.setOther2(0);

					materialRecieptAcc.setCess(0);
					materialRecieptAcc.setTaxableAmt(Float.valueOf(df.format(materialRecieptAcc.getValue() - materialRecieptAcc.getDiscAmt()
							- materialRecieptAcc.getCdAmt()
							+ (materialRecieptAcc.getFreightAmt() + materialRecieptAcc.getInsuAmt()
									+ materialRecieptAcc.getOther1() + materialRecieptAcc.getOther2()))));
					taxableAmt = taxableAmt + materialRecieptAcc.getTaxableAmt(); 
					
					if(checkSupp==1)
					{
						materialRecieptAcc.setCgstAmt(Float.valueOf(df.format(materialRecieptAcc.getTaxableAmt() * materialRecieptAcc.getCgst() / 100)));
						cgst = cgst + materialRecieptAcc.getCgstAmt();
						System.out.println("cgst" + cgst);

						materialRecieptAcc.setSgstAmt(Float.valueOf(df.format(materialRecieptAcc.getTaxableAmt() * materialRecieptAcc.getSgst() / 100)));
						sgst = sgst + materialRecieptAcc.getSgstAmt();
						System.out.println("sgst" + sgst);

						materialRecieptAcc.setIgstAmt(0);
						igst = igst + materialRecieptAcc.getIgstAmt();
					}
					else
					{
						materialRecieptAcc.setCgstAmt(0);
						cgst = cgst + materialRecieptAcc.getCgstAmt();
						System.out.println("cgst" + cgst);

						materialRecieptAcc.setSgstAmt(0);
						sgst = sgst + materialRecieptAcc.getSgstAmt();
						System.out.println("sgst" + sgst);

						materialRecieptAcc.setIgstAmt(Float.valueOf(df.format(materialRecieptAcc.getTaxableAmt() * materialRecieptAcc.getIgst() / 100)));
						igst = igst + materialRecieptAcc.getIgstAmt();
						
					}

					
					materialRecieptAcc.setCessAmt(0);
					materialRecieptAcc.setGrandTotal(Float.valueOf(df.format(materialRecieptAcc.getTaxableAmt()+materialRecieptAcc.getOther3()
							+materialRecieptAcc.getOther4()+materialRecieptAcc.getCgstAmt()+materialRecieptAcc.getSgstAmt()
							+materialRecieptAcc.getIgstAmt()+materialRecieptAcc.getCessAmt()))); 
					materialRecieptAccList.add(materialRecieptAcc);

				

			}

			for (int i = 0; i < materialRecieptAccList.size(); i++) // cal freight amt, insurance amnt;
			{
				float divFactor = materialRecieptAccList.get(i).getValue() / valueTotal * 100;
				if(Float.isNaN(divFactor)) 
					materialRecieptAccList.get(i).setDivFactor(0); 
				else
					materialRecieptAccList.get(i).setDivFactor(Float.valueOf(df.format(divFactor))); 

			}

			materialRecNoteHeaderAcc.setBasicValue(valueTotal);
			materialRecNoteHeaderAcc.setDiscAmt2(discAmtTotal);
			materialRecNoteHeaderAcc.setDiscAmt(Float.valueOf(df.format((materialRecNoteHeaderAcc.getBasicValue() - materialRecNoteHeaderAcc.getDiscAmt2())*materialRecNoteHeaderAcc.getDiscPer() / 100)));
			materialRecNoteHeaderAcc.setCgst(cgst);
			materialRecNoteHeaderAcc.setSgst(sgst);
			materialRecNoteHeaderAcc.setIgst(igst);
			materialRecNoteHeaderAcc.setCess(cess);
			float finalAmt = (materialRecNoteHeaderAcc.getBasicValue() - materialRecNoteHeaderAcc.getDiscAmt2()
					- materialRecNoteHeaderAcc.getDiscAmt()-other1total-other2total)
					+ (materialRecNoteHeaderAcc.getFreightAmt() + materialRecNoteHeaderAcc.getInsuranceAmt() + other3total
							+ other4total + materialRecNoteHeaderAcc.getCgst() + materialRecNoteHeaderAcc.getSgst()
							+ materialRecNoteHeaderAcc.getIgst() + materialRecNoteHeaderAcc.getCess()
							+ materialRecNoteHeaderAcc.getRoundOff());
			materialRecNoteHeaderAcc.setBillAmount(Float.valueOf(df.format(finalAmt)));
			float roundOff = Float.valueOf(df.format(materialRecNoteHeaderAcc.getBillAmount()-(int)materialRecNoteHeaderAcc.getBillAmount()));
			if(roundOff==0)
				materialRecNoteHeaderAcc.setRoundOff(Float.valueOf(df.format(0)));
			else
				materialRecNoteHeaderAcc.setRoundOff(Float.valueOf(df.format(1-roundOff)));
			 
			System.out.println("materialRecieptAccList " + materialRecieptAccList.toString());

			System.out.println("Supplier List :" + supplierList.getSupplierDetailslist().toString());
			model.addObject("materialRecNoteHeader", materialRecNoteHeaderAcc);
			model.addObject("supplierDetailsList", supplierList.getSupplierDetailslist());
			model.addObject("materialRecieptAccList", materialRecieptAccList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/updatedetailed", method = RequestMethod.GET)
	@ResponseBody
	public List<MaterialRecieptAcc> updatedetailed(HttpServletRequest request, HttpServletResponse response) {
		DecimalFormat df = new DecimalFormat("#.00");
		 

		int index = Integer.parseInt(request.getParameter("index"));
		float varifiedRate = Float.parseFloat(request.getParameter("varifiedRate"));
		int recQty = Integer.parseInt(request.getParameter("recQty"));
		float discPer = Float.parseFloat(request.getParameter("discPer"));
		float cessAmt = Float.parseFloat(request.getParameter("cessAmt")); 
		float other3 = Float.parseFloat(request.getParameter("other3"));
		float freightAmt = Float.parseFloat(request.getParameter("freightAmt"));
		float insuranceAmt = Float.parseFloat(request.getParameter("insuranceAmt"));
		float other2 = Float.parseFloat(request.getParameter("other2"));
		float other4 = Float.parseFloat(request.getParameter("other4")); 
		System.out.println("index" + index);
		System.out.println("discPer" + discPer);
		System.out.println("recQty" + recQty);
		System.out.println("varifiedRate" + varifiedRate);
		System.out.println("cessAmt" + cessAmt); 
		System.out.println("other3 " + other3); 
		System.out.println("freightAmt" + freightAmt);
		System.out.println("insuranceAmt" + insuranceAmt);
		System.out.println("other2 " + other2); 
		System.out.println("other4 " + other4); 

		try {

			for (int i = 0; i < materialRecieptAccList.size(); i++) 
			{
				if (i == index) 
				{
					materialRecieptAccList.get(i).setVarifiedRate(varifiedRate);
					if(materialRecieptAccList.get(i).getIncldTax()==1)
					{
						
						materialRecieptAccList.get(i).setRateCal(Float.valueOf(df.format(materialRecieptAccList.get(i).getVarifiedRate()
								/ (1 + materialRecieptAccList.get(i).getGst() / 100))));
					}
					else
					{
						materialRecieptAccList.get(i).setRateCal(materialRecieptAccList.get(i).getVarifiedRate());
					}
					materialRecieptAccList.get(i).setReciedvedQty(recQty);
					int rejQty = materialRecieptAccList.get(i).getReciedvedQty()-materialRecieptAccList.get(i).getStockQty();
					if(rejQty<0)
					{
						materialRecieptAccList.get(i).setRejectedQty(0);
					}
					else
					{
						materialRecieptAccList.get(i).setRejectedQty(rejQty);
					}
					materialRecieptAccList.get(i).setValue(Float.valueOf(df.format(materialRecieptAccList.get(i).getReciedvedQty()* materialRecieptAccList.get(i).getRateCal())));
					materialRecieptAccList.get(i).setDiscPer(discPer);
					materialRecieptAccList.get(i).setDiscAmt(Float.valueOf(df.format(materialRecieptAccList.get(i).getValue()
							* materialRecieptAccList.get(i).getDiscPer() / 100))); 
					materialRecieptAccList.get(i).setCdAmt(
							Float.valueOf(df.format((materialRecieptAccList.get(i).getValue() - materialRecieptAccList.get(i).getDiscAmt())
									* materialRecieptAccList.get(i).getCdPer() / 100)));  
					materialRecieptAccList.get(i).setTaxableAmt(Float.valueOf(df.format(materialRecieptAccList.get(i).getValue()
							- materialRecieptAccList.get(i).getDiscAmt() - materialRecieptAccList.get(i).getCdAmt()
							-materialRecieptAccList.get(i).getOther2()
							+ (materialRecieptAccList.get(i).getFreightAmt()
									+ materialRecieptAccList.get(i).getInsuAmt()))));
 
					if(checkSupp==1)
					{
						materialRecieptAccList.get(i).setCgstAmt(Float.valueOf(df.format(materialRecieptAccList.get(i).getTaxableAmt()
								* materialRecieptAccList.get(i).getCgst() / 100)));
						materialRecieptAccList.get(i).setSgstAmt(Float.valueOf(df.format(materialRecieptAccList.get(i).getTaxableAmt()
								* materialRecieptAccList.get(i).getSgst() / 100)));
						materialRecieptAccList.get(i).setIgstAmt(0);
					}
					else
					{
						materialRecieptAccList.get(i).setCgstAmt(0);
						materialRecieptAccList.get(i).setSgstAmt(0);
						materialRecieptAccList.get(i).setIgstAmt(Float.valueOf(df.format(materialRecieptAccList.get(i).getTaxableAmt()
								* materialRecieptAccList.get(i).getIgst() / 100)));
						
					}
					materialRecieptAccList.get(i).setCessAmt(cessAmt);
					materialRecieptAccList.get(i).setGrandTotal(Float.valueOf(df.format(materialRecieptAccList.get(i).getTaxableAmt()+materialRecieptAccList.get(i).getOther3()
							+materialRecieptAccList.get(i).getOther4()+materialRecieptAccList.get(i).getCgstAmt()+materialRecieptAccList.get(i).getSgstAmt()
							+materialRecieptAccList.get(i).getIgstAmt()+materialRecieptAccList.get(i).getCessAmt())));
				}

			}
			valueTotal = 0;
			discAmtTotal = 0;
			cdAmtTotal = 0;
			freightAmtTotal = 0;
			insuAmtTotal = 0;
			other1total = 0;
			other2total = 0;
			other3total = 0;
			other4total = 0;
			taxableAmt = 0;
			cgst = 0;
			sgst = 0;
			igst = 0;
			cess = 0;
			for (int i = 0; i < materialRecieptAccList.size(); i++) {
				valueTotal = Float.valueOf(df.format(valueTotal + materialRecieptAccList.get(i).getValue()));
				discAmtTotal = Float.valueOf(df.format(discAmtTotal + materialRecieptAccList.get(i).getDiscAmt()));
				taxableAmt = Float.valueOf(df.format(taxableAmt + materialRecieptAccList.get(i).getTaxableAmt()));
				cgst = Float.valueOf(df.format(cgst + materialRecieptAccList.get(i).getCgstAmt()));
				sgst = Float.valueOf(df.format(sgst + materialRecieptAccList.get(i).getSgstAmt()));
				igst = Float.valueOf(df.format(igst + materialRecieptAccList.get(i).getIgstAmt()));
				cess = Float.valueOf(df.format(cess + materialRecieptAccList.get(i).getCessAmt())); 
				other2total = other2total + materialRecieptAccList.get(i).getOther2();
				other3total = other3total + materialRecieptAccList.get(i).getOther3();
				other4total = other4total + materialRecieptAccList.get(i).getOther4();

			}
			for (int i = 0; i < materialRecieptAccList.size(); i++) // cal freight amt
			{
				float divFactor = materialRecieptAccList.get(i).getValue() / valueTotal * 100;
				if(Float.isNaN(divFactor))
				{
					materialRecieptAccList.get(i).setDivFactor(0); 
					materialRecieptAccList.get(i).setOther2(0);
					materialRecieptAccList.get(i).setOther3(0);
					materialRecieptAccList.get(i).setOther4(0);
					materialRecieptAccList.get(i).setFreightAmt(0);
					materialRecieptAccList.get(i).setInsuAmt(0); 
					 
				}
				else
				{
					materialRecieptAccList.get(i).setDivFactor(Float.valueOf(df.format(divFactor)));
					materialRecieptAccList.get(i).setOther2(materialRecieptAccList.get(i).getDivFactor() * other2 / 100);
					materialRecieptAccList.get(i).setOther3(materialRecieptAccList.get(i).getDivFactor() * other3 / 100);
					materialRecieptAccList.get(i).setOther4(materialRecieptAccList.get(i).getDivFactor() * other4 / 100);
					materialRecieptAccList.get(i).setFreightAmt(materialRecieptAccList.get(i).getDivFactor() * freightAmt / 100);
					materialRecieptAccList.get(i).setInsuAmt(materialRecieptAccList.get(i).getDivFactor() * insuranceAmt / 100);
				}
			}	

			System.out.println("materialRecieptAccList " + materialRecieptAccList.toString());
			 
		} catch (Exception e) {
			e.printStackTrace();
		}

		return materialRecieptAccList;

	}

	@RequestMapping(value = "/updateHeader", method = RequestMethod.GET)
	@ResponseBody
	public MaterialRecNote updateHeader(HttpServletRequest request, HttpServletResponse response) {
		DecimalFormat df = new DecimalFormat("#.00");
		try {
			System.out.println("ala");
			valueTotal = 0;
			discAmtTotal = 0;
			cdAmtTotal = 0;
			freightAmtTotal = 0;
			insuAmtTotal = 0;
			other1total = 0;
			other2total = 0;
			other3total = 0;
			other4total = 0;
			taxableAmt = 0;
			cgst = 0;
			sgst = 0;
			igst = 0;
			cess = 0;
			float finalAmt=0;
			for (int i = 0; i < materialRecieptAccList.size(); i++) {
				valueTotal = Float.valueOf(df.format(valueTotal + materialRecieptAccList.get(i).getValue()));
				discAmtTotal = Float.valueOf(df.format(discAmtTotal + materialRecieptAccList.get(i).getDiscAmt()));
				taxableAmt = Float.valueOf(df.format(taxableAmt + materialRecieptAccList.get(i).getTaxableAmt()));
				cgst = Float.valueOf(df.format(cgst + materialRecieptAccList.get(i).getCgstAmt()));
				sgst = Float.valueOf(df.format(sgst + materialRecieptAccList.get(i).getSgstAmt()));
				igst = Float.valueOf(df.format(igst + materialRecieptAccList.get(i).getIgstAmt()));
				cess = Float.valueOf(df.format(cess + materialRecieptAccList.get(i).getCessAmt())); 
				other2total = other2total + materialRecieptAccList.get(i).getOther2();
				other3total = other3total + materialRecieptAccList.get(i).getOther3();
				other4total = other4total + materialRecieptAccList.get(i).getOther4();

			}
			for (int i = 0; i < materialRecieptAccList.size(); i++) // cal freight amt, 
			{
				float divFactor = materialRecieptAccList.get(i).getValue() / valueTotal * 100;
				if(Float.isNaN(divFactor)) 
					materialRecieptAccList.get(i).setDivFactor(0); 
				else
					materialRecieptAccList.get(i).setDivFactor(Float.valueOf(df.format(divFactor)));  

			}
			materialRecNoteHeaderAcc.setBasicValue(valueTotal);
			materialRecNoteHeaderAcc.setDiscAmt2(discAmtTotal);
			materialRecNoteHeaderAcc
					.setDiscAmt(Float.valueOf(df.format((materialRecNoteHeaderAcc.getBasicValue() - materialRecNoteHeaderAcc.getDiscAmt2())
							* materialRecNoteHeaderAcc.getDiscPer() / 100)));
			materialRecNoteHeaderAcc.setCgst(cgst);
			materialRecNoteHeaderAcc.setSgst(sgst);
			materialRecNoteHeaderAcc.setIgst(igst);
			materialRecNoteHeaderAcc.setCess(cess);
			materialRecNoteHeaderAcc.setOther1(other1total);
			materialRecNoteHeaderAcc.setOther2(other2total);
			materialRecNoteHeaderAcc.setOther3(other3total);
			materialRecNoteHeaderAcc.setOther4(other4total);
			System.out.println("before submit" + finalAmt);
			finalAmt = (materialRecNoteHeaderAcc.getBasicValue() - materialRecNoteHeaderAcc.getDiscAmt2()
					- materialRecNoteHeaderAcc.getDiscAmt()-other1total-other2total)
					+ (materialRecNoteHeaderAcc.getFreightAmt() + materialRecNoteHeaderAcc.getInsuranceAmt() + other3total
							+ other4total + materialRecNoteHeaderAcc.getCgst() + materialRecNoteHeaderAcc.getSgst()
							+ materialRecNoteHeaderAcc.getIgst() + materialRecNoteHeaderAcc.getCess());
			materialRecNoteHeaderAcc.setBillAmount(Float.valueOf(df.format(finalAmt)));
			int integer = (int)materialRecNoteHeaderAcc.getBillAmount();
			double decimal = materialRecNoteHeaderAcc.getBillAmount()-(int)materialRecNoteHeaderAcc.getBillAmount();
			System.out.println("integer"+integer);
			System.out.println("decimal"+decimal);
			float roundOff = Float.valueOf(df.format(materialRecNoteHeaderAcc.getBillAmount()-(int)materialRecNoteHeaderAcc.getBillAmount()));
			if(roundOff==0)
				materialRecNoteHeaderAcc.setRoundOff(Float.valueOf(df.format(0)));
			else
				materialRecNoteHeaderAcc.setRoundOff(Float.valueOf(df.format(1-roundOff)));
			 
			System.out.println("cgst" + materialRecNoteHeaderAcc.getCgst());
			System.out.println(" sgst" + materialRecNoteHeaderAcc.getSgst());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return materialRecNoteHeaderAcc;

	}

	@RequestMapping(value = "/updateFreightAmt", method = RequestMethod.GET)
	@ResponseBody
	public List<MaterialRecieptAcc> updateFreightAmt(HttpServletRequest request, HttpServletResponse response) {

		float discPer = Float.parseFloat(request.getParameter("discPer"));
		float freightAmt = Float.parseFloat(request.getParameter("freightAmt"));
		float insuranceAmt = Float.parseFloat(request.getParameter("insuranceAmt"));
		float other2 = Float.parseFloat(request.getParameter("other2"));
		float other3 = Float.parseFloat(request.getParameter("other3"));
		float other4 = Float.parseFloat(request.getParameter("other4"));
		System.out.println("discPer" + discPer);
		System.out.println("freightAmt" + freightAmt);
		System.out.println("insuranceAmt" + insuranceAmt);
		System.out.println("other2" + other2);
		System.out.println("other4" + other4);

		try {
			materialRecNoteHeaderAcc.setDiscPer(discPer);
			materialRecNoteHeaderAcc.setFreightAmt(freightAmt);
			materialRecNoteHeaderAcc.setInsuranceAmt(insuranceAmt);
			materialRecNoteHeaderAcc.setOther2(other2);
			materialRecNoteHeaderAcc.setOther3(other3);
			materialRecNoteHeaderAcc.setOther4(other4);

			for (int i = 0; i < materialRecieptAccList.size(); i++) {
				materialRecieptAccList.get(i).setCdPer(discPer);
				materialRecieptAccList.get(i).setCdAmt(Float.valueOf(df.format(
						(materialRecieptAccList.get(i).getValue() - materialRecieptAccList.get(i).getDiscAmt())
								* materialRecieptAccList.get(i).getCdPer() / 100)));
				materialRecieptAccList.get(i)
						.setFreightAmt(Float.valueOf(df.format(materialRecieptAccList.get(i).getDivFactor() * freightAmt / 100)));
				materialRecieptAccList.get(i)
						.setInsuAmt(Float.valueOf(df.format(materialRecieptAccList.get(i).getDivFactor() * insuranceAmt / 100)));
				materialRecieptAccList.get(i).setOther2(materialRecieptAccList.get(i).getDivFactor() * other2 / 100);
				materialRecieptAccList.get(i).setOther3(materialRecieptAccList.get(i).getDivFactor() * other3 / 100);
				materialRecieptAccList.get(i).setOther4(materialRecieptAccList.get(i).getDivFactor() * other4 / 100);
				materialRecieptAccList.get(i).setTaxableAmt(Float.valueOf(df.format(materialRecieptAccList.get(i).getValue()
						- materialRecieptAccList.get(i).getDiscAmt() - materialRecieptAccList.get(i).getCdAmt() 
						-materialRecieptAccList.get(i).getOther2()
						+ (materialRecieptAccList.get(i).getFreightAmt() + materialRecieptAccList.get(i).getInsuAmt()))));
				
				if(checkSupp==1)
				{
					materialRecieptAccList.get(i).setCgstAmt(Float.valueOf(df.format(materialRecieptAccList.get(i).getTaxableAmt()
							* materialRecieptAccList.get(i).getCgst() / 100)));
					materialRecieptAccList.get(i).setSgstAmt(Float.valueOf(df.format(materialRecieptAccList.get(i).getTaxableAmt()
							* materialRecieptAccList.get(i).getSgst() / 100)));
					materialRecieptAccList.get(i).setIgstAmt(0);
				}
				else
				{
					materialRecieptAccList.get(i).setCgstAmt(0);
					materialRecieptAccList.get(i).setSgstAmt(0);
					materialRecieptAccList.get(i).setIgstAmt(Float.valueOf(df.format(materialRecieptAccList.get(i).getTaxableAmt()
							* materialRecieptAccList.get(i).getIgst() / 100)));
					
				}
				materialRecieptAccList.get(i).setGrandTotal(Float.valueOf(df.format(materialRecieptAccList.get(i).getTaxableAmt()+materialRecieptAccList.get(i).getOther3()
						+materialRecieptAccList.get(i).getOther4()+materialRecieptAccList.get(i).getCgstAmt()+materialRecieptAccList.get(i).getSgstAmt()
						+materialRecieptAccList.get(i).getIgstAmt()+materialRecieptAccList.get(i).getCessAmt())));

			}
			System.out.println("materialRecieptAccList" + materialRecieptAccList.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return materialRecieptAccList;

	}

	@RequestMapping(value = "/submitMaterialAcc", method = RequestMethod.POST)
	public String submitMaterialAcc(HttpServletRequest request, HttpServletResponse response) {


		String booking_date = request.getParameter("booking_date");
		String invoice_date = request.getParameter("invoice_date");
		String invoice_no = request.getParameter("invoice_no");
		int mrntype = Integer.parseInt(request.getParameter("mrntype"));
		System.out.println("booking_date" + booking_date);
		System.out.println("invoice_date" + invoice_date);
		System.out.println("invoice_no" + invoice_no);

		try {
			DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			Date bookingdate = null;
			Date invoicedate = null;
			bookingdate=formatter.parse(booking_date);
			invoicedate=formatter.parse(invoice_date);
			
			System.out.println("bookingdate" + bookingdate);
			System.out.println("invoicedate" + invoicedate);

			materialRecNoteHeaderAcc.setInvBookDate(bookingdate);
			materialRecNoteHeaderAcc.setInvDate(invoicedate);
			materialRecNoteHeaderAcc.setInvoiceNumber(invoice_no);
			materialRecNoteHeaderAcc.setMrnType(mrntype);
			materialRecNoteHeaderAcc.setStatus(5);
			materialRecNoteHeaderAcc.setAccRemark("");
			materialRecNoteHeaderAcc.setUseridAcc(0);

			for (int i = 0; i < materialRecNoteHeaderAcc.getMaterialRecNoteDetails().size(); i++) {
				for (int j = 0; j < materialRecieptAccList.size(); j++) {
					if (materialRecNoteHeaderAcc.getMaterialRecNoteDetails().get(i)
							.getMrnDetailId() == materialRecieptAccList.get(j).getMrnDetailedId()) {
						
						materialRecNoteHeaderAcc.getMaterialRecNoteDetails().get(i).setRecdQty(materialRecieptAccList.get(j).getReciedvedQty());
						materialRecNoteHeaderAcc.getMaterialRecNoteDetails().get(i).setRejectedQty(materialRecieptAccList.get(j).getRejectedQty());
						
						materialRecNoteHeaderAcc.getMaterialRecNoteDetails().get(i).setVarifiedRate(materialRecieptAccList.get(j).getVarifiedRate());
						materialRecNoteHeaderAcc.getMaterialRecNoteDetails().get(i).setValue(materialRecieptAccList.get(j).getValue());
						materialRecNoteHeaderAcc.getMaterialRecNoteDetails().get(i).setDiscPer(materialRecieptAccList.get(j).getDiscPer());
						materialRecNoteHeaderAcc.getMaterialRecNoteDetails().get(i).setDiscAmt(materialRecieptAccList.get(j).getDiscAmt());
						materialRecNoteHeaderAcc.getMaterialRecNoteDetails().get(i).setGstPer(materialRecieptAccList.get(j).getGst());
						materialRecNoteHeaderAcc.getMaterialRecNoteDetails().get(i).setFreightAmt(materialRecieptAccList.get(j).getFreightAmt());
						materialRecNoteHeaderAcc.getMaterialRecNoteDetails().get(i).setInsurance_amt(materialRecieptAccList.get(j).getInsuAmt());
						materialRecNoteHeaderAcc.getMaterialRecNoteDetails().get(i).setCgstPer(materialRecieptAccList.get(j).getCgst());
						materialRecNoteHeaderAcc.getMaterialRecNoteDetails().get(i).setCgstRs(materialRecieptAccList.get(j).getCgstAmt());
						materialRecNoteHeaderAcc.getMaterialRecNoteDetails().get(i).setSgstPer(materialRecieptAccList.get(j).getSgst());
						materialRecNoteHeaderAcc.getMaterialRecNoteDetails().get(i).setSgstRs(materialRecieptAccList.get(j).getSgstAmt());
						materialRecNoteHeaderAcc.getMaterialRecNoteDetails().get(i).setIgstPer(materialRecieptAccList.get(j).getIgst());
						materialRecNoteHeaderAcc.getMaterialRecNoteDetails().get(i).setIgstRs(materialRecieptAccList.get(j).getIgstAmt());
						materialRecNoteHeaderAcc.getMaterialRecNoteDetails().get(i).setCessPer(materialRecieptAccList.get(j).getCess());
						materialRecNoteHeaderAcc.getMaterialRecNoteDetails().get(i).setCessRs(materialRecieptAccList.get(j).getCessAmt());
						materialRecNoteHeaderAcc.getMaterialRecNoteDetails().get(i).setAmount(materialRecieptAccList.get(j).getTaxableAmt());
						materialRecNoteHeaderAcc.getMaterialRecNoteDetails().get(i).setOther1(materialRecieptAccList.get(j).getCdAmt());
						materialRecNoteHeaderAcc.getMaterialRecNoteDetails().get(i).setOther2(materialRecieptAccList.get(j).getOther2()); 
						materialRecNoteHeaderAcc.getMaterialRecNoteDetails().get(i).setOther3(materialRecieptAccList.get(j).getOther3()); 
						materialRecNoteHeaderAcc.getMaterialRecNoteDetails().get(i).setOther4(materialRecieptAccList.get(j).getOther4());


					}
				}
			}
			RestTemplate rest = new RestTemplate();
			System.out.println("Update   " + materialRecNoteHeaderAcc.toString());
			materialRecNoteHeaderAcc = rest.postForObject(Constants.url + "/postMaterialRecNote",
					materialRecNoteHeaderAcc, MaterialRecNote.class);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/allMaterialRecieptAccList";

	}

}
