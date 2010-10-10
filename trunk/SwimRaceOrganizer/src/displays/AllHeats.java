package displays;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;

import customComponents.TimesComboBox;

import utils.EventOperations;
import work.Operations;
import entities.Heat;

public class AllHeats extends JDialog {

	private Operations operations = new Operations();
	private GridLayout controlLayout = new GridLayout(1,4);
	private EventOperations evOperations = new EventOperations();

	private JButton jBCancel = new JButton("Cancel");
	private JButton jBGenerateRezults = new JButton("Generate Results");;
	private JComboBox jCBMSeconds = new JComboBox();
	private JComboBox jCBMinutes = new JComboBox();
	private JComboBox jCBSeconds = new JComboBox();


	public AllHeats() {
		super();
		addComponentsToPane(getContentPane());
	}
	
	/**
	 * Adding the required components/panels to the dialog the dialog
	 * @param pane
	 */
    public void addComponentsToPane(final Container pane) {
    	setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        //Adding to the control panel (buttons)
    	final JPanel controlsPanel = new JPanel();
        controlsPanel.setLayout(controlLayout);
        
        controlsPanel.add(jBGenerateRezults);
        controlsPanel.add(new Label(" "));
        controlsPanel.add(new Label(" "));
        controlsPanel.add(jBCancel);

        //Adding to the heats panel
        List<Heat> heatList = operations.generateHeats(operations
				.returnEvent("50 liber"));

		final JPanel heatsPanel = new JPanel();
        heatsPanel.setLayout(new GridLayout((heatList.size() * 10), 7));

        for (Heat heats : heatList) {
        	// header
        	heatsPanel.add(new Label("Heat " + heats.getHeatNumber()));
            heatsPanel.add(new Label("Name"));
            heatsPanel.add(new Label("Age Group"));
            heatsPanel.add(new Label("Entry Time"));
            heatsPanel.add(new Label("Minutes"));
            heatsPanel.add(new Label("Seconds"));
            heatsPanel.add(new Label("M. Seconds"));
        	// Lane 1
        	heatsPanel.add(new Label("Lane " + heats.getLane1().getLaneNumber()));
            heatsPanel.add(new Label(heats.getLane1().getSwimmer().getName()));
            heatsPanel.add(new Label(heats.getLane1().getSwimmer().getAgeGroup()));
            heatsPanel.add(new Label(heats.getLane1().getEntryMinutes()+":"+heats.getLane1().getEntrySecondes()+":"+heats.getLane1().getEntryMSeconds()));
            heatsPanel.add(new TimesComboBox("minutes"));
            heatsPanel.add(new TimesComboBox("seconds"));
            heatsPanel.add(new TimesComboBox("mseconds"));
        	// Lane 2
        	heatsPanel.add(new Label("Lane " + heats.getLane2().getLaneNumber()));
            heatsPanel.add(new Label(heats.getLane2().getSwimmer().getName()));
            heatsPanel.add(new Label(heats.getLane2().getSwimmer().getAgeGroup()));
            heatsPanel.add(new Label(heats.getLane2().getEntryMinutes()+":"+heats.getLane2().getEntrySecondes()+":"+heats.getLane2().getEntryMSeconds()));
            heatsPanel.add(new TimesComboBox("minutes"));
            heatsPanel.add(new TimesComboBox("seconds"));
            heatsPanel.add(new TimesComboBox("mseconds"));
        	// Lane 3
        	heatsPanel.add(new Label("Lane " + heats.getLane3().getLaneNumber()));
            heatsPanel.add(new Label(heats.getLane3().getSwimmer().getName()));
            heatsPanel.add(new Label(heats.getLane3().getSwimmer().getAgeGroup()));
            heatsPanel.add(new Label(heats.getLane3().getEntryMinutes()+":"+heats.getLane3().getEntrySecondes()+":"+heats.getLane3().getEntryMSeconds()));
            heatsPanel.add(new TimesComboBox("minutes"));
            heatsPanel.add(new TimesComboBox("seconds"));
            heatsPanel.add(new TimesComboBox("mseconds"));
        	// Lane 4
        	heatsPanel.add(new Label("Lane " + heats.getLane4().getLaneNumber()));
            heatsPanel.add(new Label(heats.getLane4().getSwimmer().getName()));
            heatsPanel.add(new Label(heats.getLane4().getSwimmer().getAgeGroup()));
            heatsPanel.add(new Label(heats.getLane4().getEntryMinutes()+":"+heats.getLane4().getEntrySecondes()+":"+heats.getLane4().getEntryMSeconds()));
            heatsPanel.add(new TimesComboBox("minutes"));
            heatsPanel.add(new TimesComboBox("seconds"));
            heatsPanel.add(new TimesComboBox("mseconds"));
        	// Lane 5
        	heatsPanel.add(new Label("Lane " + heats.getLane5().getLaneNumber()));
            heatsPanel.add(new Label(heats.getLane5().getSwimmer().getName()));
            heatsPanel.add(new Label(heats.getLane5().getSwimmer().getAgeGroup()));
            heatsPanel.add(new Label(heats.getLane5().getEntryMinutes()+":"+heats.getLane5().getEntrySecondes()+":"+heats.getLane5().getEntryMSeconds()));
            heatsPanel.add(new TimesComboBox("minutes"));
            heatsPanel.add(new TimesComboBox("seconds"));
            heatsPanel.add(new TimesComboBox("mseconds"));
        	// Lane 6
        	heatsPanel.add(new Label("Lane " + heats.getLane6().getLaneNumber()));
            heatsPanel.add(new Label(heats.getLane6().getSwimmer().getName()));
            heatsPanel.add(new Label(heats.getLane6().getSwimmer().getAgeGroup()));
            heatsPanel.add(new Label(heats.getLane6().getEntryMinutes()+":"+heats.getLane6().getEntrySecondes()+":"+heats.getLane6().getEntryMSeconds()));
            heatsPanel.add(new TimesComboBox("minutes"));
            heatsPanel.add(new TimesComboBox("seconds"));
            heatsPanel.add(new TimesComboBox("mseconds"));
        	// Lane 7
        	heatsPanel.add(new Label("Lane " + heats.getLane7().getLaneNumber()));
            heatsPanel.add(new Label(heats.getLane7().getSwimmer().getName()));
            heatsPanel.add(new Label(heats.getLane7().getSwimmer().getAgeGroup()));
            heatsPanel.add(new Label(heats.getLane7().getEntryMinutes()+":"+heats.getLane7().getEntrySecondes()+":"+heats.getLane7().getEntryMSeconds()));
            heatsPanel.add(new TimesComboBox("minutes"));
            heatsPanel.add(new TimesComboBox("seconds"));
            heatsPanel.add(new TimesComboBox("mseconds"));
        	// Lane 8
        	heatsPanel.add(new Label("Lane " + heats.getLane8().getLaneNumber()));
            heatsPanel.add(new Label(heats.getLane8().getSwimmer().getName()));
            heatsPanel.add(new Label(heats.getLane8().getSwimmer().getAgeGroup()));
            heatsPanel.add(new Label(heats.getLane8().getEntryMinutes()+":"+heats.getLane8().getEntrySecondes()+":"+heats.getLane8().getEntryMSeconds()));
            heatsPanel.add(new TimesComboBox("minutes"));
            heatsPanel.add(new TimesComboBox("seconds"));
            heatsPanel.add(new TimesComboBox("mseconds"));
            // separators
            heatsPanel.add(new JSeparator());
            heatsPanel.add(new JSeparator());
            heatsPanel.add(new JSeparator());
            heatsPanel.add(new JSeparator());
            heatsPanel.add(new JSeparator());
            heatsPanel.add(new JSeparator());
            heatsPanel.add(new JSeparator());
        }

        jBCancel.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	dispose();
            }
        });


    	JScrollPane editorScroll = new JScrollPane(heatsPanel);
    	editorScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    	editorScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    	pane.setLayout(new BorderLayout());
    	pane.add(editorScroll);
    	pane.setPreferredSize(new Dimension(300, 300));
        pane.add(controlsPanel, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }

	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				AllHeats dialog = new AllHeats();
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
