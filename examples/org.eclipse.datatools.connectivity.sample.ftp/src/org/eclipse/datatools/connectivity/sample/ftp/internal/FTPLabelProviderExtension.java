/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf & mdow - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.sample.ftp.internal;

import org.eclipse.datatools.connectivity.ui.CommonLabelProviderBase;

public class FTPLabelProviderExtension extends CommonLabelProviderBase {

	public FTPLabelProviderExtension() {
		super(new FtpLabelProvider());
	}

}
