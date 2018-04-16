package com.ats.adminpanel.controller;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.model.AllFrIdNameList;
import com.ats.adminpanel.model.FrMenu;
import com.ats.adminpanel.model.Info;
import com.ats.adminpanel.model.franchisee.FranchiseeList;
import com.ats.adminpanel.model.grngvn.FrSetting;
import com.ats.adminpanel.model.grngvn.GetBillsForFr;
import com.ats.adminpanel.model.grngvn.GetBillsForFrList;
import com.ats.adminpanel.model.grngvn.GetGrnConfResponse;
import com.ats.adminpanel.model.grngvn.GetGrnItemConfig;
import com.ats.adminpanel.model.grngvn.GrnGvn;
import com.ats.adminpanel.model.grngvn.GrnGvnHeader;
import com.ats.adminpanel.model.grngvn.PostGrnGvnList;
import com.ats.adminpanel.model.stock.PostFrItemStockDetail;

@Controller
@Scope("session")

public class ManualGrnController {
	
	
	
	public  String getGrnGvnSrNo(HttpServletRequest request, HttpServletResponse response,String frCode) {

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		RestTemplate restTemplate = new RestTemplate();

		

		map.add("frId", frId);
		FrSetting frSetting = restTemplate.postForObject(Constants.url + "getFrSettingValue", map, FrSetting.class);

		int settingValue = frSetting.getGrnGvnNo();

		System.out.println("Setting Value Received " + settingValue);
		int year = Year.now().getValue();
		String curStrYear = String.valueOf(year);
		curStrYear = curStrYear.substring(2);

		int preMarchYear = Year.now().getValue() - 1;
		String preMarchStrYear = String.valueOf(preMarchYear);
		preMarchStrYear = preMarchStrYear.substring(2);

		System.out.println("Pre MArch year ===" + preMarchStrYear);

		int nextYear = Year.now().getValue() + 1;
		String nextStrYear = String.valueOf(nextYear);
		nextStrYear = nextStrYear.substring(2);

		System.out.println("Next  year ===" + nextStrYear);

		int postAprilYear = nextYear + 1;
		String postAprilStrYear = String.valueOf(postAprilYear);
		postAprilStrYear = postAprilStrYear.substring(2);

		System.out.println("Post April   year ===" + postAprilStrYear);

		java.util.Date date = new java.util.Date();
		Calendar cale = Calendar.getInstance();
		cale.setTime(date);
		int month = cale.get(Calendar.MONTH);
month=month+1;
		if (month <= 3) {

			curStrYear = preMarchStrYear + curStrYear;
			System.out.println("Month <= 3::Cur Str Year " + curStrYear);
		} else if (month >= 4) {

			curStrYear = curStrYear + nextStrYear;
			System.out.println("Month >=4::Cur Str Year " + curStrYear);
		}

		////

		int length = String.valueOf(settingValue).length();

		String invoiceNo = null;

		if (length == 1)

			invoiceNo = curStrYear + "-" + frCode+"-"+"000" + settingValue;
		if (length == 2)

			invoiceNo = curStrYear + "-" + frCode+"-"+"00" + settingValue;

		if (length == 3)

			invoiceNo = curStrYear + "-" +frCode+"-"+ "0" + settingValue;

		
		
		
		System.out.println("*** invoiceNo= " + invoiceNo);
		return invoiceNo;

	}

	List<GetBillsForFr> frBillList;
	GetBillsForFrList billsForFr = new GetBillsForFrList();

	@RequestMapping(value = "/showManGrn", method = RequestMethod.GET)
	public ModelAndView showManGrn(HttpServletRequest request, HttpServletResponse response) {

		System.err.println("Inside showManGrn");
		ModelAndView model = new ModelAndView("grngvn/manGrn");
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		AllFrIdNameList allFrIdNameList = new AllFrIdNameList();
		try {

			allFrIdNameList = restTemplate.getForObject(Constants.url + "getAllFrIdName", AllFrIdNameList.class);

		} catch (Exception e) {
			System.out.println("Exception in getAllFrIdName" + e.getMessage());
			e.printStackTrace();

		}

		model.addObject("frList", allFrIdNameList.getFrIdNamesList());

		return model;

	}

	String frId;

	@RequestMapping(value = "/getBillForFr", method = RequestMethod.GET)
	public @ResponseBody List<GetBillsForFr> billForFr(HttpServletRequest request, HttpServletResponse response) {
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		frId = request.getParameter("fr_id");

		map.add("frId", frId);
		java.util.Date cDate = new java.util.Date();
		
		System.err.println("fr Id " +frId);

		String curDate = new SimpleDateFormat("dd-MM-yyyy").format(cDate);

		//map.add("curDate", curDate);

		billsForFr = new GetBillsForFrList();

		billsForFr = restTemplate.postForObject(Constants.url + "getBillsForManGrnBackEndFr", map, GetBillsForFrList.class);
		frBillList = new ArrayList<>();
		frBillList = billsForFr.getGetBillsForFr();
		
		
	

		System.err.println("Bills received " + frBillList.toString());
		return frBillList;

	}

	GetGrnConfResponse grnGvnConfResponse=new GetGrnConfResponse();

	List<GetGrnItemConfig> grnConfList,selectedGrn;

	@RequestMapping(value = "/getItemsByBillNo", method = RequestMethod.GET)
	public @ResponseBody List<GetGrnItemConfig> getItemsByBillNo(HttpServletRequest request, HttpServletResponse response) {
		System.err.println("In Jax /getItemsByBillNo 1");
		try {

			System.err.println("In Jax /getItemsByBillNo 2 try");
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			String billNo = request.getParameter("bill_no");

			System.out.println("req param billNo " + billNo +"fr Id " +frId);

			RestTemplate restTemplate = new RestTemplate();

			map.add("frId", frId);
			map.add("billNo", billNo);

			grnGvnConfResponse = restTemplate.postForObject(Constants.url + "getItemsForManGrn", map,
					GetGrnConfResponse.class);
			grnConfList = new ArrayList<>();

			grnConfList = grnGvnConfResponse.getGetGrnItemConfigs();

			System.out.println("bill table data " + grnConfList.toString());

		} catch (Exception e) {
			System.out.println("Exception in getItemsByBillNo" + e.getMessage());
			e.printStackTrace();
		}
		return grnConfList;

	}
	
	
	
	public static float roundUp(float d) {
		return BigDecimal.valueOf(d).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
	}

	
	@RequestMapping(value = "/insertManGrn", method = RequestMethod.POST)
	public ModelAndView insertManGrn(HttpServletRequest request, HttpServletResponse response) {

		System.err.println("Inside insertManGrn");
		ModelAndView model = new ModelAndView("grngvn/manGrn");
		selectedGrn=new ArrayList<>();
		try {
			
			RestTemplate restTemplate = new RestTemplate();

		
			for(int i=0;i<grnConfList.size();i++) {
			String billNo =request.getParameter(""+grnConfList.get(i).getBillDetailNo());
			
			String grnQty=request.getParameter("qty"+billNo);
			
			if(billNo!=null) {
				
				grnConfList.get(i).setAutoGrnQty(Integer.parseInt(grnQty));
				selectedGrn.add(grnConfList.get(i));
			}
			System.err.println("grnQty" + grnQty);
			
			System.err.println("Bill no "+ billNo);
				System.err.println("");	
			}
			
			System.err.println("selected GRn " +selectedGrn.toString());
			

			
			
			FranchiseeList frList = restTemplate.getForObject(Constants.url + "getFranchisee?frId={frId}",
					FranchiseeList.class, frId);
			
			java.sql.Date grnGvnDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());

			
			List<GrnGvn> postGrnGvnList = new ArrayList<GrnGvn>();

			PostGrnGvnList postGrnList = new PostGrnGvnList();

			GrnGvnHeader grnHeader = new GrnGvnHeader();
			
			float sumTaxableAmt = 0;
			float sumTaxAmt = 0;
			float sumTotalAmt = 0;

			String curDateTime = null;
			
			for (int i = 0; i < selectedGrn.size(); i++) {

				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();
				DateFormat dateFormatDate = new SimpleDateFormat("yyyy-MM-dd");
				Calendar calDate = Calendar.getInstance();

				
				/*String tempGrnQtyAuto = request.getParameter("grnqtyauto" + selectedGrn.get(i).getItemId() + "");

				String tempGrnQty = request.getParameter("grnqty" + selectedGrn.get(i).getItemId() + "");

				System.out.println("tempGrnQty ===" + tempGrnQty);

				System.out.println("tempGrnQtyAuto ===" + tempGrnQtyAuto);*/

				int grnQty = selectedGrn.get(i).getAutoGrnQty();
				
				
				/*String frGrnRemark = request.getParameter("grn_remark" + selectedGrn.get(i).getItemId());

				if (frGrnRemark == null || frGrnRemark == "") {
					frGrnRemark = "no remark entered";

				}
				*/
				if (grnQty > 0) {
					
					GrnGvn postGrnGvn = new GrnGvn();

				
				float baseRate=selectedGrn.get(i).getRate();
				float grnBaseRate = 0.0f;

				float grnRate = 0.0f;

				if (selectedGrn.get(i).getGrnType() == 0) {
					grnBaseRate = baseRate * 75 / 100;

					grnRate = (selectedGrn.get(i).getRate() * 75) / 100;
					// postGrnGvn.setGrnGvnAmt(roundUp(grnAmt));
				}

				if (selectedGrn.get(i).getGrnType() == 1) {
					grnBaseRate = baseRate * 90 / 100;
					grnRate = (selectedGrn.get(i).getRate() * 90) / 100;
					// postGrnGvn.setGrnGvnAmt(roundUp(grnAmt));
				}

				if (selectedGrn.get(i).getGrnType() == 2 || selectedGrn.get(i).getGrnType() == 4) {
					// postGrnGvn.setGrnGvnAmt(roundUp(grnAmt));

					grnBaseRate = baseRate;
					grnRate = selectedGrn.get(i).getRate();
				}

				float taxableAmt = grnBaseRate * grnQty;

				float totalTax = (taxableAmt
						* (selectedGrn.get(i).getSgstPer() + selectedGrn.get(i).getCgstPer())) / 100;

				float grandTotal = taxableAmt + totalTax;

				float finalAmt = grnRate * grnQty;

				postGrnGvn.setGrnGvnAmt(roundUp(grandTotal));
				float roundUpAmt = finalAmt - grandTotal;

				curDateTime = dateFormat.format(cal.getTime());
			
					postGrnGvn.setGrnGvnDate(grnGvnDate);

					postGrnGvn.setBillDetailNo((selectedGrn.get(i).getBillDetailNo()));// 15 Feb added

					curDateTime = dateFormat.format(cal.getTime());

					postGrnGvn.setBillNo(selectedGrn.get(i).getBillNo());
					postGrnGvn.setFrId(Integer.parseInt(frId));
					postGrnGvn.setItemId(selectedGrn.get(i).getItemId());
					postGrnGvn.setItemRate(selectedGrn.get(i).getRate());
					postGrnGvn.setItemMrp(selectedGrn.get(i).getMrp());
					postGrnGvn.setGrnGvnQty(grnQty);
					postGrnGvn.setGrnType(selectedGrn.get(i).getGrnType());
					postGrnGvn.setIsGrn(1);
					postGrnGvn.setIsGrnEdit(0);
					postGrnGvn.setGrnGvnEntryDateTime(dateFormat.format(cal.getTime()));
					postGrnGvn.setFrGrnGvnRemark("Man GRN BY Monginis");
					postGrnGvn.setGvnPhotoUpload1("grn:no photo");
					postGrnGvn.setGvnPhotoUpload2("grn:no photo");
					postGrnGvn.setGrnGvnStatus(6);
					postGrnGvn.setApprovedLoginGate(0);
					postGrnGvn.setApproveimedDateTimeGate(dateFormat.format(cal.getTime()));
					postGrnGvn.setApprovedRemarkGate(" ");

					postGrnGvn.setApprovedLoginStore(0);
					postGrnGvn.setApprovedDateTimeStore(dateFormat.format(cal.getTime()));
					postGrnGvn.setApprovedRemarkStore(" ");
					postGrnGvn.setApprovedLoginAcc(0);
					postGrnGvn.setGrnApprovedDateTimeAcc(dateFormat.format(cal.getTime()));
					postGrnGvn.setApprovedRemarkAcc("Acc Approved ");

					postGrnGvn.setDelStatus(0);
					postGrnGvn.setGrnGvnQtyAuto(grnQty);

					// newly added

					postGrnGvn.setIsTallySync(0);
					postGrnGvn.setBaseRate(roundUp(baseRate));
					postGrnGvn.setSgstPer(selectedGrn.get(i).getSgstPer());
					postGrnGvn.setCgstPer(selectedGrn.get(i).getCgstPer());
					postGrnGvn.setIgstPer(selectedGrn.get(i).getIgstPer());

					postGrnGvn.setTaxableAmt(roundUp(taxableAmt));
					postGrnGvn.setTotalTax(roundUp(totalTax));
					postGrnGvn.setFinalAmt(roundUp(finalAmt));
					postGrnGvn.setRoundUpAmt(roundUpAmt);

					postGrnGvn.setIsCreditNote(0);

					postGrnGvn.setCatId(selectedGrn.get(i).getCatId());
					postGrnGvn.setMenuId(selectedGrn.get(i).getMenuId());

					postGrnGvn.setRefInvoiceDate(selectedGrn.get(i).getBillDate());
					postGrnGvn.setInvoiceNo(selectedGrn.get(i).getInvoiceNo());

					// setting new field added on 23 FEB

					postGrnGvn.setAprQtyGate(grnQty);
					postGrnGvn.setAprQtyStore(grnQty);
					postGrnGvn.setAprQtyAcc(grnQty);
					postGrnGvn.setAprTaxableAmt(taxableAmt);
					postGrnGvn.setAprTotalTax(totalTax);
					
					if(frList.getIsSameState()==1) {
						
						postGrnGvn.setAprSgstRs((totalTax/2));
						postGrnGvn.setAprCgstRs((totalTax/2));
						
					}else {
						postGrnGvn.setAprIgstRs(totalTax);

					}
					
					postGrnGvn.setAprGrandTotal(postGrnGvn.getGrnGvnAmt());
					postGrnGvn.setAprROff(postGrnGvn.getRoundUpAmt());
					postGrnGvn.setIsSameState(frList.getIsSameState());

					System.out.println("post grn ref inv date " + postGrnGvn.getRefInvoiceDate());

					// 15 Feb
					sumTaxableAmt = sumTaxableAmt + postGrnGvn.getTaxableAmt();
					sumTaxAmt = sumTaxAmt + postGrnGvn.getTotalTax();
					sumTotalAmt = sumTotalAmt + postGrnGvn.getGrnGvnAmt();

					postGrnGvnList.add(postGrnGvn);
					
				} // end of if checking for grnQty
			} // end of for

			grnHeader.setGrnGvn(postGrnGvnList);

			grnHeader.setFrId(Integer.parseInt(frId));
			grnHeader.setApporvedAmt(sumTotalAmt);
			grnHeader.setApprovedDatetime(curDateTime);
			grnHeader.setCreditNoteId(0);
			grnHeader.setGrngvnDate(new SimpleDateFormat("dd-MM-yyyy").format(grnGvnDate));
			grnHeader.setGrngvnSrno(getGrnGvnSrNo(request, response,frList.getFrCode()));
			grnHeader.setGrngvnStatus(6);
			grnHeader.setIsCreditNote(0);
			grnHeader.setIsGrn(1);
			grnHeader.setAprGrandTotal(sumTotalAmt);

			grnHeader.setTaxableAmt(sumTaxableAmt);
			grnHeader.setTaxAmt(sumTaxAmt);
			grnHeader.setTotalAmt(sumTotalAmt);
			grnHeader.setGrnGvn(postGrnGvnList);
			grnHeader.setAprTaxableAmt(sumTaxableAmt);
			
			grnHeader.setAprTotalTax(sumTaxAmt);
			if(frList.getIsSameState()==1) {
				
				grnHeader.setAprCgstRs((sumTaxAmt/2));	
				grnHeader.setAprSgstRs((sumTaxAmt/2));	
				
			}else {
				grnHeader.setAprIgstRs((sumTaxAmt));	

			}
			
			postGrnList.setGrnGvnHeader(grnHeader);
			Info insertGrn = null;
			if(postGrnList!=null) {
						insertGrn = restTemplate.postForObject(Constants.url + "insertGrnGvn", postGrnList, Info.class);
						
			}
						//Info insertGrn=null;
						if (insertGrn.getError() == false) {
							
							MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

							

							map.add("frId", frList.getFrId());
							FrSetting frSetting = restTemplate.postForObject(Constants.url + "getFrSettingValue", map,
									FrSetting.class);

							int grnGvnSrNo = frSetting.getGrnGvnNo();

							grnGvnSrNo = grnGvnSrNo + 1;

							map = new LinkedMultiValueMap<String, Object>();

							map.add("frId", frList.getFrId());
							map.add("grnGvnNo", grnGvnSrNo);

							Info info = restTemplate.postForObject(Constants.url + "updateFrSettingGrnGvnNo", map, Info.class);

							System.out.println("/updateFrSettingGrnGvnNo: Response @ManualGrInsertBackEnd  info=  " + info.toString());
							
							
						}
			
		}
		catch (Exception e) {
			
		System.err.println("Exception in insert Man GRN " + e.getMessage());
		e.printStackTrace();
		
		}
		
		
		return model;
		
	}

}
