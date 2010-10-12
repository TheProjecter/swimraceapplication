package generators;

import displays.AllHeats;

import swimraceorganizer.SwimRaceOrganizerApp;

public class GenerateHeatOutputs extends GenerateHeats {
	
	private static final long serialVersionUID = 1L;
	private AllHeats allHeats;
	
	public GenerateHeatOutputs() {
        super();
        fillEventNames();
    }

	protected void generateHeats(java.awt.event.ActionEvent evt) {
        allHeats = new AllHeats(this.getSelectedEvent());
        SwimRaceOrganizerApp.getApplication().show(allHeats);
        dispose();
	}
}
