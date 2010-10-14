package pdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Element;


public class WritePdf {

	private static Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 22,
			Font.BOLD);
	private static Font headerFont = new Font(Font.FontFamily.COURIER, 16,
			Font.BOLD);
	private static String file = "d:/tmp/javaStuff/SwimTimer/FirstPdf.pdf";
	
	public static void main(String[] args) {
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();
			addContent(document);
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void addContent(Document document)
			throws DocumentException {

		Paragraph content = new Paragraph();
		/**
		 * Add the logo
		 */
		try {
			Image image = Image.getInstance("wyvern-predatux.png");
			image.setAlignment(Element.ALIGN_CENTER);
			document.add(image);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/**
		 * Add the Title paragraph
		 */
		Paragraph titleParagraph = new Paragraph("Concurs", titleFont);
		titleParagraph.setAlignment(Element.ALIGN_CENTER);
		document.add(titleParagraph);
		addEmptyLine(content, 2);
		document.add(content);
		
		/**
		 * Add the date
		 */
		Format format = new SimpleDateFormat("EEEE dd MMM yyyy");
		Paragraph dateParagraph = new Paragraph(format.format((new Date())), headerFont);
		document.add(dateParagraph);
		
		addEmptyLine(content, 8);

		content.add(new Paragraph(
				"This document is a preliminary version and not subject to your license agreement or any other agreement with vogella.de ;-)."));

		document.add(content);
		// Start a new page
		document.newPage();
	}

	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
}
