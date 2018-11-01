/*******************************************************************************
 * Copyright (c) 2004-2013 Sybase, Inc. and others.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 *               IBM Corporation - fix for 243829
 *               Actuate Corporation - Bugzilla 300464
 *               Actuate Corporation - support for OSGi-less platform (Bugzilla 338997)
 *               IBM Corporation - Bugzilla 330725
 *               Actuate Corporation - Bugzilla 330725: fix for OSGi-less platform support
 *               IBM Corporation - Bugzilla 399992
 ******************************************************************************/
package org.eclipse.datatools.connectivity.drivers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.drivers.models.CategoryDescriptor;
import org.eclipse.datatools.connectivity.drivers.models.OverrideTemplateDescriptor;
import org.eclipse.datatools.connectivity.drivers.models.TemplateDescriptor;
import org.eclipse.datatools.connectivity.internal.ConnectivityPlugin;
import org.eclipse.datatools.connectivity.services.PluginResourceLocator;

import com.ibm.icu.util.StringTokenizer;

/**
 * This class provides a management-level interface for drivers.
 * 
 * @author brianf
 */
public class DriverManager {

	private static DriverManager sInstance;
	
	private static HashMap mDriverInstanceMap;
	
	private static boolean refreshDriverMap = false;
	
	private static String DRIVER_MARKER_FILE_NAME = "driverManagerPreferences.xml"; //$NON-NLS-1$
    private static final String EMPTY_STRING = ""; //$NON-NLS-1$
	
	private static boolean mDebug = ConnectivityPlugin.getDefault().isDebugging();

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

	private synchronized void loadAllInstances() {
		loadAllInstances(true);
	}
	
	private synchronized void loadAllInstances(boolean migrate) {
		debug ("loadAllInstances: migrate = " + migrate); //$NON-NLS-1$
		XMLFileManager.setFileName(IDriverMgmtConstants.DRIVER_FILE);
		try {
			IPropertySet[] psets = XMLFileManager.loadPropertySets();
			if (psets.length > 0) {
				boolean changed = false;
				for (int i = 0; i < psets.length; i++) {
					IPropertySet pset = psets[i];
					DriverInstance ndi = new DriverInstance(pset);
					if (migrate) {
						changed = ndi.migrate();
					}
					if (changed) {
						IPropertySet migratedPset = ndi.getPropertySet();
						debug ("loadAllInstances: migrated di = " + migratedPset.getID() ); //$NON-NLS-1$
						psets[i] = migratedPset;
						DriverInstance mndi = new DriverInstance(migratedPset);
						mDriverInstanceMap.put(mndi.getId(), mndi);
						saveChanges(psets);
						refreshDriverMap = true;
					}
					else 
						mDriverInstanceMap.put(ndi.getId(), ndi);
				}

				return;
			}
		}
		catch (CoreException e) {
			ConnectivityPlugin.getDefault().log(e);
		}
	}

	private void saveChanges(IPropertySet[] psets) {
		debug("saveChanges"); //$NON-NLS-1$
		XMLFileManager.setFileName(IDriverMgmtConstants.DRIVER_FILE);
		try {
			XMLFileManager.saveNamedPropertySet(psets);
//			syncDriverValuesChangesToPreferenceStore();
//			mDriverInstanceMap = new HashMap();
//			loadAllInstances();
		}
		catch (CoreException e) {
			ConnectivityPlugin.getDefault().log(e);
		}
	}

	private DriverManager() {
		resetDefaultInstances(true);
	}

	private DriverInstance getDriverInstanceFromMapByName( String name ) {
//		updatemDriverInstanceMap();
        Iterator iter = mDriverInstanceMap.values().iterator();
        while (iter.hasNext()) {
            DriverInstance di = (DriverInstance) iter.next();
            if (di.getName().equals(name))
                return di;
        }
		return null;
	}
	
	private DriverInstance getDriverInstanceFromMapByID( String id ) {
//		updatemDriverInstanceMap();
        return (DriverInstance) mDriverInstanceMap.get(id);
	}

	private DriverInstance[] getDriverInstancesFromMapByCategoryID( String categoryid ) {
//		updatemDriverInstanceMap();
		Iterator iter = mDriverInstanceMap.values().iterator();
		ArrayList<DriverInstance> list = new ArrayList<DriverInstance>();
		while (iter.hasNext()) {
			DriverInstance di = (DriverInstance) iter.next();
			// Bug 311028: It's possible for a template to be null so adding a null check to prevent an NPE
			TemplateDescriptor template = di.getTemplate();
			if(template != null) {
				// Bug 311028: the call to template.getParent() may return null so adding this null check to be safe.
				CategoryDescriptor templateParent = template.getParent();
				if((templateParent != null) && templateParent.getId().equals(categoryid)) {
					list.add(di);					
				}
			}
		}
		return list.toArray(new DriverInstance[list.size()]);
	}

	private DriverInstance[] getDriverInstancesFromMapForTemplateID( String templateid ) {
//		updatemDriverInstanceMap();
		Iterator iter = mDriverInstanceMap.values().iterator();
		ArrayList<DriverInstance> list = new ArrayList<DriverInstance>();
		while (iter.hasNext()) {
			DriverInstance di = (DriverInstance) iter.next();
			// Bug 311028: It's possible for a template to be null so adding a null check to prevent an NPE
			TemplateDescriptor template = di.getTemplate();
			if((template != null) && template.getId().equals(templateid)) {
				list.add(di);
			}
		}
		return list.toArray(new DriverInstance[list.size()]);
	}

	/**
	 * Retrieve a DriverInstance by Id
	 * @param id ID of the driver
	 * @return DriverInstance
	 */
	public DriverInstance getDriverInstanceByID(String id) {
		DriverInstance di = getDriverInstanceFromMapByID(id);
		if (di == null) {
			XMLFileManager.setFileName(IDriverMgmtConstants.DRIVER_FILE);
			try {
				IPropertySet[] psets = XMLFileManager.loadPropertySets();
				if (psets.length > 0) {
					for (int i = 0; i < psets.length; i++) {
						IPropertySet pset = psets[i];
						if (pset.getID().equals(id)) {
							di = new DriverInstance(pset);
							mDriverInstanceMap.put(di.getId(), di);
						}
					}
				}
			}
			catch (CoreException e) {
				ConnectivityPlugin.getDefault().log(e);
			}
		}
		return di;
	}

	public DriverInstance[] getDriverInstancesByCategory(String categoryid) {
		return getDriverInstancesFromMapByCategoryID(categoryid);
	}

	public DriverInstance[] getDriverInstancesByTemplate(String templateid) {
		return getDriverInstancesFromMapForTemplateID(templateid);
	}

	/**
	 * Retrieve a DriverInstance by name.
	 * @param name Name of the driver
	 * @return Driver Instance
	 */
	public DriverInstance getDriverInstanceByName(String name) {
		DriverInstance di = getDriverInstanceFromMapByName(name);
		if (di == null) {
			XMLFileManager.setFileName(IDriverMgmtConstants.DRIVER_FILE);
			try {
				IPropertySet[] psets = XMLFileManager.loadPropertySets();
				if (psets.length > 0) {
					for (int i = 0; i < psets.length; i++) {
						IPropertySet pset = psets[i];
						if (pset.getName().equals(name)) {
							di = new DriverInstance(pset);
							mDriverInstanceMap.put(di.getId(), di);
						}
					}
				}
			}
			catch (CoreException e) {
				ConnectivityPlugin.getDefault().log(e);
			}
		}
		return di;
	}

	/**
     * Retrieve DriverInstances by name.
     * @param name Name of the driver
     * @return Driver Instances
     */
    public DriverInstance[] getDriverInstancesByName(String name) {
        Vector driverInstanceCollection = new Vector();
            XMLFileManager.setFileName(IDriverMgmtConstants.DRIVER_FILE);
            try {
//        		updatemDriverInstanceMap();
                IPropertySet[] psets = XMLFileManager.loadPropertySets();
                if (psets.length > 0) {
                    for (int i = 0; i < psets.length; i++) {
                        IPropertySet pset = psets[i];
                        if (pset.getName().equals(name)) {
                            DriverInstance driver = new DriverInstance(pset);
                            driverInstanceCollection.add(new DriverInstance(pset));
                            mDriverInstanceMap.put(driver.getId(), driver);
                        }
                    }
                }
            }
            catch (CoreException e) {
                ConnectivityPlugin.getDefault().log(e);
            }
            DriverInstance[] driverInstanceArray = new DriverInstance [driverInstanceCollection.size()];
            driverInstanceCollection.copyInto(driverInstanceArray);
        return driverInstanceArray;
    }

	/**
	 * Return a comma-delimited list of all jars for all drivers.
	 * @return String
	 */
	public String getFullJarList() {
//		updatemDriverInstanceMap();
		Object[] drivers = mDriverInstanceMap.values().toArray();
		String fullList = EMPTY_STRING;
		for (int x = 0; x < drivers.length; x++) {
			DriverInstance di = (DriverInstance) drivers[x];
			if (di.getJarList() != null) {
				String jarlist = di.getJarList().trim();
				if (fullList.trim().length() > 0)
					fullList = fullList + IDriverMgmtConstants.PATH_DELIMITER;
				fullList = fullList + jarlist
					+ IDriverMgmtConstants.PATH_DELIMITER;
			}
			if (fullList
					.substring(fullList.length() - 1, fullList.length())
					.equals(IDriverMgmtConstants.PATH_DELIMITER)) {
				fullList = fullList.substring(0, fullList.length() - 1);
			}
		}
		if (fullList.trim().length() > 0) {
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

			String newList = EMPTY_STRING;
			Iterator iter2 = list.iterator();
			while (iter2.hasNext()) {
				newList = newList + iter2.next()
						+ IDriverMgmtConstants.PATH_DELIMITER;
			}
			if (newList.length() > 0) {
				if (newList.substring(newList.length() - 1, newList.length())
						.equals(IDriverMgmtConstants.PATH_DELIMITER)) {
					newList = newList.substring(0, newList.length() - 1);
				}
			}
			return newList;
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
//		updatemDriverInstanceMap();
		Iterator iter = mDriverInstanceMap.values().iterator();
		ArrayList list = new ArrayList();
		while (iter.hasNext()) {
			DriverInstance di = (DriverInstance) iter.next();
			DriverValidator validator = new DriverValidator(di);
			if (validator.isValid())
				list.add(di);
		}
		array = new DriverInstance[list.size()];
		for (int i = 0; i < list.size(); i++) {
			array[i] = (DriverInstance) list.get(i);
		}

		return array;
	}
	
	/**
	 * Returns an array of all driver instances
	 * @return DriverInstance[]
	 */
	public DriverInstance[] getAllDriverInstances() {
//		updatemDriverInstanceMap();
		return (DriverInstance[]) 
			mDriverInstanceMap.values().toArray(
					new DriverInstance[mDriverInstanceMap.values().size()]);
	}

	private IPropertySet[] getPropertySetsFromMap() {
//		updatemDriverInstanceMap();
		Iterator iter = mDriverInstanceMap.values().iterator();
		ArrayList list = new ArrayList();
		while (iter.hasNext()) {
			DriverInstance di = (DriverInstance) iter.next();
			list.add(di.getPropertySet());
		}
		return (IPropertySet[]) list.toArray(new IPropertySet[list.size()]);
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
            String name, String jarList, String driverClass) {
        if (templateID == null) return null;
        if (name == null) return null;
        
        IPropertySet pset = createDefaultInstance(templateID);
        
        // if for some reason, we get back a null, pass that back
        if (pset == null)
            return null;
        
        if (name != null)
            pset.setName(name);
        String prefix = DriverMgmtMessages
                .getString("NewDriverDialog.text.id_prefix"); //$NON-NLS-1$
        String id = prefix + templateID + "." + name; //$NON-NLS-1$
        pset.setID(id);
        Properties props = pset.getBaseProperties();
        if (jarList != null)
            props.setProperty(IDriverMgmtConstants.PROP_DEFN_JARLIST, jarList);
        props.setProperty("org.eclipse.datatools.connectivity.db.driverClass", driverClass);
        addDriverInstance(pset);
        return getDriverInstanceByID(pset.getID());
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
//		if (jarList == null) return null;
		
		IPropertySet pset = createDefaultInstance(templateID);
		
		// if for some reason, we get back a null, pass that back
		if (pset == null)
			return null;
		
		if (name != null)
			pset.setName(name);
		String prefix = DriverMgmtMessages
				.getString("NewDriverDialog.text.id_prefix"); //$NON-NLS-1$
		String id = prefix + templateID + "." + name; //$NON-NLS-1$
		pset.setID(id);
		Properties props = pset.getBaseProperties();
		if (jarList != null)
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
							DriverValidator.removeOldProblemMarkers(pset.getName());
						}
						else {
							newPsets[counter] = pset;
							counter++;
						}
					}
					if (rtnFlag == true) {
						XMLFileManager.saveNamedPropertySet(newPsets);
//						syncDriverValuesChangesToPreferenceStore();
						mDriverInstanceMap = new HashMap();
						loadAllInstances();
					}
				}
			}
			catch (CoreException e) {
				ConnectivityPlugin.getDefault().log(e);
			}
		}
		return rtnFlag;
	}

	public void addDriverInstance ( DriverInstance di ) {
//		updatemDriverInstanceMap();
		mDriverInstanceMap.put(di.getId(), di);
		IPropertySet[] psets = getPropertySetsFromMap();
		XMLFileManager.setFileName(IDriverMgmtConstants.DRIVER_FILE);
		try {
			XMLFileManager.saveNamedPropertySet(psets);
//			syncDriverValuesChangesToPreferenceStore();
			mDriverInstanceMap = new HashMap();
			loadAllInstances();
		}
		catch (CoreException e) {
			ConnectivityPlugin.getDefault().log(e);
		}
	}
	
	/**
	 * Adds a new driver instance to the Drivers file
	 * @param pset IPropertySet
	 */
	public void addDriverInstance(IPropertySet pset) {
		DriverInstance di = new DriverInstance(pset);
		addDriverInstance(di);
	}

	public void addDriverInstances(IPropertySet[] propertySets) {
		
		for(int i = 0; i < propertySets.length;i++)	{
			DriverInstance di = new DriverInstance(propertySets[i]);
			mDriverInstanceMap.put(di.getId(), di);
		}
		IPropertySet[] psets = getPropertySetsFromMap();
		saveChanges(psets);
		mDriverInstanceMap = new HashMap();
		loadAllInstances();
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
	 * Checks to see if the default drivers marker exists, which
	 * indicates that default driver definitions were created already.
	 * @return
	 */
	private boolean wereDefaultDriversCreated() {
		IPath metadataPath = 
			ConnectivityPlugin.getWorkspaceFilePath(DRIVER_MARKER_FILE_NAME);
		File file = metadataPath.toFile();
		if (file.exists()){
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(file);
				ConnectivityPlugin.getDefault().loadPreferences( fis );
			} catch (FileNotFoundException e) {
				ConnectivityPlugin.getDefault().log(e);
			} catch (IOException e) {
				ConnectivityPlugin.getDefault().log(e);
			} finally {
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						ConnectivityPlugin.getDefault().log(e);
					}
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * Creates the default drivers marker if it didn't already exist
	 * @return
	 */
	private boolean createDefaultDriversMarker() {
		IPath metadataPath = 
			ConnectivityPlugin.getWorkspaceFilePath(DRIVER_MARKER_FILE_NAME);
		File file = metadataPath.toFile();
		if (!file.exists()){
			try {
				file.createNewFile();
				return true;
			} catch (IOException e) {
			}
		}
		return false;
	}
	
	private boolean wasDefaultCreatedBefore(TemplateDescriptor td) {
		return ConnectivityPlugin.getDefault().getPreferenceBooleanValue( td.getId() );
	}
	
//	/**
//	 * persists any changes in driver definitions preferences to its preference store 
//	 * @return true in case both are not equal
//	 */
//	public static boolean syncDriverValuesChangesToPreferenceStore(){
//		boolean changed = syncDriverChangesToPreferenceStore();
//		if (changed){
//			syncPreferenceStoreAndDriverManagerXML();
//		}
//		return changed;		
//	}

//	/**
//	 * persists any Driver definitions preference store changes due to import to Driver definitions 
//	 * @return true in case both are not equal
//	 */
//	public static boolean syncPreferenceStoreChangesToDriverValues(){
//		boolean changed = syncPreferenceStoreChangesToDriver();
//		if(changed){
//			syncPreferenceStoreAndDriverManagerXML();	
//		}
//
//		return changed;
//	}

//	private static boolean syncDriverChangesToPreferenceStore() {
//		String driverValues = EMPTY_STRING;
//		String driverValuesTemp = EMPTY_STRING;
//		IPath metadataPath = ConnectivityPlugin.getWorkspaceFilePath(IDriverMgmtConstants.DRIVER_FILE);
//		File file = metadataPath.toFile();
//		if (file.exists()){
//			FileReader fr = null;
//			BufferedReader br = null;
//			try {				
//				fr = new FileReader(file);
//				br = new BufferedReader(fr);
//
//				if((driverValuesTemp = br.readLine())!=null){
//					driverValues = driverValuesTemp;
//				}
//				String preferenceStoreDriverValues = ConnectivityPlugin.getDefault().getPreferenceStringValue(IDriverMgmtConstants.DRIVER_VALUES);
//
//				if(!(driverValues.equals(preferenceStoreDriverValues))){
//					// setting key value preference pair in preference store
//					ConnectivityPlugin.getDefault().setPreferenceValue(IDriverMgmtConstants.DRIVER_VALUES, driverValues);
//					return true;
//				}
//			} catch (FileNotFoundException e) {
//				ConnectivityPlugin.getDefault().log(e);
//			} catch (IOException e) {
//				ConnectivityPlugin.getDefault().log(e);
//			}finally{
//				if(br!=null){
//					try {
//						br.close();
//					} catch (IOException e) {
//						ConnectivityPlugin.getDefault().log(e);
//					}
//					if(fr!=null){
//						try {
//							fr.close();
//						} catch (IOException e) {
//							ConnectivityPlugin.getDefault().log(e);
//						}
//					}
//				}
//			}
//		}
//		return false;
//	}

//	private static void syncPreferenceStoreAndDriverManagerXML(){
//		IPath metadataPath = ConnectivityPlugin.getWorkspaceFilePath(DRIVER_MARKER_FILE_NAME);
//		File file = metadataPath.toFile();
//		FileOutputStream fos = null;
//		try {
//			fos = new FileOutputStream(file);
//			ConnectivityPlugin.getDefault().storePreferences(fos, "DriverManager.Preferences"); //$NON-NLS-1$
//		} catch (FileNotFoundException e) {
//			ConnectivityPlugin.getDefault().log(e);
//		} catch (IOException e) {
//			ConnectivityPlugin.getDefault().log(e);
//		} finally {
//			if (fos != null) {
//				try {
//					fos.close();
//				} catch (IOException e) {
//					ConnectivityPlugin.getDefault().log(e);
//				}
//			}
//		}
//	}

//	private static boolean syncPreferenceStoreChangesToDriver(){
//		String preferenceStoreDriverValues = 
//		        ConnectivityPlugin.getDefault().getPreferenceStringValue(IDriverMgmtConstants.DRIVER_VALUES);
//		if( preferenceStoreDriverValues.equals( EMPTY_STRING ) )
//		    return false;     // no driver values in preference store, nothing to sync
//		
//        String driverValues = EMPTY_STRING;
//        String driverValuesTemp = EMPTY_STRING;
//		IPath metadataPath = ConnectivityPlugin.getWorkspaceFilePath(IDriverMgmtConstants.DRIVER_FILE);
//		File file = metadataPath.toFile();
//		if (file.exists()){
//			FileReader fr = null;
//			BufferedReader br = null;
//			try {
//				fr = new FileReader(file);
//				br = new BufferedReader(fr);
//
//				if((driverValuesTemp = br.readLine())!=null){
//					driverValues = driverValuesTemp;
//				}
//
//			} catch (FileNotFoundException e) {
//				ConnectivityPlugin.getDefault().log(e);
//			} catch (IOException e) {
//				ConnectivityPlugin.getDefault().log(e);
//			} finally{
//				if(br!=null){
//					try {
//						br.close();
//					} catch (IOException e) {
//						ConnectivityPlugin.getDefault().log(e);
//					}
//					if(fr!=null){
//						try {
//							fr.close();
//						} catch (IOException e) {
//							ConnectivityPlugin.getDefault().log(e);
//						}
//					}
//				}
//			}
//
//			if(!(driverValues.equals(preferenceStoreDriverValues))){
//				FileWriter fw = null;
//				BufferedWriter bw = null;
//				try{
//					fw = new FileWriter(file);
//					bw = new BufferedWriter(fw);
//					bw.write(preferenceStoreDriverValues);
//
//					return true;
//				}catch (FileNotFoundException e) {
//					ConnectivityPlugin.getDefault().log(e);
//				} catch (IOException e) {
//					ConnectivityPlugin.getDefault().log(e);
//				} finally{
//					if(bw!=null){
//						try {
//							bw.close();
//						} catch (IOException e) {
//							ConnectivityPlugin.getDefault().log(e);
//						}
//						if(fw!=null){
//							try {
//								fw.close();
//							} catch (IOException e) {
//								ConnectivityPlugin.getDefault().log(e);
//							}
//						}
//					}
//				}
//			}
//		}
//		//To take care of the case when Driver Definitions are not initialized but preference store has been set due to import 
//		else if( !file.exists() )
//		{
//			FileWriter fw = null;
//			BufferedWriter bw = null;
//			try{
//				fw = new FileWriter(file);
//				bw = new BufferedWriter(fw);
//				bw.write(preferenceStoreDriverValues);
//
//				return true;
//			}catch (FileNotFoundException e) {
//				ConnectivityPlugin.getDefault().log(e);
//			} catch (IOException e) {
//				ConnectivityPlugin.getDefault().log(e);
//			} finally{
//				if(bw!=null){
//					try {
//						bw.close();
//					} catch (IOException e) {
//						ConnectivityPlugin.getDefault().log(e);
//					}
//					if(fw!=null){
//						try {
//							fw.close();
//						} catch (IOException e) {
//							ConnectivityPlugin.getDefault().log(e);
//						}
//					}
//				}
//			}
//		}
//		return false;
//	}

//	/**
//	 * mDriverInstanceMap containing driver definition objects has to be updated in case there is a change in driver definition preferences 
//	 * @return true in case update has happened
//	 */
//	private boolean updatemDriverInstanceMap(){
//		if(syncPreferenceStoreChangesToDriverValues()){
//			mDriverInstanceMap = new HashMap();
//			loadAllInstances();
//			return true;
//		}
//		return false;
//	}
	
	/**
	 * Creates any default driver template instances that need to be created.
	 * This is when the plug-in is loaded.
	 */
	public void resetDefaultInstances() {
		resetDefaultInstances(false);
	}
	
	public void resetDefaultInstances(boolean batchUpdate) {
		debug ("resetDefaultInstances"); //$NON-NLS-1$
		
		// Start building a list
		ArrayList psets_list = new ArrayList();

		// Grab all the driver templates
		TemplateDescriptor types[] = TemplateDescriptor
				.getDriverTemplateDescriptors();

		// prep the file for reading
		XMLFileManager.setFileName(IDriverMgmtConstants.DRIVER_FILE);

		// load the hash map from the file to in memory
		if (mDriverInstanceMap == null || refreshDriverMap) {
			debug ("resetDefaultInstances: loading hash map"); //$NON-NLS-1$
			mDriverInstanceMap = new HashMap();
			loadAllInstances(true);
			if (refreshDriverMap) 
				refreshDriverMap = false;
		}

		wereDefaultDriversCreated();

		debug ("resetDefaultInstances: checking for drivers to create by default"); //$NON-NLS-1$

		// Now grab all the driver instances from the file
		IPropertySet[] psets = getPropertySetsFromMap();

		List<IPropertySet> pendingPropertySets = null;
		List<TemplateDescriptor> pendingDefaultsCreated = null;
		boolean updatePending = false;
		if (batchUpdate) {
			pendingPropertySets = new ArrayList<IPropertySet>();
			pendingDefaultsCreated = new ArrayList<TemplateDescriptor>();
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
			
			boolean defaultExists = wasDefaultCreatedBefore(type);

			// if we need to create one and it doesn't already
			// exist, create one and add it to the list.
			boolean createDefaultValue = false;
			if (type.getValuesProviderClass() != null) {
				String value = 
					type.getValuesProviderClass().createDefaultValue(IDriverValuesProvider.VALUE_CREATE_DEFAULT);
				if (value != null) {
					createDefaultValue = Boolean.valueOf(value).booleanValue();
				}
			}
			IDriverValuesProvider overrideDriverValsProvider = null;
			OverrideTemplateDescriptor[] otds = 
				OverrideTemplateDescriptor.getByDriverTemplate(type.getId());
			if (otds != null && otds.length > 0) {
				overrideDriverValsProvider =
					otds[0].getValuesProviderClass();
				if (overrideDriverValsProvider != null) {
					String value = 
						overrideDriverValsProvider.createDefaultValue(IDriverValuesProvider.VALUE_CREATE_DEFAULT);
					if (value != null) {
						createDefaultValue = Boolean.valueOf(value).booleanValue();
					}
				}
			}
			
			debug("Default already exists for " + type.getId() + ": " + defaultExists); //$NON-NLS-1$ //$NON-NLS-2$
			if ((createDefaultValue || type.getCreateDefaultFlag()) && !defaultExists && !alreadyExists) {
				IPropertySet newPset = createDefaultInstance(type);
				if (newPset != null) {
					if (batchUpdate) {
						pendingPropertySets.add(newPset);
						pendingDefaultsCreated.add(type);
						updatePending = true;
					} else {
						addDriverInstance(newPset);
						setDefaultCreated( type.getId(), true );
					}
				}			
			}
		}
		
		if (batchUpdate && updatePending) {
			addDriverInstances(pendingPropertySets.toArray(new IPropertySet[0]));
			setDefaultCreated(pendingDefaultsCreated.toArray(new TemplateDescriptor[0]), true);
		}
		
		boolean markerCreated = createDefaultDriversMarker();
		debug("Marker created: " + markerCreated); //$NON-NLS-1$
	}

	private void setDefaultCreated(TemplateDescriptor[] types, boolean isDefaultCreated) {
		for (TemplateDescriptor type : types) {
			ConnectivityPlugin.getDefault().setPreferenceValue( type.getId(), isDefaultCreated );
		}
		
		savePreferences();
	}
	
	private void setDefaultCreated( String driverTemplateId, boolean isDefaultCreated )
	{
        ConnectivityPlugin.getDefault().
            setPreferenceValue( driverTemplateId, isDefaultCreated );
        
            savePreferences();
	}
	
	private void savePreferences() {	
        IPath metadataPath = 
            ConnectivityPlugin.getWorkspaceFilePath(DRIVER_MARKER_FILE_NAME);
        File file = metadataPath.toFile();
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
            }
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            ConnectivityPlugin.getDefault().
                storePreferences( fos, 
                        "DriverManager.Preferences"); //$NON-NLS-1$
        } catch (FileNotFoundException e) {
            ConnectivityPlugin.getDefault().log(e);
        } catch (IOException e) {
            ConnectivityPlugin.getDefault().log(e);
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    ConnectivityPlugin.getDefault().log(e);
                }
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

		IDriverValuesProvider driverValsProvider = null;
		IDriverValuesProvider overrideDriverValsProvider = null;
		OverrideTemplateDescriptor[] otds = null; 
		if (template != null) {
			
			debug("Creating Default Instance of " + template.getId()); //$NON-NLS-1$
			otds = OverrideTemplateDescriptor.getByDriverTemplate(template.getId());
			if (otds != null && otds.length > 0) {
				overrideDriverValsProvider =
					otds[0].getValuesProviderClass();
				if (overrideDriverValsProvider != null) {
					debug("Have a driver values provider from the override"); //$NON-NLS-1$
				}
			}
			driverValsProvider =
				(IDriverValuesProvider) template.getValuesProviderClass();
			if (driverValsProvider != null) {
				debug("Have a driver values provider from the template"); //$NON-NLS-1$
			}
		}
		
		boolean createDefault = (template != null) ? template.getCreateDefaultFlag() : false;
		debug("Create Default from the template is " + createDefault); //$NON-NLS-1$
		if (driverValsProvider != null) {
			String valsCreateDefault = driverValsProvider.createDefaultValue(IDriverValuesProvider.VALUE_CREATE_DEFAULT);
			if (valsCreateDefault != null) {
				createDefault = Boolean.valueOf(valsCreateDefault).booleanValue();
				debug("Create Default was reset to " + valsCreateDefault + " by the driver values provider"); //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
		if (overrideDriverValsProvider != null) {
			String overrideValsCreateDefault = overrideDriverValsProvider.createDefaultValue(IDriverValuesProvider.VALUE_CREATE_DEFAULT);
			if (overrideValsCreateDefault != null) {
				createDefault = Boolean.valueOf(overrideValsCreateDefault).booleanValue();
				debug("Create Default was reset to " + overrideValsCreateDefault + " by the override driver values provider"); //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
		
		if (template != null && (createDefault || override)) {

			// generate the id and name for the new instance
			String prefix = DriverMgmtMessages
					.getString("NewDriverDialog.text.id_prefix"); //$NON-NLS-1$
			String suffix = DriverMgmtMessages
					.getString("DriverMgmtPlugin.default_instance_suffix"); //$NON-NLS-1$			
					
			String name = template.getDefaultDefinitionName() + " " + suffix;//$NON-NLS-1$
			String id = prefix + template.getId()+ "." + name;
			
			debug("Default driver name from the template is " + name); //$NON-NLS-1$
			if (driverValsProvider != null) {
				String driverValsName = driverValsProvider.createDefaultValue(IDriverValuesProvider.VALUE_NAME);
				if (driverValsName != null) {
					name = driverValsName;
					debug("Driver name was reset to " + driverValsName + " by the driver values provider"); //$NON-NLS-1$ //$NON-NLS-2$
				}
				String driverValsDefaultDefinitionName = driverValsProvider.createDefaultValue(IDriverValuesProvider.VALUE_DEFAULT_DEFINITION_NAME);
				if (driverValsDefaultDefinitionName != null) {
					name = driverValsDefaultDefinitionName;
					debug("Driver name was reset to " + driverValsDefaultDefinitionName + " (Default Definition Name) by the driver values provider"); //$NON-NLS-1$ //$NON-NLS-2$
				}
			}
			if (overrideDriverValsProvider != null) {
				String overrideDriverValsName = overrideDriverValsProvider.createDefaultValue(IDriverValuesProvider.VALUE_NAME);
				if (overrideDriverValsName != null) {
					name = overrideDriverValsName;
					debug("Driver name was reset to " + overrideDriverValsName + " by the override driver values provider"); //$NON-NLS-1$ //$NON-NLS-2$
				}
				String overrideDriverValsDefaultDefinitionName = overrideDriverValsProvider.createDefaultValue(IDriverValuesProvider.VALUE_DEFAULT_DEFINITION_NAME);
				if (overrideDriverValsDefaultDefinitionName != null) {
					name = overrideDriverValsDefaultDefinitionName;
					debug("Driver name was reset to " + overrideDriverValsDefaultDefinitionName + " (Default Definition Name) by the override driver values provider"); //$NON-NLS-1$ //$NON-NLS-2$
				}
			}

			// Now create the property set
			PropertySetImpl propset = new PropertySetImpl(id, template
					.getName());
			propset.setID(id);
			propset.setName(name);

			// create the base properties
			Properties props = new Properties();

			String jarList = EMPTY_STRING;
			String valsJarList = null;
			String overrideValsJarList = null;
			
			if (driverValsProvider != null) {
				valsJarList = driverValsProvider.createDefaultValue(IDriverValuesProvider.VALUE_JARLIST);
				if (valsJarList != null) {
					jarList = valsJarList;
					debug("Jar list was reset to " + valsJarList + " by the driver values provider"); //$NON-NLS-1$ //$NON-NLS-2$
				}
			}
			if (overrideDriverValsProvider != null) {
				overrideValsJarList = overrideDriverValsProvider.createDefaultValue(IDriverValuesProvider.VALUE_JARLIST);
				if (overrideValsJarList != null) {
					jarList = overrideValsJarList;
					debug("Jar list was reset to " + overrideValsJarList + " by the override driver values provider"); //$NON-NLS-1$ //$NON-NLS-2$
				}
			}
			if (valsJarList == null && overrideValsJarList == null) {
				jarList = updatePluginJarList(template);
				debug("Default jar list from the template is " + jarList); //$NON-NLS-1$
			}

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
					debug("Default Value of property " + propid + " from the template is " + propvalue); //$NON-NLS-1$ //$NON-NLS-2$
					boolean removeIt = false;
					if (driverValsProvider != null) {
						String valsPropValue = driverValsProvider.createDefaultValue(propid);
						if (valsPropValue != null) {
							propvalue = valsPropValue;
							debug("Value of property " + propid + " was reset to " + valsPropValue + " by the driver values provider"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						}
					}
					if (otds != null && otds.length > 0) {
						String temp =
							otds[0].getPropertyValueFromId(propid);
						if (temp != null && temp.length() > 0) {
							propvalue = temp;
							debug("Value of property " + propid + " was reset to " + temp + " by a driver override"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						}
						if (otds[0].getPropertyRemoveFlagFromID(propid)) {
							removeIt = true;
							debug("Property " + propid + " was removed by a driver override"); //$NON-NLS-1$ //$NON-NLS-2$
						}
					}
					if (overrideDriverValsProvider != null) {
						String overrideValsPropValue = overrideDriverValsProvider.createDefaultValue(propid);
						if (overrideValsPropValue != null) {
							propvalue = overrideValsPropValue;
							debug("Value of property " + propid + " was reset to " + overrideValsPropValue + " by the override driver values provider"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						}
					}
					if (!removeIt) 
						props.setProperty(propid, propvalue == null ? new String()
							: propvalue);
					else
						props.remove(propid);
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
	 * Removes the default driver instance of the specified driver template,
	 * if it is invalid.
	 * It may also optionally reset the state of having created a default instance to false.
	 * @param driverTemplateId the id of a driver template type
	 * @param resetDefaultCreated    true to reset the state of having created 
	 *         a default instance if it is removed; false to leave the state as is
	 * @return true if the specified driver template's default driver instance is invalid
	 *             and successfully removed; false otherwise
	 * @since 1.7.2
	 */
	public boolean removeInvalidDefaultInstance( String driverTemplateId, boolean resetDefaultCreated ) {
	    if( driverTemplateId == null || driverTemplateId.length() == 0 )
	        return false;
	    
        TemplateDescriptor driverTemplate = 
            TemplateDescriptor.getDriverTemplateDescriptor( driverTemplateId );
	    if ( driverTemplate == null  )
	        return false;
	    
	    String defaultDefnName = driverTemplate.getDefaultDefinitionName();
        if ( defaultDefnName == null || defaultDefnName.length() == 0 )
            return false;
	    
        DriverInstance defaultInstance = getDriverInstanceByName( defaultDefnName );
        if ( defaultInstance == null  )
            return false;

        if( new DriverValidator( defaultInstance ).isValid( false ) )
            return false;   // valid driver instance is not removed

	    boolean isRemoved = removeDriverInstance( defaultInstance.getId() );

	    // reset the state of having previously created a default instance to false
	    if ( isRemoved && resetDefaultCreated )
	        setDefaultCreated( driverTemplateId, false );

	    return isRemoved;
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
			int index = jarList.indexOf(IDriverMgmtConstants.PATH_DELIMITER, 0);
			if (index > 0) {
				// don't do anything, the list is fine and already using the platform-specific delimiter
			}
			else {
				index = jarList.indexOf(IDriverMgmtConstants.PATH_DELIMITER_COMMA, 0);
				if (index > 0) {
					// found commas, replace with the platform-specific delimiter
					jarList = jarList.replace(',', IDriverMgmtConstants.PATH_DELIMITER_CHAR);
				}
				else {
					index = jarList.indexOf(IDriverMgmtConstants.PATH_DELIMITER_SEMICOLON, 0);
					if (index > 0) {
						// found semi-colons, replace with the platform-specific delimiter
						jarList = jarList.replace(';', IDriverMgmtConstants.PATH_DELIMITER_CHAR);
					}
				}
			}
			index = jarList.indexOf("["); //$NON-NLS-1$
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
				if (jarList.indexOf(IDriverMgmtConstants.PATH_DELIMITER, index) > 0) {
					restOfPath = jarList
							.substring(
									jarList.indexOf("]", index) + 1, jarList.indexOf(IDriverMgmtConstants.PATH_DELIMITER, index)); //$NON-NLS-1$
				}
				else if (jarList.indexOf(IDriverMgmtConstants.PATH_DELIMITER_COMMA, index) > 0) {
					restOfPath = jarList
							.substring(
									jarList.indexOf("]", index) + 1, jarList.indexOf(IDriverMgmtConstants.PATH_DELIMITER_COMMA, index)); //$NON-NLS-1$
				}
				else if (jarList.indexOf(IDriverMgmtConstants.PATH_DELIMITER_SEMICOLON, index) > 0) {
					restOfPath = jarList
							.substring(
									jarList.indexOf("]", index) + 1, jarList.indexOf(IDriverMgmtConstants.PATH_DELIMITER_SEMICOLON, index)); //$NON-NLS-1$
				}
				else {
					restOfPath = jarList.substring(
							jarList.indexOf("]", index) + 1, jarList.length()); //$NON-NLS-1$
				}

				if( Platform.getBundle(pluginId) != null || 
				        ! ConnectivityPlugin.isRunningOSGiPlatform() ) {
					IPath path = null;
					if (restOfPath == null || restOfPath.trim().length() == 0) {
					    path = PluginResourceLocator.getPluginRootPath( pluginId );
					}
					else {
	                    String entry = File.separator + restOfPath + File.separator;
	                    path = PluginResourceLocator.getPluginEntryPath( pluginId, entry );
					}

					if (path != null) {
					    int totalLength = toReplace.length()
									+ restOfPath.length();
					    jarList = jarList.substring(0, index)
									+ path.toOSString()
									+ jarList.substring(index + totalLength,
											jarList.length());
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

				index = jarList.indexOf(IDriverMgmtConstants.PATH_DELIMITER, index);
				if (index > 0) {
					index++;
					index = jarList.indexOf("[", index); //$NON-NLS-1$
				}
			}
		} else {
			// no [plugin] references, but let's see if we can clean up the delimiters to be platform-specific
			int index = jarList.indexOf(IDriverMgmtConstants.PATH_DELIMITER, 0);
			if (index > 0) {
				// don't do anything, the list is fine and already using the platform-specific delimiter
				return jarList;
			}
			else {
				index = jarList.indexOf(IDriverMgmtConstants.PATH_DELIMITER_COMMA, 0);
				if (index > 0) {
					// found commas, replace with the platform-specific delimiter
					jarList = jarList.replace(',', IDriverMgmtConstants.PATH_DELIMITER_CHAR);
				}
				else {
					index = jarList.indexOf(IDriverMgmtConstants.PATH_DELIMITER_SEMICOLON, 0);
					if (index > 0) {
						// found semi-colons, replace with the platform-specific delimiter
						jarList = jarList.replace(';', IDriverMgmtConstants.PATH_DELIMITER_CHAR);
					}
				}
			}
			
		}

		return jarList;
	}

	public static void debug ( String msg ) {
		if (mDebug)
			System.out.println("Debug: " + msg); //$NON-NLS-1$
	}
}
