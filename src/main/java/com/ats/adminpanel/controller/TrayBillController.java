package com.ats.adminpanel.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.ats.adminpanel.model.AllFrIdName;
import com.ats.adminpanel.model.AllFrIdNameList;
import com.ats.adminpanel.model.Info;
import com.ats.adminpanel.model.TrayType;
import com.ats.adminpanel.model.item.FrItemStockConfigureList;
import com.ats.adminpanel.model.tray.TrayMgtDetail;
import com.ats.adminpanel.model.tray.TrayMgtDetailBean;


@Controller
@Scope("session")
public class TrayBillController {
	
	
	@RequestMapping(value = "/showTrayBill", method = RequestMethod.GET)
	public ModelAndView showTrayBill(HttpServletRequest request, HttpServletResponse response) {

		//Constants.mainAct=17;
		//Constants.subAct =90;

		ModelAndView model = new ModelAndView("trayBill/trayBills");
		try {
			RestTemplate restTemplate = new RestTemplate();

			TrayMgtDetail[] trayMgtDetails=restTemplate.getForObject(Constants.url + "/traymgt/getTrayMgtDetailsForBill",TrayMgtDetail[].class);

			ArrayList<TrayMgtDetail> trayMgtDetailsList=new ArrayList<TrayMgtDetail>(Arrays.asList(trayMgtDetails));
			
			System.out.println("trayMgtDetailsList"+trayMgtDetailsList.toString());
			
		    TrayType[] trayTypes=restTemplate.getForObject(Constants.url+"/getTrayTypes", TrayType[].class);
		    
			ArrayList<TrayType> trayTypeList=new ArrayList<TrayType>(Arrays.asList(trayTypes));

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			
			String settingKey = "tray_gst_per";
			map.add("settingKeyList", settingKey);
			FrItemStockConfigureList settingList = restTemplate.postForObject(Constants.url + "getDeptSettingValue", map,FrItemStockConfigureList.class);
		
		  model.addObject("gstPer", settingList.getFrItemStockConfigure().get(0).getSettingValue());
		  model.addObject("smallPrice", trayTypeList.get(0).getTrayPrice());
		  model.addObject("bigPrice", trayTypeList.get(1).getTrayPrice());
		  model.addObject("leadPrice", trayTypeList.get(2).getTrayPrice());
		  model.addObject("extraPrice", trayTypeList.get(3).getTrayPrice());

		  model.addObject("trayMgtDetailsList",trayMgtDetailsList);
		  
		  
		} catch (Exception e) {
			System.out.println("Exc In showTrayBills:" + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/insertTrayBills", method = RequestMethod.POST)
	public String insertTrayBills(HttpServletRequest request, HttpServletResponse response) {


		ModelAndView model = new ModelAndView("trayBill/trayBills");
	  try {
			RestTemplate restTemplate = new RestTemplate();
			DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			DateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
			
			TrayMgtDetail[] trayMgtDetails=restTemplate.getForObject(Constants.url + "/traymgt/getTrayMgtDetailsForBill",TrayMgtDetail[].class);

			ArrayList<TrayMgtDetail> trayMgtDetailsList=new ArrayList<TrayMgtDetail>(Arrays.asList(trayMgtDetails));
			
		    TrayType[] trayTypes=restTemplate.getForObject(Constants.url+"/getTrayTypes", TrayType[].class);
		    
			ArrayList<TrayType> trayTypeList=new ArrayList<TrayType>(Arrays.asList(trayTypes));

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			
			String settingKey = "tray_gst_per";
			map.add("settingKeyList", settingKey);
			FrItemStockConfigureList settingList = restTemplate.postForObject(Constants.url + "getDeptSettingValue", map,FrItemStockConfigureList.class);
		
			List<TrayMgtDetailBean> trayMgtDetailBeanList=new ArrayList<TrayMgtDetailBean>();
			for(int i=0;i<trayMgtDetailsList.size();i++)
			{
				int tranDetailId=0;
				try {
				 tranDetailId=Integer.parseInt(request.getParameter("tranDetailId"+trayMgtDetailsList.get(i).getTranDetailId()));
				}
				catch(Exception e)
				{
					tranDetailId=0;
					e.printStackTrace();
				}
                if(tranDetailId>0)
                {
                	
                	
                	TrayMgtDetailBean trayMgtDetailBean=new TrayMgtDetailBean();
                	
                	trayMgtDetailBean.setTranDetailId(tranDetailId);
                	trayMgtDetailBean.setTranId(trayMgtDetailsList.get(i).getTranId());
                	trayMgtDetailBean.setFrId(trayMgtDetailsList.get(i).getFrId());
                	trayMgtDetailBean.setOuttrayDate(sdf1.format(sdf.parse((trayMgtDetailsList.get(i).getOuttrayDate()))));
                	
                	trayMgtDetailBean.setOuttrayBig(trayMgtDetailsList.get(i).getOuttrayBig());
                	trayMgtDetailBean.setOuttraySmall(trayMgtDetailsList.get(i).getOuttraySmall());
                	trayMgtDetailBean.setOuttrayLead(trayMgtDetailsList.get(i).getOuttrayLead());
                	trayMgtDetailBean.setOuttrayExtra(trayMgtDetailsList.get(i).getOuttrayExtra());
                	
                   	trayMgtDetailBean.setIntrayDate(sdf1.format(sdf.parse((trayMgtDetailsList.get(i).getIntrayDate()))));
                	trayMgtDetailBean.setIntrayBig(trayMgtDetailsList.get(i).getIntrayBig());
                	trayMgtDetailBean.setIntraySmall(trayMgtDetailsList.get(i).getIntraySmall());
                	trayMgtDetailBean.setIntrayLead(trayMgtDetailsList.get(i).getIntrayLead());
                	trayMgtDetailBean.setIntrayExtra(trayMgtDetailsList.get(i).getIntrayExtra());

                	trayMgtDetailBean.setIntrayDate1(sdf1.format(sdf.parse((trayMgtDetailsList.get(i).getIntrayDate1()))));
                	trayMgtDetailBean.setIntrayBig1(trayMgtDetailsList.get(i).getIntrayBig1());
                	trayMgtDetailBean.setIntraySmall1(trayMgtDetailsList.get(i).getIntraySmall1());
                	trayMgtDetailBean.setIntrayLead1(trayMgtDetailsList.get(i).getIntrayLead1());
                	trayMgtDetailBean.setIntrayExtra1(trayMgtDetailsList.get(i).getIntrayExtra1());

                	trayMgtDetailBean.setBalanceBig(trayMgtDetailsList.get(i).getBalanceBig());
                	trayMgtDetailBean.setBalanceSmall(trayMgtDetailsList.get(i).getBalanceSmall());
                	trayMgtDetailBean.setBalanceLead(trayMgtDetailsList.get(i).getBalanceLead());
                	trayMgtDetailBean.setBalanceExtra(trayMgtDetailsList.get(i).getBalanceExtra());
                	
                	trayMgtDetailBean.setIntrayDate2(sdf1.format(sdf.parse((trayMgtDetailsList.get(i).getIntrayDate2()))));
                	trayMgtDetailBean.setIntrayBig2(trayMgtDetailsList.get(i).getIntrayBig2());
                	trayMgtDetailBean.setIntraySmall2(trayMgtDetailsList.get(i).getIntraySmall2());
                	trayMgtDetailBean.setIntrayLead2(trayMgtDetailsList.get(i).getIntrayLead2());
                	trayMgtDetailBean.setIntrayExtra2(trayMgtDetailsList.get(i).getIntrayExtra2());
                	
                 	trayMgtDetailBean.setPriceSmall(trayTypeList.get(0).getTrayPrice());
                	trayMgtDetailBean.setPriceBig(trayTypeList.get(1).getTrayPrice());
                	trayMgtDetailBean.setPriceLead(trayTypeList.get(2).getTrayPrice());
                	trayMgtDetailBean.setPriceExtra(trayTypeList.get(3).getTrayPrice());

                	trayMgtDetailBean.setQtyBig(trayMgtDetailsList.get(i).getBalanceBig());
                	trayMgtDetailBean.setQtyExtra(trayMgtDetailsList.get(i).getBalanceExtra());
                	trayMgtDetailBean.setQtyLead(trayMgtDetailsList.get(i).getBalanceLead());
                	trayMgtDetailBean.setQtySmall(trayMgtDetailsList.get(i).getBalanceSmall());  
                	
                	float smallQtyPrice=(trayTypeList.get(0).getTrayPrice())*(trayMgtDetailsList.get(i).getBalanceSmall());
                	float bigQtyPrice=(trayTypeList.get(1).getTrayPrice())*(trayMgtDetailsList.get(i).getBalanceBig());
                	float leadQtyPrice=(trayTypeList.get(2).getTrayPrice())*(trayMgtDetailsList.get(i).getBalanceLead());
                	float extraQtyPrice=(trayTypeList.get(3).getTrayPrice())*(trayMgtDetailsList.get(i).getBalanceExtra());
                	
                	float taxableAmt=bigQtyPrice+smallQtyPrice+leadQtyPrice+extraQtyPrice;
                	float taxAmt=taxableAmt*(settingList.getFrItemStockConfigure().get(0).getSettingValue())/100;
                    float grandTotal=taxableAmt+taxAmt;
                    
                	trayMgtDetailBean.setGrandTotal(grandTotal);
                	trayMgtDetailBean.setTrayStatus(5);
                	trayMgtDetailBean.setDepositIsUsed(1);
                	trayMgtDetailBean.setDelStatus(0);
                	
                	trayMgtDetailBean.setGstPer(settingList.getFrItemStockConfigure().get(0).getSettingValue());
                	trayMgtDetailBean.setGstRs(taxAmt);
                
                	trayMgtDetailBean.setTaxAmt(taxAmt);
                	trayMgtDetailBean.setTaxableAmt(taxableAmt);
                	trayMgtDetailBean.setIsSameDay(trayMgtDetailsList.get(i).getIsSameDay());

                	trayMgtDetailBeanList.add(trayMgtDetailBean);
                }
			
			}
			
			Info info=restTemplate.postForObject(Constants.url +"/traymgt/saveTrayMgtDetailForBill" , trayMgtDetailBeanList, Info.class);
			System.out.println("info Res"+info.toString());
			
		} catch (Exception e) {
			System.out.println("Exc In insertTrayBills:" + e.getMessage());
		}
		return "redirect:/showTrayBill";
		
	}
	@RequestMapping(value = "/viewTrayBills", method = RequestMethod.GET)
	public ModelAndView viewTrayBills(HttpServletRequest request, HttpServletResponse response) {

		//Constants.mainAct=17;
		//Constants.subAct =90;

		ModelAndView model = new ModelAndView("trayBill/showTrayBills");
		try {
			RestTemplate restTemplate = new RestTemplate();

			ZoneId z = ZoneId.of("Asia/Calcutta");

			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			String todaysDate = date.format(formatters);
			
			AllFrIdNameList allFrIdNameList = new AllFrIdNameList();
			try {

				allFrIdNameList = restTemplate.getForObject(Constants.url + "getAllFrIdName", AllFrIdNameList.class);

			} catch (Exception e) {
				System.out.println("Exception in getAllFrIdName" + e.getMessage());
				e.printStackTrace();

			}

			System.out.println(" Fr " + allFrIdNameList.getFrIdNamesList());
		  model.addObject("allFrIdNameList",allFrIdNameList.getFrIdNamesList());
		  model.addObject("todaysDate", todaysDate);
		  
		} catch (Exception e) {
			System.out.println("Exc In viewTrayBills:" + e.getMessage());
		}
		return model;
	}
	List<TrayMgtDetail>	billList = new ArrayList<TrayMgtDetail>();
	List<TrayMgtDetail>	billsList = new ArrayList<TrayMgtDetail>();

		@RequestMapping(value = "/getTrayBillListProcess", method = RequestMethod.GET)
		public @ResponseBody List<TrayMgtDetail> getTrayBillListProcess(HttpServletRequest request,
				HttpServletResponse response) {

			//Constants.mainAct = 8;
			//Constants.subAct = 83;


			try {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				RestTemplate restTemplate = new RestTemplate();

				String routeId = "0";
				String frIdString = "";

				System.out.println("inside getTrayBillListProcess ajax call");

				frIdString = request.getParameter("fr_id_list");
				String fromDate = request.getParameter("from_date");
				String toDate = request.getParameter("to_date");

				boolean isAllFrSelected = false;

				frIdString = frIdString.substring(1, frIdString.length() - 1);
				frIdString = frIdString.replaceAll("\"", "");

				
				if (frIdString.contains("-1")) {
					isAllFrSelected = true;

				}

				
				if (isAllFrSelected) {

					map.add("fromDate", fromDate);
					map.add("toDate", toDate);
					map.add("frIds", frIdString);
					System.out.println("Inside IF  is All fr Selected " + isAllFrSelected);

					 ParameterizedTypeReference<List<TrayMgtDetail>> typeRef = new ParameterizedTypeReference<List<TrayMgtDetail>>() {
						};
						ResponseEntity<List<TrayMgtDetail>> responseEntity = restTemplate.exchange(Constants.url + "/traymgt/viewTrayMgtBillDetails",
								HttpMethod.POST, new HttpEntity<>(map), typeRef);
					
						billList =new ArrayList<TrayMgtDetail>();
						billList =responseEntity.getBody();
						System.out.println("billList"+billList.toString());
				} else { // few franchisee selected

					 map = new LinkedMultiValueMap<String, Object>();
					System.out.println("Inside Else: Few Fr Selected ");
					map.add("frIds", frIdString);
					map.add("fromDate", fromDate);
					map.add("toDate", toDate);

					 ParameterizedTypeReference<List<TrayMgtDetail>> typeRef = new ParameterizedTypeReference<List<TrayMgtDetail>>() {
						};
						ResponseEntity<List<TrayMgtDetail>> responseEntity = restTemplate.exchange(Constants.url + "/traymgt/viewTrayMgtBillDetails",
								HttpMethod.POST, new HttpEntity<>(map), typeRef);
						billList =new ArrayList<TrayMgtDetail>();
						billList =responseEntity.getBody();
						System.out.println("billList"+billList.toString());
				}

			} catch (Exception e) {

				System.out.println("Ex in getting trayBillList" + e.getMessage());
				e.printStackTrace();
			}

			return billList;

		}
	/*	@RequestMapping(value = "/getSelectedTrayIdForPrint", method = RequestMethod.GET)
		public void getSelectedTrayIdForPrint(HttpServletRequest request, HttpServletResponse response) {

			System.out.println("IN Metjod");

			billsList = new ArrayList<TrayMgtDetail>();
			String selectedId = request.getParameter("id");
			selectedId = selectedId.substring(1, selectedId.length() - 1);
			selectedId = selectedId.replaceAll("\"", "");

			System.out.println("selectedId  " + selectedId);

			List<String> selectedIdList = new ArrayList<>();
			System.out.println("billList  " + billList.toString());
			selectedIdList = Arrays.asList(selectedId.split(","));
			for (int i = 0; i < billList.size(); i++) {
				for (int j = 0; j < selectedIdList.size(); j++) {
					if (Integer.parseInt(selectedIdList.get(j)) == billList.get(i).getTranDetailId()) {
						System.out.println(i);
						billsList.add(billList.get(i));
					}
				}
			}

		}*/

		@RequestMapping(value = "/printSelectedTrayBill/{selectedId}", method = RequestMethod.GET)
		public ModelAndView printSelectedTrayBill(@PathVariable("selectedId") String selectedId,HttpServletRequest request, HttpServletResponse response) {
			ModelAndView model = new ModelAndView("trayBill/trayBillPrint");
			System.out.println("IN Print Selected Bill");
            try {
			System.out.println("IN Metjod");

			billsList = new ArrayList<TrayMgtDetail>();
			//String selectedId = request.getParameter("id");
		/*	selectedId = selectedId.substring(1, selectedId.length() - 1);
			selectedId = selectedId.replaceAll("\"", "");*/

			System.out.println("selectedId  " + selectedId);

			List<String> selectedIdList = new ArrayList<>();
			System.out.println("billList  " + billList.toString());
			selectedIdList = Arrays.asList(selectedId.split(","));
			for (int i = 0; i < billList.size(); i++) {
				for (int j = 0; j < selectedIdList.size(); j++) {
					if (Integer.parseInt(selectedIdList.get(j)) == billList.get(i).getTranDetailId()) {
						System.out.println(i);
						billsList.add(billList.get(i));
					}
				}
			}
			System.out.println("Selected List " + billsList.toString());
			model.addObject("billsList", billsList);
			
			model.addObject("invNo","");


			model.addObject("date", new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
			System.out.println("After print ");
            }
            catch (Exception e) {
				System.out.println(e.getMessage());
			}
			return model;
		}
}
