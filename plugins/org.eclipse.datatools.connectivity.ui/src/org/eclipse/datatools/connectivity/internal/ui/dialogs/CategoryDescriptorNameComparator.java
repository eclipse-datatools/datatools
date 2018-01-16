/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui.dialogs;

import java.util.Comparator;

import org.eclipse.datatools.connectivity.drivers.models.CategoryDescriptor;

public class CategoryDescriptorNameComparator implements Comparator {

	public int compare(Object arg0, Object arg1) {
		if (arg0 instanceof CategoryDescriptor &&
				arg1 instanceof CategoryDescriptor) {
			CategoryDescriptor cd0 = (CategoryDescriptor) arg0;
			CategoryDescriptor cd1 = (CategoryDescriptor) arg1;
			return cd0.getName().compareToIgnoreCase(cd1.getName());
		}
		return 0;
	}
	
}