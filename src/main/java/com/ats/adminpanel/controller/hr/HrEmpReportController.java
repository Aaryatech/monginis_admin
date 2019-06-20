package com.ats.adminpanel.controller.hr;

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
import com.ats.adminpanel.model.hr.EmpDeptDisplay;
import com.ats.adminpanel.model.hr.EmpGatepassDisplay;
import com.ats.adminpanel.model.hr.EmpWiseVisitorReport;
import com.ats.adminpanel.model.hr.Employee;
import com.ats.adminpanel.model.hr.MaterialGatepassDisplay;
import com.ats.adminpanel.model.hr.SupplierModel;
import com.ats.adminpanel.model.hr.VisitorGatepassDisplay;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Controller
public class HrEmpReportController {

	List<String> empList = new ArrayList<>();
	List<String> deptList = new ArrayList<>();
	List<String> supList = new ArrayList<>();

	List<EmpWiseVisitorReport> countReportList = new ArrayList<>();

	List<VisitorGatepassDisplay> reportList = new ArrayList<>();

	List<EmpGatepassDisplay> supervisorReportList = new ArrayList<>();

	List<EmpGatepassDisplay> empWiseReportList = new ArrayList<>();

	List<MaterialGatepassDisplay> materialReportList = new ArrayList<>();

	@RequestMapping(value = "/showEmpWiseGatepassReport", method = RequestMethod.GET)
	public ModelAndView showEmpWiseGatepassReport(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("hrEmployeeReports/empWiseVisitorReport");

		HttpSession session = request.getSession();

		System.out.println("session Id in show Page  " + session.getId());

		countReportList.clear();

		try {
			ZoneId z = ZoneId.of("Asia/Calcutta");

			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			String todaysDate = date.format(formatters);

			RestTemplate restTemplate = new RestTemplate();

			// get Employee

			Employee[] empArray = restTemplate.getForObject(Constants.security_app_url + "master/allEmployees",
					Employee[].class);

			List<Employee> empList = new ArrayList<>(Arrays.asList(empArray));

			// end get Employee

			model.addObject("todaysDate", todaysDate);
			model.addObject("empList", empList);

		} catch (Exception e) {

			System.out.println("Exc in show emp wise gatepass report " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/getEmpwiseGPReport", method = RequestMethod.GET)
	public @ResponseBody List<EmpWiseVisitorReport> getEmpwiseGPReport(HttpServletRequest request,
			HttpServletResponse response) {

		List<EmpWiseVisitorReport> reportList = new ArrayList<>();

		countReportList.clear();

		try {
			System.out.println("Inside get Sale Bill Wise");
			String selectedType = request.getParameter("type");
			String selectedStatus = request.getParameter("status");
			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
			String selectedEmpId = request.getParameter("emp_id_list");

			fromDate = DateConvertor.convertToYMD(fromDate);
			toDate = DateConvertor.convertToYMD(toDate);

			boolean isAllEmpSelected = false;
			selectedEmpId = selectedEmpId.substring(1, selectedEmpId.length() - 1);
			selectedEmpId = selectedEmpId.replaceAll("\"", "");

			empList = new ArrayList<>();
			empList = Arrays.asList(selectedEmpId);

			if (empList.contains("-1")) {
				isAllEmpSelected = true;
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			if (isAllEmpSelected) {

				System.out.println("Inside If all emp Selected ");

				map.add("empIds", -1);

			} else {
				System.out.println("Inside else Few Emp Selected " + empList.toString());

				map.add("empIds", selectedEmpId);

			}

			map.add("fromDate", fromDate);
			map.add("toDate", toDate);
			map.add("type", selectedType);
			map.add("status", selectedStatus);

			ParameterizedTypeReference<List<EmpWiseVisitorReport>> typeRef = new ParameterizedTypeReference<List<EmpWiseVisitorReport>>() {
			};
			ResponseEntity<List<EmpWiseVisitorReport>> responseEntity = restTemplate.exchange(
					Constants.security_app_url + "report/getEmpWiseVisitorReport", HttpMethod.POST,
					new HttpEntity<>(map), typeRef);

			countReportList = responseEntity.getBody();
			System.err.println("emp ***********************" + countReportList.toString());

		} catch (Exception e) {
			System.out.println("get emp wise Report  " + e.getMessage());
			e.printStackTrace();

		}

		// exportToExcel

		List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

		ExportToExcel expoExcel = new ExportToExcel();
		List<String> rowData = new ArrayList<String>();

		rowData.add("Employee Name");
		rowData.add("Count");

		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);
		for (int i = 0; i < countReportList.size(); i++) {
			expoExcel = new ExportToExcel();
			rowData = new ArrayList<String>();

			rowData.add("" + countReportList.get(i).getEmpName());
			rowData.add("" + countReportList.get(i).getCount());

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);

		}

		HttpSession session = request.getSession();
		session.setAttribute("exportExcelList", exportToExcelList);
		session.setAttribute("excelName", "empWiseVisitorReport");

		return countReportList;
	}

	// ---------------EMPLOYEE DETAIL REPORT------------------------
	@RequestMapping(value = "/getDetailOfEmp/{fromDate}/{toDate}/{empId}/{type}/{status}", method = RequestMethod.GET)
	public ModelAndView updateCompany(@PathVariable String fromDate, @PathVariable String toDate,
			@PathVariable int empId, @PathVariable int type, @PathVariable int status, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView mav = new ModelAndView("hrEmployeeReports/empWiseVisitorDetailReport");
		try {

			String fromDateYMD = DateConvertor.convertToYMD(fromDate);
			String toDateYMD = DateConvertor.convertToYMD(toDate);

			RestTemplate rest = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("fromDate", fromDateYMD);
			map.add("toDate", toDateYMD);
			map.add("empId", empId);
			map.add("gatepassType", type);
			map.add("status", status);

			VisitorGatepassDisplay[] dispArray = rest.postForObject(
					Constants.security_app_url + "/report/getVisitorGatepassReport", map,
					VisitorGatepassDisplay[].class);
			reportList = new ArrayList<>(Arrays.asList(dispArray));

			System.out.println("Response: ------------------ " + reportList.toString());

			List<VisitorGatepassDisplay> newReportList = new ArrayList<>();

			MultiValueMap<String, Object> map1 = new LinkedMultiValueMap<String, Object>();
			map1.add("empId", empId);

			Employee emp = rest.postForObject(Constants.security_app_url + "/master/getEmployeeById", map1,
					Employee.class);

			String empName = emp.getEmpFname() + " " + emp.getEmpMname() + " " + emp.getEmpSname();

			System.out.println(emp.toString());

			try {

				if (reportList != null) {

					for (int i = 0; i < reportList.size(); i++) {

						String date = DateConvertor.convertToDMY(reportList.get(i).getVisitDateIn());
						reportList.get(i).setVisitDateIn(date);

						newReportList.add(reportList.get(i));

					}

					mav.addObject("reportList", newReportList);
					mav.addObject("fromDate", fromDate);
					mav.addObject("toDate", toDate);
					mav.addObject("type", type);
					mav.addObject("status", status);
					mav.addObject("emp", empName);

				}

			} catch (Exception e) {
				System.out.println("Exception In Emp Report Detail:" + e.getMessage());

				mav.addObject("reportList", reportList);
			}

			// exportToExcel

			List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

			ExportToExcel expoExcel = new ExportToExcel();
			List<String> rowData = new ArrayList<String>();

			rowData.add("Visit Date");
			rowData.add("Person Name");
			rowData.add("Person Company");
			rowData.add("Contact Number");
			rowData.add("Visit Purpose");
			rowData.add("Visit In Time");
			rowData.add("Visit Out Time");
			rowData.add("Total Visit Time");
			rowData.add("Assign Employee");

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);
			for (int i = 0; i < reportList.size(); i++) {
				expoExcel = new ExportToExcel();
				rowData = new ArrayList<String>();

				rowData.add("" + reportList.get(i).getVisitDateIn());
				rowData.add("" + reportList.get(i).getPersonName());
				rowData.add("" + reportList.get(i).getPersonCompany());
				rowData.add("" + reportList.get(i).getMobileNo());
				rowData.add("" + reportList.get(i).getVisitPurposeText());
				rowData.add("" + reportList.get(i).getInTime());
				rowData.add("" + reportList.get(i).getVisitOutTime());
				rowData.add("" + reportList.get(i).getTotalTimeDifference());
				rowData.add("" + reportList.get(i).getEmpName());

				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);

			}

			HttpSession session = request.getSession();
			session.setAttribute("exportExcelList", exportToExcelList);
			session.setAttribute("excelName", "empWiseVisitorDetailReport");

		} catch (Exception e) {
			System.out.println("Exception In Emp Report Detail:" + e.getMessage());

			return mav;

		}

		return mav;
	}

	@RequestMapping(value = "/showEmpwiseGPPdf/{fromDate}/{toDate}", method = RequestMethod.GET)
	public void showEmpwiseGPPdf(@PathVariable("fromDate") String fromDate, @PathVariable("toDate") String toDate,
			HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException {
		BufferedOutputStream outStream = null;
		System.out.println("Inside Pdf showContractorwisePdf");
		Document document = new Document(PageSize.A4);

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();

		System.out.println("time in Gen Bill PDF ==" + dateFormat.format(cal.getTime()));
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

			hcell = new PdfPCell(new Phrase("Employee", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Count", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			int index = 0;

			System.err.println("REPORT LIST -------------------------------------------------- " + countReportList);

			for (EmpWiseVisitorReport work : countReportList) {
				index++;
				PdfPCell cell;

				cell = new PdfPCell(new Phrase(String.valueOf(index), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPadding(3);
				cell.setPaddingRight(2);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + work.getEmpName(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + work.getCount(), headFont));
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
			Paragraph company = new Paragraph("Employee Wise Gatepass Report\n", f);
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

	@RequestMapping(value = "/showEmpwiseGPDetailPdf/{fromDate}/{toDate}", method = RequestMethod.GET)
	public void showEmpwiseGPDetailPdf(@PathVariable("fromDate") String fromDate, @PathVariable("toDate") String toDate,
			HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException {
		BufferedOutputStream outStream = null;
		System.out.println("Inside Pdf showContractorwisePdf");
		Document document = new Document(PageSize.A4);

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();

		System.out.println("time in Gen Bill PDF ==" + dateFormat.format(cal.getTime()));
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

		PdfPTable table = new PdfPTable(10);
		try {
			System.out.println("Inside PDF Table try");
			table.setWidthPercentage(100);
			table.setWidths(new float[] { 2.4f, 3.2f, 3.2f, 3.2f, 3.2f, 3.2f, 3.2f, 3.2f, 3.2f, 3.2f });
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

			hcell = new PdfPCell(new Phrase("Visit Date", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Person Name", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);
			hcell = new PdfPCell(new Phrase("Person Company", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);
			hcell = new PdfPCell(new Phrase("Contact Number", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);
			hcell = new PdfPCell(new Phrase("Purpose", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);
			hcell = new PdfPCell(new Phrase("In Time", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);
			hcell = new PdfPCell(new Phrase("Out Time", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);
			hcell = new PdfPCell(new Phrase("Total Time", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);
			hcell = new PdfPCell(new Phrase("Assign Employee", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);
			int index = 0;

			System.err.println("REPORT LIST -------------------------------------------------- " + reportList);

			for (VisitorGatepassDisplay work : reportList) {
				index++;
				PdfPCell cell;

				cell = new PdfPCell(new Phrase(String.valueOf(index), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPadding(3);
				cell.setPaddingRight(2);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + work.getVisitDateIn(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + work.getPersonName(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + work.getPersonCompany(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + work.getMobileNo(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + work.getVisitPurposeText(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + work.getInTime(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + work.getVisitOutTime(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + work.getTotalTimeDifference(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + work.getEmpName(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

			}
			document.open();
			Paragraph name = new Paragraph("GFPL\n", f);
			name.setAlignment(Element.ALIGN_CENTER);
			document.add(name);
			document.add(new Paragraph(" "));
			Paragraph company = new Paragraph("Employee Wise Gatepass Detail Report\n", f);
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

	// -------------------------------SUPERVISOR COMPARISON
	// REPORT---------------------------------------------

	@RequestMapping(value = "/showSupCompReport", method = RequestMethod.GET)
	public ModelAndView showSupCompReport(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("hrEmployeeReports/supervisorCompReport");
		countReportList = new ArrayList<>();
		HttpSession session = request.getSession();

		System.out.println("session Id in show Page  " + session.getId());

		try {

			ZoneId z = ZoneId.of("Asia/Calcutta");

			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			String todaysDate = date.format(formatters);

			RestTemplate restTemplate = new RestTemplate();

			// get Employee

			EmpDeptDisplay[] deptArray = restTemplate.getForObject(
					Constants.security_app_url + "master/allEmployeeDepartmentList", EmpDeptDisplay[].class);
			List<EmpDeptDisplay> deptList = new ArrayList<>(Arrays.asList(deptArray));

			// end get Employee

			model.addObject("todaysDate", todaysDate);
			model.addObject("deptList", deptList);

		} catch (Exception e) {

			System.out.println("Exc in show sup comp report " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/getSupCompReport", method = RequestMethod.GET)
	public @ResponseBody List<EmpWiseVisitorReport> getSupCompReport(HttpServletRequest request,
			HttpServletResponse response) {

		List<EmpWiseVisitorReport> reportList = new ArrayList<>();

		try {
			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
			String selectedDeptId = request.getParameter("dept_id_list");

			fromDate = DateConvertor.convertToYMD(fromDate);
			toDate = DateConvertor.convertToYMD(toDate);

			boolean isAllDeptSelected = false;
			selectedDeptId = selectedDeptId.substring(1, selectedDeptId.length() - 1);
			selectedDeptId = selectedDeptId.replaceAll("\"", "");

			deptList = new ArrayList<>();
			deptList = Arrays.asList(selectedDeptId);

			if (deptList.contains("-1")) {
				isAllDeptSelected = true;
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			if (isAllDeptSelected) {

				System.out.println("Inside If all Dept Selected ");

				map.add("deptIds", -1);

			} else {
				System.out.println("Inside else Few Dept Selected " + deptList.toString());

				map.add("deptIds", selectedDeptId);

			}

			map.add("fromDate", fromDate);
			map.add("toDate", toDate);

			ParameterizedTypeReference<List<EmpWiseVisitorReport>> typeRef = new ParameterizedTypeReference<List<EmpWiseVisitorReport>>() {
			};
			ResponseEntity<List<EmpWiseVisitorReport>> responseEntity = restTemplate.exchange(
					Constants.security_app_url + "report/getEmpGatepassCountForSupervisorForReport", HttpMethod.POST,
					new HttpEntity<>(map), typeRef);

			countReportList = responseEntity.getBody();
			System.err.println("Dept ***********************" + countReportList.toString());

		} catch (Exception e) {
			System.out.println("get Dept wise Report  " + e.getMessage());
			e.printStackTrace();

		}

		// exportToExcel

		List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

		ExportToExcel expoExcel = new ExportToExcel();
		List<String> rowData = new ArrayList<String>();

		rowData.add("Supervisor Name");
		rowData.add("Count");

		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);
		for (int i = 0; i < countReportList.size(); i++) {
			expoExcel = new ExportToExcel();
			rowData = new ArrayList<String>();

			rowData.add("" + countReportList.get(i).getEmpName());
			rowData.add("" + countReportList.get(i).getCount());

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);

		}

		HttpSession session = request.getSession();
		session.setAttribute("exportExcelList", exportToExcelList);
		session.setAttribute("excelName", "Supervisor_Comparison_Report");

		return countReportList;
	}

	// ---------------SUPERVISOR DETAIL REPORT------------------------
	@RequestMapping(value = "/getDetailOfSup/{fromDate}/{toDate}/{empId}", method = RequestMethod.GET)
	public ModelAndView getDetailOfSup(@PathVariable String fromDate, @PathVariable String toDate,
			@PathVariable int empId, HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mav = new ModelAndView("hrEmployeeReports/supervisorCompDetail");
		try {

			String fromDateYMD = DateConvertor.convertToYMD(fromDate);
			String toDateYMD = DateConvertor.convertToYMD(toDate);

			RestTemplate rest = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("fromDate", fromDateYMD);
			map.add("toDate", toDateYMD);
			map.add("userId", empId);

			EmpGatepassDisplay[] dispArray = rest.postForObject(
					Constants.security_app_url + "/report/getEmpGatepassReportByUser", map, EmpGatepassDisplay[].class);
			supervisorReportList = new ArrayList<>(Arrays.asList(dispArray));

			System.out.println("Response: ------------------ " + supervisorReportList.toString());

			List<EmpGatepassDisplay> newReportList = new ArrayList<>();

			MultiValueMap<String, Object> map1 = new LinkedMultiValueMap<String, Object>();
			map1.add("empId", empId);

			Employee emp = rest.postForObject(Constants.security_app_url + "/master/getEmployeeById", map1,
					Employee.class);

			String empName = emp.getEmpFname() + " " + emp.getEmpMname() + " " + emp.getEmpSname();

			System.out.println(emp.toString());

			try {

				if (supervisorReportList != null) {

					for (int i = 0; i < supervisorReportList.size(); i++) {

						supervisorReportList.get(i)
								.setEmpDateOut(DateConvertor.convertToDMY(supervisorReportList.get(i).getEmpDateOut()));
						supervisorReportList.get(i)
								.setEmpDateIn(DateConvertor.convertToDMY(supervisorReportList.get(i).getEmpDateIn()));

					}

					mav.addObject("reportList", supervisorReportList);
					mav.addObject("fromDate", fromDate);
					mav.addObject("toDate", toDate);
					mav.addObject("emp", empName);

				}

			} catch (Exception e) {
				System.out.println("Exception In Emp Report Detail:" + e.getMessage());

				mav.addObject("reportList", supervisorReportList);
			}

			// exportToExcel

			List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

			ExportToExcel expoExcel = new ExportToExcel();
			List<String> rowData = new ArrayList<String>();

			rowData.add("Supervisor");
			rowData.add("Employee");
			rowData.add("Purpose");
			rowData.add("Gatepass Type");
			rowData.add("Date");
			rowData.add("Out Time");
			rowData.add("In Time");
			rowData.add("Total Time");

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);
			for (int i = 0; i < supervisorReportList.size(); i++) {
				expoExcel = new ExportToExcel();
				rowData = new ArrayList<String>();

				rowData.add("" + supervisorReportList.get(i).getUserName());
				rowData.add("" + supervisorReportList.get(i).getEmpName());
				rowData.add("" + supervisorReportList.get(i).getPurposeText());

				if (supervisorReportList.get(i).getGatePassSubType() == 1) {
					rowData.add("Temporary");
				} else if (supervisorReportList.get(i).getGatePassSubType() == 2) {
					rowData.add("Day");
				}

				rowData.add("" + supervisorReportList.get(i).getEmpDateOut());
				rowData.add("" + supervisorReportList.get(i).getNewOutTime());
				rowData.add("" + supervisorReportList.get(i).getNewInTime());
				rowData.add("" + supervisorReportList.get(i).getActualTimeDifference());

				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);

			}

			HttpSession session = request.getSession();
			session.setAttribute("exportExcelList", exportToExcelList);
			session.setAttribute("excelName", "Supervisor_Comparison_Detail_Report");

		} catch (Exception e) {
			System.out.println("Exception In Supervisor Report Detail:" + e.getMessage());

			return mav;

		}

		return mav;
	}

	@RequestMapping(value = "/showSupCompPdf/{fromDate}/{toDate}", method = RequestMethod.GET)
	public void showSupCompPdf(@PathVariable("fromDate") String fromDate, @PathVariable("toDate") String toDate,
			HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException {
		BufferedOutputStream outStream = null;
		Document document = new Document(PageSize.A4);

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();

		System.out.println("time in PDF ==" + dateFormat.format(cal.getTime()));
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

			hcell = new PdfPCell(new Phrase("Supervisor", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Count", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			int index = 0;

			System.err.println("REPORT LIST -------------------------------------------------- " + countReportList);

			for (EmpWiseVisitorReport work : countReportList) {
				index++;
				PdfPCell cell;

				cell = new PdfPCell(new Phrase(String.valueOf(index), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPadding(3);
				cell.setPaddingRight(2);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + work.getEmpName(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + work.getCount(), headFont));
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
			Paragraph company = new Paragraph("Supervisor Comparison Report\n", f);
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

	@RequestMapping(value = "/showSupCompDetailPdf/{fromDate}/{toDate}", method = RequestMethod.GET)
	public void showSupCompDetailPdf(@PathVariable("fromDate") String fromDate, @PathVariable("toDate") String toDate,
			HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException {
		BufferedOutputStream outStream = null;
		System.out.println("Inside Pdf showContractorwisePdf");
		Document document = new Document(PageSize.A4);

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();

		System.out.println("time in Gen Bill PDF ==" + dateFormat.format(cal.getTime()));
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
			table.setWidths(new float[] { 2.4f, 3.2f, 3.2f, 3.2f, 3.2f, 3.2f, 3.2f, 3.2f, 3.2f });
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

			hcell = new PdfPCell(new Phrase("Supervisor", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Employee", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Purpose", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);
			hcell = new PdfPCell(new Phrase("Gatepass Type", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);
			hcell = new PdfPCell(new Phrase("Date", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Out Time", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("In Time", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);
			hcell = new PdfPCell(new Phrase("Total Time", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			int index = 0;

			System.err.println("REPORT LIST -------------------------------------------------- " + reportList);

			for (EmpGatepassDisplay work : supervisorReportList) {
				index++;
				PdfPCell cell;

				cell = new PdfPCell(new Phrase(String.valueOf(index), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPadding(3);
				cell.setPaddingRight(2);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + work.getUserName(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + work.getEmpName(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + work.getPurposeText(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				String type = "";
				if (work.getGatePassSubType() == 1) {
					type = "Temporary";
				} else if (work.getGatePassSubType() == 2) {
					type = "Day";
				}

				cell = new PdfPCell(new Phrase("" + type, headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + work.getEmpDateOut(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + work.getNewOutTime(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + work.getNewInTime(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + work.getActualTimeDifference(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

			}
			document.open();
			Paragraph name = new Paragraph("GFPL\n", f);
			name.setAlignment(Element.ALIGN_CENTER);
			document.add(name);
			document.add(new Paragraph(" "));
			Paragraph company = new Paragraph("Supervisor Comparison Detail Report\n", f);
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

	// -------------------------------EMP WISE REPORT FOR
	// EMPLOYEE--------------------------------------------

	@RequestMapping(value = "/showEmpWiseReportForEmp", method = RequestMethod.GET)
	public ModelAndView showEmpWiseReportForEmp(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("hrEmployeeReports/empWiseEmpGatepassReport");

		HttpSession session = request.getSession();

		System.out.println("session Id in show Page  " + session.getId());

		empWiseReportList.clear();

		try {
			ZoneId z = ZoneId.of("Asia/Calcutta");

			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			String todaysDate = date.format(formatters);

			RestTemplate restTemplate = new RestTemplate();

			// get Employee

			Employee[] empArray = restTemplate.getForObject(Constants.security_app_url + "master/allSupervisorList",
					Employee[].class);

			List<Employee> empList = new ArrayList<>(Arrays.asList(empArray));

			EmpDeptDisplay[] deptArray = restTemplate.getForObject(
					Constants.security_app_url + "master/allEmployeeDepartmentList", EmpDeptDisplay[].class);
			List<EmpDeptDisplay> deptList = new ArrayList<>(Arrays.asList(deptArray));

			// end get Employee

			model.addObject("todaysDate", todaysDate);
			model.addObject("empList", empList);
			model.addObject("deptList", deptList);

		} catch (Exception e) {

			System.out.println("Exc in show emp gatepass report " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/getEmpwiseGatepassReport", method = RequestMethod.GET)
	public @ResponseBody List<EmpGatepassDisplay> getEmpwiseGatepassReport(HttpServletRequest request,
			HttpServletResponse response) {

		List<EmpWiseVisitorReport> reportList = new ArrayList<>();

		try {
			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
			String selectedEmpId = request.getParameter("emp_id_list");
			String selectedDeptId = request.getParameter("dept_id_list");

			fromDate = DateConvertor.convertToYMD(fromDate);
			toDate = DateConvertor.convertToYMD(toDate);

			boolean isAllEmpSelected = false, isAllDeptSelected = false;

			selectedEmpId = selectedEmpId.substring(1, selectedEmpId.length() - 1);
			selectedEmpId = selectedEmpId.replaceAll("\"", "");

			selectedDeptId = selectedDeptId.substring(1, selectedDeptId.length() - 1);
			selectedDeptId = selectedDeptId.replaceAll("\"", "");

			empList = new ArrayList<>();
			empList = Arrays.asList(selectedEmpId);

			if (empList.contains("-1")) {
				isAllEmpSelected = true;
			}

			deptList = new ArrayList<>();
			deptList = Arrays.asList(selectedDeptId);

			if (empList.contains("-1")) {
				isAllDeptSelected = true;
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			if (isAllEmpSelected) {

				System.out.println("Inside If all emp Selected ");

				map.add("empIds", -1);

			} else {
				System.out.println("Inside else Few Emp Selected " + empList.toString());

				map.add("empIds", selectedEmpId);

			}

			if (isAllDeptSelected) {

				System.out.println("Inside If all DEPT Selected ");

				map.add("deptIds", -1);

			} else {
				System.out.println("Inside else Few DEPT Selected " + empList.toString());

				map.add("deptIds", selectedDeptId);

			}

			map.add("fromDate", fromDate);
			map.add("toDate", toDate);

			ParameterizedTypeReference<List<EmpGatepassDisplay>> typeRef = new ParameterizedTypeReference<List<EmpGatepassDisplay>>() {
			};
			ResponseEntity<List<EmpGatepassDisplay>> responseEntity = restTemplate.exchange(
					Constants.security_app_url + "report/getEmpGatepassReportByDept", HttpMethod.POST,
					new HttpEntity<>(map), typeRef);

			empWiseReportList = responseEntity.getBody();
			System.err.println("emp ***********************" + empWiseReportList.toString());

		} catch (Exception e) {
			System.out.println("get emp wise Report  " + e.getMessage());
			e.printStackTrace();

		}

		// exportToExcel

		List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

		ExportToExcel expoExcel = new ExportToExcel();
		List<String> rowData = new ArrayList<String>();

		rowData.add("Supervisor");
		rowData.add("Employee");
		rowData.add("Purpose");
		rowData.add("Gatepass Type");
		rowData.add("Date");
		rowData.add("Out Time");
		rowData.add("In Time");
		rowData.add("Total Time");

		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);
		for (int i = 0; i < empWiseReportList.size(); i++) {
			expoExcel = new ExportToExcel();
			rowData = new ArrayList<String>();

			rowData.add("" + empWiseReportList.get(i).getUserName());
			rowData.add("" + empWiseReportList.get(i).getEmpName());
			rowData.add("" + empWiseReportList.get(i).getPurposeText());

			if (empWiseReportList.get(i).getGatePassSubType() == 1) {
				rowData.add("Temporary");
			} else if (empWiseReportList.get(i).getGatePassSubType() == 2) {
				rowData.add("Day");
			}

			rowData.add("" + empWiseReportList.get(i).getEmpDateOut());
			rowData.add("" + empWiseReportList.get(i).getNewOutTime());
			rowData.add("" + empWiseReportList.get(i).getNewInTime());
			rowData.add("" + empWiseReportList.get(i).getActualTimeDifference());

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);

		}

		HttpSession session = request.getSession();
		session.setAttribute("exportExcelList", exportToExcelList);
		session.setAttribute("excelName", "Employee_Gatepass_Report");

		return empWiseReportList;
	}

	@RequestMapping(value = "/showEmpGatepassPdf/{fromDate}/{toDate}", method = RequestMethod.GET)
	public void showEmpGatepassPdf(@PathVariable("fromDate") String fromDate, @PathVariable("toDate") String toDate,
			HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException {
		BufferedOutputStream outStream = null;
		Document document = new Document(PageSize.A4);

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();

		System.out.println("time in Gen Bill PDF ==" + dateFormat.format(cal.getTime()));
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
			table.setWidths(new float[] { 2.4f, 3.2f, 3.2f, 3.2f, 3.2f, 3.2f, 3.2f, 3.2f, 3.2f });
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

			hcell = new PdfPCell(new Phrase("Supervisor", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Employee", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Purpose", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);
			hcell = new PdfPCell(new Phrase("Gatepass Type", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);
			hcell = new PdfPCell(new Phrase("Date", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Out Time", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("In Time", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);
			hcell = new PdfPCell(new Phrase("Total Time", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			int index = 0;

			System.err.println("REPORT LIST -------------------------------------------------- " + empWiseReportList);

			for (EmpGatepassDisplay work : empWiseReportList) {
				index++;
				PdfPCell cell;

				cell = new PdfPCell(new Phrase(String.valueOf(index), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPadding(3);
				cell.setPaddingRight(2);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + work.getUserName(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + work.getEmpName(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + work.getPurposeText(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				String type = "";
				if (work.getGatePassSubType() == 1) {
					type = "Temporary";
				} else if (work.getGatePassSubType() == 2) {
					type = "Day";
				}

				cell = new PdfPCell(new Phrase("" + type, headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + work.getEmpDateOut(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + work.getNewOutTime(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + work.getNewInTime(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + work.getActualTimeDifference(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

			}
			document.open();
			Paragraph name = new Paragraph("GFPL\n", f);
			name.setAlignment(Element.ALIGN_CENTER);
			document.add(name);
			document.add(new Paragraph(" "));
			Paragraph company = new Paragraph("Employee Gatepass  Report\n", f);
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

	// -------------------------------Material Gatepass
	// REPORT--------------------------------------------

	@RequestMapping(value = "/showMatGatepassReport", method = RequestMethod.GET)
	public ModelAndView showMatGatepassReport(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("hrEmployeeReports/materialReport");

		HttpSession session = request.getSession();

		System.out.println("session Id in show Page  " + session.getId());

		materialReportList.clear();

		try {
			ZoneId z = ZoneId.of("Asia/Calcutta");

			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			String todaysDate = date.format(formatters);

			RestTemplate restTemplate = new RestTemplate();

			// get Supplier

			SupplierModel[] empArray = restTemplate.getForObject(Constants.store_api_url + "getAllVendorByIsUsed",
					SupplierModel[].class);

			List<SupplierModel> supList = new ArrayList<>(Arrays.asList(empArray));

			System.err.println("SUPPLIER - -------------------------------- " + supList);

			model.addObject("todaysDate", todaysDate);
			model.addObject("supList", supList);

		} catch (Exception e) {

			System.out.println("Exc in show material gatepass report " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/getMatGatepassReport", method = RequestMethod.GET)
	public @ResponseBody List<MaterialGatepassDisplay> getMatGatepassReport(HttpServletRequest request,
			HttpServletResponse response) {

		List<MaterialGatepassDisplay> reportList = new ArrayList<>();

		List<MaterialGatepassDisplay> newReportList = new ArrayList<>();

		try {
			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
			String selectedSupId = request.getParameter("sup_id_list");

			fromDate = DateConvertor.convertToYMD(fromDate);
			toDate = DateConvertor.convertToYMD(toDate);

			boolean isAllSupSelected = false;

			selectedSupId = selectedSupId.substring(1, selectedSupId.length() - 1);
			selectedSupId = selectedSupId.replaceAll("\"", "");

			supList = new ArrayList<>();
			supList = Arrays.asList(selectedSupId);

			if (supList.contains("-1")) {
				isAllSupSelected = true;
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			if (isAllSupSelected) {

				System.out.println("Inside If all Sup Selected ");

				map.add("supIds", -1);

			} else {
				System.out.println("Inside else Few Emp Selected " + supList.toString());

				map.add("supIds", selectedSupId);

			}

			map.add("fromDate", fromDate);
			map.add("toDate", toDate);

			ParameterizedTypeReference<List<MaterialGatepassDisplay>> typeRef = new ParameterizedTypeReference<List<MaterialGatepassDisplay>>() {
			};
			ResponseEntity<List<MaterialGatepassDisplay>> responseEntity = restTemplate.exchange(
					Constants.security_app_url + "report/getMaterialGatepassReport", HttpMethod.POST,
					new HttpEntity<>(map), typeRef);

			newReportList = responseEntity.getBody();
			System.err.println("MAterial ***********************" + newReportList.toString());

		} catch (Exception e) {
			System.out.println("get mat wise Report  " + e.getMessage());
			e.printStackTrace();

		}

		try {

			if (newReportList != null) {

				for (int i = 0; i < newReportList.size(); i++) {

					String date = DateConvertor.convertToDMY(newReportList.get(i).getInwardDate());
					newReportList.get(i).setInwardDate(date);

					materialReportList.add(newReportList.get(i));

				}

			}

		} catch (Exception e) {
			System.out.println("Exception In mat Report :" + e.getMessage());
		}

		// exportToExcel

		List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

		ExportToExcel expoExcel = new ExportToExcel();
		List<String> rowData = new ArrayList<String>();

		rowData.add("Date");
		rowData.add("Type");
		rowData.add("Invoice No");
		rowData.add("Party Name");
		rowData.add("Security");

		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);
		for (int i = 0; i < materialReportList.size(); i++) {
			expoExcel = new ExportToExcel();
			rowData = new ArrayList<String>();

			rowData.add("" + materialReportList.get(i).getInwardDate());

			if (materialReportList.get(i).getGatePassSubType() == 1) {
				rowData.add("Material");
			} else if (materialReportList.get(i).getGatePassSubType() == 2) {
				rowData.add("Parcel");
			}
			rowData.add("" + materialReportList.get(i).getInvoiceNumber());
			rowData.add("" + materialReportList.get(i).getPartyName());

			rowData.add("" + materialReportList.get(i).getSecurityName());

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);

		}

		HttpSession session = request.getSession();
		session.setAttribute("exportExcelList", exportToExcelList);
		session.setAttribute("excelName", "Material_Gatepass_Report");

		return materialReportList;
	}

	@RequestMapping(value = "/showMatGatepassPdf/{fromDate}/{toDate}", method = RequestMethod.GET)
	public void showMatGatepassPdf(@PathVariable("fromDate") String fromDate, @PathVariable("toDate") String toDate,
			HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException {
		BufferedOutputStream outStream = null;
		Document document = new Document(PageSize.A4);

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();

		System.out.println("time in Gen Bill PDF ==" + dateFormat.format(cal.getTime()));
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
			table.setWidths(new float[] { 2.4f, 3.2f, 3.2f, 3.2f, 3.2f, 3.2f });
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

			hcell = new PdfPCell(new Phrase("Date", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Type", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Invoice No", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);
			hcell = new PdfPCell(new Phrase("Party Name", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);
			hcell = new PdfPCell(new Phrase("Security", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			int index = 0;

			System.err.println("REPORT LIST -------------------------------------------------- " + materialReportList);

			for (MaterialGatepassDisplay work : materialReportList) {
				index++;
				PdfPCell cell;

				cell = new PdfPCell(new Phrase(String.valueOf(index), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPadding(3);
				cell.setPaddingRight(2);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + work.getInwardDate(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				String type = "";

				if (work.getGatePassSubType() == 1) {
					type = "Material";
				} else {
					type = "Parcel";
				}

				cell = new PdfPCell(new Phrase("" + type, headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + work.getInvoiceNumber(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + work.getPartyName(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + work.getSecurityName(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

			}
			document.open();
			Paragraph name = new Paragraph("GFPL\n", f);
			name.setAlignment(Element.ALIGN_CENTER);
			document.add(name);
			document.add(new Paragraph(" "));
			Paragraph company = new Paragraph("Material Gatepass  Report\n", f);
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

	// ---------------------------------------------------------------------------------------------

	@RequestMapping(value = "/showMatTrackReportBetDate", method = RequestMethod.GET)
	public ModelAndView showMatTrackReportBetDate(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("hrEmployeeReports/matTrackingReportBetDate");

		HttpSession session = request.getSession();

		System.out.println("session Id in show Page  " + session.getId());

		materialReportList.clear();

		try {
			ZoneId z = ZoneId.of("Asia/Calcutta");

			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			String todaysDate = date.format(formatters);

			RestTemplate restTemplate = new RestTemplate();

			// get Supplier

			EmpDeptDisplay[] deptArray = restTemplate.getForObject(
					Constants.security_app_url + "master/allEmployeeDepartmentList", EmpDeptDisplay[].class);
			List<EmpDeptDisplay> deptList = new ArrayList<>(Arrays.asList(deptArray));

			model.addObject("todaysDate", todaysDate);
			model.addObject("deptList", deptList);

		} catch (Exception e) {

			System.out.println("Exc in show material track report " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/getMatTrackReport", method = RequestMethod.GET)
	public @ResponseBody List<MaterialGatepassDisplay> getMatTrackReport(HttpServletRequest request,
			HttpServletResponse response) {

		List<MaterialGatepassDisplay> reportList = new ArrayList<>();

		List<MaterialGatepassDisplay> newReportList = new ArrayList<>();

		try {
			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
			String selectedDeptId = request.getParameter("dept_id_list");
			String stat = request.getParameter("status");

			fromDate = DateConvertor.convertToYMD(fromDate);
			toDate = DateConvertor.convertToYMD(toDate);

			boolean isAllDeptSelected = false;

			selectedDeptId = selectedDeptId.substring(1, selectedDeptId.length() - 1);
			selectedDeptId = selectedDeptId.replaceAll("\"", "");

			deptList = new ArrayList<>();
			deptList = Arrays.asList(selectedDeptId);

			if (deptList.contains("-1")) {
				isAllDeptSelected = true;
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();
			if (isAllDeptSelected) {

				System.out.println("Inside If all Dept Selected ");

				map.add("deptIds", -1);

			} else {
				System.out.println("Inside else Few Emp Selected " + supList.toString());

				map.add("deptIds", selectedDeptId);

			}

			map.add("fromDate", fromDate);
			map.add("toDate", toDate);
			map.add("empIds", -1);

			int status = Integer.parseInt(stat);

			if (status == 0) {
				map.add("status", 0);
			} else if (status == 1) {
				map.add("status", 1);
			} else if (status == 2) {
				map.add("status", 2);
			}

			System.err.println("MAP------------------------------------------------- = " + map.toString());
			System.err.println("STATUS------------------------------------------------- = " + status);

			ParameterizedTypeReference<List<MaterialGatepassDisplay>> typeRef = new ParameterizedTypeReference<List<MaterialGatepassDisplay>>() {
			};
			ResponseEntity<List<MaterialGatepassDisplay>> responseEntity = restTemplate.exchange(
					Constants.security_app_url + "transaction/getMaterialTrackGatepassListWithDateFilter",
					HttpMethod.POST, new HttpEntity<>(map), typeRef);

			newReportList = responseEntity.getBody();
			System.err.println("MAterial ***********************" + newReportList.toString());
			materialReportList.clear();

		} catch (Exception e) {
			System.out.println("get mat wise Report  " + e.getMessage());
			e.printStackTrace();

		}

		try {

			if (newReportList != null) {

				for (int i = 0; i < newReportList.size(); i++) {

					String date = DateConvertor.convertToDMY(newReportList.get(i).getInwardDate());
					newReportList.get(i).setInwardDate(date);

					materialReportList.add(newReportList.get(i));

				}

			} else {
				materialReportList.clear();
			}

		} catch (Exception e) {
			System.out.println("Exception In mat Report :" + e.getMessage());
		}

		// exportToExcel

		List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

		ExportToExcel expoExcel = new ExportToExcel();
		List<String> rowData = new ArrayList<String>();

		rowData.add("Date");
		rowData.add("Type");
		rowData.add("Invoice No");
		rowData.add("Party Name");
		rowData.add("Security");

		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);
		for (int i = 0; i < materialReportList.size(); i++) {
			expoExcel = new ExportToExcel();
			rowData = new ArrayList<String>();

			rowData.add("" + materialReportList.get(i).getInwardDate());

			if (materialReportList.get(i).getGatePassSubType() == 1) {
				rowData.add("Material");
			} else if (materialReportList.get(i).getGatePassSubType() == 2) {
				rowData.add("Parcel");
			}
			rowData.add("" + materialReportList.get(i).getInvoiceNumber());
			rowData.add("" + materialReportList.get(i).getPartyName());

			rowData.add("" + materialReportList.get(i).getSecurityName());

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);

		}

		HttpSession session = request.getSession();
		session.setAttribute("exportExcelList", exportToExcelList);
		session.setAttribute("excelName", "Material_Tracking_Report_Bet_Date");

		return materialReportList;
	}

	@RequestMapping(value = "/showMatTrackReportByStatus", method = RequestMethod.GET)
	public ModelAndView showMatTrackReportByStatus(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("hrEmployeeReports/matTrackReportByStatus");

		HttpSession session = request.getSession();

		System.out.println("session Id in show Page  " + session.getId());

		materialReportList.clear();

		try {
			ZoneId z = ZoneId.of("Asia/Calcutta");

			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			String todaysDate = date.format(formatters);

			RestTemplate restTemplate = new RestTemplate();

			EmpDeptDisplay[] deptArray = restTemplate.getForObject(
					Constants.security_app_url + "master/allEmployeeDepartmentList", EmpDeptDisplay[].class);
			List<EmpDeptDisplay> deptList = new ArrayList<>(Arrays.asList(deptArray));

			model.addObject("deptList", deptList);

		} catch (Exception e) {

			System.out.println("Exc in show material track report " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/getMatTrackReportData", method = RequestMethod.GET)
	public @ResponseBody List<MaterialGatepassDisplay> getMatTrackReportData(HttpServletRequest request,
			HttpServletResponse response) {

		List<MaterialGatepassDisplay> reportList = new ArrayList<>();

		List<MaterialGatepassDisplay> newReportList = new ArrayList<>();

		try {
			String selectedDeptId = request.getParameter("dept_id_list");
			String stat = request.getParameter("status");

			boolean isAllDeptSelected = false;

			selectedDeptId = selectedDeptId.substring(1, selectedDeptId.length() - 1);
			selectedDeptId = selectedDeptId.replaceAll("\"", "");

			deptList = new ArrayList<>();
			deptList = Arrays.asList(selectedDeptId);

			if (deptList.contains("-1")) {
				isAllDeptSelected = true;
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();
			if (isAllDeptSelected) {

				System.out.println("Inside If all Dept Selected ");

				map.add("deptIds", -1);

			} else {
				System.out.println("Inside else Few Dept Selected " + supList.toString());

				map.add("deptIds", selectedDeptId);

			}

			// map.add("deptIds", -1);
			map.add("empIds", -1);

			int status = Integer.parseInt(stat);

			if (status == 0) {
				map.add("status", 0);
			} else if (status == 1) {
				map.add("status", 1);
			} else if (status == 2) {
				map.add("status", 2);
			}

			System.err.println("MAP------------------------------------------------- = " + map.toString());
			System.err.println("STATUS------------------------------------------------- = " + status);

			ParameterizedTypeReference<List<MaterialGatepassDisplay>> typeRef = new ParameterizedTypeReference<List<MaterialGatepassDisplay>>() {
			};
			ResponseEntity<List<MaterialGatepassDisplay>> responseEntity = restTemplate.exchange(
					Constants.security_app_url + "transaction/getMaterialTrackGatepassListWithFilter", HttpMethod.POST,
					new HttpEntity<>(map), typeRef);

			newReportList = responseEntity.getBody();
			System.err.println("MAterial ***********************" + newReportList.toString());
			materialReportList.clear();

		} catch (Exception e) {
			System.out.println("get mat wise Report  " + e.getMessage());
			e.printStackTrace();

		}

		try {

			if (newReportList != null) {

				for (int i = 0; i < newReportList.size(); i++) {

					String date = DateConvertor.convertToDMY(newReportList.get(i).getInwardDate());
					newReportList.get(i).setInwardDate(date);

					materialReportList.add(newReportList.get(i));

				}

			} else {
				materialReportList.clear();
			}

		} catch (Exception e) {
			System.out.println("Exception In mat Report :" + e.getMessage());
		}

		// exportToExcel

		List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

		ExportToExcel expoExcel = new ExportToExcel();
		List<String> rowData = new ArrayList<String>();

		rowData.add("Date");
		rowData.add("Type");
		rowData.add("Invoice No");
		rowData.add("Party Name");
		rowData.add("Security");

		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);
		for (int i = 0; i < materialReportList.size(); i++) {
			expoExcel = new ExportToExcel();
			rowData = new ArrayList<String>();

			rowData.add("" + materialReportList.get(i).getInwardDate());

			if (materialReportList.get(i).getGatePassSubType() == 1) {
				rowData.add("Material");
			} else if (materialReportList.get(i).getGatePassSubType() == 2) {
				rowData.add("Parcel");
			}
			rowData.add("" + materialReportList.get(i).getInvoiceNumber());
			rowData.add("" + materialReportList.get(i).getPartyName());

			rowData.add("" + materialReportList.get(i).getSecurityName());

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);

		}

		HttpSession session = request.getSession();
		session.setAttribute("exportExcelList", exportToExcelList);
		session.setAttribute("excelName", "Material_Tracking_Report_By_Status");

		return materialReportList;
	}

	@RequestMapping(value = "/showMatGatepassByStatusPdf", method = RequestMethod.GET)
	public void showMatGatepassByStatusPdf(HttpServletRequest request, HttpServletResponse response)
			throws FileNotFoundException {
		BufferedOutputStream outStream = null;
		Document document = new Document(PageSize.A4);

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();

		System.out.println("time in Gen Bill PDF ==" + dateFormat.format(cal.getTime()));
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
			table.setWidths(new float[] { 2.4f, 3.2f, 3.2f, 3.2f, 3.2f, 3.2f });
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

			hcell = new PdfPCell(new Phrase("Date", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Type", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Invoice No", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);
			hcell = new PdfPCell(new Phrase("Party Name", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);
			hcell = new PdfPCell(new Phrase("Security", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			int index = 0;

			System.err.println("REPORT LIST -------------------------------------------------- " + materialReportList);

			for (MaterialGatepassDisplay work : materialReportList) {
				index++;
				PdfPCell cell;

				cell = new PdfPCell(new Phrase(String.valueOf(index), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPadding(3);
				cell.setPaddingRight(2);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + work.getInwardDate(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				String type = "";

				if (work.getGatePassSubType() == 1) {
					type = "Material";
				} else {
					type = "Parcel";
				}

				cell = new PdfPCell(new Phrase("" + type, headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + work.getInvoiceNumber(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + work.getPartyName(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + work.getSecurityName(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

			}
			document.open();
			Paragraph name = new Paragraph("GFPL\n", f);
			name.setAlignment(Element.ALIGN_CENTER);
			document.add(name);
			document.add(new Paragraph(" "));
			Paragraph company = new Paragraph("Material Gatepass Report\n", f);
			company.setAlignment(Element.ALIGN_CENTER);
			document.add(company);
			document.add(new Paragraph(" "));

			DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
			String reportDate = DF.format(new Date());
			// Paragraph p1 = new Paragraph("From Date:" + fromDate + " To Date:" + toDate,
			// headFont);
			// p1.setAlignment(Element.ALIGN_CENTER);
			// document.add(p1);
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
