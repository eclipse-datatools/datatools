/*
 *************************************************************************
 * Copyright (c) 2007, 2008 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Sybase, Inc. - initial API and implementation
 *  Actuate Corporation - extracted implementation to this utility class
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.internal.ui;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ui.wizards.ProfilePropertyPage;
import org.eclipse.jface.preference.IPreferenceNode;
import org.eclipse.jface.preference.IPreferencePage;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.PreferencesUtil;

/**
 *  An internal utility class of the Connection Profile UI Manager.
 *  This has now switched from using internal Platform UI packages 
 *  to Platform UI API methods that were added in 3.4 (Bugzilla 208830). 
 *  Backward compatibility to Eclipse Platform version 3.3 is no longer supported from DTP 1.7 on.
 *  @since DTP 1.6
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
        return PreferencesUtil.hasPropertiesContributors( element );
    }

    /**
     * Creates a new preference dialog that shows all property page contributions of the 
     * specified element.  The pages shown meet the same filtering criteria the search uses.
     * @param parentShell   the shell to use to parent the dialog if required
     * @param element       an adaptable element to open the dialog on
     * @return  A preference dialog showing properties for the specified element, or
     *         <code>null</code> if it could not be created
     */
    public static PreferenceDialog createPreferenceDialog( Shell parentShell, Object element )
    {
        if ( element == null || !(element instanceof IAdaptable) )
            return null;

        IPreferenceNode[] nodes = PreferencesUtil.propertiesContributorsFor( element ); 
        
        String[] displayedIds = new String[nodes.length]; 
        for ( int i = 0; i < nodes.length; i++ ) 
        { 
            displayedIds[i] = nodes[i].getId(); 
        } 
        PreferenceDialog propertyDialog = 
                PreferencesUtil.createPropertyDialogOn( parentShell, (IAdaptable) element, 
                        null, displayedIds, null ); 
        return propertyDialog; 
    }
    
    /**
     * Creates a new property page contribution of the specified connection profile.
     * @param profile   a connection profile
     * @return  the profile property page
     */
    public static ProfilePropertyPage createPropertyPage( IConnectionProfile profile )
    {
        IPreferenceNode[] nodes = PreferencesUtil.propertiesContributorsFor( profile );
        
        for( int i=0; i < nodes.length; i++ )
        {
            IPreferenceNode pageNode = nodes[i];
            IPreferencePage propPage = pageNode.getPage();
            if( propPage == null )  // page is not created yet
            {
                pageNode.createPage();
                propPage = pageNode.getPage();
            }
            
            if( ! ( propPage instanceof ProfilePropertyPage ))
            {
                if( propPage != null )
                    pageNode.disposeResources();
                continue;   // try the next page contribution node
            }
            
            return (ProfilePropertyPage) propPage;
        }

        return null;
    }
    
}
