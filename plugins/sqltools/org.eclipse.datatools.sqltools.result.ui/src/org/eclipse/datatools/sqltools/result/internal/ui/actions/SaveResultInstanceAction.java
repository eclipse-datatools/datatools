/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.result.internal.ui.actions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Iterator;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.result.internal.ui.Messages;
import org.eclipse.datatools.sqltools.result.internal.ui.PreferenceConstants;
import org.eclipse.datatools.sqltools.result.internal.ui.view.ColumnAlignedResultItem;
import org.eclipse.datatools.sqltools.result.internal.utils.ILogger;
import org.eclipse.datatools.sqltools.result.internal.utils.StatusTextProvider;
import org.eclipse.datatools.sqltools.result.model.IResultInstance;
import org.eclipse.datatools.sqltools.result.ui.IHelpConstants;
import org.eclipse.datatools.sqltools.result.ui.ResultsViewUIPlugin;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.IUpdate;

/**
 * @author Dafan Yang
 */
public class SaveResultInstanceAction extends Action implements IUpdate
{
    private static ILogger _log = ResultsViewUIPlugin.getLogger(null);
    Shell                  _shell;
    ISelectionProvider     _provider;
    
    private static String OUTER_SEPARATOR_LINE = "=======================================================================================";     //$NON-NLS-1$
    private static String INNER_SEPARATOR_LINE = "---------------------------------------------------------------------------------------";     //$NON-NLS-1$

    /**
     * 
     */
    public SaveResultInstanceAction(Shell shell, ISelectionProvider selectionProvider)
    {
        super(Messages.SaveResultInstanceAction_save_history); 
        this._shell = shell;
        this._provider = selectionProvider;
        
        PlatformUI.getWorkbench().getHelpSystem().setHelp(this, HelpUtil.getContextId(IHelpConstants.ACTION_SAVE_RESULT_INSTANCE, ResultsViewUIPlugin.getDefault().getBundle().getSymbolicName()));
    }

    public void update()
    {
        IStructuredSelection selection = (IStructuredSelection) _provider.getSelection();
        if (selection == null)
        {
            setEnabled(false);
            return;
        }
        Object[] obj = selection.toArray();
        if (obj == null || obj.length == 0 || obj.length > 1)
        {
            setEnabled(false);
            return;
        }
        if (!(obj[0] instanceof IResultInstance))
        {
            setEnabled(false);
            return;
        }
        setEnabled(true);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    public void run()
    {
        IStructuredSelection selection = (IStructuredSelection) _provider.getSelection();
        Object[] obj = selection.toArray();
        if (obj == null || obj.length == 0 || obj.length > 1)
        {
            // should not happen
            return;
        }
        IResultInstance instance = (IResultInstance) obj[0];
        FileDialog dialog = new FileDialog(_shell, SWT.SAVE);
        dialog.setText(Messages.SaveResultInstanceAction_save_hisotry_title); 

        String filename = ""; //$NON-NLS-1$
        boolean selectAgain = false;
        do
        {
            filename = dialog.open();
            if (filename == null)
            {
                return;
            }
            File file = new File(filename);
            if (file.exists())
            {
                String[] buttons = new String[]
                {
                    IDialogConstants.YES_LABEL, IDialogConstants.NO_LABEL, IDialogConstants.CANCEL_LABEL
                };
                String question = NLS.bind(Messages.ResultExportWizard_overwrite, new Object[] 
				{
				    filename
				});
                MessageDialog d = new MessageDialog(_shell, Messages.ResultExportWizard_question, 
                        null, question, MessageDialog.QUESTION, buttons, 0);
                int overwrite = d.open();
                switch (overwrite)
                {
                    case 0: // Yes
                        selectAgain = false;
                        break;
                    case 1: // No
                        selectAgain = true;
                        break;
                    case 2: // Cancel
                    default:
                        return;
                }
            }
        }
        while (selectAgain);

        try
        {
            FileOutputStream fos = new FileOutputStream(new File(filename));
            PrintWriter w = new PrintWriter(new BufferedWriter((new OutputStreamWriter(fos, "UTF-8"))));
            printResultInstance(w, instance, "", true);
            w.close();
        }
        catch (IOException ex)
        {
            _log.error("SaveResultInstanceAction_cant_export_result_log", ex); //$NON-NLS-1$
            ErrorDialog
                    .openError(
                            _shell,
                            Messages.SaveResultInstanceAction_save_error, Messages.SaveResultInstanceAction_can_not_save, new Status(IStatus.ERROR, ResultsViewUIPlugin
                                            .PLUGIN_ID, 0, ex.getMessage(), ex));
        }
    }

    /**
     * Recursively print the IResultInstance object and its sub results to the output stream.
     *  
     * @param w                 - the output stream
     * @param instance          - the IResultInstance object to be printed
     * @param prefix            - the prefix to control the indent format
     * @param showFullHeader    - true to print full header, otherwise simple header
     */
    private void printResultInstance(PrintWriter w, IResultInstance instance, String prefix, boolean showFullHeader)
    {
        if (showFullHeader)
        {
            w.print(prefix + addPrefix(StatusTextProvider.getHistoryHeader(instance), prefix));
        }
        else
        {
            w.print(prefix + addPrefix(StatusTextProvider.getSimpleHeader(instance), prefix));
        }
        w.println(INNER_SEPARATOR_LINE);
        w.println(prefix);
        String dispStr = ColumnAlignedResultItem.getResultInstanceDispString(instance, ResultsViewUIPlugin.getDefault()
                .getPreferenceStore().getString(PreferenceConstants.SQL_RESULTS_VIEW_NULL_STRING));
        w.print(prefix + addPrefix(dispStr, prefix));

        prefix += "\t";     //$NON-NLS-1$
        Iterator it = instance.getSubResults().iterator();
        while (it.hasNext())
        {
            IResultInstance subInstance = (IResultInstance)it.next();
            if (subInstance != null)
            {
                w.println(prefix);
                w.println(prefix + OUTER_SEPARATOR_LINE);
                printResultInstance(w, subInstance, prefix, false);
            }
        }
    }
    
    /**
     * Add the content of prefix to the beginning of every line of the input text.
     */
    private String addPrefix(String text, String prefix)
    {
        return text.replaceAll("\n", "\n" + prefix);        //$NON-NLS-1$
    }
}
