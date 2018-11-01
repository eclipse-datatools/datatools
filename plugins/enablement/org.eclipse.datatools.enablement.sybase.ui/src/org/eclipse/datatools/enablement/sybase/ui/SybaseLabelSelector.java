/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.enablement.sybase.ui;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.services.LabelSelector;

public class SybaseLabelSelector implements LabelSelector {

	public boolean select(Object element) {
		return true;
	}

}
