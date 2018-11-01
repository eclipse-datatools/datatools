/*
 *************************************************************************
 * Copyright (c) 2007 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.profile.internal;

import org.eclipse.datatools.connectivity.ICategory;
import org.eclipse.datatools.connectivity.IConnectionProfileProvider;
import org.eclipse.datatools.connectivity.internal.CategoryProvider;
import org.eclipse.datatools.connectivity.internal.ConnectionProfileManager;
import org.eclipse.datatools.connectivity.oda.profile.Constants;

/**
 *  An internal utility class for ODA connection profile category definition.
 *  @since DTP 1.6
 */
public class ProfileCategoryUtil
{

    /**
     * Returns the category instance of the connection profile type defined
     * with the specified ODA data source ID.
     * @param odaDataSourceId   ODA data source type ID
     * @return  a connection profile category instance
     */
    public static ICategory getCategory( String odaDataSourceId )
    {
        IConnectionProfileProvider profileProvider =
            ConnectionProfileManager.getInstance().getProvider( odaDataSourceId );
        
        return ( profileProvider != null ) ? profileProvider.getCategory() : null;
    }

    /**
     * Indicates whether the specified connection profile category
     * has an unknown category id.
     * @param profileCategory   a connection profile category
     * @return  true if the specified category is null or unknown type;
     *      false otherwise
     */
    public static boolean isUnknownCategory( ICategory profileCategory )
    {
        String categoryId = ( profileCategory != null ) ?
                profileCategory.getId() : null;
        return isUnknownCategory( categoryId );
    }

    /**
     * Indicates whether the specified connection profile category id
     * is defined as unknown.
     * @param profileCategory   a connection profile category
     * @return  true if the specified category id is null or defined as unknown;
     *      false otherwise
     */
    public static boolean isUnknownCategory( String categoryId )
    {
        return ( categoryId == null || 
                 categoryId.equals( CategoryProvider.ID_CATEGORY_UNKNOWN ) );
    }

    /**
     * Indicates whether the specified connection profile category is defined
     * under a parent category with the ODA parent id.
     * @param profileCategory  a connection profile category
     * @return  true if the specified category is under an ODA parent category;
     *      false otherwise
     */
    public static boolean hasODAParentCategory( ICategory profileCategory )
    {
        if( profileCategory == null || profileCategory.getParent() == null )
            return false;   // unknown parent category
    
        return profileCategory.getParent().getId()
                    .equalsIgnoreCase( Constants.ODA_PARENT_CATEGORY_ID );       
    }

}
