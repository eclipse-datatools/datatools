/*******************************************************************************
 * Copyright (c) 2004-2005, 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 * 			IBM Corporation - defect fix for 231005
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.IProfileListener;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonContentProvider;
import org.eclipse.ui.navigator.IExtensionStateModel;
import org.eclipse.ui.navigator.INavigatorContentService;

/**
 * Base content provider class that can be extended for adding content to a
 * connection profile using a navigatorContent extension.
 * 
 * Manages the associations between IConnectionProfile, IContentExtension and
 * the connection object. Content from the connection object on down is provided
 * by the delegate content provider.
 * 
 * This class allows clients to plug-in existing content providers for a
 * connection type.
 * 
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
	private Viewer mViewer;
	private IProfileListener mProfileListener = new IProfileListener() {

		public void profileAdded(IConnectionProfile profile) {
			handleProfileAdded(profile);
		}

		public void profileChanged(IConnectionProfile profile) {
			handleProfileChanged(profile);
		}

		public void profileDeleted(IConnectionProfile profile) {
			handleProfileDeleted(profile);
		}
		
	};

	/**
	 * The delegate content provider should be minimally capable of handling
	 * the connection object wrapped by the content extension.
	 * 
	 * @param contentProvider the delegate content provider.
	 */
	protected CommonContentProviderBase(ITreeContentProvider contentProvider) {
		super();
		mDelegate = contentProvider;
	}

	/**
	 * Create a content extension object for the specified profile
	 * 
	 * @param profile
	 * 
	 * @return a new content extension object
	 */
	protected abstract IContentExtension createContentExtension(
			IConnectionProfile profile);

	public void dispose() {
		mDelegate.dispose();
		ProfileManager.getInstance().removeProfileListener(mProfileListener);
		for (Iterator it = mProfileToExtensionNode.values().iterator(); it
				.hasNext();) {
			((IContentExtension) it.next()).dispose();
		}
		mProfileToExtensionNode.clear();

		mStateModel = null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.navigator.ICommonContentProvider#init(org.eclipse.ui.navigator.IExtensionStateModel, org.eclipse.ui.IMemento)
	 */
	public void init(IExtensionStateModel aStateModel, IMemento aMemento) {
		mStateModel = aStateModel;
	}

	public void init(ICommonContentExtensionSite aConfig) {
		mStateModel = aConfig.getExtensionStateModel();
		ProfileManager.getInstance().addProfileListener(mProfileListener);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.navigator.IMementoAware#restoreState(org.eclipse.ui.IMemento)
	 */
	public void restoreState(IMemento aMemento) {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.navigator.IMementoAware#saveState(org.eclipse.ui.IMemento)
	 */
	public void saveState(IMemento aMemento) {
	}

	/**
	 * @return the delegate contente provider
	 */
	protected ITreeContentProvider getDelegate() {
		return mDelegate;
	}

	/**
	 * @return the state model
	 */
	protected IExtensionStateModel getStateModel() {
		return mStateModel;
	}

	/**
	 * @param profile
	 * 
	 * @return the content extension for the specified profile.  A content
	 * extension will be created if one does not already exist.
	 */
	public IContentExtension getContentExtension(IConnectionProfile profile) {
		//make sure we create contentExtension only after profile's connected.
		if(profile.getConnectionState() == IConnectionProfile.DISCONNECTED_STATE) {
			return null;
		}
		IContentExtension extension = (IContentExtension) mProfileToExtensionNode
				.get(profile);
		if (extension == null) {
			extension = createContentExtension(profile);
			mProfileToExtensionNode.put(profile, extension);
			if (profile.getConnectionState() == IConnectionProfile.CONNECTED_STATE) {
				try {
					extension.openConnection();
				} catch (ExceptionInInitializerError e) {
					ConnectivityUIPlugin.getDefault().log(e);
					extension.closeConnection();
				} catch (Throwable e2) {
					ConnectivityUIPlugin.getDefault().log(e2);
					extension.closeConnection();
				}
			}
		}
		return extension;
	}

	/*
	 * Returns the children of the specified parentElement.
	 * 
	 * If the parent element is a IConnectionProfile, a IContentExtension is
	 * returned if the IContentExtension.isVisible() or if more than one
	 * extension exists, otherwise it returns
	 * getChildren(IContentExtension.getConnection()).
	 * 
	 * If the parent is a IContentExtension,
	 * getChildren(IContentExtension.getConnection()) is returned.
	 * 
	 * If the parent is anything else, mDelegate.getChildren() is invoked.
	 */
	public Object[] getChildren(Object parentElement) {
		Object[] children = null;
		if (parentElement instanceof IConnectionProfile) {
			IContentExtension extension = getContentExtension((IConnectionProfile) parentElement);
			if (extension == null || extension.getConnection() == null
					|| extension.getConnection().getConnectException() != null) {
				children = new Object[0];
			}
			else {
				children = extensionVisible(extension) ? new Object[] { extension}
						: getChildren(extension);
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

	/*
	 * If the elemet is an IContentExtension,
	 * IContentExtension.getConnectionProfile() is returned.
	 * 
	 * If the element is a "connection" object, the IContentExtension associated
	 * with that connection is returned if the content extension is visible,
	 * otherwise the connection profile is returned.
	 * 
	 * For all other objects, mDelegate.getParent() is returned.
	 */
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
				if (parent != null
						&& !extensionVisible((IContentExtension) parent)) {
					parent = getParent(parent);
				}
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
				return extensionVisible(extension) ? true
						: hasChildren(extension);
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
		mViewer = viewer;
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

	private boolean extensionVisible(IContentExtension extension) {
		if (!(mViewer instanceof CommonViewer)) {
			// Short circuit if the viewer is not a CommonViewer
			return true;
		}
		CommonViewer viewer = (CommonViewer) mViewer;
		INavigatorContentService contentService = viewer
				.getNavigatorContentService();
		Set contentExtensions = contentService
				.findRootContentExtensions(extension.getConnectionProfile());

		return extension.isVisible();
	}
	
	protected void handleProfileAdded(IConnectionProfile profile) {
	}
	
	protected void handleProfileChanged(IConnectionProfile profile) {
	}
	
	protected void handleProfileDeleted(IConnectionProfile profile) {
		if (mProfileToExtensionNode.containsKey(profile)) {
			IContentExtension contentExtension = (IContentExtension) mProfileToExtensionNode
					.remove(profile);
			if (contentExtension != null) {
				contentExtension.dispose();
			}
		}
	}

}