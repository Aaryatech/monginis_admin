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
import com.ats.adminpanel.model.checklist.ChecklistActionDetail;
import com.ats.adminpanel.model.checklist.ChecklistHeader;
import com.ats.adminpanel.model.checklist.DeptWiseChecklistCountReport;
import com.ats.adminpanel.model.checklist.EmpWiseHeaderReport;
import com.ats.adminpanel.model.checklist.EmpWiseReport;
import com.ats.adminpanel.model.hr.EmpDeptDisplay;
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
public class ChecklistReportEmpWiseController {
	
	 List<EmpWiseReport> empWiseTotalReportList=new ArrayList<>();
	 List<EmpWiseHeaderReport> empHeaderReportList=new ArrayList<>();
	 List<ChecklistActionDetail> empChecklistDetailList=new ArrayList<>();

	@RequestMapping(value = "/showEmpWiseChecklistReport", method = RequestMethod.GET)
	public ModelAndView showEmpWiseChecklistReport(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("checklistReport/empWiseReport");

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
	
	@RequestMapping(value = "/getEmpWiseChecklistReport", method = RequestMethod.GET)
	public @ResponseBody List<EmpWiseReport> getEmpWiseChecklistReport(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
			String deptId = request.getParameter("selectedDept");

			fromDate = DateConvertor.convertToYMD(fromDate);
			toDate = DateConvertor.convertToYMD(toDate);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

					
			if (deptId.equalsIgnoreCase("-1")) {

				MultiValueMap<String, Object> map1 = new LinkedMultiValueMap<String, Object>();

				EmpDeptDisplay[] deptArray = restTemplate.getForObject(
						Constants.security_app_url + "master/allEmployeeDepartmentList", EmpDeptDisplay[].class);
				List<EmpDeptDisplay> deptList = new ArrayList<>(Arrays.asList(deptArray));

				ArrayList<String> ids = new ArrayList<>();

				if (deptList != null) {
					if (deptList.size() > 0) {
						for (int i = 0; i < deptList.size(); i++) {
							ids.add("" + deptList.get(i).getEmpDeptId());
						}
					}
				}

				if (ids.size() > 0) {
					String temp = ids.toString();
					deptId = temp.substring(1, (temp.length() - 1));
					deptId = deptId.replaceAll("\"", "");

				}

			}
			
			
			map.add("fromDate", fromDate);
			map.add("toDate", toDate);
			map.add("deptId", deptId);
			

			ParameterizedTypeReference<List<EmpWiseReport>> typeRef = new ParameterizedTypeReference<List<EmpWiseReport>>() {
			};
			ResponseEntity<List<EmpWiseReport>> responseEntity = restTemplate.exchange(
					Constants.security_app_url + "checklist/getReportEmpWise", HttpMethod.POST,
					new HttpEntity<>(map), typeRef);

			empWiseTotalReportList = responseEntity.getBody();
			System.err.println("REPORT ***********************" + empWiseTotalReportList.toString());

		} catch (Exception e) {
			System.out.println("get emp wise Report  " + e.getMessage());
			e.printStackTrace();

		}

		// exportToExcel

		List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

		ExportToExcel expoExcel = new ExportToExcel();
		List<String> rowData = new ArrayList<String>();

		rowData.add("Employee");
		rowData.add("Assign Duty");
		rowData.add("Total Task");
		rowData.add("Total Detail Task");
		rowData.add("Total Pending");
		rowData.add("Total Approved");
		rowData.add("Total Rejected");

		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);
		for (int i = 0; i < empWiseTotalReportList.size(); i++) {
			expoExcel = new ExportToExcel();
			rowData = new ArrayList<String>();

			rowData.add("" + empWiseTotalReportList.get(i).getEmpName());
			rowData.add("" + empWiseTotalReportList.get(i).getTotalAssign());
			rowData.add("" + empWiseTotalReportList.get(i).getTotalTask());
			rowData.add("" + empWiseTotalReportList.get(i).getTotalDetailTask());
			rowData.add("" + empWiseTotalReportList.get(i).getPendingCount());
			rowData.add("" + empWiseTotalReportList.get(i).getApprovedCount());
			rowData.add("" + empWiseTotalReportList.get(i).getRejectedCount());

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);

		}

		HttpSession session = request.getSession();
		session.setAttribute("exportExcelList", exportToExcelList);
		session.setAttribute("excelName", "Employee_Wise_Checklist_Report");

		return empWiseTotalReportList;
	}
	
	
	@RequestMapping(value = "/showEmpWiseChecklistReportPdf/{fromDate}/{toDate}", method = RequestMethod.GET)
	public void showEmpWiseChecklistReportPdf(@PathVariable("fromDate") String fromDate,
			@PathVariable("toDate") String toDate, HttpServletRequest request, HttpServletResponse response)
			throws FileNotFoundException {
		BufferedOutputStream outStream = null;
		System.out.println("Inside Pdf showEmpWiseChecklistReportPdf");
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

		PdfPTable table = new PdfPTable(8);
		try {
			System.out.println("Inside PDF Table try");
			table.setWidthPercentage(100);
			table.setWidths(new float[] { 1.3f, 3f, 2f, 2f, 2f, 2f, 2f, 2f });
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

			hcell = new PdfPCell(new Phrase("Employee", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("Assign Duty", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Total Task", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("Total Detail Task", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);


			
			hcell = new PdfPCell(new Phrase("Total Pending", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Total Approved", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Total Rejected", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			int index = 0;

			System.err.println("REPORT LIST -------------------------------------------------- " + empWiseTotalReportList);

			for (EmpWiseReport data : empWiseTotalReportList) {
				index++;
				PdfPCell cell;

				cell = new PdfPCell(new Phrase(String.valueOf(index), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPadding(3);
				cell.setPaddingRight(2);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + data.getEmpName(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase("" + data.getTotalAssign(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + data.getTotalTask(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				
				cell = new PdfPCell(new Phrase("" + data.getTotalDetailTask(), headFont));
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
			Paragraph company = new Paragraph("Employee Wise Checklist Report\n", f);
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
	
	
	
	@RequestMapping(value = "/showEmpChecklistHeaderReport/{fromDate}/{toDate}/{empId}/{empName}", method = RequestMethod.GET)
	public ModelAndView showEmpChecklistHeaderReport(@PathVariable String fromDate, @PathVariable String toDate,
			@PathVariable int empId,@PathVariable String empName) {

		ModelAndView model = new ModelAndView("checklistReport/empWiseHeaderReport");

		try {

			RestTemplate restTemplate = new RestTemplate();

			model.addObject("fromDate", fromDate);
			model.addObject("toDate", toDate);
			model.addObject("empId", empId);
			model.addObject("empName", empName);
			
			MultiValueMap<String, Object> map2 = new LinkedMultiValueMap<String, Object>();
			
			fromDate = DateConvertor.convertToYMD(fromDate);
			toDate = DateConvertor.convertToYMD(toDate);

			map2.add("fromDate", fromDate);
			map2.add("toDate", toDate);
			map2.add("empId", empId);

			ParameterizedTypeReference<List<EmpWiseHeaderReport>> typeRef2 = new ParameterizedTypeReference<List<EmpWiseHeaderReport>>() {
			};
			ResponseEntity<List<EmpWiseHeaderReport>> responseEntity2 = restTemplate.exchange(
					Constants.security_app_url + "checklist/getReportEmpDetail", HttpMethod.POST,
					new HttpEntity<>(map2), typeRef2);

			empHeaderReportList = responseEntity2.getBody();

			model.addObject("reportList", empHeaderReportList);
			
			

		} catch (Exception e) {

			System.out.println("exce in showEmpChecklistHeaderReport " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}
	
	
	
	@RequestMapping(value = "/showEmpChecklistHeaderReportPdf/{fromDate}/{toDate}/{empName}", method = RequestMethod.GET)
	public void showEmpChecklistHeaderReportPdf(@PathVariable("fromDate") String fromDate,
			@PathVariable("toDate") String toDate,@PathVariable("empName") String empName, HttpServletRequest request, HttpServletResponse response)
			throws FileNotFoundException {
		BufferedOutputStream outStream = null;
		System.out.println("Inside Pdf showEmpChecklistHeaderReportPdf");
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

		PdfPTable table = new PdfPTable(9);
		try {
			System.out.println("Inside PDF Table try");
			table.setWidthPercentage(100);
			table.setWidths(new float[] { 1.3f, 3f, 2f, 2f, 2f, 2f, 2f, 2f,2f });
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

			hcell = new PdfPCell(new Phrase("Task Start Date", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("Checklist Name", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Task End Date", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("Closed By", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("Total Detail Task", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);


			
			hcell = new PdfPCell(new Phrase("Total Pending", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Total Approved", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Total Rejected", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			int index = 0;

			System.err.println("REPORT LIST -------------------------------------------------- " + empWiseTotalReportList);

			for (EmpWiseHeaderReport data : empHeaderReportList) {
				index++;
				PdfPCell cell;

				cell = new PdfPCell(new Phrase(String.valueOf(index), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPadding(3);
				cell.setPaddingRight(2);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase("" + data.getActionDate(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + data.getChecklistName(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase("" + data.getClosedDate(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + data.getClosedByName(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				
				cell = new PdfPCell(new Phrase("" + data.getTotalDetailTask(), headFont));
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
			Paragraph company = new Paragraph("Employee Wise Checklist Report\n", f);
			company.setAlignment(Element.ALIGN_CENTER);
			document.add(company);
			document.add(new Paragraph(" "));
			
			Paragraph eName = new Paragraph(""+empName+"\n", headFont);
			eName.setAlignment(Element.ALIGN_CENTER);
			document.add(eName);

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

	
	@RequestMapping(value = "/showEmpChecklistDetailReport/{headerId}", method = RequestMethod.GET)
	public ModelAndView showEmpChecklistDetailReport(@PathVariable int headerId) {

		ModelAndView model = new ModelAndView("checklistReport/empDetailReport");

		try {

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("headerId", headerId);
			
			
			ParameterizedTypeReference<List<ChecklistActionDetail>> typeRef2 = new ParameterizedTypeReference<List<ChecklistActionDetail>>() {
			};
			ResponseEntity<List<ChecklistActionDetail>> responseEntity2 = restTemplate.exchange(
					Constants.security_app_url + "checklist/getChecklistActionDetail", HttpMethod.POST,
					new HttpEntity<>(map), typeRef2);

			empChecklistDetailList = responseEntity2.getBody();
			
			System.err.println("REPORT---------- "+empChecklistDetailList);
			model.addObject("reportList", empChecklistDetailList);

			/*empChecklistDetailList = restTemplate
					.postForObject(Constants.security_app_url + "checklist/getChecklistActionDetail", map, List.class);

			model.addObject("reportList", empChecklistDetailList);*/
			
			
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
			System.out.println("exce in showEmpChecklistDetailReport " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}
	
	
	@RequestMapping(value = "/showEmpChecklistDetailReportPdf", method = RequestMethod.GET)
	public void showEmpChecklistDetailReportPdf(HttpServletRequest request, HttpServletResponse response)
			throws FileNotFoundException {
		BufferedOutputStream outStream = null;
		System.out.println("Inside Pdf showEmpChecklistDetailReportPdf");
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


			for (ChecklistActionDetail data : empChecklistDetailList) {
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
			Paragraph company = new Paragraph("Employee Wise Checklist Detail Report\n", f);
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
