/*******************************************************************************
 * Copyright (c) 2004-2011 Sybase, Inc. and others.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.drivers;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.datatools.connectivity.drivers.models.OverrideTemplateDescriptor;
import org.eclipse.datatools.connectivity.drivers.models.TemplateDescriptor;
import org.eclipse.datatools.connectivity.internal.ConnectivityPlugin;

import com.ibm.icu.util.StringTokenizer;

/**
 * Validates a driver instance.
 * 
 * @author brianf
 */
public class DriverValidator {

	// template instance
	private TemplateDescriptor mTemplate;

	// driver instance
	private IPropertySet mInstance;

	// Message to pass back
	private String mMessage;

	/**
	 * Constructor
	 * @param template
	 * @param instance
	 */
	public DriverValidator(TemplateDescriptor template, IPropertySet instance) {
		this.mTemplate = template;
		this.mInstance = instance;
	}

	/**
	 * Driver validator constructor 
	 * @param driverInstance
	 */
	public DriverValidator(DriverInstance driverInstance) {
		this.mTemplate = driverInstance.getTemplate();
		this.mInstance = driverInstance.getPropertySet();
	}

	/**
	 * Return the error message.
	 * 
	 * @return String of error message
	 */
	public String getMessage() {
		return this.mMessage;
	}

	/**
	 * Return true/false if the driver instance is valid
	 * 
	 * @return boolean true if valid, false otherwise
	 */
	public boolean isValid() {
		return isValid(true);
	}
	
	/**
	 * Checks for validity and adds markers if flag is set
	 * @param registerMarkers
	 * @return
	 */
	public boolean isValid ( boolean registerMarkers ) {
		DriverInstance instance = new DriverInstance(this.mInstance);
		removeOldProblemMarkers(instance.getName());
		this.mMessage = null;
		boolean flag = true;
		flag = validateJarListFiles();
		if (flag)
			flag = validateProperties();
		if (!flag && registerMarkers)
			addProblemMarker(instance.getName(), getMessage());
		return flag;
	}

	/*
	 * Validate the properties
	 */
	private boolean validateProperties() {
		boolean flag = true;
		DriverInstance instance = new DriverInstance(this.mInstance);

		IConfigurationElement[] templateProps = this.mTemplate.getProperties();
		for (int i = 0; i < templateProps.length; i++) {
			IConfigurationElement element = templateProps[i];
			String id = element.getAttribute("id");//$NON-NLS-1$
			OverrideTemplateDescriptor[] otds = 
				OverrideTemplateDescriptor.getByDriverTemplate(this.mTemplate.getId());
			String name = element.getAttribute("name"); //$NON-NLS-1$
			if (otds != null && otds.length > 0) {
				String temp =
					otds[0].getPropertyNameFromId(id);
				if (temp != null && temp.length() > 0)
					name = temp;
			}
			String required = element.getAttribute("required"); //$NON-NLS-1$
			if (otds != null && otds.length > 0) {
				String temp =
					otds[0].getPropertyRequiredFromId(id);
				if (temp != null && temp.length() > 0)
					required = temp;
			}
			boolean propRequired = true;
			if (required != null && required.equals("false")) //$NON-NLS-1$
				propRequired = false;
			String value = instance.getNamedPropertyByID(id);
			if (otds != null && otds.length > 0) {
				String temp =
					otds[0].getPropertyValueFromId(id);
				if (temp != null && temp.length() > 0)
					value = temp;
			}
			boolean exists = (value != null);
			boolean notEmpty = false;
			if (exists)
				notEmpty = (value.length() > 0);
			if (propRequired) {
				if (!(exists && notEmpty)) {
					flag = false;
					this.mMessage = DriverMgmtMessages
							.format(
									"DriverValidator.msg.missing_required_property", new String[] { name}); //$NON-NLS-1$ 
					break;
				}
			}
		}

		return flag;
	}

	/*
	 * Validate the jar list files
	 */
	protected boolean validateJarListFiles() {
		boolean flag = true;

		boolean emptyJarListIsOK = this.mTemplate.getEmptyJarListIsOKFlag();

		if (emptyJarListIsOK)
			return flag;

		Properties baseProps = this.mInstance.getBaseProperties();

		if (baseProps != null
				&& baseProps
						.getProperty(IDriverMgmtConstants.PROP_DEFN_JARLIST) != null) {
			String jarList = baseProps
					.getProperty(IDriverMgmtConstants.PROP_DEFN_JARLIST);

			if (jarList != null) {
				if (jarList.length() > 0) {
					String[] jarListArray = parseString(jarList);
					for (int i = 0; i < jarListArray.length; i++) {
						String path = jarListArray[i];

						File testFile = new File(path);
						if (!testFile.exists()) {
							flag = false;
							this.mMessage = DriverMgmtMessages
									.format(
											"DriverValidator.msg.jarfile_not_found", new String[] { path}); //$NON-NLS-1$
							break;
						}
					}
				}
				else {
					this.mMessage = DriverMgmtMessages
							.getString("DriverValidator.msg.empty_jar_list"); //$NON-NLS-1$
					flag = false;
				}
			}
		}
		else {
			this.mMessage = DriverMgmtMessages
					.getString("DriverValidator.msg.empty_jar_list"); //$NON-NLS-1$
			flag = false;
		}

		return flag;
	}

	/*
	 * Parse the string
	 */
	private String[] parseString(String str_list) {
		StringTokenizer tk = new StringTokenizer(str_list,
				IDriverMgmtConstants.PATH_DELIMITER);
		String[] pieces = new String[tk.countTokens()];
		int index = 0;
		while (tk.hasMoreTokens())
			pieces[index++] = tk.nextToken();
		return pieces;
	}

	public static void addProblemMarker(String name, String message) {
	    // maintenance of problem markers is only applicable on OSGi platform
	    if( ! ConnectivityPlugin.isRunningOSGiPlatform() )
	        return;
	    
		IResource resource = ResourcesPlugin.getWorkspace().getRoot();
		Map map = new HashMap(3);
		map.put(IMarker.MESSAGE, ConnectivityPlugin.getDefault().getResourceString(
				"drivermarker.error", new String[] { name, message})); //$NON-NLS-1$
		map.put(IMarker.SEVERITY, new Integer(IMarker.SEVERITY_ERROR));
		map.put(IMarker.LOCATION, name);
		map.put(IMarker.TRANSIENT, Boolean.FALSE.toString());

		try {
			IMarker marker = resource
					.createMarker("org.eclipse.datatools.connectivity.ui.driverProblem"); //$NON-NLS-1$
			marker.setAttributes(map);
		}
		catch (CoreException e) {
		}
	}

	public static void removeOldProblemMarkers(String name) {
        // maintenance of problem markers is only applicable on OSGi platform
        if( ! ConnectivityPlugin.isRunningOSGiPlatform() )
            return;
        
		IResource resource = ResourcesPlugin.getWorkspace().getRoot();
		try {
			IMarker[] markers = resource.findMarkers(
					"org.eclipse.datatools.connectivity.ui.driverProblem", true, //$NON-NLS-1$
					IResource.DEPTH_INFINITE);
			for (int i = 0; i < markers.length; i++) {
				if (markers[i].getAttribute(IMarker.LOCATION, new String())
						.equals(name)) {
					markers[i].delete();
				}
			}
		}
		catch (CoreException e) {
		}
	}
	
	protected boolean isEmptyJarListOK()
	{
		return mTemplate.getEmptyJarListIsOKFlag();
	}
}
