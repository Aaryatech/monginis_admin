package com.ats.adminpanel.controller;

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

import org.springframework.context.annotation.Scope;
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
import com.ats.adminpanel.model.AllFrIdName;
import com.ats.adminpanel.model.AllFrIdNameList;
import com.ats.adminpanel.model.ExportToExcel;
import com.ats.adminpanel.model.Info;
import com.ats.adminpanel.model.TrayType;
import com.ats.adminpanel.model.ggreports.GrnGvnReportByGrnType;
import com.ats.adminpanel.model.item.FrItemStockConfigureList;
import com.ats.adminpanel.model.tray.GetVehicleAvg;
import com.ats.adminpanel.model.tray.TrayMgtDetail;
import com.ats.adminpanel.model.tray.TrayMgtDetailBean;
import com.ats.adminpanel.util.ItextPageEvent;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


@Controller
@Scope("session")
public class TrayBillController {
	
	
	@RequestMapping(value = "/showTrayBill", method = RequestMethod.GET)
	public ModelAndView showTrayBill(HttpServletRequest request, HttpServletResponse response) {

		//Constants.mainAct=17;
		//Constants.subAct =90;

		ModelAndView model = new ModelAndView("trayBill/trayBills");
		try {
			RestTemplate restTemplate = new RestTemplate();

			TrayMgtDetail[] trayMgtDetails=restTemplate.getForObject(Constants.url + "/traymgt/getTrayMgtDetailsForBill",TrayMgtDetail[].class);

			ArrayList<TrayMgtDetail> trayMgtDetailsList=new ArrayList<TrayMgtDetail>(Arrays.asList(trayMgtDetails));
			
			System.out.println("trayMgtDetailsList"+trayMgtDetailsList.toString());
			
		    TrayType[] trayTypes=restTemplate.getForObject(Constants.url+"/getTrayTypes", TrayType[].class);
		    
			ArrayList<TrayType> trayTypeList=new ArrayList<TrayType>(Arrays.asList(trayTypes));

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			
			String settingKey = "tray_gst_per";
			map.add("settingKeyList", settingKey);
			FrItemStockConfigureList settingList = restTemplate.postForObject(Constants.url + "getDeptSettingValue", map,FrItemStockConfigureList.class);
		
		  model.addObject("gstPer", settingList.getFrItemStockConfigure().get(0).getSettingValue());
		  model.addObject("smallPrice", trayTypeList.get(0).getTrayPrice());
		  model.addObject("bigPrice", trayTypeList.get(1).getTrayPrice());
		  model.addObject("leadPrice", trayTypeList.get(2).getTrayPrice());
		  model.addObject("extraPrice", trayTypeList.get(3).getTrayPrice());

		  model.addObject("trayMgtDetailsList",trayMgtDetailsList);
		  
		  
		} catch (Exception e) {
			System.out.println("Exc In showTrayBills:" + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/insertTrayBills", method = RequestMethod.POST)
	public String insertTrayBills(HttpServletRequest request, HttpServletResponse response) {


		ModelAndView model = new ModelAndView("trayBill/trayBills");
	  try {
			RestTemplate restTemplate = new RestTemplate();
			DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			DateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
			
			TrayMgtDetail[] trayMgtDetails=restTemplate.getForObject(Constants.url + "/traymgt/getTrayMgtDetailsForBill",TrayMgtDetail[].class);

			ArrayList<TrayMgtDetail> trayMgtDetailsList=new ArrayList<TrayMgtDetail>(Arrays.asList(trayMgtDetails));
			
		    TrayType[] trayTypes=restTemplate.getForObject(Constants.url+"/getTrayTypes", TrayType[].class);
		    
			ArrayList<TrayType> trayTypeList=new ArrayList<TrayType>(Arrays.asList(trayTypes));

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			
			String settingKey = "tray_gst_per";
			map.add("settingKeyList", settingKey);
			FrItemStockConfigureList settingList = restTemplate.postForObject(Constants.url + "getDeptSettingValue", map,FrItemStockConfigureList.class);
		
			List<TrayMgtDetailBean> trayMgtDetailBeanList=new ArrayList<TrayMgtDetailBean>();
			for(int i=0;i<trayMgtDetailsList.size();i++)
			{
				int tranDetailId=0;
				try {
				 tranDetailId=Integer.parseInt(request.getParameter("tranDetailId"+trayMgtDetailsList.get(i).getTranDetailId()));
				}
				catch(Exception e)
				{
					tranDetailId=0;
					e.printStackTrace();
				}
                if(tranDetailId>0)
                {
                	
                	
                	TrayMgtDetailBean trayMgtDetailBean=new TrayMgtDetailBean();
                	
                	trayMgtDetailBean.setTranDetailId(tranDetailId);
                	trayMgtDetailBean.setTranId(trayMgtDetailsList.get(i).getTranId());
                	trayMgtDetailBean.setFrId(trayMgtDetailsList.get(i).getFrId());
                	trayMgtDetailBean.setOuttrayDate(sdf1.format(sdf.parse((trayMgtDetailsList.get(i).getOuttrayDate()))));
                	
                	trayMgtDetailBean.setOuttrayBig(trayMgtDetailsList.get(i).getOuttrayBig());
                	trayMgtDetailBean.setOuttraySmall(trayMgtDetailsList.get(i).getOuttraySmall());
                	trayMgtDetailBean.setOuttrayLead(trayMgtDetailsList.get(i).getOuttrayLead());
                	trayMgtDetailBean.setOuttrayExtra(trayMgtDetailsList.get(i).getOuttrayExtra());
                	
                   	trayMgtDetailBean.setIntrayDate(sdf1.format(sdf.parse((trayMgtDetailsList.get(i).getIntrayDate()))));
                	trayMgtDetailBean.setIntrayBig(trayMgtDetailsList.get(i).getIntrayBig());
                	trayMgtDetailBean.setIntraySmall(trayMgtDetailsList.get(i).getIntraySmall());
                	trayMgtDetailBean.setIntrayLead(trayMgtDetailsList.get(i).getIntrayLead());
                	trayMgtDetailBean.setIntrayExtra(trayMgtDetailsList.get(i).getIntrayExtra());

                	trayMgtDetailBean.setIntrayDate1(sdf1.format(sdf.parse((trayMgtDetailsList.get(i).getIntrayDate1()))));
                	trayMgtDetailBean.setIntrayBig1(trayMgtDetailsList.get(i).getIntrayBig1());
                	trayMgtDetailBean.setIntraySmall1(trayMgtDetailsList.get(i).getIntraySmall1());
                	trayMgtDetailBean.setIntrayLead1(trayMgtDetailsList.get(i).getIntrayLead1());
                	trayMgtDetailBean.setIntrayExtra1(trayMgtDetailsList.get(i).getIntrayExtra1());

                	trayMgtDetailBean.setBalanceBig(trayMgtDetailsList.get(i).getBalanceBig());
                	trayMgtDetailBean.setBalanceSmall(trayMgtDetailsList.get(i).getBalanceSmall());
                	trayMgtDetailBean.setBalanceLead(trayMgtDetailsList.get(i).getBalanceLead());
                	trayMgtDetailBean.setBalanceExtra(trayMgtDetailsList.get(i).getBalanceExtra());
                	
                	trayMgtDetailBean.setIntrayDate2(sdf1.format(sdf.parse((trayMgtDetailsList.get(i).getIntrayDate2()))));
                	trayMgtDetailBean.setIntrayBig2(trayMgtDetailsList.get(i).getIntrayBig2());
                	trayMgtDetailBean.setIntraySmall2(trayMgtDetailsList.get(i).getIntraySmall2());
                	trayMgtDetailBean.setIntrayLead2(trayMgtDetailsList.get(i).getIntrayLead2());
                	trayMgtDetailBean.setIntrayExtra2(trayMgtDetailsList.get(i).getIntrayExtra2());
                	
                 	trayMgtDetailBean.setPriceSmall(trayTypeList.get(0).getTrayPrice());
                	trayMgtDetailBean.setPriceBig(trayTypeList.get(1).getTrayPrice());
                	trayMgtDetailBean.setPriceLead(trayTypeList.get(2).getTrayPrice());
                	trayMgtDetailBean.setPriceExtra(trayTypeList.get(3).getTrayPrice());

                	trayMgtDetailBean.setQtyBig(trayMgtDetailsList.get(i).getBalanceBig());
                	trayMgtDetailBean.setQtyExtra(trayMgtDetailsList.get(i).getBalanceExtra());
                	trayMgtDetailBean.setQtyLead(trayMgtDetailsList.get(i).getBalanceLead());
                	trayMgtDetailBean.setQtySmall(trayMgtDetailsList.get(i).getBalanceSmall());  
                	
                	float smallQtyPrice=(trayTypeList.get(0).getTrayPrice())*(trayMgtDetailsList.get(i).getBalanceSmall());
                	float bigQtyPrice=(trayTypeList.get(1).getTrayPrice())*(trayMgtDetailsList.get(i).getBalanceBig());
                	float leadQtyPrice=(trayTypeList.get(2).getTrayPrice())*(trayMgtDetailsList.get(i).getBalanceLead());
                	float extraQtyPrice=(trayTypeList.get(3).getTrayPrice())*(trayMgtDetailsList.get(i).getBalanceExtra());
                	
                	float taxableAmt=bigQtyPrice+smallQtyPrice+leadQtyPrice+extraQtyPrice;
                	float taxAmt=taxableAmt*(settingList.getFrItemStockConfigure().get(0).getSettingValue())/100;
                    float grandTotal=taxableAmt+taxAmt;
                    
                	trayMgtDetailBean.setGrandTotal(grandTotal);
                	trayMgtDetailBean.setTrayStatus(5);
                	trayMgtDetailBean.setDepositIsUsed(1);
                	trayMgtDetailBean.setDelStatus(0);
                	
                	trayMgtDetailBean.setGstPer(settingList.getFrItemStockConfigure().get(0).getSettingValue());
                	trayMgtDetailBean.setGstRs(taxAmt);
                
                	trayMgtDetailBean.setTaxAmt(taxAmt);
                	trayMgtDetailBean.setTaxableAmt(taxableAmt);
                	trayMgtDetailBean.setIsSameDay(trayMgtDetailsList.get(i).getIsSameDay());

                	trayMgtDetailBeanList.add(trayMgtDetailBean);
                }
			
			}
			
			Info info=restTemplate.postForObject(Constants.url +"/traymgt/saveTrayMgtDetailForBill" , trayMgtDetailBeanList, Info.class);
			System.out.println("info Res"+info.toString());
			
		} catch (Exception e) {
			System.out.println("Exc In insertTrayBills:" + e.getMessage());
		}
		return "redirect:/showTrayBill";
		
	}
	@RequestMapping(value = "/viewTrayBills", method = RequestMethod.GET)
	public ModelAndView viewTrayBills(HttpServletRequest request, HttpServletResponse response) {

		//Constants.mainAct=17;
		//Constants.subAct =90;

		ModelAndView model = new ModelAndView("trayBill/showTrayBills");
		try {
			RestTemplate restTemplate = new RestTemplate();

			ZoneId z = ZoneId.of("Asia/Calcutta");

			LocalDate date = LocalDate.now(z);
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
			String todaysDate = date.format(formatters);
			
			AllFrIdNameList allFrIdNameList = new AllFrIdNameList();
			try {

				allFrIdNameList = restTemplate.getForObject(Constants.url + "getAllFrIdName", AllFrIdNameList.class);

			} catch (Exception e) {
				System.out.println("Exception in getAllFrIdName" + e.getMessage());
				e.printStackTrace();

			}

			System.out.println(" Fr " + allFrIdNameList.getFrIdNamesList());
		  model.addObject("allFrIdNameList",allFrIdNameList.getFrIdNamesList());
		  model.addObject("todaysDate", todaysDate);
		  
		} catch (Exception e) {
			System.out.println("Exc In viewTrayBills:" + e.getMessage());
		}
		return model;
	}
	List<TrayMgtDetail>	billList = new ArrayList<TrayMgtDetail>();
	List<TrayMgtDetail>	billsList = new ArrayList<TrayMgtDetail>();

		@RequestMapping(value = "/getTrayBillListProcess", method = RequestMethod.GET)
		public @ResponseBody List<TrayMgtDetail> getTrayBillListProcess(HttpServletRequest request,
				HttpServletResponse response) {

			//Constants.mainAct = 8;
			//Constants.subAct = 83;


			try {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				RestTemplate restTemplate = new RestTemplate();

				String routeId = "0";
				String frIdString = "";

				System.out.println("inside getTrayBillListProcess ajax call");

				frIdString = request.getParameter("fr_id_list");
				String fromDate = request.getParameter("from_date");
				String toDate = request.getParameter("to_date");

				boolean isAllFrSelected = false;

				frIdString = frIdString.substring(1, frIdString.length() - 1);
				frIdString = frIdString.replaceAll("\"", "");

				
				if (frIdString.contains("-1")) {
					isAllFrSelected = true;

				}

				
				if (isAllFrSelected) {

					map.add("fromDate", fromDate);
					map.add("toDate", toDate);
					map.add("frIds", frIdString);
					System.out.println("Inside IF  is All fr Selected " + isAllFrSelected);

					 ParameterizedTypeReference<List<TrayMgtDetail>> typeRef = new ParameterizedTypeReference<List<TrayMgtDetail>>() {
						};
						ResponseEntity<List<TrayMgtDetail>> responseEntity = restTemplate.exchange(Constants.url + "/traymgt/viewTrayMgtBillDetails",
								HttpMethod.POST, new HttpEntity<>(map), typeRef);
					
						billList =new ArrayList<TrayMgtDetail>();
						billList =responseEntity.getBody();
						System.out.println("billList"+billList.toString());
				} else { // few franchisee selected

					 map = new LinkedMultiValueMap<String, Object>();
					System.out.println("Inside Else: Few Fr Selected ");
					map.add("frIds", frIdString);
					map.add("fromDate", fromDate);
					map.add("toDate", toDate);

					 ParameterizedTypeReference<List<TrayMgtDetail>> typeRef = new ParameterizedTypeReference<List<TrayMgtDetail>>() {
						};
						ResponseEntity<List<TrayMgtDetail>> responseEntity = restTemplate.exchange(Constants.url + "/traymgt/viewTrayMgtBillDetails",
								HttpMethod.POST, new HttpEntity<>(map), typeRef);
						billList =new ArrayList<TrayMgtDetail>();
						billList =responseEntity.getBody();
						System.out.println("billList"+billList.toString());
				}

			} catch (Exception e) {

				System.out.println("Ex in getting trayBillList" + e.getMessage());
				e.printStackTrace();
			}

			return billList;

		}
	/*	@RequestMapping(value = "/getSelectedTrayIdForPrint", method = RequestMethod.GET)
		public void getSelectedTrayIdForPrint(HttpServletRequest request, HttpServletResponse response) {

			System.out.println("IN Metjod");

			billsList = new ArrayList<TrayMgtDetail>();
			String selectedId = request.getParameter("id");
			selectedId = selectedId.substring(1, selectedId.length() - 1);
			selectedId = selectedId.replaceAll("\"", "");

			System.out.println("selectedId  " + selectedId);

			List<String> selectedIdList = new ArrayList<>();
			System.out.println("billList  " + billList.toString());
			selectedIdList = Arrays.asList(selectedId.split(","));
			for (int i = 0; i < billList.size(); i++) {
				for (int j = 0; j < selectedIdList.size(); j++) {
					if (Integer.parseInt(selectedIdList.get(j)) == billList.get(i).getTranDetailId()) {
						System.out.println(i);
						billsList.add(billList.get(i));
					}
				}
			}

		}*/

		@RequestMapping(value = "/printSelectedTrayBill/{selectedId}", method = RequestMethod.GET)
		public ModelAndView printSelectedTrayBill(@PathVariable("selectedId") String selectedId,HttpServletRequest request, HttpServletResponse response) {
			ModelAndView model = new ModelAndView("trayBill/trayBillPrint");
			System.out.println("IN Print Selected Bill");
            try {
			System.out.println("IN Metjod");

			billsList = new ArrayList<TrayMgtDetail>();
			//String selectedId = request.getParameter("id");
		/*	selectedId = selectedId.substring(1, selectedId.length() - 1);
			selectedId = selectedId.replaceAll("\"", "");*/

			System.out.println("selectedId  " + selectedId);

			List<String> selectedIdList = new ArrayList<>();
			System.out.println("billList  " + billList.toString());
			selectedIdList = Arrays.asList(selectedId.split(","));
			for (int i = 0; i < billList.size(); i++) {
				for (int j = 0; j < selectedIdList.size(); j++) {
					if (Integer.parseInt(selectedIdList.get(j)) == billList.get(i).getTranDetailId()) {
						System.out.println(i);
						billsList.add(billList.get(i));
					}
				}
			}
			System.out.println("Selected List " + billsList.toString());
			model.addObject("billsList", billsList);
			
			model.addObject("invNo","");


			model.addObject("date", new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
			System.out.println("After print ");
            }
            catch (Exception e) {
				System.out.println(e.getMessage());
			}
			return model;
		}
		List<GetVehicleAvg> vehicleAvgList=null;
		@RequestMapping(value = "/getVehicleAvg", method = RequestMethod.GET)
		public @ResponseBody List<GetVehicleAvg> getVehicleAvg(HttpServletRequest request,HttpServletResponse response) {
		
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
				String date = request.getParameter("date");
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				RestTemplate restTemplate = new RestTemplate();
                map.add("date",sdf2.format(sdf.parse(date)));
				
                ParameterizedTypeReference<List<GetVehicleAvg>> typeRef = new ParameterizedTypeReference<List<GetVehicleAvg>>() {
				};
				ResponseEntity<List<GetVehicleAvg>> responseEntity = restTemplate.exchange(Constants.url + "/traymgt/getAllTrayHeadersByDate",
						HttpMethod.POST, new HttpEntity<>(map), typeRef);
				vehicleAvgList =responseEntity.getBody();
				
				List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

				ExportToExcel expoExcel = new ExportToExcel();
				List<String> rowData = new ArrayList<String>();

				rowData.add("Sr.No.");
				rowData.add("Vehicle No");
				rowData.add("Driver Name");
				rowData.add("Route");
				rowData.add("Out Kms.");
				rowData.add("In Kms.");
				rowData.add("Running Kms.");
				rowData.add("Diesel");
				rowData.add("Actual Avg.");
				rowData.add("Minimum Avg.");
				rowData.add("Standard Avg.");
			
				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);
				for (int i = 0; i < vehicleAvgList.size(); i++) {
					expoExcel = new ExportToExcel();
					rowData = new ArrayList<String>();
					rowData.add((i+1)+"");

					rowData.add("" + vehicleAvgList.get(i).getVehNo());
					rowData.add(vehicleAvgList.get(i).getDriverName());
					rowData.add(vehicleAvgList.get(i).getRouteName());
					rowData.add("" + vehicleAvgList.get(i).getVehOutkm());
					rowData.add("" + vehicleAvgList.get(i).getVehInkm());
					rowData.add("" + vehicleAvgList.get(i).getVehRunningKm());
					rowData.add("" + vehicleAvgList.get(i).getDiesel());
					float avg= vehicleAvgList.get(i).getVehRunningKm()/vehicleAvgList.get(i).getDiesel();
					if(avg>0)
					{
					 rowData.add(SalesReportController.roundUp(avg)+"");
					}
					else
					{
					 rowData.add(SalesReportController.roundUp(0)+"");
					}
					rowData.add("" + vehicleAvgList.get(i).getVehMiniAvg());
					rowData.add("" + vehicleAvgList.get(i).getVehStandAvg());

					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);

				}

				HttpSession session = request.getSession();
				session.setAttribute("exportExcelList", exportToExcelList);
				session.setAttribute("excelName", "Vehicle Avg.");
				
				
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return vehicleAvgList;
			
		}
		@RequestMapping(value = "/getVehAvgReportPdf", method = RequestMethod.GET)
		public void getVehAvgReportPdf(HttpServletRequest request, HttpServletResponse response) {

			BufferedOutputStream outStream = null;
			System.out.println("Inside Pdf ");

			Document document = new Document(PageSize.A4,20,20,150,30);
			// ByteArrayOutputStream out = new ByteArrayOutputStream();

			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			Calendar cal = Calendar.getInstance();

			String timeStamp = dateFormat.format(cal.getTime());
			String FILE_PATH = Constants.REPORT_SAVE;
			File file = new File(FILE_PATH);

			PdfWriter writer = null;

			FileOutputStream out = null;
			try {
				out = new FileOutputStream(FILE_PATH);
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				
				String header=
						" Galdhar Foods Pvt.Ltd\n" + "Factory Add: A-32 Shendra, MIDC, Auraangabad-4331667"
								+ "\nPhone:0240-2466217, Email: aurangabad@monginis.net";
			

				String title="Report-For Vehicle Average Details";

				DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
				String reportDate = DF.format(new Date());
				
				writer = PdfWriter.getInstance(document, out);
				
				ItextPageEvent event=new ItextPageEvent(header,title, reportDate);
				
				writer.setPageEvent(event);
				
			} catch (DocumentException e) {

				e.printStackTrace();
			}

			PdfPTable table = new PdfPTable(11);
			try {
				System.out.println("Inside PDF Table try");
				table.setWidthPercentage(100);
				table.setWidths(new float[] {0.4f,1.7f, 1.9f,1.9f,1.6f,1.6f,1.6f,1.3f,1.3f,1.3f,1.2f});
				Font headFont = new Font(FontFamily.TIMES_ROMAN, 13, Font.NORMAL, BaseColor.BLACK);
				Font headFont1 = new Font(FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK);
				Font f = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.UNDERLINE, BaseColor.BLUE);

				PdfPCell hcell=new PdfPCell();
				hcell.setBackgroundColor(BaseColor.PINK);
				hcell.setPadding(4);
				
				hcell = new PdfPCell(new Phrase("Sr.", headFont1));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Vehicle No", headFont1));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(hcell);

				
				hcell = new PdfPCell(new Phrase("Driver Name", headFont1));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(hcell);
				
				
				hcell = new PdfPCell(new Phrase("Route", headFont1));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(hcell);
				
				
				
				hcell = new PdfPCell(new Phrase("Out Kms.", headFont1));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(hcell);
				
				hcell = new PdfPCell(new Phrase("In Kms.", headFont1));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(hcell);
				
				
				hcell = new PdfPCell(new Phrase("Running Kms.", headFont1));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(hcell);
				
				hcell = new PdfPCell(new Phrase("Diesel", headFont1));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(hcell);
				
				hcell = new PdfPCell(new Phrase("Actual Avg.", headFont1));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(hcell);
				
				hcell = new PdfPCell(new Phrase("Minimum Avg.", headFont1));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(hcell);
				
				hcell = new PdfPCell(new Phrase("Standard Avg.", headFont1));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(hcell);
				int index = 0;
				for (GetVehicleAvg vehicleAvg: vehicleAvgList) {
					index++;
					PdfPCell cell;

					cell = new PdfPCell(new Phrase(String.valueOf(index), headFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					  cell.setPadding(4);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase(vehicleAvg.getVehNo(), headFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell.setPaddingRight(2);
					  cell.setPadding(4);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase(String.valueOf(vehicleAvg.getDriverName()), headFont));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell.setPaddingRight(2);
						  cell.setPadding(4);
						table.addCell(cell);
				
						cell = new PdfPCell(new Phrase(String.valueOf(vehicleAvg.getRouteName()), headFont));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell.setPaddingRight(2);
						  cell.setPadding(4);
						table.addCell(cell);
						
						
						cell = new PdfPCell(new Phrase(String.valueOf(vehicleAvg.getVehOutkm()), headFont));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						cell.setPaddingRight(2);
						  cell.setPadding(4);
						table.addCell(cell);
						
						
						cell = new PdfPCell(new Phrase(String.valueOf(vehicleAvg.getVehInkm()), headFont));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						cell.setPaddingRight(2);
						  cell.setPadding(4);
						  
					 table.addCell(cell);
					
						
					cell = new PdfPCell(new Phrase(""+vehicleAvg.getVehRunningKm(), headFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					cell.setPaddingRight(2);
					  cell.setPadding(5);
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase(""+vehicleAvg.getDiesel(), headFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					cell.setPaddingRight(2);
					  cell.setPadding(5);
					table.addCell(cell);
					float avg=vehicleAvg.getVehRunningKm()/vehicleAvg.getDiesel();
					if(avg>0) {
					cell = new PdfPCell(new Phrase(SalesReportController.roundUp(avg)+"", headFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					cell.setPaddingRight(2);
					  cell.setPadding(5);
					table.addCell(cell);
					}
					else {
						cell = new PdfPCell(new Phrase(SalesReportController.roundUp(0)+"", headFont));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						cell.setPaddingRight(2);
						  cell.setPadding(5);
						table.addCell(cell);
					}
					
					cell = new PdfPCell(new Phrase(""+vehicleAvg.getVehMiniAvg(), headFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					cell.setPaddingRight(2);
					  cell.setPadding(5);
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase(""+vehicleAvg.getVehStandAvg(), headFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					cell.setPaddingRight(2);
					  cell.setPadding(5);
					table.addCell(cell);
				
				}
				
				document.open();
				document.add(table);
				document.close();
			
				if (file != null) {

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

				System.out.println("Pdf Generation Error" + ex.getMessage());

				ex.printStackTrace();

			}
		}
		@RequestMapping(value = "/showVehAvg", method = RequestMethod.GET)
		public ModelAndView showVehAvg(HttpServletRequest request, HttpServletResponse response) {

			//Constants.mainAct=17;
			//Constants.subAct =90;

			ModelAndView model = new ModelAndView("trayBill/vehAvg");
			try {
				
				
			}
            catch (Exception e) {
				// TODO: handle exception
			}
		return model;
		}
		
		//Sachin 11-02-2020 Update vehicle report by admin in/out and diesel
		
		//			Info info=restTemplate.postForObject(Constants.url +"/traymgt/saveTrayMgtDetailForBill" , trayMgtDetailBeanList, Info.class);

		
		@RequestMapping(value = "/updateVehDetailByAdmin", method = RequestMethod.GET)
		public @ResponseBody Info getItemCode(HttpServletRequest request, HttpServletResponse response) {
			
			RestTemplate restTemplate = new RestTemplate();
			Info info = new Info();
			try {
				
				int tranId = Integer.parseInt(request.getParameter("tranId"));
				float  vehOutkm = Float.parseFloat(request.getParameter("vehOutkm"));
				float  vehInkm = Float.parseFloat(request.getParameter("vehInkm"));
				float  diesel = Float.parseFloat(request.getParameter("diesel"));
				int paramKey =Integer.parseInt(request.getParameter("paramKey"));
				

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				
				map.add("tranId", tranId);
				map.add("vehOutkm", vehOutkm);
				map.add("vehInkm", vehInkm);
				map.add("diesel", diesel);
				map.add("paramKey", paramKey);


				System.out.println(map);
				 info=restTemplate.postForObject(Constants.url +"/traymgt/updateVehDetailByAdmin" , map, Info.class);
				 
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return info;
		}
}
