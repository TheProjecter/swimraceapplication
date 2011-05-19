package generators;

import generators.behaviors.CreateHeatsBehavior;
import generators.behaviors.SwimmerRelated;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import utils.EventOperations;
import utils.SwimmersPerHeatSingleton;

public class HeatCreatorGenerator extends HeatGenerator {

	private GridBagLayout controlLayout = new GridBagLayout();
	private JButton jBCancel = new JButton("Cancel");
	private JButton jBGenerate = new JButton("Generate");
	private JTextField jTSwimmersPerHeat = new JTextField(5);
	private String poolType;
	private String competitionTitle;
	private SwimmersPerHeatSingleton swimmerPerHeat = SwimmersPerHeatSingleton
			.getInstance();

	public HeatCreatorGenerator(String poolType, String competitionTitle,
			String title) {
		super(poolType, competitionTitle, title);
		generateHeatBehavior = new CreateHeatsBehavior();
		swimmerRelated = (SwimmerRelated) generateHeatBehavior;
		setPoolType(poolType);
		setCompetitionTitle(competitionTitle);
		jBGenerate.setPreferredSize(new Dimension(100, 26));
		jBCancel.setPreferredSize(new Dimension(100, 26));
		addComponentsToPane(getContentPane());
	}

	public void addComponentsToPane(final Container pane) {
		pane.setLayout(controlLayout);
		GridBagConstraints c = new GridBagConstraints();

		c.weightx = 0.0;
		c.gridwidth = 2;

		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(10, 10, 10, 10);
		pane.add(new JLabel("Numele probei"), c);

		c.gridx = 2;
		c.gridy = 0;
		pane.add(jCBHeatName, c);

		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(10, 10, 10, 10);
		pane.add(new JLabel("Tipul probei"), c);

		c.gridx = 2;
		c.gridy = 1;
		pane.add(jCBHeatsGender, c);

		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(10, 10, 10, 10);
		pane.add(new JLabel("Concurenti pe serie"), c);

		c.gridx = 2;
		c.gridy = 2;
		pane.add(jTSwimmersPerHeat, c);

		c.gridx = 0;
		c.gridy = 3;
		pane.add(jBGenerate, c);

		c.gridx = 2;
		c.gridy = 3;
		pane.add(jBCancel, c);

		jBCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		jBGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (jTSwimmersPerHeat.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"Specificati numarul de concurenti pe serie!",
							"Warrning...", 1);
				} else {
					// adding the number of swimmers / heat / event. It needs to
					// be used later
					swimmerPerHeat.push(jCBHeatName.getSelectedItem()
							.toString(), Integer.parseInt(jTSwimmersPerHeat
							.getText()));
					swimmerRelated.setSwimmersPerHeat(Integer
							.parseInt(jTSwimmersPerHeat.getText()));
					generateHeatBehavior.generateHeats(jCBHeatName
							.getSelectedItem().toString(), getPoolType(),
							getCompetitionTitle(), jCBHeatsGender
									.getSelectedItem().toString());
				}
			}
		});

		pack();
		setVisible(true);

	}

	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				HeatCreatorGenerator dialog = new HeatCreatorGenerator(
						new String(), new String(), new String());
				dialog.addWindowListener(new java.awt.event.WindowAdapter() {
					public void windowClosing(java.awt.event.WindowEvent e) {
						System.exit(0);
					}
				});
				dialog.setVisible(true);
			}
		});
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
