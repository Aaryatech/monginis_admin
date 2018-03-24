package com.ats.adminpanel.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
import com.ats.adminpanel.commons.DateConvertor;
import com.ats.adminpanel.model.BmsStockDetailed;
import com.ats.adminpanel.model.BmsStockHeader;
import com.ats.adminpanel.model.Info;
import com.ats.adminpanel.model.RawMaterial.RawMaterialDetails;
import com.ats.adminpanel.model.RawMaterial.RawMaterialDetailsList;
import com.ats.adminpanel.model.creditnote.GetGrnGvnForCreditNoteList;
import com.ats.adminpanel.model.item.FrItemStockConfigure;
import com.ats.adminpanel.model.item.FrItemStockConfigureList;
import com.ats.adminpanel.model.item.Item;
import com.ats.adminpanel.model.login.UserResponse;
import com.ats.adminpanel.model.productionplan.BillOfMaterialDetailed;
import com.ats.adminpanel.model.productionplan.BillOfMaterialHeader;
import com.ats.adminpanel.model.productionplan.GetBillOfMaterialList;
import com.ats.adminpanel.model.stock.GetBmsCurrentStock;
import com.ats.adminpanel.model.stock.GetBmsCurrentStockList;

@Controller
@Scope("session")
public class BmsToStoreBomController {

	
	public static RawMaterialDetailsList rawMaterialDetailsList;
	public static List<BmsStockDetailed> bmsStockDetailedList;
	public List<BillOfMaterialHeader> getbomList = new ArrayList<BillOfMaterialHeader>();
	private List<BillOfMaterialHeader> getBOMListall;
	BillOfMaterialHeader billOfMaterialHeader = new BillOfMaterialHeader();
	List<BillOfMaterialDetailed> bomwithdetaild = new ArrayList<BillOfMaterialDetailed>();
	
	@RequestMapping(value = "/showBmsToStoreBom", method = RequestMethod.GET)
	public ModelAndView showInsertCreditNote(HttpServletRequest request, HttpServletResponse response) {

	 	Constants.mainAct =8;
	 	Constants.subAct =46;
 
		ModelAndView model = new ModelAndView("bmsToStore/bmsToStoreBom");

		try {

			RestTemplate rest = new RestTemplate();

			  rawMaterialDetailsList=rest.getForObject(Constants.url +"rawMaterial/getAllRawMaterial", RawMaterialDetailsList.class);
			
			MultiValueMap<String,Object> map=new LinkedMultiValueMap<>();
			Date currentDate=new Date();
			System.out.println("Current Date : "+currentDate);
			map.add("status", 0);
			 
			map.add("rmType", 1);
			
			
			BmsStockHeader bmsStockHeader=rest.postForObject(Constants.url +"getBmsStockHeader",map, BmsStockHeader.class);
		
		System.out.println("BMS Header  :  "+bmsStockHeader.toString());	
			map=new LinkedMultiValueMap<>();
			
			map.add("fromDate", new SimpleDateFormat("yyyy-MM-dd").format(bmsStockHeader.getBmsStockDate()));
			map.add("toDate", new SimpleDateFormat("yyyy-MM-dd").format(bmsStockHeader.getBmsStockDate()));
			map.add("rmType", 1);
			
			ParameterizedTypeReference<List<BmsStockDetailed>> typeRef = new ParameterizedTypeReference<List<BmsStockDetailed>>() {
			};
			ResponseEntity<List<BmsStockDetailed>> responseEntity = rest.exchange(Constants.url + "getBmsStock",
					HttpMethod.POST, new HttpEntity<>(map), typeRef);
			
			  bmsStockDetailedList = responseEntity.getBody();
			//=rest.postForObject(Constants.url +"getBmsStock",map, List.class);
			System.out.println("BMS Stock   :  "+bmsStockDetailedList.toString());
			/*if(bmsStockHeader.getBmsStatus()==0)
			{*/
				 String settingKey = new String();

				settingKey = "PROD" + "," + "MIX" + "," + "BMS" + "," + "STORE";

				map.add("settingKeyList", settingKey);

				FrItemStockConfigureList settingList = rest.postForObject(Constants.url + "getDeptSettingValue", map,
						FrItemStockConfigureList.class);

				System.out.println("SettingKeyList" + settingList.toString());

				
				int prodDeptId = 0, bmsDeptId = 0, mixDeptId = 0;

				List<FrItemStockConfigure> settingKeyList = settingList.getFrItemStockConfigure();

				for (int i = 0; i < settingKeyList.size(); i++) {

					if (settingKeyList.get(i).getSettingKey().equalsIgnoreCase("PROD")) {

						prodDeptId = settingKeyList.get(i).getSettingValue();

					}
					if (settingKeyList.get(i).getSettingKey().equalsIgnoreCase("BMS")) {

						bmsDeptId = settingKeyList.get(i).getSettingValue();

					}
					if (settingKeyList.get(i).getSettingKey().equalsIgnoreCase("Mix")) {

						mixDeptId = settingKeyList.get(i).getSettingValue();

					}

				}
				
				 
				map=new LinkedMultiValueMap<>();
				
				map.add("prodDeptId", prodDeptId);
				map.add("mixDeptId", mixDeptId);
				map.add("bmsDeptId", bmsDeptId);
				map.add("stockDate", new SimpleDateFormat("dd-MM-yyyy").format(bmsStockHeader.getBmsStockDate()));
				System.out.println("map"+map);
				GetBmsCurrentStockList getBmsCurrentStockList=rest.postForObject(Constants.url +"/getCurentBmsStockRM",map, GetBmsCurrentStockList.class);
			 
			
			for(int i=0;i<bmsStockDetailedList.size();i++)
			{
				for(int j=0;j<getBmsCurrentStockList.getBmsCurrentStock().size();j++)
					{
					if(getBmsCurrentStockList.getBmsCurrentStock().get(j).getRmId()==bmsStockDetailedList.get(i).getRmId())
					{
						GetBmsCurrentStock getBmsCurrentStock=getBmsCurrentStockList.getBmsCurrentStock().get(j); 
							float stockQty=(getBmsCurrentStock.getBmsOpeningStock()+getBmsCurrentStock.getStoreIssueQty()+getBmsCurrentStock.getProdReturnQty()+getBmsCurrentStock.getMixingReturnQty())
							-(getBmsCurrentStock.getProdIssueQty()+getBmsCurrentStock.getMixingIssueQty()+getBmsCurrentStock.getStoreRejectedQty());
							bmsStockDetailedList.get(i).setClosingQty(stockQty);
							
							/*System.out.println("name"+getBmsCurrentStock.getRmName());
							System.out.println("("+getBmsCurrentStock.getBmsOpeningStock()+"+"+getBmsCurrentStock.getStoreIssueQty()+"+"+getBmsCurrentStock.getProdReturnQty()+"+"+getBmsCurrentStock.getMixingReturnQty()
							+")-("+getBmsCurrentStock.getProdIssueQty()+"+"+getBmsCurrentStock.getMixingIssueQty()+"+"+getBmsCurrentStock.getStoreRejectedQty()+")");
							 
							System.out.println("stockQty"+stockQty);*/
							
					}
				}	
			}
			//model.addObject("rmStockList",bmsStockDetailedList);
			/*}*/
			 System.out.println("Rm Stock List  :  "+bmsStockDetailedList.toString());
			model.addObject("rmStockList",bmsStockDetailedList);
			model.addObject("rmList",rawMaterialDetailsList.getRawMaterialDetailsList());
			
		} catch (Exception e) {

			System.out.println("Error in Getting   details " + e.getMessage());

			e.printStackTrace();
		}

		return model;

	}
	
	
	
	@RequestMapping(value = "/submitBmstoStore", method = RequestMethod.POST)
	public String submitBmstoStore( HttpServletRequest request, HttpServletResponse response) {
		
		String []checkbox=request.getParameterValues("select_to_approve");
		//for(int i=0;i<checkbox.length;i++) {
		//System.out.println("Checked List " +checkbox[i]);
		
		 
		
		
			HttpSession session=request.getSession();
			UserResponse userResponse =(UserResponse) session.getAttribute("UserDetail");
			
			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			
			int deptId=userResponse.getUser().getDeptId();
			
			int userId=userResponse.getUser().getId();
			
			String settingKey = new String();

			settingKey = "BMS" + "," + "STORE";

			map.add("settingKeyList", settingKey);
			
			// web Service to get Dept Name And Dept Id for bom toDept and toDeptId
			
			FrItemStockConfigureList settingList = restTemplate.postForObject(Constants.url + "getDeptSettingValue", map,
					FrItemStockConfigureList.class);
			
			 
			
			System.out.println("new Field Dept Id = "+userResponse.getUser().getDeptId());
			
			int toDeptId=0;
			String toDeptName=new String();
			List<FrItemStockConfigure> settingKeyList = settingList.getFrItemStockConfigure();
			for (int i = 0; i < settingKeyList.size(); i++) {

				if (settingKeyList.get(i).getSettingKey().equalsIgnoreCase("BMS")) {

					deptId = settingKeyList.get(i).getSettingValue();

				}
				if (settingKeyList.get(i).getSettingKey().equalsIgnoreCase("STORE")) {

					toDeptId = settingKeyList.get(i).getSettingValue();
					toDeptName=settingKeyList.get(i).getSettingKey();
				}
				 

			}
			
			 

			try {
				
				//int toDeptId=settingList.getFrItemStockConfigure().get(0).getSettingValue();
				  //toDeptName=settingList.getFrItemStockConfigure().get(0).getSettingKey();
				
				Date date = new Date();

				BillOfMaterialHeader billOfMaterialHeader = new BillOfMaterialHeader();

				billOfMaterialHeader.setApprovedDate(date);
				billOfMaterialHeader.setApprovedUserId(0);
				billOfMaterialHeader.setDelStatus(0);
				billOfMaterialHeader.setFromDeptId(deptId);
				billOfMaterialHeader.setProductionDate(date);
				billOfMaterialHeader.setProductionId(3);
				billOfMaterialHeader.setReqDate(date);
				billOfMaterialHeader.setSenderUserid(userId);
				billOfMaterialHeader.setStatus(0);
				billOfMaterialHeader.setToDeptId(toDeptId);
				billOfMaterialHeader.setToDeptName(toDeptName);
				
				billOfMaterialHeader.setRejApproveDate(date);
				billOfMaterialHeader.setRejApproveUserId(0);
				billOfMaterialHeader.setRejDate(date);
				billOfMaterialHeader.setRejUserId(0);
				
				billOfMaterialHeader.setIsManual(0);

				List<BillOfMaterialDetailed> bomDetailList = new ArrayList<BillOfMaterialDetailed>();
				BillOfMaterialDetailed bomDetail = null;

				 
					billOfMaterialHeader.setIsProduction(3);

					billOfMaterialHeader.setFromDeptName("BMS");
					//billOfMaterialHeader.setIsPlan(globalIsPlan);

					
					billOfMaterialHeader.setIsPlan(0);

					for (int i = 0; i < bmsStockDetailedList.size(); i++) {

						BmsStockDetailed bmsStockDetailed=bmsStockDetailedList.get(i);
						for(int j=0;j<checkbox.length;j++) {
							if(Integer.parseInt(checkbox[j])==bmsStockDetailedList.get(i).getRmId())
							{
						String orderQty = request.getParameter("orderQty" + checkbox[j]);
						bomDetail = new BillOfMaterialDetailed();

						System.out.println("orderQty " + orderQty);

						bomDetail.setDelStatus(0);
						bomDetail.setRmId(bmsStockDetailed.getRmId());
						bomDetail.setRmIssueQty(0.0F);
						bomDetail.setUom(bmsStockDetailed.getUom());
						bomDetail.setRmType(bmsStockDetailed.getRmType());
						bomDetail.setRmReqQty((int)Float.parseFloat(orderQty));
						bomDetail.setRmName(bmsStockDetailed.getRmName());
						
						bomDetail.setRejectedQty(0);
						bomDetail.setAutoRmReqQty((int)Float.parseFloat(orderQty));
						
						bomDetail.setReturnQty(0);
						bomDetailList.add(bomDetail);

					}
					}
					}
					
					System.out.println("bom detail List " + bomDetailList.toString());
					billOfMaterialHeader.setBillOfMaterialDetailed(bomDetailList);

					System.out.println(" insert List " + billOfMaterialHeader.toString()); 
					
					Info info = restTemplate.postForObject(Constants.url + "saveBom", billOfMaterialHeader, Info.class);
					
					 
					
					
				 
 

				


			} catch (Exception e) {
				e.printStackTrace();

			}
			
	 
		 
		 
		return "redirect:/showBmsToStoreBom"; 
	}
	
	
	
	
	@RequestMapping(value = "/getBomListBmsToStore", method = RequestMethod.GET)
	public ModelAndView getBomList(HttpServletRequest request, HttpServletResponse response) {
		 Constants.mainAct =8;
		Constants.subAct=47; 
		
		ModelAndView model = new ModelAndView("bmsToStore/bmsToStoreBomHeader");//
		getbomList = new ArrayList<BillOfMaterialHeader>();
		HttpSession session=request.getSession();
		UserResponse userResponse =(UserResponse) session.getAttribute("UserDetail");
		 
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		RestTemplate rest = new RestTemplate();
		int fromDept=0;
		try
		{
			String settingKey = new String();

			settingKey = "BMS" + "," + "STORE";

			map.add("settingKeyList", settingKey);
			
			// web Service to get Dept Name And Dept Id for bom toDept and toDeptId
			
			FrItemStockConfigureList settingList = rest.postForObject(Constants.url + "getDeptSettingValue", map,
					FrItemStockConfigureList.class);
			
			 
			
			System.out.println("new Field Dept Id = "+userResponse.getUser().getDeptId());
			
			int toDeptId=0;
			
			String toDeptName=new String();
			List<FrItemStockConfigure> settingKeyList = settingList.getFrItemStockConfigure();
			for (int i = 0; i < settingKeyList.size(); i++) {

				if (settingKeyList.get(i).getSettingKey().equalsIgnoreCase("BMS")) {

					fromDept = settingKeyList.get(i).getSettingValue();

				}
				if (settingKeyList.get(i).getSettingKey().equalsIgnoreCase("STORE")) {

					toDeptId = settingKeyList.get(i).getSettingValue();
					toDeptName=settingKeyList.get(i).getSettingKey();
				}
				 

			}
			
			map = new LinkedMultiValueMap<String, Object>();
			map.add("fromDept",fromDept);         
			map.add("toDept",toDeptId);           
			map.add("status","0"); 
			
			GetBillOfMaterialList getBillOfMaterialList= rest.postForObject(Constants.url + "/getBOMHeaderBmsAndStore",map, GetBillOfMaterialList.class);
			 
			
			System.out.println("getbomList"+getBillOfMaterialList.getBillOfMaterialHeader().toString());
			getbomList=getBillOfMaterialList.getBillOfMaterialHeader();
			System.out.println("bomHeaderList"+getBillOfMaterialList.getBillOfMaterialHeader().toString());
			
		}catch(Exception e)
		{
			System.out.println("error in controller "+e.getMessage());
		}
		model.addObject("settingvalue",fromDept) ;
		model.addObject("deptId",userResponse.getUser().getDeptId()) ;
		model.addObject("getbomList",getbomList) ;
		return model;

	}
	
	
	
	
	@RequestMapping(value = "/getBomListBmsToStoreWithDate", method = RequestMethod.GET)
	@ResponseBody
	public List<BillOfMaterialHeader> getBomAllListWithDate(HttpServletRequest request, HttpServletResponse response) {
		/*Constants.mainAct = 17;
		Constants.subAct=184;*/
		System.out.println("in controller");
		String frmdate=request.getParameter("from_date");
		String todate=request.getParameter("to_date");
		  
		
		try {
			     
			System.out.println("in getMixingListWithDate   "+frmdate+todate);
			String frdate=DateConvertor.convertToYMD(frmdate);
			String tdate=DateConvertor.convertToYMD(todate);
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			String settingKey = new String(); 
			settingKey = "STORE"; 
			map.add("settingKeyList", settingKey);
			
			RestTemplate rest = new RestTemplate();
			FrItemStockConfigureList settingList = rest.postForObject(Constants.url + "getDeptSettingValue", map,
					FrItemStockConfigureList.class);
			
			
			map = new LinkedMultiValueMap<String, Object>();
			String settingKey2 = new String(); 
			settingKey2 = "BMS"; 
			map.add("settingKeyList", settingKey2);
			
			
			FrItemStockConfigureList settingList2 = rest.postForObject(Constants.url + "getDeptSettingValue", map,
					FrItemStockConfigureList.class);
			
			map = new LinkedMultiValueMap<String, Object>();
			map.add("fromDept",settingList2.getFrItemStockConfigure().get(0).getSettingValue());         
			map.add("toDept",settingList.getFrItemStockConfigure().get(0).getSettingValue());   
			map.add("frmdate",frdate);
			map.add("todate",tdate);
			map.add("fromDept",16);             
			            
			
			System.out.println("in getBOMListWithDate   "+frdate+tdate);
			
			GetBillOfMaterialList getBillOfMaterialList= rest.postForObject(Constants.url + "/getBOMHeaderListBmsAndStore",map, GetBillOfMaterialList.class);
			getBOMListall  = getBillOfMaterialList.getBillOfMaterialHeader();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return getBOMListall;
	

	}
	
	
	@RequestMapping(value = "/viewDetailBOMBmsToStoreRequest", method = RequestMethod.GET)
	public ModelAndView viewDetailBOMRequest(HttpServletRequest request, HttpServletResponse response) {
		/*Constants.mainAct = 17;
		Constants.subAct=184;*/
		ModelAndView model = new ModelAndView("bmsToStore/bmsToStoreBomDetail");
		
		HttpSession session=request.getSession();
		UserResponse userResponse =(UserResponse) session.getAttribute("UserDetail"); 
		int deptId=userResponse.getUser().getDeptId();
		
		//String mixId=request.getParameter("mixId");
		int reqId=Integer.parseInt(request.getParameter("reqId"));
		System.out.println(reqId);
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("reqId",reqId);
		RestTemplate rest = new RestTemplate();
		billOfMaterialHeader=rest.postForObject(Constants.url + "/getDetailedwithreqId",map, BillOfMaterialHeader.class);
		bomwithdetaild =billOfMaterialHeader.getBillOfMaterialDetailed();
		
		model.addObject("deptId",deptId);
		model.addObject("billOfMaterialHeader",billOfMaterialHeader);
		model.addObject("bomwithdetaild", bomwithdetaild);
		
		return model;
	}
	
	//getBOMHeaderListBmsAndStore
	
	
	
	
	
	@RequestMapping(value = "/approvedBomFromStore", method = RequestMethod.POST)
	public String approvedBom(HttpServletRequest request, HttpServletResponse response) {
		Constants.mainAct = 17;
		Constants.subAct=184;
		Date date= new Date();
		
		HttpSession session=request.getSession();
		UserResponse userResponse =(UserResponse) session.getAttribute("UserDetail");
		
		int deptId=userResponse.getUser().getDeptId();
		int userId=userResponse.getUser().getId();
		
		
		for(int i=0;i<billOfMaterialHeader.getBillOfMaterialDetailed().size();i++)
		{
			System.out.println(12);
			 
			 
				System.out.println(13);
				String issue_qty=request.getParameter("issue_qty"+billOfMaterialHeader.getBillOfMaterialDetailed().get(i).getReqDetailId());
				
				if(issue_qty!=null) {
					System.out.println("issue_qty Qty   :"+issue_qty);
					float issueqty= Float.parseFloat(issue_qty);
					billOfMaterialHeader.getBillOfMaterialDetailed().get(i).setRmIssueQty(issueqty);
					System.out.println("productionQty  :"+issueqty);
				}
				else
				{
					billOfMaterialHeader.getBillOfMaterialDetailed().get(i).setRmIssueQty(0);
				}
				System.out.println(2);
			 
		}
		billOfMaterialHeader.setStatus(1);
		billOfMaterialHeader.setApprovedUserId(userId);
		billOfMaterialHeader.setApprovedDate(date);
		
		System.out.println(billOfMaterialHeader.toString());
		
		RestTemplate rest = new RestTemplate();
		
		Info info = rest.postForObject(Constants.url + "saveBom", billOfMaterialHeader, Info.class);	
		System.out.println(info);
		
		return "redirect:/getBomListBmsToStore";
	}
	
	
	@RequestMapping(value = "/rejectiontoStore", method = RequestMethod.GET)
	public ModelAndView rejectiontoBms(HttpServletRequest request, HttpServletResponse response) {
		Constants.mainAct = 17;
		Constants.subAct=184;
		ModelAndView model = new ModelAndView("bmsToStore/rejectforbom"); 
		System.out.println("in rejection form " );
		
		model.addObject("billOfMaterialHeader",billOfMaterialHeader);
		model.addObject("bomwithdetaild", bomwithdetaild); 
		return model;
	}
	
	
	@RequestMapping(value = "/updateRejectedQtyToStore", method = RequestMethod.POST)
	public String updateRejectedQty(HttpServletRequest request, HttpServletResponse response) {
		
		Date date= new Date();
		HttpSession session=request.getSession();
		UserResponse userResponse =(UserResponse) session.getAttribute("UserDetail");
	 
		
		int userId=userResponse.getUser().getId();
		
		for(int i=0;i<billOfMaterialHeader.getBillOfMaterialDetailed().size();i++)
		{
			System.out.println(12);
			 
			 
				System.out.println(13);
				String reject_qty=request.getParameter("rejectedQty"+billOfMaterialHeader.getBillOfMaterialDetailed().get(i).getReqDetailId());
				String return_qty=request.getParameter("returnQty"+billOfMaterialHeader.getBillOfMaterialDetailed().get(i).getReqDetailId());
				
				if(reject_qty!=null) {
					System.out.println("reject_qty Qty   :"+reject_qty);
					float rejectqty= Float.parseFloat(reject_qty);
					billOfMaterialHeader.getBillOfMaterialDetailed().get(i).setRejectedQty(rejectqty);
					System.out.println("reject_qty  :"+rejectqty);
				}
				else
				{
					billOfMaterialHeader.getBillOfMaterialDetailed().get(i).setRejectedQty(0);
				}
				
				if(return_qty!=null) {
					System.out.println("return_qty Qty   :"+return_qty);
					float returnqty= Float.parseFloat(return_qty);
					billOfMaterialHeader.getBillOfMaterialDetailed().get(i).setReturnQty(returnqty);
					System.out.println("return_qty  :"+returnqty);
				}
				else
				{
					billOfMaterialHeader.getBillOfMaterialDetailed().get(i).setReturnQty(0);
				}
				System.out.println(2);
			 
		}
		billOfMaterialHeader.setStatus(2);
		billOfMaterialHeader.setRejDate(date);
		billOfMaterialHeader.setRejUserId(userId);
		
		System.out.println(billOfMaterialHeader.toString());
		
		RestTemplate rest = new RestTemplate();
		
		Info info = rest.postForObject(Constants.url + "saveBom", billOfMaterialHeader, Info.class);	
		System.out.println(info);
		 
		 return "redirect:/getBomListBmsToStore";
	}
	
	
	
	
	@RequestMapping(value = "/approveRejectedByStore", method = RequestMethod.GET)
	public String approveRejected(HttpServletRequest request, HttpServletResponse response) {
		
		
		System.out.println("inside Approve Rejected ");
		Date date= new Date();
		HttpSession session=request.getSession();
		UserResponse userResponse =(UserResponse) session.getAttribute("UserDetail");
		
		int reqId=Integer.parseInt(request.getParameter("reqId"));
		System.out.println(reqId);
		
		int userId=userResponse.getUser().getId();
		billOfMaterialHeader.setRejApproveDate(date);
		billOfMaterialHeader.setRejApproveUserId(userId);
		
		billOfMaterialHeader.setStatus(3);//3 for Approve Rejected 
		
		RestTemplate rest = new RestTemplate();
		
		Info info = rest.postForObject(Constants.url + "saveBom", billOfMaterialHeader, Info.class);	
		System.out.println(info.toString());
		
		return "redirect:/getBomListBmsToStore";
	} 
}
