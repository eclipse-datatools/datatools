/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal.ui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal.SOEUIPlugin;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.util.ILogger;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.util.Images;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IDialogConstants;
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
 * @author Idull
 */
public class SaveAsDialog extends TitleAreaDialog
{
    private static ILogger            _log           = SOEUIPlugin.getLogger(null);
    private IFile                     _originalFile  = null;
    private String                    _originalName  = null;
    private IPath                     _result;

    private ResourceAndContainerGroup _resourceGroup;
    private Button                    _okButton;
    private String                    _content;
    private static final String       ENCODING       = "UTF-8";
    /**
     * Image for title area
     */
    private Image                     _dlgTitleImage = null;

    private boolean                   _first         = true;
    private Listener                  _listener      = new Listener()
                                                     {
                                                         public void handleEvent(Event event)
                                                         {
                                                             if (_resourceGroup != null)
                                                             {
                                                                 boolean valid = _resourceGroup.validateContainer();
                                                                 if (!valid)
                                                                 {
                                                                     SaveAsDialog.this
                                                                             .getButton(IDialogConstants.OK_ID)
                                                                             .setEnabled(false);
                                                                 }
                                                                 else
                                                                 {
                                                                     SaveAsDialog.this.setMessage(null);
                                                                     SaveAsDialog.this
                                                                             .getButton(IDialogConstants.OK_ID)
                                                                             .setEnabled(true);
                                                                 }
                                                             }
                                                         }
                                                     };

    /**
     * Creates a new Save As dialog (Used to save a result set object)
     * 
     * @param parentShell the parent shell
     */
    public SaveAsDialog(Shell parentShell, String content)
    {
        super(parentShell);
        this._content = content;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
     */
    protected void configureShell(Shell shell)
    {
        super.configureShell(shell);
        shell.setText(Messages.SaveAsDialog_title);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.window.Window#createContents(org.eclipse.swt.widgets.Composite)
     */
    protected Control createContents(Composite parent)
    {
        Control contents = super.createContents(parent);

        initializeControls();
        validatePage();
        _resourceGroup.setFocus();

        setTitle(Messages.SaveAsDialog_title);
        setMessage(Messages.SaveAsDialog_message);
        _dlgTitleImage = Images.get(Images.IMG_SAVEAS);
        setTitleImage(_dlgTitleImage);
        _first = false;

        return contents;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
     */
    protected void createButtonsForButtonBar(Composite parent)
    {
        _okButton = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
        createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    protected Control createDialogArea(Composite parent)
    {
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
        _resourceGroup = new ResourceAndContainerGroup(composite, listener, "", "file", _listener); //$NON-NLS-1$ //$NON-NLS-2$
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

    /*
     * (non-Javadoc) Method declared on Dialog.
     */
    protected void okPressed()
    {
        String filename = _resourceGroup.getResource();
        IPath path = _resourceGroup.getContainerFullPath().append(filename);

        // If the path already exists then confirm overwrite.
        IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);

        if (file.exists())
        {
            String[] buttons = new String[]
            {
                IDialogConstants.YES_LABEL, IDialogConstants.NO_LABEL, IDialogConstants.CANCEL_LABEL
            };
            String question = NLS.bind(Messages.SaveAsDialog_overwrite, new Object[]
            {
                path.toOSString()
            });
            MessageDialog d = new MessageDialog(getShell(), Messages.SaveAsDialog_question, null, question,
                    MessageDialog.QUESTION, buttons, 0);
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
            String resource = _resourceGroup.getContainerFullPath().toString();
            if (ResourcesPlugin.getWorkspace().getRoot().findMember(resource) != null)
            {

                String fullpath = ResourcesPlugin.getWorkspace().getRoot().findMember(resource).getLocation()
                        .toOSString()
                        + File.separator + filename;
                PrintWriter output = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fullpath), ENCODING)); //$NON-NLS-1$
                if (_content != null)
                {
                    output.write(_content);
                }
                if (output != null)
                {
                    output.close();
                }
                try
                {
                    if (file != null && file.getProject() != null)
                    {
                        file.getProject().refreshLocal(IResource.DEPTH_INFINITE, null);
                        // set the correct charset for this file
                        file.setCharset(ENCODING, new NullProgressMonitor()); //$NON-NLS-1$

                        IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
                        IDE.openEditor(page, file, true);
                    }

                }
                catch (CoreException e)
                {
                    _log.error("SaveAsDialog_open_error", e); //$NON-NLS-1$
                }
            }
        }
        catch (IOException ex)
        {
            ErrorDialog.openError(getShell(), Messages.SaveAsDialog_error, Messages.SaveAsDialog_error_msg, new Status(
                    IStatus.ERROR, SOEUIPlugin.PLUGIN_ID, 0, ex.getMessage(), ex));
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
     * Set the original file name to use. Used instead of <code>setOriginalFile</code> when the original resource is not
     * an IFile. Must be called before <code>create</code>.
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
        setMessage(Messages.SaveAsDialog_message);

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
