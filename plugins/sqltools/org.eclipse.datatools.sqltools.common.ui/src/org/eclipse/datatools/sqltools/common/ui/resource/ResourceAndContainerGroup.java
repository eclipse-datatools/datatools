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

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;


/**
 * Workbench-level composite for resource and container specification by the user. Services such as field validation are
 * performed by the group. The group can be configured to accept existing resources, or only new resources.
 */
public class ResourceAndContainerGroup implements Listener
{
    // problem identifiers
    public static final int         PROBLEM_NONE                        = 0;
    public static final int         PROBLEM_RESOURCE_EMPTY              = 1;
    public static final int         PROBLEM_RESOURCE_EXIST              = 2;
    public static final int         PROBLEM_RESOURCE_CONTAINS_SEPARATOR = 3;
    public static final int         PROBLEM_PATH_INVALID                = 4;
    public static final int         PROBLEM_CONTAINER_EMPTY             = 5;
    public static final int         PROBLEM_PROJECT_DOES_NOT_EXIST      = 6;
    public static final int         PROBLEM_NAME_INVALID                = 7;
    public static final int         PROBLEM_LINKED_PROJECT              = 8;
    protected final static String   LINKED_NATURE_ID                    = "com.sybase.stf.navigator.linked.NavLinkedNature";

    private String                  _PLAINTYPE                          = "";
    // the client to notify of changes
    private Listener                _client;

    // whether to allow existing resources
    private boolean                 _allowExistingResources             = false;

    // resource type (file, folder, project)
    private String                  _resourceType                       = Messages
        .ResourceGroup_resource; //$NON-NLS-1$

    // show closed projects in the tree, by default
    private boolean                 _showClosedProjects                 = true;

    // problem indicator
    private String                  _problemMessage                     = "";                                        //$NON-NLS-1$
    private int                     _problemType                        = PROBLEM_NONE;

    // widgets
    private ContainerSelectionGroup _containerGroup;
    private Text                    _resourceNameField;

    // constants
    private static final int        _SIZING_TEXT_FIELD_WIDTH            = 250;

    /**
     * Create an instance of the group to allow the user to enter/select a container and specify a resource name.
     * 
     * @param parent composite widget to parent the group
     * @param client object interested in changes to the group's fields value
     * @param resourceFieldLabel label to use in front of the resource name field
     * @param resourceType one word, in lowercase, to describe the resource to the user (file, folder, project)
     */
    public ResourceAndContainerGroup(Composite parent, Listener client, String resourceFieldLabel, String resourceType)
    {
        this(parent, client, resourceFieldLabel, resourceType, true);
    }

    /**
     * Create an instance of the group to allow the user to enter/select a container and specify a resource name.
     * 
     * @param parent composite widget to parent the group
     * @param client object interested in changes to the group's fields value
     * @param resourceFieldLabel label to use in front of the resource name field
     * @param resourceType one word, in lowercase, to describe the resource to the user (file, folder, project)
     * @param showClosedProjects whether or not to show closed projects
     */
    public ResourceAndContainerGroup(Composite parent, Listener client, String resourceFieldLabel, String resourceType,
        boolean showClosedProjects)
    {
        this(parent, client, resourceFieldLabel, resourceType, showClosedProjects, SWT.DEFAULT);
    }

    /**
     * Create an instance of the group to allow the user to enter/select a container and specify a resource name.
     * 
     * @param parent composite widget to parent the group
     * @param client object interested in changes to the group's fields value
     * @param resourceFieldLabel label to use in front of the resource name field
     * @param resourceType one word, in lowercase, to describe the resource to the user (file, folder, project)
     * @param showClosedProjects whether or not to show closed projects
     * @param heightHint height hint for the container selection widget group
     */
    public ResourceAndContainerGroup(Composite parent, Listener client, String resourceFieldLabel, String resourceType,
        boolean showClosedProjects, int heightHint)
    {
        super();
        this._resourceType = resourceType;
        this._showClosedProjects = showClosedProjects;
        createContents(parent, resourceFieldLabel, heightHint);
        this._client = client;
    }

    /**
     * Returns a boolean indicating whether all controls in this group contain valid values.
     * 
     * @return boolean
     */
    public boolean areAllValuesValid()
    {
        return _problemType == PROBLEM_NONE;
    }

    /**
     * Creates this object's visual components.
     * 
     * @param parent org.eclipse.swt.widgets.Composite
     * @param heightHint height hint for the container selection widget group
     */
    protected void createContents(Composite parent, String resourceLabelString, int heightHint)
    {

        Font font = parent.getFont();
        // server name group
        Composite composite = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.marginWidth = 0;
        layout.marginHeight = 0;
        composite.setLayout(layout);
        composite.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL));
        composite.setFont(font);

        // container group
        if (heightHint == SWT.DEFAULT)
        {
            _containerGroup = new ContainerSelectionGroup(composite, this, true, null, _showClosedProjects);
        }
        else
        {
            _containerGroup = new ContainerSelectionGroup(composite, this, true, null, _showClosedProjects, heightHint);
        }

        // resource name group
        Composite nameGroup = new Composite(composite, SWT.NONE);
        layout = new GridLayout();
        layout.numColumns = 2;
        layout.marginWidth = 0;
        nameGroup.setLayout(layout);
        nameGroup.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL));
        nameGroup.setFont(font);

        Label label = new Label(nameGroup, SWT.NONE);
        label.setText(resourceLabelString);
        label.setFont(font);

        // resource name entry field
        _resourceNameField = new Text(nameGroup, SWT.BORDER);
        _resourceNameField.addListener(SWT.Modify, this);
        GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
        data.widthHint = _SIZING_TEXT_FIELD_WIDTH;
        _resourceNameField.setLayoutData(data);
        _resourceNameField.setFont(font);

        //create advanced control
        createAdvancedContents(composite, layout, font, data);

        validateControls();
    }

    /**
     * Creates advanced contents to extend UI.
     * 
     * @param composit org.eclipse.swt.widgets.Composite
     * @param layout
     * @param font
     * @param data
     */
    protected void createAdvancedContents(Composite composite, GridLayout layout, Font font, GridData data)
    {

    }

    /**
     * Returns the path of the currently selected container or null if no container has been selected. Note that the
     * container may not exist yet if the user entered a new container name in the field.
     */
    public IPath getContainerFullPath()
    {
        return _containerGroup.getContainerFullPath();
    }

    /**
     * Returns an error message indicating the current problem with the value of a control in the group, or an empty
     * message if all controls in the group contain valid values.
     * 
     * @return java.lang.String
     */
    public String getProblemMessage()
    {
        return _problemMessage;
    }

    /**
     * Returns the type of problem with the value of a control in the group.
     * 
     * @return one of the PROBLEM_* constants
     */
    public int getProblemType()
    {
        return _problemType;
    }

    /**
     * Returns a string that is the path of the currently selected container. Returns an empty string if no container
     * has been selected.
     */
    public String getResource()
    {
        return _resourceNameField.getText();
    }

    /**
     * Handles events for all controls in the group.
     * 
     * @param e org.eclipse.swt.widgets.Event
     */
    public void handleEvent(Event e)
    {
        validateControls();
        if (_client != null)
        {
            _client.handleEvent(e);
        }
    }

    /**
     * Sets the flag indicating whether existing resources are permitted.
     */
    public void setAllowExistingResources(boolean value)
    {
        _allowExistingResources = value;
    }

    /**
     * Sets the value of this page's container.
     * 
     * @param path Full path to the container.
     */
    public void setContainerFullPath(IPath path)
    {
        IResource initial = ResourcesPlugin.getWorkspace().getRoot().findMember(path);
        if (initial != null)
        {
            if (!(initial instanceof IContainer))
            {
                initial = initial.getParent();
            }
            _containerGroup.setSelectedContainer((IContainer) initial);
        }
        validateControls();
    }

    /**
     * Gives focus to the resource name field and selects its contents
     */
    public void setFocus()
    {
        //select the whole resource name.
        _containerGroup.setInitialFocus();
        _resourceNameField.setSelection(0, _resourceNameField.getText().length());
        _resourceNameField.setFocus();
    }

    /**
     * Sets the value of this page's resource name.
     * 
     * @param value new value
     */
    public void setResource(String value)
    {
        _resourceNameField.setText(value);
        validateControls();
    }

    /**
     * Returns a <code>boolean</code> indicating whether a container name represents a valid container resource in the
     * workbench. An error message is stored for future reference if the name does not represent a valid container.
     * 
     * @return <code>boolean</code> indicating validity of the container name
     */
    protected boolean validateContainer()
    {
        IPath path = _containerGroup.getContainerFullPath();
        if (path == null)
        {
            _problemType = PROBLEM_CONTAINER_EMPTY;
            _problemMessage = Messages.ResourceGroup_folderEmpty; //$NON-NLS-1$
            return false;
        }
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        String projectName = path.segment(0);
        IProject project = workspace.getRoot().getProject(projectName);
        if (projectName == null || !project.exists())
        {
            _problemType = PROBLEM_PROJECT_DOES_NOT_EXIST;
            _problemMessage = Messages.ResourceGroup_noProject; //$NON-NLS-1$
            return false;
        }
        try
        {
            if (project.hasNature(LINKED_NATURE_ID))
            {
                _problemType = PROBLEM_LINKED_PROJECT;
                _problemMessage = Messages.ResourceAndContainerGroup_error_linked_project; //-NLS-1$
                return false;
            }
        }
        catch (CoreException e)
        {
            //when the project is closed, we can't check hasNature, so just ignore;
            //otherwise, this exception should not happen
        }
        return true;
    }

    /**
     * Validates the values for each of the group's controls. If an invalid value is found then a descriptive error
     * message is stored for later reference. Returns a boolean indicating the validity of all of the controls in the
     * group.
     */
    protected boolean validateControls()
    {
        // don't attempt to validate controls until they have been created
        if (_containerGroup == null)
        {
            return false;
        }
        _problemType = PROBLEM_NONE;
        _problemMessage = "";//$NON-NLS-1$

        if (!validateContainer() || !validateResourceName())
        {
            return false;
        }

        IPath path = _containerGroup.getContainerFullPath().append(_resourceNameField.getText());
        return validateFullResourcePath(path);
    }

    /**
     * Returns a <code>boolean</code> indicating whether the specified resource path represents a valid new resource
     * in the workbench. An error message is stored for future reference if the path does not represent a valid new
     * resource path.
     * 
     * @param resourcePath the path to validate
     * @return <code>boolean</code> indicating validity of the resource path
     */
    protected boolean validateFullResourcePath(IPath resourcePath)
    {
        IWorkspace workspace = ResourcesPlugin.getWorkspace();

        IStatus result = workspace.validatePath(resourcePath.toString(), IResource.FOLDER);
        if (!result.isOK())
        {
            _problemType = PROBLEM_PATH_INVALID;
            _problemMessage = result.getMessage();
            return false;
        }

        if (!_allowExistingResources
        && (workspace.getRoot().getFolder(resourcePath).exists() || workspace.getRoot().getFile(resourcePath)
            .exists()))
        {
            _problemType = PROBLEM_RESOURCE_EXIST;
            _problemMessage = Messages.ResourceGroup_nameExists; //$NON-NLS-1$
            return false;
        }
        return true;
    }

    /**
     * Returns a <code>boolean</code> indicating whether the resource name rep- resents a valid resource name in the
     * workbench. An error message is stored for future reference if the name does not represent a valid resource name.
     * 
     * @return <code>boolean</code> indicating validity of the resource name
     */
    protected boolean validateResourceName()
    {
        String resourceName = _resourceNameField.getText();

        if (resourceName.equals(""))
        {
            //$NON-NLS-1$
            _problemType = PROBLEM_RESOURCE_EMPTY;
            _problemMessage = NLS.bind(Messages.ResourceGroup_emptyName, 
                _resourceType
            ); //$NON-NLS-1$
            return false;
        }

        if (!(new Path("")).isValidSegment(resourceName))
        {
            //$NON-NLS-1$
            _problemType = PROBLEM_NAME_INVALID;
            _problemMessage = NLS.bind(Messages
                .ResourceGroup_invalidFilename,
                resourceName
            ); //$NON-NLS-1$
            return false;
        }

        return true;
    }

}
