/*******************************************************************************
 * Copyright (c) 2001, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.internal.ui.dialogs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.util.resources.ResourceLoader;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.widgets.DataContainerSelectionGroup;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.widgets.IDataSelectionValidator;
import org.eclipse.jface.resource.JFaceColors;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.SelectionDialog;

/**
 * A standard selection dialog which solicits a container resource from the
 * user. The <code>getResult</code> method returns the selected container
 * resource.
 * <p>
 * This class may be instantiated; it is not intended to be subclassed.
 * </p>
 * <p>
 * Example:
 * 
 * <pre>
 * ContainerSelectionDialog dialog = new ContainerSelectionDialog(getShell(),
 *         initialSelection, allowNewContainerName(), msg);
 * dialog.open();
 * Object[] result = dialog.getResult();
 * </pre>
 * 
 * </p>
 * 
 * The original implementation of this class was copied from
 * org.eclipse.ui.dialogs.ContainerSelectionDialog.
 *  
 */
public class ResourceChooserDialog extends SelectionDialog {
	private static final ResourceLoader resource = ResourceLoader.getResourceLoader();
    // the widget group;
    DataContainerSelectionGroup group;

    // the root resource to populate the viewer with
    private IContainer initialSelection;

    // the file preselected in the viewer
    private IFile selectedFile;

    // allow the user to type in a new container name
    private boolean allowNewContainerName = true;

    // the validation message
    Label statusMessage;

    //for validating the selection
    IDataSelectionValidator validator;

    // show closed projects by default
    private boolean showClosedProjects = true;

    // show files by default
    private boolean showFiles = true;

    // show all files by default
    private String[] fileFilter = new String[0];

    // show projects regardless of nature by default
    private String[] projectNatureFilter = new String[0];
    
    private boolean allowMultipleSelection = false;
    
    private String[] filesToExclude = new String[0];

    /**
     * Creates a resource container selection dialog rooted at the given
     * resource. All selections are considered valid.
     * 
     * @param parentShell
     *            the parent shell
     * @param initialRoot
     *            the initial selection in the tree
     * @param allowNewContainerName
     *            <code>true</code> to enable the user to type in a new
     *            container name, and <code>false</code> to restrict the user
     *            to just selecting from existing ones
     * @param message
     *            the message to be displayed at the top of this dialog, or
     *            <code>null</code> to display a default message
     */
    public ResourceChooserDialog(Shell parentShell, IContainer initialRoot,
            boolean allowNewContainerName, String message, boolean showFiles) {
        super(parentShell);
        if (showFiles){
            setTitle(resource.queryString("_UI_TITLE_FILE_CHOOSER")); //$NON-NLS-1$
        } else {
            setTitle(resource.queryString("_UI_TITLE_FOLDER_CHOOSER")); //$NON-NLS-1$
        }
        this.initialSelection = initialRoot;
        this.showFiles = showFiles;
        this.allowNewContainerName = allowNewContainerName;
        if (message != null)
        {
            setMessage(message);
        }
        else 
        {
            if (showFiles){
                setMessage(resource.queryString("_UI_DESCRIPTION_FILE")); //$NON-NLS-1$
            } else {
            setMessage(resource.queryString("_UI_DESCRIPTION_FOLDER"));    //$NON-NLS-1$
            }
        }
        setShellStyle(getShellStyle() | SWT.RESIZE);
    }

    /**
     * Creates a resource container selection dialog rooted at the given
     * resource. All selections are considered valid.
     * 
     * @param parentShell
     *            the parent shell
     * @param initialRoot
     *            the initial selection in the tree
     * @param initialFile
     *            the initial file selection in the tree
     * @param allowNewContainerName
     *            <code>true</code> to enable the user to type in a new
     *            container name, and <code>false</code> to restrict the user
     *            to just selecting from existing ones
     * @param message
     *            the message to be displayed at the top of this dialog, or
     *            <code>null</code> to display a default message
     */
    public ResourceChooserDialog(Shell parentShell, IContainer initialRoot, IFile initialFile,
            boolean allowNewContainerName, String message, boolean showFiles) {
        this(parentShell, initialRoot, allowNewContainerName, message, showFiles);
        this.selectedFile = initialFile;
    }

    /*
     * (non-Javadoc) Method declared on Dialog.
     */
    protected Control createDialogArea(Composite parent) {
        // create composite
        Composite area = (Composite) super.createDialogArea(parent);

        Listener listener = new Listener() {
            public void handleEvent(Event event) {
                if (statusMessage != null && validator != null) {
                    String errorMsg = validator.isValid(group
                            .getSelection());
                    if (errorMsg == null || errorMsg.equals("")) { //$NON-NLS-1$
                        statusMessage.setText(""); //$NON-NLS-1$
                        getOkButton().setEnabled(true);
                    } else {
                        statusMessage.setForeground(JFaceColors
                                .getErrorText(statusMessage.getDisplay()));
                        statusMessage.setText(errorMsg);
                        getOkButton().setEnabled(false);
                    }
                }
            }
        };

        // container selection group
        group = new DataContainerSelectionGroup(area, listener,
                allowNewContainerName, getMessage(), showClosedProjects,
                projectNatureFilter, showFiles, fileFilter, this.allowMultipleSelection, filesToExclude);
        
        if (selectedFile != null) {
            group.setSelectedFile(selectedFile);
        } 
        else if (initialSelection != null) {
            group.setSelectedContainer(initialSelection);
        }

        statusMessage = new Label(group, SWT.NONE);
        statusMessage.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        statusMessage.setFont(parent.getFont());

        return dialogArea;
    }

    /**
     * The <code>ContainerSelectionDialog</code> implementation of this
     * <code>Dialog</code> method builds a list of the selected resource
     * containers for later retrieval by the client and closes this dialog.
     */
    protected void okPressed() {

        List selectionList = new ArrayList();
        IStructuredSelection returnValue = group.getSelection();
        if (returnValue != null)
            selectionList.add(returnValue);
        setResult(selectionList);
        super.okPressed();
    }

    /**
     * Sets the validator to use.
     * 
     * @param validator
     *            A selection validator
     */
    public void setValidator(IDataSelectionValidator validator) {
        this.validator = validator;
    }

    /**
     * Set whether or not closed projects should be shown in the selection
     * dialog.
     * 
     * @param show
     *            Whether or not to show closed projects.
     */
    public void showClosedProjects(boolean show) {
        this.showClosedProjects = show;
    }

    public void setFileFilter(String[] filter) {
        this.fileFilter = filter;
    }

    /**
     * Specify the natures of projects to be displayed. Default is to show
     * projects regardless of nature.
     * 
     * @param naturesToShow
     *            String[] containing the project natures a project must have to
     *            be displayed
     */
    public void setProjectNatureFilter(String[] naturesToShow) {
        this.projectNatureFilter = naturesToShow;
    }
    
    public void allowMultipleSelections(boolean allow){
        this.allowMultipleSelection = allow;
    }  
    
    public void setExcludedFiles(String[] filesToExclude){
        this.filesToExclude = filesToExclude;
    }
}