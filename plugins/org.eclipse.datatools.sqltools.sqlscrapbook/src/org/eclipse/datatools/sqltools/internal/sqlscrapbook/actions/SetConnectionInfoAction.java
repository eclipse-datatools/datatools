/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.internal.sqlscrapbook.actions;

import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.SqlscrapbookPlugin;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.connection.ConnectionInfoDialog;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.editor.SQLScrapbookEditor;
import org.eclipse.datatools.sqltools.sqleditor.internal.IHelpContextIds;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.TextEditorAction;


/**
 * @author Li Huang
 *
 */
public class SetConnectionInfoAction extends TextEditorAction
{
    SQLScrapbookEditor _sqlEditor;

    /**
     * @param bundle
     * @param prefix
     * @param editor
     */
    public SetConnectionInfoAction(ResourceBundle bundle, String prefix, ITextEditor editor)
    {
        super(bundle, prefix, editor);
        _sqlEditor = (SQLScrapbookEditor) editor;
        PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IHelpContextIds.ATTACH_CONNECTION_PROFILE_ACTION);
    }

    public void run()
    {
//    	reset editor input will lose current modification, so prompt to save first
        if (_sqlEditor.isDirty())
        {
            String message = NLS.bind( Messages.EditorManager_saveChangesQuestion, 
            		_sqlEditor.getTitle()
            ); //$NON-NLS-1$
            // Show a dialog.
            String[] buttons = new String[]
            {
                IDialogConstants.YES_LABEL, IDialogConstants.NO_LABEL, IDialogConstants.CANCEL_LABEL
            }
            ;
            MessageDialog d = new MessageDialog(null, Messages.Save_Resource, //$NON-NLS-1$
            null, message, MessageDialog.QUESTION, buttons, 0);
            int choice = d.open();

            // Branch on the user choice.
            // The choice id is based on the order of button labels above.
            switch (choice)
            {
                case 0: //yes
                    // Create save block.
                    IRunnableWithProgress progressOp = new IRunnableWithProgress()
                    {
                        public void run(IProgressMonitor monitor)
                        {
                        	_sqlEditor.doSave(monitor);
                        }
                    }
                    ;

                    try
                    {
                        // Do the save.
                        SqlscrapbookPlugin.getActiveWorkbenchWindow().run(false, true, progressOp);
                    }
                    catch (InvocationTargetException e)
                    {
                        Throwable targetExc = e.getTargetException();
                        String title = NLS.bind(Messages.EditorManager_operationFailed, 
                            Messages.Save
                        ); //$NON-NLS-1$
                        MessageDialog.openError(null, Messages.common_error, //$NON-NLS-1$
                        title + ':' + targetExc.getMessage());
                    }
                    catch (InterruptedException e)
                    {
                        // Ignore. The user pressed cancel.
                        return;
                    }
                    break;
                case 1: //no
                    break;
                default:
                case 2: //cancel
                    return;
            }

        }
        ConnectionInfoDialog dlg = new ConnectionInfoDialog(_sqlEditor.getEditorSite().getShell(), _sqlEditor.getConnectionInfo());
        if (dlg.open() != IDialogConstants.CANCEL_ID)
        {
        	_sqlEditor.setConnectionInfo(dlg.getConnectionInfo());
        }
    }

}
