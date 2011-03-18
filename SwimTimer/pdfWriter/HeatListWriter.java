package pdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import utils.Constants;
import work.Operations;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Element;

import entities.Event;
import entities.Heat;
import entities.HeatComparator;

public class HeatListWriter {

	private static Font titleFont = new Font(Font.FontFamily.COURIER, 26,
			Font.BOLD);
	private static Font headerFont1 = new Font(Font.FontFamily.COURIER, 18,
			Font.BOLD);
	private static Font headerFont2 = new Font(Font.FontFamily.COURIER, 14,
			Font.BOLD);
	private static Font normalHeaderFont = new Font(Font.FontFamily.COURIER, 10,
			Font.BOLD);
	private static Font normalFont = new Font(Font.FontFamily.COURIER, 10,
			Font.NORMAL);
	private static String file;
	private String competitionTitle;

	private Event event;
	private Operations operations = new Operations();
	private Map<String, String> pathFile = new Constants().getDataFiles();
	private List<Heat> heatList;
	
	public HeatListWriter(Event event, String competitionTitle, List<Heat> heatList) {
		setEvent(event);
		setHeatList(heatList);
		setFile(pathFile.get("serii") + "\\" + "Serii " + event.getName()+".pdf");
		setCompetitionTitle(competitionTitle);
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

		Paragraph twoEmptyLine = new Paragraph();
		addEmptyLine(twoEmptyLine, 2);
		Paragraph oneEmptyLine = new Paragraph();
		addEmptyLine(oneEmptyLine, 1);
		/**
		 * Add the logo
		 */
		try {
			Image image = Image.getInstance(pathFile.get("util") + "\\logo.png");
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
		Paragraph titleParagraph = new Paragraph(competitionTitle, titleFont);
		titleParagraph.setAlignment(Element.ALIGN_CENTER);
		document.add(titleParagraph);
		document.add(twoEmptyLine);

		/**
		 * Add the date
		 */
		Format format = new SimpleDateFormat("EEEE dd MMM yyyy");
		Paragraph dateParagraph = new Paragraph(format.format((new Date())),
				headerFont1);
		document.add(dateParagraph);
		document.add(twoEmptyLine);

		/**
		 * Add the event name
  	    */
		Paragraph eventNameParagraph = new Paragraph(event.getName()
				.toUpperCase(), headerFont2);
		document.add(eventNameParagraph);
		document.add(oneEmptyLine);

		// table
		PdfPTable table = getHeatTable();
		document.add(table);
		/**
		 * Table of heats
		 */

		// Start a new page
		document.newPage();
	}

	private PdfPTable getHeatTable() {
		float[] tableWidth = { 0.04f, 0.36f, 0.30f, 0.14f, 0.16f};
		PdfPTable table = new PdfPTable(tableWidth);

		// add Column titles
		PdfPCell c21 = new PdfPCell(new Phrase("Nr", normalHeaderFont));
		c21.setHorizontalAlignment(Element.ALIGN_CENTER);
		disableBorders(c21);
		table.addCell(c21);
		PdfPCell c22 = new PdfPCell(new Phrase("Nume", normalHeaderFont));
		c21.setHorizontalAlignment(Element.ALIGN_CENTER);
		disableBorders(c22);
		table.addCell(c22);
		PdfPCell c23 = new PdfPCell(new Phrase("Club", normalHeaderFont));
		c21.setHorizontalAlignment(Element.ALIGN_CENTER);
		disableBorders(c23);
		table.addCell(c23);
		PdfPCell c24 = new PdfPCell(new Phrase("Categorie", normalHeaderFont));
		c21.setHorizontalAlignment(Element.ALIGN_CENTER);
		disableBorders(c24);
		table.addCell(c24);
		PdfPCell c25 = new PdfPCell(new Phrase("Timp", normalHeaderFont));
		c21.setHorizontalAlignment(Element.ALIGN_RIGHT);
		disableBorders(c25);
		table.addCell(c25);

		for (Heat heats : heatList) {
			// heat number cell
			PdfPCell c11 = new PdfPCell(new Phrase("Seria "
					+ heats.getHeatNumber(), normalHeaderFont));
			c11.setHorizontalAlignment(Element.ALIGN_CENTER);
			disableBorders(c11);
			c11.setColspan(5);
			table.addCell(c11);
			
			// lane 1 cells
			PdfPCell c31 = new PdfPCell(new Phrase(Integer.toString(heats
					.getLane1().getLaneNumber()), normalFont));
			c31.setHorizontalAlignment(Element.ALIGN_LEFT);
			disableBorders(c31);
			table.addCell(c31);
			PdfPCell c32 = new PdfPCell(new Phrase(heats.getLane1()
					.getSwimmer().getName(), normalFont));
			c32.setHorizontalAlignment(Element.ALIGN_LEFT);
			disableBorders(c32);
			table.addCell(c32);
			PdfPCell c33 = new PdfPCell(new Phrase(heats.getLane1()
					.getSwimmer().getClub(), normalFont));
			c33.setHorizontalAlignment(Element.ALIGN_LEFT);
			disableBorders(c33);
			table.addCell(c33);
			PdfPCell c34 = new PdfPCell(new Phrase(heats.getLane1()
					.getSwimmer().getAgeGroup(), normalFont));
			c34.setHorizontalAlignment(Element.ALIGN_LEFT);
			disableBorders(c34);
			table.addCell(c34);
			PdfPCell c35 = new PdfPCell(new Phrase(padLeft(
					Integer.toString(heats.getLane1().getEntryMinutes()), 2)
					+ ":"
					+ padLeft(Integer.toString(heats.getLane1()
							.getEntrySecondes()), 2)
					+ ","
					+ padLeft(Integer.toString(heats.getLane1()
							.getEntryMSeconds()), 2), normalFont));
			c35.setHorizontalAlignment(Element.ALIGN_LEFT);
			disableBorders(c35);
			table.addCell(c35);

			// lane 2 cells
			PdfPCell c41 = new PdfPCell(new Phrase(Integer.toString(heats
					.getLane2().getLaneNumber()), normalFont));
			c41.setHorizontalAlignment(Element.ALIGN_LEFT);
			disableBorders(c41);
			table.addCell(c41);
			PdfPCell c42 = new PdfPCell(new Phrase(heats.getLane2()
					.getSwimmer().getName(), normalFont));
			c42.setHorizontalAlignment(Element.ALIGN_LEFT);
			disableBorders(c42);
			table.addCell(c42);
			PdfPCell c43 = new PdfPCell(new Phrase(heats.getLane2()
					.getSwimmer().getClub(), normalFont));
			c43.setHorizontalAlignment(Element.ALIGN_LEFT);
			disableBorders(c43);
			table.addCell(c43);
			PdfPCell c44 = new PdfPCell(new Phrase(heats.getLane2()
					.getSwimmer().getAgeGroup(), normalFont));
			c44.setHorizontalAlignment(Element.ALIGN_LEFT);
			disableBorders(c44);
			table.addCell(c44);
			PdfPCell c45 = new PdfPCell(new Phrase(padLeft(
					Integer.toString(heats.getLane2().getEntryMinutes()), 2)
					+ ":"
					+ padLeft(Integer.toString(heats.getLane2()
							.getEntrySecondes()), 2)
					+ ","
					+ padLeft(Integer.toString(heats.getLane2()
							.getEntryMSeconds()), 2), normalFont));
			c45.setHorizontalAlignment(Element.ALIGN_LEFT);
			disableBorders(c45);
			table.addCell(c45);

			// lane 3 cells
			PdfPCell c51 = new PdfPCell(new Phrase(Integer.toString(heats
					.getLane3().getLaneNumber()), normalFont));
			c51.setHorizontalAlignment(Element.ALIGN_LEFT);
			disableBorders(c51);
			table.addCell(c51);
			PdfPCell c52 = new PdfPCell(new Phrase(heats.getLane3()
					.getSwimmer().getName(), normalFont));
			c52.setHorizontalAlignment(Element.ALIGN_LEFT);
			disableBorders(c52);
			table.addCell(c52);
			PdfPCell c53 = new PdfPCell(new Phrase(heats.getLane3()
					.getSwimmer().getClub(), normalFont));
			c53.setHorizontalAlignment(Element.ALIGN_LEFT);
			disableBorders(c53);
			table.addCell(c53);
			PdfPCell c54 = new PdfPCell(new Phrase(heats.getLane3()
					.getSwimmer().getAgeGroup(), normalFont));
			c54.setHorizontalAlignment(Element.ALIGN_LEFT);
			disableBorders(c54);
			table.addCell(c54);
			PdfPCell c55 = new PdfPCell(new Phrase(padLeft(
					Integer.toString(heats.getLane3().getEntryMinutes()), 2)
					+ ":"
					+ padLeft(Integer.toString(heats.getLane3()
							.getEntrySecondes()), 2)
					+ ","
					+ padLeft(Integer.toString(heats.getLane3()
							.getEntryMSeconds()), 2), normalFont));
			c55.setHorizontalAlignment(Element.ALIGN_LEFT);
			disableBorders(c55);
			table.addCell(c55);

			// lane 4 cells
			PdfPCell c61 = new PdfPCell(new Phrase(Integer.toString(heats
					.getLane4().getLaneNumber()), normalFont));
			c61.setHorizontalAlignment(Element.ALIGN_LEFT);
			disableBorders(c61);
			table.addCell(c61);
			PdfPCell c62 = new PdfPCell(new Phrase(heats.getLane4()
					.getSwimmer().getName(), normalFont));
			c62.setHorizontalAlignment(Element.ALIGN_LEFT);
			disableBorders(c62);
			table.addCell(c62);
			PdfPCell c63 = new PdfPCell(new Phrase(heats.getLane4()
					.getSwimmer().getClub(), normalFont));
			c63.setHorizontalAlignment(Element.ALIGN_LEFT);
			disableBorders(c63);
			table.addCell(c63);
			PdfPCell c64 = new PdfPCell(new Phrase(heats.getLane4()
					.getSwimmer().getAgeGroup(), normalFont));
			c64.setHorizontalAlignment(Element.ALIGN_LEFT);
			disableBorders(c64);
			table.addCell(c64);
			PdfPCell c65 = new PdfPCell(new Phrase(padLeft(
					Integer.toString(heats.getLane4().getEntryMinutes()), 2)
					+ ":"
					+ padLeft(Integer.toString(heats.getLane4()
							.getEntrySecondes()), 2)
					+ ","
					+ padLeft(Integer.toString(heats.getLane4()
							.getEntryMSeconds()), 2), normalFont));
			c65.setHorizontalAlignment(Element.ALIGN_LEFT);
			disableBorders(c65);
			table.addCell(c65);

			// lane 5 cells
			PdfPCell c71 = new PdfPCell(new Phrase(Integer.toString(heats
					.getLane5().getLaneNumber()), normalFont));
			c71.setHorizontalAlignment(Element.ALIGN_LEFT);
			disableBorders(c71);
			table.addCell(c71);
			PdfPCell c72 = new PdfPCell(new Phrase(heats.getLane5()
					.getSwimmer().getName(), normalFont));
			c72.setHorizontalAlignment(Element.ALIGN_LEFT);
			disableBorders(c72);
			table.addCell(c72);
			PdfPCell c73 = new PdfPCell(new Phrase(heats.getLane5()
					.getSwimmer().getClub(), normalFont));
			c73.setHorizontalAlignment(Element.ALIGN_LEFT);
			disableBorders(c73);
			table.addCell(c73);
			PdfPCell c74 = new PdfPCell(new Phrase(heats.getLane5()
					.getSwimmer().getAgeGroup(), normalFont));
			c74.setHorizontalAlignment(Element.ALIGN_LEFT);
			disableBorders(c74);
			table.addCell(c74);
			PdfPCell c75 = new PdfPCell(new Phrase(padLeft(
					Integer.toString(heats.getLane5().getEntryMinutes()), 2)
					+ ":"
					+ padLeft(Integer.toString(heats.getLane5()
							.getEntrySecondes()), 2)
					+ ","
					+ padLeft(Integer.toString(heats.getLane5()
							.getEntryMSeconds()), 2), normalFont));
			c75.setHorizontalAlignment(Element.ALIGN_LEFT);
			disableBorders(c75);
			table.addCell(c75);

			// lane 6 cells
			PdfPCell c81 = new PdfPCell(new Phrase(Integer.toString(heats
					.getLane6().getLaneNumber()), normalFont));
			c81.setHorizontalAlignment(Element.ALIGN_LEFT);
			disableBorders(c81);
			table.addCell(c81);
			PdfPCell c82 = new PdfPCell(new Phrase(heats.getLane6()
					.getSwimmer().getName(), normalFont));
			c82.setHorizontalAlignment(Element.ALIGN_LEFT);
			disableBorders(c82);
			table.addCell(c82);
			PdfPCell c83 = new PdfPCell(new Phrase(heats.getLane6()
					.getSwimmer().getClub(), normalFont));
			c83.setHorizontalAlignment(Element.ALIGN_LEFT);
			disableBorders(c83);
			table.addCell(c83);
			PdfPCell c84 = new PdfPCell(new Phrase(heats.getLane6()
					.getSwimmer().getAgeGroup(), normalFont));
			c84.setHorizontalAlignment(Element.ALIGN_LEFT);
			disableBorders(c84);
			table.addCell(c84);
			PdfPCell c85 = new PdfPCell(new Phrase(padLeft(
					Integer.toString(heats.getLane6().getEntryMinutes()), 2)
					+ ":"
					+ padLeft(Integer.toString(heats.getLane6()
							.getEntrySecondes()), 2)
					+ ","
					+ padLeft(Integer.toString(heats.getLane6()
							.getEntryMSeconds()), 2), normalFont));
			c85.setHorizontalAlignment(Element.ALIGN_LEFT);
			disableBorders(c85);
			table.addCell(c85);

			if (event.getPoolType().contains("50")) {
				
				// lane 7 cells
				PdfPCell c91 = new PdfPCell(new Phrase(Integer.toString(heats
						.getLane7().getLaneNumber()), normalFont));
				c91.setHorizontalAlignment(Element.ALIGN_LEFT);
				disableBorders(c91);
				table.addCell(c91);
				PdfPCell c92 = new PdfPCell(new Phrase(heats.getLane7()
						.getSwimmer().getName(), normalFont));
				c92.setHorizontalAlignment(Element.ALIGN_LEFT);
				disableBorders(c92);
				table.addCell(c92);
				PdfPCell c93 = new PdfPCell(new Phrase(heats.getLane7()
						.getSwimmer().getClub(), normalFont));
				c93.setHorizontalAlignment(Element.ALIGN_LEFT);
				disableBorders(c93);
				table.addCell(c93);
				PdfPCell c94 = new PdfPCell(new Phrase(heats.getLane7()
						.getSwimmer().getAgeGroup(), normalFont));
				c94.setHorizontalAlignment(Element.ALIGN_LEFT);
				disableBorders(c94);
				table.addCell(c94);
				PdfPCell c95 = new PdfPCell(new Phrase(padLeft(
						Integer.toString(heats.getLane7().getEntryMinutes()), 2)
						+ ":"
						+ padLeft(Integer.toString(heats.getLane7()
								.getEntrySecondes()), 2)
						+ ","
						+ padLeft(Integer.toString(heats.getLane7()
								.getEntryMSeconds()), 2), normalFont));
				c95.setHorizontalAlignment(Element.ALIGN_LEFT);
				disableBorders(c95);
				table.addCell(c95);

				// lane 8 cells
				PdfPCell c10_1 = new PdfPCell(new Phrase(Integer.toString(heats
						.getLane8().getLaneNumber()), normalFont));
				c10_1.setHorizontalAlignment(Element.ALIGN_LEFT);
				disableBorders(c10_1);
				table.addCell(c10_1);
				PdfPCell c10_2 = new PdfPCell(new Phrase(heats.getLane8()
						.getSwimmer().getName(), normalFont));
				c10_2.setHorizontalAlignment(Element.ALIGN_LEFT);
				disableBorders(c10_2);
				table.addCell(c10_2);
				PdfPCell c10_3 = new PdfPCell(new Phrase(heats.getLane8()
						.getSwimmer().getClub(), normalFont));
				c10_3.setHorizontalAlignment(Element.ALIGN_LEFT);
				disableBorders(c10_3);
				table.addCell(c10_3);
				PdfPCell c10_4 = new PdfPCell(new Phrase(heats.getLane8()
						.getSwimmer().getAgeGroup(), normalFont));
				c10_4.setHorizontalAlignment(Element.ALIGN_LEFT);
				disableBorders(c10_4);
				table.addCell(c10_4);
				PdfPCell c10_5 = new PdfPCell(new Phrase(padLeft(
						Integer.toString(heats.getLane8().getEntryMinutes()), 2)
						+ ":"
						+ padLeft(Integer.toString(heats.getLane8()
								.getEntrySecondes()), 2)
						+ ","
						+ padLeft(Integer.toString(heats.getLane8()
								.getEntryMSeconds()), 2), normalFont));
				c10_5.setHorizontalAlignment(Element.ALIGN_LEFT);
				disableBorders(c10_5);
				table.addCell(c10_5);
			} // end 50m pool extra lanes
			
			// add empty line after each heat
			PdfPCell c11_1 = new PdfPCell(new Phrase(" "));
			c11_1.setHorizontalAlignment(Element.ALIGN_CENTER);
			disableBorders(c11_1);
			table.addCell(c11_1);
			PdfPCell c11_2 = new PdfPCell(new Phrase(" "));
			disableBorders(c11_2);
			table.addCell(c11_2);
			PdfPCell c11_3 = new PdfPCell(new Phrase(" "));
			disableBorders(c11_3);
			table.addCell(c11_3);
			PdfPCell c11_4 = new PdfPCell(new Phrase(" "));
			disableBorders(c11_4);
			table.addCell(c11_4);
			PdfPCell c11_5 = new PdfPCell(new Phrase(" "));
			disableBorders(c11_5);
			table.addCell(c11_5);
			
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

	public String getCompetitionTitle() {
		return competitionTitle;
	}

	public void setCompetitionTitle(String competitionTitle) {
		this.competitionTitle = competitionTitle;
	}

	public List<Heat> getHeatList() {
		return heatList;
	}

	public void setHeatList(List<Heat> heatList) {
		this.heatList = heatList;
	}
}
