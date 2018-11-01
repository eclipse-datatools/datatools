/*
 *************************************************************************
 * Copyright (c) 2009, 2011 Actuate Corporation.
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

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.internal.ui.wizards.ExportProfilesDialog;
import org.eclipse.datatools.connectivity.oda.design.ResourceIdentifiers;
import org.eclipse.datatools.connectivity.ui.actions.ExportProfileViewAction;
import org.eclipse.swt.widgets.Shell;

/**
 *  Internal action class to handle the dialog that creates a new profile store 
 *  for selected connection profile(s). 
 */
public class CreateProfileStoreAction extends ExportProfileViewAction
{
    private ProfileStoreCreationDialog m_dialog;
    private IConnectionProfile m_preSelectProfile;
    private ResourceIdentifiers m_resourceIdentifiers;
    
    public CreateProfileStoreAction( Shell dialogShell, ResourceIdentifiers resourceIdentifiers )
    {
        super();
        m_resourceIdentifiers = resourceIdentifiers;
        init( dialogShell );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.ui.actions.ExportProfileViewAction#createExportProfilesDialog(org.eclipse.swt.widgets.Shell)
     * @override base method
     */
    protected ExportProfilesDialog createExportProfilesDialog( Shell parentShell )
    {
        m_dialog = new ProfileStoreCreationDialog( parentShell );
        m_dialog.setPreSelectedProfile( m_preSelectProfile );
        m_dialog.setBlockOnOpen( true );
        m_dialog.setHostResourceIdentifiers( m_resourceIdentifiers );
        return m_dialog;
    }
    
    public ProfileStoreCreationDialog getProfileStoreCreationDialog()
    {
        return m_dialog;
    }
    
    /**
     * Specifies the connection profile element to pre-select by default
     * in this action's dialog.  
     * This method can be called before running this action.
     * @param profile  the profile element to pre-select in the action's dialog; 
     *                 may be null to unset before running the action
     */
    public void setPreSelectedProfile( IConnectionProfile profile )
    {
        // hold on to profile to pre-select when the dialog is created
        m_preSelectProfile = profile;
    }

}
