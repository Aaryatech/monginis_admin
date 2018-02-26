package com.ats.adminpanel.controller;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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

	//	ModelAndView model = new ModelAndView("creditNote/generateCreditNote");
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

	@RequestMapping(value = "/insertCreNoteProcess", method = RequestMethod.GET)
	public ModelAndView showInsertCreditNote(HttpServletRequest request, HttpServletResponse response) {

		//Constants.mainAct = 11;
		//Constants.subAct = 72;

		ModelAndView model = new ModelAndView("creditNote/generateCreditNote");

		try {

			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			if (isGrn == 1) {

				map.add("isGrn", 1);

				// get /grnGvnDetailForCreditNote for GRN

				getGrnGvnForCreditNoteList = restTemplate.postForObject(Constants.url + "grnGvnDetailForCreditNote",map,
						GetGrnGvnForCreditNoteList.class);

			} else {

				// get /grnGvnDetailForCreditNote for GVN
				map.add("isGrn", 0);

				// get /grnGvnDetailForCreditNote for GRN

				getGrnGvnForCreditNoteList = restTemplate.postForObject(Constants.url + "grnGvnDetailForCreditNote", map,
						GetGrnGvnForCreditNoteList.class);

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
						float cgstRs = (creditNote.getCgstPer() * creditNote.getTaxableAmt()) / 100;
						creditNoteDetail.setCgstRs(roundUp(cgstRs));

						creditNoteDetail.setSgstPer(creditNote.getSgstPer());
						float sgstRs = (creditNote.getSgstPer() * creditNote.getTaxableAmt()) / 100;
						creditNoteDetail.setSgstRs(roundUp(sgstRs));

						creditNoteDetail.setIgstPer(creditNote.getIgstPer());
						float igstRs = (creditNote.getIgstPer() * creditNote.getTaxableAmt()) / 100;
						creditNoteDetail.setIgstRs(roundUp(igstRs));

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

						creditHeader.setCrnTaxableAmt(creditHeader.getCrnTaxableAmt() + creditNote.getTaxableAmt());

						creditHeader.setCrnTotalTax(creditHeader.getCrnTotalTax() + creditNote.getTotalTax());

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

					// System.out.println("grnGvnDate= "+grnGvnDate);

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
					float cgstRs = (creditNote.getCgstPer() * creditNote.getTaxableAmt()) / 100;
					creditNoteDetail.setCgstRs(cgstRs);
					creditNoteDetail.setSgstPer(creditNote.getSgstPer());
					float sgstRs = (creditNote.getSgstPer() * creditNote.getTaxableAmt()) / 100;
					creditNoteDetail.setSgstRs(sgstRs);
					creditNoteDetail.setIgstPer(creditNote.getIgstPer());
					float igstRs = (creditNote.getIgstPer() * creditNote.getTaxableAmt()) / 100;
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

			System.out.println("header List srno at 0 " + creditHeaderList.get(0).getGrnGvnSrNoList());

			System.out.println("header List srno at 1 " + creditHeaderList.get(1).getGrnGvnSrNoList());

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

}
