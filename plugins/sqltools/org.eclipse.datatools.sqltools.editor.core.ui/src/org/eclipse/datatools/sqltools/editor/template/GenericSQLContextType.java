/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.editor.template;

import org.eclipse.datatools.sqltools.sql.parser.ParsingResult;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.templates.GlobalTemplateVariables;
import org.eclipse.jface.text.templates.TemplateBuffer;
import org.eclipse.jface.text.templates.TemplateContext;
import org.eclipse.jface.text.templates.TemplateContextType;

/**
 * A generic SQL context type defines a context within which generic SQL statement templates are resolved. It
 * stores a number of <code>TemplateVariableResolver</code>s. A
 * <code>TemplateBuffer</code> can be resolved in a
 * <code>TemplateContext</code> using the
 * {@link #resolve(TemplateBuffer, TemplateContext)} method.
 * <p>
 * Clients may extend this class to provide vendor specific template support.
 * </p>
 *
 * @author Hui Cao
 * 
 */
public class GenericSQLContextType extends TemplateContextType {

    /** This context's id */
    public static final String SQL_CONTEXT_TYPE = "org.eclipse.datatools.sqltools.editor.template.sql.generic";

    /**
     *  
     */
    public GenericSQLContextType()
    {
        addResolver(new GlobalTemplateVariables.Cursor());
        addResolver(new GlobalTemplateVariables.WordSelection());
        addResolver(new GlobalTemplateVariables.LineSelection());
        addResolver(new GlobalTemplateVariables.Dollar());
        addResolver(new GlobalTemplateVariables.Date());
        addResolver(new GlobalTemplateVariables.Year());
        addResolver(new GlobalTemplateVariables.Time());
        addResolver(new GlobalTemplateVariables.User());

        addResolver(new CursorNameResolver());

    }

    public GenericSQLContext createContext(IDocument document, int completionOffset, int completionLength,
        ParsingResult result)
    {
        return new GenericSQLContext(this, document, completionOffset, completionLength, result);
    }

    /**
     * return the compatible context type ids. subclass should override this method.
     * 
     * @return
     */
    public String[] getIds()
    {
        return new String[] 
        {
            getSQLContextId()
        }
        ;
    }

    /**
     * @return Returns the sQL_CONTEXT_TYPE.
     */
    public String getSQLContextId()
    {
        return SQL_CONTEXT_TYPE;
    }
}
