/*******************************************************************************
 * Copyright (c) 2004-2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.drivers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.drivers.models.TemplateDescriptor;
import org.eclipse.datatools.connectivity.internal.ConnectivityPlugin;

import com.ibm.icu.util.StringTokenizer;

/**
 * This class provides a management-level interface for drivers.
 * 
 * @author brianf
 */
public class DriverManager {

	private static DriverManager sInstance;

	/**
	 * Retrieve an instance of the DriverManager
	 * @return DriverManager
	 */
	public static synchronized DriverManager getInstance() {
		if (sInstance == null) {
			sInstance = new DriverManager();
		}
		return sInstance;
	}

	private DriverManager() {
		resetDefaultInstances();
	}

	/**
	 * Retrieve a DriverInstance by Id
	 * @param id ID of the driver
	 * @return DriverInstance
	 */
	public DriverInstance getDriverInstanceByID(String id) {
		XMLFileManager.setFileName(IDriverMgmtConstants.DRIVER_FILE);
		try {
			IPropertySet[] psets = XMLFileManager.loadPropertySets();
			if (psets.length > 0) {
				for (int i = 0; i < psets.length; i++) {
					IPropertySet pset = psets[i];
					if (pset.getID().equals(id)) {
						return new DriverInstance(pset);
					}
				}
			}
		}
		catch (CoreException e) {
			ConnectivityPlugin.getDefault().log(e);
		}
		return null;
	}

	/**
	 * Retrieve a DriverInstance by name.
	 * @param name Name of the driver
	 * @return Driver Instance
	 */
	public DriverInstance getDriverInstanceByName(String name) {
		XMLFileManager.setFileName(IDriverMgmtConstants.DRIVER_FILE);
		try {
			IPropertySet[] psets = XMLFileManager.loadPropertySets();
			if (psets.length > 0) {
				for (int i = 0; i < psets.length; i++) {
					IPropertySet pset = psets[i];
					if (pset.getName().equals(name)) {
						return new DriverInstance(pset);
					}
				}
			}
		}
		catch (CoreException e) {
			ConnectivityPlugin.getDefault().log(e);
		}
		return null;
	}

	/**
	 * Return a comma-delimited list of all jars for all drivers.
	 * @return String
	 */
	public String getFullJarList() {
		XMLFileManager.setFileName(IDriverMgmtConstants.DRIVER_FILE);
		try {
			IPropertySet[] psets = XMLFileManager.loadPropertySets();
			if (psets.length > 0) {
				String fullList = ""; //$NON-NLS-1$
				for (int i = 0; i < psets.length; i++) {
					IPropertySet pset = psets[i];
					if (pset.getBaseProperties() != null) {
						if (pset.getBaseProperties().getProperty(
								IDriverMgmtConstants.PROP_DEFN_JARLIST) != null) {
							String jarlist = pset
									.getBaseProperties()
									.getProperty(
											IDriverMgmtConstants.PROP_DEFN_JARLIST)
									.trim();
							fullList = fullList + jarlist
									+ IDriverMgmtConstants.PATH_DELIMITER;
						}
					}
				}
				if (fullList
						.substring(fullList.length() - 1, fullList.length())
						.equals(IDriverMgmtConstants.PATH_DELIMITER)) {
					fullList = fullList.substring(0, fullList.length() - 1);
				}

				String[] paths = parseString(fullList,
						IDriverMgmtConstants.PATH_DELIMITER);
				ArrayList list = new ArrayList();
				for (int i = 0; i < paths.length; i++) {
					File testFile = new File(paths[i]);
					if (testFile.exists()) {
						if (!list.contains(paths[i]))
							list.add(paths[i]);
					}
				}

				String newList = ""; //$NON-NLS-1$
				Iterator iter = list.iterator();
				while (iter.hasNext()) {
					newList = newList + iter.next()
							+ IDriverMgmtConstants.PATH_DELIMITER;
				}
				if (newList.substring(newList.length() - 1, newList.length())
						.equals(IDriverMgmtConstants.PATH_DELIMITER)) {
					newList = newList.substring(0, newList.length() - 1);
				}

				return newList;
			}
		}
		catch (CoreException e) {
			ConnectivityPlugin.getDefault().log(e);
		}
		return null;
	}

	/**
	 * Return an array of all jars for all drivers.
	 * @return String[]
	 */
	public String[] getFullJarListAsArray() {
		if (getFullJarList() != null) {
			if (getFullJarList().length() == 0)
				return new String[0];
			String[] paths = parseString(getFullJarList(),
					IDriverMgmtConstants.PATH_DELIMITER);
			return paths;
		}
		return null;
	}

	/**
	 * Returns an array of valid driver instances
	 * @return DriverInstance[]
	 */
	public DriverInstance[] getValidDriverInstances() {
		DriverInstance[] array = new DriverInstance[0];
		XMLFileManager.setFileName(IDriverMgmtConstants.DRIVER_FILE);
		try {
			IPropertySet[] psets = XMLFileManager.loadPropertySets();
			if (psets.length > 0) {
				ArrayList list = new ArrayList();
				for (int i = 0; i < psets.length; i++) {
					IPropertySet pset = psets[i];
					DriverInstance ndi = new DriverInstance(pset);
					DriverValidator validator = new DriverValidator(ndi);
					if (validator.isValid())
						list.add(ndi);
				}

				array = new DriverInstance[list.size()];
				for (int i = 0; i < list.size(); i++) {
					array[i] = (DriverInstance) list.get(i);
				}

				return array;
			}
		}
		catch (CoreException e) {
			ConnectivityPlugin.getDefault().log(e);
		}
		return array;
	}

	/**
	 * Create a new DriverInstance based on the incoming templateID,
	 * driver name, and jar list.
	 * @param templateID String ID of the template
	 * @param name String name to give the driver
	 * @param jarList String jar list to give the driver
	 * @return DriverInstance
	 */
	public DriverInstance createNewDriverInstance(String templateID,
			String name, String jarList) {
		if (templateID == null) return null;
		if (name == null) return null;
		if (jarList == null) return null;
		
		IPropertySet pset = createDefaultInstance(templateID);
		pset.setName(name);
		String prefix = DriverMgmtMessages
				.getString("NewDriverDialog.text.id_prefix"); //$NON-NLS-1$
		String id = prefix + name;
		pset.setID(id);
		Properties props = pset.getBaseProperties();
		props.setProperty(IDriverMgmtConstants.PROP_DEFN_JARLIST, jarList);
		addDriverInstance(pset);
		return getDriverInstanceByID(pset.getID());
	}

	/**
	 * Removes a driver instance based on the id.
	 * @param id String ID of the driver instance
	 * @return true on success, false otherwise
	 */
	public boolean removeDriverInstance(String id) {
		boolean rtnFlag = false;
		if (getDriverInstanceByID(id) != null) {
			XMLFileManager.setFileName(IDriverMgmtConstants.DRIVER_FILE);
			try {
				IPropertySet[] psets = XMLFileManager.loadPropertySets();
				if (psets.length > 0) {
					IPropertySet[] newPsets = new IPropertySet[psets.length - 1];
					int counter = 0;
					for (int i = 0; i < psets.length; i++) {
						IPropertySet pset = psets[i];
						if (pset.getID().equals(id)) {
							rtnFlag = true;
						}
						else {
							newPsets[counter] = pset;
							counter++;
						}
					}
					if (rtnFlag == true) {
						XMLFileManager.saveNamedPropertySet(newPsets);
					}
				}
			}
			catch (CoreException e) {
				ConnectivityPlugin.getDefault().log(e);
			}
		}
		return rtnFlag;
	}

	/**
	 * Adds a new driver instance to the Drivers file
	 * @param pset IPropertySet
	 */
	private void addDriverInstance(IPropertySet pset) {
		XMLFileManager.setFileName(IDriverMgmtConstants.DRIVER_FILE);
		try {
			IPropertySet[] psets = XMLFileManager.loadPropertySets();
			IPropertySet[] newPsets = new IPropertySet[psets.length + 1];
			for (int i = 0; i < psets.length; i++) {
				newPsets[i] = psets[i];
			}
			newPsets[psets.length] = pset;

			XMLFileManager.saveNamedPropertySet(newPsets);
		}
		catch (CoreException e) {
			ConnectivityPlugin.getDefault().log(e);
		}
	}

	/**
	 * Parses the incoming string by the token.
	 * @param str_list String list
	 * @param token Token to use to break up the string
	 * @return String array 
	 */
	private String[] parseString(String str_list, String token) {
		StringTokenizer tk = new StringTokenizer(str_list, token);
		String[] pieces = new String[tk.countTokens()];
		int index = 0;
		while (tk.hasMoreTokens())
			pieces[index++] = tk.nextToken();
		return pieces;
	}

	/**
	 * Creates any default driver template instances that need to be created.
	 * This is when the plug-in is loaded.
	 */
	public void resetDefaultInstances() {

		// Start building a list
		ArrayList psets_list = new ArrayList();

		// Grab all the driver templates
		TemplateDescriptor types[] = TemplateDescriptor
				.getDriverTemplateDescriptors();

		// prep the file for reading
		XMLFileManager.setFileName(IDriverMgmtConstants.DRIVER_FILE);

		// Now grab all the driver instances from the file
		IPropertySet[] psets = new IPropertySet[0];
		try {
			psets = XMLFileManager.loadPropertySets();
		}
		catch (CoreException e) {
			ConnectivityPlugin.getDefault().log(e);
		}

		// now process the templates and see if we need to
		// create any instances
		for (int i = 0; i < types.length; i++) {
			TemplateDescriptor type = types[i];

			// do we need to create a default instance?
			boolean alreadyExists = false;
			if (psets.length > 0) {
				for (int j = 0; j < psets.length; j++) {
					IPropertySet pset = psets[j];
					if (pset.getBaseProperties().getProperty(
							IDriverMgmtConstants.PROP_DEFN_TYPE) != null) {
						String category = pset.getBaseProperties().getProperty(
								IDriverMgmtConstants.PROP_DEFN_TYPE);
						if (category.equalsIgnoreCase(type.getId())) {
							alreadyExists = true;
							psets_list.add(pset);
						}
					}
				}
			}

			// if we need to create one and it doesn't already
			// exist, create one and add it to the list.
			if (type.getCreateDefaultFlag() && !alreadyExists) {
				IPropertySet newPset = createDefaultInstance(type);
				psets_list.add(newPset);
			}
		}

		// Now update the file
		if (!psets_list.isEmpty()) {
			Object[] objs = psets_list.toArray();
			IPropertySet[] propsets = new IPropertySet[objs.length];
			for (int i = 0; i < objs.length; i++) {
				propsets[i] = (IPropertySet) objs[i];
			}
			try {
				XMLFileManager.saveNamedPropertySet(propsets);
			}
			catch (CoreException e) {
				ConnectivityPlugin.getDefault().log(e);
			}
		}
	}

	/**
	 * Creates a default instance of the driver.
	 * @param id String ID of driver
	 * @return IPropertySet
	 */
	public IPropertySet createDefaultInstance(String id) {
		TemplateDescriptor template = TemplateDescriptor
				.getDriverTemplateDescriptor(id);
		IPropertySet pset = createInstance(template);
		return pset;
	}

	/**
	 * Creates an empty instance of the template
	 * @param template IPropertySet
	 * @return IPropertySet
	 */
	private IPropertySet createInstance(TemplateDescriptor template) {
		return createDefaultInstance(template, true);
	}

	/**
	 * Create a default instance of a driver template.
	 * 
	 * @param template
	 * @return IPropertySet
	 */
	private IPropertySet createDefaultInstance(TemplateDescriptor template) {
		return createDefaultInstance(template, false);
	}

	/**
	 * Creates a default instance of the template
	 * @param template TemplateDescriptor to use
	 * @param override Boolean flag indicating if the template should be overridden
	 * @return IPropertySet object
	 */
	private IPropertySet createDefaultInstance(TemplateDescriptor template,
			boolean override) {

		if (template != null && (template.getCreateDefaultFlag() || override)) {

			// generate the id and name for the new instance
			String prefix = DriverMgmtMessages
					.getString("NewDriverDialog.text.id_prefix"); //$NON-NLS-1$
			String suffix = DriverMgmtMessages
					.getString("DriverMgmtPlugin.default_instance_suffix"); //$NON-NLS-1$

			String id = prefix + template.getId();
			String name = template.getName() + " " + suffix;//$NON-NLS-1$

			// Now create the property set
			PropertySetImpl propset = new PropertySetImpl(id, template
					.getName());
			propset.setID(id);
			propset.setName(name); //$NON-NLS-1$

			// create the base properties
			Properties props = new Properties();

			String jarList = updatePluginJarList(template);

			props.setProperty(IDriverMgmtConstants.PROP_DEFN_JARLIST, jarList);
			props.setProperty(IDriverMgmtConstants.PROP_DEFN_TYPE, template
					.getId());

			// and add all the template properties
			IConfigurationElement[] templateprops = template.getProperties();
			if (props != null && templateprops.length > 0) {
				for (int i = 0; i < templateprops.length; i++) {
					IConfigurationElement prop = templateprops[i];
					String propid = prop.getAttribute("id"); //$NON-NLS-1$
					String propvalue = prop.getAttribute("value"); //$NON-NLS-1$
					props.setProperty(propid, propvalue == null ? new String()
						: propvalue);
				}
			}

			// set the base properties
			propset.setBaseProperties(props);

			// and set it free!
			return propset;
		}

		return null;
	}

	/**
	 * Updates the jar list if it contains any [PLUGIN] tags, replacing them
	 * with the actual path referenced. For instance, the install location in 
	 * the file system for the given plugin.
	 * @param template TemplateDescriptor
	 * @return String Updated jar list
	 */
	public String updatePluginJarList(TemplateDescriptor template) {
		String jarList = template.getJarList();

		if (jarList.indexOf("[") > -1) { //$NON-NLS-1$
			int index = jarList.indexOf("["); //$NON-NLS-1$
			while (index > -1) {
				String toReplace = jarList.substring(index, jarList.indexOf(
						"]", index) + 1); //$NON-NLS-1$
				String pluginId = null;
				if (toReplace.toUpperCase().equals("[PLUGIN]")) { //$NON-NLS-1$
					pluginId = template.getElement().getContributor().getName();
				}
				else {
					pluginId = toReplace.substring(1, toReplace.length() - 1);
				}
				String restOfPath = null;
				if (jarList.indexOf(",", index) > 0) { //$NON-NLS-1$
					restOfPath = jarList
							.substring(
									jarList.indexOf("]", index) + 1, jarList.indexOf(",", index)); //$NON-NLS-1$ //$NON-NLS-2$
				}
				else {
					restOfPath = jarList.substring(
							jarList.indexOf("]", index) + 1, jarList.length()); //$NON-NLS-1$
				}

				if (Platform.getBundle(pluginId) != null) {
					String entry = File.separator + restOfPath + File.separator;
					URL url = Platform.getBundle(pluginId).getEntry(entry);
					if (url != null) {
						try {
							url = FileLocator.toFileURL(url);
							IPath path = new Path(url.getFile());
							int totalLength = toReplace.length()
									+ restOfPath.length();
							jarList = jarList.substring(0, index)
									+ path.toOSString()
									+ jarList.substring(index + totalLength,
											jarList.length());
						}
						catch (IOException e) {
							String[] strs = new String[] { pluginId
									+ restOfPath};
							System.err.println(DriverMgmtMessages.format(
									"DriverMgmtPlugin.FileMissing", strs)); //$NON-NLS-1$
							ConnectivityPlugin
									.getDefault()
									.log(
											DriverMgmtMessages
													.format(
															"DriverMgmtPlugin.FileMissing", strs)); //$NON-NLS-1$
						}
					}
					else {
						String[] strs = new String[] { pluginId + restOfPath};
						String msg = DriverMgmtMessages.format(
								"DriverMgmtPlugin.FileMissing", strs);//$NON-NLS-1$
						System.err.println(msg);
						ConnectivityPlugin.getDefault().log(msg);
					}
				}
				else {
					String[] strs = new String[] { pluginId};
					System.err.println(DriverMgmtMessages.format(
							"DriverMgmtPlugin.BundleMissing", strs)); //$NON-NLS-1$
					ConnectivityPlugin.getDefault().log(
							DriverMgmtMessages.format(
									"DriverMgmtPlugin.BundleMissing", strs)); //$NON-NLS-1$
				}

				index = jarList.indexOf(",", index); //$NON-NLS-1$
				if (index > 0)
					index++;
			}
		}

		return jarList;
	}

}
