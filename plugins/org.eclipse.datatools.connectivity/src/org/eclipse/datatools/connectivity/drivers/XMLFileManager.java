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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;
import java.util.StringTokenizer;

import org.eclipse.core.runtime.IPath;
import org.eclipse.datatools.connectivity.internal.ConnectivityPlugin;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.XMLMemento;

/**
 * Basic XML file manager
 * 
 * @author brianf
 */
public class XMLFileManager {

	// default file name
	private final static String FILENAME = "PropertySets.xml"; //$NON-NLS-1$

	// Basic properties
	private final static String ROOTNAME = "DataTools.PropertySets"; //$NON-NLS-1$
	private final static String CHILDNAME = "propertySet"; //$NON-NLS-1$
	private final static String PROPSETNAME = "name"; //$NON-NLS-1$
	private final static String PROPSETID = "iD"; //$NON-NLS-1$
	private final static String PROPSETKEYS = "keys"; //$NON-NLS-1$

	// prefix for property names
	private final static String PROPPREFIX = "prop_"; //$NON-NLS-1$

	// delimiter for property name list
	private final static String PROPDELIM = " "; //$NON-NLS-1$

	// storage path
	private static IPath storageLocation = null;

	// file name
	private static String mFileName = null;

	/**
	 * Constructor
	 */
	public XMLFileManager() {
		mFileName = FILENAME;
	}

	/**
	 * Convert an Enumeration to a space-separated String
	 * 
	 * @param enu
	 * @return
	 */
	private static String keysToString(Enumeration enu) {
		StringBuffer keys = new StringBuffer();
		while (enu.hasMoreElements()) {
			keys.append((String) enu.nextElement() + " "); //$NON-NLS-1$
		}
		return keys.toString();
	}

	/**
	 * Convert a String to a Properties object
	 * 
	 * @param xmlChild
	 * @return
	 */
	private static Properties keysToProperties(IMemento xmlChild) {
		Properties props = new Properties();
		String keys = xmlChild.getString(PROPSETKEYS), key, value;
		StringTokenizer st = new StringTokenizer(keys, PROPDELIM);
		while (st.hasMoreTokens()) {
			key = st.nextToken();
			value = xmlChild.getString(PROPPREFIX + key);
			props.put(key, value);
		}
		return props;
	}

	/**
	 * Save property sets
	 * 
	 * @param pss To be saved property sets
	 * @throws IOException
	 */
	public static void saveNamedPropertySet(IPropertySet[] pss)
			throws IOException {
		XMLMemento xmlMemento = XMLMemento.createWriteRoot(ROOTNAME);
		IMemento xmlChild;
		IPath path = getStorageLocation();
		path = path.append(mFileName);
		File file = path.toFile();
		if (!file.exists())
			file.createNewFile();
		Writer writer = new BufferedWriter(new FileWriter(file));
		IPropertySet ps;
		try {
			for (int i = 0; i < pss.length; i++) {
				ps = pss[i];
				xmlChild = xmlMemento.createChild(CHILDNAME);
				xmlChild.putString(PROPSETNAME, ps.getName());
				xmlChild.putString(PROPSETID, ps.getID());
				Properties props = ps.getBaseProperties();
				String keys = keysToString(props.keys());
				xmlChild.putString(PROPSETKEYS, keys);
				String key, value;
				for (Enumeration enu = props.propertyNames(); enu
						.hasMoreElements();) {
					key = (String) enu.nextElement();
					value = props.getProperty(key);
					xmlChild.putString(PROPPREFIX + key, value);
				}
			}
			xmlMemento.save(writer);
		}
		finally {
			writer.close();
		}
	}

	/**
	 * Load all property sets from storage
	 * 
	 * @return property sets
	 * @throws IOException
	 * @throws WorkbenchException
	 */
	public static IPropertySet[] loadPropertySets() throws IOException,
			WorkbenchException {
		IPath path = getStorageLocation();
		path = path.append(mFileName);
		File file = path.toFile();
		if (!file.exists())
			return new IPropertySet[0];
		Reader reader = new BufferedReader(new FileReader(file));
		IPropertySet ps;
		ArrayList pss = new ArrayList();
		try {
			IMemento xmlMemento = XMLMemento.createReadRoot(reader);
			IMemento[] xmlChildren = xmlMemento.getChildren(CHILDNAME);
			for (int i = 0; i < xmlChildren.length; i++) {
				IMemento xmlChild = xmlChildren[i];

				String name = xmlChild.getString(PROPSETNAME);
				String id = xmlChild.getString(PROPSETID);
				Properties props = keysToProperties(xmlChild);
				ps = new PropertySetImpl(name, id);
				ps.setBaseProperties(props);
				pss.add(ps);
			}
		}
		finally {
			reader.close();
		}
		return (IPropertySet[]) pss.toArray(new IPropertySet[0]);
	}

	/**
	 * Get property set storage location
	 * 
	 * @return location of the connection profiles
	 */
	public static IPath getStorageLocation() {
		if (storageLocation == null)
			return ConnectivityPlugin.getDefault().getStateLocation();
		return storageLocation;
	}

	/**
	 * Set property set storage location
	 * 
	 * @param location where connection profiles storage is put
	 */
	public static void setStorageLocation(IPath location) {
		storageLocation = location;
	}

	/**
	 * Return the file name
	 * 
	 * @return
	 */
	public static String getFileName() {
		return mFileName;
	}

	/**
	 * Set the file name
	 */
	public static void setFileName(String name) {
		mFileName = name;
	}

	public static String getFileDateTimeStamp() {
		IPath path = getStorageLocation();
		path = path.append(mFileName);
		File file = path.toFile();
		if (!file.exists())
			return null;
		Long modified = new Long(file.lastModified());
		return modified.toString();
	}
}
