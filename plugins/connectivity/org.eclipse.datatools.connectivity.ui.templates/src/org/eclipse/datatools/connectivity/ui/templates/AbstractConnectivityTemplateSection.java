/*******************************************************************************
 * Copyright (c) 2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui.templates;

import java.net.URL;
import java.util.ResourceBundle;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.osgi.service.resolver.VersionRange;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.IPluginReference;
import org.eclipse.pde.ui.IFieldData;
import org.eclipse.pde.ui.templates.OptionTemplateSection;
import org.eclipse.pde.ui.templates.TemplateOption;
import org.osgi.framework.Bundle;
import org.osgi.framework.Version;

/**
 * Abstract template section that provides many helper functions to
 * other Connectivity PDE templates.
 * 
 * @author brianf
 *
 */
public abstract class AbstractConnectivityTemplateSection extends OptionTemplateSection {

	static final String SPACE_CHAR = " ";  //$NON-NLS-1$    
	static final String COLON_CHAR = ":"; //$NON-NLS-1$
	static final String REQUIRED_INDICATOR = "*"; //$NON-NLS-1$

	/* (non-Javadoc)
	 * @see org.eclipse.pde.ui.templates.OptionTemplateSection#getInstallURL()
	 */
	protected URL getInstallURL() {
		return ConnectivityTemplatesPlugin.getDefault().getInstallURL();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.pde.ui.templates.AbstractTemplateSection#getPluginResourceBundle()
	 */
	protected ResourceBundle getPluginResourceBundle() {
		Bundle bundle = Platform.getBundle( ConnectivityTemplatesPlugin.getPluginId() );
		return Platform.getResourceBundle( bundle );
	}

	/* (non-Javadoc)
	 * @see org.eclipse.pde.ui.templates.ITemplateSection#getNewFiles()
	 */
	public String[] getNewFiles() {
		return new String[] { "META-INF/",   //$NON-NLS-1$
				"plugin.xml",   //$NON-NLS-1$
		"plugin.properties" };  //$NON-NLS-1$
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
		return addRequiredOption(name, label, 0);
	}

	/**
	 * Adds a required template option with specified name and label with required indicator 
	 * to page index passed in with no initial value.
	 * @param name  option name
	 * @param label option label/prompt
	 * @param page option page index
	 * @return  the newly created option
	 */
	protected TemplateOption addRequiredOption( String name, String label, int page )
	{
		TemplateOption newOption =
			addOption( name, label + REQUIRED_INDICATOR + COLON_CHAR, (String) null, page );
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
		return addOptionalOption(name, label, 0);
	}

	/**
	 * Adds an optional template option with specified name and label with required indicator 
	 * to page index passed in with no initial value.
	 * @param name  option name
	 * @param label option label/prompt
	 * @param page option page index
	 * @return  the newly created option
	 */
	protected TemplateOption addOptionalOption( String name, String label, int page )
	{
		TemplateOption newOption = addOption( name, label + COLON_CHAR, (String) null, page );
		newOption.setRequired( false );
		return newOption;
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

	/**
	 * Initializes option values
	 */
	protected abstract void initializeOptions();

	/**
	 * Returns dependencies for the new plug-in
	 * @return
	 */
	public IPluginReference[] getDependencies()
	{
		Version version150 = new Version( 1, 5, 0 );
		Version version200 = new Version( 2, 0, 0 );
		VersionRange compatible150 = new VersionRange( version150, true, version200, false );       

		return new IPluginReference[] 
		                            { new CompatiblePluginReference( 
		                            		"org.eclipse.datatools.connectivity",  //$NON-NLS-1$
		                            		compatible150.toString() ),
		                            };
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.pde.ui.templates.AbstractTemplateSection#addPages(org.eclipse.jface.wizard.Wizard)
	 */
	public void addPages( Wizard wizard )
	{
		WizardPage page = createPage( 0, null );
		page.setTitle( getWizardPageTitle() ); 
		page.setDescription( getWizardPageDescription() );  
		wizard.addPage( page );
		markPagesAdded();
	}

	/**
	 * Returns the wizard page title defined by a subclass.
	 */
	protected abstract String getWizardPageTitle();

	/**
	 * Returns the wizard page description defined by a subclass.
	 */
	protected abstract String getWizardPageDescription();

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

		if( ! isJavaPackageValid( changedOption ) )
			return false;

		return true;
	}

	/**
	 * Determines if the package name is valid
	 * @param changedOption
	 * @return
	 */
	private boolean isJavaPackageValid (TemplateOption changedOption) {
		if (changedOption.getName().equals(KEY_PACKAGE_NAME)) {
			String value = (String) changedOption.getValue();
			if (value.indexOf("package") > -1) { //$NON-NLS-1$
				return false;
			}
		}
		return true;
	}

}
