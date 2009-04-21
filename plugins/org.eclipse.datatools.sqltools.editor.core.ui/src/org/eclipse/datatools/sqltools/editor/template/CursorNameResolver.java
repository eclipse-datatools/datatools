/**
 * Created on 2004-9-27
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.editor.template;

import org.eclipse.datatools.sqltools.editor.internal.template.Messages;
import org.eclipse.jface.text.templates.TemplateContext;
import org.eclipse.jface.text.templates.TemplateVariable;
import org.eclipse.jface.text.templates.TemplateVariableResolver;


/**
 * @author Hui Cao
 *  
 */
public class CursorNameResolver extends TemplateVariableResolver
{
    public CursorNameResolver()
    {
        super("cursor_name", Messages.CursorNameResolver_description); 
    }

    protected String[] resolveAll(TemplateContext context)
    {
        return ((GenericSQLContext) context).getCursorNames();
    }

    /*
     * @see org.eclipse.jface.text.templates.TemplateVariableResolver#resolve(org.eclipse.jface.text.templates.TemplateVariable,
     *      org.eclipse.jface.text.templates.TemplateContext)
     */
    public void resolve(TemplateVariable variable, TemplateContext context)
    {
        super.resolve(variable, context);
    }

    protected String resolve(TemplateContext context)
    {
        return super.resolve(context);
    }
}
