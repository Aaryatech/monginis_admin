package com.ats.adminpanel.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
import com.ats.adminpanel.commons.DateConvertor;
import com.ats.adminpanel.model.AllFrIdName;
import com.ats.adminpanel.model.AllFrIdNameList;
import com.ats.adminpanel.model.AllRoutesListResponse;
import com.ats.adminpanel.model.Info;
import com.ats.adminpanel.model.RegCakeAsSpDispatchReport;
import com.ats.adminpanel.model.RegCakeAsSpDispatchReportList;
import com.ats.adminpanel.model.Route;
import com.ats.adminpanel.model.billing.FrBillHeaderForPrint;
import com.ats.adminpanel.model.franchisee.FrNameIdByRouteId;
import com.ats.adminpanel.model.franchisee.FrNameIdByRouteIdResponse;
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
public class RegCakeAsSpOrderController {

	public AllFrIdNameList allFrIdNameList = new AllFrIdNameList();

	@RequestMapping(value = "/showRegCakeSpOrderReport", method = RequestMethod.GET)
	public ModelAndView showBillListForPrint(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/regCakeAsSp");

		try {

			RestTemplate restTemplate = new RestTemplate();

			ZoneId z = ZoneId.of("Asia/Calcutta");

			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			String todaysDate = date.format(formatters);

			allFrIdNameList = restTemplate.getForObject(Constants.url + "getAllFrIdName", AllFrIdNameList.class);

			AllRoutesListResponse allRouteListResponse = restTemplate.getForObject(Constants.url + "showRouteList",
					AllRoutesListResponse.class);

			List<Route> routeList = new ArrayList<Route>();

			routeList = allRouteListResponse.getRoute();

			model.addObject("routeList", routeList);
			model.addObject("todaysDate", todaysDate);
			model.addObject("allFrIdNameList", allFrIdNameList.getFrIdNamesList());

		} catch (Exception e) {
			System.out.println("Exce in showRegCakeSpOrderReport " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	List<RegCakeAsSpDispatchReport> regCakeAsSp = new ArrayList<>();

	@RequestMapping(value = "/callGetRegCakeAsSp", method = RequestMethod.GET)
	public @ResponseBody List<RegCakeAsSpDispatchReport> callGetRegCakeAsSp(HttpServletRequest request,
			HttpServletResponse response) throws FileNotFoundException {

		ModelAndView model = new ModelAndView("reports/regCakeAsSp");

		String routeId = "0";
		String frIdString = "";

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		System.out.println("inside callGetRegCakeAsSp ajax call");

		frIdString = request.getParameter("fr_id_list");
		String fromDate = request.getParameter("from_date");
		String toDate = request.getParameter("to_date");

		routeId = request.getParameter("route_id");

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

			FrNameIdByRouteIdResponse frNameId = restTemplate.postForObject(Constants.url + "getFrNameIdByRouteId", map,
					FrNameIdByRouteIdResponse.class);

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

		if (isAllFrSelected) {
			System.err.println("All fr Selected ");

			map.add("frIdList", 0);

		} else { // few franchisee selected

			System.out.println("Inside Else: Few Fr Selected ");
			map.add("frIdList", frIdString);

		}

		System.err.println("fr IDS " + frIdString);

		map.add("fromDate", DateConvertor.convertToYMD(fromDate));

		map.add("toDate", DateConvertor.convertToYMD(toDate));

		RegCakeAsSpDispatchReportList spList = restTemplate.postForObject(
				Constants.url + "spProduction/getRegCakeAsSpRepo", map, RegCakeAsSpDispatchReportList.class);
		if (spList.getInfo().getError() == false) {

			regCakeAsSp = spList.getRegCakeAsSp();

		}

		System.err.println("regCakeAsSp" + regCakeAsSp.toString());

		
		return regCakeAsSp;
	}

	List<AllFrIdName> tempFrList = new ArrayList<>();

	@RequestMapping(value = "/regCakeAsSp", method = RequestMethod.POST)
	public void regCakeAsSp(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException {

		Document document = new Document(PageSize.A4);

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();

		System.out.println("time in regCakeAsSp  PDF ==" + dateFormat.format(cal.getTime()));
		String timeStamp = dateFormat.format(cal.getTime());
		String FILE_PATH = Constants.REPORT_SAVE;
		File file = new File(FILE_PATH);

		PdfWriter writer = null;

		FileOutputStream out = null;
		try {
			out = new FileOutputStream(FILE_PATH);
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
		try {
			writer = PdfWriter.getInstance(document, out);
		} catch (DocumentException e) {

			e.printStackTrace();
		}
		List<AllFrIdName> frLIst = allFrIdNameList.getFrIdNamesList();

		tempFrList = new ArrayList<>();
		try {
			Map<Integer, String> frIdMap = new HashMap<Integer, String>();

			for (int i = 0; i < regCakeAsSp.size(); i++) {

				if (tempFrList.isEmpty()) {
					
					System.err.println("temp list empty");

					AllFrIdName names = new AllFrIdName();

					names.setFrId(regCakeAsSp.get(i).getFrId());
					names.setFrName(regCakeAsSp.get(i).getFrName());

					tempFrList.add(names);
					frIdMap.put(regCakeAsSp.get(i).getFrId(), regCakeAsSp.get(i).getFrName());

				} else if (!frIdMap.containsKey(regCakeAsSp.get(i).getFrId())) {
					
					System.err.println("temp list not empty");


					AllFrIdName names = new AllFrIdName();

					names.setFrId(regCakeAsSp.get(i).getFrId());
					names.setFrName(regCakeAsSp.get(i).getFrName());
					
					frIdMap.put(regCakeAsSp.get(i).getFrId(), regCakeAsSp.get(i).getFrName());

					tempFrList.add(names);

				}
			}
			
			System.err.println("Temp Fr List " +tempFrList);

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("temp Fr List " + tempFrList.toString());
		}

		PdfPTable table = new PdfPTable(4);
		try {
			System.out.println("Inside PDF Table try");
			table.setWidthPercentage(100);
			table.setWidths(new float[] { 0.4f, 2.0f, 0.6f, 1.0f });
			Font headFont = new Font(FontFamily.TIMES_ROMAN, 18, Font.NORMAL, BaseColor.BLACK);
			
			Font frFont = new Font(FontFamily.TIMES_ROMAN, 18, Font.BOLD, BaseColor.MAGENTA);

			
			Font headFont1 = new Font(FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLACK);
			Font f = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.UNDERLINE, BaseColor.BLUE);

			PdfPCell hcell = new PdfPCell();
			hcell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			hcell.setPadding(4);
			hcell = new PdfPCell(new Phrase("Sr.No.", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Item Name", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Quantity", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Remark", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			for (int k = 0; k < tempFrList.size(); k++) {

				PdfPCell cell1;
				
				
				cell1 = new PdfPCell(new Phrase("", headFont));
				cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell1.setPaddingRight(2);
				cell1.setPadding(4);
				table.addCell(cell1);
				
				
				cell1 = new PdfPCell(new Phrase(tempFrList.get(k).getFrName(), frFont));
				cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell1.setPaddingRight(2);
				cell1.setPadding(4);
				table.addCell(cell1);

				

				cell1 = new PdfPCell(new Phrase("", headFont));
				cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell1.setPaddingRight(2);
				cell1.setPadding(4);
				table.addCell(cell1);

				cell1 = new PdfPCell(new Phrase("", headFont));
				cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell1.setPaddingRight(2);
				cell1.setPadding(4);
				table.addCell(cell1);
				
				int index = 0;
				for (int j = 0; j < regCakeAsSp.size(); j++) {
					PdfPCell cell;
					
					if (tempFrList.get(k).getFrId() == regCakeAsSp.get(j).getFrId()) {
						index++;

						cell = new PdfPCell(new Phrase(String.valueOf(index), headFont));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setPadding(4);
						table.addCell(cell);

						cell = new PdfPCell(new Phrase(regCakeAsSp.get(j).getItemName(), headFont));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell.setPaddingRight(2);
						cell.setPadding(4);
						table.addCell(cell);

						cell = new PdfPCell(new Phrase(String.valueOf(regCakeAsSp.get(j).getQty()), headFont));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setPaddingRight(2);
						cell.setPadding(4);
						table.addCell(cell);

						cell = new PdfPCell(new Phrase("", headFont));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setPaddingRight(2);
						cell.setPadding(4);
						table.addCell(cell);
						
					}

				}
			}

			document.open();
			Paragraph company = new Paragraph(
					"Galdhar Foods Pvt.Ltd\n" + "Factory Add: A-32 Shendra, MIDC, Auraangabad-4331667"
							+ " Phone:0240-2466217, Email: aurangabad@monginis.net",
					f);
			company.setAlignment(Element.ALIGN_CENTER);
			document.add(company);
			document.add(new Paragraph(" "));

			Paragraph heading = new Paragraph("Regular Cake As Special Order Dispatch Report");
			heading.setAlignment(Element.ALIGN_CENTER);
			document.add(heading);
			
			DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
			String reportDate = DF.format(new Date());

			document.add(new Paragraph(" Date: " + reportDate));
			document.add(new Paragraph("\n"));
			document.add(table);
			document.add(new Paragraph("\n"));
			int totalPages = writer.getPageNumber();

			System.out.println("Page no " + totalPages);

			document.close();
			if (file != null) {
				System.err.println("File is not null");

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

			System.out.println("Pdf Generation Error: Reg cake as Sp Order Dispatch Report" + ex.getMessage());

			ex.printStackTrace();

		}

	}
}
