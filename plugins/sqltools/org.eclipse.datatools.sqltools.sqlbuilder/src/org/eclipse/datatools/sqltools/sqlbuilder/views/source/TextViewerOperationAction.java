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

package org.eclipse.datatools.sqltools.sqlbuilder.views.source;

import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.text.ITextOperationTarget;
import org.eclipse.jface.text.TextViewer;


// This class creates actions for a TextViewer based on the text operations
// defined in ITextOperationTarget
public class TextViewerOperationAction extends Action {

    protected int textOperation;
    protected TextViewer textViewer;

    public TextViewerOperationAction(TextViewer textViewer, int textOperation) {
        this.textViewer = textViewer;
        this.textOperation = textOperation;
        initText();
    }

    protected void initText() {
        switch (textOperation) {
        case ITextOperationTarget.COPY:
            setText(Messages._UI_MENU_COPY);
            break;
        case ITextOperationTarget.CUT:
            setText(Messages._UI_MENU_CUT);
            break;
        case ITextOperationTarget.DELETE:
            setText(Messages._UI_MENU_DELETE);
            break;
        case ITextOperationTarget.PASTE:
            setText(Messages._UI_MENU_PASTE);
            break;
        case ITextOperationTarget.PREFIX:
            setText(Messages._UI_MENU_PREFIX);
            break;
        case ITextOperationTarget.REDO:
            setText(Messages._UI_MENU_REDO);
            break;
        case ITextOperationTarget.SELECT_ALL:
            setText(Messages._UI_MENU_SELECT_ALL);
            break;
        case ITextOperationTarget.SHIFT_LEFT:
            setText(Messages._UI_MENU_SHIFT_LEFT);
            break;
        case ITextOperationTarget.SHIFT_RIGHT:
            setText(Messages._UI_MENU_SHIFT_RIGHT);
            break;
        case ITextOperationTarget.STRIP_PREFIX:
            setText(Messages._UI_MENU_STRIP_PREFIX);
            break;
        case ITextOperationTarget.UNDO:
            setText(Messages._UI_MENU_UNDO);
            break;
        }
    }

    public void run() {
        textViewer.doOperation(textOperation);
    }

    public void update() {
        setEnabled(textViewer.canDoOperation(textOperation));
    }
}