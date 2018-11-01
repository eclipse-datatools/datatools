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
package org.eclipse.datatools.connectivity.ui.navigator;

import org.eclipse.datatools.connectivity.ICategory;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.internal.ui.LocalRepositoryNode;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

public class ConnectionProfileSorter extends ViewerSorter {

	public int compare(Viewer viewer, Object e1, Object e2) {
		// Make sure the local repository node is the first item in the tree
		if (e1 instanceof LocalRepositoryNode) {
			return -1;
		}
		else if (e2 instanceof LocalRepositoryNode) {
			return 1;
		}
		if (e1 instanceof IConnectionProfile && e2 instanceof IConnectionProfile) {
			IConnectionProfile icp1 = (IConnectionProfile) e1;
			IConnectionProfile icp2 = (IConnectionProfile) e2;
			return icp1.getName().compareToIgnoreCase(icp2.getName());
		}
		if (e1 instanceof ICategory && e2 instanceof ICategory) {
			ICategory icat1 = (ICategory) e1;
			ICategory icat2 = (ICategory) e2;
			return icat1.getName().compareToIgnoreCase(icat2.getName());
		}
		return super.compare(viewer, e1, e2);
	}

}
