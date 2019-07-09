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
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.model.AllRoutesListResponse;
import com.ats.adminpanel.model.ExportToExcel;
import com.ats.adminpanel.model.Route;
import com.ats.adminpanel.model.salescompare.SalesCompareGrnTot;
import com.ats.adminpanel.model.salescompare.SalesCompareList;
import com.ats.adminpanel.model.salescompare.SalesComparison;
import com.ats.adminpanel.model.salescompare.SalesComparisonReport;
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
public class SalesCompareFrController {

	public static float roundUp(double d) {
		return BigDecimal.valueOf(d).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
	}

	SalesCompareList salesCompareList = new SalesCompareList();
	SalesComparison reportList = new SalesComparison();

	@RequestMapping(value = "/showSalescomparisonFr", method = RequestMethod.GET)
	public ModelAndView showSalescomparisonFr(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = null;
		try {
			modelAndView = new ModelAndView("reports/salescomparisonFr");

			int year = Year.now().getValue();
			int prevYear = year - 1;

			modelAndView.addObject("prevYear", prevYear);
			modelAndView.addObject("year", year);
		} catch (Exception e) {

			e.printStackTrace();

		}

		return modelAndView;
	}

	@RequestMapping(value = "/getSalesReportComparionFr", method = RequestMethod.GET)
	public @ResponseBody SalesCompareList getSalesReportComparionFr(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			String month = request.getParameter("month");
			String month_next = request.getParameter("month_next");

			System.err.println("Year" + month + "Month" + month_next);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			int year = Year.now().getValue();
			int year_next = Year.now().getValue();

			String m = null;
			String m_next = null;

			if (Integer.parseInt(month) > 12) {

				year = year - 1;

			}
			if (Integer.parseInt(month_next) > 12) {

				year_next = year_next - 1;

			}

			System.err.println("Year " + year_next + "Month_next" + m_next);
			System.err.println("Year " + year + "Month " + m);

			if (Integer.parseInt(month) == 13) {
				System.err.println("a 13");
				m = "1";
			} else if (Integer.parseInt(month) == 20) {
				System.err.println("a 20");
				m = "2";
			} else if (Integer.parseInt(month) == 30) {
				System.err.println("a 30");
				m = "3";
			} else if (Integer.parseInt(month) == 40) {
				System.err.println("a 40");
				m = "4";
			} else if (Integer.parseInt(month) == 50) {
				System.err.println("a 50");
				m = "5";
			} else if (Integer.parseInt(month) == 60) {
				System.err.println("a 60");
				m = "6";
			} else if (Integer.parseInt(month) == 70) {
				System.err.println("a 70");
				m = "7";
			} else if (Integer.parseInt(month) == 80) {
				System.err.println("a 80");
				m = "8";
			} else if (Integer.parseInt(month) == 90) {
				System.err.println("a 90");
				m = "9";
			} else if (Integer.parseInt(month) == 100) {
				System.err.println("a 100");
				m = "10";
			} else if (Integer.parseInt(month) == 110) {
				System.err.println("a 110");
				m = "11";
			} else if (Integer.parseInt(month) == 120) {
				System.err.println("a 120");
				m = "12";
			} else {
				m = month;
				System.err.println("m Else final ");
			}

			if (Integer.parseInt(month_next) == 13) {
				System.err.println("a 13");
				m_next = "1";
			} else if (Integer.parseInt(month_next) == 20) {
				System.err.println("a 20");
				m_next = "2";
			} else if (Integer.parseInt(month_next) == 30) {
				System.err.println("a 30");
				m_next = "3";
			} else if (Integer.parseInt(month_next) == 40) {
				System.err.println("a 40");
				m_next = "4";
			} else if (Integer.parseInt(month_next) == 50) {
				System.err.println("a 50");
				m_next = "5";
			} else if (Integer.parseInt(month_next) == 60) {
				System.err.println("a 60");
				m_next = "6";
			} else if (Integer.parseInt(month_next) == 70) {
				System.err.println("a 70");
				m_next = "7";
			} else if (Integer.parseInt(month_next) == 80) {
				System.err.println("a 80");
				m_next = "8";
			} else if (Integer.parseInt(month_next) == 90) {
				System.err.println("a 90");
				m_next = "9";
			} else if (Integer.parseInt(month_next) == 100) {
				System.err.println("a 100");
				m_next = "10";
			} else if (Integer.parseInt(month_next) == 110) {
				System.err.println("a 110");
				m_next = "11";
			} else if (Integer.parseInt(month_next) == 120) {
				System.err.println("a 120");
				m_next = "12";
			} else {
				m_next = month_next;
				System.err.println("m Else final ");
			}

			System.err.println("Year " + year_next + "Month " + m_next);
			System.err.println("Year " + year + "Month " + m);

			map.add("monthNumber", m);
			map.add("year", year);

			RestTemplate restTemplate = new RestTemplate();

			SalesComparison reportList = restTemplate.postForObject(Constants.url + "getSalesReportComparionForFr", map,
					SalesComparison.class);
			AllRoutesListResponse allRouteListResponse = restTemplate.getForObject(Constants.url + "showRouteList",
					AllRoutesListResponse.class);

			List<Route> routeList = allRouteListResponse.getRoute();
			List<SalesComparisonReport> billTotalList = reportList.getBillTotalList();

			List<SalesCompareGrnTot> grnGvnTotalList = reportList.getGrnGvnTotalList();

			SalesComparison firstList = new SalesComparison();

			List<SalesComparison> saleCompListFirst = new ArrayList<SalesComparison>();

			for (int j = 0; j < billTotalList.size(); j++) {
				float total = billTotalList.get(j).getBillTotal();
				firstList = new SalesComparison();
				firstList.setFrId(billTotalList.get(j).getFrId());

				firstList.setPerMonthSale(total);

				for (int i = 0; i < grnGvnTotalList.size(); i++) {

					if (grnGvnTotalList.get(i).getFrId() == billTotalList.get(j).getFrId()) {

						firstList.setFrId(billTotalList.get(j).getFrId());

						float finalGrnTotal = (float) (((grnGvnTotalList.get(i).getBillTotal() * 100) / 75) * 0.25);

						System.out.println("finalGrnTotal*********" + finalGrnTotal);

						firstList.setPerMonthSale(billTotalList.get(j).getBillTotal() - finalGrnTotal);

					}

				}
				System.out.println(firstList);

				firstList.setFrName(billTotalList.get(j).getFrName());
				firstList.setRouteId(billTotalList.get(j).getFrRouteId());
				firstList.setRouteName(billTotalList.get(j).getRouteName());

				saleCompListFirst.add(firstList);
			}

			for (int i = 0; i < saleCompListFirst.size(); i++) {

				if (saleCompListFirst.get(i).getFrId() == 15) {
					System.out.println("fr Id 15 found ");
					System.err.println("saleCompListFirst for May " + saleCompListFirst.get(i));

				}

			}

			map = new LinkedMultiValueMap<String, Object>();
			int intMonth = Integer.parseInt(m_next);
			// intMonth = intMonth - 1;

			map.add("monthNumber", intMonth);
			map.add("year", year_next);
			SalesComparison prevMonthReport = restTemplate.postForObject(Constants.url + "getSalesReportComparionForFr",
					map, SalesComparison.class);

			List<SalesComparisonReport> billTotalListPrev = new ArrayList<SalesComparisonReport>();
			billTotalListPrev = prevMonthReport.getBillTotalList();

			List<SalesCompareGrnTot> grnGvnTotalListPrevMonth = new ArrayList<SalesCompareGrnTot>();
			grnGvnTotalListPrevMonth = prevMonthReport.getGrnGvnTotalList();

			List<SalesComparison> saleCompListPrev = new ArrayList<SalesComparison>();

			SalesComparison prevList = new SalesComparison();

			for (int j = 0; j < billTotalListPrev.size(); j++) {

				float total = billTotalListPrev.get(j).getBillTotal();
				prevList = new SalesComparison();
				prevList.setFrId(billTotalListPrev.get(j).getFrId());

				prevList.setPrevMonthSale(total);
				for (int i = 0; i < grnGvnTotalListPrevMonth.size(); i++) {

					if (grnGvnTotalListPrevMonth.get(i).getFrId() == billTotalListPrev.get(j).getFrId()) {

						if (grnGvnTotalListPrevMonth.get(i).getFrId() == 15) {

							System.err.println("fr Id 15 is avail in prev month");

							System.err.println("grnGvnTotalListPrevMonth for apri " + grnGvnTotalListPrevMonth.get(i));

							System.err.println("billTotalListPrev for apri " + billTotalListPrev.get(i));

						}

						prevList.setFrId(billTotalListPrev.get(j).getFrId());

						float finalGrnPrevTotal = (float) (((grnGvnTotalListPrevMonth.get(i).getBillTotal() * 100) / 75)
								* 0.25);

						System.out.println("finalGrnTotal*********" + finalGrnPrevTotal);

						prevList.setPrevMonthSale((billTotalListPrev.get(j).getBillTotal() - finalGrnPrevTotal));

					}

				}

				prevList.setRouteId(billTotalListPrev.get(j).getFrRouteId());
				prevList.setRouteName(billTotalListPrev.get(j).getRouteName());

				prevList.setFrName(billTotalListPrev.get(j).getFrName());

				saleCompListPrev.add(prevList);

			}
			for (int j = 0; j < saleCompListPrev.size(); j++) {

				if (saleCompListPrev.get(j).getFrId() == 15) {
					System.out.println("fr Id 15 found ");
					System.err.println("saleCompListPrev for April " + saleCompListPrev.get(j));

				}
			}

			List<SalesComparison> saleCompFinal = new ArrayList<SalesComparison>();
			SalesComparison sales;

			for (int i = 0; i < saleCompListFirst.size(); i++) {
				sales = new SalesComparison();
				for (int j = 0; j < saleCompListPrev.size(); j++) {

					if (saleCompListFirst.get(i).getFrId() == saleCompListPrev.get(j).getFrId()) {

						sales.setFrId(saleCompListFirst.get(i).getFrId());

						sales.setFrName(saleCompListFirst.get(i).getFrName());

						sales.setPerMonthSale(saleCompListFirst.get(i).getPerMonthSale());

						sales.setPrevMonthSale(saleCompListPrev.get(j).getPrevMonthSale());

						sales.setLastMonthDiff((saleCompListFirst.get(i).getPerMonthSale()
								- saleCompListPrev.get(j).getPrevMonthSale()));
						float onePer = 0;
						if (saleCompListPrev.get(j).getPrevMonthSale() > 0) {
							onePer = (saleCompListPrev.get(j).getPrevMonthSale() / 100);
						} else {
							onePer = 1;
						}

						float diff = saleCompListFirst.get(i).getPerMonthSale()
								- saleCompListPrev.get(j).getPrevMonthSale();

						float per = (diff / onePer);

						sales.setMonthDiffInPer(per);

						sales.setRouteId(saleCompListPrev.get(j).getRouteId());

						sales.setRouteName(saleCompListPrev.get(j).getRouteName());
						saleCompFinal.add(sales);
						break;
					}

				}

			}

			/*
			 * for (int i = 0; i < saleCompFinal.size(); i++)
			 * System.out.println("sale comparison final ele " + i + "" +
			 * saleCompFinal.get(i).toString());
			 */
			salesCompareList.setRouteList(routeList);
			salesCompareList.setSaleCompFinal(saleCompFinal);
			List<String> months = Arrays.asList("", "January", "February", "March", "April", "May", "June", "July",
					"August", "September", "October", "November", "December");

			salesCompareList.setPrevMonth(months.get(Integer.parseInt(m)) + "-" + year);
			salesCompareList.setCurrMonth(months.get(intMonth) + "-" + year_next);

			// export to excel

			List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

			ExportToExcel expoExcel = new ExportToExcel();
			List<String> rowData = new ArrayList<String>();

			rowData.add("Party Name");
			rowData.add("First Month Sale Value(" + salesCompareList.getPrevMonth() + ")");
			rowData.add("Second Month Sale Value(" + salesCompareList.getCurrMonth() + ")");
			rowData.add("Last Month Diff(" + salesCompareList.getPrevMonth() + "--" + salesCompareList.getCurrMonth()
					+ ")");
			rowData.add("%");
			rowData.add("Route");
			rowData.add("Average Per Day Sale");
			rowData.add("11.11% (" + salesCompareList.getPrevMonth() + ")");
			rowData.add("14.9% (" + salesCompareList.getPrevMonth() + ")");
			rowData.add("17.6% (" + salesCompareList.getPrevMonth() + ")");

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);
			for (Route route : salesCompareList.getRouteList()) {

				double currRouteTotal = 0;
				double prevMonthRouteTotal = 0;
				for (int i = 0; i < salesCompareList.getSaleCompFinal().size(); i++) {

					if (route.getRouteId() == salesCompareList.getSaleCompFinal().get(i).getRouteId()) {

						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();
						float perDaySaleAvg = roundUp(
								salesCompareList.getSaleCompFinal().get(i).getPerMonthSale() / 30);
						double per1 = salesCompareList.getSaleCompFinal().get(i).getPerMonthSale() * 0.1111;
						double per2 = salesCompareList.getSaleCompFinal().get(i).getPerMonthSale() * 0.149;
						double per3 = salesCompareList.getSaleCompFinal().get(i).getPerMonthSale() * 0.176;
						currRouteTotal = currRouteTotal + salesCompareList.getSaleCompFinal().get(i).getPerMonthSale();
						prevMonthRouteTotal = prevMonthRouteTotal
								+ salesCompareList.getSaleCompFinal().get(i).getPrevMonthSale();

						rowData.add("" + salesCompareList.getSaleCompFinal().get(i).getFrName());
						rowData.add("" + salesCompareList.getSaleCompFinal().get(i).getPerMonthSale());
						rowData.add("" + salesCompareList.getSaleCompFinal().get(i).getPrevMonthSale());

						rowData.add("" + salesCompareList.getSaleCompFinal().get(i).getLastMonthDiff());
						rowData.add(roundUp(salesCompareList.getSaleCompFinal().get(i).getMonthDiffInPer()) + "");
						rowData.add("" + salesCompareList.getSaleCompFinal().get(i).getRouteName());
						rowData.add("" + perDaySaleAvg);
						rowData.add(roundUp(per1) + "");
						rowData.add(roundUp(per2) + "");
						rowData.add(roundUp(per3) + "");
						expoExcel.setRowData(rowData);
						exportToExcelList.add(expoExcel);

					}
				}
				expoExcel = new ExportToExcel();
				rowData = new ArrayList<String>();

				rowData.add("Route Total");
				rowData.add(roundUp(prevMonthRouteTotal) + "");
				rowData.add(roundUp(currRouteTotal) + "");
				rowData.add("");
				rowData.add("");
				rowData.add("");
				rowData.add("");
				rowData.add("");
				rowData.add("");
				rowData.add("");

				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);

				expoExcel = new ExportToExcel();
				rowData = new ArrayList<String>();
				rowData.add("");
				rowData.add("");
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
			}

			HttpSession session = request.getSession();
			session.setAttribute("exportExcelList", exportToExcelList);
			session.setAttribute("excelName", "SalesCompareList");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return salesCompareList;

	}

	@RequestMapping(value = "/showSalesComparePdfFr", method = RequestMethod.GET)
	public void showSalesComparePdfFr(HttpServletRequest request, HttpServletResponse response)
			throws FileNotFoundException {

		BufferedOutputStream outStream = null;
		System.out.println("Inside Pdf showSalesComparePdf");

		// moneyOutList = prodPlanDetailList;
		// ByteArrayOutputStream out = new ByteArrayOutputStream();
		// Rectangle envelope = new Rectangle(700, 252);
		Document document = new Document(PageSize.A3);

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();

		System.out.println("time in Gen Bill PDF ==" + dateFormat.format(cal.getTime()));
		String FILE_PATH = Constants.REPORT_SAVE;
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
			table.setWidths(new float[] { 7f, 5.3f, 5.3f, 5.3f, 5.2f, 5.9f, 5f, 5f, 5f, 5f });
			Font headFont = new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
			Font headFont1 = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
			headFont1.setColor(BaseColor.WHITE);
			Font f = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.NORMAL, BaseColor.BLUE);

			PdfPCell hcell = new PdfPCell();
			hcell.setBackgroundColor(BaseColor.PINK);

			hcell = new PdfPCell(new Phrase("Party Name", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(
					new Phrase("Prev Month Sale Value(" + salesCompareList.getPrevMonth() + ")", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(
					new Phrase("Current Month Sale Value(" + salesCompareList.getCurrMonth() + ")", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase(
					"Last Month Diff(" + salesCompareList.getPrevMonth() + "--" + salesCompareList.getCurrMonth() + ")",
					headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("%", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Route", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Average Per Day Sale", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("11.11% (" + salesCompareList.getPrevMonth() + ")", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("14.9% (" + salesCompareList.getPrevMonth() + ")", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("17.6% (" + salesCompareList.getPrevMonth() + ")", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);
			table.setHeaderRows(1);

			for (Route route : salesCompareList.getRouteList()) {

				double currRouteTotal = 0;
				double prevMonthRouteTotal = 0;
				for (SalesComparison salesComparison : salesCompareList.getSaleCompFinal()) {

					if (route.getRouteId() == salesComparison.getRouteId()) {
						PdfPCell cell;
						cell = new PdfPCell(new Phrase("" + salesComparison.getFrName(), headFont));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell.setPaddingRight(2);
						cell.setPadding(3);
						table.addCell(cell);

						cell = new PdfPCell(new Phrase("" + salesComparison.getPerMonthSale(), headFont));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						cell.setPaddingRight(2);
						cell.setPadding(3);
						table.addCell(cell);

						cell = new PdfPCell(new Phrase("" + salesComparison.getPrevMonthSale(), headFont));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						cell.setPaddingRight(2);
						cell.setPadding(3);
						table.addCell(cell);

						currRouteTotal = currRouteTotal + salesComparison.getPerMonthSale();
						prevMonthRouteTotal = prevMonthRouteTotal + salesComparison.getPrevMonthSale();
						float perDaySaleAvg = roundUp(salesComparison.getPerMonthSale() / 30);
						double per1 = salesComparison.getPerMonthSale() * 0.1111;
						double per2 = salesComparison.getPerMonthSale() * 0.149;
						double per3 = salesComparison.getPerMonthSale() * 0.176;

						cell = new PdfPCell(new Phrase("" + salesComparison.getLastMonthDiff(), headFont));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						cell.setPaddingRight(2);
						cell.setPadding(3);
						table.addCell(cell);

						cell = new PdfPCell(new Phrase(roundUp(salesComparison.getMonthDiffInPer()) + "", headFont));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						cell.setPaddingRight(2);
						cell.setPadding(3);
						table.addCell(cell);

						cell = new PdfPCell(new Phrase("" + salesComparison.getRouteName(), headFont));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell.setPaddingRight(2);
						cell.setPadding(3);
						table.addCell(cell);

						cell = new PdfPCell(new Phrase("" + perDaySaleAvg, headFont));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						cell.setPaddingRight(2);
						cell.setPadding(3);
						table.addCell(cell);

						cell = new PdfPCell(new Phrase(roundUp(per1) + "", headFont));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						cell.setPaddingRight(2);
						cell.setPadding(3);
						table.addCell(cell);

						cell = new PdfPCell(new Phrase(roundUp(per2) + "", headFont));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						cell.setPaddingRight(2);
						cell.setPadding(3);
						table.addCell(cell);

						cell = new PdfPCell(new Phrase(roundUp(per3) + "", headFont));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						cell.setPaddingRight(2);
						cell.setPadding(3);
						table.addCell(cell);

					}
				}
				PdfPCell cell;
				cell = new PdfPCell(new Phrase("Route Total", f));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(roundUp(prevMonthRouteTotal) + "", f));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(roundUp(currRouteTotal) + "", f));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("", headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("", headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("", headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("", headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("", headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("", headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("", headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);
			}

			document.open();
			Paragraph name = new Paragraph(" Galdhar Foods\n", f);
			name.setAlignment(Element.ALIGN_CENTER);
			document.add(name);
			document.add(new Paragraph(" "));
			DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
			String reportDate = DF.format(new Date());
			Paragraph company = new Paragraph("Sales Comparison Report " + reportDate, f);
			company.setAlignment(Element.ALIGN_CENTER);
			document.add(company);
			document.add(new Paragraph(" "));

			document.add(new Paragraph("\n"));
			document.add(table);

			int totalPages = writer.getPageNumber();

			System.out.println("Page no " + totalPages);

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

				InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

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
