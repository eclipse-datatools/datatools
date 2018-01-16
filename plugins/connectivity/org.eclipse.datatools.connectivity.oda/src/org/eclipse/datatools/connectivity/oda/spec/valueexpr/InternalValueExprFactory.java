/*
 *************************************************************************
 * Copyright (c) 2010 Actuate Corporation.
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

/**
 *  Internal factory of package classes. 
 */
public class InternalValueExprFactory
{
    public static CustomFunction createCustomFunction( String extensionId, String id )
    {
        return new CustomFunction( extensionId, id );
    }
    
    public static CombinedValueExpressionOperator createCombinedOperator( String id, String literal )
    {
        return new CombinedValueExpressionOperator( id, literal );
    }
    
}
