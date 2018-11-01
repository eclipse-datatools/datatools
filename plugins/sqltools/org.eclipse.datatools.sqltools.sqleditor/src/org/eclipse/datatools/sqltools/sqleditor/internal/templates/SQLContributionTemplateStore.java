/***********************************************************************************************************************
 * Copyright (c) 2007 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.sqleditor.internal.templates;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.sqltools.editor.template.SQLTemplate;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.templates.ContextTypeRegistry;
import org.eclipse.jface.text.templates.Template;
import org.eclipse.jface.text.templates.TemplateException;
import org.eclipse.jface.text.templates.persistence.TemplatePersistenceData;
import org.eclipse.jface.text.templates.persistence.TemplateStore;
import org.osgi.framework.Bundle;

/**
 * 
 * @author lihuang
 */
public class SQLContributionTemplateStore extends TemplateStore
{
    /* extension point string literals */
    private static final String TEMPLATES_EXTENSION_POINT = "org.eclipse.ui.editors.templates"; //$NON-NLS-1$

    private static final String ID                        = "id";                              //$NON-NLS-1$
    private static final String NAME                      = "name";                            //$NON-NLS-1$

    private static final String CONTEXT_TYPE_ID           = "contextTypeId";                   //$NON-NLS-1$
    private static final String DESCRIPTION               = "description";                     //$NON-NLS-1$
    private static final String AUTO_INSERT               = "autoinsert";                      //$NON-NLS-1$

    private static final String TEMPLATE                  = "template";                        //$NON-NLS-1$
    private static final String PATTERN                   = "pattern";                         //$NON-NLS-1$

    private static final String INCLUDE                   = "include";                         //$NON-NLS-1$
    private static final String FILE                      = "file";                            //$NON-NLS-1$
    private static final String TRANSLATIONS              = "translations";                    //$NON-NLS-1$
    private static final String PROPOSAL_POPUP_DESCRIPTION = "proposalPopupDescription";

    private HashMap _intelligentTemplateList = new HashMap();
    
    /**
     * Creates a new template store.
     * 
     * @param store the preference store in which to store custom templates under <code>key</code>
     * @param key the key into <code>store</code> where to store custom templates
     */
    public SQLContributionTemplateStore(IPreferenceStore store, String key)
    {
        super(store, key);
    }

    /**
     * Creates a new template store with a context type registry. Only templates that specify a context type contained
     * in the registry will be loaded by this store if the registry is not <code>null</code>.
     * 
     * @param registry a context type registry, or <code>null</code> if all templates should be loaded
     * @param store the preference store in which to store custom templates under <code>key</code>
     * @param key the key into <code>store</code> where to store custom templates
     */
    public SQLContributionTemplateStore(ContextTypeRegistry registry, IPreferenceStore store, String key)
    {
        super(registry, store, key);
    }

    /**
     * Loads the templates contributed via the templates extension point.
     * 
     * @throws IOException {@inheritDoc}
     */
    protected void loadContributedTemplates() throws IOException
    {
        IConfigurationElement[] extensions = getTemplateExtensions();
        Collection contributed = readContributedTemplates(extensions);
        for (Iterator it = contributed.iterator(); it.hasNext();)
        {
            TemplatePersistenceData data = (TemplatePersistenceData) it.next();
            internalAdd(data);
        }
    }

    private Collection readContributedTemplates(IConfigurationElement[] extensions) throws IOException
    {
        Collection templates = new ArrayList();
        for (int i = 0; i < extensions.length; i++)
        {
            if (extensions[i].getName().equals(TEMPLATE))
            {
                createTemplate(templates, extensions[i]);
            }
            else if (extensions[i].getName().equals(INCLUDE))
            {
                readIncludedTemplates(templates, extensions[i]);
            }
        }

        return templates;
    }

    private void readIncludedTemplates(Collection templates, IConfigurationElement element) throws IOException
    {
        String file = element.getAttribute(FILE);
        if (file != null)
        {
            Bundle plugin = Platform.getBundle(element.getContributor().getName());
            URL url = FileLocator.find(plugin, Path.fromOSString(file), null);
            if (url != null)
            {
                ResourceBundle bundle = null;
                InputStream bundleStream = null;
                InputStream stream = null;
                try
                {
                    String translations = element.getAttribute(TRANSLATIONS);
                    if (translations != null)
                    {
                        URL bundleURL = FileLocator.find(plugin, Path.fromOSString(translations), null);
                        if (bundleURL != null)
                        {
                            bundleStream = bundleURL.openStream();
                            bundle = new PropertyResourceBundle(bundleStream);
                        }
                    }

                    stream = new BufferedInputStream(url.openStream());
                    SQLTemplateReaderWriter reader = new SQLTemplateReaderWriter();
                    TemplatePersistenceData[] datas = reader.read(stream, bundle);
                    for (int i = 0; i < datas.length; i++)
                    {
                        TemplatePersistenceData data = datas[i];
                        if (data.isCustom())
                        {
                            if (data.getId() == null)
                            {
                                SQLEditorPlugin.getDefault().log(
                                        (Messages.format(Messages.ContributionTemplateStore_ignore_no_id, data
                                                .getTemplate().getName())));
                            }
                            else
                            {
                                SQLEditorPlugin.getDefault().log(
                                        Messages.format(Messages.ContributionTemplateStore_ignore_deleted, data
                                                .getTemplate().getName()));
                            }
                        }
                        else if (!validateTemplate(data.getTemplate()))
                        {
                            if (contextExists(data.getTemplate().getContextTypeId()))
                            {
                                SQLEditorPlugin.getDefault().log(
                                        Messages.format(Messages.ContributionTemplateStore_ignore_validation_failed,
                                                data.getTemplate().getName()));
                            }
                        }
                        else
                        {
                            templates.add(data);
                        }
                    }
                }
                finally
                {
                    try
                    {
                        if (bundleStream != null)
                        {
                            bundleStream.close();
                        }
                    }
                    catch (IOException x)
                    {
                    }
                    finally
                    {
                        try
                        {
                            if (stream != null)
                            {
                                stream.close();
                            }
                        }
                        catch (IOException x)
                        {
                        }
                    }
                }
            }
        }
    }

    /**
     * Validates a template against the context type registered in the context type registry. Returns always
     * <code>true</code> if no registry is present.
     * 
     * @param template the template to validate
     * @return <code>true</code> if validation is successful or no context type registry is specified,
     *         <code>false</code> if validation fails
     */
    private boolean validateTemplate(Template template)
    {
        String contextTypeId = template.getContextTypeId();
        if (!contextExists(contextTypeId))
        {
            return false;
        }

        if (getRegistry() != null)
        {
            try
            {
                getRegistry().getContextType(contextTypeId).validate(template.getPattern());
            }
            catch (TemplateException e)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns <code>true</code> if a context type id specifies a valid context type or if no context type registry is
     * present.
     * 
     * @param contextTypeId the context type id to look for
     * @return <code>true</code> if the context type specified by the id is present in the context type registry, or
     *         if no registry is specified
     */
    private boolean contextExists(String contextTypeId)
    {
        return contextTypeId != null && (getRegistry() == null || getRegistry().getContextType(contextTypeId) != null);
    }

    private static IConfigurationElement[] getTemplateExtensions()
    {
        return Platform.getExtensionRegistry().getConfigurationElementsFor(TEMPLATES_EXTENSION_POINT);
    }

    private void createTemplate(Collection map, IConfigurationElement element)
    {
        String contextTypeId = element.getAttribute(CONTEXT_TYPE_ID);
        // log failures since extension point id and name are mandatory
        if (contextExists(contextTypeId))
        {
            String id = element.getAttribute(ID);
            if (isValidTemplateId(id))
            {

                String name = element.getAttribute(NAME);
                if (name != null)
                {

                    String pattern = element.getChildren(PATTERN)[0].getValue();
                    if (pattern != null)
                    {

                        String desc = element.getAttribute(DESCRIPTION);
                        if (desc == null)
                        {
                            desc = ""; //$NON-NLS-1$
                        }

                        String autoInsert = element.getAttribute(AUTO_INSERT);
                        boolean bAutoInsert;
                        if (autoInsert == null)
                        {
                            bAutoInsert = true;
                        }
                        else
                        {
                            bAutoInsert = Boolean.valueOf(autoInsert).booleanValue();
                        }
                        
                        String proprosal = element.getAttribute(PROPOSAL_POPUP_DESCRIPTION);
                        if (proprosal != null)
                        {
                            System.out.println("asdf");
                        }
                        Template template = new Template(name, desc, contextTypeId, pattern, bAutoInsert);
                        TemplatePersistenceData data = new TemplatePersistenceData(template, true, id);
                        if (validateTemplate(template))
                        {
                            map.add(data);
                        }
                    }
                }
            }
        }
    }

    private static boolean isValidTemplateId(String id)
    {
        return id != null && id.trim().length() != 0;
    }

    /*
     * @see org.eclipse.jface.text.templates.persistence.TemplateStore#handleException(java.io.IOException)
     * @since 3.2
     */
    protected void handleException(IOException x)
    {
        SQLEditorPlugin.getDefault().log(x);
    }
    
    public void registerIntelligentTemplate(SQLTemplate template)
    {
        _intelligentTemplateList.put(template.getClass().getName(), template);
    }

    public SQLIntelligentTemplate getRegisteredIntelligentTemplate(String templateID)
    {
        SQLIntelligentTemplate sqlTemplate = null;
        for (int i = 0; i < _intelligentTemplateList.size(); i++)
        {
            SQLIntelligentTemplate template = (SQLIntelligentTemplate) _intelligentTemplateList.get(templateID);
            if (template != null)
            {
                sqlTemplate = template;
                break;
            }
        }
        return sqlTemplate;
    }
}
