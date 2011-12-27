package com.roly.swt.experiment;

import org.eclipse.swt.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class Snippet293 {
	public static void main (String [] args) {
		Display display = new Display ();
		Shell shell = new Shell (display, SWT.SHELL_TRIM | SWT.DOUBLE_BUFFERED);
		shell.setLayout(new FillLayout ());
		final Image image = display.getSystemImage (SWT.ICON_QUESTION);
		shell.addListener (SWT.Paint, new Listener () {
			public void handleEvent (Event e) {
				Rectangle rect = image.getBounds ();
				GC gc = e.gc;
				int x = 50, y = 40;
				gc.drawImage (image, x, y);
				Transform tr = new Transform (e.display);
				//tr.setElements (1, 0, 0, -1, 1, 2*(y+rect.height));
				tr.setElements (1, 0, 0, -1, 1, 2*(y+rect.height));
				gc.setTransform (tr);
				gc.drawImage (image, x, y);
				gc.setTransform (null);
				Color background = gc.getBackground ();
				Pattern p = new Pattern (e.display, x, y+rect.height, x, y+(2*rect.height), background, 0, background, 255);
				gc.setBackgroundPattern (p);
				gc.fillRectangle (x, y+rect.height, rect.width, rect.height);
				p.dispose ();
				tr.dispose ();
			}
		});
		shell.setSize (200, 200);
		shell.open ();
		while (!shell.isDisposed ()) {
			if (!display.readAndDispatch ())
				display.sleep ();
		}
		display.dispose ();
	}
	
}