/*
 *************************************************************************
 * Copyright (c) 2007 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Sybase, Inc. - initial API and implementation
 *  Actuate Corporation - extracted implementation to this utility class
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.internal.ui;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ui.wizards.ProfilePropertyPage;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPropertyPage;
import org.eclipse.ui.internal.dialogs.PropertyDialog;
import org.eclipse.ui.internal.dialogs.PropertyPageContributorManager;
import org.eclipse.ui.internal.dialogs.PropertyPageManager;
import org.eclipse.ui.internal.dialogs.RegistryPageContributor;

/**
 *  An utility class of the Connection Profile UI Manager.
 *  TODO: This currently uses internal Platform UI packages to get at property page details.
 *  It is pending resolution to Bugzilla 208830 for Platform UI to make
 *  some of the internal APIs public or offer alternatives that we can use.
 */
public class ProfileUIManager
{
    /**
     * Indicates whether the specified element has at least one property page contributor.
     * @param element   an adapter element of a property page
     * @return  true for having at least one contributor; false otherwise
     */
    public static boolean hasContributors( Object element ) 
    {
        if ( element == null || !(element instanceof IAdaptable) )
            return false;
        Collection contributors = PropertyPageContributorManager.getManager()
                .getApplicableContributors( element );
        return contributors != null && contributors.size() > 0;
    }

    /**
     * Creates a new preference dialog under the control of the element's 
     * property page manager.
     * @param parentShell   the parent shell
     * @param selection     the selection that will be used to determine target object
     * @param element       an adaptable element that owns the properties shown in a property page
     * @return  the preference dialog
     */
    public static PreferenceDialog createPreferenceDialog( Shell parentShell, 
            ISelection selection, Object element )
    {
        if ( element == null || !(element instanceof IAdaptable) )
            return null;

        // load pages for the selection
        // fill the manager with contributions from the matching contributors
        PropertyPageManager pageManager = new PropertyPageManager();
        PropertyPageContributorManager.getManager().contribute( pageManager, element );

        PropertyDialog propertyDialog = new PropertyDialog( parentShell, pageManager, selection );
        propertyDialog.create();
        return propertyDialog;
    }
    
    /**
     * Creates a new property page contribution of the specified connection profile.
     * @param profile   a connection profile
     * @return  the profile property page
     */
    public static ProfilePropertyPage createPropertyPage( IConnectionProfile profile )
    {
        Collection propertyPageContributions =
            PropertyPageContributorManager.getManager().getApplicableContributors( profile );
        
        Iterator iter = propertyPageContributions.iterator();
        while( iter.hasNext() )
        {
            RegistryPageContributor contributor = (RegistryPageContributor) iter.next();
            IWorkbenchPropertyPage profilePropPage = null;
            try
            {
                profilePropPage = contributor.createPage( profile );
            }
            catch( CoreException ex )
            {
                // ignore exception
                continue;   // try the next contribution
            }

            if( ! ( profilePropPage instanceof ProfilePropertyPage ))
            {
                if( profilePropPage != null )
                    profilePropPage.dispose();
                continue;   // try the next contribution
            }
            
            return (ProfilePropertyPage) profilePropPage;
        }

        return null;
    }
    
}
