/*
 *  This program is distributed under the terms of the GNU General Public License;
 * 
 * 	SAM Race Edition is a swimming competition management software.
 *  
 * 	Copyright (C) 2010-2011 Rolland J. Sovarszki
 * 
 *  This file is part of SAM Race Edition: you can redistribute it and/or modify
 * 	it under the terms of the GNU General Public License as published by
 * 	the Free Software Foundation, either version 3 of the License, or
 * 	(at your option) any later version.
 * 	
 * 	This program is distributed in the hope that it will be useful,
 * 	but WITHOUT ANY WARRANTY; without even the implied warranty of
 * 	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * 	GNU General Public License for more details.
 * 	
 * 	You should have received a copy of the GNU General Public License
 * 	along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.roly.samaboutwindow;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

public class AboutWindow {

	private GridData gridData;

	public Shell createShell(final Display display) {
		final Shell shell = new Shell(display, SWT.CLOSE | SWT.TITLE | SWT.MIN);
		shell.setText("About SAM...");
		
		// layout
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		shell.setLayout(gridLayout);

		// logo SAM
		Image imgLogo = getLogo(display, "grooveshark.ico");
		// Image imgLogo = getLogo(display, "tv2.png");
		Label imageLabelLogo = new Label(shell, SWT.NONE);
		imageLabelLogo.setImage(imgLogo);
		gridData = new GridData();
		gridData.horizontalAlignment = GridData.BEGINNING;
		imageLabelLogo.setLayoutData(gridData);

		// text
		Label nameLabel = new Label(shell, SWT.END);
		nameLabel.setText("SAM Race Edition");
		Font csFont = new Font(shell.getDisplay(), "Comic Sans MS", 16, SWT.BOLD);
		nameLabel.setFont(csFont);
		gridData = new GridData();
		gridData.horizontalAlignment = GridData.END;
		nameLabel.setLayoutData(gridData);

		// logo GPL
		Image imgGPL = getLogo(display, "gplv3-127x51.png");
		Label imageLabelGPL = new Label(shell, SWT.NONE);
		imageLabelGPL.setImage(imgGPL);
		gridData = new GridData();
		gridData.horizontalAlignment = GridData.END;
		imageLabelGPL.setLayoutData(gridData);

		// the tabs
		TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
		gridData = new GridData();
		gridData.horizontalSpan = 3;
		gridData.horizontalAlignment = GridData.FILL;
		tabFolder.setSize(200, 200);
		tabFolder.setLayoutData(gridData);

		// Description with composite
		TabItem tabItemDesc = new TabItem(tabFolder, SWT.NONE);
		tabItemDesc.setText("About");
		Composite compositeForDesc = new Composite(tabFolder, SWT.NONE);
		FormLayout gl = new FormLayout();
		compositeForDesc.setLayout(gl);
		// set layouts for the composite
		// title
		final Label titleBold = new Label(compositeForDesc, SWT.NONE);
		titleBold.setText("SAM Race Edition");
		Font titleFont = new Font(shell.getDisplay(), "Comic Sans MS", 14, SWT.BOLD);
		titleBold.setFont(titleFont);
		FormData formDataTitle = new FormData();
		formDataTitle.top = new FormAttachment(12, 0);
		formDataTitle.left = new FormAttachment(3, 0);
		titleBold.setLayoutData(formDataTitle);
		// description
		final Label titleDescription = new Label(compositeForDesc, SWT.NONE);
		titleDescription.setText("- Management of swimming competitions");
		Font descriptionFont = new Font(shell.getDisplay(), "Comic Sans MS", 10, SWT.NONE);
		titleDescription.setFont(descriptionFont);
		FormData formData = new FormData();
		formData.top = new FormAttachment(titleBold, 0, SWT.CENTER);
		formData.left = new FormAttachment(titleBold, 20);
		titleDescription.setLayoutData(formData);
		// Copyright
		final Label copyrightLable = new Label(compositeForDesc, SWT.NONE);
		copyrightLable.setText("Copyright (C) 2010-2011 Rolland J. Sovarszki");
		FormData fdCopyright = new FormData();
		fdCopyright.top = new FormAttachment(titleDescription, 7);
		fdCopyright.left = new FormAttachment(3, 0);
		copyrightLable.setLayoutData(fdCopyright);
		// Licence notice
		final Label licenceNotice = new Label(compositeForDesc, SWT.NONE);
		licenceNotice.setText("This program is distributed under the terms of the GNU General Public License(GPL) version 3");
		FontData licenceFontData = licenceNotice.getFont().getFontData()[0];
		Font fontLicence = new Font(display, new FontData(licenceFontData.getName(), licenceFontData.getHeight(), SWT.ITALIC));
		licenceNotice.setFont(fontLicence);
		FormData fdLicenceNotice = new FormData();
		fdLicenceNotice.top = new FormAttachment(copyrightLable, 15);
		fdLicenceNotice.left = new FormAttachment(3, 0);
		fdLicenceNotice.bottom = new FormAttachment(93, 0);
		fdLicenceNotice.right = new FormAttachment(97, 0);
		licenceNotice.setLayoutData(fdLicenceNotice);

		tabItemDesc.setControl(compositeForDesc);

		// Credits tab item
		TabItem creditsItem = new TabItem(tabFolder, SWT.NONE);
		creditsItem.setText("Credits");
		Composite compositeForCredits = new Composite(tabFolder, SWT.NONE);
		FormLayout glCredits = new FormLayout();
		compositeForCredits.setLayout(glCredits);
		final Text creditsText = new Text(compositeForCredits, SWT.SINGLE | SWT.BORDER);
		creditsText.setText("credits");

		creditsItem.setControl(compositeForCredits);

		// Licence tab item with composite
		TabItem licenceItem = new TabItem(tabFolder, SWT.NONE);
		licenceItem.setText("Licence");
		Composite compositeForLicence = new Composite(tabFolder, SWT.H_SCROLL | SWT.V_SCROLL); 
		FormLayout glLicence = new FormLayout();
		compositeForLicence.setLayout(glLicence);
		final StyledText licenceText = new StyledText(compositeForLicence, SWT.NONE);
		licenceText.setText("\n");
		List<String> lines = returnLicenceLinePerLine();
		int i = 0;
		for (String ln : lines) {
			if (i <= 30) {
				licenceText.append(ln.toString() + "\n");
				System.out.println(ln);
			}
			i++;
		}
		// licenceText.setLayoutData(layoutData)

		licenceItem.setControl(compositeForLicence);

		// TabItem ti1 = new TabItem(tabFolder, SWT.BORDER);
		// ti1.setText("Option Group");
		// ti1.setControl(new GroupExample(tabFolder, SWT.SHADOW_ETCHED_IN));

		// the buttons
		// Button creditsButton = new Button(shell, SWT.PUSH);
		// creditsButton.setText("Credits");
		// gridData = new GridData();
		// gridData.horizontalAlignment = GridData.CENTER;
		// creditsButton.setLayoutData(gridData);
		//
		// Button licenceButton = new Button(shell, SWT.PUSH);
		// licenceButton.setText("Licence");
		// gridData = new GridData();
		// gridData.horizontalAlignment = GridData.CENTER;
		// licenceButton.setLayoutData(gridData);

		// close button
		Button closeButton = new Button(shell, SWT.PUSH);
		closeButton.setText("Close");
		gridData = new GridData();
		gridData.horizontalSpan = 3;
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

	private Image getLogo(Display display, String imageName) {
		// determine the path where the pictures are stored
		String path = System.getProperty("user.dir") + "/util/";
		Image img = new Image(display, path + imageName);
		return img;
	}

	private List<String> returnLicenceLinePerLine() {
		Scanner scanner;
		List<String> lines = new ArrayList<String>();
		String path = System.getProperty("user.dir") + "/util/";
		try {
			scanner = new Scanner(new File(path + "gplv3.txt"));
			try {
				while (scanner.hasNextLine()) {
					lines.add(scanner.nextLine());
				}
			} finally {
				scanner.close();
			}
		} catch (IOException e) {
		}

		return lines;
	}

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new AboutWindow().createShell(display);
		shell.setSize(500, 400);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}

}
