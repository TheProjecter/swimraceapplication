package generators;

import java.util.Collections;
import java.util.List;

import entities.Result;
import entities.ResultComparator;

import work.Operations;

public class GenerateHeatResults extends GenerateHeats {
	
	private static final long serialVersionUID = 1L;
	private Operations operations = new Operations();
	private List<Result> results;
	
	public GenerateHeatResults() {
        super();
        fillEventNames();
    }

	protected void generateHeats(java.awt.event.ActionEvent evt) {
		setResults(operations.returnResults(this.getSelectedEvent()));
		Collections.sort(results, new ResultComparator());
		operations.generateResultTable(results, this.getSelectedEvent());
	}

	public List<Result> getResults() {
		return results;
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}
}
