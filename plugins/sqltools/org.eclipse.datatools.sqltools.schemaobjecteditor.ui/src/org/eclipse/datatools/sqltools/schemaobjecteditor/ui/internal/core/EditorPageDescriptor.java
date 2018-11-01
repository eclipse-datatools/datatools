/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal.core;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorPage;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.extensions.IEditorDescriptor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.extensions.IEditorPageDescriptor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal.SOEUIPlugin;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal.Constants;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.util.ILogger;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * An instance of this class describe some static information of a page in a specific schema object editor.
 * 
 * @author Idull
 */
public class EditorPageDescriptor implements IEditorPageDescriptor
{
    private String                  _contextHelpId;
    private IEditorDescriptor       _editor;
    private String                  _editorId;
    private ILogger                 _logger = SOEUIPlugin.getLogger(null);
    private boolean                 _mustBeShown;
    private ISchemaObjectEditorPage _page;
    private IConfigurationElement   _pageClassElement;
    private String                  _pageExtensionId;
    private String                  _pageId;
    private String                  _pageName;
    private boolean                 _showByDefault;
    private String                  _objectClassType;

    public EditorPageDescriptor(String editorId, String pageId, String pageName, boolean beShown,
            boolean showByDefault, ISchemaObjectEditorPage page, IEditorDescriptor editor,
            IConfigurationElement pageClassElement, String pageExtensionId, String contextHelpId, String objectClassType)
    {
        super();
        _editorId = editorId;
        _pageId = pageId;
        _pageName = pageName;
        _mustBeShown = beShown;
        _showByDefault = showByDefault;
        _page = page;
        _editor = editor;
        _pageClassElement = pageClassElement;
        _pageExtensionId = pageExtensionId;
        _contextHelpId = contextHelpId;
        _objectClassType = objectClassType;
    }

    public String getContextHelpId()
    {
        return _contextHelpId;
    }

    public IEditorDescriptor getEditor()
    {
        return _editor;
    }

    public String getEditorId()
    {
        return _editorId;
    }

    public ISchemaObjectEditorPage getPageClass()
    {
        ISchemaObjectEditorPage page = _page;
        try
        {
            _page = (ISchemaObjectEditorPage) _pageClassElement
                    .createExecutableExtension(Constants.EXTENSION_POINT_PAGE_CLASS);
        }
        catch (Exception e)
        {
            _logger.error("EditorPage_error_create_class", e);
        }
        return page;
    }

    public String getPageExtensionId()
    {
        return _pageExtensionId;
    }

    public String getPageId()
    {
        return _pageId;
    }

    public String getPageName()
    {
        return _pageName;
    }

    public boolean isRequired()
    {
        return _mustBeShown;
    }

    public boolean isSelectedToShow()
    {
        // show all pages if no preference group generated
        if (_mustBeShown || !_editor.isVisibilityConfigurable())
        {
            return true;
        }
        IPreferenceStore store = SOEUIPlugin.getDefault().getPreferenceStore();
        String preferenceName = Constants.EDITOR_PAGE_VISIABILITY + getEditorId() + getPageId();
        return store.getBoolean(preferenceName);
    }

    public boolean isVisibleByDefault()
    {
        return _showByDefault;
    }

    public void setPageExtensionId(String pageExtensionId)
    {
        this._pageExtensionId = pageExtensionId;
    }

    public String getObjectClassType()
    {
        return _objectClassType;
    }

    public boolean isVisible()
    {
        IPreferenceStore store = SOEUIPlugin.getDefault().getPreferenceStore();
        String key = Constants.EDITOR_PAGE_VISIABILITY + getEditorId() + getPageId();
        return store.getBoolean(key);
    }

}
