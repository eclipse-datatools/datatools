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

package org.eclipse.datatools.connectivity.oda.spec.result;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.spec.ExpressionVariable;
import org.eclipse.datatools.connectivity.oda.spec.ValidationContext;

/**
 * <strong>EXPERIMENTAL</strong>.
 * </p>
 * The abstract base class for all ODA aggregate expressions.
 * <p>
 * An expression may be validated by an {@link org.eclipse.datatools.connectivity.oda.spec.IValidator} implemented
 * by an extension of the org.eclipse.datatools.connectivity.oda.dynamicResultSet extension point.
 * </p>
 * @since 3.2 (DTP 1.7)
 */
public abstract class AggregateExpression
{    
    protected static final String ALIAS_SEPARATOR = "_"; //$NON-NLS-1$
    
    private List<ExpressionVariable> m_inputVariables;   // 0..n ExpressionVariable
    private boolean m_ignoresDups = false;
    private boolean m_ignoresNull = false;
    private String m_alias;
    
    /**
     * Constructor with a single input source variable.
     * @param inputSourceVar an {@link ExpressionVariable} that identifies the source of input values 
     *                  to apply in the aggregate
     */
    protected AggregateExpression( ExpressionVariable inputSourceVar )
    {
        add( inputSourceVar );
    }
    
    /**
     * Appends the specified input source variable to this.
     * @param inputSourceVar  an {@link ExpressionVariable} that identifies the source of input values 
     *                  to apply in the aggregate
     * @return  this
     */
    public AggregateExpression add( ExpressionVariable inputSourceVar )
    {
        getVariables().add( inputSourceVar );
        return this;
    }

    /**
     * Returns the input source variables.
     * @return 
     */
    public List<ExpressionVariable> getVariables()
    {
        if( m_inputVariables == null )
        {
            m_inputVariables = new ArrayList<ExpressionVariable>(1);
        }
        return m_inputVariables;
    }

    /**
     * Sets an ordered list of input source variables.
     * @param variables the list of variables to set; may be an empty list
     */
    public void setVariables( List<ExpressionVariable> variables )
    {
        m_inputVariables = variables;
    }

    /**
     * Returns the qualified id of this expression.
     * @return  qualified id
     */
    public String getQualifiedId()
    {
        return getClass().getName();
    }

    /**
     * Gets the alias of this aggregate expression.  
     * @return  the alias, or the combined aliases of its input source variables if no alias is specified
     * @return 
     */
    public String getAlias()
    {
        if( m_alias == null )
        {   
            StringBuffer combinedAlias = new StringBuffer();
            Iterator<ExpressionVariable> iter = getVariables().iterator();
            while( iter.hasNext() )
            {
                if( combinedAlias.length() > 0 )
                    combinedAlias.append( ALIAS_SEPARATOR );
                combinedAlias.append( iter.next().getAlias() );
            }
            m_alias = combinedAlias.toString();
        }
        return m_alias;
    }

    /**
     * Specifies the alias.
     * @param alias the alias to set
     */
    public void setAlias( String alias )
    {
        m_alias = alias;
    }

    /**
     * Indicates whether this aggregate should ignore duplicate input values 
     * of its input source variable(s).
     * @return  true to ignore; false otherwise
     */
    public boolean ignoresDuplicateValues()
    {
        return m_ignoresDups;
    }

    /**
     * Specifies whether this aggregate to ignore duplicate values 
     * of its input source variable(s).
     * @param ignoresDups true to ignore; false otherwise
     */
    public void setIgnoreDuplicateValues( boolean ignoresDups )
    {
        m_ignoresDups = ignoresDups;
    }

    /**
     * Indicates whether this aggregate should ignore duplicate null values 
     * of its input source variable(s).
     * @return  true to ignore; false otherwise
     */
    public boolean ignoresNullValues()
    {
        return m_ignoresNull;
    }

    /**
     * Specifies whether this aggregate should ignore duplicate null values 
     * of its input source variable(s).
     * @param ignoresNull true to ignore; false otherwise
     */
    public void setIgnoreNullValues( boolean ignoresNull )
    {
        m_ignoresNull = ignoresNull;
    }

    /**
     * Validates this expression. 
     * @throws OdaException
     */
    public void validate() throws OdaException
    {
        validate( null );
    }

    /**
     * Validates this expression in the specified context. 
     * @param context   context for validation; may be null which would limit the scope of validation
     * @throws OdaException if validation failed. The concrete reason is 
     *          defined by the subclass implementing this method.
     */
    public abstract void validate( ValidationContext context ) 
        throws OdaException;
    
}
