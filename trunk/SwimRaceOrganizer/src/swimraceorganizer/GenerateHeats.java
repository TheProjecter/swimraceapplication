/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * GenerateHeats.java
 *
 * Created on 27.09.2010, 14:29:22
 */

package swimraceorganizer;

import java.util.List;

import entities.Event;
import entities.Heat;

import utils.EventOperations;
import work.Operations;

/**
 *
 * @author rsovarsz
 */
public class GenerateHeats extends javax.swing.JDialog {

	private EventOperations evOperations = new EventOperations();
	private Operations operations = new Operations();
	
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBCancel;
    private javax.swing.JButton jBGenerate;
    private javax.swing.JComboBox jCBEvent;
    private javax.swing.JLabel jLEvent;
    private javax.swing.JLabel jLStatus;
    private javax.swing.JSeparator jSeparatorStatus;
    // End of variables declaration//GEN-END:variables

    /** Creates new form GenerateHeats */
    public GenerateHeats(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        fillEventNames();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCBEvent = new javax.swing.JComboBox();
        jBGenerate = new javax.swing.JButton();
        jBCancel = new javax.swing.JButton();
        jLEvent = new javax.swing.JLabel();
        jSeparatorStatus = new javax.swing.JSeparator();
        jLStatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(swimraceorganizer.SwimRaceOrganizerApp.class).getContext().getResourceMap(GenerateHeats.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N
        setResizable(false);

        jCBEvent.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jCBEvent.setName("jCBEvent"); // NOI18N

        jBGenerate.setText(resourceMap.getString("jBGenerate.text")); // NOI18N
        jBGenerate.setName("jBGenerate"); // NOI18N
        jBGenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateHeats(evt);
            }
        });

        jBCancel.setText(resourceMap.getString("jBCancel.text")); // NOI18N
        jBCancel.setName("jBCancel"); // NOI18N
        jBCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancel(evt);
            }
        });

        jLEvent.setText(resourceMap.getString("jLEvent.text")); // NOI18N
        jLEvent.setName("jLEvent"); // NOI18N

        jSeparatorStatus.setName("jSeparatorStatus"); // NOI18N

        jLStatus.setFont(resourceMap.getFont("jLStatus.font")); // NOI18N
        jLStatus.setText(resourceMap.getString("jLStatus.text")); // NOI18N
        jLStatus.setName("jLStatus"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jCBEvent, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jBGenerate)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jBCancel)))
                    .addComponent(jLEvent)
                    .addComponent(jSeparatorStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                    .addComponent(jLStatus))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLEvent)
                .addGap(6, 6, 6)
                .addComponent(jCBEvent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBGenerate)
                    .addComponent(jBCancel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparatorStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLStatus)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	protected void generateHeats(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_generateHeats
		List<Heat> heatList = operations.generateHeats(operations
				.returnEvent(jCBEvent.getSelectedItem().toString()));
		operations.registerHeats(heatList, heatList.get(0).getEventName() + ".csv");
		jLStatus.setText("Heat " + heatList.get(0).getEventName() + " registered");
	}// GEN-LAST:event_generateHeats

    private void cancel(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancel
		dispose();
    }//GEN-LAST:event_cancel

	public void fillEventNames() {
		List<String> eventNames = evOperations.getEventNames();
		jCBEvent.removeAllItems();
		for (String evName : eventNames) {
			jCBEvent.addItem(evName);
		}
	}

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GenerateHeats dialog = new GenerateHeats(new javax.swing.JFrame(), true);
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
