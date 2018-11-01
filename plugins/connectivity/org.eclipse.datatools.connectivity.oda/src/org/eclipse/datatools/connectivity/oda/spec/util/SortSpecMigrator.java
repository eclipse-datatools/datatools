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

import org.eclipse.datatools.connectivity.oda.SortSpec;
import org.eclipse.datatools.connectivity.oda.nls.Messages;
import org.eclipse.datatools.connectivity.oda.spec.result.SortSpecification;
import org.eclipse.datatools.connectivity.oda.spec.result.ColumnIdentifier;

/**
 * An utility to convert a pre-3.2 {@link SortSpec} instance to and from
 * a {@link SortSpecification} instance.
 * @since 3.3 (DTP 1.8)
 */
public class SortSpecMigrator
{
    /**
     * Converts the specified pre-3.2 {@link SortSpec} instance to a 
     * {@link SortSpecification} instance that can be used in a QuerySpecification.
     * @param oldSortSpec   a pre-3.2 {@link SortSpec} instance to convert from
     * @return  a new {@link SortSpecification} instance
     */
    public static SortSpecification convertSortSpecification( SortSpec oldSortSpec )
    {
        if( oldSortSpec == null )
            return null;
        
        SortSpecification resultSortSpec = 
            new QuerySpecificationHelper().createSortSpecification( oldSortSpec.getSortMode() );
        for( int i=1; i <= oldSortSpec.getSortKeyCount(); i++ )
        {
            resultSortSpec.addSortKey( 
                    new ColumnIdentifier( oldSortSpec.getSortColumn( i )), 
                    oldSortSpec.getSortOrder( i ) );
        }
        return resultSortSpec;
    }
    
    /**
     * Converts the specified {@link SortSpecification} instance used in a QuerySpecification
     * to a pre-3.2 {@link SortSpec} instance.
     * @param resultSortSpec    a {@link SortSpecification} instance to convert from
     * @return  a new pre-3.2 {@link SortSpec} instance
     */
    public static SortSpec convertSortSpecification( SortSpecification resultSortSpec )
    {
        if( resultSortSpec == null )
            return null;
        
        SortSpec oldSortSpec = new SortSpec( resultSortSpec.getSortMode() );
        for( int i=1; i <= resultSortSpec.getSortKeyCount(); i++ )
        {
            ColumnIdentifier sortColumn = resultSortSpec.getSortColumn( i );
            if( sortColumn == null )
                throw new IllegalArgumentException( Messages.bind( Messages.querySpec_INVALID_ARG, resultSortSpec ));
            oldSortSpec.addSortKey( sortColumn.getNameExpression(), resultSortSpec.getSortDirection( i ) );
        }
        return oldSortSpec;
    }
    
}
