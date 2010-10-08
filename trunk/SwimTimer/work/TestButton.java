package work;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import entities.Swimmer;

public class TestButton extends JDialog {
	
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				TestButton dialog = new TestButton();
				dialog.addWindowListener(new java.awt.event.WindowAdapter() {
					public void windowClosing(java.awt.event.WindowEvent e) {
						System.exit(0);
					}
				});
				dialog.setVisible(true);
			}
		});
	}
	
    public void addComponentsToPane(final Container pane) {
    	setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    	
        final JPanel compsToExperiment = new JPanel();
        compsToExperiment.setLayout(new GridLayout(0,2));
        JPanel controls = new JPanel();
        controls.setLayout(new GridLayout(2,3));

        compsToExperiment.add(new JButton("Button 1"));
        compsToExperiment.add(new JButton("Button 2"));
        compsToExperiment.add(new JButton("Button 3"));
        compsToExperiment.add(new JButton("Button 4"));
        compsToExperiment.add(new JButton("5"));

        JComboBox horGapComboBox = new JComboBox(new String [] {"1", "2"});
        JComboBox verGapComboBox = new JComboBox(new String [] {"1", "2"});

        controls.add(new Label("Horizontal gap:"));
        controls.add(new Label("Vertical gap:"));
        controls.add(new Label(" "));
        controls.add(horGapComboBox);
        controls.add(verGapComboBox);
        controls.add(new JButton("applyButton"));

		final JPanel center = new JPanel();
		center.setLayout(new GridLayout(2,3));

		Operations operations = new Operations();
		List<Swimmer> swimmers = operations.returnAllSwimmers();
		int i = 0;
		for (Swimmer sw : swimmers) {
			JButton newbutton = new JButton(sw.getName());
			JSeparator lable = new javax.swing.JSeparator();
			center.add(newbutton);
			center.revalidate();
			center.repaint();
		}
        
		pane.add(compsToExperiment, BorderLayout.NORTH);
        pane.add(new JSeparator(), BorderLayout.CENTER);
        pane.add(controls, BorderLayout.CENTER);
        pane.add(center, BorderLayout.SOUTH);
        
        pack();
        setVisible(true);
    }
    
	public TestButton() {
		super();
		addComponentsToPane(getContentPane());
		
		
		//		setBounds(100, 100, 500, 375);
//
//		final JPanel center = new JPanel();
//		getContentPane().add(center, BorderLayout.CENTER);
		//
		// final JPanel south = new JPanel();
		// getContentPane().add(south, BorderLayout.SOUTH);
		// south.setBackground(new Color(3));
		//
		// final JButton button = new JButton();
		// button.addActionListener(new ActionListener() {
		// public void actionPerformed(final ActionEvent arg0) {
		// JButton newbutton = new JButton("Heeeeeeeee");
		// center.add(newbutton);
		// center.revalidate();
		// center.repaint();
		// }
		// });
		//
		// button.setText("Button1");
		// south.add(button);
//		center.setLayout(new GridLayout(2, 3));
//		center.add(new JButton("North"),  BorderLayout.NORTH);
//		center.add(new JSeparator(), BorderLayout.CENTER);
//		center.add(new JButton("South"),  BorderLayout.SOUTH);
//		center.revalidate();
//		center.repaint();
//		Operations operations = new Operations();
//		List<Swimmer> swimmers = operations.returnAllSwimmers();
//		int i = 0;
//		for (Swimmer sw : swimmers) {
//			JButton newbutton = new JButton(sw.getName());
//			JSeparator lable = new javax.swing.JSeparator();
//			lable.setSize(50, 50);
//			lable.setName("lable");
//			center.add(newbutton);
//			center.add(new JSeparator(), BorderLayout.CENTER);
//			center.revalidate();
//			center.repaint();
//		}
	}
	
}