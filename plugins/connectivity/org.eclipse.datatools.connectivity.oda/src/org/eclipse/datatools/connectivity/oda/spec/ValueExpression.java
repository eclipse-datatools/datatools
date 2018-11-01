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

import java.sql.Types;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.spec.ExpressionVariable.VariableType;
import org.eclipse.datatools.connectivity.oda.spec.util.QuerySpecificationHelper;

/**
 * The abstract base class for an ODA expression
 * that resolves to a value.
 * It may be associated with an {@link ExpressionVariable} or
 * {@link ExpressionArguments} in an ODA query specification.
 * <br>It is the responsibility of an ODA driver to resolve an expression,
 * when evaluating it with a query result spec expression.
 * This may be extended to represent complex types of value expression.
 * @since 3.3 (DTP 1.8)
 */
public abstract class ValueExpression
{
    public static final Integer UNKNOWN_ODA_DATA_TYPE = Integer.valueOf( Types.NULL );

    protected static final String SPACE = " "; //$NON-NLS-1$    
    protected static final String LEFT_PARANTHESIS = "("; //$NON-NLS-1$
    protected static final String RIGHT_PARANTHESIS = ")"; //$NON-NLS-1$
    protected static final String LEFT_CURLY_BRACKET = " {"; //$NON-NLS-1$
    protected static final String RIGHT_CURLY_BRACKET = "} "; //$NON-NLS-1$
    // trace logging variables
    private static final String sm_className = ValueExpression.class.getName();
    
    private Integer m_odaDataType; 
    
    /**
     * Returns the qualified id of this value expression type.
     * @return  qualified id
     */
    public String getQualifiedId()
    {
        return getClass().getName();
    }
    
    /**
     * Gets the name of this value expression type.
     * It may be used to identify this in user messages or logging.
     * @return  name of this value expression
     */
    public String getName()
    {
        return getClass().getSimpleName();
    }
    
    /**
     * Returns the type of expression. 
     * @return
     */
    public VariableType getVariableType()
    {
        return VariableType.QUERY_EXPRESSION;
    }

    /**
     * Gets the ODA-defined code value of this variable's data type.
     * This serves as an optional hint that may have been specified based on the resolved expression.
     * @return      the ODA data type code of this variable, or null if not available
     */
    public Integer getOdaDataType()
    {
        return m_odaDataType;
    }

    /**
     * Sets the ODA data type of this variable.
     * This serves as an optional hint that may have been specified based on the resolved expression,
     * and may be ignored by the runtime driver, if appropriate.
     * @param odaDataTypeCode the ODA data type code of this variable;
     *              may be null to unset current value
     */
    public void setOdaDataType( Integer odaDataType )
    {
        m_odaDataType = odaDataType;
    }

    protected static boolean isNumeric( Integer odaDataType )
    {
        if( odaDataType == null || odaDataType == UNKNOWN_ODA_DATA_TYPE )
            return false;
        
        int odaDataTypeCode = odaDataType.intValue();
        return odaDataTypeCode == Types.INTEGER || 
                odaDataTypeCode == Types.DOUBLE || 
                odaDataTypeCode == Types.DECIMAL;
    }

    /**
     * Checks whether two objects are equal using the
     * <code>equals(Object)</code> method of the <code>left</code> object.
     * This method handles <code>null</code> for either the <code>left</code>
     * or <code>right</code> object.
     * @param left the first object to compare; may be <code>null</code>.
     * @param right the second object to compare; may be <code>null</code>.
     * @return <code>true</code> if the two objects are equivalent;
     *         <code>false</code> otherwise.
     */
    protected static final boolean equals( final Object left, final Object right )
    {
        return left == null ? right == null : 
                            ((right != null) && left.equals( right ));
    }

    /**
     * Validates this value expression. 
     * @throws OdaException if validation failed. The concrete cause is 
     *          defined by the subclass implementing this method.
     */
    public void validate() throws OdaException
    {
        validate( null );
    }

    /**
     * Validates this expression in the specified context. 
     * @param context   context for validation; may be null which would limit the scope of validation
     * @throws OdaException if validation failed. The concrete cause is 
     *          defined by the subclass implementing this method.
     */
    public void validate( ValidationContext context ) 
        throws OdaException
    {
        try
        {
            validateSyntax( context );

            // pass this to custom validator, if exists, for further overall validation
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
        StringBuffer buffer = new StringBuffer( getName() + SPACE + getVariableType() );
        return buffer.toString();
    }

}
