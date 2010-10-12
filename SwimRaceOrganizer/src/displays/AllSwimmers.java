/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AllSwimmers.java
 *
 * Created on 23.09.2010, 16:58:15
 */

package displays;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import swimraceorganizer.SwimRaceOrganizerApp;
import utils.Constants;
import work.Operations;
import entities.Event;
import entities.Swimmer;

/**
 * 
 * @author rsovarsz
 */
public class AllSwimmers extends javax.swing.JDialog {

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBCancel;
    private javax.swing.JButton jBDelete;
    private javax.swing.JComboBox jCBSwimmerSelection;
    private javax.swing.JLabel jLSwimmerToDelete;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTAllSwimmers;
    // End of variables declaration//GEN-END:variables

	private Operations sOps = new Operations();
	private List<Swimmer> swimmers = sOps.returnAllSwimmers();
	private Map<String, String> dataFile = new Constants().getDataFiles();

	/** Creates new form AllSwimmers */
	public AllSwimmers(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
		setSwimmers(sOps.returnAllSwimmers());
		fillAllSwimmers();
		fillSwimmersComboBox();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTAllSwimmers = new javax.swing.JTable();
        jLSwimmerToDelete = new javax.swing.JLabel();
        jCBSwimmerSelection = new javax.swing.JComboBox();
        jBDelete = new javax.swing.JButton();
        jBCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("AllSwimmers"); // NOI18N
        setResizable(false);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTAllSwimmers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Swimmer Name", "Birth Year", "Age Group", "Gender", "Club"
            }
        ));
        jTAllSwimmers.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTAllSwimmers.setEditingRow(0);
        jTAllSwimmers.setName("jTAllSwimmers"); // NOI18N
        jScrollPane1.setViewportView(jTAllSwimmers);

        jLSwimmerToDelete.setName("jLSwimmerToDelete"); // NOI18N

        jCBSwimmerSelection.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jCBSwimmerSelection.setName("jCBSwimmerSelection"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(swimraceorganizer.SwimRaceOrganizerApp.class).getContext().getResourceMap(AllSwimmers.class);
        jBDelete.setText(resourceMap.getString("jBDelete.text")); // NOI18N
        jBDelete.setName("jBDelete"); // NOI18N
        jBDelete.setPreferredSize(new java.awt.Dimension(100, 25));
        jBDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteSwimmer(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jCBSwimmerSelection, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(113, 113, 113)
                        .addComponent(jBDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLSwimmerToDelete, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLSwimmerToDelete)
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBSwimmerSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void formComponentShown(java.awt.event.ComponentEvent evt) {// GEN-FIRST:event_formComponentShown
		setSwimmers(sOps.returnAllSwimmers());
		fillAllSwimmers();
		fillSwimmersComboBox();
	}// GEN-LAST:event_formComponentShown

	private void deleteSwimmer(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jBDeletedeleteSwimmer
		Swimmer swimmer = sOps.returnSwimmer(jCBSwimmerSelection
				.getSelectedItem().toString());
		sOps.deleteRegistrationForSwimmer(swimmer);
		setSwimmers(sOps.deleteSwimmers(swimmers, swimmer));
		fillAllSwimmers();
		fillSwimmersComboBox();
		String lineToRemove = swimmer.getName() + ";" + swimmer.getBirthYear()
				+ ";" + swimmer.getAgeGroup() + ";" + swimmer.getGender() + ";"
				+ swimmer.getClub();
		sOps.removeLineFromFile(dataFile.get("swimmers"), lineToRemove);
	}// GEN-LAST:event_jBDeletedeleteSwimmer

	private void cancel(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jBCancelcancel
		dispose();
	}// GEN-LAST:event_jBCancelcancel

	public void fillAllSwimmers() {
		String[] columnNames = { "Swimmer Name", "Birth year", "Age Group",
				"Gender", "Club" };
		List<String[]> tableLines = new ArrayList<String[]>();
		for (Swimmer sw : swimmers) {
			tableLines.add(new String[] { sw.getName(), sw.getBirthYear(),
					sw.getAgeGroup(), sw.getGender(), sw.getClub() });
		}
		Object[][] data = new Object[tableLines.size()][5];
		for (int i = 0; i < tableLines.size(); i++) {
			data[i] = tableLines.get(i);
		}
		jTAllSwimmers.setModel(new javax.swing.table.DefaultTableModel(data,
				columnNames) {
			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		});
	}

	private void fillSwimmersComboBox() {
		jCBSwimmerSelection.removeAllItems();
		for (Swimmer swimmer : swimmers) {
			jCBSwimmerSelection.addItem(swimmer.getName());
		}
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				AllSwimmers dialog = new AllSwimmers(new javax.swing.JFrame(),
						true);
				dialog.addWindowListener(new java.awt.event.WindowAdapter() {
					public void windowClosing(java.awt.event.WindowEvent e) {
						System.exit(0);
					}
				});
				dialog.setVisible(true);
			}
		});
	}

	public List<Swimmer> getSwimmers() {
		return swimmers;
	}

	public void setSwimmers(List<Swimmer> swimmers) {
		this.swimmers = swimmers;
	}

}
