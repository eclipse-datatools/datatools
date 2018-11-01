/*******************************************************************************
 * Copyright (c) 2004-2006 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui.test;

import java.util.Map;
import java.util.Set;

import org.eclipse.datatools.connectivity.internal.ui.ConnectionProfileManagerUI;
import org.eclipse.datatools.connectivity.ui.wizards.IWizardCategoryProvider;
import org.eclipse.jface.wizard.IWizard;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * This tests the ConnectionProfileManagerUI class.
 * 
 * @author brianf
 *
 */
public class ConnectionProfileManagerUITests extends TestCase {

	public final void testGetInstance() {
		ConnectionProfileManagerUI cpmui = ConnectionProfileManagerUI.getInstance();
		Assert.assertNotNull(cpmui);
	}

	public final void testGetWizardCategories() {
		ConnectionProfileManagerUI cpmui = ConnectionProfileManagerUI.getInstance();
		Map wizCategories = cpmui.getWizardCategories();
		Assert.assertNotNull(wizCategories);
	}

	public final void testGetNewWizards() {
		ConnectionProfileManagerUI cpmui = ConnectionProfileManagerUI.getInstance();
		Map newWizards = cpmui.getNewWizards();
		Assert.assertNotNull(newWizards);
		Assert.assertTrue(!newWizards.isEmpty());
	}

	public final void testGetWizardCategory() {
		ConnectionProfileManagerUI cpmui = ConnectionProfileManagerUI.getInstance();
		Map wizCategories = cpmui.getWizardCategories();
		Set keys = wizCategories.keySet();
		if (keys.size() > 0) {
			Object[] keysArray = keys.toArray();
			String keys1 = (String) keysArray[0];
			IWizardCategoryProvider iwcp = cpmui.getWizardCategory(keys1);
			Assert.assertNotNull(iwcp);
		}
	}

	/*
	 * Test method for 'org.eclipse.datatools.connectivity.internal.ConnectionProfileManager.getNewWizard(String)'
	 * 
	 * Added to test BZ 129425
	 */
	public final void testGetNewWizard() {
		String dataSourceId = "bogusValue";
		IWizard wizard = ConnectionProfileManagerUI.getInstance().getNewWizard( dataSourceId );
		Assert.assertNull("Found bogus wizard", wizard);
		Map wizMap = ConnectionProfileManagerUI.getInstance().getNewWizards();
		Set keys = wizMap.keySet();
		if (keys.size() > 0) {
			Object[] keysArray = keys.toArray();
			String keys1 = (String) keysArray[0];
			IWizard wizard2 = ConnectionProfileManagerUI.getInstance().getNewWizard( keys1 );
			Assert.assertNotNull("Didn't find valid wizard", wizard2);
		}
	}

}
