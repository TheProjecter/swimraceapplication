package com.roly.samaboutwindow;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridData;

import swing2swt.layout.BoxLayout;

public class About extends Shell {

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			About shell = new About(display);
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the shell.
	 * 
	 * @param display
	 */
	public About(Display display) {
		super(display, SWT.SHELL_TRIM);
		setLayout(new GridLayout(1, false));

		Composite parent = new Composite(this, SWT.NONE);
		RowLayout gridLayout = new RowLayout();
		//gridLayout.numColumns = 5;
		parent.setLayout(gridLayout);

		// determine the path where the pictures are stored
		Image img = getLogo(display);

		// row 1 column 1
		Label emptyLabel11 = new Label(parent, SWT.NONE);
		emptyLabel11.setImage(img);
		Color color = new Color(display , 22, 22, 22);
		emptyLabel11.setBackground(color );
		// row 1 column 2
//		Label emptyLabel12 = new Label(parent, SWT.NONE);
//		// row 1 column 3
//		// determine the path where the pictures are stored
		Label imgLabel = new Label(parent, SWT.NONE);
		imgLabel.setImage(img);
//		// row 2 column 1
//		Label emptyLabel21 = new Label(parent, SWT.NONE);
//		// row 2 column 2
//		Label emptyLabel22 = new Label(parent, SWT.NONE);
//		// row 2 column 3
//		Label emptyLabel23 = new Label(parent, SWT.NONE);
//		// row 2 column 4
//		Label emptyLabel24 = new Label(parent, SWT.NONE);
//		// row 2 column 5
//		Label emptyLabel25 = new Label(parent, SWT.NONE);
//		// row 3 column 1
//		Label emptyLabel31 = new Label(parent, SWT.NONE);
//		// row 3 column 2
//		Label emptyLabel32 = new Label(parent, SWT.NONE);
//		// row 3 column 3
//		Label emptyLabel33 = new Label(parent, SWT.NONE);
//		// row 3 column 4
//		Label emptyLabel34 = new Label(parent, SWT.NONE);
//		// row 3 column 5
//		Label emptyLabel35 = new Label(parent, SWT.NONE);

		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("SWT Application");
		setSize(650, 406);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	private Image getLogo(Display display) {
		// determine the path where the pictures are stored
		String path = System.getProperty("user.dir") + "/util/";
		Image img = new Image(display, path + "grooveshark.ico");
		return img;
	}
}
