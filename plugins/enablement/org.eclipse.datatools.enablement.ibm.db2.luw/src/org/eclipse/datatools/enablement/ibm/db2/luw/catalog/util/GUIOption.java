/*******************************************************************************
 * Copyright (c) 2004, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.db2.luw.catalog.util;

import java.util.Vector;

public class GUIOption
{
    public GUIOption(String aName, Vector theValues, String defVal,
                     boolean editable, boolean required, boolean multivalueAllowed,
                     boolean alter, String delimiter, String platform, String hints,
                     String description)
    {
        name = aName;
        values = theValues;
        defaultValue = defVal;
        this.editable = editable;
        this.required = required;
        this.multivalueAllowed = multivalueAllowed;
        this.alter = alter;
        this.delimiter = delimiter;
        this.platform = platform;
        this.hints = hints;
        this.description = description;
    }
    public void setName(String aName)
    {
        name = aName;
    }
    public String getName()
    {
        return name;
    }
    public void setValues(Vector vals)
    {
        values = vals;
    }
    public Vector getValues()
    {
        return values;
    }
    public void setDefaultValue(String defVal)
    {
        defaultValue = defVal;
    }
    public String getDefaultValue()
    {
        return defaultValue;
    }
    public void setEditable(boolean editable)
    {
        this.editable = editable;
    }
    public boolean isEditable()
    {
        return editable;
    }
    public void setRequired(boolean required)
    {
        this.required = required;
    }
    public boolean isRequired()
    {
        return required;
    }
    public boolean isMultiValueAllowed()
    {
        return multivalueAllowed;
    }
    public boolean isAlteringAllowed()
    {
        return alter;
    }

    public void setDelimiter(String d)
    {
        delimiter = d;
    }
    public String getDelimiter()
    {
        return delimiter;
    }
    public void setPlatform(String p)
    {
        platform = p;
    }
    public String getPlatform()
    {
        return platform;
    }
    public void setHints(String h)
    {
        hints = h;
    }
    public String getHints()
    {
        return hints;
    }
    public void setDescription(String d)
    {
        description = d;
    }
    public String getDescription()
    {
        return description;
    }

    /* Name of the column*/
    private String name;
    /* Possible values */
    private Vector values;
    /* Default Value */
    private String defaultValue;
    /*Is this GUI component editable*/
    private boolean editable;
    /* Is thins GUI component required*/
    private boolean required;

    private String delimiter;
    private boolean multivalueAllowed;
    private boolean alter;
    private String platform;
    private String hints;
    private String description;
}

