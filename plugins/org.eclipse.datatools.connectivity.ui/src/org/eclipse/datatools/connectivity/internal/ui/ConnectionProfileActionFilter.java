/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui;

import org.eclipse.core.expressions.IPropertyTester;
import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.datatools.connectivity.ConnectionProfileConstants;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ui.IConnectionProfileActionFilter;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

public class ConnectionProfileActionFilter extends PropertyTester implements IConnectionProfileActionFilter, IPropertyTester {

	public ConnectionProfileActionFilter() {
		super();
	}

	public boolean testAttribute(Object target, String name, String value) {
		if (target == null || !(target instanceof IConnectionProfile)) {
			return false;
		}

		IConnectionProfile profile = (IConnectionProfile) target;
		if (name.equals(PROFILE_PROPERTY_PROFILE_TYPE_ID) || name.equals(TYPE_ID)) {
			return profile.getProviderId().equals(value);
		}
		else if (name.equals(PROFILE_PROPERTY_CATEGORY_ID) || name.equals(CATEGORY_ID)) {
			return profile.getCategory().getId().equals(value);
		}
		else if (name.equals(PROFILE_PROPERTY_HAS_EXTENDED_PROPERTIES) || name.equals(HAS_EXTENDED_PROPERTIES)) {
			return profile.getProvider().getProfileExtensions().size() != 0;
		}
		else if (name.equals(PROFILE_PROPERTY_PROPERTY_EXTENSION_ID) || name.equals(EXTENSION_ID)) {
			return profile.getProvider().getProfileExtensions().containsKey(
					value);
		}
		else if (name.equals(PROFILE_PROPERTY_MAINTAIN_CONNECTION) || name.equals(MAINTAIN_CONNECTION)) {
			return profile.getProvider().needsMaintainConnection();
		}
		else if (name.equals(PROFILE_PROPERTY_IS_CONNECTED) || name.equals(IS_CONNECTED)) {
			return profile.isConnected();
		}
		else if (name.equals(PROFILE_PROPERTY_SUPPORTS_PING) || name.equals(SUPPORTS_PING)) {
			return profile.getProvider().getConnectionFactories().containsKey(
					ConnectionProfileConstants.PING_FACTORY_ID);
		}
		else if (name.equals(PROFILE_PROPERTY_FACTORY_ID) || name.equals(FACTORY_ID)) {
			return profile.getProvider().getConnectionFactories().containsKey(
					value);
		}
		else if (name.equals(PROFILE_PROPERTY_VIEW_ID) || name.equals(VIEW_ID)) {
			IWorkbench workbench = PlatformUI.getWorkbench();
			IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
			IWorkbenchPage page = window.getActivePage();
			IWorkbenchPart part = page.getActivePart();
			if (part instanceof IViewPart) {
				IViewPart view = (IViewPart) part;
				String viewID = view.getViewSite().getId();
				return viewID.equals(value);
			}
			return false;
		}
		else {
			return false;
		}
	}

	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		return testAttribute(receiver, property, (String) expectedValue);
	}

}
