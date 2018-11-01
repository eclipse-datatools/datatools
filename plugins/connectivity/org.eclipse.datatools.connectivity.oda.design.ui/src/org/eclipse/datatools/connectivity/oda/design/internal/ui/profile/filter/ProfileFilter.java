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

import org.eclipse.datatools.connectivity.ICategory;
import org.eclipse.datatools.connectivity.internal.ui.wizards.CPWizardNode;
import org.eclipse.datatools.connectivity.oda.profile.internal.ProfileCategoryUtil;
import org.eclipse.datatools.connectivity.oda.util.manifest.ExtensionManifest;
import org.eclipse.datatools.connectivity.oda.util.manifest.ManifestExplorer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.ui.navigator.CommonViewer;

/**
 * A filter that hides all ODA wrapper or deprecated extensions in 
 * the Data Source Explorer view.
 */
public class ProfileFilter extends ViewerFilter
{
    public ProfileFilter()
    {
        super();
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
     */
    public boolean select( Viewer viewer, Object parentElement, Object element )
    {
        String odaDataSourceId = null;
        if( ! (viewer instanceof CommonViewer) )
        {
            // only covers filtering for the list of connection profile types displayed
            // in the New Connection Profile dialog
            if( ! (element instanceof CPWizardNode) )
                return true;
            
            String wizardProviderId = ((CPWizardNode) element).getProvider().getId();

            // ODA data source is expected to define its category with the same id; otherwise
            // this element is not for an ODA data source;
            // extra check is done here to optimize performance by avoiding unnecessary lookup 
            // of an ODA manifest
            ICategory profileCategory = ProfileCategoryUtil.getCategory( wizardProviderId );
            if( profileCategory != null &&
                wizardProviderId.equalsIgnoreCase( profileCategory.getId() ) )
                odaDataSourceId = wizardProviderId;
        }
        else if( element instanceof ICategory )
        {
            /* For filtering of a CommonViewer.
             * If an ODA data source wants to hide its ODA category from the DSE tree,
             * instead of using this filter class, it is much more efficient 
             * to implement a filterExpression in a commonFilter element of the
             * org.eclipse.ui.navigator.navigatorContent extension point.
             */

            String categoryId = ((ICategory) element).getId();
            // ODA data source is expected to use same id for its category
            odaDataSourceId = categoryId;
        }
        
        // not an ODA data source type, do not hide
        if( odaDataSourceId == null )
            return true;
        
        ExtensionManifest manifest = getElementOdaManifest( odaDataSourceId );           
        if( manifest == null )              
            return true; // not an ODA extension, do not hide
        
        // do not hide element if it is not deprecated nor a wrapper ODA extension
        return ! manifest.isDeprecated() && ! manifest.isWrapper();
    }

    private ExtensionManifest getElementOdaManifest( String odaDataSourceId )
    {
        ExtensionManifest manifest = null;
        try
        {
            manifest =
                ManifestExplorer.getInstance().getExtensionManifest( odaDataSourceId );
        }
        catch( Exception ex )
        {
            // ignore exception
        }
        return manifest;
    }
    
}
