/*******************************************************************************
 * Copyright (c) 2005-2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package  org.eclipse.datatools.connectivity.ui.dse.dialogs.filters;

import org.eclipse.datatools.connectivity.ICategory;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.internal.ui.LocalRepositoryNode;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

/**
 * Filters the DSE's viewer so only categories and profiles appear
 * @author brianf
 *
 */
public class DownToProfilesOnlyFilter extends ViewerFilter {

	/**
	 * Only show down to the category or profile node
	 *  
	 */
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if (element instanceof ICategory) {
			return true;
		}
		else if (element instanceof IConnectionProfile) {
			return true;
		}
		else if (element instanceof LocalRepositoryNode) {
            return true;
		}
		return false;
	}

}
