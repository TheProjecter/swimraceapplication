/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AllRegistrations.java
 *
 * Created on 29.09.2010, 11:33:24
 */

package displays;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import utils.Constants;
import work.Operations;
import entities.Registration;

/**
 * 
 * @author rsovarsz
 */
public class AllRegistrations extends javax.swing.JDialog {

	private static final long serialVersionUID = 1L;

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jBCancel;
	private javax.swing.JButton jBDelete;
	private javax.swing.JComboBox jCBRegistrationSelection;
	private javax.swing.JLabel jLRegToDelete;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable jTAllRegistrations;
	// End of variables declaration//GEN-END:variables

	private Operations sOps = new Operations();
	private List<Registration> registrations = new ArrayList<Registration>();
	private Map<String, String> dataFile = new Constants().getDataFiles();
	private Map<String, String> pathFile = new Constants().getDataFiles();
	private String osName = new Constants().getOsName();

	/** Creates new form AllRegistrations */
	public AllRegistrations(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
		setTitle("All Registrations");
		setRegistrations(sOps.getAllRegistrations());
		fillAllRegistrations();
		fillRegistrationComboBox();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */

	private void initComponents() {

		jScrollPane1 = new javax.swing.JScrollPane();
		jTAllRegistrations = new javax.swing.JTable();
		jTAllRegistrations.setAutoCreateRowSorter(true);
		jCBRegistrationSelection = new javax.swing.JComboBox();
		jBDelete = new javax.swing.JButton();
		jBCancel = new javax.swing.JButton();
		jLRegToDelete = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setName("AllRegistrations"); // NOI18N
		setResizable(false);
		addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentShown(java.awt.event.ComponentEvent evt) {
				formComponentShown(evt);
			}
		});

		jScrollPane1.setName("jScrollPane1"); // NOI18N

		jTAllRegistrations.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

		},
				new String[] { "Nr.", "Swimmer Name", "BirthYear", "Age Group", "Gender", "Club", "Event Name",
						"Entry Time" }));
		jTAllRegistrations.setName("jTAllRegistrations"); // NOI18N

		jScrollPane1.setViewportView(jTAllRegistrations);

		jCBRegistrationSelection.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2",
				"Item 3", "Item 4" }));
		jCBRegistrationSelection.setName("jCBRegistrationSelection"); // NOI18N

		org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application
				.getInstance(swimraceorganizer.SwimRaceOrganizerApp.class).getContext()
				.getResourceMap(AllRegistrations.class);
		jBDelete.setText(resourceMap.getString("jBDelete.text")); // NOI18N
		jBDelete.setName("jBDelete"); // NOI18N
		jBDelete.setPreferredSize(new java.awt.Dimension(100, 25));
		jBDelete.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				deleteRegistration(evt);
			}
		});

		jBCancel.setText(resourceMap.getString("jBCancel.text")); // NOI18N
		jBCancel.setName("jBCancel"); // NOI18N
		jBCancel.setPreferredSize(new java.awt.Dimension(100, 25));
		jBCancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cancel(evt);
			}
		});

		jLRegToDelete.setName("jLRegToDelete"); // NOI18N

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1000,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGroup(
												layout.createSequentialGroup()
														.addComponent(jCBRegistrationSelection,
																javax.swing.GroupLayout.PREFERRED_SIZE, 800,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGap(18, 18, 18)
														.addComponent(jBDelete, javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(jBCancel, javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addComponent(jLRegToDelete))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(jLRegToDelete)
						.addGap(3, 3, 3)
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jCBRegistrationSelection, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jBDelete, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jBCancel, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void formComponentShown(java.awt.event.ComponentEvent evt) {// GEN-FIRST:event_formComponentShown
		setRegistrations(sOps.getAllRegistrations());
		fillAllRegistrations();
		fillRegistrationComboBox();
	}// GEN-LAST:event_formComponentShown

	private void cancel(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cancel
		dispose();
	}// GEN-LAST:event_cancel

	private void deleteRegistration(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_deleteRegistration
		String[] entry = jCBRegistrationSelection.getSelectedItem().toString().split(",");
		String[] times = entry[2].split(":");
		Registration registration = sOps
				.getRegistration(entry[0].trim(), entry[1].trim(), Integer.parseInt(times[0].trim()),
						Integer.parseInt(times[1].trim()), Integer.parseInt(times[2].trim()));
		setRegistrations(sOps.deleteRegistration(registrations, registration));
		fillAllRegistrations();
		fillRegistrationComboBox();
		String lineToRemove = registration.getSwimmer().getName() + ";" + registration.getSwimmer().getBirthYear()
				+ ";" + registration.getSwimmer().getAgeGroup() + ";" + registration.getSwimmer().getGender() + ";"
				+ registration.getSwimmer().getClub() + ";" + registration.getEvent().getName() + ";"
				+ setZero(registration.getMinutes().toString()) + ";" + setZero(registration.getSeconds().toString())
				+ ";" + setZero(registration.getmSeconds().toString());
		sOps.removeLineFromFile(pathFile.get("core") + (osName.toLowerCase().startsWith("linux") ? "/" : "\\")
				+ dataFile.get("registrations"), lineToRemove);
	}// GEN-LAST:event_deleteRegistration

	public String setZero(String value) {
		return (value.length() < 2) ? "0" + value : value;
	}

	public void fillAllRegistrations() {
		String[] columnNames = { "Nr.", "Swimmer Name", "BirthYear", "Age Group", "Gender", "Club", "Event Name",
				"Entry Time" };
		List<String[]> tableLines = new ArrayList<String[]>();

		int counter = 0; // display registration order number
		for (Registration reg : registrations) {
			String time = padLeft(Integer.toString(reg.getMinutes()), 2) + ":"
					+ padLeft(Integer.toString(reg.getSeconds()), 2) + ":"
					+ padLeft(Integer.toString(reg.getmSeconds()), 2);
			tableLines.add(new String[] { Integer.toString(++counter), reg.getSwimmer().getName(),
					reg.getSwimmer().getBirthYear(), reg.getSwimmer().getAgeGroup(), reg.getSwimmer().getGender(),
					reg.getSwimmer().getClub(), reg.getEvent().getName(), time });
		}
		Object[][] data = new Object[tableLines.size()][5];
		for (int i = 0; i < tableLines.size(); i++) {
			data[i] = tableLines.get(i);
		}
		jTAllRegistrations.setModel(new javax.swing.table.DefaultTableModel(data, columnNames) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		});
		jTAllRegistrations.getColumnModel().getColumn(0).setMinWidth(20);
		jTAllRegistrations.getColumnModel().getColumn(0).setMaxWidth(45);
		jTAllRegistrations.getColumnModel().getColumn(0).setWidth(45);

		jTAllRegistrations.getColumnModel().getColumn(1).setMinWidth(100);
		jTAllRegistrations.getColumnModel().getColumn(1).setMaxWidth(300);
		jTAllRegistrations.getColumnModel().getColumn(1).setWidth(300);

		jTAllRegistrations.getColumnModel().getColumn(2).setMinWidth(30);
		jTAllRegistrations.getColumnModel().getColumn(2).setMaxWidth(70);
		jTAllRegistrations.getColumnModel().getColumn(2).setWidth(70);

		jTAllRegistrations.getColumnModel().getColumn(3).setMinWidth(5);
		jTAllRegistrations.getColumnModel().getColumn(3).setMaxWidth(75);
		jTAllRegistrations.getColumnModel().getColumn(3).setWidth(75);

		jTAllRegistrations.getColumnModel().getColumn(4).setMinWidth(5);
		jTAllRegistrations.getColumnModel().getColumn(4).setMaxWidth(50);
		jTAllRegistrations.getColumnModel().getColumn(4).setWidth(50);

		jTAllRegistrations.getColumnModel().getColumn(5).setMinWidth(50);
		jTAllRegistrations.getColumnModel().getColumn(5).setMaxWidth(200);
		jTAllRegistrations.getColumnModel().getColumn(5).setWidth(200);

		jTAllRegistrations.getColumnModel().getColumn(6).setMinWidth(100);
		jTAllRegistrations.getColumnModel().getColumn(6).setMaxWidth(300);
		jTAllRegistrations.getColumnModel().getColumn(6).setWidth(300);

		jTAllRegistrations.getColumnModel().getColumn(7).setMinWidth(50);
		jTAllRegistrations.getColumnModel().getColumn(7).setMaxWidth(90);
		jTAllRegistrations.getColumnModel().getColumn(7).setWidth(90);

	}

	private static String padLeft(String s, int n) {
		return String.format("%1$#" + n + "s", s).replace(' ', '0');
	}

	private void fillRegistrationComboBox() {
		jCBRegistrationSelection.removeAllItems();
		for (Registration reg : registrations) {
			String time = padLeft(Integer.toString(reg.getMinutes()), 2) + ":"
					+ padLeft(Integer.toString(reg.getSeconds()), 2) + ":"
					+ padLeft(Integer.toString(reg.getmSeconds()), 2);
			jCBRegistrationSelection
					.addItem(reg.getSwimmer().getName() + ", " + reg.getEvent().getName() + ", " + time);
		}
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				AllRegistrations dialog = new AllRegistrations(new javax.swing.JFrame(), true);
				dialog.addWindowListener(new java.awt.event.WindowAdapter() {
					public void windowClosing(java.awt.event.WindowEvent e) {
						System.exit(0);
					}
				});
				dialog.setVisible(true);
			}
		});
	}

	public List<Registration> getRegistrations() {
		return registrations;
	}

	public void setRegistrations(List<Registration> registrations) {
		this.registrations = registrations;
	}
}
