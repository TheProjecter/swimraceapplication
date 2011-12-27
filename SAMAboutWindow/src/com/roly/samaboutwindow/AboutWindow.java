package com.roly.samaboutwindow;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class AboutWindow {

	public Shell createShell(final Display display) {
		final Shell shell = new Shell(display,  SWT.CLOSE | SWT.TITLE | SWT.MIN);
		shell.setText("SAM Version 2.0");

		// layout
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		shell.setLayout(gridLayout);

		// logo
		Image img = getLogo(display);
		Label imageLabel = new Label(shell, SWT.NONE);
		imageLabel.setImage(img);
		GridData gridData = new GridData();
		gridData.horizontalSpan = 3;
		gridData.horizontalAlignment = GridData.CENTER;
		imageLabel.setLayoutData(gridData);

		// text
		Label textLabel = new Label(shell, SWT.NONE);
		textLabel.setText("Software for swimming race management");
		gridData = new GridData();
		gridData.horizontalSpan = 3;
		gridData.horizontalAlignment = GridData.CENTER;
		textLabel.setLayoutData(gridData);

		// the buttons
		Button creditsButton = new Button(shell, SWT.PUSH);
		creditsButton.setText("Credits");
		gridData = new GridData();
		gridData.horizontalAlignment = GridData.CENTER;
		creditsButton.setLayoutData(gridData);
		
		Button licenceButton = new Button(shell, SWT.PUSH);
		licenceButton.setText("Licence");
		gridData = new GridData();
		gridData.horizontalAlignment = GridData.CENTER;
		licenceButton.setLayoutData(gridData);
		
		Button closeButton = new Button(shell, SWT.PUSH);
		closeButton.setText("Close");
		gridData = new GridData();
		gridData.horizontalAlignment = GridData.END;
		closeButton.setLayoutData(gridData);
		
		shell.pack();
		return shell;
	}

	private Image getLogo(Display display) {
		// determine the path where the pictures are stored
		String path = System.getProperty("user.dir") + "/util/";
		Image img = new Image(display, path + "grooveshark.ico");
		return img;
	}

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new AboutWindow().createShell(display);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}

}
