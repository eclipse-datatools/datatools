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

package org.eclipse.datatools.connectivity.oda.spec.util;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.nls.Messages;
import org.eclipse.datatools.connectivity.oda.spec.ExpressionVariable;
import org.eclipse.datatools.connectivity.oda.spec.result.FilterExpression;
import org.eclipse.datatools.connectivity.oda.spec.result.filter.AtomicExpression;
import org.eclipse.datatools.connectivity.oda.spec.result.filter.CompositeExpression;
import org.eclipse.datatools.connectivity.oda.spec.result.filter.CustomExpression;

/**
 * <strong>EXPERIMENTAL</strong>.
 * Utility class for use by implementation of IValidator.
 */
public class ValidatorUtil
{
    private static final String AT_SYMBOL = "@"; //$NON-NLS-1$
    
    /**
     * Validates the specified CustomExpression to be an instance of the specified class.
     * @param customExpr
     * @param expectedExprClass
     * @throws OdaException if validation fails
     */
    public static void validateCustomExprType( CustomExpression customExpr, Class<?> expectedExprClass ) 
        throws OdaException
    {
        if( expectedExprClass.isInstance( customExpr ) )
            return;     // is expected type

        // not an instance of the expected class
        throw newFilterExprException( 
                Messages.bind( Messages.querySpec_UNEXPECTED_CUSTOM_EXPR_TYPE,
                        customExpr.getName(), expectedExprClass.getName() ), 
                customExpr );
    }

    /**
     * Validates each and every CustomExpression nested in the specified FilterExpression 
     * to be an instance of the specified class.
     * @param expr
     * @param expectedExprClass
     * @throws OdaException if validation fails
     */
    public static void validateAllCustomExprType( FilterExpression expr, Class<?> expectedExprClass ) 
        throws OdaException
    {
        if( expr instanceof CustomExpression )
        {
            validateCustomExprType( (CustomExpression) expr, expectedExprClass );
            return;
        }
        
        if( expr instanceof CompositeExpression )
        {
            CompositeExpression parentExpr = (CompositeExpression) expr;
            FilterExpression[] childrenExpr = parentExpr.getChildren();
            for( int i= 0; i < childrenExpr.length; i++ )
            {
                validateAllCustomExprType( childrenExpr[i], expectedExprClass );
            }
            return;
        }
    }
        
    /**
     * Validates that the specified CustomExpression is contributed by the specified dynamicResultSet
     * extension id.
     * @param customExpr
     * @param expectedExtensionId
     * @throws OdaException if validation fails
     */
    public static void validateCustomExprExtension( CustomExpression customExpr, String expectedExtensionId )
        throws OdaException
    {
        if( ! expectedExtensionId.equals( customExpr.getDeclaringExtensionId() ) )
            throw newFilterExprException( 
                    Messages.bind( Messages.querySpec_UNEXPECTED_CUSTOM_EXPR_EXTENSION, customExpr.getName() ), 
                    customExpr );
    }
 
    /**
     * Validates that the specified AtomicExpression has an associated ExpressionVariable.
     * @param expr
     * @throws OdaException if validation fails
     */
    public static void validateHasExprVariable( AtomicExpression expr )
        throws OdaException
    {
        if( expr.getVariable() == null )
            throw newFilterExprException( 
                    Messages.bind( Messages.querySpec_MISSING_EXPR_VARIABLE, expr.getName() ), 
                            expr );
    }
    
    /**
     * Validates that the specified AtomicExpression is one of the specified VariableType.
     * @param expr
     * @param supportedVarTypes
     * @throws OdaException if validation fails
     */
    public static void validateSupportedVariableTypes( AtomicExpression expr, 
            ExpressionVariable.VariableType[] supportedVarTypes )
        throws OdaException
    {
        ExpressionVariable exprVar = expr.getVariable();
        for( int i=0; i < supportedVarTypes.length; i++ )
        {
            if( exprVar.getType() == supportedVarTypes[i] )
                return;     // is a supported type
        }
        
        // expr variable is not a supported type
        throw newFilterExprException( 
                Messages.bind( Messages.querySpec_UNEXPECTED_EXPR_VARIABLE_TYPE, exprVar ), 
                expr );
    }

    /**
     * Creates and returns an OdaException with the specified message and
     * an IllegalArgumentException cause with the specified causeIdentifier.
     * @param message
     * @param causeIdentifier
     * @return  a new OdaException
     */
    public static OdaException newOdaException( String message, String causeIdentifier )
    {
        OdaException odaEx = new OdaException( message );
        odaEx.initCause( new IllegalArgumentException( causeIdentifier ) );
        return odaEx;
    }
    
    /**
     * Adds the new OdaException object to the end of the OdaException chain.
     * @param rootEx    the root of an OdaException chain
     * @param newEx     a new OdaException to append to the end of the chain
     * @return
     */
    public static OdaException addException( OdaException rootEx, OdaException newEx )
    {
        if( rootEx == null )
            return newEx;  // nothing to append to
        if( newEx != null )
            rootEx.setNextException( newEx );
        return rootEx;
    }
    
    /**
     * Creates and returns a top-level OdaException to indicate that the 
     * specified FilterExpression is the cause of the specified driverEx exception.
     * @param invalidFilterExpr a top-level FilterExpression that is invalid
     * @param driverEx  optional detail OdaException thrown by an ODA driver that has detected 
     *              the invalid state; may be null
     * @return  an OdaException chain with the specified invalid FilterExpression
     *          identified as the cause
     * @see {@link #isInvalidFilterExpression(FilterExpression, OdaException)}
     */
    public static OdaException newFilterExprException( FilterExpression invalidFilterExpr, OdaException driverEx )
    {
        return newFilterExprException( Messages.querySpec_INVALID_FILTER_EXPR, invalidFilterExpr, driverEx );
    }
    
    /**
     * Creates and returns an OdaException with the specified FilterExpression identified as the cause.
     * @param message   custom exception message
     * @param invalidFilterExpr    the invalid FilterExpression to set as the cause
     * @return  an OdaException with the specified message and invalid FilterExpression
     *          identified as the cause
     * @see {@link #isInvalidFilterExpression(FilterExpression, OdaException)}
     */
    public static OdaException newFilterExprException( String message, FilterExpression invalidFilterExpr )
    {
        return newFilterExprException( message, invalidFilterExpr, null );
    }
    
    private static OdaException newFilterExprException( String message, FilterExpression invalidFilterExpr, 
            OdaException driverEx )
    {
        OdaException rootEx = newOdaException( message, getInstanceId( invalidFilterExpr ) );
        addException( rootEx, driverEx );
        return rootEx;
    }
    
    /**
     * Indicates whether the specified FilterExpression is identified as one of the cause(s)
     * in the specified OdaException chain.
     * @param filterExpr    a filter expression whose processing might have caused
     *          an OdaException
     * @param rootEx    the root of an OdaException chain caught while processing 
     *          the filter expression
     * @return  true if the specified FilterExpression is one of the cause(s) in the OdaException chain;
     *          false otherwise
     */
    public static boolean isInvalidFilterExpression( FilterExpression filterExpr, OdaException rootEx )
    {
        if( filterExpr == null )
            return true;

        String filterExprId = getInstanceId( filterExpr );
        OdaException currentEx = rootEx;
        while( currentEx != null )
        {
            Throwable cause = currentEx.getCause();
            if( cause instanceof IllegalArgumentException && 
                    filterExprId.equals( cause.getMessage() ) )
                return true;
            
            currentEx = currentEx.getNextException();
        }

        return false;
    }
    
    private static String getInstanceId( FilterExpression filterExpr )
    {
        return filterExpr.getQualifiedId() + AT_SYMBOL + Integer.toHexString( filterExpr.hashCode() );
    }
    
}
