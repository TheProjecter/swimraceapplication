package generators.behaviors;

import java.util.List;

import pdfWriter.HeatListWriter;
import pdfWriter.JudgesListWriter;
import work.Operations;
import entities.Heat;

public class CreateHeatsBehavior implements GenerateHeatBehavior,
		SwimmerRelated {

	private Operations operations = new Operations();
	private int swimmersPerHeat;

	@Override
	public void generateHeats(String eventName, String poolType,
			String competitionTitle, String heatGender) {
		// get the list with the heats
		List<Heat> heatList = operations.generateHeats(
				operations.returnEvent(eventName), poolType, swimmersPerHeat, heatGender);

		// register the heats in csv files
		operations.registerHeats(poolType, heatList, heatList.get(0)
				.getEventName() + " " + heatGender + ".csv");

		// write the heats in pdf files
		HeatListWriter hWriter = new HeatListWriter(
				operations.returnEvent(eventName), competitionTitle, heatList, heatGender);
		hWriter.run();

		// write the judges list
		for (Heat heat : heatList) {
			JudgesListWriter judgeWriter = new JudgesListWriter(
					operations.returnEvent(eventName), competitionTitle, heat, heatGender);
			judgeWriter.run();
		}
	}

	public int getSwimmersPerHeat() {
		return swimmersPerHeat;
	}

	public void setSwimmersPerHeat(int swimmersPerHeat) {
		this.swimmersPerHeat = swimmersPerHeat;
	}

}
