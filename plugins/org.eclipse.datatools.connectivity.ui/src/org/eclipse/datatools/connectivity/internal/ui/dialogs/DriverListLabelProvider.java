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

import org.eclipse.datatools.connectivity.drivers.DriverInstance;
import org.eclipse.datatools.connectivity.drivers.IPropertySet;
import org.eclipse.datatools.connectivity.drivers.jdbc.IJDBCDriverDefinitionConstants;
import org.eclipse.datatools.connectivity.internal.ui.DriverTreeLabelProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

public class DriverListLabelProvider extends DriverTreeLabelProvider implements ITableLabelProvider {

	public String getText(Object element) {
		if (element instanceof IPropertySet) {
			return ((IPropertySet)element).getName();
		}
		return super.getText(element);
	}

	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	public String getColumnText(Object element, int columnIndex) {
		if (element instanceof IPropertySet) {
			IPropertySet pset = (IPropertySet) element;
			DriverInstance di = new DriverInstance(pset);
			if (columnIndex == 0)
				return di.getName();
			else if (columnIndex == 1) {
				String vendor = 
					di.getNamedPropertyByID(IJDBCDriverDefinitionConstants.DATABASE_VENDOR_PROP_ID);
				if (vendor != null && vendor.trim().length() > 0) {
					return vendor;
				}
			}
			else if (columnIndex == 2) {
				String version = 
					di.getNamedPropertyByID(IJDBCDriverDefinitionConstants.DATABASE_VERSION_PROP_ID);
				if (version != null && version.trim().length() > 0) {
					return version;
				}
			}
		}
		return null;
	}

}
