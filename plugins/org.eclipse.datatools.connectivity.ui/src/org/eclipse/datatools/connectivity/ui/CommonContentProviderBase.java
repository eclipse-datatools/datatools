/*******************************************************************************
 * Copyright (c) 2004-2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.IExtensionStateModel;
import org.eclipse.ui.navigator.internal.extensions.ICommonContentProvider;

/**
 * @author rcernich
 * 
 * Created on Apr 20, 2004
 */
public abstract class CommonContentProviderBase implements
		ICommonContentProvider {

	private Map mProfileToExtensionNode = new HashMap();
	private Map mConnectionToExtensionNode = new HashMap();
	private ITreeContentProvider mDelegate;
	private IExtensionStateModel mStateModel;

	protected CommonContentProviderBase(ITreeContentProvider contentProvider) {
		super();
		mDelegate = contentProvider;
	}

	protected abstract IContentExtension createContentExtension(
			IConnectionProfile profile);

	public void dispose() {
		mDelegate.dispose();
		for (Iterator it = mProfileToExtensionNode.values().iterator(); it
				.hasNext();) {
			((IContentExtension) it.next()).dispose();
		}
		mProfileToExtensionNode.clear();

		mStateModel = null;
	}

	public void init(IExtensionStateModel aStateModel, IMemento aMemento) {
		mStateModel = aStateModel;
	}

	public void restoreState(IMemento aMemento) {
	}

	public void saveState(IMemento aMemento) {
	}

	protected ITreeContentProvider getDelegate() {
		return mDelegate;
	}

	protected IExtensionStateModel getStateModel() {
		return mStateModel;
	}

	public IContentExtension getContentExtension(IConnectionProfile profile) {
		IContentExtension extension = (IContentExtension) mProfileToExtensionNode
				.get(profile);
		if (extension == null) {
			extension = createContentExtension(profile);
			mProfileToExtensionNode.put(profile, extension);
			if (profile.isConnected()) {
				extension.openConnection();
			}
		}
		return extension;
	}

	public Object[] getChildren(Object parentElement) {
		Object[] children = null;
		if (parentElement instanceof IConnectionProfile) {
			IContentExtension extension = getContentExtension((IConnectionProfile) parentElement);
			if (extension == null || extension.getConnection() == null
					|| extension.getConnection().getConnectException() != null) {
				children = new Object[0];
			}
			else {
				children = new Object[] { extension};
			}
		}
		else if (parentElement instanceof IContentExtension) {
			IConnection ic = ((IContentExtension) parentElement)
					.getConnection();
			Object connection = ic.getRawConnection();
			children = mDelegate.getChildren(connection);
			mConnectionToExtensionNode.put(connection, parentElement);
		}
		else {
			children = mDelegate.getChildren(parentElement);
		}
		return children;
	}

	public Object getParent(Object element) {
		Object parent = null;
		if (element instanceof IConnectionProfile) {
			// BEGIN WORKAROUND - Navigator framework bug (for some reason,
			// we're responsible for this type).
			// parent = getNavigatorExtensionSite()
			// .getContentExtension(
			// "org.eclipse.datatools.connectivity.ui.views.dataSourceExplorer")
			// .getContentProvider().getParent(element);
			// END WORKAROUND - Navigator framework bug.
		}
		else if (element instanceof IContentExtension) {
			parent = ((IContentExtension) element).getConnectionProfile();
		}
		else {
			parent = mDelegate.getParent(element);
			// check to see if the returned parent is the connection
			if (mConnectionToExtensionNode.containsKey(parent)) {
				parent = mConnectionToExtensionNode.get(parent);
			}
		}
		return parent;
	}

	public boolean hasChildren(Object element) {
		if (element instanceof IConnectionProfile) {
			IContentExtension extension = getContentExtension((IConnectionProfile) element);
			if (extension == null || extension.getConnection() == null
					|| extension.getConnection().getConnectException() != null) {
				return false;
			}
			else {
				return true;
			}
		}
		if (element instanceof IContentExtension) {
			IConnection ic = ((IContentExtension) element).getConnection();
			return mDelegate.hasChildren(ic.getRawConnection());
		}
		else {
			return mDelegate.hasChildren(element);
		}
	}

	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		mDelegate.inputChanged(viewer, oldInput, newInput);
	}

	public boolean equals(Object obj) {
		if (obj instanceof CommonContentProviderBase) {
			return ((CommonContentProviderBase) obj).mDelegate
					.equals(mDelegate);
		}
		return false;
	}

	public int hashCode() {
		return mDelegate.hashCode();
	}

}