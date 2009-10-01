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

package org.eclipse.datatools.connectivity.oda.spec;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.spec.ExpressionVariable.VariableType;

/**
 * <strong>EXPERIMENTAL</strong>.
 * The abstract base class for an ODA expression
 * that resolves to a value.
 * It may be associated with an {@link ExpressionVariable} or
 * {@link ExpressionArguments} in an ODA query specification.
 * <br>It is the responsibility of an ODA driver to resolve an expression,
 * when evaluating it with a query result spec expression.
 * This may be extended to represent complex types of value expression.
 * @since 3.2.2 (DTP 1.7.2)
 */
public abstract class ValueExpression
{
    protected static final String SPACE = " "; //$NON-NLS-1$    
    protected static final String LEFT_PARANTHESIS = "("; //$NON-NLS-1$
    protected static final String RIGHT_PARANTHESIS = ")"; //$NON-NLS-1$

    private Integer m_odaDataTypeCode; 
    
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
        return m_odaDataTypeCode;
    }

    /**
     * Sets the ODA data type of this variable.
     * This serves as an optional hint that may have been specified based on the resolved expression,
     * and may be ignored by the runtime driver, if appropriate.
     * @param odaDataTypeCode the ODA data type code of this variable;
     *              may be null to unset current value
     */
    public void setOdaDataType( Integer odaDataTypeCode )
    {
        m_odaDataTypeCode = odaDataTypeCode;
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
        validateSyntax( context );

        // pass this to custom validator, if exists, for further overall validation
        // TODO - uncomment
//        if( context != null && context.getValidator() != null )
//            context.getValidator().validate( this, context );
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
