/*
 * SwimRaceOrganizerView.java
 */

package swimraceorganizer;

import java.lang.reflect.InvocationTargetException;

import generators.HeatCreatorGenerator;
import generators.HeatOutputGenerator;
import generators.HeatResultGenerator;

import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.jdesktop.application.Action;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.SingleFrameApplication;

import displays.AllEvents;
import displays.AllRegistrations;
import displays.AllSwimmers;

/**
 * The application's main frame.
 */
public class SwimRaceOrganizerView extends FrameView {

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jBAddSwimmer;
	private javax.swing.JButton jBAddSwimmingEvent;
	private javax.swing.JButton jBAllEvents;
	private javax.swing.JButton jBAllRegistrations;
	private javax.swing.JButton jBAllSwimmers;
	private javax.swing.JButton jBGenerateResults;
	private javax.swing.JButton jBRegisterSwimmer;
	private javax.swing.JButton jBStoreResults;
	private javax.swing.JButton jBGenerateHeats;
	private javax.swing.JLabel jLEventActions;
	private javax.swing.JLabel jLGenerateActions;
	private javax.swing.JLabel jLSwimmerActions;
	private javax.swing.JLabel jRegistrationActions;
	private javax.swing.JSeparator jSeparator1;
	private javax.swing.JSeparator jSeparator2;
	private javax.swing.JPanel mainPanel;
	private javax.swing.JMenuBar menuBar;
	// End of variables declaration//GEN-END:variables

	private final Icon[] busyIcons = new Icon[15];

	private JDialog aboutBox;
	private JDialog addSwimmer;
	private JDialog addSwimmingEvent;
	private JDialog allSwimmers;
	private JDialog allEvents;
	private JDialog registerSwimmer;
	private JDialog generateHeats;
	private JDialog allRegistrations;
	private HeatOutputGenerator generateHeatOutputs;
	private HeatResultGenerator generateHeatResults;
	private SettingsWindow settingsWindow;

	private String poolType;
	private String competitionName;

	public SwimRaceOrganizerView(SingleFrameApplication app) {
		super(app);
		initComponents();
		getFrame().setResizable(false);
	}

	@Action
	public void showAboutBox() {
		if (aboutBox == null) {
			AboutWindow about = new AboutWindow();
			try {

				Display display = new Display();
				Shell shell = new AboutWindow().createShell(display);
				shell.open();
				while (!shell.isDisposed()) {
					if (!display.readAndDispatch())
						display.sleep();
				}
			} catch (Exception e) {
				//System.out.println("Invocation");
				System.out.println(e.getMessage());
			}
		}
		// SwimRaceOrganizerApp.getApplication().show(aboutBox);
	}

	@Action
	public void settings() {
		if (settingsWindow == null) {
			JFrame mainFrame = SwimRaceOrganizerApp.getApplication().getMainFrame();
			settingsWindow = new SettingsWindow();
			settingsWindow.setLocationRelativeTo(mainFrame);
		}
		SwimRaceOrganizerApp.getApplication().show(settingsWindow);
		settingsWindow.setSize(300, 180);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		mainPanel = new javax.swing.JPanel();
		jBAddSwimmer = new javax.swing.JButton();
		jBAddSwimmingEvent = new javax.swing.JButton();
		jBAllSwimmers = new javax.swing.JButton();
		jBAllEvents = new javax.swing.JButton();
		jSeparator1 = new javax.swing.JSeparator();
		jLSwimmerActions = new javax.swing.JLabel();
		jLEventActions = new javax.swing.JLabel();
		jBRegisterSwimmer = new javax.swing.JButton();
		jBGenerateHeats = new javax.swing.JButton();
		jBAllRegistrations = new javax.swing.JButton();
		jRegistrationActions = new javax.swing.JLabel();
		jSeparator2 = new javax.swing.JSeparator();
		jLGenerateActions = new javax.swing.JLabel();
		jBStoreResults = new javax.swing.JButton();
		jBGenerateResults = new javax.swing.JButton();
		menuBar = new javax.swing.JMenuBar();
		javax.swing.JMenu fileMenu = new javax.swing.JMenu();
		javax.swing.JMenuItem settingsMenuItem = new javax.swing.JMenuItem();
		javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
		javax.swing.JMenu helpMenu = new javax.swing.JMenu();
		javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();

		mainPanel.setName("mainPanel"); // NOI18N

		org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(swimraceorganizer.SwimRaceOrganizerApp.class).getContext()
				.getResourceMap(SwimRaceOrganizerView.class);
		jBAddSwimmer.setText(resourceMap.getString("jBAddSwimmer.text")); // NOI18N
		jBAddSwimmer.setName("jBAddSwimmer"); // NOI18N
		jBAddSwimmer.setPreferredSize(new java.awt.Dimension(100, 25));
		jBAddSwimmer.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jBAddSwimmerActionPerformed(evt);
			}
		});

		jBAddSwimmingEvent.setText(resourceMap.getString("jBAddSwimmingEvent.text")); // NOI18N
		jBAddSwimmingEvent.setName("jBAddSwimmingEvent"); // NOI18N
		jBAddSwimmingEvent.setPreferredSize(new java.awt.Dimension(100, 25));
		jBAddSwimmingEvent.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jBAddSwimmingEventActionPerformed(evt);
			}
		});

		jBAllSwimmers.setText(resourceMap.getString("jBAllSwimmers.text")); // NOI18N
		jBAllSwimmers.setName("jBAllSwimmers"); // NOI18N
		jBAllSwimmers.setPreferredSize(new java.awt.Dimension(100, 25));
		jBAllSwimmers.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jBAllSwimmersActionPerformed(evt);
			}
		});

		jBAllEvents.setText(resourceMap.getString("jBAllEvents.text")); // NOI18N
		jBAllEvents.setName("jBAllEvents"); // NOI18N
		jBAllEvents.setPreferredSize(new java.awt.Dimension(100, 25));
		jBAllEvents.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jBAllEventsActionPerformed(evt);
			}
		});

		jSeparator1.setName("jSeparator1"); // NOI18N

		jLSwimmerActions.setText(resourceMap.getString("jLSwimmerActions.text")); // NOI18N
		jLSwimmerActions.setName("jLSwimmerActions"); // NOI18N

		jLEventActions.setText(resourceMap.getString("jLEventActions.text")); // NOI18N
		jLEventActions.setName("jLEventActions"); // NOI18N

		jBRegisterSwimmer.setText(resourceMap.getString("jBRegisterSwimmer.text")); // NOI18N
		jBRegisterSwimmer.setName("jBRegisterSwimmer"); // NOI18N
		jBRegisterSwimmer.setPreferredSize(new java.awt.Dimension(100, 25));
		jBRegisterSwimmer.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jBRegisterSwimmerActionPerformed(evt);
			}
		});

		jBGenerateHeats.setText(resourceMap.getString("jBgenerateHeats.text")); // NOI18N
		jBGenerateHeats.setName("jBgenerateHeats"); // NOI18N
		jBGenerateHeats.setPreferredSize(new java.awt.Dimension(100, 25));
		jBGenerateHeats.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jBGenerateHeatsActionPerformed(evt);
			}
		});

		jBAllRegistrations.setText(resourceMap.getString("jBAllRegistrations.text")); // NOI18N
		jBAllRegistrations.setName("jBAllRegistrations"); // NOI18N
		jBAllRegistrations.setPreferredSize(new java.awt.Dimension(100, 25));
		jBAllRegistrations.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jBAllRegistrationsActionPerformed(evt);
			}
		});

		jRegistrationActions.setText(resourceMap.getString("jRegistrationActions.text")); // NOI18N
		jRegistrationActions.setName("jRegistrationActions"); // NOI18N

		jSeparator2.setName("jSeparator2"); // NOI18N

		jLGenerateActions.setText(resourceMap.getString("jLGenerateActions.text")); // NOI18N
		jLGenerateActions.setName("jLGenerateActions"); // NOI18N

		jBStoreResults.setText(resourceMap.getString("jBStoreResults.text")); // NOI18N
		jBStoreResults.setName("jBStoreResults"); // NOI18N
		jBStoreResults.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jBStoreResultsActionPerformed(evt);
			}
		});

		jBGenerateResults.setText(resourceMap.getString("jBGenerateResults.text")); // NOI18N
		jBGenerateResults.setName("jBGenerateResults"); // NOI18N
		jBGenerateResults.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jBGenerateResultsActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout gl_mainPanel = new javax.swing.GroupLayout(mainPanel);
		mainPanel.setLayout(gl_mainPanel);
		gl_mainPanel.setHorizontalGroup(gl_mainPanel
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						gl_mainPanel
								.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										gl_mainPanel.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jBRegisterSwimmer, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jBGenerateHeats, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jBAllSwimmers, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jBAddSwimmer, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGroup(
										gl_mainPanel
												.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
												.addGroup(
														gl_mainPanel
																.createSequentialGroup()
																.addGap(143, 143, 143)
																.addGroup(
																		gl_mainPanel
																				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(jBAddSwimmingEvent, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE,
																						162, Short.MAX_VALUE)
																				.addComponent(jBAllEvents, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 162,
																						Short.MAX_VALUE)
																				.addComponent(jBAllRegistrations, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE,
																						162, Short.MAX_VALUE)))
												.addGroup(
														gl_mainPanel.createSequentialGroup().addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(jBStoreResults, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))).addContainerGap())
				.addGroup(gl_mainPanel.createSequentialGroup().addGap(10, 10, 10).addComponent(jLSwimmerActions).addGap(401, 401, 401))
				.addGroup(
						gl_mainPanel.createSequentialGroup().addContainerGap().addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
								.addContainerGap(11, Short.MAX_VALUE))
				.addGroup(
						gl_mainPanel.createSequentialGroup().addContainerGap().addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
								.addContainerGap(11, Short.MAX_VALUE))
				.addGroup(gl_mainPanel.createSequentialGroup().addContainerGap().addComponent(jLGenerateActions).addContainerGap(423, Short.MAX_VALUE))
				.addGroup(javax.swing.GroupLayout.Alignment.CENTER, gl_mainPanel.createSequentialGroup().addGap(367, 367, 367).addComponent(jLEventActions).addGap(58, 58, 58))
				.addGroup(gl_mainPanel.createSequentialGroup().addContainerGap().addComponent(jRegistrationActions).addContainerGap(385, Short.MAX_VALUE))
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						gl_mainPanel.createSequentialGroup().addContainerGap(317, Short.MAX_VALUE)
								.addComponent(jBGenerateResults, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap()));
		gl_mainPanel
				.setVerticalGroup(gl_mainPanel.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								gl_mainPanel
										.createSequentialGroup()
										.addGap(15, 15, 15)
										.addGroup(
												gl_mainPanel
														.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
														.addGroup(
																gl_mainPanel
																		.createSequentialGroup()
																		.addComponent(jLSwimmerActions)
																		.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(jBAddSwimmer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(jBAllSwimmers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(
																gl_mainPanel
																		.createSequentialGroup()
																		.addComponent(jLEventActions)
																		.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(jBAddSwimmingEvent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(jBAllEvents, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jRegistrationActions)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												gl_mainPanel
														.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jBRegisterSwimmer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jBAllRegistrations, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(5, 5, 5)
										.addComponent(jLGenerateActions)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												gl_mainPanel
														.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jBGenerateHeats, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jBStoreResults))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jBGenerateResults)
										.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		menuBar.setName("menuBar"); // NOI18N

		fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
		fileMenu.setName("fileMenu"); // NOI18N

		javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(swimraceorganizer.SwimRaceOrganizerApp.class).getContext().getActionMap(SwimRaceOrganizerView.class, this);
		settingsMenuItem.setAction(actionMap.get("settings")); // NOI18N
		settingsMenuItem.setText(resourceMap.getString("settingsMenuItem.text")); // NOI18N
		settingsMenuItem.setName("settingsMenuItem"); // NOI18N
		fileMenu.add(settingsMenuItem);

		exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
		exitMenuItem.setName("exitMenuItem"); // NOI18N
		fileMenu.add(exitMenuItem);

		menuBar.add(fileMenu);

		helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
		helpMenu.setName("helpMenu"); // NOI18N

		aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
		aboutMenuItem.setName("aboutMenuItem"); // NOI18N
		helpMenu.add(aboutMenuItem);

		menuBar.add(helpMenu);

		setComponent(mainPanel);
		setMenuBar(menuBar);

	}// </editor-fold>//GEN-END:initComponents

	private void jBAddSwimmerActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jBAddSwimmerActionPerformed
		JFrame mainFrame = SwimRaceOrganizerApp.getApplication().getMainFrame();
		addSwimmer = new AddSwimmer();
		addSwimmer.setLocationRelativeTo(mainFrame);
		SwimRaceOrganizerApp.getApplication().show(addSwimmer);
	}// GEN-LAST:event_jBAddSwimmerActionPerformed

	private void jBAddSwimmingEventActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jBAddSwimmingEventActionPerformed
		JFrame mainFrame = SwimRaceOrganizerApp.getApplication().getMainFrame();
		try {
			setPoolType(settingsWindow.getPoolType());
			addSwimmingEvent = new AddSwimmingEvent(poolType);
			addSwimmingEvent.setLocationRelativeTo(mainFrame);
			SwimRaceOrganizerApp.getApplication().show(addSwimmingEvent);
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "Trebuie completate setarile pt a purcede!", "Atentie!!!", 1);			
		}
	}// GEN-LAST:event_jBAddSwimmingEventActionPerformed

	private void jBAllSwimmersActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jBAllSwimmersActionPerformed
		JFrame mainFrame = SwimRaceOrganizerApp.getApplication().getMainFrame();
		allSwimmers = new AllSwimmers(mainFrame, true);
		allSwimmers.setLocationRelativeTo(mainFrame);
		SwimRaceOrganizerApp.getApplication().show(allSwimmers);
	}// GEN-LAST:event_jBAllSwimmersActionPerformed

	private void jBAllEventsActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jBAllEventsActionPerformed
		JFrame mainFrame = SwimRaceOrganizerApp.getApplication().getMainFrame();
		allEvents = new AllEvents(mainFrame, true);
		allEvents.setLocationRelativeTo(mainFrame);
		SwimRaceOrganizerApp.getApplication().show(allEvents);
	}// GEN-LAST:event_jBAllEventsActionPerformed

	private void jBRegisterSwimmerActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jBRegisterSwimmerActionPerformed
		JFrame mainFrame = SwimRaceOrganizerApp.getApplication().getMainFrame();
		registerSwimmer = new RegisterSwimmerForEvent(mainFrame, true);
		registerSwimmer.setLocationRelativeTo(mainFrame);
		SwimRaceOrganizerApp.getApplication().show(registerSwimmer);
	}// GEN-LAST:event_jBRegisterSwimmerActionPerformed

	private void jBGenerateHeatsActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jBgenerateHeatsActionPerformed
		JFrame mainFrame = SwimRaceOrganizerApp.getApplication().getMainFrame();
		try {
			setPoolType(settingsWindow.getPoolType());
			//setCompetitionName(settingsWindow.getCompetitionName());
			generateHeats = new HeatCreatorGenerator(poolType, competitionName, "Generare Serii");
			generateHeats.setLocationRelativeTo(mainFrame);
			SwimRaceOrganizerApp.getApplication().show(generateHeats);
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "Trebuie completate setarile pt a purcede!", "Atentie!!!", 1);
		}
	}// GEN-LAST:event_jBGenerateHeatsActionPerformed

	private void jBAllRegistrationsActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jBAllRegistrationsActionPerformed
		JFrame mainFrame = SwimRaceOrganizerApp.getApplication().getMainFrame();
		allRegistrations = new AllRegistrations(mainFrame, true);
		allRegistrations.setLocationRelativeTo(mainFrame);
		SwimRaceOrganizerApp.getApplication().show(allRegistrations);
	}// GEN-LAST:event_jBAllRegistrationsActionPerformed

	private void jBStoreResultsActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jBStoreResultsActionPerformed
		try {
			JFrame mainFrame = SwimRaceOrganizerApp.getApplication().getMainFrame();
			setPoolType(settingsWindow.getPoolType());
			//setCompetitionName(settingsWindow.getCompetitionName());
			generateHeatOutputs = new HeatOutputGenerator(poolType, competitionName, "Store Results");
			// generateHeatOutputs.setLocationRelativeTo(mainFrame);
			SwimRaceOrganizerApp.getApplication().show(generateHeatOutputs);
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "Trebuie completate setarile pt a purcede!", "Atentie!!!", 1);
		}
	}// GEN-LAST:event_jBStoreResultsActionPerformed

	private void jBGenerateResultsActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jBGenerateResultsActionPerformed
		try {
			JFrame mainFrame = SwimRaceOrganizerApp.getApplication().getMainFrame();
			setPoolType(settingsWindow.getPoolType());
//			setCompetitionName(settingsWindow.getCompetitionName());
			generateHeatResults = new HeatResultGenerator(poolType, competitionName, "Generate Results");
			generateHeatResults.setLocationRelativeTo(mainFrame);
			SwimRaceOrganizerApp.getApplication().show(generateHeatResults);
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "Trebuie completate setarile pt a purcede!", "Atentie!!!", 1);
		}
	}// GEN-LAST:event_jBGenerateResultsActionPerformed

	public String getPoolType() {
		return poolType;
	}

	public void setPoolType(String poolType) {
		this.poolType = poolType;
	}

	public String getCompetitionName() {
		return competitionName;
	}

	public void setCompetitionName(String competitionName) {
		this.competitionName = competitionName;
	}

}
