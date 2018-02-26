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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import org.apache.tomcat.util.bcel.classfile.Constant;
import org.springframework.context.annotation.Scope;
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
import com.ats.adminpanel.model.Info;
import com.ats.adminpanel.model.item.FrItemStockConfigureList;
import com.ats.adminpanel.model.login.UserResponse;
import com.ats.adminpanel.model.production.GetProdPlanDetail;
import com.ats.adminpanel.model.production.mixing.temp.GetSFMixingForBom;
import com.ats.adminpanel.model.production.mixing.temp.GetSFMixingForBomList;
import com.ats.adminpanel.model.production.mixing.temp.GetSFPlanDetailForMixing;
import com.ats.adminpanel.model.production.mixing.temp.GetSFPlanDetailForMixingList;
import com.ats.adminpanel.model.production.mixing.temp.GetTempMixItemDetail;
import com.ats.adminpanel.model.production.mixing.temp.GetTempMixItemDetailList;
import com.ats.adminpanel.model.production.mixing.temp.TempMixing;
import com.ats.adminpanel.model.production.mixing.temp.TempMixingList;
import com.ats.adminpanel.model.productionplan.BillOfMaterialDetailed;
import com.ats.adminpanel.model.productionplan.BillOfMaterialHeader;
import com.ats.adminpanel.model.productionplan.GetBillOfMaterialList;
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

public class BomController {

	GetSFPlanDetailForMixingList getSFPlanDetailForBomList;

	List<GetSFPlanDetailForMixing> sfPlanDetailForBom = new ArrayList<>();

	GetSFMixingForBomList sFMixingForBomList;
	
	List<GetSFMixingForBom> sFMixingForBom = new ArrayList<>();

	String prodOrMixDate;
	int globalHeaderId;
int globalIsPlan;
	@RequestMapping(value = "/showBom/{prodHeaderId}/{isMix}/{date}/{isPlan}/{catId}", method = RequestMethod.GET)
	public ModelAndView showBom2(@PathVariable int prodHeaderId, @PathVariable int isMix, @PathVariable String date,
			@PathVariable int isPlan,@PathVariable int catId,
			HttpServletRequest request, HttpServletResponse response) throws ParseException {

		prodOrMixDate = date;

		globalHeaderId = prodHeaderId;

		globalIsPlan=isPlan;
		ModelAndView mav = new ModelAndView("production/addBom");
		mav.addObject("catId", catId);
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			RestTemplate restTemplate = new RestTemplate();
			if (isMix == 1) {
System.out.println("It is Production BOM ");
				map.add("headerId", prodHeaderId);

				getSFPlanDetailForBomList = restTemplate.postForObject(Constants.url + "getSfPlanDetailForBom", map,
						GetSFPlanDetailForMixingList.class);

				sfPlanDetailForBom = getSFPlanDetailForBomList.getSfPlanDetailForMixing();

				System.out.println("sf Plan Detail For Bom  " + sfPlanDetailForBom.toString());
				/*//ceil fun 6 feb
				for(int i=0;i<sfPlanDetailForBom.size();i++) {
					
					sfPlanDetailForBom.get(i).setTotal((int)Math.ceil(sfPlanDetailForBom.get(i).getTotal()));

				}
				*/
				mav.addObject("planDetailForBom", sfPlanDetailForBom);
				
			} else if (isMix == 0) {
				
				System.out.println("inside Else: It is Mixing BOM ");

				map = new LinkedMultiValueMap<String, Object>();

				map.add("mixingId", prodHeaderId);

				sFMixingForBomList = restTemplate.postForObject(Constants.url + "getSFMixingForBom", map,
						GetSFMixingForBomList.class);

				sFMixingForBom = sFMixingForBomList.getsFMixingForBom();

				System.out.println("sf Mixing Detail For Bom  " + sFMixingForBom.toString());
				/*//ceil fun 6 feb
				for(int i=0;i<sFMixingForBom.size();i++) {
				sFMixingForBom.get(i).setTotal((int)Math.ceil(sFMixingForBom.get(i).getTotal()));
				}
*/
				mav.addObject("planDetailForBom", sFMixingForBom);
			}

			mav.addObject("isMix", isMix);

		} catch (Exception e) {
			e.printStackTrace();

			System.out.println("Error In showBom " + e.getMessage());
		}
		
	return mav;

	}

	@RequestMapping(value = "/insertBom", method = RequestMethod.GET)
	public String insertBom(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mav = new ModelAndView("production/addBom");

		HttpSession session=request.getSession();
		UserResponse userResponse =(UserResponse) session.getAttribute("UserDetail");
		
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		 int userId=userResponse.getUser().getId();
		
		String settingKey = new String();
		settingKey = "BMS";
		map.add("settingKeyList", settingKey);
		FrItemStockConfigureList settingList = restTemplate.postForObject(Constants.url + "getDeptSettingValue", map,
				FrItemStockConfigureList.class);
		
		
		
		System.out.println("new Field Dept Id = "+userResponse.getUser().getDeptId());
		
		String isMix = request.getParameter("isMix");
		System.out.println("isMix " + isMix);
		int isMixing = Integer.parseInt(isMix);
		System.out.println("inside insert Bom ");
		int catId = Integer.parseInt(request.getParameter("catId"));
		Date prodOrMixDate1 = null;

		SimpleDateFormat dtFormat = new SimpleDateFormat("dd-MM-yyyy");

		try {
			prodOrMixDate1 = dtFormat.parse(prodOrMixDate);
		} catch (ParseException e1) {

			System.out.println("Exce In Date conversion");
			e1.printStackTrace();
		}
		String ret="";

		try {
			
			
			
			int toDeptId=settingList.getFrItemStockConfigure().get(0).getSettingValue();
			String toDeptName=settingList.getFrItemStockConfigure().get(0).getSettingKey();
			
			Date date = new Date();

			BillOfMaterialHeader billOfMaterialHeader = new BillOfMaterialHeader();

			billOfMaterialHeader.setApprovedDate(date);
			billOfMaterialHeader.setApprovedUserId(0);
			billOfMaterialHeader.setDelStatus(0);
			 
			billOfMaterialHeader.setProductionDate(prodOrMixDate1);
			billOfMaterialHeader.setProductionId(globalHeaderId);
			billOfMaterialHeader.setReqDate(date);
			billOfMaterialHeader.setSenderUserid(userId);
			billOfMaterialHeader.setStatus(0);
			billOfMaterialHeader.setToDeptId(toDeptId);
			billOfMaterialHeader.setToDeptName(toDeptName);
			billOfMaterialHeader.setExInt1(catId);//Category
			billOfMaterialHeader.setRejApproveDate(date);
			billOfMaterialHeader.setRejApproveUserId(0);
			billOfMaterialHeader.setRejDate(date);
			billOfMaterialHeader.setRejUserId(0);
			
			billOfMaterialHeader.setIsManual(0);

			List<BillOfMaterialDetailed> bomDetailList = new ArrayList<BillOfMaterialDetailed>();
			BillOfMaterialDetailed bomDetail = null;
			if (isMixing == 1) {
				
				map = new LinkedMultiValueMap<String, Object>();
				String settingKey1 = new String();
				settingKey1 = "PROD";
				map.add("settingKeyList", settingKey1);
				FrItemStockConfigureList settingList1 = restTemplate.postForObject(Constants.url + "getDeptSettingValue", map,
						FrItemStockConfigureList.class);
				int fromDeptId=settingList1.getFrItemStockConfigure().get(0).getSettingValue();
				String fromDeptName=settingList1.getFrItemStockConfigure().get(0).getSettingKey();
				
				billOfMaterialHeader.setIsProduction(1);
				billOfMaterialHeader.setFromDeptId(fromDeptId);
				billOfMaterialHeader.setFromDeptName(fromDeptName);
				billOfMaterialHeader.setIsPlan(globalIsPlan);

				billOfMaterialHeader.setIsPlan(0);

				for (int i = 0; i < sfPlanDetailForBom.size(); i++) {

					String editQty = request.getParameter("editQty" + i);
					bomDetail = new BillOfMaterialDetailed();

					System.out.println("editQty " + editQty);

					bomDetail.setDelStatus(0);
					bomDetail.setRmId(sfPlanDetailForBom.get(i).getRmId());
					bomDetail.setRmIssueQty(0.0F);
					bomDetail.setUom(sfPlanDetailForBom.get(i).getUom());
					bomDetail.setRmType(sfPlanDetailForBom.get(i).getRmType());
					bomDetail.setRmReqQty(Float.parseFloat(editQty));
					bomDetail.setRmName(sfPlanDetailForBom.get(i).getRmName());
					
					bomDetail.setRejectedQty(0);
					bomDetail.setAutoRmReqQty(sfPlanDetailForBom.get(i).getTotal());
					
					bomDetail.setReturnQty(0);
					bomDetailList.add(bomDetail);

				}
				
				System.out.println("bom detail List " + bomDetailList.toString());
				billOfMaterialHeader.setBillOfMaterialDetailed(bomDetailList);

				System.out.println(" insert List " + billOfMaterialHeader.toString());
				int prodId=billOfMaterialHeader.getProductionId();
				Info info = restTemplate.postForObject(Constants.url + "saveBom", billOfMaterialHeader, Info.class);
				System.out.println(info);
				
				if(info.getError()==false)
				{
					map = new LinkedMultiValueMap<String, Object>();
					map.add("productionId", prodId);
					map.add("flag", 1);
					int updateisBom = restTemplate.postForObject(Constants.url + "updateisMixingandBom", map,
							Integer.class); 
					System.out.println("updateIsBom "+updateisBom);
				}
				

				 ret="redirect:/getBomListforProduction";
				
			}

			else {
				map = new LinkedMultiValueMap<String, Object>();
				String settingKey1 = new String();
				settingKey1 = "MIX";
				map.add("settingKeyList", settingKey1);
				FrItemStockConfigureList settingList1 = restTemplate.postForObject(Constants.url + "getDeptSettingValue", map,
						FrItemStockConfigureList.class);
				int fromDeptId=settingList1.getFrItemStockConfigure().get(0).getSettingValue();
				String fromDeptName=settingList1.getFrItemStockConfigure().get(0).getSettingKey();

				billOfMaterialHeader.setFromDeptId(fromDeptId);
				billOfMaterialHeader.setFromDeptName(fromDeptName);
				billOfMaterialHeader.setIsProduction(0);
				
				billOfMaterialHeader.setIsPlan(0);

				for (int i = 0; i < sFMixingForBom.size(); i++) {

					String editQty = request.getParameter("editQty" + i);
					bomDetail = new BillOfMaterialDetailed();

					System.out.println("editQty " + editQty);

					bomDetail.setDelStatus(0);
					bomDetail.setRmId(sFMixingForBom.get(i).getRmId());
					bomDetail.setRmIssueQty(1.0F);
					bomDetail.setUom(sFMixingForBom.get(i).getUom());
					bomDetail.setRmType(sFMixingForBom.get(i).getRmType());
					bomDetail.setAutoRmReqQty(sFMixingForBom.get(i).getTotal());
					bomDetail.setRmName(sFMixingForBom.get(i).getRmName());
					
					bomDetail.setRmReqQty(Float.parseFloat(editQty));

					bomDetail.setReturnQty(0);


					bomDetailList.add(bomDetail);

				}
				
				System.out.println("bom detail List " + bomDetailList.toString());
				billOfMaterialHeader.setBillOfMaterialDetailed(bomDetailList);

				System.out.println(" insert List " + billOfMaterialHeader.toString());
				int mixId=billOfMaterialHeader.getProductionId();
				Info info = restTemplate.postForObject(Constants.url + "saveBom", billOfMaterialHeader, Info.class);
				System.out.println(info);
				
				if(info.getError()==false)
				{
					map = new LinkedMultiValueMap<String, Object>(); 
					map.add("mixId", mixId); 
					int updateisBom = restTemplate.postForObject(Constants.url + "updateisBomInMixing", map,
							Integer.class);
					System.out.println("updateIsBom in Mixing "+updateisBom);
				}

				
				 ret="redirect:/getBomListforMixing";
			}

			


		} catch (Exception e) {
			e.printStackTrace();

		}
		
	return ret;
	
	}
	
	//from akshay View BOM-------------
	
	private List<BillOfMaterialHeader> getBOMListall;
	public List<BillOfMaterialHeader> getbomList = new ArrayList<BillOfMaterialHeader>();
	BillOfMaterialHeader billOfMaterialHeader = new BillOfMaterialHeader();
	List<BillOfMaterialDetailed> bomwithdetaild = new ArrayList<BillOfMaterialDetailed>();
	FrItemStockConfigureList PROD = new FrItemStockConfigureList();
	FrItemStockConfigureList MIX = new FrItemStockConfigureList();
	
	@RequestMapping(value = "/getBomList", method = RequestMethod.GET)
	public ModelAndView getBomList(HttpServletRequest request, HttpServletResponse response) {
		Constants.mainAct =8;
		Constants.subAct=44;
		
		ModelAndView model = new ModelAndView("productionPlan/getbomlist");//
		getbomList = new ArrayList<BillOfMaterialHeader>();
		
		
		RestTemplate rest = new RestTemplate();
		try
		{
			GetBillOfMaterialList getBillOfMaterialList= rest.getForObject(Constants.url + "/getallBOMHeaderList", GetBillOfMaterialList.class);
			
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			System.out.println("date"+df.format(date));
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			String key = new String();
			key = "PROD";
			map.add("settingKeyList", key);
			 PROD = rest.postForObject(Constants.url + "getDeptSettingValue", map,
					FrItemStockConfigureList.class);
			
			map = new LinkedMultiValueMap<String, Object>();
			String key1 = new String();
			key1 = "MIX";
			map.add("settingKeyList", key1);
			 MIX = rest.postForObject(Constants.url + "getDeptSettingValue", map,
					FrItemStockConfigureList.class);
			
			
			System.out.println("getbomList"+getBillOfMaterialList.getBillOfMaterialHeader().toString());
			for(int i=0;i<getBillOfMaterialList.getBillOfMaterialHeader().size();i++)
			{
				 
				if(getBillOfMaterialList.getBillOfMaterialHeader().get(i).getStatus()==0 || df.format(date).equals(df.format(getBillOfMaterialList.getBillOfMaterialHeader().get(i).getReqDate())))
				{
					if(getBillOfMaterialList.getBillOfMaterialHeader().get(i).getFromDeptId()==PROD.getFrItemStockConfigure().get(0).getSettingValue() || getBillOfMaterialList.getBillOfMaterialHeader().get(i).getFromDeptId()==MIX.getFrItemStockConfigure().get(0).getSettingValue()) 
						getbomList.add(getBillOfMaterialList.getBillOfMaterialHeader().get(i)); 
				}
			}
			System.out.println("bomHeaderList"+getBillOfMaterialList.getBillOfMaterialHeader().toString());
			
		}catch(Exception e)
		{
			System.out.println("error in controller "+e.getMessage());
		}
		model.addObject("getbomList",getbomList) ;
		return model;

	}
	
	
	
	
	@RequestMapping(value = "/getBomAllListWithDate", method = RequestMethod.GET)
	@ResponseBody
	public List<BillOfMaterialHeader> getBomAllListWithDate(HttpServletRequest request, HttpServletResponse response) {
		/*Constants.mainAct = 17;
		Constants.subAct=184;*/
		RestTemplate rest = new RestTemplate();
		String frmdate=request.getParameter("from_date");
		String todate=request.getParameter("to_date");
		
		System.out.println("in getMixingListWithDate   "+frmdate+todate);
		String frdate=DateConvertor.convertToYMD(frmdate);
		String tdate=DateConvertor.convertToYMD(todate);
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>(); 
		map = new LinkedMultiValueMap<String, Object>();
		map.add("frmdate",frdate);
		map.add("todate",tdate);
		map.add("bmsDeptId",PROD.getFrItemStockConfigure().get(0).getSettingValue());
		map.add("mixDeptId",MIX.getFrItemStockConfigure().get(0).getSettingValue());
		System.out.println("map " + map);
		
		GetBillOfMaterialList getBillOfMaterialList= rest.postForObject(Constants.url + "/getBOMHeaderList",map, GetBillOfMaterialList.class);
		getBOMListall  = getBillOfMaterialList.getBillOfMaterialHeader();
		System.out.println("getBOMListall"+getBOMListall.toString());
		return getBOMListall;
	

	}
	
	@RequestMapping(value = "/viewDetailBOMRequest", method = RequestMethod.GET)
	public ModelAndView viewDetailBOMRequest(HttpServletRequest request, HttpServletResponse response) {
		/*Constants.mainAct = 17;
		Constants.subAct=184;*/
		ModelAndView model = new ModelAndView("productionPlan/showbomdetailed");
		
		
		//String mixId=request.getParameter("mixId");
		int reqId=Integer.parseInt(request.getParameter("reqId"));
		System.out.println(reqId);
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("reqId",reqId);
		RestTemplate rest = new RestTemplate();
		billOfMaterialHeader=rest.postForObject(Constants.url + "/getDetailedwithreqId",map, BillOfMaterialHeader.class);
		bomwithdetaild =billOfMaterialHeader.getBillOfMaterialDetailed();
		
		model.addObject("billOfMaterialHeader",billOfMaterialHeader);
		model.addObject("bomwithdetaild", bomwithdetaild);
		
		return model;
	}
	
	
	@RequestMapping(value = "/showProdBOMPdf", method = RequestMethod.GET)
	public void  showProdByOrderPdf(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException {
		  BufferedOutputStream outStream = null;
		System.out.println("Inside show Prod BOM Pdf ");
		Document doc=new Document();
			
		
		List<BillOfMaterialDetailed> bomDetails = bomwithdetaild;
		
		bomDetails = bomwithdetaild;
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
		
		 PdfPTable table = new PdfPTable(3);
		 try {
		 System.out.println("Inside PDF Table try");
		 table.setWidthPercentage(100);
	     table.setWidths(new float[]{0.5f, 1.4f,0.5f});
	     Font headFont = new Font(FontFamily.HELVETICA, 8, Font.ITALIC, BaseColor.BLACK);
	     Font headFont1 = new Font(FontFamily.HELVETICA, 8, Font.BOLD, BaseColor.BLACK);
	     Font f=new Font(FontFamily.TIMES_ROMAN,12.0f,Font.UNDERLINE,BaseColor.BLUE);
	     
	     PdfPCell hcell;
	     hcell = new PdfPCell(new Phrase("Sr.No.", headFont1));
	     hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	     table.addCell(hcell);

	     hcell = new PdfPCell(new Phrase("Item Name", headFont1));
	     hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	     table.addCell(hcell);
	    
	     hcell = new PdfPCell(new Phrase("Order Quantity", headFont1));
	     hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	     table.addCell(hcell);
	 
	     int index=0;
	     for (BillOfMaterialDetailed bomDetail : bomDetails) {
	       index++;
	         PdfPCell cell;

	        cell = new PdfPCell(new Phrase(String.valueOf(index),headFont));
	         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	         table.addCell(cell);

	        
	         cell = new PdfPCell(new Phrase(bomDetail.getRmName(),headFont));
	         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	         cell.setPaddingRight(2);
	         table.addCell(cell);
	         
	         cell = new PdfPCell(new Phrase(String.valueOf(bomDetail.getRmIssueQty()),headFont));
	         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	         cell.setPaddingRight(2);
	         table.addCell(cell);
	        	         
	         //FooterTable footerEvent = new FooterTable(table);
	        // writer.setPageEvent(footerEvent);
	     }

	     document.open();
	     Paragraph company = new Paragraph("Galdhar Foods Pvt.Ltd\n" + 
					"Factory Add: A-32 Shendra, MIDC, Auraangabad-4331667" + 
					"Phone:0240-2466217, Email: aurangabad@monginis.net", f);	     company.setAlignment(Element.ALIGN_CENTER);
	     document.add(company);

	     Paragraph heading = new Paragraph("Report-Request to BMS");
	     heading.setAlignment(Element.ALIGN_CENTER);
	     document.add(heading);
	     DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
			String reportDate = DF.format(new Date());
			
			document.add(new Paragraph(""+ reportDate));
			document.add(new Paragraph("\n"));
	     document.add(new Paragraph(" "));
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
					System.out.println("Excep in Opening a Pdf File");
					e.printStackTrace();
				}
			}
	      
	     
	 } catch (DocumentException ex) {
	 
		 System.out.println("Pdf Generation Error: Prod From Orders"+ex.getMessage());
		 
		 ex.printStackTrace();
	   
	 }

		ModelAndView model = new ModelAndView("production/pdf/productionPdf");
		//model.addObject("prodFromOrderReport",updateStockDetailList);

	
	}
	
	
	@RequestMapping(value = "/approvedBom", method = RequestMethod.POST)
	public String approvedBom(HttpServletRequest request, HttpServletResponse response) {
		/*Constants.mainAct = 17;
		Constants.subAct=184;*/
		Date date= new Date();
		
		HttpSession session=request.getSession();
		UserResponse userResponse =(UserResponse) session.getAttribute("UserDetail");
		
		
		int userId=userResponse.getUser().getId();
		
		
		for(int i=0;i<billOfMaterialHeader.getBillOfMaterialDetailed().size();i++)
		{
			System.out.println(12);
			 
			 
				System.out.println(13);
				String issue_qty=request.getParameter("issue_qty"+billOfMaterialHeader.getBillOfMaterialDetailed().get(i).getReqDetailId());
				
				if(issue_qty!=null) {
					System.out.println("issue_qty Qty   :"+issue_qty);
					float issueqty= Float.parseFloat(issue_qty);
					billOfMaterialHeader.getBillOfMaterialDetailed().get(i).setRmIssueQty(issueqty);
					System.out.println("productionQty  :"+issueqty);
				}
				else
				{
					billOfMaterialHeader.getBillOfMaterialDetailed().get(i).setRmIssueQty(0);
				}
				System.out.println(2);
			 
		}
		billOfMaterialHeader.setStatus(1);
		billOfMaterialHeader.setApprovedUserId(userId);
		billOfMaterialHeader.setApprovedDate(date);
		
		System.out.println(billOfMaterialHeader.toString());
		
		RestTemplate rest = new RestTemplate();
		
		Info info = rest.postForObject(Constants.url + "saveBom", billOfMaterialHeader, Info.class);	
		System.out.println(info);
		
		return "redirect:/getBomList";
	}
	
	
	@RequestMapping(value = "/rejectiontoBms", method = RequestMethod.GET)
	public ModelAndView rejectiontoBms(HttpServletRequest request, HttpServletResponse response) {
		/*Constants.mainAct = 17;
		Constants.subAct=184;*/
		ModelAndView model = new ModelAndView("productionPlan/rejectforbom");
		
		
		
		System.out.println("in rejection form ");
		
		model.addObject("billOfMaterialHeader",billOfMaterialHeader);
		model.addObject("bomwithdetaild", bomwithdetaild);
		
		return model;
	}
	
	
	@RequestMapping(value = "/updateRejectedQty", method = RequestMethod.POST)
	public String updateRejectedQty(HttpServletRequest request, HttpServletResponse response) {
		/*Constants.mainAct = 17;
		Constants.subAct=184;*/
		Date date= new Date();
		HttpSession session=request.getSession();
		UserResponse userResponse =(UserResponse) session.getAttribute("UserDetail"); 
		
		int userId=userResponse.getUser().getId();
		
		for(int i=0;i<billOfMaterialHeader.getBillOfMaterialDetailed().size();i++)
		{
			 
				System.out.println(13);
				float reject_qty=Float.parseFloat(request.getParameter("rejectedQty"+billOfMaterialHeader.getBillOfMaterialDetailed().get(i).getReqDetailId()));
				float return_qty=Float.parseFloat(request.getParameter("returnQty"+billOfMaterialHeader.getBillOfMaterialDetailed().get(i).getReqDetailId()));
				System.out.println("reject_qty Qty   :"+reject_qty); 
				billOfMaterialHeader.getBillOfMaterialDetailed().get(i).setRejectedQty(reject_qty);   
				System.out.println("return_qty Qty   :"+return_qty); 
				billOfMaterialHeader.getBillOfMaterialDetailed().get(i).setReturnQty(return_qty);  
				System.out.println(2);  
				 
			 
		}
		billOfMaterialHeader.setStatus(2);
		billOfMaterialHeader.setRejDate(date);
		billOfMaterialHeader.setRejUserId(userId); 
		System.out.println(billOfMaterialHeader.toString());
		
		RestTemplate rest = new RestTemplate();
		
		Info info = rest.postForObject(Constants.url + "saveBom", billOfMaterialHeader, Info.class);	
		System.out.println(info);
		
		return "redirect:/getBomList";
	}
	
	
	
	
	@RequestMapping(value = "/approveRejected", method = RequestMethod.GET)
	public String approveRejected(HttpServletRequest request, HttpServletResponse response) {
		
		
		System.out.println("inside Approve Rejected ");
		Date date= new Date();
		HttpSession session=request.getSession();
		UserResponse userResponse =(UserResponse) session.getAttribute("UserDetail");
		
		int reqId=Integer.parseInt(request.getParameter("reqId"));
		System.out.println(reqId);
		
		int userId=userResponse.getUser().getId();
		billOfMaterialHeader.setRejApproveDate(date);
		billOfMaterialHeader.setRejApproveUserId(userId);
		
		billOfMaterialHeader.setStatus(3);//3 for Approve Rejected 
		List<BillOfMaterialDetailed> billOfMaterialDetailed = new ArrayList<BillOfMaterialDetailed>();
		billOfMaterialHeader.setBillOfMaterialDetailed(billOfMaterialDetailed);
		RestTemplate rest = new RestTemplate();
		
		Info info = rest.postForObject(Constants.url + "saveBom", billOfMaterialHeader, Info.class);	
		System.out.println(info.toString());
		
		return "redirect:/getBomList";
	}
	
	
	
	@RequestMapping(value = "/getBomListforMixing", method = RequestMethod.GET)
	public ModelAndView getBomListforMixing(HttpServletRequest request, HttpServletResponse response) {
	
		List<BillOfMaterialHeader> getbomListsorted = new ArrayList<BillOfMaterialHeader>();
		ModelAndView model = new ModelAndView("productionPlan/bomDepWise");
		Constants.mainAct =6;
		Constants.subAct =41;
		getbomList = new ArrayList<BillOfMaterialHeader>();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		FrItemStockConfigureList fromsettingvalue = new FrItemStockConfigureList();
		FrItemStockConfigureList tosettingvalue = new FrItemStockConfigureList();
		RestTemplate rest = new RestTemplate();
		 
		try
		{
			
			 map = new LinkedMultiValueMap<String, Object>();
			 String fromdep = new String(); 
			 fromdep = "MIX"; 
				map.add("settingKeyList", fromdep);
				 fromsettingvalue = rest.postForObject(Constants.url + "getDeptSettingValue", map,
						FrItemStockConfigureList.class);
			map = new LinkedMultiValueMap<String, Object>();
			String todep = new String(); 
			todep = "BMS"; 
			map.add("settingKeyList", todep);
			tosettingvalue = rest.postForObject(Constants.url + "getDeptSettingValue", map,
					FrItemStockConfigureList.class);
		 	
			map = new LinkedMultiValueMap<String, Object>();
			map.add("fromDept",fromsettingvalue.getFrItemStockConfigure().get(0).getSettingValue());         
			map.add("toDept",tosettingvalue.getFrItemStockConfigure().get(0).getSettingValue());           
			map.add("status","0,1,2,3,4");   
			
			System.out.println("map"+map);
			
			GetBillOfMaterialList getBillOfMaterialList= rest.postForObject(Constants.url + "/getBOMHeaderBmsAndStore",map, GetBillOfMaterialList.class);
			 
			
			System.out.println("getbomList"+getBillOfMaterialList.getBillOfMaterialHeader().toString());
			getbomList=getBillOfMaterialList.getBillOfMaterialHeader();
			System.out.println("bomHeaderList"+getBillOfMaterialList.getBillOfMaterialHeader().toString());
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			Date date = new Date();
			
			for(int i=0;i<getbomList.size();i++)
			{
				 if(df.format(date).equals(df.format(getbomList.get(i).getReqDate())) || getbomList.get(i).getStatus()==0)
				{
					getbomListsorted.add(getbomList.get(i));
				}
			}
			
			System.out.println(getbomListsorted);
			
		}catch(Exception e)
		{
			System.out.println("error in controller "+e.getMessage());
		}
		model.addObject("getbomList",getbomListsorted) ;
		model.addObject("toDept",tosettingvalue.getFrItemStockConfigure().get(0).getSettingValue()) ;
		model.addObject("fromDept",fromsettingvalue.getFrItemStockConfigure().get(0).getSettingValue()) ;
		
		
		return model;

	}
	
	@RequestMapping(value = "/getBomListforProduction", method = RequestMethod.GET)
	public ModelAndView getBomListforProduction(HttpServletRequest request, HttpServletResponse response) {
	
		List<BillOfMaterialHeader> getbomListsorted = new ArrayList<BillOfMaterialHeader>(); 
		ModelAndView model = new ModelAndView("productionPlan/bomDepWise");
		Constants.mainAct =4;
		Constants.subAct =39;
		getbomList = new ArrayList<BillOfMaterialHeader>();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		FrItemStockConfigureList fromsettingvalue = new FrItemStockConfigureList();
		FrItemStockConfigureList tosettingvalue = new FrItemStockConfigureList();
		RestTemplate rest = new RestTemplate();
		 
		try
		{
			
			 map = new LinkedMultiValueMap<String, Object>();
			 String fromdep = new String(); 
			 fromdep = "PROD"; 
				map.add("settingKeyList", fromdep);
				 fromsettingvalue = rest.postForObject(Constants.url + "getDeptSettingValue", map,
						FrItemStockConfigureList.class);
			map = new LinkedMultiValueMap<String, Object>();
			String todep = new String(); 
			todep = "BMS"; 
			map.add("settingKeyList", todep);
			tosettingvalue = rest.postForObject(Constants.url + "getDeptSettingValue", map,
					FrItemStockConfigureList.class);
		 	
			map = new LinkedMultiValueMap<String, Object>();
			map.add("fromDept",fromsettingvalue.getFrItemStockConfigure().get(0).getSettingValue());         
			map.add("toDept",tosettingvalue.getFrItemStockConfigure().get(0).getSettingValue());           
			map.add("status","0,1,2,3,4");   
			
			System.out.println("map"+map);
			
			GetBillOfMaterialList getBillOfMaterialList= rest.postForObject(Constants.url + "/getBOMHeaderBmsAndStore",map, GetBillOfMaterialList.class);
			 
			
			System.out.println("getbomList"+getBillOfMaterialList.getBillOfMaterialHeader().toString());
			getbomList=getBillOfMaterialList.getBillOfMaterialHeader();
			System.out.println("bomHeaderList"+getBillOfMaterialList.getBillOfMaterialHeader().toString());
			
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			Date date = new Date();
			
			for(int i=0;i<getbomList.size();i++)
			{
				 if(df.format(date).equals(df.format(getbomList.get(i).getReqDate())) || getbomList.get(i).getStatus()==0)
				{
					getbomListsorted.add(getbomList.get(i));
				}
			}
			
		}catch(Exception e)
		{
			System.out.println("error in controller "+e.getMessage());
		}
		model.addObject("getbomList",getbomListsorted) ;
		model.addObject("toDept",tosettingvalue.getFrItemStockConfigure().get(0).getSettingValue()) ;
		model.addObject("fromDept",fromsettingvalue.getFrItemStockConfigure().get(0).getSettingValue()) ;
		
		
		return model;

	}
	
	@RequestMapping(value = "/bomDetailDepWise", method = RequestMethod.GET)
	public ModelAndView bomDetailDepWise(HttpServletRequest request, HttpServletResponse response) {
		/*Constants.mainAct = 17;
		Constants.subAct=184;*/
		ModelAndView model = new ModelAndView("productionPlan/bomDetailDepWise");
		
		
		 
		int reqId=Integer.parseInt(request.getParameter("reqId"));
		System.out.println(reqId);
		int fromDept= Integer.parseInt(request.getParameter("fromDept"));
		HttpSession session=request.getSession();
		UserResponse userResponse =(UserResponse) session.getAttribute("UserDetail");
		 
		int userId=userResponse.getUser().getId();
		try
		{
			
			System.out.println("userID"+userId);
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("reqId",reqId);
			RestTemplate rest = new RestTemplate();
			billOfMaterialHeader=rest.postForObject(Constants.url + "/getDetailedwithreqId",map, BillOfMaterialHeader.class);
			bomwithdetaild =billOfMaterialHeader.getBillOfMaterialDetailed();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		model.addObject("billOfMaterialHeader",billOfMaterialHeader);
		model.addObject("bomwithdetaild", bomwithdetaild);
		model.addObject("fromDept", fromDept);
		model.addObject("userId", userId);
		
		return model;
	}
	
	@RequestMapping(value = "/getBomListDepiseWithDate", method = RequestMethod.GET)
	@ResponseBody
	public List<BillOfMaterialHeader> getBomListDepiseWithDate(HttpServletRequest request, HttpServletResponse response) {
		/*Constants.mainAct = 17;
		Constants.subAct=184;*/
		System.out.println("in controller");
		String frmdate=request.getParameter("from_date");
		String todate=request.getParameter("to_date");
		
		try {
			int fromDep= Integer.parseInt(request.getParameter("fromDept"));
			int toDep= Integer.parseInt(request.getParameter("toDept"));
			System.out.println("in getMixingListWithDate   "+frmdate+todate);
			String frdate=DateConvertor.convertToYMD(frmdate);
			String tdate=DateConvertor.convertToYMD(todate);
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("frmdate",frdate);
			map.add("todate",tdate);
			map.add("fromDept",fromDep);             
			map.add("toDept",toDep);            
			System.out.println("map"+map);
			System.out.println("in getBOMListWithDate   "+frdate+tdate);
			RestTemplate rest = new RestTemplate();
			GetBillOfMaterialList getBillOfMaterialList= rest.postForObject(Constants.url + "/getBOMHeaderListBmsAndStore",map, GetBillOfMaterialList.class);
			getBOMListall  = getBillOfMaterialList.getBillOfMaterialHeader();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return getBOMListall;
	

	}
	
	@RequestMapping(value = "/rejectiontoBmsByDeptWise", method = RequestMethod.GET)
	public ModelAndView rejectiontoBmsByDeptWise(HttpServletRequest request, HttpServletResponse response) {
		/*Constants.mainAct = 17;
		Constants.subAct=184;*/
		ModelAndView model = new ModelAndView("productionPlan/rejectBomByDept");
		int fromDept= Integer.parseInt(request.getParameter("fromDept"));
		System.out.println("in rejection form "+ fromDept);
		
		model.addObject("billOfMaterialHeader",billOfMaterialHeader);
		model.addObject("bomwithdetaild", bomwithdetaild);
		model.addObject("fromDept", fromDept);
		
		return model;
	}
	
	@RequestMapping(value = "/updateRejectedQtyByDept", method = RequestMethod.POST)
	public String updateRejectedQtyByDept(HttpServletRequest request, HttpServletResponse response) {
		/*Constants.mainAct = 17;
		Constants.subAct=184;*/
		RestTemplate rest = new RestTemplate();
		String ret = null;
		try {
			Date date= new Date();
			HttpSession session=request.getSession();
			UserResponse userResponse =(UserResponse) session.getAttribute("UserDetail");
			int fromDept= Integer.parseInt(request.getParameter("fromDept"));
			List<BillOfMaterialDetailed> getBillOfMaterialDetailed = new ArrayList<BillOfMaterialDetailed>();
			int userId=userResponse.getUser().getId();
			
			for(int i=0;i<billOfMaterialHeader.getBillOfMaterialDetailed().size();i++)
			{
				  
					float reject_qty=Float.parseFloat(request.getParameter("rejectedQty"+billOfMaterialHeader.getBillOfMaterialDetailed().get(i).getReqDetailId()));
					float return_qty=Float.parseFloat(request.getParameter("returnQty"+billOfMaterialHeader.getBillOfMaterialDetailed().get(i).getReqDetailId()));
					if(reject_qty!=billOfMaterialHeader.getBillOfMaterialDetailed().get(i).getRejectedQty() || return_qty!=billOfMaterialHeader.getBillOfMaterialDetailed().get(i).getReturnQty())
					{
						System.out.println("reject_qty Qty   :"+reject_qty); 
						billOfMaterialHeader.getBillOfMaterialDetailed().get(i).setRejectedQty(reject_qty);   
						System.out.println("return_qty Qty   :"+return_qty); 
						billOfMaterialHeader.getBillOfMaterialDetailed().get(i).setReturnQty(return_qty);  
						System.out.println(2); 
						getBillOfMaterialDetailed.add(billOfMaterialHeader.getBillOfMaterialDetailed().get(i));
					}
				 
			}
			billOfMaterialHeader.setStatus(2);
			billOfMaterialHeader.setRejDate(date);
			billOfMaterialHeader.setRejUserId(userId);
			billOfMaterialHeader.setBillOfMaterialDetailed(getBillOfMaterialDetailed);
			System.out.println(billOfMaterialHeader.toString());
			
			
			
			Info info = rest.postForObject(Constants.url + "saveBom", billOfMaterialHeader, Info.class);	
			System.out.println(info);
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			FrItemStockConfigureList fromsettingvalue = new FrItemStockConfigureList();
			FrItemStockConfigureList tosettingvalue = new FrItemStockConfigureList();
			
			map = new LinkedMultiValueMap<String, Object>();
			 String forreturnlist = new String(); 
			 forreturnlist = "PROD"; 
				map.add("settingKeyList", forreturnlist);
				 fromsettingvalue = rest.postForObject(Constants.url + "getDeptSettingValue", map,
						FrItemStockConfigureList.class);
				 
			map = new LinkedMultiValueMap<String, Object>();
			String forreturnlist1 = new String(); 
			forreturnlist1 = "MIX"; 
			map.add("settingKeyList", forreturnlist1);
			tosettingvalue = rest.postForObject(Constants.url + "getDeptSettingValue", map,
					FrItemStockConfigureList.class);
			
			if(fromDept==fromsettingvalue.getFrItemStockConfigure().get(0).getSettingValue())
			{
				ret="redirect:/getBomListforProduction";
			}
			else if(fromDept==tosettingvalue.getFrItemStockConfigure().get(0).getSettingValue())
			{
				ret="redirect:/getBomListforMixing";
			}
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		return ret;

	}

	 
}
