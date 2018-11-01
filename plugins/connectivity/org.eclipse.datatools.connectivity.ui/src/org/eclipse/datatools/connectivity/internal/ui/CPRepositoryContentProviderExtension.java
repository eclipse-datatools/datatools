/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    rcernich - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.datatools.connectivity.internal.ui;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ui.CommonContentProviderBase;
import org.eclipse.datatools.connectivity.ui.IContentExtension;
import org.eclipse.jface.viewers.ITreeContentProvider;


public class CPRepositoryContentProviderExtension extends
		CommonContentProviderBase implements ITreeContentProvider {

	public CPRepositoryContentProviderExtension() {
		super(new CPRepositoryContentProvider());
	}

	protected IContentExtension createContentExtension(
			IConnectionProfile profile) {
		return new CPRepositoryContentExtension(profile);
	}

}
