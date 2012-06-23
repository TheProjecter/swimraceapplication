package pdfWriter;

import java.io.FileOutputStream;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import utils.Constants;
import work.Operations;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import entities.Event;
import entities.Heat;

public class JudgesListWriter {

	private static Font titleFont = new Font(Font.FontFamily.COURIER, 26, Font.BOLD);
	private static Font headerFont1 = new Font(Font.FontFamily.COURIER, 18, Font.BOLD);
	private static Font headerFont2 = new Font(Font.FontFamily.COURIER, 14, Font.BOLD);
	private static Font normalHeaderFont = new Font(Font.FontFamily.COURIER, 16, Font.BOLD);
	private static Font normalFont = new Font(Font.FontFamily.COURIER, 22, Font.NORMAL);
	private static String file;
	private String competitionTitle;

	private Event event;
	private Operations operations = new Operations();
	private Map<String, String> pathFile = new Constants().getDataFiles();
	private Heat heat;
	private String osName = new Constants().getOsName();

	public JudgesListWriter(Event event, String competitionTitle, Heat heat, String heatGender) {
		setEvent(event);
		setHeat(heat);
		setFile(pathFile.get("arbitrii") + (osName.toLowerCase().startsWith("linux") ? "/" : "\\") + "Foaie pt seria "
				+ heat.getHeatNumber() + " proba " + event.getName() + " " + heatGender + ".pdf");
		setCompetitionTitle(competitionTitle);
	}

	public void run() {
		try {
			Document document = new Document(PageSize.A4.rotate());
			PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();
			addContent(document);
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addContent(Document document) throws DocumentException {

		Paragraph twoEmptyLine = new Paragraph();
		addEmptyLine(twoEmptyLine, 2);
		Paragraph oneEmptyLine = new Paragraph();
		addEmptyLine(oneEmptyLine, 1);

		/**
		 * Add the Title paragraph
		 */
		Paragraph titleParagraph = new Paragraph(competitionTitle, titleFont);
		titleParagraph.setAlignment(Element.ALIGN_CENTER);
		document.add(titleParagraph);
		document.add(twoEmptyLine);

		/**
		 * Add the date
		 */
		Format format = new SimpleDateFormat("EEEE dd MMM yyyy");
		Paragraph dateParagraph = new Paragraph(format.format((new Date())), headerFont2);
		document.add(dateParagraph);
		document.add(twoEmptyLine);

		/**
		 * Add the event name
		 */
		Paragraph eventNameParagraph = new Paragraph(event.getName().toUpperCase(), headerFont1);
		eventNameParagraph.setAlignment(Element.ALIGN_CENTER);
		document.add(eventNameParagraph);
		document.add(oneEmptyLine);

		/**
		 * Add the heat number
		 */
		Paragraph heatNumberParagraph = new Paragraph("Seria " + heat.getHeatNumber(), headerFont2);
		heatNumberParagraph.setAlignment(Element.ALIGN_CENTER);
		document.add(heatNumberParagraph);
		document.add(oneEmptyLine);

		/**
		 * Table of heats
		 */
		PdfPTable table = getHeatTable();
		document.add(table);

		// Start a new page
		document.newPage();
	}

	private PdfPTable getHeatTable() {
		float[] tableWidth = { 0.07f, 0.65f, 0.28f };
		PdfPTable table = new PdfPTable(tableWidth);

		// add Column titles
		PdfPCell c21 = new PdfPCell(new Phrase("NR", normalHeaderFont));
		c21.setHorizontalAlignment(Element.ALIGN_CENTER);
		c21.setVerticalAlignment(Element.ALIGN_CENTER);
		table.addCell(c21);
		PdfPCell c22 = new PdfPCell(new Phrase("Nume", normalHeaderFont));
		c22.setHorizontalAlignment(Element.ALIGN_CENTER);
		c22.setVerticalAlignment(Element.ALIGN_CENTER);
		table.addCell(c22);
		PdfPCell c23 = new PdfPCell(new Phrase("Timp", normalHeaderFont));
		c23.setHorizontalAlignment(Element.ALIGN_CENTER);
		c23.setVerticalAlignment(Element.ALIGN_CENTER);
		table.addCell(c23);

		// lane 1 cells
		PdfPCell c31 = new PdfPCell(new Phrase(Integer.toString(heat.getLane1().getLaneNumber()), normalFont));
		table.addCell(c31);
		PdfPCell c32 = new PdfPCell(new Phrase(heat.getLane1().getSwimmer().getName(), normalFont));
		table.addCell(c32);
		table.addCell(new PdfPCell());

		// lane 2 cells
		PdfPCell c41 = new PdfPCell(new Phrase(Integer.toString(heat.getLane2().getLaneNumber()), normalFont));
		table.addCell(c41);
		PdfPCell c42 = new PdfPCell(new Phrase(heat.getLane2().getSwimmer().getName(), normalFont));
		table.addCell(c42);
		table.addCell(new PdfPCell());

		// lane 3 cells
		PdfPCell c51 = new PdfPCell(new Phrase(Integer.toString(heat.getLane3().getLaneNumber()), normalFont));
		table.addCell(c51);
		PdfPCell c52 = new PdfPCell(new Phrase(heat.getLane3().getSwimmer().getName(), normalFont));
		table.addCell(c52);
		table.addCell(new PdfPCell());

		// lane 4 cells
		PdfPCell c61 = new PdfPCell(new Phrase(Integer.toString(heat.getLane4().getLaneNumber()), normalFont));
		table.addCell(c61);
		PdfPCell c62 = new PdfPCell(new Phrase(heat.getLane4().getSwimmer().getName(), normalFont));
		table.addCell(c62);
		table.addCell(new PdfPCell());

		// lane 5 cells
		PdfPCell c71 = new PdfPCell(new Phrase(Integer.toString(heat.getLane5().getLaneNumber()), normalFont));
		table.addCell(c71);
		PdfPCell c72 = new PdfPCell(new Phrase(heat.getLane5().getSwimmer().getName(), normalFont));
		table.addCell(c72);
		table.addCell(new PdfPCell());

		// lane 6 cells
		PdfPCell c81 = new PdfPCell(new Phrase(Integer.toString(heat.getLane6().getLaneNumber()), normalFont));
		table.addCell(c81);
		PdfPCell c82 = new PdfPCell(new Phrase(heat.getLane6().getSwimmer().getName(), normalFont));
		table.addCell(c82);
		table.addCell(new PdfPCell());

		if (event.getPoolType().contains("50")) {

			// lane 7 cells
			PdfPCell c91 = new PdfPCell(new Phrase(Integer.toString(heat.getLane7().getLaneNumber()), normalFont));
			table.addCell(c91);
			PdfPCell c92 = new PdfPCell(new Phrase(heat.getLane7().getSwimmer().getName(), normalFont));
			table.addCell(c92);
			table.addCell(new PdfPCell());

			// lane 8 cells
			PdfPCell c101 = new PdfPCell(new Phrase(Integer.toString(heat.getLane8().getLaneNumber()), normalFont));
			table.addCell(c101);
			PdfPCell c102 = new PdfPCell(new Phrase(heat.getLane8().getSwimmer().getName(), normalFont));
			table.addCell(c102);
			table.addCell(new PdfPCell());
		} // end 50m pool extra lanes

		return table;
	}

	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

	private static String padLeft(String s, int n) {
		return String.format("%1$#" + n + "s", s).replace(' ', '0');
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public String getCompetitionTitle() {
		return competitionTitle;
	}

	public void setCompetitionTitle(String competitionTitle) {
		this.competitionTitle = competitionTitle;
	}

	public Heat getHeat() {
		return heat;
	}

	public void setHeat(Heat heat) {
		this.heat = heat;
	}

}
