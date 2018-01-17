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

import javax.mail.Session;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.zefer.pd4ml.PD4Constants;
import org.zefer.pd4ml.PD4ML;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.commons.DateConvertor;
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
	static List<GetMaterialRecieptReportBillWise> billWisePdf;
	@RequestMapping(value = "/purchaseReport", method = RequestMethod.GET)
	public ModelAndView purchaseReport(HttpServletRequest request, HttpServletResponse response) 
	{
		
		ModelAndView model = new ModelAndView("masters/materialRecieptReport/report");
		RestTemplate rest = new RestTemplate();
		try
		{
			supplierDetailsList = rest.getForObject(Constants.url + "/getAllSupplierlist", Supplist.class);
			model.addObject("supplierList",supplierDetailsList.getSupplierDetailslist());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		 

		 
		return model;

	}
	
	@RequestMapping(value = "/materialRecieptBillWiseReport", method = RequestMethod.GET)
	public @ResponseBody List<GetMaterialRecieptReportBillWise> materialRecieptBillWiseReport(HttpServletRequest request, HttpServletResponse response) 
	{
		
		List<GetMaterialRecieptReportBillWise> materialRecieptBillWiseReport = new ArrayList<GetMaterialRecieptReportBillWise>();
		
		 
		RestTemplate rest = new RestTemplate();
		try
		{
			String fromDate = request.getParameter("from_date");
			String todate = request.getParameter("to_date");
			String[] supplier = request.getParameterValues("suppliers[]");
			System.out.println("fromDate"+fromDate);
			System.out.println("todate"+todate);
			System.out.println("supplier"+supplier.toString());
			
			StringBuilder sb = new StringBuilder();
			String suppliers = null;
			
			 if(supplier[0].equals("-1"))
			 {
				 System.out.println("in if");
				 for (int i = 0; i < supplierDetailsList.getSupplierDetailslist().size(); i++) {
						sb = sb.append(supplierDetailsList.getSupplierDetailslist().get(i).getSuppId() + ",");

					}

					suppliers = sb.toString();
					suppliers = suppliers.substring(0, suppliers.length() - 1);
					System.out.println("suppliers id list is" + suppliers.toString());
				 
			 }
			 else
			 {
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
		 materialRecieptBillWiseReport = rest.postForObject(Constants.url + "/materialRecieptBillWiseReport",map, List.class);
		 billWisePdf=materialRecieptBillWiseReport;
		 System.out.println("materialRecieptBillWiseReport"+materialRecieptBillWiseReport.toString());
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		} 
		return materialRecieptBillWiseReport;

	}
	
	@RequestMapping(value = "/billWisePdf", method = RequestMethod.GET)
	public ModelAndView billWisePdf(HttpServletRequest request, HttpServletResponse response) 
	{
		
		ModelAndView model = new ModelAndView("masters/materialRecieptReport/pdf/billwise");
		try
		{
			model.addObject("staticlist",billWisePdf);
			System.out.println("billWisePdf"+billWisePdf);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		  return model;

	}
	
	static List<GetMaterialRecieptReportBillWise> supplierWisePdf;
	
	@RequestMapping(value = "/materialRecieptsSupplierWise", method = RequestMethod.GET)
	public ModelAndView materialRecieptsSupplierWise(HttpServletRequest request, HttpServletResponse response) 
	{
		
		ModelAndView model = new ModelAndView("masters/materialRecieptReport/supplierWise");
		RestTemplate rest = new RestTemplate();
		try
		{
			supplierDetailsList = rest.getForObject(Constants.url + "/getAllSupplierlist", Supplist.class);
			model.addObject("supplierList",supplierDetailsList.getSupplierDetailslist());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		 

		 
		return model;

	}
	
	@RequestMapping(value = "/materialRecieptsSupplierWiseReport", method = RequestMethod.GET)
	public @ResponseBody List<GetMaterialRecieptReportBillWise> materialRecieptsSupplierWiseReport(HttpServletRequest request, HttpServletResponse response) 
	{
		List<GetMaterialRecieptReportBillWise> materialRecieptsSupplierWiseReport = new ArrayList<GetMaterialRecieptReportBillWise>();
		
		 
		RestTemplate rest = new RestTemplate();
		try
		{
			String fromDate = request.getParameter("from_date");
			String todate = request.getParameter("to_date");
			String[] supplier = request.getParameterValues("suppliers[]");
			System.out.println("fromDate"+fromDate);
			System.out.println("todate"+todate);
			System.out.println("supplier"+supplier.toString());
			
			StringBuilder sb = new StringBuilder();
			String suppliers = null;
			
			 if(supplier[0].equals("-1"))
			 {
				 System.out.println("in if");
				 for (int i = 0; i < supplierDetailsList.getSupplierDetailslist().size(); i++) {
						sb = sb.append(supplierDetailsList.getSupplierDetailslist().get(i).getSuppId() + ",");

					} 
				 suppliers = sb.toString();
				 suppliers = suppliers.substring(0, suppliers.length() - 1);
				System.out.println("suppliers id list is" + suppliers.toString());
				 
			 }
			 else
			 {
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
			materialRecieptsSupplierWiseReport = rest.postForObject(Constants.url + "/materialRecieptSupplierWiseReport",map, List.class);
		 System.out.println("materialRecieptBillWiseReport"+materialRecieptsSupplierWiseReport.toString());
		 supplierWisePdf=materialRecieptsSupplierWiseReport;
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		} 
		return materialRecieptsSupplierWiseReport;

	}
	
	
	@RequestMapping(value = "/supplierWisePdf", method = RequestMethod.GET)
	public ModelAndView supplierWisePdf(HttpServletRequest request, HttpServletResponse response) 
	{
		
		ModelAndView model = new ModelAndView("masters/materialRecieptReport/pdf/supplierwisepdf");
		try
		{
			model.addObject("staticlist",supplierWisePdf);
			System.out.println("supplierWisePdf"+supplierWisePdf);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		  return model;

	}
	
	static List<GetMaterialRecieptReportBillWise> dateWisePdf;
	@RequestMapping(value = "/materialRecieptsDateWise", method = RequestMethod.GET)
	public @ResponseBody ModelAndView materialRecieptsDateWise(HttpServletRequest request, HttpServletResponse response) 
	{
		
		ModelAndView model = new ModelAndView("masters/materialRecieptReport/dateWise");
		RestTemplate rest = new RestTemplate();
		try
		{
			supplierDetailsList = rest.getForObject(Constants.url + "/getAllSupplierlist", Supplist.class);
			model.addObject("supplierList",supplierDetailsList.getSupplierDetailslist());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		 

		 
		return model;

	}
	
	@RequestMapping(value = "/materialRecieptsDateWiseReport", method = RequestMethod.GET)
	public @ResponseBody List<GetMaterialRecieptReportBillWise> materialRecieptsDateWiseReport(HttpServletRequest request, HttpServletResponse response) 
	{
		List<GetMaterialRecieptReportBillWise> materialRecieptsDateWiseReport = new ArrayList<GetMaterialRecieptReportBillWise>();
		
		 
		RestTemplate rest = new RestTemplate();
		try
		{
			String fromDate = request.getParameter("from_date");
			String todate = request.getParameter("to_date");
			String[] supplier = request.getParameterValues("suppliers[]");
			System.out.println("fromDate"+fromDate);
			System.out.println("todate"+todate);
			System.out.println("supplier"+supplier.toString());
			
			StringBuilder sb = new StringBuilder();
			String suppliers = null;
			
			 if(supplier[0].equals("-1"))
			 {
				 System.out.println("in if");
				 for (int i = 0; i < supplierDetailsList.getSupplierDetailslist().size(); i++) {
						sb = sb.append(supplierDetailsList.getSupplierDetailslist().get(i).getSuppId() + ",");

					} 
				 suppliers = sb.toString();
				 suppliers = suppliers.substring(0, suppliers.length() - 1);
				System.out.println("suppliers id list is" + suppliers.toString());
				 
			 }
			 else
			 {
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
			materialRecieptsDateWiseReport = rest.postForObject(Constants.url + "/materialRecieptDateWiseReport",map, List.class);
		 System.out.println("materialRecieptBillWiseReport"+materialRecieptsDateWiseReport.toString());
		 dateWisePdf=materialRecieptsDateWiseReport; 
		}catch(Exception e)
		{
			e.printStackTrace();
		} 
		return materialRecieptsDateWiseReport;

	}
	
	@RequestMapping(value = "/dateWisePdf", method = RequestMethod.GET)
	public ModelAndView dateWisePdf(HttpServletRequest request, HttpServletResponse response) 
	{
		
		ModelAndView model = new ModelAndView("masters/materialRecieptReport/pdf/dateWisePdf");
		try
		{
			model.addObject("staticlist",dateWisePdf);
			System.out.println("dateWisePdf"+dateWisePdf);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		  return model;

	}
	
	static List<GetMaterialRecieptReportItemWise> ItemWisepdf;
	GetRawMaterialDetailList getRawMaterialDetail = new GetRawMaterialDetailList();
	@RequestMapping(value = "/materialRecieptsItemWise", method = RequestMethod.GET)
	public ModelAndView materialRecieptsItemWise(HttpServletRequest request, HttpServletResponse response) 
	{
		
		ModelAndView model = new ModelAndView("masters/materialRecieptReport/itemWise");
		RestTemplate rest = new RestTemplate();
		try
		{
			 getRawMaterialDetail = rest.getForObject(Constants.url + "rawMaterial/getAllRawMaterialList", GetRawMaterialDetailList.class);
			model.addObject("rawlist",getRawMaterialDetail.getRawMaterialDetailsList());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		 

		 
		return model;

	}
	 
	@RequestMapping(value = "/materialRecieptsItemWiseReport", method = RequestMethod.GET)
	public @ResponseBody List<GetMaterialRecieptReportItemWise> materialRecieptsItemWiseReport(HttpServletRequest request, HttpServletResponse response) 
	{
		List<GetMaterialRecieptReportItemWise> materialRecieptsItemWiseReport = new ArrayList<GetMaterialRecieptReportItemWise>();
		
		 
		RestTemplate rest = new RestTemplate();
		try
		{
			String fromDate = request.getParameter("from_date");
			String todate = request.getParameter("to_date");
			String[] item = request.getParameterValues("item[]");
			System.out.println("fromDate"+fromDate);
			System.out.println("todate"+todate);
			System.out.println("supplier"+item.toString());
			
			StringBuilder sb = new StringBuilder();
			String items = null;
			
			 if(item[0].equals("-1"))
			 {
				 System.out.println("in if");
				 for (int i = 0; i < getRawMaterialDetail.getRawMaterialDetailsList().size(); i++) {
						sb = sb.append(getRawMaterialDetail.getRawMaterialDetailsList().get(i).getRmId() + ",");

					} 
				 items = sb.toString();
				 items = items.substring(0, items.length() - 1);
				System.out.println("items id list is" + items.toString());
				 
			 }
			 else
			 {
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
			materialRecieptsItemWiseReport = rest.postForObject(Constants.url + "/materialRecieptItemWiseReport",map, List.class);
		 System.out.println("materialRecieptBillWiseReport"+materialRecieptsItemWiseReport.toString());
		 ItemWisepdf=materialRecieptsItemWiseReport;
		 
		}catch(Exception e)
		{
			e.printStackTrace();
		} 
		return materialRecieptsItemWiseReport;

	}
	
	@RequestMapping(value = "/itemWisePdf", method = RequestMethod.GET)
	public ModelAndView itemWisePdf(HttpServletRequest request, HttpServletResponse response) 
	{
		
		ModelAndView model = new ModelAndView("masters/materialRecieptReport/pdf/itemWisePdf");
		try
		{
			
			/*RestTemplate rest = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			map.add("toDate", DateConvertor.convertToYMD(todate));
			map.add("item", items);
			List<GetMaterialRecieptReportItemWise> materialRecieptsItemWiseReport = rest.postForObject(Constants.url + "/materialRecieptItemWiseReport",map, List.class);
			System.out.println("ItemWisepdf"+materialRecieptsItemWiseReport.toString());*/
			model.addObject("staticlist",ItemWisepdf);
			System.out.println("ItemWisepdf"+ItemWisepdf);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		  return model;

	}
	
	static List<GetMaterialRecieptReportHsnCodeWise>  HsndCodeReportpdf;
	@RequestMapping(value = "/materialRecieptsHsndCodeWise", method = RequestMethod.GET)
	public ModelAndView materialRecieptsHsndCodeWise(HttpServletRequest request, HttpServletResponse response) 
	{
		
		ModelAndView model = new ModelAndView("masters/materialRecieptReport/hsndCodeWise");
		RestTemplate rest = new RestTemplate();
		try
		{
			supplierDetailsList = rest.getForObject(Constants.url + "/getAllSupplierlist", Supplist.class);
			model.addObject("supplierList",supplierDetailsList.getSupplierDetailslist());
		}catch(Exception e)
		{
			e.printStackTrace();
		} 
		return model;

	}
	
	@RequestMapping(value = "/materialRecieptHsndCodeReport", method = RequestMethod.GET)
	public @ResponseBody List<GetMaterialRecieptReportHsnCodeWise> materialRecieptHsndCodeReport(HttpServletRequest request, HttpServletResponse response) 
	{
		
		List<GetMaterialRecieptReportHsnCodeWise> materialRecieptHsndCodeReport = new ArrayList<GetMaterialRecieptReportHsnCodeWise>();
		
		 
		RestTemplate rest = new RestTemplate();
		try
		{
			String fromDate = request.getParameter("from_date");
			String todate = request.getParameter("to_date");
			String[] supplier = request.getParameterValues("suppliers[]");
			System.out.println("fromDate"+fromDate);
			System.out.println("todate"+todate);
			System.out.println("supplier"+supplier.toString());
			
			StringBuilder sb = new StringBuilder();
			String suppliers = null;
			
			 if(supplier[0].equals("-1"))
			 {
				 System.out.println("in if");
				 for (int i = 0; i < supplierDetailsList.getSupplierDetailslist().size(); i++) {
						sb = sb.append(supplierDetailsList.getSupplierDetailslist().get(i).getSuppId() + ",");

					}

					suppliers = sb.toString();
					suppliers = suppliers.substring(0, suppliers.length() - 1);
					System.out.println("suppliers id list is" + suppliers.toString());
				 
			 }
			 else
			 {
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
			materialRecieptHsndCodeReport = rest.postForObject(Constants.url + "/materialRecieptHsnCodeWiseReport",map, List.class);
			HsndCodeReportpdf=materialRecieptHsndCodeReport;
		 System.out.println("materialRecieptHsndCodeReport"+materialRecieptHsndCodeReport.toString());
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		} 
		return materialRecieptHsndCodeReport;

	}
	@RequestMapping(value = "/HsnCodeWisePdf", method = RequestMethod.GET)
	public ModelAndView HsnCodeWisePdf(HttpServletRequest request, HttpServletResponse response) 
	{
		
		ModelAndView model = new ModelAndView("masters/materialRecieptReport/pdf/HsnCodeWisePdf");
		try
		{  
			model.addObject("staticlist",HsndCodeReportpdf);
			System.out.println("HsndCodeReportpdf"+HsndCodeReportpdf);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		  return model;

	}
	
	
	static List<GetMaterialRecieptReportMonthWise> monthwisepdf;
	@RequestMapping(value = "/materialRecieptsMonthWise", method = RequestMethod.GET)
	public ModelAndView materialRecieptsMonthWise(HttpServletRequest request, HttpServletResponse response) 
	{
		
		ModelAndView model = new ModelAndView("masters/materialRecieptReport/monthWise");
		RestTemplate rest = new RestTemplate();
		try
		{
			supplierDetailsList = rest.getForObject(Constants.url + "/getAllSupplierlist", Supplist.class);
			model.addObject("supplierList",supplierDetailsList.getSupplierDetailslist());
		}catch(Exception e)
		{
			e.printStackTrace();
		} 
		return model;

	}
	
	@RequestMapping(value = "/materialRecieptMonthReport", method = RequestMethod.GET)
	public @ResponseBody List<GetMaterialRecieptReportMonthWise> materialRecieptMonthReport(HttpServletRequest request, HttpServletResponse response) 
	{
		
		List<GetMaterialRecieptReportMonthWise> materialRecieptMonthReport = new ArrayList<GetMaterialRecieptReportMonthWise>();
		
		 
		RestTemplate rest = new RestTemplate();
		try
		{
			String fromDate = request.getParameter("from_date");
			String todate = request.getParameter("to_date");
			String[] supplier = request.getParameterValues("suppliers[]");
			System.out.println("fromDate"+fromDate);
			System.out.println("todate"+todate);
			System.out.println("supplier"+supplier.toString());
			
			StringBuilder sb = new StringBuilder();
			String suppliers = null;
			
			 if(supplier[0].equals("-1"))
			 {
				 System.out.println("in if");
				 for (int i = 0; i < supplierDetailsList.getSupplierDetailslist().size(); i++) {
						sb = sb.append(supplierDetailsList.getSupplierDetailslist().get(i).getSuppId() + ",");

					}

					suppliers = sb.toString();
					suppliers = suppliers.substring(0, suppliers.length() - 1);
					System.out.println("suppliers id list is" + suppliers.toString());
				 
			 }
			 else
			 {
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
			materialRecieptMonthReport = rest.postForObject(Constants.url + "/materialRecieptMonthWiseReport",map, List.class);
			monthwisepdf=materialRecieptMonthReport;
		 System.out.println("materialRecieptMonthReport"+materialRecieptMonthReport.toString());
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		} 
		return materialRecieptMonthReport;

	}
	
	@RequestMapping(value = "/monthWisePdf", method = RequestMethod.GET)
	public ModelAndView monthWisePdf(HttpServletRequest request, HttpServletResponse response) 
	{
		
		ModelAndView model = new ModelAndView("masters/materialRecieptReport/pdf/monthWisePdf");
		try
		{  
			model.addObject("staticlist",monthwisepdf);
			System.out.println("monthwisepdf"+monthwisepdf);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		  return model;

	}
	
	private Dimension format = PD4Constants.A4;
	private boolean landscapeValue = false;
	private int topValue = 0;
	private int leftValue = 0;
	private int rightValue = 0;
	private int bottomValue = 0;
	private String unitsValue = "m";
	private String proxyHost = "";
	private int proxyPort = 0;

	private int userSpaceWidth = 750;
	private static int BUFFER_SIZE = 1024;

	@RequestMapping(value = "/materialRec", method = RequestMethod.GET)
	public void showPDF(HttpServletRequest request, HttpServletResponse response) {

		String url = request.getParameter("url");
		System.out.println("URL "+url);
		// http://monginis.ap-south-1.elasticbeanstalk.com
		File f = new File("C:/pdf/ordermemo221.pdf");
System.out.println("I am here "+f.toString());
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
		String filePath = "C:/pdf/ordermemo221.pdf";

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
	}

}
