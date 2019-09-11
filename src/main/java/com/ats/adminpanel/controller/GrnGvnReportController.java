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
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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
import org.springframework.util.FileCopyUtils;
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
import com.ats.adminpanel.model.AllFrIdNameList;
import com.ats.adminpanel.model.AllRoutesListResponse;
import com.ats.adminpanel.model.ExportToExcel;
import com.ats.adminpanel.model.Route;
import com.ats.adminpanel.model.SalesVoucherList;
import com.ats.adminpanel.model.billing.FrBillHeaderForPrint;
import com.ats.adminpanel.model.franchisee.FrNameIdByRouteId;
import com.ats.adminpanel.model.franchisee.FrNameIdByRouteIdResponse;
import com.ats.adminpanel.model.ggreports.GGReportByDateAndFr;
import com.ats.adminpanel.model.ggreports.GGReportGrpByFrId;
import com.ats.adminpanel.model.ggreports.GGReportGrpByMonthDate;
import com.ats.adminpanel.model.ggreports.GrnGvnReportByGrnType;
import com.ats.adminpanel.model.mastexcel.TallyItem;
import com.ats.adminpanel.model.production.GetProdDetailBySubCat;
import com.ats.adminpanel.model.production.GetProdDetailBySubCatList;
import com.ats.adminpanel.model.production.GetProdPlanDetail;
import com.ats.adminpanel.model.salesreport.SalesReportBillwise;
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
public class GrnGvnReportController {

	public AllFrIdNameList allFrIdNameList = new AllFrIdNameList();

	public List<Route> routeList = new ArrayList<Route>();
	AllRoutesListResponse allRouteListResponse = new AllRoutesListResponse();

	AllFrIdNameList getFrNameId() {

		RestTemplate restTemplate = new RestTemplate();
		return allFrIdNameList = restTemplate.getForObject(Constants.url + "getAllFrIdName", AllFrIdNameList.class);
	}

	AllRoutesListResponse getAllRoute() {

		RestTemplate restTemplate = new RestTemplate();

		return allRouteListResponse = restTemplate.getForObject(Constants.url + "showRouteList",
				AllRoutesListResponse.class);

	}
	// 25-05-2018

	@RequestMapping(value = "/showGGvnReportByGrnType", method = RequestMethod.GET)
	public ModelAndView showGGvnReportByGrnType(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/grnGvn/ggreportbytype");

		try {

			ZoneId z = ZoneId.of("Asia/Calcutta");

			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			String todaysDate = date.format(formatters);

			allFrIdNameList = getFrNameId();

			allRouteListResponse = getAllRoute();

			model.addObject("routeList", allRouteListResponse.getRoute());
			model.addObject("todaysDate", todaysDate);
			model.addObject("unSelectedFrList", allFrIdNameList.getFrIdNamesList());

		} catch (Exception e) {
			System.out.println("Exce showGGvnReportByGrnType " + e.getMessage());
			e.printStackTrace();
		}

		return model;
	}

	List<GrnGvnReportByGrnType> grnGvnByTypeList = new ArrayList<>();

	List<GrnGvnReportByGrnType> pdfTypeList = new ArrayList<>();

	@RequestMapping(value = "/getGGvnReportByGrnType", method = RequestMethod.GET)
	@ResponseBody
	public List<GrnGvnReportByGrnType> getGGvnReportByGrnType(HttpServletRequest request,
			HttpServletResponse response) {
		System.err.println("Inside Ajax call /getGGvnReportByGrnType");
		List<GrnGvnReportByGrnType> grnGvnByTypeList = new ArrayList<>();

		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			RestTemplate restTemplate = new RestTemplate();

			String routeId = "0";
			String frIdString = "";

			System.out.println("inside getGrnGvnByDatewise ajax call");

			frIdString = request.getParameter("fr_id_list");
			String fromDate = request.getParameter("from_date");
			String toDate = request.getParameter("to_date");
			routeId = request.getParameter("route_id");

			System.out.println("fromDate= " + fromDate);

			boolean isAllFrSelected = false;

			frIdString = frIdString.substring(1, frIdString.length() - 1);
			frIdString = frIdString.replaceAll("\"", "");

			List<String> franchIds = new ArrayList();
			franchIds = Arrays.asList(frIdString);

			System.out.println("fr Id ArrayList " + franchIds.toString());

			if (franchIds.contains("-1")) {
				isAllFrSelected = true;

			}

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
				frIdString = strFrIdRouteWise.substring(0, strFrIdRouteWise.length() - 1);
				System.out.println("fr Id Route WISE = " + frIdString);

			} // end of if

			map = new LinkedMultiValueMap<String, Object>();
			if (isAllFrSelected) {

				System.out.println("Inside IF  is All fr Selected " + isAllFrSelected);

				map.add("frIdList", 0);
				// model.addObject("billHeadersList",billHeadersListForPrint);

			} else { // few franchisee selected

				System.out.println("Inside Else: Few Fr Selected ");
				map.add("frIdList", frIdString);

			}

			map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			map.add("toDate", DateConvertor.convertToYMD(toDate));

			ParameterizedTypeReference<List<GrnGvnReportByGrnType>> typeRef = new ParameterizedTypeReference<List<GrnGvnReportByGrnType>>() {
			};
			ResponseEntity<List<GrnGvnReportByGrnType>> responseEntity = restTemplate.exchange(
					Constants.url + "getGrnGvnReportByGrnType", HttpMethod.POST, new HttpEntity<>(map), typeRef);

			grnGvnByTypeList = responseEntity.getBody();
			pdfTypeList = grnGvnByTypeList;
			System.err.println("List getGrnGvnReportByGrnType " + grnGvnByTypeList.toString());

			List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

			ExportToExcel expoExcel = new ExportToExcel();
			List<String> rowData = new ArrayList<String>();

			rowData.add("Sr. No.");

			rowData.add("Franchise Name");
			rowData.add("GRN 1");
			rowData.add("GRN 2");
			rowData.add("GRN 3");
			rowData.add("GVN");
			rowData.add("Total");

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);
			List<GrnGvnReportByGrnType> excelItems = grnGvnByTypeList;
			float grandTotal = 0;
			float grn1Amt = 0;
			float grn2Amt = 0;
			float grn3Amt = 0;
			float gvnAmt = 0;
			for (int i = 0; i < excelItems.size(); i++) {
				expoExcel = new ExportToExcel();
				rowData = new ArrayList<String>();
				rowData.add("" + (i + 1));
				rowData.add("" + excelItems.get(i).getFrName());

				float total = excelItems.get(i).getAprAmtGrn1() + excelItems.get(i).getAprAmtGrn2()
						+ excelItems.get(i).getAprAmtGrn3() + +excelItems.get(i).getAprAmtGvn();
				grandTotal = grandTotal + total;
				grn1Amt = grn1Amt + excelItems.get(i).getAprAmtGrn1();
				grn2Amt = grn2Amt + excelItems.get(i).getAprAmtGrn2();
				grn3Amt = grn3Amt + excelItems.get(i).getAprAmtGrn3();
				gvnAmt = gvnAmt + excelItems.get(i).getAprAmtGvn();
				rowData.add("" + excelItems.get(i).getAprAmtGrn1());
				rowData.add("" + excelItems.get(i).getAprAmtGrn2());
				rowData.add("" + excelItems.get(i).getAprAmtGrn3());

				rowData.add("" + excelItems.get(i).getAprAmtGvn());
				rowData.add("" + total);

				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);

			}
			expoExcel = new ExportToExcel();

			rowData = new ArrayList<String>();
			rowData.add("Total");
			rowData.add("");

			rowData.add("" + grn1Amt);

			rowData.add("" + grn2Amt);
			rowData.add("" + grn3Amt);
			rowData.add("" + gvnAmt);

			rowData.add("" + grandTotal);

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);

			HttpSession session = request.getSession();
			session.setAttribute("exportExcelList", exportToExcelList);
			session.setAttribute("excelName", "grnGvnByTypeReport");

		} catch (Exception e) {

			System.out.println("Ex in getting /getgGReportByDate List  Ajax call" + e.getMessage());
			e.printStackTrace();
		}

		return grnGvnByTypeList;

	}

	@RequestMapping(value = "/getGGreportByTypePdf", method = RequestMethod.GET)
	public void getGGreportByTypePdf(HttpServletRequest request, HttpServletResponse response) {

		BufferedOutputStream outStream = null;
		System.out.println("Inside Pdf prod From Order Or Plan");

		grnGvnByTypeList = pdfTypeList;
		Document document = new Document(PageSize.A4, 20, 20, 150, 30);
		// ByteArrayOutputStream out = new ByteArrayOutputStream();

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();

		System.out.println("time in getGGreportByTypePdf PDF ==" + dateFormat.format(cal.getTime()));
		String timeStamp = dateFormat.format(cal.getTime());
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

			String header = "                                           Galdhar Foods\n"
					+ "          Factory Add: Plot No.48,Chikalthana Midc, Aurangabad."
					+ "\n              Phone:0240-2466217, Email: aurangabad@monginis.net";

			String title = "                 Report-For GRN GVN BY GRN TYPE";

			DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
			String reportDate = DF.format(new Date());

			writer = PdfWriter.getInstance(document, out);

			ItextPageEvent event = new ItextPageEvent(header, title, reportDate);

			writer.setPageEvent(event);

		} catch (DocumentException e) {

			e.printStackTrace();
		}

		PdfPTable table = new PdfPTable(7);
		try {
			System.out.println("Inside PDF Table try");
			table.setWidthPercentage(100);
			table.setWidths(new float[] { 0.4f, 1.7f, 0.9f, 1.0f, 0.9f, 0.9f, 1.0f });
			Font headFont = new Font(FontFamily.TIMES_ROMAN, 15, Font.NORMAL, BaseColor.BLACK);
			Font headFont1 = new Font(FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLACK);
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

			hcell = new PdfPCell(new Phrase("Franchise Name", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("GRN 1", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("GRN 2", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("GRN 3", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("GVN", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Total", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);
			table.addCell(hcell);
			table.setHeaderRows(1);

			float grandTotal = 0;
			float grn1Amt = 0;
			float grn2Amt = 0;
			float grn3Amt = 0;
			float gvnAmt = 0;
			int index = 0;
			for (GrnGvnReportByGrnType grnGByType : grnGvnByTypeList) {
				index++;
				PdfPCell cell;

				cell = new PdfPCell(new Phrase(String.valueOf(index), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPadding(4);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(grnGByType.getFrName(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				cell.setPadding(4);
				table.addCell(cell);

				grn1Amt = grn1Amt + grnGByType.getAprAmtGrn1();
				grn2Amt = grn2Amt + grnGByType.getAprAmtGrn2();
				grn3Amt = grn3Amt + grnGByType.getAprAmtGrn3();
				gvnAmt = gvnAmt + grnGByType.getAprAmtGvn();
				cell = new PdfPCell(new Phrase(String.valueOf(grnGByType.getAprAmtGrn1()), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(4);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(String.valueOf(grnGByType.getAprAmtGrn2()), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(4);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(String.valueOf(grnGByType.getAprAmtGrn3()), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(4);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(String.valueOf(grnGByType.getAprAmtGvn()), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(4);

				table.addCell(cell);

				float total = grnGByType.getAprAmtGrn3() + grnGByType.getAprAmtGrn2() + grnGByType.getAprAmtGrn1()
						+ grnGByType.getAprAmtGvn();
				grandTotal = grandTotal + total;
				cell = new PdfPCell(new Phrase("" + total, headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(5);
				table.addCell(cell);
				// FooterTable footerEvent = new FooterTable(table);
				// writer.setPageEvent(footerEvent);
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

			cell = new PdfPCell(new Phrase(String.valueOf("" + grn1Amt), headFont));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setPaddingRight(2);
			cell.setPadding(4);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(String.valueOf("" + grn2Amt), headFont));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setPaddingRight(2);
			cell.setPadding(4);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(String.valueOf("" + grn3Amt), headFont));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setPaddingRight(2);
			cell.setPadding(4);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(String.valueOf("" + gvnAmt), headFont));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setPaddingRight(2);
			cell.setPadding(4);

			table.addCell(cell);

			cell = new PdfPCell(new Phrase("" + grandTotal, headFont));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingRight(2);
			cell.setPadding(5);
			table.addCell(cell);

			document.open();
			document.add(table);
			document.close();

			/*
			 * document.open(); Paragraph company = new Paragraph(
			 * "Galdhar Foods Pvt.Ltd\n", f); company.setAlignment(Element.ALIGN_CENTER);
			 * document.add(company); document.add(new Paragraph(" "));
			 * 
			 * DateFormat DF = new SimpleDateFormat("dd-MM-yyyy"); String reportDate =
			 * DF.format(new Date());
			 * 
			 * document.add(new Paragraph("Report Date: " + reportDate)); document.add(new
			 * Paragraph("\n"));
			 * 
			 * document.add(new Paragraph("Grn Gvn Report By Grn Type "));
			 * 
			 * document.add(new Paragraph("\n")); document.add(table); document.add(new
			 * Paragraph("\n"));
			 * 
			 * int totalPages = writer.getPageNumber();
			 */
			// System.out.println("Page no " + totalPages);

			// document.close();
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

	// 25-05-2018

	// r1
	@RequestMapping(value = "/showGGReportDateWise", method = RequestMethod.GET)
	public ModelAndView showGGReportDateWise(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/grnGvn/ggByDate");

		try {

			ZoneId z = ZoneId.of("Asia/Calcutta");

			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			String todaysDate = date.format(formatters);

			allFrIdNameList = getFrNameId();

			allRouteListResponse = getAllRoute();

			model.addObject("routeList", allRouteListResponse.getRoute());
			model.addObject("todaysDate", todaysDate);
			model.addObject("unSelectedFrList", allFrIdNameList.getFrIdNamesList());

		} catch (Exception e) {
			System.out.println("Exce inshowGGReportDateWise " + e.getMessage());
			e.printStackTrace();
		}

		return model;
	}
	// consume R1 web Service

	@RequestMapping(value = "/getGrnGvnByDatewise", method = RequestMethod.GET)
	@ResponseBody
	public List<GGReportByDateAndFr> getGrnGvnByDatewise(HttpServletRequest request, HttpServletResponse response) {

		List<GGReportByDateAndFr> grnGvnByDateList = new ArrayList<>();

		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			RestTemplate restTemplate = new RestTemplate();

			String routeId = "0";
			String frIdString = "";

			System.out.println("inside getGrnGvnByDatewise ajax call");

			frIdString = request.getParameter("fr_id_list");
			String fromDate = request.getParameter("from_date");
			String toDate = request.getParameter("to_date");
			routeId = request.getParameter("route_id");

			String isGrn = request.getParameter("is_grn");
			String grnType;
			if (isGrn.equalsIgnoreCase("2")) {

				System.err.println("Is Grn =2");
				grnType = "1" + "," + "0";

				map.add("isGrn", grnType);
			} else {
				System.err.println("Is Grn not =2");
				grnType = isGrn;
				map.add("isGrn", isGrn);

			}

			System.out.println("fromDate= " + fromDate);

			boolean isAllFrSelected = false;

			frIdString = frIdString.substring(1, frIdString.length() - 1);
			frIdString = frIdString.replaceAll("\"", "");

			List<String> franchIds = new ArrayList();
			franchIds = Arrays.asList(frIdString);

			System.out.println("fr Id ArrayList " + franchIds.toString());

			if (franchIds.contains("-1")) {
				isAllFrSelected = true;

			}

			if (!routeId.equalsIgnoreCase("0")) {

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
				frIdString = strFrIdRouteWise.substring(0, strFrIdRouteWise.length() - 1);
				System.out.println("fr Id Route WISE = " + frIdString);

			} // end of if

			map = new LinkedMultiValueMap<String, Object>();
			if (isAllFrSelected) {

				System.out.println("Inside IF  is All fr Selected " + isAllFrSelected);

				map.add("frIdList", 0);
				// model.addObject("billHeadersList",billHeadersListForPrint);

			} else { // few franchisee selected

				System.out.println("Inside Else: Few Fr Selected ");
				map.add("frIdList", frIdString);

			}

			map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			map.add("toDate", DateConvertor.convertToYMD(toDate));
			map.add("isGrn", grnType);

			ParameterizedTypeReference<List<GGReportByDateAndFr>> typeRef = new ParameterizedTypeReference<List<GGReportByDateAndFr>>() {
			};
			ResponseEntity<List<GGReportByDateAndFr>> responseEntity = restTemplate
					.exchange(Constants.url + "getgGReportByDate", HttpMethod.POST, new HttpEntity<>(map), typeRef);

			grnGvnByDateList = responseEntity.getBody();

			System.err.println("List " + grnGvnByDateList.toString());

			List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

			ExportToExcel expoExcel = new ExportToExcel();
			List<String> rowData = new ArrayList<String>();

			rowData.add("Sr. No.");
			rowData.add("Date");
			rowData.add("Type");
			rowData.add("GrnGvn SrNo");
			rowData.add("Franchise Name");
			rowData.add("Req Qty");
			rowData.add("Req Value");
			rowData.add("Apr Qty");
			rowData.add("Apr Value");

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);
			List<GGReportByDateAndFr> excelItems = grnGvnByDateList;
			for (int i = 0; i < excelItems.size(); i++) {
				expoExcel = new ExportToExcel();
				rowData = new ArrayList<String>();
				rowData.add("" + (i + 1));
				rowData.add("" + excelItems.get(i).getGrngvnDate());

				String type;
				if (excelItems.get(i).getIsGrn() == 1) {
					type = "GRN";

				} else {
					type = "GVN";
				}
				rowData.add(type);
				rowData.add(excelItems.get(i).getGrngvnSrno());
				rowData.add(excelItems.get(i).getFrName());
				rowData.add("" + excelItems.get(i).getReqQty());
				rowData.add("" + excelItems.get(i).getTotalAmt());
				rowData.add("" + excelItems.get(i).getAprQty());

				rowData.add("" + excelItems.get(i).getAprGrandTotal());

				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);

			}

			HttpSession session = request.getSession();
			session.setAttribute("exportExcelList", exportToExcelList);
			session.setAttribute("excelName", "grnGvnReport");

		} catch (Exception e) {

			System.out.println("Ex in getting /getgGReportByDate List  Ajax call" + e.getMessage());
			e.printStackTrace();
		}

		return grnGvnByDateList;

	}
	// showGGreportByDate PDF

	@RequestMapping(value = "pdf/showGGreportByDate/{fDate}/{tDate}/{selectedFr}/{routeId}/{isGrn}", method = RequestMethod.GET)
	public ModelAndView showGGreportByDatePdf(@PathVariable String fDate, @PathVariable String tDate,
			@PathVariable String selectedFr, @PathVariable String routeId, @PathVariable int isGrn,
			HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/grnGvn/pdf/r1");

		List<GGReportByDateAndFr> grnGvnByDateList = new ArrayList<>();

		System.err.println("Inside PDF mapping");

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
				map.add("frIdList", 0);

			} else {
				System.out.println("Inside else Few fr Selected ");

				map.add("frIdList", selectedFr);

			}

			if (isGrn == 2) {
				System.err.println("Is Grn ==2");

				map.add("isGrn", "1" + "," + "0");
			} else {
				System.err.println("Is Grn  not Eq 2");

				map.add("isGrn", isGrn);

			}

			map.add("fromDate", DateConvertor.convertToYMD(fDate));
			map.add("toDate", DateConvertor.convertToYMD(tDate));
			// map.add("isGrn", isGrn);

			ParameterizedTypeReference<List<GGReportByDateAndFr>> typeRef = new ParameterizedTypeReference<List<GGReportByDateAndFr>>() {
			};
			ResponseEntity<List<GGReportByDateAndFr>> responseEntity = restTemplate
					.exchange(Constants.url + "getgGReportByDate", HttpMethod.POST, new HttpEntity<>(map), typeRef);

			grnGvnByDateList = responseEntity.getBody();

			System.err.println("List---- " + grnGvnByDateList.toString());

		} catch (Exception e) {
			System.err.println("Exc in GRN PDF report 2");
			e.printStackTrace();
		}

		model.addObject("fromDate", fDate);

		model.addObject("toDate", tDate);

		model.addObject("report", grnGvnByDateList);

		return model;
	}

	// r2
	@RequestMapping(value = "/showGGReportGrpByFr", method = RequestMethod.GET)
	public ModelAndView showGGReportGrpByFr(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/grnGvn/ggGrpByFr");

		try {

			ZoneId z = ZoneId.of("Asia/Calcutta");

			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			String todaysDate = date.format(formatters);

			allFrIdNameList = getFrNameId();

			allRouteListResponse = getAllRoute();

			model.addObject("routeList", allRouteListResponse.getRoute());
			model.addObject("todaysDate", todaysDate);
			model.addObject("unSelectedFrList", allFrIdNameList.getFrIdNamesList());

		} catch (Exception e) {
			System.out.println("Exce inshowGGReportDateWise " + e.getMessage());
			e.printStackTrace();
		}

		return model;
	}

	// consume r2 web service Ajax call
	@RequestMapping(value = "/getGrnGvnByGrpByFr", method = RequestMethod.GET)
	@ResponseBody
	public List<GGReportGrpByFrId> getGrnGvnByGrpByFr(HttpServletRequest request, HttpServletResponse response) {

		List<GGReportGrpByFrId> grnGvnGrpByFrList = new ArrayList<>();

		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			RestTemplate restTemplate = new RestTemplate();

			String routeId = "0";
			String frIdString = "";

			System.out.println("inside getGrnGvnByDatewise ajax call");

			frIdString = request.getParameter("fr_id_list");
			String fromDate = request.getParameter("from_date");
			String toDate = request.getParameter("to_date");
			routeId = request.getParameter("route_id");
			String isGrn = request.getParameter("is_grn");
			System.err.println("Is Grn " + isGrn);
			String grnType;
			if (isGrn.equalsIgnoreCase("2")) {

				System.err.println("Is Grn =2");
				grnType = "0,1,2";

				map.add("isGrn", grnType);
			} else if (isGrn.equalsIgnoreCase("0")) {
				System.err.println("Its GVN ");
				grnType = "0,2";
			} else {

				System.err.println("Is Grn not =2");
				grnType = isGrn;
				map.add("isGrn", isGrn);

			}

			System.out.println("fromDate= " + fromDate);

			boolean isAllFrSelected = false;

			frIdString = frIdString.substring(1, frIdString.length() - 1);
			frIdString = frIdString.replaceAll("\"", "");

			List<String> franchIds = new ArrayList();
			franchIds = Arrays.asList(frIdString);

			System.out.println("fr Id ArrayList " + franchIds.toString());

			if (franchIds.contains("-1")) {
				isAllFrSelected = true;

			}

			if (!routeId.equalsIgnoreCase("0")) {

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
				frIdString = strFrIdRouteWise.substring(0, strFrIdRouteWise.length() - 1);
				System.out.println("fr Id Route WISE = " + frIdString);

			} // end of if

			map = new LinkedMultiValueMap<String, Object>();
			if (isAllFrSelected) {

				System.out.println("Inside IF  is All fr Selected " + isAllFrSelected);

				map.add("frIdList", 0);
				// model.addObject("billHeadersList",billHeadersListForPrint);

			} else { // few franchisee selected

				System.out.println("Inside Else: Few Fr Selected ");
				map.add("frIdList", frIdString);

			}

			map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			map.add("toDate", DateConvertor.convertToYMD(toDate));
			map.add("isGrn", grnType);

			ParameterizedTypeReference<List<GGReportGrpByFrId>> typeRef = new ParameterizedTypeReference<List<GGReportGrpByFrId>>() {
			};
			ResponseEntity<List<GGReportGrpByFrId>> responseEntity = restTemplate
					.exchange(Constants.url + "gGReportGrpByFrId", HttpMethod.POST, new HttpEntity<>(map), typeRef);

			grnGvnGrpByFrList = responseEntity.getBody();

			System.err.println("List " + grnGvnGrpByFrList.toString());

			List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

			ExportToExcel expoExcel = new ExportToExcel();
			List<String> rowData = new ArrayList<String>();

			rowData.add("Sr. No.");
			rowData.add("Type");
			rowData.add("Franchise Name");
			rowData.add("Req Qty");
			rowData.add("Req Value");
			rowData.add("Apr Qty");
			rowData.add("Apr Value");

			rowData.add("Fr Contri Value");

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);
			List<GGReportGrpByFrId> excelItems = grnGvnGrpByFrList;
			for (int i = 0; i < excelItems.size(); i++) {
				expoExcel = new ExportToExcel();
				rowData = new ArrayList<String>();
				rowData.add("" + (i + 1));

				String type;
				if (isGrn.equalsIgnoreCase("1")) {
					type = "GRN";

				} else if (isGrn.equalsIgnoreCase("0")) {
					type = "GVN";
				} else {
					type = "All";
				}
				rowData.add(type);
				rowData.add(excelItems.get(i).getFrName());
				rowData.add("" + excelItems.get(i).getReqQty());
				rowData.add("" + excelItems.get(i).getTotalAmt());
				rowData.add("" + excelItems.get(i).getAprQty());

				rowData.add("" + excelItems.get(i).getAprGrandTotal());
		/*	float 	frCont=0;
			if (isGrn.equalsIgnoreCase("1")|| isGrn.equalsIgnoreCase("2")) {
				float value1=excelItems.get(i).getAprGrandTotal()*100;
				float billValue=(value1)/75;
				 frCont=(float) ((billValue)*0.25);
			}*/
			  	
				rowData.add("" + roundUp( excelItems.get(i).getFrContr()));

				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);

			}

			HttpSession session = request.getSession();
			session.setAttribute("exportExcelList", exportToExcelList);
			session.setAttribute("excelName", "grnGvnReport");

		} catch (Exception e) {

			System.out.println("Ex in getting /getGrnGvnByGrpByFr List  Ajax call" + e.getMessage());
			e.printStackTrace();
		}

		return grnGvnGrpByFrList;

	}
	public static float roundUp(float d) {
		return BigDecimal.valueOf(d).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
	}

	// r2 PDFshowGGreportGrpByFr

	@RequestMapping(value = "pdf/showGGreportGrpByFr/{fDate}/{tDate}/{selectedFr}/{routeId}/{isGrn}", method = RequestMethod.GET)
	public ModelAndView showSaleReportByDatePdf(@PathVariable String fDate, @PathVariable String tDate,
			@PathVariable String selectedFr, @PathVariable String routeId, @PathVariable int isGrn,
			HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/grnGvn/pdf/r2");

		List<GGReportGrpByFrId> grnGvnGrpByFrList = new ArrayList<>();

		System.err.println("Inside PDF mapping");

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
				map.add("frIdList", 0);

			} else {
				System.out.println("Inside else Few fr Selected ");

				map.add("frIdList", selectedFr);

			}

			/*
			 * if(isGrn==2) { System.err.println("Is Grn ==2");
			 * 
			 * map.add("isGrn", "1"+","+"0"); } else {
			 * System.err.println("Is Grn  not Eq 2");
			 * 
			 * map.add("isGrn", isGrn);
			 * 
			 * }
			 */

			String grnType;
			if (isGrn == 2) {

				System.err.println("Is Grn =2");
				grnType = "1" + "," + "0" + "," + "2";

				//map.add("isGrn", grnType);
			} else if (isGrn == 0) {
				System.err.println("Its GVN ");
				grnType = "0" + "," + "2";
			} else {

				System.err.println("Is Grn not =2");
				grnType = "1";
				

			}
			map.add("isGrn", grnType);
			map.add("fromDate", DateConvertor.convertToYMD(fDate));
			map.add("toDate", DateConvertor.convertToYMD(tDate));
			// map.add("isGrn", isGrn);

			ParameterizedTypeReference<List<GGReportGrpByFrId>> typeRef = new ParameterizedTypeReference<List<GGReportGrpByFrId>>() {
			};
			ResponseEntity<List<GGReportGrpByFrId>> responseEntity = restTemplate
					.exchange(Constants.url + "gGReportGrpByFrId", HttpMethod.POST, new HttpEntity<>(map), typeRef);

			grnGvnGrpByFrList = responseEntity.getBody();

			System.err.println("List---- " + grnGvnGrpByFrList.toString());

		} catch (Exception e) {
			System.err.println("Exc in GRN PDF report 2");
			e.printStackTrace();
		}

		model.addObject("fromDate", fDate);

		model.addObject("toDate", tDate);

		model.addObject("report", grnGvnGrpByFrList);
		
		model.addObject("isGrn", isGrn);

		return model;
	}

	// r3

	@RequestMapping(value = "/showGGReportGrpByDate", method = RequestMethod.GET)
	public ModelAndView showGGReportGrpByDate(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/grnGvn/grnGvnGrpByDate");

		try {

			ZoneId z = ZoneId.of("Asia/Calcutta");

			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			String todaysDate = date.format(formatters);

			allFrIdNameList = getFrNameId();

			allRouteListResponse = getAllRoute();

			model.addObject("routeList", allRouteListResponse.getRoute());
			model.addObject("todaysDate", todaysDate);
			model.addObject("unSelectedFrList", allFrIdNameList.getFrIdNamesList());

		} catch (Exception e) {
			System.out.println("Exce inshowGGReportDateWise " + e.getMessage());
			e.printStackTrace();
		}

		return model;
	}

	// consume r3 web service ajax call
	//

	@RequestMapping(value = "/getGrnGvnByGrpByDate", method = RequestMethod.GET)
	@ResponseBody
	public List<GGReportGrpByMonthDate> getGrnGvnByGrpByDate(HttpServletRequest request, HttpServletResponse response) {

		List<GGReportGrpByMonthDate> grnGvnGrpByDateList = new ArrayList<>();

		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			RestTemplate restTemplate = new RestTemplate();

			String routeId = "0";
			String frIdString = "";

			System.out.println("inside getGrnGvnByDatewise ajax call");

			frIdString = request.getParameter("fr_id_list");
			String fromDate = request.getParameter("from_date");
			String toDate = request.getParameter("to_date");
			routeId = request.getParameter("route_id");
			String isGrn = request.getParameter("is_grn");

			String grnType;
			if (isGrn.equalsIgnoreCase("2")) {

				System.err.println("Is Grn =2");
				grnType = "1" + "," + "0";

				map.add("isGrn", grnType);
			} else {
				System.err.println("Is Grn not =2");
				grnType = isGrn;
				map.add("isGrn", isGrn);

			}

			System.out.println("fromDate= " + fromDate);

			boolean isAllFrSelected = false;

			frIdString = frIdString.substring(1, frIdString.length() - 1);
			frIdString = frIdString.replaceAll("\"", "");

			List<String> franchIds = new ArrayList();
			franchIds = Arrays.asList(frIdString);

			System.out.println("fr Id ArrayList " + franchIds.toString());

			if (franchIds.contains("-1")) {
				isAllFrSelected = true;

			}

			if (!routeId.equalsIgnoreCase("0")) {

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
				frIdString = strFrIdRouteWise.substring(0, strFrIdRouteWise.length() - 1);
				System.out.println("fr Id Route WISE = " + frIdString);

			} // end of if

			map = new LinkedMultiValueMap<String, Object>();
			if (isAllFrSelected) {

				System.out.println("Inside IF  is All fr Selected " + isAllFrSelected);

				map.add("frIdList", 0);
				// model.addObject("billHeadersList",billHeadersListForPrint);

			} else { // few franchisee selected

				System.out.println("Inside Else: Few Fr Selected ");
				map.add("frIdList", frIdString);

			}

			map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			map.add("toDate", DateConvertor.convertToYMD(toDate));
			map.add("isGrn", grnType);
			map.add("frIdList", 0);

			ParameterizedTypeReference<List<GGReportGrpByMonthDate>> typeRef = new ParameterizedTypeReference<List<GGReportGrpByMonthDate>>() {
			};
			ResponseEntity<List<GGReportGrpByMonthDate>> responseEntity = restTemplate
					.exchange(Constants.url + "getGGReportGrpByDate", HttpMethod.POST, new HttpEntity<>(map), typeRef);

			grnGvnGrpByDateList = responseEntity.getBody();

			System.err.println("List " + grnGvnGrpByDateList.toString());

			List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

			ExportToExcel expoExcel = new ExportToExcel();
			List<String> rowData = new ArrayList<String>();

			rowData.add("Sr. No.");
			rowData.add("Date");

			rowData.add("Type");
			rowData.add("Req Qty");
			rowData.add("Req Value");
			rowData.add("Apr Qty");
			rowData.add("Apr Value");

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);
			List<GGReportGrpByMonthDate> excelItems = grnGvnGrpByDateList;
			for (int i = 0; i < excelItems.size(); i++) {
				expoExcel = new ExportToExcel();
				rowData = new ArrayList<String>();
				rowData.add("" + (i + 1));

				String type;
				if (excelItems.get(i).getIsGrn() == 1) {
					type = "GRN";

				} else {
					type = "GVN";
				}
				rowData.add(excelItems.get(i).getGrnGvnDate());
				rowData.add(type);
				rowData.add("" + excelItems.get(i).getReqQty());
				rowData.add("" + excelItems.get(i).getTotalAmt());
				rowData.add("" + excelItems.get(i).getAprQty());

				rowData.add("" + excelItems.get(i).getAprGrandTotal());

				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);

			}

			HttpSession session = request.getSession();
			session.setAttribute("exportExcelList", exportToExcelList);
			session.setAttribute("excelName", "grnGvnReport");

		} catch (Exception e) {

			System.out.println("Ex in getting /getGrnGvnByGrpByDate List  Ajax call" + e.getMessage());
			e.printStackTrace();
		}

		return grnGvnGrpByDateList;

	}

	// showGGreportGrpByDate r3 PDF

	@RequestMapping(value = "pdf/showGGreportGrpByDate/{fDate}/{tDate}/{selectedFr}/{routeId}/{isGrn}", method = RequestMethod.GET)
	public ModelAndView showGGreportGrpByDatePdf(@PathVariable String fDate, @PathVariable String tDate,
			@PathVariable String selectedFr, @PathVariable String routeId, @PathVariable int isGrn,
			HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/grnGvn/pdf/r3");

		List<GGReportGrpByMonthDate> grnGvnGrpByDateList = new ArrayList<>();

		System.err.println("Inside PDF mapping");

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
				map.add("frIdList", 0);

			} else {
				System.out.println("Inside else Few fr Selected ");

				map.add("frIdList", selectedFr);

			}

			if (isGrn == 2) {
				System.err.println("Is Grn ==2");

				map.add("isGrn", "1" + "," + "0");
			} else {
				System.err.println("Is Grn  not Eq 2");

				map.add("isGrn", isGrn);

			}

			map.add("fromDate", DateConvertor.convertToYMD(fDate));
			map.add("toDate", DateConvertor.convertToYMD(tDate));
			// map.add("isGrn", isGrn);
			map.add("frIdList", 0);

			ParameterizedTypeReference<List<GGReportGrpByMonthDate>> typeRef = new ParameterizedTypeReference<List<GGReportGrpByMonthDate>>() {
			};
			ResponseEntity<List<GGReportGrpByMonthDate>> responseEntity = restTemplate
					.exchange(Constants.url + "getGGReportGrpByDate", HttpMethod.POST, new HttpEntity<>(map), typeRef);

			grnGvnGrpByDateList = responseEntity.getBody();

			System.err.println("List---- " + grnGvnGrpByDateList.toString());

		} catch (Exception e) {
			System.err.println("Exc in GRN PDF report 2");
			e.printStackTrace();
		}

		model.addObject("fromDate", fDate);

		model.addObject("toDate", tDate);

		model.addObject("report", grnGvnGrpByDateList);

		return model;
	}

	// r4

	@RequestMapping(value = "/showGGReportGrpByMonth", method = RequestMethod.GET)
	public ModelAndView showGGReportGrpByMonth(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/grnGvn/gGGrpByMonth");

		try {

			ZoneId z = ZoneId.of("Asia/Calcutta");

			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			String todaysDate = date.format(formatters);

			allFrIdNameList = getFrNameId();

			allRouteListResponse = getAllRoute();

			model.addObject("routeList", allRouteListResponse.getRoute());
			model.addObject("todaysDate", todaysDate);
			model.addObject("unSelectedFrList", allFrIdNameList.getFrIdNamesList());

		} catch (Exception e) {
			System.out.println("Exce inshowGGReportDateWise " + e.getMessage());
			e.printStackTrace();
		}

		return model;
	}

	// r4 consume web service

	@RequestMapping(value = "/getGrnGvnByGrpByMonth", method = RequestMethod.GET)
	@ResponseBody
	public List<GGReportGrpByMonthDate> getGrnGvnByGrpByMonth(HttpServletRequest request,
			HttpServletResponse response) {

		List<GGReportGrpByMonthDate> grnGvnGrpByMonthList = new ArrayList<>();

		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			RestTemplate restTemplate = new RestTemplate();

			String routeId = "0";
			String frIdString = "";

			System.out.println("inside getGrnGvnByDatewise ajax call");

			frIdString = request.getParameter("fr_id_list");
			String fromDate = request.getParameter("from_date");
			String toDate = request.getParameter("to_date");
			routeId = request.getParameter("route_id");
			String isGrn = request.getParameter("is_grn");

			String grnType;
			if (isGrn.equalsIgnoreCase("2")) {

				System.err.println("Is Grn =2");
				grnType = "1" + "," + "0";

				map.add("isGrn", grnType);
			} else {
				System.err.println("Is Grn not =2");
				grnType = isGrn;
				map.add("isGrn", isGrn);

			}

			System.out.println("fromDate= " + fromDate);

			boolean isAllFrSelected = false;

			frIdString = frIdString.substring(1, frIdString.length() - 1);
			frIdString = frIdString.replaceAll("\"", "");

			List<String> franchIds = new ArrayList();
			franchIds = Arrays.asList(frIdString);

			System.out.println("fr Id ArrayList " + franchIds.toString());

			if (franchIds.contains("-1")) {
				isAllFrSelected = true;

			}

			if (!routeId.equalsIgnoreCase("0")) {

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
				frIdString = strFrIdRouteWise.substring(0, strFrIdRouteWise.length() - 1);
				System.out.println("fr Id Route WISE = " + frIdString);

			} // end of if

			map = new LinkedMultiValueMap<String, Object>();
			if (isAllFrSelected) {

				System.out.println("Inside IF  is All fr Selected " + isAllFrSelected);

				map.add("frIdList", 0);
				// model.addObject("billHeadersList",billHeadersListForPrint);

			} else { // few franchisee selected

				System.out.println("Inside Else: Few Fr Selected ");
				map.add("frIdList", frIdString);

			}

			map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			map.add("toDate", DateConvertor.convertToYMD(toDate));
			map.add("isGrn", grnType);
			map.add("frIdList", 0);

			ParameterizedTypeReference<List<GGReportGrpByMonthDate>> typeRef = new ParameterizedTypeReference<List<GGReportGrpByMonthDate>>() {
			};
			ResponseEntity<List<GGReportGrpByMonthDate>> responseEntity = restTemplate
					.exchange(Constants.url + "getGGReportGrpByMonth", HttpMethod.POST, new HttpEntity<>(map), typeRef);

			grnGvnGrpByMonthList = responseEntity.getBody();

			System.err.println("List " + grnGvnGrpByMonthList.toString());

			List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

			ExportToExcel expoExcel = new ExportToExcel();
			List<String> rowData = new ArrayList<String>();

			rowData.add("Sr. No.");
			rowData.add("Month");

			rowData.add("Req Qty");
			rowData.add("Req Value");
			rowData.add("Apr Qty");
			rowData.add("Apr Value");

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);
			List<GGReportGrpByMonthDate> excelItems = grnGvnGrpByMonthList;
			for (int i = 0; i < excelItems.size(); i++) {
				expoExcel = new ExportToExcel();
				rowData = new ArrayList<String>();
				rowData.add("" + (i + 1));

				rowData.add(excelItems.get(i).getMonth());
				rowData.add("" + excelItems.get(i).getReqQty());
				rowData.add("" + excelItems.get(i).getTotalAmt());
				rowData.add("" + excelItems.get(i).getAprQty());

				rowData.add("" + excelItems.get(i).getAprGrandTotal());

				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);

			}

			HttpSession session = request.getSession();
			session.setAttribute("exportExcelList", exportToExcelList);
			session.setAttribute("excelName", "grnGvnReport");

		} catch (Exception e) {

			System.out.println("Ex in getting /getGrnGvnByGrpByDate List  Ajax call" + e.getMessage());
			e.printStackTrace();
		}

		return grnGvnGrpByMonthList;

	}

	// showGGreportGrpByMonth r4 PDF

	@RequestMapping(value = "pdf/showGGreportGrpByMonth/{fDate}/{tDate}/{selectedFr}/{routeId}/{isGrn}", method = RequestMethod.GET)
	public ModelAndView showGGreportGrpByMonthPdf(@PathVariable String fDate, @PathVariable String tDate,
			@PathVariable String selectedFr, @PathVariable String routeId, @PathVariable int isGrn,
			HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/grnGvn/pdf/r4");

		List<GGReportGrpByMonthDate> grnGvnGrpByDateList = new ArrayList<>();

		System.err.println("Inside PDF mapping");

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
				map.add("frIdList", 0);

			} else {
				System.out.println("Inside else Few fr Selected ");

				map.add("frIdList", selectedFr);

			}

			map.add("fromDate", DateConvertor.convertToYMD(fDate));
			map.add("toDate", DateConvertor.convertToYMD(tDate));

			if (isGrn == 2) {
				System.err.println("Is Grn ==2");

				map.add("isGrn", "1" + "," + "0");
			} else {
				System.err.println("Is Grn  not Eq 2");

				map.add("isGrn", isGrn);

			}
			// map.add("isGrn", isGrn);
			map.add("frIdList", 0);

			ParameterizedTypeReference<List<GGReportGrpByMonthDate>> typeRef = new ParameterizedTypeReference<List<GGReportGrpByMonthDate>>() {
			};
			ResponseEntity<List<GGReportGrpByMonthDate>> responseEntity = restTemplate
					.exchange(Constants.url + "getGGReportGrpByMonth", HttpMethod.POST, new HttpEntity<>(map), typeRef);

			grnGvnGrpByDateList = responseEntity.getBody();

			System.err.println("List---- " + grnGvnGrpByDateList.toString());

		} catch (Exception e) {
			System.err.println("Exc in GRN PDF report 2");
			e.printStackTrace();
		}

		model.addObject("fromDate", fDate);

		model.addObject("toDate", tDate);

		model.addObject("report", grnGvnGrpByDateList);

		return model;
	}

}
