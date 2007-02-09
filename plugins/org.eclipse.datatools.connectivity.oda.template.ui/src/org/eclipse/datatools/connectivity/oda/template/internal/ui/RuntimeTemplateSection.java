/*
 *************************************************************************
 * Copyright (c) 2006, 2007 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.template.internal.ui;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.connectivity.oda.template.ui.nls.Messages;
import org.eclipse.pde.core.plugin.IPluginExtension;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.IPluginReference;
import org.eclipse.pde.ui.IFieldData;
import org.eclipse.pde.ui.templates.NewPluginTemplateWizard;

/**
 * A section of the ODA runtime driver plug-in template wizard that generates a new 
 * ODA runtime driver plug-in.
 * It is intended for use as part of the new plug-in project wizard.
 */
class RuntimeTemplateSection extends OdaTemplateSection
{
    static final String OPTION_NUM_CONN_PROPERTIES = "numConnProperties";  //$NON-NLS-1$
    static final String OPTION_NUM_QUERY_PROPERTIES = "numQueryProperties";  //$NON-NLS-1$

    // extension points used in the generated plug-in
    private static final String ODA_RUNTIME_EXT_PT =
        "org.eclipse.datatools.connectivity.oda.dataSource"; //$NON-NLS-1$

    
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
    public void initializeFields( IPluginModelBase model )
    {
        super.initializeFields( model );
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
                createExtension( ODA_RUNTIME_EXT_PT, true );
        
        OdaPluginModeler modeler = new OdaPluginModeler( this );
        modeler.updateRuntimeModel( odaModel, runtimeExtension );    
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
        return new IPluginReference[] 
                    { new CompatiblePluginReference( 
                        "org.eclipse.datatools.connectivity.oda",  //$NON-NLS-1$
                        "3.0.3" ) };  //$NON-NLS-1$
    }

    /* (non-Javadoc)
     * @see org.eclipse.pde.ui.templates.ITemplateSection#getUsedExtensionPoint()
     */
    public String getUsedExtensionPoint()
    {
        return ODA_RUNTIME_EXT_PT;
    }
    
}
