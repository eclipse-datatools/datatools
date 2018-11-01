/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.extensions;

import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorPage;

/**
 * Containing some static information of an editor page
 * 
 * @author Idull
 */
public interface IEditorPageDescriptor
{
    /**
     * Returns the page id
     * 
     * @return
     */
    public String getPageId();

    /**
     * Returns the page name
     * 
     * @return
     */
    public String getPageName();

    /**
     * Returns the id of the editor in which this page is contained
     * 
     * @return
     */
    public String getEditorId();

    /**
     * Checks if this page is required (Must be visible)
     * 
     * @return
     */
    public boolean isRequired();

    /**
     * Checks if the page is visible by default
     * 
     * @return
     */
    public boolean isVisibleByDefault();

    /**
     * Checks if this page is visible or not
     * 
     * @return
     */
    public boolean isSelectedToShow();

    /**
     * Returns the page instance
     * 
     * @return
     */
    public ISchemaObjectEditorPage getPageClass();

    /**
     * Returns the page extension id defined in the extension, can be <code>null</code>
     * 
     * @return
     */
    public String getPageExtensionId();

    /**
     * Returns the instance of the editor in which this page is contained
     * 
     * @return
     */
    public IEditorDescriptor getEditor();

    /**
     * Returns the context help id
     * 
     * @return
     */
    public String getContextHelpId();

    /**
     * Returns the object class type for which the page is designed
     * 
     * @return
     */
    public String getObjectClassType();

    /**
     * Checks if the page is selected to be visible
     * 
     * @return
     */
    public boolean isVisible();
}
