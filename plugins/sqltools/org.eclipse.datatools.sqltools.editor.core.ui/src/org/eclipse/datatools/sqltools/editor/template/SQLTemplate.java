/***********************************************************************************************************************
 * Copyright (c) 2007 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.editor.template;

import org.eclipse.jface.text.templates.Template;

/**
 * 
 * This class extends <code>Template</code> to add attribute 'template id'.
 * 
 * @author lihuang
 */
public class SQLTemplate extends Template implements TemplateConstant
{

    /** The id of this template */
    private/* final */String _id;

    private String _proposalDescription;
    /**
     * Creates an empty template.
     */
    public SQLTemplate()
    {
        super("", "", "", "", true); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    }

    /**
     * Creates a copy of a template.
     * 
     * @param template the template to copy
     */
    public SQLTemplate(SQLTemplate template)
    {
        super(template);
    }

    /**
     * Creates a template.
     * 
     * @param name the name of the template
     * @param description the description of the template
     * @param contextTypeId the id of the context type in which the template can be applied
     * @param pattern the template pattern
     * 
     */
    public SQLTemplate(String id, String name, String description, String contextTypeId, String pattern)
    {
        this(id, name, description, contextTypeId, pattern, true, ""); // templates are auto insertable per default
    }

    /**
     * Creates a template.
     * 
     * @param name the name of the template
     * @param description the description of the template
     * @param contextTypeId the id of the context type in which the template can be applied
     * @param pattern the template pattern
     * @param isAutoInsertable the auto insertable property of the template
     * @since 3.1
     */
    public SQLTemplate(String id, String name, String description, String contextTypeId, String pattern,
            boolean isAutoInsertable, String proposalDescription)
    {
        super(name, description, contextTypeId, pattern, isAutoInsertable);
        _id = id;
        _proposalDescription = proposalDescription;
    }

    /*
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object o)
    {
        if (!(o instanceof SQLTemplate))
        {
            return false;
        }

        SQLTemplate t = (SQLTemplate) o;
        if (t == this)
        {
            return true;
        }

        return t.getId().equals(getId()) && t.getName().equals(getName()) && t.getPattern().equals(getPattern())
                && t.getContextTypeId().equals(getContextTypeId()) && t.getDescription().equals(getDescription())
                && t.isAutoInsertable() == isAutoInsertable();
    }

    public String getId()
    {
        return _id;
    }

    public void setId(String id)
    {
        this._id = id;
    }
    
    public String getProposalPopupDescription()
    {
        return _proposalDescription;
    }
}
