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
package org.eclipse.datatools.connectivity.sample.cp;

import org.eclipse.datatools.connectivity.sample.cp.ui.FileLabelProvider;
import org.eclipse.datatools.connectivity.ui.CommonLabelProviderBase;

public class DirectoryLabelProviderExtension extends CommonLabelProviderBase {

	public DirectoryLabelProviderExtension() {
		super(new FileLabelProvider());
	}

}
