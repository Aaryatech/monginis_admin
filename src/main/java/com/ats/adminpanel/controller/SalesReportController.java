package com.ats.adminpanel.controller;

import java.awt.Dimension;
import java.awt.Insets;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
import org.springframework.util.FileCopyUtils;
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
import com.ats.adminpanel.model.AllFrIdName;
import com.ats.adminpanel.model.AllFrIdNameList;
import com.ats.adminpanel.model.AllRoutesListResponse;
import com.ats.adminpanel.model.DispatchReport;
import com.ats.adminpanel.model.DispatchReportList;
import com.ats.adminpanel.model.ExportToExcel;
import com.ats.adminpanel.model.Info;
import com.ats.adminpanel.model.ItemWiseGrnGvnReport;
import com.ats.adminpanel.model.MiniSubCategory;
import com.ats.adminpanel.model.Orders;
import com.ats.adminpanel.model.Route;
import com.ats.adminpanel.model.SettingNew;
import com.ats.adminpanel.model.accessright.ModuleJson;
import com.ats.adminpanel.model.franchisee.FrNameIdByRouteId;
import com.ats.adminpanel.model.franchisee.FrNameIdByRouteIdResponse;
import com.ats.adminpanel.model.franchisee.FranchiseeAndMenuList;
import com.ats.adminpanel.model.franchisee.Menu;
import com.ats.adminpanel.model.franchisee.SubCategory;
import com.ats.adminpanel.model.ggreports.GrnGvnReportByGrnType;
import com.ats.adminpanel.model.item.AllItemsListResponse;
import com.ats.adminpanel.model.item.CategoryListResponse;
import com.ats.adminpanel.model.item.FrItemStockConfigureList;
import com.ats.adminpanel.model.item.GetItemSup;
import com.ats.adminpanel.model.item.Item;
import com.ats.adminpanel.model.item.MCategoryList;
import com.ats.adminpanel.model.production.OrderDispatchRepDao;
import com.ats.adminpanel.model.salesreport.CrnSalesReportDateWise;
import com.ats.adminpanel.model.salesreport.RoyaltyListBean;
import com.ats.adminpanel.model.salesreport.RoyaltyListBeanConsByCat;
import com.ats.adminpanel.model.salesreport.SalesReportBillwise;
import com.ats.adminpanel.model.salesreport.SalesReportBillwiseAllFr;
import com.ats.adminpanel.model.salesreport.SalesReportItemwise;
import com.ats.adminpanel.model.salesreport.SalesReportRoyalty;
import com.ats.adminpanel.model.salesreport.SalesReportRoyaltyFr;
import com.ats.adminpanel.model.salesreport.SalesRoyaltyConsByCat;
import com.ats.adminpanel.model.salesreport.SubCatFrRepItemList;
import com.ats.adminpanel.model.salesreport.SubCatItemReport;
import com.ats.adminpanel.model.salesreport.Tax1Report;
import com.ats.adminpanel.model.salesreport2.CrNoteRegItem;
import com.ats.adminpanel.model.salesreport2.CrNoteRegSp;
import com.ats.adminpanel.model.salesreport2.CrNoteRegisterList;
import com.ats.adminpanel.model.salesreport2.SalesReport;
import com.ats.adminpanel.model.salesreport2.SubCatFrReport;
import com.ats.adminpanel.model.salesreport2.SubCatFrReportList;
import com.ats.adminpanel.model.salesreport2.SubCatReport;
import com.ats.adminpanel.model.salesvaluereport.SalesReturnValueDaoList;
import com.ats.adminpanel.util.ItextPageEvent;
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
public class SalesReportController {

	List<String> frList = new ArrayList<>();
	AllFrIdNameList allFrIdNameList = new AllFrIdNameList();
	List<SalesReportBillwise> saleListForPdf;// it is Static
	String todaysDate;
	List<SalesReportRoyalty> royaltyListForPdf;

	List<SalesReportBillwiseAllFr> staticSaleByAllFr;

	List<SalesReportRoyaltyFr> royaltyFrList;

	List<SalesReportRoyaltyFr> staticRoyaltyFrList;

	List<SalesReportItemwise> staticSaleListItemWise;

	List<MCategoryList> mCategoryList;

	RoyaltyListBeanConsByCat staticRoyaltyBeanConsByCat = new RoyaltyListBeanConsByCat();
	RoyaltyListBean staticRoyaltyBean = new RoyaltyListBean();

	List<SalesRoyaltyConsByCat> royaltyConsByCatListForPdf;

	public static float roundUp(float d) {
		return BigDecimal.valueOf(d).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
	}

	float getRoyPer() {

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		String settingKey = new String();

		settingKey = "roy_percentage";

		map.add("settingKeyList", settingKey);

		FrItemStockConfigureList settingList = restTemplate.postForObject(Constants.url + "getDeptSettingValue", map,
				FrItemStockConfigureList.class);

		float royPer = settingList.getFrItemStockConfigure().get(0).getSettingValue();

		return royPer;
	}

	@RequestMapping(value = "/showSaleReports", method = RequestMethod.GET)
	public ModelAndView showSaleReporPage(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/viewSalesReports");

		return model;

	}

	@RequestMapping(value = "/showSaleReportByDate", method = RequestMethod.GET)
	public ModelAndView showSaleReportByDate(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/billwisesalesbydate");

		// Constants.mainAct =2;
		// Constants.subAct =20;

		try {
			ZoneId z = ZoneId.of("Asia/Calcutta");

			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			todaysDate = date.format(formatters);

			RestTemplate restTemplate = new RestTemplate();

			// get Routes

			AllRoutesListResponse allRouteListResponse = restTemplate.getForObject(Constants.url + "showRouteList",
					AllRoutesListResponse.class);

			List<Route> routeList = new ArrayList<Route>();

			routeList = allRouteListResponse.getRoute();

			// end get Routes

			allFrIdNameList = new AllFrIdNameList();
			try {

				allFrIdNameList = restTemplate.getForObject(Constants.url + "getAllFranchises", AllFrIdNameList.class);
				//getAllFrIdName
			} catch (Exception e) {
				System.out.println("Exception in getAllFrIdName" + e.getMessage());
				e.printStackTrace();

			}
			List<AllFrIdName> selectedFrListAll = new ArrayList();
			List<Menu> selectedMenuList = new ArrayList<Menu>();

			System.out.println(" Fr " + allFrIdNameList.getFrIdNamesList());

			model.addObject("todaysDate", todaysDate);
			model.addObject("unSelectedFrList", allFrIdNameList.getFrIdNamesList());

			model.addObject("routeList", routeList);

		} catch (Exception e) {

			System.out.println("Exc in show sales report bill wise  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/itemwiseGrnGvnReport", method = RequestMethod.GET)
	public ModelAndView itemwiseGrnGvnReport(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/grnGvn/itemwiseGrnGvnReport");

		try {

			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");

			if (fromDate != null && toDate != null) {

				String isGrn = request.getParameter("isGrn");

				int grn = Integer.parseInt(isGrn);

				if (isGrn.equals("-1")) {
					isGrn = "0,1,2";
				} else if (isGrn.equals("0")) {
					isGrn = "0,2";
				}

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("fromDate", DateConvertor.convertToYMD(fromDate));
				map.add("toDate", DateConvertor.convertToYMD(toDate));
				map.add("isGrn", isGrn);

				RestTemplate restTemplate = new RestTemplate();
				ItemWiseGrnGvnReport[] itemWiseGrnGvnReport = restTemplate.postForObject(
						Constants.url + "itemwiseGrnGvnReportbetweenDate", map, ItemWiseGrnGvnReport[].class);

				List<ItemWiseGrnGvnReport> list = new ArrayList<>(Arrays.asList(itemWiseGrnGvnReport));
				model.addObject("list", list);
				model.addObject("fromDate", fromDate);
				model.addObject("toDate", toDate);
				model.addObject("grn", grn);

				List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

				ExportToExcel expoExcel = new ExportToExcel();
				List<String> rowData = new ArrayList<String>();

				rowData.add("Sr No");
				rowData.add("Item Name");
				rowData.add("Request QTY");
				rowData.add("Approved QTY");
				rowData.add("Taxable AMT");
				rowData.add("Tax AMT");
				rowData.add("Total");

				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);
				for (int i = 0; i < list.size(); i++) {
					expoExcel = new ExportToExcel();
					rowData = new ArrayList<String>();

					rowData.add("" + (i + 1));
					rowData.add(list.get(i).getItemName());
					rowData.add("" + list.get(i).getGrnGvnQty());
					rowData.add("" + list.get(i).getAprQtyAcc());
					rowData.add("" + list.get(i).getAprTaxableAmt());
					rowData.add("" + list.get(i).getAprTotalTax());
					rowData.add("" + list.get(i).getAprGrandTotal());

					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);

				}

				HttpSession session = request.getSession();
				session.setAttribute("exportExcelList", exportToExcelList);
				session.setAttribute("excelName", "itemwisegrnreport");
			} else {

				Date dt = new Date();
				SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");

				model.addObject("fromDate", sf.format(dt));
				model.addObject("toDate", sf.format(dt));
			}

		} catch (Exception e) {

			System.out.println("Exc in show sales report bill wise  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "pdf/showItemwiseGrnReportPdf/{fromDate}/{toDate}/{isGrn}", method = RequestMethod.GET)
	public ModelAndView showItemwiseGrnReportPdf(@PathVariable String fromDate, @PathVariable String toDate,
			@PathVariable String isGrn, HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/grnGvn/pdf/itemwiseGrnReportPdf");

		try {
			if (isGrn.equals("-1")) {
				isGrn = "0,1,2";
			} else if (isGrn.equals("0")) {
				isGrn = "0,2";
			}
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			map.add("toDate", DateConvertor.convertToYMD(toDate));
			map.add("isGrn", isGrn);

			RestTemplate restTemplate = new RestTemplate();
			ItemWiseGrnGvnReport[] itemWiseGrnGvnReport = restTemplate.postForObject(
					Constants.url + "itemwiseGrnGvnReportbetweenDate", map, ItemWiseGrnGvnReport[].class);

			List<ItemWiseGrnGvnReport> list = new ArrayList<>(Arrays.asList(itemWiseGrnGvnReport));
			model.addObject("list", list);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		model.addObject("fromDate", fromDate);
		model.addObject("toDate", toDate);
		return model;
	}

	@RequestMapping(value = "/getSaleBillwise", method = RequestMethod.GET)
	public @ResponseBody List<SalesReportBillwise> saleReportBillWise(HttpServletRequest request,
			HttpServletResponse response) {

		List<SalesReportBillwise> saleList = new ArrayList<>();
		String fromDate = "";
		String toDate = "";
		try {
			System.out.println("Inside get Sale Bill Wise");
			String selectedFr = request.getParameter("fr_id_list");
			fromDate = request.getParameter("fromDate");
			toDate = request.getParameter("toDate");
			String routeId = request.getParameter("route_id");

			boolean isAllFrSelected = false;
			selectedFr = selectedFr.substring(1, selectedFr.length() - 1);
			selectedFr = selectedFr.replaceAll("\"", "");

			frList = new ArrayList<>();
			frList = Arrays.asList(selectedFr);

			if (!routeId.equalsIgnoreCase("0")) {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				RestTemplate restTemplate = new RestTemplate();

				map.add("routeId", routeId);

				FrNameIdByRouteIdResponse frNameId = restTemplate.postForObject(Constants.url + "getFrNameIdByRouteId",
						map, FrNameIdByRouteIdResponse.class);

				List<FrNameIdByRouteId> frNameIdByRouteIdList = frNameId.getFrNameIdByRouteIds();

				System.out.println("route wise franchisee " + frNameIdByRouteIdList.toString());

				StringBuilder sbForRouteFrId = new StringBuilder();
				for (int i = 0; i < frNameIdByRouteIdList.size(); i++) {

					sbForRouteFrId = sbForRouteFrId.append(frNameIdByRouteIdList.get(i).getFrId().toString() + ",");

				}

				String strFrIdRouteWise = sbForRouteFrId.toString();
				selectedFr = strFrIdRouteWise.substring(0, strFrIdRouteWise.length() - 1);
				System.out.println("fr Id Route WISE = " + selectedFr);

			} // end of if

			if (frList.contains("-1")) {
				isAllFrSelected = true;
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();
			float tcsPer = getTcsPer();
			if (isAllFrSelected) {

				System.out.println("Inside If all fr Selected ");

				map.add("fromDate", fromDate);
				map.add("toDate", toDate);
				ParameterizedTypeReference<List<SalesReportBillwise>> typeRef = new ParameterizedTypeReference<List<SalesReportBillwise>>() {
				};
				ResponseEntity<List<SalesReportBillwise>> responseEntity = restTemplate.exchange(
						Constants.url + "getSaleReportBillwiseAllFrSelected", HttpMethod.POST, new HttpEntity<>(map),
						typeRef);
				saleList = responseEntity.getBody();
				
				for (int i = 0; i < saleList.size(); i++) {		
					saleList.get(i).setTcsAmt(saleList.get(i).getRoundOff());
				}
				saleListForPdf = new ArrayList<>();
				saleListForPdf = saleList;
				System.out.println("sales List Bill Wise " + saleList.toString());

			} else {
				System.out.println("Inside else Few fr Selected ");

				map.add("frIdList", selectedFr);
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				ParameterizedTypeReference<List<SalesReportBillwise>> typeRef = new ParameterizedTypeReference<List<SalesReportBillwise>>() {
				};
				ResponseEntity<List<SalesReportBillwise>> responseEntity = restTemplate.exchange(
						Constants.url + "getSaleReportBillwise", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				saleList = responseEntity.getBody();
				
				for (int i = 0; i < saleList.size(); i++) {	
					saleList.get(i).setTcsAmt(saleList.get(i).getRoundOff());
				}
				saleListForPdf = new ArrayList<>();
				saleListForPdf = saleList;
				System.out.println("sales List Bill Wise " + saleList.toString());

			}
		} catch (Exception e) {
			System.out.println("get sale Report Bill Wise " + e.getMessage());
			e.printStackTrace();

		}

		float ttlTaxable = 0;
		float ttlCgst = 0;
		float ttlSgst = 0;
		float ttlIgst = 0;
		float ttlTcs = 0;
		float ttlTax = 0;
		
		float total = 0;
		float grandTotal = 0;
		
		// exportToExcel
		List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

		ExportToExcel expoExcel = new ExportToExcel();
		List<String> rowData = new ArrayList<String>();

		rowData.add("Bill No");
		rowData.add("Invoice No");
		rowData.add("Bill Date");
		rowData.add("Franchisee Code");
		rowData.add("Franchisee Name");
		rowData.add("Franchisee City");
		rowData.add("Franchisee Gst No");
		rowData.add("Sgst sum");
		rowData.add("Cgst sum");
		rowData.add("Igst sum");
		rowData.add("Total Tax");
		rowData.add("Taxable Amt");
		rowData.add("TCS Amt");
		rowData.add("Grand Total");

		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);
		for (int i = 0; i < saleList.size(); i++) {
			expoExcel = new ExportToExcel();
			rowData = new ArrayList<String>();
			
			total = saleList.get(i).getTaxableAmt()+saleList.get(i).getCgstSum()+saleList.get(i).getSgstSum()+saleList.get(i).getTcsAmt();


			rowData.add("" + saleList.get(i).getBillNo());
			rowData.add(saleList.get(i).getInvoiceNo());
			rowData.add(saleList.get(i).getBillDate());

			rowData.add("" + saleList.get(i).getFrId());
			rowData.add(saleList.get(i).getFrName());

			rowData.add(saleList.get(i).getFrCity());
			rowData.add(saleList.get(i).getFrGstNo());
			rowData.add("" + saleList.get(i).getSgstSum());
			rowData.add("" + saleList.get(i).getCgstSum());
			rowData.add("" + saleList.get(i).getIgstSum());
			rowData.add("" + saleList.get(i).getTotalTax());
			rowData.add("" + saleList.get(i).getTaxableAmt());
			rowData.add("" + saleList.get(i).getTcsAmt());
			rowData.add("" + total);
			
			ttlTaxable = ttlTaxable + saleList.get(i).getTaxableAmt();
			ttlCgst = ttlCgst + saleList.get(i).getCgstSum();
			ttlSgst = ttlSgst + saleList.get(i).getCgstSum();
			ttlIgst = ttlIgst + saleList.get(i).getIgstSum();
			ttlTcs = ttlTcs + saleList.get(i).getTcsAmt();
			ttlTax = ttlTax + saleList.get(i).getTotalTax();
			grandTotal = grandTotal + total;
			
			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);

		}
		
		expoExcel = new ExportToExcel();
		rowData = new ArrayList<String>();

		rowData.add("Total");
		rowData.add("");
		rowData.add("");
		rowData.add("");
		rowData.add("");
		rowData.add("");
		rowData.add("");
		
		rowData.add("" + roundUp(ttlSgst));
		rowData.add("" + roundUp(ttlCgst));
		rowData.add("" + roundUp(ttlIgst));
		rowData.add("" + roundUp(ttlTax));
		rowData.add("" + roundUp(ttlTaxable));
		rowData.add("" + roundUp(ttlTcs));
		rowData.add("" + roundUp(grandTotal));
		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);

		HttpSession session = request.getSession();
		session.setAttribute("exportExcelListNew", exportToExcelList);
		session.setAttribute("excelNameNew", "SaleBillWiseDate");
		session.setAttribute("reportNameNew", "Billwise Report");
		session.setAttribute("searchByNew", "From Date: " + fromDate + "  To Date: " + toDate + " ");
		session.setAttribute("mergeUpto1", "$A$1:$M$1");
		session.setAttribute("mergeUpto2", "$A$2:$M$2");

		return saleList;
	}

	
	@RequestMapping(value = "pdf/showSaleReportByDatePdf/{fDate}/{tDate}/{selectedFr}/{routeId}", method = RequestMethod.GET)
	public ModelAndView showSaleReportByDatePdf(@PathVariable String fDate, @PathVariable String tDate,
			@PathVariable String selectedFr, @PathVariable String routeId, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/pdf/billwisesalesbydatePdf");

		List<SalesReportBillwise> saleList = new ArrayList<>();

		boolean isAllFrSelected = false;
		try {

			float tcsPer = getTcsPer();	
			if (!routeId.equalsIgnoreCase("0")) {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				RestTemplate restTemplate = new RestTemplate();

				map.add("routeId", routeId);

				FrNameIdByRouteIdResponse frNameId = restTemplate.postForObject(Constants.url + "getFrNameIdByRouteId",
						map, FrNameIdByRouteIdResponse.class);

				List<FrNameIdByRouteId> frNameIdByRouteIdList = frNameId.getFrNameIdByRouteIds();

				System.out.println("route wise franchisee " + frNameIdByRouteIdList.toString());

				StringBuilder sbForRouteFrId = new StringBuilder();
				for (int i = 0; i < frNameIdByRouteIdList.size(); i++) {

					sbForRouteFrId = sbForRouteFrId.append(frNameIdByRouteIdList.get(i).getFrId().toString() + ",");

				}

				String strFrIdRouteWise = sbForRouteFrId.toString();
				selectedFr = strFrIdRouteWise.substring(0, strFrIdRouteWise.length() - 1);
				System.out.println("fr Id Route WISE = " + selectedFr);

			} // end of if

			if (selectedFr.equalsIgnoreCase("-1")) {
				isAllFrSelected = true;
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			if (isAllFrSelected) {

				System.out.println("Inside If all fr Selected ");

				map.add("fromDate", fDate);
				map.add("toDate", tDate);
				ParameterizedTypeReference<List<SalesReportBillwise>> typeRef = new ParameterizedTypeReference<List<SalesReportBillwise>>() {
				};
				ResponseEntity<List<SalesReportBillwise>> responseEntity = restTemplate.exchange(
						Constants.url + "getSaleReportBillwiseAllFrSelected", HttpMethod.POST, new HttpEntity<>(map),
						typeRef);

				saleList = responseEntity.getBody();
				for (int i = 0; i < saleList.size(); i++) {					
					saleList.get(i).setTcsAmt(saleList.get(i).getRoundOff());
				}
				saleListForPdf = new ArrayList<>();
				saleListForPdf = saleList;
				System.out.println("sales List Bill Wise " + saleList.toString());

			} else {
				System.out.println("Inside else Few fr Selected ");

				map.add("frIdList", selectedFr);
				map.add("fromDate", fDate);
				map.add("toDate", tDate);

				ParameterizedTypeReference<List<SalesReportBillwise>> typeRef = new ParameterizedTypeReference<List<SalesReportBillwise>>() {
				};
				ResponseEntity<List<SalesReportBillwise>> responseEntity = restTemplate.exchange(
						Constants.url + "getSaleReportBillwise", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				saleList = responseEntity.getBody();
				for (int i = 0; i < saleList.size(); i++) {					
					saleList.get(i).setTcsAmt(saleList.get(i).getRoundOff());
				}
				saleListForPdf = new ArrayList<>();
				saleListForPdf = saleList;
				System.out.println("sales List Bill Wise " + saleList.toString());

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		model.addObject("fromDate", fDate);

		model.addObject("toDate", tDate);

		model.addObject("report", saleList);

		return model;
	}

	// report 2
	@RequestMapping(value = "/showSaleReportByFr", method = RequestMethod.GET)
	public ModelAndView showSaleReportByFr(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/billwisesalebyfr");
		System.out.println("inside showSaleReportByFr ");
		// Constants.mainAct =2;
		// Constants.subAct =20;
		HttpSession session = request.getSession();

		System.out.println("session Id in show Page  " + session.getId());

		try {
			ZoneId z = ZoneId.of("Asia/Calcutta");

			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			String todaysDate = date.format(formatters);

			RestTemplate restTemplate = new RestTemplate();

			// get Routes

			AllRoutesListResponse allRouteListResponse = restTemplate.getForObject(Constants.url + "showRouteList",
					AllRoutesListResponse.class);

			List<Route> routeList = new ArrayList<Route>();

			routeList = allRouteListResponse.getRoute();

			// end get Routes

			allFrIdNameList = new AllFrIdNameList();
			try {

				allFrIdNameList = restTemplate.getForObject(Constants.url + "getAllFranchises", AllFrIdNameList.class);

			} catch (Exception e) {
				System.out.println("Exception in getAllFrIdName" + e.getMessage());
				e.printStackTrace();

			}
			List<AllFrIdName> selectedFrListAll = new ArrayList();
			List<Menu> selectedMenuList = new ArrayList<Menu>();

			System.out.println(" Fr " + allFrIdNameList.getFrIdNamesList());

			model.addObject("todaysDate", todaysDate);
			model.addObject("unSelectedFrList", allFrIdNameList.getFrIdNamesList());

			model.addObject("routeList", routeList);

		} catch (Exception e) {

			System.out.println("Exc in show sales report bill wise  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/getSaleBillwiseByFr", method = RequestMethod.GET)
	public @ResponseBody List<SalesReportBillwise> getSaleBillwiseByFr(HttpServletRequest request,
			HttpServletResponse response) {

		List<SalesReportBillwise> saleList = new ArrayList<>();
		String fromDate = "";
		String toDate = "";
		try {
			System.out.println("Inside get Sale Bill Wise");
			String selectedFr = request.getParameter("fr_id_list");
			fromDate = request.getParameter("fromDate");
			toDate = request.getParameter("toDate");
			String routeId = request.getParameter("route_id");

			boolean isAllFrSelected = false;
			selectedFr = selectedFr.substring(1, selectedFr.length() - 1);
			selectedFr = selectedFr.replaceAll("\"", "");

			frList = new ArrayList<>();
			frList = Arrays.asList(selectedFr);

			if (!routeId.equalsIgnoreCase("0")) {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				RestTemplate restTemplate = new RestTemplate();

				map.add("routeId", routeId);

				FrNameIdByRouteIdResponse frNameId = restTemplate.postForObject(Constants.url + "getFrNameIdByRouteId",
						map, FrNameIdByRouteIdResponse.class);

				List<FrNameIdByRouteId> frNameIdByRouteIdList = frNameId.getFrNameIdByRouteIds();

				System.out.println("route wise franchisee " + frNameIdByRouteIdList.toString());

				StringBuilder sbForRouteFrId = new StringBuilder();
				for (int i = 0; i < frNameIdByRouteIdList.size(); i++) {

					sbForRouteFrId = sbForRouteFrId.append(frNameIdByRouteIdList.get(i).getFrId().toString() + ",");

				}

				String strFrIdRouteWise = sbForRouteFrId.toString();
				selectedFr = strFrIdRouteWise.substring(0, strFrIdRouteWise.length() - 1);
				System.out.println("fr Id Route WISE = " + selectedFr);

			} // end of if

			if (frList.contains("-1")) {
				isAllFrSelected = true;
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			if (isAllFrSelected) {

				System.out.println("Inside If all fr Selected ");

				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				ParameterizedTypeReference<List<SalesReportBillwise>> typeRef = new ParameterizedTypeReference<List<SalesReportBillwise>>() {
				};
				ResponseEntity<List<SalesReportBillwise>> responseEntity = restTemplate.exchange(
						Constants.url + "getSaleReportBillwiseByFrAllFr", HttpMethod.POST, new HttpEntity<>(map),
						typeRef);

				saleList = responseEntity.getBody();
				saleListForPdf = new ArrayList<>();
				String name = "Sachin";

				HttpSession session = request.getSession();

				System.out.println("session Id  In Ajax Call " + session.getId());
				session.setAttribute("pdfData", saleList);

				session.setAttribute("name", name);
				saleListForPdf = saleList;

				System.out.println("sales List Bill Wise " + saleList.toString());

			} else {
				System.out.println("Inside else Few fr Selected ");

				map.add("frIdList", selectedFr);
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				ParameterizedTypeReference<List<SalesReportBillwise>> typeRef = new ParameterizedTypeReference<List<SalesReportBillwise>>() {
				};
				ResponseEntity<List<SalesReportBillwise>> responseEntity = restTemplate.exchange(
						Constants.url + "getSaleReportBillwiseByFr", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				saleList = responseEntity.getBody();
				saleListForPdf = new ArrayList<>();
				String name = "Sachin";

				HttpSession session = request.getSession();

				System.out.println("session Id  In Ajax Call " + session.getId());
				session.setAttribute("pdfData", saleList);

				session.setAttribute("name", name);
				saleListForPdf = saleList;

				System.out.println("sales List Bill Wise " + saleList.toString());

			}
		} catch (Exception e) {
			System.out.println("get sale Report Bill Wise " + e.getMessage());
			e.printStackTrace();

		}

		// exportToExcel
		List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

		ExportToExcel expoExcel = new ExportToExcel();
		List<String> rowData = new ArrayList<String>();

		rowData.add("Bill No");
		rowData.add("Invoice No");
		rowData.add("Bill Date");
		rowData.add("Franchisee Code");
		rowData.add("Franchisee Name");
		rowData.add("Franchisee City");
		rowData.add("Franchisee Gst No");
		rowData.add("sgst sum");
		rowData.add("cgst sum");
		rowData.add("igst sum");
		rowData.add("Total Tax");
		rowData.add("Taxable Amt");
		rowData.add("Grand Total");

		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);
		for (int i = 0; i < saleList.size(); i++) {
			expoExcel = new ExportToExcel();
			rowData = new ArrayList<String>();

			rowData.add("" + saleList.get(i).getBillNo());
			rowData.add(saleList.get(i).getInvoiceNo());
			rowData.add(saleList.get(i).getBillDate());

			rowData.add("" + saleList.get(i).getFrId());
			rowData.add(saleList.get(i).getFrName());

			rowData.add(saleList.get(i).getFrCity());
			rowData.add(saleList.get(i).getFrGstNo());
			rowData.add("" + saleList.get(i).getSgstSum());
			rowData.add("" + saleList.get(i).getCgstSum());
			rowData.add("" + saleList.get(i).getIgstSum());
			rowData.add("" + saleList.get(i).getTotalTax());

			rowData.add("" + saleList.get(i).getTaxableAmt());
			rowData.add("" + saleList.get(i).getGrandTotal());
			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);

		}

		HttpSession session = request.getSession();
		session.setAttribute("exportExcelListNew", exportToExcelList);
		session.setAttribute("excelNameNew", "SaleBillWiseFr");
		session.setAttribute("reportNameNew", "Billwise Report by Franchise");
		session.setAttribute("searchByNew", "From Date: " + fromDate + "  To Date: " + toDate + " ");
		session.setAttribute("mergeUpto1", "$A$1:$M$1");
		session.setAttribute("mergeUpto2", "$A$2:$M$2");
		return saleList;
	}

	@RequestMapping(value = "pdf/showSaleBillwiseByFrPdf/{fromDate}/{toDate}/{selectedFr}/{routeId}", method = RequestMethod.GET)
	public ModelAndView showSaleBillwiseByFrPdf(@PathVariable String fromDate, @PathVariable String toDate,
			@PathVariable String selectedFr, @PathVariable String routeId, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView model = new ModelAndView("reports/sales/pdf/billwisesalebyfrPdf");

		List<SalesReportBillwise> saleList = new ArrayList<>();
		boolean isAllFrSelected = false;

		try {

			if (!routeId.equalsIgnoreCase("0")) {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				RestTemplate restTemplate = new RestTemplate();

				map.add("routeId", routeId);

				FrNameIdByRouteIdResponse frNameId = restTemplate.postForObject(Constants.url + "getFrNameIdByRouteId",
						map, FrNameIdByRouteIdResponse.class);

				List<FrNameIdByRouteId> frNameIdByRouteIdList = frNameId.getFrNameIdByRouteIds();

				System.out.println("route wise franchisee " + frNameIdByRouteIdList.toString());

				StringBuilder sbForRouteFrId = new StringBuilder();
				for (int i = 0; i < frNameIdByRouteIdList.size(); i++) {

					sbForRouteFrId = sbForRouteFrId.append(frNameIdByRouteIdList.get(i).getFrId().toString() + ",");

				}

				String strFrIdRouteWise = sbForRouteFrId.toString();
				selectedFr = strFrIdRouteWise.substring(0, strFrIdRouteWise.length() - 1);
				System.out.println("fr Id Route WISE = " + selectedFr);

			} // end of if

			if (selectedFr.equalsIgnoreCase("-1")) {
				isAllFrSelected = true;
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			if (isAllFrSelected) {

				System.out.println("Inside If all fr Selected ");

				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				ParameterizedTypeReference<List<SalesReportBillwise>> typeRef = new ParameterizedTypeReference<List<SalesReportBillwise>>() {
				};
				ResponseEntity<List<SalesReportBillwise>> responseEntity = restTemplate.exchange(
						Constants.url + "getSaleReportBillwiseByFrAllFr", HttpMethod.POST, new HttpEntity<>(map),
						typeRef);

				saleList = responseEntity.getBody();
				saleListForPdf = new ArrayList<>();
				String name = "Sachin";

				HttpSession session = request.getSession();

				System.out.println("session Id  In Ajax Call " + session.getId());
				session.setAttribute("pdfData", saleList);

				session.setAttribute("name", name);
				saleListForPdf = saleList;

				System.out.println("sales List Bill Wise " + saleList.toString());

			} else {

				System.out.println("Inside else Few fr Selected mgg" + selectedFr + "ui");

				map.add("frIdList", selectedFr);
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				ParameterizedTypeReference<List<SalesReportBillwise>> typeRef = new ParameterizedTypeReference<List<SalesReportBillwise>>() {
				};
				ResponseEntity<List<SalesReportBillwise>> responseEntity = restTemplate.exchange(
						Constants.url + "getSaleReportBillwiseByFr", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				saleList = responseEntity.getBody();
				saleListForPdf = new ArrayList<>();
				String name = "Sachin";

				HttpSession session = request.getSession();

				System.out.println("session Id  In Ajax Call " + session.getId());
				session.setAttribute("pdfData", saleList);

				session.setAttribute("name", name);
				saleListForPdf = saleList;

				System.out.println("sales List Bill Wise " + saleList.toString());

			}

			model.addObject("fromDate", fromDate);

			model.addObject("toDate", toDate);

			model.addObject("report", saleListForPdf);
		} catch (Exception e) {
			System.out.println("get sale Report Bill Wise1 " + e.getMessage());
			e.printStackTrace();

		}

		return model;
	}

	@RequestMapping(value = "/showSaleReportBySubCatAndItem", method = RequestMethod.GET)
	public ModelAndView showSaleReportBySubCatAndItem(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		HttpSession session = request.getSession();

		/*
		 * List<ModuleJson> newModuleList = (List<ModuleJson>)
		 * session.getAttribute("newModuleList"); Info view =
		 * AccessControll.checkAccess("showSaleReportBySubCategory",
		 * "showSaleReportBySubCategory", "1", "0", "0", "0", newModuleList);
		 * 
		 * if (view.getError() == true) {
		 * 
		 * model = new ModelAndView("accessDenied");
		 * 
		 * } else {
		 */
		model = new ModelAndView("reports/sales/saleRepBySubCatItem");

		try {
			ZoneId z = ZoneId.of("Asia/Calcutta");

			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			todaysDate = date.format(formatters);

			RestTemplate restTemplate = new RestTemplate();

			allFrIdNameList = new AllFrIdNameList();
			try {

				allFrIdNameList = restTemplate.getForObject(Constants.url + "getAllFranchises", AllFrIdNameList.class);

			} catch (Exception e) {
				System.out.println("Exception in getAllFrIdName" + e.getMessage());
				e.printStackTrace();

			}
			model.addObject("todaysDate", todaysDate);
			model.addObject("unSelectedFrList", allFrIdNameList.getFrIdNamesList());

			CategoryListResponse categoryListResponse;

			categoryListResponse = restTemplate.getForObject(Constants.url + "showAllCategory",
					CategoryListResponse.class);

			List<MCategoryList> mCategoryList = categoryListResponse.getmCategoryList();

			model.addObject("mCategoryList", mCategoryList);
		} catch (Exception e) {

			System.out.println("Exc in show sales report bill wise  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/getFrListofAllFrForFrSummery", method = RequestMethod.GET)
	@ResponseBody
	public List<AllFrIdName> getFrListofAllFrForFrSummery(HttpServletRequest request, HttpServletResponse response) {

		return allFrIdNameList.getFrIdNamesList();
	}

	@RequestMapping(value = "/getSaleReportBySubCatAndFrItem", method = RequestMethod.GET)
	public @ResponseBody SubCatFrRepItemList getSaleReportBySubCatAndFrItem(HttpServletRequest request,
			HttpServletResponse response) {

		SubCatFrRepItemList subCatFrReportListData = new SubCatFrRepItemList();

		List<SubCatItemReport> subCatFrReportList = new ArrayList<>();
		List<AllFrIdName> frListFinal = new ArrayList<>();
		List<SubCategory> subCatList = new ArrayList<>();

		List<Item> itemListFinal = new ArrayList<>();
		List<SubCategory> subCatListFinal = new ArrayList<>();

		// subCatIdListFinal

		String fromDate = "";
		String toDate = "";
		try {
			System.out.println("Inside get Sale Bill Wise");
			String selectedFr = request.getParameter("fr_id_list");
			String selectedSubCatIdList = request.getParameter("subCat_id_list");
			fromDate = request.getParameter("fromDate");
			toDate = request.getParameter("toDate");

			System.out.println("selectedFrBefore------------------" + selectedFr);

			selectedFr = selectedFr.substring(1, selectedFr.length() - 1);
			selectedFr = selectedFr.replaceAll("\"", "");

			selectedSubCatIdList = selectedSubCatIdList.substring(1, selectedSubCatIdList.length() - 1);
			selectedSubCatIdList = selectedSubCatIdList.replaceAll("\"", "");

			System.out.println("selectedFrAfter------------------" + selectedFr);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			System.out.println("Inside If all fr Selected ");

			map.add("fromDate", fromDate);
			map.add("toDate", toDate);
			map.add("frIdList", selectedFr);
			map.add("subCatIdList", selectedSubCatIdList);

			ParameterizedTypeReference<List<SubCatItemReport>> typeRef = new ParameterizedTypeReference<List<SubCatItemReport>>() {
			};
			ResponseEntity<List<SubCatItemReport>> responseEntity = restTemplate.exchange(
					Constants.url + "getSubCatFrItemReportApi", HttpMethod.POST, new HttpEntity<>(map), typeRef);

			subCatFrReportList = responseEntity.getBody();

			for (int i = 0; i < subCatFrReportList.size(); i++) {

				float netQty = subCatFrReportList.get(i).getSoldQty()
						- (subCatFrReportList.get(i).getVarQty() + subCatFrReportList.get(i).getRetQty());
				float netAmt = subCatFrReportList.get(i).getSoldAmt()
						- (subCatFrReportList.get(i).getVarAmt() + subCatFrReportList.get(i).getRetAmt());
				float retAmtPer = (((subCatFrReportList.get(i).getVarAmt() + subCatFrReportList.get(i).getRetAmt())
						* 100) / subCatFrReportList.get(i).getSoldAmt());

				subCatFrReportList.get(i).setNetQty(netQty);
				subCatFrReportList.get(i).setNetAmt(netAmt);
				subCatFrReportList.get(i).setRetAmtPer(retAmtPer);
			}

			AllItemsListResponse allItemsListResponse = restTemplate.getForObject(Constants.url + "getAllItems",
					AllItemsListResponse.class);

			List<Item> itemsList = allItemsListResponse.getItems();

			SubCategory[] subCatListArray = restTemplate.getForObject(Constants.url + "getSubCateList",
					SubCategory[].class);

			subCatList = new ArrayList<SubCategory>(Arrays.asList(subCatListArray));

			allFrIdNameList = restTemplate.getForObject(Constants.url + "getAllFrIdName", AllFrIdNameList.class);

			TreeSet<Integer> frIdListFinal = new TreeSet<Integer>();
			for (int j = 0; j < subCatFrReportList.size(); j++) {
				frIdListFinal.add(subCatFrReportList.get(j).getFrId());
			}
			for (int frId : frIdListFinal) {
				for (int j = 0; j < allFrIdNameList.getFrIdNamesList().size(); j++) {
					if (allFrIdNameList.getFrIdNamesList().get(j).getFrId() == frId) {
						frListFinal.add(allFrIdNameList.getFrIdNamesList().get(j));

					}
				}
			}

			TreeSet<Integer> itemIdListFinal = new TreeSet<Integer>();
			for (int j = 0; j < subCatFrReportList.size(); j++) {
				itemIdListFinal.add(subCatFrReportList.get(j).getItemId());
			}
			for (int itemId : itemIdListFinal) {
				for (int j = 0; j < itemsList.size(); j++) {
					if (itemsList.get(j).getId() == itemId) {

						itemListFinal.add(itemsList.get(j));

					}
				}
			}

			TreeSet<Integer> subIdListFinal = new TreeSet<Integer>();
			for (int j = 0; j < subCatFrReportList.size(); j++) {
				subIdListFinal.add(subCatFrReportList.get(j).getSubCatId());
			}
			for (int subCatId : subIdListFinal) {
				for (int j = 0; j < subCatList.size(); j++) {
					if (subCatList.get(j).getSubCatId() == subCatId) {
						subCatListFinal.add(subCatList.get(j));

					}
				}
			}

			subCatFrReportListData.setSubCatList(subCatListFinal);

			subCatFrReportListData.setFrList(frListFinal);
			subCatFrReportListData.setSubCatItemReport(subCatFrReportList);
			subCatFrReportListData.setItemList(itemListFinal);

			System.out.println("subCatFrReportList*********************************************"
					+ subCatFrReportListData.toString());

		} catch (Exception e) {
			System.out.println("get sale Report Bill Wise " + e.getMessage());
			e.printStackTrace();

		}

		/*
		 * List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();
		 * 
		 * ExportToExcel expoExcel = new ExportToExcel(); List<String> rowData = new
		 * ArrayList<String>();
		 * 
		 * rowData.add("Sr"); rowData.add("Item Name"); rowData.add("Sold Qty");
		 * rowData.add("Sold Amt"); rowData.add("Var Qty"); rowData.add("Var Amt");
		 * rowData.add("Ret Qty"); rowData.add("Ret Amt"); rowData.add("Net Qty");
		 * rowData.add("Net Amt"); rowData.add("Ret Per Amt");
		 * 
		 * expoExcel.setRowData(rowData); int srno = 1;
		 * exportToExcelList.add(expoExcel);
		 * 
		 * for (int j = 0; j < frListFinal.size(); j++) {
		 * 
		 * float totalSoldQty = 0; float totalSoldAmt = 0; float totalVarQty = 0; float
		 * totalVarAmt = 0; float totalRetQty = 0; float totalRetAmt = 0; float
		 * totalNetQty = 0; float totalNetAmt = 0; float retAmtPer = 0;
		 * 
		 * expoExcel = new ExportToExcel(); rowData = new ArrayList<String>();
		 * 
		 * rowData.add(""); rowData.add("" + frListFinal.get(j).getFrName());
		 * rowData.add(""); rowData.add(""); rowData.add(""); rowData.add("");
		 * rowData.add(""); rowData.add(""); rowData.add(""); rowData.add("");
		 * rowData.add("");
		 * 
		 * expoExcel.setRowData(rowData); exportToExcelList.add(expoExcel);
		 * 
		 * for (int k = 0; k < subCatListFinal.size(); k++) {
		 * 
		 * float SoldQty = 0; float SoldAmt = 0; float VarQty = 0; float VarAmt = 0;
		 * float RetQty = 0; float RetAmt = 0; float NetQty = 0; float NetAmt = 0; float
		 * AmtPer = 0;
		 * 
		 * expoExcel = new ExportToExcel(); rowData = new ArrayList<String>();
		 * 
		 * rowData.add(""); rowData.add("" + subCatListFinal.get(k).getSubCatName());
		 * rowData.add(""); rowData.add(""); rowData.add(""); rowData.add("");
		 * rowData.add(""); rowData.add(""); rowData.add(""); rowData.add("");
		 * rowData.add("");
		 * 
		 * expoExcel.setRowData(rowData); exportToExcelList.add(expoExcel);
		 * 
		 * for (int i = 0; i < subCatFrReportList.size(); i++) {
		 * 
		 * if (subCatListFinal.get(k).getSubCatId() ==
		 * subCatFrReportList.get(i).getSubCatId()) {
		 * 
		 * if (frListFinal.get(j).getFrId() == subCatFrReportList.get(i).getFrId()) {
		 * 
		 * expoExcel = new ExportToExcel(); rowData = new ArrayList<String>();
		 * 
		 * SoldQty = SoldQty + subCatFrReportList.get(i).getSoldQty(); SoldAmt = SoldAmt
		 * + subCatFrReportList.get(i).getSoldAmt(); VarQty = VarQty +
		 * subCatFrReportList.get(i).getVarQty(); VarAmt = VarAmt +
		 * subCatFrReportList.get(i).getVarAmt(); RetQty = RetQty +
		 * subCatFrReportList.get(i).getRetQty(); RetAmt = RetAmt +
		 * subCatFrReportList.get(i).getRetAmt(); NetQty = NetQty +
		 * subCatFrReportList.get(i).getNetQty(); NetAmt = NetAmt +
		 * subCatFrReportList.get(i).getNetAmt(); AmtPer = AmtPer +
		 * subCatFrReportList.get(i).getRetAmtPer();
		 * 
		 * totalSoldQty = totalSoldQty + subCatFrReportList.get(i).getSoldQty();
		 * totalSoldAmt = totalSoldAmt + subCatFrReportList.get(i).getSoldAmt();
		 * totalVarQty = totalVarQty + subCatFrReportList.get(i).getVarQty();
		 * totalVarAmt = totalVarAmt + subCatFrReportList.get(i).getVarAmt();
		 * totalRetQty = totalRetQty + subCatFrReportList.get(i).getRetQty();
		 * totalRetAmt = totalRetAmt + subCatFrReportList.get(i).getRetAmt();
		 * totalNetQty = totalNetQty + subCatFrReportList.get(i).getNetQty();
		 * totalNetAmt = totalNetAmt + subCatFrReportList.get(i).getNetAmt(); retAmtPer
		 * = retAmtPer + subCatFrReportList.get(i).getRetAmtPer();
		 * 
		 * rowData.add("" + srno); rowData.add(subCatFrReportList.get(i).getItemName());
		 * 
		 * rowData.add("" + roundUp(subCatFrReportList.get(i).getSoldQty()));
		 * rowData.add("" + roundUp(subCatFrReportList.get(i).getSoldAmt()));
		 * rowData.add("" + roundUp(subCatFrReportList.get(i).getVarQty()));
		 * rowData.add("" + roundUp(subCatFrReportList.get(i).getVarAmt()));
		 * rowData.add("" + roundUp(subCatFrReportList.get(i).getRetQty()));
		 * 
		 * rowData.add("" + roundUp(subCatFrReportList.get(i).getRetAmt()));
		 * rowData.add("" + roundUp(subCatFrReportList.get(i).getNetQty()));
		 * rowData.add("" + roundUp(subCatFrReportList.get(i).getNetAmt()));
		 * rowData.add("" + roundUp(subCatFrReportList.get(i).getRetAmtPer()));
		 * 
		 * srno = srno + 1;
		 * 
		 * expoExcel.setRowData(rowData); exportToExcelList.add(expoExcel); } } }
		 * 
		 * expoExcel = new ExportToExcel(); rowData = new ArrayList<String>();
		 * rowData.add(" "); rowData.add("Total");
		 * 
		 * rowData.add("" + roundUp(SoldQty)); rowData.add("" + roundUp(SoldAmt));
		 * rowData.add("" + roundUp(VarQty)); rowData.add("" + roundUp(VarAmt));
		 * rowData.add("" + roundUp(RetQty)); rowData.add("" + roundUp(RetAmt));
		 * 
		 * rowData.add("" + roundUp(NetQty)); rowData.add("" + roundUp(NetAmt));
		 * rowData.add("" + roundUp(AmtPer));
		 * 
		 * expoExcel.setRowData(rowData); exportToExcelList.add(expoExcel); } expoExcel
		 * = new ExportToExcel(); rowData = new ArrayList<String>(); rowData.add(" ");
		 * rowData.add("Total");
		 * 
		 * rowData.add("" + roundUp(totalSoldQty)); rowData.add("" +
		 * roundUp(totalSoldAmt)); rowData.add("" + roundUp(totalVarQty));
		 * rowData.add("" + roundUp(totalVarAmt)); rowData.add("" +
		 * roundUp(totalRetQty)); rowData.add("" + roundUp(totalRetAmt));
		 * 
		 * rowData.add("" + roundUp(totalNetQty)); rowData.add("" +
		 * roundUp(totalNetAmt)); rowData.add("" + roundUp(retAmtPer));
		 * 
		 * expoExcel.setRowData(rowData); exportToExcelList.add(expoExcel); }
		 * 
		 * HttpSession session = request.getSession();
		 * session.setAttribute("exportExcelList", exportToExcelList);
		 * session.setAttribute("excelName", "SaleBillWiseDate");
		 */

		return subCatFrReportListData;
	}

	@RequestMapping(value = "pdf/showSummeryFrAndSubCatItemPdf/{fromDate}/{toDate}/{selectedFr}/{selectedSubCatIdList} ", method = RequestMethod.GET)
	public ModelAndView showSummeryFrAndSubCatItemPdf(@PathVariable String fromDate, @PathVariable String toDate,
			@PathVariable String selectedFr, @PathVariable String selectedSubCatIdList, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView model = new ModelAndView("reports/sales/pdf/saleRepBySubCatItemPdf");

		SubCatFrRepItemList subCatFrReportListData = new SubCatFrRepItemList();

		List<SubCatItemReport> subCatFrReportList = new ArrayList<>();
		List<AllFrIdName> frListFinal = new ArrayList<>();
		List<SubCategory> subCatList = new ArrayList<>();

		List<Item> itemListFinal = new ArrayList<>();
		List<SubCategory> subCatListFinal = new ArrayList<>();

		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			System.out.println("Inside If all fr Selected ");

			map.add("fromDate", fromDate);
			map.add("toDate", toDate);
			map.add("frIdList", selectedFr);
			map.add("subCatIdList", selectedSubCatIdList);

			ParameterizedTypeReference<List<SubCatItemReport>> typeRef = new ParameterizedTypeReference<List<SubCatItemReport>>() {
			};
			ResponseEntity<List<SubCatItemReport>> responseEntity = restTemplate.exchange(
					Constants.url + "getSubCatFrItemReportApi", HttpMethod.POST, new HttpEntity<>(map), typeRef);

			subCatFrReportList = responseEntity.getBody();

			for (int i = 0; i < subCatFrReportList.size(); i++) {

				float netQty = subCatFrReportList.get(i).getSoldQty()
						- (subCatFrReportList.get(i).getVarQty() + subCatFrReportList.get(i).getRetQty());
				float netAmt = subCatFrReportList.get(i).getSoldAmt()
						- (subCatFrReportList.get(i).getVarAmt() + subCatFrReportList.get(i).getRetAmt());
				float retAmtPer = (((subCatFrReportList.get(i).getVarAmt() + subCatFrReportList.get(i).getRetAmt())
						* 100) / subCatFrReportList.get(i).getSoldAmt());

				subCatFrReportList.get(i).setNetQty(netQty);
				subCatFrReportList.get(i).setNetAmt(netAmt);
				subCatFrReportList.get(i).setRetAmtPer(retAmtPer);
			}

			AllItemsListResponse allItemsListResponse = restTemplate.getForObject(Constants.url + "getAllItems",
					AllItemsListResponse.class);

			List<Item> itemsList = allItemsListResponse.getItems();

			SubCategory[] subCatListArray = restTemplate.getForObject(Constants.url + "getSubCateList",
					SubCategory[].class);

			subCatList = new ArrayList<SubCategory>(Arrays.asList(subCatListArray));

			allFrIdNameList = restTemplate.getForObject(Constants.url + "getAllFrIdName", AllFrIdNameList.class);

			TreeSet<Integer> frIdListFinal = new TreeSet<Integer>();
			for (int j = 0; j < subCatFrReportList.size(); j++) {
				frIdListFinal.add(subCatFrReportList.get(j).getFrId());
			}
			for (int frId : frIdListFinal) {
				for (int j = 0; j < allFrIdNameList.getFrIdNamesList().size(); j++) {
					if (allFrIdNameList.getFrIdNamesList().get(j).getFrId() == frId) {
						frListFinal.add(allFrIdNameList.getFrIdNamesList().get(j));

					}
				}
			}

			TreeSet<Integer> itemIdListFinal = new TreeSet<Integer>();
			for (int j = 0; j < subCatFrReportList.size(); j++) {
				itemIdListFinal.add(subCatFrReportList.get(j).getItemId());
			}
			for (int itemId : itemIdListFinal) {
				for (int j = 0; j < itemsList.size(); j++) {
					if (itemsList.get(j).getId() == itemId) {

						itemListFinal.add(itemsList.get(j));

					}
				}
			}

			TreeSet<Integer> subIdListFinal = new TreeSet<Integer>();
			for (int j = 0; j < subCatFrReportList.size(); j++) {
				subIdListFinal.add(subCatFrReportList.get(j).getSubCatId());
			}
			for (int subCatId : subIdListFinal) {
				for (int j = 0; j < subCatList.size(); j++) {
					if (subCatList.get(j).getSubCatId() == subCatId) {
						subCatListFinal.add(subCatList.get(j));

					}
				}
			}

			subCatFrReportListData.setSubCatList(subCatListFinal);

			subCatFrReportListData.setFrList(frListFinal);
			subCatFrReportListData.setSubCatItemReport(subCatFrReportList);
			subCatFrReportListData.setItemList(itemListFinal);

			model.addObject("subCatFrReportListData", subCatFrReportListData);
			model.addObject("frList", frListFinal);
			model.addObject("subCatList", subCatListFinal);
			model.addObject("subCatFrReportList", subCatFrReportList);
			model.addObject("FACTORYNAME", "Galdhar Foods");
			model.addObject("FACTORYADDRESS", "Plot No.48,Chikalthana Midc, Aurangabad");
			model.addObject("fromDate", fromDate);
			model.addObject("toDate", toDate);

		} catch (Exception e) {
			System.out.println("get sale Report Bill Wise " + e.getMessage());
			e.printStackTrace();

		}

		return model;
	}

	@RequestMapping(value = "/getSubCatByCatIdForReport", method = RequestMethod.GET)
	public @ResponseBody List<SubCategory> getSubCatByCatIdForReport(HttpServletRequest request,
			HttpServletResponse response) {

		List<SubCategory> subCatList = new ArrayList<SubCategory>();
		try {
			RestTemplate restTemplate = new RestTemplate();
			String selectedCat = request.getParameter("catId");
			boolean isAllCatSelected = false;

			System.out.println(
					"System.out.println(selectedCat);System.out.println(selectedCat);System.out.println(selectedCat);"
							+ selectedCat);

			if (selectedCat.contains("-1")) {
				isAllCatSelected = true;
			} else {
				selectedCat = selectedCat.substring(1, selectedCat.length() - 1);
				selectedCat = selectedCat.replaceAll("\"", "");
			}

			System.out.println(selectedCat);
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("catId", selectedCat);
			map.add("isAllCatSelected", isAllCatSelected);

			subCatList = restTemplate.postForObject(Constants.url + "getSubCatListByCatIdInForDisp", map, List.class);
			System.out.println(subCatList.toString());

		} catch (Exception e) {

		}

		return subCatList;
	}

	// report 3
	@RequestMapping(value = "/showSaleReportGrpByDate", method = RequestMethod.GET)
	public ModelAndView showSaleReportGrpByDate(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/billwisesalesgrpbydate");
		System.out.println("inside showSaleReportGrpByDate ");
		// Constants.mainAct =2;
		// Constants.subAct =20;

		try {
			ZoneId z = ZoneId.of("Asia/Calcutta");

			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			String todaysDate = date.format(formatters);

			RestTemplate restTemplate = new RestTemplate();

			// get Routes

			AllRoutesListResponse allRouteListResponse = restTemplate.getForObject(Constants.url + "showRouteList",
					AllRoutesListResponse.class);

			List<Route> routeList = new ArrayList<Route>();

			routeList = allRouteListResponse.getRoute();

			// end get Routes

			allFrIdNameList = new AllFrIdNameList();
			try {

				allFrIdNameList = restTemplate.getForObject(Constants.url + "getAllFranchises", AllFrIdNameList.class);

			} catch (Exception e) {
				System.out.println("Exception in getAllFrIdName" + e.getMessage());
				e.printStackTrace();

			}
			List<AllFrIdName> selectedFrListAll = new ArrayList();
			List<Menu> selectedMenuList = new ArrayList<Menu>();

			System.out.println(" Fr " + allFrIdNameList.getFrIdNamesList());

			model.addObject("todaysDate", todaysDate);
			model.addObject("unSelectedFrList", allFrIdNameList.getFrIdNamesList());

			model.addObject("routeList", routeList);

		} catch (Exception e) {

			System.out.println("Exc in show sales report bill wise  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/getSaleBillwiseGrpByDate", method = RequestMethod.GET)
	public @ResponseBody List<SalesReportBillwise> getSaleBillwiseGrpByDate(HttpServletRequest request,
			HttpServletResponse response) {

		List<SalesReportBillwise> saleList = new ArrayList<>();
		String fromDate = "";
		String toDate = "";
		try {
			System.out.println("Inside get Sale Bill Wise");
			String selectedFr = request.getParameter("fr_id_list");
			fromDate = request.getParameter("fromDate");
			toDate = request.getParameter("toDate");
			String routeId = request.getParameter("route_id");

			boolean isAllFrSelected = false;
			selectedFr = selectedFr.substring(1, selectedFr.length() - 1);
			selectedFr = selectedFr.replaceAll("\"", "");

			frList = new ArrayList<>();
			frList = Arrays.asList(selectedFr);
			
			if (!routeId.equalsIgnoreCase("0")) {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				RestTemplate restTemplate = new RestTemplate();

				map.add("routeId", routeId);

				FrNameIdByRouteIdResponse frNameId = restTemplate.postForObject(Constants.url + "getFrNameIdByRouteId",
						map, FrNameIdByRouteIdResponse.class);

				List<FrNameIdByRouteId> frNameIdByRouteIdList = frNameId.getFrNameIdByRouteIds();

				System.out.println("route wise franchisee " + frNameIdByRouteIdList.toString());

				StringBuilder sbForRouteFrId = new StringBuilder();
				for (int i = 0; i < frNameIdByRouteIdList.size(); i++) {

					sbForRouteFrId = sbForRouteFrId.append(frNameIdByRouteIdList.get(i).getFrId().toString() + ",");

				}

				String strFrIdRouteWise = sbForRouteFrId.toString();
				selectedFr = strFrIdRouteWise.substring(0, strFrIdRouteWise.length() - 1);
				System.out.println("fr Id Route WISE = " + selectedFr);

			} // end of if

			if (frList.contains("-1")) {
				isAllFrSelected = true;
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			float tcsPer = getTcsPer();
			
			if (isAllFrSelected) {

				System.out.println("Inside If all fr Selected ");

				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				ParameterizedTypeReference<List<SalesReportBillwise>> typeRef = new ParameterizedTypeReference<List<SalesReportBillwise>>() {
				};
				ResponseEntity<List<SalesReportBillwise>> responseEntity = restTemplate.exchange(
						Constants.url + "getSaleReportBillwiseByDateAllFr", HttpMethod.POST, new HttpEntity<>(map),
						typeRef);

				saleList = responseEntity.getBody();
				
				for (int i = 0; i < saleList.size(); i++) {	
					saleList.get(i).setTcsAmt(saleList.get(i).getRoundOff());
				}
				
				saleListForPdf = new ArrayList<>();

				saleListForPdf = saleList;

				System.out.println("sales List Bill Wise " + saleList.toString());

			} else {
				System.out.println("Inside else Few fr Selected ");

				map.add("frIdList", selectedFr);
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				ParameterizedTypeReference<List<SalesReportBillwise>> typeRef = new ParameterizedTypeReference<List<SalesReportBillwise>>() {
				};
				ResponseEntity<List<SalesReportBillwise>> responseEntity = restTemplate.exchange(
						Constants.url + "getSaleReportBillwiseByDate", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				saleList = responseEntity.getBody();
				
				for (int i = 0; i < saleList.size(); i++) {					
					saleList.get(i).setTcsAmt(saleList.get(i).getRoundOff());
				}
				
				saleListForPdf = new ArrayList<>();

				saleListForPdf = saleList;

				System.out.println("sales List Bill Wise " + saleList.toString());

			}
		} catch (Exception e) {
			System.out.println("get sale Report Bill Wise " + e.getMessage());
			e.printStackTrace();

		}

		// exportToExcel
		List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

		ExportToExcel expoExcel = new ExportToExcel();
		List<String> rowData = new ArrayList<String>();

		rowData.add("Date");
		rowData.add("Taxable Amount");
		rowData.add("CGST");
		rowData.add("SGST");
		rowData.add("IGST");
		rowData.add("TCS");
		rowData.add("Total");

//		rowData.add("Bill No");
//		rowData.add("Invoice No");
//		rowData.add("Bill Date");
//		rowData.add("Franchisee Code");
//		rowData.add("Franchisee Name");
//		rowData.add("Franchisee City");
//		rowData.add("Franchisee Gst No");
//		rowData.add("sgst sum");
//		rowData.add("cgst sum");
//		rowData.add("igst sum");
//		rowData.add("Total Tax");
//		
//		rowData.add("Taxable Amt");
//		rowData.add("Grand Total");

		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);

		float taxableTot = 0, cgstTot = 0, sgstTot = 0, igstTot = 0, tot = 0, ttlTcs = 0;

		for (int i = 0; i < saleList.size(); i++) {
			expoExcel = new ExportToExcel();
			rowData = new ArrayList<String>();

//			rowData.add("" + saleList.get(i).getBillNo());
//			rowData.add(saleList.get(i).getInvoiceNo());
//			rowData.add(saleList.get(i).getBillDate());
//
//			rowData.add("" + saleList.get(i).getFrId());
//			rowData.add(saleList.get(i).getFrName());
//
//			rowData.add(saleList.get(i).getFrCity());
//			rowData.add(saleList.get(i).getFrGstNo());
//			rowData.add("" + saleList.get(i).getSgstSum());
//			rowData.add("" + saleList.get(i).getCgstSum());
//			rowData.add("" + saleList.get(i).getIgstSum());
//			rowData.add("" + saleList.get(i).getTotalTax());
//			rowData.add("" + saleList.get(i).getTaxableAmt());
//			rowData.add("" + saleList.get(i).getGrandTotal());

			rowData.add("" + saleList.get(i).getBillDate());
			rowData.add("" + saleList.get(i).getTaxableAmt());
			rowData.add("" + saleList.get(i).getCgstSum());
			rowData.add("" + saleList.get(i).getSgstSum());
			rowData.add("" + saleList.get(i).getIgstSum());
			rowData.add("" + saleList.get(i).getTcsAmt());

			float grandTot = saleList.get(i).getTaxableAmt() + saleList.get(i).getCgstSum()
					+ saleList.get(i).getSgstSum() + saleList.get(i).getIgstSum() + saleList.get(i).getTcsAmt();

			rowData.add("" + grandTot);

			taxableTot = taxableTot + saleList.get(i).getTaxableAmt();
			cgstTot = cgstTot + saleList.get(i).getCgstSum();
			sgstTot = sgstTot + saleList.get(i).getSgstSum();
			igstTot = igstTot + saleList.get(i).getIgstSum();
			ttlTcs = ttlTcs + saleList.get(i).getTcsAmt();
			tot = tot + grandTot;

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);

		}

		expoExcel = new ExportToExcel();
		rowData = new ArrayList<String>();

		rowData.add("Total");
		rowData.add("" + taxableTot);
		rowData.add("" + cgstTot);
		rowData.add("" + sgstTot);
		rowData.add("" + igstTot);
		rowData.add("" + ttlTcs);
		rowData.add("" + tot);

		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);

		HttpSession session = request.getSession();
		session.setAttribute("exportExcelListNew", exportToExcelList);
		session.setAttribute("excelNameNew", "SalesReportGroupByDate");
		session.setAttribute("reportNameNew", "Sales Report Group By Date");
		session.setAttribute("searchByNew", "From Date: " + fromDate + "  To Date: " + toDate + " ");
		session.setAttribute("mergeUpto1", "$A$1:$F$1");
		session.setAttribute("mergeUpto2", "$A$2:$F$2");

		return saleList;

	}

	@RequestMapping(value = "pdf/showSaleBillwiseGrpByDatePdf/{fromDate}/{toDate}/{selectedFr}/{routeId}", method = RequestMethod.GET)
	public ModelAndView showSaleBillwiseGrpByDate(@PathVariable String fromDate, @PathVariable String toDate,
			@PathVariable String selectedFr, @PathVariable String routeId, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/pdf/billwisesalesgrpbydatePdf");
		List<SalesReportBillwise> saleList = new ArrayList<>();
		boolean isAllFrSelected = false;

		try {
			System.out.println("Inside get Sale Bill Wise");

			frList = new ArrayList<>();
			frList = Arrays.asList(selectedFr);

			if (!routeId.equalsIgnoreCase("0")) {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				RestTemplate restTemplate = new RestTemplate();

				map.add("routeId", routeId);

				FrNameIdByRouteIdResponse frNameId = restTemplate.postForObject(Constants.url + "getFrNameIdByRouteId",
						map, FrNameIdByRouteIdResponse.class);

				List<FrNameIdByRouteId> frNameIdByRouteIdList = frNameId.getFrNameIdByRouteIds();

				System.out.println("route wise franchisee " + frNameIdByRouteIdList.toString());

				StringBuilder sbForRouteFrId = new StringBuilder();
				for (int i = 0; i < frNameIdByRouteIdList.size(); i++) {

					sbForRouteFrId = sbForRouteFrId.append(frNameIdByRouteIdList.get(i).getFrId().toString() + ",");

				}

				String strFrIdRouteWise = sbForRouteFrId.toString();
				selectedFr = strFrIdRouteWise.substring(0, strFrIdRouteWise.length() - 1);
				System.out.println("fr Id Route WISE = " + selectedFr);

			} // end of if

			if (selectedFr.equalsIgnoreCase("-1")) {
				isAllFrSelected = true;
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();
			float tcsPer = getTcsPer();
			if (isAllFrSelected) {

				System.out.println("Inside If all fr Selected ");

				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				ParameterizedTypeReference<List<SalesReportBillwise>> typeRef = new ParameterizedTypeReference<List<SalesReportBillwise>>() {
				};
				ResponseEntity<List<SalesReportBillwise>> responseEntity = restTemplate.exchange(
						Constants.url + "getSaleReportBillwiseByDateAllFr", HttpMethod.POST, new HttpEntity<>(map),
						typeRef);

				saleList = responseEntity.getBody();
				for (int i = 0; i < saleList.size(); i++) {					
					saleList.get(i).setTcsAmt(saleList.get(i).getRoundOff());
				}
				saleListForPdf = new ArrayList<>();

				saleListForPdf = saleList;

				System.out.println("sales List Bill Wise " + saleList.toString());

			} else {
				System.out.println("Inside else Few fr Selected ");

				map.add("frIdList", selectedFr);
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				ParameterizedTypeReference<List<SalesReportBillwise>> typeRef = new ParameterizedTypeReference<List<SalesReportBillwise>>() {
				};
				ResponseEntity<List<SalesReportBillwise>> responseEntity = restTemplate.exchange(
						Constants.url + "getSaleReportBillwiseByDate", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				saleList = responseEntity.getBody();
				for (int i = 0; i < saleList.size(); i++) {					
					saleList.get(i).setTcsAmt(saleList.get(i).getRoundOff());
				}
				saleListForPdf = new ArrayList<>();

				saleListForPdf = saleList;

				System.out.println("sales List Bill Wise " + saleList.toString());

			}

		} catch (Exception e) {

			System.out.println("Exce in show Sale Bill wise by fr PDF " + e.getMessage());
			e.printStackTrace();

		}
		model.addObject("fromDate", fromDate);

		model.addObject("toDate", toDate);

		model.addObject("report", saleListForPdf);
		return model;
	}

	// getSaleReportBillwiseByMonth

	@RequestMapping(value = "/showSaleReportByMonth", method = RequestMethod.GET)
	public ModelAndView showSaleReportByMonth(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/billwisesalebymonth");

		// Constants.mainAct =2;
		// Constants.subAct =20;

		try {
			ZoneId z = ZoneId.of("Asia/Calcutta");

			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			todaysDate = date.format(formatters);

			RestTemplate restTemplate = new RestTemplate();

			// get Routes

			AllRoutesListResponse allRouteListResponse = restTemplate.getForObject(Constants.url + "showRouteList",
					AllRoutesListResponse.class);

			List<Route> routeList = new ArrayList<Route>();

			routeList = allRouteListResponse.getRoute();

			// end get Routes

			allFrIdNameList = new AllFrIdNameList();
			try {

				allFrIdNameList = restTemplate.getForObject(Constants.url + "getAllFranchises", AllFrIdNameList.class);

			} catch (Exception e) {
				System.out.println("Exception in getAllFrIdName" + e.getMessage());
				e.printStackTrace();

			}
			List<AllFrIdName> selectedFrListAll = new ArrayList();
			List<Menu> selectedMenuList = new ArrayList<Menu>();

			System.out.println(" Fr " + allFrIdNameList.getFrIdNamesList());

			model.addObject("todaysDate", todaysDate);
			model.addObject("unSelectedFrList", allFrIdNameList.getFrIdNamesList());

			model.addObject("routeList", routeList);

		} catch (Exception e) {

			System.out.println("Exc in show sales report bill wise  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/setAllFrIdSelected", method = RequestMethod.GET)
	public @ResponseBody List<AllFrIdName> setAllFrIdSelected() {
		// logger.info("inside ajax call for fr all selected");

		return allFrIdNameList.getFrIdNamesList();
	}

	@RequestMapping(value = "/getSaleBillwiseGrpByMonth", method = RequestMethod.GET)
	public @ResponseBody List<SalesReportBillwise> getSaleBillwiseGrpByMonth(HttpServletRequest request,
			HttpServletResponse response) {

		List<SalesReportBillwise> saleList = new ArrayList<>();
		String fromDate = "";
		String toDate = "";
		try {
			System.out.println("Inside get Sale Bill Wise");
			String selectedFr = request.getParameter("fr_id_list");
			fromDate = request.getParameter("fromDate");
			toDate = request.getParameter("toDate");
			String routeId = request.getParameter("route_id");

			boolean isAllFrSelected = false;
			selectedFr = selectedFr.substring(1, selectedFr.length() - 1);
			selectedFr = selectedFr.replaceAll("\"", "");

			if (!routeId.equalsIgnoreCase("0")) {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				RestTemplate restTemplate = new RestTemplate();

				map.add("routeId", routeId);

				FrNameIdByRouteIdResponse frNameId = restTemplate.postForObject(Constants.url + "getFrNameIdByRouteId",
						map, FrNameIdByRouteIdResponse.class);

				List<FrNameIdByRouteId> frNameIdByRouteIdList = frNameId.getFrNameIdByRouteIds();

				System.out.println("route wise franchisee " + frNameIdByRouteIdList.toString());

				StringBuilder sbForRouteFrId = new StringBuilder();
				for (int i = 0; i < frNameIdByRouteIdList.size(); i++) {

					sbForRouteFrId = sbForRouteFrId.append(frNameIdByRouteIdList.get(i).getFrId().toString() + ",");

				}

				String strFrIdRouteWise = sbForRouteFrId.toString();
				selectedFr = strFrIdRouteWise.substring(0, strFrIdRouteWise.length() - 1);
				System.out.println("fr Id Route WISE = " + selectedFr);

			} // end of if

			if (frList.contains("-1")) {
				isAllFrSelected = true;
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			float tcsPer = getTcsPer();
			
//			if (isAllFrSelected) {
//
//				System.out.println("Inside If all fr Selected ");
//
//				map.add("fromDate", fromDate);
//				map.add("toDate", toDate);
//
//				ParameterizedTypeReference<List<SalesReportBillwise>> typeRef = new ParameterizedTypeReference<List<SalesReportBillwise>>() {
//				};
//				ResponseEntity<List<SalesReportBillwise>> responseEntity = restTemplate.exchange(
//						Constants.url + "getSaleReportBillwiseByMonthAllFr", HttpMethod.POST, new HttpEntity<>(map),
//						typeRef);
//
//				saleList = responseEntity.getBody();
//				saleListForPdf = new ArrayList<>();
//
//				saleListForPdf = saleList;
//
//				System.out.println("sales List Bill Wise " + saleList.toString());
//
//			} else {
			System.out.println("Inside else Few fr Selected ");

			map.add("frIdList", selectedFr);
			map.add("fromDate", fromDate);
			map.add("toDate", toDate);

			ParameterizedTypeReference<List<SalesReportBillwise>> typeRef = new ParameterizedTypeReference<List<SalesReportBillwise>>() {
			};
			ResponseEntity<List<SalesReportBillwise>> responseEntity = restTemplate.exchange(
					Constants.url + "getSaleReportBillwiseByMonth", HttpMethod.POST, new HttpEntity<>(map), typeRef);

			saleList = responseEntity.getBody();
			
			for (int i = 0; i < saleList.size(); i++) {	
				saleList.get(i).setTcsAmt(saleList.get(i).getRoundOff());
			}
			
			saleListForPdf = new ArrayList<>();

			saleListForPdf = saleList;

			System.out.println("sales List Bill Wise " + saleList.toString());

			// }
		} catch (Exception e) {
			System.out.println("get sale Report Bill Wise " + e.getMessage());
			e.printStackTrace();

		}

		// exportToExcel
		List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

		ExportToExcel expoExcel = new ExportToExcel();
		List<String> rowData = new ArrayList<String>();

		rowData.add("Month");
		rowData.add("Basic Value");
		rowData.add("CGST");
		rowData.add("SGST");
		rowData.add("IGST");
		rowData.add("TCS");
		rowData.add("Total");

//		rowData.add("Bill No");
//		rowData.add("Invoice No");
//		rowData.add("Bill Date");
//		rowData.add("Franchisee Code");
//		rowData.add("Franchisee Name");
//		rowData.add("Franchisee City");
//		rowData.add("Franchisee Gst No");
//		rowData.add("sgst sum");
//		rowData.add("cgst sum");
//		rowData.add("igst sum");
//		rowData.add("Total Tax");
//		rowData.add("Taxable Amt");
//		rowData.add("Grand Total");

		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);

		float taxableTotal = 0, cgstTotal = 0, sgstTotal = 0, igstTotal = 0, tot = 0, ttlTcs = 0;

		for (int i = 0; i < saleList.size(); i++) {
			expoExcel = new ExportToExcel();
			rowData = new ArrayList<String>();

//			rowData.add("" + saleList.get(i).getBillNo());
//			rowData.add(saleList.get(i).getInvoiceNo());
//			rowData.add(saleList.get(i).getBillDate());
//
//			rowData.add("" + saleList.get(i).getFrId());
//			rowData.add(saleList.get(i).getFrName());
//
//			rowData.add(saleList.get(i).getFrCity());
//			rowData.add(saleList.get(i).getFrGstNo());
//			rowData.add("" + saleList.get(i).getSgstSum());
//			rowData.add("" + saleList.get(i).getCgstSum());
//			rowData.add("" + saleList.get(i).getIgstSum());
//			rowData.add("" + saleList.get(i).getTotalTax());
//			rowData.add("" + saleList.get(i).getTaxableAmt());
//			rowData.add("" + saleList.get(i).getGrandTotal());

			rowData.add("" + saleList.get(i).getMonth());
			rowData.add("" + saleList.get(i).getTaxableAmt());
			rowData.add("" + saleList.get(i).getCgstSum());
			rowData.add("" + saleList.get(i).getSgstSum());
			rowData.add("" + saleList.get(i).getIgstSum());
			rowData.add("" + saleList.get(i).getTcsAmt());

			taxableTotal = taxableTotal + saleList.get(i).getTaxableAmt();
			cgstTotal = cgstTotal + saleList.get(i).getCgstSum();
			sgstTotal = sgstTotal + saleList.get(i).getSgstSum();
			igstTotal = igstTotal + saleList.get(i).getIgstSum();
			ttlTcs = ttlTcs + saleList.get(i).getTcsAmt();

			float grandTot = saleList.get(i).getTaxableAmt() + saleList.get(i).getCgstSum()
					+ saleList.get(i).getSgstSum() + saleList.get(i).getIgstSum() + saleList.get(i).getTcsAmt();
			rowData.add("" + grandTot);

			tot = tot + grandTot;

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);

		}

		expoExcel = new ExportToExcel();
		rowData = new ArrayList<String>();

		rowData.add("TOTAL");
		rowData.add("" + taxableTotal);
		rowData.add("" + cgstTotal);
		rowData.add("" + sgstTotal);
		rowData.add("" + igstTotal);
		rowData.add("" + ttlTcs);
		rowData.add("" + tot);

		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);

		HttpSession session = request.getSession();
		session.setAttribute("exportExcelListNew", exportToExcelList);
		session.setAttribute("excelNameNew", "SaleBillWiseByMonth");
		session.setAttribute("reportNameNew", "Sales Report Monthwise");
		session.setAttribute("searchByNew", "From Date: " + fromDate + "  To Date: " + toDate + " ");
		session.setAttribute("mergeUpto1", "$A$1:$F$1");
		session.setAttribute("mergeUpto2", "$A$2:$F$2");
		return saleList;

	}

	@RequestMapping(value = "pdf/showSaleBillwiseGrpByMonthPdf/{fromDate}/{toDate}/{selectedFr}/{routeId}", method = RequestMethod.GET)
	public ModelAndView showSaleBillwiseGrpByMonthPdf(@PathVariable String fromDate, @PathVariable String toDate,
			@PathVariable String selectedFr, @PathVariable String routeId, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/pdf/billwisesalesgrpbymonthPdf");
		List<SalesReportBillwise> saleList = new ArrayList<>();
		boolean isAllFrSelected = false;

		try {
			System.out.println("Inside get Sale Bill Wise");

			if (!routeId.equalsIgnoreCase("0")) {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				RestTemplate restTemplate = new RestTemplate();

				map.add("routeId", routeId);

				FrNameIdByRouteIdResponse frNameId = restTemplate.postForObject(Constants.url + "getFrNameIdByRouteId",
						map, FrNameIdByRouteIdResponse.class);

				List<FrNameIdByRouteId> frNameIdByRouteIdList = frNameId.getFrNameIdByRouteIds();

				System.out.println("route wise franchisee " + frNameIdByRouteIdList.toString());

				StringBuilder sbForRouteFrId = new StringBuilder();
				for (int i = 0; i < frNameIdByRouteIdList.size(); i++) {

					sbForRouteFrId = sbForRouteFrId.append(frNameIdByRouteIdList.get(i).getFrId().toString() + ",");

				}

				String strFrIdRouteWise = sbForRouteFrId.toString();
				selectedFr = strFrIdRouteWise.substring(0, strFrIdRouteWise.length() - 1);
				System.out.println("fr Id Route WISE = " + selectedFr);

			} // end of if

			if (selectedFr.equalsIgnoreCase("-1")) {
				isAllFrSelected = true;
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();			

			float tcsPer = getTcsPer();

			if (isAllFrSelected) {

				System.out.println("Inside If all fr Selected ");

				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				ParameterizedTypeReference<List<SalesReportBillwise>> typeRef = new ParameterizedTypeReference<List<SalesReportBillwise>>() {
				};
				ResponseEntity<List<SalesReportBillwise>> responseEntity = restTemplate.exchange(
						Constants.url + "getSaleReportBillwiseByMonthAllFr", HttpMethod.POST, new HttpEntity<>(map),
						typeRef);

				saleList = responseEntity.getBody();
				
				for (int i = 0; i < saleList.size(); i++) {					
					saleList.get(i).setTcsAmt(saleList.get(i).getRoundOff());
				}
				
				saleListForPdf = new ArrayList<>();

				saleListForPdf = saleList;

				System.out.println("sales List Bill Wise " + saleList.toString());

			} else {
				System.out.println("Inside else Few fr Selected ");

				map.add("frIdList", selectedFr);
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				ParameterizedTypeReference<List<SalesReportBillwise>> typeRef = new ParameterizedTypeReference<List<SalesReportBillwise>>() {
				};
				ResponseEntity<List<SalesReportBillwise>> responseEntity = restTemplate.exchange(
						Constants.url + "getSaleReportBillwiseByMonth", HttpMethod.POST, new HttpEntity<>(map),
						typeRef);

				saleList = responseEntity.getBody();
				
				for (int i = 0; i < saleList.size(); i++) {					
					saleList.get(i).setTcsAmt(saleList.get(i).getRoundOff());
				}
				
				saleListForPdf = new ArrayList<>();

				saleListForPdf = saleList;

				System.out.println("sales List Bill Wise " + saleList.toString());

			}

		} catch (Exception e) {
			System.out.println("Exce in show Sale Bill wise by fr PDF " + e.getMessage());
			e.printStackTrace();
		}
		model.addObject("fromDate", fromDate);

		model.addObject("toDate", toDate);

		model.addObject("report", saleListForPdf);
		return model;
	}

	// *******************************************************************//
	// Royalty Sale
	@RequestMapping(value = "/showSaleRoyaltyByCat", method = RequestMethod.GET)
	public ModelAndView showSaleRoyaltyByCat(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/salesroyaltybycat");

		try {
			ZoneId z = ZoneId.of("Asia/Calcutta");
			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			todaysDate = date.format(formatters);
			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			AllRoutesListResponse allRouteListResponse = restTemplate.getForObject(Constants.url + "showRouteList",
					AllRoutesListResponse.class);
			List<Route> routeList = new ArrayList<Route>();
			routeList = allRouteListResponse.getRoute();
			allFrIdNameList = new AllFrIdNameList();
			try {
				allFrIdNameList = restTemplate.getForObject(Constants.url + "getAllFranchises", AllFrIdNameList.class);
			} catch (Exception e) {
				// System.out.println("Exception in getAllFrIdName" + e.getMessage());
				e.printStackTrace();
			}
			List<AllFrIdName> selectedFrListAll = new ArrayList();
			List<Menu> selectedMenuList = new ArrayList<Menu>();
			// System.out.println(" Fr " + allFrIdNameList.getFrIdNamesList());
			model.addObject("todaysDate", todaysDate);
			model.addObject("unSelectedFrList", allFrIdNameList.getFrIdNamesList());
			model.addObject("routeList", routeList);
			model.addObject("royPer", getRoyPer());
		} catch (Exception e) {
			// System.out.println("Exc in show sales report bill wise " + e.getMessage());
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/getSaleRoyaltyByCat", method = RequestMethod.GET)
	public @ResponseBody RoyaltyListBean getSaleRoyaltyByCat(HttpServletRequest request, HttpServletResponse response) {

		List<SalesReportBillwise> saleList = new ArrayList<>();
		List<SalesReportRoyalty> royaltyList = new ArrayList<>();
		RoyaltyListBean royaltyBean = new RoyaltyListBean();
		String fromDate = "";
		String toDate = "";
		try {
			String selectedFr = request.getParameter("fr_id_list");
			fromDate = request.getParameter("fromDate");
			toDate = request.getParameter("toDate");
			String routeId = request.getParameter("route_id");

			boolean isAllFrSelected = false;
			selectedFr = selectedFr.substring(1, selectedFr.length() - 1);
			selectedFr = selectedFr.replaceAll("\"", "");

			frList = new ArrayList<>();
			frList = Arrays.asList(selectedFr);

			if (!routeId.equalsIgnoreCase("0")) {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				RestTemplate restTemplate = new RestTemplate();

				map.add("routeId", routeId);

				FrNameIdByRouteIdResponse frNameId = restTemplate.postForObject(Constants.url + "getFrNameIdByRouteId",
						map, FrNameIdByRouteIdResponse.class);

				List<FrNameIdByRouteId> frNameIdByRouteIdList = frNameId.getFrNameIdByRouteIds();

				System.out.println("route wise franchisee " + frNameIdByRouteIdList.toString());

				StringBuilder sbForRouteFrId = new StringBuilder();
				for (int i = 0; i < frNameIdByRouteIdList.size(); i++) {

					sbForRouteFrId = sbForRouteFrId.append(frNameIdByRouteIdList.get(i).getFrId().toString() + ",");

				}

				String strFrIdRouteWise = sbForRouteFrId.toString();
				selectedFr = strFrIdRouteWise.substring(0, strFrIdRouteWise.length() - 1);
				System.out.println("fr Id Route WISE = " + selectedFr);

			} // end of if

			if (frList.contains("-1")) {
				isAllFrSelected = true;
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			if (isAllFrSelected) {

				System.out.println("Inside If all fr Selected ");

				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				ParameterizedTypeReference<List<SalesReportRoyalty>> typeRef = new ParameterizedTypeReference<List<SalesReportRoyalty>>() {
				};
				ResponseEntity<List<SalesReportRoyalty>> responseEntity = restTemplate.exchange(
						Constants.url + "getSalesReportRoyaltyAllFr", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				royaltyList = responseEntity.getBody();
				royaltyListForPdf = new ArrayList<>();

			} else {
				System.out.println("Inside else Few fr Selected ");

				map.add("frIdList", selectedFr);
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				ParameterizedTypeReference<List<SalesReportRoyalty>> typeRef = new ParameterizedTypeReference<List<SalesReportRoyalty>>() {
				};
				ResponseEntity<List<SalesReportRoyalty>> responseEntity = restTemplate.exchange(
						Constants.url + "getSalesReportRoyalty", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				royaltyList = responseEntity.getBody();
				royaltyListForPdf = new ArrayList<>();
			}

			royaltyListForPdf = royaltyList;

			System.out.println("royaltyList List Bill Wise " + saleList.toString());

			CategoryListResponse categoryListResponse = restTemplate.getForObject(Constants.url + "showAllCategory",
					CategoryListResponse.class);
			List<MCategoryList> categoryList;
			categoryList = categoryListResponse.getmCategoryList();
			// allFrIdNameList = new AllFrIdNameList();
			System.out.println("Category list  " + categoryList);

			royaltyBean.setCategoryList(categoryList);
			royaltyBean.setSalesReportRoyalty(royaltyList);
			staticRoyaltyBean = royaltyBean;

		} catch (Exception e) {
			System.out.println("get sale Report royaltyList by cat " + e.getMessage());
			e.printStackTrace();

		}
		float royPer = getRoyPer();

		// exportToExcel
		List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

		ExportToExcel expoExcel = new ExportToExcel();
		List<String> rowData = new ArrayList<String>();

		rowData.add("Sr.No.");
		rowData.add("Category Name");
		rowData.add("Item Name");
		rowData.add("Sale Qty");
		rowData.add("Sale Value");

		rowData.add("GRN Qty");
		rowData.add("GRN Value");
		rowData.add("GVN Qty");
		rowData.add("GVN Value");

		rowData.add("Net Qty");
		rowData.add("Net Value");
		rowData.add("Royalty %");
		rowData.add("Royalty Amt");
		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);
		if (!royaltyBean.getSalesReportRoyalty().isEmpty()) {
			royaltyList.sort((SalesReportRoyalty s1, SalesReportRoyalty s2) -> s1.getCatId() - s2.getCatId());

			for (int i = 0; i < royaltyList.size(); i++) {
				int index = 1;
				index = index + i;
				expoExcel = new ExportToExcel();
				rowData = new ArrayList<String>();

				rowData.add("" + index);
				rowData.add("" + royaltyList.get(i).getCat_name());

				rowData.add("" + royaltyList.get(i).getItem_name());

				rowData.add("" + roundUp(royaltyList.get(i).gettBillQty()));
				rowData.add("" + roundUp(royaltyList.get(i).gettBillTaxableAmt()));

				rowData.add("" + roundUp(royaltyList.get(i).gettGrnQty()));

				rowData.add("" + roundUp(royaltyList.get(i).gettGrnTaxableAmt()));
				rowData.add("" + roundUp(royaltyList.get(i).gettGvnQty()));
				rowData.add("" + roundUp(royaltyList.get(i).gettGvnTaxableAmt()));

				float netQty = royaltyList.get(i).gettBillQty()
						- (royaltyList.get(i).gettGrnQty() + royaltyList.get(i).gettGvnQty());

				float netValue = royaltyList.get(i).gettBillTaxableAmt()
						- (royaltyList.get(i).gettGrnTaxableAmt() + royaltyList.get(i).gettGvnTaxableAmt());

				float rAmt = netValue * royPer / 100;

				rowData.add("" + roundUp(netQty));
				rowData.add("" + roundUp(netValue));
				rowData.add("" + roundUp(royPer));
				rowData.add("" + roundUp(rAmt));

				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);

			}
		}

		HttpSession session = request.getSession();
		session.setAttribute("exportExcelListNew", exportToExcelList);
		session.setAttribute("excelNameNew", "RoyaltyByCatList");
		session.setAttribute("reportNameNew", "Royalty Report By Category");
		session.setAttribute("searchByNew", "From Date: " + fromDate + "  To Date: " + toDate + " ");
		session.setAttribute("mergeUpto1", "$A$1:$M$1");
		session.setAttribute("mergeUpto2", "$A$2:$M$2");
		return royaltyBean;

	}

	@RequestMapping(value = "pdf/showSaleRoyaltyByCatPdf/{fromDate}/{toDate}/{selectedFr}/{routeId}", method = RequestMethod.GET)
	public ModelAndView showSaleBil(@PathVariable String fromDate, @PathVariable String toDate,
			@PathVariable String selectedFr, @PathVariable String routeId, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/pdf/salesroyaltybycatPdf");
		List<SalesReportBillwise> saleList = new ArrayList<>();
		List<SalesReportRoyalty> royaltyList = new ArrayList<>();
		RoyaltyListBean royaltyBean = new RoyaltyListBean();
		boolean isAllFrSelected = false;

		try {
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			RestTemplate restTemplate = new RestTemplate();

			float royPer = getRoyPer();

			if (!routeId.equalsIgnoreCase("0")) {

				map = new LinkedMultiValueMap<String, Object>();
				map.add("routeId", routeId);

				FrNameIdByRouteIdResponse frNameId = restTemplate.postForObject(Constants.url + "getFrNameIdByRouteId",
						map, FrNameIdByRouteIdResponse.class);

				List<FrNameIdByRouteId> frNameIdByRouteIdList = frNameId.getFrNameIdByRouteIds();

				System.out.println("route wise franchisee " + frNameIdByRouteIdList.toString());

				StringBuilder sbForRouteFrId = new StringBuilder();
				for (int i = 0; i < frNameIdByRouteIdList.size(); i++) {

					sbForRouteFrId = sbForRouteFrId.append(frNameIdByRouteIdList.get(i).getFrId().toString() + ",");

				}

				String strFrIdRouteWise = sbForRouteFrId.toString();
				selectedFr = strFrIdRouteWise.substring(0, strFrIdRouteWise.length() - 1);
				System.out.println("fr Id Route WISE = " + selectedFr);

			} // end of if

			if (selectedFr.equalsIgnoreCase("-1")) {
				isAllFrSelected = true;
			}

			map = new LinkedMultiValueMap<String, Object>();

			if (isAllFrSelected) {

				System.out.println("Inside If all fr Selected ");
				// getSalesReportRoyaltyAllFr
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				ParameterizedTypeReference<List<SalesReportRoyalty>> typeRef = new ParameterizedTypeReference<List<SalesReportRoyalty>>() {
				};
				ResponseEntity<List<SalesReportRoyalty>> responseEntity = restTemplate.exchange(
						Constants.url + "getSalesReportRoyaltyAllFr", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				royaltyList = responseEntity.getBody();

			} else {
				System.out.println("Inside else Few fr Selected ");

				map.add("frIdList", selectedFr);
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				ParameterizedTypeReference<List<SalesReportRoyalty>> typeRef = new ParameterizedTypeReference<List<SalesReportRoyalty>>() {
				};
				ResponseEntity<List<SalesReportRoyalty>> responseEntity = restTemplate.exchange(
						Constants.url + "getSalesReportRoyalty", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				royaltyList = responseEntity.getBody();

			}
			royaltyListForPdf = new ArrayList<>();

			royaltyListForPdf = royaltyList;

			System.out.println("royaltyList List Bill Wise " + saleList.toString());

			CategoryListResponse categoryListResponse = restTemplate.getForObject(Constants.url + "showAllCategory",
					CategoryListResponse.class);
			List<MCategoryList> categoryList;
			categoryList = categoryListResponse.getmCategoryList();
			// allFrIdNameList = new AllFrIdNameList();
			System.out.println("Category list  " + categoryList);

			royaltyBean.setCategoryList(categoryList);
			royaltyBean.setSalesReportRoyalty(royaltyList);
			staticRoyaltyBean = royaltyBean;
			model.addObject("royPer", royPer);

		} catch (Exception e) {
			System.out.println("get sale Report royaltyList by cat " + e.getMessage());
			e.printStackTrace();

		}

		model.addObject("fromDate", fromDate);

		model.addObject("toDate", toDate);

		model.addObject("catList", staticRoyaltyBean.getCategoryList());
		model.addObject("royaltyList", staticRoyaltyBean.getSalesReportRoyalty());
		return model;
	}

	// royalty FR wise

	@RequestMapping(value = "/showSaleRoyaltyByFr", method = RequestMethod.GET)
	public ModelAndView showSaleRoyaltyByFr(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/salesroyaltybyfr");

		// Constants.mainAct =2;
		// Constants.subAct =20;

		try {
			ZoneId z = ZoneId.of("Asia/Calcutta");

			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			todaysDate = date.format(formatters);

			RestTemplate restTemplate = new RestTemplate();

			// get Routes

			AllRoutesListResponse allRouteListResponse = restTemplate.getForObject(Constants.url + "showRouteList",
					AllRoutesListResponse.class);

			List<Route> routeList = new ArrayList<Route>();

			routeList = allRouteListResponse.getRoute();

			// end get Routes

			allFrIdNameList = new AllFrIdNameList();
			try {

				allFrIdNameList = restTemplate.getForObject(Constants.url + "getAllFranchises", AllFrIdNameList.class);

			} catch (Exception e) {
				System.out.println("Exception in getAllFrIdName" + e.getMessage());
				e.printStackTrace();

			}
			List<AllFrIdName> selectedFrListAll = new ArrayList();
			List<Menu> selectedMenuList = new ArrayList<Menu>();

			System.out.println(" Fr " + allFrIdNameList.getFrIdNamesList());
			model.addObject("royPer", getRoyPer());
			model.addObject("todaysDate", todaysDate);
			model.addObject("unSelectedFrList", allFrIdNameList.getFrIdNamesList());

			model.addObject("routeList", routeList);

		} catch (Exception e) {

			System.out.println("Exc in show sales report bill wise  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/getSaleRoyaltyByFr", method = RequestMethod.GET)
	public @ResponseBody List<SalesReportRoyaltyFr> getSaleRoyaltyByFr(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			System.out.println("Inside get Sale royalty by fr");
			String selectedFr = request.getParameter("fr_id_list");
			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
			String routeId = request.getParameter("route_id");

			boolean isAllFrSelected = false;
			selectedFr = selectedFr.substring(1, selectedFr.length() - 1);
			selectedFr = selectedFr.replaceAll("\"", "");

			frList = new ArrayList<>();
			frList = Arrays.asList(selectedFr);

			if (!routeId.equalsIgnoreCase("0")) {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				RestTemplate restTemplate = new RestTemplate();

				map.add("routeId", routeId);

				FrNameIdByRouteIdResponse frNameId = restTemplate.postForObject(Constants.url + "getFrNameIdByRouteId",
						map, FrNameIdByRouteIdResponse.class);

				List<FrNameIdByRouteId> frNameIdByRouteIdList = frNameId.getFrNameIdByRouteIds();

				System.out.println("route wise franchisee " + frNameIdByRouteIdList.toString());

				StringBuilder sbForRouteFrId = new StringBuilder();
				for (int i = 0; i < frNameIdByRouteIdList.size(); i++) {

					sbForRouteFrId = sbForRouteFrId.append(frNameIdByRouteIdList.get(i).getFrId().toString() + ",");

				}

				String strFrIdRouteWise = sbForRouteFrId.toString();
				selectedFr = strFrIdRouteWise.substring(0, strFrIdRouteWise.length() - 1);
				System.out.println("fr Id Route WISE = " + selectedFr);

			} // end of if

			if (frList.contains("-1")) {
				isAllFrSelected = true;
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			if (isAllFrSelected) {

				System.out.println("Inside If all fr Selected /getSaleRoyaltyByFr :getSalesReportRoyaltyFrAllFr");
				// Web Service :getSalesReportRoyaltyFrAllFr
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				ParameterizedTypeReference<List<SalesReportRoyaltyFr>> typeRef = new ParameterizedTypeReference<List<SalesReportRoyaltyFr>>() {
				};
				ResponseEntity<List<SalesReportRoyaltyFr>> responseEntity = restTemplate.exchange(
						Constants.url + "getSalesReportRoyaltyFrAllFr", HttpMethod.POST, new HttpEntity<>(map),
						typeRef);

				royaltyFrList = new ArrayList<>();
				royaltyFrList = responseEntity.getBody();

			} else {
				System.out.println("Inside else Few fr Selected /getSaleRoyaltyByFr :getSalesReportRoyaltyFr");

				map.add("frIdList", selectedFr);
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				ParameterizedTypeReference<List<SalesReportRoyaltyFr>> typeRef = new ParameterizedTypeReference<List<SalesReportRoyaltyFr>>() {
				};
				ResponseEntity<List<SalesReportRoyaltyFr>> responseEntity = restTemplate.exchange(
						Constants.url + "getSalesReportRoyaltyFr", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				royaltyFrList = new ArrayList<>();
				royaltyFrList = responseEntity.getBody();
			}
			// royaltyListForPdf=new ArrayList<>();

			// royaltyListForPdf=royaltyList;
			staticRoyaltyFrList = new ArrayList<>();

			staticRoyaltyFrList = royaltyFrList;
			System.out.println("royalty List List royaltyFr List " + royaltyFrList.toString());

			// allFrIdNameList = new AllFrIdNameList();

		} catch (Exception e) {
			System.out.println("get sale Report royaltyList by cat " + e.getMessage());
			e.printStackTrace();

		}
		float royPer = getRoyPer();
		// exportToExcel
		List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

		ExportToExcel expoExcel = new ExportToExcel();
		List<String> rowData = new ArrayList<String>();

		rowData.add("fr Id");
		rowData.add("Franchisee Code");
		rowData.add("Franchisee Name");
		rowData.add("Franchisee City");
		rowData.add("Sale Value");
		rowData.add("GRN Value");
		rowData.add("GVN Value");
		rowData.add("Royalty %");
		rowData.add("Net Amount");
		rowData.add("Royalty Amt");

		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);
		for (int i = 0; i < royaltyFrList.size(); i++) {
			expoExcel = new ExportToExcel();
			rowData = new ArrayList<String>();

			rowData.add("" + royaltyFrList.get(i).getFrId());

			rowData.add("" + royaltyFrList.get(i).getFrId());
			rowData.add(royaltyFrList.get(i).getFrName());

			rowData.add(royaltyFrList.get(i).getFrCity());
			rowData.add("" + royaltyFrList.get(i).gettBillTaxableAmt());

			rowData.add("" + royaltyFrList.get(i).gettGrnTaxableAmt());
			rowData.add("" + royaltyFrList.get(i).gettGvnTaxableAmt());
			rowData.add("" + royPer);
			float netValue = royaltyFrList.get(i).gettBillTaxableAmt()
					- (royaltyFrList.get(i).gettGrnTaxableAmt() + royaltyFrList.get(i).gettGvnTaxableAmt());
			float royaltyAmt = netValue * royPer / 100;
			rowData.add("" + roundUp(netValue));
			rowData.add("" + roundUp(royaltyAmt));
			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);

		}

		HttpSession session = request.getSession();
		session.setAttribute("exportExcelList", exportToExcelList);
		session.setAttribute("excelName", "RoyaltyFrList");

		return royaltyFrList;

	}

	// royalty fr pdf is not done

	// done pdf
	@RequestMapping(value = "pdf/showSaleRoyaltyByFrPdf/{fromDate}/{toDate}/{selectedFr}/{routeId}", method = RequestMethod.GET)
	public ModelAndView showSaleRoyaltyByFrPdf(@PathVariable String fromDate, @PathVariable String toDate,
			@PathVariable String selectedFr, @PathVariable String routeId, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/pdf/salesroyaltybyfrPdf");
		boolean isAllFrSelected = false;

		try {
			System.out.println("Inside get Sale royalty by fr");

			if (!routeId.equalsIgnoreCase("0")) {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				RestTemplate restTemplate = new RestTemplate();

				map.add("routeId", routeId);

				FrNameIdByRouteIdResponse frNameId = restTemplate.postForObject(Constants.url + "getFrNameIdByRouteId",
						map, FrNameIdByRouteIdResponse.class);

				List<FrNameIdByRouteId> frNameIdByRouteIdList = frNameId.getFrNameIdByRouteIds();

				System.out.println("route wise franchisee " + frNameIdByRouteIdList.toString());

				StringBuilder sbForRouteFrId = new StringBuilder();
				for (int i = 0; i < frNameIdByRouteIdList.size(); i++) {

					sbForRouteFrId = sbForRouteFrId.append(frNameIdByRouteIdList.get(i).getFrId().toString() + ",");

				}

				String strFrIdRouteWise = sbForRouteFrId.toString();
				selectedFr = strFrIdRouteWise.substring(0, strFrIdRouteWise.length() - 1);
				System.out.println("fr Id Route WISE = " + selectedFr);

			} // end of if

			if (selectedFr.equalsIgnoreCase("-1")) {
				isAllFrSelected = true;
			}
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			if (isAllFrSelected) {

				System.out.println("Inside If all fr Selected ");

				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				ParameterizedTypeReference<List<SalesReportRoyaltyFr>> typeRef = new ParameterizedTypeReference<List<SalesReportRoyaltyFr>>() {
				};
				ResponseEntity<List<SalesReportRoyaltyFr>> responseEntity = restTemplate.exchange(
						Constants.url + "getSalesReportRoyaltyFrAllFr", HttpMethod.POST, new HttpEntity<>(map),
						typeRef);

				royaltyFrList = new ArrayList<>();
				royaltyFrList = responseEntity.getBody();

				// getSalesReportRoyaltyFrAllFr

			} else {
				System.out.println("Inside else Few fr Selected ");

				map.add("frIdList", selectedFr);
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				ParameterizedTypeReference<List<SalesReportRoyaltyFr>> typeRef = new ParameterizedTypeReference<List<SalesReportRoyaltyFr>>() {
				};
				ResponseEntity<List<SalesReportRoyaltyFr>> responseEntity = restTemplate.exchange(
						Constants.url + "getSalesReportRoyaltyFr", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				royaltyFrList = new ArrayList<>();
				royaltyFrList = responseEntity.getBody();

			}
			// royaltyListForPdf=new ArrayList<>();

			// royaltyListForPdf=royaltyList;
			staticRoyaltyFrList = new ArrayList<>();

			staticRoyaltyFrList = royaltyFrList;
			System.out.println("royalty List List royaltyFr List " + royaltyFrList.toString());

			// allFrIdNameList = new AllFrIdNameList();

		} catch (Exception e) {
			System.out.println("get sale Report royaltyList by Fr " + e.getMessage());
			e.printStackTrace();

		}
		model.addObject("royPer", getRoyPer());

		model.addObject("fromDate", fromDate);

		model.addObject("toDate", toDate);

		model.addObject("report", staticRoyaltyFrList);

		return model;
	}

	// report no 8
	@RequestMapping(value = "/showSaleReportItemwise", method = RequestMethod.GET)
	public ModelAndView showSaleReportItemwise(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/salesreportitemwise");

		// Constants.mainAct =2;
		// Constants.subAct =20;

		try {
			ZoneId z = ZoneId.of("Asia/Calcutta");

			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			todaysDate = date.format(formatters);

			RestTemplate restTemplate = new RestTemplate();

			// get Routes

			AllRoutesListResponse allRouteListResponse = restTemplate.getForObject(Constants.url + "showRouteList",
					AllRoutesListResponse.class);

			List<Route> routeList = new ArrayList<Route>();

			routeList = allRouteListResponse.getRoute();

			// end get Routes

			allFrIdNameList = new AllFrIdNameList();
			try {

				allFrIdNameList = restTemplate.getForObject(Constants.url + "getAllFrIdName", AllFrIdNameList.class);

			} catch (Exception e) {
				System.out.println("Exception in getAllFrIdName" + e.getMessage());
				e.printStackTrace();

			}
			List<AllFrIdName> selectedFrListAll = new ArrayList();
			List<Menu> selectedMenuList = new ArrayList<Menu>();

			System.out.println(" Fr " + allFrIdNameList.getFrIdNamesList());

			model.addObject("todaysDate", todaysDate);
			model.addObject("unSelectedFrList", allFrIdNameList.getFrIdNamesList());

			model.addObject("routeList", routeList);

		} catch (Exception e) {

			System.out.println("Exc in show sales report bill wise  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/getSaleReportItemwise", method = RequestMethod.GET)
	public @ResponseBody List<SalesReportItemwise> getSaleReportItemwise(HttpServletRequest request,
			HttpServletResponse response) {

		List<SalesReportItemwise> saleList = new ArrayList<>();

		try {
			System.out.println("Inside get Sale Item  Wise");
			String selectedFr = request.getParameter("fr_id_list");
			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
			String routeId = request.getParameter("route_id");

			boolean isAllFrSelected = false;
			selectedFr = selectedFr.substring(1, selectedFr.length() - 1);
			selectedFr = selectedFr.replaceAll("\"", "");

			frList = new ArrayList<>();
			frList = Arrays.asList(selectedFr);

			if (!routeId.equalsIgnoreCase("0")) {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				RestTemplate restTemplate = new RestTemplate();

				map.add("routeId", routeId);

				FrNameIdByRouteIdResponse frNameId = restTemplate.postForObject(Constants.url + "getFrNameIdByRouteId",
						map, FrNameIdByRouteIdResponse.class);

				List<FrNameIdByRouteId> frNameIdByRouteIdList = frNameId.getFrNameIdByRouteIds();

				System.out.println("route wise franchisee " + frNameIdByRouteIdList.toString());

				StringBuilder sbForRouteFrId = new StringBuilder();
				for (int i = 0; i < frNameIdByRouteIdList.size(); i++) {

					sbForRouteFrId = sbForRouteFrId.append(frNameIdByRouteIdList.get(i).getFrId().toString() + ",");

				}

				String strFrIdRouteWise = sbForRouteFrId.toString();
				selectedFr = strFrIdRouteWise.substring(0, strFrIdRouteWise.length() - 1);
				System.out.println("fr Id Route WISE = " + selectedFr);

			} // end of if

			if (frList.contains("-1")) {
				isAllFrSelected = true;

				// No frIds for Filter:it us based on Item Selection :
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			if (isAllFrSelected) {

				System.out.println("Inside If all fr Selected ");

				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

			} else {
				System.out.println("Inside else Few fr Selected ");

				map.add("frIdList", selectedFr);
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				ParameterizedTypeReference<List<SalesReportItemwise>> typeRef = new ParameterizedTypeReference<List<SalesReportItemwise>>() {
				};
				ResponseEntity<List<SalesReportItemwise>> responseEntity = restTemplate.exchange(
						Constants.url + "getSaleReportItemwise", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				saleList = responseEntity.getBody();
				saleListForPdf = new ArrayList<>();
				staticSaleListItemWise = saleList;
				// saleListForPdf=saleList;

				System.out.println("sales List Bill Wise " + saleList.toString());

			}
		} catch (Exception e) {
			System.out.println("get sale Report Bill Wise " + e.getMessage());
			e.printStackTrace();

		}

		// exportToExcel
		List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

		ExportToExcel expoExcel = new ExportToExcel();
		List<String> rowData = new ArrayList<String>();

		rowData.add("Item Id");

		rowData.add("Item Name");
		rowData.add("Item Hsn Code");
		rowData.add("Bill Qty Sum");
		rowData.add("Taxable Amt");
		rowData.add("Item Tax1");
		rowData.add("Item Tax2");
		rowData.add("Total Tax");
		rowData.add("sgst sum");
		rowData.add("cgst sum");
		rowData.add("igst sum");
		rowData.add("Total");

		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);
		for (int i = 0; i < saleList.size(); i++) {
			expoExcel = new ExportToExcel();
			rowData = new ArrayList<String>();

			rowData.add("" + saleList.get(i).getId());

			rowData.add("" + saleList.get(i).getItemName());
			rowData.add("" + saleList.get(i).getItemHsncd());
			rowData.add("" + saleList.get(i).getBillQtySum());
			rowData.add("" + saleList.get(i).getTaxableAmtSum());
			rowData.add("" + saleList.get(i).getItemTax1());

			rowData.add("" + saleList.get(i).getItemTax2());
			rowData.add("" + saleList.get(i).getItemTax3());
			rowData.add("" + saleList.get(i).getSgstRsSum());
			rowData.add("" + saleList.get(i).getCgstRsSum());

			rowData.add("" + saleList.get(i).getIgstRsSum());
			rowData.add("" + saleList.get(i).getIgstRsSum() + saleList.get(i).getCgstRsSum()
					+ saleList.get(i).getSgstRsSum() + saleList.get(i).getTaxableAmtSum());

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);

		}

		HttpSession session = request.getSession();
		session.setAttribute("exportExcelList", exportToExcelList);
		session.setAttribute("excelName", "SaleReportItemWise");
		return saleList;

	}

	// pdf for r8 to be done
	// pdf for r8
	@RequestMapping(value = "pdf/showSaleReportItemwisePdf/{fromDate}/{toDate}/{selectedFr}/{routeId}", method = RequestMethod.GET)
	public ModelAndView showSaleReportItemwisePdf(@PathVariable String fromDate, @PathVariable String toDate,
			@PathVariable String selectedFr, @PathVariable String routeId, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/pdf/salesreportitemwisePdf");

		List<SalesReportItemwise> saleList = new ArrayList<>();
		boolean isAllFrSelected = false;

		try {
			System.out.println("Inside get Sale Item  Wise");

			if (!routeId.equalsIgnoreCase("0")) {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				RestTemplate restTemplate = new RestTemplate();

				map.add("routeId", routeId);

				FrNameIdByRouteIdResponse frNameId = restTemplate.postForObject(Constants.url + "getFrNameIdByRouteId",
						map, FrNameIdByRouteIdResponse.class);

				List<FrNameIdByRouteId> frNameIdByRouteIdList = frNameId.getFrNameIdByRouteIds();

				System.out.println("route wise franchisee " + frNameIdByRouteIdList.toString());

				StringBuilder sbForRouteFrId = new StringBuilder();
				for (int i = 0; i < frNameIdByRouteIdList.size(); i++) {

					sbForRouteFrId = sbForRouteFrId.append(frNameIdByRouteIdList.get(i).getFrId().toString() + ",");

				}

				String strFrIdRouteWise = sbForRouteFrId.toString();
				selectedFr = strFrIdRouteWise.substring(0, strFrIdRouteWise.length() - 1);
				System.out.println("fr Id Route WISE = " + selectedFr);

			} // end of if

			if (selectedFr.equalsIgnoreCase("-1")) {
				isAllFrSelected = true;
			}
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			if (isAllFrSelected) {

				System.out.println("Inside If all fr Selected ");

				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

			} else {
				System.out.println("Inside else Few fr Selected ");

				map.add("frIdList", selectedFr);
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				ParameterizedTypeReference<List<SalesReportItemwise>> typeRef = new ParameterizedTypeReference<List<SalesReportItemwise>>() {
				};
				ResponseEntity<List<SalesReportItemwise>> responseEntity = restTemplate.exchange(
						Constants.url + "getSaleReportItemwise", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				saleList = responseEntity.getBody();
				saleListForPdf = new ArrayList<>();
				staticSaleListItemWise = saleList;
				// saleListForPdf=saleList;

				System.out.println("sales List Bill Wise " + saleList.toString());

			}
		} catch (Exception e) {
			System.out.println("get sale Report Bill Wise " + e.getMessage());
			e.printStackTrace();

		}

		model.addObject("fromDate", fromDate);

		model.addObject("toDate", toDate);

		model.addObject("report", staticSaleListItemWise);

		return model;
	}

	// report 7
	@RequestMapping(value = "/showSaleReportBillwiseAllFr", method = RequestMethod.GET)
	public ModelAndView showSaleReportBillwiseAllFr(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/salesreportbillallfr");

		// Constants.mainAct =2;
		// Constants.subAct =20;

		try {
			ZoneId z = ZoneId.of("Asia/Calcutta");

			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			todaysDate = date.format(formatters);

			RestTemplate restTemplate = new RestTemplate();

			// get Routes

			AllRoutesListResponse allRouteListResponse = restTemplate.getForObject(Constants.url + "showRouteList",
					AllRoutesListResponse.class);

			List<Route> routeList = new ArrayList<Route>();

			routeList = allRouteListResponse.getRoute();

			// end get Routes

			allFrIdNameList = new AllFrIdNameList();
			try {

				allFrIdNameList = restTemplate.getForObject(Constants.url + "getAllFrIdName", AllFrIdNameList.class);

			} catch (Exception e) {
				System.out.println("Exception in getAllFrIdName" + e.getMessage());
				e.printStackTrace();

			}
			List<AllFrIdName> selectedFrListAll = new ArrayList();
			List<Menu> selectedMenuList = new ArrayList<Menu>();

			System.out.println(" Fr " + allFrIdNameList.getFrIdNamesList());

			model.addObject("todaysDate", todaysDate);
			model.addObject("unSelectedFrList", allFrIdNameList.getFrIdNamesList());

			model.addObject("routeList", routeList);

		} catch (Exception e) {

			System.out.println("Exc in show sales report bill wise  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/getSaleReportBillwiseAllFr", method = RequestMethod.GET)
	public @ResponseBody List<SalesReportBillwiseAllFr> getSaleReportBillwiseAllFr(HttpServletRequest request,
			HttpServletResponse response) {

		List<SalesReportBillwiseAllFr> saleList = new ArrayList<>();

		try {
			System.out.println("Inside get Sale Item  Wise");
			// String selectedFr = request.getParameter("fr_id_list");
			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
			String routeId = request.getParameter("route_id");

			String selectedFr;

			// boolean isAllFrSelected = false;
			// selectedFr = selectedFr.substring(1, selectedFr.length() - 1);
			// selectedFr = selectedFr.replaceAll("\"", "");

			// frList = new ArrayList<>();
			// frList = Arrays.asList(selectedFr);

			/*
			 * if (!routeId.equalsIgnoreCase("0")) {
			 * 
			 * MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,
			 * Object>();
			 * 
			 * RestTemplate restTemplate = new RestTemplate();
			 * 
			 * map.add("routeId", routeId);
			 * 
			 * FrNameIdByRouteIdResponse frNameId = restTemplate.postForObject(Constants.url
			 * + "getFrNameIdByRouteId", map, FrNameIdByRouteIdResponse.class);
			 * 
			 * List<FrNameIdByRouteId> frNameIdByRouteIdList =
			 * frNameId.getFrNameIdByRouteIds();
			 * 
			 * System.out.println("route wise franchisee " +
			 * frNameIdByRouteIdList.toString());
			 * 
			 * StringBuilder sbForRouteFrId = new StringBuilder(); for (int i = 0; i <
			 * frNameIdByRouteIdList.size(); i++) {
			 * 
			 * sbForRouteFrId =
			 * sbForRouteFrId.append(frNameIdByRouteIdList.get(i).getFrId().toString() +
			 * ",");
			 * 
			 * }
			 * 
			 * String strFrIdRouteWise = sbForRouteFrId.toString(); selectedFr =
			 * strFrIdRouteWise.substring(0, strFrIdRouteWise.length() - 1);
			 * System.out.println("fr Id Route WISE = " + selectedFr);
			 * 
			 * } // end of if
			 * 
			 * if (frList.contains("-1")) { //isAllFrSelected = true; }
			 */

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			map.add("fromDate", fromDate);
			map.add("toDate", toDate);

			ParameterizedTypeReference<List<SalesReportBillwiseAllFr>> typeRef = new ParameterizedTypeReference<List<SalesReportBillwiseAllFr>>() {
			};
			ResponseEntity<List<SalesReportBillwiseAllFr>> responseEntity = restTemplate.exchange(
					Constants.url + "getSaleReportBillwiseAllFr", HttpMethod.POST, new HttpEntity<>(map), typeRef);

			saleList = responseEntity.getBody();
			staticSaleByAllFr = new ArrayList<>();
			staticSaleByAllFr = saleList;
			// saleListForPdf=saleList;

			System.out.println("sales List Bill Wise all fr  " + saleList.toString());

		} catch (Exception e) {
			System.out.println("get sale Report Bill Wise all Fr " + e.getMessage());
			e.printStackTrace();

		}

		// exportToExcel
		List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

		ExportToExcel expoExcel = new ExportToExcel();
		List<String> rowData = new ArrayList<String>();

		rowData.add("Bill No");
		rowData.add("Invoice No");
		rowData.add("Bill Date");
		rowData.add("Franchisee Id");
		rowData.add("Franchisee Name");
		rowData.add("Franchisee City");
		rowData.add("Franchisee Gst No");
		rowData.add("Item Name");
		rowData.add("Item Hsn Code");
		rowData.add("Item Tax1");
		rowData.add("Item Tax2");
		rowData.add("Item Tax2");
		rowData.add("Total Tax");
		rowData.add("sgst sum");
		rowData.add("cgst sum");
		rowData.add("igst sum");

		rowData.add("Taxable Amt");

		/*
		 * float taxableAmtSum; float sgstRsSum; float cgstRsSum; float igstRsSum;
		 */
		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);
		for (int i = 0; i < saleList.size(); i++) {
			expoExcel = new ExportToExcel();
			rowData = new ArrayList<String>();

			rowData.add("" + saleList.get(i).getBillNo());
			rowData.add(saleList.get(i).getInvoiceNo());
			rowData.add(saleList.get(i).getBillDate());

			rowData.add("" + saleList.get(i).getFrId());
			rowData.add(saleList.get(i).getFrName());

			rowData.add(saleList.get(i).getFrCity());
			rowData.add(saleList.get(i).getFrGstNo());
			rowData.add("" + saleList.get(i).getItemName());
			rowData.add("" + saleList.get(i).getItemHsncd());
			rowData.add("" + saleList.get(i).getItemTax1());

			rowData.add("" + saleList.get(i).getItemTax2());
			rowData.add("" + saleList.get(i).getItemTax3());
			rowData.add("" + saleList.get(i).getSgstRsSum());
			rowData.add("" + saleList.get(i).getCgstRsSum());

			rowData.add("" + saleList.get(i).getIgstRsSum());
			rowData.add("" + saleList.get(i).getTaxableAmtSum());

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);

		}

		HttpSession session = request.getSession();
		session.setAttribute("exportExcelList", exportToExcelList);
		session.setAttribute("excelName", "BillWiseAllFr");
		return saleList;
	}
	// pdf to be done

	// pdf report 7
	@RequestMapping(value = "pdf/showSaleReportBillwiseAllFrPdf/{fromDate}/{toDate}", method = RequestMethod.GET)
	public ModelAndView showSaleReportBillwiseAllFrPdf(@PathVariable String fromDate, @PathVariable String toDate,
			HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/pdf/salereportbillallfrPdf");

		List<SalesReportBillwiseAllFr> saleList = new ArrayList<>();

		try {
			System.out.println("Inside get Sale Item  Wise");

			String selectedFr;

			// boolean isAllFrSelected = false;
			// selectedFr = selectedFr.substring(1, selectedFr.length() - 1);
			// selectedFr = selectedFr.replaceAll("\"", "");

			// frList = new ArrayList<>();
			// frList = Arrays.asList(selectedFr);

			/*
			 * if (!routeId.equalsIgnoreCase("0")) {
			 * 
			 * MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,
			 * Object>();
			 * 
			 * RestTemplate restTemplate = new RestTemplate();
			 * 
			 * map.add("routeId", routeId);
			 * 
			 * FrNameIdByRouteIdResponse frNameId = restTemplate.postForObject(Constants.url
			 * + "getFrNameIdByRouteId", map, FrNameIdByRouteIdResponse.class);
			 * 
			 * List<FrNameIdByRouteId> frNameIdByRouteIdList =
			 * frNameId.getFrNameIdByRouteIds();
			 * 
			 * System.out.println("route wise franchisee " +
			 * frNameIdByRouteIdList.toString());
			 * 
			 * StringBuilder sbForRouteFrId = new StringBuilder(); for (int i = 0; i <
			 * frNameIdByRouteIdList.size(); i++) {
			 * 
			 * sbForRouteFrId =
			 * sbForRouteFrId.append(frNameIdByRouteIdList.get(i).getFrId().toString() +
			 * ",");
			 * 
			 * }
			 * 
			 * String strFrIdRouteWise = sbForRouteFrId.toString(); selectedFr =
			 * strFrIdRouteWise.substring(0, strFrIdRouteWise.length() - 1);
			 * System.out.println("fr Id Route WISE = " + selectedFr);
			 * 
			 * } // end of if
			 * 
			 * if (frList.contains("-1")) { //isAllFrSelected = true; }
			 */

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			System.out.println("Inside else Few fr Selected ");

			// map.add("frIdList", selectedFr);
			map.add("fromDate", fromDate);
			map.add("toDate", toDate);

			ParameterizedTypeReference<List<SalesReportBillwiseAllFr>> typeRef = new ParameterizedTypeReference<List<SalesReportBillwiseAllFr>>() {
			};
			ResponseEntity<List<SalesReportBillwiseAllFr>> responseEntity = restTemplate.exchange(
					Constants.url + "getSaleReportBillwiseAllFr", HttpMethod.POST, new HttpEntity<>(map), typeRef);

			saleList = responseEntity.getBody();
			staticSaleByAllFr = new ArrayList<>();
			staticSaleByAllFr = saleList;
			// saleListForPdf=saleList;

			System.out.println("sales List Bill Wise all fr  " + saleList.toString());

		} catch (Exception e) {
			System.out.println("get sale Report Bill Wise all Fr " + e.getMessage());
			e.printStackTrace();

		}
		model.addObject("fromDate", fromDate);

		model.addObject("toDate", toDate);

		model.addObject("report", staticSaleByAllFr);

		return model;
	}

	List<MCategoryList> categoryListAjax;

	@RequestMapping(value = "/getAllCatAjax", method = RequestMethod.GET)
	public @ResponseBody List<MCategoryList> getAllCatAjax(HttpServletRequest request, HttpServletResponse response) {
		return categoryListAjax;
	}

	@RequestMapping(value = "/getFrListForDatewiseReport", method = RequestMethod.GET)
	@ResponseBody
	public List<AllFrIdName> getFrListForDatewiseReport(HttpServletRequest request, HttpServletResponse response) {

		return allFrIdNameList.getFrIdNamesList();
	}

	// report no 10 conso by category report

	@RequestMapping(value = "/showSaleReportRoyConsoByCat", method = RequestMethod.GET)
	public ModelAndView showSaleReportRoyConsoByCat(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/salesconsbycat");

		// Constants.mainAct =2;
		// Constants.subAct =20;

		try {
			ZoneId z = ZoneId.of("Asia/Calcutta");

			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			todaysDate = date.format(formatters);

			RestTemplate restTemplate = new RestTemplate();

			// get Routes

			AllRoutesListResponse allRouteListResponse = restTemplate.getForObject(Constants.url + "showRouteList",
					AllRoutesListResponse.class);

			List<Route> routeList = new ArrayList<Route>();

			routeList = allRouteListResponse.getRoute();

			// end get Routes

			allFrIdNameList = new AllFrIdNameList();
			try {

				allFrIdNameList = restTemplate.getForObject(Constants.url + "getAllFranchises", AllFrIdNameList.class);
				StringBuilder frId = new StringBuilder();

				for (int i = 0; i < allFrIdNameList.getFrIdNamesList().size(); i++) {
					frId = frId.append(allFrIdNameList.getFrIdNamesList().get(i).getFrId() + ",");

				}
				String strFrId = frId.toString();
				strFrId = strFrId.substring(0, strFrId.length() - 1);
				model.addObject("strFrId", strFrId);
			} catch (Exception e) {
				System.out.println("Exception in getAllFrIdName" + e.getMessage());
				e.printStackTrace();

			}

			CategoryListResponse categoryListResponse = restTemplate.getForObject(Constants.url + "showAllCategory",
					CategoryListResponse.class);
			// List<MCategoryList> categoryList;
			categoryListAjax = categoryListResponse.getmCategoryList();

			System.out.println(" Fr " + allFrIdNameList.getFrIdNamesList());

			model.addObject("catList", categoryListAjax);

			model.addObject("todaysDate", todaysDate);
			model.addObject("unSelectedFrList", allFrIdNameList.getFrIdNamesList());

			model.addObject("routeList", routeList);
			model.addObject("royPer", getRoyPer());

		} catch (Exception e) {

			System.out.println("Exc in show sales report bill wise  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	// ----------------------------Show Dispatch Item
	// List-----------------------------
	@RequestMapping(value = "/showDispatchItemReport", method = RequestMethod.GET)
	public ModelAndView showDispatchItemReport(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/dispatchReport");

		// Constants.mainAct =2;
		// Constants.subAct =20;

		try {
			ZoneId z = ZoneId.of("Asia/Calcutta");

			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			todaysDate = date.format(formatters);

			RestTemplate restTemplate = new RestTemplate();

			// get Routes

			AllRoutesListResponse allRouteListResponse = restTemplate.getForObject(Constants.url + "showRouteList",
					AllRoutesListResponse.class);

			List<Route> routeList = new ArrayList<Route>();

			routeList = allRouteListResponse.getRoute();

			// end get Routes

			allFrIdNameList = new AllFrIdNameList();
			try {

				allFrIdNameList = restTemplate.getForObject(Constants.url + "getAllFrIdName", AllFrIdNameList.class);

			} catch (Exception e) {
				System.out.println("Exception in getAllFrIdName" + e.getMessage());
				e.printStackTrace();

			}

			CategoryListResponse categoryListResponse = restTemplate.getForObject(Constants.url + "showAllCategory",
					CategoryListResponse.class);
			List<MCategoryList> categoryList;
			categoryList = categoryListResponse.getmCategoryList();

			System.out.println(" Fr " + allFrIdNameList.getFrIdNamesList());

			model.addObject("catList", categoryList);

			model.addObject("todaysDate", todaysDate);
			model.addObject("unSelectedFrList", allFrIdNameList.getFrIdNamesList());

			model.addObject("routeList", routeList);

		} catch (Exception e) {

			System.out.println("Exc in show sales report bill wise  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	// ---------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/getDispatchReportByRoute", method = RequestMethod.GET)
	public @ResponseBody DispatchReportList getDispatchReportByRoute(HttpServletRequest request,
			HttpServletResponse response) {

		List<DispatchReport> dispatchReportList = new ArrayList<DispatchReport>();
		DispatchReportList dispatchReports = new DispatchReportList();
		try {
			System.out.println("Inside get Dispatch Report");
			String billDate = request.getParameter("bill_date");
			String routeId = request.getParameter("route_id");
			String selectedCat = request.getParameter("cat_id_list");
			String status = request.getParameter("status");

			System.out.println("------------------------------------------" + status);

			boolean isAllCatSelected = false;
			String selectedFr = null;

			if (selectedCat.contains("-1")) {
				isAllCatSelected = true;
			} else {
				selectedCat = selectedCat.substring(1, selectedCat.length() - 1);
				selectedCat = selectedCat.replaceAll("\"", "");
				System.out.println("selectedCat" + selectedCat.toString());
			}
			List<String> catList = new ArrayList<>();
			catList = Arrays.asList(selectedCat);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			RestTemplate restTemplate = new RestTemplate();

			map.add("routeId", routeId);

			FrNameIdByRouteIdResponse frNameId = restTemplate.postForObject(Constants.url + "getFrNameIdByRouteId", map,
					FrNameIdByRouteIdResponse.class);

			List<FrNameIdByRouteId> frNameIdByRouteIdList = frNameId.getFrNameIdByRouteIds();

			System.out.println("route wise franchisee " + frNameIdByRouteIdList.toString());

			StringBuilder sbForRouteFrId = new StringBuilder();
			for (int i = 0; i < frNameIdByRouteIdList.size(); i++) {

				sbForRouteFrId = sbForRouteFrId.append(frNameIdByRouteIdList.get(i).getFrId().toString() + ",");

			}

			String strFrIdRouteWise = sbForRouteFrId.toString();
			selectedFr = strFrIdRouteWise.substring(0, strFrIdRouteWise.length() - 1);
			System.out.println("fr Id Route WISE = " + selectedFr);

			if (selectedCat.contains("-1")) {
				isAllCatSelected = true;
			}

			map = new LinkedMultiValueMap<String, Object>();

			allFrIdNameList = new AllFrIdNameList();
			try {

				allFrIdNameList = restTemplate.getForObject(Constants.url + "getAllFrIdName", AllFrIdNameList.class);

			} catch (Exception e) {
				System.out.println("Exception in getAllFrIdName" + e.getMessage());
				e.printStackTrace();

			}

			if (isAllCatSelected) {
				map = new LinkedMultiValueMap<String, Object>();

				CategoryListResponse categoryListResponse = restTemplate.getForObject(Constants.url + "showAllCategory",
						CategoryListResponse.class);
				List<MCategoryList> categoryList = categoryListResponse.getmCategoryList();
				// List<Integer> cateList=new ArrayList<>();
				StringBuilder cateList = new StringBuilder();

				for (MCategoryList mCategoryList : categoryList) {
					// cateList.add(mCategoryList.getCatId());
					cateList = cateList.append(mCategoryList.getCatId().toString() + ",");
				}

				String catlist = cateList.toString();
				selectedCat = catlist.substring(0, catlist.length() - 1);
				System.out.println("cateList" + selectedCat.toString());
				System.out.println("selectedFr" + selectedFr.toString());
				System.out.println("billDate" + billDate.toString());

				map.add("categories", selectedCat);
				map.add("billDate", billDate);
				map.add("frId", selectedFr);

				if (status.equals("1")) {

					ParameterizedTypeReference<List<DispatchReport>> typeRef = new ParameterizedTypeReference<List<DispatchReport>>() {
					};

					ResponseEntity<List<DispatchReport>> responseEntity = restTemplate.exchange(
							Constants.url + "getDispatchItemReport", HttpMethod.POST, new HttpEntity<>(map), typeRef);

					dispatchReportList = responseEntity.getBody();
					System.out.println("dispatchReportList = " + dispatchReportList.toString());
				} else {

					ParameterizedTypeReference<List<DispatchReport>> typeRef = new ParameterizedTypeReference<List<DispatchReport>>() {
					};

					ResponseEntity<List<DispatchReport>> responseEntity = restTemplate.exchange(
							Constants.url + "getDispatchItemReportByOrder", HttpMethod.POST, new HttpEntity<>(map),
							typeRef);

					dispatchReportList = responseEntity.getBody();
					System.out.println("dispatchReportList = " + dispatchReportList.toString());
				}

				map = new LinkedMultiValueMap<String, Object>();
				map.add("catIdList", selectedCat);
				ParameterizedTypeReference<List<Item>> typeRef1 = new ParameterizedTypeReference<List<Item>>() {
				};

				ResponseEntity<List<Item>> responseEntity1 = restTemplate.exchange(
						Constants.url + "getItemsByCatIdForDisp", HttpMethod.POST, new HttpEntity<>(map), typeRef1);
				System.out.println("Items:" + responseEntity1.toString());
				SubCategory[] subCatList = restTemplate.getForObject(Constants.url + "getAllSubCatList",
						SubCategory[].class);

				ArrayList<SubCategory> subCatAList = new ArrayList<SubCategory>(Arrays.asList(subCatList));
				System.out.println("subCatAList:" + subCatAList.toString());
				dispatchReports.setDispatchReportList(dispatchReportList);
				dispatchReports.setFrList(frNameIdByRouteIdList);
				dispatchReports.setItemList(responseEntity1.getBody());
				dispatchReports.setSubCatList(subCatAList);

			} else {
				System.out.println("selectedCat" + selectedCat.toString());
				System.out.println("selectedFr" + selectedFr.toString());

				map.add("categories", selectedCat);
				map.add("billDate", billDate);
				map.add("frId", selectedFr);

				if (status.equals("1")) {

					ParameterizedTypeReference<List<DispatchReport>> typeRef = new ParameterizedTypeReference<List<DispatchReport>>() {
					};

					ResponseEntity<List<DispatchReport>> responseEntity = restTemplate.exchange(
							Constants.url + "getDispatchItemReport", HttpMethod.POST, new HttpEntity<>(map), typeRef);
					System.out.println("Items:" + responseEntity.toString());
					dispatchReportList = responseEntity.getBody();
					System.out.println("dispatchReportList = " + dispatchReportList.toString());
				} else {
					ParameterizedTypeReference<List<DispatchReport>> typeRef = new ParameterizedTypeReference<List<DispatchReport>>() {
					};

					ResponseEntity<List<DispatchReport>> responseEntity = restTemplate.exchange(
							Constants.url + "getDispatchItemReportByOrder", HttpMethod.POST, new HttpEntity<>(map),
							typeRef);
					System.out.println("Items:" + responseEntity.toString());
					dispatchReportList = responseEntity.getBody();
					System.out.println("dispatchReportList = " + dispatchReportList.toString());
				}

				map = new LinkedMultiValueMap<String, Object>();
				map.add("catIdList", selectedCat);
				ParameterizedTypeReference<List<Item>> typeRef1 = new ParameterizedTypeReference<List<Item>>() {
				};

				ResponseEntity<List<Item>> responseEntity1 = restTemplate.exchange(
						Constants.url + "getItemsByCatIdForDisp", HttpMethod.POST, new HttpEntity<>(map), typeRef1);

				map = new LinkedMultiValueMap<String, Object>();
				map.add("catId", selectedCat);
				ParameterizedTypeReference<List<SubCategory>> typeRef2 = new ParameterizedTypeReference<List<SubCategory>>() {
				};

				ResponseEntity<List<SubCategory>> responseEntity2 = restTemplate
						.exchange(Constants.url + "getSubCatList", HttpMethod.POST, new HttpEntity<>(map), typeRef2);

				dispatchReports.setDispatchReportList(dispatchReportList);
				dispatchReports.setFrList(frNameIdByRouteIdList);
				dispatchReports.setItemList(responseEntity1.getBody());
				dispatchReports.setSubCatList(responseEntity2.getBody());
			}

		} catch (Exception e) {
			System.out.println("get Dispatch Report Exception: " + e.getMessage());
			e.printStackTrace();

		}

		return dispatchReports;

	}

	@RequestMapping(value = "pdf/getDispatchReportPdf/{billDate}/{routeId}/{selectedCat}/{status}", method = RequestMethod.GET)
	public ModelAndView getSaleReportRoyConsoByCat(@PathVariable String billDate, @PathVariable String routeId,
			@PathVariable String selectedCat, @PathVariable String status, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView model = new ModelAndView("reports/sales/dispatchReportPdf");
		RestTemplate restTemplate = new RestTemplate();

		List<DispatchReport> dispatchReportList = new ArrayList<DispatchReport>();
		DispatchReportList dispatchReports = new DispatchReportList();
		List<SubCategory> subCategoryList = new ArrayList<SubCategory>();
		List<Item> itemList = new ArrayList<Item>();
		try {
			System.out.println("Inside get Dispatch Report");
			// String billDate = request.getParameter("bill_date");
			// String routeId = request.getParameter("route_id");
			// String selectedCat=request.getParameter("cat_id_list");
			AllRoutesListResponse allRouteListResponse = restTemplate.getForObject(Constants.url + "showRouteList",
					AllRoutesListResponse.class);

			List<Route> routeList = new ArrayList<Route>();

			routeList = allRouteListResponse.getRoute();
			String routeName = "def";
			for (int i = 0; i < routeList.size(); i++) {

				if (routeList.get(i).getRouteId() == Integer.parseInt(routeId)) {
					routeName = routeList.get(i).getRouteName();
					break;

				}
			}
			boolean isAllCatSelected = false;
			String selectedFr = null;

			if (selectedCat.contains("-1")) {
				isAllCatSelected = true;
			} else {
				// selectedCat = selectedCat.substring(1, selectedCat.length() - 1);
				// selectedCat = selectedCat.replaceAll("\"", "");
				// System.out.println("selectedCat"+selectedCat.toString());
			}
			List<String> catList = new ArrayList<>();
			catList = Arrays.asList(selectedCat);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("routeId", routeId);

			FrNameIdByRouteIdResponse frNameId = restTemplate.postForObject(Constants.url + "getFrNameIdByRouteId", map,
					FrNameIdByRouteIdResponse.class);

			List<FrNameIdByRouteId> frNameIdByRouteIdList = frNameId.getFrNameIdByRouteIds();

			System.out.println("route wise franchisee " + frNameIdByRouteIdList.toString());

			StringBuilder sbForRouteFrId = new StringBuilder();
			for (int i = 0; i < frNameIdByRouteIdList.size(); i++) {

				sbForRouteFrId = sbForRouteFrId.append(frNameIdByRouteIdList.get(i).getFrId().toString() + ",");

			}

			String strFrIdRouteWise = sbForRouteFrId.toString();
			selectedFr = strFrIdRouteWise.substring(0, strFrIdRouteWise.length() - 1);
			System.out.println("fr Id Route WISE = " + selectedFr);

			if (selectedCat.contains("-1")) {
				isAllCatSelected = true;
			}

			map = new LinkedMultiValueMap<String, Object>();

			allFrIdNameList = new AllFrIdNameList();
			try {

				allFrIdNameList = restTemplate.getForObject(Constants.url + "getAllFrIdName", AllFrIdNameList.class);

			} catch (Exception e) {
				System.out.println("Exception in getAllFrIdName" + e.getMessage());
				e.printStackTrace();

			}

			if (isAllCatSelected) {
				map = new LinkedMultiValueMap<String, Object>();

				CategoryListResponse categoryListResponse = restTemplate.getForObject(Constants.url + "showAllCategory",
						CategoryListResponse.class);
				List<MCategoryList> categoryList = categoryListResponse.getmCategoryList();

				StringBuilder cateList = new StringBuilder();
				// List<String> cateList = new ArrayList<>();
				for (MCategoryList mCategoryList : categoryList) {
					cateList = cateList.append(mCategoryList.getCatId().toString() + ",");
					// cateList.add("" + mCategoryList.getCatId());
				}
				System.err.println(cateList);
				String catlist = cateList.toString();
				selectedCat = catlist.substring(0, catlist.length() - 1);
				map.add("categories", selectedCat);
				map.add("billDate", billDate);
				map.add("frId", selectedFr);

				if (status.equals("1")) {

					ParameterizedTypeReference<List<DispatchReport>> typeRef = new ParameterizedTypeReference<List<DispatchReport>>() {
					};

					ResponseEntity<List<DispatchReport>> responseEntity = restTemplate.exchange(
							Constants.url + "getDispatchItemReport", HttpMethod.POST, new HttpEntity<>(map), typeRef);
					System.out.println("Items:" + responseEntity.toString());
					dispatchReportList = responseEntity.getBody();
					System.out.println("dispatchReportList = " + dispatchReportList.toString());
				} else {
					ParameterizedTypeReference<List<DispatchReport>> typeRef = new ParameterizedTypeReference<List<DispatchReport>>() {
					};

					ResponseEntity<List<DispatchReport>> responseEntity = restTemplate.exchange(
							Constants.url + "getDispatchItemReportByOrder", HttpMethod.POST, new HttpEntity<>(map),
							typeRef);
					System.out.println("Items:" + responseEntity.toString());
					dispatchReportList = responseEntity.getBody();
					System.out.println("dispatchReportList = " + dispatchReportList.toString());
				}

				/*
				 * ParameterizedTypeReference<List<DispatchReport>> typeRef = new
				 * ParameterizedTypeReference<List<DispatchReport>>() { };
				 * 
				 * ResponseEntity<List<DispatchReport>> responseEntity = restTemplate.exchange(
				 * Constants.url + "getDispatchItemReport", HttpMethod.POST, new
				 * HttpEntity<>(map), typeRef);
				 * 
				 * dispatchReportList = responseEntity.getBody();
				 * System.out.println("dispatchReportList = " + dispatchReportList.toString());
				 */

				map = new LinkedMultiValueMap<String, Object>();
				map.add("catIdList", selectedCat);
				ParameterizedTypeReference<List<Item>> typeRef1 = new ParameterizedTypeReference<List<Item>>() {
				};

				ResponseEntity<List<Item>> responseEntity1 = restTemplate.exchange(
						Constants.url + "getItemsByCatIdForDisp", HttpMethod.POST, new HttpEntity<>(map), typeRef1);

				SubCategory[] subCatList = restTemplate.getForObject(Constants.url + "getAllSubCatList",
						SubCategory[].class);

				ArrayList<SubCategory> subCatAList = new ArrayList<SubCategory>(Arrays.asList(subCatList));
				subCategoryList.addAll(subCatAList);
				/*
				 * if(!dispatchReportList.isEmpty()&&!responseEntity1.getBody().isEmpty()&&!
				 * frNameIdByRouteIdList.isEmpty()) { for(int
				 * j=0;j<responseEntity1.getBody().size();j++) { for(int
				 * i=0;i<frNameIdByRouteIdList.size();i++) { boolean flag=false; for(int
				 * k=0;k<dispatchReportList.size();k++) {
				 * if(dispatchReportList.get(k).getFrId()==frNameIdByRouteIdList.get(i).getFrId(
				 * ) &&
				 * dispatchReportList.get(k).getItemId()==responseEntity1.getBody().get(j).getId
				 * ()) { flag=true; break;
				 * 
				 * } } if(flag==false) { DispatchReport dispachReport=new DispatchReport();
				 * dispachReport.setBillDetailNo(0);
				 * dispachReport.setFrId(frNameIdByRouteIdList.get(i).getFrId());
				 * dispachReport.setItemId(responseEntity1.getBody().get(j).getId());
				 * dispachReport.setBillQty(0); dispatchReportList.add(dispachReport); } } } }
				 */
				itemList.addAll(responseEntity1.getBody());
				model.addObject("dispatchReportList", dispatchReportList);
				model.addObject("frList", frNameIdByRouteIdList);
				model.addObject("itemList", responseEntity1.getBody());
				model.addObject("subCatList", subCatAList);

			} else {
				System.out.println("selectedCat" + selectedCat.toString());
				System.out.println("selectedFr" + selectedFr.toString());

				map.add("categories", selectedCat);
				map.add("billDate", billDate);
				map.add("frId", selectedFr);

				if (status.equals("1")) {

					ParameterizedTypeReference<List<DispatchReport>> typeRef = new ParameterizedTypeReference<List<DispatchReport>>() {
					};

					ResponseEntity<List<DispatchReport>> responseEntity = restTemplate.exchange(
							Constants.url + "getDispatchItemReport", HttpMethod.POST, new HttpEntity<>(map), typeRef);
					System.out.println("Items:" + responseEntity.toString());
					dispatchReportList = responseEntity.getBody();
					System.out.println("dispatchReportList = " + dispatchReportList.toString());
				} else {
					ParameterizedTypeReference<List<DispatchReport>> typeRef = new ParameterizedTypeReference<List<DispatchReport>>() {
					};

					ResponseEntity<List<DispatchReport>> responseEntity = restTemplate.exchange(
							Constants.url + "getDispatchItemReportByOrder", HttpMethod.POST, new HttpEntity<>(map),
							typeRef);
					System.out.println("Items:" + responseEntity.toString());
					dispatchReportList = responseEntity.getBody();
					System.out.println("dispatchReportList = " + dispatchReportList.toString());
				}
				/*
				 * ParameterizedTypeReference<List<DispatchReport>> typeRef = new
				 * ParameterizedTypeReference<List<DispatchReport>>() { };
				 * 
				 * ResponseEntity<List<DispatchReport>> responseEntity = restTemplate.exchange(
				 * Constants.url + "getDispatchItemReport", HttpMethod.POST, new
				 * HttpEntity<>(map), typeRef);
				 * 
				 * dispatchReportList = responseEntity.getBody();
				 * System.out.println("dispatchReportList = " + dispatchReportList.toString());
				 */

				map = new LinkedMultiValueMap<String, Object>();
				map.add("catIdList", selectedCat);
				ParameterizedTypeReference<List<Item>> typeRef1 = new ParameterizedTypeReference<List<Item>>() {
				};

				ResponseEntity<List<Item>> responseEntity1 = restTemplate.exchange(
						Constants.url + "getItemsByCatIdForDisp", HttpMethod.POST, new HttpEntity<>(map), typeRef1);

				map = new LinkedMultiValueMap<String, Object>();
				map.add("catId", selectedCat);
				ParameterizedTypeReference<List<SubCategory>> typeRef2 = new ParameterizedTypeReference<List<SubCategory>>() {
				};

				ResponseEntity<List<SubCategory>> responseEntity2 = restTemplate
						.exchange(Constants.url + "getSubCatList", HttpMethod.POST, new HttpEntity<>(map), typeRef2);
				subCategoryList.addAll(responseEntity2.getBody());
				/*
				 * if(!dispatchReportList.isEmpty()&&!responseEntity1.getBody().isEmpty()&&!
				 * frNameIdByRouteIdList.isEmpty()) { for(int
				 * j=0;j<responseEntity1.getBody().size();j++) { for(int
				 * i=0;i<frNameIdByRouteIdList.size();i++) { boolean flag=false; for(int
				 * k=0;k<dispatchReportList.size();k++) {
				 * if(dispatchReportList.get(k).getFrId()==frNameIdByRouteIdList.get(i).getFrId(
				 * ) &&
				 * dispatchReportList.get(k).getItemId()==responseEntity1.getBody().get(j).getId
				 * ()) { flag=true; break;
				 * 
				 * } } if(flag==false) { DispatchReport dispachReport=new DispatchReport();
				 * dispachReport.setBillDetailNo(0);
				 * dispachReport.setFrId(frNameIdByRouteIdList.get(i).getFrId());
				 * dispachReport.setItemId(responseEntity1.getBody().get(j).getId());
				 * dispachReport.setBillQty(0); dispatchReportList.add(dispachReport); } } } }
				 */
				itemList.addAll(responseEntity1.getBody());
				model.addObject("dispatchReportList", dispatchReportList);
				model.addObject("frList", frNameIdByRouteIdList);
				model.addObject("itemList", responseEntity1.getBody());
				model.addObject("subCatList", responseEntity2.getBody());
			}
			try {
				// exportToExcel
				List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

				ExportToExcel expoExcel = new ExportToExcel();
				List<String> rowData = new ArrayList<String>();

				rowData.add("Sr.No.");

				rowData.add("Item Name");
				for (int i = 0; i < frNameIdByRouteIdList.size(); i++) {
					rowData.add("" + frNameIdByRouteIdList.get(i).getFrName());
				}
				rowData.add("Total");

				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);
				float allTotal = 0.0f;

				for (int i = 0; i < subCategoryList.size(); i++) {
					expoExcel = new ExportToExcel();
					rowData = new ArrayList<String>();
					rowData.add("");
					rowData.add("" + subCategoryList.get(i).getSubCatName());
					for (int j = 0; j < frNameIdByRouteIdList.size(); j++) {
						rowData.add("");
					}
					rowData.add("");
					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);

					int srNo = 1;
					for (int k = 0; k < itemList.size(); k++) {
						float total = 0.0f;
						float frTotal = 0.0f;
						if (itemList.get(k).getItemGrp2() == subCategoryList.get(i).getSubCatId()) {
							expoExcel = new ExportToExcel();
							rowData = new ArrayList<String>();
							rowData.add("" + srNo);
							srNo = srNo + 1;
							rowData.add("" + itemList.get(k).getItemName());
							for (int j = 0; j < frNameIdByRouteIdList.size(); j++) {
								float billQty = 0.0f;
								for (int l = 0; l < dispatchReportList.size(); l++) {
									if (dispatchReportList.get(l).getItemId() == itemList.get(k).getId()) {
										if (dispatchReportList.get(l).getFrId() == frNameIdByRouteIdList.get(j)
												.getFrId()) {
											billQty = dispatchReportList.get(l).getBillQty();
											total = total + dispatchReportList.get(l).getBillQty();
										}
									}
									frTotal = frTotal + dispatchReportList.get(l).getBillQty();
								}
								rowData.add("" + billQty);
							}
							rowData.add("" + total);
							allTotal = allTotal + total;
							expoExcel.setRowData(rowData);
							exportToExcelList.add(expoExcel);
						}
					}
				}
				for (int i = 0; i < subCategoryList.size(); i++) {
					expoExcel = new ExportToExcel();
					rowData = new ArrayList<String>();
					rowData.add("");
					rowData.add("" + subCategoryList.get(i).getSubCatName());
					float totalItems = 0.0f;
					for (int j = 0; j < frNameIdByRouteIdList.size(); j++) {
						float itemTotal = 0.0f;
						for (int l = 0; l < dispatchReportList.size(); l++) {
							if (dispatchReportList.get(l).getSubCatId() == subCategoryList.get(i).getSubCatId()) {
								if (dispatchReportList.get(l).getFrId() == frNameIdByRouteIdList.get(j).getFrId()) {
									itemTotal = itemTotal + dispatchReportList.get(l).getBillQty();
								}
							}
						}
						totalItems = totalItems + itemTotal;
						rowData.add("" + itemTotal);
					}
					rowData.add("" + totalItems);
					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);
				}

				expoExcel = new ExportToExcel();
				rowData = new ArrayList<String>();
				rowData.add("Total");
				rowData.add(" ");

				for (int j = 0; j < frNameIdByRouteIdList.size(); j++) {
					float itemTotal = 0.0f;
					for (int l = 0; l < dispatchReportList.size(); l++) {

						if (dispatchReportList.get(l).getFrId() == frNameIdByRouteIdList.get(j).getFrId()) {
							itemTotal = itemTotal + dispatchReportList.get(l).getBillQty();
						}
					}
					rowData.add("" + itemTotal);
				}
				rowData.add("" + allTotal);
				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);
				HttpSession session = request.getSession();
				session.setAttribute("exportExcelList", exportToExcelList);
				session.setAttribute("excelName", "DispatchReport");

			} catch (Exception e) {
				e.printStackTrace();
			}
			model.addObject("routeName", routeName);
		} catch (Exception e) {
			System.out.println("get Dispatch Report Exception: " + e.getMessage());
			e.printStackTrace();

		}
		return model;

	}

	// --------------------------------------------------------------------------------------------------

	@RequestMapping(value = "/getSaleReportRoyConsoByCat", method = RequestMethod.GET)
	public @ResponseBody RoyaltyListBeanConsByCat getSaleReportRoyConsoByCat(HttpServletRequest request,
			HttpServletResponse response) {
		String fromDate = "";
		String toDate = "";
		List<SalesReportBillwise> saleList = new ArrayList<>();
		List<SalesRoyaltyConsByCat> royaltyList = new ArrayList<>();
		RoyaltyListBeanConsByCat royaltyBean = new RoyaltyListBeanConsByCat();
		int getBy = 1;
		int type = 1;
		List<String> catList = new ArrayList<>();
		String frDisplay = "";
		RestTemplate restTemplate = new RestTemplate();

		try {
			System.out.println("Inside get Sale Bill Wise");
			String selectedFr = request.getParameter("fr_id_list");
			fromDate = request.getParameter("fromDate");
			toDate = request.getParameter("toDate");
			String routeId = request.getParameter("route_id");
			getBy = Integer.parseInt(request.getParameter("getBy"));
			type = Integer.parseInt(request.getParameter("type"));
			int isGraph = Integer.parseInt(request.getParameter("is_graph"));

			String selectedCat = request.getParameter("cat_id_list");

			boolean isAllFrSelected = false;
			boolean isAllCatSelected = false;
			selectedFr = selectedFr.substring(1, selectedFr.length() - 1);
			selectedFr = selectedFr.replaceAll("\"", "");

			// frList = new ArrayList<>();
			// frList = Arrays.asList(selectedFr);

			frList = Stream.of(selectedFr.split(",")).collect(Collectors.toList());

			selectedCat = selectedCat.substring(1, selectedCat.length() - 1);
			selectedCat = selectedCat.replaceAll("\"", "");

			catList.clear();
			catList = Stream.of(selectedCat.split(",")).collect(Collectors.toList());

			// List<String> catList = new ArrayList<>();
			// catList = Arrays.asList(selectedCat);

			if (!routeId.equalsIgnoreCase("0")) {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				// RestTemplate restTemplate = new RestTemplate();

				map.add("routeId", routeId);

				FrNameIdByRouteIdResponse frNameId = restTemplate.postForObject(Constants.url + "getFrNameIdByRouteId",
						map, FrNameIdByRouteIdResponse.class);

				List<FrNameIdByRouteId> frNameIdByRouteIdList = frNameId.getFrNameIdByRouteIds();

				System.out.println("route wise franchisee " + frNameIdByRouteIdList.toString());

				StringBuilder sbForRouteFrId = new StringBuilder();
				for (int i = 0; i < frNameIdByRouteIdList.size(); i++) {

					sbForRouteFrId = sbForRouteFrId.append(frNameIdByRouteIdList.get(i).getFrId().toString() + ",");

				}

				String strFrIdRouteWise = sbForRouteFrId.toString();
				selectedFr = strFrIdRouteWise.substring(0, strFrIdRouteWise.length() - 1);
				System.out.println("fr Id Route WISE = " + selectedFr);

			} // end of if

			if (frList.contains("-1")) {
				isAllFrSelected = true;
			}

			if (catList.contains("-1")) {
				isAllCatSelected = true;
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			// new code

			// few fr Selected

			map.add("catIdList", selectedCat);

			map.add("fromDate", fromDate);
			map.add("toDate", toDate);

			map.add("frIdList", selectedFr);
			map.add("getBy", getBy);
			map.add("type", type);

			ParameterizedTypeReference<List<SalesRoyaltyConsByCat>> typeRef = new ParameterizedTypeReference<List<SalesRoyaltyConsByCat>>() {
			};

			ResponseEntity<List<SalesRoyaltyConsByCat>> responseEntity = restTemplate.exchange(
					Constants.url + "getSaleRoyConsoByCatReportData", HttpMethod.POST, new HttpEntity<>(map), typeRef);

			royaltyList = responseEntity.getBody();
			royaltyConsByCatListForPdf = new ArrayList<>();

			royaltyConsByCatListForPdf = royaltyList;

			System.out.println("royaltyList List Bill Wise " + royaltyList.toString());

			CategoryListResponse categoryListResponse = restTemplate.getForObject(Constants.url + "showAllCategory",
					CategoryListResponse.class);
			List<MCategoryList> categoryList;

			categoryList = categoryListResponse.getmCategoryList();
			// allFrIdNameList = new AllFrIdNameList();
			System.out.println("Category list  " + categoryList);
			List<MCategoryList> tempList = new ArrayList<>();

			// royaltyBean.setCategoryList(categoryList);
			Map<Integer, String> catNameId = new HashMap<Integer, String>();

			for (int i = 0; i < categoryList.size(); i++) {

				for (int j = 0; j < royaltyList.size(); j++) {

					if (categoryList.get(i).getCatId() == royaltyList.get(j).getCatId()) {
						catNameId.put(categoryList.get(i).getCatId(), categoryList.get(i).getCatName());

						if (!tempList.contains(categoryList.get(i))) {

							tempList.add(categoryList.get(i));

						}
					}

				}
			}
			System.out.println("temp list " + categoryList.toString() + "size of t List " + categoryList.size());
			royaltyBean.setCategoryList(tempList);
			royaltyBean.setSalesReportRoyalty(royaltyList);
			staticRoyaltyBeanConsByCat = royaltyBean;

			List<Integer> frIds = Stream.of(selectedFr.split(",")).map(Integer::parseInt).collect(Collectors.toList());

			Set<String> set = new HashSet<>();

			if (allFrIdNameList.getFrIdNamesList() != null) {

				for (int j = 0; j < frIds.size(); j++) {

					for (int i = 0; i < allFrIdNameList.getFrIdNamesList().size(); i++) {

						if (allFrIdNameList.getFrIdNamesList().get(i).getFrId() == frIds.get(j)) {
							set.add(allFrIdNameList.getFrIdNamesList().get(i).getFrName());
							break;
						}

					}

				}
			}
			frDisplay = String.join(", ", set);

		} catch (Exception e) {
			System.out.println("get sale Report royaltyList by cat " + e.getMessage());
			e.printStackTrace();
		}

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("del", 0);

		ParameterizedTypeReference<List<GetItemSup>> typeRef = new ParameterizedTypeReference<List<GetItemSup>>() {
		};

		ResponseEntity<List<GetItemSup>> responseEntity = restTemplate.exchange(Constants.url + "getAllItemSupData",
				HttpMethod.POST, new HttpEntity<>(map), typeRef);

		List<GetItemSup> itemSupList = responseEntity.getBody();

		//System.err.println("ITEM SUP ----------*********************** > " + itemSupList);

		// exportToExcel
		List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

		ExportToExcel expoExcel = new ExportToExcel();
		List<String> rowData = new ArrayList<String>();

		rowData.add("Sr.No.");
		rowData.add("Category Name");
		rowData.add("Item Name");
		rowData.add("Actual Weight");
		rowData.add("Sale Qty");
		rowData.add("Sale Value");

		rowData.add("GRN Qty");
		rowData.add("GRN Value");
		rowData.add("GVN Qty");
		rowData.add("GVN Value");

		rowData.add("Net Qty");
		rowData.add("Net Value");

		rowData.add("Royalty %");
		rowData.add("Royalty Amt");

		rowData.add("Return % GRN");
		rowData.add("Return % GVN");
		rowData.add("Return % SUM");

		rowData.add("Contribution %");

		float royPer = getRoyPer();
		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);

		float saleQty = 0.0f;
		float saleValue = 0.0f;
		float grnQty = 0.0f;
		float grnValue = 0.0f;
		float gvnQty = 0.0f;
		float gvnValue = 0.0f;
		float netQtyTotal = 0.0f;
		float netValueTotal = 0.0f;
		float rAmtTotal = 0.0f;
		float contriTotal = 0.0f;

		float netValTotalForContri = 0.0f;

		if (!royaltyBean.getSalesReportRoyalty().isEmpty()) {

			for (int i = 0; i < royaltyList.size(); i++) {

				float billVal = 0, grnQty1 = 0, grnVal = 0, gvnQty1 = 0, gvnVal = 0;

				if (getBy == 1) {
					billVal = royaltyList.get(i).getTaxableAmt();

					if (type == 1) {
						grnVal = royaltyList.get(i).getGrnTaxableAmt();
						gvnVal = royaltyList.get(i).getGvnTaxableAmt();

					} else {
						grnVal = royaltyList.get(i).getCrnGrnTaxableAmt();
						gvnVal = royaltyList.get(i).getCrnGvnTaxableAmt();

					}
				} else {
					billVal = royaltyList.get(i).getGrandTotal();

					if (type == 1) {
						grnVal = royaltyList.get(i).getGrnGrandTotal();
						gvnVal = royaltyList.get(i).getGvnGrandTotal();

					} else {
						grnVal = royaltyList.get(i).getCrnGrnGrandTotal();
						gvnVal = royaltyList.get(i).getCrnGvnGrandTotal();

					}

				}

				float netValue = billVal - (grnVal + gvnVal);

				netValTotalForContri = netValTotalForContri + netValue;

			}

			for (int c = 0; c < catList.size(); c++) {

				int sr = 1;

				float saleQtyCat = 0.0f;
				float saleValueCat = 0.0f;
				float grnQtyCat = 0.0f;
				float grnValueCat = 0.0f;
				float gvnQtyCat = 0.0f;
				float gvnValueCat = 0.0f;
				float netQtyTotalCat = 0.0f;
				float netValueTotalCat = 0.0f;
				float rAmtTotalCat = 0.0f;

				for (int i = 0; i < royaltyList.size(); i++) {

					if (catList.get(c).equalsIgnoreCase(String.valueOf(royaltyList.get(i).getCatId()))) {

						int index = 1;

						float billVal = 0, grnQty1 = 0, grnVal = 0, gvnQty1 = 0, gvnVal = 0;

						if (getBy == 1) {
							billVal = royaltyList.get(i).getTaxableAmt();

							if (type == 1) {
								grnVal = royaltyList.get(i).getGrnTaxableAmt();
								gvnVal = royaltyList.get(i).getGvnTaxableAmt();

							} else {
								grnVal = royaltyList.get(i).getCrnGrnTaxableAmt();
								gvnVal = royaltyList.get(i).getCrnGvnTaxableAmt();

							}
						} else {
							billVal = royaltyList.get(i).getGrandTotal();

							if (type == 1) {
								grnVal = royaltyList.get(i).getGrnGrandTotal();
								gvnVal = royaltyList.get(i).getGvnGrandTotal();

							} else {
								grnVal = royaltyList.get(i).getCrnGrnGrandTotal();
								gvnVal = royaltyList.get(i).getCrnGvnGrandTotal();

							}

						}

						if (type == 1) {
							grnQty1 = royaltyList.get(i).getGrnQty();
							gvnQty1 = royaltyList.get(i).getGvnQty();
						} else {
							grnQty1 = royaltyList.get(i).getCrnGrnQty();
							gvnQty1 = royaltyList.get(i).getCrnGvnQty();
						}

						if (billVal > 0 || grnVal > 0 || gvnVal > 0) {

							expoExcel = new ExportToExcel();
							rowData = new ArrayList<String>();

							index = index + i;

							rowData.add("" + sr);
							sr = sr + 1;
							rowData.add("" + royaltyList.get(i).getCat_name());

							rowData.add("" + royaltyList.get(i).getItem_name());

							String baseWeight = "";
							try {
								for (int s = 0; s < itemSupList.size(); s++) {
									if (royaltyList.get(i).getId() == itemSupList.get(s).getItemId()) {
										baseWeight = "" + itemSupList.get(s).getActualWeight();
										//System.err.println("MATCH ====> " + itemSupList.get(s).getItemId());
										break;
									}
								}
							} catch (Exception e) {
								//System.err.println("ERROR -----------------------------> " + e.getMessage());
							}
							rowData.add("" + baseWeight);

							rowData.add("" + roundUp(royaltyList.get(i).getBillQty()));

							rowData.add("" + roundUp(billVal));

							rowData.add("" + roundUp(grnQty1));

							rowData.add("" + roundUp(grnVal));
							rowData.add("" + roundUp(gvnQty1));
							rowData.add("" + roundUp(gvnVal));

							float netQty = royaltyList.get(i).getBillQty() - (grnQty1 + gvnQty1);

							float netValue = billVal - (grnVal + gvnVal);
							// float royPer = getRoyPer();

							float rAmt = netValue * royPer / 100;

							rowData.add("" + roundUp(netQty));
							rowData.add("" + roundUp(netValue));
							rowData.add("" + roundUp(royPer));
							rowData.add("" + roundUp(rAmt));

							float grnRet = 0;
							float gvnRet = 0;
							float sumRet = 0;

							if (billVal > 0) {
								grnRet = (grnVal * 100) / billVal;
								gvnRet = (gvnVal * 100) / billVal;
								sumRet = ((grnVal + gvnVal) * 100) / billVal;
							}

							//System.err.println("GRN - " + grnVal);
							//System.err.println("GVN - " + gvnVal);
							//System.err.println("BILL - " + billVal);

							//System.err.println("RET GRN - " + grnRet);
							//System.err.println("RET GVN - " + gvnRet);
							//System.err.println("SUM - " + sumRet);

							rowData.add("" + grnRet);
							rowData.add("" + roundUp(gvnRet));
							rowData.add("" + roundUp(sumRet));

							float contri = (netValue * 100) / netValTotalForContri;
							contriTotal = contriTotal + contri;

							rowData.add("" + roundUp(contri));

							saleQtyCat = saleQtyCat + royaltyList.get(i).getBillQty();
							saleValueCat = saleValueCat + billVal;
							grnQtyCat = grnQtyCat + grnQty1;
							grnValueCat = grnValueCat + grnVal;
							gvnQtyCat = gvnQtyCat + gvnQty1;
							gvnValueCat = gvnValueCat + gvnVal;
							netQtyTotalCat = netQtyTotalCat + netQty;
							netValueTotalCat = netValueTotalCat + netValue;

							rAmtTotalCat = rAmtTotalCat + rAmt;

							saleQty = saleQty + royaltyList.get(i).getBillQty();
							saleValue = saleValue + billVal;
							grnQty = grnQty + grnQty1;
							grnValue = grnValue + grnVal;
							gvnQty = gvnQty + gvnQty1;
							gvnValue = gvnValue + gvnVal;
							netQtyTotal = netQtyTotal + netQty;
							netValueTotal = netValueTotal + netValue;

							rAmtTotal = rAmtTotal + rAmt;

							expoExcel.setRowData(rowData);
							exportToExcelList.add(expoExcel);

						}

					}

				}

				expoExcel = new ExportToExcel();
				rowData = new ArrayList<String>();

				rowData.add("");
				rowData.add("Total");
				rowData.add("");
				rowData.add("");

				rowData.add("" + roundUp(saleQtyCat));
				rowData.add("" + roundUp(saleValueCat));
				rowData.add("" + roundUp(grnQtyCat));
				rowData.add("" + roundUp(grnValueCat));
				rowData.add("" + roundUp(gvnQtyCat));
				rowData.add("" + roundUp(gvnValueCat));
				rowData.add("" + roundUp(netQtyTotalCat));
				rowData.add("" + roundUp(netValueTotalCat));
				rowData.add("");
				rowData.add("" + roundUp(rAmtTotalCat));

				float grnRet = (grnValueCat * 100) / saleValueCat;
				float gvnRet = (gvnValueCat * 100) / saleValueCat;
				float sumRet = ((grnValueCat + gvnValueCat) * 100) / saleValueCat;

				//System.err.println("************** > " + grnRet);

				if (new Float(grnRet).isNaN()) {
					grnRet = 0;
				}

				if (new Float(gvnRet).isNaN()) {
					gvnRet = 0;
				}

				if (new Float(sumRet).isNaN()) {
					sumRet = 0;
				}

				rowData.add("" + roundUp(grnRet));
				rowData.add("" + roundUp(gvnRet));
				rowData.add("" + roundUp(sumRet));

				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);

			}

		}

		expoExcel = new ExportToExcel();
		rowData = new ArrayList<String>();

		rowData.add("");
		rowData.add("Total");
		rowData.add("");
		rowData.add("");

		rowData.add("" + roundUp(saleQty));
		rowData.add("" + roundUp(saleValue));
		rowData.add("" + roundUp(grnQty));
		rowData.add("" + roundUp(grnValue));
		rowData.add("" + roundUp(gvnQty));
		rowData.add("" + roundUp(gvnValue));
		rowData.add("" + roundUp(netQtyTotal));
		rowData.add("" + roundUp(netValueTotal));
		rowData.add("");
		rowData.add("" + roundUp(rAmtTotal));

		float grnRet = (grnValue * 100) / saleValue;
		float gvnRet = (gvnValue * 100) / saleValue;
		float sumRet = ((grnValue + gvnValue) * 100) / saleValue;

		rowData.add("" + roundUp(grnRet));
		rowData.add("" + roundUp(gvnRet));
		rowData.add("" + roundUp(sumRet));

		rowData.add("" + roundUp(contriTotal));

		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);

		String searchBy = "", searchType = "";
		if (getBy == 1) {
			searchBy = "Taxable Amount";
		} else {
			searchBy = "Grand Total";
		}

		if (type == 1) {
			searchType = "GRN";
		} else {
			searchType = "CRN";
		}

		//System.out.println("exportToExcelList" + exportToExcelList);
		HttpSession session = request.getSession();
		session.setAttribute("exportExcelListNew", exportToExcelList);
		session.setAttribute("excelNameNew", "RoyaltyConsolidatedCatList");
		session.setAttribute("reportNameNew", "View Sales Royalty by Category  " + " From Date: " + fromDate
				+ "  To Date: " + toDate + "   And Search By: " + searchBy + "  and  " + searchType);
		session.setAttribute("searchByNew", "Franchisee: " + frDisplay);

		session.setAttribute("mergeUpto1", "$A$1:$R$1");
		session.setAttribute("mergeUpto2", "$A$2:$R$2");

		return royaltyBean;

	}

	@RequestMapping(value = "pdf/getSaleReportRoyConsoByCatPdf/{fromDate}/{toDate}/{selectedFr}/{routeId}/{selectedCat}/{isGraph}/{getBy}/{type}", method = RequestMethod.GET)
	public ModelAndView getSaleReportRoyConsoByCat(@PathVariable String fromDate, @PathVariable String toDate,
			@PathVariable String selectedFr, @PathVariable String routeId, @PathVariable String selectedCat,
			@PathVariable int isGraph, @PathVariable int getBy, @PathVariable int type, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView model = new ModelAndView("reports/sales/pdf/salesconsbycatPdf");

		List<SalesReportBillwise> saleList = new ArrayList<>();
		List<SalesRoyaltyConsByCat> royaltyList = new ArrayList<>();
		RoyaltyListBeanConsByCat royaltyBean = new RoyaltyListBeanConsByCat();
		boolean isAllFrSelected = false;
		boolean isAllCatSelected = false;
		try {
			System.out.println("Inside get Sale Bill Wise");

			if (!routeId.equalsIgnoreCase("0")) {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				RestTemplate restTemplate = new RestTemplate();

				map.add("routeId", routeId);

				FrNameIdByRouteIdResponse frNameId = restTemplate.postForObject(Constants.url + "getFrNameIdByRouteId",
						map, FrNameIdByRouteIdResponse.class);

				List<FrNameIdByRouteId> frNameIdByRouteIdList = frNameId.getFrNameIdByRouteIds();

				System.out.println("route wise franchisee " + frNameIdByRouteIdList.toString());

				StringBuilder sbForRouteFrId = new StringBuilder();
				for (int i = 0; i < frNameIdByRouteIdList.size(); i++) {

					sbForRouteFrId = sbForRouteFrId.append(frNameIdByRouteIdList.get(i).getFrId().toString() + ",");

				}

				String strFrIdRouteWise = sbForRouteFrId.toString();
				selectedFr = strFrIdRouteWise.substring(0, strFrIdRouteWise.length() - 1);
				System.out.println("fr Id Route WISE = " + selectedFr);

			} // end of if

			if (selectedFr.contains("-1")) {
				isAllFrSelected = true;
				model.addObject("fr", "All Franchisee");
			} else {

				List<Integer> frIds = Stream.of(selectedFr.split(",")).map(Integer::parseInt)
						.collect(Collectors.toList());

				Set<String> set = new HashSet<>();

				if (allFrIdNameList.getFrIdNamesList() != null) {

					for (int j = 0; j < frIds.size(); j++) {

						for (int i = 0; i < allFrIdNameList.getFrIdNamesList().size(); i++) {

							if (allFrIdNameList.getFrIdNamesList().get(i).getFrId() == frIds.get(j)) {
								set.add(allFrIdNameList.getFrIdNamesList().get(i).getFrName());
								break;
							}

						}

					}
				}
				String string = String.join(", ", set);
				model.addObject("fr", string);
			}

			if (selectedCat.contains("-1")) {
				isAllCatSelected = true;
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			// few fr Selected

			map.add("catIdList", selectedCat);

			map.add("fromDate", fromDate);
			map.add("toDate", toDate);

			map.add("frIdList", selectedFr);
			map.add("getBy", getBy);
			map.add("type", type);
			if (isGraph == 0) {
				ParameterizedTypeReference<List<SalesRoyaltyConsByCat>> typeRef = new ParameterizedTypeReference<List<SalesRoyaltyConsByCat>>() {
				};

				ResponseEntity<List<SalesRoyaltyConsByCat>> responseEntity = restTemplate.exchange(
						Constants.url + "getSaleRoyConsoByCatReportData", HttpMethod.POST, new HttpEntity<>(map),
						typeRef);

				royaltyList = responseEntity.getBody();
				royaltyConsByCatListForPdf = new ArrayList<>();

				royaltyConsByCatListForPdf = royaltyList;
			}

			System.out.println("royaltyList List Bill Wise " + royaltyList.toString());

			CategoryListResponse categoryListResponse = restTemplate.getForObject(Constants.url + "showAllCategory",
					CategoryListResponse.class);
			List<MCategoryList> categoryList;

			categoryList = categoryListResponse.getmCategoryList();
			// allFrIdNameList = new AllFrIdNameList();
			System.out.println("Category list  " + categoryList);
			List<MCategoryList> tempList = new ArrayList<>();

			// royaltyBean.setCategoryList(categoryList);
			Map<Integer, String> catNameId = new HashMap<Integer, String>();

			for (int i = 0; i < categoryList.size(); i++) {

				for (int j = 0; j < royaltyList.size(); j++) {

					if (categoryList.get(i).getCatId() == royaltyList.get(j).getCatId()) {
						catNameId.put(categoryList.get(i).getCatId(), categoryList.get(i).getCatName());

						if (!tempList.contains(categoryList.get(i))) {

							tempList.add(categoryList.get(i));

						}
					}

				}

				// }

				System.out.println("temp list " + tempList.toString() + "size of t List " + tempList.size());
				royaltyBean.setCategoryList(tempList);
				royaltyBean.setSalesReportRoyalty(royaltyList);
				model.addObject("royaltyList", royaltyBean);
				model.addObject("fromDate", fromDate);
				model.addObject("toDate", toDate);
				// model.addObject("FACTORYNAME", Constants.FACTORYNAME);
				// model.addObject("FACTORYADDRESS", Constants.FACTORYADDRESS);
				model.addObject("royPer", getRoyPer());

				model.addObject("getBy", getBy);
				model.addObject("type", type);

			}
		} catch (

		Exception e) {
			System.out.println("get sale Report royaltyList by cat " + e.getMessage());
			e.printStackTrace();

		}

		return model;

	}

	List<OrderDispatchRepDao> dispatchList = null;

	@RequestMapping(value = "/showOrderDispatchReport", method = RequestMethod.GET)
	public ModelAndView showOrderDispatchReport(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/orderDispReport");
		try {
			ZoneId z = ZoneId.of("Asia/Calcutta");
			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			todaysDate = date.format(formatters);
			String deliveryDate = request.getParameter("deliveryDate");

			RestTemplate restTemplate = new RestTemplate();
			CategoryListResponse categoryListResponse = restTemplate.getForObject(Constants.url + "showAllCategory",
					CategoryListResponse.class);
			List<MCategoryList> categoryList;
			categoryList = categoryListResponse.getmCategoryList();

			model.addObject("catList", categoryList);
			int flag = 0;
			if (deliveryDate != null) {
				String menuIdStr = "";
				List<Integer> menuIdList = new ArrayList<>();
				String[] menuId = request.getParameterValues("menuId");
				for (int i = 0; i < menuId.length; i++) {
					menuIdStr = menuIdStr + "," + menuId[i];
					menuIdList.add(Integer.parseInt(menuId[i]));
				}
				int catId = Integer.parseInt(request.getParameter("catId"));
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("catId", catId);
				map.add("menuId", menuIdStr);
				map.add("deliveryDate", DateConvertor.convertToYMD(deliveryDate));
				ParameterizedTypeReference<List<OrderDispatchRepDao>> typeRef = new ParameterizedTypeReference<List<OrderDispatchRepDao>>() {
				};

				ResponseEntity<List<OrderDispatchRepDao>> responseEntity = restTemplate.exchange(
						Constants.url + "getOrderDispatchReport", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				dispatchList = responseEntity.getBody();
				model.addObject("todaysDate", deliveryDate);
				model.addObject("dispatchList", dispatchList);

				FranchiseeAndMenuList franchiseeAndMenuList = restTemplate
						.getForObject(Constants.url + "getFranchiseeAndMenu", FranchiseeAndMenuList.class);

				model.addObject("menuList", franchiseeAndMenuList.getAllMenu());

				flag = 1;

				// exportToExcel
				List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

				ExportToExcel expoExcel = new ExportToExcel();
				List<String> rowData = new ArrayList<String>();

				rowData.add("Sr.No.");
				rowData.add("Item Name");
				rowData.add("Op Stock Qty");
				rowData.add("Order Qty");

				rowData.add("Take from Opening");
				rowData.add("Take from Fresh");
				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);
				if (!dispatchList.isEmpty()) {
					for (int i = 0; i < dispatchList.size(); i++) {
						int index = 1;
						index = index + i;
						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();

						rowData.add("" + index);
						rowData.add("" + dispatchList.get(i).getItemName());
						rowData.add("" + dispatchList.get(i).getOpTotal());
						rowData.add("" + dispatchList.get(i).getOrderQty());
						float op = 0;
						float fresh = 0;
						if (dispatchList.get(i).getOrderQty() <= dispatchList.get(i).getOpTotal()
								&& dispatchList.get(i).getOrderQty() > 0) {
							op = dispatchList.get(i).getOrderQty();
							fresh = 0;
						} else if (dispatchList.get(i).getOrderQty() > dispatchList.get(i).getOpTotal()) {
							fresh = dispatchList.get(i).getOrderQty() - dispatchList.get(i).getOpTotal();
							op = dispatchList.get(i).getOpTotal();
						}
						rowData.add("" + op);
						rowData.add("" + fresh);
						expoExcel.setRowData(rowData);
						exportToExcelList.add(expoExcel);

					}
				}

				HttpSession session = request.getSession();
				session.setAttribute("exportExcelList", exportToExcelList);
				session.setAttribute("excelName", "OrderDispatchReport");
				model.addObject("catId", catId);
				model.addObject("menuIdList", menuIdList);
			} else {
				model.addObject("todaysDate", todaysDate);
			}
			model.addObject("flag", flag);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/showOrderDispatchReportPdf", method = RequestMethod.GET)
	public void showOrderDispatchReportPdf(HttpServletRequest request, HttpServletResponse response) {

		BufferedOutputStream outStream = null;
		Document document = new Document(PageSize.A4, 20, 20, 150, 30);

		String FILE_PATH = Constants.REPORT_SAVE;
		File file = new File(FILE_PATH);

		PdfWriter writer = null;

		FileOutputStream out = null;
		try {
			out = new FileOutputStream(FILE_PATH);
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			String header = "";
			/*
			 * String header = "                                           Galdhar Foods\n"
			 * + "          Factory Add: Plot No.48,Chikalthana Midc, Aurangabad." +
			 * "\n              Phone:0240-2466217, Email: aurangabad@monginis.net";
			 */

			String title = "                Order Dispatch Report";

			DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
			String reportDate = DF.format(new Date());

			writer = PdfWriter.getInstance(document, out);

			ItextPageEvent event = new ItextPageEvent(header, title, reportDate);

			writer.setPageEvent(event);

		} catch (DocumentException e) {

			e.printStackTrace();
		}

		PdfPTable table = new PdfPTable(6);
		try {
			System.out.println("Inside PDF Table try");
			table.setWidthPercentage(100);
			table.setWidths(new float[] { 0.4f, 1.7f, 0.9f, 1.0f, 0.9f, 0.9f });
			Font headFont = new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
			Font headFont1 = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
			Font f = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.UNDERLINE, BaseColor.BLUE);
			headFont1.setColor(BaseColor.WHITE);

			PdfPCell hcell = new PdfPCell();
			hcell.setBackgroundColor(BaseColor.PINK);
			hcell.setPaddingTop(4);
			hcell.setPaddingBottom(4);
			hcell = new PdfPCell(new Phrase("Sr.", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Item Name", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Op Stock Qty", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Order Qty", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Take From Opening", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Take From Fresh", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);
			table.addCell(hcell);

			float opStockQty = 0;
			float orderQty = 0;
			float takeFromOp = 0;
			float takeFromFresh = 0;
			int index = 0;
			if (!dispatchList.isEmpty()) {
				for (OrderDispatchRepDao dispatch : dispatchList) {
					index++;
					PdfPCell cell;

					cell = new PdfPCell(new Phrase(String.valueOf(index), headFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setPadding(4);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase(dispatch.getItemName(), headFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell.setPaddingRight(2);
					cell.setPadding(4);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase(String.valueOf(dispatch.getOpTotal()), headFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					cell.setPaddingRight(2);
					cell.setPadding(4);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase(String.valueOf(dispatch.getOrderQty()), headFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					cell.setPaddingRight(2);
					cell.setPadding(4);
					table.addCell(cell);
					float fresh = 0;
					float op = 0;
					if (dispatch.getOrderQty() <= dispatch.getOpTotal() && dispatch.getOrderQty() > 0) {
						op = dispatch.getOrderQty();
						fresh = 0;
					} else if (dispatch.getOrderQty() > dispatch.getOpTotal()) {
						fresh = dispatch.getOrderQty() - dispatch.getOpTotal();
						op = dispatch.getOpTotal();
					}
					cell = new PdfPCell(new Phrase(String.valueOf(op), headFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					cell.setPaddingRight(2);
					cell.setPadding(4);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase(String.valueOf(fresh), headFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					cell.setPaddingRight(2);
					cell.setPadding(4);

					table.addCell(cell);

					opStockQty = opStockQty + dispatch.getOpTotal();
					orderQty = orderQty + dispatch.getOrderQty();
					takeFromOp = takeFromOp + op;
					takeFromFresh = takeFromFresh + fresh;
				}
			}
			PdfPCell cell;

			cell = new PdfPCell(new Phrase("", headFont));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setPadding(4);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("Total:", headFont));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingRight(2);
			cell.setPadding(4);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(String.valueOf("" + opStockQty), headFont));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingRight(2);
			cell.setPadding(4);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(String.valueOf("" + orderQty), headFont));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingRight(2);
			cell.setPadding(4);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(String.valueOf("" + takeFromOp), headFont));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingRight(2);
			cell.setPadding(4);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(String.valueOf("" + takeFromFresh), headFont));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingRight(2);
			cell.setPadding(4);

			table.addCell(cell);

			document.open();
			document.add(table);
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

			System.out.println("Pdf Generation Error:Disp Order Report" + ex.getMessage());

			ex.printStackTrace();

		}

	}

	// pdf function
	private Dimension format = PD4Constants.A4;
	private boolean landscapeValue = false;
	private int topValue = 8;
	private int leftValue = 0;
	private int rightValue = 0;
	private int bottomValue = 8;
	private String unitsValue = "m";
	private String proxyHost = "";
	private int proxyPort = 0;

	private int userSpaceWidth = 750;
	private static int BUFFER_SIZE = 1024;

	@RequestMapping(value = "/pdfForReport", method = RequestMethod.GET)
	public void showPDF(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Inside PDf For Report URL ");
		String url = request.getParameter("url");
		System.out.println("URL " + url);

		File f = new File(Constants.REPORT_SAVE);
		// File f = new File("/home/ats-12/report.pdf");

		try {
			runConverter(Constants.ReportURL + url, f, request, response);
			// runConverter("www.google.com", f,request,response);

		} catch (IOException e) {

			System.out.println("Pdf conversion exception " + e.getMessage());
		}

		// get absolute path of the application
		ServletContext context = request.getSession().getServletContext();
		String appPath = context.getRealPath("");
		String filePath = Constants.REPORT_SAVE;

		// String filePath ="/home/ats-12/report.pdf";

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

	private void runConverter(String urlstring, File output, HttpServletRequest request, HttpServletResponse response)
			throws IOException {

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
				pd4ml.enableSmartTableBreaks(true);

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

	@RequestMapping(value = "/pdfForDisReport", method = RequestMethod.GET)
	public void showPDF1(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Inside PDf For Report URL ");
		String url = request.getParameter("url");
		System.out.println("URL " + url);

		File f = new File(Constants.REPORT_SAVE);
		// File f = new File("/opt/tomcat-latest/webapps/uploads/report.pdf");

		try {
			runConverter1(Constants.ReportURL + url, f, request, response);
			// runConverter("www.google.com", f,request,response);

		} catch (IOException e) {

			System.out.println("Pdf conversion exception " + e.getMessage());
		}

		// get absolute path of the application
		ServletContext context = request.getSession().getServletContext();
		String appPath = context.getRealPath("");
		String filePath = Constants.REPORT_SAVE;

		// String filePath = "/opt/tomcat-latest/webapps/uploads/report.pdf";

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

	private void runConverter1(String urlstring, File output, HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		if (urlstring.length() > 0) {
			if (!urlstring.startsWith("http://") && !urlstring.startsWith("file:")) {
				urlstring = "http://" + urlstring;
			}
			System.out.println("PDF URL " + urlstring);
			java.io.FileOutputStream fos = new java.io.FileOutputStream(output);

			PD4ML pd4ml = new PD4ML();
			pd4ml.enableSmartTableBreaks(true);
			try {

				try {
					pd4ml.setPageSize(landscapeValue ? pd4ml.changePageOrientation(format) : format);
				} catch (Exception e) {
					e.printStackTrace();
				}

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

	/********************************************************************************/
	// Mahendra

	@RequestMapping(value = "/showMonthlySalesReturnValueWiseReport", method = RequestMethod.GET)
	public ModelAndView showMonthlySalesValueWiseReport(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;

		model = new ModelAndView("reports/sales/monthwisesalesreturnvalue");
		RestTemplate restTemplate = new RestTemplate();

		try {
			String year = request.getParameter("year");

			if (year != "") {
				String[] yrs = year.split("-"); // returns an array with the 2 parts

				MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();

				map.add("fromYear", yrs[0]);
				map.add("toYear", yrs[1]);

				SalesReturnValueDaoList[] salesReturnValueReportListRes = restTemplate.postForObject(
						Constants.url + "getSalesReturnValueReport", map, SalesReturnValueDaoList[].class);

				List<SalesReturnValueDaoList> salesReturnValueReportList = new ArrayList<SalesReturnValueDaoList>(
						Arrays.asList(salesReturnValueReportListRes));

				SubCategory[] subCategoryList = restTemplate.getForObject(Constants.url + "getAllSubCatList",
						SubCategory[].class);

				ArrayList<SubCategory> subCatList = new ArrayList<SubCategory>(Arrays.asList(subCategoryList));

				LinkedHashMap<Integer, SalesReturnValueDaoList> salesReturnValueReport = new LinkedHashMap<>();

				for (int i = 0; i < salesReturnValueReportList.size(); i++) {
					salesReturnValueReport.put(i, salesReturnValueReportList.get(i));
					float totBillAmt = 0;
					float totGrnValue = 0;
					float totGvnValue = 0;
					for (int k = 0; k < salesReturnValueReportList.get(i).getSalesReturnQtyValueList().size(); k++) {
						totBillAmt = totBillAmt
								+ salesReturnValueReportList.get(i).getSalesReturnQtyValueList().get(k).getGrandTotal();
						totGrnValue = totGrnValue
								+ salesReturnValueReportList.get(i).getSalesReturnQtyValueList().get(k).getGrnQty();
						totGvnValue = totGvnValue
								+ salesReturnValueReportList.get(i).getSalesReturnQtyValueList().get(k).getGvnQty();
					}
					salesReturnValueReportList.get(i).setTotBillAmt(totBillAmt);
					salesReturnValueReportList.get(i).setTotGrnQty(totGrnValue);
					salesReturnValueReportList.get(i).setTotGvnQty(totGvnValue);
				}

				model.addObject("salesReturnValueReport", salesReturnValueReport);
				model.addObject("subCatList", subCatList);

				// exportToExcel
				List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

				ExportToExcel expoExcel = new ExportToExcel();
				List<String> rowData = new ArrayList<String>();
				rowData.add("Sr.");
				rowData.add("Group Name");
				for (int i = 0; i < salesReturnValueReport.size(); i++) {
					rowData.add(salesReturnValueReport.get(i).getMonth() + " Gross Sale");
					rowData.add(salesReturnValueReport.get(i).getMonth() + " GVN Value");
					rowData.add(salesReturnValueReport.get(i).getMonth() + " GRN Value");
					rowData.add(salesReturnValueReport.get(i).getMonth() + " Total");
				}
				rowData.add("Total Gross Sale");
				rowData.add("Total GRN Value");
				rowData.add("Total GVN Value");
				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);
				float totBillAmt = 0.0f;
				float totGrnAmt = 0.0f;
				float totGvnAmt = 0.0f;
				for (int k = 0; k < subCatList.size(); k++) {

					float grandTotal = 0.0f;
					float grnQty = 0.0f;
					float gvnQty = 0.0f;

					expoExcel = new ExportToExcel();
					rowData = new ArrayList<String>();
					rowData.add("" + (k + 1));
					rowData.add("" + subCatList.get(k).getSubCatName());
					for (int i = 0; i < salesReturnValueReport.size(); i++) {
						for (int j = 0; j < salesReturnValueReport.get(i).getSalesReturnQtyValueList().size(); j++) {

							if (salesReturnValueReport.get(i).getSalesReturnQtyValueList().get(j)
									.getSubCatId() == subCatList.get(k).getSubCatId()) {
								rowData.add("" + roundUp(salesReturnValueReport.get(i).getSalesReturnQtyValueList()
										.get(j).getGrandTotal()));
								rowData.add("" + roundUp(
										salesReturnValueReport.get(i).getSalesReturnQtyValueList().get(j).getGvnQty()));
								rowData.add("" + roundUp(
										salesReturnValueReport.get(i).getSalesReturnQtyValueList().get(j).getGrnQty()));
								rowData.add("" + roundUp(salesReturnValueReport.get(i).getSalesReturnQtyValueList()
										.get(j).getGrandTotal()
										- (salesReturnValueReport.get(i).getSalesReturnQtyValueList().get(j).getGvnQty()
												+ salesReturnValueReport.get(i).getSalesReturnQtyValueList().get(j)
														.getGrnQty())));
								grandTotal = grandTotal + salesReturnValueReport.get(i).getSalesReturnQtyValueList()
										.get(j).getGrandTotal();
								grnQty = grnQty
										+ salesReturnValueReport.get(i).getSalesReturnQtyValueList().get(j).getGrnQty();
								gvnQty = gvnQty
										+ salesReturnValueReport.get(i).getSalesReturnQtyValueList().get(j).getGvnQty();
							}

						}
					}
					rowData.add("" + roundUp(grandTotal));
					rowData.add("" + roundUp(grnQty));
					rowData.add("" + roundUp(gvnQty));

					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);
					totBillAmt = totBillAmt + grandTotal;
					totGrnAmt = totGrnAmt + grnQty;
					totGvnAmt = totGvnAmt + gvnQty;

				}

				expoExcel = new ExportToExcel();
				rowData = new ArrayList<String>();
				rowData.add("");
				rowData.add("Total");
				for (int i = 0; i < salesReturnValueReport.size(); i++) {
					rowData.add("" + roundUp(salesReturnValueReport.get(i).getTotBillAmt()));
					rowData.add("" + roundUp(salesReturnValueReport.get(i).getTotGvnQty()));
					rowData.add("" + roundUp(salesReturnValueReport.get(i).getTotGrnQty()));
					rowData.add(roundUp((salesReturnValueReport.get(i).getTotBillAmt()
							- (salesReturnValueReport.get(i).getTotGvnQty()
									+ salesReturnValueReport.get(i).getTotGrnQty())))
							+ "");
				}
				rowData.add("" + totBillAmt);
				rowData.add("" + totGrnAmt);
				rowData.add("" + totGvnAmt);
				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);
				System.err.println("exportToExcelList" + exportToExcelList.toString());
				HttpSession session = request.getSession();
				session.setAttribute("exportExcelList", exportToExcelList);
				session.setAttribute("excelName", "MonthlySalesReturnValueReport");

			}
		} catch (Exception e) {

		}

		return model;

	}

	// Subcategory Summary Report
	@RequestMapping(value = "/showSaleReportBySubCategory", method = RequestMethod.GET)
	public ModelAndView showSaleReportBySubCategory(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		HttpSession session = request.getSession();

		model = new ModelAndView("reports/sales/saleRepBySubCatOnly");

		try {
			ZoneId z = ZoneId.of("Asia/Calcutta");

			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			todaysDate = date.format(formatters);

			model.addObject("todaysDate", todaysDate);

		} catch (Exception e) {

			System.out.println("Exc in show sales report bill wise  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	List<SubCatReport> saleList = new ArrayList<>();

	@RequestMapping(value = "/getSubCatReport", method = RequestMethod.GET)
	public @ResponseBody List<SubCatReport> getSubCatReport(HttpServletRequest request, HttpServletResponse response) {

		saleList = new ArrayList<>();
		saleList.clear();

		String fromDate = "";
		String toDate = "";
		try {

			fromDate = request.getParameter("fromDate");
			toDate = request.getParameter("toDate");
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			RestTemplate restTemplate = new RestTemplate();

			map.add("fromDate", fromDate);
			map.add("toDate", toDate);

			ParameterizedTypeReference<List<SubCatReport>> typeRef = new ParameterizedTypeReference<List<SubCatReport>>() {
			};
			ResponseEntity<List<SubCatReport>> responseEntity = restTemplate
					.exchange(Constants.url + "getSubCatReportApi", HttpMethod.POST, new HttpEntity<>(map), typeRef);

			saleList = responseEntity.getBody();

			for (int i = 0; i < saleList.size(); i++) {

				float netQty = saleList.get(i).getSoldQty()
						- (saleList.get(i).getVarQty() + saleList.get(i).getRetQty());
				float netAmt = saleList.get(i).getSoldAmt()
						- (saleList.get(i).getVarAmt() + saleList.get(i).getRetAmt());
				float retAmtPer = (((saleList.get(i).getVarAmt() + saleList.get(i).getRetAmt()) * 100)
						/ saleList.get(i).getSoldAmt());

				saleList.get(i).setNetQty(netQty);
				saleList.get(i).setNetAmt(netAmt);
				saleList.get(i).setRetAmtPer(retAmtPer);
			}

			System.err.println("SALE - " + saleList);

			// exportToExcel
			List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

			ExportToExcel expoExcel = new ExportToExcel();
			List<String> rowData = new ArrayList<String>();

			rowData.add("Sr");
			rowData.add("Sub Category Name");
			rowData.add("Pur Qty");
			rowData.add("Pur Amt");

			rowData.add("Var Qty");
			rowData.add("Var Amt");

			rowData.add("Ret Qty");
			rowData.add("Ret Amt");

			rowData.add("Net Qty");
			rowData.add("Net Amt");

			rowData.add("Ret Per Amt%");
			rowData.add("Contribution %");

			expoExcel.setRowData(rowData);
			int srno = 1;
			exportToExcelList.add(expoExcel);

			float totalSoldQty = 0;
			float totalSoldAmt = 0;
			float totalVarQty = 0;
			float totalVarAmt = 0;
			float totalRetQty = 0;
			float totalRetAmt = 0;
			float totalNetQty = 0;
			float totalNetAmt = 0;
			float retAmtPer = 0;
			float contriTotal = 0;

			float netTotalForContri = 0;
			for (int i = 0; i < saleList.size(); i++) {
				netTotalForContri = netTotalForContri + saleList.get(i).getNetAmt();
			}

			for (int i = 0; i < saleList.size(); i++) {

				totalSoldQty = totalSoldQty + saleList.get(i).getSoldQty();
				totalSoldAmt = totalSoldAmt + saleList.get(i).getSoldAmt();
				totalVarQty = totalVarQty + saleList.get(i).getVarQty();
				totalVarAmt = totalVarAmt + saleList.get(i).getVarAmt();
				totalRetQty = totalRetQty + saleList.get(i).getRetQty();
				totalRetAmt = totalRetAmt + saleList.get(i).getRetAmt();
				totalNetQty = totalNetQty + saleList.get(i).getNetQty();
				totalNetAmt = totalNetAmt + saleList.get(i).getNetAmt();

				if (!Double.isNaN(saleList.get(i).getRetAmtPer())) {
					retAmtPer = retAmtPer + saleList.get(i).getRetAmtPer();
				}

				expoExcel = new ExportToExcel();
				rowData = new ArrayList<String>();

				rowData.add("" + srno);
				rowData.add(saleList.get(i).getSubCatName());

				rowData.add("" + roundUp(saleList.get(i).getSoldQty()));
				rowData.add("" + roundUp(saleList.get(i).getSoldAmt()));
				rowData.add("" + roundUp(saleList.get(i).getVarQty()));
				rowData.add("" + roundUp(saleList.get(i).getVarAmt()));
				rowData.add("" + roundUp(saleList.get(i).getRetQty()));

				rowData.add("" + roundUp(saleList.get(i).getRetAmt()));
				rowData.add("" + roundUp(saleList.get(i).getNetQty()));
				rowData.add("" + roundUp(saleList.get(i).getNetAmt()));

				if (Double.isNaN(saleList.get(i).getRetAmtPer())) {
					rowData.add("0.00%");
				} else {
					rowData.add("" + roundUp(saleList.get(i).getRetAmtPer()) + "%");
				}

				float contri = (saleList.get(i).getNetAmt() * 100) / netTotalForContri;
				contriTotal = contriTotal + contri;

				rowData.add("" + contri);

				srno = srno + 1;

				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);

			}
			expoExcel = new ExportToExcel();
			rowData = new ArrayList<String>();
			rowData.add(" ");
			rowData.add("Total");

			rowData.add("" + roundUp(totalSoldQty));
			rowData.add("" + roundUp(totalSoldAmt));
			rowData.add("" + roundUp(totalVarQty));
			rowData.add("" + roundUp(totalVarAmt));
			rowData.add("" + roundUp(totalRetQty));
			rowData.add("" + roundUp(totalRetAmt));

			rowData.add("" + roundUp(totalNetQty));
			rowData.add("" + roundUp(totalNetAmt));
			rowData.add("" + roundUp(retAmtPer) + "%");
			rowData.add("" + roundUp(contriTotal));

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);

			HttpSession session = request.getSession();
			session.setAttribute("exportExcelListNew", exportToExcelList);
			session.setAttribute("excelNameNew", "SubCategoryWise_Summary_Report");
			session.setAttribute("reportNameNew", "Sub Category Wise Summary Report");
			session.setAttribute("searchByNew", "From Date: " + fromDate + "  To Date: " + toDate + " ");
			session.setAttribute("mergeUpto1", "$A$1:$K$1");
			session.setAttribute("mergeUpto2", "$A$2:$K$2");

		} catch (Exception e) {
			// TODO: handle exception
		}

		return saleList;
	}

	@RequestMapping(value = "pdf/showSaleReportBySubCatPdf/{fromDate}/{toDate}  ", method = RequestMethod.GET)
	public ModelAndView showSaleReportBySubCatPdf(@PathVariable String fromDate, @PathVariable String toDate,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("reports/sales/pdf/saleRepBySubCatOnlyPdf");

		List<SubCatReport> subCatReportList = new ArrayList<>();

		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			map.add("fromDate", fromDate);
			map.add("toDate", toDate);

			ParameterizedTypeReference<List<SubCatReport>> typeRef = new ParameterizedTypeReference<List<SubCatReport>>() {
			};
			ResponseEntity<List<SubCatReport>> responseEntity = restTemplate
					.exchange(Constants.url + "getSubCatReportApi", HttpMethod.POST, new HttpEntity<>(map), typeRef);

			subCatReportList = responseEntity.getBody();

			for (int i = 0; i < subCatReportList.size(); i++) {

				float netQty = subCatReportList.get(i).getSoldQty()
						- (subCatReportList.get(i).getVarQty() + subCatReportList.get(i).getRetQty());
				float netAmt = subCatReportList.get(i).getSoldAmt()
						- (subCatReportList.get(i).getVarAmt() + subCatReportList.get(i).getRetAmt());
				float retAmtPer = (((subCatReportList.get(i).getVarAmt() + subCatReportList.get(i).getRetAmt()) * 100)
						/ subCatReportList.get(i).getSoldAmt());

				subCatReportList.get(i).setNetQty(netQty);
				subCatReportList.get(i).setNetAmt(netAmt);
				subCatReportList.get(i).setRetAmtPer(retAmtPer);
			}

			model.addObject("subCatReportList", subCatReportList);
			// model.addObject("FACTORYNAME", Constants.FACTORYNAME);
			// model.addObject("FACTORYADDRESS", Constants.FACTORYADDRESS);
			model.addObject("fromDate", fromDate);
			model.addObject("toDate", toDate);

		} catch (

		Exception e) {
			System.out.println("get sale Report Bill Wise " + e.getMessage());
			e.printStackTrace();

		}

		return model;
	}
	/*
	 * @RequestMapping(value = "/getSaleReportBySubCatAndFr", method =
	 * RequestMethod.GET) public @ResponseBody SubCatFrReportList
	 * getSaleReportBySubCatAndFr(HttpServletRequest request, HttpServletResponse
	 * response) {
	 * 
	 * SubCatFrReportList subCatFrReportListData = new SubCatFrReportList();
	 * 
	 * List<SubCatFrReport> subCatFrReportList = new ArrayList<>();
	 * List<AllFrIdName> frListFinal = new ArrayList<>();
	 * 
	 * String fromDate = ""; String toDate = ""; try {
	 * System.out.println("Inside get Sale Bill Wise"); String selectedFr =
	 * request.getParameter("fr_id_list"); String selectedSubCatIdList =
	 * request.getParameter("subCat_id_list"); fromDate =
	 * request.getParameter("fromDate"); toDate = request.getParameter("toDate");
	 * 
	 * System.out.println("selectedFrBefore------------------" + selectedFr);
	 * 
	 * selectedFr = selectedFr.substring(1, selectedFr.length() - 1); selectedFr =
	 * selectedFr.replaceAll("\"", "");
	 * 
	 * selectedSubCatIdList = selectedSubCatIdList.substring(1,
	 * selectedSubCatIdList.length() - 1); selectedSubCatIdList =
	 * selectedSubCatIdList.replaceAll("\"", "");
	 * 
	 * System.out.println("selectedFrAfter------------------" + selectedFr);
	 * 
	 * MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,
	 * Object>(); RestTemplate restTemplate = new RestTemplate();
	 * 
	 * System.out.println("Inside If all fr Selected ");
	 * 
	 * map.add("fromDate", fromDate); map.add("toDate", toDate); map.add("frIdList",
	 * selectedFr); map.add("subCatIdList", selectedSubCatIdList);
	 * System.out.println(map); ParameterizedTypeReference<List<SubCatFrReport>>
	 * typeRef = new ParameterizedTypeReference<List<SubCatFrReport>>() { };
	 * ResponseEntity<List<SubCatFrReport>> responseEntity = restTemplate
	 * .exchange(Constants.url + "getSubCatFrReportApi", HttpMethod.POST, new
	 * HttpEntity<>(map), typeRef);
	 * 
	 * subCatFrReportList = responseEntity.getBody();
	 * 
	 * for (int i = 0; i < subCatFrReportList.size(); i++) {
	 * 
	 * float netQty = subCatFrReportList.get(i).getSoldQty() -
	 * (subCatFrReportList.get(i).getVarQty() +
	 * subCatFrReportList.get(i).getRetQty()); float netAmt =
	 * subCatFrReportList.get(i).getSoldAmt() -
	 * (subCatFrReportList.get(i).getVarAmt() +
	 * subCatFrReportList.get(i).getRetAmt()); float retAmtPer =
	 * (((subCatFrReportList.get(i).getVarAmt() +
	 * subCatFrReportList.get(i).getRetAmt()) 100) /
	 * subCatFrReportList.get(i).getSoldAmt());
	 * 
	 * subCatFrReportList.get(i).setNetQty(netQty);
	 * subCatFrReportList.get(i).setNetAmt(netAmt);
	 * subCatFrReportList.get(i).setRetAmtPer(retAmtPer); }
	 * 
	 * allFrIdNameList = restTemplate.getForObject(Constants.url + "getAllFrIdName",
	 * AllFrIdNameList.class);
	 * 
	 * TreeSet<Integer> frIdListFinal = new TreeSet<Integer>(); for (int j = 0; j <
	 * subCatFrReportList.size(); j++) {
	 * frIdListFinal.add(subCatFrReportList.get(j).getFrId()); } for (int frId :
	 * frIdListFinal) { for (int j = 0; j <
	 * allFrIdNameList.getFrIdNamesList().size(); j++) { if
	 * (allFrIdNameList.getFrIdNamesList().get(j).getFrId() == frId) {
	 * frListFinal.add(allFrIdNameList.getFrIdNamesList().get(j));
	 * 
	 * } } }
	 * 
	 * subCatFrReportListData.setFrList(frListFinal);
	 * subCatFrReportListData.setSubCatFrReportList(subCatFrReportList);
	 * 
	 * System.out.println(
	 * "subCatFrReportList*********************************************" +
	 * subCatFrReportListData.toString());
	 * 
	 * } catch (Exception e) { System.out.println("get sale Report Bill Wise " +
	 * e.getMessage()); e.printStackTrace();
	 * 
	 * }
	 * 
	 * List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();
	 * 
	 * ExportToExcel expoExcel = new ExportToExcel(); List<String> rowData = new
	 * ArrayList<String>();
	 * 
	 * rowData.add("Sr"); rowData.add("Sub Category Name"); rowData.add("Sold Qty");
	 * rowData.add("Sold Amt"); rowData.add("Var Qty"); rowData.add("Var Amt");
	 * rowData.add("Ret Qty"); rowData.add("Ret Amt"); rowData.add("Net Qty");
	 * rowData.add("Net Amt"); rowData.add("Ret Per Amt");
	 * 
	 * expoExcel.setRowData(rowData); int srno = 1;
	 * exportToExcelList.add(expoExcel);
	 * 
	 * for (int j = 0; j < frListFinal.size(); j++) {
	 * 
	 * float totalSoldQty = 0; float totalSoldAmt = 0; float totalVarQty = 0; float
	 * totalVarAmt = 0; float totalRetQty = 0; float totalRetAmt = 0; float
	 * totalNetQty = 0; float totalNetAmt = 0; float retAmtPer = 0;
	 * 
	 * expoExcel = new ExportToExcel(); rowData = new ArrayList<String>();
	 * 
	 * rowData.add(""); rowData.add("" + frListFinal.get(j).getFrName());
	 * rowData.add(""); rowData.add(""); rowData.add(""); rowData.add("");
	 * rowData.add(""); rowData.add(""); rowData.add(""); rowData.add("");
	 * rowData.add("");
	 * 
	 * expoExcel.setRowData(rowData); exportToExcelList.add(expoExcel);
	 * 
	 * for (int i = 0; i < subCatFrReportList.size(); i++) {
	 * 
	 * if (frListFinal.get(j).getFrId() == subCatFrReportList.get(i).getFrId()) {
	 * 
	 * expoExcel = new ExportToExcel(); rowData = new ArrayList<String>();
	 * 
	 * totalSoldQty = totalSoldQty + subCatFrReportList.get(i).getSoldQty();
	 * totalSoldAmt = totalSoldAmt + subCatFrReportList.get(i).getSoldAmt();
	 * totalVarQty = totalVarQty + subCatFrReportList.get(i).getVarQty();
	 * totalVarAmt = totalVarAmt + subCatFrReportList.get(i).getVarAmt();
	 * totalRetQty = totalRetQty + subCatFrReportList.get(i).getRetQty();
	 * totalRetAmt = totalRetAmt + subCatFrReportList.get(i).getRetAmt();
	 * totalNetQty = totalNetQty + subCatFrReportList.get(i).getNetQty();
	 * totalNetAmt = totalNetAmt + subCatFrReportList.get(i).getNetQty(); retAmtPer
	 * = retAmtPer + subCatFrReportList.get(i).getRetAmtPer();
	 * 
	 * expoExcel = new ExportToExcel(); rowData = new ArrayList<String>();
	 * 
	 * rowData.add("" + srno);
	 * rowData.add(subCatFrReportList.get(i).getSubCatName());
	 * 
	 * rowData.add("" + roundUp(subCatFrReportList.get(i).getSoldQty()));
	 * rowData.add("" + roundUp(subCatFrReportList.get(i).getSoldQty()));
	 * rowData.add("" + roundUp(subCatFrReportList.get(i).getVarQty()));
	 * rowData.add("" + roundUp(subCatFrReportList.get(i).getVarAmt()));
	 * rowData.add("" + roundUp(subCatFrReportList.get(i).getRetQty()));
	 * 
	 * rowData.add("" + roundUp(subCatFrReportList.get(i).getRetAmt()));
	 * rowData.add("" + roundUp(subCatFrReportList.get(i).getNetQty()));
	 * rowData.add("" + roundUp(subCatFrReportList.get(i).getNetQty()));
	 * rowData.add("" + roundUp(subCatFrReportList.get(i).getRetAmtPer()));
	 * 
	 * srno = srno + 1;
	 * 
	 * expoExcel.setRowData(rowData); exportToExcelList.add(expoExcel); } }
	 * 
	 * expoExcel = new ExportToExcel(); rowData = new ArrayList<String>();
	 * rowData.add(" "); rowData.add("Total");
	 * 
	 * rowData.add("" + roundUp(totalSoldQty)); rowData.add("" +
	 * roundUp(totalSoldAmt)); rowData.add("" + roundUp(totalVarQty));
	 * rowData.add("" + roundUp(totalVarAmt)); rowData.add("" +
	 * roundUp(totalRetQty)); rowData.add("" + roundUp(totalRetAmt));
	 * 
	 * rowData.add("" + roundUp(totalNetQty)); rowData.add("" +
	 * roundUp(totalNetAmt)); rowData.add("" + roundUp(retAmtPer));
	 * 
	 * expoExcel.setRowData(rowData); exportToExcelList.add(expoExcel); }
	 * 
	 * HttpSession session = request.getSession();
	 * session.setAttribute("exportExcelListNew", exportToExcelList);
	 * session.setAttribute("excelNameNew", "FrSubCatReport");
	 * session.setAttribute("reportNameNew",
	 * "Sales Report (Franchisee SubCategory Wise)");
	 * session.setAttribute("searchByNew", "From Date: " + fromDate + "  To Date: "
	 * + toDate + " "); session.setAttribute("mergeUpto1", "$A$1:$K$1");
	 * session.setAttribute("mergeUpto2", "$A$2:$K$2");
	 * 
	 * return subCatFrReportListData; }
	 */

	@RequestMapping(value = "pdf/showSummeryFrAndSubCatPdf/{fromDate}/{toDate}/{selectedFr}/{selectedSubCatIdList} ", method = RequestMethod.GET)
	public ModelAndView showSummeryFrAndSubCatPdf(@PathVariable String fromDate, @PathVariable String toDate,
			@PathVariable String selectedFr, @PathVariable String selectedSubCatIdList, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView model = new ModelAndView("reports/sales/pdf/saleRepBySubCatPdf");

		SubCatFrReportList subCatFrReportListData = new SubCatFrReportList();

		List<SubCatFrReport> subCatFrReportList = new ArrayList<>();
		List<AllFrIdName> frListFinal = new ArrayList<>();

		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			map.add("fromDate", fromDate);
			map.add("toDate", toDate);
			map.add("frIdList", selectedFr);
			map.add("subCatIdList", selectedSubCatIdList);

			System.out.println(map);
			ParameterizedTypeReference<List<SubCatFrReport>> typeRef = new ParameterizedTypeReference<List<SubCatFrReport>>() {
			};
			ResponseEntity<List<SubCatFrReport>> responseEntity = restTemplate
					.exchange(Constants.url + "getSubCatFrReportApi", HttpMethod.POST, new HttpEntity<>(map), typeRef);

			subCatFrReportList = responseEntity.getBody();

			for (int i = 0; i < subCatFrReportList.size(); i++) {

				float netQty = subCatFrReportList.get(i).getSoldQty()
						- (subCatFrReportList.get(i).getVarQty() + subCatFrReportList.get(i).getRetQty());
				float netAmt = subCatFrReportList.get(i).getSoldAmt()
						- (subCatFrReportList.get(i).getVarAmt() + subCatFrReportList.get(i).getRetAmt());
				float retAmtPer = (((subCatFrReportList.get(i).getVarAmt() + subCatFrReportList.get(i).getRetAmt())
						* 100) / subCatFrReportList.get(i).getSoldAmt());

				subCatFrReportList.get(i).setNetQty(netQty);
				subCatFrReportList.get(i).setNetAmt(netAmt);
				subCatFrReportList.get(i).setRetAmtPer(retAmtPer);
			}

			allFrIdNameList = restTemplate.getForObject(Constants.url + "getAllFrIdName", AllFrIdNameList.class);

			TreeSet<Integer> frIdListFinal = new TreeSet<Integer>();
			for (int j = 0; j < subCatFrReportList.size(); j++) {
				frIdListFinal.add(subCatFrReportList.get(j).getFrId());
			}
			for (int frId : frIdListFinal) {
				for (int j = 0; j < allFrIdNameList.getFrIdNamesList().size(); j++) {
					if (allFrIdNameList.getFrIdNamesList().get(j).getFrId() == frId) {
						frListFinal.add(allFrIdNameList.getFrIdNamesList().get(j));

					}
				}
			}

			subCatFrReportListData.setFrList(frListFinal);
			subCatFrReportListData.setSubCatFrReportList(subCatFrReportList);

			System.out.println(frListFinal.toString());
			System.out.println(subCatFrReportList.toString());

			model.addObject("subCatFrReportListData", subCatFrReportListData);
			model.addObject("frList", frListFinal);
			model.addObject("subCatFrReportList", subCatFrReportList);
			// model.addObject("FACTORYNAME", Constants.FACTORYNAME);
			// model.addObject("FACTORYADDRESS", Constants.FACTORYADDRESS);
			model.addObject("fromDate", fromDate);
			model.addObject("toDate", toDate);

		} catch (

		Exception e) {
			System.out.println("get sale Report Bill Wise " + e.getMessage());
			e.printStackTrace();

		}

		return model;
	}

	@RequestMapping(value = "/getSalesReportV2", method = RequestMethod.GET)
	public ModelAndView getSalesReportV2(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/v2/fr_sales_report");

		try {

			RestTemplate restTemplate = new RestTemplate();

			ZoneId z = ZoneId.of("Asia/Calcutta");

			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			String todaysDate = date.format(formatters);

			allFrIdNameList = restTemplate.getForObject(Constants.url + "getAllFranchises", AllFrIdNameList.class);

			model.addObject("todaysDate", todaysDate);
			model.addObject("allFrIdNameList", allFrIdNameList.getFrIdNamesList());

		} catch (Exception e) {
			System.out.println("Exce in showRegCakeSpOrderReport " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	List<SalesReport> saleReportList;

	@RequestMapping(value = "/getSalesReport", method = RequestMethod.GET)
	public @ResponseBody List<SalesReport> callGetRegCakeAsSp(HttpServletRequest request, HttpServletResponse response)
			throws FileNotFoundException {

		String frIdString = "";
		String fromDate = "";
		String toDate = "";

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		System.out.println("inside getSalesReport ajax call");

		frIdString = request.getParameter("fr_id_list");
		fromDate = request.getParameter("fromDate");
		toDate = request.getParameter("toDate");
		try {

			frIdString = frIdString.substring(1, frIdString.length() - 1);
			frIdString = frIdString.replaceAll("\"", "");

			List<String> franchIds = new ArrayList();

			franchIds = Arrays.asList(frIdString);
			if (franchIds.contains("-1")) {
				map.add("frIdList", -1);

			} else {

				map.add("frIdList", frIdString);
			}
			System.err.println("frId string " + frIdString);
			map.add("fromDate", DateConvertor.convertToYMD(fromDate));

			map.add("toDate", DateConvertor.convertToYMD(toDate));

			SalesReport[] saleRepArray = restTemplate.postForObject(Constants.url + "getSalesReportV2", map,
					SalesReport[].class);
			saleReportList = new ArrayList<>(Arrays.asList(saleRepArray));

			System.err.println("saleReportList " + saleReportList.toString());

			List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

			ExportToExcel expoExcel = new ExportToExcel();
			List<String> rowData = new ArrayList<String>();

			rowData.add("Sr. No.");
			rowData.add("Party Code");
			rowData.add("Party Name");
			rowData.add("Sales");
			rowData.add("G.V.N");
			rowData.add("NET Value");
			rowData.add("G.R.N");
			rowData.add("NET Value");
			rowData.add("In Lakh");
			rowData.add("Return %");

			float salesValue = 0;
			float gvn = 0.0f;
			float gvnNetValue = 0.0f;
			float grn = 0.0f;
			float grnNetValue = 0.0f;
			float inLaskTotal = 0.0f;
			float returnTotal = 0.0f;

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);
			for (int i = 0; i < saleReportList.size(); i++) {
				expoExcel = new ExportToExcel();
				rowData = new ArrayList<String>();
				rowData.add("" + (i + 1));
				rowData.add("" + saleReportList.get(i).getFrCode());
				rowData.add("" + saleReportList.get(i).getFrName());
				rowData.add("" + roundUp(saleReportList.get(i).getSaleValue()));
				rowData.add("" + roundUp(saleReportList.get(i).getGvnValue()));
				float netVal1 = (saleReportList.get(i).getSaleValue()) - (saleReportList.get(i).getGvnValue());
				float netVal2 = (netVal1) - (saleReportList.get(i).getGrnValue());
				float inLac = (netVal2) / 100000;
				float retPer = 0.0f;
				if (saleReportList.get(i).getGrnValue() > 0) {
					retPer = ((saleReportList.get(i).getGrnValue()) / (saleReportList.get(i).getSaleValue() / 100));
				}
				rowData.add("" + roundUp(netVal1));
				rowData.add("" + roundUp(saleReportList.get(i).getGrnValue()));
				rowData.add("" + roundUp(netVal2));
				rowData.add("" + roundUp(inLac));
				rowData.add("" + roundUp(retPer));

				salesValue = salesValue + saleReportList.get(i).getSaleValue();
				gvn = gvn + saleReportList.get(i).getGvnValue();
				gvnNetValue = gvnNetValue + netVal1;
				grn = grn + saleReportList.get(i).getGrnValue();
				grnNetValue = grnNetValue + netVal2;
				inLaskTotal = inLaskTotal + inLac;
				returnTotal = returnTotal + retPer;

				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);

			}

			expoExcel = new ExportToExcel();
			rowData = new ArrayList<String>();

			rowData.add("");
			rowData.add("Total");
			rowData.add("");

			rowData.add("" + roundUp(salesValue));
			rowData.add("" + roundUp(gvn));
			rowData.add("" + roundUp(gvnNetValue));
			rowData.add("" + roundUp(grn));
			rowData.add("" + roundUp(grnNetValue));
			rowData.add("" + roundUp(inLaskTotal));
			rowData.add("" + roundUp(returnTotal));

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);

			HttpSession session = request.getSession();
			session.setAttribute("exportExcelListNew", exportToExcelList);
			session.setAttribute("excelNameNew", "SalesReport");
			session.setAttribute("reportNameNew", "Sales Report By Franchise");
			session.setAttribute("searchByNew", "From Date: " + fromDate + "  To Date: " + toDate + " ");
			session.setAttribute("mergeUpto1", "$A$1:$L$1");
			session.setAttribute("mergeUpto2", "$A$2:$L$2");

		} catch (Exception e) {

			e.printStackTrace();

		}
		return saleReportList;
	}

	// postProductionPlanDetaillist
	@RequestMapping(value = "/getSalesReportPdf", method = RequestMethod.GET)
	public void showProdByOrderPdf(HttpServletRequest request, HttpServletResponse response)
			throws FileNotFoundException {

		Document document = new Document(PageSize.A4);
		document.setPageSize(PageSize.A4.rotate());
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

		PdfPTable table = new PdfPTable(9);
		try {
			table.setHeaderRows(1);
			System.out.println("Inside PDF Table try");
			table.setWidthPercentage(100);
			table.setWidths(new float[] { 0.7f, 3.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f });
			Font headFont = new Font(FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
			Font headFont1 = new Font(FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK);
			Font f = new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.UNDERLINE, BaseColor.BLUE);

			PdfPCell hcell;
			hcell = new PdfPCell(new Phrase("Sr.No.", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Party Name", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Sales", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("GVN", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("NET Value", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("GRN", headFont1)); // Varience title replaced with P2 Production
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("NET Value", headFont1)); // Varience title replaced with P2 Production
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("IN Lacs", headFont1)); // Varience title replaced with P2 Production
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Return%", headFont1)); // Varience title replaced with P2 Production
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);
			table.addCell(hcell);
			float totalSaleValue = 0.0f;
			float totalGvnValue = 0.0f;
			float totalNetVal1 = 0.0f;
			float totalGrnValue = 0.0f;
			float totalNetVal2 = 0.0f;
			float totalInLac = 0.0f;
			float totalRetPer = 0.0f;
			int index = 0;
			for (int j = 0; j < saleReportList.size(); j++) {

				index++;
				PdfPCell cell;

				cell = new PdfPCell(new Phrase(String.valueOf(index), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				float netVal1 = (saleReportList.get(j).getSaleValue()) - (saleReportList.get(j).getGvnValue());
				float netVal2 = (netVal1) - (saleReportList.get(j).getGrnValue());
				float inLac = (netVal2) / 100000;
				float retPer = 0.0f;
				if (saleReportList.get(j).getGrnValue() > 0) {
					retPer = ((saleReportList.get(j).getGrnValue()) / (saleReportList.get(j).getSaleValue() / 100));
				}

				totalSaleValue = totalSaleValue + saleReportList.get(j).getSaleValue();
				totalGvnValue = totalGvnValue + saleReportList.get(j).getGvnValue();
				totalNetVal1 = totalNetVal1 + netVal1;
				totalGrnValue = totalGrnValue + saleReportList.get(j).getGrnValue();
				totalNetVal2 = totalNetVal2 + netVal2;
				totalInLac = totalInLac + inLac;
				totalRetPer = totalRetPer + retPer;

				cell = new PdfPCell(new Phrase(String.valueOf(saleReportList.get(j).getFrName()), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(8);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(roundUp(saleReportList.get(j).getSaleValue()) + "", headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(8);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(roundUp(saleReportList.get(j).getGvnValue()) + "", headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(8);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(String.valueOf(roundUp(netVal1)), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(8);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(String.valueOf(roundUp(saleReportList.get(j).getGrnValue())), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(8);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(String.valueOf(roundUp(netVal1)), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(8);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(String.valueOf(roundUp(inLac)), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(8);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(String.valueOf(roundUp(retPer)), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(8);
				table.addCell(cell);

			}

			PdfPCell cell;

			cell = new PdfPCell(new Phrase("Total", headFont1));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("", headFont1));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			double d = Double.parseDouble("" + totalSaleValue);
			String strTotalSaleValue = String.format("%f", d);

			cell = new PdfPCell(new Phrase("" + strTotalSaleValue, headFont1));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(cell);

			double d1 = Double.parseDouble("" + totalGvnValue);
			String strTotalGvnValue = String.format("%f", d1);

			cell = new PdfPCell(new Phrase("" + strTotalGvnValue, headFont1));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(cell);

			double d2 = Double.parseDouble("" + totalNetVal1);
			String strTotalNetVal1 = String.format("%f", d2);

			cell = new PdfPCell(new Phrase("" + strTotalNetVal1, headFont1));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(cell);

			double d3 = Double.parseDouble("" + totalGrnValue);
			String strTotalGrnValue = String.format("%f", d3);

			cell = new PdfPCell(new Phrase("" + strTotalGrnValue, headFont1));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(cell);

			double d4 = Double.parseDouble("" + totalNetVal2);
			String strTotalNetVal2 = String.format("%f", d4);

			cell = new PdfPCell(new Phrase("" + strTotalNetVal2, headFont1));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(cell);

			double d5 = Double.parseDouble("" + totalInLac);
			String strTotalInLac = String.format("%f", d5);

			cell = new PdfPCell(new Phrase("" + strTotalInLac, headFont1));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(cell);

			double d6 = Double.parseDouble("" + totalRetPer);
			String strTotalRetPer = String.format("%f", d6);

			cell = new PdfPCell(new Phrase("" + strTotalRetPer, headFont1));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(cell);

			document.open();

			Paragraph heading = new Paragraph("Sales Report");
			heading.setAlignment(Element.ALIGN_CENTER);
			document.add(heading);

			DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
			String reportDate = DF.format(new Date());

			document.add(new Paragraph("\n"));

			document.add(table);

			document.close();

			if (file != null) {

				String mimeType = URLConnection.guessContentTypeFromName(file.getName());

				if (mimeType == null) {

					mimeType = "application/pdf";

				}

				response.setContentType(mimeType);

				response.addHeader("content-disposition", String.format("inline; filename=\"%s\"", file.getName()));

				// response.setHeader("Content-Disposition", String.format("attachment;
				// filename=\"%s\"", file.getName()));

				response.setContentLength((int) file.length());

				InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

				try {
					FileCopyUtils.copy(inputStream, response.getOutputStream());
				} catch (IOException e) {
					System.out.println("Excep in Opening a Pdf File");
					e.printStackTrace();
				}

			}

		} catch (DocumentException ex) {

			System.out.println("Pdf Generation Error: Prod From Orders" + ex.getMessage());

			ex.printStackTrace();

		}
	}

	@RequestMapping(value = "/showMonthlySalesPercentageReport", method = RequestMethod.GET)
	public ModelAndView showMonthlySalesPercentageReport(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		String fromYear = "", toYear = "";

		model = new ModelAndView("reports/sales/monthwisesalespercentage");
		RestTemplate restTemplate = new RestTemplate();

		try {
			String year = request.getParameter("year");

			if (year != "") {
				String[] yrs = year.split("-"); // returns an array with the 2 parts

				MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();

				map.add("fromYear", yrs[0]);
				map.add("toYear", yrs[1]);

				fromYear = yrs[0];
				toYear = yrs[1];

				SalesReturnValueDaoList[] salesReturnValueReportListRes = restTemplate.postForObject(
						Constants.url + "getSalesReturnValueReport", map, SalesReturnValueDaoList[].class);

				List<SalesReturnValueDaoList> salesReturnValueReportList = new ArrayList<SalesReturnValueDaoList>(
						Arrays.asList(salesReturnValueReportListRes));

				SubCategory[] subCategoryList = restTemplate.getForObject(Constants.url + "getAllSubCatList",
						SubCategory[].class);

				ArrayList<SubCategory> subCatList = new ArrayList<SubCategory>(Arrays.asList(subCategoryList));

				LinkedHashMap<Integer, SalesReturnValueDaoList> salesReturnValueReport = new LinkedHashMap<>();

				for (int i = 0; i < salesReturnValueReportList.size(); i++) {
					salesReturnValueReport.put(i, salesReturnValueReportList.get(i));
					float totBillAmt = 0;
					float totGrnValue = 0;
					float totGvnValue = 0;
					for (int k = 0; k < salesReturnValueReportList.get(i).getSalesReturnQtyValueList().size(); k++) {
						totBillAmt = totBillAmt
								+ salesReturnValueReportList.get(i).getSalesReturnQtyValueList().get(k).getGrandTotal();
						totGrnValue = totGrnValue
								+ salesReturnValueReportList.get(i).getSalesReturnQtyValueList().get(k).getGrnQty();
						totGvnValue = totGvnValue
								+ salesReturnValueReportList.get(i).getSalesReturnQtyValueList().get(k).getGvnQty();
					}
					salesReturnValueReportList.get(i).setTotBillAmt(totBillAmt);
					salesReturnValueReportList.get(i).setTotGrnQty(totGrnValue);
					salesReturnValueReportList.get(i).setTotGvnQty(totGvnValue);
				}

				System.out.println("LIst===========" + salesReturnValueReportList.toString());

				model.addObject("salesReturnValueReport", salesReturnValueReport);
				model.addObject("subCatList", subCatList);

				// exportToExcel

				List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

				ExportToExcel expoExcel = new ExportToExcel();
				List<String> rowData = new ArrayList<String>();
				rowData.add("Sr.");
				rowData.add("Group Name");
				for (int i = 0; i < salesReturnValueReport.size(); i++) {
					rowData.add(salesReturnValueReport.get(i).getMonth() + " Total");
					rowData.add(salesReturnValueReport.get(i).getMonth() + "%");
				}
				rowData.add("Total");
				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);

				for (int k = 0; k < subCatList.size(); k++) {

					expoExcel = new ExportToExcel();
					rowData = new ArrayList<String>();
					rowData.add("" + (k + 1));
					rowData.add("" + subCatList.get(k).getSubCatName());

					float horizontalTotal = 0.0f;

					for (int i = 0; i < salesReturnValueReport.size(); i++) {
						for (int j = 0; j < salesReturnValueReport.get(i).getSalesReturnQtyValueList().size(); j++) {

							if (salesReturnValueReport.get(i).getSalesReturnQtyValueList().get(j)
									.getSubCatId() == subCatList.get(k).getSubCatId()) {

								rowData.add("" + roundUp(salesReturnValueReport.get(i).getSalesReturnQtyValueList()
										.get(j).getGrandTotal()
										- (salesReturnValueReport.get(i).getSalesReturnQtyValueList().get(j).getGvnQty()
												+ salesReturnValueReport.get(i).getSalesReturnQtyValueList().get(j)
														.getGrnQty())));

								horizontalTotal = horizontalTotal
										+ salesReturnValueReport.get(i).getSalesReturnQtyValueList().get(j)
												.getGrandTotal()
										- (salesReturnValueReport.get(i).getSalesReturnQtyValueList().get(j).getGvnQty()
												+ salesReturnValueReport.get(i).getSalesReturnQtyValueList().get(j)
														.getGrnQty());

								if (salesReturnValueReport.get(i).getTotBillAmt() > 0) {
									rowData.add("" + roundUp(((salesReturnValueReport.get(i)
											.getSalesReturnQtyValueList().get(j).getGrandTotal()
											- (salesReturnValueReport.get(i).getSalesReturnQtyValueList().get(j)
													.getGvnQty()
													+ salesReturnValueReport.get(i).getSalesReturnQtyValueList().get(j)
															.getGrnQty()))
											* 100) / salesReturnValueReport.get(i).getTotBillAmt()));
								} else {
									rowData.add("0.00");
								}
							}
						}
					}

					rowData.add("" + roundUp(horizontalTotal));

					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);
				}

				expoExcel = new ExportToExcel();
				rowData = new ArrayList<String>();
				rowData.add("");
				rowData.add("Total");
				for (int i = 0; i < salesReturnValueReport.size(); i++) {
					rowData.add("" + salesReturnValueReport.get(i).getTotBillAmt());
					if (salesReturnValueReport.get(i).getTotBillAmt() > 0) {
						rowData.add("100.00");
					} else {
						rowData.add("0.00");
					}

				}

				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);

				System.err.println("exportToExcelList" + exportToExcelList.toString());
				// HttpSession session = request.getSession();
				// session.setAttribute("exportExcelList", exportToExcelList);
				// session.setAttribute("excelName", "MonthlySalesPerContrReport");

				HttpSession session = request.getSession();
				session.setAttribute("exportExcelListNew", exportToExcelList);
				session.setAttribute("excelNameNew", "SubCategoryWise_Contribution_Report");
				session.setAttribute("reportNameNew", "Sub Category Wise Contribution Report");
				session.setAttribute("searchByNew", "Year : " + fromYear + " To " + toYear + " ");
				session.setAttribute("mergeUpto1", "$A$1:$K$1");
				session.setAttribute("mergeUpto2", "$A$2:$K$2");

			}
		} catch (Exception e) {

		}
		// }
		return model;

	}
	
	
	
	@RequestMapping(value = "/showTcsReportByDate", method = RequestMethod.GET)
	public ModelAndView showTcsReportByDate(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/tcsbydate");

		try {
			ZoneId z = ZoneId.of("Asia/Calcutta");

			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			todaysDate = date.format(formatters);

			RestTemplate restTemplate = new RestTemplate();			

			allFrIdNameList = new AllFrIdNameList();
			try {

				allFrIdNameList = restTemplate.getForObject(Constants.url + "getAllFranchises", AllFrIdNameList.class);
				//getAllFrIdName
			} catch (Exception e) {
				System.out.println("Exception in getAllFrIdName" + e.getMessage());
				e.printStackTrace();

			}			

			System.out.println(" Fr " + allFrIdNameList.getFrIdNamesList());

			model.addObject("todaysDate", todaysDate);
			model.addObject("unSelectedFrList", allFrIdNameList.getFrIdNamesList());

		} catch (Exception e) {

			System.out.println("Exc in show sales report bill wise  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}
	
	@RequestMapping(value = "/getTcsFrDatewise", method = RequestMethod.GET)
	public @ResponseBody List<SalesReportBillwise> getTcsFrDatewise(HttpServletRequest request,
			HttpServletResponse response) {

		List<SalesReportBillwise> saleList = new ArrayList<>();
		String fromDate = "";
		String toDate = "";
		try {
			System.out.println("Inside get TCS Report Fr Wise");
			String selectedFr = request.getParameter("fr_id_list");
			fromDate = request.getParameter("fromDate");
			toDate = request.getParameter("toDate");
			
			boolean isAllFrSelected = false;
			selectedFr = selectedFr.substring(1, selectedFr.length() - 1);
			selectedFr = selectedFr.replaceAll("\"", "");

			frList = new ArrayList<>();
			frList = Arrays.asList(selectedFr);

			if (frList.contains("-1")) {
				isAllFrSelected = true;
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			if (isAllFrSelected) {

				System.out.println("Inside If all fr Selected ");

				map.add("fromDate", fromDate);
				map.add("toDate", toDate);
				ParameterizedTypeReference<List<SalesReportBillwise>> typeRef = new ParameterizedTypeReference<List<SalesReportBillwise>>() {
				};
				ResponseEntity<List<SalesReportBillwise>> responseEntity = restTemplate.exchange(
						Constants.url + "getTcsReportByAllFr", HttpMethod.POST, new HttpEntity<>(map),
						typeRef);

				saleList = responseEntity.getBody();
				saleListForPdf = new ArrayList<>();
				saleListForPdf = saleList;
				System.out.println("sales List Bill Wise " + saleList.toString());

			} else {
				System.out.println("Inside else Few fr Selected ");

				map.add("frIdList", selectedFr);
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				ParameterizedTypeReference<List<SalesReportBillwise>> typeRef = new ParameterizedTypeReference<List<SalesReportBillwise>>() {
				};
				ResponseEntity<List<SalesReportBillwise>> responseEntity = restTemplate.exchange(
						Constants.url + "getTcsReportSelectedFr", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				saleList = responseEntity.getBody();
				saleListForPdf = new ArrayList<>();
				saleListForPdf = saleList;
				System.out.println("sales List Bill Wise " + saleList.toString());

			}
		} catch (Exception e) {
			System.out.println("get sale Report Bill Wise " + e.getMessage());
			e.printStackTrace();

		}
		
		float ttlTaxable = 0;
		float ttlCgst = 0;
		float ttlSgst = 0;
		float ttlIgst = 0;
		float ttlTcs = 0;
		float ttlTax = 0;
		
		float total = 0;
		float grandTotal = 0;

		// exportToExcel
		List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

		ExportToExcel expoExcel = new ExportToExcel();
		List<String> rowData = new ArrayList<String>();

		rowData.add("Bill No");
		rowData.add("Invoice No");
		rowData.add("Bill Date");
		rowData.add("Franchisee Code");
		rowData.add("Franchisee Name");
		rowData.add("Franchisee City");
		rowData.add("Franchisee Gst No");
		rowData.add("Sgst Sum");
		rowData.add("Cgst Sum");
		rowData.add("Igst Sum");
		rowData.add("Total Tax");
		rowData.add("Taxable Amt");
		rowData.add("Tcs Amt");
		rowData.add("Grand Total");

		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);
		for (int i = 0; i < saleList.size(); i++) {
			expoExcel = new ExportToExcel();
			rowData = new ArrayList<String>();
			
			total = saleList.get(i).getTaxableAmt()+saleList.get(i).getCgstSum()+saleList.get(i).getSgstSum()+saleList.get(i).getRoundOff();

			rowData.add("" + saleList.get(i).getBillNo());
			rowData.add(saleList.get(i).getInvoiceNo());
			rowData.add(saleList.get(i).getBillDate());

			rowData.add("" + saleList.get(i).getFrId());
			rowData.add(saleList.get(i).getFrName());

			rowData.add(saleList.get(i).getFrCity());
			rowData.add(saleList.get(i).getFrGstNo());
			rowData.add("" + saleList.get(i).getSgstSum());
			rowData.add("" + saleList.get(i).getCgstSum());
			rowData.add("" + saleList.get(i).getIgstSum());
			rowData.add("" + saleList.get(i).getTotalTax());
			rowData.add("" + saleList.get(i).getTaxableAmt());
			rowData.add("" + saleList.get(i).getRoundOff());
			rowData.add("" + total);			
			
			ttlTaxable = ttlTaxable + saleList.get(i).getTaxableAmt();
			ttlCgst = ttlCgst + saleList.get(i).getCgstSum();
			ttlSgst = ttlSgst + saleList.get(i).getCgstSum();
			ttlIgst = ttlIgst + saleList.get(i).getIgstSum();
			ttlTcs = ttlTcs + saleList.get(i).getRoundOff();
			ttlTax = ttlTax + saleList.get(i).getTotalTax();
			grandTotal = grandTotal + total;
			
			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);
			
			

		}
		
		expoExcel = new ExportToExcel();
		rowData = new ArrayList<String>();

		rowData.add("Total");
		rowData.add("");
		rowData.add("");
		rowData.add("");
		rowData.add("");
		rowData.add("");
		rowData.add("");
		
		rowData.add("" + roundUp(ttlSgst));
		rowData.add("" + roundUp(ttlCgst));
		rowData.add("" + roundUp(ttlIgst));
		rowData.add("" + roundUp(ttlTax));
		rowData.add("" + roundUp(ttlTaxable));
		rowData.add("" + roundUp(ttlTcs));
		rowData.add("" + roundUp(grandTotal));
		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);


		HttpSession session = request.getSession();
		session.setAttribute("exportExcelListNew", exportToExcelList);
		session.setAttribute("excelNameNew", "TCSReport");
		session.setAttribute("reportNameNew", "TCS Report");
		session.setAttribute("searchByNew", "From Date: " + fromDate + "  To Date: " + toDate + " ");
		session.setAttribute("mergeUpto1", "$A$1:$M$1");
		session.setAttribute("mergeUpto2", "$A$2:$M$2");

		return saleList;
	}
	
	@RequestMapping(value = "pdf/showTcsReportByDatePdf/{fDate}/{tDate}/{selectedFr}", method = RequestMethod.GET)
	public ModelAndView showTcsReportByDatePdf(@PathVariable String fDate, @PathVariable String tDate,
			@PathVariable String selectedFr, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/pdf/tcsbydatePdf");

		List<SalesReportBillwise> saleList = new ArrayList<>();

		boolean isAllFrSelected = false;
		try {

			if (selectedFr.equalsIgnoreCase("-1")) {
				isAllFrSelected = true;
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			if (isAllFrSelected) {

				System.out.println("Inside If all fr Selected ");

				map.add("fromDate", fDate);
				map.add("toDate", tDate);
				ParameterizedTypeReference<List<SalesReportBillwise>> typeRef = new ParameterizedTypeReference<List<SalesReportBillwise>>() {
				};
				ResponseEntity<List<SalesReportBillwise>> responseEntity = restTemplate.exchange(
						Constants.url + "getTcsReportByAllFr", HttpMethod.POST, new HttpEntity<>(map),
						typeRef);

				saleList = responseEntity.getBody();
				saleListForPdf = new ArrayList<>();
				saleListForPdf = saleList;
				System.out.println("sales List Bill Wise " + saleList.toString());

			} else {
				System.out.println("Inside else Few fr Selected ");

				map.add("frIdList", selectedFr);
				map.add("fromDate", fDate);
				map.add("toDate", tDate);

				ParameterizedTypeReference<List<SalesReportBillwise>> typeRef = new ParameterizedTypeReference<List<SalesReportBillwise>>() {
				};
				ResponseEntity<List<SalesReportBillwise>> responseEntity = restTemplate.exchange(
						Constants.url + "getTcsReportSelectedFr", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				saleList = responseEntity.getBody();
				saleListForPdf = new ArrayList<>();
				saleListForPdf = saleList;
				System.out.println("sales List Bill Wise " + saleList.toString());

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		model.addObject("fromDate", fDate);

		model.addObject("toDate", tDate);

		model.addObject("report", saleList);

		return model;
	}
	
	private float getTcsPer() {
		float tcsPer = 0;
		try{			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();
			
			map.add("settingKey", "TCS Value");
			SettingNew getTcsVal = restTemplate.postForObject(Constants.url + "getSettingValueByKey", map,
			SettingNew.class);	
			tcsPer = Float.parseFloat(getTcsVal.getSettingValue1());
		}catch (Exception e) {
			e.getMessage();
		}
		return tcsPer;
	}
	
	
	@RequestMapping(value = "/showConsolidatedCrnReport", method = RequestMethod.GET)
	public ModelAndView showConsolidatedCrnReportByDate(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/consoldtdcrn");

		try {
			ZoneId z = ZoneId.of("Asia/Calcutta");

			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			todaysDate = date.format(formatters);

			RestTemplate restTemplate = new RestTemplate();

			allFrIdNameList = new AllFrIdNameList();
			try {

				allFrIdNameList = restTemplate.getForObject(Constants.url + "getAllFranchises", AllFrIdNameList.class);
				// getAllFrIdName
			} catch (Exception e) {
				System.out.println("Exception in getAllFrIdName" + e.getMessage());
				e.printStackTrace();

			}

			model.addObject("todaysDate", todaysDate);
			model.addObject("unSelectedFrList", allFrIdNameList.getFrIdNamesList());

		} catch (Exception e) {

			System.out.println("Exc in /showConsolidatedCrnReportByDate  " + e.getMessage());
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/getConsldtCrnSalesReportByDate", method = RequestMethod.GET)
	public @ResponseBody List<CrnSalesReportDateWise> getConsldtCrnSalesReportByDate(HttpServletRequest request,
			HttpServletResponse response) {
		List<CrnSalesReportDateWise> crnSalesList = new ArrayList<CrnSalesReportDateWise>();
		String fromDate = "";
		String toDate = "";
		try {
			System.out.println("Inside get Sale Bill Wise");
			
			String selectedFr = request.getParameter("fr_id_list");
			fromDate = request.getParameter("fromDate");
			toDate = request.getParameter("toDate");
			
			selectedFr = selectedFr.substring(1, selectedFr.length() - 1);
			selectedFr = selectedFr.replaceAll("\"", "");


			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			System.out.println("Inside else Few fr Selected ");

			map.add("frIdList", selectedFr);
			map.add("fromDate", fromDate);
			map.add("toDate", toDate);

			ParameterizedTypeReference<List<CrnSalesReportDateWise>> typeRef = new ParameterizedTypeReference<List<CrnSalesReportDateWise>>() {
			};
			ResponseEntity<List<CrnSalesReportDateWise>> responseEntity = restTemplate.exchange(
					Constants.url + "getConsolidatedCrnReport", HttpMethod.POST, new HttpEntity<>(map), typeRef);

			crnSalesList = responseEntity.getBody();
			
			System.out.println("sales List Bill Wise " + saleList.toString());

		} catch (Exception e) {
			System.out.println("get sale Report Bill Wise " + e.getMessage());
			e.printStackTrace();

		}

		// exportToExcel
		List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

		ExportToExcel expoExcel = new ExportToExcel();
		List<String> rowData = new ArrayList<String>();

		rowData.add("Sr. No.");
		rowData.add("CRN No.");
		rowData.add("CRN Date");
		rowData.add("Franchise");
		rowData.add("Taxable Amt");
		rowData.add("Tax Amt");
		rowData.add("Total Amt");
		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);

		float taxableTotal = 0, taxTotal = 0, tot = 0;
		int srNo = 1;
		for (int i = 0; i < crnSalesList.size(); i++) {
			expoExcel = new ExportToExcel();
			rowData = new ArrayList<String>();

			rowData.add("" + srNo);
			rowData.add("" + crnSalesList.get(i).getCrnNo());
			rowData.add("" + crnSalesList.get(i).getCrnDate());
			rowData.add("" + crnSalesList.get(i).getFrName());
			rowData.add("" + crnSalesList.get(i).getCrnTaxableAmt());
			rowData.add("" + crnSalesList.get(i).getCrnTotalTax());
			rowData.add("" + crnSalesList.get(i).getCrnGrandTotal());

			taxableTotal = taxableTotal + crnSalesList.get(i).getCrnTaxableAmt();			
			taxTotal = taxTotal + crnSalesList.get(i).getCrnTotalTax();
			tot = tot + crnSalesList.get(i).getCrnGrandTotal();

			srNo = srNo+1;
			
			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);

		}

		expoExcel = new ExportToExcel();
		rowData = new ArrayList<String>();

		rowData.add("TOTAL");
		rowData.add("");
		rowData.add("");
		rowData.add("");
		rowData.add("" + taxableTotal);
		rowData.add("" + taxTotal);
		rowData.add("" + tot);

		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);

		HttpSession session = request.getSession();
		session.setAttribute("exportExcelListNew", exportToExcelList);
		session.setAttribute("excelNameNew", "ConsolidateCRNReport");
		session.setAttribute("reportNameNew", "Consolidate CRN Report");
		session.setAttribute("searchByNew", "From Date: " + fromDate + "  To Date: " + toDate + " ");
		session.setAttribute("mergeUpto1", "$A$1:$F$1");
		session.setAttribute("mergeUpto2", "$A$2:$F$2");
		return crnSalesList;

	}

	@RequestMapping(value = "pdf/showConsoldtdCrnPdf/{fDate}/{tDate}/{selectedFr}", method = RequestMethod.GET)
	public ModelAndView showConsoldtdCrnPdf(@PathVariable String fDate, @PathVariable String tDate,
			@PathVariable String selectedFr, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/pdf/cncldtdcrnPdf");

		List<CrnSalesReportDateWise> crnPdfList = new ArrayList<CrnSalesReportDateWise>();
		
		try {
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();
			
				map.add("frIdList", selectedFr);
				map.add("fromDate", fDate);
				map.add("toDate", tDate);

				ParameterizedTypeReference<List<CrnSalesReportDateWise>> typeRef = new ParameterizedTypeReference<List<CrnSalesReportDateWise>>() {
				};
				ResponseEntity<List<CrnSalesReportDateWise>> responseEntity = restTemplate.exchange(
						Constants.url + "getConsolidatedCrnReport", HttpMethod.POST, new HttpEntity<>(map), typeRef);
				crnPdfList = responseEntity.getBody();
				

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		model.addObject("fromDate", fDate);

		model.addObject("toDate", tDate);

		model.addObject("report", crnPdfList);

		return model;
	}
	
	@RequestMapping(value = "/showCrnSalesReportByDate", method = RequestMethod.GET)
	public ModelAndView showCrnSalesReportByDate(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/crnsalesreport");

		try {
			ZoneId z = ZoneId.of("Asia/Calcutta");

			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			todaysDate = date.format(formatters);

			RestTemplate restTemplate = new RestTemplate();

			allFrIdNameList = new AllFrIdNameList();
			try {

				allFrIdNameList = restTemplate.getForObject(Constants.url + "getAllFranchises", AllFrIdNameList.class);
				// getAllFrIdName
			} catch (Exception e) {
				System.out.println("Exception in getAllFrIdName" + e.getMessage());
				e.printStackTrace();

			}

			model.addObject("todaysDate", todaysDate);
			model.addObject("unSelectedFrList", allFrIdNameList.getFrIdNamesList());

		} catch (Exception e) {

			System.out.println("Exc in /showCrnSalesReportByDate  " + e.getMessage());
			e.printStackTrace();
		}

		return model;
	}
	
	
	@RequestMapping(value = "/getCrnSalesReportByDate", method = RequestMethod.GET)
	public @ResponseBody List<CrnSalesReportDateWise> getCrnSalesReportByDate(HttpServletRequest request,
			HttpServletResponse response) {
		List<CrnSalesReportDateWise> crnSalesList = new ArrayList<CrnSalesReportDateWise>();
		String fromDate = "";
		String toDate = "";
		try {
			System.out.println("Inside get Sale Bill Wise");
			
			String selectedFr = request.getParameter("fr_id_list");
			fromDate = request.getParameter("fromDate");
			toDate = request.getParameter("toDate");
			
			selectedFr = selectedFr.substring(1, selectedFr.length() - 1);
			selectedFr = selectedFr.replaceAll("\"", "");


			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			System.out.println("Inside else Few fr Selected ");

			map.add("frIdList", selectedFr);
			map.add("fromDate", fromDate);
			map.add("toDate", toDate);

			ParameterizedTypeReference<List<CrnSalesReportDateWise>> typeRef = new ParameterizedTypeReference<List<CrnSalesReportDateWise>>() {
			};
			ResponseEntity<List<CrnSalesReportDateWise>> responseEntity = restTemplate.exchange(
					Constants.url + "getCrnSaleReportDateWise", HttpMethod.POST, new HttpEntity<>(map), typeRef);

			crnSalesList = responseEntity.getBody();
			
			System.out.println("sales List Bill Wise " + saleList.toString());

		} catch (Exception e) {
			System.out.println("get sale Report Bill Wise " + e.getMessage());
			e.printStackTrace();

		}

		// exportToExcel
		List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

		ExportToExcel expoExcel = new ExportToExcel();
		List<String> rowData = new ArrayList<String>();

		rowData.add("Sr No.");
		rowData.add("CRN Date");
		rowData.add("Taxable Amt");
		rowData.add("Tax Amt");
		rowData.add("Total Amt");
		int srNo = 1;
		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);

		float taxableTotal = 0, taxTotal = 0, tot = 0;

		for (int i = 0; i < crnSalesList.size(); i++) {
			expoExcel = new ExportToExcel();
			rowData = new ArrayList<String>();

			rowData.add("" + srNo);
			rowData.add("" + crnSalesList.get(i).getCrnDate());
			rowData.add("" + crnSalesList.get(i).getCrnTaxableAmt());
			rowData.add("" + crnSalesList.get(i).getCrnTotalTax());
			rowData.add("" + crnSalesList.get(i).getCrnGrandTotal());

			taxableTotal = taxableTotal + crnSalesList.get(i).getCrnTaxableAmt();			
			taxTotal = taxTotal + crnSalesList.get(i).getCrnTotalTax();
			tot = tot + crnSalesList.get(i).getCrnGrandTotal();

			srNo = srNo+1;
			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);

		}

		expoExcel = new ExportToExcel();
		rowData = new ArrayList<String>();

		rowData.add("TOTAL");
		rowData.add("");
		rowData.add("" + taxableTotal);
		rowData.add("" + taxTotal);
		rowData.add("" + tot);

		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);

		HttpSession session = request.getSession();
		session.setAttribute("exportExcelListNew", exportToExcelList);
		session.setAttribute("excelNameNew", "CRNSalesReportDateWise");
		session.setAttribute("reportNameNew", "CRN Sales Report Date Wise");
		session.setAttribute("searchByNew", "From Date: " + fromDate + "  To Date: " + toDate + " ");
		session.setAttribute("mergeUpto1", "$A$1:$F$1");
		session.setAttribute("mergeUpto2", "$A$2:$F$2");
		return crnSalesList;

	}

	
	@RequestMapping(value = "pdf/showCrnSaleByDatePdf/{fDate}/{tDate}/{selectedFr}", method = RequestMethod.GET)
	public ModelAndView showCrnSaleByDatePdf(@PathVariable String fDate, @PathVariable String tDate,
			@PathVariable String selectedFr, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/pdf/crnsalesbydatePdf");

		List<CrnSalesReportDateWise> crnPdfList = new ArrayList<CrnSalesReportDateWise>();
		
		try {
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();
			
				map.add("frIdList", selectedFr);
				map.add("fromDate", fDate);
				map.add("toDate", tDate);

				ParameterizedTypeReference<List<CrnSalesReportDateWise>> typeRef = new ParameterizedTypeReference<List<CrnSalesReportDateWise>>() {
				};
				ResponseEntity<List<CrnSalesReportDateWise>> responseEntity = restTemplate.exchange(
						Constants.url + "getCrnSaleReportDateWise", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				crnPdfList = responseEntity.getBody();
				

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		model.addObject("fromDate", fDate);

		model.addObject("toDate", tDate);

		model.addObject("report", crnPdfList);

		return model;
	}

	@RequestMapping(value = "/showCrnSalesReportByMonth", method = RequestMethod.GET)
	public ModelAndView showCrnSalesReportByMonth(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/crnreportbymonth");

		try {
			ZoneId z = ZoneId.of("Asia/Calcutta");

			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			todaysDate = date.format(formatters);

			RestTemplate restTemplate = new RestTemplate();

			allFrIdNameList = new AllFrIdNameList();
			try {

				allFrIdNameList = restTemplate.getForObject(Constants.url + "getAllFranchises", AllFrIdNameList.class);
				// getAllFrIdName
			} catch (Exception e) {
				System.out.println("Exception in getAllFrIdName" + e.getMessage());
				e.printStackTrace();

			}

			model.addObject("todaysDate", todaysDate);
			model.addObject("unSelectedFrList", allFrIdNameList.getFrIdNamesList());

		} catch (Exception e) {

			System.out.println("Exc in /showCrnSalesReportByMonth  " + e.getMessage());
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/getCrnSalesReportByMonth", method = RequestMethod.GET)
	public @ResponseBody List<CrnSalesReportDateWise> getCrnSalesReportByMonth(HttpServletRequest request,
			HttpServletResponse response) {
		List<CrnSalesReportDateWise> crnSalesList = new ArrayList<CrnSalesReportDateWise>();
		String fromDate = "";
		String toDate = "";
		try {
			System.out.println("Inside get Sale Bill Wise");
			
			String selectedFr = request.getParameter("fr_id_list");
			fromDate = request.getParameter("fromDate");
			toDate = request.getParameter("toDate");
			
			selectedFr = selectedFr.substring(1, selectedFr.length() - 1);
			selectedFr = selectedFr.replaceAll("\"", "");


			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			System.out.println("Inside else Few fr Selected ");

			map.add("frIdList", selectedFr);
			map.add("fromDate", fromDate);
			map.add("toDate", toDate);

			ParameterizedTypeReference<List<CrnSalesReportDateWise>> typeRef = new ParameterizedTypeReference<List<CrnSalesReportDateWise>>() {
			};
			ResponseEntity<List<CrnSalesReportDateWise>> responseEntity = restTemplate.exchange(
					Constants.url + "getCrnSaleReportMonthWise", HttpMethod.POST, new HttpEntity<>(map), typeRef);

			crnSalesList = responseEntity.getBody();
			
			System.out.println("sales List Bill Wise " + saleList.toString());

		} catch (Exception e) {
			System.out.println("get sale Report Bill Wise " + e.getMessage());
			e.printStackTrace();

		}

		// exportToExcel
		List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

		ExportToExcel expoExcel = new ExportToExcel();
		List<String> rowData = new ArrayList<String>();

		rowData.add("Sr No.");
		rowData.add("Month");
		rowData.add("Taxable Amt");
		rowData.add("Tax Amt");
		rowData.add("Total Amt");
		int srNo = 1;
		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);

		float taxableTotal = 0, taxTotal = 0, tot = 0;

		for (int i = 0; i < crnSalesList.size(); i++) {
			expoExcel = new ExportToExcel();
			rowData = new ArrayList<String>();

			rowData.add("" + srNo);
			rowData.add("" + crnSalesList.get(i).getMonthName()+"-"+crnSalesList.get(i).getFrName());
			rowData.add("" + crnSalesList.get(i).getCrnTaxableAmt());
			rowData.add("" + crnSalesList.get(i).getCrnTotalTax());
			rowData.add("" + crnSalesList.get(i).getCrnGrandTotal());

			taxableTotal = taxableTotal + crnSalesList.get(i).getCrnTaxableAmt();			
			taxTotal = taxTotal + crnSalesList.get(i).getCrnTotalTax();
			tot = tot + crnSalesList.get(i).getCrnGrandTotal();

			srNo = srNo+1;
			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);

		}

		expoExcel = new ExportToExcel();
		rowData = new ArrayList<String>();

		rowData.add("TOTAL");
		rowData.add("");
		rowData.add("" + taxableTotal);
		rowData.add("" + taxTotal);
		rowData.add("" + tot);

		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);

		HttpSession session = request.getSession();
		session.setAttribute("exportExcelListNew", exportToExcelList);
		session.setAttribute("excelNameNew", "CRNSalesReportMonthWise");
		session.setAttribute("reportNameNew", "CRN Sales Report Month Wise");
		session.setAttribute("searchByNew", "From Date: " + fromDate + "  To Date: " + toDate + " ");
		session.setAttribute("mergeUpto1", "$A$1:$F$1");
		session.setAttribute("mergeUpto2", "$A$2:$F$2");
		return crnSalesList;

	}

	@RequestMapping(value = "pdf/showCrnSaleByMonthPdf/{fDate}/{tDate}/{selectedFr}", method = RequestMethod.GET)
	public ModelAndView showCrnSaleByMonthPdf(@PathVariable String fDate, @PathVariable String tDate,
			@PathVariable String selectedFr, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/pdf/crnsalesbymonthPdf");

		List<CrnSalesReportDateWise> crnPdfList = new ArrayList<CrnSalesReportDateWise>();
		
		try {
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();
			
				map.add("frIdList", selectedFr);
				map.add("fromDate", fDate);
				map.add("toDate", tDate);

				ParameterizedTypeReference<List<CrnSalesReportDateWise>> typeRef = new ParameterizedTypeReference<List<CrnSalesReportDateWise>>() {
				};
				ResponseEntity<List<CrnSalesReportDateWise>> responseEntity = restTemplate.exchange(
						Constants.url + "getCrnSaleReportMonthWise", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				crnPdfList = responseEntity.getBody();
				

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		model.addObject("fromDate", fDate);

		model.addObject("toDate", tDate);

		model.addObject("report", crnPdfList);

		return model;
	}
	
	@RequestMapping(value = "/showBillTaxSlabReport", method = RequestMethod.GET)
	public ModelAndView showTaxReport(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		HttpSession session = request.getSession();
		String fromDate = "";
		String toDate = "";

			model = new ModelAndView("reports/tax/tax1Report");
			
			List<Tax1Report> taxReportList = null;

			try {

				RestTemplate restTemplate = new RestTemplate();

				fromDate = request.getParameter("fromDate");
				toDate = request.getParameter("toDate");

				if (fromDate == null && toDate == null) {
					Date date = new Date();
					SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
					fromDate = formatter.format(date);
					toDate = formatter.format(date);
				}
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				ParameterizedTypeReference<List<Tax1Report>> typeRef = new ParameterizedTypeReference<List<Tax1Report>>() {
				};
				ResponseEntity<List<Tax1Report>> responseEntity = restTemplate.exchange(Constants.url + "getTax1Report",
						HttpMethod.POST, new HttpEntity<>(map), typeRef);

				taxReportList = responseEntity.getBody();
				model.addObject("taxReportList", taxReportList);
				model.addObject("fromDate", fromDate);
				model.addObject("toDate", toDate);
				
				// exportToExcel
				List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

				ExportToExcel expoExcel = new ExportToExcel();
				List<String> rowData = new ArrayList<String>();

				expoExcel = new ExportToExcel();
				rowData = new ArrayList<String>();
				rowData.add("Sr.No.");
				rowData.add("GSTIN/UIN of Recipient");
				rowData.add("Receiver Name");
				rowData.add("Invoice No");
				rowData.add("Invoice date");
				rowData.add("Invoice Value");
				rowData.add("Place of Supply");
				rowData.add("Reverse Charge");

				rowData.add("Applicable % of Tax Rate");
				rowData.add("Invoice Type");
				rowData.add("E Commerce GSTIN");
				rowData.add("Rate");
				rowData.add("Taxable Value");
				rowData.add("Cess Amount");

				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);

				float taxableAmt = 0.0f;
				float cgstSum = 0.0f;
				float sgstSum = 0.0f;

				float totalTax = 0.0f;
				float totalFinal = 0.0f;
				float grandTotal = 0.0f;

				for (int i = 0; i < taxReportList.size(); i++) {
					float finalTotal = 0;
					for (int j = 0; j < taxReportList.size(); j++) {

						if (taxReportList.get(j).getBillNo() == taxReportList.get(i).getBillNo()) {
							finalTotal = finalTotal + taxReportList.get(j).getGrandTotal();
						}
					}

					taxableAmt = taxableAmt + taxReportList.get(i).getTaxableAmt();
					cgstSum = cgstSum + taxReportList.get(i).getCgstAmt();
					sgstSum = sgstSum + taxReportList.get(i).getSgstAmt();

					totalTax = totalTax + taxReportList.get(i).getTotalTax();
					grandTotal = grandTotal + taxReportList.get(i).getGrandTotal();
					totalFinal = totalFinal + finalTotal;

					expoExcel = new ExportToExcel();
					rowData = new ArrayList<String>();
					rowData.add((i + 1) + "");
					rowData.add("" + taxReportList.get(i).getFrGstNo());
					rowData.add("" + taxReportList.get(i).getFrName());
					rowData.add("" + taxReportList.get(i).getInvoiceNo());
					rowData.add("" + taxReportList.get(i).getBillDate());

					rowData.add("" + finalTotal);
					rowData.add("" + Constants.STATE);
					rowData.add("N");
					rowData.add(" ");

					rowData.add("Regular");
					rowData.add(" ");

					rowData.add("" + (taxReportList.get(i).getCgstPer() + taxReportList.get(i).getSgstPer()));
					rowData.add("" + taxReportList.get(i).getTaxableAmt());
					rowData.add("0");

					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);

				}

				expoExcel = new ExportToExcel();
				rowData = new ArrayList<String>();

				rowData.add("Total");
				rowData.add("");
				rowData.add("");
				rowData.add("");
				rowData.add("");
				rowData.add("" + roundUp(totalFinal));
				rowData.add("");
				rowData.add("");
				rowData.add("");
				rowData.add("");
				rowData.add("");
				rowData.add("");
				rowData.add("");
				rowData.add("");

				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);
				
				session.setAttribute("exportExcelListNew", exportToExcelList);
				session.setAttribute("excelNameNew", "BillWiseTaxSlabWiseReport");
				session.setAttribute("reportNameNew", "Bill Wise Tax Slab Wise Report");
				session.setAttribute("searchByNew", "From Date: " + fromDate + "  To Date: " + toDate + " ");
				session.setAttribute("mergeUpto1", "$A$1:$N$1");
				session.setAttribute("mergeUpto2", "$A$2:$N$2");

				
			} catch (Exception e) {
				System.out.println("Exc in Tax Report" + e.getMessage());
				e.printStackTrace();
			}
		

		return model;

	}
	
	
	@RequestMapping(value = "/showCRNoteRegisterDone", method = RequestMethod.GET)
	public ModelAndView showCRNoteRegisterDone(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/v2/crNote_register_done");

		try {

			ZoneId z = ZoneId.of("Asia/Calcutta");

			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			String todaysDate = date.format(formatters);

			model.addObject("todaysDate", todaysDate);

		} catch (Exception e) {
			System.out.println("Exce in showRegCakeSpOrderReport " + e.getMessage());
			e.printStackTrace();
		}

		return model;
	}
	
	List<CrNoteRegItem> crNoteRegItemList = new ArrayList<>();
	// getCRNoteRegister Ajax

	@RequestMapping(value = "/getCRNoteRegisterDone", method = RequestMethod.GET)
	public @ResponseBody List<CrNoteRegItem> getCRNoteRegisterDone(HttpServletRequest request,
			HttpServletResponse response) throws FileNotFoundException {


		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();


		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");
		String CreditNoteType = request.getParameter("Credittype");
		try {

			map.add("fromDate", DateConvertor.convertToYMD(fromDate));

			map.add("toDate", DateConvertor.convertToYMD(toDate));
			map.add("CreditNoteType", CreditNoteType);
			System.out.println("CreditNoteType" + CreditNoteType);

			CrNoteRegisterList crnArray = restTemplate.postForObject(Constants.url + "getCrNoteRegisterDone", map,
					CrNoteRegisterList.class);
			//System.out.println("Data1---------->"+crnArray.getCrNoteRegItemList());
			//System.out.println("Data2---------->"+crnArray.getCrNoteRegSpList());
			List<CrNoteRegSp> crnRegSpList = new ArrayList<>();

			crNoteRegItemList = crnArray.getCrNoteRegItemList();
			crnRegSpList = crnArray.getCrNoteRegSpList();

			for (int j = 0; j < crnRegSpList.size(); j++) {
				int flag = 0;

				for (int i = 0; i < crNoteRegItemList.size(); i++) {

					if (crNoteRegItemList.get(i).getCrnId() == crnRegSpList.get(j).getCrnId()
							&& crNoteRegItemList.get(i).getHsnCode().equals(crnRegSpList.get(j).getHsnCode())) {
						flag = 1;
						crNoteRegItemList.get(i)
								.setCrnQty(crNoteRegItemList.get(i).getCrnQty() + crnRegSpList.get(j).getCrnQty());

						crNoteRegItemList.get(i).setCrnTaxable(
								(crNoteRegItemList.get(i).getCrnTaxable() + crnRegSpList.get(j).getCrnTaxable()));

						crNoteRegItemList.get(i)
								.setCgstAmt((crNoteRegItemList.get(i).getCgstAmt() + crnRegSpList.get(j).getCgstAmt()));
						crNoteRegItemList.get(i)
								.setSgstAmt((crNoteRegItemList.get(i).getSgstAmt() + crnRegSpList.get(j).getSgstAmt()));
						crNoteRegItemList.get(i)
								.setCrnAmt((crNoteRegItemList.get(i).getCrnAmt() + crnRegSpList.get(j).getCrnAmt()));

						crNoteRegItemList.get(i).setTtlTaxable(crnRegSpList.get(j).getTtlTaxable());
						crNoteRegItemList.get(i).setTtlTaxAmt(crnRegSpList.get(j).getTtlTaxAmt());
						crNoteRegItemList.get(i).setTtlCrnAmt(crnRegSpList.get(j).getTtlCrnAmt());
					}

				}

				if (flag == 0) {

					//System.err.println("New hsn code item found ");

					CrNoteRegItem regItem = new CrNoteRegItem();

					regItem.setCrnDate(crnRegSpList.get(j).getCrnDate());

					regItem.setBillDate(crnRegSpList.get(j).getBillDate());
					regItem.setCrndId(crnRegSpList.get(j).getCrndId());
					regItem.setCrnId(crnRegSpList.get(j).getCrnId());
					regItem.setCrnQty(crnRegSpList.get(j).getCrnQty());
					regItem.setCgstAmt(crnRegSpList.get(j).getCgstAmt());
					regItem.setCgstPer(crnRegSpList.get(j).getCgstPer());
					regItem.setFrGstNo(crnRegSpList.get(j).getFrGstNo());
					regItem.setFrName(crnRegSpList.get(j).getFrName());
					regItem.setCrnAmt(crnRegSpList.get(j).getCrnAmt());
					regItem.setHsnCode(crnRegSpList.get(j).getHsnCode());
					regItem.setInvoiceNo(crnRegSpList.get(j).getInvoiceNo());
					regItem.setSgstAmt(crnRegSpList.get(j).getSgstAmt());
					regItem.setSgstPer(crnRegSpList.get(j).getSgstPer());
					regItem.setCrnTaxable(crnRegSpList.get(j).getCrnTaxable());

					regItem.setFrCode(crnRegSpList.get(j).getFrCode());
					
					regItem.setTtlCrnAmt(crnRegSpList.get(j).getTtlCrnAmt());
					regItem.setTtlTaxable(crnRegSpList.get(j).getTtlTaxable());
					regItem.setTtlTaxAmt(crnRegSpList.get(j).getTtlTaxAmt());

					crNoteRegItemList.add(regItem);
				}
			}

			//System.err.println("crNoteRegItemList combined  " + crNoteRegItemList.toString());

			List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

			ExportToExcel expoExcel = new ExportToExcel();
			List<String> rowData = new ArrayList<String>();

			rowData.add("Sr. No.");
			rowData.add("GSTIN/UIN of Recipient");
			rowData.add("Receiver Name");
			rowData.add("Invoice/Advance Receipt Number");
			rowData.add("Invoice/Advance Receipt date");
			rowData.add("Note/Refund Voucher Number");
			rowData.add("Note/Refund Voucher date");
			rowData.add("Document Type");
			rowData.add("Place Of Supply");
			rowData.add("Note/Refund Voucher Value");
			rowData.add("Applicable % of Tax Rate");
			rowData.add("Rate");
			rowData.add("Taxable Value");
			rowData.add("Cess Amount");
			
			rowData.add("Total Taxable");
			rowData.add("Total Tax");
			rowData.add("Grand Total");

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);
			float crnQty = 0.0f;
			float crnTaxable = 0.0f;
			float cgstAmt = 0.0f;
			float sgstAmt = 0.0f;
			float crnAmt = 0.0f;

			for (int i = 0; i < crNoteRegItemList.size(); i++) {
				float crnTotal = 0.0f;
				for (int j = 0; j < crNoteRegItemList.size(); j++) {
					if (crNoteRegItemList.get(i).getCrnId() == crNoteRegItemList.get(j).getCrnId()) {
						crnTotal = crnTotal + roundUp(crNoteRegItemList.get(j).getCrnAmt());
					}
				}

				expoExcel = new ExportToExcel();
				rowData = new ArrayList<String>();
				rowData.add("" + (i + 1));
				rowData.add("" + crNoteRegItemList.get(i).getFrGstNo());
				rowData.add("" + crNoteRegItemList.get(i).getFrName());
				rowData.add("" + crNoteRegItemList.get(i).getInvoiceNo());
				rowData.add("" + crNoteRegItemList.get(i).getBillDate());
				rowData.add("" + crNoteRegItemList.get(i).getFrCode());
				rowData.add("" + crNoteRegItemList.get(i).getCrnDate());
				rowData.add(" ");
				rowData.add("" + Constants.STATE);
				rowData.add("" + roundUp(crnTotal));
				rowData.add(" ");
				rowData.add("" + (crNoteRegItemList.get(i).getCgstPer() + crNoteRegItemList.get(i).getSgstPer()));
				rowData.add("" + roundUp(crNoteRegItemList.get(i).getCrnTaxable()));
				rowData.add("0");
				
				rowData.add("" + roundUp(crNoteRegItemList.get(i).getTtlTaxable()));
				rowData.add("" + roundUp(crNoteRegItemList.get(i).getTtlTaxAmt()));
				rowData.add("" + roundUp(crNoteRegItemList.get(i).getTtlCrnAmt()));

				crnQty = crnQty + crNoteRegItemList.get(i).getCrnQty();
				crnTaxable = crnTaxable + crNoteRegItemList.get(i).getCrnTaxable();
				cgstAmt = cgstAmt + crNoteRegItemList.get(i).getCgstAmt();
				sgstAmt = sgstAmt + crNoteRegItemList.get(i).getSgstAmt();
				crnAmt = crnAmt + crNoteRegItemList.get(i).getCrnAmt();

				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);

			}
			expoExcel = new ExportToExcel();
			rowData = new ArrayList<String>();
			rowData.add("Total");
			rowData.add("");
			rowData.add("");
			rowData.add("");
			rowData.add("");
			rowData.add("");
			rowData.add("");
			rowData.add("");
			rowData.add("");

			rowData.add("" + Math.round(crnAmt));
			rowData.add("");
			rowData.add("");

			rowData.add("" + roundUp(crnTaxable));

			rowData.add("");

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);

			HttpSession session = request.getSession();
			session.setAttribute("exportExcelListNew", exportToExcelList);
			session.setAttribute("excelNameNew", "CR Note Register");
			session.setAttribute("reportNameNew", "Credit Note Register Report");
			session.setAttribute("searchByNew", "From Date: " + fromDate + "  To Date: " + toDate + " ");
			session.setAttribute("mergeUpto1", "$A$1:$M$1");
			session.setAttribute("mergeUpto2", "$A$2:$M$2");

		} catch (Exception e) {

			e.printStackTrace();

		}
		return crNoteRegItemList;
	}

}
