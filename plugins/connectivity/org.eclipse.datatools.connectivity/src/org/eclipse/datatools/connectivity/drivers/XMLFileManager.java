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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.internal.ConnectivityPlugin;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.ibm.icu.util.StringTokenizer;

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

	private static DocumentBuilderFactory documentBuilderFactory = null;
	private static DocumentBuilder documentBuilder = null;
	private static TransformerFactory transFactory = null;
	private static Transformer transformer = null;

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
	 * @param elem
	 * @return
	 */
	private static Properties keysToProperties(Element elem) {
		Properties props = new Properties();
		String keys = elem.getAttribute(PROPSETKEYS), key, value;
		StringTokenizer st = new StringTokenizer(keys, PROPDELIM);
		while (st.hasMoreTokens()) {
			key = st.nextToken();
			value = elem.getAttribute(PROPPREFIX + key);
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
	public static synchronized void saveNamedPropertySet(IPropertySet[] pss)
		throws CoreException 
	{
		try {
		    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		    documentBuilderFactory.setNamespaceAware(true);
		    DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		    Document document = documentBuilder.newDocument();
		    Element rootElement = document.createElement(ROOTNAME);
		    document.appendChild(rootElement);
		    Element child = null;
			IPath path = getStorageLocation();
			path = path.append(mFileName);
			File file = path.toFile();
			if (!file.exists())
				file.createNewFile();
			OutputStream outs = new FileOutputStream(file);
			Writer writer = null;
			IPropertySet ps;
			try {
				OutputStreamWriter outw = new OutputStreamWriter(outs, "UTF-8"); //$NON-NLS-1$
				writer = new BufferedWriter(outw);
				for (int i = 0; i < pss.length; i++) {
					ps = pss[i];
					if (ps == null) continue;
					child = document.createElement(CHILDNAME);
					child.setAttribute(PROPSETNAME, ps.getName());
					child.setAttribute(PROPSETID, ps.getID());
					Properties props = ps.getBaseProperties();
					String keys = keysToString(props.keys());
					child.setAttribute(PROPSETKEYS, keys);
					String key, value;
					for (Enumeration enu = props.propertyNames(); enu
							.hasMoreElements();) {
						key = (String) enu.nextElement();
						value = props.getProperty(key);
						child.setAttribute(PROPPREFIX + key, value);
					}
					rootElement.appendChild(child);
				}
				DOMSource source = new DOMSource(document);
		        StreamResult result = new StreamResult(outw);
		
		        getTransformer().transform(source, result);	
			}
			finally {
				if (writer != null) {
					writer.close();
				}
				if (outs != null) {
					outs.close();
				}
			}
		} catch (IOException e) {
			throw new CoreException(new Status(Status.ERROR, ConnectivityPlugin.PLUGIN_ID, -1, 
					ConnectivityPlugin.getDefault().getResourceString("error.savedriversxml"), e));//$NON-NLS-1$
		} catch (TransformerException e) {
			throw new CoreException(new Status(Status.ERROR, ConnectivityPlugin.PLUGIN_ID, -1, 
					ConnectivityPlugin.getDefault().getResourceString("error.savedriversxml"), e));//$NON-NLS-1$
		} catch (ParserConfigurationException e) {
			throw new CoreException(new Status(Status.ERROR, ConnectivityPlugin.PLUGIN_ID, -1, 
					ConnectivityPlugin.getDefault().getResourceString("error.savedriversxml"), e));//$NON-NLS-1$
		}
	}

	/**
	 * Load all property sets from storage
	 * 
	 * @return property sets
	 * @throws IOException
	 */
	public static synchronized IPropertySet[] loadPropertySets() throws CoreException 
	{
		InputStream fis = null;
		try {
			IPath path = getStorageLocation();
			path = path.append(mFileName);
			File file = path.toFile();
			if (!file.exists())
				return new IPropertySet[0];
			
			fis = new FileInputStream(file);
			InputSource source = new InputSource(fis);
			source.setEncoding("UTF-8"); //$NON-NLS-1$
		    Document document = getDocumentBuilder().parse(source);
			IPropertySet ps;
			ArrayList pss = new ArrayList();
		    NodeList nl = document.getElementsByTagName(CHILDNAME);
			for (int i = 0; i < nl.getLength(); i++) {
				Node node = nl.item(i);
				if (node instanceof Element) {
					Element elem = (Element) node;
					String name = elem.getAttribute(PROPSETNAME);
					String id = elem.getAttribute(PROPSETID);
					Properties props = keysToProperties(elem);
					ps = new PropertySetImpl(name, id);
					ps.setBaseProperties(props);
					pss.add(ps);
				}
			}
			if (fis != null) { 
				try {
					fis.close();
				} catch (IOException e) {
					throw new CoreException(new Status(Status.ERROR, ConnectivityPlugin.PLUGIN_ID, -1, 
							ConnectivityPlugin.getDefault().getResourceString("error.loaddriversxml"), e));//$NON-NLS-1$
				}
			}				
			return (IPropertySet[]) pss.toArray(new IPropertySet[0]);
		} catch (IOException e) {
			throw new CoreException(new Status(Status.ERROR, ConnectivityPlugin.PLUGIN_ID, -1, 
					ConnectivityPlugin.getDefault().getResourceString("error.loaddriversxml"), e));//$NON-NLS-1$
		} catch (SAXException e) {
			throw new CoreException(new Status(Status.ERROR, ConnectivityPlugin.PLUGIN_ID, -1, 
					ConnectivityPlugin.getDefault().getResourceString("error.loaddriversxml"), e));//$NON-NLS-1$
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					throw new CoreException(new Status(Status.ERROR, ConnectivityPlugin.PLUGIN_ID, -1, 
							ConnectivityPlugin.getDefault().getResourceString("error.loaddriversxml"), e));//$NON-NLS-1$
				}
			}
		}
	}


	/**
	 * Get property set storage location
	 * 
	 * @return location of the connection profiles
	 */
	public static IPath getStorageLocation() {
		if (storageLocation == null)
			return ConnectivityPlugin.getStorageLocation();
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
	 * @return String
	 */
	public static String getFileName() {
		return mFileName;
	}

	/**
	 * Set the file name
	 * @param name
	 */
	public static void setFileName(String name) {
		mFileName = name;
	}

	/**
	 * Returns date last the file was last modified
	 * @return String
	 */
	public static String getFileDateTimeStamp() {
		IPath path = getStorageLocation();
		path = path.append(mFileName);
		File file = path.toFile();
		if (!file.exists())
			return null;
		Long modified = new Long(file.lastModified());
		return modified.toString();
	}
	
	/**
	 * Internal method to get a handle to an XML document builder.
	 * @return
	 */
	private static DocumentBuilder getDocumentBuilder() {
		if (documentBuilder == null) {
		    documentBuilderFactory = DocumentBuilderFactory.newInstance();
		    documentBuilderFactory.setNamespaceAware(true);
		    try {
				documentBuilder = documentBuilderFactory.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				ConnectivityPlugin.getDefault().log(e);
			}
		}
		return documentBuilder;
	}
	
	/**
	 * Internal method to get a handle to an XML document transformer.
	 * @return
	 */
	private static Transformer getTransformer() {
		if (transformer == null) {
            transFactory = TransformerFactory.newInstance();
            try {
            	transformer = transFactory.newTransformer();
            } catch (TransformerConfigurationException e ) {
            	ConnectivityPlugin.getDefault().log(e);
            }
		}
		return transformer;
	}
}
