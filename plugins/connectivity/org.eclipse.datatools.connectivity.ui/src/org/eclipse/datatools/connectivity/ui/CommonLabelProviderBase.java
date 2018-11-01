/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui;

import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IServerVersionProvider;
import org.eclipse.datatools.connectivity.Version;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonLabelProvider;
import org.eclipse.ui.navigator.IExtensionStateModel;

import com.ibm.icu.text.MessageFormat;

/**
 * Base label provider class that can be extended for adding content to a
 * connection profile using a navigatorContent extension.
 * 
 * Manages the labels for IContentExtension. Labels from the connection object
 * on down is provided by the delegate label provider.
 * 
 * This class allows clients to plug-in existing label providers for a
 * connection type.
 * 
 */
public class CommonLabelProviderBase implements ICommonLabelProvider {

	private IExtensionStateModel mStateModel;
	private ITreeContentProvider mContentProvider;
	private ILabelProvider mDelegate;

	public CommonLabelProviderBase(ILabelProvider delegate) {
		super();
		mDelegate = delegate;
	}

	public void init(IExtensionStateModel aStateModel,
			ITreeContentProvider aContentProvider) {
		mStateModel = aStateModel;
		mContentProvider = aContentProvider;
	}

	public String getDescription(Object anElement) {
		return getText(anElement);
	}

	public void restoreState(IMemento aMemento) {
	}

	public void saveState(IMemento aMemento) {
	}

	public Image getImage(Object element) {
		if (element instanceof IContentExtension) {
			return ((IContentExtension) element).getImage();
		}
		else {
			return mDelegate.getImage(element);
		}
	}

	public String getText(Object element) {
		if (element instanceof IContentExtension) {
			IContentExtension ice = (IContentExtension) element;
			IConnection conn = ice.getConnection();
			StringBuffer sb = new StringBuffer(ice.getLabel());

			if (conn instanceof IServerVersionProvider) {
				IServerVersionProvider isvp = (IServerVersionProvider) conn;
				Version techVersion = isvp.getTechnologyVersion();
				String techName = isvp.getTechnologyName();
				if (techName != null && techName.length() > 0) {
					String versionStr = MessageFormat.format(
							ConnectivityUIPlugin.getDefault().getResourceString("CommonLabelProviderBase.label.version"),  //$NON-NLS-1$
							new String[] {sb.toString(),
								techName,
								techVersion.toString()});
					sb = new StringBuffer(versionStr);
//					sb.append(" ("); //$NON-NLS-1$
//					sb.append(techName).append(' ');
//					sb.append(ConnectivityUIPlugin.getDefault().getResourceString("CommonLabelProviderBase.label.version"));//$NON-NLS-1$
//					sb.append(' ' + techVersion.toString()).append(')');
				}
			}
			return sb.toString();
		}
		else {
			return mDelegate.getText(element);
		}
	}

	public void addListener(ILabelProviderListener listener) {
		mDelegate.addListener(listener);
	}

	public boolean isLabelProperty(Object element, String property) {
		return mDelegate.isLabelProperty(element, property);
	}

	public void removeListener(ILabelProviderListener listener) {
		mDelegate.removeListener(listener);
	}

	public void dispose() {
		mDelegate.dispose();

		mStateModel = null;
		mContentProvider = null;
	}

	protected ITreeContentProvider getContentProvider() {
		return mContentProvider;
	}

	protected IExtensionStateModel getStateModel() {
		return mStateModel;
	}

	protected ILabelProvider getDelegate() {
		return mDelegate;
	}

	public boolean equals(Object obj) {
		if (obj instanceof CommonLabelProviderBase) {
			return super.equals(obj);
		}
		return false;
	}

	public int hashCode() {
		return mDelegate.hashCode();
	}

	public void init(ICommonContentExtensionSite aConfig) {
		// TODO Auto-generated method stub
		
	}

}
