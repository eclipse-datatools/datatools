/*******************************************************************************
 * Copyright 2000, 2018 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.sqlbuilder.dialogs;

import org.eclipse.datatools.modelbase.sql.query.TableJoinedOperator;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderContextIds;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.util.ViewUtility;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.PlatformUI;


public class DefineTypeComposite {

    Button innerJoinRadio;
    Button leftOuterJoinRadio;
    Button rightOuterJoinRadio;
    Button fullOuterJoinRadio;
    SQLDomainModel domainModel;
    private Composite typeControls;

    public DefineTypeComposite(Composite parent, SQLDomainModel domainModel, int joinKind) {
        typeControls = new Composite(parent, SWT.NULL);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(typeControls, SQLBuilderContextIds.SQLG_DEFINE_JOINTYPE_DIALOG);

        GridLayout layout = new GridLayout();
        layout.numColumns = 1;
        typeControls.setLayout(layout);

        typeControls.setLayoutData(ViewUtility.createFill());

        this.domainModel = domainModel;

        createInputPanel(typeControls);
        initButtons(joinKind);
    }

    public Composite getControl() {
        return typeControls;
    }

    private void createInputPanel(Composite parent) {
        Group buttonGroup = ViewUtility.createGroup(parent, 1, 
                Messages._UI_DEFINE_JOIN_DIALOG_INSTR, false);

        innerJoinRadio = ViewUtility.createRadioButton(buttonGroup, 
                Messages._UI_RADIO_INNER_JOIN);
        leftOuterJoinRadio = ViewUtility.createRadioButton(buttonGroup, 
                Messages._UI_RADIO_LEFT_OUTER_JOIN);
        rightOuterJoinRadio = ViewUtility.createRadioButton(buttonGroup, 
                Messages._UI_RADIO_RIGHT_OUTER_JOIN);
        fullOuterJoinRadio = ViewUtility.createRadioButton(buttonGroup, 
                Messages._UI_RADIO_FULL_OUTER_JOIN);

        PlatformUI.getWorkbench().getHelpSystem().setHelp(innerJoinRadio, SQLBuilderContextIds.SQLG_DEFINE_JOINTYPE_DIALOG);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(leftOuterJoinRadio, SQLBuilderContextIds.SQLG_DEFINE_JOINTYPE_DIALOG);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(rightOuterJoinRadio, SQLBuilderContextIds.SQLG_DEFINE_JOINTYPE_DIALOG);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(fullOuterJoinRadio, SQLBuilderContextIds.SQLG_DEFINE_JOINTYPE_DIALOG);

        if (domainModel.getVendor().isDB2() || domainModel.getVendor().isMSSQLServer() || 
                domainModel.getVendor().isInstantDB()) {
            fullOuterJoinRadio.setEnabled(true);
        }
        else if (domainModel.getVendor().isInformix()) {
            fullOuterJoinRadio.setEnabled(false);
            leftOuterJoinRadio.setEnabled(false);
            rightOuterJoinRadio.setEnabled(false);
        }
        else {
            fullOuterJoinRadio.setEnabled(false);
        }

        String title = Messages._UI_DEFINE_JOIN_DIALOG_TITLE;
        Label filler = ViewUtility.createHorizontalFiller(parent, 1);

        // Expanding the dialog width to fit title text. Will use filler.
        // icon + length of title string + x

        filler.setText("       " + title + "      ");
        try {
            Font pFont = parent.getFont();
            if (pFont != null) {
                if (pFont.getFontData() != null) {
                    FontData pFontData = (pFont.getFontData())[0];
                    pFontData.setStyle(SWT.BOLD);
                    filler.setFont(new Font(Display.getCurrent(), pFontData));
                }
            }
        }
        catch (Exception e) {
        }
        filler.setVisible(false);

        parent.setLayoutData(ViewUtility.createFill());
    }

    public boolean isSet(int joinKind) {
        if (joinKind == TableJoinedOperator.DEFAULT_INNER) {
            return innerJoinRadio.getSelection();
        }
        else if (joinKind == TableJoinedOperator.LEFT_OUTER) {
            return leftOuterJoinRadio.getSelection();
        }
        else if (joinKind == TableJoinedOperator.RIGHT_OUTER) {
            return rightOuterJoinRadio.getSelection();
        }
        else {
            return fullOuterJoinRadio.getSelection();
        }
    }

    protected void initButtons(int joinKind) {
        if (joinKind == TableJoinedOperator.DEFAULT_INNER) {
            innerJoinRadio.setSelection(true);
        }
        else if (joinKind == TableJoinedOperator.LEFT_OUTER) {
            leftOuterJoinRadio.setSelection(true);
        }
        else if (joinKind == TableJoinedOperator.RIGHT_OUTER) {
            rightOuterJoinRadio.setSelection(true);
        }
        else {
            fullOuterJoinRadio.setSelection(true);
        }
    }

    public int getJoinKind() {
        if (isSet(TableJoinedOperator.DEFAULT_INNER)) {
            return TableJoinedOperator.DEFAULT_INNER;
        }
        else if (isSet(TableJoinedOperator.LEFT_OUTER)) {
            return TableJoinedOperator.LEFT_OUTER;
        }
        else if (isSet(TableJoinedOperator.RIGHT_OUTER)) {
            return TableJoinedOperator.RIGHT_OUTER;
        }
        else {
            return TableJoinedOperator.FULL_OUTER;
        }
    }

} // composite class

