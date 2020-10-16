package com.ats.adminpanel.commons;

import java.io.IOException;


import java.util.Iterator;
import java.util.List;

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

import com.ats.adminpanel.model.ExportToExcel;


public class ExceUtil {
	
	public static int incCellValue=4;
	//public static XSSFWorkbook createWorkbook(List<ExportToExcel> exportToExcelList,String instName,String reportName,String filterValue,String reportSummary,Character endChar) throws IOException {
	public static XSSFWorkbook createWorkbook(List<ExportToExcel> exportToExcelList,String instName,String reportName,String filterValue,String reportSummary,Character endChar) throws IOException {
		 
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet("Sheet1");
       System.err.println("instName"+instName);
		sheet.createFreezePane(0, 4);
		//Character endChar='H';
			CellStyle style=wb.createCellStyle();
			style.setAlignment(CellStyle.ALIGN_RIGHT);
			style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			 style.setWrapText(true);
	        Row titleRow = sheet.createRow(0);
	        titleRow.setHeightInPoints(20);
	        titleRow.setRowStyle(style);
	       
	        Cell titleCell = titleRow.createCell(0);
	        titleCell.setCellValue(instName);
	        titleCell.setCellStyle(style);
	        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$"+endChar+"$1"));
	        
	       
	        CellStyle style2=wb.createCellStyle();
		    style2.setAlignment(CellStyle.ALIGN_CENTER);
		    //style2.setAlignment(HorizontalAlignment.RIGHT);
		    style2.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		   
		    Row titleRow2 = sheet.createRow(1);
	        titleRow2.setHeightInPoints(30);
	        titleRow2.setRowStyle(style2);
	        style2.setWrapText(true);
	        //style.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
	        Font font = wb.createFont();
	        font.setFontHeightInPoints((short)12);
	        font.setUnderline(HSSFFont.U_SINGLE);

	        font.setFontName("Times Roman");
	       
	        style2.setFont(font);
	        Cell titleCell2 = titleRow2.createCell(0);
	        titleCell2.setCellStyle(style2);
	        
	        titleCell2.setCellValue(reportName);
	        
	        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$2:$"+endChar+"$2"));
	       
	        CellStyle style3=wb.createCellStyle();
		    style3.setAlignment(CellStyle.ALIGN_LEFT);
		    style3.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		    
	        Row titleRow3 = sheet.createRow(2);
	        titleRow3.setHeightInPoints(20);
	        titleRow3.setRowStyle(style3);
	        
	        Cell titleCell3 = titleRow3.createCell(0);
	        titleCell3.setCellStyle(style3);
	        titleCell3.setCellValue(""+filterValue);//Need Dynamic
	        
	        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$3:$"+endChar+"$3"));
	       
     
         System.err.println("Excel size  " +exportToExcelList.size());
         CellStyle cellStyle=wb.createCellStyle();
		for (int rowIndex = 0; rowIndex < exportToExcelList.size(); rowIndex++) {
			XSSFRow row = sheet.createRow(rowIndex+3);
			for (int j = 0; j < exportToExcelList.get(rowIndex).getRowData().size(); j++) {

				XSSFCell cell = row.createCell(j);

				//cell.setCellValue(exportToExcelList.get(rowIndex).getRowData().get(j));
				
				try 
			       { 
			           // checking valid integer using parseInt() method 
			          int value=Integer.parseInt(exportToExcelList.get(rowIndex).getRowData().get(j)); 
			           cell.setCellValue(value);
			       }  
			       catch (NumberFormatException e)  
			       { 
			       	try
			            { 
			                // checking valid float using parseInt() method
			       	XSSFDataFormat xssfDataFormat = wb.createDataFormat(); 
			               double value=Double.parseDouble(exportToExcelList.get(rowIndex).getRowData().get(j)); 
			               cellStyle.setDataFormat(xssfDataFormat.getFormat("#,##0.00"));
			               cell.setCellStyle(cellStyle);
			               cell.setCellValue(value);
			               
			            }  
			            catch (NumberFormatException e1) 
			            { 
			           	cell.setCellValue(exportToExcelList.get(rowIndex).getRowData().get(j));
			            } 
			              
			       } 
				if ((rowIndex+3) == 3)
		           cell.setCellStyle(createHeaderStyleNew(wb));  
			}
			
		}
		    
		    int cellNum=exportToExcelList.size()+incCellValue;
		    Sheet sh = wb.getSheetAt(0);
	     //   Row r = sh.k(sh.getPhysicalNumberOfRows());
		    Row titleRow4 = sheet.createRow(sh.getPhysicalNumberOfRows());
	        titleRow4.setHeightInPoints(20);
	        titleRow4.setRowStyle(style2);
	        
	        Cell titleCell4 = titleRow4.createCell(0);
	        titleCell4.setCellStyle(style2);
	        titleCell4.setCellValue(reportSummary);//Need Dynamic
	      
	        String s= "$A$"+cellNum+":$"+endChar+"$"+cellNum;
	        sheet.addMergedRegion(CellRangeAddress.valueOf(s));
	       
		return wb;
	}
	public static void autoSizeColumns(Workbook workbook,int index) {
	        int numberOfSheets = workbook.getNumberOfSheets();
	        for (int i = 0; i < numberOfSheets; i++) {
	            Sheet sheet = workbook.getSheetAt(i);
	            if (sheet.getPhysicalNumberOfRows() > 0) {
	                Row row = sheet.getRow(index);
	                row.setHeight((short)700);

	                Iterator<Cell> cellIterator = row.cellIterator();
	                while (cellIterator.hasNext()) {
	                    Cell cell = cellIterator.next();
	                    int columnIndex = cell.getColumnIndex();
	                    sheet.autoSizeColumn(columnIndex);
	                }
	            }
	        }
	    }
	public static XSSFCellStyle createHeaderStyleNew(XSSFWorkbook workbook) {
	        XSSFCellStyle style = workbook.createCellStyle();
	        style.setWrapText(true);
	       // style.setFillForegroundColor(new XSSFColor(new java.awt.Color(247, 161, 103)));
	        
	        style.setFillForegroundColor(IndexedColors.GOLD.getIndex());


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
	       
	        org.apache.poi.ss.usermodel.Font font =workbook.createFont();
	        font.setFontName("Times New Roman");
	        font.setFontHeightInPoints((short)12);
	       
	        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	        font.setBold(true);
	        font.setColor(HSSFColor.WHITE.index);
	        style.setFont(font);
	 
	        return style;
	    }

}
