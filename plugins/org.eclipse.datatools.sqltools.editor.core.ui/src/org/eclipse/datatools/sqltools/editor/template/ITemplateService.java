/***********************************************************************************************************************
 * Copyright (c) 2007 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.editor.template;

import java.util.HashMap;

import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;

/**
 * 
 * @author lihuang
 */
public interface ITemplateService
{

    /**
     * Return the corresponding SQLTemplate from template id. <br>
     * Each template id identifiers a SQL template. 'default-templates.xml' in each server plugin defines the supported
     * SQL templates.
     * 
     * @param templateId
     * @param params key-value pairs include SQLEDITOR - SQLEditor instance, the name of template variable - the value
     *            user input into SQL Editor.
     * @param databaseIdentifier
     * @return
     */
    public SQLTemplate getIntelligenceTemplate(String templateId, HashMap params,
            DatabaseIdentifier databaseIdentifier);
}
