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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.spec.result.ResultSetSpecification;

/**
 * <strong>EXPERIMENTAL</strong>.
 * Specification of the query characteristics to apply when preparing and executing 
 * a query text of an {@link IQuery}.
 * <br>It takes effect only if assigned prior to an IQuery prepares a query text.
 * If any part of the specification is not supported by a driver, its implementation 
 * should throw an OdaException at {@link IQuery#prepare(String)}.
 * @since 3.2 (DTP 1.7)
 */
public class QuerySpecification
{
    private ResultSetSpecification m_resultSpec;
    private Map<String,Object> m_propertyMap;
    private Map<ParameterIdentifier,Object> m_parameterValues;
    
    /**
     * Specifies the characteristics of all the result set(s) to be retrieved by
     * the associated {@link IQuery}.
     * @param resultSpec    specification of a query's result set(s)
     */
    public void setResultSetSpecification( ResultSetSpecification resultSpec )
    {
        m_resultSpec = resultSpec;
    }
    
    /**
     * Gets the current result set specification of an {@link IQuery}.
     * @return  the current {@link ResultSetSpecification}, or null if not specified
     */
    public ResultSetSpecification getResultSetSpecification()
    {
        return m_resultSpec;
    }

    /**
     * Specifies the value(s) of a data set query property.  
     * <br>A property may have multiple values kept in a {@link Collection}.
     * The property value specified here may be the same as the value set separately
     * by {@link IQuery#setProperty(String, String)}, which is set after 
     * a query is prepared.  
     * <br>A property value may be null, whose handling is specific to individual 
     * driver implementation.
     * An ODA consumer does not necessarily distinguish whether a property value
     * is not set or explicitly set to null.
     * @param propertyName  name of the property
     * @param value the value of the specified property; may be null
     */
    public void setProperty( String propertyName, Object value )
    {
        if( m_propertyMap == null )
            m_propertyMap = new HashMap<String,Object>(5);

        m_propertyMap.put( propertyName, value );
    }
    
    /**
     * Gets the value(s) of a data set query property.  
     * <br>A property may have multiple values kept in a {@link Collection}.
     * The property value specified here may be the same as the value set separately
     * by {@link IQuery#setProperty(String, String)}, which is set after 
     * a query is prepared.  
     * <br>Its handling is optional and specific to individual driver implementation
     * on whether to apply this property value(s) when preparing a query.
     * <br>If a property name is not recognized by the driver,
     * it should simply ignore, and not throw an exception.
     * <br>A property value may be null, whose handling is specific to individual 
     * driver implementation.
     * An ODA consumer does not necessarily distinguish whether a property value
     * is not set or explicitly set to null.  
     * @param propertyName  name of the property
     * @return value the value of the specified property; may be null
     */
    public Object getProperty( String propertyName )
    {
        if( m_propertyMap == null )
            return null;
        return m_propertyMap.get( propertyName );
    }

    /**
     * Specifies the values of all data set query properties, with each property name 
     * as the key to its corresponding value(s).
     * <br>A property may have multiple values kept in a {@link Collection}.
     * The property values specified here may be the same as those set separately
     * by {@link IQuery#setProperty(String, String)}, which are set after 
     * a query is prepared.  
     * <br>A property value may be null, whose handling is specific to individual 
     * driver implementation.
     * An ODA consumer does not necessarily distinguish whether a property value
     * is not set or explicitly set to null.  
     * @param propertyMap   a {@link Map} of data set query properties in name-value pairs
     */
    public void setProperties( Map<String,Object> propertyMap )
    {
        m_propertyMap = propertyMap;
    }
    
    /**
     * Gets the values of all data set query properties, with each property name 
     * as the key to its corresponding value(s).
     * <br>A property may have multiple values kept in a {@link Collection}.
     * The property values specified here may be the same as those set separately
     * by {@link IQuery#setProperty(String, String)}, which are set after 
     * a query is prepared.  
     * <br>Its handling is optional and specific to individual driver implementation
     * on whether to apply these property values when preparing a query.
     * <br>If a property name is not recognized by the driver,
     * it should simply ignore, and not throw an exception.
     * <br>A property value may be null, whose handling is specific to individual 
     * driver implementation.
     * An ODA consumer does not necessarily distinguish whether a property value
     * is not set or explicitly set to null.  
     * @return  a {@link Map} of all currently specified data set query properties 
     *          with each property name as the key to its corresponding value(s), 
     *          or null if none
     */
    public Map<String,Object> getProperties()
    {
        return m_propertyMap;
    }

    /**
     * Specifies the input value(s) of a data set query parameter, identified by its native name.  
     * <br>A parameter may have multiple input values kept in a {@link Collection}.
     * The parameter value specified here may be the same as the value set separately
     * by a query's set by data type method, which is set after a query is prepared.
     * <br>A value in primitive data type is specified in its corresponding object type.
     * A parameter value may be null, whose handling is specific to individual 
     * driver implementation.
     * @param parameterName native name of the parameter
     * @param value input value of the specified parameter
     * @see #setParameterValue(int, Object)
     */
    public void setParameterValue( String parameterName, Object value )
    {
        setParameterValue( new ParameterIdentifier( parameterName ), value );
    }

    /**
     * Specifies the input value(s) of a data set query parameter, identified by its id.  
     * <br>A parameter may have multiple input values kept in a {@link Collection}.
     * The parameter value specified here may be the same as the value set separately
     * by a query's set by data type method, which is set after a query is prepared.
     * <br>A value in primitive data type is specified in its corresponding object type.
     * A parameter value may be null, whose handling is specific to individual 
     * driver implementation.
     * @param parameterId   id of the parameter (1-based)
     * @param value input value of the specified parameter
     * @see #setParameterValue(String, Object)
     */
    public void setParameterValue( int parameterId, Object value )
    {
        setParameterValue( new ParameterIdentifier( parameterId ), value );
    }

    /**
     * Specifies the input value(s) of a data set query parameter, identified by native name or id.  
     * @param paramIdentifier   a {@link ParameterIdentifier}
     *                          that identifies an input parameter by its native name or id (1-based)
     * @param value input value of the specified parameter
     * @see #setParameterValue(String, Object)
     * @see #setParameterValue(int, Object)
     */
    public void setParameterValue( ParameterIdentifier paramIdentifier, Object value )
    {
        if( m_parameterValues == null )
            m_parameterValues = new HashMap<ParameterIdentifier,Object>(5);

        m_parameterValues.put( paramIdentifier, value );
    }
    
    /**
     * Gets the input value(s) of a data set query parameter, identified by its native name.  
     * <br>A parameter may have multiple input values kept in a {@link Collection}.
     * The parameter value specified here may be the same as the value set separately
     * by a query's set by data type method, which is set after a query is prepared.
     * <br>Its handling is optional and specific to individual driver implementation
     * on whether to apply the parameter input value(s) when preparing a query.
     * <br>A value in primitive data type is specified in its corresponding object type.
     * A parameter value may be null, whose handling is specific to individual 
     * driver implementation.
     * @param parameterName native name of the parameter
     * @return input value of the specified parameter
     * @see #getParameterValue(int)
     */
    public Object getParameterValue( String parameterName )
    {
        return getParameterValue( new ParameterIdentifier( parameterName ));
    }
    
    /**
     * Gets the input value(s) of a data set query parameter, identified by its native name.  
     * <br>A parameter may have multiple input values kept in a {@link Collection}.
     * The parameter value specified here may be the same as the value set separately
     * by a query's set by data type method, which is set after a query is prepared.
     * <br>Its handling is optional and specific to individual driver implementation
     * on whether to apply the parameter input value(s) when preparing a query.
     * <br>A value in primitive data type is specified in its corresponding object type.
     * A parameter value may be null, whose handling is specific to individual 
     * driver implementation.
     * @param parameterId   id of the parameter (1-based)
     * @return input value of the specified parameter
     * @see #getParameterValue(String)
     */
    public Object getParameterValue( int parameterId )
    {
        return getParameterValue( new ParameterIdentifier( parameterId ));
    }
    
    /**
     * Gets the input value(s) of a data set query parameter, identified by native name or id.  
     * @param paramIdentifier   a {@link ParameterIdentifier}
     *                          that identifies an input parameter by its native name or id (1-based)
     * @return  input value of the specified parameter
     * @see #getParameterValue(String)
     * @see #getParameterValue(int)
     */
    public Object getParameterValue( ParameterIdentifier paramIdentifier )
    {
        if( m_parameterValues == null )
            return null;
        return m_parameterValues.get( paramIdentifier );
    }
    
    /**
     * Specifies the values of all input parameters of the associated {@link IQuery}, with  
     * each parameter identified by name or id as the key to its corresponding input value(s).
     * <br>A parameter may have multiple input values kept in a {@link Collection}.
     * The parameter values specified here may be the same as those set separately
     * by a query's set by data type methods, which are set after a query is prepared.
     * <br>A value in primitive data type is specified in its corresponding object type.
     * A parameter value may be null, whose handling is specific to individual 
     * driver implementation.
     * @param paramValues   a {@link Map} of {@link ParameterIdentifier} as the key 
     *              to its corresponding input value(s)
     */
    public void setParameterValues( Map<ParameterIdentifier,Object> paramValues )
    {
        m_parameterValues = paramValues;
    }
    
    /**
     * Gets the values of all input parameters of the associated {@link IQuery}, with  
     * each parameter identified by name or id as the key to its corresponding input value(s).
     * <br>A parameter may have multiple input values kept in a {@link Collection}.
     * The parameter values specified here may be the same as those set separately
     * by a query's set by data type methods, which are set after a query is prepared.
     * <br>Its handling is optional and specific to individual driver implementation
     * on whether to apply these parameter input values when preparing a query.
     * <br>A value in primitive data type is specified in its corresponding object type.
     * A parameter value may be null, whose handling is specific to individual 
     * driver implementation.
     * @return  a {@link Map} of all currently specified data set query parameters 
     *          with each {@link ParameterIdentifier} as the key 
     *          to its corresponding input value(s)
     */
    public Map<ParameterIdentifier,Object> getParameterValues()
    {
        return m_parameterValues;
    }
    
    /**
     * The identifier of a data set query parameter, defined by its native name or id (1-based).
     * <br>A name if specified takes precedence over its specified id.
     * This may be used as an unique key in a {@link Map}.
     * Comparison by name is case-sensitive.
     */
    public class ParameterIdentifier
    {
        private Integer m_paramId;
        private String m_paramName;
        
        /**
         * Creates a parameter identifier with its id.
         * @param paramId   id of the parameter (1-based)
         * @throws IllegalArgumentException if specified argument is not greater or equal to 1
         */
        public ParameterIdentifier( int paramId )
        {
            if( paramId < 1 )
                throw new IllegalArgumentException( Integer.valueOf( paramId ).toString() );
            
            m_paramId = Integer.valueOf( paramId );
        }
        
        /**
         * Creates a parameter identifier with its native name.
         * @param paramName native name of the parameter
         * @throws IllegalArgumentException if specified argument is null or empty
         */
        public ParameterIdentifier( String paramName )
        {
            if( paramName == null || paramName.length() == 0 )
                throw new IllegalArgumentException( paramName );
            
            m_paramName = paramName;
        }

        /**
         * Gets the parameter id, if specified.
         * @return  parameter id, or null if not specified
         */
        public Integer getParameterId()
        {
            return m_paramId;
        }

        /**
         * Gets the parameter's native name, if specified.
         * @return  parameter's native name, or null if not specified
         */
        public String getParameterName()
        {
            return m_paramName;
        }

        /* (non-Javadoc)
         * @see java.lang.Object#equals(java.lang.Object)
         */
        @Override
        public boolean equals( Object obj )
        {
            if( ! (obj instanceof ParameterIdentifier) )
                return false;

            // compares by name, if exists
            ParameterIdentifier thatObj = (ParameterIdentifier) obj;
            if( this.m_paramName != null && this.m_paramName.length() > 0 )
                return this.m_paramName.equals( thatObj.m_paramName );

            if( this.m_paramId != null && this.m_paramId.intValue() > 0 )
                return( this.m_paramId.equals( thatObj.m_paramId ));
            
            return false;   // no valid identifier
        }

        /* (non-Javadoc)
         * @see java.lang.Object#hashCode()
         */
        @Override
        public int hashCode()
        {
            // use its name for hashcode if exists
            if( m_paramName != null && m_paramName.length() > 0 )
                return m_paramName.hashCode();
            
            if( m_paramId != null && m_paramId.intValue() > 0 )
                return m_paramId.hashCode();
            
            return super.hashCode();
        }       
    }
    
}
