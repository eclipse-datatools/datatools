/*
 *************************************************************************
 * Copyright (c) 2009 Actuate Corporation.
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

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.nls.Messages;
import org.eclipse.datatools.connectivity.oda.spec.ExpressionArguments;
import org.eclipse.datatools.connectivity.oda.spec.ExpressionVariable;
import org.eclipse.datatools.connectivity.oda.spec.manifest.AggregateDefinition;
import org.eclipse.datatools.connectivity.oda.spec.manifest.CombinedExpressionOperatorDefinition;
import org.eclipse.datatools.connectivity.oda.spec.manifest.FunctionExpressionDefinition;
import org.eclipse.datatools.connectivity.oda.spec.manifest.FilterExpressionDefinition;
import org.eclipse.datatools.connectivity.oda.spec.manifest.ResultExtensionExplorer;
import org.eclipse.datatools.connectivity.oda.spec.result.CustomAggregate;
import org.eclipse.datatools.connectivity.oda.spec.result.filter.CustomExpression;
import org.eclipse.datatools.connectivity.oda.spec.valueexpr.CombinedValueExpressionOperator;
import org.eclipse.datatools.connectivity.oda.spec.valueexpr.CustomFunction;

/**
 * Factory of expression instances for use in a 
 * {@link org.eclipse.datatools.connectivity.oda.spec.QuerySpecification}.
 * <br>Provides convenient methods to create expression instances.
 * @since 3.3 (DTP 1.8)
 */
public class ExpressionFactory
{
    private static final String DOT_SEPARATOR = "."; //$NON-NLS-1$
    
    /**
     * Creates a custom filter expression instance of the specified filter expression
     * contributed by the specified dynamicResultSet extension. 
     * The custom filter expression may be applied as part of an ODA filter specification.
     * @param extensionId   unique id of an extension that implements the dynamicResultSet extension point
     * @param exprId    id of a custom filter expression 
     * @return  an instance of CustomExpression or its subclass contributed by 
     *          the specified dynamicResultSet extension
     * @throws IllegalArgumentException if the specified extension and/or expression are not valid
     * @throws OdaException
     */
    public static CustomExpression createCustomExpression( String extensionId, String exprId ) 
        throws IllegalArgumentException, OdaException
    {
        return findCustomFilterDefinition( extensionId, exprId ).createExpression();
    }
 
    /**
     * Creates a custom filter expression instance, with the given expression context, 
     * of the specified filter expression contributed by the specified dynamicResultSet extension. 
     * The custom filter expression may be applied as part of an ODA filter specification.
     * @param extensionId   unique id of an extension that implements the dynamicResultSet extension point
     * @param exprId    id of a custom filter expression 
     * @param variable  the expression variable to set on the created instance; may be null
     * @param args  the expression arguments to set on the created instance; may be null
     * @return  an instance of CustomExpression or its subclass contributed by 
     *          the specified dynamicResultSet extension
     * @throws IllegalArgumentException if the specified extension and/or expression are not valid
     * @throws OdaException
     */
    public static CustomExpression createCustomExpression( String extensionId, String exprId,
            ExpressionVariable variable, ExpressionArguments args ) 
        throws IllegalArgumentException, OdaException
    {
        return findCustomFilterDefinition( extensionId, exprId ).createExpression( variable, args );
    }
    
    private static FilterExpressionDefinition findCustomFilterDefinition( String extensionId, String exprId ) 
        throws IllegalArgumentException, OdaException
    {
        FilterExpressionDefinition filterExprDefn =
            ResultExtensionExplorer.getInstance().getExtensionFilterDefinition( extensionId, exprId );
        if( filterExprDefn == null )
            throw new IllegalArgumentException( Messages.bind( Messages.querySpec_NON_DEFINED_CUSTOM_FILTER, 
                    extensionId + DOT_SEPARATOR + exprId ));
        return filterExprDefn;
    }

    /**
     * Creates a custom aggregate expression instance of the specified aggregate expression 
     * contributed by the specified dynamicResultSet extension. 
     * The custom aggregate expression may be applied in an ODA 
     * {@link org.eclipse.datatools.connectivity.oda.spec.result.ResultProjection} specification.
     * @param extensionId   unique id of an extension that implements the dynamicResultSet extension point
     * @param exprId    id of a custom filter expression 
     * @return  an instance of CustomAggregate or its subclass contributed by 
     *          the specified dynamicResultSet extension
     * @throws IllegalArgumentException if the specified extension and/or expression are not valid
     * @throws OdaException
     */
    public static CustomAggregate createCustomAggregate( String extensionId, String exprId )
        throws IllegalArgumentException, OdaException
    {
        return findCustomAggregateDefinition( extensionId, exprId ).createExpression();
    }
    
    /**
     * Creates a custom aggregate expression instance, with the given expression variable, 
     * of the specified aggregate expression contributed by the specified dynamicResultSet extension. 
     * The custom aggregate expression may be applied in an ODA 
     * {@link org.eclipse.datatools.connectivity.oda.spec.result.ResultProjection} specification.
     * @param extensionId   unique id of an extension that implements the dynamicResultSet extension point
     * @param exprId    id of a custom filter expression 
     * @param variable  the input source variable to set on the created instance; may be null
     * @return  an instance of CustomAggregate or its subclass contributed by 
     *          the specified dynamicResultSet extension
     * @throws IllegalArgumentException if the specified extension and/or expression are not valid
     * @throws OdaException
     */
    public static CustomAggregate createCustomAggregate( String extensionId, String exprId, ExpressionVariable variable )
        throws IllegalArgumentException, OdaException
    {
        return findCustomAggregateDefinition( extensionId, exprId ).createExpression( variable );
    }
    
    private static AggregateDefinition findCustomAggregateDefinition( String extensionId, String exprId ) 
        throws IllegalArgumentException, OdaException
    {
        AggregateDefinition aggrgExprDefn =
            ResultExtensionExplorer.getInstance().getExtensionAggregateDefinition( extensionId, exprId );
        if( aggrgExprDefn == null )
            throw new IllegalArgumentException( Messages.bind( Messages.querySpec_NON_DEFINED_CUSTOM_AGGR, 
                    extensionId + DOT_SEPARATOR + exprId ));
        return aggrgExprDefn;
    }
 
    /**
     * Obtains the instance of the specified combined operator type,
     * as supported or contributed by the specified dynamicResultSet extension.
     * A dynamicResultSet extension may override a built-in operator type, whose custom implementation
     * would be returned.
     * The returned operator instance may be applied to combine value expressions in an ODA 
     * {@link org.eclipse.datatools.connectivity.oda.spec.valueexpr.CombinedValueExpression}.
     * @param extensionId   unique id of an extension that implements the dynamicResultSet extension point
     * @param operatorId    the id of a value expression combined operator type
     * @return  an instance of ValueExpressionCombinedOperator or its subclass contributed by 
     *          the specified dynamicResultSet extension
     * @throws IllegalArgumentException if the specified extension and/or operator are not valid
     * @throws OdaException
     */
    public static CombinedValueExpressionOperator getCombinedOperator( String extensionId, String operatorId ) 
        throws IllegalArgumentException, OdaException
    {
        CombinedExpressionOperatorDefinition combinedOpDefn =
            ResultExtensionExplorer.getInstance().getExtensionCombinedOperatorDefinition( extensionId, operatorId );
        if( combinedOpDefn == null )
            throw new IllegalArgumentException( Messages.bind( Messages.querySpec_NON_DEFINED_COMBINED_OP, 
                    extensionId + DOT_SEPARATOR + operatorId ));

        return combinedOpDefn.getOperator();
    }

    /**
     * Creates a custom function value expression instance of the specified function type
     * contributed by the specified dynamicResultSet extension. 
     * Caller may use the returned instance as a value expression, and 
     * assign function arguments to it as appropriate.
     * @param extensionId   unique id of an extension that implements the dynamicResultSet extension point
     * @param functionId    id of a custom function expression type
     * @return  an instance of CustomFunction or its subclass contributed by 
     *          the specified dynamicResultSet extension
     * @throws IllegalArgumentException if the specified extension and/or function ids are not valid
     * @throws OdaException
     */
    public static CustomFunction createCustomFunction( String extensionId, String functionId )
        throws IllegalArgumentException, OdaException
    {
        return findCustomFunctionDefinition( extensionId, functionId ).createExpression();
    }
    
    private static FunctionExpressionDefinition findCustomFunctionDefinition( String extensionId, 
            String functionId ) 
        throws IllegalArgumentException, OdaException
    {
        FunctionExpressionDefinition functionDefn =
            ResultExtensionExplorer.getInstance().getExtensionFunctionDefinition( extensionId, functionId );
        if( functionDefn == null )
            throw new IllegalArgumentException( Messages.bind( Messages.querySpec_NON_DEFINED_CUSTOM_FUNC, 
                    extensionId + DOT_SEPARATOR + functionId ));
        return functionDefn;
    }

}
