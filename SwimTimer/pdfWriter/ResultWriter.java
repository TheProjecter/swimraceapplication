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

import utils.AgeGroup;
import utils.Calculations;
import utils.Constants;
import work.Operations;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import entities.Event;
import entities.Result;
import entities.ResultComparator;

public class ResultWriter {

	private static Font titleFont = new Font(Font.FontFamily.COURIER, 26,
			Font.BOLD);
	private static Font headerFont1 = new Font(Font.FontFamily.COURIER, 18,
			Font.BOLD);
	private static Font headerFont2 = new Font(Font.FontFamily.COURIER, 14,
			Font.BOLD);
	private static Font normalHeaderFont = new Font(Font.FontFamily.COURIER,
			10, Font.BOLD);
	private static Font normalFont = new Font(Font.FontFamily.COURIER, 10,
			Font.NORMAL);
	private static String file;
	private String competitionTitle;

	private Event event;
	private Operations operations = new Operations();
	private Calculations calculations = Calculations.getInstance();
	private Map<String, String> pathFile = new Constants().getDataFiles();

	public ResultWriter(Event event, String competitionTitle) {
		setEvent(event);
		setFile(pathFile.get("rezultate") + "\\" + "Rezultate " + event.getName() + ".pdf");
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
		PdfPTable table = getResultsTable();
		document.add(table);
		/**
		 * Table of heats
		 */

		// Start a new page
		document.newPage();

	}

	private PdfPTable getResultsTable() {
		float[] tableWidth = { 0.04f, 0.26f, 0.21f, 0.12f, 0.14f, 0.12f, 0.04f, 0.06f };
		PdfPTable table = new PdfPTable(tableWidth);

		// get the results and order them after the time
		List<Result> results = operations.returnResults(event);
		Collections.sort(results, new ResultComparator());
		// get the age-groups
		AgeGroup ageGroup = new AgeGroup();
		List<String> ageGroups = ageGroup.getAgeGroups();

		int classification = 1;

		for (String age : ageGroups) {
			if (operations.searchAgeGroupInResult(results, age)) {
				// Age-group
				PdfPCell c11 = new PdfPCell(new Phrase(age, normalHeaderFont));
				c11.setHorizontalAlignment(Element.ALIGN_LEFT);
				disableBorders(c11);
				c11.setColspan(8);
				table.addCell(c11);

				for (Result res : results) {
					if (res.getSwimmer().getAgeGroup().equals(age)) {
						// add the swimmer
						PdfPCell c21 = new PdfPCell(new Phrase(
								Integer.toString(classification), normalFont));
						c21.setHorizontalAlignment(Element.ALIGN_CENTER);
						disableBorders(c21);
						table.addCell(c21);
						PdfPCell c22 = new PdfPCell(new Phrase(res.getSwimmer()
								.getName(), normalFont));
						disableBorders(c22);
						table.addCell(c22);
						PdfPCell c23 = new PdfPCell(new Phrase(res.getSwimmer()
								.getClub(), normalFont));
						disableBorders(c23);
						table.addCell(c23);
						PdfPCell c24 = new PdfPCell(new Phrase(res.getSwimmer()
								.getBirthYear(), normalFont));
						disableBorders(c24);
						table.addCell(c24);
						PdfPCell c25 = new PdfPCell(new Phrase(padLeft(
								Integer.toString(res.getResultMinutes()), 2)
								+ ":"
								+ padLeft(Integer.toString(res
										.getResultSecondes()), 2)
								+ ","
								+ padLeft(Integer.toString(res
										.getResultMSeconds()), 2), normalFont));
						disableBorders(c25);
						table.addCell(c25);
						PdfPCell c26 = new PdfPCell(
								new Phrase(
										calculations
												.calculateFinaPoints(
														res.getSwimTimeSeconds(),
														calculations
																.getBaseTimeForEvent(event)),
										normalFont));
						disableBorders(c26);
						table.addCell(c26);
						PdfPCell c27 = new PdfPCell(new Phrase(
								Integer.toString(calculations
										.calculateLocalPoints(classification)),
								normalFont));
						disableBorders(c27);
						table.addCell(c27);
						PdfPCell c28 = new PdfPCell(new Phrase(res.getPerformanceStatus(), normalFont));
						disableBorders(c28);
						table.addCell(c28);
						classification++;
					}
				}

				classification = 1;

				// add empty line
				PdfPCell c31 = new PdfPCell(new Phrase(" ", normalHeaderFont));
				c31.setHorizontalAlignment(Element.ALIGN_LEFT);
				disableBorders(c31);
				c31.setColspan(8);
				table.addCell(c31);

			}
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

	public static String getFile() {
		return file;
	}

	public static void setFile(String file) {
		ResultWriter.file = file;
	}

	public String getCompetitionTitle() {
		return competitionTitle;
	}

	public void setCompetitionTitle(String competitionTitle) {
		this.competitionTitle = competitionTitle;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

}
