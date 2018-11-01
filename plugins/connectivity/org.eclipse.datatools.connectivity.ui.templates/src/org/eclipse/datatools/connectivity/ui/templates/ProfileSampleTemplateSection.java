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
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.osgi.service.resolver.VersionRange;
import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.core.plugin.IPluginElement;
import org.eclipse.pde.core.plugin.IPluginExtension;
import org.eclipse.pde.core.plugin.IPluginModelFactory;
import org.eclipse.pde.core.plugin.IPluginReference;
import org.osgi.framework.Version;

/**
 * PDE template section for defining a new connection profile plug-in
 * based on the sample.
 * 
 * @author brianf
 *
 */
public class ProfileSampleTemplateSection extends AbstractConnectivityTemplateSection {

	private static final String PROFILE_PROPERTY_ID = "org.eclipse.datatools.profile.property.id"; //$NON-NLS-1$
	private static final String GENERIC_JDBC_CONN_FACTORY = "org.eclipse.datatools.connectivity.db.generic.JDBCConnectionFactory"; //$NON-NLS-1$
	private static final String EXTENSION_POINT = "org.eclipse.datatools.connectivity.connectionProfile"; //$NON-NLS-1$
	private static final String PROP_EXTENSION_POINT = "org.eclipse.ui.propertyPages"; //$NON-NLS-1$
	private static final String KEY_PROFILE_ID = "profileID"; //$NON-NLS-1$
	private static final String KEY_PROFILE_NAME = "profileName"; //$NON-NLS-1$
	private static final String KEY_ICON = "icon"; //$NON-NLS-1$
	private static final String KEY_WIZARD_CLASS = "newWizardClass"; //$NON-NLS-1$
	private static final String KEY_WIZARD_PAGE_CLASS = "newWizardPageClass"; //$NON-NLS-1$
	private static final String KEY_PROPERTY_PAGE_CLASS = "propertyPageClass"; //$NON-NLS-1$
	
	private static final String GEN_JDBC_CATEGORY = "org.eclipse.datatools.connectivity.db.category"; //$NON-NLS-1$

	/**
	 * Constructor
	 */
	public ProfileSampleTemplateSection() {
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
		addOptionalOption(KEY_ICON, Messages.getString("ConnectionProfileTemplateSection.IconPathLabel")); //$NON-NLS-1$
		addOptionalOption(KEY_PACKAGE_NAME, Messages.getString("ConnectionProfileTemplateSection.PackageNameLabel")); //$NON-NLS-1$
		addOptionalOption(KEY_WIZARD_CLASS, Messages.getString("ConnectionProfileTemplateSection.WizardClassLabel")); //$NON-NLS-1$
		addOptionalOption(KEY_WIZARD_PAGE_CLASS, Messages.getString("ProfileSampleTemplateSection.WizardPageClassLabel")); //$NON-NLS-1$
		addOptionalOption(KEY_PROPERTY_PAGE_CLASS, Messages.getString("ProfileSampleTemplateSection.PropertyPageClassLabel")); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see org.eclipse.pde.ui.templates.OptionTemplateSection#getSectionId()
	 */
	public String getSectionId() {
		return "profileSample";  //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see org.eclipse.pde.ui.templates.AbstractTemplateSection#updateModel(org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected void updateModel(IProgressMonitor monitor) throws CoreException {
		/*
		 * id, category, name, configurationType, icon, maintainConnection, 
		 * pingFactory, propertiesPersistenceHook
		 */
		IPluginExtension extension = 
			createExtension(EXTENSION_POINT, true);

		IPluginExtension proppageExtension = 
			createExtension(PROP_EXTENSION_POINT, true);

        IPluginBase plugin = model.getPluginBase();
        if ( ! extension.isInTheModel() )
            plugin.add( extension );
        if ( ! proppageExtension.isInTheModel() )
            plugin.add( proppageExtension );

		IPluginModelFactory factory = model.getPluginFactory();

		IPluginElement element = factory.createElement(extension);
		element.setName("connectionProfile"); //$NON-NLS-1$
		element.setAttribute("id", getStringOption(KEY_PROFILE_ID)); //$NON-NLS-1$

		element.setAttribute("name", "%connection.profile.name"); //getStringOption(KEY_PROFILE_NAME)); //$NON-NLS-1$ //$NON-NLS-2$

		element.setAttribute("pingFactory", GENERIC_JDBC_CONN_FACTORY); //$NON-NLS-1$
		element.setAttribute("category", GEN_JDBC_CATEGORY); //$NON-NLS-1$

		String wizard = getStringOption(KEY_WIZARD_CLASS);
		if (wizard != null && wizard.trim().length() > 0) {
			IPluginElement wizardElement = factory.createElement(extension);
			wizardElement.setName("newWizard"); //$NON-NLS-1$

			String id = getStringOption(KEY_PROFILE_ID) + ".newWizard"; //$NON-NLS-1$
			wizardElement.setAttribute("id", id); //$NON-NLS-1$
			wizardElement.setAttribute("name", "%wizard.name"); //$NON-NLS-1$ //$NON-NLS-2$

			String fullClassName = getStringOption(KEY_PACKAGE_NAME) + "." + wizard;  //$NON-NLS-1$
			wizardElement.setAttribute("class", fullClassName); //$NON-NLS-1$
			wizardElement.setAttribute("profile", getStringOption(KEY_PROFILE_ID)); //$NON-NLS-1$
			wizardElement.setAttribute("icon", "icons/new_db_element.gif"); //$NON-NLS-1$ //$NON-NLS-2$
			extension.add(wizardElement);
		}

		String icon = getStringOption(KEY_ICON);
		if (icon != null && icon.trim().length() > 0) {
			element.setAttribute("icon", icon); //$NON-NLS-1$
		}

		extension.add(element);

		IPluginElement proppageElement = factory.createElement(proppageExtension);
		proppageElement.setName("page"); //$NON-NLS-1$
		proppageElement.setAttribute("id", getStringOption(KEY_PROFILE_ID) + ".propertyPage"); //$NON-NLS-1$ //$NON-NLS-2$
		proppageElement.setAttribute("name", "%connection.profile.proppage.name"); //$NON-NLS-1$ //$NON-NLS-2$

		String fullClassName = getStringOption(KEY_PACKAGE_NAME) + "." + getStringOption(KEY_PROPERTY_PAGE_CLASS);  //$NON-NLS-1$
		proppageElement.setAttribute("class", fullClassName); //$NON-NLS-1$

		IPluginElement filterElement = factory.createElement(proppageElement);
		filterElement.setName("filter"); //$NON-NLS-1$
		filterElement.setAttribute("name", PROFILE_PROPERTY_ID); //$NON-NLS-1$
		filterElement.setAttribute("value", getStringOption(KEY_PROFILE_ID)); //$NON-NLS-1$
		proppageElement.add(filterElement);

		IPluginElement enabledWhenElement = factory.createElement(proppageElement);
		enabledWhenElement.setName("enabledWhen"); //$NON-NLS-1$
		
		IPluginElement instanceOfElement = factory.createElement(enabledWhenElement);
		instanceOfElement.setName("instanceof"); //$NON-NLS-1$
		instanceOfElement.setAttribute("value", IConnectionProfile.class.getName()); //$NON-NLS-1$
		enabledWhenElement.add(instanceOfElement);

		proppageElement.add(enabledWhenElement);

		proppageExtension.add(proppageElement);
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
		String propertyPageName = sanitizedProfileName + "PropertyPage"; //$NON-NLS-1$
		String wizardName = sanitizedProfileName + "Wizard"; //$NON-NLS-1$
		String wizardPageName = sanitizedProfileName + "WizardPage"; //$NON-NLS-1$
		
		initializeOption( KEY_PROFILE_NAME, profileName);
		initializeOption( KEY_PROFILE_ID, Messages.getString("ConnectionProfileTemplateSection.DefaultProfileID")); //$NON-NLS-1$
		initializeOption( KEY_ICON, "icons/jdbc_16.gif"); //$NON-NLS-1$
		initializeOption( KEY_PACKAGE_NAME, packageName);
		initializeOption ( KEY_PROPERTY_PAGE_CLASS, propertyPageName);
		initializeOption ( KEY_WIZARD_CLASS, wizardName);
		initializeOption ( KEY_WIZARD_PAGE_CLASS, wizardPageName);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.pde.ui.templates.AbstractTemplateSection#getDependencies(java.lang.String)
	 */
	public IPluginReference[] getDependencies( String schemaVersion )
	{
		Version version100 = new Version( 1, 0, 0 );
		Version version101 = new Version( 1, 0, 1 );
		Version version200 = new Version( 2, 0, 0 );
		VersionRange compatible150 = new VersionRange( version101, true, version200, false );       
		VersionRange compatible100 = new VersionRange( version100, true, version200, false );       

		return new IPluginReference[] 
		                            { new CompatiblePluginReference( 
		                            		"org.eclipse.datatools.connectivity",  //$NON-NLS-1$
		                            		compatible150.toString() ),
		                              new CompatiblePluginReference( 
		                            		"org.eclipse.datatools.connectivity.ui",  //$NON-NLS-1$
		                            		compatible150.toString() ),
		                              new CompatiblePluginReference( 
		                            		"org.eclipse.datatools.connectivity.db.generic",  //$NON-NLS-1$
		                            		compatible100.toString() ),
		                              new CompatiblePluginReference( 
		                            		"org.eclipse.datatools.connectivity.db.generic.ui",  //$NON-NLS-1$
		                            		compatible100.toString() )
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
