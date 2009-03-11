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
import org.eclipse.datatools.connectivity.oda.spec.ExpressionArguments;
import org.eclipse.datatools.connectivity.oda.spec.ExpressionVariable;
import org.eclipse.datatools.connectivity.oda.spec.QuerySpecification;
import org.eclipse.datatools.connectivity.oda.spec.manifest.AggregateDefinition;
import org.eclipse.datatools.connectivity.oda.spec.manifest.FilterExpressionDefinition;
import org.eclipse.datatools.connectivity.oda.spec.manifest.ResultExtensionExplorer;
import org.eclipse.datatools.connectivity.oda.spec.result.CustomAggregate;
import org.eclipse.datatools.connectivity.oda.spec.result.filter.CustomExpression;

/**
 * <strong>EXPERIMENTAL</strong>.
 * Factory of expression instances for use in a {@link QuerySpecification}.
 * <br>Provides convenient methods to create expression instances.
 */
public class ExpressionFactory
{
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
            throw new IllegalArgumentException( Messages.bind( "Invalid custom filter expression id: {0}.{1}.", 
                    extensionId, exprId ));
        return filterExprDefn;
    }

    /**
     * Creates a custom aggregate expression instance of the specified aggregate expression 
     * contributed by the specified dynamicResultSet extension. 
     * The custom aggregate expression may be applied in an ODA {@link ResultProjection} specification.
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
     * The custom aggregate expression may be applied in an ODA {@link ResultProjection} specification.
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
            throw new IllegalArgumentException( Messages.bind( "Invalid custom aggregate expression id: {0}.{1}.", 
                    extensionId, exprId ));
        return aggrgExprDefn;
    }
    
}
