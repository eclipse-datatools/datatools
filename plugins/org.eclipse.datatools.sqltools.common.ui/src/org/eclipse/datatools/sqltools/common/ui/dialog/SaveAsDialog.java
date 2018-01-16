/**
 * Created on 2005-1-18
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.common.ui.dialog;

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
import org.eclipse.datatools.sqltools.common.ui.internal.Activator;
import org.eclipse.datatools.sqltools.common.ui.internal.IHelpContextIds;
import org.eclipse.datatools.sqltools.common.ui.internal.Messages;
import org.eclipse.datatools.sqltools.common.ui.resource.ResourceAndContainerGroup;
import org.eclipse.datatools.sqltools.common.ui.util.Images;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
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
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

/**
 * A standard "Save As" dialog which solicits a path from the user. The <code>getResult</code> method returns the
 * path. Note that the folder at the specified path might not exist and might need to be created.
 * 
 * <p>
 * This class differs from org.eclipse.ui.dialogs.SaveAsDialog in that it allows user to create a project.
 * </p>
 * 
 * @see org.eclipse.ui.dialogs.ContainerGenerator
 * @author Hui Cao
 */
public class SaveAsDialog extends TitleAreaDialog
{
    private IFile                     _originalFile  = null;
    private String                    _originalName  = null;
    private IPath                     _result;

    // widgets
    private ResourceAndContainerGroup _resourceGroup;
    private Button                    _okButton;

    /**
     * Image for title area
     */
    private Image                     _dlgTitleImage = null;
    private boolean                   _first         = true;
    private String                    _content;
    private static final String       ENCODING       = "UTF-8";
    private IEditorPart               _editor;
    private boolean                   _execSaveAs = true;
    
    private boolean                   _openMode = true;

    /**
     * Creates a new Save As dialog for no specific file.
     * 
     * @param parentShell the parent shell
     */
    public SaveAsDialog(Shell parentShell, boolean execSaveAs)
    {
        super(parentShell);
        _execSaveAs = execSaveAs;
    }
    
    /**
     * Creates a new Save As dialog for no specific file.
     * 
     * @param parentShell the parent shell
     */
    public SaveAsDialog(Shell parentShell)
    {
        super(parentShell);
    }

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
     * (non-Javadoc) Method declared in Window.
     */
    protected void configureShell(Shell shell)
    {
        super.configureShell(shell);
        shell.setText(Messages.SaveAsDialog_text); //$NON-NLS-1$
        PlatformUI.getWorkbench().getHelpSystem().setHelp(shell, IHelpContextIds.SAVE_AS_DIALOG);
    }

    /*
     * (non-Javadoc) Method declared in Window.
     */
    protected Control createContents(Composite parent)
    {

        Control contents = super.createContents(parent);

        initializeControls();
        validatePage();
        _resourceGroup.setFocus();
        setTitle(Messages.SaveAsDialog_title); //$NON-NLS-1$
        _dlgTitleImage = Images.get(Images.IMG_SAVEAS);
        setTitleImage(_dlgTitleImage);
        _first = false;

        return contents;
    }

    /**
     * The <code>SaveAsDialog</code> implementation of this <code>Window</code> method disposes of the banner image
     * when the dialog is closed.
     */
    public boolean close()
    {
        if (_dlgTitleImage != null)
        {
            //            _dlgTitleImage.dispose();
        }
        return super.close();
    }

    /*
     * (non-Javadoc) Method declared on Dialog.
     */
    protected void createButtonsForButtonBar(Composite parent)
    {
        _okButton = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
        createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
    }

    /*
     * (non-Javadoc) Method declared on Dialog.
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
        }
        ;

        _resourceGroup = new ResourceAndContainerGroup(composite, listener, Messages.SaveAsDialog_filename, Messages.SaveAsDialog_file); //$NON-NLS-2$ //$NON-NLS-1$
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
        // Get new path.
        String filename = _resourceGroup.getResource();
        IPath path = _resourceGroup.getContainerFullPath().append(filename);

        //If the user does not supply a file extension and if the save
        //as dialog was provided a default file name append the extension
        //of the default filename to the new name
        if (path.getFileExtension() == null)
        {
            if (_originalFile != null && _originalFile.getFileExtension() != null)
            {
                path = path.addFileExtension(_originalFile.getFileExtension());
            }
            else if (_originalName != null)
            {
                int pos = _originalName.lastIndexOf('.');
                if (++pos > 0 && pos < _originalName.length())
                {
                    path = path.addFileExtension(_originalName.substring(pos));
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
            }
            ;
            String question = Messages.bind(Messages.SaveAsDialog_overwriteQuestion, //$NON-NLS-1$
            new Object[]
            {
                path.toOSString()
            }
            );
            MessageDialog d = new MessageDialog(getShell(), Messages.Question, //$NON-NLS-1$
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
        
        if(!_execSaveAs)
        {
            close();
            return;
        }
        
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

                        if(_openMode)
                        {
                            IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
                            _editor = IDE.openEditor(page, file, true);
                        }
                    }
                }
                catch (CoreException e)
                {
                    Activator.getDefault().log(Messages.SaveAsDialog_error_msg, e); //$NON-NLS-1$
                }
            }
        }
        catch (IOException ex)
        {
            ErrorDialog.openError(getShell(), Messages.SaveAsDialog_error, Messages.SaveAsDialog_error_msg, new Status(
                IStatus.ERROR, Activator.PLUGIN_ID, 0, ex.getMessage(), ex));
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
        setMessage(Messages.SaveAsDialog_message); //$NON-NLS-1$

        if (!_resourceGroup.areAllValuesValid())
        {
            if (_resourceGroup.getProblemType() == ResourceAndContainerGroup.PROBLEM_RESOURCE_EMPTY
            || _resourceGroup.getProblemType() == ResourceAndContainerGroup.PROBLEM_CONTAINER_EMPTY)
            {
                //according to eclipse ui guideline, we shall start a wizard/dialog with a prompt, not an error
                //because he/she hasn't done anything yet.
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

    public IEditorPart getEditor()
    {
        return _editor;
    }
    
    public void setOpenMode(boolean openMode)
    {
        _openMode = openMode;
    }
}
