/*
 *************************************************************************
 * Copyright (c) 2008 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.jdbc.dbprofile.sample.ui.filter;

import java.util.ArrayList;

import org.eclipse.datatools.connectivity.ui.actions.AddProfileViewAction;
import org.eclipse.jface.viewers.ViewerFilter;

/**
 * Extends the DTP Data Source Explorer's Default Add Profile Action
 * to filter out the Databases Connection Profile wrapper type from the DSE.
 */
public class AddDbProfileViewAction extends AddProfileViewAction
{
    private ViewerFilter m_dbProfileFilter;

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.ui.actions.AddProfileViewAction#getWizardSelectionFilters()
     */
    protected ViewerFilter[] getWizardSelectionFilters()
    {
        // overrides base class method to provide an additional filter to 
        // hide the Databases wrapper profile type in the DSE view
        ViewerFilter[] baseFilters = super.getWizardSelectionFilters();
        return appendFilter( baseFilters, getDbProfileFilter() );
    }

    private ViewerFilter getDbProfileFilter()
    {
        if( m_dbProfileFilter == null )
            m_dbProfileFilter = new DbProfileCategoryFilter();
        
        return m_dbProfileFilter;
    }

    private ViewerFilter[] appendFilter( ViewerFilter[] filters, ViewerFilter moreFilter )
    {
        ArrayList mergedFilters = new ArrayList();
        if( filters != null )
        {
            for( int i=0; i < filters.length; i++ )
            {
                mergedFilters.add( filters[i] );
            }
        }
        
        mergedFilters.add( moreFilter );
        return (ViewerFilter[]) mergedFilters.toArray( new ViewerFilter[ mergedFilters.size() ] );
    }
    
}
