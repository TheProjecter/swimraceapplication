package displays;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import customComponents.HeaderText;
import customComponents.TimesComboBox;

import work.Operations;
import entities.Event;
import entities.Heat;
import entities.Lane;
import entities.Swimmer;

public class AllHeats extends JDialog {

	private Operations operations = new Operations();
	private GridLayout controlLayout = new GridLayout(1, 6);
	private List<Lane> resultLanes = new ArrayList<Lane>();
	private Event event;
	private String poolType;
	
	private JButton jBCancel = new JButton("Cancel");
	private JButton jBGenerateRezults = new JButton("Generate Results");;

	private static final long serialVersionUID = 1L;

	public AllHeats(Event event, String poolType) {
		super();
		setEvent(event);
		setPoolType(poolType);
		setTitle("All heats for " + event.getName());
		addComponentsToPane(getContentPane());
		setAlwaysOnTop(true);
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
		List<Heat> heatList = operations.generateHeats(event, poolType);

		final JPanel heatsPanel = new JPanel();
		/**
		 * The lines are 8 or 10 based on the pooltype
		 */
		heatsPanel.setLayout(new GridLayout((heatList.size() * (poolType.contains("50") ? 10 : 8)), 8));

		for (Heat heats : heatList) {
			// header
			heatsPanel.add(new HeaderText("Heat " + heats.getHeatNumber()));
			heatsPanel.add(new HeaderText("Name"));
			heatsPanel.add(new HeaderText("Age Gr."));
			heatsPanel.add(new HeaderText("Entry"));
			heatsPanel.add(new HeaderText("Minutes"));
			heatsPanel.add(new HeaderText("Seconds"));
			heatsPanel.add(new HeaderText("M. Sec."));
			heatsPanel.add(new HeaderText("Save"));

			// Lane 1
			final TimesComboBox tMCBL1 = new TimesComboBox("minutes");
			final TimesComboBox tSCBL1 = new TimesComboBox("seconds");
			final TimesComboBox tMSCBL1 = new TimesComboBox("mseconds");
			JButton saveL1 = new JButton("Save");
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
			final Swimmer swimmerL1 = heats.getLane1().getSwimmer();
			final Event eventL1 = heats.getLane1().getEvent();
			saveL1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Lane lane1 = new Lane(swimmerL1, eventL1, 0,
							new Integer(0), new Integer(0), new Integer(0));
					lane1.setResultMinutes(Integer.parseInt(tMCBL1
							.getSelectedItem().toString()));
					lane1.setResultSecondes(Integer.parseInt(tSCBL1
							.getSelectedItem().toString()));
					lane1.setResultMSeconds(Integer.parseInt(tMSCBL1
							.getSelectedItem().toString()));
					lane1.setResultTime();
					resultLanes.add(lane1);
					tMCBL1.setEnabled(false);
					tSCBL1.setEnabled(false);
					tMSCBL1.setEnabled(false);
					System.out.println(lane1.getSwimmer().getName() + ";"
							+ lane1.getSwimmer().getAgeGroup() + ";"
							+ lane1.getResultMinutes() + ";"
							+ lane1.getResultSecondes() + ";"
							+ lane1.getResultMSeconds() + ";"
							+ lane1.getResultTime());
				}
			});

			// Lane 2
			final TimesComboBox tMCBL2 = new TimesComboBox("minutes");
			final TimesComboBox tSCBL2 = new TimesComboBox("seconds");
			final TimesComboBox tMSCBL2 = new TimesComboBox("mseconds");
			JButton saveL2 = new JButton("Save");
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
			final Swimmer swimmerL2 = heats.getLane2().getSwimmer();
			final Event eventL2 = heats.getLane2().getEvent();
			saveL2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Lane lane2 = new Lane(swimmerL2, eventL2, 0,
							new Integer(0), new Integer(0), new Integer(0));
					lane2.setResultMinutes(Integer.parseInt(tMCBL2
							.getSelectedItem().toString()));
					lane2.setResultSecondes(Integer.parseInt(tSCBL2
							.getSelectedItem().toString()));
					lane2.setResultMSeconds(Integer.parseInt(tMSCBL2
							.getSelectedItem().toString()));
					lane2.setResultTime();
					resultLanes.add(lane2);
					tMCBL2.setEnabled(false);
					tSCBL2.setEnabled(false);
					tMSCBL2.setEnabled(false);
					System.out.println(lane2.getSwimmer().getName() + ";"
							+ lane2.getSwimmer().getAgeGroup() + ";"
							+ lane2.getResultMinutes() + ";"
							+ lane2.getResultSecondes() + ";"
							+ lane2.getResultMSeconds() + ";"
							+ lane2.getResultTime());
				}
			});

			// Lane 3
			final TimesComboBox tMCBL3 = new TimesComboBox("minutes");
			final TimesComboBox tSCBL3 = new TimesComboBox("seconds");
			final TimesComboBox tMSCBL3 = new TimesComboBox("mseconds");
			JButton saveL3 = new JButton("Save");
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
			heatsPanel.add(saveL3);
			final Swimmer swimmerL3 = heats.getLane3().getSwimmer();
			final Event eventL3 = heats.getLane3().getEvent();
			saveL3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Lane lane3 = new Lane(swimmerL3, eventL3, 0,
							new Integer(0), new Integer(0), new Integer(0));
					lane3.setResultMinutes(Integer.parseInt(tMCBL3
							.getSelectedItem().toString()));
					lane3.setResultSecondes(Integer.parseInt(tSCBL3
							.getSelectedItem().toString()));
					lane3.setResultMSeconds(Integer.parseInt(tMSCBL3
							.getSelectedItem().toString()));
					lane3.setResultTime();
					resultLanes.add(lane3);
					tMCBL3.setEnabled(false);
					tSCBL3.setEnabled(false);
					tMSCBL3.setEnabled(false);
					System.out.println(lane3.getSwimmer().getName() + ";"
							+ lane3.getSwimmer().getAgeGroup() + ";"
							+ lane3.getResultMinutes() + ";"
							+ lane3.getResultSecondes() + ";"
							+ lane3.getResultMSeconds() + ";"
							+ lane3.getResultTime());
				}
			});

			// Lane 4
			final TimesComboBox tMCBL4 = new TimesComboBox("minutes");
			final TimesComboBox tSCBL4 = new TimesComboBox("seconds");
			final TimesComboBox tMSCBL4 = new TimesComboBox("mseconds");
			JButton saveL4 = new JButton("Save");
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
			final Swimmer swimmerL4 = heats.getLane4().getSwimmer();
			final Event eventL4 = heats.getLane4().getEvent();
			saveL4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Lane lane4 = new Lane(swimmerL4, eventL4, 0,
							new Integer(0), new Integer(0), new Integer(0));
					lane4.setResultMinutes(Integer.parseInt(tMCBL4
							.getSelectedItem().toString()));
					lane4.setResultSecondes(Integer.parseInt(tSCBL4
							.getSelectedItem().toString()));
					lane4.setResultMSeconds(Integer.parseInt(tMSCBL4
							.getSelectedItem().toString()));
					lane4.setResultTime();
					resultLanes.add(lane4);
					tMCBL4.setEnabled(false);
					tSCBL4.setEnabled(false);
					tMSCBL4.setEnabled(false);
					System.out.println(lane4.getSwimmer().getName() + ";"
							+ lane4.getSwimmer().getAgeGroup() + ";"
							+ lane4.getResultMinutes() + ";"
							+ lane4.getResultSecondes() + ";"
							+ lane4.getResultMSeconds() + ";"
							+ lane4.getResultTime());
				}
			});

			// Lane 5
			final TimesComboBox tMCBL5 = new TimesComboBox("minutes");
			final TimesComboBox tSCBL5 = new TimesComboBox("seconds");
			final TimesComboBox tMSCBL5 = new TimesComboBox("mseconds");
			JButton saveL5 = new JButton("Save");
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
			final Swimmer swimmerL5 = heats.getLane5().getSwimmer();
			final Event eventL5 = heats.getLane5().getEvent();
			saveL5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Lane lane5 = new Lane(swimmerL5, eventL5, 0,
							new Integer(0), new Integer(0), new Integer(0));
					lane5.setResultMinutes(Integer.parseInt(tMCBL5
							.getSelectedItem().toString()));
					lane5.setResultSecondes(Integer.parseInt(tSCBL5
							.getSelectedItem().toString()));
					lane5.setResultMSeconds(Integer.parseInt(tMSCBL5
							.getSelectedItem().toString()));
					lane5.setResultTime();
					resultLanes.add(lane5);
					tMCBL5.setEnabled(false);
					tSCBL5.setEnabled(false);
					tMSCBL5.setEnabled(false);
					System.out.println(lane5.getSwimmer().getName() + ";"
							+ lane5.getSwimmer().getAgeGroup() + ";"
							+ lane5.getResultMinutes() + ";"
							+ lane5.getResultSecondes() + ";"
							+ lane5.getResultMSeconds() + ";"
							+ lane5.getResultTime());
				}
			});

			// Lane 6
			final TimesComboBox tMCBL6 = new TimesComboBox("minutes");
			final TimesComboBox tSCBL6 = new TimesComboBox("seconds");
			final TimesComboBox tMSCBL6 = new TimesComboBox("mseconds");
			JButton saveL6 = new JButton("Save");
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
			final Swimmer swimmerL6 = heats.getLane6().getSwimmer();
			final Event eventL6 = heats.getLane6().getEvent();
			saveL6.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Lane lane6 = new Lane(swimmerL6, eventL6, 0,
							new Integer(0), new Integer(0), new Integer(0));
					lane6.setResultMinutes(Integer.parseInt(tMCBL6
							.getSelectedItem().toString()));
					lane6.setResultSecondes(Integer.parseInt(tSCBL6
							.getSelectedItem().toString()));
					lane6.setResultMSeconds(Integer.parseInt(tMSCBL6
							.getSelectedItem().toString()));
					lane6.setResultTime();
					resultLanes.add(lane6);
					tMCBL6.setEnabled(false);
					tSCBL6.setEnabled(false);
					tMSCBL6.setEnabled(false);
					System.out.println(lane6.getSwimmer().getName() + ";"
							+ lane6.getSwimmer().getAgeGroup() + ";"
							+ lane6.getResultMinutes() + ";"
							+ lane6.getResultSecondes() + ";"
							+ lane6.getResultMSeconds() + ";"
							+ lane6.getResultTime());
				}
			});
			
			if (poolType.contains("50")) {
				// Lane 7
				final TimesComboBox tMCBL7 = new TimesComboBox("minutes");
				final TimesComboBox tSCBL7 = new TimesComboBox("seconds");
				final TimesComboBox tMSCBL7 = new TimesComboBox("mseconds");
				JButton saveL7 = new JButton("Save");
				heatsPanel.add(new JLabel("Lane "
						+ heats.getLane7().getLaneNumber()));
				heatsPanel.add(new JLabel(heats.getLane7().getSwimmer().getName()));
				heatsPanel.add(new JLabel(heats.getLane7().getSwimmer()
						.getAgeGroup()));
				heatsPanel.add(new JLabel(heats.getLane7().getEntryMinutes() + ":"
						+ heats.getLane7().getEntrySecondes() + ":"
						+ heats.getLane7().getEntryMSeconds()));
				heatsPanel.add(tMCBL7);
				heatsPanel.add(tSCBL7);
				heatsPanel.add(tMSCBL7);
				heatsPanel.add(saveL7);
				final Swimmer swimmerL7 = heats.getLane7().getSwimmer();
				final Event eventL7 = heats.getLane7().getEvent();
				saveL7.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Lane lane7 = new Lane(swimmerL7, eventL7, 0,
								new Integer(0), new Integer(0), new Integer(0));
						lane7.setResultMinutes(Integer.parseInt(tMCBL7
								.getSelectedItem().toString()));
						lane7.setResultSecondes(Integer.parseInt(tSCBL7
								.getSelectedItem().toString()));
						lane7.setResultMSeconds(Integer.parseInt(tMSCBL7
								.getSelectedItem().toString()));
						lane7.setResultTime();
						resultLanes.add(lane7);
						tMCBL7.setEnabled(false);
						tSCBL7.setEnabled(false);
						tMSCBL7.setEnabled(false);
						System.out.println(lane7.getSwimmer().getName() + ";"
								+ lane7.getSwimmer().getAgeGroup() + ";"
								+ lane7.getResultMinutes() + ";"
								+ lane7.getResultSecondes() + ";"
								+ lane7.getResultMSeconds() + ";"
								+ lane7.getResultTime());
					}
				});
	
				// Lane 8
				final TimesComboBox tMCBL8 = new TimesComboBox("minutes");
				final TimesComboBox tSCBL8 = new TimesComboBox("seconds");
				final TimesComboBox tMSCBL8 = new TimesComboBox("mseconds");
				JButton saveL8 = new JButton("Save");
				heatsPanel.add(new JLabel("Lane "
						+ heats.getLane8().getLaneNumber()));
				heatsPanel.add(new JLabel(heats.getLane8().getSwimmer().getName()));
				heatsPanel.add(new JLabel(heats.getLane8().getSwimmer()
						.getAgeGroup()));
				heatsPanel.add(new JLabel(heats.getLane8().getEntryMinutes() + ":"
						+ heats.getLane8().getEntrySecondes() + ":"
						+ heats.getLane8().getEntryMSeconds()));
				heatsPanel.add(tMCBL8);
				heatsPanel.add(tSCBL8);
				heatsPanel.add(tMSCBL8);
				heatsPanel.add(saveL8);
				final Swimmer swimmerL8 = heats.getLane8().getSwimmer();
				final Event eventL8 = heats.getLane8().getEvent();
				saveL8.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Lane lane8 = new Lane(swimmerL8, eventL8, 0,
								new Integer(0), new Integer(0), new Integer(0));
						lane8.setResultMinutes(Integer.parseInt(tMCBL8
								.getSelectedItem().toString()));
						lane8.setResultSecondes(Integer.parseInt(tSCBL8
								.getSelectedItem().toString()));
						lane8.setResultMSeconds(Integer.parseInt(tMSCBL8
								.getSelectedItem().toString()));
						lane8.setResultTime();
						resultLanes.add(lane8);
						tMCBL8.setEnabled(false);
						tSCBL8.setEnabled(false);
						tMSCBL8.setEnabled(false);
						System.out.println(lane8.getSwimmer().getName() + ";"
								+ lane8.getSwimmer().getAgeGroup() + ";"
								+ lane8.getResultMinutes() + ";"
								+ lane8.getResultSecondes() + ";"
								+ lane8.getResultMSeconds() + ";"
								+ lane8.getResultTime());
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

	private void printResults(String eventName) {
		try {
			FileWriter fstream = new FileWriter("Rezultate " + eventName + ".csv", true);
			BufferedWriter out = new BufferedWriter(fstream);
			for (Lane lanes : resultLanes) {
				out.write(lanes.getSwimmer().getName() + ";"
						+ lanes.getSwimmer().getAgeGroup() + ";"
						+ lanes.getSwimmer().getClub() + ";"
						+ lanes.getResultMinutes() + ";"
						+ lanes.getResultSecondes() + ";"
						+ lanes.getResultMSeconds() + ";"
						+ lanes.getResultTime());
				out.newLine();
			}
			out.close();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				AllHeats dialog = new AllHeats(new Event(), new String());
				dialog.addWindowListener(new java.awt.event.WindowAdapter() {
					public void windowClosing(java.awt.event.WindowEvent e) {
						System.exit(0);
					}
				});
				dialog.setVisible(true);
			}
		});
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

}
