/*******************************************************************************
 * Copyright (c) 2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;

/**
 * Placeholder node for local connection profile repository
 * 
 * @author rcernich
 * 
 * Created on Mar 22, 2007
 */
public class LocalRepositoryNode implements IAdaptable {

	public Object getAdapter(Class adapter) {
		if (adapter.isInstance(this)) {
			return this;
		}
		return Platform.getAdapterManager().getAdapter(this, adapter);
	}

	public int hashCode() {
		return 1;
	}

	public boolean equals(Object obj) {
		return obj instanceof LocalRepositoryNode;
	}

}
