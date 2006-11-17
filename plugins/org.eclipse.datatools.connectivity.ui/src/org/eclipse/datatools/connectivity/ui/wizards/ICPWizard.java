/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: shongxum - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui.wizards;

import org.eclipse.datatools.connectivity.IConnectionProfile;

/**
 * @author shongxum
 */
public interface ICPWizard {

	public void initProviderID(String providerID);
	
	public void setParentProfile(IConnectionProfile profile);
}
