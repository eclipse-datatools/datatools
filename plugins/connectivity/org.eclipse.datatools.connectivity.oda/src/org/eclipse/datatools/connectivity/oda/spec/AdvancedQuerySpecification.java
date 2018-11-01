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

import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.spec.result.ResultSetSpecification;

/**
 * Extended specification of the query characteristics to apply when preparing and executing 
 * a query text of an {@link org.eclipse.datatools.connectivity.oda.IAdvancedQuery}.
 * It takes effect only if assigned prior to an IAdvancedQuery prepares a query text
 * at {@link IQuery#prepare(String)}.
 * @since 3.3 (DTP 1.8)
 */
public class AdvancedQuerySpecification extends QuerySpecification
{
    private Map<String,ResultSetSpecification> m_namedResultSpecs;
    private Map<Integer,ResultSetSpecification> m_seqResultSpecs;
    
    /**
     * Internal constructor.
     * <br>Use {@link org.eclipse.datatools.connectivity.oda.spec.util.QuerySpecificationHelper#createAdvancedQuerySpecification()} 
     * to create an instance.
     */
    AdvancedQuerySpecification() {}
    
    /**
     * Specifies the characteristics of the named result set to be retrieved by
     * the associated {@link org.eclipse.datatools.connectivity.oda.IAdvancedQuery}.
     * <br>This overrides the default specification set by the base class method
     * {@link QuerySpecification#setResultSetSpecification(ResultSetSpecification)}.
     * @param resultSetName     the name of a result set
     * @param resultSpec    specification of the specified result set
     */
    public void setResultSetSpecification( String resultSetName, ResultSetSpecification resultSpec )
    {
        getNamedResultSpecs().put( resultSetName, resultSpec );
    }
    
    /**
     * Gets the specification of the named result set to be retrieved by
     * the associated {@link org.eclipse.datatools.connectivity.oda.IAdvancedQuery}.
     * @param resultSetName the name of a result set
     * @return  the {@link ResultSetSpecification} of the specified result set,
     *      or null if not specified
     */
    public ResultSetSpecification getResultSetSpecification( String resultSetName )
    {
        return getNamedResultSpecs().get( resultSetName );
    }
 
    /**
     * Specifies the characteristics of the specified result set to be retrieved by
     * the associated {@link org.eclipse.datatools.connectivity.oda.IAdvancedQuery}.
     * <br>This overrides the default specification set by the base class method
     * {@link QuerySpecification#setResultSetSpecification(ResultSetSpecification)}.
     * @param resultSetNum     a 1-based index number that indicates the sequence of a result set 
     *                         among a sequential set of multiple result sets
     * @param resultSpec    specification of the specified result set
     */
    public void setResultSetSpecification( int resultSetNum, ResultSetSpecification resultSpec )
    {
        getSequencedResultSpecs().put( Integer.valueOf( resultSetNum ), resultSpec );
    }
    
    /**
     * Gets the specification of the specified result set to be retrieved by
     * the associated {@link org.eclipse.datatools.connectivity.oda.IAdvancedQuery}.
     * @param resultSetNum     a 1-based index number that indicates the sequence of a result set 
     *                         among a sequential set of multiple result sets
     * @return  the {@link ResultSetSpecification} of the specified result set,
     *      or null if not specified
     */
    public ResultSetSpecification getResultSetSpecification( int resultSetNum )
    {
        return getSequencedResultSpecs().get( Integer.valueOf( resultSetNum ) );
    }

    protected Map<String, ResultSetSpecification> getNamedResultSpecs()
    {
        if( m_namedResultSpecs == null )
        {
            m_namedResultSpecs = new HashMap<String, ResultSetSpecification>(5);
        }
        return m_namedResultSpecs;
    }

    protected Map<Integer, ResultSetSpecification> getSequencedResultSpecs()
    {
        if( m_seqResultSpecs == null )
        {
            m_seqResultSpecs = new HashMap<Integer, ResultSetSpecification>(5);
        }
        return m_seqResultSpecs;
    }
    
}
