/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AllEvents.java
 *
 * Created on 24.09.2010, 15:14:50
 */

package displays;

import entities.Event;
import entities.Registration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import swimraceorganizer.SwimRaceOrganizerApp;
import utils.Constants;
import work.Operations;
import entities.Swimmer;

/**
 * 
 * @author rsovarsz
 */
public class AllEvents extends javax.swing.JDialog {

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBCancel;
    private javax.swing.JButton jBDelete;
    private javax.swing.JComboBox jCBEventSelection;
    private javax.swing.JLabel jLEventToDelete;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTAllEvents;
    // End of variables declaration//GEN-END:variables

	private Operations sOps = new Operations();
	private List<Event> events = new ArrayList<Event>();
	private Map<String, String> dataFile = new Constants().getDataFiles();

	/** Creates new form AllEvents */
	public AllEvents(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
		setTitle("All Events");
		setEvents(sOps.returnAllEvents());
		fillAllEvents();
		fillEventComboBox();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTAllEvents = new javax.swing.JTable();
        jBCancel = new javax.swing.JButton();
        jBDelete = new javax.swing.JButton();
        jCBEventSelection = new javax.swing.JComboBox();
        jLEventToDelete = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("AllEvents"); // NOI18N
        setResizable(false);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTAllEvents.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Event Name", "Distance", "Stype", "Gender", "Pool Type"
            }
        ));
        jTAllEvents.setName("jTAllEvents"); // NOI18N
        jScrollPane1.setViewportView(jTAllEvents);

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(swimraceorganizer.SwimRaceOrganizerApp.class).getContext().getResourceMap(AllEvents.class);
        jBCancel.setText(resourceMap.getString("jBCancel.text")); // NOI18N
        jBCancel.setName("jBCancel"); // NOI18N
        jBCancel.setPreferredSize(new java.awt.Dimension(100, 25));
        jBCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancel(evt);
            }
        });

        jBDelete.setText(resourceMap.getString("jBDelete.text")); // NOI18N
        jBDelete.setName("jBDelete"); // NOI18N
        jBDelete.setPreferredSize(new java.awt.Dimension(100, 25));
        jBDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteEvent(evt);
            }
        });

        jCBEventSelection.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jCBEventSelection.setName("jCBEventSelection"); // NOI18N

        jLEventToDelete.setName("jLEventToDelete"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jCBEventSelection, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(113, 113, 113)
                        .addComponent(jBDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLEventToDelete))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLEventToDelete)
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBEventSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void formComponentShown(java.awt.event.ComponentEvent evt) {// GEN-FIRST:event_formComponentShown
		setEvents(sOps.returnAllEvents());
		fillAllEvents();
		fillEventComboBox();
	}// GEN-LAST:event_formComponentShown

	private void cancel(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cancel
		dispose();
	}// GEN-LAST:event_cancel

	private void deleteEvent(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_deleteEvent
		Event event = sOps.returnEvent(jCBEventSelection.getSelectedItem()
				.toString());
		sOps.deleteRegistrationForEvent(event);
		setEvents(sOps.deleteEvents(events, event));
		fillAllEvents();
		fillEventComboBox();
		String lineToRemove = event.getName() + ";" + event.getLength() + ";"
				+ event.getStyle() + ";" + event.getGender() + ";"
				+ event.getPoolType();
		sOps.removeLineFromFile(dataFile.get("events"), lineToRemove);
	}// GEN-LAST:event_deleteEvent

	public void fillAllEvents() {
		String[] columnNames = { "Event Name", "Distance", "Style", "Gender",
				"Pool Type" };
		List<String[]> tableLines = new ArrayList<String[]>();
		for (Event ev : events) {
			tableLines.add(new String[] { ev.getName(), ev.getLength(),
					ev.getStyle(), ev.getGender(), ev.getPoolType() });
		}
		Object[][] data = new Object[tableLines.size()][5];
		for (int i = 0; i < tableLines.size(); i++) {
			data[i] = tableLines.get(i);
		}
		jTAllEvents.setModel(new javax.swing.table.DefaultTableModel(data,
				columnNames) {
			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		});
	}

	private void fillEventComboBox() {
		jCBEventSelection.removeAllItems();
		for (Event ev : events) {
			jCBEventSelection.addItem(ev.getName());
		}
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				AllEvents dialog = new AllEvents(new javax.swing.JFrame(), true);
				dialog.addWindowListener(new java.awt.event.WindowAdapter() {
					public void windowClosing(java.awt.event.WindowEvent e) {
						System.exit(0);
					}
				});
				dialog.setVisible(true);
			}
		});
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

}