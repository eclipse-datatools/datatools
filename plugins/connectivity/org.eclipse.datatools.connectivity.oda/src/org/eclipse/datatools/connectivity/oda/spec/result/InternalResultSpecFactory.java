/*
 *************************************************************************
 * Copyright (c) 2010 Actuate Corporation.
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
