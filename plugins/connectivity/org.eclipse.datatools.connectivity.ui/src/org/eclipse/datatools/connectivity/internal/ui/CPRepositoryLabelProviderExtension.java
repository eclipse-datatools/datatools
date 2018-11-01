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

import org.eclipse.datatools.connectivity.ui.CommonLabelProviderBase;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;

public class CPRepositoryLabelProviderExtension extends CommonLabelProviderBase
		implements ILabelProvider {

	public CPRepositoryLabelProviderExtension() {
		super(new LabelProvider());
	}

}
