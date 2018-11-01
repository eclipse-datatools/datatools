/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples;

import org.eclipse.datatools.sqltools.editor.template.GenericSQLContext;
import org.eclipse.datatools.sqltools.editor.template.GenericSQLContextType;
import org.eclipse.datatools.sqltools.sql.parser.ParsingResult;
import org.eclipse.jface.text.IDocument;


/**
 * @author Hui Cao
 *  
 */
public class ASAContextType extends GenericSQLContextType
{
    /** This context's id */
    public static final String SQL_CONTEXT_TYPE = "com.sybase.stf.dmp.ui.sqleditor.template.sql.asa";

    /**
     * return the compatible context type ids. subclass should override this method.
     * 
     * @return
     */
    public String[] getIds()
    {
        return new String[] 
        {
            super.getSQLContextId(), getSQLContextId()
        }
        ;
    }

    public GenericSQLContext createContext(IDocument document, int completionOffset, int completionLength,
        ParsingResult result)
    {
        return new ASAContext(this, document, completionOffset, completionLength, result);
    }

    /**
     * @return Returns the sQL_CONTEXT_TYPE.
     */
    public String getSQLContextId()
    {
        return SQL_CONTEXT_TYPE;
    }

}
