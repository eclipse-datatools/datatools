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

import org.eclipse.datatools.connectivity.drivers.jdbc.IJDBCDriverDefinitionConstants;
import org.eclipse.datatools.connectivity.drivers.models.CategoryDescriptor;
import org.eclipse.datatools.connectivity.drivers.models.TemplateDescriptor;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.osgi.util.TextProcessor;
import org.eclipse.swt.graphics.Image;

public class DriverTreeTableLabelProvider extends LabelProvider implements
		ITableLabelProvider {

	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	public String getColumnText(Object element, int columnIndex) {
		if (element instanceof TemplateDescriptor) {
			TemplateDescriptor td = (TemplateDescriptor) element;

			String name = TextProcessor.process(td.getName());
			String vendor = 
				td.getPropertyValueFromId(IJDBCDriverDefinitionConstants.DATABASE_VENDOR_PROP_ID);
			String version = 
				td.getPropertyValueFromId(IJDBCDriverDefinitionConstants.DATABASE_VERSION_PROP_ID);
			
			if (vendor == null)
				vendor = ""; //$NON-NLS-1$
			if (version == null)
				version = ""; //$NON-NLS-1$
			DatabaseDefinition dbDef =
				RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(vendor, version);

			String versionDisplay = null;
			String vendorDisplay = null;
			if (dbDef != null) {
				versionDisplay = TextProcessor.process(dbDef.getVersionDisplayString());
				vendorDisplay = TextProcessor.process(dbDef.getProductDisplayString());
			}

			switch (columnIndex) {
			case 0:
				return name;
			case 1: 
				return vendorDisplay;
			case 2:
				return versionDisplay;
			default:
				return ""; //$NON-NLS-1$
			}
		}
		else if (element instanceof CategoryDescriptor) {
			CategoryDescriptor cd = (CategoryDescriptor) element;
			String name = cd.getName();
			switch (columnIndex) {
				case 0:
					return name;
				default:
					return ""; //$NON-NLS-1$
			}
		}
		return new String();
	}

	public String getText(Object element) {
		if (element instanceof TemplateDescriptor) {
			TemplateDescriptor td = (TemplateDescriptor) element;
			String name = td.getName();
			return name;
		}
		else if (element instanceof CategoryDescriptor) {
			CategoryDescriptor cd = (CategoryDescriptor) element;
			String name = cd.getName();
			return name;
		}
		return super.getText(element);
	}

}
