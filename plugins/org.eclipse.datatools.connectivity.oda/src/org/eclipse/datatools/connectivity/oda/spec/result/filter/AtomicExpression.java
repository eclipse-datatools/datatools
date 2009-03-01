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

import org.eclipse.datatools.connectivity.oda.spec.ExpressionArguments;
import org.eclipse.datatools.connectivity.oda.spec.ExpressionVariable;
import org.eclipse.datatools.connectivity.oda.spec.result.FilterExpression;

/**
 * The abstract base class for all basic, indivisible unit of filter expressions
 * that can be evaluated by itself.
 * @since 3.2 (DTP 1.7)
 */
public abstract class AtomicExpression extends FilterExpression
{
    private ExpressionVariable m_variable;      // 0..1; optional
    private ExpressionArguments m_args;         // 1; an instance may contain no values
    
    protected AtomicExpression( ExpressionVariable variable, ExpressionArguments args )
    {
        setVariable( variable );
        setArguments( args );
    }
    
    /**
     * Returns the expression variable.
     * @return may be null
     */
    public ExpressionVariable getVariable()
    {
        return m_variable;
    }

    /**
     * @param variable the expression variable to set
     */
    public void setVariable( ExpressionVariable variable )
    {
        m_variable = variable;
    }

    /**
     * Returns the expression arguments.
     * @return 
     */
    public ExpressionArguments getArguments()
    {
        if( m_args == null )
            m_args = new ExpressionArguments( null );     // default argument with no value
        return m_args;
    }

    /**
     * @param args the arguments to set
     */
    public void setArguments( ExpressionArguments args )
    {
        m_args = args;
    }

    /**
     * Indicates whether this expression can be optional and skipped in a filter specification if 
     * all its expected argument values are null.  
     * Default value is false if none is specified in the extension.
     * @return true if this expression can be skipped when no argument values are available; 
     *          false otherwise 
     */
    public boolean isOptionable()
    {
        return false;   // an optional expression needs special handling and is not the default behavior
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        StringBuffer buffer = new StringBuffer( getClass().getName() + " Variable: (" ); //$NON-NLS-1$
        buffer.append( m_variable );
        buffer.append( "); Argument(s): (" ); //$NON-NLS-1$
        buffer.append( m_args );
        buffer.append( ")." ); //$NON-NLS-1$
        return buffer.toString();
    }

}
