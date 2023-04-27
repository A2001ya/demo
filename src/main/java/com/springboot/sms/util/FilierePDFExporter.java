package com.springboot.sms.util;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.springboot.sms.entity.Filiere;

public class FilierePDFExporter {

	private final List<Filiere> sList;

    public FilierePDFExporter(List<Filiere> sList) {
		super();
		this.sList = sList;
	}

	private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.GRAY);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.COURIER);
        font.setColor(Color.WHITE);
        font.setStyle("font-weight: bold;");

        cell.setPhrase(new Phrase("ID", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Filiere", font));
        table.addCell(cell);

        
        cell.setPhrase(new Phrase("Departement", font));
        table.addCell(cell);

    }

    private void writeTableData(PdfPTable table) {
        for (Filiere filiere : sList) {
            table.addCell(String.valueOf(filiere.getId()));
            table.addCell(String.valueOf(filiere.getNomFiliere()));
            table.addCell(String.valueOf(filiere.getDepartement().getName()));
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER_BOLD);
        font.setSize(16);
        font.setColor(Color.ORANGE);

        Paragraph paragraph = new Paragraph("Filiere List", font);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(paragraph);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);
        document.close();
    }
}
