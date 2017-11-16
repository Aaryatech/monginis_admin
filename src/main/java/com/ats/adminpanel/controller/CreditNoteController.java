package com.ats.adminpanel.controller;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.model.Info;
import com.ats.adminpanel.model.creditnote.GetGrnGvnForCreditNote;
import com.ats.adminpanel.model.creditnote.GetGrnGvnForCreditNoteList;
import com.ats.adminpanel.model.creditnote.PostCreditNoteDetails;
import com.ats.adminpanel.model.creditnote.PostCreditNoteHeader;
import com.ats.adminpanel.model.creditnote.PostCreditNoteHeaderList;

@Controller
public class CreditNoteController {

	
	public static float roundUp(float d) {
		return BigDecimal.valueOf(d).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
	}

	
	
	GetGrnGvnForCreditNoteList getGrnGvnForCreditNoteList;

	List<GetGrnGvnForCreditNote> getGrnGvnForCreditNote;

	@RequestMapping(value = "/showInsertCreditNote", method = RequestMethod.GET)
	public ModelAndView showInsertCreditNote(HttpServletRequest request, HttpServletResponse response) {

		Constants.mainAct = 8;
		Constants.subAct = 85;

		ModelAndView model = new ModelAndView("creditNote/generateCreditNote");

		try {

			RestTemplate restTemplate = new RestTemplate();

			getGrnGvnForCreditNoteList = restTemplate.getForObject(Constants.url + "grnGvnDetailForCreditNote",
					GetGrnGvnForCreditNoteList.class);

			getGrnGvnForCreditNote = new ArrayList<>();

			getGrnGvnForCreditNote = getGrnGvnForCreditNoteList.getGetGrnGvnForCreditNotes();
			
			
			
			System.out.println("grn gvn for credit note  : " + getGrnGvnForCreditNote.toString());

		} catch (Exception e) {

			System.out.println("Error in Getting grngvn for credit details " + e.getMessage());

			e.printStackTrace();
		}

		model.addObject("creditNoteList", getGrnGvnForCreditNote);

		return model;

	}

	@RequestMapping(value = "/insertCreditNote", method = RequestMethod.POST)
	public ModelAndView insertCreditNote(HttpServletRequest request, HttpServletResponse response) {

		Constants.mainAct = 8;
		Constants.subAct = 85;
		
		ModelAndView model = new ModelAndView("creditNote/generateCreditNote");
		System.out.println("inside insert credit note ");
				
		try {

			RestTemplate restTemplate = new RestTemplate();

			String[] grnGvnIdList = request.getParameterValues("select_to_credit");

			for (int i = 0; i < grnGvnIdList.length; i++) {

				System.out.println("selected grngvn Id " + grnGvnIdList[i]);

			}

			List<GetGrnGvnForCreditNote> selectedCreditNote=new ArrayList<>();
			
			
			for(int i=0 ;i<grnGvnIdList.length;i++) {
				
				
				int grnGvnId= Integer.parseInt(grnGvnIdList[i]);
				
				for(int j=0;j<getGrnGvnForCreditNote.size();j++) {
					
					GetGrnGvnForCreditNote creditNote=getGrnGvnForCreditNote.get(j);
					if(grnGvnId==creditNote.getGrnGvnId()) {
						
						
						selectedCreditNote.add(creditNote);
					}
					
				}
			}
			
			System.out.println("Selected Credit notes count = "+selectedCreditNote.size()+"\n Data: " + selectedCreditNote.toString());
			
			List<PostCreditNoteHeader> creditHeaderList =new ArrayList<>();
			
			List<PostCreditNoteDetails> postCreditNoteDetails=new ArrayList<>();
			
			for(int i=0;i<selectedCreditNote.size();i++) {
				
				GetGrnGvnForCreditNote creditNote=selectedCreditNote.get(i);
				
				boolean isRepeated=false;
				
				for(int j=0;j<creditHeaderList.size();j++) {
					
					PostCreditNoteHeader creditHeader=creditHeaderList.get(j);
					
					if(creditHeader.getFrId()==creditNote.getFrId()) {
						
						isRepeated=true;
						
						PostCreditNoteHeader postCreditHeaderMatched=new  PostCreditNoteHeader();
												
						List<PostCreditNoteDetails> postCreditNoteDetailsListMatched=creditHeader.getPostCreditNoteDetails();
												
						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Calendar cal = Calendar.getInstance();

						java.sql.Date creditNoteDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
						
						DateFormat Df = new SimpleDateFormat("yyyy-MM-dd");
						
						Date grnGvnDate=creditNote.getGrnGvnDate();
						
						
						grnGvnDate=Df.parse(grnGvnDate.toString());
						System.out.println("grnGvnDate= "+grnGvnDate);
						
						PostCreditNoteDetails creditNoteDetail=new PostCreditNoteDetails();
						
						creditNoteDetail.setBillNo(creditNote.getBillNo());
						creditNoteDetail.setCessRs(00);
						
						creditNoteDetail.setCgstPer(creditNote.getCgstPer());
						float cgstRs=creditNote.getCgstPer()*creditNote.getTaxableAmt()/100;
						creditNoteDetail.setCgstRs(cgstRs);
						
						creditNoteDetail.setSgstPer(creditNote.getSgstPer());
						float sgstRs=creditNote.getSgstPer()*creditNote.getTaxableAmt()/100;
						creditNoteDetail.setSgstRs(sgstRs);
						

						creditNoteDetail.setIgstPer(creditNote.getIgstPer());
						float igstRs=creditNote.getIgstPer()*creditNote.getTaxableAmt()/100;
						creditNoteDetail.setIgstRs(igstRs);
						
						creditNoteDetail.setDelStatus(0);
						creditNoteDetail.setGrnGvnAmt(creditNote.getGrnGvnAmt());
						creditNoteDetail.setGrnGvnDate(grnGvnDate);
						creditNoteDetail.setGrnGvnId(creditNote.getGrnGvnId());
						creditNoteDetail.setGrnGvnQty(creditNote.getGrnGvnQty());
						
						creditNoteDetail.setGrnType(creditNote.getGrnType());
						
						creditNoteDetail.setIsGrn(creditNote.getIsGrn());
						creditNoteDetail.setItemId(creditNote.getItemId());
						
						creditNoteDetail.setTaxableAmt(creditNote.getTaxableAmt());
						creditNoteDetail.setTotalTax(creditNote.getTotalTax());
						
						
						creditNoteDetail.setBillDate(creditNote.getRefInvoiceDate());
						
						//newly added
						creditNoteDetail.setCatId(creditNote.getCatId());
						creditNoteDetail.setBaseRate(creditNote.getBaseRate());
						creditNoteDetail.setCessPer(0);
						creditNoteDetail.setRefInvoiceNo(creditNote.getInvoiceNo());
						
						postCreditNoteDetailsListMatched.add(creditNoteDetail);
						
						creditHeader.setPostCreditNoteDetails(postCreditNoteDetailsListMatched);
						
						creditHeader.setCrnTaxableAmt(creditHeader.getCrnTaxableAmt()+creditNote.getTaxableAmt());
						
						creditHeader.setCrnTotalTax(creditHeader.getCrnTotalTax()+creditNote.getTotalTax());
						
						float grandTotal=creditHeader.getCrnTotalTax() + creditHeader.getCrnTaxableAmt();
						
						creditHeader.setCrnGrandTotal(grandTotal);
						
						creditHeader.setCrnFinalAmt(roundUp(grandTotal));
						
						float roundOff=grandTotal-roundUp(grandTotal);
																		
						creditHeader.setRoundOff(roundOff);
															
					}
				
				}
				
				if(!isRepeated) {
					
					PostCreditNoteHeader postCreditHeader=new  PostCreditNoteHeader();
					
					
					
					DateFormat Df = new SimpleDateFormat("yyyy-MM-dd");
					
					Date grnGvnDate=creditNote.getGrnGvnDate();
					
					
					grnGvnDate=Df.parse(grnGvnDate.toString());
					System.out.println("grnGvnDate= "+grnGvnDate);
					
					
					
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Calendar cal = Calendar.getInstance();

					java.sql.Date creditNoteDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
					
					postCreditHeader.setCreatedDateTime(dateFormat.format(cal.getTime()));
					postCreditHeader.setCrnDate(creditNoteDate);
					postCreditHeader.setCrnFinalAmt(creditNote.getFinalAmt());
					postCreditHeader.setCrnGrandTotal(creditNote.getGrnGvnAmt());
					postCreditHeader.setCrnTaxableAmt(creditNote.getTaxableAmt());
					postCreditHeader.setCrnTotalTax(creditNote.getTotalTax());
					postCreditHeader.setFrId(creditNote.getFrId());
					postCreditHeader.setIsTallySync(creditNote.getIsTallySync());
					postCreditHeader.setRoundOff(creditNote.getRoundUpAmt());
					postCreditHeader.setUserId(0);
					postCreditHeader.setCrnNo("gfpl :default");
					
						
					PostCreditNoteDetails creditNoteDetail=new PostCreditNoteDetails();
															
					creditNoteDetail.setBillNo(creditNote.getBillNo());
					creditNoteDetail.setCessRs(00);
					
					creditNoteDetail.setCgstPer(creditNote.getCgstPer());
					float cgstRs=(creditNoteDetail.getCgstPer()*creditNoteDetail.getTaxableAmt())/100;
					System.out.println("getCgstPer  "+creditNoteDetail.getCgstPer());

					System.out.println("cgst Rs "+cgstRs);
					creditNoteDetail.setCgstRs(cgstRs);
					
					
					creditNoteDetail.setSgstPer(creditNote.getSgstPer());
					float sgstRs=(creditNoteDetail.getSgstPer()*creditNoteDetail.getTaxableAmt())/100;
					creditNoteDetail.setSgstRs(sgstRs);
					System.out.println("getSgstPer  "+creditNoteDetail.getSgstPer());

					System.out.println("sgstRs Rs "+sgstRs);


					creditNoteDetail.setIgstPer(creditNote.getIgstPer());
					float igstRs=(creditNoteDetail.getIgstPer()*creditNoteDetail.getTaxableAmt())/100;
					creditNoteDetail.setIgstRs(igstRs);
					
					creditNoteDetail.setDelStatus(0);
					creditNoteDetail.setGrnGvnAmt(creditNote.getGrnGvnAmt());
					creditNoteDetail.setGrnGvnDate(grnGvnDate);
					creditNoteDetail.setGrnGvnId(creditNote.getGrnGvnId());
					creditNoteDetail.setGrnGvnQty(creditNote.getGrnGvnQty());
					
					creditNoteDetail.setGrnType(creditNote.getGrnType());
					
					creditNoteDetail.setIsGrn(creditNote.getIsGrn());
					creditNoteDetail.setItemId(creditNote.getItemId());
					
					creditNoteDetail.setTaxableAmt(creditNote.getTaxableAmt());
					creditNoteDetail.setTotalTax(creditNote.getTotalTax());
					
					//newly added
					
					creditNoteDetail.setRefInvoiceNo(creditNote.getInvoiceNo());
					creditNoteDetail.setCatId(creditNote.getCatId());
					creditNoteDetail.setBaseRate(creditNote.getBaseRate());
					creditNoteDetail.setCessPer(00);
					creditNoteDetail.setBillDate(creditNote.getRefInvoiceDate());
					
					
					List<PostCreditNoteDetails> postCreditNoteDetailsList=new ArrayList<>();
					
					postCreditNoteDetailsList.add(creditNoteDetail);
									
					postCreditHeader.setPostCreditNoteDetails(postCreditNoteDetailsList);
					
					creditHeaderList.add(postCreditHeader);
					
				}
							
			}
			
			System.out.println("header List "+creditHeaderList.toString());
			
			
			float totalCrnTaxableAmt = 0.0f;

			
			
			PostCreditNoteHeaderList postCreditNoteHeaderList=new PostCreditNoteHeaderList();
			
			
			postCreditNoteHeaderList.setPostCreditNoteHeader(creditHeaderList);
			
			
			Info info=restTemplate.postForObject(Constants.url+"postCreditNote", postCreditNoteHeaderList,Info.class);
			
			
			System.out.println("taxable amt =" + totalCrnTaxableAmt);
			
		} catch (Exception e) {
		
			System.out.println("Error in  : Insert Credit "+e.getMessage());
			
			e.printStackTrace();
			
			
		}

		return model;

	}

}
