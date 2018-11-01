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

package org.eclipse.datatools.connectivity.oda.spec.valueexpr;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.spec.manifest.CombinedExpressionOperatorDefinition;

/**
 * Represents the operator that combines two value expressions in a {@link CombinedValueExpression}.
 * <br>An ODA dynamicResultSet extension declares its support of specific built-in operator types
 * in the combinedOperatorTypes element in its extension manifest.  
 * <br>An extension may extend this class to override the literal representation 
 * of a built-in combined operator, or to implement its custom combined operator type(s). 
 * A subclass specified in a dynamicResultSet extension manifest must implement IExecutableExtension
 * with a public 0-arg constructor, and override the #setLiteral and/or #getLiteral methods.
 * @since 3.2.2 (DTP 1.7.2)
 */
public class CombinedValueExpressionOperator implements IExecutableExtension
{
    /**
     * The id of built-in combined operator types.
     */
    public static final String ADD = "Add"; //$NON-NLS-1$
    public static final String SUBTRACT = "Subtract"; //$NON-NLS-1$
    public static final String MULTIPLY = "Multiply"; //$NON-NLS-1$
    public static final String DIVIDE = "Divide"; //$NON-NLS-1$
    public static final String CONCATENATE = "Concatenate"; //$NON-NLS-1$

    /**
     * Literal instances of built-in combined operator types.
     */
    public static final CombinedValueExpressionOperator ADD_LITERAL = new CombinedValueExpressionOperator( ADD, "+" ); //$NON-NLS-1$
    public static final CombinedValueExpressionOperator SUBTRACT_LITERAL = new CombinedValueExpressionOperator( SUBTRACT, "-" ); //$NON-NLS-1$
    public static final CombinedValueExpressionOperator MULTIPLY_LITERAL = new CombinedValueExpressionOperator( MULTIPLY, "*" ); //$NON-NLS-1$
    public static final CombinedValueExpressionOperator DIVIDE_LITERAL = new CombinedValueExpressionOperator( DIVIDE, "/" ); //$NON-NLS-1$
    public static final CombinedValueExpressionOperator CONCATENATE_LITERAL = new CombinedValueExpressionOperator( CONCATENATE, "+" ); //$NON-NLS-1$

    private static Map<String,CombinedValueExpressionOperator> sm_builtInOperators = null;
    
    /**
     * Returns the literal instance of the specified built-in combined operator type.
     * @param type  the type code of a built-in combined operator
     * @return  literal instance of the specified operator type
     */
    public static final CombinedValueExpressionOperator get( String builtInOperatorId )
    {
        if( sm_builtInOperators == null )
        {
            synchronized( CombinedValueExpressionOperator.class )
            {
                if( sm_builtInOperators == null )
                {
                    sm_builtInOperators = new HashMap<String,CombinedValueExpressionOperator>(5);
                    sm_builtInOperators.put( ADD, ADD_LITERAL );
                    sm_builtInOperators.put( SUBTRACT, SUBTRACT_LITERAL );
                    sm_builtInOperators.put( MULTIPLY, MULTIPLY_LITERAL );
                    sm_builtInOperators.put( DIVIDE, DIVIDE_LITERAL );
                    sm_builtInOperators.put( CONCATENATE, CONCATENATE_LITERAL );
                }
            }
        }

        return sm_builtInOperators.get( builtInOperatorId );
    }

    // instance variables
    private String m_id;
    private String m_literal;

    /**
     * Base class constructor.
     */
    protected CombinedValueExpressionOperator( String id, String literal )
    {
        setId( id );
        setLiteral( literal );
    }

    /* (non-Javadoc)
     * @see org.eclipse.core.runtime.IExecutableExtension#setInitializationData(org.eclipse.core.runtime.IConfigurationElement, java.lang.String, java.lang.Object)
     */
    public void setInitializationData( IConfigurationElement config,
            String propertyName, Object data ) throws CoreException
    {
        // default implementation for custom extended class, which may override as needed
        setId( config );
        setLiteral( config );
    }

    /**
     * Initializes the operator id with the id attribute value specified in a 
     * supportedOdaCombinedOperator or combinedOperatorType element declared in an extension manifest.
     * For use by a subclass that implements IExecutableExtension with a public 0-argument constructor.
     * @param config
     * @throws CoreException
     * @see org.eclipse.core.runtime.IExecutableExtension#setInitializationData(org.eclipse.core.runtime.IConfigurationElement, java.lang.String, java.lang.Object)
     */
    protected void setId( IConfigurationElement config ) throws CoreException
    {
        try
        {
            setId( CombinedExpressionOperatorDefinition.getIdAttributeValue( config ) );
        }
        catch( OdaException ex )
        {
            throw new CoreException( new Status( IStatus.ERROR, config.getNamespaceIdentifier(), ex.getLocalizedMessage(), ex ));
        }
    }
    
    /**
     * Initializes the operator's literal symbol with the literal attribute value specified in a 
     * combinedOperatorType element declared in an extension manifest.
     * For use by a subclass that implements IExecutableExtension with a public 0-argument constructor.
     * @param config
     * @throws CoreException
     * @see org.eclipse.core.runtime.IExecutableExtension#setInitializationData(org.eclipse.core.runtime.IConfigurationElement, java.lang.String, java.lang.Object)
     */
    protected void setLiteral( IConfigurationElement config ) throws CoreException
    {
        try
        {
            setLiteral( CombinedExpressionOperatorDefinition.getCustomLiteralAttributeValue( config ) );
        }
        catch( OdaException ex )
        {
            throw new CoreException( new Status( IStatus.ERROR, config.getNamespaceIdentifier(), ex.getLocalizedMessage(), ex ));
        }
    }

    protected void setId( String id )
    {
        m_id = id;
    }

    protected void setLiteral( String literal )
    {
        m_literal = literal;
    }
    
    /**
     * Returns the id of this operator.
     * @return  combined operator id
     */
    public String getId()
    {
        return m_id;
    }

    /**
     * Returns the literal symbol or representation of this operator.
     * @return  literal representation
     */
    public String getLiteral()
    {
        return m_literal;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return getClass().getName() + " [id= " + m_id +   //$NON-NLS-1$
        ", literal= " + m_literal + "]"; //$NON-NLS-1$ //$NON-NLS-2$
    }
    
}
