/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
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
 * @author rcernich
 * 
 * Created on May 31, 2005
 */
public interface IServerVersionProvider {

	/**
	 * @return the version of the server (e.g. ASE 12.5)
	 */
	Version getProviderVersion();

	/**
	 * @return the name of the provider (e.g. ASE)
	 */
	String getProviderName();

	/**
	 * @return the version of the technology running on the server (e.g. JMS
	 *         1.1)
	 */
	Version getTechnologyVersion();

	/**
	 * @return the technology name; null if not supported (e.g. JMS)
	 */
	String getTechnologyName();

}