/*
 *************************************************************************
 * Copyright (c) 2013 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.spec.basequery;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.nls.Messages;
import org.eclipse.datatools.connectivity.oda.spec.BaseQuery;
import org.eclipse.datatools.connectivity.oda.spec.QuerySpecification;
import org.eclipse.datatools.connectivity.oda.spec.ValidationContext;
import org.eclipse.datatools.connectivity.oda.spec.result.FilterExpression;
import org.eclipse.datatools.connectivity.oda.spec.util.ValidatorUtil;

/**
 * <strong>EXPERIMENTAL</strong>.<br>
 * A composite base query formed by dynamically combining two sets of
 * query characteristics under a specified condition.
 * Each combining query, specified in a {@link QuerySpecification}, may in turn be based on
 * a nested CombinedQuery, with the lowest level of nested base query being an {@link AtomicQuery}.
 * @since 3.4 (DTP 1.11)
 */
public class CombinedQuery extends BaseQuery
{
    public enum CombinedType
    {
        INNER_JOIN,
        LEFT_OUTER,
        RIGHT_OUTER,
        FULL_OUTER;
    }
    
    private CombinedType m_combinedType;
    private QuerySpecification m_leftQuery;
    private QuerySpecification m_rightQuery;
    private CombinedQueryCondition m_combinedCondition;
    
    private static final CombinedQuery sm_factory = new CombinedQuery();
    
    public CombinedQuery()
    {
        this( CombinedType.INNER_JOIN );  // default
    }
    
    public CombinedQuery( CombinedType combinedType )
    {
        m_combinedType = combinedType;
    }
    
    /** 
     * Specifies the left and right sets of {@link QuerySpecification} to combine 
     * under the CombinedQueryCondition.
     * @param leftQuery     the left query to combine
     * @param rightQuery    the right query to combine
     * @param combinedCondition     the condition to evaluate in combining the 2 sets of queries
     * @throws OdaException     when the specified combining components are invalid
     */
    public void setCombinedQuery( QuerySpecification leftQuery, QuerySpecification rightQuery, CombinedQueryCondition combinedCondition ) 
        throws OdaException
    {
        m_leftQuery = leftQuery;
        m_rightQuery = rightQuery;        
        m_combinedCondition = combinedCondition;
        
        validateSyntax( null );
    }

    /**
     * Gets the left query to combine.
     * @return  the {@link QuerySpecification} of the combining left query
     */
    public QuerySpecification getLeftQuery()
    {
        return m_leftQuery;
    }

    /**
     * Gets the right query to combine.
     * @return  the {@link QuerySpecification} of the combining right query
     */
    public QuerySpecification getRightQuery()
    {
        return m_rightQuery;
    }

    /**
     * Gets the type of this CombinedQuery.
     * @return  a {@link CombinedType}
     */
    public CombinedType getCombinedType()
    {
        return m_combinedType;
    }

    /**
     * Gets the condition to evaluate in combining the 2 sets of queries.
     * @return  a {@link CombinedQueryCondition}
     */
    public CombinedQueryCondition getCombinedCondition()
    {
        return m_combinedCondition;
    }

    @Override
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.BaseQuery#validateSyntax(org.eclipse.datatools.connectivity.oda.spec.ValidationContext)
     */
    public void validateSyntax( ValidationContext context ) throws OdaException
    {
        if( m_leftQuery == null || m_rightQuery == null || m_combinedCondition == null )
            throw ValidatorUtil.newBaseQueryException( Messages.querySpec_INCOMPLETE_COMBINED_QUERY, this );

        try
        {
            m_combinedCondition.validateSyntax( context );
        }
        catch( OdaException ex )
        {
            throw ValidatorUtil.newBaseQueryException( this, ex );
        }
    }

    @Override
    public String toString()
    {
        StringBuffer buffer = new StringBuffer( CombinedQuery.class.getSimpleName() );
        buffer.append( " " ); //$NON-NLS-1$
        buffer.append( m_combinedType.name() );
        buffer.append( "\n    { leftQuery: " ); //$NON-NLS-1$
        buffer.append( m_leftQuery );
        buffer.append( " }\n    { rightQuery: " ); //$NON-NLS-1$
        buffer.append( m_rightQuery );
        buffer.append( " }\n    { combinedQueryCondition: " ); //$NON-NLS-1$
        buffer.append( m_combinedCondition );
        buffer.append( " }" ); //$NON-NLS-1$
        return buffer.toString();
    }

    /**
     * Instantiates a new CombinedQueryCondition that specifies the matching criteria to apply
     * in combining 2 sets of queries.
     * @param combinedExpr  a root-level filter expression for evaluation of matching criteria
     * @return  a {@link CombinedQueryCondition} that may be used to specify a CombinedQuery
     */
    public static CombinedQueryCondition createCombinedCondition( FilterExpression combinedExpr )
    {
        return sm_factory.new CombinedQueryCondition( combinedExpr );
    }

    /**
     * Specifies the condition under which two query specifications are to be combined.
     */
    public class CombinedQueryCondition
    {
        private FilterExpression m_expr;
        
        /**
         * Constructor.
         * @param matchingExpr    a root-level {@link FilterExpression} for evaluation of matching criteria
         */
        public CombinedQueryCondition( FilterExpression matchingExpr )
        {
            m_expr = matchingExpr;
        }
        
        /**
         * Gets the root-level filter expression for evaluation of matching criteria to combine
         * two sets of query specification.
         * @return  a {@link FilterExpression}
         */
        public FilterExpression getMatchingExpression()
        {
            return m_expr;
        }

        /**
         * Validates this condition. 
         * @throws OdaException if validation failed. The concrete cause is 
         *          defined by the subclass implementing this method.
         */
        public void validate() throws OdaException
        {
            validate( null );
        }

        /**
         * Validates this condition in the specified context. 
         * @param context   context for validation; may be null which would limit the scope of validation
         * @throws OdaException if validation failed
         */
        public void validate( ValidationContext context ) 
            throws OdaException
        {
            validateSyntax( context );
            m_expr.validate( context );
        }

        /**
         * Performs syntactic validation of this condition in the specified context. 
         * @param context   context for validation; may be null which would limit the scope of validation
         * @throws OdaException if validation failed.
         */
        public void validateSyntax( ValidationContext context ) 
            throws OdaException
        {
            if( m_expr == null )
                throw new OdaException( Messages.querySpec_MISSING_COMBINED_MATCHING_EXPR );
            m_expr.validateSyntax( context );
        }

        @Override
        public String toString()
        {
            StringBuffer buffer = new StringBuffer( " { matching expr: " ); //$NON-NLS-1$
            buffer.append( m_expr );
            buffer.append( " }" ); //$NON-NLS-1$
            return buffer.toString();
        }
    }

}
