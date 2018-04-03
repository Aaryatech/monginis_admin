package com.ats.adminpanel.controller;

import java.math.BigDecimal;
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
import com.ats.adminpanel.model.Info;
import com.ats.adminpanel.model.creditnote.CreditNoteHeaderPrint;
import com.ats.adminpanel.model.creditnote.CreditNoteHeaderPrintList;
import com.ats.adminpanel.model.creditnote.CreditPrintBean;
import com.ats.adminpanel.model.creditnote.CrnSrNoDateBean;
import com.ats.adminpanel.model.creditnote.GetCreditNoteHeaders;
import com.ats.adminpanel.model.creditnote.GetCreditNoteHeadersList;
import com.ats.adminpanel.model.creditnote.GetCrnDetails;
import com.ats.adminpanel.model.creditnote.GetCrnDetailsList;
import com.ats.adminpanel.model.creditnote.GetGrnGvnForCreditNote;
import com.ats.adminpanel.model.creditnote.GetGrnGvnForCreditNoteList;
import com.ats.adminpanel.model.creditnote.PostCreditNoteDetails;
import com.ats.adminpanel.model.creditnote.PostCreditNoteHeader;
import com.ats.adminpanel.model.creditnote.PostCreditNoteHeaderList;
import com.ats.adminpanel.model.login.UserResponse;

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

						creditHeader.setCrnGrandTotal(grandTotal);

						creditHeader.setCrnFinalAmt(roundUp(grandTotal));

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
					postCreditHeader.setCrnFinalAmt(creditNote.getAprGrandTotal());
					postCreditHeader.setCrnGrandTotal(creditNote.getAprGrandTotal());
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

	@RequestMapping(value = "/showCreditNotes", method = RequestMethod.POST)
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

		RestTemplate restTemplate = new RestTemplate();

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		map.add("crnId", crnId);
		crnDetailResponse = restTemplate.postForObject(Constants.url + "getCrnDetails", map, GetCrnDetailsList.class);

		crnDetailList = crnDetailResponse.getCrnDetails();

		System.out.println("crn Detail List******** " + crnDetailList);

		model.addObject("crnDetailList", crnDetailList);
		model.addObject("crnId", crnId);

		return model;
	}

	@RequestMapping(value = "/getCrnCheckedHeaders/{checked}", method = RequestMethod.GET)
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

				// System.err.println("I = " + i);

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

						if (!srNoList.contains(crnDetailList.get(j).getGrngvnSrno())) {
							srNoList.add(crnDetailList.get(j).getGrngvnSrno());
						}
						
						if(!srNoDateList.contains(crnDetailList.get(j).getGrngvnSrno())) {
							
							CrnSrNoDateBean bean=new CrnSrNoDateBean();
							bean.setGrnGvnDate(crnDetailList.get(j).getGrnGvnDate());
							bean.setSrNo(crnDetailList.get(j).getGrngvnSrno());
							
							//srNoDateList.get(j).setGrnGvnDate(crnDetailList.get(j).getGrnGvnDate());
							//srNoDateList.get(j).setSrNo(crnDetailList.get(j).getGrngvnSrno());
							srNoDateList.add(bean);

						}

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
