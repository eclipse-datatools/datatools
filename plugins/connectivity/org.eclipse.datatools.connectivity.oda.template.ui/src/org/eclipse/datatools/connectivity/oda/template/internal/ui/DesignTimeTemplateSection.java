/*
 *************************************************************************
 * Copyright (c) 2006, 2009 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.template.internal.ui;

import java.util.ArrayList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.connectivity.oda.template.ui.nls.Messages;
import org.eclipse.osgi.service.resolver.VersionRange;
import org.eclipse.pde.core.plugin.IPluginExtension;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.IPluginReference;
import org.eclipse.pde.ui.IFieldData;
import org.eclipse.pde.ui.templates.NewPluginTemplateWizard;
import org.eclipse.pde.ui.templates.PluginReference;
import org.osgi.framework.Version;

/**
 * A section of the ODA designer plug-in template wizard that generates a new 
 * ODA designer plug-in.
 * It is intended for use as part of the new plug-in project wizard.
 */
class DesignTimeTemplateSection extends OdaTemplateSection
{
    static final String OPTION_RUNTIME_DRIVER_PLUGIN_ID = "odaDriverPluginId";  //$NON-NLS-1$
    static final String OPTION_RUNTIME_DATA_SET_ID = "odaDataSetId";  //$NON-NLS-1$
    static final String OPTION_RUNTIME_DRIVER_CLASS = "odaDriverClass";  //$NON-NLS-1$

    
    DesignTimeTemplateSection( NewPluginTemplateWizard wiz )
    {
        super.setTemplateWizard( wiz );
        setPageCount( 1 );
        createOptions();       
    }
    
    /**
     * Create options for the template option page.
     */
    private void createOptions() 
    {
        // required template options on first page
        addRequiredOption( KEY_PACKAGE_NAME, 
                Messages.baseSection_optLabel_packageName );
        addRequiredOption( OPTION_RUNTIME_DRIVER_PLUGIN_ID, 
                Messages.designSection_optLabel_driverPluginId );
        addRequiredOption( OPTION_RUNTIME_DATA_SOURCE_ID, 
                Messages.designSection_optLabel_odaDataSourceId );
        addRequiredOption( OPTION_RUNTIME_DRIVER_CLASS, 
                Messages.designSection_optLabel_driverClass );
        addRequiredOption( OPTION_RUNTIME_DATA_SET_ID, 
                Messages.designSection_optLabel_odaDataSetId );
        addRequiredOption( OPTION_DATASOURCE_DISPLAY_NAME, 
                Messages.baseSection_optLabel_dataSourceName );
        addRequiredOption( OPTION_DATASET_DISPLAY_NAME, 
                Messages.baseSection_optLabel_dataSetName );
    }
    
    /*
     * (non-Javadoc)
     * @see org.eclipse.pde.ui.templates.BaseOptionTemplateSection#initializeFields(org.eclipse.pde.ui.IFieldData)
     */
    protected void initializeFields( IFieldData data )   
    {
        // lazy initialization of page options before it is made visible
        super.initializeFields( data );
        initializeOptions();
    }
    
    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.template.internal.ui.OdaTemplateSection#initializeFields(org.eclipse.pde.core.plugin.IPluginModelBase)
     */
    public void initializeFields( IPluginModelBase aModel )
    {
        super.initializeFields( aModel );
        initializeOptions();
    }
    
    private void initializeOptions()
    {
        initializeOption( KEY_PACKAGE_NAME, super.getDefaultPackageName() );
        initializeOption( OPTION_RUNTIME_DRIVER_PLUGIN_ID, getDefaultRuntimePluginId() );
        initializeOption( OPTION_RUNTIME_DATA_SOURCE_ID, getDefaultDataSourceId() );
        initializeOption( OPTION_RUNTIME_DRIVER_CLASS, getDefaultDriverClassName() );
        initializeOption( OPTION_RUNTIME_DATA_SET_ID, getDefaultDataSetId() );
        initializeOption( OPTION_DATASOURCE_DISPLAY_NAME, getDefaultDataSourceName() );
        initializeOption( OPTION_DATASET_DISPLAY_NAME, getDefaultDataSetName() );
    }
    
    /**
     * Formats and returns the initialized value of the runtime plugin id option.
     */
    private String getDefaultRuntimePluginId()
    {
        String pluginId = getNewPluginId(); // designer plugin id
        String uiSuffix = ".ui"; //$NON-NLS-1$

        // strip out ui suffix if exists
        if( pluginId.endsWith( uiSuffix ) )     
            return pluginId.substring( 0, pluginId.length() - uiSuffix.length() );
        return "com.custom.oda.data.runtime"; //$NON-NLS-1$
    }
    
    /**
     * Formats and returns the initialized value of the ODA data source element id.
     */
    private String getDefaultDataSourceId()
    {
        return getDefaultRuntimePluginId().toLowerCase( IDENTIFIER_LOCALE );
    }
    
    /**
     * Formats and returns the initialized value of the ODA data set element id.
     */
    private String getDefaultDataSetId()
    {
        String dataSourceId = getDefaultDataSourceId();
        if( dataSourceId.length() > 0 )
            dataSourceId += "."; //$NON-NLS-1$
        return dataSourceId + "dataSet"; //$NON-NLS-1$
    }
    
    /**
     * Formats and returns the initialized value of the ODA runtime driver class name.
     */
    private String getDefaultDriverClassName()
    {
        return getDefaultDataSourceId() + ".impl.Driver";  //$NON-NLS-1$
    }
    
    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.template.internal.ui.OdaTemplateSection#getAdditionalJavaIdentifierOptions()
     */
    protected String[] getAdditionalJavaIdentifierOptions()
    {
        return new String[] { OPTION_RUNTIME_DRIVER_CLASS };
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.pde.ui.templates.OptionTemplateSection#getSectionId()
     */
    public String getSectionId()
    {
        return "designer";  //$NON-NLS-1$
    }
    
    protected String getWizardPageTitle()
    {
        return Messages.designSection_wizardPageTitle;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.template.internal.ui.OdaTemplateSection#updateOdaPluginModel()
     */
    protected void updateOdaPluginModel( IPluginModelBase odaModel ) throws CoreException
    {
        // calls inherited method to create extension
        IPluginExtension profileExtension = 
            createExtension( OdaPluginModeler.CONNECTIVITY_PROFILE_EXT_PT, true );
        IPluginExtension profileImageExtension = 
            createExtension( OdaPluginModeler.CONNECTIVITY_PROFILE_IMAGE_EXT_PT, true );
        IPluginExtension profilePageExtension = 
            createExtension( OdaPluginModeler.CONNECTIVITY_PROFILE_PAGE_EXT_PT, true );

        OdaPluginModeler modeler = new OdaPluginModeler( this );
        modeler.updateConnProfileDesignerModel( odaModel, 
                profileExtension, profileImageExtension, profilePageExtension );    
        
        IPluginExtension designerExtension = 
                createExtension( OdaPluginModeler.ODA_DESIGN_TIME_EXT_PT, true );        
        modeler.updateDesignerModel( odaModel, designerExtension );    
    }

    /* (non-Javadoc)
     * @see org.eclipse.pde.ui.templates.AbstractTemplateSection#generateFiles(org.eclipse.core.runtime.IProgressMonitor)
     */
    protected void generateFiles( IProgressMonitor monitor )
            throws CoreException
    {
        // adjust the model's manifest headers to work around Bugzilla #172744
        OdaPluginModeler modeler = new OdaPluginModeler( this );
        modeler.adjustManifestHeaders( getExecutingModel(), false );
        
        super.generateFiles( monitor );
    }
    
    /*
     * (non-Javadoc)
     * @see org.eclipse.pde.ui.templates.AbstractTemplateSection#getDependencies(java.lang.String)
     */
    public IPluginReference[] getDependencies( String schemaVersion )
    {
        String odaRuntimePluginId = getStringOption( OPTION_RUNTIME_DRIVER_PLUGIN_ID );
        // in case this is called before initializeFields
        if( odaRuntimePluginId == null )
            odaRuntimePluginId = getDefaultRuntimePluginId();
        
        Version version320 = new Version( 3, 2, 0 );
        Version version400 = new Version( 4, 0, 0 );
        VersionRange compatible320 = new VersionRange( version320, true, version400, false );       

        return new IPluginReference[] 
                    { // adopts similar plugin sequence as PDE UI base template wizard
                      new PluginReference(
                        "org.eclipse.ui", null, 0 ), //$NON-NLS-1$
                      new PluginReference( 
                        "org.eclipse.core.runtime", null, 0 ), //$NON-NLS-1$
                      new CompatiblePluginReference( 
                        "org.eclipse.datatools.connectivity.oda.design.ui",  //$NON-NLS-1$
                        compatible320.toString() ),
                      new CompatiblePluginReference( 
                        odaRuntimePluginId,
                        null ) };
    }

    /* (non-Javadoc)
     * @see org.eclipse.pde.ui.templates.ITemplateSection#getUsedExtensionPoint()
     */
    public String getUsedExtensionPoint()
    {
        return OdaPluginModeler.ODA_DESIGN_TIME_EXT_PT;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.template.internal.ui.OdaTemplateSection#getNewFiles()
     */
    public String[] getNewFiles()
    {
        // merge the inherited list with own file(s)
        String[] baseBuildList = super.getNewFiles();
        ArrayList buildList = new ArrayList( baseBuildList.length + 1 );
        for( int i = 0; i < baseBuildList.length; i++ )
        {
            buildList.add( baseBuildList[i] );
        }
        
        // add an entry for the designer icons
        buildList.add( "icons/" ); //$NON-NLS-1$
        
        return (String[]) buildList.toArray( new String[ buildList.size() ] );
    }

}
