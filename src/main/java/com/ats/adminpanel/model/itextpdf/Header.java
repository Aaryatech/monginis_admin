package com.ats.adminpanel.model.itextpdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class Header extends PdfPageEventHelper {
	 
    protected Phrase header;
 
    public void setHeader(Phrase header) {
        this.header = header;
    }
 
    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        PdfContentByte canvas = writer.getDirectContentUnder();
        ColumnText.showTextAligned(
            canvas, Element.ALIGN_RIGHT, header, 559, 806, 0);
    }
}