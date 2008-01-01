/*
 *************************************************************************
 * Copyright (c) 2007 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.design.internal.ui.profile.db;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.connectivity.internal.ui.wizards.CPWizardNode;
import org.eclipse.datatools.connectivity.internal.ui.wizards.CPWizardSelectionPage;
import org.eclipse.datatools.connectivity.internal.ui.wizards.NewCPWizardCategoryFilter;
import org.eclipse.datatools.connectivity.oda.profile.Constants;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.IWizard;

/**
 * An extended Connection Profile Type Selection Page for use by 
 * the NewDbDataSourceWizard.
 * It wraps each of the DB connection profile type wizard node into an ODA extended one.
 */
public class DbTypesSelectionPage extends CPWizardSelectionPage
{
    
    private static final ViewerFilter sm_viewerFilter = 
        new NewCPWizardCategoryFilter( Constants.DATABASE_CATEGORY_ID );
    
    DbTypesSelectionPage()
    {        
        super( DbTypesSelectionPage.class.getName(), sm_viewerFilter );
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.internal.ui.wizards.CPWizardSelectionPage#getCatagoryItems(java.lang.String)
     * @Override    takes each CPWizardNode returned by the base class method
     *  and wraps in an ODA-extended node.
     */
    public List getCatagoryItems( String wizardCategory ) 
    {
        List baseWizardNodes = super.getCatagoryItems( wizardCategory );
        if( baseWizardNodes == null || baseWizardNodes.isEmpty() )
            return baseWizardNodes;
        
        assert( getWizard() instanceof NewDbDataSourceWizardBase );
        NewDbDataSourceWizardBase sessionWizard = (NewDbDataSourceWizardBase) getWizard();
        
        List wrappedWizardNodes = new ArrayList();
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
     * @see org.eclipse.jface.wizard.WizardPage#isPageComplete()
     */
    public boolean isPageComplete()
    {
        return super.isPageComplete() || getSelectedNode() != null;
    }
    
    IWizard getSelectedNodeWizard()
    {
        if( getSelectedNode() == null )
            return null;
        return getSelectedNode().getWizard();
    }
    
}
