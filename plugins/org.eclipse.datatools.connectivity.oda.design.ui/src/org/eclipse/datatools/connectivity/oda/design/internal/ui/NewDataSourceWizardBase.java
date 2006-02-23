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

import java.io.File;
import java.lang.reflect.Constructor;
import java.text.MessageFormat;
import java.util.Properties;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.internal.ProfileWizardProvider;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataSourceDesign;
import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.ui.designsession.DesignSessionUtil;
import org.eclipse.datatools.connectivity.oda.design.ui.manifest.DataSourceWizardInfo;
import org.eclipse.datatools.connectivity.oda.design.ui.manifest.UIExtensionManifest;
import org.eclipse.datatools.connectivity.oda.design.ui.manifest.UIManifestExplorer;
import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceWizardPage;
import org.eclipse.datatools.connectivity.ui.wizards.NewConnectionProfileWizard;
import org.eclipse.jface.wizard.IWizardPage;
import org.osgi.framework.Bundle;

/**
 * The ODA data source wizard base class implementation that 
 * looks up and adds customized wizard page to create an 
 * extended ODA connection profile instance.
 * <p>This wizard is part of the ODA Designer UI framework that 
 * extends the DTP connection profile wizard framework.
 * It may also be launched from the DTP Data Source Explorer.
 */
public class NewDataSourceWizardBase extends NewConnectionProfileWizard
    implements IExecutableExtension
{
    private String m_odaDataSourceId;
    private String m_odaDesignerPluginId;
    private Properties m_profileProps;
    private DataSourceWizardPage m_startPage;
    private UIExtensionManifest m_manifest;

    private boolean m_isInDesignSession = false;
    private DataSourceDesign m_dataSourceDesign;
    private LinkedProfile m_linkedProfile;

    public NewDataSourceWizardBase()
    {
        // create and initialize common properties for the new profile
        m_profileProps = new Properties();
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.core.runtime.IExecutableExtension#setInitializationData(org.eclipse.core.runtime.IConfigurationElement, java.lang.String, java.lang.Object)
     * Gets called after a new object is instantiated.
     */    
    public void setInitializationData( IConfigurationElement newWizardElement, String classAttribute, Object data ) 
        throws CoreException
    {
        // ODA profiles use the odaDataSourceId as its profile identifier
        // which uniquely identifies an ODA run-time data source extension
        m_odaDataSourceId = 
            newWizardElement.getAttribute( ProfileWizardProvider.ATTR_PROFILE );
        if( m_odaDataSourceId == null || m_odaDataSourceId.length() == 0 )
            throw new CoreException( 
                    new Status( IStatus.ERROR, 
                            newWizardElement.getNamespace(), 
                            IStatus.OK,
                    "Missing ODA Data Source ID in the Profile attribute.", null ));
        
        initProviderID( m_odaDataSourceId );               

        // the plugin that implements the custom wizard page
        m_odaDesignerPluginId = newWizardElement.getNamespace();

        // find the oda.design.ui.dataSource extension manifest content
        try
        {
            m_manifest = 
                UIManifestExplorer.getInstance().getExtensionManifest( 
                        m_odaDataSourceId );
        }
        catch( OdaException ex )
        {
            throw new CoreException( 
                    new Status( IStatus.ERROR, 
                            m_odaDesignerPluginId, 
                            IStatus.OK,
                    "Invalid manifest content in the oda.design.ui.dataSource extension.", ex ));
        }
        
        // set wizard behavior as defined by extension
        DataSourceWizardInfo wizardInfo = 
                m_manifest.getDataSourceWizardInfo();
        assert( wizardInfo != null );
        
        // progress monitor
        setNeedsProgressMonitor( wizardInfo.includesProgressMonitor() );

        // custom data source wizard window title
        String windowTitle = wizardInfo.getWindowTitle();
        // use newWizard extension's name attribute as default 
        if( windowTitle == null || windowTitle.length() == 0 )
            windowTitle = newWizardElement.getAttribute( ProfileWizardProvider.ATTR_NAME );
        setWindowTitle( windowTitle );        
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.ui.wizards.NewConnectionProfileWizard#addCustomPages()
     */
    public void addCustomPages()
    {
        if( m_startPage != null )
            return;     // already added
        
        // get page attributes from ODA wizard page's extension element
        DataSourceWizardInfo wizardInfo = 
            m_manifest.getDataSourceWizardInfo();
        assert( wizardInfo != null );
        String wizardPageClassName = wizardInfo.getPageClassName();
        String pageTitle = wizardInfo.getPageTitle();
        
        m_startPage = createWizardPage( wizardPageClassName, pageTitle );
        addPage( m_startPage );
    }

    protected DataSourceWizardPage createWizardPage( String wizardPageClassName,
                                                    String pageTitle )
    {
        Object pageInstance = null;
        try
        {
            Bundle bundle = Platform.getBundle( m_odaDesignerPluginId );
            Class wizardPageClass = bundle.loadClass( wizardPageClassName );

            // looks for custom wizard page constructor 
            // with a single pageName argument,
            // extending from DataSourceWizardPage base class
            Class argTypes[] = new Class[1];
            argTypes[0] = String.class;
            Constructor ct = wizardPageClass.getConstructor( argTypes );

            // instantiate custom wizard page with class name as page name
            Object argList[] = new Object[1];
            argList[0] = wizardPageClassName;   
            pageInstance = ct.newInstance( argList );
        }
        catch( Exception ex )
        {
            // TODO - localization
            String messageText = "Unable to find or create custom wizard page ({0}).";
            throw new RuntimeException( MessageFormat.format( messageText, new Object[]{ wizardPageClassName } ), 
                                        ex );
        }
        
        if( ! ( pageInstance instanceof DataSourceWizardPage ))
        {
            // TODO - localization
            String messageText = "Invalid wizard page ({0}) implementation. An ODA custom wizard page must extend from {1}.";
            throw new RuntimeException( MessageFormat.format( messageText, 
                    new Object[]{ wizardPageClassName,
                                  DataSourceWizardPage.class.getName() } )); 
        }
        
        // a valid wizard page, subclass from DataSourceWizardPage
        if( pageTitle != null )
            (( DataSourceWizardPage ) pageInstance ).setTitle( pageTitle );
        return ( DataSourceWizardPage ) pageInstance;

    }
    
    /**
     * Returns the customized starting page provided by 
     * an ODA wizard page's extension.
     * This method should be called only after IWizard#addPages() is done;
     * otherwise, returns null.
     */
    public IWizardPage getCustomStartingPage()
    {
        return getCustomWizardPage();
    }
   
    /**
     * This method should be called only after IWizard#addPages() is done.
     */
    protected DataSourceWizardPage getCustomWizardPage()
    {
        assert( m_startPage != null );
        return m_startPage;
    }
    
    /**
     * Initializes the wizard and its custom page with given properties.
     * This method may be called only after IWizard#addPages()is done.
     * @param props     a collection of properties in name value pairs
     */
    public void setInitialProperties( Properties props )
    {
        m_profileProps = props;
        getCustomWizardPage().setInitialProperties( m_profileProps );
        m_linkedProfile = null;     // reset
    }

    public void setLinkedProfile( String profileName, File storageFile )
    {
        m_linkedProfile = 
            new LinkedProfile( profileName, storageFile );
    }
 
    private boolean hasLinkToProfile()
    {
        return ( m_linkedProfile != null && 
                 m_linkedProfile.hasLinkAttributes() );
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.ui.wizards.NewConnectionProfileWizard#getProfileProperties()
     */
    public Properties getProfileProperties()
    {
        assert( m_profileProps != null );
        m_profileProps = collectCustomProperties();
        return m_profileProps;
    }

    /*
     * Returns the custom properties provided by user
     * in the customized wizard page.
     * This method does not change the state of the wizard instance.
     */
    protected Properties collectCustomProperties()
    {
        if( m_startPage != null )
            return m_startPage.collectCustomProperties();

        return m_profileProps;    // use own cached properties
    }

    /**
     * Returns the ODA data source element id that 
     * uniquely identifies the ODA run-time data source extension
     * of the ODA driver's custom designer plug-in.
     */
    public String getOdaDataSourceId()
    {
        return m_odaDataSourceId;
    }

    /**
     * Returns the ODA driver's custom designer plug-in id that 
     * implements the DTP oda.design.ui.dataSource extension point.
     */
    public String getOdaDesignerId()
    {
        return m_odaDesignerPluginId;
    }

    /**
     * Indicates whether the wizard is in the midst of a 
     * ODA design session to create a new data source definition.
     * @return true if in an ODA design session; 
     *         false otherwise, such as started by the Data Source Explorer.
     */
    public boolean isInOdaDesignSession()
    {
        return m_isInDesignSession;
    }

    /**
     * For internal use only.
     */
    public void setInOdaDesignSession( boolean value )
    {
        m_isInDesignSession = value;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.ui.wizards.NewConnectionProfileWizard#performFinish()
     */
    public boolean performFinish()
    {
        // creates a new connection profile 
        // only if wizard was started by DSE        
        if( ! isInOdaDesignSession() )
            return super.performFinish();    // done
        
        // is in an ODA design session, create new ODA design instead
        try
        {
            finishDataSourceDesign();
        }
        catch( OdaException e )
        {
            // TODO error message
            return false;
        }
        
        return true;
    }

    /**
     * Performs finish on the design session with
     * a new data source design, which contains 
     * the data source connection properties and values
     * collected in the wizard pages.
     * <br>Resets the design session state.
     * @throws OdaException
     */
    public DataSourceDesign finishDataSourceDesign()
        throws OdaException
    {
        if( ! isInOdaDesignSession() )
            throw new OdaException( "Not in design session" );

        m_dataSourceDesign = createDataSourceDesign();
        
        setInOdaDesignSession( false );    // done with design session
        return m_dataSourceDesign;
    }
    
    /**
     * Returns the ODA data source design created on Finish.
     * Applicable only if the data source was defined within
     * an ODA design session.
     * @return 
     */
    public DataSourceDesign getDataSourceDesign()
    {
        if( ! isInOdaDesignSession() )  // performFinish is done with design session
            return m_dataSourceDesign;  // ok to return created design
        return null;
    }
    
    /**
     * Takes the data source connection properties and values
     * collected in wizard page, and maps to a
     * new Data Source Design object.
     * @return the new Data Source Design object
     */
    private DataSourceDesign createDataSourceDesign()
        throws OdaException
    {
        DataSourceDesign newDesign = DesignFactory.eINSTANCE.createDataSourceDesign();
    
        // assigns profile instance info to data source design
        
        newDesign.setOdaExtensionId( getOdaDataSourceId() );
            // no need to explicitly set OdaExtensionDataSourceId
        try
        {
            newDesign.setName( getProfileName() );
            newDesign.setDisplayName( getProfileDescription() );
        }
        catch( RuntimeException e ) 
        {
            // in case wizard profile page is not available
            // TODO log error and ignore
            newDesign.setName( getOdaDataSourceId() );
        }
        
        newDesign.setPublicProperties(
                DesignSessionUtil.createDataSourcePublicProperties( 
                            getOdaDataSourceId(),
                            getProfileProperties() ));
    
        // adds attributes of linked profile, if specified,
        // to the data source design
        if( hasLinkToProfile() )
        {
            assert( m_linkedProfile != null );
            newDesign.setLinkedProfileName( m_linkedProfile.getProfileName() );
            newDesign.setLinkedProfileStoreFile( m_linkedProfile.getStorageFile() );
        }
        
        // let subclass implementation further specifies the data source design
        return getCustomWizardPage().collectDataSourceDesign( newDesign );
    }
    
    /**
     * Nested internal class for managing the connection profile
     * attributes that may be linked to a new data source design.
     */
    private class LinkedProfile
    {
        private String m_profileName;
        private File m_storageFile;
        
        public LinkedProfile( String profileName, File storageFile )
        {
            m_profileName = profileName;
            m_storageFile = storageFile;
        }

        /**
         * Indicates whether essential profile attribute exists
         * to serve as a valid link.
         * @return
         */
        public boolean hasLinkAttributes()
        {
            return ( m_profileName != null && 
                     m_profileName.length() > 0 );
        }
        
        /**
         * @return Returns the m_profileName.
         */
        public String getProfileName()
        {
            return m_profileName;
        }

        /**
         * @return Returns the m_storageFile.
         */
        public File getStorageFile()
        {
            return m_storageFile;
        }                
    }
    
}
