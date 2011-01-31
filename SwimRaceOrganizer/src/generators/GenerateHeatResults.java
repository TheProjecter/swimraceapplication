package generators;

import java.util.Collections;
import java.util.List;

import pdfWriter.ResultWriter;
import work.Operations;
import entities.Event;
import entities.Result;
import entities.ResultComparator;

public class GenerateHeatResults extends GenerateHeats {
	
	private static final long serialVersionUID = 1L;
	private Operations operations = new Operations();
	private List<Result> results;
	private String competitionTitle;
	
	public GenerateHeatResults(String poolType, String competitionTitle, String title) {
        super(poolType, competitionTitle, title);
        setCompetitionTitle(competitionTitle);
        fillEventNames();
        System.out.println("result...");
	}

	protected void generateHeats(java.awt.event.ActionEvent evt) {
		setResults(operations.returnResults(this.getSelectedEvent()));
		Collections.sort(results, new ResultComparator());
		operations.generateResultTable(results, this.getSelectedEvent());
		Event event = getSelectedEvent();
		ResultWriter rWriter = new ResultWriter(event, competitionTitle);
		rWriter.run();
		dispose();
	}

	public List<Result> getResults() {
		return results;
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}

	public String getCompetitionTitle() {
		return competitionTitle;
	}

	public void setCompetitionTitle(String competitionTitle) {
		this.competitionTitle = competitionTitle;
	}
}
