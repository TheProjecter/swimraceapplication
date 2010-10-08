package work;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import entities.Swimmer;

public class TestButton extends JDialog {
	public static void main(String args[]) {
		try {
			TestButton frame = new TestButton();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public TestButton() {
		super();
		setBounds(100, 100, 500, 375);
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		final JPanel center = new JPanel();
		getContentPane().add(center, BorderLayout.CENTER);
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
		Operations operations = new Operations();
		List<Swimmer> swimmers = operations.returnAllSwimmers();
		int i = 0;
		for (Swimmer sw : swimmers) {
			JButton newbutton = new JButton(sw.getName());
			center.add(newbutton);
			center.revalidate();
			center.repaint();
		}
	}
}