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

package org.eclipse.datatools.connectivity.oda.spec.result;

import org.eclipse.datatools.connectivity.oda.spec.ExpressionVariable;

/**
 *  Internal factory of package classes. 
 */
public class InternalResultSpecFactory
{
    public static CustomAggregate createCustomAggregate( String extensionId, String id, ExpressionVariable inputSourceVar )
    {
        return new CustomAggregate( extensionId, id, inputSourceVar );
    }
    
    public static CustomAggregate createCustomAggregate( String extensionId, String id )
    {
        return new CustomAggregate( extensionId, id );
    }

    public static ResultProjection createResultProjection()
    {
        return new ResultProjection();
    }
    
    public static ResultSetSpecification createResultSetSpecification()
    {
        return new ResultSetSpecification();
    }
    
    public static SortSpecification createSortSpecification()
    {
        return new SortSpecification();
    }
    
    public static SortSpecification createSortSpecification( int sortMode )
    {
        return new SortSpecification( sortMode );
    }
    
}
