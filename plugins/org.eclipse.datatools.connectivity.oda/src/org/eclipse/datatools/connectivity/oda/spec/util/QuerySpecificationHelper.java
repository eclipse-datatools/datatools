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

package org.eclipse.datatools.connectivity.oda.spec.util;

import java.util.Map.Entry;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.spec.AdvancedQuerySpecification;
import org.eclipse.datatools.connectivity.oda.spec.QuerySpecification;
import org.eclipse.datatools.connectivity.oda.spec.QuerySpecification.ParameterIdentifier;
import org.eclipse.datatools.connectivity.oda.spec.manifest.ExtensionContributor;
import org.eclipse.datatools.connectivity.oda.spec.manifest.ResultExtensionExplorer;
import org.eclipse.datatools.connectivity.oda.spec.result.FilterExpression;
import org.eclipse.datatools.connectivity.oda.spec.result.ResultProjection;
import org.eclipse.datatools.connectivity.oda.spec.result.ResultSetSpecification;
import org.eclipse.datatools.connectivity.oda.spec.result.SortSpecification;

/**
 * <strong>EXPERIMENTAL</strong>.
 * The helper that locates the factory class, associated
 * with an org.eclipse.datatools.connectivity.oda.dynamicResultSet extension,
 * to create and/or access the content of a {@link QuerySpecification}.
 * <br>Provides convenient methods to create and access specification instances.
 */
public class QuerySpecificationHelper
{    
    private static final String LOG_NEWLINE_CHAR = "\n "; //$NON-NLS-1$
    private static final String LOG_PAIR_ENTRY_SEPARATOR = " -> "; //$NON-NLS-1$
    private static final String SPACE = " "; //$NON-NLS-1$

    private QuerySpecificationFactory m_factory;
    
    /**
     * Constructor for use with the specified dynamicResultSet extension id.
     * @param dynamicResultSetExtnId    the id of an extension that implements 
     *                the oda.dynamicResultSet extension point; 
     *                may be null to use the default factory
     */
    public QuerySpecificationHelper( String dynamicResultSetExtnId )
    {
        if( dynamicResultSetExtnId == null )
            return;     // use the base class factory by default

        try
        {
            ExtensionContributor contributor =
                ResultExtensionExplorer.getInstance().getExtensionContributor( dynamicResultSetExtnId );
            setFactory( contributor );            
        }
        catch( OdaException e )
        {
            // TODO log warning
            // ignore, use the base class factory by default
        }
    }
    
    /**
     * Constructor for use with the specified dynamicResultSet extension contributor.
     * @param contributor   contributor of a dynamicResultSet extension;
     *                      may be null to use the default factory
     */
    public QuerySpecificationHelper( ExtensionContributor contributor )
    {
        setFactory( contributor );
    }
    
    /**
     * Constructor for use with the specified query specification factory.
     * @param factory   the factory to create instances of query specification classes 
     */
    public QuerySpecificationHelper( QuerySpecificationFactory factory )
    {
        m_factory = factory;
    }
    
    QuerySpecificationHelper()
    {
        this( (QuerySpecificationFactory) null );
    }
    
    private void setFactory( ExtensionContributor contributor )
    {
        if( contributor == null )
            return;     // use the base class factory by default
        
        try
        {
            m_factory = contributor.getSpecificationFactory();
        }
        catch( OdaException e )
        {
            // TODO log warning
            // ignore, use the base class factory by default
        }
    }
    
    /**
     * Gets the associated query specification factory instance.
     * @return  associated query specification factory instance
     */
    public QuerySpecificationFactory getFactory()
    {
        if( m_factory == null )
        {
            m_factory = new QuerySpecificationFactory();    // use the base class by default
        }
        return m_factory;
    }
    
    /**
     * Creates a query specification instance with the specified filter expression root.
     * @param filterExpr    a composite or atomic FilterExpression with corresponding variable 
     *                  and argument values; may be null 
     * @return  a new {@link QuerySpecification} instance
     */
    public QuerySpecification createQuerySpecification( FilterExpression filterExpr )
    {
        return createQuerySpecification( filterExpr, null, null );
    }
    
    /**
     * Creates a query specification instance with the specified result projection.
     * @param projectionSpec    the projection specification of a query result set; may be null 
     * @return  a new {@link QuerySpecification} instance
     */
    public QuerySpecification createQuerySpecification( ResultProjection projectionSpec )
    {
        return createQuerySpecification( null, projectionSpec, null );
    }
    
    /**
     * Creates a query specification instance with the specified sort specification.
     * @param sortSpec  the SortSpecification representing sorting characteristics 
     *                  of a query result set; may be null 
     * @return  a new {@link QuerySpecification} instance
     */
    public QuerySpecification createQuerySpecification( SortSpecification sortSpec )
    {
        return createQuerySpecification( null, null, sortSpec );
    }

    /**
     * Creates a query specification instance with the specified filter expression root,
     * result projection, and/or sort specification.
     * @param filterExpr    a composite or atomic FilterExpression with corresponding variable 
     *                  and argument values; may be null  
     * @param projectionSpec    the projection specification of a query result set; may be null 
     * @param sortSpec  the SortSpecification representing sorting characteristics; may be null  
     *                  of a query result set
     * @return  a new {@link QuerySpecification} instance
     */
    public QuerySpecification createQuerySpecification( FilterExpression filterExpr,
            ResultProjection projectionSpec, SortSpecification sortSpec )
    {
        return getFactory().createQuerySpecification( filterExpr, projectionSpec, sortSpec );
    }
    
    /**
     * Returns an empty query specification instance created by the QuerySpecificationFactory 
     * specified in the constructor.
     * @return  a new {@link QuerySpecification} instance
     */
    public QuerySpecification createQuerySpecification()
    {
        return getFactory().createQuerySpecification();
    }

    /**
     * Returns an empty advanced query specification instance created by the QuerySpecificationFactory 
     * specified in the constructor.
     * @return  a new {@link AdvancedQuerySpecification} instance
     */
    public AdvancedQuerySpecification createAdvancedQuerySpecification()
    {
        return getFactory().createAdvancedQuerySpecification();
    }
    
    /**
     * Returns an empty result set specification instance created by the QuerySpecificationFactory specified 
     * in the constructor.
     * @return  a new {@link ResultSetSpecification} instance
     */
    public ResultSetSpecification createResultSetSpecification()
    {
        return getFactory().createResultSetSpecification();
    }
    
    /**
     * Returns an empty result projection instance created by the QuerySpecificationFactory specified 
     * in the constructor.
     * @return  a new {@link ResultProjection} instance
     */
    public ResultProjection createResultProjection()
    {
        return getFactory().createResultProjection();
    }
    
    /**
     * Returns an empty sort specification instance created by the QuerySpecificationFactory specified 
     * in the constructor.
     * @return  a new {@link SortSpecification} instance
     */
    public SortSpecification createSortSpecification()
    {
        return getFactory().createSortSpecification();
    }
    
    /**
     * Returns an empty sort specification instance
     * created by the QuerySpecificationFactory specified in the constructor.
     * By specifiying a sort mode, a sort key that gets added to the sort specification
     * will be validated to match the sort mode.
     * @param sortMode  the sort mode of this <code>SortSpecification</code>; one of 
     *                  <code>IDataSetMetaData.sortModeNone</code>, 
     *                  <code>IDataSetMetaData.sortModeSingleOrder</code>,
     *                  <code>IDataSetMetaData.sortModeColumnOrder</code>,
     *                  <code>IDataSetMetaData.sortModeSingleColumn</code>.
     * @return  a new {@link SortSpecification} instance with the defined mode,
     */
    public SortSpecification createSortSpecification( int sortMode )
    {
        return getFactory().createSortSpecification( sortMode );
    }
    
    /**
     * Gets the filter expression root from the specified querySpec. 
     * @param querySpec a query specification
     * @return  the composite or atomic FilterExpression with corresponding variable 
     *                  and argument values, or null if none is available
     */
    public static FilterExpression getFilterSpecification( QuerySpecification querySpec )
    {
        ResultSetSpecification resultSpec = getResultSetSpecification( querySpec );
        return ( resultSpec != null ) ? resultSpec.getFilterSpecification() : null;
    }
    
    /**
     * Gets the result projection from the specified querySpec. 
     * @param querySpec a query specification
     * @return  the projection specification of the query's result set(s), 
     *          or null if none is available
     */
    public static ResultProjection getResultProjection( QuerySpecification querySpec )
    {
        ResultSetSpecification resultSpec = getResultSetSpecification( querySpec );
        return ( resultSpec != null ) ? resultSpec.getResultProjection() : null;
    }
    
    /**
     * Gets the sort specification from the specified querySpec. 
     * @param querySpec a query specification
     * @return  the sort specification of the query's result set(s), 
     *          or null if none is available
     */
    public static SortSpecification getSortSpecification( QuerySpecification querySpec )
    {
        ResultSetSpecification resultSpec = getResultSetSpecification( querySpec );
        return ( resultSpec != null ) ? resultSpec.getSortSpecification() : null;
    }
    
    /**
     * Gets the result set specification from the specified querySpec. 
     * @param querySpec a query specification
     * @return  the result set specification instance in the specified querySpec,
     *          or null if none is available
     */
    public static ResultSetSpecification getResultSetSpecification( QuerySpecification querySpec )
    {
        return ( querySpec != null ) ? querySpec.getResultSetSpecification() : null;
    }
    
    /**
     * Returns the string representation of the content found in the specified QuerySpecification.
     * This utility method may be used for logging and debugging purpose.
     * @param querySpec  a query specification
     * @return  string representation of the query specification
     */
    public static String getContentAsString( QuerySpecification querySpec )
    {
        StringBuffer buffer = new StringBuffer( QuerySpecification.class.getSimpleName() + " [" ); //$NON-NLS-1$
        if( querySpec == null )
        {
            buffer.append( "null]" ); //$NON-NLS-1$
            return buffer.toString();
        }

        buffer.append( "\nProperty name-value pairs: " ); //$NON-NLS-1$
        for( Entry<String,Object> propValuePair : querySpec.getProperties().entrySet() )
        {
            buffer.append( LOG_NEWLINE_CHAR + propValuePair.getKey() +
                    LOG_PAIR_ENTRY_SEPARATOR + propValuePair.getValue().getClass().getSimpleName() +
                    SPACE + propValuePair.getValue() );
        }

        buffer.append( "\nParameter values: " ); //$NON-NLS-1$
        for( Entry<ParameterIdentifier,Object> parameterValuePair : querySpec.getParameterValues().entrySet() )
        {
            buffer.append( LOG_NEWLINE_CHAR + parameterValuePair.getKey() +
                    LOG_PAIR_ENTRY_SEPARATOR + parameterValuePair.getValue().getClass().getSimpleName() +
                    SPACE + parameterValuePair.getValue() );
        }
        
        buffer.append( "\nFilter spec: " ); //$NON-NLS-1$
        buffer.append( getFilterSpecification( querySpec ) );

        buffer.append( "\nResult projection: " ); //$NON-NLS-1$
        buffer.append( getResultProjection( querySpec ) );

        buffer.append( "\nSort spec: " ); //$NON-NLS-1$
        buffer.append( getSortSpecification( querySpec ) );

        buffer.append( "]" ); //$NON-NLS-1$
        return buffer.toString();
    }
}
