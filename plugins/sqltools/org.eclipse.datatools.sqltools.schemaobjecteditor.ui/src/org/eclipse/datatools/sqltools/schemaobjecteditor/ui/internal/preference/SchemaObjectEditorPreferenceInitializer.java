/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal.preference;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.extensions.IEditorDescriptor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.extensions.IEditorPageDescriptor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal.SOEUIPlugin;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal.Constants;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal.ISchemaObjectEditorExtensionsRegistryReader;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal.SchemaObjectEditorExtensionsRegistryReader;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * The preference initializer for schema object editors
 * 
 * @author Idull
 */
public class SchemaObjectEditorPreferenceInitializer extends AbstractPreferenceInitializer
{

    public SchemaObjectEditorPreferenceInitializer()
    {

    }

    public void initializeDefaultPreferences()
    {
        IPreferenceStore store = SOEUIPlugin.getDefault().getPreferenceStore();
        store.setDefault(Constants.PREFERENCE_ALWAYS_SHOW_PREVIEW, true);
        store.setDefault(Constants.PREFERENCE_USE_LATEST_VERSION, true);
        store.setDefault(Constants.PREFERENCE_CHECK_EXISTENCE, false);
        store.setDefault(Constants.PREFERENCE_OPEN_FILE_AFTER_SAVEAS, true);

        ISchemaObjectEditorExtensionsRegistryReader reader = SchemaObjectEditorExtensionsRegistryReader.getInstance();
        IEditorDescriptor[] editors = reader.getEditorDescriptors();

        if (editors != null)
        {
            for (int i = 0; i < editors.length; i++)
            {
                IEditorPageDescriptor[] pages = editors[i].getPageDescriptors();
                for (int j = 0; j < pages.length; j++)
                {
                    String prefName = Constants.EDITOR_PAGE_VISIABILITY + editors[i].getEditorId()
                            + pages[j].getPageId();
                    store.setDefault(prefName, pages[j].isVisibleByDefault() || pages[j].isRequired()
                            || !pages[j].getEditor().isVisibilityConfigurable());

                    Integer order = (Integer) editors[i].getDefaultPagesOrder().get(pages[j]);
                    if (order != null)
                    {
                        String orderPrefName = Constants.EDITOR_PAGE_ORDER + pages[j].getEditorId()
                                + pages[j].getPageId();
                        store.setDefault(orderPrefName, order.intValue());
                    }
                }
            }
        }
    }

}
