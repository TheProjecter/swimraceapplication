package com.roly.swt.experiment;

import org.eclipse.swt.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.layout.*;

/*
 * Splash screen!!
 */
/*
 * ScrolledComposite example snippet: scroll a control in a scrolled composite
 *
 * For a list of all SWT example snippets see
 * http://www.eclipse.org/swt/snippets/
 */
import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.custom.*;

public class Snipets {

public static void main (String [] args) 
{
    Display display = new Display ();
    Shell shell = new Shell (display);
    shell.setLayout(new FillLayout());

    // this button is always 400 x 400. Scrollbars appear if the window is resized to be
    // too small to show part of the button
    ScrolledComposite c1 = new ScrolledComposite(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
    Button b1 = new Button(c1, SWT.PUSH);
    b1.setText("fixed size button");
    b1.setSize(400, 400);
    c1.setContent(b1);

    // this button has a minimum size of 400 x 400. If the window is resized to be big
    // enough to show more than 400 x 400, the button will grow in size. If the window
    // is made too small to show 400 x 400, scrollbars will appear.
    ScrolledComposite c2 = new ScrolledComposite(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
    Button b2 = new Button(c2, SWT.PUSH);
    b2.setText("expanding button");
    c2.setContent(b2);
    c2.setExpandHorizontal(true);
    c2.setExpandVertical(true);
    c2.setMinSize(400, 400);

    shell.setSize(600, 300);
    shell.open ();
    while (!shell.isDisposed ()) {
        if (!display.readAndDispatch ()) display.sleep ();
    }
    display.dispose ();
}

}