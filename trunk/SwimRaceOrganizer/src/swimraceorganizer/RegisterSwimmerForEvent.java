/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RegisterSwimmerForEvent.java
 *
 * Created on 24.09.2010, 16:45:17
 */

package swimraceorganizer;

import java.io.IOException;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import customComponents.WarrningDialog;

import entities.Event;
import entities.Registration;
import entities.Swimmer;

import utils.EventOperations;
import utils.SwimmerOperations;
import work.Operations;

/**
 * 
 * @author rsovarsz
 */
public class RegisterSwimmerForEvent extends javax.swing.JDialog {

	private SwimmerOperations swOperation = new SwimmerOperations();
	private EventOperations evOperations = new EventOperations();
	private Operations operations = new Operations();
	private WarrningDialog warrningBox;

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jBCancel;
	private javax.swing.JButton jBRegister;
	private javax.swing.JComboBox jCBEventName;
	private javax.swing.JComboBox jCBMSeconds;
	private javax.swing.JComboBox jCBMinutes;
	private javax.swing.JComboBox jCBSeconds;
	private javax.swing.JComboBox jCBSwimmerName;
	private javax.swing.JLabel jLEventName;
	private javax.swing.JLabel jLMSeconds;
	private javax.swing.JLabel jLMinutes;
	private javax.swing.JLabel jLSeconds;
	private javax.swing.JLabel jLStatus;
	private javax.swing.JLabel jLSwimmerName;
	private javax.swing.JSeparator jSeparatorStatus;
	private javax.swing.JTextField jTAgeGroup;
	private javax.swing.JTextField jTClubName;
	private javax.swing.JTextField jTGender;
	private javax.swing.JTextField jTGenderEvent;

	// End of variables declaration//GEN-END:variables

	/** Creates new form RegisterSwimmerForEvent */
	public RegisterSwimmerForEvent(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
		fillSwimmerNames();
		fillEventNames();
		fillTimeTables();
		jTGenderEvent.setVisible(false);
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

		jCBSwimmerName = new javax.swing.JComboBox();
		jTClubName = new javax.swing.JTextField();
		jTAgeGroup = new javax.swing.JTextField();
		jTGender = new javax.swing.JTextField();
		jCBMinutes = new javax.swing.JComboBox();
		jCBSeconds = new javax.swing.JComboBox();
		jCBMSeconds = new javax.swing.JComboBox();
		jBRegister = new javax.swing.JButton();
		jBCancel = new javax.swing.JButton();
		jCBEventName = new javax.swing.JComboBox();
		jLSwimmerName = new javax.swing.JLabel();
		jLEventName = new javax.swing.JLabel();
		jLMinutes = new javax.swing.JLabel();
		jLSeconds = new javax.swing.JLabel();
		jLMSeconds = new javax.swing.JLabel();
		jTGenderEvent = new javax.swing.JTextField();
		jSeparatorStatus = new javax.swing.JSeparator();
		jLStatus = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application
				.getInstance(swimraceorganizer.SwimRaceOrganizerApp.class)
				.getContext().getResourceMap(RegisterSwimmerForEvent.class);
		setTitle(resourceMap.getString("AddRegistrationsToEvent.title")); // NOI18N
		setName("AddRegistrationsToEvent"); // NOI18N
		setResizable(false);
		addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentShown(java.awt.event.ComponentEvent evt) {
				formComponentShown(evt);
			}
		});

		jCBSwimmerName.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
		jCBSwimmerName.setName("jCBSwimmerName"); // NOI18N
		jCBSwimmerName.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jCBSwimmerNameActionPerformed(evt);
			}
		});

		jTClubName.setEditable(false);
		jTClubName.setText(resourceMap.getString("jTClubName.text")); // NOI18N
		jTClubName.setBorder(null);
		jTClubName.setName("jTClubName"); // NOI18N

		jTAgeGroup.setEditable(false);
		jTAgeGroup.setText(resourceMap.getString("jTAgeGroup.text")); // NOI18N
		jTAgeGroup.setBorder(null);
		jTAgeGroup.setName("jTAgeGroup"); // NOI18N

		jTGender.setEditable(false);
		jTGender.setText(resourceMap.getString("jTGender.text")); // NOI18N
		jTGender.setBorder(null);
		jTGender.setName("jTGender"); // NOI18N

		jCBMinutes.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"Item 1", "Item 2", "Item 3", "Item 4" }));
		jCBMinutes.setName("jCBMinutes"); // NOI18N

		jCBSeconds.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"Item 1", "Item 2", "Item 3", "Item 4" }));
		jCBSeconds.setName("jCBSeconds"); // NOI18N

		jCBMSeconds.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"Item 1", "Item 2", "Item 3", "Item 4" }));
		jCBMSeconds.setName("jCBMSeconds"); // NOI18N

		jBRegister.setText(resourceMap.getString("jBRegister.text")); // NOI18N
		jBRegister.setName("jBRegister"); // NOI18N
		jBRegister.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				registerSwimmerToEvent(evt);
			}
		});

		jBCancel.setText(resourceMap.getString("jBCancel.text")); // NOI18N
		jBCancel.setName("jBCancel"); // NOI18N
		jBCancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cancel(evt);
			}
		});

		jCBEventName.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
		jCBEventName.setName("jCBEventName"); // NOI18N

		jLSwimmerName.setText(resourceMap.getString("jLSwimmerName.text")); // NOI18N
		jLSwimmerName.setName("jLSwimmerName"); // NOI18N

		jLEventName.setText(resourceMap.getString("jLEventName.text")); // NOI18N
		jLEventName.setName("jLEventName"); // NOI18N

		jLMinutes.setText(resourceMap.getString("jLMinutes.text")); // NOI18N
		jLMinutes.setName("jLMinutes"); // NOI18N

		jLSeconds.setText(resourceMap.getString("jLSeconds.text")); // NOI18N
		jLSeconds.setName("jLSeconds"); // NOI18N

		jLMSeconds.setText(resourceMap.getString("jLMSeconds.text")); // NOI18N
		jLMSeconds.setName("jLMSeconds"); // NOI18N

		jTGenderEvent.setEditable(false);
		jTGenderEvent.setText(resourceMap.getString("jTGenderEvent.text")); // NOI18N
		jTGenderEvent.setBorder(null);
		jTGenderEvent.setName("jTGenderEvent"); // NOI18N

		jSeparatorStatus.setName("jSeparatorStatus"); // NOI18N

		jLStatus.setFont(resourceMap.getFont("jLStatus.font")); // NOI18N
		jLStatus.setText(resourceMap.getString("jLStatus.text")); // NOI18N
		jLStatus.setName("jLStatus"); // NOI18N

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jLSwimmerName)
												.addGroup(
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(
																						jLEventName)
																				.addComponent(
																						jBRegister)
																				.addGroup(
																						layout.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING,
																								false)
																								.addComponent(
																										jCBSwimmerName,
																										javax.swing.GroupLayout.Alignment.LEADING,
																										0,
																										javax.swing.GroupLayout.DEFAULT_SIZE,
																										Short.MAX_VALUE)
																								.addComponent(
																										jCBEventName,
																										javax.swing.GroupLayout.Alignment.LEADING,
																										0,
																										javax.swing.GroupLayout.DEFAULT_SIZE,
																										Short.MAX_VALUE)
																								.addGroup(
																										javax.swing.GroupLayout.Alignment.LEADING,
																										layout.createSequentialGroup()
																												.addGroup(
																														layout.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING)
																																.addComponent(
																																		jLMinutes)
																																.addComponent(
																																		jCBMinutes,
																																		javax.swing.GroupLayout.PREFERRED_SIZE,
																																		56,
																																		javax.swing.GroupLayout.PREFERRED_SIZE))
																												.addPreferredGap(
																														javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																												.addGroup(
																														layout.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING)
																																.addComponent(
																																		jLSeconds)
																																.addComponent(
																																		jCBSeconds,
																																		javax.swing.GroupLayout.PREFERRED_SIZE,
																																		javax.swing.GroupLayout.DEFAULT_SIZE,
																																		javax.swing.GroupLayout.PREFERRED_SIZE))
																												.addGap(14,
																														14,
																														14)
																												.addGroup(
																														layout.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING)
																																.addComponent(
																																		jLMSeconds)
																																.addComponent(
																																		jCBMSeconds,
																																		javax.swing.GroupLayout.PREFERRED_SIZE,
																																		javax.swing.GroupLayout.DEFAULT_SIZE,
																																		javax.swing.GroupLayout.PREFERRED_SIZE)))))
																.addGap(53, 53,
																		53)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.TRAILING)
																				.addGroup(
																						layout.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING,
																								false)
																								.addComponent(
																										jTClubName,
																										javax.swing.GroupLayout.PREFERRED_SIZE,
																										104,
																										javax.swing.GroupLayout.PREFERRED_SIZE)
																								.addGroup(
																										layout.createSequentialGroup()
																												.addComponent(
																														jTAgeGroup,
																														0,
																														0,
																														Short.MAX_VALUE)
																												.addPreferredGap(
																														javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																												.addGroup(
																														layout.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING,
																																false)
																																.addComponent(
																																		jTGenderEvent,
																																		javax.swing.GroupLayout.Alignment.TRAILING)
																																.addComponent(
																																		jTGender,
																																		javax.swing.GroupLayout.Alignment.TRAILING,
																																		javax.swing.GroupLayout.DEFAULT_SIZE,
																																		41,
																																		Short.MAX_VALUE))))
																				.addComponent(
																						jBCancel)))
												.addComponent(
														jSeparatorStatus,
														javax.swing.GroupLayout.Alignment.TRAILING,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														349, Short.MAX_VALUE)
												.addComponent(jLStatus))
								.addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(jLSwimmerName)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		jTClubName,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(11, 11,
																		11)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(
																						jTGender,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						jTAgeGroup,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																.addComponent(
																		jTGenderEvent,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																		69,
																		Short.MAX_VALUE)
																.addComponent(
																		jBCancel)
																.addGap(1, 1, 1))
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		jCBSwimmerName,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		jLEventName)
																.addGap(10, 10,
																		10)
																.addComponent(
																		jCBEventName,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(
																						jLMinutes)
																				.addComponent(
																						jLSeconds)
																				.addComponent(
																						jLMSeconds))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(
																						jCBMinutes,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						jCBMSeconds,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						jCBSeconds,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE))
																.addGap(18, 18,
																		18)
																.addComponent(
																		jBRegister)))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jSeparatorStatus,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										10,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jLStatus).addContainerGap()));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void jCBSwimmerNameActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jCBSwimmerNameActionPerformed
		try {
			Swimmer swimmer = operations.returnSwimmer((String) jCBSwimmerName
					.getSelectedItem());
			jTClubName.setText(swimmer.getClub());
			jTAgeGroup.setText(swimmer.getAgeGroup());
			jTGender.setText((swimmer.getGender().equals("M")) ? "Male"
					: "Female");
		} catch (Exception e) {
		}
	}// GEN-LAST:event_jCBSwimmerNameActionPerformed

	private void cancel(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cancel
		dispose();
	}// GEN-LAST:event_cancel

	private void formComponentShown(java.awt.event.ComponentEvent evt) {// GEN-FIRST:event_formComponentShown
		fillSwimmerNames();
		fillEventNames();
		fillTimeTables();
	}// GEN-LAST:event_formComponentShown

	private void registerSwimmerToEvent(java.awt.event.ActionEvent evt) {
		if (operations.isSwimmerRegisteredForEvent(operations
				.returnSwimmer(jCBSwimmerName.getSelectedItem().toString()),
				operations.returnEvent(jCBEventName.getSelectedItem()
						.toString()))) {
			JOptionPane.showMessageDialog(
					null,
					"Inotatorul a fost deja inregistrat la proba "
							+ operations.returnEvent(jCBEventName
									.getSelectedItem().toString()).getName(),
					"Warrning!!!", 1);
			return;
		}
		Registration registration = new Registration(
				operations.returnSwimmer(jCBSwimmerName.getSelectedItem()
						.toString()), operations.returnEvent(jCBEventName
						.getSelectedItem().toString()),
				Integer.parseInt(jCBMinutes.getSelectedItem().toString()),
				Integer.parseInt(jCBSeconds.getSelectedItem().toString()),
				Integer.parseInt(jCBMSeconds.getSelectedItem().toString()));
		try {
			operations.registerRegistration(registration);
			jLStatus.setText("Inregistrat, "
					+ registration.getSwimmer().getName() + " : "
					+ registration.getEvent().getName());
		} catch (IOException e) {
			jLStatus.setText("Probleme cu inregistrarea, "
					+ registration.getSwimmer().getName() + " : "
					+ registration.getEvent().getName());
		}
	}// GEN-LAST:event_registerSwimmerToEvent

	public void fillSwimmerNames() {
		List<String> swimmerNames = swOperation.getSwimmerNames();
		jCBSwimmerName.removeAllItems();
		for (String swName : swimmerNames) {
			jCBSwimmerName.addItem(swName);
		}
	}

	public void fillEventNames() {
		List<String> eventNames = evOperations.getEventNames();
		jCBEventName.removeAllItems();
		for (String evName : eventNames) {
			jCBEventName.addItem(evName);
		}
	}

	public void fillTimeTables() {
		List<String> minutes = evOperations.getMinutes();
		List<String> seconds = evOperations.getSeconds();
		List<String> mSeconde = evOperations.getmSecondes();
		jCBMinutes.removeAllItems();
		jCBSeconds.removeAllItems();
		jCBMSeconds.removeAllItems();
		for (String m : minutes) {
			jCBMinutes.addItem(m);
		}
		for (String m : seconds) {
			jCBSeconds.addItem(m);
		}
		for (String m : mSeconde) {
			jCBMSeconds.addItem(m);
		}
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				RegisterSwimmerForEvent dialog = new RegisterSwimmerForEvent(
						new javax.swing.JFrame(), true);
				dialog.addWindowListener(new java.awt.event.WindowAdapter() {
					public void windowClosing(java.awt.event.WindowEvent e) {
						System.exit(0);
					}
				});
				dialog.setVisible(true);
			}
		});
	}

}
