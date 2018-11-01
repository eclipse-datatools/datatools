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

import org.eclipse.datatools.connectivity.oda.spec.ExpressionArguments;
import org.eclipse.datatools.connectivity.oda.spec.ExpressionVariable;
import org.eclipse.datatools.connectivity.oda.spec.result.filter.CustomExpression;

@SuppressWarnings("restriction")
public class MyCustomExpression extends CustomExpression
{

    public MyCustomExpression( String extensionId, String id )
    {
        super( extensionId, id );
        // TODO Auto-generated constructor stub
    }

    public MyCustomExpression( String extensionId, String id,
            ExpressionVariable variable, ExpressionArguments args )
    {
        super( extensionId, id, variable, args );
        // TODO Auto-generated constructor stub
    }

    public MyCustomExpression()
    {
        // TODO Auto-generated constructor stub
    }

}
