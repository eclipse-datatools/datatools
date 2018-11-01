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
package org.eclipse.datatools.connectivity.internal.ui;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.datatools.connectivity.drivers.DriverMgmtMessages;
import org.eclipse.datatools.connectivity.drivers.IPropertySet;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * Properties table label provider
 * 
 * @author brianf
 */
public class PropertiesLabelProvider implements ITableLabelProvider {

	// local property set
	private IPropertySet mPropSet;

	/**
	 * Constructor
	 * 
	 * @param pset
	 */
	public PropertiesLabelProvider(IPropertySet pset) {
		this.mPropSet = pset;
	}

	public void setPropertySet(IPropertySet pset) {
		this.mPropSet = pset;
	}

	public IPropertySet getPropertySet() {
		return this.mPropSet;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ITableItemLabelProvider#getColumnText(java.lang.Object,
	 *      int)
	 */
	public String getColumnText(Object object, int columnIndex) {
		String val = ""; //$NON-NLS-1$

		if (object instanceof IConfigurationElement) {
			IConfigurationElement prop = (IConfigurationElement) object;
			String name = prop.getAttribute("name"); //$NON-NLS-1$
			String id = prop.getAttribute("id"); //$NON-NLS-1$
			String value = prop.getAttribute("value"); //$NON-NLS-1$
			String visible = prop.getAttribute("visible"); //$NON-NLS-1$
			boolean propvisible = true;
			if (visible != null && visible.equals("false")) //$NON-NLS-1$
				propvisible = false;
			if (propvisible) {
				switch (columnIndex) {
				case 0:
					val = name;
					break;
				case 1:
					val = value;
					if (this.mPropSet != null) {
						String propval = this.mPropSet.getBaseProperties()
								.getProperty(id);
						if (propval != null && propval.length() > 0) {
							val = propval;
						}
					}
					break;
				}
			}
		}

		return val == null ? "" : val; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object,
	 *      int)
	 */
	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
	 */
	public void addListener(ILabelProviderListener listener) {
		// empty
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
	 */
	public void dispose() {
		// empty
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object,
	 *      java.lang.String)
	 */
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
	 */
	public void removeListener(ILabelProviderListener listener) {
		// empty
	}
}
