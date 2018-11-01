/*
 *************************************************************************
 * Copyright (c) 2007, 2010 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.design.internal.ui.profile.db;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.connectivity.internal.ui.wizards.CPWizardNode;
import org.eclipse.datatools.connectivity.internal.ui.wizards.CPWizardSelectionPage;
import org.eclipse.datatools.connectivity.internal.ui.wizards.NewCPWizardCategoryFilter;
import org.eclipse.datatools.connectivity.oda.profile.Constants;
import org.eclipse.datatools.connectivity.ui.wizards.ConnectionProfileDetailsPage;
import org.eclipse.datatools.connectivity.ui.wizards.IProfileWizardProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.widgets.Composite;

/**
 * An extended Connection Profile Type Selection Page for use by 
 * the NewDbDataSourceWizardBase.
 * It wraps each of the DB connection profile type wizard node into an ODA extended one.
 */
public class DbTypesSelectionPage extends CPWizardSelectionPage
{
    
    private static final ViewerFilter[] sm_viewerFilters = new ViewerFilter[]
    { 
        // primary filter to include those under the Databases profile category
        new NewCPWizardCategoryFilter( Constants.DATABASE_CATEGORY_ID ),
        // secondary filter to hide all db profile types that use NewDbDataSourceWizardBase, 
        // which is the client of this page
        new DbProfileWizardFilter()
    };
    
    DbTypesSelectionPage()
    {        
        super( DbTypesSelectionPage.class.getName(), sm_viewerFilters );
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.internal.ui.wizards.CPWizardSelectionPage#getCategoryItems(java.lang.String)
     * @Override    takes each CPWizardNode returned by the base class method
     *  and wraps in an ODA-extended node.
     */
    public List getCategoryItems( String wizardCategory ) 
    {
        List baseWizardNodes = super.getCategoryItems( wizardCategory );
        if( baseWizardNodes == null || baseWizardNodes.isEmpty() )
            return baseWizardNodes;
        
        assert( getWizard() instanceof NewDbDataSourceWizardBase );
        NewDbDataSourceWizardBase sessionWizard = (NewDbDataSourceWizardBase) getWizard();
        
        List<Object> wrappedWizardNodes = new ArrayList<Object>();
        Iterator iter = baseWizardNodes.iterator();
        while( iter.hasNext() )
        {
            Object wizardNode = iter.next();
            if( wizardNode.getClass() == CPWizardNode.class )
            {
                wizardNode = new OdaDbProfileWizardNode( 
                        ((CPWizardNode) wizardNode ).getProvider(),
                        sessionWizard );
            }
            wrappedWizardNodes.add( wizardNode );
        }
        return wrappedWizardNodes;
    }
          
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.internal.ui.wizards.CPWizardSelectionPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl( Composite parent )
    {       
        super.createControl( parent );
        
        // hide the profile info controls whose conflicting values are ignored anyway
        setProfileNameVisible( false );
        setProfileDescriptionVisible( false );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.internal.ui.wizards.CPWizardSelectionPage#getNextPage()
     */
    @Override
    public IWizardPage getNextPage()
    {
        boolean arePageControlsCreated = getSelectedNode() != null && getSelectedNode().isContentCreated();

        IWizardPage wizardPage = super.getNextPage();
        
        if( ! arePageControlsCreated && wizardPage instanceof ConnectionProfileDetailsPage )
        {
            ((ConnectionProfileDetailsPage)wizardPage).setCreateAutoConnectControls( false );
        }
        return wizardPage;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.wizard.WizardPage#isPageComplete()
     */
    public boolean isPageComplete()
    {
        if( super.isPageComplete() )
            return true;
        if( getSelectedNode() != null )
        {
            IWizard cpNodeWizard = getSelectedNodeWizard();
            return cpNodeWizard != null && cpNodeWizard.canFinish();
        }
        
        return false;
    }
    
    IWizard getSelectedNodeWizard()
    {
        if( getSelectedNode() == null )
            return null;
        return getSelectedNode().getWizard();
    }

    /**
     * Filter to hide any NewDbDataSourceWizardBase nodes from 
     * this profile wizard selection page.
     */
    static class DbProfileWizardFilter extends ViewerFilter
    {
        public boolean select( Viewer viewer, Object parentElement, Object element ) 
        {
            if( ! ( element instanceof CPWizardNode ))
                return true;
            
            CPWizardNode wizardNode = (CPWizardNode) element;
            if( ! ( wizardNode.getProvider() instanceof IProfileWizardProvider ))
                return true;
                
            IProfileWizardProvider profileWizProvider = wizardNode.getProvider();
            IWizard profileWizard = profileWizProvider.getWizard();
            boolean dbWrapperWizard = ( profileWizard instanceof NewDbDataSourceWizardBase );
            return ! dbWrapperWizard;  // hide if using a db profile wrapper wizard
        }
    }

}
