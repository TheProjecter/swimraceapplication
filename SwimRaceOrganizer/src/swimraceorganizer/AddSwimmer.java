/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AddSwimmer.java
 *
 * Created on 23.06.2010, 16:18:53
 */

package swimraceorganizer;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.swing.JOptionPane;

import utils.SwimmerOperations;
import work.Operations;
import customComponents.WarrningDialog;
import entities.Swimmer;

/**
 * 
 * @author rsovarsz
 */
public class AddSwimmer extends javax.swing.JDialog {

	private SwimmerOperations swOp = new SwimmerOperations();
	private Operations sOp = new Operations();

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jBCancel;
	private javax.swing.JButton jBRegisterSwimmer;
	private javax.swing.JComboBox jCBBirthYear;
	private javax.swing.JComboBox jCBGender;
	private javax.swing.JLabel jLAgeGroup;
	private javax.swing.JLabel jLBirthyear;
	private javax.swing.JLabel jLClub;
	private javax.swing.JLabel jLGender;
	private javax.swing.JLabel jLStatus;
	private javax.swing.JLabel jLSwimmerName;
	private javax.swing.JSeparator jSeparatorStatus;
	private javax.swing.JTextField jTAgeGroup;
	private javax.swing.JTextField jTClub;
	private javax.swing.JTextField jTSwimmerName;
	// End of variables declaration//GEN-END:variables

	private WarrningDialog warrningBox;

	/** Creates new form AddSwimmer */
	public AddSwimmer() {
		initComponents();
		fillBirthYearCB();
		fillGenderCB();
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jTSwimmerName = new javax.swing.JTextField();
		jTClub = new javax.swing.JTextField();
		jCBBirthYear = new javax.swing.JComboBox();
		jCBGender = new javax.swing.JComboBox();
		jBRegisterSwimmer = new javax.swing.JButton();
		jBCancel = new javax.swing.JButton();
		jTAgeGroup = new javax.swing.JTextField();
		jLSwimmerName = new javax.swing.JLabel();
		jLGender = new javax.swing.JLabel();
		jLBirthyear = new javax.swing.JLabel();
		jLAgeGroup = new javax.swing.JLabel();
		jLClub = new javax.swing.JLabel();
		jSeparatorStatus = new javax.swing.JSeparator();
		jLStatus = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application
				.getInstance(swimraceorganizer.SwimRaceOrganizerApp.class)
				.getContext().getResourceMap(AddSwimmer.class);
		setTitle(resourceMap.getString("Form.title")); // NOI18N
		setName("Form"); // NOI18N
		setResizable(false);

		jTSwimmerName.setText(resourceMap.getString("jTSwimmerName.text")); // NOI18N
		jTSwimmerName.setName("jTSwimmerName"); // NOI18N

		jTClub.setText(resourceMap.getString("jTClub.text")); // NOI18N
		jTClub.setName("jTClub"); // NOI18N

		jCBBirthYear.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jCBBirthYearActionPerformed(evt);
			}
		});

		jBRegisterSwimmer
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						registerSwimmer(evt);
					}
				});
		jBRegisterSwimmer.setName("jRegisterSwimmer"); // NOI18N
		jBRegisterSwimmer.setText("Register");

		jBCancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cancel(evt);
			}
		});
		jBCancel.setName("jBCancel"); // NOI18N
		jBCancel.setText("Cancel"); // NOI18N

		jTAgeGroup.setEditable(false);
		jTAgeGroup.setText(resourceMap.getString("jTAgeGroup.text")); // NOI18N
		jTAgeGroup.setName("jTAgeGroup"); // NOI18N
		jTAgeGroup.setBorder(null);

		jLSwimmerName.setText(resourceMap.getString("jLSwimmerName.text")); // NOI18N
		jLSwimmerName.setName("jLSwimmerName"); // NOI18N

		jLGender.setText(resourceMap.getString("jLGender.text")); // NOI18N
		jLGender.setName("jLGender"); // NOI18N

		jLBirthyear.setText(resourceMap.getString("jLBirthyear.text")); // NOI18N
		jLBirthyear.setName("jLBirthyear"); // NOI18N

		jLAgeGroup.setText(resourceMap.getString("jLAgeGroup.text")); // NOI18N
		jLAgeGroup.setName("jLAgeGroup"); // NOI18N

		jLClub.setText(resourceMap.getString("jLClub.text")); // NOI18N
		jLClub.setName("jLClub"); // NOI18N

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
												.addComponent(
														jSeparatorStatus,
														javax.swing.GroupLayout.Alignment.TRAILING,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														261, Short.MAX_VALUE)
												.addComponent(jLClub)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		jBRegisterSwimmer)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																		123,
																		Short.MAX_VALUE)
																.addComponent(
																		jBCancel))
												.addComponent(
														jTClub,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														261, Short.MAX_VALUE)
												.addGroup(
														javax.swing.GroupLayout.Alignment.TRAILING,
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(
																						jLSwimmerName)
																				.addComponent(
																						jTSwimmerName,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						187,
																						Short.MAX_VALUE))
																.addGap(18, 18,
																		18)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.TRAILING)
																				.addComponent(
																						jLGender)
																				.addComponent(
																						jCBGender,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE)))
												.addGroup(
														javax.swing.GroupLayout.Alignment.TRAILING,
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(
																						jLBirthyear)
																				.addComponent(
																						jCBBirthYear,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						80,
																						javax.swing.GroupLayout.PREFERRED_SIZE))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																		131,
																		Short.MAX_VALUE)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
																				false)
																				.addComponent(
																						jTAgeGroup)
																				.addComponent(
																						jLAgeGroup,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)))
												.addComponent(jLStatus))
								.addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.TRAILING,
												false)
												.addGroup(
														javax.swing.GroupLayout.Alignment.LEADING,
														layout.createSequentialGroup()
																.addComponent(
																		jLGender)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		jCBGender,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		jLAgeGroup))
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		jLSwimmerName)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		jTSwimmerName,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addComponent(
																		jLBirthyear)))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														jCBBirthYear,
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
								.addComponent(jLClub)
								.addGap(9, 9, 9)
								.addComponent(jTClub,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jBRegisterSwimmer)
												.addComponent(jBCancel))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jSeparatorStatus,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										10,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE).addComponent(jLStatus)
								.addContainerGap()));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void registerSwimmer(ActionEvent evt) {
		Boolean register = true;
		if (jTSwimmerName.getText().isEmpty()) {
			register = false;
			JOptionPane.showMessageDialog(null,
					"Swimmer Name can not be empty!", "Warrning!!!", 1);
		}
		if (jTClub.getText().isEmpty()) {
			register = false;
			JOptionPane.showMessageDialog(null, "Club Name can not be empty!",
					"Warrning!!!", 1);
		}
		if (register) {
			Swimmer swimmer = new Swimmer(jTSwimmerName.getText(), jCBBirthYear
					.getSelectedItem().toString(), jTAgeGroup.getText(),
					jCBGender.getSelectedItem().toString(), jTClub.getText());
			// check that the swimmer has not been registered already
			if (sOp.existsSwimmer(swimmer.getName())) {
				JOptionPane.showMessageDialog(null, "Swimmer already exists!",
						"Warrning!!!", 1);
			} else {
				try {
					swOp.registerSwimmer(swimmer);
					jLStatus.setText("Adaugat inotatorul, " + swimmer.getName());
				} catch (IOException e) {
					jLStatus.setText("Eroare la adaugarea inotatorului, "
							+ swimmer.getName());
				}
			}
		}
	}

	private void cancel(ActionEvent evt) {
		dispose();
	}

	private void jCBBirthYearActionPerformed(ActionEvent evt) {
		int birthYear = Integer.parseInt((String) jCBBirthYear
				.getSelectedItem());
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		jTAgeGroup.setText(swOp.ageGroupCalculation(year - birthYear));
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				AddSwimmer dialog = new AddSwimmer();
				dialog.addWindowListener(new java.awt.event.WindowAdapter() {
					public void windowClosing(java.awt.event.WindowEvent e) {
						System.exit(0);
					}
				});
				dialog.setVisible(true);
			}
		});
	}

	public void fillBirthYearCB() {
		List<String> birthYears = swOp.getBirthYears();
		jCBBirthYear.removeAllItems();
		for (String birth : birthYears) {
			jCBBirthYear.addItem(birth);
		}
	}

	public void fillGenderCB() {
		jCBGender.removeAllItems();
		jCBGender.addItem("M".toString());
		jCBGender.addItem("F".toString());
	}
}
