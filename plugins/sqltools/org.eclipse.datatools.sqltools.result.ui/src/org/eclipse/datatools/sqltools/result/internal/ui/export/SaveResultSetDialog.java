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

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.help.ContextProviderDelegate;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.result.IResultSetObject;
import org.eclipse.datatools.sqltools.result.export.AbstractOutputter;
import org.eclipse.datatools.sqltools.result.export.IResultConstants;
import org.eclipse.datatools.sqltools.result.internal.export.IOutputterDescriptor;
import org.eclipse.datatools.sqltools.result.internal.export.OutputterConstants;
import org.eclipse.datatools.sqltools.result.internal.ui.Messages;
import org.eclipse.datatools.sqltools.result.internal.ui.utils.Images;
import org.eclipse.datatools.sqltools.result.internal.utils.ILogger;
import org.eclipse.datatools.sqltools.result.model.IResultInstance;
import org.eclipse.datatools.sqltools.result.ui.IHelpConstants;
import org.eclipse.datatools.sqltools.result.ui.ResultsViewUIPlugin;
import org.eclipse.help.IContext;
import org.eclipse.help.IContextProvider;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

/**
 * An UI class that is used to save the result set or all result sets in a result instance into an Eclipse project file
 * 
 * @author Dafan Yang
 */
public class SaveResultSetDialog extends TitleAreaDialog implements IContextProvider
{
    private static ILogger   _log           = ResultsViewUIPlugin.getLogger(null);
    private IFile            _originalFile  = null;
    private String           _originalName  = null;
    private IPath            _result;
    private IResultSetObject _resultset;
    private IResultInstance  _resultInstance;

    private SaveResultGroup  _resourceGroup;
    private Button           _okButton;

    /**
     * Image for title area
     */
    private Image            _dlgTitleImage = null;
    
    private boolean          _first         = true;
    private Listener         _listener      = new Listener()
    {
        public void handleEvent(Event event)
        {
            if (_resourceGroup != null)
            {
                IStatus status = _resourceGroup.getStatus();
                if (status.getSeverity() == IStatus.ERROR)
                {
                    SaveResultSetDialog.this.setMessage(status.getMessage(), IMessageProvider.ERROR);
                    SaveResultSetDialog.this.getButton(IDialogConstants.OK_ID).setEnabled(false);
                }
                else
                {
                    SaveResultSetDialog.this.setMessage(null);
                    SaveResultSetDialog.this.getButton(IDialogConstants.OK_ID).setEnabled(true);
                }
            }
        }
    };
    
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
     * Creates a new Save As dialog (Used to save a result set object)
     * 
     * @param parentShell the parent shell
     * @param resultset the result set object
     */
    public SaveResultSetDialog(Shell parentShell, IResultSetObject resultset)
    {
        super(parentShell);
        _resultset = resultset;
    }

    /**
     * Creates a new Save As dialog (Used to save all the result set objects in a result instance)
     * 
     * @param parentShell the parent shell
     * @param result the result instance
     */
    public SaveResultSetDialog(Shell parentShell, IResultInstance result)
    {
        super(parentShell);
        _resultInstance = result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
     */
    protected void configureShell(Shell shell)
    {
        super.configureShell(shell);

        if (_resultInstance != null)
        {
            shell.setText(Messages.SaveResultSetDialog_saveAllResults_text);  
        }
        else
        {
            shell.setText(Messages.SaveResultSetDialog_saveResult_text);  
        }

    }

    /*
     *  (non-Javadoc)
     * @see org.eclipse.jface.window.Window#createContents(org.eclipse.swt.widgets.Composite)
     */
    protected Control createContents(Composite parent)
    {
        Control contents = super.createContents(parent);

        initializeControls();
        validatePage();
        _resourceGroup.setFocus();
        if (_resultInstance != null)
        {
            setTitle(Messages.SaveResultSetDialog_saveAllResults_title);  
        }
        else
        {
            setTitle(Messages.SaveResultSetDialog_saveResult_title);  
        }

        _dlgTitleImage = Images.get(Images.IMG_EXPORT_RESULT);
        setTitleImage(_dlgTitleImage);
        _first = false;

        return contents;
    }
    
    /*
     *  (non-Javadoc)
     * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
     */
    protected void createButtonsForButtonBar(Composite parent)
    {
        _okButton = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
        createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
    }
    
    /*
     *  (non-Javadoc)
     * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    protected Control createDialogArea(Composite parent)
    {
        getShell().setData(HelpUtil.CONTEXT_PROVIDER_KEY, this);
        HelpUtil.setHelp(parent, HelpUtil.getContextId(IHelpConstants.DIALOG_SAVE_RESULTSET, ResultsViewUIPlugin.getDefault().getBundle().getSymbolicName()));
        
        // top level composite
        Composite parentComposite = (Composite) super.createDialogArea(parent);

        // create a composite with standard margins and spacing
        Composite composite = new Composite(parentComposite, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.marginHeight = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
        layout.marginWidth = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
        layout.verticalSpacing = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
        layout.horizontalSpacing = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
        composite.setLayout(layout);
        composite.setLayoutData(new GridData(GridData.FILL_BOTH));
        composite.setFont(parentComposite.getFont());

        Listener listener = new Listener()
        {
            public void handleEvent(Event event)
            {
                setDialogComplete(validatePage());
            }
        };

        if (_resultset != null)
        {
            _resourceGroup = new SaveResultGroup(composite, listener, Messages.SaveResultSetDialog_filename, //$NON-NLS-1$
                    "file", _resultset, _listener); //$NON-NLS-1$
        }
        else if (_resultInstance != null)
        {
            _resourceGroup = new SaveResultGroup(composite, listener, Messages.SaveResultSetDialog_filename, //$NON-NLS-1$
                    "file", _resultInstance, _listener); //$NON-NLS-1$
        }

        _resourceGroup.setAllowExistingResources(true);

        return parentComposite;
    }

    /**
     * Returns the full path entered by the user.
     * <p>
     * Note that the file and container might not exist and would need to be created. See the <code>IFile.create</code>
     * method and the <code>ContainerGenerator</code> class.
     * </p>
     * 
     * @return the path, or <code>null</code> if Cancel was pressed
     */
    public IPath getResult()
    {
        return _result;
    }

    /**
     * Initializes the controls of this dialog.
     */
    private void initializeControls()
    {
        if (_originalFile != null)
        {
            _resourceGroup.setContainerFullPath(_originalFile.getParent().getFullPath());
            _resourceGroup.setResource(_originalFile.getName());
        }
        else if (_originalName != null)
        {
            _resourceGroup.setResource(_originalName);
        }

        setDialogComplete(validatePage());
    }

    protected void okPressed()
    {
        String filename = _resourceGroup.getResource();
        
        IOutputterDescriptor descriptor = _resourceGroup.getOutputterDesp();
        IPath path = _resourceGroup.getContainerFullPath().append(filename);

        /**
         * If the user does not supply a file extension, add a file extension based on the format
         */
        if (path.getFileExtension() == null)
        {            
            if (filename != null)
            {
                int pos = filename.lastIndexOf('.');
                if (pos < 0)
                {
                    filename = filename + "." + descriptor.getFileExtension();
                    path = path.addFileExtension(descriptor.getFileExtension());
                }
            }
        }

        // If the path already exists then confirm overwrite.
        IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);

        if (file.exists())
        {
            String[] buttons = new String[]
            {
                IDialogConstants.YES_LABEL, IDialogConstants.NO_LABEL, IDialogConstants.CANCEL_LABEL
            };
            String question = NLS.bind(Messages.SaveResultSetDialog_overwrite, new Object[] 
			{
			    path.toOSString()
			});
            MessageDialog d = new MessageDialog(getShell(), Messages.SaveResultSetDialog_question,  
                    null, question, MessageDialog.QUESTION, buttons, 0);
            int overwrite = d.open();
            switch (overwrite)
            {
                case 0: // Yes
                    break;
                case 1: // No
                    return;
                case 2: // Cancel
                default:
                    cancelPressed();
                    return;
            }
        }

        // Store path and close.
        _result = path;

        try
        {
            Properties options = new Properties();
            String resource = _resourceGroup.getContainerFullPath().toString();
            if (ResourcesPlugin.getWorkspace().getRoot().findMember(resource) != null)
            {

                String fullpath = ResourcesPlugin.getWorkspace().getRoot().findMember(resource).getLocation()
                        .toOSString()
                        + File.separator + filename;

                AbstractOutputter outputter = descriptor.getOutputter();
                if (_resourceGroup.getDelimiter().equals(OutputterConstants.USER_DEFINED))
                {
                    options.setProperty(IResultConstants.USERDEFINED_DELIMITER, _resourceGroup
                            .getUserDefinedDelimiter());
                }
                options.setProperty(IResultConstants.DELIMITER, _resourceGroup.getDelimiter());
                options.setProperty(IResultConstants.ENCODING, _resourceGroup.getEncoding());
                
                if (_resultset != null)
                {
                    outputter.output(_resultset, options, fullpath);
                }
                if (_resultInstance != null)
                {
                    outputter.output(_resultInstance, options, fullpath);
                }
                    
                try
                {
                    if (file != null && file.getProject() != null)
                    {
                        file.getProject().refreshLocal(IResource.DEPTH_INFINITE, null);
                        // set the correct charset for this file
                        file.setCharset(_resourceGroup.getEncoding(), new NullProgressMonitor());
                        
                        IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
                        IDE.openEditor(page, file, true);
                    }

                }
                catch (CoreException e)
                {
                    _log.error("common_error", e);  //$NON-NLS-1$
                }
            }
        }
        catch (IOException ex)
        {
            ErrorDialog.openError(getShell(), Messages.ResultExportWizard_export_error,
                    Messages.ResultExportWizard_failed_to_export_result_set, new Status(IStatus.ERROR,
                            ResultsViewUIPlugin.getPluginId(), 0, ex.getMessage(), ex));
        }
        close();
    }

    /**
     * Sets the completion state of this dialog and adjusts the enable state of the Ok button accordingly.
     * 
     * @param value <code>true</code> if this dialog is compelete, and <code>false</code> otherwise
     */
    protected void setDialogComplete(boolean value)
    {
        _okButton.setEnabled(value);
    }

    /**
     * Sets the original file to use.
     * 
     * @param originalFile the original file
     */
    public void setOriginalFile(IFile originalFile)
    {
        this._originalFile = originalFile;
    }

    /**
     * Set the original file name to use. Used instead of <code>setOriginalFile</code> when the original resource is
     * not an IFile. Must be called before <code>create</code>.
     * 
     * @param originalName default file name
     */
    public void setOriginalName(String originalName)
    {
        this._originalName = originalName;
    }

    /**
     * Returns whether this page's visual components all contain valid values.
     * 
     * @return <code>true</code> if valid, and <code>false</code> otherwise
     */
    private boolean validatePage()
    {
        setErrorMessage(null);
        setMessage(Messages.SaveResultSetDialog_message);  

        if (!_resourceGroup.areAllValuesValid())
        {
            if (_resourceGroup.getProblemType() == ResourceAndContainerGroup.PROBLEM_RESOURCE_EMPTY
                    || _resourceGroup.getProblemType() == ResourceAndContainerGroup.PROBLEM_CONTAINER_EMPTY)
            {
                // according to eclipse ui guideline, we shall start a wizard/dialog with a prompt, not an error
                // because he/she hasn't done anything yet.
                if (_first)
                {
                    setMessage(_resourceGroup.getProblemMessage());
                    setErrorMessage(null);
                }
                else
                {
                    setErrorMessage(_resourceGroup.getProblemMessage());
                }
            }
            else
            {
                setErrorMessage(_resourceGroup.getProblemMessage());
            }
            return false;
        }

        return true;
    }
}
