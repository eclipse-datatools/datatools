/*
 *************************************************************************
 * Copyright (c) 2008 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.design.internal.ui.profile.filter;

import java.util.ArrayList;

import org.eclipse.datatools.connectivity.ui.actions.AddProfileViewAction;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.widgets.Shell;

/**
 * Extends the DTP Data Source Explorer's default Add Profile Action
 * to hide deprecated and wrapper ODA data source types from the New Connection Profile dialog.
 */
public class NewProfileAction extends AddProfileViewAction
{
    private ViewerFilter m_profileFilter;

    public NewProfileAction()
    {
        super();
    }
    
    public NewProfileAction( Shell dialogParentShell )
    {
        super();
        init( dialogParentShell );
    }
    
    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.ui.actions.AddProfileViewAction#getWizardSelectionFilters()
     */
    protected ViewerFilter[] getWizardSelectionFilters()
    {
        // overrides base class method to provide an additional filter to 
        // hide applicable ODA data source types
        ViewerFilter[] baseFilters = super.getWizardSelectionFilters();
        return appendFilter( baseFilters, getProfileFilter() );
    }

    private ViewerFilter getProfileFilter()
    {
        if( m_profileFilter == null )
            m_profileFilter = new ProfileFilter();        
        return m_profileFilter;
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
