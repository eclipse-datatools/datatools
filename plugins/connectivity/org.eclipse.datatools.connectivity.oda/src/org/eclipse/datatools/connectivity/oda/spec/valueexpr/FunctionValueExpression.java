/*
 *************************************************************************
 * Copyright (c) 2009, 2013 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.spec.valueexpr;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.spec.ExpressionArguments;
import org.eclipse.datatools.connectivity.oda.spec.ValidationContext;

/**
 * The abstract base class for all ODA function value expressions.
 * <p>
 * A value expression may be validated by an {@link org.eclipse.datatools.connectivity.oda.spec.IValidator} implemented
 * by an extension of the org.eclipse.datatools.connectivity.oda.dynamicResultSet extension point.
 * </p>
 * @since 3.2.2 (DTP 1.7.2)
 */
public abstract class FunctionValueExpression extends AtomicValueExpression
{
    protected static final String ARG_SEPARATOR = ", "; //$NON-NLS-1$
    
    private ExpressionArguments m_args;     // function arguments
    private boolean m_ignoresDups = false;

    protected FunctionValueExpression( ExpressionArguments args )
    {
        if( args != null )
            setArguments( args );
    }

    /**
     * Returns the qualified id of this expression type.
     * @return  qualified id
     */
    public String getQualifiedId()
    {
        return getClass().getName();
    }

    public ExpressionArguments getArguments()
    {
        if( m_args == null )
            m_args = new ExpressionArguments();     // an empty arguments instance
        return m_args;
    }

    public void setArguments( ExpressionArguments mArgs )
    {
        m_args = mArgs;
    }

    /**
     * Indicates whether this function should ignore duplicate values 
     * of its input variable(s).
     * @return  true to ignore; false otherwise
     */
    public boolean ignoresDuplicateValues()
    {
        return m_ignoresDups;
    }

    /**
     * Specifies whether this function to ignore duplicate values 
     * of its input variable(s).
     * @param ignoresDups true to ignore; false otherwise
     */
    public void setIgnoreDuplicateValues( boolean ignoresDups )
    {
        m_ignoresDups = ignoresDups;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.ValueExpression#getOdaDataType()
     */
    @Override
    public Integer getOdaDataType()
    {
        Integer functionDataType = super.getOdaDataType();

        // no data type explicitly specified, try derive from the function arguments, if exists
        if( (functionDataType == null || functionDataType == UNKNOWN_ODA_DATA_TYPE)
                && getArguments().hasValues() )
        {
            Integer derivedDataType = functionDataType;
            int numArgs = getArguments().valueCount();
            for( int i=0; i < numArgs; i++ )
            {
                Integer argDataType = getArguments().getValueExpression(i).getOdaDataType();
                if( argDataType != null )
                {
                    // another argument has a different data type
                    if( derivedDataType != null && derivedDataType != argDataType)
                        return functionDataType;        // not able to derive data type from arguments
                    derivedDataType = argDataType;
                }
            }
            return derivedDataType;
        }
        
        return functionDataType;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.ValueExpression#validateSyntax(org.eclipse.datatools.connectivity.oda.spec.ValidationContext)
     */
    @Override
    public void validateSyntax( ValidationContext context ) throws OdaException
    {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        StringBuffer buffer = new StringBuffer( getQualifiedId() );
        buffer.append( "\n    ( function " ); //$NON-NLS-1$
        buffer.append( getArguments() );
        buffer.append( " )" ); //$NON-NLS-1$
        return buffer.toString();
    }

}
