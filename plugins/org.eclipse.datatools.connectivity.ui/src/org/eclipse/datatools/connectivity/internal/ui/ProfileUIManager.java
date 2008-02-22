/*
 *************************************************************************
 * Copyright (c) 2007, 2008 Sybase, Inc.
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
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ui.wizards.ProfilePropertyPage;
import org.eclipse.jface.preference.IPreferenceNode;
import org.eclipse.jface.preference.IPreferencePage;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.PreferencesUtil;
import org.eclipse.ui.internal.dialogs.PropertyPageContributorManager;
import org.eclipse.ui.internal.dialogs.PropertyPageManager;

/**
 *  An internal utility class of the Connection Profile UI Manager.
 *  This currently uses internal Platform UI packages to get at property page details.
 *  It is pending switch to use Platform UI patch (Bugzilla 208830) in 3.4,
 *  when DTP no longer needs to provide backward compatibility to Platform version 3.3.  
 *  See TODO tags below.
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
        // TODO replace with call to method in org.eclipse.ui.dialogs.PreferencesUtil
        return hasPropertiesContributors( element );
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

        // TODO replace with call to method in org.eclipse.ui.dialogs.PreferencesUtil
        IPreferenceNode[] nodes = propertiesContributorsFor( element ); 
        
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
        // TODO replace with call to method in org.eclipse.ui.dialogs.PreferencesUtil
        IPreferenceNode[] nodes = propertiesContributorsFor( profile );
        
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

    /*
     * TODO Below utility methods are a copy of new utility API methods added to 
     * org.eclipse.ui.dialogs.PreferencesUtil in 3.4 M6 (Bugzilla 208830 patch 89658).
     * Need to remove these methods when DTP no longer needs to provide 
     * backward compatibility to Eclipse platform version 3.3.
     */

    /**
     * Indicates whether the specified element has at least one property page
     * contributor.
     * 
     * @param element
     *            an adapter element of a property page
     * @return true for having at least one contributor; false otherwise
     * @since 3.4
     */
    public static boolean hasPropertiesContributors(Object element) {
        if (element == null || !(element instanceof IAdaptable))
            return false;
        Collection contributors = PropertyPageContributorManager.getManager()
                .getApplicableContributors(element);
        return contributors != null && contributors.size() > 0;
    }

    /**
     * Return all of the properties page contributors for an element.
     * @param element
     * @return {@link IPreferenceNode}[]
     * @since 3.4
     */
    public static IPreferenceNode[] propertiesContributorsFor(Object element) {
        PropertyPageManager pageManager = new PropertyPageManager();
            if (element == null) {
            return null;
        }
        // load pages for the selection
        // fill the manager with contributions from the matching contributors
        PropertyPageContributorManager.getManager().contribute(pageManager,
                element);
        // testing if there are pages in the manager
        List pages =  pageManager.getElements(PreferenceManager.PRE_ORDER);
        IPreferenceNode[] nodes = new IPreferenceNode[pages.size()];
        pages.toArray(nodes);
        return nodes;
    }
    
}
