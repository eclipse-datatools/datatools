/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.debugger.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.source.IVerticalRulerInfo;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.texteditor.AbstractRulerActionDelegate;
import org.eclipse.ui.texteditor.ITextEditor;

/**
 * @author Yang Liu
 */
public class ManageBreakpointRulerActionDelegate extends AbstractRulerActionDelegate
{

    private ManageBreakpointRulerAction targetAction;
    private IEditorPart                 activeEditor;

    /**
     * @see AbstractRulerActionDelegate#createAction()
     */
    protected IAction createAction(ITextEditor editor, IVerticalRulerInfo rulerInfo)
    {
        targetAction = new ManageBreakpointRulerAction(rulerInfo, editor);
        return targetAction;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IEditorActionDelegate#setActiveEditor(org.eclipse.jface.action.IAction,
     *      org.eclipse.ui.IEditorPart)
     */
    public void setActiveEditor(IAction callerAction, IEditorPart targetEditor)
    {
        if (activeEditor != null)
        {
            if (targetAction != null)
            {
                targetAction.dispose();
                targetAction = null;
            }
        }
        activeEditor = targetEditor;
        super.setActiveEditor(callerAction, targetEditor);
    }
}
