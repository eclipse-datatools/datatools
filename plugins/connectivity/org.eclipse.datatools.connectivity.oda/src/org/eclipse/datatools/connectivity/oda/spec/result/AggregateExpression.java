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

package org.eclipse.datatools.connectivity.oda.spec.result;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.spec.ExpressionVariable;
import org.eclipse.datatools.connectivity.oda.spec.ValidationContext;
import org.eclipse.datatools.connectivity.oda.spec.util.QuerySpecificationHelper;

/**
 * The abstract base class for all ODA aggregate expressions.
 * <p>
 * An expression may be validated by an {@link org.eclipse.datatools.connectivity.oda.spec.IValidator} implemented
 * by an extension of the org.eclipse.datatools.connectivity.oda.dynamicResultSet extension point.
 * </p>
 * @since 3.3 (DTP 1.8)
 */
public abstract class AggregateExpression
{    
    private List<ExpressionVariable> m_inputVariables;   // 0..n ExpressionVariable
    private boolean m_ignoresDups = false;
    private boolean m_ignoresNull = true;
    private String m_alias;

    private static final String LOG_VAR_ENTRY = "\n      "; //$NON-NLS-1$
    private static final String sm_className = AggregateExpression.class.getName();

    /**
     * Constructor with a single input source variable.
     * @param inputSourceVar an {@link ExpressionVariable} that identifies the source of input values 
     *                  to apply in the aggregate
     */
    protected AggregateExpression( ExpressionVariable inputSourceVar )
    {
        if( inputSourceVar != null )
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
     * Returns the qualified id of this expression type.
     * @return  qualified id
     */
    public String getQualifiedId()
    {
        return getClass().getName();
    }
    
    /**
     * Gets the name of this expression type.
     * It may be used to identify this in user messages or logging.
     * @return  name of this expression
     */
    public String getName()
    {
        return getClass().getSimpleName();
    }

    /**
     * Gets the alias of this aggregate expression instance.  The alias may be used to reference
     * an instance.
     * @return  the alias; may be null
     */
    public String getAlias()
    {
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
    public void validate( ValidationContext context ) 
        throws OdaException
    {
        try
        {
            validateSyntax( context );
   
            // pass this to custom validator, if exists, for further overall validation;
            // up to custom validator class to resolve a variable's data type and validate
            // against one of the expression's restricted data types
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

    /**
     * Performs syntactic validation of this expression in the specified context. 
     * @param context   context for validation; may be null which would limit the scope of validation
     * @throws OdaException if validation failed. The concrete cause is 
     *          defined by the subclass implementing this method.
     */
    public abstract void validateSyntax( ValidationContext context ) 
        throws OdaException;

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        StringBuffer buffer = new StringBuffer( getQualifiedId() );
        buffer.append( "\n    ( input variable(s): " ); //$NON-NLS-1$
        if( m_inputVariables != null )
        {
            for( ExpressionVariable inputVar : m_inputVariables )
                buffer.append( LOG_VAR_ENTRY + inputVar );
        }
        buffer.append( " )" ); //$NON-NLS-1$
        return buffer.toString();
    }
   
}
