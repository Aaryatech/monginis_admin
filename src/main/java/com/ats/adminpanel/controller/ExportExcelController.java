package com.ats.adminpanel.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList; 
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

 
import com.ats.adminpanel.model.ExportToExcel;

 

@Controller
public class ExportExcelController {

/*	<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-ooxml</artifactId>
    <version>3.13</version>
</dependency>*/
	List<ExportToExcel> exportToExcelListNew = new ArrayList<ExportToExcel>();
	
	List<ExportToExcel> exportToExcelList=new ArrayList<ExportToExcel>();
	  @RequestMapping(value = "/exportToExcel", method = RequestMethod.GET)
	    @ResponseBody
	    public void downloadSpreadsheet(HttpServletResponse response, HttpServletRequest request) throws Exception {
	        XSSFWorkbook wb = null;
	        HttpSession session = request.getSession();
	        try {
	        	
	        	
	        	  exportToExcelList=(List)session.getAttribute("exportExcelList"); 
	        	System.out.println("Excel List :"+exportToExcelList.toString());
	         
	        	String excelName=(String)session.getAttribute("excelName"); 
	            wb=createWorkbook();
	         
	            response.setContentType("application/vnd.ms-excel");
	            String date=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	            response.setHeader("Content-disposition", "attachment; filename="+excelName+"-"+date+".xlsx");
	            wb.write(response.getOutputStream());
	        	
	        } catch (IOException ioe) {
	            throw new RuntimeException("Error writing spreadsheet to output stream");
	        } finally {
	            if (wb != null) {
	                wb.close();
	            }
	        }
	        session.removeAttribute( "exportExcelList" );
	        System.out.println("Session List"+session.getAttribute("exportExcelList")); 
	    }
	 
	    private XSSFWorkbook createWorkbook() throws IOException {
	        XSSFWorkbook wb = new XSSFWorkbook();
	        XSSFSheet sheet = wb.createSheet("Cities to visit");
	 
	       /* writeHeaders(wb, sheet);
	        writeHeaders(wb, sheet);
	        writeHeaders(wb, sheet);
	 */
	        
	        	 for (int rowIndex = 0; rowIndex < exportToExcelList.size(); rowIndex++) {
	        	 XSSFRow row = sheet.createRow(rowIndex);
	        	 for (int j = 0; j < exportToExcelList.get(rowIndex).getRowData().size(); j++) {
	           
	            XSSFCell cell = row.createCell(j);
	            
	           
	            cell.setCellValue(exportToExcelList.get(rowIndex).getRowData().get(j));
	           
	             
	      
	        }
	        	 if(rowIndex==0)
	        	 row.setRowStyle(createHeaderStyle(wb));
	        }
	        return wb;
	    }
	 
	/*    private void writeHeaders(XSSFWorkbook workbook, XSSFSheet sheet) {
	        XSSFRow header = sheet.createRow(0);
	        XSSFCell headerCell = header.createCell(0);
	        headerCell.setCellValue("Cities to visit");
	        headerCell.setCellStyle(createHeaderStyle(workbook));
	         
	    }*/
	 
	    private XSSFCellStyle createHeaderStyle(XSSFWorkbook workbook) {
	        XSSFCellStyle style = workbook.createCellStyle();
	        style.setWrapText(true);
	        style.setFillForegroundColor(new XSSFColor(new java.awt.Color(53, 119, 192)));

	        //style.setFillPattern(CellStyle.SOLID_FOREGROUND);
	 
	        /*style.setBorderRight(CellStyle.BORDER_THIN);
	        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
	        style.setBorderBottom(CellStyle.BORDER_THIN);
	        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	        style.setBorderLeft(CellStyle.BORDER_THIN);
	        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
	        style.setBorderTop(CellStyle.BORDER_THIN);
	        style.setTopBorderColor(IndexedColors.BLACK.getIndex());*/
	      //  style.setDataFormat(1);
	       
	        Font font =workbook.createFont();
	        font.setFontName("Arial");
	        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	        font.setBold(true);
	        //font.setColor(HSSFColor.WHITE.index);
	        style.setFont(font);
	 
	        return style;
	    }
	    
	    @RequestMapping(value = "/exportToExcelNew", method = RequestMethod.GET)
		@ResponseBody
		public void exportToExcelNew(HttpServletResponse response, HttpServletRequest request) throws Exception {
			XSSFWorkbook wb = null;
			HttpSession session = request.getSession();
			try {

				exportToExcelListNew = (List) session.getAttribute("exportExcelListNew");
				System.out.println("Excel List :" + exportToExcelListNew.toString());

				String excelName = (String) session.getAttribute("excelNameNew");
				String reportName = (String) session.getAttribute("reportNameNew");
				String searchBy = (String) session.getAttribute("searchByNew");
				String mergeUpto1 = (String) session.getAttribute("mergeUpto1");
				String mergeUpto2 = (String) session.getAttribute("mergeUpto2");
				wb = createWorkbookNew(reportName, searchBy, mergeUpto1, mergeUpto2);
				autoSizeColumns(wb, 2);
				response.setContentType("application/vnd.ms-excel");
				String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
				response.setHeader("Content-disposition", "attachment; filename=" + excelName + "-" + date + ".xlsx");
				wb.write(response.getOutputStream());

			} catch (IOException ioe) {
				throw new RuntimeException("Error writing spreadsheet to output stream");
			} finally {
				if (wb != null) {
					wb.close();
				}
			}
			session.removeAttribute("exportExcelListNew");
			System.out.println("Session List" + session.getAttribute("exportExcelListNew"));
		}
	    
	    public void autoSizeColumns(Workbook workbook, int index) {
			int numberOfSheets = workbook.getNumberOfSheets();
			for (int i = 0; i < numberOfSheets; i++) {
				Sheet sheet = workbook.getSheetAt(i);
				if (sheet.getPhysicalNumberOfRows() > 0) {
					Row row = sheet.getRow(index);
					row.setHeight((short) 700);

					Iterator<Cell> cellIterator = row.cellIterator();
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						int columnIndex = cell.getColumnIndex();
						sheet.autoSizeColumn(columnIndex);
					}
				}
			}
		}
	    
	    private XSSFWorkbook createWorkbookNew(String reportName, String searchBy, String mergeUpto1, String mergeUpto2)
				throws IOException {
			XSSFWorkbook wb = new XSSFWorkbook();
			XSSFSheet sheet = wb.createSheet("Sheet1");
			sheet.createFreezePane(0, 3);

			CellStyle style = wb.createCellStyle();
			// style.setFillForegroundColor(IndexedColors.PINK.getIndex());"$A$1:$L$1"
			// style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			// style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			style.setAlignment(CellStyle.ALIGN_CENTER);

			Row titleRow = sheet.createRow(0);
			titleRow.setHeightInPoints(25);
			titleRow.setRowStyle(style);
			Cell titleCell = titleRow.createCell(0);

			// titleCell.setAlignment(CellStyle.ALIGN_CENTER);

			titleCell.setCellValue("" + reportName);
			titleCell.setCellStyle(createHeaderStyleHeaderFont(wb, 255, 243, 235, 0));
			sheet.addMergedRegion(CellRangeAddress.valueOf(mergeUpto1));

			Row searchByRow = sheet.createRow(1);
			searchByRow.setHeightInPoints(25);
			searchByRow.setRowStyle(style);
			Cell searchByCell = searchByRow.createCell(0);

			// titleCell.setAlignment(CellStyle.ALIGN_CENTER);

			searchByCell.setCellValue("Search By.." + searchBy);
			searchByCell.setCellStyle(createHeaderStyleHeaderFont(wb, 255, 243, 235, 0));
			// titleCell.setCellStyle(styles.get("title"));
			sheet.addMergedRegion(CellRangeAddress.valueOf(mergeUpto2));
			/*
			 * writeHeaders(wb, sheet); writeHeaders(wb, sheet); writeHeaders(wb, sheet);
			 */
			 XSSFCellStyle cellStyle = wb.createCellStyle();

			for (int rowIndex = 0; rowIndex < exportToExcelListNew.size(); rowIndex++) {
				XSSFRow row = sheet.createRow(rowIndex + 2);
				for (int j = 0; j < exportToExcelListNew.get(rowIndex).getRowData().size(); j++) {

					XSSFCell cell = row.createCell(j);

					try 
			        { 
			            // checking valid integer using parseInt() method 
			           int value=Integer.parseInt(exportToExcelListNew.get(rowIndex).getRowData().get(j)); 
			            cell.setCellValue(value);
			        }  
			        catch (NumberFormatException e)  
			        { 
			        	 try
			             { 
			                 // checking valid float using parseInt() method
			        		 XSSFDataFormat xssfDataFormat = wb.createDataFormat(); 

			                double value=Double.parseDouble(exportToExcelListNew.get(rowIndex).getRowData().get(j)); 
			                
			                cellStyle.setDataFormat(xssfDataFormat.getFormat("#,##0.00"));
			                cell.setCellStyle(cellStyle);
			                cell.setCellValue(value);
			                
			             }  
			             catch (NumberFormatException e1) 
			             { 
			            	 cell.setCellValue(exportToExcelListNew.get(rowIndex).getRowData().get(j));
			             } 
			               
			        } 
					

					// if((rowIndex+1)==1)
					// cell.setCellStyle(createHeaderStyleHeaderFont(wb,242, 242, 242,1));
					if ((rowIndex + 2) == 2)
						cell.setCellStyle(createHeaderStyleNew(wb));

				}
				// if((rowIndex+1)==1)
				// row.setRowStyle(createHeaderStyleNew(wb));
			}
			return wb;
		}
		public static float roundUp(float d) {
			return BigDecimal.valueOf(d).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
		}
		
		private XSSFCellStyle createHeaderStyleHeaderFont(XSSFWorkbook workbook, int r, int g, int b, int align) {
			XSSFCellStyle style = workbook.createCellStyle();
			style.setWrapText(true);
			style.setFillForegroundColor(new XSSFColor(new java.awt.Color(r, g, b)));

			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			if (align == 0)
				style.setAlignment(CellStyle.ALIGN_CENTER);
			if (align == 1)
				style.setAlignment(CellStyle.ALIGN_LEFT);

			style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

			style.setBorderRight(CellStyle.BORDER_THIN);
			style.setRightBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderBottom(CellStyle.BORDER_THIN);
			style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderLeft(CellStyle.BORDER_THIN);
			style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderTop(CellStyle.BORDER_THIN);
			style.setTopBorderColor(IndexedColors.BLACK.getIndex());
			style.setDataFormat(1);

			Font font = workbook.createFont();
			font.setFontName("Times New Roman");
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font.setBold(true);
			font.setColor(HSSFColor.BLACK.index);
			style.setFont(font);

			return style;
		}
		
		private XSSFCellStyle createHeaderStyleNew(XSSFWorkbook workbook) {
			XSSFCellStyle style = workbook.createCellStyle();
			style.setWrapText(true);
			style.setFillForegroundColor(new XSSFColor(new java.awt.Color(247, 161, 103)));

			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			style.setAlignment(CellStyle.ALIGN_CENTER);
			style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

			style.setBorderRight(CellStyle.BORDER_THIN);
			style.setRightBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderBottom(CellStyle.BORDER_THIN);
			style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderLeft(CellStyle.BORDER_THIN);
			style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderTop(CellStyle.BORDER_THIN);
			style.setTopBorderColor(IndexedColors.BLACK.getIndex());
			style.setDataFormat(1);

			Font font = workbook.createFont();
			font.setFontName("Times New Roman");
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font.setBold(true);
			font.setColor(HSSFColor.WHITE.index);
			style.setFont(font);

			return style;
		}
}
