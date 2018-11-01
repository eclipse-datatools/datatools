/*
 *************************************************************************
 * Copyright (c) 2007, 2011 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.design.internal.ui.profile;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.ResourceIdentifiers;
import org.eclipse.datatools.connectivity.oda.design.internal.designsession.DataSourceDesignSessionBase;
import org.eclipse.datatools.connectivity.oda.design.ui.nls.Messages;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;

/**
 * Internal ODA Wizard that controls the behavior of the connection profile wizard page.
 * For use by internal packages only.
 * @since   3.0.4
 */
public class ProfileSelectionWizard extends Wizard
{
    private DataSourceDesignSessionBase m_designSession;
    private ProfileSelectionWizardPage m_page;
    private ResourceIdentifiers m_profileResourceIds;

    public ProfileSelectionWizard( DataSourceDesignSessionBase designSession )
    {
        this( designSession, null );
    }

    public ProfileSelectionWizard( DataSourceDesignSessionBase designSession,
            ResourceIdentifiers profileResourceIdentifiers )
    {
        super();
        m_designSession = designSession;
        m_profileResourceIds = profileResourceIdentifiers;
        addPages();
        setWindowTitle( Messages.profilePage_pageLabel );
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#addPages()
     */
    public void addPages()
    {
        if( m_page != null )
            return;     // already added
        
        m_page = new ProfileSelectionWizardPage( Messages.profilePage_pageTitle );
        addPage( m_page );
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#canFinish()
     */
    public boolean canFinish()
    {
        // requires user to go to the custom ODA wizard page where
        // the necessary operation is performed
        return false;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    public boolean performFinish()
    {
        return true;
    }
    
    /**
     * Obtains the custom wizard starting page provided by
     * the selected profile's ODA designer extension.
     * Also updates the design session of the selected ODA extension
     * and the name of the data source design.
     * @param profileSelection
     * @return
     */
    IWizardPage getSelectedProfileStartingPage( ProfileSelection profileSelection )
    {
        if( profileSelection == null ||
            profileSelection.getOdaDataSourceId() == null )
        {
            return null;
        }
        
        try
        {
            return m_designSession.getNewCustomOdaStartingPage( 
                    profileSelection.getOdaDataSourceId(),
                    profileSelection.getDataSourceDesignName(),
                    profileSelection.getProfileRef() );
        }
        catch( OdaException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Returns the design resource identifiers of a connection profile store file location.
     * @return the host resource identifiers; may be null if none was specified by client
     * @since 3.2.6 (DTP 1.9.2)
     */
    ResourceIdentifiers getHostResourceIdentifiers()
    {
        return m_profileResourceIds;
    }

}
