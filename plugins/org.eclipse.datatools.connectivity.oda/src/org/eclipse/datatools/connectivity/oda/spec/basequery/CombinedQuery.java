/*
 *************************************************************************
 * Copyright (c) 2013, 2014 Actuate Corporation.
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
import org.eclipse.datatools.connectivity.oda.spec.ValueExpression;
import org.eclipse.datatools.connectivity.oda.spec.result.ColumnIdentifier;
import org.eclipse.datatools.connectivity.oda.spec.result.FilterExpression;
import org.eclipse.datatools.connectivity.oda.spec.result.filter.AtomicExpression;
import org.eclipse.datatools.connectivity.oda.spec.result.filter.CompositeExpression;
import org.eclipse.datatools.connectivity.oda.spec.util.ValidatorUtil;
import org.eclipse.datatools.connectivity.oda.spec.valueexpr.ColumnValueExpression;
import org.eclipse.datatools.connectivity.oda.spec.valueexpr.CombinedValueExpression;
import org.eclipse.datatools.connectivity.oda.spec.valueexpr.NestedValueExpression;

/**
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
     * @param combinedCondition     the condition to evaluate in combining the 2 queries
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

    private void validateCombiningExpression( FilterExpression combiningExpr ) 
            throws OdaException
    {
        if( combiningExpr instanceof AtomicExpression )
        {
            AtomicExpression atomicCombiningExpr = (AtomicExpression)combiningExpr;

            // validate the variable's querySpec qualifier, if exists
            validateQueryQualifier( atomicCombiningExpr.getVariable().getValueExpression() );

            int numArgs = atomicCombiningExpr.getArguments().valueCount();
            for( int i=0; i < numArgs; i++ )
            {
                // validate each argument's querySpec qualifier, if exists
                validateQueryQualifier( atomicCombiningExpr.getArguments().getValueExpression(i) );
            }
            return;
        }

        if( combiningExpr instanceof CompositeExpression )
        {
            FilterExpression[] childExprs = ((CompositeExpression)combiningExpr).getChildren();
            for( int i= 0; i < childExprs.length; i++ )
                validateCombiningExpression( childExprs[i] );
            return;
        }
    }
    
    private void validateQueryQualifier( ValueExpression valueExpr )
        throws OdaException
    {
        if( valueExpr instanceof ColumnValueExpression )
        {
            ColumnIdentifier columnIdentifier = ((ColumnValueExpression)valueExpr).getColumnReference();
            if( columnIdentifier != null )
            {
                // validate that its querySpec qualifier, if exists, must be one of the combining queries
                QuerySpecification columnQuerySpec = columnIdentifier.getQueryQualifier();
                if( columnQuerySpec != null && ! containsCombiningQuery( columnQuerySpec ) )
                    throw ValidatorUtil.newValueExprException( 
                        Messages.bind( Messages.querySpec_NON_RELATED_QUERYSPEC, columnIdentifier ), valueExpr );
                return;              
            }
        }

        if( valueExpr instanceof NestedValueExpression )
        {
            validateQueryQualifier( ((NestedValueExpression)valueExpr).getNestedExpression() );
            return;
        }

        if( valueExpr instanceof CombinedValueExpression )
        {
            validateQueryQualifier( ((CombinedValueExpression)valueExpr).getLeftExpression() );
            validateQueryQualifier( ((CombinedValueExpression)valueExpr).getRightExpression() );
            return;
        }
    }

    /*
     *  Checks whether the specified querySpec is one of the combining queries, which may be nested.
     */
    private boolean containsCombiningQuery( QuerySpecification querySpec )
    {
        if( querySpec == getLeftQuery() )
            return true;
        if( querySpec == getRightQuery() )
            return true;
        
        // check nested combining queries
        CombinedQuery nestedCombinedQuery;
        if( getLeftQuery().getBaseQuery() instanceof CombinedQuery )
        {
            nestedCombinedQuery = (CombinedQuery)getLeftQuery().getBaseQuery();
            if( nestedCombinedQuery != this && nestedCombinedQuery.containsCombiningQuery( querySpec ) )
                return true;
        }
        if( getRightQuery().getBaseQuery() instanceof CombinedQuery )
        {
            nestedCombinedQuery = (CombinedQuery)getRightQuery().getBaseQuery();
            if( nestedCombinedQuery != this && nestedCombinedQuery.containsCombiningQuery( querySpec ) )
                return true;
        }
        return false;
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
     * @param combinedExpr  a root-level filter expression for evaluation of combining criteria
     * @return  a {@link CombinedQueryCondition} that may be used to define a CombinedQuery
     * @since 3.4.1 (DTP 1.11.1)
     */
    public CombinedQueryCondition createCombinedCondition( FilterExpression combinedExpr )
    {
        return this.new CombinedQueryCondition( combinedExpr );
    }

    /**
     * Specifies the condition under which two query specifications are to be combined.
     */
    public class CombinedQueryCondition
    {
        private FilterExpression m_expr;
        
        /**
         * Constructor.
         * @param combiningExpr    a root-level {@link FilterExpression} for evaluation of matching criteria
         *                  to combine two sets of query specification
         */
        CombinedQueryCondition( FilterExpression combiningExpr )
        {
            m_expr = combiningExpr;
        }
        
        /**
         * Gets the root-level filter expression for evaluation of matching criteria to combine
         * two sets of query specification.
         * @return  a {@link FilterExpression}
         * @since 3.4.1 (DTP 1.11.1)
         */
        public FilterExpression getCombiningExpression()
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
            validateCombiningExpression( m_expr );
        }

        @Override
        public String toString()
        {
            StringBuffer buffer = new StringBuffer( " { combining expr: " ); //$NON-NLS-1$
            buffer.append( m_expr );
            buffer.append( " }" ); //$NON-NLS-1$
            return buffer.toString();
        }
    }

}
