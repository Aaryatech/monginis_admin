/*package com.ats.adminpanel.controller.temp;
import com.ats.adminpanel.model.production.GetOrderItemQty;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GeneratePdfReport {

    public static ByteArrayInputStream moneyOutReport( List<GetOrderItemQty> moneyOutList) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            
            PdfPTable table = new PdfPTable(8);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{0.9f, 1.6f, 1.4f,1.4f,1.4f,1.4f,3.9f,1.4f});
            Font headFont = new Font(FontFamily.HELVETICA, 8, Font.ITALIC, BaseColor.BLACK);
            Font headFont1 = new Font(FontFamily.HELVETICA, 8, Font.BOLD, BaseColor.BLACK);
            Font f=new Font(FontFamily.TIMES_ROMAN,12.0f,Font.UNDERLINE,BaseColor.BLUE);
            
            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("Sr.No.", headFont1));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("User Name", headFont1));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Person Name", headFont1));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            
            hcell = new PdfPCell(new Phrase("Out Date", headFont1));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            
            hcell = new PdfPCell(new Phrase("Out Amount", headFont1));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            
            hcell = new PdfPCell(new Phrase("Close Amount", headFont1));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            
            hcell = new PdfPCell(new Phrase("Remark", headFont1));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            
            hcell = new PdfPCell(new Phrase("Status", headFont1));
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

                cell = new PdfPCell(new Phrase(getMoneyOut.getUserName(),headFont));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(getMoneyOut.getPersonName(),headFont));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setPaddingRight(5);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase(getMoneyOut.getOutDate().toString(),headFont));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPaddingRight(5);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase(String.valueOf(getMoneyOut.getOutAmt()),headFont));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPaddingRight(5);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase(String.valueOf(getMoneyOut.getTrClosedAmt()),headFont));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPaddingRight(5);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase(getMoneyOut.getOutRemark(),headFont));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setPaddingRight(5);
                table.addCell(cell);
                
                String status;
                if(getMoneyOut.getIsApproved()==0)
                {
                	status="UnApproved";
                }
                else
                {
                	status="Approved";
                }
                cell = new PdfPCell(new Phrase(status,headFont));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
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
        
            Logger.getLogger(GeneratePdfReport.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}*/