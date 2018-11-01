/*******************************************************************************
 * Copyright (c) 2005, 2008, 2009 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 *  Actuate Corporation - refactored to improve extensibility
 *  Sybase, Inc. - created public API version for easier extensibility
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui.wizards;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.internal.ui.wizards.NewCPWizard;
import org.eclipse.jface.viewers.ViewerFilter;

/**
 * @author brianf
 */
public class NewFilteredCPWizard extends NewCPWizard {

	public NewFilteredCPWizard() {
		super();
	}

	public NewFilteredCPWizard(ViewerFilter filter,
			IConnectionProfile parentProfile) {
		super(filter, parentProfile);
	}

	public NewFilteredCPWizard(ViewerFilter[] filters,
			IConnectionProfile parentProfile) {
		super(filters, parentProfile);
	}

}
