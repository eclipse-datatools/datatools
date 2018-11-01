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
import java.util.Iterator;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.osgi.service.resolver.VersionRange;
import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.core.plugin.IPluginElement;
import org.eclipse.pde.core.plugin.IPluginExtension;
import org.eclipse.pde.core.plugin.IPluginModelFactory;
import org.eclipse.pde.core.plugin.IPluginReference;
import org.osgi.framework.Version;

/**
 * PDE template for Driver Templates 
 * 
 * @author brianf
 *
 */
public class DriverTemplateSection extends AbstractConnectivityTemplateSection {

    private static final String EXTENSION_POINT = "org.eclipse.datatools.connectivity.driverExtension"; //$NON-NLS-1$
	private static final String KEY_DRIVER_ID = "driverID"; //$NON-NLS-1$
    private static final String KEY_DRIVER_NAME = "driverName"; //$NON-NLS-1$
    private static final String KEY_DRIVER_DESCRIPTION = "driverDesc"; //$NON-NLS-1$
    private static final String KEY_DRIVER_CATEGORY = "driverCategory"; //$NON-NLS-1$
    private static final String KEY_DRIVER_JARLIST = "driverJarList"; //$NON-NLS-1$
    private static final String KEY_DRIVER_CREATEDEFAULT = "driverCreateDefault"; //$NON-NLS-1$
    private static final String KEY_DRIVER_EMPTYJARLISTOK = "driverEmptyJarListIsOK"; //$NON-NLS-1$
    private static final String KEY_DRIVER_PROPERTIES = "properties"; //$NON-NLS-1$
    
    /**
     * Constructor
     */
    public DriverTemplateSection() {
        setPageCount( 2 );
        createOptions();       
        initializeOptions();
	}

	/**
	 * Creates the options for the template UI
	 */
	private void createOptions() {
		addRequiredOption(KEY_DRIVER_ID, Messages.getString("DriverTemplateSection.DriverTemplateIDLabel")); //$NON-NLS-1$
		addRequiredOption(KEY_DRIVER_NAME, Messages.getString("DriverTemplateSection.DriverTemplateNameLabel")); //$NON-NLS-1$
		addRequiredOption(KEY_DRIVER_JARLIST, Messages.getString("DriverTemplateSection.JarListLabel")); //$NON-NLS-1$
		addOptionalOption(KEY_DRIVER_DESCRIPTION, Messages.getString("DriverTemplateSection.DescriptionLabel")); //$NON-NLS-1$
		addOptionalOption(KEY_DRIVER_CATEGORY, Messages.getString("DriverTemplateSection.CategoryLabel")); //$NON-NLS-1$

		addOption(KEY_DRIVER_CREATEDEFAULT, Messages.getString("DriverTemplateSection.CreateDefaultLabel"), //$NON-NLS-1$
				new String [][] {
					{"true", "true"}, //$NON-NLS-1$ //$NON-NLS-2$
					{"false", "false"}}, //$NON-NLS-1$ //$NON-NLS-2$
					"false", 0); //$NON-NLS-1$
		addOption(KEY_DRIVER_EMPTYJARLISTOK, Messages.getString("DriverTemplateSection.EmptyJarListLabel"), //$NON-NLS-1$
				new String [][] {
						{"true", "true"}, //$NON-NLS-1$ //$NON-NLS-2$
						{"false", "false"}}, //$NON-NLS-1$ //$NON-NLS-2$
						"false", 0); //$NON-NLS-1$

		registerOption(new PropertyTemplateOption(this, KEY_DRIVER_PROPERTIES, Messages.getString("DriverTemplateSection.PropertiesLabel")), "", 1); //$NON-NLS-1$ //$NON-NLS-2$
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.ui.templates.AbstractConnectivityTemplateSection#getWizardPageDescription()
	 */
	protected String getWizardPageDescription() {
		return Messages.getString("DriverTemplateSection.WizardPageDesc"); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.ui.templates.AbstractConnectivityTemplateSection#getWizardPageTitle()
	 */
	protected String getWizardPageTitle() {
		return Messages.getString("DriverTemplateSection.WizardPageTitle"); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.ui.templates.AbstractConnectivityTemplateSection#initializeOptions()
	 */
	protected void initializeOptions() {
		initializeOption(KEY_DRIVER_ID, Messages.getString("DriverTemplateSection.DefaultDriverID")); //$NON-NLS-1$
		initializeOption(KEY_DRIVER_NAME, Messages.getString("DriverTemplateSection.DefaultDriverName")); //$NON-NLS-1$
		initializeOption(KEY_DRIVER_JARLIST, Messages.getString("DriverTemplateSection.DefaultDriverJarList")); //$NON-NLS-1$
		initializeOption(KEY_DRIVER_DESCRIPTION, Messages.getString("DriverTemplateSection.DefaultDriverDescription")); //$NON-NLS-1$
		initializeOption(KEY_DRIVER_CATEGORY, ""); //$NON-NLS-1$
		initializeOption(KEY_DRIVER_CREATEDEFAULT, "false"); //$NON-NLS-1$
		initializeOption(KEY_DRIVER_EMPTYJARLISTOK, "false"); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see org.eclipse.pde.ui.templates.OptionTemplateSection#getSectionId()
	 */
	public String getSectionId() {
		return "driverTemplate"; //$NON-NLS-1$
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
			createExtension(getUsedExtensionPoint(), true);
		
		IPluginElement element = factory.createElement(extension);
		element.setName("driverTemplate"); //$NON-NLS-1$
		element.setAttribute("id", getStringOption(KEY_DRIVER_ID)); //$NON-NLS-1$

		String category = getStringOption(KEY_DRIVER_CATEGORY);
		if (category != null && category.trim().length() > 0) {
			element.setAttribute("parentCategory", category); //$NON-NLS-1$

			IPluginElement categoryElement = factory.createElement(extension);
			categoryElement.setName("category"); //$NON-NLS-1$
			categoryElement.setAttribute("id", category); //$NON-NLS-1$
			categoryElement.setAttribute("name", "%driverCategory.name"); //$NON-NLS-1$ //$NON-NLS-2$
			extension.add(categoryElement);
		}
		element.setAttribute("name", "%driverTemplate.name"); //$NON-NLS-1$ //$NON-NLS-2$
		
		String description = getStringOption(KEY_DRIVER_DESCRIPTION);
		if (description == null || description.trim().length() == 0) {
			description = ""; //$NON-NLS-1$
		}
		else
			element.setAttribute("description", "%driverTemplate.description"); //$NON-NLS-1$ //$NON-NLS-2$

		element.setAttribute("jarList", getStringOption(KEY_DRIVER_JARLIST)); //$NON-NLS-1$
		String value = (String) getValue(KEY_DRIVER_CREATEDEFAULT);
		element.setAttribute("createDefault", value); //$NON-NLS-1$
		value = (String) getValue(KEY_DRIVER_EMPTYJARLISTOK);
		element.setAttribute("emptyJarListIsOK", value); //$NON-NLS-1$
		
		if (getValue(KEY_DRIVER_PROPERTIES) != null) {
			ArrayList properties = (ArrayList) getValue(KEY_DRIVER_PROPERTIES);
			if (!properties.isEmpty()) {
				
				IPluginElement propsElement = factory.createElement(element);
				propsElement.setName("properties"); //$NON-NLS-1$
				Iterator iter = properties.iterator();
				boolean addedProperties = false;
				while (iter.hasNext()) {
					PropertyObject property = (PropertyObject) iter.next();
					if (property != null) {
						addedProperties = true;
						IPluginElement propElement = factory.createElement(propsElement);
						propElement.setName("property"); //$NON-NLS-1$
						propElement.setAttribute("id", property.getPropertyID()); //$NON-NLS-1$
						propElement.setAttribute("name", property.getPropertyName()); //$NON-NLS-1$
						if (property.getPropertyDescription() != null && property.getPropertyDescription().trim().length() > 0)
							propElement.setAttribute("description", property.getPropertyDescription()); //$NON-NLS-1$
						propElement.setAttribute("value", property.getPropertyValue()); //$NON-NLS-1$
						propElement.setAttribute("visible", property.getPropertyVisible()); //$NON-NLS-1$
						propElement.setAttribute("required", property.getPropertyRequired()); //$NON-NLS-1$
						if (property.getPropertyCategory() != null && property.getPropertyCategory().trim().length() > 0)
							propElement.setAttribute("category", property.getPropertyCategory()); //$NON-NLS-1$
						propsElement.add(propElement);
					}
				}
				if (addedProperties)
					element.add(propsElement);
			}
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
	 * @see org.eclipse.datatools.connectivity.ui.templates.AbstractConnectivityTemplateSection#addPages(org.eclipse.jface.wizard.Wizard)
	 */
	public void addPages(Wizard wizard) {
		WizardPage page0 = createPage(0);
		page0.setTitle( getWizardPageTitle() ); 
		page0.setDescription( getWizardPageDescription() );  
		wizard.addPage(page0);

		WizardPage page1 = createPage(1);
		page1.setTitle( getWizardPageTitle() ); 
		page1.setDescription( getWizardPageDescription() );  
		wizard.addPage(page1);
		markPagesAdded();
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
		                            };
	}
}
