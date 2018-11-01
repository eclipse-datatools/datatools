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

import java.net.URL;
import java.util.Map;

/**
 * This interface is used for accessing functionality provided by a connection
 * profile extension.
 * 
 * @author rcernich
 * 
 * Created on Jan 14, 2004
 */
public interface IConnectionProfileProvider {

	/**
	 * @return the name of this connection profile provider
	 */
	String getName();

	/**
	 * @return the id of this connection profile provider
	 */
	String getId();

	/**
	 * @return the URL for the icon of this connection profile provider
	 */
	URL getIconURL();

	/**
	 * This method returns a connection factory object that corresponds to the
	 * type specified. The type must match the id of a connection factory
	 * extension.
	 * 
	 * @param type the type or id of a connection factory associated with this
	 *        connection profile type.
	 * 
	 * @return a connection factory provider. null if no factory exists with the
	 *         specified type.
	 */
	IConnectionFactoryProvider getConnectionFactory(String type);

	/**
	 * This method returns all the connection factories associated with this
	 * connection profile. The data is returned in a Map object as follows:
	 * (String id,IConnectionFactoryProvider provider)
	 * 
	 * @return the connection factories defined for this connection profile
	 */
	Map getConnectionFactories();

	/**
	 * This method returns all the profile extensions associated with this
	 * connection profile. The data is returned in a Map object as follows:
	 * (String id,IProfileExtensionProvider provider)
	 * 
	 * @return the profile extensions defined for this connection profile
	 */
	Map getProfileExtensions();

	/**
	 * The configuration type of this connection profile.
	 * 
	 * @return the configuration type supported by this connection profile
	 */
	IConfigurationType getConfigurationType();

	/**
	 * The category this connection profile belongs to.
	 * 
	 * @return the category this connection profile belongs to.
	 */
	ICategory getCategory();

	/**
	 * The file extension associated with workspace resources of this connection
	 * profile type.
	 * 
	 * @return the file extension of workspace resources belonging to this
	 *         connection profile type.
	 */
	String getFileExtension();

	/**
	 * This property is used to determin whether we enable "Connect" or
	 * "Disconnect" for a connection profile.
	 * 
	 * @return true if the connection profile is responsible to maintain a
	 *         connection.
	 */
	boolean needsMaintainConnection();
}
