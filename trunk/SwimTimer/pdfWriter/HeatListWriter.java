package pdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;

import work.Operations;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Element;

import entities.Event;
import entities.Heat;

public class HeatListWriter {

	private static Font titleFont = new Font(Font.FontFamily.COURIER, 26,
			Font.BOLD);
	private static Font headerFont1 = new Font(Font.FontFamily.COURIER, 18,
			Font.BOLD);
	private static Font headerFont2 = new Font(Font.FontFamily.COURIER, 14,
			Font.BOLD);
	private static Font normalFont = new Font(Font.FontFamily.COURIER, 12,
			Font.NORMAL);
	private static String file = "d:/tmp/javaStuff/SwimTimer/FirstPdf.pdf";

	private Event event;
	private Operations operations = new Operations();

	public HeatListWriter(Event event) {
		setEvent(event);
	}

	public void run() {
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

	private void addContent(Document document) throws DocumentException {

		Paragraph content = new Paragraph();
		addEmptyLine(content, 2);
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
		document.add(content);

		/**
		 * Add the date
		 */
		Format format = new SimpleDateFormat("EEEE dd MMM yyyy");
		Paragraph dateParagraph = new Paragraph(format.format((new Date())),
				headerFont1);
		document.add(dateParagraph);

		/**
		 * Add the event name
		 */
		document.add(content);
		Paragraph eventNameParagraph = new Paragraph(event.getName()
				.toUpperCase(), headerFont2);
		document.add(eventNameParagraph);

		// table
		PdfPTable table = getHeatTable();
		document.add(table);
		/**
		 * Table of heats
		 */

		// Start a new page
		document.newPage();
	}

	private PdfPTable createTable() throws BadElementException {
		PdfPTable table = new PdfPTable(3);

		// t.setBorderColor(BaseColor.GRAY);
		// t.setPadding(4);
		// t.setSpacing(4);
		// t.setBorderWidth(1);
		PdfPCell c1 = new PdfPCell(new Phrase("Table Header 1"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		disableBorders(c1);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Table Header 2"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Table Header 3"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		table.setHeaderRows(1);

		table.addCell("1.0");
		table.addCell("1.1");
		table.addCell("1.2");
		table.addCell("2.1");
		table.addCell("2.2");
		table.addCell("2.3");

		return table;

	}

	private PdfPTable getHeatTable() {
		PdfPTable table = new PdfPTable(5);

		List<Heat> heatList = operations.generateHeats(event,
				event.getPoolType());

		for (Heat heats : heatList) {
			// heat number cell
			PdfPCell c11 = new PdfPCell(new Phrase("Seria "
					+ heats.getHeatNumber(), normalFont));
			c11.setHorizontalAlignment(Element.ALIGN_CENTER);
			disableBorders(c11);
			table.addCell(c11);
			PdfPCell c12 = new PdfPCell(new Phrase(""));
			disableBorders(c12);
			table.addCell(c12);
			PdfPCell c13 = c12;
			disableBorders(c13);
			table.addCell(c13);
			PdfPCell c14 = c12;
			disableBorders(c14);
			table.addCell(c14);
			PdfPCell c15 = c12;
			disableBorders(c15);
			table.addCell(c15);
			
			// add Column titles
			
			// lane 1 cells
			PdfPCell c21 = new PdfPCell(new Phrase(Integer.toString(heats
					.getLane1().getLaneNumber()), normalFont));
			c21.setHorizontalAlignment(Element.ALIGN_LEFT);
			disableBorders(c21);
			table.addCell(c21);
			PdfPCell c22 = new PdfPCell(new Phrase(heats.getLane1()
					.getSwimmer().getName(), normalFont));
			c22.setHorizontalAlignment(Element.ALIGN_LEFT);
			disableBorders(c22);
			table.addCell(c22);
			PdfPCell c23 = new PdfPCell(new Phrase(heats.getLane1()
					.getSwimmer().getClub(), normalFont));
			c23.setHorizontalAlignment(Element.ALIGN_LEFT);
			disableBorders(c23);
			table.addCell(c23);
			PdfPCell c24 = new PdfPCell(new Phrase(heats.getLane1()
					.getSwimmer().getAgeGroup(), normalFont));
			c24.setHorizontalAlignment(Element.ALIGN_LEFT);
			disableBorders(c24);
			table.addCell(c24);
			PdfPCell c25 = new PdfPCell(new Phrase(padLeft(
					Integer.toString(heats.getLane1().getEntryMinutes()), 2)
					+ ":"
					+ padLeft(Integer.toString(heats.getLane1()
							.getEntrySecondes()), 2)
					+ ":"
					+ padLeft(Integer.toString(heats.getLane1()
							.getEntryMSeconds()), 2), normalFont));
			c25.setHorizontalAlignment(Element.ALIGN_LEFT);
			disableBorders(c25);
			table.addCell(c25);

			// // lane 2 paragraph
			// Paragraph lane2Paragraph = new Paragraph();
			// lane2Paragraph.setFont(normalFont);
			// lane2Paragraph.add("Culoar"
			// + ";"
			// + heats.getLane2().getLaneNumber()
			// + ";"
			// + heats.getLane2().getSwimmer().getName()
			// + ";"
			// + heats.getLane2().getSwimmer().getClub()
			// + ";"
			// + heats.getLane2().getSwimmer().getAgeGroup()
			// + ";"
			// + padLeft(Integer.toString(heats.getLane2()
			// .getEntryMinutes()), 2)
			// + ":"
			// + padLeft(Integer.toString(heats.getLane2()
			// .getEntrySecondes()), 2)
			// + ":"
			// + padLeft(Integer.toString(heats.getLane2()
			// .getEntryMSeconds()), 2));
			// // lane 3 paragraph
			// Paragraph lane3Paragraph = new Paragraph();
			// lane3Paragraph.setFont(normalFont);
			// lane3Paragraph.add("Culoar"
			// + ";"
			// + heats.getLane3().getLaneNumber()
			// + ";"
			// + heats.getLane3().getSwimmer().getName()
			// + ";"
			// + heats.getLane3().getSwimmer().getClub()
			// + ";"
			// + heats.getLane3().getSwimmer().getAgeGroup()
			// + ";"
			// + padLeft(Integer.toString(heats.getLane3()
			// .getEntryMinutes()), 2)
			// + ":"
			// + padLeft(Integer.toString(heats.getLane3()
			// .getEntrySecondes()), 2)
			// + ":"
			// + padLeft(Integer.toString(heats.getLane3()
			// .getEntryMSeconds()), 2));
			// // lane 4 paragraph
			// Paragraph lane4Paragraph = new Paragraph();
			// lane4Paragraph.setFont(normalFont);
			// lane4Paragraph.add("Culoar"
			// + ";"
			// + heats.getLane4().getLaneNumber()
			// + ";"
			// + heats.getLane4().getSwimmer().getName()
			// + ";"
			// + heats.getLane4().getSwimmer().getClub()
			// + ";"
			// + heats.getLane4().getSwimmer().getAgeGroup()
			// + ";"
			// + padLeft(Integer.toString(heats.getLane4()
			// .getEntryMinutes()), 2)
			// + ":"
			// + padLeft(Integer.toString(heats.getLane4()
			// .getEntrySecondes()), 2)
			// + ":"
			// + padLeft(Integer.toString(heats.getLane4()
			// .getEntryMSeconds()), 2));
			// // lane 5 paragraph
			// Paragraph lane5Paragraph = new Paragraph();
			// lane5Paragraph.setFont(normalFont);
			// lane5Paragraph.add("Culoar"
			// + ";"
			// + heats.getLane5().getLaneNumber()
			// + ";"
			// + heats.getLane5().getSwimmer().getName()
			// + ";"
			// + heats.getLane5().getSwimmer().getClub()
			// + ";"
			// + heats.getLane5().getSwimmer().getAgeGroup()
			// + ";"
			// + padLeft(Integer.toString(heats.getLane5()
			// .getEntryMinutes()), 2)
			// + ":"
			// + padLeft(Integer.toString(heats.getLane5()
			// .getEntrySecondes()), 2)
			// + ":"
			// + padLeft(Integer.toString(heats.getLane5()
			// .getEntryMSeconds()), 2));
			// // lane 6 paragraph
			// Paragraph lane6Paragraph = new Paragraph();
			// lane6Paragraph.setFont(normalFont);
			// lane6Paragraph.add("Culoar"
			// + ";"
			// + heats.getLane6().getLaneNumber()
			// + ";"
			// + heats.getLane6().getSwimmer().getName()
			// + ";"
			// + heats.getLane6().getSwimmer().getClub()
			// + ";"
			// + heats.getLane6().getSwimmer().getAgeGroup()
			// + ";"
			// + padLeft(Integer.toString(heats.getLane6()
			// .getEntryMinutes()), 2)
			// + ":"
			// + padLeft(Integer.toString(heats.getLane6()
			// .getEntrySecondes()), 2)
			// + ":"
			// + padLeft(Integer.toString(heats.getLane6()
			// .getEntryMSeconds()), 2));
			// // lane 7 paragraph
			// Paragraph lane7Paragraph = new Paragraph();
			// lane7Paragraph.setFont(normalFont);
			// lane7Paragraph.add("Culoar"
			// + ";"
			// + heats.getLane7().getLaneNumber()
			// + ";"
			// + heats.getLane7().getSwimmer().getName()
			// + ";"
			// + heats.getLane7().getSwimmer().getClub()
			// + ";"
			// + heats.getLane7().getSwimmer().getAgeGroup()
			// + ";"
			// + padLeft(Integer.toString(heats.getLane7()
			// .getEntryMinutes()), 2)
			// + ":"
			// + padLeft(Integer.toString(heats.getLane7()
			// .getEntrySecondes()), 2)
			// + ":"
			// + padLeft(Integer.toString(heats.getLane7()
			// .getEntryMSeconds()), 2));
			// // lane 8 paragraph
			// Paragraph lane8Paragraph = new Paragraph();
			// lane8Paragraph.setFont(normalFont);
			// lane8Paragraph.add("Culoar"
			// + ";"
			// + heats.getLane8().getLaneNumber()
			// + ";"
			// + heats.getLane8().getSwimmer().getName()
			// + ";"
			// + heats.getLane8().getSwimmer().getClub()
			// + ";"
			// + heats.getLane8().getSwimmer().getAgeGroup()
			// + ";"
			// + padLeft(Integer.toString(heats.getLane8()
			// .getEntryMinutes()), 2)
			// + ":"
			// + padLeft(Integer.toString(heats.getLane8()
			// .getEntrySecondes()), 2)
			// + ":"
			// + padLeft(Integer.toString(heats.getLane8()
			// .getEntryMSeconds()), 2));

			// document.add(heatNumberParagraph);
			// document.add(lane1Paragraph);
			// document.add(lane2Paragraph);
			// document.add(lane3Paragraph);
			// document.add(lane4Paragraph);
			// document.add(lane5Paragraph);
			// document.add(lane6Paragraph);
			// document.add(lane7Paragraph);
			// document.add(lane8Paragraph);
		}

		return table;
	}

	private void disableBorders(PdfPCell cell) {
		cell.disableBorderSide(PdfPCell.LEFT);
		cell.disableBorderSide(PdfPCell.RIGHT);
		cell.disableBorderSide(PdfPCell.TOP);
		cell.disableBorderSide(PdfPCell.BOTTOM);
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
}
