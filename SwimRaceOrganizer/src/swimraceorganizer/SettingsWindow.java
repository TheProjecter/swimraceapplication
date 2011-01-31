package swimraceorganizer;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SettingsWindow  extends JDialog {

	private static final long serialVersionUID = 1L;

	private GridBagLayout controlLayout = new GridBagLayout();
	private JButton jBCancel = new JButton("Cancel");
	private JButton jBSaveSettings = new JButton("Save");
	private JComboBox jCBPoolType = new JComboBox();
	private JTextField jTEventName = new JTextField(10);
	
	private String poolType;
	private String competitionName;
	
	public SettingsWindow() {
		super();
		addComponentsToPane(getContentPane());
		fillPoolTypeCB();
		setAlwaysOnTop(true);
		setResizable(false);
		setTitle("Setari");
	}

	/**
	 * Adding the elements to the lable
	 * @param pane
	 */
	public void addComponentsToPane(final Container pane) {
		pane.setLayout(controlLayout);
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(10, 10, 10, 10);
		pane.add(new JLabel("Tipul bazinului"), c);

		c.weightx = 0.0;
		c.gridwidth = 2;
		c.gridx = 2;
		c.gridy = 0;
		pane.add(jCBPoolType, c);

		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(10, 10, 10, 10);
		pane.add(new JLabel("Nume concurs"), c);

		c.weightx = 0.0;
		c.gridwidth = 2;
		c.gridx = 2;
		c.gridy = 1;
		pane.add(jTEventName, c);
		
		c.gridx = 0;
		c.gridy = 2;
		pane.add(jBSaveSettings, c);

		c.gridx = 2;
		c.gridy = 2;
		jBCancel.setSize(50, 30);
		pane.add(jBCancel, c);

		jBCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		jBSaveSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setPoolType(jCBPoolType.getSelectedItem().toString());
				setCompetitionName(jTEventName.getText());
				dispose();
			}
		});
		
		pack();
		setVisible(true);
		
	}

	private void fillPoolTypeCB() {
		jCBPoolType.removeAllItems();
		jCBPoolType.addItem("25 Meters".toString());
		jCBPoolType.addItem("50 Meters".toString());
	}
	
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				SettingsWindow dialog = new SettingsWindow();
				dialog.addWindowListener(new java.awt.event.WindowAdapter() {
					public void windowClosing(java.awt.event.WindowEvent e) {
						System.exit(0);
					}
				});
				dialog.setVisible(true);
			}
		});
	}

	public String getPoolType() {
		return poolType;
	}

	public void setPoolType(String poolType) {
		this.poolType = poolType;
	}

	public String getCompetitionName() {
		return competitionName;
	}

	public void setCompetitionName(String eventName) {
		this.competitionName = eventName;
	}
}
