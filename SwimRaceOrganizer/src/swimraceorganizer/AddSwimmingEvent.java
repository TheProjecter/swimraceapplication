/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AddSwimmingEvent.java
 *
 * Created on 23.09.2010, 14:30:54
 */

package swimraceorganizer;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

import utils.Constants;
import utils.EventOperations;
import entities.Event;

/**
 * 
 * @author rsovarsz
 */
public class AddSwimmingEvent extends javax.swing.JDialog {

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jBCancel;
	private javax.swing.JButton jBRegister;
	private javax.swing.JComboBox jCBDistance;
	private javax.swing.JComboBox jCBGender;
	private javax.swing.JComboBox jCBPoolType;
	private javax.swing.JComboBox jCBStyle;
	private javax.swing.JLabel jLDistance;
	private javax.swing.JLabel jLEventName;
	private javax.swing.JLabel jLGender;
	private javax.swing.JLabel jLStatus;
	private javax.swing.JLabel jLStyle;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JSeparator jSeparatorStatus;
	private javax.swing.JTextField jTEventName;
	// End of variables declaration//GEN-END:variables

	private String poolType;
	private Map<String, String> styleType = new Constants().getStyleNames();
	private EventOperations evOp = new EventOperations();

	/** Creates new form AddSwimmingEvent */
	public AddSwimmingEvent(String poolType) {
		super();
		setPoolType(poolType);
		initComponents();
		fillGenderCB();
		fillDistanceCB();
		fillStyleCB();
		fillPoolTypeCB();
		fillEventName();
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

		jTEventName = new javax.swing.JTextField();
		jCBDistance = new javax.swing.JComboBox();
		jCBStyle = new javax.swing.JComboBox();
		jCBGender = new javax.swing.JComboBox();
		jCBPoolType = new javax.swing.JComboBox();
		jLEventName = new javax.swing.JLabel();
		jLGender = new javax.swing.JLabel();
		jLDistance = new javax.swing.JLabel();
		jLStyle = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		jBRegister = new javax.swing.JButton();
		jBCancel = new javax.swing.JButton();
		jSeparatorStatus = new javax.swing.JSeparator();
		jLStatus = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application
				.getInstance(swimraceorganizer.SwimRaceOrganizerApp.class)
				.getContext().getResourceMap(AddSwimmingEvent.class);
		setTitle(resourceMap.getString("Form.title")); // NOI18N
		setName("Form"); // NOI18N
		setResizable(false);
		
		jTEventName.setText(resourceMap.getString("jTEventName.text")); // NOI18N
		jTEventName.setName("jTEventName"); // NOI18N
		jTEventName.setFont(new Font(jLEventName.getFont().getName(),
				Font.BOLD, jLEventName.getFont().getSize()));
		jTEventName.setEditable(false);
		jTEventName.setBorder(BorderFactory.createEmptyBorder());

		jLEventName.setText(resourceMap.getString("jLEventName.text")); // NOI18N
		jLEventName.setName("jLEventName"); // NOI18N

		jLGender.setText(resourceMap.getString("jLGender.text")); // NOI18N
		jLGender.setName("jLGender"); // NOI18N

		jLDistance.setText(resourceMap.getString("jLDistance.text")); // NOI18N
		jLDistance.setName("jLDistance"); // NOI18N

		jLStyle.setText(resourceMap.getString("jLStyle.text")); // NOI18N
		jLStyle.setName("jLStyle"); // NOI18N

		jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
		jLabel5.setName("jLabel5"); // NOI18N

		jBRegister.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				registerSwimmingEvent(evt);
			}
		});
		jBRegister.setText(resourceMap.getString("jBRegister.text")); // NOI18N
		jBRegister.setName("jBRegister"); // NOI18N

		jBCancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cancel(evt);
			}
		});
		jBCancel.setText(resourceMap.getString("jBCancel.text")); // NOI18N
		jBCancel.setName("jBCancel"); // NOI18N

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
												javax.swing.GroupLayout.Alignment.TRAILING)
												.addComponent(
														jSeparatorStatus,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														363, Short.MAX_VALUE)
												.addGroup(
														javax.swing.GroupLayout.Alignment.LEADING,
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(
																						jLEventName)
																				.addComponent(
																						jBRegister)
																				.addGroup(
																						javax.swing.GroupLayout.Alignment.TRAILING,
																						layout.createSequentialGroup()
																								.addGroup(
																										layout.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.TRAILING)
																												.addComponent(
																														jTEventName,
																														javax.swing.GroupLayout.Alignment.LEADING,
																														javax.swing.GroupLayout.DEFAULT_SIZE,
																														260,
																														Short.MAX_VALUE)
																												.addGroup(
																														layout.createSequentialGroup()
																																.addComponent(
																																		jLDistance)
																																.addPreferredGap(
																																		javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																																		195,
																																		Short.MAX_VALUE)
																																.addComponent(
																																		jLStyle)))
																								.addGap(24,
																										24,
																										24)))
																.addGap(14, 14,
																		14)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.TRAILING)
																				.addComponent(
																						jCBGender,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						jLGender)
																				.addComponent(
																						jLabel5)
																				.addComponent(
																						jBCancel)))
												.addComponent(
														jLStatus,
														javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		jCBDistance,
																		0,
																		100,
																		Short.MAX_VALUE)
																.addGap(18, 18,
																		18)
																.addComponent(
																		jCBStyle,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		144,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																.addComponent(
																		jCBPoolType,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		91,
																		javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLEventName)
												.addComponent(jLGender))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														jTEventName,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														jCBGender,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(5, 5, 5)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLDistance)
												.addComponent(jLabel5)
												.addComponent(jLStyle))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														jCBDistance,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														jCBStyle,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														jCBPoolType,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jBRegister)
												.addComponent(jBCancel))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(jSeparatorStatus,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										10,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jLStatus)
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));

		jCBDistance.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					fillEventName();
				} catch (NullPointerException e) {
				}
			}
		});
		jCBStyle.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					fillEventName();
				} catch (NullPointerException e) {
				}
			}
		});
		jCBGender.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					fillEventName();
				} catch (NullPointerException e) {
				}
			}
		});

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void cancel(ActionEvent evt) {
		dispose();
	}

	private void registerSwimmingEvent(ActionEvent evt) {
		if (jTEventName.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Event Name can not be empty!",
					"Warrning!!!", 1);
		} else {
			Event event = new Event(jTEventName.getText(), jCBDistance
					.getSelectedItem().toString(), jCBStyle.getSelectedItem()
					.toString(), jCBGender.getSelectedItem().toString(),
					jCBPoolType.getSelectedItem().toString());
			try {
				evOp.registerEvent(event);
				jLStatus.setText("S-a inregistrat proba, " + event.getName());
			} catch (IOException e) {
				jLStatus.setText("Nu s-a putut inregistra proba, "
						+ event.getName());
			}
		}
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				AddSwimmingEvent dialog = new AddSwimmingEvent(new String());
				dialog.addWindowListener(new java.awt.event.WindowAdapter() {
					public void windowClosing(java.awt.event.WindowEvent e) {
						System.exit(0);
					}
				});
				dialog.setVisible(true);
			}
		});
	}

	public void fillGenderCB() {
		jCBGender.removeAllItems();
		jCBGender.addItem("M".toString());
		jCBGender.addItem("F".toString());
	}

	public void fillDistanceCB() {
		jCBDistance.removeAllItems();
		jCBDistance.addItem("25 Meters".toString());
		jCBDistance.addItem("50 Meters".toString());
		jCBDistance.addItem("100 Meters".toString());
		jCBDistance.addItem("200 Meters".toString());
		jCBDistance.addItem("400 Meters".toString());
		jCBDistance.addItem("800 Meters".toString());
		jCBDistance.addItem("1500 Meters".toString());
	}

	public void fillStyleCB() {
		jCBStyle.removeAllItems();
		jCBStyle.addItem(styleType.get("FREE").toString());
		jCBStyle.addItem(styleType.get("BACK").toString());
		jCBStyle.addItem(styleType.get("BREAST").toString());
		jCBStyle.addItem(styleType.get("FLY").toString());
		jCBStyle.addItem(styleType.get("MEDLEY").toString());
	}

	public void fillPoolTypeCB() {
		jCBPoolType.removeAllItems();
		jCBPoolType.addItem(getPoolType());
		jCBPoolType.setEditable(false);
	}

	public String getPoolType() {
		return poolType;
	}

	public void setPoolType(String poolType) {
		this.poolType = poolType;
	}

	private void fillEventName() {
		jTEventName.setText("Proba " + (evOp.getNumberOfEvent() + 1) + " : "
				+ jCBDistance.getSelectedItem().toString() + " "
				+ jCBStyle.getSelectedItem().toString() + " "
				+ jCBGender.getSelectedItem().toString());
	}
}
