/*
 *************************************************************************
 * Copyright (c) 2009 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.spec;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.spec.manifest.ExtensionContributor;


/**
 * <strong>EXPERIMENTAL</strong>.
 * The context for validation of an ODA expression in a query specification.
 * It may include a custom tester and/or the contributor of the expression being validated.
 * @since 3.2 (DTP 1.7)
 */
public class ValidationContext
{
    private ExtensionContributor m_contributor;
    private ITester m_tester;
    private Object m_contextData;
    
    public ValidationContext( ExtensionContributor contributor )
    {
        m_contributor = contributor;
    }
    
    public ValidationContext( ITester tester )
    {
        m_tester = tester;
    }
    
    /**
     * Gets the contributor of a custom filter expression being validated.
     * @return  an instance of the {@link ExtensionContributor} that defines its scope and capabilities,
     *      or null if none is available
     */
    public ExtensionContributor getContributor()
    {
        return m_contributor;
    }

    /**
     * Gets the custom tester of a filter expression being validated.
     * @return  an {@link ITester} instance, or null if none is available
     */
    public ITester getTester()
    {
        if( m_tester != null )
            return m_tester;
        
        if( m_contributor != null )
        {
            try
            {
                m_tester = m_contributor.getTester();
                return m_tester;
            }
            catch( OdaException ex )
            {
                // TODO log warning
            }
        }
        
        return null;
    }
    
    /**
     * Sets the custom tester of a filter expression being validated.
     * @param tester    an {@link ITester} instance
     */
    public void setTester( ITester tester )
    {
        m_tester = tester;
    }
    
    /**
     * Gets the value(s) of extension-defined property.
     * An extension contributor may set arbitrary objects for use at validation. 
     * @return      the context value, or null if none is set
     */
    public Object getData()
    {
        return m_contextData;
    }

    /**
     * Sets the value(s) of extension-defined property.
     * An extension contributor may use this to specify arbitrary objects for use at validation.
     * @param contextData  context value
     */
    public void setData( Object contextData )
    {
        m_contextData = contextData;
    }
    
}
