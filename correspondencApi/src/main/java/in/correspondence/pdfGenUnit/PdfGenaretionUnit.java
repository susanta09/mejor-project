package in.correspondence.pdfGenUnit;

import java.awt.Color;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

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

import in.correspondence.responce.CoEntityR;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class PdfGenaretionUnit {
	
	
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(3);
         
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(Color.WHITE);
        font.setSize(10);
        
        
         
        cell.setPhrase(new Phrase("C_Num", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("TracingId", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("P_Name", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("P_Status", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("B_Amount", font));
        table.addCell(cell);  
         
        cell.setPhrase(new Phrase("N_PrintDate", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("N_Status", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Action", font));
        table.addCell(cell); 
        
        cell.setPhrase(new Phrase("StartDate", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("EndDate", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("N_S3Url", font));
        table.addCell(cell);
    }
     
    private void writeTableData(PdfPTable table,List<CoEntityR> corl) {
    	for (CoEntityR coEntityR : corl) {
    		table.addCell(String.valueOf(coEntityR.getCaseNum()));
    		table.addCell(String.valueOf(coEntityR.getTraceId()));
    		table.addCell(String.valueOf(coEntityR.getPlanName()));
    		table.addCell(String.valueOf(coEntityR.getPlanStatus()));
    		table.addCell(String.valueOf(coEntityR.getBenefitAmount()));
    		table.addCell(String.valueOf(coEntityR.getNoticePrintDate()));
    		table.addCell(String.valueOf(coEntityR.getNoticeStatus()));
    		table.addCell(String.valueOf(coEntityR.getPrint()));
    		table.addCell(String.valueOf(coEntityR.getStartDate()));
    		table.addCell(String.valueOf(coEntityR.getEndDate()));
    		table.addCell(String.valueOf(coEntityR.getNoticeS3Url()));
		}
    }
    public void export(HttpServletResponse response,List<CoEntityR> corl) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
       
        Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fontTiltle.setSize(8);
		// Creating paragraph
		Paragraph paragraph = new Paragraph("Notices of IES", fontTiltle);
		// Aligning the paragraph in document
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);
		// Adding the created paragraph in document
		document.add(paragraph);
		
		 PdfPTable table = new PdfPTable(11);
	        table.setWidthPercentage(100);
	        table.setWidths(new float[] {3,3,3,3,3,3,3,3,3,3,3});
	        table.setSpacingBefore(5);
         
        writeTableHeader(table);
        writeTableData(table,corl);
         
        document.add(table);
     
        document.close();
         
    }
	 public void exportToPDF(HttpServletResponse response,List<CoEntityR> corl) throws DocumentException, IOException {
	        response.setContentType("application/pdf");
	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	        String currentDateTime = dateFormatter.format(new Date());
	         
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=NoticeFromIES_" + currentDateTime + ".pdf";
	        response.setHeader(headerKey, headerValue);
	         
	       export(response,corl);
	         
	    }

}
