package customComponents;

import java.awt.Font;

import javax.swing.JLabel;

public class HeaderText extends JLabel{
	public HeaderText(String value, int allignment) {
		super(value, allignment);
		this.setFont(new Font("verdana", Font.BOLD, 11));
	}
}
