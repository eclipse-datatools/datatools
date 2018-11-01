/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorActionBarContributor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorHandler;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.extensions.IEditorDescriptor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.extensions.IEditorPageDescriptor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal.SOEUIPlugin;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal.Constants;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.util.ILogger;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.graphics.Image;

/**
 * An instance of this class stands for a specific schema object editor, which contains some static information of this
 * editor: name, icon, pages, etc.
 * 
 * @author Idull
 */
public class EditorDescriptor implements IEditorDescriptor
{
    private IConfigurationElement                   _configElement;

    private ISchemaObjectEditorActionBarContributor _contributor;

    private IConfigurationElement                   _contributorElement;

    private Map                                     _defaultPagesOrder;

    private String                                  _editorId;

    private String                                  _editorName;

    private ISchemaObjectEditorHandler              _handler;

    private Image                                   _icon;

    private boolean                                 _isVisibilityConfigurable;

    private ILogger                                 _logger = SOEUIPlugin.getLogger(null);

    private IEditorPageDescriptor                   _mandatoryFirstPage;

    private IEditorPageDescriptor                   _mandatoryLastPage;

    private String                                  _objectType;

    private IEditorPageDescriptor[]                 _pages;

    private IPreferenceStore                        _store;

    private String                                  _vendorName;

    private String                                  _version;

    private String                                  _objTypeName;

    private String                                  _pluginId;

    public EditorDescriptor(String id, String name, String dbName, String dbVersion, String type, Image icon,
            boolean genPreference)
    {
        this(id, name, dbName, dbVersion, type, icon, genPreference, null, null, null, null, null);
    }

    public EditorDescriptor(String id, String name, String dbName, String dbVersion, String type, Image icon,
            boolean genPreference, IEditorPageDescriptor[] _pages, ISchemaObjectEditorHandler configuration,
            ISchemaObjectEditorActionBarContributor contributor, IConfigurationElement configElement,
            IConfigurationElement contributorElement)
    {
        super();
        _defaultPagesOrder = new HashMap();
        _editorId = id;
        _editorName = name;
        _vendorName = dbName;
        _version = dbVersion;
        _objectType = type;
        _icon = icon;
        _isVisibilityConfigurable = genPreference;
        this._pages = _pages;
        this._handler = configuration;
        this._contributor = contributor;
        _configElement = configElement;
        _contributorElement = contributorElement;
        _store = SOEUIPlugin.getDefault().getPreferenceStore();
    }

    public ISchemaObjectEditorActionBarContributor getActionContributor()
    {

        try
        {
            if (_contributor == null)
            {
                _contributor = (ISchemaObjectEditorActionBarContributor) _contributorElement
                        .createExecutableExtension(Constants.EXTENSION_POINT_EDITOR_CONTRIBUTOR_CLASS);
            }
        }
        catch (Exception e)
        {
        }
        return _contributor;
    }

    public Map getDefaultPagesOrder()
    {
        return _defaultPagesOrder;
    }

    public IEditorPageDescriptor[] getDefaultSortedPages()
    {
        List sortedPages = new ArrayList();

        HashMap orders = (HashMap) getDefaultPagesOrder();

        HashMap clonedOrder = (HashMap) orders.clone();
        IEditorPageDescriptor ePage = getNextPage(clonedOrder);
        while (ePage != null)
        {
            sortedPages.add(ePage);
            ePage = getNextPage(clonedOrder);
        }

        for (int i = 0; i < _pages.length; i++)
        {
            if (!getDefaultPagesOrder().keySet().contains(_pages[i]))
            {
                sortedPages.add(_pages[i]);
            }
        }

        return (IEditorPageDescriptor[]) sortedPages.toArray(new IEditorPageDescriptor[sortedPages.size()]);
    }

    public String getEditorId()
    {
        return _editorId;
    }

    public String getEditorName()
    {
        return _editorName;
    }

    public ISchemaObjectEditorHandler getHandler()
    {
        ISchemaObjectEditorHandler config = _handler;
        try
        {
            _handler = (ISchemaObjectEditorHandler) _configElement
                    .createExecutableExtension(Constants.EXTENSION_POINT_EDITOR_HANDLER);
        }
        catch (Exception e)
        {
            _logger.error("ExtendedEditor_error_create_handler", e);
        }
        return config;
    }

    public Image getIcon()
    {
        return _icon;
    }

    public IEditorPageDescriptor getMandatoryFirstPage()
    {
        return _mandatoryFirstPage;
    }

    public IEditorPageDescriptor getMandatoryLastPage()
    {
        return _mandatoryLastPage;
    }

    /**
     * Returns the next page given the orders of pages. The order number can be 0 or greater than 0. If it's 0, that
     * means it's unordered
     * 
     * @param orders
     * @return the next page with the minumum order number (greater than 0)
     */
    private IEditorPageDescriptor getNextPage(Map orders)
    {
        if (orders != null && orders.size() > 0)
        {
            IEditorPageDescriptor page = null;
            IEditorPageDescriptor firstPage = null;
            int minNumber = -1;
            Iterator iter = orders.keySet().iterator();
            if (iter.hasNext())
            {
                firstPage = (IEditorPageDescriptor) iter.next();
                int number = ((Integer) orders.get(firstPage)).intValue();
                if (number > 0)
                {
                    minNumber = number;
                    page = firstPage;
                }
            }

            while (iter.hasNext())
            {
                IEditorPageDescriptor tmp = (IEditorPageDescriptor) iter.next();
                int number = ((Integer) orders.get(tmp)).intValue();
                if (number > 0 && (number < minNumber || minNumber == -1))
                {
                    minNumber = number;
                    page = tmp;
                }
            }
            if (page != null)
            {
                orders.remove(page);
            }
            else
            {
                // all pages are unsorted, return the first one
                orders.remove(firstPage);
                return firstPage;
            }
            return page;
        }
        return null;
    }

    public IEditorPageDescriptor[] getPageDescriptors()
    {
        return _pages;
    }

    public String getSchemaObjectType()
    {
        return _objectType;
    }

    public IEditorPageDescriptor[] getSortedPages()
    {
        IEditorPageDescriptor[] pages = getPageDescriptors();
        List sortedPages = new ArrayList();

        HashMap orders = new HashMap();
        for (int i = 0; i < pages.length; i++)
        {
            String key = Constants.EDITOR_PAGE_ORDER + pages[i].getEditorId() + pages[i].getPageId();
            int orderNum = _store.getInt(key);
            orders.put(pages[i], new Integer(orderNum));
        }

        IEditorPageDescriptor ePage = getNextPage(orders);
        while (ePage != null)
        {
            sortedPages.add(ePage);
            ePage = getNextPage(orders);
        }

        return (IEditorPageDescriptor[]) sortedPages.toArray(new IEditorPageDescriptor[sortedPages.size()]);
    }

    public String getVendorName()
    {
        return _vendorName;
    }

    public String getVersion()
    {
        return _version;
    }

    public IEditorPageDescriptor[] getVisibleSortedPages()
    {
        return null;
    }

    public IEditorPageDescriptor[] getVisibleSortedPagesDefault()
    {
        return null;
    }

    public boolean isVisibilityConfigurable()
    {
        return _isVisibilityConfigurable;
    }

    public void setActionContributor(ISchemaObjectEditorActionBarContributor contributor)
    {
        this._contributor = contributor;

    }

    public void setConfigElement(IConfigurationElement element)
    {
        _configElement = element;
    }

    public void setContributorElement(IConfigurationElement element)
    {
        _contributorElement = element;
    }

    public void setHandler(ISchemaObjectEditorHandler handler)
    {
        this._handler = handler;
    }

    public void setMandatoryFirstPage(IEditorPageDescriptor mandatoryFirstPage)
    {
        _mandatoryFirstPage = mandatoryFirstPage;
    }

    public void setMandatoryLastPage(IEditorPageDescriptor mandatoryLastPage)
    {
        _mandatoryLastPage = mandatoryLastPage;
    }

    public void setPageDescriptors(IEditorPageDescriptor[] pages)
    {
        _pages = pages;
    }

    public String getObjectTypeName()
    {
        return _objTypeName == null ? "" : _objTypeName;
    }

    public void setObjectTypeName(String objTypeName)
    {
        _objTypeName = objTypeName;
    }

    public String getPluginId()
    {
        return _pluginId;
    }

    public void setPluginId(String pluginId)
    {
        _pluginId = pluginId;
    }
}
