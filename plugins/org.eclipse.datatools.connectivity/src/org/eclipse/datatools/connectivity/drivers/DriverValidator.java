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
import java.util.Properties;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.datatools.connectivity.drivers.models.TemplateDescriptor;

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
		this.mMessage = null;
		boolean flag = true;
		flag = validateJarListFiles();
		if (flag)
			flag = validateProperties();
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
			String name = element.getAttribute("name"); //$NON-NLS-1$
			String required = element.getAttribute("required"); //$NON-NLS-1$
			boolean propRequired = true;
			if (required != null && required.equals("false")) //$NON-NLS-1$
				propRequired = false;
			String value = instance.getNamedProperty(name);
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
	private boolean validateJarListFiles() {
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
}
