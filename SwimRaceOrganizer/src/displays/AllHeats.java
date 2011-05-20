package displays;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import utils.Constants;
import utils.SwimmersPerHeatSingleton;
import work.Operations;
import customComponents.HeaderText;
import customComponents.TimesComboBox;
import entities.Event;
import entities.Heat;
import entities.Lane;
import entities.Result;
import entities.Swimmer;

public class AllHeats extends JDialog {

	private Operations operations = new Operations();
	private GridLayout controlLayout = new GridLayout(1, 6);
	private List<Lane> resultLanes = new ArrayList<Lane>();
	private Event event;
	private String poolType;
	private String competitionTitle;
	private String heatGender;

	private JButton jBCancel = new JButton("Cancel");
	private JButton jBGenerateRezults = new JButton("Generate Results");
	private Map<String, String> pathFile = new Constants().getDataFiles();
	private SwimmersPerHeatSingleton swimmerPerHeat = SwimmersPerHeatSingleton
			.getInstance();
	private List<Result> results = new ArrayList<Result>();

	private static final long serialVersionUID = 1L;

	public AllHeats(Event event, String poolType, String competitionTitle,
			String heatGender) {
		super();
		setEvent(event);
		setResults(operations.returnResults(event, heatGender));
		setPoolType(poolType);
		setCompetitionTitle(competitionTitle);
		setHeatGender(heatGender);
		setTitle("All heats for " + event.getName());
		addComponentsToPane(getContentPane());
		setAlwaysOnTop(false);
		setResizable(false);
		setLocation(100, 100);
	}

	/**
	 * Adding the required components/panels to the dialog the dialog
	 * 
	 * @param pane
	 */
	public void addComponentsToPane(final Container pane) {
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		// Adding to the control panel (buttons)
		final JPanel controlsPanel = new JPanel();
		controlsPanel.setLayout(controlLayout);

		controlsPanel.add(jBGenerateRezults);
		controlsPanel.add(new Label(" "));
		controlsPanel.add(new Label(" "));
		controlsPanel.add(new Label(" "));
		controlsPanel.add(new Label(" "));
		controlsPanel.add(jBCancel);

		// Adding to the heats panel
		List<Heat> heatList;
		try {
			heatList = operations.generateHeats(event, poolType,
					swimmerPerHeat.getValue(event.getName()), heatGender);
		} catch (NullPointerException e) {
			heatList = operations.generateHeats(event, poolType, 6, heatGender);
		}

		final JPanel heatsPanel = new JPanel();
		/**
		 * The lines are 8 or 10 based on the pooltype
		 */
		heatsPanel.setLayout(new GridLayout((heatList.size() * (poolType
				.contains("50") ? 10 : 8)), 10));

		for (Heat heats : heatList) {
			// header
			heatsPanel.add(new HeaderText("Heat " + heats.getHeatNumber(),
					JLabel.CENTER));
			heatsPanel.add(new HeaderText("Name", JLabel.CENTER));
			heatsPanel.add(new HeaderText("Age Gr.", JLabel.CENTER));
			heatsPanel.add(new HeaderText("Entry", JLabel.CENTER));
			heatsPanel.add(new HeaderText("Minutes", JLabel.CENTER));
			heatsPanel.add(new HeaderText("Seconds", JLabel.CENTER));
			heatsPanel.add(new HeaderText("M. Sec.", JLabel.CENTER));
			heatsPanel.add(new HeaderText("Save", JLabel.CENTER));
			heatsPanel.add(new HeaderText("DSQ", JLabel.CENTER));
			heatsPanel.add(new HeaderText("DNS", JLabel.CENTER));
			heatsPanel.add(new HeaderText("Status", JLabel.CENTER));

			// Lane 1
			final TimesComboBox tMCBL1 = new TimesComboBox("minutes");
			final TimesComboBox tSCBL1 = new TimesComboBox("seconds");
			final TimesComboBox tMSCBL1 = new TimesComboBox("mseconds");
			final JButton saveL1 = new JButton("Save");
			final JButton dsqL1 = new JButton("DSQ");
			final JButton dnsL1 = new JButton("DNS");
			heatsPanel.add(new JLabel("Lane "
					+ heats.getLane1().getLaneNumber()));
			heatsPanel.add(new JLabel(heats.getLane1().getSwimmer().getName()));
			heatsPanel.add(new JLabel(heats.getLane1().getSwimmer()
					.getAgeGroup()));
			heatsPanel.add(new JLabel(heats.getLane1().getEntryMinutes() + ":"
					+ heats.getLane1().getEntrySecondes() + ":"
					+ heats.getLane1().getEntryMSeconds()));
			heatsPanel.add(tMCBL1);
			heatsPanel.add(tSCBL1);
			heatsPanel.add(tMSCBL1);
			heatsPanel.add(saveL1);
			heatsPanel.add(dsqL1);
			heatsPanel.add(dnsL1);
			final JLabel statusLableL1 = new JLabel("waiting", JLabel.CENTER);
			heatsPanel.add(statusLableL1);
			final Swimmer swimmerL1 = heats.getLane1().getSwimmer();
			final Event eventL1 = heats.getLane1().getEvent();
			final Lane lane1 = new Lane(swimmerL1, eventL1, 0, new Integer(0),
					new Integer(0), new Integer(0));
			// populate the times with the previously entered times, if there
			// are any
			final Result perviousResultL1 = operations.getResultForSwimmer(
					results, heats.getLane1().getSwimmer());
			if (!perviousResultL1.getSwimmer().getName().equals("")) {
				tMCBL1.setSelectedItem(padLeft(perviousResultL1
						.getResultMinutes().toString(), 2));
				tSCBL1.setSelectedItem(padLeft(perviousResultL1
						.getResultSecondes().toString(), 2));
				tMSCBL1.setSelectedItem(padLeft(perviousResultL1
						.getResultMSeconds().toString(), 2));
				tMCBL1.setEnabled(false);
				tSCBL1.setEnabled(false);
				tMSCBL1.setEnabled(false);
				lane1.setResultMinutes(Integer.parseInt(perviousResultL1
						.getResultMinutes().toString()));
				lane1.setResultSecondes(Integer.parseInt(perviousResultL1
						.getResultSecondes().toString()));
				lane1.setResultMSeconds(Integer.parseInt(perviousResultL1
						.getResultMSeconds().toString()));
				if (perviousResultL1.getPerformanceStatus().equals("OK")) {
					tMCBL1.setEnabled(false);
					tSCBL1.setEnabled(false);
					tMSCBL1.setEnabled(false);
					dsqL1.setEnabled(false);
					dnsL1.setEnabled(false);
					saveL1.setText("Edit");
					statusLableL1.setText("OK");
					statusLableL1.setForeground(new Color(0, 153, 0));
					lane1.setPerformanceStatus("OK");
					lane1.setResultTime();
				} else if (perviousResultL1.getPerformanceStatus()
						.equals("DSQ")) {
					tMCBL1.setEnabled(false);
					tSCBL1.setEnabled(false);
					tMSCBL1.setEnabled(false);
					saveL1.setEnabled(false);
					dnsL1.setEnabled(false);
					dsqL1.setText("dsq");
					statusLableL1.setText("DSQ");
					statusLableL1.setForeground(Color.RED);
					lane1.setPerformanceStatus("DSQ");
					lane1.setResultTime(99999998);
				} else if (perviousResultL1.getPerformanceStatus()
						.equals("DNS")) {
					tMCBL1.setEnabled(false);
					tSCBL1.setEnabled(false);
					tMSCBL1.setEnabled(false);
					saveL1.setEnabled(false);
					dsqL1.setEnabled(false);
					dnsL1.setText("dns");
					statusLableL1.setText("DNS");
					statusLableL1.setForeground(Color.BLUE);
					lane1.setPerformanceStatus("DNS");
					lane1.setResultTime(99999999);
				}
				resultLanes.add(lane1);
			}
			saveL1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (tMCBL1.isEnabled()) {
						lane1.setResultMinutes(Integer.parseInt(tMCBL1
								.getSelectedItem().toString()));
						lane1.setResultSecondes(Integer.parseInt(tSCBL1
								.getSelectedItem().toString()));
						lane1.setResultMSeconds(Integer.parseInt(tMSCBL1
								.getSelectedItem().toString()));
						lane1.setResultTime();
						lane1.setPerformanceStatus("OK");
						resultLanes.add(lane1);
						tMCBL1.setEnabled(false);
						tSCBL1.setEnabled(false);
						tMSCBL1.setEnabled(false);
						dsqL1.setEnabled(false);
						dnsL1.setEnabled(false);
						saveL1.setText("Edit");
						statusLableL1.setText("OK");
						statusLableL1.setForeground(new Color(0, 153, 0));
					} else {
						tMCBL1.setEnabled(true);
						tSCBL1.setEnabled(true);
						tMSCBL1.setEnabled(true);
						dsqL1.setEnabled(true);
						dnsL1.setEnabled(true);
						saveL1.setText("Save");
						statusLableL1.setText("waiting");
						statusLableL1.setForeground(Color.BLACK);
					}
				}
			});
			dsqL1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (tMCBL1.isEnabled()) {
						tMCBL1.setSelectedItem("00");
						tSCBL1.setSelectedItem("00");
						tMSCBL1.setSelectedItem("00");
						lane1.setResultMinutes(Integer.parseInt(tMCBL1
								.getSelectedItem().toString()));
						lane1.setResultSecondes(Integer.parseInt(tSCBL1
								.getSelectedItem().toString()));
						lane1.setResultMSeconds(Integer.parseInt(tMSCBL1
								.getSelectedItem().toString()));
						lane1.setResultTime(99999998);
						lane1.setPerformanceStatus("DSQ");
						resultLanes.add(lane1);
						tMCBL1.setEnabled(false);
						tSCBL1.setEnabled(false);
						tMSCBL1.setEnabled(false);
						saveL1.setEnabled(false);
						dnsL1.setEnabled(false);
						dsqL1.setText("dsq");
						statusLableL1.setText("DSQ");
						statusLableL1.setForeground(Color.RED);
					} else {
						tMCBL1.setEnabled(true);
						tSCBL1.setEnabled(true);
						tMSCBL1.setEnabled(true);
						saveL1.setEnabled(true);
						dnsL1.setEnabled(true);
						dsqL1.setText("DSQ");
						statusLableL1.setText("waiting");
						statusLableL1.setForeground(Color.BLACK);
					}
				}
			});
			dnsL1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (tMCBL1.isEnabled()) {
						tMCBL1.setSelectedItem("00");
						tSCBL1.setSelectedItem("00");
						tMSCBL1.setSelectedItem("00");
						lane1.setResultMinutes(Integer.parseInt(tMCBL1
								.getSelectedItem().toString()));
						lane1.setResultSecondes(Integer.parseInt(tSCBL1
								.getSelectedItem().toString()));
						lane1.setResultMSeconds(Integer.parseInt(tMSCBL1
								.getSelectedItem().toString()));
						lane1.setResultTime(99999999);
						lane1.setPerformanceStatus("DNS");
						resultLanes.add(lane1);
						tMCBL1.setEnabled(false);
						tSCBL1.setEnabled(false);
						tMSCBL1.setEnabled(false);
						saveL1.setEnabled(false);
						dsqL1.setEnabled(false);
						dnsL1.setText("dns");
						statusLableL1.setText("DNS");
						statusLableL1.setForeground(Color.BLUE);
					} else {
						tMCBL1.setEnabled(true);
						tSCBL1.setEnabled(true);
						tMSCBL1.setEnabled(true);
						saveL1.setEnabled(true);
						dsqL1.setEnabled(true);
						dnsL1.setText("DNS");
						statusLableL1.setText("waiting");
						statusLableL1.setForeground(Color.BLACK);
					}
				}
			});

			// Lane 2
			final TimesComboBox tMCBL2 = new TimesComboBox("minutes");
			final TimesComboBox tSCBL2 = new TimesComboBox("seconds");
			final TimesComboBox tMSCBL2 = new TimesComboBox("mseconds");
			final JButton saveL2 = new JButton("Save");
			final JButton dsqL2 = new JButton("DSQ");
			final JButton dnsL2 = new JButton("DNS");
			heatsPanel.add(new JLabel("Lane "
					+ heats.getLane2().getLaneNumber()));
			heatsPanel.add(new JLabel(heats.getLane2().getSwimmer().getName()));
			heatsPanel.add(new JLabel(heats.getLane2().getSwimmer()
					.getAgeGroup()));
			heatsPanel.add(new JLabel(heats.getLane2().getEntryMinutes() + ":"
					+ heats.getLane2().getEntrySecondes() + ":"
					+ heats.getLane2().getEntryMSeconds()));
			heatsPanel.add(tMCBL2);
			heatsPanel.add(tSCBL2);
			heatsPanel.add(tMSCBL2);
			heatsPanel.add(saveL2);
			heatsPanel.add(dsqL2);
			heatsPanel.add(dnsL2);
			final JLabel statusLableL2 = new JLabel("waiting", JLabel.CENTER);
			heatsPanel.add(statusLableL2);
			final Swimmer swimmerL2 = heats.getLane2().getSwimmer();
			final Event eventL2 = heats.getLane2().getEvent();
			final Lane lane2 = new Lane(swimmerL2, eventL2, 0, new Integer(0),
					new Integer(0), new Integer(0));
			// populate the times with the previously entered times, if there
			// are any
			final Result perviousResultL2 = operations.getResultForSwimmer(
					results, heats.getLane2().getSwimmer());
			if (!perviousResultL2.getSwimmer().getName().equals("")) {
				tMCBL2.setSelectedItem(padLeft(perviousResultL2
						.getResultMinutes().toString(), 2));
				tSCBL2.setSelectedItem(padLeft(perviousResultL2
						.getResultSecondes().toString(), 2));
				tMSCBL2.setSelectedItem(padLeft(perviousResultL2
						.getResultMSeconds().toString(), 2));
				tMCBL2.setEnabled(false);
				tSCBL2.setEnabled(false);
				tMSCBL2.setEnabled(false);
				lane2.setResultMinutes(Integer.parseInt(perviousResultL2
						.getResultMinutes().toString()));
				lane2.setResultSecondes(Integer.parseInt(perviousResultL2
						.getResultSecondes().toString()));
				lane2.setResultMSeconds(Integer.parseInt(perviousResultL2
						.getResultMSeconds().toString()));
				if (perviousResultL2.getPerformanceStatus().equals("OK")) {
					tMCBL2.setEnabled(false);
					tSCBL2.setEnabled(false);
					tMSCBL2.setEnabled(false);
					dsqL2.setEnabled(false);
					dnsL2.setEnabled(false);
					saveL2.setText("Edit");
					statusLableL2.setText("OK");
					statusLableL2.setForeground(new Color(0, 153, 0));
					lane2.setPerformanceStatus("OK");
					lane2.setResultTime();
				} else if (perviousResultL2.getPerformanceStatus()
						.equals("DSQ")) {
					tMCBL1.setEnabled(false);
					tSCBL1.setEnabled(false);
					tMSCBL2.setEnabled(false);
					saveL2.setEnabled(false);
					dnsL2.setEnabled(false);
					dsqL2.setText("dsq");
					statusLableL2.setText("DSQ");
					statusLableL2.setForeground(Color.RED);
					lane2.setPerformanceStatus("DSQ");
					lane2.setResultTime(99999998);
				} else if (perviousResultL2.getPerformanceStatus()
						.equals("DNS")) {
					tMCBL1.setEnabled(false);
					tSCBL1.setEnabled(false);
					tMSCBL2.setEnabled(false);
					saveL2.setEnabled(false);
					dsqL2.setEnabled(false);
					dnsL2.setText("dns");
					statusLableL2.setText("DNS");
					statusLableL2.setForeground(Color.BLUE);
					lane2.setPerformanceStatus("DNS");
					lane2.setResultTime(99999999);
				}
				resultLanes.add(lane2);
			}
			saveL2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (tMCBL2.isEnabled()) {
						lane2.setResultMinutes(Integer.parseInt(tMCBL2
								.getSelectedItem().toString()));
						lane2.setResultSecondes(Integer.parseInt(tSCBL2
								.getSelectedItem().toString()));
						lane2.setResultMSeconds(Integer.parseInt(tMSCBL2
								.getSelectedItem().toString()));
						lane2.setResultTime();
						lane2.setPerformanceStatus("OK");
						resultLanes.add(lane2);
						tMCBL2.setEnabled(false);
						tSCBL2.setEnabled(false);
						tMSCBL2.setEnabled(false);
						dsqL2.setEnabled(false);
						dnsL2.setEnabled(false);
						saveL2.setText("Edit");
						statusLableL2.setText("OK");
						statusLableL2.setForeground(new Color(0, 153, 0));
					} else {
						tMCBL2.setEnabled(true);
						tSCBL2.setEnabled(true);
						tMSCBL2.setEnabled(true);
						dsqL2.setEnabled(true);
						dnsL2.setEnabled(true);
						saveL2.setText("Save");
						statusLableL2.setText("waiting");
						statusLableL2.setForeground(Color.BLACK);
					}
				}
			});
			dsqL2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (tMCBL2.isEnabled()) {
						tMCBL2.setSelectedItem("00");
						tSCBL2.setSelectedItem("00");
						tMSCBL2.setSelectedItem("00");
						lane2.setResultMinutes(Integer.parseInt(tMCBL2
								.getSelectedItem().toString()));
						lane2.setResultSecondes(Integer.parseInt(tSCBL2
								.getSelectedItem().toString()));
						lane2.setResultMSeconds(Integer.parseInt(tMSCBL2
								.getSelectedItem().toString()));
						lane2.setResultTime(99999998);
						lane2.setPerformanceStatus("DSQ");
						resultLanes.add(lane2);
						tMCBL2.setEnabled(false);
						tSCBL2.setEnabled(false);
						tMSCBL2.setEnabled(false);
						saveL2.setEnabled(false);
						dnsL2.setEnabled(false);
						dsqL2.setText("dsq");
						statusLableL2.setText("DSQ");
						statusLableL2.setForeground(Color.RED);
					} else {
						tMCBL2.setEnabled(true);
						tSCBL2.setEnabled(true);
						tMSCBL2.setEnabled(true);
						saveL2.setEnabled(true);
						dnsL2.setEnabled(true);
						dsqL2.setText("DSQ");
						statusLableL2.setText("waiting");
						statusLableL2.setForeground(Color.BLACK);
					}
				}
			});
			dnsL2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (tMCBL2.isEnabled()) {
						tMCBL2.setSelectedItem("00");
						tSCBL2.setSelectedItem("00");
						tMSCBL2.setSelectedItem("00");
						lane2.setResultMinutes(Integer.parseInt(tMCBL2
								.getSelectedItem().toString()));
						lane2.setResultSecondes(Integer.parseInt(tSCBL2
								.getSelectedItem().toString()));
						lane2.setResultMSeconds(Integer.parseInt(tMSCBL2
								.getSelectedItem().toString()));
						lane2.setResultTime(99999999);
						lane2.setPerformanceStatus("DNS");
						resultLanes.add(lane2);
						tMCBL2.setEnabled(false);
						tSCBL2.setEnabled(false);
						tMSCBL2.setEnabled(false);
						saveL2.setEnabled(false);
						dsqL2.setEnabled(false);
						dnsL2.setText("dns");
						statusLableL2.setText("DNS");
						statusLableL2.setForeground(Color.BLUE);
					} else {
						tMCBL2.setEnabled(true);
						tSCBL2.setEnabled(true);
						tMSCBL2.setEnabled(true);
						saveL2.setEnabled(true);
						dsqL2.setEnabled(true);
						dnsL2.setText("DNS");
						statusLableL2.setText("waiting");
						statusLableL2.setForeground(Color.BLACK);
					}
				}
			});

			// Lane 3
			final TimesComboBox tMCBL3 = new TimesComboBox("minutes");
			final TimesComboBox tSCBL3 = new TimesComboBox("seconds");
			final TimesComboBox tMSCBL3 = new TimesComboBox("mseconds");
			final JButton saveL3 = new JButton("Save");
			final JButton dsqL3 = new JButton("DSQ");
			final JButton dnsL3 = new JButton("DNS");
			heatsPanel.add(new JLabel("Lane "
					+ heats.getLane3().getLaneNumber()));
			heatsPanel.add(new JLabel(heats.getLane3().getSwimmer().getName()));
			heatsPanel.add(new JLabel(heats.getLane3().getSwimmer()
					.getAgeGroup()));
			heatsPanel.add(new JLabel(heats.getLane3().getEntryMinutes() + ":"
					+ heats.getLane3().getEntrySecondes() + ":"
					+ heats.getLane3().getEntryMSeconds()));
			heatsPanel.add(tMCBL3);
			heatsPanel.add(tSCBL3);
			heatsPanel.add(tMSCBL3);
			heatsPanel.add(tSCBL3);
			heatsPanel.add(tMSCBL3);
			heatsPanel.add(saveL3);
			heatsPanel.add(dsqL3);
			heatsPanel.add(dnsL3);
			final JLabel statusLableL3 = new JLabel("waiting", JLabel.CENTER);
			heatsPanel.add(statusLableL3);
			final Swimmer swimmerL3 = heats.getLane3().getSwimmer();
			final Event eventL3 = heats.getLane3().getEvent();
			final Lane lane3 = new Lane(swimmerL3, eventL3, 0, new Integer(0),
					new Integer(0), new Integer(0));
			// populate the times with the previously entered times, if there
			// are any
			final Result perviousResultL3 = operations.getResultForSwimmer(
					results, heats.getLane3().getSwimmer());
			if (!perviousResultL3.getSwimmer().getName().equals("")) {
				tMCBL3.setSelectedItem(padLeft(perviousResultL3
						.getResultMinutes().toString(), 2));
				tSCBL3.setSelectedItem(padLeft(perviousResultL3
						.getResultSecondes().toString(), 2));
				tMSCBL3.setSelectedItem(padLeft(perviousResultL3
						.getResultMSeconds().toString(), 2));
				tMCBL3.setEnabled(false);
				tSCBL3.setEnabled(false);
				tMSCBL3.setEnabled(false);
				lane3.setResultMinutes(Integer.parseInt(perviousResultL3
						.getResultMinutes().toString()));
				lane3.setResultSecondes(Integer.parseInt(perviousResultL3
						.getResultSecondes().toString()));
				lane3.setResultMSeconds(Integer.parseInt(perviousResultL3
						.getResultMSeconds().toString()));
				if (perviousResultL3.getPerformanceStatus().equals("OK")) {
					tMCBL3.setEnabled(false);
					tSCBL3.setEnabled(false);
					tMSCBL3.setEnabled(false);
					dsqL3.setEnabled(false);
					dnsL3.setEnabled(false);
					saveL3.setText("Edit");
					statusLableL3.setText("OK");
					statusLableL3.setForeground(new Color(0, 153, 0));
					lane3.setPerformanceStatus("OK");
					lane3.setResultTime();
				} else if (perviousResultL3.getPerformanceStatus()
						.equals("DSQ")) {
					tMCBL3.setEnabled(false);
					tSCBL3.setEnabled(false);
					tMSCBL3.setEnabled(false);
					saveL3.setEnabled(false);
					dnsL3.setEnabled(false);
					dsqL3.setText("dsq");
					statusLableL3.setText("DSQ");
					statusLableL3.setForeground(Color.RED);
					lane3.setPerformanceStatus("DSQ");
					lane3.setResultTime(99999998);
				} else if (perviousResultL3.getPerformanceStatus()
						.equals("DNS")) {
					tMCBL3.setEnabled(false);
					tSCBL3.setEnabled(false);
					tMSCBL3.setEnabled(false);
					saveL3.setEnabled(false);
					dsqL3.setEnabled(false);
					dnsL3.setText("dns");
					statusLableL3.setText("DNS");
					statusLableL3.setForeground(Color.BLUE);
					lane3.setPerformanceStatus("DNS");
					lane3.setResultTime(99999999);
				}
				resultLanes.add(lane3);
			}
			saveL3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (tMCBL3.isEnabled()) {
						lane3.setResultMinutes(Integer.parseInt(tMCBL3
								.getSelectedItem().toString()));
						lane3.setResultSecondes(Integer.parseInt(tSCBL3
								.getSelectedItem().toString()));
						lane3.setResultMSeconds(Integer.parseInt(tMSCBL3
								.getSelectedItem().toString()));
						lane3.setResultTime();
						lane3.setPerformanceStatus("OK");
						resultLanes.add(lane3);
						tMCBL3.setEnabled(false);
						tSCBL3.setEnabled(false);
						tMSCBL3.setEnabled(false);
						dsqL3.setEnabled(false);
						dnsL3.setEnabled(false);
						saveL3.setText("Edit");
						statusLableL3.setText("OK");
						statusLableL3.setForeground(new Color(0, 153, 0));
					} else {
						tMCBL3.setEnabled(true);
						tSCBL3.setEnabled(true);
						tMSCBL3.setEnabled(true);
						dsqL3.setEnabled(true);
						dnsL3.setEnabled(true);
						saveL3.setText("Save");
						statusLableL3.setText("waiting");
						statusLableL3.setForeground(Color.BLACK);
					}
				}
			});
			dsqL3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (tMCBL3.isEnabled()) {
						tMCBL3.setSelectedItem("00");
						tSCBL3.setSelectedItem("00");
						tMSCBL3.setSelectedItem("00");
						lane3.setResultMinutes(Integer.parseInt(tMCBL3
								.getSelectedItem().toString()));
						lane3.setResultSecondes(Integer.parseInt(tSCBL3
								.getSelectedItem().toString()));
						lane3.setResultMSeconds(Integer.parseInt(tMSCBL3
								.getSelectedItem().toString()));
						lane3.setResultTime(99999998);
						lane3.setPerformanceStatus("DSQ");
						resultLanes.add(lane3);
						tMCBL3.setEnabled(false);
						tSCBL3.setEnabled(false);
						tMSCBL3.setEnabled(false);
						saveL3.setEnabled(false);
						dnsL3.setEnabled(false);
						dsqL3.setText("dsq");
						statusLableL3.setText("DSQ");
						statusLableL3.setForeground(Color.RED);
					} else {
						tMCBL3.setEnabled(true);
						tSCBL3.setEnabled(true);
						tMSCBL3.setEnabled(true);
						saveL3.setEnabled(true);
						dnsL3.setEnabled(true);
						dsqL3.setText("DSQ");
						statusLableL3.setText("waiting");
						statusLableL3.setForeground(Color.BLACK);
					}
				}
			});
			dnsL3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (tMCBL3.isEnabled()) {
						tMCBL3.setSelectedItem("00");
						tSCBL3.setSelectedItem("00");
						tMSCBL3.setSelectedItem("00");
						lane3.setResultMinutes(Integer.parseInt(tMCBL3
								.getSelectedItem().toString()));
						lane3.setResultSecondes(Integer.parseInt(tSCBL3
								.getSelectedItem().toString()));
						lane3.setResultMSeconds(Integer.parseInt(tMSCBL3
								.getSelectedItem().toString()));
						lane3.setResultTime(99999999);
						lane3.setPerformanceStatus("DNS");
						resultLanes.add(lane3);
						tMCBL3.setEnabled(false);
						tSCBL3.setEnabled(false);
						tMSCBL3.setEnabled(false);
						saveL3.setEnabled(false);
						dsqL3.setEnabled(false);
						dnsL3.setText("dns");
						statusLableL3.setText("DNS");
						statusLableL3.setForeground(Color.BLUE);
					} else {
						tMCBL3.setEnabled(true);
						tSCBL3.setEnabled(true);
						tMSCBL3.setEnabled(true);
						saveL3.setEnabled(true);
						dsqL3.setEnabled(true);
						dnsL3.setText("DNS");
						statusLableL3.setText("waiting");
						statusLableL3.setForeground(Color.BLACK);
					}
				}
			});

			// Lane 4
			final TimesComboBox tMCBL4 = new TimesComboBox("minutes");
			final TimesComboBox tSCBL4 = new TimesComboBox("seconds");
			final TimesComboBox tMSCBL4 = new TimesComboBox("mseconds");
			final JButton saveL4 = new JButton("Save");
			final JButton dsqL4 = new JButton("DSQ");
			final JButton dnsL4 = new JButton("DNS");
			heatsPanel.add(new JLabel("Lane "
					+ heats.getLane4().getLaneNumber()));
			heatsPanel.add(new JLabel(heats.getLane4().getSwimmer().getName()));
			heatsPanel.add(new JLabel(heats.getLane4().getSwimmer()
					.getAgeGroup()));
			heatsPanel.add(new JLabel(heats.getLane4().getEntryMinutes() + ":"
					+ heats.getLane4().getEntrySecondes() + ":"
					+ heats.getLane4().getEntryMSeconds()));
			heatsPanel.add(tMCBL4);
			heatsPanel.add(tSCBL4);
			heatsPanel.add(tMSCBL4);
			heatsPanel.add(saveL4);
			heatsPanel.add(dsqL4);
			heatsPanel.add(dnsL4);
			final JLabel statusLableL4 = new JLabel("waiting", JLabel.CENTER);
			heatsPanel.add(statusLableL4);
			final Swimmer swimmerL4 = heats.getLane4().getSwimmer();
			final Event eventL4 = heats.getLane4().getEvent();
			final Lane lane4 = new Lane(swimmerL4, eventL4, 0, new Integer(0),
					new Integer(0), new Integer(0));
			// populate the times with the previously entered times, if there
			// are any
			final Result perviousResultL4 = operations.getResultForSwimmer(
					results, heats.getLane4().getSwimmer());
			if (!perviousResultL4.getSwimmer().getName().equals("")) {
				tMCBL4.setSelectedItem(padLeft(perviousResultL4
						.getResultMinutes().toString(), 2));
				tSCBL4.setSelectedItem(padLeft(perviousResultL4
						.getResultSecondes().toString(), 2));
				tMSCBL4.setSelectedItem(padLeft(perviousResultL4
						.getResultMSeconds().toString(), 2));
				tMCBL4.setEnabled(false);
				tSCBL4.setEnabled(false);
				tMSCBL4.setEnabled(false);
				lane4.setResultMinutes(Integer.parseInt(perviousResultL4
						.getResultMinutes().toString()));
				lane4.setResultSecondes(Integer.parseInt(perviousResultL4
						.getResultSecondes().toString()));
				lane4.setResultMSeconds(Integer.parseInt(perviousResultL4
						.getResultMSeconds().toString()));
				if (perviousResultL4.getPerformanceStatus().equals("OK")) {
					tMCBL4.setEnabled(false);
					tSCBL4.setEnabled(false);
					tMSCBL4.setEnabled(false);
					dsqL4.setEnabled(false);
					dnsL4.setEnabled(false);
					saveL4.setText("Edit");
					statusLableL4.setText("OK");
					statusLableL4.setForeground(new Color(0, 153, 0));
					lane4.setPerformanceStatus("OK");
					lane4.setResultTime();
				} else if (perviousResultL4.getPerformanceStatus()
						.equals("DSQ")) {
					tMCBL4.setEnabled(false);
					tSCBL4.setEnabled(false);
					tMSCBL4.setEnabled(false);
					saveL4.setEnabled(false);
					dnsL4.setEnabled(false);
					dsqL4.setText("dsq");
					statusLableL4.setText("DSQ");
					statusLableL4.setForeground(Color.RED);
					lane4.setPerformanceStatus("DSQ");
					lane4.setResultTime(99999998);
				} else if (perviousResultL4.getPerformanceStatus()
						.equals("DNS")) {
					tMCBL4.setEnabled(false);
					tSCBL4.setEnabled(false);
					tMSCBL4.setEnabled(false);
					saveL4.setEnabled(false);
					dsqL4.setEnabled(false);
					dnsL4.setText("dns");
					statusLableL4.setText("DNS");
					statusLableL4.setForeground(Color.BLUE);
					lane4.setPerformanceStatus("DNS");
					lane4.setResultTime(99999999);
				}
				resultLanes.add(lane4);
			}
			saveL4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (tMCBL4.isEnabled()) {
						Lane lane4 = new Lane(swimmerL4, eventL4, 0,
								new Integer(0), new Integer(0), new Integer(0));
						lane4.setResultMinutes(Integer.parseInt(tMCBL4
								.getSelectedItem().toString()));
						lane4.setResultSecondes(Integer.parseInt(tSCBL4
								.getSelectedItem().toString()));
						lane4.setResultMSeconds(Integer.parseInt(tMSCBL4
								.getSelectedItem().toString()));
						lane4.setResultTime();
						lane4.setPerformanceStatus("OK");
						resultLanes.add(lane4);
						tMCBL4.setEnabled(false);
						tSCBL4.setEnabled(false);
						tMSCBL4.setEnabled(false);
						dsqL4.setEnabled(false);
						dnsL4.setEnabled(false);
						saveL4.setText("Edit");
						statusLableL4.setText("OK");
						statusLableL4.setForeground(new Color(0, 153, 0));
					} else {
						tMCBL4.setEnabled(true);
						tSCBL4.setEnabled(true);
						tMSCBL4.setEnabled(true);
						dsqL4.setEnabled(true);
						dnsL4.setEnabled(true);
						saveL4.setText("Save");
						statusLableL4.setText("waiting");
						statusLableL4.setForeground(Color.BLACK);
					}
				}
			});
			dsqL4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (tMCBL4.isEnabled()) {
						tMCBL4.setSelectedItem("00");
						tSCBL4.setSelectedItem("00");
						tMSCBL4.setSelectedItem("00");
						lane4.setResultMinutes(Integer.parseInt(tMCBL4
								.getSelectedItem().toString()));
						lane4.setResultSecondes(Integer.parseInt(tSCBL4
								.getSelectedItem().toString()));
						lane4.setResultMSeconds(Integer.parseInt(tMSCBL4
								.getSelectedItem().toString()));
						lane4.setResultTime(99999998);
						lane4.setPerformanceStatus("DSQ");
						resultLanes.add(lane4);
						tMCBL4.setEnabled(false);
						tSCBL4.setEnabled(false);
						tMSCBL4.setEnabled(false);
						saveL4.setEnabled(false);
						dnsL4.setEnabled(false);
						dsqL4.setText("dsq");
						statusLableL4.setText("DSQ");
						statusLableL4.setForeground(Color.RED);
					} else {
						tMCBL4.setEnabled(true);
						tSCBL4.setEnabled(true);
						tMSCBL4.setEnabled(true);
						saveL4.setEnabled(true);
						dnsL4.setEnabled(true);
						dsqL4.setText("DSQ");
						statusLableL4.setText("waiting");
						statusLableL4.setForeground(Color.BLACK);
					}
				}
			});
			dnsL4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (tMCBL4.isEnabled()) {
						tMCBL4.setSelectedItem("00");
						tSCBL4.setSelectedItem("00");
						tMSCBL4.setSelectedItem("00");
						lane4.setResultMinutes(Integer.parseInt(tMCBL4
								.getSelectedItem().toString()));
						lane4.setResultSecondes(Integer.parseInt(tSCBL4
								.getSelectedItem().toString()));
						lane4.setResultMSeconds(Integer.parseInt(tMSCBL4
								.getSelectedItem().toString()));
						lane4.setResultTime(99999999);
						lane4.setPerformanceStatus("DNS");
						resultLanes.add(lane4);
						tMCBL4.setEnabled(false);
						tSCBL4.setEnabled(false);
						tMSCBL4.setEnabled(false);
						saveL4.setEnabled(false);
						dsqL4.setEnabled(false);
						dnsL4.setText("dns");
						statusLableL4.setText("DNS");
						statusLableL4.setForeground(Color.BLUE);
					} else {
						tMCBL4.setEnabled(true);
						tSCBL4.setEnabled(true);
						tMSCBL4.setEnabled(true);
						saveL4.setEnabled(true);
						dsqL4.setEnabled(true);
						dnsL4.setText("DNS");
						statusLableL4.setText("waiting");
						statusLableL4.setForeground(Color.BLACK);
					}
				}
			});

			// Lane 5
			final TimesComboBox tMCBL5 = new TimesComboBox("minutes");
			final TimesComboBox tSCBL5 = new TimesComboBox("seconds");
			final TimesComboBox tMSCBL5 = new TimesComboBox("mseconds");
			final JButton saveL5 = new JButton("Save");
			final JButton dsqL5 = new JButton("DSQ");
			final JButton dnsL5 = new JButton("DNS");
			heatsPanel.add(new JLabel("Lane "
					+ heats.getLane5().getLaneNumber()));
			heatsPanel.add(new JLabel(heats.getLane5().getSwimmer().getName()));
			heatsPanel.add(new JLabel(heats.getLane5().getSwimmer()
					.getAgeGroup()));
			heatsPanel.add(new JLabel(heats.getLane5().getEntryMinutes() + ":"
					+ heats.getLane5().getEntrySecondes() + ":"
					+ heats.getLane5().getEntryMSeconds()));
			heatsPanel.add(tMCBL5);
			heatsPanel.add(tSCBL5);
			heatsPanel.add(tMSCBL5);
			heatsPanel.add(saveL5);
			heatsPanel.add(dsqL5);
			heatsPanel.add(dnsL5);
			final JLabel statusLableL5 = new JLabel("waiting", JLabel.CENTER);
			heatsPanel.add(statusLableL5);
			final Swimmer swimmerL5 = heats.getLane5().getSwimmer();
			final Event eventL5 = heats.getLane5().getEvent();
			final Lane lane5 = new Lane(swimmerL5, eventL5, 0, new Integer(0),
					new Integer(0), new Integer(0));
			// populate the times with the previously entered times, if there
			// are any
			final Result perviousResultL5 = operations.getResultForSwimmer(
					results, heats.getLane5().getSwimmer());
			if (!perviousResultL5.getSwimmer().getName().equals("")) {
				tMCBL5.setSelectedItem(padLeft(perviousResultL5
						.getResultMinutes().toString(), 2));
				tSCBL5.setSelectedItem(padLeft(perviousResultL5
						.getResultSecondes().toString(), 2));
				tMSCBL5.setSelectedItem(padLeft(perviousResultL5
						.getResultMSeconds().toString(), 2));
				tMCBL5.setEnabled(false);
				tSCBL5.setEnabled(false);
				tMSCBL5.setEnabled(false);
				lane5.setResultMinutes(Integer.parseInt(perviousResultL5
						.getResultMinutes().toString()));
				lane5.setResultSecondes(Integer.parseInt(perviousResultL5
						.getResultSecondes().toString()));
				lane5.setResultMSeconds(Integer.parseInt(perviousResultL5
						.getResultMSeconds().toString()));
				if (perviousResultL5.getPerformanceStatus().equals("OK")) {
					tMCBL5.setEnabled(false);
					tSCBL5.setEnabled(false);
					tMSCBL5.setEnabled(false);
					dsqL5.setEnabled(false);
					dnsL5.setEnabled(false);
					saveL5.setText("Edit");
					statusLableL5.setText("OK");
					statusLableL5.setForeground(new Color(0, 153, 0));
					lane5.setPerformanceStatus("OK");
					lane5.setResultTime();
				} else if (perviousResultL5.getPerformanceStatus()
						.equals("DSQ")) {
					tMCBL5.setEnabled(false);
					tSCBL5.setEnabled(false);
					tMSCBL5.setEnabled(false);
					saveL5.setEnabled(false);
					dnsL5.setEnabled(false);
					dsqL5.setText("dsq");
					statusLableL5.setText("DSQ");
					statusLableL5.setForeground(Color.RED);
					lane5.setPerformanceStatus("DSQ");
					lane5.setResultTime(99999998);
				} else if (perviousResultL5.getPerformanceStatus()
						.equals("DNS")) {
					tMCBL5.setEnabled(false);
					tSCBL5.setEnabled(false);
					tMSCBL5.setEnabled(false);
					saveL5.setEnabled(false);
					dsqL5.setEnabled(false);
					dnsL5.setText("dns");
					statusLableL5.setText("DNS");
					statusLableL5.setForeground(Color.BLUE);
					lane5.setPerformanceStatus("DNS");
					lane5.setResultTime(99999999);
				}
				resultLanes.add(lane5);
			}
			saveL5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (tMCBL5.isEnabled()) {
						lane5.setResultMinutes(Integer.parseInt(tMCBL5
								.getSelectedItem().toString()));
						lane5.setResultSecondes(Integer.parseInt(tSCBL5
								.getSelectedItem().toString()));
						lane5.setResultMSeconds(Integer.parseInt(tMSCBL5
								.getSelectedItem().toString()));
						lane5.setResultTime();
						lane5.setPerformanceStatus("OK");
						resultLanes.add(lane5);
						tMCBL5.setEnabled(false);
						tSCBL5.setEnabled(false);
						tMSCBL5.setEnabled(false);
						dsqL5.setEnabled(false);
						dnsL5.setEnabled(false);
						saveL5.setText("Edit");
						statusLableL5.setText("OK");
						statusLableL5.setForeground(new Color(0, 153, 0));
					} else {
						tMCBL5.setEnabled(true);
						tSCBL5.setEnabled(true);
						tMSCBL5.setEnabled(true);
						dsqL5.setEnabled(true);
						dnsL5.setEnabled(true);
						saveL5.setText("Save");
						statusLableL5.setText("waiting");
						statusLableL5.setForeground(Color.BLACK);
					}
				}
			});
			dsqL5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (tMCBL5.isEnabled()) {
						tMCBL5.setSelectedItem("00");
						tSCBL5.setSelectedItem("00");
						tMSCBL5.setSelectedItem("00");
						lane5.setResultMinutes(Integer.parseInt(tMCBL5
								.getSelectedItem().toString()));
						lane5.setResultSecondes(Integer.parseInt(tSCBL5
								.getSelectedItem().toString()));
						lane5.setResultMSeconds(Integer.parseInt(tMSCBL5
								.getSelectedItem().toString()));
						lane5.setResultTime(99999998);
						lane5.setPerformanceStatus("DSQ");
						resultLanes.add(lane5);
						tMCBL5.setEnabled(false);
						tSCBL5.setEnabled(false);
						tMSCBL5.setEnabled(false);
						saveL5.setEnabled(false);
						dnsL5.setEnabled(false);
						dsqL5.setText("dsq");
						statusLableL5.setText("DSQ");
						statusLableL5.setForeground(Color.RED);
					} else {
						tMCBL5.setEnabled(true);
						tSCBL5.setEnabled(true);
						tMSCBL5.setEnabled(true);
						saveL5.setEnabled(true);
						dnsL5.setEnabled(true);
						dsqL5.setText("DSQ");
						statusLableL5.setText("waiting");
						statusLableL5.setForeground(Color.BLACK);
					}
				}
			});
			dnsL5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (tMCBL5.isEnabled()) {
						tMCBL5.setSelectedItem("00");
						tSCBL5.setSelectedItem("00");
						tMSCBL5.setSelectedItem("00");
						lane5.setResultMinutes(Integer.parseInt(tMCBL5
								.getSelectedItem().toString()));
						lane5.setResultSecondes(Integer.parseInt(tSCBL5
								.getSelectedItem().toString()));
						lane5.setResultMSeconds(Integer.parseInt(tMSCBL5
								.getSelectedItem().toString()));
						lane5.setResultTime(99999999);
						lane5.setPerformanceStatus("DNS");
						resultLanes.add(lane5);
						tMCBL5.setEnabled(false);
						tSCBL5.setEnabled(false);
						tMSCBL5.setEnabled(false);
						saveL5.setEnabled(false);
						dsqL5.setEnabled(false);
						dnsL5.setText("dns");
						statusLableL5.setText("DNS");
						statusLableL5.setForeground(Color.BLUE);
					} else {
						tMCBL5.setEnabled(true);
						tSCBL5.setEnabled(true);
						tMSCBL5.setEnabled(true);
						saveL5.setEnabled(true);
						dsqL5.setEnabled(true);
						dnsL5.setText("DNS");
						statusLableL5.setText("waiting");
						statusLableL5.setForeground(Color.BLACK);
					}
				}
			});

			// Lane 6
			final TimesComboBox tMCBL6 = new TimesComboBox("minutes");
			final TimesComboBox tSCBL6 = new TimesComboBox("seconds");
			final TimesComboBox tMSCBL6 = new TimesComboBox("mseconds");
			final JButton saveL6 = new JButton("Save");
			final JButton dsqL6 = new JButton("DSQ");
			final JButton dnsL6 = new JButton("DNS");
			heatsPanel.add(new JLabel("Lane "
					+ heats.getLane6().getLaneNumber()));
			heatsPanel.add(new JLabel(heats.getLane6().getSwimmer().getName()));
			heatsPanel.add(new JLabel(heats.getLane6().getSwimmer()
					.getAgeGroup()));
			heatsPanel.add(new JLabel(heats.getLane6().getEntryMinutes() + ":"
					+ heats.getLane6().getEntrySecondes() + ":"
					+ heats.getLane6().getEntryMSeconds()));
			heatsPanel.add(tMCBL6);
			heatsPanel.add(tSCBL6);
			heatsPanel.add(tMSCBL6);
			heatsPanel.add(saveL6);
			heatsPanel.add(dsqL6);
			heatsPanel.add(dnsL6);
			final JLabel statusLableL6 = new JLabel("waiting", JLabel.CENTER);
			heatsPanel.add(statusLableL6);
			final Swimmer swimmerL6 = heats.getLane6().getSwimmer();
			final Event eventL6 = heats.getLane6().getEvent();
			final Lane lane6 = new Lane(swimmerL6, eventL6, 0, new Integer(0),
					new Integer(0), new Integer(0));
			// populate the times with the previously entered times, if there
			// are any
			Result perviousResultL6 = operations.getResultForSwimmer(results,
					heats.getLane6().getSwimmer());
			if (!perviousResultL6.getSwimmer().getName().equals("")) {
				tMCBL6.setSelectedItem(padLeft(perviousResultL6
						.getResultMinutes().toString(), 2));
				tSCBL6.setSelectedItem(padLeft(perviousResultL6
						.getResultSecondes().toString(), 2));
				tMSCBL6.setSelectedItem(padLeft(perviousResultL6
						.getResultMSeconds().toString(), 2));
				tMCBL6.setEnabled(false);
				tSCBL6.setEnabled(false);
				tMSCBL6.setEnabled(false);
				lane6.setResultMinutes(Integer.parseInt(perviousResultL6
						.getResultMinutes().toString()));
				lane6.setResultSecondes(Integer.parseInt(perviousResultL6
						.getResultSecondes().toString()));
				lane6.setResultMSeconds(Integer.parseInt(perviousResultL6
						.getResultMSeconds().toString()));
				if (perviousResultL6.getPerformanceStatus().equals("OK")) {
					tMCBL6.setEnabled(false);
					tSCBL6.setEnabled(false);
					tMSCBL6.setEnabled(false);
					dsqL6.setEnabled(false);
					dnsL6.setEnabled(false);
					saveL6.setText("Edit");
					statusLableL6.setText("OK");
					statusLableL6.setForeground(new Color(0, 153, 0));
					lane6.setResultTime();
					lane6.setPerformanceStatus("OK");
				} else if (perviousResultL6.getPerformanceStatus()
						.equals("DSQ")) {
					tMCBL6.setEnabled(false);
					tSCBL6.setEnabled(false);
					tMSCBL6.setEnabled(false);
					saveL6.setEnabled(false);
					dnsL6.setEnabled(false);
					dsqL6.setText("dsq");
					statusLableL6.setText("DSQ");
					statusLableL6.setForeground(Color.RED);
					lane6.setPerformanceStatus("DSQ");
					lane6.setResultTime(99999998);
				} else if (perviousResultL6.getPerformanceStatus()
						.equals("DNS")) {
					tMCBL6.setEnabled(false);
					tSCBL6.setEnabled(false);
					tMSCBL6.setEnabled(false);
					saveL6.setEnabled(false);
					dsqL6.setEnabled(false);
					dnsL6.setText("dns");
					statusLableL6.setText("DNS");
					statusLableL6.setForeground(Color.BLUE);
					lane6.setPerformanceStatus("DNS");
					lane6.setResultTime(99999999);
				}
				resultLanes.add(lane6);
			}
			saveL6.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (tMCBL6.isEnabled()) {
						lane6.setResultMinutes(Integer.parseInt(tMCBL6
								.getSelectedItem().toString()));
						lane6.setResultSecondes(Integer.parseInt(tSCBL6
								.getSelectedItem().toString()));
						lane6.setResultMSeconds(Integer.parseInt(tMSCBL6
								.getSelectedItem().toString()));
						lane6.setResultTime();
						lane6.setPerformanceStatus("OK");
						resultLanes.add(lane6);
						tMCBL6.setEnabled(false);
						tSCBL6.setEnabled(false);
						tMSCBL6.setEnabled(false);
						dsqL6.setEnabled(false);
						dnsL6.setEnabled(false);
						saveL6.setText("Edit");
						statusLableL6.setText("OK");
						statusLableL6.setForeground(new Color(0, 153, 0));
					} else {
						tMCBL6.setEnabled(true);
						tSCBL6.setEnabled(true);
						tMSCBL6.setEnabled(true);
						dsqL6.setEnabled(true);
						dnsL6.setEnabled(true);
						saveL6.setText("Save");
						statusLableL6.setText("waiting");
						statusLableL6.setForeground(Color.BLACK);
					}
				}
			});
			dsqL6.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (tMCBL6.isEnabled()) {
						tMCBL6.setSelectedItem("00");
						tSCBL6.setSelectedItem("00");
						tMSCBL6.setSelectedItem("00");
						lane6.setResultMinutes(Integer.parseInt(tMCBL6
								.getSelectedItem().toString()));
						lane6.setResultSecondes(Integer.parseInt(tSCBL6
								.getSelectedItem().toString()));
						lane6.setResultMSeconds(Integer.parseInt(tMSCBL6
								.getSelectedItem().toString()));
						lane6.setResultTime(99999998);
						lane6.setPerformanceStatus("DSQ");
						resultLanes.add(lane6);
						tMCBL6.setEnabled(false);
						tSCBL6.setEnabled(false);
						tMSCBL6.setEnabled(false);
						saveL6.setEnabled(false);
						dnsL6.setEnabled(false);
						dsqL6.setText("dsq");
						statusLableL6.setText("DSQ");
						statusLableL6.setForeground(Color.RED);
					} else {
						tMCBL6.setEnabled(true);
						tSCBL6.setEnabled(true);
						tMSCBL6.setEnabled(true);
						saveL6.setEnabled(true);
						dnsL6.setEnabled(true);
						dsqL6.setText("DSQ");
						statusLableL6.setText("waiting");
						statusLableL6.setForeground(Color.BLACK);
					}
				}
			});
			dnsL6.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (tMCBL6.isEnabled()) {
						tMCBL6.setSelectedItem("00");
						tSCBL6.setSelectedItem("00");
						tMSCBL6.setSelectedItem("00");
						lane6.setResultMinutes(Integer.parseInt(tMCBL6
								.getSelectedItem().toString()));
						lane6.setResultSecondes(Integer.parseInt(tSCBL6
								.getSelectedItem().toString()));
						lane6.setResultMSeconds(Integer.parseInt(tMSCBL6
								.getSelectedItem().toString()));
						lane6.setResultTime(99999999);
						lane6.setPerformanceStatus("DNS");
						resultLanes.add(lane6);
						tMCBL6.setEnabled(false);
						tSCBL6.setEnabled(false);
						tMSCBL6.setEnabled(false);
						saveL6.setEnabled(false);
						dsqL6.setEnabled(false);
						dnsL6.setText("dns");
						statusLableL6.setText("DNS");
						statusLableL6.setForeground(Color.BLUE);
					} else {
						tMCBL6.setEnabled(true);
						tSCBL6.setEnabled(true);
						tMSCBL6.setEnabled(true);
						saveL6.setEnabled(true);
						dsqL6.setEnabled(true);
						dnsL6.setText("DNS");
						statusLableL6.setText("waiting");
						statusLableL6.setForeground(Color.BLACK);
					}
				}
			});

			if (poolType.contains("50")) {
				// Lane 7
				final TimesComboBox tMCBL7 = new TimesComboBox("minutes");
				final TimesComboBox tSCBL7 = new TimesComboBox("seconds");
				final TimesComboBox tMSCBL7 = new TimesComboBox("mseconds");
				final JButton saveL7 = new JButton("Save");
				final JButton dsqL7 = new JButton("DSQ");
				final JButton dnsL7 = new JButton("DNS");
				heatsPanel.add(new JLabel("Lane "
						+ heats.getLane7().getLaneNumber()));
				heatsPanel.add(new JLabel(heats.getLane7().getSwimmer()
						.getName()));
				heatsPanel.add(new JLabel(heats.getLane7().getSwimmer()
						.getAgeGroup()));
				heatsPanel.add(new JLabel(heats.getLane7().getEntryMinutes()
						+ ":" + heats.getLane7().getEntrySecondes() + ":"
						+ heats.getLane7().getEntryMSeconds()));
				heatsPanel.add(tMCBL7);
				heatsPanel.add(tSCBL7);
				heatsPanel.add(tMSCBL7);
				heatsPanel.add(saveL7);
				heatsPanel.add(dsqL7);
				heatsPanel.add(dnsL7);
				final JLabel statusLableL7 = new JLabel("waiting",
						JLabel.CENTER);
				heatsPanel.add(statusLableL7);
				final Swimmer swimmerL7 = heats.getLane7().getSwimmer();
				final Event eventL7 = heats.getLane7().getEvent();
				final Lane lane7 = new Lane(swimmerL7, eventL7, 0, new Integer(
						0), new Integer(0), new Integer(0));
				// populate the times with the previously entered times, if
				// there
				// are any
				Result perviousResultL7 = operations.getResultForSwimmer(
						results, heats.getLane7().getSwimmer());
				if (!perviousResultL7.getSwimmer().getName().equals("")) {
					tMCBL7.setSelectedItem(padLeft(perviousResultL7
							.getResultMinutes().toString(), 2));
					tSCBL7.setSelectedItem(padLeft(perviousResultL7
							.getResultSecondes().toString(), 2));
					tMSCBL7.setSelectedItem(padLeft(perviousResultL7
							.getResultMSeconds().toString(), 2));
					tMCBL7.setEnabled(false);
					tSCBL7.setEnabled(false);
					tMSCBL7.setEnabled(false);
					lane7.setResultMinutes(Integer.parseInt(perviousResultL7
							.getResultMinutes().toString()));
					lane7.setResultSecondes(Integer.parseInt(perviousResultL7
							.getResultSecondes().toString()));
					lane7.setResultMSeconds(Integer.parseInt(perviousResultL7
							.getResultMSeconds().toString()));
					if (perviousResultL7.getPerformanceStatus().equals("OK")) {
						tMCBL7.setEnabled(false);
						tSCBL7.setEnabled(false);
						tMSCBL7.setEnabled(false);
						dsqL7.setEnabled(false);
						dnsL7.setEnabled(false);
						saveL7.setText("Edit");
						statusLableL7.setText("OK");
						statusLableL7.setForeground(new Color(0, 153, 0));
						lane7.setPerformanceStatus("OK");
						lane7.setResultTime();
					} else if (perviousResultL7.getPerformanceStatus().equals(
							"DSQ")) {
						tMCBL7.setEnabled(false);
						tSCBL7.setEnabled(false);
						tMSCBL7.setEnabled(false);
						saveL7.setEnabled(false);
						dnsL7.setEnabled(false);
						dsqL7.setText("dsq");
						statusLableL7.setText("DSQ");
						statusLableL7.setForeground(Color.RED);
						lane7.setPerformanceStatus("DSQ");
						lane7.setResultTime(99999998);
					} else if (perviousResultL7.getPerformanceStatus().equals(
							"DNS")) {
						tMCBL7.setEnabled(false);
						tSCBL7.setEnabled(false);
						tMSCBL7.setEnabled(false);
						saveL7.setEnabled(false);
						dsqL7.setEnabled(false);
						dnsL7.setText("dns");
						statusLableL7.setText("DNS");
						statusLableL7.setForeground(Color.BLUE);
						lane7.setPerformanceStatus("DNS");
						lane7.setResultTime(99999999);
					}
					resultLanes.add(lane7);
				}
				saveL7.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (tMCBL7.isEnabled()) {
							lane7.setResultMinutes(Integer.parseInt(tMCBL7
									.getSelectedItem().toString()));
							lane7.setResultSecondes(Integer.parseInt(tSCBL7
									.getSelectedItem().toString()));
							lane7.setResultMSeconds(Integer.parseInt(tMSCBL7
									.getSelectedItem().toString()));
							lane7.setResultTime();
							lane7.setPerformanceStatus("OK");
							resultLanes.add(lane7);
							tMCBL7.setEnabled(false);
							tSCBL7.setEnabled(false);
							tMSCBL7.setEnabled(false);
							dsqL7.setEnabled(false);
							dnsL7.setEnabled(false);
							saveL7.setText("Edit");
							statusLableL7.setText("OK");
							statusLableL7.setForeground(new Color(0, 153, 0));
						} else {
							tMCBL7.setEnabled(true);
							tSCBL7.setEnabled(true);
							tMSCBL7.setEnabled(true);
							dsqL7.setEnabled(true);
							dnsL7.setEnabled(true);
							saveL7.setText("Save");
							statusLableL7.setText("waiting");
							statusLableL7.setForeground(Color.BLACK);
						}
					}
				});
				dsqL7.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (tMCBL7.isEnabled()) {
							tMCBL7.setSelectedItem("00");
							tSCBL7.setSelectedItem("00");
							tMSCBL7.setSelectedItem("00");
							lane7.setResultMinutes(Integer.parseInt(tMCBL7
									.getSelectedItem().toString()));
							lane7.setResultSecondes(Integer.parseInt(tSCBL7
									.getSelectedItem().toString()));
							lane7.setResultMSeconds(Integer.parseInt(tMSCBL7
									.getSelectedItem().toString()));
							lane7.setResultTime(99999998);
							lane7.setPerformanceStatus("DSQ");
							resultLanes.add(lane7);
							tMCBL7.setEnabled(false);
							tSCBL7.setEnabled(false);
							tMSCBL7.setEnabled(false);
							saveL7.setEnabled(false);
							dnsL7.setEnabled(false);
							dsqL7.setText("dsq");
							statusLableL7.setText("DSQ");
							statusLableL7.setForeground(Color.RED);
						} else {
							tMCBL7.setEnabled(true);
							tSCBL7.setEnabled(true);
							tMSCBL7.setEnabled(true);
							saveL7.setEnabled(true);
							dnsL7.setEnabled(true);
							dsqL7.setText("DSQ");
							statusLableL7.setText("waiting");
							statusLableL7.setForeground(Color.BLACK);
						}
					}
				});
				dnsL7.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (tMCBL7.isEnabled()) {
							tMCBL7.setSelectedItem("00");
							tSCBL7.setSelectedItem("00");
							tMSCBL7.setSelectedItem("00");
							lane7.setResultMinutes(Integer.parseInt(tMCBL7
									.getSelectedItem().toString()));
							lane7.setResultSecondes(Integer.parseInt(tSCBL7
									.getSelectedItem().toString()));
							lane7.setResultMSeconds(Integer.parseInt(tMSCBL7
									.getSelectedItem().toString()));
							lane7.setResultTime(99999999);
							lane7.setPerformanceStatus("DNS");
							resultLanes.add(lane7);
							tMCBL7.setEnabled(false);
							tSCBL7.setEnabled(false);
							tMSCBL7.setEnabled(false);
							saveL7.setEnabled(false);
							dsqL7.setEnabled(false);
							dnsL7.setText("dns");
							statusLableL7.setText("DNS");
							statusLableL7.setForeground(Color.BLUE);
						} else {
							tMCBL7.setEnabled(true);
							tSCBL7.setEnabled(true);
							tMSCBL7.setEnabled(true);
							saveL7.setEnabled(true);
							dsqL7.setEnabled(true);
							dnsL7.setText("DNS");
							statusLableL7.setText("waiting");
							statusLableL7.setForeground(Color.BLACK);
						}
					}
				});

				// Lane 8
				final TimesComboBox tMCBL8 = new TimesComboBox("minutes");
				final TimesComboBox tSCBL8 = new TimesComboBox("seconds");
				final TimesComboBox tMSCBL8 = new TimesComboBox("mseconds");
				final JButton saveL8 = new JButton("Save");
				final JButton dsqL8 = new JButton("DSQ");
				final JButton dnsL8 = new JButton("DNS");
				heatsPanel.add(new JLabel("Lane "
						+ heats.getLane8().getLaneNumber()));
				heatsPanel.add(new JLabel(heats.getLane8().getSwimmer()
						.getName()));
				heatsPanel.add(new JLabel(heats.getLane8().getSwimmer()
						.getAgeGroup()));
				heatsPanel.add(new JLabel(heats.getLane8().getEntryMinutes()
						+ ":" + heats.getLane8().getEntrySecondes() + ":"
						+ heats.getLane8().getEntryMSeconds()));
				heatsPanel.add(tMCBL8);
				heatsPanel.add(tSCBL8);
				heatsPanel.add(tMSCBL8);
				heatsPanel.add(saveL8);
				heatsPanel.add(dsqL8);
				heatsPanel.add(dnsL8);
				final JLabel statusLableL8 = new JLabel("waiting",
						JLabel.CENTER);
				heatsPanel.add(statusLableL8);
				final Swimmer swimmerL8 = heats.getLane8().getSwimmer();
				final Event eventL8 = heats.getLane8().getEvent();
				final Lane lane8 = new Lane(swimmerL8, eventL8, 0, new Integer(
						0), new Integer(0), new Integer(0));
				// populate the times with the previously entered times, if
				// there
				// are any
				Result perviousResultL8 = operations.getResultForSwimmer(
						results, heats.getLane8().getSwimmer());
				if (!perviousResultL8.getSwimmer().getName().equals("")) {
					tMCBL8.setSelectedItem(padLeft(perviousResultL8
							.getResultMinutes().toString(), 2));
					tSCBL8.setSelectedItem(padLeft(perviousResultL8
							.getResultSecondes().toString(), 2));
					tMSCBL8.setSelectedItem(padLeft(perviousResultL8
							.getResultMSeconds().toString(), 2));
					tMCBL8.setEnabled(false);
					tSCBL8.setEnabled(false);
					tMSCBL8.setEnabled(false);
					lane8.setResultMinutes(Integer.parseInt(perviousResultL8
							.getResultMinutes().toString()));
					lane8.setResultSecondes(Integer.parseInt(perviousResultL8
							.getResultSecondes().toString()));
					lane8.setResultMSeconds(Integer.parseInt(perviousResultL8
							.getResultMSeconds().toString()));
					if (perviousResultL8.getPerformanceStatus().equals("OK")) {
						tMCBL8.setEnabled(false);
						tSCBL8.setEnabled(false);
						tMSCBL8.setEnabled(false);
						dsqL8.setEnabled(false);
						dnsL8.setEnabled(false);
						saveL8.setText("Edit");
						statusLableL8.setText("OK");
						statusLableL8.setForeground(new Color(0, 153, 0));
						lane8.setPerformanceStatus("OK");
						lane8.setResultTime();
					} else if (perviousResultL8.getPerformanceStatus().equals(
							"DSQ")) {
						tMCBL8.setEnabled(false);
						tSCBL8.setEnabled(false);
						tMSCBL8.setEnabled(false);
						saveL8.setEnabled(false);
						dnsL8.setEnabled(false);
						dsqL8.setText("dsq");
						statusLableL8.setText("DSQ");
						statusLableL8.setForeground(Color.RED);
						lane8.setPerformanceStatus("DSQ");
						lane8.setResultTime(99999998);
					} else if (perviousResultL8.getPerformanceStatus().equals(
							"DNS")) {
						tMCBL8.setEnabled(false);
						tSCBL8.setEnabled(false);
						tMSCBL8.setEnabled(false);
						saveL8.setEnabled(false);
						dsqL8.setEnabled(false);
						dnsL8.setText("dns");
						statusLableL8.setText("DNS");
						statusLableL8.setForeground(Color.BLUE);
						lane8.setPerformanceStatus("DNS");
						lane8.setResultTime(99999999);
					}
					resultLanes.add(lane8);
				}
				saveL8.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (tMCBL8.isEnabled()) {
							Lane lane8 = new Lane(swimmerL8, eventL8, 0,
									new Integer(0), new Integer(0),
									new Integer(0));
							lane8.setResultMinutes(Integer.parseInt(tMCBL8
									.getSelectedItem().toString()));
							lane8.setResultSecondes(Integer.parseInt(tSCBL8
									.getSelectedItem().toString()));
							lane8.setResultMSeconds(Integer.parseInt(tMSCBL8
									.getSelectedItem().toString()));
							lane8.setResultTime();
							lane8.setPerformanceStatus("OK");
							resultLanes.add(lane8);
							tMCBL8.setEnabled(false);
							tSCBL8.setEnabled(false);
							tMSCBL8.setEnabled(false);
							dsqL8.setEnabled(false);
							dnsL8.setEnabled(false);
							saveL8.setText("Edit");
							statusLableL8.setText("OK");
							statusLableL8.setForeground(new Color(0, 153, 0));
						} else {
							tMCBL8.setEnabled(true);
							tSCBL8.setEnabled(true);
							tMSCBL8.setEnabled(true);
							dsqL8.setEnabled(true);
							dnsL8.setEnabled(true);
							saveL8.setText("Save");
							statusLableL8.setText("waiting");
							statusLableL8.setForeground(Color.BLACK);
						}
					}
				});
				dsqL8.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (tMCBL8.isEnabled()) {
							tMCBL8.setSelectedItem("00");
							tSCBL8.setSelectedItem("00");
							tMSCBL8.setSelectedItem("00");
							lane8.setResultMinutes(Integer.parseInt(tMCBL8
									.getSelectedItem().toString()));
							lane8.setResultSecondes(Integer.parseInt(tSCBL8
									.getSelectedItem().toString()));
							lane8.setResultMSeconds(Integer.parseInt(tMSCBL8
									.getSelectedItem().toString()));
							lane8.setResultTime(99999998);
							lane8.setPerformanceStatus("DSQ");
							resultLanes.add(lane8);
							tMCBL8.setEnabled(false);
							tSCBL8.setEnabled(false);
							tMSCBL8.setEnabled(false);
							saveL8.setEnabled(false);
							dnsL8.setEnabled(false);
							dsqL8.setText("dsq");
							statusLableL8.setText("DSQ");
							statusLableL8.setForeground(Color.RED);
						} else {
							tMCBL8.setEnabled(true);
							tSCBL8.setEnabled(true);
							tMSCBL8.setEnabled(true);
							saveL8.setEnabled(true);
							dnsL8.setEnabled(true);
							dsqL8.setText("DSQ");
							statusLableL8.setText("waiting");
							statusLableL8.setForeground(Color.BLACK);
						}
					}
				});
				dnsL8.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (tMCBL8.isEnabled()) {
							tMCBL8.setSelectedItem("00");
							tSCBL8.setSelectedItem("00");
							tMSCBL8.setSelectedItem("00");
							lane8.setResultMinutes(Integer.parseInt(tMCBL8
									.getSelectedItem().toString()));
							lane8.setResultSecondes(Integer.parseInt(tSCBL8
									.getSelectedItem().toString()));
							lane8.setResultMSeconds(Integer.parseInt(tMSCBL8
									.getSelectedItem().toString()));
							lane8.setResultTime(99999999);
							lane8.setPerformanceStatus("DNS");
							resultLanes.add(lane8);
							tMCBL8.setEnabled(false);
							tSCBL8.setEnabled(false);
							tMSCBL8.setEnabled(false);
							saveL8.setEnabled(false);
							dsqL8.setEnabled(false);
							dnsL8.setText("dns");
							statusLableL8.setText("DNS");
							statusLableL8.setForeground(Color.BLUE);
						} else {
							tMCBL8.setEnabled(true);
							tSCBL8.setEnabled(true);
							tMSCBL8.setEnabled(true);
							saveL8.setEnabled(true);
							dsqL8.setEnabled(true);
							dnsL8.setText("DNS");
							statusLableL8.setText("waiting");
							statusLableL8.setForeground(Color.BLACK);
						}
					}
				});
			}
			// separators
			heatsPanel.add(new JSeparator());
			heatsPanel.add(new JSeparator());
			heatsPanel.add(new JSeparator());
			heatsPanel.add(new JSeparator());
			heatsPanel.add(new JSeparator());
			heatsPanel.add(new JSeparator());
			heatsPanel.add(new JSeparator());
			heatsPanel.add(new JSeparator());
			heatsPanel.add(new JSeparator());
			heatsPanel.add(new JSeparator());
			heatsPanel.add(new JSeparator());
		}

		jBCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		jBGenerateRezults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printResults(event.getName());
			}
		});

		heatsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		JScrollPane editorScroll = new JScrollPane(heatsPanel);
		editorScroll
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		editorScroll
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		editorScroll.setViewportBorder(BorderFactory
				.createLineBorder(Color.black));

		pane.setLayout(new BorderLayout());
		pane.add(editorScroll);
		pane.add(controlsPanel, BorderLayout.SOUTH);
		pack();
		setVisible(true);
	}

	private void handleFile(String dirType, String fileType) throws IOException {
		// in this case only the dir is required
		if (fileType.equals("-1")) {
			File dir = new File(pathFile.get(dirType));
			if (!dir.exists()) {
				dir.mkdir();
			}
		}
	}

	private void printResults(String eventName) {
		try {
			handleFile("rezultate", "-1");
			FileWriter fstream = new FileWriter(pathFile.get("rezultate")
					+ "\\" + "Rezultate " + eventName + " " + heatGender + ".csv", false);
			BufferedWriter out = new BufferedWriter(fstream);
			for (Lane lanes : resultLanes) {
				out.write(lanes.getSwimmer().getName() + ";"
						+ lanes.getSwimmer().getAgeGroup() + ";"
						+ lanes.getSwimmer().getClub() + ";"
						+ lanes.getResultMinutes() + ";"
						+ lanes.getResultSecondes() + ";"
						+ lanes.getResultMSeconds() + ";"
						+ lanes.getResultTime() + ";"
						+ lanes.getPerformanceStatus());
				out.newLine();
			}
			out.close();
		} catch (Exception e) {
			e.getMessage();
		}
		// write the heats in pdf files
		// ResultWriter rWriter = new ResultWriter(event, competitionTitle);
		// rWriter.run();

	}

	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				AllHeats dialog = new AllHeats(new Event(), new String(),
						new String(), new String());
				// Operations operations = new Operations();
				// AllHeats dialog = new
				// AllHeats(operations.returnEvent("sprint"), "25 Meters",
				// "www");
				dialog.addWindowListener(new java.awt.event.WindowAdapter() {
					public void windowClosing(java.awt.event.WindowEvent e) {
						System.exit(0);
					}
				});
				dialog.setVisible(true);
			}
		});
	}

	private static String padLeft(String s, int n) {
		return String.format("%1$#" + n + "s", s).replace(' ', '0');
	}

	public Operations getOperations() {
		return operations;
	}

	public void setOperations(Operations operations) {
		this.operations = operations;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
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

	public List<Result> getResults() {
		return results;
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}

	public String getHeatGender() {
		return heatGender;
	}

	public void setHeatGender(String heatGender) {
		this.heatGender = heatGender;
	}

}
