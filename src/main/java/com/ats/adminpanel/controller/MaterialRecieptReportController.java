package com.ats.adminpanel.controller;

import java.awt.Dimension;
import java.awt.Insets;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream; 
import java.util.ArrayList;
import java.util.List;
 
import javax.servlet.ServletContext;
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
import org.zefer.pd4ml.PD4Constants;
import org.zefer.pd4ml.PD4ML;
import org.zefer.pd4ml.PD4PageMark;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.commons.DateConvertor;
import com.ats.adminpanel.model.ExportToExcel;
import com.ats.adminpanel.model.RawMaterial.GetRawMaterialDetailList; 
import com.ats.adminpanel.model.materialreceipt.Supplist;
import com.ats.adminpanel.model.materialrecreport.GetMaterialRecieptReportBillWise;
import com.ats.adminpanel.model.materialrecreport.GetMaterialRecieptReportHsnCodeWise;
import com.ats.adminpanel.model.materialrecreport.GetMaterialRecieptReportItemWise;
import com.ats.adminpanel.model.materialrecreport.GetMaterialRecieptReportMonthWise;

@Controller
@Scope("session")
public class MaterialRecieptReportController {

	Supplist supplierDetailsList = new Supplist();


	@RequestMapping(value = "/purchaseReport", method = RequestMethod.GET)
	public ModelAndView purchaseReport(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/materialRecieptReport/report");
		RestTemplate rest = new RestTemplate();
		try {
			supplierDetailsList = rest.getForObject(Constants.url + "/getAllSupplierlist", Supplist.class);
			model.addObject("supplierList", supplierDetailsList.getSupplierDetailslist());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/materialRecieptBillWiseReport", method = RequestMethod.GET)
	public @ResponseBody List<GetMaterialRecieptReportBillWise> materialRecieptBillWiseReport(
			HttpServletRequest request, HttpServletResponse response) {

		List<GetMaterialRecieptReportBillWise> materialRecieptBillWiseReport = new ArrayList<GetMaterialRecieptReportBillWise>();

		RestTemplate rest = new RestTemplate();
		try {
			String fromDate = request.getParameter("from_date");
			String todate = request.getParameter("to_date");
			String[] supplier = request.getParameterValues("suppliers[]");
			System.out.println("fromDate" + fromDate);
			System.out.println("todate" + todate);
			System.out.println("supplier" + supplier.toString());

			StringBuilder sb = new StringBuilder();
			String suppliers = null;

			if (supplier[0].equals("-1")) {
				System.out.println("in if");
				for (int i = 0; i < supplierDetailsList.getSupplierDetailslist().size(); i++) {
					sb = sb.append(supplierDetailsList.getSupplierDetailslist().get(i).getSuppId() + ",");

				}

				suppliers = sb.toString();
				suppliers = suppliers.substring(0, suppliers.length() - 1);
				System.out.println("suppliers id list is" + suppliers.toString());

			} else {
				for (int i = 0; i < supplier.length; i++) {
					sb = sb.append(supplier[i] + ",");

				}

				suppliers = sb.toString();
				suppliers = suppliers.substring(0, suppliers.length() - 1);
				System.out.println("suppliers id list is" + suppliers.toString());
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			map.add("toDate", DateConvertor.convertToYMD(todate));
			map.add("suppId", suppliers);
		 
			
			ParameterizedTypeReference<List<GetMaterialRecieptReportBillWise>> typeRef = new ParameterizedTypeReference<List<GetMaterialRecieptReportBillWise>>() {
			};
			ResponseEntity<List<GetMaterialRecieptReportBillWise>> responseEntity = rest.exchange(Constants.url + "materialRecieptBillWiseReport",
					HttpMethod.POST, new HttpEntity<>(map), typeRef);
			
			materialRecieptBillWiseReport = responseEntity.getBody();
			
			 
			System.out.println("materialRecieptBillWiseReport" + materialRecieptBillWiseReport.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//export to excel
	 
		
List<ExportToExcel> exportToExcelList=new ArrayList<ExportToExcel>();
		
		ExportToExcel expoExcel=new ExportToExcel();
		List<String> rowData=new ArrayList<String>();
		 
		rowData.add("Mrn Id");
		rowData.add("Mrn No");
		rowData.add("Invoice Book Date");
		rowData.add("Invoice No");
		rowData.add("Invoice Date");
		 
		rowData.add("Supplier Id");
		rowData.add("Supplier Name");
		rowData.add("Supplier City");
		rowData.add("Supplier GST No");
		rowData.add("Basic Value");
		 
		rowData.add("Discount Amt");
		rowData.add("Freight Amt");
		rowData.add("Insurance Amt");
		rowData.add("Other");
		rowData.add("cgst");
		 
		rowData.add("sgst");
		rowData.add("igst");
		rowData.add("cess");
		rowData.add("Bill Amt");
	 
			
		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);
		for(int i=0;i<materialRecieptBillWiseReport.size();i++)
		{
			  expoExcel=new ExportToExcel();
			 rowData=new ArrayList<String>();
			 
		 
			 rowData.add(""+materialRecieptBillWiseReport.get(i).getMrnId());
			 rowData.add(""+materialRecieptBillWiseReport.get(i).getMrnNo());
			rowData.add(""+materialRecieptBillWiseReport.get(i).getInvBookDate());
			rowData.add(""+materialRecieptBillWiseReport.get(i).getInvoiceNumber());
		 
			rowData.add(""+materialRecieptBillWiseReport.get(i).getInvDate());
			rowData.add(""+materialRecieptBillWiseReport.get(i).getSupplierId());
			rowData.add(""+materialRecieptBillWiseReport.get(i).getSuppName());
			rowData.add(""+materialRecieptBillWiseReport.get(i).getSuppCity());
			

			
			rowData.add(""+materialRecieptBillWiseReport.get(i).getSuppGstin());
			rowData.add(""+materialRecieptBillWiseReport.get(i).getBasicValue());
			rowData.add(""+materialRecieptBillWiseReport.get(i).getDiscAmt());
			rowData.add(""+materialRecieptBillWiseReport.get(i).getFreightAmt());
		 

			rowData.add(""+materialRecieptBillWiseReport.get(i).getInsuranceAmt());
			rowData.add(""+materialRecieptBillWiseReport.get(i).getOther());
			rowData.add(""+materialRecieptBillWiseReport.get(i).getCgst());
			rowData.add(""+materialRecieptBillWiseReport.get(i).getSgst());

			rowData.add(""+materialRecieptBillWiseReport.get(i).getIgst());
			rowData.add(""+materialRecieptBillWiseReport.get(i).getCess());
			rowData.add(""+materialRecieptBillWiseReport.get(i).getBillAmount());
			
			
			
			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);
			 
		}
		 
		
		
		HttpSession session = request.getSession();
		session.setAttribute("exportExcelList", exportToExcelList);
		session.setAttribute("excelName", "BillWisePurchase");
		
		return materialRecieptBillWiseReport;

	}

	@RequestMapping(value = "pdf/billWisePdf/{from_date}/{to_date}/{supplier}", method = RequestMethod.GET)
	public ModelAndView billWisePdf(@PathVariable String from_date,@PathVariable String to_date,@PathVariable String supplier[],HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/materialRecieptReport/pdf/billwise");
		try {
			List<GetMaterialRecieptReportBillWise> materialRecieptBillWiseReport = new ArrayList<GetMaterialRecieptReportBillWise>();

			RestTemplate rest = new RestTemplate();
			
			StringBuilder sb = new StringBuilder();
			String suppliers = null;

			supplierDetailsList = rest.getForObject(Constants.url + "/getAllSupplierlist", Supplist.class);
			if (supplier[0].equals("-1")) {
				System.out.println("in if");
				for (int i = 0; i < supplierDetailsList.getSupplierDetailslist().size(); i++) {
					sb = sb.append(supplierDetailsList.getSupplierDetailslist().get(i).getSuppId() + ",");

				}

				suppliers = sb.toString();
				suppliers = suppliers.substring(0, suppliers.length() - 1);
				System.out.println("suppliers id list is" + suppliers.toString());

			} else {
				for (int i = 0; i < supplier.length; i++) {
					sb = sb.append(supplier[i] + ",");

				}

				suppliers = sb.toString();
				suppliers = suppliers.substring(0, suppliers.length() - 1);
				System.out.println("suppliers id list is" + suppliers.toString());
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("fromDate", DateConvertor.convertToYMD(from_date));
			map.add("toDate", DateConvertor.convertToYMD(to_date));
			map.add("suppId", suppliers);
		 
			
			ParameterizedTypeReference<List<GetMaterialRecieptReportBillWise>> typeRef = new ParameterizedTypeReference<List<GetMaterialRecieptReportBillWise>>() {
			};
			ResponseEntity<List<GetMaterialRecieptReportBillWise>> responseEntity = rest.exchange(Constants.url + "materialRecieptBillWiseReport",
					HttpMethod.POST, new HttpEntity<>(map), typeRef);
			
			materialRecieptBillWiseReport = responseEntity.getBody();
			model.addObject("staticlist", materialRecieptBillWiseReport);
			model.addObject("fromDate", from_date);
			model.addObject("toDate", to_date);
			System.out.println("billWisePdf" + materialRecieptBillWiseReport);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;

	}

	 

	@RequestMapping(value = "/materialRecieptsSupplierWise", method = RequestMethod.GET)
	public ModelAndView materialRecieptsSupplierWise(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/materialRecieptReport/supplierWise");
		RestTemplate rest = new RestTemplate();
		try {
			supplierDetailsList = rest.getForObject(Constants.url + "/getAllSupplierlist", Supplist.class);
			model.addObject("supplierList", supplierDetailsList.getSupplierDetailslist());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/materialRecieptsSupplierWiseReport", method = RequestMethod.GET)
	public @ResponseBody List<GetMaterialRecieptReportBillWise> materialRecieptsSupplierWiseReport(
			HttpServletRequest request, HttpServletResponse response) {
		List<GetMaterialRecieptReportBillWise> materialRecieptsSupplierWiseReport = new ArrayList<GetMaterialRecieptReportBillWise>();

		RestTemplate rest = new RestTemplate();
		try {
			String fromDate = request.getParameter("from_date");
			String todate = request.getParameter("to_date");
			String[] supplier = request.getParameterValues("suppliers[]");
			System.out.println("fromDate" + fromDate);
			System.out.println("todate" + todate);
			System.out.println("supplier" + supplier.toString());

			StringBuilder sb = new StringBuilder();
			String suppliers = null;

			if (supplier[0].equals("-1")) {
				System.out.println("in if");
				for (int i = 0; i < supplierDetailsList.getSupplierDetailslist().size(); i++) {
					sb = sb.append(supplierDetailsList.getSupplierDetailslist().get(i).getSuppId() + ",");

				}
				suppliers = sb.toString();
				suppliers = suppliers.substring(0, suppliers.length() - 1);
				System.out.println("suppliers id list is" + suppliers.toString());

			} else {
				for (int i = 0; i < supplier.length; i++) {
					sb = sb.append(supplier[i] + ",");

				}

				suppliers = sb.toString();
				suppliers = suppliers.substring(0, suppliers.length() - 1);
				System.out.println("suppliers id list is" + suppliers.toString());
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			map.add("toDate", DateConvertor.convertToYMD(todate));
			map.add("suppId", suppliers);
			 
			
			
			ParameterizedTypeReference<List<GetMaterialRecieptReportBillWise>> typeRef = new ParameterizedTypeReference<List<GetMaterialRecieptReportBillWise>>() {
			};
			ResponseEntity<List<GetMaterialRecieptReportBillWise>> responseEntity = rest.exchange(Constants.url + "materialRecieptSupplierWiseReport",
					HttpMethod.POST, new HttpEntity<>(map), typeRef);
			
			materialRecieptsSupplierWiseReport = responseEntity.getBody();
			
			System.out.println("materialRecieptBillWiseReport" + materialRecieptsSupplierWiseReport.toString());
			 

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//export to excel
		 
		
		List<ExportToExcel> exportToExcelList=new ArrayList<ExportToExcel>();
				
				ExportToExcel expoExcel=new ExportToExcel();
				List<String> rowData=new ArrayList<String>();
				 
				rowData.add("Mrn Id");
				rowData.add("Mrn No");
				rowData.add("Invoice Book Date");
				rowData.add("Invoice No");
				rowData.add("Invoice Date");
				 
				rowData.add("Supplier Id");
				rowData.add("Supplier Name");
				rowData.add("Supplier City");
				rowData.add("Supplier GST No");
				rowData.add("Basic Value");
				 
				rowData.add("Discount Amt");
				rowData.add("Freight Amt");
				rowData.add("Insurance Amt");
				rowData.add("Other");
				rowData.add("cgst");
				 
				rowData.add("sgst");
				rowData.add("igst");
				rowData.add("cess");
				rowData.add("Bill Amt");
				
				
				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);
				for(int i=0;i<materialRecieptsSupplierWiseReport.size();i++)
				{
					  expoExcel=new ExportToExcel();
					 rowData=new ArrayList<String>();
					 
				 
					 rowData.add(""+materialRecieptsSupplierWiseReport.get(i).getMrnId());
					 rowData.add(""+materialRecieptsSupplierWiseReport.get(i).getMrnNo());
					rowData.add(""+materialRecieptsSupplierWiseReport.get(i).getInvBookDate());
					rowData.add(""+materialRecieptsSupplierWiseReport.get(i).getInvoiceNumber());
				 
					rowData.add(""+materialRecieptsSupplierWiseReport.get(i).getInvDate());
					rowData.add(""+materialRecieptsSupplierWiseReport.get(i).getSupplierId());
					rowData.add(""+materialRecieptsSupplierWiseReport.get(i).getSuppName());
					rowData.add(""+materialRecieptsSupplierWiseReport.get(i).getSuppCity());
					

					
					rowData.add(""+materialRecieptsSupplierWiseReport.get(i).getSuppGstin());
					rowData.add(""+materialRecieptsSupplierWiseReport.get(i).getBasicValue());
					rowData.add(""+materialRecieptsSupplierWiseReport.get(i).getDiscAmt());
					rowData.add(""+materialRecieptsSupplierWiseReport.get(i).getFreightAmt());
				 

					rowData.add(""+materialRecieptsSupplierWiseReport.get(i).getInsuranceAmt());
					rowData.add(""+materialRecieptsSupplierWiseReport.get(i).getOther());
					rowData.add(""+materialRecieptsSupplierWiseReport.get(i).getCgst());
					rowData.add(""+materialRecieptsSupplierWiseReport.get(i).getSgst());

					rowData.add(""+materialRecieptsSupplierWiseReport.get(i).getIgst());
					rowData.add(""+materialRecieptsSupplierWiseReport.get(i).getCess());
					rowData.add(""+materialRecieptsSupplierWiseReport.get(i).getBillAmount());
					
					
					
					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);
					 
				}
				 
				
				
				HttpSession session = request.getSession();
				session.setAttribute("exportExcelList", exportToExcelList);
				session.setAttribute("excelName", "SupplierWisePurchase");
				
		return materialRecieptsSupplierWiseReport;

	}

	@RequestMapping(value = "pdf/supplierWisePdf/{from_date}/{to_date}/{supplier}", method = RequestMethod.GET)
	public ModelAndView supplierWisePdf(@PathVariable String from_date,@PathVariable String to_date,@PathVariable String supplier[],HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/materialRecieptReport/pdf/supplierwisepdf");
		try {
			List<GetMaterialRecieptReportBillWise> materialRecieptsSupplierWiseReport = new ArrayList<GetMaterialRecieptReportBillWise>();

			RestTemplate rest = new RestTemplate();
			
			StringBuilder sb = new StringBuilder();
			String suppliers = null;

			supplierDetailsList = rest.getForObject(Constants.url + "/getAllSupplierlist", Supplist.class);
			if (supplier[0].equals("-1")) {
				System.out.println("in if");
				for (int i = 0; i < supplierDetailsList.getSupplierDetailslist().size(); i++) {
					sb = sb.append(supplierDetailsList.getSupplierDetailslist().get(i).getSuppId() + ",");

				}

				suppliers = sb.toString();
				suppliers = suppliers.substring(0, suppliers.length() - 1);
				System.out.println("suppliers id list is" + suppliers.toString());

			} else {
				for (int i = 0; i < supplier.length; i++) {
					sb = sb.append(supplier[i] + ",");

				}

				suppliers = sb.toString();
				suppliers = suppliers.substring(0, suppliers.length() - 1);
				System.out.println("suppliers id list is" + suppliers.toString());
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("fromDate", DateConvertor.convertToYMD(from_date));
			map.add("toDate", DateConvertor.convertToYMD(to_date));
			map.add("suppId", suppliers);
		 
			
			ParameterizedTypeReference<List<GetMaterialRecieptReportBillWise>> typeRef = new ParameterizedTypeReference<List<GetMaterialRecieptReportBillWise>>() {
			};
			ResponseEntity<List<GetMaterialRecieptReportBillWise>> responseEntity = rest.exchange(Constants.url + "materialRecieptSupplierWiseReport",
					HttpMethod.POST, new HttpEntity<>(map), typeRef);
			
			materialRecieptsSupplierWiseReport = responseEntity.getBody();
			model.addObject("staticlist", materialRecieptsSupplierWiseReport);
			model.addObject("from_date", from_date);
			model.addObject("to_date", to_date);
			System.out.println("supplierWisePdf" + materialRecieptsSupplierWiseReport);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;

	}

	 

	@RequestMapping(value = "/materialRecieptsDateWise", method = RequestMethod.GET)
	public @ResponseBody ModelAndView materialRecieptsDateWise(HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/materialRecieptReport/dateWise");
		RestTemplate rest = new RestTemplate();
		try {
			supplierDetailsList = rest.getForObject(Constants.url + "/getAllSupplierlist", Supplist.class);
			model.addObject("supplierList", supplierDetailsList.getSupplierDetailslist());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/materialRecieptsDateWiseReport", method = RequestMethod.GET)
	public @ResponseBody List<GetMaterialRecieptReportBillWise> materialRecieptsDateWiseReport(
			HttpServletRequest request, HttpServletResponse response) {
		List<GetMaterialRecieptReportBillWise> materialRecieptsDateWiseReport = new ArrayList<GetMaterialRecieptReportBillWise>();

		RestTemplate rest = new RestTemplate();
		try {
			String fromDate = request.getParameter("from_date");
			String todate = request.getParameter("to_date");
			String[] supplier = request.getParameterValues("suppliers[]");
			System.out.println("fromDate" + fromDate);
			System.out.println("todate" + todate);
			System.out.println("supplier" + supplier.toString());

			StringBuilder sb = new StringBuilder();
			String suppliers = null;

			if (supplier[0].equals("-1")) {
				System.out.println("in if");
				for (int i = 0; i < supplierDetailsList.getSupplierDetailslist().size(); i++) {
					sb = sb.append(supplierDetailsList.getSupplierDetailslist().get(i).getSuppId() + ",");

				}
				suppliers = sb.toString();
				suppliers = suppliers.substring(0, suppliers.length() - 1);
				System.out.println("suppliers id list is" + suppliers.toString());

			} else {
				for (int i = 0; i < supplier.length; i++) {
					sb = sb.append(supplier[i] + ",");

				}

				suppliers = sb.toString();
				suppliers = suppliers.substring(0, suppliers.length() - 1);
				System.out.println("suppliers id list is" + suppliers.toString());
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			map.add("toDate", DateConvertor.convertToYMD(todate));
			map.add("suppId", suppliers);
	 
			
			ParameterizedTypeReference<List<GetMaterialRecieptReportBillWise>> typeRef = new ParameterizedTypeReference<List<GetMaterialRecieptReportBillWise>>() {
			};
			ResponseEntity<List<GetMaterialRecieptReportBillWise>> responseEntity = rest.exchange(Constants.url + "materialRecieptDateWiseReport",
					HttpMethod.POST, new HttpEntity<>(map), typeRef);
			
			materialRecieptsDateWiseReport = responseEntity.getBody();
			
			System.out.println("materialRecieptBillWiseReport" + materialRecieptsDateWiseReport.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	//export to excel
		 
		
		List<ExportToExcel> exportToExcelList=new ArrayList<ExportToExcel>();
				
				ExportToExcel expoExcel=new ExportToExcel();
				List<String> rowData=new ArrayList<String>();
				 
				rowData.add("Mrn Id");
				rowData.add("Mrn No");
				rowData.add("Invoice Book Date");
				rowData.add("Invoice No");
				rowData.add("Invoice Date");
				 
				rowData.add("Supplier Id");
				rowData.add("Supplier Name");
				rowData.add("Supplier City");
				rowData.add("Supplier GST No");
				rowData.add("Basic Value");
				 
				rowData.add("Discount Amt");
				rowData.add("Freight Amt");
				rowData.add("Insurance Amt");
				rowData.add("Other");
				rowData.add("cgst");
				 
				rowData.add("sgst");
				rowData.add("igst");
				rowData.add("cess");
				rowData.add("Bill Amt");
				
				
				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);
				for(int i=0;i<materialRecieptsDateWiseReport.size();i++)
				{
					  expoExcel=new ExportToExcel();
					 rowData=new ArrayList<String>();
					 
				 
					 rowData.add(""+materialRecieptsDateWiseReport.get(i).getMrnId());
					 rowData.add(""+materialRecieptsDateWiseReport.get(i).getMrnNo());
					rowData.add(""+materialRecieptsDateWiseReport.get(i).getInvBookDate());
					rowData.add(""+materialRecieptsDateWiseReport.get(i).getInvoiceNumber());
				 
					rowData.add(""+materialRecieptsDateWiseReport.get(i).getInvDate());
					rowData.add(""+materialRecieptsDateWiseReport.get(i).getSupplierId());
					rowData.add(""+materialRecieptsDateWiseReport.get(i).getSuppName());
					rowData.add(""+materialRecieptsDateWiseReport.get(i).getSuppCity());
					

					
					rowData.add(""+materialRecieptsDateWiseReport.get(i).getSuppGstin());
					rowData.add(""+materialRecieptsDateWiseReport.get(i).getBasicValue());
					rowData.add(""+materialRecieptsDateWiseReport.get(i).getDiscAmt());
					rowData.add(""+materialRecieptsDateWiseReport.get(i).getFreightAmt());
				 

					rowData.add(""+materialRecieptsDateWiseReport.get(i).getInsuranceAmt());
					rowData.add(""+materialRecieptsDateWiseReport.get(i).getOther());
					rowData.add(""+materialRecieptsDateWiseReport.get(i).getCgst());
					rowData.add(""+materialRecieptsDateWiseReport.get(i).getSgst());

					rowData.add(""+materialRecieptsDateWiseReport.get(i).getIgst());
					rowData.add(""+materialRecieptsDateWiseReport.get(i).getCess());
					rowData.add(""+materialRecieptsDateWiseReport.get(i).getBillAmount());
					
					
					
					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);
					 
				}
				 
				
				
				HttpSession session = request.getSession();
				session.setAttribute("exportExcelList", exportToExcelList);
				session.setAttribute("excelName", "DateWisePurchase");
				
		return materialRecieptsDateWiseReport;

	}

	@RequestMapping(value = "pdf/dateWisePdf/{from_date}/{to_date}/{supplier}", method = RequestMethod.GET)
	public ModelAndView dateWisePdf(@PathVariable String from_date,@PathVariable String to_date,@PathVariable String supplier[],HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/materialRecieptReport/pdf/dateWisePdf");
		try {
			
			List<GetMaterialRecieptReportBillWise> materialRecieptsDateWiseReport = new ArrayList<GetMaterialRecieptReportBillWise>();

			RestTemplate rest = new RestTemplate();
			
			StringBuilder sb = new StringBuilder();
			String suppliers = null;

			supplierDetailsList = rest.getForObject(Constants.url + "/getAllSupplierlist", Supplist.class);
			if (supplier[0].equals("-1")) {
				System.out.println("in if");
				for (int i = 0; i < supplierDetailsList.getSupplierDetailslist().size(); i++) {
					sb = sb.append(supplierDetailsList.getSupplierDetailslist().get(i).getSuppId() + ",");

				}

				suppliers = sb.toString();
				suppliers = suppliers.substring(0, suppliers.length() - 1);
				System.out.println("suppliers id list is" + suppliers.toString());

			} else {
				for (int i = 0; i < supplier.length; i++) {
					sb = sb.append(supplier[i] + ",");

				}

				suppliers = sb.toString();
				suppliers = suppliers.substring(0, suppliers.length() - 1);
				System.out.println("suppliers id list is" + suppliers.toString());
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("fromDate", DateConvertor.convertToYMD(from_date));
			map.add("toDate", DateConvertor.convertToYMD(to_date));
			map.add("suppId", suppliers);
		 
			
			ParameterizedTypeReference<List<GetMaterialRecieptReportBillWise>> typeRef = new ParameterizedTypeReference<List<GetMaterialRecieptReportBillWise>>() {
			};
			ResponseEntity<List<GetMaterialRecieptReportBillWise>> responseEntity = rest.exchange(Constants.url + "materialRecieptDateWiseReport",
					HttpMethod.POST, new HttpEntity<>(map), typeRef);
			
			materialRecieptsDateWiseReport = responseEntity.getBody();
			model.addObject("staticlist", materialRecieptsDateWiseReport);
			model.addObject("fromDate", from_date);
			model.addObject("toDate", to_date);
			model.addObject("staticlist", materialRecieptsDateWiseReport);
			System.out.println("dateWisePdf" + materialRecieptsDateWiseReport);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;

	}

	 
	GetRawMaterialDetailList getRawMaterialDetail = new GetRawMaterialDetailList();

	@RequestMapping(value = "/materialRecieptsItemWise", method = RequestMethod.GET)
	public ModelAndView materialRecieptsItemWise(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/materialRecieptReport/itemWise");
		RestTemplate rest = new RestTemplate();
		try {
			getRawMaterialDetail = rest.getForObject(Constants.url + "rawMaterial/getAllRawMaterialList",
					GetRawMaterialDetailList.class);
			model.addObject("rawlist", getRawMaterialDetail.getRawMaterialDetailsList());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/materialRecieptsItemWiseReport", method = RequestMethod.GET)
	public @ResponseBody List<GetMaterialRecieptReportItemWise> materialRecieptsItemWiseReport(
			HttpServletRequest request, HttpServletResponse response) {
		List<GetMaterialRecieptReportItemWise> materialRecieptsItemWiseReport = new ArrayList<GetMaterialRecieptReportItemWise>();

		RestTemplate rest = new RestTemplate();
		try {
			String fromDate = request.getParameter("from_date");
			String todate = request.getParameter("to_date");
			String[] item = request.getParameterValues("item[]");
			System.out.println("fromDate" + fromDate);
			System.out.println("todate" + todate);
			System.out.println("supplier" + item.toString());

			StringBuilder sb = new StringBuilder();
			String items = null;

			if (item[0].equals("-1")) {
				System.out.println("in if");
				for (int i = 0; i < getRawMaterialDetail.getRawMaterialDetailsList().size(); i++) {
					sb = sb.append(getRawMaterialDetail.getRawMaterialDetailsList().get(i).getRmId() + ",");

				}
				items = sb.toString();
				items = items.substring(0, items.length() - 1);
				System.out.println("items id list is" + items.toString());

			} else {
				for (int i = 0; i < item.length; i++) {
					sb = sb.append(item[i] + ",");

				}

				items = sb.toString();
				items = items.substring(0, items.length() - 1);
				System.out.println("items id list is" + items.toString());
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			map.add("toDate", DateConvertor.convertToYMD(todate));
			map.add("item", items);
		 
			ParameterizedTypeReference<List<GetMaterialRecieptReportItemWise>> typeRef = new ParameterizedTypeReference<List<GetMaterialRecieptReportItemWise>>() {
			};
			ResponseEntity<List<GetMaterialRecieptReportItemWise>> responseEntity = rest.exchange(Constants.url + "materialRecieptItemWiseReport",
					HttpMethod.POST, new HttpEntity<>(map), typeRef);
			
			materialRecieptsItemWiseReport = responseEntity.getBody();
			
			
			System.out.println("materialRecieptBillWiseReport" + materialRecieptsItemWiseReport.toString());
		 

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//export to excel
		 
		
				List<ExportToExcel> exportToExcelList=new ArrayList<ExportToExcel>();
						
						ExportToExcel expoExcel=new ExportToExcel();
						List<String> rowData=new ArrayList<String>();
						 
						rowData.add("RM Id");
						rowData.add("Rm Name");
						rowData.add("Hsn No");
						rowData.add("Tax Rate");
						rowData.add("recieved Qty");
						 
					 
						rowData.add("cgst");
						rowData.add("sgst");
						rowData.add("igst");
						rowData.add("Taxable Amt");
					 
						/*
						private String rmId; 
	private String rmName; 
	private int hsncdNo; 
	private float taxRate; 
	private float recdQty; 
	private float cgst; 
	private float sgst; 
	private float igst; 
	private float taxableAmt;
						*/
						expoExcel.setRowData(rowData);
						exportToExcelList.add(expoExcel);
						for(int i=0;i<materialRecieptsItemWiseReport.size();i++)
						{
							  expoExcel=new ExportToExcel();
							 rowData=new ArrayList<String>();
							 
						 
							 rowData.add(""+materialRecieptsItemWiseReport.get(i).getRmId());
							 rowData.add(""+materialRecieptsItemWiseReport.get(i).getRmName());
							rowData.add(""+materialRecieptsItemWiseReport.get(i).getHsncdNo());
							rowData.add(""+materialRecieptsItemWiseReport.get(i).getTaxRate());
						 
							rowData.add(""+materialRecieptsItemWiseReport.get(i).getRecdQty());
							 
							rowData.add(""+materialRecieptsItemWiseReport.get(i).getCgst());
							rowData.add(""+materialRecieptsItemWiseReport.get(i).getSgst());

							rowData.add(""+materialRecieptsItemWiseReport.get(i).getIgst());
							rowData.add(""+materialRecieptsItemWiseReport.get(i).getTaxableAmt());
							 
							
							
							
							expoExcel.setRowData(rowData);
							exportToExcelList.add(expoExcel);
							 
						}
						 
						
						
						HttpSession session = request.getSession();
						session.setAttribute("exportExcelList", exportToExcelList);
						session.setAttribute("excelName", "ItemWisePurchase");
						
		return materialRecieptsItemWiseReport;

	}

	@RequestMapping(value = "pdf/itemWisePdf/{from_date}/{to_date}/{item}", method = RequestMethod.GET)
	public ModelAndView itemWisePdf(@PathVariable String from_date,@PathVariable String to_date,@PathVariable String item[],HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/materialRecieptReport/pdf/itemWisePdf");
		try {

			List<GetMaterialRecieptReportItemWise> materialRecieptsItemWiseReport = new ArrayList<GetMaterialRecieptReportItemWise>();

			RestTemplate rest = new RestTemplate();
			System.out.println("fromDate" + from_date);
			System.out.println("todate" + to_date);
			System.out.println("item" + item.toString());

			StringBuilder sb = new StringBuilder();
			String items = null;

			getRawMaterialDetail = rest.getForObject(Constants.url + "rawMaterial/getAllRawMaterialList",
					GetRawMaterialDetailList.class);
			if (item[0].equals("-1")) {
				System.out.println("in if");
				for (int i = 0; i < getRawMaterialDetail.getRawMaterialDetailsList().size(); i++) {
					sb = sb.append(getRawMaterialDetail.getRawMaterialDetailsList().get(i).getRmId() + ",");

				}
				items = sb.toString();
				items = items.substring(0, items.length() - 1);
				System.out.println("items id list is" + items.toString());

			} else {
				for (int i = 0; i < item.length; i++) {
					sb = sb.append(item[i] + ",");

				}

				items = sb.toString();
				items = items.substring(0, items.length() - 1);
				System.out.println("items id list is" + items.toString());
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("fromDate", DateConvertor.convertToYMD(from_date));
			map.add("toDate", DateConvertor.convertToYMD(to_date));
			map.add("item", items);
		 
			ParameterizedTypeReference<List<GetMaterialRecieptReportItemWise>> typeRef = new ParameterizedTypeReference<List<GetMaterialRecieptReportItemWise>>() {
			};
			ResponseEntity<List<GetMaterialRecieptReportItemWise>> responseEntity = rest.exchange(Constants.url + "materialRecieptItemWiseReport",
					HttpMethod.POST, new HttpEntity<>(map), typeRef);
			
			materialRecieptsItemWiseReport = responseEntity.getBody();
			 
			model.addObject("staticlist", materialRecieptsItemWiseReport);
			model.addObject("fromDate", from_date);
			model.addObject("toDate", to_date);
			System.out.println("ItemWisepdf" + materialRecieptsItemWiseReport);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;

	}

	 

	@RequestMapping(value = "/materialRecieptsHsndCodeWise", method = RequestMethod.GET)
	public ModelAndView materialRecieptsHsndCodeWise(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/materialRecieptReport/hsndCodeWise");
		RestTemplate rest = new RestTemplate();
		try {
			supplierDetailsList = rest.getForObject(Constants.url + "/getAllSupplierlist", Supplist.class);
			model.addObject("supplierList", supplierDetailsList.getSupplierDetailslist());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;

	}

	@RequestMapping(value = "/materialRecieptHsndCodeReport", method = RequestMethod.GET)
	public @ResponseBody List<GetMaterialRecieptReportHsnCodeWise> materialRecieptHsndCodeReport(
			HttpServletRequest request, HttpServletResponse response) {

		List<GetMaterialRecieptReportHsnCodeWise> materialRecieptHsndCodeReport = new ArrayList<GetMaterialRecieptReportHsnCodeWise>();

		RestTemplate rest = new RestTemplate();
		try {
			String fromDate = request.getParameter("from_date");
			String todate = request.getParameter("to_date");
			String[] supplier = request.getParameterValues("suppliers[]");
			System.out.println("fromDate" + fromDate);
			System.out.println("todate" + todate);
			System.out.println("supplier" + supplier.toString());

			StringBuilder sb = new StringBuilder();
			String suppliers = null;

			if (supplier[0].equals("-1")) {
				System.out.println("in if");
				for (int i = 0; i < supplierDetailsList.getSupplierDetailslist().size(); i++) {
					sb = sb.append(supplierDetailsList.getSupplierDetailslist().get(i).getSuppId() + ",");

				}

				suppliers = sb.toString();
				suppliers = suppliers.substring(0, suppliers.length() - 1);
				System.out.println("suppliers id list is" + suppliers.toString());

			} else {
				for (int i = 0; i < supplier.length; i++) {
					sb = sb.append(supplier[i] + ",");

				}

				suppliers = sb.toString();
				suppliers = suppliers.substring(0, suppliers.length() - 1);
				System.out.println("suppliers id list is" + suppliers.toString());
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			map.add("toDate", DateConvertor.convertToYMD(todate));
			map.add("suppId", suppliers);
			 
			
			
			ParameterizedTypeReference<List<GetMaterialRecieptReportHsnCodeWise>> typeRef = new ParameterizedTypeReference<List<GetMaterialRecieptReportHsnCodeWise>>() {
			};
			ResponseEntity<List<GetMaterialRecieptReportHsnCodeWise>> responseEntity = rest.exchange(Constants.url + "materialRecieptHsnCodeWiseReport",
					HttpMethod.POST, new HttpEntity<>(map), typeRef);
			
			materialRecieptHsndCodeReport = responseEntity.getBody();
			
			 
			System.out.println("materialRecieptHsndCodeReport" + materialRecieptHsndCodeReport.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//export to excel
		 
		
		List<ExportToExcel> exportToExcelList=new ArrayList<ExportToExcel>();
				
				ExportToExcel expoExcel=new ExportToExcel();
				List<String> rowData=new ArrayList<String>();
				 
				rowData.add("Mrn Detail Id");
				rowData.add("Mrn No");
				rowData.add("Invoice Book Date");
				rowData.add("Invoice Number");
				rowData.add("Supplier Name");
				 
			 
				rowData.add("Hsn No");
				rowData.add("Value");
				rowData.add("cgst");
				rowData.add("sgst");
				rowData.add("igst");
				rowData.add("Taxable Amt");
			 
			 
				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);
				for(int i=0;i<materialRecieptHsndCodeReport.size();i++)
				{
					  expoExcel=new ExportToExcel();
					 rowData=new ArrayList<String>();
					 
					 rowData.add(""+materialRecieptHsndCodeReport.get(i).getMrnDetailId());
					 rowData.add(""+materialRecieptHsndCodeReport.get(i).getMrnNo());
					 rowData.add(""+materialRecieptHsndCodeReport.get(i).getInvBookDate());
					 rowData.add(""+materialRecieptHsndCodeReport.get(i).getInvoiceNumber());
					rowData.add(""+materialRecieptHsndCodeReport.get(i).getSuppName());
					rowData.add(""+materialRecieptHsndCodeReport.get(i).getHsncdNo());
				 
					rowData.add(""+materialRecieptHsndCodeReport.get(i).getValue());
					rowData.add(""+materialRecieptHsndCodeReport.get(i).getCgst());
					rowData.add(""+materialRecieptHsndCodeReport.get(i).getSgst());
					rowData.add(""+materialRecieptHsndCodeReport.get(i).getIgst());
					rowData.add(""+materialRecieptHsndCodeReport.get(i).getTaxableAmt());
					 
					
					
					
					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);
					 
				}
				 
				
				
				HttpSession session = request.getSession();
				session.setAttribute("exportExcelList", exportToExcelList);
				session.setAttribute("excelName", "HsnCodeWisePurchase");
				
				
		return materialRecieptHsndCodeReport;

	}

	@RequestMapping(value = "pdf/HsnCodeWisePdf/{from_date}/{to_date}/{supplier}", method = RequestMethod.GET)
	public ModelAndView HsnCodeWisePdf(@PathVariable String from_date,@PathVariable String to_date,@PathVariable String supplier[],HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/materialRecieptReport/pdf/HsnCodeWisePdf");
		try {
			
			List<GetMaterialRecieptReportHsnCodeWise> materialRecieptHsndCodeReport = new ArrayList<GetMaterialRecieptReportHsnCodeWise>();

			RestTemplate rest = new RestTemplate();
			
			StringBuilder sb = new StringBuilder();
			String suppliers = null;

			supplierDetailsList = rest.getForObject(Constants.url + "/getAllSupplierlist", Supplist.class);
			if (supplier[0].equals("-1")) {
				System.out.println("in if");
				for (int i = 0; i < supplierDetailsList.getSupplierDetailslist().size(); i++) {
					sb = sb.append(supplierDetailsList.getSupplierDetailslist().get(i).getSuppId() + ",");

				}

				suppliers = sb.toString();
				suppliers = suppliers.substring(0, suppliers.length() - 1);
				System.out.println("suppliers id list is" + suppliers.toString());

			} else {
				for (int i = 0; i < supplier.length; i++) {
					sb = sb.append(supplier[i] + ",");

				}

				suppliers = sb.toString();
				suppliers = suppliers.substring(0, suppliers.length() - 1);
				System.out.println("suppliers id list is" + suppliers.toString());
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("fromDate", DateConvertor.convertToYMD(from_date));
			map.add("toDate", DateConvertor.convertToYMD(to_date));
			map.add("suppId", suppliers);
		 
			
			ParameterizedTypeReference<List<GetMaterialRecieptReportHsnCodeWise>> typeRef = new ParameterizedTypeReference<List<GetMaterialRecieptReportHsnCodeWise>>() {
			};
			ResponseEntity<List<GetMaterialRecieptReportHsnCodeWise>> responseEntity = rest.exchange(Constants.url + "materialRecieptHsnCodeWiseReport",
					HttpMethod.POST, new HttpEntity<>(map), typeRef);
			
			materialRecieptHsndCodeReport = responseEntity.getBody();
			
			materialRecieptHsndCodeReport = responseEntity.getBody();
			model.addObject("staticlist", materialRecieptHsndCodeReport);
			model.addObject("fromDate", from_date);
			model.addObject("toDate", to_date);
			model.addObject("staticlist", materialRecieptHsndCodeReport);
			System.out.println("HsndCodeReportpdf" + materialRecieptHsndCodeReport);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;

	}

	 
	@RequestMapping(value = "/materialRecieptsMonthWise", method = RequestMethod.GET)
	public ModelAndView materialRecieptsMonthWise(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/materialRecieptReport/monthWise");
		RestTemplate rest = new RestTemplate();
		try {
			supplierDetailsList = rest.getForObject(Constants.url + "/getAllSupplierlist", Supplist.class);
			model.addObject("supplierList", supplierDetailsList.getSupplierDetailslist());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;

	}

	@RequestMapping(value = "/materialRecieptMonthReport", method = RequestMethod.GET)
	public @ResponseBody List<GetMaterialRecieptReportMonthWise> materialRecieptMonthReport(HttpServletRequest request,
			HttpServletResponse response) {

		List<GetMaterialRecieptReportMonthWise> materialRecieptMonthReport = new ArrayList<GetMaterialRecieptReportMonthWise>();

		RestTemplate rest = new RestTemplate();
		try {
			String fromDate = request.getParameter("from_date");
			String todate = request.getParameter("to_date");
			String[] supplier = request.getParameterValues("suppliers[]");
			System.out.println("fromDate" + fromDate);
			System.out.println("todate" + todate);
			System.out.println("supplier" + supplier.toString());

			StringBuilder sb = new StringBuilder();
			String suppliers = null;

			if (supplier[0].equals("-1")) {
				System.out.println("in if");
				for (int i = 0; i < supplierDetailsList.getSupplierDetailslist().size(); i++) {
					sb = sb.append(supplierDetailsList.getSupplierDetailslist().get(i).getSuppId() + ",");

				}

				suppliers = sb.toString();
				suppliers = suppliers.substring(0, suppliers.length() - 1);
				System.out.println("suppliers id list is" + suppliers.toString());

			} else {
				for (int i = 0; i < supplier.length; i++) {
					sb = sb.append(supplier[i] + ",");

				}

				suppliers = sb.toString();
				suppliers = suppliers.substring(0, suppliers.length() - 1);
				System.out.println("suppliers id list is" + suppliers.toString());
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			map.add("toDate", DateConvertor.convertToYMD(todate));
			map.add("suppId", suppliers);
		 
			ParameterizedTypeReference<List<GetMaterialRecieptReportMonthWise>> typeRef = new ParameterizedTypeReference<List<GetMaterialRecieptReportMonthWise>>() {
			};
			ResponseEntity<List<GetMaterialRecieptReportMonthWise>> responseEntity = rest.exchange(Constants.url + "materialRecieptMonthWiseReport",
					HttpMethod.POST, new HttpEntity<>(map), typeRef);
			
			materialRecieptMonthReport = responseEntity.getBody();
			
			
		 
			System.out.println("materialRecieptMonthReport" + materialRecieptMonthReport.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//export to excel
		 
		
		List<ExportToExcel> exportToExcelList=new ArrayList<ExportToExcel>();
				
				ExportToExcel expoExcel=new ExportToExcel();
				List<String> rowData=new ArrayList<String>();
				 
				rowData.add("Mrn Id");
				rowData.add("Month");
				 
				rowData.add("Basic Value");
				 
				rowData.add("Discount Amt");
				rowData.add("Freight Amt");
				rowData.add("Insurance Amt");
				rowData.add("Other");
				rowData.add("cgst");
				 
				rowData.add("sgst");
				rowData.add("igst");
				rowData.add("cess");
				rowData.add("Bill Amt");
			 
			  
				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);
				for(int i=0;i<materialRecieptMonthReport.size();i++)
				{
					  expoExcel=new ExportToExcel();
					 rowData=new ArrayList<String>();
					 
				 
					 rowData.add(""+materialRecieptMonthReport.get(i).getMrnId());
					 rowData.add(""+materialRecieptMonthReport.get(i).getMonth());
				  
			 
					rowData.add(""+materialRecieptMonthReport.get(i).getBasicValue());
					rowData.add(""+materialRecieptMonthReport.get(i).getDiscAmt());
					rowData.add(""+materialRecieptMonthReport.get(i).getFreightAmt());
				 

					rowData.add(""+materialRecieptMonthReport.get(i).getInsuranceAmt());
					rowData.add(""+materialRecieptMonthReport.get(i).getOther());
					rowData.add(""+materialRecieptMonthReport.get(i).getCgst());
					rowData.add(""+materialRecieptMonthReport.get(i).getSgst());

					rowData.add(""+materialRecieptMonthReport.get(i).getIgst());
					rowData.add(""+materialRecieptMonthReport.get(i).getCess());
					rowData.add(""+materialRecieptMonthReport.get(i).getBillAmount());
					
					
					
					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);
					 
				}
				 
				
				
				HttpSession session = request.getSession();
				session.setAttribute("exportExcelList", exportToExcelList);
				session.setAttribute("excelName", "MonthWisePurchase");
				
				
		return materialRecieptMonthReport;

	}

	@RequestMapping(value = "pdf/monthWisePdf/{from_date}/{to_date}/{supplier}", method = RequestMethod.GET)
	public ModelAndView monthWisePdf(@PathVariable String from_date,@PathVariable String to_date,@PathVariable String supplier[],HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/materialRecieptReport/pdf/monthWisePdf");
		try {
			
			List<GetMaterialRecieptReportMonthWise> materialRecieptMonthReport = new ArrayList<GetMaterialRecieptReportMonthWise>();

			RestTemplate rest = new RestTemplate();
			
			StringBuilder sb = new StringBuilder();
			String suppliers = null;

			supplierDetailsList = rest.getForObject(Constants.url + "/getAllSupplierlist", Supplist.class);
			if (supplier[0].equals("-1")) {
				System.out.println("in if");
				for (int i = 0; i < supplierDetailsList.getSupplierDetailslist().size(); i++) {
					sb = sb.append(supplierDetailsList.getSupplierDetailslist().get(i).getSuppId() + ",");

				}

				suppliers = sb.toString();
				suppliers = suppliers.substring(0, suppliers.length() - 1);
				System.out.println("suppliers id list is" + suppliers.toString());

			} else {
				for (int i = 0; i < supplier.length; i++) {
					sb = sb.append(supplier[i] + ",");

				}

				suppliers = sb.toString();
				suppliers = suppliers.substring(0, suppliers.length() - 1);
				System.out.println("suppliers id list is" + suppliers.toString());
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("fromDate", DateConvertor.convertToYMD(from_date));
			map.add("toDate", DateConvertor.convertToYMD(to_date));
			map.add("suppId", suppliers);
		 
			
			ParameterizedTypeReference<List<GetMaterialRecieptReportMonthWise>> typeRef = new ParameterizedTypeReference<List<GetMaterialRecieptReportMonthWise>>() {
			};
			ResponseEntity<List<GetMaterialRecieptReportMonthWise>> responseEntity = rest.exchange(Constants.url + "materialRecieptMonthWiseReport",
					HttpMethod.POST, new HttpEntity<>(map), typeRef);
			
			materialRecieptMonthReport = responseEntity.getBody();
			
			materialRecieptMonthReport = responseEntity.getBody();
			model.addObject("staticlist", materialRecieptMonthReport);
			model.addObject("fromDate", from_date);
			model.addObject("toDate", to_date);
			model.addObject("staticlist", materialRecieptMonthReport);
			System.out.println("monthwisepdf" + materialRecieptMonthReport);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;

	}

	private Dimension format = PD4Constants.A2;
	private boolean landscapeValue = false;
	private int topValue = 8;
	private int leftValue = 0;
	private int rightValue = 0;
	private int bottomValue =8;
	private String unitsValue = "m";
	private String proxyHost = "";
	private int proxyPort = 0;

	private int userSpaceWidth = 750;
	private static int BUFFER_SIZE = 1024;

	@RequestMapping(value = "/materialRec", method = RequestMethod.GET)
	public void showPDF(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Inside PDf For Report URL ");
		String url = request.getParameter("url");
		System.out.println("URL " + url);
		
		File f = new File("/opt/tomcat-latest/webapps/uploads/Inward.pdf");
		//File f = new File("C:/pdf/ordermemo221.pdf");

		try {
			runConverter(Constants.ReportURL + url, f,request,response);
			//runConverter("www.google.com", f,request,response);

		} catch (IOException e) {

			System.out.println("Pdf conversion exception " + e.getMessage());
		}

		// get absolute path of the application
		ServletContext context = request.getSession().getServletContext();
		String appPath = context.getRealPath("");
		// String filePath = "C:/pdf/ordermemo221.pdf";

		String filePath = "/opt/tomcat-latest/webapps/uploads/Inward.pdf";

		// construct the complete absolute path of the file
		String fullPath = appPath + filePath;
		File downloadFile = new File(filePath);
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(downloadFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			// get MIME type of the file
			String mimeType = context.getMimeType(fullPath);
			if (mimeType == null) {
				// set to binary type if MIME mapping not found
				mimeType = "application/pdf";
			}
			System.out.println("MIME type: " + mimeType);

			String headerKey = "Content-Disposition";

			// response.addHeader("Content-Disposition", "attachment;filename=report.pdf");
			response.setContentType("application/pdf");

			OutputStream outStream;

			outStream = response.getOutputStream();

			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;


			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}

			inputStream.close();
			outStream.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void runConverter(String urlstring, File output, HttpServletRequest request, HttpServletResponse response ) throws IOException {

		if (urlstring.length() > 0) {
			if (!urlstring.startsWith("http://") && !urlstring.startsWith("file:")) {
				urlstring = "http://" + urlstring;
			}
			System.out.println("PDF URL " + urlstring);
			java.io.FileOutputStream fos = new java.io.FileOutputStream(output);

			PD4ML pd4ml = new PD4ML();
		
			try {

				Dimension landscapeA4 = pd4ml.changePageOrientation(PD4Constants.A4);
				pd4ml.setPageSize(landscapeA4);
			
				PD4PageMark footer = new PD4PageMark();  
				
	            footer.setPageNumberTemplate("Page $[page] of $[total]");  
	            footer.setPageNumberAlignment(PD4PageMark.RIGHT_ALIGN);  
	            footer.setFontSize(10);  
	            footer.setAreaHeight(20);     
	            
	            pd4ml.setPageFooter(footer); 
				
			} catch (Exception e) {
				System.out.println("Pdf conversion method excep " + e.getMessage());
			}

			if (unitsValue.equals("mm")) {
				pd4ml.setPageInsetsMM(new Insets(topValue, leftValue, bottomValue, rightValue));
			} else {
				pd4ml.setPageInsets(new Insets(topValue, leftValue, bottomValue, rightValue));
			}

			pd4ml.setHtmlWidth(userSpaceWidth);

			pd4ml.render(urlstring, fos);
		}
	}
	
/*	public void showPDF(HttpServletRequest request, HttpServletResponse response) {

		String url = request.getParameter("url");
		System.out.println("URL " + url);
		// http://monginis.ap-south-1.elasticbeanstalk.com
		File f = new File("/home/ats-12/pdf/Report.pdf");
		//File f = new File("/ordermemo221.pdf");
		System.out.println("I am here " + f.toString());
		try {
			runConverter(Constants.ReportURL + url, f);
			System.out.println("Come on lets get ");
		} catch (IOException e) {
			// TODO Auto-generated catch block

			System.out.println("Pdf conversion exception " + e.getMessage());
		}

		// get absolute path of the application
		ServletContext context = request.getSession().getServletContext();
		String appPath = context.getRealPath("");
		String filename = "ordermemo221.pdf";
		String filePath = "/home/ats-12/pdf/Report.pdf";
		//String filePath = "/ordermemo221.pdf";

		// construct the complete absolute path of the file
		String fullPath = appPath + filePath;
		File downloadFile = new File(filePath);
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(downloadFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			// get MIME type of the file
			String mimeType = context.getMimeType(fullPath);
			if (mimeType == null) {
				// set to binary type if MIME mapping not found
				mimeType = "application/pdf";
			}
			System.out.println("MIME type: " + mimeType);

			String headerKey = "Content-Disposition";

			// response.addHeader("Content-Disposition", "attachment;filename=report.pdf");
			response.setContentType("application/pdf");

			// get output stream of the response
			OutputStream outStream;

			outStream = response.getOutputStream();

			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;

			// write bytes read from the input stream into the output stream

			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}

			inputStream.close();
			outStream.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void runConverter(String urlstring, File output) throws IOException {

		if (urlstring.length() > 0) {
			if (!urlstring.startsWith("http://") && !urlstring.startsWith("file:")) {
				urlstring = "http://" + urlstring;
			}

			java.io.FileOutputStream fos = new java.io.FileOutputStream(output);

			if (proxyHost != null && proxyHost.length() != 0 && proxyPort != 0) {
				System.getProperties().setProperty("proxySet", "true");
				System.getProperties().setProperty("proxyHost", proxyHost);
				System.getProperties().setProperty("proxyPort", "" + proxyPort);
			}

			PD4ML pd4ml = new PD4ML();

			try {
				pd4ml.setPageSize(landscapeValue ? pd4ml.changePageOrientation(format) : format);
			} catch (Exception e) {
				System.out.println("Pdf conversion ethod excep " + e.getMessage());
			}

			if (unitsValue.equals("mm")) {
				pd4ml.setPageInsetsMM(new Insets(topValue, leftValue, bottomValue, rightValue));
			} else {
				pd4ml.setPageInsets(new Insets(topValue, leftValue, bottomValue, rightValue));
			}

			pd4ml.setHtmlWidth(userSpaceWidth);

			pd4ml.render(urlstring, fos);
		}
	}*/

}
