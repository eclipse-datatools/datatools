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
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Properties;
import java.util.StringTokenizer;

import org.eclipse.datatools.connectivity.drivers.models.TemplateDescriptor;

/**
 * @author brianf
 */
public class DriverInstance {

	private TemplateDescriptor mTemplate;
	private IPropertySet mInstance;
	private Properties mInstanceProps;
	private ClassLoader mClassLoader;

	public DriverInstance(IPropertySet instance) {
		this.mInstance = instance;
		this.mInstanceProps = this.mInstance.getBaseProperties();

		if (this.mInstanceProps != null) {
			String templateId = this.mInstanceProps
					.getProperty(IDriverMgmtConstants.PROP_DEFN_TYPE);
			this.mTemplate = TemplateDescriptor
					.getDriverTemplateDescriptor(templateId);
		}
	}

	public DriverInstance(TemplateDescriptor template, IPropertySet instance) {
		this.mTemplate = template;
		this.mInstance = instance;
		this.mInstanceProps = this.mInstance.getBaseProperties();
	}

	public String getName() {
		return this.mInstance.getName();
	}

	public String getId() {
		return this.mInstance.getID();
	}

	public String getJarList() {
		if (this.mInstanceProps != null) {
			if (this.mInstanceProps
					.getProperty(IDriverMgmtConstants.PROP_DEFN_JARLIST) != null) {
				String jarlist = this.mInstanceProps
						.getProperty(IDriverMgmtConstants.PROP_DEFN_JARLIST);
				return jarlist.trim();
			}
		}
		return null;
	}

	public String[] getJarListAsArray() {
		if (this.mInstanceProps != null && getJarList() != null) {
			if (getJarList().length() == 0)
				return new String[0];
			String[] paths = parseString(getJarList(),
					IDriverMgmtConstants.PATH_DELIMITER);
			return paths;
		}
		return null;
	}

	public String getNamedProperty(String name) {
		String rtnStr = ""; //$NON-NLS-1$
		if (this.mInstanceProps != null) {
			String id = getTemplate().getPropertyIDFromName(name);
			if (id != null) {
				rtnStr = this.mInstanceProps.getProperty(id);
			}
		}
		if (rtnStr == null) {
			rtnStr = getTemplate().getPropertyValue(name);
		}
		return rtnStr;
	}

	public String getProperty(String id) {
		String rtnStr = null;
		if (mInstanceProps != null) {
			rtnStr = mInstanceProps.getProperty(id);
		}
		return rtnStr;
	}

	public TemplateDescriptor getTemplate() {
		return this.mTemplate;
	}

	private String[] parseString(String str_list, String token) {
		StringTokenizer tk = new StringTokenizer(str_list, token);
		String[] pieces = new String[tk.countTokens()];
		int index = 0;
		while (tk.hasMoreTokens())
			pieces[index++] = tk.nextToken();
		return pieces;
	}

	public IPropertySet getPropertySet() {
		return this.mInstance;
	}

	public ClassLoader getClassLoader() throws Exception {
		if (mClassLoader == null) {
			mClassLoader = createClassLoader(null);
		}
		return mClassLoader;
	}

	public ClassLoader createClassLoader(ClassLoader parentCL) throws Exception {
		String jarList = getJarList();
		if ((jarList == null || jarList.trim().length() == 0)
				&& !getTemplate().getEmptyJarListIsOKFlag()) {
			throw new Exception(
					"Invalid driver definition; JAR list not defined.");
		}

		String[] jarStrings = getJarListAsArray();
		URL[] jars = new URL[jarStrings.length];
		for (int index = 0, count = jars.length; index < count; ++index) {
			try {
				jars[index] = new File(jarStrings[index]).toURL();
			}
			catch (MalformedURLException e) {
				throw new Exception("Invalid driver class path", e);
			}
		}
		if (parentCL == null) {
			return URLClassLoader.newInstance(jars);
		}
		return URLClassLoader.newInstance(jars, parentCL);
	}

	public boolean equals(Object obj) {
		if (obj instanceof DriverInstance) {
			return obj == this || getId().equals(((DriverInstance)obj).getId());
		}
		return false;
	}

	public int hashCode() {
		return getId().hashCode();
	}

}
