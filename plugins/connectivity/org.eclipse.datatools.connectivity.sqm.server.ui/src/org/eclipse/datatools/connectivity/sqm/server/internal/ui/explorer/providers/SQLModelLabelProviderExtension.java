/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers;

import org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers.label.ServerExplorerLabelProvider;
import org.eclipse.datatools.connectivity.ui.CommonLabelProviderBase;

/**
 * This class is a label provider implemention for navigatorContent
 * extensions. This class provides SQL model labels to the navigator.
 */
public class SQLModelLabelProviderExtension extends CommonLabelProviderBase {

	public SQLModelLabelProviderExtension() {
		super(new ServerExplorerLabelProvider());
	}

}
