package com.roly.swt.experiment;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.layout.FillLayout;

public class Snippet293 {
	Display display = new Display();
	Shell shell = new Shell(display);

	public Snippet293() {
		shell.setLayout(new FillLayout());
		shell.setText("Tabs");

		final TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
		for (int i = 1; i < 5; i++) {
			TabItem item = new TabItem(tabFolder, SWT.NULL);
			item.setText("Tab" + i);
			Text text = new Text(tabFolder, SWT.BORDER | SWT.MULTI);
			text.setText("This is Tab " + i);
			item.setControl(text);
		}
		tabFolder.setSize(850, 850);
		tabFolder.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				System.out.println("You have selected:" + tabFolder.getSelection()[0].toString());
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
		shell.setSize(1300, 950);
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

	public static void main(String[] args) {
		new Snippet293();
	}
}