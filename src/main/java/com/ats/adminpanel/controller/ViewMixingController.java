package com.ats.adminpanel.controller;



import java.awt.Desktop;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.ats.adminpanel.commons.DateConvertor;
import com.ats.adminpanel.model.Info;
import com.ats.adminpanel.model.item.FrItemStockConfigureList;
import com.ats.adminpanel.model.productionplan.BillOfMaterialDetailed;
import com.ats.adminpanel.model.productionplan.GetMixingList;
import com.ats.adminpanel.model.productionplan.MixingDetailed;
import com.ats.adminpanel.model.productionplan.MixingHeader;
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
public class ViewMixingController {
	
	
	
	
	public List<MixingHeader> mixingHeaderList = new ArrayList<MixingHeader>();
	public List<MixingHeader> getMixingListall = new ArrayList<MixingHeader>();
	public List<MixingDetailed> mixwithdetaild = new ArrayList<MixingDetailed>();
	
	public MixingHeader mixingHeader=new MixingHeader();
	 
	
	@RequestMapping(value = "/getMixingList", method = RequestMethod.GET)
	public ModelAndView getMixingList(HttpServletRequest request, HttpServletResponse response) {
		Constants.mainAct =6;
		Constants.subAct=42;
		
		ModelAndView model = new ModelAndView("productionPlan/getMixinglist");//

		
		
		RestTemplate rest = new RestTemplate();
		
			GetMixingList getMixingList= rest.getForObject(Constants.url + "/gettodaysMixingRequest", GetMixingList.class);
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			String settingKey1 = new String();
			settingKey1 = "MIX";
			map.add("settingKeyList", settingKey1);
			RestTemplate restTemplate = new RestTemplate();
			FrItemStockConfigureList settingList1 = restTemplate.postForObject(Constants.url + "getDeptSettingValue", map,
					FrItemStockConfigureList.class);
			 
			System.out.println("flag "+settingList1.getFrItemStockConfigure().get(0).getSettingValue());
		
		
		model.addObject("todaysmixrequest",getMixingList.getMixingHeaderList());
		model.addObject("flag",settingList1.getFrItemStockConfigure().get(0).getSettingValue());
		return model;

	}
	
	@RequestMapping(value = "/getMixingListByProduction", method = RequestMethod.GET)
	public ModelAndView getMixingListByProduction(HttpServletRequest request, HttpServletResponse response) {
		/*Constants.mainAct = 17;
		Constants.subAct=184;*/
		
		ModelAndView model = new ModelAndView("productionPlan/getMixinglist");//

		
		
		RestTemplate rest = new RestTemplate();
		
			GetMixingList getMixingList= rest.getForObject(Constants.url + "/gettodaysMixingRequest", GetMixingList.class);
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			String settingKey1 = new String();
			settingKey1 = "PROD";
			map.add("settingKeyList", settingKey1);
			RestTemplate restTemplate = new RestTemplate();
			FrItemStockConfigureList settingList1 = restTemplate.postForObject(Constants.url + "getDeptSettingValue", map,
					FrItemStockConfigureList.class);
			 
			System.out.println("flag "+settingList1.getFrItemStockConfigure().get(0).getSettingValue());
		
		
		model.addObject("todaysmixrequest",getMixingList.getMixingHeaderList());
		model.addObject("flag",settingList1.getFrItemStockConfigure().get(0).getSettingValue());
		return model;

	}
	
	@RequestMapping(value = "/getMixingListWithDate", method = RequestMethod.GET)
	@ResponseBody
	public List<MixingHeader> getMixingListWithDate(HttpServletRequest request, HttpServletResponse response) {
		/*Constants.mainAct = 17;
		Constants.subAct=184;*/
		
		String frmdate=request.getParameter("from_date");
		String todate=request.getParameter("to_date");
		
		System.out.println("in getMixingListWithDate   "+frmdate+todate);
		String frdate=DateConvertor.convertToYMD(frmdate);
		String tdate=DateConvertor.convertToYMD(todate);
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("frmdate",frdate);
		map.add("todate",tdate);
		System.out.println("in getMixingListWithDate   "+frdate+tdate);
		

		RestTemplate rest = new RestTemplate();
		try
		{
			GetMixingList getMixingList= rest.postForObject(Constants.url + "/getMixingHeaderList",map, GetMixingList.class);
			 
			mixingHeaderList = new ArrayList<MixingHeader>();
			System.out.println("getMixingList"+getMixingList.getMixingHeaderList().toString());
			for(int i=0;i<getMixingList.getMixingHeaderList().size();i++)
			{
				MixingHeader mixingHeader=getMixingList.getMixingHeaderList().get(i);
				int Status=mixingHeader.getStatus();
				if(Status==0)
				{
					mixingHeaderList.add(getMixingList.getMixingHeaderList().get(i));
				}
			}
			System.out.println("mixingHeaderList"+mixingHeaderList.toString());
			
		}catch(Exception e)
		{
			System.out.println("error in controller "+e.getMessage());
		}
		return mixingHeaderList;
	

	}
	
	
	@RequestMapping(value = "/getMixingAllListWithDate", method = RequestMethod.GET)
	@ResponseBody
	public List<MixingHeader> getMixingAllListWithDate(HttpServletRequest request, HttpServletResponse response) {
		/*Constants.mainAct = 17;
		Constants.subAct=184;*/
		String frmdate=request.getParameter("from_date");
		String todate=request.getParameter("to_date");
		
		System.out.println("in getMixingListWithDate   "+frmdate+todate);
		String frdate=DateConvertor.convertToYMD(frmdate);
		String tdate=DateConvertor.convertToYMD(todate);
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("frmdate",frdate);
		map.add("todate",tdate);
		System.out.println("in getMixingListWithDate   "+frdate+tdate);
		RestTemplate rest = new RestTemplate();
		GetMixingList getMixingList= rest.postForObject(Constants.url + "/getMixingHeaderList",map, GetMixingList.class);
		getMixingListall  = getMixingList.getMixingHeaderList();
		return getMixingListall;
	

	}
	
	@RequestMapping(value = "/viewDetailMixRequest", method = RequestMethod.GET)
	public ModelAndView viewDetailMixRequest(HttpServletRequest request, HttpServletResponse response) {
		/*Constants.mainAct = 17;
		Constants.subAct=184;*/
		ModelAndView model = new ModelAndView("productionPlan/showmixindetailed");
		
		
		//String mixId=request.getParameter("mixId");
		int deptId=Integer.parseInt(request.getParameter("deptId"));
		int mixId=Integer.parseInt(request.getParameter("mixId"));
		System.out.println(mixId);
		System.out.println(deptId);
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("mixId",mixId);
		RestTemplate rest = new RestTemplate();
		 mixingHeader=rest.postForObject(Constants.url + "/getDetailedwithMixId",map, MixingHeader.class);
		 mixwithdetaild =mixingHeader.getMixingDetailed();
		
		 Date mixdate = mixingHeader.getMixDate();
		 
		 
		 
		 SimpleDateFormat dtFormat=new SimpleDateFormat("dd-MM-yyyy");
			
		 String date=dtFormat.format(mixdate);
				
		 System.out.println("date" +date);
			model.addObject("date",date);

			model.addObject("mixheader",mixingHeader);
		model.addObject("mixwithdetaild", mixwithdetaild);
		model.addObject("deptId", deptId);
		 
		
		return model;
	}
	
	
	@RequestMapping(value = "/showMixReqPdf", method = RequestMethod.GET)
	public void  showProdByOrderPdf(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException {
		  BufferedOutputStream outStream = null;
		System.out.println("Inside show Prod BOM Pdf ");
		Document doc=new Document();
		
		List<MixingDetailed> mixDetailList = mixwithdetaild;
		
		mixDetailList = mixwithdetaild;
		Document document = new Document(PageSize.A4);
		//  ByteArrayOutputStream out = new ByteArrayOutputStream();
		 
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();

		System.out.println("time in Gen Bill PDF ==" + dateFormat.format(cal.getTime()));
		String timeStamp=dateFormat.format(cal.getTime());
		String FILE_PATH=Constants.REPORT_SAVE;
		File file=new File(FILE_PATH);
		
		PdfWriter writer = null;
		
		
		 FileOutputStream out=new FileOutputStream(FILE_PATH);
		   try {
			    writer=PdfWriter.getInstance(document,out);
		} catch (DocumentException e) {
			
			e.printStackTrace();
		}
		
		 PdfPTable table = new PdfPTable(5);
		 try {
		 System.out.println("Inside PDF Table try");
		 table.setWidthPercentage(100);
	     table.setWidths(new float[]{0.9f, 1.8f,1.4f,1.4f,1.4f});
	     Font headFont = new Font(FontFamily.HELVETICA, 8, Font.ITALIC, BaseColor.BLACK);
	     Font headFont1 = new Font(FontFamily.HELVETICA, 8, Font.BOLD, BaseColor.BLACK);
	     Font f=new Font(FontFamily.TIMES_ROMAN,12.0f,Font.UNDERLINE,BaseColor.BLUE);
	     
	     PdfPCell hcell;
	     hcell = new PdfPCell(new Phrase("Sr.No.", headFont1));
	     hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	     table.addCell(hcell);

	     hcell = new PdfPCell(new Phrase("SF Name", headFont1));
	     hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	     table.addCell(hcell);
	    
	     
	     hcell = new PdfPCell(new Phrase("Ori Quantity", headFont1));
	     hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	     table.addCell(hcell);
	     
	     
	    
	     
	     hcell = new PdfPCell(new Phrase("Add Weight", headFont1));
	     hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	     table.addCell(hcell);
	     
	    
	     hcell = new PdfPCell(new Phrase("Order Quantity", headFont1));
	     hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	     table.addCell(hcell);
	 
	     int index=0;
	     for (MixingDetailed mixDetail : mixDetailList) {
	       index++;
	         PdfPCell cell;

	        cell = new PdfPCell(new Phrase(String.valueOf(index),headFont));
	         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	         table.addCell(cell);

	        
	         cell = new PdfPCell(new Phrase(mixDetail.getSfName(),headFont));
	         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	         cell.setPaddingRight(4);
	         table.addCell(cell);
	         
	         
	         cell = new PdfPCell(new Phrase(String.valueOf(mixDetail.getOriginalQty()),headFont));
	         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	         cell.setPaddingRight(4);
	         table.addCell(cell);
	         
	         
	         cell = new PdfPCell(new Phrase(String.valueOf(mixDetail.getExInt2()),headFont));
	         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	         cell.setPaddingRight(4);
	         table.addCell(cell);
	         
	         
	         cell = new PdfPCell(new Phrase(String.valueOf(mixDetail.getReceivedQty()),headFont));
	         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	         cell.setPaddingRight(4);
	         table.addCell(cell);
	         
	       
	        	         
	         //FooterTable footerEvent = new FooterTable(table);
	        // writer.setPageEvent(footerEvent);
	     }

	     document.open();
	     Paragraph company = new Paragraph("Galdhar Foods Pvt.Ltd\n" + 
					"Factory Add: A-32 Shendra, MIDC, Auraangabad-4331667" + 
					"Phone:0240-2466217, Email: aurangabad@monginis.net", f);
	     company.setAlignment(Element.ALIGN_CENTER);
	     document.add(company);
	     document.add(new Paragraph(" "));

	     Paragraph heading = new Paragraph("Report-Mixing Request");
	     heading.setAlignment(Element.ALIGN_CENTER);
	     document.add(heading);
	     DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
			String reportDate = DF.format(new Date());
			
			document.add(new Paragraph(""+ reportDate));
			document.add(new Paragraph("\n"));
	     //document.add(new Paragraph(" "));
	     document.add(table);
	 	 int totalPages=writer.getPageNumber();
	 	/*com.ats.adminpanel.model.itextpdf.Header event; // = new com.ats.adminpanel.model.itextpdf.Header();
	 	for(int i=1;i<totalPages;i++) {
	 	 event = new com.ats.adminpanel.model.itextpdf.Header();
	 	event.setHeader(new Phrase(String.format("page %s", i)));
	 	
	 	writer.setPageEvent(event);
	 	}
	 	 FooterTable footerEvent = new FooterTable(table);
	 	 */
	 	 
	 //	 document.add(new Paragraph(""+document.setPageCount(document.getPageNumber()));
	     
	 	 System.out.println("Page no "+totalPages);
	     
	    // document.addHeader("Page" ,String.valueOf(totalPages));
	    // writer.setPageEvent((PdfPageEvent) new Phrase());
	    
	     document.close();
	     
	     
	   //Atul Sir code to open a Pdf File 
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
					System.out.println("Excep in Opening a Pdf File for Mixing");
					e.printStackTrace();
				}
			}
	     
	    /* Desktop d=Desktop.getDesktop();
	     
	     if(file.exists()) {
	    	 try {
				d.open(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     }*/
	     
	 } catch (DocumentException ex) {
	 
		 System.out.println("Pdf Generation Error: Prod From Orders"+ex.getMessage());
		 
		 ex.printStackTrace();
	   
	 }

		ModelAndView model = new ModelAndView("production/pdf/productionPdf");
		//model.addObject("prodFromOrderReport",updateStockDetailList);

	
	}
	
	
	@RequestMapping(value = "/updateProdctionQty", method = RequestMethod.POST)
	public String updateProdctionQty(HttpServletRequest request, HttpServletResponse response) {
		/*Constants.mainAct = 17;
		Constants.subAct=184;*/
		try
		{
			for(int i=0;i<mixingHeader.getMixingDetailed().size();i++)
			{
				System.out.println(12);
				 
				 
					System.out.println(13);
					String production_Qty=request.getParameter("production_Qty"+mixingHeader.getMixingDetailed().get(i).getMixing_detailId());
					String rejected_Qty=request.getParameter("rejected_Qty"+mixingHeader.getMixingDetailed().get(i).getMixing_detailId());
					if(production_Qty!=null) {
						System.out.println("production_Qty Qty   :"+production_Qty);
						float productionQty=Float.parseFloat(production_Qty);
						mixingHeader.getMixingDetailed().get(i).setProductionQty(productionQty);
						System.out.println("productionQty  :"+productionQty);
					}
					else
					{
						mixingHeader.getMixingDetailed().get(i).setProductionQty(0);
					}
					
					if(rejected_Qty!=null) {
						System.out.println("rejected_Qty Qty   :"+rejected_Qty);
						float rejectedQty=Float.parseFloat(rejected_Qty);
						mixingHeader.getMixingDetailed().get(i).setRejectedQty(rejectedQty);
						System.out.println("productionQty  :"+rejectedQty);
					}
					else
					{
						mixingHeader.getMixingDetailed().get(i).setRejectedQty(0);
					}
					System.out.println(2);
				 
			}
			mixingHeader.setStatus(2);
			mixingHeader.setMixingDetailed(mixingHeader.getMixingDetailed());
			
			System.out.println(mixingHeader.toString());
			
			RestTemplate rest = new RestTemplate();
			 mixingHeader=rest.postForObject(Constants.url + "/insertMixingHeaderndDetailed",mixingHeader, MixingHeader.class);
			 
			 if(mixingHeader!=null)
			 {
				 int mixId = mixingHeader.getMixId();
				 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,Object>();
				 map.add("prodId", mixId);
				 map.add("isProduction", 0);
				 System.out.println("map"+map);
				 
				 Info info= rest.postForObject(Constants.url + "/updateStatusWhileCompletProd",map, Info.class);
				 System.out.println("info"+info);
			 }
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
		return "redirect:/getMixingList";
	}
	
	
	

}
