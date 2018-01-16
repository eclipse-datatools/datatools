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
