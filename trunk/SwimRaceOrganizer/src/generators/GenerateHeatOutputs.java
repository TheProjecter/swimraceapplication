package generators;

import javax.swing.JDialog;
import javax.swing.JFrame;

import displays.AllHeats;

import swimraceorganizer.GenerateHeats;
import swimraceorganizer.SwimRaceOrganizerApp;

public class GenerateHeatOutputs extends GenerateHeats {
	
	private AllHeats allHeats;
	
	public GenerateHeatOutputs(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        fillEventNames();
    }

	protected void generateHeats(java.awt.event.ActionEvent evt) {
        if (allHeats == null) {
            JFrame mainFrame = SwimRaceOrganizerApp.getApplication().getMainFrame();
            allHeats = new AllHeats();
            allHeats.setLocationRelativeTo(mainFrame);
        }
        SwimRaceOrganizerApp.getApplication().show(allHeats);
        dispose();
	}
}
