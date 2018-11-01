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

/**
 * @author Zhi-hong(Bryan) Yang
 */

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.action.MenuManager;
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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.internal.ide.misc.ContainerContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.part.DrillDownComposite;


/**
 * Workbench-level composite for choosing a container.
 */
public class ContainerSelectionGroup extends Composite
{
    // The listener to notify of events
    private Listener            _listener;

    // Enable user to type in new container name
    private boolean             _allowNewContainerName        = true;

    // show all projects by default
    private boolean             _showClosedProjects           = true;

    // Last selection made by user
    private IContainer          _selectedContainer;

    // handle on parts
    private Text                _containerNameField;

    TreeViewer                  _treeViewer;

    private Menu                _treeMenu;
    private MenuItem            _menuItem;
    private Button              _btnCreateProject;
    // the message to display at the top of this dialog
    private static final String DEFAULT_MSG_NEW_ALLOWED      = Messages.ContainerGroup_message; //$NON-NLS-1$
    private static final String DEFAULT_MSG_SELECT_ONLY      = Messages.ContainerGroup_selectFolder; //$NON-NLS-1$

    // sizing constants
    private static final int    SIZING_SELECTION_PANE_WIDTH  = 480;
    private static final int    SIZING_SELECTION_PANE_HEIGHT = 300;

    /**
     * Creates a new instance of the widget.
     * 
     * @param parent The parent widget of the group.
     * @param listener A listener to forward events to. Can be null if no listener is required.
     * @param allowNewContainerName Enable the user to type in a new container name instead of just selecting from the
     *            existing ones.
     */
    public ContainerSelectionGroup(Composite parent, Listener listener, boolean allowNewContainerName)
    {
        this(parent, listener, allowNewContainerName, null);
    }

    /**
     * Creates a new instance of the widget.
     * 
     * @param parent The parent widget of the group.
     * @param listener A listener to forward events to. Can be null if no listener is required.
     * @param allowNewContainerName Enable the user to type in a new container name instead of just selecting from the
     *            existing ones.
     * @param message The text to present to the user.
     */
    public ContainerSelectionGroup(Composite parent, Listener listener, boolean allowNewContainerName, String message)
    {
        this(parent, listener, allowNewContainerName, message, true);
    }

    /**
     * Creates a new instance of the widget.
     * 
     * @param parent The parent widget of the group.
     * @param listener A listener to forward events to. Can be null if no listener is required.
     * @param allowNewContainerName Enable the user to type in a new container name instead of just selecting from the
     *            existing ones.
     * @param message The text to present to the user.
     * @param showClosedProjects Whether or not to show closed projects.
     */
    public ContainerSelectionGroup(Composite parent, Listener listener, boolean allowNewContainerName, String message,
        boolean showClosedProjects)
    {
        this(parent, listener, allowNewContainerName, message, showClosedProjects, SIZING_SELECTION_PANE_HEIGHT);
    }

    /**
     * Creates a new instance of the widget.
     * 
     * @param parent The parent widget of the group.
     * @param listener A listener to forward events to. Can be null if no listener is required.
     * @param allowNewContainerName Enable the user to type in a new container name instead of just selecting from the
     *            existing ones.
     * @param message The text to present to the user.
     * @param showClosedProjects Whether or not to show closed projects.
     * @param heightHint height hint for the drill down composite
     */
    public ContainerSelectionGroup(Composite parent, Listener listener, boolean allowNewContainerName, String message,
        boolean showClosedProjects, int heightHint)
    {
        super(parent, SWT.NONE);
        this._listener = listener;
        this._allowNewContainerName = allowNewContainerName;
        this._showClosedProjects = showClosedProjects;
        if (message != null)
        {
            createContents(message, heightHint);
        }
        else if (allowNewContainerName)
        {
            createContents(DEFAULT_MSG_NEW_ALLOWED, heightHint);
        }
        else
        {
            createContents(DEFAULT_MSG_SELECT_ONLY, heightHint);
        }
    }

    /**
     * The container selection has changed in the tree view. Update the container name field value and notify all
     * listeners.
     */
    public void containerSelectionChanged(IContainer container)
    {
        _selectedContainer = container;

        if (_allowNewContainerName)
        {
            if (container == null)
            {
                _containerNameField.setText("");//$NON-NLS-1$
            }
            else
            {
                _containerNameField.setText(container.getFullPath().makeRelative().toString());
            }
        }

        // fire an event so the parent can update its controls
        if (_listener != null)
        {
            Event changeEvent = new Event();
            changeEvent.type = SWT.Selection;
            changeEvent.widget = this;
            _listener.handleEvent(changeEvent);
        }
    }

    /**
     * Creates the contents of the composite.
     */
    public void createContents(String message)
    {
        createContents(message, SIZING_SELECTION_PANE_HEIGHT);
    }

    /**
     * Creates the contents of the composite.
     * 
     * @param heightHint height hint for the drill down composite
     */
    public void createContents(String message, int heightHint)
    {
        GridLayout layout = new GridLayout();
        layout.marginWidth = 0;
        setLayout(layout);
        setLayoutData(new GridData(GridData.FILL_BOTH));

        Label label = new Label(this, SWT.WRAP);
        label.setText(message);
        label.setFont(this.getFont());

        if (_allowNewContainerName)
        {
            Composite _cmps = new Composite(this, SWT.NONE);
            _cmps.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_FILL));
            GridLayout gridLayout = new GridLayout();
            gridLayout.numColumns = 2;
            gridLayout.marginWidth = 0;
            _cmps.setLayout(gridLayout);

            _containerNameField = new Text(_cmps, SWT.SINGLE | SWT.BORDER);
            GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
            _containerNameField.setLayoutData(gridData);
            _containerNameField.addListener(SWT.Modify, _listener);
            _containerNameField.setFont(this.getFont());

            _btnCreateProject = new Button(_cmps, SWT.NONE);
            _btnCreateProject.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
            _btnCreateProject.setText(Messages.ContainerSelectionGroup_createProjectAction_label);
            _btnCreateProject.addSelectionListener(new btnCreateProjectSelectionListener());
        }
        else
        {
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
    protected void createTreeViewer(int heightHint)
    {
        // Create drill down.
        DrillDownComposite drillDown = new DrillDownComposite(this, SWT.BORDER);
        GridData spec = new GridData(GridData.VERTICAL_ALIGN_FILL | GridData.HORIZONTAL_ALIGN_FILL
            | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL);
        spec.widthHint = SIZING_SELECTION_PANE_WIDTH;
        spec.heightHint = heightHint;
        drillDown.setLayoutData(spec);

        // Create tree viewer inside drill down.
        _treeViewer = new TreeViewer(drillDown, SWT.NONE);
        drillDown.setChildTree(_treeViewer);
        ContainerContentProvider cp = new ContainerContentProvider();
        cp.showClosedProjects(_showClosedProjects);
        _treeViewer.setContentProvider(cp);
        _treeViewer.setLabelProvider(WorkbenchLabelProvider.getDecoratingWorkbenchLabelProvider());
        _treeViewer.setSorter(new ViewerSorter());

        _treeViewer.addSelectionChangedListener(new ISelectionChangedListener()
        {
            public void selectionChanged(SelectionChangedEvent event)
            {
                IStructuredSelection selection = (IStructuredSelection) event.getSelection();
                containerSelectionChanged((IContainer) selection.getFirstElement()); // allow null
            }
        }
        );
        _treeViewer.addDoubleClickListener(new IDoubleClickListener()
        {
            public void doubleClick(DoubleClickEvent event)
            {
                ISelection selection = event.getSelection();
                if (selection instanceof IStructuredSelection)
                {
                    Object item = ((IStructuredSelection) selection).getFirstElement();
                    if (_treeViewer.getExpandedState(item))
                    _treeViewer.collapseToLevel(item, 1);
                    else
                    _treeViewer.expandToLevel(item, 1);
                }
            }
        }
        );

        // This has to be done after the viewer has been laid out
        _treeViewer.setInput(ResourcesPlugin.getWorkspace());

        MenuManager mgr = new MenuManager();
        //        mgr.add(new Action("Save Result Set..."){});
        mgr.add(new CreateProjectAction(_treeViewer));
        CreateFolderAction createFolderAction = new CreateFolderAction(_treeViewer);
        _treeViewer.addSelectionChangedListener(createFolderAction);
        mgr.add(createFolderAction);

        Menu menu = mgr.createContextMenu(_treeViewer.getTree());
        _treeViewer.getTree().setMenu(menu);
        _treeViewer.getTree().setFocus();

        /*
         * treeMenu = new Menu(treeViewer.getTree()); treeViewer.getTree().setMenu(treeMenu); menuItem = new
         * MenuItem(treeMenu, SWT.NONE); menuItem.addSelectionListener(new MenuItemSelectionListener());
         * menuItem.setText("New Project");
         */
    }

    /**
     * Returns the currently entered container name. Null if the field is empty. Note that the container may not exist
     * yet if the user entered a new container name in the field.
     */
    public IPath getContainerFullPath()
    {
        if (_allowNewContainerName)
        {
            String pathName = _containerNameField.getText();
            if (pathName == null || pathName.length() < 1)
            return null;
            else
            //The user may not have made this absolute so do it for them
            return (new Path(pathName)).makeAbsolute();
        }
        else
        {
            if (_selectedContainer == null)
            return null;
            else
            return _selectedContainer.getFullPath();
        }
    }

    /**
     * Gives focus to one of the widgets in the group, as determined by the group.
     */
    public void setInitialFocus()
    {
        if (_allowNewContainerName)
        _containerNameField.setFocus();
        else
        _treeViewer.getTree().setFocus();
        _treeViewer.getTree().setFocus();
    }

    /**
     * Sets the selected existing container.
     */
    public void setSelectedContainer(IContainer container)
    {
        _selectedContainer = container;

        //expand to and select the specified container
        List itemsToExpand = new ArrayList();
        IContainer parent = container.getParent();
        while (parent != null)
        {
            itemsToExpand.add(0, parent);
            parent = parent.getParent();
        }
        _treeViewer.setExpandedElements(itemsToExpand.toArray());
        _treeViewer.setSelection(new StructuredSelection(container), true);
    }

    private class MenuItemSelectionListener extends SelectionAdapter
    {
        public void widgetSelected(SelectionEvent e)
        {
            do_menuItem_widgetSelected();
        }
    }

    protected void do_menuItem_widgetSelected()
    {

    }

    private class btnCreateProjectSelectionListener extends SelectionAdapter
    {
        public void widgetSelected(SelectionEvent e)
        {
            do_btnCreateProject_widgetSelected();
        }
    }

    protected void do_btnCreateProject_widgetSelected()
    {
        CreateProjectAction action = new CreateProjectAction(_treeViewer);
        action.run();
    }

}
