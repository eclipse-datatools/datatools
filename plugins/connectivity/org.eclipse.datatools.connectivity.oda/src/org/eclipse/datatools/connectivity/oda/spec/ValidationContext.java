/*
 *************************************************************************
 * Copyright (c) 2009, 2010 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.spec;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.spec.manifest.ExtensionContributor;


/**
 * The context for validation of a query specification and associated query specification expressions.
 * It may include a custom validator and/or the contributor of the expression being validated.
 * @since 3.3 (DTP 1.8)
 */
public class ValidationContext
{
    private static final String NAMESPACE = ValidationContext.class.getName();
    
    /**
     * Pre-defined property names of validation context data that may be used at validation
     */
    protected static final String DATA_PROPERTY_QUERY_TEXT = NAMESPACE + ".QueryTextProp"; //$NON-NLS-1$
    protected static final String DATA_PROPERTY_CONNECTION = NAMESPACE + ".ConnProp";  //$NON-NLS-1$
    
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
    
    /**
     * Gets the optional query text specified in this context.
     * @return  the query text specified in context, may be null if none is specified
     */
    public String getQueryText()
    {
        Object value = getData( DATA_PROPERTY_QUERY_TEXT );
        return (value instanceof String) ? (String) value : null;
    }
    
    /**
     * Sets the query text specified in this context.
     * @param queryText query text; may be null to unset previous value
     */
    public void setQueryText( String queryText )
    {
        setData( DATA_PROPERTY_QUERY_TEXT, queryText );
    }
    
    /**
     * Gets the optional connection context that may be used by an {@link IValidator} implementation
     * for online validation.
     * A client may optimize performance by re-using its properties for opening a related connection 
     * to prepare and execute a query.     
     * @return  the connection context for online validation; may be null if none is specified
     */
    public Connection getConnection()
    {
        Object value = getData( DATA_PROPERTY_CONNECTION );
        return (value instanceof Connection) ? (Connection)value : null;
    }

    /**
     * Sets the connection context for online validation.
     * The connection context, if exists, may be used by an {@link IValidator} implementation
     * to open a connection to perform online validation.
     * @param props connection properties for opening a connection for online validation 
     */
    public void setConnection( Connection validationConn )
    {
        setData( DATA_PROPERTY_CONNECTION, validationConn );
    }
    
    
    /**
     * A connection context for online validation.  An instance can be shared 
     * by multiple ValidationContext.  
     * Its optional use by a client would expand the scope of validation performed.  
     * An instance must contain the connection properties needed by a validator to open a connection.
     * An {@link IValidator} implementation may add its own name-value pairs to its properties, 
     * such as a connection handle, for optimizing its performance.  
     * The client is responsible to close the connection context when it is no longer needed.
     */
    public class Connection
    {
        private Properties m_properties;
        
        public Connection( Properties props )
        {
            m_properties = props;
        }
        
        public Properties getProperties()
        {
            return m_properties;
        }

        public void setProperties( Properties props )
        {
            if( ! hasProperties( props ) )
            {
                // close any existing connection handle before replacing the existing properties
                close();    
                m_properties = props;
            }
        }
        
        /**
         * Closes this connection context.
         * <br>The client is responsible to close this when it is no longer needed.
         */
        public void close()
        {
            if( m_properties != null && ! m_properties.isEmpty() )
            {
                IValidator validator = getValidator();
                if( validator != null )
                    validator.closeConnection( this );
            }
        }
        
        /*
         * Checks whether this connection's existing properties 
         * contain each and every key-value pair of the specified props.
         * It is ok if existing properties have additional entries than the specified ones.
         */
        private boolean hasProperties( Properties props )
        {
            if( props == null || props.isEmpty() || props == m_properties )
                return true;
            if( m_properties == null || m_properties.isEmpty() )
                return false;

            if( props.size() > m_properties.size() )
                return false;

            // check whether the key and value of each specified property exist
            for( Entry<Object, Object> newPropEntry : props.entrySet() )
            {
                Object key = newPropEntry.getKey();
                Object value = newPropEntry.getValue();
                if( value == null )
                {
                    if( !( m_properties.containsKey( key ) && m_properties.get( key ) == null ) )
                        return false;
                }
                else
                {
                    if( ! value.equals( m_properties.get( key ) ) )
                        return false;
                }
            }

            return true;
        }
    }
    
}
