/*
 *************************************************************************
 * Copyright (c) 2006, 2014 Actuate Corporation.
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
 * A section of the ODA runtime driver plug-in template wizard that generates a new 
 * ODA runtime driver plug-in.
 * It is intended for use as part of the new plug-in project wizard.
 */
class RuntimeTemplateSection extends OdaTemplateSection
{
    static final String OPTION_NUM_CONN_PROPERTIES = "numConnProperties";  //$NON-NLS-1$
    static final String OPTION_NUM_QUERY_PROPERTIES = "numQueryProperties";  //$NON-NLS-1$

    
    RuntimeTemplateSection( NewPluginTemplateWizard wiz )
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
        // required template options
        addRequiredOption( KEY_PACKAGE_NAME, 
                Messages.baseSection_optLabel_packageName );
        addRequiredOption( OPTION_RUNTIME_DATA_SOURCE_ID, 
                Messages.runtimeSection_optLabel_odaDataSourceId );
        
        // optional template options
        addOptionalOption( OPTION_DATASOURCE_DISPLAY_NAME, 
                Messages.baseSection_optLabel_dataSourceName );        
        addOptionalOption( OPTION_NUM_CONN_PROPERTIES, 
                Messages.runtimeSection_optLabel_numSourceProps );
        addOptionalOption( OPTION_DATASET_DISPLAY_NAME, 
                Messages.baseSection_optLabel_dataSetName );       
        addOptionalOption( OPTION_NUM_QUERY_PROPERTIES, 
                Messages.runtimeSection_optLabel_numSetProps );
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
        initializeOption( KEY_PACKAGE_NAME, getDefaultPackageName() );
        initializeOption( OPTION_RUNTIME_DATA_SOURCE_ID, getDefaultDataSourceId() );
        
        initializeOption( OPTION_DATASOURCE_DISPLAY_NAME, getDefaultDataSourceName() );
        initializeOption( OPTION_NUM_CONN_PROPERTIES, "2" );   //$NON-NLS-1$       
        initializeOption( OPTION_DATASET_DISPLAY_NAME, getDefaultDataSetName() );
        initializeOption( OPTION_NUM_QUERY_PROPERTIES, "0" );   //$NON-NLS-1$
    }
 
    /**
     * Formats and returns the initialized value of the ODA data source element id.
     */
    private String getDefaultDataSourceId()
    {
        return getNewPluginId().toLowerCase( IDENTIFIER_LOCALE );
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.pde.ui.templates.OptionTemplateSection#getSectionId()
     */
    public String getSectionId()
    {
        return "runtimeDriver";  //$NON-NLS-1$
    }
 
    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.template.internal.ui.OdaTemplateSection#getWizardPageTitle()
     */
    protected String getWizardPageTitle()
    {
        return Messages.runtimeSection_wizardPageTitle;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.template.internal.ui.OdaTemplateSection#updateOdaPluginModel(org.eclipse.pde.core.plugin.IPluginModelBase)
     */
    protected void updateOdaPluginModel( IPluginModelBase odaModel )
        throws CoreException
    {
        // calls inherited method to create extension
        IPluginExtension runtimeExtension = 
                createExtension( OdaPluginModeler.ODA_RUNTIME_EXT_PT, true );
        
        OdaPluginModeler modeler = new OdaPluginModeler( this );
        modeler.updateRuntimeModel( odaModel, runtimeExtension );    

        IPluginExtension profileExtension = 
            createExtension( OdaPluginModeler.CONNECTIVITY_PROFILE_EXT_PT, true );
        modeler.updateConnProfileRuntimeModel( odaModel, profileExtension );    
}

    /* (non-Javadoc)
     * @see org.eclipse.pde.ui.templates.AbstractTemplateSection#generateFiles(org.eclipse.core.runtime.IProgressMonitor)
     */
    protected void generateFiles( IProgressMonitor monitor )
            throws CoreException
    {
        // adjust the model's manifest headers to work around Bugzilla #172744
        OdaPluginModeler modeler = new OdaPluginModeler( this );
        modeler.adjustManifestHeaders( getExecutingModel(), true );
        
        super.generateFiles( monitor );
    }
    
    /*
     * (non-Javadoc)
     * @see org.eclipse.pde.ui.templates.AbstractTemplateSection#getDependencies(java.lang.String)
     */
    public IPluginReference[] getDependencies( String schemaVersion )
    {
        Version version320 = new Version( 3, 2, 0 );
        Version version340 = new Version( 3, 4, 0 );
        Version version400 = new Version( 4, 0, 0 );
        VersionRange compatible320 = new VersionRange( version320, true, version400, false );       
        VersionRange compatible340 = new VersionRange( version340, true, version400, false );       
        
        return new IPluginReference[] 
                    {   
                      new PluginReference( 
                        "org.eclipse.core.runtime", null, 0 ), //$NON-NLS-1$
                      new CompatiblePluginReference( 
                        "org.eclipse.datatools.connectivity.oda",  //$NON-NLS-1$
                        compatible340.toString() ),
                      new CompatiblePluginReference( 
                        "org.eclipse.datatools.connectivity.oda.profile",  //$NON-NLS-1$
                        compatible320.toString() )
                    };
    }

    /* (non-Javadoc)
     * @see org.eclipse.pde.ui.templates.ITemplateSection#getUsedExtensionPoint()
     */
    public String getUsedExtensionPoint()
    {
        return OdaPluginModeler.ODA_RUNTIME_EXT_PT;
    }
    
}
