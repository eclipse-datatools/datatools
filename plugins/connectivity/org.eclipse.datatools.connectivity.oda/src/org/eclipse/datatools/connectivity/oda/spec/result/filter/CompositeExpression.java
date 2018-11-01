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

package org.eclipse.datatools.connectivity.oda.spec.result.filter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.nls.Messages;
import org.eclipse.datatools.connectivity.oda.spec.ValidationContext;
import org.eclipse.datatools.connectivity.oda.spec.result.FilterExpression;
import org.eclipse.datatools.connectivity.oda.spec.util.ValidatorUtil;

/**
 * The abstract base class for all composites of one or multiple child filter expressions.  
 * Its child expressions are ordered; their relationships are defined by its concrete extended classes.
 * @since 3.2 (DTP 1.7)
 */
public abstract class CompositeExpression extends FilterExpression
{
    private static final FilterExpression[] EMPTY_ARRAY = new FilterExpression[0];

    protected List<FilterExpression> m_expressions;

    /**
     * Appends the specified FilterExpression to its collection of child expressions.
     * @param expression    any type of filter expression
     * @return  this
     */
    public CompositeExpression add( FilterExpression expression )
    {
        if( expression == null )
            throw new IllegalArgumentException( "null FilterExpression" ); //$NON-NLS-1$
        if( m_expressions == null )
            m_expressions = new ArrayList<FilterExpression>( 2 );
        m_expressions.add( expression );
        return this;
    }

    /**
     * Returns the number of child expressions in this composite.
     * @return  the number of child expressions
     */
    public int childCount()
    {
        return ( m_expressions == null ) ? 0 : m_expressions.size();
    }
    
    /**
     * Returns the collection of child expressions in this composite.
     * @return  an array of child {@link FilterExpression}; an empty array is returned if no child expression is set
     */
    public FilterExpression[] getChildren()
    {
        if( m_expressions == null )
            return EMPTY_ARRAY;
        return (FilterExpression[]) m_expressions
                .toArray( new FilterExpression[m_expressions.size()] );
    }

    protected List<FilterExpression> getExpressions()
    {
        if( m_expressions == null )
            m_expressions = new ArrayList<FilterExpression>( 2 );
        return m_expressions;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.result.FilterExpression#validateSyntax(org.eclipse.datatools.connectivity.oda.spec.ValidationContext)
     */
    @Override
    public void validateSyntax( ValidationContext context ) throws OdaException
    {
        // syntactic validation of each of its children individually, if exists
        validateChildren( context );
    }

    /**
     * Iteratively walks thru each nested child node to validate individually.
     * Subclass implementation would determine its scope of validation and call 
     * {@link #validateChildren(ValidationContext, boolean)} as appropriate.
     */
    protected abstract void validateChildren( ValidationContext context ) 
        throws OdaException;
    
    /**
     * Iteratively walks thru each nested child node to validate individually.
     * Note that any child node that is a CustomExpression will also be validated 
     * by custom IValidator#validateSyntax implementation.
     * @param context   validation context
     * @param validateAll   indicates whether to continue validate all children regardless if 
     *           one is found to have validation exception.  True to validate all children and
     *           append all validation exceptions in the thrown OdaException chain; 
     *           false otherwise and throws an OdaException at the first detection
     * @throws OdaException
     */
    protected void validateChildren( ValidationContext context, boolean validateAll ) 
        throws OdaException
    {
        if( m_expressions == null )
            return;     // no children to validate

        OdaException rootEx = null;
        for( Iterator<FilterExpression> iter = m_expressions.iterator(); iter.hasNext(); )
        {
            FilterExpression childExpr = iter.next();
            try
            {
                if( childExpr instanceof CompositeExpression )
                    ((CompositeExpression)childExpr).validateChildren( context );
                else
                    childExpr.validateSyntax( context );
            }
            catch( OdaException ex )
            {
                if( ! validateAll )
                    throw ex;
                
                // append exception to chain, and continue iterate next child expression
                rootEx = ValidatorUtil.addException( rootEx, ex );
            }
        }  
        
        if( rootEx != null )
            throw rootEx;
    }

    /**
     * Validates that this has the specified minimum number of child expressions.
     * @param minimumChildren   minimum number of child expressions expected
     * @throws OdaException if validation failed
     */
    protected void validateMinElements( int minimumChildren ) throws OdaException
    {
        if( getExpressions().size() < minimumChildren )
            throw ValidatorUtil.newFilterExprException( Messages.bind( Messages.querySpec_MISSING_COMPOSITE_MIN_CHILDREN,
                    getName(), Integer.valueOf( minimumChildren ) ), this );
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.result.FilterExpression#isNegatable()
     */
    @Override
    public boolean isNegatable()
    {
        return true;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        StringBuffer buffer = new StringBuffer( getName() + " [" ); //$NON-NLS-1$
        
        if( m_expressions == null )
            buffer.append( m_expressions );
        else if( m_expressions.size() == 1 )
        {
            buffer.append( "\n     " + m_expressions.get(0) ); //$NON-NLS-1$
        }
        else
        {
            int i=0;
            for( FilterExpression childExpr : m_expressions )
            {
                buffer.append( "\n " + (++i) + ": " + childExpr ); //$NON-NLS-1$ //$NON-NLS-2$
            }
        }
        
        buffer.append( "]" ); //$NON-NLS-1$
        return buffer.toString();
    }
    
}
