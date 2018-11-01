/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui.dialogs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.datatools.connectivity.drivers.DriverInstance;
import org.eclipse.datatools.connectivity.drivers.DriverManager;
import org.eclipse.datatools.connectivity.drivers.models.DriversProvider;
import org.eclipse.datatools.connectivity.internal.ui.DriverTreeContentProvider;

public class DriverListContentProvider extends DriverTreeContentProvider {

	public Object[] getElements(Object element) {
		if (element instanceof DriversProvider) {
			DriverInstance[] dinstances = 
				DriverManager.getInstance().getAllDriverInstances();
			ArrayList list = new ArrayList();
			for (int i = 0; i < dinstances.length; i++) {
				list.add(dinstances[i].getPropertySet());
			}
			return list.toArray();
		}
		return super.getElements(element);
	}

	public List getDriverInstances() {
		DriverInstance[] dinstances = 
			DriverManager.getInstance().getAllDriverInstances();
		ArrayList list = new ArrayList();
		for (int i = 0; i < dinstances.length; i++) {
			list.add(dinstances[i].getPropertySet());
		}
		return list;
	}

}
