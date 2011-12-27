package com.roly.samaboutwindow;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

public class MainAbout extends Shell {
	public MainAbout() {
	}
	private Table table;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		// setup the SWT window
		Display displayImg = new Display();
		final Shell shell = new Shell(displayImg);
		shell.setSize(520, 200);
		shell.setLayout(new RowLayout());
		shell.setText("Photo Application");

		// initialize a parent composite with a grid layout manager
		// since the demo application uses 4x pictures the grid has exactly
		// 2x columns
		Composite parent = new Composite(shell, SWT.NONE);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		parent.setLayout(gridLayout);

		// determine the path where the pictures are stored
		String path = System.getProperty("user.dir") + "/util/";
		
		Label label = new Label(parent, SWT.NONE);
		Image img = new Image(displayImg, path + "grooveshark.ico");
		Label label2 = new Label(parent, SWT.NONE);
		label.setImage(img);
		label2.setImage(img);

		// show the SWT window
		shell.open();
		while (!shell.isDisposed()) {
			if (!displayImg.readAndDispatch())
				displayImg.sleep();
		}
		// tear down the SWT window
		displayImg.dispose();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("SWT Application");
		setSize(524, 354);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
