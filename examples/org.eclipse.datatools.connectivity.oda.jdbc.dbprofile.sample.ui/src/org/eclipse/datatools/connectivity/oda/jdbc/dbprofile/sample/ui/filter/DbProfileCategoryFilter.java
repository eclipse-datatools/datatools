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

import org.eclipse.datatools.connectivity.ICategory;
import org.eclipse.datatools.connectivity.internal.ui.wizards.CPWizardNode;
import org.eclipse.datatools.connectivity.oda.jdbc.dbprofile.sample.Activator;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.ui.navigator.CommonViewer;

/**
 * A filter that hides the ODA DB Profile wrapper in the Data Source Explorer view.
 */
public class DbProfileCategoryFilter extends ViewerFilter
{
    private static String sm_runtimePluginId;
    
    public DbProfileCategoryFilter()
    {
        super();
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
     */
    public boolean select( Viewer viewer, Object parentElement, Object element )
    {
        if( ! (viewer instanceof CommonViewer) )
        {
            if( ! (element instanceof CPWizardNode) )
                return true;
            
            String wizardProviderId = ((CPWizardNode) element).getProvider().getId();
            return ! getRuntimePluginId().equalsIgnoreCase( wizardProviderId );
        }
        
        if( ! (element instanceof ICategory) )
            return true;

        // filters out this oda data source id in the DSE
        String categoryId = ((ICategory) element).getId();
        return ! categoryId.equalsIgnoreCase( getRuntimePluginId() );
    }

    private static String getRuntimePluginId()
    {
        if( sm_runtimePluginId == null )
            sm_runtimePluginId = Activator.getBundleId();
        return sm_runtimePluginId;
    }
    
}
