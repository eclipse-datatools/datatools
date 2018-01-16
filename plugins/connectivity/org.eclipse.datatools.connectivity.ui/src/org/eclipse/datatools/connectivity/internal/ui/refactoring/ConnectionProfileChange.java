/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui.refactoring;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.ltk.core.refactoring.Change;

/**
 * Used by the rename change
 * @author brianf
 *
 */
public abstract class ConnectionProfileChange extends Change {

	protected IConnectionProfile mSource;
	protected IConnectionProfile mTarget;

	/**
	 * @param source
	 * @param target
	 */
	public ConnectionProfileChange ( IConnectionProfile source, IConnectionProfile target ) {
		super();
		mSource = source;
		mTarget = target;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.Change#getAffectedObjects()
	 */
	public Object[] getAffectedObjects() {
		return new Object[] {mSource};
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.Change#getModifiedElement()
	 */
	public Object getModifiedElement() {
		return mTarget;
	}

}
