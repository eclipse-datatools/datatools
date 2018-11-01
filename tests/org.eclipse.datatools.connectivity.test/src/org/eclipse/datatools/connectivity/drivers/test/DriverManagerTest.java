/*******************************************************************************
 * Copyright (c) 2004-2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.drivers.test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.drivers.DriverInstance;
import org.eclipse.datatools.connectivity.drivers.DriverManager;
import org.eclipse.datatools.connectivity.drivers.DriverMgmtMessages;

/**
 * This tests the driver management framework
 * 
 * @author brianf
 */
public class DriverManagerTest extends TestCase {

	private static String TEST_DRIVER_1_TEMPLATE_ID = "org.eclipse.datatools.connectivity.drivers.test.driver1"; //$NON-NLS-1$
	private static String TEST_DRIVER_2_TEMPLATE_ID = "org.eclipse.datatools.connectivity.drivers.test.driver2"; //$NON-NLS-1$
	private static String TEST_DRIVER_1_INSTANCE_NAME = "DriverTest.TestDriver1Instance"; //$NON-NLS-1$
	private static String TEST_DRIVER_2_INSTANCE_NAME = "DriverTest.TestDriver21Instance"; //$NON-NLS-1$
	private static String ID_PREFIX = DriverMgmtMessages.getString("NewDriverDialog.text.id_prefix"); //$NON-NLS-1$
	private static String TEST_DRIVER_1_INSTANCE_ID = ID_PREFIX + TEST_DRIVER_1_TEMPLATE_ID;
	private static String TEST_DRIVER_1_INSTANCE_ID2 = ID_PREFIX + TEST_DRIVER_1_INSTANCE_NAME;
	private static String TEST_DRIVER_2_INSTANCE_ID = ID_PREFIX + TEST_DRIVER_2_INSTANCE_NAME;
	
	public static void createDriverInstance2() {
		Assert.assertNull(DriverManager.getInstance().getDriverInstanceByID(TEST_DRIVER_2_TEMPLATE_ID));

		String jarList;
		IPath path = filePathFromPlugin("org.eclipse.datatools.connectivity.test", "test_files/dummy2.jar");  //$NON-NLS-1$//$NON-NLS-2$
		jarList = path.toString();
		
		File file = new File(jarList);
		
		Assert.assertTrue(file.exists());
		System.out.println("Found DUMMY2.JAR: " + file.exists());//$NON-NLS-1$
		
		DriverInstance driverInstance = DriverManager.getInstance().createNewDriverInstance(TEST_DRIVER_2_TEMPLATE_ID, TEST_DRIVER_2_INSTANCE_NAME, jarList);
		Assert.assertNotNull(DriverManager.getInstance().getDriverInstanceByID(TEST_DRIVER_2_INSTANCE_ID));
		System.out.println("Test Driver 2 Instance Created: " + driverInstance.getName()); //$NON-NLS-1$
	}
	
	public static IPath filePathFromPlugin(String pluginId, String filePath) {
		if (pluginId == null || filePath == null) {
			throw new IllegalArgumentException();
		}
		
		 URL url = Platform.getBundle(pluginId).getEntry(File.separator + filePath + File.separator); 
		 try {
			url = FileLocator.toFileURL(url);
			
			IPath path = new Path(url.getFile());
			return path;
		} catch (IOException e) {
			// do nothing
		} 
		 
		return null;
	}

	public void testCreateNewDriverInstance() {
		
		DriverManager.getInstance().resetDefaultInstances();
		
		String jarList;
		IPath path = filePathFromPlugin("org.eclipse.datatools.connectivity.test", "test_files/dummy2.jar");  //$NON-NLS-1$//$NON-NLS-2$
		jarList = path.toString();
		
		File file = new File(jarList);
		
		Assert.assertTrue(file.exists());
		System.out.println("Found DUMMY2.JAR: " + file.exists());//$NON-NLS-1$
		
		DriverInstance driverInstance = DriverManager.getInstance().createNewDriverInstance(TEST_DRIVER_2_TEMPLATE_ID, TEST_DRIVER_2_INSTANCE_NAME, jarList);
		Assert.assertNotNull(DriverManager.getInstance().getDriverInstanceByID(TEST_DRIVER_2_INSTANCE_ID));
		System.out.println("Test Driver 2 Instance Created: " + driverInstance.getName()); //$NON-NLS-1$
		
		DriverManager.getInstance().removeDriverInstance(TEST_DRIVER_1_INSTANCE_ID);
		Assert.assertNull(DriverManager.getInstance().getDriverInstanceByID(TEST_DRIVER_1_INSTANCE_ID));
		System.out.println("Default Test Driver 1 Instance Removed"); //$NON-NLS-1$
		
		path = filePathFromPlugin("org.eclipse.datatools.connectivity.test", "test_files/dummy1.jar");  //$NON-NLS-1$//$NON-NLS-2$
		jarList = path.toString();

		file = new File(jarList);
		
		Assert.assertTrue(file.exists());
		System.out.println("Found DUMMY1.JAR: " + file.exists());//$NON-NLS-1$

		driverInstance = DriverManager.getInstance().createNewDriverInstance(TEST_DRIVER_1_TEMPLATE_ID, TEST_DRIVER_1_INSTANCE_NAME, jarList);
		Assert.assertNotNull(DriverManager.getInstance().getDriverInstanceByID(TEST_DRIVER_1_INSTANCE_ID2));
		System.out.println("Test Driver 1 Instance Created: " + driverInstance.getName()); //$NON-NLS-1$
	}

	public void testCreateNewDriverInstanceNullNullNull() {
		
		DriverManager.getInstance().resetDefaultInstances();
		
		DriverInstance driverInstance = DriverManager.getInstance().createNewDriverInstance(null, null, null);
		Assert.assertNull(driverInstance);

		DriverInstance driverInstance2 = DriverManager.getInstance().createNewDriverInstance(TEST_DRIVER_1_TEMPLATE_ID, null, null);
		Assert.assertNull(driverInstance2);

		DriverInstance driverInstance3 = DriverManager.getInstance().createNewDriverInstance(TEST_DRIVER_1_TEMPLATE_ID, TEST_DRIVER_1_INSTANCE_NAME, null);
		Assert.assertNull(driverInstance3);

		DriverInstance driverInstance4 = DriverManager.getInstance().createNewDriverInstance(TEST_DRIVER_1_TEMPLATE_ID, TEST_DRIVER_1_INSTANCE_NAME, "");
		Assert.assertNotNull(driverInstance4);
	}

	public void testGetDriverInstanceByID() {
		if (DriverManager.getInstance().getDriverInstanceByID(TEST_DRIVER_2_INSTANCE_ID) != null) {
			DriverInstance driverInstance = DriverManager.getInstance().getDriverInstanceByID(TEST_DRIVER_2_INSTANCE_ID);
			Assert.assertNotNull(driverInstance);
		}
	}

	public void testGetFullJarList() {
		if (DriverManager.getInstance().getDriverInstanceByID(TEST_DRIVER_1_INSTANCE_ID2) == null) {
			testCreateNewDriverInstance();
		}
		else {
			DriverManager.getInstance().removeDriverInstance(TEST_DRIVER_1_INSTANCE_ID2);
			testCreateNewDriverInstance();
		}
		Assert.assertNotNull(DriverManager.getInstance().getDriverInstanceByID(TEST_DRIVER_1_INSTANCE_ID2));
		Assert.assertTrue(DriverManager.getInstance().getFullJarList().indexOf("dummy1.jar") > -1); //$NON-NLS-1$
		Assert.assertTrue(DriverManager.getInstance().getFullJarList().indexOf("dummy2.jar") > -1); //$NON-NLS-1$
		System.out.println("Full driver list: " + DriverManager.getInstance().getFullJarList()); //$NON-NLS-1$
	}

	public void testGetFullJarListAsArray() {
		if (DriverManager.getInstance().getDriverInstanceByID(TEST_DRIVER_1_INSTANCE_ID2) == null) {
			testCreateNewDriverInstance();
		}
		String[] array = DriverManager.getInstance().getFullJarListAsArray();
		DriverInstance[] diarray = DriverManager.getInstance().getValidDriverInstances();
		ArrayList list = new ArrayList();
		for (int i = 0; i < diarray.length; i++) {
			DriverInstance di = diarray[i];
			if (di.getJarList() != null && di.getJarList().length() > 0 ) {
				for (int j = 0; j < di.getJarListAsArray().length; j++) {
					if (!list.contains(di.getJarListAsArray()[j]))
						list.add(di.getJarListAsArray()[j]);
				}
			}
		}
		Assert.assertTrue(array.length == list.size());
	}

	public void testRemoveDriverInstance() {
		if (DriverManager.getInstance().getDriverInstanceByID(TEST_DRIVER_1_INSTANCE_ID2) == null) {
			testCreateNewDriverInstance();
		}
		boolean flag = DriverManager.getInstance().removeDriverInstance(TEST_DRIVER_1_INSTANCE_ID2);
		assertTrue(flag);
		System.out.println("Test Driver 1 Instance Removed: " + flag); //$NON-NLS-1$
	}

	public void testResetDriverInstances() {
		DriverManager.getInstance().removeDriverInstance(TEST_DRIVER_1_INSTANCE_ID);
		Assert.assertNull(DriverManager.getInstance().getDriverInstanceByID(TEST_DRIVER_1_INSTANCE_ID));
		DriverManager.getInstance().resetDefaultInstances();
		Assert.assertNotNull(DriverManager.getInstance().getDriverInstanceByID(TEST_DRIVER_1_INSTANCE_ID));
	}

	public void testGetInstance() {
		DriverManager dm = DriverManager.getInstance();
		assertNotNull(dm);
	}
}
