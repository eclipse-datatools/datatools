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

import java.util.ArrayList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.osgi.service.resolver.VersionRange;
import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.core.plugin.IPluginElement;
import org.eclipse.pde.core.plugin.IPluginExtension;
import org.eclipse.pde.core.plugin.IPluginModelFactory;
import org.eclipse.pde.core.plugin.IPluginReference;
import org.osgi.framework.Version;

/**
 * PDE template section for defining a new connection profile plug-in
 * 
 * @author brianf
 *
 */
public class ConnectionProfileTemplateSection extends AbstractConnectivityTemplateSection {

	private static final String EXTENSION_POINT = "org.eclipse.datatools.connectivity.connectionProfile"; //$NON-NLS-1$
	private static final String KEY_PROFILE_ID = "profileID"; //$NON-NLS-1$
	private static final String KEY_CATEGORY_ID = "categoryID"; //$NON-NLS-1$
	private static final String KEY_PROFILE_NAME = "profileName"; //$NON-NLS-1$
	private static final String KEY_ICON = "icon"; //$NON-NLS-1$
	private static final String KEY_PING_FACTORY = "pingFactory"; //$NON-NLS-1$
	private static final String KEY_WIZARD_CLASS = "newWizardClass"; //$NON-NLS-1$
	private static final String GEN_JDBC_CATEGORY = "org.eclipse.datatools.connectivity.db.category"; //$NON-NLS-1$

	/**
	 * Constructor
	 */
	public ConnectionProfileTemplateSection() {
		super();
		setPageCount( 1 );
		createOptions();       
		initializeOptions();
	}

	/*
	 * Creates base options
	 */
	private void createOptions() {
		addRequiredOption(KEY_PROFILE_NAME, Messages.getString("ConnectionProfileTemplateSection.ProfileNameLabel")); //$NON-NLS-1$
		addRequiredOption(KEY_PROFILE_ID, Messages.getString("ConnectionProfileTemplateSection.ProfileIDLabel")); //$NON-NLS-1$
		addRequiredOption(KEY_CATEGORY_ID, Messages.getString("ConnectionProfileTemplateSection.CategoryIDLabel")); //$NON-NLS-1$
		addOptionalOption(KEY_ICON, Messages.getString("ConnectionProfileTemplateSection.IconPathLabel")); //$NON-NLS-1$
		addOptionalOption(KEY_PACKAGE_NAME, Messages.getString("ConnectionProfileTemplateSection.PackageNameLabel")); //$NON-NLS-1$
		addOptionalOption(KEY_PING_FACTORY, Messages.getString("ConnectionProfileTemplateSection.PingFactoryLabel")); //$NON-NLS-1$
		addOptionalOption(KEY_WIZARD_CLASS, Messages.getString("ConnectionProfileTemplateSection.WizardClassLabel")); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see org.eclipse.pde.ui.templates.OptionTemplateSection#getSectionId()
	 */
	public String getSectionId() {
		return "connectionProfile";  //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see org.eclipse.pde.ui.templates.AbstractTemplateSection#updateModel(org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected void updateModel(IProgressMonitor monitor) throws CoreException {
		/*
		 * id, category, name, configurationType, icon, maintainConnection, 
		 * pingFactory, propertiesPersistenceHook
		 */
		IPluginBase plugin = model.getPluginBase();
		IPluginModelFactory factory = model.getPluginFactory();

		IPluginExtension extension = 
			createExtension(EXTENSION_POINT, true);

		IPluginElement element = factory.createElement(extension);
		element.setName("connectionProfile"); //$NON-NLS-1$
		element.setAttribute("id", getStringOption(KEY_PROFILE_ID)); //$NON-NLS-1$

		element.setAttribute("name", "%connection.profile.name"); //getStringOption(KEY_PROFILE_NAME)); //$NON-NLS-1$ //$NON-NLS-2$

		String pingFactory = getStringOption(KEY_PING_FACTORY);
		String pingFactoryPackage = getStringOption(KEY_PACKAGE_NAME);
		if (pingFactory == null || pingFactory.trim().length() == 0) {
			pingFactory = ""; //$NON-NLS-1$
		}
		if (pingFactoryPackage == null || pingFactoryPackage.trim().length() == 0) {
			pingFactoryPackage = ""; //$NON-NLS-1$
		}
		if (pingFactory.trim().length() > 0 && pingFactoryPackage.trim().length() > 0) {
			String fullClassName = pingFactoryPackage + "." + pingFactory; //$NON-NLS-1$
			element.setAttribute("pingFactory", fullClassName); //$NON-NLS-1$
		}

		String category = getStringOption(KEY_CATEGORY_ID);
		if (category != null && category.trim().length() > 0) {
			element.setAttribute("category", category); //$NON-NLS-1$

			IPluginElement categoryElement = factory.createElement(extension);
			categoryElement.setName("category"); //$NON-NLS-1$
			categoryElement.setAttribute("id", category); //$NON-NLS-1$
			categoryElement.setAttribute("name", "%category.name"); //$NON-NLS-1$ //$NON-NLS-2$
			extension.add(categoryElement);
		}

		String wizard = getStringOption(KEY_WIZARD_CLASS);
		if (wizard != null && wizard.trim().length() > 0) {
			IPluginElement wizardElement = factory.createElement(extension);
			wizardElement.setName("newWizard"); //$NON-NLS-1$
			
			String id = getStringOption(KEY_PROFILE_ID) + ".newWizard"; //$NON-NLS-1$
			wizardElement.setAttribute("id", id); //$NON-NLS-1$
			wizardElement.setAttribute("name", "%wizard.name"); //$NON-NLS-1$ //$NON-NLS-2$
			wizardElement.setAttribute("icon", "icons/new_db_element.gif"); //$NON-NLS-1$ //$NON-NLS-2$

			String fullClassName = getStringOption(KEY_PACKAGE_NAME) + "." + wizard;  //$NON-NLS-1$
			wizardElement.setAttribute("class", fullClassName); //$NON-NLS-1$
			wizardElement.setAttribute("profile", getStringOption(KEY_PROFILE_ID)); //$NON-NLS-1$
			extension.add(wizardElement);
		}

		String icon = getStringOption(KEY_ICON);
		if (icon != null && icon.trim().length() > 0) {
			element.setAttribute("icon", icon); //$NON-NLS-1$
		}

		extension.add(element);
		plugin.add(extension);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.pde.ui.templates.ITemplateSection#getUsedExtensionPoint()
	 */
	public String getUsedExtensionPoint() {
		return EXTENSION_POINT;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.ui.templates.AbstractConnectivityTemplateSection#initializeOptions()
	 */
	protected void initializeOptions()
	{
		String profileName = Messages.getString("ConnectionProfileTemplateSection.DefaultProfileName");  //$NON-NLS-1$
		String sanitizedProfileName = profileName.replaceAll(" ", ""); //$NON-NLS-1$ //$NON-NLS-2$
		sanitizedProfileName = sanitizedProfileName.replaceAll("_", ""); //$NON-NLS-1$ //$NON-NLS-2$
		sanitizedProfileName = sanitizedProfileName.replaceAll("\\.", ""); //$NON-NLS-1$ //$NON-NLS-2$
		
		String packageName = "org.mycompany." + sanitizedProfileName.toLowerCase();//$NON-NLS-1$
		initializeOption( KEY_PROFILE_NAME, profileName);
		initializeOption( KEY_PROFILE_ID, Messages.getString("ConnectionProfileTemplateSection.DefaultProfileID")); //$NON-NLS-1$
		initializeOption( KEY_CATEGORY_ID, GEN_JDBC_CATEGORY);
		initializeOption( KEY_ICON, "icons/jdbc_16.gif"); //$NON-NLS-1$
		initializeOption( KEY_PING_FACTORY, sanitizedProfileName + "PingFactory"); //$NON-NLS-1$
		initializeOption( KEY_WIZARD_CLASS, sanitizedProfileName + "Wizard"); //$NON-NLS-1$
		initializeOption( KEY_PACKAGE_NAME, packageName);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.pde.ui.templates.AbstractTemplateSection#getDependencies(java.lang.String)
	 */
	public IPluginReference[] getDependencies( String schemaVersion )
	{
		Version version101 = new Version( 1, 0, 1 );
		Version version200 = new Version( 2, 0, 0 );
		VersionRange compatible150 = new VersionRange( version101, true, version200, false );       

		return new IPluginReference[] 
		                            { new CompatiblePluginReference( 
		                            		"org.eclipse.datatools.connectivity",  //$NON-NLS-1$
		                            		compatible150.toString() ),
		                            		new CompatiblePluginReference( 
		                            				"org.eclipse.datatools.connectivity.ui",  //$NON-NLS-1$
		                            				compatible150.toString() )
		                            };
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.ui.templates.AbstractConnectivityTemplateSection#getWizardPageDescription()
	 */
	protected String getWizardPageDescription() {
		return Messages.getString("ConnectionProfileTemplateSection.WizardPageDesc"); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.ui.templates.AbstractConnectivityTemplateSection#getWizardPageTitle()
	 */
	protected String getWizardPageTitle() {
		return Messages.getString("ConnectionProfileTemplateSection.WizardPageTitle"); //$NON-NLS-1$
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
        
        // add an entry for the icons
        buildList.add( "icons/" ); //$NON-NLS-1$
        
        return (String[]) buildList.toArray( new String[ buildList.size() ] );
    }
}
