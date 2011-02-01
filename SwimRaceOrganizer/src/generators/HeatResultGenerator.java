package generators;

import generators.behaviors.ResultHeatBehavior;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import utils.EventOperations;

public class HeatResultGenerator extends HeatGenerator {

	private GridBagLayout controlLayout = new GridBagLayout();
	private JButton jBCancel = new JButton("Cancel");
	private JButton jBGenerate = new JButton("Generate");
	private JComboBox jCBHeatName = new JComboBox();
	private EventOperations evOperations = new EventOperations();
	private String poolType;
	private String competitionTitle;

	public HeatResultGenerator(String poolType, String competitionTitle,
			String title) {
		super(poolType, competitionTitle, title);
		generateHeatBehavior = new ResultHeatBehavior();
		setPoolType(poolType);
		setCompetitionTitle(competitionTitle);
		fillEventNames();
		jCBHeatName.setPreferredSize(new Dimension(120, 20));
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
		pane.add(jBGenerate, c);

		c.gridx = 2;
		c.gridy = 1;
		pane.add(jBCancel, c);

		jBCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		jBGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generateHeatBehavior.generateHeats(jCBHeatName
						.getSelectedItem().toString(), getPoolType(),
						getCompetitionTitle());
			}
		});

		pack();
		setVisible(true);

	}

	public void fillEventNames() {
		List<String> eventNames = evOperations.getEventNames();
		jCBHeatName.removeAllItems();
		for (String evName : eventNames) {
			jCBHeatName.addItem(evName);
		}
	}

	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				HeatResultGenerator dialog = new HeatResultGenerator(
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
