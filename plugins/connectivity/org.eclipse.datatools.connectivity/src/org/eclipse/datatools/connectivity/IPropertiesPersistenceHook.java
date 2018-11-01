/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity;

import java.util.Properties;

public interface IPropertiesPersistenceHook {

	/**
	 * Returns the property set that should be persisted by the framework. This
	 * allows extensions to prevent certain properties from being persisted by
	 * the framework (e.g. authentication information, derived properties,
	 * etc.). This method is invoked by the framework just prior to the
	 * properties being persisted.
	 * 
	 * @param props the properties currently associated with a connection
	 *        profile
	 * 
	 * @return the properties to be persisted by the framework
	 */
	Properties getPersitentProperties(Properties props);

	/**
	 * Returns a property set which includes any transient or derived properties
	 * that must be set prior to usage. This allows the provider to alter the
	 * property set when it is being loaded (e.g. for migration purposes, etc.).
	 * This method is invoked by the framework after the properties have been
	 * loaded, but prior to them being associated with the connection profile.
	 * 
	 * @param props the properties loaded from the workspace
	 * 
	 * @return the modified property set
	 */
	Properties populateTransientProperties(Properties props);

	/**
	 * Allows the framework to determine whether or not a connection dialog
	 * needs to be displayed prior to opening a connection to the server (e.g.
	 * authentication information is missing). This call is not intended to
	 * verify that the information will allow for a successful connection, just
	 * that the information is complete enough to make an attempt.
	 * 
	 * @param props the property set to inspect
	 * 
	 * @return true if the property set is complete; false if additional
	 *         information must be specified.
	 */
	boolean arePropertiesComplete(Properties props);

	/**
	 * Returns the ID of the property page used to collect the information
	 * required to make the property set complete.
	 * 
	 * @return the ID of the property page used to collect the missing required
	 *         properties
	 */
	String getConnectionPropertiesPageID();

}
