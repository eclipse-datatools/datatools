/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.internal.repository.IConnectionProfileRepository;
import org.eclipse.datatools.connectivity.ui.ManagedContentExtensionBase;
import org.eclipse.datatools.connectivity.ui.Messages;
import org.eclipse.swt.graphics.Image;

public class CPRepositoryContentExtension extends ManagedContentExtensionBase {

	public CPRepositoryContentExtension(IConnectionProfile profile) {
		super(profile, IConnectionProfileRepository.class.getName());
	}

	public Image getImage() {
		return SharedImages.get(SharedImages.IMG_CVIEW_EXPLORER);
	}

	public String getLabel() {
		return Messages.CPRepositoryContentExtension_CPExtensionName;
	}

	public boolean isVisible() {
		return false;
	}

}
