package com.roly.samaboutwindow;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class AboutWindow {

	public Shell createShell(final Display display) {
		final Shell shell = new Shell(display, SWT.CLOSE | SWT.TITLE | SWT.MIN);
		shell.setText("About SAM...");

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
		// text
		Label textLabel1 = new Label(shell, SWT.NONE);
		textLabel1.setText("SAM Race Edition");
		// FontData[] fD = textLabel1.getFont().getFontData();
		// fD[0].setHeight(16);
		// fD[0].setStyle(SWT.BOLD);
		// textLabel1.setFont( new Font(display,fD[0]));

		Font csFont = new Font(shell.getDisplay(), "Comic Sans MS", 16,
				SWT.BOLD);
		textLabel1.setFont(csFont);
		gridData = new GridData();
		gridData.horizontalSpan = 3;
		gridData.horizontalAlignment = GridData.CENTER;
		textLabel1.setLayoutData(gridData);

		Label textLabel = new Label(shell, SWT.CENTER);
		textLabel.setText("\n"
				+ "Management of a swimming competition \n"
				+ "		Product version 2.0 \n");
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

		// close button
		Button closeButton = new Button(shell, SWT.PUSH);
		closeButton.setText("Close");
		gridData = new GridData();
		gridData.horizontalAlignment = GridData.END;
		closeButton.setLayoutData(gridData);
		closeButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				shell.dispose();
			}
		});

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
