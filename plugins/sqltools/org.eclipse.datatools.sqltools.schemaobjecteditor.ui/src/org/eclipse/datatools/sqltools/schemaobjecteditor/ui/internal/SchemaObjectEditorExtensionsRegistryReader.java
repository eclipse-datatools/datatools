/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorActionBarContributor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorHandler;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorPage;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.extensions.IEditorDescriptor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.extensions.IEditorPageDescriptor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal.core.EditorDescriptor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal.core.EditorPageDescriptor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.util.ILogger;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.util.Images;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.osgi.framework.Bundle;

/**
 * The registry reader
 * 
 * @author Idull
 */
public class SchemaObjectEditorExtensionsRegistryReader implements ISchemaObjectEditorExtensionsRegistryReader
{
    private List                                              _editors;
    private static SchemaObjectEditorExtensionsRegistryReader _instance;
    private ILogger                                           _logger = SOEUIPlugin.getLogger(null);

    private SchemaObjectEditorExtensionsRegistryReader()
    {

    }

    Image _image = null;

    public static synchronized SchemaObjectEditorExtensionsRegistryReader getInstance()
    {
        if (_instance == null)
        {
            _instance = new SchemaObjectEditorExtensionsRegistryReader();
        }
        return _instance;
    }

    public IEditorDescriptor[] getEditorDescriptors()
    {
        if (_editors == null)
        {
            init();
        }
        return _editors == null ? new IEditorDescriptor[0] : (IEditorDescriptor[]) _editors
                .toArray(new IEditorDescriptor[_editors.size()]);
    }

    private void init()
    {
        _editors = new ArrayList();
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        IExtensionPoint point = registry.getExtensionPoint(Constants.PLUGIN_ID, Constants.EXTENSION_POINT_ID);
        if (point != null)
        {
            IExtension[] extensions = point.getExtensions();
            if (extensions != null)
            {
                for (int i = 0; i < extensions.length; i++)
                {
                    IConfigurationElement[] elements = extensions[i].getConfigurationElements();
                    IEditorDescriptor editor = null;
                    for (int j = 0; j < elements.length; j++)
                    {
                        if (elements[j].getName().equals(Constants.EXTENSION_POINT_PAGE))
                        {
                            continue;
                        }
                        String editorId = elements[j].getAttribute(Constants.EXTENSION_POINT_EDITOR_ID);
                        String editorName = elements[j].getAttribute(Constants.EXTENSION_POINT_EDITOR_NAME);
                        String objectType = elements[j].getAttribute(Constants.EXTENSION_POINT_EDITOR_OBJECT_TYPE_ID);
                        String vendorName = elements[j].getAttribute(Constants.EXTENSION_POINT_EDITOR_VENDOR_NAME);
                        String version = elements[j].getAttribute(Constants.EXTENSION_POINT_EDITOR_DB_VERSION);
                        String iconLoc = elements[j].getAttribute(Constants.EXTENSION_POINT_EDITOR_ICON);
                        String objectTypeName = elements[j]
                                .getAttribute(Constants.EXTENSION_POINT_EDITOR_OBJECT_TYPE_NAME);
                        String pluginNamespace = extensions[i].getNamespaceIdentifier();
                        Bundle bundle = Platform.getBundle(pluginNamespace);
                        Image icon = readImage(bundle, iconLoc);
                        String needGenPrefGrpString = elements[j]
                                .getAttribute(Constants.EXTENSION_POINT_EDITOR_GENERATE_PREF_GROUP);

                        // true by default
                        boolean needGenPrefGrp = true;
                        if (needGenPrefGrpString != null)
                        {
                            needGenPrefGrp = needGenPrefGrpString.equals("true");
                        }
                        editor = new EditorDescriptor(editorId, editorName, vendorName, version, objectType, icon,
                                needGenPrefGrp);
                        editor.setPluginId(elements[j].getDeclaringExtension().getNamespaceIdentifier());
                        editor.setObjectTypeName(objectTypeName);
                        String mandatoryFirstPage = "";
                        String mandatoryLastPage = "";
                        try
                        {
                            ISchemaObjectEditorHandler handler = (ISchemaObjectEditorHandler) elements[j]
                                    .createExecutableExtension(Constants.EXTENSION_POINT_EDITOR_HANDLER);
                            ISchemaObjectEditorActionBarContributor contributor = null;

                            // The contributor class can be left empty
                            try
                            {
                                contributor = (ISchemaObjectEditorActionBarContributor) elements[j]
                                        .createExecutableExtension(Constants.EXTENSION_POINT_EDITOR_CONTRIBUTOR_CLASS);

                            }
                            catch (Exception e)
                            {
                                // ignore
                            }

                            mandatoryFirstPage = elements[j]
                                    .getAttribute(Constants.EXTENSION_POINT_EDITOR_MANDATORYFRISTPAGE);
                            mandatoryLastPage = elements[j]
                                    .getAttribute(Constants.EXTENSION_POINT_EDITOR_MANDATORYLASTPAGE);

                            editor.setHandler(handler);
                            editor.setActionContributor(contributor);
                            ((EditorDescriptor) editor).setConfigElement(elements[j]);
                            ((EditorDescriptor) editor).setContributorElement(elements[j]);
                        }
                        catch (Exception e)
                        {
                            _logger.error("SchemaObjectEditorExtensionRegistryReader_error_reading_editor", e);
                        }
                        List pages = new ArrayList();

                        // Read the referenced editor(s)
                        IConfigurationElement[] referencedEditors = elements[j]
                                .getChildren(Constants.EXTENSION_POINT_EDITOR_REFERENCED_EDITOR);

                        for (int k = 0; k < referencedEditors.length; k++)
                        {
                            List excludePages = new ArrayList();
                            String id = referencedEditors[k].getAttribute(Constants.EXTENSION_POINT_EDITOR_ID);

                            IConfigurationElement[] excludes = referencedEditors[k]
                                    .getChildren(Constants.EXTENSION_POINT_EDITOR_REFERENCED_EDITOR_EXCLUDES);
                            if (excludes != null && excludes.length > 0)
                            {
                                IConfigurationElement[] excludeElements = excludes[0]
                                        .getChildren(Constants.EXTENSION_POINT_EDITOR_REFERENCED_EDITOR_EXCLUDEPAGE);
                                for (int kk = 0; kk < excludeElements.length; kk++)
                                {
                                    String excludeId = excludeElements[kk]
                                            .getAttribute(Constants.EXTENSION_POINT_PAGE_ID);
                                    excludePages.add(excludeId);
                                }
                            }
                            IEditorPageDescriptor[] selectedPages = resolveReferenceEditor(id, editor, extensions,
                                    excludePages);
                            for (int kk = 0; kk < selectedPages.length; kk++)
                            {
                                pages.add(selectedPages[kk]);
                            }
                        }

                        // Read self-defined editor pages
                        IConfigurationElement[] declaredPages = elements[j].getChildren(Constants.EXTENSION_POINT_PAGE);
                        for (int k = 0; k < declaredPages.length; k++)
                        {
                            IEditorPageDescriptor page = readPage(declaredPages[k], editor);
                            if (page != null)
                            {
                                pages.add(page);
                            }
                        }

                        // Read referenced pages
                        IConfigurationElement[] referencedPages = elements[j]
                                .getChildren(Constants.EXTENSION_POINT_REFERENCED_PAGE);
                        for (int k = 0; k < referencedPages.length; k++)
                        {
                            try
                            {
                                String eId = referencedPages[k].getAttribute(Constants.EXTENSION_POINT_EDITOR_ID);
                                String pId = referencedPages[k].getAttribute(Constants.EXTENSION_POINT_PAGE_ID);
                                String pageExtensionId = referencedPages[k]
                                        .getAttribute(Constants.EXTENSION_POINT_PAGE_EXTENSION_ID);
                                IEditorPageDescriptor page = resolveReferencedPage(eId, pId, extensions, editor);
                                ((EditorPageDescriptor) page).setPageExtensionId(pageExtensionId);
                                if (page != null)
                                {
                                    pages.add(page);
                                }

                            }
                            catch (Exception e)
                            {
                                System.out.println(e.getMessage());
                            }
                        }

                        editor.setPageDescriptors((IEditorPageDescriptor[]) pages
                                .toArray(new IEditorPageDescriptor[pages.size()]));

                        // set the mandatory first/last page for the editor
                        if (mandatoryFirstPage != null && !mandatoryFirstPage.trim().equals(""))
                        {
                            IEditorPageDescriptor[] p = editor.getPageDescriptors();
                            for (int l = 0; l < p.length; l++)
                            {
                                if (p[l].getPageId().equals(mandatoryFirstPage.trim()))
                                {
                                    editor.setMandatoryFirstPage(p[l]);
                                }
                            }
                        }

                        if (mandatoryLastPage != null && !mandatoryLastPage.trim().equals(""))
                        {
                            IEditorPageDescriptor[] p = editor.getPageDescriptors();
                            for (int l = 0; l < p.length; l++)
                            {
                                if (p[l].getPageId().equals(mandatoryLastPage.trim()))
                                {
                                    editor.setMandatoryLastPage(p[l]);
                                }
                            }
                        }

                        IConfigurationElement[] orders = elements[j]
                                .getChildren(Constants.EXTENSION_POINT_EDITOR_DEFAULT_ORDER);
                        if (orders != null && orders.length > 0)
                        {
                            IConfigurationElement[] orderItems = orders[0]
                                    .getChildren(Constants.EXTENSION_POINT_EDITOR_ORDER_ITEM);
                            for (int kk = 0; kk < orderItems.length; kk++)
                            {
                                String pId = orderItems[kk].getAttribute(Constants.EXTENSION_POINT_PAGE_ID);
                                String orderNum = orderItems[kk]
                                        .getAttribute(Constants.EXTENSION_POINT_EDITOR_ORDER_NUM);
                                int order = -1;
                                try
                                {
                                    order = Integer.parseInt(orderNum);
                                    if (order < 1)
                                    {
                                        continue;
                                    }
                                }
                                catch (Exception e)
                                {
                                    continue;
                                }
                                IEditorPageDescriptor page = getPageById(pages, pId);
                                if (page != null)
                                {
                                    editor.getDefaultPagesOrder().put(page, new Integer(order));
                                }
                            }
                        }
                        _editors.add(editor);
                    }
                }
            }
        }
    }

    private IEditorPageDescriptor getPageById(List pages, String pId)
    {
        Iterator iter = pages.iterator();
        while (iter.hasNext())
        {
            IEditorPageDescriptor page = (IEditorPageDescriptor) iter.next();
            if (page.getPageId().equals(pId))
            {
                return page;
            }
        }
        return null;
    }

    private IEditorPageDescriptor resolveReferencedPage(String editorId, String pageId, IExtension[] extensions,
            IEditorDescriptor editor)
    {
        for (int i = 0; i < extensions.length; i++)
        {
            IConfigurationElement[] elements = extensions[i].getConfigurationElements();
            for (int j = 0; j < elements.length; j++)
            {
                // is a standalone editor page
                if (elements[j].getName().equals(Constants.EXTENSION_POINT_PAGE))
                {
                    // if the editor id is empty, it references a standalone
                    // page
                    if (editorId == null || editorId.trim().length() == 0)
                    {
                        String pId = elements[j].getAttribute(Constants.EXTENSION_POINT_PAGE_ID);
                        if (pId.equals(pageId))
                        {
                            IEditorPageDescriptor page = readPage(elements[j], editor);
                            return page;
                        }
                        else
                        {
                            continue;
                        }
                    }
                    else
                    {
                        continue;
                    }
                }

                // match the editor id
                String eId = elements[j].getAttribute(Constants.EXTENSION_POINT_EDITOR_ID);
                if (!eId.equals(editorId))
                {
                    continue;
                }
                IConfigurationElement[] pages = elements[j].getChildren(Constants.EXTENSION_POINT_PAGE);
                for (int k = 0; k < pages.length; k++)
                {
                    // match the page id
                    String pId = pages[k].getAttribute(Constants.EXTENSION_POINT_PAGE_ID);
                    if (!pId.equals(pageId))
                    {
                        continue;
                    }
                    IEditorPageDescriptor page = readPage(pages[k], editor);
                    return page;
                }
            }
        }
        return null;
    }

    private IEditorPageDescriptor readPage(IConfigurationElement element, IEditorDescriptor editor)
    {
        IEditorPageDescriptor page = null;
        String pageId = element.getAttribute(Constants.EXTENSION_POINT_PAGE_ID);
        String pageName = element.getAttribute(Constants.EXTENSION_POINT_PAGE_NAME);
        String requiredStr = element.getAttribute(Constants.EXTENSION_POINT_PAGE_IS_REQUIRED);
        String visibleByDefaultStr = element.getAttribute(Constants.EXTENSION_POINT_PAGE_VISIBLE_BYDEFAULT);
        String contextHelpId = element.getAttribute(Constants.EXTENSION_POINT_PAGE_HELPID);
        String objectClassType = element.getAttribute(Constants.EXTENSION_POINT_PAGE_OBJECT_CLASS_TYPE);

        ISchemaObjectEditorPage pageClass = null;
        try
        {
            pageClass = (ISchemaObjectEditorPage) element
                    .createExecutableExtension(Constants.EXTENSION_POINT_PAGE_CLASS);
            boolean isRequired = false;
            if (requiredStr != null)
            {
                isRequired = requiredStr.equals("true");
            }
            boolean isVisibleByDefault = false;
            if (visibleByDefaultStr != null)
            {
                isVisibleByDefault = visibleByDefaultStr.equals("true");
            }
            page = new EditorPageDescriptor(editor.getEditorId(), pageId, pageName, isRequired, isVisibleByDefault,
                    pageClass, editor, element, null, contextHelpId, objectClassType);
            return page;
        }
        catch (Exception e)
        {
            _logger.error("SchemaObjectEditorExtensionRegistryReader_error_reading_page", e);
            return null;
        }
    }

    private IEditorPageDescriptor[] resolveReferenceEditor(String editorId, IEditorDescriptor editor,
            IExtension[] extensions, List excludes)
    {
        List selectedPages = new ArrayList();
        for (int i = 0; i < extensions.length; i++)
        {
            IConfigurationElement[] elements = extensions[i].getConfigurationElements();
            for (int j = 0; j < elements.length; j++)
            {
                // is a standalone editor page
                if (elements[j].getName().equals(Constants.EXTENSION_POINT_PAGE))
                {
                    continue;
                }

                // match the editor id
                String eId = elements[j].getAttribute(Constants.EXTENSION_POINT_EDITOR_ID);
                if (!eId.equals(editorId))
                {
                    continue;
                }
                IConfigurationElement[] pages = elements[j].getChildren(Constants.EXTENSION_POINT_PAGE);
                for (int k = 0; k < pages.length; k++)
                {
                    String pageId = pages[k].getAttribute(Constants.EXTENSION_POINT_PAGE_ID);
                    if (excludes.contains(pageId))
                    {
                        continue;
                    }
                    IEditorPageDescriptor page = readPage(pages[k], editor);
                    if (page != null)
                    {
                        selectedPages.add(page);
                    }
                }
            }
        }
        return (IEditorPageDescriptor[]) selectedPages.toArray(new IEditorPageDescriptor[selectedPages.size()]);
    }

    private Image readImage(final Bundle bundle, final String iconLoc)
    {
        // CR:471496-1DBGR: Failed to load source when debugging last launch configuration (F11)
        SOEUIPlugin.getActiveWorkbenchShell().getDisplay().syncExec(new Runnable()
        {

            public void run()
            {
                if (bundle == null || iconLoc == null || bundle.getEntry(iconLoc) == null)
                {
                    _image = null;
                    return;
                }
                String key = bundle.getBundleId() + "_" + iconLoc;
                Image img = Images.getImageRegistry().get(key);
                if (img == null)
                {
                    try
                    {
                        ImageDescriptor desp = ImageDescriptor.createFromURL(bundle.getEntry(iconLoc));
                        Images.getImageRegistry().put(key, desp);
                    }
                    catch (Exception ex)
                    {
                        _logger.error("SchemaObjectEditorExtensionsRegistryRead_error_read_image", ex);
                        _image = null;
                        return;
                    }
                }
                _image = Images.getImageRegistry().get(key);
            }
        });
        return _image;
    }
}
