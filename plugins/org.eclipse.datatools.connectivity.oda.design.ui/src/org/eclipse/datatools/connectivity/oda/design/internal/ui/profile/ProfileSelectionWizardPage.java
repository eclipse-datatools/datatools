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

package org.eclipse.datatools.connectivity.oda.design.internal.ui.profile;

import org.eclipse.datatools.connectivity.oda.design.internal.designsession.DataSourceDesignSessionBase.DesignNameValidatorBase;
import org.eclipse.datatools.connectivity.oda.design.ui.nls.Messages;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;

/**
 * Internal ODA connection profile wizard page for users to select 
 * a connection profile to define a data source design.
 * For use by internal packages only.
 * @since   3.0.4
 */
public class ProfileSelectionWizardPage extends WizardPage
{
    private ProfileSelectionPageHelper m_pageHelper;
    
    public ProfileSelectionWizardPage( String pageName )
    {
        super( pageName );
        setTitle( Messages.profilePage_pageTitle );
        setMessage( Messages.profilePage_selectProfileDefaultMessage );
    }
    
    private ProfileSelectionPageHelper getPageHelper()
    {
        if ( m_pageHelper == null )
            m_pageHelper = new ProfileSelectionPageHelper( this );        
        return m_pageHelper;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    public void createControl( Composite parent )
    {
        setControl( getPageHelper().createControl( parent ) );
    }
    
    public ProfileSelection collectProfileSelection()
    {
        if ( m_pageHelper != null )
            return m_pageHelper.collectProfileSelection();
        // controls are not created yet, nothing to collect
        return null;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.DialogPage#setVisible(boolean)
     */
    public void setVisible( boolean visible )
    {
        super.setVisible( visible );
        getControl( ).setFocus( );
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.wizard.WizardPage#canFlipToNextPage()
     */
    public boolean canFlipToNextPage()
    {
        // avoid repeated call to getNextPage() of base class method
        return isPageComplete();
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.wizard.WizardPage#getNextPage()
     */
    public IWizardPage getNextPage()
    {
        ProfileSelection profileSelection = collectProfileSelection();
        return getMyWizard().getSelectedProfileStartingPage( profileSelection );
    }

    private ProfileSelectionWizard getMyWizard()
    {
        return (ProfileSelectionWizard) getWizard();
    }
    
    /**
     * For use by internal packages only.
     */
    public void setDesignNameValidator( DesignNameValidatorBase validator )
    {
        getPageHelper().setDesignNameValidator( validator );
    }
    
}
