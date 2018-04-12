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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.util.SystemOutLogger;
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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.model.AllFrIdNameList;
import com.ats.adminpanel.model.ExportToExcel;
import com.ats.adminpanel.model.Info;
import com.ats.adminpanel.model.RawMaterial.ItemDetailList;
import com.ats.adminpanel.model.item.FrItemStockConfigureList;
import com.ats.adminpanel.model.itextpdf.FooterTable;
import com.ats.adminpanel.model.production.GetOrderItemQty;
import com.ats.adminpanel.model.production.GetProdDetailBySubCat;
import com.ats.adminpanel.model.production.GetProdDetailBySubCatList;
import com.ats.adminpanel.model.production.GetProdPlanDetail;
import com.ats.adminpanel.model.production.GetProdPlanDetailList;
import com.ats.adminpanel.model.production.GetProdPlanHeader;
import com.ats.adminpanel.model.production.GetProdPlanHeaderList;
import com.ats.adminpanel.model.production.PostProductionPlanDetail;
import com.ats.adminpanel.model.production.mixing.temp.GetSFPlanDetailForMixing;
import com.ats.adminpanel.model.production.mixing.temp.GetSFPlanDetailForMixingList;
import com.ats.adminpanel.model.production.mixing.temp.GetTempMixItemDetail;
import com.ats.adminpanel.model.production.mixing.temp.GetTempMixItemDetailList;
import com.ats.adminpanel.model.production.mixing.temp.ProdMixingReqP1;
import com.ats.adminpanel.model.production.mixing.temp.ProdMixingReqP1List;
import com.ats.adminpanel.model.production.mixing.temp.TempMixing;
import com.ats.adminpanel.model.production.mixing.temp.TempMixingList;
import com.ats.adminpanel.model.productionplan.MixingDetailed;
import com.ats.adminpanel.model.productionplan.MixingHeader;
import com.ats.adminpanel.model.stock.FinishedGoodStock;
import com.ats.adminpanel.model.stock.FinishedGoodStockDetail;
import com.ats.adminpanel.model.stock.GetCurProdAndBillQty;
import com.ats.adminpanel.model.stock.GetCurProdAndBillQtyList; 
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
public class ViewProdController {

	String globalProductionBatch;
	int globalTimeSlot;
	// new Lists

	GetCurProdAndBillQtyList getCurProdAndBillQtyList = new GetCurProdAndBillQtyList();

	GetSFPlanDetailForMixingList getSFPlanDetailForMixingList;

	List<GetSFPlanDetailForMixing> sfPlanDetailForMixing = new ArrayList<>();

	GetTempMixItemDetailList getTempMixItemDetailList;

	List<GetTempMixItemDetail> tempMixItemDetail = new ArrayList<>();

	// temp Mix table beans
	TempMixingList tempMixingList;
	List<TempMixing> tempMixing = new ArrayList<>();

	List<GetProdPlanHeader> prodPlanHeaderList;

	public List<GetProdPlanDetail> prodPlanDetailList;
	public List<PostProductionPlanDetail> postProdPlanDetailList;

	int globalHeaderId = 0;
	private int productionId;
	private int isMixing;
	private String globalProdDate;
	String fromDate, toDate;

	@RequestMapping(value = "/showProdHeader", method = RequestMethod.GET)
	public ModelAndView showProdHeader(HttpServletRequest request, HttpServletResponse response) {

		Constants.mainAct = 4;
		Constants.subAct = 118;
		postProdPlanDetailList = new ArrayList<PostProductionPlanDetail>();

		ModelAndView model = new ModelAndView("production/viewProdHeader");
		try {
			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			if (request.getParameter("from_date") == null || request.getParameter("to_date") == null) {
				Date date = new Date();
				DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				fromDate = df.format(date);
				toDate = df.format(date);
				System.out.println("From Date And :" + fromDate + "ToDATE" + toDate);

				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				System.out.println("inside if ");
			} else {
				fromDate = request.getParameter("from_date");
				toDate = request.getParameter("to_date");

				System.out.println("inside Else ");

				System.out.println("fromDate " + fromDate);

				System.out.println("toDate " + toDate);

				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

			}

			GetProdPlanHeaderList prodHeader = restTemplate.postForObject(Constants.url + "getProdPlanHeader", map,
					GetProdPlanHeaderList.class);

			prodPlanHeaderList = new ArrayList<>();

			prodPlanHeaderList = prodHeader.getProdPlanHeader();

			System.out.println("prod header " + prodPlanHeaderList.toString());
			model.addObject("fromDate", fromDate);
			model.addObject("toDate", toDate);

			model.addObject("planHeader", prodPlanHeaderList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;

	}

	List<FinishedGoodStockDetail> updateStockDetailList;
	public GetProdPlanHeader pdfPlanHeader;

	@RequestMapping(value = "/getProdDetail/{productionHeaderId}", method = RequestMethod.GET)
	public ModelAndView getProdDetail(@PathVariable("productionHeaderId") int productionHeaderId,
			HttpServletRequest request, HttpServletResponse response) {

		System.out.println("inside Prod Detail");

		// Constants.mainAct=16;
		// Constants.subAct=164;
		ModelAndView model = new ModelAndView("production/prodDetail");

		try {
			globalHeaderId = productionHeaderId;
			System.out.println("After model ");
			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map = new LinkedMultiValueMap<String, Object>();
			map.add("stockStatus", 0);

			FinishedGoodStock sHeader = restTemplate.postForObject(Constants.url + "getFinGoodStockHeader", map,
					FinishedGoodStock.class);

			System.out.println("stock Header " + sHeader.toString());

			Date stockHeaderDate = sHeader.getFinGoodStockDate();

			GetProdPlanHeader planHeader = new GetProdPlanHeader();

			pdfPlanHeader = new GetProdPlanHeader();

			for (int i = 0; i < prodPlanHeaderList.size(); i++) {

				if (prodPlanHeaderList.get(i).getProductionHeaderId() == globalHeaderId) {

					planHeader = prodPlanHeaderList.get(i);

					System.out.println("Prod Header Received ID = " + planHeader.getProductionHeaderId());
				}

			}
			pdfPlanHeader = planHeader;
			globalProdDate = planHeader.getProductionDate();

			String prompt;
			if (stockHeaderDate.before(new SimpleDateFormat("yyyy-MM-dd").parse(globalProdDate))) {

				System.err.println("prod Date is Afrer Stock Date");
				System.out.println("Alert");

				prompt = "Please Close your Production First";

				model.addObject("msg", prompt);
			}

			else {

				map.add("planHeaderId", productionHeaderId);

				GetProdPlanDetailList prodDetail = restTemplate.postForObject(Constants.url + "getProdPlanDetail", map,
						GetProdPlanDetailList.class);

				prodPlanDetailList = new ArrayList<>();

				prodPlanDetailList = prodDetail.getProdPlanDetail();

				System.out.println("Porduction Details Received " + prodPlanDetailList.toString());

				globalProductionBatch = planHeader.getProductionBatch();
				globalTimeSlot = planHeader.getTimeSlot();
				productionId = planHeader.getProductionHeaderId();
				isMixing = planHeader.getIsMixing();
				for (int j = 0; j < prodPlanDetailList.size(); j++) {
					PostProductionPlanDetail postProductionPlanDetail = new PostProductionPlanDetail();
					postProductionPlanDetail.setProductionDetailId(prodPlanDetailList.get(j).getProductionDetailId());
					postProductionPlanDetail.setItemId(prodPlanDetailList.get(j).getItemId());
					postProductionPlanDetail.setOpeningQty(prodPlanDetailList.get(j).getOpeningQty());
					postProductionPlanDetail.setPlanQty(prodPlanDetailList.get(j).getPlanQty());
					postProductionPlanDetail.setProductionBatch(prodPlanDetailList.get(j).getProductionBatch());
					postProductionPlanDetail.setProductionDate(prodPlanDetailList.get(j).getProductionDate());
					postProductionPlanDetail.setProductionHeaderId(prodPlanDetailList.get(j).getProductionHeaderId());
					postProductionPlanDetail.setProductionQty(prodPlanDetailList.get(j).getProductionQty());
					postProductionPlanDetail.setOrderQty(prodPlanDetailList.get(j).getOrderQty());
					postProductionPlanDetail.setRejectedQty(prodPlanDetailList.get(j).getRejectedQty());
					postProdPlanDetailList.add(postProductionPlanDetail);
				}

				// new Code
				updateStockDetailList = new ArrayList<>();

				try {

					map = new LinkedMultiValueMap<String, Object>();
					map.add("stockDate", globalProdDate);
					System.out.println("global Prod Date for prod Detail " + globalProdDate);

					FinishedGoodStock stockHeader = restTemplate
							.postForObject(Constants.url + "getFinGoodStockHeaderByDate", map, FinishedGoodStock.class);
					DateFormat dfYmd = new SimpleDateFormat("yyyy-MM-dd");

					/*
					 * FinishedGoodStock stockHeader = restTemplate.postForObject(Constants.url +
					 * "getFinGoodStockHeader", map, FinishedGoodStock.class);
					 */
					System.out.println("stock Header " + stockHeader.toString());
					if (stockHeader.getFinGoodStockStatus() == 0) {

						Date stockDate = stockHeader.getFinGoodStockDate();

						List<GetCurProdAndBillQty> getCurProdAndBillQty = new ArrayList<>();
						map = new LinkedMultiValueMap<String, Object>();

						System.out.println("stock date " + stockDate);
						String stkDate = dfYmd.format(stockDate);
						// int selCate=Integer.parseInt(selectedCat);
						System.out.println("stk Date for get Cur Prod and Bill Qty " + stkDate);

						System.out.println("stk CatId for get Cur Prod and Bill Qty " + planHeader.getCatId());

						map.add("prodDate", stkDate);
						map.add("catId", planHeader.getCatId());
						map.add("delStatus", 0);

						map.add("timestamp", stockHeader.getTimestamp());

						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Calendar cal = Calendar.getInstance();

						map.add("curTimeStamp", dateFormat.format(cal.getTime()));

						getCurProdAndBillQtyList = restTemplate.postForObject(
								Constants.url + "getCurrentProdAndBillQty", map, GetCurProdAndBillQtyList.class);

						getCurProdAndBillQty = getCurProdAndBillQtyList.getGetCurProdAndBillQty();

						System.out.println("Cur Prod And Bill Qty Listy " + getCurProdAndBillQty.toString());
						DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

						String stockkDate = df.format(stockDate);
						map = new LinkedMultiValueMap<String, Object>();
						map.add("stockDate", stockkDate);
						map.add("catId", planHeader.getCatId());
						ParameterizedTypeReference<List<FinishedGoodStockDetail>> typeRef = new ParameterizedTypeReference<List<FinishedGoodStockDetail>>() {
						};
						ResponseEntity<List<FinishedGoodStockDetail>> responseEntity = restTemplate.exchange(
								Constants.url + "getFinGoodStockDetail", HttpMethod.POST, new HttpEntity<>(map),
								typeRef);

						List<FinishedGoodStockDetail> finGoodDetail = responseEntity.getBody();

						System.out.println("Finished Good Stock Detail " + finGoodDetail.toString());

						FinishedGoodStockDetail stockDetail = new FinishedGoodStockDetail();
						GetCurProdAndBillQty curProdBilQty = new GetCurProdAndBillQty();

						for (int i = 0; i < getCurProdAndBillQty.size(); i++) {

							curProdBilQty = getCurProdAndBillQty.get(i);

							for (int j = 0; j < finGoodDetail.size(); j++) {

								stockDetail = finGoodDetail.get(j);

								if (curProdBilQty.getId() == stockDetail.getItemId()) {

									System.out.println("item Id Matched " + curProdBilQty.getId() + "and "
											+ stockDetail.getItemId());

									float a = 0, b = 0, c = 0;

									float cloT1 = 0;
									float cloT2 = 0;
									float cloT3 = 0;

									float curClosing = 0;

									float totalClosing = 0;

									int billQty = curProdBilQty.getBillQty() + curProdBilQty.getDamagedQty();
									int prodQty = curProdBilQty.getProdQty();
									int rejQty = curProdBilQty.getRejectedQty();

									float t1 = stockDetail.getOpT1();
									float t2 = stockDetail.getOpT2();
									float t3 = stockDetail.getOpT3();

									System.out.println("t1 : " + t1 + " t2: " + t2 + " t3: " + t3);

									if (t3 > 0) {

										if (billQty < t3) {
											c = billQty;
										} else {
											c = t3;
										}

									} // end of t3>0

									if (t2 > 0) {

										if ((billQty - c) < t2) {
											b = (billQty - c);
										} else {

											b = t2;
										}

									} // end of t2>0

									if (t1 > 0) {

										if ((billQty - c - b) < t1) {

											a = (billQty - b - c);

										} else {

											a = t1;
										}
									} // end of if t1>0

									System.out.println("---------");
									System.out.println("bill Qty = " + curProdBilQty.getBillQty());
									System.out.println(" for Item Id " + curProdBilQty.getId());
									System.out.println("a =" + a + "b = " + b + "c= " + c);
									float damagedQty = curProdBilQty.getDamagedQty();

									float curIssue = billQty - (a + b + c);

									System.out.println("cur Issue qty =" + curIssue);

									cloT1 = t1 - a;
									cloT2 = t2 - b;
									cloT3 = t3 - c;

									curClosing = prodQty - rejQty - curIssue;

									totalClosing = ((t1 + t2 + t3) + (prodQty - rejQty)) - billQty;
									stockDetail.setCloCurrent(curClosing);
									stockDetail.setCloT1(cloT1);
									stockDetail.setCloT2(cloT2);
									stockDetail.setCloT3(cloT3);
									stockDetail.setFrSaleQty(billQty);
									stockDetail.setGateSaleQty(damagedQty);
									stockDetail.setProdQty(prodQty);
									stockDetail.setRejQty(rejQty);
									stockDetail.setTotalCloStk(totalClosing);

									updateStockDetailList.add(stockDetail);

									System.out.println("closing Qty  : t1 " + cloT1 + " t2 " + cloT2 + " t3 " + cloT3);

									System.out.println("cur Closing " + curClosing);
									System.out.println("total closing " + totalClosing);

									System.out.println("---------");

								} // end of if isSameItem =true
							} // end of Inner For Loop
						} // End of outer For loop

					} // end of if stockStatus ==0

					else {
						System.out.println("Else Calc Stock Bet Date ");

						// calc Stock Between Date
						Date stockDate = stockHeader.getFinGoodStockDate();

						System.out.println("stock date " + stockDate);
						String stkDate = dfYmd.format(stockDate);

						map = new LinkedMultiValueMap<String, Object>();

						map.add("catId", planHeader.getCatId());
						map.add("fromDate", stkDate);
						map.add("toDate", stkDate);
						ParameterizedTypeReference<List<FinishedGoodStockDetail>> typeRef = new ParameterizedTypeReference<List<FinishedGoodStockDetail>>() {
						};
						ResponseEntity<List<FinishedGoodStockDetail>> responseEntity = restTemplate.exchange(
								Constants.url + "getFinGoodStockBetTwoDateByCat", HttpMethod.POST,
								new HttpEntity<>(map), typeRef);

						updateStockDetailList = responseEntity.getBody();

					}
				} catch (Exception e) {
					System.out.println("Excein Prod Controller get Current Fin good Stock " + e.getMessage());
					e.printStackTrace();

				}

				int status = Integer.valueOf(planHeader.getProductionStatus());
				System.out.println("status" + status);
				for (int i = 0; i < prodPlanDetailList.size(); i++) {

					for (int j = 0; j < updateStockDetailList.size(); j++) {

						if (prodPlanDetailList.get(i).getItemId() == updateStockDetailList.get(j).getItemId()) {

							prodPlanDetailList.get(i).setCurClosingQty(updateStockDetailList.get(j).getCloCurrent());

							prodPlanDetailList.get(i).setCurOpeQty(updateStockDetailList.get(j).getTotalCloStk());

							if (status < 4) {
								prodPlanDetailList.get(i)
										.setOpeningQty((int) updateStockDetailList.get(j).getOpTotal());
							}

						}

					}

				}

				// end of new Code
				model.addObject("planDetail", prodPlanDetailList);

				model.addObject("planHeader", planHeader);
				
				try
				{
					List<ExportToExcel> exportToExcelList=new ArrayList<ExportToExcel>();
					
					ExportToExcel expoExcel=new ExportToExcel();
					List<String> rowData=new ArrayList<String>();
					 
					rowData.add("Sr.No.");
					rowData.add("Item Name"); 
					rowData.add("Current Stock ");  
					rowData.add("Opening Qty ");
					rowData.add("Plan Qty ");
					rowData.add("Order Qty"); 
					rowData.add("Actual Production "); 
					rowData.add("Rejected Qty"); 
					rowData.add("Total Qty "); 
				 
						
					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);
					for(int i=0;i<prodPlanDetailList.size();i++)
					{
						  expoExcel=new ExportToExcel();
						 rowData=new ArrayList<String>();
						 
					 
						 
						 rowData.add(""+(i+1)); 
						 rowData.add(""+prodPlanDetailList.get(i).getItemName());
						rowData.add(""+prodPlanDetailList.get(i).getCurOpeQty());  
						rowData.add(""+prodPlanDetailList.get(i).getOpeningQty());
						rowData.add(""+prodPlanDetailList.get(i).getPlanQty());
						rowData.add(""+prodPlanDetailList.get(i).getOrderQty()); 
						rowData.add(""+prodPlanDetailList.get(i).getProductionQty()); 
						rowData.add(""+prodPlanDetailList.get(i).getRejectedQty());
						rowData.add(""+prodPlanDetailList.get(i).getOpeningQty()+prodPlanDetailList.get(i).getPlanQty()); 
						
						expoExcel.setRowData(rowData);
						exportToExcelList.add(expoExcel);
						 
					}
					 
					
					
					HttpSession session = request.getSession();
					session.setAttribute("exportExcelList", exportToExcelList);
					session.setAttribute("excelName", "productionList");
				}catch(Exception e)
				{
					e.printStackTrace();
					System.out.println("Exception to generate Excel ");
				}
			} // End of ELse
		} catch (Exception e) {

			System.out.println("in catch model show Prod Detail  ");

			e.printStackTrace();

		}
		return model;

	}

	// (production cake & pestries)prodjobcard
	@RequestMapping(value = "/showProdByOrderPdf", method = RequestMethod.GET)
	public void showProdByOrderPdf(HttpServletRequest request, HttpServletResponse response)
			throws FileNotFoundException {
		BufferedOutputStream outStream = null;
		System.out.println("Inside Pdf prod From Order Or Plan");

		List<GetProdPlanDetail> moneyOutList = prodPlanDetailList;

		// moneyOutList = prodPlanDetailList;
		Document document = new Document(PageSize.A4);
		// ByteArrayOutputStream out = new ByteArrayOutputStream();

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();

		System.out.println("time in Gen Bill PDF ==" + dateFormat.format(cal.getTime()));
		String timeStamp = dateFormat.format(cal.getTime());
		String FILE_PATH = Constants.REPORT_SAVE;
		File file = new File(FILE_PATH);

		PdfWriter writer = null;

		FileOutputStream out = new FileOutputStream(FILE_PATH);
		try {
			writer = PdfWriter.getInstance(document, out);
		} catch (DocumentException e) {

			e.printStackTrace();
		}

		PdfPTable table = new PdfPTable(4);
		try {
			System.out.println("Inside PDF Table try");
			table.setWidthPercentage(100);
			table.setWidths(new float[] { 0.4f, 1.7f, 0.9f,1.0f});
			Font headFont = new Font(FontFamily.TIMES_ROMAN, 18, Font.NORMAL, BaseColor.BLACK);
			Font headFont1 = new Font(FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLACK);
			Font f = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.UNDERLINE, BaseColor.BLUE);

			PdfPCell hcell=new PdfPCell();
			hcell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			hcell.setPadding(4);
			hcell = new PdfPCell(new Phrase("Sr.No.", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Item Name", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			System.out.println("Plan Header data " + pdfPlanHeader.getIsPlanned());

			if (pdfPlanHeader.getIsPlanned() == 0) {
				hcell = new PdfPCell(new Phrase("Order Quantity", headFont1));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(hcell);
			} else if (pdfPlanHeader.getIsPlanned() == 1) {
				hcell = new PdfPCell(new Phrase("Plan Quantity", headFont1));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(hcell);

			}
			hcell = new PdfPCell(new Phrase("Production Quantity", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			
			int index = 0;
			for (GetProdPlanDetail getMoneyOut : moneyOutList) {
				index++;
				PdfPCell cell;

				cell = new PdfPCell(new Phrase(String.valueOf(index), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  cell.setPadding(4);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(getMoneyOut.getItemName(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				  cell.setPadding(4);
				table.addCell(cell);

				if (pdfPlanHeader.getIsPlanned() == 0) {
					cell = new PdfPCell(new Phrase(String.valueOf(getMoneyOut.getOrderQty()), headFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setPaddingRight(2);
					  cell.setPadding(4);
					table.addCell(cell);
				} else {
					cell = new PdfPCell(new Phrase(String.valueOf(getMoneyOut.getPlanQty()), headFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setPaddingRight(2);
					  cell.setPadding(4);
					table.addCell(cell);
				}
				cell = new PdfPCell(new Phrase("", headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingRight(2);
				  cell.setPadding(4);
				table.addCell(cell);
				// FooterTable footerEvent = new FooterTable(table);
				// writer.setPageEvent(footerEvent);
			}

			PdfPTable subCatTable = new PdfPTable(3);

			System.out.println("Inside PDF Table try");
						subCatTable.setWidthPercentage(100);
						subCatTable.setWidths(new float[] { 0.4f, 1.7f, 1.7f});
						
						 hcell=new PdfPCell();
						hcell.setBackgroundColor(BaseColor.LIGHT_GRAY);
						hcell.setPadding(2);
						hcell = new PdfPCell(new Phrase("Sr.No.", headFont1));
						hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
						subCatTable.addCell(hcell);

						hcell = new PdfPCell(new Phrase("Sub Category Name", headFont1));
						hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
						subCatTable.addCell(hcell);


						hcell = new PdfPCell(new Phrase("Total Quantity", headFont1));
						hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
						subCatTable.addCell(hcell);
						RestTemplate restTemplate = new RestTemplate();
						MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
						map.add("prodHeaderId", globalHeaderId);
						GetProdDetailBySubCatList subList=restTemplate.postForObject(Constants.url+ "getProdDetailBySubCat",map, GetProdDetailBySubCatList.class);
						index=0;
						for (GetProdDetailBySubCat getMoneyOut : subList.getProdDetailBySubCat()) {
							index++;
							PdfPCell cell;

							cell = new PdfPCell(new Phrase(String.valueOf(index), headFont));
							cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
							cell.setHorizontalAlignment(Element.ALIGN_CENTER);
							  cell.setPadding(2);
							subCatTable.addCell(cell);

							cell = new PdfPCell(new Phrase(getMoneyOut.getSubCateName(), headFont));
							cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
							cell.setHorizontalAlignment(Element.ALIGN_LEFT);
							cell.setPaddingRight(2);
							  cell.setPadding(2);
							subCatTable.addCell(cell);


							cell = new PdfPCell(new Phrase(String.valueOf(getMoneyOut.getTotalQty()), headFont));
								cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
								cell.setHorizontalAlignment(Element.ALIGN_CENTER);
								cell.setPaddingRight(2);
								  cell.setPadding(2);
								subCatTable.addCell(cell);

			}

			document.open();
			Paragraph company = new Paragraph(
					"Galdhar Foods Pvt.Ltd\n",
					f);
			company.setAlignment(Element.ALIGN_CENTER);
			document.add(company);
			document.add(new Paragraph(" "));
			if (pdfPlanHeader.getIsPlanned() == 1) {
				Paragraph heading = new Paragraph(
						"Report-Production Planning (Kitchen Department) " + pdfPlanHeader.getCatName());
				heading.setAlignment(Element.ALIGN_CENTER);
				document.add(heading);
			}
			if (pdfPlanHeader.getIsPlanned() == 0) {
				Paragraph heading = new Paragraph(pdfPlanHeader.getCatName()+" Report Job Card ");
				heading.setAlignment(Element.ALIGN_CENTER);
				document.add(heading);
			}
			DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
			String reportDate = DF.format(new Date());

			document.add(new Paragraph("Production Date: " + reportDate));
			document.add(new Paragraph("\n"));
			document.add(table);
			document.add(new Paragraph("\n"));
			document.add(subCatTable);
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

			System.out.println("Pdf Generation Error: BOm Prod  View Prod" + ex.getMessage());

			ex.printStackTrace();

		}

	}

	@RequestMapping(value = "/updateQty", method = RequestMethod.POST)
	public String updateQty(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("production/prodDetail");
		int prodId = 0;
		try {
			int prodStatus = Integer.parseInt(request.getParameter("productionStatus"));

			int productionId = Integer.parseInt(request.getParameter("production_id"));
			System.out.println("productionId" + productionId);

			prodId = productionId;

			System.out.println("prodStatus" + prodStatus);

			for (int i = 0; i < postProdPlanDetailList.size(); i++) {

				if (prodStatus == 1) {
					int planQty = Integer.parseInt(
							request.getParameter("plan_qty" + postProdPlanDetailList.get(i).getProductionDetailId()));
					System.out.println("planQty" + planQty);

					postProdPlanDetailList.get(i).setPlanQty(planQty);

				} else if (prodStatus == 2) {
					int orderQty = Integer.parseInt(
							request.getParameter("order_qty" + postProdPlanDetailList.get(i).getProductionDetailId()));
					System.out.println("orderQty:" + orderQty);

					postProdPlanDetailList.get(i).setOrderQty(orderQty);

				}

			}

			System.out.println("ItemDetail List:" + postProdPlanDetailList.toString());

			RestTemplate restTemplate = new RestTemplate();

			Info info = restTemplate.postForObject(Constants.url + "updateProdQty", postProdPlanDetailList, Info.class);

			System.out.println("Info" + info.toString());
		} catch (Exception e) {
			System.out.println("Exception In Update Plan Qty" + e.getMessage());
		}
		return "redirect:/getProdDetail/" + prodId;
	}

	@RequestMapping(value = "/completeProd", method = RequestMethod.POST)
	public String completeProduction(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("production/prodDetail");
		int prodId = 0;
		try {
			int prodStatus = Integer.parseInt(request.getParameter("productionStatus"));
			int productionId = Integer.parseInt(request.getParameter("production_id"));
			int isPlan = Integer.parseInt(request.getParameter("is_plan"));
			prodId = productionId;
			int prodUnit = Integer.parseInt(request.getParameter("prodUnit"));

			System.err.println("Prod Unit = " + prodUnit);

			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			System.out.println(dateFormat.format(date));

			Calendar cal = Calendar.getInstance();
			cal.setTime(date);

			// Integer dayOfMonth = cal.get(Calendar.DATE);
			String prodBatch = new String();
			prodBatch = prodBatch.concat("GF" + "-");

			if (prodUnit == 1) {
				System.err.println("Prod Unit = " +"Shendra");
				
				prodBatch = prodBatch.concat("S");
			} else {
				System.err.println("Prod Unit = " +"Chikhalthana");
				prodBatch = prodBatch.concat("C");
			}
			
			//int serial=1;
			
			int calCurrentMonth = cal.get(Calendar.MONTH) + 1;
			System.out.println("Current Cal Month " + calCurrentMonth);

			if (calCurrentMonth == 1) {
				prodBatch = prodBatch.concat("A");
			} else if (calCurrentMonth == 2) {
				prodBatch = prodBatch.concat("B");
			} else if (calCurrentMonth == 3) {
				prodBatch = prodBatch.concat("C");
			} else if (calCurrentMonth == 4) {
				prodBatch = prodBatch.concat("D");
			} else if (calCurrentMonth == 5) {
				prodBatch = prodBatch.concat("E");
			} else if (calCurrentMonth == 6) {
				prodBatch = prodBatch.concat("F");
			} else if (calCurrentMonth == 7) {
				prodBatch = prodBatch.concat("G");
			} else if (calCurrentMonth == 8) {
				prodBatch = prodBatch.concat("H");
			} else if (calCurrentMonth == 9) {
				prodBatch = prodBatch.concat("I");
			} else if (calCurrentMonth == 10) {
				prodBatch = prodBatch.concat("J");
			} else if (calCurrentMonth == 11) {
				prodBatch = prodBatch.concat("K");
			} else if (calCurrentMonth == 12) {
				prodBatch = prodBatch.concat("L");
			}

			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			
			map = new LinkedMultiValueMap<String, Object>();
			String settingKey1 = new String();
			settingKey1 = "prod_month"+","+"prod_batch_srno";
			map.add("settingKeyList", settingKey1);
			FrItemStockConfigureList settingList1 = restTemplate.postForObject(Constants.url + "getDeptSettingValue", map,
					FrItemStockConfigureList.class);
			int serial=settingList1.getFrItemStockConfigure().get(1).getSettingValue();
			int month=settingList1.getFrItemStockConfigure().get(0).getSettingValue();
			
			System.out.println("prod sr No  ="+serial + "month Name = " +month);
			
			if(calCurrentMonth!=month) {
				map = new LinkedMultiValueMap<String, Object>();
				System.err.println("Month not matched  Reset counter of srno ");
				
				map.add("settingValue", 1);

				map.add("settingKey", "prod_batch_srno");

				Info updateSetting = restTemplate.postForObject(Constants.url + "updateSeetingForPB", map,
						Info.class);
				System.err.println("Month not matched call for Update prod_month value in setting table ");
				
			}
			
			System.out.println("completeProd prodStatus" + prodStatus);

			for (int i = 0; i < postProdPlanDetailList.size(); i++) {
				
				System.err.println("Batch =="+prodBatch.concat("-"+serial));
				//prodBatch=prodBatch.concat("-"+serial);
				//int prodQty = 0,opTotal = 0, rejQty = 0;
				int prodQty = Integer.parseInt(
						request.getParameter("act_prod_qty" + postProdPlanDetailList.get(i).getProductionDetailId()));
				float opTotal = Float.parseFloat(
						request.getParameter("op_total" + postProdPlanDetailList.get(i).getProductionDetailId()));
				float rejQty = Float.parseFloat(
						request.getParameter("rej_qty" + postProdPlanDetailList.get(i).getProductionDetailId()));

				System.out.println("prodQty:" + prodQty);

				postProdPlanDetailList.get(i).setProductionQty(prodQty);
				postProdPlanDetailList.get(i).setOpeningQty((int) opTotal);
				postProdPlanDetailList.get(i).setRejectedQty((int) rejQty);
				
				postProdPlanDetailList.get(i).setProductionBatch(prodBatch+"-"+serial);
				serial=serial+1;
				
			}
			map.add("settingValue", serial);

			map.add("settingKey", "prod_batch_srno");

			Info updateSetting = restTemplate.postForObject(Constants.url + "updateSeetingForPB", map,
					Info.class);
			System.out.println("ItemDetail List:" + postProdPlanDetailList.toString());

			//RestTemplate restTemplate = new RestTemplate();

			
			 Info info = restTemplate.postForObject(Constants.url + "updateProdQty",
			 postProdPlanDetailList, Info.class);
			 
			 if (info.getError() == false) {
			 
			  map =
			 new LinkedMultiValueMap<String, Object>(); map.add("productionId",
			  productionId); if (isPlan == 1) { map.add("prodStatus", 4);
			 
			 } else { map.add("prodStatus", 5);
			 
			  }
			  
			  int isUpdated = restTemplate.postForObject(Constants.url + "updateProductionStatus",
			  map, Integer.class);
			  
			  System.out.println("isProdUpdated:" + isUpdated);
			  
			  if (isUpdated == 1) {
			  
			  map = new LinkedMultiValueMap<String, Object>(); map.add("prodId",
			  productionId); map.add("isProduction", 1); System.out.println("map" + map);
			  
			  info = restTemplate.postForObject(Constants.url + "/updateStatusWhileCompletProd",
			  map, Info.class); System.out.println("info" + info); }
			  
			  try { map = new LinkedMultiValueMap<String, Object>(); map.add("fromDate",
			  fromDate); map.add("toDate", toDate);
			  
			  GetProdPlanHeaderList prodHeader = restTemplate.postForObject(Constants.url +
			  "getProdPlanHeader", map, GetProdPlanHeaderList.class);
			  
			  prodPlanHeaderList = new ArrayList<>();
			  
			  prodPlanHeaderList = prodHeader.getProdPlanHeader(); } catch (Exception e) {
			  System.out.println("Exception In Complete Production:" + e.getMessage()); } }
			  System.out.println("Info" + info.toString());
			 
		} catch (Exception e) {
			System.out.println("Exception In complete Production");
			e.printStackTrace();
		}
		return "redirect:/getProdDetail/" + prodId;
	}

	ProdMixingReqP1List prodMixingReqP1List;

	List<ProdMixingReqP1> prodMixingReqP1;

	@RequestMapping(value = "/addMixing", method = RequestMethod.GET)
	public ModelAndView showMixing(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mav = new ModelAndView("production/addMixing");

		try {

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			System.out.println("globalHeaderId:" + globalHeaderId);
			map.add("headerId", globalHeaderId);

			prodMixingReqP1List = restTemplate.postForObject(Constants.url + "getSfPlanDetailForMixing", map,
					ProdMixingReqP1List.class);

			prodMixingReqP1 = new ArrayList<>();

			prodMixingReqP1 = prodMixingReqP1List.getProdMixingReqP1();

			System.out.println("Phase 1 Data " + prodMixingReqP1.toString());

			// sfPlanDetailForMixing =
			// getSFPlanDetailForMixingList.getSfPlanDetailForMixing();
			mav.addObject("mixingList", prodMixingReqP1);
			System.out.println("sf Plan Detail For Mixing  " + sfPlanDetailForMixing.toString());
			// ceil fun 6 feb
			for (int i = 0; i < prodMixingReqP1.size(); i++) {

				System.out.println("total === " + prodMixingReqP1.get(i).getTotal());

				prodMixingReqP1.get(i).setTotal((int) Math.ceil(prodMixingReqP1.get(i).getTotal()));

				System.out.println("total seiled new  " + prodMixingReqP1.get(i).getTotal());

			}

			TempMixing tempMx = null;
			/*
			 * for (int i = 0; i < prodMixingReqP1.size(); i++) {
			 * 
			 * ProdMixingReqP1 planMixing = prodMixingReqP1.get(i);
			 * 
			 * tempMx = new TempMixing();
			 * 
			 * tempMx.setQty(planMixing.getTotal()*planMixing.getMulFactor());
			 * 
			 * tempMx.setRmId(planMixing.getRmId()); tempMx.setSfId(0);
			 * 
			 * tempMx.setProdHeaderId(globalHeaderId);
			 * 
			 * tempMixing.add(tempMx); }
			 * 
			 * System.out.println("temp Mix List " + tempMixing.toString());
			 * 
			 * Info info = restTemplate.postForObject(Constants.url + "insertTempMixing",
			 * tempMixing, Info.class);
			 * 
			 * map = new LinkedMultiValueMap<String, Object>();
			 * 
			 * map.add("prodHeaderId", globalHeaderId);
			 * 
			 * getTempMixItemDetailList = restTemplate.postForObject(Constants.url +
			 * "getTempMixItemDetail", map, GetTempMixItemDetailList.class);
			 * 
			 * tempMixItemDetail = getTempMixItemDetailList.getTempMixItemDetail();
			 * 
			 * System.out.println("temp Mix Item Detail  " + tempMixItemDetail.toString());
			 * 
			 * // Calculations
			 */
			/*
			 * boolean isSameItem = false; ProdMixingReqP1 newItem = null;
			 * 
			 * for (int j = 0; j < tempMixItemDetail.size(); j++) {
			 * 
			 * GetTempMixItemDetail tempMixItem = tempMixItemDetail.get(j);
			 * 
			 * for (int i = 0; i < prodMixingReqP1.size(); i++) {
			 * 
			 * ProdMixingReqP1 planMixing = prodMixingReqP1.get(i);
			 * 
			 * if (tempMixItem.getRmId() == planMixing.getRmId()) {
			 * 
			 * planMixing.setTotal((int)(Math.ceil(planMixing.getTotal() +
			 * tempMixItem.getTotal())));
			 * 
			 * isSameItem = true;
			 * 
			 * } } if (isSameItem == false) {
			 * 
			 * newItem = new ProdMixingReqP1();
			 * 
			 * newItem.setRmName(tempMixItem.getRmName());
			 * newItem.setRmType(tempMixItem.getRmType());
			 * newItem.setRmId(tempMixItem.getSfId());
			 * newItem.setTotal((int)Math.ceil(tempMixItem.getTotal()));
			 * newItem.setUom(tempMixItem.getUom());
			 * 
			 * prodMixingReqP1.add(newItem);
			 * 
			 * } //isSameItem=false; }
			 */
			System.out.println("Final List " + prodMixingReqP1.toString());

			// mav.addObject("mixingList", sfPlanDetailForMixing);
			// mav.addObject("mixingList", prodMixingReqP1);
			mav.addObject("productionBatch", globalProductionBatch);
			mav.addObject("globalTimeSlot", globalTimeSlot);
			mav.addObject("productionId", productionId);
			mav.addObject("isMixing", isMixing);
			ModelAndView model = new ModelAndView("production/addBom");
			model.addObject("prodDate", globalProdDate);
		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("Ex oc");
		}

		return mav;

	}

	@RequestMapping(value = "/addMixingreqst", method = RequestMethod.POST)
	public String addMixingreqst(HttpServletRequest request, HttpServletResponse response) {
		// Constants.mainAct = 17;
		// Constants.subAct=184;

		RestTemplate restTemplate = new RestTemplate();

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		System.out.println("Inside add Mixing request ");
		int timeSlot = Integer.parseInt(request.getParameter("globalTimeSlot"));
		String globalProductionBatch = request.getParameter("globalProductionBatch");
		int prodId = Integer.parseInt(request.getParameter("productionId"));
		// new code 31 Jan
		int count = 0;
		for (int i = 0; i < prodMixingReqP1.size(); i++) {

			System.out.println("in second for ");
			float prod_qty = Float.parseFloat(request.getParameter("editQty" + count));
			System.out.println("prodqty for element  " + count + " " + prod_qty);
			prodMixingReqP1.get(i).setPrevTotal(prodMixingReqP1.get(i).getTotal());
			prodMixingReqP1.get(i).setTotal(prod_qty);
			count++;
		}

		TempMixing tempMx = null;

		ProdMixingReqP1 planMixing;
		for (int i = 0; i < prodMixingReqP1.size(); i++) {

			planMixing = new ProdMixingReqP1();
			planMixing = prodMixingReqP1.get(i);

			tempMx = new TempMixing();

			tempMx.setQty(planMixing.getTotal() * planMixing.getMulFactor());

			tempMx.setRmId(planMixing.getRmId());
			tempMx.setSfId(0);
			System.out.println("temp item data " + tempMx.getQty());

			tempMx.setProdHeaderId(globalHeaderId);

			tempMixing.add(tempMx);
		}

		System.out.println("temp Mix List : Inserted Into Temp Mixing table " + tempMixing.toString());

		Info info = restTemplate.postForObject(Constants.url + "insertTempMixing", tempMixing, Info.class);

		map = new LinkedMultiValueMap<String, Object>();

		map.add("prodHeaderId", globalHeaderId);

		getTempMixItemDetailList = restTemplate.postForObject(Constants.url + "getTempMixItemDetail", map,
				GetTempMixItemDetailList.class);

		tempMixItemDetail = getTempMixItemDetailList.getTempMixItemDetail();

		System.out.println("temp Mix Item Detail:Received from temp table:  " + tempMixItemDetail.toString());

		// Calculations

		System.out.println("calc Started ");
		boolean isSameItem = false;
		ProdMixingReqP1 newItem = new ProdMixingReqP1();
		ProdMixingReqP1 planMixingP1 = new ProdMixingReqP1();

		GetTempMixItemDetail tempMixItem = new GetTempMixItemDetail();
		for (int j = 0; j < tempMixItemDetail.size(); j++) {

			tempMixItem = new GetTempMixItemDetail();

			tempMixItem = tempMixItemDetail.get(j);

			for (int i = 0; i < prodMixingReqP1.size(); i++) {
				planMixingP1 = new ProdMixingReqP1();
				planMixingP1 = prodMixingReqP1.get(i);

				if (tempMixItem.getRmId() == planMixingP1.getRmId()) {

					planMixingP1.setTotal((int) (Math.ceil(planMixingP1.getTotal() + tempMixItem.getTotal())));

					isSameItem = true;

				}
			}
			if (isSameItem == false) {

				newItem = new ProdMixingReqP1();
				System.out.println("New Rm Item Found " + tempMixItem.getSfId() + "Name" + tempMixItem.getRmName());
				newItem.setRmName(tempMixItem.getRmName());
				newItem.setRmType(tempMixItem.getRmType());
				newItem.setRmId(tempMixItem.getRmId());
				newItem.setTotal((int) Math.ceil(tempMixItem.getTotal()));// do not multiply mulFact
				newItem.setUom(tempMixItem.getUom());
				newItem.setMulFactor(tempMixItem.getMulFactor());
				newItem.setPrevTotal(tempMixItem.getTotal());

				prodMixingReqP1.add(newItem);

			}
			isSameItem = false;
		}

		System.out.println("Final List in Insert called  " + prodMixingReqP1.toString());

		// end of new code

		MixingHeader mixingHeader = new MixingHeader();
		System.out.println(
				"globalTimeSlot " + globalTimeSlot + "globalProductionBatch  " + globalProductionBatch + "editQty");

		Date date = new Date();

		mixingHeader.setMixId(0);
		mixingHeader.setMixDate(date);
		mixingHeader.setProductionId(prodId);
		mixingHeader.setProductionBatch(globalProductionBatch);
		mixingHeader.setStatus(0);
		mixingHeader.setDelStatus(0);
		mixingHeader.setTimeSlot(timeSlot);
		mixingHeader.setIsBom(0);
		mixingHeader.setExBool1(0);
		mixingHeader.setExInt2(0);
		mixingHeader.setExInt3(0);
		mixingHeader.setExVarchar1("");
		mixingHeader.setExVarchar2("");
		mixingHeader.setExVarchar3("");

		List<MixingDetailed> addmixingDetailedlist = new ArrayList<MixingDetailed>();

		for (int i = 0; i < prodMixingReqP1.size(); i++) {
			System.out.println("in for ");
			MixingDetailed mixingDetailed = new MixingDetailed();
			mixingDetailed.setMixing_detailId(0);
			mixingDetailed.setMixingId(0);
			mixingDetailed.setSfId(prodMixingReqP1.get(i).getRmId());

			mixingDetailed.setSfName(prodMixingReqP1.get(i).getRmName());
			mixingDetailed.setReceivedQty(prodMixingReqP1.get(i).getTotal());

			mixingDetailed.setUom(prodMixingReqP1.get(i).getUom());
			mixingDetailed.setMixingDate(date);
			mixingDetailed.setExBool1(0);
			mixingDetailed.setExInt2((int) prodMixingReqP1.get(i).getMulFactor());// temp it is typecasted to insert
																					// mulfactor
			mixingDetailed.setExInt1(0);
			mixingDetailed.setExInt3(0);
			mixingDetailed.setExVarchar1("");
			mixingDetailed.setExVarchar2("");
			mixingDetailed.setExVarchar3("");

			mixingDetailed.setOriginalQty(prodMixingReqP1.get(i).getPrevTotal());// new field 22 Jan

			mixingDetailed
					.setAutoOrderQty(prodMixingReqP1.get(i).getMulFactor() * prodMixingReqP1.get(i).getPrevTotal());// new
																													// field
			addmixingDetailedlist.add(mixingDetailed);

		}
		// int count = 0;
		/*
		 * for (int i = 0; i < addmixingDetailedlist.size(); i++) {
		 * System.out.println("in second for "); float prod_qty =
		 * Float.parseFloat(request.getParameter("editQty" + count));
		 * System.out.println("prodqty  " + prod_qty); //
		 * addmixingDetailedlist.get(i).setProductionQty(prod_qty);
		 * addmixingDetailedlist.get(i).setReceivedQty(prod_qty); count++; }
		 */

		mixingHeader.setMixingDetailed(addmixingDetailedlist);
		System.out.println("while inserting Mixing Header = " + mixingHeader.toString());
		RestTemplate rest = new RestTemplate();
		MixingHeader mixingHeaderin = rest.postForObject(Constants.url + "insertMixingHeaderndDetailed", mixingHeader,
				MixingHeader.class);
		map = new LinkedMultiValueMap<String, Object>();
		map.add("productionId", prodId);
		map.add("flag", 0);
		if (mixingHeaderin != null) {
			int updateisMixing = rest.postForObject(Constants.url + "updateisMixingandBom", map, Integer.class);
		}
		System.out.println(mixingHeaderin.toString());

		return "redirect:/getMixingListByProduction";

	}

}
