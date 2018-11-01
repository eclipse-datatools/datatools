/*******************************************************************************
 * Copyright (c) 2005 -- 2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.sqltools.common.ui.resource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.Iterator;

import org.eclipse.core.filesystem.URIUtil;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceStatus;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Preferences;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.datatools.sqltools.common.ui.internal.Activator;
import org.eclipse.datatools.sqltools.common.ui.internal.IHelpContextIds;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.ContainerGenerator;
import org.eclipse.ui.internal.ide.dialogs.CreateLinkedResourceGroup;

/**
 * 
 * @see org.eclipse.ui.dialogs.WizardNewFileCreationPage We choose to reimplement the class because we want to a new
 *      ResourceAndContainerGroup which can has a "create project..." button.
 * 
 * Standard main page for a wizard that creates a file resource.
 * <p>
 * This page may be used by clients as-is; it may be also be subclassed to suit.
 * </p>
 * <p>
 * Subclasses may override
 * <ul>
 * <li><code>getInitialContents</code></li>
 * <li><code>getNewFileLabel</code></li>
 * </ul>
 * </p>
 * <p>
 * Subclasses may extend
 * <ul>
 * <li><code>handleEvent</code></li>
 * </ul>
 * </p>
 * 
 * @author Hui Cao
 */
public class WizardNewFileCreationPage extends WizardPage implements Listener
{
    private static final int          _SIZING_CONTAINER_GROUP_HEIGHT = 200;
    private static final int          _SIZING_CONTAINER_GROUP_MEDIAM_HEIGHT = 100;
    private static final int          _SIZING_CONTAINER_GROUP_SMALL_HEIGHT = 60;
    // the current resource selection
    private IStructuredSelection      _currentSelection;

    // cache of newly-created file
    private IFile                     _newFile;
    private IPath                     _linkTargetPath;

    // widgets
    private ResourceAndContainerGroup _resourceGroup;
    private Button                    _advancedButton;
    private CreateLinkedResourceGroup _linkedResourceGroup;
    private Composite                 _linkedResourceParent;
    private Composite                 _linkedResourceComposite;

    // initial value stores
    private String                    _initialFileName;
    private IPath                     _initialContainerFullPath;
    /**
     * Height of the "advanced" linked resource group. Set when the advanced group is first made visible.
     */
    private int                       _linkedResourceGroupHeight     = -1;
    /**
     * First time the advanced group is validated.
     */
    private boolean                   _firstLinkCheck                = true;

    /**
     * Creates a new file creation wizard page. If the initial resource selection contains exactly one container
     * resource then it will be used as the default container resource.
     * 
     * @param pageName the name of the page
     * @param selection the current resource selection
     */
    public WizardNewFileCreationPage(String pageName, IStructuredSelection selection)
    {
        super(pageName);
        setPageComplete(false);
        this._currentSelection = selection;
    }

    /**
     * Creates the widget for advanced options.
     * 
     * @param parent the parent composite
     */
    protected void createAdvancedControls(Composite parent)
    {
        Preferences preferences = ResourcesPlugin.getPlugin().getPluginPreferences();

        if (preferences.getBoolean(ResourcesPlugin.PREF_DISABLE_LINKING) == false)
        {
            _linkedResourceParent = new Composite(parent, SWT.NONE);
            _linkedResourceParent.setFont(parent.getFont());
            _linkedResourceParent.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
            GridLayout layout = new GridLayout();
            layout.marginHeight = 0;
            layout.marginWidth = 0;
            _linkedResourceParent.setLayout(layout);

            _advancedButton = new Button(_linkedResourceParent, SWT.PUSH);
            _advancedButton.setFont(_linkedResourceParent.getFont());
            _advancedButton.setText(Messages.showAdvanced); //$NON-NLS-1$
            GridData data = setButtonLayoutData(_advancedButton);
            data.horizontalAlignment = GridData.BEGINNING;
            _advancedButton.setLayoutData(data);
            _advancedButton.addSelectionListener(new SelectionAdapter()
            {
                public void widgetSelected(SelectionEvent e)
                {
                    handleAdvancedButtonSelect();
                }
            }
            );
        }
        _linkedResourceGroup = new CreateLinkedResourceGroup(IResource.FILE, new Listener()
        {
            public void handleEvent(Event e)
            {
                setPageComplete(validatePage());
                _firstLinkCheck = false;
            }
        }, null
        );
    }

    /**
     * (non-Javadoc) Method declared on IDialogPage.
     */
    public void createControl(Composite parent)
    {
        initializeDialogUnits(parent);
        // top level group
        Composite topLevel = new Composite(parent, SWT.NONE);
        topLevel.setLayout(new GridLayout());
        topLevel.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_FILL | GridData.HORIZONTAL_ALIGN_FILL));
        topLevel.setFont(parent.getFont());
        PlatformUI.getWorkbench().getHelpSystem().setHelp(topLevel, IHelpContextIds.NEW_FILE_WIZARD_PAGE);//TODO: use dmp help id

        createPageControl(topLevel);

        validatePage();
        // Show description on opening
        setErrorMessage(null);
        setMessage(null);
        setControl(topLevel);
    }

    protected void createPageControl(Composite parent)
    {
        // resource and container group
        int height = _SIZING_CONTAINER_GROUP_HEIGHT;
        int screenHeight = Display.getDefault().getBounds().height;
        if (screenHeight <= 600)
        {
            height = _SIZING_CONTAINER_GROUP_SMALL_HEIGHT;
        }
        else if (screenHeight <= 768)
        {
            height = _SIZING_CONTAINER_GROUP_MEDIAM_HEIGHT;
        }
        _resourceGroup = new ResourceAndContainerGroup(parent, this, getNewFileLabel(), Messages
            .WizardNewFileCreationPage_file, false, height); //$NON-NLS-1$
        _resourceGroup.setAllowExistingResources(false);
        initialPopulateContainerNameField();
        createAdvancedControls(parent);
        if (_initialFileName != null)
        {
            _resourceGroup.setResource(_initialFileName);
        }
    }

    /**
     * Creates a file resource given the file handle and contents.
     * 
     * @param fileHandle the file handle to create a file resource with
     * @param contents the initial contents of the new file resource, or <code>null</code> if none (equivalent to an
     *            empty stream)
     * @param monitor the progress monitor to show visual progress with
     * @exception CoreException if the operation fails
     * @exception OperationCanceledException if the operation is canceled
     */
    protected void createFile(IFile fileHandle, InputStream contents, IProgressMonitor monitor) throws CoreException
    {
        if (contents == null)
        {
            contents = new ByteArrayInputStream(new byte[0]);
        }

        try
        {
            // Create a new file resource in the workspace
            if (_linkTargetPath != null)
            {
                fileHandle.createLink(_linkTargetPath, IResource.ALLOW_MISSING_LOCAL, monitor);
            }
            else
            {
                fileHandle.create(contents, false, monitor);
            }
        }
        catch (CoreException e)
        {
            // If the file already existed locally, just refresh to get contents
            if (e.getStatus().getCode() == IResourceStatus.PATH_OCCUPIED)
            {
                fileHandle.refreshLocal(IResource.DEPTH_ZERO, null);
            }
            else
            {
                throw e;
            }
        }

        if (monitor.isCanceled())
        {
            throw new OperationCanceledException();
        }
    }

    /**
     * Creates a file resource handle for the file with the given workspace path. This method does not create the file
     * resource; this is the responsibility of <code>createFile</code>.
     * 
     * @param filePath the path of the file resource to create a handle for
     * @return the new file resource handle
     * @see #createFile
     */
    protected IFile createFileHandle(IPath filePath)
    {
        return ResourcesPlugin.getWorkspace().getRoot().getFile(filePath);
    }

    /**
     * Creates the link target path if a link target has been specified.
     */
    protected void createLinkTarget()
    {
        try
        {
            //Eclipse 3.2 and before
            Method getLinkTarget = CreateLinkedResourceGroup.class.getMethod("getLinkTarget", null);
            String linkTarget;
            linkTarget = (String)getLinkTarget.invoke(_linkedResourceGroup, null);
            if (linkTarget != null)
            {
                _linkTargetPath = new Path(linkTarget);
            }
            else
            {
                _linkTargetPath = null;
            }
        }
        catch (NoSuchMethodException e)
        {
            // Eclipse 3.3
            try
            {
                //Eclipse 3.2 and before
                Method getLinkTargetURI = CreateLinkedResourceGroup.class.getMethod("getLinkTargetURI", null);
                URI linkTargetURI = (URI)getLinkTargetURI.invoke(_linkedResourceGroup, null);
                if (linkTargetURI != null)
                {
                    _linkTargetPath = URIUtil.toPath(linkTargetURI);
                }
                else
                {
                    _linkTargetPath = null;
                }
            }
            catch (Exception ee)
            {
                Activator.getDefault().log(
                        NLS.bind(Messages.WizardNewFileCreationPage_internalErrorMessage,  
                        getClass().getName(), ee
                    ));//$NON-NLS-1$

            }
        }
        catch (Exception e)
        {
            Activator.getDefault().log(
                    NLS.bind(Messages.WizardNewFileCreationPage_internalErrorMessage,  
                    getClass().getName(), e
                ));//$NON-NLS-1$

        }
    }

    /**
     * Creates a new file resource in the selected container and with the selected name. Creates any missing resource
     * containers along the path; does nothing if the container resources already exist.
     * <p>
     * In normal usage, this method is invoked after the user has pressed Finish on the wizard; the enablement of the
     * Finish button implies that all controls on on this page currently contain valid values.
     * </p>
     * <p>
     * Note that this page caches the new file once it has been successfully created; subsequent invocations of this
     * method will answer the same file resource without attempting to create it again.
     * </p>
     * <p>
     * This method should be called within a workspace modify operation since it creates resources.
     * </p>
     * 
     * @return the created file resource, or <code>null</code> if the file was not created
     */
    public IFile createNewFile()
    {
        if (_newFile != null)
        {
            return _newFile;
        }

        // create the new file and cache it if successful

        final IPath containerPath = _resourceGroup.getContainerFullPath();
        IPath newFilePath = containerPath.append(_resourceGroup.getResource());
        final IFile newFileHandle = createFileHandle(newFilePath);
        final InputStream initialContents = getInitialContents();

        createLinkTarget();
        WorkspaceModifyOperation op = new WorkspaceModifyOperation(null)
        {
            protected void execute(IProgressMonitor monitor) throws CoreException, InterruptedException
            {
                try
                {
                    monitor.beginTask(Messages.WizardNewFileCreationPage_progress, 2000); //$NON-NLS-1$
                    ContainerGenerator generator = new ContainerGenerator(containerPath);
                    generator.generateContainer(new SubProgressMonitor(monitor, 1000));
                    createFile(newFileHandle, initialContents, new SubProgressMonitor(monitor, 1000));
                }
                finally
                {
                    monitor.done();
                }
            }
        }
        ;

        try
        {
            getContainer().run(true, true, op);
        }
        catch (InterruptedException e)
        {
            return null;
        }
        catch (InvocationTargetException e)
        {
            if (e.getTargetException() instanceof CoreException)
            {
                ErrorDialog.openError(getContainer().getShell(), // Was Utilities.getFocusShell()
                Messages.WizardNewFileCreationPage_errorTitle, //$NON-NLS-1$
                null, // no special message
                ((CoreException) e.getTargetException()).getStatus());
            }
            else
            {
                // CoreExceptions are handled above, but unexpected runtime exceptions and errors may still occur.
                Activator.getDefault().log(
                    NLS.bind(Messages.WizardNewFileCreationPage_error_create_file,  
                    getClass().getName(), e.getTargetException()
                ));//$NON-NLS-1$
                MessageDialog
                    .openError(
                    getContainer().getShell(),
                    Messages.WizardNewFileCreationPage_internalErrorTitle, NLS.bind(Messages.WizardNewFileCreationPage_internalErrorMessage,  
                    e.getTargetException().getMessage()
                )); //$NON-NLS-2$ //$NON-NLS-1$
            }
            return null;
        }

        _newFile = newFileHandle;

        return _newFile;
    }

    /**
     * Returns the current full path of the containing resource as entered or selected by the user, or its anticipated
     * initial value.
     * 
     * @return the container's full path, anticipated initial value, or <code>null</code> if no path is known
     */
    public IPath getContainerFullPath()
    {
        return _resourceGroup.getContainerFullPath();
    }

    /**
     * Returns the current file name as entered by the user, or its anticipated initial value.
     * 
     * @return the file name, its anticipated initial value, or <code>null</code> if no file name is known
     */
    public String getFileName()
    {
        if (_resourceGroup == null)
        {
            return _initialFileName;
        }

        return _resourceGroup.getResource();
    }

    /**
     * Returns a stream containing the initial contents to be given to new file resource instances. <b>Subclasses </b>
     * may wish to override. This default implementation provides no initial contents.
     * 
     * @return initial contents to be given to new file resource instances
     */
    protected InputStream getInitialContents()
    {
        return null;
    }

    /**
     * Returns the label to display in the file name specification visual component group.
     * <p>
     * Subclasses may reimplement.
     * </p>
     * 
     * @return the label to display in the file name specification visual component group
     */
    protected String getNewFileLabel()
    {
        return Messages.WizardNewFileCreationPage_fileLabel;
    }

    /**
     * Shows/hides the advanced option widgets.
     */
    protected void handleAdvancedButtonSelect()
    {
        Shell shell = getShell();
        Point shellSize = shell.getSize();
        Composite composite = (Composite) getControl();

        if (_linkedResourceComposite != null)
        {
            _linkedResourceComposite.dispose();
            _linkedResourceComposite = null;
            composite.layout();
            shell.setSize(shellSize.x, shellSize.y - _linkedResourceGroupHeight);
            _advancedButton.setText(Messages.showAdvanced); //$NON-NLS-1$
        }
        else
        {
            _linkedResourceComposite = _linkedResourceGroup.createContents(_linkedResourceParent);
            if (_linkedResourceGroupHeight == -1)
            {
                Point groupSize = _linkedResourceComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
                _linkedResourceGroupHeight = groupSize.y;
            }
            shell.setSize(shellSize.x, shellSize.y + _linkedResourceGroupHeight);
            composite.layout();
            _advancedButton.setText(Messages.hideAdvanced); //$NON-NLS-1$
        }
    }

    /**
     * The <code>WizardNewFileCreationPage</code> implementation of this <code>Listener</code> method handles all
     * events and enablements for controls on this page. Subclasses may extend.
     */
    public void handleEvent(Event event)
    {
        setPageComplete(validatePage());
    }

    /**
     * Sets the initial contents of the container name entry field, based upon either a previously-specified initial
     * value or the ability to determine such a value.
     */
    protected void initialPopulateContainerNameField()
    {
        if (_initialContainerFullPath != null)
        {
            _resourceGroup.setContainerFullPath(_initialContainerFullPath);
        }
        else
        {
            Iterator i = _currentSelection.iterator();
            if (i.hasNext())
            {
                Object object = i.next();
                IResource selectedResource = null;
                if (object instanceof IResource)
                {
                    selectedResource = (IResource) object;
                }
                else if (object instanceof IAdaptable)
                {
                    selectedResource = (IResource) ((IAdaptable) object).getAdapter(IResource.class);
                }
                if (selectedResource != null)
                {
                    if (selectedResource.getType() == IResource.FILE)
                    {
                        selectedResource = selectedResource.getParent();
                    }
                    if (selectedResource.isAccessible())
                    {
                        _resourceGroup.setContainerFullPath(selectedResource.getFullPath());
                    }
                }
            }
        }
    }

    /**
     * Sets the value of this page's container name field, or stores it for future use if this page's controls do not
     * exist yet.
     * 
     * @param path the full path to the container
     */
    public void setContainerFullPath(IPath path)
    {
        if (_resourceGroup == null)
        {
            _initialContainerFullPath = path;
        }
        else
        {
            _resourceGroup.setContainerFullPath(path);
        }
    }

    /**
     * Sets the value of this page's file name field, or stores it for future use if this page's controls do not exist
     * yet.
     * 
     * @param value new file name
     */
    public void setFileName(String value)
    {
        if (_resourceGroup == null)
        {
            _initialFileName = value;
        }
        else
        {
            _resourceGroup.setResource(value);
        }
    }

    /**
     * Checks whether the linked resource target is valid. Sets the error message accordingly and returns the status.
     * 
     * @return IStatus validation result from the CreateLinkedResourceGroup
     */
    protected IStatus validateLinkedResource()
    {
        IPath containerPath = _resourceGroup.getContainerFullPath();
        IPath newFilePath = containerPath.append(_resourceGroup.getResource());
        IFile newFileHandle = createFileHandle(newFilePath);
        IStatus status = _linkedResourceGroup.validateLinkLocation(newFileHandle);

        if (status.getSeverity() == IStatus.ERROR)
        {
            if (_firstLinkCheck)
            {
                setMessage(status.getMessage());
            }
            else
            {
                setErrorMessage(status.getMessage());
            }
        }
        else if (status.getSeverity() == IStatus.WARNING)
        {
            setMessage(status.getMessage(), WARNING);
            setErrorMessage(null);
        }
        return status;
    }

    /**
     * Returns whether this page's controls currently all contain valid values.
     * 
     * @return <code>true</code> if all controls are valid, and <code>false</code> if at least one is invalid
     */
    protected boolean validatePage()
    {
        boolean valid = true;

        if (!_resourceGroup.areAllValuesValid())
        {
            // if blank name then fail silently
            if (_resourceGroup.getProblemType() == ResourceAndContainerGroup.PROBLEM_RESOURCE_EMPTY
            || _resourceGroup.getProblemType() == ResourceAndContainerGroup.PROBLEM_CONTAINER_EMPTY)
            {
                setMessage(_resourceGroup.getProblemMessage());
                setErrorMessage(null);
            }
            else
            {
                setErrorMessage(_resourceGroup.getProblemMessage());
            }
            valid = false;
        }

		String resourceName = _resourceGroup.getResource();
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IStatus result = workspace.validateName(resourceName, IResource.FILE);
		if (!result.isOK()) {
			setErrorMessage(result.getMessage());
			return false;
		}

        IStatus linkedResourceStatus = null;
        if (valid)
        {
            linkedResourceStatus = validateLinkedResource();
            if (linkedResourceStatus.getSeverity() == IStatus.ERROR)
            {
                valid = false;
            }
        }
        // validateLinkedResource sets messages itself
        if (valid && (linkedResourceStatus == null || linkedResourceStatus.isOK()))
        {
            setMessage(null);
            setErrorMessage(null);
        }
        return valid;
    }

    /*
     * @see DialogPage.setVisible(boolean)
     */
    public void setVisible(boolean visible)
    {
        super.setVisible(visible);
        if (visible)
        {
            _resourceGroup.setFocus();
        }
    }
}
