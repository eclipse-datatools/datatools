/*******************************************************************************
 * Copyright (c) 2001, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.internal.ui.widgets;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.util.resources.ResourceLoader;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.part.DrillDownComposite;

/**
 * Workbench-level composite for choosing a container.
 * 
 * The original implementation of this class was copied from 
 * org.eclipse.ui.internal.ide.misc.ContainerSelectionGroup.
 */
public class DataContainerSelectionGroup extends Composite {
	private static final ResourceLoader resource = ResourceLoader.getResourceLoader();
    
	// The listener to notify of events
	private Listener listener;

	// Enable user to type in new container name
	private boolean allowNewContainerName = true;

	// show all projects by default
	private boolean showClosedProjects = true;
	
	// show files by default
	private boolean showFiles = true;
	
	// show all files by default
	private String[] fileFilter = new String[]{};
	
	// show projects regardless of nature
	private String[] projectNatureFilter = new String[0];
	
	private boolean allowMultipleSelection = false;
	
	private String[] filesToExclude = new String[0];
	
	// Last selection made by user
	private IResource selectedContainer;
	
	private IStructuredSelection lastSelection;
	
	// handle on parts
	private Text containerNameField;
	TreeViewer treeViewer;
	

	// the message to display at the top of this dialog
	private static final String DEFAULT_MSG_NEW_ALLOWED = resource.queryString("_UI_DEFAULT_MSG_NEW_ALLOWED"); //$NON-NLS-1$
	private static final String DEFAULT_MSG_SELECT_ONLY = resource.queryString("_UI_DEFAULT_MSG_SELECT_ONLY"); //$NON-NLS-1$

	// sizing constants
	private static final int SIZING_SELECTION_PANE_WIDTH = 320;
	private static final int SIZING_SELECTION_PANE_HEIGHT = 300;
/**
 * Creates a new instance of the widget.
 *
 * @param parent The parent widget of the group.
 * @param listener A listener to forward events to. Can be null if
 *	 no listener is required.
 * @param allowNewContainerName Enable the user to type in a new container
 *  name instead of just selecting from the existing ones.
 */
public DataContainerSelectionGroup (Composite parent, Listener listener, boolean allowNewContainerName) {
	this(parent, listener, allowNewContainerName, null);
}
/**
 * Creates a new instance of the widget.
 *
 * @param parent The parent widget of the group.
 * @param listener A listener to forward events to.  Can be null if
 *	 no listener is required.
 * @param allowNewContainerName Enable the user to type in a new container
 *  name instead of just selecting from the existing ones.
 * @param message The text to present to the user.
 */
public DataContainerSelectionGroup (Composite parent, Listener listener, boolean allowNewContainerName, String message) {
	this(parent, listener, allowNewContainerName, message, true, new String[0], true, new String[]{}, false, new String[0]);
}
/**
 * Creates a new instance of the widget.
 *
 * @param parent The parent widget of the group.
 * @param listener A listener to forward events to.  Can be null if
 *	 no listener is required.
 * @param allowNewContainerName Enable the user to type in a new container
 *  name instead of just selecting from the existing ones.
 * @param message The text to present to the user.
 * @param showClosedProjects Whether or not to show closed projects.
 */
public DataContainerSelectionGroup (Composite parent, Listener listener, boolean allowNewContainerName, String message, boolean showClosedProjects, String[] projectNatureFilter, boolean showFiles, String[] fileFilter, boolean allowMultipleSelection, String [] filesToExclude) {
	this(parent, listener, allowNewContainerName, message, showClosedProjects, SIZING_SELECTION_PANE_HEIGHT, projectNatureFilter,showFiles, fileFilter, allowMultipleSelection, filesToExclude);
}
/**
 * Creates a new instance of the widget.
 *
 * @param parent The parent widget of the group.
 * @param listener A listener to forward events to.  Can be null if
 *	 no listener is required.
 * @param allowNewContainerName Enable the user to type in a new container
 *  name instead of just selecting from the existing ones.
 * @param message The text to present to the user.
 * @param showClosedProjects Whether or not to show closed projects.
 * @param heightHint height hint for the drill down composite
 */
public DataContainerSelectionGroup (Composite parent, Listener listener, boolean allowNewContainerName, String message, boolean showClosedProjects, int heightHint, String[] projectNatureFilter, boolean showFiles, String[] fileFilter, boolean allowMultipleSelection, String[] filesToExclude) {
	super (parent, SWT.NONE);
	this.listener = listener;
	this.allowNewContainerName = allowNewContainerName;
	this.showClosedProjects = showClosedProjects;
	this.showFiles = showFiles;
	this.fileFilter = fileFilter;
	this.projectNatureFilter = projectNatureFilter; 
	this.filesToExclude = filesToExclude;
	this.allowMultipleSelection = allowMultipleSelection;
	if (message != null)
		createContents(message, heightHint);
	else if (allowNewContainerName)
		createContents(DEFAULT_MSG_NEW_ALLOWED, heightHint);
	else
		createContents(DEFAULT_MSG_SELECT_ONLY, heightHint);
}
/**
 * The container selection has changed in the
 * tree view. Update the container name field
 * value and notify all listeners.
 */
public void containerSelectionChanged(IStructuredSelection selection) {
    lastSelection = selection;
    
    if (!showFiles){
    IResource container = (IResource) selection.getFirstElement();
	
	selectedContainer = container;
	
	if (allowNewContainerName) {
		if (container == null)
			containerNameField.setText("");//$NON-NLS-1$
		else
			containerNameField.setText(container.getFullPath().makeRelative().toString());
	}

    }
	// fire an event so the parent can update its controls
	if (listener != null) {
		Event changeEvent = new Event();
		changeEvent.type = SWT.Selection;
		changeEvent.widget = this;
		listener.handleEvent(changeEvent);
	}
}
/**
 * Creates the contents of the composite.
 */
public void createContents(String message) {
	createContents(message, SIZING_SELECTION_PANE_HEIGHT);
}
/**
 * Creates the contents of the composite.
 * 
 * @param heightHint height hint for the drill down composite
 */
public void createContents(String message, int heightHint) {
	GridLayout layout = new GridLayout();
	layout.marginWidth = 0;
	setLayout(layout);
	setLayoutData(new GridData(GridData.FILL_BOTH));

	Label label = new Label(this,SWT.WRAP);
	label.setText(message);
	label.setFont(this.getFont());

	if (allowNewContainerName) {
		containerNameField = new Text(this, SWT.SINGLE | SWT.BORDER);
		containerNameField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		containerNameField.addListener(SWT.Modify, listener);
		containerNameField.setFont(this.getFont());
	}
	else {
		// filler...
		new Label(this, SWT.NONE);
	}

	createTreeViewer(heightHint);
	Dialog.applyDialogFont(this);
}
/**
 * Returns a new drill down viewer for this dialog.
 *
 * @param heightHint height hint for the drill down composite
 * @return a new drill down viewer
 */
protected void createTreeViewer(int heightHint) {
	// Create drill down.
	DrillDownComposite drillDown = new DrillDownComposite(this, SWT.BORDER);
	GridData spec = new GridData(
		GridData.VERTICAL_ALIGN_FILL | GridData.HORIZONTAL_ALIGN_FILL |
		GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL);
	spec.widthHint = SIZING_SELECTION_PANE_WIDTH;
	spec.heightHint = heightHint;
	drillDown.setLayoutData(spec);

	// Create tree viewer inside drill down.
	Tree tree = null;
	if (allowMultipleSelection){
	tree = new Tree(drillDown, SWT.MULTI);
	} else {
	    tree = new Tree(drillDown, SWT.NONE);
	}
	treeViewer = new TreeViewer(tree);
	drillDown.setChildTree(treeViewer);
	DataContainerContentProvider cp = new DataContainerContentProvider();
	cp.showClosedProjects(showClosedProjects);
	cp.showFiles(this.showFiles);
	cp.setFileFilter(this.fileFilter);
	cp.setProjectNatureFilter(projectNatureFilter);
	cp.setExcludedFiles(filesToExclude);
	treeViewer.setContentProvider(cp);
	treeViewer.setLabelProvider(WorkbenchLabelProvider.getDecoratingWorkbenchLabelProvider());
	treeViewer.setSorter(new ViewerSorter());
	treeViewer.addSelectionChangedListener(
		new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection)event.getSelection();
				containerSelectionChanged(selection);
			}
		});
	treeViewer.addDoubleClickListener(
		new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				ISelection selection = event.getSelection();
				if (selection instanceof IStructuredSelection) {
					Object item = ((IStructuredSelection)selection).getFirstElement();
					if (treeViewer.getExpandedState(item))
						treeViewer.collapseToLevel(item, 1);
					else
						treeViewer.expandToLevel(item, 1);
				}
			}
		});

	// This has to be done after the viewer has been laid out
	treeViewer.setInput(ResourcesPlugin.getWorkspace());
}
/**
 * Returns the currently entered container name.
 * Null if the field is empty. Note that the
 * container may not exist yet if the user
 * entered a new container name in the field.
 */
public IPath getContainerFullPath() {
	if (allowNewContainerName) {
		String pathName = containerNameField.getText();
		if (pathName == null || pathName.length() < 1)
			return null;
		else
			//The user may not have made this absolute so do it for them
			return (new Path(pathName)).makeAbsolute();
	} else {
		if (selectedContainer == null)
			return null;
		else
			return selectedContainer.getFullPath();
	}
}
/**
 * Gives focus to one of the widgets in the group, as determined by the group.
 */
public void setInitialFocus() {
	if (allowNewContainerName)
		containerNameField.setFocus();
	else
		treeViewer.getTree().setFocus();
}
/**
 * Sets the selected existing container.
 */
public void setSelectedContainer(IContainer container) {
	selectedContainer = container;
	
	//expand to and select the specified container
	List itemsToExpand = new ArrayList();
	IContainer parent = container.getParent();
	while (parent != null) {
		itemsToExpand.add(0,parent);
		parent = parent.getParent();
	}
	treeViewer.setExpandedElements(itemsToExpand.toArray()); 
	treeViewer.setSelection(new StructuredSelection(container),true);
}

/**
 * Sets the selected existing file.
 */
public void setSelectedFile(IFile selectedFile) {
    //expand to and select the specified container
    List itemsToExpand = new ArrayList();
    IContainer parent = selectedFile.getParent();
    while (parent != null) {
        itemsToExpand.add(0,parent);
        parent = parent.getParent();
    }
    treeViewer.setExpandedElements(itemsToExpand.toArray()); 
    treeViewer.setSelection(new StructuredSelection(selectedFile),true);
}

public IStructuredSelection getSelection(){
    return this.lastSelection;
}

}
