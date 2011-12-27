package com.roly.swt.experiment;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class LayoutExample {
    public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display);
        // Create the layout.
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 3;
        //gridLayout.makeColumnsEqualWidth = true;
        shell.setLayout(gridLayout);
        // Create the children of the composite.
        
        Button button1 = new Button(shell, SWT.PUSH);
        button1.setText("B1");
        GridData gridData = new GridData();
        gridData.verticalAlignment = GridData.FILL;
        button1.setLayoutData(gridData);

        new Button(shell, SWT.PUSH).setText("Wide Button 2");

        Button button3 = new Button(shell, SWT.PUSH);
        button3.setText("Button 3");
        gridData = new GridData();
        gridData.verticalAlignment = GridData.FILL;
        gridData.verticalSpan = 2;
        gridData.grabExcessVerticalSpace = true;
        gridData.horizontalAlignment = GridData.FILL;
        gridData.grabExcessHorizontalSpace = true;
        button3.setLayoutData(gridData);

        Button button4 = new Button(shell, SWT.PUSH);
        button4.setText("B4");
        gridData = new GridData();
        gridData.verticalAlignment = GridData.FILL;
        button4.setLayoutData(gridData);

        new Button(shell, SWT.PUSH).setText("Button 5");
        
        String path = System.getProperty("user.dir") + "/util/";
		Image img = new Image(display, path + "grooveshark.ico");

		// row 1 column 1
//		Label emptyLabel11 = new Label(shell, SWT.NONE);
//		emptyLabel11.setImage(img);
//		Color color = new Color(display , 22, 22, 22);
//		emptyLabel11.setBackground(color);

        shell.pack();
        shell.open();
        
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) display.sleep();
        }
    }

}