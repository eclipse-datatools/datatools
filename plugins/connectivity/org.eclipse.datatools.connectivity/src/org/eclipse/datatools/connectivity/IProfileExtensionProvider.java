/*******************************************************************************
 * Copyright (c) 2004-2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity;

/**
 * The interface used to access functionality provided by a profile extension.
 * 
 * @author rcernich
 * 
 * Created on Jan 16, 2004
 */
public interface IProfileExtensionProvider {

	/**
	 * @return the id of this profile extension
	 */
	String getId();

	/**
	 * @return the name of this profile extension
	 */
	String getName();

	/**
	 * @return the connection profile provider extended by this extension
	 */
	IConnectionProfileProvider getConnectionProfileProvider();

}
