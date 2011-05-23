package generators.behaviors;

import java.util.Collections;
import java.util.List;

import pdfWriter.ResultWriter;
import work.Operations;
import entities.Event;
import entities.Result;
import entities.ResultComparator;

public class ResultHeatBehavior implements GenerateHeatBehavior {
	private Operations operations = new Operations();
	private List<Result> results;

	@Override
	public void generateHeats(String eventName, String poolType,
			String competitionTitle, String heatGender) {
		setResults(operations.returnResults(operations.returnEvent(eventName), heatGender, "Mixt"));
		Collections.sort(results, new ResultComparator());
		operations.generateResultTable(results,
				operations.returnEvent(eventName), heatGender);
		Event event = operations.returnEvent(eventName);
		if (heatGender.equals("Mixt")) {
			ResultWriter rWriterM = new ResultWriter(event, competitionTitle, heatGender, "M");			
			rWriterM.run();
			ResultWriter rWriterF = new ResultWriter(event, competitionTitle, heatGender, "F");			
			rWriterF.run();
		} else {
			ResultWriter rWriter = new ResultWriter(event, competitionTitle, heatGender, "Mixt");			
			rWriter.run();
		}
	}

	public List<Result> getResults() {
		return results;
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}

}
