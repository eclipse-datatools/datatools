/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: shongxum - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;

/**
 * @author shongxum
 */
public class ConnectionProfilePropertySource implements IPropertySource {

	private static final String P_ID_ENTITY_NAME = "org.eclipse.datatools.connectivity.properties.general.cpname"; //$NON-NLS-1$

	private static final String P_ID_ENTITY_DESC = "org.eclipse.datatools.connectivity.properties.general.cpdesc"; //$NON-NLS-1$

	private static final String P_ID_ENTITY_AUTOCONNECT = "org.eclipse.datatools.connectivity.properties.general.cpautoconnect"; //$NON-NLS-1$

	private static final String P_ID_ENTITY_TYPE = "org.eclipse.datatools.connectivity.properties.general.cptype"; //$NON-NLS-1$

	private IConnectionProfile mCP;
	private boolean _showAutoConnect = true;

	public ConnectionProfilePropertySource(IConnectionProfile cp) {
		mCP = cp;
		_showAutoConnect = mCP.getProvider().needsMaintainConnection();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getEditableValue()
	 */
	public Object getEditableValue() {
		return mCP;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyDescriptors()
	 */
	public IPropertyDescriptor[] getPropertyDescriptors() {
		PropertyDescriptor desName = new PropertyDescriptor(P_ID_ENTITY_NAME,
				ConnectivityUIPlugin.getDefault().getResourceString(
						"properties.connectionprofile.name")); //$NON-NLS-1$
		desName.setCategory(ConnectivityUIPlugin.getDefault()
				.getResourceString("properties.category.general")); //$NON-NLS-1$
		PropertyDescriptor desDesc = new PropertyDescriptor(P_ID_ENTITY_DESC,
				ConnectivityUIPlugin.getDefault().getResourceString(
						"properties.connectionprofile.desc")); //$NON-NLS-1$
		desDesc.setCategory(ConnectivityUIPlugin.getDefault()
				.getResourceString("properties.category.general")); //$NON-NLS-1$

		PropertyDescriptor desType = new PropertyDescriptor(P_ID_ENTITY_TYPE,
				ConnectivityUIPlugin.getDefault().getResourceString(
						"properties.connectionprofile.type")); //$NON-NLS-1$
		desType.setCategory(ConnectivityUIPlugin.getDefault()
				.getResourceString("properties.category.general")); //$NON-NLS-1$

		if (!_showAutoConnect) {
			return new IPropertyDescriptor[] { desName, desType};
		}
		PropertyDescriptor autoConnectType = new PropertyDescriptor(
				P_ID_ENTITY_AUTOCONNECT, ConnectivityUIPlugin.getDefault()
						.getResourceString(
								"properties.connectionprofile.autoconnect")); //$NON-NLS-1$
		autoConnectType.setCategory(ConnectivityUIPlugin.getDefault()
				.getResourceString("properties.category.general")); //$NON-NLS-1$

		return new IPropertyDescriptor[] { desName, desDesc, desType, autoConnectType};
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		if (id.equals(P_ID_ENTITY_NAME)) {
			return mCP.getName();
		}
		else if (id.equals(P_ID_ENTITY_DESC)) {
			return mCP.getDescription();
		}
		else if (id.equals(P_ID_ENTITY_TYPE)) {
			return mCP.getProviderName();
		}
		else if (id.equals(P_ID_ENTITY_AUTOCONNECT)) {
			if (!_showAutoConnect) {
				return new Boolean(true);
			}
			else {
				return new Boolean(mCP.isAutoConnect());
			}
		}
		else {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#isPropertySet(java.lang.Object)
	 */
	public boolean isPropertySet(Object id) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#resetPropertyValue(java.lang.Object)
	 */
	public void resetPropertyValue(Object id) {
		// Do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object,
	 *      java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		// Do nothing
	}
}
