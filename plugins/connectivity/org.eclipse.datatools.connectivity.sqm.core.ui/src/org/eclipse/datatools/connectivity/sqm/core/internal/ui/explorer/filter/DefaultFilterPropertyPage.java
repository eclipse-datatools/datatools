/*******************************************************************************
 * Copyright (c) 2006, 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 *          IBM Corporation - fix for defect 222691
 ******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.filter;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionFilter;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionFilterImpl;

public abstract class DefaultFilterPropertyPage extends ConnectionFilterPropertyPage {

	public DefaultFilterPropertyPage() {
		super();
		setHideSelectionOption(true);
	}

	public ConnectionFilter getConnectionFilter() {
		IConnectionProfile profile = getConnectionProfile();
		if (profile == null) {
			return null;
		}

		String predicate = profile.getProperties(
				ConnectionFilter.FILTER_SETTINGS_PROFILE_EXTENSION_ID)
				.getProperty(getConnectionFilterType());
		if (predicate == null || predicate.length() == 0) {
			return null;
		}
		return new ConnectionFilterImpl(predicate);
	}

	protected IConnectionProfile getConnectionProfile() {
		IAdaptable element = getElement();
		if (element instanceof IConnectionProfile) {
			return (IConnectionProfile) element;
		}
		return null;
	}

}
