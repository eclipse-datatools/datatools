/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.filter;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionFilter;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionFilterImpl;

public abstract class DefaultFilterPropertyPage extends ConnectionFilterPropertyPage {

	public DefaultFilterPropertyPage() {
		super();
	}

	protected ConnectionFilter getConnectionFilter() {
		IConnectionProfile profile = getConnectionProfile();
		if (profile == null) {
			return null;
		}

		String predicate = profile.getBaseProperties()
				.getProperty(
						ConnectionFilter.FILTER_PROP_PREFIX
								+ getConnectionFilterType());
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
