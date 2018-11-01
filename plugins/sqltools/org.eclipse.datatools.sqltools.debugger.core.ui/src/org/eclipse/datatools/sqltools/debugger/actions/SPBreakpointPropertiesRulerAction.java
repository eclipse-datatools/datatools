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

import org.eclipse.datatools.sqltools.debugger.breakpoint.SPLineBreakpoint;
import org.eclipse.datatools.sqltools.debugger.core.internal.DebuggerMessages;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.source.IVerticalRulerInfo;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.dialogs.PropertyDialogAction;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.IUpdate;

/**
 * @author Yang Liu
 */
public class SPBreakpointPropertiesRulerAction extends AbstractBreakpointRulerAction implements IAction
{

    /**
     * Creates the action to enable/disable breakpoints
     */
    public SPBreakpointPropertiesRulerAction(ITextEditor editor, IVerticalRulerInfo info)
    {
        setInfo(info);
        setTextEditor(editor);
        setText(DebuggerMessages.SPBreakpointPropertiesRulerAction_label); 
    }

    /**
     * @see Action#run()
     */
    public void run()
    {
        if (getBreakpoint() != null)
        {
            PropertyDialogAction action = new PropertyDialogAction(getTextEditor().getEditorSite().getShell(),
                new ISelectionProvider()
            {
                public void addSelectionChangedListener(ISelectionChangedListener listener)
                {
                }

                public ISelection getSelection()
                {
                    return new StructuredSelection(getBreakpoint());
                }

                public void removeSelectionChangedListener(ISelectionChangedListener listener)
                {
                }

                public void setSelection(ISelection selection)
                {
                }
            }
            );
            action.run();
        }
    }

    /**
     * @see IUpdate#update()
     */
    public void update()
    {
        setBreakpoint(determineBreakpoint());
        if (getBreakpoint() == null || !(getBreakpoint() instanceof SPLineBreakpoint))
        {
            setBreakpoint(null);
            setEnabled(false);
            return;
        }
        setEnabled(true);
    }

}
