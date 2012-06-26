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

package swimraceorganizer;

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
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

public class AboutWindow {

	private GridData gridData;

	public Shell createShell(final Display display) {
		final Shell shell = new Shell(display, SWT.TITLE);
		// final Shell shell = new Shell(display);
		shell.setText("About SAM...");
		shell.addListener(SWT.Close, new Listener() {
			public void handleEvent(Event event) {
				shell.dispose();
				display.dispose();
			}
		});

		final Font bitstreamVeraSansMono9 = new Font(shell.getDisplay(), "Bitstream Vera Sans Mono", 9, SWT.NONE);
		final Font bitstreamVeraSansMonoItallic9 = new Font(shell.getDisplay(), "Bitstream Vera Sans Mono", 9, SWT.ITALIC);
		final Font csFont = new Font(shell.getDisplay(), "Comic Sans MS", 16, SWT.BOLD);
		final Font titleFont = new Font(shell.getDisplay(), "Comic Sans MS", 14, SWT.BOLD);
		final Font descriptionFont = new Font(shell.getDisplay(), "Comic Sans MS", 10, SWT.NONE);

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
		gridData.verticalAlignment = GridData.CENTER;
		gridData.horizontalAlignment = GridData.CENTER;
		imageLabelLogo.setLayoutData(gridData);

		// text
		Label nameLabel = new Label(shell, SWT.END);
		nameLabel.setText("SAM Race Edition");
		nameLabel.setFont(csFont);
		gridData = new GridData();
		gridData.verticalAlignment = GridData.CENTER;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.CENTER;
		gridData.grabExcessHorizontalSpace = true;
		nameLabel.setLayoutData(gridData);

		// logo GPL
		Image imgGPL = getLogo(display, "gplv3-127x51.png");
		Label imageLabelGPL = new Label(shell, SWT.NONE);
		imageLabelGPL.setImage(imgGPL);
		gridData = new GridData();
		gridData.verticalAlignment = GridData.CENTER;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.CENTER;
		gridData.grabExcessHorizontalSpace = true;
		imageLabelGPL.setLayoutData(gridData);

		// the tabs
		TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
		gridData = new GridData();
		gridData.horizontalSpan = 3;
		gridData.horizontalAlignment = GridData.FILL;
		gridData.verticalAlignment = GridData.FILL;
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
		titleBold.setFont(titleFont);
		FormData formDataTitle = new FormData();
		formDataTitle.top = new FormAttachment(12, 0);
		formDataTitle.left = new FormAttachment(3, 0);
		titleBold.setLayoutData(formDataTitle);
		// description
		final Label titleDescription = new Label(compositeForDesc, SWT.NONE);
		titleDescription.setText("- Management of swimming competitions");
		titleDescription.setFont(descriptionFont);
		FormData formData = new FormData();
		formData.top = new FormAttachment(titleBold, 0, SWT.CENTER);
		formData.left = new FormAttachment(titleBold, 20);
		titleDescription.setLayoutData(formData);
		// Copyright
		final Label copyrightLable = new Label(compositeForDesc, SWT.NONE);
		copyrightLable.setText("Copyright (C) 2010-2012 Rolland J. Sovarszki");
		copyrightLable.setFont(bitstreamVeraSansMono9);
		FormData fdCopyright = new FormData();
		fdCopyright.top = new FormAttachment(titleDescription, 7);
		fdCopyright.left = new FormAttachment(3, 0);
		copyrightLable.setLayoutData(fdCopyright);
		// Licence notice
		final Label licenceNotice = new Label(compositeForDesc, SWT.NONE);
		licenceNotice.setText("This program is distributed under the terms of the GNU General Public License(GPL) version 3");
		licenceNotice.setFont(bitstreamVeraSansMonoItallic9);
		FormData fdLicenceNotice = new FormData();
		fdLicenceNotice.top = new FormAttachment(copyrightLable, 15);
		fdLicenceNotice.left = new FormAttachment(3, 0);
		fdLicenceNotice.bottom = new FormAttachment(93, 1);
		fdLicenceNotice.right = new FormAttachment(97, 0);
		licenceNotice.setLayoutData(fdLicenceNotice);

		tabItemDesc.setControl(compositeForDesc);

		// ////////////////////////////////////////////////

		TabItem creditsItem = new TabItem(tabFolder, SWT.NONE);
		creditsItem.setText("Credits");

		Composite creditsArea = new Composite(tabFolder, SWT.NONE);
		FormLayout gl2 = new FormLayout();
		creditsArea.setLayout(gl2);

		// create some controls in TabArea and assign a layout to TabArea
		final Label mainDeveloper = new Label(creditsArea, SWT.NONE);
		mainDeveloper.setText("Developed by Rolland J. Sovarszki");
		mainDeveloper.setFont(bitstreamVeraSansMono9);
		FormData devPosition = new FormData();
		devPosition.top = new FormAttachment(12, 0);
		devPosition.left = new FormAttachment(3, 0);
		mainDeveloper.setLayoutData(devPosition);

		final Label testers = new Label(creditsArea, SWT.NONE);
		testers.setText("Tested by Cezar Listeveanu");
		testers.setFont(bitstreamVeraSansMono9);
		FormData testersPosition = new FormData();
		testersPosition.top = new FormAttachment(42, 0);
		testersPosition.left = new FormAttachment(3, 0);
		testers.setLayoutData(testersPosition);

		creditsItem.setControl(creditsArea);

		// //////////////////////////////////////////////////

		// Licence tab item with composite
		TabItem licenceItem = new TabItem(tabFolder, SWT.NONE);
		licenceItem.setText("Licence");
		Composite compositeForLicence = new Composite(tabFolder, SWT.NONE);
		FormLayout glLicence = new FormLayout();
		compositeForLicence.setLayout(glLicence);

		Link link = new Link(compositeForLicence, SWT.NONE);
		String message = "GNU General Public License(GPL) version 3 <a href=\"http://www.gnu.org\">www.gnu.org</a>";
		link.setText(message);
		FormData linkPosition = new FormData();
		linkPosition.top = new FormAttachment(42, 0);
		linkPosition.left = new FormAttachment(3, 0);
		link.setLayoutData(linkPosition);
		// link.addSelectionListener(new SelectionAdapter() {
		// @Override
		// public void widgetSelected(SelectionEvent e) {
		// System.out.println("You have selected: " + e.text);
		// try {
		// // Open default external browser
		// PlatformUI.getWorkbench().getBrowserSupport().getExternalBrowser().openURL(new
		// URL(e.text));
		// } catch (PartInitException ex) {
		// // TODO Auto-generated catch block
		// ex.printStackTrace();
		// } catch (MalformedURLException ex) {
		// // TODO Auto-generated catch block
		// ex.printStackTrace();
		// }
		// }
		// });
		licenceItem.setControl(compositeForLicence);

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
				display.dispose();
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
