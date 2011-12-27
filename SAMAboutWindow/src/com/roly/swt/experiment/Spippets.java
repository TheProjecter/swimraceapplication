package com.roly.swt.experiment;

import org.eclipse.swt.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.graphics.*;

public class Spippets {

	static String text = "Plans do not materialize out of nowhere, nor are they entirely static. To ensure the planning process is " +
		"transparent and open to the entire Eclipse community, we (the Eclipse PMC) post plans in an embryonic "+
		"form and revise them throughout the release cycle. \n"+
		"The first part of the plan deals with the important matters of release deliverables, release milestones, target "+
		"operating environments, and release-to-release compatibility. These are all things that need to be clear for "+
		"any release, even if no features were to change.  \n";
	static Image oldImage;
	
	public static void main(String [] args) {
		final Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		final StyledText styledText = new StyledText(shell, SWT.WRAP | SWT.BORDER);
		styledText.setText(text);
		FontData data = display.getSystemFont().getFontData()[0];
		Font font = new Font(display, data.getName(), 16, SWT.BOLD);
		styledText.setFont(font);
		styledText.setForeground(display.getSystemColor (SWT.COLOR_BLUE));
		styledText.addListener (SWT.Resize, new Listener () {
			public void handleEvent (Event event) {
				Rectangle rect = styledText.getClientArea ();
				Image newImage = new Image (display, 1, Math.max (1, rect.height));
				GC gc = new GC (newImage);
				gc.setForeground (display.getSystemColor (SWT.COLOR_WHITE));
				gc.setBackground (display.getSystemColor (SWT.COLOR_YELLOW));
				gc.fillGradientRectangle (rect.x, rect.y, 1, rect.height, true);
				gc.dispose ();
				styledText.setBackgroundImage (newImage);
				if (oldImage != null) oldImage.dispose ();
				oldImage = newImage;
			}
		});	
		shell.setSize(700, 400);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		if (oldImage != null) oldImage.dispose ();
		font.dispose();
		display.dispose();
	}
}