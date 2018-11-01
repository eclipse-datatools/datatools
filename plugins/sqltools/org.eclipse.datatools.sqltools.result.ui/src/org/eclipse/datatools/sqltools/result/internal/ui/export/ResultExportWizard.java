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
package org.eclipse.datatools.sqltools.result.internal.ui.export;

import java.util.Properties;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.datatools.help.ContextProviderDelegate;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.result.IResultSetObject;
import org.eclipse.datatools.sqltools.result.export.AbstractOutputter;
import org.eclipse.datatools.sqltools.result.export.IResultConstants;
import org.eclipse.datatools.sqltools.result.internal.export.OutputterConstants;
import org.eclipse.datatools.sqltools.result.internal.ui.Messages;
import org.eclipse.datatools.sqltools.result.internal.ui.utils.Images;
import org.eclipse.datatools.sqltools.result.model.IResultInstance;
import org.eclipse.datatools.sqltools.result.ui.IHelpConstants;
import org.eclipse.datatools.sqltools.result.ui.ResultsViewUIPlugin;
import org.eclipse.help.IContext;
import org.eclipse.help.IContextProvider;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

/**
 * The wizard to export result set(s) into an external file
 * 
 * @author Dafan Yang 
 */
public class ResultExportWizard extends Wizard implements IContextProvider
{
    ResultFormatWizardPage _formatPage;
    IResultSetObject        _resultObject;
    IResultInstance        _instance;

    private ContextProviderDelegate contextProviderDelegate = new ContextProviderDelegate(ResultsViewUIPlugin.getDefault().getBundle().getSymbolicName());
    
    public IContext getContext(Object target)
    {
        return contextProviderDelegate.getContext(target);
    }

    public int getContextChangeMask()
    {
        return contextProviderDelegate.getContextChangeMask();
    }

    public String getSearchExpression(Object target)
    {
        return contextProviderDelegate.getSearchExpression(target);
    }
    
    /**
     * Will be used to export a result set object
     * 
     * @param result the result set object
     */
    public ResultExportWizard(IResultSetObject result)
    {
        super();
        this._resultObject = result;
        this.setWindowTitle(Messages.ResultExportWizard_exportResult_title); 
        _formatPage = new ResultFormatWizardPage(result);
        _formatPage.setImageDescriptor(Images.DESC_EXPORT_RESULT);
    }

    /**
     * Will be used to export all result set objects in a result instance
     * 
     * @param result the result instance
     */
    public ResultExportWizard(IResultInstance result)
    {
        super();
        this._instance = result;
        this.setWindowTitle(Messages.ResultExportWizard_exportAllResults_title); 
        _formatPage = new ResultFormatWizardPage(result);
    }

    public void addPages()
    {
        _formatPage.setImageDescriptor(Images.DESC_EXPORT_RESULT);
        this.addPage(_formatPage);
    }

    public boolean performFinish()
    {
        final String pathName = _formatPage.getFileName();
        if (pathName == null || pathName.length() < 1)
        {
            return false;
        }
        
        //The user may not have made this absolute so do it for them        
        IPath path = (new Path(pathName)).makeAbsolute();

        if (path.toFile().exists())
        {
            String[] buttons = new String[]
            {
                IDialogConstants.YES_LABEL, IDialogConstants.NO_LABEL, IDialogConstants.CANCEL_LABEL
            }
            ;

            String question = NLS.bind(Messages.ResultExportWizard_overwrite, new Object[] 
			{
			    path.toOSString()
			});

            MessageDialog d = new MessageDialog(getShell(), Messages.ResultExportWizard_question, null, 
                    question, MessageDialog.QUESTION, buttons, 0);
            
            int overwrite = d.open();
            switch (overwrite)
            {
                case 0: // Yes
                    break;
                case 1: // No
                    return false;
                case 2: // Cancel
                default:                    
                    return false;
            }
        }

        try
        {
            final Properties options = new Properties();

            final AbstractOutputter outputter = _formatPage.getOutputterDesp().getOutputter();
            if(_formatPage.getDelimiter().equals(OutputterConstants.USER_DEFINED))
            {
                options.setProperty(IResultConstants.USERDEFINED_DELIMITER, _formatPage.getUserDefinedDelimiter());
            }
            options.setProperty(IResultConstants.DELIMITER, _formatPage.getDelimiter());
            options.setProperty(IResultConstants.ENCODING, _formatPage.getEncoding());

            if (_resultObject != null)
            {
                Runnable exportRunnable = new Runnable()
                {
                    public void run()
                    {
                        try
                        {
                            outputter.output(_resultObject, options, pathName);
                        }
                        catch (final Exception e)
                        {
                            Display display = getContainer() != null ? getContainer().getShell().getDisplay() :
                                ResultsViewUIPlugin.getDefault().getWorkbench().getDisplay();
                            
                            display.syncExec(new Runnable()
                            {
                                public void run()
                                {
                                    ErrorDialog.openError(getShell(), Messages.ResultExportWizard_export_error,
                                            Messages.ResultExportWizard_failed_to_export_result_set, new Status(
                                                    IStatus.ERROR, ResultsViewUIPlugin.PLUGIN_ID, 0, e.getMessage(),
                                                    e));
                                }
                            });
                        }
                    }
                };
                Thread exportThread = new Thread(exportRunnable);
                exportThread.start();
            }

            if (_instance != null)
            {
                Runnable exportRunnable = new Runnable()
                {
                    public void run()
                    {
                        try
                        {
                            outputter.output(_instance, options, pathName);
                        }
                        catch (final Exception e)
                        {
                            Display display = getContainer() != null ? getContainer().getShell().getDisplay() :
                                ResultsViewUIPlugin.getDefault().getWorkbench().getDisplay();
                            
                            display.syncExec(new Runnable()
                            {
                                public void run()
                                {
                                    ErrorDialog.openError(getShell(), Messages.ResultExportWizard_export_error,
                                            Messages.ResultExportWizard_failed_to_export_result_set, new Status(
                                                    IStatus.ERROR, ResultsViewUIPlugin.PLUGIN_ID, 0, e.getMessage(),
                                                    e));
                                }
                            });
                        }
                    }
                };
                Thread exportThread = new Thread(exportRunnable);
                exportThread.start();
            }
            return true;
        }
        catch (Exception ex)
        {
            ErrorDialog
                    .openError(
                            getShell(),
                            Messages.ResultExportWizard_export_error, Messages.ResultExportWizard_failed_to_export_result_set, new Status(IStatus.ERROR, 
                                    ResultsViewUIPlugin.PLUGIN_ID, 0, ex.getMessage(), ex));
            return false;
        }
    }
    
    class ExportJob extends Job
    {
        public ExportJob(String name)
        {
            super(name);
        }

        protected IStatus run(IProgressMonitor monitor)
        {
            return null;
        }
    }

    public void createPageControls(Composite pageContainer)
    {
        super.createPageControls(pageContainer);
        
        getShell().setData(HelpUtil.CONTEXT_PROVIDER_KEY, this);
        HelpUtil.setHelp(pageContainer, HelpUtil.getContextId(IHelpConstants.WIZARD_RESULT_EXPORT, ResultsViewUIPlugin.getDefault().getBundle().getSymbolicName()));
    }
}
