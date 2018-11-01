/*******************************************************************************
 * Copyright (c) 2004-2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich, shongxum - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui;

import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.swt.graphics.Image;

/**
 * This interface is used by the CommonContentProviderBase for managing user
 * extensions to connection profiles through navigatorContent extensions.
 * 
 * @author rcernich, shongxum
 * 
 * Created on Jan 15, 2004
 */
public interface IContentExtension {

	/**
	 * @return the connection profile extended by this object.
	 */
	IConnectionProfile getConnectionProfile();

	/**
	 * When invoked, this object should open a connection to the referenced
	 * connection profile and return the root elements to be displayed under
	 * this elements node in the tree. If the connection is already open, the
	 * existing nodes should be returned.
	 * 
	 * @return the root nodes to display under the connection profile
	 */
	void openConnection();

	/**
	 * This method should close any active connection referenced by this object.
	 */
	void closeConnection();

	/**
	 * @return the connection used by this extension.
	 */
	IConnection getConnection();

	/**
	 * This method should dispose of any resources allocated by this object,
	 * including closing open connections to the referenced connection profile.
	 */
	void dispose();

	/**
	 * @return the label to be displayed for this extension node.
	 */
	String getLabel();

	/**
	 * @return the image to be displayed for this extension node.
	 */
	Image getImage();

	/**
	 * @return true if this content extension node should be visible
	 */
	boolean isVisible();
}
