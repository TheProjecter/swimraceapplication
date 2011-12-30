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

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
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

import com.roly.swt.experiment.GridComposite;

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
		Label imageLabelLogo = new Label(shell, SWT.NONE);
		imageLabelLogo.setImage(imgLogo);
		gridData = new GridData();
		// gridData.horizontalSpan = 2;
		gridData.horizontalAlignment = GridData.BEGINNING;
		imageLabelLogo.setLayoutData(gridData);

		// text
		Label nameLabel = new Label(shell, SWT.CENTER);
		nameLabel.setText("SAM Race Edition");
		Font csFont = new Font(shell.getDisplay(), "Comic Sans MS", 16, SWT.BOLD);
		nameLabel.setFont(csFont);
		gridData = new GridData();
		gridData.horizontalAlignment = GridData.CENTER;
		nameLabel.setLayoutData(gridData);

		// logo GPL
		Image imgGPL = getLogo(display, "gplv3-127x51.png");
		Label imageLabelGPL = new Label(shell, SWT.NONE);
		imageLabelGPL.setImage(imgGPL);
		gridData = new GridData();
		// gridData.horizontalSpan = 3;
		gridData.horizontalAlignment = GridData.END;
		imageLabelGPL.setLayoutData(gridData);

		// // text
		// Label textLabel1 = new Label(shell, SWT.NONE);
		// textLabel1.setText("SAM Race Edition");
		// // FontData[] fD = textLabel1.getFont().getFontData();
		// // fD[0].setHeight(16);
		// // fD[0].setStyle(SWT.BOLD);
		// // textLabel1.setFont( new Font(display,fD[0]));
		//
		// Font csFont = new Font(shell.getDisplay(), "Comic Sans MS", 16,
		// SWT.BOLD);
		// textLabel1.setFont(csFont);
		// gridData = new GridData();
		// gridData.horizontalSpan = 3;
		// gridData.horizontalAlignment = GridData.CENTER;
		// textLabel1.setLayoutData(gridData);
		//
		// Label textLabel = new Label(shell, SWT.CENTER);
		// textLabel.setText("\n" + "Management of a swimming competition \n"
		// + "		Product version 2.0 \n");
		// gridData = new GridData();
		// gridData.horizontalSpan = 3;
		// gridData.horizontalAlignment = GridData.CENTER;
		// textLabel.setLayoutData(gridData);

		// the tabs
		TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
		gridData = new GridData();
		gridData.horizontalSpan = 3;
		gridData.horizontalAlignment = GridData.FILL;
		tabFolder.setLayoutData(gridData);

		// About tab item
		TabItem aboutItem = new TabItem(tabFolder, SWT.NONE);
		aboutItem.setText("About");
		Label textLabel = new Label(tabFolder, SWT.CENTER);
		textLabel.setText("\n" + "Management of a swimming competition \n" + "    Product version 2.0    \n" + "   Copyright (C) 2010-2011 Rolland J. Sovarszki   \n"
				+ "   This program is distributed under the terms of the GNU General Public License(GPL) version 3 \n");
		aboutItem.setControl(textLabel);

		// Credits tab item
		TabItem creditsItem = new TabItem(tabFolder, SWT.NONE);
		creditsItem.setText("Credits");

		// Licence tab item
		TabItem licenceItem = new TabItem(tabFolder, SWT.NONE);
		licenceItem.setText("Licence");

		TabItem ti2 = new TabItem(tabFolder, SWT.BORDER);
		ti2.setText("Grid");
		ti2.setControl(new GridComposite(tabFolder));

		// Description with composite
		TabItem tabItemDesc = new TabItem(tabFolder, SWT.NONE);
		tabItemDesc.setText("About");
		Composite compositeForDesc = new Composite(tabFolder, SWT.NONE);
		FormLayout gl = new FormLayout();
		compositeForDesc.setLayout(gl);
		// set layouts for the composite
		// title
		final Label titleBold = new Label(compositeForDesc, SWT.BORDER);
		titleBold.setText("SAM Race Edition");
		Font titleFont = new Font(shell.getDisplay(), "Comic Sans MS", 14, SWT.BOLD);
		titleBold.setFont(titleFont);
		FormData formDataTitle = new FormData();
		formDataTitle.top = new FormAttachment(17,0);
		formDataTitle.left = new FormAttachment(3,0);
		titleBold.setLayoutData(formDataTitle);
		// description
		final Label titleDescription = new Label(compositeForDesc, SWT.BORDER);
		titleDescription.setText("- Management of a swimming competition");
		Font descriptionFont = new Font(shell.getDisplay(), "Comic Sans MS", 10, SWT.NONE);
		titleDescription.setFont(descriptionFont);
		FormData formData = new FormData();
		formData.top = new FormAttachment(titleBold, 0, SWT.CENTER);
		formData.left = new FormAttachment(titleBold, 20);
		titleDescription.setLayoutData(formData);
		// Copyright
		final Label copyrightLable = new Label(compositeForDesc, SWT.BORDER);
		copyrightLable.setText("Copyright (C) 2010-2011 Rolland J. Sovarszki");
		FormData fdCopyright = new FormData();
		fdCopyright.top = new FormAttachment(50,0);
		fdCopyright.left = new FormAttachment(3,0);
		copyrightLable.setLayoutData(fdCopyright);

		
		tabItemDesc.setControl(compositeForDesc);

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
