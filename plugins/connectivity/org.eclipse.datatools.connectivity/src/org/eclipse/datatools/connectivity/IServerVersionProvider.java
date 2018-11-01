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
package org.eclipse.datatools.connectivity;

/**
 * Provides version information about the server and technology supported by
 * the server to the connection profile framework.  The provider version is
 * only collected by connections created from the "ping" connection factory.
 * 
 * @author rcernich
 * 
 * Created on May 31, 2005
 */
public interface IServerVersionProvider {

	/**
	 * @return the version of the server (e.g. 12.5)
	 */
	Version getProviderVersion();

	/**
	 * @return the name of the provider (e.g. ASE)
	 */
	String getProviderName();

	/**
	 * @return the version of the technology running on the server (e.g. 1.1)
	 */
	Version getTechnologyVersion();

	/**
	 * @return the technology name; null if not supported (e.g. JMS)
	 */
	String getTechnologyName();

}