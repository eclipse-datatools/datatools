/*
 *************************************************************************
 * Copyright (c) 2004, 2006 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.util.manifest;

import org.eclipse.core.runtime.IConfigurationElement;

/**
 * A choice of property values for an extension-defined property.
 */
public class PropertyChoice
{
    private String m_name;
    private String m_displayName;
    private String m_value;
   
    PropertyChoice( IConfigurationElement choiceElement )
    {
        // no validation is done; up to the consumer to process
        m_name = choiceElement.getAttribute( "name" ); //$NON-NLS-1$
        m_displayName = ManifestExplorer.getElementDisplayName( choiceElement );  
        m_value = choiceElement.getAttribute( "value" ); //$NON-NLS-1$
    }
    
    /**
     * Returns the name of the choice element.
     * @return	the name of the choice
     */
    public String getName()
    {
        return m_name;
    }
    
    /**
     * Returns the display name of the choice element.
     * Defaults to the choice name if no display name is specified.
     * @return	the display name of the choice
     */
    public String getDisplayName()
    {
        return m_displayName;
    }
    
    /**
     * Returns the value of this choice of property values.
     * @return	the value of the choice.
     */
    public String getValue()
    {
        return m_value;
    }

}
