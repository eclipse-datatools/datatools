/*******************************************************************************
 * Copyright (c) 2004-2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: shongxum - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
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
import org.eclipse.datatools.connectivity.ConnectionProfileConstants;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.drivers.DriverInstance;
import org.eclipse.datatools.connectivity.drivers.DriverManager;
import org.eclipse.datatools.connectivity.internal.security.ICipherProvider;
import org.eclipse.datatools.connectivity.internal.security.SecurityManager;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.ibm.icu.util.StringTokenizer;

/**
 * This class is used for connection profiles management such as loading and
 * saving Note: adding and removing connection profiles are supported by loading
 * and saving because IMemento doesn't support adding and deleting children.
 * 
 * @author shongxum
 */
public class ConnectionProfileMgmt {

	public final static String FILENAME = "ServerProfiles.dat"; //$NON-NLS-1$

	public final static String DEFAULTCP_FILENAME = "WorkSpaceServerConnectionProfiles.xml"; //$NON-NLS-1$

	private final static String ROOTNAME = "DataTools.ServerProfiles"; //$NON-NLS-1$

	private final static String CHILDNAME = "profile"; //$NON-NLS-1$

	private final static String PROFILENAME = "name"; //$NON-NLS-1$

	private final static String PROFILEDESC = "desc"; //$NON-NLS-1$

	private final static String PROFILEAUTOCONNECT = "autoconnect"; //$NON-NLS-1$

	private final static String PROFILEPARENT = "parent"; //$NON-NLS-1$

	private final static String PROVIDERID = "providerID"; //$NON-NLS-1$

	private final static String PROPKEYS = "keys"; //$NON-NLS-1$

	private final static String PROPPREFIX = "prop_"; //$NON-NLS-1$

	private final static String PROPDELIM = " "; //$NON-NLS-1$

	private final static String LITERAL_YES = "Yes"; //$NON-NLS-1$

	private final static String LITERAL_NO = "No"; //$NON-NLS-1$
	
	private final static String PROFILEID = "id"; //$NON-NLS-1$
	
	private final static String PROPERTYTAG = "property"; //$NON-NLS-1$
	
	private final static String PROPERTYNAMEATTR = "name"; //$NON-NLS-1$
	
	private final static String PROPERTYVALUEATTR = "value"; //$NON-NLS-1$
	
	private final static String BASEPROPERTIESTAG = "baseproperties"; //$NON-NLS-1$
	
	private final static String VERSIONATTR = "version"; //$NON-NLS-1$
	
	private final static String VERSIONSTR = "1.0"; //$NON-NLS-1$
	
	private final static String DRIVERREFTAG = "driverreference"; //$NON-NLS-1$
	
	private final static String DRIVERNAMEATTR = "driverName"; //$NON-NLS-1$
	
	private final static String DRIVERTYPEIDATTR = "driverTypeID"; //$NON-NLS-1$

	private static IPath storageLocation = null;
	
	private static DocumentBuilderFactory documentBuilderFactory = null;
	private static DocumentBuilder documentBuilder = null;
	private static TransformerFactory transFactory = null;
	private static Transformer transformer = null;

	/**
	 * Convert a String to a Properties object
	 * 
	 * @param xmlChild
	 * @return
	 */
	private static Properties keysToProperties(Element elem) {
		Properties props = new Properties();
		String keys = elem.getAttribute(PROPKEYS), key, value;
		StringTokenizer st = new StringTokenizer(keys, PROPDELIM);
		while (st.hasMoreTokens()) {
			key = st.nextToken();
			value = elem.getAttribute(PROPPREFIX + key);
			props.put(key, value);
		}
		return props;
	}

	/**
	 * Save connection profiles
	 * 
	 * @param cps To be saved connection profiles
	 * @throws IOException
	 * @throws TransformerException 
	 * @throws ParserConfigurationException 
	 * @throws TransformerConfigurationException 
	 */
	public static void saveCPs(IConnectionProfile[] cps) 
		throws CoreException 
	{
		saveCPs(cps, getStorageLocation().append(FILENAME).toFile(), SecurityManager.getInstance().getDefaultCipherProvider());
	}

	/**
	 * Save connection profiles
	 * 
	 * @param cps
	 * @param file
	 * @param isp
	 * @throws IOException
	 * @throws ParserConfigurationException 
	 * @throws TransformerException 
	 * @throws TransformerConfigurationException 
	 * @throws GeneralSecurityException
	 * @throws TransformerException 
	 */
	public static void saveCPs(IConnectionProfile[] cps, File file,
			ICipherProvider isp)
		throws CoreException 
	{
		try {
		    Document document = getDocumentBuilder().newDocument();
		    Element rootElement = document.createElement(ROOTNAME);
		    document.appendChild(rootElement);
			if (!file.exists())
				file.createNewFile();
			OutputStream out = null, outs = new FileOutputStream(file);
			Writer writer = null;
			
			try {
				if (isp != null) {
					out = new CipherOutputStream(outs, isp.createEncryptionCipher());
				}
				else {
					out = outs;
				}
				OutputStreamWriter outw = new OutputStreamWriter(out, "UTF-8"); //$NON-NLS-1$
				writer = new BufferedWriter(outw);
				writeCPToXML1_0(cps, document, rootElement);
				DOMSource source = new DOMSource(document);
	            StreamResult result = new StreamResult(outw);
	        	
	            getTransformer().transform(source, result);	
	
			}
			finally {
				if (writer != null)
					writer.close();
			}
		} catch (DOMException e) {
			throw new CoreException(new Status(Status.ERROR, ConnectivityPlugin.PLUGIN_ID, -1, 
					ConnectivityPlugin.getDefault().getResourceString("error.saveprofilesxml"), e));//$NON-NLS-1$
		} catch (IOException e) {
			throw new CoreException(new Status(Status.ERROR, ConnectivityPlugin.PLUGIN_ID, -1, 
					ConnectivityPlugin.getDefault().getResourceString("error.saveprofilesxml"), e));//$NON-NLS-1$
		} catch (GeneralSecurityException e) {
			throw new CoreException(new Status(Status.ERROR, ConnectivityPlugin.PLUGIN_ID, -1, 
					ConnectivityPlugin.getDefault().getResourceString("error.saveprofilesxml"), e));//$NON-NLS-1$
		} catch (TransformerException e) {
			throw new CoreException(new Status(Status.ERROR, ConnectivityPlugin.PLUGIN_ID, -1, 
					ConnectivityPlugin.getDefault().getResourceString("error.saveprofilesxml"), e));//$NON-NLS-1$
		}
	}
	
	/**
	 * Write out the CPs list to XML in the new format
	 * @param cps
	 * @param document
	 * @param rootElement
	 */
	private static void writeCPToXML1_0 ( IConnectionProfile[] cps, Document document, Element rootElement ) {
		IConnectionProfile cp;
		
		Element child, extraChild, baseProps;
		rootElement.setAttribute(VERSIONATTR, VERSIONSTR);
		
		for (int i = 0; i < cps.length; i++) {
			cp = cps[i];
			child = document.createElement(CHILDNAME);
			child.setAttribute(PROFILENAME, cp.getName());
			child.setAttribute(PROFILEDESC, cp.getDescription());
			child.setAttribute(PROFILEAUTOCONNECT, ((ConnectionProfile) cp)
					.isAutoConnect() ? LITERAL_YES : LITERAL_NO);
			child.setAttribute(PROVIDERID, cp.getProviderId());
			child.setAttribute(PROFILEID, cp.getInstanceID());
			
			boolean hasDriverReference = false;
			String driverID = null;
			Properties props = cp.getBaseProperties();
			try {
				props = ((ConnectionProfileProvider) cp
						.getProvider()).getPropertiesPersistenceHook()
						.getPersitentProperties(props);
			}
			catch (Exception e) {
				if (ConnectionProfileManager.DEBUG_CONNECTION_PROFILE_EXTENSION) {
					System.err
							.println(ConnectivityPlugin
									.getDefault()
									.getResourceString(
											"trace.error.propertiesPersistenceHookSaveError", //$NON-NLS-1$
											new Object[] {
													cp.getName(),
													cp.getProviderId()}));
					e.printStackTrace();
				}
			}
			baseProps = document.createElement(BASEPROPERTIESTAG);
			String key, value;
			for (Enumeration enu = props.propertyNames(); enu
					.hasMoreElements();) {
				key = (String) enu.nextElement();
				value = props.getProperty(key);
				if (key.equals(ConnectionProfileConstants.PROP_DRIVER_DEFINITION_ID)) {
					driverID = value;
					hasDriverReference = true;
				}
				appendPropertyToElement ( document, baseProps, key, value );
			}
			child.appendChild(baseProps);
			for (Iterator it = ((ConnectionProfile)cp).getPropertiesMap().entrySet()
					.iterator(); it.hasNext();) {
				Map.Entry me = (Map.Entry) it.next();
				String type = (String) me.getKey();

				if (type.equals(cp.getProviderId())) {
					continue;
				}
				else if (type.equals(BASEPROPERTIESTAG)) {
					continue;
				}
				else if (type.equals(DRIVERREFTAG)) {
					continue;
				}

				props = (Properties) me.getValue();
				extraChild = document.createElement(type);
				if (props == null) {
					props = new Properties();
				}
				try {
					ProfileExtensionProvider pep = (ProfileExtensionProvider) cp
							.getProfileExtensions().get(type);
					if (pep != null) {
						props = pep.getPropertiesPersistenceHook()
								.getPersitentProperties(props);
					}
				}
				catch (Exception e) {
					if (ConnectionProfileManager.DEBUG_CONNECTION_PROFILE_EXTENSION) {
						System.err
								.println(ConnectivityPlugin
										.getDefault()
										.getResourceString(
												"trace.error.propertiesPersistenceHookSaveError", //$NON-NLS-1$
												new Object[] {
														cp.getName(),
														type}));
						e.printStackTrace();
					}
				}
				for (Enumeration enu = props.propertyNames(); enu
						.hasMoreElements();) {
					key = (String) enu.nextElement();
					value = props.getProperty(key);
					if (key.equals(ConnectionProfileConstants.PROP_DRIVER_DEFINITION_ID)) {
						driverID = value;
						hasDriverReference = true;
					}
					appendPropertyToElement ( document, extraChild, key, value );
				}
				child.appendChild(extraChild);
			}
			if (hasDriverReference && driverID != null ) {
				Element driverElem = document.createElement(DRIVERREFTAG);
				DriverInstance di = DriverManager.getInstance().getDriverInstanceByID(driverID);
				String driverName = di.getName();
				String driverType = di.getTemplate().getId();
				appendPropertyToElement ( document, driverElem, DRIVERNAMEATTR, driverName );
				appendPropertyToElement ( document, driverElem, DRIVERTYPEIDATTR, driverType );
				child.appendChild(driverElem);
			}
			rootElement.appendChild(child);
		}
	}
	
	/**
	 * Utility method to add a key/value property to a node
	 * @param document
	 * @param parent
	 * @param key
	 * @param value
	 */
	private static void appendPropertyToElement ( Document document, Element parent, String key, String value ) {
		Element propElem = document.createElement(PROPERTYTAG);
		propElem.setAttribute(PROPERTYNAMEATTR, key);
		propElem.setAttribute(PROPERTYVALUEATTR, value);
		parent.appendChild(propElem);
	}

	/**
	 * Load connection profile by name from storage
	 * 
	 * @param cpName
	 * @return the connection profile object
	 * @throws IOException
	 * @throws ParserConfigurationException 
	 * @throws SAXException 
	 * @throws TransformerException 
	 * @throws TransformerConfigurationException 
	 * @deprecated currently, this isn't used, and shouldn't be. leaving this as
	 *             deprecated, just in case.
	 */
	public static IConnectionProfile loadCP(String cpName)
			throws CoreException {
		try {
			IPath path = getStorageLocation();
			path = path.append(FILENAME);
			File file = path.toFile();
			if (!file.exists())
				return null;
			InputStream is = new FileInputStream(file);
			// CipherInputStream cis = new CipherInputStream(is, CipherManager1
			// .getInstance().getDecryptCipher());
			InputSource source = new InputSource(is);
			source.setEncoding("UTF-8"); //$NON-NLS-1$
			Document document = getDocumentBuilder().parse(source);
			ConnectionProfile cp = null;
			NodeList nl = document.getElementsByTagName(CHILDNAME);
			for (int i = 0; i < nl.getLength(); i++) {
				Node node = nl.item(i);
				if (node instanceof Element) {
					Element elem = (Element) node;
					if (elem.getAttribute(PROFILENAME).equals(cpName)) {
						cp = new ConnectionProfile(elem
								.getAttribute(PROFILENAME), elem
								.getAttribute(PROFILEDESC), elem
								.getAttribute(PROVIDERID),elem
								.getAttribute(PROFILEPARENT), LITERAL_YES
								.equals(elem.getAttribute(PROFILEAUTOCONNECT)),
								elem.getAttribute(PROFILEID));
				
						if (cp.getProvider() == null)
							continue;
						cp.setBaseProperties(keysToProperties(elem));
				
						if (cp.getProfileExtensions().size() != 0) {
							for (Iterator it = cp.getProfileExtensions().entrySet()
									.iterator(); it.hasNext();) {
								Map.Entry me = (Map.Entry) it.next();
								String type = (String) me.getKey();
								Node xmlExtraChild = elem.getFirstChild();
								if (xmlExtraChild != null && elem.getTagName().equals(type))
									cp.setProperties(type,
											keysToProperties((Element)xmlExtraChild));
							}
						}
				
						break;
					}
				}
			}
			cp.setCreated();
			return cp;
		} catch (IOException e) {
			throw new CoreException(new Status(Status.ERROR, ConnectivityPlugin.PLUGIN_ID, -1, 
					ConnectivityPlugin.getDefault().getResourceString("error.loadprofilesxml"), e));//$NON-NLS-1$
		} catch (SAXException e) {
			throw new CoreException(new Status(Status.ERROR, ConnectivityPlugin.PLUGIN_ID, -1, 
					ConnectivityPlugin.getDefault().getResourceString("error.loadprofilesxml"), e));//$NON-NLS-1$
		}
	}

	/**
	 * @param file
	 * @return
	 * @throws CoreException
	 */
	public static IConnectionProfile[] loadCPs(File file) throws CoreException {
		try {
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8"); //$NON-NLS-1$
			BufferedReader reader = new BufferedReader(isr);
			String line = reader.readLine();
			reader.close();
			isr.close();
			fis.close();
			if (line.matches(".*xml.*")) { //$NON-NLS-1$
				// not encrpyted
				return loadCPs(file, null);
			}
			else {
				// encrypted
				return loadCPs(file, SecurityManager.getInstance().getDefaultCipherProvider());
			}
		} catch (FileNotFoundException e) {
			throw new CoreException(new Status(Status.ERROR, ConnectivityPlugin.PLUGIN_ID, -1, 
					ConnectivityPlugin.getDefault().getResourceString("error.loadprofilesxml"), e));//$NON-NLS-1$
		} catch (UnsupportedEncodingException e) {
			throw new CoreException(new Status(Status.ERROR, ConnectivityPlugin.PLUGIN_ID, -1, 
					ConnectivityPlugin.getDefault().getResourceString("error.loadprofilesxml"), e));//$NON-NLS-1$
		} catch (IOException e) {
			throw new CoreException(new Status(Status.ERROR, ConnectivityPlugin.PLUGIN_ID, -1, 
					ConnectivityPlugin.getDefault().getResourceString("error.loadprofilesxml"), e));//$NON-NLS-1$
		}
	}

	/**
	 * Takes the properties for a given element and converts them to a 
	 * Properties object.
	 * @param elem
	 * @return
	 */
	private static Properties keysElementsToProperties(Element elem) {
		Properties props = new Properties();
		NodeList nl = elem.getElementsByTagName(PROPERTYTAG);
		for (int i = 0; i < nl.getLength(); i++) {
			Node node = nl.item(i);
			if (!(node instanceof Element))
				continue;

			Element property = (Element) node;
			String key = property.getAttribute(PROPERTYNAMEATTR);
			String value = property.getAttribute(PROPERTYVALUEATTR);
			props.put(key, value);
		}
		return props;
	}

	/**
	 * Read back the 1.0 format for CP storage
	 * @param cps
	 * @param document
	 * @return
	 */
	private static boolean readCPsFromXML1_0 (ArrayList cps, Document document) {
		if (cps == null)
			cps = new ArrayList();
		boolean updatedIDs = false;
		NodeList nl = document.getElementsByTagName(CHILDNAME);
		for (int i = 0; i < nl.getLength(); i++) {
			Node node = nl.item(i);
			if (!(node instanceof Element))
				continue;

			Element elem = (Element) node;
			ConnectionProfile cp = new ConnectionProfile(elem
					.getAttribute(PROFILENAME), elem
					.getAttribute(PROFILEDESC), elem
					.getAttribute(PROVIDERID), elem
					.getAttribute(PROFILEPARENT), LITERAL_YES.equals(elem
					.getAttribute(PROFILEAUTOCONNECT)), elem
					.getAttribute(PROFILEID));

			if (cp.getProvider() == null)
				continue;
			
			NodeList nl2 = elem.getElementsByTagName(BASEPROPERTIESTAG);
			if (nl.getLength() > 0) {
				
				Element baseProps = null;
				for (int j = 0; j < nl2.getLength(); j++) {
					Node testnode = nl2.item(j);
					if (!(testnode instanceof Element))
						continue;
					Element el = (Element) testnode;
					if (el.getTagName().equals(BASEPROPERTIESTAG)) {
						baseProps = el;
						break;
					}
				}
				if (baseProps != null ) {
					Properties props = keysElementsToProperties(baseProps);
					try {
						props = ((ConnectionProfileProvider) cp.getProvider())
								.getPropertiesPersistenceHook()
								.populateTransientProperties(props);
					}
					catch (Exception e) {
						if (ConnectionProfileManager.DEBUG_CONNECTION_PROFILE_EXTENSION) {
							System.err
									.println(ConnectivityPlugin
											.getDefault()
											.getResourceString(
													"trace.error.propertiesPersistenceHookLoadError", //$NON-NLS-1$
													new Object[] { cp.getName(),
															cp.getProviderId()}));
							e.printStackTrace();
						}
					}
					cp.setBaseProperties(props);
				}
			}

			NodeList extElements = elem.getChildNodes();
			for (int extIndex = 0, extCount = extElements.getLength(); extIndex < extCount; ++extIndex) {
				Node extNode = extElements.item(extIndex);
				if (extNode.getNodeType() != Node.ELEMENT_NODE) {
					continue;
				}
				String type = extNode.getNodeName();
				if (type.equals(BASEPROPERTIESTAG)) {
					continue;
				}
				else if (type.equals(DRIVERREFTAG)) {
					// check for drivers
					Properties props = keysElementsToProperties((Element) extNode);
					String driverName = props.getProperty(DRIVERNAMEATTR);
					String driverTypeID = props.getProperty(DRIVERTYPEIDATTR);
					
					if (DriverManager.getInstance().getDriverInstanceByName(driverName) != null) {
						// we found the driver, so we're ok to continue
					}
					else {
						DriverInstance di = DriverManager.getInstance().createNewDriverInstance(driverTypeID, driverName, new String());
						String driverID = di.getId();
						
						cp.getBaseProperties().setProperty(ConnectionProfileConstants.PROP_DRIVER_DEFINITION_ID, driverID);
						updatedIDs = true;
					}
					continue;
				}
				Properties props = keysElementsToProperties((Element) extNode);
				try {
					ProfileExtensionProvider pep = (ProfileExtensionProvider) cp
							.getProfileExtensions().get(type);
					if (pep != null) {
						props = pep.getPropertiesPersistenceHook()
								.populateTransientProperties(props);
					}
				}
				catch (Exception e) {
					if (ConnectionProfileManager.DEBUG_CONNECTION_PROFILE_EXTENSION) {
						System.err
								.println(ConnectivityPlugin
										.getDefault()
										.getResourceString(
												"trace.error.propertiesPersistenceHookLoadError", //$NON-NLS-1$
												new Object[] {
														cp.getName(), type}));
						e.printStackTrace();
					}
				}
				cp.setProperties(type, props);
			}

			cp.setCreated();
			cps.add(cp);

			updatedIDs = elem.getAttribute(PROFILEID) == null || updatedIDs;
		}
		return updatedIDs;
	}
	
	/**
	 * Load all connection profiles from storage
	 * 
	 * @param file
	 * @param isp
	 * @return IConnectionProfile[]
	 * @throws IOException
	 * @throws GeneralSecurityException
	 * @throws SAXException
	 * @throws TransformerException 
	 * @throws ParserConfigurationException
	 * @throws TransformerConfigurationException
	 * @throws TransformerException
	 */
	public static IConnectionProfile[] loadCPs(File file, ICipherProvider isp)
		throws CoreException 
	{
		IConnectionProfile retVal[] = null;
		try {
			if (!file.exists())
				return new IConnectionProfile[0];
			InputStream is, fis = new FileInputStream(file);
			if (isp != null) {
				is = new CipherInputStream(fis, isp.createDecryptionCipher());
			}
			else {
				is = fis;
			}
			InputSource source = new InputSource(is);
			source.setEncoding("UTF-8"); //$NON-NLS-1$
			Document document = null;
			try {
				document = getDocumentBuilder().parse(source);
			} catch (SAXException e) {
				// if we get this, the parser may already be in use, so try again
				try {
					document = getDocumentBuilder(true).parse(source);
				} catch (SAXException e2) {
					// if we got this again, some other issue may be occurring
					throw new CoreException(new Status(Status.ERROR, ConnectivityPlugin.PLUGIN_ID, -1, 
							ConnectivityPlugin.getDefault().getResourceString("error.loadprofilesxml"), e));//$NON-NLS-1$
				}
			}
			ArrayList cps = new ArrayList();
			boolean updatedIDs = false;
			String version = document.getDocumentElement().getAttribute(VERSIONATTR);
			if (version != null && version.equals(VERSIONSTR))
				updatedIDs = readCPsFromXML1_0(cps, document);
			else {
				NodeList nl = document.getElementsByTagName(CHILDNAME);
				for (int i = 0; i < nl.getLength(); i++) {
					Node node = nl.item(i);
					if (!(node instanceof Element))
						continue;
	
					Element elem = (Element) node;
					ConnectionProfile cp = new ConnectionProfile(elem
							.getAttribute(PROFILENAME), elem
							.getAttribute(PROFILEDESC), elem
							.getAttribute(PROVIDERID), elem
							.getAttribute(PROFILEPARENT), LITERAL_YES.equals(elem
							.getAttribute(PROFILEAUTOCONNECT)), elem
							.getAttribute(PROFILEID));
	
					if (cp.getProvider() == null)
						continue;
					
					Properties props = keysToProperties(elem);
					try {
						props = ((ConnectionProfileProvider) cp.getProvider())
								.getPropertiesPersistenceHook()
								.populateTransientProperties(props);
					}
					catch (Exception e) {
						if (ConnectionProfileManager.DEBUG_CONNECTION_PROFILE_EXTENSION) {
							System.err
									.println(ConnectivityPlugin
											.getDefault()
											.getResourceString(
													"trace.error.propertiesPersistenceHookLoadError", //$NON-NLS-1$
													new Object[] { cp.getName(),
															cp.getProviderId()}));
							e.printStackTrace();
						}
					}
	
					cp.setBaseProperties(props);
	
					NodeList extElements = elem.getChildNodes();
					for (int extIndex = 0, extCount = extElements.getLength(); extIndex < extCount; ++extIndex) {
						Node extNode = extElements.item(extIndex);
						if (extNode.getNodeType() != Node.ELEMENT_NODE) {
							continue;
						}
						String type = extNode.getNodeName();
						props = keysToProperties((Element) extNode);
						try {
							ProfileExtensionProvider pep = (ProfileExtensionProvider) cp
									.getProfileExtensions().get(type);
							if (pep != null) {
								props = pep.getPropertiesPersistenceHook()
										.populateTransientProperties(props);
							}
						}
						catch (Exception e) {
							if (ConnectionProfileManager.DEBUG_CONNECTION_PROFILE_EXTENSION) {
								System.err
										.println(ConnectivityPlugin
												.getDefault()
												.getResourceString(
														"trace.error.propertiesPersistenceHookLoadError", //$NON-NLS-1$
														new Object[] {
																cp.getName(), type}));
								e.printStackTrace();
							}
						}
						cp.setProperties(type, props);
					}
	
					cp.setCreated();
					cps.add(cp);
	
					updatedIDs = elem.getAttribute(PROFILEID) == null || updatedIDs;
				}
			}
			retVal = (IConnectionProfile[]) cps.toArray(new IConnectionProfile[cps.size()]);
			if (updatedIDs) {
				saveCPs(retVal,file, isp);
			}
		} catch (IOException e) {
			throw new CoreException(new Status(Status.ERROR, ConnectivityPlugin.PLUGIN_ID, -1, 
					ConnectivityPlugin.getDefault().getResourceString("error.loadprofilesxml"), e));//$NON-NLS-1$
		} catch (GeneralSecurityException e) {
			throw new CoreException(new Status(Status.ERROR, ConnectivityPlugin.PLUGIN_ID, -1, 
					ConnectivityPlugin.getDefault().getResourceString("error.loadprofilesxml"), e));//$NON-NLS-1$
//		} catch (SAXException e) {
//			throw new CoreException(new Status(Status.ERROR, ConnectivityPlugin.PLUGIN_ID, -1, 
//					ConnectivityPlugin.getDefault().getResourceString("error.loadprofilesxml"), e));//$NON-NLS-1$
		}
		return retVal;
	}


	/**
	 * Get connection profiles storage location
	 * 
	 * @return location of the connection profiles
	 */
	public static IPath getStorageLocation() {
		if (storageLocation == null) {
			return ConnectivityPlugin.getDefault().getStateLocation();
		}
		return storageLocation;
	}

	/**
	 * Set connection profiles storage location
	 * 
	 * @param location where connection profiles storage is put
	 */
	public static void setStorageLocation(IPath location) {
		storageLocation = location;
	}

	/**
	 * Internal method to get a handle to an XML document builder.
	 * @return
	 */
	private static DocumentBuilder getDocumentBuilder() {
		return getDocumentBuilder(false);
	}
	private static DocumentBuilder getDocumentBuilder(boolean reset) {
		if ((documentBuilder == null ) || reset) {
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