package com.ats.adminpanel.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.commons.DateConvertor;
import com.ats.adminpanel.model.dashboard.CredNoteReport;
import com.ats.adminpanel.model.dashboard.DashboardData;
import com.ats.adminpanel.model.dashboard.MonthlyDashboardData;
import com.ats.adminpanel.model.dashboard.PurBillBackEndReport;
import com.ats.adminpanel.model.ggreports.GrnGvnReportByGrnType;
import com.ats.adminpanel.model.salescompare.SalesComparison;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;


@Controller
@Scope("session")
public class DashboardController {

	RestTemplate rest = new RestTemplate();
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

	@RequestMapping(value = "/showDashboard", method = RequestMethod.GET)
	public ModelAndView showDashboard(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("dashboard");
		
		 LinkedHashMap<String,String> hm = new LinkedHashMap<String, String>();
        
         hm.put("4", "Apr");
         hm.put("5", "May");
         hm.put("6", "Jun");
         hm.put("7", "Jul");
         hm.put("8", "Aug");
         hm.put("9", "Sep");
         hm.put("10", "Oct");
         hm.put("11", "Nov");
         hm.put("12", "Dec");
         hm.put("1", "Jan");
         hm.put("2", "Feb");
         hm.put("3", "Mar");
         model.addObject("months", hm);
         
         
	List<MonthlyDashboardData> monthDataList=new ArrayList<>();
		try {
			// --------Getting Current Month And Year --------
			java.util.Date utildate = new Date();
			Calendar calender = Calendar.getInstance();
			calender.setTime(utildate);
			int currentMonth = calender.get(Calendar.MONTH)+1;
			int year=calender.get(Calendar.YEAR);
			//------------------------------------------------
			int startYear=year-1;
			int endYear=year;
			if(currentMonth>3)
			{
				startYear=year;
				endYear=(year+1);
			}
			for(int i=1;i<=12;i++) {
				System.err.println("||----------------------"+i+"-------------------||");
				MonthlyDashboardData monthData=new MonthlyDashboardData();
				 String fromDate="";
				 String toDate="";
				if(i<=3)
				{
			        fromDate="01-"+i+"-"+endYear;
			        try{
			        java.util.Date dt= formatter.parse(fromDate);
			        Calendar calendar = Calendar.getInstance();  
			        calendar.setTime(dt);  

			        calendar.add(Calendar.MONTH, 1);  
			        calendar.set(Calendar.DAY_OF_MONTH, 1);  
			        calendar.add(Calendar.DATE, -1);  

			        java.util.Date lastDay = calendar.getTime();  

			        toDate = formatter.format(lastDay);
			        } catch (ParseException e) {
			            e.printStackTrace();
			        }
				}
				else
				{
					 fromDate="01-"+i+"-"+startYear;
					 try{
					        java.util.Date dt= formatter.parse(fromDate);
					        Calendar calendar = Calendar.getInstance();  
					        calendar.setTime(dt);  

					        calendar.add(Calendar.MONTH, 1);  
					        calendar.set(Calendar.DAY_OF_MONTH, 1);  
					        calendar.add(Calendar.DATE, -1);  

					        java.util.Date lastDay = calendar.getTime();  

					        toDate = formatter.format(lastDay);
					        } catch (ParseException e) {
					            e.printStackTrace();
					        }
				}
				System.err.println("fromDate:"+fromDate+"toDate:"+toDate);
		
				 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			     map.add("frIdList", 0);
		         map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			     map.add("toDate", DateConvertor.convertToYMD(toDate));
			
			
				ParameterizedTypeReference<List<GrnGvnReportByGrnType>> typeRef = new ParameterizedTypeReference<List<GrnGvnReportByGrnType>>() {
				};
				ResponseEntity<List<GrnGvnReportByGrnType>> responseEntity = rest.exchange(
						Constants.url + "getGrnGvnReportByGrnType", HttpMethod.POST, new HttpEntity<>(map), typeRef);
				
				List<GrnGvnReportByGrnType> grnGvnByTypeList = responseEntity.getBody();
				System.err.println("grn"+grnGvnByTypeList.toString());
				
				float grnAmt1 = 0;float grnAmt2 = 0;float grnAmt3 = 0;float grnTotalAmt = 0;float gvnAmt=0;long netSalesAmt=0;
				long salesAmt=0;
				map = new LinkedMultiValueMap<String, Object>();
				map.add("monthNumber", i);
				if(i<=3)
				{
				map.add("year", endYear);
				}
				else
				{
				map.add("year", startYear);
				}
				SalesComparison	reportList = rest.postForObject(Constants.url + "getSalesReportComparion", map,
						SalesComparison.class);
				System.err.println("report"+reportList.toString());
				for(int k=0;k<reportList.getBillTotalList().size();k++)
				{
					salesAmt=salesAmt+(Math.round(reportList.getBillTotalList().get(k).getBillTotal()));
				}
				monthData.setSalesAmt(salesAmt);
				
				for(int j=0;j<grnGvnByTypeList.size();j++)
				{
					grnAmt1=grnAmt1+(roundUp(grnGvnByTypeList.get(j).getAprAmtGrn1()));
					grnAmt2=grnAmt2+(roundUp(grnGvnByTypeList.get(j).getAprAmtGrn2()));
					grnAmt3=grnAmt3+(roundUp(grnGvnByTypeList.get(j).getAprAmtGrn3()));
					gvnAmt=gvnAmt+(roundUp(grnGvnByTypeList.get(j).getAprAmtGvn()));
				}
				grnTotalAmt=grnTotalAmt+(grnAmt1+grnAmt2+grnAmt3);

				netSalesAmt=salesAmt-(Math.round(grnTotalAmt+gvnAmt));
				
				monthData.setGrnAmt1(grnAmt1);
				monthData.setGrnAmt2(grnAmt2);
				monthData.setGrnAmt3(grnAmt3);
				monthData.setGrnTotalAmt(grnTotalAmt);
				monthData.setGvnAmt(gvnAmt);
				monthData.setMonth(i);
				monthData.setNetSaleAmt(netSalesAmt);
				if(i<=3)
				{
					monthData.setYear(endYear);
				}
				else
				{
					monthData.setYear(startYear);
				}
				monthDataList.add(monthData);
			}
			System.err.println(monthDataList.toString());	
          model.addObject("monthDataList", monthDataList);
          
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return model;
	}
	public static float roundUp(float d) {
		return BigDecimal.valueOf(d).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
	}
	@RequestMapping(value = "/getDashboardData", method = RequestMethod.GET)
	public @ResponseBody DashboardData getDashboardData(HttpServletRequest request,
			HttpServletResponse response) {

		DashboardData dashData=new DashboardData();
		try {
			CredNoteReport daygrn=null;
			CredNoteReport	daygvn=null;
			CredNoteReport	monthgrn=null;
			CredNoteReport	monthgvn=null;
			CredNoteReport yeargrn=null;
			CredNoteReport yeargvn=null;
			PurBillBackEndReport daysales=null;PurBillBackEndReport monthsale=null;PurBillBackEndReport yearSale=null;
			
			String date = request.getParameter("date");
			String ymdDate=DateConvertor.convertToYMD(date);
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
            map.add("fromDate", ymdDate);
            map.add("toDate", ymdDate);
            
			 try {
				daysales=rest.postForObject(Constants.url + "/getPurBillReport", map, PurBillBackEndReport.class);
				dashData.setDaySale(daysales);
			} catch (Exception e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			
			map = new LinkedMultiValueMap<String, Object>();
            map.add("fromDate", ymdDate);
            map.add("toDate", ymdDate);
            map.add("frIdList", 0);
            map.add("isGrn",1);
				try {
					daygrn=rest.postForObject(Constants.url + "/getCredNoteReport", map, CredNoteReport.class);
                    dashData.setDayGrn(daygrn);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			map = new LinkedMultiValueMap<String, Object>();
            map.add("fromDate", ymdDate);
            map.add("toDate", ymdDate);
            map.add("frIdList", 0);
            map.add("isGrn",0);
				try {
					daygvn=rest.postForObject(Constants.url + "/getCredNoteReport", map, CredNoteReport.class);
dashData.setDayGvn(daygvn);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	            Date d = sdf.parse(date);
	            Calendar cal = Calendar.getInstance();
	            cal.setTime(d);
	            int month = cal.get(Calendar.MONTH)+1;
	            int year=cal.get(Calendar.YEAR);
	            String startDate=year+"-"+month+"-01";
	            System.err.println(startDate);
	            
	           
	            
	            map = new LinkedMultiValueMap<String, Object>();
	            map.add("fromDate", startDate);
	            map.add("toDate", ymdDate);
	            
				 try {
					monthsale=rest.postForObject(Constants.url + "/getPurBillReport", map, PurBillBackEndReport.class);
					dashData.setMonthSale(monthsale);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				map = new LinkedMultiValueMap<String, Object>();
	            map.add("fromDate", startDate);
	            map.add("toDate", ymdDate);
	            map.add("frIdList", 0);
	            map.add("isGrn",1);
					try {
						monthgrn=rest.postForObject(Constants.url + "/getCredNoteReport", map, CredNoteReport.class);
dashData.setMonthGrn(monthgrn);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				map = new LinkedMultiValueMap<String, Object>();
	            map.add("fromDate", startDate);
	            map.add("toDate", ymdDate);
	            map.add("frIdList", 0);
	            map.add("isGrn",0);
					try {
						monthgvn=rest.postForObject(Constants.url + "/getCredNoteReport", map, CredNoteReport.class);
dashData.setMonthGvn(monthgvn);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				    java.util.Date utildate= new Date();
		            Calendar calender = Calendar.getInstance();
		            calender.setTime(utildate);
		            int currentMonth = calender.get(Calendar.MONTH)+1;
		            int prevYear=year-1;
		            String yearStartDate=prevYear+"-04-01";
		            if(currentMonth>3)
		            {
		            	yearStartDate=year+"-04-01";
		            }
		            
				  map = new LinkedMultiValueMap<String, Object>();
		            map.add("fromDate", yearStartDate);
		            map.add("toDate", ymdDate);
		            
					 try {
						yearSale=rest.postForObject(Constants.url + "/getPurBillReport", map, PurBillBackEndReport.class);
						dashData.setYearSale(yearSale);
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
                    
					map = new LinkedMultiValueMap<String, Object>();
		            map.add("fromDate", yearStartDate);
		            map.add("toDate", ymdDate);
		            map.add("frIdList", 0);
		            map.add("isGrn",1);
						try {
							yeargrn=rest.postForObject(Constants.url + "/getCredNoteReport", map, CredNoteReport.class);
dashData.setYearGrn(yeargrn);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
					map = new LinkedMultiValueMap<String, Object>();
		            map.add("fromDate", yearStartDate);
		            map.add("toDate", ymdDate);
		            map.add("frIdList", 0);
		            map.add("isGrn",0);
						try {
							yeargvn=rest.postForObject(Constants.url + "/getCredNoteReport", map, CredNoteReport.class);
dashData.setYearGvn(yeargvn);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					System.err.println("GRN YEAR "+yeargrn.toString());
					System.err.println("GVN YEAR "+yeargvn.toString());

					System.err.println("GRN Month "+monthgrn.toString());
					System.err.println("dashData "+dashData.toString());

		}
			catch (Exception e) {
			e.printStackTrace();
		}
		
		return dashData;
	}
}
