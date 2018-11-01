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

import java.util.Locale;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.datatools.connectivity.oda.template.ui.nls.Messages;
import org.eclipse.pde.core.plugin.IPluginElement;
import org.eclipse.pde.core.plugin.IPluginExtension;
import org.eclipse.pde.core.plugin.IPluginLibrary;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.IPluginModelFactory;

/**
 * A helper responsible for updating an ODA plug-in model with ODA-specific extensions
 * configuration based on the user-defined template options defined in
 * extended OdaTemplateSection. 
 */
class OdaPluginModeler
{
    private static final String PROP_ODA_DATA_SOURCE_ID = "%oda.data.source.id";  //$NON-NLS-1$
    private static final String PROP_DATA_SOURCE_NAME = "%data.source.name";  //$NON-NLS-1$
    private static final String ODA_PROFILE_FACTORY_CLASS = 
        "org.eclipse.datatools.connectivity.oda.profile.OdaConnectionFactory";  //$NON-NLS-1$
    private static final String VALUE_TRUE = Boolean.toString( true );
    private static final String VALUE_FALSE = Boolean.toString( false );
    private static final Locale IDENTIFIER_LOCALE = OdaTemplateSection.IDENTIFIER_LOCALE;

    // extension points used in the generated plug-ins
    static final String ODA_RUNTIME_EXT_PT =
        "org.eclipse.datatools.connectivity.oda.dataSource"; //$NON-NLS-1$
    static final String ODA_DESIGN_TIME_EXT_PT =
        "org.eclipse.datatools.connectivity.oda.design.ui.dataSource"; //$NON-NLS-1$
    static final String CONNECTIVITY_PROFILE_EXT_PT =
        "org.eclipse.datatools.connectivity.connectionProfile"; //$NON-NLS-1$
    static final String CONNECTIVITY_PROFILE_IMAGE_EXT_PT =
        "org.eclipse.datatools.connectivity.ui.connectionProfileImage"; //$NON-NLS-1$
    static final String CONNECTIVITY_PROFILE_PAGE_EXT_PT =
        "org.eclipse.ui.propertyPages"; //$NON-NLS-1$

    private OdaTemplateSection m_section;    


    OdaPluginModeler( OdaTemplateSection section )
    {
        assert( section != null );
        m_section = section;
    }
    
    /**
     * Updates the specified runtime extension configuration
     * based on the user-defined runtime template options, and
     * adds the updated extension to the specified odaModel.
     * @param odaModel
     * @param runtimeExtension
     * @throws CoreException
     */
    void updateRuntimeModel( IPluginModelBase odaModel,
                             IPluginExtension runtimeExtension ) 
        throws CoreException
    {
        odaModel.getPluginBase().setName( "%plugin.name" ); //$NON-NLS-1$
        
        IPluginExtension extension = runtimeExtension;
        extension.setId( PROP_ODA_DATA_SOURCE_ID );
        if ( ! extension.isInTheModel() )
            odaModel.getPluginBase().add( extension );
        
        IPluginModelFactory factory = odaModel.getPluginFactory();
        
        // specifies root classpath
        IPluginLibrary rootLib = factory.createLibrary();
        rootLib.setName( "." ); //$NON-NLS-1$
        if ( ! rootLib.isInTheModel() )
            odaModel.getPluginBase().add( rootLib );

        // specifies export package for access by corresponding designer plugin
        IPluginLibrary exportPackage = factory.createLibrary();
        exportPackage.setName( "src" ); //$NON-NLS-1$
        exportPackage.setExported( true );
        String exportPackageFilter = getKeyPackageName() + ".*"; //$NON-NLS-1$
        exportPackage.setContentFilters( new String[]{ exportPackageFilter } );
        if ( ! exportPackage.isInTheModel() )
            odaModel.getPluginBase().add( exportPackage );

        // dataSource element
        IPluginElement dataSourceElement = factory.createElement( extension );
        dataSourceElement.setName( "dataSource" ); //$NON-NLS-1$
        dataSourceElement.setAttribute( "id", PROP_ODA_DATA_SOURCE_ID ); //$NON-NLS-1$
        dataSourceElement.setAttribute( "driverClass", getKeyPackageName() + ".Driver" );  //$NON-NLS-1$ //$NON-NLS-2$
        dataSourceElement.setAttribute( "odaVersion", "3.2" ); //$NON-NLS-1$ //$NON-NLS-2$
        dataSourceElement.setAttribute( "defaultDisplayName", PROP_DATA_SOURCE_NAME  ); //$NON-NLS-1$
        dataSourceElement.setAttribute( "setThreadContextClassLoader" , VALUE_FALSE );//$NON-NLS-1$
        extension.add( dataSourceElement );

        // data source properties
        String numPropsValue = m_section.getStringOption( RuntimeTemplateSection.OPTION_NUM_CONN_PROPERTIES );
        addPropertiesElement( factory, dataSourceElement, numPropsValue );
        
        // dataSet element
        IPluginElement dataSetElement = factory.createElement( extension );
        dataSetElement.setName( "dataSet" ); //$NON-NLS-1$
        dataSetElement.setAttribute( "id", getOdaDataSourceId() + ".dataSet" ); //$NON-NLS-1$ //$NON-NLS-2$
        dataSetElement.setAttribute( "defaultDisplayName", "%data.set.name" ); //$NON-NLS-1$ //$NON-NLS-2$
        extension.add( dataSetElement );
        
        // data set's properties
        numPropsValue = m_section.getStringOption( RuntimeTemplateSection.OPTION_NUM_QUERY_PROPERTIES );
        addPropertiesElement( factory, dataSetElement, numPropsValue );
        
        // data set's dataTypeMapping child elements
        addDataTypeMappingElements( factory, dataSetElement );        
    }

    /**
     * Generates the properties element and adds to the specified data source/set element
     * with the specified number of nested property elements with corresponding attributes .
     * @param factory
     * @param dataElement
     * @param numPropsValue
     * @throws CoreException
     */
    private void addPropertiesElement( IPluginModelFactory factory, 
                                        IPluginElement dataElement,
                                        String numPropsValue )
        throws CoreException
    {
        if( numPropsValue == null )
            return;     // no properties needed
        
        int numProps = 0;
        try
        {
            numProps = Integer.parseInt( numPropsValue );
        }
        catch( NumberFormatException e )
        {
            // TODO - log warning
            e.printStackTrace();
        }
        if( numProps <= 0 )
            return;     // no properties needed
        
        // Properties element
        IPluginElement propertiesElement = factory.createElement( dataElement );
        propertiesElement.setName( "properties" ); //$NON-NLS-1$
        dataElement.add( propertiesElement );
        
        // for each nested property element
        for( int i = 1; i <= numProps; i++ )
        {
            IPluginElement aPropElement = factory.createElement( propertiesElement );
            aPropElement.setName( "property" );  //$NON-NLS-1$
            aPropElement.setAttribute( "name", "property" + i );  //$NON-NLS-1$ //$NON-NLS-2$
            aPropElement.setAttribute( "defaultDisplayName",  //$NON-NLS-1$
                    Messages.modeler_propertyDisplayNamePrefix + " " + i );  //$NON-NLS-1$
            aPropElement.setAttribute( "type", "string" );  //$NON-NLS-1$ //$NON-NLS-2$
            //  leaves "defaultValue" attribute with no value      
            aPropElement.setAttribute( "isEncryptable", VALUE_FALSE );   //$NON-NLS-1$
            aPropElement.setAttribute( "allowsEmptyValueAsNull" , VALUE_TRUE );  //$NON-NLS-1$
            propertiesElement.add( aPropElement );
        }
    }
    
    /**
     * Generates the default dataTypeMapping elements and 
     * adds to the specified data set element.
     * @param factory
     * @param dataSetElement
     * @throws CoreException
     */
    private void addDataTypeMappingElements( IPluginModelFactory factory, 
                                        IPluginElement dataSetElement )
        throws CoreException
    {
        // add each dataTypeMapping
        DataTypeMapping[] dataTypeMappings = getDataTypeMappings();
        for( int i = 0; i < dataTypeMappings.length; i++ )
        {
            IPluginElement mappingElement = factory.createElement( dataSetElement );
            mappingElement.setName( "dataTypeMapping" ); //$NON-NLS-1$
            
            DataTypeMapping dataType = dataTypeMappings[ i ];           
            mappingElement.setAttribute( "nativeDataTypeCode", dataType.getNativeDataTypeCodeAsString() ); //$NON-NLS-1$
            mappingElement.setAttribute( "nativeDataType", dataType.getNativeDataTypeName() ); //$NON-NLS-1$
            mappingElement.setAttribute( "odaScalarDataType", dataType.getOdaScalarDataType() ); //$NON-NLS-1$

            dataSetElement.add( mappingElement );
        }
    }
    
    /**
     * Gets the user-defined value for the ODA runtime data source element id.
     */
    private String getOdaDataSourceId()
    {
        String id = m_section.getStringOption( OdaTemplateSection.OPTION_RUNTIME_DATA_SOURCE_ID );
        assert( id != null );
        return id.toLowerCase( IDENTIFIER_LOCALE );        
    }
    
    /**
     * Gets the user-defined value for the Java package name to generate.
     */
    private String getKeyPackageName()
    {
        String name = m_section.getStringOption( OdaTemplateSection.KEY_PACKAGE_NAME );
        assert( name != null );
        return name.toLowerCase( IDENTIFIER_LOCALE );
    }

    /**
     * Data Set's default data type mappings for generation of related extension elements.
     */
    private static DataTypeMapping[] sm_dataTypeMappings = null;
    
    static DataTypeMapping[] getDataTypeMappings()
    {
        if( sm_dataTypeMappings == null )
        {
            sm_dataTypeMappings = new DataTypeMapping[] 
                {
                    new DataTypeMapping( java.sql.Types.CHAR, "String", "String" ),  //$NON-NLS-1$ //$NON-NLS-2$
                    new DataTypeMapping( java.sql.Types.INTEGER, "Integer", "Integer" ),  //$NON-NLS-1$ //$NON-NLS-2$
                    new DataTypeMapping( java.sql.Types.DOUBLE, "Double", "Double" ),  //$NON-NLS-1$ //$NON-NLS-2$
                    new DataTypeMapping( java.sql.Types.DECIMAL, "BigDecimal", "Decimal" ),  //$NON-NLS-1$ //$NON-NLS-2$
                    new DataTypeMapping( java.sql.Types.DATE, "Date", "Date" ),  //$NON-NLS-1$ //$NON-NLS-2$
                    new DataTypeMapping( java.sql.Types.TIME, "Time", "Time" ),  //$NON-NLS-1$ //$NON-NLS-2$
                    new DataTypeMapping( java.sql.Types.TIMESTAMP, "Timestamp", "Timestamp" ),  //$NON-NLS-1$ //$NON-NLS-2$
                    new DataTypeMapping( java.sql.Types.BOOLEAN, "Boolean", "Boolean" )  //$NON-NLS-1$ //$NON-NLS-2$
                    // excludes the more complex data types by default
//                  new DataTypeMapping( java.sql.Types.BLOB, "BLOB", "String" ),  //$NON-NLS-1$ //$NON-NLS-2$
//                  new DataTypeMapping( java.sql.Types.CLOB, "CLOB", "String" )  //$NON-NLS-1$ //$NON-NLS-2$
                };
        }
        
        return sm_dataTypeMappings;
    }
    
    private static class DataTypeMapping
    {
        // the default mapping does not contain alternativeOdaDataType 
        
        private String m_nativeTypeCodeString;
        private String m_nativeTypeName;
        private String m_odaScalarType;
        
        DataTypeMapping( int nativeTypeCode, String nativeTypeName, String odaScalarType )
        {
            m_nativeTypeCodeString = Integer.toString( nativeTypeCode );
            m_nativeTypeName = nativeTypeName;
            m_odaScalarType = odaScalarType;
        }
        
        String getNativeDataTypeCodeAsString()
        {
            return m_nativeTypeCodeString;
        }
        
        String getNativeDataTypeName()
        {
            return m_nativeTypeName;
        }
        
        String getOdaScalarDataType()
        {
            return m_odaScalarType;
        }
    }
 
    /**
     * Updates the specified connection profile extension configuration
     * with runtime elements, based on the user-defined runtime template options.
     * And adds the updated extension to the specified odaModel.
     * @param odaModel
     * @param profileExtension
     * @throws CoreException
     */
    void updateConnProfileRuntimeModel( IPluginModelBase odaModel, 
                                        IPluginExtension profileExtension )
        throws CoreException
    {
        if ( ! profileExtension.isInTheModel() )
            odaModel.getPluginBase().add( profileExtension );
        
        IPluginModelFactory factory = odaModel.getPluginFactory();
        
        // connectionProfile extension
        // category element
        IPluginElement categoryElement = factory.createElement( profileExtension );
        categoryElement.setName( "category" ); //$NON-NLS-1$
        categoryElement.setAttribute( "id", PROP_ODA_DATA_SOURCE_ID ); //$NON-NLS-1$
        categoryElement.setAttribute( "name", PROP_DATA_SOURCE_NAME ); //$NON-NLS-1$
        categoryElement.setAttribute( "parentCategory",   //$NON-NLS-1$
                "org.eclipse.datatools.connectivity.oda.profileCategory" ); //$NON-NLS-1$
        profileExtension.add( categoryElement );
        
        // connectionProfile element
        IPluginElement profileElement = factory.createElement( profileExtension );
        profileElement.setName( "connectionProfile" ); //$NON-NLS-1$
        profileElement.setAttribute( "category", PROP_ODA_DATA_SOURCE_ID ); //$NON-NLS-1$
        profileElement.setAttribute( "id", PROP_ODA_DATA_SOURCE_ID ); //$NON-NLS-1$
        profileElement.setAttribute( "name", "%connection.profile.name" ); //$NON-NLS-1$ //$NON-NLS-2$
        profileElement.setAttribute( "pingFactory", ODA_PROFILE_FACTORY_CLASS ); //$NON-NLS-1$
        profileExtension.add( profileElement );
         
        // connectionFactory element
        IPluginElement factoryElement = factory.createElement( profileExtension );
        factoryElement.setName( "connectionFactory" ); //$NON-NLS-1$
        factoryElement.setAttribute( "id", "org.eclipse.datatools.connectivity.oda.IConnection" ); //$NON-NLS-1$ //$NON-NLS-2$
        factoryElement.setAttribute( "profile", PROP_ODA_DATA_SOURCE_ID ); //$NON-NLS-1$
        factoryElement.setAttribute( "name", Messages.modeler_odaConnectionFactory ); //$NON-NLS-1$
        factoryElement.setAttribute( "class", ODA_PROFILE_FACTORY_CLASS ); //$NON-NLS-1$
        profileExtension.add( factoryElement );   
    }

    /**
     * Updates the specified connection profile extension configuration
     * with design ui elements, and the profile property page
     * extension configuration, based on the user-defined designer template options.
     * And adds the updated extensions to the specified odaModel.
     * @param odaModel
     * @param profileExtension
     * @param profileImageExtension
     * @param profilePageExtension
     * @throws CoreException
     */
    void updateConnProfileDesignerModel( IPluginModelBase odaModel, 
                            IPluginExtension profileExtension,
                            IPluginExtension profileImageExtension,
                            IPluginExtension profilePageExtension )
        throws CoreException
    {
        if ( ! profileExtension.isInTheModel() )
            odaModel.getPluginBase().add( profileExtension );
        if ( ! profileImageExtension.isInTheModel() )
            odaModel.getPluginBase().add( profileImageExtension );
        if ( ! profilePageExtension.isInTheModel() )
            odaModel.getPluginBase().add( profilePageExtension );
        
        IPluginModelFactory factory = odaModel.getPluginFactory();
        
        // connectionProfile extension        
        // newWizard element
        IPluginElement profileWizElement = factory.createElement( profileExtension );
        profileWizElement.setName( "newWizard" ); //$NON-NLS-1$
        profileWizElement.setAttribute( "id", PROP_ODA_DATA_SOURCE_ID ); //$NON-NLS-1$
        profileWizElement.setAttribute( "profile", PROP_ODA_DATA_SOURCE_ID ); //$NON-NLS-1$
        profileWizElement.setAttribute( "name", "%newwizard.name" ); //$NON-NLS-1$ //$NON-NLS-2$
        profileWizElement.setAttribute( "description", "%newwizard.description" ); //$NON-NLS-1$ //$NON-NLS-2$
        profileWizElement.setAttribute( "class",   //$NON-NLS-1$
                "org.eclipse.datatools.connectivity.oda.design.ui.wizards.NewDataSourceWizard" ); //$NON-NLS-1$
        profileWizElement.setAttribute( "icon", "icons/new_oda_dswiz.ico" );  //$NON-NLS-1$ //$NON-NLS-2$
        profileExtension.add( profileWizElement );

        // profile icon image extension
        // profileImage element
        IPluginElement pageImageElement = factory.createElement( profileImageExtension );
        pageImageElement.setName( "profileImage" ); //$NON-NLS-1$
        pageImageElement.setAttribute( "profileID", PROP_ODA_DATA_SOURCE_ID ); //$NON-NLS-1$
        pageImageElement.setAttribute( "icon", "icons/profile.gif" ); //$NON-NLS-1$  //$NON-NLS-2$
        profileImageExtension.add( pageImageElement );
        
        // profile property page extension
        // page element
        IPluginElement pageElement = factory.createElement( profilePageExtension );
        pageElement.setName( "page" ); //$NON-NLS-1$
        pageElement.setAttribute( "id", PROP_ODA_DATA_SOURCE_ID ); //$NON-NLS-1$
        pageElement.setAttribute( "name", "%profile.propertypage.name" ); //$NON-NLS-1$ //$NON-NLS-2$
        pageElement.setAttribute( "class",   //$NON-NLS-1$
                "org.eclipse.datatools.connectivity.oda.design.ui.pages.impl.DefaultDataSourcePropertyPage" ); //$NON-NLS-1$
        profilePageExtension.add( pageElement );
        
        // page enabledWhen element
        IPluginElement pageEnabledWhenElement = factory.createElement( pageElement );
        pageEnabledWhenElement.setName( "enabledWhen" ); //$NON-NLS-1$
        IPluginElement pageinstanceOfElement = factory.createElement( pageEnabledWhenElement );
        pageinstanceOfElement.setName( "instanceof" ); //$NON-NLS-1$
        pageinstanceOfElement.setAttribute( "value",  //$NON-NLS-1$
                "org.eclipse.datatools.connectivity.IConnectionProfile" ); //$NON-NLS-1$
        pageEnabledWhenElement.add( pageinstanceOfElement );
        pageElement.add( pageEnabledWhenElement );

        // page filter element
        IPluginElement pageFilterElement = factory.createElement( pageElement );
        pageFilterElement.setName( "filter" ); //$NON-NLS-1$
        pageFilterElement.setAttribute( "name",   //$NON-NLS-1$
                "org.eclipse.datatools.profile.property.id" ); //$NON-NLS-1$
        pageFilterElement.setAttribute( "value", PROP_ODA_DATA_SOURCE_ID ); //$NON-NLS-1$
        pageElement.add( pageFilterElement );
    }
    
    /**
     * Updates the specified designer extension configuration
     * based on the user-defined designer template options, and
     * adds the updated extension to the specified odaModel.
     * @param odaModel
     * @param designerExtension
     * @throws CoreException
     */
    void updateDesignerModel( IPluginModelBase odaModel,
                              IPluginExtension designerExtension ) 
        throws CoreException
    {
        odaModel.getPluginBase().setName( "%plugin.name" );  //$NON-NLS-1$

        if ( ! designerExtension.isInTheModel() )
            odaModel.getPluginBase().add( designerExtension );
        
        IPluginModelFactory factory = odaModel.getPluginFactory();
            
        // dataSourceUI element
        IPluginElement dataSourceUIElement = factory.createElement( designerExtension );
        dataSourceUIElement.setName( "dataSourceUI" ); //$NON-NLS-1$
        dataSourceUIElement.setAttribute( "id", PROP_ODA_DATA_SOURCE_ID ); //$NON-NLS-1$
        designerExtension.add( dataSourceUIElement );
        
        // dataSourceUI.newDataSourceWizard element
        IPluginElement odaSourceWizElement = factory.createElement( dataSourceUIElement );
        odaSourceWizElement.setName( "newDataSourceWizard" ); //$NON-NLS-1$
        odaSourceWizElement.setAttribute( "includesProgressMonitor", VALUE_FALSE ); //$NON-NLS-1$
        odaSourceWizElement.setAttribute( "pageClass",   //$NON-NLS-1$
                "org.eclipse.datatools.connectivity.oda.design.ui.pages.impl.DefaultDataSourceWizardPage" ); //$NON-NLS-1$
        odaSourceWizElement.setAttribute( "pageTitle", "%wizard.data.source.page.title" );  //$NON-NLS-1$ //$NON-NLS-2$
        odaSourceWizElement.setAttribute( "windowTitle", "%wizard.window.title" );  //$NON-NLS-1$ //$NON-NLS-2$
        dataSourceUIElement.add( odaSourceWizElement );
        
        // dataSetUI element
        IPluginElement dataSetUIElement = factory.createElement( designerExtension );
        dataSetUIElement.setName( "dataSetUI" ); //$NON-NLS-1$
        
        String dataSourceId = m_section.getStringOption( DesignTimeTemplateSection.OPTION_RUNTIME_DATA_SOURCE_ID );
        String dataSetId = m_section.getStringOption( DesignTimeTemplateSection.OPTION_RUNTIME_DATA_SET_ID );
        if( dataSetId == null && dataSourceId != null )
            dataSetId = dataSourceId + ".dataSet"; //$NON-NLS-1$
        dataSetUIElement.setAttribute( "id", dataSetId ); //$NON-NLS-1$
        
        String dataSetPageId = dataSetId + ".page1"; //$NON-NLS-1$
        dataSetUIElement.setAttribute( "initialPageId", dataSetPageId ); //$NON-NLS-1$
        
        dataSetUIElement.setAttribute( "supportsInParameters", VALUE_TRUE ); //$NON-NLS-1$
        dataSetUIElement.setAttribute( "supportsOutParameters", VALUE_FALSE ); //$NON-NLS-1$
        designerExtension.add( dataSetUIElement );
        
        // dataSetUI.dataSetWizard element
        IPluginElement dataSetWizElement = factory.createElement( dataSetUIElement );
        dataSetWizElement.setName( "dataSetWizard" ); //$NON-NLS-1$
        dataSetWizElement.setAttribute( "class", //$NON-NLS-1$
           "org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizard" ); //$NON-NLS-1$
        dataSetWizElement.setAttribute( "windowTitle", "%wizard.data.set.window.title" );  //$NON-NLS-1$ //$NON-NLS-2$
        dataSetUIElement.add( dataSetWizElement );

        // dataSetUI.dataSetPage element
        IPluginElement dataSetPageElement = factory.createElement( dataSetUIElement );
        dataSetPageElement.setName( "dataSetPage" ); //$NON-NLS-1$
        dataSetPageElement.setAttribute( "id", dataSetPageId ); //$NON-NLS-1$
        dataSetPageElement.setAttribute( "displayName", "%wizard.data.set.page.title" );  //$NON-NLS-1$ //$NON-NLS-2$
        dataSetPageElement.setAttribute( "icon", "icons/datasetpage.ico" );  //$NON-NLS-1$ //$NON-NLS-2$
        dataSetPageElement.setAttribute( "path", "/" );  //$NON-NLS-1$ //$NON-NLS-2$
        dataSetPageElement.setAttribute( "wizardPageClass",  //$NON-NLS-1$
                getKeyPackageName() + ".CustomDataSetWizardPage" );  //$NON-NLS-1$
        dataSetUIElement.add( dataSetPageElement );
    }
    
    /**
     * Adjusts the model's bundle headers to trigger generation of needed entries
     * in the manifest.mf of the new plug-in project (Bugzilla 172744).
     * Using internal PDE API as an interim solution to work around 
     * the PDE UI Template API's restrictions (Bugzilla 173393).
     * @param odaModel      model being executed during project creation
     * @param forRuntimeBundle  true for adjusting headers in the runtime bundle;
     *                      false for the design-time bundle
     */
    void adjustManifestHeaders( IPluginModelBase odaModel, boolean forRuntimeBundle )
    {
        /* removed workaround code for bug 173393, which is 
         * fixed in PDE UI Template 3.3M7
         */        
    }

}
