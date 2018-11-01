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

package org.eclipse.datatools.connectivity.oda.spec.util;

import java.util.Iterator;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.nls.Messages;
import org.eclipse.datatools.connectivity.oda.spec.BaseQuery;
import org.eclipse.datatools.connectivity.oda.spec.ExpressionVariable;
import org.eclipse.datatools.connectivity.oda.spec.ValueExpression;
import org.eclipse.datatools.connectivity.oda.spec.ExpressionVariable.VariableType;
import org.eclipse.datatools.connectivity.oda.spec.manifest.VariableRestrictions;
import org.eclipse.datatools.connectivity.oda.spec.result.AggregateExpression;
import org.eclipse.datatools.connectivity.oda.spec.result.CustomAggregate;
import org.eclipse.datatools.connectivity.oda.spec.result.FilterExpression;
import org.eclipse.datatools.connectivity.oda.spec.result.ResultProjection;
import org.eclipse.datatools.connectivity.oda.spec.result.ResultSetSpecification;
import org.eclipse.datatools.connectivity.oda.spec.result.SortSpecification;
import org.eclipse.datatools.connectivity.oda.spec.result.filter.AtomicExpression;
import org.eclipse.datatools.connectivity.oda.spec.result.filter.CompositeExpression;
import org.eclipse.datatools.connectivity.oda.spec.result.filter.CustomExpression;
import org.eclipse.datatools.connectivity.oda.spec.valueexpr.CustomFunction;

/**
 * Utility class for use by implementation of IValidator.
 * @since 3.3 (DTP 1.8)
 */
public class ValidatorUtil
{
    private static final String AT_SYMBOL = "@"; //$NON-NLS-1$
    
    /**
     * Validates the specified CustomExpression to be an instance of the specified class.
     * @param customExpr    a custom filter expression instance
     * @param expectedExprClass  the expected class of the custom expression
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
     * Validates the specified CustomAggregate to be an instance of the specified class.
     * @param customExpr    a custom aggregate expression instance
     * @param expectedExprClass the expected class of the custom expression
     * @throws OdaException  if validation fails
     */
    public static void validateCustomExprType( CustomAggregate customExpr, Class<?> expectedExprClass ) 
        throws OdaException
    {
        if( expectedExprClass.isInstance( customExpr ) )
            return;     // is expected type
    
        // not an instance of the expected class
        throw newAggregateException( 
                Messages.bind( Messages.querySpec_UNEXPECTED_AGGR_EXPR_TYPE,
                        customExpr.getName(), expectedExprClass.getName() ), 
                customExpr );
    }

    /**
     * Validates the specified CustomFunction to be an instance of the specified class.
     * @param customExpr    a custom function value expression instance
     * @param expectedExprClass the expected class of the custom function value expression
     * @throws OdaException  if validation fails
     * @since 3.2.3
     */
    public static void validateCustomExprType( CustomFunction customExpr, Class<?> expectedExprClass ) 
        throws OdaException
    {
        if( expectedExprClass.isInstance( customExpr ) )
            return;     // is expected type
    
        // not an instance of the expected class
        throw newValueExprException( 
                Messages.bind( Messages.querySpec_UNEXPECTED_FUNC_EXPR_TYPE,
                        customExpr.getName(), expectedExprClass.getName() ), 
                customExpr );
    }

    /**
     * Validates each and every CustomExpression nested in the specified FilterExpression 
     * to be an instance of the specified class.
     * @param customExpr    a custom filter expression instance
     * @param expectedExprClass  the expected class of the custom expression
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
     * @param customExpr    a custom filter expression instance
     * @param expectedExtensionId   id of the expected oda dynamicResultSet extension
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
     * Validates that the specified CustomAggregate is contributed by the specified dynamicResultSet
     * extension id.
     * @param customAggrExpr    a custom aggregate expression instance
     * @param expectedExtensionId   id of the expected oda dynamicResultSet extension
     * @throws OdaException if validation fails
     */
    public static void validateCustomExprExtension( CustomAggregate customAggrExpr, String expectedExtensionId )
        throws OdaException
    {
        if( ! expectedExtensionId.equals( customAggrExpr.getDeclaringExtensionId() ) )
            throw newAggregateException( 
                    Messages.bind( Messages.querySpec_UNEXPECTED_AGGR_EXPR_EXTENSION, customAggrExpr.getName() ), 
                    customAggrExpr );
    }

    /**
     * Validates that the specified CustomFunction is contributed by the specified dynamicResultSet
     * extension id.
     * @param customFuncExpr    a custom function value expression instance
     * @param expectedExtensionId   id of the expected oda dynamicResultSet extension
     * @throws OdaException if validation fails
     * @since 3.2.3
     */
    public static void validateCustomExprExtension( CustomFunction customFuncExpr, String expectedExtensionId )
        throws OdaException
    {
        if( ! expectedExtensionId.equals( customFuncExpr.getDeclaringExtensionId() ) )
            throw newValueExprException( 
                    Messages.bind( Messages.querySpec_UNEXPECTED_FUNC_EXPR_EXTENSION, customFuncExpr.getName() ), 
                    customFuncExpr );
    }
 
    /**
     * Validates that the specified AtomicExpression has an associated ExpressionVariable.
     * @param expr    an atomic filter expression instance
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
     * Validates that the expression variable of the specified filter expression 
     * is one of the specified variable types.
     * @param expr    an atomic filter expression instance
     * @param supportedVarTypes array of supported types of expression variable
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
     * Validates that the input expression variable of the specified aggregate expression 
     * is one of the specified variable types.
     * @param expr    a custom aggregate expression instance
     * @param supportedVarTypes array of supported types of expression variable
     * @throws OdaException if validation fails
     */
    public static void validateSupportedVariableTypes( CustomAggregate expr, 
            ExpressionVariable.VariableType[] supportedVarTypes )
        throws OdaException
    {
        if( expr.getVariables().isEmpty() )
            return;     // no input variables to validate
        
        // iterator thru each input variable to check if it is one of the supported types
        Iterator<ExpressionVariable> iterVars = expr.getVariables().iterator();
        while( iterVars.hasNext() )
        {
            ExpressionVariable exprVar = iterVars.next();
            
            boolean isSupported = false;
            for( int i=0; i < supportedVarTypes.length; i++ )
            {
                if( exprVar.getType() == supportedVarTypes[i] )
                {
                    isSupported = true;     // is a supported type
                    break;
                }
            }
            
            // expr variable is not a supported type
            if( ! isSupported )
                throw newAggregateException( 
                        Messages.bind( Messages.querySpec_UNEXPECTED_EXPR_VARIABLE_TYPE, exprVar ), 
                        expr );
        }        
    }

    /**
     * Validates that the data type of the specified expression variable is one of the
     * supported data types listed in the VariableRestrictions.
     * @param exprVar   an expression variable
     * @param varRestrictions   supported data types defined by an oda dynamicResultSet extension
     * @param odaDataSourceId   the id of an oda.dataSource extension
     * @param dataSetType       the id of a data set type defined by an oda.dataSource extension;
     *                          used to map native data type to corresponding ODA data type
     * @throws OdaException  if validation fails
     */
    public static void validateSupportedVariableDataTypes( ExpressionVariable exprVar, 
            VariableRestrictions varRestrictions, String odaDataSourceId, String dataSetType ) 
        throws OdaException
    {
        validateSupportedVariableDataTypes( exprVar.getValueExpression(), varRestrictions, 
                odaDataSourceId, dataSetType );
    }
    
    /**
     * Validates that the data type of the specified value expression is one of the
     * supported data types listed in the VariableRestrictions.
     * @param valueExpr a value expression
     * @param varRestrictions   supported data types defined by an oda dynamicResultSet extension
     * @param odaDataSourceId   the id of an oda.dataSource extension
     * @param dataSetType       the id of a data set type defined by an oda.dataSource extension;
     *                          used to map native data type to corresponding ODA data type
     * @throws OdaException  if validation fails
     */
    public static void validateSupportedVariableDataTypes( ValueExpression valueExpr, 
            VariableRestrictions varRestrictions, String odaDataSourceId, String dataSetType ) 
        throws OdaException
    {
        // TODO - validate ExpressionVariable's instance type is one of restrictedInstanceTypes 
        if( valueExpr.getVariableType() == VariableType.INSTANCE_OF )
            return;     // validation is not supported yet 
        
        // look up oda data type if available in the value expression
        Integer odaDataType = valueExpr.getOdaDataType();
        if( odaDataType == null || odaDataType == ValueExpression.UNKNOWN_ODA_DATA_TYPE )
            return;     // no data type to validate
        
        int[] restrictedOdaDataTypes = null;
//        String[] restrictedInstanceTypes = null;
        boolean hasRestrictedOdaDataTypes = false;
        boolean hasRestrictedInstanceTypes = false;
        switch( valueExpr.getVariableType() )
        {
            case RESULT_SET_COLUMN:
                restrictedOdaDataTypes = varRestrictions.getResultColumnRestrictedOdaDataTypes();
                if( restrictedOdaDataTypes.length > 0 )
                    hasRestrictedOdaDataTypes = true;
                break;
            case QUERY_EXPRESSION:
                restrictedOdaDataTypes = varRestrictions.getQueryExpressionRestrictedOdaDataTypes();
                if( restrictedOdaDataTypes.length > 0 )
                    hasRestrictedOdaDataTypes = true;
                break;
//            case INSTANCE_OF:
//                restrictedInstanceTypes = varRestrictions.getInstanceRestrictedTypes();
//                if( restrictedInstanceTypes.length > 0 )
//                    hasRestrictedInstanceTypes = true;
//                break;
            default:
                break;
        }
        if( ! hasRestrictedOdaDataTypes && ! hasRestrictedInstanceTypes )
            return;     // has no type restrictions
        
        boolean meetsRestriction = false;
        if( hasRestrictedOdaDataTypes )
        {            
            // check if the variable's oda type is one of the allowed data types
            for( int i=0; i < restrictedOdaDataTypes.length; i++ )
            {
                if( restrictedOdaDataTypes[i] == odaDataType.intValue() )
                {
                    meetsRestriction = true;
                    break;
                }
            }

            if( ! meetsRestriction )
                throw new OdaException( Messages.bind( Messages.querySpec_NONSUPPORTED_VAR_DATA_TYPE,
                        odaDataType, valueExpr.getName() ));
        }
    }

    /**
     * Validates that null ordering is not specified in the given sortSpec.
     * This utility method can be used by the custom validator of an ODA driver that does not support null ordering.
     * @param sortSpec      the sort specification found in a query spec
     * @throws OdaException  if validation fails
     */
    public static void validateHasNoNullOrderingSpec( SortSpecification sortSpec ) 
        throws OdaException
    {
        if( sortSpec == null || sortSpec.getSortKeyCount() == 0 )
            return;     // nothing to validate

        // null ordering is not supported, validate that none of the sort keys have specified null ordering
        int numSortKeys = sortSpec.getSortKeyCount();
        for( int i=1; i <= numSortKeys; i++ )
        {
            int nullOrderingType = sortSpec.getNullOrdering( i );
            if( nullOrderingType != SortSpecification.NULL_ORDERING_NONE )
                throw newSortSpecException( Messages.querySpec_NONSUPPORTED_NULL_ORDERING,
                        sortSpec );
        }
    }

    private static OdaException newOdaException( String message, String causeIdentifier, 
            OdaException chainedEx )
    {
        if( causeIdentifier == null )
            return ( chainedEx != null ) ? chainedEx : new OdaException( message );
        
        OdaException rootEx = newOdaException( message, causeIdentifier );
        addException( rootEx, chainedEx );
        return rootEx;
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

    private static boolean isCauseOfException( OdaException odaEx, String causeIdentifier )
    {
        if( causeIdentifier == null )
            return false;

        while( odaEx != null )
        {
            Throwable cause = odaEx.getCause();
            if( cause instanceof IllegalArgumentException && 
                    causeIdentifier.equals( cause.getMessage() ) )
                return true;

            // check on nested cause, if exists
            if( cause instanceof OdaException )
            {
                if( isCauseOfException( (OdaException)cause, causeIdentifier ) )
                    return true;
            }
                            
            odaEx = odaEx.getNextException();
        }

        return false;
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
            OdaException chainedEx )
    {
        // if this filter expr is already identified as a cause in the caught exception,
        // proceed to use it as is; otherwise, add this filter expr as the root cause 
        if( chainedEx != null && isInvalidFilterExpression( invalidFilterExpr, chainedEx ) )
            return chainedEx;
        return newOdaException( message, getInstanceId( invalidFilterExpr ), chainedEx );
    }
    
    /**
     * Indicates whether the specified FilterExpression is one of the cause(s)
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
        return isCauseOfException( rootEx, getInstanceId( filterExpr ) );
    }
    
    /**
     * Creates and returns a top-level OdaException to indicate that the 
     * specified aggregate expression is the cause of the specified driverEx exception.
     * @param invalidAggrExpr the invalid AggregateExpression to set as the cause
     * @param driverEx  optional detail OdaException thrown by an ODA driver that has detected 
     *              the invalid state; may be null
     * @return  an OdaException chain with the specified invalid aggregate expression
     *          identified as the cause
     * @see {@link #isInvalidAggregateExpression(AggregateExpression, OdaException)}
     */
    public static OdaException newAggregateException( AggregateExpression invalidAggrExpr, OdaException driverEx )
    {
        return newAggregateException( Messages.querySpec_INVALID_AGGR_EXPR, invalidAggrExpr, driverEx );
    }
    
    /**
     * Creates and returns an OdaException with the specified AggregateExpression identified as the cause.
     * @param message   custom exception message
     * @param invalidAggrExpr    the invalid AggregateExpression to set as the cause
     * @return  an OdaException with the specified message and invalid AggregateExpression
     *          identified as the cause
     * @see {@link #isInvalidAggregateExpression(AggregateExpression, OdaException)}
     */
    public static OdaException newAggregateException( String message, AggregateExpression invalidAggrExpr )
    {
        return newAggregateException( message, invalidAggrExpr, null );
    }
    
    private static OdaException newAggregateException( String message, AggregateExpression invalidAggrExpr, 
            OdaException chainedEx )
    {
        // if this aggregate expr is already identified as a cause in the caught exception,
        // proceed to use it as is; otherwise, add this expr as the root cause 
        if( chainedEx != null && isInvalidAggregateExpression( invalidAggrExpr, chainedEx ) )
            return chainedEx;
        return newOdaException( message, getInstanceId( invalidAggrExpr ), chainedEx );
    }
    
    /**
     * Indicates whether the specified aggregate expression is one of the cause(s)
     * in the specified OdaException chain.
     * @param aggrExpr    an aggregate expression whose processing might have caused an OdaException
     * @param rootEx    the root of an OdaException chain caught while processing 
     *          the aggregate expression
     * @return  true if the specified aggregate expression is one of the cause(s) in the OdaException chain;
     *          false otherwise
     */
    public static boolean isInvalidAggregateExpression( AggregateExpression aggrExpr, OdaException rootEx )
    {
        if( aggrExpr == null )
            return true;
        return isCauseOfException( rootEx, getInstanceId( aggrExpr ) );
    }
    
    /**
     * Creates and returns a top-level OdaException to indicate that the 
     * specified value expression is the cause of the specified driverEx exception.
     * @param invalidValueExpr the invalid ValueExpression to set as the cause
     * @param driverEx  optional detail OdaException thrown by an ODA driver that has detected 
     *              the invalid state; may be null
     * @return  an OdaException chain with the specified invalid value expression
     *          identified as the cause
     * @see {@link #isInvalidValueExpression(ValueExpression, OdaException)}
     */
    public static OdaException newValueExprException( ValueExpression invalidValueExpr, OdaException driverEx )
    {
        return newValueExprException( Messages.querySpec_INVALID_VALUE_EXPR, invalidValueExpr, driverEx );
    }
    
    /**
     * Creates and returns an OdaException with the specified value expression identified as the cause.
     * @param message   custom exception message
     * @param invalidValueExpr    the invalid ValueExpression to set as the cause
     * @return  an OdaException with the specified message and invalid ValueExpression
     *          identified as the cause
     * @see {@link #isInvalidValueExpression(ValueExpression, OdaException)}
     */
    public static OdaException newValueExprException( String message, ValueExpression invalidValueExpr )
    {
        return newValueExprException( message, invalidValueExpr, null );
    }
    
    private static OdaException newValueExprException( String message, ValueExpression invalidValueExpr, 
            OdaException chainedEx )
    {
        // if this value expr is already identified as a cause in the caught exception,
        // proceed to use it as is; otherwise, add this expr as the root cause 
        if( chainedEx != null && isInvalidValueExpression( invalidValueExpr, chainedEx ) )
            return chainedEx;
        return newOdaException( message, getInstanceId( invalidValueExpr ), chainedEx );
    }
    
    /**
     * Indicates whether the specified value expression is one of the cause(s)
     * in the specified OdaException chain.
     * @param valueExpr    a value expression whose processing might have caused an OdaException
     * @param rootEx    the root of an OdaException chain caught while processing 
     *          the value expression
     * @return  true if the specified value expression is one of the cause(s) in the OdaException chain;
     *          false otherwise
     */
    public static boolean isInvalidValueExpression( ValueExpression valueExpr, OdaException rootEx )
    {
        if( valueExpr == null )
            return true;
        return isCauseOfException( rootEx, getInstanceId( valueExpr ) );
    }
    
    /**
     * Creates and returns a top-level OdaException to indicate that the 
     * specified result projection is the cause of the specified driverEx exception.
     * @param resultProj  the invalid ResultProjection to set as the cause
     * @param driverEx    optional detail OdaException thrown by an ODA driver that has detected 
     *              the invalid state; may be null
     * @return  an OdaException chain with the specified invalid result projection
     *          identified as the cause
     * @see {@link #isInvalidResultProjection(ResultProjection, OdaException)}
     */
    public static OdaException newResultProjectionException( ResultProjection resultProj, OdaException driverEx )
    {
        return newResultProjectionException( Messages.querySpec_INVALID_RESULT_PROJ, resultProj, driverEx );
    }
    
    /**
     * Creates and returns a top-level OdaException to indicate that the 
     * specified result projection is the cause of the specified driverEx exception.
     * @param message   custom exception message
     * @param resultProj  the invalid ResultProjection to set as the cause
     * @return  an OdaException with the specified message and invalid result projection
     *          identified as the cause
     * @see {@link #isInvalidResultProjection(ResultProjection, OdaException)}
     */
    public static OdaException newResultProjectionException( String message, ResultProjection resultProj )
    {
        return newResultProjectionException( message, resultProj, null );
    }
    
    private static OdaException newResultProjectionException( String message, ResultProjection resultProj, 
            OdaException chainedEx )
    {
        // if this result projection is already identified as a cause in the caught exception,
        // proceed to use it as is; otherwise, add this expr as the root cause 
        if( chainedEx != null && isInvalidResultProjection( resultProj, chainedEx ) )
            return chainedEx;
        return newOdaException( message, getInstanceId( resultProj ), chainedEx );
    }
    
    /**
     * Indicates whether the specified result projection is one of the cause(s)
     * in the specified OdaException chain.
     * @param resultProj  a result projection whose processing might have caused an OdaException
     * @param rootEx    the root of an OdaException chain caught while processing the result projection
     * @return  true if the specified result projection is one of the cause(s) in the OdaException chain;
     *          false otherwise
     */
    public static boolean isInvalidResultProjection( ResultProjection resultProj, OdaException rootEx )
    {
        if( resultProj == null )
            return true;
        return isCauseOfException( rootEx, getInstanceId( resultProj ) );
    }
    
    /**
     * Creates and returns a top-level OdaException to indicate that the 
     * specified sort key is the cause of the specified driverEx exception.
     * @param sortKeySequenceOrder the sequence ordering position of a sort key that is invalid
     * @param driverEx  optional detail OdaException thrown by an ODA driver that has detected 
     *              the invalid state; may be null
     * @return  an OdaException chain with the specified invalid sort key
     *          identified as the cause
     * @see {@link #isInvalidSortKey(int, OdaException)}
     */
    public static OdaException newSortKeyException( int sortKeySequenceOrder, OdaException driverEx )
    {
        return newSortKeyException( Messages.querySpec_INVALID_SORT_KEY, sortKeySequenceOrder, driverEx );
    }
    
    /**
     * Creates and returns a top-level OdaException to indicate that the 
     * specified sort key is the cause of the specified driverEx exception.
     * @param message   custom exception message
     * @param sortKeySequenceOrder the sequence ordering position of a sort key that is invalid
     * @return  an OdaException with the specified message and invalid sort key
     *          identified as the cause
     * @see {@link #isInvalidSortKey(int, OdaException)}
     */
    public static OdaException newSortKeyException( String message, int sortKeySequenceOrder )
    {
        return newSortKeyException( message, sortKeySequenceOrder, null );
    }
    
    private static OdaException newSortKeyException( String message, int sortKeySequenceOrder, 
            OdaException chainedEx )
    {
        // if this sort key is already identified as a cause in the caught exception,
        // proceed to use it as is; otherwise, add this expr as the root cause 
        if( chainedEx != null && isInvalidSortKey( sortKeySequenceOrder, chainedEx ) )
            return chainedEx;
        return newOdaException( message, String.valueOf( sortKeySequenceOrder ), chainedEx );
    }
    
    /**
     * Indicates whether the specified sort key is one of the cause(s)
     * in the specified OdaException chain.
     * @param sortKeySequenceOrder  the sequence ordering position (1-based) of a sort key
     *                  whose processing might have caused an OdaException
     * @param rootEx    the root of an OdaException chain caught while processing the sort key
     * @return  true if the specified sort key is one of the cause(s) in the OdaException chain;
     *          false otherwise
     */
    public static boolean isInvalidSortKey( int sortKeySequenceOrder, OdaException rootEx )
    {
        if( sortKeySequenceOrder == 0 )
            return true;

        String sortKeyId = String.valueOf( sortKeySequenceOrder );
        return isCauseOfException( rootEx, sortKeyId );
    }
    
    /**
     * Creates and returns a top-level OdaException to indicate that the 
     * specified sort specification is the cause of the specified driverEx exception.
     * @param sortSpec  the invalid SortSpecification to set as the cause
     * @param driverEx    optional detail OdaException thrown by an ODA driver that has detected 
     *              the invalid state; may be null
     * @return  an OdaException chain with the specified invalid sort key
     *          identified as the cause
     * @see {@link #isInvalidSortSpec(SortSpecification, OdaException)}
     */
    public static OdaException newSortSpecException( SortSpecification sortSpec, OdaException driverEx )
    {
        return newSortSpecException( Messages.querySpec_INVALID_SORT_SPEC, sortSpec, driverEx );
    }
    
    /**
     * Creates and returns a top-level OdaException to indicate that the 
     * specified sort specification is the cause of the specified driverEx exception.
     * @param message   custom exception message
     * @param sortSpec  the invalid SortSpecification to set as the cause
     * @return  an OdaException with the specified message and invalid sort key
     *          identified as the cause
     * @see {@link #isInvalidSortSpec(SortSpecification, OdaException)}
     */
    public static OdaException newSortSpecException( String message, SortSpecification sortSpec )
    {
        return newSortSpecException( message, sortSpec, null );
    }
    
    private static OdaException newSortSpecException( String message, SortSpecification sortSpec, 
            OdaException chainedEx )
    {
        // if this sort spec is already identified as a cause in the caught exception,
        // proceed to use it as is; otherwise, add this expr as the root cause 
        if( chainedEx != null && isInvalidSortSpec( sortSpec, chainedEx ) )
            return chainedEx;
        return newOdaException( message, getInstanceId( sortSpec ), chainedEx );
    }
    
    /**
     * Indicates whether the specified sort specification is one of the cause(s)
     * in the specified OdaException chain.
     * @param sortSpec  a sort specification whose processing might have caused an OdaException
     * @param rootEx    the root of an OdaException chain caught while processing the sort specification
     * @return  true if the specified sort specification is one of the cause(s) in the OdaException chain;
     *          false otherwise
     */
    public static boolean isInvalidSortSpec( SortSpecification sortSpec, OdaException rootEx )
    {
        if( sortSpec == null )
            return true;
        return isCauseOfException( rootEx, getInstanceId( sortSpec ) );
    }
    
    /**
     * Creates and returns a top-level OdaException to indicate that the 
     * specified result set specification is the cause of the specified driverEx exception.
     * @param message   custom exception message
     * @param resultSetSpec  the invalid ResultSetSpecification to set as the cause
     * @param driverEx    optional detail OdaException thrown by an ODA driver that has detected 
     *              the invalid state; may be null
     * @return  an OdaException chain with the specified invalid result set specification
     *          identified as the cause
     * @see {@link #isInvalidResultSetSpec(ResultSetSpecification, OdaException)}
     */
    public static OdaException newResultSetSpecException( String message, ResultSetSpecification resultSetSpec, 
            OdaException chainedEx )
    {
        // if this result set spec is already identified as a cause in the caught exception,
        // proceed to use it as is; otherwise, add this spec as the root cause 
        if( chainedEx != null && isInvalidResultSetSpec( resultSetSpec, chainedEx ) )
            return chainedEx;
        return newOdaException( message, getInstanceId( resultSetSpec ), chainedEx );
    }
    
    /**
     * Indicates whether the specified result set specification is one of the cause(s)
     * in the specified OdaException chain.
     * @param resultSetSpec  a result set specification whose processing might have caused an OdaException
     * @param rootEx    the root of an OdaException chain caught while processing the result set specification
     * @return  true if the specified result set specification is one of the cause(s) in the OdaException chain;
     *          false otherwise
     */
    public static boolean isInvalidResultSetSpec( ResultSetSpecification resultSetSpec, OdaException rootEx )
    {
        if( resultSetSpec == null )
            return true;
        return isCauseOfException( rootEx, getInstanceId( resultSetSpec ) );
    }

    /**
     * Creates and returns a top-level OdaException to indicate that the 
     * specified invalidBaseQuery is the cause of the specified driverEx exception.
     * @param invalidBaseQuery a {@link BaseQuery} that is invalid
     * @param driverEx  optional detail OdaException thrown by an ODA driver that has detected 
     *              the invalid state; may be null
     * @return  an OdaException chain with the specified invalidBaseQuery
     *          identified as the cause
     * @see {@link #isInvalidBaseQuery(BaseQuery, OdaException)}
     * @since 3.4 (DTP 1.11)
     */
    public static OdaException newBaseQueryException( BaseQuery invalidBaseQuery, OdaException driverEx )
    {
        return newBaseQueryException( Messages.querySpec_INVALID_BASE_QUERY, invalidBaseQuery, driverEx );
    }
    
    /**
     * Creates and returns an OdaException with the specified invalidBaseQuery identified as the cause.
     * @param message   custom exception message
     * @param invalidBaseQuery    the invalid {@link BaseQuery} to set as the cause
     * @return  an OdaException with the specified message and invalidBaseQuery
     *          identified as the cause
     * @see {@link #isInvalidBaseQuery(BaseQuery, OdaException)}
     * @since 3.4 (DTP 1.11)
     */
    public static OdaException newBaseQueryException( String message, BaseQuery invalidBaseQuery )
    {
        return newBaseQueryException( message, invalidBaseQuery, null );
    }
    
    private static OdaException newBaseQueryException( String message, BaseQuery invalidBaseQuery, 
            OdaException chainedEx )
    {
        // if this BaseQuery is already identified as a cause in the caught exception,
        // proceed to use chainedEx as is; otherwise, add this BaseQuery as the root cause 
        if( chainedEx != null && isInvalidBaseQuery( invalidBaseQuery, chainedEx ) )
            return chainedEx;
        return newOdaException( message, getInstanceId( invalidBaseQuery ), chainedEx );
    }
    
    /**
     * Indicates whether the specified baseQuery is one of the cause(s)
     * in the specified OdaException chain.
     * @param baseQuery    a {@link BaseQuery} whose processing might have caused an OdaException
     * @param rootEx    the root of an OdaException chain caught while processing the baseQuery
     * @return  true if the specified baseQuery is one of the cause(s) in the OdaException chain;
     *          false otherwise
     * @since 3.4 (DTP 1.11)
     */
    public static boolean isInvalidBaseQuery( BaseQuery baseQuery, OdaException rootEx )
    {
        if( baseQuery == null )
            return true;
        return isCauseOfException( rootEx, getInstanceId( baseQuery ) );
    }

    private static String getInstanceId( FilterExpression filterExpr )
    {
        if( filterExpr == null )
            return null;
        return filterExpr.getQualifiedId() + AT_SYMBOL + Integer.toHexString( filterExpr.hashCode() );
    }
    
    private static String getInstanceId( AggregateExpression aggrExpr )
    {
        if( aggrExpr == null )
            return null;
        return aggrExpr.getName() + AT_SYMBOL + Integer.toHexString( aggrExpr.hashCode() );
    }
    
    private static String getInstanceId( ValueExpression valueExpr )
    {
        if( valueExpr == null )
            return null;
        return valueExpr.getName() + AT_SYMBOL + Integer.toHexString( valueExpr.hashCode() );
    }
    
    private static String getInstanceId( ResultProjection resultProj )
    {
        if( resultProj == null )
            return null;
        return resultProj.getClass().getSimpleName() + AT_SYMBOL + Integer.toHexString( resultProj.hashCode() );
    }
    
    private static String getInstanceId( SortSpecification sortSpec )
    {
        if( sortSpec == null )
            return null;
        return sortSpec.getClass().getSimpleName() + AT_SYMBOL + Integer.toHexString( sortSpec.hashCode() );
    }
    
    private static String getInstanceId( ResultSetSpecification resultSetSpec )
    {
        if( resultSetSpec == null )
            return null;
        return resultSetSpec.getClass().getSimpleName() + AT_SYMBOL + Integer.toHexString( resultSetSpec.hashCode() );
    }

    private static String getInstanceId( BaseQuery baseQuery )
    {
        if( baseQuery == null )
            return null;
        return baseQuery.getName() + AT_SYMBOL + Integer.toHexString( baseQuery.hashCode() );
    }
    
}
