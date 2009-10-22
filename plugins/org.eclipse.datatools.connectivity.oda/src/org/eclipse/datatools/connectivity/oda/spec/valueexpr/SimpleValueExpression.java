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

package org.eclipse.datatools.connectivity.oda.spec.valueexpr;

import java.sql.Types;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.spec.ValidationContext;
import org.eclipse.datatools.connectivity.oda.spec.ExpressionVariable.VariableType;
import org.eclipse.datatools.connectivity.oda.util.manifest.DataTypeMapping;

/**
 * A concrete value expression associated with an object as its value.
 * The associated value object will be simply processed as is.
 * <br>An ODA driver that implements the ODA dynamicResultSet extension point
 * must support this type of expressions defined in a 
 * {@link org.eclipse.datatools.connectivity.oda.spec.QuerySpecification}.
 * @since 3.2.2 (DTP 1.7.2)
 */
public class SimpleValueExpression extends AtomicValueExpression
{
    private Object m_value;

    public SimpleValueExpression( Object value )
    {
        m_value = value;
        setOdaDataType( getValueOdaDataType( value ) );
    }
    
    /**
     * Returns the value of this simple expression.
     * @return  expression value; may be null
     */
    public Object getValue()
    {
        return m_value;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.ValueExpression#getName()
     */
    @Override
    public String getName()
    {
        return String.valueOf( m_value );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.valueexpr.AtomicValueExpression#getVariableType()
     */
    @Override
    public VariableType getVariableType()
    {
        return ( m_value instanceof String ) ? VariableType.QUERY_EXPRESSION : VariableType.INSTANCE_OF;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.ValueExpression#validateSyntax(org.eclipse.datatools.connectivity.oda.spec.ValidationContext)
     */
    @Override
    public void validateSyntax( ValidationContext context ) throws OdaException
    {
        // no validation is done; expression value is used as is
    }

    private static Integer getValueOdaDataType( Object exprValue )
    {
        // derive the ODA data type from the type of object value
        int odaDataType = DataTypeMapping.getOdaDataTypeCodeOfObject( exprValue );
        return ( odaDataType == Types.NULL ) ? null : Integer.valueOf( odaDataType );
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        StringBuffer buffer = new StringBuffer( getClass().getSimpleName() + SPACE );
        buffer.append( "value: " + getValue().getClass().getSimpleName() ); //$NON-NLS-1$
        buffer.append( SPACE + getName() );
        return buffer.toString();
    }

}
