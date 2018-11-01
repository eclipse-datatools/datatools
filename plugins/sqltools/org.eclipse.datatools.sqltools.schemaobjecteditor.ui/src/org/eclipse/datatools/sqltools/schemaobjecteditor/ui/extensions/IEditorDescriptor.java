/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.extensions;

import java.util.Map;

import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorActionBarContributor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorHandler;
import org.eclipse.swt.graphics.Image;

/**
 * Containing some static information of an schema object editor
 * 
 * @author Idull
 */
public interface IEditorDescriptor
{
    /**
     * Returns the editor name
     * 
     * @return
     */
    public String getEditorName();

    /**
     * Returns the editor id
     * 
     * @return
     */
    public String getEditorId();

    /**
     * Returns the vendor name
     * 
     * @return
     */
    public String getVendorName();

    /**
     * Returns the product version for which this editor is defined
     * 
     * @return
     */
    public String getVersion();

    /**
     * Returns the db object type id for which this editor is defined
     * 
     * @return
     */
    public String getSchemaObjectType();

    /**
     * Returns the pages list of this editor
     * 
     * @return
     */
    public IEditorPageDescriptor[] getPageDescriptors();

    /**
     * Sets the pages of this editor, should NOT called by consumer
     * 
     * @param pages
     */
    public void setPageDescriptors(IEditorPageDescriptor[] pages);

    /**
     * Returns the handler for this editor
     * 
     * @return
     */
    public ISchemaObjectEditorHandler getHandler();

    /**
     * Sets the handler, should NOT called by consumer
     * 
     * @param handler
     */
    public void setHandler(ISchemaObjectEditorHandler handler);

    /**
     * Returns the icon for this editor, can be <code>null</code>
     * 
     * @return
     */
    public Image getIcon();

    /**
     * Checks if the visibility of pages of this editor can be set by end user
     * 
     * @return
     */
    public boolean isVisibilityConfigurable();

    /**
     * Returns the action bar contributor, can be <code>null</code>
     * 
     * @return
     */
    public ISchemaObjectEditorActionBarContributor getActionContributor();

    /**
     * Sets the action bar contributor, should NOT called by consumer
     * 
     * @param contributor
     */
    public void setActionContributor(ISchemaObjectEditorActionBarContributor contributor);

    /**
     * Returns the default order of the pages. ATTN: The contents of the map may be removed outside of this class
     * 
     * @return
     */
    public Map getDefaultPagesOrder();

    /**
     * Returns the pages of this editor with the order set by end-user
     * 
     * @return
     */
    public IEditorPageDescriptor[] getSortedPages();

    /**
     * Returns the pages of this editor with default order
     * 
     * @return
     */
    public IEditorPageDescriptor[] getDefaultSortedPages();

    /**
     * Returns the visible pages of this editor with the order set by end-user
     * 
     * @return
     */
    public IEditorPageDescriptor[] getVisibleSortedPages();

    /**
     * Returns the visible pages of this editor with default order
     * 
     * @return
     */
    public IEditorPageDescriptor[] getVisibleSortedPagesDefault();

    /**
     * Returns the page which is specified as the first page when it's visible
     * 
     * @return the first page
     */
    public IEditorPageDescriptor getMandatoryFirstPage();

    /**
     * Returns the page which is specified as the last page when it's visible
     * 
     * @return the last page
     */
    public IEditorPageDescriptor getMandatoryLastPage();

    /**
     * 
     * @param mandatoryFirstPage
     */
    public void setMandatoryFirstPage(IEditorPageDescriptor mandatoryFirstPage);

    /**
     * 
     * @param mandatoryLastPage
     */
    public void setMandatoryLastPage(IEditorPageDescriptor mandatoryLastPage);

    /**
     * Returns the object type name
     * 
     * @return
     */
    public String getObjectTypeName();

    /**
     * 
     * @param objTypeName
     */
    public void setObjectTypeName(String objTypeName);

    /**
     * Returns the plugin id which this editors belongs to
     * 
     * @return
     */
    public String getPluginId();

    /**
     * Sets the plugin id
     */
    public void setPluginId(String pluginId);
}
