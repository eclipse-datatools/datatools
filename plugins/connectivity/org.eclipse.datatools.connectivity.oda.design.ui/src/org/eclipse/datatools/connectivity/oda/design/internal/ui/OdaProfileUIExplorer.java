/*
 *************************************************************************
 * Copyright (c) 2006, 2008 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.design.internal.ui;

import java.io.File;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.internal.ui.ConnectionProfileManagerUI;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.ui.manifest.DataSetUIElement;
import org.eclipse.datatools.connectivity.oda.design.ui.manifest.DataSetWizardInfo;
import org.eclipse.datatools.connectivity.oda.design.ui.manifest.UIManifestExplorer;
import org.eclipse.datatools.connectivity.oda.design.ui.nls.Messages;
import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizard;
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
     * @deprecated  As of DTP 1.6, replaced by {@link #getNewDataSourceWizard(String, File)}
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
     * @param profileInstanceId the instance id of a connection profile
     * @param storageFile   a file that stores profile instances; 
     *                      may be null, which would use the default store 
     *                      of the Connectivity plugin
     * @return  the ODA custom wizard, or null if no wizard is provided
     * @throws OdaException if the specified profile is not found, or
     *                      if the profile provider has not implemented 
     *                      the expected ODA wizard
     * @since DTP 1.6
     */
    public NewDataSourceWizard getNewDataSourceWizard( String profileInstanceId,
                                                        File storageFile )
        throws OdaException
    {
        IConnectionProfile profile =
            OdaProfileExplorer.getInstance().getProfileById( profileInstanceId, storageFile );
        if( profile == null )
            throw new OdaException( new IllegalArgumentException( profileInstanceId ) );
        
        // ODA profiles use the odaDataSourceId as its profile identifier
        String odaDataSourceId = profile.getProviderId();
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
        IWizard dataSourceWizard = null;
        try
        {
            dataSourceWizard = ConnectionProfileManagerUI.getInstance().getNewWizard( odaDataSourceId );
        }
        catch( RuntimeException ex )
        {
            throw new OdaException( ex );
        }
        
        if( dataSourceWizard == null )
            return null;    // has not implemented a new data source wizard
        
        if( dataSourceWizard instanceof NewDataSourceWizard == false )
            throw new OdaException( 
                    Messages.bind( Messages.extension_mustInheritFromODAWizard, 
                            dataSourceWizard.getClass().getName(),
                            NewDataSourceWizard.class.getName() ));

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
        catch( CoreException ex )
        {
            throw new OdaException( ex );
        }

        if( ! ( propPage instanceof DataSourceEditorPage ) )
            return null;    // the implemented property page is not an ODA extended page

        String pageName = pageElement.getAttribute( "name" ); //$NON-NLS-1$
        if( pageName == null || pageName.length() == 0 )
            pageName = Messages.page_defaultDataSourceTitle;
        ((DataSourceEditorPage) propPage).setTitle( pageName );

        return (DataSourceEditorPage) propPage;
    }
    
    /**
     * Returns the ODA custom wizard provided by an
     * ODA data set designer that implements the 
     * oda data source ui extension point.
     * @param odaDataSourceId
     * @param dataSetElement
     * @return
     * @throws OdaException
     */
    public DataSetWizard getDataSetWizard( String odaDataSourceId,
                            DataSetUIElement dataSetElement )
        throws OdaException
    {
        DataSetWizardInfo wizardInfo = dataSetElement.getWizardInfo();
        if( wizardInfo == null )
        {
            // use default wizard base class
            return new DataSetWizard();
        }

        // instantiate specified wizard class
        Object wizardInstance = null;
        try
        {
            IConfigurationElement wizardElement =
                DataSetUIElement.getWizardElement( dataSetElement.getElement() );
            wizardInstance = wizardElement.createExecutableExtension( 
                    DataSetWizardInfo.CLASS_ATTRIBUTE );
        }
        catch( CoreException ex )
        {
            throw new OdaException( ex );
        }
        
        if( ! ( wizardInstance instanceof DataSetWizard ))
        {
            throw new OdaException( 
                    Messages.bind( Messages.extension_mustInheritFromODAWizard, 
                            wizardInfo.getClassName(),
                            DataSetWizard.class.getName() )); 
        }
        
        // a valid wizard, subclass of DataSetWizard
        String wizardTitle = wizardInfo.getWindowTitle();
        if( wizardTitle != null && wizardTitle.length() > 0 )
            (( DataSetWizard ) wizardInstance ).setWindowTitle( wizardTitle );

        return ( DataSetWizard ) wizardInstance;   
    }

}