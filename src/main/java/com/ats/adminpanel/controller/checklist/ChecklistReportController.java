package com.ats.adminpanel.controller.checklist;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
import com.ats.adminpanel.model.ExportToExcel;
import com.ats.adminpanel.model.billing.GetBillDetail;
import com.ats.adminpanel.model.billing.GetBillDetailsResponse;
import com.ats.adminpanel.model.checklist.ChecklistActionDetail;
import com.ats.adminpanel.model.checklist.ChecklistDetail;
import com.ats.adminpanel.model.checklist.ChecklistHeader;
import com.ats.adminpanel.model.checklist.DeptWiseChecklistCountReport;
import com.ats.adminpanel.model.hr.EmpDeptDisplay;
import com.ats.adminpanel.model.hr.EmpWiseVisitorReport;
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
public class ChecklistReportController {

	List<String> empList = new ArrayList<>();
	List<String> deptList = new ArrayList<>();

	List<DeptWiseChecklistCountReport> deptWiseReportList = new ArrayList<>();
	List<DeptWiseChecklistCountReport> deptWiseHeaderReportList = new ArrayList<>();
	
	List<ChecklistActionDetail> deptWiseChecklistDetailReportList = new ArrayList<>();
	
	

	@RequestMapping(value = "/showDeptWiseChecklistReport", method = RequestMethod.GET)
	public ModelAndView showEmpWiseGatepassReport(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("checklistReport/deptWiseReport");

		HttpSession session = request.getSession();

		System.out.println("session Id in show Page  " + session.getId());

		try {
			ZoneId z = ZoneId.of("Asia/Calcutta");

			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			String todaysDate = date.format(formatters);

			RestTemplate restTemplate = new RestTemplate();

			EmpDeptDisplay[] deptArray = restTemplate.getForObject(
					Constants.security_app_url + "master/allEmployeeDepartmentList", EmpDeptDisplay[].class);
			List<EmpDeptDisplay> deptList = new ArrayList<>(Arrays.asList(deptArray));

			model.addObject("todaysDate", todaysDate);
			model.addObject("deptList", deptList);

		} catch (Exception e) {

			System.out.println("Exc in show dept wise checklist report " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/getDeptWiseChecklistReport", method = RequestMethod.GET)
	public @ResponseBody List<DeptWiseChecklistCountReport> getDeptWiseChecklistReport(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");

			fromDate = DateConvertor.convertToYMD(fromDate);
			toDate = DateConvertor.convertToYMD(toDate);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			map.add("fromDate", fromDate);
			map.add("toDate", toDate);

			ParameterizedTypeReference<List<DeptWiseChecklistCountReport>> typeRef = new ParameterizedTypeReference<List<DeptWiseChecklistCountReport>>() {
			};
			ResponseEntity<List<DeptWiseChecklistCountReport>> responseEntity = restTemplate.exchange(
					Constants.security_app_url + "checklist/getReportByDeptGroupBy", HttpMethod.POST,
					new HttpEntity<>(map), typeRef);

			deptWiseReportList = responseEntity.getBody();
			System.err.println("REPORT ***********************" + deptWiseReportList.toString());

		} catch (Exception e) {
			System.out.println("get dept wise Report  " + e.getMessage());
			e.printStackTrace();

		}

		// exportToExcel

		List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

		ExportToExcel expoExcel = new ExportToExcel();
		List<String> rowData = new ArrayList<String>();

		rowData.add("Department");
		rowData.add("Pending");
		rowData.add("Approved");
		rowData.add("Rejected");

		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);
		for (int i = 0; i < deptWiseReportList.size(); i++) {
			expoExcel = new ExportToExcel();
			rowData = new ArrayList<String>();

			rowData.add("" + deptWiseReportList.get(i).getDeptName());
			rowData.add("" + deptWiseReportList.get(i).getPendingCount());
			rowData.add("" + deptWiseReportList.get(i).getApprovedCount());
			rowData.add("" + deptWiseReportList.get(i).getRejectedCount());

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);

		}

		HttpSession session = request.getSession();
		session.setAttribute("exportExcelList", exportToExcelList);
		session.setAttribute("excelName", "Department_Wise_Checklist_Report");

		return deptWiseReportList;
	}

	@RequestMapping(value = "/showDeptWiseChecklistReportPdf/{fromDate}/{toDate}", method = RequestMethod.GET)
	public void showDeptWiseChecklistReportPdf(@PathVariable("fromDate") String fromDate,
			@PathVariable("toDate") String toDate, HttpServletRequest request, HttpServletResponse response)
			throws FileNotFoundException {
		BufferedOutputStream outStream = null;
		System.out.println("Inside Pdf showDeptWiseChecklistReportPdf");
		Document document = new Document(PageSize.A4);

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();

		String FILE_PATH = Constants.REPORT_SAVE;
		// String FILE_PATH = Constants.APP_IMAGE_URL + "Report.pdf";
		File file = new File(FILE_PATH);

		PdfWriter writer = null;

		FileOutputStream out = new FileOutputStream(FILE_PATH);
		try {
			writer = PdfWriter.getInstance(document, out);
		} catch (DocumentException e) {

			e.printStackTrace();
		}

		PdfPTable table = new PdfPTable(5);
		try {
			System.out.println("Inside PDF Table try");
			table.setWidthPercentage(100);
			table.setWidths(new float[] { 2f, 4.5f, 2f, 2f, 2f });
			Font headFont = new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
			Font headFont1 = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
			headFont1.setColor(BaseColor.WHITE);
			Font f = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.UNDERLINE, BaseColor.BLUE);

			PdfPCell hcell = new PdfPCell();
			hcell.setBackgroundColor(BaseColor.PINK);

			hcell.setPadding(3);
			hcell = new PdfPCell(new Phrase("Sr.No.", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Department", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Pending", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Approved", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Rejected", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			int index = 0;

			System.err.println("REPORT LIST -------------------------------------------------- " + deptWiseReportList);

			for (DeptWiseChecklistCountReport data : deptWiseReportList) {
				index++;
				PdfPCell cell;

				cell = new PdfPCell(new Phrase(String.valueOf(index), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPadding(3);
				cell.setPaddingRight(2);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + data.getDeptName(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + data.getPendingCount(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + data.getApprovedCount(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + data.getRejectedCount(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

			}
			document.open();
			Paragraph name = new Paragraph("GFPL\n", f);
			name.setAlignment(Element.ALIGN_CENTER);
			document.add(name);
			document.add(new Paragraph(" "));
			Paragraph company = new Paragraph("Department Wise Checklist Report\n", f);
			company.setAlignment(Element.ALIGN_CENTER);
			document.add(company);
			document.add(new Paragraph(" "));

			DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
			String reportDate = DF.format(new Date());
			Paragraph p1 = new Paragraph("From Date:" + fromDate + "  To Date:" + toDate, headFont);
			p1.setAlignment(Element.ALIGN_CENTER);
			document.add(p1);
			document.add(new Paragraph("\n"));
			document.add(table);

			int totalPages = writer.getPageNumber();

			System.out.println("Page no " + totalPages);

			document.close();

			if (file != null) {

				String mimeType = URLConnection.guessContentTypeFromName(file.getName());

				if (mimeType == null) {

					mimeType = "application/pdf";

				}

				response.setContentType(mimeType);

				response.addHeader("content-disposition", String.format("inline; filename=\"%s\"", file.getName()));

				response.setContentLength((int) file.length());

				BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));

				try {
					FileCopyUtils.copy(inputStream, response.getOutputStream());
				} catch (IOException e) {
					System.out.println("Excep in Opening a Pdf File");
					e.printStackTrace();
				}
			}

		} catch (DocumentException ex) {

			System.out.println("Pdf Generation Error: " + ex.getMessage());

			ex.printStackTrace();

		}

	}

	@RequestMapping(value = "/showDeptChecklistHeaderReport/{fromDate}/{toDate}/{deptId}", method = RequestMethod.GET)
	public ModelAndView showDeptChecklistHeaderReport(@PathVariable String fromDate, @PathVariable String toDate,
			@PathVariable int deptId) {

		ModelAndView model = new ModelAndView("checklistReport/checklistHeaderWiseReport");

		try {

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("deptId", deptId);
			
			ParameterizedTypeReference<List<ChecklistHeader>> typeRef = new ParameterizedTypeReference<List<ChecklistHeader>>() {
			};
			ResponseEntity<List<ChecklistHeader>> responseEntity = restTemplate.exchange(
					Constants.security_app_url + "checklist/getAllChecklistByDept", HttpMethod.POST,
					new HttpEntity<>(map), typeRef);

			List<ChecklistHeader> checklistHeaderList = responseEntity.getBody();

			model.addObject("checklistHeaderList", checklistHeaderList);
			model.addObject("fromDate", fromDate);
			model.addObject("toDate", toDate);
			model.addObject("deptId", deptId);
			
			ArrayList<String> ids = new ArrayList<>();

			if (checklistHeaderList != null) {
				if (checklistHeaderList.size() > 0) {
					for (int i = 0; i < checklistHeaderList.size(); i++) {
						ids.add("" + checklistHeaderList.get(i).getChecklistHeaderId());
					}
				}
			}
			
			String headerId="";

			if (ids.size() > 0) {
				String temp = ids.toString();
				headerId = temp.substring(1, (temp.length() - 1));
				headerId = headerId.replaceAll("\"", "");

			}
			
			MultiValueMap<String, Object> map1 = new LinkedMultiValueMap<String, Object>();

			System.err.println("HEADER ID-------------------------------------- " + headerId);

			fromDate = DateConvertor.convertToYMD(fromDate);
			toDate = DateConvertor.convertToYMD(toDate);
			
			map1.add("fromDate", fromDate);
			map1.add("toDate", toDate);
			map1.add("headerId", headerId);

			ParameterizedTypeReference<List<DeptWiseChecklistCountReport>> typeRef1 = new ParameterizedTypeReference<List<DeptWiseChecklistCountReport>>() {
			};
			ResponseEntity<List<DeptWiseChecklistCountReport>> responseEntity1 = restTemplate.exchange(
					Constants.security_app_url + "checklist/getReportByChkHeaderGroupBy", HttpMethod.POST,
					new HttpEntity<>(map1), typeRef1);

			deptWiseHeaderReportList = responseEntity1.getBody();
			
			System.err.println("REPORT LIST DEPT----------------- "+deptWiseHeaderReportList);
			
			model.addObject("reportList", deptWiseHeaderReportList);
			
			

		} catch (Exception e) {

			System.out.println("exce in showDeptChecklistHeaderReport " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/getDeptHeaderChecklistReport", method = RequestMethod.GET)
	public @ResponseBody List<DeptWiseChecklistCountReport> getDeptHeaderChecklistReport(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
			String headerId = request.getParameter("headerId");
			int deptId = Integer.parseInt(request.getParameter("deptId"));

			fromDate = DateConvertor.convertToYMD(fromDate);
			toDate = DateConvertor.convertToYMD(toDate);

			RestTemplate restTemplate = new RestTemplate();

			if (headerId.equalsIgnoreCase("-1")) {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				map.add("deptId", deptId);

				ParameterizedTypeReference<List<ChecklistHeader>> typeRef = new ParameterizedTypeReference<List<ChecklistHeader>>() {
				};
				ResponseEntity<List<ChecklistHeader>> responseEntity = restTemplate.exchange(
						Constants.security_app_url + "checklist/getAllChecklistByDept", HttpMethod.POST,
						new HttpEntity<>(map), typeRef);

				List<ChecklistHeader> checklistHeaderList = responseEntity.getBody();

				System.err.println("HEADER LIST ------------------------------- " + checklistHeaderList);

				ArrayList<String> ids = new ArrayList<>();

				if (checklistHeaderList != null) {
					if (checklistHeaderList.size() > 0) {
						for (int i = 0; i < checklistHeaderList.size(); i++) {
							ids.add("" + checklistHeaderList.get(i).getChecklistHeaderId());
						}
					}
				}

				if (ids.size() > 0) {
					String temp = ids.toString();
					headerId = temp.substring(1, (temp.length() - 1));
					headerId = headerId.replaceAll("\"", "");

				}

			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			System.err.println("HEADER ID-------------------------------------- " + headerId);

			map.add("fromDate", fromDate);
			map.add("toDate", toDate);
			map.add("headerId", headerId);

			ParameterizedTypeReference<List<DeptWiseChecklistCountReport>> typeRef = new ParameterizedTypeReference<List<DeptWiseChecklistCountReport>>() {
			};
			ResponseEntity<List<DeptWiseChecklistCountReport>> responseEntity = restTemplate.exchange(
					Constants.security_app_url + "checklist/getReportByChkHeaderGroupBy", HttpMethod.POST,
					new HttpEntity<>(map), typeRef);

			deptWiseHeaderReportList = responseEntity.getBody();
			System.err.println("REPORT ***********************" + deptWiseHeaderReportList.toString());

		} catch (Exception e) {
			System.out.println("Error getDeptHeaderChecklistReport  " + e.getMessage());
			e.printStackTrace();

		}

		// exportToExcel

		List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

		ExportToExcel expoExcel = new ExportToExcel();
		List<String> rowData = new ArrayList<String>();

		rowData.add("Department");
		rowData.add("Checklist Name");
		rowData.add("Pending");
		rowData.add("Approved");
		rowData.add("Rejected");

		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);
		for (int i = 0; i < deptWiseHeaderReportList.size(); i++) {
			expoExcel = new ExportToExcel();
			rowData = new ArrayList<String>();

			rowData.add("" + deptWiseHeaderReportList.get(i).getDeptName());
			rowData.add("" + deptWiseHeaderReportList.get(i).getChecklistName());
			rowData.add("" + deptWiseHeaderReportList.get(i).getPendingCount());
			rowData.add("" + deptWiseHeaderReportList.get(i).getApprovedCount());
			rowData.add("" + deptWiseHeaderReportList.get(i).getRejectedCount());

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);

		}

		HttpSession session = request.getSession();
		session.setAttribute("exportExcelList", exportToExcelList);
		session.setAttribute("excelName", "Dept_Wise_Checklist_Name_Report");

		return deptWiseHeaderReportList;
	}
	
	
	@RequestMapping(value = "/showDeptHeaderChecklistReportPdf/{fromDate}/{toDate}", method = RequestMethod.GET)
	public void showDeptHeaderChecklistReportPdf(@PathVariable("fromDate") String fromDate,
			@PathVariable("toDate") String toDate, HttpServletRequest request, HttpServletResponse response)
			throws FileNotFoundException {
		BufferedOutputStream outStream = null;
		System.out.println("Inside Pdf showDeptHeaderChecklistReportPdf");
		Document document = new Document(PageSize.A4);

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();

		String FILE_PATH = Constants.REPORT_SAVE;
		// String FILE_PATH = Constants.APP_IMAGE_URL + "Report.pdf";
		File file = new File(FILE_PATH);

		PdfWriter writer = null;

		FileOutputStream out = new FileOutputStream(FILE_PATH);
		try {
			writer = PdfWriter.getInstance(document, out);
		} catch (DocumentException e) {

			e.printStackTrace();
		}

		PdfPTable table = new PdfPTable(6);
		try {
			System.out.println("Inside PDF Table try");
			table.setWidthPercentage(100);
			table.setWidths(new float[] { 2f, 3f, 3f,2f,2f,2f });
			Font headFont = new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
			Font headFont1 = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
			headFont1.setColor(BaseColor.WHITE);
			Font f = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.UNDERLINE, BaseColor.BLUE);

			PdfPCell hcell = new PdfPCell();
			hcell.setBackgroundColor(BaseColor.PINK);

			hcell.setPadding(3);
			hcell = new PdfPCell(new Phrase("Sr.No.", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Department", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("Checklist Name", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Pending", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Approved", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Rejected", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			int index = 0;

			System.err.println("REPORT LIST -------------------------------------------------- " + deptWiseHeaderReportList);

			for (DeptWiseChecklistCountReport data : deptWiseHeaderReportList) {
				index++;
				PdfPCell cell;

				cell = new PdfPCell(new Phrase(String.valueOf(index), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPadding(3);
				cell.setPaddingRight(2);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + data.getDeptName(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase("" + data.getChecklistName(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + data.getPendingCount(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + data.getApprovedCount(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + data.getRejectedCount(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

			}
			document.open();
			Paragraph name = new Paragraph("GFPL\n", f);
			name.setAlignment(Element.ALIGN_CENTER);
			document.add(name);
			document.add(new Paragraph(" "));
			Paragraph company = new Paragraph("Department Wise Checklist Name Report\n", f);
			company.setAlignment(Element.ALIGN_CENTER);
			document.add(company);
			document.add(new Paragraph(" "));

			DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
			String reportDate = DF.format(new Date());
			Paragraph p1 = new Paragraph("From Date:" + fromDate + "  To Date:" + toDate, headFont);
			p1.setAlignment(Element.ALIGN_CENTER);
			document.add(p1);
			document.add(new Paragraph("\n"));
			document.add(table);

			int totalPages = writer.getPageNumber();

			System.out.println("Page no " + totalPages);

			document.close();

			if (file != null) {

				String mimeType = URLConnection.guessContentTypeFromName(file.getName());

				if (mimeType == null) {

					mimeType = "application/pdf";

				}

				response.setContentType(mimeType);

				response.addHeader("content-disposition", String.format("inline; filename=\"%s\"", file.getName()));

				response.setContentLength((int) file.length());

				BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));

				try {
					FileCopyUtils.copy(inputStream, response.getOutputStream());
				} catch (IOException e) {
					System.out.println("Excep in Opening a Pdf File");
					e.printStackTrace();
				}
			}

		} catch (DocumentException ex) {

			System.out.println("Pdf Generation Error: " + ex.getMessage());

			ex.printStackTrace();

		}

	}
	
	
	@RequestMapping(value = "/showDeptChecklistDetailReport/{headerId}", method = RequestMethod.GET)
	public ModelAndView showDeptChecklistDetailReport(@PathVariable int headerId) {

		ModelAndView model = new ModelAndView("checklistReport/deptChecklistDetailReport");

		try {

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("headerId", headerId);
			
			

			/*deptWiseChecklistDetailReportList = restTemplate
					.postForObject(Constants.security_app_url + "checklist/getChecklistActionDetail", map, List.class);
			*/
			ParameterizedTypeReference<List<ChecklistActionDetail>> typeRef = new ParameterizedTypeReference<List<ChecklistActionDetail>>() {
			};
			ResponseEntity<List<ChecklistActionDetail>> responseEntity = restTemplate.exchange(
					Constants.security_app_url + "checklist/getChecklistActionDetail", HttpMethod.POST,
					new HttpEntity<>(map), typeRef);

			deptWiseChecklistDetailReportList = responseEntity.getBody();
			

			model.addObject("reportList", deptWiseChecklistDetailReportList);
			
			
			// exportToExcel

			/*List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

			ExportToExcel expoExcel = new ExportToExcel();
			List<String> rowData = new ArrayList<String>();

			rowData.add("Checklist Details");
			rowData.add("Status");

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);
			for (int i = 0; i < checklistDetailList.size(); i++) {
				expoExcel = new ExportToExcel();
				rowData = new ArrayList<String>();

				rowData.add("" + checklistDetailList.get(i).getChecklist_desc());
				
				if(checklistDetailList.get(i).getCheckStatus()==0) {
					rowData.add("Pending");
				}else if(checklistDetailList.get(i).getCheckStatus()==1) {
					rowData.add("Action Taken");
				}else if(checklistDetailList.get(i).getCheckStatus()==2) {
					rowData.add("Approved");
				}else if(checklistDetailList.get(i).getCheckStatus()==3) {
					rowData.add("Rejected");
				}

				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);

			}

			HttpSession session = request.getSession();
			session.setAttribute("exportExcelList", exportToExcelList);
			session.setAttribute("excelName", "Dept_Wise_Checklist_Detail_Report");*/
		

		} catch (Exception e) {
			System.out.println("exce in showDeptChecklistDetailReport " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}
	
	
	@RequestMapping(value = "/showDeptChecklistDetailReportPdf", method = RequestMethod.GET)
	public void showDeptChecklistDetailReportPdf(HttpServletRequest request, HttpServletResponse response)
			throws FileNotFoundException {
		BufferedOutputStream outStream = null;
		System.out.println("Inside Pdf showDeptChecklistDetailReportPdf");
		Document document = new Document(PageSize.A4);

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();

		String FILE_PATH = Constants.REPORT_SAVE;
		// String FILE_PATH = Constants.APP_IMAGE_URL + "Report.pdf";
		File file = new File(FILE_PATH);

		PdfWriter writer = null;

		FileOutputStream out = new FileOutputStream(FILE_PATH);
		try {
			writer = PdfWriter.getInstance(document, out);
		} catch (DocumentException e) {

			e.printStackTrace();
		}

		PdfPTable table = new PdfPTable(3);
		try {
			System.out.println("Inside PDF Table try");
			table.setWidthPercentage(100);
			table.setWidths(new float[] { 2f, 4.5f, 2.2f });
			Font headFont = new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
			Font headFont1 = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
			headFont1.setColor(BaseColor.WHITE);
			Font f = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.UNDERLINE, BaseColor.BLUE);

			PdfPCell hcell = new PdfPCell();
			hcell.setBackgroundColor(BaseColor.PINK);

			hcell.setPadding(3);
			hcell = new PdfPCell(new Phrase("Sr.No.", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Checklist Detail", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Status", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			

			int index = 0;


			for (ChecklistActionDetail data : deptWiseChecklistDetailReportList) {
				index++;
				PdfPCell cell;

				cell = new PdfPCell(new Phrase(String.valueOf(index), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPadding(3);
				cell.setPaddingRight(2);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + data.getChecklist_desc(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);
				
				if(data.getCheckStatus()==0) {
					
					cell = new PdfPCell(new Phrase("Pending", headFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setPaddingRight(2);
					cell.setPadding(3);
					table.addCell(cell);
					
				}else if(data.getCheckStatus()==1) {
					
					cell = new PdfPCell(new Phrase("Action Taken", headFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setPaddingRight(2);
					cell.setPadding(3);
					table.addCell(cell);
					
				} else if(data.getCheckStatus()==2) {
					
					cell = new PdfPCell(new Phrase("Approved", headFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setPaddingRight(2);
					cell.setPadding(3);
					table.addCell(cell);
					
				}else if(data.getCheckStatus()==3) {
					
					cell = new PdfPCell(new Phrase("Rejected", headFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setPaddingRight(2);
					cell.setPadding(3);
					table.addCell(cell);
					
				}
				
				

			}
			document.open();
			Paragraph name = new Paragraph("GFPL\n", f);
			name.setAlignment(Element.ALIGN_CENTER);
			document.add(name);
			document.add(new Paragraph(" "));
			Paragraph company = new Paragraph("Department Wise Checklist Detail Report\n", f);
			company.setAlignment(Element.ALIGN_CENTER);
			document.add(company);
			document.add(new Paragraph(" "));

			//DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
			//String reportDate = DF.format(new Date());
			//Paragraph p1 = new Paragraph("From Date:" + fromDate + "  To Date:" + toDate, headFont);
			//p1.setAlignment(Element.ALIGN_CENTER);
			//document.add(p1);
			document.add(new Paragraph("\n"));
			document.add(table);

			int totalPages = writer.getPageNumber();

			System.out.println("Page no " + totalPages);

			document.close();

			if (file != null) {

				String mimeType = URLConnection.guessContentTypeFromName(file.getName());

				if (mimeType == null) {

					mimeType = "application/pdf";

				}

				response.setContentType(mimeType);

				response.addHeader("content-disposition", String.format("inline; filename=\"%s\"", file.getName()));

				response.setContentLength((int) file.length());

				BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));

				try {
					FileCopyUtils.copy(inputStream, response.getOutputStream());
				} catch (IOException e) {
					System.out.println("Excep in Opening a Pdf File");
					e.printStackTrace();
				}
			}

		} catch (DocumentException ex) {

			System.out.println("Pdf Generation Error: " + ex.getMessage());

			ex.printStackTrace();

		}

	}
	
	

}
