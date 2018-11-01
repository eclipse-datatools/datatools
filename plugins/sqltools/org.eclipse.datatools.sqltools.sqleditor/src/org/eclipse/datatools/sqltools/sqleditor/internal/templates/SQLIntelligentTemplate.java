/***********************************************************************************************************************
 * Copyright (c) 2007 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.sqleditor.internal.templates;

import java.util.HashMap;

import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.editor.template.SQLTemplate;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;

/**
 * 
 * @author lihuang
 */
public class SQLIntelligentTemplate extends SQLTemplate
{
    protected HashMap            _params            = null;
    protected DatabaseIdentifier _databaseIdentifier;
    private HashMap              _valueDatatypePair = new HashMap();

    public SQLIntelligentTemplate()
    {
    }

    public SQLIntelligentTemplate(HashMap params, String contextTypeId, DatabaseIdentifier databaseIdentifier)
    {
        super("", "", "", contextTypeId, "", true, "");
        _params = params;
        _databaseIdentifier = databaseIdentifier;
        SQLEditorPlugin.getDefault().getTemplateStore().registerIntelligentTemplate(this);
    }

    protected String generatePattern()
    {
        return null;
    }

    public HashMap getKeyValuePair()
    {
        return _valueDatatypePair;
    }

    public void setKeyValuePair(String key, String value)
    {
        _valueDatatypePair.put(key, value);
    }

    /**
     * get the string to be used when presenting the context from the selected value.
     * 
     * @param value
     * @return
     */
    public String getContextInformation(String value)
    {
        String type = (String) getKeyValuePair().get(value);
        return type;
    }
    


}