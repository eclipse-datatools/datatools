/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples;

import org.eclipse.datatools.sqltools.editor.template.GenericSQLContext;
import org.eclipse.datatools.sqltools.sql.parser.ParsingResult;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.templates.TemplateContextType;


/**
 * @author Hui Cao
 *  
 */
public class ASAContext extends GenericSQLContext
{

    /**
     * @param type
     * @param document
     * @param completionOffset
     * @param completionLength
     * @param result
     */
    public ASAContext(TemplateContextType type, IDocument document, int completionOffset, int completionLength,
        ParsingResult result)
    {
        super(type, document, completionOffset, completionLength, result);
        // TODO Auto-generated constructor stub
    }

}
