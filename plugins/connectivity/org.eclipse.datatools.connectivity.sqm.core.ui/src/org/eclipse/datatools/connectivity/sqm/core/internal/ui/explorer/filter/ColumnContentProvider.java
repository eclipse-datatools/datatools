/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.filter;

import java.util.ArrayList;

import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionFilter;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class ColumnContentProvider implements IStructuredContentProvider {
	private ArrayList predicates;

	public ColumnContentProvider(ConnectionFilter connFilter) {
		predicates = connFilter.getPredicatesCollection();
	}

	public Object[] getElements(Object parent) {
		return predicates.toArray();
	}

	public void dispose() {
	}

	public void inputChanged(Viewer v, Object oldInput, Object newInput) {
	}
}
