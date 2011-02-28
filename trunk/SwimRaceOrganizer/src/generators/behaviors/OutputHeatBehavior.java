package generators.behaviors;

import swimraceorganizer.SwimRaceOrganizerApp;
import work.Operations;
import displays.AllHeats;

public class OutputHeatBehavior implements GenerateHeatBehavior {

	private AllHeats allHeats;
	private Operations operations = new Operations();
	
	@Override
	public void generateHeats(String eventName, String poolType,
			String competitionTitle) {
		allHeats = new AllHeats(operations.returnEvent(eventName), poolType,
				competitionTitle);
		SwimRaceOrganizerApp.getApplication().show(allHeats);
		allHeats.setSize(700, 300);
	}
}
