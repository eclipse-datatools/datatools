/*******************************************************************************
 * Copyright (c) 2001, 2004, 2008 IBM Corporation and others. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which accompanies this distribution,
 * and is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: IBM Corporation - initial API and implementation
 ******************************************************************************/

package org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.filter;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionFilter;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfo;
import org.eclipse.jface.viewers.StructuredSelection;

/**
 * @author ledunnel
 */
public class FilterNodeFilterPropertyPage extends ConnectionFilterPropertyPage {

	public FilterNodeFilterPropertyPage() {
		super();
		//setHideSelectionOption(true);
	}

	public void setElement(IAdaptable element) {
		super.setElement(element);
		// The following is for backward compatibility for those relying on 
		IFilterNode filterNode = getFilterNode();
		if (filterNode == null) {
			selection = null;
		}
		else {
			selection = new StructuredSelection(filterNode);
		}
	}

	public ConnectionFilter getConnectionFilter() {
		IFilterNode filterNode = getFilterNode();
		if (filterNode == null) {
			return null;
		}

		ConnectionInfo connection = filterNode.getParentConnection();
		if (connection == null) {
			return null;
		}
		return connection.getFilter(filterNode.getFilterName());
	}

	protected IConnectionProfile getConnectionProfile() {
		IFilterNode filterNode = getFilterNode();
		if (filterNode == null) {
			return null;
		}

		ConnectionInfo connection = filterNode.getParentConnection();
		if (connection instanceof IConnection) {
			return ((IConnection)connection).getConnectionProfile();
		}
		return null;
	}
	
	protected IFilterNode getFilterNode() {
		IAdaptable element = this.getElement();
		IFilterNode filterNode = (IFilterNode) element
				.getAdapter(IFilterNode.class);
		if (filterNode == null) {
			filterNode = (IFilterNode) Platform.getAdapterManager()
					.loadAdapter(element, IFilterNode.class.getName());
		}
		return filterNode;
	}

	protected String getConnectionFilterType() {
		IFilterNode filterNode = getFilterNode();
		if (filterNode == null) {
			return null;
		}
		return filterNode.getFilterName();
	}

}