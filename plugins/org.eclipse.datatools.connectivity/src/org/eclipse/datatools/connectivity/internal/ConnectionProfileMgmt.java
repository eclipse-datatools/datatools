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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;

import org.eclipse.core.runtime.IPath;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.internal.security.ICipherProvider;
import org.eclipse.datatools.connectivity.internal.security.SecurityManager;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.XMLMemento;

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

	private static IPath storageLocation = null;

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
		String keys = xmlChild.getString(PROPKEYS), key, value;
		StringTokenizer st = new StringTokenizer(keys, PROPDELIM);
		while (st.hasMoreTokens()) {
			key = st.nextToken();
			value = xmlChild.getString(PROPPREFIX + key);
			props.put(key, value);
		}
		return props;
	}

	/**
	 * Save connection profiles
	 * 
	 * @param cps To be saved connection profiles
	 * @throws IOException
	 */
	public static void saveCPs(IConnectionProfile[] cps) throws IOException, GeneralSecurityException {
		saveCPs(cps, getStorageLocation().append(FILENAME).toFile(), SecurityManager.getInstance().getDefaultCipherProvider());
	}

	/**
	 * Save connection profiles
	 * 
	 * @param cps
	 * @param file
	 * @param isp
	 * @throws IOException
	 */
	public static void saveCPs(IConnectionProfile[] cps, File file,
			ICipherProvider isp) throws IOException, GeneralSecurityException {
		
		XMLMemento xmlMemento = XMLMemento.createWriteRoot(ROOTNAME);
		IMemento xmlChild, xmlExtraChild;
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
			OutputStreamWriter outw = new OutputStreamWriter(out, "UTF8"); //$NON-NLS-1$
			writer = new BufferedWriter(outw);
			IConnectionProfile cp;
			for (int i = 0; i < cps.length; i++) {
				cp = cps[i];
				xmlChild = xmlMemento.createChild(CHILDNAME);
				xmlChild.putString(PROFILENAME, cp.getName());
				xmlChild.putString(PROFILEDESC, cp.getDescription());
				xmlChild.putString(PROFILEAUTOCONNECT, ((ConnectionProfile) cp)
						.isAutoConnect() ? LITERAL_YES : LITERAL_NO);
				xmlChild
						.putString(
								PROFILEPARENT,
								cp.getParentProfile() == null ? "" : cp.getParentProfile().getName()); //$NON-NLS-1$ 
				xmlChild.putString(PROVIDERID, cp.getProviderId());
				xmlChild.putString(PROFILEID, cp.getInstanceID());
				Properties props = cp.getBaseProperties();
				String keys = keysToString(props.keys());
				xmlChild.putString(PROPKEYS, keys);
				String key, value;
				for (Enumeration enu = props.propertyNames(); enu
						.hasMoreElements();) {
					key = (String) enu.nextElement();
					value = props.getProperty(key);
					xmlChild.putString(PROPPREFIX + key, value);
				}
				if (cp.getProfileExtensions().size() != 0) {
					for (Iterator it = cp.getProfileExtensions().entrySet()
							.iterator(); it.hasNext();) {
						Map.Entry me = (Map.Entry) it.next();
						String type = (String) me.getKey();
						xmlExtraChild = xmlChild.createChild(type);
						props = cp.getProperties(type);
						if (props == null)
							props = new Properties();
						keys = keysToString(props.keys());
						xmlExtraChild.putString(PROPKEYS, keys);
						for (Enumeration enu = props.propertyNames(); enu
								.hasMoreElements();) {
							key = (String) enu.nextElement();
							value = props.getProperty(key);
							xmlExtraChild.putString(PROPPREFIX + key, value);
						}
					}
				}
			}
			xmlMemento.save(writer);
		}
		finally {
			if (writer != null) {
				writer.close();
			}
			else if (out != null) {
				try {
					out.close();
				}
				catch (IOException e) {
				}
			}
			else {
				try {
					outs.close();
				}
				catch (IOException e) {
				}
			}
		}
	}

	/**
	 * Load connection profile by name from storage
	 * 
	 * @param cpName
	 * @return the connection profile object
	 * @throws WorkbenchException
	 * @throws IOException
	 * @deprecated currently, this isn't used, and shouldn't be. leaving this as
	 *             deprecated, just in case.
	 */
	public static IConnectionProfile loadCP(String cpName)
			throws WorkbenchException, IOException {
		IPath path = getStorageLocation();
		path = path.append(FILENAME);
		File file = path.toFile();
		if (!file.exists())
			return null;
		InputStream is = new FileInputStream(file);
		// CipherInputStream cis = new CipherInputStream(is, CipherManager1
		// .getInstance().getDecryptCipher());
		InputStreamReader isr = new InputStreamReader(is, "UTF8"); //$NON-NLS-1$
		Reader reader = new BufferedReader(isr);
		ConnectionProfile cp = null;
		try {
			IMemento xmlMemento = XMLMemento.createReadRoot(reader), xmlExtraChild;
			IMemento[] xmlChildren = xmlMemento.getChildren(CHILDNAME);
			for (int i = 0; i < xmlChildren.length; i++) {
				if (xmlChildren[i].getString(PROFILENAME).equals(cpName)) {
					cp = new ConnectionProfile(xmlChildren[i]
							.getString(PROFILENAME), xmlChildren[i]
							.getString(PROFILEDESC), xmlChildren[i]
							.getString(PROVIDERID), xmlChildren[i]
							.getString(PROFILEPARENT));
					if (xmlChildren[i].getString(PROFILEAUTOCONNECT) == null)
						cp.setAutoConnect(false);
					else
						cp.setAutoConnect(xmlChildren[i].getString(
								PROFILEAUTOCONNECT).equals(LITERAL_YES) ? true
								: false);
					// Note: If we can't find a provider associated with the
					// cp, we should abandon it.
					if (cp.getProvider() == null)
						continue;
					cp.setBaseProperties(keysToProperties(xmlChildren[i]));

					if (cp.getProfileExtensions().size() != 0) {
						for (Iterator it = cp.getProfileExtensions().entrySet()
								.iterator(); it.hasNext();) {
							Map.Entry me = (Map.Entry) it.next();
							String type = (String) me.getKey();
							xmlExtraChild = xmlChildren[i].getChild(type);
							if (xmlExtraChild != null)
								cp.setProperties(type,
										keysToProperties(xmlExtraChild));
						}
					}
					break;
				}
			}
		}
		finally {
			reader.close();
		}
		cp.setCreated();
		return cp;
	}

	public static IConnectionProfile[] loadCPs(File file) throws IOException,
			WorkbenchException, GeneralSecurityException {
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis, "UTF8"); //$NON-NLS-1$
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
	}

	/**
	 * Load all connection profiles from storage
	 * 
	 * @param file
	 * @param isp
	 * @return IConnectionProfile[]
	 * @throws IOException
	 * @throws WorkbenchException
	 */
	public static IConnectionProfile[] loadCPs(File file, ICipherProvider isp)
			throws IOException, WorkbenchException, GeneralSecurityException {
		IConnectionProfile retVal[];
		if (!file.exists())
			return new IConnectionProfile[0];
		InputStream is, fis = new FileInputStream(file);
		if (isp != null) {
			is = new CipherInputStream(fis, isp.createDecryptionCipher());
		}
		else {
			is = fis;
		}
		InputStreamReader isr = new InputStreamReader(is, "UTF8"); //$NON-NLS-1$
		Reader reader = new BufferedReader(isr);
		ConnectionProfile cp;
		ArrayList cps = new ArrayList();
		boolean updatedIDs = false;
		try {
			IMemento xmlMemento = XMLMemento.createReadRoot(reader), xmlExtraChild;
			IMemento[] xmlChildren = xmlMemento.getChildren(CHILDNAME);
			for (int i = 0; i < xmlChildren.length; i++) {
				updatedIDs = xmlChildren[i].getString(PROFILEID) == null;
				cp = new ConnectionProfile(xmlChildren[i]
						.getString(PROFILENAME), xmlChildren[i]
						.getString(PROFILEDESC), xmlChildren[i]
						.getString(PROVIDERID), xmlChildren[i]
						.getString(PROFILEPARENT), LITERAL_YES
						.equals(xmlChildren[i].getString(PROFILEAUTOCONNECT)),
						xmlChildren[i].getString(PROFILEID));

				// Note: If we can't find a provider associated with the cp, we
				// should abandon it.
				if (cp.getProvider() == null)
					continue;
				cp.setBaseProperties(keysToProperties(xmlChildren[i]));

				if (cp.getProfileExtensions().size() != 0) {
					for (Iterator it = cp.getProfileExtensions().entrySet()
							.iterator(); it.hasNext();) {
						Map.Entry me = (Map.Entry) it.next();
						String type = (String) me.getKey();
						xmlExtraChild = xmlChildren[i].getChild(type);
						if (xmlExtraChild != null)
							cp.setProperties(type,
									keysToProperties(xmlExtraChild));
					}
				}

				cp.setCreated();
				cps.add(cp);
			}
		}
		finally {
			reader.close();
		}
		retVal = (IConnectionProfile[]) cps.toArray(new IConnectionProfile[cps.size()]);
		if (updatedIDs) {
			saveCPs(retVal,file, isp);
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
}