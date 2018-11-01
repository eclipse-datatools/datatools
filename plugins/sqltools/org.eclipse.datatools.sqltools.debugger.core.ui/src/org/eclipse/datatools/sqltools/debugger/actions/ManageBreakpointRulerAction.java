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

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.EditorCorePlugin;
import org.eclipse.datatools.sqltools.core.IControlConnection;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.debugger.core.internal.DebuggerCorePlugin;
import org.eclipse.datatools.sqltools.debugger.core.internal.DebuggerMessages;
import org.eclipse.datatools.sqltools.debugger.core.internal.DebuggerPreferenceConstants;
import org.eclipse.datatools.sqltools.debugger.core.ui.DebuggerCoreUIPlugin;
import org.eclipse.datatools.sqltools.debugger.model.SPDebugModelUtil;
import org.eclipse.datatools.sqltools.routineeditor.ui.ProcEditorInput;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditor;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.IBreakpointManager;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.IVerticalRulerInfo;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.texteditor.AbstractMarkerAnnotationModel;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.IUpdate;

/**
 * This action is for toggle breakpoints when user double click ruler.
 * 
 * @author Yang Liu
 */
public class ManageBreakpointRulerAction extends Action implements IUpdate
{
	
    private IVerticalRulerInfo      fRuler;
    private ITextEditor             fTextEditor;
    private ToggleBreakpointAdapter fBreakpointAdapter;

    public ManageBreakpointRulerAction(IVerticalRulerInfo ruler, ITextEditor editor)
    {
        super(DebuggerMessages.ManageBreakpointRulerAction_label); 
        fRuler = ruler;
        fTextEditor = editor;
        fBreakpointAdapter = new ToggleBreakpointAdapter();
    }

    /**
     * Disposes this action
     */
    public void dispose()
    {
        fTextEditor = null;
        fRuler = null;
    }

    /**
     * Returns this action's vertical ruler info.
     * 
     * @return this action's vertical ruler
     */
    protected IVerticalRulerInfo getVerticalRulerInfo()
    {
        return fRuler;
    }

    /**
     * Returns this action's editor.
     * 
     * @return this action's editor
     */
    protected ITextEditor getTextEditor()
    {
        return fTextEditor;
    }

    protected Shell getShell()
    {
        return fTextEditor.getSite().getShell();
    }

    /**
     * Returns the <code>IDocument</code> of the editor's input.
     * 
     * @return the document of the editor's input
     */
    protected IDocument getDocument()
    {
        IDocumentProvider provider = fTextEditor.getDocumentProvider();
        return provider.getDocument(fTextEditor.getEditorInput());
    }

    /**
     * @see Action#run()
     */
    public void run()
    {
        //If the editor is dirty, whether setting breakpoint disabled prompt will display or not according to the user preference  
        if(getTextEditor().isDirty())  
        {
            String preferenceKey = DebuggerPreferenceConstants.PROMPT_SETTING_BRAKEPOINT_DISABLE;  
            String notPrompt = DebuggerCoreUIPlugin.getDefault().getPreferenceStore().getString(preferenceKey);  
            if (MessageDialogWithToggle.ALWAYS.equals(notPrompt))  
            {
                //call ToggleBreakpointerAdapter util method to display error message in status line  
                fBreakpointAdapter.report(DebuggerMessages.ToggleBreakpointAdapter_canToggleLineBreakpoints_message, getTextEditor());  
                return;  
            }
            IWorkbenchWindow window = DebuggerCoreUIPlugin.getActiveWorkbenchWindow();  
            Shell shell = window.getShell();  
            if (shell == null)  
            {
                return;  
            }
            // Activate the shell if necessary so the prompt is visible  
            if (shell.getMinimized())  
            {
                shell.setMinimized(false);  
            }
            MessageDialogWithToggle dialog = MessageDialogWithToggle.openInformation(  
                shell,  
                DebuggerMessages.ToggleBreakpointAdapter_canToggleLineBreakpoints_title,  
                DebuggerMessages.ToggleBreakpointAdapter_canToggleLineBreakpoints_message,  
                DebuggerMessages.ToggleBreakpointAdapter_canToggleLineBreakpoints_toggleMessage,  
                false, DebuggerCoreUIPlugin.getDefault().getPreferenceStore(),  
                preferenceKey); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$  
            return;  
        }

        try
        {
            int lineNumber = getVerticalRulerInfo().getLineOfLastMouseButtonActivity() + 1;
            IEditorInput input = fTextEditor.getEditorInput();
            ProcIdentifier procid;
            if (input instanceof ProcEditorInput)
            {
                procid = ((ProcEditorInput) input).getProcIdentifier();
            }
            else
            {
                return; //breakpoint only for ProcEditInput
            }
            List list = SPDebugModelUtil.findAllLineBreakpoint(procid, lineNumber);

            if (list.isEmpty())
            {
                // create new markers
                fBreakpointAdapter.toggleLineBreakpoints(fTextEditor, lineNumber);
                //                SPDebugModelUtil.createLineBreakpoint(procid, lineNumber, true);
            }
            else
            {
                // remove existing breakpoints of any type
                IBreakpointManager manager = DebugPlugin.getDefault().getBreakpointManager();
                Iterator iterator = list.iterator();
                while (iterator.hasNext())
                {
                    IBreakpoint breakpoint = (IBreakpoint) iterator.next();
                    if (breakpoint != null)
                    {
                        breakpoint.delete();
                    }
                }
            }
        }
        catch (CoreException e)
        {
            ErrorDialog
					.openError(
							getShell(),
							DebuggerMessages.ManageBreakpointRulerAction_error, DebuggerMessages.ManageBreakpointRulerAction_fail, DebuggerCorePlugin.getDefault().createErrorStatus(e)); 
        }
    }

    /**
     * Returns the <code>AbstractMarkerAnnotationModel</code> of the editor's input.
     * 
     * @return the marker annotation model
     */
    protected AbstractMarkerAnnotationModel getAnnotationModel()
    {
        IDocumentProvider provider = fTextEditor.getDocumentProvider();
        IAnnotationModel model = provider.getAnnotationModel(fTextEditor.getEditorInput());
        if (model instanceof AbstractMarkerAnnotationModel)
        {
            return (AbstractMarkerAnnotationModel) model;
        }
        return null;
    }

    /**
     * Checks whether a position includes the ruler's line of activity.
     * 
     * @param position the position to be checked
     * @param document the document the position refers to
     * @return <code>true</code> if the line is included by the given position
     */
    protected boolean includesRulerLine(Position position, IDocument document)
    {

        if (position != null)
        {
            try
            {
                int markerLine = document.getLineOfOffset(position.getOffset());
                int line = fRuler.getLineOfLastMouseButtonActivity();
                if (line == markerLine)
                {
                    return true;
                }
            }
            catch (BadLocationException x)
            {
            }
        }

        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.texteditor.IUpdate#update()
     */
    public void update()
    {
        if (fTextEditor instanceof SQLEditor)
        {
            SQLEditor sqlEditor = (SQLEditor) fTextEditor;
            if (sqlEditor.getEditorInput() instanceof ProcEditorInput)
            {
                IControlConnection con = null;
                try
                {
                	DatabaseIdentifier dbid = new DatabaseIdentifier(sqlEditor.getConnectionInfo().getConnectionProfileName(), sqlEditor.getConnectionInfo().getDatabaseName());
                    con = EditorCorePlugin.getControlConnectionManager().getOrCreateControlConnection(dbid);
                }
                catch (Exception e)
                {
                    DebuggerCorePlugin.getDefault().log(DebuggerMessages.ManageBreakpointRulerAction_exception_getControlConnection, e);
                }
                this.setEnabled(con != null && con.supportsDebugging());
            }
            else
            {
                this.setEnabled(false);
            }
        }

    }

}
