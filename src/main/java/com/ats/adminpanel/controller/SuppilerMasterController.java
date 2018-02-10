package com.ats.adminpanel.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.model.DumpOrderList;
import com.ats.adminpanel.model.RawMaterial.Info;
import com.ats.adminpanel.model.modules.ErrorMessage;
import com.ats.adminpanel.model.supplierMaster.SupPaymentTerms;
import com.ats.adminpanel.model.supplierMaster.SupPaymentTermsList;
import com.ats.adminpanel.model.supplierMaster.SupplierDetails;
import com.ats.adminpanel.model.supplierMaster.Transporter;
import com.ats.adminpanel.model.supplierMaster.TransporterList;

@Controller
@Scope("session")
public class SuppilerMasterController {

	public  List<SupplierDetails> supplierDetailsList;

	@RequestMapping(value = "/showAddSupplier", method = RequestMethod.GET)
	public ModelAndView showAddSupplier(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/addSupplier");
		Constants.mainAct =13;
		Constants.subAct =77;
		return model;
	}

	@RequestMapping(value = "/addSupplier", method = RequestMethod.POST)
	public String addSupplier(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView();
		String strReturn = "redirect:/showSupplierList";

		try {
		System.out.println("In method");
		String suppId = request.getParameter("supp_id");
		System.out.println(suppId);
		String suppName = request.getParameter("supp_name");
		String suppAddr = request.getParameter("supp_addr");
		String suppCity = request.getParameter("supp_city");
		String suppState = request.getParameter("supp_state");
		String suppCountry = request.getParameter("supp_country");

		String suppMob1 = request.getParameter("supp_mob1");
		String suppMob2 = request.getParameter("supp_mob2");
		String suppMob3 = request.getParameter("supp_mob3");
		String suppPhone1 = request.getParameter("supp_phone1");
		String suppPhone2 = request.getParameter("supp_phone2");
		String suppEmail1 = request.getParameter("supp_email1");
		String suppEmail2 = request.getParameter("supp_email2");
		String suppEmail3 = request.getParameter("supp_email3");
		String suppEmail4 = request.getParameter("supp_email4");
		String suppEmail5 = request.getParameter("supp_email5");

		String suppGstin = request.getParameter("supp_gstin");
		String suppCPerson = request.getParameter("supp_c_person");
		String suppFdaLic = request.getParameter("supp_fdalic");

		String suppPayId = request.getParameter("supp_pay_id");
		String suppCreditDays = request.getParameter("supp_credit_days");
		String suppPanNo = request.getParameter("supp_panno");

		SupplierDetails supplierDetails = new SupplierDetails();

		if (suppId != null) {
			int supp_Id = Integer.parseInt(suppId);
			supplierDetails.setSuppId(supp_Id);
			strReturn = new String("redirect:/showSupplierList");
			// model = new ModelAndView("masters/supplierDetails");
			model.addObject("supplierList", supplierDetailsList);
		}

		
		supplierDetails.setSuppAddr(suppAddr);
		supplierDetails.setSuppCity(suppCity);
		supplierDetails.setSuppState(suppState);
		supplierDetails.setSuppCountry(suppCountry);
		supplierDetails.setSuppCPerson(suppCPerson);
		supplierDetails.setSuppCreditDays(Integer.parseInt(suppCreditDays));
		supplierDetails.setSuppEmail1(suppEmail1);
		supplierDetails.setSuppEmail2(suppEmail2);
		supplierDetails.setSuppEmail3(suppEmail3);
		supplierDetails.setSuppEmail4(suppEmail4);
		supplierDetails.setSuppEmail5(suppEmail5);
		supplierDetails.setSuppFdaLic(suppFdaLic);
		supplierDetails.setSuppPhone2(suppPhone2);
		supplierDetails.setSuppPhone1(suppPhone1);
		supplierDetails.setSuppGstin(suppGstin);
		supplierDetails.setSuppMob1(suppMob1);
		supplierDetails.setSuppMob2(suppMob2);
		supplierDetails.setSuppMob3(suppMob3);

		supplierDetails.setSuppPayId(Integer.parseInt(suppPayId));
		supplierDetails.setSuppPanNo(suppPanNo);
		supplierDetails.setSuppName(suppName);
		supplierDetails.setDelStatus(0);
		supplierDetails.setIsTallySync(0);
		System.out.println("Data  : " + supplierDetails);
		RestTemplate rest = new RestTemplate();
		Info info = rest.postForObject(Constants.url + "addNewSupplier", supplierDetails, Info.class);

		System.out.println("Response : " + info.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return strReturn;
	}

	@RequestMapping(value = "/showSupplierDetails/{suppId}", method = RequestMethod.GET)
	public ModelAndView showSupplierDetails(@PathVariable("suppId")int suppId,HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/supplierDetails");

		RestTemplate rest = new RestTemplate();
		supplierDetailsList = rest.getForObject(Constants.url + "getAllSupplier", List.class);
		model.addObject("suppId", suppId);

		model.addObject("supplierList", supplierDetailsList);
		return model;
	}
	@RequestMapping(value = "/showSupplierList", method = RequestMethod.GET)
	public ModelAndView showSupplierList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/supplierList");
		Constants.mainAct =13;
		Constants.subAct =78;
		RestTemplate rest = new RestTemplate();
		supplierDetailsList = rest.getForObject(Constants.url + "getAllSupplier", List.class);

		model.addObject("supplierList", supplierDetailsList);
		return model;
	}
	@RequestMapping(value = "/getSupplierDetails", method = RequestMethod.GET)
	public @ResponseBody SupplierDetails getSupplierDetails(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("In method");
		String supp_id = request.getParameter("selectedSupplier");
		int suppId = Integer.parseInt(supp_id);

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("suppId", suppId);
		RestTemplate rest = new RestTemplate();
		SupplierDetails supplierDetails = rest.postForObject(Constants.url + "getSupplierDetails", map,
				SupplierDetails.class);

		System.out.println("Supplier data  : " + supplierDetails);
		return supplierDetails;
	}

	@RequestMapping(value = "/deleteSupplier/{suppId}", method = RequestMethod.GET)
	public String deleteSupplier(@PathVariable("suppId")int suppId,HttpServletRequest request, HttpServletResponse response) {

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("suppId", suppId);
		RestTemplate rest = new RestTemplate();
		try {
		
		Info info = rest.postForObject(Constants.url + "deleteSupplier", map, Info.class);

		System.out.println("response : " + info.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "redirect:/showSupplierList";

		}
		return "redirect:/showSupplierList";
	}

	// ------------------------------Show Add Transporter
		// Jsp------------------------------------
		@RequestMapping(value = "/showAddTransporter", method = RequestMethod.GET)
		public ModelAndView showAddTransporter(HttpServletRequest request, HttpServletResponse response) {

			ModelAndView model = new ModelAndView("masters/addTransporter");
			Constants.mainAct =13;
			Constants.subAct =81;

			return model;
		}

		// ----------------------------------------END-------------------------------------------------------------
		// ------------------------------Show Add Payment Terms
		// Jsp------------------------------------
		@RequestMapping(value = "/showAddPaymentTerms", method = RequestMethod.GET)
		public ModelAndView showAddPaymentTerms(HttpServletRequest request, HttpServletResponse response) {

			ModelAndView model = new ModelAndView("masters/addPaymentTerms");
			Constants.mainAct =13;
			Constants.subAct =83;

			return model;
		}
		// ----------------------------------------END-------------------------------------------------------------
		// ------------------------------ADD Transporter
		// Process------------------------------------

		@RequestMapping(value = "/addTransporterProcess", method = RequestMethod.POST)
		public String addTransporterProcess(HttpServletRequest request, HttpServletResponse response) {

			ModelAndView model = new ModelAndView("masters/transporterList");
			try {

				String tranName = request.getParameter("transporter_name");

				String tranMobile = request.getParameter("transporter_mob");

				String tranEmail = request.getParameter("tran_email_id");

				Transporter transporter = new Transporter();

				transporter.setTranEmail(tranEmail);
				transporter.setTranId(0);
				transporter.setTranMobile(tranMobile);
				transporter.setTranName(tranName);
				transporter.setDelStatus(0);

				RestTemplate restTemplate = new RestTemplate();

				ErrorMessage errorMessage = restTemplate.postForObject(Constants.url + "/saveTransporter", transporter,
						ErrorMessage.class);
				System.out.println("Transporter Adding Response: " + errorMessage.toString());
			} catch (Exception e) {
				System.out.println("Exception In Add Transporter Process:" + e.getMessage());

				return "redirect:/showTransporterList";

			}

			return "redirect:/showTransporterList";
		}
		// ----------------------------------------END-------------------------------------------------------------
		// ------------------------------Update Transporter
		// Process------------------------------------

		@RequestMapping(value = "/updateTransporterProcess", method = RequestMethod.POST)
		public String updateTransporterProcess(HttpServletRequest request, HttpServletResponse response) {

			ModelAndView model = new ModelAndView("masters/transporterList");

			try {
				int tranId = Integer.parseInt(request.getParameter("tran_id"));
				System.out.println("Transaction Id:" + tranId);

				String tranName = request.getParameter("transporter_name");
				System.out.println("tranName" + tranName);

				String tranMobile = request.getParameter("transporter_mob");
				System.out.println("tranMobile" + tranMobile);

				String tranEmail = request.getParameter("tran_email_id");
				System.out.println("tranEmail" + tranEmail);

				Transporter transporter = new Transporter();

				transporter.setTranEmail(tranEmail);
				transporter.setTranId(tranId);
				transporter.setTranMobile(tranMobile);
				transporter.setTranName(tranName);
				transporter.setDelStatus(0);
				System.out.println("Transporter :" + transporter.toString());

				RestTemplate restTemplate = new RestTemplate();

				ErrorMessage errorMessage = restTemplate.postForObject(Constants.url + "/saveTransporter", transporter,
						ErrorMessage.class);
				System.out.println("Transporter Update Response: " + errorMessage.toString());
			} catch (Exception e) {
				System.out.println("Exception In Update Transporter Process:" + e.getMessage());
				return "redirect:/showTransporterList";

			}

			return "redirect:/showTransporterList";
		}
		// ----------------------------------------END-------------------------------------------------------------
		// ------------------------------ADD Payment Term
		// Process------------------------------------

		@RequestMapping(value = "/addPaymentTermsProcess", method = RequestMethod.POST)
		public String addPaymentTermProcess(HttpServletRequest request, HttpServletResponse response) {

			ModelAndView model = new ModelAndView("masters/paymentTermsList");
			try {

				String payDesc = request.getParameter("payment_desc");

				int creditDays = Integer.parseInt(request.getParameter("credit_days"));

				SupPaymentTerms supPaymentTerms = new SupPaymentTerms();

				supPaymentTerms.setPayId(0);
				supPaymentTerms.setPayDesc(payDesc);
				supPaymentTerms.setDelStatus(0);
				supPaymentTerms.setCreditDays(creditDays);

				RestTemplate restTemplate = new RestTemplate();

				ErrorMessage errorMessage = restTemplate.postForObject(Constants.url + "/saveSupPaymentTerms",
						supPaymentTerms, ErrorMessage.class);
				System.out.println("Payment Term Adding Response: " + errorMessage.toString());
			} catch (Exception e) {
				System.out.println("Exception In Add Payment Terms Process:" + e.getMessage());

				return "redirect:/showPaymentTermsList";

			}

			return "redirect:/showPaymentTermsList";
		}
		// ----------------------------------------END-------------------------------------------------------------
		// ------------------------------Update Payment Term
		// Process------------------------------------

		@RequestMapping(value = "/updatePaymentTermsProcess", method = RequestMethod.POST)
		public String updatePaymentTermProcess(HttpServletRequest request, HttpServletResponse response) {

			ModelAndView model = new ModelAndView("masters/editPaymentTerms");
			try {

				String payDesc = request.getParameter("payment_desc");

				int creditDays = Integer.parseInt(request.getParameter("credit_days"));

				int payId = Integer.parseInt(request.getParameter("pay_id"));

				SupPaymentTerms supPaymentTerms = new SupPaymentTerms();

				supPaymentTerms.setPayId(payId);
				supPaymentTerms.setPayDesc(payDesc);
				supPaymentTerms.setDelStatus(0);
				supPaymentTerms.setCreditDays(creditDays);

				RestTemplate restTemplate = new RestTemplate();

				ErrorMessage errorMessage = restTemplate.postForObject(Constants.url + "/saveSupPaymentTerms",
						supPaymentTerms, ErrorMessage.class);
				System.out.println("Payment Term update Response: " + errorMessage.toString());

			} catch (Exception e) {
				System.out.println("Exception In Update Payment Terms Process:" + e.getMessage());

				return "redirect:/showPaymentTermsList";

			}

			return "redirect:/showPaymentTermsList";
		}

		// ----------------------------------------END-------------------------------------------------------------
		// ------------------------------show Transporter List
		// Jsp------------------------------------
		@RequestMapping(value = "/showTransporterList")
		public ModelAndView showTransporterList(HttpServletRequest request, HttpServletResponse response) {
			ModelAndView mav = new ModelAndView("masters/transporterList");
			Constants.mainAct =13;
			Constants.subAct =82;
			try {
				RestTemplate restTemplate = new RestTemplate();

				TransporterList transporterList = restTemplate.getForObject(Constants.url + "/showTransporters",
						TransporterList.class);

				System.out.println("Transporter List Response:" + transporterList.toString());

				mav.addObject("transporterList", transporterList.getTransporterList());

			} catch (Exception e) {
				System.out.println("Exception In Transporter List Show:" + e.getMessage());

			}

			return mav;
		}

		// ----------------------------------------END-------------------------------------------------------------
		// ------------------------------show Payment Terms List
		// Jsp------------------------------------
		@RequestMapping(value = "/showPaymentTermsList")
		public ModelAndView showPaymentTermsList(HttpServletRequest request, HttpServletResponse response) {
			ModelAndView mav = new ModelAndView("masters/paymentTermsList");
			Constants.mainAct =13;
			Constants.subAct =84;
			try {
				RestTemplate restTemplate = new RestTemplate();

				SupPaymentTermsList supPaymentTerms = restTemplate.getForObject(Constants.url + "/showPaymentTerms",
						SupPaymentTermsList.class);

				System.out.println("Payment Term List Response:" + supPaymentTerms.toString());

				mav.addObject("paymentTermsList", supPaymentTerms.getSupPaymentTermsList());

			} catch (Exception e) {
				System.out.println("Exception In Payment Terms List Show:" + e.getMessage());

			}

			return mav;
		}

		// ----------------------------------------END-------------------------------------------------------------
		// ------------------------------Update Transporter JSP------------------------------------

		@RequestMapping(value = "/updateTransporter/{tranId}", method = RequestMethod.GET)
		public ModelAndView updateTransporter(@PathVariable("tranId") int tranId) {

			ModelAndView model = new ModelAndView("masters/editTransporter");
			try {
				RestTemplate restTemplate = new RestTemplate();

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("tranId", tranId);

				Transporter transporter = restTemplate.postForObject(Constants.url + "/getTransporter", map,
						Transporter.class);

				System.out.println("Transporter Response: " + transporter.toString());

				model.addObject("transporter", transporter);

			} catch (Exception e) {
				System.out.println("Exception In Update Transporter JspPage Show:" + e.getMessage());

			}

			return model;
		}
		// ----------------------------------------END-------------------------------------------------------------
		// ------------------------------Update Payment Term JSP------------------------------------

		@RequestMapping(value = "/updatePaymentTerm/{payId}", method = RequestMethod.GET)
		public ModelAndView updatePaymentTerm(@PathVariable("payId") int payId) {

			ModelAndView model = new ModelAndView("masters/editPaymentTerms");
			try {
				RestTemplate restTemplate = new RestTemplate();

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("payId", payId);

				SupPaymentTerms supPaymentTerms = restTemplate.postForObject("" + Constants.url + "/getSupPaymentTerm", map,
						SupPaymentTerms.class);

				System.out.println("supPaymentTerms Response: " + supPaymentTerms.toString());

				model.addObject("paymentTerms", supPaymentTerms);

			} catch (Exception e) {
				System.out.println("Exception in Edit Payment Terms Jsp Show!");
			}

			return model;
		}

		// ----------------------------------------END-------------------------------------------------------------
		// ------------------------------Delete
		// Transporter------------------------------------
		@RequestMapping(value = "/deleteTransporter/{id}", method = RequestMethod.GET)
		public String deleteTransporter(@PathVariable int id) {

			ModelAndView mav = new ModelAndView("masters/transporterList");
			try {

				RestTemplate rest = new RestTemplate();
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("tranId", id);

				ErrorMessage errorResponse = rest.postForObject("" + Constants.url + "/deleteTransporter", map,
						ErrorMessage.class);
				System.out.println(errorResponse.toString());

				if (errorResponse.getError()) {
					return "redirect:/showTransporterList";

				} else {
					return "redirect:/showTransporterList";

				}
			} catch (Exception e) {
				System.out.println("Exception In delete Transporter:" + e.getMessage());

				return "redirect:/showItemCatList";

			}
		}

		// ----------------------------------------END-------------------------------------------------------------
		// ------------------------------Delete Payment Term
		// ------------------------------------
		@RequestMapping(value = "/deletePaymentTerm/{id}", method = RequestMethod.GET)
		public String deletePaymentTerm(@PathVariable int id) {

			ModelAndView mav = new ModelAndView("masters/paymentTermsList");
			try {

				RestTemplate rest = new RestTemplate();
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("payId", id);

				ErrorMessage errorResponse = rest.postForObject("" + Constants.url + "/deleteSupPaymentTerm", map,
						ErrorMessage.class);
				System.out.println(errorResponse.toString());

				if (errorResponse.getError()) {
					return "redirect:/showPaymentTermsList";

				} else {
					return "redirect:/showPaymentTermsList";

				}
			} catch (Exception e) {
				System.out.println("Exception In delete Payment Terms:" + e.getMessage());

				return "redirect:/showItemCatList";

			}
		}

		// ----------------------------------------END-------------------------------------------------------------

}
