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

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.result.FilterExpression#validate(org.eclipse.datatools.connectivity.oda.spec.ValidationContext)
     */
    @Override
    public void validate( ValidationContext context ) throws OdaException
    {      
        validateSyntax( context );
        
        // pass this root-level expression tree to custom IValidator#validate for further overall validation
        if( context != null && context.getValidator() != null )
            context.getValidator().validate( this, context );        
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.result.FilterExpression#validateSyntax(org.eclipse.datatools.connectivity.oda.spec.ValidationContext)
     */
    @Override
    public void validateSyntax( ValidationContext context ) throws OdaException
    {
        // validate each of its children individually, if exists; 
        validateChildren( context );
    }

    /**
     * Iteratively walk thru each nested child node to validate individually.
     * Any child node that is a CustomExpression is also passed to custom IValidator#validateCustomExpression
     */
    protected void validateChildren( ValidationContext context ) throws OdaException
    {
        if( m_expressions == null )
            return;     // no children to validate

        for( Iterator<FilterExpression> iter = m_expressions.iterator(); iter.hasNext(); )
        {
            FilterExpression childExpr = iter.next();
            if( childExpr instanceof CompositeExpression )
                ((CompositeExpression)childExpr).validateChildren( context );
            else
                childExpr.validateSyntax( context );
        }    
    }

    /**
     * Validates that this has the specified minimum number of child expressions.
     * @param minimumChildren   minimum number of child expressions expected
     * @throws OdaException if validation failed
     */
    protected void validateMinElements( int minimumChildren ) throws OdaException
    {
        if( getExpressions().size() < minimumChildren )
            throw ValidatorUtil.newOdaException( Messages.bind( Messages.querySpec_MISSING_COMPOSITE_MIN_CHILDREN,
                    getName(), Integer.valueOf( minimumChildren ) ), getQualifiedId() );
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
            buffer.append( "\n     " + m_expressions.get(0).toString() ); //$NON-NLS-1$
        }
        else
        {
            Iterator<FilterExpression> iter = m_expressions.iterator();
            for( int i=1; iter.hasNext(); i++ )
            {
                buffer.append( "\n " + i + ": " + iter.next().toString() ); //$NON-NLS-1$ //$NON-NLS-2$
            }
        }
        
        buffer.append( "]" ); //$NON-NLS-1$
        return buffer.toString();
    }
    
}
