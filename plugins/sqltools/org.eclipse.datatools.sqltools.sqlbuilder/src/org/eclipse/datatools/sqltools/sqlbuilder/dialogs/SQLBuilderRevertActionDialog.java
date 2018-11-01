/*******************************************************************************
 * Copyright © 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.sqlbuilder.dialogs;

import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.util.ViewUtility;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;


public class SQLBuilderRevertActionDialog extends MessageDialog {

    private String goodSource = "";

    public SQLBuilderRevertActionDialog(Shell shell, String title, Image image, String message, 
            int imageType, String[] buttonLabels, int defaultIndex) {
        super(shell, title, image, message, imageType, buttonLabels, defaultIndex);
        int style = getShellStyle();
        setShellStyle(SWT.RESIZE | style);
    }

    protected Control createDialogArea(Composite parent) {
        if (parent.getLayout() instanceof GridLayout) {
            GridLayout gridLayout = (GridLayout) parent.getLayout();
            gridLayout.numColumns = 1;
        }

        ViewUtility.createLabel(parent, Messages._UI_LAST_CORRECT_SOURCE);

        Text multiText = ViewUtility.createMultiTextField(parent, 200, 200, true);
        GridData data = (GridData) multiText.getLayoutData();
        data.grabExcessVerticalSpace = true;
        data.grabExcessHorizontalSpace = true;

        multiText.setEditable(false);
        multiText.setText(getProperSourceString());
        return parent;
    }

    public void setProperSourceString(String proper) {
        goodSource = proper;
    }

    public String getProperSourceString() {
        return goodSource;
    }
}