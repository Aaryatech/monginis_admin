package com.ats.adminpanel.controller;

import java.awt.Desktop;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.net.URLConnection;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
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
import org.zefer.html.doc.q;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.commons.DateConvertor;
import com.ats.adminpanel.model.AllFrIdNameList;
import com.ats.adminpanel.model.AllRoutesListResponse;
import com.ats.adminpanel.model.ExportToExcel;
import com.ats.adminpanel.model.Info;
import com.ats.adminpanel.model.Route;
import com.ats.adminpanel.model.Variance;
import com.ats.adminpanel.model.VarianceList;
import com.ats.adminpanel.model.franchisee.AllMenuResponse;
import com.ats.adminpanel.model.franchisee.FrNameIdByRouteId;
import com.ats.adminpanel.model.franchisee.FrNameIdByRouteIdResponse;
import com.ats.adminpanel.model.franchisee.Menu;
import com.ats.adminpanel.model.franchisee.SubCategory;
import com.ats.adminpanel.model.item.AllItemsListResponse;
import com.ats.adminpanel.model.item.CategoryListResponse;
import com.ats.adminpanel.model.item.Item;
import com.ats.adminpanel.model.item.MCategoryList;
import com.ats.adminpanel.model.itextpdf.FooterTable;
import com.ats.adminpanel.model.production.GetOrderItemQty;
import com.ats.adminpanel.model.production.GetProdDetailBySubCat;
import com.ats.adminpanel.model.production.GetProdDetailBySubCatList;
import com.ats.adminpanel.model.production.GetRegSpCakeOrderQty;
import com.ats.adminpanel.model.production.PostProdPlanHeader;
import com.ats.adminpanel.model.production.PostProductionDetail;
import com.ats.adminpanel.model.production.PostProductionHeader;
import com.ats.adminpanel.model.production.PostProductionPlanDetail;
import com.ats.adminpanel.model.production.ProductionBarcode;
import com.ats.adminpanel.model.production.SubCatwiseVariancePdf;
import com.ats.adminpanel.model.production.TempProdPlanDetail;
import com.ats.adminpanel.model.production.UpdateOrderStatus;
import com.ats.adminpanel.model.productionplan.MixingDetailed;
import com.ats.adminpanel.model.salesreport.OrderFromProdPdfView;
import com.ats.adminpanel.model.stock.FinishedGoodStock;
import com.ats.adminpanel.model.stock.FinishedGoodStockDetail;
import com.ats.adminpanel.model.stock.GetCurProdAndBillQty;
import com.ats.adminpanel.model.stock.GetCurProdAndBillQtyList; 
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Header;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEvent;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.Path;
import com.itextpdf.text.pdf.parser.clipper.Paths;

//import javafx.scene.text.TextAlignment;

@Controller
@Scope("session")
public class ProductionController {

	// AllFrIdNameList allFrIdNameList;
	List<Menu> menuList;
	public List<MCategoryList> categoryList;
	public String selectedCat;
	public String productionDate;
	public List<GetRegSpCakeOrderQty> getRegSpCakeOrderQtyList;
	public List<GetOrderItemQty> getOrderItemQtyList;
	public int[] timeSlot;
	List<ProductionBarcode> barcodeList = new ArrayList<ProductionBarcode>();;
	int selCate;
	ArrayList<Item> itemList = new ArrayList<>();
	GetCurProdAndBillQtyList getCurProdAndBillQtyList = new GetCurProdAndBillQtyList();
	String date;

	@RequestMapping(value = "/showproduction", method = RequestMethod.GET)
	public ModelAndView showProdForcasting(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("production/production");
		Constants.mainAct = 4;
		Constants.subAct = 32;

	

		RestTemplate restTemplate = new RestTemplate();

		CategoryListResponse categoryListResponse = restTemplate.getForObject(Constants.url + "showAllCategory",
				CategoryListResponse.class);

		categoryList = categoryListResponse.getmCategoryList();
		// allFrIdNameList = new AllFrIdNameList();
		System.out.println("Category list  " + categoryList);
		int productionTimeSlot = 0;
		try {

			productionTimeSlot = restTemplate.getForObject(Constants.url + "getProductionTimeSlot", Integer.class);
			System.out.println("time slot  " + productionTimeSlot);
		} catch (Exception e) {
			// System.out.println(e.getMessage());
			// e.printStackTrace();

		}

		timeSlot = new int[productionTimeSlot];
		for (int i = 0; i < productionTimeSlot; i++)
			timeSlot[i] = i + 1;
		model.addObject("todayDate", new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
		model.addObject("unSelectedCatList", categoryList);
		model.addObject("productionTimeSlot", timeSlot);

		return model;
	}

	@RequestMapping(value = "/prodBarcodePrinting", method = RequestMethod.GET)
	public ModelAndView prodBarcodePrinting(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("production/prodBarcode");
		Constants.mainAct = 4;
		Constants.subAct = 32;

		barcodeList = new ArrayList<ProductionBarcode>();
		
		RestTemplate restTemplate = new RestTemplate();

		CategoryListResponse categoryListResponse = restTemplate.getForObject(Constants.url + "showAllCategory",
				CategoryListResponse.class);

		categoryList = categoryListResponse.getmCategoryList();
		// allFrIdNameList = new AllFrIdNameList();
		System.out.println("Category list  " + categoryList);

		model.addObject("unSelectedCatList", categoryList);
Date  todayDate=new Date();

DateFormat df=new SimpleDateFormat("dd-MM-yyyy");
model.addObject("todayDate",df.format(todayDate));
		return model;
	}

	@RequestMapping(value = "/getProductionOrderBarcode", method = RequestMethod.GET)
	public @ResponseBody List<ProductionBarcode> getProductionOrderBarcode(HttpServletRequest request,
			HttpServletResponse response) {

		date = request.getParameter("productionDate");
		int cat = Integer.parseInt(request.getParameter("selectedCat"));

		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
		String ds2 = null;
		try {
			ds2 = sdf1.format(sdf2.parse(date));
		} catch (ParseException e) {

			e.printStackTrace();
		}
		System.out.println(ds2);

		barcodeList = new ArrayList<ProductionBarcode>();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		RestTemplate rest = new RestTemplate();
		map.add("catId", cat);
		map.add("date", ds2);

		System.out.println("map " + map.toString());
		ParameterizedTypeReference<List<ProductionBarcode>> typeRef = new ParameterizedTypeReference<List<ProductionBarcode>>() {
		};
		ResponseEntity<List<ProductionBarcode>> responseEntity = rest.exchange(Constants.url + "getItemForBarcode",
				HttpMethod.POST, new HttpEntity<>(map), typeRef);

		barcodeList = responseEntity.getBody();

		System.out.println("barcode data " + barcodeList.toString());
		return barcodeList;
	}

	@RequestMapping(value = "/getItemByCatId", method = RequestMethod.GET)
	public @ResponseBody ArrayList<Item> getItemsByCatId(HttpServletRequest request, HttpServletResponse response) {

		String date = request.getParameter("productionDate");
		int cat = Integer.parseInt(request.getParameter("selectedCat"));

		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
		String ds2 = null;
		try {
			ds2 = sdf1.format(sdf2.parse(date));
		} catch (ParseException e) {

			e.printStackTrace();
		}
		System.out.println(ds2);

		RestTemplate restTemplate = new RestTemplate();

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("itemGrp1", cat);

		Item[] item = restTemplate.postForObject(Constants.url + "getItemsByCatIdAndSortId", map, Item[].class);
		itemList.clear();

		itemList = new ArrayList<Item>(Arrays.asList(item));
		System.out.println("Filter Item List " + itemList.toString());
		
		return itemList;
	}

	@RequestMapping(value = "/addNewItemToList", method = RequestMethod.GET)
	public @ResponseBody List<ProductionBarcode> addNewItemToList(HttpServletRequest request,
			HttpServletResponse response) {

		date = request.getParameter("productionDate");
		int cat = Integer.parseInt(request.getParameter("selectedCat"));
		int id = Integer.parseInt(request.getParameter("selectedItem"));
		int qty = Integer.parseInt(request.getParameter("qty"));

		for (Item item : itemList) {

			if (id == item.getId()) {

				ProductionBarcode barcode = new ProductionBarcode();
				barcode.setItemCode(item.getItemId());
				barcode.setItemId(item.getId());
				barcode.setItemName(item.getItemName());
				barcode.setProductionQty(qty);

				barcodeList.add(barcode);
			}

		}

		System.out.println("barcode data " + barcodeList.toString());
		return barcodeList;
	}

	
	@RequestMapping(value = "/getEditedList", method = RequestMethod.GET)
	public @ResponseBody List<ProductionBarcode> getEditedList(HttpServletRequest request,
			HttpServletResponse response) {
		
		int key = Integer.parseInt(request.getParameter("key"));
		int qty = Integer.parseInt(request.getParameter("qty"));
		
		System.err.println("key = " +key + "Qty = " +qty);
		
		if(qty>0) {
		barcodeList.get(key).setProductionQty(qty);
		System.err.println("Item Qty Changed ");
		}
		else	{	
			barcodeList.remove(key);
		System.err.println("Item removed ");
		}

				return barcodeList;

	}
	
	
	
	
	@RequestMapping(value = "/submitProductionBarcode", method = RequestMethod.POST)
	public void submitProductionBarcode(HttpServletRequest request, HttpServletResponse response)
			throws FileNotFoundException {

		File file = new File("prod.txt");

		try (Writer writer = new BufferedWriter(new FileWriter(file))) {

			for (ProductionBarcode prod : barcodeList) {

				int q = prod.getProductionQty();

				for (int i = 1; i <= q; i++) {

					/*
					 * SIZE 47.5 mm, 24.5 mm SPEED 3 DENSITY 17 SET RIBBON ON DIRECTION 0,0
					 * REFERENCE 0,0 OFFSET 0 mm SET PEEL OFF SET CUTTER OFF SET TEAR ON CLS
					 * CODEPAGE 850 TEXT 347,135,"ROMAN.TTF",180,1,10,"Item Code" TEXT
					 * 235,145,"ROMAN.TTF",180,1,16,<item_code> BARCODE
					 * 347,100,"128M",54,0,180,2,4,<item_code> TEXT
					 * 347,40,"ROMAN.TTF",180,1,10,<item_name> TEXT 39,50,"ROMAN.TTF",90,1,8,<date>
					 * PRINT 1,1
					 * 
					 */

					// FRUITCAKES

					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
					LocalDate localDate = LocalDate.parse(date, formatter);
					int dayOfMonth = localDate.getDayOfMonth();

					String number = String.valueOf(dayOfMonth);

					String encryptDate = "";

					for (int d = 0; d < number.length(); d++) {
						int j = Character.digit(number.charAt(d), 10);
						System.out.println("digit: " + j);

						switch (j) {
						case 0:
							encryptDate = encryptDate + "F";
							break;
						case 1:
							encryptDate = encryptDate + "R";
							break;
						case 2:
							encryptDate = encryptDate + "U";
							break;
						case 3:
							encryptDate = encryptDate + "I";
							break;
						case 4:
							encryptDate = encryptDate + "T";
							break;
						case 5:
							encryptDate = encryptDate + "C";
							break;
						case 6:
							encryptDate = encryptDate + "A";
							break;
						case 7:
							encryptDate = encryptDate + "K";
							break;
						case 8:
							encryptDate = encryptDate + "E";
							break;
						case 9:
							encryptDate = encryptDate + "S";
							break;

						}

					}

					System.out.println("encryptedDate " + encryptDate);
					
					String prodItemName= prod.getItemName();
					
					int l=prodItemName.length();
					
					String itemName="";
					if(l>8) {
					 itemName=prodItemName.substring(0, 7);
					}else {
						itemName=prodItemName;
					}

					String generalSetting = "SIZE 47.5 mm, 24.5 mm" + System.getProperty("line.separator") + "SPEED 3"
							+ System.getProperty("line.separator") + "DENSITY 17" + System.getProperty("line.separator")
							+ "SET RIBBON ON" + System.getProperty("line.separator") + "DIRECTION 0,0"
							+ System.getProperty("line.separator") + "REFERENCE 0,0"
							+ System.getProperty("line.separator") + "OFFSET 0 mm"
							+ System.getProperty("line.separator") + "SET PEEL OFF"
							+ System.getProperty("line.separator") + "SET TEAR ON"
							+ System.getProperty("line.separator") + "CLS" + System.getProperty("line.separator")
							+ "CODEPAGE 850" + System.getProperty("line.separator")
							+ "TEXT 347,135,\"ROMAN.TTF\",180,1,10,\"Item Code\"";

					String barcode = generalSetting + System.getProperty("line.separator")
							+ "TEXT 235,145,\"ROMAN.TTF\",180,1,16,\"" + prod.getItemCode() + "\"" 
							+ System.getProperty("line.separator") + "BARCODE 347,100,\"128M\",54,0,180,2,4,\""
							+ prod.getItemCode() + "\"";
 
					String text = barcode + System.getProperty("line.separator")
							+ "TEXT 347,40,\"ROMAN.TTF\",180,1,10,\"" + itemName + "\"";

					String date = text + System.getProperty("line.separator") + "TEXT 39,50,\"ROMAN.TTF\",90,1,8,\""
							+ encryptDate + "\"";

					String contents = date + System.getProperty("line.separator") + "PRINT 1,1"
							+ System.getProperty("line.separator");

					writer.write(contents);

				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("file " + file.getAbsolutePath());

		String mimeType = URLConnection.guessContentTypeFromName(file.getName());

		if (mimeType == null) {

			System.out.println("mimetype is not detectable");
			mimeType = "application/octet-stream";

		}

		System.out.println("mimetype : " + mimeType);

		response.setContentType(mimeType);

		response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));

		// response.setHeader("Content-Disposition", String.format("attachment;
		// filename=\"%s\"", file.getName()));

		response.setContentLength((int) file.length());

		InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

		try {
			FileCopyUtils.copy(inputStream, response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/getMenu", method = RequestMethod.GET)
	public @ResponseBody List<Menu> getMenu(HttpServletRequest request, HttpServletResponse response) {

		selectedCat = request.getParameter("selectedCat");
		selCate = Integer.parseInt(request.getParameter("selectedCat"));

		// selCate=Integer.parseInt(request.getParameter("selectedCat"));

		System.out.println("Inside getMenu seleCatId VAlue " + selCate);

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		RestTemplate rest = new RestTemplate();

		map.add("catId", selectedCat);

		try {

			AllMenuResponse allMenuResponse = rest.postForObject(Constants.url + "getMenuByCat", map,
					AllMenuResponse.class);

			menuList = allMenuResponse.getMenuConfigurationPage();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println("List of Menu : " + menuList.toString());

		return menuList;

	}

	@RequestMapping(value = "/getProductionOrder", method = RequestMethod.GET)
	public @ResponseBody List<GetOrderItemQty> generateOrderList(HttpServletRequest request,
			HttpServletResponse response) {

		getOrderItemQtyList = new ArrayList<GetOrderItemQty>();

		productionDate = request.getParameter("productionDate");
		String selectedMenuList = request.getParameter("selectedMenu_list");
		System.out.println("selectedMenuList" + selectedMenuList.toString());
		if (selectedMenuList.contains("-1")) {
			System.out.println("selectedMenuList" + selectedMenuList.toString());

			List<String> ids = new ArrayList<>();
			for (int i = 0; i < menuList.size(); i++) {
				ids.add(String.valueOf(menuList.get(i).getMenuId()));
			}
			String idList = ids.toString();
			selectedMenuList = idList.substring(1, idList.length() - 1);
			selectedMenuList = selectedMenuList.replaceAll("\"", "");
			System.out.println("selectedMenuList" + selectedMenuList.toString());
		} else {
			selectedMenuList = selectedMenuList.substring(1, selectedMenuList.length() - 1);
			selectedMenuList = selectedMenuList.replaceAll("\"", "");
		}
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		RestTemplate rest = new RestTemplate();

		map.add("productionDate", productionDate);
		map.add("menuId", selectedMenuList);
		try {
			ParameterizedTypeReference<List<GetOrderItemQty>> typeRef = new ParameterizedTypeReference<List<GetOrderItemQty>>() {
			};
			ResponseEntity<List<GetOrderItemQty>> responseEntity = rest.exchange(Constants.url + "getOrderAllItemQty",
					HttpMethod.POST, new HttpEntity<>(map), typeRef);

			getOrderItemQtyList = responseEntity.getBody();

			// getOrderItemQtyList=rest.postForObject(Constants.url + "getOrderAllItemQty",
			// map, List.class);

			// new code for getting current stock
			RestTemplate restTemplate = new RestTemplate();

			DateFormat dfYmd = new SimpleDateFormat("yyyy-MM-dd");

			map = new LinkedMultiValueMap<String, Object>();
			map.add("stockStatus", 0);

			FinishedGoodStock stockHeader = restTemplate.postForObject(Constants.url + "getFinGoodStockHeader", map,
					FinishedGoodStock.class);

			System.out.println("stock Header " + stockHeader.toString());

			Date stockDate = stockHeader.getFinGoodStockDate();

			List<GetCurProdAndBillQty> getCurProdAndBillQty = new ArrayList<>();
			map = new LinkedMultiValueMap<String, Object>();

			System.out.println("stock date " + stockDate);
			String prodDate = dfYmd.format(stockDate);
			map.add("prodDate", prodDate);
			map.add("catId", selCate);
			map.add("delStatus", 0);
			map.add("timestamp", stockHeader.getTimestamp());

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();

			map.add("curTimeStamp", dateFormat.format(cal.getTime()));

			getCurProdAndBillQtyList = restTemplate.postForObject(Constants.url + "getCurrentProdAndBillQty", map,
					GetCurProdAndBillQtyList.class);

			getCurProdAndBillQty = getCurProdAndBillQtyList.getGetCurProdAndBillQty();

			System.out.println("Cur Prod And Bill Qty Listy " + getCurProdAndBillQty.toString());
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

			String stkDate = df.format(stockDate);
			map = new LinkedMultiValueMap<String, Object>();
			map.add("stockDate", stkDate);

			map.add("catId", selectedCat);
			// RestTemplate restTemplate = new RestTemplate();

			ParameterizedTypeReference<List<FinishedGoodStockDetail>> typeRef1 = new ParameterizedTypeReference<List<FinishedGoodStockDetail>>() {
			};
			ResponseEntity<List<FinishedGoodStockDetail>> responseEntity1 = restTemplate.exchange(
					Constants.url + "getFinGoodStockDetail", HttpMethod.POST, new HttpEntity<>(map), typeRef1);

			List<FinishedGoodStockDetail> finGoodDetail = responseEntity1.getBody();

			System.out.println("Finished Good Stock Detail " + finGoodDetail.toString());

			// new code
			List<FinishedGoodStockDetail> updateStockDetailList = new ArrayList<>();

			FinishedGoodStockDetail stockDetail = new FinishedGoodStockDetail();
			GetCurProdAndBillQty curProdBilQty = new GetCurProdAndBillQty();

			for (int i = 0; i < getCurProdAndBillQty.size(); i++) {

				curProdBilQty = getCurProdAndBillQty.get(i);

				for (int j = 0; j < finGoodDetail.size(); j++) {

					stockDetail = finGoodDetail.get(j);

					if (curProdBilQty.getId() == stockDetail.getItemId()) {

						System.out
								.println("item Id Matched " + curProdBilQty.getId() + "and " + stockDetail.getItemId());

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

			for (int i = 0; i < getOrderItemQtyList.size(); i++) {

				for (int j = 0; j < updateStockDetailList.size(); j++) {
					if (Integer.parseInt(getOrderItemQtyList.get(i).getItemId()) == updateStockDetailList.get(j)
							.getItemId()) {

						getOrderItemQtyList.get(i).setCurClosingQty(updateStockDetailList.get(j).getCloCurrent());
						getOrderItemQtyList.get(i).setCurOpeQty(updateStockDetailList.get(j).getTotalCloStk());
					}
				}
			}
			
			try
			{
				 
					List<ExportToExcel> exportToExcelList=new ArrayList<ExportToExcel>();
					
					ExportToExcel expoExcel=new ExportToExcel();
					List<String> rowData=new ArrayList<String>();
					 
					rowData.add("Sr.No."); 
					rowData.add("Item Id");  
					rowData.add("Item Name");
					rowData.add("Current Stock");
					rowData.add("Order Qty");  
					
					
					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);
					for(int i=0;i<getOrderItemQtyList.size();i++)
					{
						  expoExcel=new ExportToExcel();
						 rowData=new ArrayList<String>();
						 
					 
						 
						 rowData.add(""+(i+1)); 
						rowData.add(""+getOrderItemQtyList.get(i).getItemId());  
						rowData.add(""+getOrderItemQtyList.get(i).getItemName());
						rowData.add(""+getOrderItemQtyList.get(i).getCurOpeQty());
						rowData.add(""+getOrderItemQtyList.get(i).getQty());  
						
						expoExcel.setRowData(rowData);
						exportToExcelList.add(expoExcel);
						 
					}
					 
					
					
					HttpSession session = request.getSession();
					session.setAttribute("exportExcelList", exportToExcelList);
					session.setAttribute("excelName", "savariousProductionList");

					 
				 
			}catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("exception to generate excel ");
			}
			

			// end of new Code

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return getOrderItemQtyList;

	}

	// Pdf for Prod From Order
	/*
	 * @RequestMapping(value = "/showProdByOrderPdf", method = RequestMethod.GET)
	 * public @ResponseBody ByteArrayInputStream
	 * showProdByOrderPdf(HttpServletRequest request, HttpServletResponse response)
	 * {
	 * 
	 * List<GetOrderItemQty> moneyOutList = getOrderItemQtyList;
	 * 
	 * ByteArrayInputStream bis = OrderFromProdPdfView.moneyOutReport(moneyOutList);
	 * 
	 * HttpHeaders headers = new HttpHeaders(); headers.add("Content-Disposition",
	 * "filename=MoneyOutReport.pdf");
	 * 
	 * return ResponseEntity .ok() .headers(headers)
	 * .contentType(MediaType.APPLICATION_PDF) .body(new InputStreamResource(bis));
	 * 
	 * ModelAndView model = new ModelAndView("production/pdf/productionPdf");
	 * model.addObject("prodFromOrderReport",getOrderItemQtyList); return model;
	 * return bis; }doc,new FileOutputStream("report.pdf")); doc.open(); doc.add(new
	 * Para
	 */

	/*
	 * @RequestMapping(value = "/showProdByOrderPdf", method = RequestMethod.GET)
	 * public void showProdByOrderPdf(HttpServletRequest request,
	 * HttpServletResponse response) throws FileNotFoundException {
	 * BufferedOutputStream outStream = null;
	 * System.out.println("Inside Pdf prod From Order"); Document doc=new
	 * Document();
	 * 
	 * 
	 * List<GetOrderItemQty> moneyOutList = getOrderItemQtyList;
	 * 
	 * moneyOutList = getOrderItemQtyList; Document document = new
	 * Document(PageSize.A4); // ByteArrayOutputStream out = new
	 * ByteArrayOutputStream();
	 * 
	 * DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); Calendar
	 * cal = Calendar.getInstance();
	 * 
	 * System.out.println("time in Gen Bill PDF ==" +
	 * dateFormat.format(cal.getTime())); String
	 * timeStamp=dateFormat.format(cal.getTime()); String
	 * FILE_PATH="/home/ats-11/REPORT.pdf"; File file=new File(FILE_PATH);
	 * 
	 * PdfWriter writer = null;
	 * 
	 * 
	 * FileOutputStream out=new FileOutputStream(FILE_PATH); try {
	 * writer=PdfWriter.getInstance(document,out); } catch (DocumentException e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * PdfPTable table = new PdfPTable(6); try {
	 * System.out.println("Inside PDF Table try"); table.setWidthPercentage(100);
	 * table.setWidths(new float[]{0.9f, 1.6f, 1.4f,1.4f,1.4f,1.4f}); Font headFont
	 * = new Font(FontFamily.HELVETICA, 8, Font.ITALIC, BaseColor.BLACK); Font
	 * headFont1 = new Font(FontFamily.HELVETICA, 8, Font.BOLD, BaseColor.BLACK);
	 * Font f=new Font(FontFamily.TIMES_ROMAN,12.0f,Font.UNDERLINE,BaseColor.BLUE);
	 * 
	 * PdfPCell hcell; hcell = new PdfPCell(new Phrase("Sr.No.", headFont1));
	 * hcell.setHorizontalAlignment(Element.ALIGN_CENTER); table.addCell(hcell);
	 * 
	 * hcell = new PdfPCell(new Phrase("Item ID", headFont1));
	 * hcell.setHorizontalAlignment(Element.ALIGN_CENTER); table.addCell(hcell);
	 * 
	 * hcell = new PdfPCell(new Phrase("Item Name", headFont1));
	 * hcell.setHorizontalAlignment(Element.ALIGN_CENTER); table.addCell(hcell);
	 * 
	 * hcell = new PdfPCell(new Phrase("Cur Closing", headFont1));
	 * hcell.setHorizontalAlignment(Element.ALIGN_CENTER); table.addCell(hcell);
	 * 
	 * hcell = new PdfPCell(new Phrase("Cur Opening", headFont1));
	 * hcell.setHorizontalAlignment(Element.ALIGN_CENTER); table.addCell(hcell);
	 * 
	 * hcell = new PdfPCell(new Phrase("Order Quantity", headFont1));
	 * hcell.setHorizontalAlignment(Element.ALIGN_CENTER); table.addCell(hcell);
	 * 
	 * int index=0; for (GetOrderItemQty getMoneyOut : moneyOutList) { index++;
	 * PdfPCell cell;
	 * 
	 * cell = new PdfPCell(new Phrase(String.valueOf(index),headFont));
	 * cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	 * cell.setHorizontalAlignment(Element.ALIGN_CENTER); table.addCell(cell);
	 * 
	 * cell = new PdfPCell(new Phrase(getMoneyOut.getItemId(),headFont));
	 * cell.setPaddingLeft(5); cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	 * cell.setHorizontalAlignment(Element.ALIGN_CENTER); table.addCell(cell);
	 * 
	 * cell = new PdfPCell(new Phrase(getMoneyOut.getItemName(),headFont));
	 * cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	 * cell.setHorizontalAlignment(Element.ALIGN_CENTER); cell.setPaddingRight(5);
	 * table.addCell(cell);
	 * 
	 * cell = new PdfPCell(new
	 * Phrase(String.valueOf(getMoneyOut.getCurClosingQty()),headFont));
	 * cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	 * cell.setHorizontalAlignment(Element.ALIGN_RIGHT); cell.setPaddingRight(5);
	 * table.addCell(cell);
	 * 
	 * cell = new PdfPCell(new
	 * Phrase(String.valueOf(getMoneyOut.getCurOpeQty()),headFont));
	 * cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	 * cell.setHorizontalAlignment(Element.ALIGN_RIGHT); cell.setPaddingRight(5);
	 * table.addCell(cell);
	 * 
	 * cell = new PdfPCell(new
	 * Phrase(String.valueOf(getMoneyOut.getQty()),headFont));
	 * cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	 * cell.setHorizontalAlignment(Element.ALIGN_RIGHT); cell.setPaddingRight(5);
	 * table.addCell(cell);
	 * 
	 * FooterTable footerEvent = new FooterTable(table);
	 * writer.setPageEvent(footerEvent); }
	 * 
	 * document.open(); Paragraph company = new Paragraph("G F P L",f);
	 * company.setAlignment(Element.ALIGN_CENTER); document.add(company);
	 * document.add(new Paragraph(" "));
	 * 
	 * Paragraph heading = new Paragraph("Report");
	 * heading.setAlignment(Element.ALIGN_CENTER); document.add(heading);
	 * 
	 * document.add(new Paragraph(" ")); document.add(table); int
	 * totalPages=writer.getPageNumber(); com.ats.adminpanel.model.itextpdf.Header
	 * event; // = new com.ats.adminpanel.model.itextpdf.Header(); for(int
	 * i=1;i<totalPages;i++) { event = new
	 * com.ats.adminpanel.model.itextpdf.Header(); event.setHeader(new
	 * Phrase(String.format("page %s", i)));
	 * 
	 * writer.setPageEvent(event); }
	 * 
	 * 
	 * FooterTable footerEvent = new FooterTable(table);
	 * 
	 * 
	 * // document.add(new
	 * Paragraph(""+document.setPageCount(document.getPageNumber()));
	 * 
	 * System.out.println("Page no "+totalPages);
	 * 
	 * // document.addHeader("Page" ,String.valueOf(totalPages)); //
	 * writer.setPageEvent((PdfPageEvent) new Phrase());
	 * 
	 * document.close();
	 * 
	 * Desktop d=Desktop.getDesktop();
	 * 
	 * if(file.exists()) { try { d.open(file); } catch (IOException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } }
	 * 
	 * } catch (DocumentException ex) {
	 * 
	 * System.out.println("Pdf Generation Error: Prod From Orders");
	 * 
	 * }
	 * 
	 * ModelAndView model = new ModelAndView("production/pdf/productionPdf");
	 * model.addObject("prodFromOrderReport",getOrderItemQtyList);
	 * 
	 * 
	 * }
	 */

	@RequestMapping(value = "/getProductionRegSpCakeOrder", method = RequestMethod.GET)
	public @ResponseBody List<GetRegSpCakeOrderQty> generateRegSpCakeOrderList(HttpServletRequest request,
			HttpServletResponse response) {

		getRegSpCakeOrderQtyList = new ArrayList<GetRegSpCakeOrderQty>();

		String productionDate = request.getParameter("productionDate");
		String selectedMenuList = request.getParameter("selectedMenu_list");

		selectedMenuList = selectedMenuList.substring(1, selectedMenuList.length() - 1);
		selectedMenuList = selectedMenuList.replaceAll("\"", "");

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		// List<String> selectedList=new ArrayList<>();
		// selectedList=Arrays.asList(selectedMenuList.split(","));

		RestTemplate rest = new RestTemplate();

		map.add("productionDate", productionDate);
		map.add("menuId", selectedMenuList);
		try {
			ParameterizedTypeReference<List<GetRegSpCakeOrderQty>> typeRef = new ParameterizedTypeReference<List<GetRegSpCakeOrderQty>>() {
			};
			ResponseEntity<List<GetRegSpCakeOrderQty>> responseEntity = rest.exchange(
					Constants.url + "getOrderQtyRegSpCakeAllItems", HttpMethod.POST, new HttpEntity<>(map), typeRef);

			getRegSpCakeOrderQtyList = responseEntity.getBody();
			// getRegSpCakeOrderQtyList=rest.postForObject(Constants.url +
			// "getOrderQtyRegSpCakeAllItems", map, List.class);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println("List of Orders : " + getRegSpCakeOrderQtyList.toString());

		return getRegSpCakeOrderQtyList;

	}

	java.sql.Date convertedDate;

	@RequestMapping(value = "/submitProduction", method = RequestMethod.POST)
	public String submitProduction(HttpServletRequest request, HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("production/production");

		// String productionDate=request.getParameter("production_date");
		String selectTime = request.getParameter("selectTime");
		String convertedDate = null;

		if (productionDate != null && productionDate != "" && selectTime != null && selectTime != "") {
			try {
				SimpleDateFormat ymdSDF = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat dmySDF = new SimpleDateFormat("dd-MM-yyyy");
				Date dmyDate = dmySDF.parse(productionDate);

				convertedDate = ymdSDF.format(dmyDate);

			} catch (ParseException e) {

				e.printStackTrace();
			}

			int timeSlot = Integer.parseInt(selectTime);

			System.out.println("Date  :  " + convertedDate);
			for (int i = 0; i < getOrderItemQtyList.size(); i++) {

				System.out.println("item  Id " + getOrderItemQtyList.get(i).getItemId());
			}

			RestTemplate restTemplate = new RestTemplate();

			PostProductionHeader postProductionHeader = new PostProductionHeader();

			postProductionHeader.setTimeSlot(timeSlot);
			postProductionHeader.setItemGrp1(Integer.parseInt(selectedCat));
			postProductionHeader.setProductionDate(convertedDate);
			postProductionHeader.setDelStatus(0);
			postProductionHeader.setIsBom(0);

			postProductionHeader.setIsMixing(0);
			postProductionHeader.setIsPlanned(0);
			postProductionHeader.setProductionBatch("");
			postProductionHeader.setProductionStatus(2);

			List<PostProductionDetail> postProductionDetailList = new ArrayList<>();
			PostProductionDetail postProductionDetail;

			System.out.println("List    :" + getOrderItemQtyList);
			List<String> orderId = new ArrayList<String>();

			for (int i = 0; i < getOrderItemQtyList.size(); i++) {
				postProductionDetail = new PostProductionDetail();
				String a = getOrderItemQtyList.get(i).getItemId();
				// System.out.println("a============"+a);

				postProductionDetail.setItemId(Integer.parseInt(a));

				postProductionDetail.setOrderQty(getOrderItemQtyList.get(i).getQty());
				postProductionDetail.setProductionDate(convertedDate);
				postProductionDetail.setProductionQty(0);
				postProductionDetail.setProductionBatch("");
				postProductionDetail.setRejectedQty(0);
				postProductionDetail.setPlanQty(0);

				postProductionDetailList.add(postProductionDetail);

				orderId.add(String.valueOf(getOrderItemQtyList.get(i).getOrderId()));
			}

			List<Integer> regOrderId = new ArrayList<Integer>();
			regOrderId.add(0);
			for (int i = 0; i < getRegSpCakeOrderQtyList.size(); i++) {
				postProductionDetail = new PostProductionDetail();

				postProductionDetail.setItemId(getRegSpCakeOrderQtyList.get(i).getItemId());
				postProductionDetail.setOrderQty(getRegSpCakeOrderQtyList.get(i).getQty());
				postProductionDetail.setProductionDate(convertedDate);
				postProductionDetail.setOpeningQty(0);
				postProductionDetail.setProductionQty(0);
				postProductionDetail.setRejectedQty(0);
				postProductionDetail.setProductionBatch("");
				postProductionDetail.setPlanQty(0);
				postProductionDetailList.add(postProductionDetail);

				regOrderId.add(getRegSpCakeOrderQtyList.get(i).getItemId());
			}

			postProductionHeader.setPostProductionDetail(postProductionDetailList);
			try {

				Info info = restTemplate.postForObject(Constants.url + "postProduction", postProductionHeader,
						Info.class);

				System.out.println("Info After post to production :   " + info.toString());

				UpdateOrderStatus updateOrderStatus = new UpdateOrderStatus();
				List<String> res = new ArrayList<String>();
				res.add("0");
				for (int i = 0; i < getOrderItemQtyList.size(); i++) {
					String orderId1 = getOrderItemQtyList.get(i).getItemId();
					res.add(orderId1);
				}
				updateOrderStatus.setOrderItemId(res);
				updateOrderStatus.setRegOrderItemId(regOrderId);
				updateOrderStatus.setProdDate(convertedDate);

				info = restTemplate.postForObject(Constants.url + "updateIsBillGenerate", updateOrderStatus,
						Info.class);

				System.out.println("Info After update status  :    " + info.toString());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			// model.addObject("unSelectedCatList", categoryList);
			// model.addObject("productionTimeSlot", timeSlot);
		}
		return "redirect:/showproduction";
	}

	@RequestMapping(value = "/addForecasting", method = RequestMethod.GET)
	public ModelAndView showAddProdForcasting(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("production/addProdForecasting");
		// Constants.mainAct = 8;
		// Constants.subAct = 82;

		RestTemplate restTemplate = new RestTemplate();

		CategoryListResponse categoryListResponse = restTemplate.getForObject(Constants.url + "showAllCategory",
				CategoryListResponse.class);

		categoryList = categoryListResponse.getmCategoryList();
		// allFrIdNameList = new AllFrIdNameList();
		System.out.println("Category list  " + categoryList);
		int productionTimeSlot = 0;
		try {

			productionTimeSlot = restTemplate.getForObject(Constants.url + "getProductionTimeSlot", Integer.class);
			System.out.println("time slot  " + productionTimeSlot);
		} catch (Exception e) {
			// System.out.println(e.getMessage());
			// e.printStackTrace();

		}

		timeSlot = new int[productionTimeSlot];
		for (int i = 0; i < productionTimeSlot; i++)
			timeSlot[i] = i + 1;

		model.addObject("unSelectedCatList", categoryList);
		model.addObject("productionTimeSlot", timeSlot);

		return model;
	}

	@RequestMapping(value = "/getProdOrderForecating", method = RequestMethod.GET)
	public @ResponseBody List<GetOrderItemQty> getProdOrderForecating(HttpServletRequest request,
			HttpServletResponse response) {

		System.out.println("In method");
		getOrderItemQtyList = new ArrayList<GetOrderItemQty>();

		productionDate = request.getParameter("productionDate");
		String selectedMenuList = request.getParameter("selectedMenu_list");

		selectedMenuList = selectedMenuList.substring(1, selectedMenuList.length() - 1);
		selectedMenuList = selectedMenuList.replaceAll("\"", "");
		String timeSlot = request.getParameter("timeSlot");
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		RestTemplate rest = new RestTemplate();

		map.add("productionDate", productionDate);
		map.add("menuId", selectedMenuList);
		try {
			ParameterizedTypeReference<List<GetOrderItemQty>> typeRef = new ParameterizedTypeReference<List<GetOrderItemQty>>() {
			};
			ResponseEntity<List<GetOrderItemQty>> responseEntity = rest.exchange(Constants.url + "getOrderAllItemQty",
					HttpMethod.POST, new HttpEntity<>(map), typeRef);

			getOrderItemQtyList = responseEntity.getBody();

			// getOrderItemQtyList=rest.postForObject(Constants.url + "getOrderAllItemQty",
			// map, List.class);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println("List of Orders : " + getOrderItemQtyList.toString());

		return getOrderItemQtyList;

	}

	// -----------------------------------------------------Variation--------------------------------------
	List<PostProductionPlanDetail> postProductionPlanDetaillist = new ArrayList<PostProductionPlanDetail>();
	PostProdPlanHeader postProdPlanHeader = new PostProdPlanHeader();
	CategoryListResponse categoryListComp = new CategoryListResponse();
	public List<Item> pdfItemList;
	List<Route> routeList = new ArrayList<Route>();
	AllFrIdNameList allFrIdNameList = new AllFrIdNameList();
	List<Variance> getVarianceorderlistforsort = new ArrayList<Variance>();

	@RequestMapping(value = "/listForVariation", method = RequestMethod.GET)
	public ModelAndView listForVariation(HttpServletRequest request, HttpServletResponse response) {

		postProductionPlanDetaillist = new ArrayList<PostProductionPlanDetail>();
		ModelAndView model = new ModelAndView("production/variation");
		Constants.mainAct = 4;
		Constants.subAct = 35;

		try {
			RestTemplate restTemplate = new RestTemplate();
			List<PostProdPlanHeader> postProdPlanHeader = restTemplate
					.getForObject(Constants.url + "PostProdPlanHeaderVariationlist", List.class);

			categoryListComp = restTemplate.getForObject(Constants.url + "showAllCategory", CategoryListResponse.class);
			System.out.println("categoryListComp " + categoryListComp.getmCategoryList().toString());

			System.out.println("postProdPlanHeader" + postProdPlanHeader.toString());
			model.addObject("postProdPlanHeaderList", postProdPlanHeader);
			model.addObject("categoryList", categoryListComp.getmCategoryList());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/varianceDetailed", method = RequestMethod.GET)
	public ModelAndView varianceDetailed(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("production/variancelistdetailed");
		int productionHeaderId = Integer.parseInt(request.getParameter("productionHeaderId"));
		System.out.println("productionHeaderId" + productionHeaderId);
		try {
			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("planHeaderId", productionHeaderId);

			postProdPlanHeader = restTemplate.postForObject(Constants.url + "PostProdPlanHeaderwithDetailed", map,
					PostProdPlanHeader.class);

			AllItemsListResponse allItemsListResponse = restTemplate
					.getForObject(Constants.url + "getAllItemsBySorting", AllItemsListResponse.class);
			List<Item> itemsList = allItemsListResponse.getItems();

			AllRoutesListResponse allRouteListResponse = restTemplate.getForObject(Constants.url + "showRouteList",
					AllRoutesListResponse.class);

			routeList = new ArrayList<Route>();

			routeList = allRouteListResponse.getRoute();

			// end get Routes

			allFrIdNameList = new AllFrIdNameList();

			allFrIdNameList = restTemplate.getForObject(Constants.url + "getAllFrIdName", AllFrIdNameList.class);
			System.out.println("allFrIdNameList" + allFrIdNameList);
			System.out.println("routeList" + routeList);

			model.addObject("itemsList", itemsList);
			model.addObject("allFrIdNameList", allFrIdNameList.getFrIdNamesList());
			model.addObject("routeList", routeList);
			model.addObject("postProdPlanHeader", postProdPlanHeader);
			model.addObject("categoryList", categoryListComp.getmCategoryList());
			model.addObject("postProdPlanHeaderDetailed", postProdPlanHeader.getPostProductionPlanDetail());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/varianceDetailedCalculation", method = RequestMethod.POST)
	public ModelAndView varianceDetailedCalculation(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("production/calculateVariance");
		try {
			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			String sbForRouteFrId = new String();
			int all = 0;
			String[] frId = request.getParameterValues("fr_id[]");
			String[] rtid = request.getParameterValues("rtid[]");
			System.out.println("rtid " + rtid);
			System.out.println("frId " + frId);
			int flag = 0;

			if (frId != null) {
				if (frId[0].equals("0")) {
					all = 1;
				}
				for (int i = 0; i < frId.length; i++) {
					sbForRouteFrId = sbForRouteFrId.concat(frId[i] + ",");

				}
				System.out.println("sbForRouteFrId" + sbForRouteFrId);
				flag = 0;
			} else {
				for (int j = 0; j < rtid.length; j++) {
					map = new LinkedMultiValueMap<String, Object>();
					map.add("routeId", rtid[j]);

					FrNameIdByRouteIdResponse frNameId = restTemplate.postForObject(
							Constants.url + "getFrNameIdByRouteId", map, FrNameIdByRouteIdResponse.class);

					List<FrNameIdByRouteId> frNameIdByRouteIdList = frNameId.getFrNameIdByRouteIds();

					System.out.println("route wise franchisee " + frNameIdByRouteIdList.toString());

					for (int i = 0; i < frNameIdByRouteIdList.size(); i++) {

						sbForRouteFrId = sbForRouteFrId
								.concat(String.valueOf(frNameIdByRouteIdList.get(i).getFrId()) + ",");
					}
					flag = 1;
				}
			
				System.out.println("fr Id Route WISE = " + sbForRouteFrId);
			}
			model.addObject("flag", flag);
			model.addObject("frId", frId);
			model.addObject("rtid", rtid);
			model.addObject("routeList", routeList);
			model.addObject("allFrIdNameList", allFrIdNameList.getFrIdNamesList());

			int groupType = postProdPlanHeader.getItemGrp1();
			String prodDate = postProdPlanHeader.getProductionDate();
			System.out.println(prodDate);
			String date = DateConvertor.convertToYMD(prodDate);

			map = new LinkedMultiValueMap<String, Object>();
			map.add("Date", date);
			map.add("groupType", groupType);
			map.add("frId", sbForRouteFrId);
			map.add("all", all);
			System.out.println("map" + map);
			VarianceList getQtyforVariance = restTemplate.postForObject(Constants.url + "getQtyforVariance", map,
					VarianceList.class);

			System.out.println(getQtyforVariance.getVarianceorderlist().size() + "getQtyforVariance"
					+ getQtyforVariance.getVarianceorderlist().toString());
			System.out.println("postProdPlanHeader" + postProdPlanHeader.toString());

			postProductionPlanDetaillist = postProdPlanHeader.getPostProductionPlanDetail();

			model.addObject("getQtyforVariance", getQtyforVariance.getVarianceorderlist());
			System.out.println("unsort size " + getQtyforVariance.getVarianceorderlist().size());

			// new Code
			List<FinishedGoodStockDetail> updateStockDetailList = new ArrayList<>();

			try {
				DateFormat dfYmd = new SimpleDateFormat("yyyy-MM-dd");
				map = new LinkedMultiValueMap<String, Object>();
				map = new LinkedMultiValueMap<String, Object>();
				map.add("stockStatus", 0);

				FinishedGoodStock stockHeader = restTemplate.postForObject(Constants.url + "getFinGoodStockHeader", map,
						FinishedGoodStock.class);

				System.out.println("stock Header for varience detailed  " + stockHeader.toString());

				Date stockDate = stockHeader.getFinGoodStockDate();

				List<GetCurProdAndBillQty> getCurProdAndBillQty = new ArrayList<>();
				map = new LinkedMultiValueMap<String, Object>();

				System.out.println("stock date " + stockDate);
				String stkDate = dfYmd.format(stockDate);
				// int selCate=Integer.parseInt(selectedCat);
				System.out.println("stk Date for get Cur Prod and Bill Qty " + stkDate);

				System.out.println("stk CatId for get Cur Prod and Bill Qty " + groupType);

				map.add("prodDate", stkDate);
				map.add("catId", groupType);
				map.add("delStatus", 0);

				map.add("timestamp", stockHeader.getTimestamp());

				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();

				map.add("curTimeStamp", dateFormat.format(cal.getTime()));

				getCurProdAndBillQtyList = restTemplate.postForObject(Constants.url + "getCurrentProdAndBillQty", map,
						GetCurProdAndBillQtyList.class);

				getCurProdAndBillQty = getCurProdAndBillQtyList.getGetCurProdAndBillQty();

				System.out.println("Cur Prod And Bill Qty Listy " + getCurProdAndBillQty.toString());
				DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

				String stockkDate = df.format(stockDate);
				map = new LinkedMultiValueMap<String, Object>();
				map.add("stockDate", stockkDate);
				map.add("catId", groupType);
				ParameterizedTypeReference<List<FinishedGoodStockDetail>> typeRef = new ParameterizedTypeReference<List<FinishedGoodStockDetail>>() {
				};
				ResponseEntity<List<FinishedGoodStockDetail>> responseEntity = restTemplate.exchange(
						Constants.url + "getFinGoodStockDetail", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				List<FinishedGoodStockDetail> finGoodDetail = responseEntity.getBody();

				System.out.println("Finished Good Stock Detail " + finGoodDetail.toString());

				FinishedGoodStockDetail stockDetail = new FinishedGoodStockDetail();
				GetCurProdAndBillQty curProdBilQty = new GetCurProdAndBillQty();

				for (int i = 0; i < getCurProdAndBillQty.size(); i++) {

					curProdBilQty = getCurProdAndBillQty.get(i);

					for (int j = 0; j < finGoodDetail.size(); j++) {

						stockDetail = finGoodDetail.get(j);

						if (curProdBilQty.getId() == stockDetail.getItemId()) {

							System.out.println(
									"item Id Matched " + curProdBilQty.getId() + "and " + stockDetail.getItemId());

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
							stockDetail.setCloCurrent(stockDetail.getOpTotal());

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

				for (int i = 0; i < postProductionPlanDetaillist.size(); i++) {

					for (int j = 0; j < updateStockDetailList.size(); j++) {

						if (postProductionPlanDetaillist.get(i).getItemId() == updateStockDetailList.get(j)
								.getItemId()) {

							postProductionPlanDetaillist.get(i)
									.setCurClosingQty(updateStockDetailList.get(j).getCloCurrent());

							postProductionPlanDetaillist.get(i)
									.setCurOpeQty(updateStockDetailList.get(j).getTotalCloStk()); // current stock
						}

					}

				}

				System.out.println("Fianl Post Prod Detail  List " + postProductionPlanDetaillist.toString());
				// end of new Code

			} catch (Exception e) {
				System.out.println("Excein Prod Controller get Current Fin good Stock " + e.getMessage());
				e.printStackTrace();

			}

			getVarianceorderlistforsort = getQtyforVariance.getVarianceorderlist();

			for (int i = 0; i < getVarianceorderlistforsort.size(); i++) {

				for (int j = 0; j < updateStockDetailList.size(); j++) {

					if (getVarianceorderlistforsort.get(i).getId() == updateStockDetailList.get(j).getItemId()) {

						getVarianceorderlistforsort.get(i)
								.setCurClosingQty(updateStockDetailList.get(j).getCloCurrent());

						getVarianceorderlistforsort.get(i).setCurOpeQty(updateStockDetailList.get(j).getTotalCloStk());
						float remainingProQty = getVarianceorderlistforsort.get(i).getOrderQty() 
								- getVarianceorderlistforsort.get(i).getCurOpeQty();

						if (remainingProQty > 0) {
							getVarianceorderlistforsort.get(i).setRemainingQty((int) remainingProQty);
						} else {
							getVarianceorderlistforsort.get(i).setRemainingQty(0);
						}
					}

				}

			}

			for (int i = 0; i < postProductionPlanDetaillist.size(); i++) {
				int planItemid = postProductionPlanDetaillist.get(i).getItemId();

				for (int j = 0; j < getVarianceorderlistforsort.size(); j++) {
					int varianceItemId = getVarianceorderlistforsort.get(j).getId();

					if (planItemid == varianceItemId) {

						postProductionPlanDetaillist.get(i).setOrderQty(getVarianceorderlistforsort.get(j).getOrderQty() );
						float remainingProQty = postProductionPlanDetaillist.get(i).getOrderQty()
								- postProductionPlanDetaillist.get(i).getCurOpeQty();

						if (remainingProQty > 0) {
							postProductionPlanDetaillist.get(i).setInt4((int) remainingProQty);
						} else {
							postProductionPlanDetaillist.get(i).setInt4(0);
						}
						getVarianceorderlistforsort.remove(j);
					}

				}

			}
			AllItemsListResponse allItemsListResponse = restTemplate.getForObject(Constants.url + "getAllItems",
					AllItemsListResponse.class);
			List<Item> itemsList = allItemsListResponse.getItems();
			pdfItemList = allItemsListResponse.getItems();
			System.out.println("getVarianceorderlistforsort size " + getVarianceorderlistforsort.size());
			System.out.println("unsort size " + getQtyforVariance.getVarianceorderlist().size());
			System.out.println(postProductionPlanDetaillist.toString());

			model.addObject("postProdPlanHeader", postProdPlanHeader);
			model.addObject("getVarianceorderlistforsort", getVarianceorderlistforsort);
			model.addObject("itemsList", itemsList);
			model.addObject("categoryList", categoryListComp.getmCategoryList());
			model.addObject("postProdPlanHeaderDetailed", postProductionPlanDetaillist);
			
			try
			{
				
				List<ExportToExcel> exportToExcelList=new ArrayList<ExportToExcel>();
				
				ExportToExcel expoExcel=new ExportToExcel();
				List<String> rowData=new ArrayList<String>();
				 
				rowData.add("Sr.No."); 
				rowData.add("Item Name"); 
				rowData.add("Opening Qty ");
				rowData.add("Plan Qty");
				rowData.add("Production Qty"); 
				rowData.add("current Stock "); 
				rowData.add("Order Qty"); 
				rowData.add("Rejected Qty"); 
				rowData.add("Remaining Production"); 
			 
					
				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);
				int srNo=1;
				for(int i=0;i<postProductionPlanDetaillist.size();i++)
				{
					  expoExcel=new ExportToExcel();
					 rowData=new ArrayList<String>();
					 
				 
					 
					 rowData.add(""+srNo); 
					 for(int j=0;j<itemsList.size();j++)
					 {
						 if(itemsList.get(j).getId()==postProductionPlanDetaillist.get(i).getItemId())
						 {
							 rowData.add(""+itemsList.get(j).getItemName());
							 break;
						 }
						 
					 }  
					rowData.add(""+postProductionPlanDetaillist.get(i).getCurClosingQty()); 
					rowData.add(""+postProductionPlanDetaillist.get(i).getPlanQty());
					rowData.add(""+postProductionPlanDetaillist.get(i).getProductionQty()); 
					rowData.add(""+postProductionPlanDetaillist.get(i).getCurOpeQty());
					rowData.add(""+postProductionPlanDetaillist.get(i).getOrderQty()); 
					rowData.add(""+postProductionPlanDetaillist.get(i).getRejectedQty());
					rowData.add(""+postProductionPlanDetaillist.get(i).getInt4()); 
					srNo=srNo+1;
					
					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);
					 
				}
				
				for(int i=0;i<getVarianceorderlistforsort.size();i++)
				{
					  expoExcel=new ExportToExcel();
					 rowData=new ArrayList<String>();
					 
				 
					 
					 rowData.add(""+srNo); 
					 
					rowData.add(""+getVarianceorderlistforsort.get(i).getItemName());
					rowData.add(""+getVarianceorderlistforsort.get(i).getCurOpeQty());
					rowData.add(""+0); 
					rowData.add(""+0); 
					rowData.add(""+getVarianceorderlistforsort.get(i).getOrderQty()); 
					rowData.add(""+0); 
					rowData.add(""+getVarianceorderlistforsort.get(i).getRemainingQty()); 
					srNo=srNo+1;
					
					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);
					 
				}
				 
				
				
				HttpSession session = request.getSession();
				session.setAttribute("exportExcelList", exportToExcelList);
				session.setAttribute("excelName", "varianceList");
				System.err.println("planList original *** " +postProductionPlanDetaillist.size() );
				System.err.println("getVarianceorderlistforsort *** " +getVarianceorderlistforsort.size() );
				
			}catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("Exception in generate excel ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	// postProductionPlanDetaillist
	@RequestMapping(value = "/showVariencePdf", method = RequestMethod.GET)
	public void showProdByOrderPdf(HttpServletRequest request, HttpServletResponse response)
			throws FileNotFoundException {
		BufferedOutputStream outStream = null;
		System.out.println("Inside show Prod BOM Pdf ");
		Document doc = new Document();

		File openFile = null;
		
		List<TempProdPlanDetail> tempProdPlanDetailList= new ArrayList<>();
		
		for(int i = 0;i<getVarianceorderlistforsort.size();i++)
		{
			TempProdPlanDetail TempProdPlanDetail = new TempProdPlanDetail();
			TempProdPlanDetail.setItemId(getVarianceorderlistforsort.get(i).getId());
			TempProdPlanDetail.setOrderQty(getVarianceorderlistforsort.get(i).getOrderQty());
			
			
			TempProdPlanDetail.setCurClosingQty(getVarianceorderlistforsort.get(i).getCurClosingQty());
			TempProdPlanDetail.setCurOpeQty(getVarianceorderlistforsort.get(i).getCurOpeQty());
			TempProdPlanDetail.setInt4(getVarianceorderlistforsort.get(i).getRemainingQty());
			tempProdPlanDetailList.add(TempProdPlanDetail);
			
		}
		
		for(int i = 0;i<postProductionPlanDetaillist.size();i++)
		{
			TempProdPlanDetail TempProdPlanDetail = new TempProdPlanDetail();
			TempProdPlanDetail.setItemId(postProductionPlanDetaillist.get(i).getItemId());
			TempProdPlanDetail.setOrderQty(postProductionPlanDetaillist.get(i).getOrderQty());
			
			TempProdPlanDetail.setPlanQty(postProductionPlanDetaillist.get(i).getPlanQty());
			TempProdPlanDetail.setProductionQty(postProductionPlanDetaillist.get(i).getProductionQty());
			TempProdPlanDetail.setCurClosingQty(postProductionPlanDetaillist.get(i).getCurClosingQty());
			TempProdPlanDetail.setCurOpeQty(postProductionPlanDetaillist.get(i).getCurOpeQty());
			TempProdPlanDetail.setInt4(postProductionPlanDetaillist.get(i).getInt4());
			tempProdPlanDetailList.add(TempProdPlanDetail);
			
		}
		
		 System.err.println("Temp Prod Detail new " +tempProdPlanDetailList.toString());
		
		 
		 
		
		//postProdDetailList = postProductionPlanDetaillist;
		Document document = new Document(PageSize.A4);
		// ByteArrayOutputStream out = new ByteArrayOutputStream();

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();

		System.out.println("time in Gen Bill PDF ==" + dateFormat.format(cal.getTime()));
		String timeStamp = dateFormat.format(cal.getTime());
		String FILE_PATH = Constants.REPORT_SAVE;
		File file = new File(FILE_PATH);
		
		RestTemplate restTemplate = new RestTemplate();

		SubCategory[] subCatList = restTemplate.getForObject(Constants.url + "getAllSubCatList",
				SubCategory[].class);

		ArrayList<SubCategory> subCatAList = new ArrayList<SubCategory>(Arrays.asList(subCatList));
		System.err.println("Sub Cat List /getAllSubCatList " +subCatList.toString());
		
		
		List<SubCategory> filteredSubCat = new ArrayList<SubCategory>();
		
		
		
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		/*
		Item[] itemList = restTemplate.postForObject(Constants.url + "getItemsBySubCatId",map,
				Item[].class);

		ArrayList<Item> items = new ArrayList<Item>(Arrays.asList(itemList));
*/

		PdfWriter writer = null;

		FileOutputStream out = new FileOutputStream(FILE_PATH);

		try {
			writer = PdfWriter.getInstance(document, out);
		} catch (DocumentException e) {

			e.printStackTrace();
		}
		
		
		
		
System.err.println("Pdf Item List " +pdfItemList.toString());
System.err.println("Plan Detail  List " +tempProdPlanDetailList.toString());
System.err.println("getVarianceorderlistforsort Item List " +getVarianceorderlistforsort.toString());

		PdfPTable table = new PdfPTable(9);
		try {
			System.out.println("Inside PDF Table try");
			table.setWidthPercentage(100);
			table.setWidths(new float[] { 0.9f, 2.9f, 1.4f, 0.9f, 1.4f, 1.4f, 0.9f, 1.4f, 1.4f });
			Font headFont = new Font(FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);
			Font headFont1 = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
			Font f = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.UNDERLINE, BaseColor.BLUE);

			PdfPCell hcell;
			hcell = new PdfPCell(new Phrase("Sr.No.", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Item Description", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("OP BAL", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("PLAN", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("PROD", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("TOTAL", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Order", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("VARI", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("CLBAL", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			int index = 0;
			for (int j = 0; j < pdfItemList.size(); j++) {

				for (TempProdPlanDetail planDetail : tempProdPlanDetailList) {

					if (pdfItemList.get(j).getId() == planDetail.getItemId()) {

						index++;
						PdfPCell cell;

						cell = new PdfPCell(new Phrase(String.valueOf(index), headFont));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(cell);

						System.out.println("Inside Item Matched ");
						cell = new PdfPCell(new Phrase(pdfItemList.get(j).getItemName(), headFont));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell.setPaddingRight(8);
						table.addCell(cell);

						cell = new PdfPCell(new Phrase(String.valueOf(planDetail.getCurClosingQty()), headFont));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setPaddingRight(8);
						table.addCell(cell);

						cell = new PdfPCell(new Phrase(String.valueOf(planDetail.getPlanQty()), headFont));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setPaddingRight(8);
						table.addCell(cell);

						cell = new PdfPCell(new Phrase(String.valueOf(planDetail.getProductionQty()), headFont));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setPaddingRight(8);
						table.addCell(cell);

						/*cell = new PdfPCell(new Phrase(
								String.valueOf(planDetail.getCurOpeQty() + planDetail.getProductionQty()), headFont));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setPaddingRight(8);
						table.addCell(cell);*/
						
						cell = new PdfPCell(new Phrase(
								String.valueOf(planDetail.getCurOpeQty()), headFont));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setPaddingRight(8);
						table.addCell(cell);


						cell = new PdfPCell(new Phrase(String.valueOf(planDetail.getOrderQty()), headFont));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setPaddingRight(8);
						table.addCell(cell);
						
						float variance= (planDetail.getOrderQty()-planDetail.getCurOpeQty());
						String var=new String();
						if(variance<0) {
							var="-";
						}else {
							var=""+variance;
						}

						cell = new PdfPCell(new Phrase(String.valueOf(var),
								headFont));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setPaddingRight(8);
						table.addCell(cell);
						
						
						float clBal= (planDetail.getCurOpeQty()-planDetail.getOrderQty());

						cell = new PdfPCell(new Phrase(String.valueOf(clBal), headFont));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setPaddingRight(8);
						table.addCell(cell);
						
						for(int a=0;a<subCatAList.size();a++) {
							
							if(pdfItemList.get(j).getItemGrp2()==subCatAList.get(a).getSubCatId()) {
								
								System.err.println(" Sub Cat Found "+subCatAList.get(a).getSubCatName());
								
								
								if (filteredSubCat.isEmpty())
									filteredSubCat.add(subCatAList.get(a));
								else if (!filteredSubCat.contains(subCatAList.get(a))) {
									filteredSubCat.add(subCatAList.get(a));
								}
								
							}
							
						}

						break;
					}
					// FooterTable footerEvent = new FooterTable(table);
					// writer.setPageEvent(footerEvent);
				}
			}
			
			/////akshay code    
			 
			/*for (int j = 0; j < pdfItemList.size(); j++) {

				for (Variance variance : varianceListForPdf) {

					if (pdfItemList.get(j).getId() == variance.getId()) {

						index++;
						PdfPCell cell;

						cell = new PdfPCell(new Phrase(String.valueOf(index), headFont));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(cell);

						System.out.println("Inside Item Matched ");
						cell = new PdfPCell(new Phrase(pdfItemList.get(j).getItemName(), headFont));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell.setPaddingRight(8);
						table.addCell(cell);

						cell = new PdfPCell(new Phrase(String.valueOf(variance.getCurClosingQty()), headFont));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setPaddingRight(8);
						table.addCell(cell);

						cell = new PdfPCell(new Phrase(String.valueOf(0), headFont));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setPaddingRight(8);
						table.addCell(cell);

						cell = new PdfPCell(new Phrase(String.valueOf(0), headFont));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setPaddingRight(8);
						table.addCell(cell);

						cell = new PdfPCell(new Phrase(
								String.valueOf(planDetail.getCurOpeQty() + planDetail.getProductionQty()), headFont));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setPaddingRight(8);
						table.addCell(cell);
						
						cell = new PdfPCell(new Phrase(
								String.valueOf(variance.getCurOpeQty()), headFont));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setPaddingRight(8);
						table.addCell(cell);


						cell = new PdfPCell(new Phrase(String.valueOf(variance.getOrderQty()), headFont));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setPaddingRight(8);
						table.addCell(cell);
						
						float varianc = ( variance.getOrderQty()-variance.getCurOpeQty());
						String var=new String();
						if(varianc<0) {
							var="-";
						}else {
							var=""+varianc;
						}

						cell = new PdfPCell(new Phrase(String.valueOf(var),
								headFont));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setPaddingRight(8);
						table.addCell(cell);
						
						
						float clBal= (variance.getCurOpeQty()-variance.getOrderQty());

						cell = new PdfPCell(new Phrase(String.valueOf(clBal), headFont));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setPaddingRight(8);
						table.addCell(cell);
						
						for(int a=0;a<subCatAList.size();a++) {
							
							if(pdfItemList.get(j).getItemGrp2()==subCatAList.get(a).getSubCatId()) {
								
								System.err.println(" Sub Cat Found "+subCatAList.get(a).getSubCatName());
								
								
								if (filteredSubCat.isEmpty())
									filteredSubCat.add(subCatAList.get(a));
								else if (!filteredSubCat.contains(subCatAList.get(a))) {
									filteredSubCat.add(subCatAList.get(a));
								}
								
							}
							
						}

						break;
					}
					// FooterTable footerEvent = new FooterTable(table);
					// writer.setPageEvent(footerEvent);
				}
			}*/
			
			
			
			
			//end
			
			
			
			
			SubCatwiseVariancePdf subVar=null;
			
			List<SubCatwiseVariancePdf> varBySubcatList=new ArrayList<SubCatwiseVariancePdf>();
			
			for(int a=0;a<filteredSubCat.size();a++) {
				
				 subVar=new SubCatwiseVariancePdf();
				 subVar.setSubCatName(filteredSubCat.get(a).getSubCatName());
				
				map = new LinkedMultiValueMap<String, Object>();
				
				map.add("subCatId", filteredSubCat.get(a).getSubCatId());

				Item[] itemList = restTemplate.postForObject(Constants.url + "getItemsBySubCatId",map,
						Item[].class);

				ArrayList<Item> items = new ArrayList<Item>(Arrays.asList(itemList));
				
				for(int b=0;b<items.size();b++) {
					
					for (TempProdPlanDetail planDetail : tempProdPlanDetailList) {
						
						if(planDetail.getItemId()==items.get(b).getId()) {
							
							subVar.setOpBalance(subVar.getOpBalance()+planDetail.getCurClosingQty());
							subVar.setPlanQty(subVar.getPlanQty()+planDetail.getPlanQty());
							subVar.setProdQty(subVar.getProdQty()+planDetail.getProductionQty());
							subVar.setTotal(subVar.getTotal()+planDetail.getCurOpeQty());
							subVar.setOrderQty(subVar.getOrderQty()+planDetail.getOrderQty());
							
							float variance= (planDetail.getOrderQty()-planDetail.getCurOpeQty());
							if(variance>0) {
								subVar.setVariance(subVar.getVariance()+variance);
							}
							float clBal= (planDetail.getCurOpeQty()-planDetail.getOrderQty());
							subVar.setClBal(subVar.getClBal()+clBal);
						}
					}
				}
				varBySubcatList.add(subVar);
	
				}
			System.err.println("Sub Cat List And Data " +				varBySubcatList.toString());
			
			System.err.println("Filterd Sub  Cat " +filteredSubCat.toString() + "size " +filteredSubCat.size());
			
			
			PdfPTable subCatTable = new PdfPTable(9);

			System.out.println("Inside PDF Table try");
			subCatTable.setWidthPercentage(100);
			subCatTable.setWidths(new float[] { 0.9f, 2.9f, 1.4f, 0.9f, 1.4f, 1.4f, 0.9f, 1.4f, 1.4f });
			 headFont = new Font(FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);
			 headFont1 = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
			 f = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.UNDERLINE, BaseColor.BLUE);

			
			hcell = new PdfPCell(new Phrase("Sr.No.", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			subCatTable.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Item Description", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			subCatTable.addCell(hcell);

			hcell = new PdfPCell(new Phrase("OP BAL", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			subCatTable.addCell(hcell);

			hcell = new PdfPCell(new Phrase("PLAN", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			subCatTable.addCell(hcell);

			hcell = new PdfPCell(new Phrase("PROD", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			subCatTable.addCell(hcell);

			hcell = new PdfPCell(new Phrase("TOTAL", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			subCatTable.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Order", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			subCatTable.addCell(hcell);

			hcell = new PdfPCell(new Phrase("VARI", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			subCatTable.addCell(hcell);

			hcell = new PdfPCell(new Phrase("CLBAL", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			subCatTable.addCell(hcell);
						index=0;
						for (int j=0;j<varBySubcatList.size();j++) {
							index++;
							PdfPCell cell;
							
							SubCatwiseVariancePdf planDetail=varBySubcatList.get(j);
							
							cell = new PdfPCell(new Phrase(String.valueOf(index), headFont));
							cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
							cell.setHorizontalAlignment(Element.ALIGN_CENTER);
							subCatTable.addCell(cell);

							System.out.println("Inside Item Matched ");
							cell = new PdfPCell(new Phrase(varBySubcatList.get(j).getSubCatName(), headFont));
							cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
							cell.setHorizontalAlignment(Element.ALIGN_LEFT);
							cell.setPaddingRight(8);
							subCatTable.addCell(cell);

							cell = new PdfPCell(new Phrase(String.valueOf(planDetail.getOpBalance()), headFont));
							cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
							cell.setHorizontalAlignment(Element.ALIGN_CENTER);
							cell.setPaddingRight(8);
							subCatTable.addCell(cell);

							cell = new PdfPCell(new Phrase(String.valueOf(planDetail.getPlanQty()), headFont));
							cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
							cell.setHorizontalAlignment(Element.ALIGN_CENTER);
							cell.setPaddingRight(8);
							subCatTable.addCell(cell);

							cell = new PdfPCell(new Phrase(String.valueOf(planDetail.getProdQty()), headFont));
							cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
							cell.setHorizontalAlignment(Element.ALIGN_CENTER);
							cell.setPaddingRight(8);
							subCatTable.addCell(cell);

							
							
							cell = new PdfPCell(new Phrase(
									String.valueOf(planDetail.getTotal()), headFont));
							cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
							cell.setHorizontalAlignment(Element.ALIGN_CENTER);
							cell.setPaddingRight(8);
							subCatTable.addCell(cell);


							cell = new PdfPCell(new Phrase(String.valueOf(planDetail.getOrderQty()), headFont));
							cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
							cell.setHorizontalAlignment(Element.ALIGN_CENTER);
							cell.setPaddingRight(8);
							subCatTable.addCell(cell);
							

							cell = new PdfPCell(new Phrase(String.valueOf(planDetail.getVariance()),
									headFont));
							cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
							cell.setHorizontalAlignment(Element.ALIGN_CENTER);
							cell.setPaddingRight(8);
							subCatTable.addCell(cell);
							
							
						
							cell = new PdfPCell(new Phrase(String.valueOf(planDetail.getClBal()), headFont));
							cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
							cell.setHorizontalAlignment(Element.ALIGN_CENTER);
							cell.setPaddingRight(8);
							table.addCell(cell);
								subCatTable.addCell(cell);

			}

			document.open();
			Paragraph company = new Paragraph(
					"Galdhar Foods Pvt.Ltd\n" + "Factory Add: A-32 Shendra, MIDC, Auraangabad-4331667"
							+ "Phone:0240-2466217, Email: aurangabad@monginis.net",
					f);
			company.setAlignment(Element.ALIGN_CENTER);
			document.add(company);
			document.add(new Paragraph(" "));

			Paragraph heading = new Paragraph("Report-For Production Varience");
			heading.setAlignment(Element.ALIGN_CENTER);
			document.add(heading);

			DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
			String reportDate = DF.format(new Date());

			document.add(new Paragraph("" + postProdPlanHeader.getProductionDate()));
			document.add(new Paragraph("\n"));

			document.add(new Paragraph(" "));
			document.add(table);
			document.add(new Paragraph("\n"));
			document.add(new Paragraph("Summary By SubCategories: "));
			document.add(new Paragraph("\n"));

			document.add(subCatTable);
			int totalPages = writer.getPageNumber();
			
			System.err.println("total Pages " +totalPages);
			
			document.close();
			
		
				

			/*
			 * com.ats.adminpanel.model.itextpdf.Header event; // = new
			 * com.ats.adminpanel.model.itextpdf.Header(); for(int i=1;i<totalPages;i++) {
			 * event = new com.ats.adminpanel.model.itextpdf.Header(); event.setHeader(new
			 * Phrase(String.format("page %s", i)));
			 * 
			 * writer.setPageEvent(event); }
			 * 
			 * 
			 * FooterTable footerEvent = new FooterTable(table);
			 */

			// document.add(new
			// Paragraph(""+document.setPageCount(document.getPageNumber()));

			System.out.println("Page no " + totalPages);

			// document.addHeader("Page" ,String.valueOf(totalPages));
			// writer.setPageEvent((PdfPageEvent) new Phrase());

			// Atul Sir code to open a Pdf File
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

			System.out.println("Pdf Generation Error: Prod From Orders" + ex.getMessage());

			ex.printStackTrace();

		}

		ModelAndView model = new ModelAndView("production/pdf/productionPdf");
		
		System.err.println("Orig List size " +postProductionPlanDetaillist.size());
		
		System.err.println("assigned list size  " +tempProdPlanDetailList.size());
		// model.addObject("prodFromOrderReport",updateStockDetailList);
		// return openFile;

	}

	@RequestMapping(value = "/updateOrderQtyinPlan", method = RequestMethod.POST)
	public String updateOrderQtyinPlan(HttpServletRequest request, HttpServletResponse response) {

		List<PostProductionPlanDetail> postProductionPlanDetailnewplan = new ArrayList<PostProductionPlanDetail>();
		PostProductionPlanDetail postProductionPlanDetailnew = new PostProductionPlanDetail();
		PostProdPlanHeader postProdPlanHeadernewplan = new PostProdPlanHeader();
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String Pdate = formatter.format(date);
		System.out.println(Pdate);

		RestTemplate restTemplate = new RestTemplate();
		try {
			postProdPlanHeadernewplan.setProductionStatus(2);
			postProdPlanHeadernewplan.setItemGrp1(postProdPlanHeader.getItemGrp1());
			postProdPlanHeadernewplan.setProductionDate(postProdPlanHeader.getProductionDate());
			postProdPlanHeadernewplan.setTimeSlot(postProdPlanHeader.getTimeSlot());
			postProdPlanHeadernewplan.setProductionBatch("");
			for (int i = 0; i < postProductionPlanDetaillist.size(); i++) {
				if (postProductionPlanDetaillist.get(i).getInt4() > 0) {
					postProductionPlanDetailnew = new PostProductionPlanDetail();
					postProductionPlanDetailnew.setItemId(postProductionPlanDetaillist.get(i).getItemId());
					postProductionPlanDetailnew.setOpeningQty(0);
					postProductionPlanDetailnew.setOrderQty(postProductionPlanDetaillist.get(i).getInt4());
					postProductionPlanDetailnew.setProductionQty(0);
					postProductionPlanDetailnew.setRejectedQty(0);
					postProductionPlanDetailnew.setPlanQty(0);
					postProductionPlanDetailnew.setInt4(0);
					postProductionPlanDetailnew
							.setProductionDate(postProductionPlanDetaillist.get(i).getProductionDate());
					postProductionPlanDetailnew.setProductionBatch("");
					postProductionPlanDetailnewplan.add(postProductionPlanDetailnew);

				}

			}
			for (int i = 0; i < getVarianceorderlistforsort.size(); i++) {
				if (getVarianceorderlistforsort.get(i).getRemainingQty() > 0) {
					postProductionPlanDetailnew = new PostProductionPlanDetail();
					postProductionPlanDetailnew.setItemId(getVarianceorderlistforsort.get(i).getId());
					postProductionPlanDetailnew.setOpeningQty(0);
					postProductionPlanDetailnew.setOrderQty(getVarianceorderlistforsort.get(i).getRemainingQty());
					postProductionPlanDetailnew.setProductionQty(0);
					postProductionPlanDetailnew.setRejectedQty(0);
					postProductionPlanDetailnew.setPlanQty(0);
					postProductionPlanDetailnew.setInt4(0);
					postProductionPlanDetailnew.setProductionDate(postProdPlanHeader.getProductionDate());
					postProductionPlanDetailnew.setProductionBatch("");
					postProductionPlanDetailnewplan.add(postProductionPlanDetailnew);

				}
			}

			postProdPlanHeader.setProductionStatus(5);
			postProdPlanHeader.setPostProductionPlanDetail(postProductionPlanDetaillist);
			Info updateOrderQtyinPlan = restTemplate.postForObject(Constants.url + "postProductionPlan",
					postProdPlanHeader, Info.class);
			System.out.println("updateOrderQtyinPlan " + updateOrderQtyinPlan);
			System.out.println("postProductionPlanDetailnewplan " + postProductionPlanDetailnewplan.size());
			if (updateOrderQtyinPlan.getError() == false && postProductionPlanDetailnewplan.size() != 0) {
				System.out.println("in if insert new plan");
				postProdPlanHeadernewplan.setPostProductionPlanDetail(postProductionPlanDetailnewplan);
				Info insertNewinPlan = restTemplate.postForObject(Constants.url + "postProductionPlan",
						postProdPlanHeadernewplan, Info.class);
				System.out.println("insertNewinPlan" + insertNewinPlan);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/listForVariation";
	}

	@RequestMapping(value = "/insertProductionPlanWithoutCompletProd", method = RequestMethod.GET)
	public String insertProductionPlanWithoutCompletProd(HttpServletRequest request, HttpServletResponse response) {

		List<PostProductionPlanDetail> postProductionPlanDetailnewplan = new ArrayList<PostProductionPlanDetail>();
		PostProductionPlanDetail postProductionPlanDetailnew = new PostProductionPlanDetail();
		PostProdPlanHeader postProdPlanHeadernewplan = new PostProdPlanHeader();
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String Pdate = formatter.format(date);
		System.out.println(Pdate);

		RestTemplate restTemplate = new RestTemplate();
		try {
			postProdPlanHeadernewplan.setProductionStatus(2);
			postProdPlanHeadernewplan.setItemGrp1(postProdPlanHeader.getItemGrp1());
			postProdPlanHeadernewplan.setProductionDate(postProdPlanHeader.getProductionDate());
			postProdPlanHeadernewplan.setTimeSlot(postProdPlanHeader.getTimeSlot());
			postProdPlanHeadernewplan.setProductionBatch("");
			for (int i = 0; i < postProductionPlanDetaillist.size(); i++) {
				if (postProductionPlanDetaillist.get(i).getInt4() > 0) {
					postProductionPlanDetailnew = new PostProductionPlanDetail();
					postProductionPlanDetailnew.setItemId(postProductionPlanDetaillist.get(i).getItemId());
					postProductionPlanDetailnew.setOpeningQty(0);
					postProductionPlanDetailnew.setOrderQty(postProductionPlanDetaillist.get(i).getInt4());
					postProductionPlanDetailnew.setProductionQty(0);
					postProductionPlanDetailnew.setRejectedQty(0);
					postProductionPlanDetailnew.setPlanQty(0);
					postProductionPlanDetailnew.setInt4(0);
					postProductionPlanDetailnew
							.setProductionDate(postProductionPlanDetaillist.get(i).getProductionDate());
					postProductionPlanDetailnew.setProductionBatch("");
					postProductionPlanDetailnewplan.add(postProductionPlanDetailnew);

				}
			}

			for (int i = 0; i < getVarianceorderlistforsort.size(); i++) {
				if (getVarianceorderlistforsort.get(i).getRemainingQty() > 0) {
					postProductionPlanDetailnew = new PostProductionPlanDetail();
					postProductionPlanDetailnew.setItemId(getVarianceorderlistforsort.get(i).getId());
					postProductionPlanDetailnew.setOpeningQty(0);
					postProductionPlanDetailnew.setOrderQty(getVarianceorderlistforsort.get(i).getRemainingQty());
					postProductionPlanDetailnew.setProductionQty(0);
					postProductionPlanDetailnew.setRejectedQty(0);
					postProductionPlanDetailnew.setPlanQty(0);
					postProductionPlanDetailnew.setInt4(0);
					postProductionPlanDetailnew.setProductionDate(postProdPlanHeader.getProductionDate());
					postProductionPlanDetailnew.setProductionBatch("");
					postProductionPlanDetailnewplan.add(postProductionPlanDetailnew);

				}
			}

			// postProdPlanHeader.setProductionStatus(5);
			postProdPlanHeader.setPostProductionPlanDetail(postProductionPlanDetaillist);
			Info updateOrderQtyinPlan = restTemplate.postForObject(Constants.url + "postProductionPlan",
					postProdPlanHeader, Info.class);
			System.out.println("updateOrderQtyinPlan " + updateOrderQtyinPlan);
			System.out.println("postProductionPlanDetailnewplan " + postProductionPlanDetailnewplan.size());
			if (updateOrderQtyinPlan.getError() == false && postProductionPlanDetailnewplan.size() != 0) {
				System.out.println("in if insert new plan");
				postProdPlanHeadernewplan.setPostProductionPlanDetail(postProductionPlanDetailnewplan);
				Info insertNewinPlan = restTemplate.postForObject(Constants.url + "postProductionPlan",
						postProdPlanHeadernewplan, Info.class);
				System.out.println("insertNewinPlan" + insertNewinPlan);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/listForVariation";
	}

}
