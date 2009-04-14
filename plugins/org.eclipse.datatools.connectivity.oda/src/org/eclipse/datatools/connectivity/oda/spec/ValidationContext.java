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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.spec.manifest.ExtensionContributor;


/**
 * <strong>EXPERIMENTAL</strong>.
 * The context for validation of a query specification and associated expression instances.
 * It may include a custom validator and/or the contributor of the expression being validated.
 * @since 3.2 (DTP 1.7)
 */
public class ValidationContext
{
    private static final String NAMESPACE = ValidationContext.class.getName();
    
    /**
     * Pre-defined property names of validation context data that may be used at validation
     */
    public static final String DATA_PROPERTY_QUERY_TEXT = NAMESPACE + ".QueryTextProp"; //$NON-NLS-1$
    public static final String DATA_PROPERTY_CONN_PROFILE = NAMESPACE + ".ConnProfileProp";  //$NON-NLS-1$
    
    private ExtensionContributor m_contributor;
    private IValidator m_validator;
    private Map<String,Object> m_customData;
    
    public ValidationContext( ExtensionContributor contributor )
    {
        m_contributor = contributor;
    }
    
    public ValidationContext( IValidator validator )
    {
        m_validator = validator;
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
     * Gets the custom validator of a filter expression being validated.
     * @return  an {@link IValidator} instance, or null if none is available
     */
    public IValidator getValidator()
    {
        if( m_validator != null )
            return m_validator;
        
        if( m_contributor != null )
        {
            try
            {
                m_validator = m_contributor.getValidator();
                return m_validator;
            }
            catch( OdaException ex )
            {
                // TODO log warning
            }
        }
        
        return null;
    }
    
    /**
     * Sets the custom validator of a filter expression being validated.
     * @param validator    an {@link IValidator} instance
     */
    public void setValidator( IValidator validator )
    {
        m_validator = validator;
    }
    
    /**
     * Gets the value of a named property.
     * An extension contributor may associate arbitrary object to an extension-defined property 
     * for use at validation. 
     * @param key   the name of property
     * @return      the value of the named property, or null if it has not been set
     */
    public Object getData( String key )
    {
        if( m_customData == null )
            return null;
        return m_customData.get( key );
    }

    /**
     * Sets the value of a named property.
     * An extension contributor may associate arbitrary object to an extension-defined property 
     * for use at validation. 
     * @param key   the name of property
     * @param value the new value of the named property
     */
    public void setData( String key, Object value )
    {
        if( m_customData == null )
            m_customData = new HashMap<String,Object>();
        m_customData.put( key, value );
    }
    
}
