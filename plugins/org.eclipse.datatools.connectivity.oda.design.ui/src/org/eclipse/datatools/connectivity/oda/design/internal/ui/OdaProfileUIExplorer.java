/*
 *************************************************************************
 * Copyright (c) 2006 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.design.internal.ui;

import org.eclipse.birt.core.framework.FrameworkException;
import org.eclipse.birt.core.framework.IConfigurationElement;
import org.eclipse.datatools.connectivity.internal.ConnectionProfileManager;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.ui.manifest.UIManifestExplorer;
import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceEditorPage;
import org.eclipse.datatools.connectivity.oda.design.ui.wizards.NewDataSourceWizard;
import org.eclipse.datatools.connectivity.oda.profile.OdaProfileExplorer;
import org.eclipse.jface.wizard.IWizard;

/**
 * The ODA Profile Wizard Explorer is a singleton class that 
 * provides the services to access an ODA data source extension's
 * customized wizard and editor page implementation.
 * The singleton instance is retrieved 
 * using the <code>getInstance()</code> method.
 */
public class OdaProfileUIExplorer
{
    private static OdaProfileUIExplorer sm_instance = null;

    public static synchronized OdaProfileUIExplorer getInstance()
    {
        if( sm_instance == null )
            sm_instance = new OdaProfileUIExplorer();
        return sm_instance;
    }

    private OdaProfileUIExplorer()
    {
    }
    
    /**
     * Returns the ODA custom wizard provided by an
     * ODA data source designer that implements the 
     * connection profile extension point.
     * @param profileInstanceId the instance id of a connection profile
     * @return  the ODA custom wizard, or null if no wizard is provided
     * @throws OdaException if the profile provider has not implemented 
     *                      the expected Oda wizard type
     */
    public NewDataSourceWizard getNewDataSourceWizard( String profileInstanceId )
        throws OdaException
    {
        // ODA profiles use the odaDataSourceId as its profile identifier
        String odaDataSourceId = 
            OdaProfileExplorer.getInstance().getProfile( profileInstanceId ).getProviderId();
        return getNewDataSourceWizardByType( odaDataSourceId );
    }
    
    /**
     * Returns the ODA custom wizard provided by an
     * ODA data source designer that implements the 
     * connection profile extension point.
     * @param odaDataSourceId   the ODA runtime data source element id
     * @return  the ODA custom wizard, or null if no wizard is provided
     * @throws OdaException if the profile provider has not implemented 
     *                      the expected Oda wizard type
     */
    public NewDataSourceWizard getNewDataSourceWizardByType( String odaDataSourceId )
        throws OdaException
    {
        assert( odaDataSourceId != null );
        IWizard dataSourceWizard = 
            ConnectionProfileManager.getInstance().getNewWizard( odaDataSourceId );
        
        if( dataSourceWizard == null )
            return null;    // has not implemented a new data source wizard
        
        if( dataSourceWizard instanceof NewDataSourceWizard == false )
            throw OdaProfileExplorer.newOdaException( "invalid.oda.wizard", odaDataSourceId );

        return (NewDataSourceWizard) dataSourceWizard;       
    }

    /**
     * Returns the ODA custom editor page provided by an
     * ODA data source designer that adopts the DTP DSE
     * framework and implements the 
     * org.eclipse.ui.propertyPages extension point.
     * @param odaDataSourceId   the ODA runtime data source element id
     * @return  a DataSourceEditorPage extension, or null
     *          if no match is found for the given page type 
     * @throws OdaException
     */
    public DataSourceEditorPage getDataSourceEditorPage( String odaDataSourceId )
        throws OdaException
    {
        IConfigurationElement pageElement =
            UIManifestExplorer.getInstance().getPropertyPageElement( odaDataSourceId );
        if( pageElement == null )
            return null;    // did not implement the editor property page
        
        Object propPage = null;
        try
        {
            propPage = pageElement.createExecutableExtension( "class" ); //$NON-NLS-1$
        }
        catch( FrameworkException ex )
        {
            throw new OdaException( ex );
        }

        if( ! ( propPage instanceof DataSourceEditorPage ) )
            return null;    // the implemented property page is not an ODA extended page

        String pageName = pageElement.getAttribute( "name" ); //$NON-NLS-1$
        if( pageName == null || pageName.length() == 0 )
            pageName = "Custom Data Source Properties";
        ((DataSourceEditorPage) propPage).setTitle( pageName );

        return (DataSourceEditorPage) propPage;
    }

}