package com.ats.adminpanel.controller;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.model.salescompare.SalesCompareGrnTot;
import com.ats.adminpanel.model.salescompare.SalesComparison;
import com.ats.adminpanel.model.salescompare.SalesComparisonReport;

@Controller
@Scope("session")
public class SaleCompareReportController {

	@RequestMapping(value = "/showSalescomparison", method = RequestMethod.GET)
	public ModelAndView showSalescomparison(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = null;
		try {
			modelAndView = new ModelAndView("reports/salescomparison");

			int year = Year.now().getValue();

			modelAndView.addObject("year", year);
		} catch (Exception e) {

			e.printStackTrace();

		}

		return modelAndView;
	}

	/*
	 * @RequestMapping(value = "/getSalesReportComparion", method =
	 * RequestMethod.GET) public @ResponseBody List<SalesComparison>
	 * getSalesReportComparion(HttpServletRequest request, HttpServletResponse
	 * response) { // ModelAndView modelAndView = new
	 * ModelAndView("grngvn/displaygrn");
	 * 
	 * System.out.println("in method"); String month =
	 * request.getParameter("month");
	 * 
	 * RestTemplate restTemplate = new RestTemplate();
	 * 
	 * MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,
	 * Object>();
	 * 
	 * map.add("monthNumber", month); SalesComparison reportList =
	 * restTemplate.postForObject(Constants.url + "getSalesReportComparion", map,
	 * SalesComparison.class);
	 * 
	 * List<SalesComparisonReport> billTotalList = reportList.getBillTotalList();
	 * 
	 * List<SalesComparisonReport> grnGvnTotalList =
	 * reportList.getGrnGvnTotalList();
	 * 
	 * SalesComparison firstList;
	 * 
	 * 
	 * List<SalesComparison> saleCompListFirst = new ArrayList<SalesComparison>();
	 * 
	 * 
	 * 
	 * for (int i = 0; i < grnGvnTotalList.size(); i++) {
	 * 
	 * for (int j = 0; j < billTotalList.size(); j++) {
	 * 
	 * if (grnGvnTotalList.get(i).getFrId() == billTotalList.get(j).getFrId()) {
	 * 
	 * firstList = new SalesComparison();
	 * 
	 * firstList.setFrId(billTotalList.get(j).getFrId());
	 * 
	 * firstList.setPerMonthSale( billTotalList.get(j).getBillTotal() -
	 * grnGvnTotalList.get(i).getBillTotal()); saleCompListFirst.add(firstList); }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * map = new LinkedMultiValueMap<String, Object>(); int intMonth =
	 * Integer.parseInt(month); intMonth = intMonth - 1;
	 * 
	 * map.add("monthNumber", intMonth); SalesComparison prevMonthReport =
	 * restTemplate.postForObject(Constants.url + "getSalesReportComparion", map,
	 * SalesComparison.class);
	 * 
	 * List<SalesComparisonReport> billTotalListPrev =
	 * prevMonthReport.getBillTotalList();
	 * 
	 * List<SalesComparisonReport> grnGvnTotalListPrevMonth =
	 * prevMonthReport.getGrnGvnTotalList();
	 * 
	 * 
	 * System.err.println("billTotalListPrev " +billTotalListPrev.toString());
	 * System.err.println("grnGvnTotalListPrevMonth "
	 * +grnGvnTotalListPrevMonth.toString());
	 * 
	 * List<SalesComparison> saleCompListPrev = new ArrayList<SalesComparison>();
	 * 
	 * SalesComparison prevList;
	 * 
	 * 
	 * for (int i = 0; i < grnGvnTotalListPrevMonth.size(); i++) {
	 * 
	 * for (int j = 0; j < billTotalListPrev.size(); j++) {
	 * 
	 * if (grnGvnTotalListPrevMonth.get(i).getFrId() ==
	 * billTotalListPrev.get(j).getFrId()) {
	 * 
	 * prevList = new SalesComparison();
	 * 
	 * prevList.setFrId(billTotalListPrev.get(j).getFrId());
	 * 
	 * prevList.setPerMonthSale( billTotalListPrev.get(j).getBillTotal() -
	 * grnGvnTotalListPrevMonth.get(i).getBillTotal());
	 * saleCompListPrev.add(prevList); }
	 * 
	 * }
	 * 
	 * } System.err.println("saleCompListPrev " +saleCompListPrev.toString());
	 * 
	 * 
	 * //sachin
	 * 
	 * List<SalesComparison> saleCompFinal=new ArrayList<SalesComparison>();
	 * 
	 * for(int i=0;i<saleCompListFirst.size();i++) {
	 * 
	 * for(int j=0;j<saleCompListPrev.size();j++) {
	 * 
	 * 
	 * if(saleCompListFirst.get(i).getFrId()==saleCompListPrev.get(j).getFrId()) {
	 * 
	 * 
	 * SalesComparison sales=new SalesComparison();
	 * 
	 * sales.setFrId(saleCompListFirst.get(i).getFrId());
	 * 
	 * sales.setFrName(saleCompListFirst.get(i).getFrName());
	 * 
	 * sales.setPerMonthSale(saleCompListFirst.get(i).getPerMonthSale());
	 * 
	 * sales.setPrevMonthSale(saleCompListPrev.get(j).getPrevMonthSale());
	 * 
	 * sales.setLastMonthDiff(saleCompListPrev.get(j).getPrevMonthSale()-
	 * saleCompListFirst.get(i).getPerMonthSale());
	 * 
	 * float onePer=(saleCompListPrev.get(j).getPrevMonthSale()/100); float
	 * diff=saleCompListPrev.get(j).getPrevMonthSale()-saleCompListFirst.get(i).
	 * getPerMonthSale(); float per=(diff/onePer);
	 * 
	 * sales.setMonthDiffInPer(per);
	 * 
	 * sales.setRouteId(saleCompListPrev.get(j).getRouteId());
	 * 
	 * sales.setRouteName(saleCompListPrev.get(j).getRouteName());
	 * 
	 * saleCompFinal.add(sales); }
	 * 
	 * }
	 * 
	 * } System.out.println("sale comparison final " +saleCompFinal.toString());
	 * return saleCompFinal;
	 * 
	 * }
	 */

	@RequestMapping(value = "/getSalesReportComparion", method = RequestMethod.GET)
	public @ResponseBody List<SalesComparison> getSalesReportComparion(HttpServletRequest request,
			HttpServletResponse response) {
		// ModelAndView modelAndView = new ModelAndView("grngvn/displaygrn");

		System.out.println("in method");
		String month = request.getParameter("month");

		RestTemplate restTemplate = new RestTemplate();

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		map.add("monthNumber", month);
		SalesComparison reportList = restTemplate.postForObject(Constants.url + "getSalesReportComparion", map,
				SalesComparison.class);

		List<SalesComparisonReport> billTotalList = reportList.getBillTotalList();

		List<SalesCompareGrnTot> grnGvnTotalList = reportList.getGrnGvnTotalList();

		SalesComparison firstList=new SalesComparison();

		List<SalesComparison> saleCompListFirst = new ArrayList<SalesComparison>();

		for (int j = 0; j < billTotalList.size(); j++) {
			float total = billTotalList.get(j).getBillTotal();
			firstList = new SalesComparison();
			for (int i = 0; i < grnGvnTotalList.size(); i++) {

				if (grnGvnTotalList.get(i).getFrId() == billTotalList.get(j).getFrId()) {

					firstList.setFrId(billTotalList.get(j).getFrId());

					firstList.setPerMonthSale(
							billTotalList.get(j).getBillTotal() - grnGvnTotalList.get(i).getBillTotal());

					// saleCompListFirst.add(firstList);
				} else {

					firstList.setFrId(billTotalList.get(j).getFrId());

					firstList.setPerMonthSale(total);

				}

			}

			firstList.setFrName(billTotalList.get(j).getFrName());
			firstList.setRouteId(billTotalList.get(j).getFrRouteId());
			firstList.setRouteName(billTotalList.get(j).getRouteName());

			saleCompListFirst.add(firstList);
		}

		for (int i = 0; i < saleCompListFirst.size(); i++) {
		
		if(saleCompListFirst.get(i).getFrId()==15) {
			System.out.println("fr Id 15 found ");
			System.err.println("saleCompListFirst for May " +saleCompListFirst.get(i));
			
		}
			
		}

		map = new LinkedMultiValueMap<String, Object>();
		int intMonth = Integer.parseInt(month);
		intMonth = intMonth - 1;

		map.add("monthNumber", intMonth);
		SalesComparison prevMonthReport = restTemplate.postForObject(Constants.url + "getSalesReportComparion", map,
				SalesComparison.class);

		List<SalesComparisonReport> billTotalListPrev=new ArrayList<SalesComparisonReport>();
		billTotalListPrev	= prevMonthReport.getBillTotalList();

		List<SalesCompareGrnTot> grnGvnTotalListPrevMonth=new ArrayList<SalesCompareGrnTot>();
		grnGvnTotalListPrevMonth= prevMonthReport.getGrnGvnTotalList();

		// System.err.println("billTotalListPrev " +billTotalListPrev.toString());
		// System.err.println("grnGvnTotalListPrevMonth "
		// +grnGvnTotalListPrevMonth.toString());

		List<SalesComparison> saleCompListPrev = new ArrayList<SalesComparison>();

		SalesComparison prevList=new SalesComparison();

		for (int j = 0; j < billTotalListPrev.size(); j++) {

			float total = billTotalListPrev.get(j).getBillTotal();
			prevList = new SalesComparison();

			for (int i = 0; i < grnGvnTotalListPrevMonth.size(); i++) {

				if (grnGvnTotalListPrevMonth.get(i).getFrId() == billTotalListPrev.get(j).getFrId()) {
					
					if(grnGvnTotalListPrevMonth.get(i).getFrId()==15) {
						
						System.err.println("fr Id 15 is avail in prev month");
						
						System.err.println("grnGvnTotalListPrevMonth for apri " +grnGvnTotalListPrevMonth.get(i));

						System.err.println("billTotalListPrev for apri " +billTotalListPrev.get(i));

					}

					prevList.setFrId(billTotalListPrev.get(j).getFrId());

					prevList.setPrevMonthSale(
							(billTotalListPrev.get(j).getBillTotal() - grnGvnTotalListPrevMonth.get(i).getBillTotal()));
					// saleCompListPrev.add(prevList);
				} else {
					prevList.setFrId(billTotalListPrev.get(j).getFrId());

					prevList.setPrevMonthSale(total);
					// saleCompListPrev.add(prevList);
				}

			}

			prevList.setRouteId(billTotalListPrev.get(j).getFrRouteId());
			prevList.setRouteName(billTotalListPrev.get(j).getRouteName());

			prevList.setFrName(billTotalListPrev.get(j).getFrName());

			saleCompListPrev.add(prevList);

		}
		for(int j=0;j<saleCompListPrev.size();j++) {
		
		if(saleCompListPrev.get(j).getFrId()==15) {
			System.out.println("fr Id 15 found ");
			System.err.println("saleCompListPrev for April " +saleCompListPrev.get(j));
			
		}
		}
			
		// System.err.println("saleCompListPrev " +saleCompListPrev.toString());

		// sachin

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

					sales.setLastMonthDiff((
							saleCompListPrev.get(j).getPrevMonthSale() - saleCompListFirst.get(i).getPerMonthSale()));
					float onePer = 0;
					if (saleCompListPrev.get(j).getPrevMonthSale() > 0) {
						onePer = (saleCompListPrev.get(j).getPrevMonthSale() / 100);
					} else {
						onePer = 1;
					}

					float diff = saleCompListPrev.get(j).getPrevMonthSale()
							- saleCompListFirst.get(i).getPerMonthSale();
					
					float per = (diff / onePer);

					sales.setMonthDiffInPer(per);

					sales.setRouteId(saleCompListPrev.get(j).getRouteId());

					sales.setRouteName(saleCompListPrev.get(j).getRouteName());

					saleCompFinal.add(sales);
					break;
				}

			}

		}
		System.out.println("sale comparison final Size " + saleCompFinal.size());

		for (int i = 0; i < saleCompFinal.size(); i++)
			System.out.println("sale comparison final ele " + i + "" + saleCompFinal.get(i).toString());
		return saleCompFinal;

	}

}
