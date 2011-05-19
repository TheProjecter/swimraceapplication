package generators;

import generators.behaviors.GenerateHeatBehavior;
import generators.behaviors.SwimmerRelated;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JDialog;

import utils.EventOperations;

public abstract class HeatGenerator extends JDialog {

	protected GenerateHeatBehavior generateHeatBehavior;
	protected SwimmerRelated swimmerRelated;
	protected String windowTitle;
	protected String poolType;
	protected String competitionTitle;
	protected JComboBox jCBHeatsGender = new JComboBox();
	protected JComboBox jCBHeatName = new JComboBox();
	private EventOperations evOperations = new EventOperations();


	public HeatGenerator(String poolType, String competitionTitle, String title) {
		super();
		setWindowTitle(title);
		setPoolType(poolType);
		setCompetitionTitle(competitionTitle);
		// setAlwaysOnTop(true);
		setResizable(false);
		setTitle(title);
		fillEventNames();
		fillGenderCB();
		jCBHeatName.setPreferredSize(new Dimension(190, 20));
		jCBHeatsGender.setPreferredSize(new Dimension(120, 20));
	}

	public void generateHeat(String eventName, String poolType,
			String competitionTitle, String heatGender) {
		generateHeatBehavior.generateHeats(eventName, poolType,
				competitionTitle, heatGender);
	}

	public void fillEventNames() {
		List<String> eventNames = evOperations.getEventNames();
		jCBHeatName.removeAllItems();
		for (String evName : eventNames) {
			jCBHeatName.addItem(evName);
		}
	}

	public void fillGenderCB() {
		jCBHeatsGender.removeAllItems();
		jCBHeatsGender.addItem("Mixt".toString());
		jCBHeatsGender.addItem("F".toString());
		jCBHeatsGender.addItem("M".toString());
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
