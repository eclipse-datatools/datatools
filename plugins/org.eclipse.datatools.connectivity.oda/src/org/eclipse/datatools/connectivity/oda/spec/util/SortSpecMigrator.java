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

import org.eclipse.datatools.connectivity.oda.SortSpec;
import org.eclipse.datatools.connectivity.oda.spec.result.SortSpecification;

/**
 * <strong>EXPERIMENTAL</strong>.
 * </p>
 * An utility to migrate a pre-3.2 {@link SortSpec} instance to and from
 * a {@link SortSpecification} instance.
 * @since 3.2 (DTP 1.7)
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
        
        SortSpecification resultSortSpec = new SortSpecification( oldSortSpec.getSortMode() );
        for( int i=1; i <= oldSortSpec.getSortKeyCount(); i++ )
        {
            resultSortSpec.addSortKey( oldSortSpec.getSortColumn( i ), oldSortSpec.getSortOrder( i ) );
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
            oldSortSpec.addSortKey( resultSortSpec.getSortColumn( i ), resultSortSpec.getSortOrder( i ) );
        }
        return oldSortSpec;
    }
    
}
