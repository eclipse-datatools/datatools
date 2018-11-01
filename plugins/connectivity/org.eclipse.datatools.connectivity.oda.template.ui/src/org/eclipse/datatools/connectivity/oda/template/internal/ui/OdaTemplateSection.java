/*
 *************************************************************************
 * Copyright (c) 2006, 2007 Actuate Corporation.
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

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.oda.template.ui.nls.Messages;
import org.eclipse.datatools.connectivity.oda.template.ui.plugin.Activator;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.ui.IFieldData;
import org.eclipse.pde.ui.templates.NewPluginTemplateWizard;
import org.eclipse.pde.ui.templates.OptionTemplateSection;
import org.eclipse.pde.ui.templates.TemplateOption;
import org.osgi.framework.Bundle;

/**
 * The abstract base class implementation of an ODA plug-in template section.
 * Provides common behavior for an extended ODA plug-in template section
 * of an ODA plug-in template wizard.
 */
abstract class OdaTemplateSection extends OptionTemplateSection
{
    protected static final String OPTION_RUNTIME_DATA_SOURCE_ID = "odaDataSourceId";  //$NON-NLS-1$
    protected static final String OPTION_DATASOURCE_DISPLAY_NAME = "dataSourceName";  //$NON-NLS-1$
    protected static final String OPTION_DATASET_DISPLAY_NAME = "dataSetName";  //$NON-NLS-1$
    protected static Locale IDENTIFIER_LOCALE = Locale.ENGLISH;
    
    static final String SPACE_CHAR = " ";  //$NON-NLS-1$    
    static final String COLON_CHAR = ":"; //$NON-NLS-1$
    static final String REQUIRED_INDICATOR = "*"; //$NON-NLS-1$
    
    private IFieldData m_fieldData;
    private IPluginModelBase m_initialModel;
    private NewPluginTemplateWizard m_templateWiz;
    private IPluginModelBase m_executingModel;

    /*
     * (non-Javadoc)
     * @see org.eclipse.pde.ui.templates.BaseOptionTemplateSection#initializeFields(org.eclipse.pde.ui.IFieldData)
     */
    protected void initializeFields( IFieldData data )
    {
        // lazy initialization of page options before it is made visible
        m_fieldData = data;    
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.pde.ui.templates.BaseOptionTemplateSection#initializeFields(org.eclipse.pde.core.plugin.IPluginModelBase)
     */
    public void initializeFields( IPluginModelBase model )
    {
        m_initialModel = model;
    }
    
    /**
     * Returns the IFieldData provided to the template section in initializeFields.
     */
    protected IFieldData getFieldData()
    {
        return m_fieldData;
    }
    
    /**
     * Returns the model provided to the template section in initializeFields.
     */
    protected IPluginModelBase getInitializeModel()
    {
        return m_initialModel;
    }

    /**
     * Returns the ODA plug-in template wizard of this extended section.
     */
    protected NewPluginTemplateWizard getTemplateWizard()
    {
        return m_templateWiz;
    }

    /**
     * Sets the ODA plug-in template wizard of this extended section.
     */
    protected void setTemplateWizard( NewPluginTemplateWizard wiz )
    {
        m_templateWiz = wiz;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.pde.ui.templates.BaseOptionTemplateSection#validateOptions(org.eclipse.pde.ui.templates.TemplateOption)
     */
    public void validateOptions( TemplateOption changedOption )
    {
        if( ! isOptionValueValid( changedOption ) )
            return;
        
        // next check all other options on same page is valid
        TemplateOption[] pageOptions = getOptions(0);
        for( int i = 0; i < pageOptions.length; i++ ) 
        {
            if( ! isOptionValueValid( pageOptions[i] ) )
                return;
        }
        
        // no missing or invalid options, ok to reset page
        resetPageState();
    }
    
    /**
     * Validates whether the specified option has valid value defined.
     * @param changedOption
     * @return  true if the option's value is valid; false otherwise.
     */
    private boolean isOptionValueValid( TemplateOption changedOption )
    {
        if( changedOption.isRequired() && changedOption.isEmpty() ) 
        {
            flagMissingRequiredOption( changedOption );
            return false;
        }
        
        if( ! isJavaIdentifierValid( changedOption ) )
            return false;
        
        return true;
    }
    
    /**
     * Checks whether the specified option is intended to be a Java identifier,
     * and thus requires validation that its value contains valid characters.
     * @param option
     * @return  true if the option requires validation that its value contains valid
     *               characters; false otherwise.
     */
    private boolean needsIdCharValidation( TemplateOption option )
    {
        // the Java package name option's value is intended to be an identifier
        if( option.getName() == KEY_PACKAGE_NAME )
            return true;
        
        // check if sub-class declares this option is intended for a Java identifier
        String[] identifierOptions = getAdditionalJavaIdentifierOptions();
        if( identifierOptions == null )
            return false;
        
        for( int i = 0; i < identifierOptions.length; i++ )
        {
            if( option.getName() == identifierOptions[i] )
                return true;            
        }
        return false;
    }
    
    /**
     * Sub-class to override and provide additional options intended
     * for Java identifier, which would then be validated.
     */
    protected String[] getAdditionalJavaIdentifierOptions()
    {
        return null;
    }

    /**
     * Checks whether the specified option is intended to contain a string value in
     * all lower cases.
     * @param option
     * @return  true if option value should be in all lower cases; false otherwise.
     */
    private boolean needsLowerCaseValue( TemplateOption option )
    {
        return option.getName() == KEY_PACKAGE_NAME;
    }
    
    /**
     * Validates whether the specified option has a string value valid for use
     * as a Java identifier.
     * @param option
     * @return  true if option value is valid for use as a Java identifier;
     *          false otherwise.
     */
    private boolean isJavaIdentifierValid( TemplateOption option )
    {
        boolean validateChars = needsIdCharValidation( option );
        boolean validateLowerCase = needsLowerCaseValue( option );
        if( ! validateChars && ! validateLowerCase )
            return true;     // no need to validate, done
        
        String value = (String) option.getValue();
        int i;
        for( i = 0; i < value.length(); i++ ) 
        {
            char ch = value.charAt(i);
            
            if( validateChars )
            {
                if( i == 0 ) 
                {
                    if( ! Character.isJavaIdentifierStart( ch ) )
                        break;  // found invalid character
                } 
                else 
                {
                    if( ! Character.isJavaIdentifierPart( ch ) && 
                        ch != '.' )
                        break;   // found invalid character
                }
            }
            
            if( validateLowerCase )
            {
                if( Character.isUpperCase( ch ) )
                    break;  // found upper case
            }
        }

        // check if all characters in option string value have been validated
        boolean isValid = ( i >= value.length() );
        if( isValid )
            return isValid;     // done validation
        
        // found invalid character
        
        WizardPage page = getPage( getPageIndex( option ) );
        if( page != null )
        {
            page.setPageComplete( false );
            page.setErrorMessage( 
                    Messages.bind( Messages.baseSection_illegaCharsForIdentifier, 
                            option.getMessageLabel() ) );
        }
        return false;
    }
    
    /**
     * Adds a required template option with specified name and label with required indicator 
     * to first page with no initial value.
     * @param name  option name
     * @param label option label/prompt
     * @return  the newly created option
     */
    protected TemplateOption addRequiredOption( String name, String label )
    {
        TemplateOption newOption =
            addOption( name, label + REQUIRED_INDICATOR + COLON_CHAR, (String) null, 0 );
        newOption.setRequired( true );
        return newOption;
    }
    
    /**
     * Adds an optional template option with specified name and label 
     * to first page with no initial value.
     * @param name  option name
     * @param label option label/prompt
     * @return  the newly created option
     */
    protected TemplateOption addOptionalOption( String name, String label )
    {
        TemplateOption newOption = addOption( name, label + COLON_CHAR, (String) null, 0 );
        newOption.setRequired( false );
        return newOption;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eclipse.pde.ui.templates.OptionTemplateSection#getInstallURL()
     */
    protected URL getInstallURL()
    {
        return Activator.getDefault().getInstallURL();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.pde.ui.templates.ITemplateSection#getNewFiles()
     */
    public String[] getNewFiles()
    {
        return new String[] { "META-INF/",   //$NON-NLS-1$
                              "plugin.xml",   //$NON-NLS-1$
                              "plugin.properties" };  //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.pde.ui.templates.OptionTemplateSection#getTemplateDirectory()
     */
    protected String getTemplateDirectory()
    {
        return "templates_oda_3.0"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.pde.ui.templates.AbstractTemplateSection#getPluginResourceBundle()
     */
    protected ResourceBundle getPluginResourceBundle()
    {
        Bundle bundle = Platform.getBundle( Activator.getPluginId() );
        return Platform.getResourceBundle( bundle );
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.pde.ui.templates.BaseOptionTemplateSection#isDependentOnParentWizard()
     */
    public boolean isDependentOnParentWizard()
    {
        return true;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.pde.ui.templates.AbstractTemplateSection#addPages(org.eclipse.jface.wizard.Wizard)
     */
    public void addPages( Wizard wizard )
    {
        WizardPage page = createPage( 0, null );
        page.setTitle( getWizardPageTitle() ); 
        page.setDescription( Messages.baseSection_pageDesc );  
        wizard.addPage( page );
        markPagesAdded();
    }
    
    /**
     * Returns the wizard page title defined by a subclass.
     */
    protected abstract String getWizardPageTitle();

    /* (non-Javadoc)
     * @see org.eclipse.pde.ui.templates.BaseOptionTemplateSection#execute(org.eclipse.core.resources.IProject, org.eclipse.pde.core.plugin.IPluginModelBase, org.eclipse.core.runtime.IProgressMonitor)
     */
    public void execute( IProject project, IPluginModelBase model,
            IProgressMonitor monitor ) throws CoreException
    {
        // hold on to model being executed to make it available to subclass 
        // to override as needed,
        // before the generateFiles method is called in execute
        m_executingModel = model;
        super.execute( project, model, monitor );
        m_executingModel = null;    // reset
    }

    protected IPluginModelBase getExecutingModel()
    {
        return m_executingModel;    // may be null if not called during execute
    }
    
    /*
     * (non-Javadoc)
     * @see org.eclipse.pde.ui.templates.AbstractTemplateSection#updateModel(org.eclipse.core.runtime.IProgressMonitor)
     */
    protected void updateModel( IProgressMonitor monitor )
        throws CoreException
    {
        updateOdaPluginModel( model );
    }

    /**
     * Subclass must implement this method to update its portion of
     * the ODA plugin model.
     */
    protected abstract void updateOdaPluginModel( IPluginModelBase odaModel ) throws CoreException;    

    /**
     * Converts given name to a valid Java identifer.
     * This is not currently used.
     */
    protected String convertToJavaIdentifier( String name )
    {
        StringBuffer buffer = new StringBuffer();
        for( int i = 0; i < name.length(); i++ ) 
        {
            char ch = name.charAt(i);
            if( i == 0 ) 
            {
                if( ! Character.isJavaIdentifierStart( ch ) )
                    continue;  // skip invalid character
            } 
            else 
            {
                if( ! Character.isJavaIdentifierPart( ch ) && 
                    ch != '.' )
                    continue;   // skip invalid character
            }
            
            // is valid character, use it
            buffer.append( ch );
        }
        
        return buffer.toString().toLowerCase( IDENTIFIER_LOCALE );        
    }    
    
    /**
     * Returns the plugin id provided by plug-in wizard in either the 
     * model or fieldData used in initializeFields.
     */
    protected String getNewPluginId()
    {
        if( m_initialModel != null ) 
            return m_initialModel.getPluginBase().getId();
        
        // perhaps this is called after page is made visible
        if( m_fieldData != null ) 
            return m_fieldData.getId();
        
        // obtain the id from the template wizard
        return getTemplateWizard().getData().getId();
    }
    
    /**
     * Returns the plugin name provided by plug-in wizard in either the 
     * model or fieldData used in initializeFields.
     */
    protected String getNewPluginName()
    {
        if( m_initialModel != null ) 
            return m_initialModel.getPluginBase().getName();
        
        // perhaps this is called after page is made visible
        if( m_fieldData != null ) 
            return m_fieldData.getName();
        
        // obtain from the template wizard
        return getTemplateWizard().getData().getName();
    }
    
    /**
     * Returns the provider name provided by plug-in wizard in either the 
     * model or fieldData used in initializeFields.
     * Returns an empty string if none is found.
     */
    protected String getNewProviderName()
    {
        String name;
        if( m_initialModel != null ) 
            name = m_initialModel.getPluginBase().getProviderName();
        else
        {
            // perhaps this is called after page is made visible
            if( m_fieldData != null ) 
                name = m_fieldData.getProvider();
            else // obtain from the template wizard
                name = getTemplateWizard().getData().getProvider();
        }
        
        return ( name == null ) ? "" : name; //$NON-NLS-1$
    }

    /**
     * Formats and returns the initialized value of the Java package name to generate.
     */
    protected String getDefaultPackageName()
    {
        return getNewPluginId().toLowerCase( IDENTIFIER_LOCALE ) + ".impl";  //$NON-NLS-1$
    }

    /**
     * Formats and returns the default base name for use as
     * the ODA extension display name.
     */
    protected String getDefaultDriverBaseName()
    {
        String pluginKeyword = " Plug-in";  //$NON-NLS-1$
        String pluginName = getNewPluginName();

        // strip out the plugin keyword if exists
        if( pluginName.endsWith( pluginKeyword ) )
            return pluginName.substring( 0, pluginName.length() - pluginKeyword.length() );
        return pluginName;
    }
    
    /**
     * Formats and returns the initialized value of the ODA data source display name.
     */
    protected String getDefaultDataSourceName()
    {
        String providerName = getNewProviderName();
        if( providerName.length() > 0 )
            providerName += SPACE_CHAR;
        return providerName + 
                getDefaultDriverBaseName() + SPACE_CHAR + 
                Messages.baseSection_dataSourceNameSuffix;
    }
    
    /**
     * Formats and returns the initialized value of the ODA data set display name.
     */
    protected String getDefaultDataSetName()
    {
        String providerName = getNewProviderName();
        if( providerName.length() > 0 )
            providerName += SPACE_CHAR;
        return providerName + 
                getDefaultDriverBaseName() + SPACE_CHAR +
                Messages.baseSection_dataSetNameSuffix;
    }
    
}
