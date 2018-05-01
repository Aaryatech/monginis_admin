package com.ats.adminpanel.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.CDL;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.model.AllFrIdNameList;
import com.ats.adminpanel.model.CreditNoteList;
import com.ats.adminpanel.model.ExportToExcel;
import com.ats.adminpanel.model.Info;
import com.ats.adminpanel.model.SalesVoucherList;
import com.ats.adminpanel.model.creditnote.CreditNoteHeaderPrint;
import com.ats.adminpanel.model.creditnote.CreditNoteHeaderPrintList;
import com.ats.adminpanel.model.creditnote.CreditPrintBean;
import com.ats.adminpanel.model.creditnote.CrnSrNoDateBean;
import com.ats.adminpanel.model.creditnote.GetCreditNoteHeaders;
import com.ats.adminpanel.model.creditnote.GetCreditNoteHeadersList;
import com.ats.adminpanel.model.creditnote.GetCreditNoteReport;
import com.ats.adminpanel.model.creditnote.GetCreditNoteReportList;
import com.ats.adminpanel.model.creditnote.GetCrnDetails;
import com.ats.adminpanel.model.creditnote.GetCrnDetailsList;
import com.ats.adminpanel.model.creditnote.GetGrnGvnForCreditNote;
import com.ats.adminpanel.model.creditnote.GetGrnGvnForCreditNoteList;
import com.ats.adminpanel.model.creditnote.PostCreditNoteDetails;
import com.ats.adminpanel.model.creditnote.PostCreditNoteHeader;
import com.ats.adminpanel.model.creditnote.PostCreditNoteHeaderList;
import com.ats.adminpanel.model.login.UserResponse;
import com.ats.adminpanel.model.production.GetProdDetailBySubCat;
import com.ats.adminpanel.model.production.GetProdDetailBySubCatList;
import com.ats.adminpanel.model.production.GetProdPlanDetail;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Controller
@Scope("session")
public class CreditNoteController {

	public static float roundUp(float d) {
		return BigDecimal.valueOf(d).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
	}

	GetGrnGvnForCreditNoteList getGrnGvnForCreditNoteList;

	List<GetGrnGvnForCreditNote> getGrnGvnForCreditNote;

	@RequestMapping(value = "/showInsertCreditNote", method = RequestMethod.GET)
	public ModelAndView showCrediNotePage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("creditNote/generateCreditNote");

		return model;

	}

	int isGrn;

	@RequestMapping(value = "/getCreditNoteType", method = RequestMethod.GET)
	public String getType(HttpServletRequest request, HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("creditNote/generateCreditNote");
		System.out.println("in Side ");
		String type = request.getParameter("selected_type");

		int typeInt = Integer.parseInt(type);

		if (typeInt == 0) {

			isGrn = 0;
		} else if (typeInt == 1) {

			isGrn = 1;
		}

		return "redirect:/insertCreNoteProcess";
	}

	@RequestMapping(value = "/insertCreNoteProcess", method = RequestMethod.POST)
	public ModelAndView showInsertCreditNote(HttpServletRequest request, HttpServletResponse response) {

		System.out.println("HIIIIIIII");

		// Constants.mainAct = 11;
		// Constants.subAct = 72;

		ModelAndView model = new ModelAndView("creditNote/generateCreditNote");

		try {
			String type = request.getParameter("selectType");

			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			if (type.equals("1")) {

				map.add("isGrn", 1);
				isGrn = 1;
				// get /grnGvnDetailForCreditNote for GRN

				getGrnGvnForCreditNoteList = restTemplate.postForObject(Constants.url + "grnGvnDetailForCreditNote",
						map, GetGrnGvnForCreditNoteList.class);

			} else {
				isGrn = 0;
				// get /grnGvnDetailForCreditNote for GVN
				map.add("isGrn", 0);

				// get /grnGvnDetailForCreditNote for GRN

				getGrnGvnForCreditNoteList = restTemplate.postForObject(Constants.url + "grnGvnDetailForCreditNote",
						map, GetGrnGvnForCreditNoteList.class);

			}

			getGrnGvnForCreditNote = new ArrayList<>();

			getGrnGvnForCreditNote = getGrnGvnForCreditNoteList.getGetGrnGvnForCreditNotes();

			System.out.println("grn gvn for credit note  : " + getGrnGvnForCreditNote.toString());

			model.addObject("creditNoteList", getGrnGvnForCreditNote);

		} catch (Exception e) {

			System.out.println("Error in Getting grngvn for credit details " + e.getMessage());

			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/insertCreditNote", method = RequestMethod.POST)
	public ModelAndView insertCreditNote(HttpServletRequest request, HttpServletResponse response) {

		// Constants.mainAct = 8;
		// Constants.subAct = 85;

		ModelAndView model = new ModelAndView("creditNote/generateCreditNote");
		System.out.println("inside insert credit note ");

		try {

			HttpSession session = request.getSession();
			UserResponse userResponse = (UserResponse) session.getAttribute("UserDetail");
			int userId = userResponse.getUser().getId();

			RestTemplate restTemplate = new RestTemplate();

			String[] grnGvnIdList = request.getParameterValues("select_to_credit");

			List<GetGrnGvnForCreditNote> selectedCreditNote = new ArrayList<>();

			for (int i = 0; i < grnGvnIdList.length; i++) {

				int grnGvnId = Integer.parseInt(grnGvnIdList[i]);

				for (int j = 0; j < getGrnGvnForCreditNote.size(); j++) {

					GetGrnGvnForCreditNote creditNote = getGrnGvnForCreditNote.get(j);
					if (grnGvnId == creditNote.getGrnGvnId()) {

						selectedCreditNote.add(creditNote);
					}

				}
			}

			System.out.println("Selected Credit notes count = " + selectedCreditNote.size() + "\n Data: "
					+ selectedCreditNote.toString());

			List<PostCreditNoteHeader> creditHeaderList = new ArrayList<>();

			// List<PostCreditNoteDetails> postCreditNoteDetails=new ArrayList<>();

			for (int i = 0; i < selectedCreditNote.size(); i++) {

				System.out.println("Sr no at index  " + i + " = " + selectedCreditNote.get(i).getGrngvnSrno());

				GetGrnGvnForCreditNote creditNote = selectedCreditNote.get(i);

				System.out.println("credit note =" + creditNote.toString());

				boolean isRepeated = false;

				for (int j = 0; j < creditHeaderList.size(); j++) {

					PostCreditNoteHeader creditHeader = creditHeaderList.get(j);

					if (creditHeader.getFrId() == creditNote.getFrId()) {

						isRepeated = true;

						List<PostCreditNoteDetails> postCreditNoteDetailsListMatched = creditHeader
								.getPostCreditNoteDetails();

						DateFormat Df = new SimpleDateFormat("yyyy-MM-dd");

						Date grnGvnDate = creditNote.getGrnGvnDate();

						grnGvnDate = Df.parse(grnGvnDate.toString());
						// System.out.println("grnGvnDate= "+grnGvnDate);

						PostCreditNoteDetails creditNoteDetail = new PostCreditNoteDetails();

						creditNoteDetail.setBillNo(creditNote.getBillNo());
						creditNoteDetail.setCessRs(00);

						creditNoteDetail.setCgstPer(creditNote.getCgstPer());
						float cgstRs = (creditNote.getCgstPer() * creditNote.getAprTaxableAmt()) / 100;
						creditNoteDetail.setCgstRs(roundUp(cgstRs));

						creditNoteDetail.setSgstPer(creditNote.getSgstPer());
						float sgstRs = (creditNote.getSgstPer() * creditNote.getAprTaxableAmt()) / 100;
						creditNoteDetail.setSgstRs(roundUp(sgstRs));

						creditNoteDetail.setIgstPer(creditNote.getIgstPer());
						float igstRs = (creditNote.getIgstPer() * creditNote.getAprTaxableAmt()) / 100;
						creditNoteDetail.setIgstRs(roundUp(igstRs));

						creditNoteDetail.setDelStatus(0);
						creditNoteDetail.setGrnGvnAmt(creditNote.getAprGrandTotal());
						creditNoteDetail.setGrnGvnDate(grnGvnDate);
						creditNoteDetail.setGrnGvnId(creditNote.getGrnGvnId());
						creditNoteDetail.setGrnGvnQty(creditNote.getAprQtyAcc());

						creditNoteDetail.setGrnType(creditNote.getGrnType());

						creditNoteDetail.setIsGrn(creditNote.getIsGrn());
						creditNoteDetail.setItemId(creditNote.getItemId());

						creditNoteDetail.setTaxableAmt(creditNote.getAprTaxableAmt());
						creditNoteDetail.setTotalTax(creditNote.getAprTotalTax());

						creditNoteDetail.setBillDate(creditNote.getRefInvoiceDate());

						// newly added
						creditNoteDetail.setCatId(creditNote.getCatId());
						creditNoteDetail.setBaseRate(creditNote.getBaseRate());
						creditNoteDetail.setCessPer(0);
						creditNoteDetail.setRefInvoiceNo(creditNote.getInvoiceNo());

						// 23 Feb
						creditNoteDetail.setGrngvnSrno(creditNote.getGrngvnSrno());
						creditNoteDetail.setGrnGvnHeaderId(creditNote.getGrnGvnHeaderId());

						postCreditNoteDetailsListMatched.add(creditNoteDetail);

						creditHeader.setPostCreditNoteDetails(postCreditNoteDetailsListMatched);

						creditHeader.setCrnTaxableAmt(creditHeader.getCrnTaxableAmt() + creditNote.getAprTaxableAmt());

						creditHeader.setCrnTotalTax(creditHeader.getCrnTotalTax() + creditNote.getAprTotalTax());

						if (creditHeader.getGrnGvnSrNoList() == null) {

							creditHeader.setGrnGvnSrNoList(creditNote.getGrngvnSrno() + ",");

						} else if (!creditHeader.getGrnGvnSrNoList().contains((creditNote.getGrngvnSrno()))) {

							creditHeader.setGrnGvnSrNoList(
									creditHeader.getGrnGvnSrNoList() + creditNote.getGrngvnSrno() + ",");

						}

						// creditHeader.setGrnGvnSrNoList(creditHeader.getGrnGvnSrNoList()+","+creditNote.getGrngvnSrno()+",");//

						float grandTotal = creditHeader.getCrnTotalTax() + creditHeader.getCrnTaxableAmt();

						creditHeader.setCrnGrandTotal(Math.round(grandTotal));

						creditHeader.setCrnFinalAmt(Math.round((grandTotal)));

						float roundOff = grandTotal - roundUp(grandTotal);

						creditHeader.setRoundOff(roundOff);

					}

				}
				// Map<String, String> srNoMap = new HashMap<String, String>();

				if (!isRepeated) {

					PostCreditNoteHeader postCreditHeader = new PostCreditNoteHeader();
					DateFormat Df = new SimpleDateFormat("yyyy-MM-dd");
					Date grnGvnDate = creditNote.getGrnGvnDate();

					grnGvnDate = Df.parse(grnGvnDate.toString());

					if (isGrn == 1) {

						System.err.println("Value of isGrn ==1");
						postCreditHeader.setIsGrn(1);
					} else {
						System.err.println("Value of isGrn ==0");
						postCreditHeader.setIsGrn(0);
					}

					// System.out.println("grnGvnDate= "+grnGvnDate);

					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Calendar cal = Calendar.getInstance();
					java.sql.Date creditNoteDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());

					postCreditHeader.setCreatedDateTime(dateFormat.format(cal.getTime()));
					postCreditHeader.setCrnDate(creditNoteDate);
					postCreditHeader.setCrnFinalAmt(Math.round(creditNote.getAprGrandTotal()));
					postCreditHeader.setCrnGrandTotal(Math.round(creditNote.getAprGrandTotal()));
					postCreditHeader.setCrnTaxableAmt(creditNote.getAprTaxableAmt());
					postCreditHeader.setCrnTotalTax(creditNote.getAprTotalTax());
					postCreditHeader.setFrId(creditNote.getFrId());
					postCreditHeader.setIsTallySync(creditNote.getIsTallySync());
					postCreditHeader.setRoundOff(creditNote.getAprROff());
					postCreditHeader.setUserId(userId);
					postCreditHeader.setCrnNo("gfpl :default");

					// newly set 23 FEB

					// srNoMap.put(creditNote.getGrngvnSrno(), creditNote.getGrngvnSrno()+",");

					// System.out.println("Sr No Map "+srNoMap.toString());

					System.out.println(" sr no received  " + creditNote.getGrngvnSrno());

					if (postCreditHeader.getGrnGvnSrNoList() == null) {

						postCreditHeader.setGrnGvnSrNoList(creditNote.getGrngvnSrno() + ",");

					} else if (!postCreditHeader.getGrnGvnSrNoList().contains((creditNote.getGrngvnSrno()))) {

						postCreditHeader.setGrnGvnSrNoList(
								postCreditHeader.getGrnGvnSrNoList() + creditNote.getGrngvnSrno() + ",");

					}

					
					
					// postCreditHeader.setGrnGvnSrNoList(srNoMap.get(creditNote.getGrngvnSrno()));
					postCreditHeader.setIsDeposited(0);

					// newly set

					PostCreditNoteDetails creditNoteDetail = new PostCreditNoteDetails();

					creditNoteDetail.setBillNo(creditNote.getBillNo());
					creditNoteDetail.setCessRs(00);
					creditNoteDetail.setCgstPer(creditNote.getCgstPer());
					float cgstRs = (creditNote.getCgstPer() * creditNote.getAprTaxableAmt()) / 100;
					creditNoteDetail.setCgstRs(cgstRs);
					creditNoteDetail.setSgstPer(creditNote.getSgstPer());
					float sgstRs = (creditNote.getSgstPer() * creditNote.getAprTaxableAmt()) / 100;
					creditNoteDetail.setSgstRs(sgstRs);
					creditNoteDetail.setIgstPer(creditNote.getIgstPer());
					float igstRs = (creditNote.getIgstPer() * creditNote.getAprTaxableAmt()) / 100;
					creditNoteDetail.setIgstRs(igstRs);
					creditNoteDetail.setDelStatus(0);
					creditNoteDetail.setGrnGvnAmt(creditNote.getAprGrandTotal());
					creditNoteDetail.setGrnGvnDate(grnGvnDate);
					creditNoteDetail.setGrnGvnId(creditNote.getGrnGvnId());
					creditNoteDetail.setGrnGvnQty(creditNote.getAprQtyAcc());
					creditNoteDetail.setGrnType(creditNote.getGrnType());
					creditNoteDetail.setIsGrn(creditNote.getIsGrn());
					creditNoteDetail.setItemId(creditNote.getItemId());
					creditNoteDetail.setTaxableAmt(creditNote.getAprTaxableAmt());
					creditNoteDetail.setTotalTax(creditNote.getAprTotalTax());

					// newly added

					creditNoteDetail.setRefInvoiceNo(creditNote.getInvoiceNo());
					creditNoteDetail.setCatId(creditNote.getCatId());
					creditNoteDetail.setBaseRate(creditNote.getBaseRate());
					creditNoteDetail.setCessPer(00);
					creditNoteDetail.setBillDate(creditNote.getRefInvoiceDate());

					// newly set 23 FEB
					creditNoteDetail.setGrnGvnHeaderId(creditNote.getGrnGvnHeaderId());
					creditNoteDetail.setGrngvnSrno(creditNote.getGrngvnSrno());
					// newly set 23 FEB

					List<PostCreditNoteDetails> postCreditNoteDetailsList = new ArrayList<>();

					postCreditNoteDetailsList.add(creditNoteDetail);

					postCreditHeader.setPostCreditNoteDetails(postCreditNoteDetailsList);

					creditHeaderList.add(postCreditHeader);

				}

			} // End of for Selected Credit note List

			PostCreditNoteHeaderList postCreditNoteHeaderList = new PostCreditNoteHeaderList();

			postCreditNoteHeaderList.setPostCreditNoteHeader(creditHeaderList);

			Info info = restTemplate.postForObject(Constants.url + "postCreditNote", postCreditNoteHeaderList,
					Info.class);

		} catch (Exception e) {

			System.out.println("Error in  : Insert Credit Note " + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	String fromDate, toDate, crnFr;

	@RequestMapping(value = "/getInputForCreditNoteHeader", method = RequestMethod.GET)
	public String getInputForCreditNoteHeader(HttpServletRequest request, HttpServletResponse response) {

		fromDate = request.getParameter("fromDate");
		toDate = request.getParameter("toDate");

		String selectedFr = request.getParameter("fr_id_list");

		System.out.println("From Date " + fromDate + "toDate " + toDate + "fr ID List " + selectedFr);
		selectedFr = selectedFr.substring(1, selectedFr.length() - 1);
		selectedFr = selectedFr.replaceAll("\"", "");

		crnFr = selectedFr;

		frList = Arrays.asList(selectedFr);

		System.out.println("Fr List Final " + frList);

		return "";

	}

	public AllFrIdNameList allFrIdNameList = new AllFrIdNameList();
	public List<String> frList = new ArrayList<String>();
	List<GetCreditNoteHeaders> creditHeaderList = new ArrayList<GetCreditNoteHeaders>();

	GetCreditNoteHeadersList headerResponse = new GetCreditNoteHeadersList();
	GetCrnDetailsList crnDetailResponse = new GetCrnDetailsList();

	List<GetCrnDetails> crnDetailList = new ArrayList<GetCrnDetails>();

	@RequestMapping(value = "/showCreditNotes", method = RequestMethod.GET)
	public ModelAndView viewCreditNotes(HttpServletRequest request, HttpServletResponse response) {

		// Constants.mainAct = 8;
		// Constants.subAct = 85;

		System.out.println("inside viewCreditNote");

		ModelAndView model = new ModelAndView("creditNote/creditNoteHeaders");

		boolean isAllFrSelected = false;

		try {

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			allFrIdNameList = new AllFrIdNameList();
			try {

				allFrIdNameList = restTemplate.getForObject(Constants.url + "getAllFrIdName", AllFrIdNameList.class);

			} catch (Exception e) {
				System.out.println("Exception in getAllFrIdName" + e.getMessage());
				e.printStackTrace();

			}
			model.addObject("unSelectedFrList", allFrIdNameList.getFrIdNamesList());
		} catch (Exception e) {
			System.err.println("Exce in viewving credit note page");
		}

		return model;
	}
	
	@RequestMapping(value = "/excelForCreaditNote", method = RequestMethod.GET)
	@ResponseBody
	public CreditNoteList excelForCreaditNote(HttpServletRequest request, HttpServletResponse response) {

		CreditNoteList creditNoteList = new CreditNoteList();
		try {
			System.out.println("ala " );
			RestTemplate restTemplate = new RestTemplate();
			String checkboxes = request.getParameter("checkboxes");
			System.out.println("checkboxes " + checkboxes);
			 
			 
			System.out.println("string " + checkboxes);
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("crnId", checkboxes);
			creditNoteList = restTemplate.postForObject(Constants.url + "/tally/getCreditNotesByCrnId",map, CreditNoteList.class);
			System.out.println("creditNoteList " + creditNoteList.getCreditNoteList());
			
			try
			{
				List<ExportToExcel> exportToExcelList=new ArrayList<ExportToExcel>();
				
				ExportToExcel expoExcel=new ExportToExcel();
				List<String> rowData=new ArrayList<String>();
				 
				rowData.add("Sr no");
				rowData.add("Crn Id");
				rowData.add("Date");
				rowData.add("Type");
				rowData.add("Fr Id");
				rowData.add("Fr Code");
				rowData.add("Party Name"); 
				rowData.add("Gst No");
				rowData.add("State");
				rowData.add("Cat Id"); 
				rowData.add("Item Id");
				rowData.add("Item Code");
				rowData.add("Item Name");
				rowData.add("Hsn Code");
				rowData.add("Qty"); 
				rowData.add("Uom"); 
				rowData.add("Rate");
				rowData.add("Amount");  
				rowData.add("Sgst Per"); 
				rowData.add("Sgst Rs");
				rowData.add("Cgst Per"); 
				rowData.add("Cgst Rs");
				rowData.add("Igst Per");
				rowData.add("Igst Rs");
				rowData.add("Cess Per"); 
				rowData.add("Cess Rs");
				rowData.add("Item Discount Per");
				rowData.add("Total Discount");
				rowData.add("Rount Off"); 
				rowData.add("Total Amt");
				rowData.add("Bill Total");
				rowData.add("Taxable Amt");
				rowData.add("Total Tax");
				rowData.add("Grand Total");
				rowData.add("Ref Bill No");
				rowData.add("Ref Bill Date");
				rowData.add("Grn Gvn Sr No");
				rowData.add("Cr Db No ");
				rowData.add("Cr Db Date ");
				rowData.add("Is Grn");
				rowData.add("Erp Link");
				
				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);
				for(int i=0;i<creditNoteList.getCreditNoteList().size();i++)
				{
					  expoExcel=new ExportToExcel();
					 rowData=new ArrayList<String>();
					 
				 
					 rowData.add(""+(i+1));
					 rowData.add(""+creditNoteList.getCreditNoteList().get(i).getvNo());
					rowData.add(""+creditNoteList.getCreditNoteList().get(i).getDate());
					rowData.add(""+creditNoteList.getCreditNoteList().get(i).getvType()); 
					rowData.add(""+creditNoteList.getCreditNoteList().get(i).getFrId()); 
					rowData.add(""+creditNoteList.getCreditNoteList().get(i).getFrCode());
					rowData.add(""+creditNoteList.getCreditNoteList().get(i).getPartyName());
					rowData.add(""+creditNoteList.getCreditNoteList().get(i).getGstin());
					rowData.add(""+creditNoteList.getCreditNoteList().get(i).getState());
					rowData.add(""+creditNoteList.getCreditNoteList().get(i).getCatId());
					rowData.add(""+creditNoteList.getCreditNoteList().get(i).getItemId()); 
					rowData.add(creditNoteList.getCreditNoteList().get(i).getItemcode()); 
					rowData.add(""+creditNoteList.getCreditNoteList().get(i).getItemName());  
					rowData.add(""+creditNoteList.getCreditNoteList().get(i).getHsnCode());
					rowData.add(""+creditNoteList.getCreditNoteList().get(i).getQty());
					rowData.add(""+creditNoteList.getCreditNoteList().get(i).getUom());
					rowData.add(""+creditNoteList.getCreditNoteList().get(i).getRate()); 
					rowData.add(""+creditNoteList.getCreditNoteList().get(i).getAmount());
					rowData.add(""+creditNoteList.getCreditNoteList().get(i).getSgstPer()); 
					rowData.add(""+creditNoteList.getCreditNoteList().get(i).getSgstRs());
					rowData.add(""+creditNoteList.getCreditNoteList().get(i).getCgstPer()); 
					rowData.add(""+creditNoteList.getCreditNoteList().get(i).getCgstRs());
					rowData.add(""+creditNoteList.getCreditNoteList().get(i).getIgstPer()); 
					rowData.add(""+creditNoteList.getCreditNoteList().get(i).getIgstRs());
					rowData.add(""+creditNoteList.getCreditNoteList().get(i).getCessPer()); 
					rowData.add(""+creditNoteList.getCreditNoteList().get(i).getCessRs());
					rowData.add(""+creditNoteList.getCreditNoteList().get(i).getItemDiscPer());
					rowData.add(""+creditNoteList.getCreditNoteList().get(i).getTotalDisc()); 
					rowData.add(""+creditNoteList.getCreditNoteList().get(i).getRoundOff()); 
					rowData.add(""+creditNoteList.getCreditNoteList().get(i).getTotalAmt());
					rowData.add(""+creditNoteList.getCreditNoteList().get(i).getBillTotal()); 
					rowData.add(""+creditNoteList.getCreditNoteList().get(i).getCrnTaxableAmt()); 
					rowData.add(""+creditNoteList.getCreditNoteList().get(i).getCrnTotalTax()); 
					rowData.add(""+creditNoteList.getCreditNoteList().get(i).getCrnGrandTotal());
					
					rowData.add(""+creditNoteList.getCreditNoteList().get(i).getRefBillNo()); 
					rowData.add(""+creditNoteList.getCreditNoteList().get(i).getRefBillDate()); 
					rowData.add(""+creditNoteList.getCreditNoteList().get(i).getGrngvnSrno()); 
					rowData.add(""+creditNoteList.getCreditNoteList().get(i).getCrDbNo());
					rowData.add(""+creditNoteList.getCreditNoteList().get(i).getCrDbDate()); 
					rowData.add(""+creditNoteList.getCreditNoteList().get(i).getIsGrn());
					rowData.add(creditNoteList.getCreditNoteList().get(i).getErpLink());
					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);
					 
				}
				 
				
				
				HttpSession session = request.getSession();
				session.setAttribute("exportExcelList", exportToExcelList);
				session.setAttribute("excelName", "creaditNote");
			}catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("Exception to genrate excel ");
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return creditNoteList;

	}

	
	//excelForCreaditNoteReport new excel report
	
	//exportToExcelReport
	
	@RequestMapping(value = "/exportToExcelReport", method = RequestMethod.GET)
	@ResponseBody
	public GetCreditNoteReportList excelForCreaditNoteReport(HttpServletRequest request, HttpServletResponse response) {

		GetCreditNoteReportList creditNoteList = new GetCreditNoteReportList();
		try {
			System.out.println("ala " );
			RestTemplate restTemplate = new RestTemplate();
			String checkboxes = request.getParameter("checkboxes");
			System.out.println("checkboxes " + checkboxes);
			 
			 
			System.out.println("string " + checkboxes);
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("crnIdList", checkboxes);
			creditNoteList = restTemplate.postForObject(Constants.url + "/getCreditNoteReport",map, GetCreditNoteReportList.class);
			System.out.println("creditNoteList getCreditNoteReport " + creditNoteList.getCreditNoteReport());
			
			try
			{
				List<ExportToExcel> exportToExcelList=new ArrayList<ExportToExcel>();
				
				ExportToExcel expoExcel=new ExportToExcel();
				List<String> rowData=new ArrayList<String>();
				 
				rowData.add("Sr no");
				rowData.add("Crn Id");
				rowData.add("Date");
				rowData.add("Type");
				rowData.add("Party Name"); 
				rowData.add("Gst No");
				rowData.add("Taxable Amt");
				rowData.add("Sgst Rs");
				rowData.add("Cgst Rs");
				rowData.add("Igst Rs");
				rowData.add("Total Tax");
				rowData.add("Grand Total");
			
				
				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);
				for(int i=0;i<creditNoteList.getCreditNoteReport().size();i++)
				{
					
					GetCreditNoteReport report=creditNoteList.getCreditNoteReport().get(i);
					  expoExcel=new ExportToExcel();
					 rowData=new ArrayList<String>();
					 
				 
					 rowData.add(""+(i+1));
					 rowData.add(""+report.getCrnId());
					 rowData.add(""+report.getCrnDate());
					 if(report.getIsGrn()==1) {
						 rowData.add("GRN");
					 }else {
						 rowData.add("GVN");
					 }
					 rowData.add(""+report.getFrName());
					 rowData.add(""+report.getFrGstNo());
					 rowData.add(""+report.getCrnTaxableAmt());
					 if(report.getIsSameState()==1) {
					 rowData.add(""+report.getSgstSum());
					 rowData.add(""+report.getCgstSum());
					 rowData.add(""+0);
					 
					 }else {
						 
						 rowData.add(""+0);
						 rowData.add(""+0);
						 rowData.add(""+report.getIgstSum());
						 
					 }
					 rowData.add(""+report.getCrnTotalTax());
					 rowData.add(""+report.getCrnGrandTotal());
					
					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);
					 
				}
				 
				
				
				HttpSession session = request.getSession();
				session.setAttribute("exportExcelList", exportToExcelList);
				session.setAttribute("excelName", "creaditNote");
			}catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("Exception to genrate excel ");
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return creditNoteList;

	}

	
	
	

	@RequestMapping(value = "/getHeaders", method = RequestMethod.GET)
	public @ResponseBody List<GetCreditNoteHeaders> getHeaders(HttpServletRequest request,
			HttpServletResponse response) {

		System.out.println("inside Ajax Call");

		ModelAndView model = new ModelAndView("creditNote/creditNoteHeaders");

		boolean isAllFrSelected = false;

		try {

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			try {

				fromDate = request.getParameter("fromDate");
				toDate = request.getParameter("toDate");

				String selectedFr = request.getParameter("fr_id_list");

				System.out.println("From Date " + fromDate + "toDate " + toDate + "fr ID List " + selectedFr);
				selectedFr = selectedFr.substring(1, selectedFr.length() - 1);
				selectedFr = selectedFr.replaceAll("\"", "");

				crnFr = selectedFr;

				frList = Arrays.asList(selectedFr);

				System.out.println("Fr List Final " + frList);

				map.add("fromDate", fromDate);

				map.add("toDate", toDate);
				if (frList.contains("-1")) {
					isAllFrSelected = true;

				}

				if (isAllFrSelected) {

					map.add("frIdList", 0);

				} else {

					map.add("frIdList", selectedFr);

				}

				headerResponse = restTemplate.postForObject(Constants.url + "getCreditNoteHeaders", map,
						GetCreditNoteHeadersList.class);

				creditHeaderList = headerResponse.getCreditNoteHeaders();

				System.err.println("CH List " + creditHeaderList.toString());

			} catch (Exception e) {
				System.out.println("Exception in getAllFrIdName" + e.getMessage());
				e.printStackTrace();
			}

		} catch (Exception e) {
			System.err.println("Exce in viewving credit note page");
		}

		return creditHeaderList;
	}

	@RequestMapping(value = "/getCrnDetailList/{crnId}", method = RequestMethod.GET)
	public ModelAndView getGrnDetailList(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("crnId") int crnId) {
		ModelAndView model = new ModelAndView("creditNote/crnDetails");
		System.out.println("In detail Page");
		GetCreditNoteHeaders creditNoteHeaders = new GetCreditNoteHeaders();
		RestTemplate restTemplate = new RestTemplate();

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		map.add("crnId", crnId);
		crnDetailResponse = restTemplate.postForObject(Constants.url + "getCrnDetails", map, GetCrnDetailsList.class);

		crnDetailList = crnDetailResponse.getCrnDetails();

		System.out.println("crn Detail List******** " + crnDetailList);
		for(int i = 0;i<creditHeaderList.size();i++)
		{
			if(crnId==creditHeaderList.get(i).getCrnId())
			{
				creditNoteHeaders =creditHeaderList.get(i);
				break;
			}
		}

		model.addObject("crnDetailList", crnDetailList);
		model.addObject("creditNoteHeaders", creditNoteHeaders);

		return model;
	}
	
	
	@RequestMapping(value = "genCrnReport/{checked}/{fromDate}/{toDate}", method = RequestMethod.GET)
	public void genCrnReportPdf(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("checked") String[] checked,
			@PathVariable("fromDate") String fromDate,
			@PathVariable("toDate") String toDate) throws FileNotFoundException {
		
		
		GetCreditNoteReportList creditNoteList = new GetCreditNoteReportList();

		
		RestTemplate restTemplate = new RestTemplate();
	
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		String crnIdList = new String();
		System.out.println("checked of zero " + checked[0]);

		for (int i = 0; i < checked.length; i++) {
			System.err.println("Value checked  " + checked[i]);
			crnIdList = crnIdList + "," + checked[i];
		}

		// Getting crn Headers

		map.add("crnIdList", crnIdList);
		creditNoteList = restTemplate.postForObject(Constants.url + "/getCreditNoteReport",map, GetCreditNoteReportList.class);
		System.out.println("creditNoteList getCreditNoteReport " + creditNoteList.getCreditNoteReport());
		
		
		
		BufferedOutputStream outStream = null;
		System.out.println("Inside Pdf prod From Order Or Plan");

		//List<GetProdPlanDetail> moneyOutList = prodPlanDetailList;

		// moneyOutList = prodPlanDetailList;
		Document document = new Document(PageSize.A4);
		// ByteArrayOutputStream out = new ByteArrayOutputStream();

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();

		System.out.println("time in Gen Bill PDF ==" + dateFormat.format(cal.getTime()));
		String timeStamp = dateFormat.format(cal.getTime());
		String FILE_PATH = Constants.REPORT_SAVE;
		File file = new File(FILE_PATH);

		PdfWriter writer = null;

		FileOutputStream out = new FileOutputStream(FILE_PATH);
		try {
			writer = PdfWriter.getInstance(document, out);
		} catch (DocumentException e) {

			e.printStackTrace();
		}

		PdfPTable table = new PdfPTable(10);
		try {
			System.out.println("Inside PDF Table try");
			table.setWidthPercentage(100);
			table.setWidths(new float[] { 0.4f, 1.0f, 0.9f,1.7f,1.1f,1.1f,1.1f,0.8f,1.1f,1.0f});
			Font headFont = new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
			Font headFont1 = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
			Font f = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.UNDERLINE, BaseColor.BLUE);

			PdfPCell hcell=new PdfPCell();
			hcell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			hcell.setPadding(4);
			hcell = new PdfPCell(new Phrase("Sr.", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Crn No", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Date", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);


			
			hcell = new PdfPCell(new Phrase("Party Name", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			
			
		/*	hcell = new PdfPCell(new Phrase("GSTIN No", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			
			*/
			hcell = new PdfPCell(new Phrase("Taxable Amt", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			
			
			hcell = new PdfPCell(new Phrase("SGST", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			
			
			hcell = new PdfPCell(new Phrase("CGST", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			
			
			hcell = new PdfPCell(new Phrase("IGST", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			
			
			hcell = new PdfPCell(new Phrase("Tax Amt", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Total", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			
			
			int index = 0;
			for (GetCreditNoteReport report : creditNoteList.getCreditNoteReport()) {
				
				if(report.getIsSameState()==1) {
					report.setIgstSum(0);
				}else {
					report.setSgstSum(0);
					report.setCgstSum(0);
				}
				
				index++;
				PdfPCell cell;

				cell = new PdfPCell(new Phrase(String.valueOf(index), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  cell.setPadding(4);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(""+report.getCrnId(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				  cell.setPadding(4);
				table.addCell(cell);

				
				cell = new PdfPCell(new Phrase(report.getCrnDate(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingRight(2);
				  cell.setPadding(4);
				table.addCell(cell);
				
				
				cell = new PdfPCell(new Phrase(report.getFrName(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				  cell.setPadding(4);
				table.addCell(cell);

				
				
			/*	cell = new PdfPCell(new Phrase(report.getFrGstNo(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				  cell.setPadding(4);
				table.addCell(cell);
*/
				cell = new PdfPCell(new Phrase(""+report.getCrnTaxableAmt(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				  cell.setPadding(4);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(""+report.getSgstSum(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				  cell.setPadding(4);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(""+report.getCgstSum(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				  cell.setPadding(4);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(""+report.getIgstSum(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				  cell.setPadding(4);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(""+report.getCrnTotalTax(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				  cell.setPadding(4);
				table.addCell(cell);

				
				cell = new PdfPCell(new Phrase(""+report.getCrnGrandTotal(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				  cell.setPadding(4);
				table.addCell(cell);

				
				
				
				// FooterTable footerEvent = new FooterTable(table);
				// writer.setPageEvent(footerEvent);
			}

			
			document.open();
			Paragraph company = new Paragraph(
					"Galdhar Foods Pvt.Ltd\n",
					f);
			company.setAlignment(Element.ALIGN_CENTER);
			document.add(company);
			document.add(new Paragraph(" "));
			
			DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
			String reportDate = DF.format(new Date());

			document.add(new Paragraph("Credit note Report : " + fromDate + " To "  +toDate));
			document.add(new Paragraph("\n"));
			document.add(table);
			document.add(new Paragraph("\n"));
			
			int totalPages = writer.getPageNumber();

			System.out.println("Page no " + totalPages);

			document.close();
			// Atul Sir code to open a Pdf File
			if (file != null) {

				String mimeType = URLConnection.guessContentTypeFromName(file.getName());

				if (mimeType == null) {

					mimeType = "application/pdf";

				}

				response.setContentType(mimeType);

				response.addHeader("content-disposition", String.format("inline; filename=\"%s\"", file.getName()));

				response.setContentLength((int) file.length());

				InputStream inputStream = null;
				try {
					inputStream = new BufferedInputStream(new FileInputStream(file));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				try {
					FileCopyUtils.copy(inputStream, response.getOutputStream());
				} catch (IOException e) {
					System.out.println("Excep in Opening a Pdf File");
					e.printStackTrace();
				}
			}

		} catch (DocumentException ex) {

			System.out.println("Pdf Generation Error: BOm Prod  View Prod" + ex.getMessage());

			ex.printStackTrace();

		}
		
	}
	
	
	
	
	@RequestMapping(value = "pdf/getCrnCheckedHeaders/{checked}", method = RequestMethod.GET)
	public ModelAndView getCrnCheckedHeaders(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("checked") String[] checked) {

		ModelAndView model = new ModelAndView("creditNote/pdf/creditnotePdf");

		try {
			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			System.out.println("In detail Page");

			// String[] checked = request.getParameterValues("select_to_agree");
			String crnIdList = new String();
			System.out.println("checked of zero " + checked[0]);

			for (int i = 0; i < checked.length; i++) {
				System.err.println("Value checked  " + checked[i]);
				crnIdList = crnIdList + "," + checked[i];
			}

			// Getting crn Headers

			map.add("crnIdList", crnIdList);
			headerResponse = restTemplate.postForObject(Constants.url + "getCreditNoteHeadersByCrnIds", map,
					GetCreditNoteHeadersList.class);

			creditHeaderList = headerResponse.getCreditNoteHeaders();
			System.err.println("Crn Id List " + crnIdList);

			System.out.println("Headers = " + creditHeaderList.toString());
			crnIdList = crnIdList.substring(1, crnIdList.length());
			System.err.println("Crn Id List on removing First comma " + crnIdList);

			// Getting crn Details

			map = new LinkedMultiValueMap<String, Object>();
			map.add("crnId", crnIdList);
			crnDetailResponse = restTemplate.postForObject(Constants.url + "getCrnDetails", map,
					GetCrnDetailsList.class);
			crnDetailList = new ArrayList<>();

			crnDetailList = crnDetailResponse.getCrnDetails();

			/*
			 * GetCrnDetails crnPrintDetail = new GetCrnDetails();
			 * 
			 * List<GetCreditNoteHeaders> tempHeaderList = creditHeaderList;
			 * 
			 * creditHeaderList=new ArrayList<>();
			 * 
			 * 
			 * 
			 * 
			 * for(int i=0;i<checked.length;i++) {
			 * 
			 * for(int j=0;j<tempHeaderList.size();j++) {
			 * 
			 * if(Integer.parseInt(checked[i])==tempHeaderList.get(j).getCrnId()) {
			 * 
			 * System.err.println("crn Id added " +checked[i]);
			 * creditHeaderList.add(tempHeaderList.get(j));
			 * 
			 * } }
			 * 
			 * }
			 */

			// List<GetCrnDetails> crnPrintDetailList=new ArrayList<>()

			DateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");

			List<CreditPrintBean> printList = new ArrayList<>();

			System.err.println("header data " + creditHeaderList.toString());
			System.err.println(
					"Size of Header = " + creditHeaderList.size() + "Size of Detail =  " + crnDetailList.toString());
			CreditPrintBean printBean = new CreditPrintBean();

			for (int i = 0; i < creditHeaderList.size(); i++) {
				printBean = new CreditPrintBean();

				 System.err.println( creditHeaderList.size()+"I = " + i);

				CreditNoteHeaderPrint cNoteHeaderPrint = new CreditNoteHeaderPrint();

				cNoteHeaderPrint.setFrAddress(creditHeaderList.get(i).getFrAddress());
				cNoteHeaderPrint.setFrId(creditHeaderList.get(i).getFrId());

				cNoteHeaderPrint.setFrName(creditHeaderList.get(i).getFrName());
				cNoteHeaderPrint.setCrnId(creditHeaderList.get(i).getCrnId());
				cNoteHeaderPrint.setCrnDate(creditHeaderList.get(i).getCrnDate());

				cNoteHeaderPrint.setFrGstNo(creditHeaderList.get(i).getFrGstNo());
				cNoteHeaderPrint.setIsGrn(creditHeaderList.get(i).getIsGrn());

				List<GetCrnDetails> crnPrintDetailList = new ArrayList<>();

				List<String> srNoList = new ArrayList<String>();
				List<CrnSrNoDateBean> srNoDateList = new ArrayList<CrnSrNoDateBean>();

				String fDate = null, tDate = null;

				for (int j = 0; j < crnDetailList.size(); j++) {

					// System.err.println("J = " + j);

					if (creditHeaderList.get(i).getCrnId() == crnDetailList.get(j).getCrnId()) {

						// System.err.println("Match found = " + j);

						crnPrintDetailList.add(crnDetailList.get(j));

						Date initDateFrom = fmt.parse(crnDetailList.get(0).getGrnGvnDate());
						Date toLastDate = fmt.parse(crnDetailList.get(0).getGrnGvnDate());

						/*if (!srNoList.contains(crnDetailList.get(j).getGrngvnSrno())) {
							srNoList.add(crnDetailList.get(j).getGrngvnSrno());
						}
						*/
						
						boolean isPrev=false;
						for(CrnSrNoDateBean bean: srNoDateList) {
							
								if(bean.getSrNo().equalsIgnoreCase(crnDetailList.get(j).getGrngvnSrno())) {
									isPrev=true;
								}
							
						}
						
						if(!isPrev) {
							
							CrnSrNoDateBean bean=new CrnSrNoDateBean();
							bean.setGrnGvnDate(crnDetailList.get(j).getGrnGvnDate());
							bean.setSrNo(crnDetailList.get(j).getGrngvnSrno());
							
							//srNoDateList.get(j).setGrnGvnDate(crnDetailList.get(j).getGrnGvnDate());
							//srNoDateList.get(j).setSrNo(crnDetailList.get(j).getGrngvnSrno());
							srNoDateList.add(bean);
							
						}
				/*		
						if(!srNoDateList.contains(crnDetailList.get(j).getGrngvnSrno())) {
							
							CrnSrNoDateBean bean=new CrnSrNoDateBean();
							bean.setGrnGvnDate(crnDetailList.get(j).getGrnGvnDate());
							bean.setSrNo(crnDetailList.get(j).getGrngvnSrno());
							
							//srNoDateList.get(j).setGrnGvnDate(crnDetailList.get(j).getGrnGvnDate());
							//srNoDateList.get(j).setSrNo(crnDetailList.get(j).getGrngvnSrno());
							srNoDateList.add(bean);

						}*/
						
						
						

						if (initDateFrom.before(fmt.parse(crnDetailList.get(j).getGrnGvnDate()))) {

						} else {
							initDateFrom = fmt.parse(crnDetailList.get(j).getGrnGvnDate());
						}

						if (toLastDate.after(fmt.parse(crnDetailList.get(j).getGrnGvnDate()))) {

						} else {
							toLastDate = fmt.parse(crnDetailList.get(j).getGrnGvnDate());
						}
						fDate = fmt.format(initDateFrom);
						tDate = fmt.format(toLastDate);
					} // end of if

				} // end of Inner for

				cNoteHeaderPrint.setFromDate(fDate);
				cNoteHeaderPrint.setToDate(tDate);

				cNoteHeaderPrint.setCrnDetails(crnPrintDetailList);
				
				cNoteHeaderPrint.setSrNoDateList(srNoDateList);
				cNoteHeaderPrint.setSrNoList(srNoList);
				printBean.setCreditHeader(cNoteHeaderPrint);

				printList.add(printBean);
				System.err.println("printList = " + printList.toString());
			} // end of outer for

			System.err.println("printList = " + printList.toString());
			model.addObject("crnPrint", printList);

			System.out.println("crn Detail List******** " + crnDetailList);

		} catch (Exception e) {
			System.err.println("Exce Occured ");
			System.err.println(e.getMessage());
			e.printStackTrace();
		}

		return model;
	}
}
