package generators;

import displays.AllHeats;

import swimraceorganizer.SwimRaceOrganizerApp;

public class GenerateHeatOutputs extends GenerateHeats {
	
	private static final long serialVersionUID = 1L;
	private AllHeats allHeats;
	private String poolType;
	private String competitionTitle;
	
	public GenerateHeatOutputs(String poolType, String competitionTitle, String title) {
        super(poolType, competitionTitle, title);
        setPoolType(poolType);
        setCompetitionTitle(competitionTitle);
        fillEventNames();
        System.out.println("output...");
	}

	protected void generateHeats(java.awt.event.ActionEvent evt) {
        allHeats = new AllHeats(this.getSelectedEvent(), getPoolType(), getCompetitionTitle());
        SwimRaceOrganizerApp.getApplication().show(allHeats);
        allHeats.setSize(700, 300);
        dispose();
	}

	public String getPoolType() {
		return poolType;
	}

	public void setPoolType(String poolType) {
		this.poolType = poolType;
	}

	public String getCompetitionTitle() {
		return competitionTitle;
	}

	public void setCompetitionTitle(String competitionTitle) {
		this.competitionTitle = competitionTitle;
	}
}
