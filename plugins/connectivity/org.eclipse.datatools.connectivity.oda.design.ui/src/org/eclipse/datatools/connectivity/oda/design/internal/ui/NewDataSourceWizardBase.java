/*
 *************************************************************************
 * Copyright (c) 2006, 2013 Actuate Corporation.
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
import java.util.Properties;
import java.util.logging.Level;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.internal.ui.wizards.ProfileWizardProvider;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataSourceDesign;
import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.DesignSessionRequest;
import org.eclipse.datatools.connectivity.oda.design.DesignerState;
import org.eclipse.datatools.connectivity.oda.design.ResourceIdentifiers;
import org.eclipse.datatools.connectivity.oda.design.internal.designsession.DataSourceDesignSessionBase.ProfileReferenceBase;
import org.eclipse.datatools.connectivity.oda.design.internal.designsession.DesignerLogger;
import org.eclipse.datatools.connectivity.oda.design.ui.designsession.DesignSessionUtil;
import org.eclipse.datatools.connectivity.oda.design.ui.manifest.DataSourceWizardInfo;
import org.eclipse.datatools.connectivity.oda.design.ui.manifest.UIExtensionManifest;
import org.eclipse.datatools.connectivity.oda.design.ui.manifest.UIManifestExplorer;
import org.eclipse.datatools.connectivity.oda.design.ui.nls.Messages;
import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceWizardPage;
import org.eclipse.datatools.connectivity.oda.profile.OdaProfileExplorer;
import org.eclipse.datatools.connectivity.oda.profile.internal.OdaConnectionProfile;
import org.eclipse.datatools.connectivity.oda.util.manifest.ConnectionProfileProperty;
import org.eclipse.datatools.connectivity.ui.wizards.NewConnectionProfileWizard;
import org.eclipse.datatools.connectivity.ui.wizards.NewConnectionProfileWizardPage;
import org.eclipse.jface.wizard.IWizardPage;

/**
 * The internal ODA data source wizard base class implementation that 
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

    // create and initialize common properties for the new profile
    private Properties m_profileProps = new Properties();

    private DataSourceWizardPage m_startPage;
    private UIExtensionManifest m_manifest;

    private boolean m_isInDesignSession = false;
    private DesignSessionRequest m_sessionRequest;
    private boolean m_hasPerformedFinish = false;
    private DataSourceDesign m_responseDataSourceDesign;
    private DesignerState m_responseDesignerState;
    private LinkedProfile m_linkedProfile;

    private static final String ODA_UI_EXT_PT = 
    	"org.eclipse.datatools.connectivity.oda.design.ui.dataSource"; //$NON-NLS-1$

    // logging variable
    private static final String sm_className = NewDataSourceWizardBase.class.getName();
    
    protected NewDataSourceWizardBase( String odaDataSourceId )
        throws OdaException
    {
        initialize( odaDataSourceId, null, 
                    Messages.wizard_dataSource_defaultTitle );
    }

    protected NewDataSourceWizardBase()
    {
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
        String odaDataSourceId = 
            newWizardElement.getAttribute( ProfileWizardProvider.ATTR_PROFILE );
        if( odaDataSourceId == null || odaDataSourceId.length() == 0 )
            throw new CoreException( 
                    new Status( IStatus.ERROR, 
                            newWizardElement.getNamespaceIdentifier(), 
                            IStatus.OK,
                            Messages.wizard_missingDataSourceId, 
                            null ) );

        // the oda ui plugin extension that implements the custom wizard
        String odaDesignerPluginId = newWizardElement.getNamespaceIdentifier();

        // use newWizard extension's name attribute as default title
        String wizardName = 
            newWizardElement.getAttribute( ProfileWizardProvider.ATTR_NAME );
        
        try
        {
            initialize( odaDataSourceId, odaDesignerPluginId, wizardName );
        }
        catch( OdaException ex )
        {
            throw new CoreException( 
                    new Status( IStatus.ERROR, 
                            odaDesignerPluginId, 
                            IStatus.OK,
                            Messages.wizard_invalidManifest, 
                            ex ));
        }
    }
    
    /**
     * Initialize the wizard's context.
     * @param odaDataSourceId   ODA runtime data source element id
     * @param odaDesignerId     ODA design-time extension's plugin namespace
     * @param defaultWizardTitle    default window title to use
     *            if none is specified in newDataSourceWizard element in
     *            oda.design.ui.dataSource extension
     * @throws OdaException
     */
    protected void initialize( String odaDataSourceId, 
                                String odaDesignerId,
                                String defaultWizardTitle )
        throws OdaException
    {
        m_odaDataSourceId = odaDataSourceId;        
        initProviderID( m_odaDataSourceId );               
        
        // find the oda.design.ui.dataSource extension manifest content
        m_manifest = 
                UIManifestExplorer.getInstance().getExtensionManifest( 
                        m_odaDataSourceId );
        if( m_manifest == null )
        {
            String errorMessage = Messages.bind( Messages.extension_missingManifestElement, ODA_UI_EXT_PT );
            OdaException odaEx = new OdaException( errorMessage );
            odaEx.initCause( new IllegalArgumentException( m_odaDataSourceId ) );
            throw odaEx;
        }

        m_odaDesignerPluginId = 
            ( odaDesignerId != null && odaDesignerId.length() > 0 ) ?
                odaDesignerId : m_manifest.getNamespace();
       
        // set wizard behavior as defined by extension
        DataSourceWizardInfo wizardInfo = 
                m_manifest.getDataSourceWizardInfo();
        assert( wizardInfo != null );
        
        // progress monitor
        setNeedsProgressMonitor( wizardInfo.includesProgressMonitor() );

        // custom data source wizard window title
        String windowTitle = wizardInfo.getWindowTitle();
        if( windowTitle == null || windowTitle.length() == 0 )
            windowTitle = defaultWizardTitle;
        setWindowTitle( windowTitle );        
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.ui.wizards.NewConnectionProfileWizard#addPages()
     */
    public void addPages()
    {
        if( getPageCount() > 0 )
            return;     // has already added pages; done
        
        super.addPages();
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
        // instantiate using a custom constructor 
        // with a single pageName argument,
        // extending from DataSourceWizardPage base class;
        // use class name as page name
        Object pageInstance = 
            DesignerUtil.createInstanceWithStringArg( 
                    m_odaDesignerPluginId,
                    wizardPageClassName, wizardPageClassName );
        
        if( ! ( pageInstance instanceof DataSourceWizardPage ))
        {
            throw new RuntimeException( 
                    Messages.bind( Messages.extension_mustInheritFromODAPage, 
                                    wizardPageClassName,
                                    DataSourceWizardPage.class.getName() )); 
        }
        
        // a valid wizard page, subclass from DataSourceWizardPage;
        // overrides page title, if specified
        if( pageTitle != null && pageTitle.length() > 0 )
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
     * For internal use only; not an API method.
     */
    public void setProfilePageProperties( String profileName, String profileDesc, 
            Boolean autoConnect, Boolean skipProfilePage )
    {
        // override page properties only if a non-null argument value is specified
        if( profileName != null )
            setProfileName( profileName );
        if( profileDesc != null )
            setProfileDescription( profileDesc );
        if( autoConnect != null )
        {
            if( getProfileNamePage() != null )
                getProfileNamePage().setAutoConnect( autoConnect.booleanValue() );
        }
        
        if( skipProfilePage != null )
            setSkipProfileNamePage( skipProfilePage.booleanValue() );
    }
    
    /**
     * For internal use only; not a public API method.
     */
    public boolean hasProfileNamePage()
    {
        return getProfileNamePage() != null; 
    }
    
    protected NewConnectionProfileWizardPage getProfileNamePage()
    {
        return getProfilePage();
    }
    
    /**
     * Refresh the wizard and its custom page with given properties, 
     * if it has any entries.
     * Otherwise, keep wizard's existing properties.
     * Refresh the custom page with the latest state.
     * @param dataSourceProps
	 * @since 3.0.4
     */
    public void refreshPropertiesIfExist( Properties dataSourceProps )
    {
        if( dataSourceProps != null && ! dataSourceProps.isEmpty() )
        {
            setInitialProperties( dataSourceProps );
        } 
        
        if( getCustomWizardPage() != null )
            getCustomWizardPage().refresh();
    }
    
    /**
     * Initializes the wizard and its custom page with given properties.
     * This method may be called only after IWizard#addPages()is done.
     * @param props     a collection of properties in name value pairs
     */
    private void setInitialProperties( Properties props )
    {
        m_profileProps = props;
        if( getCustomWizardPage() != null )
            getCustomWizardPage().setInitialProperties( m_profileProps );
    }

    public void setLinkedProfile( ProfileReferenceBase profileRef )
    {
        m_linkedProfile = new LinkedProfile( profileRef );
    }

    public void unsetLinkedProfile()
    {
        m_linkedProfile = null;        
    }
 
    protected boolean hasLinkToProfile()
    {
        return ( m_linkedProfile != null && 
                 m_linkedProfile.hasLinkAttributes() );
    }
    
    protected IConnectionProfile getLinkedProfile()
    {
        return hasLinkToProfile() ? m_linkedProfile.getProfileInstance() : null;
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
    
    private static boolean hasLinkedProfileProperties( Properties customProperties )
    {
        if( customProperties.containsKey( ConnectionProfileProperty.PROFILE_NAME_PROP_KEY ) )
            return true;
        
        return customProperties.containsKey( ConnectionProfileProperty.PROFILE_STORE_FILE_PATH_PROP_KEY );
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
     * For internal use only.
     * Indicates whether this wizard is valid and can use its pages to create a new
     * data source design of the specified oda data source type, and 
     * optionally based on reference to an existing ODA connection profile instance.
     * @param odaDataSourceId   oda data source extension id
     * @param profileRef    reference to the profile instance from which to create a new data source design;
     *                          optional, may be null
     * @return  true if this wizard can be re-used; false otherwise
     * @since DTP 1.11
     */
    public boolean isValid( String odaDataSourceId, ProfileReferenceBase profileRef )
    {
        IConnectionProfile odaProfile = ( profileRef == null ) ? null :
                                profileRef.getProfileInstance();
        if( odaProfile == null || odaProfile instanceof OdaConnectionProfile )
            return isValid( odaDataSourceId, (OdaConnectionProfile)odaProfile );
        return false;   // expects null or an OdaConnectionProfile
    }

    /**
     * Indicates whether this wizard is valid and can use its pages to create a new
     * data source design of the specified oda data source type and in reference to 
     * an existing ODA connection profile instance.
     * @param odaDataSourceId   oda data source extension id
     * @param odaProfile        the profile instance from which to create a new data source design;
     *                          optional, may be null
     * @since DTP 1.6
     */
    public boolean isValid( String odaDataSourceId, OdaConnectionProfile odaProfile )
    {
        // base class assumes that a wizard can handle only one type of oda data source,
        // and any of its existing profiles; subclass may override
        if( odaProfile != null && ! odaProfile.getProviderId().equalsIgnoreCase( odaDataSourceId ))
            return false;
        return getOdaDataSourceId().equals( odaDataSourceId );
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
    void setInOdaDesignSession( boolean value )
    {
        m_isInDesignSession = value;
    }

    /**
     * For internal use only.
     */
    public void initOdaDesignSession( ProfileReferenceBase newProfileRef, DesignSessionRequest sessionRequest )
    {
        m_hasPerformedFinish = false;
        m_sessionRequest = sessionRequest;  // may be null
        setInOdaDesignSession( true );
        
        // reset any previously linked profile
        unsetLinkedProfile();
        
        if( newProfileRef != null && newProfileRef.maintainExternalLink() )
            setLinkedProfile( newProfileRef );        
    }
    
    /**
     * Returns the resource identifiers of the ODA consumer application, if available.
     * @return  a ResourceIdentifiers instance; may be null if none is specified
     */
    ResourceIdentifiers getHostResourceIdentifiers()
    {
        if( m_sessionRequest == null )
            return null;
        
        DataSourceDesign dataSourceDesign = m_sessionRequest.getDataSourceDesign();
        return ( dataSourceDesign != null ) ? 
                dataSourceDesign.getHostResourceIdentifiers() : null;
    }
    
    /**
     * Indicates whether the data source properties may be edited by
     * the wizard's custom page in the current design session.  
     * It takes into account whether an external connection profile 
     * reference is maintained; in which case, any user edits on a
     * custom page is ignored anyway, and thus the properties are not
     * considered editable.
     * It provides initialization data for the ODA wizard
     * and its custom pages to set its customized control to be read-only.
     * An extended wizard page may choose to honor or ignore such request.
     * @return  true if the data source properties may be edited by
     *          a custom page in the current design session; false otherwise
     * @since 3.0.4
     */
    boolean isSessionEditable()
    {
        // a design session to create a new data source design
        // is editable by default;
        // check if using an external connection profile reference
        return ! hasLinkToProfile();       
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.ui.wizards.NewConnectionProfileWizard#performFinish()
     */
    public boolean performFinish()
    {
        if( m_hasPerformedFinish )
            return true;    // has already completed the finish task
        
        // creates a new connection profile instance in DSE
        // only if wizard was started by DSE, and not by an ODA design session       
        if( ! isInOdaDesignSession() )
        {
            boolean state = super.performFinish();
            m_hasPerformedFinish = true;
            return state;    // done
        }
        
        // is in an ODA design session, create new ODA design instead
        try
        {
            finishDataSourceDesign();
        }
        catch( OdaException ex )
        {
            // log warning about exception
            DesignerLogger logger = DesignerLogger.getInstance( DesignerLogger.PLUGIN_LOGGER_NAME );
            logger.warning( sm_className, "performFinish",  //$NON-NLS-1$
                    "Caught exception while finishDataSourceDesign.", ex ); //$NON-NLS-1$
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
            throw new OdaException( Messages.common_notInDesignSession );

        m_responseDataSourceDesign = saveNewDataSourceDesign();        
        m_hasPerformedFinish = true;

        return m_responseDataSourceDesign;
    }
    
    /**
     * Returns the ODA data source design created on Finish.
     * Applicable only if the data source was defined within
     * an ODA design session.
     * @return 
     */
    public DataSourceDesign getDataSourceDesign()
    {
        if( m_hasPerformedFinish )      // performFinish is done
            return m_responseDataSourceDesign;  // ok to return created design
        return null;
    }
    
    /**
     * Takes the data source connection properties and values
     * collected in wizard page, and maps to a
     * new Data Source Design object.
     * @return the new Data Source Design object
     */
    private DataSourceDesign saveNewDataSourceDesign()
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
        catch( RuntimeException ex ) 
        {
            // ignore in case wizard profile page is not available
            // log warning about exception
            DesignerLogger logger = DesignerLogger.getInstance();
            logger.warning( sm_className, "saveNewDataSourceDesign",  //$NON-NLS-1$
                    "Caught exception while copying profile attributes from wizard profile page.", ex ); //$NON-NLS-1$

            newDesign.setName( getOdaDataSourceId() );
        }
        
        // assign those properties that have values collected in wizard page
        Properties customPropertyValues = getProfileProperties();
        setDataSourceDesignProperties( newDesign, customPropertyValues );
    
        /* adds linked profile properties to the design, 
         * iff a profile is specified/initialized in this wizard
         * and the custom page did not include any linked profile properties in its 
         * custom profile properties
         */
        if( hasLinkToProfile() && ! hasLinkedProfileProperties( customPropertyValues ) )
        {
            assert( m_linkedProfile != null );
            newDesign.setLinkedProfileName( m_linkedProfile.getProfileName() );
            newDesign.setLinkedProfileStoreFilePath( m_linkedProfile.getStorageFilePathPropertyValue() );
        }
        
        if( getCustomWizardPage() == null )
            return newDesign;
        
        // let custom wizard page implementation further specifies the data source design
        return getCustomWizardPage().finishDataSourceDesign( newDesign );
    }

    /**
     * Assigns the specified custom data source properties to the
     * specified data source design, based on the ODA data source extension's
     * property definition declared in its manifest.
     * @param newDesign a data source design definition
     * @param customPropertyValues  custom property name-value pairs collected
     *                              from a custom page
     * @throws OdaException
     */
    protected void setDataSourceDesignProperties( DataSourceDesign newDesign,
            Properties customPropertyValues ) 
        throws OdaException
    {
        newDesign.setPublicProperties(
                DesignSessionUtil.createDataSourcePublicProperties( 
                            getOdaDataSourceId(),
                            customPropertyValues ));
        newDesign.setPrivateProperties( 
                DesignSessionUtil.createDataSourceNonPublicProperties( 
                            getOdaDataSourceId(),
                            customPropertyValues ));
    }
    
    /**
     * Returns the custom designer state specified by 
     * an extended wizard via the corresponding setter method.
     * May return null if none is specified.
     * @return
     */
    public DesignerState getResponseDesignerState()
    {
        return m_responseDesignerState;
    }
    
    /**
     * Allows an extended wizard 
     * to optionally assign a custom designer state, for inclusion
     * in the ODA design session response.
     * @param customDesignerState   a designer state instance
     *              that preserves the current session's internal state
     *              so that it can be restored in a subsequent design session
     */
    protected void setResponseDesignerState( DesignerState customDesignerState )
    {
        m_responseDesignerState = customDesignerState;
    }

    
    /**
     * Nested internal class for managing the connection profile
     * attributes that may be linked to a new data source design.
     */
    private class LinkedProfile
    {
        private String m_profileName;
        private File m_storageFile;
        private String m_storageFilePathPropValue;

        public LinkedProfile( ProfileReferenceBase profileRef )
        {
            m_profileName = profileRef.getName();
            m_storageFile = profileRef.getStorageFile();
            m_storageFilePathPropValue = profileRef.getStorageFilePathPropertyValue();
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
        
        public String getProfileName()
        {
            return m_profileName;
        }

        public File getStorageFile()
        {
            return m_storageFile;
        }                
        
        public String getStorageFilePathPropertyValue()
        {
            return m_storageFilePathPropValue;
        }

        private IConnectionProfile getProfileInstance()
        {
            if( ! hasLinkAttributes() )
                return null;
            
            final String methodName = "getProfileInstance";  //$NON-NLS-1$
            
            IConnectionProfile profile = null;
            try
            {
                // if null profile store file is specified, the default profile store path is used
                profile = OdaProfileExplorer.getInstance()
                            .getProfileByName( getProfileName(), getStorageFile() );
            }
            catch( OdaException ex )
            {
                // log warning about exception
                DesignerLogger logger = DesignerLogger.getInstance();
                logger.warning( sm_className, methodName,
                        "Caught exception while looking up connection profile instance by name.", ex ); //$NON-NLS-1$
            }
            
            if( profile == null )
            {
                // log warning that no profile is found
                DesignerLogger logger = DesignerLogger.getInstance( DesignerLogger.PLUGIN_LOGGER_NAME );
                logger.logp( Level.WARNING, sm_className, methodName,
                        "Unable to find referenced connection profile instance." ); //$NON-NLS-1$
            }
            return profile;
        }
        
    }
    
}
