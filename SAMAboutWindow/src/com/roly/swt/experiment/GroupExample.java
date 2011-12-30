package com.roly.swt.experiment;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

public class GroupExample extends Composite {

	  final Button b1;

	  final Button b2;

	  final Button b3;

	  public GroupExample(Composite c, int style) {
	    super(c, SWT.NO_BACKGROUND);
	    this.setSize(110, 75);
	    this.setLayout(new FillLayout());
	    final Group g = new Group(this, style);
	    g.setSize(110, 75);
	    g.setText("Options Group");
	    g.setEnabled(false);
	    b1 = new Button(g, SWT.RADIO);
	    b1.setBounds(10, 20, 75, 15);
	    b1.setText("Option One");
	    b2 = new Button(g, SWT.RADIO);
	    b2.setBounds(10, 35, 75, 15);
	    b2.setText("Option Two");
	    b3 = new Button(g, SWT.RADIO);
	    b3.setBounds(10, 50, 80, 15);
	    b3.setText("Option Three");
	  }

	  public String getSelected() {
	    if (b1.getSelection())
	      return "Option One";
	    if (b2.getSelection())
	      return "Option Two";
	    if (b3.getSelection())
	      return "Option Three";
	    return "None Selected";
	  }

	}
