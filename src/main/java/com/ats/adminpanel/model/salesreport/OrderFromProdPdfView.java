package com.ats.adminpanel.model.salesreport;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import com.ats.adminpanel.model.production.GetOrderItemQty;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class OrderFromProdPdfView  {

	public static ByteArrayInputStream moneyOutReport(List<GetOrderItemQty> moneyOutList) {
	 Document document = new Document();
	  ByteArrayOutputStream out = new ByteArrayOutputStream();
	 PdfPTable table = new PdfPTable(6);
	 try {
	 
	 table.setWidthPercentage(100);
     table.setWidths(new float[]{0.9f, 1.6f, 1.4f,1.4f,1.4f,1.4f});
     Font headFont = new Font(FontFamily.HELVETICA, 8, Font.ITALIC, BaseColor.BLACK);
     Font headFont1 = new Font(FontFamily.HELVETICA, 8, Font.BOLD, BaseColor.BLACK);
     Font f=new Font(FontFamily.TIMES_ROMAN,12.0f,Font.UNDERLINE,BaseColor.BLUE);
     
     PdfPCell hcell;
     hcell = new PdfPCell(new Phrase("Sr.No.", headFont1));
     hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
     table.addCell(hcell);

     hcell = new PdfPCell(new Phrase("Item ID", headFont1));
     hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
     table.addCell(hcell);

     hcell = new PdfPCell(new Phrase("Item Name", headFont1));
     hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
     table.addCell(hcell);
     
     hcell = new PdfPCell(new Phrase("Cur Closing", headFont1));
     hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
     table.addCell(hcell);
     
     hcell = new PdfPCell(new Phrase("Cur Opening", headFont1));
     hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
     table.addCell(hcell);
     
     hcell = new PdfPCell(new Phrase("Close Amount", headFont1));
     hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
     table.addCell(hcell);
     
     hcell = new PdfPCell(new Phrase("Order Quantity", headFont1));
     hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
     table.addCell(hcell);
 
     int index=0;
     for (GetOrderItemQty getMoneyOut : moneyOutList) {
       index++;
         PdfPCell cell;

        cell = new PdfPCell(new Phrase(String.valueOf(index),headFont));
         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         table.addCell(cell);

         cell = new PdfPCell(new Phrase(getMoneyOut.getItemId(),headFont));
         cell.setPaddingLeft(5);
         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         table.addCell(cell);

         cell = new PdfPCell(new Phrase(getMoneyOut.getItemName(),headFont));
         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         cell.setPaddingRight(5);
         table.addCell(cell);
         
         cell = new PdfPCell(new Phrase(String.valueOf(getMoneyOut.getCurClosingQty()),headFont));
         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
         cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
         cell.setPaddingRight(5);
         table.addCell(cell);
         
         cell = new PdfPCell(new Phrase(String.valueOf(getMoneyOut.getCurOpeQty()),headFont));
         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
         cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
         cell.setPaddingRight(5);
         table.addCell(cell);
         
         cell = new PdfPCell(new Phrase(String.valueOf(getMoneyOut.getQty()),headFont));
         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
         cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
         cell.setPaddingRight(5);
         table.addCell(cell);
        
     }

     PdfWriter.getInstance(document, out);
     document.open();
     Paragraph company = new Paragraph("G F P L",f);
     company.setAlignment(Element.ALIGN_CENTER);
     document.add(company);
     document.add(new Paragraph(" "));

     Paragraph heading = new Paragraph("Report");
     heading.setAlignment(Element.ALIGN_CENTER);
     document.add(heading);

     document.add(new Paragraph(" "));
     document.add(table);
     
     document.close();
	
 } catch (DocumentException ex) {
 
    // Logger.getLogger(GeneratePdfReport.class.getName()).log(Level.SEVERE, null, ex);
 }

 return new ByteArrayInputStream(out.toByteArray());
}

}
