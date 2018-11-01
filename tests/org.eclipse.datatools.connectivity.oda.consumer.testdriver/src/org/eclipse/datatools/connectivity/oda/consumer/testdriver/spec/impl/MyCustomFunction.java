/*
 *************************************************************************
 * Copyright (c) 2009 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.consumer.testdriver.spec.impl;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.spec.ValidationContext;
import org.eclipse.datatools.connectivity.oda.spec.manifest.FunctionExpressionDefinition;
import org.eclipse.datatools.connectivity.oda.spec.valueexpr.CustomFunction;

/**
 * Test class implementation of a custom function value expression type.
 */
public class MyCustomFunction extends CustomFunction
{

    /**
     * @param extensionId
     * @param id
     */
    public MyCustomFunction( String extensionId, String id )
    {
        super( extensionId, id );
    }

    /**
     * 
     */
    public MyCustomFunction()
    {
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.valueexpr.CustomFunction#getName()
     */
    @Override
    public String getName()
    {
        return "My" + super.getName();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.valueexpr.CustomFunction#validateSyntax(org.eclipse.datatools.connectivity.oda.spec.ValidationContext, org.eclipse.datatools.connectivity.oda.spec.manifest.FunctionExpressionDefinition)
     */
    @Override
    protected void validateSyntax( ValidationContext context,
            FunctionExpressionDefinition defn ) throws OdaException
    {
        // TODO override with custom validation
        super.validateSyntax( context, defn );
    }

}
