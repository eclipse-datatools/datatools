/*
 *************************************************************************
 * Copyright (c) 2009, 2014 Actuate Corporation.
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
import java.util.Map.Entry;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.spec.result.ResultSetSpecification;
import org.eclipse.datatools.connectivity.oda.spec.util.QuerySpecificationHelper;

/**
 * Specification of the query characteristics to apply when preparing and executing 
 * an {@link org.eclipse.datatools.connectivity.oda.IQuery}.
 * @since 3.2 (DTP 1.7)
 */
public class QuerySpecification
{
    private ResultSetSpecification m_resultSpec;
    private Map<String,Object> m_propertyMap;
    private Map<ParameterIdentifier,Object> m_parameterValues;
    private BaseQuery m_baseQuery;

    // trace logging variables
    private static final String sm_className = QuerySpecification.class.getName();
    
    /*
     * Internal constructor.
     * <br>Use {@link org.eclipse.datatools.connectivity.oda.spec.util.QuerySpecificationHelper#createQuerySpecification()} 
     * to create an instance.
     */
    QuerySpecification() {}

    /**
     * Specifies the value(s) of a data set query property, overriding existing values if any.  
     * <br>A property may have multiple values kept in a {@link java.util.Collection}.
     * The property value specified here may be the same as the value set separately
     * by {@link org.eclipse.datatools.connectivity.oda.IQuery#setProperty(String, String)},
     * which is set after a query is prepared.  
     * <br>A property value may be null, whose handling is specific to individual 
     * driver implementation.
     * An ODA consumer does not necessarily distinguish whether a property value
     * is not set or explicitly set to null.
     * @param propertyName  name of the property
     * @param value the value of the specified property; may be null
     */
    public void setProperty( String propertyName, Object value )
    {
        getProperties().put( propertyName, value );
    }
    
    /**
     * Gets the value(s) of a data set query property.  
     * <br>A property may have multiple values kept in a {@link java.util.Collection}.
     * The property value specified here may be the same as the value set separately
     * by {@link org.eclipse.datatools.connectivity.oda.IQuery#setProperty(String, String)}, 
     * which is set after a query is prepared.  
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
     * <br>A property may have multiple values kept in a {@link java.util.Collection}.
     * The property values specified here may be the same as those set separately
     * by {@link org.eclipse.datatools.connectivity.oda.IQuery#setProperty(String, String)}, 
     * which are set after a query is prepared.  
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
     * <br>A property may have multiple values kept in a {@link java.util.Collection}.
     * The property values specified here may be the same as those set separately
     * by {@link org.eclipse.datatools.connectivity.oda.IQuery#setProperty(String, String)}, 
     * which are set after a query is prepared.  
     * <br>Its handling is optional and specific to individual driver implementation
     * on whether to apply these property values when preparing a query.
     * <br>If a property name is not recognized by the driver,
     * it should simply ignore, and not throw an exception.
     * <br>A property value may be null, whose handling is specific to individual 
     * driver implementation.
     * An ODA consumer does not necessarily distinguish whether a property value
     * is not set or explicitly set to null.  
     * @return  a {@link Map} of all currently specified data set query properties 
     *          with each property name as the key to its corresponding value(s); 
     *          may be empty if no property value is specified
     */
    public Map<String,Object> getProperties()
    {
        if( m_propertyMap == null )
            m_propertyMap = new HashMap<String,Object>(5);
        return m_propertyMap;
    }

    /**
     * Specifies the input value(s) of a data set query parameter, identified by its native name.  
     * <br>A parameter may have multiple input values kept in a {@link java.util.Collection}.
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
     * <br>A parameter may have multiple input values kept in a {@link java.util.Collection}.
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
        getParameterValues().put( paramIdentifier, value );
    }
    
    /**
     * Gets the input value(s) of a data set query parameter, identified by its native name.  
     * <br>A parameter may have multiple input values kept in a {@link java.util.Collection}.
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
     * <br>A parameter may have multiple input values kept in a {@link java.util.Collection}.
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
        
        Object paramValue = m_parameterValues.get( paramIdentifier );
        if( paramValue != null )
            return paramValue;
        
        // try match by name or id
        for( Entry<ParameterIdentifier, Object> entry : m_parameterValues.entrySet() )
        {
            ParameterIdentifier paramIdKey = entry.getKey();
            if( paramIdKey.matchesByNameOrId( paramIdentifier ) )
                return entry.getValue();
        }
        return null;
    }
    
    /**
     * Specifies the values of all input parameters of the associated 
     * {@link org.eclipse.datatools.connectivity.oda.IQuery}, with  
     * each parameter identified by name or id as the key to its corresponding input value(s).
     * <br>A parameter may have multiple input values kept in a {@link java.util.Collection}.
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
     * Gets the values of all input parameters of the associated 
     * {@link org.eclipse.datatools.connectivity.oda.IQuery}, with  
     * each parameter identified by name or id as the key to its corresponding input value(s).
     * <br>A parameter may have multiple input values kept in a {@link java.util.Collection}.
     * The parameter values specified here may be the same as those set separately
     * by a query's set by data type methods, which are set after a query is prepared.
     * <br>Its handling is optional and specific to individual driver implementation
     * on whether to apply these parameter input values when preparing a query.
     * <br>A value in primitive data type is specified in its corresponding object type.
     * A parameter value may be null, whose handling is specific to individual 
     * driver implementation.
     * @return  a {@link Map} of all currently specified data set query parameters 
     *          with each {@link ParameterIdentifier} as the key 
     *          to its corresponding input value(s);
     *          may be empty if no parameter value is specified
     */
    public Map<ParameterIdentifier,Object> getParameterValues()
    {
        if( m_parameterValues == null )
            m_parameterValues = new HashMap<ParameterIdentifier,Object>(5);
        return m_parameterValues;
    }
    
    /**
     * Specifies the characteristics of all the result set(s) to be retrieved by
     * the associated {@link org.eclipse.datatools.connectivity.oda.IQuery}.
     * @param resultSpec    specification of a query's result set(s)
     */
    public void setResultSetSpecification( ResultSetSpecification resultSpec )
    {
        m_resultSpec = resultSpec;
    }
    
    /**
     * Gets the current result set specification of an {@link org.eclipse.datatools.connectivity.oda.IQuery}.
     * @return  the current {@link ResultSetSpecification}, or null if not specified
     */
    public ResultSetSpecification getResultSetSpecification()
    {
        return m_resultSpec;
    }

    /**
     * Indicates whether this contains a ResultSetSpecification with a non-empty content.
     * @return  true if this contains a non-empty ResultSetSpecification; false otherwise
     * @since 3.3.1 (DTP 1.8.1)
     */
    public boolean hasResultSetSpecification()
    {
        ResultSetSpecification resultSetSpec = getResultSetSpecification();
        return ( resultSetSpec != null && ! resultSetSpec.isEmpty() );
    }

    /**
     * Specifies the optional base query on which this query specification is to be applied.
     * @param baseQuery     a concrete type of BaseQuery; may be null to defer the specification 
     *                  until an {@link org.eclipse.datatools.connectivity.oda.IQuery} is prepared.
     * @since 3.4 (DTP 1.11)
     */
    public void setBaseQuery( BaseQuery baseQuery )
    {
        m_baseQuery = baseQuery;
    }

    /**
     * Gets the base query on which this query specification is to be applied.
     * @return     a concrete type of BaseQuery; may be null to defer the specification 
     *                  until an {@link org.eclipse.datatools.connectivity.oda.IQuery} is prepared.
     * @since 3.4 (DTP 1.11)
     */
    public BaseQuery getBaseQuery()
    {
        return m_baseQuery;
    }

    /**
     * Validates this in the specified context. 
     * @param context   context for validation; may be null which would limit the scope of validation
     * @throws OdaException if validation failed.  The exception thrown may be a chained OdaException, 
     *          which identifies each of those specification component(s) that has caused 
     *          the validation exception.
     * @see {@link org.eclipse.datatools.connectivity.oda.spec.util.ValidatorUtil}
     * @since 3.2.2 (DTP 1.7.2)
     */
    public void validate( ValidationContext context ) 
        throws OdaException
    {
        // pass this to custom validator, if exists, for overall validation
        try
        {
            if( context != null && context.getValidator() != null )
                context.getValidator().validate( this, context );
        }
        catch( OdaException ex )
        {
            // log the exception before re-throwing it to the caller
            QuerySpecificationHelper.logValidationException( sm_className, ex );
            throw ex;
        }
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     * @see {@link QuerySpecificationHelper#getContentAsString(QuerySpecification)}
     */
    @Override
    public String toString()
    {
        StringBuffer buffer = new StringBuffer( QuerySpecification.class.getSimpleName() );
        buffer.append( "\n    { property count: " ); //$NON-NLS-1$
        buffer.append( getProperties().size() );
        buffer.append( " }; { parameter count: " ); //$NON-NLS-1$
        buffer.append( getParameterValues().size() );
        buffer.append( " }\n    { base query: " ); //$NON-NLS-1$
        buffer.append( m_baseQuery );
        buffer.append( " }\n    { resultSpec: " ); //$NON-NLS-1$
        buffer.append( m_resultSpec );
        buffer.append( " }" ); //$NON-NLS-1$
        return buffer.toString();
    }

    /**
     * The identifier of a data set query parameter, defined by its native name and/or id (1-based).
     * <br>A name if specified takes precedence over its specified id.
     * This may be used as an unique key in a {@link Map}.
     * Comparison by name is case-sensitive.
     */
    public class ParameterIdentifier
    {
        private String m_paramName;
        private Integer m_paramId;
        
        private static final String LOG_CLASSNAME_PREFIX = "ParameterIdentifier@"; //$NON-NLS-1$
        private static final String LOG_ID_LABEL = " [id= "; //$NON-NLS-1$
        private static final String LOG_NAME_LABEL = ", name= "; //$NON-NLS-1$
        private static final String LOG_END_BRACKET = "]"; //$NON-NLS-1$

        /**
         * Creates a parameter identifier with its native name.
         * @param paramName native name of the parameter
         * @throws IllegalArgumentException if specified argument is null or empty
         */
        public ParameterIdentifier( String paramName )
        {
            setParameterName( paramName );
        }
        
        /**
         * Creates a parameter identifier with its id.
         * @param paramId   id of the parameter (1-based)
         * @throws IllegalArgumentException if specified argument is not greater or equal to 1
         */
        public ParameterIdentifier( int paramId )
        {
            setParameterId( paramId );
        }

        /**
         * Creates a parameter identifier with both its native name and id.
         * @param paramName native name of the parameter
         * @param paramId   id of the parameter (1-based)
         */
        public ParameterIdentifier( String paramName, int paramId )
        {
            setParameterName( paramName );
            setParameterId( paramId );
        }
        
        private void setParameterId( int paramId )
        {
            if( paramId < 1 )
                throw new IllegalArgumentException( Integer.valueOf( paramId ).toString() );
            
            m_paramId = Integer.valueOf( paramId );
        }
        
        private void setParameterName( String paramName )
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

        /**
         * Indicates whether this has a native name.
         * @return  true if a native name exists; false otherwise
         */
        public boolean hasName()
        {
            return ( m_paramName != null && m_paramName.length() > 0 );
        }
        
        /**
         * Indicates whether this has an 1-based id.
         * @return  true if an id exists; false otherwise
         */
        public boolean hasId()
        {
            return ( m_paramId != null && m_paramId.intValue() > 0 );
        }
        
        private boolean matchesByNameOrId( Object obj )
        {
            if( ! (obj instanceof ParameterIdentifier) )
                return false;

            ParameterIdentifier thatObj = (ParameterIdentifier) obj;
            if( this == thatObj )
                return true;
            
            // compares by name first, if exists
            boolean matchesName = false;
            if( this.hasName() && thatObj.hasName() )
            {
                if( this.m_paramName.equals( thatObj.m_paramName ) )
                    matchesName = true;
                else
                    return false;
            }

            // compares by id, if exists
            if( this.hasId() && thatObj.hasId() )
                return( this.m_paramId.equals( thatObj.m_paramId ));
            
            return matchesName;
        }
        
        /* (non-Javadoc)
         * @see java.lang.Object#equals(java.lang.Object)
         */
        @Override
        public boolean equals( Object obj )
        {
            if( ! (obj instanceof ParameterIdentifier) )
                return false;

            ParameterIdentifier thatObj = (ParameterIdentifier) obj;
            if( this == thatObj )
                return true;
            
            // compares by name first, if exists
            boolean isNameEqual = false;
            if( this.hasName() )
            {
                if( this.m_paramName.equals( thatObj.m_paramName ) )
                    isNameEqual = true;
                else
                    return false;
            }

            // compares by id, if exists
            if( this.hasId() )
                return( this.m_paramId.equals( thatObj.m_paramId ));
            
            return isNameEqual;
        }

        /* (non-Javadoc)
         * @see java.lang.Object#hashCode()
         */
        @Override
        public int hashCode()
        {
            int hashCode = 0;
            // use its name for hashcode if exists
            if( hasName() )
                hashCode = m_paramName.hashCode();
            
            if( hasId() )
                return hashCode ^ m_paramId.hashCode();
            
            return (hashCode == 0) ? super.hashCode() : hashCode;
        }

        /* (non-Javadoc)
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString()
        {
            StringBuffer buffer = new StringBuffer( LOG_CLASSNAME_PREFIX );
            buffer.append( super.hashCode() );
            buffer.append( LOG_ID_LABEL );
            buffer.append( m_paramId ); 
            buffer.append( LOG_NAME_LABEL );
            buffer.append( m_paramName );
            buffer.append( LOG_END_BRACKET ); 
            return buffer.toString();
        } 
    }
    
}
