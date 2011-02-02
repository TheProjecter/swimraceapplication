package generators;

import generators.behaviors.GenerateHeatBehavior;
import generators.behaviors.SwimmerRelated;

import javax.swing.JDialog;

public abstract class HeatGenerator extends JDialog {

	protected GenerateHeatBehavior generateHeatBehavior;
	protected SwimmerRelated swimmerRelated;
	protected String windowTitle;
	protected String poolType;
	protected String competitionTitle;

	public HeatGenerator(String poolType, String competitionTitle, String title) {
		super();
		setWindowTitle(title);
		setPoolType(poolType);
		setCompetitionTitle(competitionTitle);
		setAlwaysOnTop(true);
		setResizable(false);
		setTitle(title);
	}

	public void generateHeat(String eventName, String poolType,
			String competitionTitle) {
		generateHeatBehavior.generateHeats(eventName, poolType,
				competitionTitle);
	}

	public String getWindowTitle() {
		return windowTitle;
	}

	public void setWindowTitle(String title) {
		this.windowTitle = title;
	}

	public GenerateHeatBehavior getGenerateHeatBehavior() {
		return generateHeatBehavior;
	}

	public void setGenerateHeatBehavior(
			GenerateHeatBehavior generateHeatBehavior) {
		this.generateHeatBehavior = generateHeatBehavior;
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
